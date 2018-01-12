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
package jp.ossc.nimbus.util;

import java.util.Iterator;
import java.util.Collections;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;
import java.util.LinkedHashMap;

/**
 * �ҋ@�������j�^�B<p>
 * Object.wait()�ɂ́A�ʒm�A���荞�݁A�^�C���A�E�g�Ȃ��ɍĊJ�����u�X�v���A�X�E�F�C�N�A�b�v�v�Ƃ������ۂ����݂���B<br>
 * ����́Await�Anotify���f�����g�������v���O���}�[�ɂƂ��āA��������Ȗ��ł���B<br>
 * ���̃N���X�́A���̖����������������������A���[�e�B���e�B�N���X�ł���B<br>
 * 
 * @author M.Takata
 */
public class WaitSynchronizeMonitor implements SynchronizeMonitor, java.io.Serializable{
    
    private static final long serialVersionUID = -2224847461399411455L;
    
    protected transient Map monitorFlagMap = Collections.synchronizedMap(new LinkedHashMap());
    
    protected boolean isClosed;
    
    /**
     * �C���X�^���X�𐶐�����B<p>
     */
    public WaitSynchronizeMonitor(){
    }
    
    /**
     * �Ăяo���X���b�h�ɑ΂��郂�j�^������������B<p>
     * {@link #waitMonitor()}�A{@link #waitMonitor(long)}���Ăяo���O�ɁA���̃��\�b�h���ĂԕK�v������B<br>
     *
     * @return ���j�^������������O�ɒʒm����Ă����true
     */
    public synchronized boolean initMonitor(){
        return initMonitor(Thread.currentThread());
    }
    
    /**
     * �w�肵���X���b�h�ɑ΂��郂�j�^������������B<p>
     * �w�肵���X���b�h���A{@link #waitMonitor()}�A{@link #waitMonitor(long)}���Ăяo���O�ɁA���̃��\�b�h���ĂԕK�v������B<br>
     *
     * @param thread ���̃��j�^�ɑ΂��đҋ@����X���b�h
     * @return ���j�^������������O�ɒʒm����Ă����true
     */
    public synchronized boolean initMonitor(Thread thread){
        if(isClosed){
            return true;
        }
        MonitorFlag monitorFlag = (MonitorFlag)monitorFlagMap.get(thread);
        if(monitorFlag == null){
            monitorFlag = new MonitorFlag();
            monitorFlagMap.put(thread, monitorFlag);
        }
        boolean isNotify = monitorFlag.isNotify;
        monitorFlag.isWait = false;
        monitorFlag.isNotify = false;
        return isNotify;
    }
    
    /**
     * �Ăяo���X���b�h�ɑ΂��郂�j�^���������B<p>
     * ����X���b�h�ł��̃��j�^���ė��p����ꍇ�ɂ́A���̃��\�b�h���Ăяo���Ȃ��Ă��ǂ��B<br>
     */
    public synchronized void releaseMonitor(){
        final Thread currentThread = Thread.currentThread();
        monitorFlagMap.remove(currentThread);
    }
    
    /**
     * �S�Ẵ��j�^���������B<p>
     */
    public synchronized void releaseAllMonitor(){
        monitorFlagMap.clear();
    }
    
    /**
     * �ʒm������܂őҋ@����B<p>
     * {@link #notifyMonitor()}�A{@link #notifyAllMonitor()}�ɂ���Ēʒm�����܂őҋ@����B<br>
     *
     * @exception InterruptedException ���肱�܂ꂽ�ꍇ
     */
    public synchronized void initAndWaitMonitor() throws InterruptedException{
        initAndWaitMonitor(-1);
    }
    
    /**
     * �ʒm�����邩�A�w�肳�ꂽ���Ԃ��o�߂���܂őҋ@����B<p>
     * {@link #notifyMonitor()}�A{@link #notifyAllMonitor()}�ɂ���Ēʒm�����܂őҋ@����B<br>
     *
     * @return �ʒm�ɂ���ċN�����ꂽ�ꍇtrue�B�^�C���A�E�g�����ꍇfalse
     * @exception InterruptedException ���肱�܂ꂽ�ꍇ
     */
    public synchronized boolean initAndWaitMonitor(long timeout) throws InterruptedException{
        return !initMonitor() ? waitMonitor(timeout) : true;
    }
    
    /**
     * �ʒm������܂őҋ@����B<p>
     * {@link #notifyMonitor()}�A{@link #notifyAllMonitor()}�ɂ���Ēʒm�����܂őҋ@����B<br>
     *
     * @exception InterruptedException ���肱�܂ꂽ�ꍇ
     */
    public synchronized void waitMonitor() throws InterruptedException{
        waitMonitor(-1);
    }
    
    /**
     * �ʒm�����邩�A�w�肳�ꂽ���Ԃ��o�߂���܂őҋ@����B<p>
     * {@link #notifyMonitor()}�A{@link #notifyAllMonitor()}�ɂ���Ēʒm�����܂őҋ@����B<br>
     *
     * @return �ʒm�ɂ���ċN�����ꂽ�ꍇtrue�B�^�C���A�E�g�����ꍇfalse
     * @exception InterruptedException ���肱�܂ꂽ�ꍇ
     */
    public synchronized boolean waitMonitor(long timeout) throws InterruptedException{
        if(isClosed){
            return true;
        }
        long startTime = System.currentTimeMillis();
        final Thread currentThread = Thread.currentThread();
        MonitorFlag monitorFlag = (MonitorFlag)monitorFlagMap.get(currentThread);
        if(monitorFlag == null){
            return false;
        }
        if(monitorFlag.isNotify){
            return true;
        }
        monitorFlag.isWait = true;
        try{
            long waitTime = timeout;
            while(!monitorFlag.isNotify){
                if(timeout > 0){
                    if(waitTime >= 0){
                        wait(waitTime);
                    }
                    waitTime = timeout - (System.currentTimeMillis() - startTime);
                    if(waitTime <= 0){
                        break;
                    }
                }else{
                    wait();
                }
                if(isClosed){
                    return true;
                }
            }
        }finally{
            monitorFlag.isWait = false;
        }
        boolean isNotify = monitorFlag.isNotify;
        monitorFlag.isNotify = false;
        return isNotify;
    }
    
    /**
     * �ҋ@���Ă���ŏ��̃X���b�h�ɒʒm����B<p>
     */
    public synchronized void notifyMonitor(){
        if(monitorFlagMap.size() != 0){
            ((MonitorFlag)monitorFlagMap.values().iterator().next()).isNotify = true;
        }
        notifyAll();
    }
    
    /**
     * �ҋ@���Ă���S�ẴX���b�h�ɒʒm����B<p>
     */
    public synchronized void notifyAllMonitor(){
        if(monitorFlagMap.size() != 0){
            final Iterator itr = monitorFlagMap.values().iterator();
            while(itr.hasNext()){
                ((MonitorFlag)itr.next()).isNotify = true;
            }
        }
        notifyAll();
    }
    
    /**
     * ���̃X���b�h���ʒm�ɂ���ċN�����ꂽ���ǂ����𔻒肷��B<p>
     * 
     * @return �ʒm�ɂ���ċN�����ꂽ�ꍇ��true
     */
    public synchronized boolean isNotify(){
        if(isClosed){
            return true;
        }
        final Thread currentThread = Thread.currentThread();
        MonitorFlag monitorFlag = (MonitorFlag)monitorFlagMap.get(currentThread);
        return monitorFlag != null && monitorFlag.isNotify;
    }
    
    /**
     * �ŏ��ɑҋ@���Ă���X���b�h�����݂̃X���b�h���ǂ����𔻒肷��B<p>
     * 
     * @return �ŏ��ɑҋ@���Ă���X���b�h�����݂̃X���b�h�ł���ꍇ��true
     */
    public synchronized boolean isFirst(){
        if(monitorFlagMap.size() == 0){
            return false;
        }
        final Thread currentThread = Thread.currentThread();
        if(!monitorFlagMap.containsKey(currentThread)){
            return false;
        }
        Thread first = (Thread)monitorFlagMap.keySet().iterator().next();
        return first == null ? false : first.equals(currentThread);
    }
    
    /**
     * �ҋ@���Ă���X���b�h�����݂��邩�ǂ����𔻒肷��B<p>
     * 
     * @return �ҋ@���Ă���X���b�h�����݂���ꍇ��true
     */
    public synchronized boolean isWait(){
        if(monitorFlagMap.size() != 0){
            final Iterator itr = monitorFlagMap.values().iterator();
            while(itr.hasNext()){
                if(((MonitorFlag)itr.next()).isWait){
                    return true;
                }
            }
        }
        return false;
    }
    
    /**
     * �ҋ@���Ă���X���b�h�̐����擾����B<p>
     * 
     * @return �ҋ@���Ă���X���b�h�̐�
     */
    public synchronized int getWaitCount(){
        int count = 0;
        if(monitorFlagMap.size() != 0){
            final Iterator itr = monitorFlagMap.values().iterator();
            while(itr.hasNext()){
                if(((MonitorFlag)itr.next()).isWait){
                    count++;
                }
            }
        }
        return count;
    }
    
    /**
     * �ҋ@���Ă���X���b�h���擾����B<p>
     * 
     * @return �ҋ@���Ă���X���b�h�̔z��
     */
    public synchronized Thread[] getWaitThreads(){
        final List result = new ArrayList();
        if(monitorFlagMap.size() != 0){
            final Iterator entries = monitorFlagMap.entrySet().iterator();
            while(entries.hasNext()){
                Map.Entry entry = (Map.Entry)entries.next();
                if(((MonitorFlag)entry.getValue()).isWait){
                    result.add(entry.getKey());
                }
            }
        }
        return (Thread[])result.toArray(new Thread[result.size()]);
    }
    
    public synchronized void close(){
        isClosed = true;
        notifyAllMonitor();
    }
    
    private void readObject(java.io.ObjectInputStream in) throws java.io.IOException, ClassNotFoundException{
        in.defaultReadObject();
        monitorFlagMap = Collections.synchronizedMap(new LinkedHashMap());
    }
    
    protected static final class MonitorFlag implements java.io.Serializable{
        private static final long serialVersionUID = -4683612743846239879L;
        public boolean isWait;
        public boolean isNotify;
    }
}