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
 * char�^��PropertyEditor�N���X�B<p>
 * �������char�^�̃I�u�W�F�N�g�ɕϊ�����B<br>
 * "${"��"}"�Ɉ͂܂ꂽ������́A�����̃V�X�e���v���p�e�B�ƒu�������B<br>
 * "${\t}"�A"${\n}"�A"${\r}"�A"${\f}"�́A�G�X�P�[�v�V�[�P���X�Ƃ��Ēu�������B<br>
 * "0x"����n�܂镶����́A16�i�����Ƃ���char�ɕϊ������B
 * "��u"����n�܂镶����́A���j�R�[�h�����Ƃ���char�ɕϊ������B<br>
 * <p>
 * ��F<br>
 * &nbsp;&nbsp;a<br>
 * <br>
 * �̂悤�ȕ�����<br>
 * <br>
 * &nbsp;&nbsp;'a'<br>
 * <br>
 * �̂悤�ɕϊ������B<br>
 *
 * @author M.Takata
 */
public class CharacterEditor extends PropertyEditorSupport
 implements java.io.Serializable{
    
    private static final long serialVersionUID = 8830930843268555968L;
    
    private static final String HEX_PREFIX = "0x";
    private static final String UNICODE_PREFIX = "\\u";
    
    /**
     * �w�肳�ꂽ���������͂��ăv���p�e�B�l��ݒ肷��B<p>
     *
     * @param text ��͂���镶����
     */
    public void setAsText(String text){
        String charStr = Utility.replaceSystemProperty(text);
        if(charStr.length() == 1){
            setValue(new Character(charStr.charAt(0)));
        }else if(charStr.startsWith(HEX_PREFIX) && charStr.length() > 2){
            setValue(
                new Character((char)Integer.parseInt(charStr.substring(2), 16))
            );
        }else if(charStr.startsWith(UNICODE_PREFIX)
             && charStr.length() > 2){
            charStr = Utility.unicodeConvert(charStr);
            if(charStr.length() != 1){
                throw new IllegalArgumentException(
                    "Not a character. : " + charStr
                );
            }
            setValue(new Character(charStr.charAt(0)));
        }else{
            throw new IllegalArgumentException(
                "Not a character. : " + charStr
            );
        }
    }
    
    /**
     * �v���p�e�B��������擾����B<p>
     *
     * @return �v���p�e�B������
     */
    public String getAsText(){
        return String.valueOf(((Character)getValue()).charValue());
    }
}