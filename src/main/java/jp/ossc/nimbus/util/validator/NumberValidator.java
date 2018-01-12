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
package jp.ossc.nimbus.util.validator;

import java.math.*;

/**
 * ���l�o���f�[�^�B<p>
 * 
 * @author M.Takata
 */
public class NumberValidator implements Validator, java.io.Serializable{
    
    private static final long serialVersionUID = -1507930380189770984L;
    
    /**
     * null�����e���邩�ǂ����̃t���O�B<p>
     * true�̏ꍇ�A���e����B�f�t�H���g�́Atrue�B<br>
     */
    protected boolean isAllowNull = true;
    
    /**
     * NaN�����e���邩�ǂ����̃t���O�B<p>
     * true�̏ꍇ�A���e����B�f�t�H���g�́Atrue�B<br>
     */
    protected boolean isAllowNaN = true;
    
    /**
     * ����������e���邩�ǂ����̃t���O�B<p>
     * true�̏ꍇ�A���e����B�f�t�H���g�́Atrue�B<br>
     */
    protected boolean isAllowInfinity = true;
    
    /**
     * ���l����������e���邩�ǂ����̃t���O�B<p>
     * true�̏ꍇ�A���e����B�f�t�H���g�́Afalse�B<br>
     */
    protected boolean isAllowNumberString;
    
    /**
     * ���ؒl &gt; �l�����؂���臒l�B<p>
     */
    protected BigDecimal moreThanValue;
    
    /**
     * ���ؒl &gt;= �l�����؂���臒l�B<p>
     */
    protected BigDecimal moreEqualValue;
    
    /**
     * ���ؒl &lt; �l�����؂���臒l�B<p>
     */
    protected BigDecimal lessThanValue;
    
    /**
     * ���ؒl &lt;= �l�����؂���臒l�B<p>
     */
    protected BigDecimal lessEqualValue;
    
    /**
     * ���ؒl == �l�����؂���臒l�B<p>
     */
    protected BigDecimal equalValue;
    
    /**
     * ���ؒl != �l�����؂���臒l�B<p>
     */
    protected BigDecimal notEqualValue;
    
    /**
     * null�����e���邩�ǂ�����ݒ肷��B<p>
     * �f�t�H���g�́Atrue�B<br>
     * 
     * @param isAllow true�̏ꍇ�A���e����
     */
    public void setAllowNull(boolean isAllow){
        isAllowNull = isAllow;
    }
    
    /**
     * null�����e���邩�ǂ����𔻒肷��B<p>
     * 
     * @return ���e����ꍇ�Atrue
     */
    public boolean isAllowNull(){
        return isAllowNull;
    }
    
    /**
     * NaN�����e���邩�ǂ�����ݒ肷��B<p>
     * �f�t�H���g�́Atrue�B<br>
     * 
     * @param isAllow true�̏ꍇ�A���e����
     */
    public void setAllowNaN(boolean isAllow){
        isAllowNaN = isAllow;
    }
    
    /**
     * NaN�����e���邩�ǂ����𔻒肷��B<p>
     * 
     * @return ���e����ꍇ�Atrue
     */
    public boolean isAllowNaN(){
        return isAllowNaN;
    }
    
    /**
     * ����������e���邩�ǂ�����ݒ肷��B<p>
     * �f�t�H���g�́Atrue�B<br>
     * 
     * @param isAllow true�̏ꍇ�A���e����
     */
    public void setAllowInfinity(boolean isAllow){
        isAllowInfinity = isAllow;
    }
    
    /**
     * ����������e���邩�ǂ����𔻒肷��B<p>
     * 
     * @return ���e����ꍇ�Atrue
     */
    public boolean isAllowInfinity(){
        return isAllowInfinity;
    }
    
    /**
     * ���l����������e���邩�ǂ�����ݒ肷��B<p>
     * �f�t�H���g�́Atrue�B<br>
     * 
     * @param isAllow true�̏ꍇ�A���e����
     */
    public void setAllowNumberString(boolean isAllow){
        isAllowNumberString = isAllow;
    }
    
    /**
     * ���l����������e���邩�ǂ����𔻒肷��B<p>
     * 
     * @return ���e����ꍇ�Atrue
     */
    public boolean isAllowNumberString(){
        return isAllowNumberString;
    }
    
    /**
     * ���ؒl &gt; �l�����؂���臒l��ݒ肷��B<p>
     *
     * @param max 臒l
     */
    public void setMoreThanValue(BigDecimal max){
        moreThanValue = max;
    }
    
    /**
     * ���ؒl &gt; �l�����؂���臒l���擾����B<p>
     *
     * @return 臒l
     */
    public BigDecimal getMoreThanValue(){
        return moreThanValue;
    }
    
    /**
     * ���ؒl &gt;= �l�����؂���臒l��ݒ肷��B<p>
     *
     * @param max 臒l
     */
    public void setMoreEqualValue(BigDecimal max){
        moreEqualValue = max;
    }
    
    /**
     * ���ؒl &gt;= �l�����؂���臒l���擾����B<p>
     *
     * @return 臒l
     */
    public BigDecimal getMoreEqualValue(){
        return moreEqualValue;
    }
    
    /**
     * ���ؒl &lt; �l�����؂���臒l��ݒ肷��B<p>
     *
     * @param min 臒l
     */
    public void setLessThanValue(BigDecimal min){
        lessThanValue = min;
    }
    
    /**
     * ���ؒl &lt; �l�����؂���臒l���擾����B<p>
     *
     * @return 臒l
     */
    public BigDecimal getLessThanValue(){
        return lessThanValue;
    }
    
    /**
     * ���ؒl &lt;= �l�����؂���臒l��ݒ肷��B<p>
     *
     * @param min 臒l
     */
    public void setLessEqualValue(BigDecimal min){
        lessEqualValue = min;
    }
    
    /**
     * ���ؒl &lt;= �l�����؂���臒l���擾����B<p>
     *
     * @return 臒l
     */
    public BigDecimal getLessEqualValue(){
        return lessEqualValue;
    }
    
    /**
     * ���ؒl == �l�����؂���臒l��ݒ肷��B<p>
     *
     * @param eq 臒l
     */
    public void setEqualValue(BigDecimal eq){
        equalValue = eq;
    }
    
    /**
     * ���ؒl == �l�����؂���臒l���擾����B<p>
     *
     * @return 臒l
     */
    public BigDecimal getEqualValue(){
        return equalValue;
    }
    
    /**
     * ���ؒl != �l�����؂���臒l��ݒ肷��B<p>
     *
     * @param neq 臒l
     */
    public void setNotEqualValue(BigDecimal neq){
        notEqualValue = neq;
    }
    
    /**
     * ���ؒl != �l�����؂���臒l���擾����B<p>
     *
     * @return 臒l
     */
    public BigDecimal getNotEqualValue(){
        return notEqualValue;
    }
    
    /**
     * �w�肳�ꂽ�I�u�W�F�N�g���K�؂Ȑ��l���ǂ��������؂���B<p>
     *
     * @param obj ���ؑΏۂ̃I�u�W�F�N�g
     * @return ���،��ʁB���ؐ����̏ꍇtrue
     * @exception ValidateException ���؂Ɏ��s�����ꍇ
     */
    public boolean validate(Object obj) throws ValidateException{
        if(obj == null){
            return isAllowNull;
        }
        if(!(obj instanceof Number)){
            if(obj instanceof String && isAllowNumberString){
                return validateString((String)obj);
            }
            return false;
        }
        if(obj instanceof Byte){
            return validate(((Byte)obj).byteValue());
        }else if(obj instanceof Short){
            return validate(((Short)obj).shortValue());
        }else if(obj instanceof Integer){
            return validate(((Integer)obj).intValue());
        }else if(obj instanceof Long){
            return validate(((Long)obj).longValue());
        }else if(obj instanceof Float){
            return validate(((Float)obj).floatValue());
        }else if(obj instanceof Double){
            return validate(((Double)obj).doubleValue());
        }else if(obj instanceof BigInteger){
            return validateBigInteger((BigInteger)obj);
        }else if(obj instanceof BigDecimal){
            return validateBigDecimal((BigDecimal)obj);
        }
        throw new ValidateException(
            "Not support number." + obj.getClass().getName()
        );
    }
    
    /**
     * �w�肳�ꂽ�l���K�؂Ȑ��l���ǂ��������؂���B<p>
     *
     * @param val ���ؑΏۂ̒l
     * @return ���،��ʁB���ؐ����̏ꍇtrue
     * @exception ValidateException ���؂Ɏ��s�����ꍇ
     */
    public boolean validate(byte val) throws ValidateException{
        if(moreThanValue != null){
            if(moreThanValue.byteValue() >= val){
                return false;
            }
        }
        if(moreEqualValue != null){
            if(moreEqualValue.byteValue() > val){
                return false;
            }
        }
        if(lessThanValue != null){
            if(lessThanValue.byteValue() <= val){
                return false;
            }
        }
        if(lessEqualValue != null){
            if(lessEqualValue.byteValue() < val){
                return false;
            }
        }
        if(equalValue != null){
            if(equalValue.byteValue() != val){
                return false;
            }
        }
        if(notEqualValue != null){
            if(notEqualValue.byteValue() == val){
                return false;
            }
        }
        return true;
    }
    
    /**
     * �w�肳�ꂽ�l���K�؂Ȑ��l���ǂ��������؂���B<p>
     *
     * @param val ���ؑΏۂ̒l
     * @return ���،��ʁB���ؐ����̏ꍇtrue
     * @exception ValidateException ���؂Ɏ��s�����ꍇ
     */
    public boolean validate(short val) throws ValidateException{
        if(moreThanValue != null){
            if(moreThanValue.shortValue() >= val){
                return false;
            }
        }
        if(moreEqualValue != null){
            if(moreEqualValue.shortValue() > val){
                return false;
            }
        }
        if(lessThanValue != null){
            if(lessThanValue.shortValue() <= val){
                return false;
            }
        }
        if(lessEqualValue != null){
            if(lessEqualValue.shortValue() < val){
                return false;
            }
        }
        if(equalValue != null){
            if(equalValue.shortValue() != val){
                return false;
            }
        }
        if(notEqualValue != null){
            if(notEqualValue.shortValue() == val){
                return false;
            }
        }
        return true;
    }
    
    /**
     * �w�肳�ꂽ�l���K�؂Ȑ��l���ǂ��������؂���B<p>
     *
     * @param val ���ؑΏۂ̒l
     * @return ���،��ʁB���ؐ����̏ꍇtrue
     * @exception ValidateException ���؂Ɏ��s�����ꍇ
     */
    public boolean validate(int val) throws ValidateException{
        if(moreThanValue != null){
            if(moreThanValue.intValue() >= val){
                return false;
            }
        }
        if(moreEqualValue != null){
            if(moreEqualValue.intValue() > val){
                return false;
            }
        }
        if(lessThanValue != null){
            if(lessThanValue.intValue() <= val){
                return false;
            }
        }
        if(lessEqualValue != null){
            if(lessEqualValue.intValue() < val){
                return false;
            }
        }
        if(equalValue != null){
            if(equalValue.intValue() != val){
                return false;
            }
        }
        if(notEqualValue != null){
            if(notEqualValue.intValue() == val){
                return false;
            }
        }
        return true;
    }
    
    /**
     * �w�肳�ꂽ�l���K�؂Ȑ��l���ǂ��������؂���B<p>
     *
     * @param val ���ؑΏۂ̒l
     * @return ���،��ʁB���ؐ����̏ꍇtrue
     * @exception ValidateException ���؂Ɏ��s�����ꍇ
     */
    public boolean validate(long val) throws ValidateException{
        if(moreThanValue != null){
            if(moreThanValue.longValue() >= val){
                return false;
            }
        }
        if(moreEqualValue != null){
            if(moreEqualValue.longValue() > val){
                return false;
            }
        }
        if(lessThanValue != null){
            if(lessThanValue.longValue() <= val){
                return false;
            }
        }
        if(lessEqualValue != null){
            if(lessEqualValue.longValue() < val){
                return false;
            }
        }
        if(equalValue != null){
            if(equalValue.longValue() != val){
                return false;
            }
        }
        if(notEqualValue != null){
            if(notEqualValue.longValue() == val){
                return false;
            }
        }
        return true;
    }
    
    /**
     * �w�肳�ꂽ�l���K�؂Ȑ��l���ǂ��������؂���B<p>
     *
     * @param val ���ؑΏۂ̒l
     * @return ���،��ʁB���ؐ����̏ꍇtrue
     * @exception ValidateException ���؂Ɏ��s�����ꍇ
     */
    public boolean validate(float val) throws ValidateException{
        if(Float.isNaN(val)){
            return isAllowNaN;
        }
        if(Float.isInfinite(val)){
            return isAllowInfinity;
        }
        if(moreThanValue != null){
            if(moreThanValue.floatValue() >= val){
                return false;
            }
        }
        if(moreEqualValue != null){
            if(moreEqualValue.floatValue() > val){
                return false;
            }
        }
        if(lessThanValue != null){
            if(lessThanValue.floatValue() <= val){
                return false;
            }
        }
        if(lessEqualValue != null){
            if(lessEqualValue.floatValue() < val){
                return false;
            }
        }
        if(equalValue != null){
            if(equalValue.floatValue() != val){
                return false;
            }
        }
        if(notEqualValue != null){
            if(notEqualValue.floatValue() == val){
                return false;
            }
        }
        return true;
    }
    
    /**
     * �w�肳�ꂽ�l���K�؂Ȑ��l���ǂ��������؂���B<p>
     *
     * @param val ���ؑΏۂ̒l
     * @return ���،��ʁB���ؐ����̏ꍇtrue
     * @exception ValidateException ���؂Ɏ��s�����ꍇ
     */
    public boolean validate(double val) throws ValidateException{
        if(Double.isNaN(val)){
            return isAllowNaN;
        }
        if(Double.isInfinite(val)){
            return isAllowInfinity;
        }
        if(moreThanValue != null){
            if(moreThanValue.doubleValue() >= val){
                return false;
            }
        }
        if(moreEqualValue != null){
            if(moreEqualValue.doubleValue() > val){
                return false;
            }
        }
        if(lessThanValue != null){
            if(lessThanValue.doubleValue() <= val){
                return false;
            }
        }
        if(lessEqualValue != null){
            if(lessEqualValue.doubleValue() < val){
                return false;
            }
        }
        if(equalValue != null){
            if(equalValue.doubleValue() != val){
                return false;
            }
        }
        if(notEqualValue != null){
            if(notEqualValue.doubleValue() == val){
                return false;
            }
        }
        return true;
    }
    
    /**
     * �w�肳�ꂽ�l���K�؂Ȑ��l���ǂ��������؂���B<p>
     *
     * @param val ���ؑΏۂ̒l
     * @return ���،��ʁB���ؐ����̏ꍇtrue
     * @exception ValidateException ���؂Ɏ��s�����ꍇ
     */
    protected boolean validateBigInteger(BigInteger val) throws ValidateException{
        if(val == null){
            return isAllowNull;
        }
        
        if(moreThanValue != null){
            final int comp = moreThanValue.toBigInteger().compareTo(val);
            if(comp >= 0){
                return false;
            }
        }
        if(moreEqualValue != null){
            final int comp = moreEqualValue.toBigInteger().compareTo(val);
            if(comp > 0){
                return false;
            }
        }
        if(lessThanValue != null){
            final int comp = lessThanValue.toBigInteger().compareTo(val);
            if(comp <= 0){
                return false;
            }
        }
        if(lessEqualValue != null){
            final int comp = lessEqualValue.toBigInteger().compareTo(val);
            if(comp < 0){
                return false;
            }
        }
        if(equalValue != null){
            final int comp = equalValue.toBigInteger().compareTo(val);
            if(comp != 0){
                return false;
            }
        }
        if(notEqualValue != null){
            final int comp = notEqualValue.toBigInteger().compareTo(val);
            if(comp == 0){
                return false;
            }
        }
        return true;
    }
    
    /**
     * �w�肳�ꂽ�l���K�؂Ȑ��l���ǂ��������؂���B<p>
     *
     * @param val ���ؑΏۂ̒l
     * @return ���،��ʁB���ؐ����̏ꍇtrue
     * @exception ValidateException ���؂Ɏ��s�����ꍇ
     */
    protected boolean validateBigDecimal(BigDecimal val) throws ValidateException{
        if(val == null){
            return isAllowNull;
        }
        
        if(moreThanValue != null){
            final int comp = moreThanValue.compareTo(val);
            if(comp >= 0){
                return false;
            }
        }
        if(moreEqualValue != null){
            final int comp = moreEqualValue.compareTo(val);
            if(comp > 0){
                return false;
            }
        }
        if(lessThanValue != null){
            final int comp = lessThanValue.compareTo(val);
            if(comp <= 0){
                return false;
            }
        }
        if(lessEqualValue != null){
            final int comp = lessEqualValue.compareTo(val);
            if(comp < 0){
                return false;
            }
        }
        if(equalValue != null){
            final int comp = equalValue.compareTo(val);
            if(comp != 0){
                return false;
            }
        }
        if(notEqualValue != null){
            final int comp = notEqualValue.compareTo(val);
            if(comp == 0){
                return false;
            }
        }
        return true;
    }
    
    /**
     * �w�肳�ꂽ�����񂪓K�؂Ȑ��l�����񂩂ǂ��������؂���B<p>
     *
     * @param val ���ؑΏۂ̒l
     * @return ���،��ʁB���ؐ����̏ꍇtrue
     * @exception ValidateException ���؂Ɏ��s�����ꍇ
     */
    protected boolean validateString(String val) throws ValidateException{
        if(val == null){
            return isAllowNull;
        }
        
        if(Double.toString(Double.NaN).equals(val)){
            return isAllowNaN;
        }
        if(Double.toString(Double.NEGATIVE_INFINITY).equals(val)
            || Double.toString(Double.POSITIVE_INFINITY).equals(val)){
            return isAllowInfinity;
        }
        
        try{
            return validateBigDecimal(new BigDecimal(val));
        }catch(NumberFormatException e){
            return false;
        }
    }
}
