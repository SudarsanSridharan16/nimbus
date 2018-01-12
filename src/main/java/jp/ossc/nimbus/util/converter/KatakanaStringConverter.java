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
 * �J�^�J�i�R���o�[�^�B<p>
 * <table border=5>
 *     <tr><th>���p�J�i</th><th>�S�p�J�i</th></tr>
 *     <tr><td>�</td><td>�B</td></tr>
 *     <tr><td>�</td><td>�u</td></tr>
 *     <tr><td>�</td><td>�v</td></tr>
 *     <tr><td>�</td><td>�A</td></tr>
 *     <tr><td>�</td><td>�E</td></tr>
 *     <tr><td>�</td><td>��</td></tr>
 *     <tr><td>�</td><td>�@</td></tr>
 *     <tr><td>�</td><td>�B</td></tr>
 *     <tr><td>�</td><td>�D</td></tr>
 *     <tr><td>�</td><td>�F</td></tr>
 *     <tr><td>�</td><td>�H</td></tr>
 *     <tr><td>�</td><td>��</td></tr>
 *     <tr><td>�</td><td>��</td></tr>
 *     <tr><td>�</td><td>��</td></tr>
 *     <tr><td>�</td><td>�b</td></tr>
 *     <tr><td>�</td><td>�[</td></tr>
 *     <tr><td>�</td><td>�A</td></tr>
 *     <tr><td>�</td><td>�C</td></tr>
 *     <tr><td>�</td><td>�E</td></tr>
 *     <tr><td>�</td><td>�G</td></tr>
 *     <tr><td>�</td><td>�I</td></tr>
 *     <tr><td>�</td><td>�J</td></tr>
 *     <tr><td>�</td><td>�L</td></tr>
 *     <tr><td>�</td><td>�N</td></tr>
 *     <tr><td>�</td><td>�P</td></tr>
 *     <tr><td>�</td><td>�R</td></tr>
 *     <tr><td>�</td><td>�T</td></tr>
 *     <tr><td>�</td><td>�V</td></tr>
 *     <tr><td>�</td><td>�X</td></tr>
 *     <tr><td>�</td><td>�Z</td></tr>
 *     <tr><td>�</td><td>�\</td></tr>
 *     <tr><td>�</td><td>�^</td></tr>
 *     <tr><td>�</td><td>�`</td></tr>
 *     <tr><td>�</td><td>�c</td></tr>
 *     <tr><td>�</td><td>�e</td></tr>
 *     <tr><td>�</td><td>�g</td></tr>
 *     <tr><td>�</td><td>�i</td></tr>
 *     <tr><td>�</td><td>�j</td></tr>
 *     <tr><td>�</td><td>�k</td></tr>
 *     <tr><td>�</td><td>�l</td></tr>
 *     <tr><td>�</td><td>�m</td></tr>
 *     <tr><td>�</td><td>�n</td></tr>
 *     <tr><td>�</td><td>�q</td></tr>
 *     <tr><td>�</td><td>�t</td></tr>
 *     <tr><td>�</td><td>�w</td></tr>
 *     <tr><td>�</td><td>�z</td></tr>
 *     <tr><td>�</td><td>�}</td></tr>
 *     <tr><td>�</td><td>�~</td></tr>
 *     <tr><td>�</td><td>��</td></tr>
 *     <tr><td>�</td><td>��</td></tr>
 *     <tr><td>�</td><td>��</td></tr>
 *     <tr><td>�</td><td>��</td></tr>
 *     <tr><td>�</td><td>��</td></tr>
 *     <tr><td>�</td><td>��</td></tr>
 *     <tr><td>�</td><td>��</td></tr>
 *     <tr><td>�</td><td>��</td></tr>
 *     <tr><td>�</td><td>��</td></tr>
 *     <tr><td>�</td><td>��</td></tr>
 *     <tr><td>�</td><td>��</td></tr>
 *     <tr><td>�</td><td>��</td></tr>
 *     <tr><td>�</td><td>��</td></tr>
 *     <tr><td>��</td><td>��</td></tr>
 *     <tr><td>�</td><td>�J</td></tr>
 *     <tr><td>�</td><td>�K</td></tr>
 *     <tr><td>��</td><td>�K</td></tr>
 *     <tr><td>��</td><td>�M</td></tr>
 *     <tr><td>��</td><td>�O</td></tr>
 *     <tr><td>��</td><td>�Q</td></tr>
 *     <tr><td>��</td><td>�S</td></tr>
 *     <tr><td>��</td><td>�U</td></tr>
 *     <tr><td>��</td><td>�W</td></tr>
 *     <tr><td>��</td><td>�Y</td></tr>
 *     <tr><td>��</td><td>�[</td></tr>
 *     <tr><td>��</td><td>�]</td></tr>
 *     <tr><td>��</td><td>�_</td></tr>
 *     <tr><td>��</td><td>�a</td></tr>
 *     <tr><td>��</td><td>�d</td></tr>
 *     <tr><td>��</td><td>�f</td></tr>
 *     <tr><td>��</td><td>�h</td></tr>
 *     <tr><td>��</td><td>�o</td></tr>
 *     <tr><td>��</td><td>�r</td></tr>
 *     <tr><td>��</td><td>�u</td></tr>
 *     <tr><td>��</td><td>�x</td></tr>
 *     <tr><td>��</td><td>�{</td></tr>
 *     <tr><td>��</td><td>�p</td></tr>
 *     <tr><td>��</td><td>�s</td></tr>
 *     <tr><td>��</td><td>�v</td></tr>
 *     <tr><td>��</td><td>�y</td></tr>
 *     <tr><td>��</td><td>�|</td></tr>
 * </table>
 * 
 * @author M.Takata
 */
public class KatakanaStringConverter extends HankakuZenkakuStringConverter
 implements java.io.Serializable{
    
    private static final long serialVersionUID = -5689893283542592612L;
    
    /**
     * [���_(�����_)�t�����p�J�i][�S�p�J�i] �̔z��B
     */
    protected final static String CONV_STRS[][] = {
        {"\uFF73\uFF9E","\u30F4"}, //��
        {"\uFF76\uFF9E","\u30AC"}, //�K
        {"\uFF77\uFF9E","\u30AE"}, //�M
        {"\uFF78\uFF9E","\u30B0"}, //�O
        {"\uFF79\uFF9E","\u30B2"}, //�Q
        {"\uFF7A\uFF9E","\u30B4"}, //�S
        {"\uFF7B\uFF9E","\u30B6"}, //�U
        {"\uFF7C\uFF9E","\u30B8"}, //�W
        {"\uFF7D\uFF9E","\u30BA"}, //�Y
        {"\uFF7E\uFF9E","\u30BC"}, //�[
        {"\uFF7F\uFF9E","\u30BE"}, //�]
        {"\uFF80\uFF9E","\u30C0"}, //�_
        {"\uFF81\uFF9E","\u30C2"}, //�a
        {"\uFF82\uFF9E","\u30C5"}, //�d
        {"\uFF83\uFF9E","\u30C7"}, //�f
        {"\uFF84\uFF9E","\u30C9"}, //�h
        {"\uFF8A\uFF9E","\u30D0"}, //�o
        {"\uFF8B\uFF9E","\u30D3"}, //�r
        {"\uFF8C\uFF9E","\u30D6"}, //�u
        {"\uFF8D\uFF9E","\u30D9"}, //�x
        {"\uFF8E\uFF9E","\u30DC"}, //�{
        {"\uFF8A\uFF9F","\u30D1"}, //�p
        {"\uFF8B\uFF9F","\u30D4"}, //�s
        {"\uFF8C\uFF9F","\u30D7"}, //�v
        {"\uFF8D\uFF9F","\u30DA"}, //�y
        {"\uFF8E\uFF9F","\u30DD"}  //�|
    };
    
    /**
     * ���p���S�p�ϊ���ʂ̃J�^�J�i�R���o�[�^�𐶐�����B<p>
     */
    public KatakanaStringConverter(){
        super(HANKAKU_TO_ZENKAKU);
    }
    
    /**
     * �J�^�J�i�R���o�[�^�𐶐�����B<p>
     *
     * @param type �ϊ����
     * @see HankakuZenkakuStringConverter#HANKAKU_TO_ZENKAKU
     * @see HankakuZenkakuStringConverter#ZENKAKU_TO_HANKAKU
     */
    public KatakanaStringConverter(int type){
        super(type);
    }
    
    /**
     * ���p�S�p�ϊ��L�����N�^�z����擾����B<p>
     *
     * @return {@link KatakanaCharacterConverter#CONV_CHARS}
     */
    protected char[][] getHankakuZenkakuChars(){
        return KatakanaCharacterConverter.CONV_CHARS;
    }
    
    /**
     * ���p�S�p�ϊ�������z����擾����B<p>
     *
     * @return {@link #CONV_STRS}
     */
    protected String[][] getHankakuZenkakuStrings(){
        return CONV_STRS;
    }
}
