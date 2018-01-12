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

import java.util.*;

/**
 * �`�F�b�N�|�C���g�C���^�[�Z�v�^�B<p>
 * ���\�b�h�̌Ăяo���ɑ΂��āA�ʉ߂����X���b�h���L�^����C���^�[�Z�v�^�ł���B<br>
 * �ȉ��ɁA�`�F�b�N�|�C���g�C���^�[�Z�v�^�̃T�[�r�X��`��������B<br>
 * <pre>
 * &lt;?xml version="1.0" encoding="Shift_JIS"?&gt;
 * 
 * &lt;server&gt;
 *     
 *     &lt;manager name="Sample"&gt;
 *         
 *         &lt;service name="CheckPointInterceptor"
 *                  code="jp.ossc.nimbus.service.aop.interceptor.CheckPointInterceptorService"/&gt;
 *         
 *     &lt;/manager&gt;
 *     
 * &lt;/server&gt;
 * </pre>
 *
 * @author M.Takata
 */
public class CheckPointInterceptorService extends ServiceBase
 implements Interceptor, CheckPointInterceptorServiceMBean{
    
    private static final long serialVersionUID = -7319984801053501636L;
    
    private String checkPointName;
    private boolean enabled = true;
    private List passOverThreads = new ArrayList();
    private ServiceName checkPointTracerServiceName;
    private CheckPointTracer checkPointTracer;
    
    // CheckPointInterceptorServiceMBean��JavaDoc
    public void setEnabled(boolean enabled){
        this.enabled = enabled;
    }
    // CheckPointInterceptorServiceMBean��JavaDoc
    public boolean isEnabled(){
        return enabled;
    }
    
    // CheckPointInterceptorServiceMBean��JavaDoc
    public List passOverThreads(){
        synchronized(passOverThreads){
            return new ArrayList(passOverThreads);
        }
    }
    
    // CheckPointInterceptorServiceMBean��JavaDoc
    public void clear(){
        if(passOverThreads != null){
            passOverThreads.clear();
        }
    }
    
    // CheckPointInterceptorServiceMBean��JavaDoc
    public void setCheckPointName(String name){
        checkPointName = name;
    }
    
    // CheckPointInterceptorServiceMBean��JavaDoc
    public String getCheckPointName(){
        if(checkPointName == null && getServiceNameObject() != null){
            return getServiceNameObject().toString();
        }
        return checkPointName;
    }
    
    // CheckPointInterceptorServiceMBean��JavaDoc
    public void setCheckPointTracerServiceName(ServiceName name){
        checkPointTracerServiceName = name;
    }
    
    // CheckPointInterceptorServiceMBean��JavaDoc
    public ServiceName getCheckPointTracerServiceName(){
        return checkPointTracerServiceName;
    }
    
    /**
     * {@link CheckPointTracer}��ݒ肷��B<p>
     *
     * @param tracer CheckPointTracer
     */
    public void setCheckPointTracer(CheckPointTracer tracer){
        checkPointTracer = tracer;
    }
    
    public void startService() throws Exception{
        if(checkPointTracerServiceName != null){
            checkPointTracer = (CheckPointTracer)ServiceManagerFactory.getServiceObject(checkPointTracerServiceName);
        }
    }
    
    public void stopService() throws Exception{
        clear();
    }
    
    /**
     * �ʉ߂����X���b�h�����L�^���āA���̃C���^�[�Z�v�^���Ăяo���B<p>
     * �T�[�r�X���J�n����Ă��Ȃ��ꍇ�́A���������Ɏ��̃C���^�[�Z�v�^���Ăяo���B<br>
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
        if(getState() == STARTED && enabled){
            final String threadName = Thread.currentThread().getName();
            synchronized(passOverThreads){
                passOverThreads.add(threadName);
            }
            if(checkPointTracer != null){
                checkPointTracer.passedCheckPoint(threadName, this);
            }
        }
        return chain.invokeNext(context);
    }
}
