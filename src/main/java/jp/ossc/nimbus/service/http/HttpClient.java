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
package jp.ossc.nimbus.service.http;

import javax.servlet.http.Cookie;

/**
 * HTTP�N���C�A���g�B<p>
 *
 * @author M.Takata
 */
public interface HttpClient{
    
    /**
     * �N�b�L�[��ǉ�����B<p>
     *
     * @param cookie �N�b�L�[
     */
    public void addCookie(Cookie cookie);
    
    /**
     * �N�b�L�[���擾����B<p>
     *
     * @return �N�b�L�[�z��
     */
    public Cookie[] getCookies();
    
    /**
     * HTTP���N�G�X�g�𔭍s����B<p>
     *
     * @param request HTTP���N�G�X�g
     * @return HTTP���X�|���X
     * @exception HttpException HTTP���N�G�X�g�����ŗ�O�����������ꍇ
     */
    public HttpResponse executeRequest(HttpRequest request)
     throws HttpException;
    
    /**
     * HTTP�N���C�A���g���I������B<p>
     *
     * @exception HttpException HTTP�N���C�A���g�̏I�������ŗ�O�����������ꍇ
     */
    public void close() throws HttpException;
}