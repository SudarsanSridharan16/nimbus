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
package jp.ossc.nimbus.service.aop.interceptor;

import jp.ossc.nimbus.core.*;
import jp.ossc.nimbus.service.aop.*;
import jp.ossc.nimbus.daemon.*;
import jp.ossc.nimbus.service.queue.*;

/**
 * ���\�b�h�񓯊��Ăяo���C���^�[�Z�v�^�B<p>
 * ���\�b�h�̌Ăяo���ɑ΂��鏈����񓯊��ɂ���C���^�[�Z�v�^�ł���B<br>
 * ���̃C���^�[�Z�v�^�̔񓯊��Ăяo���ɂ́A3��ނ̔񓯊��Ăяo��������B<br>
 * �P�߂́A�߂�l��K�v�Ƃ��Ȃ��񓯊��Ăяo���B���̏ꍇ�́A�߂�l�͕K��null��Ԃ��B<br>
 * �ȉ��ɁA���̏ꍇ�̔񓯊��Ăяo���C���^�[�Z�v�^�̃T�[�r�X��`��������B<br>
 * <pre>
 * &lt;?xml version="1.0" encoding="Shift_JIS"?&gt;
 * 
 * &lt;server&gt;
 *     
 *     &lt;manager name="Sample"&gt;
 *         
 *         &lt;service name="MethodAsynchronousInterceptor"
 *                  code="jp.ossc.nimbus.service.aop.interceptor.MethodAsynchronousInterceptorService/"&gt;
 *         
 *     &lt;/manager&gt;
 *     
 * &lt;/server&gt;
 * </pre>
 * �Q�߂́A�C�ӂ̎��Ԃ����񓯊��Ăяo���̉�����҂񓯊��Ăяo���B���ԓ��ɉ������Ԃ��Ă���Ζ߂�l�܂��͗�O��Ԃ��A���ԓ��ɉ������Ԃ��Ă��Ȃ����null��Ԃ��B�A���A{@link #setFailToWaitResponseTimeout(boolean) setFailToWaitResponseTimeout(true)}�ɐݒ肷��ƁA{@link AsynchronousTimeoutException}��throw����B<br>
 * �ȉ��ɁA���̏ꍇ�̔񓯊��Ăяo���C���^�[�Z�v�^�̃T�[�r�X��`��������B<br>
 * <pre>
 * &lt;?xml version="1.0" encoding="Shift_JIS"?&gt;
 * 
 * &lt;server&gt;
 *     
 *     &lt;manager name="Sample"&gt;
 *         
 *         &lt;service name="MethodAsynchronousInterceptor"
 *                  code="jp.ossc.nimbus.service.aop.interceptor.MethodAsynchronousInterceptorService"&gt;
 *             &lt;attribute name="ResponseTimeout"&gt;1000&lt;/attribute&gt;
 *         &lt;/service&gt;
 *         
 *     &lt;/manager&gt;
 *     
 * &lt;/server&gt;
 * </pre>
 * �R�߂́A���\�b�h�̖߂�l��throw���ꂽ��O��C�ӂ̃^�C�~���O�Ŏ擾����񓯊��Ăяo���B���̏ꍇ�́A���X�|���X���i�[����{@link Queue}�T�[�r�X�����̃T�[�r�X�̑����ɐݒ肵�A����Queue�T�[�r�X����߂�l��throw���ꂽ��O���i�[����{@link AsynchronousResponse}���擾�ł���B<br>
 * �ȉ��ɁA���̏ꍇ�̔񓯊��Ăяo���C���^�[�Z�v�^�̃T�[�r�X��`��������B<br>
 * <pre>
 * &lt;?xml version="1.0" encoding="Shift_JIS"?&gt;
 * 
 * &lt;server&gt;
 *     
 *     &lt;manager name="Sample"&gt;
 *         
 *         &lt;service name="MethodAsynchronousInterceptor"
 *                  code="jp.ossc.nimbus.service.aop.interceptor.MethodAsynchronousInterceptorService"&gt;
 *             &lt;attribute name="ResponseQueueServiceName"&gt;#ResponseQueue&lt;/attribute&gt;
 *             &lt;depends&gt;ResponseQueue&lt;/depends&gt;
 *         &lt;/service&gt;
 *         
 *         &lt;service name="ResponseQueue"
 *                  code="jp.ossc.nimbus.service.queue.DefaultQueueService"/&gt;
 *         
 *     &lt;/manager&gt;
 *     
 * &lt;/server&gt;
 * </pre>
 *
 * @author M.Takata
 * @see Queue
 */
public class MethodAsynchronousInterceptorService extends ServiceBase
 implements Interceptor, MethodAsynchronousInterceptorServiceMBean{
    
    private static final long serialVersionUID = 556687756097723606L;
    
    private ServiceName requestQueueServiceName;
    private DefaultQueueService defaultRequestQueue;
    private Queue requestQueue;
    private ServiceName responseQueueServiceName;
    private Queue responseQueue;
    private Daemon[] daemons;
    private Invoker[] invokers;
    private long responseTimeout = -1;
    private boolean isFailToWaitResponseTimeout = true;
    private boolean isReturnResponse = true;
    private int invokerThreadSize = 1;
    private boolean isInvokerThreadDaemon = true;
    
    // MethodAsynchronousInterceptorServiceMBean��JavaDoc
    public void setRequestQueueServiceName(ServiceName name){
        requestQueueServiceName = name;
    }
    
    // MethodAsynchronousInterceptorServiceMBean��JavaDoc
    public ServiceName getRequestQueueServiceName(){
        return requestQueueServiceName;
    }
    
    // MethodAsynchronousInterceptorServiceMBean��JavaDoc
    public void setResponseQueueServiceName(ServiceName name){
        responseQueueServiceName = name;
    }
    
    // MethodAsynchronousInterceptorServiceMBean��JavaDoc
    public ServiceName getResponseQueueServiceName(){
        return responseQueueServiceName;
    }
    
    // MethodAsynchronousInterceptorServiceMBean��JavaDoc
    public void setResponseTimeout(long timeout){
        responseTimeout = timeout;
    }
    
    // MethodAsynchronousInterceptorServiceMBean��JavaDoc
    public long getResponseTimeout(){
        return responseTimeout;
    }
    
    // MethodAsynchronousInterceptorServiceMBean��JavaDoc
    public void setFailToWaitResponseTimeout(boolean isThrow){
        isFailToWaitResponseTimeout = isThrow;
    }
    
    // MethodAsynchronousInterceptorServiceMBean��JavaDoc
    public boolean isFailToWaitResponseTimeout(){
        return isFailToWaitResponseTimeout;
    }
    
    // MethodAsynchronousInterceptorServiceMBean��JavaDoc
    public void setInvokerThreadSize(int size){
        invokerThreadSize = size;
    }
    
    // MethodAsynchronousInterceptorServiceMBean��JavaDoc
    public int getInvokerThreadSize(){
        return invokerThreadSize;
    }
    
    // MethodAsynchronousInterceptorServiceMBean��JavaDoc
    public void setInvokerThreadDaemon(boolean isDaemon){
        isInvokerThreadDaemon = isDaemon;
    }
    
    // MethodAsynchronousInterceptorServiceMBean��JavaDoc
    public boolean isInvokerThreadDaemon(){
        return isInvokerThreadDaemon;
    }
    
    // MethodAsynchronousInterceptorServiceMBean��JavaDoc
    public int getActiveInvokerThreadSize(){
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
    
    // MethodAsynchronousInterceptorServiceMBean��JavaDoc
    public void setReturnResponse(boolean isReturn){
        isReturnResponse = isReturn;
    }
    
    // MethodAsynchronousInterceptorServiceMBean��JavaDoc
    public boolean isReturnResponse(){
        return isReturnResponse;
    }
    
    /**
     * �T�[�r�X�̊J�n�������s���B<p>
     *
     * @exception Exception Queue�T�[�r�X�̎擾�Ɏ��s�����ꍇ
     */
    public void startService() throws Exception{
        if(getRequestQueueServiceName() == null){
            if(getRequestQueueService() == null) {
                if(getDefaultRequestQueueService() == null){
                    final DefaultQueueService defaultQueue
                         = new DefaultQueueService();
                    defaultQueue.create();
                    defaultQueue.start();
                    setDefaultRequestQueueService(defaultQueue);
                }else{
                    getDefaultRequestQueueService().start();
                }
                setRequestQueueService(getDefaultRequestQueueService());
            }
        }else{
            setRequestQueueService((Queue)ServiceManagerFactory
                .getServiceObject(requestQueueServiceName)
            );
        }
        
        // �L���[��t�J�n
        getRequestQueueService().accept();
        
        // �f�[�����N��
        if(invokerThreadSize < 0){
            throw new IllegalArgumentException("invokerThreadSize < 0.");
        }
        invokers = new Invoker[invokerThreadSize];
        daemons = new Daemon[invokerThreadSize];
        for(int i = 0; i < invokerThreadSize; i++){
            invokers[i] = new Invoker();
            daemons[i] = new Daemon(invokers[i]);
            daemons[i].setName("Nimbus AsynchInvokerDaemon " + getServiceNameObject());
            daemons[i].setDaemon(isInvokerThreadDaemon);
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
            invokers[i] = null;
        }
        
        // �L���[��t��~
        getRequestQueueService().release();
        
        daemons = null;
        invokers = null;
        
        if(getRequestQueueService() == getDefaultRequestQueueService()){
            getDefaultRequestQueueService().stop();
        }
    }
    
    /**
     * �T�[�r�X�̔j���������s���B<p>
     *
     * @exception Exception �T�[�r�X�̔j�������Ɏ��s�����ꍇ
     */
    public void destroyService(){
        
        if(getRequestQueueService() == getDefaultRequestQueueService()
            && getDefaultRequestQueueService() != null){
            getDefaultRequestQueueService().destroy();
            setDefaultRequestQueueService(null);
        }
    }
    
    /**
     * �񓯊��Ăяo�������āAnull��Ԃ��B<p>
     * �{���̃��\�b�h�Ăяo���̖߂�́A{@link #setResponseQueueServiceName(ServiceName)}��{@link Queue}�T�[�r�X���ݒ肳��Ă���΁A����Queue�ɁA{@link AsynchronousResponse}�Ƃ��ăL���[����Ă���̂ŁA��������擾�ł���B<br>
     * �T�[�r�X���J�n����Ă��Ȃ��ꍇ�́A�񓯊��Ăяo�����s�킸�Ɏ��̃C���^�[�Z�v�^���Ăяo���B<br>
     * 
     *
     * @param context �Ăяo���̃R���e�L�X�g���
     * @param chain ���̃C���^�[�Z�v�^���Ăяo�����߂̃`�F�[��
     * @return null��Ԃ�
     * @exception Throwable �Ăяo����ŗ�O�����������ꍇ�A�܂��͂��̃C���^�[�Z�v�^�ŔC�ӂ̗�O�����������ꍇ�B�A���A�{���Ăяo����鏈����throw���Ȃ�RuntimeException�ȊO�̗�O��throw���Ă��A�Ăяo�����ɂ͓`�d����Ȃ��B
     */
    public Object invoke(
        InvocationContext context,
        InterceptorChain chain
    ) throws Throwable{
        if(getState() == STARTED){
            InterceptorChain ch = chain;
            if(chain instanceof DefaultThreadLocalInterceptorChain){
                DefaultInterceptorChain tmp = new DefaultInterceptorChain(
                    chain.getInterceptorChainList(),
                    chain.getInvoker()
                );
                tmp.setCurrentInterceptorIndex(
                    chain.getCurrentInterceptorIndex()
                );
                ch = tmp;
            }
            final int currentInterceptorIndex = ch.getCurrentInterceptorIndex();
            final InterceptorChain cloneCh = ch.cloneChain();
            cloneCh.setCurrentInterceptorIndex(currentInterceptorIndex);
            final Queue resQueue = getResponseQueue();
            final InvocationInfo invokeInfo = new InvocationInfo(
                (MethodInvocationContext)context,
                cloneCh,
                resQueue
            );
            getRequestQueueService().push(invokeInfo);
            
            if(resQueue != null && isReturnResponse){
                AsynchronousResponse response = null;
                if(responseTimeout > 0){
                    response = (AsynchronousResponse)resQueue.get(responseTimeout);
                }else{
                    response = (AsynchronousResponse)resQueue.get();
                }
                
                if(response == null){
                    invokeInfo.isTimeout = true;
                    if(isFailToWaitResponseTimeout){
                        throw new AsynchronousTimeoutException();
                    }
                }else{
                    if(response.isThrownException()){
                        throw response.getThrownException();
                    }else{
                        return response.getReturnObject();
                    }
                }
            }
            return null;
        }else{
            return chain.invokeNext(context);
        }
    }
    
    /**
     * �Ăяo����񓯊��ɂ��邽�߂�{@link Queue}�T�[�r�X��ݒ肷��B<p>
     *
     * @param queue Queue�T�[�r�X
     */
    public void setRequestQueueService(Queue queue){
        this.requestQueue = queue;
    }
    
    /**
     * �Ăяo����񓯊��ɂ��邽�߂�{@link Queue}�T�[�r�X���擾����B<p>
     *
     * @return Queue�T�[�r�X
     */
    protected Queue getRequestQueueService(){
        return requestQueue;
    }
    
    /**
     * �Ăяo����񓯊��ɂ��邽�߂̃f�t�H���g��{@link Queue}�T�[�r�X���擾����B<p>
     *
     * @return �f�t�H���g��Queue�T�[�r�X
     */
    protected DefaultQueueService getDefaultRequestQueueService(){
        return defaultRequestQueue;
    }
    
    /**
     * �Ăяo����񓯊��ɂ��邽�߂̃f�t�H���g��{@link Queue}�T�[�r�X��ݒ肷��B<p>
     *
     * @param queue �f�t�H���g��Queue�T�[�r�X
     */
    protected void setDefaultRequestQueueService(DefaultQueueService queue){
        defaultRequestQueue = queue;
    }
    
    /**
     * �񓯊��Ăяo���̖߂���i�[���邽�߂�{@link Queue}�T�[�r�X��ݒ肷��B<p>
     *
     * @param queue Queue�T�[�r�X
     */
    public void setResponseQueue(Queue queue){
        this.responseQueue = queue;
    }
    
    /**
     * �񓯊��Ăяo���̖߂���i�[����{���߂�@link Queue}�T�[�r�X���擾����B<p>
     *
     * @return Queue�T�[�r�X
     */
    protected Queue getResponseQueue(){
        if(responseQueue != null){
            return responseQueue;
        }
        if(getResponseQueueServiceName() != null){
            return(Queue)ServiceManagerFactory
                .getServiceObject(getResponseQueueServiceName());
        }
        if(responseTimeout > 0){
            final DefaultQueueService tmpQueue = new DefaultQueueService();
            try{
                tmpQueue.create();
                tmpQueue.start();
            }catch(Exception e){
                // �������Ȃ��͂�
            }
            return tmpQueue;
        }
        return null;
    }
    
    protected class Invoker implements DaemonRunnable{
        
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
         * @return �Ăяo�������i�[����{@link MethodAsynchronousInterceptorService.InvocationInfo}�I�u�W�F�N�g
         */
        public Object provide(DaemonControl ctrl){
            return getRequestQueueService().get();
        }
        
        /**
         * ����dequeued�œn���ꂽ{@link MethodAsynchronousInterceptorService.InvocationInfo}�I�u�W�F�N�g���g���āA���̃C���^�[�Z�v�^���Ăяo���B<p>
         * �Ăяo���̖߂�i�߂�l�܂���throw���ꂽ��O�j�́A{@link #setResponseQueueServiceName(ServiceName)}��{@link Queue}�T�[�r�X���ݒ肳��Ă���΁A����Queue�ɁA{@link AsynchronousResponse}�Ƃ��ċl�߂�B<br>
         *
         * @param dequeued �L���[������o���ꂽ�I�u�W�F�N�g
         * @param ctrl DaemonControl�I�u�W�F�N�g
         */
        public void consume(Object dequeued, DaemonControl ctrl){
            if(dequeued == null){
                return;
            }
            try{
                isActive = true;
                final InvocationInfo info = (InvocationInfo)dequeued;
                boolean throwException = false;
                Object ret = null;
                try{
                    ret = info.chain.invokeNext(info.context);
                }catch(Throwable e){
                    ret = e;
                    throwException = true;
                }
                final Queue resQueue = info.responseQueue;
                if(resQueue != null){
                    AsynchronousResponse response = null;
                    if(throwException){
                        final Class[] exceptionTypes
                             = info.context.getTargetMethod().getExceptionTypes();
                        boolean isThrowable = false;
                        if(RuntimeException.class.isInstance(ret)
                            || Error.class.isInstance(ret)
                        ){
                            isThrowable = true;
                        }else{
                            for(int i = 0; i < exceptionTypes.length; i++){
                                if(exceptionTypes[i].isInstance(ret)){
                                    isThrowable = true;
                                    break;
                                }
                            }
                        }
                        if(isThrowable){
                            response = new AsynchronousResponse(
                                info.context,
                                ret,
                                true
                            );
                        }
                    }else{
                        response = new AsynchronousResponse(info.context, ret);
                    }
                    if(!info.isTimeout && response != null){
                        resQueue.push(response);
                    }
                }
            }finally{
                isActive = false;
            }
        }
        
        /**
         * �L���[�̒��g��f���o���B<p>
         */
        public void garbage(){
            if(getRequestQueueService() != null){
                while(getRequestQueueService().size() > 0){
                    consume(getRequestQueueService().get(0), null);
                }
            }
        }
    }
    
    /**
     * �Ăяo�����B<p>
     *
     * @author M.Takata
     */
    protected static class InvocationInfo implements java.io.Serializable{
        
        private static final long serialVersionUID = 7784186054966609415L;
        
        /**
         * {@link Interceptor}�̃��\�b�h�Ăяo�����B<p>
         */
        public MethodInvocationContext context;
        
        /**
         * �C���^�[�Z�v�^�̃`�F�[���B<p>
         */
        public InterceptorChain chain;
        
        /**
         * ���X�|���X�҂������ă^�C���A�E�g�����ꍇ
         */
        public volatile boolean isTimeout;
        
        /**
         * ����Queue�B<p>
         */
        public Queue responseQueue;
        
        /**
         * �Ăяo�����𐶐�����B<p>
         *
         * @param context {@link Interceptor}�̃��\�b�h�Ăяo�����
         * @param chain �C���^�[�Z�v�^�̃`�F�[��
         * @param resQueue ����Queue
         */
        public InvocationInfo(
            MethodInvocationContext context,
            InterceptorChain chain,
            Queue resQueue
        ){
            this.context = context;
            this.chain = chain;
            responseQueue = resQueue;
        }
    }
}
