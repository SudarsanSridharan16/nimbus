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
package jp.ossc.nimbus.service.connection;

import java.io.Serializable;
import java.io.PrintWriter;
import java.sql.*;
import java.util.*;
import java.lang.reflect.*;
import javax.sql.*;
import javax.naming.Name;
import javax.naming.Referenceable;
import javax.naming.Reference;
import javax.naming.RefAddr;
import javax.naming.StringRefAddr;
import javax.naming.NamingException;
import javax.naming.spi.ObjectFactory;
import javax.naming.Context;

import jp.ossc.nimbus.beans.*;
import jp.ossc.nimbus.core.*;
import jp.ossc.nimbus.service.repository.Repository;
import jp.ossc.nimbus.service.repository.JNDIRepositoryService;

/**
 * �f�[�^�\�[�X���b�v�B<p>
 * �w�肳�ꂽ�f�[�^�\�[�X�����b�v���āA�R�l�N�V�������b�p�Ń��b�v���ꂽ�R�l�N�V������Ԃ��f�[�^�\�[�X��JNDI�Ƀo�C���h����B<br>
 * �R�l�N�V�������b�p�N���X�́Ajava.sql.Connection�C���^�t�F�[�X���������A������java.sql.Connection�����R���X�g���N�^�������Ȃ���΂Ȃ�Ȃ��B<br>
 * �ȉ��ɁA�T�[�r�X��`��������B<br>
 * <pre>
 * &lt;?xml version="1.0" encoding="Shift_JIS"?&gt;
 * 
 * &lt;server&gt;
 *     
 *     &lt;manager name="Sample"&gt;
 *         
 *         &lt;service name="WrappedDataSource"
 *                  code="jp.ossc.nimbus.service.connection.WrappedDataSourceService"&gt;
 *             &lt;attribute name="ConnectionWrapperClassName"&gt;sample.sql.ConnectionWrapper&lt;/attribute&gt;
 *             &lt;attribute name="SourceJNDIName"&gt;java:/DefaultDS&lt;/attribute&gt;
 *             &lt;attribute name="WrappedJNDIName"&gt;java:comp/WrappedDS&lt;/attribute&gt;
 *         &lt;/service&gt;
 *         
 *     &lt;/manager&gt;
 *     
 * &lt;/server&gt;
 * </pre>
 *
 * @author M.Takata
 */
public class WrappedDataSourceService extends ServiceBase
 implements DataSource, Referenceable, WrappedDataSourceServiceMBean, Serializable{
    
    private static final long serialVersionUID = 1524707347811252995L;
    
    private ServiceName jndiRepositoryServiceName;
    private Repository jndiRepository;
    
    /**
     * ���b�v����DataSource��JNDI���B<p>
     */
    private String sourceJNDIName;
    
    /**
     * ���b�v����DataSource��JNDI���B<p>
     */
    private String wrappedJNDIName;
    
    /**
     * {@link java.sql.Connection}�����b�v����N���X�̃N���X���B<p>
     * �����Ŏw��ł���N���X�́Ajava.sql.Connection�C���^�t�F�[�X���������Ă���A������java.sql.Connection�����R���X�g���N�^���������Ă���N���X�ł���B<br>
     */
    private String connectionWrapperClassName;
    
    /**
     * {@link java.sql.Connection}�����b�v����N���X�B<p>
     */
    private Class connectionWrapperClass;
    
    private Map connectionWrapperProperties;
    
    private Map properties;
    
    private Reference reference;
    
    // WrappedDataSourceServiceMBean��JavaDoc
    public void setJNDIRepositoryServiceName(ServiceName name){
        jndiRepositoryServiceName = name;
    }
    
    // WrappedDataSourceServiceMBean��JavaDoc
    public ServiceName getJNDIRepositoryServiceName(){
        return jndiRepositoryServiceName;
    }
    
    // WrappedDataSourceServiceMBean��JavaDoc
    public void setSourceJNDIName(String name){
        sourceJNDIName = name;
    }
    
    // WrappedDataSourceServiceMBean��JavaDoc
    public String getSourceJNDIName(){
        return sourceJNDIName;
    }
    
    // WrappedDataSourceServiceMBean��JavaDoc
    public void setWrappedJNDIName(String name){
        wrappedJNDIName = name;
    }
    
    // WrappedDataSourceServiceMBean��JavaDoc
    public String getWrappedJNDIName(){
        return wrappedJNDIName;
    }
    
    // WrappedDataSourceServiceMBean��JavaDoc
    public void setConnectionWrapperClassName(String className){
        connectionWrapperClassName = className;
    }
    
    // WrappedDataSourceServiceMBean��JavaDoc
    public String getConnectionWrapperClassName(){
        return connectionWrapperClassName;
    }
    
    // WrappedDataSourceServiceMBean��JavaDoc
    public void setConnectionWrapperProperties(Map prop){
        connectionWrapperProperties = prop;
    }
    
    // WrappedDataSourceServiceMBean��JavaDoc
    public Map getConnectionWrapperProperties(){
        return connectionWrapperProperties;
    }
    
    /**
     * �J�n�������s���B<p>
     *
     * @exception Exception �J�n�����Ɏ��s�����ꍇ
     */
    public void startService() throws Exception{
        
        if(sourceJNDIName == null){
            throw new IllegalArgumentException(
                "SourceJNDIName is null."
            );
        }
        if(wrappedJNDIName == null){
            throw new IllegalArgumentException(
                "WrappedJNDIName is null."
            );
        }
        
        if(jndiRepositoryServiceName != null){
            jndiRepository = (Repository)ServiceManagerFactory.getServiceObject(jndiRepositoryServiceName);
        }
        if(jndiRepository == null){
            JNDIRepositoryService jndiRepositoryService = new JNDIRepositoryService();
            jndiRepositoryService.create();
            jndiRepositoryService.start();
            jndiRepository = jndiRepositoryService;
        }
        
        if(getConnectionWrapperClassName() != null){
            connectionWrapperClass = Class.forName(
                getConnectionWrapperClassName(),
                true,
                NimbusClassLoader.getInstance()
            );
        }
        
        if(connectionWrapperProperties != null
            && connectionWrapperProperties.size() != 0){
            properties = new LinkedHashMap();
            final Iterator props
                 = connectionWrapperProperties.keySet().iterator();
            while(props.hasNext()){
                final String propName = (String)props.next();
                final Object val = connectionWrapperProperties.get(propName);
                final Property property
                     = PropertyFactory.createProperty(propName);
                properties.put(property, val);
            }
        }
        
        if(getSourceDataSource() == null){
            throw new IllegalArgumentException(
                "SourceJNDIName can not get from JNDIRepository."
            );
        }
        
        StringRefAddr addr = new StringRefAddr(
            "nimbus/service",
            getServiceNameObject().toString()
        );
        reference = new Reference(
            getClass().getName(),
            addr,
            WrappedDataSourceObjectFactory.class.getName(),
            null
        );
        if(!jndiRepository.register(wrappedJNDIName, reference)){
            throw new IllegalArgumentException(
                "WrappedJNDIName can not register to JNDIRepository."
            );
        }
    }
    
    /**
     * ��~�������s���B<p>
     *
     * @exception Exception ��~�����Ɏ��s�����ꍇ
     */
    public void stopService() throws Exception{
        jndiRepository.unregister(wrappedJNDIName);
    }
    
    private DataSource getSourceDataSource(){
        return (DataSource)jndiRepository.get(sourceJNDIName);
    }
    
    private Connection wrapConnection(Connection con) throws SQLException{
        if(con != null && connectionWrapperClass != null){
            try{
                final Constructor constructor
                     = connectionWrapperClass.getConstructor(
                        new Class[]{Connection.class}
                    );
                con = (Connection)constructor.newInstance(new Object[]{con});
                if(properties != null){
                    final Iterator props = properties.keySet().iterator();
                    while(props.hasNext()){
                        final Property prop = (Property)props.next();
                        final Object val = properties.get(prop);
                        prop.setProperty(con, val);
                    }
                }
            }catch(InstantiationException e){
                throw new SQLException(e.toString());
            }catch(IllegalAccessException e){
                throw new SQLException(e.toString());
            }catch(InvocationTargetException e){
                throw new SQLException(e.getTargetException().toString());
            }catch(NoSuchMethodException e){
                throw new SQLException(e.toString());
            }catch(NoSuchPropertyException e){
                throw new SQLException(e.toString());
            }
        }
        return con;
    }
    
    // DataSource��JavaDoc
    public Connection getConnection() throws SQLException{
        Connection con = getSourceDataSource().getConnection();
        return wrapConnection(con);
    }
    
    // DataSource��JavaDoc
    public Connection getConnection(String username, String password) throws SQLException{
        Connection con = getSourceDataSource().getConnection(username, password);
        return wrapConnection(con);
    }
    
    // DataSource��JavaDoc
    public PrintWriter getLogWriter() throws SQLException{
        return getSourceDataSource().getLogWriter();
    }
    
    // DataSource��JavaDoc
    public void setLogWriter(PrintWriter out) throws SQLException{
        getSourceDataSource().setLogWriter(out);
    }
    
    // DataSource��JavaDoc
    public void setLoginTimeout(int seconds) throws SQLException{
        getSourceDataSource().setLoginTimeout(seconds);
    }
    
    // DataSource��JavaDoc
    public int getLoginTimeout() throws SQLException{
        return getSourceDataSource().getLoginTimeout();
    }
    

    // DataSource��JavaDoc
    public <T> T unwrap(Class<T> iface) throws SQLException{
        return getSourceDataSource().unwrap(iface);
    }
    
    // DataSource��JavaDoc
    public boolean isWrapperFor(Class<?> iface) throws SQLException{
        return getSourceDataSource().isWrapperFor(iface);
    }
    

    

    // DataSource��JavaDoc
    public java.util.logging.Logger getParentLogger() throws SQLFeatureNotSupportedException{
        return getSourceDataSource().getParentLogger();
    }

    
    public void setJNDIRepository(Repository repository) {
        jndiRepository = repository;
    }
    
    public Reference getReference() throws NamingException{
        return reference;
    }
    
    public static class WrappedDataSourceObjectFactory implements ObjectFactory{
        public Object getObjectInstance(
            Object obj,
            Name name,
            Context nameCtx,
            Hashtable environment
        ) throws Exception{
            Reference ref = (Reference)obj;
            RefAddr addr = ref.get("nimbus/service");
            String serviceNameStr = (String)addr.getContent();
            ServiceNameEditor editor = new ServiceNameEditor();
            editor.setAsText(serviceNameStr);
            ServiceName serviceName = (ServiceName)editor.getValue();
            return ServiceManagerFactory.getServiceObject(serviceName);
        }
    }
}
