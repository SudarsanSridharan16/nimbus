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

import java.text.*;

/**
 * ���t�I�u�W�F�N�g�𕶎���Ƀt�H�[�}�b�g����{@link WritableElement}�����N���X�B<p>
 * �ݒ肳�ꂽ{@link java.util.Date}�I�u�W�F�N�g���A�w�肳�ꂽ�t�H�[�}�b�g�Ŏ��o���L�q�v�f�N���X�ł���B<br>
 * �ʏ�Ajava.util.Date�^�Ƀ}�b�s���O���Ďg�p����B<br>
 *
 * @author y-tokuda
 */
public class DateElement extends SimpleElement {
    
    private static final long serialVersionUID = -4106652777620891747L;
    
    //�����o�ϐ�
    /** �t�H�[�}�b�g�pString */
    private String mFormatStr;
    
    /** �f�t�H���g�t�H�[�}�b�g */
    protected static final String DEFAULT_FORMAT = "yyyy.MM.dd HH:mm:ss.SSS";
    
    /**
     * �C���X�^���X�𐶐�����B<p>
     */
    public DateElement(){
        //�f�t�H���g�̃t�H�[�}�b�g��ݒ�
        mFormatStr = DEFAULT_FORMAT;
    }
    
    /**
     * �C���X�^���X�𐶐�����B<p>
     *
     * @param value ���t�I�u�W�F�N�g
     */
    public DateElement(java.util.Date value){
        super(value);
        mFormatStr = DEFAULT_FORMAT;
    }
    
    /**
     * �C���X�^���X�𐶐�����B<p>
     *
     * @param key �L�[
     * @param value ���t�I�u�W�F�N�g
     */
    public DateElement(Object key, java.util.Date value){
        super(key, value);
        mFormatStr = DEFAULT_FORMAT;
    }
    
    /**
     * ���t�t�H�[�}�b�g��ݒ肷��B<p>
     * �t�H�[�}�b�^�ɂ́A{@link java.text.SimpleDateFormat}���g�p���Ă���̂ŁA�t�H�[�}�b�g������́A���̏����ɏ]���B<br>
     *
     * @param fmt �t�H�[�}�b�g������
     */
    public void setFormat(String fmt){
        if(fmt != null){
            mFormatStr = fmt;
        }
    }
    
    /**
     * ���̗v�f�̎���Date�I�u�W�F�N�g����t������Ƀt�H�[�}�b�g���Ď擾����B<p>
     * 
     * @return ���̗v�f�̓��t������
     */
    public String toString(){
        if(mValue == null){
            return "";
        }
        String ret = null;
        SimpleDateFormat dateFormat = null;
        try{
            dateFormat = new SimpleDateFormat(mFormatStr);
            ret = dateFormat.format(mValue);
        }catch(IllegalArgumentException e){
            dateFormat = new SimpleDateFormat(DEFAULT_FORMAT);
            ret = dateFormat.format(mValue);
        }
        return convertString(ret);
    }
    
    /**
     * ���̗v�f�̎���Date�I�u�W�F�N�g����t������Ƀt�H�[�}�b�g���Ď擾����B<p>
     * {@link #toString()}�Ɠ����l��Ԃ��B<br>
     * 
     * @return ���̗v�f�̓��t������
     */
    public Object toObject(){
        return toString();
    }
}
