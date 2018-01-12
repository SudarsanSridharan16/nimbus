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
 * �����R���o�[�^�B<p>
 * <table border=5>
 *     <tr><th>���p����</th><th>�S�p����</th></tr>
 *     <tr><td>0</td><td>�O</td></tr>
 *     <tr><td>1</td><td>�P</td></tr>
 *     <tr><td>2</td><td>�Q</td></tr>
 *     <tr><td>3</td><td>�R</td></tr>
 *     <tr><td>4</td><td>�S</td></tr>
 *     <tr><td>5</td><td>�T</td></tr>
 *     <tr><td>6</td><td>�U</td></tr>
 *     <tr><td>7</td><td>�V</td></tr>
 *     <tr><td>8</td><td>�W</td></tr>
 *     <tr><td>9</td><td>�X</td></tr>
 * </table>
 *
 * @author M.Takata
 */
public class NumberStringConverter extends HankakuZenkakuStringConverter
 implements java.io.Serializable{
    
    private static final long serialVersionUID = -5890416652278253970L;
    
    /**
     * ���p���S�p�ϊ���ʂ̐����R���o�[�^�𐶐�����B<p>
     */
    public NumberStringConverter(){
        super(HANKAKU_TO_ZENKAKU);
    }
    
    /**
     * �����R���o�[�^�[�𐶐����܂��B
     *
     * @param type �ϊ����
     * @see HankakuZenkakuStringConverter#HANKAKU_TO_ZENKAKU
     * @see HankakuZenkakuStringConverter#ZENKAKU_TO_HANKAKU
     */
    public NumberStringConverter(int type){
        super(type);
    }
    
    /**
     * ���p�S�p�ϊ��L�����N�^�z����擾����B<p>
     *
     * @return {@link NumberCharacterConverter#CONV_CHARS}
     */
    protected char[][] getHankakuZenkakuChars(){
        return NumberCharacterConverter.CONV_CHARS;
    }
    
    /**
     * ���p�S�p�ϊ�������z����擾����B<p>
     *
     * @return null
     */
    protected String[][] getHankakuZenkakuStrings(){
        return null;
    }
}
