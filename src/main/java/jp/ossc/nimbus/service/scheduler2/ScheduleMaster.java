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

import java.util.Date;
import java.util.Map;

/**
 * �X�P�W���[���}�X�^�B<p>
 * ���t�̊T�O�������Ȃ��X�P�W���[���̃}�X�^�������B<br>
 *
 * @author M.Takata
 */
public interface ScheduleMaster extends Cloneable{
    
    /**
     * �X�P�W���[���}�X�^��ID���擾����B<p>
     *
     * @return �X�P�W���[���}�X�^��ID
     */
    public String getId();
    
    /**
     * �X�P�W���[���}�X�^�̃O���[�vID���擾����B<p>
     *
     * @return �X�P�W���[���}�X�^�̃O���[�vID
     */
    public String[] getGroupIds();
    
    /**
     * �X�P�W���[�����ꂽ�^�X�N�����擾����B<p>
     *
     * @return �^�X�N��
     */
    public String getTaskName();
    
    /**
     * �X�P�W���[�����ꂽ�X�P�W���[���̎�ʂ��擾����B<p>
     *
     * @return �X�P�W���[�����
     */
    public String getScheduleType();
    
    /**
     * �X�P�W���[���̓��̓f�[�^���擾����B<p>
     *
     * @return ���̓f�[�^
     */
    public Object getInput();
    
    /**
     * �X�P�W���[���̓��̓f�[�^��ݒ肷��B<p>
     *
     * @param input ���̓f�[�^
     */
    public void setInput(Object input);
    
    /**
     * �X�P�W���[���J�n�������擾����B<p>
     *
     * @return �J�n����
     */
    public Date getStartTime();
    
    /**
     * �X�P�W���[���J�n������ݒ肷��B<p>
     *
     * @param time �J�n����
     */
    public void setStartTime(Date time);
    
    /**
     * �X�P�W���[���I���������擾����B<p>
     *
     * @return �I������
     */
    public Date getEndTime();
    
    /**
     * �X�P�W���[���I��������ݒ肷��B<p>
     *
     * @param time �I������
     */
    public void setEndTime(Date time);
    
    /**
     * �X�P�W���[���J��Ԃ����s�Ԋu[ms]���擾����B<p>
     *
     * @return �J��Ԃ����s�Ԋu
     */
    public long getRepeatInterval();
    
    /**
     * �X�P�W���[���J��Ԃ����s�Ԋu[ms]��ݒ肷��B<p>
     *
     * @param interval �J��Ԃ����s�Ԋu
     */
    public void setRepeatInterval(long interval);
    
    /**
     * �X�P�W���[�����g���C���s�Ԋu[ms]���擾����B<p>
     *
     * @return ���g���C���s�Ԋu
     */
    public long getRetryInterval();
    
    /**
     * �X�P�W���[�����g���C���s�Ԋu[ms]��ݒ肷��B<p>
     *
     * @param interval ���g���C���s�Ԋu
     */
    public void setRetryInterval(long interval);
    
    /**
     * �X�P�W���[�����g���C�I���������擾����B<p>
     *
     * @return ���g���C�I������
     */
    public Date getRetryEndTime();
    
    /**
     * �X�P�W���[�����g���C�I��������ݒ肷��B<p>
     *
     * @param time ���g���C�I������
     */
    public void setRetryEndTime(Date time);
    
    /**
     * �X�P�W���[���̍ő�x������[ms]���擾����B<p>
     *
     * @return �X�P�W���[���ő�x������
     */
    public long getMaxDelayTime();
    
    /**
     * �X�P�W���[���̍ő�x������[ms]��ݒ肷��B<p>
     *
     * @param time �X�P�W���[���ő�x������
     */
    public void setMaxDelayTime(long time);
    
    /**
     * ���̃X�P�W���[�����L�����ǂ����𔻒肷��B<p>
     *
     * @return �L���ȏꍇtrue
     */
    public boolean isEnabled();
    
    /**
     * ���̃X�P�W���[�����L�����ǂ�����ݒ肷��B<p>
     *
     * @param isEnabled true�̏ꍇ�A�L��
     */
    public void setEnabled(boolean isEnabled);
    
    /**
     * �X�P�W���[���̈ˑ������擾����B<p>
     *
     * @return �X�P�W���[���ˑ����̔z��
     */
    public ScheduleDepends[] getDepends();
    
    /**
     * �O���[�v���ł̃X�P�W���[���̈ˑ������擾����B<p>
     *
     * @param groupId �O���[�vID
     * @return �X�P�W���[���ˑ����̔z��
     */
    public ScheduleDepends[] getDependsInGroup(String groupId);
    
    /**
     * �O���[�v���ł̃X�P�W���[���̈ˑ����}�b�v���擾����B<p>
     *
     * @return �O���[�vID�ƃX�P�W���[���ˑ����̔z��̃}�b�v
     */
    public Map getDependsInGroupMap();
    
    /**
     * �X�P�W���[���ƃO���[�v�̈ˑ������擾����B<p>
     *
     * @return �X�P�W���[���ƃO���[�v�̈ˑ����̔z��
     */
    public ScheduleDepends[] getDependsOnGroup();
    
    /**
     * �X�P�W���[����������O���[�v�ƈˑ�����O���[�v�̈ˑ������擾����B<p>
     *
     * @param groupId �X�P�W���[����������O���[�vID
     * @return �ˑ�����O���[�v�̈ˑ����̔z��
     */
    public ScheduleDepends[] getGroupDependsOnGroup(String groupId);
    
    /**
     * �X�P�W���[����������O���[�v�ƈˑ�����O���[�v�̈ˑ����}�b�v���擾����B<p>
     *
     * @return �O���[�vID�ƈˑ�����O���[�v�̈ˑ����̔z��̃}�b�v
     */
    public Map getGroupDependsOnGroupMap();
    
    /**
     * ���U���Ŏ��s����{@link ScheduleExecutor}���w�肷��L�[��ݒ肷��B<p>
     *
     * @param key ScheduleExecutor����肷��L�[
     */
    public void setExecutorKey(String key);
    
    /**
     * ���U���Ŏ��s����{@link ScheduleExecutor}���w�肷��L�[���擾����B<p>
     *
     * @return ScheduleExecutor����肷��L�[
     */
    public String getExecutorKey();
    
    /**
     * {@link ScheduleExecutor}�̎�ނ�ݒ肷��B<p>
     *
     * @param type ScheduleExecutor�̎��
     */
    public void setExecutorType(String type);
    
    /**
     * {@link ScheduleExecutor}�̎�ނ��擾����B<p>
     *
     * @return ScheduleExecutor�̎��
     */
    public String getExecutorType();
    
    /**
     * �e���v���[�g���ǂ����𔻒肷��B<p>
     *
     * @return true�̏ꍇ�A�e���v���[�g
     */
    public boolean isTemplate();
    
    /**
     * �e���v���[�g���ǂ�����ݒ肷��B<p>
     *
     * @param isTemplate �e���v���[�g�̏ꍇ�Atrue
     */
    public void setTemplate(boolean isTemplate);
    
    /**
     * ���t��K�p����B<p>
     *
     * @param date ���t
     */
    public void applyDate(Date date);
    
    /**
     * �������쐬����B<p>
     *
     * @return ����
     */
    public Object clone();
}