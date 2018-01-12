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
 * �e�X�g���\�[�X�Ǘ��B<p>
 * 
 * @author M.Takata
 */
public interface TestResourceManager{
    
    /**
     * ���\�[�X�����|�W�g������`�F�b�N�A�E�g����B<p>
     *
     * @exception Exception �`�F�b�N�A�E�g�Ɏ��s�����ꍇ
     */
    public void checkOut() throws Exception;
    
    /**
     * �V�i���I�O���[�vID�̔z����擾����B<p>
     *
     * @return �V�i���I�O���[�vID�̔z��
     */
    public String[] getScenarioGroupIds() throws Exception;
    
    /**
     * �w�肳�ꂽ�V�i���I�O���[�v���̃V�i���IID�̔z����擾����B<p>
     *
     * @param groupId �V�i���I�O���[�vID
     * @return �V�i���IID�̔z��
     */
    public String[] getScenarioIds(String groupId) throws Exception;
    
    /**
     * �w�肳�ꂽ�V�i���I���̃e�X�g�P�[�XID�̔z����擾����B<p>
     *
     * @param groupId �V�i���I�O���[�vID
     * @param scenarioId �V�i���IID
     * @return �e�X�g�P�[�XID�̔z��
     */
    public String[] getTestCaseIds(String groupId, String scenarioId) throws Exception;
    
    /**
     * �w�肳�ꂽ�e�X�g�P�[�X���̃X�^�uID�̔z����擾����B<p>
     *
     * @param groupId �V�i���I�O���[�vID
     * @param scenarioId �V�i���IID
     * @param testcaseId �e�X�g�P�[�XID
     * @return �X�^�uID�̔z��
     */
    public String[] getStubIds(String groupId, String scenarioId, String testcaseId) throws Exception;
    
    /**
     * �w�肳�ꂽ�V�i���I�O���[�v�̃��\�[�X���_�E�����[�h����B<p>
     *
     * @param dir �_�E�����[�h��̃f�B���N�g��
     * @param scenarioGroupId �V�i���I�O���[�vID
     */
    public void downloadScenarioGroupResource(File dir, String scenarioGroupId) throws Exception;
    
    /**
     * �w�肳�ꂽ�V�i���I�̃��\�[�X���_�E�����[�h����B<p>
     *
     * @param dir �_�E�����[�h��̃f�B���N�g��
     * @param scenarioGroupId �V�i���I�O���[�vID
     * @param scenarioId �V�i���IID
     */
    public void downloadScenarioResource(File dir, String scenarioGroupId, String scenarioId) throws Exception;
    
}
