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
 *     <tr><td>�</td><td>�J</td></tr>
 *     <tr><td>�</td><td>�K</td></tr>
 * </table>
 * 
 * @author M.Takata
 */
public class KatakanaCharacterConverter
 extends HankakuZenkakuCharacterConverter implements java.io.Serializable{
    
    private static final long serialVersionUID = 4015624769196620285L;
    
    /**
     * [���p�J�i][�S�p�J�i] �̔z��B
     */
    protected final static char CONV_CHARS[][] = {
        {'\uFF61','\u3002'}, //�B
        {'\uFF62','\u300c'}, //�u
        {'\uFF63','\u300d'}, //�v
        {'\uFF64','\u3001'}, //�A
        {'\uFF65','\u30FB'}, //�E
        {'\uFF66','\u30F2'}, //��
        {'\uFF67','\u30A1'}, //�@
        {'\uFF68','\u30A3'}, //�B
        {'\uFF69','\u30A5'}, //�D
        {'\uFF6A','\u30A7'}, //�F
        {'\uFF6B','\u30A9'}, //�H
        {'\uFF6C','\u30E3'}, //��
        {'\uFF6D','\u30E5'}, //��
        {'\uFF6E','\u30E7'}, //��
        {'\uFF6F','\u30C3'}, //�b
        {'\uFF70','\u30FC'}, //�[ (�n�C�t���ł͂Ȃ�)
        {'\uFF71','\u30A2'}, //�A
        {'\uFF72','\u30A4'}, //�C
        {'\uFF73','\u30A6'}, //�E
        {'\uFF74','\u30A8'}, //�G
        {'\uFF75','\u30AA'}, //�I
        {'\uFF76','\u30AB'}, //�J
        {'\uFF77','\u30AD'}, //�L
        {'\uFF78','\u30AF'}, //�N
        {'\uFF79','\u30B1'}, //�P
        {'\uFF7A','\u30B3'}, //�R
        {'\uFF7B','\u30B5'}, //�T
        {'\uFF7C','\u30B7'}, //�V
        {'\uFF7D','\u30B9'}, //�X
        {'\uFF7E','\u30BB'}, //�Z
        {'\uFF7F','\u30BD'}, //�\
        {'\uFF80','\u30BF'}, //�^
        {'\uFF81','\u30C1'}, //�`
        {'\uFF82','\u30C4'}, //�c
        {'\uFF83','\u30C6'}, //�e
        {'\uFF84','\u30C8'}, //�g
        {'\uFF85','\u30CA'}, //�i
        {'\uFF86','\u30CB'}, //�j
        {'\uFF87','\u30CC'}, //�k
        {'\uFF88','\u30CD'}, //�l
        {'\uFF89','\u30CE'}, //�m
        {'\uFF8A','\u30CF'}, //�n
        {'\uFF8B','\u30D2'}, //�q
        {'\uFF8C','\u30D5'}, //�t
        {'\uFF8D','\u30D8'}, //�w
        {'\uFF8E','\u30DB'}, //�z
        {'\uFF8F','\u30DE'}, //�}
        {'\uFF90','\u30DF'}, //�~
        {'\uFF91','\u30E0'}, //��
        {'\uFF92','\u30E1'}, //��
        {'\uFF93','\u30E2'}, //��
        {'\uFF94','\u30E4'}, //��
        {'\uFF95','\u30E6'}, //��
        {'\uFF96','\u30E8'}, //��
        {'\uFF97','\u30E9'}, //��
        {'\uFF98','\u30EA'}, //��
        {'\uFF99','\u30EB'}, //��
        {'\uFF9A','\u30EC'}, //��
        {'\uFF9B','\u30ED'}, //��
        {'\uFF9C','\u30EF'}, //��
        {'\uFF9D','\u30F3'}, //��
        {'\uFF9E','\u309B'}, //�J�i���_�j
        {'\uFF9F','\u309C'}  //�K�i�����_�j
    };
    
    /**
     * ���p���S�p�ϊ���ʂ̃J�^�J�i�R���o�[�^�𐶐�����B<p>
     */
    public KatakanaCharacterConverter(){
        super(HANKAKU_TO_ZENKAKU);
    }
    
    /**
     * �J�^�J�i�R���o�[�^�𐶐�����B<p>
     *
     * @param type �ϊ����
     * @see HankakuZenkakuCharacterConverter#HANKAKU_TO_ZENKAKU
     * @see HankakuZenkakuCharacterConverter#ZENKAKU_TO_HANKAKU
     */
    public KatakanaCharacterConverter(int type){
        super(type);
    }
    
    /**
     * ���p�S�p�ϊ��L�����N�^�z����擾����B<p>
     *
     * @return {@link #CONV_CHARS}
     */
    protected char[][] getHankakuZenkakuChars(){
        return CONV_CHARS;
    }
}
