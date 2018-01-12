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
import javax.sql.DataSource;

import jp.ossc.nimbus.core.*;
import jp.ossc.nimbus.service.jndi.*;
import jp.ossc.nimbus.service.context.Context;

/**
 * �f�[�^�\�[�X�R�l�N�V�����t�@�N�g���B<p>
 * JNDI����A�w�肳�ꂽ�f�[�^�\�[�X���̃f�[�^�\�[�X���擾���āA�R�l�N�V�������擾����B<br>
 * �ȉ��ɁA�T�[�r�X��`��������B<br>
 * <pre>
 * &lt;?xml version="1.0" encoding="Shift_JIS"?&gt;
 * 
 * &lt;server&gt;
 *     
 *     &lt;manager name="Sample"&gt;
 *         
 *         &lt;service name="ConnectionFactory"
 *                  code="jp.ossc.nimbus.service.connection.DataSourceConnectionFactoryService"&gt;
 *             &lt;attribute name="Name"&gt;java:DefaultDS&lt;/attribute&gt;
 *             &lt;attribute name="JndiFinderServiceName"&gt;#DataSourceFinder&lt;/attribute&gt;
 *             &lt;depends&gt;DataSourceFinder&lt;/depends&gt;
 *         &lt;/service&gt;
 *         
 *         &lt;service name="DataSourceFinder"
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
 * @author M.Takata
 * @see jp.ossc.nimbus.service.jndi.JndiFinder JndiFinder
 */
public class DataSourceConnectionFactoryService extends ServiceBase
 implements ConnectionFactory, DataSourceConnectionFactoryServiceMBean, Serializable{
    
    private static final long serialVersionUID = -5837939620922806932L;
    
    /**
     * �f�[�^�\�[�X���B<p>
     */
    private String dataSourceName;
    
    /**
     * {@link JndiFinder}�I�u�W�F�N�g�B<p>
     */
    private JndiFinder jndiFinder;
    
    /**
     * {@link JndiFinder}�T�[�r�X���B<p>
     */
    private ServiceName jndiFinderName;
    
    /**
     * {@link Context}�I�u�W�F�N�g�B<p>
     */
    private Context context;
    
    /**
     * {@link Context}�T�[�r�X���B<p>
     */
    private ServiceName contextName;
    
    /**
     * {@link Context}��̃f�[�^�\�[�X���̃L�[�B<p>
     */
    private String dataSourceNameKey = DEFAULT_DATASOURCE_NAME_KEY;
    
    /**
     * �f�[�^�\�[�X�T�[�r�X���B<p>
     */
    private ServiceName dataSourceServiceName;
    
    /**
     * �f�[�^�\�[�X�B<p>
     */
    private DataSource dataSource;
    
    // DataSourceConnectionFactoryServiceMBean��JavaDoc
    public void setName(String name){
        dataSourceName = name;
    }
    
    // DataSourceConnectionFactoryServiceMBean��JavaDoc
    public String getName(){
        return dataSourceName;
    }
    
    // DataSourceConnectionFactoryServiceMBean��JavaDoc
    public void setJndiFinderServiceName(ServiceName name){
        jndiFinderName = name;
    }
    
    // DataSourceConnectionFactoryServiceMBean��JavaDoc
    public ServiceName getJndiFinderServiceName(){
        return jndiFinderName;
    }
    
    // DataSourceConnectionFactoryServiceMBean��JavaDoc
    public void setContextServiceName(ServiceName name){
        contextName = name;
    }
    
    // DataSourceConnectionFactoryServiceMBean��JavaDoc
    public ServiceName getContextServiceName(){
        return contextName;
    }
    
    // DataSourceConnectionFactoryServiceMBean��JavaDoc
    public void setDataSourceNameKey(String key){
        dataSourceNameKey = key;
    }
    
    // DataSourceConnectionFactoryServiceMBean��JavaDoc
    public String getDataSourceNameKey(){
        return dataSourceNameKey;
    }
    
    // DataSourceConnectionFactoryServiceMBean��JavaDoc
    public void setDataSourceServiceName(ServiceName name){
        dataSourceServiceName = name;
    }
    // DataSourceConnectionFactoryServiceMBean��JavaDoc
    public ServiceName getDataSourceServiceName(){
        return dataSourceServiceName;
    }
    
    public JndiFinder getJndiFinderService(){
        return jndiFinder;
    }
    public void setJndiFinderService(JndiFinder finder){
        jndiFinder = finder;
    }
    
    public Context getContextService(){
        return context;
    }
    public void setContextService(Context context){
        this.context = context;
    }
    
    public void setDataSourceService(DataSource ds){
        dataSource = ds;
    }
    public DataSource getDataSourceService(){
        return dataSource;
    }
    
    /**
     * �J�n�������s���B<p>
     * {@link JndiFinder}�T�[�r�X�����ݒ肳��Ă��Ȃ��ꍇ�͗�O��throw����B<br>
     * �f�[�^�\�[�X�����ݒ肳��Ă��Ȃ��ꍇ�͗�O��throw����B<br>
     *
     * @exception Exception �J�n�����Ɏ��s�����ꍇ
     */
    public void startService() throws Exception{
        
        if(getDataSourceServiceName() != null){
            dataSource = (DataSource)ServiceManagerFactory.getServiceObject(
                getDataSourceServiceName()
            );
        }
        if(getJndiFinderServiceName() != null){
            jndiFinder = (JndiFinder)ServiceManagerFactory.getServiceObject(
                getJndiFinderServiceName()
            );
        }
        if(jndiFinder == null && dataSource == null) {
            if(jndiFinder == null){
                throw new IllegalArgumentException("Argument : JndiFinderServiceName or JndiFinderService is null.");
            }
            if(dataSource == null){
                throw new IllegalArgumentException("Argument : DataSourceServiceName or DataSourceService is null.");
            }
        }
        
        if(getContextServiceName() != null){
            context = (Context)ServiceManagerFactory.getServiceObject(
                getContextServiceName()
            );
        }
        if(context == null && getName() == null){
            throw new IllegalArgumentException("DataSource name is null.");
        }
    }
    
    // ConnectionFactory��JavaDoc
    public Connection getConnection() throws ConnectionFactoryException{
        Connection con = null;
        DataSource ds = dataSource;
        try{
            if(ds == null){
                String name = getName();
                if(context != null){
                    final String tmpName
                         = (String)context.get(getDataSourceNameKey());
                    if(tmpName != null){
                        name = tmpName;
                    }
                }
                ds = (DataSource)jndiFinder.lookup(name);
            }
            con = ds.getConnection();
        }catch(SQLException e){
            throw new ConnectionFactoryException(e);
        }catch(javax.naming.NamingException e){
            throw new ConnectionFactoryException(e);
        }
        return con;
    }
}
