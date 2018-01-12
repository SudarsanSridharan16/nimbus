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

import java.util.Date;
import java.sql.Time;

/**
 * �^�C���t�H�[�}�b�g�R���o�[�^�B<p>
 * 
 * @author M.Takata
 */
public class TimeFormatConverter extends DateFormatConverter
 implements java.io.Serializable{
    
    private static final long serialVersionUID = -5574663877130540939L;

    /**
     * �t�H�[�}�b�g"yyyy/MM/dd HH:mm:ss.SSS"�œ��t��������ϊ����s���R���o�[�^�𐶐�����B<p>
     */
    public TimeFormatConverter(){
    }
    
    /**
     * �w�肳�ꂽ�ϊ���ʂ̃R���o�[�^�𐶐�����B<p>
     *
     * @param type �ϊ����
     * @param format ���l�t�H�[�}�b�g
     * @see #DATE_TO_STRING
     * @see #STRING_TO_DATE
     */
    public TimeFormatConverter(int type, String format){
        super(type, format);
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
            return super.convert(obj);
        case STRING_TO_DATE:
            Object ret = super.convert(obj);
            if(ret == null){
                return null;
            }
            return new Time(((Date)ret).getTime());
        default:
            throw new ConvertException(
                "Invalid convert type : " + convertType
            );
        }
    }
}
