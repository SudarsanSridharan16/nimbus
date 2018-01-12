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
package jp.ossc.nimbus.service.queue;

import jp.ossc.nimbus.core.*;
import jp.ossc.nimbus.daemon.*;

/**
 * ���UQueueHandler�R���e�i�T�[�r�X�B<p>
 *
 * @author M.Takata
 */
public class DistributedQueueHandlerContainerService extends ServiceBase
 implements QueueHandlerContainer, DistributedQueueHandlerContainerServiceMBean{

    private static final long serialVersionUID = 4594481433048573418L;

    protected ServiceName distributedQueueSelectorServiceName;
    protected DistributedQueueSelector distributedQueueSelector;

    protected Daemon[] daemons;
    protected QueueReceiver[] invokers;
    protected ServiceName queueHandlerServiceName;
    protected QueueHandler queueHandler;
    protected boolean isDaemonQueueHandler = true;

    protected long waitTimeout = -1;
    protected int maxRetryCount = 0;
    protected long retryInterval = 1000;
    protected String handlingErrorMessageId = DEFAULT_HANDLING_ERROR_MESSAGE_ID;
    protected String retryOverErrorMessageId = DEFAULT_RETRY_OVER_ERROR_MESSAGE_ID;
    protected int queueHandlerThreadPriority = -1;
    protected boolean isReleaseQueue = true;
    protected boolean isSuspend;
    protected boolean isIgnoreNullElement;

    // DistributedQueueHandlerContainerService��JavaDoc
    public void setDistributedQueueSelectorServiceName(ServiceName name){
        distributedQueueSelectorServiceName = name;
    }
    // DistributedQueueHandlerContainerService��JavaDoc
    public ServiceName getDistributedQueueSelectorServiceName(){
        return distributedQueueSelectorServiceName;
    }

    // DistributedQueueHandlerContainerService��JavaDoc
    public void setQueueHandlerServiceName(ServiceName name){
        if(queueHandlerServiceName == null){
            queueHandlerServiceName = name;
            if(daemons != null){
                for(int i = 0; i < daemons.length; i++){
                    daemons[i].resume();
                }
            }
        }else{
            queueHandlerServiceName = name;
        }
    }
    // DistributedQueueHandlerContainerService��JavaDoc
    public ServiceName getQueueHandlerServiceName(){
        return queueHandlerServiceName;
    }

    // DistributedQueueHandlerContainerService��JavaDoc
    public void setReleaseQueue(boolean isRelease){
        isReleaseQueue = isRelease;
    }
    // DistributedQueueHandlerContainerService��JavaDoc
    public boolean isReleaseQueue(){
        return isReleaseQueue;
    }

    // DistributedQueueHandlerContainerService��JavaDoc
    public void setWaitTimeout(long timeout){
        waitTimeout = timeout;
    }
    // DistributedQueueHandlerContainerService��JavaDoc
    public long getWaitTimeout(){
        return waitTimeout;
    }

    // DistributedQueueHandlerContainerService��JavaDoc
    public void setMaxRetryCount(int count){
        maxRetryCount = count;
    }
    // DistributedQueueHandlerContainerService��JavaDoc
    public int getMaxRetryCount(){
        return maxRetryCount;
    }

    // DistributedQueueHandlerContainerService��JavaDoc
    public void setRetryInterval(long interval){
        retryInterval = interval;
    }
    // DistributedQueueHandlerContainerService��JavaDoc
    public long getRetryInterval(){
        return retryInterval;
    }

    // DistributedQueueHandlerContainerService��JavaDoc
    public void setHandlingErrorMessageId(String id){
        handlingErrorMessageId = id;
    }
    // DistributedQueueHandlerContainerService��JavaDoc
    public String getHandlingErrorMessageId(){
        return handlingErrorMessageId;
    }

    // DistributedQueueHandlerContainerService��JavaDoc
    public void setRetryOverErrorMessageId(String id){
        retryOverErrorMessageId = id;
    }
    // DistributedQueueHandlerContainerService��JavaDoc
    public String getRetryOverErrorMessageId(){
        return retryOverErrorMessageId;
    }
    
    // DistributedQueueHandlerContainerService��JavaDoc
    public void setIgnoreNullElement(boolean isIgnore){
        isIgnoreNullElement = isIgnore;
    }
    // DistributedQueueHandlerContainerService��JavaDoc
    public boolean isIgnoreNullElement(){
        return isIgnoreNullElement;
    }

    // DistributedQueueHandlerContainerService��JavaDoc
    public int getQueueHandlerSize(){
        return invokers == null ? 0 : invokers.length;
    }

    // DistributedQueueHandlerContainerService��JavaDoc
    public int getActiveQueueHandlerSize(){
        int count = 0;
        if(invokers == null){
            if(distributedQueueSelector != null){
                final Queue[] queues = distributedQueueSelector.getQueues();
                if(queues[0] instanceof QueueHandlerContainer){
                    for(int i = 0; i < queues.length; i++){
                        count += ((QueueHandlerContainer)queues[i]).getActiveQueueHandlerSize();
                    }
                }
            }
        }else{
            for(int i = 0; i < invokers.length; i++){
                if(invokers[i].isActive){
                    count++;
                }
            }
        }
        return count;
    }

    // DistributedQueueHandlerContainerService��JavaDoc
    public int getStandbyQueueHandlerSize(){
        int count = 0;
        if(invokers == null){
            if(distributedQueueSelector != null){
                final Queue[] queues = distributedQueueSelector.getQueues();
                if(queues[0] instanceof QueueHandlerContainer){
                    for(int i = 0; i < queues.length; i++){
                        count += ((QueueHandlerContainer)queues[i]).getStandbyQueueHandlerSize();
                    }
                }
            }
        }else{
            for(int i = 0; i < invokers.length; i++){
                if(!invokers[i].isActive){
                    count++;
                }
            }
        }
        return count;
    }

    // DistributedQueueHandlerContainerService��JavaDoc
    public void setDaemonQueueHandler(boolean isDaemon){
        isDaemonQueueHandler = isDaemon;
    }

    // DistributedQueueHandlerContainerService��JavaDoc
    public boolean isDaemonQueueHandler(){
        return isDaemonQueueHandler;
    }

    // DistributedQueueHandlerContainerService��JavaDoc
    public void setQueueHandlerThreadPriority(int newPriority){
        queueHandlerThreadPriority = newPriority;
    }
    // DistributedQueueHandlerContainerService��JavaDoc
    public int getQueueHandlerThreadPriority(){
        return queueHandlerThreadPriority;
    }

    // DistributedQueueHandlerContainerService��JavaDoc
    public long getAverageHandleProcessTime(){
        long time = 0;
        if(invokers == null){
            if(distributedQueueSelector != null){
                final Queue[] queues = distributedQueueSelector.getQueues();
                if(queues[0] instanceof QueueHandlerContainer){
                    for(int i = 0; i < queues.length; i++){
                        time += ((QueueHandlerContainer)queues[i]).getAverageHandleProcessTime();
                    }
                    time /= queues.length;
                }
            }
        }else{
            if(invokers.length != 0){
                for(int i = 0; i < invokers.length; i++){
                    time += invokers[i].getAverageReceiveProcessTime();
                }
                time /= invokers.length;
            }
        }
        return time;
    }

    public void setDistributedQueueSelector(DistributedQueueSelector selector){
        distributedQueueSelector = selector;
    }

    /**
     * �T�[�r�X�̊J�n�������s���B<p>
     *
     * @exception Exception �T�[�r�X�̊J�n�����Ɏ��s�����ꍇ
     */
    public void startService() throws Exception{
        if(distributedQueueSelectorServiceName != null){
            distributedQueueSelector = (DistributedQueueSelector)ServiceManagerFactory.getServiceObject(distributedQueueSelectorServiceName);
        }

        if(distributedQueueSelector == null){
            throw new IllegalArgumentException("DistributedQueueSelector is null.");
        }
        // �L���[��t�J�n
        accept();

        final Queue[] queues = distributedQueueSelector.getQueues();
        if(!(queues[0] instanceof QueueHandlerContainer)){
            invokers = new QueueReceiver[queues.length];
            daemons = new Daemon[invokers.length];
            for(int i = 0; i < invokers.length; i++){
                invokers[i] = new QueueReceiver(queues[i]);
                invokers[i].handler = getQueueHandler();

                daemons[i] = new Daemon(invokers[i]);
                daemons[i].setDaemon(isDaemonQueueHandler);
                daemons[i].setName(getServiceNameObject() + " QueueReceiver" + (i + 1));
                if(queueHandlerThreadPriority > 0){
                    daemons[i].setPriority(queueHandlerThreadPriority);
                }
                if(invokers[i].handler == null){
                    daemons[i].suspend();
                }
                daemons[i].start();
            }
        }
    }

    /**
     * �T�[�r�X�̒�~�������s���B<p>
     *
     * @exception Exception �T�[�r�X�̒�~�����Ɏ��s�����ꍇ
     */
    public void stopService() throws Exception{

        if(daemons != null){
            // �f�[������~
            for(int i = 0; i < daemons.length; i++){
                daemons[i].stop();
                daemons[i] = null;
                invokers[i] = null;
            }
        }

        // �L���[��t��~
        if(isReleaseQueue){
            release();
        }
        distributedQueueSelector = null;
        daemons = null;
        invokers = null;
    }

    public synchronized void resume(){
        if(!isSuspend){
            return;
        }
        isSuspend = false;
        if(daemons != null){
            for(int i = 0; i < daemons.length; i++){
                daemons[i].resume();
            }
        }
    }

    public synchronized void suspend(){
        if(isSuspend){
            return;
        }
        if(daemons != null){
            for(int i = 0; i < daemons.length; i++){
                daemons[i].suspend();
            }
        }
        isSuspend = true;
    }

    public boolean isSuspend(){
        return isSuspend;
    }

    /**
     * QueueHandler��ݒ肷��B<p>
     *
     * @param handler QueueHandler
     */
    public void setQueueHandler(QueueHandler handler){
        if(queueHandler == null){
            queueHandler = handler;
            if(daemons != null){
                for(int i = 0; i < daemons.length; i++){
                    daemons[i].resume();
                }
            }else if(distributedQueueSelector != null){
                final Queue[] queues = distributedQueueSelector.getQueues();
                if(queues[0] instanceof QueueHandlerContainer){
                    for(int i = 0; i < queues.length; i++){
                        ((QueueHandlerContainer)queues[i]).setQueueHandler(handler);
                    }
                }
            }
        }else{
            queueHandler = handler;
        }
    }

    /**
     * QueueHandler���擾����B<p>
     *
     * @return QueueHandler
     */
    public QueueHandler getQueueHandler(){
        if(queueHandler != null){
            return queueHandler;
        }
        if(queueHandlerServiceName != null){
            return (QueueHandler)ServiceManagerFactory
                .getServiceObject(queueHandlerServiceName);
        }
        return null;
    }

    protected class QueueReceiver implements DaemonRunnable{

        protected Queue queue;

        protected QueueHandler handler;

        protected long receiveCount;
        protected long receiveProcessTime;

        public QueueReceiver(Queue queue){
            this.queue = queue;
        }

        public long getReceiveCount(){
            return receiveCount;
        }

        public long getAverageReceiveProcessTime(){
            return receiveCount == 0 ? 0 : (receiveProcessTime / receiveCount);
        }

        /**
         * ���s�����ǂ����������t���O�B<p>
         */
        public boolean isActive;

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
            if(handler == null){
                handler = getQueueHandler();
                if(handler == null){
                    return null;
                }
            }
            return queue.get(waitTimeout);
        }

        /**
         * ����dequeued�œn���ꂽ�I�u�W�F�N�g��������QueueHandler���Ăяo���B<p>
         *
         * @param dequeued �L���[������o���ꂽ�I�u�W�F�N�g
         * @param ctrl DaemonControl�I�u�W�F�N�g
         */
        public void consume(Object dequeued, DaemonControl ctrl){
            if(handler == null){
                return;
            }
            if(dequeued == null && isIgnoreNullElement){
                return;
            }
            boolean isRetry = false;
            int retryCount = 0;
            receiveCount++;
            long start = System.currentTimeMillis();
            do{
                try{
                    isActive = true;
                    try{
                        handler.handleDequeuedObject(dequeued);
                        isRetry = false;
                    }catch(Throwable th){
                        if(maxRetryCount > 0){
                            if(retryCount >= maxRetryCount){
                                isRetry = false;
                                try{
                                    handler.handleRetryOver(dequeued, th);
                                }catch(Throwable th2){
                                    getLogger().write(
                                        retryOverErrorMessageId,
                                        dequeued,
                                        th
                                    );
                                }
                            }else{
                                isRetry = true;
                                try{
                                    isRetry = handler.handleError(dequeued, th);
                                }catch(Throwable th2){
                                    getLogger().write(
                                        handlingErrorMessageId,
                                        dequeued,
                                        th
                                    );
                                }
                            }
                        }else{
                            isRetry = false;
                            try{
                                handler.handleError(dequeued, th);
                            }catch(Throwable th2){
                                getLogger().write(
                                    retryOverErrorMessageId,
                                    dequeued,
                                    th
                                );
                            }
                        }
                    }
                }finally{
                    isActive = false;
                    if(ctrl != null && ctrl.isRunning()){
                        Thread.interrupted();
                    }
                }
                if(isRetry && retryInterval > 0){
                    try{
                        Thread.sleep(retryInterval);
                    }catch(InterruptedException e){
                        isRetry = false;
                    }
                }
                retryCount++;
            }while(isRetry);
            receiveProcessTime += (System.currentTimeMillis() - start);
        }

        /**
         * �L���[�̒��g��f���o���B<p>
         */
        public void garbage(){
            if(queue != null){
                while(queue.size() > 0){
                    consume(queue.get(0), null);
                }
            }
        }
    }


    /**
     * �K�؂ȕ��U�L���[�̂P�Ƀf�[�^�𓊓�����B<p>
     *
     * @param item �����I�u�W�F�N�g
     */
    public synchronized void push(Object item){
        final Queue queue = distributedQueueSelector.selectQueue(item);
        queue.push(item);
    }

    /**
     * �K�؂ȕ��U�L���[�̂P�Ƀf�[�^�𓊓�����B<p>
     *
     * @param item �����I�u�W�F�N�g
     * @param timeout �^�C���A�E�g[ms]
     * @return �^�C���A�E�g�����ꍇfalse
     */
    public synchronized boolean push(Object item, long timeout){
        final Queue queue = distributedQueueSelector.selectQueue(item);
        return queue.push(item, timeout);
    }

    /**
     * �L���[����f�[�^�����o���B<p>
     * �T�|�[�g���܂���B<br>
     *
     * @return �L���[�擾�I�u�W�F�N�g
     */
    public Object get(){
        throw new UnsupportedOperationException();
    }

    /**
     * �L���[����f�[�^�����o���B<p>
     * �T�|�[�g���܂���B<br>
     *
     * @param timeOutMs �^�C���A�E�g[ms]
     * @return �L���[�擾�I�u�W�F�N�g
     */
    public Object get(long timeOutMs){
        throw new UnsupportedOperationException();
    }

    /**
     * �L���[����f�[�^��ǂށB<p>
     * �T�|�[�g���܂���B<br>
     *
     * @return �L���[�擾�I�u�W�F�N�g
     */
    public Object peek(){
        throw new UnsupportedOperationException();
    }

    /**
     * �L���[����f�[�^��ǂށB<br>
     * �T�|�[�g���܂���B<br>
     *
     * @param timeOutMs �^�C���A�E�g[ms]
     * @return �L���[�擾�I�u�W�F�N�g
     */
    public Object peek(long timeOutMs){
        throw new UnsupportedOperationException();
    }

    /**
     * �L���[����w�肵���f�[�^���폜����B<p>
     * �T�|�[�g���܂���B<br>
     *
     * @param item �폜�Ώۂ̃I�u�W�F�N�g
     */
    public Object remove(Object item){
        throw new UnsupportedOperationException();
    }

    /**
     * �S�Ă̕��U�L���[������������B<p>
     */
    public void clear(){
        final Queue[] queues = distributedQueueSelector.getQueues();
        if(queues != null){
            for(int i = 0; i < queues.length; i++){
                queues[i].clear();
            }
        }
    }

    /**
     * �S�Ă̕��U�L���[�̍��v�T�C�Y���擾����B<p>
     *
     * @return �L���[�i�[����
     */
    public int size(){
        int size = 0;
        final Queue[] queues = distributedQueueSelector.getQueues();
        if(queues != null){
            for(int i = 0; i < queues.length; i++){
                size += queues[i].size();
            }
        }
        return size;
    }

    /**
     * �S�Ă̕��U�L���[�ɓ������ꂽ�������擾����B<p>
     *
     * @return �L���[��������
     */
    public long getCount(){
        long count = 0;
        final Queue[] queues = distributedQueueSelector.getQueues();
        if(queues != null){
            for(int i = 0; i < queues.length; i++){
                count += queues[i].getCount();
            }
        }
        return count;
    }
    
    public int getWaitCount(){
        int count = 0;
        final Queue[] queues = distributedQueueSelector.getQueues();
        if(queues != null){
            for(int i = 0; i < queues.length; i++){
                count += queues[i].getWaitCount();
            }
        }
        return count;
    }

    public long getDepth(){
        long depth = 0;
        final Queue[] queues = distributedQueueSelector.getQueues();
        if(queues != null){
            for(int i = 0; i < queues.length; i++){
                depth += queues[i].size();
            }
        }
        return depth;
    }

    /**
     * �S�Ă̕��U�L���[�̃L���[�擾�҂����J�n����B<p>
     * {@link #release()}�ďo����ɁA�L���[�擾�҂����󂯕t����悤�ɂ���B
     */
    public void accept(){
        final Queue[] queues = distributedQueueSelector.getQueues();
        if(queues != null){
            for(int i = 0; i < queues.length; i++){
                queues[i].accept();
            }
        }
    }

    /**
     * �S�Ă̕��U�L���[�̃L���[�擾�҂����J�����A�L���[�擾�҂����󂯕t���Ȃ��悤�ɂ���B<p>
     */
    public void release(){
        final Queue[] queues = distributedQueueSelector.getQueues();
        if(queues != null){
            for(int i = 0; i < queues.length; i++){
                queues[i].release();
            }
        }
    }
}
