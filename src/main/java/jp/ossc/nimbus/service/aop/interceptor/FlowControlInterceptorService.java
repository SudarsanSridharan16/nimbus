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
import jp.ossc.nimbus.service.semaphore.*;

/**
 * ���ʐ���C���^�[�Z�v�^�B<p>
 * �Ăяo���ɑ΂��āA�Z�}�t�H�T�[�r�X���g���ė��ʐ�����s���C���^�[�Z�v�^�ł���B<br>
 * �ȉ��ɁA�R�܂ł��������ɃA�N�Z�X�ł��Ȃ��悤�ɗ��ʐ��䂷��C���^�[�Z�v�^�̃T�[�r�X��`��������B<br>
 * <pre>
 * &lt;?xml version="1.0" encoding="Shift_JIS"?&gt;
 * 
 * &lt;server&gt;
 *     
 *     &lt;manager name="Sample"&gt;
 *         
 *         &lt;service name="FlowControlInterceptor"
 *                  code="jp.ossc.nimbus.service.aop.interceptor.FlowControlInterceptorService"&gt;
 *             &lt;attribute name="SemaphoreServiceName"&gt;#Semaphore&lt;/attribute&gt;
 *             &lt;depends&gt;Semaphore&lt;/depends&gt;
 *         &lt;/service&gt;
 *         
 *         &lt;service name="Semaphore"
 *                  code="jp.ossc.nimbus.service.semaphore.DefaultSemaphoreService"&gt;
 *             &lt;attribute name="ResourceCapacity"&gt;3&lt;/attribute&gt;
 *         &lt;/service&gt;
 *         
 *     &lt;/manager&gt;
 *     
 * &lt;/server&gt;
 * </pre>
 *
 * @author M.Takata
 * @see Semaphore
 */
public class FlowControlInterceptorService extends ServiceBase
 implements Interceptor, FlowControlInterceptorServiceMBean{
    
    private static final long serialVersionUID = 519397295732596256L;
    
    private ServiceName semaphoreServiceName;
    private Semaphore semaphore;
    private long timeout = -1;
    private boolean isFailToObtainSemaphore = true;
    private int maxWaitingCount = -1;
    private long forceFreeTimeout = -1L;
    
    // FlowControlInterceptorServiceMBean��JavaDoc
    public void setSemaphoreServiceName(ServiceName name){
        semaphoreServiceName = name;
    }
    
    // FlowControlInterceptorServiceMBean��JavaDoc
    public ServiceName getSemaphoreServiceName(){
        return semaphoreServiceName;
    }
    
    // FlowControlInterceptorServiceMBean��JavaDoc
    public void setTimeout(long timeout){
        this.timeout = timeout;
    }
    
    // FlowControlInterceptorServiceMBean��JavaDoc
    public long getTimeout(){
        return timeout;
    }
    
    // FlowControlInterceptorServiceMBean��JavaDoc
    public void setMaxWaitingCount(int count){
        maxWaitingCount = count;
    }
    
    // FlowControlInterceptorServiceMBean��JavaDoc
    public int getMaxWaitingCount(){
        return maxWaitingCount;
    }
    
    // FlowControlInterceptorServiceMBean��JavaDoc
    public void setForceFreeTimeout(long timeout){
        forceFreeTimeout = timeout;
    }
    
    // FlowControlInterceptorServiceMBean��JavaDoc
    public long getForceFreeTimeout(){
        return forceFreeTimeout;
    }
    
    // FlowControlInterceptorServiceMBean��JavaDoc
    public void setFailToObtainSemaphore(boolean isThrow){
        isFailToObtainSemaphore = isThrow;
    }
    
    // FlowControlInterceptorServiceMBean��JavaDoc
    public boolean isFailToObtainSemaphore(){
        return isFailToObtainSemaphore;
    }
    
    /**
     * Semaphore��ݒ肷��B<p>
     *
     * @param semaphore Semaphore
     */
    public void setSemaphore(Semaphore semaphore) {
        this.semaphore = semaphore;
    }
    
    /**
     * �T�[�r�X�̊J�n�������s���B<p>
     *
     * @exception Exception {@link Semaphore}�T�[�r�X�̎擾�Ɏ��s�����ꍇ
     */
    public void startService() throws Exception{
        if(semaphore == null && semaphoreServiceName != null){
            semaphore = (Semaphore)ServiceManagerFactory.getServiceObject(
                semaphoreServiceName
            );
        }
        if(semaphore != null){
            semaphore.accept();
        }
    }
    
    /**
     * �T�[�r�X�̒�~�������s���B<p>
     *
     * @exception Exception �T�[�r�X�̒�~�����Ɏ��s�����ꍇ
     */
    public void stopService() throws Exception{
        if(semaphore != null){
            semaphore.release();
        }
    }
    
    /**
     * ���ʐ�������āA���̃C���^�[�Z�v�^���Ăяo���B<p>
     * �T�[�r�X���J�n����Ă��Ȃ��ꍇ�A�y��{@link Semaphore}�T�[�r�X���w�肳��Ă��Ȃ��ꍇ�́A���ʐ�����s�킸�Ɏ��̃C���^�[�Z�v�^���Ăяo���B<br>
     * 
     *
     * @param context �Ăяo���̃R���e�L�X�g���
     * @param chain ���̃C���^�[�Z�v�^���Ăяo�����߂̃`�F�[��
     * @return �Ăяo�����ʂ̖߂�l
     * @exception Throwable �Ăяo����ŗ�O�����������ꍇ�A�܂��͂��̃C���^�[�Z�v�^�ŔC�ӂ̗�O�����������ꍇ�B�A���A�{���Ăяo����鏈����throw���Ȃ�RuntimeException�ȊO�̗�O��throw���Ă��A�Ăяo�����ɂ͓`�d����Ȃ��B
     */
    public Object invoke(
        InvocationContext context,
        InterceptorChain chain
    ) throws Throwable{
        if(getState() == STARTED && semaphore != null){
            final boolean isSuccess = semaphore.getResource(
                timeout,
                maxWaitingCount,
                forceFreeTimeout
            );
            Thread.interrupted();
            if(!isSuccess){
                if(isFailToObtainSemaphore){
                    throw new FailToObtainSemaphoreException();
                }else{
                    return null;
                }
            }
            try{
                return chain.invokeNext(context);
            }finally{
                semaphore.freeResource();
            }
        }else{
            return chain.invokeNext(context);
        }
    }
}
