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
// �p�b�P�[�W
package jp.ossc.nimbus.service.performance;
// �C���|�[�g
import java.util.*;
import java.text.SimpleDateFormat;
import jp.ossc.nimbus.core.*;
import jp.ossc.nimbus.lang.*;
import jp.ossc.nimbus.util.*;
import jp.ossc.nimbus.daemon.*;
import jp.ossc.nimbus.service.queue.*;
import jp.ossc.nimbus.service.queue.Queue;
import jp.ossc.nimbus.service.writer.*;
//
/**
 * �X�^�e�B�X�e�B�N�X�Ǘ��N���X�B<BR>
 * �X�^�e�B�X�e�B�N�X�̌����A�o�^���s���B<BR>
 * @author H.Nakano
 * @version 1.00 
 */
public class FileReportPerformanceStatisticsService extends ServiceBase
 implements PerformanceStatistics, FileReportPerformanceStatisticsServiceMBean,
            DaemonRunnable{
    
    private static final long serialVersionUID = 3609558424484833722L;
    
    /** �f�t�H���g�C���^�[�o��(10��) */
    private static final String C_DEFAULT_INTERVAL = "600";
    
    /** �f�t�H���g�C���^�[�o��(10��) */
    private static final String C_SEP = "---";
    private static final String C_FORMAT = "yyyy-MM-dd HH-mm-ss";
    private static final SimpleDateFormat formatter
        = new SimpleDateFormat(C_FORMAT);
    
    /** MBean�Z�b�^�ێ��ϐ�(�\�[�g�L�[) */
    protected String mSortKey;
    /** MBean�Z�b�^�ێ��ϐ�(�����o���C���^�[�o���b) */
    protected String mIntervalSec = C_DEFAULT_INTERVAL;
    
    /** MBean�Z�b�^�ێ��ϐ��i�t�@�C�������o�����R�[�h�t�@�N�g���R���|�[�l���g���j*/
    protected ServiceName mWritableRecordFactoryName;
    /** �t�@�C�������o�����R�[�h�t�@�N�g���R���|�[�l���g*/
    protected WritableRecordFactoryService mWritableRecFac;
    
    /** MBean�Z�b�^�ێ��ϐ��i�t�@�C�������o���R���|�[�l���g���j*/
    protected ServiceName mWriterName;
    /** �t�@�C�������o���R���|�[�l���g */
    protected MessageWriter mWriter;
    
    /** MBean�Z�b�^�ێ��ϐ��i�L���[���i�R���|�[�l���g���j*/
    protected ServiceName mQueueName;
    /** �p�t�H�[�}���X�Z�o�L���[*/
    protected Queue mQueue;
    /**
     * {@link #getQueueServiceName()}��null�̏ꍇ�A�f�t�H���g��{@link Queue}�T�[�r�X�Ƃ��Đ�������{@link DefaultQueueService}�T�[�r�X�B<p>
     */
    private DefaultQueueService defaultQueue;
    
    /** �p�t�H�[�}���X�X�V�f�[�����X���b�h�I�u�W�F�N�g */
    protected Daemon mPerformDaemon;
    /** �p�t�H�[�}���X�o�̓f�[�����X���b�h�I�u�W�F�N�g */
    protected Daemon mWriterDaemon;
    
    /** �p�t�H�[�}���X�Ǘ��n�b�V�� */
    protected Hashtable mHash = null;
    /** �p�t�H�[�}���X���R�[�h�N���X�� */
    protected String mClassName = null;
    /** �p�t�H�[�}���X���R�[�h�N���X */
    protected Class mClsRec = null;
    
    /**
     * �R���X�g���N�^�B<BR>
     * Hash���C���X�^���V���O����Key���Z�b�g����B<BR>
     */
    public FileReportPerformanceStatisticsService(){
        super();
    }
    
    /**
     * �T�[�r�X�̐����������s���B<p>
     *
     * @exception �T�[�r�X�̐��������Ɏ��s�����ꍇ
     */
    public void createService() throws Exception{
        mHash = new Hashtable(1024,256);
    }
    
    /**
     * Queue��ݒ肷��B
     */
    public void setQueue(Queue queue) {
        mQueue = queue;
    }

    /**
     * WritableRecordFactoryService��ݒ肷��B
     */
    public void setWritableRecordFactoryService(WritableRecordFactoryService writableRecFac) {
        mWritableRecFac = writableRecFac;
    }

    /**
     * MessageWriter��ݒ肷��B
     */
    public void setMessageWriter(MessageWriter writer) {
        mWriter = writer;
    }

    /* (�� Javadoc)
     * @see jp.ossc.nimbus.core.ServiceBaseSupport#startService()
     */
    public void startService() throws Exception{
        if(mClsRec == null){
            throw new ServiceException("PEFORMANCE010","RecordClass is null");
        }
        
        // �t�@�C�������o���R���|�[�l���g�擾
        mWriter = (MessageWriter)ServiceManagerFactory.getServiceObject(
            mWriterName
        );
        
        // ���C�^�u�����R�[�h�t�@�N�g���R���|�[�l���g�擾
        mWritableRecFac = (WritableRecordFactoryService)ServiceManagerFactory
            .getServiceObject(mWritableRecordFactoryName);
        
        
        // Queue�T�[�r�X�̐����܂��͎擾
        if(getQueueServiceName() == null){
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
        }else{
            mQueue = (Queue)ServiceManagerFactory
                    .getServiceObject(getQueueServiceName());
        }
        
        // �����o���C���^�[�o���`�F�b�N
        if (mIntervalSec == null || "".equals(mIntervalSec)){
            mIntervalSec = C_DEFAULT_INTERVAL;
        }else{
            try{
                Long.parseLong(mIntervalSec);
            }catch(Exception ex){
                throw new ServiceException("PEFORMANCE013","interval setting invalid", ex);
            }
        }
        
        // �L���[��t�J�n
        mQueue.accept();
        
        // �p�t�H�[�}���X�X�V�f�[�����X���b�h����
        mPerformDaemon = new Daemon(this);
        // �t�@�C���o�̓f�[�����X���b�h����
        mWriterDaemon = new Daemon(new WriterDaemonRunnable(this));
        mPerformDaemon.start();
        mWriterDaemon.start();
    }
    /* (�� Javadoc)
     * @see jp.ossc.nimbus.core.ServiceBaseSupport#stopService()
     */
    public void stopService() throws Exception{
        clear();
        
        mPerformDaemon.stop();
        mWriterDaemon.stop();
        
        // �L���[��t��~
        mQueue.release();
        
        // Queue�T�[�r�X�𖳖��T�[�r�X�Ƃ��Đ������Ă���ꍇ�A
        // ���̃T�[�r�X���~����
        if(mQueue == getDefaultQueueService()){
            getDefaultQueueService().stop();
        }
    }
    /* (�� Javadoc)
     * @see jp.ossc.nimbus.core.ServiceBaseSupport#destroyService()
     */
    public void destroyService() throws Exception{
        mHash = null;
        mClassName = null;
        mClsRec = null;
        
        // Queue�T�[�r�X�𖳖��T�[�r�X�Ƃ��Đ������Ă���ꍇ�A
        // ���̃T�[�r�X��j������
        if(mQueue == getDefaultQueueService()){
            getDefaultQueueService().destroy();
            setDefaultQueueService(null);
        }
    }
    /* (�� Javadoc)
     * @see jp.ossc.nimbus.service.performance.PerformanceStatistics#entry(java.lang.String, long)
     */
    public void entry(String key, long msec) {
        if(key == null || key.length() == 0){
            return ;
        }
        final ArrayList enqueueList = new ArrayList(2);
        enqueueList.add(key);
        enqueueList.add(new Long(msec));
        // �p�t�H�[�}���X�G���g�����L���[��������B
        this.mQueue.push(enqueueList);
    }
    /* (�� Javadoc)
     * @see jp.ossc.nimbus.service.performance.FileReportPerformanceStatisticsServiceMBean#clear()
     */
    public void clear(){
        synchronized(mHash){
            this.mHash.clear();
        }
    }
    /* (�� Javadoc)
     * @see jp.ossc.nimbus.service.performance.FileReportPerformanceStatisticsServiceMBean#toStringAry(int, boolean)
     */
    public String[] toStringAry(int sortKey, boolean isUpset){
        // ���O���擾����
        final ArrayList sortList = new ArrayList();
        final CsvArrayList retAry = new CsvArrayList();
        Hashtable tb = null;
        synchronized(mHash){
            tb = (Hashtable)mHash.clone();
        }
        /** �f�[�^���X�g����ꍀ�ڂÂ��o���B*/
        for(Enumeration enumeration = tb.elements(); enumeration.hasMoreElements(); ){
            final PerformanceRecord item = (PerformanceRecord)enumeration.nextElement();
            if(item != null){
                //�L�[�\�[�g���\�b�h���R�[��
                _sortList(sortList, item, sortKey, isUpset);
            }
        }
        retAry.add(C_SEP);
        retAry.add(getNowDate());
        /** �L�[�\�[�g���X�g����o�͕����z��Ƀf�[�^��]�L */
        for(ListIterator iterator = sortList.listIterator(); iterator.hasNext(); ){
            //KEY�����f�[�^�擾�ECSV����
            final String sortItem = (String)iterator.next();
            final CsvArrayList keyAry = new CsvArrayList();
            keyAry.split(sortItem, ";");
            //�L�[��HASH����Ώۃp�t�H�[�}���X�}�l�[�W�������o���B
            if(mHash.containsKey(keyAry.getStr(0))){
                final PerformanceRecord item = (PerformanceRecord)mHash.get(keyAry.getStr(0));
                //�o�̓��X�g�Ƀp�t�H�[�}���X�����i�[
                retAry.add(item.toString());
            }
        }
        /** �o�� */
        final String[] retStrAry = retAry.toStringAry();
        return retStrAry;
    }
    //
    /**
     * �w��̃\�[�g�L�[�Ń\�[�g���s���B<BR>
     * @param sortList - �\�[�g���ʊi�[�z��
     * @param item - PerformanceManger�I�u�W�F�N�g
     * @param sortKey - �\�[�g�L�[
     * @param isUpset - �����A�~���̎w��
     */
    private void _sortList(ArrayList sortList, PerformanceRecord item, int sortKey, boolean isUpset){
        if(sortList == null || item == null){
            return;
        }
        String cmpKey = "";
        if(sortKey == C_NAME){
            cmpKey = item.getResourceId();
        }else if(sortKey == C_BEST){
            final Long tmpLong = new Long(item.getBestPerformance());
            cmpKey = tmpLong.toString();
        }else if(sortKey == C_WORST){
            final Long tmpLong = new Long(item.getWorstPerformance());
            cmpKey = tmpLong.toString();
        }else if(sortKey == C_AVERAGE){
            final Long tmpLong = new Long(item.getAveragePerformance());
            cmpKey = tmpLong.toString();
        }else if(sortKey == C_COUNT){
            final Long tmpLong = new Long(item.getCallTime());
            cmpKey = tmpLong.toString();
        }
        /** sort�f�[�^��������쐬����<BR>
         *    resourceId + ";" ��r�f�[�^  */
        final String rscId = item.getResourceId() + ";" + cmpKey;
        int entryCnt = 0;
        /** sortList�Ƀ\�[�g�C���T�[�g����B */
        for(ListIterator iterator = sortList.listIterator(); iterator.hasNext(); entryCnt++){
            //���X�g�̃R���y�A���ڂ����o���B
            final String destCmp = (String)iterator.next();
            final CsvArrayList parse = new CsvArrayList();
            parse.split(destCmp, ";");
            //�R���y�A
            final int ret = cmpKey.compareTo(parse.getStr(1));
            if(isUpset){
                if(ret <= 0){
                    break;
                }
            }else{
                if(ret >= 0){
                    break;
                }
            }
        }
        sortList.add(entryCnt, rscId);
    }
    /* (�� Javadoc)
     * @see jp.ossc.nimbus.service.performance.FileReportPerformanceStatisticsServiceMBean#setRecordClassName(java.lang.String)
     */
    public void setRecordClassName(String className) throws ServiceException{
        this.mClassName = className;
        if(className == null || className.length() == 0){
            this.mClsRec = null;
        }else{
            try{
                mClsRec = Class.forName(
                    className,
                    true,
                    NimbusClassLoader.getInstance()
                );
            }catch(ClassNotFoundException e){
                throw new ServiceException("PEFORMANCE010","ClassNotFoundException classnamse = " + className,e);//MOD 2004-02-09
            }
        }
    }
    /**
     * @return String - �p�t�H�[�}���X�N���X��
     * @see jp.ossc.nimbus.service.performance.FileReportPerformanceStatisticsServiceMBean#getRecordClassName()
     */
    public String getRecordClassName(){
        return this.mClassName;
    }
    /**
     * �L���[���i�R���|�[�l���g����ݒ肷��.
     * @param name - �L���[���i�R���|�[�l���g��
     */
    public void setQueueServiceName(ServiceName name){
        this.mQueueName = name;
    }
    /**
     * �L���[���i�R���|�[�l���g�����擾����B
     * @return ServiceName - �L���[���i�R���|�[�l���g��
     */
    public ServiceName getQueueServiceName(){
        return this.mQueueName;
    }
    
    /**
     * Queue���w�肳��Ă��Ȃ��ꍇ�Ɏg�p����{@link DefaultQueueService}���擾����B<p>
     * ����DefaultQueueService�́A�����T�[�r�X�Ƃ��Đ��������B�܂��A{@link #setQueueServiceName(ServiceName)}��Queue���w�肳��Ă���ꍇ�́Anull��Ԃ��ꍇ������B<br>
     *
     * @return DefaultQueueService�I�u�W�F�N�g�B��������Ă��Ȃ��ꍇ��null��Ԃ��B
     */
    protected DefaultQueueService getDefaultQueueService(){
        return defaultQueue;
    }
    
    /**
     * Queue���w�肳��Ă��Ȃ��ꍇ�Ɏg�p����{@link DefaultQueueService}��ݒ肷��B<p>
     *
     * @param queue DefaultQueueService�I�u�W�F�N�g
     */
    protected void setDefaultQueueService(DefaultQueueService queue){
        defaultQueue = queue;
    }
    
    /**
     * �t�@�C�������o���R���|�[�l���g����ݒ肷��B
     * @param name - �t�@�C�������o���R���|�[�l���g��
     */
    public void setWriterServiceName(ServiceName name){
        this.mWriterName = name;
    }
    /**
     * �t�@�C�������o���R���|�[�l���g����ݒ肷��B
     * @return - �t�@�C�������o���R���|�[�l���g��
     */
    public ServiceName getWriterServiceName(){
        return this.mWriterName;
    }
    /**
     * �t�@�C�������o�����R�[�h�t�@�N�g���R���|�[�l���g����ݒ肷��B
     * @param name - �t�@�C�������o�����R�[�h�t�@�N�g���R���|�[�l���g��
     */
    public void setWriteableRecordFactoryServiceName(ServiceName name){
        this.mWritableRecordFactoryName = name;
    }
    /**
     * �t�@�C�������o�����R�[�h�t�@�N�g���R���|�[�l���g�����擾����B
     * @return ServiceName - �t�@�C�������o�����R�[�h�t�@�N�g���R���|�[�l���g��
     */
    public ServiceName getWriteableRecordFactoryServiceName(){
        return this.mWritableRecordFactoryName;
    }
    /**
     * �t�@�C�������o���C���^�[�o��(�b)��ݒ肷��B
     * @param intervalSec - �C���^�[�o��(�b)
     */
    public void setWritableInterval(String intervalSec){
        this.mIntervalSec = intervalSec;
    }
    /**
     * �t�@�C�������o���C���^�[�o��(�b)���擾����B
     * @return String - �C���^�[�o��(�b)
     */
    public String getWritableInterval(){
        return this.mIntervalSec;
    }
    /**
     * �\�[�g�L�[��ݒ肷��B
     * @param inSortKey - �\�[�g�L�[
     */
    public void setSortKey(String inSortKey){
        this.mSortKey = inSortKey;
    }
    /**
     * �\�[�g�L�[���擾����B
     * @return String - �\�[�g�L�[
     */
    public String getSortKey(){
        return this.mSortKey;
    }
    /**
     * �f�[�����X���b�h�̒�~���F���s���B
     * @return true �X�g�b�v���F�Afalse �X�g�b�v�񏳔F
     */
    public boolean onStop(){
        return true;
    }
    /**
     * �f�[�����X���b�h�̃T�X�y���h���F���s���B
     * @return boolean - true:�T�X�y���h���F false:�T�X�y���h�񏳔F
     */
    public boolean onSuspend(){
        return true;
    }
    /**
     * �f�[�����X���b�h�̃��W���|�����F���s���B
     * @return boolean - true:���W���|�����F false:���W���|���񏳔F
     */
    public boolean onResume(){
        return true;
    }
    /**
     * �f�[�����X���b�h�̃u���b�L���O�������s���B�������L���[����W���[�i���I�u�W�F�N�g���擾����B
     *    @return Object - �W���[�i���I�u�W�F�N�g
     */
    public Object provide(DaemonControl ctrl){
        return this.mQueue.get();
    }
    /**
     * �f�[�����X���b�h�̏������s���B�p�t�H�[�}���X�X�V���s���B
     * @param paramObj - �W���[�i���I�u�W�F�N�g
     * @param ctrl - DaemonControl
     */
    public void consume(Object paramObj, DaemonControl ctrl) throws ServiceException{
        if(paramObj == null){
            return;
        }
        // �L���[����entry�f�[�^���擾����B
        final ArrayList entryList = (ArrayList)paramObj;
        final String key = (String)entryList.get(0);
        long msec = ((Long)entryList.get(1)).longValue();
        // �p�t�H�[�}���X�X�V
        PerformanceRecordOperator performanceObj = null;
        performanceObj = (PerformanceRecordOperator)mHash.get(key);
        if(performanceObj != null){
            performanceObj.entry(msec);
        }else{
            try{
                performanceObj = (PerformanceRecordOperator)mClsRec.newInstance();
            }catch(InstantiationException e){
                throw new ServiceException("PEFORMANCE001","InstantiationException",e);//MOD 2004-02-09
            }catch(IllegalAccessException e){
                throw new ServiceException("PEFORMANCE001","IllegalAccessException",e); //MOD 2004-02-09
            }
            performanceObj.setResourceId(key);
            performanceObj.entry(msec);
            mHash.put(key,performanceObj);
        }
    }
    /**
     * �f�[�����X���b�h�̌㏈�����s���B�������L���[�Ɏc���Ă���W���[�i�����O��S�ďo�͂���B
     */
    public void garbage(){
        if(mQueue == null){
            return;
        }
        //�������L���[�̓��e���Ȃ��Ȃ�܂�
        while(mQueue.size() > 0){
            Object obj = mQueue.get();
            try{
                consume(obj,mPerformDaemon);
            }catch(Exception e){
            }
        }
    }
    /**
     * �f�[�����X���b�h�̃X�^�[�g���F���s���B
     *    @return true �X�^�[�g���F�Afalse �X�^�[�g�񏳔F
     */
    public boolean onStart(){
        return true;
    }
    //
    private static final String getNowDate(){
        return formatter.format(new Date());
    }
}
