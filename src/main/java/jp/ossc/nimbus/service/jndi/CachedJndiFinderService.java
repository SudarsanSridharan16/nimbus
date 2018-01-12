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
package jp.ossc.nimbus.service.jndi;

import java.io.*;
import java.util.*;

import javax.naming.*;

import jp.ossc.nimbus.core.*;
import jp.ossc.nimbus.daemon.*;
import jp.ossc.nimbus.service.cache.*;
import jp.ossc.nimbus.service.keepalive.*;

/**
 * �����[�g�I�u�W�F�N�g�L���b�V���t��JndiFinder�T�[�r�X�B<p>
 * JNDI��IntialContext�̏������v���p�e�B�𑮐��Ƃ��Đݒ�ł���B<br>
 * �܂��A{@link jp.ossc.nimbus.service.cache.CacheMap �L���b�V���}�b�v}�T�[�r�X�𑮐��Ƃ��Đݒ肷��ƁA����JndiFinder��lookup���������[�g�I�u�W�F�N�g���L���b�V�����鎖���ł���B<br>
 * ����ɁAlookup���̒ʐM�G���[�̃��g���C��AJNDI�T�[�o�̒���I�Ȑ����`�F�b�N�Ȃǂ̋@�\�����B<br>
 * <p>
 * �ȉ��ɁA�T�[�r�X��`��������B<br>
 * <pre>
 * &lt;?xml version="1.0" encoding="Shift_JIS"?&gt;
 * 
 * &lt;server&gt;
 *     
 *     &lt;manager name="Sample"&gt;
 *         
 *         &lt;service name="JndiFinder"
 *                  code="jp.ossc.nimbus.service.jndi.CachedJndiFinderService"&gt;
 *             &lt;attribute name="Environment"&gt;
 *                 java.naming.factory.initial=org.jnp.interfaces.NamingContextFactory
 *                 java.naming.factory.url.pkgs=org.jboss.naming:org.jnp.interfaces
 *                 java.naming.provider.url=localhost
 *             &lt;/attribute&gt;
 *         &lt;/service&gt;
 *         
 *     &lt;/manager&gt;
 *     
 * &lt;/server&gt;
 * </pre>
 * 
 * @author Y.Tokuda
 * @see CacheMap
 */
public class CachedJndiFinderService extends ServiceBase
 implements JndiFinder, DaemonRunnable, KeepAliveChecker,
            CachedJndiFinderServiceMBean {
    
    private static final long serialVersionUID = 2361330897642105726L;
    
    /**
     * JNDI Lookup���̐ړ����̃f�t�H���g�l�B<p>
     */
    private static final String C_NONE = ""; //$NON-NLS-1$
    
    /**
     * JNDI�T�[�o�����m�F�p��JNDI���B<p>
     */
    private static final String ROOT_CONTEXT = "/";
    
    /**
     * JNDI IntialContext���v���p�e�B�B<p>
     */
    private Properties contextEnv;
    
    /**
     * JNDI�R���e�L�X�g�B<p>
     */
    private InitialContext initialCtx;
    
    /**
     * �����[�g�I�u�W�F�N�g�L���b�V���T�[�r�X���B<p>
     */
    private ServiceName remoteObjCacheServiceName;
    
    /**
     * �����[�g�I�u�W�F�N�g�L���b�V���T�[�r�X�B<p>
     */
    private CacheMap remoteObjCache;
    
    /**
     * JNDI�v���t�B�N�X�B<p>
     * �f�t�H���g�͋󕶎��B<br>
     */
    private String jndiPrefix = C_NONE;
    
    /**
     * lookup�G���[���̃��g���C�񐔁B<p>
     * �f�t�H���g�́A���g���C�Ȃ��B<br>
     */
    private int lookupRetryCount = 0;
    
    /**
     * lookup�G���[���̃��g���C�Ԋu [msec]�B<p>
     * �f�t�H���g�́A1�b�B<br>
     */
    private long retryInterval = 1000;
    
    /**
     * ���g���C�Ώۂ̗�O�N���X���z��B<p>
     */
    private String[] retryExceptionClassNames = DEFAULT_RETRY_EXCXEPTION_NAME;
    
    /**
     * ���g���C�Ώۂ̗�O�N���X�z��B<p>
     */
    private Class[] retryExceptionClasses;
    
    /**
     * JNDI�T�[�o�̐����m�F�����邩�ǂ����̃t���O�B<p>
     */
    private boolean isAliveCheckJNDIServer;
    
    /**
     * JNDI�T�[�o�̐������Ă��邩�ǂ����̃t���O�B<p>
     */
    private boolean isAliveJNDIServer;
    
    /**
     * JNDI�T�[�o�̐����m�F������Ԋu[msec]�B<p>
     */
    private long aliveCheckJNDIServerInterval = 60000;
    
    /**
     * {@link Daemon}�I�u�W�F�N�g�B<p>
     */
    private Daemon daemon;
    
    private boolean isLoggingDeadJNDIServer = true;
    
    private boolean isLoggingRecoverJNDIServer = true;
    
    private String deadJNDIServerLogMessageId = JNDI_SERVER_DEAD_MSG_ID;
    
    private String recoverJNDIServerLogMessageId = JNDI_SERVER_RECOVER_MSG_ID;
    
    private List keepAliveListeners;
    
    /**
     * �T�[�r�X�̐����������s���B<p>
     *
     * @exception Exception �T�[�r�X�̐��������Ɏ��s�����ꍇ
     */
    public void createService() throws Exception{
        daemon = new Daemon(this);
        daemon.setName("Nimbus JndiCheckDaemon " + getServiceNameObject());
        keepAliveListeners = new ArrayList();
    }
    
    /**
     * CacheMap��ݒ肷��B
     */
    public void setCacheMap(CacheMap remoteObjCache) {
        this.remoteObjCache = remoteObjCache;
    }

    /**
     * �T�[�r�X�̊J�n�������s���B<p>
     *
     * @exception Exception �T�[�r�X�̊J�n�����Ɏ��s�����ꍇ
     */
    public void startService() throws Exception{
        
        if(retryExceptionClassNames != null
             && retryExceptionClassNames.length != 0){
            final ClassLoader loader = NimbusClassLoader.getInstance();
            retryExceptionClasses = new Class[retryExceptionClassNames.length];
            for(int i = 0; i < retryExceptionClassNames.length; i++){
                retryExceptionClasses[i] = Class.forName(
                    retryExceptionClassNames[i],
                    true,
                    loader
                );
            }
        }
        
        //�L���b�V���T�[�r�X�̎擾
        if(remoteObjCacheServiceName != null){
            remoteObjCache = (CacheMap)ServiceManagerFactory
                .getServiceObject(remoteObjCacheServiceName);
        }
        
        if(contextEnv == null){
            initialCtx = new InitialContext();
        }else{
            initialCtx = new InitialContext(contextEnv);
        }
        
        isAliveJNDIServer = true;
        
        if(isAliveCheckJNDIServer){
            // �f�[�����N��
            daemon.start();
        }
    }
    
    /**
     * �T�[�r�X�̒�~�������s���B<p>
     *
     * @exception Exception �T�[�r�X�̒�~�����Ɏ��s�����ꍇ
     */
    public void stopService() throws Exception{
        
        // �f�[������~
        daemon.stop();
        
        initialCtx.close();
    }
    
    /**
     * �T�[�r�X�̔j���������s���B<p>
     *
     * @exception Exception �T�[�r�X�̔j�������Ɏ��s�����ꍇ
     */
    public void destory() throws Exception{
        initialCtx = null;
        remoteObjCache = null;
        contextEnv = null;
        remoteObjCacheServiceName = null;
        retryExceptionClasses = null;
        daemon = null;
        keepAliveListeners = null;
    }
    
    // JndiFinder��JavaDoc
    public Object lookup() throws NamingException{
        return lookup(C_NONE);
    }
    
    private boolean isRetryException(NamingException e){
        if(retryExceptionClasses != null && retryExceptionClasses.length != 0){
            for(int i = 0; i < retryExceptionClasses.length; i++){
                if(retryExceptionClasses[i].isInstance(e)){
                    return true;
                }
            }
        }
        return false;
    }
    
    // JndiFinder��JavaDoc
    public Object lookup(String name) throws NamingException{
        Object result = null;
        String key = jndiPrefix + name;
        
        //�L���b�V���T�[�r�X���ݒ肳��Ă���Ȃ�A�L���b�V���T�[�r�X����T��
        if(remoteObjCache != null){
            result = remoteObjCache.get(key);
            if(result != null){
                return result;
            }
        }
        
        result = lookupInternal(key);
        
        // lookup�ŃR���e�L�X�g���擾�ł��A���L���b�V�����[�h�ł���΁A
        // �擾����Context���L���b�V��
        if(result != null && remoteObjCache != null){
            remoteObjCache.put(key, result);
        }
        
        return result;
    }
    
    private Object lookupInternal(String key) throws NamingException{
        Object result = null;
        
        try{
            result = initialCtx.lookup(key);
        }catch(NamingException e){
            //���Ԃ������ă��g���C����B
            if(lookupRetryCount <= 0 || !isRetryException(e)){
                throw e;
            }
        }
        
        if(result == null){
            for(int rcont = 0; rcont < lookupRetryCount; rcont++){
                //���g���C����sleep
                try{
                    Thread.sleep(retryInterval);
                }catch(InterruptedException e){}
                
                try{
                    result = initialCtx.lookup(key);
                    break;
                }catch(NamingException e){
                    //���Ԃ������ă��g���C����B
                    if(rcont == lookupRetryCount - 1
                         || !isRetryException(e)){
                        throw e;
                    }
                }
            }
        }
        
        return result; 
    }
    
    // KeepAliveChecker��JavaDoc
    public void addKeepAliveListener(KeepAliveListener listener){
        synchronized(keepAliveListeners){
            keepAliveListeners.add(listener);
        }
    }
    
    // KeepAliveChecker��JavaDoc
    public void removeKeepAliveListener(KeepAliveListener listener){
        synchronized(keepAliveListeners){
            keepAliveListeners.remove(listener);
        }
    }
    
    // KeepAliveChecker��JavaDoc
    public void clearKeepAliveListener(){
        synchronized(keepAliveListeners){
            keepAliveListeners.clear();
        }
    }
    
    /**
     * �f�[�������J�n�������ɌĂяo�����B<p>
     * 
     * @return ���true��Ԃ�
     */
    public boolean onStart(){
        return true;
    }
    
    /**
     * �f�[��������~�������ɌĂяo�����B<p>
     * 
     * @return ���true��Ԃ�
     */
    public boolean onStop(){
        return true;
    }
    
    /**
     * �f�[���������f�������ɌĂяo�����B<p>
     * 
     * @return ���true��Ԃ�
     */
    public boolean onSuspend(){
        return true;
    }
    
    /**
     * �f�[�������ĊJ�������ɌĂяo�����B<p>
     * 
     * @return ���true��Ԃ�
     */
    public boolean onResume(){
        return true;
    }
    
    /**
     * ��莞��sleep��Ƀ��[�g�R���e�L�X�g��lookup���ĕԂ��B<p>
     * 
     * @param ctrl DaemonControl�I�u�W�F�N�g
     * @return ���[�g�R���e�L�X�g�I�u�W�F�N�g�܂���NamingException
     */
    public Object provide(DaemonControl ctrl){
        try{
            ctrl.sleep(aliveCheckJNDIServerInterval, true);
        }catch(InterruptedException e){
            Thread.interrupted();
        }
        if(isAliveCheckJNDIServer){
            try{
                return lookupInternal(ROOT_CONTEXT);
            }catch(NamingException e){
                return e;
            }
        }else{
            return null;
        }
    }
    
    /**
     * ����lookupedObj�œn���ꂽ�I�u�W�F�N�g�������B<p>
     * isAliveJNDIServer��true�̏�ԂŁA����lookupedObj�œn���ꂽ�I�u�W�F�N�g��NamingException�̏ꍇ�AJNDI�T�[�o�����񂾎|�̃G���[���O���o�͂���B<br>
     * isAliveJNDIServer��false�̏�ԂŁA����lookupedObj�œn���ꂽ�I�u�W�F�N�g��NamingException�łȂ��ꍇ�AJNDI�T�[�o�����A�����|�̒ʒm���O���o�͂���B<br>
     *
     * @param lookupedObj ���[�g�R���e�L�X�g�I�u�W�F�N�g
     * @param ctrl DaemonControl�I�u�W�F�N�g
     */
    public void consume(Object lookupedObj, DaemonControl ctrl){
        if(!isAliveCheckJNDIServer){
            return;
        }
        if(isAliveJNDIServer){
            if(lookupedObj instanceof NamingException){
                isAliveJNDIServer = false;
                clearCache();
                synchronized(keepAliveListeners){
                    final Iterator itr = keepAliveListeners.iterator();
                    while(itr.hasNext()){
                        final KeepAliveListener keepAliveListener
                             = (KeepAliveListener)itr.next();
                        keepAliveListener.onDead(this);
                    }
                }
                // �G���[���O�o��
                if(isLoggingDeadJNDIServer){
                    getLogger().write(
                        deadJNDIServerLogMessageId,
                        getJNDIServerInfo(),
                        (NamingException)lookupedObj
                    );
                }
            }
        }else{
            if(!(lookupedObj instanceof NamingException)){
                isAliveJNDIServer = true;
                synchronized(keepAliveListeners){
                    final Iterator itr = keepAliveListeners.iterator();
                    while(itr.hasNext()){
                        final KeepAliveListener keepAliveListener
                             = (KeepAliveListener)itr.next();
                        keepAliveListener.onRecover(this);
                    }
                }
                if(isLoggingRecoverJNDIServer){
                    // �ʒm���O�o��
                    getLogger().write(
                        recoverJNDIServerLogMessageId,
                        getJNDIServerInfo()
                    );
                }
            }
        }
    }
    
    private Object getJNDIServerInfo(){
        Object result = null;
        try{
            result = getEnvironment().get("java.naming.provider.url");
        }catch(NamingException e){
        }
        if(result == null){
            result = "localhost";
        }
        return result;
    }
    
    /**
     * �������Ȃ��B<p>
     */
    public void garbage(){
    }
    
    // CachedJndiFinderServiceMBean��JavaDoc
    public void setEnvironment(Properties prop){
        contextEnv = prop;
    }
    
    // CachedJndiFinderServiceMBean��JavaDoc
    public Properties getEnvironment() throws NamingException{
        if(contextEnv != null){
            return contextEnv;
        }else if(initialCtx != null){
            final Properties prop = new Properties();
            prop.putAll(initialCtx.getEnvironment());
            return prop;
        }
        return null;
    }
    
    // CachedJndiFinderServiceMBean��JavaDoc
    public void setCacheMapServiceName(ServiceName name){
        remoteObjCacheServiceName = name;
    }
    
    // CachedJndiFinderServiceMBean��JavaDoc
    public ServiceName getCacheMapServiceName(){
        return remoteObjCacheServiceName;
    }
    
    // CachedJndiFinderServiceMBean��JavaDoc
    public void setPrefix(String prefix){
        jndiPrefix = prefix;
    }
    
    // CachedJndiFinderServiceMBean��JavaDoc
    public String getPrefix(){
        return jndiPrefix;
    }
    
    // CachedJndiFinderServiceMBean��JavaDoc
    public void setRetryCount(int num){
        lookupRetryCount = num;
    }
    
    // CachedJndiFinderServiceMBean��JavaDoc
    public int getRetryCount(){
        return lookupRetryCount;
    }
    
    // CachedJndiFinderServiceMBean��JavaDoc
    public void setRetryInterval(long interval){
        retryInterval = interval;
    }
    
    // CachedJndiFinderServiceMBean��JavaDoc
    public long getRetryInterval(){
        return retryInterval;
    }
    
    // CachedJndiFinderServiceMBean��JavaDoc
    public void setRetryExceptionClassNames(String[] classNames){
        retryExceptionClassNames = classNames;
    }
    
    // CachedJndiFinderServiceMBean��JavaDoc
    public String[] getRetryExceptionClassNames(){
        return retryExceptionClassNames;
    }
    
    // CachedJndiFinderServiceMBean��JavaDoc
    public void setAliveCheckJNDIServer(boolean isCheck){
        isAliveCheckJNDIServer = isCheck;
        if(isCheck && getState() == STARTED && !daemon.isRunning()){
            daemon.start();
        }
    }
    
    // CachedJndiFinderServiceMBean��JavaDoc
    public boolean isAliveCheckJNDIServer(){
        return isAliveCheckJNDIServer;
    }
    
    // CachedJndiFinderServiceMBean��JavaDoc
    public void setAliveCheckJNDIServerInterval(long interval){
        aliveCheckJNDIServerInterval = interval;
    }
    
    // CachedJndiFinderServiceMBean��JavaDoc
    public long getAliveCheckJNDIServerInterval(){
        return aliveCheckJNDIServerInterval;
    }
    
    // CachedJndiFinderServiceMBean��JavaDoc
    public boolean isAliveJNDIServer(){
        if(getState() != STARTED){
            return false;
        }else if(isAliveCheckJNDIServer){
            return isAliveJNDIServer;
        }else{
            try{
                lookupInternal(ROOT_CONTEXT);
                return true;
            }catch(NamingException e){
                return false;
            }
        }
    }
    
    // KeepAliveChecker��JavaDoc
    public boolean isAlive(){
        return isAliveJNDIServer();
    }
    
    // KeepAliveCheckInvoker��JavaDoc
    public Object getHostInfo() {
        try{
            return initialCtx == null || initialCtx.getEnvironment() == null ? null : initialCtx.getEnvironment().get(Context.PROVIDER_URL);
        }catch(javax.naming.NamingException e){
            return null;
        }
    }
    
    // CachedJndiFinderServiceMBean��JavaDoc
    public void setLoggingDeadJNDIServer(boolean isOutput){
        isLoggingDeadJNDIServer = isOutput;
    }
    
    // CachedJndiFinderServiceMBean��JavaDoc
    public boolean isLoggingDeadJNDIServer(){
        return isLoggingDeadJNDIServer;
    }
    
    // CachedJndiFinderServiceMBean��JavaDoc
    public void setLoggingRecoverJNDIServer(boolean isOutput){
        isLoggingRecoverJNDIServer = isOutput;
    }
    
    // CachedJndiFinderServiceMBean��JavaDoc
    public boolean isLoggingRecoverJNDIServer(){
        return isLoggingRecoverJNDIServer;
    }
    
    // CachedJndiFinderServiceMBean��JavaDoc
    public void setDeadJNDIServerLogMessageId(String id){
        deadJNDIServerLogMessageId = id;
    }
    
    // CachedJndiFinderServiceMBean��JavaDoc
    public String getDeadJNDIServerLogMessageId(){
        return deadJNDIServerLogMessageId;
    }
    
    // CachedJndiFinderServiceMBean��JavaDoc
    public void setRecoverJNDIServerLogMessageId(String id){
        recoverJNDIServerLogMessageId = id;
    }
    
    // CachedJndiFinderServiceMBean��JavaDoc
    public String getRecoverJNDIServerLogMessageId(){
        return recoverJNDIServerLogMessageId;
    }
    
    // CachedJndiFinderServiceMBean��JavaDoc
    public void clearCache(){
        if(remoteObjCache != null){
            remoteObjCache.clear();
        }
    }
    
    // CachedJndiFinderServiceMBean��JavaDoc
    public void clearCache(String name){
        if(remoteObjCache != null){
            remoteObjCache.remove(name);
        }
    }
    
    // CachedJndiFinderServiceMBean��JavaDoc
    public String listContext() throws NamingException{
        if(initialCtx == null){
            return null;
        }
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        pw.println("<pre>");
        listContext(pw, initialCtx, "�@");
        pw.println("</pre>");
        return sw.toString();
    }
    
    private void listContext(PrintWriter pw, Context context, String indent) throws NamingException{
        NamingEnumeration list = context.listBindings("");
        while(list.hasMore()){
            Binding item = (Binding)list.next();
            String className = item.getClassName();
            String name = item.getName();
            pw.println(indent + className + "�@" + name);
            Object o = item.getObject();
            if(o instanceof javax.naming.Context){
                listContext(pw, (Context)o, indent + "�@");
            }
        }
    }
}
