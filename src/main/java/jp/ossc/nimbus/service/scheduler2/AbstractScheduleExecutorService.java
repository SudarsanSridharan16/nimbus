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
import java.net.UnknownHostException;
import java.text.*;

import jp.ossc.nimbus.core.*;
import jp.ossc.nimbus.service.journal.Journal;
import jp.ossc.nimbus.service.journal.editorfinder.EditorFinder;
import jp.ossc.nimbus.service.context.Context;
import jp.ossc.nimbus.service.aop.interceptor.ThreadContextKey;

/**
 * ���ۃX�P�W���[�����s�B<p>
 * ���s���˗����ꂽ�^�X�N�����s����B<br>
 *
 * @author M.Takata
 */
public abstract class AbstractScheduleExecutorService extends ServiceBase
 implements ScheduleExecutor, AbstractScheduleExecutorServiceMBean{
    
    private static final long serialVersionUID = 7621829987739712419L;
    
    protected ServiceName scheduleManagerServiceName;
    protected ScheduleManager scheduleManager;
    
    protected String key;
    protected String hostName = "localhost";
    protected String type;
    
    protected ServiceName journalServiceName;
    protected Journal journal;
    
    protected ServiceName editorFinderServiceName;
    protected EditorFinder editorFinder;
    
    protected ServiceName threadContextServiceName;
    protected Context threadContext;
    
    public void setScheduleManagerServiceName(ServiceName name){
        scheduleManagerServiceName = name;
    }
    public ServiceName getScheduleManagerServiceName(){
        return scheduleManagerServiceName;
    }
    
    public void setKey(String key){
        this.key = key;
    }
    
    public void setType(String type){
        this.type = type;
    }
    
    public void setJournalServiceName(ServiceName name){
        journalServiceName = name;
    }
    public ServiceName getJournalServiceName(){
        return journalServiceName;
    }
    
    public void setEditorFinderServiceName(ServiceName name){
        editorFinderServiceName = name;
    }
    public ServiceName getEditorFinderServiceName(){
        return editorFinderServiceName;
    }
    
    public void setThreadContextServiceName(ServiceName name){
        threadContextServiceName = name;
    }
    public ServiceName getThreadContextServiceName(){
        return threadContextServiceName;
    }
    
    public void setJournal(Journal journal){
        this.journal = journal;
    }
    public Journal getJournal(){
        return journal;
    }
    
    public void setEditorFinder(EditorFinder editorFinder){
        this.editorFinder = editorFinder;
    }
    public EditorFinder getEditorFinder(){
        return editorFinder;
    }
    
    public void setThreadContext(Context context){
        threadContext = context;
    }
    public Context getThreadContext(){
        return threadContext;
    }
    
    /**
     * �T�[�r�X�̊J�n�O�������s���B<p>
     *
     * @exception Exception �T�[�r�X�̊J�n�O�����Ɏ��s�����ꍇ
     */
    public void preStartService() throws Exception{
        
        if(scheduleManagerServiceName != null){
            scheduleManager = (ScheduleManager)ServiceManagerFactory
                .getServiceObject(scheduleManagerServiceName);
        }
        if(scheduleManager == null){
            throw new IllegalArgumentException("ScheduleManager is null.");
        }
        try{
            hostName = java.net.InetAddress.getLocalHost().getHostName();
        }catch(UnknownHostException e){
        }
        
        if(journalServiceName != null){
            journal = (Journal)ServiceManagerFactory
                .getServiceObject(journalServiceName);
        }
        
        if(editorFinderServiceName != null){
            editorFinder = (EditorFinder)ServiceManagerFactory
                .getServiceObject(editorFinderServiceName);
        }
        
        if(threadContextServiceName != null){
            threadContext = (Context)ServiceManagerFactory
                .getServiceObject(threadContextServiceName);
        }
    }
    
    // ScheduleExecutor��JavaDoc
    public ScheduleManager getScheduleManager(){
        return scheduleManager;
    }
    
    // ScheduleExecutor��JavaDoc
    public void setScheduleManager(ScheduleManager manager){
        scheduleManager = manager;
    }
    
    // ScheduleExecutor��JavaDoc
    public String getKey(){
        return key == null ? hostName : key;
    }
    
    // ScheduleExecutor��JavaDoc
    public String getType(){
        return type;
    }
    
    /**
     * �w�肳�ꂽ�X�P�W���[���̃^�X�N�����s�\���`�F�b�N����B<p>
     * �����ł́A�����������Ȃ��̂ŁA�K�v�ɉ����ăI�[�o�[���C�h���邱�ƁB<br>
     *
     * @param schedule �X�P�W���[��
     * @exception Exception �w�肳�ꂽ�X�P�W���[���̃^�X�N�����s�ł��Ȃ��ꍇ
     */
    protected void checkPreExecute(Schedule schedule) throws Exception{
    }
    
    /**
     * �w�肳�ꂽ�X�P�W���[���̃^�X�N�����s����B<p>
     *
     * @param schedule �X�P�W���[��
     * @return ���s���ʂ��܂ރX�P�W���[��
     * @exception Throwable �w�肳�ꂽ�X�P�W���[���̎��s�Ɏ��s�����ꍇ
     */
    protected abstract Schedule executeInternal(Schedule schedule)
     throws Throwable;
    
    /**
     * �w�肳�ꂽ�X�P�W���[�������s����B<p>
     * <ol>
     *   <li>{@link #checkPreExecute(Schedule)}�ŃX�P�W���[���̃^�X�N�����s�ł��邩�ǂ������`�F�b�N����B<br>�`�F�b�N�G���[�̏ꍇ�́A���O{@link #MSG_ID_EXECUTE_ERROR}���o�͂��A�X�P�W���[���̏�Ԃ�{@link Schedule#STATE_FAILED}�ɑJ�ڂ�����B<br>�X�P�W���[���̏�ԑJ�ڂɎ��s�����ꍇ�́A���O{@link #MSG_ID_STATE_CHANGE_ERROR}���o�͂���B</li>
     *   <li>�X�P�W���[���̏�Ԃ�{@link Schedule#STATE_RUN}�ɑJ�ڂ����A���O{@link #MSG_ID_RUN}���o�͂���B<br>�X�P�W���[���̏�ԑJ�ڂɎ��s�����ꍇ�́A���O{@link #MSG_ID_STATE_CHANGE_ERROR}���o�͂���B</li>
     *   <li>{@link #executeInternal(Schedule)}���Ăяo���A�X�P�W���[�������s����B</li>
     *   <li>
     *     �X�P�W���[���̎��s���ʂɏ]���āA�ȉ��̏������s���B<br>
     *     <ul>
     *       <li>�X�P�W���[��������ɏI�������ꍇ�A���O{@link #MSG_ID_RUN}���o�͂��A�X�P�W���[���̏�Ԃ�{@link Schedule#STATE_END}�ɑJ�ڂ�����B<br>�X�P�W���[���̏�ԑJ�ڂɎ��s�����ꍇ�́A���O{@link #MSG_ID_STATE_CHANGE_ERROR}���o�͂���B</li>
     *       <li>�X�P�W���[���̎��s�ŗ�O�����������ꍇ�A���O{@link #MSG_ID_EXECUTE_ERROR}���o�͂��A�X�P�W���[���̏�Ԃ�{@link Schedule#STATE_FAILED}�ɑJ�ڂ�����B<br>�X�P�W���[���̏�ԑJ�ڂɎ��s�����ꍇ�́A���O{@link #MSG_ID_STATE_CHANGE_ERROR}���o�͂���B</li>
     *       <li>�X�P�W���[���������I�����ꂽ�ꍇ�A���O{@link #MSG_ID_ABORT}���o�͂��A�X�P�W���[���̏�Ԃ�{@link Schedule#STATE_ABORT}�ɑJ�ڂ�����B<br>�X�P�W���[���̏�ԑJ�ڂɎ��s�����ꍇ�́A���O{@link #MSG_ID_STATE_CHANGE_ERROR}���o�͂���B</li>
     *       <li>�X�P�W���[�������g���C�v�����ꂽ�ꍇ�A���̃��g���C�����ɍăX�P�W���[�����āA�X�P�W���[���̏�Ԃ�{@link Schedule#STATE_RETRY}�ɑJ�ڂ�����B<br>�A���A���̃��g���C���������g���C�I���������z���Ă����ꍇ�́A���O{@link #MSG_ID_RETRY_END_ERROR}���o�͂��A�X�P�W���[���̏�Ԃ�{@link Schedule#STATE_FAILED}�ɑJ�ڂ�����B<br>�X�P�W���[���̏�ԑJ�ڂɎ��s�����ꍇ�́A���O{@link #MSG_ID_STATE_CHANGE_ERROR}���o�͂���B</li>
     *     </ul>
     *   </li>
     * </ol>
     *
     * @param schedule �X�P�W���[��
     * @return �X�P�W���[��
     */
    public Schedule execute(Schedule schedule){
        
        Schedule result = schedule;
        try{
            checkPreExecute(schedule);
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
                    Schedule.STATE_FAILED,
                    th
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
            return result;
        }
        try{
            final boolean isChanged = scheduleManager.changeState(
                schedule.getId(),
                Schedule.STATE_ENTRY,
                Schedule.STATE_RUN
            );
            if(!isChanged){
                getLogger().write(
                    MSG_ID_STATE_TRANS_ERROR,
                    new Object[]{
                        scheduleManagerServiceName,
                        schedule.getId(),
                        schedule.getMasterId(),
                        new Integer(Schedule.STATE_ENTRY),
                        new Integer(Schedule.STATE_RUN)
                    }
                );
                return schedule;
            }
        }catch(ScheduleStateControlException e){
            getLogger().write(
                MSG_ID_STATE_CHANGE_ERROR,
                new Object[]{
                    scheduleManagerServiceName,
                    schedule.getId(),
                    schedule.getMasterId(),
                    new Integer(Schedule.STATE_RUN)
                },
                e
            );
            return schedule;
        }
        getLogger().write(
            MSG_ID_RUN,
            new Object[]{
                scheduleManagerServiceName,
                schedule.getId(),
                schedule.getMasterId(),
                schedule.getInput()
            }
        );
        try{
            if(journal != null){
                journal.startJournal(JOURNAL_KEY_EXECUTE, editorFinder);
                if(threadContext != null){
                    journal.setRequestId((String)threadContext.get(ThreadContextKey.REQUEST_ID));
                }
                journal.addInfo(JOURNAL_KEY_INPUT_SCHEDULE, schedule);
            }
            result = executeInternal(schedule);
            if(journal != null){
                journal.addInfo(JOURNAL_KEY_OUTPUT_SCHEDULE, result);
            }
            if(result.getState() == Schedule.STATE_FAILED){
                getLogger().write(
                    MSG_ID_EXECUTE_ERROR,
                    new Object[]{
                        scheduleManagerServiceName,
                        schedule.getId(),
                        schedule.getMasterId()
                    }
                );
                try{
                    scheduleManager.changeState(
                        schedule.getId(),
                        Schedule.STATE_FAILED,
                        result.getOutput()
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
            }else if(result.getState() == Schedule.STATE_ABORT){
                getLogger().write(
                    MSG_ID_ABORT,
                    new Object[]{
                        scheduleManagerServiceName,
                        result.getId(),
                        result.getMasterId(),
                    },
                    result.getOutput() instanceof Throwable ? (Throwable)result.getOutput() : null
                );
                try{
                    scheduleManager.changeState(
                        result.getId(),
                        Schedule.STATE_ABORT,
                        result.getOutput()
                    );
                }catch(ScheduleStateControlException e2){
                    getLogger().write(
                        MSG_ID_STATE_CHANGE_ERROR,
                        new Object[]{
                            scheduleManagerServiceName,
                            result.getId(),
                            result.getMasterId(),
                            new Integer(Schedule.STATE_ABORT)
                        },
                        e2
                    );
                }
            }else if(result.getState() == Schedule.STATE_DISABLE){
                getLogger().write(
                    MSG_ID_DISABLE,
                    new Object[]{
                        scheduleManagerServiceName,
                        result.getId(),
                        result.getMasterId(),
                        result.getOutput()
                    }
                );
                try{
                    scheduleManager.changeState(
                        result.getId(),
                        Schedule.STATE_DISABLE,
                        result.getOutput()
                    );
                }catch(ScheduleStateControlException e2){
                    getLogger().write(
                        MSG_ID_STATE_CHANGE_ERROR,
                        new Object[]{
                            scheduleManagerServiceName,
                            result.getId(),
                            result.getMasterId(),
                            new Integer(Schedule.STATE_DISABLE)
                        },
                        e2
                    );
                }
            }else if(result.getRetryInterval() > 0
                 && result.isRetry()){
                final Date retryTime = calculateRetryTime(
                    result.getRetryInterval(),
                    result.getRetryEndTime()
                );
                if(retryTime == null){
                    getLogger().write(
                        MSG_ID_RETRY_END_ERROR,
                        new Object[]{
                            scheduleManagerServiceName,
                            result.getId(),
                            result.getMasterId(),
                            new SimpleDateFormat("yyyy/MM/dd HH:mm:ss.SSS").format(result.getRetryEndTime())
                        }
                    );
                    try{
                        scheduleManager.changeState(
                            result.getId(),
                            Schedule.STATE_FAILED
                        );
                    }catch(ScheduleStateControlException e){
                        getLogger().write(
                            MSG_ID_STATE_CHANGE_ERROR,
                            new Object[]{
                                scheduleManagerServiceName,
                                result.getId(),
                                result.getMasterId(),
                                new Integer(Schedule.STATE_FAILED)
                            },
                            e
                        );
                    }
                }else{
                    try{
                        final boolean isReschedule = scheduleManager.reschedule(
                            result.getId(),
                            retryTime,
                            result.getOutput()
                        );
                        if(!isReschedule){
                            getLogger().write(
                                MSG_ID_RESCHEDULE_ERROR,
                                new Object[]{
                                    scheduleManagerServiceName,
                                    result.getId(),
                                    result.getMasterId()
                                }
                            );
                            try{
                                scheduleManager.changeState(
                                    result.getId(),
                                    Schedule.STATE_FAILED
                                );
                            }catch(ScheduleStateControlException e){
                                getLogger().write(
                                    MSG_ID_STATE_CHANGE_ERROR,
                                    new Object[]{
                                        scheduleManagerServiceName,
                                        result.getId(),
                                        result.getMasterId(),
                                        new Integer(Schedule.STATE_FAILED)
                                    },
                                    e
                                );
                            }
                            return result;
                        }
                        final int nowState = scheduleManager.getState(result.getId());
                        switch(nowState){
                        case Schedule.STATE_RUN:
                        case Schedule.STATE_PAUSE:
                            break;
                        default:
                            getLogger().write(
                                MSG_ID_STATE_TRANS_ERROR,
                                new Object[]{
                                    scheduleManagerServiceName,
                                    result.getId(),
                                    result.getMasterId(),
                                    new Integer(nowState),
                                    new Integer(Schedule.STATE_RETRY)
                                }
                            );
                            return result;
                        }
                        final boolean isChanged = scheduleManager.changeState(
                            result.getId(),
                            nowState,
                            Schedule.STATE_RETRY
                        );
                        if(!isChanged){
                            getLogger().write(
                                MSG_ID_STATE_TRANS_ERROR,
                                new Object[]{
                                    scheduleManagerServiceName,
                                    result.getId(),
                                    result.getMasterId(),
                                    new Integer(nowState),
                                    new Integer(Schedule.STATE_RETRY)
                                }
                            );
                            return result;
                        }
                        getLogger().write(
                            MSG_ID_RESCHEDULE,
                            new Object[]{
                                scheduleManagerServiceName,
                                result.getId(),
                                result.getMasterId(),
                                new SimpleDateFormat("yyyy/MM/dd HH:mm:ss.SSS").format(retryTime)
                            }
                        );
                    }catch(ScheduleManageException e){
                        getLogger().write(
                            MSG_ID_RESCHEDULE_ERROR,
                            new Object[]{
                                scheduleManagerServiceName,
                                result.getId(),
                                result.getMasterId()
                            },
                            e
                        );
                    }catch(ScheduleStateControlException e){
                        getLogger().write(
                            MSG_ID_STATE_CHANGE_ERROR,
                            new Object[]{
                                scheduleManagerServiceName,
                                result.getId(),
                                result.getMasterId(),
                                new Integer(Schedule.STATE_RETRY)
                            },
                            e
                        );
                    }
                }
            }else{
                getLogger().write(
                    MSG_ID_END,
                    new Object[]{
                        scheduleManagerServiceName,
                        result.getId(),
                        result.getMasterId(),
                        result.getOutput()
                    }
                );
                try{
                    scheduleManager.changeState(
                        result.getId(),
                        Schedule.STATE_END,
                        result.getOutput()
                    );
                }catch(ScheduleStateControlException e){
                    getLogger().write(
                        MSG_ID_STATE_CHANGE_ERROR,
                        new Object[]{
                            scheduleManagerServiceName,
                            result.getId(),
                            result.getMasterId(),
                            new Integer(Schedule.STATE_END)
                        },
                        e
                    );
                }
            }
        }catch(Throwable th){
            if(journal != null){
                journal.addInfo(JOURNAL_KEY_EXCEPTION, th);
            }
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
                    Schedule.STATE_FAILED,
                    th
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
        }finally{
            if(journal != null){
                journal.endJournal();
            }
        }
        return result;
    }
    
    /**
     * ���g���C�������v�Z����B<p>
     *
     * @param interval ���g���C���s�Ԋu
     * @param endTime ���g���C�I������
     * @return ���g���C�����B���g���C�I���������߂��Ă���ꍇ�́Anull
     */
    protected Date calculateRetryTime(
        long interval,
        Date endTime
    ){
        final Calendar offset = Calendar.getInstance();
        Calendar end = null;
        if(endTime != null){
            end = Calendar.getInstance();
            end.setTime(endTime);
        }
        if(interval > Integer.MAX_VALUE){
            long offsetInterval = interval;
            int tmpInterval = 0;
            do{
                if(offsetInterval >= Integer.MAX_VALUE){
                    tmpInterval = Integer.MAX_VALUE;
                }else{
                    tmpInterval = (int)offsetInterval;
                }
                offset.add(Calendar.MILLISECOND, tmpInterval);
                offsetInterval -= Integer.MAX_VALUE;
            }while(offsetInterval > 0);
        }else{
            offset.add(Calendar.MILLISECOND, (int)interval);
        }
        if(end != null && offset.after(end)){
            return null;
        }else{
            return offset.getTime();
        }
    }
}