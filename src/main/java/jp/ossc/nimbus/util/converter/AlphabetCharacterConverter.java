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
public class AlphabetCharacterConverter
 extends HankakuZenkakuCharacterConverter implements java.io.Serializable{
    
    private static final long serialVersionUID = 669952771398308661L;
    
    /**
     * [���p�p��][�S�p�p��] �̔z��B<p>
     */
    protected static final char CONV_CHARS[][] = {
        {'\u0061','\uff41'}, // 'a','��'
        {'\u0062','\uff42'}, // 'b','��'
        {'\u0063','\uff43'}, // 'c','��'
        {'\u0064','\uff44'}, // 'd','��'
        {'\u0065','\uff45'}, // 'e','��'
        {'\u0066','\uff46'}, // 'f','��'
        {'\u0067','\uff47'}, // 'g','��'
        {'\u0068','\uff48'}, // 'h','��'
        {'\u0069','\uff49'}, // 'i','��'
        {'\u006a','\uff4a'}, // 'j','��'
        {'\u006b','\uff4b'}, // 'k','��'
        {'\u006c','\uff4c'}, // 'l','��'
        {'\u006d','\uff4d'}, // 'm','��'
        {'\u006e','\uff4e'}, // 'n','��'
        {'\u006f','\uff4f'}, // 'o','��'
        {'\u0070','\uff50'}, // 'p','��'
        {'\u0071','\uff51'}, // 'q','��'
        {'\u0072','\uff52'}, // 'r','��'
        {'\u0073','\uff53'}, // 's','��'
        {'\u0074','\uff54'}, // 't','��'
        {'\u0075','\uff55'}, // 'u','��'
        {'\u0076','\uff56'}, // 'v','��'
        {'\u0077','\uff57'}, // 'w','��'
        {'\u0078','\uff58'}, // 'x','��'
        {'\u0079','\uff59'}, // 'y','��'
        {'\u007a','\uff5a'}, // 'z','��'
        {'\u0041','\uff21'}, // 'A','�`'
        {'\u0042','\uff22'}, // 'B','�a'
        {'\u0043','\uff23'}, // 'C','�b'
        {'\u0044','\uff24'}, // 'D','�c'
        {'\u0045','\uff25'}, // 'E','�d'
        {'\u0046','\uff26'}, // 'F','�e'
        {'\u0047','\uff27'}, // 'G','�f'
        {'\u0048','\uff28'}, // 'H','�g'
        {'\u0049','\uff29'}, // 'I','�h'
        {'\u004a','\uff2a'}, // 'J','�i'
        {'\u004b','\uff2b'}, // 'K','�j'
        {'\u004c','\uff2c'}, // 'L','�k'
        {'\u004d','\uff2d'}, // 'M','�l'
        {'\u004e','\uff2e'}, // 'N','�m'
        {'\u004f','\uff2f'}, // 'O','�n'
        {'\u0050','\uff30'}, // 'P','�o'
        {'\u0051','\uff31'}, // 'Q','�p'
        {'\u0052','\uff32'}, // 'R','�q'
        {'\u0053','\uff33'}, // 'S','�r'
        {'\u0054','\uff34'}, // 'T','�s'
        {'\u0055','\uff35'}, // 'U','�t'
        {'\u0056','\uff36'}, // 'V','�u'
        {'\u0057','\uff37'}, // 'W','�v'
        {'\u0058','\uff38'}, // 'X','�w'
        {'\u0059','\uff39'}, // 'Y','�x'
        {'\u005a','\uff3a'}  // 'Z','�y'
    };
    
    /**
     * ���p���S�p�ϊ��̉p���R���o�[�^�𐶐�����B<p>
     */
    public AlphabetCharacterConverter(){
        this(HANKAKU_TO_ZENKAKU);
    }
    
    /**
     * �w�肳�ꂽ�ϊ���ʂ̉p���R���o�[�^�[�𐶐�����B<p>
     *
     * @param type �ϊ����
     * @see #HANKAKU_TO_ZENKAKU
     * @see #ZENKAKU_TO_HANKAKU
     */
    public AlphabetCharacterConverter(int type){
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
