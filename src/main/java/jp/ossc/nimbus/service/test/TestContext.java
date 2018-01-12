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

import java.io.File;

/**
 * �e�X�g�R���e�L�X�g�B<p>
 * �e�X�g�����s���Ă���ۂ̃R���e�L�X�g�����i�[����B<br>
 * 
 * @author M.Ishida
 */
public interface TestContext{
    
    /**
     * �e�X�g�t�F�[�Y���擾����B<p>
     *
     * @return �e�X�g�t�F�[�Y
     */
    public String getTestPhase();
    
    /**
     * �e�X�g�V�i���I�O���[�v���擾����B<p>
     *
     * @return �e�X�g�V�i���I�O���[�v
     */
    public TestScenarioGroup getTestScenarioGroup();
    
    /**
     * �e�X�g�V�i���I���擾����B<p>
     *
     * @return �e�X�g�V�i���I
     */
    public TestScenario getTestScenario();
    
    /**
     * �e�X�g�P�[�X���擾����B<p>
     *
     * @return �e�X�g�P�[�X
     */
    public TestCase getTestCase();
    
    /**
     * ���s�f�B���N�g�����擾����B<p>
     *
     * @return ���s�f�B���N�g��
     */
    public File getCurrentDirectory();
    
    /**
     * ����K�w���̎w�肵���e�X�g�A�N�V�����̎��s���ʂ�ݒ肷��B<p>
     *
     * @param actionId �A�N�V����ID
     * @param result �e�X�g�A�N�V�����̎��s����
     */
    public void setTestActionResult(String actionId, Object result);
    
    /**
     * ����K�w���̎w�肵���e�X�g�A�N�V�����̎��s���ʂ��擾����B<p>
     *
     * @param actionId �A�N�V����ID
     * @return �e�X�g�A�N�V�����̎��s����
     */
    public Object getTestActionResult(String actionId);
    
    /**
     * �w�肵���e�X�g�P�[�X���̃e�X�g�A�N�V�����̎��s���ʂ��擾����B<p>
     *
     * @param testcaseId �e�X�g�P�[�XID
     * @param actionId �A�N�V����ID
     * @return �e�X�g�A�N�V�����̎��s����
     */
    public Object getTestActionResult(String testcaseId, String actionId);
}
