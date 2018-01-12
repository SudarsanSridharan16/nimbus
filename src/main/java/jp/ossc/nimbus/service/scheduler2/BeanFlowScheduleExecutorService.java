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

import jp.ossc.nimbus.core.*;
import jp.ossc.nimbus.service.beancontrol.*;
import jp.ossc.nimbus.service.beancontrol.interfaces.*;

/**
 * BeanFlow�X�P�W���[�����s�B<p>
 * ���s���˗����ꂽ�^�X�N��BeanFlow�Ƃ��Ď��s����B<br>
 *
 * @author M.Takata
 */
public class BeanFlowScheduleExecutorService
 extends AbstractScheduleExecutorService
 implements ScheduleExecutor, BeanFlowScheduleExecutorServiceMBean{
    
    private static final long serialVersionUID = 4412763751084029798L;
    protected ServiceName beanFlowInvokerFactoryServiceName;
    protected BeanFlowInvokerFactory beanFlowInvokerFactory;
    
    protected Map monitors;
    
    protected long controlStateChangingWaitInterval = 500l;
    protected long controlStateChangingWaitTimeout = -1l;
    
    {
        type = DEFAULT_EXECUTOR_TYPE;
    }
    
    public void setBeanFlowInvokerFactoryServiceName(ServiceName name){
        beanFlowInvokerFactoryServiceName = name;
    }
    public ServiceName getBeanFlowInvokerFactoryServiceName(){
        return beanFlowInvokerFactoryServiceName;
    }
    
    public void setControlStateChangingWaitInterval(long interval){
        controlStateChangingWaitInterval = interval;
    }
    public long getControlStateChangingWaitInterval(){
        return controlStateChangingWaitInterval;
    }
    
    public void setControlStateChangingWaitTimeout(long timeout){
        controlStateChangingWaitTimeout = timeout;
    }
    public long getControlStateChangingWaitTimeout(){
        return controlStateChangingWaitTimeout;
    }
    
    /**
     * �T�[�r�X�̐����������s���B<p>
     *
     * @exception Exception �T�[�r�X�̐��������Ɏ��s�����ꍇ
     */
    public void createService() throws Exception{
        monitors = Collections.synchronizedMap(new HashMap());
    }
    
    /**
     * �T�[�r�X�̊J�n�������s���B<p>
     *
     * @exception Exception �T�[�r�X�̊J�n�����Ɏ��s�����ꍇ
     */
    public void startService() throws Exception{
        
        if(beanFlowInvokerFactoryServiceName != null){
            beanFlowInvokerFactory = (BeanFlowInvokerFactory)ServiceManagerFactory
                .getServiceObject(beanFlowInvokerFactoryServiceName);
        }
        if(beanFlowInvokerFactory == null){
            throw new IllegalArgumentException("BeanFlowInvokerFactory is null.");
        }
    }
    
    /**
     * �T�[�r�X�̒�~�������s���B<p>
     *
     * @exception Exception �T�[�r�X�̒�~�����Ɏ��s�����ꍇ
     */
    public void stopService() throws Exception{
        monitors.clear();
    }
    
    /**
     * �T�[�r�X�̔j���������s���B<p>
     *
     * @exception Exception �T�[�r�X�̔j�������Ɏ��s�����ꍇ
     */
    public void destroyService() throws Exception{
        monitors = null;
    }
    
    /**
     * {@link BeanFlowInvokerFactory}��ݒ肷��B<p>
     *
     * @param factory BeanFlowInvokerFactory
     */
    public void setBeanFlowInvokerFactory(BeanFlowInvokerFactory factory){
        beanFlowInvokerFactory = factory;
    }
    
    /**
     * {@link BeanFlowInvokerFactory}���擾����B<p>
     *
     * @return BeanFlowInvokerFactory
     */
    public BeanFlowInvokerFactory getBeanFlowInvokerFactory(){
        return beanFlowInvokerFactory;
    }
    
    /**
     * �w�肳�ꂽ�X�P�W���[���̃^�X�N���ABeanFlow�Ƃ��đ��݂��邩�`�F�b�N����B<p>
     *
     * @param schedule �X�P�W���[��
     * @exception Exception �w�肳�ꂽ�X�P�W���[���̃^�X�N���ABeanFlow�Ƃ��đ��݂��Ȃ��ꍇ
     */
    protected void checkPreExecute(Schedule schedule) throws Exception{
        if(!beanFlowInvokerFactory.containsFlow(schedule.getTaskName())){
            throw new IllegalArgumentException("BeanFlow is not found : " + schedule.getTaskName());
        }
    }
    
    /**
     * �w�肳�ꂽ�X�P�W���[���̃^�X�N��BeanFlow�Ƃ��Ď��s����B<p>
     *
     * @param schedule �X�P�W���[��
     * @return ���s���ʂ��܂ރX�P�W���[��
     * @exception Throwable �w�肳�ꂽ�X�P�W���[���̎��s�Ɏ��s�����ꍇ
     */
    protected Schedule executeInternal(Schedule schedule) throws Throwable{
        final BeanFlowInvoker invoker = getBeanFlowInvoker(schedule);
        BeanFlowMonitor monitor = null;
        Schedule result = schedule;
        try{
            monitor = invoker.createMonitor();
            monitors.put(schedule.getId(), monitor);
            Object ret = invoker.invokeFlow(schedule, monitor);
            if(ret instanceof Schedule){
                result = (Schedule)ret;
            }else if(ret != null){
                schedule.setOutput(ret);
            }
        }catch(BeanFlowMonitorStopException e){
            result.setOutput(e);
            result.setState(Schedule.STATE_ABORT);
        }finally{
            monitors.remove(schedule.getId());
        }
        return result;
    }
    
    protected BeanFlowInvoker getBeanFlowInvoker(Schedule schedule) throws Throwable{
        return beanFlowInvokerFactory.createFlow(schedule.getTaskName());
    }
    
    // ScheduleExecutor��JavaDoc
    public boolean controlState(String id, int cntrolState)
     throws ScheduleStateControlException{
        final BeanFlowMonitor monitor = (BeanFlowMonitor)monitors.get(id);
        if(monitor == null){
            return false;
        }
        final long startTime = System.currentTimeMillis();
        switch(cntrolState){
        case Schedule.CONTROL_STATE_PAUSE:
            if(monitor.isSuspend() || monitor.isEnd()){
                return false;
            }
            monitor.suspend();
            while(!monitor.isEnd()
                 && !monitor.isSuspended()){
                if(controlStateChangingWaitTimeout > 0
                    && System.currentTimeMillis() - startTime
                        > controlStateChangingWaitTimeout){
                    throw new ScheduleStateControlException("State change timeout.");
                }
                try{
                    Thread.sleep(controlStateChangingWaitInterval);
                }catch(InterruptedException e){
                    throw new ScheduleStateControlException(e);
                }
            }
            if(monitor.isEnd()){
                return false;
            }
            getLogger().write(MSG_ID_PAUSE, new Object[]{scheduleManagerServiceName, id});
            scheduleManager.changeState(
                id,
                Schedule.STATE_RUN,
                Schedule.STATE_PAUSE
            );
            break;
        case Schedule.CONTROL_STATE_RESUME:
            if(!monitor.isSuspend()){
                return false;
            }
            monitor.resume();
            while(!monitor.isEnd()
                 && monitor.isSuspended()){
                if(controlStateChangingWaitTimeout > 0
                    && System.currentTimeMillis() - startTime
                        > controlStateChangingWaitTimeout){
                    throw new ScheduleStateControlException("State change timeout.");
                }
                try{
                    Thread.sleep(controlStateChangingWaitInterval);
                }catch(InterruptedException e){
                    throw new ScheduleStateControlException(e);
                }
            }
            getLogger().write(MSG_ID_RESUME, new Object[]{scheduleManagerServiceName, id});
            if(monitor.isEnd()){
                return false;
            }
            scheduleManager.changeState(
                id,
                Schedule.STATE_PAUSE,
                Schedule.STATE_RUN
            );
            break;
        case Schedule.CONTROL_STATE_ABORT:
            if(monitor.isStop()){
                return false;
            }
            monitor.stop();
            if(monitor.isSuspend()){
                monitor.resume();
            }
            while(!monitor.isEnd()
                 && !monitor.isStopped()){
                if(controlStateChangingWaitTimeout > 0
                    && System.currentTimeMillis() - startTime
                        > controlStateChangingWaitTimeout){
                    throw new ScheduleStateControlException("State change timeout.");
                }
                try{
                    Thread.sleep(controlStateChangingWaitInterval);
                }catch(InterruptedException e){
                    throw new ScheduleStateControlException(e);
                }
            }
            if(monitor.isEnd() && !monitor.isStopped()){
                return false;
            }
            scheduleManager.changeState(
                id,
                Schedule.STATE_RUN,
                Schedule.STATE_ABORT
            );
            break;
        }
        return true;
    }
}