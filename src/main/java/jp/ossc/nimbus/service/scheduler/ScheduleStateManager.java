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

/**
 * �X�P�W���[����ԊǗ��B<p>
 *
 * @author M.Takata
 */
public interface ScheduleStateManager{
    
    /**
     * �X�P�W���[���̃X�e�[�^�X�F�s���i�Ǘ�����Ă��Ȃ��j�B<p>
     */
    public static final int STATE_UNKNOWN = -1;
    
    /**
     * �X�P�W���[���̃X�e�[�^�X�F�����B<p>
     */
    public static final int STATE_INIT = 0;
    
    /**
     * �X�P�W���[���̃X�e�[�^�X�F���s�҂��B<p>
     */
    public static final int STATE_WAIT = 1;
    
    /**
     * �X�P�W���[���̃X�e�[�^�X�F�ˑ��֌W�ɂ����s�҂��B<p>
     */
    public static final int STATE_DEPENDS_WAIT = 2;
    
    /**
     * �X�P�W���[���̃X�e�[�^�X�F���s���B<p>
     */
    public static final int STATE_RUN = 3;
    
    /**
     * �X�P�W���[���̃X�e�[�^�X�F�I���B<p>
     */
    public static final int STATE_CLOSE = 4;
    
    /**
     * �X�P�W���[���̃X�e�[�^�X�F�����B<p>
     */
    public static final int STATE_INVALID = 5;
    
    /**
     * �X�P�W���[���̏�Ԃ�ύX����B<p>
     *
     * @param name �X�P�W���[����
     * @param state �X�P�W���[���̏��
     */
    public void changeState(String name, int state);
    
    /**
     * �X�P�W���[���̏�Ԃ��擾����B<p>
     *
     * @param name �X�P�W���[����
     * @return �X�P�W���[���̏��
     */
    public int getState(String name);
    
    /**
     * �X�P�W���[���̏�ԊǗ����폜����B<p>
     *
     * @param name �X�P�W���[����
     */
    public void clearState(String name);
    
    /**
     * �S�ẴX�P�W���[���̏�ԊǗ����폜����B<p>
     */
    public void clearAllStates();
}