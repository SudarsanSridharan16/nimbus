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
package jp.ossc.nimbus.service.scheduler;

import java.util.*;

import jp.ossc.nimbus.core.*;

/**
 * java.util.Timer���g�����X�P�W���[���T�[�r�X�B<p>
 * {@link ScheduleTask}�C���^�t�F�[�X�����������^�X�N���Ajava.util.Timer���g���ăX�P�W���[�����O���Ď��s����T�[�r�X�ł���B<br>
 *
 * @author M.Takata
 */
public class TimerSchedulerService extends ServiceBase
 implements Scheduler, TimerSchedulerServiceMBean{
    
    private static final long serialVersionUID = -4403010534401982918L;
    
    protected Timer timer;
    protected boolean isDaemon = true;
    protected boolean isScheduleOnStart;
    protected Map schedules;
    protected TimerSchedule[] attrSchedules;
    protected ServiceName[] scheduleServiceNames;
    protected ServiceName scheduleFactoryServiceName;
    protected ScheduleFactory scheduleFactory;
    protected Object scheduleFactoryKey;
    protected ServiceName scheduleStateManagerServiceName;
    
    // TimerSchedulerServiceMBean��JavaDoc
    public void setDaemon(boolean isDaemon){
        this.isDaemon = isDaemon;
    }
    // TimerSchedulerServiceMBean��JavaDoc
    public boolean isDaemon(){
        return isDaemon;
    }
    
    // TimerSchedulerServiceMBean��JavaDoc
    public void setScheduleOnStart(boolean isSchedule){
        isScheduleOnStart = isSchedule;
    }
    // TimerSchedulerServiceMBean��JavaDoc
    public boolean isScheduleOnStart(){
        return isScheduleOnStart;
    }
    
    // TimerSchedulerServiceMBean��JavaDoc
    public void setScheduleFactoryServiceName(ServiceName name){
        scheduleFactoryServiceName = name;
    }
    // TimerSchedulerServiceMBean��JavaDoc
    public ServiceName getScheduleFactoryServiceName(){
        return scheduleFactoryServiceName;
    }
    
    // TimerSchedulerServiceMBean��JavaDoc
    public void setScheduleFactoryKey(Object key){
        scheduleFactoryKey = key;
    }
    // TimerSchedulerServiceMBean��JavaDoc
    public Object getScheduleFactoryKey(){
        return scheduleFactoryKey;
    }
    
    // TimerSchedulerServiceMBean��JavaDoc
    public void setScheduleStateManagerServiceName(ServiceName name){
        scheduleStateManagerServiceName = name;
    }
    // TimerSchedulerServiceMBean��JavaDoc
    public ServiceName getScheduleStateManagerServiceName(){
        return scheduleStateManagerServiceName;
    }
    
    // TimerSchedulerServiceMBean��JavaDoc
    public void setScheduleServiceNames(ServiceName[] names){
        scheduleServiceNames = names;
    }
    
    // TimerSchedulerServiceMBean��JavaDoc
    public ServiceName[] getScheduleServiceNames(){
        return scheduleServiceNames;
    }
    
    // TimerSchedulerServiceMBean��JavaDoc
    public void addSchedule(TimerSchedule schedule){
        if(schedules.containsKey(schedule.getName())){
            removeSchedule(schedule.getName());
        }
        synchronized(schedules){
            schedules.put(schedule.getName(), schedule);
            if(getState() == Service.STARTED){
                schedule.schedule(timer);
            }
        }
    }
    
    // TimerSchedulerServiceMBean��JavaDoc
    public void reloadSchedule(String name){
        if(!schedules.containsKey(name)){
            return;
        }
        cancelSchedule(name);
        synchronized(schedules){
            TimerSchedule schedule = (TimerSchedule)schedules.get(name);
            schedule.schedule(timer);
        }
    }
    
    // TimerSchedulerServiceMBean��JavaDoc
    public void removeSchedule(String name){
        if(!schedules.containsKey(name)){
            return;
        }
        synchronized(schedules){
            TimerSchedule schedule = (TimerSchedule)schedules.remove(name);
            schedule.cancel();
        }
    }
    
    // TimerSchedulerServiceMBean��JavaDoc
    public void cancelSchedule(String name){
        if(!schedules.containsKey(name)){
            return;
        }
        synchronized(schedules){
            TimerSchedule schedule = (TimerSchedule)schedules.get(name);
            schedule.cancel();
        }
    }
    
    // TimerSchedulerServiceMBean��JavaDoc
    public void validateSchedule(String name){
        if(!schedules.containsKey(name)){
            return;
        }
        synchronized(schedules){
            TimerSchedule schedule = (TimerSchedule)schedules.get(name);
            schedule.setValid(true);
        }
    }
    
    // TimerSchedulerServiceMBean��JavaDoc
    public void invalidateSchedule(String name){
        if(!schedules.containsKey(name)){
            return;
        }
        synchronized(schedules){
            TimerSchedule schedule = (TimerSchedule)schedules.get(name);
            schedule.setValid(false);
        }
    }
    
    // TimerSchedulerServiceMBean��JavaDoc
    public void executeSchedule(String name){
        if(!schedules.containsKey(name)){
            return;
        }
        synchronized(schedules){
            TimerSchedule schedule = (TimerSchedule)schedules.get(name);
            schedule.executeForce();
        }
    }
    
    // TimerSchedulerServiceMBean��JavaDoc
    public void executeSchedule(String name, long delay){
        if(!schedules.containsKey(name)){
            return;
        }
        synchronized(schedules){
            TimerSchedule schedule = (TimerSchedule)schedules.get(name);
            schedule.executeForce(timer, delay);
        }
    }
    
    // TimerSchedulerServiceMBean��JavaDoc
    public void executeSchedule(String name, Date time){
        if(!schedules.containsKey(name)){
            return;
        }
        synchronized(schedules){
            TimerSchedule schedule = (TimerSchedule)schedules.get(name);
            schedule.executeForce(timer, time);
        }
    }
    
    // TimerSchedulerServiceMBean��JavaDoc
    public Collection runningSchedules(){
        if(schedules == null){
            return new HashSet();
        }
        TimerSchedule[] scheduleArray = null;
        synchronized(schedules){
            scheduleArray = (TimerSchedule[])schedules.values()
                .toArray(new TimerSchedule[schedules.size()]);
        }
        Map result = new TreeMap();
        for(int i = 0; i < scheduleArray.length; i++){
            if(scheduleArray[i].isRunning()){
                result.put(
                    scheduleArray[i].getScheduledExecutionTime(),
                    scheduleArray[i]
                );
            }
        }
        return result.values();
    }
    
    // TimerSchedulerServiceMBean��JavaDoc
    public Collection runningScheduleNames(){
        if(schedules == null){
            return new HashSet();
        }
        TimerSchedule[] scheduleArray = null;
        synchronized(schedules){
            scheduleArray = (TimerSchedule[])schedules.values()
                .toArray(new TimerSchedule[schedules.size()]);
        }
        Map result = new TreeMap();
        for(int i = 0; i < scheduleArray.length; i++){
            if(scheduleArray[i].isRunning()){
                result.put(
                    scheduleArray[i].getScheduledExecutionTime(),
                    scheduleArray[i].getName()
                );
            }
        }
        return result.values();
    }
    
    // TimerSchedulerServiceMBean��JavaDoc
    public Collection closedScheduleNames(){
        if(schedules == null){
            return new HashSet();
        }
        TimerSchedule[] scheduleArray = null;
        synchronized(schedules){
            scheduleArray = (TimerSchedule[])schedules.values()
                .toArray(new TimerSchedule[schedules.size()]);
        }
        Map result = new TreeMap();
        for(int i = 0; i < scheduleArray.length; i++){
            if(scheduleArray[i].isClosed()){
                result.put(
                    scheduleArray[i].getLastExecutionTime(),
                    scheduleArray[i].getName()
                );
            }
        }
        return result.values();
    }
    
    // TimerSchedulerServiceMBean��JavaDoc
    public Collection closedSchedules(){
        if(schedules == null){
            return new HashSet();
        }
        TimerSchedule[] scheduleArray = null;
        synchronized(schedules){
            scheduleArray = (TimerSchedule[])schedules.values()
                .toArray(new TimerSchedule[schedules.size()]);
        }
        Map result = new TreeMap();
        for(int i = 0; i < scheduleArray.length; i++){
            if(scheduleArray[i].isClosed()){
                result.put(
                    scheduleArray[i].getLastExecutionTime(),
                    scheduleArray[i]
                );
            }
        }
        return result.values();
    }
    
    // TimerSchedulerServiceMBean��JavaDoc
    public Collection validScheduleNames(){
        if(schedules == null){
            return new HashSet();
        }
        TimerSchedule[] scheduleArray = null;
        synchronized(schedules){
            scheduleArray = (TimerSchedule[])schedules.values()
                .toArray(new TimerSchedule[schedules.size()]);
        }
        Map result = new TreeMap();
        for(int i = 0; i < scheduleArray.length; i++){
            if(scheduleArray[i].isValid()){
                result.put(
                    scheduleArray[i].getScheduledExecutionTime(),
                    scheduleArray[i].getName()
                );
            }
        }
        return result.values();
    }
    
    // TimerSchedulerServiceMBean��JavaDoc
    public Collection validSchedules(){
        if(schedules == null){
            return new HashSet();
        }
        TimerSchedule[] scheduleArray = null;
        synchronized(schedules){
            scheduleArray = (TimerSchedule[])schedules.values()
                .toArray(new TimerSchedule[schedules.size()]);
        }
        Map result = new TreeMap();
        for(int i = 0; i < scheduleArray.length; i++){
            if(scheduleArray[i].isValid()){
                result.put(
                    scheduleArray[i].getScheduledExecutionTime(),
                    scheduleArray[i]
                );
            }
        }
        return result.values();
    }
    
    // TimerSchedulerServiceMBean��JavaDoc
    public Collection errorScheduleNames(){
        if(schedules == null){
            return new HashSet();
        }
        TimerSchedule[] scheduleArray = null;
        synchronized(schedules){
            scheduleArray = (TimerSchedule[])schedules.values()
                .toArray(new TimerSchedule[schedules.size()]);
        }
        Map result = new TreeMap();
        for(int i = 0; i < scheduleArray.length; i++){
            if(scheduleArray[i].isError()){
                result.put(
                    scheduleArray[i].getScheduledExecutionTime(),
                    scheduleArray[i].getName()
                );
            }
        }
        return result.values();
    }
    
    // TimerSchedulerServiceMBean��JavaDoc
    public Collection errorSchedules(){
        if(schedules == null){
            return new HashSet();
        }
        TimerSchedule[] scheduleArray = null;
        synchronized(schedules){
            scheduleArray = (TimerSchedule[])schedules.values()
                .toArray(new TimerSchedule[schedules.size()]);
        }
        Map result = new TreeMap();
        for(int i = 0; i < scheduleArray.length; i++){
            if(scheduleArray[i].isError()){
                result.put(
                    scheduleArray[i].getScheduledExecutionTime(),
                    scheduleArray[i]
                );
            }
        }
        return result.values();
    }
    
    /**
     * �T�[�r�X�̐����������s���B<p>
     *
     * @exception Exception �T�[�r�X�̐��������Ɏ��s�����ꍇ
     */
    public void createService() throws Exception{
        schedules = new HashMap();
    }
    
    /**
     * �T�[�r�X�̊J�n�������s���B<p>
     *
     * @exception Exception �T�[�r�X�̊J�n�����Ɏ��s�����ꍇ
     */
    public void startService() throws Exception{
        if(isScheduleOnStart){
            startSchedule(scheduleFactoryKey);
        }
    }
    
    /**
     * �T�[�r�X�̒�~�������s���B<p>
     *
     * @exception Exception �T�[�r�X�̒�~�����Ɏ��s�����ꍇ
     */
    public void stopService() throws Exception{
        stopSchedule();
    }
    
    /**
     * �T�[�r�X�̔j���������s���B<p>
     *
     * @exception Exception �T�[�r�X�̔j�������Ɏ��s�����ꍇ
     */
    public void destroyService() throws Exception{
        schedules = null;
    }
    
    // Scheduler��JavaDoc
    public synchronized void startSchedule(Object key){
        if(getState() < Service.CREATED
            && getState() > Service.STARTED){
            throw new IllegalStateException();
        }
        stopSchedule();
        timer = new Timer(isDaemon);
        schedules.putAll(createScheduleMap(key));
        if(schedules.size() != 0){
            ScheduleStateManager scheduleStateManager = null;
            if(scheduleStateManagerServiceName != null){
                scheduleStateManager
                     = (ScheduleStateManager)ServiceManagerFactory
                        .getServiceObject(scheduleStateManagerServiceName);
            }
            Iterator itr = schedules.values().iterator();
            while(itr.hasNext()){
                TimerSchedule schedule = (TimerSchedule)itr.next();
                if(scheduleStateManager != null){
                    schedule.setScheduleStateManager(scheduleStateManager);
                }
                schedule.setScheduler(this);
            }
            itr = schedules.values().iterator();
            while(itr.hasNext()){
                TimerSchedule schedule = (TimerSchedule)itr.next();
                schedule.schedule(timer);
            }
        }
    }
    
    // Scheduler��JavaDoc
    public synchronized void stopSchedule(){
        if(timer != null){
            timer.cancel();
            timer = null;
        }
        if(schedules != null){
            schedules.clear();
        }
    }
    
    // Scheduler��JavaDoc
    public synchronized void waitUntilScheduleClose(){
        waitUntilScheduleClose(0);
    }
    
    // Scheduler��JavaDoc
    public synchronized boolean waitUntilScheduleClose(long timeout){
        if(schedules == null || schedules.size() == 0){
            return true;
        }
        final Thread currentThread = Thread.currentThread();
        Runtime.getRuntime().addShutdownHook(
            new Thread(){
                public void run(){
                    currentThread.interrupt();
                }
            }
        );
        long start = System.currentTimeMillis();
        long procTime = 0;
        while(true){
            final Iterator itr = schedules.values().iterator();
            boolean isExistNotClosed = false;
            while(itr.hasNext()){
                if(!((Schedule)itr.next()).isClosed()){
                    isExistNotClosed = true;
                    break;
                }
            }
            if(!isExistNotClosed){
                break;
            }
            try{
                if(timeout > 0){
                    Thread.sleep(
                        timeout - procTime > 500 ? 500 : timeout - procTime
                    );
                }else{
                    Thread.sleep(500);
                }
            }catch(InterruptedException e){
                break;
            }
            procTime = System.currentTimeMillis() - start;
            if(timeout > 0 && procTime >= timeout){
                return false;
            }
        }
        return true;
    }
    
    // Scheduler��JavaDoc
    public Schedule getSchedule(Object key, String name){
        return (Schedule)createScheduleMap(key).get(name);
    }
    
    // Scheduler��JavaDoc
    public Schedule[] getSchedules(Object key){
        return (Schedule[])createScheduleMap(key).values()
            .toArray(new TimerSchedule[schedules.size()]);
    }
    
    /**
     * �w�肳�ꂽ�L�[�ɊY������X�P�W���[���̃}�b�v���쐬����B<p>
     *
     * @param key �X�P�W���[���̃L�[
     * @return �w�肳�ꂽ�L�[�ɊY������X�P�W���[�����ƃX�P�W���[���̃}�b�s���O
     */
    protected Map createScheduleMap(Object key){
        Map schedules = new TreeMap();
        if(scheduleFactoryServiceName != null){
            scheduleFactory = (ScheduleFactory)ServiceManagerFactory
                .getServiceObject(scheduleFactoryServiceName);
            final Schedule[] schs = scheduleFactory.getSchedules(
                key == null ? (scheduleFactoryKey == null
                     ? new Date() : scheduleFactoryKey) : key
            );
            for(int i = 0; i < schs.length; i++){
                schedules.put(schs[i].getName(), (TimerSchedule)schs[i]);
            }
        }
        if(scheduleServiceNames != null){
            for(int i = 0; i < scheduleServiceNames.length; i++){
                TimerSchedule schedule = (TimerSchedule)ServiceManagerFactory
                    .getServiceObject(scheduleServiceNames[i]);
                schedules.put(schedule.getName(), schedule);
            }
        }
        if(attrSchedules != null){
            for(int i = 0; i < attrSchedules.length; i++){
                schedules.put(attrSchedules[i].getName(), attrSchedules[i]);
            }
        }
        return schedules;
    }
    
    // Scheduler��JavaDoc
    public Schedule getSchedule(String name){
        if(schedules == null){
            return null;
        }
        synchronized(schedules){
            return (TimerSchedule)schedules.get(name);
        }
    }
    
    // Scheduler��JavaDoc
    public Schedule[] getSchedules(){
        if(schedules == null){
            return new TimerSchedule[0];
        }
        return (Schedule[])schedules.values()
            .toArray(new TimerSchedule[schedules.size()]);
    }
    
    /**
     * �X�P�W���[���t�@�N�g����ݒ肷��B<p>
     *
     * @param factory �X�P�W���[���t�@�N�g��
     */
    public void setScheduleFactory(ScheduleFactory factory){
        scheduleFactory = factory;
    }
    
    /**
     * �X�P�W���[����ݒ肷��B<p>
     *
     * @param schedules �X�P�W���[���̔z��
     */
    public void setSchedules(TimerSchedule[] schedules){
        attrSchedules = schedules;
    }
    
}