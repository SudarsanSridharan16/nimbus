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
package jp.ossc.nimbus.service.test;

import java.util.Date;

/**
 * ���X�e�[�^�X�N���X�B<p>
 * 
 * @author M.Ishida
 */
public class StatusBaseImpl implements java.io.Serializable{
    
    private String userId;
    private Date startTime;
    private boolean result = true;
    
    /**
     * �w�肳�ꂽ���s���[�U�ł̃X�e�[�^�X�𐶐�����B<p>
     *
     * @param userId ���s���[�U
     */
    public StatusBaseImpl(String userId) {
        this.userId = userId;
    }
    
    /**
     * ���s���[�UID��ݒ肷��B<p>
     *
     * @param userId ���s���[�UID
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }
    
    public String getUserId() {
        return userId;
    }
    
    /**
     * ���s�J�n������ݒ肷��B<p>
     *
     * @param startTime ���s�J�n����
     */
    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }
    
    public Date getStartTime() {
        return startTime;
    }
    
    public boolean getResult() {
        return result;
    }
    
    /**
     * ���s���ʂ�ݒ肷��B<p>
     *
     * @param result ���s���ʁBtrue�̏ꍇ�A�����Bfalse�̏ꍇ�A���s
     */
    public void setResult(boolean result) {
        this.result = result;
    }
    
}
