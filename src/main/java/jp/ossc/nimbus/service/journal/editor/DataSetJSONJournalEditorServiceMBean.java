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
 * {@link DataSetJSONJournalEditorService}��MBean�C���^�t�F�[�X�B<p>
 * 
 * @author M.Takata
 * @see DataSetJSONJournalEditorService
 */
public interface DataSetJSONJournalEditorServiceMBean
 extends ImmutableJournalEditorServiceBaseMBean{
    
    /**
     * �X�L�[�}�����o�͂��邩�ǂ�����ݒ肷��B<p>
     * JSON��schema�v�f���o�͂��邩�ǂ�����ݒ肷��Btrue�̏ꍇ�A�o�͂���B�f�t�H���g�́Atrue�B<br>
     *
     * @param isOutput �X�L�[�}�����o�͂���ꍇ��true
     */
    public void setOutputSchema(boolean isOutput);
    
    /**
     * �X�L�[�}�����o�͂��邩�ǂ����𔻒肷��B<p>
     *
     * @return true�̏ꍇ�X�L�[�}�����o�͂���
     */
    public boolean isOutputSchema();
    
    /**
     * �w�b�_�̃v���p�e�B�����o�͂��邩�ǂ�����ݒ肷��B<p>
     * �f�t�H���g�́Atrue�ŁA�o�͂���B<br>
     * false�ɂ���ƁA�w�b�_��JSON�̃I�u�W�F�N�g�`���ł͂Ȃ��A�z��`���ŏo�͂����B<br>
     *
     * @param isOutput �w�b�_�̃v���p�e�B�����o�͂���ꍇ�́Atrue
     */
    public void setOutputPropertyNameOfHeader(boolean isOutput);
    
    /**
     * �w�b�_�̃v���p�e�B�����o�͂��邩�ǂ����𔻒肷��B<p>
     *
     * @return true�̏ꍇ�A�w�b�_�̃v���p�e�B�����o�͂���
     */
    public boolean isOutputPropertyNameOfHeader();
    
    /**
     * ���R�[�h���X�g�̃v���p�e�B�����o�͂��邩�ǂ�����ݒ肷��B<p>
     * �f�t�H���g�́Atrue�ŁA�o�͂���B<br>
     * false�ɂ���ƁA���R�[�h���X�g��JSON�̃I�u�W�F�N�g�`���ł͂Ȃ��A�z��`���ŏo�͂����B<br>
     *
     * @param isOutput ���R�[�h���X�g�̃v���p�e�B�����o�͂���ꍇ�́Atrue
     */
    public void setOutputPropertyNameOfRecordList(boolean isOutput);
    
    /**
     * ���R�[�h���X�g�̃v���p�e�B�����o�͂��邩�ǂ����𔻒肷��B<p>
     *
     * @return true�̏ꍇ�A���R�[�h���X�g�̃v���p�e�B�����o�͂���
     */
    public boolean isOutputPropertyNameOfRecordList();
}
