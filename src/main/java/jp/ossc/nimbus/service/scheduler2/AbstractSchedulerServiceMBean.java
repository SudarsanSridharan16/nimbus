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

import jp.ossc.nimbus.core.*;

/**
 * {@link AbstractSchedulerService}��MBean�C���^�t�F�[�X�B<p>
 * 
 * @author M.Takata
 */
public interface AbstractSchedulerServiceMBean extends ServiceBaseMBean{
    
    /**
     * �X�P�W���[���̏�ԕύX�Ɏ��s�����ꍇ�̃��O���b�Z�[�WID�B<p>
     */
    public static final String MSG_ID_STATE_CHANGE_ERROR = "AS___00001";
    
    /**
     * �X�P�W���[���̎��s�Ɏ��s�����ꍇ�̃��O���b�Z�[�WID�B<p>
     */
    public static final String MSG_ID_EXECUTE_ERROR = "AS___00002";
    
    /**
     * �X�P�W���[���𓊓������ꍇ�̃��O���b�Z�[�WID�B<p>
     */
    public static final String MSG_ID_ENTRY = "AS___00003";
    
    /**
     * �X�P�W���[���̎��o���Ɏ��s�����ꍇ�̃��O���b�Z�[�WID�B<p>
     */
    public static final String MSG_ID_SCHEDULE_GET_ERROR = "AS___00004";
    
    /**
     * �\�����Ȃ��G���[�����������ꍇ�̃��O���b�Z�[�WID�B<p>
     */
    public static final String MSG_ID_UNEXPEXTED_ERROR = "AS___00005";
    
    /**
     * �X�P�W���[���̏�ԑJ�ڂɎ��s�����ꍇ�̃��O���b�Z�[�WID�B<p>
     */
    public static final String MSG_ID_STATE_TRANS_ERROR = "AS___00006";
    
    /**
     * �X�P�W���[���̓����Ɏ��s�����ꍇ�̃��O���b�Z�[�WID�B<p>
     */
    public static final String MSG_ID_ENTRY_ERROR = "AS___00007";
    
    /**
     * �Y������X�P�W���[�����s�����݂��Ȃ��ꍇ�̃��O���b�Z�[�WID�B<p>
     */
    public static final String MSG_ID_NOT_FOUND_EXECUTOR_ERROR = "AS___00008";
    
    /**
     * ���s���ׂ��X�P�W���[����{@link ScheduleManager}�Ɋm�F���ɂ����Ԋu[ms]��ݒ肷��B<p>
     * �f�t�H���g�́A1000[ms]�B<br>
     *
     * @param interval �Ԋu[ms]
     */
    public void setScheduleTickerInterval(long interval);
    
    /**
     * ���s���ׂ��X�P�W���[����{@link ScheduleManager}�Ɋm�F���ɂ����Ԋu[ms]���擾����B<p>
     *
     * @return �Ԋu[ms]
     */
    public long getScheduleTickerInterval();
    
    /**
     * {@link ScheduleManager}�T�[�r�X�̃T�[�r�X����ݒ肷��B<p>
     *
     * @param name ScheduleManager�T�[�r�X�̃T�[�r�X��
     */
    public void setScheduleManagerServiceName(ServiceName name);
    
    /**
     * {@link ScheduleManager}�T�[�r�X�̃T�[�r�X�����擾����B<p>
     *
     * @return ScheduleManager�T�[�r�X�̃T�[�r�X��
     */
    public ServiceName getScheduleManagerServiceName();
    
    /**
     * {@link ScheduleExecutor}�T�[�r�X�̃T�[�r�X����ݒ肷��B<p>
     *
     * @param name ScheduleExecutor�T�[�r�X�̃T�[�r�X��
     */
    public void setScheduleExecutorServiceName(ServiceName name);
    
    /**
     * {@link ScheduleExecutor}�T�[�r�X�̃T�[�r�X�����擾����B<p>
     *
     * @return ScheduleExecutor�T�[�r�X�̃T�[�r�X��
     */
    public ServiceName getScheduleExecutorServiceName();
    
    /**
     * {@link ScheduleExecutor}�T�[�r�X�̃T�[�r�X���z���ݒ肷��B<p>
     *
     * @param names ScheduleExecutor�T�[�r�X�̃T�[�r�X���z��
     */
    public void setScheduleExecutorServiceNames(ServiceName[] names);
    
    /**
     * {@link ScheduleExecutor}�T�[�r�X�̃T�[�r�X���z����擾����B<p>
     *
     * @return ScheduleExecutor�T�[�r�X�̃T�[�r�X���z��
     */
    public ServiceName[] getScheduleExecutorServiceNames();
    
    /**
     * {@link jp.ossc.nimbus.service.context.Context Context}�T�[�r�X�̃T�[�r�X����ݒ肷��B<p>
     * {@link jp.ossc.nimbus.service.sequence.Sequence Sequence}�T�[�r�X�Ŕ��Ԃ����ʔԂ��悹��B<br>
     *
     * @param name Context�T�[�r�X�̃T�[�r�X��
     */
    public void setThreadContextServiceName(ServiceName name);
    
    /**
     * {@link jp.ossc.nimbus.service.context.Context Context}�T�[�r�X�̃T�[�r�X�����擾����B<p>
     *
     * @return Context�T�[�r�X�̃T�[�r�X��
     */
    public ServiceName getThreadContextServiceName();
    
    /**
     * {@link jp.ossc.nimbus.service.sequence.Sequence Sequence}�T�[�r�X�̃T�[�r�X����ݒ肷��B<p>
     *
     * @param name Sequence�T�[�r�X�̃T�[�r�X��
     */
    public void setSequenceServiceName(ServiceName name);
    
    /**
     * {@link jp.ossc.nimbus.service.sequence.Sequence Sequence}�T�[�r�X�̃T�[�r�X�����擾����B<p>
     *
     * @return Sequence�T�[�r�X�̃T�[�r�X��
     */
    public ServiceName getSequenceServiceName();
    
    /**
     * �X�P�W���[���̓��������Ńg�����U�N�V����������s�����ǂ�����ݒ肷��B<p>
     * �f�t�H���g�́Afalse�ŁA�g�����U�N�V�������䂵�Ȃ��B<br>
     *
     * @param isControl �g�����U�N�V����������s���ꍇtrue
     */
    public void setTransactionControl(boolean isControl);
    
    /**
     * �X�P�W���[���̓��������Ńg�����U�N�V����������s�����ǂ����𔻒肷��B<p>
     *
     * @return true�̏ꍇ�A�g�����U�N�V����������s��
     */
    public boolean isTransactionControl();
    
    /**
     * ���̃X�P�W���[�����s����肷��L�[���擾����B<p>
     * �X�P�W���[�����ɃX�P�W���[��������U�肽�����Ɏg�p����B<br>
     * ���̃L�[���w�肷��ƁA{@link ScheduleManager}����X�P�W���[�����擾����ۂɁA{@link ScheduleManager#findExecutableSchedules(Date, String[], String)}�̑�O�����Ƃ��ēn���B<br>
     * �w�肵�Ȃ��ꍇ�́A{@link ScheduleManager#findExecutableSchedules(Date, String[])}���g�p����B<br>
     *
     * @param key ���s�L�[
     */
    public void setExecutorKey(String key);
    
    /**
     * ���̃X�P�W���[�����s����肷��L�[���擾����B<p>
     *
     * @return ���s�L�[
     */
    public String getExecutorKey();
    
    /**
     * {@link jp.ossc.nimbus.service.transaction.TransactionManagerFactory TransactionManagerFactory}�T�[�r�X�̃T�[�r�X����ݒ肷��B<p>
     *
     * @param name TransactionManagerFactory�T�[�r�X�̃T�[�r�X��
     */
    public void setTransactionManagerFactoryServiceName(ServiceName name);
    
    /**
     * {@link jp.ossc.nimbus.service.transaction.TransactionManagerFactory TransactionManagerFactory}�T�[�r�X�̃T�[�r�X�����擾����B<p>
     *
     * @return TransactionManagerFactory�T�[�r�X�̃T�[�r�X��
     */
    public ServiceName getTransactionManagerFactoryServiceName();
    
    /**
     * {@link jp.ossc.nimbus.service.keepalive.ClusterService �N���X�^}�T�[�r�X�̃T�[�r�X����ݒ肷��B<p>
     * ���̑�����ݒ肵���ꍇ�A�N���X�^�T�[�r�X��{@link jp.ossc.nimbus.service.keepalive.ClusterService#isMain() ClusterService.isMain()}=true�ƂȂ��Ă���ꍇ�̂݁A�X�P�W���[���̓������s���B<br>
     * �N���X�^�T�[�r�X�̃N���X�^�ւ̎Q���́A���̃T�[�r�X�̏�ԂƘA������K�v�����邽�߁A{@link jp.ossc.nimbus.service.keepalive.ClusterService#setJoinOnStart(boolean) ClusterService.setJoinOnStart(false)}�ɂ��Ă����K�v������B<br>
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
     * �X�P�W���[���̓������J�n����B<p>
     */
    public void startEntry();
    
    /**
     * �X�P�W���[���̓������~����B<p>
     */
    public void stopEntry();
    
    /**
     * �X�P�W���[���̓������J�n����Ă��邩���肷��B<p>
     *
     * @return �X�P�W���[���̓������J�n����Ă���ꍇtrue
     */
    public boolean isStartEntry();
}