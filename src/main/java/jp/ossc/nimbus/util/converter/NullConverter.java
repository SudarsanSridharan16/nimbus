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

import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * Null�R���o�[�^�B<p>
 * null�Q�Ƃ�C�ӂ̃I�u�W�F�N�g�ɕϊ�����B<br>
 * 
 * @author M.Takata
 */
public class NullConverter implements ReversibleConverter, java.io.Serializable{
    
    private static final long serialVersionUID = -5603437969416832007L;
    
    /**
     * null���I�u�W�F�N�g��\���ϊ���ʒ萔�B<p>
     */
    public static final int NULL_TO_OBJECT = POSITIVE_CONVERT;
    
    /**
     * �I�u�W�F�N�g��null��\���ϊ���ʒ萔�B<p>
     */
    public static final int OBJECT_TO_NULL = REVERSE_CONVERT;
    
    /**
     * �ϊ���ʁB<p>
     */
    protected int convertType;
    
    /**
     * null�Q�ƂɑΉ�����I�u�W�F�N�g�B<p>
     */
    protected Object nullObject;
    
    /**
     * null���I�u�W�F�N�g�ϊ��̃L�����N�^�R���o�[�^�𐶐�����B<p>
     */
    public NullConverter(){
        this(NULL_TO_OBJECT);
    }
    
    /**
     * �w�肳�ꂽ�ϊ���ʂ̕�����R���o�[�^�𐶐�����B<p>
     *
     * @param type �ϊ����
     * @see #NULL_TO_OBJECT
     * @see #OBJECT_TO_NULL
     */
    public NullConverter(int type){
        setConvertType(type);
    }
    
    /**
     * �ϊ���ʂ�ݒ肷��B<p>
     *
     * @param type �ϊ����
     * @see #getConvertType()
     * @see #NULL_TO_OBJECT
     * @see #OBJECT_TO_NULL
     */
    public void setConvertType(int type){
        convertType = type;
        switch(convertType){
        case NULL_TO_OBJECT:
        case OBJECT_TO_NULL:
            break;
        default:
            throw new IllegalArgumentException(
                "Invalid convert type : " + type
            );
        }
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
     * null�Q�ƂɑΉ�����I�u�W�F�N�g��ݒ肷��B<p>
     *
     * @param obj �I�u�W�F�N�g
     */
    public void setNullObject(Object obj){
        nullObject = obj;
    }
    
    /**
     * null�Q�ƂɑΉ�����I�u�W�F�N�g���擾����B<p>
     *
     * @return �I�u�W�F�N�g
     */
    public Object getNullObject(){
        return nullObject;
    }
    
    /**
     * null�Q�ƂɑΉ�����boolean��ݒ肷��B<p>
     *
     * @param na null�Q�ƂɑΉ�����boolean
     */
    public void setNullBoolean(Boolean na){
        nullObject = na;
    }
    
    /**
     * null�Q�ƂɑΉ�����char��ݒ肷��B<p>
     *
     * @param na null�Q�ƂɑΉ�����char
     */
    public void setNullChar(Character na){
        nullObject = na;
    }
    
    /**
     * null�Q�ƂɑΉ�����byte��ݒ肷��B<p>
     *
     * @param na null�Q�ƂɑΉ�����byte
     */
    public void setNullByte(Byte na){
        nullObject = na;
    }
    
    /**
     * null�Q�ƂɑΉ�����short��ݒ肷��B<p>
     *
     * @param na null�Q�ƂɑΉ�����short
     */
    public void setNullShort(Short na){
        nullObject = na;
    }
    
    /**
     * null�Q�ƂɑΉ�����int��ݒ肷��B<p>
     *
     * @param na null�Q�ƂɑΉ�����int
     */
    public void setNullInt(Integer na){
        nullObject = na;
    }
    
    /**
     * null�Q�ƂɑΉ�����long��ݒ肷��B<p>
     *
     * @param na null�Q�ƂɑΉ�����long
     */
    public void setNullLong(Long na){
        nullObject = na;
    }
    
    /**
     * null�Q�ƂɑΉ�����float��ݒ肷��B<p>
     *
     * @param na null�Q�ƂɑΉ�����float
     */
    public void setNullFloat(Float na){
        nullObject = na;
    }
    
    /**
     * null�Q�ƂɑΉ�����double��ݒ肷��B<p>
     *
     * @param na null�Q�ƂɑΉ�����double
     */
    public void setNullDouble(Double na){
        nullObject = na;
    }
    
    /**
     * null�Q�ƂɑΉ�����BigInteger��ݒ肷��B<p>
     *
     * @param na null�Q�ƂɑΉ�����BigInteger
     */
    public void setNullBigInteger(BigInteger na){
        nullObject = na;
    }
    
    /**
     * null�Q�ƂɑΉ�����BigDecimal��ݒ肷��B<p>
     *
     * @param na null�Q�ƂɑΉ�����BigDecimal
     */
    public void setNullBigDecimal(BigDecimal na){
        nullObject = na;
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
        case OBJECT_TO_NULL:
            if((obj == null && nullObject == null)
                || nullObject == obj
                || (nullObject != null && nullObject.equals(obj))){
                return null;
            }else{
                return obj;
            }
        case NULL_TO_OBJECT:
        default:
            if(obj == null){
                return nullObject;
            }else{
                return obj;
            }
        }
    }
}
