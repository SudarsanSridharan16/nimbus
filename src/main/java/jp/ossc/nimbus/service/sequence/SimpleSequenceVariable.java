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
package jp.ossc.nimbus.service.sequence;

import jp.ossc.nimbus.util.CsvArrayList;

/**
 * �P�������Ǘ��N���X�B<p>
 * �e�������̊J�n�����A�I���������Ǘ����ăJ�����g�����̌��オ�葀����s���B<br>
 * 
 * @author H.Nakano
 */
public class SimpleSequenceVariable
 implements SequenceVariable, java.io.Serializable{
    
    private static final long serialVersionUID = -2852302890121212311L;
    
    public static final String DELIMITER = "," ; //$NON-NLS-1$
    
    //## �����o�[�ϐ��錾 ##
    
    /** �J�n�l */
    private String mStartVal;
    /** �I���l */
    private String mEndVal;
    /** �J�����g�ԍ� */
    private String mCurrentVal;
    
    /**
     * �R���X�g���N�^�B<p>
     * �J�n�l�A�I���l���p�[�X���ă����o�ϐ��ɃZ�b�g���Č��ݒl�ɊJ�n�l���Z�b�g����B<br>
     * 
     * @param format �J�n����,�I������ �`���̕�����
     */
    public SimpleSequenceVariable(String format){
        this(format, null);
    }
    
    /**
     * �R���X�g���N�^�B<p>
     * �J�n�l�A�I���l���p�[�X���ă����o�ϐ��ɃZ�b�g����B�܂��A�w�肳�ꂽ���ݒl�������o�ϐ��ɃZ�b�g����B<br>
     * 
     * @param format �J�n����,�I������ �`���̕�����
     * @param current ���ݒl
     */
    public SimpleSequenceVariable(String format, String current){
        // format���J�n�l�ƏI���l�ɕ�������
        CsvArrayList parser = new CsvArrayList();
        parser.split(format,DELIMITER);
        
        // �J�n�l�A�I���l�A���ݒl�������o�ϐ��ɃZ�b�g����
        this.mStartVal=parser.getStr(0);
        this.mEndVal=parser.getStr(1);
        this.mCurrentVal=current == null ? this.mStartVal : current;
    }
    
    // SequenceVariable ��JavaDoc
    public boolean increment(){
        // ���ݒl���C���N�������g����B
        char [] cValtmp = this.mCurrentVal.toCharArray();
        cValtmp[0]++;
        String incVal = new String(cValtmp);
        // �C���N�������g�������ʂ��I���l�𒴂��Ă��Ȃ����`�F�b�N����
        if(incVal.compareTo(mEndVal) > 0){
            mCurrentVal = mStartVal;
            return true;
        }else{
            mCurrentVal = incVal;
        }
        return false;
    }
    
    // SequenceVariable ��JavaDoc
    public void clear(){
        mCurrentVal = mStartVal;
    }
    
    // SequenceVariable ��JavaDoc
    public String getCurrent(){
        return this.mCurrentVal;
    }
}
