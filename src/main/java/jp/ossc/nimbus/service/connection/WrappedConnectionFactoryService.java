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
import java.sql.*;
import java.util.*;
import java.lang.reflect.*;

import jp.ossc.nimbus.beans.*;
import jp.ossc.nimbus.core.*;

/**
 * ���b�v�R�l�N�V�����t�@�N�g���B<p>
 * �w�肳�ꂽ�R�l�N�V�����t�@�N�g�������������R�l�N�V�������R�l�N�V�������b�p�Ń��b�v���ĕԂ��B<br>
 * �R�l�N�V�������b�p�N���X�́Ajava.sql.Connection�C���^�t�F�[�X���������A������java.sql.Connection�����R���X�g���N�^�������Ȃ���΂Ȃ�Ȃ��B<br>
 * �ȉ��ɁA�T�[�r�X��`��������B<br>
 * <pre>
 * &lt;?xml version="1.0" encoding="Shift_JIS"?&gt;
 * 
 * &lt;server&gt;
 *     
 *     &lt;manager name="Sample"&gt;
 *         
 *         &lt;service name="ConnectionFactory"
 *                  code="jp.ossc.nimbus.service.connection.WrappedConnectionFactoryService"&gt;
 *             &lt;attribute name="ConnectionFactoryServiceName"&gt;#JDBCConnectionFactory&lt;/attribute&gt;
 *             &lt;attribute name="ConnectionWrapperClassName"&gt;sample.sql.ConnectionWrapper&lt;/attribute&gt;
 *             &lt;depends&gt;JDBCConnectionFactory&lt;/depends&gt;
 *         &lt;/service&gt;
 *         
 *         &lt;service name="JDBCConnectionFactory"
 *                  code="jp.ossc.nimbus.service.connection.JDBCConnectionFactoryService"&gt;
 *             &lt;attribute name="DriverName"&gt;com.mysql.jdbc.Driver&lt;/attribute&gt;
 *             &lt;attribute name="ConnectionURL"&gt;jdbc:mysql://localhost/sample?useUnicode=true&amp;characterEncoding=MS932&lt;/attribute&gt;
 *             &lt;attribute name="UserName"&gt;hoge&lt;/attribute&gt;
 *             &lt;attribute name="Password"&gt;fuga&lt;/attribute&gt;
 *         &lt;/service&gt;
 *         
 *     &lt;/manager&gt;
 *     
 * &lt;/server&gt;
 * </pre>
 *
 * @author M.Takata
 */
public class WrappedConnectionFactoryService extends ServiceBase
 implements ConnectionFactory, WrappedConnectionFactoryServiceMBean, Serializable{
    
    private static final long serialVersionUID = -7525550711109470382L;
    
    /**
     * ���b�v����ConnectionFactory�T�[�r�X�̃T�[�r�X���B<p>
     */
    private ServiceName connectionFactoryServiceName;
    
    /**
     * ���b�v����ConnectionFactory�T�[�r�X�B<p>
     */
    private ConnectionFactory connectionFactory;
    
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
    
    /**
     * �J�n�������s���B<p>
     *
     * @exception Exception �J�n�����Ɏ��s�����ꍇ
     */
    public void startService() throws Exception{
        
        if(connectionFactoryServiceName != null){
            connectionFactory = (ConnectionFactory)ServiceManagerFactory.getServiceObject(
                connectionFactoryServiceName
            );
        } else if(connectionFactory == null) {
            throw new IllegalArgumentException(
                "Argument : ConnectionFactoryServiceName or ConnectionFactory is null."
            );
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
    }
    
    /**
     * ��~�������s���B<p>
     *
     * @exception Exception ��~�����Ɏ��s�����ꍇ
     */
    public void stopService() throws Exception{
        connectionFactory = null;
        connectionWrapperClass = null;
        properties = null;
    }
    
    // ConnectionFactory��JavaDoc
    public Connection getConnection() throws ConnectionFactoryException{
        Connection con = connectionFactory.getConnection();
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
                throw new ConnectionFactoryException(e);
            }catch(IllegalAccessException e){
                throw new ConnectionFactoryException(e);
            }catch(InvocationTargetException e){
                throw new ConnectionFactoryException(e.getTargetException());
            }catch(NoSuchMethodException e){
                throw new ConnectionFactoryException(e);
            }catch(NoSuchPropertyException e){
                throw new ConnectionFactoryException(e);
            }
        }
        return con;
    }
    
    // WrappedConnectionFactoryServiceMBean��JavaDoc
    public void setConnectionFactoryServiceName(ServiceName name){
        connectionFactoryServiceName = name;
    }
    
    // WrappedConnectionFactoryServiceMBean��JavaDoc
    public ServiceName getConnectionFactoryServiceName(){
        return connectionFactoryServiceName;
    }
    
    // WrappedConnectionFactoryServiceMBean��JavaDoc
    public void setConnectionWrapperClassName(String className){
        connectionWrapperClassName = className;
    }
    
    // WrappedConnectionFactoryServiceMBean��JavaDoc
    public String getConnectionWrapperClassName(){
        return connectionWrapperClassName;
    }
    
    // WrappedConnectionFactoryServiceMBean��JavaDoc
    public void setConnectionWrapperProperties(Map prop){
        connectionWrapperProperties = prop;
    }
    
    // WrappedConnectionFactoryServiceMBean��JavaDoc
    public Map getConnectionWrapperProperties(){
        return connectionWrapperProperties;
    }
    
    public void setConnectionFactory(ConnectionFactory connectionFactory) {
        this.connectionFactory = connectionFactory;
    }
}