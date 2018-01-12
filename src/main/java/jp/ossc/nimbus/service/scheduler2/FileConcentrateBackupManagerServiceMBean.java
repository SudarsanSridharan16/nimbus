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
package jp.ossc.nimbus.service.scheduler2;

import java.io.File;
import java.util.Date;

import jp.ossc.nimbus.core.ServiceBaseMBean;

/**
 * {@link FileConcentrateBackupManagerService}��MBean�C���^�t�F�[�X�B<p>
 * 
 * @author M.Takata
 */
public interface FileConcentrateBackupManagerServiceMBean extends ServiceBaseMBean{
    
    /**
     * �f�t�H���g�̓��t�f�B���N�g���t�H�[�}�b�g�B<p>
     */
    public static final String DEFAULT_DATE_FORMAT = "yyyyMMdd";
    
    /**
     * ���k���[�h�F�񈳏k�B<p>
     */
    public static final int COMPRESS_MODE_NONE = 0;
    
    /**
     * ���k���[�h�FZLIB�`���B<p>
     */
    public static final int COMPRESS_MODE_ZLIB = 1;
    
    /**
     * ���k���[�h�FZIP�`���B<p>
     */
    public static final int COMPRESS_MODE_ZIP = 2;
    
    /**
     * ���k���[�h�FGZIP�`���B<p>
     */
    public static final int COMPRESS_MODE_GZIP = 3;
    
    /**
     * ���t�f�B���N�g���̃t�H�[�}�b�g��ݒ肷��B<p>
     * �f�t�H���g�́A{@link #DEFAULT_DATE_FORMAT}�B<br>
     * 
     * @param format �t�H�[�}�b�g
     */
    public void setDateFormat(String format);
    
    /**
     * ���t�f�B���N�g���̃t�H�[�}�b�g���擾����B<p>
     * 
     * @return �t�H�[�}�b�g
     */
    public String getDateFormat();
    
    /**
     * �o�b�N�A�b�v�f�B���N�g����ݒ肷��B<p>
     * �f�t�H���g�́A"backup"�B<br>
     *
     * @param dir �o�b�N�A�b�v�f�B���N�g��
     */
    public void setBackupDirectory(File dir);
    
    /**
     * �o�b�N�A�b�v�f�B���N�g�����擾����B<p>
     *
     * @return �o�b�N�A�b�v�f�B���N�g��
     */
    public File getBackupDirectory();
    
    /**
     * �o�b�N�A�b�v���̓ǂݍ��݃X�g���[���̃o�b�t�@�T�C�Y��ݒ肷��B<p>
     * �f�t�H���g�́A1024�B<br>
     *
     * @param size �o�b�t�@�T�C�Y
     */
    public void setBufferSize(int size);
    
    /**
     * �o�b�N�A�b�v���̓ǂݍ��݃X�g���[���̃o�b�t�@�T�C�Y���擾����B<p>
     *
     * @return �o�b�t�@�T�C�Y
     */
    public int getBufferSize();
    
    /**
     * ���k���[�h��ݒ肷��B<p>
     * �f�t�H���g�́A{@link #COMPRESS_MODE_NONE �񈳏k}�B<br>
     * 
     * @param mode ���k���[�h
     * @see #COMPRESS_MODE_NONE
     * @see #COMPRESS_MODE_ZLIB
     * @see #COMPRESS_MODE_ZIP
     * @see #COMPRESS_MODE_GZIP
     */
    public void setCompressMode(int mode);
    
    /**
     * ���k���[�h���擾����B<p>
     * 
     * @return ���k���[�h
     */
    public int getCompressMode();
    
    /**
     * ���k���x����ݒ肷��B<p>
     * �f�t�H���g�́A{@link java.util.zip.Deflater#DEFAULT_COMPRESSION}�B<br>
     * ���k���[�h���A{@link #COMPRESS_MODE_ZLIB}�A{@link #COMPRESS_MODE_ZIP}�̏ꍇ�A�L���B<br>
     * 
     * @param level ���k���x��
     */
    public void setCompressLevel(int level);
    
    /**
     * ���k���x�����擾����B<p>
     * 
     * @return ���k���x��
     */
    public int getCompressLevel();
    
    /**
     * ���k���\�b�h��ݒ肷��B<p>
     * �f�t�H���g�́A{@link java.util.zip.ZipOutputStream#DEFLATED}�B<br>
     * ���k���[�h���A{@link #COMPRESS_MODE_ZIP}�̏ꍇ�̂݁A�L���B<br>
     * 
     * @param method ���k���\�b�h
     */
    public void setCompressMethod(int method);
    
    /**
     * ���k���\�b�h���擾����B<p>
     * 
     * @return ���k���\�b�h
     */
    public int getCompressMethod();
    
    /**
     * �o�b�N�A�b�v��S�č폜����B<p>
     *
     * @return �폜�ł����ꍇ�́Atrue
     * @exception ConcentrateBackupException �o�b�N�A�b�v�폜���Ɉُ킪���������ꍇ
     */
    public boolean clear() throws ConcentrateBackupException;
    
    /**
     * �w�肵���o�b�N�A�b�v�O���[�v�̃o�b�N�A�b�v��S�č폜����B<p>
     *
     * @param group �o�b�N�A�b�v�O���[�v��
     * @return �폜�ł����ꍇ�́Atrue
     * @exception ConcentrateBackupException �o�b�N�A�b�v�폜���Ɉُ킪���������ꍇ
     */
    public boolean remove(String group) throws ConcentrateBackupException;
    
    /**
     * �w�肵���o�b�N�A�b�v���t�̃o�b�N�A�b�v��S�č폜����B<p>
     *
     * @param date �o�b�N�A�b�v���t
     * @return �폜�ł����ꍇ�́Atrue
     * @exception ConcentrateBackupException �o�b�N�A�b�v�폜���Ɉُ킪���������ꍇ
     */
    public boolean remove(Date date) throws ConcentrateBackupException;
    
    /**
     * �w�肵���o�b�N�A�b�v�O���[�v���o�b�N�A�b�v���t�̃o�b�N�A�b�v��S�č폜����B<p>
     *
     * @param group �o�b�N�A�b�v�O���[�v��
     * @param date �o�b�N�A�b�v���t
     * @return �폜�ł����ꍇ�́Atrue
     * @exception ConcentrateBackupException �o�b�N�A�b�v�폜���Ɉُ킪���������ꍇ
     */
    public boolean remove(String group, Date date) throws ConcentrateBackupException;
    
    /**
     * �w�肵���o�b�N�A�b�v���t�܂ł̃o�b�N�A�b�v��S�č폜����B<p>
     *
     * @param date �o�b�N�A�b�v���t
     * @return �폜�ł����ꍇ�́Atrue
     * @exception ConcentrateBackupException �o�b�N�A�b�v�폜���Ɉُ킪���������ꍇ
     */
    public boolean removeTo(Date date) throws ConcentrateBackupException;
    
    /**
     * �w�肵���o�b�N�A�b�v�O���[�v���o�b�N�A�b�v���t�܂ł̃o�b�N�A�b�v��S�č폜����B<p>
     *
     * @param group �o�b�N�A�b�v�O���[�v��
     * @param date �o�b�N�A�b�v���t
     * @return �폜�ł����ꍇ�́Atrue
     * @exception ConcentrateBackupException �o�b�N�A�b�v�폜���Ɉُ킪���������ꍇ
     */
    public boolean removeTo(String group, Date date) throws ConcentrateBackupException;
    
    /**
     * �w�肵���o�b�N�A�b�v�O���[�v�A�o�b�N�A�b�v���t�A�o�b�N�A�b�v�L�[�̃o�b�N�A�b�v��S�č폜����B<p>
     *
     * @param group �o�b�N�A�b�v�O���[�v��
     * @param date �o�b�N�A�b�v���t
     * @param key �o�b�N�A�b�v�L�[
     * @return �폜�ł����ꍇ�́Atrue
     * @exception ConcentrateBackupException �o�b�N�A�b�v�폜���Ɉُ킪���������ꍇ
     */
    public boolean remove(String group, Date date, String key) throws ConcentrateBackupException;
}