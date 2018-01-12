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

import jp.ossc.nimbus.util.converter.StringConverter;
import jp.ossc.nimbus.util.converter.FormatConverter;
import jp.ossc.nimbus.util.converter.ConvertException;

/**
 * �ȈՋL�q�v�f�B<p>
 * �ݒ肳�ꂽ�l�����̂܂܎��o���ȈՂȋL�q�v�f�N���X�ł���B<br>
 * {@link WritableRecord}�̃f�t�H���g�̗v�f�ł���B<br>
 *
 * @author Y.Tokuda
 */
public class SimpleElement implements WritableElement, java.io.Serializable {
    
    private static final long serialVersionUID = 8510769262946215439L;
    
    protected Object key;
    
    protected Object mValue;
    
    protected StringConverter stringConverter;
    
    protected FormatConverter formatConverter;
    
    protected String nullString = null;
    
    /**
     * ��̃C���X�^���X�𐶐�����B<p>
     */
    public SimpleElement(){
        key = this;
    }
    
    /**
     * �C���X�^���X�𐶐�����B<p>
     *
     * @param value �ϊ��Ώۂ̃I�u�W�F�N�g
     */
    public SimpleElement(Object value){
        key = this;
        mValue = value;
    }
    
    /**
     * �C���X�^���X�𐶐�����B<p>
     *
     * @param key �L�[
     * @param value �ϊ��Ώۂ̃I�u�W�F�N�g
     */
    public SimpleElement(Object key, Object value){
        this.key = key;
        mValue = value;
    }
    
    // WritableElement��JavaDoc
    public void setKey(Object key){
        this.key = key;
    }
    
    // WritableElement��JavaDoc
    public Object getKey(){
        return key == null ? this : key;
    }
    
    // WritableElement��JavaDoc
    public void setValue(Object val){
        mValue = val;
    }
    
    // WritableElement��JavaDoc
    public Object getValue(){
        return mValue;
    }
    
    /**
     * �l��null���������ɏo�͂��镶�����ݒ肷��B<p>
     *
     * @param str ������
     */
    public void setNullString(String str){
        nullString = str;
    }
    
    /**
     * �l��null���������ɏo�͂��镶������擾����B<p>
     *
     * @return ������
     */
    public String getNullString(){
        return nullString;
    }
    
    /**
     * ������ϊ����s���ۂɓK�p����R���o�[�^��ݒ肷��B<p>
     *
     * @param converter �R���o�[�^
     */
    public void setStringConverter(StringConverter converter){
        stringConverter = converter;
    }
    
    /**
     * ������ϊ����s���ۂɓK�p����R���o�[�^���擾����B<p>
     *
     * @return �R���o�[�^
     */
    public StringConverter getStringConverter(){
        return stringConverter;
    }
    
    /**
     * ������ϊ����s���ۂɓK�p����R���o�[�^��ݒ肷��B<p>
     *
     * @param converter �R���o�[�^
     */
    public void setFormatConverter(FormatConverter converter){
        formatConverter = converter;
        formatConverter.setConvertType(FormatConverter.OBJECT_TO_STRING);
    }
    
    /**
     * ������ϊ����s���ۂɓK�p����R���o�[�^���擾����B<p>
     *
     * @return �R���o�[�^
     */
    public FormatConverter getFormatConverter(){
        return formatConverter;
    }
    
    /**
     * ���̗v�f�̒l�����̂܂ܕ�����ɂ��Ď擾����B<p>
     * 
     * @return ���̗v�f�̒l��toString()���Ăяo��������
     */
    public String toString(){
        if(formatConverter == null){
            return convertString(mValue != null ? mValue.toString() : nullString);
        }else{
            final String ret = (String)formatConverter.convert(mValue);
            return ret == null ? nullString : ret;
        }
    }
    
    protected String convertString(String str){
        String result =  str;
        if(stringConverter != null && result != null){
            try{
                result = stringConverter.convert(result);
            }catch(ConvertException e){
            }
        }
        return result;
    }
    
    /**
     * ���̗v�f�̃I�u�W�F�N�g�����̂܂܎擾����B<p>
     * {@link #getValue()}�Ɠ����l��Ԃ��B<br>
     * 
     * @return ���̗v�f�̃I�u�W�F�N�g
     */
    public Object toObject(){
        return mValue;
    }
}