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
 * �X�P�W���[���B<p>
 * �X�P�W���[�����������ׂ��C���^�t�F�[�X�ł���B<br>
 *
 * @author M.Takata
 */
public interface Schedule{
    
    /**
     * �X�P�W���[�������擾����B<p>
     *
     * @return �X�P�W���[����
     */
    public String getName();
    
    /**
     * �X�P�W���[���̗L��/������ݒ肷��B<p>
     *
     * @param isValid �L���̏ꍇ�Atrue
     */
    public void setValid(boolean isValid);
    
    /**
     * �X�P�W���[���̗L��/�����𔻒肷��B<p>
     *
     * @return true�̏ꍇ�A�L��
     */
    public boolean isValid();
    
    /**
     * �����I�Ɏ��s�����X�P�W���[�����ǂ����𔻒肷��B<p>
     *
     * @return �����I�Ɏ��s�����X�P�W���[���̏ꍇtrue
     */
    public boolean isCyclic();
    
    /**
     * �X�P�W���[�����I�����Ă��邩�ǂ����𔻒肷��B<p>
     *
     * @return �I�����Ă���ꍇtrue
     */
    public boolean isClosed();
    
    /**
     * �X�P�W���[�������s�����ǂ����𔻒肷��B<p>
     *
     * @return ���s���̏ꍇtrue
     */
    public boolean isRunning();
    
    /**
     * �X�P�W���[�����G���[�I���������ǂ����𔻒肷��B<p>
     * �����I�Ɏ��s�����X�P�W���[���̏ꍇ�́A���O�̎��s���ʂ������B<br>
     *
     * @return �X�P�W���[�����G���[�I�������ꍇ��true
     */
    public boolean isError();
    
    /**
     * �X�P�W���[����ԊǗ���ݒ肷��B<p>
     *
     * @param manager �X�P�W���[����ԊǗ�
     */
    public void setScheduleStateManager(ScheduleStateManager manager);
    
    /**
     * �X�P�W���[����ԊǗ����擾����B<p>
     *
     * @return �X�P�W���[����ԊǗ�
     */
    public ScheduleStateManager getScheduleStateManager();
}