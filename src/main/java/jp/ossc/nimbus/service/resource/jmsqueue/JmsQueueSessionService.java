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
package jp.ossc.nimbus.service.resource.jmsqueue;

import jp.ossc.nimbus.core.ServiceBase;
import jp.ossc.nimbus.core.ServiceManagerFactory;
import jp.ossc.nimbus.core.ServiceName;

import javax.jms.*;
import javax.naming.*;

import jp.ossc.nimbus.service.cache.*;
import jp.ossc.nimbus.service.jndi.*;
import jp.ossc.nimbus.service.resource.*;
import jp.ossc.nimbus.lang.*;
import jp.ossc.nimbus.service.semaphore.*;



/**
 *    JMS�L���[�Z�b�V�����T�[�r�X
 *    @author    y-tokuda
 *    @version    1.00 �쐬�F2003/10/24�| y-tokuda<BR>
 *                �X�V�F2006/03/31 M.Kameda
 */
public class JmsQueueSessionService
    extends ServiceBase
    implements JmsQueueSessionServiceMBean, JmsQueueSession, CacheRemoveListener{
    
    private static final long serialVersionUID = 3277268519172853381L;
    
    //�����o�ϐ�
    /** JNDI�t�@�C���_�[�T�[�r�X */
    private JndiFinder mJndiFinderService = null;
    /** JNDI�t�@�C���_�[�T�[�r�X���� */
    private ServiceName mJndiFinderServiceName = null;
    /** AcknowledgeMode */
    private int mAckMode = Session.AUTO_ACKNOWLEDGE;
    /** �g�����U���N�V�������[�h */
    private boolean mTransanctionMode = false;
    /** �L���[�R�l�N�V�����t�@�N�g�� */
    private QueueConnectionFactory mQueueConnectionFactory;
    /** �Z�}�t�H�t�@�N�g���[�T�[�r�X�� */
    private ServiceName mSemaphoreFactoryServiceName;
    /** �Z�}�t�H�t�@�N�g���[�T�[�r�X */
    private SemaphoreFactory mSemaphoreFactory;
    /** �Z�}�t�H */
    private Semaphore mSemaphore;
    /** �R�l�N�V���� */
    private QueueConnection mConnection;
    /** �Z�b�V�����̃L���p�V�e�B */
    private int mSemaphoreCapacity;
    
    private String userName;
    private String password;
    
    /** �R�l�N�V�����t�@�N�g���[�� */
    private String queueConnectionFactoryName;
    /** �R�l�N�V�����L���b�V���}�b�v�ւ̃R�l�N�V�����i�[�L�[ */
    private String connectionKey = DEFAULT_CONNECTION_CACHE_KEY;
	/** �R�l�N�V�����L���b�V���}�b�v�T�[�r�X�� */
	private ServiceName connectionCacheMapServiceName;
	/** �R�l�N�V�����L���b�V���}�b�v */
	private CacheMap connectionCache;
    
    /**
     * �Z�}�t�H�t�@�N�g���T�[�r�X���̃Z�b�^�[
     */
    public void setSemaphoreFactoryServiceName(ServiceName name){
        mSemaphoreFactoryServiceName = name;
    }
    
    /**
     * �L���p�V�e�B�̃Z�b�^�[
     */
    public void setCapacity(int capa){
        mSemaphoreCapacity = capa;
    }
    
    /**
     * JNDI�t�@�C���_�[�T�[�r�X���̂̃Z�b�^�[
     * @param name
     */
    public void setJndiFinderServiceName(ServiceName name){
        mJndiFinderServiceName = name;
    }
    
    /**
     * JNDI�t�@�C���_�[�T�[�r�X���̂̃Q�b�^�[
     * 
     */
    public ServiceName getJndiFinderServiceName(){
        return mJndiFinderServiceName;
    }
    
    /**
     * Acknowledge���[�h�̃Q�b�^�[
     */
    public int getAcknowledgeMode(){
        return mAckMode;
    }
    /**
     * �g�����U���N�V�������[�h�̃Z�b�^�[
     */
    public void setTransanctionMode(boolean mode){
        mTransanctionMode = mode;
    }
    /**
     * �g�����U���N�V�������[�h�̃Q�b�^�[
     */
    public boolean getTransanctionMode(){
        return mTransanctionMode;
    }
    
    // JmsQueueSessionServiceMBean��JavaDoc
    public void setUserName(String name){
        userName = name;
    }
    
    // JmsQueueSessionServiceMBean��JavaDoc
    public String getUserName(){
        return userName;
    }
    
    // JmsQueueSessionServiceMBean��JavaDoc
    public void setPassword(String password){
        this.password = password;
    }
    
    // JmsQueueSessionServiceMBean��JavaDoc
    public String getPassword(){
        return password;
    }
    
    // JmsQueueSessionServiceMBean��JavaDoc
    public void setConnectionFactoryName(String name){
        queueConnectionFactoryName = name;
    }
    
    // JmsQueueSessionServiceMBean��JavaDoc
    public String getConnectionFactoryName(){
        return queueConnectionFactoryName;
    }

    /**
     * �L���b�V���}�b�v�ւ̃R�l�N�V�����i�[�L�[��ݒ�<p>
     * �}�b�v�Ɋi�[����ׂ̐ݒ�L�[�B�ݒ肪�����ꍇ�́A�f�t�H���g(QueueConnection)���̗p�B
     * @param key �i�[�L�[
     */
    public void setConnectionCacheKey(String key){
	    connectionKey = key;
	}
	/**
     * �L���b�V���}�b�v�ւ̃R�l�N�V�����i�[�L�[���擾<p>
     * �}�b�v�Ɋi�[����ׂ̐ݒ�L�[���擾�B
     * @return �i�[�L�[
     */
	public String getConnectionCacheKey(){
	    return connectionKey;
	}
	
	/**
     * �R�l�N�V�����L���b�V���}�b�v�T�[�r�X�̃T�[�r�X����ݒ�<p>
     * @param name �T�[�r�X��
     */
    public void setConnectionCacheMapServiceName(ServiceName name){
        connectionCacheMapServiceName = name;
    }
    /**
     * �R�l�N�V�����L���b�V���}�b�v�T�[�r�X�̃T�[�r�X�����擾<p>
     * @return �T�[�r�X��
     */
    public ServiceName getConnectionCacheMapServiceName(){
        return connectionCacheMapServiceName;
    }
    
    public void setJndiFinder(JndiFinder jndiFinder) {
        mJndiFinderService = jndiFinder;
    }
    
    public void setSemaphoreFactory(SemaphoreFactory semaphoreFactory) {
        mSemaphoreFactory = semaphoreFactory;
    }

    public void setConnectionCache(CacheMap connectionCache) {
        this.connectionCache = connectionCache;
    }

    /**
     * �J�n
     */
    public void startService() throws Exception{
        if(mJndiFinderServiceName != null) {
            mJndiFinderService = (JndiFinder)ServiceManagerFactory.getService(
                mJndiFinderServiceName
            );
        }
        if(mSemaphoreFactoryServiceName != null){
            mSemaphoreFactory = (SemaphoreFactory)ServiceManagerFactory
                .getService(this.mSemaphoreFactoryServiceName);
        }
        try{
			//�R�l�N�V�����t�@�N�g���[�̎擾
            if(queueConnectionFactoryName == null){
                mQueueConnectionFactory
                     = (QueueConnectionFactory)mJndiFinderService.lookup();
            }else{
                mQueueConnectionFactory
                     = (QueueConnectionFactory)mJndiFinderService.lookup(
                         queueConnectionFactoryName
                    );
            }
        }catch(NamingException e){
            //lookup�Ɏ��s
            throw new ServiceException(
                "JMSQUEUESESSIONSERVICE013",
                "Fail to lookup QueueConnectionFactory",
                e
            );
        }catch(ClassCastException e){
            throw new ServiceException(
                "JMSQUEUESESSIONSERVICE014",
                "found resource is not QueueConnectionFactory.",
                e
            );
        }
        //�R�l�N�V�����̎擾
        try{
        	if(connectionCacheMapServiceName != null){
                connectionCache = (CacheMap)ServiceManagerFactory.getServiceObject(connectionCacheMapServiceName);
            }
            if(connectionCache != null) {
                connectionCache.put(connectionKey, createConnection());
                connectionCache.getCachedReference(connectionKey)
                    .addCacheRemoveListener(this);
            }
            else {
				//�L���b�V���}�b�v���g�p���Ȃ�
        		mConnection = createConnection();
        	}         
        }catch(Exception e){
            throw new ServiceException(
                "JMSQUEUESESSIONSERVICE015",
                "Fail to Create Connection.",
                e
            );
        }
        if(mSemaphoreFactory != null){
            //�Z�}�t�H�̃C���X�^���X���擾
            mSemaphore = mSemaphoreFactory.createSemaphore(mSemaphoreCapacity);
            mSemaphore.accept();
        }
    }
    /**
     * ��~
     */
    public void stopService(){
        //�R�l�N�V��������
        try{
        	if(mConnection != null){
        		mConnection.close();
        	}
        }catch(Exception e){
            //�N���[�Y�Ɏ��s���Ă��Ȃɂ����Ȃ��B
        }
        //�Z�}�t�H�̉��
        if(mSemaphore != null){
            mSemaphore.release();
        }
        mSemaphore = null;
        //�L���b�V���}�b�v���̃R�l�N�V��������A�L���b�V���}�b�v�̏�����
        if(connectionCache != null){
	        connectionCache.remove(connectionKey);
	        connectionCache = null;
	    }
    }
    /**
     * �j��
     *
     */
    public void destory(){
        mJndiFinderService = null;
    }
    
    /**
     * Acknowledge���[�h�̃Z�b�^�[
     */
    public void setAcknowledgeMode(int mode){
        if ((mode != Session.AUTO_ACKNOWLEDGE) &&
            (mode != Session.CLIENT_ACKNOWLEDGE) &&
            (mode != Session.DUPS_OK_ACKNOWLEDGE)){
            //�L���łȂ��l���ݒ肳�ꂽ�ꍇ�Ȃɂ����Ȃ��B
            //���ʂƂ��ăf�t�H���g��Session.AUTO_ACKNOWLEDGE�ƂȂ�B
        }
        else{
            mAckMode = mode;
        }
    }
    
    /**
     * QueueSession�������\�b�h
     * @param key
     * @return TransactionObject
     */
    public TransactionResource makeResource(String key) throws JMSException{
    	QueueSession qSession = null;
    	QueueConnection conn = null;
    	
        if(mSemaphore != null){
            mSemaphore.getResource();
        }
        try{
        	if(mConnection == null && connectionCache != null){
				//�L���b�V���}�b�v�g�p�P�[�X
        		conn = (QueueConnection)connectionCache.get(connectionKey);
        		if(conn == null){
			        synchronized(connectionCache){
                		conn = (QueueConnection)connectionCache.get(connectionKey);
        			    if(conn == null){
        					//�ێ�����R�l�N�V��������
                			conn = createConnection();
                			connectionCache.put(connectionKey, conn);
                            connectionCache.getCachedReference(connectionKey)
                                .addCacheRemoveListener(this);
        			    }
			        }
        		}
        	}else{
				//�L���b�V���}�b�v�g�p���Ȃ��P�[�X
        		conn = mConnection;
        	}
        }catch(JMSException e){
            try{
    			//�R�l�N�V�����������ɗ�O�����P�[�X
            	try {
    				//�R�l�N�V�����t�@�N�g���[�̎擾
    				mQueueConnectionFactory = (QueueConnectionFactory)mJndiFinderService.lookup(queueConnectionFactoryName);
    			} catch (NamingException e1) {
    				//QueueConnectionFactory��lookup���s
    				throw new ServiceException("00013","Fail to lookup key = "+ queueConnectionFactoryName,e1); 
    			}
    			if(mConnection == null && connectionCache != null){
    				//�L���b�V���}�b�v�g�p�P�[�X
            		conn = (QueueConnection)connectionCache.get(connectionKey);
            		if(conn == null){
    			        synchronized(connectionCache){
                    		conn = (QueueConnection)connectionCache.get(connectionKey);
            			    if(conn == null){
            					//�ێ�����R�l�N�V��������
                    			conn = createConnection();
                    			connectionCache.put(connectionKey, conn);
                                connectionCache.getCachedReference(connectionKey)
                                    .addCacheRemoveListener(this);
            			    }
    			        }
            		}
            	}else{
            		conn = mConnection;
            	}
            }catch(JMSException e2){
                if(mSemaphore != null){
                    mSemaphore.freeResource();
                }
                throw e2;
            }catch(Throwable th){
                if(mSemaphore != null){
                    mSemaphore.freeResource();
                }
                if(th instanceof RuntimeException){
                    throw (RuntimeException)th;
                }else{
                    throw (Error)th;
                }
            }
        }catch(Throwable th){
            if(mSemaphore != null){
                mSemaphore.freeResource();
            }
            if(th instanceof RuntimeException){
                throw (RuntimeException)th;
            }else{
                throw (Error)th;
            }
        }
        try{
            //�R�l�N�V�������擾��A�Z�b�V�������擾
            qSession = conn.createQueueSession(mTransanctionMode, mAckMode);
            //QueTransanctionResource�ɁA�Z�}�t�H��n���Ă���
            //QueTransanctionResource��close()�ŁA�Z�}�t�H��freeResource()���R�[������B
        }catch(JMSException e){
            if(mSemaphore != null){
                mSemaphore.freeResource();
            }
            throw e;
        }catch(Throwable th){
            if(mSemaphore != null){
                mSemaphore.freeResource();
            }
            if(th instanceof RuntimeException){
                throw (RuntimeException)th;
            }else{
                throw (Error)th;
            }
        }
        final QueueTransanctionResource tranObj
             = new QueueTransanctionResource(qSession, conn, mSemaphore);
        return tranObj;
    }
    
    /**
     * �L���[�R�l�N�V�����̐���
     * @retrun QueueConnection �L���[�R�l�N�V����
     */
    private QueueConnection createConnection() throws JMSException{
    	if(userName != null){
			//���[�U�[�����ݒ肳��Ă���ꍇ�A���[�U�[���A�p�X���[�h���g�p���ăR�l�N�V�����擾
            return mQueueConnectionFactory
                .createQueueConnection(userName, password);
        }else{
            return mQueueConnectionFactory.createQueueConnection();
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
