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
package jp.ossc.nimbus.beans.dataset;

/**
 * �v���p�e�B�̃X�L�[�}��`�B<p>
 * 
 * @author M.Takata
 */
public interface PropertySchema{
    
    /**
     * �v���p�e�B�̃X�L�[�}��`��ݒ肷��B<p>
     *
     * @param schema �v���p�e�B�̃X�L�[�}��`
     * @exception PropertySchemaDefineException �v���p�e�B�̃X�L�[�}��`�Ɏ��s�����ꍇ
     */
    public void setSchema(String schema) throws PropertySchemaDefineException;
    
    /**
     * �v���p�e�B�̃X�L�[�}��������擾����B<p>
     *
     * @return �v���p�e�B�̃X�L�[�}������
     */
    public String getSchema();
    
    /**
     * �v���p�e�B�̖��O���擾����B<p>
     *
     * @return �v���p�e�B�̖��O
     */
    public String getName();
    
    /**
     * �v���p�e�B�̌^���擾����B<p>
     *
     * @return �v���p�e�B�̌^
     */
    public Class getType();
    
    /**
     * ��L�[���ǂ����𔻕ʂ���B<p>
     *
     * @return true�̏ꍇ�A��L�[
     */
    public boolean isPrimaryKey();
    
    /**
     * �v���p�e�B�̒l��ݒ肷�鎞�ɌĂяo�����B<p>
     *
     * @param val �ݒ肵�悤�Ƃ��Ă���v���p�e�B�̒l
     * @return �ݒ肳���v���p�e�B�̒l
     * @exception PropertySetException �v���p�e�B�̐ݒ�Ɏ��s�����ꍇ
     */
    public Object set(Object val) throws PropertySetException;
    
    /**
     * �v���p�e�B�̒l���擾���鎞�ɌĂяo�����B<p>
     *
     * @param val �擾���悤�Ƃ��Ă���v���p�e�B�̒l
     * @return �擾�����v���p�e�B�̒l
     * @exception PropertyGetException �v���p�e�B�̎擾�Ɏ��s�����ꍇ
     */
    public Object get(Object val) throws PropertyGetException;
    
    /**
     * �t�H�[�}�b�g���ꂽ�v���p�e�B�̒l���擾���鎞�ɌĂяo�����B<p>
     *
     * @param val �擾���悤�Ƃ��Ă���t�H�[�}�b�g���ꂽ�v���p�e�B�̒l
     * @return �擾�����t�H�[�}�b�g���ꂽ�v���p�e�B�̒l
     * @exception PropertyGetException �v���p�e�B�̎擾�Ɏ��s�����ꍇ
     */
    public Object format(Object val) throws PropertyGetException;
    
    /**
     * �p�[�X���ăv���p�e�B�̒l��ݒ肷�鎞�ɌĂяo�����B<p>
     *
     * @param val �ݒ肵�悤�Ƃ��Ă���p�[�X����v���p�e�B�̒l
     * @return �ݒ肳���p�[�X���ꂽ�v���p�e�B�̒l
     * @exception PropertySetException �v���p�e�B�̐ݒ�Ɏ��s�����ꍇ
     */
    public Object parse(Object val) throws PropertySetException;
    
    /**
     * �v���p�e�B�̒l�����؂��鎞�ɌĂяo�����B<p>
     *
     * @param val ���؂��悤�Ƃ��Ă���v���p�e�B�̒l
     * @return ���،��ʁBtrue�̏ꍇ�A���ؐ���
     * @exception PropertyValidateException �v���p�e�B�̌��؎��ɗ�O�����������ꍇ
     */
    public boolean validate(Object val) throws PropertyValidateException;
}
