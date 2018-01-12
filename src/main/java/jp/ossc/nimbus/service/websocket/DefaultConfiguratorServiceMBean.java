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

import jp.ossc.nimbus.core.ServiceName;

/**
 * {@link DefaultConfiguratorService}��MBean�C���^�t�F�[�X
 * <p>
 *
 * @author M.Ishida
 */
public interface DefaultConfiguratorServiceMBean extends NimbusConfigurator {

    /**
     * Header�܂��̓��N�G�X�g�p�����[�^�Ƀn���h�V�F�C�N�F�؎��Ɏg�p����ID��ݒ肷��ۂ̃L�[�̃f�t�H���g�l�B
     * <p>
     */
    public static final String DEFAULT_HANDSHAKE_ID_KEY = "id";

    /**
     * Header�܂��̓��N�G�X�g�p�����[�^�Ƀn���h�V�F�C�N�F�؎��Ɏg�p����`�P�b�g��ݒ肷��ۂ̃L�[�̃f�t�H���g�l�B
     * <p>
     */
    public static final String DEFAULT_HANDSHAKE_TICKET_KEY = "ticket";

    /**
     * ThreadContext�ɃN���C�A���gIP�A�h���X��ݒ肷��ۂ̃L�[�̃f�t�H���g�l�B
     * <p>
     */
    public static final String DEFAULT_CONTEXT_IP_KEY = "WebSocket-Remote-IP";

    /**
     * ThreadContext�ɃN���C�A���g�|�[�g��ݒ肷��ۂ̃L�[�̃f�t�H���g�l�B
     * <p>
     */
    public static final String DEFAULT_CONTEXT_PORT_KEY = "WebSocket-Remote-Port";

    /**
     * Endpoint�ɑ΂���p�X��ݒ肷��B
     * <p>
     *
     * @param path Endpoint�ɑ΂���p�X
     */
    public void setPath(String path);

    /**
     * Endpoint�T�[�r�X�����擾����B
     * <p>
     *
     * @return Endpoint�T�[�r�X��
     */
    public ServiceName getEndpointServiceName();

    /**
     * Endpoint�T�[�r�X����ݒ肷��B
     * <p>
     *
     * @param name Endpoint�T�[�r�X��
     */
    public void setEndpointServiceName(ServiceName name);

    /**
     * ThreadContext�T�[�r�X�����擾����B
     * <p>
     *
     * @return ThreadContext�T�[�r�X��
     */
    public ServiceName getThreadContextServiceName();

    /**
     * ThreadContext�T�[�r�X����ݒ肷��B
     * <p>
     *
     * @param name ThreadContext�T�[�r�X��
     */
    public void setThreadContextServiceName(ServiceName name);

    /**
     * ID���ݒ肳��Ă��郊�N�G�X�g�p�����[�^�̃L�[���擾����B
     * <p>
     *
     * @return ID���ݒ肳��Ă��郊�N�G�X�g�p�����[�^�̃L�[
     */
    public String getIdKey();

    /**
     * ID���ݒ肳��Ă��郊�N�G�X�g�p�����[�^�̃L�[��ݒ肷��B�f�t�H���g��{@link #DEFAULT_HANDSHAKE_ID_KEY}�B
     * <p>
     *
     * @param idKey ID���ݒ肳��Ă��郊�N�G�X�g�p�����[�^�̃L�[
     */
    public void setIdKey(String idKey);

    /**
     * �`�P�b�g���ݒ肳��Ă��郊�N�G�X�g�p�����[�^�̃L�[���擾����B
     * <p>
     *
     * @return �`�P�b�g���ݒ肳��Ă��郊�N�G�X�g�p�����[�^�̃L�[
     */
    public String getTicketKey();

    /**
     * �`�P�b�g���ݒ肳��Ă��郊�N�G�X�g�p�����[�^�̃L�[��ݒ肷��B�f�t�H���g��
     * {@link #DEFAULT_HANDSHAKE_TICKET_KEY}�B
     * <p>
     *
     * @param ticketKey �`�P�b�g���ݒ肳��Ă��郊�N�G�X�g�p�����[�^�̃L�[
     */
    public void setTicketKey(String ticketKey);

    /**
     * ThreadContext�ɃN���C�A���gIP�A�h���X��ݒ肷��ۂ̃L�[���擾����B
     * <p>
     *
     * @return ThreadContext�ɃN���C�A���gIP�A�h���X��ݒ肷��ۂ̃L�[
     */
    public String getContextIpKey();

    /**
     * ThreadContext�ɃN���C�A���gIP�A�h���X��ݒ肷��ۂ̃L�[��ݒ肷��B�f�t�H���g��{@link #DEFAULT_CONTEXT_IP_KEY}�B
     * <p>
     *
     * @param key ThreadContext�ɃN���C�A���gIP�A�h���X��ݒ肷��ۂ̃L�[
     */
    public void setContextIpKey(String key);

    /**
     * ThreadContext�ɃN���C�A���g�|�[�g��ݒ肷��ۂ̃L�[���擾����B
     * <p>
     *
     * @return ThreadContext�ɃN���C�A���g�|�[�g��ݒ肷��ۂ̃L�[
     */
    public String getContextPortKey();

    /**
     * ThreadContext�ɃN���C�A���g�|�[�g��ݒ肷��ۂ̃L�[��ݒ肷��B�f�t�H���g��{@link #DEFAULT_CONTEXT_PORT_KEY}�B
     * <p>
     *
     * @param key ThreadContext�ɃN���C�A���g�|�[�g��ݒ肷��ۂ̃L�[
     */
    public void setContextPortKey(String key);

}
