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

/**
 * JMS�Z�b�V�����t�@�N�g���B<p>
 * javax.jms.Connection�����b�v���AJMS�Z�b�V�����̐������ȗ�������B<br>
 * �܂��A�������ꂽJMS�Z�b�V�����̊J���R���h�~����@�\�����B<br>
 * Queue��Topi�̃C���^�t�F�[�X���������ꂽJMS 1.1�ɑΉ����Ă��܂��BJMS 1.1�ȑO�̃o�[�W�����Ŏg�p����ꍇ�ɂ́A�T�u�N���X��{@link JMSQueueSessionFactoryService}��A{@link JMSTopicSessionFactoryService}���g�p���ĉ������B<br>
 * 
 * @author M.Takata
 */
public class JMSSessionFactoryService extends ServiceBase
 implements JMSSessionFactory, JMSSessionFactoryServiceMBean{
    
    private static final long serialVersionUID = 5981302065231856716L;
    
    protected ServiceName jmsConnectionFactoryServiceName;
    protected JMSConnectionFactory jmsConnectionFactory;
    
    protected String ackModeStr = AUTO_ACKNOWLEDGE;
    protected int ackMode = Session.AUTO_ACKNOWLEDGE;
    
    protected boolean transactionMode;
    
    protected Connection connection;
    protected boolean isConnectionCreate = true;
    protected Set sessions;
    protected boolean isStartConnection;
    protected boolean isStopConnection;
    protected boolean isCloseConnection;
    protected boolean isSessionManagement;
    
    // JMSSessionFactoryServiceMBean��JavaDoc
    public void setSessionManagement(boolean isManaged){
        isSessionManagement = isManaged;
    }
    // JMSSessionFactoryServiceMBean��JavaDoc
    public boolean isSessionManagement(){
        return isSessionManagement;
    }
    
    // JMSSessionFactoryServiceMBean��JavaDoc
    public void setJMSConnectionFactoryServiceName(ServiceName name){
        jmsConnectionFactoryServiceName = name;
    }
    // JMSSessionFactoryServiceMBean��JavaDoc
    public ServiceName getJMSConnectionFactoryServiceName(){
        return jmsConnectionFactoryServiceName;
    }
    
    // JMSSessionFactoryServiceMBean��JavaDoc
    public void setAcknowledgeMode(String mode){
        if(AUTO_ACKNOWLEDGE.equals(ackModeStr)){
            ackModeStr = mode;
            ackMode = Session.AUTO_ACKNOWLEDGE;
        }else if(CLIENT_ACKNOWLEDGE.equals(ackModeStr)){
            ackModeStr = mode;
            ackMode = Session.CLIENT_ACKNOWLEDGE;
        }else if(DUPS_OK_ACKNOWLEDGE.equals(ackModeStr)){
            ackModeStr = mode;
            ackMode = Session.DUPS_OK_ACKNOWLEDGE;
        }else{
            throw new IllegalArgumentException(mode);
        }
    }
    // JMSSessionFactoryServiceMBean��JavaDoc
    public String getAcknowledgeMode(){
        return ackModeStr;
    }
    
    // JMSSessionFactoryServiceMBean��JavaDoc
    public void setTransactionMode(boolean isTransacted){
        transactionMode = isTransacted;
    }
    // JMSSessionFactoryServiceMBean��JavaDoc
    public boolean getTransactionMode(){
        return transactionMode;
    }
    
    // JMSSessionFactoryServiceMBean��JavaDoc
    public void setConnectionCreate(boolean isCreate){
        isConnectionCreate = isCreate;
    }
    // JMSSessionFactoryServiceMBean��JavaDoc
    public boolean isConnectionCreate(){
        return isConnectionCreate;
    }
    
    // JMSSessionFactoryServiceMBean��JavaDoc
    public void setStartConnection(boolean isStart){
        isStartConnection = isStart;
    }
    // JMSSessionFactoryServiceMBean��JavaDoc
    public boolean isStartConnection(){
        return isStartConnection;
    }
    
    // JMSSessionFactoryServiceMBean��JavaDoc
    public void setStopConnection(boolean isStop){
        isStopConnection = isStop;
    }
    // JMSSessionFactoryServiceMBean��JavaDoc
    public boolean isStopConnection(){
        return isStopConnection;
    }
    
    // JMSSessionFactoryServiceMBean��JavaDoc
    public void setCloseConnection(boolean isClose){
        isCloseConnection = isClose;
    }
    // JMSSessionFactoryServiceMBean��JavaDoc
    public boolean isCloseConnection(){
        return isCloseConnection;
    }
    
    /**
     * {@link JMSConnectionFactory}�T�[�r�X��ݒ肷��B<p>
     * ConnectionCreate������true�̏ꍇ�A�T�[�r�X�̊J�n���ɁA�����Őݒ肳�ꂽJMSConnectionFactory�T�[�r�X���g���āAConnection�𐶐����ێ�����B<br>
     * ConnectionCreate������false�̏ꍇ�A�ŏ���Session���擾���悤�Ƃ������ɁAConnection�𐶐����ێ�����B<br>
     *
     * @param jmsConnectionFactory JMSConnectionFactory�T�[�r�X��
     */
    public void setJMSConnectionFactory(JMSConnectionFactory jmsConnectionFactory) {
        this.jmsConnectionFactory = jmsConnectionFactory;
    }
    
    /**
     * �T�[�r�X�̐����������s���B<p>
     *
     * @exception Exception ���������Ɏ��s�����ꍇ
     */
    public void createService() throws Exception{
        sessions = new HashSet();
    }
    
    /**
     * �T�[�r�X�̊J�n�������s���B<p>
     *
     * @exception Exception �J�n�����Ɏ��s�����ꍇ
     */
    public void startService() throws Exception{
        
        if(jmsConnectionFactoryServiceName != null){
            jmsConnectionFactory
                 = (JMSConnectionFactory)ServiceManagerFactory
                    .getServiceObject(jmsConnectionFactoryServiceName);
        }
        if(isConnectionCreate){
            if(jmsConnectionFactory == null){
                throw new IllegalArgumentException(
                    "jmsConnectionFactoryServiceName must be specified."
                );
            }
            connection = jmsConnectionFactory.getConnection();
            if(isStartConnection){
                connection.start();
            }
        }
    }
    
    /**
     * �T�[�r�X�̒�~�������s���B<p>
     *
     * @exception Exception ��~�����Ɏ��s�����ꍇ
     */
    public void stopService() throws Exception{
        
        if(isStopConnection && connection != null){
            try{
                connection.stop();
            }catch(JMSException e){
            }
        }
        if(isCloseConnection && connection != null){
            try{
                connection.close();
            }catch(JMSException e){
            }
        }
        connection = null;
        
        if(sessions != null && sessions.size() != 0){
            final Iterator ss = sessions.iterator();
            while(ss.hasNext()){
                final Session s = (Session)ss.next();
                try{
                    s.close();
                }catch(JMSException e){
                }
            }
            sessions.clear();
        }
        
        jmsConnectionFactory = null;
    }
    
    /**
     * �T�[�r�X�̔j���������s���B<p>
     *
     * @exception Exception �j�������Ɏ��s�����ꍇ
     */
    public void destroyService() throws Exception{
        sessions = null;
    }
    
    // JMSSessionFactory��JavaDoc
    public Connection getConnection(){
        return connection;
    }
    
    // JMSSessionFactory��JavaDoc
    public JMSConnectionFactory getConnectionFactory(){
        return jmsConnectionFactory;
    }
    
    // JMSSessionFactory��JavaDoc
    public Session getSession() throws JMSSessionCreateException{
        Connection con = connection;
        if(con == null){
            try{
                con = jmsConnectionFactory.getConnection();
            }catch(JMSConnectionCreateException e){
                throw new JMSSessionCreateException(e);
            }
        }
        return getSession(con);
    }
    
    // JMSSessionFactory��JavaDoc
    public Session getSession(
        boolean transactionMode,
        int ackMode
    ) throws JMSSessionCreateException{
        Connection con = connection;
        if(con == null){
            try{
                con = jmsConnectionFactory.getConnection();
                if(isStartConnection){
                    con.start();
                }
            }catch(JMSException e){
                throw new JMSSessionCreateException(e);
            }catch(JMSConnectionCreateException e){
                throw new JMSSessionCreateException(e);
            }
        }
        return getSession(con, transactionMode, ackMode);
    }
    
    // JMSSessionFactory��JavaDoc
    public Session getSession(Connection con) throws JMSSessionCreateException{
        return getSession(con, transactionMode, ackMode);
    }
    
    // JMSSessionFactory��JavaDoc
    public Session getSession(
        Connection con,
        boolean transactionMode,
        int ackMode
    ) throws JMSSessionCreateException{
        try{
            final Session session = con.createSession(
                transactionMode,
                ackMode
            );
            if(isSessionManagement){
                sessions.add(session);
            }
            return session;
        }catch(JMSException e){
            throw new JMSSessionCreateException(e);
        }
    }
}
