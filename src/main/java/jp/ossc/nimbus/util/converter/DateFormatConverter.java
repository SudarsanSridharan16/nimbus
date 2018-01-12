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
package jp.ossc.nimbus.util.converter;

import java.text.*;

/**
 * ���t�t�H�[�}�b�g�R���o�[�^�B<p>
 * 
 * @author M.Takata
 */
public class DateFormatConverter
 implements FormatConverter, java.io.Serializable{
    
    private static final long serialVersionUID = -1183874197480695923L;
    
    /**
     * ���t���������\���ϊ���ʒ萔�B<p>
     */
    public static final int DATE_TO_STRING = OBJECT_TO_STRING;
    
    /**
     * �����񁨓��t��\���ϊ���ʒ萔�B<p>
     */
    public static final int STRING_TO_DATE = STRING_TO_OBJECT;
    
    /**
     * �ϊ���ʁB<p>
     */
    protected int convertType;
    
    /**
     * �t�H�[�}�b�g�B<p>
     */
    protected String format;
    
    /**
     * �n���ꂽDate��null�̏ꍇ�ɕԂ�������B<p>
     */
    protected String nullString = "";
    
    /**
     * �t�H�[�}�b�g"yyyy/MM/dd HH:mm:ss.SSS"�œ��t��������ϊ����s���R���o�[�^�𐶐�����B<p>
     */
    public DateFormatConverter(){
        this(DATE_TO_STRING, "yyyy/MM/dd HH:mm:ss.SSS");
    }
    
    /**
     * �w�肳�ꂽ�ϊ���ʂ̃R���o�[�^�𐶐�����B<p>
     *
     * @param type �ϊ����
     * @param format ���l�t�H�[�}�b�g
     * @see #DATE_TO_STRING
     * @see #STRING_TO_DATE
     */
    public DateFormatConverter(int type, String format){
        convertType = type;
        this.format = format;
        new SimpleDateFormat(format);
    }
    
    /**
     * �ϊ���ʂ�ݒ肷��B<p>
     *
     * @param type �ϊ����
     * @see #getConvertType()
     * @see #DATE_TO_STRING
     * @see #STRING_TO_DATE
     */
    public void setConvertType(int type){
        convertType = type;
    }
    
    /**
     * �ϊ���ʂ��擾����B<p>
     *
     * @return �ϊ����
     * @see #setConvertType(int)
     */
    public int getConvertType(){
        return convertType;
    }
    
    /**
     * �ϊ��t�H�[�}�b�g��ݒ肷��B<p>
     *
     * @param format {@link SimpleDateFormat}�̕ϊ��t�H�[�}�b�g
     */
    public void setFormat(String format){
        this.format = format;
    }
    
    /**
     * �ϊ��t�H�[�}�b�g���擾����B<p>
     *
     * @return �ϊ��t�H�[�}�b�g
     * @see #setFormat(String)
     */
    public String getFormat(){
        return format;
    }
    
    /**
     * �n���ꂽDate��null�̏ꍇ�ɕԂ��������ݒ肷��B<p>
     * �f�t�H���g�́A�󕶎��B<br>
     *
     * @param str �n���ꂽDate��null�̏ꍇ�ɕԂ�������
     */
    public void setNullString(String str){
        nullString = str;
    }
    
    /**
     * �n���ꂽDate��null�̏ꍇ�ɕԂ���������擾����B<p>
     *
     * @return �n���ꂽDate��null�̏ꍇ�ɕԂ�������
     */
    public String getNullString(){
        return nullString;
    }
    
    /**
     * �w�肳�ꂽ�I�u�W�F�N�g��ϊ�����B<p>
     *
     * @param obj �ϊ��Ώۂ̃I�u�W�F�N�g
     * @return �ϊ���̃I�u�W�F�N�g
     * @exception ConvertException �ϊ��Ɏ��s�����ꍇ
     */
    public Object convert(Object obj) throws ConvertException{
        
        switch(convertType){
        case DATE_TO_STRING:
            if(obj == null){
                return nullString;
            }
            return new SimpleDateFormat(format).format(obj);
        case STRING_TO_DATE:
            if(obj == null || ((String)obj).length() == 0){
                return null;
            }
            try{
                return new SimpleDateFormat(format).parse((String)obj);
            }catch(ParseException e){
                throw new ConvertException(e);
            }
        default:
            throw new ConvertException(
                "Invalid convert type : " + convertType
            );
        }
    }
}
