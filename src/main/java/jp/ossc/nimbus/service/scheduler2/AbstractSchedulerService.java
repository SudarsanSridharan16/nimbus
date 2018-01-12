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
package jp.ossc.nimbus.service.scheduler2;

import java.util.*;
import javax.naming.*;
import javax.transaction.*;

import jp.ossc.nimbus.core.*;
import jp.ossc.nimbus.daemon.*;
import jp.ossc.nimbus.service.context.Context;
import jp.ossc.nimbus.service.sequence.Sequence;
import jp.ossc.nimbus.service.aop.interceptor.ThreadContextKey;
import jp.ossc.nimbus.service.transaction.TransactionManagerFactory;
import jp.ossc.nimbus.service.keepalive.ClusterService;
import jp.ossc.nimbus.service.system.Time;

/**
 * ���ۃX�P�W���[���B<p>
 * �X�P�W���[�����ꂽ�^�X�N�����s����ӔC�𕉂��B<br>
 * {@link ScheduleManager}������s���ׂ�{@link Schedule}���擾���āA{@link ScheduleExecutor}�Ɏ��s���˗�����B<br>
 *
 * @author M.Takata
 */
public abstract class AbstractSchedulerService extends ServiceBase
 implements AbstractSchedulerServiceMBean, Scheduler{
    
    private static final long serialVersionUID = 6938915052580428501L;
    
    /**
     * TransactionManager��JNDI���B<p>
     * J2EE�̎d�l�ŁA�\�񂳂�Ă���JNDI���ł���B
     */
    protected static final String TRANSACTION_MANAGER_JNDI_NAME
         = "java:/TransactionManager";
    
    protected long scheduleTickerInterval = 1000l;
    
    protected ServiceName scheduleManagerServiceName;
    protected ScheduleManager scheduleManager;
    
    protected ServiceName[] scheduleExecutorServiceNames;
    protected Map scheduleExecutors;
    
    protected Daemon scheduleTicker;
    
    protected boolean isTransactionControl;
    protected TransactionManager transactionManager;
    protected ServiceName transactionManagerFactoryServiceName;
    
    protected String executorKey;
    
    protected ServiceName threadContextServiceName;
    protected Context threadContext;
    
    protected ServiceName sequenceServiceName;
    protected Sequence sequence;
    
    protected ServiceName clusterServiceName;
    protected ClusterService cluster;
    protected ClusterListener clusterListener;
    
    protected ServiceName timeServiceName;
    protected Time time;
    
    // AbstractSchedulerServiceMBean��JavaDoc
    public void setScheduleTickerInterval(long interval){
        scheduleTickerInterval = interval;
    }
    // AbstractSchedulerServiceMBean��JavaDoc
    public long getScheduleTickerInterval(){
        return scheduleTickerInterval;
    }
    
    // AbstractSchedulerServiceMBean��JavaDoc
    public void setScheduleManagerServiceName(ServiceName name){
        scheduleManagerServiceName = name;
    }
    // AbstractSchedulerServiceMBean��JavaDoc
    public ServiceName getScheduleManagerServiceName(){
        return scheduleManagerServiceName;
    }
    
    // AbstractSchedulerServiceMBean��JavaDoc
    public void setScheduleExecutorServiceName(ServiceName name){
        scheduleExecutorServiceNames = name == null ? null : new ServiceName[]{name};
    }
    // AbstractSchedulerServiceMBean��JavaDoc
    public ServiceName getScheduleExecutorServiceName(){
        return scheduleExecutorServiceNames == null || scheduleExecutorServiceNames.length != 1 ? null : scheduleExecutorServiceNames[0];
    }
    
    // AbstractSchedulerServiceMBean��JavaDoc
    public void setScheduleExecutorServiceNames(ServiceName[] names){
        scheduleExecutorServiceNames = names;
    }
    // AbstractSchedulerServiceMBean��JavaDoc
    public ServiceName[] getScheduleExecutorServiceNames(){
        return scheduleExecutorServiceNames;
    }
    
    // AbstractSchedulerServiceMBean��JavaDoc
    public void setThreadContextServiceName(ServiceName name){
        threadContextServiceName = name;
    }
    // AbstractSchedulerServiceMBean��JavaDoc
    public ServiceName getThreadContextServiceName(){
        return threadContextServiceName;
    }
    
    // AbstractSchedulerServiceMBean��JavaDoc
    public void setSequenceServiceName(ServiceName name){
        sequenceServiceName = name;
    }
    // AbstractSchedulerServiceMBean��JavaDoc
    public ServiceName getSequenceServiceName(){
        return sequenceServiceName;
    }
    
    // AbstractSchedulerServiceMBean��JavaDoc
    public void setTransactionControl(boolean isControl){
        isTransactionControl = isControl;
    }
    // AbstractSchedulerServiceMBean��JavaDoc
    public boolean isTransactionControl(){
        return isTransactionControl;
    }
    
    // AbstractSchedulerServiceMBean��JavaDoc
    public void setExecutorKey(String key){
        executorKey = key;
    }
    // AbstractSchedulerServiceMBean��JavaDoc
    public String getExecutorKey(){
        return executorKey;
    }
    
    // AbstractSchedulerServiceMBean��JavaDoc
    public void setTransactionManagerFactoryServiceName(ServiceName name){
        transactionManagerFactoryServiceName = name;
    }
    // AbstractSchedulerServiceMBean��JavaDoc
    public ServiceName getTransactionManagerFactoryServiceName(){
        return transactionManagerFactoryServiceName;
    }
    
    // AbstractSchedulerServiceMBean��JavaDoc
    public void setClusterServiceName(ServiceName name){
        clusterServiceName = name;
    }
    // AbstractSchedulerServiceMBean��JavaDoc
    public ServiceName getClusterServiceName(){
        return clusterServiceName;
    }
    
    // AbstractSchedulerServiceMBean��JavaDoc
    public void setTimeServiceName(ServiceName name){
        timeServiceName = name;
    }
    // AbstractSchedulerServiceMBean��JavaDoc
    public ServiceName getTimeServiceName(){
        return timeServiceName;
    }
    
    /**
     * �T�[�r�X�̐����O�������s���B<p>
     *
     * @exception Exception �T�[�r�X�̐����O�����Ɏ��s�����ꍇ
     */
    public void preCreateService() throws Exception{
        super.preCreateService();
    }
    
    /**
     * �T�[�r�X�̊J�n�O�������s���B<p>
     *
     * @exception Exception �T�[�r�X�̊J�n�O�����Ɏ��s�����ꍇ
     */
    public void preStartService() throws Exception{
        super.preStartService();
        
        if(isTransactionControl){
            if(transactionManagerFactoryServiceName == null){
                final InitialContext context = new InitialContext();
                transactionManager = (TransactionManager)context.lookup(
                    TRANSACTION_MANAGER_JNDI_NAME
                );
            }else{
                TransactionManagerFactory transactionManagerFactory = (TransactionManagerFactory)ServiceManagerFactory
                    .getServiceObject(transactionManagerFactoryServiceName);
                transactionManager = transactionManagerFactory.getTransactionManager();
            }
        }
        
        if(scheduleExecutorServiceNames != null){
            scheduleExecutors = new HashMap();
            for(int i = 0; i < scheduleExecutorServiceNames.length; i++){
                ScheduleExecutor executor = (ScheduleExecutor)ServiceManagerFactory
                    .getServiceObject(scheduleExecutorServiceNames[i]);
                scheduleExecutors.put(executor.getType(), executor);
            }
        }
        if(scheduleExecutors == null || scheduleExecutors.size() == 0){
            throw new IllegalArgumentException("ScheduleExecutor is null.");
        }
        
        if(scheduleManagerServiceName != null){
            scheduleManager = (ScheduleManager)ServiceManagerFactory
                .getServiceObject(scheduleManagerServiceName);
        }
        if(scheduleManager == null){
            throw new IllegalArgumentException("ScheduleManager is null.");
        }
        
        if(threadContextServiceName != null){
            threadContext = (Context)ServiceManagerFactory
                .getServiceObject(threadContextServiceName);
        }
        
        if(sequenceServiceName != null){
            sequence = (Sequence)ServiceManagerFactory
                .getServiceObject(sequenceServiceName);
        }
        
        if(timeServiceName != null){
            time = (Time)ServiceManagerFactory
                .getServiceObject(timeServiceName);
        }
    }
    
    /**
     * �T�[�r�X�̊J�n�㏈�����s���B<p>
     *
     * @exception Exception �T�[�r�X�̊J�n�㏈���Ɏ��s�����ꍇ
     */
    public void postStartService() throws Exception{
        scheduleManager.addScheduleControlListener(this);
        
        scheduleTicker = new Daemon(new ScheduleTicker());
        scheduleTicker.setName(getServiceNameObject() + " ScheduleTicker");
        scheduleTicker.suspend();
        scheduleTicker.start();
        
        if(clusterServiceName != null){
            cluster = (ClusterService)ServiceManagerFactory.getServiceObject(clusterServiceName);
            if(cluster.isJoin()){
                throw new IllegalArgumentException("ClusterService already join.");
            }
            clusterListener = new ClusterListener();
            cluster.addClusterListener(clusterListener);
            cluster.join();
        }else{
            scheduleTicker.resume();
        }
        
        super.postStartService();
    }
    
    /**
     * �T�[�r�X�̒�~�O�������s���B<p>
     *
     * @exception Exception �T�[�r�X�̒�~�O�����Ɏ��s�����ꍇ
     */
    public void preStopService() throws Exception{
        
        if(scheduleTicker != null){
            scheduleTicker.stop();
        }
        
        scheduleManager.removeScheduleControlListener(this);
        
        if(cluster != null){
            cluster.removeClusterListener(clusterListener);
            clusterListener = null;
            cluster.leave();
            cluster = null;
        }
        
        super.preStopService();
    }
    
    /**
     * �T�[�r�X�̒�~�㏈�����s���B<p>
     *
     * @exception Exception �T�[�r�X�̒�~�㏈���Ɏ��s�����ꍇ
     */
    public void postStopService() throws Exception{
        
        super.postStopService();
    }
    
    /**
     * �T�[�r�X�̔j���㏈�����s���B<p>
     *
     * @exception Exception �T�[�r�X�̔j���㏈���Ɏ��s�����ꍇ
     */
    public void postDestroyService() throws Exception{
        
        scheduleTicker = null;
        
        super.postDestroyService();
    }
    
    /**
     * ���N�G�X�g�ʔԂ�ݒ肷��{@link Context}�T�[�r�X��ݒ肷��B<p>
     *
     * @param context Context�T�[�r�X
     */
    public void setThreadContext(Context context){
        threadContext = context;
    }
    
    /**
     * ���N�G�X�g�ʔԂ�ݒ肷��{@link Context}�T�[�r�X���擾����B<p>
     *
     * @return Context�T�[�r�X
     */
    public Context getThreadContext(){
        return threadContext;
    }
    
    /**
     * ���N�G�X�g�ʔԂ𔭍s����{@link Sequence}�T�[�r�X��ݒ肷��B<p>
     *
     * @param seq Sequence�T�[�r�X
     */
    public void setSequence(Sequence seq){
        sequence = seq;
    }
    
    /**
     * ���N�G�X�g�ʔԂ𔭍s����{@link Sequence}�T�[�r�X���擾����B<p>
     *
     * @return Sequence�T�[�r�X
     */
    public Sequence getSequence(){
        return sequence;
    }
    
    // Scheduler��JavaDoc
    public ScheduleManager getScheduleManager(){
        return scheduleManager;
    }
    
    // Scheduler��JavaDoc
    public void setScheduleManager(ScheduleManager manager){
        scheduleManager = manager;
    }
    
    // Scheduler��JavaDoc
    public ScheduleExecutor getScheduleExecutor(String type){
        if(scheduleExecutors.size() == 1){
            ScheduleExecutor executor = (ScheduleExecutor)scheduleExecutors.values().iterator().next();
            return type == null || type.equals(executor.getType()) ? executor : null;
        }else{
            return (ScheduleExecutor)scheduleExecutors.get(type);
        }
    }
    
    // Scheduler��JavaDoc
    public void setScheduleExecutor(ScheduleExecutor executor){
        scheduleExecutors.put(executor.getType(), executor);
    }
    
    // Scheduler��JavaDoc
    public Map getScheduleExecutors(){
        return scheduleExecutors;
    }
    
    // Scheduler��JavaDoc
    public void startEntry(){
        if(scheduleTicker != null){
            scheduleTicker.resume();
        }
    }
    
    // Scheduler��JavaDoc
    public boolean isStartEntry(){
        return scheduleTicker == null ? false : scheduleTicker.isRunning() && !scheduleTicker.isSusupend();
    }
    
    // Scheduler��JavaDoc
    public void stopEntry(){
        if(scheduleTicker != null){
            scheduleTicker.suspend();
        }
    }
    
    /**
     * �X�P�W���[�������Ԃ��ύX���ꂽ���ɒʒm�����B<p>
     * ���s���̃X�P�W���[���̐����Ԃ𐧌䂷��B<br>
     *
     * @param id �X�P�W���[��ID
     * @param state �ύX���ꂽ������
     * @exception ScheduleStateControlException ���s���X�P�W���[���̐����Ԃ̕ύX�Ɏ��s�����ꍇ
     */
    public void changedControlState(String id, int state)
     throws ScheduleStateControlException{
        ScheduleExecutor[] executors = (ScheduleExecutor[])scheduleExecutors.values().toArray(
            new ScheduleExecutor[scheduleExecutors.size()]
        );
        for(int i = 0; i < executors.length; i++){
            if(executors[i].controlState(id, state)){
                break;
            }
        }
    }
    
    /**
     * �X�P�W���[���𓊓�����L���[��JTA���T�|�[�g���邩�ǂ����𔻒肷��B<p>
     *
     * @return JTA���T�|�[�g����ꍇ�́Atrue
     */
    protected abstract boolean isTransactableQueue();
    
    /**
     * �X�P�W���[�����L���[�ɓ�������B<p>
     *
     * @param request �X�P�W���[�����N�G�X�g
     */
    protected abstract void entrySchedule(ScheduleRequest request)
     throws Throwable;
    
    /**
     * �X�P�W���[����{@link ScheduleExecutor}�Ɏ��s�˗�����B<p>
     * �܂��A{@link ScheduleManager}���g���āA�X�P�W���[���̏�Ԃ�ύX����B<br>
     * 
     * @param request �L���[������o�����X�P�W���[�����N�G�X�g
     */
    protected void dispatchSchedule(ScheduleRequest request){
        if(threadContext != null){
            threadContext.clear();
        }
        if(threadContext != null && request.getRequestId() != null){
            threadContext.put(
                ThreadContextKey.REQUEST_ID,
                request.getRequestId()
            );
        }
        Schedule schedule = request.getSchedule();
        ScheduleExecutor scheduleExecutor = getScheduleExecutor(schedule.getExecutorType());
        if(scheduleExecutor == null){
            getLogger().write(
                MSG_ID_NOT_FOUND_EXECUTOR_ERROR,
                new Object[]{
                    scheduleManagerServiceName,
                    schedule.getId(),
                    schedule.getMasterId()
                }
            );
            try{
                scheduleManager.changeState(
                    schedule.getId(),
                    Schedule.STATE_FAILED
                );
            }catch(ScheduleStateControlException e){
                getLogger().write(
                    MSG_ID_STATE_CHANGE_ERROR,
                    new Object[]{
                        scheduleManagerServiceName,
                        schedule.getId(),
                        schedule.getMasterId(),
                        new Integer(Schedule.STATE_FAILED)
                    },
                    e
                );
            }
            return;
        }
        try{
            schedule = scheduleExecutor.execute(schedule);
        }catch(Throwable th){
            getLogger().write(
                MSG_ID_EXECUTE_ERROR,
                new Object[]{
                    scheduleManagerServiceName,
                    schedule.getId(),
                    schedule.getMasterId()
                },
                th
            );
            try{
                scheduleManager.changeState(
                    schedule.getId(),
                    Schedule.STATE_FAILED
                );
            }catch(ScheduleStateControlException e){
                getLogger().write(
                    MSG_ID_STATE_CHANGE_ERROR,
                    new Object[]{
                        scheduleManagerServiceName,
                        schedule.getId(),
                        schedule.getMasterId(),
                        new Integer(Schedule.STATE_FAILED)
                    },
                    e
                );
            }
            return;
        }
    }
    
    protected class ClusterListener implements jp.ossc.nimbus.service.keepalive.ClusterListener{
        
        public void memberInit(Object myId, List members){}
        
        public void memberChange(List oldMembers, List newMembers){}
        
        public void changeMain() throws Exception{
            startEntry();
        }
        
        public void changeSub(){
            stopEntry();
        }
    }
    
    /**
     * �X�P�W���[���e�B�b�J�[�B<p>
     * �X�P�W���[�������I�Ɏ擾���āA���s�L���[�ɓ�������B<br>
     *
     * @author M.Takata
     */
    protected class ScheduleTicker implements DaemonRunnable{
        
        /**
         * �f�[�������J�n�������ɌĂяo�����B<p>
         * 
         * @return ���true��Ԃ�
         */
        public boolean onStart() {
            return true;
        }
        
        /**
         * �f�[��������~�������ɌĂяo�����B<p>
         * 
         * @return ���true��Ԃ�
         */
        public boolean onStop() {
            return true;
        }
        
        /**
         * �f�[���������f�������ɌĂяo�����B<p>
         * 
         * @return ���true��Ԃ�
         */
        public boolean onSuspend() {
            return true;
        }
        
        /**
         * �f�[�������ĊJ�������ɌĂяo�����B<p>
         * 
         * @return ���true��Ԃ�
         */
        public boolean onResume() {
            return true;
        }
        
        /**
         * ��莞�ԋ󂯂�B<p>
         * 
         * @param ctrl DaemonControl�I�u�W�F�N�g
         * @return �X�P�W���[���̔z��
         */
        public Object provide(DaemonControl ctrl) throws Throwable{
            ctrl.sleep(getScheduleTickerInterval(), true);
            return null;
        }
        
        /**
         * ����dequeued�œn���ꂽ�I�u�W�F�N�g��������QueueHandler���Ăяo���B<p>
         *
         * @param schedules �L���[������o���ꂽ�I�u�W�F�N�g
         * @param ctrl DaemonControl�I�u�W�F�N�g
         */
        public void consume(Object schedules, DaemonControl ctrl)
         throws Throwable{
            boolean rollbackMark = false;
            List scheduleList = null;
            List scheduleRequests = null;
            try{
                if(isTransactionControl){
                    transactionManager.begin();
                }
                try{
                    final String[] executorTypes = (String[])scheduleExecutors.keySet().toArray(
                        new String[scheduleExecutors.size()]
                    );
                    if(executorKey == null){
                        scheduleList = scheduleManager.findExecutableSchedules(
                            time == null ? new Date() : new Date(time.currentTimeMillis()),
                            executorTypes
                        );
                    }else{
                        scheduleList = scheduleManager.findExecutableSchedules(
                            time == null ? new Date() : new Date(time.currentTimeMillis()),
                            executorTypes,
                            executorKey
                        );
                    }
                }catch(ScheduleManageException e){
                    getLogger().write(MSG_ID_SCHEDULE_GET_ERROR, getServiceNameObject(), e);
                    rollbackMark = true;
                    return;
                }
                if(scheduleList == null || scheduleList.size() == 0){
                    return;
                }
                scheduleRequests = new ArrayList();
                final Iterator itr = scheduleList.iterator();
                while(itr.hasNext()){
                    Schedule schedule = (Schedule)itr.next();
                    final ScheduleRequest request
                        = new ScheduleRequest(schedule);
                    if(sequence != null){
                        request.setRequestId(sequence.increment());
                        if(threadContext != null){
                            threadContext.put(
                                ThreadContextKey.REQUEST_ID,
                                request.getRequestId()
                            );
                        }
                    }
                    scheduleRequests.add(request);
                    try{
                        final int nowState = scheduleManager.getState(schedule.getId());
                        switch(nowState){
                        case Schedule.STATE_INITIAL:
                        case Schedule.STATE_RETRY:
                            break;
                        default:
                            getLogger().write(
                                MSG_ID_STATE_TRANS_ERROR,
                                new Object[]{
                                    scheduleManagerServiceName,
                                    schedule.getId(),
                                    schedule.getMasterId(),
                                    new Integer(nowState),
                                    new Integer(Schedule.STATE_ENTRY)
                                }
                            );
                            return;
                        }
                        schedule.setRetry(false);
                        schedule.setOutput(null);
                        final boolean isChanged = scheduleManager.changeState(
                            schedule.getId(),
                            nowState,
                            Schedule.STATE_ENTRY,
                            null
                        );
                        if(!isChanged){
                            itr.remove();
                            getLogger().write(
                                MSG_ID_STATE_TRANS_ERROR,
                                new Object[]{
                                    scheduleManagerServiceName,
                                    schedule.getId(),
                                    schedule.getMasterId(),
                                    new Integer(nowState),
                                    new Integer(Schedule.STATE_ENTRY)
                                }
                            );
                            continue;
                        }
                    }catch(ScheduleStateControlException e){
                        getLogger().write(
                            MSG_ID_STATE_CHANGE_ERROR,
                            new Object[]{
                                scheduleManagerServiceName,
                                schedule.getId(),
                                schedule.getMasterId(),
                                new Integer(Schedule.STATE_ENTRY)
                            },
                            e
                        );
                        rollbackMark = true;
                        break;
                    }
                    if(!isTransactionControl || isTransactableQueue()){
                        getLogger().write(
                            MSG_ID_ENTRY,
                            new Object[]{
                                scheduleManagerServiceName,
                                schedule.getId(),
                                schedule.getMasterId(),
                                schedule.getInput()
                            }
                        );
                        try{
                            entrySchedule(request);
                        }catch(Throwable th){
                            getLogger().write(
                                MSG_ID_ENTRY_ERROR,
                                new Object[]{
                                    scheduleManagerServiceName,
                                    schedule.getId(),
                                    schedule.getMasterId(),
                                    schedule.getInput()
                                },
                                th
                            );
                            rollbackMark = true;
                            break;
                        }
                    }
                }
            }catch(Throwable th){
                getLogger().write(MSG_ID_UNEXPEXTED_ERROR, getServiceNameObject(), th);
                if(isTransactionControl){
                    transactionManager.rollback();
                }
                throw th;
            }finally{
                if(isTransactionControl){
                    if(rollbackMark){
                        transactionManager.rollback();
                    }else{
                        transactionManager.commit();
                    }
                }
            }
            if(scheduleRequests != null && scheduleRequests.size() != 0
                 && isTransactionControl && !isTransactableQueue()
                 && !rollbackMark){
                for(int i = 0, imax = scheduleRequests.size(); i < imax; i++){
                    final ScheduleRequest request
                        = (ScheduleRequest)scheduleRequests.get(i);
                    if(threadContext != null && request.getRequestId() != null){
                        threadContext.put(
                            ThreadContextKey.REQUEST_ID,
                            request.getRequestId()
                        );
                    }
                    final Schedule schedule = request.getSchedule();
                    getLogger().write(
                        MSG_ID_ENTRY,
                        new Object[]{
                            scheduleManagerServiceName,
                            schedule.getId(),
                            schedule.getMasterId(),
                            schedule.getInput()
                        }
                    );
                    try{
                        entrySchedule(request);
                    }catch(Throwable th){
                        getLogger().write(
                            MSG_ID_ENTRY_ERROR,
                            new Object[]{
                                scheduleManagerServiceName,
                                schedule.getId(),
                                schedule.getMasterId(),
                                schedule.getInput()
                            },
                            th
                        );
                    }
                }
            }
        }
        
        /**
         * �������Ȃ��B<p>
         */
        public void garbage(){
        }
    }
    
    /**
     * �X�P�W���[�����N�G�X�g�B<p>
     *
     * @author M.Takata
     */
    protected static class ScheduleRequest implements java.io.Serializable{
        
        private static final long serialVersionUID = 8405850740460011444L;
        protected Schedule schedule;
        protected String requestId;
        
        public ScheduleRequest(Schedule schedule){
            this.schedule = schedule;
        }
        
        public Schedule getSchedule(){
            return schedule;
        }
        public void setSchedule(Schedule schedule){
            this.schedule = schedule;
        }
        
        public String getRequestId(){
            return requestId;
        }
        public void setRequestId(String id){
            requestId = id;
        }
    }
}