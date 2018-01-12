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

/**
 * �e�X�g�X�^�u�B<p>
 * 
 * @author M.Takata
 */
public interface TestStub{
    
    /**
     * �X�^�u��ID���擾����B<p>
     *
     * @return �X�^�u��ID
     */
    public String getId();
    
    /**
     * �V�i���I�̃e�X�g���J�n����B<p>
     *
     * @param userId ���[�UID
     * @param scenarioGroupId �V�i���I�O���[�vID
     * @param scenarioId �V�i���IID
     * @exception Exception �V�i���I�̃e�X�g���s���ɗ�O�����������ꍇ
     */
    public void startScenario(String userId, String scenarioGroupId, String scenarioId) throws Exception;
    
    /**
     * �V�i���I�̃e�X�g���������B<p>
     *
     * @exception Exception �V�i���I�̃e�X�g������ɗ�O�����������ꍇ
     */
    public void cancelScenario() throws Exception;
    
    /**
     * �V�i���I�̃e�X�g���I������B<p>
     *
     * @exception Exception �V�i���I�̃e�X�g�I�����ɗ�O�����������ꍇ
     */
    public void endScenario() throws Exception;
    
    /**
     * �e�X�g�P�[�X�̃e�X�g���J�n����B<p>
     *
     * @param testcaseId �e�X�g�P�[�XID
     * @exception Exception �e�X�g�P�[�X�̃e�X�g���s���ɗ�O�����������ꍇ
     */
    public void startTestCase(String testcaseId) throws Exception;
    
    /**
     * �e�X�g�P�[�X�̃e�X�g���I������B<p>
     *
     * @exception Exception �e�X�g�P�[�X�̃e�X�g�I�����ɗ�O�����������ꍇ
     */
    public void endTestCase() throws Exception;
}