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

import java.util.regex.*;

/**
 * ���K�\��������o���f�[�^�B<p>
 * 
 * @author M.Takata
 */
public class PatternStringValidator extends AbstractStringValidator
 implements java.io.Serializable{
    
    private static final long serialVersionUID = 3525013552708443644L;
    
    /**
     * ����������؂��鐳�K�\���B<p>
     */
    protected Pattern pattern;
    
    /**
     * ���؂Ɏg�p���鐳�K�\���p�^�[����ݒ肷��B<p>
     *
     * @param pattern ���K�\���p�^�[��
     */
    public void setPattern(Pattern pattern){
        this.pattern = pattern;
    }
    
    /**
     * ���؂Ɏg�p���鐳�K�\���p�^�[�����擾����B<p>
     *
     * @return ���K�\���p�^�[��
     */
    public Pattern getPattern(){
        return pattern;
    }
    
    /**
     * ���؂Ɏg�p���鐳�K�\���p�^�[���������ݒ肷��B<p>
     *
     * @param pattern ���K�\���p�^�[��������
     */
    public void setPatternString(String pattern){
        this.pattern = Pattern.compile(pattern);
    }
    
    /**
     * ���؂Ɏg�p���鐳�K�\���p�^�[���������ݒ肷��B<p>
     *
     * @param pattern ���K�\���p�^�[��������
     * @param flags �}�b�`�t���O
     */
    public void setPatternString(String pattern, int flags){
        this.pattern = Pattern.compile(pattern, flags);
    }
    
    /**
     * ���؂Ɏg�p���鐳�K�\���p�^�[����������擾����B<p>
     *
     * @return ���K�\���p�^�[��������
     */
    public String getPatternString(){
        return pattern == null ? null : pattern.pattern();
    }
    
    /**
     * �w�肳�ꂽ�����񂪐��K�\���Ƀ}�b�`���邩�ǂ��������؂���B<p>
     *
     * @param str ���ؑΏۂ̕�����
     * @return ���،��ʁB���ؐ����̏ꍇtrue
     * @exception ValidateException ���؂Ɏ��s�����ꍇ
     */
    protected boolean validateString(String str) throws ValidateException{
        if(pattern != null){
            if(!pattern.matcher(str).matches()){
                return false;
            }
        }
        return true;
    }
}