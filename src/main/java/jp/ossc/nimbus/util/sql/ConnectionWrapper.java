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
package jp.ossc.nimbus.util.sql;

import java.io.Serializable;
import java.sql.*;
import java.sql.Array;
import java.util.*;
import java.lang.reflect.*;

import jp.ossc.nimbus.beans.*;

/**
 * Connection���b�p�[�B<p>
 * 
 * @author M.Takata
 */
public class ConnectionWrapper implements Connection, Serializable {
    
    private static final long serialVersionUID = 6168176679889807870L;
    
    protected Connection connection;
    
    protected Class statementWrapperClass;
    
    protected Class callableStatementWrapperClass;
    
    protected Class preparedStatementWrapperClass;
    
    protected Class resultSetWrapperClass;
    
    protected Map statementProperties;
    
    protected Map callableStatementProperties;
    
    protected Map preparedStatementProperties;
    
    protected Map resultSetProperties;
    
    /**
     * �w�肵���R�l�N�V���������b�v����C���X�^���X�𐶐�����B<p>
     *
     * @param con ���b�v����R�l�N�V����
     */
    public ConnectionWrapper(Connection con){
        connection = con;
    }
    
    /**
     * ���b�v���Ă���R�l�N�V�������擾����B<p>
     *
     * @return ���b�v���Ă���R�l�N�V����
     */
    public Connection getConnection(){
        return connection;
    }
    
    /**
     * ���b�v����R�l�N�V������ݒ肷��B<p>
     *
     * @param con ���b�v����R�l�N�V����
     */
    public void setConnection(Connection con){
        connection = con;
    }
    
    /**
     * ���b�v����{@link StatementWrapper}�̎����N���X��ݒ肷��B<p>
     *
     * @param clazz ���b�v����StatementWrapper�̎����N���X
     * @exception IllegalArgumentException �w�肵���N���X��StatementWrapper�̃T�u�N���X�łȂ��ꍇ
     */
    public void setStatementWrapperClass(Class clazz)
     throws IllegalArgumentException{
        
        if(clazz != null && !StatementWrapper.class.isAssignableFrom(clazz)){
            throw new IllegalArgumentException(
                "Illegal class : " + clazz.getName()
            );
        }
        statementWrapperClass = clazz;
    }
    
    /**
     * ���b�v����{@link StatementWrapper}�̎����N���X���擾����B<p>
     *
     * @return ���b�v����StatementWrapper�̎����N���X
     */
    public Class getStatementWrapperClass(){
        return statementWrapperClass;
    }
    
    /**
     * ���b�v����{@link CallableStatement}�̎����N���X��ݒ肷��B<p>
     *
     * @param clazz ���b�v����CallableStatement�̎����N���X
     * @exception IllegalArgumentException �w�肵���N���X��CallableStatement�̎����N���X�łȂ��A�܂���StatementWrapper�̃T�u�N���X�ł͂Ȃ��ꍇ
     */
    public void setCallableStatementWrapperClass(Class clazz)
     throws IllegalArgumentException{
        
        if(clazz != null
             && !(CallableStatement.class.isAssignableFrom(clazz)
                    && StatementWrapper.class.isAssignableFrom(clazz))){
            throw new IllegalArgumentException(
                "Illegal class : " + clazz.getName()
            );
        }
        callableStatementWrapperClass = clazz;
    }
    
    /**
     * ���b�v����{@link CallableStatementWrapper}�̎����N���X���擾����B<p>
     *
     * @return ���b�v����CallableStatementWrapper�̎����N���X
     */
    public Class getCallableStatementWrapperClass(){
        return callableStatementWrapperClass;
    }
    
    /**
     * ���b�v����{@link PreparedStatement}�̎����N���X��ݒ肷��B<p>
     *
     * @param clazz ���b�v����PreparedStatement�̎����N���X
     * @exception IllegalArgumentException �w�肵���N���X��PreparedStatement�̎����N���X�łȂ��A�܂���StatementWrapper�̃T�u�N���X�ł͂Ȃ��ꍇ
     */
    public void setPreparedStatementWrapperClass(Class clazz)
     throws IllegalArgumentException{
        
        if(clazz != null
             && !(PreparedStatement.class.isAssignableFrom(clazz)
                    && StatementWrapper.class.isAssignableFrom(clazz))){
            throw new IllegalArgumentException(
                "Illegal class : " + clazz.getName()
            );
        }
        preparedStatementWrapperClass = clazz;
    }
    
    /**
     * ���b�v����{@link PreparedStatementWrapper}�̎����N���X���擾����B<p>
     *
     * @return ���b�v����PreparedStatementWrapper�̎����N���X
     */
    public Class getPreparedStatementWrapperClass(){
        return preparedStatementWrapperClass;
    }
    
    /**
     * ���b�v����{@link ResultSetWrapper}�̎����N���X��ݒ肷��B<p>
     *
     * @param clazz ���b�v����ResultSetWrapper�̎����N���X
     * @exception IllegalArgumentException �w�肵���N���X��ResultSetWrapper�̃T�u�N���X�łȂ��ꍇ
     */
    public void setResultSetWrapperClass(Class clazz)
     throws IllegalArgumentException{
        
        if(clazz != null
             && !ResultSetWrapper.class.isAssignableFrom(clazz)){
            throw new IllegalArgumentException(
                "Illegal class : " + clazz.getName()
            );
        }
        resultSetWrapperClass = clazz;
    }
    
    /**
     * ���b�v����{@link ResultSetWrapper}�̎����N���X���擾����B<p>
     *
     * @return ���b�v����ResultSetWrapper�̎����N���X
     */
    public Class getResultSetWrapperClass(){
        return resultSetWrapperClass;
    }
    
    /**
     * �S�Ă�Statement�Ƀv���p�e�B��ݒ肷��B<p>
     * {@link #setStatementProperties(Map)}�A{@link #setCallableStatementProperties(Map)}�A{@link #setPreparedStatementProperties(Map)}���Ăяo���B<br>
     *
     * @param props �v���p�e�B�}�b�v
     */
    public void setAllStatementProperties(Map props){
        setStatementProperties(props);
        setCallableStatementProperties(props);
        setPreparedStatementProperties(props);
    }
    
    /**
     * �S�Ă�Statement�Ƀv���p�e�B��ݒ肷��B<p>
     * {@link #setStatementProperty(String, Object)}�A{@link #setCallableStatementProperty(String, Object)}�A{@link #setPreparedStatementProperty(String, Object)}���Ăяo���B<br>
     *
     * @param name �v���p�e�B��
     * @param value �l
     */
    public void setAllStatementProperty(String name, Object value){
        setStatementProperty(name, value);
        setCallableStatementProperty(name, value);
        setPreparedStatementProperty(name, value);
    }
    
    /**
     * {@link StatementWrapper}�Ƀv���p�e�B��ݒ肷��B<p>
     *
     * @param props �v���p�e�B�}�b�v
     */
    public void setStatementProperties(Map props){
        if(props == null || props.size() == 0){
            if(statementProperties != null){
                statementProperties = null;
            }
            return;
        }
        final Iterator names = props.keySet().iterator();
        while(names.hasNext()){
            String name = (String)names.next();
            setStatementProperty(name, props.get(name));
        }
    }
    
    /**
     * {@link StatementWrapper}�Ƀv���p�e�B��ݒ肷��B<p>
     *
     * @param name �v���p�e�B��
     * @param value �l
     */
    public void setStatementProperty(String name, Object value){
        if(statementProperties == null){
            statementProperties = new LinkedHashMap();
        }
        final Property prop = PropertyFactory.createProperty(name);
        statementProperties.put(prop, value);
    }
    
    /**
     * {@link StatementWrapper}�̃v���p�e�B���擾����B<p>
     *
     * @param name �v���p�e�B��
     * @return �l
     */
    public Object getStatementProperty(String name){
        if(statementProperties == null){
            return null;
        }
        final Iterator props = statementProperties.keySet().iterator();
        while(props.hasNext()){
            final Property prop = (Property)props.next();
            if(prop.getPropertyName().equals(name)){
                return statementProperties.get(prop);
            }
        }
        return null;
    }
    
    /**
     * {@link CallableStatementWrapper}�Ƀv���p�e�B��ݒ肷��B<p>
     *
     * @param props �v���p�e�B�}�b�v
     */
    public void setCallableStatementProperties(Map props){
        if(props == null || props.size() == 0){
            if(callableStatementProperties != null){
                callableStatementProperties = null;
            }
            return;
        }
        final Iterator names = props.keySet().iterator();
        while(names.hasNext()){
            String name = (String)names.next();
            setCallableStatementProperty(name, props.get(name));
        }
    }
    
    /**
     * {@link CallableStatementWrapper}�Ƀv���p�e�B��ݒ肷��B<p>
     *
     * @param name �v���p�e�B��
     * @param value �l
     */
    public void setCallableStatementProperty(String name, Object value){
        if(callableStatementProperties == null){
            callableStatementProperties = new LinkedHashMap();
        }
        final Property prop = PropertyFactory.createProperty(name);
        callableStatementProperties.put(prop, value);
    }
    
    /**
     * {@link CallableStatementWrapper}�̃v���p�e�B���擾����B<p>
     *
     * @param name �v���p�e�B��
     * @return �l
     */
    public Object getCallableStatementProperty(String name){
        if(callableStatementProperties == null){
            return null;
        }
        final Iterator props = callableStatementProperties.keySet().iterator();
        while(props.hasNext()){
            final Property prop = (Property)props.next();
            if(prop.getPropertyName().equals(name)){
                return callableStatementProperties.get(prop);
            }
        }
        return null;
    }
    
    /**
     * {@link PreparedStatementWrapper}�Ƀv���p�e�B��ݒ肷��B<p>
     *
     * @param props �v���p�e�B�}�b�v
     */
    public void setPreparedStatementProperties(Map props){
        if(props == null || props.size() == 0){
            if(preparedStatementProperties != null){
                preparedStatementProperties = null;
            }
            return;
        }
        final Iterator names = props.keySet().iterator();
        while(names.hasNext()){
            String name = (String)names.next();
            setPreparedStatementProperty(name, props.get(name));
        }
    }
    
    /**
     * {@link PreparedStatementWrapper}�Ƀv���p�e�B��ݒ肷��B<p>
     *
     * @param name �v���p�e�B��
     * @param value �l
     */
    public void setPreparedStatementProperty(String name, Object value){
        if(preparedStatementProperties == null){
            preparedStatementProperties = new LinkedHashMap();
        }
        final Property prop = PropertyFactory.createProperty(name);
        preparedStatementProperties.put(prop, value);
    }
    
    /**
     * {@link PreparedStatementWrapper}�̃v���p�e�B���擾����B<p>
     *
     * @param name �v���p�e�B��
     * @return �l
     */
    public Object getPreparedStatementProperty(String name){
        if(preparedStatementProperties == null){
            return null;
        }
        final Iterator props = preparedStatementProperties.keySet().iterator();
        while(props.hasNext()){
            final Property prop = (Property)props.next();
            if(prop.getPropertyName().equals(name)){
                return preparedStatementProperties.get(prop);
            }
        }
        return null;
    }
    
    /**
     * {@link ResultSetWrapper}�Ƀv���p�e�B��ݒ肷��B<p>
     *
     * @param props �v���p�e�B�}�b�v
     */
    public void setResultSetProperties(Map props){
        if(props == null || props.size() == 0){
            if(resultSetProperties != null){
                resultSetProperties = null;
            }
            return;
        }
        final Iterator names = props.keySet().iterator();
        while(names.hasNext()){
            String name = (String)names.next();
            setResultSetProperty(name, props.get(name));
        }
    }
    
    /**
     * {@link ResultSetWrapper}�Ƀv���p�e�B��ݒ肷��B<p>
     *
     * @param name �v���p�e�B��
     * @param value �l
     */
    public void setResultSetProperty(String name, Object value){
        if(resultSetProperties == null){
            resultSetProperties = new LinkedHashMap();
        }
        resultSetProperties.put(name, value);
    }
    
    /**
     * {@link ResultSetWrapper}�̃v���p�e�B���擾����B<p>
     *
     * @param name �v���p�e�B��
     * @return �l
     */
    public Object getResultSetProperty(String name){
        if(resultSetProperties == null){
            return null;
        }
        return resultSetProperties.get(name);
    }
    
    protected Statement createStatementWrapper(Statement stmt)
     throws SQLException{
        if(statementWrapperClass == null){
            return stmt;
        }
        StatementWrapper result = null;
        try{
            final Constructor constructor
                 = statementWrapperClass.getConstructor(
                    new Class[]{
                        Connection.class,
                        Statement.class
                    }
                );
            result = (StatementWrapper)constructor.newInstance(
                new Object[]{this, stmt}
            );
            applyStatementProperties(result);
        }catch(InvocationTargetException e){
            throw new SQLException(e.getTargetException().toString());
        }catch(Exception e){
            throw new SQLException(e.toString());
        }
        return result;
    }
    
    protected void applyStatementProperties(StatementWrapper stmtw)
     throws Exception{
        applyStatementProperties(stmtw, statementProperties);
    }
    
    protected CallableStatement createCallableStatementWrapper(
        CallableStatement stmt,
        String sql
    ) throws SQLException{
        if(callableStatementWrapperClass == null){
            return stmt;
        }
        StatementWrapper result = null;
        try{
            final Constructor constructor
                 = callableStatementWrapperClass.getConstructor(
                    new Class[]{
                        Connection.class,
                        CallableStatement.class,
                        String.class
                    }
                );
            result = (StatementWrapper)constructor.newInstance(
                new Object[]{this, stmt, sql}
            );
            applyCallableStatementProperties(result);
        }catch(InvocationTargetException e){
            throw new SQLException(e.getTargetException().toString());
        }catch(Exception e){
            throw new SQLException(e.toString());
        }
        return (CallableStatement)result;
    }
    
    protected void applyCallableStatementProperties(StatementWrapper stmtw)
     throws Exception{
        applyStatementProperties(stmtw, callableStatementProperties);
    }
    
    protected PreparedStatement createPreparedStatementWrapper(
        PreparedStatement stmt,
        String sql
    ) throws SQLException{
        if(preparedStatementWrapperClass == null){
            return stmt;
        }
        StatementWrapper result = null;
        try{
            final Constructor constructor
                 = preparedStatementWrapperClass.getConstructor(
                    new Class[]{
                        Connection.class,
                        PreparedStatement.class,
                        String.class
                    }
                );
            result = (StatementWrapper)constructor.newInstance(
                new Object[]{this, stmt, sql}
            );
            applyPreparedStatementProperties(result);
        }catch(InvocationTargetException e){
            throw new SQLException(e.getTargetException().toString());
        }catch(Exception e){
            throw new SQLException(e.toString());
        }
        return (PreparedStatement)result;
    }
    
    protected void applyPreparedStatementProperties(StatementWrapper stmtw)
     throws Exception{
        applyStatementProperties(stmtw, preparedStatementProperties);
    }
    
    protected void applyStatementProperties(
        StatementWrapper stmt,
        Map properties
    ) throws Exception{
        if(resultSetWrapperClass != null){
            stmt.setResultSetWrapperClass(resultSetWrapperClass);
            if(resultSetProperties != null && resultSetProperties.size() != 0){
                final Iterator names = resultSetProperties.keySet().iterator();
                while(names.hasNext()){
                    final String name = (String)names.next();
                    stmt.setResultSetProperty(
                        name,
                        resultSetProperties.get(name)
                    );
                }
            }
        }
        if(properties != null && properties.size() != 0){
            final Iterator props = properties.keySet().iterator();
            while(props.hasNext()){
                final Property prop = (Property)props.next();
                prop.setProperty(stmt, properties.get(prop));
            }
        }
    }
    
    public int getHoldability() throws SQLException {
        return connection.getHoldability();
    }
    
    public int getTransactionIsolation() throws SQLException {
        return connection.getTransactionIsolation();
    }
    
    public void clearWarnings() throws SQLException {
        connection.clearWarnings();
    }
    
    public void close() throws SQLException {
        connection.close();
    }
    
    public void commit() throws SQLException {
        connection.commit();
    }
    
    public void rollback() throws SQLException {
        connection.rollback();
    }
    
    public boolean getAutoCommit() throws SQLException {
        return connection.getAutoCommit();
    }
    
    public boolean isClosed() throws SQLException {
        return connection.isClosed();
    }
    
    public boolean isReadOnly() throws SQLException {
        return connection.isReadOnly();
    }
    
    public void setHoldability(int arg0) throws SQLException {
        connection.setHoldability(arg0);
    }
    
    public void setTransactionIsolation(int arg0) throws SQLException {
        connection.setTransactionIsolation(arg0);
    }
    
    public void setAutoCommit(boolean arg0) throws SQLException {
        connection.setAutoCommit(arg0);
    }
    
    public void setReadOnly(boolean arg0) throws SQLException {
        connection.setReadOnly(arg0);
    }
    
    public String getCatalog() throws SQLException {
        return connection.getCatalog();
    }
    
    public void setCatalog(String arg0) throws SQLException {
        connection.setCatalog(arg0);
    }
    
    public DatabaseMetaData getMetaData() throws SQLException {
        return connection.getMetaData();
    }
    
    public SQLWarning getWarnings() throws SQLException {
        return connection.getWarnings();
    }
    
    public Savepoint setSavepoint() throws SQLException {
        return connection.setSavepoint();
    }
    
    public void releaseSavepoint(Savepoint arg0) throws SQLException {
        connection.releaseSavepoint(arg0);
    }
    
    public void rollback(Savepoint arg0) throws SQLException {
        connection.rollback(arg0);
    }
    
    public Statement createStatement() throws SQLException {
        return createStatementWrapper(connection.createStatement());
    }
    
    public Statement createStatement(int arg0, int arg1) throws SQLException {
        return createStatementWrapper(connection.createStatement(arg0, arg1));
    }
    
    public Statement createStatement(int arg0, int arg1, int arg2)
     throws SQLException {
        return createStatementWrapper(
            connection.createStatement(arg0, arg1, arg2)
        );
    }
    
    public Map getTypeMap() throws SQLException {
        return connection.getTypeMap();
    }
    
    public void setTypeMap(Map arg0) throws SQLException {
        connection.setTypeMap(arg0);
    }
    
    public String nativeSQL(String arg0) throws SQLException {
        return connection.nativeSQL(arg0);
    }
    
    public CallableStatement prepareCall(String arg0) throws SQLException {
        return createCallableStatementWrapper(
            connection.prepareCall(arg0),
            arg0
        );
    }
    
    public CallableStatement prepareCall(String arg0, int arg1, int arg2)
            throws SQLException {
        return createCallableStatementWrapper(
            connection.prepareCall(arg0, arg1, arg2),
            arg0
        );
    }
    
    public CallableStatement prepareCall(String arg0, int arg1, int arg2,
            int arg3) throws SQLException {
        return createCallableStatementWrapper(
            connection.prepareCall(arg0, arg1, arg2, arg3),
            arg0
        );
    }
    
    public PreparedStatement prepareStatement(String arg0) throws SQLException {
        return createPreparedStatementWrapper(
            connection.prepareStatement(arg0),
            arg0
        );
    }
    
    public PreparedStatement prepareStatement(String arg0, int arg1)
     throws SQLException {
        return createPreparedStatementWrapper(
            connection.prepareStatement(arg0, arg1),
            arg0
        );
    }
    
    public PreparedStatement prepareStatement(String arg0, int arg1, int arg2)
     throws SQLException {
        return createPreparedStatementWrapper(
            connection.prepareStatement(arg0, arg1, arg2),
            arg0
        );
    }
    
    public PreparedStatement prepareStatement(
        String arg0,
        int arg1,
        int arg2,
        int arg3
    ) throws SQLException {
        return createPreparedStatementWrapper(
            connection.prepareStatement(arg0, arg1, arg2, arg3),
            arg0
        );
    }
    
    public PreparedStatement prepareStatement(String arg0, int[] arg1)
     throws SQLException {
        return createPreparedStatementWrapper(
            connection.prepareStatement(arg0, arg1),
            arg0
        );
    }
    
    public PreparedStatement prepareStatement(String arg0, String[] arg1)
     throws SQLException {
        return createPreparedStatementWrapper(
            connection.prepareStatement(arg0, arg1),
            arg0
        );
    }
    
    public Savepoint setSavepoint(String arg0) throws SQLException {
        return connection.setSavepoint(arg0);
    }
    
    

    public Struct createStruct(String typeName, Object[] attributes) throws SQLException{
        return connection.createStruct(typeName, attributes);
    }
    
    public Array createArrayOf(String typeName, Object[] elements) throws SQLException{
        return connection.createArrayOf(typeName, elements);
    }
    
    public Properties getClientInfo() throws SQLException{
        return connection.getClientInfo();
    }
    
    public String getClientInfo(String name) throws SQLException{
        return connection.getClientInfo(name);
    }
    
    public void setClientInfo(Properties properties) throws SQLClientInfoException{
        connection.setClientInfo(properties);
    }
    
    public void setClientInfo(String name, String value) throws SQLClientInfoException{
        connection.setClientInfo(name, value);
    }
    
    public boolean isValid(int timeout) throws SQLException{
        return connection.isValid(timeout);
    }
    
    public SQLXML createSQLXML() throws SQLException{
        return connection.createSQLXML();
    }
    
    public Blob createBlob() throws SQLException{
        return connection.createBlob();
    }
    
    public Clob createClob() throws SQLException{
        return connection.createClob();
    }
    
    public NClob createNClob() throws SQLException{
        return connection.createNClob();
    }
    
    public boolean isWrapperFor(Class<?> iface) throws SQLException{
        return connection.isWrapperFor(iface);
    }
    
    public <T> T unwrap(Class<T> iface) throws SQLException{
        return connection.unwrap(iface);
    }

    

    public void setSchema(String schema) throws SQLException{
        connection.setSchema(schema);
    }
    
    public String getSchema() throws SQLException{
        return connection.getSchema();
    }
    
    public void abort(java.util.concurrent.Executor executor) throws SQLException{
        connection.abort(executor);
    }
    
    public void setNetworkTimeout(java.util.concurrent.Executor executor, int milliseconds) throws SQLException{
        connection.setNetworkTimeout(executor, milliseconds);
    }
    
    public int getNetworkTimeout() throws SQLException{
        return connection.getNetworkTimeout();
    }

    
    protected void finalize() throws Throwable{
        try{
            if(!isClosed()){
                close();
            }
        }catch(SQLException e){
        }
        super.finalize();
    }
}
