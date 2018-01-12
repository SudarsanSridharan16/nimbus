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
package jp.ossc.nimbus.service.scp;

import java.io.File;

/**
 * SCP�N���C�A���g�B<p>
 *
 * @author M.Takata
 */
public interface SCPClient{
    
    /**
     * �T�[�o�ɐڑ�����B<p>
     *
     * @param user ���[�U��
     * @param host �ڑ���T�[�o�̃z�X�g��
     * @param password �p�X���[�h
     * @exception SCPException �T�[�o�Ƃ̐ڑ��Ɏ��s�����ꍇ
     */
    public void connect(String user, String host, String password) throws SCPException;
    
    /**
     * �T�[�o�ɐڑ�����B<p>
     *
     * @param user ���[�U��
     * @param host �ڑ���T�[�o�̃z�X�g��
     * @param port �ڑ���T�[�o�̃|�[�g�ԍ�
     * @param password �p�X���[�h
     * @exception SCPException �T�[�o�Ƃ̐ڑ��Ɏ��s�����ꍇ
     */
    public void connect(String user, String host, int port, String password) throws SCPException;
    
    /**
     * �T�[�o�ɐڑ�����B<p>
     *
     * @param user ���[�U��
     * @param host �ڑ���T�[�o�̃z�X�g��
     * @param pemFile �閧���t�@�C��
     * @param passphrase �p�X�t���[�Y
     * @exception SCPException �T�[�o�Ƃ̐ڑ��Ɏ��s�����ꍇ
     */
    public void connect(String user, String host, File pemFile, String passphrase) throws SCPException;
    
    /**
     * �T�[�o�ɐڑ�����B<p>
     *
     * @param user ���[�U��
     * @param host �ڑ���T�[�o�̃z�X�g��
     * @param port �ڑ���T�[�o�̃|�[�g�ԍ�
     * @param pemFile �閧���t�@�C��
     * @param passphrase �p�X�t���[�Y
     * @exception SCPException �T�[�o�Ƃ̐ڑ��Ɏ��s�����ꍇ
     */
    public void connect(String user, String host, int port, File pemFile, String passphrase) throws SCPException;
    
    /**
     * �T�[�o���̎w�肳�ꂽ�t�@�C�����擾����B<p>
     * 
     * @param remote �擾����t�@�C���̃p�X
     * @return �擾�����t�@�C��
     * @exception SCPException �擾�Ɏ��s�����ꍇ
     */
    public File get(String remote) throws SCPException;
    
    /**
     * �T�[�o���̎w�肳�ꂽ�t�@�C�����A�w�肳�ꂽ���O�̃t�@�C���Ƃ��Ď擾����B<p>
     * 
     * @param remote �擾����t�@�C���̃p�X
     * @param local �擾��̃t�@�C����
     * @return �擾�����t�@�C��
     * @exception SCPException �擾�Ɏ��s�����ꍇ
     */
    public File get(String remote, String local) throws SCPException;
    
    /**
     * �T�[�o���̎w�肳�ꂽ�����̃t�@�C�����擾����B<p>
     * 
     * @param remote �擾����t�@�C���̃p�X
     * @return �擾�����t�@�C��
     * @exception SCPException �擾�Ɏ��s�����ꍇ
     */
    public File[] mget(String remote) throws SCPException;
    
    /**
     * �T�[�o���̎w�肳�ꂽ�����̃t�@�C�����擾����B<p>
     * 
     * @param remote �擾����t�@�C���̃p�X
     * @param localDir �擾��̃f�B���N�g����
     * @return �擾�����t�@�C��
     * @exception SCPException �擾�Ɏ��s�����ꍇ
     */
    public File[] mget(String remote, String localDir) throws SCPException;
    
    /**
     * �T�[�o���Ɏw�肳�ꂽ�t�@�C����]������B<p>
     * 
     * @param local �]������t�@�C���̃p�X
     * @exception SCPException �]���Ɏ��s�����ꍇ
     */
    public void put(String local) throws SCPException;
    
    /**
     * �T�[�o���Ɏw�肳�ꂽ�t�@�C�����A�w�肳�ꂽ�t�@�C�����œ]������B<p>
     * 
     * @param local �]������t�@�C���̃p�X
     * @param remote �]����ł̃t�@�C����
     * @exception SCPException �]���Ɏ��s�����ꍇ
     */
    public void put(String local, String remote) throws SCPException;
    
    /**
     * �T�[�o���Ɏw�肳�ꂽ�t�@�C�����A�w�肳�ꂽ�t�@�C�����œ]������B<p>
     * 
     * @param local �]������t�@�C���̃p�X
     * @param remote �]����ł̃t�@�C����
     * @param mode �]����ł̃t�@�C���̌����B�����S��
     * @exception SCPException �]���Ɏ��s�����ꍇ
     */
    public void put(String local, String remote, String mode) throws SCPException;
    
    /**
     * �T�[�o���Ɏw�肳�ꂽ�S�Ẵt�@�C����]������B<p>
     * 
     * @param local �]������t�@�C���̃p�X
     * @exception SCPException �]���Ɏ��s�����ꍇ
     */
    public void mput(String local) throws SCPException;
    
    /**
     * �T�[�o���Ɏw�肳�ꂽ�t�@�C�����A�w�肳�ꂽ�t�@�C�����œ]������B<p>
     * 
     * @param local �]������t�@�C���̃p�X
     * @param remoteDir �]����̃f�B���N�g����
     * @exception SCPException �]���Ɏ��s�����ꍇ
     */
    public void mput(String local, String remoteDir) throws SCPException;
    
    /**
     * �T�[�o���Ɏw�肳�ꂽ�t�@�C�����A�w�肳�ꂽ�t�@�C�����œ]������B<p>
     * 
     * @param local �]������t�@�C���̃p�X
     * @param remoteDir �]����̃f�B���N�g����
     * @param mode �]����ł̃t�@�C���̌����B�����S��
     * @exception SCPException �]���Ɏ��s�����ꍇ
     */
    public void mput(String local, String remoteDir, String mode) throws SCPException;
    
    /**
     * �T�[�o�Ƃ̐ڑ���ؒf����B<p>
     * 
     * @exception SCPException �ؒf�Ɏ��s�����ꍇ
     */
    public void close() throws SCPException;
}