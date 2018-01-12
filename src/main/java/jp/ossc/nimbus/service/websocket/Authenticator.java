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
import javax.servlet.http.HttpServletResponse;

/**
 * WebSocket�̃A�v���P�[�V�������x���̔F�؂��s���C���^�t�F�[�X�B
 * <p>
 * ���N�G�X�g/���v���C�^�̔F�،�AWebSocket�ł̍ĔF�؂��s���B<br>
 *
 * @author M.Ishida
 */
public interface Authenticator {

    /**
     * ���O�C�������B
     * <p>
     * WebSocketAuthServlet����Ăяo�����B<br>
     * �F�،��ʂ�Bean��WebSocket�ڑ�URL�֘A����`�P�b�g��񂪊܂܂��B<br>
     *
     * @param req HttpServletRequest
     * @param res HttpServletResponse
     * @return �F�،��ʂ�Bean
     * @throws AuthenticateException �������ɗ�O�����������ꍇ
     */
    public AuthResult login(HttpServletRequest req, HttpServletResponse res) throws AuthenticateException;

    /**
     * �n���h�V�F�C�N�F�؏����B ���O�C���F�؎��ɕԋp�����`�P�b�g���󂯎��s���ȃn���h�V�F�C�N���N�G�X�g�ł͂Ȃ����Ƃ����؂���B
     * <p>
     *
     * @param id ���[�U����肷��ID
     * @param ticket WebSocket��Handshake�F�؂Ɏg�p����`�P�b�g���B���O�C�������̕ԋp�l�Ɋ܂܂��B
     * @return �F�،���
     * @throws AuthenticateException �������ɗ�O�����������ꍇ
     */
    public boolean handshake(String id, String ticket) throws AuthenticateException;

    /**
     * ���O�A�E�g����B
     * <p>
     *
     * @param id ���[�U����肷��id
     * @param ticket �F�؂Ɏg�p����`�P�b�g���
     * @param isForce �����A�ُ�I���̏ꍇ�́Atrue
     * @throws AuthenticateException �������ɗ�O�����������ꍇ
     */
    public void logout(String id, String ticket, boolean isForce) throws AuthenticateException;

}
