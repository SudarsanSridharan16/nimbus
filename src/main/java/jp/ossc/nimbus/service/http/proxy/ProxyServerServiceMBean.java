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
 * {@link ProxyServerService}��MBean�C���^�t�F�[�X�B<p>
 * 
 * @author M.Takata
 * @see ProxyServerService
 */
public interface ProxyServerServiceMBean extends ServiceBaseMBean{
    
    /**
     * �f�t�H���g�̑҂��󂯃|�[�g�ԍ��B<p>
     */
    public static final int DEFAULT_PORT = 8080;
    
    /**
     * {@link Process}�T�[�r�X�̃T�[�r�X����ݒ肷��B<p>
     *
     * @param name Process�T�[�r�X�̃T�[�r�X��
     */
    public void setProcessServiceName(ServiceName name);
    
    /**
     * {@link Process}�T�[�r�X�̃T�[�r�X�����擾����B<p>
     *
     * @return Process�T�[�r�X�̃T�[�r�X��
     */
    public ServiceName getProcessServiceName();
    
    /**
     * {@link javax.net.ServerSocketFactory}�T�[�r�X�̃T�[�r�X����ݒ肷��B<p>
     *
     * @param name ServerSocketFactory�T�[�r�X�̃T�[�r�X��
     */
    public void setServerSocketFactoryServiceName(ServiceName name);
    
    /**
     * {@link javax.net.ServerSocketFactory}�T�[�r�X�̃T�[�r�X�����擾����B<p>
     *
     * @return ServerSocketFactory�T�[�r�X�̃T�[�r�X��
     */
    public ServiceName getServerSocketFactoryServiceName();
    
    /**
     * �T�[�o�\�P�b�g�̃o�C���h�A�h���X��ݒ肷��B<p>
     *
     * @param address �o�C���h�A�h���X
     */
    public void setBindAddress(String address);
    
    /**
     * �T�[�o�\�P�b�g�̃o�C���h�A�h���X���擾����B<p>
     *
     * @return �o�C���h�A�h���X
     */
    public String getBindAddress();
    
    /**
     * �T�[�o�\�P�b�g�̃o�b�N���O��ݒ肷��B<p>
     * �f�t�H���g�́A0�B<br>
     *
     * @param backlog �o�b�N���O
     */
    public void setBacklog(int backlog);
    
    /**
     * �T�[�o�\�P�b�g�̃o�b�N���O���擾����B<p>
     *
     * @return �o�b�N���O
     */
    public int getBacklog();
    
    /**
     * ���̃v���L�V�̑҂��󂯃|�[�g�ԍ���ݒ肷��B<p>
     * �f�t�H���g�́A{@link #DEFAULT_PORT}�B<br>
     *
     * @param port �|�[�g�ԍ�
     */
    public void setPort(int port);
    
    /**
     * ���̃v���L�V�̑҂��󂯃|�[�g�ԍ����擾����B<p>
     *
     * @return �|�[�g�ԍ�
     */
    public int getPort();
    
    /**
     * �\�P�b�g�̓ǂݍ��݃^�C���A�E�g[ms]��ݒ肷��B<p>
     * �f�t�H���g�́A�^�C���A�E�g���Ȃ��B<br>
     *
     * @param millis �ǂݍ��݃^�C���A�E�g[ms]
     */
    public void setSoTimeout(int millis);
    
    /**
     * �\�P�b�g�̓ǂݍ��݃^�C���A�E�g[ms]���擾����B<p>
     *
     * @return �ǂݍ��݃^�C���A�E�g[ms]
     */
    public int getSoTimeout();
    
    /**
     * ���N�G�X�g�����X���b�h����ݒ肷��B<p>
     * �f�t�H���g�́A1�B<br>
     *
     * @param count �X���b�h��
     */
    public void setMaxProcessCount(int count);
    
    /**
     * ���N�G�X�g�����X���b�h�����擾����B<p>
     *
     * @return �X���b�h��
     */
    public int getMaxProcessCount();
}
