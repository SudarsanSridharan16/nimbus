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
package jp.ossc.nimbus.service.scheduler2;

import jp.ossc.nimbus.core.*;
import jp.ossc.nimbus.service.queue.*;

/**
 * �f�t�H���g�X�P�W���[���B<p>
 * �X�P�W���[����{@link Queue}�ɓ������āA�X�P�W���[���f�B�X�p�b�`�X���b�h�ő҂��󂯁A�X�P�W���[�������s����B<br>
 *
 * @author M.Takata
 */
public class DefaultSchedulerService extends AbstractSchedulerService
 implements DefaultSchedulerServiceMBean{
    
    private static final long serialVersionUID = 1536820942062675121L;
    protected QueueHandlerContainerService queueHandlerContainer;
    protected ServiceName queueServiceName;
    protected Queue requestQueue;
    protected int scheduleDispatcherSize = 1;
    protected boolean isDaemonScheduleDispatcher = true;
    protected long stopWaitTimeout = -1;
    
    // DefaultSchedulerServiceMBean��JavaDoc
    public void setQueueServiceName(ServiceName name){
        queueServiceName = name;
    }
    // DefaultSchedulerServiceMBean��JavaDoc
    public ServiceName getQueueServiceName(){
        return queueServiceName;
    }
    
    // DefaultSchedulerServiceMBean��JavaDoc
    public void setScheduleDispatcherSize(int size){
        scheduleDispatcherSize = size;
    }
    
    // DefaultSchedulerServiceMBean��JavaDoc
    public int getScheduleDispatcherSize(){
        return scheduleDispatcherSize;
    }
    
    // DefaultSchedulerServiceMBean��JavaDoc
    public int getActiveScheduleDispatcherSize(){
        return queueHandlerContainer == null
            ? 0 : queueHandlerContainer.getActiveQueueHandlerSize();
    }
    
    // DefaultSchedulerServiceMBean��JavaDoc
    public void setDaemonScheduleDispatcher(boolean isDaemon){
        isDaemonScheduleDispatcher = isDaemon;
    }
    
    // DefaultSchedulerServiceMBean��JavaDoc
    public boolean isDaemonScheduleDispatcher(){
        return isDaemonScheduleDispatcher;
    }
    
    // DefaultSchedulerServiceMBean��JavaDoc
    public void setStopWaitTimeout(long timeout){
        stopWaitTimeout = timeout;
    }
    
    // DefaultSchedulerServiceMBean��JavaDoc
    public long getStopWaitTimeout(){
        return stopWaitTimeout;
    }
    
    /**
     * �T�[�r�X�̐����������s���B<p>
     *
     * @exception Exception �T�[�r�X�̐��������Ɏ��s�����ꍇ
     */
    public void createService() throws Exception{
        queueHandlerContainer = new QueueHandlerContainerService();
        queueHandlerContainer.create();
    }
    
    /**
     * �T�[�r�X�̊J�n�������s���B<p>
     *
     * @exception Exception �T�[�r�X�̊J�n�����Ɏ��s�����ꍇ
     */
    public void startService() throws Exception{
        
        queueHandlerContainer.setQueueHandlerSize(scheduleDispatcherSize);
        queueHandlerContainer.setDaemonQueueHandler(isDaemonScheduleDispatcher);
        if(queueServiceName != null){
            queueHandlerContainer.setQueueServiceName(queueServiceName);
        }else if(requestQueue != null){
            queueHandlerContainer.setQueueService(requestQueue);
        }else{
            DefaultQueueService queue = new DefaultQueueService();
            queue.create();
            queue.start();
            queueHandlerContainer.setQueueService(queue);
        }
        queueHandlerContainer.setQueueHandler(new ScheduleDispatcher());
        queueHandlerContainer.setIgnoreNullElement(true);
        queueHandlerContainer.setWaitTimeout(1000l);
        queueHandlerContainer.setStopWaitTimeout(stopWaitTimeout);
        queueHandlerContainer.start();
    }
    
    /**
     * �T�[�r�X�̒�~�������s���B<p>
     *
     * @exception Exception �T�[�r�X�̒�~�����Ɏ��s�����ꍇ
     */
    public void stopService() throws Exception{
        
        queueHandlerContainer.stop();
    }
    
    /**
     * �T�[�r�X�̔j���������s���B<p>
     *
     * @exception Exception �T�[�r�X�̔j�������Ɏ��s�����ꍇ
     */
    public void destroyService() throws Exception{
        
        queueHandlerContainer.destroy();
        queueHandlerContainer = null;
    }
    
    /**
     * �X�P�W���[���𓊓�����{@link Queue}�T�[�r�X��ݒ肷��B<p>
     *
     * @param queue Queue�T�[�r�X
     */
    public void setQueue(Queue queue){
        this.requestQueue = queue;
    }
    
    /**
     * �X�P�W���[���𓊓�����{{@link Queue}�T�[�r�X���擾����B<p>
     *
     * @return Queue�T�[�r�X
     */
    protected Queue getQueue(){
        return requestQueue;
    }
    
    /**
     * �g�����U�N�V�����Q���s�\�Ȃ̂�false��Ԃ��B<p>
     *
     * @return false
     */
    protected boolean isTransactableQueue(){
        return false;
    }
    
    /**
     * {@link Queue}�ɃX�P�W���[�����N�G�X�g�𓊓�����B<p>
     *
     * @param request �X�P�W���[�����N�G�X�g
     * @exception Throwable �����Ɏ��s�����ꍇ
     */
    protected void entrySchedule(ScheduleRequest request) throws Throwable{
        queueHandlerContainer.getQueueService().push(request);
    }
    
    /**
     * �X�P�W���[���f�B�X�p�b�`���B<p>
     * �X�P�W���[����{@link ScheduleExecutor}�Ɏ��s�˗�����B�܂��A{@link ScheduleManager}���g���āA�X�P�W���[���̏�Ԃ�ύX����B<br>
     * 
     * @author M.Takata
     */
    protected class ScheduleDispatcher implements QueueHandler{
        
        /**
         * {@link Queue}������o�����X�P�W���[����{@link ScheduleExecutor}�Ɏ��s�˗�����B<p>
         *
         * @param obj {@link Queue}������o�����X�P�W���[��
         * @exception Throwable
         */
        public void handleDequeuedObject(Object obj) throws Throwable{
            if(obj == null){
                return;
            }
            dispatchSchedule((ScheduleRequest)obj);
        }
        public boolean handleError(Object obj, Throwable th) throws Throwable{
            throw th;
        }
        public void handleRetryOver(Object obj, Throwable th) throws Throwable{
            throw th;
        }
    }
}