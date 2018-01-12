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
 * {@link MapJournalEditorService}��MBean�C���^�t�F�[�X�B<p>
 * 
 * @author M.Takata
 * @see MapJournalEditorService
 */
public interface MapJournalEditorServiceMBean
 extends ImmutableJournalEditorServiceBaseMBean{
    
    /**
     * �J�n��؂蕶����ݒ肷��B<p>
     *
     * @param delim ��؂蕶��
     */
    public void setStartDelimiter(String delim);
    
    /**
     * �J�n��؂蕶�����擾����B<p>
     *
     * @return ��؂蕶��
     */
    public String getStartDelimiter();
    
    /**
     * �I����؂蕶����ݒ肷��B<p>
     *
     * @param delim ��؂蕶��
     */
    public void setEndDelimiter(String delim);
    
    /**
     * �I����؂蕶�����擾����B<p>
     *
     * @return ��؂蕶��
     */
    public String getEndDelimiter();
    
    /**
     * �}�b�v�̃G���g���̋�؂蕶����ݒ肷��B<p>
     *
     * @param delim ��؂蕶��
     */
    public void setDelimiter(String delim);
    
    /**
     * �}�b�v�̃G���g���̋�؂蕶�����擾����B<p>
     *
     * @return ��؂蕶��
     */
    public String getDelimiter();
    
    /**
     * �}�b�v�̃L�[�ƒl�̋�؂蕶����ݒ肷��B<p>
     *
     * @param delim ��؂蕶��
     */
    public void setKeyValueDelimiter(String delim);
    
    /**
     * �}�b�v�̃L�[�ƒl�̋�؂蕶�����擾����B<p>
     *
     * @return ��؂蕶��
     */
    public String getKeyValueDelimiter();
    
    /**
     * �}�b�v�̒l�̊J�n��؂蕶����ݒ肷��B<p>
     *
     * @param delim ��؂蕶��
     */
    public void setStartValueDelimiter(String delim);
    
    /**
     * �}�b�v�̒l�̊J�n��؂蕶�����擾����B<p>
     *
     * @return ��؂蕶��
     */
    public String getStartValueDelimiter();
    
    /**
     * �}�b�v�̒l�̏I����؂蕶����ݒ肷��B<p>
     *
     * @param delim ��؂蕶��
     */
    public void setEndValueDelimiter(String delim);
    
    /**
     * �}�b�v�̒l�̏I����؂蕶�����擾����B<p>
     *
     * @return ��؂蕶��
     */
    public String getEndValueDelimiter();
    
    /**
     * �l���}�X�N����ۂ̃}�X�N�������ݒ肷��B<p>
     *
     * @param str �}�X�N������
     */
    public void setSecretString(String str);
    
    /**
     * �l���}�X�N����ۂ̃}�X�N��������擾����B<p>
     *
     * @return �}�X�N������
     */
    public String getSecretString();
    
    /**
     * �l���}�X�N����L�[���̔z���ݒ肷��B<p>
     *
     * @param keys �L�[���̔z��
     */
    public void setSecretKeys(String[] keys);
    
    /**
     * �l���}�X�N����L�[���̔z����擾����B<p>
     *
     * @return �L�[���̔z��
     */
    public String[] getSecretKeys();
    
    /**
     * �l���o�͂���L�[���̔z���ݒ肷��B<p>
     * ���̑�����ݒ肵�Ȃ��ꍇ�́A�S�ẴL�[���o�͂����B<br>
     *
     * @param keys �L�[���̔z��
     */
    public void setEnabledKeys(String[] keys);
    
    /**
     * �l���o�͂���L�[���̔z����擾����B<p>
     *
     * @return �L�[���̔z��
     */
    public String[] getEnabledKeys();
}
