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
 * �J�X�^���L�����N�^�R���o�[�^�B<p>
 * 
 * @author M.Takata
 */
public class CustomCharacterConverter extends AbstractCharacterConverter
 implements java.io.Serializable{
    
    private static final long serialVersionUID = 2333016801467875893L;
    
    protected char[][] convertChars;
    
    /**
     * �������ϊ������̃J�X�^���L�����N�^�R���o�[�^�𐶐�����B<p>
     */
    public CustomCharacterConverter(){
        this(POSITIVE_CONVERT);
    }
    
    /**
     * ��̃J�X�^���L�����N�^�R���o�[�^�𐶐�����B<p>
     *
     * @param type �ϊ����
     * @see ReversibleConverter#POSITIVE_CONVERT
     * @see ReversibleConverter#REVERSE_CONVERT
     */
    public CustomCharacterConverter(int type){
        super(type);
    }
    
    /**
     * �J�X�^���L�����N�^�R���o�[�^�𐶐�����B<p>
     *
     * @param type �ϊ����
     * @param fromChars �ϊ���L�����N�^�z��
     * @param toChars �ϊ��ΏۃL�����N�^�z��
     * @see ReversibleConverter#POSITIVE_CONVERT
     * @see ReversibleConverter#REVERSE_CONVERT
     */
    public CustomCharacterConverter(int type, char[] fromChars, char[] toChars){
        super(type);
        setConvertChars(fromChars, toChars);
    }
    
    /**
     * �ϊ��L�����N�^�z���ݒ肷��B<p>
     *
     * @param fromChars �ϊ��ΏۃL�����N�^�z��
     * @param toChars �ϊ���L�����N�^�z��
     */
    public void setConvertChars(char[] fromChars, char[] toChars){
        if(toChars == null && fromChars == null){
            convertChars = null;
        }else if((toChars == null || fromChars == null)
            || toChars.length != fromChars.length){
            throw new IllegalArgumentException("Invalid ConvertChars.");
        }else{
            char[][] convChars = new char[toChars.length][];
            for(int i = 0; i < toChars.length; i++){
                convChars[i] = new char[]{fromChars[i], toChars[i]};
            }
            convertChars = convChars;
        }
    }
    
    /**
     * �ϊ��L�����N�^�z����擾����B<p>
     *
     * @return �ϊ��L�����N�^�z��
     */
    protected char[][] getConvertChars(){
        return convertChars;
    }
}
