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

/**
 * ������ҏW�R���o�[�^�B<p>
 * 
 * @author M.Takata
 */
public class StringEditConverter
 implements StringConverter, java.io.Serializable{
    
    private static final long serialVersionUID = -3531421350654717601L;
    private boolean isTrim;
    private boolean isToLowerCase;
    private boolean isToUpperCase;
    private boolean isToCapitalize;
    private int startIndex = -1;
    private int endIndex = -1;
    private boolean isIgnoreArrayIndexOutOfBounds;
    
    /**
     * �g�������邩�ǂ�����ݒ肷��B<p>
     *
     * @param trim �g��������ꍇtrue
     */
    public void setTrim(boolean trim){
        isTrim = trim;
    }
    
    /**
     * �g�������邩�ǂ����𔻒肷��B<p>
     *
     * @return true�̏ꍇ�A�g��������
     */
    public boolean isTrim(){
        return isTrim;
    }
    
    /**
     * �������ɕϊ����邩�ǂ�����ݒ肷��B<p>
     *
     * @param lower �������ɕϊ�����ꍇtrue
     */
    public void setToLowerCase(boolean lower){
        isToLowerCase = lower;
        if(isToLowerCase && isToUpperCase){
            isToUpperCase = false;
        }
    }
    
    /**
     * �������ɕϊ����邩�ǂ����𔻒肷��B<p>
     *
     * @return true�̏ꍇ�A�������ɕϊ�����
     */
    public boolean isToLowerCase(){
        return isToLowerCase;
    }
    
    /**
     * �啶���ɕϊ����邩�ǂ�����ݒ肷��B<p>
     *
     * @param upper �啶���ɕϊ�����ꍇtrue
     */
    public void setToUpperCase(boolean upper){
        isToUpperCase = upper;
        if(isToLowerCase && isToUpperCase){
            isToLowerCase = false;
        }
    }
    
    /**
     * �啶���ɕϊ����邩�ǂ����𔻒肷��B<p>
     *
     * @return true�̏ꍇ�A�啶���ɕϊ�����
     */
    public boolean isToUpperCase(){
        return isToUpperCase;
    }
    
    /**
     * ��������啶���ɕϊ����邩�ǂ�����ݒ肷��B<p>
     *
     * @param capitalize ��������啶���ɕϊ�����ꍇtrue
     */
    public void setToCapitalize(boolean capitalize){
        isToCapitalize = capitalize;
    }
    
    /**
     * ��������啶���ɕϊ����邩�ǂ����𔻒肷��B<p>
     *
     * @return true�̏ꍇ�A��������啶���ɕϊ�����
     */
    public boolean isToCapitalize(){
        return isToCapitalize;
    }
    
    /**
     * ����������ɂ��邽�߂̊J�n�ʒu��ݒ肷��B<p>
     *
     * @param index ����������ɂ��邽�߂̊J�n�ʒu
     */
    public void setStartIndex(int index){
        startIndex = index;
    }
    
    /**
     * ����������ɂ��邽�߂̊J�n�ʒu���擾����B<p>
     *
     * @return ����������ɂ��邽�߂̊J�n�ʒu
     */
    public int getStartIndex(){
        return startIndex;
    }
    
    /**
     * ����������ɂ��邽�߂̏I���ʒu��ݒ肷��B<p>
     *
     * @param index ����������ɂ��邽�߂̏I���ʒu
     */
    public void setEndIndex(int index){
        endIndex = index;
    }
    
    /**
     * ����������ɂ��邽�߂̏I���ʒu���擾����B<p>
     *
     * @return ����������ɂ��邽�߂̏I���ʒu
     */
    public int getEndIndex(){
        return endIndex;
    }
    
    /**
     * �w�肳�ꂽ����������ɖ����Ȃ��ꍇ�ɁA��O�𔭐������Ȃ��悤�ɂ��邩�ǂ�����ݒ肷��B<p>
     *
     * @param isIgnore ��O�𔭐������Ȃ��悤�ɂ���ꍇtrue
     */
    public void setIgnoreArrayIndexOutOfBounds(boolean isIgnore){
        isIgnoreArrayIndexOutOfBounds = isIgnore;
    }
    
    /**
     * �w�肳�ꂽ����������ɖ����Ȃ��ꍇ�ɁA��O�𔭐������Ȃ��悤�ɂ��邩�ǂ����𔻒肷��B<p>
     *
     * @return ��O�𔭐������Ȃ��悤�ɂ���ꍇtrue
     */
    public boolean isIgnoreArrayIndexOutOfBounds(){
        return isIgnoreArrayIndexOutOfBounds;
    }
    
    public Object convert(Object obj) throws ConvertException{
        return convert(obj == null ? null : obj.toString());
    }
    
    public String convert(String str) throws ConvertException{
        if(str == null || str.length() == 0){
            return str;
        }
        String result = str;
        if(isTrim){
            result = result.trim();
        }
        int sIndex = startIndex;
        int eIndex = endIndex;
        if(isIgnoreArrayIndexOutOfBounds){
            if(sIndex > 0 && sIndex >= result.length()){
                sIndex = result.length();
            }
            if(eIndex > 1 && eIndex > result.length()){
                eIndex = result.length();
            }
        }
        if(sIndex == 0){
            sIndex = -1;
        }
        if(eIndex == result.length()){
            eIndex = -1;
        }
        if(sIndex >= 0 && eIndex >= 0){
            try{
                result = result.substring(sIndex, eIndex);
            }catch(ArrayIndexOutOfBoundsException e){
                throw new ConvertException(e);
            }
        }else if(sIndex >= 0){
            try{
                result = result.substring(sIndex);
            }catch(ArrayIndexOutOfBoundsException e){
                throw new ConvertException(e);
            }
        }else if(eIndex >= 0){
            try{
                result = result.substring(0, eIndex);
            }catch(ArrayIndexOutOfBoundsException e){
                throw new ConvertException(e);
            }
        }
        if(isToLowerCase){
            result = result.toLowerCase();
        }else if(isToUpperCase){
            result = result.toUpperCase();
        }
        if(isToCapitalize){
            result = Character.toUpperCase(result.charAt(0)) + result.substring(1);
        }
        return result;
    }
    
}