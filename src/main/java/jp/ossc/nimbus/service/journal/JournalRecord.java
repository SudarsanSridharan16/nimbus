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
package jp.ossc.nimbus.service.journal;

import jp.ossc.nimbus.service.journal.editorfinder.EditorFinder;

/**
 * �W���[�i�����R�[�h�C���^�t�F�[�X�B<p>
 * �W���[�i�������i�[����I�u�W�F�N�g�ł���B<br>
 * 
 * @author   H.Nakano
 */
public interface JournalRecord {
    
    /**
     * �W���[�i�����̃L�[���擾����B<p>
     * 
     * @return �W���[�i�����̃L�[
     */
    public String getKey();
    
    /**
     * �W���[�i������ҏW����{@link JournalEditor}���擾����B<p>
     * 
     * @return �W���[�i������ҏW����JournalEditor
     */
    public JournalEditor getJournalEditor();
    
    /**
     * �W���[�i������ҏW�������ʂ̃I�u�W�F�N�g���擾����B<p>
     * 
     * @return �W���[�i������ҏW�������ʂ̃I�u�W�F�N�g
     */
    public Object toObject();
    
    /**
     * �W���[�i�������w�肳�ꂽ{@link JournalEditor}�ŕҏW�������ʂ̃I�u�W�F�N�g���擾����B<p>
     * 
     * @param finder �W���[�i������ҏW����JournalEditor
     * @return �W���[�i������ҏW�������ʂ̃I�u�W�F�N�g
     */
    public Object toObject(EditorFinder finder);
    
    /**
     * ���̃W���[�i�����R�[�h���X�e�b�v���ǂ����𔻒肷��B<p>
     * 
     * @return �X�e�b�v�̏ꍇtrue
     */
    public boolean isStep();
}
