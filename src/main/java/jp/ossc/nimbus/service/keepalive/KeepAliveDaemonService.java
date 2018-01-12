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
package jp.ossc.nimbus.service.keepalive;

import java.util.*;
import jp.ossc.nimbus.core.*;
import jp.ossc.nimbus.daemon.*;
import jp.ossc.nimbus.util.*;

/**
 * KeepAliveChecker�Ď��T�[�r�X�B<p>
 * �����T�[�o���Ď����āA�����Ă���T�[�o��D��x������у��E���h���r�����ɐ����m�F����B<br>
 * 
 * @author H.Nakano
 * @version  1.00 �쐬: 2003/10/08 - H.Nakano
 */
public class KeepAliveDaemonService extends ServiceBase
 implements KeepAliveDaemonServiceMBean, DaemonRunnable {
    
    private static final long serialVersionUID = -1630984556813685757L;
    
    private static final String C_NAME = "Server name: " ; //$NON-NLS-1$
    private static final String C_STATUS = "status : " ; //$NON-NLS-1$
    private static final String C_RUNNING = "running" ; //$NON-NLS-1$
    private static final String C_STOP ="stop" ; //$NON-NLS-1$
    private static final String C_ALIVEKEY = "KEEPALIVE001" ; //$NON-NLS-1$
    private static final String C_DEADKEY = "KEEPALIVE002" ; //$NON-NLS-1$
    
    //## �N���X�ϐ��錾 ##
    /** ���M�T�[�o�ғ���Ԉꗗ */
    protected Hashtable mServerTbl = new Hashtable();
    /** �����p�I�u�W�F�N�g */
    protected Boolean mSyncObj = new Boolean(true);
    /** �T�[�o�`�F�b�J�[���X�g */
    protected List mCheckerList = new ArrayList();
    /** �`�F�b�N�C���^�[�o�� */
    protected volatile long mInterval;
    /** �f�[�����N���X�C���X�^���X */
    protected Daemon mDaemon;
    /** ���E���h���r���J�E���^�[ */
    protected volatile int mRoundRobin;
    protected String aliveLogMessageId = C_ALIVEKEY;
    protected String deadLogMessageId = C_DEADKEY;
    protected boolean isOutputAliveLogMessage = true;
    protected boolean isOutputDeadLogMessage = true;
    
    public void createService() throws Exception{
        mDaemon = new Daemon(this);
        mDaemon.setName(getServiceName());
    }
    
    public  void startService() throws Exception{
        mDaemon.start();
    }
    
    public  void stopService() throws Exception{
        mDaemon.stop();
    }
    
    public  void destroyService() throws Exception{
        mDaemon = null;
    }
    
    // KeepAliveDaemonServiceMBean��JavaDoc
    public void setChekerServiceNames(ServiceName[] serviceNames){
        synchronized(mSyncObj){
            mCheckerList.clear();
            for(int cnt = 0; cnt < serviceNames.length; cnt++){
                mCheckerList.add(serviceNames[cnt]);
            }
        }
    }
    
    // KeepAliveDaemonServiceMBean��JavaDoc
    public ServiceName[] getChekerServiceNames(){
        return (ServiceName[])mCheckerList.toArray(new ServiceName[mCheckerList.size()]);
    }
    
    // KeepAliveDaemonServiceMBean��JavaDoc
    public void setIntervalTimeMillis(long miliseconds){
        mInterval = miliseconds ;
    }
    
    // KeepAliveDaemonServiceMBean��JavaDoc
    public long getIntervalTimeMillis(){
        return mInterval;
    }
    
    // KeepAliveDaemonServiceMBean��JavaDoc
    public String[] getStatusString(){
        CsvArrayList ret = new CsvArrayList();
        Map keepAlive = getKeepAliveMap();
        for(Iterator keys = keepAlive.keySet().iterator();keys.hasNext();){
            String serverId = (String)keys.next();
            String servername = C_NAME + serverId;
            String status = C_STATUS;
            if(Boolean.TRUE.equals((Boolean)keepAlive.get(serverId))){
                status = status + C_RUNNING;
            }else{
                status = status + C_STOP;
            }
            ret.add(servername);
            ret.add(status);
        }
        return ret.toStringAry();
    }
    
    // KeepAliveDaemonServiceMBean��JavaDoc
    public void setAliveLogMessageId(String id){
        aliveLogMessageId = id;
    }
    
    // KeepAliveDaemonServiceMBean��JavaDoc
    public String getAliveLogMessageId(){
        return aliveLogMessageId;
    }
    
    // KeepAliveDaemonServiceMBean��JavaDoc
    public void setDeadLogMessageId(String id){
        deadLogMessageId = id;
    }
    
    // KeepAliveDaemonServiceMBean��JavaDoc
    public String getDeadLogMessageId(){
        return deadLogMessageId;
    }
    
    // KeepAliveDaemonServiceMBean��JavaDoc
    public void setOutputAliveLogMessage(boolean isOutput){
        isOutputAliveLogMessage = isOutput;
    }
    
    // KeepAliveDaemonServiceMBean��JavaDoc
    public boolean isOutputAliveLogMessage(){
        return isOutputAliveLogMessage;
    }
    
    // KeepAliveDaemonServiceMBean��JavaDoc
    public void setOutputDeadLogMessage(boolean isOutput){
        isOutputDeadLogMessage = isOutput;
    }
    
    // KeepAliveDaemonServiceMBean��JavaDoc
    public boolean isOutputDeadLogMessage(){
        return isOutputDeadLogMessage;
    }
    
    // DaemonRunnable��JavaDoc
    public boolean onStart(){
        
        for(Iterator iterator = mCheckerList.iterator();iterator.hasNext();){
            ServiceName name = (ServiceName)iterator.next();
            KeepAliveChecker kac =(KeepAliveChecker)ServiceManagerFactory.getServiceObject(name);
            //�R���j�`�n���Ă����Ă݂�
            boolean bret = kac.isAlive();
            //���ʂ��A�b�v�f�[�g���Ƃ�
            updateTblStructure(name, bret);
        }
        return true;
    }
    
    // DaemonRunnable��JavaDoc
    public boolean onStop(){
        return true;
    }
    
    // DaemonRunnable��JavaDoc
    public boolean onSuspend(){
        return true;
    }
    
    // DaemonRunnable��JavaDoc
    public boolean onResume(){
        return true;
    }
    
    // DaemonRunnable��JavaDoc
    public Object provide(DaemonControl ctrl) throws Exception{
        Thread.sleep(mInterval);
        return null;
    }
    
    // DaemonRunnable��JavaDoc
    public void consume(Object paramObj, DaemonControl ctrl) throws Exception{
        onStart();
    }
    
    // DaemonRunnable��JavaDoc
    public void garbage(){
    }
    
    //
    /**
     * ���M�T�[�o�ꗗ���X�V����B<p>
     * �����Ŏw�肳�ꂽ���M�T�[�o�̉ғ���Ԃ𑗐M�T�[�o�ꗗ�ɍX�V����B
     *
     * @param msid �T�[�o��
     * @param keepAlive �ғ����
     */
    protected void updateTblStructure(Object msid, boolean keepAlive){
        synchronized(mSyncObj){
            Boolean bret = (Boolean)mServerTbl.get(msid);
            if(bret != null){
                if(!bret.booleanValue() && keepAlive){
                    //�����I�I
                    if(isOutputAliveLogMessage){
                        getLogger().write(aliveLogMessageId, msid);
                    }
                }
                if(bret.booleanValue() && !keepAlive){
                    //��~
                    if(isOutputDeadLogMessage){
                        getLogger().write(deadLogMessageId, msid);
                    }
                }
            }
            mServerTbl.put(msid, new Boolean(keepAlive));
        }
    }
    
    // QueryKeepAlive��JavaDoc
    public Map getKeepAliveMap(){
        synchronized(mSyncObj){
            return (Map)mServerTbl.clone();
        }
    }
    
    // QueryKeepAlive��JavaDoc
    public void updateTbl(Object msid, boolean keepAlive) {
        synchronized(mSyncObj) {
            Boolean bret = (Boolean)mServerTbl.get(msid);
            if(bret != null){
                updateTblStructure(msid,keepAlive);
            }
        }
    }
    
    // QueryKeepAlive��JavaDoc
    public List getPriolityAry(){
        CsvArrayList ret = new CsvArrayList();
        synchronized(mSyncObj){
            for(ListIterator iterator = mCheckerList.listIterator();iterator.hasNext();){
                ServiceName name = (ServiceName)iterator.next();
                Boolean  bret = (Boolean)this.mServerTbl.get(name);
                if(bret.booleanValue()){
                    ret.add(name);
                }
            }
        }
        return ret;
    }
    
    // QueryKeepAlive��JavaDoc
    public List getPriolityAry(Set available){
        synchronized(mSyncObj){
            List ret = getPriolityAry();
            for(ListIterator iterator = ret.listIterator();iterator.hasNext();){
                Object name = (Object)iterator.next();
                if(!available.contains(name)){
                    ret.remove(name);
                }
            }
            return ret;
        }
    }
    
    // QueryKeepAlive��JavaDoc
    public List getRoundrobinAry() {
        CsvArrayList ret = new CsvArrayList() ;
        synchronized(mSyncObj) {
            for(int cnt = mRoundRobin; cnt < mCheckerList.size(); cnt++){
                ServiceName name = (ServiceName)mCheckerList.get(cnt);
                Boolean  bret = (Boolean)this.mServerTbl.get(name);
                if(bret.booleanValue()){
                    ret.add(name);
                }
            }
            for(int cnt = 0; cnt < mRoundRobin; cnt++){
                ServiceName name = (ServiceName)mCheckerList.get(cnt);
                Boolean  bret = (Boolean)this.mServerTbl.get(name) ;
                if(bret.booleanValue()){
                    ret.add(name) ;
                }
            }
            mRoundRobin++;
            if(mCheckerList.size() <= mRoundRobin){
                mRoundRobin = 0;
            }
        }
        return ret;
    }
    
    // QueryKeepAlive��JavaDoc
    public List getRoundrobinAry(Set available) {
        synchronized(mSyncObj) {
            List ret = getRoundrobinAry();
            for(ListIterator iterator = ret.listIterator();iterator.hasNext();){
                Object name = iterator.next();
                if(!available.contains(name)){
                    ret.remove(name);
                }
            }
            return ret;
        }
    }
}
