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
package jp.ossc.nimbus.service.context;

/**
 * ���L�R���e�L�X�g�l�������T�|�[�g�B<p>
 * ���L�R���e�L�X�g�̒l�Ƃ��č����X�V�\��Bean���T�|�[�g���ׂ��C���^�t�F�[�X�B<br>
 * 
 * @author M.Takata
 */
public interface SharedContextValueDifferenceSupport{
    
    /**
     * �X�V�o�[�W������ݒ肷��B<p>
     *
     * @param version �X�V�o�[�W����
     */
    public void setUpdateVersion(int version);
    
    /**
     * �X�V�o�[�W�������擾����B<p>
     *
     * @return �X�V�o�[�W����
     */
    public int getUpdateVersion();
    
    /**
     * �w�肳�ꂽ���������󂯂čX�V����B<p>
     *
     * @param diff �������
     * @return �S�čX�V���ꂽ�ꍇ�A1�B�X�V���ꂽ���̂ƁA�X�V����K�v���Ȃ��������̂����݂���ꍇ�A0�B����������ꂸ�ɁA�X�V�ł��Ȃ����̂����݂���ꍇ�A-1�B
     * @exception SharedContextUpdateException �����̍X�V�Ɏ��s�����ꍇ
     */
    public int update(SharedContextValueDifference diff) throws SharedContextUpdateException;
    
    /**
     * ���̃I�u�W�F�N�g�̕������쐬����B<p>
     *
     * @return ���̃I�u�W�F�N�g�̕���
     */
    public Object clone();
}