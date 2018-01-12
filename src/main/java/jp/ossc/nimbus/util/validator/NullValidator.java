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
package jp.ossc.nimbus.util.validator;

/**
 * Null�o���f�[�^�B<p>
 * 
 * @author M.Takata
 */
public class NullValidator implements Validator, java.io.Serializable{
    
    private static final long serialVersionUID = 5789372101951625339L;
    
    /**
     * null�ł��鎖�����؂��邩�ǂ����̃t���O�B<p>
     * true�̏ꍇ�A���ؑΏۂ̃I�u�W�F�N�g��null�ł���΁A���،��ʂ�true�ƂȂ�B<br>
     * false�̏ꍇ�A���ؑΏۂ̃I�u�W�F�N�g����null�ł���΁A���،��ʂ�true�ƂȂ�B<br>
     * �f�t�H���g�́Afalse�B<br>
     */
    protected boolean isNull;
    
    /**
     * null�ł��鎖�����؂��邩�ǂ�����ݒ肷��B<p>
     * true�̏ꍇ�A���ؑΏۂ̃I�u�W�F�N�g��null�ł���΁A���،��ʂ�true�ƂȂ�B<br>
     * false�̏ꍇ�A���ؑΏۂ̃I�u�W�F�N�g����null�ł���΁A���،��ʂ�true�ƂȂ�B<br>
     * �f�t�H���g�́Afalse�B<br>
     * 
     * @param isNull null�ł��鎖�����؂��邩�ǂ����̃t���O
     */
    public void setNull(boolean isNull){
        this.isNull = isNull;
    }
    
    /**
     * null�ł��鎖�����؂��邩�ǂ����𔻒肷��B<p>
     * 
     * @return null�ł��鎖�����؂��邩�ǂ����̃t���O
     */
    public boolean isNull(){
        return isNull;
    }
    
    /**
     * �w�肳�ꂽ�I�u�W�F�N�g��null���ǂ��������؂���B<p>
     *
     * @param obj ���ؑΏۂ̃I�u�W�F�N�g
     * @return ���،��ʁB���ؐ����̏ꍇtrue
     * @exception ValidateException ���؂Ɏ��s�����ꍇ
     */
    public boolean validate(Object obj) throws ValidateException{
        return obj == null && isNull
            || obj != null && !isNull;
    }
}