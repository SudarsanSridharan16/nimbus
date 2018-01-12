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
package jp.ossc.nimbus.service.http.proxy;

import jp.ossc.nimbus.core.*;

/**
 * {@link HttpProcessServiceBase}��MBean�C���^�t�F�[�X�B<p>
 * 
 * @author M.Takata
 * @see HttpProcessServiceBase
 */
public interface HttpProcessServiceBaseMBean extends ServiceBaseMBean{
    
    /**
     * ���N�G�X�g�X�g���[���̉𓀂��s�����ǂ�����ݒ肷��B<p>
     * �f�t�H���g�́Atrue�B<br>
     *
     * @param isInflate �𓀂��s���ꍇ�́Atrue
     */
    public void setRequestStreamInflate(boolean isInflate);
    
    /**
     * ���N�G�X�g�X�g���[���̉𓀂��s�����ǂ����𔻒肷��B<p>
     *
     * @return true�̏ꍇ�A�𓀂��s��
     */
    public boolean isRequestStreamInflate();
    
    /**
     * HTTP�g���l�����O���s���ꍇ�ɃT�[�o�Ƃ̐ڑ��Ɏg�p����SocketFactory�T�[�r�X�̃T�[�r�X����ݒ肷��B<p>
     *
     * @param name SocketFactory�T�[�r�X�̃T�[�r�X��
     */
    public void setTunnelSocketFactoryServiceName(ServiceName name);
    
    /**
     * HTTP�g���l�����O���s���ꍇ�ɃT�[�o�Ƃ̐ڑ��Ɏg�p����SocketFactory�T�[�r�X�̃T�[�r�X�����擾����B<p>
     *
     * @return SocketFactory�T�[�r�X�̃T�[�r�X��
     */
    public ServiceName getTunnelSocketFactoryServiceName();
    
    /**
     * HTTP�g���l�����O���s���ꍇ�̒ʐM�o�b�t�@�T�C�Y��ݒ肷��B<p>
     *
     * @param size �ʐM�o�b�t�@�T�C�Y[byte]
     */
    public void setTunnelBufferSize(int size);
    
    /**
     * HTTP�g���l�����O���s���ꍇ�̒ʐM�o�b�t�@�T�C�Y���擾����B<p>
     *
     * @return �ʐM�o�b�t�@�T�C�Y[byte]
     */
    public int getTunnelBufferSize();
    
    /**
     * �v���L�V�̃z�X�g�����擾����B<p>
     *
     * @return �v���L�V�̃z�X�g��
     */
    public String getProxyHost();
    
    /**
     * �v���L�V�̃z�X�g����ݒ肷��B<p>
     *
     * @param host �v���L�V�̃z�X�g��
     */
    public void setProxyHost(String host);
    
    /**
     * �v���L�V�̃|�[�g�ԍ����擾����B<p>
     *
     * @return �v���L�V�̃|�[�g�ԍ�
     */
    public int getProxyPort();
    
    /**
     * �v���L�V�̃|�[�g�ԍ���ݒ肷��B<p>
     *
     * @param port �v���L�V�̃|�[�g�ԍ�
     */
    public void setProxyPort(int port);
    
    /**
     * �v���L�V��BASIC�F�؃��[�U�����擾����B<p>
     *
     * @return �v���L�V��BASIC�F�؃��[�U��
     */
    public String getProxyUser();
    
    /**
     * �v���L�V��BASIC�F�؃��[�U����ݒ肷��B<p>
     *
     * @param user �v���L�V��BASIC�F�؃��[�U��
     */
    public void setProxyUser(String user);
    
    /**
     * �v���L�V��BASIC�F�؃p�X���[�h���擾����B<p>
     *
     * @return �v���L�V��BASIC�F�؃p�X���[�h
     */
    public String getProxyPassword();
    
    /**
     * �v���L�V��BASIC�F�؃p�X���[�h��ݒ肷��B<p>
     *
     * @param password �v���L�V��BASIC�F�؃p�X���[�h
     */
    public void setProxyPassword(String password);
}