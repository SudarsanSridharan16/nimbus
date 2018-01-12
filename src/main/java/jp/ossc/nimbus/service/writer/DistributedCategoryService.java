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

package jp.ossc.nimbus.service.writer;

import jp.ossc.nimbus.core.*;
import jp.ossc.nimbus.daemon.*;
import jp.ossc.nimbus.service.queue.*;

/**
 * ���U�J�e�S���T�[�r�X�B<p>
 * �o�͐��I/O���\���J�o�[���邽�߂ɁA�o�͐�𕪎U����J�e�S�������N���X�B<br>
 *
 * @author M.Takata
 */
public class DistributedCategoryService extends ServiceBase
 implements DistributedCategoryServiceMBean{
    
    private static final long serialVersionUID = 1605519537623731512L;
    
    /**
     * ���̃J�e�S�����L�����ǂ����̃t���O�B<p>
     * �L���ȏꍇ�Atrue
     */
    protected boolean isEnabled = true;
    
    /**
     * ���U����J�e�S���̃T�[�r�X���z��B<p>
     */
    protected ServiceName[] categoryServiceNames;
    
    /**
     * ���U����J�e�S���̃T�[�r�X�z��B<p>
     */
    protected Category[] categories;
    
    /**
     * ���U�O�ɐ��񂳂���Queue�T�[�r�X�̃T�[�r�X���B<p>
     */
    protected ServiceName queueServiceName;
    
    /**
     * ���U�O�ɐ��񂳂���Queue�T�[�r�X�B<p>
     */
    protected Queue queue;
    
    /**
     * ���U�O�ɐ��񂳂���f�t�H���g��Queue�T�[�r�X�B<p>
     */
    protected DefaultQueueService defaultQueue;
    
    /**
     * ���U�O�ɐ��񂳂���Queue��I�����镪�UQueue�Z���N�^�T�[�r�X�̃T�[�r�X���B<p>
     */
    protected ServiceName distributedQueueSelectorServiceName;
    
    /**
     * ���U�O�ɐ��񂳂���Queue��I�����镪�UQueue�Z���N�^�B<p>
     */
    protected DistributedQueueSelector queueSelector;
    
    /**
     * ���U�O�ɐ��񂳂���Queue�T�[�r�X�t�@�N�g���T�[�r�X�̃T�[�r�X���B<p>
     */
    protected ServiceName queueFactoryServiceName;
    
    /**
     * ���U�������s���f�[�����X���b�h�z��B<p>
     */
    protected Daemon[] daemons;
    
    /**
     * ���U�����X���b�h���f�[���������邩�ǂ����̃t���O�B<p>
     */
    protected boolean isDaemon = true;
    
    /**
     * ���U�����X���b�h�̃X���b�h�D�揇�ʁB<p>
     */
    protected int threadPriority = -1;
    
    /**
     * ���U�����X���b�h�ŁA���U�����J�e�S���ɏo�͂��悤�Ƃ������ɁA��O�����������ꍇ�̃��O���b�Z�[�WID�B<p>
     */
    protected String writeErrorLogMessageId;
    
    // DistributedCategoryServiceMBean��JavaDoc
    public void setCategoryServiceNames(ServiceName[] names){
        categoryServiceNames = names;
    }
    
    // DistributedCategoryServiceMBean��JavaDoc
    public ServiceName[] getCategoryServiceNames(){
        return categoryServiceNames;
    }
    
    // DistributedCategoryServiceMBean��JavaDoc
    public void setQueueServiceName(ServiceName name){
        queueServiceName = name;
    }
    
    // DistributedCategoryServiceMBean��JavaDoc
    public ServiceName getQueueServiceName(){
        return queueServiceName;
    }
    
    // DistributedCategoryServiceMBean��JavaDoc
    public void setQueueFactoryServiceName(ServiceName name){
        queueFactoryServiceName = name;
    }
    
    // DistributedCategoryServiceMBean��JavaDoc
    public ServiceName getQueueFactoryServiceName(){
        return queueFactoryServiceName;
    }
    
    // DistributedCategoryServiceMBean��JavaDoc
    public void setDistributedQueueSelectorServiceName(ServiceName name){
        distributedQueueSelectorServiceName = name;
    }
    
    // DistributedCategoryServiceMBean��JavaDoc
    public ServiceName getDistributedQueueSelectorServiceName(){
        return distributedQueueSelectorServiceName;
    }
    
    // DistributedCategoryServiceMBean��JavaDoc
    public void setThreadPriority(int newPriority){
        threadPriority = newPriority;
    }
    // DistributedCategoryServiceMBean��JavaDoc
    public int getThreadPriority(){
        return threadPriority;
    }
    
    // DistributedCategoryServiceMBean��JavaDoc
    public void setDaemon(boolean isDaemon){
        this.isDaemon = isDaemon;
    }
    
    // DistributedCategoryServiceMBean��JavaDoc
    public boolean isDaemon(){
        return isDaemon;
    }
    
    // DistributedCategoryServiceMBean��JavaDoc
    public void setWriteErrorLogMessageId(String id){
        writeErrorLogMessageId = id;
    }
    // DistributedCategoryServiceMBean��JavaDoc
    public String getWriteErrorLogMessageId(){
        return writeErrorLogMessageId;
    }
    
    /**
     * ���U����J�e�S����ݒ肷��B<p>
     *
     * @param categories ���U����J�e�S���̔z��
     */
    public void setCategories(Category[] categories) {
        this.categories = categories;
    }
    
    /**
     * ���U�O�ɐ��񂳂���Queue��ݒ肷��B<p>
     *
     * @param container Queue
     */
    public void setQueue(Queue container){
        queue = container;
    }
    
    /**
     * ���U�O�ɐ��񂳂���Queue���擾����B<p>
     *
     * @return Queue
     */
    public Queue getQueue(){
        return queue;
    }
    
    /**
     * �T�[�r�X�̊J�n�������s���B<p>
     *
     * @exception Exception �T�[�r�X�̊J�n�����Ɏ��s�����ꍇ
     */
    public void startService() throws Exception{
        if(categoryServiceNames != null){
            categories = new Category[categoryServiceNames.length];
            for(int i = 0, max = categoryServiceNames.length; i < max; i++){
                categories[i] = (Category)ServiceManagerFactory
                    .getServiceObject(categoryServiceNames[i]);
            }
        }
        if(categories == null || categories.length == 0){
            throw new IllegalArgumentException("Categories is null.");
        }
        
        Queue[] queues = null;
        if(distributedQueueSelectorServiceName == null){
            
            if(queueServiceName != null){
                queue = (Queue)ServiceManagerFactory
                        .getServiceObject(queueServiceName);
            }else if(queueFactoryServiceName != null){
                queue = (Queue)ServiceManagerFactory
                        .getServiceObject(queueFactoryServiceName);
            }
            if(queue == null){
                defaultQueue = new DefaultQueueService();
                defaultQueue.create();
                defaultQueue.start();
                queue = defaultQueue;
            }
            queue.accept();
        }else{
            queueSelector = (DistributedQueueSelector)ServiceManagerFactory
                    .getServiceObject(distributedQueueSelectorServiceName);
            queues = queueSelector.getQueues();
            if(queues == null || categories.length != queues.length){
                throw new IllegalArgumentException("Categry and Queues is not match.");
            }
        }
        
        // �f�[�����N��
        final CategoryWriter[] writers = new CategoryWriter[categories.length];
        daemons = new Daemon[categories.length];
        for(int i = 0; i < categories.length; i++){
            writers[i] = new CategoryWriter();
            writers[i].category = categories[i];
            writers[i].queue = queues == null ? queue : queues[i];
            daemons[i] = new Daemon(writers[i]);
            daemons[i].setDaemon(isDaemon);
            daemons[i].setName(getServiceNameObject() + " CategoryWriter" + (i + 1));
            if(threadPriority > 0){
                daemons[i].setPriority(threadPriority);
            }
            daemons[i].start();
        }
    }
    
    /**
     * �T�[�r�X�̒�~�������s���B<p>
     *
     * @exception Exception �T�[�r�X�̒�~�����Ɏ��s�����ꍇ
     */
    public void stopService() throws Exception{
        
        // �f�[������~
        for(int i = 0; i < daemons.length; i++){
            daemons[i].stop();
            daemons[i] = null;
        }
        
        // �L���[��t��~
        if(queue != null){
            queue.release();
        }
        if(queueSelector != null){
            Queue[] queues = queueSelector.getQueues();
            if(queues != null){
                for(int i = 0; i < queues.length; i++){
                    queues[i].release();
                }
            }
        }
        
        daemons = null;
    }
    
    // Category��JavaDoc
    public boolean isEnabled(){
        return isEnabled;
    }
    
    // Category��JavaDoc
    public void setEnabled(boolean enable){
        isEnabled = enable;
    }
    
    // Category��JavaDoc
    public void write(Object elements) throws MessageWriteException{
        if(!isEnabled()){
            return;
        }
        Queue queue = null;
        if(queueSelector == null){
            queue = this.queue;
        }else{
            queue = queueSelector.selectQueue(elements);
        }
        if(queue != null){
            queue.push(elements);
        }
    }
    
    protected class CategoryWriter implements DaemonRunnable{
        
        protected Queue queue;
        
        protected Category category;
        
        /**
         * �f�[�������J�n�������ɌĂяo�����B<p>
         * 
         * @return ���true��Ԃ�
         */
        public boolean onStart() {
            return true;
        }
        
        /**
         * �f�[��������~�������ɌĂяo�����B<p>
         * 
         * @return ���true��Ԃ�
         */
        public boolean onStop() {
            return true;
        }
        
        /**
         * �f�[���������f�������ɌĂяo�����B<p>
         * 
         * @return ���true��Ԃ�
         */
        public boolean onSuspend() {
            return true;
        }
        
        /**
         * �f�[�������ĊJ�������ɌĂяo�����B<p>
         * 
         * @return ���true��Ԃ�
         */
        public boolean onResume() {
            return true;
        }
        
        /**
         * �L���[����P���o���ĕԂ��B<p>
         * 
         * @param ctrl DaemonControl�I�u�W�F�N�g
         * @return ���̓I�u�W�F�N�g
         */
        public Object provide(DaemonControl ctrl){
            return queue.get(1000);
        }
        
        /**
         * ����dequeued�œn���ꂽ�I�u�W�F�N�g��������QueueHandler���Ăяo���B<p>
         *
         * @param dequeued �L���[������o���ꂽ�I�u�W�F�N�g
         * @param ctrl DaemonControl�I�u�W�F�N�g
         */
        public void consume(Object dequeued, DaemonControl ctrl){
            if(dequeued == null){
                return;
            }
            try{
                category.write(dequeued);
            }catch(MessageWriteException e){
                if(writeErrorLogMessageId != null){
                    getLogger().write(writeErrorLogMessageId, e);
                }
            }
        }
        
        /**
         * �L���[�̒��g��f���o���B<p>
         */
        public void garbage(){
            if(queue != null){
                while(queue != null && queue.size() > 0){
                    consume(queue.get(0), null);
                }
            }
        }
    }
}
