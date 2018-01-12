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

import java.util.*;

/**
 * �X�P�W���[���Ǘ��B<p>
 * �X�P�W���[�����쐬�E�Ǘ����A���s���ׂ��X�P�W���[����񋟂���B<br>
 * ���s���ׂ����ǂ����́A�X�P�W���[�������A�X�P�W���[����ԁA�ˑ��֌W���画�f����B<br>
 *
 * @author M.Takata
 */
public interface ScheduleManager{
    
    /**
     * �w�肳�ꂽ���t�̃X�P�W���[�����쐬����B<p>
     *
     * @param date ���t
     * @return �쐬�����X�P�W���[���̃��X�g
     * @throws ScheduleMakeException �X�P�W���[���쐬�Ɏ��s�����ꍇ
     */
    public List makeSchedule(Date date) throws ScheduleMakeException;
    
    /**
     * �w�肳�ꂽ�X�P�W���[���}�X�^�ŁA�w�肳�ꂽ���t�̃X�P�W���[�����쐬����B<p>
     *
     * @param date ���t
     * @param master �X�P�W���[���}�X�^
     * @return �쐬�����X�P�W���[���̃��X�g
     * @throws ScheduleMakeException �X�P�W���[���쐬�Ɏ��s�����ꍇ
     */
    public List makeSchedule(Date date, ScheduleMaster master) throws ScheduleMakeException;
    
    /**
     * �w�肳�ꂽ�X�P�W���[���}�X�^�ŁA�w�肳�ꂽ���t�̃X�P�W���[�����쐬����B<p>
     *
     * @param date ���t
     * @param masters �X�P�W���[���}�X�^�̃��X�g
     * @return �쐬�����X�P�W���[���̃��X�g
     * @throws ScheduleMakeException �X�P�W���[���쐬�Ɏ��s�����ꍇ
     */
    public List makeSchedule(Date date, List masters) throws ScheduleMakeException;
    
    /**
     * �w�肳�ꂽ�X�P�W���[����ʂ̃X�P�W���[�����쐬����{@link ScheduleMaker}��o�^����B<p>
     *
     * @param scheduleType �X�P�W���[�����
     * @param maker ScheduleMaker
     */
    public void setScheduleMaker(String scheduleType, ScheduleMaker maker);
    
    /**
     * �w�肳�ꂽ�X�P�W���[����ʂ̃X�P�W���[�����쐬����{@link ScheduleMaker}���擾����B<p>
     *
     * @param scheduleType �X�P�W���[�����
     * @return ScheduleMaker
     */
    public ScheduleMaker getScheduleMaker(String scheduleType);
    
    /**
     * �X�P�W���[����ʂ�{@link ScheduleMaker}�̃}�b�s���O���擾����B<p>
     *
     * @return �X�P�W���[����ʂ�{@link ScheduleMaker}�̃}�b�s���O
     */
    public Map getScheduleMakerMap();
    
    /**
     * �X�P�W���[����ʂɊY������{@link ScheduleMaker}���o�^����Ă��Ȃ��ꍇ�Ɏg�p����f�t�H���g��ScheduleMaker��ݒ肷��B<p>
     *
     * @param maker ScheduleMaker
     */
    public void setDefaultScheduleMaker(ScheduleMaker maker);
    
    /**
     * �X�P�W���[����ʂɊY������{@link ScheduleMaker}���o�^����Ă��Ȃ��ꍇ�Ɏg�p����f�t�H���g��ScheduleMaker���擾����B<p>
     *
     * @return ScheduleMaker
     */
    public ScheduleMaker getDefaultScheduleMaker();
    
    
    
    /**
     * �S�ẴX�P�W���[���}�X�^����������B<p>
     *
     * @return �X�P�W���[���}�X�^���X�g
     * @exception ScheduleManageException �X�P�W���[���}�X�^�̌����Ɏ��s�����ꍇ
     */
    public List findAllScheduleMasters() throws ScheduleManageException;
    
    /**
     * �w�肳�ꂽ�X�P�W���[���}�X�^����������B<p>
     *
     * @param groupId �O���[�vID
     * @return �X�P�W���[���}�X�^���X�g
     * @exception ScheduleManageException �X�P�W���[���}�X�^�̌����Ɏ��s�����ꍇ
     */
    public List findScheduleMasters(String groupId) throws ScheduleManageException;
    
    /**
     * �w�肳�ꂽ�X�P�W���[���}�X�^����������B<p>
     *
     * @param id �X�P�W���[���}�X�^��ID
     * @return �X�P�W���[���}�X�^
     * @exception ScheduleManageException �X�P�W���[���}�X�^�̌����Ɏ��s�����ꍇ
     */
    public ScheduleMaster findScheduleMaster(String id) throws ScheduleManageException;
    
    /**
     * �S�ẴX�P�W���[������������B<p>
     *
     * @return �X�P�W���[�����X�g
     * @exception ScheduleManageException �X�P�W���[���̌����Ɏ��s�����ꍇ
     */
    public List findAllSchedules() throws ScheduleManageException;
    
    /**
     * �w�肳�ꂽ�X�P�W���[������������B<p>
     *
     * @param id �X�P�W���[����ID
     * @return �X�P�W���[��
     * @exception ScheduleManageException �X�P�W���[���̌����Ɏ��s�����ꍇ
     */
    public Schedule findSchedule(String id) throws ScheduleManageException;
    
    /**
     * �w�肳�ꂽ�O���[�vID�̃X�P�W���[������������B<p>
     *
     * @param groupId �X�P�W���[���}�X�^�̃O���[�vID
     * @return �X�P�W���[�����X�g
     * @exception ScheduleManageException �X�P�W���[���̌����Ɏ��s�����ꍇ
     */
    public List findSchedules(String groupId) throws ScheduleManageException;
    
    /**
     * �w�肳�ꂽ�}�X�^ID�̃X�P�W���[������������B<p>
     *
     * @param masterId �X�P�W���[���}�X�^��ID
     * @param masterGroupId �X�P�W���[���}�X�^�̃O���[�vID
     * @return �X�P�W���[�����X�g
     * @exception ScheduleManageException �X�P�W���[���̌����Ɏ��s�����ꍇ
     */
    public List findSchedules(String masterId, String masterGroupId) throws ScheduleManageException;
    
    /**
     * �w�肳�ꂽ���t�̃X�P�W���[������������B<p>
     *
     * @param date ���t
     * @return �X�P�W���[�����X�g
     * @exception ScheduleManageException �X�P�W���[���̌����Ɏ��s�����ꍇ
     */
    public List findSchedules(Date date) throws ScheduleManageException;
    
    /**
     * �w�肳�ꂽ���Ԃ̃X�P�W���[������������B<p>
     *
     * @param from ���Ԃ̊J�n����
     * @param to ���Ԃ̏I������
     * @return �X�P�W���[�����X�g
     * @exception ScheduleManageException �X�P�W���[���̌����Ɏ��s�����ꍇ
     */
    public List findSchedules(Date from, Date to) throws ScheduleManageException;
    
    /**
     * �w�肳�ꂽ��Ԃ̃X�P�W���[������������B<p>
     *
     * @param states �X�P�W���[����Ԃ̔z��
     * @return �X�P�W���[�����X�g
     * @exception ScheduleManageException �X�P�W���[���̌����Ɏ��s�����ꍇ
     */
    public List findSchedules(int[] states) throws ScheduleManageException;
    
    /**
     * �w�肳�ꂽ���ԁA�w�肳�ꂽ��Ԃ̃X�P�W���[������������B<p>
     *
     * @param from ���Ԃ̊J�n����
     * @param to ���Ԃ̏I������
     * @param states �X�P�W���[����Ԃ̔z��
     * @return �X�P�W���[�����X�g
     * @exception ScheduleManageException �X�P�W���[���̌����Ɏ��s�����ꍇ
     */
    public List findSchedules(Date from, Date to, int[] states) throws ScheduleManageException;
    
    /**
     * �w�肳�ꂽ���ԁA��ԁA�}�X�^ID�A�}�X�^�O���[�vID�̃X�P�W���[������������B<p>
     *
     * @param from ���Ԃ̊J�n����
     * @param to ���Ԃ̏I������
     * @param states �X�P�W���[����Ԃ̔z��
     * @param masterId �X�P�W���[���}�X�^��ID
     * @param masterGroupId �X�P�W���[���}�X�^�̃O���[�vID
     * @param groupId �X�P�W���[���̃O���[�vID
     * @return �X�P�W���[�����X�g
     * @exception ScheduleManageException �X�P�W���[���̌����Ɏ��s�����ꍇ
     */
    public List findSchedules(Date from, Date to, int[] states, String masterId, String masterGroupId, String groupId) throws ScheduleManageException;
    
    /**
     * �w�肳�ꂽ�����Ǝ��s��ʂŎ��s�\�ȃX�P�W���[������������B<p>
     *
     * @param date ����
     * @param executorTypes ���s��ʔz��
     * @return �X�P�W���[�����X�g
     * @exception ScheduleManageException �X�P�W���[���̌����Ɏ��s�����ꍇ
     */
    public List findExecutableSchedules(Date date, String[] executorTypes) throws ScheduleManageException;
    
    /**
     * �w�肳�ꂽ���s�L�[�Ǝ��s��ʁA�����Ŏ��s�\�ȃX�P�W���[������������B<p>
     *
     * @param date ����
     * @param executorTypes ���s��ʔz��
     * @param executorKey ���s�L�[
     * @return �X�P�W���[�����X�g
     * @exception ScheduleManageException �X�P�W���[���̌����Ɏ��s�����ꍇ
     */
    public List findExecutableSchedules(Date date, String[] executorTypes, String executorKey) throws ScheduleManageException;
    
    /**
     * �w�肳�ꂽID�̃X�P�W���[�����ˑ����Ă���X�P�W���[���i��s�X�P�W���[���j����������B<p>
     *
     * @param id �X�P�W���[����ID
     * @return �X�P�W���[�����X�g
     * @exception ScheduleManageException �X�P�W���[���̌����Ɏ��s�����ꍇ
     */
    public List findDependsSchedules(String id) throws ScheduleManageException;
    
    /**
     * �w�肳�ꂽID�̃X�P�W���[���Ɉˑ����Ă���X�P�W���[���i�㑱�X�P�W���[���j����������B<p>
     *
     * @param id �X�P�W���[����ID
     * @return �X�P�W���[�����X�g
     * @exception ScheduleManageException �X�P�W���[���̌����Ɏ��s�����ꍇ
     */
    public List findDependedSchedules(String id) throws ScheduleManageException;
    
    /**
     * �X�P�W���[����ǉ�����B<p>
     *
     * @param schedule �X�P�W���[��
     * @exception ScheduleManageException �X�P�W���[���̒ǉ��Ɏ��s�����ꍇ
     */
    public void addSchedule(Schedule schedule) throws ScheduleManageException;
    
    /**
     * �X�P�W���[��������ύX����B<p>
     *
     * @param id �X�P�W���[����ID
     * @param time ����
     * @param output ���s����
     * @return �X�P�W���[�����X�V���ꂽ�ꍇtrue
     * @exception ScheduleManageException �X�P�W���[���̍X�V�Ɏ��s�����ꍇ
     */
    public boolean reschedule(String id, Date time, Object output) throws ScheduleManageException;
    
    /**
     * �X�P�W���[�����폜����B<p>
     *
     * @param id �X�P�W���[����ID
     * @return �X�P�W���[�����폜���ꂽ�ꍇtrue
     * @exception ScheduleManageException �X�P�W���[���̍폜�Ɏ��s�����ꍇ
     */
    public boolean removeSchedule(String id) throws ScheduleManageException;
    
    /**
     * �X�P�W���[�����폜����B<p>
     *
     * @param masterId �X�P�W���[���}�X�^��ID
     * @param masterGroupId �X�P�W���[���}�X�^�̃O���[�vID
     * @return �X�P�W���[�����폜���ꂽ�ꍇtrue
     * @exception ScheduleManageException �X�P�W���[���̍폜�Ɏ��s�����ꍇ
     */
    public boolean removeScheduleByMasterId(String masterId, String masterGroupId) throws ScheduleManageException;
    
    /**
     * �w�肳�ꂽ���t�̃X�P�W���[�����폜����B<p>
     *
     * @param date ���t
     * @return �X�P�W���[�����폜���ꂽ�ꍇtrue
     * @exception ScheduleManageException �X�P�W���[���̍폜�Ɏ��s�����ꍇ
     */
    public boolean removeSchedule(Date date) throws ScheduleManageException;
    
    /**
     * �w�肳�ꂽ���ԁA��ԁA�}�X�^ID�̃X�P�W���[�����폜����B<p>
     *
     * @param from ���Ԃ̊J�n����
     * @param to ���Ԃ̏I������
     * @param states �X�P�W���[����Ԃ̔z��
     * @param masterId �X�P�W���[���}�X�^��ID
     * @param masterGroupId �X�P�W���[���}�X�^�̃O���[�vID
     * @param groupId �X�P�W���[���̃O���[�vID
     * @return �X�P�W���[�����X�g
     * @exception ScheduleManageException �X�P�W���[���̍폜�Ɏ��s�����ꍇ
     */
    public boolean removeSchedule(Date from, Date to, int[] states, String masterId, String masterGroupId, String groupId) throws ScheduleManageException;
    
    /**
     * �X�P�W���[�������s����{@link ScheduleExecutor}��ݒ肷��B<p>
     *
     * @param id �X�P�W���[����ID
     * @param key ScheduleExecutor����肷��L�[
     * @exception ScheduleManageException �X�P�W���[���̍X�V�Ɏ��s�����ꍇ
     */
    public void setExecutorKey(String id, String key) throws ScheduleManageException;
    
    /**
     * �X�P�W���[�����g���C�I��������ݒ肷��B<p>
     *
     * @param id �X�P�W���[����ID
     * @param time �X�P�W���[�����g���C�I������
     * @exception ScheduleManageException �X�P�W���[���̍X�V�Ɏ��s�����ꍇ
     */
    public void setRetryEndTime(String id, Date time) throws ScheduleManageException;
    
    /**
     * �X�P�W���[���̍ő�x������[ms]��ݒ肷��B<p>
     *
     * @param id �X�P�W���[����ID
     * @param time �X�P�W���[���ő�x������[ms]
     * @exception ScheduleManageException �X�P�W���[���̍X�V�Ɏ��s�����ꍇ
     */
    public void setMaxDelayTime(String id, long time) throws ScheduleManageException;
    
    /**
     * �w�肳�ꂽ�X�P�W���[���̏�Ԃ��擾����B<p>
     *
     * @param id �X�P�W���[��ID
     * @return ���
     * @exception ScheduleStateControlException �X�P�W���[����Ԃ̎擾�Ɏ��s�����ꍇ
     */
    public int getState(String id) throws ScheduleStateControlException;
    
    /**
     * �w�肳�ꂽ�X�P�W���[���̐����Ԃ��擾����B<p>
     *
     * @param id �X�P�W���[��ID
     * @return ������
     * @exception ScheduleStateControlException �X�P�W���[�������Ԃ̎擾�Ɏ��s�����ꍇ
     */
    public int getControlState(String id) throws ScheduleStateControlException;
    
    /**
     * �w�肳�ꂽ�X�P�W���[���̏�Ԃ�ύX����B<p>
     *
     * @param id �X�P�W���[��ID
     * @param state ���
     * @return ��Ԃ��ύX���ꂽ�ꍇtrue
     * @exception ScheduleStateControlException �X�P�W���[����Ԃ̕ύX�Ɏ��s�����ꍇ
     */
    public boolean changeState(String id, int state) throws ScheduleStateControlException;
    
    /**
     * �w�肳�ꂽ�X�P�W���[���̏�Ԃ�ύX����B<p>
     *
     * @param id �X�P�W���[��ID
     * @param oldState ���݂̏��
     * @param newState �ύX��̏��
     * @return ��Ԃ��ύX���ꂽ�ꍇtrue
     * @exception ScheduleStateControlException �X�P�W���[����Ԃ̕ύX�Ɏ��s�����ꍇ
     */
    public boolean changeState(String id, int oldState, int newState) throws ScheduleStateControlException;
    
    /**
     * �w�肳�ꂽ�X�P�W���[���̏�Ԃ�ύX����B<p>
     *
     * @param id �X�P�W���[��ID
     * @param state ���
     * @param output ���s����
     * @return ��Ԃ��ύX���ꂽ�ꍇtrue
     * @exception ScheduleStateControlException �X�P�W���[����Ԃ̕ύX�Ɏ��s�����ꍇ
     */
    public boolean changeState(String id, int state, Object output) throws ScheduleStateControlException;
    
    /**
     * �w�肳�ꂽ�X�P�W���[���̏�Ԃ�ύX����B<p>
     *
     * @param id �X�P�W���[��ID
     * @param oldState ���݂̏��
     * @param newState �ύX��̏��
     * @param output ���s����
     * @return ��Ԃ��ύX���ꂽ�ꍇtrue
     * @exception ScheduleStateControlException �X�P�W���[����Ԃ̕ύX�Ɏ��s�����ꍇ
     */
    public boolean changeState(String id, int oldState, int newState, Object output) throws ScheduleStateControlException;
    
    /**
     * �w�肳�ꂽ�X�P�W���[���̐����Ԃ�ύX����B<p>
     *
     * @param id �X�P�W���[��ID
     * @param state ���
     * @return �����Ԃ��ύX���ꂽ�ꍇtrue
     * @exception ScheduleStateControlException �X�P�W���[�������Ԃ̕ύX�Ɏ��s�����ꍇ
     */
    public boolean changeControlState(String id, int state) throws ScheduleStateControlException;
    
    /**
     * �w�肳�ꂽ�X�P�W���[���̐����Ԃ�ύX����B<p>
     *
     * @param id �X�P�W���[��ID
     * @param oldState ���݂̏��
     * @param newState �ύX��̏��
     * @return �����Ԃ��ύX���ꂽ�ꍇtrue
     * @exception ScheduleStateControlException �X�P�W���[�������Ԃ̕ύX�Ɏ��s�����ꍇ
     */
    public boolean changeControlState(String id, int oldState, int newState) throws ScheduleStateControlException;
    
    /**
     * �X�P�W���[�����䃊�X�i��o�^����B<p>
     *
     * @param listener �X�P�W���[�����䃊�X�i
     */
    public void addScheduleControlListener(ScheduleControlListener listener);
    
    /**
     * �X�P�W���[�����䃊�X�i���폜����B<p>
     *
     * @param listener �X�P�W���[���폜���X�i
     */
    public void removeScheduleControlListener(ScheduleControlListener listener);
}