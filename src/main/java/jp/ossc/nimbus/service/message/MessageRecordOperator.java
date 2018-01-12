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
package jp.ossc.nimbus.service.message;

import java.util.*;

/**
 * ���b�Z�[�W���R�[�h�̊Ǘ��p�C���^�t�F�[�X�B<p>
 * ���b�Z�[�W���R�[�h���̎g�p�񐔂̎擾�A���Z�b�g�Ȃǂ��s���C���^�[�t�F�C�X���`����B
 *
 * @author H.Nakano
 */
public interface MessageRecordOperator{
    
    /**
     * ���b�Z�[�W��`�t�@�C����1�s��ǂݍ��ށB<p>
     *
     * @param defString ���b�Z�[�W��`�t�@�C����1�s�̕�����
     */
    public void rec2Obj(String defString) throws MessageRecordParseException;
    
    /**
     * ���P�[�����̃��b�Z�[�W��ǉ�����B<p>
     * 
     * @param message ���b�Z�[�W������
     * @param locale ���P�[���w�蕶����
     */
    public void addMessage(String message, String locale);
    
    /**
     * �g�p�񐔂��擾����B<p>
     * 
     * @return �g�p��
     */
    public long getUsedCount();
    
    /**
     * �g�p�񐔂��N���A����B<p>
     */
    public void clearUsedCount();
    
    /**
     * �g�p�ŏI�������擾����B<p>
     * 
     * @return �ŏI�g�p����
     */
    public Date getLastUsedDate();
    
    /**
     * �閧���ߍ��݃��b�Z�[�W��閧�����Ń}�X�N���邩�ǂ�����ݒ肷��B<p>
     * 
     * @param flg �閧�����Ń}�X�N����ꍇtrue
     */
    public void setSecret(boolean flg);
    
    /**
     * �閧������ݒ肷��B<p>
     * �ݒ肵�Ȃ��ꍇ�́A���b�Z�[�W��`�̂܂܂ŏo�͂����B<br>
     * 
     * @param secret �閧����
     */
    public void setSecretString(String secret);
    
    /**
     * ���b�Z�[�W���R�[�h�t�@�N�g����ݒ肷��B<p>
     * 
     * @param fac ���b�Z�[�W���R�[�h�t�@�N�g��
     */
    public void setFactory(MessageRecordFactory fac);
}
