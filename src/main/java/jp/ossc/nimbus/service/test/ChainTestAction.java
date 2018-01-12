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

import java.io.Reader;

/**
 * �A���e�X�g�A�N�V�����B<p>
 * �e�X�g�A�N�V������A�������Ď��s����e�X�g�A�N�V�����ł���B<br>
 * 
 * @author M.Ishida
 */
public interface ChainTestAction{
    
    /**
     * �e�X�g�A�N�V������A�������Ď��s����B<p>
     *
     * @param context �e�X�g�R���e�L�X�g
     * @param actionId ���̃e�X�g�A�N�V������ID
     * @param resources �A�������Ă���e�e�X�g�A�N�V�����ւ̃��\�[�X�z��
     * @return �ŏI�I�Ȏ��s����
     * @exception Exception �A���������e�X�g�A�N�V�����̎��s�ŗ�O�����������ꍇ
     */
    public Object execute(TestContext context, String actionId, Reader[] resources) throws Exception;
    
    /**
     * �A���e�X�g�A�N�V�����v���Z�X�B<p>
     * �A������Ă���P�O�̃e�X�g�A�N�V�����̌��ʂ𗘗p���āA�e�X�g���s����A����p�̃e�X�g�A�N�V�����ł���B<br>
     * 
     * @author M.ishida
     */
    public interface TestActionProcess{
        
        /**
         * �A������Ă���P�O�̃e�X�g�A�N�V�����̌��ʂ𗘗p���āA�e�X�g���s����B<p>
         *
         * @param context �e�X�g�R���e�L�X�g
         * @param actionId ���̃e�X�g�A�N�V������ID
         * @param preResult �A������Ă���P�O�̃e�X�g�A�N�V�����̌���
         * @param resource ���̃e�X�g�A�N�V�����ւ̃��\�[�X
         * @return ���s����
         * @exception Exception �e�X�g�A�N�V�����̎��s�ŗ�O�����������ꍇ
         */
        public Object execute(TestContext context, String actionId, Object preResult, Reader resource) throws Exception;
    }
}
