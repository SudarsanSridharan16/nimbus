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
package jp.ossc.nimbus.service.ftp;

import java.io.File;

/**
 * FTP�N���C�A���g�B<p>
 *
 * @author M.Takata
 */
public interface FTPClient{
    
    /**
     * �T�[�o�ɐڑ�����B<p>
     *
     * @param host �ڑ���T�[�o�̃z�X�g��
     * @exception FTPException �T�[�o�Ƃ̐ڑ��Ɏ��s�����ꍇ
     */
    public void connect(String host) throws FTPException;
    
    /**
     * �T�[�o�ɐڑ�����B<p>
     *
     * @param host �ڑ���T�[�o�̃z�X�g��
     * @param port �ڑ���T�[�o�̃|�[�g�ԍ�
     * @exception FTPException �T�[�o�Ƃ̐ڑ��Ɏ��s�����ꍇ
     */
    public void connect(String host, int port) throws FTPException;
    
    /**
     * �T�[�o�ɐڑ�����B<p>
     *
     * @param host �ڑ���T�[�o�̃z�X�g��
     * @param port �ڑ���T�[�o�̃|�[�g�ԍ�
     * @param localAddr �N���C�A���g�̃A�h���X
     * @param localPort �N���C�A���g�̃|�[�g�ԍ�
     * @exception FTPException �T�[�o�Ƃ̐ڑ��Ɏ��s�����ꍇ
     */
    public void connect(
        String host,
        int port,
        String localAddr,
        int localPort
    ) throws FTPException;
    
    /**
     * ���O�C������B<p>
     *
     * @param user ���[�U��
     * @param password �p�X���[�h
     * @exception FTPException ���O�C���Ɏ��s�����ꍇ
     */
    public void login(String user, String password) throws FTPException;
    
    /**
     * ���O�A�E�g����B<p>
     * 
     * @exception FTPException ���O�A�E�g�Ɏ��s�����ꍇ
     */
    public void logout() throws FTPException;
    
    /**
     * �T�[�o�̃t�@�C�����ꗗ���擾����B<p>
     * 
     * @return �t�@�C�����̔z��
     * @exception FTPException �擾�Ɏ��s�����ꍇ
     */
    public String[] ls() throws FTPException;
    
    /**
     * �T�[�o�̎w�肳�ꂽ�f�B���N�g�����̃t�@�C�����ꗗ���擾����B<p>
     * 
     * @return �t�@�C�����̔z��
     * @exception FTPException �擾�Ɏ��s�����ꍇ
     */
    public String[] ls(String path) throws FTPException;
    
    /**
     * �T�[�o���ł̃J�����g�f�B���N�g�����擾����B<p>
     * 
     * @return �J�����g�f�B���N�g���̃p�X
     * @exception FTPException �擾�Ɏ��s�����ꍇ
     */
    public String pwd() throws FTPException;
    
    /**
     * �N���C�A���g���ł̃J�����g�f�B���N�g�����擾����B<p>
     * 
     * @return �J�����g�f�B���N�g��
     * @exception FTPException �擾�Ɏ��s�����ꍇ
     */
    public File lpwd() throws FTPException;
    
    /**
     * �T�[�o���ł̃J�����g�f�B���N�g�����w�肳�ꂽ�p�X�Ɉړ�����B<p>
     * 
     * @param path �ړ���̃p�X
     * @exception FTPException �ړ��Ɏ��s�����ꍇ
     */
    public void cd(String path) throws FTPException;
    
    /**
     * �N���C�A���g���ł̃J�����g�f�B���N�g�����w�肳�ꂽ�p�X�Ɉړ�����B<p>
     * 
     * @param path �ړ���̃p�X
     * @exception FTPException �ړ��Ɏ��s�����ꍇ
     */
    public void lcd(String path) throws FTPException;
    
    /**
     * �T�[�o���Ŏw�肳�ꂽ�f�B���N�g�����쐬����B<p>
     * 
     * @param dir �쐬����f�B���N�g���̃p�X
     * @exception FTPException �쐬�Ɏ��s�����ꍇ
     */
    public void mkdir(String dir) throws FTPException;
    
    /**
     * �T�[�o���Ŏw�肳�ꂽ�t�@�C���̃t�@�C������ύX����B<p>
     * 
     * @param from �ύX�Ώۂ̃t�@�C���̃p�X
     * @param to �ύX��̃t�@�C����
     * @exception FTPException �ύX�Ɏ��s�����ꍇ
     */
    public void rename(String from, String to) throws FTPException;
    
    /**
     * �T�[�o���̎w�肳�ꂽ�t�@�C�����擾����B<p>
     * 
     * @param path �擾����t�@�C���̃p�X
     * @return �擾�����t�@�C��
     * @exception FTPException �擾�Ɏ��s�����ꍇ
     */
    public File get(String path) throws FTPException;
    
    /**
     * �T�[�o���̎w�肳�ꂽ�t�@�C�����A�w�肳�ꂽ���O�̃t�@�C���Ƃ��Ď擾����B<p>
     * 
     * @param remote �擾����t�@�C���̃p�X
     * @param local �擾��̃t�@�C����
     * @return �擾�����t�@�C��
     * @exception FTPException �擾�Ɏ��s�����ꍇ
     */
    public File get(String remote, String local) throws FTPException;
    
    /**
     * �T�[�o���̎w�肳�ꂽ�S�Ẵt�@�C�����擾����B<p>
     * 
     * @param path �擾����t�@�C���̃p�X
     * @return �擾�����t�@�C���z��
     * @exception FTPException �擾�Ɏ��s�����ꍇ
     */
    public File[] mget(String path) throws FTPException;
    
    /**
     * �T�[�o���Ɏw�肳�ꂽ�t�@�C����]������B<p>
     * 
     * @param path �]������t�@�C���̃p�X
     * @exception FTPException �]���Ɏ��s�����ꍇ
     */
    public void put(String path) throws FTPException;
    
    /**
     * �T�[�o���Ɏw�肳�ꂽ�t�@�C�����A�w�肳�ꂽ�t�@�C�����œ]������B<p>
     * 
     * @param local �]������t�@�C���̃p�X
     * @param remote �]����ł̃t�@�C����
     * @exception FTPException �]���Ɏ��s�����ꍇ
     */
    public void put(String local, String remote) throws FTPException;
    
    /**
     * �T�[�o���Ɏw�肳�ꂽ�S�Ẵt�@�C����]������B<p>
     * 
     * @param path �]������t�@�C���̃p�X
     * @exception FTPException �]���Ɏ��s�����ꍇ
     */
    public void mput(String path) throws FTPException;
    
    /**
     * �T�[�o���̎w�肳�ꂽ�t�@�C�����폜����B<p>
     * 
     * @param path �폜����t�@�C���̃p�X
     * @exception FTPException �폜�Ɏ��s�����ꍇ
     */
    public void delete(String path) throws FTPException;
    
    /**
     * �T�[�o���̎w�肳�ꂽ�S�Ẵt�@�C�����폜����B<p>
     * 
     * @param path �폜����t�@�C���̃p�X
     * @exception FTPException �폜�Ɏ��s�����ꍇ
     */
    public void mdelete(String path) throws FTPException;
    
    /**
     * �]�����[�h��ASCII�ɕύX����B<p>
     * 
     * @exception FTPException �ύX�Ɏ��s�����ꍇ
     */
    public void ascii() throws FTPException;
    
    /**
     * �]�����[�h���o�C�i���ɕύX����B<p>
     * 
     * @exception FTPException �ύX�Ɏ��s�����ꍇ
     */
    public void binary() throws FTPException;
    
    /**
     * �]�����[�h���w�肳�ꂽ���[�h�ɕύX����B<p>
     * �����Ŏw�肷��]�����[�h�́A�����ˑ��B<br>
     *
     * @param type �]�����[�h
     * @exception FTPException �ύX�Ɏ��s�����ꍇ
     */
    public void setTransferType(int type) throws FTPException;
    
    /**
     * ���݂̓]�����[�h���擾����B<p>
     *
     * @return �]�����[�h
     * @exception FTPException �擾�Ɏ��s�����ꍇ
     */
    public int getTransferType() throws FTPException;
    
    /**
     * �A�N�e�B�uFTP�ɐ؂�ւ���B<p>
     * 
     * @exception FTPException �ύX�Ɏ��s�����ꍇ
     */
    public void active() throws FTPException;
    
    /**
     * �p�b�V�uFTP�ɐ؂�ւ���B<p>
     * 
     * @exception FTPException �ύX�Ɏ��s�����ꍇ
     */
    public void passive() throws FTPException;
    
    /**
     * �T�[�o�Ƃ̐ڑ���ؒf����B<p>
     * 
     * @exception FTPException �ؒf�Ɏ��s�����ꍇ
     */
    public void close() throws FTPException;
}