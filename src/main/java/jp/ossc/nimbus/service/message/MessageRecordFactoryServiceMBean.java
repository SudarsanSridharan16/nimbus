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
package jp.ossc.nimbus.service.message;

import java.util.*;
import jp.ossc.nimbus.core.*;

/**
 * {@link MessageRecordFactoryService}�T�[�r�XMBean�C���^�t�F�[�X�B<p>
 *
 * @author H.Nakano
 */
public interface MessageRecordFactoryServiceMBean extends ServiceBaseMBean{
    
    /**
     * {@link MessageRecord}�C���^�t�F�[�X�̎����N���X����ݒ肷��B<p>
     * �f�t�H���g�́A{@link MessageRecordImpl}�B<br>
     * 
     * @param className MessageRecord�C���^�t�F�[�X�̎����N���X��
     */
    public void setMessageRecordClassName(String className);
    
    /**
     * {@link MessageRecord}�C���^�t�F�[�X�̎����N���X�����擾����B<p>
     * 
     * @return MessageRecord�C���^�t�F�[�X�̎����N���X��
     */
    public String getMessageRecordClassName();
    
    /**
     * �閧���ߍ��݃��b�Z�[�W��閧�����Ń}�X�N���邩�ǂ�����ݒ肷��B<p>
     * �f�t�H���g�́Afalse�B<br>
     * 
     * @param flg �閧�����Ń}�X�N����ꍇtrue
     */
    public void setSecretMode(boolean flg);
    
    /**
     * �閧���ߍ��݃��b�Z�[�W��閧�����Ń}�X�N���邩�ǂ����𔻒肷��B<p>
     * 
     * @return true�̏ꍇ�A�閧�����Ń}�X�N����
     */
    public boolean isSecretMode();
    
    /**
     * �閧������ݒ肷��B<p>
     * �f�t�H���g�́Anull�ŁA���b�Z�[�W��`�̂܂܂ŏo�͂����B<br>
     * 
     * @param secret �閧����
     */
    public void setSecretString(String secret);
    
    /**
     * �閧�������擾����B<p>
     * 
     * @return �閧����
     */
    public String getSecretString();
    
    /**
     * ���b�Z�[�W��`�t�@�C���̔z�u�f�B���N�g����ݒ肷��B<p>
     * �w�肵���f�B���N�g���̒����ɁA�f�t�H���g���P�[���p�̃��b�Z�[�W��`�t�@�C����z�u����B<br>
     * ���ۉ��Ή�����ꍇ�́A�w�肵���f�B���N�g���̒����Ƀ��P�[�����̃f�B���N�g�����쐬���āA���̔z���ɑΉ����郁�b�Z�[�W��`�t�@�C����z�u����B<br>
     *
     * @param dirPaths �f�B���N�g���p�X�z��
     */
    public void setMessageDirPaths(String[] dirPaths);
    
    /**
     * ���b�Z�[�W��`�t�@�C���̔z�u�f�B���N�g�����擾����B<p>
     *
     * @return �f�B���N�g���p�X�z��
     */
    public String[] getMessageDirPaths();
    
    /**
     * ���b�Z�[�W��`�t�@�C���̃N���X�p�X���̃��\�[�X�p�X��ݒ肷��B<p>
     * �N���X�p�X�Ƀ��b�Z�[�W��`�t�@�C����z�u����B<br>
     * ���ۉ��Ή�����ꍇ�́A�g���q�̑O�Ƀ��P�[������t�^����B�i�v���p�e�B�t�@�C���Ɠ��l�j<br>
     * �f�t�H���g�ŁAjp.ossc.nimbus.resource.Nimbus��K���܂ށB<br>
     * 
     * @param paths ���b�Z�[�W��`�t�@�C���̃N���X�p�X���̃��\�[�X�p�X�z��
     */
    public void setMessageFiles(String[] paths);
    
    /**
     * ���b�Z�[�W��`�t�@�C���̃N���X�p�X���̃��\�[�X�p�X���擾����B<p>
     * 
     * @return ���b�Z�[�W��`�t�@�C���̃N���X�p�X���̃��\�[�X�p�X�z��
     */
    public String[] getMessageFiles();
    
    /**
     * ���b�Z�[�W��`�̃t�@�C���g���q��ݒ肷��B<p>
     * �f�t�H���g�́A"def"�B<br>
     * 
     * @param name �g���q������iex "hogeho")
     */
    public void setExtentionOfMessageFile(String name);
    
    /**
     * ���b�Z�[�W��`�̃t�@�C���g���q���擾����B<p>
     * 
     * @return �g���q������
     */
    public String getExtentionOfMessageFile();
    
    /**
     * �����ǂݍ��݂��郁�b�Z�[�W��`�t�@�C���̃��P�[����ݒ肷��B<p>
     * �����Őݒ肵�Ȃ����P�[���̃��b�Z�[�W��`�t�@�C���́A���߂Ă��̃��P�[���̃��b�Z�[�W���K�v�ƂȂ����ꍇ�Ƀ��[�h�����B
     * �A���A�f�t�H���g���P�[���́A�T�[�r�X�J�n���Ƀ��[�h�����B<br>
     * 
     * @param locales ���P�[�������z��
     */
    public void setLocaleStrings(String[] locales);
    
    /**
     * �����ǂݍ��݂��郁�b�Z�[�W��`�t�@�C���̃��P�[�����擾����B<p>
     * 
     * @return ���P�[�������z��
     */
    public String[] getLocaleStrings();
    
    /**
     * ���b�Z�[�W�̏㏑����`�����e���邩�ǂ�����ݒ肷��B<p>
     *
     * @param isAllow ���e����ꍇ�Atrue
     */
    public void setAllowOverrideMessage(boolean isAllow);
    
    /**
     * ���b�Z�[�W�̏㏑����`�����e���邩�ǂ����𔻒肷��B<p>
     *
     * @return true�̏ꍇ�A���e����
     */
    public boolean isAllowOverrideMessage();
    
    /**
     * Nimbus���g�̃��b�Z�[�W�t�@�C����ǂݍ��ނ��ǂ�����ݒ肷��B<p>
     * �f�t�H���g�́Atrue�œǂݍ��ށB<br>
     *
     * @param isLoad �ǂݍ��ޏꍇ�́Atrue
     */
    public void setLoadNimbusMessageFile(boolean isLoad);
    
    /**
     * Nimbus���g�̃��b�Z�[�W�t�@�C����ǂݍ��ނ��ǂ����𔻒肷��B<p>
     *
     * @return true�̏ꍇ�A�ǂݍ���
     */
    public boolean isLoadNimbusMessageFile();
    
    /**
     * ���I�Ƀ��b�Z�[�W��`�t�@�C���̔z�u�f�B���N�g����ǉ�����B<p>
     * �����ɕ����ǉ�����ꍇ�́A;��؂�ŕ����w�肷�邱�Ƃ��\�B<br>
     * ���̃��\�b�h�́A�T�[�r�X�J�n��ɓ��I�Ƀ��b�Z�[�W��`��ǉ�����ꍇ�Ɏg�p����B<br>
     * 
     * @param dirPaths  �f�B���N�g���w�蕶����
     * @exception Exception ���b�Z�[�W��`�̓��I���[�h�Ɏ��s�����ꍇ
     */
    public void addMessageDirPaths(String dirPaths) throws Exception;
    
    /**
     * ���I�Ƀ��b�Z�[�W��`�t�@�C���̃N���X�p�X���̃��\�[�X�p�X��ǉ�����B<p>
     * �����ɕ����ǉ�����ꍇ�́A;��؂�ŕ����w�肷�邱�Ƃ��\�B<br>
     * ���̃��\�b�h�́A�T�[�r�X�J�n��ɓ��I�Ƀ��b�Z�[�W��`��ǉ�����ꍇ�Ɏg�p����B<br>
     * 
     * @param paths ���b�Z�[�W��`�t�@�C���̃N���X�p�X���̃��\�[�X�p�X������
     * @exception Exception ���b�Z�[�W��`�̓��I���[�h�Ɏ��s�����ꍇ
     */
    public void addMessageFiles(String paths) throws Exception;
    
    /**
     * ���b�Z�[�W�ꗗ���擾����B<p>
     * 
     * @return ���b�Z�[�W�ꗗ
     */
    public ArrayList getMessgaeList();
    
    /**
     * �g�p���ꂽ���b�Z�[�W�̈ꗗ���擾����B<p>
     * 
     * @return �g�p���ꂽ���b�Z�[�W�ꗗ
     */
    public ArrayList getUsedMessgaeList();
    
    /**
     * ���b�Z�[�W�̎g�p�J�E���g������������B<p>
     */
    public void initUsedCount();


}
