package jp.ossc.nimbus.service.resource.datasource;
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

//�C���|�[�g
import java.lang.reflect.Constructor;
import java.sql.Connection;
import java.sql.SQLException;
import javax.naming.NamingException;

import javax.sql.*;
import jp.ossc.nimbus.service.jndi.*;
import jp.ossc.nimbus.service.journal.Journal;
import jp.ossc.nimbus.core.*;
import jp.ossc.nimbus.lang.*;
import jp.ossc.nimbus.service.performance.PerformanceStatistics;
import jp.ossc.nimbus.service.resource.TransactionResource;
import jp.ossc.nimbus.service.sequence.Sequence;
import jp.ossc.nimbus.service.log.*;
import java.util.*;
//
/**
 * �f�[�^�\�[�X����JDBC�R�l�N�V�������o�͂���t�@�N�g���[ 
 * @author   H.Nakano
 * @version  1.00 �쐬: 2003/11/30 -�@H.Nakano
 */
public class JdbcConnectionFactoryFromDataSourceService
	extends ServiceBase
	implements
		JdbcConnectionFactory,
		JdbcConnectionFactoryFromDataSourceServiceMBean {
	
    private static final long serialVersionUID = -5025578679152037666L;
    
	final static Class DEFAULT_CONNECTION_CLASS = NimbusJdbcConnection.class;
	
	final static String JDBCR = "JDBCR";
	final static String JDBCR0 = JDBCR + 0;
	final static String JDBCR00 = JDBCR0 + 0;
	final static String JDBCR000 = JDBCR00 + 0;
	final static String JDBCR0000 = JDBCR000 + 0;
	final static String JDBCR00001 = JDBCR0000 + 1;//
	final static String JDBCR00002 = JDBCR0000 + 2;//Connection�쐬���s��
	final static String JDBCR00003 = JDBCR0000 + 3;//
	final static String JDBCR00004 = JDBCR0000 + 4;//Connection�C���X�^���X�쐬���s��
	
	//DataSource�쐬�Ɋւ��p�����^
	/** JNDI�t�@�C���_�[�T�[�r�X */
	private JndiFinder  mJndiFinder = null ;
	/** JNDI�t�@�C���_�[�T�[�r�X�� */
	private ServiceName mJndiFinderServiceName = null ;

	//���O�֘A
	/** ���O�T�[�r�X�� */
	private ServiceName mLogServiceName = null;
	/** ���K�[ */
	private Logger mLogger = null;	
	private Map mDsMap = null ;
	/** AutoCommit���[�h */
	private boolean mIsAutoCommit = false ;
	private boolean mIsManagedResource = true ;
	/** Connection���[�h */
	private int mConnectionMode = CONNECTION_MODE_NORMAL;

	//�R�l�N�V�����쐬�Ɋւ��p�����^
	/** Connection�ɐݒ肷��Journal�T�[�r�X�� */
	private ServiceName mJournalServiceName = null;
	/** Journal ���x�� **/
	private int mJournalLevel=0;
	/** Connection�ɐݒ肷��Performance���v�T�[�r�X�� */
	private ServiceName mPerformanceServiceName = null;
	/** Connection�ɐݒ肷��V�[�P���X�T�[�r�X�� */
	private ServiceName mSequenceServiceName = null;
	
	/** Journal�T�[�r�X*/
	private Journal mJournalService = null;
	/** Performance�T�[�r�X*/
	private PerformanceStatistics mPerformanceService = null;
	/** Sequence�T�[�r�X*/
	private Sequence mSequenceService = null;

	/** �쐬�����R�l�N�V�����N���X���B
	 *�@NimbusJdbcConnection�������͂��̃T�u�N���X�ł���K�v������  
	 */
	private String mConnectionClassName = null;
	/**�R�l�N�V�����N���X�B�f�t�H���g��NimbusConnection*/
	private Class mConnectionClass ;
	/**�R�l�N�V�����N���X�R���X�g���N�^�B�f�t�H���g��NimbusConnection��Connection�����̂���*/
	private Constructor mConnectionConstructor;
	private String dataSourceName = "";
	
    //###### �T�[�r�X�������`�T�[�r�X�j��######
	/* (�� Javadoc)
	 * @see jp.ossc.nimbus.core.ServiceBaseSupport#startService()
	 */
	public void startService(){
		mJndiFinder = (JndiFinder)ServiceManagerFactory.getServiceObject(mJndiFinderServiceName) ;
		if(mLogServiceName != null){
			mLogger = (Logger)ServiceManagerFactory.getServiceObject(mLogServiceName) ;
		}
        if(mLogger == null){
			mLogger = getLogger();
		}
		mDsMap = new Hashtable() ;
		//Connection�����ݒ肳��Ă���ꍇ���̃N���X���g�p
		if( mConnectionClassName != null ){
			try {
			    //�R�l�N�V�����N���X�����݂��Ă��邩���`�F�b�N
				mConnectionClass = Class.forName(mConnectionClassName);
			} catch (ClassNotFoundException e) {
				throw new IllegalArgumentException("Connection class :"+mConnectionClassName+" not found.");
			}
			if( !isValidConnectionClass(mConnectionClass) ){
				throw new IllegalArgumentException("Connection class  :"+mConnectionClassName+" must be derived class of "+ DEFAULT_CONNECTION_CLASS);				
			}
		} else {
			//Default��NimbusJdbcConnection
			mConnectionClass = DEFAULT_CONNECTION_CLASS;
		}
		//Constractor���݃`�F�b�N
		try {
			mConnectionConstructor  = mConnectionClass.getDeclaredConstructor(new Class[]{Connection.class});
		} catch( NoSuchMethodException e ){
			throw new IllegalArgumentException("Connection class must have constructor : " + mConnectionClassName +"("+Connection.class+")" );				                
		}
		
		//Journal�T�[�r�X�����Journal�T�[�r�X���擾
		if( mJournalServiceName != null ) {
			//�W���[�i���T�[�r�X�̎擾
		    try {
		        mJournalService = (Journal) ServiceManagerFactory.getServiceObject(mJournalServiceName);
		        //Journal�T�[�r�X�����w�肵�Ă��G���[�̏ꍇ��O
		    } catch ( ServiceNotFoundException e ){
				throw new IllegalArgumentException("Cannot resolve Journal Service : "+ mJournalServiceName+".");														        
		    }
			if( mJournalService == null ){
				throw new IllegalArgumentException("Cannot resolve Journal Service");												
			}
			//�V�[�P���X�T�[�r�X�̎擾
		    try {
		        mSequenceService = (Sequence) ServiceManagerFactory.getServiceObject(mSequenceServiceName);
		    } catch ( ServiceNotFoundException e ){
				throw new IllegalArgumentException("Cannot resolve Sequence Service : "+ mSequenceServiceName+".");														        
		    }
			if( mSequenceService == null ){
				throw new IllegalArgumentException("Cannot resolve Sequence Service");															    
			}
		}
		//Performance�T�[�r�X�����Journal�T�[�r�X���擾
		if( mPerformanceServiceName != null ){
		    try {
		        mPerformanceService = (PerformanceStatistics) ServiceManagerFactory.getServiceObject(mPerformanceServiceName);
		    } catch ( ServiceNotFoundException e ){
				throw new IllegalArgumentException("Cannot resolve Performance Service : "+ mPerformanceServiceName+".");														        
		    }
			//Performance�T�[�r�X�����w�肵�Ă��G���[�̏ꍇ��O
			if( mPerformanceService == null ){
				throw new IllegalArgumentException("Cannot resolve PerformanceService");												
			}
		}
	}
	/* (�� Javadoc)
	 * @see jp.ossc.nimbus.core.ServiceBaseSupport#stopService()
	 */
	public void stopService(){
		mJndiFinder = null;
		mLogger = null;
	}
	/* (�� Javadoc)
	 * @see jp.ossc.nimbus.service.resource.datasource.JdbcConnectionFactory#makeConnection(java.lang.String)
	 */
	
	//#####�����w���p�֐�#####
	/**
	 * 
	 * @param conClass �g�p���悤�Ƃ��Ă���N���X��
	 * @return NimbusConnection�̌p���N���X���ǂ���
	 */
	private boolean isValidConnectionClass(Class conClass){
		if( DEFAULT_CONNECTION_CLASS.equals(conClass) ) return true;
		return DEFAULT_CONNECTION_CLASS.isAssignableFrom(conClass);
	}

	
	//#####AP�����C���^�[�t�F�C�X#####
	public Connection makeConnection(String key) {
		DataSource ds = null ;
		NimbusJdbcConnection con = null ;
		if(key == null || key.length() == 0){
			key = dataSourceName ;
		}
		//�f�[�^�\�[�X��MAP���f�[�^�\�[�X���擾
		ds = (DataSource)this.mDsMap.get(key) ;
		if(ds == null){
		    //����A�N�Z�X���͂�����
			try {
				ds = (DataSource)this.mJndiFinder.lookup(key) ;
			} catch (NamingException e) {
				throw new ServiceException("JdbcConnectionFactoryFromDataSourceService001","NamingException key is "+key ,e) ; //$NON-NLS-1$ //$NON-NLS-2$
			}
			mDsMap.put(key,ds);
		}
		Connection fromCon=null;
		try {
		    //�f�[�^�[�\�[�X���R�l�N�V�������擾
			fromCon = ds.getConnection();
		} catch (SQLException e1) {
			if(mLogger != null){
				mLogger.write(JDBCR00002,(new Boolean(mIsAutoCommit).toString()));
			}
			throw new ServiceException("JdbcConnectionFactoryFromDataSourceService002","Connection get Error",e1);
		}
		try {
			con = (NimbusJdbcConnection) mConnectionConstructor.newInstance(new Object[]{fromCon});
		} catch (Exception e) {
			if(mLogger != null){
				mLogger.write(JDBCR00004,(new Boolean(mIsAutoCommit).toString()));
			}
			throw new ServiceException("JdbcConnectionFactoryFromDataSourceService002","Connection get Error",e);
		}
		//�p�t�H�[�}���X�T�[�r�X���ݒ肳��Ă���ꍇ���̃T�[�r�X��ݒ�
		if( mPerformanceService != null ){
			con.setPerformanceService(mPerformanceService);
		}
		//�W���[�i���T�[�r�X���ݒ肳��Ă���ꍇ���̃T�[�r�X��ݒ�
		if( mJournalService != null ){
			con.setJournalService(mJournalService);
			con.setJournalLevel(mJournalLevel);
			con.setSequenceService(mSequenceService);
		}
		if( mConnectionMode == CONNECTION_MODE_FAKE ){
		    //Fake���[�h�ɐݒ�
		    con.setFakeMode(true);
		} else {
		    con.setFakeMode(false);
		}
		
		
		if(!this.mIsManagedResource){
			try {
				if(mLogger != null){
					mLogger.write(JDBCR00002,(new Boolean(mIsAutoCommit).toString()));
				}
				con.setTrueAutoCommit(this.mIsAutoCommit) ;
			} catch (SQLException e2) {
				if(mLogger != null){
					mLogger.write(JDBCR00003,e2);
				}
				throw new ServiceException("JdbcConnectionFactoryFromDataSourceService003","AutoCommitChange Error",e2);
				
			}	
		}
		return con ;
	}
	/* (�� Javadoc)
	 * @see jp.ossc.nimbus.service.resource.datasource.JdbcConnectionFactoryFromDataSourceServiceMBean#setJndiFinderServiceName(jp.ossc.nimbus.core.ServiceName)
	 */
	public void setLogServiceName(ServiceName name) {
		mLogServiceName = name ;
	}
	/* (�� Javadoc)
	 * @see jp.ossc.nimbus.service.resource.datasource.JdbcConnectionFactoryFromDataSourceServiceMBean#getJndiFinderServiceName()
	 */
	public ServiceName getLogServiceName() {
		return mLogServiceName;
	}
	/* (�� Javadoc)
	 * @see jp.ossc.nimbus.service.resource.datasource.JdbcConnectionFactoryFromDataSourceServiceMBean#setJndiFinderServiceName(jp.ossc.nimbus.core.ServiceName)
	 */
	public void setJndiFinderServiceName(ServiceName name) {
		mJndiFinderServiceName = name ;
	}
	/* (�� Javadoc)
	 * @see jp.ossc.nimbus.service.resource.datasource.JdbcConnectionFactoryFromDataSourceServiceMBean#getJndiFinderServiceName()
	 */
	public ServiceName getJndiFinderServiceName() {
		return mJndiFinderServiceName;
	}
	/* (�� Javadoc)
	 * @see jp.ossc.nimbus.service.resource.datasource.JdbcConnectionFactoryFromDataSourceServiceMBean#setAutoCommit(boolean)
	 */
	public void setAutoCommit(boolean isAutoCommit) {
		this.mIsAutoCommit = isAutoCommit ;
	}
	/* (�� Javadoc)
	 * @see jp.ossc.nimbus.service.resource.ResourceFactory#makeResource(java.lang.String)
	 */
	public TransactionResource makeResource(String key) throws SQLException {
		Connection con = makeConnection(key) ;
		JdbcConnectionTransactionResource ret = new JdbcConnectionTransactionResource(con) ;
		return ret ;
	}
	/* (�� Javadoc)
	 * @see jp.ossc.nimbus.service.resource.datasource.JdbcConnectionFactoryFromDataSourceServiceMBean#setManagedResourcre(boolean)
	 */
	public void setManagedResource(boolean isManaged) {
		this.mIsManagedResource = isManaged ;
		
	}
	/* (�� Javadoc)
	 * @see jp.ossc.nimbus.service.resource.datasource.JdbcConnectionFactoryFromDataSourceServiceMBean#setConnectionClassName(java.lang.String)
	 */
	public void setConnectionClassName(String name) {
		mConnectionClassName = name;
	}
	/* (�� Javadoc)
	 * @see jp.ossc.nimbus.service.resource.datasource.JdbcConnectionFactoryFromDataSourceServiceMBean#setPreformanceLogServiceName(jp.ossc.nimbus.core.ServiceName)
	 */
	public void setPerformanceServiceName(ServiceName serviceName) {
		mPerformanceServiceName = serviceName;
		
	}
	/* (�� Javadoc)
	 * @see jp.ossc.nimbus.service.resource.datasource.JdbcConnectionFactoryFromDataSourceServiceMBean#setJournalServiceName(jp.ossc.nimbus.core.ServiceName)
	 */
	public ServiceName getPerformanceServiceName(  ){
	    return mPerformanceServiceName;
	}
	/* (�� Javadoc)
	 * @see jp.ossc.nimbus.service.resource.datasource.JdbcConnectionFactoryFromDataSourceServiceMBean#setJournalServiceName(jp.ossc.nimbus.core.ServiceName)
	 */
	public void setJournalServiceName(ServiceName serviceName) {
		mJournalServiceName = serviceName;
	}
	/* (�� Javadoc)
	 * @see jp.ossc.nimbus.service.resource.datasource.JdbcConnectionFactoryFromDataSourceServiceMBean#setJournalServiceName(jp.ossc.nimbus.core.ServiceName)
	 */
	public ServiceName getJournalServiceName(  ){
	    return mJournalServiceName;
	}
	/* (�� Javadoc)
	 * @see jp.ossc.nimbus.service.resource.datasource.JdbcConnectionFactoryFromDataSourceServiceMBean#setJournalServiceName(jp.ossc.nimbus.core.ServiceName)
	 */
	public void setSequenceServiceName(ServiceName serviceName) {
		mSequenceServiceName = serviceName;
	}
	/* (�� Javadoc)
	 * @see jp.ossc.nimbus.service.resource.datasource.JdbcConnectionFactoryFromDataSourceServiceMBean#setJournalServiceName(jp.ossc.nimbus.core.ServiceName)
	 */
	public ServiceName getSequenceServiceName(  ){
	    return mSequenceServiceName;
	}
	/* (�� Javadoc)
	 * @see jp.ossc.nimbus.service.resource.datasource.JdbcConnectionFactoryFromDataSourceServiceMBean#setJournalServiceName(jp.ossc.nimbus.core.ServiceName)
	 */
	public void setJournalLevel( int level ){
	    this.mJournalLevel = level;
	}
	/* (�� Javadoc)
	 * @see jp.ossc.nimbus.service.resource.datasource.JdbcConnectionFactoryFromDataSourceServiceMBean#setJournalServiceName(jp.ossc.nimbus.core.ServiceName)
	 */
	public int getJournalLevel() {
	    return mJournalLevel;
	}
    /* (�� Javadoc)
     * @see jp.ossc.nimbus.service.resource.datasource.JdbcConnectionFactoryFromDataSourceServiceMBean#getConnectionMode()
     */
    public int getConnectionMode() {
        return mConnectionMode;
    }
    /* (�� Javadoc)
     * @see jp.ossc.nimbus.service.resource.datasource.JdbcConnectionFactoryFromDataSourceServiceMBean#setConnectionMode(int)
     */
    public void setConnectionMode(int mode) {
        mConnectionMode = mode;
    }
    public void setDataSourceName(String name){
        dataSourceName = name;
    }
    public String getDataSourceName(){
        return dataSourceName;
    }
    
    public void setJndiFinder(JndiFinder jndiFinder) {
        mJndiFinder = jndiFinder;
    }
    public void setJournalService(Journal journalService) {
        mJournalService = journalService;
    }
    public void setLogger(Logger logger) {
        mLogger = logger;
    }
    public void setPerformanceService(PerformanceStatistics performanceService) {
        mPerformanceService = performanceService;
    }
    public void setSequenceService(Sequence sequenceService) {
        mSequenceService = sequenceService;
    }
	
}
