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

import java.util.*;

/**
 * char�z��^��PropertyEditor�N���X�B<p>
 * �J���}��؂�̕������char[]�^�̃I�u�W�F�N�g�ɕϊ�����B<br>
 * �ŏ��ƍŌ�̋󔒂Ɖ��s�O��̋󔒂̓g���������B
 * �󔒂́A{@link java.lang.Character#isWhitespace(char)}�Ŕ��肳���B
 * �܂��A"&lt;!--"��"--&gt;"�Ɉ͂܂ꂽ������̓R�����g�Ɖ��߂��ꖳ�������B
 * �܂��A"${"��"}"�Ɉ͂܂ꂽ������́A�����̃V�X�e���v���p�e�B�ƒu�������B
 * "${\t}"�A"${\n}"�A"${\r}"�A"${\f}"�́A�G�X�P�[�v�V�[�P���X�Ƃ��Ēu�������B
 * "0x"����n�܂镶����́A16�i�����Ƃ���char�ɕϊ������B
 * "��u"����n�܂镶����́A���j�R�[�h�����Ƃ���char�ɕϊ������B<br>
 * <p>
 * ��F<br>
 * &nbsp;&nbsp;a,b,0x63,<br>
 * &nbsp;&nbsp;d,��u3042, ,&lt;!--7,<br>
 * &nbsp;&nbsp;8,--&gt;9<br>
 * <br>
 * &nbsp;�̂悤�ȕ�����<br>
 * <br>
 * &nbsp;&nbsp;new char[]{'a', 'b', 'c', 'd', '��', ' ', '9'}<br>
 * <br>
 * &nbsp;�̂悤�ɕϊ������B<br>
 *
 * @author M.Takata
 */
public class CharacterArrayEditor extends ArrayEditor
 implements java.io.Serializable{
    
    private static final long serialVersionUID = -6605836013809384384L;
    
    private static final String HEX_PREFIX = "0x";
    private static final String UNICODE_PREFIX = "\\u";
    
    protected Object createArray(List strList){
        final char [] charArray = new char[strList.size()];
        for(int i = 0; i < charArray.length; i++){
            String charStr = (String)strList.get(i);
            if(charStr.length() == 1){
                charArray[i] = charStr.charAt(0);
            }else if(charStr.startsWith(HEX_PREFIX) && charStr.length() > 2){
                charArray[i] = (char)Integer.parseInt(charStr.substring(2), 16);
            }else if(charStr.startsWith(UNICODE_PREFIX)
                 && charStr.length() > 2){
                charStr = Utility.unicodeConvert(charStr);
                if(charStr.length() != 1){
                    throw new IllegalArgumentException(
                        "Not a character. : " + charStr
                    );
                }
                charArray[i] = charStr.charAt(0);
            }else{
                throw new IllegalArgumentException(
                    "Not a character. : " + charStr
                );
            }
        }
        return charArray;
    }
}
