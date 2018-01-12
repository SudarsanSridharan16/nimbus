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

import java.util.Map;

/**
 * �X�P�W���[���B<p>
 * �X�P�W���[���̊Ď��Ǝ��s�˗����s���B<p>
 * �X�P�W���[���̊Ď��Ǝ��s�˗��́A���ꂼ�ꂪ�Ɨ����ē��삷�ׂ��ł��邽�߁A�ʃX���b�h�ŏ��������ׂ��ł���B<br>
 * �X�P�W���[���Ď��X���b�h�́A����I��{@link ScheduleManager}������s���ׂ�{@link Schedule}���擾���āA�X�P�W���[�����s�˗��L���[�ɓ�������B<br>
 * �܂��A���̍ۂɁA{@link ScheduleManager#changeState(String, int) ScheduleManager#changeState(id, Schedule.STATE_ENTRY)}���Ăяo���A�X�P�W���[���̏�Ԃ�J�ڂ�����B<br>
 * �X�P�W���[�����s�X���b�h�́A�X�P�W���[�����s�˗��L���[����X�P�W���[�������o���āA{@link ScheduleExecutor}�Ɏ��s���˗�����B<br>
 *
 * @author M.Takata
 */
public interface Scheduler extends ScheduleControlListener{
    
    /**
     * �X�P�W���[���̓������J�n����B<p>
     */
    public void startEntry();
    
    /**
     * �X�P�W���[���̓������J�n���Ă��邩�ǂ����𔻒肷��B<p>
     * 
     * @return �X�P�W���[���̓������J�n���Ă���ꍇtrue
     */
    public boolean isStartEntry();
    
    /**
     * �X�P�W���[���̓������~����B<p>
     */
    public void stopEntry();
    
    /**
     * �X�P�W���[�����Ǘ�����{@link ScheduleManager}���擾����B<p>
     *
     * @return ScheduleManager
     */
    public ScheduleManager getScheduleManager();
    
    /**
     * �X�P�W���[�����Ǘ�����{@link ScheduleManager}��ݒ肷��B<p>
     *
     * @param manager ScheduleManager
     */
    public void setScheduleManager(ScheduleManager manager);
    
    /**
     * �X�P�W���[�������s����{@link ScheduleExecutor}���擾����B<p>
     *
     * @param type ScheduleExecutor�̎��
     * @return ScheduleExecutor
     */
    public ScheduleExecutor getScheduleExecutor(String type);
    
    /**
     * �X�P�W���[�������s����{@link ScheduleExecutor}��ݒ肷��B<p>
     *
     * @param executor ScheduleExecutor
     */
    public void setScheduleExecutor(ScheduleExecutor executor);
    
    /**
     * �X�P�W���[�������s����{@link ScheduleExecutor}�̃}�b�s���O�擾����B<p>
     *
     * @return �L�[��ScheduleExecutor�̎�ށA�l��ScheduleExecutor�̃}�b�v
     */
    public Map getScheduleExecutors();
    
    /**
     * ���̃X�P�W���[�����s����肷��L�[���擾����B<p>
     *
     * @return �L�[
     */
    public String getExecutorKey();
}