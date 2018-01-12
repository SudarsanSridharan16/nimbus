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
package jp.ossc.nimbus.service.test.evaluate;

import jp.ossc.nimbus.core.ServiceBaseMBean;

/**
 * {@link TextCompareEvaluateActionService}��MBean�C���^�t�F�[�X<p>
 * 
 * @author M.Takata
 * @see TextCompareEvaluateActionService
 */
public interface TextCompareEvaluateActionServiceMBean extends ServiceBaseMBean{
    
    /**
     * �ҏW��̔�r�Ώۃt�@�C���̊g���q�̃f�t�H���g�l�B<p>
     */
    public static final String DEFAULT_AFTER_EDIT_FILE_EXTENTION = ".edt";
    
    /**
     * ��r�Ώۃt�@�C���̕����G���R�[�f�B���O��ݒ肷��B<p>
     *
     * @param encoding �����G���R�[�f�B���O
     */
    public void setFileEncoding(String encoding);
    
    /**
     * ��r�Ώۃt�@�C���̕����G���R�[�f�B���O���擾����B<p>
     *
     * @return �����G���R�[�f�B���O
     */
    public String getFileEncoding();
    
    /**
     * ���K�\���̃}�b�`�t���O��ݒ肷��B<p>
     *
     * @param flags ���K�\���̃}�b�`�t���O
     */
    public void setMatchFlags(int[] flags);
    
    /**
     * ���K�\���̃}�b�`�t���O���擾����B<p>
     *
     * @return ���K�\���̃}�b�`�t���O
     */
    public int[] getMatchFlags();
    
    /**
     * �u�����̕ҏW���s������̔�r�Ώۃt�@�C�����o�͂��邩�ǂ����𔻒肷��B<p>
     *
     * @return true�̏ꍇ�A�o�͂���
     */
    public boolean isOutputFileAfterEdit();
    
    /**
     * �u�����̕ҏW���s������̔�r�Ώۃt�@�C�����o�͂��邩�ǂ�����ݒ肷��B<p>
     * �f�t�H���g�́Afalse�ŏo�͂��Ȃ��B<br>
     * �u�����̕ҏW���s������̔�r�Ώۃt�@�C���̃t�@�C�����́A���̃t�@�C�����ɁA{@link #getFileAfterEditExtention()}�Ŏ擾�����g���q��t���������O�ɂȂ�B<br>
     *
     * @param isOutput �o�͂���ꍇ�Atrue
     */
    public void setOutputFileAfterEdit(boolean isOutput);
    
    /**
     * �u�����̕ҏW���s������̔�r�Ώۃt�@�C���̊g���q��ݒ肷��B<p>
     *
     * @param extention �g���q
     */
    public void setFileAfterEditExtention(String extention);
    
    /**
     * �u�����̕ҏW���s������̔�r�Ώۃt�@�C���̊g���q���擾����B<p>
     *
     * @return �g���q
     */
    public String getFileAfterEditExtention();
    
    /**
     * ���̃A�N�V�����̃��\�[�X��`���쐬����ۂ̃f�t�H���g�̑z��R�X�g��ݒ肷��B<p>
     * 
     * @param cost �z��R�X�g
     */
    public void setExpectedCost(double cost);
    
    /**
     * ���̃A�N�V�����̃��\�[�X��`���쐬����ۂ̃f�t�H���g�̑z��R�X�g���擾����B<p>
     * 
     * @return �z��R�X�g
     */
    public double getExpectedCost();
    
    /**
     * ��r��t�@�C�������݂��Ȃ��ꍇ�A�e�X�g���ʂ�NG�ɂ��邩�ǂ����𔻒肷��B<p>
     *
     * @return true�̏ꍇ�A�e�X�g���ʂ�NG�ɂ���
     */
    public boolean isResultNGOnNotFoundDestFile();
    
    /**
     * ��r��t�@�C�������݂��Ȃ��ꍇ�A�e�X�g���ʂ�NG�ɂ��邩�ǂ�����ݒ肷��B<p>
     * �f�t�H���g�́Afalse�ŁANG�ɂ��Ȃ��B<br>
     *
     * @param isResultNG �e�X�g���ʂ�NG�ɂ���ꍇ�Atrue
     */
    public void setResultNGOnNotFoundDestFile(boolean isResultNG);
}
