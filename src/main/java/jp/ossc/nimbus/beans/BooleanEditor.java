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
package jp.ossc.nimbus.beans;

import java.beans.*;

/**
 * {@link Boolean}�^��PropertyEditor�N���X�B<p>
 * �������java.lang.Boolean�^�̃I�u�W�F�N�g�ɕϊ�����B<br>
 * "${"��"}"�Ɉ͂܂ꂽ������́A�����̃V�X�e���v���p�e�B�ƒu�������B<br>
 * <p>
 * ��F<br>
 * &nbsp;&nbsp;true �܂��� 1�Aon�Ayes<br>
 * <br>
 * �̂悤�ȕ�����<br>
 * <br>
 * &nbsp;&nbsp;true<br>
 * <br>
 * �̂悤�ɕϊ������B<br>
 *
 * @author M.Takata
 */
public class BooleanEditor extends PropertyEditorSupport
 implements java.io.Serializable{
    
    private static final long serialVersionUID = 1833962710671752692L;
    
    public static final int AS_TEXT_TYPE_BOOLEAN = 1;
    public static final int AS_TEXT_TYPE_NUMBER  = 2;
    public static final int AS_TEXT_TYPE_ON_OFF  = 3;
    public static final int AS_TEXT_TYPE_YES_NO  = 4;
    
    private int asTextType = AS_TEXT_TYPE_BOOLEAN;
    
    /**
     * �v���p�e�B�l���v���p�e�B������ɕϊ����鎞�̕ϊ���ʂ�ݒ肷��B<p>
     *
     * @param type �ϊ����
     * @see #AS_TEXT_TYPE_BOOLEAN
     * @see #AS_TEXT_TYPE_NUMBER
     * @see #AS_TEXT_TYPE_ON_OFF
     * @see #AS_TEXT_TYPE_YES_NO
     */
    public void setAsTextType(int type){
        asTextType = type;
    }
    
    /**
     * �v���p�e�B�l���v���p�e�B������ɕϊ����鎞�̕ϊ���ʂ��擾����B<p>
     *
     * @return �ϊ����
     */
    public int getAsTextType(){
        return asTextType;
    }
    
    /**
     * �w�肳�ꂽ���������͂��ăv���p�e�B�l��ݒ肷��B<p>
     *
     * @param text ��͂���镶����
     */
    public void setAsText(String text){
        if(text == null){
            setValue(Boolean.FALSE);
            return;
        }
        setValue(
            toBoolean(
                Utility.replaceSystemProperty(text)
            ) ? Boolean.TRUE : Boolean.FALSE
        );
    }
    
    /**
     * �v���p�e�B��������擾����B<p>
     *
     * @return �v���p�e�B������
     */
    public String getAsText(){
        final Boolean bool = (Boolean)getValue();
        return toText(asTextType, bool == null ? false : bool.booleanValue());
    }
    
    public static boolean toBoolean(String value){ 
        return ((value != null)
            && (value.equalsIgnoreCase("true")
                || value.equals("1")
                || value.equalsIgnoreCase("on")
                || value.equalsIgnoreCase("yes")));
    }
    
    public static String toText(int asTextType, boolean value){ 
        switch(asTextType){
        case AS_TEXT_TYPE_NUMBER:
            return value ? "1" :  "0";
        case AS_TEXT_TYPE_ON_OFF:
            return value ? "on" :  "off";
        case AS_TEXT_TYPE_YES_NO:
            return value ? "yes" :  "no";
        case AS_TEXT_TYPE_BOOLEAN:
        default:
            return value ? "true" : "false";
        }
    }
}
