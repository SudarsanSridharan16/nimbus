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
 * {@link AbstractMessageHandlerFactoryService}��MBean�C���^�t�F�[�X�B
 * <p>
 *
 * @author M.Ishida
 */
public interface WebSocketAccessJournalServiceMBean extends ServiceBaseMBean {

    /**
     * ���N�G�X�g�W���[�i���̃��[�g�X�e�b�v�̃L�[�̃f�t�H���g�l�B
     * <p>
     */
    public static final String DEFAULT_ACCESS_JOURNAL_KEY = "Access";

    /**
     * �W���[�i����ID�̃L�[�̃f�t�H���g�l�B
     * <p>
     */
    public static final String DEFAULT_ID_JOURNAL_KEY = "Id";

    /**
     * �W���[�i���̃`�P�b�g�̃L�[�̃f�t�H���g�l�B
     * <p>
     */
    public static final String DEFAULT_TICKET_JOURNAL_KEY = "Ticket";

    /**
     * �W���[�i����WebSocket�Z�b�V����ID�̃L�[�̃f�t�H���g�l�B
     * <p>
     */
    public static final String DEFAULT_WEBSOCKET_SESSION_ID_JOURNAL_KEY = "WebSocketSessionId";

    /**
     * �W���[�i����Http�Z�b�V����ID�̃L�[�̃f�t�H���g�l�B
     * <p>
     */
    public static final String DEFAULT_HTTP_SESSION_ID_JOURNAL_KEY = "HttpSessionId";

    /**
     * �W���[�i���̃p�X�̃L�[�̃f�t�H���g�l�B
     * <p>
     */
    public static final String DEFAULT_PATH_JOURNAL_KEY = "Path";

    /**
     * �W���[�i����IP�̃L�[�̃f�t�H���g�l�B
     * <p>
     */
    public static final String DEFAULT_IP_JOURNAL_KEY = "Ip";

    /**
     * �W���[�i���̃|�[�g�̃L�[�̃f�t�H���g�l�B
     * <p>
     */
    public static final String DEFAULT_PORT_JOURNAL_KEY = "Port";

    /**
     * �W���[�i���̃��N�G�X�g�w�b�_�̃L�[�̃f�t�H���g�l�B
     * <p>
     */
    public static final String DEFAULT_HEADER_JOURNAL_KEY = "Header";

    /**
     * �W���[�i���̃��N�G�X�g�p�����[�^�̃L�[�̃f�t�H���g�l�B
     * <p>
     */
    public static final String DEFAULT_PARAMETER_JOURNAL_KEY = "Parameter";

    /**
     * �W���[�i���̃��N�G�X�g���b�Z�[�W�̃L�[�̃f�t�H���g�l�B
     * <p>
     */
    public static final String DEFAULT_REQUEST_MESSAGE_JOURNAL_KEY = "Message";

    /**
     * �W���[�i����CloseReason�̃L�[�̃f�t�H���g�l�B
     * <p>
     */
    public static final String DEFAULT_CLOSE_REASON_JOURNAL_KEY = "CloseReason";

    /**
     * �W���[�i����AuthResult�̃L�[�̃f�t�H���g�l�B
     * <p>
     */
    public static final String DEFAULT_AUTH_RESULT_JOURNAL_KEY = "AuthResult";

    /**
     * �W���[�i���̗�O�̃L�[�̃f�t�H���g�l�B
     * <p>
     */
    public static final String DEFAULT_EXCEPTION_JOURNAL_KEY = "Exception";

    /**
     * �W���[�i���̃T�[�r�X�����擾����B
     * <p>
     *
     * @return �W���[�i���̃T�[�r�X��
     */
    public ServiceName getJournalServiceName();

    /**
     * �W���[�i���̃T�[�r�X����ݒ肷��B
     * <p>
     *
     * @param name �W���[�i���̃T�[�r�X��
     */
    public void setJournalServiceName(ServiceName name);

    /**
     * �W���[�i�����O����ۂ�EditorFinder�T�[�r�X�̃T�[�r�X����ݒ肷��B
     * <p>
     *
     * @param name EditorFinder�T�[�r�X�̃T�[�r�X��
     */
    public void setEditorFinderServiceName(ServiceName name);

    /**
     * �W���[�i�����O����ۂ�EditorFinder�T�[�r�X�̃T�[�r�X�����擾����B
     * <p>
     *
     * @return EditorFinder�T�[�r�X�̃T�[�r�X��
     */
    public ServiceName getEditorFinderServiceName();

    /**
     * �W���[�i�����O����ۂ̃W���[�i���L�[����ݒ肷��B
     * <p>
     * �f�t�H���g�́A{@link #DEFAULT_ACCESS_JOURNAL_KEY}�B<br>
     *
     * @param key �W���[�i���L�[��
     */
    public void setAccessJournalKey(String key);

    /**
     * �W���[�i�����O����ۂ̃W���[�i���L�[�����擾����B
     * <p>
     *
     * @return �W���[�i���L�[��
     */
    public String getAccessJournalKey();

    /**
     * �N���C�A���gID���W���[�i�����O����ۂ̃W���[�i���L�[����ݒ肷��B
     * <p>
     * �f�t�H���g�́A{@link #DEFAULT_ID_JOURNAL_KEY}�B<br>
     *
     * @param key �W���[�i���L�[��
     */
    public void setIdJournalKey(String key);

    /**
     * �N���C�A���gID���W���[�i�����O����ۂ̃W���[�i���L�[�����擾����B
     * <p>
     *
     * @return �W���[�i���L�[��
     */
    public String getIdJournalKey();

    /**
     * �`�P�b�g���W���[�i�����O����ۂ̃W���[�i���L�[����ݒ肷��B
     * <p>
     * �f�t�H���g�́A{@link #DEFAULT_TICKET_JOURNAL_KEY}�B<br>
     *
     * @param key �W���[�i���L�[��
     */
    public void setTicketJournalKey(String key);

    /**
     * �`�P�b�g���W���[�i�����O����ۂ̃W���[�i���L�[�����擾����B
     * <p>
     *
     * @return �W���[�i���L�[��
     */
    public String getTicketJournalKey();

    /**
     * WebSocket�Z�b�V����ID���W���[�i�����O����ۂ̃W���[�i���L�[����ݒ肷��B
     * <p>
     * �f�t�H���g�́A{@link #DEFAULT_WEBSOCKET_SESSION_ID_JOURNAL_KEY}�B<br>
     *
     * @param key �W���[�i���L�[��
     */
    public void setWebSocketSessionIdJournalKey(String key);

    /**
     * WebSocket�Z�b�V����ID���W���[�i�����O����ۂ̃W���[�i���L�[�����擾����B
     * <p>
     *
     * @return �W���[�i���L�[��
     */
    public String getWebSocketSessionIdJournalKey();

    /**
     * HTTP�Z�b�V����ID���W���[�i�����O����ۂ̃W���[�i���L�[����ݒ肷��B
     * <p>
     * �f�t�H���g�́A{@link #DEFAULT_HTTP_SESSION_ID_JOURNAL_KEY}�B<br>
     *
     * @param key �W���[�i���L�[��
     */
    public void setHttpSessionIdJournalKey(String key);

    /**
     * HTTP�Z�b�V����ID���W���[�i�����O����ۂ̃W���[�i���L�[�����擾����B
     * <p>
     *
     * @return �W���[�i���L�[��
     */
    public String getHttpSessionIdJournalKey();

    /**
     * WebSocket�̃p�X���W���[�i�����O����ۂ̃W���[�i���L�[����ݒ肷��B
     * <p>
     * �f�t�H���g�́A{@link #DEFAULT_PATH_JOURNAL_KEY}�B<br>
     *
     * @param key �W���[�i���L�[��
     */
    public void setPathJournalKey(String key);

    /**
     * WebSocket�̃p�X���W���[�i�����O����ۂ̃W���[�i���L�[�����擾����B
     * <p>
     *
     * @return �W���[�i���L�[��
     */
    public String getPathJournalKey();

    /**
     * �N���C�A���gIP���W���[�i�����O����ۂ̃W���[�i���L�[����ݒ肷��B
     * <p>
     * �f�t�H���g�́A{@link #DEFAULT_SESSION_IP_JOURNAL_KEY}�B<br>
     *
     * @param key �W���[�i���L�[��
     */
    public void setIpJournalKey(String key);

    /**
     * �N���C�A���gIP���W���[�i�����O����ۂ̃W���[�i���L�[�����擾����B
     * <p>
     *
     * @return �W���[�i���L�[��
     */
    public String getIpJournalKey();

    /**
     * �N���C�A���g�|�[�g���W���[�i�����O����ۂ̃W���[�i���L�[����ݒ肷��B
     * <p>
     * �f�t�H���g�́A{@link #DEFAULT_SESSION_PORT_JOURNAL_KEY}�B<br>
     *
     * @param key �W���[�i���L�[��
     */
    public void setPortJournalKey(String key);

    /**
     * �N���C�A���g�|�[�g���W���[�i�����O����ۂ̃W���[�i���L�[�����擾����B
     * <p>
     *
     * @return �W���[�i���L�[��
     */
    public String getPortJournalKey();

    /**
     * ���b�Z�[�W���W���[�i�����O����ۂ̃W���[�i���L�[����ݒ肷��B
     * <p>
     * �f�t�H���g�́A{@link #DEFAULT_REQUEST_MESSAGE_JOURNAL_KEY}�B<br>
     *
     * @param key �W���[�i���L�[��
     */
    public void setRequestMessageJournalKey(String key);

    /**
     * ���b�Z�[�W���W���[�i�����O����ۂ̃W���[�i���L�[�����擾����B
     * <p>
     *
     * @return �W���[�i���L�[��
     */
    public String getRequestMessageJournalKey();

    /**
     * CloseReason���W���[�i�����O����ۂ̃W���[�i���L�[����ݒ肷��B
     * <p>
     * �f�t�H���g�́A{@link #DEFAULT_CLOSE_REASON_JOURNAL_KEY}�B<br>
     *
     * @param key �W���[�i���L�[��
     */
    public void setCloseReasonJournalKey(String key);

    /**
     * CloseReason���W���[�i�����O����ۂ̃W���[�i���L�[�����擾����B
     * <p>
     *
     * @return �W���[�i���L�[��
     */
    public String getCloseReasonJournalKey();

    /**
     * AuthResult���W���[�i�����O����ۂ̃W���[�i���L�[����ݒ肷��B
     * <p>
     * �f�t�H���g�́A{@link #DEFAULT_AUTH_RESULT_JOURNAL_KEY}�B<br>
     *
     * @param key �W���[�i���L�[��
     */
    public void setAuthResultJournalKey(String key);

    /**
     * AuthResult���W���[�i�����O����ۂ̃W���[�i���L�[�����擾����B
     * <p>
     *
     * @return �W���[�i���L�[��
     */
    public String getAuthResultJournalKey();

    /**
     * ���N�G�X�g�y�ё��M���̗�O���W���[�i�����O����ۂ̃W���[�i���L�[����ݒ肷��B
     * <p>
     * �f�t�H���g�́A{@link #DEFAULT_EXCEPTION_JOURNAL_KEY}�B<br>
     *
     * @param key �W���[�i���L�[��
     */
    public void setExceptionJournalKey(String key);

    /**
     * ���N�G�X�g�y�ё��M���̗�O���W���[�i�����O����ۂ̃W���[�i���L�[�����擾����B
     * <p>
     *
     * @return �W���[�i���L�[��
     */
    public String getExceptionJournalKey();

    /**
     * ���N�G�X�g���W���[�i�����O����ۂɃ��N�G�X�g�ʔԂ𔭍s����Sequence�T�[�r�X�̃T�[�r�X����ݒ肷��B
     * <p>
     *
     * @param name Sequence�T�[�r�X�̃T�[�r�X��
     */
    public void setSequenceServiceName(ServiceName name);

    /**
     * ���N�G�X�g���W���[�i�����O����ۂɃ��N�G�X�g�ʔԂ𔭍s����Sequence�T�[�r�X�̃T�[�r�X�����擾����B
     * <p>
     *
     * @return Sequence�T�[�r�X�̃T�[�r�X��
     */
    public ServiceName getSequenceServiceName();
}