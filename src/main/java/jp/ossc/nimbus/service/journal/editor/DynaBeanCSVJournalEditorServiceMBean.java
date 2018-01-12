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
 * {@link DynaBeanCSVJournalEditorService}��MBean�C���^�t�F�[�X�B<p>
 * 
 * @author M.Takata
 * @see DynaBeanCSVJournalEditorService
 */
public interface DynaBeanCSVJournalEditorServiceMBean
 extends CSVJournalEditorServiceBaseMBean{
    
    public static final String DYNA_CLASS_KEY = "DYNA_CLASS";
    public static final String PROPERTIES_KEY = "PROPERTIES";
    
    public void setOutputElementKeys(String[] keys)
     throws IllegalArgumentException;
    
    public String[] getOutputElementKeys();
    
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
     * {@link org.apache.commons.beanutils.DynaBean DynaBean}�̃v���p�e�B���W���[�i���ɏo�͂��鎞�ɁA�l���B���v���p�e�B�̖��O�z���ݒ肷��B<p>
     *
     * @param names �l���B���v���p�e�B�̖��O�z��
     * @see #getSecretString()
     */
    public void setSecretProperties(String[] names);
    
    /**
     * {@link org.apache.commons.beanutils.DynaBean DynaBean}�̃v���p�e�B���W���[�i���ɏo�͂��鎞�ɁA�l���B���v���p�e�B�̖��O�z����擾����B<p>
     *
     * @return �l���B���v���p�e�B�̖��O�z��
     * @see #setSecretProperties(String[])
     */
    public String[] getSecretProperties();
    
    /**
     * {@link org.apache.commons.beanutils.DynaBean DynaBean}�̃v���p�e�B���W���[�i���ɏo�͂��鎞�ɁA�l���o�͂���v���p�e�B�̖��O�z���ݒ肷��B<p>
     *
     * @param names �l���o�͂���v���p�e�B�̖��O�z��
     * @see #getEnabledProperties()
     */
    public void setEnabledProperties(String[] names);
    
    /**
     * {@link org.apache.commons.beanutils.DynaBean DynaBean}�̃v���p�e�B���W���[�i���ɏo�͂��鎞�ɁA�l���o�͂���v���p�e�B�̖��O�z����擾����B<p>
     *
     * @return �l���o�͂���v���p�e�B�̖��O�z��
     * @see #setEnabledProperties(String[])
     */
    public String[] getEnabledProperties();
}