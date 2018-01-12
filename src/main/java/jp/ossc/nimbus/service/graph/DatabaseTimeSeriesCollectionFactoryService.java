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
package jp.ossc.nimbus.service.graph;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ParameterMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;

import jp.ossc.nimbus.core.ServiceManagerFactory;
import jp.ossc.nimbus.core.ServiceName;
import jp.ossc.nimbus.service.connection.ConnectionFactory;
import jp.ossc.nimbus.service.connection.ConnectionFactoryException;

/**
 * �f�[�^�x�[�XTimeSeriesCollection�t�@�N�g���T�[�r�X�B<p>
 *
 * @author M.Takata
 */
public class DatabaseTimeSeriesCollectionFactoryService
    extends TimeSeriesCollectionFactoryService
    implements DatabaseTimeSeriesCollectionFactoryServiceMBean{
    
    private static final long serialVersionUID = 62063250205247679L;
    
    // �萔
    /** �Z�p���[�^ [=] */
    protected static final String SEPARATOR = "=";
    
    /** �R�l�N�V�����t�@�N�g�� */
    protected ConnectionFactory connFactory;
    /** [�V���[�Y��=SQL]�̕�����z�� */
    protected String[] sqls;
    /** �f�[�^�Z�b�g�����̃��X�g */
    protected List dsConditionList;
    /** �L�[�ɃV���[�Y���A�l��SQL�̃}�b�v */
    protected Map seriesSqlMap;
    /** �t�F�b�`�T�C�Y */
    protected int fetchSize = DEFAULT_FETCH_SIZE;
    
    /** �J������ : ���t */
    protected String dateColumnName;
    /** �J������ : ���� */
    protected String timeColumnName;
    /** �J������ : �l */
    protected String valueColumnName;
    
    /** �J�����C���f�b�N�X : ���t */
    protected int dateColumnIndex = -1;
    /** �J�����C���f�b�N�X : ���� */
    protected int timeColumnIndex = -1;
    /** �J�����C���f�b�N�X : �l */
    protected int valueColumnIndex = -1;
    
    /** ���t�t�H�[�}�b�g�p�^�[�� */
    protected String dateFormatPattern;
    /** ���t�t�H�[�}�b�g�T�[�r�X�� */
    protected ServiceName dateFormatServiceName;
    
    // DatabaseTimeSeriesCollectionFactoryServiceMBean��JavaDoc
    public void setConnectionFactory(ConnectionFactory connFactory){
        this.connFactory = connFactory;
    }
    
    // DatabaseTimeSeriesCollectionFactoryServiceMBean��JavaDoc
    public ConnectionFactory getConnectionFactory(){
        return connFactory;
    }
    
    // DatabaseTimeSeriesCollectionFactoryServiceMBean��JavaDoc
    public void setSqls(String[] sqls){
        this.sqls = sqls;
    }
    
    // DatabaseTimeSeriesCollectionFactoryServiceMBean��JavaDoc
    public String[] getSqls(){
        return sqls;
    }
    
    // DatabaseTimeSeriesCollectionFactoryServiceMBean��JavaDoc
    public void setFetchSize(int size){
        fetchSize = size;
    }
    
    // DatabaseTimeSeriesCollectionFactoryServiceMBean��JavaDoc
    public int getFetchSize(){
        return fetchSize;
    }
    
    // DatabaseTimeSeriesCollectionFactoryServiceMBean��JavaDoc
    public void setDateFormatPattern(String pattern){
        dateFormatPattern = pattern;
    }
    
    // DatabaseTimeSeriesCollectionFactoryServiceMBean��JavaDoc
    public String getDateFormatPattern(){
        return dateFormatPattern;
    }
    
    // DatabaseTimeSeriesCollectionFactoryServiceMBean��JavaDoc
    public void setDateFormatServiceName(ServiceName serviceName){
        dateFormatServiceName = serviceName;
    }
    
    // DatabaseTimeSeriesCollectionFactoryServiceMBean��JavaDoc
    public ServiceName getDateFormatServiceName(){
        return dateFormatServiceName;
    }
    
    // DatabaseTimeSeriesCollectionFactoryServiceMBean��JavaDoc
    public void setDateColumnName(String columnName){
        dateColumnName = columnName;
    }
    
    // DatabaseTimeSeriesCollectionFactoryServiceMBean��JavaDoc
    public String getDateColumnName(){
        return dateColumnName;
    }
    
    // DatabaseTimeSeriesCollectionFactoryServiceMBean��JavaDoc
    public void setDateColumnIndex(int index){
        dateColumnIndex = index;
    }
    
    // DatabaseTimeSeriesCollectionFactoryServiceMBean��JavaDoc
    public int getDateColumnIndex(){
        return dateColumnIndex;
    }
    
    // DatabaseTimeSeriesCollectionFactoryServiceMBean��JavaDoc
    public void setTimeColumnName(String columnName){
        timeColumnName = columnName;
    }
    
    // DatabaseTimeSeriesCollectionFactoryServiceMBean��JavaDoc
    public String getTimeColumnName(){
        return timeColumnName;
    }
    
    // DatabaseTimeSeriesCollectionFactoryServiceMBean��JavaDoc
    public void setTimeColumnIndex(int index){
        timeColumnIndex = index;
    }
    
    // DatabaseTimeSeriesCollectionFactoryServiceMBean��JavaDoc
    public int getTimeColumnIndex(){
        return timeColumnIndex;
    }
    
    // DatabaseTimeSeriesCollectionFactoryServiceMBean��JavaDoc
    public void setValueColumnName(String columnName){
        valueColumnName = columnName;
    }
    
    // DatabaseTimeSeriesCollectionFactoryServiceMBean��JavaDoc
    public String getValueColumnName(){
        return valueColumnName;
    }
    
    // DatabaseTimeSeriesCollectionFactoryServiceMBean��JavaDoc
    public void setValueColumnIndex(int index){
        valueColumnIndex = index;
    }
    
    // DatabaseTimeSeriesCollectionFactoryServiceMBean��JavaDoc
    public int getValueColumnIndex(){
        return valueColumnIndex;
    }
    
    // DatabaseTimeSeriesCollectionFactoryServiceMBean��JavaDoc
    public void addDatasetCondition(DatasetCondition dsCondition){
        dsConditionList.add(dsCondition);
    }
    
    // DatabaseTimeSeriesCollectionFactoryServiceMBean��JavaDoc
    public DatasetCondition[] getDatasetConditions(){
        return (DatasetCondition[]) dsConditionList.toArray(
            new DatasetCondition[dsConditionList.size()]
        );
    }
    
    // ServiceBase��JavaDoc
    public void createService() throws Exception{
        dsConditionList = new ArrayList();
        seriesSqlMap = new LinkedHashMap();
    }
    
    // ServiceBase��JavaDoc
    public void startService() throws Exception{
        
        if(connFactory == null){
            throw new IllegalArgumentException(
                "ConnectionFactory is null."
            );
        }
        
        if(sqls == null || sqls.length == 0){
            throw new IllegalArgumentException(
                "sqls must be specified."
            );
        }
        
        for(int i = 0; i < sqls.length; i++){
            String seriesSql = sqls[i];
            
            int index = seriesSql.indexOf(SEPARATOR);
            if(index == -1){
                throw new IllegalArgumentException("sqls is invalid." + seriesSql);
            }
            
            String seriesName = seriesSql.substring(0, index);
            String sql = seriesSql.substring(index + 1);
            // �L�[�ɃV���[�Y��, �l��SQL
            seriesSqlMap.put(seriesName, sql);
        }
        
        if(dateFormatPattern != null){
            new SimpleDateFormat(dateFormatPattern);
        }
        
        if((dateColumnName == null && dateColumnIndex <= 0)
            && (timeColumnName == null && timeColumnIndex <= 0)){
            throw new IllegalArgumentException(
                "dateColumnName or dateColumnIndex or timeColumnName or timeColumnIndex must be specified."
            );
        }
        
        if(valueColumnName == null && valueColumnIndex <= 0){
            throw new IllegalArgumentException(
                "valueColumnName or valueColumnIndex must be specified."
            );
        }
    }
    
    // ServiceBase��JavaDoc
    public void stopService() throws Exception{
        seriesSqlMap.clear();
    }
    
    // ServiceBase��JavaDoc
    public void destroyService() throws Exception{
        dsConditionList = null;
        seriesSqlMap = null;
    }
    
    protected DatasetConnection createConnection(DatasetCondition[] dsConditions)
     throws DatasetCreateException{
        
        DateFormat dateFormat = null;
        if(dateFormatServiceName != null){
            dateFormat = (DateFormat)ServiceManagerFactory.getServiceObject(dateFormatServiceName);
        }else if(dateFormatPattern != null){
            dateFormat = new SimpleDateFormat(dateFormatPattern);
        }
        
        // �R�l�N�V�������擾
        Connection conn = null;
        try{
            conn = connFactory.getConnection();
        }catch(ConnectionFactoryException e){
            // �R�l�N�V�����擾���s
            throw new DatasetCreateException("Dataset [" + getName() + "]", e);
        }
        DatasetConnection connection = new DatabaseTimeSeriesDatasetConnection(
            getName(),
            conn
        );
        
        Iterator itr = seriesSqlMap.keySet().iterator();
        while(itr.hasNext()){
            // �V���[�Y
            String series = (String)itr.next();
            DatabaseTimeSeriesCursor cursor = new DatabaseTimeSeriesCursor(
                series,
                conn,
                (String)seriesSqlMap.get(series),
                dateFormat
            );
            for(int i = 0, imax = dsConditionList.size(); i < imax; i++){
                cursor.addCondition((DatasetCondition)dsConditionList.get(i));
            }
            if(dsConditions != null){
                for(int i = 0; i < dsConditions.length; i++){
                    cursor.addCondition(dsConditions[i]);
                }
            }
            cursor.execute();
            connection.addSeriesCursor(cursor);
        }
        return connection;
    }
    
    protected class DatabaseTimeSeriesDatasetConnection extends DatasetConnection{
        protected Connection connection;
        
        public DatabaseTimeSeriesDatasetConnection(String datasetName, Connection con){
            super(datasetName);
            connection = con;
        }
        
        public void close(){
            super.close();
            if(connection != null){
                try{
                    connection.close();
                }catch(SQLException e){
                }
                connection = null;
            }
        }
    }
    
    protected class DatabaseTimeSeriesCursor extends TimeSeriesCursor{
        
        protected PreparedStatement pstmt;
        protected DateFormat dateFormat;
        protected ResultSet rs;
        
        public DatabaseTimeSeriesCursor(
            String seriesName,
            Connection conn,
            String sql,
            DateFormat dateFormat
        ) throws DatasetCreateException{
            super(seriesName);
            try{
                pstmt = conn.prepareStatement(
                    sql,
                    ResultSet.TYPE_FORWARD_ONLY,
                    ResultSet.CONCUR_READ_ONLY
                );
                pstmt.setFetchSize(fetchSize);
                pstmt.setFetchDirection(ResultSet.FETCH_FORWARD);
            }catch(SQLException e){
                throw new DatasetCreateException("Dataset[" + dataSetName + ", " + seriesName + "]", e);
            }
            
            this.dateFormat = dateFormat;
        }
        
        public boolean addCondition(DatasetCondition condition) throws DatasetCreateException{
            if(!super.addCondition(condition)){
                return false;
            }
            if(condition instanceof DatabaseDatasetCondition){
                DatabaseDatasetCondition dbDsCondition = (DatabaseDatasetCondition)condition;
                try{
                    // �p�����[�^���^�f�[�^
                    ParameterMetaData paramMetaData = pstmt.getParameterMetaData();
                    if(paramMetaData == null){
                        throw new DatasetCreateException(
                            "ParameterMetaData is null."
                        );
                    }
                    
                    // �l��PreparedStatement�ɐݒ�
                    for(int i = 0, imax = paramMetaData.getParameterCount(); i < imax; i++){
                        Object paramObj = dbDsCondition.getParamObject(i);
                        if(paramObj != null){
                            pstmt.setObject(i + 1, paramObj);
                        }
                    }
                }catch(SQLException e){
                    throw new DatasetCreateException(e);
                }
            }
            return true;
        }
        
        public void execute() throws DatasetCreateException{
            try{
                rs = pstmt.executeQuery();
            }catch(SQLException e){
                throw new DatasetCreateException("Dataset[" + dataSetName + ", " + seriesName + "]", e);
            }
        }
        
        public boolean next() throws DatasetCreateException{
            try{
                return rs.next();
            }catch(SQLException e){
                throw new DatasetCreateException("Dataset[" + dataSetName + ", " + seriesName + "]", e);
            }
        }
        
        public Date getDate() throws DatasetCreateException{
            Date date = null;
            try{
                if(dateFormat != null){
                    String dateStr = null;
                    
                    String dateVal = null;
                    String timeVal = null;
                    if(dateColumnIndex > 0){
                        dateVal = rs.getString(dateColumnIndex);
                    }else if (dateColumnName != null){
                        dateVal = rs.getString(dateColumnName);
                    }
                    
                    if(timeColumnIndex > 0){
                        timeVal = rs.getString(timeColumnIndex);
                    }else if (timeColumnName != null){
                        timeVal = rs.getString(timeColumnName);
                    }
                    
                    boolean isTimeOnly = false;
                    if(dateVal != null && timeVal != null){
                        dateStr = dateVal + timeVal;
                    }else if (dateVal != null){
                        dateStr = dateVal;
                    }else{
                        dateStr = timeVal;
                        isTimeOnly = true;
                    }
                    
                    date = dateFormat.parse(dateStr);
                    if(isTimeOnly){
                        // �����݂̂������ꍇ�A���t�������ɐݒ�
                        Calendar cal = Calendar.getInstance();
                        int year = cal.get(Calendar.YEAR);
                        int month = cal.get(Calendar.MONTH);
                        int day = cal.get(Calendar.DAY_OF_MONTH);
                        
                        cal.clear();
                        cal.setTime(date);
                        cal.set(Calendar.YEAR, year);
                        cal.set(Calendar.MONTH, month);
                        cal.set(Calendar.DAY_OF_MONTH, day);
                        date = cal.getTime();
                    }
                }else{
                    if(dateColumnIndex > 0){
                        date = rs.getDate(dateColumnIndex);
                    }else if(dateColumnName != null){
                        date = rs.getDate(dateColumnName);
                    }
                }
            }catch(ParseException e){
                throw new DatasetCreateException("Dataset[" + dataSetName + ", " + seriesName + "]", e);
            }catch(SQLException e){
                throw new DatasetCreateException("Dataset[" + dataSetName + ", " + seriesName + "]", e);
            }
            return date;
        }
        
        public double getValue() throws DatasetCreateException{
            double value = Double.NaN;
            try{
                if(valueColumnIndex > 0){
                    value = rs.getDouble(valueColumnIndex);
                }else{
                    value = rs.getDouble(valueColumnName);
                }
            }catch(SQLException e){
                throw new DatasetCreateException("Dataset[" + dataSetName + ", " + seriesName + "]", e);
            }
            return value;
        }
        
        public boolean wasNull() throws DatasetCreateException{
            try{
                return rs.wasNull();
            }catch(SQLException e){
                throw new DatasetCreateException("Dataset[" + dataSetName + ", " + seriesName + "]", e);
            }
        }
        
        public void close(){
            if(pstmt != null){
                try{
                    pstmt.close();
                }catch(SQLException e){
                }
                pstmt = null;
            }
            if(rs != null){
                try{
                    rs.close();
                }catch(SQLException e){
                }
                rs = null;
            }
            super.close();
        }
    }
}
