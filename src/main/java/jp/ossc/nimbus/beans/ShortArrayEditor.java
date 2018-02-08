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
 * short�z��^��PropertyEditor�N���X�B<p>
 * �J���}��؂�̕������short[]�^�̃I�u�W�F�N�g�ɕϊ�����B<br>
 * �󔒂̓g���������B
 * �󔒂́A{@link java.lang.Character#isWhitespace(char)}�Ŕ��肳���B
 * "&lt;!--"��"--&gt;"�Ɉ͂܂ꂽ������̓R�����g�Ɖ��߂��ꖳ�������B
 * "${"��"}"�Ɉ͂܂ꂽ������́A�����̃V�X�e���v���p�e�B�ƒu�������B<br>
 * short�^��static�萔�����Q�Ƃ��鎖���ł���B<br>
 * �܂��A����l�Ƃ��āAMAX_VALUE�AMIN_VALUE���T�|�[�g����B<br>
 * <p>
 * ��F<br>
 * &nbsp;&nbsp;1,2, 3  <br>
 * &nbsp;&nbsp;4, 5,6 ,&lt;!--7,<br>
 * &nbsp;&nbsp;8,--&gt;9<br>
 * <br>
 * &nbsp;�̂悤�ȕ�����<br>
 * <br>
 * &nbsp;&nbsp;new short[]{1, 2, 34, 5, 6, 9}<br>
 * <br>
 * &nbsp;�̂悤�ɕϊ������B<br>
 *
 * @author M.Takata
 */
public class ShortArrayEditor extends ArrayEditor
 implements java.io.Serializable{
    
    private static final long serialVersionUID = -151047287049598076L;
    
    protected Object createArray(List strList){
        final short [] shortArray = new short[strList.size()];
        for(int i = 0; i < shortArray.length; i++){
            shortArray[i] = ShortEditor.toShort(((String)strList.get(i)).trim(), false);
        }
        return shortArray;
    }
}