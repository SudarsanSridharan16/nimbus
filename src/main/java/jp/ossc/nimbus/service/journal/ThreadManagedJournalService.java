/*
 * This software is distributed under following license based on modified BSD
 * style license.
 * ----------------------------------------------------------------------
 * 
 * Copyright 2003 The Nimbus Project. All rights reserved.
 * 
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 * 
 * 1. Redistributions of source code must retain the above copyright notice,
 *    this list of conditions and the following disclaimer. 
 * 2. Redistributions in binary form must reproduce the above copyright notice,
 *    this list of conditions and the following disclaimer in the documentation
 *    and/or other materials provided with the distribution.
 * 
 * THIS SOFTWARE IS PROVIDED BY THE NIMBUS PROJECT ``AS IS'' AND ANY EXPRESS
 * OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES
 * OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN
 * NO EVENT SHALL THE NIMBUS PROJECT OR CONTRIBUTORS BE LIABLE FOR ANY
 * DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF
 * THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 * 
 * The views and conclusions contained in the software and documentation are
 * those of the authors and should not be interpreted as representing official
 * policies, either expressed or implied, of the Nimbus Project.
 */
package jp.ossc.nimbus.service.journal;

import java.util.*;
import jp.ossc.nimbus.core.*;
import jp.ossc.nimbus.service.journal.editorfinder.*;
import jp.ossc.nimbus.service.writer.*;
import jp.ossc.nimbus.service.sequence.*;
import jp.ossc.nimbus.service.queue.*;
import jp.ossc.nimbus.service.queue.Queue;
import jp.ossc.nimbus.daemon.*;
import jp.ossc.nimbus.lang.*;

/**
 * �W���[�i���T�[�r�X�B<p>
 * 
 * @author H.Nakano
 */
public class ThreadManagedJournalService
 extends ServiceBase
 implements Journal, ThreadManagedJournalServiceMBean, DaemonRunnable, QueueHandler {
    
    private static final long serialVersionUID = 435149061357609295L;
    
    //�萔��`
    private static final String C_NOP = ""; //$NON-NLS-1$
    private String mWrKeyName = "JOURNAL";  //$NON-NLS-1$
    
    //�����o�[�ϐ�
    /** �G�f�B�^�[�T�[�r�X�}�l�[�W���[�� */
    private ServiceName mEditorFinderName;
    
    /** �G�f�B�^�[�T�[�r�X�}�l�[�W���[ */
    private EditorFinder mEditorFinder;
    
    /** �V�[�N�G���X�T�[�r�X�� */
    private ServiceName mSequenceServiceName;
    
    /** �V�[�N�G���X */
    private Sequence mSequence;
    
    /** ���N�G�X�g�I�u�W�F�N�g�X���b�h���[�J�� */
    private ThreadLocal mRequestLocal;
    
    /** �J�����g�X�e�b�v�X���b�h���[�J�� */
    private ThreadLocal mCurrentLocal;
    
    /** �J�����g�X�e�b�v�X���b�h���[�J�� */
    private ThreadLocal mStepLocal;
    
    /** Queue�T�[�r�X�� */
    private ServiceName mQueueServiceName;
    
    /** Queue */
    private Queue mQueue;
    
    /**
     * {@link #getQueueServiceName()}��null�̏ꍇ�A�f�t�H���g��{@link Queue}�T�[�r�X�Ƃ��Đ�������{@link DefaultQueueService}�T�[�r�X�B<p>
     */
    private DefaultQueueService defaultQueue;
    
    /**
     * �J�e�S���T�[�r�X���z��B<p>
     */
    private ServiceName[] categoryNames;
    
    /**
     * �J�e�S���T�[�r�X���X�g�B<p>
     */
    private List categories;
    
    private int writeDaemonSize = 1;
    
    /** Daemon�I�u�W�F�N�g */
    private Daemon[] mDaemon ;
    private int mJournalLevel;
    
    /**
     * �T�[�r�X�̐����������s���B<p>
     * �C���X�^���X�ϐ��̏��������s���B<br>
     * 
     * @exception Exception �T�[�r�X�̐��������Ɏ��s�����ꍇ
     */
    public void createService() throws Exception{
        //�X���b�h���[�J���쐬
        mRequestLocal = new ThreadLocal();
        mCurrentLocal = new ThreadLocal();
        mStepLocal = new ThreadLocal();
    }
    
    /**
     * EditorFinder��ݒ肷��B
     */
    public void setEditorFinder(EditorFinder editorFinder) {
        mEditorFinder = editorFinder;
    }
    
    /**
     * Queue��ݒ肷��B
     */
    public void setQueue(Queue queue) {
        mQueue = queue;
    }
    
    /**
     * Sequence��ݒ肷��B
     */
    public void setSequence(Sequence sequence) {
        mSequence = sequence;
    }
    
    /**
     * Category��ݒ肷��B
     */
    public void setCategories(List categories) {
        this.categories = categories;
    }

    /**
     * �T�[�r�X�̊J�n�������s���B<p>
     * 
     * @exception Exception �T�[�r�X�̊J�n�����Ɏ��s�����ꍇ
     */
    public void startService() throws Exception{
        //�T�[�r�X�擾
        mEditorFinder = (EditorFinder)ServiceManagerFactory.getServiceObject(
            mEditorFinderName
        );
        
        if(mSequenceServiceName != null){
            mSequence = (Sequence)ServiceManagerFactory.getServiceObject(
                mSequenceServiceName
            );
        }
        
        if(mQueueServiceName == null){
            if(mQueue == null) {
                if(getDefaultQueueService() == null){
                    final DefaultQueueService defaultQueue
                         = new DefaultQueueService();
                    defaultQueue.create();
                    defaultQueue.start();
                    setDefaultQueueService(defaultQueue);
                }else{
                    getDefaultQueueService().start();
                }
                mQueue = getDefaultQueueService();
            }
        }else{
            mQueue = (Queue)ServiceManagerFactory
                .getServiceObject(mQueueServiceName);
        }
        
        // �J�e�S���̓o�^
        if(categories == null) {
            categories = new ArrayList();
            final ServiceName[] categoryNames = getCategoryServiceNames();
            if(categoryNames != null){
                for(int i = 0; i < categoryNames.length; i++){
                    final ServiceName categoryName = categoryNames[i];
                    final Category category = (Category)ServiceManagerFactory
                        .getServiceObject(categoryName);
                    categories.add(category);
                }
            }
        }
        
        // �L���[�擾�҂����J�n����
        mQueue.accept();
        
        if(mQueue instanceof QueueHandlerContainer){
            ((QueueHandlerContainer)mQueue).setQueueHandler(this);
            ((QueueHandlerContainer)mQueue).start();
        }else{
            mDaemon = new Daemon[writeDaemonSize];
            for(int i = 0; i < writeDaemonSize; i++){
                mDaemon[i] = new Daemon(this);
                mDaemon[i].setName("Nimbus JournalWriterDaemon " + getServiceNameObject() + '[' + (i + 1) + ']');
                mDaemon[i].start();
            }
        }
    }
    
    /**
     * �T�[�r�X�̒�~�������s���B<p>
     * 
     * @exception Exception �T�[�r�X�̒�~�����Ɏ��s�����ꍇ
     */
    public void stopService(){
        
        if(mDaemon != null){
            for(int i = 0; i < mDaemon.length; i++){
                mDaemon[i].stop();
            }
        }
        
        if(mQueue instanceof QueueHandlerContainer){
            ((QueueHandlerContainer)mQueue).stop();
        }
        
        // �L���[�擾�҂����J������
        mQueue.release();
        
        // Queue�T�[�r�X�𖳖��T�[�r�X�Ƃ��Đ������Ă���ꍇ�A
        // ���̃T�[�r�X���~����
        if(mQueue == getDefaultQueueService()){
            getDefaultQueueService().stop();
            mQueue = null;
        }
        
        categories.clear();
        
        mSequence = null;
    }
    
    /**
     * �T�[�r�X�̔j���������s���B<p>
     * 
     * @exception Exception �T�[�r�X�̔j�������Ɏ��s�����ꍇ
     */
    public void destroyService(){
        mRequestLocal = null;
        mCurrentLocal = null;
        mStepLocal = null;
        
        //�T�[�r�X�擾
        mEditorFinder = null;
        mSequence = null ;
        
        // QueueFactory�T�[�r�X�𖳖��T�[�r�X�Ƃ��Đ������Ă���ꍇ�A
        // ���̃T�[�r�X��j������
        if(mQueue == getDefaultQueueService()){
            getDefaultQueueService().destroy();
            setDefaultQueueService(null);
        }
        mQueue = null;
        
        mDaemon = null;
        
        categories = null;
    }
    
    // Journal ��JavaDoc
    public String getRequestId(){
        if(mCurrentLocal == null){
            return null;
        }
        
        //Root���N�G�X�g�̃��N�G�X�gID���擾1
        JournalRecordImpl jr = (JournalRecordImpl)mCurrentLocal.get();
        if(jr != null){
            RequestJournal rj = (RequestJournal)jr.getObject();
            return rj.getRequestId();
        }
        return null;
    }
    
    // Journal ��JavaDoc
    public void setRequestId(String requestID){
        if(mCurrentLocal == null){
            return;
        }
        //Root���N�G�X�g�̃��N�G�X�gID��ݒ�
        JournalRecordImpl jr = (JournalRecordImpl)mCurrentLocal.get();
        if(jr != null){
            RequestJournalImpl rj = (RequestJournalImpl)jr.getObject();
            rj.setRequestId(requestID) ;
        }
    }
    
    // Journal ��JavaDoc
    public void startJournal(String key){
        startJournal(key, new Date(), null);
    }
    
    // Journal ��JavaDoc
    public void startJournal(
        String key ,
        Date startTime,
        EditorFinder finder
    ){
        if(getState() != STARTED){
            return;
        }
        JournalRecordImpl jr = (JournalRecordImpl)mRequestLocal.get();
        if(jr == null){
            
            String id = "";
            // �ʔԃT�[�r�X�����p�ł���Ȃ�΁A
            // �ʔԃT�[�r�X�𗘗p���ăC���N�������g
            if(mSequence != null){
                id = mSequence.increment();
            }
            //�V�K���N�G�X�g�I�u�W�F�N�g���쐬
            RequestJournalImpl rj = new RequestJournalImpl(false);
            rj.setStartTime(startTime);
            rj.setKey(key);
            rj.setRequestId(id);
            rj.setRoot(null,null);
            jr = new JournalRecordImpl();
            if(finder == null){
                jr.setEditorFinder(mEditorFinder);
            }else{
                jr.setEditorFinder(finder);
            }
            if(key == null){
                jr.setKey(C_NOP);
            }else{
                jr.setKey(key);
            }
            jr.setParamObj(rj);
            mRequestLocal.set(jr);
            mCurrentLocal.set(jr);
            mStepLocal.set(jr);
        }else{
            JournalRecordImpl curRec = (JournalRecordImpl)mCurrentLocal.get();
            JournalRecordImpl stepRec = (JournalRecordImpl)mStepLocal.get();
            RequestJournalImpl curStep
                 = (RequestJournalImpl)stepRec.getObject();
            RequestJournalImpl newStep = new RequestJournalImpl(true);
            newStep.setKey(key);
            newStep.setRequestId(curStep.getRequestId());
            newStep.setStartTime(startTime);
            newStep.setRoot(stepRec,curRec);
            if(finder==null){
                finder = mEditorFinder;
            }
            if(key == null){
                key = C_NOP;
            }
            JournalRecord newRec = curStep.setParamObj(key,finder,newStep);
            //�J�����g�X�e�b�v�ύX
            mCurrentLocal.set(newRec);
            mStepLocal.set(newRec);
        }
    }
    
    // Journal ��JavaDoc
    public void startJournal(String key, Date startTime){
        startJournal(key, startTime, null);
    }
    
    // Journal ��JavaDoc
    public void startJournal(String key, EditorFinder finder){
        startJournal(key, new Date(), finder);
    }
    
    public boolean isStartJournal(){
        return mRequestLocal.get() != null;
    }
    
    // Journal ��JavaDoc
    public void endJournal(){
        endJournal(new Date());
    }
    
    /**
     * �W���[�i�����L���[�ɏ������ށB<p>
     *
     * @param jr �W���[�i�����R�[�h
     */
    protected void writeJarnal(JournalRecordImpl jr){
        if(getState() != STARTED){
            return;
        }
        mQueue.push(jr);
    }
    
    // Journal ��JavaDoc
    public void endJournal(Date endTime){
        if(getState() != STARTED){
            return;
        }
        JournalRecordImpl curRec = (JournalRecordImpl)mCurrentLocal.get();
        JournalRecordImpl stepRec = (JournalRecordImpl)mStepLocal.get();
        if((curRec == null) || (stepRec == null)){
            throw new ServiceException(
                "JOURNALSERVICE001",
                "startJournal() and endJournal must be used in a pair."
            );
        }
        //�������̃X�e�b�v���I�����ԓo�^
        while(true){
            if(stepRec != null && curRec == stepRec){
                break ;
            }
            RequestJournalImpl step = (RequestJournalImpl)stepRec.getObject();
            step.setEndTime(endTime);
            stepRec = step.getStepRoot();
        }
        //���N�G�X�g�I�u�W�F�N�g���G�f�B�^�[�ɓn���B
        RequestJournalImpl curStep = (RequestJournalImpl) curRec.getObject();
        curStep.setEndTime(endTime);
        JournalRecordImpl rootRec = curStep.getCurRoot();
        JournalRecordImpl stepRec1 = curStep.getStepRoot();
        if(rootRec == null){
            writeJarnal(curRec);
            mRequestLocal.set(null);
            mCurrentLocal.set(null);
            mStepLocal.set(null);
        }else{
            mCurrentLocal.set(rootRec);
            mStepLocal.set(stepRec1);
        }
    }
    
    // Journal ��JavaDoc
    public void addInfo(String key, Object value,int level){
        //�ݒ肳�ꂽJournalLevel�ȉ��̂��̂͏o���Ȃ�
        if( level < this.getJournalLevel()){
            return;
        }
        addInfo(key,value);
    }
    
    // ThreadManagedJournalServiceMBean ��JavaDoc
    public int getJournalLevel(){
        return mJournalLevel;
    }
    
    // Journal ��JavaDoc
    public void addInfo(String key, Object value){
        if(getState() != STARTED){
            return;
        }
        JournalRecordImpl stepRec = (JournalRecordImpl)mStepLocal.get();
        if(stepRec != null){
            RequestJournalImpl step = (RequestJournalImpl)stepRec.getObject();
            step.setInfoObj(key,stepRec.getFinder(),value);
        }
    }
    
    // Journal ��JavaDoc
    public void addInfo(String key,Object value,EditorFinder finder){
        if(getState() != STARTED){
            return;
        }
        JournalRecordImpl stepRec = (JournalRecordImpl)mStepLocal.get();
        if(stepRec != null){
            RequestJournalImpl step = (RequestJournalImpl)stepRec.getObject();
            step.setInfoObj(
                key,finder == null ? stepRec.getFinder() : finder,value
            );
        }
    }
    
    // Journal ��JavaDoc
    public void addInfo(
        String key,
        Object value,
        EditorFinder finder,
        int level
    ){
        if(getState() != STARTED){
            return;
        }
        //�ݒ肳�ꂽJournalLevel�ȉ��̂��̂͏o���Ȃ�
        if(level < this.getJournalLevel()){
            return;
        }
        addInfo(key,value,finder);
    }
    
    public void removeInfo(int from){
        if(getState() != STARTED){
            return;
        }
        JournalRecordImpl stepRec = (JournalRecordImpl)mStepLocal.get();
        if(stepRec != null){
            RequestJournalImpl step = (RequestJournalImpl)stepRec.getObject();
            step.clearParam(from);
        }
    }
    
    // Journal ��JavaDoc
    public void addStartStep(String key){
        addStartStep(key, new Date(), null);
    }
    
    // Journal ��JavaDoc
    public void addStartStep(
        String key,
        EditorFinder finder
    ){
        addStartStep(key, new Date(), finder);
    }
    
    // Journal ��JavaDoc
    public void addStartStep(String key, Date startTime){
        addStartStep(key, startTime, null);
    }
    
    // Journal ��JavaDoc
    public void addStartStep(
        String key,
        Date startTime,
        EditorFinder finder
    ){
        if(getState() != STARTED){
            return;
        }
        JournalRecordImpl rootRec = (JournalRecordImpl)mRequestLocal.get();
        if(rootRec != null){
            JournalRecordImpl curRec = (JournalRecordImpl)mCurrentLocal.get();
            JournalRecordImpl stepRec = (JournalRecordImpl)mStepLocal.get();
            RequestJournalImpl curStep
                 = (RequestJournalImpl)curRec.getObject();
            RequestJournalImpl newStep = new RequestJournalImpl(true);
            newStep.setKey(key);
            newStep.setRequestId(curStep.getRequestId());
            newStep.setStartTime(startTime);
            newStep.setRoot(stepRec,curRec);
            if(finder == null){
                finder = mEditorFinder;
            }
            if(key == null){
                key = C_NOP;
            }
            JournalRecord newRec = curStep.setParamObj(key,finder,newStep);
            
            //�J�����g�X�e�b�v�ύX
            mStepLocal.set(newRec);
            mCurrentLocal.set(newRec);
        }
    }
    
    // Journal ��JavaDoc
    public void addEndStep(){
        addEndStep(new Date());
    }
    
    // Journal ��JavaDoc
    public void addEndStep(Date endTime){
        if(getState() != STARTED){
            return;
        }
        JournalRecordImpl curRec = (JournalRecordImpl)mStepLocal.get();
        if(curRec != null){
            //startJournal�Œǉ������X�e�b�v�͖���
            if(curRec.isStep() == false){
                return ;
            }
            RequestJournalImpl curStep
                 = (RequestJournalImpl)curRec.getObject();
            curStep.setEndTime(endTime);
            //�J�����g�X�e�b�v��POP
            JournalRecordImpl root = curStep.getCurRoot();
            if(root != null){
                    mCurrentLocal.set(root);
            }
            root = curStep.getStepRoot();
            if(root != null){
                mStepLocal.set(root);
            }
        }
    }
    
    // DaemonRunnable ��JavaDoc
    public boolean onStop(){
        return true;
    }
    
    // DaemonRunnable ��JavaDoc
    public boolean onSuspend(){
        return true;
    }
    
    // DaemonRunnable ��JavaDoc
    public boolean onResume(){
        return true;
    }
    
    /**
     * �L���[����W���[�i�����R�[�h�����o���B<p>
     *
     * @param ctrl �f�[��������I�u�W�F�N�g
     * @return �W���[�i�����R�[�h
     */
    public Object provide(DaemonControl ctrl){
        if(mQueue == null){
            return null;
        }
        return mQueue.get(1000);
    }
    
    /**
     * �W���[�i�����R�[�h��ҏW���ăJ�e�S���ɏ������ށB<p>
     * 
     * @param paramObj �W���[�i�����R�[�h
     * @param ctrl �f�[��������I�u�W�F�N�g
     */
    public void consume(Object paramObj, DaemonControl ctrl){
        if(paramObj == null){
            return;
        }
        JournalRecord rj = (JournalRecord)paramObj;
        Object journal = rj.toObject();
        final Map elements = new HashMap();
        
        elements.put(getWritableElementKey(), journal);
        if(categories != null){
            for(int i = 0, imax = categories.size(); i < imax; i++){
                final Category category = (Category)categories.get(i);
                if(category.isEnabled()){
                    try{
                        category.write(elements);
                    }catch(MessageWriteException e){
                        // ��������
                    }
                }
            }
        }
    }
    
    /**
     * �L���[����S�ẴW���[�i�����R�[�h�����o���āA�����B<p>
     */
    public void garbage(){
        // �L���[������΃L���[�̎c�茏�������O�������o��
        if(mQueue != null){
            //�L���[�̓��e���Ȃ��Ȃ�܂�
            while(mQueue.size() > 0){
                Object obj = mQueue.get(0);
                try{
                    consume(obj, null);
                }catch(Exception e){
                }
            }
        }
    }
    
    // DaemonRunnable ��JavaDoc
    public boolean onStart(){
        return true;
    }
    // QueueHandler ��JavaDoc
    public void handleDequeuedObject(Object obj) throws Throwable{
        if(obj == null){
            return;
        }
        consume(obj, null);
    }
    
    // QueueHandler ��JavaDoc
    public boolean handleError(Object obj, Throwable th) throws Throwable{
        return true;
    }
    
    // QueueHandler ��JavaDoc
    public void handleRetryOver(Object obj, Throwable th) throws Throwable{
    }
    
    // ThreadManagedJournalServiceMBean ��JavaDoc
    public void setEditorFinderName(ServiceName name){
        mEditorFinderName = name;
    }
    
    // ThreadManagedJournalServiceMBean ��JavaDoc
    public ServiceName getEditorFinderName(){
        return mEditorFinderName;
    }
    
    // ThreadManagedJournalServiceMBean ��JavaDoc
    public void setSequenceServiceName(ServiceName name){
        mSequenceServiceName = name;
    }
    
    public ServiceName getSequenceServiceName(){
        return mSequenceServiceName;
    }
    
    // ThreadManagedJournalServiceMBean��JavaDoc
    public void setCategoryServiceNames(ServiceName[] names){
        categoryNames = names;
    }
    
    // ThreadManagedJournalServiceMBean��JavaDoc
    public ServiceName[] getCategoryServiceNames(){
        return categoryNames;
    }
    
    // ThreadManagedJournalServiceMBean ��JavaDoc
    public void setQueueServiceName(ServiceName name){
        mQueueServiceName = name;
    }
    
    // ThreadManagedJournalServiceMBean ��JavaDoc
    public ServiceName getQueueServiceName(){
        return mQueueServiceName;
    }
    
    /**
     * Queue�T�[�r�X���w�肳��Ă��Ȃ��ꍇ�Ɏg�p����{@link DefaultQueueService}���擾����B<p>
     * ����DefaultQueueService�́A�����T�[�r�X�Ƃ��Đ��������B�܂��A{@link #setQueueServiceName(ServiceName)}��Queue���w�肳��Ă���ꍇ�́Anull��Ԃ��ꍇ������B<br>
     *
     * @return DefaultQueueService�I�u�W�F�N�g�B��������Ă��Ȃ��ꍇ��null��Ԃ��B
     */
    protected DefaultQueueService getDefaultQueueService(){
        return defaultQueue;
    }
    
    /**
     * Queue�T�[�r�X���w�肳��Ă��Ȃ��ꍇ�Ɏg�p����{@link DefaultQueueService}��ݒ肷��B<p>
     *
     * @param queue DefaultQueueService�I�u�W�F�N�g
     */
    protected void setDefaultQueueService(DefaultQueueService queue){
        defaultQueue = queue;
    }
    
    // ThreadManagedJournalServiceMBean ��JavaDoc
    public String getWritableElementKey(){
        return mWrKeyName;
    }
    
    // ThreadManagedJournalServiceMBean ��JavaDoc
    public void setWritableElementKey(String string){
        mWrKeyName = string;
    }
    
    // ThreadManagedJournalServiceMBean ��JavaDoc
    public void setJournalLevel(int level){
        mJournalLevel = level;
    }
    
    // ThreadManagedJournalServiceMBean ��JavaDoc
    public void setWriteDaemonSize(int size){
        writeDaemonSize = size;
    }
    // ThreadManagedJournalServiceMBean ��JavaDoc
    public int getWriteDaemonSize(){
        return writeDaemonSize;
    }
    
    /**
     * ���݂̃W���[�i���o�͕�������擾����B<p>
     * 
     * @param finderServiceName �W���[�i����ҏW����{@link JournalEditor}����������{@link EditorFinder}�T�[�r�X�̃T�[�r�X��
     * @return ���݂̃W���[�i���o�͕�����
     */
    public String getCurrentJournalString(ServiceName finderServiceName){
        EditorFinder finder = (EditorFinder)ServiceManagerFactory
            .getServiceObject(finderServiceName);
        return getCurrentJournalString(finder);
    }
    
    // Journal ��JavaDoc
    public String getCurrentJournalString(EditorFinder finder){
        JournalRecordImpl curRec = (JournalRecordImpl)mCurrentLocal.get();
        if(curRec == null){
            return "";
        }
        if(mEditorFinder != null){
            finder = mEditorFinder;
        }
        Object journal = finder == null || finder == mEditorFinder ? curRec.toObject() : curRec.toObject(finder);
        return journal == null ? null : journal.toString();
   }
}
