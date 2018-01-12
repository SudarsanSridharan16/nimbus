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

import jp.ossc.nimbus.core.ServiceBaseMBean;
import jp.ossc.nimbus.service.journal.ImmutableJournalEditor;

/**
 * {@link ImmutableJournalEditorServiceBase}��MBean�C���^�t�F�[�X�B<p>
 * 
 * @author M.Takata
 * @see ImmutableJournalEditorServiceBase
 */
public interface ImmutableJournalEditorServiceBaseMBean
 extends ServiceBaseMBean, ImmutableJournalEditor{
    
    /**
     * �L�[���o�͂��邩�ǂ�����ݒ肷��B<p>
     *
     * @param isOutput true�̏ꍇ�A�L�[��������o�͂���
     */
    public void setOutputKey(boolean isOutput);
    
    /**
     * �L�[���o�͂��邩�ǂ����𔻒肷��B<p>
     *
     * @return true�̏ꍇ�A�L�[��������o�͂���
     */
    public boolean isOutputKey();
    
    /**
     * �C���f���g���o�͂��邩���Ȃ�����ݒ肷��B<p>
     * �f�t�H���g��true�ŁA�o�͂���B<br>
     *
     * @param isOutput �C���f���g���o�͂���ꍇtrue
     */
    public void setOutputIndent(boolean isOutput);
    
    /**
     * �C���f���g���o�͂��邩���Ȃ����𔻒肷��B<p>
     *
     * @return true�̏ꍇ�A�C���f���g���o�͂���
     */
    public boolean isOutputIndent();
    
    /**
     * ���s�������ݒ肷��B<p>
     *
     * @param separator ���s������
     * @see #getLineSeparator()
     */
    public void setLineSeparator(String separator);
    
    /**
     * ���s��������擾����B<p>
     *
     * @return ���s������
     * @see #setLineSeparator(String)
     */
    public String getLineSeparator();
    
    /**
     * �C���f���g�������ݒ肷��B<p>
     *
     * @param indent �C���f���g������
     * @see #getIndent()
     */
    public void setIndent(String indent);
    
    /**
     * �C���f���g��������擾����B<p>
     *
     * @return �C���f���g������
     * @see #setIndent(String)
     */
    public String getIndent();
}
