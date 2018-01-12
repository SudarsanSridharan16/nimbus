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
package jp.ossc.nimbus.service.publish.websocket;

import jp.ossc.nimbus.core.ServiceBaseMBean;
import jp.ossc.nimbus.core.ServiceName;
import jp.ossc.nimbus.service.websocket.ExceptionHandlerMappingService;

/**
 * {@link AbstractPublishMessageDispatcherService}��MBean�C���^�t�F�[�X
 * <p>
 *
 * @author M.Ishida
 */
public interface AbstractPublishMessageDispatcherServiceMBean extends ServiceBaseMBean {

    /**
     * �f�[�^���M���ɃG���[�����������ۂɏo�͂��郁�b�Z�[�WID�̃f�t�H���g�l�B
     * <p>
     */
    public static final String DEFAULT_SEND_ERROR_MESSAGE_ID = "WS___00006";

    /**
     * ���b�Z�[�W�𑗐M���邽�߂�QueueHandlerContainer�ւ̃p�����[�^�I�u�W�F�N�g���ė��p���邽�߂̃��X�g�T�C�Y�̃f�t�H���g�l�B
     *
     */
    public static final int DEFAULT_MESSAGE_SEND_PARAMETER_RECYCLE_LIST_SIZE = -1;

    /**
     * ���b�Z�[�W�z�M����M���邽�߂�QueueHandlerContainer�̃T�[�r�X�����擾����B
     *
     * @return �T�[�r�X��
     */
    public ServiceName getMessageListenerQueueHandlerContainerServiceName();

    /**
     * ���b�Z�[�W�z�M����M���邽�߂�QueueHandlerContainer�̃T�[�r�X����ݒ肷��B
     *
     * @param name �T�[�r�X��
     */
    public void setMessageListenerQueueHandlerContainerServiceName(ServiceName name);

    /**
     * ���b�Z�[�W�z�M����M���邽�߂�QueueHandlerContainer�ɐݒ肷��DistributedQueueSelector�̃T�[�r�X�����擾����
     * �B
     *
     * @return �T�[�r�X��
     */
    public ServiceName getMessageListenerQueueSelectorServiceName();

    /**
     * ���b�Z�[�W�z�M����M���邽�߂�QueueHandlerContainer�ɐݒ肷��DistributedQueueSelector�̃T�[�r�X����ݒ肷��
     * �B
     *
     * @param name �T�[�r�X��
     */
    public void setMessageListenerQueueSelectorServiceName(ServiceName name);

    /**
     * ���b�Z�[�W�𑗐M���邽�߂�QueueHandlerContainer�̃T�[�r�X�����擾����B
     *
     * @return �T�[�r�X��
     */
    public ServiceName getMessageSendQueueHandlerContainerServiceName();

    /**
     * ���b�Z�[�W�𑗐M���邽�߂�QueueHandlerContainer�̃T�[�r�X����ݒ肷��B
     *
     * @param name �T�[�r�X��
     */
    public void setMessageSendQueueHandlerContainerServiceName(ServiceName name);

    /**
     * ���b�Z�[�W�𑗐M���邽�߂�QueueHandlerContainer�ɐݒ肷��DistributedQueueSelector�̃T�[�r�X�����擾����
     * �B
     *
     * @return �T�[�r�X��
     */
    public ServiceName getMessageSendQueueSelectorServiceName();

    /**
     * ���b�Z�[�W�𑗐M���邽�߂�QueueHandlerContainer�ɐݒ肷��DistributedQueueSelector�̃T�[�r�X����ݒ肷��
     * �B
     *
     * @param name �T�[�r�X��
     */
    public void setMessageSendQueueSelectorServiceName(ServiceName name);

    /**
     * ��O�n���h���}�b�s���O�T�[�r�X{@link ExceptionHandlerMappingService}�̃T�[�r�X�����擾����B
     *
     * @return �T�[�r�X��
     */
    public ServiceName getMessageSendExceptionHandlerMappingServiceName();

    /**
     * ��O�n���h���}�b�s���O�T�[�r�X{@link ExceptionHandlerMappingService}�̃T�[�r�X����ݒ肷��B
     *
     * @param name �T�[�r�X��
     */
    public void setMessageSendExceptionHandlerMappingServiceName(ServiceName name);

    /**
     * �f�[�^���M���ɃG���[�����������ۂɏo�͂��郁�b�Z�[�WID���擾����B
     *
     * @return ���b�Z�[�WID
     */
    public String getSendErrorMessageId();

    /**
     * �f�[�^���M���ɃG���[�����������ۂɏo�͂��郁�b�Z�[�WID��ݒ肷��B�f�t�H���g��
     * {@link #DEFAULT_SEND_ERROR_MESSAGE_ID} �B
     *
     * @param messageId ���b�Z�[�WID
     */
    public void setSendErrorMessageId(String messageId);

    /**
     * ���b�Z�[�W�𑗐M���邽�߂�QueueHandlerContainer�ւ̃p�����[�^�I�u�W�F�N�g���ė��p���邽�߂̃��X�g�T�C�Y���擾����B
     *
     * @return ���X�g�T�C�Y
     */
    public int getMessageSendParameterRecycleListSize();

    /**
     * ���b�Z�[�W�𑗐M���邽�߂�QueueHandlerContainer�ւ̃p�����[�^�I�u�W�F�N�g���ė��p���邽�߂̃��X�g�T�C�Y��ݒ肷��B
     * �f�t�H���g�� {@link #DEFAULT_MESSAGE_SEND_PARAMETER_RECYCLE_LIST_SIZE} �B
     *
     * @param size ���X�g�T�C�Y
     */
    public void setMessageSendParameterRecycleListSize(int size);

    /**
     * ���b�Z�[�W���M�������擾����B
     * <p>
     *
     * @return ���b�Z�[�W���M����
     */
    public long getMessageSendCount();
}
