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
package jp.ossc.nimbus.service.validator;

import java.util.*;
import java.sql.*;
import java.lang.reflect.InvocationTargetException;

import jp.ossc.nimbus.beans.*;
import jp.ossc.nimbus.beans.dataset.RecordList;
import jp.ossc.nimbus.beans.dataset.Record;
import jp.ossc.nimbus.beans.dataset.PropertySchema;
import jp.ossc.nimbus.core.*;
import jp.ossc.nimbus.recset.RecordSet;
import jp.ossc.nimbus.service.connection.ConnectionFactory;
import jp.ossc.nimbus.service.connection.ConnectionFactoryException;
import jp.ossc.nimbus.service.connection.PersistentManager;
import jp.ossc.nimbus.service.connection.PersistentException;
import jp.ossc.nimbus.service.context.Context;
import jp.ossc.nimbus.service.codemaster.CodeMasterFinder;
import jp.ossc.nimbus.util.validator.Validator;
import jp.ossc.nimbus.util.validator.ValidateException;

/**
 * �}�X�^�o���f�[�^�B<p>
 * DB�̃}�X�^�Ɋ܂܂�Ă��邩�����؂���B<br>
 * �}�X�^�̎擾���@�Ƃ��đ傫���Q��ނ���A�}�X�^�̍X�V�p�x�ɉ����đI���\�ł���B<br>
 * <p>
 * �}�X�^�̍X�V�p�x�������ꍇ�́A���؂̓s�x�}�X�^����������K�v������B<br>
 * ���̂悤�ȏꍇ�́A{@link #setRecordSet(RecordSet)}�A{@link #setConnectionFactoryServiceName(ServiceName)}�A{@link #setBindData(int, String)}��ݒ肷��B<br>
 * �܂��́A{@link #setQuery(String)}�A{@link #setConnectionFactoryServiceName(ServiceName)}�A{@link #setPersistentManagerServiceName(ServiceName)}��ݒ肷��B<br>
 * <p>
 * �}�X�^�̍X�V�p�x���Ⴂ�ꍇ�́A���؂̓s�x�}�X�^����������K�v���Ȃ��B<br>
 * ���̂悤�ȃ}�X�^���g�p����ꍇ�ANimbus�ł͒ʏ�{@link CodeMasterFinder �R�[�h�}�X�^}�ɁA�}�X�^RecordSet�܂���RecordList��o�^���Ă������ŁA�}�X�^�𖈉񌟍����Ȃ��悤�ɂ���B<br>
 * ���̋@�\�𗘗p���鎖�ŁA���؂̓s�x�}�X�^�����������ɁA�R�[�h�}�X�^��̃}�X�^RecordSet�܂���RecordList�ɑ΂��ē��I�������s�����؂��邱�Ƃ��ł���B<br>
 * �R�[�h�}�X�^�̎擾���@�ɂ͂Q��ނ���A�ǂݎ���ѐ��ۏႷ��ꍇ�́A�X���b�h�R���e�L�X�g����擾����B���̏ꍇ�A{@link #setThreadContextServiceName(ServiceName)}�A{@link #setCodeMasterThreadContextKey(String)}��ݒ肷��B<br>
 * �܂��A�ǂݎ���ѐ��ۏ������K�v���Ȃ��ꍇ�́A����{@link CodeMasterFinder}����擾���鎖���\�ł���B���̏ꍇ�A{@link #setCodeMasterFinderServiceName(ServiceName)}��ݒ肷��B<br>
 * �擾�����R�[�h�}�X�^����}�X�^RecordSet�܂���RecordList����肷�邽�߂ɁA{@link #setCodeMasterName(String)}��ݒ肷��B<br>
 * �}�X�^RecordSet�܂���RecordList���瓮�I�������s���ۂ̌��������́A���ؒl���C�ӂ̏����ɍ��v���郌�R�[�h�����邩�ǂ��������؂��鎖���\�ŁA���̏ꍇ�́A{@link #setRecordSetSearchCondition(String)}�A{@link #setBindDataMap(String, String)}��ݒ肷��B<br>
 * 
 * @author M.Takata
 */
public class MasterValidatorService extends ServiceBase
 implements Validator, MasterValidatorServiceMBean{
    
    private static final long serialVersionUID = 3833661471756025996L;
    private ServiceName connectionFactoryServiceName;
    private ConnectionFactory connectionFactory;
    private ServiceName persistentManagerServiceName;
    private PersistentManager persistentManager;
    private String query;
    private RecordSet templateRecordSet;
    private List bindDataList;
    
    private ServiceName codeMasterFinderServiceName;
    private CodeMasterFinder codeMasterFinder;
    
    private ServiceName threadContextServiceName;
    private Context threadContext;
    private String codeMasterThreadContextKey
         = jp.ossc.nimbus.service.aop.interceptor.ThreadContextKey.CODEMASTER;
    
    private String codeMasterName;
    private String searchCondition;
    private Map bindDataMap;
    
    // MasterValidatorServiceMBean��JavaDoc
    public void setConnectionFactoryServiceName(ServiceName name){
        connectionFactoryServiceName = name;
    }
    // MasterValidatorServiceMBean��JavaDoc
    public ServiceName getConnectionFactoryServiceName(){
        return connectionFactoryServiceName;
    }
    
    // MasterValidatorServiceMBean��JavaDoc
    public void setPersistentManagerServiceName(ServiceName name){
        persistentManagerServiceName = name;
    }
    // MasterValidatorServiceMBean��JavaDoc
    public ServiceName getPersistentManagerServiceName(){
        return persistentManagerServiceName;
    }
    
    // MasterValidatorServiceMBean��JavaDoc
    public void setQuery(String query){
        this.query = query;
    }
    // MasterValidatorServiceMBean��JavaDoc
    public String getQuery(){
        return query;
    }
    
    // MasterValidatorServiceMBean��JavaDoc
    public void setRecordSet(RecordSet recset){
        templateRecordSet = recset;
    }
    // MasterValidatorServiceMBean��JavaDoc
    public RecordSet getRecordSet(){
        return templateRecordSet;
    }
    
    // MasterValidatorServiceMBean��JavaDoc
    public void setBindData(int index, String valueKey){
        if(bindDataList == null){
            bindDataList = new ArrayList();
        }
        for(int i = bindDataList.size(); i <= index; i++){
            bindDataList.add(null);
        }
        if(!valueKey.startsWith(BIND_DATA_VALUE_KEY)){
            throw new IllegalArgumentException("ValueKey must start with 'VALUE'.");
        }
        if(!valueKey.equals(BIND_DATA_VALUE_KEY)){
            valueKey = valueKey.substring(BIND_DATA_VALUE_KEY.length());
            if(valueKey.charAt(0) == '.'){
                valueKey = valueKey.substring(1);
            }
            bindDataList.set(index, PropertyFactory.createProperty(valueKey));
        }
    }
    // MasterValidatorServiceMBean��JavaDoc
    public String getBindData(int index){
        if(bindDataList == null || bindDataList.size() <= index){
            return null;
        }
        final Property prop = (Property)bindDataList.get(index);
        return prop == null ? BIND_DATA_VALUE_KEY
             : BIND_DATA_VALUE_KEY + '.' + prop.getPropertyName();
    }
    
    // MasterValidatorServiceMBean��JavaDoc
    public void setCodeMasterFinderServiceName(ServiceName name){
        codeMasterFinderServiceName = name;
    }
    // MasterValidatorServiceMBean��JavaDoc
    public ServiceName getCodeMasterFinderServiceName(){
        return codeMasterFinderServiceName;
    }
    
    // MasterValidatorServiceMBean��JavaDoc
    public void setThreadContextServiceName(ServiceName name){
        threadContextServiceName = name;
    }
    // MasterValidatorServiceMBean��JavaDoc
    public ServiceName getThreadContextServiceName(){
        return threadContextServiceName;
    }
    
    // MasterValidatorServiceMBean��JavaDoc
    public void setCodeMasterThreadContextKey(String key){
        codeMasterThreadContextKey = key;
    }
    // MasterValidatorServiceMBean��JavaDoc
    public String getCodeMasterThreadContextKey(){
        return codeMasterThreadContextKey;
    }
    
    // MasterValidatorServiceMBean��JavaDoc
    public void setCodeMasterName(String name){
        codeMasterName = name;
    }
    // MasterValidatorServiceMBean��JavaDoc
    public String getCodeMasterName(){
        return codeMasterName;
    }
    
    // MasterValidatorServiceMBean��JavaDoc
    public void setRecordSetSearchCondition(String condition){
        searchCondition = condition;
    }
    // MasterValidatorServiceMBean��JavaDoc
    public String getRecordSetSearchCondition(){
        return searchCondition;
    }
    
    // MasterValidatorServiceMBean��JavaDoc
    public void setSearchCondition(String condition){
        searchCondition = condition;
    }
    // MasterValidatorServiceMBean��JavaDoc
    public String getSearchCondition(){
        return searchCondition;
    }
    
    // MasterValidatorServiceMBean��JavaDoc
    public void setBindDataMap(String key, String valueKey){
        if(bindDataMap == null){
            bindDataMap = new HashMap();
        }
        if(!valueKey.startsWith(BIND_DATA_VALUE_KEY)){
            throw new IllegalArgumentException("ValueKey must start with 'VALUE'.");
        }
        if(valueKey.equals(BIND_DATA_VALUE_KEY)){
            bindDataMap.put(key, null);
        }else{
            valueKey = valueKey.substring(BIND_DATA_VALUE_KEY.length());
            if(valueKey.charAt(0) == '.'){
                valueKey = valueKey.substring(1);
            }
            bindDataMap.put(key, PropertyFactory.createProperty(valueKey));
        }
    }
    // MasterValidatorServiceMBean��JavaDoc
    public String getBindDataMap(String key){
        if(bindDataMap == null){
            return null;
        }
        final Property prop = (Property)bindDataMap.get(key);
        return prop == null ? BIND_DATA_VALUE_KEY
             : BIND_DATA_VALUE_KEY + '.' + prop.getPropertyName();
    }
    
    /**
     * �T�[�r�X�̊J�n�������s���B<p>
     *
     * @exception Exception �T�[�r�X�̊J�n�����Ɏ��s�����ꍇ
     */
    public void startService() throws Exception{
        if(connectionFactoryServiceName != null){
            connectionFactory = (ConnectionFactory)ServiceManagerFactory
                .getServiceObject(connectionFactoryServiceName);
            if(persistentManagerServiceName == null){
                if(templateRecordSet == null){
                    throw new IllegalArgumentException("RecordSet must be specified.");
                }
                if(bindDataList == null){
                    throw new IllegalArgumentException("BindData must be specified.");
                }
            }else{
                persistentManager = (PersistentManager)ServiceManagerFactory
                    .getServiceObject(persistentManagerServiceName);
                
                if(query == null){
                    throw new IllegalArgumentException("Query must be specified.");
                }
            }
        }else{
            if(codeMasterFinderServiceName != null){
                codeMasterFinder = (CodeMasterFinder)ServiceManagerFactory
                    .getServiceObject(codeMasterFinderServiceName);
            }
            if(threadContextServiceName != null){
                threadContext = (Context)ServiceManagerFactory
                    .getServiceObject(threadContextServiceName);
            }
            if(codeMasterFinder == null && threadContext == null){
                throw new IllegalArgumentException("It is necessary to set either of CodeMasterFinder or ThreadContext.");
            }
            if(codeMasterName == null){
                throw new IllegalArgumentException("CodeMasterName must be specified.");
            }
            if(searchCondition != null){
                if(bindDataMap == null || bindDataMap.size() == 0){
                    throw new IllegalArgumentException("BindDataMap must be specified.");
                }
            }
        }
    }
    
    /**
     * �w�肳�ꂽ�I�u�W�F�N�g���}�X�^�Ɋ܂܂�Ă��邩�����؂���B<p>
     *
     * @param obj ���ؑΏۂ̃I�u�W�F�N�g
     * @return ���،��ʁB���ؐ����̏ꍇtrue
     * @exception ValidateException ���؂Ɏ��s�����ꍇ
     */
    public boolean validate(Object obj) throws ValidateException{
        if(connectionFactory != null){
            if(persistentManager != null){
                Connection con = null;
                try{
                    con = connectionFactory.getConnection();
                    final List result = (List)persistentManager.loadQuery(con, query, obj, null);
                    if(result.size() == 0){
                        return false;
                    }
                    final Collection values = ((Map)result.get(0)).values();
                    if(values.size() == 0){
                        return false;
                    }
                    if(values.size() == 1){
                        final Object value = values.iterator().next();
                        if(value instanceof Boolean){
                            return ((Boolean)value).booleanValue();
                        }else if(value instanceof Number){
                            return ((Number)value).intValue() != 0;
                        }else{
                            return true;
                        }
                    }else{
                        return true;
                    }
                }catch(ConnectionFactoryException e){
                    throw new ValidateException(e);
                }catch(PersistentException e){
                    throw new ValidateException(e);
                }finally{
                    if(con != null){
                        try{
                            con.close();
                        }catch(SQLException e){
                        }
                    }
                }
            }else{
                final RecordSet recset = templateRecordSet.cloneEmpty();
                Connection con = null;
                try{
                    con = connectionFactory.getConnection();
                    recset.setConnection(con);
                    recset.setLogger(getLogger());
                    for(int i = 0, imax = bindDataList.size(); i < imax; i++){
                        final Property prop = (Property)bindDataList.get(i);
                        if(prop == null){
                            recset.setBindData(i, obj);
                        }else{
                            recset.setBindData(i, prop.getProperty(obj));
                        }
                    }
                    return recset.search() != 0;
                }catch(ConnectionFactoryException e){
                    throw new ValidateException(e);
                }catch(NoSuchPropertyException e){
                    throw new ValidateException(e);
                }catch(InvocationTargetException e){
                    throw new ValidateException(e.getCause());
                }catch(SQLException e){
                    throw new ValidateException(e);
                }finally{
                    if(con != null){
                        try{
                            con.close();
                        }catch(SQLException e){
                        }
                    }
                }
            }
        }else{
            Map codeMaster = null;
            if(threadContext != null){
                codeMaster = (Map)threadContext.get(codeMasterThreadContextKey);
            }
            if(codeMaster == null && codeMasterFinder != null){
                codeMaster = codeMasterFinder.getCodeMasters();
            }
            if(codeMaster == null){
                throw new ValidateException("CodeMaster is not found.");
            }
            final Object master = codeMaster.get(codeMasterName);
            if(master == null){
                throw new ValidateException("Master '" + codeMasterName + "' is not found.");
            }
            if(master instanceof RecordSet){
                final RecordSet recset = (RecordSet)master;
                if(searchCondition != null){
                    final Map params = new HashMap();
                    final Iterator entries = bindDataMap.entrySet().iterator();
                    try{
                        while(entries.hasNext()){
                            final Map.Entry entry = (Map.Entry)entries.next();
                            final String key = (String)entry.getKey();
                            final Property prop = (Property)entry.getValue();
                            if(prop == null){
                                params.put(key, obj);
                            }else{
                                params.put(key, prop.getProperty(obj));
                            }
                        }
                    }catch(NoSuchPropertyException e){
                        throw new ValidateException(e);
                    }catch(InvocationTargetException e){
                        throw new ValidateException(e.getCause());
                    }
                    try{
                        return recset.searchDynamicConditionReal(
                            searchCondition,
                            params
                        ).size() != 0;
                    }catch(Exception e){
                        throw new ValidateException(e);
                    }
                }else{
                    return recset.get(obj == null ? null : obj.toString()) != null;
                }
            }else if(master instanceof RecordList){
                final RecordList recordList = (RecordList)master;
                if(searchCondition != null){
                    final Map params = new HashMap();
                    final Iterator entries = bindDataMap.entrySet().iterator();
                    try{
                        while(entries.hasNext()){
                            final Map.Entry entry = (Map.Entry)entries.next();
                            final String key = (String)entry.getKey();
                            final Property prop = (Property)entry.getValue();
                            if(prop == null){
                                params.put(key, obj);
                            }else{
                                params.put(key, prop.getProperty(obj));
                            }
                        }
                    }catch(NoSuchPropertyException e){
                        throw new ValidateException(e);
                    }catch(InvocationTargetException e){
                        throw new ValidateException(e.getCause());
                    }
                    try{
                        return recordList.realSearch(
                            searchCondition,
                            params
                        ).size() != 0;
                    }catch(Exception e){
                        throw new ValidateException(e);
                    }
                }else{
                    PropertySchema[] schemata = recordList.getRecordSchema().getPrimaryKeyPropertySchemata();
                    if(schemata == null || schemata.length != 1){
                        throw new ValidateException("Size of primary key property not equal 1.");
                    }
                    Record key = recordList.createRecord();
                    key.setProperty(schemata[0].getName(), obj);
                    return recordList.searchByPrimaryKey(key) != null;
                }
            }else{
                throw new ValidateException("Master '" + codeMasterName + "' is not supported type. type=" + master.getClass().getName());
            }
        }
    }
}