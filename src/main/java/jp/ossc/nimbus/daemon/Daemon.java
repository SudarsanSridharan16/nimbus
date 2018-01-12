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
package jp.ossc.nimbus.daemon;

import jp.ossc.nimbus.util.*;

/**
 * �f�[�����X���b�h�B<p>
 * �f�[�����X���b�h�̈��S�Ȑ�����߂��������̂ł��B<br>
 *
 * @author H.Nakano
 */
public class Daemon implements Runnable, DaemonControl{
    
    //## �N���X�����o�[�ϐ��錾 ##
    
    /**
     * �f�[�����ғ�������t���O�B<p>
     */
    protected volatile boolean isRunning;
    
    /**
     * �u���b�L���O��Ԕ���t���O�B<p>
     */
    protected volatile boolean isBlocking;
    
    /**
     * �T�X�y���h��Ԕ���t���O�B<p>
     */
    protected volatile boolean isSusupend;
    protected SynchronizeMonitor susupendMonitor = new WaitSynchronizeMonitor();
    
    /**
     * �f�[�����X���b�h�I�u�W�F�N�g�B<p>
     */
    protected transient Thread daemonThread;
    
    /**
     * �f�[�����X���b�h���B<p>
     */
    protected String threadName;
    
    /**
     * �f�[�����ݒ�t���O�B<p>
     * �f�t�H���g�́Atrue�B
     */
    protected boolean isDaemon = true;
    
    /**
     * �f�[���������i�u���B<p>
     */
    protected DaemonRunnable runnable;
    
    /**
     * �K�x�[�W���t���O�B<p>
     */
    protected boolean isGarbaging;
    
    /**
     * ����t���O�B<p>
     */
    protected boolean isConsuming;
    
    /**
     * �������t���O�B<p>
     */
    protected boolean isProviding;
    
    /**
     * �D�揇�ʁB<p>
     */
    protected int priority = -1;
    
    protected long suspendCheckInterval = 500l;
    
    protected long lastProvideTime = -1;
    
    /**
     * �C���X�^���X�𐶐�����B<p>
     *
     * @param run �f�[�������������s����DaemonRunnable
     */
    public Daemon(DaemonRunnable run){
        runnable = run;
    }
    
    /**
     * {@link DaemonRunnable}���擾����B<p>
     * 
     * @return DaemonRunnable
     */
    public DaemonRunnable getDaemonRunnable(){
        return runnable;
    }
    
    /**
     * �f�[�����X���b�h���擾����B<p>
     *
     * @return �f�[�����X���b�h
     */
    public Thread getDaemonThread(){
        return daemonThread;
    }
    
    /**
     * �f�[�����X���b�h�̖��O��ݒ肷��B<p>
     * 
     * @param name �f�[�����X���b�h�̖��O
     */
    public void setName(String name){
        threadName = name;
        if(daemonThread != null){
            daemonThread.setName(name);
        }
    }
    
    /**
     * �f�[�����X���b�h�̖��O���擾����B<p>
     * 
     * @return �f�[�����X���b�h�̖��O
     */
    public String getName(){
        return threadName;
    }
    
    /**
     * �f�[�����X���b�h�̗D�揇�ʂ�ݒ肷��B<p>
     * 
     * @param newPriority �f�[�����X���b�h�̗D�揇��
     */
    public void setPriority(int newPriority){
        priority = newPriority;
        if(daemonThread != null){
            daemonThread.setPriority(newPriority);
        }
    }
    
    /**
     * �f�[�����X���b�h�̗D�揇�ʂ��擾����B<p>
     * 
     * @return �f�[�����X���b�h�̗D�揇��
     */
    public int getPriority(){
        return daemonThread == null ? priority : daemonThread.getPriority();
    }
    
    /**
     * �ꎞ��~�����畜�A����ׂ������`�F�b�N����Ԋu[ms]��ݒ肷��B<p>
     * �f�t�H���g�́A500[ms]�B<br>
     *
     * @param interval �`�F�b�N�Ԋu[ms]
     */
    public void setSuspendCheckInterval(long interval){
        suspendCheckInterval = interval;
    }
    
    /**
     * �ꎞ��~�����畜�A����ׂ������`�F�b�N����Ԋu[ms]���擾����B<p>
     *
     * @return �`�F�b�N�Ԋu[ms]
     */
    public long getSuspendCheckInterval(){
        return suspendCheckInterval;
    }
    
    /**
     * �ғ���Ԃ𔻒肷��B<p>
     * 
     * @return true�̏ꍇ�A�ғ���
     */
    public boolean isRunning(){
        return this.isRunning;
    }
    
    /**
     * �ғ���Ԃ�ݒ肷��B<p>
     * 
     * @param runFlg �ғ����ɐݒ肵�����ꍇtrue
     */
    public void setRunning(boolean runFlg){
        this.isRunning = runFlg;
    }
    
    /**
     * �u���b�L���O��Ԃ𔻒肷��B<p>
     *
     * @return true�̏ꍇ�A�u���b�N��
     */
    public boolean isBlocking(){
        return this.isBlocking;
    }
    
    /**
     * �u���b�L���O��Ԃ�ݒ肷��B<p>
     *
     * @param blockFlg �u���b�N���ɐݒ肵�����ꍇtrue
     */
    public void setBlocking(boolean blockFlg){
        this.isBlocking = blockFlg;
    }
    
    /**
     * �f�[�����t���O��ݒ肷��B<p>
     * �f�t�H���g�ł́Atrue�B
     *
     * @param isDaemon �f�[�����X���b�h�ɂ���ꍇtrue
     */
    public void setDaemon(boolean isDaemon){
        this.isDaemon = isDaemon;
    }
    
    /**
     * �f�[�����X���b�h���ǂ������肷��B<p>
     *
     * @return true�̏ꍇ�A�f�[�����X���b�h
     */
    public boolean isDaemon(){
        return isDaemon ;
    }
    
    /**
     * ���������ǂ����𔻒肷��B<p>
     *
     * @return �������̏ꍇtrue
     */
    public boolean isProviding(){
        return isProviding;
    }
    
    /**
     * ������ǂ����𔻒肷��B<p>
     *
     * @return ����̏ꍇtrue
     */
    public boolean isConsuming(){
        return isConsuming;
    }
    
    /**
     * �ꎞ��~�����ǂ����𔻒肷��B<p>
     *
     * @return �ꎞ��~���̏ꍇtrue
     */
    public boolean isSusupend(){
        return isSusupend;
    }
    
    public void sleep(long interval, boolean isFirstSleep) throws InterruptedException{
        if(lastProvideTime == -1){
            if(isFirstSleep){
                Thread.sleep(interval);
            }
        }else{
            final long currentTime = System.currentTimeMillis();
            final long sleepTime = interval - (lastProvideTime - currentTime);
            if(sleepTime > 0){
                Thread.sleep(sleepTime);
            }
        }
    }
    
    /**
     * ���̃f�[�����X���b�h�̏�Ԃ��擾����B<p>
     *
     * @return �f�[�����X���b�h�̏��
     */
    public CsvArrayList getDeamonInfo(){
        final CsvArrayList parser = new CsvArrayList();
        
        //�f�[�����X���b�h��
        parser.add(getName());
        
        //�����j���O���
        parser.add(String.valueOf(isRunning()));
        
        //�u���b�L���O���
        parser.add(String.valueOf(isBlocking()));
        
        return parser;
    }
    
    /**
     * �X���b�h���J�n����B<p>
     */
    public synchronized void start(){
        // ���łɎ��s���Ȃ烊�^�[��
        if(isRunning()){
            return;
        }else if(!runnable.onStart()){
            return;
        }
        // �V�����X���b�h���쐬����
        if(getName() == null || getName().length() == 0){
            daemonThread = new Thread(this);
        }else{
            daemonThread = new Thread(this, getName());
        }
        daemonThread.setDaemon(isDaemon());
        if(priority > 0){
            daemonThread.setPriority(priority);
        }
        
        // ���s���t���O�ݒ�
        setRunning(true);
        setBlocking(true);
        if(isSusupend){
            susupendMonitor.initMonitor(daemonThread);
        }
        
        daemonThread.start();
    }
    
    /**
     * �X���b�h���~����B<p>
     * �X���b�h����~����܂ŁA60�b�����ҋ@����B
     */
    public synchronized void stop(){
        stop(60000);
    }
    
    /**
     * �X���b�h���~����B<p>
     * �X���b�h����~����܂ŁA�w�肳�ꂽ���Ԃ����ҋ@����B
     *
     * @param millis �ҋ@����[ms]
     */
    public synchronized void stop(long millis){
        if(daemonThread == null){
            // �f�[�����͒�~��
            return;
        }else if(!runnable.onStop()){
            return;
        }
        
        setRunning(false);
        if(isBlocking() && daemonThread != null && daemonThread.isAlive()){
            isSusupend = false;
        }
        if(daemonThread != null){
            daemonThread.interrupt();
            
            if(isConsuming){
                if(!daemonThread.isInterrupted()){
                    daemonThread.interrupt();
                }
            }
        }
        if(millis >= 0){
            stopWait(millis);
        }
    }
    
    /**
     * �X���b�h����~����܂ő҂B<p>
     */
    public synchronized void stopWait(){
        stopWait(0);
    }
    
    /**
     * �X���b�h����~����܂ő҂B<p>
     */
    public synchronized void stopWait(long millis){
        long startTime = System.currentTimeMillis();
        try{
            if(daemonThread != null && daemonThread.isAlive()){
                daemonThread.join(millis);
            }
        }catch(InterruptedException e){
            Thread.interrupted();
            long processTime = System.currentTimeMillis() - startTime;
            if(millis > processTime){
                stopWait(millis - processTime);
            }
        }
        daemonThread = null;
    }
    
    /**
     * �X���b�h�ɒ�~���߂��o���B<p>
     */
    public synchronized void stopNoWait(){
        stop(-1);
    }
    
    /**
     * �X���b�h���ꎞ��~����B<p>
     */
    public synchronized void suspend(){
        if(isSusupend){
            // �f�[�����͒�~��
            return;
        }else if(!runnable.onSuspend()){
            return;
        }
        isSusupend = true;
        if(daemonThread != null){
            susupendMonitor.initMonitor(daemonThread);
        }
    }
    
    /**
     * �X���b�h���ĊJ����B<p>
     */
    public synchronized void resume(){
        if(!isSusupend){
            // �f�[�����͒�~��
            return;
        }else if(!runnable.onResume()){
            return;
        }
        isSusupend = false;
        susupendMonitor.notifyMonitor();
    }
    
    /**
     * �f�[�����X���b�h�����s����B<p>
     */
    public void run(){
        boolean breakFlg = false;
        Object waitObj = null;
        try{
            //���[�v�͈ȉ��̂Q�̕ϐ��𐧌䂷�邱�ƁB
            while(isRunning()){
                setBlocking(true);
                // ���炩�̃A�N�V������҂ꍇ��InterruptedException
                // ���L���b�`���邱��
                while(isSusupend){
                    try{
                        susupendMonitor.waitMonitor(suspendCheckInterval);
                    }catch(InterruptedException e1){
                        Thread.interrupted();
                        breakFlg = true;
                        break;
                    }
                }
                if(breakFlg){
                    breakFlg = false;
                    continue;
                }
                isProviding = true;
                try{
                    lastProvideTime = System.currentTimeMillis();
                    waitObj = runnable.provide(this);
                }catch(InterruptedException e){
                    if(!breakFlg && isRunning()){
                        e.printStackTrace();
                    }else{
                        break;
                    }
                }catch(Throwable e){
                    if(!breakFlg && isRunning()){
                        e.printStackTrace();
                    }
                    Thread.interrupted();
                    waitObj = null;
                }
                isProviding = false;
                while(this.isSusupend){
                    try{
                        susupendMonitor.waitMonitor(suspendCheckInterval);
                    }catch(InterruptedException e2){
                        Thread.interrupted();
                        breakFlg= true;
                        break;
                    }
                }
                setBlocking(false);
                if(breakFlg){
                    breakFlg = false;
                    continue;
                }
                // ��������
                isConsuming = true;
                try{
                    runnable.consume(waitObj, this);
                }catch(InterruptedException e){
                    if(!breakFlg && isRunning()){
                        e.printStackTrace();
                    }
                }catch(Throwable e){
                    if(!breakFlg && isRunning()){
                        e.printStackTrace();
                    }
                }
                isConsuming = false;
            }
        }finally{
            // �I�����̓L���[�̎c��������o��
            setRunning(false);
            isGarbaging = true;
            try{
                runnable.garbage();
            }catch(Throwable e){
                e.printStackTrace();
            }
            isGarbaging = false;
        }
    }
}
