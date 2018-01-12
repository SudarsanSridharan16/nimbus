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
package jp.ossc.nimbus.service.websocket;

import jp.ossc.nimbus.core.ServiceBaseMBean;
import jp.ossc.nimbus.core.ServiceName;

/**
 * {@link DefaultPingPongHandlerService}��MBean�C���^�t�F�[�X
 * <p>
 *
 * @author M.Ishida
 */
public interface DefaultPingPongHandlerServiceMBean extends ServiceBaseMBean {

    /**
     * Ping���M�˗�������UserProperties�Ɋi�[����ۂ̃L�[�B
     * <p>
     */
    public static final String PING_REQUEST_TIME_KEY = "PingRequestTime";

    /**
     * Ping���M������UserProperties�Ɋi�[����ۂ̃L�[�B
     * <p>
     */
    public static final String PING_SEND_TIME_KEY = "PingSendTime";

    /**
     * Ping���M���b�Z�[�W�̃f�t�H���g�l�B
     * <p>
     */
    public static final String DEFAULT_PING_MESSAGE = "";

    /**
     * Ping���M�C���^�[�o���̃f�t�H���g�l�B
     * <p>
     */
    public static final long DEFAULT_PING_SEND_INTERVAL = 5000l;

    /**
     * PingSendQueueHandlerContainerService���w�肳��Ȃ������ꍇ��Queue�T�C�Y�B
     * <p>
     */
    public static final int DEFAULT_QUEUE_SIZE = 1;

    /**
     * Ping���M�G���[�����������ۂɏo�͂��郁�b�Z�[�WID�̃f�t�H���g�l�B
     * <p>
     */
    public static final String DEFAULT_PING_SEND_ERROR_MESSAGE_ID = "WS___00005";

    /**
     * Pong����M���e�t���O�̃f�t�H���g�l�B
     * <p>
     */
    public static final boolean DEFAULT_ALLOW_NO_PONG = false;

    /**
     * Ping�𑗐M����ۂ�QueueHandlerContainerService�̃T�[�r�X�����擾����B
     * <p>
     *
     * @return QueueHandlerContainerService�̃T�[�r�X��
     */
    public ServiceName getPingSendQueueHandlerContainerServiceName();

    /**
     * Ping�𑗐M����ۂ�QueueHandlerContainerService�̃T�[�r�X����ݒ肷��B
     * <p>
     *
     * @param name QueueHandlerContainerService�̃T�[�r�X��
     */
    public void setPingSendQueueHandlerContainerServiceName(ServiceName name);

    /**
     * QueueHandlerContainerService���ݒ肳��Ȃ������ꍇ�Ɏg�p����f�t�H���g��QueueHandler�̃T�C�Y���擾����B
     * <p>
     *
     * @return QueueHandler�̃T�C�Y
     */
    public int getQueueHandlerSize();

    /**
     * QueueHandlerContainerService���ݒ肳��Ȃ������ꍇ�Ɏg�p����f�t�H���g��QueueHandler�̃T�C�Y��ݒ肷��B
     * <p>
     *
     * @param size QueueHandler�̃T�C�Y
     */
    public void setQueueHandlerSize(int size);

    /**
     * Ping���M���b�Z�[�W���擾����B
     * <p>
     *
     * @return Ping���M���b�Z�[�W
     */
    public String getPingMessage();

    /**
     * Ping���M���b�Z�[�W��ݒ肷��B
     * <p>
     *
     * @param message Ping���M���b�Z�[�W
     */
    public void setPingMessage(String message);

    /**
     * Ping���M�C���^�[�o��(�~���b)���擾����B
     * <p>
     *
     * @return Ping���M�C���^�[�o��(�~���b)
     */
    public long getPingSendInterval();

    /**
     * Ping���M�C���^�[�o��(�~���b)��ݒ肷��B
     *
     * @param interval Ping���M�C���^�[�o��(�~���b)
     */
    public void setPingSendInterval(long interval);

    /**
     * Ping���M�G���[�����������ۂɏo�͂��郁�b�Z�[�WID���擾����B
     *
     * @return ���b�Z�[�WID
     */
    public String getPingSendErrorMessageId();

    /**
     * Ping���M�G���[�����������ۂɏo�͂��郁�b�Z�[�WID��ݒ肷��B�f�t�H���g��
     * {@link #DEFAULT_PING_SEND_ERROR_MESSAGE_ID} �B
     *
     * @param messageId
     */
    public void setPingSendErrorMessageId(String messageId);

}
