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
package jp.ossc.nimbus.service.ejb;

import java.lang.reflect.*;
import javax.naming.*;
import javax.ejb.*;

import jp.ossc.nimbus.core.*;

/**
 * �P��EJB�t�@�N�g���B<p>
 * ����EJBObject���擾���邽�߂�EJB�t�@�N�g���T�[�r�X�ł���B<br>
 * EJBHome�̃N���X���AEJBObject�̃N���X���AEJBHome��create���\�b�h�̃V�O�j�`���Ȃǂ�ݒ肷�鎖�ŁAEJBObject�̎擾���ȈՉ�����B<br>
 * �ȉ��ɁA�T�[�r�X��`��������B<br>
 * <pre>
 * &lt;?xml version="1.0" encoding="Shift_JIS"?&gt;
 * 
 * &lt;server&gt;
 *     
 *     &lt;manager name="Sample"&gt;
 *         
 *         &lt;service name="EJBFactory"
 *                  code="jp.ossc.nimbus.service.ejb.UnitEJBFactoryService"&gt;
 *             &lt;attribute name="JndiFinderServiceName"&gt;#JndiFinder&lt;/attribute&gt;
 *             &lt;attribute name="HomeType"&gt;sample.SampleEJBHome&lt;/attribute&gt;
 *             &lt;attribute name="RemoteType"&gt;sample.SampleEJBRemote&lt;/attribute&gt;
 *             &lt;attribute name="CreateMethodParamTypes"&gt;java.lang.String&lt;/attribute&gt;
 *             &lt;depends&gt;JndiFinder&lt;/depends&gt;
 *         &lt;/service&gt;
 *         
 *         &lt;service name="JndiFinder"
 *                  code="jp.ossc.nimbus.service.jndi.CachedJndiFinderService"&gt;
 *             &lt;attribute name="Environment"&gt;
 *                 java.naming.factory.initial=org.jnp.interfaces.NamingContextFactory
 *                 java.naming.factory.url.pkgs=org.jboss.naming:org.jnp.interfaces
 *                 java.naming.provider.url=localhost:1099
 *             &lt;/attribute&gt;
 *         &lt;/service&gt;
 *         
 *     &lt;/manager&gt;
 *     
 * &lt;/server&gt;
 * </pre>
 *
 * @author  M.Takata
 * @see jp.ossc.nimbus.service.jndi.JndiFinder JndiFinder
 * @see jp.ossc.nimbus.service.cache.CacheMap CacheMap
 */
public class UnitEJBFactoryService extends InvocationEJBFactoryService
 implements UnitEJBFactoryServiceMBean{
    
    private static final long serialVersionUID = 7479531378907664537L;
    
    /**
     * �f�t�H���g��EJBHome�N���X�B<p>
     */
    private static final Class DEFAULT_HOME_CLASS = javax.ejb.EJBHome.class;
    
    /**
     * �f�t�H���g��EJBLocalHome�N���X�B<p>
     */
    private static final Class DEFAULT_LOCAL_HOME_CLASS = javax.ejb.EJBLocalHome.class;
    
    /**
     * �f�t�H���g��EJBObject�N���X�B<p>
     */
    private static final Class DEFAULT_REMOTE_CLASS = javax.ejb.EJBObject.class;
    
    /**
     * �f�t�H���g��EJBLocalObject�N���X�B<p>
     */
    private static final Class DEFAULT_LOCAL_CLASS = javax.ejb.EJBLocalObject.class;
    
    /**
     * EJBHome�̃N���X���B<p>
     * 
     * @see #setHomeType(String)
     * @see #getHomeType()
     */
    private String homeClassName;
    
    /**
     * EJBLocalHome�̃N���X���B<p>
     * 
     * @see #setLocalHomeType(String)
     * @see #getLocalHomeType()
     */
    private String localHomeClassName;
    
    /**
     * EJBObject�̃N���X���B<p>
     * 
     * @see #setRemoteType(String)
     * @see #getRemoteType()
     */
    private String remoteClassName;
    
    /**
     * EJBLocalObject�̃N���X���B<p>
     * 
     * @see #setLocalType(String)
     * @see #getLocalType()
     */
    private String localClassName;
    
    /**
     * EJBHome��create���\�b�h�̈����̌^���z��B<p>
     * 
     * @see #setCreateMethodParamTypes(String[])
     * @see #getCreateMethodParamTypes()
     */
    private String[] createMethodParamTypes;
    
    /**
     * EJBHome�̃N���X�B<p>
     */
    private Class homeClass = DEFAULT_HOME_CLASS;
    
    /**
     * EJBLocalHome�̃N���X�B<p>
     */
    private Class localHomeClass = DEFAULT_LOCAL_HOME_CLASS;
    
    /**
     * EJBObject�̃N���X�B<p>
     */
    private Class remoteClass = DEFAULT_REMOTE_CLASS;
    
    /**
     * EJBLocalObject�̃N���X�B<p>
     */
    private Class localClass = DEFAULT_LOCAL_CLASS;
    
    /**
     * EJBHome��create���\�b�h�̈����̌^�z��B<p>
     */
    private Class[] paramTypes;
    
    // UnitEJBFactoryMBean��JavaDoc
    public String getHomeType(){
        return homeClassName;
    }
    
    // UnitEJBFactoryMBean��JavaDoc
    public void setHomeType(String className){
        homeClassName = className;
    }
    
    // UnitEJBFactoryMBean��JavaDoc
    public String getLocalHomeType(){
        return localHomeClassName;
    }
    
    // UnitEJBFactoryMBean��JavaDoc
    public void setLocalHomeType(String className){
        localHomeClassName = className;
    }
    
    // UnitEJBFactoryMBean��JavaDoc
    public String getRemoteType(){
        return remoteClassName;
    }
    
    // UnitEJBFactoryMBean��JavaDoc
    public void setRemoteType(String className){
        remoteClassName = className;
    }
    
    // UnitEJBFactoryMBean��JavaDoc
    public String getLocalType(){
        return localClassName;
    }
    
    // UnitEJBFactoryMBean��JavaDoc
    public void setLocalType(String className){
        localClassName = className;
    }
    
    // UnitEJBFactoryMBean��JavaDoc
    public String[] getCreateMethodParamTypes(){
        return createMethodParamTypes;
    }
    
    // UnitEJBFactoryMBean��JavaDoc
    public void setCreateMethodParamTypes(String[] params){
        createMethodParamTypes = params;
    }
    
    /**
     * EJB�t�@�N�g���̏������������s���B<p>
     * �����ł́A�ȉ��̏������s���B<br>
     * <ol>
     *   <li>{@link #setHomeType(String)}�Őݒ肳�ꂽEJBHome�N���X�����[�h����B</li>
     *   <li>{@link #setLocalHomeType(String)}�Őݒ肳�ꂽEJBLocalHome�N���X�����[�h����B</li>
     *   <li>{@link #setRemoteType(String)}�Őݒ肳�ꂽEJBObject�N���X�����[�h����B</li>
     *   <li>{@link #setLocalType(String)}�Őݒ肳�ꂽEJBLocalObject�N���X�����[�h����B</li>
     *   <li>{@link #setCreateMethodParamTypes(String[])}�Őݒ肳�ꂽEJBHome��create���\�b�h�����z��̃N���X�����[�h����B</li>
     * </ol>
     * 
     * @exception Exception InitialContext�̏������Ɏ��s�����ꍇ�A�܂��́AEJBHome���̃N���X�̃��[�h�Ɏ��s�����ꍇ�B
     */
    public void startService() throws Exception{
        super.startService();
        
        if(homeClassName == null && localHomeClassName == null){
            throw new Exception("HomeType is null");
        }
        if(homeClassName != null){
            homeClass = Class.forName(
                homeClassName,
                true,
                NimbusClassLoader.getInstance()
            );
        }
        if(localHomeClassName != null){
            localHomeClass = Class.forName(
                localHomeClassName,
                true,
                NimbusClassLoader.getInstance()
            );
        }
        if(remoteClassName != null){
            remoteClass = Class.forName(
                remoteClassName,
                true,
                NimbusClassLoader.getInstance()
            );
        }
        if(localClassName != null){
            localClass = Class.forName(
                localClassName,
                true,
                NimbusClassLoader.getInstance()
            );
        }
        if(createMethodParamTypes != null
             && createMethodParamTypes.length != 0){
            Class[] params = new Class[createMethodParamTypes.length];
            for(int i = 0; i < createMethodParamTypes.length; i++){
                params[i] = Class.forName(
                    createMethodParamTypes[i],
                    true,
                    NimbusClassLoader.getInstance()
                );
            }
            paramTypes = params;
        }
    }
    
    // EJBFactory��JavaDoc
    public EJBObject get(
        String name
    ) throws NamingException, CreateException, NoSuchMethodException,
             IllegalAccessException, InvocationTargetException{
        return get(name, homeClass, remoteClass, null, null);
    }
    
    // EJBFactory��JavaDoc
    public EJBLocalObject getLocal(
        String name
    ) throws NamingException, CreateException, NoSuchMethodException,
             IllegalAccessException, InvocationTargetException{
        return getLocal(name, localHomeClass, localClass, null, null);
    }
    
    // EJBFactory��JavaDoc
    public EJBObject get(
        String name,
        Object[] params
    ) throws NamingException, CreateException, NoSuchMethodException,
             IllegalAccessException, InvocationTargetException{
        return get(name, homeClass, remoteClass, paramTypes, params);
    }
    
    // EJBFactory��JavaDoc
    public EJBLocalObject getLocal(
        String name,
        Object[] params
    ) throws NamingException, CreateException, NoSuchMethodException,
             IllegalAccessException, InvocationTargetException{
        return getLocal(name, localHomeClass, localClass, paramTypes, params);
    }
}
