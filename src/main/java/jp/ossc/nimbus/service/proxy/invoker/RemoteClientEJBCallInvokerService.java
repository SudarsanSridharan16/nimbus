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
package jp.ossc.nimbus.service.proxy.invoker;

import jp.ossc.nimbus.core.*;
import jp.ossc.nimbus.service.ejb.*;
import jp.ossc.nimbus.service.aop.*;
import jp.ossc.nimbus.service.proxy.RemoteServerInvoker;
import jp.ossc.nimbus.service.proxy.RemoteServiceCallException;

/**
 * �����[�g�N���C�A���gEJB�Ăяo��Invoker�B<p>
 * EJB�o�R�ŁA�����[�g�T�[�o��̃T�[�r�X���Ăяo�����߂�Invoker�ł���B<br>
 * �����[�g�T�[�o���ɁA{@link RemoteServerInvoker}�C���^�t�F�[�X����������EJB���f�v���C����Ă��Ȃ���΂Ȃ�Ȃ��B�]���āA{@link jp.ossc.nimbus.service.proxy.RemoteServiceServerSessionBean RemoteServiceServerSessionBean}�������[�g�T�[�o���ɁA�f�v���C���Ă����B<br>
 *
 * @author M.Takata
 */
public class RemoteClientEJBCallInvokerService extends ServiceBase
 implements Invoker, java.io.Serializable,
            RemoteClientEJBCallInvokerServiceMBean{
    
    private static final long serialVersionUID = -7734676901899009764L;
    
    private ServiceName ejbFactoryServiceName;
    private EJBFactory ejbFactory;
    private String jndiName;
    private ServiceName remoteServiceName;
    
    // RemoteClientEJBCallInvokerServiceMBean��JavaDoc
    public void setEJBFactoryServiceName(ServiceName name){
        ejbFactoryServiceName = name;
    }
    // RemoteClientEJBCallInvokerServiceMBean��JavaDoc
    public ServiceName getEJBFactoryServiceName(){
        return ejbFactoryServiceName;
    }
    
    // RemoteClientEJBCallInvokerServiceMBean��JavaDoc
    public void setRemoteServerEJBJndiName(String name){
        jndiName = name;
    }
    // RemoteClientEJBCallInvokerServiceMBean��JavaDoc
    public String getRemoteServerEJBJndiName(){
        return jndiName;
    }
    
    // RemoteClientEJBCallInvokerServiceMBean��JavaDoc
    public void setRemoteServiceName(ServiceName name){
        remoteServiceName = name;
    }
    // RemoteClientEJBCallInvokerServiceMBean��JavaDoc
    public ServiceName getRemoteServiceName(){
        return remoteServiceName;
    }
    
    /**
     * {@link RemoteServerInvoker}�C���^�t�F�[�X����������EJB���擾����{@link jp.ossc.nimbus.service.ejb.EJBFactory EJBFactory}�T�[�r�X��ݒ肷��B<p>
     *
     * @param ejbFactory EJBFactory�T�[�r�X
     */
    public void setEjbFactory(EJBFactory ejbFactory) {
        this.ejbFactory = ejbFactory;
    }
    
    /**
     * �T�[�r�X�̊J�n�������s���B<p>
     *
     * @exception Exception �T�[�r�X�̊J�n�����Ɏ��s�����ꍇ
     */
    public void startService() throws Exception{
        if(ejbFactoryServiceName != null){
            ejbFactory = (EJBFactory)ServiceManagerFactory
                .getServiceObject(ejbFactoryServiceName);
        }
        if(ejbFactory == null) {
            throw new IllegalArgumentException(
                "EjbFactoryServiceName or EjbFactory must be specified."
            );
        }
        
        if(jndiName == null){
            throw new IllegalArgumentException(
                "jndiName must be specified."
            );
        }
    }
    
    /**
     * {@link RemoteServerInvoker}�C���^�t�F�[�X����������EJB���Ăяo���B<p>
     * 
     * @param context �Ăяo���̃R���e�L�X�g���
     * @return �Ăяo�����ʂ̖߂�l
     * @exception Throwable �Ăяo����ŗ�O�����������ꍇ
     */
    public Object invoke(InvocationContext context) throws Throwable{
        final MethodInvocationContext methodContext
             = (MethodInvocationContext)context;
        try{
            final RemoteServerInvoker serverInvoker
                 = (RemoteServerInvoker)ejbFactory.get(jndiName);
            if(remoteServiceName != null){
                methodContext.setTargetObject(remoteServiceName);
            }
            return serverInvoker.invoke(context);
        }catch(javax.naming.NamingException e){
            throw new RemoteServiceCallException(e);
        }catch(javax.ejb.CreateException e){
            throw new RemoteServiceCallException(e);
        }catch(NoSuchMethodException e){
            throw new RemoteServiceCallException(e);
        }catch(IllegalAccessException e){
            throw new RemoteServiceCallException(e);
        }catch(java.lang.reflect.InvocationTargetException e){
            throw new RemoteServiceCallException(e.getTargetException());
        }
    }
}
