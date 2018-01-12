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

import java.util.List;

/**
 * �u���C�N�|�C���g�B<p>
 *
 * @author M.Takata
 */
public interface BreakPoint{
    
    /**
     * �u���C�N�|�C���g�̗L��/������ݒ肷��B<p>
     *
     * @param enabled �L���ɂ���ꍇ�́Atrue
     */
    public void setEnabled(boolean enabled);
    
    /**
     * �u���C�N�|�C���g�̗L��/�����𔻒肷��B<p>
     *
     * @return true�̏ꍇ�A�L��
     */
    public boolean isEnabled();
    
    /**
     * �u���C�N�|�C���g�őҋ@���Ă���X���b�h���ĊJ����B<p>
     */
    public void resume();
    
    /**
     * �u���C�N�|�C���g�őҋ@���Ă���X���b�h��S�čĊJ����B<p>
     */
    public void resumeAll();
    
    /**
     * �u���C�N�|�C���g�őҋ@���Ă���X���b�h�����擾����B<p>
     *
     * @return �u���C�N�|�C���g�őҋ@���Ă���X���b�h���̃��X�g
     */
    public List suspendThreads();
    
    /**
     * �u���C�N�|�C���g�ɃX���b�h�������Ă���܂őҋ@����B<p>
     */
    public void waitSuspend() throws InterruptedException;
    
    /**
     * �u���C�N�|�C���g�ɃX���b�h�������Ă���܂őҋ@����B<p>
     *
     * @param timeout �ҋ@�ő厞��[ms]
     * @return �^�C���A�E�g�����ꍇ�́Afalse
     */
    public boolean waitSuspend(long timeout) throws InterruptedException;
    
    /**
     * �u���C�N�|�C���g�Ɏw�肳�ꂽ�X���b�h�������Ă���܂őҋ@����B<p>
     *
     * @param threadName �X���b�h��
     */
    public void waitSuspend(String threadName) throws InterruptedException;
    
    /**
     * �u���C�N�|�C���g�Ɏw�肳�ꂽ�X���b�h�������Ă���܂őҋ@����B<p>
     *
     * @param threadName �X���b�h��
     * @param timeout �ҋ@�ő厞��[ms]
     * @return �^�C���A�E�g�����ꍇ�́Afalse
     */
    public boolean waitSuspend(String threadName, long timeout) throws InterruptedException;
}