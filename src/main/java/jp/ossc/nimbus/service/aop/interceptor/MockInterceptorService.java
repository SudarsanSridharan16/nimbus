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

import java.lang.reflect.Method;

import jp.ossc.nimbus.core.*;
import jp.ossc.nimbus.service.aop.*;

/**
 * ���b�N�C���^�[�Z�v�^�B<p>
 * ���\�b�h�̌Ăяo���ɑ΂��āA�Ăяo���Ώۂ̃I�u�W�F�N�g��{@link MockFactory}���������郂�b�N�A�܂��͐ݒ肳�ꂽ���b�N�ɂ��肩����C���^�[�Z�v�^�ł���B
 * �ȉ��ɁA���b�N�C���^�[�Z�v�^�̃T�[�r�X��`��������B<br>
 * <pre>
 * &lt;?xml version="1.0" encoding="Shift_JIS"?&gt;
 * 
 * &lt;server&gt;
 *     
 *     &lt;manager name="Sample"&gt;
 *         
 *         &lt;service name="MockInterceptor"
 *                  code="jp.ossc.nimbus.service.aop.interceptor.MockInterceptorService"&gt;
 *             &lt;attribute name="Mock"&gt;
 *                 &lt;object code="sample.MockConnection"/&gt;
 *             &lt;/attribute&gt;
 *         &lt;/service&gt;
 *         
 *     &lt;/manager&gt;
 *     
 * &lt;/server&gt;
 * </pre>
 *
 * @author M.Takata
 */
public class MockInterceptorService extends ServiceBase
 implements Interceptor, MockInterceptorServiceMBean{
    
    private static final long serialVersionUID = 5565234776209550375L;
    
    private ServiceName mockFactoryServiceName;
    private MockFactory mockFactory;
    private ServiceName mockServiceName;
    private Object mock;
    
    // MockInterceptorServiceMBean��JavaDoc
    public void setMockFactoryServiceName(ServiceName name){
        mockFactoryServiceName = name;
    }
    // MockInterceptorServiceMBean��JavaDoc
    public ServiceName getMockFactoryServiceName(){
        return mockFactoryServiceName;
    }
    
    // MockInterceptorServiceMBean��JavaDoc
    public void setMockServiceName(ServiceName name){
        mockServiceName = name;
    }
    // MockInterceptorServiceMBean��JavaDoc
    public ServiceName getMockServiceName(){
        return mockServiceName;
    }
    
    /**
     * MockFactory��ݒ肷��B<p>
     *
     * @param mockFactory MockFactory
     */
    public void setMockFactory(MockFactory mockFactory) {
        this.mockFactory = mockFactory;
    }
    
    /**
     * ���b�N��ݒ肷��B<p>
     *
     * @param mock ���b�N
     */
    public void setMock(Object mock) {
        this.mock = mock;
    }
    
    /**
     * �T�[�r�X�̊J�n�������s���B<p>
     *
     * @exception Exception �J�n�����Ɏ��s�����ꍇ
     */
    public void startService() throws Exception{
        if(mockFactoryServiceName != null){
            mockFactory = (MockFactory)ServiceManagerFactory
                .getServiceObject(mockFactoryServiceName);
        }else if(mockFactory == null
            && mockServiceName == null
            && mock == null
        ){
            throw new IllegalArgumentException("It is necessary to set any of mockFactory or mockFactoryServiceName or mockServiceName or mock.");
        }
    }
    
    /**
     * �ݒ肳�ꂽ{@link MockFactory}���Ăяo���Ώۂ̃I�u�W�F�N�g�Ƃ���ւ��āA���̃C���^�[�Z�v�^���Ăяo���B<p>
     * �T�[�r�X���J�n����Ă��Ȃ��ꍇ�́A����ւ����s�킸�Ɏ��̃C���^�[�Z�v�^���Ăяo���B<br>
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
        final MethodInvocationContext ctx = (MethodInvocationContext)context;
        final Object target = ctx.getTargetObject();
        final Method targetMethod = ctx.getTargetMethod();
        try{
            if(getState() == STARTED){
                Object mock = null;
                if(mockFactory != null){
                    mock = mockFactory.createMock(ctx);
                }else if(this.mock != null){
                    mock = this.mock;
                }else if(mockServiceName != null){
                    try{
                        mock = ServiceManagerFactory
                            .getServiceObject(mockServiceName);
                    }catch(ServiceNotFoundException e){
                    }
                }
                if(mock != null){
                    Method mockMethod = null;
                    try{
                        mockMethod = mock.getClass().getMethod(
                            targetMethod.getName(),
                            targetMethod.getParameterTypes()
                        );
                        ctx.setTargetObject(mock);
                        ctx.setTargetMethod(mockMethod);
                    }catch(NoSuchMethodException e){
                        // TODO ���O�o��
                    }
                }
            }
            return chain.invokeNext(context);
        }finally{
            if(getState() == STARTED){
                ctx.setTargetObject(target);
                ctx.setTargetMethod(targetMethod);
            }
        }
    }
}
