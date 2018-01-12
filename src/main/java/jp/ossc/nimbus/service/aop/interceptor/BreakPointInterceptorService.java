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
package jp.ossc.nimbus.service.aop.interceptor;

import jp.ossc.nimbus.core.*;
import jp.ossc.nimbus.service.aop.*;
import jp.ossc.nimbus.util.SynchronizeMonitor;
import jp.ossc.nimbus.util.WaitSynchronizeMonitor;

import java.util.*;

/**
 * �u���C�N�|�C���g�C���^�[�Z�v�^�B<p>
 * ���\�b�h�̌Ăяo���ɑ΂��āA�C�ӂ̊ԁA�X���b�h�𒆒f����C���^�[�Z�v�^�ł���B<br>
 * �ȉ��ɁA�u���C�N�|�C���g�C���^�[�Z�v�^�̃T�[�r�X��`��������B<br>
 * <pre>
 * &lt;?xml version="1.0" encoding="Shift_JIS"?&gt;
 * 
 * &lt;server&gt;
 *     
 *     &lt;manager name="Sample"&gt;
 *         
 *         &lt;service name="BreakPointInterceptor"
 *                  code="jp.ossc.nimbus.service.aop.interceptor.BreakPointInterceptorService"/&gt;
 *         
 *     &lt;/manager&gt;
 *     
 * &lt;/server&gt;
 * </pre>
 *
 * @author M.Takata
 */
public class BreakPointInterceptorService extends ServiceBase
 implements Interceptor, BreakPointInterceptorServiceMBean{
    
    private static final long serialVersionUID = -2667830848395155759L;
    
    private SynchronizeMonitor monitor = new WaitSynchronizeMonitor();
    private SynchronizeMonitor listenerMonitor = new WaitSynchronizeMonitor();
    private boolean enabled = true;
    private int breakPoint = BREAK_POINT_IN;
    private long timeout;
    private List threads = new ArrayList();
    
    // BreakPointInterceptorServiceMBean��JavaDoc
    public void setEnabled(boolean enabled){
        this.enabled = enabled;
    }
    // BreakPointInterceptorServiceMBean��JavaDoc
    public boolean isEnabled(){
        return enabled;
    }
    
    // BreakPointInterceptorServiceMBean��JavaDoc
    public void setMonitor(SynchronizeMonitor monitor){
        this.monitor = monitor;
    }
    // BreakPointInterceptorServiceMBean��JavaDoc
    public SynchronizeMonitor getMonitor(){
        return monitor;
    }
    
    // BreakPointInterceptorServiceMBean��JavaDoc
    public void setBreakPoint(int breakPoint){
        this.breakPoint = breakPoint;
    }
    // BreakPointInterceptorServiceMBean��JavaDoc
    public int getBreakPoint(){
        return breakPoint;
    }
    
    // BreakPointInterceptorServiceMBean��JavaDoc
    public void setTimeout(long timeout){
        this.timeout = timeout;
    }
    // BreakPointInterceptorServiceMBean��JavaDoc
    public long getTimeout(){
        return timeout;
    }
    
    // BreakPointInterceptorServiceMBean��JavaDoc
    public void resume(){
        synchronized(monitor){
            monitor.notifyMonitor();
        }
    }
    
    // BreakPointInterceptorServiceMBean��JavaDoc
    public void resumeAll(){
        synchronized(monitor){
            monitor.notifyAllMonitor();
        }
    }
    
    // BreakPointInterceptorServiceMBean��JavaDoc
    public List suspendThreads(){
        synchronized(threads){
            return new ArrayList(threads);
        }
    }
    
    // BreakPointInterceptorServiceMBean��JavaDoc
    public void waitSuspend() throws InterruptedException{
        if(getState() != STARTED){
            return;
        }
        synchronized(listenerMonitor){
            synchronized(threads){
                if(threads.size() != 0){
                    return;
                }
            }
            listenerMonitor.initMonitor();
            listenerMonitor.waitMonitor();
        }
    }
    
    // BreakPointInterceptorServiceMBean��JavaDoc
    public boolean waitSuspend(long timeout) throws InterruptedException{
        if(getState() != STARTED){
            return false;
        }
        synchronized(listenerMonitor){
            synchronized(threads){
                if(threads.size() != 0){
                    return true;
                }
            }
            listenerMonitor.initMonitor();
            listenerMonitor.waitMonitor(timeout);
            synchronized(threads){
                if(threads.size() == 0){
                    return false;
                }else{
                    return true;
                }
            }
        }
    }
    
    // BreakPointInterceptorServiceMBean��JavaDoc
    public void waitSuspend(String threadName) throws InterruptedException{
        if(getState() != STARTED){
            return;
        }
        synchronized(listenerMonitor){
            synchronized(threads){
                if(threads.contains(threadName)){
                    return;
                }
            }
            listenerMonitor.initMonitor();
            listenerMonitor.waitMonitor();
            synchronized(threads){
                if(threads.contains(threadName)){
                    return;
                }
            }
            waitSuspend(threadName);
        }
    }
    
    // BreakPointInterceptorServiceMBean��JavaDoc
    public boolean waitSuspend(String threadName, long timeout) throws InterruptedException{
        if(getState() != STARTED){
            return false;
        }
        synchronized(listenerMonitor){
            synchronized(threads){
                if(threads.contains(threadName)){
                    return true;
                }
            }
            final long startTime = System.currentTimeMillis();
            listenerMonitor.initMonitor();
            listenerMonitor.waitMonitor(timeout);
            final long waitTime = System.currentTimeMillis() - startTime;
            synchronized(threads){
                if(threads.contains(threadName)){
                    return true;
                }else if(waitTime >= timeout){
                    return false;
                }
            }
            return waitSuspend(threadName, timeout - waitTime);
        }
    }
    
    public void stopService() throws Exception{
        synchronized(listenerMonitor){
            listenerMonitor.notifyAllMonitor();
        }
        synchronized(monitor){
            monitor.notifyAllMonitor();
        }
    }
    
    /**
     * �w�肳�ꂽ�u���C�N�|�C���g��{@link #BREAK_POINT_IN}�̏ꍇ�A{@link #resume()}��������{@link #resumeAll()}���Ăяo�����܂őҋ@���āA���̃C���^�[�Z�v�^���Ăяo���B�u���C�N�|�C���g��{@link #BREAK_POINT_OUT}�̏ꍇ�A���̃C���^�[�Z�v�^���Ăяo������A{@link #resume()}��������{@link #resumeAll()}���Ăяo�����܂őҋ@����B<p>
     * �T�[�r�X���J�n����Ă��Ȃ��ꍇ�́A���������Ɏ��̃C���^�[�Z�v�^���Ăяo���B<br>
     *
     * @param context �Ăяo���̃R���e�L�X�g���
     * @param chain ���̃C���^�[�Z�v�^���Ăяo�����߂̃`�F�[��
     * @return �Ăяo�����ʂ̖߂�l
     * @exception Throwable �Ăяo����ŗ�O�����������ꍇ�A�܂��͂��̃C���^�[�Z�v�^�ŔC�ӂ̗�O�����������ꍇ�B�A���A�{���Ăяo����鏈����throw���Ȃ�RuntimeException�ȊO�̗�O��throw���Ă��A�Ăяo�����ɂ͓`�d����Ȃ��B
     */
    public Object invoke(
        InvocationContext context,
        InterceptorChain chain
    ) throws Throwable{
        if(getState() == STARTED && enabled && breakPoint == BREAK_POINT_IN){
            breakpoint();
        }
        try{
            return chain.invokeNext(context);
        }finally{
            if(getState() == STARTED && enabled && breakPoint == BREAK_POINT_OUT){
                breakpoint();
            }
        }
    }
    
    private void breakpoint(){
        Thread thread = Thread.currentThread();
        try{
            synchronized(listenerMonitor){
                synchronized(threads){
                    threads.add(thread.getName());
                }
                listenerMonitor.notifyAllMonitor();
            }
            synchronized(monitor){
                try{
                    monitor.initMonitor();
                    if(timeout > 0){
                        monitor.waitMonitor(timeout);
                    }else{
                        monitor.waitMonitor();
                    }
                }catch(InterruptedException e){
                }
            }
        }finally{
            synchronized(listenerMonitor){
                synchronized(threads){
                    threads.remove(thread.getName());
                }
            }
        }
    }
}
