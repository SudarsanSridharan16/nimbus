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

/**
 * �X�P�W���[�����s�B<p>
 * ���s���˗����ꂽ�^�X�N�����s���A�X�P�W���[���̏�ԑJ�ڂ�{@link ScheduleManager}�ɒʒm����B<br>
 * �܂��A�X�P�W���[���̎��s��A{@link Schedule#isRetry()}��true�̏ꍇ�́A�X�P�W���[���̃��g���C�Ԋu�ɏ]���āA���̃X�P�W���[�������ɍăX�P�W���[������B<br>
 *
 * @author M.Takata
 */
public interface ScheduleExecutor{
    
    /**
     * ���̃X�P�W���[�����s����肷��L�[���擾����B<p>
     *
     * @return �L�[
     */
    public String getKey();
    
    /**
     * ���̃X�P�W���[�����s�̎�ނ��擾����B<p>
     *
     * @return �X�P�W���[�����s�̎��
     */
    public String getType();
    
    /**
     * �w�肳�ꂽ�X�P�W���[�������s����B<p>
     *
     * @param schedule �X�P�W���[��
     * @return �X�P�W���[��
     */
    public Schedule execute(Schedule schedule);
    
    /**
     * ���s���̎w�肳�ꂽ�X�P�W���[�����A�w�肳�ꂽ���s��Ԃɐ��䂷��B<p>
     * 
     * @param id �X�P�W���[��ID
     * @param cntrolState ���s���
     * @return ���s��Ԃ̂��ύX���ꂽ�ꍇtrue
     * @exception ScheduleStateControlException ���s���̃X�P�W���[���̏�ԕύX�Ɏ��s�����ꍇ
     */
    public boolean controlState(String id, int cntrolState) throws ScheduleStateControlException;
    
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
}