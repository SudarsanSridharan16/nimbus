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

import jp.ossc.nimbus.core.*;

/**
 * {@link DatabaseScheduleManagerService}��MBean�C���^�t�F�[�X�B<p>
 * 
 * @author M.Takata
 */
public interface DatabaseScheduleManagerServiceMBean extends ServiceBaseMBean{
    
    /**
     * �X�P�W���[���̐����Ԃ̊Ď��Ɏ��s�����ꍇ�̃��O���b�Z�[�WID�B<p>
     */
    public static final String MSG_ID_CONTROL_STATE_CHECK_ERROR = "DSM__00001";
    
    /**
     * �X�P�W���[���̃^�C���I�[�o�[�Ď��Ɏ��s�����ꍇ�̃��O���b�Z�[�WID�B<p>
     */
    public static final String MSG_ID_TIMEOVER_CHECK_ERROR = "DSM__00002";
    
    /**
     * �X�P�W���[���̃^�C���I�[�o�[�Ď��Ń^�C���I�[�o�[�����X�P�W���[���𔭌������ꍇ�̃��O���b�Z�[�WID�B<p>
     */
    public static final String MSG_ID_TIMEOVER_ERROR = "DSM__00003";
    
    /**
     * �X�P�W���[���e�[�u���̓��t�J�����̓��t�t�H�[�}�b�g�̃f�t�H���g�l�B<p>
     */
    public static final String DEFAULT_DATE_FORMAT = "yyyyMMdd";
    
    /**
     * �X�P�W���[���e�[�u���y�уX�P�W���[���}�X�^�e�[�u���̎����J�����̎����t�H�[�}�b�g�̃f�t�H���g�l�B<p>
     */
    public static final String DEFAULT_TIME_FORMAT = "HHmmssSSS";
    
    /**
     * �X�P�W���[����ʂ�{@link ScheduleMaker}�T�[�r�X�̃}�b�s���O��ݒ肷��B<p>
     *
     * @param mapping �X�P�W���[����ʂ�ScheduleMaker�T�[�r�X�̃}�b�s���O�B�X�P�W���[�����=ScheduleMaker�T�[�r�X��
     */
    public void setScheduleMakerTypeMapping(Properties mapping);
    
    /**
     * �X�P�W���[����ʂ�{@link ScheduleMaker}�T�[�r�X�̃}�b�s���O���擾����B<p>
     *
     * @return �X�P�W���[����ʂ�ScheduleMaker�T�[�r�X�̃}�b�s���O
     */
    public Properties getScheduleMakerTypeMapping();
    
    /**
     * �X�P�W���[����ʂ�{@link ScheduleMaker}�T�[�r�X�̃}�b�s���O�ɐ��K�\�����g�p���邩�ǂ�����ݒ肷��B<p>
     * �f�t�H���g�́Afalse�Ŏg�p���Ȃ��B<br>
     *
     * @param isEnable ���K�\�����g�p����ꍇ�́Atrue
     */
    public void setScheduleMakerTypeRegexEnabled(boolean isEnable);
    
    /**
     * �X�P�W���[����ʂ�{@link ScheduleMaker}�T�[�r�X�̃}�b�s���O�ɐ��K�\�����g�p���邩�ǂ����𔻒肷��B<p>
     *
     * @return true�̏ꍇ�́A���K�\�����g�p����
     */
    public boolean isScheduleMakerTypeRegexEnabled();
    
    /**
     * �f�t�H���g��{@link ScheduleMaker}�T�[�r�X�̃T�[�r�X����ݒ肷��B<p>
     * �w�肵�Ȃ��ꍇ�́A{@link DefaultScheduleMakerService}���K�p�����B<br>
     *
     * @param name ScheduleMaker�T�[�r�X�̃T�[�r�X��
     */
    public void setDefaultScheduleMakerServiceName(ServiceName name);
    
    /**
     * �f�t�H���g��{@link ScheduleMaker}�T�[�r�X�̃T�[�r�X�����擾����B<p>
     *
     * @return ScheduleMaker�T�[�r�X�̃T�[�r�X��
     */
    public ServiceName getDefaultScheduleMakerServiceName();
    
    /**
     * {@link jp.ossc.nimbus.service.connection.ConnectionFactory ConnectionFactory}�T�[�r�X�̃T�[�r�X����ݒ肷��B<p>
     *
     * @param name ConnectionFactory�T�[�r�X�̃T�[�r�X��
     */
    public void setConnectionFactoryServiceName(ServiceName name);
    
    /**
     * {@link jp.ossc.nimbus.service.connection.ConnectionFactory ConnectionFactory}�T�[�r�X�̃T�[�r�X�����擾����B<p>
     *
     * @return ConnectionFactory�T�[�r�X�̃T�[�r�X��
     */
    public ServiceName getConnectionFactoryServiceName();
    
    /**
     * {@link jp.ossc.nimbus.service.system.Time Time}�T�[�r�X�̃T�[�r�X����ݒ肷��B<p>
     *
     * @param name Time�T�[�r�X�̃T�[�r�X��
     */
    public void setTimeServiceName(ServiceName name);
    
    /**
     * {@link jp.ossc.nimbus.service.system.Time Time}�T�[�r�X�̃T�[�r�X�����擾����B<p>
     *
     * @return Time�T�[�r�X�̃T�[�r�X��
     */
    public ServiceName getTimeServiceName();
    
    /**
     * �X�P�W���[���e�[�u���̓��t�J�����̓��t�t�H�[�}�b�g��ݒ肷��B<p>
     * �f�t�H���g�́A{@link #DEFAULT_DATE_FORMAT}�B<br>
     * 
     * @param format ���t�t�H�[�}�b�g
     */
    public void setDateFormat(String format);
    
    /**
     * �X�P�W���[���e�[�u���̓��t�J�����̓��t�t�H�[�}�b�g���擾����B<p>
     * 
     * @return ���t�t�H�[�}�b�g
     */
    public String getDateFormat();
    
    /**
     * �X�P�W���[���e�[�u���y�уX�P�W���[���}�X�^�e�[�u���̎����J�����̎����t�H�[�}�b�g��ݒ肷��B<p>
     * �f�t�H���g�́A{@link #DEFAULT_TIME_FORMAT}�B<br>
     * 
     * @param format �����t�H�[�}�b�g
     */
    public void setTimeFormat(String format);
    
    /**
     * �X�P�W���[���e�[�u���y�уX�P�W���[���}�X�^�e�[�u���̎����J�����̎����t�H�[�}�b�g���擾����B<p>
     * 
     * @return �����t�H�[�}�b�g
     */
    public String getTimeFormat();
    
    /**
     * �X�P�W���[���e�[�u���y�уX�P�W���[���ˑ��֌W�e�[�u���̍X�V���[�UID�J�����̒l��ݒ肷��B<p>
     * �f�t�H���g�́A���[�J���z�X�g���B<br>
     * 
     * @param id �X�V���[�UID
     */
    public void setUpdateUserId(String id);
    
    /**
     * �X�P�W���[���e�[�u���y�уX�P�W���[���ˑ��֌W�e�[�u���̍X�V���[�UID�J�����̒l���擾����B<p>
     * 
     * @return �X�V���[�UID
     */
    public String getUpdateUserId();
    
    /**
     * �X�P�W���[���}�X�^�e�[�u���̃X�L�[�}�����擾����B<p>
     *
     * @return �X�P�W���[���}�X�^�e�[�u���̃X�L�[�}���
     */
    public DatabaseScheduleManagerService.ScheduleMasterTableSchema getScheduleMasterTableSchema();
    
    /**
     * �X�P�W���[���}�X�^�e�[�u���̃X�L�[�}����ݒ肷��B<p>
     *
     * @param schema �X�P�W���[���}�X�^�e�[�u���̃X�L�[�}���
     */
    public void setScheduleMasterTableSchema(DatabaseScheduleManagerService.ScheduleMasterTableSchema schema);
    
    /**
     * �X�P�W���[���O���[�v�}�X�^�e�[�u���̃X�L�[�}�����擾����B<p>
     *
     * @return �X�P�W���[���O���[�v�}�X�^�e�[�u���̃X�L�[�}���
     */
    public DatabaseScheduleManagerService.ScheduleGroupMasterTableSchema getScheduleGroupMasterTableSchema();
    
    /**
     * �X�P�W���[���O���[�v�}�X�^�e�[�u���̃X�L�[�}����ݒ肷��B<p>
     *
     * @param schema �X�P�W���[���O���[�v�}�X�^�e�[�u���̃X�L�[�}���
     */
    public void setScheduleGroupMasterTableSchema(DatabaseScheduleManagerService.ScheduleGroupMasterTableSchema schema);
    
    /**
     * �X�P�W���[���ˑ��֌W�}�X�^�e�[�u���̃X�L�[�}�����擾����B<p>
     *
     * @return �X�P�W���[���ˑ��֌W�}�X�^�e�[�u���̃X�L�[�}���
     */
    public DatabaseScheduleManagerService.ScheduleDependsMasterTableSchema getScheduleDependsMasterTableSchema();
    
    /**
     * �X�P�W���[���ˑ��֌W�}�X�^�e�[�u���̃X�L�[�}����ݒ肷��B<p>
     *
     * @param schema �X�P�W���[���ˑ��֌W�}�X�^�e�[�u���̃X�L�[�}���
     */
    public void setScheduleDependsMasterTableSchema(DatabaseScheduleManagerService.ScheduleDependsMasterTableSchema schema);
    
    /**
     * �X�P�W���[���O���[�v�ˑ��֌W�}�X�^�e�[�u���̃X�L�[�}�����擾����B<p>
     *
     * @return �X�P�W���[���O���[�v�ˑ��֌W�}�X�^�e�[�u���̃X�L�[�}���
     */
    public DatabaseScheduleManagerService.ScheduleGroupDependsMasterTableSchema getScheduleGroupDependsMasterTableSchema();
    
    /**
     * �X�P�W���[���O���[�v�ˑ��֌W�}�X�^�e�[�u���̃X�L�[�}����ݒ肷��B<p>
     *
     * @param schema �X�P�W���[���O���[�v�ˑ��֌W�}�X�^�e�[�u���̃X�L�[�}���
     */
    public void setScheduleGroupDependsMasterTableSchema(DatabaseScheduleManagerService.ScheduleGroupDependsMasterTableSchema schema);
    
    /**
     * �X�P�W���[���e�[�u���̃X�L�[�}�����擾����B<p>
     *
     * @return �X�P�W���[���e�[�u���̃X�L�[�}���
     */
    public DatabaseScheduleManagerService.ScheduleTableSchema getScheduleTableSchema();
    
    /**
     * �X�P�W���[���e�[�u���̃X�L�[�}����ݒ肷��B<p>
     *
     * @param schema �X�P�W���[���e�[�u���̃X�L�[�}���
     */
    public void setScheduleTableSchema(DatabaseScheduleManagerService.ScheduleTableSchema schema);
    
    /**
     * �X�P�W���[���O���[�v�e�[�u���̃X�L�[�}�����擾����B<p>
     *
     * @return �X�P�W���[���O���[�v�e�[�u���̃X�L�[�}���
     */
    public DatabaseScheduleManagerService.ScheduleGroupTableSchema getScheduleGroupTableSchema();
    
    /**
     * �X�P�W���[���O���[�v�e�[�u���̃X�L�[�}����ݒ肷��B<p>
     *
     * @param schema �X�P�W���[���O���[�v�e�[�u���̃X�L�[�}���
     */
    public void setScheduleGroupTableSchema(DatabaseScheduleManagerService.ScheduleGroupTableSchema schema);
    
    /**
     * �X�P�W���[���ˑ��֌W�e�[�u���̃X�L�[�}�����擾����B<p>
     *
     * @return �X�P�W���[���ˑ��֌W�e�[�u���̃X�L�[�}���
     */
    public DatabaseScheduleManagerService.ScheduleDependsTableSchema getScheduleDependsTableSchema();
    
    /**
     * �X�P�W���[���ˑ��֌W�e�[�u���̃X�L�[�}����ݒ肷��B<p>
     *
     * @param schema �X�P�W���[���ˑ��֌W�e�[�u���̃X�L�[�}���
     */
    public void setScheduleDependsTableSchema(DatabaseScheduleManagerService.ScheduleDependsTableSchema schema);
    
    /**
     * �X�P�W���[���O���[�v�ˑ��֌W�e�[�u���̃X�L�[�}�����擾����B<p>
     *
     * @return �X�P�W���[���O���[�v�ˑ��֌W�e�[�u���̃X�L�[�}���
     */
    public DatabaseScheduleManagerService.ScheduleGroupDependsTableSchema getScheduleGroupDependsTableSchema();
    
    /**
     * �X�P�W���[���O���[�v�ˑ��֌W�e�[�u���̃X�L�[�}����ݒ肷��B<p>
     *
     * @param schema �X�P�W���[���O���[�v�ˑ��֌W�e�[�u���̃X�L�[�}���
     */
    public void setScheduleGroupDependsTableSchema(DatabaseScheduleManagerService.ScheduleGroupDependsTableSchema schema);
    
    /**
     * �X�P�W���[��ID�𔭔Ԃ���SQL��ݒ肷��B<p>
     *
     * @param query SQL
     */
    public void setNextScheduleIdSelectQuery(String query);
    
    /**
     * �X�P�W���[��ID�𔭔Ԃ���SQL���擾����B<p>
     *
     * @return SQL
     */
    public String getNextScheduleIdSelectQuery();
    
    /**
     * �����Ԃ��`�F�b�N����Ԋu[ms]��ݒ肷��B<p>
     * �f�t�H���g�́A1�b�B<br>
     *
     * @param interval �Ԋu[ms]
     */
    public void setControlStateCheckInterval(long interval);
    
    /**
     * �����Ԃ��`�F�b�N����Ԋu[ms]�𔻒肷��B<p>
     *
     * @return �Ԋu[ms]
     */
    public long getControlStateCheckInterval();
    
    /**
     * �ő�x�����Ԃ��`�F�b�N����Ԋu[ms]��ݒ肷��B<p>
     * �f�t�H���g�́A1�b�B<br>
     *
     * @param interval �Ԋu[ms]
     */
    public void setTimeoverCheckInterval(long interval);
    
    /**
     * �ő�x�����Ԃ��`�F�b�N����Ԋu[ms]�𔻒肷��B<p>
     *
     * @return �Ԋu[ms]
     */
    public long getTimeoverCheckInterval();
    
    /**
     * �T�[�r�X�̊J�n���ɃV�X�e�����t���g���āA�X�P�W���[�����쐬���邩�ǂ�����ݒ肷��B<p>
     *
     * @param isMake �쐬����ꍇ�Atrue
     */
    public void setMakeScheduleOnStart(boolean isMake);
    
    /**
     * �T�[�r�X�̊J�n���ɃV�X�e�����t���g���āA�X�P�W���[�����쐬���邩�ǂ����𔻒肷��B<p>
     *
     * @return true�̏ꍇ�A�쐬����
     */
    public boolean isMakeScheduleOnStart();
    
    /**
     * {@link #findExecutableSchedules(Date,String[])}�Ăяo�����ɁA�Y���X�P�W���[���̃��R�[�h�����b�N���邩�ǂ�����ݒ肷��B<p>
     * �f�t�H���g�́Afalse�Ń��b�N���Ȃ��B<br>
     *
     * @param isLock ���b�N����ꍇtrue
     */
    public void setLockForFindExecutable(boolean isLock);
    
    /**
     * {@link #findExecutableSchedules(Date,String[])}�Ăяo�����ɁA�Y���X�P�W���[���̃��R�[�h�����b�N���邩�ǂ����𔻒肷��B<p>
     *
     * @return ���b�N����ꍇtrue
     */
    public boolean isLockForFindExecutable();
    
    /**
     * {@link jp.ossc.nimbus.service.keepalive.ClusterService �N���X�^}�T�[�r�X�̃T�[�r�X����ݒ肷��B<p>
     * ���̑�����ݒ肵���ꍇ�A�N���X�^�T�[�r�X��{@link jp.ossc.nimbus.service.keepalive.ClusterService#isMain() ClusterService.isMain()}=true�ƂȂ��Ă���ꍇ�̂݁A�����Ԃ̃`�F�b�N�y�сA�ő�x�����Ԃ̃`�F�b�N���s���B<br>
     *
     * @param name �N���X�^�T�[�r�X�̃T�[�r�X��
     */
    public void setClusterServiceName(ServiceName name);
    
    /**
     * {@link jp.ossc.nimbus.service.keepalive.ClusterService �N���X�^}�T�[�r�X�̃T�[�r�X�����擾����B<p>
     *
     * @return �N���X�^�T�[�r�X�̃T�[�r�X��
     */
    public ServiceName getClusterServiceName();
    
    /**
     * �X�P�W���[��ID�𔭔Ԃ���{@link jp.ossc.nimbus.service.sequence.Sequence Sequence}�T�[�r�X�̃T�[�r�X����ݒ肷��B<p>
     *
     * @param name Sequence�T�[�r�X�̃T�[�r�X��
     */
    public void setSequenceServiceName(ServiceName name);
    
    /**
     * �X�P�W���[��ID�𔭔Ԃ���{@link jp.ossc.nimbus.service.sequence.Sequence Sequence}�T�[�r�X�̃T�[�r�X�����擾����B<p>
     *
     * @return Sequence�T�[�r�X�̃T�[�r�X��
     */
    public ServiceName getSequenceServiceName();
    
    /**
     * �����Ŏg�p����SQL�ŕ�����A����CONCAT�֐����g�����ǂ�����ݒ肷��B<p>
     * �f�t�H���g�́Afalse�ŁA"||"�ŘA������B<br>
     *
     * @param isUse CONCAT�֐����g���ꍇtrue
     */
    public void setUseConcatFunction(boolean isUse);
    
    /**
     * �����Ŏg�p����SQL�ŕ�����A����CONCAT�֐����g�����ǂ����𔻒肷��B<p>
     *
     * @return true�̏ꍇ�ACONCAT�֐����g��
     */
    public boolean isUseConcatFunction();
    
    /**
     * �X�P�W���[���̓��͂�JSON�t�H�[�}�b�g���T�|�[�g���邩�ǂ�����ݒ肷��B<p>
     * �f�t�H���g�́Afalse�ŁA�X�P�W���[���̓��͂͒P�Ȃ镶����Ƃ��Ĉ����B<br>
     *
     * @param isJson �X�P�W���[���̓��͂�JSON�t�H�[�}�b�g���T�|�[�g����ꍇtrue
     */
    public void setJSONInput(boolean isJson);
    
    /**
     * �X�P�W���[���̓��͂�JSON�t�H�[�}�b�g���T�|�[�g���邩�ǂ����𔻒肷��B<p>
     *
     * @return true�̏ꍇ�A�X�P�W���[���̓��͂�JSON�t�H�[�}�b�g���T�|�[�g����
     */
    public boolean isJSONInput();
    
    /**
     * �w�肳�ꂽ���t�̃X�P�W���[�����쐬����B<p>
     *
     * @param date ���t
     * @return �쐬�����X�P�W���[���̃��X�g
     * @throws ScheduleMakeException �X�P�W���[���쐬�Ɏ��s�����ꍇ
     */
    public List makeSchedule(Date date) throws ScheduleMakeException;
    
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
     * �X�P�W���[����o�^����B<p>
     *
     * @param masterId �X�P�W���[���}�X�^ID
     * @param time �X�P�W���[������
     * @param taskName �^�X�N��
     * @param input ���̓f�[�^
     * @param depends �ˑ�����X�P�W���[���}�X�^ID�̔z��
     * @param executorKey ScheduleExecutor����肷��L�[
     * @param executorType ScheduleExecutor�̎��
     * @param retryInterval ���g���C�Ԋu[ms]
     * @param retryEndTime ���g���C�I������
     * @param maxDelayTime �ő�x������[ms]
     * @exception ScheduleManageException �X�P�W���[���̓o�^�Ɏ��s�����ꍇ
     */
    public void addSchedule(
        String masterId,
        Date time,
        String taskName,
        Object input,
        String[] depends,
        String executorKey,
        String executorType,
        long retryInterval,
        Date retryEndTime,
        long maxDelayTime
    ) throws ScheduleManageException;
    
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
     * @param state ������
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
     * �����Ԃ̃`�F�b�N���J�n����B<p>
     */
    public void startControlStateCheck();
    
    /**
     * �����Ԃ̃`�F�b�N���J�n����Ă��邩���肷��B<p>
     *
     * @return �����Ԃ̃`�F�b�N���J�n����Ă���ꍇtrue
     */
    public boolean isStartControlStateCheck();
    
    /**
     * �����Ԃ̃`�F�b�N���~����B<p>
     */
    public void stopControlStateCheck();
    
    /**
     * �ő�x�����Ԃ̃`�F�b�N���J�n����B<p>
     */
    public void startTimeoverCheck();
    
    /**
     * �ő�x�����Ԃ̃`�F�b�N���J�n����Ă��邩���肷��B<p>
     *
     * @return �ő�x�����Ԃ̃`�F�b�N���J�n����Ă���ꍇtrue
     */
    public boolean isStartTimeoverCheck();
    
    /**
     * �ő�x�����Ԃ̃`�F�b�N���~����B<p>
     */
    public void stopTimeoverCheck();
}
