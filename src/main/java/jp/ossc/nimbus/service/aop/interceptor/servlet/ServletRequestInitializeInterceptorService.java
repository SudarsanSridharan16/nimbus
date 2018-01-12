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

import java.util.*;
import javax.servlet.*;

import jp.ossc.nimbus.core.*;
import jp.ossc.nimbus.service.aop.*;
import jp.ossc.nimbus.service.context.*;

/**
 * ���N�G�X�g�������C���^�[�Z�v�^�B<p>
 *
 * @author M.Takata
 */
public class ServletRequestInitializeInterceptorService
 extends ServletFilterInterceptorService
 implements ServletRequestInitializeInterceptorServiceMBean{
    
    private static final long serialVersionUID = 2753369702347163943L;
    
    protected ServiceName contextServiceName;
    protected Context context;
    protected String[] contextKeys;
    protected Map attributes;
    protected ServiceNameRef[] requestAttributeServiceNames;
    protected boolean isThrowServiceNotFoundException = true;
    
    // ServletRequestInitializeInterceptorServiceMBean��JavaDoc
    public void setContextServiceName(ServiceName name){
        contextServiceName = name;
    }
    // ServletRequestInitializeInterceptorServiceMBean��JavaDoc
    public ServiceName getContextServiceName(){
        return contextServiceName;
    }
    
    // ServletRequestInitializeInterceptorServiceMBean��JavaDoc
    public void setContextKeys(String[] keys){
        contextKeys = keys;
    }
    // ServletRequestInitializeInterceptorServiceMBean��JavaDoc
    public String[] getContextKeys(){
        return contextKeys;
    }
    
    // ServletRequestInitializeInterceptorServiceMBean��JavaDoc
    public void setRequestAttributeServiceNames(ServiceNameRef[] names){
        requestAttributeServiceNames = names;
    }
    
    // ServletRequestInitializeInterceptorServiceMBean��JavaDoc
    public ServiceNameRef[] getRequestAttributeServiceNames(){
        return requestAttributeServiceNames;
    }
    
    // ServletRequestInitializeInterceptorServiceMBean��JavaDoc
    public void setRequestAttributes(Map attrs){
        attributes.putAll(attrs);
    }
    
    // ServletRequestInitializeInterceptorServiceMBean��JavaDoc
    public Map getRequestAttributes(){
        return attributes;
    }
    
    // ServletRequestInitializeInterceptorServiceMBean��JavaDoc
    public void setRequestAttribute(String name, Object attr){
        attributes.put(name, attr);
    }
    // ServletRequestInitializeInterceptorServiceMBean��JavaDoc
    public Object getRequestAttribute(String name){
        return attributes.get(name);
    }
    
    // ServletRequestInitializeInterceptorServiceMBean��JavaDoc
    public boolean isThrowServiceNotFoundException(){
        return isThrowServiceNotFoundException;
    }
    // ServletRequestInitializeInterceptorServiceMBean��JavaDoc
    public void setThrowServiceNotFoundException(boolean isThrow){
        isThrowServiceNotFoundException = isThrow;
    }
    
    /**
     * �T�[�r�X�̐����������s���B<p>
     *
     * @exception Exception �T�[�r�X�̐����Ɏ��s�����ꍇ
     */
    public void createService() throws Exception{
        attributes = new HashMap();
    }
    
    /**
     * �T�[�r�X�̊J�n�������s���B<p>
     *
     * @exception Exception �T�[�r�X�̊J�n�Ɏ��s�����ꍇ
     */
    public void startService() throws Exception{
        if(contextServiceName != null){
            context = (Context)ServiceManagerFactory
                .getServiceObject(contextServiceName);
        }
    }
    
    /**
     * �T�[�r�X�̔j���������s���B<p>
     *
     * @exception Exception �T�[�r�X�̔j���Ɏ��s�����ꍇ
     */
    public void destroyService() throws Exception{
        attributes = null;
    }
    
    /**
     * ���N�G�X�g�����������āA���̃C���^�[�Z�v�^���Ăяo���B<p>
     * �T�[�r�X���J�n����Ă��Ȃ��ꍇ�́A���������Ɏ��̃C���^�[�Z�v�^���Ăяo���B<br>
     *
     * @param context �Ăяo���̃R���e�L�X�g���
     * @param chain ���̃C���^�[�Z�v�^���Ăяo�����߂̃`�F�[��
     * @return �Ăяo�����ʂ̖߂�l
     * @exception Throwable �Ăяo����ŗ�O�����������ꍇ�A�܂��͂��̃C���^�[�Z�v�^�ŔC�ӂ̗�O�����������ꍇ�B�A���A�{���Ăяo����鏈����throw���Ȃ�RuntimeException�ȊO�̗�O��throw���Ă��A�Ăяo�����ɂ͓`�d����Ȃ��B
     */
    public Object invokeFilter(
        ServletFilterInvocationContext context,
        InterceptorChain chain
    ) throws Throwable{
        if(getState() == STARTED){
            
            final ServletRequest request = context.getServletRequest();
            if(context != null && contextKeys != null){
                for(int i = 0; i < contextKeys.length; i++){
                    request.setAttribute(
                        contextKeys[i],
                        this.context.get(contextKeys[i])
                    );
                }
            }
            if(attributes != null && attributes.size() != 0){
                final Iterator names = attributes.keySet().iterator();
                while(names.hasNext()){
                    final String name = (String)names.next();
                    request.setAttribute(
                        name,
                        attributes.get(name)
                    );
                }
            }
            if(requestAttributeServiceNames != null){
                for(int i = 0; i < requestAttributeServiceNames.length; i++){
                    try{
                        request.setAttribute(
                            requestAttributeServiceNames[i]
                                .getReferenceServiceName(),
                            ServiceManagerFactory.getServiceObject(
                                requestAttributeServiceNames[i].getServiceName()
                            )
                        );
                    }catch(ServiceNotFoundException e){
                        if(isThrowServiceNotFoundException){
                            throw e;
                        }
                    }
                }
            }
        }
        return chain.invokeNext(context);
    }
}
