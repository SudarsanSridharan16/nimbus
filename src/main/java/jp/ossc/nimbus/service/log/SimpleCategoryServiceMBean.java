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

package jp.ossc.nimbus.service.log;

import java.util.*;

/**
 * {@link SimpleCategoryService}�T�[�r�XMBean�C���^�t�F�[�X�B<p>
 *
 * @author M.Takata
 */
public interface SimpleCategoryServiceMBean
 extends LogCategory, jp.ossc.nimbus.service.writer.SimpleCategoryServiceMBean{
    
    /**
     * �J�e�S������ݒ肷��B<p>
     *
     * @param name �J�e�S����
     */
    public void setCategoryName(String name);
    
    /**
     * ���̃J�e�S���ŏo�͂���郍�O�̗D�揇�ʔ͈͂�ݒ肷��B<p>
     *
     * @param range �D�揇�ʔ͈́B�ŏ��l:�ő�l�̏����Ŏw�肷��B
     * @exception IllegalArgumentException �D�揇�ʔ͈͂̎w�肪�s���ȏꍇ�B
     */
    public void setPriorityRange(String range) throws IllegalArgumentException;
    
    /**
     * ���̃J�e�S���ŏo�͂���郍�O�̗D�揇�ʔ͈͂��擾����B<p>
     *
     * @return �D�揇�ʔ͈�
     */
    public String getPriorityRange();
    
    /**
     * ���O�̗D�揇�ʂɑΉ����郉�x����ݒ肷��B<p>
     * ������labels�ɂ́A�ȉ��̃}�b�s���O��ݒ肷��B<br>
     * <table border="1">
     *   <tr bgcolor="#CCCCFF"><th colspan="2">�L�[</th><th colspan="2">�l</th></tr>
     *   <tr bgcolor="#CCCCFF"><th>�^</th><th>���e</th><th>�^</th><th>���e</th></tr>
     *   <tr><td>String</td><td>�D�揇�ʔ͈́B�ŏ��l:�ő�l�̏����Ŏw�肷��B</td><td>String</td><td>���x��</td></tr>
     * </table>
     *
     * @param labels ���O�̗D�揇�ʂɑΉ����郉�x���̃}�b�s���O
     * @exception IllegalArgumentException �D�揇�ʔ͈͂̎w�肪�s���ȏꍇ�B
     */
    public void setLabels(Properties labels) throws IllegalArgumentException;
    
    /**
     * ���O�̗D�揇�ʂɑΉ����郉�x�����擾����B<p>
     *
     * @return ���O�̗D�揇�ʂɑΉ����郉�x���̃}�b�s���O
     */
    public Properties getLabels();
}
