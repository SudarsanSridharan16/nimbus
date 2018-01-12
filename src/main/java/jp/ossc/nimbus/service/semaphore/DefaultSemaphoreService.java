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

package jp.ossc.nimbus.service.semaphore;

import jp.ossc.nimbus.core.*;

/**
 * �Z�}�t�H�T�[�r�X�B<p>
 * 
 * @author H.Nakano
 */
public class DefaultSemaphoreService extends ServiceBase
 implements Semaphore, DefaultSemaphoreServiceMBean {
    
    private static final long serialVersionUID = 6475921591298262486L;
    
    /** �L���p�V�e�B�[�T�C�Y */
    private int capacity = -1 ;
    
    /** �Z�}�t�H */
    private Semaphore semaphore = null ;
    
    private long sleepTime = -1;
    private long checkInterval = -1;
    
    private String semaphoreClassName = MemorySemaphore.class.getName();
    
    private long timeoutMillis = -1L;
    private int maxWaitCount = -1;
    private long forceFreeTimeoutMillis = -1L;
    private boolean isThreadBinding = true;
    
    // Semaphore ��JavaDoc
    public boolean getResource(
        long timeout,
        int count,
        long forceFreeTimeout
    ){
        if(semaphore == null){
            return false;
        }
        return semaphore.getResource(
            timeout,
            count,
            forceFreeTimeout
        );
    }
    
    // Semaphore ��JavaDoc
    public boolean getResource(long timeout, int count) {
        if(semaphore == null){
            return false;
        }
        return semaphore.getResource(
            timeout,
            count,
            forceFreeTimeoutMillis
        );
    }
    
    // Semaphore ��JavaDoc
    public boolean getResource(long timeout) {
        if(semaphore == null){
            return false;
        }
        return semaphore.getResource(
            timeout,
            maxWaitCount,
            forceFreeTimeoutMillis
        );
    }
    
    // Semaphore ��JavaDoc
    public boolean getResource(int count){
        if(semaphore == null){
            return false;
        }
        return semaphore.getResource(
            timeoutMillis,
            count,
            forceFreeTimeoutMillis
        );
    }
    
    // Semaphore ��JavaDoc
    public boolean getResource() {
        if(semaphore == null){
            return false;
        }
        return semaphore.getResource(
            timeoutMillis,
            maxWaitCount,
            forceFreeTimeoutMillis
        );
    }
    
    // Semaphore ��JavaDoc
    public void freeResource() {
        semaphore.freeResource() ;
    }
    
    // Semaphore ��JavaDoc
    public int getResourceCapacity() {
        return capacity;
    }
    
    // Semaphore ��JavaDoc
    public void setResourceCapacity(int capa) {
        this.capacity = capa ;
    }
    
    // Semaphore ��JavaDoc
    public void setSleepTime(long millis){
        sleepTime = millis;
    }
    
    // Semaphore ��JavaDoc
    public long getSleepTime(){
        return sleepTime;
    }
    
    // Semaphore ��JavaDoc
    public void setCheckInterval(long millis){
        checkInterval = millis;
    }
    
    // Semaphore ��JavaDoc
    public long getCheckInterval(){
        return checkInterval;
    }
    
    // Semaphore ��JavaDoc
    public long getTimeoutMillis(){
        return timeoutMillis;
    }
    // Semaphore ��JavaDoc
    public void setTimeoutMillis(long timeout){
        timeoutMillis = timeout;
    }
    
    // Semaphore ��JavaDoc
    public int getMaxWaitCount(){
        return maxWaitCount;
    }
    // Semaphore ��JavaDoc
    public void setMaxWaitCount(int count){
        maxWaitCount = count;
    }
    
    // Semaphore ��JavaDoc
    public long getForceFreeTimeoutMillis(){
        return forceFreeTimeoutMillis;
    }
    // Semaphore ��JavaDoc
    public void setForceFreeTimeoutMillis(long timeout){
        forceFreeTimeoutMillis = timeout;
    }
    
    // Semaphore ��JavaDoc
    public int getResourceRemain() {
        return semaphore == null ? -1 : semaphore.getResourceRemain();
    }
    
    // Semaphore ��JavaDoc
    public int getWaitingCount(){
        return semaphore == null ? 0 : semaphore.getWaitingCount();
    }
    
    // Semaphore ��JavaDoc
    public void release(){
        semaphore.release();
    }
    
    // Semaphore ��JavaDoc
    public void accept(){
        semaphore.accept();
    }
    
    // Semaphore ��JavaDoc
    public int getMaxUsedResource(){
        return semaphore == null ? 0 : semaphore.getMaxUsedResource();
    }
    
    // Semaphore ��JavaDoc
    public int getMaxWaitedCount(){
        return semaphore == null ? 0 : semaphore.getMaxWaitedCount();
    }
    
    // Semaphore ��JavaDoc
    public void setThreadBinding(boolean isBinding){
        isThreadBinding = isBinding;
    }
    
    // Semaphore ��JavaDoc
    public boolean isThreadBinding(){
        return isThreadBinding;
    }
    
    // DefaultSemaphoreServiceMBean ��JavaDoc
    public void setSemaphoreClassName(String name) {
        semaphoreClassName = name ;
    }
    
    // DefaultSemaphoreServiceMBean ��JavaDoc
    public String getSemaphoreClassName(){
        return semaphoreClassName;
    }
    
    /**
     * �T�[�r�X�̊J�n�������s���܂��B<p>
     *
     * @exception Exception �T�[�r�X�̊J�n�����Ɏ��s�����ꍇ
     */
    public void startService() throws Exception{
        semaphore = (Semaphore)Class.forName(
            semaphoreClassName,
            true,
            NimbusClassLoader.getInstance()
        ).newInstance();
        semaphore.setResourceCapacity(getResourceCapacity());
        if(getSleepTime() > 0){
            semaphore.setSleepTime(getSleepTime());
        }
        if(getCheckInterval() > 0){
            semaphore.setCheckInterval(getCheckInterval());
        }
        semaphore.setThreadBinding(isThreadBinding());
    }
    
    /**
     * �T�[�r�X�̒�~�������s���܂��B<p>
     *
     * @exception Exception �T�[�r�X�̒�~�����Ɏ��s�����ꍇ
     */
    public void stopService(){
        semaphore.release();
    }
}
