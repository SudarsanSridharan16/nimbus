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

import java.io.Serializable;
import java.util.Map;
import java.util.Iterator;
import java.lang.reflect.InvocationTargetException;
import javax.servlet.http.*;

import jp.ossc.nimbus.beans.PropertyAccess;
import jp.ossc.nimbus.beans.NoSuchPropertyException;
import jp.ossc.nimbus.core.*;
import jp.ossc.nimbus.service.aop.*;
import jp.ossc.nimbus.service.context.Context;

/**
 * �F�؃C���^�[�Z�v�^�B<p>
 * ���O�C���̃��N�G�X�g�������s���A�v���P�[�V�����ŔF�؏��𐶐����A�F�؃��N�G�X�g����(��������{@link #getAuthenticatedInfoAttributeName()})�ɐݒ肷��ƁA���̃C���^�[�Z�v�^���F�؏����Z�b�V�����̑����Ƃ��Đݒ肷��B<br>
 * ���O�C����̃��N�G�X�g�����ł́A���̓��N�G�X�g����(��������{@link #getRequestObjectAttributeName()})������̓I�u�W�F�N�g���擾���A�F�؃Z�b�V������������擾�����F�؏��Ƃ̔�r���s���A�F�؂���Ă��邩�ǂ������`�F�b�N����B���̓I�u�W�F�N�g�ƔF�؏��̔�r���ǂ̂悤�ɍs�����́A{@link #setAuthenticatedInfoMapping(Map)}�Őݒ肷��B�F�؏��ƍ��v���Ȃ��ꍇ�́A{@link AuthenticateException}��throw����B<br>
 * ���O�A�E�g�̃��N�G�X�g��������������ƁA�Z�b�V��������F�؏����폜����B<br>
 * <p>
 * {@link AuthenticateStore}��ݒ肷��ƁA���O�C�����ɂ�{@link AuthenticateStore#create(HttpServletRequest, Object)}���Ăяo���A�F�؏����X�g�A����B<br>
 * ���O�C����̃��N�G�X�g�����ŁA�F�؃Z�b�V������������F�؏�񂪎擾�ł��Ȃ��ꍇ�A{@link AuthenticateStore#activate(HttpServletRequest, Object)}���Ăяo���A�F�؏��𕜌�����B<br>
 * ���O�A�E�g�̃��N�G�X�g��������������ƁA{@link AuthenticateStore#destroy(HttpServletRequest, Object)}���Ăяo���A�F�؏����폜����B<br>
 * �Z�b�V�����^�C���A�E�g����������ƁA{@link AuthenticateStore#deactivate(HttpSession, Object)}���Ăяo���A�F�؏���񊈐�������B<br>
 * �ȉ��ɁA�F�؃C���^�[�Z�v�^�̃T�[�r�X��`��������B<br>
 * <pre>
 * &lt;?xml version="1.0" encoding="Shift_JIS"?&gt;
 * &lt;server&gt;
 *     &lt;manager name="Sample"&gt;
 *         &lt;service name="AuthenticateInterceptor"
 *                  code="jp.ossc.nimbus.service.aop.interceptor.servlet.AuthenticateInterceptorService"&gt;
 *             &lt;attribute name="AuthenticatedInfoMapping"&gt;
 *                 Header(Common).id=id
 *                 Header(Common).sessionId=sessionId
 *             &lt;/attribute&gt;
 *             &lt;attribute name="LoginPath"&gt;/login.bf&lt;/attribute&gt;
 *             &lt;attribute name="LogoutPath"&gt;/logout.bf&lt;/attribute&gt;
 *         &lt;/service&gt;
 *     &lt;/manager&gt;
 * &lt;/server&gt;
 * </pre>
 *
 * @author M.Takata
 */
public class AuthenticateInterceptorService extends ServletFilterInterceptorService
 implements AuthenticateInterceptorServiceMBean{

    private static final long serialVersionUID = -4298385595443568724L;

    protected String authenticatedInfoAttributeName
         = DEFAULT_AUTH_INFO_ATTRIBUTE_NAME;

    protected String authenticatedInfoContextKey
         = DEFAULT_AUTH_INFO_ATTRIBUTE_NAME;

    protected String requestObjectAttributeName
         = StreamExchangeInterceptorServiceMBean.DEFAULT_REQUEST_OBJECT_ATTRIBUTE_NAME;

    protected String requestObjectContextKey
         = StreamExchangeInterceptorServiceMBean.DEFAULT_REQUEST_OBJECT_CONTEXT_KEY;

    protected ServiceName threadContextServiceName;
    protected Context threadContext;
    protected Map authenticatedInfoMapping;
    protected PropertyAccess propertyAccess;
    protected String loginPath;
    protected String logoutPath;
    protected ServiceName authenticateStoreServiceName;
    protected AuthenticateStore authenticateStore;
    protected boolean isStoreCreate = true;
    protected boolean isStoreDestroy = true;
    protected boolean isSessionInvalidate = false;

    // AuthenticateInterceptorServiceMBean ��JavaDoc
    public void setThreadContextServiceName(ServiceName name){
        threadContextServiceName = name;
    }
    // AuthenticateInterceptorServiceMBean ��JavaDoc
    public ServiceName getThreadContextServiceName(){
        return threadContextServiceName;
    }

    // AuthenticateInterceptorServiceMBean ��JavaDoc
    public void setRequestObjectAttributeName(String name){
        requestObjectAttributeName = name;
    }
    // AuthenticateInterceptorServiceMBean ��JavaDoc
    public String getRequestObjectAttributeName(){
        return requestObjectAttributeName;
    }

    // AuthenticateInterceptorServiceMBean ��JavaDoc
    public void setRequestObjectContextKey(String key){
        requestObjectContextKey = key;
    }
    // AuthenticateInterceptorServiceMBean ��JavaDoc
    public String getRequestObjectContextKey(){
        return requestObjectContextKey;
    }

    // AuthenticateInterceptorServiceMBean ��JavaDoc
    public void setAuthenticatedInfoAttributeName(String name){
        authenticatedInfoAttributeName = name;
    }
    // AuthenticateInterceptorServiceMBean ��JavaDoc
    public String getAuthenticatedInfoAttributeName(){
        return authenticatedInfoAttributeName;
    }

    // AuthenticateInterceptorServiceMBean ��JavaDoc
    public void setAuthenticatedInfoContextKey(String name){
        authenticatedInfoContextKey = name;
    }
    // AuthenticateInterceptorServiceMBean ��JavaDoc
    public String getAuthenticatedInfoContextKey(){
        return authenticatedInfoContextKey;
    }

    // AuthenticateInterceptorServiceMBean ��JavaDoc
    public void setAuthenticatedInfoMapping(Map mapping){
        authenticatedInfoMapping = mapping;
    }
    // AuthenticateInterceptorServiceMBean ��JavaDoc
    public Map getAuthenticatedInfoMapping(){
        return authenticatedInfoMapping;
    }

    // AuthenticateInterceptorServiceMBean ��JavaDoc
    public void setLoginPath(String path){
        loginPath = path;
    }
    // AuthenticateInterceptorServiceMBean ��JavaDoc
    public String getLoginPath(){
        return loginPath;
    }

    // AuthenticateInterceptorServiceMBean ��JavaDoc
    public void setLogoutPath(String path){
        logoutPath = path;
    }
    // AuthenticateInterceptorServiceMBean ��JavaDoc
    public String getLogoutPath(){
        return logoutPath;
    }

    // AuthenticateInterceptorServiceMBean ��JavaDoc
    public void setAuthenticateStoreServiceName(ServiceName name){
        authenticateStoreServiceName = name;
    }
    // AuthenticateInterceptorServiceMBean ��JavaDoc
    public ServiceName getAuthenticateStoreServiceName(){
        return authenticateStoreServiceName;
    }

    // AuthenticateInterceptorServiceMBean ��JavaDoc
    public void setStoreCreate(boolean isCreate){
        isStoreCreate = isCreate;
    }
    // AuthenticateInterceptorServiceMBean ��JavaDoc
    public boolean isStoreCreate(){
        return isStoreCreate;
    }

    // AuthenticateInterceptorServiceMBean ��JavaDoc
    public void setStoreDestroy(boolean isDestroy){
        isStoreDestroy = isDestroy;
    }
    // AuthenticateInterceptorServiceMBean ��JavaDoc
    public boolean isStoreDestroy(){
        return isStoreDestroy;
    }

    // AuthenticateInterceptorServiceMBean ��JavaDoc
    public void setSessionInvalidate(boolean isInvalidate){
        isSessionInvalidate = isInvalidate;
    }
    // AuthenticateInterceptorServiceMBean ��JavaDoc
    public boolean isSessionInvalidate(){
        return isSessionInvalidate;
    }

    /**
     * �F�؏��I�u�W�F�N�g���i��������{@link AuthenticateStore}�T�[�r�X��ݒ肷��B<p>
     *
     * @param store AuthenticateStore�T�[�r�X
     */
    public void setAuthenticateStore(AuthenticateStore store){
        authenticateStore = store;
    }

    /**
     * �F�؏��I�u�W�F�N�g���i��������{@link AuthenticateStore}�T�[�r�X���擾����B<p>
     *
     * @return AuthenticateStore�T�[�r�X
     */
    public AuthenticateStore getAuthenticateStore(){
        return authenticateStore;
    }

    /**
     * �F�؏��I�u�W�F�N�g���悹��{@link jp.ossc.nimbus.service.context.Context Context}�T�[�r�X��ݒ肷��B<p>
     *
     * @param context Context�T�[�r�X
     */
    public void setThreadContext(Context context){
        threadContext = context;
    }

    /**
     * �F�؏��I�u�W�F�N�g���悹��{@link jp.ossc.nimbus.service.context.Context Context}�T�[�r�X���擾����B<p>
     *
     * @return Context�T�[�r�X
     */
    public Context getThreadContext(){
        return threadContext;
    }

    /**
     * �T�[�r�X�̐����������s���B<p>
     *
     * @exception Exception �T�[�r�X�̐����Ɏ��s�����ꍇ
     */
    public void createService() throws Exception{
        propertyAccess = new PropertyAccess();
        propertyAccess.setIgnoreNullProperty(true);
    }

    /**
     * �T�[�r�X�̊J�n�������s���B<p>
     *
     * @exception Exception �T�[�r�X�̊J�n�Ɏ��s�����ꍇ
     */
    public void startService() throws Exception{
        if(threadContextServiceName != null){
            threadContext = (Context)ServiceManagerFactory
                .getServiceObject(threadContextServiceName);
        }
        if(loginPath == null){
            throw new IllegalArgumentException("LoginPath must be specified.");
        }
        if(authenticatedInfoMapping == null || authenticatedInfoMapping.size() == 0){
            throw new IllegalArgumentException("AuthenticatedInfoMapping must be specified.");
        }
        if(authenticateStoreServiceName != null){
            authenticateStore = (AuthenticateStore)ServiceManagerFactory.getServiceObject(authenticateStoreServiceName);
        }
    }

    /**
     * �T�[�r�X�̔j���������s���B<p>
     *
     * @exception Exception �T�[�r�X�̔j���Ɏ��s�����ꍇ
     */
    public void destroyService() throws Exception{
        propertyAccess = null;
    }

    /**
     * �F�؏��̕ێ��A�F�؏��̌��؁A�F�؏��̍폜���s���B<p>
     * ���O�C���p�X�̏ꍇ�A�㑱�̏������I�������A�F�؃��N�G�X�g����(��������{@link #getAuthenticatedInfoAttributeName()})�ɐݒ肳�ꂽ�F�؏����擾���A�Z�b�V�����̑����Ƃ��Đݒ肷��B<br>
     * ���O�A�E�g�p�X�̏ꍇ�A���̓��N�G�X�g����(��������{@link #getRequestObjectAttributeName()})������̓I�u�W�F�N�g���擾���A�F�؃Z�b�V������������擾�����F�؏��Ƃ̔�r���s���A�F�؂���Ă��邩�ǂ������`�F�b�N����B���̓I�u�W�F�N�g�ƔF�؏��̔�r���ǂ̂悤�ɍs�����́A{@link #setAuthenticatedInfoMapping(Map)}�Őݒ肷��B�F�؏��ƍ��v���Ȃ��ꍇ�́A{@link AuthenticateException}��throw����B���̌�A�㑱�̏������I�������A�F�؏����Z�b�V��������폜����B<br>
     * ��L�ȊO�̃p�X�̏ꍇ�A���̓��N�G�X�g����(��������{@link #getRequestObjectAttributeName()})������̓I�u�W�F�N�g���擾���A�F�؃Z�b�V������������擾�����F�؏��Ƃ̔�r���s���A�F�؂���Ă��邩�ǂ������`�F�b�N����B<br>
     * <p>
     * ��L�ɉ����āA{@link AuthenticateStore}��ݒ肷��ƁA���O�C���p�X�̏ꍇ�A{@link AuthenticateStore#create(HttpServletRequest, Object)}���Ăяo���A�F�؏����X�g�A����B<br>
     * ���O�A�E�g�p�X�̏ꍇ�A�㑱�̏������I�������A{@link AuthenticateStore#destroy(HttpServletRequest, Object)}���Ăяo���A�F�؏����폜����B<br>
     * ��L�ȊO�̃p�X�̏ꍇ�A�F�؃Z�b�V������������F�؏�񂪎擾�ł��Ȃ��ꍇ�A{@link AuthenticateStore#activate(HttpServletRequest, Object)}���Ăяo���A�F�؏��𕜌�����B<br>
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
        if(getState() != STARTED){
            return chain.invokeNext(context);
        }
        final HttpServletRequest request = (HttpServletRequest)context.getServletRequest();
        String reqPath = request.getServletPath();
        if(request.getPathInfo() != null){
            reqPath = reqPath + request.getPathInfo();
        }
        if(loginPath.equals(reqPath)){
            Object ret = chain.invokeNext(context);
            newAuthenticatedInfo(request);
            return ret;
        }else if(logoutPath != null && logoutPath.equals(reqPath)){
            checkAuthenticated(request);
            setupAuthenticatedInfo(request);
            Object ret = chain.invokeNext(context);
            removeAuthenticatedInfo(request);
            return ret;
        }else{
            checkAuthenticated(request);
            setupAuthenticatedInfo(request);
            return chain.invokeNext(context);
        }
    }

    protected void newAuthenticatedInfo(HttpServletRequest request) throws AuthenticateException{
        Object authenticatedInfo = request.getAttribute(authenticatedInfoAttributeName);
        if(authenticatedInfo == null && threadContext != null){
            authenticatedInfo = threadContext.get(authenticatedInfoContextKey);
        }
        if(authenticatedInfo != null){
            HttpSession session = request.getSession(false);
            if(session != null && isSessionInvalidate){
                try{
                    session.invalidate();
                } catch(IllegalStateException e){
                }
                session = null;
            }
            if(session == null){
                session = request.getSession(true);
            }
            session.setAttribute(authenticatedInfoAttributeName, new AuthenticatedInfo(authenticatedInfo, authenticateStoreServiceName));
            if(authenticateStore != null && isStoreCreate){
                authenticateStore.create(request, authenticatedInfo);
            }
        }
    }

    protected void setupAuthenticatedInfo(HttpServletRequest request){
        HttpSession session = request.getSession(false);
        if(session != null){
            AuthenticatedInfo authenticatedInfo = (AuthenticatedInfo)session.getAttribute(authenticatedInfoAttributeName);
            if(authenticatedInfo != null){
                if(request.getAttribute(authenticatedInfoAttributeName) == null){
                    request.setAttribute(authenticatedInfoContextKey, authenticatedInfo.authenticatedInfo);
                }
                if(threadContext != null && !threadContext.containsKey(authenticatedInfoContextKey)){
                    threadContext.put(authenticatedInfoContextKey, authenticatedInfo.authenticatedInfo);
                }
            }
        }
    }

    protected void removeAuthenticatedInfo(HttpServletRequest request) throws AuthenticateException{
        Object requestObject = request.getAttribute(requestObjectAttributeName);
        if(requestObject == null){
            if(threadContext != null){
                requestObject = threadContext.get(requestObjectContextKey);
            }
            if(requestObject == null){
                throw new IllegalAuthenticateException("RequestObject is null.");
            }
        }
        try{
            if(authenticateStore != null && isStoreDestroy){
                authenticateStore.destroy(request, requestObject);
            }
        }finally{
            HttpSession session = request.getSession(false);
            if(session != null){
                session.removeAttribute(authenticatedInfoAttributeName);
            }
        }
    }

    protected void checkAuthenticated(HttpServletRequest request) throws AuthenticateException{
        Object requestObject = request.getAttribute(requestObjectAttributeName);
        if(requestObject == null){
            if(threadContext != null){
                requestObject = threadContext.get(requestObjectContextKey);
            }
            if(requestObject == null){
                throw new IllegalAuthenticateException("RequestObject is null.");
            }
        }
        Object authenticatedInfo = null;
        HttpSession session = request.getSession(false);
        if(session != null){
            authenticatedInfo = session.getAttribute(authenticatedInfoAttributeName);
            if(authenticatedInfo != null && authenticatedInfo instanceof AuthenticatedInfo){
                authenticatedInfo = ((AuthenticatedInfo)authenticatedInfo).authenticatedInfo;
            }
        }
        if(authenticatedInfo == null && authenticateStore != null){
            authenticatedInfo = authenticateStore.activate(request, requestObject);
            if(session == null){
                session = request.getSession(true);
            }
            session.setAttribute(authenticatedInfoAttributeName, new AuthenticatedInfo(authenticatedInfo, authenticateStoreServiceName));
        }
        if(authenticatedInfo == null){
            throw new NoAuthenticateException("AuthenticatedInfo is null.");
        }
        Iterator entries = authenticatedInfoMapping.entrySet().iterator();
        while(entries.hasNext()){
            Map.Entry entry = (Map.Entry)entries.next();
            Object requestValue = null;
            try{
                requestValue = propertyAccess.get(requestObject, (String)entry.getKey());
            }catch(IllegalArgumentException e){
                throw new IllegalAuthenticateException("Authenticated value '" + entry.getKey() + "' cannot acquire from a request.", e);
            }catch(NoSuchPropertyException e){
                throw new IllegalAuthenticateException("Authenticated value '" + entry.getKey() + "' cannot acquire from a request.", e);
            }catch(InvocationTargetException e){
                throw new IllegalAuthenticateException("Authenticated value '" + entry.getKey() + "' cannot acquire from a request.", e.getTargetException());
            }
            if(requestValue == null){
                throw new IllegalAuthenticateException("Authenticated value '" + entry.getKey() + "' cannot acquire from a request. value=null");
            }
            Object authenticatedValue = null;
            try{
                authenticatedValue = propertyAccess.get(authenticatedInfo, (String)entry.getValue());
            }catch(IllegalArgumentException e){
                throw new IllegalAuthenticateException("Authenticated value '" + entry.getValue() + "' cannot acquire from a session.", e);
            }catch(NoSuchPropertyException e){
                throw new IllegalAuthenticateException("Authenticated value '" + entry.getValue() + "' cannot acquire from a session.", e);
            }catch(InvocationTargetException e){
                throw new IllegalAuthenticateException("Authenticated value '" + entry.getValue() + "' cannot acquire from a session.", e.getTargetException());
            }
            if(authenticatedValue == null){
                throw new IllegalAuthenticateException("Authenticated value '" + entry.getValue() + "' cannot acquire from a session. value=null");
            }
            if(!requestValue.equals(authenticatedValue)){
                throw new IllegalAuthenticateException("Authenticated value '" + entry.getKey() + "' and '" + entry.getValue() + "' are not in agreement. requestValue=" + requestValue + ", authenticatedValue=" + authenticatedValue);
            }
        }
    }

    public static class AuthenticatedInfo implements HttpSessionBindingListener, Serializable{

        private static final long serialVersionUID = -5976568672626640653L;

        public Object authenticatedInfo;

        protected ServiceName authenticateStoreServiceName;

        public AuthenticatedInfo(){}

        public AuthenticatedInfo(Object authInfo, ServiceName storeServiceName){
            authenticatedInfo = authInfo;
            authenticateStoreServiceName = storeServiceName;
        }

        public void valueBound(HttpSessionBindingEvent event){
        }

        public void valueUnbound(HttpSessionBindingEvent event){
            if(authenticateStoreServiceName != null){
                AuthenticateStore authenticateStore = (AuthenticateStore)ServiceManagerFactory.getServiceObject(authenticateStoreServiceName);
                authenticateStore.deactivate(event.getSession(), authenticatedInfo);
            }
        }
    }
}