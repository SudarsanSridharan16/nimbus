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
import javax.rmi.PortableRemoteObject;
import javax.ejb.*;

import jp.ossc.nimbus.core.*;
import jp.ossc.nimbus.service.cache.CacheMap;
import jp.ossc.nimbus.service.jndi.JndiFinder;

/**
 * �ėpEJB�t�@�N�g���B<p>
 * �C�ӂ�EJB���擾���邽�߂�EJB�t�@�N�g���T�[�r�X�ł���B���̂��߁A�ȉ��̓����EJB���擾���郁�\�b�h�̓T�|�[�g���Ă��Ȃ��B<br>
 * <ul>
 *   <li>{@link #get(String)}</li>
 *   <li>{@link #get(String, Object[])}</li>
 * </ul>
 * �ȉ��ɁA�T�[�r�X��`��������B<br>
 * <pre>
 * &lt;?xml version="1.0" encoding="Shift_JIS"?&gt;
 * 
 * &lt;server&gt;
 *     
 *     &lt;manager name="Sample"&gt;
 *         
 *         &lt;service name="EJBFactory"
 *                  code="jp.ossc.nimbus.service.ejb.InvocationEJBFactoryService"&gt;
 *             &lt;attribute name="JndiFinderServiceName"&gt;#JndiFinder&lt;/attribute&gt;
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
 * @see JndiFinder
 * @see CacheMap
 */
public class InvocationEJBFactoryService extends ServiceBase
 implements EJBFactory, InvocationEJBFactoryServiceMBean{
    
    private static final long serialVersionUID = 1678166099743647407L;
    
    /**
     * EJBObject�̃L���b�V���B<p>
     *
     * @see #getRemoteCacheMapServiceName()
     * @see #setRemoteCacheMapServiceName(ServiceName)
     */
    protected CacheMap remoteCache;
    
    /**
     * EJBObject�̃L���b�V���T�[�r�X�̃T�[�r�X���B<p>
     * ���̃T�[�r�X���̃T�[�r�X�́A{@link CacheMap}���������Ă���K�v������B<br>
     *
     * @see #getRemoteCacheMapServiceName()
     * @see #setRemoteCacheMapServiceName(ServiceName)
     */
    protected ServiceName remoteCacheServiceName;
    
    /**
     * JndiFinder�T�[�r�X�B<p>
     *
     * @see #getJndiFinderServiceName()
     * @see #setJndiFinderServiceName(ServiceName)
     */
    protected JndiFinder jndiFinder;
    
    /**
     * JndiFinder�̃L���b�V���T�[�r�X�̃T�[�r�X���B<p>
     * ���̃T�[�r�X���̃T�[�r�X�́A{@link JndiFinder}���������Ă���K�v������B<br>
     *
     * @see #getJndiFinderServiceName()
     * @see #setJndiFinderServiceName(ServiceName)
     */
    protected ServiceName jndiFinderServiceName;
    
    // InvocationEJBFactoryMBean��JavaDoc
    public ServiceName getRemoteCacheMapServiceName(){
        return remoteCacheServiceName;
    }
    
    // InvocationEJBFactoryMBean��JavaDoc
    public void setRemoteCacheMapServiceName(ServiceName serviceName){
        remoteCacheServiceName = serviceName;
    }
    
    // InvocationEJBFactoryMBean��JavaDoc
    public ServiceName getJndiFinderServiceName(){
        return jndiFinderServiceName;
    }
    
    // InvocationEJBFactoryMBean��JavaDoc
    public void setJndiFinderServiceName(ServiceName serviceName){
        jndiFinderServiceName = serviceName;
    }
    
    /**
     * JndiFinder��ݒ肷��B
     */
    public void setJndiFinder(JndiFinder jndiFinder) {
        this.jndiFinder = jndiFinder;
    }

    /**
     * CacheMap��ݒ肷��B
     */
    public void setCacheMap(CacheMap remoteCache) {
        this.remoteCache = remoteCache;
    }

    /**
     * EJB�t�@�N�g���̏������������s���B<p>
     * �����ł́A�ȉ��̏������s���B<br>
     * <ol>
     *   <li>{@link #setRemoteCacheMapServiceName(ServiceName)}�Őݒ肳�ꂽEJBObject�̃L���b�V����ݒ肷��B</li>
     *   <li>{@link #setJndiFinderServiceName(ServiceName)}�Őݒ肳�ꂽJndiFinder�T�[�r�X��ݒ肷��B�ݒ肳��Ă��Ȃ��ꍇ�́A��O��throw����B</li>
     * </ol>
     * 
     * @exception Exception InitialContext�̏������Ɏ��s�����ꍇ�A�܂��́AsetJndiFinderServiceName(ServiceName)�ŗL����JndiFinder�T�[�r�X���ݒ肳��Ă��Ȃ��ꍇ
     */
    public void startService() throws Exception{
        if(remoteCacheServiceName != null){
            remoteCache = (CacheMap)ServiceManagerFactory.getServiceObject(
                remoteCacheServiceName
            );
        }
        if(jndiFinderServiceName != null){
            jndiFinder = (JndiFinder)ServiceManagerFactory.getServiceObject(
                jndiFinderServiceName
            );
        }
        if(jndiFinder == null){
            throw new Exception("Property \"jndiFinder\" is null.");
        }
    }
    
    /**
     * EJB�t�@�N�g���̔j���������s���B<p>
     * �����ł́A�ȉ��̏������s���B<br>
     * <ol>
     *   <li>EJBObject�̃L���b�V���̎Q�Ƃ�j������B</li>
     *   <li>JndiFinder�T�[�r�X�̎Q�Ƃ�j������B</li>
     * </ol>
     * 
     * @exception Exception InitialContext�̃N���[�Y�Ɏ��s�����ꍇ
     */
    public void destroyService() throws Exception{
        remoteCache = null;
        jndiFinder = null;
    }
    
    /**
     * ���T�|�[�g�B<p>
     * ����EJB�t�@�N�g���ł́AEJBHome�̌^�������ł��Ȃ��ƁAcreate���\�b�h��Method�I�u�W�F�N�g���擾�ł����A���t���N�V�����Ăяo�����s���Ȃ��B�]���āAEJBHome�̌^������ł��Ȃ��A���̃��\�b�h�̓T�|�[�g�ł��Ȃ��B<br>
     */
    public EJBObject get(
        String name
    ) throws NamingException, CreateException, NoSuchMethodException,
             IllegalAccessException, InvocationTargetException{
        throw new UnsupportedOperationException();
    }
    
    /**
     * ���T�|�[�g�B<p>
     * ����EJB�t�@�N�g���ł́AEJBLocalHome�̌^�������ł��Ȃ��ƁAcreate���\�b�h��Method�I�u�W�F�N�g���擾�ł����A���t���N�V�����Ăяo�����s���Ȃ��B�]���āAEJBLocalHome�̌^������ł��Ȃ��A���̃��\�b�h�̓T�|�[�g�ł��Ȃ��B<br>
     */
    public EJBLocalObject getLocal(
        String name
    ) throws NamingException, CreateException, NoSuchMethodException,
             IllegalAccessException, InvocationTargetException{
        throw new UnsupportedOperationException();
    }
    
    /**
     * ���T�|�[�g�B<p>
     * ����EJB�t�@�N�g���ł́AEJBHome�̌^�������ł��Ȃ��ƁAcreate���\�b�h��Method�I�u�W�F�N�g���擾�ł����A���t���N�V�����Ăяo�����s���Ȃ��B�]���āAEJBHome�̌^������ł��Ȃ��A���̃��\�b�h�̓T�|�[�g�ł��Ȃ��B<br>
     */
    public EJBObject get(
        String name,
        Object[] params
    ) throws NamingException, CreateException, NoSuchMethodException,
             IllegalAccessException, InvocationTargetException{
        throw new UnsupportedOperationException();
    }
    
    /**
     * ���T�|�[�g�B<p>
     * ����EJB�t�@�N�g���ł́AEJBLocaHome�̌^�������ł��Ȃ��ƁAcreate���\�b�h��Method�I�u�W�F�N�g���擾�ł����A���t���N�V�����Ăяo�����s���Ȃ��B�]���āAEJBLocaHome�̌^������ł��Ȃ��A���̃��\�b�h�̓T�|�[�g�ł��Ȃ��B<br>
     */
    public EJBLocalObject getLocal(
        String name,
        Object[] params
    ) throws NamingException, CreateException, NoSuchMethodException,
             IllegalAccessException, InvocationTargetException{
        throw new UnsupportedOperationException();
    }
    
    /**
     * �w�肵�����O��EJB��EJBObject���擾����B<p>
     * �ݒ肳�ꂽ{@link JndiFinder}���g���āA�w�肳�ꂽ���O��EJBHome��lookup����B�܂��Alookup����EJBHome�ɑ΂��āA�����Ȃ���create���\�b�h���Ăяo����EJBObject���擾����B<br>
     *
     * @param name JndiFinder�T�[�r�X�ɓn�����O�B
     * @param homeType EJBHome�̃N���X�I�u�W�F�N�g
     * @return �w�肵��JNDI���ɑΉ�����EJBObject
     * @exception NamingException EJBHome��lookup�Ɏ��s�����ꍇ
     * @exception CreateException EJBHome��create���\�b�h���Ăяo�����ۂɗ�O�����������ꍇ
     * @exception NoSuchMethodException EJBHome��create���\�b�h��������Ȃ��ꍇ
     * @exception IllegalAccessException EJBHome��create���\�b�h���Ăяo�������ɁA�A�N�Z�X�C���q�ɂ��A�N�Z�X�������s���ȏꍇ
     * @exception InvocationTargetException EJBHome��create���\�b�h���Ăяo�������ɁA�Ăяo����ŉ��炩�̗�O�����������ꍇ
     */
    public EJBObject get(
        String name,
        Class homeType
    ) throws NamingException, CreateException, NoSuchMethodException,
             IllegalAccessException, InvocationTargetException{
        return get(name, homeType, null, null, null);
    }
    
    /**
     * �w�肵�����O��EJB��EJBLocalObject���擾����B<p>
     * �ݒ肳�ꂽ{@link JndiFinder}���g���āA�w�肳�ꂽ���O��EJBLocalHome��lookup����B�܂��Alookup����EJBLocalHome�ɑ΂��āA�����Ȃ���create���\�b�h���Ăяo����EJBLocalObject���擾����B<br>
     *
     * @param name JndiFinder�T�[�r�X�ɓn�����O�B
     * @param homeType EJBLocalHome�̃N���X�I�u�W�F�N�g
     * @return �w�肵��JNDI���ɑΉ�����EJBLocalObject
     * @exception NamingException EJBLocalHome��lookup�Ɏ��s�����ꍇ
     * @exception CreateException EJBLocalHome��create���\�b�h���Ăяo�����ۂɗ�O�����������ꍇ
     * @exception NoSuchMethodException EJBLocalHome��create���\�b�h��������Ȃ��ꍇ
     * @exception IllegalAccessException EJBLocalHome��create���\�b�h���Ăяo�������ɁA�A�N�Z�X�C���q�ɂ��A�N�Z�X�������s���ȏꍇ
     * @exception InvocationTargetException EJBLocalHome��create���\�b�h���Ăяo�������ɁA�Ăяo����ŉ��炩�̗�O�����������ꍇ
     */
    public EJBLocalObject getLocal(
        String name,
        Class homeType
    ) throws NamingException, CreateException, NoSuchMethodException,
             IllegalAccessException, InvocationTargetException{
        return getLocal(name, homeType, null, null, null);
    }
    
    /**
     * �w�肵�����O��EJB��EJBObject���擾����B<p>
     * �ݒ肳�ꂽ{@link JndiFinder}���g���āA�w�肳�ꂽ���O��EJBHome��lookup����B�܂��Alookup����EJBHome�ɑ΂��āA�w�肵��������create���\�b�h���Ăяo����EJBObject���擾����B<br>
     *
     * @param name JndiFinder�T�[�r�X�ɓn�����O�B
     * @param homeType EJBHome�̃N���X�I�u�W�F�N�g
     * @param paramTypes �����̌^�z��
     * @param params �����̔z��
     * @return �w�肵��JNDI���ɑΉ�����EJBObject
     * @exception NamingException EJBHome��lookup�Ɏ��s�����ꍇ
     * @exception CreateException EJBHome��create���\�b�h���Ăяo�����ۂɗ�O�����������ꍇ
     * @exception NoSuchMethodException EJBHome��create���\�b�h��������Ȃ��ꍇ
     * @exception IllegalAccessException EJBHome��create���\�b�h���Ăяo�������ɁA�A�N�Z�X�C���q�ɂ��A�N�Z�X�������s���ȏꍇ
     * @exception InvocationTargetException EJBHome��create���\�b�h���Ăяo�������ɁA�Ăяo����ŉ��炩�̗�O�����������ꍇ
     */
    public EJBObject get(
        String name,
        Class homeType,
        Class[] paramTypes,
        Object[] params
    ) throws NamingException, CreateException, NoSuchMethodException,
             IllegalAccessException, InvocationTargetException{
        return get(name, homeType, null, paramTypes, params);
    }
    
    /**
     * �w�肵�����O��EJB��EJBLocalObject���擾����B<p>
     * �ݒ肳�ꂽ{@link JndiFinder}���g���āA�w�肳�ꂽ���O��EJBLocalHome��lookup����B�܂��Alookup����EJBLocalHome�ɑ΂��āA�w�肵��������create���\�b�h���Ăяo����EJBLocalObject���擾����B<br>
     *
     * @param name JndiFinder�T�[�r�X�ɓn�����O�B
     * @param homeType EJBLocalHome�̃N���X�I�u�W�F�N�g
     * @param paramTypes �����̌^�z��
     * @param params �����̔z��
     * @return �w�肵��JNDI���ɑΉ�����EJBLocalObject
     * @exception NamingException EJBLocalHome��lookup�Ɏ��s�����ꍇ
     * @exception CreateException EJBLocalHome��create���\�b�h���Ăяo�����ۂɗ�O�����������ꍇ
     * @exception NoSuchMethodException EJBLocalHome��create���\�b�h��������Ȃ��ꍇ
     * @exception IllegalAccessException EJBLocalHome��create���\�b�h���Ăяo�������ɁA�A�N�Z�X�C���q�ɂ��A�N�Z�X�������s���ȏꍇ
     * @exception InvocationTargetException EJBLocalHome��create���\�b�h���Ăяo�������ɁA�Ăяo����ŉ��炩�̗�O�����������ꍇ
     */
    public EJBLocalObject getLocal(
        String name,
        Class homeType,
        Class[] paramTypes,
        Object[] params
    ) throws NamingException, CreateException, NoSuchMethodException,
             IllegalAccessException, InvocationTargetException{
        return getLocal(name, homeType, null, paramTypes, params);
    }
    
    /**
     * �w�肵�����O��EJB��EJBObject���擾����B<p>
     * �ݒ肳�ꂽ{@link JndiFinder}���g���āA�w�肳�ꂽ���O��EJBHome��lookup����B�܂��Alookup����EJBHome�ɑ΂��āA�w�肵��������create���\�b�h���Ăяo����EJBObject���擾���āAEJBObject��ړI�̃^�C�v�ɃL���X�g���ĕԂ��B<br>
     *
     * @param name JndiFinder�T�[�r�X�ɓn�����O�B
     * @param homeType EJBHome�̃N���X�I�u�W�F�N�g
     * @param remoteType EJBObject�̃N���X�I�u�W�F�N�g
     * @param paramTypes �����̌^�z��
     * @param params �����̔z��
     * @return �w�肵��JNDI���ɑΉ�����EJBObject
     * @exception NamingException EJBHome��lookup�Ɏ��s�����ꍇ
     * @exception CreateException EJBHome��create���\�b�h���Ăяo�����ۂɗ�O�����������ꍇ
     * @exception NoSuchMethodException EJBHome��create���\�b�h��������Ȃ��ꍇ
     * @exception IllegalAccessException EJBHome��create���\�b�h���Ăяo�������ɁA�A�N�Z�X�C���q�ɂ��A�N�Z�X�������s���ȏꍇ
     * @exception InvocationTargetException EJBHome��create���\�b�h���Ăяo�������ɁA�Ăяo����ŉ��炩�̗�O�����������ꍇ
     */
    public EJBObject get(
        String name,
        Class homeType,
        Class remoteType,
        Class[] paramTypes,
        Object[] params
    ) throws NamingException, CreateException, NoSuchMethodException,
             IllegalAccessException, InvocationTargetException{
        EJBObject remote = null;
        RemoteKey key = null;
        if(remoteCache != null){
            key = new RemoteKey(name, params);
            remote = (EJBObject)remoteCache.get(key);
        }
        final EJBHome home = createHome(name, homeType);
        if(remote == null){
            remote = createRemote(home, homeType, paramTypes, params);
            
            if(remoteCache != null){
                remoteCache.put(key, remote);
            }
        }
        if(remoteType == null){
            return remote;
        }
        return (EJBObject)PortableRemoteObject.narrow(remote, remoteType);
    }
    
    /**
     * �w�肵�����O��EJB��EJBLocalObject���擾����B<p>
     * �ݒ肳�ꂽ{@link JndiFinder}���g���āA�w�肳�ꂽ���O��EJBLocalHome��lookup����B�܂��Alookup����EJBLocalHome�ɑ΂��āA�w�肵��������create���\�b�h���Ăяo����EJBObject���擾���āAEJBObject��ړI�̃^�C�v�ɃL���X�g���ĕԂ��B<br>
     *
     * @param name JndiFinder�T�[�r�X�ɓn�����O�B
     * @param homeType EJBLocalHome�̃N���X�I�u�W�F�N�g
     * @param localType EJBLocalObject�̃N���X�I�u�W�F�N�g
     * @param paramTypes �����̌^�z��
     * @param params �����̔z��
     * @return �w�肵��JNDI���ɑΉ�����EJBLocalObject
     * @exception NamingException EJBLocalHome��lookup�Ɏ��s�����ꍇ
     * @exception CreateException EJBLocalHome��create���\�b�h���Ăяo�����ۂɗ�O�����������ꍇ
     * @exception NoSuchMethodException EJBLocalHome��create���\�b�h��������Ȃ��ꍇ
     * @exception IllegalAccessException EJBLocalHome��create���\�b�h���Ăяo�������ɁA�A�N�Z�X�C���q�ɂ��A�N�Z�X�������s���ȏꍇ
     * @exception InvocationTargetException EJBLocalHome��create���\�b�h���Ăяo�������ɁA�Ăяo����ŉ��炩�̗�O�����������ꍇ
     */
    public EJBLocalObject getLocal(
        String name,
        Class homeType,
        Class localType,
        Class[] paramTypes,
        Object[] params
    ) throws NamingException, CreateException, NoSuchMethodException,
             IllegalAccessException, InvocationTargetException{
        EJBLocalObject local = null;
        final EJBLocalHome home = createLocalHome(name, homeType);
        if(local == null){
            local = createLocal(home, homeType, paramTypes, params);
        }
        if(localType == null){
            return local;
        }
        return (EJBLocalObject)PortableRemoteObject.narrow(local, localType);
    }
    
    /**
     * �w�肵�����O��EJB��EJBHome���擾���āA�w�肳�ꂽ�^�ɃL���X�g����B<p>
     * {@link JndiFinder#lookup(String)}�ŁAEJBHome���擾���āA�w�肳�ꂽ�^�ɃL���X�g����B<br>
     * 
     * @param name JndiFinder�T�[�r�X�ɓn�����O�B
     * @param type EJBHome���L���X�g����^
     * @return EJBHome�I�u�W�F�N�g
     * @exception NamingException lookup�Ɏ��s�����ꍇ
     */
    protected EJBHome createHome(String name, Class type)
     throws NamingException{
        final EJBHome home = (EJBHome)jndiFinder.lookup(name);
        if(type == null){
            return home;
        }
        return (EJBHome)PortableRemoteObject.narrow(home, type);
    }
    
    /**
     * �w�肵�����O��EJB��EJBLocalHome���擾���āA�w�肳�ꂽ�^�ɃL���X�g����B<p>
     * {@link JndiFinder#lookup(String)}�ŁAEJBLocalHome���擾���āA�w�肳�ꂽ�^�ɃL���X�g����B<br>
     * 
     * @param name JndiFinder�T�[�r�X�ɓn�����O�B
     * @param type EJBLocalHome���L���X�g����^
     * @return EJBLocalHome�I�u�W�F�N�g
     * @exception NamingException lookup�Ɏ��s�����ꍇ
     */
    protected EJBLocalHome createLocalHome(String name, Class type)
     throws NamingException{
        final EJBLocalHome home = (EJBLocalHome)jndiFinder.lookup(name);
        if(type == null){
            return home;
        }
        return (EJBLocalHome)PortableRemoteObject.narrow(home, type);
    }
    
    /**
     * EJBObject�𐶐�����B<p>
     * ���t���N�V����API���g���āAEJBHome��create���\�b�h���Ăяo���B<br>
     *
     * @param home EJBHome�I�u�W�F�N�g
     * @param homeType EJBHome�N���X
     * @param paramTypes create���\�b�h�̈����̌^�z��
     * @param params create���\�b�h�̈����z��
     * @return EJBObject�I�u�W�F�N�g
     * @exception CreateException EJBHome��create���\�b�h���Ăяo�����ۂɗ�O�����������ꍇ
     * @exception NoSuchMethodException EJBHome��create���\�b�h��������Ȃ��ꍇ
     * @exception IllegalAccessException EJBHome��create���\�b�h���Ăяo�������ɁA�A�N�Z�X�C���q�ɂ��A�N�Z�X�������s���ȏꍇ
     * @exception InvocationTargetException EJBHome��create���\�b�h���Ăяo�������ɁA�Ăяo����ŉ��炩�̗�O�����������ꍇ
     */
    protected EJBObject createRemote(
        EJBHome home,
        Class homeType,
        Class[] paramTypes,
        Object[] params
    ) throws CreateException, NoSuchMethodException, IllegalAccessException,
             InvocationTargetException{
        final Method create
             = homeType.getMethod(EJB_CREATE_METHOD_NAME, paramTypes);
        try{
            return (EJBObject)create.invoke(home, params);
        }catch(InvocationTargetException e){
            Throwable th = e.getTargetException();
            if(th instanceof CreateException){
                throw (CreateException)th;
            }else{
                throw e;
            }
        }
    }
    
    /**
     * EJBLocalObject�𐶐�����B<p>
     * ���t���N�V����API���g���āAEJBLocalHome��create���\�b�h���Ăяo���B<br>
     *
     * @param home EJBLocalHome�I�u�W�F�N�g
     * @param homeType EJBLocalHome�N���X
     * @param paramTypes create���\�b�h�̈����̌^�z��
     * @param params create���\�b�h�̈����z��
     * @return EJBLocalObject�I�u�W�F�N�g
     * @exception CreateException EJBLocalHome��create���\�b�h���Ăяo�����ۂɗ�O�����������ꍇ
     * @exception NoSuchMethodException EJBLocalHome��create���\�b�h��������Ȃ��ꍇ
     * @exception IllegalAccessException EJBLocalHome��create���\�b�h���Ăяo�������ɁA�A�N�Z�X�C���q�ɂ��A�N�Z�X�������s���ȏꍇ
     * @exception InvocationTargetException EJBLocalHome��create���\�b�h���Ăяo�������ɁA�Ăяo����ŉ��炩�̗�O�����������ꍇ
     */
    protected EJBLocalObject createLocal(
        EJBLocalHome home,
        Class homeType,
        Class[] paramTypes,
        Object[] params
    ) throws CreateException, NoSuchMethodException, IllegalAccessException,
             InvocationTargetException{
        final Method create
             = homeType.getMethod(EJB_CREATE_METHOD_NAME, paramTypes);
        try{
            return (EJBLocalObject)create.invoke(home, params);
        }catch(InvocationTargetException e){
            Throwable th = e.getTargetException();
            if(th instanceof CreateException){
                throw (CreateException)th;
            }else{
                throw e;
            }
        }
    }
    
    /**
     * �w�肵�����O��EJB�̃L���b�V���𖳌�������B<p>
     * {@link #setJndiFinderServiceName(ServiceName)}�ŁAJndiFinder���ݒ肳��Ă���ꍇ�́AJndiFinder�̃L���b�V������w�肳�ꂽJNDI����EJBHome���폜����B<br>
     * {@link #setRemoteCacheMapServiceName(ServiceName)}�ŁAEJBObject�̃L���b�V�����ݒ肳��Ă���ꍇ�́A���̃L���b�V������w�肳�ꂽJNDI����EJBObject���폜����B<br>
     * 
     * @param name JndiFinder�T�[�r�X�ɓn�����O�B
     */
    public void invalidate(String name){
        if(jndiFinder != null){
            jndiFinder.clearCache(name);
        }
        if(remoteCache != null){
            remoteCache.remove(new RemoteKey(name));
        }
    }
    
    /**
     * EJB�̃L���b�V���𖳌�������B<p>
     * {@link #setJndiFinderServiceName(ServiceName)}�ŁAJndiFinder���ݒ肳��Ă���ꍇ�́AJndiFinder�̃L���b�V�����폜����B<br>
     * {@link #setRemoteCacheMapServiceName(ServiceName)}�ŁAEJBObject�̃L���b�V�����ݒ肳��Ă���ꍇ�́A���̃L���b�V�����폜����B<br>
     */
    public void invalidate(){
        if(jndiFinder != null){
            jndiFinder.clearCache();
        }
        if(remoteCache != null){
            remoteCache.clear();
        }
    }
    
    /**
     * EJBObject�����ʂ��邽�߂̃L�[�B<p>
     * EJBHome��JNDI���ƁAEJBObject�𐶐�����ۂɎg�p����EJBHome��create���\�b�h�̈����z������킹�āA��ӂȃL�[�Ƃ���B<br>
     *
     * @author M.Takata
     */
    protected class RemoteKey{
        
        /**
         * EJBHome��JNDI���B<p>
         */
        private final String jndiName;
        
        /**
         * EJBHome��create���\�b�h�̈����z��B<p>
         */
        private final Object[] params;
        
        /**
         * ����EJBHome���琶�����ꂽ�S�Ă�EJBObject�̃L�[�ł��鎖�������t���O�B<p>
         */
        private boolean isAll;
        
        /**
         * ����EJBHome���琶�����ꂽ�S�Ă�EJBObject�̃L�[�Ƃ��ẴC���X�^���X�𐶐�����B<p>
         * 
         * @param jndiName EJBHome��JNDI��
         */
        public RemoteKey(String jndiName){
            this(jndiName, null);
            isAll = true;
        }
        
        /**
         * �w�肳�ꂽJNDI����EJBHome����A�w�肳�ꂽ�����z���create���ꂽEJBObject�̃L�[�Ƃ��ẴC���X�^���X�𐶐�����B<p>
         *
         * @param jndiName EJBHome��JNDI��
         * @param params EJBHome��create���\�b�h�̈����z��
         */
        public RemoteKey(String jndiName, Object[] params){
            this.jndiName = jndiName;
            this.params = params;
        }
        
        /**
         * ���̃I�u�W�F�N�g�Ƒ��̃I�u�W�F�N�g�����������ǂ����������B<p>
         * JNDI���ƁAcreate���\�b�h�̈����z��̔�r���s���A�������ꍇ��true��Ԃ��B�A���A��r�Ώۂ�RemoteKey�C���X�^���X��RemoteKey(String)�Ő�������Ă���ꍇ�́AJNDI���̔�r�̂ݍs���B<br>
         *
         * @param obj ��r�Ώۂ̎Q�ƃI�u�W�F�N�g
         * @return �����Ɏw�肳�ꂽ�I�u�W�F�N�g�Ƃ��̃I�u�W�F�N�g���������ꍇ�� true�A�����łȂ��ꍇ�� false
         */
        public boolean equals(Object obj){
            if(obj == null){
                return false;
            }
            if(obj == this){
                return true;
            }
            if(!(obj instanceof RemoteKey)){
                return false;
            }
            final RemoteKey key = (RemoteKey)obj;
            if(!jndiName.equals(key.jndiName)){
                return false;
            }else if(key.isAll){
                return true;
            }else if(params == null){
                return key.params == null;
            }else if(key.params == null){
                return false;
            }else if(params.length != key.params.length){
                return false;
            }else{
                boolean result = true;
                for(int i = 0; i < params.length; i++){
                    if(params[i] == null){
                        if(key.params[i] != null){
                            return false;
                        }
                    }else if(!params[i].equals(key.params[i])){
                        return false;
                    }
                }
                return result;
            }
        }
        
        /**
         * �I�u�W�F�N�g�̃n�b�V���R�[�h�l��Ԃ��B<p>
         *
         * @return ���̃I�u�W�F�N�g�̃n�b�V���R�[�h�l
         */
        public int hashCode(){
            if(params != null){
                return jndiName.hashCode() + params.hashCode();
            }else{
                return jndiName.hashCode();
            }
        }
    }
}
