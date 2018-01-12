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

import java.util.Date;

import jp.ossc.nimbus.core.*;

/**
 * {@link TimerScheduleService}��MBean�C���^�t�F�[�X�B<p>
 * 
 * @author M.Takata
 */
public interface TimerScheduleServiceMBean extends ServiceBaseMBean{
    
    /**
     * �X�P�W���[�����ꂽ�^�X�N�̎��s���ɃG���[�����������ꍇ�ɏo�͂���G���[���O�̃��b�Z�[�WID�̃f�t�H���g�B<p>
     */
    public static final String DEFAULT_ERROR_LOG_MESSAGE_ID = "TS___00001";
    
    /**
     * �X�P�W���[�����ꂽ�^�X�N���ˑ�����X�P�W���[���̏I���҂��Ń^�C���A�E�g�����ꍇ�ɏo�͂���G���[���O�̃��b�Z�[�WID�̃f�t�H���g�B<p>
     */
    public static final String DEFAULT_TIMEOUT_LOG_MESSAGE_ID = "TS___00002";
    
    /**
     * �W���[�i���̃��[�g�v�f���B<p>
     */
    public static final String JOURNAL_KEY_ROOT = "Schedule";
    
    /**
     * �W���[�i���̏o�͗v�f�� �X�P�W���[�����B<p>
     */
    public static final String JOURNAL_KEY_NAME = "Name";
    
    /**
     * �W���[�i���̏o�͗v�f�� ���ʃX�e�[�^�X�B<p>
     */
    public static final String JOURNAL_KEY_STATUS = "ResultStatus";
    
    /**
     * �W���[�i���̏o�͒l ���ʃX�e�[�^�X �����B<p>
     */
    public static final String JOURNAL_VAL_STATUS_SUCCESS = "SUCCESS";
    
    /**
     * �W���[�i���̏o�͒l ���ʃX�e�[�^�X �G���[�B<p>
     */
    public static final String JOURNAL_VAL_STATUS_ERROR = "ERROR";
    
    /**
     * �W���[�i���̏o�͒l ���ʃX�e�[�^�X �^�C���A�E�g�B<p>
     */
    public static final String JOURNAL_VAL_STATUS_TIMEOUT = "TIMEOUT";
    
    /**
     * �W���[�i���̏o�͗v�f�� ��O�B<p>
     */
    public static final String JOURNAL_KEY_EXCEPTION = "Exception";
    
    /**
     * �X�P�W���[������ݒ肷��B<p>
     * �f�t�H���g�́A�T�[�r�X���B<br>
     *
     * @param name �X�P�W���[����
     */
    public void setName(String name);
    
    /**
     * �X�P�W���[�������擾����B<p>
     *
     * @return �X�P�W���[����
     */
    public String getName();
    
    /**
     * �^�X�N�T�[�r�X����ݒ肷��B<p>
     *
     * @param name �^�X�N�T�[�r�X��
     */
    public void setTaskServiceName(ServiceName name);
    
    /**
     * �^�X�N�T�[�r�X�����擾����B<p>
     *
     * @return �^�X�N�T�[�r�X��
     */
    public ServiceName getTaskServiceName();
    
    /**
     * ���s�J�n������ݒ肷��B<p>
     * 
     * @param time ���s�J�n����
     */
    public void setStartTime(Date time);
    
    /**
     * ���s�J�n�������擾����B<p>
     * 
     * @return ���s�J�n����
     */
    public Date getStartTime();
    
    /**
     * �X�P�W���[�����J�n���鎞�ɁA���Ɏ��s�J�n�������߂��Ă����ꍇ�ɁA�^�X�N�����s���邩�ǂ�����ݒ肷��B<p>
     * �f�t�H���g�́Atrue�B<br>
     *
     * @param isExecute ���s����ꍇtrue
     */
    public void setExecuteWhenOverStartTime(boolean isExecute);
    
    /**
     * �X�P�W���[�����J�n���鎞�ɁA���Ɏ��s�J�n�������߂��Ă����ꍇ�ɁA�^�X�N�����s���邩�ǂ����𔻒肷��B<p>
     *
     * @return true�̏ꍇ�́A���s����
     */
    public boolean isExecuteWhenOverStartTime();
    
    /**
     * ���s�I��������ݒ肷��B<p>
     * 
     * @param time ���s�I������
     */
    public void setEndTime(Date time);
    
    /**
     * ���s�I���������擾����B<p>
     * 
     * @return ���s�I������
     */
    public Date getEndTime();
    
    /**
     * �x������[ms]��ݒ肷��B<p>
     * 
     * @param delay �x������[ms]
     */
    public void setDelay(long delay);
    
    /**
     * �x������[ms]���擾����B<p>
     * 
     * @return �x������[ms]
     */
    public long getDelay();
    
    /**
     * �J��Ԃ��Ԋu[ms]��ݒ肷��B<p>
     * 
     * @param period �J��Ԃ��Ԋu[ms]
     */
    public void setPeriod(long period);
    
    /**
     * �J��Ԃ��Ԋu[ms]���擾����B<p>
     * 
     * @return �J��Ԃ��Ԋu[ms]
     */
    public long getPeriod();
    
    /**
     * �J��Ԃ��񐔂�ݒ肷��B<p>
     * 
     * @param count �J��Ԃ���
     */
    public void setCount(int count);
    
    /**
     * �J��Ԃ��񐔂��擾����B<p>
     * 
     * @return �J��Ԃ���
     */
    public int getCount();
    
    /**
     * �Œ�p�x���s���s�����ǂ�����ݒ肷��B<p>
     * �f�t�H���g��false�B<br>
     * 
     * @param isFixedRate �Œ�p�x���s���s���ꍇtrue
     */
    public void setFixedRate(boolean isFixedRate);
    
    /**
     * �Œ�p�x���s���s�����ǂ����𔻒肷��B<p>
     * 
     * @return true�̏ꍇ�A�Œ�p�x���s���s��
     */
    public boolean isFixedRate();
    
    /**
     * �ˑ�����X�P�W���[������ݒ肷��B<p>
     * �A���A�J��Ԃ��������s���X�P�W���[���ɂ͈ˑ��ł��Ȃ��B�J��Ԃ��������s���X�P�W���[���ւ̈ˑ��͖��������B<br>
     *
     * @param names �ˑ�����X�P�W���[�����z��
     */
    public void setDependsScheduleNames(String[] names);
    
    /**
     * �ˑ�����X�P�W���[�������擾����B<p>
     *
     * @return �ˑ�����X�P�W���[�����z��
     */
    public String[] getDependsScheduleNames();
    
    /**
     * �ˑ�����X�P�W���[���̏I����҂��̃^�C���A�E�g��ݒ肷��B<p>
     * �^�C���A�E�g�����ꍇ�́A�X�P�W���[�����L�����Z������B<br>
     * �f�t�H���g�ł́A�^�C���A�E�g���Ȃ��B<br>
     *
     * @param timeout �^�C���A�E�g[ms]
     */
    public void setDependencyTimeout(long timeout);
    
    /**
     * �ˑ�����X�P�W���[���̏I����҂��̃^�C���A�E�g���擾����B<p>
     *
     * @return �^�C���A�E�g[ms]
     */
    public long getDependencyTimeout();
    
    /**
     * �ˑ�����X�P�W���[���̏I�����m�F����Ԋu��ݒ肷��B<p>
     * �f�t�H���g�ł́A1�b�B<br>
     *
     * @param interval �ˑ�����X�P�W���[���̏I�����m�F����Ԋu[ms]
     */
    public void setDependencyConfirmInterval(long interval);
    
    /**
     * �ˑ�����X�P�W���[���̏I�����m�F����Ԋu���擾����B<p>
     *
     * @return �ˑ�����X�P�W���[���̏I�����m�F����Ԋu[ms]
     */
    public long getDependencyConfirmInterval();
    
    /**
     * �X�P�W���[�����ꂽ�^�X�N�̎��s���ɃG���[�����������ꍇ�ɏo�͂���G���[���O�̃��b�Z�[�WID��ݒ肷��B<p>
     * �f�t�H���g�́A{@link #DEFAULT_ERROR_LOG_MESSAGE_ID}�B<br>
     *
     * @param id �G���[���O�̃��b�Z�[�WID
     */
    public void setErrorLogMessageId(String id);
    
    /**
     * �X�P�W���[�����ꂽ�^�X�N�̎��s���ɃG���[�����������ꍇ�ɏo�͂���G���[���O�̃��b�Z�[�WID���擾����B<p>
     *
     * @return �G���[���O�̃��b�Z�[�WID
     */
    public String getErrorLogMessageId();
    
    /**
     * �X�P�W���[�����ꂽ�^�X�N���ˑ�����X�P�W���[���̏I���҂��Ń^�C���A�E�g�����ꍇ�ɏo�͂���G���[���O�̃��b�Z�[�WID��ݒ肷��B<p>
     * �f�t�H���g�́A{@link #DEFAULT_TIMEOUT_LOG_MESSAGE_ID}�B<br>
     *
     * @param id �G���[���O�̃��b�Z�[�WID
     */
    public void setTimeoutLogMessageId(String id);
    
    /**
     * �X�P�W���[�����ꂽ�^�X�N���ˑ�����X�P�W���[���̏I���҂��Ń^�C���A�E�g�����ꍇ�ɏo�͂���G���[���O�̃��b�Z�[�WID���擾����B<p>
     *
     * @return �G���[���O�̃��b�Z�[�WID
     */
    public String getTimeoutLogMessageId();
    
    /**
     * �W���[�i���T�[�r�X�̃T�[�r�X����ݒ肷��B<p>
     *
     * @param name �W���[�i���T�[�r�X�̃T�[�r�X��
     */
    public void setJournalServiceName(ServiceName name);
    
    /**
     * �W���[�i���T�[�r�X�̃T�[�r�X�����擾����B<p>
     *
     * @return �W���[�i���T�[�r�X�̃T�[�r�X��
     */
    public ServiceName getJournalServiceName();
    
    /**
     * �L���[�T�[�r�X�̃T�[�r�X����ݒ肷��B<p>
     * �X�P�W���[����񓯊��Ŏ��s�������ꍇ�ɐݒ肷��B<br>
     *
     * @param name �L���[�T�[�r�X�̃T�[�r�X��
     */
    public void setQueueServiceName(ServiceName name);
    
    /**
     * �L���[�T�[�r�X�̃T�[�r�X�����擾����B<p>
     *
     * @return �L���[�T�[�r�X�̃T�[�r�X��
     */
    public ServiceName getQueueServiceName();
    
    /**
     * �X�P�W���[���̏I�����ɃL���[�ɗ��܂����^�X�N���������邩�ǂ�����ݒ肷��B<p>
     * �f�t�H���g�́Afalse�B<br>
     *
     * @param isGarbage �L���[�ɗ��܂����^�X�N����������ꍇtrue
     */
    public void setGarbageQueue(boolean isGarbage);
    
    /**
     * �X�P�W���[���̏I�����ɃL���[�ɗ��܂����^�X�N���������邩�ǂ����𔻒肷��B<p>
     *
     * @return true�̏ꍇ�A�L���[�ɗ��܂����^�X�N����������
     */
    public boolean isGarbageQueue();
    
    /**
     * �X�P�W���[���̏�Ԃ��Ǘ�����{@link ScheduleStateManager}�T�[�r�X�̃T�[�r�X����ݒ肷��B<p>
     *
     * @param name ScheduleStateManager�T�[�r�X�̃T�[�r�X��
     */
    public void setScheduleStateManagerServiceName(ServiceName name);
    
    /**
     * �X�P�W���[���̏�Ԃ��Ǘ�����{@link ScheduleStateManager}�T�[�r�X�̃T�[�r�X�����擾����B<p>
     *
     * @return ScheduleStateManager�T�[�r�X�̃T�[�r�X��
     */
    public ServiceName getScheduleStateManagerServiceName();
    
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
     * �ˑ�����X�P�W���[���̏I����ҋ@���Ă��邩�ǂ����𔻒肷��B<p>
     *
     * @return �ˑ�����X�P�W���[���̏I����ҋ@���Ă���ꍇ��true
     */
    public boolean isWaiting();
    
    /**
     * �X�P�W���[�����G���[�I���������ǂ����𔻒肷��B<p>
     * �����I�Ɏ��s�����X�P�W���[���̏ꍇ�́A���O�̎��s���ʂ������B<br>
     *
     * @return �X�P�W���[�����G���[�I�������ꍇ��true
     */
    public boolean isError();
    
    /**
     * �ˑ�����X�P�W���[���̏I���҂��Ń^�C���A�E�g�������ǂ����𔻒肷��B<p>
     *
     * @return �ˑ�����X�P�W���[���̏I���҂��Ń^�C���A�E�g�����ꍇ��true
     */
    public boolean isTimeout();
    
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