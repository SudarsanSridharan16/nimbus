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
import jp.ossc.nimbus.util.SynchronizeMonitor;
import jp.ossc.nimbus.util.WaitSynchronizeMonitor;

/**
 * QueueHandler�R���e�i�T�[�r�X�B<p>
 * 
 * @author M.Takata
 */
public class QueueHandlerContainerService extends ServiceBase
 implements QueueHandlerContainer, QueueHandlerContainerServiceMBean{
    
    private static final long serialVersionUID = -6527205946658554031L;
    
    protected ServiceName queueServiceName;
    protected Queue requestQueue;
    protected Daemon[] daemons;
    protected QueueReceiver[] invokers;
    protected int queueHandlerSize = 1;
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
    protected long count = 0;
    protected boolean isQueueHandlerNowaitOnStop;
    protected boolean isGarbageQueueOnStop = true;
    protected boolean isSuspend;
    protected SynchronizeMonitor suspendMonitor = new WaitSynchronizeMonitor();
    protected boolean isIgnoreNullElement;
    protected long stopWaitTimeout = -1;
    
    // QueueHandlerContainerServiceMBean��JavaDoc
    public void setQueueServiceName(ServiceName name){
        queueServiceName = name;
    }
    // QueueHandlerContainerServiceMBean��JavaDoc
    public ServiceName getQueueServiceName(){
        return queueServiceName;
    }
    
    // QueueHandlerContainerServiceMBean��JavaDoc
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
    // QueueHandlerContainerServiceMBean��JavaDoc
    public ServiceName getQueueHandlerServiceName(){
        return queueHandlerServiceName;
    }
    
    // QueueHandlerContainerServiceMBean��JavaDoc
    public void setQueueHandlerSize(int size){
        queueHandlerSize = size;
    }
    // QueueHandlerContainerServiceMBean��JavaDoc
    public int getQueueHandlerSize(){
        return queueHandlerSize;
    }
    
    // QueueHandlerContainerServiceMBean��JavaDoc
    public void setReleaseQueue(boolean isRelease){
        isReleaseQueue = isRelease;
    }
    // QueueHandlerContainerServiceMBean��JavaDoc
    public boolean isReleaseQueue(){
        return isReleaseQueue;
    }
    
    // QueueHandlerContainerServiceMBean��JavaDoc
    public void setWaitTimeout(long timeout){
        waitTimeout = timeout;
    }
    // QueueHandlerContainerServiceMBean��JavaDoc
    public long getWaitTimeout(){
        return waitTimeout;
    }
    
    // QueueHandlerContainerServiceMBean��JavaDoc
    public void setMaxRetryCount(int count){
        maxRetryCount = count;
    }
    // QueueHandlerContainerServiceMBean��JavaDoc
    public int getMaxRetryCount(){
        return maxRetryCount;
    }
    
    // QueueHandlerContainerServiceMBean��JavaDoc
    public void setRetryInterval(long interval){
        retryInterval = interval;
    }
    // QueueHandlerContainerServiceMBean��JavaDoc
    public long getRetryInterval(){
        return retryInterval;
    }
    
    // QueueHandlerContainerServiceMBean��JavaDoc
    public void setHandlingErrorMessageId(String id){
        handlingErrorMessageId = id;
    }
    // QueueHandlerContainerServiceMBean��JavaDoc
    public String getHandlingErrorMessageId(){
        return handlingErrorMessageId;
    }
    
    // QueueHandlerContainerServiceMBean��JavaDoc
    public void setRetryOverErrorMessageId(String id){
        retryOverErrorMessageId = id;
    }
    // QueueHandlerContainerServiceMBean��JavaDoc
    public String getRetryOverErrorMessageId(){
        return retryOverErrorMessageId;
    }
    
    // QueueHandlerContainerServiceMBean��JavaDoc
    public void setGarbageQueueOnStop(boolean isGarbage){
        isGarbageQueueOnStop = isGarbage;
    }
    // QueueHandlerContainerServiceMBean��JavaDoc
    public boolean isGarbageQueueOnStop(){
        return isGarbageQueueOnStop;
    }
    
    // QueueHandlerContainerServiceMBean��JavaDoc
    public void setIgnoreNullElement(boolean isIgnore){
        isIgnoreNullElement = isIgnore;
    }
    // QueueHandlerContainerServiceMBean��JavaDoc
    public boolean isIgnoreNullElement(){
        return isIgnoreNullElement;
    }
    
    // QueueHandlerContainer��JavaDoc
    public int getActiveQueueHandlerSize(){
        if(invokers == null){
            return 0;
        }
        int count = 0;
        for(int i = 0; i < invokers.length; i++){
            if(invokers[i].isActive){
                count++;
            }
        }
        return count;
    }
    
    // QueueHandlerContainer��JavaDoc
    public int getStandbyQueueHandlerSize(){
        if(invokers == null){
            return 0;
        }
        int count = 0;
        for(int i = 0; i < invokers.length; i++){
            if(!invokers[i].isActive){
                count++;
            }
        }
        return count;
    }
    
    // QueueHandlerContainerServiceMBean��JavaDoc
    public void setDaemonQueueHandler(boolean isDaemon){
        isDaemonQueueHandler = isDaemon;
    }
    
    // QueueHandlerContainerServiceMBean��JavaDoc
    public boolean isDaemonQueueHandler(){
        return isDaemonQueueHandler;
    }
    
    // QueueHandlerContainerServiceMBean��JavaDoc
    public void setQueueHandlerThreadPriority(int newPriority){
        queueHandlerThreadPriority = newPriority;
    }
    // QueueHandlerContainerServiceMBean��JavaDoc
    public int getQueueHandlerThreadPriority(){
        return queueHandlerThreadPriority;
    }
    
    // QueueHandlerContainerServiceMBean��JavaDoc
    public void setQueueHandlerNowaitOnStop(boolean isNowait){
        isQueueHandlerNowaitOnStop = isNowait;
    }
    
    // QueueHandlerContainerServiceMBean��JavaDoc
    public boolean isQueueHandlerNowaitOnStop(){
        return isQueueHandlerNowaitOnStop;
    }
    
    // QueueHandlerContainerServiceMBean��JavaDoc
    public void setStopWaitTimeout(long timeout){
        stopWaitTimeout = timeout;
    }
    
    // QueueHandlerContainerServiceMBean��JavaDoc
    public long getStopWaitTimeout(){
        return stopWaitTimeout;
    }
    
    // QueueHandlerContainerServiceMBean��JavaDoc
    public long getAverageHandleProcessTime(){
        if(invokers == null){
            return 0;
        }
        int time = 0;
        if(invokers.length != 0){
            for(int i = 0; i < invokers.length; i++){
                time += invokers[i].getAverageReceiveProcessTime();
            }
            time /= invokers.length;
        }
        return time;
    }
    
    /**
     * �T�[�r�X�̊J�n�������s���B<p>
     *
     * @exception Exception Queue�T�[�r�X�̎擾�Ɏ��s�����ꍇ
     */
    public void startService() throws Exception{
        if(getQueueServiceName() != null){
            setQueueService((Queue)ServiceManagerFactory
                .getServiceObject(queueServiceName)
            );
        }
        if(getQueueService() == null && queueHandlerSize > 0){
            DefaultQueueService queue = new DefaultQueueService();
            queue.create();
            queue.start();
            setQueueService(queue);
        }
        if(getQueueService() == null){
            if(getQueueHandler() == null){
                throw new IllegalArgumentException("QueueHandler is null.");
            }
        }else if(queueHandlerSize > 0){
            // �L���[��t�J�n
            getQueueService().accept();
            
            // �f�[�����N��
            if(queueHandlerSize < 0){
                throw new IllegalArgumentException("queueHandlerSize < 0.");
            }
            invokers = new QueueReceiver[queueHandlerSize];
            daemons = new Daemon[queueHandlerSize];
            for(int i = 0; i < queueHandlerSize; i++){
                invokers[i] = new QueueReceiver();
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
            final long startTime = System.currentTimeMillis();
            for(int i = 0; i < daemons.length; i++){
                if(isQueueHandlerNowaitOnStop){
                    daemons[i].stopNoWait();
                }else if(stopWaitTimeout < 0){
                    daemons[i].stop();
                }else{
                    long currentStopWaitTime = stopWaitTimeout - (System.currentTimeMillis() - startTime);
                    if(currentStopWaitTime > 0){
                        daemons[i].stop(currentStopWaitTime);
                    }else{
                        daemons[i].stopNoWait();
                    }
                }
                daemons[i] = null;
                invokers[i] = null;
            }
        }
        
        // �L���[��t��~
        if(getQueueService() != null && isReleaseQueue){
            getQueueService().release();
        }
        daemons = null;
        invokers = null;
        count = 0;
    }
    
    /**
     * �Ăяo����񓯊��ɂ��邽�߂�{@link Queue}�T�[�r�X��ݒ肷��B<p>
     *
     * @param queue Queue�T�[�r�X
     */
    public void setQueueService(Queue queue){
        this.requestQueue = queue;
    }
    
    /**
     * �Ăяo����񓯊��ɂ��邽�߂�{@link Queue}�T�[�r�X���擾����B<p>
     *
     * @return Queue�T�[�r�X
     */
    public Queue getQueueService(){
        return requestQueue;
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
    
    public void push(Object item){
        if(getQueueService() == null || queueHandlerSize == 0){
            count++;
            handleDequeuedObjectWithLock(getQueueHandler(), item, null);
        }else{
            getQueueService().push(item);
        }
    }
    
    public boolean push(Object item, long timeout){
        if(getQueueService() == null || queueHandlerSize == 0){
            count++;
            handleDequeuedObjectWithLock(getQueueHandler(), item, null);
            return true;
        }else{
            return getQueueService().push(item, timeout);
        }
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
    
    public Object peek(){
        if(getQueueService() == null){
            return null;
        }else{
            return getQueueService().peek();
        }
    }
    
    public Object peek(long timeOutMs){
        if(getQueueService() == null){
            return null;
        }else{
            return getQueueService().peek(timeOutMs);
        }
    }
    
    public Object remove(Object item){
        if(getQueueService() == null){
            return null;
        }else{
            return getQueueService().remove(item);
        }
    }
    
    public void clear(){
        if(getQueueService() == null){
            return;
        }else{
            getQueueService().clear();
        }
    }
    
    public int size(){
        if(getQueueService() == null){
            return 0;
        }else{
            return getQueueService().size();
        }
    }
    
    public long getCount(){
        if(getQueueService() == null){
            return count;
        }else{
            return getQueueService().getCount();
        }
    }
    
    public int getWaitCount(){
        if(getQueueService() == null){
            return 0;
        }else{
            return getQueueService().getWaitCount();
        }
    }
    
    public long getDepth(){
        if(getQueueService() == null){
            return 0;
        }else{
            return getQueueService().size();
        }
    }
    
    public void accept(){
        if(getQueueService() != null){
            getQueueService().accept();
        }
    }
    
    public void release(){
        if(getQueueService() != null){
            getQueueService().release();
        }
        count = 0;
    }
    
    public synchronized void resume(){
        if(!isSuspend){
            return;
        }
        isSuspend = false;
        if(getQueueService() == null){
            suspendMonitor.notifyAllMonitor();
            suspendMonitor.releaseAllMonitor();
        }else{
            if(daemons != null){
                for(int i = 0; i < daemons.length; i++){
                    daemons[i].resume();
                }
            }
        }
    }
    
    public synchronized void suspend(){
        if(isSuspend){
            return;
        }
        if(getQueueService() != null){
            if(daemons != null){
                for(int i = 0; i < daemons.length; i++){
                    daemons[i].suspend();
                }
            }
        }
        isSuspend = true;
    }
    
    public boolean isSuspend(){
        return isSuspend;
    }
    
    protected void handleDequeuedObjectWithLock(QueueHandler handler, Object dequeued, QueueReceiver receiver){
        if(isSuspend){
            try{
                suspendMonitor.initAndWaitMonitor();
            }catch(InterruptedException e){
            }
        }
        handleDequeuedObject(handler, dequeued, receiver, null);
    }
    
    protected void handleDequeuedObject(QueueHandler handler, Object dequeued, QueueReceiver receiver, DaemonControl ctrl){
        if(handler == null){
            return;
        }
        boolean isRetry = false;
        int retryCount = 0;
        do{
            try{
                if(receiver != null){
                    receiver.isActive = true;
                }
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
                                isRetry = false;
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
                            handler.handleRetryOver(dequeued, th);
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
                if(receiver != null){
                    receiver.isActive = false;
                }
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
    }
    
    protected class QueueReceiver implements DaemonRunnable{
        
        protected QueueHandler handler;
        
        /**
         * ���s�����ǂ����������t���O�B<p>
         */
        public boolean isActive;
        
        protected long receiveCount;
        protected long receiveProcessTime;
        
        public long getReceiveCount(){
            return receiveCount;
        }
        
        public long getAverageReceiveProcessTime(){
            return receiveCount == 0 ? 0 : (receiveProcessTime / receiveCount);
        }
        
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
            return getQueueService().get(waitTimeout);
        }
        
        /**
         * ����dequeued�œn���ꂽ�I�u�W�F�N�g��������QueueHandler���Ăяo���B<p>
         *
         * @param dequeued �L���[������o���ꂽ�I�u�W�F�N�g
         * @param ctrl DaemonControl�I�u�W�F�N�g
         */
        public void consume(Object dequeued, DaemonControl ctrl){
            if(dequeued == null && isIgnoreNullElement){
                return;
            }
            receiveCount++;
            long start = System.currentTimeMillis();
            try{
                handleDequeuedObject(handler, dequeued, this, ctrl);
            }finally{
                receiveProcessTime += (System.currentTimeMillis() - start);
            }
        }
        
        /**
         * �L���[�̒��g��f���o���B<p>
         */
        public void garbage(){
            if(getQueueService() != null && isGarbageQueueOnStop){
                while(getQueueService().size() > 0){
                    consume(getQueueService().get(0), null);
                }
            }
        }
    }
}
