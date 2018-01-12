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

import jp.ossc.nimbus.core.ServiceName;

/**
 * {@link AbstractJMSMessageDispatcherService}��MBean�C���^�t�F�[�X
 * <p>
 *
 * @author M.Ishida
 */
public interface AbstractJMSMessageDispatcherServiceMBean extends AbstractPublishMessageDispatcherServiceMBean {

    /**
     * JMS���b�Z�[�W����M���邽�߂�JmsMessageConsumerFactoryService�̃T�[�r�X�����擾����B
     * <p>
     *
     * @return JmsMessageConsumerFactoryService�̃T�[�r�X��
     */
    public ServiceName[] getJmsMessageConsumerFactoryServiceNames();

    /**
     * JMS���b�Z�[�W����M���邽�߂�JmsMessageConsumerFactoryService�̃T�[�r�X����ݒ肷��B
     * <p>
     *
     * @param names JmsMessageConsumerFactoryService�̃T�[�r�X��
     */
    public void setJmsMessageConsumerFactoryServiceNames(ServiceName[] names);

    /**
     * �T�[�r�X�̊J�n���ɁA��M���J�n���邩�ǂ����𔻒肷��B
     * <p>
     *
     * @return true�̏ꍇ�A��M���J�n����
     */
    public boolean isStartReceiveOnStart();

    /**
     * �T�[�r�X�̊J�n���ɁA��M���J�n���邩�ǂ�����ݒ肷��B
     * <p>
     *
     * @param isStart ��M���J�n����ꍇ�Atrue
     */
    public void setStartReceiveOnStart(boolean isStart);

    /**
     * ���b�Z�[�W�̎�M�������擾����B
     *
     * @return ���b�Z�[�W�̎�M����
     */
    public long getMessageReceiveCount();

    /**
     * ��M���J�n����B
     * <p>
     *
     * @throws Exception
     */
    public void startReceive() throws Exception;

    /**
     * ��M���~����B
     * <p>
     *
     * @throws Exception
     */
    public void stopReceive() throws Exception;

}
