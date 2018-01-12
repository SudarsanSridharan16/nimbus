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
 * �X�P�W���[�������������e�X�g���\�[�X�B<p>
 * 
 * @author M.Ishida
 */
public interface ScheduledTestResource extends TestResourceBase {
    
    /**
     * �쐬�҂��擾����B<p>
     *
     * @return �쐬��
     */
    public String getCreator();
    
    /**
     * �쐬�J�n�\��������擾����B<p>
     *
     * @return �쐬�J�n�\�����
     */
    public Date getScheduledCreateStartDate();
    
    /**
     * �쐬�I���\��������擾����B<p>
     *
     * @return �쐬�I���\�����
     */
    public Date getScheduledCreateEndDate();
    
    /**
     * �\��R�X�g���擾����B<p>
     *
     * @return �\��R�X�g
     */
    public double getExpectedCost();
    
    /**
     * �쐬�J�n�������擾����B<p>
     *
     * @return �쐬�J�n����
     */
    public Date getCreateStartDate();
    
    /**
     * �쐬�I���������擾����B<p>
     *
     * @return �쐬�I������
     */
    public Date getCreateEndDate();
    
    /**
     * �R�X�g���擾����B<p>
     *
     * @return �R�X�g
     */
    public double getCost();
    
    /**
     * �i�������擾����B<p>
     *
     * @return �i����
     */
    public double getProgress();
    
    /**
     * �w�肳�ꂽ�A�N�V�����̗\��R�X�g���擾����B<p>
     *
     * @param actionId �A�N�V����ID
     * @return �\��R�X�g
     */
    public double getActionExpectedCost(String actionId);
    
    /**
     * �w�肳�ꂽ�A�N�V�����̃R�X�g���擾����B<p>
     *
     * @param actionId �A�N�V����ID
     * @return �R�X�g
     */
    public double getActionCost(String actionId);
}
