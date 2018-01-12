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
import java.math.*;
import java.util.*;
import java.lang.reflect.*;

import jp.ossc.nimbus.beans.*;

/**
 * �����t�H�[�}�b�g�R���o�[�^�B<p>
 * 
 * @author M.Takata
 */
public class DecimalFormatConverter implements FormatConverter{
    
    private static final long serialVersionUID = -1183874197480695923L;
    
    /**
     * ���l���������\���ϊ���ʒ萔�B<p>
     */
    public static final int NUMBER_TO_STRING = OBJECT_TO_STRING;
    
    /**
     * �����񁨐��l�ϊ���\���ϊ���ʒ萔�B<p>
     */
    public static final int STRING_TO_NUMBER = STRING_TO_OBJECT;
    
    protected static final String DOUBLE_NAN_STR;
    
    protected static final String DOUBLE_POSITIVE_INFINITY_STR;
    
    protected static final String DOUBLE_NEGATIVE_INFINITY_STR;
    
    static{
        DecimalFormat f = new DecimalFormat("#.#");
        DOUBLE_NAN_STR = f.format(Double.NaN);
        DOUBLE_POSITIVE_INFINITY_STR = f.format(Double.POSITIVE_INFINITY);
        DOUBLE_NEGATIVE_INFINITY_STR = f.format(Double.NEGATIVE_INFINITY);
    }
    
    /**
     * �ϊ���ʁB<p>
     */
    protected int convertType;
    
    /**
     * �t�H�[�}�b�g�B<p>
     */
    protected String format;
    
    /**
     * NA�l���g�p���邩�ǂ����̃t���O�B<p>
     * �f�t�H���g�́Afalse�Ŏg�p���Ȃ��B<br>
     */
    protected boolean isUseNotApplicable;
    
    /**
     * byte��NA�l�B<p>
     */
    protected Byte notApplicableForByte;
    
    /**
     * short��NA�l�B<p>
     */
    protected Short notApplicableForShort;
    
    /**
     * int��NA�l�B<p>
     */
    protected Integer notApplicableForInt;
    
    /**
     * long��NA�l�B<p>
     */
    protected Long notApplicableForLong;
    
    /**
     * float��NA�l�B<p>
     */
    protected Float notApplicableForFloat;
    
    /**
     * double��NA�l�B<p>
     */
    protected Double notApplicableForDouble;
    
    /**
     * BigInteger��NA�l�B<p>
     */
    protected BigInteger notApplicableForBigInteger;
    
    /**
     * BigDecimal��NA�l�B<p>
     */
    protected BigDecimal notApplicableForBigDecimal;
    
    /**
     * byte��NA�l�̕�����B<p>
     * �f�t�H���g�́Anull�B<br>
     */
    protected String notApplicableStringForByte;
    
    /**
     * short��NA�l�̕�����B<p>
     * �f�t�H���g�́Anull�B<br>
     */
    protected String notApplicableStringForShort;
    
    /**
     * int��NA�l�̕�����B<p>
     * �f�t�H���g�́Anull�B<br>
     */
    protected String notApplicableStringForInt;
    
    /**
     * long��NA�l�̕�����B<p>
     * �f�t�H���g�́Anull�B<br>
     */
    protected String notApplicableStringForLong;
    
    /**
     * float��NA�l�̕�����B<p>
     * �f�t�H���g�́Anull�B<br>
     */
    protected String notApplicableStringForFloat;
    
    /**
     * double��NA�l�̕�����B<p>
     * �f�t�H���g�́Anull�B<br>
     */
    protected String notApplicableStringForDouble;
    
    /**
     * BigInteger��NA�l�̕�����B<p>
     * �f�t�H���g�́Anull�B<br>
     */
    protected String notApplicableStringForBigInteger;
    
    /**
     * BigDecimal��NA�l�̕�����B<p>
     * �f�t�H���g�́Anull�B<br>
     */
    protected String notApplicableStringForBigDecimal;
    
    /**
     * java.text.DecimalFormat�ɐݒ肷��v���p�e�B���Ǘ�����}�b�v�B<p>
     */
    protected Map decimalFormatProperties;
    
    /**
     * �t�H�[�}�b�g�Ȃ��Ő��l��������ϊ����s���R���o�[�^�𐶐�����B<p>
     */
    public DecimalFormatConverter(){
        this(NUMBER_TO_STRING, "");
    }
    
    /**
     * �w�肳�ꂽ�ϊ���ʂ̃R���o�[�^�𐶐�����B<p>
     *
     * @param type �ϊ����
     * @param format ���l�t�H�[�}�b�g
     * @see #NUMBER_TO_STRING
     * @see #STRING_TO_NUMBER
     */
    public DecimalFormatConverter(int type, String format){
        convertType = type;
        this.format = format;
        new DecimalFormat(format);
    }
    
    /**
     * �ϊ���ʂ�ݒ肷��B<p>
     *
     * @param type �ϊ����
     * @see #getConvertType()
     * @see #NUMBER_TO_STRING
     * @see #STRING_TO_NUMBER
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
     * @param format {@link DecimalFormat}�̕ϊ��t�H�[�}�b�g
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
     * NA�l���g�p���邩�ǂ����𔻒肷��B<p>
     *
     * @return true�̏ꍇ�ANA�l���g�p����
     */
    public boolean isUseNotApplicable(){
        return isUseNotApplicable;
    }
    
    /**
     * NA�l���g�p���邩�ǂ����𔻒肷��B<p>
     * �f�t�H���g�́Afalse�ŁANA�l���g�p���Ȃ��B<br>
     *
     * @param isUse NA�l���g�p����ꍇ�Atrue
     */
    public void setUseNotApplicable(boolean isUse){
        isUseNotApplicable = isUse;
    }
    
    /**
     * byte��NA�l���擾����B<p>
     *
     * @return byte��NA�l
     */
    public Byte getNotApplicableForByte(){
        return notApplicableForByte;
    }
    
    /**
     * byte��NA�l��ݒ肷��B<p>
     *
     * @param na byte��NA�l
     */
    public void setNotApplicableForByte(Byte na){
        notApplicableForByte = na;
    }
    
    /**
     * short��NA�l���擾����B<p>
     *
     * @return short��NA�l
     */
    public Short getNotApplicableForShort(){
        return notApplicableForShort;
    }
    
    /**
     * short��NA�l��ݒ肷��B<p>
     *
     * @param na short��NA�l
     */
    public void setNotApplicableForShort(Short na){
        notApplicableForShort = na;
    }
    
    /**
     * int��NA�l���擾����B<p>
     *
     * @return int��NA�l
     */
    public Integer getNotApplicableForInt(){
        return notApplicableForInt;
    }
    
    /**
     * int��NA�l��ݒ肷��B<p>
     *
     * @param na int��NA�l
     */
    public void setNotApplicableForInt(Integer na){
        notApplicableForInt = na;
    }
    
    /**
     * long��NA�l���擾����B<p>
     *
     * @return long��NA�l
     */
    public Long getNotApplicableForLong(){
        return notApplicableForLong;
    }
    
    /**
     * long��NA�l��ݒ肷��B<p>
     *
     * @param na long��NA�l
     */
    public void setNotApplicableForLong(Long na){
        notApplicableForLong = na;
    }
    
    /**
     * float��NA�l���擾����B<p>
     *
     * @return byte��NA�l
     */
    public Float getNotApplicableForFloat(){
        return notApplicableForFloat;
    }
    
    /**
     * float��NA�l��ݒ肷��B<p>
     *
     * @param na float��NA�l
     */
    public void setNotApplicableForFloat(Float na){
        notApplicableForFloat = na;
    }
    
    /**
     * double��NA�l���擾����B<p>
     *
     * @return byte��NA�l
     */
    public Double getNotApplicableForDouble(){
        return notApplicableForDouble;
    }
    
    /**
     * double��NA�l��ݒ肷��B<p>
     *
     * @param na double��NA�l
     */
    public void setNotApplicableForDouble(Double na){
        notApplicableForDouble = na;
    }
    
    /**
     * BigInteger��NA�l���擾����B<p>
     *
     * @return byte��NA�l
     */
    public BigInteger getNotApplicableForBigInteger(){
        return notApplicableForBigInteger;
    }
    
    /**
     * BigInteger��NA�l��ݒ肷��B<p>
     *
     * @param na BigInteger��NA�l
     */
    public void setNotApplicableForBigInteger(BigInteger na){
        notApplicableForBigInteger = na;
    }
    
    /**
     * BigDecimal��NA�l���擾����B<p>
     *
     * @return byte��NA�l
     */
    public BigDecimal getNotApplicableForBigDecimal(){
        return notApplicableForBigDecimal;
    }
    
    /**
     * BigDecimal��NA�l��ݒ肷��B<p>
     *
     * @param na BigDecimal��NA�l
     */
    public void setNotApplicableForBigDecimal(BigDecimal na){
        notApplicableForBigDecimal = na;
    }
    
    /**
     * byte��NA�l�̕�������擾����B<p>
     *
     * @return byte��NA�l�̕�����
     */
    public String getNotApplicableStringForByte(){
        return notApplicableStringForByte;
    }
    
    /**
     * byte��NA�l�̕������ݒ肷��B<p>
     * �f�t�H���g�́Anull�B<br>
     *
     * @param na byte��NA�l�̕�����
     */
    public void setNotApplicableStringForByte(String na){
        notApplicableStringForByte = na;
    }
    
    /**
     * short��NA�l�̕�������擾����B<p>
     *
     * @return short��NA�l�̕�����
     */
    public String getNotApplicableStringForShort(){
        return notApplicableStringForShort;
    }
    
    /**
     * short��NA�l�̕������ݒ肷��B<p>
     * �f�t�H���g�́Anull�B<br>
     *
     * @param na short��NA�l�̕�����
     */
    public void setNotApplicableStringForShort(String na){
        notApplicableStringForShort = na;
    }
    
    /**
     * int��NA�l�̕�������擾����B<p>
     *
     * @return int��NA�l�̕�����
     */
    public String getNotApplicableStringForInt(){
        return notApplicableStringForInt;
    }
    
    /**
     * int��NA�l�̕������ݒ肷��B<p>
     * �f�t�H���g�́Anull�B<br>
     *
     * @param na int��NA�l�̕�����
     */
    public void setNotApplicableStringForInt(String na){
        notApplicableStringForInt = na;
    }
    
    /**
     * long��NA�l�̕�������擾����B<p>
     *
     * @return long��NA�l�̕�����
     */
    public String getNotApplicableStringForLong(){
        return notApplicableStringForLong;
    }
    
    /**
     * long��NA�l�̕������ݒ肷��B<p>
     * �f�t�H���g�́Anull�B<br>
     *
     * @param na long��NA�l�̕�����
     */
    public void setNotApplicableStringForLong(String na){
        notApplicableStringForLong = na;
    }
    
    /**
     * float��NA�l�̕�������擾����B<p>
     *
     * @return float��NA�l�̕�����
     */
    public String getNotApplicableStringForFloat(){
        return notApplicableStringForFloat;
    }
    
    /**
     * float��NA�l�̕������ݒ肷��B<p>
     * �f�t�H���g�́Anull�B<br>
     *
     * @param na float��NA�l�̕�����
     */
    public void setNotApplicableStringForFloat(String na){
        notApplicableStringForFloat = na;
    }
    
    /**
     * double��NA�l�̕�������擾����B<p>
     *
     * @return double��NA�l�̕�����
     */
    public String getNotApplicableStringForDouble(){
        return notApplicableStringForDouble;
    }
    
    /**
     * double��NA�l�̕������ݒ肷��B<p>
     * �f�t�H���g�́Anull�B<br>
     *
     * @param na double��NA�l�̕�����
     */
    public void setNotApplicableStringForDouble(String na){
        notApplicableStringForDouble = na;
    }
    
    /**
     * BigInteger��NA�l�̕�������擾����B<p>
     *
     * @return BigInteger��NA�l�̕�����
     */
    public String getNotApplicableStringForBigInteger(){
        return notApplicableStringForBigInteger;
    }
    
    /**
     * BigInteger��NA�l�̕������ݒ肷��B<p>
     * �f�t�H���g�́Anull�B<br>
     *
     * @param na BigInteger��NA�l�̕�����
     */
    public void setNotApplicableStringForBigInteger(String na){
        notApplicableStringForBigInteger = na;
    }
    
    /**
     * BigDecimal��NA�l�̕�������擾����B<p>
     *
     * @return BigDecimal��NA�l�̕�����
     */
    public String getNotApplicableStringForBigDecimal(){
        return notApplicableStringForBigDecimal;
    }
    
    /**
     * BigDecimal��NA�l�̕������ݒ肷��B<p>
     * �f�t�H���g�́Anull�B<br>
     *
     * @param na BigDecimal��NA�l�̕�����
     */
    public void setNotApplicableStringForBigDecimal(String na){
        notApplicableStringForBigDecimal = na;
    }
    
    /**
     * java.text.DecimalFormat�̃v���p�e�B��ݒ肷��B<p>
     *
     * @param name �v���p�e�B��
     * @param value �v���p�e�B�l
     */
    public void setDecimalFormatProperty(String name, Object value){
        if(decimalFormatProperties == null){
            decimalFormatProperties = new LinkedHashMap();
        }
        decimalFormatProperties.put(PropertyFactory.createProperty(name), value);
    }
    
    protected DecimalFormat createDecimalFormat() throws ConvertException{
        DecimalFormat df = new DecimalFormat(format);
        if(decimalFormatProperties != null){
            Iterator entries = decimalFormatProperties.entrySet().iterator();
            while(entries.hasNext()){
                Map.Entry entry = (Map.Entry)entries.next();
                Property prop = (Property)entry.getKey();
                try{
                    prop.setProperty(df, entry.getValue());
                }catch(NoSuchPropertyException e){
                    throw new ConvertException("DecimalFormat have not property. property=" + prop + ", value=" + entry.getValue(), e);
                }catch(InvocationTargetException e){
                    throw new ConvertException("DecimalFormat can not set property. property=" + prop + ", value=" + entry.getValue(), e);
                }
            }
        }
        return df;
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
        case NUMBER_TO_STRING:
            if(isUseNotApplicable){
                if(obj instanceof Byte){
                    if((notApplicableForByte == null && obj == null)
                        || (notApplicableForByte != null
                                && notApplicableForByte.equals(obj))){
                        return notApplicableStringForByte;
                    }
                }else if(obj instanceof Short){
                    if((notApplicableForShort == null && obj == null)
                        || (notApplicableForShort != null
                                && notApplicableForShort.equals(obj))){
                        return notApplicableStringForShort;
                    }
                }else if(obj instanceof Integer){
                    if((notApplicableForInt == null && obj == null)
                        || (notApplicableForInt != null
                                && notApplicableForInt.equals(obj))){
                        return notApplicableStringForInt;
                    }
                }else if(obj instanceof Long){
                    if((notApplicableForLong == null && obj == null)
                        || (notApplicableForLong != null
                                && notApplicableForLong.equals(obj))){
                        return notApplicableStringForLong;
                    }
                }else if(obj instanceof Float){
                    if((notApplicableForFloat == null && obj == null)
                        || (notApplicableForFloat != null
                                && (notApplicableForFloat.equals(obj))
                                    || (notApplicableForFloat.isNaN() && ((Float)obj).isNaN())
                                    || (notApplicableForFloat.isInfinite() && ((Float)obj).isInfinite()))
                    ){
                        return notApplicableStringForFloat;
                    }
                }else if(obj instanceof Double){
                    if((notApplicableForDouble == null && obj == null)
                        || (notApplicableForDouble != null
                                && (notApplicableForDouble.equals(obj))
                                    || (notApplicableForDouble.isNaN() && ((Double)obj).isNaN())
                                    || (notApplicableForDouble.isInfinite() && ((Double)obj).isInfinite()))
                    ){
                        return notApplicableStringForDouble;
                    }
                }else if(obj instanceof BigInteger){
                    if((notApplicableForBigInteger == null && obj == null)
                        || (notApplicableForBigInteger != null
                                && notApplicableForBigInteger.equals(obj))){
                        return notApplicableStringForBigInteger;
                    }
                }else if(obj instanceof BigDecimal){
                    if((notApplicableForBigDecimal == null && obj == null)
                        || (notApplicableForBigDecimal != null
                                && notApplicableForBigDecimal.equals(obj))){
                        return notApplicableStringForBigDecimal;
                    }
                }
            }
            if(obj == null){
                return createDecimalFormat().format(new Long(0));
            }
            return createDecimalFormat().format(obj);
        case STRING_TO_NUMBER:
            if(isUseNotApplicable){
                if((notApplicableStringForByte == null && obj == null)
                    || (notApplicableStringForByte != null
                            && notApplicableStringForByte.equals(obj))){
                    return notApplicableForByte;
                }
                if((notApplicableStringForShort == null && obj == null)
                    || (notApplicableStringForShort != null
                            && notApplicableStringForShort.equals(obj))){
                    return notApplicableForShort;
                }
                if((notApplicableStringForInt == null && obj == null)
                    || (notApplicableStringForInt != null
                            && notApplicableStringForInt.equals(obj))){
                    return notApplicableForInt;
                }
                if((notApplicableStringForLong == null && obj == null)
                    || (notApplicableStringForLong != null
                            && notApplicableStringForLong.equals(obj))){
                    return notApplicableForLong;
                }
                if((notApplicableStringForFloat == null && obj == null)
                    || (notApplicableStringForFloat != null
                            && notApplicableStringForFloat.equals(obj))){
                    return notApplicableForFloat;
                }
                if((notApplicableStringForDouble == null && obj == null)
                    || (notApplicableStringForDouble != null
                            && notApplicableStringForDouble.equals(obj))){
                    return notApplicableForDouble;
                }
                if((notApplicableStringForBigInteger == null && obj == null)
                    || (notApplicableStringForBigInteger != null
                            && notApplicableStringForBigInteger.equals(obj))){
                    return notApplicableForBigInteger;
                }
                if((notApplicableStringForBigDecimal == null && obj == null)
                    || (notApplicableStringForBigDecimal != null
                            && notApplicableStringForBigDecimal.equals(obj))){
                    return notApplicableForBigDecimal;
                }
            }
            if(obj == null){
                return new Long(0);
            }
            if(obj instanceof String){
                final String val = (String)obj;
                if(DOUBLE_NAN_STR.equals(val)){
                    return new Double(Double.NaN);
                }else if(DOUBLE_NEGATIVE_INFINITY_STR.equals(val)){
                    return new Double(Double.NEGATIVE_INFINITY);
                }else if(DOUBLE_POSITIVE_INFINITY_STR.equals(val)){
                    return new Double(Double.POSITIVE_INFINITY);
                }
            }
            try{
                return createDecimalFormat().parse((String)obj);
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
