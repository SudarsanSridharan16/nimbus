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
 * ���p�S�p�L�����N�^�R���o�[�^�̒��ۃN���X�B<p>
 * 
 * @author M.Takata
 */
public abstract class HankakuZenkakuCharacterConverter
 extends AbstractCharacterConverter implements HankakuZenkakuConverter{
    
    private static final long serialVersionUID = 4189115972314152574L;
    
    /**
     * ���p���S�p�ϊ���ʂ̃L�����N�^�R���o�[�^�𐶐�����B<p>
     */
    public HankakuZenkakuCharacterConverter(){
        super(HANKAKU_TO_ZENKAKU);
    }
    
    /**
     * �w�肳�ꂽ�ϊ���ʂ̃L�����N�^�R���o�[�^�𐶐�����B<p>
     *
     * @param type �ϊ����
     * @see #HANKAKU_TO_ZENKAKU
     * @see #ZENKAKU_TO_HANKAKU
     */
    public HankakuZenkakuCharacterConverter(int type){
        super(type);
    }
    
    /**
     * �ϊ��L�����N�^�z����擾����B<p>
     * {@link #getHankakuZenkakuChars()}���Ăяo���B<br>
     *
     * @return �ϊ��L�����N�^�z��
     */
    protected final char[][] getConvertChars(){
        return getHankakuZenkakuChars();
    }
    
    /**
     * ���p�S�p�ϊ��L�����N�^�z����擾����B<p>
     *
     * @return ���p�S�p�ϊ��L�����N�^�z��
     */
    protected abstract char[][] getHankakuZenkakuChars();
}