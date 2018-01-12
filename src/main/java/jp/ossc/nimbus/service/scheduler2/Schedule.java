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
 * �X�P�W���[���B<p>
 *
 * @author M.Takata
 */
public interface Schedule{
    
    /**
     * �X�P�W���[���̏�ԁF�����B<p>
     */
    public static final int STATE_INITIAL = 1;
    
    /**
     * �X�P�W���[���̏�ԁF�����B<p>
     */
    public static final int STATE_ENTRY   = 2;
    
    /**
     * �X�P�W���[���̏�ԁF���s���B<p>
     */
    public static final int STATE_RUN     = 3;
    
    /**
     * �X�P�W���[���̏�ԁF����I���B<p>
     */
    public static final int STATE_END     = 4;
    
    /**
     * �X�P�W���[���̏�ԁF�ُ�I���B<p>
     */
    public static final int STATE_FAILED  = 5;
    
    /**
     * �X�P�W���[���̏�ԁF�ꎞ��~�B<p>
     */
    public static final int STATE_PAUSE   = 6;
    
    /**
     * �X�P�W���[���̏�ԁF���f�B<p>
     */
    public static final int STATE_ABORT   = 7;
    
    /**
     * �X�P�W���[���̏�ԁF���g���C�B<p>
     */
    public static final int STATE_RETRY   = 8;
    
    /**
     * �X�P�W���[���̏�ԁF�������B<p>
     */
    public static final int STATE_DISABLE = 9;
    
    /**
     * �X�P�W���[���̏�ԁF��`�O�B<p>
     */
    public static final int STATE_UNKNOWN   = -1;
    
    
    /**
     * �X�P�W���[���̐����ԁF������ԁB<p>
     */
    public static final int CONTROL_STATE_INITIAL  = 1;
    
    /**
     * �X�P�W���[���̐����ԁF�ꎞ��~�B<p>
     */
    public static final int CONTROL_STATE_PAUSE    = 2;
    
    /**
     * �X�P�W���[���̐����ԁF�ĊJ�B<p>
     */
    public static final int CONTROL_STATE_RESUME   = 3;
    
    /**
     * �X�P�W���[���̐����ԁF���f�B<p>
     */
    public static final int CONTROL_STATE_ABORT    = 4;
    
    /**
     * �X�P�W���[���̐����ԁF���䎸�s�B<p>
     */
    public static final int CONTROL_STATE_FAILED   = 5;
    
    /**
     * �X�P�W���[���̐����ԁF��`�O�B<p>
     */
    public static final int CONTROL_STATE_UNKNOWN  = -1;
    
    
    /**
     * �X�P�W���[���̃`�F�b�N��ԁF������ԁB<p>
     */
    public static final int CHECK_STATE_INITIAL  = 1;
    
    /**
     * �X�P�W���[���̃`�F�b�N��ԁF�^�C���I�[�o�[�B<p>
     */
    public static final int CHECK_STATE_TIMEOVER  = 2;
    
    /**
     * �X�P�W���[���̃`�F�b�N��ԁF��`�O�B<p>
     */
    public static final int CHECK_STATE_UNKNOWN  = -1;
    
    /**
     * �X�P�W���[����ID���擾����B<p>
     *
     * @return �X�P�W���[��ID
     */
    public String getId();
    
    /**
     * �X�P�W���[����ID��ݒ肷��B<p>
     *
     * @param id �X�P�W���[��ID
     */
    public void setId(String id);
    
    /**
     * ��������O���[�vID���擾����B<p>
     *
     * @param masterGroupId �}�X�^�O���[�vID
     * @return �O���[�vID
     */
    public String getGroupId(String masterGroupId);
    
    /**
     * ��������O���[�vID��ݒ肷��B<p>
     *
     * @param masterGroupId �}�X�^�O���[�vID
     * @param id �O���[�vID
     */
    public void setGroupId(String masterGroupId, String id);
    
    /**
     * ��������O���[�vID�̃}�b�v���擾����B<p>
     *
     * @return �L�[���}�X�^�O���[�vID�A�l���O���[�vID�̃}�b�v
     */
    public Map getGroupIdMap();
    
    /**
     * �X�P�W���[���}�X�^��ID���擾����B<p>
     *
     * @return �X�P�W���[���}�X�^ID
     */
    public String getMasterId();
    
    /**
     * �X�P�W���[���}�X�^�̃O���[�vID���擾����B<p>
     *
     * @return �X�P�W���[���}�X�^�O���[�vID
     */
    public String[] getMasterGroupIds();
    
    /**
     * �X�P�W���[�����ꂽ�������擾����B<p>
     *
     * @return �X�P�W���[������
     */
    public Date getTime();
    
    /**
     * �X�P�W���[�����ꂽ������ݒ肷��B<p>
     *
     * @param time �X�P�W���[������
     */
    public void setTime(Date time);
    
    /**
     * �X�P�W���[�����ꂽ�^�X�N�����擾����B<p>
     *
     * @return �^�X�N��
     */
    public String getTaskName();
    
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
     * �ˑ�����X�P�W���[���̈ˑ������擾����B<p>
     *
     * @return �X�P�W���[���ˑ����̔z��
     */
    public ScheduleDepends[] getDepends();
    
    /**
     * �O���[�v���ł̃X�P�W���[���̈ˑ������擾����B<p>
     *
     * @param masterGroupId �}�X�^�O���[�vID
     * @return �X�P�W���[���ˑ����̔z��
     */
    public ScheduleDepends[] getDependsInGroupMaster(String masterGroupId);
    
    /**
     * �O���[�v���ł̃X�P�W���[���̈ˑ����}�b�v���擾����B<p>
     *
     * @return �}�X�^�O���[�vID�ƃX�P�W���[���ˑ����̔z��̃}�b�v
     */
    public Map getDependsInGroupMasterMap();
    
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
     * �X�P�W���[���̃O���[�v�ˑ������擾����B<p>
     *
     * @return �X�P�W���[���O���[�v�ˑ����̔z��
     */
    public ScheduleDepends[] getDependsOnGroup();
    
    /**
     * �X�P�W���[����������}�X�^�O���[�v�ƈˑ�����O���[�v�̈ˑ������擾����B<p>
     *
     * @param masterGroupId �}�X�^�O���[�vID
     * @return �X�P�W���[���ˑ����̔z��
     */
    public ScheduleDepends[] getGroupDependsOnGroupMaster(String masterGroupId);
    
    /**
     * �X�P�W���[����������}�X�^�O���[�v�ƈˑ�����O���[�v�̈ˑ����}�b�v���擾����B<p>
     *
     * @return �}�X�^�O���[�vID�ƃX�P�W���[���ˑ����̔z��̃}�b�v
     */
    public Map getGroupDependsOnGroupMasterMap();
    
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
     * �X�P�W���[���̏������ʂ��擾����B<p>
     *
     * @return ��������
     */
    public Object getOutput();
    
    /**
     * �X�P�W���[���̏������ʂ�ݒ肷��B<p>
     *
     * @param out ��������
     */
    public void setOutput(Object out);
    
    /**
     * �ŏ��ɃX�P�W���[�����ꂽ�������擾����B<p>
     *
     * @return �ŏ��ɃX�P�W���[�����ꂽ����
     */
    public Date getInitialTime();
    
    /**
     * �X�P�W���[�����g���C���s�Ԋu[ms]���擾����B<p>
     *
     * @return ���g���C���s�Ԋu
     */
    public long getRetryInterval();
    
    /**
     * �X�P�W���[�����g���C�I���������擾����B<p>
     *
     * @return �X�P�W���[�����g���C�I������
     */
    public Date getRetryEndTime();
    
    /**
     * �X�P�W���[�����g���C�I��������ݒ肷��B<p>
     *
     * @param time �X�P�W���[�����g���C�I������
     */
    public void setRetryEndTime(Date time);
    
    /**
     * ���g���C���邩�ǂ����𔻒肷��B<p>
     *
     * @return true�̏ꍇ���g���C����
     */
    public boolean isRetry();
    
    /**
     * ���g���C���邩�ǂ�����ݒ肷��B<p>
     *
     * @param retry ���g���C����ꍇ�́Atrue
     */
    public void setRetry(boolean retry);
    
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
     * �X�P�W���[���̏�Ԃ��擾����B<p>
     *
     * @return �X�P�W���[���̏��
     */
    public int getState();
    
    /**
     * �X�P�W���[���̏�Ԃ�ݒ肷��B<p>
     * ���̃I�u�W�F�N�g��DTO�ł��邽�߁A�X�P�W���[���̏�Ԃ�ύX����ɂ́A{@link ScheduleManager#changeState(String, int)}���Ăяo���K�v������B<br>
     *
     * @param state �X�P�W���[���̏��
     */
    public void setState(int state);
    
    /**
     * �X�P�W���[���̐����Ԃ��擾����B<p>
     *
     * @return �X�P�W���[���̐�����
     */
    public int getControlState();
    
    /**
     * �X�P�W���[���̐����Ԃ�ݒ肷��B<p>
     * ���̃I�u�W�F�N�g��DTO�ł��邽�߁A�X�P�W���[���̐����Ԃ�ύX����ɂ́A{@link ScheduleManager#changeControlState(String, int)}���Ăяo���K�v������B<br>
     *
     * @param state �X�P�W���[���̐�����
     */
    public void setControlState(int state);
    
    /**
     * �X�P�W���[���̃`�F�b�N��Ԃ��擾����B<p>
     *
     * @return �X�P�W���[���̃`�F�b�N���
     */
    public int getCheckState();
    
    /**
     * �X�P�W���[���̃`�F�b�N��Ԃ�ݒ肷��B<p>
     *
     * @param state �X�P�W���[���̃`�F�b�N���
     */
    public void setCheckState(int state);
    
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
     * �X�P�W���[���̊J�n�������擾����B<p>
     *
     * @return �X�P�W���[���̊J�n����
     */
    public Date getExecuteStartTime();
    
    /**
     * �X�P�W���[���̊J�n������ݒ肷��B<p>
     *
     * @param time �X�P�W���[���̊J�n����
     */
    public void setExecuteStartTime(Date time);
    
    /**
     * �X�P�W���[���̏I���������擾����B<p>
     *
     * @return �X�P�W���[���̏I������
     */
    public Date getExecuteEndTime();
    
    /**
     * �X�P�W���[���̏I��������ݒ肷��B<p>
     *
     * @param time �X�P�W���[���̏I������
     */
    public void setExecuteEndTime(Date time);
}