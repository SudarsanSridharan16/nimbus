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

/**
 * ������o���f�[�^���ۃN���X�B<p>
 * 
 * @author M.Takata
 */
public abstract class AbstractStringValidator
 implements Validator, java.io.Serializable{
    
    private static final long serialVersionUID = -468946068283281754L;
    
    /**
     * null�����e���邩�ǂ����̃t���O�B<p>
     * true�̏ꍇ�A���e����B�f�t�H���g�́Atrue�B<br>
     */
    protected boolean isAllowNull = true;
    
    /**
     * �󕶎������e���邩�ǂ����̃t���O�B<p>
     * true�̏ꍇ�A���e����B�f�t�H���g�́Afalse�B<br>
     */
    protected boolean isAllowEmpty;
    
    /**
     * String�ȊO�̃I�u�W�F�N�g�����e���邩�ǂ����̃t���O�B<p>
     * true�̏ꍇ�AtoString()�ŕ�����ɕϊ����Č��؂���B�f�t�H���g�́Afalse�B<br>
     */
    protected boolean isAllowObject;
    
    /**
     * �󔒂��g�������邩�ǂ����̃t���O�B<p>
     * true�̏ꍇ�A�g�������Č��؂���B�f�t�H���g�́Afalse�B<br>
     */
    protected boolean isTrim;
    
    /**
     * �󔒂��g��������ꍇ�̋󔒕����̔z��B<p>
     * �ݒ肳��Ă��Ȃ��ꍇ�́A{@link Character#isWhitespace(char)}���g�p����B<br>
     */
    protected char[] whiteSpaceCharacters;
    
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
     * �󕶎������e���邩�ǂ�����ݒ肷��B<p>
     * �f�t�H���g�́Afalse�B<br>
     * 
     * @param isAllow true�̏ꍇ�A���e����
     */
    public void setAllowEmpty(boolean isAllow){
        isAllowEmpty = isAllow;
    }
    
    /**
     * �󕶎������e���邩�ǂ����𔻒肷��B<p>
     * 
     * @return ���e����ꍇ�Atrue
     */
    public boolean isAllowEmpty(){
        return isAllowEmpty;
    }
    
    /**
     * String�ȊO�̃I�u�W�F�N�g�����e���邩�ǂ�����ݒ肷��B<p>
     * true�̏ꍇ�AtoString()�ŕ�����ɕϊ����Č��؂���B<br>
     * �f�t�H���g�́Afalse�B<br>
     *
     * @param isAllow true�̏ꍇ�A���e����
     */
    public void setAllowObject(boolean isAllow){
        isAllowObject = isAllow;
    }
    
    /**
     * String�ȊO�̃I�u�W�F�N�g�����e���邩�ǂ����𔻒肷��B<p>
     *
     * @return ���e����ꍇ�Atrue
     */
    public boolean isAllowObject(){
        return isAllowObject;
    }
    
    /**
     * �󔒂��g�������邩�ǂ�����ݒ肷��B<p>
     * true�̏ꍇ�A�g�������Č��؂���B<br>
     * �f�t�H���g�́Afalse�B<br>
     *
     * @param trim true�̏ꍇ�A�g��������
     */
    public void setTrim(boolean trim){
        isTrim = trim;
    }
    
    /**
     * �󔒂��g�������邩�ǂ����𔻒肷��B<p>
     *
     * @return �g��������ꍇ�Atrue
     */
    public boolean isTrim(){
        return isTrim;
    }
    
    /**
     * �󔒂��g��������ꍇ�̋󔒕�����ݒ肷��B<p>
     * �ݒ肳��Ă��Ȃ��ꍇ�́A{@link Character#isWhitespace(char)}���g�p����B<br>
     * 
     * @param chars �󔒂��g��������ꍇ�̋󔒕����z��
     */
    public void setWhiteSpaceCharacters(char[] chars){
        whiteSpaceCharacters = chars;
    }
    
    /**
     * �󔒂��g��������ꍇ�̋󔒕������擾����B<p>
     * 
     * @return �󔒂��g��������ꍇ�̋󔒕����z��
     */
    public char[] getWhiteSpaceCharacters(){
        return whiteSpaceCharacters;
    }
    
    /**
     * �w�肳�ꂽ�I�u�W�F�N�g�����؂���B<p>
     * null�`�F�b�N�AString���ǂ����̃`�F�b�N�A�󕶎��`�F�b�N��ʉ߂��āA{@link #validateString(String)}���Ăяo���B<br>
     *
     * @param obj ���ؑΏۂ̃I�u�W�F�N�g
     * @return ���،��ʁB���ؐ����̏ꍇtrue
     * @exception ValidateException ���؂Ɏ��s�����ꍇ
     * @see #validateString(String)
     */
    public boolean validate(Object obj) throws ValidateException{
        if(obj == null){
            return isAllowNull;
        }
        String str = null;
        if(obj instanceof String){
            str = (String)obj;
        }else{
            if(!isAllowObject){
                return false;
            }
            str = obj.toString();
        }
        if(isTrim){
            str = trim(str);
        }
        if(str.length() == 0 && isAllowEmpty){
            return true;
        }
        return validateString(str);
    }
    
    /**
     * �w�肳�ꂽ����������؂���B<p>
     *
     * @param str ���ؑΏۂ̕�����
     * @return ���،��ʁB���ؐ����̏ꍇtrue
     * @exception ValidateException ���؂Ɏ��s�����ꍇ
     */
    protected abstract boolean validateString(String str)
     throws ValidateException;
    
    /**
     * �w�肳�ꂽ������̑O��̋󔒂��g��������B<p>
     *
     * @param str �g�����Ώە�����
     * @return �g������̕�����
     */
    protected String trim(String str){
        String result = str;
        for(int i = 0, max = result.length(); i < max; i++){
            final char c = result.charAt(i);
            if(!isWhitespace(c)){
                result = result.substring(i);
                break;
            }
        }
        for(int i = result.length(); --i >= 0;){
            final char c = result.charAt(i);
            if(!isWhitespace(c)){
                result = result.substring(0, i + 1);
                break;
            }
        }
        return result;
    }
    
    /**
     * �w�肳�ꂽ�������󔒂��ǂ����𔻒肷��B<p>
     *
     * @param c �Ώە���
     * @return �󔒂̏ꍇ�Atrue
     */
    protected boolean isWhitespace(char c){
        if(whiteSpaceCharacters == null || whiteSpaceCharacters.length == 0){
            return Character.isWhitespace(c);
        }
        for(int i = 0; i < whiteSpaceCharacters.length; i++){
            if(c == whiteSpaceCharacters[i]){
                return true;
            }
        }
        return false;
    }
}