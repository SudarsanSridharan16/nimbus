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
package jp.ossc.nimbus.service.scp.ganymed;

import java.io.File;

import jp.ossc.nimbus.core.*;

/**
 * {@link SCPClientFactoryService}��MBean�C���^�t�F�[�X<p>
 * 
 * @author M.Takata
 * @see SCPClientFactoryService
 */
public interface SCPClientFactoryServiceMBean extends ServiceBaseMBean{
    
    /**
     * TCP�̐ڑ��^�C���A�E�g[ms]��ݒ肷��B<p>
     * �f�t�H���g�ł́A�^�C���A�E�g���Ȃ��B<br>
     *
     * @param timeout �^�C���A�E�g[ms]
     */
    public void setConnectionTimeout(int timeout);
    
    /**
     * TCP�̐ڑ��^�C���A�E�g[ms]���擾����B<p>
     *
     * @return �^�C���A�E�g[ms]
     */
    public int getConnectionTimeout();
    
    /**
     * SSH�ڑ��^�C���A�E�g[ms]��ݒ肷��B<p>
     * �f�t�H���g�ł́A�^�C���A�E�g���Ȃ��B<br>
     *
     * @param timeout �^�C���A�E�g[ms]
     */
    public void setKeyExchangeTimeout(int timeout);
    
    /**
     * SSH�ڑ��^�C���A�E�g[ms]���擾����B<p>
     *
     * @return �^�C���A�E�g[ms]
     */
    public int getKeyExchangeTimeout();
    
    /**
     * TCP_NODELAY�̗L��/������ݒ肷��B<p>
     *
     * @param noDelay �L���ɂ���ꍇtrue
     */
    public void setTcpNoDelay(boolean noDelay);
    
    /**
     * TCP_NODELAY�̗L��/�����𔻒肷��B<p>
     *
     * @return true�̏ꍇ�A�L��
     */
    public boolean isTcpNoDelay();
    
    /**
     * �ڑ���T�[�o�̃z�X�g����ݒ肷��B<p>
     * ���̑������w�肵���ꍇ�A{@link SCPClientFactoryService#createSCPClient()}�Ő�������{@link jp.ossc.nimbus.service.scp.SCPClient SCPClient}�́A�ڑ��ςƂȂ�B<br>
     *
     * @param addr �z�X�g��
     */
    public void setHostName(String addr);
    
    /**
     * �ڑ���T�[�o�̃z�X�g�����擾����B<p>
     *
     * @return �z�X�g��
     */
    public String getHostName();
    
    /**
     * �ڑ���T�[�o�̃|�[�g�ԍ���ݒ肷��B<p>
     *
     * @param port �|�[�g�ԍ�
     */
    public void setPort(int port);
    
    /**
     * �ڑ���T�[�o�̃|�[�g�ԍ����擾����B<p>
     *
     * @return �|�[�g�ԍ�
     */
    public int getPort();
    
    /**
     * �F�؂��郆�[�U����ݒ肷��B<p>
     * ���̑������w�肵���ꍇ�A{@link SCPClientFactoryService#createSCPClient()}�Ő�������{@link jp.ossc.nimbus.service.scp.SCPClient SCPClient}�́A�F�؍ςƂȂ�B<br>
     *
     * @param name ���[�U��
     */
    public void setUserName(String name);
    
    /**
     * �F�؂��郆�[�U�����擾����B<p>
     *
     * @return ���[�U��
     */
    public String getUserName();
    
    /**
     * �F�؂��郆�[�U�̃p�X���[�h�܂��͔閧���̃p�X�t���[�Y��ݒ肷��B<p>
     *
     * @param password �p�X���[�h�܂��͔閧���̃p�X�t���[�Y
     */
    public void setPassword(String password);
    
    /**
     * �F�؂��郆�[�U�̃p�X���[�h�܂��͔閧���̃p�X�t���[�Y���擾����B<p>
     *
     * @return �p�X���[�h�܂��͔閧���̃p�X�t���[�Y
     */
    public String getPassword();
    
    /**
     * �閧���t�@�C���̃p�X��ݒ肷��B<p>
     *
     * @param path �閧���t�@�C���̃p�X
     */
    public void setPemFile(File path);
    
    /**
     * �閧���t�@�C���̃p�X���擾����B<p>
     *
     * @return �閧���t�@�C���̃p�X
     */
    public File getPemFile();
    
    /**
     * �������A���S���Y����ݒ肷��B<p>
     *
     * @param algos �������A���S���Y�����z��
     */
    public void setServerHostKeyAlgorithms(String[] algos);
    
    /**
     * �������A���S���Y�����擾����B<p>
     *
     * @return �������A���S���Y�����z��
     */
    public String[] getServerHostKeyAlgorithms();
    
    /**
     * ���O�C������̃N���C�A���g�̃z�[���f�B���N�g����ݒ肷��B<p>
     * �ݒ肵�Ȃ��ꍇ�́A�V�X�e���v���p�e�B"user.home"�̎����f�B���N�g���ƂȂ�B<br>
     * 
     * @param dir �z�[���f�B���N�g��
     */
    public void setHomeDirectory(File dir);
    
    /**
     * ���O�C������̃N���C�A���g�̃z�[���f�B���N�g�����擾����B<p>
     * 
     * @return �z�[���f�B���N�g��
     */
    public File getHomeDirectory();
}