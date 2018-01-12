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
package jp.ossc.nimbus.service.proxy;

import java.net.*;
import java.lang.reflect.Method;
import java.rmi.RemoteException;
import javax.ejb.*;
import javax.naming.*;

import jp.ossc.nimbus.core.*;
import jp.ossc.nimbus.beans.*;
import jp.ossc.nimbus.service.aop.*;
import jp.ossc.nimbus.service.aop.invoker.*;
import jp.ossc.nimbus.service.performance.ResourceUsage;

/**
 * �����[�g�T�[�r�X�T�[�oEJB�����N���X�B<p>
 * �����[�g�T�[�r�X��Facade�ƂȂ�Stateless Session Bean�̎����N���X�ł���B<br>
 * ����EJB�����N���X���g�p����ꍇ�AEJB�̃f�v���C�����g�L�q�qejb-jar.xml��&lt;ejb-class&gt;�v�f�ɁA���̃N���X�̃N���X�����w�肷��K�v������B<br>
 * <pre>
 *   &lt;ejb-jar&gt;
 *     &lt;enterprise-beans&gt;
 *       &lt;session&gt;
 *         &lt;ejb-name&gt;RemoteServiceServer1&lt;/ejb-name&gt;
 *               :
 *         &lt;ejb-class&gt;jp.ossc.nimbus.service.remote.RemoteServiceServerSessionBean&lt;/ejb-class&gt;
 *               :
 * </pre>
 * 
 * @author M.Takata
 * @see RemoteServiceServerEJBHome
 * @see RemoteServiceServerEJBObject
 */
public class RemoteServiceServerSessionBean implements SessionBean{
    
    private static final long serialVersionUID = -1629897916230733253L;
    
    /**
     * EJB�̊��ϐ���JNDI����lookup����ׂ�JNDI�R���e�L�X�g���B<p>
     */
    private static final String JAVA_ENV_KEY = "java:comp/env";
    
    /**
     * &lt;env-entry&gt;�v�f�̎q�v�f&lt;env-entry-name&gt;�Ŏw�肷�郊���[�g�T�[�r�X���̃L�[���B<p>
     */
    public static final String REMOTE_SERVICE_NAME_ENV_KEY = "remote-service-name";
    
    /**
     * &lt;env-entry&gt;�v�f�̎q�v�f&lt;env-entry-name&gt;�Ŏw�肷��InterceptorChainList�T�[�r�X���̃L�[���B<p>
     */
    public static final String INTERCEPTOR_CHAIN_LIST_SERVICE_NAME_ENV_KEY = "interceptor-chain-list-service-name";
    
    /**
     * &lt;env-entry&gt;�v�f�̎q�v�f&lt;env-entry-name&gt;�Ŏw�肷��InterceptorChainFactory�T�[�r�X���̃L�[���B<p>
     */
    public static final String INTERCEPTOR_CHAIN_FACTORY_SERVICE_NAME_ENV_KEY = "interceptor-chain-factory-service-name";
    
    /**
     * &lt;env-entry&gt;�v�f�̎q�v�f&lt;env-entry-name&gt;�Ŏw�肷��Invoker�T�[�r�X���̃L�[���B<p>
     */
    public static final String INVOKER_SERVICE_NAME_ENV_KEY = "invoker-service-name";
    
    /**
     * &lt;env-entry&gt;�v�f�̎q�v�f&lt;env-entry-name&gt;�Ŏw�肷��ResourceUsage�T�[�r�X���̃L�[���B<p>
     */
    public static final String RESOURCE_USAGE_SERVICE_NAME_ENV_KEY = "resource-usage-service-name";
    
    /**
     * &lt;env-entry&gt;�v�f�̎q�v�f&lt;env-entry-name&gt;�Ŏw�肷�郊���[�g�T�[�r�X��`�t�@�C���p�X�̃L�[���B<p>
     */
    public static final String SERVICE_PATH_ENV_KEY = "service-path";
    
    /**
     * SessionContext �I�u�W�F�N�g�B<p>
     */
    private SessionContext sessionContext;
    
    /**
     * �����[�g�T�[�r�X���B<p>
     */
    private ServiceName remoteServiceName;
    
    /**
     * InterceptorChainList�T�[�r�X���B<p>
     */
    private ServiceName interceptorChainListServiceName;
    
    /**
     * InterceptorChainFactory�T�[�r�X���B<p>
     */
    private ServiceName interceptorChainFactoryServiceName;
    
    /**
     * Invoker�T�[�r�X���B<p>
     */
    private ServiceName invokerServiceName;
    
    /**
     * ResourceUsage�T�[�r�X���B<p>
     */
    private ServiceName resourceUsageServiceName;
    
    /**
     * �����[�g�T�[�r�X��`�t�@�C���p�X�B<p>
     */
    private String servicePath;
    
    private MethodReflectionCallInvokerService defaultInvoker;
    
    /**
     * �����[�g�Ăяo�������T�[�r�X���Ăяo���B<p>
     * �Ăяo���R���e�L�X�g��{@link InvocationContext#getTargetObject()}�Ŏ擾�����T�[�r�X���̃T�[�r�X�����[�J����{@link ServiceManager}����擾���āA{@link InvocationContext#setTargetObject(Object)}�ŁA�Ăяo���R���e�L�X�g�ɐݒ肷��B<br>
     * InvocationContext.getTargetObject()�ŃT�[�r�X�����擾�ł��Ȃ��ꍇ�́A&lt;env-entry&gt; "remote-service-name"�Őݒ肳�ꂽ�T�[�r�X���̃T�[�r�X���擾���āA�Ăяo���R���e�L�X�g�ɐݒ肷��B<br>
     * ���̌�A&lt;env-entry&gt; "interceptor-chain-list-service-name"��"invoker-service-name"�Ŏw�肳�ꂽ{@link InterceptorChainList}��{@link Invoker}���������A{@link InterceptorChain}�𐶐����āA�Ăяo���B<br>
     * 
     * @param context �Ăяo���R���e�L�X�g
     * @return �T�[�r�X�̌Ăяo������
     * @exception java.rmi.RemoteException �����[�g�Ăяo�������T�[�r�X�̌Ăяo���Ɏ��s�����ꍇ
     * @exception Exception �����[�g�Ăяo�������T�[�r�X�̌Ăяo���Ɏ��s�����ꍇ
     */
    public Object invoke(InvocationContext context)
     throws Exception, java.rmi.RemoteException{
        
        InterceptorChain chain = null;
        if(interceptorChainFactoryServiceName == null){
            chain = new DefaultInterceptorChain(
                interceptorChainListServiceName,
                invokerServiceName
            );
            if(invokerServiceName == null && defaultInvoker != null){
                ((DefaultInterceptorChain)chain).setInvoker(defaultInvoker);
            }
        }else{
            StringBuffer key = new StringBuffer();
            Object target = context.getTargetObject();
            if(target != null){
                key.append(target);
            }
            if(context instanceof MethodInvocationContext){
                Method method = ((MethodInvocationContext)context).getTargetMethod();
                if(method != null){
                    final MethodEditor editor = new MethodEditor();
                    editor.setValue(method);
                    key.append(':').append(editor.getAsText());
                }
            }
            InterceptorChainFactory interceptorChainFactory = (InterceptorChainFactory)ServiceManagerFactory
                    .getServiceObject(interceptorChainFactoryServiceName);
            chain = interceptorChainFactory.getInterceptorChain(key.length() == 0 ? null : key.toString());
        }
        
        ServiceName serviceName = remoteServiceName;
        if(serviceName == null
            && context.getTargetObject() != null
            && context.getTargetObject() instanceof ServiceName){
            serviceName = (ServiceName)context.getTargetObject();
        }
        if(serviceName == null){
            throw new ServiceNotFoundException(null);
        }
        context.setTargetObject(
            ServiceManagerFactory.getServiceObject(serviceName)
        );
        try{
            chain.setCurrentInterceptorIndex(-1);
            return chain.invokeNext(context);
        }catch(Exception e){
            throw e;
        }catch(Throwable e){
            e.printStackTrace();
            return null;
        }finally{
            chain.setCurrentInterceptorIndex(-1);
        }
    }
    
    public boolean isAlive(ServiceName name) throws java.rmi.RemoteException{
        ServiceName serviceName = remoteServiceName;
        if(name != null){
            if(remoteServiceName != null
                 && !remoteServiceName.equals(name)){
                return false;
            }
            serviceName = name;
        }
        if(serviceName == null){
            return true;
        }else{
            try{
                final Service service = ServiceManagerFactory.getService(serviceName);
                return service != null && service.getState() == Service.STARTED;
            }catch(ServiceNotFoundException e){
                return false;
            }
        }
    }
    
    public Comparable getResourceUsage() throws java.rmi.RemoteException{
        return resourceUsageServiceName == null ? null : ((ResourceUsage)ServiceManagerFactory.getServiceObject(resourceUsageServiceName)).getUsage();
    }
    
    /**
     * �֘A�t����ꂽSessionContext��ݒ肷��B<p>
     * 
     * @param context SessionContext�I�u�W�F�N�g
     * @exception EJBException �V�X�e�����x���̃G���[�������ŏ�Q�����������ꍇ
     * @exception RemoteException ���̗�O�́AEJB 1.0�d�l�����ɏ����ꂽ�G���^�[�v���C�YBean �ɉ��ʌ݊������������邽�߂Ƀ��\�b�h�̃V�O�j�`���[�ɒ�`����Ă���BEJB 1.1�d�l�����ɏ����ꂽ�G���^�[�v���C�YBean�́A���̗�O�̑����javax.ejb.EJBException���X���[����K�v������BEJB2.0�ȍ~�̎d�l�����ɏ����ꂽ�G���^�[�v���C�YBean�́A���̗�O�̑����javax.ejb.EJBException���X���[����K�v������
     * @see #getSessionContext()
     */
    public void setSessionContext(final SessionContext context)
     throws EJBException, RemoteException{
        sessionContext = context;
    }
    
    /**
     * �֘A�t����ꂽSessionContext���擾����B<p>
     * 
     * @return SessionContext�I�u�W�F�N�g
     * @see #setSessionContext(SessionContext)
     */
    public SessionContext getSessionContext(){
        if(sessionContext == null){
            throw new IllegalStateException("session context is invalid");
        }
        return sessionContext;
    }
    
    /**
     * �֘A�t����ꂽEJBContext���擾����B<p>
     * 
     * @return EJBContext�I�u�W�F�N�g
     */
    public EJBContext getEJBContext(){
        return getSessionContext();
    }
    
    /**
     * ����EJB�̃����[�g�Q�Ƃł���EJBObject�I�u�W�F�N�g���擾����B<p>
     * 
     * @return EJBObject�I�u�W�F�N�g
     */
    public EJBObject getEJBObject() {
        return getSessionContext().getEJBObject();
    }
    
    /**
     * activate���\�b�h�́A�C���X�^���X���u�񊈐����v��Ԃ��犈������ԂɂȂ�Ƃ��ɌĂяo�����B<p>
     * ���̃C���X�^���X�ł́A�ȑO��ejbPassivate()���\�b�h�ŉ���������\�[�X�����ׂĎ擾����K�v������B<br>
     * <p>
     * ���̃��\�b�h�́A�g�����U�N�V�����R���e�L�X�g���g�p���Ȃ��ŌĂяo�����B
     *
     * @exception EJBException �V�X�e�����x���̃G���[�������ŏ�Q�����������ꍇ
     * @exception RemoteException ���̗�O�́AEJB 1.0�d�l�����ɏ����ꂽ�G���^�[�v���C�YBean �ɉ��ʌ݊������������邽�߂Ƀ��\�b�h�̃V�O�j�`���[�ɒ�`����Ă���BEJB 1.1�d�l�����ɏ����ꂽ�G���^�[�v���C�YBean�́A���̗�O�̑����javax.ejb.EJBException���X���[����K�v������BEJB2.0�ȍ~�̎d�l�����ɏ����ꂽ�G���^�[�v���C�YBean�́A���̗�O�̑����javax.ejb.EJBException���X���[����K�v������
     */
    public void ejbActivate() throws EJBException, RemoteException{}
    
    /**
     * passivate���\�b�h�́A�C���X�^���X���u�񊈐����v��ԂɂȂ�O�ɌĂяo�����B<p>
     * ���̃C���X�^���X�ł́A���Ƃ�ejbActivate()���\�b�h�Ŏ擾���Ȃ������Ƃ��ł��郊�\�[�X�����ׂĉ������K�v������B<br>
     * <p>
     * passivate���\�b�h������������A���̃C���X�^���X�́A�R���e�i��Java Serialization�v���g�R�����g���ăC���X�^���X�̏�Ԃ��O�������A�ۊǂ��Ă������ԂɂȂ�Ȃ���΂Ȃ�܂���B<br>
     * <p>
     * ���̃��\�b�h�́A�g�����U�N�V�����R���e�L�X�g���g�p���Ȃ��ŌĂяo����܂��B<br>
     * 
     * @exception EJBException �V�X�e�����x���̃G���[�������ŏ�Q�����������ꍇ
     * @exception RemoteException ���̗�O�́AEJB 1.0�d�l�����ɏ����ꂽ�G���^�[�v���C�YBean �ɉ��ʌ݊������������邽�߂Ƀ��\�b�h�̃V�O�j�`���[�ɒ�`����Ă���BEJB 1.1�d�l�����ɏ����ꂽ�G���^�[�v���C�YBean�́A���̗�O�̑����javax.ejb.EJBException���X���[����K�v������BEJB2.0�ȍ~�̎d�l�����ɏ����ꂽ�G���^�[�v���C�YBean�́A���̗�O�̑����javax.ejb.EJBException���X���[����K�v������
     */
    public void ejbPassivate() throws EJBException, RemoteException{}
    
    /**
     * �R���e�i�ł́A�Z�b�V�����I�u�W�F�N�g�̗L�����Ԃ��I��点��O�ɁA���̃��\�b�h���Ăяo���܂��B<p>
     * ���̏����́A�N���C�A���g���폜�I�y���[�V�������Ăяo�������ʂƂ��āA�܂��̓R���e�i���^�C���A�E�g��ɃZ�b�V�����I�u�W�F�N�g���I��������Ƃ��ɍs���܂��B<br>
     * <p>
     * ���̃��\�b�h�́A�g�����U�N�V�����R���e�L�X�g���g�p���Ȃ��ŌĂяo����܂��B<br>
     * 
     * @exception EJBException �V�X�e�����x���̃G���[�������ŏ�Q�����������ꍇ
     * @exception RemoteException ���̗�O�́AEJB 1.0�d�l�����ɏ����ꂽ�G���^�[�v���C�YBean �ɉ��ʌ݊������������邽�߂Ƀ��\�b�h�̃V�O�j�`���[�ɒ�`����Ă���BEJB 1.1�d�l�����ɏ����ꂽ�G���^�[�v���C�YBean�́A���̗�O�̑����javax.ejb.EJBException���X���[����K�v������BEJB2.0�ȍ~�̎d�l�����ɏ����ꂽ�G���^�[�v���C�YBean�́A���̗�O�̑����javax.ejb.EJBException���X���[����K�v������
     */
    public void ejbRemove() throws EJBException, RemoteException{
        if(defaultInvoker != null){
            defaultInvoker.stop();
            defaultInvoker.destroy();
            defaultInvoker = null;
        }
        
        if(servicePath != null){
            final URL serviceURL
                 = getClass().getClassLoader().getResource(servicePath);
            ServiceManagerFactory.unloadManager(serviceURL);
        }
    }
    
    /**
     * �R���e�i�ł́A�Z�b�V�����I�u�W�F�N�g�̗L�����Ԃ��J�n������O�ɁA���̃��\�b�h���Ăяo���܂��B<p>
     * ���̏����́A�N���C�A���g�������I�y���[�V�������Ăяo�������ʂƂ��čs���܂��B<br>
     * <p>
     * ���̃��\�b�h�́A�g�����U�N�V�����R���e�L�X�g���g�p���Ȃ��ŌĂяo����܂��B<br>
     * 
     * @exception EJBException �V�X�e�����x���̃G���[�������ŏ�Q�����������ꍇ
     * @exception RemoteException ���̗�O�́AEJB 1.0�d�l�����ɏ����ꂽ�G���^�[�v���C�YBean �ɉ��ʌ݊������������邽�߂Ƀ��\�b�h�̃V�O�j�`���[�ɒ�`����Ă���BEJB 1.1�d�l�����ɏ����ꂽ�G���^�[�v���C�YBean�́A���̗�O�̑����javax.ejb.EJBException���X���[����K�v������BEJB2.0�ȍ~�̎d�l�����ɏ����ꂽ�G���^�[�v���C�YBean�́A���̗�O�̑����javax.ejb.EJBException���X���[����K�v������
     */
    public void ejbCreate() throws EJBException, RemoteException{
        final ServiceNameEditor editor = new ServiceNameEditor();
        
        final String remoteServiceNameStr
             = getEnvProperty(REMOTE_SERVICE_NAME_ENV_KEY);
        if(remoteServiceNameStr != null){
            editor.setAsText(remoteServiceNameStr);
            remoteServiceName = (ServiceName)editor.getValue();
        }
        
        final String interceptorChainListServiceNameStr
             = getEnvProperty(INTERCEPTOR_CHAIN_LIST_SERVICE_NAME_ENV_KEY);
        if(interceptorChainListServiceNameStr != null){
            editor.setAsText(interceptorChainListServiceNameStr);
            interceptorChainListServiceName = (ServiceName)editor.getValue();
        }
        
        final String interceptorChainFactoryServiceNameStr
             = getEnvProperty(INTERCEPTOR_CHAIN_FACTORY_SERVICE_NAME_ENV_KEY);
        if(interceptorChainFactoryServiceNameStr != null){
            editor.setAsText(interceptorChainFactoryServiceNameStr);
            interceptorChainFactoryServiceName = (ServiceName)editor.getValue();
        }
        
        final String invokerServiceNameStr
             = getEnvProperty(INVOKER_SERVICE_NAME_ENV_KEY);
        if(invokerServiceNameStr == null){
            try{
                if(defaultInvoker == null){
                    defaultInvoker = new MethodReflectionCallInvokerService();
                    defaultInvoker.create();
                }
                defaultInvoker.start();
            }catch(Exception e){
                throw new EJBException(e);
            }
        }else{
            editor.setAsText(invokerServiceNameStr);
            invokerServiceName = (ServiceName)editor.getValue();
        }
        
        final String resourceUsageServiceNameStr
             = getEnvProperty(RESOURCE_USAGE_SERVICE_NAME_ENV_KEY);
        if(resourceUsageServiceNameStr != null){
            editor.setAsText(resourceUsageServiceNameStr);
            resourceUsageServiceName = (ServiceName)editor.getValue();
        }
        
        servicePath = getEnvProperty(SERVICE_PATH_ENV_KEY);
        if(servicePath != null){
            final URL serviceURL
                 = getClass().getClassLoader().getResource(servicePath);
            ServiceManagerFactory.loadManager(serviceURL);
        }
    }
    
    /**
     * EJB�̊��ϐ���JNDI����lookup���āA�擾����B<p>
     *
     * @param name ���ϐ���
     * @return ���ϐ��B������Ȃ��ꍇ�́Anull��Ԃ��B
     */
    private static String getEnvProperty(String name){
        String value = null;
        try{
            final Context context = new InitialContext();
            final Context env = (Context)context.lookup(JAVA_ENV_KEY);
            value = (String)env.lookup(name);
        }catch(NamingException e){
        }
        return value;
    }
}