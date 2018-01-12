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
package jp.ossc.nimbus.service.ftp.ftpclient;

import java.util.Map;
import java.io.File;

import jp.ossc.nimbus.core.*;

/**
 * {@link FTPClientFactoryService}��MBean�C���^�t�F�[�X<p>
 * 
 * @author M.Takata
 * @see FTPClientFactoryService
 */
public interface FTPClientFactoryServiceMBean extends ServiceBaseMBean{
    
    /**
     * SO�^�C���^�E�g��ݒ肷��B<p>
     *
     * @param timeout �^�C���^�E�g[ms]
     */
    public void setSoTimeout(int timeout);
    
    /**
     * SO�^�C���^�E�g���擾����B<p>
     *
     * @return �^�C���^�E�g[ms]
     */
    public int getSoTimeout();
    
    /**
     * SO�����K�[�̒x�����Ԃ�ݒ肷��B<p>
     *
     * @param time �x������[ms]
     */
    public void setSoLinger(int time);
    
    /**
     * SO�����K�[�̒x�����Ԃ��擾����B<p>
     *
     * @return �x������[ms]
     */
    public int getSoLinger();
    
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
     * ���̑������w�肵���ꍇ�A{@link FTPClientFactoryService#createFTPClient()}�Ő�������{@link jp.ossc.nimbus.service.ftp.FTPClient FTPClient}�́A�ڑ��ςƂȂ�B<br>
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
     * �N���C�A���g�̃A�h���X��ݒ肷��B<p>
     *
     * @param addr �A�h���X
     */
    public void setBindAddress(String addr);
    
    /**
     * �N���C�A���g�̃A�h���X���擾����B<p>
     *
     * @return �A�h���X
     */
    public String getBindAddress();
    
    /**
     * �N���C�A���g�̃|�[�g�ԍ���ݒ肷��B<p>
     *
     * @param port �|�[�g�ԍ�
     */
    public void setLocalPort(int port);
    
    /**
     * �N���C�A���g�̃|�[�g�ԍ����擾����B<p>
     *
     * @return �|�[�g�ԍ�
     */
    public int getLocalPort();
    
    /**
     * ���O�C�����郆�[�U����ݒ肷��B<p>
     * ���̑������w�肵���ꍇ�A{@link FTPClientFactoryService#createFTPClient()}�Ő�������{@link jp.ossc.nimbus.service.ftp.FTPClient FTPClient}�́A���O�C���ςƂȂ�B<br>
     *
     * @param name ���[�U��
     */
    public void setUserName(String name);
    
    /**
     * ���O�C�����郆�[�U�����擾����B<p>
     *
     * @return ���[�U��
     */
    public String getUserName();
    
    /**
     * ���O�C�����郆�[�U�̃p�X���[�h��ݒ肷��B<p>
     *
     * @param password �p�X���[�h
     */
    public void setPassword(String password);
    
    /**
     * ���O�C�����郆�[�U�̃p�X���[�h���擾����B<p>
     *
     * @return �p�X���[�h
     */
    public String getPassword();
    
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
    
    /**
     * Java�̐��K�\�����g�p���邩�ǂ�����ݒ肷��B<p>
     * true�ɐݒ肵���ꍇ�A{@link jp.ossc.nimbus.service.ftp.FTPClient#mput(String) mput(String)}�A{@link jp.ossc.nimbus.service.ftp.FTPClient#mget(String) mget(String)}�A{@link jp.ossc.nimbus.service.ftp.FTPClient#mdelete(String) mdelete(String)}�ɂ�����t�@�C�����̎w��ŁAJava�̐��K�\�����g�p�ł���B<br>
     * �f�t�H���g�́Afalse�ŁA���C���h�J�[�h�w��̂ݗL���B<br>
     * 
     * @param isEnabled �g�p����ꍇ�́Atrue
     */
    public void setJavaRegexEnabled(boolean isEnabled);
    
    /**
     * Java�̐��K�\�����g�p���邩�ǂ����𔻒肷��B<p>
     * 
     * @return true�̏ꍇ�A�g�p����
     */
    public boolean isJavaRegexEnabled();
    
    /**
     * �p�b�V�uFTP���邩�ǂ�����ݒ肷��B<p>
     *
     * @param isPassive �p�b�V�uFTP�ɂ���ꍇtrue
     */
    public void setPassive(boolean isPassive);
    
    /**
     * �p�b�V�uFTP���邩�ǂ����𔻒肷��B<p>
     *
     * @return true�̏ꍇ�A�p�b�V�uFTP
     */
    public boolean isPassive();
    
    /**
     * �ڑ����̍ő僊�g���C�񐔂�ݒ肷��B<p>
     * �f�t�H���g�́A0�Ń��g���C���Ȃ��B<br>
     *
     * @param count �ő僊�g���C��
     */
    public void setConnectMaxRetryCount(int count);
    
    /**
     * �ڑ����̍ő僊�g���C�񐔂��擾����B<p>
     *
     * @return �ő僊�g���C��
     */
    public int getConnectMaxRetryCount();
    
    /**
     * {@link jp.ossc.nimbus.service.ftp.FTPClient FTPClient}�ɐݒ肷��v���p�e�B��ݒ肷��B<p>
     *
     * @param name �v���p�e�B��
     * @param value �l
     */
    public void setFTPClientProperty(String name, Object value);
    
    /**
     * {@link jp.ossc.nimbus.service.ftp.FTPClient FTPClient}�̎w�肳�ꂽ�v���p�e�B���̃v���p�e�B�l���擾����B<p>
     *
     * @param name �v���p�e�B��
     * @return �l
     */
    public Object getFTPClientProperty(String name);
    
    /**
     * {@link jp.ossc.nimbus.service.ftp.FTPClient FTPClient}�̎w�肳�ꂽ�v���p�e�B���̃v���p�e�B�l���폜����B<p>
     *
     * @param name �v���p�e�B��
     */
    public void removeFTPClientProperty(String name);
    
    /**
     * {@link jp.ossc.nimbus.service.ftp.FTPClient FTPClient}�ɐݒ肷��v���p�e�B���N���A����B<p>
     */
    public void clearFTPClientProperties();
    
    /**
     * {@link jp.ossc.nimbus.service.ftp.FTPClient FTPClient}�ɐݒ肷��v���p�e�B���擾����B<p>
     *
     * @return FTPClient�ɐݒ肷��v���p�e�B�W��
     */
    public Map getFTPClientProperties();
}