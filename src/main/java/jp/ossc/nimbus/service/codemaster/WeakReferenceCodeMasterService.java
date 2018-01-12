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
// �p�b�P�[�W
// �C���|�[�g
package jp.ossc.nimbus.service.codemaster;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.HashSet;

import jp.ossc.nimbus.core.ServiceManagerFactory;
import jp.ossc.nimbus.core.ServiceName;
import jp.ossc.nimbus.lang.ServiceException;
import jp.ossc.nimbus.service.beancontrol.interfaces.BeanFlowInvoker;
import jp.ossc.nimbus.service.beancontrol.interfaces.BeanFlowInvokerFactory;
import jp.ossc.nimbus.service.cache.Cache;
import jp.ossc.nimbus.service.cache.CachedReference;
import jp.ossc.nimbus.service.codemaster.map.CacheMap;
import jp.ossc.nimbus.core.ServiceBase;

/**
 * ��Q�Ƃ�p�����R�[�h�}�X�^�N���X<p>
 * �L���b�V���T�[�r�X���g�p���Ď�Q�Ƃ�p�����R�[�h�}�X�^�̊Ǘ����s��
 * @version $Name:  $
 * @author K.Nagai
 * @since 1.0
 */
public class WeakReferenceCodeMasterService extends ServiceBase implements
        WeakReferenceCodeMasterServiceMBean, CodeMasterFinder {

    private static final long serialVersionUID = 3626251129819845012L;

    /** �����X�V���t�L�[ */
    private static final String FIND_DATE_KEY = "date";
    /** �}�X�^�[�f�[�^�I�u�W�F�N�g�L�[ */
    private static final String MASTER_DATA_KEY = "data";

    /**�R�[�h�}�X�^���B���̖��O��BeanFlow�̃L�[�Ƃ��Ďg�p�����B*/
    private String[] mMasterNames= null ;

    private String[] notUpdateAllMasterNames;

    /**�}�X�^�[�i�[�pHash*/
    protected HashMap mMaster= null;
    /**���K�[��*/
    private ServiceName mLoggerServiceName = null ;
    /**BeanFlowInvoker�t�@�N�g����*/
    private ServiceName mBFInvokerFactoryName = null;
    /**BeanFlowInvoker�t�@�N�g��*/
    private BeanFlowInvokerFactory mBFInvokerFactory = null;
    /**Cache�T�[�r�X��*/
    private ServiceName mCacheServiceName;
    /**Cache�T�[�r�X����*/
    private Cache mCache;

    //�Z�b�^�[/�Q�b�^�[
    /* (�� Javadoc)
     * @see jp.ossc.nimbus.service.codemaster.WeakReferenceCodeMasterServiceMBean#setMasterNames(java.lang.String[])
     */
    public void setMasterNames(String[] names) {
        mMasterNames = names;
    }

    /* (�� Javadoc)
     * @see jp.ossc.nimbus.service.codemaster.WeakReferenceCodeMasterServiceMBean#setLoggerServiceName(jp.ossc.nimbus.core.ServiceName)
     */
    public void setLoggerServiceName(ServiceName name) {
        mLoggerServiceName = name;
    }

    /* (�� Javadoc)
     * @see jp.ossc.nimbus.service.codemaster.WeakReferenceCodeMasterServiceMBean#getMasterNames()
     */
    public String[] getMasterNames() {
        return mMasterNames;
    }

    /* (�� Javadoc)
     * @see jp.ossc.nimbus.service.codemaster.WeakReferenceCodeMasterServiceMBean#setLoggerServiceName()
     */
    public ServiceName getLoggerServiceName() {
        return mLoggerServiceName;
    }

    /* (�� Javadoc)
     * @see jp.ossc.nimbus.service.codemaster.WeakReferenceCodeMasterServiceMBean#setBeanFlowInvokerFactoryName(jp.ossc.nimbus.core.ServiceName)
     */
    public void setBeanFlowInvokerFactoryName(ServiceName name) {
        mBFInvokerFactoryName = name;
    }

    /* (�� Javadoc)
     * @see jp.ossc.nimbus.service.codemaster.WeakReferenceCodeMasterServiceMBean#getBeanFlowInvokerFactoryName()
     */
    public ServiceName getBeanFlowInvokerFactoryName() {
        return mBFInvokerFactoryName;
    }

    /* (�� Javadoc)
     * @see jp.ossc.nimbus.service.codemaster.WeakReferenceCodeMasterServiceMBean#setCacheServiceName(jp.ossc.nimbus.core.ServiceName)
     */
    public void setCacheServiceName(ServiceName name) {
        mCacheServiceName = name ;
    }

    /* (�� Javadoc)
     * @see jp.ossc.nimbus.service.codemaster.WeakReferenceCodeMasterServiceMBean#getCacheServiceName()
     */
    public ServiceName getCacheServiceName() {
        return mCacheServiceName;
    }

    // CodeMasterServiceMBean ��JavaDoc
    public String[] getNotUpdateAllMasterNames() {
        return notUpdateAllMasterNames;
    }

    // CodeMasterServiceMBean ��JavaDoc
    public void setNotUpdateAllMasterNames(String[] names) {
        this.notUpdateAllMasterNames = names;
    }

    /**
     * BeanFlowInvokerFactory��ݒ肷��B
     */
    public void setBeanFlowInvokerFactory(BeanFlowInvokerFactory invokerFactory) {
        mBFInvokerFactory = invokerFactory;
    }

    /**
     * Cache��ݒ肷��B
     */
    public void setCache(Cache cache) {
        mCache = cache;
    }

    /* (�� Javadoc)
     * @see jp.ossc.nimbus.core.ServiceBaseSupport#startService()
     */
    public void startService() throws Exception {
        if( mMasterNames == null ){
            throw new IllegalArgumentException("Attribute : MasterNames is null");
        }
        mMaster = new HashMap() ;

        if( mCacheServiceName != null) {
            mCache = (Cache)ServiceManagerFactory.getServiceObject(mCacheServiceName);
        } else if(mCache == null) {
            throw new IllegalArgumentException("Attribute : CacheServiceName or Cache is null");
        }

        if(mBFInvokerFactoryName != null) {
            mBFInvokerFactory = (BeanFlowInvokerFactory)ServiceManagerFactory.getServiceObject(mBFInvokerFactoryName);
        } else if(mBFInvokerFactory == null) {
            throw new IllegalArgumentException("Attribute : BeanFlowInvokerFactoryName or BeanFlowInvokerFactory is null");
        }

        try {
            initMasterHash();
        } catch ( ServiceException e) {
            throw e;
        }
    }
    /* (�� Javadoc)
     * @see jp.ossc.nimbus.core.ServiceBaseSupport#stopService()
     */
    public void stopService()  {
        this.mBFInvokerFactory = null;
        this.mBFInvokerFactoryName = null;
        if( this.mCache != null ) {
            this.mCache.clear();
        }
        this.mCache = null;
        this.mCacheServiceName = null;
        this.mMasterNames = null;
        if( this.mMaster != null ){
            this.mMaster.clear();
        }
        mMaster = null;
    }

    /**
     * �}�X�^�Ǘ��e�[�u��������������
     * @throws ServiceException
     */
    private void initMasterHash () throws ServiceException {
        for( int cnt = 0 ; cnt < mMasterNames.length ; cnt++ ){
            final String bfname = mMasterNames[cnt];
            final BeanFlowInvoker invoker = mBFInvokerFactory.createFlow(bfname);
            if( invoker == null ){
                //BeanFlowInvokerFactory�͖����L�[��NULL��Ԃ��܂�
                throw new ServiceException("WeakReferenceCodeMasterService001","Cannot specify Invoker with key ->"+bfname);
            }
            //���n��Ǘ��}�X�^�e�[�u�����쐬
            TimeManageMaster tmgr = new TimeManageMaster() ;
            tmgr.setMasterName(bfname) ;
            //�}�X�^�ɓo�^
            this.mMaster.put(bfname,tmgr) ;
            Object outMaster = null;
            try {
                //BeanFlow�����s����
                outMaster = invoker.invokeFlow(null);
            } catch ( Exception e ){
                throw new ServiceException("WeakReferenceCodeMasterService002","Exception occured in Invoker with key ->"+bfname,e);
            }
            if( outMaster != null ){
                //�R�[�h�}�X�^��o�^����(�����Ŏ�Q�Ƃɕϊ������)
                tmgr.addMaster(new Date(),outMaster) ;
            }
        }
    }
       /* (�� Javadoc)
     * @see jp.ossc.nimbus.service.codemaster.CodeMasterFinder#getCodeMasters()
     */
    public Map getCodeMasters()  {
        Map map = new CacheMap() ;
        Date nowDate = new Date();
        Set keys = this.mMaster.keySet();
        Iterator ite = keys.iterator() ;
        //�S�R�[�h�}�X�^�[���L�[Map�Ɋi�[
        while (ite.hasNext()){
            String key = (String)ite.next() ;
            TimeManageMaster tmp = null ;
            synchronized(this.mMaster){
                tmp = (TimeManageMaster)this.mMaster.get(key) ;
            }
            //���ݎ����Ń}�X�^�[������
            Object mst = tmp.getMaster(nowDate) ;
            //��Q�Ƃ̂܂ܓo�^����
            map.put(key,mst) ;
        }
        return map;
    }

    /**
     * �S�}�X�^�[�X�V�E���ݎ���
     * @see javax.jms.MessageListener#onMessage(javax.jms.Message)
     */
    public void codeMasterRefresh(){
        //���ݎ����擾
        codeMasterRefresh(new Date());
    }
    /**
     * �S�}�X�^�[�X�V�E�w�莞��
     * @see javax.jms.MessageListener#onMessage(javax.jms.Message)
     */
    public void codeMasterRefresh(Date date){
        codeMasterRefresh(mMasterNames,date);
    }
    /**
     * �w��}�X�^�[�X�V�E���ݎ���
     * @see javax.jms.MessageListener#onMessage(javax.jms.Message)
     */
    public void codeMasterRefresh(String flowName){
        //���ݎ����擾
        codeMasterRefresh(flowName,new Date());
    }
    /**
     * �w��}�X�^�[�X�V�E�w�莞��
     * @see javax.jms.MessageListener#onMessage(javax.jms.Message)
     */
    public void codeMasterRefresh(String flowName,Date date){
        final String[] flows = { flowName };
        codeMasterRefresh(flows,date);
    }

    /**
     * �}�X�^�[�X�V
     * Message �� MapMessage�Ƃ��A<br>
     * name��value�̑g�ݍ��킹�́A<br>
     * "key" (String)  | [�}�X�^�[��] (String)<br>
     * "date" (String) | [�f�[�^�L������](long)<br>
     * �Őݒ肷�邱��<br>
     * �w�肵�����t�ȍ~�̓��t�����ɐݒ肳��Ă���΁A�Y������}�X�^�f�[�^�𖳌��ɂ���
     * @see javax.jms.MessageListener#onMessage(javax.jms.Message)
     */
    private void codeMasterRefresh(String[] flowNames ,Date date) {
        for ( int i = 0 ; i < flowNames.length ; i ++ ){
            String bfname = flowNames[i] ;
            final BeanFlowInvoker invoker = mBFInvokerFactory.createFlow(bfname);
            if( invoker == null ){
                //BeanFlowInvokerFactory�͖����L�[��NULL��Ԃ�
                throw new ServiceException("WeakReferenceCodeMasterService004","Cannot specify Invoker with key ->"+bfname);
            }
            TimeManageMaster tmgr = (TimeManageMaster) this.mMaster.get(bfname);
            //���������ꍇ�V�����o�^���s��
            if( tmgr == null ){
                //���n��Ǘ��}�X�^�e�[�u�����쐬
                tmgr = new TimeManageMaster() ;
                tmgr.setMasterName(bfname) ;
                //�}�X�^�ɓo�^
                synchronized( mMaster ) {
                    this.mMaster.put(bfname,tmgr) ;
                }
            }
            Object outMaster = null;
            try {
                //BeanFlow�����s����
                outMaster = invoker.invokeFlow(null);
            } catch ( Exception e ){
                throw new ServiceException("WeakReferenceCodeMasterService005","Exception occured in Invoker with key ->"+bfname,e);
            }
            if( outMaster == null ){
                throw new ServiceException("WeakReferenceCodeMasterService006","Return codemaster is null : key ->"+bfname);
            }
            final TimeManageMaster newTm = tmgr.cloneOwn() ;
            //�}�X�^��o�^(�����ŃL���b�V���Q�Ƃɕϊ������)
            newTm.addMaster(date,outMaster) ;
            //���ݎ����ŕs�v�ȃ}�X�^���폜
            newTm.clear() ;
            synchronized(this.mMaster){
                this.mMaster.put(bfname,newTm) ;
            }
        }
    }

    public void updateAllCodeMasters() throws Exception{
        Set codeMasterNameSet = getCodeMasterNameSet();
        if(codeMasterNameSet != null){
            final Collection notUpdateAllMasterNameSet = Arrays.asList(notUpdateAllMasterNames == null ? new String[0] : notUpdateAllMasterNames);
            final Iterator codeMasterNames = codeMasterNameSet.iterator();
            while(codeMasterNames.hasNext()){
                String codeMasterName = (String)codeMasterNames.next();
                if(!notUpdateAllMasterNameSet.contains(codeMasterName)){
                    updateCodeMaster(codeMasterName);
                }
            }
        }
    }

    public void updateCodeMaster(String key) throws Exception{
        updateCodeMaster(key, new Date());
    }

    public void updateCodeMaster(String key, Date updateTime) throws Exception{
        updateCodeMaster(key, null, updateTime);
    }

    public void updateCodeMaster(String key, Object input, Date updateTime){
        String bfname = key ;
        Date date = updateTime;
        final BeanFlowInvoker invoker = mBFInvokerFactory.createFlow(bfname);
        if( invoker == null ){
            //BeanFlowInvokerFactory�͖����L�[��NULL��Ԃ�
            throw new ServiceException("WeakReferenceCodeMasterService004","Cannot specify Invoker with key ->"+bfname);
        }
        TimeManageMaster tmgr = (TimeManageMaster) this.mMaster.get(bfname);
        //���������ꍇ�V�����o�^���s��
        if( tmgr == null ){
            //���n��Ǘ��}�X�^�e�[�u�����쐬
            tmgr = new TimeManageMaster() ;
            tmgr.setMasterName(bfname) ;
            //�}�X�^�ɓo�^
            synchronized( mMaster ) {
                this.mMaster.put(bfname,tmgr) ;
            }
        }
        Object outMaster = null;
        try {
            //BeanFlow�����s����
            outMaster = invoker.invokeFlow(input);
        } catch ( Exception e ){
            throw new ServiceException("WeakReferenceCodeMasterService005","Exception occured in Invoker with key ->"+bfname,e);
        }
        if( outMaster == null ){
            throw new ServiceException("WeakReferenceCodeMasterService006","Return codemaster is null : key ->"+bfname);
        }
        final TimeManageMaster newTm = tmgr.cloneOwn() ;
        if(date == null){
            date = new Date();
        }
        //�}�X�^��o�^(�����ŃL���b�V���Q�Ƃɕϊ������)
        newTm.addMaster(date,outMaster) ;
        //���ݎ����ŕs�v�ȃ}�X�^���폜
        newTm.clear() ;
        synchronized(this.mMaster){
            this.mMaster.put(bfname,newTm) ;
        }
    }

    // CodeMasterFinder��JavaDoc
    public Set getCodeMasterNameSet(){
        return mMaster == null
             ? new HashSet() : new HashSet(mMaster.keySet());
    }

    /**
     * �}�X�^�[Bean�̎����ł̊Ǘ����s���N���X<p>
     * @version $Name:  $
     * @author H.Nakano
     * @since 1.0
     */
    private class TimeManageMaster{
        private String mFlowKey = null ;
        private ArrayList mTimeList = null ;
        /**
         * �R���X�g���N�^�[
         */
        public TimeManageMaster(){
            mTimeList = new ArrayList() ;
        }
        /**
         * �}�X�^�[���ݒ�
         * @param name
         */
        public void setMasterName(String name){
            mFlowKey = name ;
        }
        /**
         * �}�X�^�[���擾
         * @return
         */
        public String getMasterName(){
            return mFlowKey ;
        }
        /**
         * �}�X�^�[�f�[�^�ǉ�
         * @param time
         * @param master
         */
        public void addMaster(Date time ,Object master){            //�o�^������
            HashMap rec = new HashMap() ;
            //�L���b�V���Q�Ƃɕϊ�
            CachedReference wref = mCache.add(master);
            //�L���b�V���Q�Ƃ�o�^
            rec.put(MASTER_DATA_KEY,wref) ;
            rec.put(FIND_DATE_KEY,time) ;
            boolean instFlg = false ;
            for(int cnt= mTimeList.size()-1; cnt > -1 ;cnt--){
                Map map = (Map)mTimeList.get(cnt) ;
                Date tmpTime = (Date)map.get(FIND_DATE_KEY) ;
                if(tmpTime.before(time)){
                    //�������O�̂��̂𔭌�
                    if(cnt== mTimeList.size()-1){
                        mTimeList.add(rec) ;
                    }else{
                        mTimeList.add(cnt+1,rec) ;
                    }
                    instFlg = true ;
                    break ;
                } else if( tmpTime.equals(time) ){
                    //�������̏ꍇ�A�u��������
                    mTimeList.set(cnt,rec);
                    instFlg = true ;
                    break ;
                }
            }
            if(!instFlg){
                //���͍ł���������������
                if(mTimeList.size()==0){
                    mTimeList.add(rec) ;
                }else{
                    mTimeList.add(0,rec) ;
                }
            }
        }
        /**
         * �w�莞���ł̃}�X�^�[(CachedReference�ƂȂ�)�擾
         * @param time
         * @return
         */
        public Object getMaster(Date time){
            Object ret = null ;
            for(int cnt= mTimeList.size()-1; cnt > -1 ;cnt--){
                Map map = (Map)mTimeList.get(cnt) ;
                Date tmpTime = (Date)map.get(FIND_DATE_KEY) ;
                if(tmpTime.before(time)){
                    ret= map.get(MASTER_DATA_KEY) ;
                    break ;
                }
            }
            return ret ;
        }
        /**
         * ���ݎ����ŕs�K�v�ȃ}�X�^�[��j��
         */
        public void clear(){
            Date now = new Date() ;
            for(int cnt= mTimeList.size()-1; cnt >= 0 ;cnt--){
                Map map = (Map)mTimeList.get(cnt) ;
                Date tmpTime = (Date)map.get(FIND_DATE_KEY) ;
                if(tmpTime.before(now)){
                    if(cnt>0){
                        for(int rcnt = cnt-1;rcnt>=0;rcnt--){
                            HashMap masterMap = (HashMap) mTimeList.get(rcnt);
                            //�L���b�V���Q�Ƃ�����
                            CachedReference ref = (CachedReference) masterMap.get(MASTER_DATA_KEY);
                            mCache.remove(ref);
                            //�o�^������
                            mTimeList.remove(rcnt) ;
                        }
                        break;
                    }
                }
            }
        }
        /**
         * �N���[��
         * @return
         */
        public TimeManageMaster cloneOwn(){
            TimeManageMaster ret = new TimeManageMaster() ;
            ret.setMasterName(this.getMasterName()) ;
            for(int cnt= 0;cnt<mTimeList.size();cnt++){
                ret.mTimeList.add(this.mTimeList.get(cnt));
            }
            return ret ;
        }
    }

}
