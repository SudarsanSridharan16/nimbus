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
package jp.ossc.nimbus.service.log;

import java.util.*;

import jp.ossc.nimbus.core.*;
import jp.ossc.nimbus.beans.ServiceNameEditor;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.logging.LogConfigurationException;

/**
 * Nimbus�p��Jakarta Commons Logging��LogFactory�g���N���X�B<p>
 * �v���p�e�B�t�@�C��"commons-logging.properties"�ɁA
 * <pre>
 * org.apache.commons.logging.LogFactory=jp.ossc.nimbus.service.log.NimbusLogFactory
 * </pre>
 * ���w�肷�鎖�ŁA{@link LogFactory}�̎����N���X�Ƃ��āA���̃N���X���g�p�\�ɂȂ�B�܂��A�V�X�e���v���p�e�B�ł����l�̎w�肪�\�ł���B<br>
 * <p>
 * ���̃��O�t�@�N�g���́A{@link CommonsLogFactory}�C���^�t�F�[�X�����������T�[�r�X���g�p����{@link Log}�C���X�^���X�𐶐�����B<br>
 * ���̂��߁ACommonsLogFactory�C���^�t�F�[�X�����������T�[�r�X���`���āA���̒�`�����[�h���Ă����K�v������BCommonsLogFactory�C���^�t�F�[�X�����������T�[�r�X�����[�h����Ă��Ȃ��ꍇ�A�܂��́A�N������Ă��Ȃ��ꍇ�́A�f�t�H���g��{@link LogFactory}���g�p����Log�C���X�^���X�𐶐�����B<br>
 * �f�t�H���g��LogFactory�́Aorg.apache.commons.logging.impl.LogFactoryImpl���g�p����B�A���A�f�t�H���g��LogFactory��ύX���鎖���\�ŁA�v���p�e�B�t�@�C��"commons-logging.properties"�ɁA
 * <pre>
 * jp.ossc.nimbus.service.log.NimbusLogFactory.DefaultLogFactory=org.apache.commons.logging.impl.Log4jFactory
 * </pre>
 * �̂悤�Ɏw�肷�鎖�ŁA�ύX�ł���B�܂��A�V�X�e���v���p�e�B�ł����l�̎w�肪�\�ł���B<br>
 * <p>
 * {@link CommonsLogFactory}�C���^�t�F�[�X�����������T�[�r�X�Ƃ��āA{@link DefaultCommonsLogFactoryService}���񋟂���Ă���B<br>
 * DefaultCommonsLogFactoryService�́A�N������{@link LogFactory#getFactory()}���Ăяo���āA���̃t�@�N�g���̃C���X�^���X���擾���A{@link #setCommonsLogFactory(CommonsLogFactory)}�Ŏ������g�����̃t�@�N�g���ɐݒ肷��B���̂��߁A�O�q����"org.apache.commons.logging.LogFactory"�v���p�e�B�̐ݒ�ƁADefaultCommonsLogFactoryService�̃T�[�r�X��`�݂̂ŁA�g�p�\�ł���B<br>
 * �A���ALogFactory.getFactory()�Ŏ擾�ł���LogFactory�C���X�^���X�́A�Ăяo���X���b�h�Ɋ֘A�t����ꂽ�N���X���[�_�P�ʂŎ擾�����B���̂��߁ADefaultCommonsLogFactoryService�̃��[�h���s���X���b�h�Ɋ֘A�t����ꂽ�N���X���[�_�ƁA{@link LogFactory#getLog(String)}���Ăяo���X���b�h�Ɋ֘A�t����ꂽ�N���X���[�_���قȂ�ꍇ�́A��L�̐ݒ�݂̂ł́A���̃t�@�N�g����DefaultCommonsLogFactoryService�̎Q�Ƃ𓾂鎖���ł��Ȃ��B<br>
 * <p>
 * {@link LogFactory#getLog(Class)}�A{@link LogFactory#getLog(String)}���Ăяo���X���b�h�Ɋ֘A�t����ꂽ�N���X���[�_�ƁACommonsLogFactory�̎����T�[�r�X�����[�h�����X���b�h�Ɋ֘A�t����ꂽ�N���X���[�_���قȂ�ꍇ�́A�v���p�e�B�t�@�C��"commons-logging.properties"�ɁA
 * <pre>
 * jp.ossc.nimbus.service.log.NimbusLogFactory.CommonsLogFactoryName=Nimbus#CommonsLog
 * </pre>
 * �̂悤�ɁACommonsLogFactory�̎����T�[�r�X�̃T�[�r�X�����w�肷��K�v������B<br>
 * 
 * @author M.Takata
 * @see CommonsLogFactory
 */
public class NimbusLogFactory extends LogFactory implements java.io.Serializable{
    
    private static final long serialVersionUID = -3343921992875545571L;
    
    /**
     * {@link CommonsLogFactory}���ݒ肳��Ă��Ȃ����Ɏg�p����{@link LogFactory}�̎����N���X�����w�肷��v���p�e�B���B<p>
     * �v���p�e�B�t�@�C��"commons-logging.properties"�ɁA���̃v���p�e�B���w�肷��B�܂��́A�V�X�e���v���p�e�B�Ŏw�肷��B<br>
     */
    public static final String DEFAULT_FACTORY_PROPERTY =
        "jp.ossc.nimbus.service.log.NimbusLogFactory.DefaultLogFactory";
    
    /**
     * {@link CommonsLogFactory}�̃T�[�r�X�����w�肷��v���p�e�B���B<p>
     * �v���p�e�B�t�@�C��"commons-logging.properties"�ɁA���̃v���p�e�B���w�肷��B�܂��́A�V�X�e���v���p�e�B�Ŏw�肷��B<br>
     * {@link LogFactory#getLog(Class)}�A{@link LogFactory#getLog(String)}���Ăяo�����X���b�h�̃N���X���[�_�ƁACommonsLogFactory�̎����T�[�r�X�����[�h�����X���b�h�̃N���X���[�_���قȂ�ꍇ�́A���̃v���p�e�B���w�肷��K�v������B���҂̃N���X���[�_���������ꍇ�́A���̃v���p�e�B���w�肷��K�v�͂Ȃ��B<br>
     */
    public static final String FACTORY_NAME_PROPERTY =
        "jp.ossc.nimbus.service.log.NimbusLogFactory.CommonsLogFactoryName";
    
    /**
     * {@link #DEFAULT_FACTORY_PROPERTY}�̎w�肪�Ȃ��ꍇ�ɁA���������{@link LogFactory}�̎����N���X���B<p>
     */
    public static final String DEFAULT_FACTORY_DEFAULT =
        "org.apache.commons.logging.impl.LogFactoryImpl";
    
    /**
     * {@link Log}�𐶐�����{@link CommonsLogFactory}�B<p>
     */
    private CommonsLogFactory logFactory;
    
    /**
     * �f�t�H���g��{@link LogFactory}�B<p>
     */
    private LogFactory deafultLogFactory;
    
    /**
     * �����Ǘ��}�b�v�B<p>
     * <table border="1">
     *   <tr bgcolor="#CCCCFF"><th colspan="2">�L�[</th><th colspan="2">�l</th></tr>
     *   <tr bgcolor="#CCCCFF"><th>�^</th><th>���e</th><th>�^</th><th>���e</th></tr>
     *   <tr><td>Object</td><td>������</td><td>Object</td><td>�����l</td></tr>
     * </table>
     */
    private Map attributes = new HashMap();
    
    /**
     * {@link Log}�C���X�^���X�Ǘ��}�b�v�B<p>
     * <table border="1">
     *   <tr bgcolor="#CCCCFF"><th colspan="2">�L�[</th><th colspan="2">�l</th></tr>
     *   <tr bgcolor="#CCCCFF"><th>�^</th><th>���e</th><th>�^</th><th>���e</th></tr>
     *   <tr><td>Object</td><td>Log�C���X�^���X���ʏ��</td><td>Log</td><td>Log�C���X�^���X</td></tr>
     * </table>
     */
    private Map logInstances = new HashMap();
    
    /**
     * {@link CommonsLogFactory}��ݒ肷��B<p>
     *
     * @param factory CommonsLogFactory�I�u�W�F�N�g
     */
    public void setCommonsLogFactory(CommonsLogFactory factory){
        if(logFactory != null && logFactory == factory){
            return;
        }
        logFactory = factory;
        if(factory != null){
            final String[] names = getAttributeNames();
            for(int i = 0; i < names.length; i++){
                final String name = names[i];
                factory.setAttribute(name, getAttribute(name));
            }
        }
        final Iterator logKeys = logInstances.keySet().iterator();
        while(logKeys.hasNext()){
            final Object key = logKeys.next();
            final LogWrapper log = (LogWrapper)logInstances.get(key);
            if(factory != null){
                if(key instanceof Class){
                    log.setRealLog(factory.getInstance((Class)key));
                }else{
                    log.setRealLog(factory.getInstance((String)key));
                }
                log.real();
            }else{
                log.dummy();
            }
        }
    }
    
    /**
     * �w�肳�ꂽ�L�[�ɑΉ�����Log�C���X�^���X���擾����B<p>
     * 
     * @param key �L�[���
     * @return Log�C���X�^���X
     */
    private Log getInstance(final Object key){
        if(logInstances.containsKey(key)){
            return (Log)logInstances.get(key);
        }
        
        if(deafultLogFactory == null){
            deafultLogFactory = createDefaultLogFactory();
        }
        LogWrapper log = null;
        if(logFactory != null){
            if(key instanceof Class){
                log = new LogWrapper(
                    logFactory.getInstance((Class)key),
                    deafultLogFactory.getInstance((Class)key)
                );
            }else{
                log = new LogWrapper(
                    logFactory.getInstance((String)key),
                    deafultLogFactory.getInstance((String)key)
                );
            }
            logInstances.put(key, log);
            return log;
        }
        
        String factoryName
             = System.getProperty(FACTORY_NAME_PROPERTY);
        if(factoryName == null){
            factoryName = (String)getAttribute(FACTORY_NAME_PROPERTY);
        }
        if(factoryName == null){
            if(key instanceof Class){
                log = new LogWrapper(
                    deafultLogFactory.getInstance((Class)key)
                );
            }else{
                log = new LogWrapper(
                    deafultLogFactory.getInstance((String)key)
                );
            }
            logInstances.put(key, log);
            return log;
        }
        
        final ServiceNameEditor editor = new ServiceNameEditor();
        editor.setAsText(factoryName);
        final ServiceName name = (ServiceName)editor.getValue();
        LogWrapper tmpLog = null;
        if(key instanceof Class){
            tmpLog = new LogWrapper(deafultLogFactory.getInstance((Class)key));
        }else{
            tmpLog = new LogWrapper(deafultLogFactory.getInstance((String)key));
        }
        log = tmpLog;
        logInstances.put(key, log);
        final String managerName = name.getServiceManagerName();
        final String serviceName = name.getServiceName();
        if(!ServiceManagerFactory.isRegisteredManager(managerName)){
            waitRegistrationManager(managerName, serviceName);
            return log;
        }
        final ServiceManager manager
             = ServiceManagerFactory.findManager(managerName);
        if(!manager.isRegisteredService(serviceName)){
            waitRegistrationService(manager, serviceName);
            return log;
        }
        final Service service = manager.getService(serviceName);
        waitStartService(service);
        return log;
    }
    
    /**
     * CommonsLogFactory�T�[�r�X���o�^�����ServiceManager���AServiceManagerFactory�ɓo�^�����̂�ҋ@���āACommonsLogFactory�T�[�r�X��ݒ肷��B<p>
     * �T�[�r�X��targetMng��ServiceManager��ServiceManagerFactory�ɓo�^�����ƁA�T�[�r�X��targetService�̃T�[�r�X���擾���Ă݂�B�擾�ł��Ȃ��ꍇ�́A{@link #waitRegistrationService(ServiceManager, String)}���Ăяo���āA�T�[�r�X���o�^�����̂�ҋ@����B�擾�ł���ꍇ�́A{@link #waitStartService(Service)}���Ăяo���āA�T�[�r�X���J�n�����̂�ҋ@����B<br>
     *
     * @param targetMng CommonsLogFactory�T�[�r�X���o�^�����ServiceManager�̃T�[�r�X��
     * @param targetService CommonsLogFactory�̃T�[�r�X��
     */
    private void waitRegistrationManager(
        final String targetMng,
        final String targetService
    ){
        ServiceManagerFactory.addRegistrationListener(
            new RegistrationListener(){
                public void registered(RegistrationEvent e){
                    final ServiceManager manager
                         = (ServiceManager)e.getRegistration();
                    if(!manager.getServiceName().equals(targetMng)){
                        return;
                    }
                    ServiceManagerFactory.removeRegistrationListener(this);
                    Service service = null;
                    try{
                        service = manager.getService(targetService);
                    }catch(ServiceNotFoundException ex){
                        waitRegistrationService(
                            manager,
                            targetService
                        );
                    }
                    if(service != null){
                        waitStartService(service);
                    }
                }
                public void unregistered(RegistrationEvent e){
                }
            }
        );
    }
    
    /**
     * CommonsLogFactory�T�[�r�X��ServiceManager�ɓo�^�����̂�ҋ@���āACommonsLogFactory�T�[�r�X��ݒ肷��B<p>
     * �T�[�r�X��targetService��CommonsLogFactory��ServiceManager�ɓo�^�����ƁA{@link #waitStartService(Service)}���Ăяo���āA�T�[�r�X���J�n�����̂�ҋ@����B<br>
     *
     * @param targetMng CommonsLogFactory�T�[�r�X���o�^�����ServiceManager
     * @param targetService CommonsLogFactory�̃T�[�r�X��
     * @param log LogWrapper�I�u�W�F�N�g
     */
    private void waitRegistrationService(
        final ServiceManager targetMng,
        final String targetService
    ){
        targetMng.addRegistrationListener(
            new RegistrationListener(){
                public void registered(RegistrationEvent e){
                    final Service service
                         = (Service)e.getRegistration();
                    if(!service.getServiceName().equals(targetService)){
                        return;
                    }
                    targetMng.removeRegistrationListener(this);
                    waitStartService(service);
                }
                public void unregistered(RegistrationEvent e){
                }
            }
        );
    }
    
    /**
     * CommonsLogFactory�T�[�r�X���J�n�����̂�ҋ@���āACommonsLogFactory�T�[�r�X��ݒ肷��B<p>
     * �T�[�r�Xservice���J�n�����ƁA{@link #setCommonsLogFactory(CommonsLogFactory)}���Ăяo���āACommonsLogFactory�̎Q�Ƃ�ݒ肷��B<br>
     * �܂��A�T�[�r�Xservice����~�����ƁA{@link #setCommonsLogFactory(CommonsLogFactory)}���Ăяo���āACommonsLogFactory�̎Q�Ƃ�j������B<br>
     *
     * @param targetService CommonsLogFactory�T�[�r�X
     */
    private void waitStartService(final Service service){
        Service targetService = null;
        if(!(service instanceof ServiceStateBroadcaster)){
            final String managerName = service.getServiceManagerName();
            final ServiceManager mng = ServiceManagerFactory.findManager(
                managerName
            );
            targetService = mng;
        }else{
            targetService = service;
        }
        final ServiceStateBroadcaster broad = ServiceManagerFactory
            .getServiceStateBroadcaster(
                targetService.getServiceManagerName(),
                targetService.getServiceName()
            );
        if(broad != null){
            broad.addServiceStateListener(
                new ServiceStateListener(){
                    public void stateChanged(ServiceStateChangeEvent e)
                     throws Exception{
                        CommonsLogFactory factory = null;
                        switch(service.getState()){
                        case Service.STARTED:
                            try{
                                factory = (CommonsLogFactory)
                                    ServiceManagerFactory.getServiceObject(
                                        service.getServiceManagerName(),
                                        service.getServiceName()
                                );
                            }catch(ServiceNotFoundException ex){
                                factory = null;
                            }
                            break;
                        case Service.STOPPED:
                            factory = null;
                            break;
                        case Service.DESTROYED:
                            broad.removeServiceStateListener(this);
                            factory = null;
                            break;
                        default:
                        }
                        setCommonsLogFactory(factory);
                    }
                    public boolean isEnabledState(int state){
                        return state == Service.STARTED
                             || state == Service.STOPPED
                             || state == Service.DESTROYED;
                    }
                }
            );
        }
        if(service.getState() == Service.STARTED){
            CommonsLogFactory factory = null;
            try{
                factory = (CommonsLogFactory)ServiceManagerFactory.getServiceObject(
                    service.getServiceManagerName(),
                    service.getServiceName()
                );
            }catch(ServiceNotFoundException ex){
                // �N���蓾�Ȃ�
            }
            setCommonsLogFactory(factory);
        }
    }
    
    /**
     * �f�t�H���g��LogFactory�𐶐�����B<p>
     *
     * @return �f�t�H���g��LogFactory
     */
    private LogFactory createDefaultLogFactory()
     throws LogConfigurationException {
        LogFactory factory = null;
        try{
            final String factoryClass
                 = System.getProperty(DEFAULT_FACTORY_PROPERTY);
            if(factoryClass != null){
                final ClassLoader classLoader
                     = Thread.currentThread().getContextClassLoader();
                factory = newFactory(factoryClass, classLoader);
            }
        }catch(SecurityException e){
            factory = null;
        }
        
        if(factory == null){
            final String factoryClass = (String)getAttribute(DEFAULT_FACTORY_PROPERTY);
            if(factoryClass != null){
                final ClassLoader classLoader
                     = Thread.currentThread().getContextClassLoader();
                factory = newFactory(factoryClass, classLoader);
            }
        }
        
        if(factory == null){
            final ClassLoader classLoader
                 = Thread.currentThread().getContextClassLoader();
            factory = newFactory(DEFAULT_FACTORY_DEFAULT, classLoader);
        }
        
        if(factory != null){
            final String[] names = getAttributeNames();
            for(int i = 0; i < names.length; i++){
                final String name = names[i];
                factory.setAttribute(name, getAttribute(name));
            }
        }
        
        return factory;
    }
    
    /**
     * �����Ŏw�肵���N���X�I�u�W�F�N�g�Ɋ֘A�t����{@link Log}�C���X�^���X���擾����B<p>
     *
     * @param clazz �擾����Log�C���X�^���X�����ʂ���L�[�ƂȂ�N���X�I�u�W�F�N�g
     * @return �����Ŏw�肵���N���X�I�u�W�F�N�g�Ɋ֘A�t����{@link Log}�C���X�^���X
     * @exception LogConfigurationException Log�C���X�^���X�̍쐬�Ɏ��s�����ꍇ
     */
    public Log getInstance(Class clazz) throws LogConfigurationException{
        return getInstance((Object)clazz);
    }
    
    /**
     * �����Ŏw�肵�����O�Ɋ֘A�t����{@link Log}�C���X�^���X���擾����B<p>
     *
     * @param name �擾����Log�C���X�^���X�����ʂ��閼�O
     * @return �����Ŏw�肵�����O�Ɋ֘A�t����{@link Log}�C���X�^���X
     * @exception LogConfigurationException Log�C���X�^���X�̍쐬�Ɏ��s�����ꍇ
     */
    public Log getInstance(String name) throws LogConfigurationException{
        return getInstance((Object)name);
    }
    
    /**
     * �쐬����{@link Log}�C���X�^���X���J������B<p>
     */
    public void release(){
        logInstances.clear();
        if(logFactory != null){
            logFactory.release();
        }
    }
    
    /**
     * �����l���擾����B<p>
     * "commons-logging.properties"�Őݒ肵���v���p�e�B�������Ƃ��Ċi�[�����B<p>
     *
     * @param name ������
     * @return �����l
     * @see #getAttributeNames()
     * @see #removeAttribute(String)
     * @see #setAttribute(String, Object)
     */
    public Object getAttribute(String name){
        if(logFactory != null){
            return logFactory.getAttribute(name);
        }
        return attributes.get(name);
    }
    
    /**
     * �������̔z����擾����B<p>
     * "commons-logging.properties"�Őݒ肵���v���p�e�B�������Ƃ��Ċi�[�����B<p>
     *
     * @return �������̔z��
     * @see #getAttribute(String)
     * @see #removeAttribute(String)
     * @see #setAttribute(String, Object)
     */
    public String[] getAttributeNames(){
        if(logFactory != null){
            return logFactory.getAttributeNames();
        }
        return (String[])attributes.keySet()
            .toArray(new String[attributes.size()]);
    }
    
    /**
     * �������폜����B<p>
     * "commons-logging.properties"�Őݒ肵���v���p�e�B�������Ƃ��Ċi�[�����B<p>
     *
     * @param name ������
     * @see #getAttribute(String)
     * @see #getAttributeNames()
     * @see #setAttribute(String, Object)
     */
    public void removeAttribute(String name){
        attributes.remove(name);
        if(logFactory != null){
            logFactory.removeAttribute(name);
        }
    }
    
    /**
     * ������ݒ肷��B<p>
     * "commons-logging.properties"�Őݒ肵���v���p�e�B�������Ƃ��Ċi�[�����B<p>
     *
     * @param name ������
     * @param value �����l
     * @see #getAttribute(String)
     * @see #getAttributeNames()
     * @see #removeAttribute(String)
     */
    public void setAttribute(String name, Object value){
        attributes.put(name, value);
        if(logFactory != null){
            logFactory.setAttribute(name, value);
        }
    }
    
    private class LogWrapper implements org.apache.commons.logging.Log{
        
        private Log logger;
        private Log dummyLogger;
        private Log currentLogger;
        
        public LogWrapper(Log dummyLogger){
            this(null, dummyLogger);
        }
        
        public LogWrapper(Log logger, Log dummyLogger){
            this.logger = logger;
            this.dummyLogger = dummyLogger;
            if(logger != null){
                currentLogger = logger;
            }else{
                currentLogger = dummyLogger;
            }
        }
        
        public void setRealLog(Log logger){
            LogWrapper.this.logger = logger;
        }
        
        public void real(){
            currentLogger = logger;
        }
        
        public void dummy(){
            currentLogger = dummyLogger;
        }
        
        public void trace(Object message){
            currentLogger.trace(message);
        }
        
        public void trace(Object message, Throwable t){
            currentLogger.trace(message, t);
        }
        
        public void debug(Object message){
            currentLogger.debug(message);
        }
        
        public void debug(Object message, Throwable t){
            currentLogger.debug(message, t);
        }
        
        public void info(Object message){
            currentLogger.info(message);
        }
        
        public void info(Object message, Throwable t){
            currentLogger.info(message, t);
        }
        
        public void warn(Object message){
            currentLogger.warn(message);
        }
        
        public void warn(Object message, Throwable t){
            currentLogger.warn(message, t);
        }
        
        public void error(Object message){
            currentLogger.error(message);
        }
        
        public void error(Object message, Throwable t){
            currentLogger.error(message, t);
        }
        
        public void fatal(Object message){
            currentLogger.fatal(message);
        }
        
        public void fatal(Object message, Throwable t) {
            currentLogger.fatal(message, t);
        }
        
        public boolean isTraceEnabled() {
            return currentLogger.isTraceEnabled();
        }
        
        public boolean isDebugEnabled() {
            return currentLogger.isDebugEnabled();
        }
        
        public boolean isInfoEnabled() {
            return currentLogger.isInfoEnabled();
        }
        
        public boolean isWarnEnabled() {
            return currentLogger.isWarnEnabled();
        }
        
        public boolean isErrorEnabled() {
            return currentLogger.isErrorEnabled();
        }
        
        public boolean isFatalEnabled() {
            return currentLogger.isFatalEnabled();
        }
    }
}