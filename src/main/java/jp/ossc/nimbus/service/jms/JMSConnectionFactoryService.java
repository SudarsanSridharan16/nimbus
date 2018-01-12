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
package jp.ossc.nimbus.service.jms;

import java.util.*;
import javax.jms.*;

import jp.ossc.nimbus.core.*;
import jp.ossc.nimbus.service.jndi.*;
import jp.ossc.nimbus.service.cache.*;
import jp.ossc.nimbus.service.keepalive.*;

/**
 * JMS�R�l�N�V�����Z�b�V�����t�@�N�g���B<p>
 * javax.jms.ConnectionFactory�����b�v���AJMS�R�l�N�V�����̐������ȗ�������B<br>
 * �܂��A�������ꂽJMS�R�l�N�V�����̊J���R���h�~������AJMS�T�[�o�̃_�E�����ɁA������JMS�R�l�N�V�������Đڑ�����@�\�����B<br>
 * Queue��Topi�̃C���^�t�F�[�X���������ꂽJMS 1.1�ɑΉ����Ă��܂��BJMS 1.1�ȑO�̃o�[�W�����Ŏg�p����ꍇ�ɂ́A�T�u�N���X��{@link JMSQueueConnectionFactoryService}��A{@link JMSTopicConnectionFactoryService}���g�p���ĉ������B<br>
 * 
 * @author M.Takata
 */
public class JMSConnectionFactoryService extends ServiceBase
 implements JMSConnectionFactory, JMSConnectionFactoryServiceMBean, CacheRemoveListener{
    
    private static final long serialVersionUID = -8996430918339950670L;
    
    protected ServiceName jndiFinderServiceName;
    protected JndiFinder jndiFinder;
    
    protected String userName;
    protected String password;
    
    protected String connectionFactoryName = DEFAULT_CONNECTION_FACTORY_NAME;
    protected ConnectionFactory connectionFactory;
    protected Connection connection;
    protected Set connections;
    
    protected boolean isSingleConnection = true;
    protected boolean isConnectionManagement;
    protected boolean isStartConnection;
    
    protected String connectionKey = DEFAULT_CONNECTION_KEY;
    protected ServiceName connectionCacheMapServiceName;
    protected CacheMap connectionCache;
    
    protected int autoReconnectMode = AUTO_RECONNECT_MODE_NON;
    protected ServiceName jndiKeepAliveCheckerServiceName;
    protected KeepAliveChecker jndiKeepAliveChecker;
    protected String autoReconnectErrorLogMessageId;
    protected int autoReconnectMaxRetryCount;
    protected long autoReconnectRetryInterval = 1000;
    
    // JMSConnectionFactoryServiceMBean��JavaDoc
    public void setConnectionManagement(boolean isManaged){
        isConnectionManagement = isManaged;
    }
    // JMSConnectionFactoryServiceMBean��JavaDoc
    public boolean isConnectionManagement(){
        return isConnectionManagement;
    }
    
    // JMSConnectionFactoryServiceMBean��JavaDoc
    public void setSingleConnection(boolean isSingle){
        isSingleConnection = isSingle;
    }
    // JMSConnectionFactoryServiceMBean��JavaDoc
    public boolean isSingleConnection(){
        return isSingleConnection;
    }
    
    // JMSConnectionFactoryServiceMBean��JavaDoc
    public void setJndiFinderServiceName(ServiceName name){
        jndiFinderServiceName = name;
    }
    // JMSConnectionFactoryServiceMBean��JavaDoc
    public ServiceName getJndiFinderServiceName(){
        return jndiFinderServiceName;
    }
    
    // JMSConnectionFactoryServiceMBean��JavaDoc
    public void setConnectionFactoryName(String name){
        connectionFactoryName = name;
    }
    // JMSConnectionFactoryServiceMBean��JavaDoc
    public String getConnectionFactoryName(){
        return connectionFactoryName;
    }
    
    // JMSConnectionFactoryServiceMBean��JavaDoc
    public void setUserName(String name){
        userName = name;
    }
    // JMSConnectionFactoryServiceMBean��JavaDoc
    public String getUserName(){
        return userName;
    }
    
    // JMSConnectionFactoryServiceMBean��JavaDoc
    public void setPassword(String passwd){
        password = passwd;
    }
    // JMSConnectionFactoryServiceMBean��JavaDoc
    public String getPassword(){
        return password;
    }
    
    // JMSConnectionFactoryServiceMBean��JavaDoc
    public void setConnectionKey(String key){
        connectionKey = key;
    }
    // JMSConnectionFactoryServiceMBean��JavaDoc
    public String getConnectionKey(){
        return connectionKey;
    }
    
    // JMSConnectionFactoryServiceMBean��JavaDoc
    public void setConnectionCacheMapServiceName(ServiceName name){
        connectionCacheMapServiceName = name;
    }
    // JMSConnectionFactoryServiceMBean��JavaDoc
    public ServiceName getConnectionCacheMapServiceName(){
        return connectionCacheMapServiceName;
    }
    
    // JMSConnectionFactoryServiceMBean��JavaDoc
    public void setAutoReconnectMode(int mode){
        autoReconnectMode = mode;
    }
    // JMSConnectionFactoryServiceMBean��JavaDoc
    public int getAutoReconnectMode(){
        return autoReconnectMode;
    }
    
    // JMSConnectionFactoryServiceMBean��JavaDoc
    public void setJndiKeepAliveCheckerServiceName(ServiceName name){
        jndiKeepAliveCheckerServiceName = name;
    }
    // JMSConnectionFactoryServiceMBean��JavaDoc
    public ServiceName getJndiKeepAliveCheckerServiceName(){
        return jndiKeepAliveCheckerServiceName;
    }
    
    // JMSConnectionFactoryServiceMBean��JavaDoc
    public void setAutoReconnectErrorLogMessageId(String id){
        autoReconnectErrorLogMessageId = id;
    }
    // JMSConnectionFactoryServiceMBean��JavaDoc
    public String getAutoReconnectErrorLogMessageId(){
        return autoReconnectErrorLogMessageId;
    }
    
    // JMSConnectionFactoryServiceMBean��JavaDoc
    public void setStartConnection(boolean isStart){
        isStartConnection = isStart;
    }
    // JMSConnectionFactoryServiceMBean��JavaDoc
    public boolean isStartConnection(){
        return isStartConnection;
    }
    
    // JMSConnectionFactoryServiceMBean��JavaDoc
    public void setAutoReconnectMaxRetryCount(int count){
        autoReconnectMaxRetryCount = count;
    }
    // JMSConnectionFactoryServiceMBean��JavaDoc
    public int getAutoReconnectMaxRetryCount(){
        return autoReconnectMaxRetryCount;
    }
    
    // JMSConnectionFactoryServiceMBean��JavaDoc
    public void setAutoReconnectRetryInterval(long interval){
        autoReconnectRetryInterval = interval;
    }
    // JMSConnectionFactoryServiceMBean��JavaDoc
    public long getAutoReconnectRetryInterval(){
        return autoReconnectRetryInterval;
    }
    
    /**
     * {@link jp.ossc.nimbus.service.keepalive.KeepAliveChecker KeepAliveChecker}�T�[�r�X��ݒ肷��B<p>
     * �����Őݒ肳�ꂽKeepAliveChecker�T�[�r�X���g���āAJNDI�T�[�o�̐����m�F���s���B<br>
     *
     * @param checker KeepAliveChecker�T�[�r�X
     */
    public void setJndiKeepAliveChecker(KeepAliveChecker checker) {
        this.jndiKeepAliveChecker = checker;
    }
    
    /**
     * {@link jp.ossc.nimbus.service.jndi.JndiFinder JndiFinder}�T�[�r�X��ݒ肷��B<p>
     * �����Őݒ肳�ꂽJndiFinder�T�[�r�X���g���āAJNDI�T�[�o����javax.jms.Destination��lookup����B<br>
     *
     * @param jndiFinder JndiFinder�T�[�r�X
     */
    public void setJndiFinder(JndiFinder jndiFinder) {
        this.jndiFinder = jndiFinder;
    }
    
    /**
     * {@link jp.ossc.nimbus.service.cache.CacheMap CacheMap}�T�[�r�X��ݒ肷��B<p>
     * �����Őݒ肳�ꂽCacheMap�T�[�r�X���g���āA��������Connection���L���b�V������B<br>
     *
     * @param connectionCache CacheMap�T�[�r�X
     */
    public void setCacheMap(CacheMap connectionCache) {
        this.connectionCache = connectionCache;
    }
    
    /**
     * �T�[�r�X�̐����������s���B<p>
     *
     * @exception Exception ���������Ɏ��s�����ꍇ
     */
    public void createService() throws Exception{
        connections = new HashSet();
    }
    
    /**
     * �T�[�r�X�̊J�n�������s���B<p>
     *
     * @exception Exception �J�n�����Ɏ��s�����ꍇ
     */
    public void startService() throws Exception{
        if(jndiFinderServiceName != null) {
            jndiFinder = (JndiFinder)ServiceManagerFactory.getServiceObject(
                jndiFinderServiceName
            );
        }else if(jndiFinder == null) {
            throw new IllegalArgumentException("JndiFinderServiceName or JndiFinder must be specified.");
        }
        
        if(connectionCacheMapServiceName != null){
            connectionCache = (CacheMap)ServiceManagerFactory.getServiceObject(
                connectionCacheMapServiceName
            );
        }
        
        connectionFactory = (ConnectionFactory)
            jndiFinder.lookup(connectionFactoryName);
        
        if((autoReconnectMode == AUTO_RECONNECT_MODE_ON_RECOVER
            || autoReconnectMode == AUTO_RECONNECT_MODE_ON_DEAD)
            && jndiKeepAliveCheckerServiceName == null
        ){
            throw new IllegalArgumentException("JndiKeepAliveCheckerServiceName or JndiKeepAliveChecker must be specified.");
        }
        if(jndiKeepAliveCheckerServiceName != null) {
            jndiKeepAliveChecker = (KeepAliveChecker)ServiceManagerFactory
                .getServiceObject(jndiKeepAliveCheckerServiceName);
        }
        
        if(isSingleConnection){
            getConnection();
        }
    }
    
    /**
     * �T�[�r�X�̒�~�������s���B<p>
     *
     * @exception Exception ��~�����Ɏ��s�����ꍇ
     */
    public void stopService() throws Exception{
        connectionFactory = null;
        if(connection != null){
            try{
                connection.stop();
            }catch(JMSException e){
            }
            try{
                connection.close();
            }catch(JMSException e){
            }
        }
        connection = null;
        if(connections != null && connections.size() != 0){
            final Iterator cons = connections.iterator();
            while(cons.hasNext()){
                final Connection con = (Connection)cons.next();
                try{
                    con.stop();
                }catch(JMSException e){
                }
                try{
                    con.close();
                }catch(JMSException e){
                }
            }
            connections.clear();
        }
        if(connectionCache != null){
            connectionCache.remove(connectionKey);
        }
    }
    
    /**
     * �T�[�r�X�̔j���������s���B<p>
     *
     * @exception Exception �j�������Ɏ��s�����ꍇ
     */
    public void destroyService() throws Exception{
        connections = null;
    }
    
    // JMSConnectionFactory��JavaDoc
    public synchronized Connection getConnection()
     throws JMSConnectionCreateException{
         return getConnection(userName, password);
    }
    
    // JMSConnectionFactory��JavaDoc
    public synchronized Connection getConnection(String user, String pwd)
     throws JMSConnectionCreateException{
        if(connectionFactory == null){
            throw new JMSConnectionCreateException(
                "ConnectionFactory is null."
            );
        }
        Connection con = null;
        if(isSingleConnection){
            if(connectionCache != null){
                con = (Connection)connectionCache.get(connectionKey);
                if(con != null){
                    return con;
                }
            }else if(connection != null){
                return connection;
            }
        }
        try{
            if(isSingleConnection){
                if(connectionCache != null){
                    con = (Connection)connectionCache.get(connectionKey);
                    if(con != null){
                        return con;
                    }
                }else if(connection != null){
                    return connection;
                }
            }
            con = createConnection(
                user,
                pwd
            );
            if(isSingleConnection){
                if(con != null){
                    if(connectionCache != null){
                        connectionCache.put(connectionKey, con);
                        connectionCache.getCachedReference(connectionKey)
                            .addCacheRemoveListener(this);
                    }else{
                        connection = con;
                    }
                }
            }
            if(isConnectionManagement){
                connections.add(con);
            }
            if(isStartConnection){
                con.start();
            }
        }catch(JMSException e){
            throw new JMSConnectionCreateException(e);
        }
        return con;
    }
    
    protected Connection createConnection(String user, String pwd)
     throws JMSException, JMSConnectionCreateException{
        if(autoReconnectMode == AUTO_RECONNECT_MODE_ON_RECOVER
            || autoReconnectMode == AUTO_RECONNECT_MODE_ON_DEAD){
            ReconnectableConnection con = null;
            if(user == null){
                con = new ReconnectableConnection(connectionFactory);
            }else{
                con = new ReconnectableConnection(
                    connectionFactory,
                    user,
                    pwd
                );
            }
            con.setKeepAliveChecker(jndiKeepAliveChecker);
            con.setReconnectMode(autoReconnectMode);
            if(autoReconnectErrorLogMessageId != null){
                con.setReconnectErrorLogMessageId(
                    autoReconnectErrorLogMessageId
                );
                con.setLogger(getLogger());
            }
            con.setReconnectMaxRetryCount(autoReconnectMaxRetryCount);
            con.setReconnectRetryInterval(autoReconnectRetryInterval);
            
            return con;
        }else{
            if(user != null){
                return connectionFactory.createConnection(
                    user,
                    pwd
                );
            }else{
                return connectionFactory.createConnection();
            }
        }
    }
    
    /**
     * Connection���L���b�V�����Ă��鎞�ɁA�L���b�V������폜���ꂽ�ꍇ�ɌĂяo�����B<p>
     * �L���b�V������폜���ꂽConnection��close����B<br>
     *
     * @param ref �폜�����L���b�V���Q��
     */
    public void removed(CachedReference ref){
        final Connection con = (Connection)ref.get();
        if(con != null){
            try{
                con.close();
            }catch(JMSException e){
            }
        }
    }
}
