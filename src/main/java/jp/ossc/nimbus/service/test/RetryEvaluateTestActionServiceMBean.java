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

import jp.ossc.nimbus.core.ServiceBaseMBean;

/**
 * {@link RetryEvaluateTestActionService}��MBean�C���^�t�F�[�X<p>
 * 
 * @author M.Ishida
 * @see RetryEvaluateTestActionService
 */
public interface RetryEvaluateTestActionServiceMBean extends ServiceBaseMBean{
    
    /**
     * �]�������s�������ꍇ�̓����ʁF���g���C�B<p>
     */
    public static final int NG_TYPE_RETRY = 1;
    
    /**
     * �]�������s�������ꍇ�̓����ʁF�I���B<p>
     */
    public static final int NG_TYPE_RETURN = 2;
    
    /**
     * �]�������s�������ꍇ�̓����ʁF�����B<p>
     */
    public static final int NG_TYPE_IGNOR = 3;
    
    /**
     * �f�t�H���g�̃��g���C�Ԋu[ms]��ݒ肷��B<p>
     * �f�t�H���g�́A0�B<br>
     *
     * @param interval ���g���C�Ԋu[ms]
     */
    public void setDefaultInterval(long interval);
    
    /**
     * �f�t�H���g�̃��g���C�Ԋu[ms]���擾����B<p>
     *
     * @return ���g���C�Ԋu[ms]
     */
    public long getDefaultInterval();
    
    /**
     * �f�t�H���g�̃��g���C�񐔂�ݒ肷��B<p>
     * �f�t�H���g�́A0�B<br>
     *
     * @param count ���g���C��
     */
    public void setDefaultRetryCount(int count);
    
    /**
     * �f�t�H���g�̃��g���C�񐔂��擾����B<p>
     *
     * @return ���g���C��
     */
    public int getDefaultRetryCount();
    
    /**
     * ���g���C���̃��g���C�J�n�ʒu��ݒ肷��B<p>
     * �f�t�H���g�́A0�B<br>
     *
     * @param index ���g���C�J�n�ʒu
     */
    public void setRetryMarkIndex(int index);
    
    /**
     * ���g���C���̃��g���C�J�n�ʒu���擾����B<p>
     *
     * @return ���g���C�J�n�ʒu
     */
    public int getRetryMarkIndex();
    
    /**
     * �A������e�X�g�A�N�V������ǉ�����B<p>
     *
     * @param action �e�X�g�A�N�V����
     */
    public void addTestAction(TestAction action);
    
    /**
     * �A������]���e�X�g�A�N�V������ǉ�����B<p>
     *
     * @param action �]���e�X�g�A�N�V����
     * @param type �]�������s�������ꍇ�̓�����
     * @see #NG_TYPE_RETRY
     * @see #NG_TYPE_RETURN
     * @see #NG_TYPE_IGNORE
     */
    public void addEvaluateTestAction(EvaluateTestAction action, int type);
    
    /**
     * �A���̍Ō�̕]���e�X�g�A�N�V������ݒ肷��B<p>
     *
     * @param action �]���e�X�g�A�N�V����
     */
    public void setEndEvaluateTestAction(EvaluateTestAction action);
    
    /**
     * ���̃A�N�V�����̃��\�[�X��`���쐬����ۂ̃f�t�H���g�̑z��R�X�g���擾����B<p>
     * 
     * @return �A�����ꂽ�e�X�g�A�N�V�����̑z��R�X�g�̑��a
     */
    public double getExpectedCost();
    
}
