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
package jp.ossc.nimbus.service.test.proxy.netcrusher;

import jp.ossc.nimbus.core.ServiceBaseMBean;
import jp.ossc.nimbus.service.test.proxy.TcpNetProxy;

/**
 * {@link TcpNetProxyService}��MBean�C���^�t�F�[�X�B<p>
 *
 * @author M.Ishida
 */
public interface TcpNetProxyServiceMBean extends ServiceBaseMBean, TcpNetProxy {

    /**
     * TcpCrusher�ɐݒ肷��BindAddress���擾����B<p>
     *
     * @return TcpCrusher�ɐݒ肷��BindAddress
     */
    public String getBindAddress();

    /**
     * TcpCrusher�ɐݒ肷��BindAddress��ݒ肷��B<p>
     *
     * @param address TcpCrusher�ɐݒ肷��BindAddress
     */
    public void setBindAddress(String address);

    /**
     * TcpCrusher�ɐݒ肷��BindPort���擾����B<p>
     *
     * @return TcpCrusher�ɐݒ肷��BindPort
     */
    public int getBindPort();

    /**
     * TcpCrusher�ɐݒ肷��BindPort��ݒ肷��B<p>
     *
     * @param port TcpCrusher�ɐݒ肷��BindPort
     */
    public void setBindPort(int port);

    /**
     * �v���L�V�̐ڑ���A�h���X���擾����B<p>
     *
     * @return �v���L�V�̐ڑ���A�h���X
     */
    public String getConnectAddress();

    /**
     * �v���L�V�̐ڑ���A�h���X��ݒ肷��B<p>
     *
     * @param address �v���L�V�̐ڑ���A�h���X
     */
    public void setConnectAddress(String address);

    /**
     * �v���L�V�̐ڑ���Port���擾����B<p>
     *
     * @return �v���L�V�̐ڑ���Port
     */
    public int getConnectPort();

    /**
     * �v���L�V�̐ڑ���Port��ݒ肷��B<p>
     *
     * @param port �v���L�V�̐ڑ���Port
     */
    public void setConnectPort(int port);

    /**
     * �T�[�r�X�J�n���ɃI�[�v�����邩���擾����B<p>
     *
     * @return �T�[�r�X�J�n���ɃI�[�v������ꍇ�Atrue
     */
    public boolean isOpenOnStart();

    /**
     * �T�[�r�X�J�n���ɃI�[�v�����邩��ݒ肷��B<p>
     *
     * @param isOpenOnStart �T�[�r�X�J�n���ɃI�[�v������ꍇ�Atrue
     */
    public void setOpenOnStart(boolean isOpenOnStart);
}
