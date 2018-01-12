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
package jp.ossc.nimbus.service.journal.editor;

/**
 * {@link BeanJournalEditorService}��MBean�C���^�t�F�[�X�B<p>
 * 
 * @author M.Takata
 * @see BeanJournalEditorService
 */
public interface BeanJournalEditorServiceMBean
 extends BlockJournalEditorServiceBaseMBean{
    
    /**
     * �v���p�e�B�̌^�����o�͂��邩�ǂ�����ݒ肷��B<p>
     * �f�t�H���g��true�B<br>
     *
     * @param isOutput �o�͂���ꍇ�́Atrue
     */
    public void setOutputPropertyType(boolean isOutput);
    
    /**
     * �v���p�e�B�̌^�����o�͂��邩�ǂ����𔻒肷��B<p>
     *
     * @return true�̏ꍇ�́A�o�͂���
     */
    public boolean isOutputPropertyType();
    
    /**
     * �ҏW����Bean��public�t�B�[���h�݂̂�ΏۂƂ��邩�ǂ����𔻒肷��B<p>
     *
     * @return true�̏ꍇ�Apublic�t�B�[���h�݂̂�ΏۂƂ���
     */
    public boolean isFieldOnly();
    
    /**
     * �ҏW����Bean��public�t�B�[���h�݂̂�ΏۂƂ��邩�ǂ�����ݒ肷��B<p>
     * �f�t�H���g�́Afalse��public�t�B�[���h�݂̂�Ώۂɂ͂��Ȃ��B<br>
     *
     * @param isFieldOnly public�t�B�[���h�݂̂�ΏۂƂ���ꍇ�́Atrue
     */
    public void setFieldOnly(boolean isFieldOnly);
    
    /**
     * �ҏW����Bean��public��getter�݂̂�ΏۂƂ��邩�ǂ����𔻒肷��B<p>
     *
     * @return true�̏ꍇ�Apublic��getter�݂̂�ΏۂƂ���
     */
    public boolean isAccessorOnly();
    
    /**
     * �ҏW����Bean��public��getter�݂̂�ΏۂƂ��邩�ǂ�����ݒ肷��B<p>
     * �f�t�H���g�́Atrue��public��getter�݂̂�Ώۂɂ���B<br>
     *
     * @param isAccessorOnly public��getter�݂̂�ΏۂƂ���ꍇ�Atrue
     */
    public void setAccessorOnly(boolean isAccessorOnly);
    
    /**
     * �W���[�i���ɏo�͂��鎞�ɁA�l���B�����߂̕������ݒ肷��B<p>
     *
     * @param str �l���B�����߂̕�����
     * @see #getSecretString()
     */
    public void setSecretString(String str);
    
    /**
     * �W���[�i���ɏo�͂��鎞�ɁA�l���B�����߂̕�������擾����B<p>
     *
     * @return �l���B�����߂̕�����
     * @see #setSecretString(String)
     */
    public String getSecretString();
    
    /**
     * Bean�̃v���p�e�B���W���[�i���ɏo�͂��鎞�ɁA�l���B���v���p�e�B�̖��O�z���ݒ肷��B<p>
     * �N���X���܂Ŏw�肵�����ꍇ�́A�u���S�C���N���X��#�v���p�e�B���v�Ŏw�肷��B<br>
     *
     * @param names �l���B���v���p�e�B�̖��O�z��
     * @see #getSecretString()
     */
    public void setSecretProperties(String[] names);
    
    /**
     * Bean�̃v���p�e�B���W���[�i���ɏo�͂��鎞�ɁA�l���B���v���p�e�B�̖��O�z����擾����B<p>
     *
     * @return �l���B���v���p�e�B�̖��O�z��
     * @see #setSecretProperties(String[])
     */
    public String[] getSecretProperties();
    
    /**
     * �W���[�i���ɏo�͂���Bean�̃v���p�e�B�̖��O�z���ݒ肷��B<p>
     * �N���X���܂Ŏw�肵�����ꍇ�́A�u���S�C���N���X��#�v���p�e�B���v�Ŏw�肷��B<br>
     *
     * @param names �W���[�i���ɏo�͂���v���p�e�B�̖��O�z��
     */
    public void setEnabledProperties(String[] names);
    
    /**
     * �W���[�i���ɏo�͂���Bean�̃v���p�e�B�̖��O�z����擾����B<p>
     *
     * @return �W���[�i���ɏo�͂���v���p�e�B�̖��O�z��
     */
    public String[] getEnabledProperties();
    
    /**
     * �v���p�e�B�̌^�����o�͂���ۂ̊J�n��؂蕶����ݒ肷��B<p>
     *
     * @param delimiter ��؂蕶��
     */
    public void setPropertyTypeStartDelimiter(String delimiter);
    
    /**
     * �v���p�e�B�̌^�����o�͂���ۂ̊J�n��؂蕶�����擾����B<p>
     *
     * @return ��؂蕶��
     */
    public String getPropertyTypeStartDelimiter();
    
    /**
     * �v���p�e�B�̌^�����o�͂���ۂ̏I����؂蕶����ݒ肷��B<p>
     *
     * @param delimiter ��؂蕶��
     */
    public void setPropertyTypeEndDelimiter(String delimiter);
    
    /**
     * �v���p�e�B�̌^�����o�͂���ۂ̏I����؂蕶�����擾����B<p>
     *
     * @return ��؂蕶��
     */
    public String getPropertyTypeEndDelimiter();
    
    /**
     * �v���p�e�B���ƒl�̋�؂蕶����ݒ肷��B<p>
     *
     * @param delimiter ��؂蕶��
     */
    public void setPropertyNameValueDelimiter(String delimiter);
    
    /**
     * �v���p�e�B���ƒl�̋�؂蕶�����擾����B<p>
     *
     * @return ��؂蕶��
     */
    public String getPropertyNameValueDelimiter();
    
    /**
     * �v���p�e�B�l�̊J�n��؂蕶����ݒ肷��B<p>
     *
     * @param delimiter ��؂蕶��
     */
    public void setStartValueDelimiter(String delimiter);
    
    /**
     * �v���p�e�B�l�̊J�n��؂蕶�����擾����B<p>
     *
     * @return ��؂蕶��
     */
    public String getStartValueDelimiter();
    
    /**
     * �v���p�e�B�l�̏I����؂蕶����ݒ肷��B<p>
     *
     * @param delimiter ��؂蕶��
     */
    public void setEndValueDelimiter(String delimiter);
    
    /**
     * �v���p�e�B�l�̏I����؂蕶�����擾����B<p>
     *
     * @return ��؂蕶��
     */
    public String getEndValueDelimiter();
    
    /**
     * �v���p�e�B�̋�؂蕶����ݒ肷��B<p>
     *
     * @param delimiter ��؂蕶��
     */
    public void setPropertyDelimiter(String delimiter);
    
    /**
     * �v���p�e�B�̋�؂蕶�����擾����B<p>
     *
     * @return ��؂蕶��
     */
    public String getPropertyDelimiter();
}
