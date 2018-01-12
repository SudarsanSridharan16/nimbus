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
package jp.ossc.nimbus.service.aop.interceptor.servlet;

import jp.ossc.nimbus.core.*;
import jp.ossc.nimbus.service.aop.*;

/**
 * �T�[�u���b�g�t�B���^�C���^�[�Z�v�^�A�_�v�^�B<p>
 * {@link Interceptor}�ɁA{@link ServletFilterInterceptorService}�̋@�\��t������A�_�v�^�ł���B<br>
 *
 * @author M.Takata
 */
public class ServletFilterInterceptorAdapterService
 extends ServletFilterInterceptorService
 implements ServletFilterInterceptorAdapterServiceMBean{
    
    private static final long serialVersionUID = -3558138995073714122L;
    
    private ServiceName interceptorServiceName;
    private Interceptor interceptor;
    
    // ServletFilterInterceptorAdapterServiceMBean��JavaDoc
    public void setInterceptorServiceName(ServiceName name){
        interceptorServiceName = name;
    }
    // ServletFilterInterceptorAdapterServiceMBean��JavaDoc
    public ServiceName getInterceptorServiceName(){
        return interceptorServiceName;
    }
    
    public void startService() throws Exception{
        if(interceptor == null){
            if(interceptorServiceName == null){
                throw new IllegalArgumentException(
                    "interceptorServiceName must be specified."
                );
            }
            interceptor = (Interceptor)ServiceManagerFactory.getServiceObject(interceptorServiceName);
        }
    }
    
    public void stopService() throws Exception{
        if(interceptorServiceName != null){
            interceptor = null;
        }
    }
    
    public void setInterceptor(Interceptor val){
        interceptor = val;
    }
    
    /**
     * �T�[�u���b�g�Ăяo�����C���^�[�Z�v�g���āA�A�_�v�^���Ă���C���^�[�Z�v�^���Ăяo���B<p>
     *
     * @param context �T�[�u���b�g�t�B���^�Ăяo���̃R���e�L�X�g���
     * @param chain ���̃C���^�[�Z�v�^���Ăяo�����߂̃`�F�[��
     * @return �Ăяo�����ʂ̖߂�l
     * @exception Throwable �Ăяo����ŗ�O�����������ꍇ�A�܂��͂��̃C���^�[�Z�v�^�ŔC�ӂ̗�O�����������ꍇ
     */
    public Object invokeFilter(
        ServletFilterInvocationContext context,
        InterceptorChain chain
    ) throws Throwable{
        if(interceptor != null){
            return interceptor.invoke(context, chain);
        }else{
            return chain.invokeNext(context);
        }
    }
}