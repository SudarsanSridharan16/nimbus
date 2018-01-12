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

import jp.ossc.nimbus.service.writer.Category;
import jp.ossc.nimbus.service.writer.MessageWriteException;

/**
 * ���O�J�e�S���B<p>
 * ���O�o�͐�𕪗ނ���J�e�S����\���C���^�t�F�[�X�B<br>
 *
 * @author M.Takata
 */
public interface LogCategory extends Category{
    
    /**
     * �J�e�S�������擾����B<p>
     *
     * @return �J�e�S����
     */
    public String getCategoryName();
    
    /**
     * �w�肳�ꂽ���O�̗D�揇�ʂ����̃J�e�S���̗D�揇�ʔ͈͓������肷��B<p>
     *
     * @param priority ���O�̗D�揇��
     * @return ���̃J�e�S���̗D�揇�ʔ͈͓��ł���ꍇ��true
     */
    public boolean isValidPriorityRange(int priority);
    
    /**
     * �w�肳�ꂽ���O�̗D�揇�ʂɑΉ����郉�x�����擾����B<p>
     *
     * @param priority ���O�̗D�揇��
     * @return ���x��������
     */
    public String getLabel(int priority);
    
    /**
     * �w�肳�ꂽ���O�o�͗v�f�̃}�b�s���O���A���̃J�e�S���ɏo�͂���B<p>
     *
     * @param priority ���O�̗D�揇��
     * @param elements WritableRecordFactory�ɓn�����O�o�͗v�f�̃}�b�s���O
     */
    public void write(int priority, java.util.Map elements) throws MessageWriteException;
}
