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
 * �p���R���o�[�^�B<p>
 * <table border=5>
 *     <tr><th>���p�p��</th><th>�S�p�p��</th></tr>
 *     <tr><td>a</td><td>��</td></tr>
 *     <tr><td>b</td><td>��</td></tr>
 *     <tr><td>c</td><td>��</td></tr>
 *     <tr><td>d</td><td>��</td></tr>
 *     <tr><td>e</td><td>��</td></tr>
 *     <tr><td>f</td><td>��</td></tr>
 *     <tr><td>g</td><td>��</td></tr>
 *     <tr><td>h</td><td>��</td></tr>
 *     <tr><td>i</td><td>��</td></tr>
 *     <tr><td>j</td><td>��</td></tr>
 *     <tr><td>k</td><td>��</td></tr>
 *     <tr><td>l</td><td>��</td></tr>
 *     <tr><td>m</td><td>��</td></tr>
 *     <tr><td>n</td><td>��</td></tr>
 *     <tr><td>o</td><td>��</td></tr>
 *     <tr><td>p</td><td>��</td></tr>
 *     <tr><td>q</td><td>��</td></tr>
 *     <tr><td>r</td><td>��</td></tr>
 *     <tr><td>s</td><td>��</td></tr>
 *     <tr><td>t</td><td>��</td></tr>
 *     <tr><td>u</td><td>��</td></tr>
 *     <tr><td>v</td><td>��</td></tr>
 *     <tr><td>w</td><td>��</td></tr>
 *     <tr><td>x</td><td>��</td></tr>
 *     <tr><td>y</td><td>��</td></tr>
 *     <tr><td>z</td><td>��</td></tr>
 *     <tr><td>A</td><td>�`</td></tr>
 *     <tr><td>B</td><td>�a</td></tr>
 *     <tr><td>C</td><td>�b</td></tr>
 *     <tr><td>D</td><td>�c</td></tr>
 *     <tr><td>E</td><td>�d</td></tr>
 *     <tr><td>F</td><td>�e</td></tr>
 *     <tr><td>G</td><td>�f</td></tr>
 *     <tr><td>H</td><td>�g</td></tr>
 *     <tr><td>I</td><td>�h</td></tr>
 *     <tr><td>J</td><td>�i</td></tr>
 *     <tr><td>K</td><td>�j</td></tr>
 *     <tr><td>L</td><td>�k</td></tr>
 *     <tr><td>M</td><td>�l</td></tr>
 *     <tr><td>N</td><td>�m</td></tr>
 *     <tr><td>O</td><td>�n</td></tr>
 *     <tr><td>P</td><td>�o</td></tr>
 *     <tr><td>Q</td><td>�p</td></tr>
 *     <tr><td>R</td><td>�q</td></tr>
 *     <tr><td>S</td><td>�r</td></tr>
 *     <tr><td>T</td><td>�s</td></tr>
 *     <tr><td>U</td><td>�t</td></tr>
 *     <tr><td>V</td><td>�u</td></tr>
 *     <tr><td>W</td><td>�v</td></tr>
 *     <tr><td>X</td><td>�w</td></tr>
 *     <tr><td>Y</td><td>�x</td></tr>
 *     <tr><td>Z</td><td>�y</td></tr>
 * </table>
 * 
 * @author   M.Takata
 */
public class AlphabetStringConverter extends HankakuZenkakuStringConverter
 implements java.io.Serializable{
    
    private static final long serialVersionUID = 2583585212364793389L;
    
    /**
     * ���p���S�p�ϊ��̉p���R���o�[�^�𐶐�����B<p>
     */
    public AlphabetStringConverter(){
        this(HANKAKU_TO_ZENKAKU);
    }
    
    /**
     * �w�肳�ꂽ�ϊ���ʂ̉p���R���o�[�^�[�𐶐�����B<p>
     *
     * @param type �ϊ����
     * @see #HANKAKU_TO_ZENKAKU
     * @see #ZENKAKU_TO_HANKAKU
     */
    public AlphabetStringConverter(int type){
        super(type);
    }
    
    /**
     * ���p�S�p�ϊ��L�����N�^�z����擾����B<p>
     *
     * @return {@link AlphabetCharacterConverter#CONV_CHARS}
     */
    protected char[][] getHankakuZenkakuChars(){
        return AlphabetCharacterConverter.CONV_CHARS;
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
