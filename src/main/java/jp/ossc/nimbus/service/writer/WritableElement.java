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
package jp.ossc.nimbus.service.writer;

/**
 * MessageWriter���������݉\�ȗv�f��\���C���^�t�F�[�X�B<p>
 * �L�[�ƒl�̊T�O�����B�܂��A������ւ̕ϊ��A�C�ӂ̃I�u�W�F�N�g�ւ̕ϊ����s���@�\�����B<br>
 * 
 * @author Y.Tokuda
 */
public interface WritableElement {
    
    /**
     * ���̗v�f��\���L�[��ݒ肷��B<p>
     *
     * @param key �L�[
     */
    public void setKey(Object key);
    
    /**
     * ���̗v�f��\���L�[���擾����B<p>
     *
     * @return �L�[
     */
    public Object getKey();
    
    /**
     * ���̗v�f�̕�����\�����擾����B<p>
     * 
     * @return ���̗v�f�̕�����\��
     */
    public String toString();
    
    /**
     * ���̗v�f�̕ҏW��I�u�W�F�N�g���擾����B<p>
     * 
     * @return ���̗v�f�̕ҏW��I�u�W�F�N�g
     */
    public Object toObject();
    
    /**
    * �v�f�̒l��ݒ肷��B<p>
    *
    * @param obj �v�f�̒l�̃I�u�W�F�N�g
    */
    public void setValue(Object obj);
    
    /**
    * �v�f�̒l���擾����B<p>
    *
    * @return �v�f�̒l�̃I�u�W�F�N�g
    */
    public Object getValue();
}
