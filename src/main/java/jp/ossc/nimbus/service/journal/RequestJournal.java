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

import java.util.*;

/**
 * ���N�G�X�g�W���[�i���C���^�t�F�[�X�B<p>
 * �W���[�i���̃X�e�b�v�����i�[����I�u�W�F�N�g�ł���B<br>
 * 
 * @author   H.Nakano
 */
public interface RequestJournal{
    
    /**
     * �X�e�b�v�̃L�[���擾����B<p>
     *
     * @return �X�e�b�v�̃L�[
     */
    public String getKey();
    
    /**
     * ���N�G�X�gID���擾����B<p>
     *
     * @return ���N�G�X�gID
     */
    public String getRequestId();
    
    /**
     * �X�e�b�v�J�n�������擾����B<p>
     * 
     * @return �X�e�b�v�J�n����
     */
    public Date getStartTime();
    
    /**
     * �X�e�b�v�I���������擾����B<p>
     * 
     * @return �X�e�b�v�I������
     */
    public Date getEndTime();
    
    /**
     * �X�e�b�v��������[ms]���擾����B<p>
     * 
     * @return �X�e�b�v��������[ms]
     */
    public long getPerformance();
    
    /**
     * �X�e�b�v�ɒǉ����ꂽ�W���[�i�������擾����B<p>
     * 
     * @return �W���[�i�����̔z��
     */
    public JournalRecord[] getParamAry();
    
    /**
     * �X�e�b�v�ɒǉ����ꂽ�W���[�i����񂩂�A�w�肳�ꂽ�L�[�̃W���[�i�������擾����B<p>
     * 
     * @return �w�肳�ꂽ�L�[�̃W���[�i�����̔z��
     */
    public JournalRecord[] findParamArys(String key);
    
    /**
     * ���[�g�X�e�b�v���ǂ����𔻒肷��B<p>
     * 
     * @return ���[�g�X�e�b�v�̏ꍇtrue
     */
    public boolean isRoot();
    
    public void clearParam(int from);
}
