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

import java.math.*;

/**
 * �������ۂ߃R���o�[�^�B<p>
 * 
 * @author M.Takata
 */
public class DecimalScaleConverter implements Converter{
    
    private int scale = 0;
    private int roundingMode = BigDecimal.ROUND_HALF_UP;
    private Class returnType;
    
    /**
     * �X�P�[����ݒ肷��B<p>
     * �f�t�H���g�́A0�B<br>
     *
     * @param scale �X�P�[��
     */
    public void setScale(int scale){
        this.scale = scale;
    }
    
    /**
     * �X�P�[�����擾����B<p>
     *
     * @return �X�P�[��
     */
    public int getScale(){
        return scale;
    }
    
    /**
     * �ۂ߃��[�h��ݒ肷��B<p>
     * �f�t�H���g�́A{@link BigDecimal#ROUND_HALF_UP}�B<br>
     *
     * @param roundingMode �ۂ߃��[�h
     * @see BigDecimal
     */
    public void setRoundingMode(int roundingMode){
        new BigDecimal(0.0).setScale(scale, roundingMode);
        this.roundingMode = roundingMode;
    }
    
    /**
     * �ۂ߃��[�h���擾����B<p>
     *
     * @return �ۂ߃��[�h
     */
    public int getRoundingMode(){
        return roundingMode;
    }
    
    /**
     * �ϊ���̖߂�l�̌^��ݒ肷��B<p>
     * �f�t�H���g�́Anull�ŁA���͂̌^�ɑΉ������^�ŕԂ��B<br>
     * �T�|�[�g����^�́ABigDecimal�ADouble�AFloat�AString�B<br>
     * 
     * @param type �߂�l�̌^
     */
    public void setReturnType(Class type){
        if(type != null
            && !type.equals(BigDecimal.class)
            && !type.equals(Double.class)
            && !type.equals(Float.class)
            && !type.equals(String.class)
        ){
            throw new IllegalArgumentException("Unsupported type." + type.getName());
        }
        returnType = type;
    }
    
    /**
     * �ϊ���̖߂�l�̌^���擾����B<p>
     * 
     * @return �߂�l�̌^
     */
    public Class getReturnType(){
        return returnType;
    }
    
    /**
     * �w�肳�ꂽ�I�u�W�F�N�g���ۂ߂�B<p>
     * �T�|�[�g���Ȃ��^�̃I�u�W�F�N�g���n�����ƁA���̂܂ܕԂ��B<br>
     *
     * @param obj �ϊ��Ώۂ̃I�u�W�F�N�g
     * @return �ϊ���̃I�u�W�F�N�g
     * @exception ConvertException �ϊ��Ɏ��s�����ꍇ
     */
    public Object convert(Object obj) throws ConvertException{
        if(obj == null){
            return null;
        }
        Class retType = returnType;
        BigDecimal retVal = null;
        if(obj instanceof BigDecimal){
            retVal = ((BigDecimal)obj).setScale(scale, roundingMode);
            if(retType == null){
                retType = BigDecimal.class;
            }
        }else if(obj instanceof Number){
            retVal = new BigDecimal(((Number)obj).doubleValue()).setScale(scale, roundingMode);
            if(retType == null){
                if(obj instanceof Float){
                    retType = Float.class;
                }else{
                    retType = Double.class;
                }
            }
        }else if(obj instanceof String){
            try{
                retVal = new BigDecimal((String)obj).setScale(scale, roundingMode);
            }catch(NumberFormatException e){
                throw new ConvertException(e);
            }
            if(retType == null){
                retType = String.class;
            }
        }else{
            return obj;
        }
        if(retType.equals(BigDecimal.class)){
            return retVal;
        }else if(retType.equals(Double.class)){
            return new Double(retVal.doubleValue());
        }else if(retType.equals(Float.class)){
            return new Float(retVal.floatValue());
        }else{
            return retVal.toString();
        }
    }
}
