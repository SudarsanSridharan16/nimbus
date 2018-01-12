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
 * {@link DataSetJournalEditorService}��MBean�C���^�t�F�[�X�B<p>
 * 
 * @author M.Takata
 * @see DataSetJournalEditorService
 */
public interface DataSetJournalEditorServiceMBean
 extends BlockJournalEditorServiceBaseMBean{
    
    /**
     * {@link jp.ossc.nimbus.beans.dataset.DataSet DataSet}�������O���o�͂��邩�ǂ�����ݒ肷��B<p>
     * �f�t�H���g�́Atrue�B<p>
     * 
     * @param isOutput �o�͂���ꍇ�́Atrue
     */
    public void setOutputDataSetName(boolean isOutput);
    
    /**
     * {@link jp.ossc.nimbus.beans.dataset.DataSet DataSet}�������O���o�͂��邩�ǂ����𔻒肷��B<p>
     * 
     * @return true�̏ꍇ�́A�o�͂���
     */
    public boolean isOutputDataSetName();
    
    /**
     * {@link jp.ossc.nimbus.beans.dataset.DataSet DataSet}������{@link jp.ossc.nimbus.beans.dataset.Header Header}���W���[�i���ɏo�͂��鎞�ɁA�o�͂���w�b�_�̖��O�z���ݒ肷��B<p>
     *
     * @param names �o�͂���w�b�_�̖��O�z��
     * @see #getEnabledHeaders()
     */
    public void setEnabledHeaders(String[] names);
    
    /**
     * {@link jp.ossc.nimbus.beans.dataset.DataSet DataSet}������{@link jp.ossc.nimbus.beans.dataset.Header Header}���W���[�i���ɏo�͂��鎞�ɁA�o�͂���w�b�_�̖��O�z����擾����B<p>
     *
     * @return �o�͂���w�b�_�̖��O�z��
     * @see #setEnabledHeaders(String[])
     */
    public String[] getEnabledHeaders();
    
    /**
     * {@link jp.ossc.nimbus.beans.dataset.DataSet DataSet}������{@link jp.ossc.nimbus.beans.dataset.RecordList RecordList}���W���[�i���ɏo�͂��鎞�ɁA�o�͂��郌�R�[�h���X�g�̖��O�z���ݒ肷��B<p>
     *
     * @param names �o�͂��郌�R�[�h���X�g�̖��O�z��
     * @see #getEnabledRecordLists()
     */
    public void setEnabledRecordLists(String[] names);
    
    /**
     * {@link jp.ossc.nimbus.beans.dataset.DataSet DataSet}������{@link jp.ossc.nimbus.beans.dataset.RecordList RecordList}���W���[�i���ɏo�͂��鎞�ɁA�o�͂��郌�R�[�h���X�g�̖��O�z����擾����B<p>
     *
     * @return �o�͂��郌�R�[�h���X�g�̖��O�z��
     * @see #setEnabledRecordLists(String[])
     */
    public String[] getEnabledRecordLists();
}