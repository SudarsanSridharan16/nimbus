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
import java.math.*;

/**
 * {@link BigInteger}�^��PropertyEditor�N���X�B<p>
 * �N���X���������java.math.BigInteger�^�̃I�u�W�F�N�g�ɕϊ�����B<br>
 * "${"��"}"�Ɉ͂܂ꂽ������́A�����̃V�X�e���v���p�e�B�ƒu�������B<br>
 * <p>
 * ��F<br>
 * &nbsp;&nbsp;12345678901234567890<br>
 * <br>
 * �̂悤�ȕ�����<br>
 * <br>
 * &nbsp;&nbsp;new BigInteger("12345678901234567890")<br>
 * <br>
 * �̂悤�ɕϊ������B<br>
 *
 * @author M.Takata
 */
public class BigIntegerEditor extends PropertyEditorSupport
 implements java.io.Serializable{
    
    private static final long serialVersionUID = -1894982936266167769L;
    
    /**
     * �w�肳�ꂽ���������͂��ăv���p�e�B�l��ݒ肷��B<p>
     *
     * @param text ��͂���镶����
     */
    public void setAsText(String text){
        if(text == null){
            setValue(null);
            return;
        }
        setValue(
            new BigInteger(
                Utility.replaceSystemProperty(text)
            )
        );
    }
    
    /**
     * �v���p�e�B��������擾����B<p>
     *
     * @return �v���p�e�B������
     */
    public String getAsText(){
        final BigInteger val = (BigInteger)getValue();
        return val == null ? null : val.toString();
    }
}
