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
import java.sql.*;
import java.util.*;

import jp.ossc.nimbus.service.journal.Journal;
import jp.ossc.nimbus.service.performance.PerformanceStatistics;
import jp.ossc.nimbus.service.sequence.Sequence;
/**
 * �R�l�N�V�������b�p�[�N���X<p>
 * Close���Ɏ����̍쐬����Stetement���ӔC�������ăN���[�Y����B
 * @version $Name:  $
 * @author H.Nakano
 * @since 1.0
 */
public class NimbusJdbcConnection implements Connection {
	/**JournalService�I�u�W�F�N�g*/
	private Journal journalService;
	/**Performance���v�I�u�W�F�N�g*/
	private PerformanceStatistics performanceService;
	/**�V�[�P���X�T�[�r�X�I�u�W�F�N�g*/
	private Sequence sequenceService;
	/**�W���[�i�����x��**/
	private int journalLevel;
	
	/**
	 * setJournalService
	 * @param journal �W���[�i���T�[�r�X
	 * */
	public void setJournalService(Journal journal){
		journalService = journal;
	}
	/**
	 * setPerformanceService
	 * @param perform �p�t�H�[�}���X�擾�T�[�r�X
	 * */
	public void setPerformanceService(PerformanceStatistics perform){
		performanceService = perform;
	}
	   /**
     * @param sequenceService sequenceService ��ݒ�B
     */
    public void setSequenceService(Sequence sequenceService) {
        this.sequenceService = sequenceService;
    }
    /**
     * �W���[�i�����x����ݒ�
     * @param level �W���[�i�����x��
     */    
    public void setJournalLevel(int level){
        this.journalLevel = level;
    }
    /**
     * �W���[�i�����x�����擾
     * @return �W���[�i�����x��
     */    
    public int getJournalLevel(){
        return journalLevel;
    }
    
    public void setFakeMode(boolean isFake){
        mIsFakeClose = isFake ;
    }
    private boolean mIsFakeClose = false ;
    /**�R�l�N�V��������*/    
	private Connection mConn = null ;
	/**�X�e�[�g�����g��ێ����Ă����הz��*/
	private ArrayList mStatementList = new ArrayList() ;

	/**
	 * �R���X�g���N�^�[
	 */
	public NimbusJdbcConnection(Connection conn ) {
		super();
		this.mConn = conn ;
	}

	/* (�� Javadoc)
	 * @see java.sql.Connection#getHoldability()
	 */
	public int getHoldability() throws SQLException {
		return mConn.getHoldability() ;
	}

	/* (�� Javadoc)
	 * @see java.sql.Connection#getTransactionIsolation()
	 */
	public int getTransactionIsolation() throws SQLException {
		return mConn.getTransactionIsolation();
	}

	/* (�� Javadoc)
	 * @see java.sql.Connection#clearWarnings()
	 */
	public void clearWarnings() throws SQLException {
		mConn.clearWarnings() ;
	}

	/* (�� Javadoc)
	 * @see java.sql.Connection#close()
	 */
	public void trueClose() throws SQLException { 
	    //�S�ẴX�e�[�g�����g��Close
		for(int cnt=0 ;cnt<this.mStatementList.size();cnt++){
			Statement st = (Statement)this.mStatementList.get(cnt) ;
			st.close() ;
		}
		mStatementList.clear() ;
		mConn.close() ;
	}
	/* (�� Javadoc)
	 * @see java.sql.Connection#close()
	 */
	public void close() throws SQLException { 
	    if(!mIsFakeClose){
	        trueClose();
	    }
	}

	/* (�� Javadoc)
	 * @see java.sql.Connection#commit()
	 */
	public void commit() throws SQLException {
		this.mConn.commit() ;
	}

	/* (�� Javadoc)
	 * @see java.sql.Connection#rollback()
	 */
	public void rollback() throws SQLException {
		this.mConn.rollback() ;
	}

	/* (�� Javadoc)
	 * @see java.sql.Connection#getAutoCommit()
	 */
	public boolean getAutoCommit() throws SQLException {
		return mConn.getAutoCommit() ;
	}

	/* (�� Javadoc)
	 * @see java.sql.Connection#isClosed()
	 */
	public boolean isClosed() throws SQLException {
		return mConn.isClosed() ;
	}

	/* (�� Javadoc)
	 * @see java.sql.Connection#isReadOnly()
	 */
	public boolean isReadOnly() throws SQLException {
		return this.mConn.isReadOnly() ;
	}

	/* (�� Javadoc)
	 * @see java.sql.Connection#setHoldability(int)
	 */
	public void setHoldability(int arg0) throws SQLException {
		mConn.setHoldability(arg0) ;
	}

	/* (�� Javadoc)
	 * @see java.sql.Connection#setTransactionIsolation(int)
	 */
	public void setTransactionIsolation(int arg0) throws SQLException {
		mConn.setTransactionIsolation(arg0) ;
	}

	/* (�� Javadoc)
	 * @see java.sql.Connection#setAutoCommit(boolean)
	 */
	public void setTrueAutoCommit(boolean arg0) throws SQLException {
	    mConn.setAutoCommit(arg0) ;
	}
	/* (�� Javadoc)
	 * @see java.sql.Connection#setAutoCommit(boolean)
	 */
	public void setAutoCommit(boolean arg0) throws SQLException {
		if(!mIsFakeClose){
		    mConn.setAutoCommit(arg0) ;
		}
	}

	/* (�� Javadoc)
	 * @see java.sql.Connection#setReadOnly(boolean)
	 */
	public void setReadOnly(boolean arg0) throws SQLException {
		mConn.setReadOnly(arg0) ;
	}

	/* (�� Javadoc)
	 * @see java.sql.Connection#getCatalog()
	 */
	public String getCatalog() throws SQLException {
		return mConn.getCatalog();
	}

	/* (�� Javadoc)
	 * @see java.sql.Connection#setCatalog(java.lang.String)
	 */
	public void setCatalog(String arg0) throws SQLException {
		mConn.setCatalog(arg0) ;
	}

	/* (�� Javadoc)
	 * @see java.sql.Connection#getMetaData()
	 */
	public DatabaseMetaData getMetaData() throws SQLException {
		return mConn.getMetaData() ;
	}

	/* (�� Javadoc)
	 * @see java.sql.Connection#getWarnings()
	 */
	public SQLWarning getWarnings() throws SQLException {
		return mConn.getWarnings() ;
	}

	/* (�� Javadoc)
	 * @see java.sql.Connection#setSavepoint()
	 */
	public Savepoint setSavepoint() throws SQLException {
		return mConn.setSavepoint() ;
	}

	/* (�� Javadoc)
	 * @see java.sql.Connection#releaseSavepoint(java.sql.Savepoint)
	 */
	public void releaseSavepoint(Savepoint arg0) throws SQLException {
		mConn.releaseSavepoint(arg0) ;
	}

	/* (�� Javadoc)
	 * @see java.sql.Connection#rollback(java.sql.Savepoint)
	 */
	public void rollback(Savepoint arg0) throws SQLException {
		this.mConn.rollback(arg0) ;
	}

	/* (�� Javadoc)
	 * @see java.sql.Connection#createStatement()
	 */
	public Statement createStatement() throws SQLException {
		Statement tmp = mConn.createStatement() ; 	
		NimbusStatement ret = new NimbusStatement(tmp) ;
		//Performance/Journal�T�[�r�X�ݒ�
		if(journalService!= null){
		    ret.setJournalService(journalService,sequenceService,journalLevel);		    
		}
		if( performanceService != null ){
		    ret.setPerformanceService(performanceService);		    
		}
		//���ɓn�����X�e�[�g�����g���Ǘ�
		this.mStatementList.add(ret) ;
		return ret;
	}

	/* (�� Javadoc)
	 * @see java.sql.Connection#createStatement(int, int)
	 */
	public Statement createStatement(int arg0, int arg1) throws SQLException {
		Statement tmp = mConn.createStatement(arg0,arg1) ; 	
		NimbusStatement ret = new NimbusStatement(tmp) ;
		//Performance/Journal�T�[�r�X�ݒ�
		if( journalService!= null){
		    ret.setJournalService(journalService,sequenceService,journalLevel);		    
		}
		if( performanceService != null ){
		    ret.setPerformanceService(performanceService);		    
		}
		//���ɓn�����X�e�[�g�����g���Ǘ�
		this.mStatementList.add(ret) ;
		return ret;
	}

	/* (�� Javadoc)
	 * @see java.sql.Connection#createStatement(int, int, int)
	 */
	public Statement createStatement(int arg0, int arg1, int arg2)
		throws SQLException {
		Statement tmp = mConn.createStatement(arg0,arg1,arg2) ; 	
		NimbusStatement ret = new NimbusStatement(tmp) ;
		//Performance/Journal�T�[�r�X�ݒ�
		if(journalService!= null){
		    ret.setJournalService(journalService,sequenceService,journalLevel);		    
		}
		if( performanceService != null ){
		    ret.setPerformanceService(performanceService);		    
		}
		//���ɓn�����X�e�[�g�����g���Ǘ�
		this.mStatementList.add(ret) ;
		return ret;
	}

	/* (�� Javadoc)
	 * @see java.sql.Connection#getTypeMap()
	 */
	public Map getTypeMap() throws SQLException {
		return mConn.getTypeMap() ;
	}

	/* (�� Javadoc)
	 * @see java.sql.Connection#setTypeMap(java.util.Map)
	 */
	public void setTypeMap(Map arg0) throws SQLException {
		mConn.setTypeMap(arg0) ;
	}

	/* (�� Javadoc)
	 * @see java.sql.Connection#nativeSQL(java.lang.String)
	 */
	public String nativeSQL(String arg0) throws SQLException {
		return mConn.nativeSQL(arg0) ;
	}

	/* (�� Javadoc)
	 * @see java.sql.Connection#prepareCall(java.lang.String)
	 */
	public CallableStatement prepareCall(String arg0) throws SQLException {
		CallableStatement tmp = mConn.prepareCall(arg0) ; 	
		NimbusCallableStatement ret = new NimbusCallableStatement(tmp,arg0) ;
		//Performance/Journal�T�[�r�X�ݒ�
		if(journalService!= null){
		    ret.setJournalService(journalService,sequenceService,journalLevel);		    
		}
		if( performanceService != null ){
		    ret.setPerformanceService(performanceService);		    
		}
		//���ɓn�����X�e�[�g�����g���Ǘ�
		this.mStatementList.add(ret) ;
		return ret ;
	}

	/* (�� Javadoc)
	 * @see java.sql.Connection#prepareCall(java.lang.String, int, int)
	 */
	public CallableStatement prepareCall(String arg0, int arg1, int arg2)
		throws SQLException {
			CallableStatement tmp = mConn.prepareCall(arg0,arg1,arg2) ; 	
			NimbusCallableStatement ret = new NimbusCallableStatement(tmp,arg0) ;
			//Performance/Journal�T�[�r�X�ݒ�
			if(journalService!= null){
			    ret.setJournalService(journalService,sequenceService,journalLevel);		    
			}
			if( performanceService != null ){
			    ret.setPerformanceService(performanceService);		    
			}
			//���ɓn�����X�e�[�g�����g���Ǘ�
			this.mStatementList.add(ret) ;
			return ret ;
	}

	/* (�� Javadoc)
	 * @see java.sql.Connection#prepareCall(java.lang.String, int, int, int)
	 */
	public CallableStatement prepareCall(
		String arg0,
		int arg1,
		int arg2,
		int arg3)
		throws SQLException {
		CallableStatement tmp = mConn.prepareCall(arg0,arg1,arg2,arg3) ; 	
		NimbusCallableStatement ret = new NimbusCallableStatement(tmp,arg0) ;
		//Performance/Journal�T�[�r�X�ݒ�
		if(journalService!= null){
		    ret.setJournalService(journalService,sequenceService,journalLevel);		    
		}
		if( performanceService != null ){
		    ret.setPerformanceService(performanceService);		    
		}
		//���ɓn�����X�e�[�g�����g���Ǘ�
		this.mStatementList.add(ret) ;
		return ret ;
	}

	/* (�� Javadoc)
	 * @see java.sql.Connection#prepareStatement(java.lang.String)
	 */
	public PreparedStatement prepareStatement(String arg0)
		throws SQLException {
		PreparedStatement tmp = mConn.prepareStatement(arg0) ; 	
		NimbusPreparedStatement ret = new NimbusPreparedStatement(tmp,arg0) ;
		//Performance/Journal�T�[�r�X�ݒ�
		if(journalService!= null){
		    ret.setJournalService(journalService,sequenceService,journalLevel);		    
		}
		if( performanceService != null ){
		    ret.setPerformanceService(performanceService);		    
		}
		//���ɓn�����X�e�[�g�����g���Ǘ�
		this.mStatementList.add(ret) ;
		return ret ;
	}

	/* (�� Javadoc)
	 * @see java.sql.Connection#prepareStatement(java.lang.String, int)
	 */
	public PreparedStatement prepareStatement(String arg0, int arg1)
		throws SQLException {
		PreparedStatement tmp = mConn.prepareStatement(arg0,arg1) ; 	
		NimbusPreparedStatement ret = new NimbusPreparedStatement(tmp,arg0) ;
		//Performance/Journal�T�[�r�X�ݒ�
		if(journalService!= null){
		    ret.setJournalService(journalService,sequenceService,journalLevel);		    
		}
		if( performanceService != null ){
		    ret.setPerformanceService(performanceService);		    
		}
		//���ɓn�����X�e�[�g�����g���Ǘ�
		this.mStatementList.add(ret) ;
		
		return ret ;
	}

	/* (�� Javadoc)
	 * @see java.sql.Connection#prepareStatement(java.lang.String, int, int)
	 */
	public PreparedStatement prepareStatement(String arg0, int arg1, int arg2)
		throws SQLException {
		PreparedStatement tmp = mConn.prepareStatement(arg0,arg1,arg2) ; 	
		NimbusPreparedStatement ret = new NimbusPreparedStatement(tmp,arg0) ;
		//Performance/Journal�T�[�r�X�ݒ�
		if(journalService!= null){
		    ret.setJournalService(journalService,sequenceService,journalLevel);		    
		}
		if( performanceService != null ){
		    ret.setPerformanceService(performanceService);		    
		}
		//���ɓn�����X�e�[�g�����g���Ǘ�
		this.mStatementList.add(ret) ;
		return ret ;
	}

	/* (�� Javadoc)
	 * @see java.sql.Connection#prepareStatement(java.lang.String, int, int, int)
	 */
	public PreparedStatement prepareStatement(
		String arg0,
		int arg1,
		int arg2,
		int arg3)
		throws SQLException {
		PreparedStatement tmp = mConn.prepareStatement(arg0,arg1,arg2,arg3) ; 	
		NimbusPreparedStatement ret = new NimbusPreparedStatement(tmp,arg0) ;
		//Performance/Journal�T�[�r�X�ݒ�
		if(journalService!= null){
		    ret.setJournalService(journalService,sequenceService,journalLevel);		    
		}
		if( performanceService != null ){
		    ret.setPerformanceService(performanceService);		    
		}
		//���ɓn�����X�e�[�g�����g���Ǘ�
		this.mStatementList.add(ret) ;
		return ret ;
	}

	/* (�� Javadoc)
	 * @see java.sql.Connection#prepareStatement(java.lang.String, int[])
	 */
	public PreparedStatement prepareStatement(String arg0, int[] arg1)
		throws SQLException {
		PreparedStatement tmp = mConn.prepareStatement(arg0,arg1) ; 	
		NimbusPreparedStatement ret = new NimbusPreparedStatement(tmp,arg0) ;
		//Performance/Journal�T�[�r�X�ݒ�
		if(journalService!= null){
		    ret.setJournalService(journalService,sequenceService,journalLevel);		    
		}
		if( performanceService != null ){
		    ret.setPerformanceService(performanceService);		    
		}
		//���ɓn�����X�e�[�g�����g���Ǘ�
		this.mStatementList.add(ret) ;
		return ret ;
	}

	/* (�� Javadoc)
	 * @see java.sql.Connection#setSavepoint(java.lang.String)
	 */
	public Savepoint setSavepoint(String arg0) throws SQLException {
		return mConn.setSavepoint(arg0);
	}

	/* (�� Javadoc)
	 * @see java.sql.Connection#prepareStatement(java.lang.String, java.lang.String[])
	 */
	public PreparedStatement prepareStatement(String arg0, String[] arg1)
		throws SQLException {
		PreparedStatement tmp = mConn.prepareStatement(arg0,arg1) ; 	
		NimbusPreparedStatement ret = new NimbusPreparedStatement(tmp,arg0) ;
		//Performance/Journal�T�[�r�X�ݒ�
		if(journalService!= null){
		    ret.setJournalService(journalService,sequenceService,journalLevel);
		}		
		if( performanceService != null ){
		    ret.setPerformanceService(performanceService);		    
		}
		//���ɓn�����X�e�[�g�����g���Ǘ�
		this.mStatementList.add(ret) ;
		return ret ;
	}
    
    

    public Struct createStruct(String typeName, Object[] attributes) throws SQLException{
        return mConn.createStruct(typeName, attributes);
    }
    
    public Array createArrayOf(String typeName, Object[] elements) throws SQLException{
        return mConn.createArrayOf(typeName, elements);
    }
    
    public Properties getClientInfo() throws SQLException{
        return mConn.getClientInfo();
    }
    
    public String getClientInfo(String name) throws SQLException{
        return mConn.getClientInfo(name);
    }
    
    public void setClientInfo(Properties properties) throws SQLClientInfoException{
        mConn.setClientInfo(properties);
    }
    
    public void setClientInfo(String name, String value) throws SQLClientInfoException{
        mConn.setClientInfo(name, value);
    }
    
    public boolean isValid(int timeout) throws SQLException{
        return mConn.isValid(timeout);
    }
    
    public SQLXML createSQLXML() throws SQLException{
        return mConn.createSQLXML();
    }
    
    public Blob createBlob() throws SQLException{
        return mConn.createBlob();
    }
    
    public Clob createClob() throws SQLException{
        return mConn.createClob();
    }
    
    public NClob createNClob() throws SQLException{
        return mConn.createNClob();
    }
    
    public boolean isWrapperFor(Class<?> iface) throws SQLException{
        return mConn.isWrapperFor(iface);
    }
    
    public <T> T unwrap(Class<T> iface) throws SQLException{
        return mConn.unwrap(iface);
    }

    

    public void setSchema(String schema) throws SQLException{
        mConn.setSchema(schema);
    }
    
    public String getSchema() throws SQLException{
        return mConn.getSchema();
    }
    
    public void abort(java.util.concurrent.Executor executor) throws SQLException{
        mConn.abort(executor);
    }
    
    public void setNetworkTimeout(java.util.concurrent.Executor executor, int milliseconds) throws SQLException{
        mConn.setNetworkTimeout(executor, milliseconds);
    }
    
    public int getNetworkTimeout() throws SQLException{
        return mConn.getNetworkTimeout();
    }

}
