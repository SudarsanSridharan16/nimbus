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
package jp.ossc.nimbus.servlet;

import java.util.Properties;

import jp.ossc.nimbus.core.ServiceBaseMBean;

/**
 * {@link MappingBeanFlowSelectorService}��MBean�C���^�t�F�[�X<p>
 * 
 * @author M.Takata
 * @see MappingBeanFlowSelectorService
 */
public interface MappingBeanFlowSelectorServiceMBean extends DefaultBeanFlowSelectorServiceMBean{
    
    /**
     * ���N�G�X�g�p�X�ƃA�N�V�����t���[���̃}�b�s���O��ݒ肷��B<p>
     *
     * @param mapping ���N�G�X�g�p�X�ƃA�N�V�����t���[���̃}�b�s���O�B���N�G�X�g�p�X=�A�N�V�����t���[��
     */
    public void setMapping(Properties mapping);
    
    /**
     * ���N�G�X�g�p�X�ƃA�N�V�����t���[���̃}�b�s���O���擾����B<p>
     *
     * @return ���N�G�X�g�p�X�ƃA�N�V�����t���[���̃}�b�s���O
     */
    public Properties getMapping();
    
    /**
     * ���N�G�X�g�p�X�ƃA�N�V�����t���[���̃}�b�s���O�ɂ����āA���N�G�X�g�p�X�ɐ��K�\�����w��ł���悤�ɂ��邩�ǂ�����ݒ肷��B<p>
     * �f�t�H���g�́Afalse�Ő��K�\�����g�p���Ȃ��B<br>
     *
     * @param isEnable ���K�\�����g�p����ꍇ�́Atrue
     */
    public void setRegexEnabled(boolean isEnable);
    
    /**
     * ���N�G�X�g�p�X�ƃA�N�V�����t���[���̃}�b�s���O�ɂ����āA���N�G�X�g�p�X�ɐ��K�\�����w��ł��邩�ǂ����𔻒肷��B<p>
     *
     * @return true�̏ꍇ�A���K�\�����g�p����
     */
    public boolean isRegexEnabled();
    
    /**
     * ���K�\����r���s���ꍇ�Ɏg�p����}�b�`�t���O��ݒ肷��B<p>
     * �A���A{@link #isRegexEnabled()}��true�̏ꍇ�̂ݗL���ł���B<br>
     * �f�t�H���g�́A0�B<br>
     *
     * @param flag �}�b�`�t���O
     * @see java.util.regex.Pattern#CANON_EQ
     * @see java.util.regex.Pattern#CASE_INSENSITIVE
     * @see java.util.regex.Pattern#DOTALL
     * @see java.util.regex.Pattern#MULTILINE
     * @see java.util.regex.Pattern#UNICODE_CASE
     * @see java.util.regex.Pattern#UNIX_LINES
     */
    public void setRegexMatchFlag(int flag);
    
    /**
     * ���K�\����r���s���ꍇ�Ɏg�p����}�b�`�t���O���擾����B<p>
     *
     * @return �}�b�`�t���O
     */
    public int getRegexMatchFlag();
}
