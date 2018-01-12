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

import java.util.*;

/**
 * �^�C�}�[�X�P�W���[���B<p>
 * {@link TimerSchedulerService}�ɓo�^����X�P�W���[�����������ׂ��C���^�t�F�[�X�ł���B<br>
 *
 * @author M.Takata
 */
public interface TimerSchedule extends Schedule{
    
    /**
     * �ˑ�����X�P�W���[���̏I����ҋ@���Ă��邩�ǂ����𔻒肷��B<p>
     *
     * @return �ˑ�����X�P�W���[���̏I����ҋ@���Ă���ꍇ��true
     */
    public boolean isWaiting();
    
    /**
     * �ˑ�����X�P�W���[���̏I���҂��Ń^�C���A�E�g�������ǂ����𔻒肷��B<p>
     *
     * @return �ˑ�����X�P�W���[���̏I���҂��Ń^�C���A�E�g�����ꍇ��true
     */
    public boolean isTimeout();
    
    /**
     * �X�P�W���[����ݒ肷��B<p>
     *
     * @param scheduler �X�P�W���[��
     */
    public void setScheduler(Scheduler scheduler);
    
    /**
     * �^�X�N���������s����B<p>
     */
    public void executeForce();
    
    /**
     * �^�X�N�������I�ɒx�����s����B<p>
     * 
     * @param timer �^�C�}�[
     * @param delay �x������[ms]
     */
    public void executeForce(Timer timer, long delay);
    
    /**
     * �^�X�N�������I�Ɏw�莞�����s����B<p>
     * 
     * @param timer �^�C�}�[
     * @param time ���s����
     */
    public void executeForce(Timer timer, Date time);
    
    /**
     * ���̃X�P�W���[�����^�C�}�[�ɓo�^����B<p>
     *
     * @param timer �^�C�}�[
     */
    public void schedule(Timer timer);
    
    /**
     * �X�P�W���[�����L�����Z������B<p>
     */
    public void cancel();
    
    /**
     * �X�P�W���[�����Ō�Ɏ��s���ꂽ�������擾����B<p>
     *
     * @return �ŏI���s����
     */
    public Date getLastExecutionTime();
    
    /**
     * �X�P�W���[�������s����鎞�����擾����B<p>
     * �܂����s����Ă��Ȃ��ꍇ�A�߂�l�͖���`�B
     *
     * @return ���s����
     */
    public Date getScheduledExecutionTime();
}