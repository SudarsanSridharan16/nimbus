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
 * �L���R���o�[�^�B<p>
 * <table border=5>
 *     <tr><th>���p�L��</th><th>�S�p�L��</th></tr>
 *     <tr><td>&nbsp;</td><td>�@</td></tr>
 *     <tr><td>!</td><td>�I</td></tr>
 *     <tr><td>"</td><td>�h</td></tr>
 *     <tr><td>#</td><td>��</td></tr>
 *     <tr><td>$</td><td>��</td></tr>
 *     <tr><td>%</td><td>��</td></tr>
 *     <tr><td>&amp</td><td>��</td></tr>
 *     <tr><td>'</td><td>�f</td></tr>
 *     <tr><td>(</td><td>�i</td></tr>
 *     <tr><td>)</td><td>�j</td></tr>
 *     <tr><td>*</td><td>��</td></tr>
 *     <tr><td>+</td><td>�{</td></tr>
 *     <tr><td>,</td><td>�C</td></tr>
 *     <tr><td>-</td><td>�|(MS932)</td></tr>
 *     <tr><td>.</td><td>�D</td></tr>
 *     <tr><td>/</td><td>�^</td></tr>
 *     <tr><td>:</td><td>�F</td></tr>
 *     <tr><td>;</td><td>�G</td></tr>
 *     <tr><td>&lt</td><td>��</td></tr>
 *     <tr><td>=</td><td>��</td></tr>
 *     <tr><td>&gt</td><td>��</td></tr>
 *     <tr><td>?</td><td>�H</td></tr>
 *     <tr><td>@</td><td>��</td></tr>
 *     <tr><td>[</td><td>�m</td></tr>
 *     <tr><td>\</td><td>��(MS932)</td></tr>
 *     <tr><td>]</td><td>�n</td></tr>
 *     <tr><td>^</td><td>�O</td></tr>
 *     <tr><td>_</td><td>�Q</td></tr>
 *     <tr><td>`</td><td>�e</td></tr>
 *     <tr><td>{</td><td>�o</td></tr>
 *     <tr><td>|</td><td>�b</td></tr>
 *     <tr><td>}</td><td>�p</td></tr>
 *     <tr><td>~</td><td>�`</td></tr>
 * </table>
 *
 * @author M.Takata
 */
public class SymbolStringConverter extends HankakuZenkakuStringConverter
 implements java.io.Serializable{
    
    private static final long serialVersionUID = 7676178336951918605L;
    
    /**
     * ���p���S�p�ϊ���ʂ̋L���R���o�[�^�𐶐�����B<p>
     */
    public SymbolStringConverter(){
        super(HANKAKU_TO_ZENKAKU);
    }
    
    /**
     * �L���R���o�[�^�[�𐶐�����B<p>
     *
     * @param type �ϊ����
     * @see HankakuZenkakuStringConverter#HANKAKU_TO_ZENKAKU
     * @see HankakuZenkakuStringConverter#ZENKAKU_TO_HANKAKU
     */
    public SymbolStringConverter(int type){
        super(type);
    }
    
    /**
     * ���p�S�p�ϊ��L�����N�^�z����擾����B<p>
     *
     * @return {@link SymbolCharacterConverter#CONV_CHARS}
     */
    protected char[][] getHankakuZenkakuChars(){
        return SymbolCharacterConverter.CONV_CHARS;
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