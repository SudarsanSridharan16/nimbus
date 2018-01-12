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

import javax.servlet.http.HttpServletRequest;

/**
 * ���O�C���������ʂ�WebSocket�ڑ�URL�ɕK�v�ȏ����i�[����Bean�B
 * <p>
 *
 * @author M.Ishida
 */
public class AuthResult {

    /**
     * ���[�U����肷��ID
     */
    private String id;

    /**
     * handshake�F�؂Ɏg�p����`�P�b�g���
     */
    private String ticket;

    /**
     * �F�،���
     */
    private boolean result;

    /**
     * URL�X�L�[�}�iws/wss�j
     */
    private String urlSchema;

    /**
     * �z�X�g�iIP�A�h���X�j
     */
    private String host;

    /**
     * �|�[�g
     */
    private int port = -1;

    /**
     * �R���e�L�X�g�p�X
     */
    private String contextPath;

    /**
     * url
     */
    private String url;

    /**
     * WebSocket�p�X
     */
    private String webSocketPath;

    /**
     * ���[�U����肷��ID���擾����B
     * <p>
     *
     * @return ���[�U����肷��ID
     */
    public String getId() {
        return id;
    }

    /**
     * ���[�U����肷��ID��ݒ肷��B
     * <p>
     *
     * @param id ���[�U����肷��ID
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * handshake�F�؂Ɏg�p����`�P�b�g�����擾����B
     * <p>
     *
     * @return handshake�F�؂Ɏg�p����`�P�b�g���
     */
    public String getTicket() {
        return ticket;
    }

    /**
     * handshake�F�؂Ɏg�p����`�P�b�g����ݒ肷��B
     * <p>
     *
     * @param ticket handshake�F�؂Ɏg�p����`�P�b�g���
     */
    public void setTicket(String ticket) {
        this.ticket = ticket;
    }

    /**
     * �F�،��ʂ��擾����B
     * <p>
     *
     * @return �F�،���
     */
    public boolean isResult() {
        return result;
    }

    /**
     * �F�،��ʂ�ݒ肷��B
     * <p>
     *
     * @param result �F�،���
     */
    public void setResult(boolean result) {
        this.result = result;
    }

    /**
     * URL�X�L�[�}��ݒ肷��B�iws�܂���wss��ݒ�j
     * <p>
     *
     * @param urlSchema URL�X�L�[�}
     */
    public void setUrlSchema(String urlSchema) {
        this.urlSchema = urlSchema;
    }

    /**
     * �z�X�g�iIP�A�h���X�j��ݒ肷��B
     * <p>
     *
     * @param host �z�X�g�iIP�A�h���X�j
     */
    public void setHost(String host) {
        this.host = host;
    }

    /**
     * �|�[�g��ݒ肷��B
     * <p>
     *
     * @param port �|�[�g
     */
    public void setPort(int port) {
        this.port = port;
    }

    /**
     * �R���e�L�X�g�p�X��ݒ肷��B
     * <p>
     *
     * @param contextPath �R���e�L�X�g�p�X
     */
    public void setContextPath(String contextPath) {
        this.contextPath = contextPath;
    }

    /**
     * WebSocket�p�X��ݒ肷��B
     * <p>
     *
     * @param webSocketPath WebSocket�p�X
     */
    public void setWebSocketPath(String webSocketPath) {
        this.webSocketPath = webSocketPath;
    }

    /**
     * URL���擾����B�F��NG�̏ꍇ��null��ԋp����B
     * <p>
     *
     * @return URL�p�X
     */
    public String getUrl() {
        if (result) {
            return url;
        }
        return null;
    }

    /**
     * URL��ݒ肷��B
     * <p>
     *
     * @param req HTTP���N�G�X�g
     * @param paramWebsocketPath WebSocket�p�X
     */
    public void setUrl(HttpServletRequest req, String paramWebsocketPath) {
        urlSchema = removeSlashAndColon(urlSchema);
        if (urlSchema == null || urlSchema.length() == 0) {
            urlSchema = "ws";
        }
        host = removeSlashAndColon(host);
        if (host == null || host.length() == 0) {
            host = removeSlashAndColon(req.getLocalAddr());
        }
        if (port == -1) {
            port = req.getLocalPort();
        }
        contextPath = removeSlashAndColon(contextPath);
        if (contextPath == null || contextPath.length() == 0) {
            contextPath = removeSlashAndColon(req.getContextPath());
        }
        webSocketPath = removeSlashAndColon(webSocketPath);
        if (webSocketPath == null || webSocketPath.length() == 0) {
            webSocketPath = removeSlashAndColon(paramWebsocketPath);
        }
        url = urlSchema + "://" + host + ":" + port + "/" + contextPath + "/" + webSocketPath;
    }

    private static String removeSlashAndColon(String str) {
        if (str == null || str.length() == 0) {
            return str;
        }
        final String slash = "/";
        final String colon = ":";
        while (str.startsWith(slash) || str.endsWith(slash) || str.startsWith(colon) || str.endsWith(colon)) {
            if (str.startsWith(slash) || str.startsWith(colon)) {
                str = str.substring(1);
            }
            if (str.endsWith(slash) || str.endsWith(colon)) {
                str = str.substring(0, str.length() - 1);
            }
        }
        return str;
    }

    public String toString() {
        return "[id:" + id + ", ticket:" + ticket + ", result:" + result + ", url:" + getUrl() + "]";
    }
}
