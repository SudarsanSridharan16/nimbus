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

import jp.ossc.nimbus.core.*;

/**
 * {@link DatabaseTimerScheduleFactoryService}�T�[�r�X��MBean�C���^�t�F�[�X�B<p>
 * 
 * @author M.Takata
 */
public interface DatabaseTimerScheduleFactoryServiceMBean extends ServiceBaseMBean{
    
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
     * �X�P�W���[���}�X�^���擾����SQL��ݒ肷��B<p>
     *
     * @param query �X�P�W���[���}�X�^���擾����SQL
     */
    public void setScheduleMasterQuery(String query);
    
    /**
     * �X�P�W���[���}�X�^���擾����SQL���擾����B<p>
     *
     * @return �X�P�W���[���}�X�^���擾����SQL
     */
    public String getScheduleMasterQuery();
    
    /**
     * �X�P�W���[���̃L�[�ƂȂ�f�[�^�̗�C���f�b�N�X��ݒ肷��B<p>
     * �X�P�W���[���̃L�[�Ƃ́A{@link ScheduleFactory#getSchedules(Object)}�̈����ƂȂ�L�[������ł���B<br>
     * �X�P�W���[���̃L�[���K�v�Ȃ��ꍇ�́A�w�肷��K�v�͂Ȃ��B<br>
     *
     * @param index ��C���f�b�N�X
     */
    public void setScheduleKeyQueryIndex(int index);
    
    /**
     * �X�P�W���[���̃L�[�ƂȂ�f�[�^�̗�C���f�b�N�X���擾����B<p>
     *
     * @return ��C���f�b�N�X
     */
    public int getScheduleKeyQueryIndex();
    
    /**
     * �X�P�W���[�����ƂȂ�f�[�^�̗�C���f�b�N�X��ݒ肷��B<p>
     * �ݒ肵�Ȃ��ꍇ�A�X�P�W���[�����́A���̃T�[�r�X�̃T�[�r�X���ɒʔԂ�U�������̂ɂȂ�B<br>
     * �܂��A�ݒ肵���ꍇ�́A���̗�̒l��NULL�ɂȂ鎖�͋��e���Ȃ��B<br>
     *
     * @param index ��C���f�b�N�X
     */
    public void setScheduleNameQueryIndex(int index);
    
    /**
     * �X�P�W���[�����ƂȂ�f�[�^�̗�C���f�b�N�X���擾����B<p>
     *
     * @return ��C���f�b�N�X
     */
    public int getScheduleNameQueryIndex();
    
    /**
     * {@link ScheduleTask}�T�[�r�X�̃T�[�r�X���ƂȂ�f�[�^�̗�C���f�b�N�X��ݒ肷��B<p>
     * ���s����^�X�N�́A�C�ӂ�ScheduleTask�T�[�r�X�ABeanFlow�Ăяo���^�X�N�AIOC�Ăяo���^�X�N��3��ނ��T�|�[�g���Ă���A���̑����́A�C�ӂ�ScheduleTask�T�[�r�X���g�p���邽�߂̂��̂ł���B<br>
     *
     * @param index ��C���f�b�N�X
     */
    public void setScheduleTaskServiceNameQueryIndex(int index);
    
    /**
     * {@link ScheduleTask}�T�[�r�X�̃T�[�r�X���ƂȂ�f�[�^�̗�C���f�b�N�X���擾����B<p>
     *
     * @return ��C���f�b�N�X
     */
    public int getScheduleTaskServiceNameQueryIndex();
    
    /**
     * {@link jp.ossc.nimbus.service.beancontrol.interfaces.BeanFlowInvokerFactory BeanFlowInvokerFactory}�T�[�r�X�̃T�[�r�X����ݒ肷��B<p>
     * ���s����^�X�N�́A�C�ӂ�ScheduleTask�T�[�r�X�ABeanFlow�Ăяo���^�X�N�AIOC�Ăяo���^�X�N��3��ނ��T�|�[�g���Ă���A���̑����́ABeanFlow�Ăяo���^�X�N���g�p���邽�߂̂��̂ł���B<br>
     *
     * @param name BeanFlowInvokerFactory�T�[�r�X�̃T�[�r�X��
     */
    public void setScheduleBeanFlowInvokerFactoryServiceName(ServiceName name);
    
    /**
     * {@link jp.ossc.nimbus.service.beancontrol.interfaces.BeanFlowInvokerFactory BeanFlowInvokerFactory}�T�[�r�X�̃T�[�r�X����ݒ肷��B<p>
     *
     * @return BeanFlowInvokerFactory�T�[�r�X�̃T�[�r�X��
     */
    public ServiceName getScheduleBeanFlowInvokerFactoryServiceName();
    
    /**
     * {@link jp.ossc.nimbus.service.beancontrol.interfaces.BeanFlowInvokerFactory BeanFlowInvokerFactory}�T�[�r�X�̃T�[�r�X���ƂȂ�f�[�^�̗�C���f�b�N�X��ݒ肷��B<p>
     * ���s����^�X�N�́A�C�ӂ�ScheduleTask�T�[�r�X�ABeanFlow�Ăяo���^�X�N�AIOC�Ăяo���^�X�N��3��ނ��T�|�[�g���Ă���A���̑����́ABeanFlow�Ăяo���^�X�N���g�p���邽�߂̂��̂ł���B<br>
     *
     * @param index ��C���f�b�N�X
     */
    public void setScheduleBeanFlowInvokerFactoryServiceNameQueryIndex(int index);
    
    /**
     * {@link jp.ossc.nimbus.service.beancontrol.interfaces.BeanFlowInvokerFactory BeanFlowInvokerFactory}�T�[�r�X�̃T�[�r�X���ƂȂ�f�[�^�̗�C���f�b�N�X��ݒ肷��B<p>
     *
     * @return ��C���f�b�N�X
     */
    public int getScheduleBeanFlowInvokerFactoryServiceNameQueryIndex();
    
    /**
     * BeanFlow�Ăяo���^�X�N�ŌĂяo��BeanFlow���ƂȂ�f�[�^�̗�C���f�b�N�X��ݒ肷��B<p>
     * BeanFlow�Ăяo���^�X�N���g�p����ꍇ�́A�K���w�肵�Ȃ���΂Ȃ�Ȃ��B<br>
     *
     * @param index ��C���f�b�N�X
     */
    public void setScheduleBeanFlowNameQueryIndex(int index);
    
    /**
     * BeanFlow�Ăяo���^�X�N�ŌĂяo��BeanFlow���ƂȂ�f�[�^�̗�C���f�b�N�X���擾����B<p>
     *
     * @return ��C���f�b�N�X
     */
    public int getScheduleBeanFlowNameQueryIndex();
    
    /**
     * {@link jp.ossc.nimbus.service.ioccall.FacadeCaller FacadeCaller}�T�[�r�X�̃T�[�r�X����ݒ肷��B<p>
     * ���s����^�X�N�́A�C�ӂ�ScheduleTask�T�[�r�X�ABeanFlow�Ăяo���^�X�N�AIOC�Ăяo���^�X�N��3��ނ��T�|�[�g���Ă���A���̑����́AIOC�Ăяo���^�X�N���g�p���邽�߂̂��̂ł���B<br>
     *
     * @param name FacadeCaller�T�[�r�X�̃T�[�r�X��
     */
    public void setScheduleFacadeCallerServiceName(ServiceName name);
    
    /**
     * {@link jp.ossc.nimbus.service.ioccall.FacadeCaller FacadeCaller}�T�[�r�X�̃T�[�r�X�����擾����B<p>
     *
     * @return FacadeCaller�T�[�r�X�̃T�[�r�X��
     */
    public ServiceName getScheduleFacadeCallerServiceName();
    
    /**
     * IOC�Ăяo���^�X�N�Ŏg�p����{@link jp.ossc.nimbus.service.ioccall.FacadeCaller FacadeCaller}�T�[�r�X�̃T�[�r�X���ƂȂ�f�[�^�̗�C���f�b�N�X��ݒ肷��B<p>
     *
     * @param index ��C���f�b�N�X
     */
    public void setScheduleFacadeCallerServiceNameQueryIndex(int index);
    
    /**
     * IOC�Ăяo���^�X�N�Ŏg�p����{@link jp.ossc.nimbus.service.ioccall.FacadeCaller FacadeCaller}�T�[�r�X�̃T�[�r�X���ƂȂ�f�[�^�̗�C���f�b�N�X���擾����B<p>
     *
     * @return ��C���f�b�N�X
     */
    public int getScheduleFacadeCallerServiceNameQueryIndex();
    
    /**
     * IOC�Ăяo���^�X�N�ŌĂяo��BeanFlow���ƂȂ�f�[�^�̗�C���f�b�N�X��ݒ肷��B<p>
     * IOC�Ăяo���^�X�N���g�p����ꍇ�́A�K���w�肵�Ȃ���΂Ȃ�Ȃ��B<br>
     * �܂��A������BeanFlow���Ăяo���ꍇ�́A�J���}��؂��BeanFlow�����w�肷��B<br>
     *
     * @param index ��C���f�b�N�X
     */
    public void setScheduleBeanFlowNamesQueryIndex(int index);
    
    /**
     * IOC�Ăяo���^�X�N�ŌĂяo��BeanFlow���ƂȂ�f�[�^�̗�C���f�b�N�X���擾����B<p>
     *
     * @return ��C���f�b�N�X
     */
    public int getScheduleBeanFlowNamesQueryIndex();
    
    public void setScheduleIOCCallTypeQueryIndex(int index);
    public int getScheduleIOCCallTypeQueryIndex();
    
    public void setScheduleIOCCallType(String type);
    public String getScheduleIOCCallType();
    
    public void setScheduleStartTimeQueryIndex(int index);
    public int getScheduleStartTimeQueryIndex();
    
    public void setScheduleStartTimeFormat(String format);
    public String getScheduleStartTimeFormat();
    
    public void setScheduleExecuteWhenOverStartTimeQueryIndex(int index);
    public int getScheduleExecuteWhenOverStartTimeQueryIndex();
    
    public void setScheduleEndTimeQueryIndex(int index);
    public int getScheduleEndTimeQueryIndex();
    
    public void setScheduleEndTimeFormat(String format);
    public String getScheduleEndTimeFormat();
    
    public void setScheduleDelayQueryIndex(int index);
    public int getScheduleDelayQueryIndex();
    
    public void setSchedulePeriodQueryIndex(int index);
    public int getSchedulePeriodQueryIndex();
    
    public void setScheduleCountQueryIndex(int index);
    public int getScheduleCountQueryIndex();
    
    public void setScheduleFixedRateQueryIndex(int index);
    public int getScheduleFixedRateQueryIndex();
    
    public void setScheduleDependsScheduleNamesQueryIndex(int index);
    public int getScheduleDependsScheduleNamesQueryIndex();
    
    public void setScheduleDependencyTimeoutQueryIndex(int index);
    public int getScheduleDependencyTimeoutQueryIndex();
    
    public void setScheduleDependencyConfirmIntervalQueryIndex(int index);
    public int getScheduleDependencyConfirmIntervalQueryIndex();
    
    public void setScheduleErrorLogMessageIdQueryIndex(int index);
    public int getScheduleErrorLogMessageIdQueryIndex();
    
    public void setScheduleErrorLogMessageId(String id);
    public String getScheduleErrorLogMessageId();
    
    public void setScheduleTimeoutLogMessageIdQueryIndex(int index);
    public int getScheduleTimeoutLogMessageIdQueryIndex();
    
    public void setScheduleTimeoutLogMessageId(String id);
    public String getScheduleTimeoutLogMessageId();
    
    public void setScheduleJournalServiceName(ServiceName name);
    public ServiceName getScheduleJournalServiceName();
    
    public void setScheduleJournalServiceNameQueryIndex(int index);
    public int getScheduleJournalServiceNameQueryIndex();
    
    public void setScheduleQueueServiceName(ServiceName name);
    public ServiceName getScheduleQueueServiceName();
    
    public void setScheduleQueueServiceNameQueryIndex(int index);
    public int getScheduleQueueServiceNameQueryIndex();
    
    public void setScheduleGarbageQueueQueryIndex(int index);
    public int getScheduleGarbageQueueQueryIndex();
    
    public void setScheduleStateManagerServiceName(ServiceName name);
    public ServiceName getScheduleStateManagerServiceName();
    
    public void setScheduleStateManagerServiceNameQueryIndex(int index);
    public int getScheduleStateManagerServiceNameQueryIndex();
}