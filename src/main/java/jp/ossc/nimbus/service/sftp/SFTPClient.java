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
package jp.ossc.nimbus.service.sftp;

import java.io.File;
import java.util.Date;

/**
 * SFTP�N���C�A���g�B<p>
 *
 * @author M.Takata
 */
public interface SFTPClient{
    
    /**
     * �T�[�o�ɐڑ�����B<p>
     *
     * @param user ���[�U��
     * @param host �ڑ���T�[�o�̃z�X�g��
     * @param password �p�X���[�h
     * @exception SFTPException �T�[�o�Ƃ̐ڑ��Ɏ��s�����ꍇ
     */
    public void connect(String user, String host, String password) throws SFTPException;
    
    /**
     * �T�[�o�ɐڑ�����B<p>
     *
     * @param user ���[�U��
     * @param host �ڑ���T�[�o�̃z�X�g��
     * @param port �ڑ���T�[�o�̃|�[�g�ԍ�
     * @param password �p�X���[�h
     * @exception SFTPException �T�[�o�Ƃ̐ڑ��Ɏ��s�����ꍇ
     */
    public void connect(String user, String host, int port, String password) throws SFTPException;
    
    /**
     * �T�[�o�ɐڑ�����B<p>
     *
     * @param user ���[�U��
     * @param host �ڑ���T�[�o�̃z�X�g��
     * @param pemFile �閧���t�@�C��
     * @param passphrase �p�X�t���[�Y
     * @exception SFTPException �T�[�o�Ƃ̐ڑ��Ɏ��s�����ꍇ
     */
    public void connect(String user, String host, File pemFile, String passphrase) throws SFTPException;
    
    /**
     * �T�[�o�ɐڑ�����B<p>
     *
     * @param user ���[�U��
     * @param host �ڑ���T�[�o�̃z�X�g��
     * @param port �ڑ���T�[�o�̃|�[�g�ԍ�
     * @param pemFile �閧���t�@�C��
     * @param passphrase �p�X�t���[�Y
     * @exception SFTPException �T�[�o�Ƃ̐ڑ��Ɏ��s�����ꍇ
     */
    public void connect(String user, String host, int port, File pemFile, String passphrase) throws SFTPException;
    
    /**
     * �T�[�o�̃t�@�C�����ꗗ���擾����B<p>
     * 
     * @return �t�@�C�����̔z��
     * @exception SFTPException �擾�Ɏ��s�����ꍇ
     */
    public String[] ls() throws SFTPException;
    
    /**
     * �T�[�o�̎w�肳�ꂽ�f�B���N�g�����̃t�@�C�����ꗗ���擾����B<p>
     * 
     * @return �t�@�C�����̔z��
     * @exception SFTPException �擾�Ɏ��s�����ꍇ
     */
    public String[] ls(String path) throws SFTPException;
    
    /**
     * �T�[�o�̃t�@�C�����ꗗ���擾����B<p>
     * 
     * @return SFTP�t�@�C���̔z��
     * @exception SFTPException �擾�Ɏ��s�����ꍇ
     */
    public SFTPFile[] lsFile() throws SFTPException;
    
    /**
     * �T�[�o�̎w�肳�ꂽ�f�B���N�g�����̃t�@�C�����ꗗ���擾����B<p>
     * 
     * @return SFTP�t�@�C���̔z��
     * @exception SFTPException �擾�Ɏ��s�����ꍇ
     */
    public SFTPFile[] lsFile(String path) throws SFTPException;
    
    /**
     * �T�[�o���ł̃J�����g�f�B���N�g�����擾����B<p>
     * 
     * @return �J�����g�f�B���N�g���̃p�X
     * @exception SFTPException �擾�Ɏ��s�����ꍇ
     */
    public String pwd() throws SFTPException;
    
    /**
     * �N���C�A���g���ł̃J�����g�f�B���N�g�����擾����B<p>
     * 
     * @return �J�����g�f�B���N�g��
     * @exception SFTPException �擾�Ɏ��s�����ꍇ
     */
    public File lpwd() throws SFTPException;
    
    /**
     * �T�[�o���ł̃J�����g�f�B���N�g�����w�肳�ꂽ�p�X�Ɉړ�����B<p>
     * 
     * @param path �ړ���̃p�X
     * @exception SFTPException �ړ��Ɏ��s�����ꍇ
     */
    public void cd(String path) throws SFTPException;
    
    /**
     * �N���C�A���g���ł̃J�����g�f�B���N�g�����w�肳�ꂽ�p�X�Ɉړ�����B<p>
     * 
     * @param path �ړ���̃p�X
     * @exception SFTPException �ړ��Ɏ��s�����ꍇ
     */
    public void lcd(String path) throws SFTPException;
    
    /**
     * �T�[�o���Ŏw�肳�ꂽ�f�B���N�g�����쐬����B<p>
     * 
     * @param dir �쐬����f�B���N�g���̃p�X
     * @exception SFTPException �쐬�Ɏ��s�����ꍇ
     */
    public void mkdir(String dir) throws SFTPException;
    
    /**
     * �T�[�o���Ŏw�肳�ꂽ�t�@�C���̃t�@�C������ύX����B<p>
     * 
     * @param from �ύX�Ώۂ̃t�@�C���̃p�X
     * @param to �ύX��̃t�@�C����
     * @exception SFTPException �ύX�Ɏ��s�����ꍇ
     */
    public void rename(String from, String to) throws SFTPException;
    
    /**
     * �T�[�o���̎w�肳�ꂽ�t�@�C�����擾����B<p>
     * 
     * @param path �擾����t�@�C���̃p�X
     * @return �擾�����t�@�C��
     * @exception SFTPException �擾�Ɏ��s�����ꍇ
     */
    public File get(String path) throws SFTPException;
    
    /**
     * �T�[�o���̎w�肳�ꂽ�t�@�C�����A�w�肳�ꂽ���O�̃t�@�C���Ƃ��Ď擾����B<p>
     * 
     * @param remote �擾����t�@�C���̃p�X
     * @param local �擾��̃t�@�C����
     * @return �擾�����t�@�C��
     * @exception SFTPException �擾�Ɏ��s�����ꍇ
     */
    public File get(String remote, String local) throws SFTPException;
    
    /**
     * �T�[�o���̎w�肳�ꂽ�S�Ẵt�@�C�����擾����B<p>
     * 
     * @param path �擾����t�@�C���̃p�X
     * @return �擾�����t�@�C���z��
     * @exception SFTPException �擾�Ɏ��s�����ꍇ
     */
    public File[] mget(String path) throws SFTPException;
    
    /**
     * �T�[�o���Ɏw�肳�ꂽ�t�@�C����]������B<p>
     * 
     * @param path �]������t�@�C���̃p�X
     * @exception SFTPException �]���Ɏ��s�����ꍇ
     */
    public void put(String path) throws SFTPException;
    
    /**
     * �T�[�o���Ɏw�肳�ꂽ�t�@�C�����A�w�肳�ꂽ�t�@�C�����œ]������B<p>
     * 
     * @param local �]������t�@�C���̃p�X
     * @param remote �]����ł̃t�@�C����
     * @exception SFTPException �]���Ɏ��s�����ꍇ
     */
    public void put(String local, String remote) throws SFTPException;
    
    /**
     * �T�[�o���Ɏw�肳�ꂽ�S�Ẵt�@�C����]������B<p>
     * 
     * @param path �]������t�@�C���̃p�X
     * @exception SFTPException �]���Ɏ��s�����ꍇ
     */
    public void mput(String path) throws SFTPException;
    
    /**
     * �T�[�o���̎w�肳�ꂽ�t�@�C�����폜����B<p>
     * 
     * @param path �폜����t�@�C���̃p�X
     * @return �폜�����ꍇtrue
     * @exception SFTPException �폜�Ɏ��s�����ꍇ
     */
    public boolean rm(String path) throws SFTPException;
    
    /**
     * �T�[�o���̎w�肳�ꂽ�f�B���N�g�����폜����B<p>
     * 
     * @param path �폜����f�B���N�g���̃p�X
     * @return �폜�����ꍇtrue
     * @exception SFTPException �폜�Ɏ��s�����ꍇ
     */
    public boolean rmdir(String path) throws SFTPException;
    
    /**
     * �T�[�o���̎w�肳�ꂽ�t�@�C���̃p�[�~�b�V������ύX����B<p>
     *
     * @param mode �p�[�~�b�V����
     * @param path �ύX����t�@�C���̃p�X
     * @exception SFTPException �ύX�Ɏ��s�����ꍇ
     */
    public void chmod(String mode, String path) throws SFTPException;
    
    /**
     * �T�[�o���̎w�肳�ꂽ�t�@�C���̏��L�҂�ύX����B<p>
     *
     * @param uid ���[�UID
     * @param path �ύX����t�@�C���̃p�X
     * @exception SFTPException �ύX�Ɏ��s�����ꍇ
     */
    public void chown(String uid, String path) throws SFTPException;
    
    /**
     * �T�[�o���̎w�肳�ꂽ�t�@�C���̃O���[�v��ύX����B<p>
     *
     * @param gid �O���[�vID
     * @param path �ύX����t�@�C���̃p�X
     * @exception SFTPException �ύX�Ɏ��s�����ꍇ
     */
    public void chgrp(String gid, String path) throws SFTPException;
    
    /**
     * �T�[�o���̎w�肳�ꂽ�t�@�C���̃V���{���b�N�����N���쐬����B<p>
     *
     * @param path �Ώۂ̃t�@�C���̃p�X
     * @param link �쐬����V���{���b�N�����N�̃p�X
     * @exception SFTPException �쐬�Ɏ��s�����ꍇ
     */
    public void symlink(String path, String link) throws SFTPException;
    
    /**
     * �T�[�o���̎w�肳�ꂽ�t�@�C���̃n�[�h�����N���쐬����B<p>
     *
     * @param path �Ώۂ̃t�@�C���̃p�X
     * @param link �쐬����n�[�h�����N�̃p�X
     * @exception SFTPException �쐬�Ɏ��s�����ꍇ
     */
    public void ln(String path, String link) throws SFTPException;
    
    /**
     * �T�[�o�Ƃ̐ڑ���ؒf����B<p>
     * 
     * @exception SFTPException �ؒf�Ɏ��s�����ꍇ
     */
    public void close() throws SFTPException;
    
    /**
     * SFTP�t�@�C���B<p>
     *
     * @author M.Takata
     */
    public interface SFTPFile{
        
        /**
         * �t�@�C�������擾����B<p>
         *
         * @return �t�@�C����
         */
        public String getName();
        
        /**
         * ���[�UID���擾����B<p>
         *
         * @return ���[�UID
         */
        public int getUserId();
        
        /**
         * �O���[�vID���擾����B<p>
         *
         * @return �O���[�vID
         */
        public int getGroupId();
        
        /**
         * �������擾����B<p>
         *
         * @return ����
         */
        public int getPermissions();
        
        /**
         * �ŏI�A�N�Z�X�������擾����B<p>
         *
         * @return �ŏI�A�N�Z�X����
         */
        public Date getLastAccessTime();
        
        /**
         * �ŏI�X�V�������擾����B<p>
         *
         * @return �ŏI�X�V����
         */
        public Date getLastModificationTime();
        
        /**
         * �f�B���N�g�����ǂ����𔻒肷��B<p>
         *
         * @return �f�B���N�g���̏ꍇtrue
         */
        public boolean isDirectory();
        
        /**
         * �����N���ǂ����𔻒肷��B<p>
         *
         * @return �����N�̏ꍇtrue
         */
        public boolean isLink();
        
        /**
         * �t�@�C���T�C�Y���擾����B<p>
         *
         * @return �t�@�C���T�C�Y
         */
        public long size();
    }
}