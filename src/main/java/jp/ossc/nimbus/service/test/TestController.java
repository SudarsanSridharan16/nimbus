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
 * �e�X�g�R���g���[���B<p>
 *
 * @author M.Ishida
 */
public interface TestController extends TestEventListener {

    /**
     * �e�X�g���ʂ��_�E�����[�h����ۂ̃��X�|���X�^�C�v�����k�������萔�B<p>
     */
    public static final int RESPONSE_FILE_TYPE_DEFAULT = 0;

    /**
     * �e�X�g���ʂ��_�E�����[�h����ۂ̃��X�|���X�^�C�vZIP���k�������萔�B<p>
     */
    public static final int RESPONSE_FILE_TYPE_ZIP = 1;

    /**
     * �e�X�g�Ώۂ̂��ׂĂ�{@link jp.ossc.nimbus.service.test.TestScenarioGroup TestScenarioGroup}�̔z����擾����B
     * <p>
     *
     * @return TestScenarioGroup�̔z��
     * @throws Exception �擾����O
     */
    public TestScenarioGroup[] getScenarioGroups() throws Exception;

    /**
     * �e�X�g�Ώۂ̂��ׂẴV�i���I�O���[�vID�̔z����擾����B
     * <p>
     *
     * @return �V�i���I�O���[�vID�̔z��
     * @throws Exception �擾����O
     */
    public String[] getScenarioGroupIds() throws Exception;

    /**
     * �w�肳�ꂽID��{@link jp.ossc.nimbus.service.test.TestScenarioGroup TestScenarioGroup}���擾����B
     * <p>
     *
     * @param scenarioGroupId �V�i���I�O���[�vID
     * @return TestScenarioGroup
     * @throws Exception �擾����O
     */
    public TestScenarioGroup getScenarioGroup(String scenarioGroupId) throws Exception;

    /**
     * ���ݎ��s����{@link jp.ossc.nimbus.service.test.TestScenarioGroup TestScenarioGroup}���擾����B
     * <p>
     *
     * @return TestScenarioGroup
     * @throws Exception �擾����O
     */
    public TestScenarioGroup getCurrentScenarioGroup() throws Exception;

    /**
     * �w�肳�ꂽID��{@link jp.ossc.nimbus.service.test.TestScenarioGroup.TestScenarioGroupResource TestScenarioGroupResource}���擾����B
     * <p>
     *
     * @param scenarioGroupId �V�i���I�O���[�vID
     * @return TestScenarioGroupResource
     * @throws Exception �擾����O
     */
    public TestScenarioGroup.TestScenarioGroupResource getTestScenarioGroupResource(String scenarioGroupId) throws Exception;

    /**
     * �w�肳�ꂽID��{@link jp.ossc.nimbus.service.test.TestScenarioGroup.Status Status}���擾����B
     * <p>
     *
     * @param scenarioGroupId �V�i���I�O���[�vID
     * @return Status
     */
    public TestScenarioGroup.Status getTestScenarioGroupStatus(String scenarioGroupId);

    /**
     * �w�肳�ꂽ�V�i���I�O���[�v�z����{@link jp.ossc.nimbus.service.test.TestScenario TestScenario}�̔z����擾����B
     * <p>
     *
     * @param scenarioGroupId �V�i���I�O���[�vID
     * @return TestScenario�̔z��
     * @throws Exception �擾����O
     */
    public TestScenario[] getScenarios(String scenarioGroupId) throws Exception;

    /**
     * �w�肳�ꂽ�V�i���I�O���[�v�z���̃V�i���IID�̔z����擾����B
     * <p>
     *
     * @param scenarioGroupId �V�i���I�O���[�vID
     * @return �V�i���IID�̔z��
     * @throws Exception �擾����O
     */
    public String[] getScenarioIds(String scenarioGroupId) throws Exception;

    /**
     * �w�肳�ꂽ�V�i���I�O���[�vID�A�V�i���IID��{@link jp.ossc.nimbus.service.test.TestScenario TestScenario}���擾����B
     * <p>
     *
     * @param scenarioGroupId �V�i���I�O���[�vID
     * @param scenarioId �V�i���IID
     * @return TestScenario
     * @throws Exception �擾����O
     */
    public TestScenario getScenario(String scenarioGroupId, String scenarioId) throws Exception;

    /**
     * ���ݎ��s����{@link jp.ossc.nimbus.service.test.TestScenario TestScenario}���擾����B
     * <p>
     *
     * @return TestScenario
     * @throws Exception �擾����O
     */
    public TestScenario getCurrentScenario() throws Exception;

    /**
     * �w�肳�ꂽ�V�i���I�O���[�vID�A�V�i���IID��{@link jp.ossc.nimbus.service.test.TestScenario.TestScenarioResource TestScenarioResource}���擾����B
     * <p>
     *
     * @param scenarioGroupId �V�i���I�O���[�vID
     * @param scenarioId �V�i���IID
     * @return TestScenarioResource
     * @throws Exception �擾����O
     */
    public TestScenario.TestScenarioResource getTestScenarioResource(String scenarioGroupId, String scenarioId) throws Exception;

    /**
     * �w�肳�ꂽ�V�i���I�O���[�vID�A�V�i���IID��{@link jp.ossc.nimbus.service.test.TestScenario.Status Status}���擾����B
     * <p>
     *
     * @param scenarioGroupId �V�i���I�O���[�vID
     * @param scenarioId �V�i���IID
     * @return Status
     */
    public TestScenario.Status getTestScenarioStatus(String scenarioGroupId, String scenarioId);

    /**
     * �w�肳�ꂽ�V�i���I�O���[�v�A�V�i���I�z����{@link jp.ossc.nimbus.service.test.TestCase TestCase}�̔z����擾����B
     * <p>
     *
     * @param scenarioGroupId �V�i���I�O���[�vID
     * @param scenarioId �V�i���IID
     * @return TestCase�̔z��
     * @throws Exception �擾����O
     */
    public TestCase[] getTestCases(String scenarioGroupId, String scenarioId) throws Exception;

    /**
     * �w�肳�ꂽ�V�i���I�O���[�v�A�V�i���I�z���̃e�X�g�P�[�XID�̔z����擾����B
     * <p>
     *
     * @param scenarioGroupId �V�i���I�O���[�vID
     * @param scenarioId �V�i���IID
     * @return �e�X�g�P�[�XID�̔z��
     * @throws Exception �擾����O
     */
    public String[] getTestCaseIds(String scenarioGroupId, String scenarioId) throws Exception;

    /**
     * �w�肳�ꂽ�V�i���I�O���[�vID�A�V�i���IID�A�e�X�g�P�[�XID��{@link jp.ossc.nimbus.service.test.TestCase TestCase}���擾����B
     * <p>
     *
     * @param scenarioGroupId �V�i���I�O���[�vID
     * @param scenarioId �V�i���IID
     * @param testcaseId �e�X�g�P�[�XID
     * @return TestCase
     * @throws Exception �擾����O
     */
    public TestCase getTestCase(String scenarioGroupId, String scenarioId, String testcaseId) throws Exception;

    /**
     * ���ݎ��s����{@link jp.ossc.nimbus.service.test.TestCase TestCase}���擾����B
     * <p>
     *
     * @return TestCase
     * @throws Exception �擾����O
     */
    public TestCase getCurrentTestCase() throws Exception;

    /**
     * �w�肳�ꂽ�V�i���I�O���[�vID�A�V�i���IID�A�e�X�g�P�[�XID��{@link jp.ossc.nimbus.service.test.TestCase.TestCaseResource TestCaseResource}���擾����B
     * <p>
     *
     * @param scenarioGroupId �V�i���I�O���[�vID
     * @param scenarioId �V�i���IID
     * @param testcaseId �e�X�g�P�[�XID
     * @return TestCaseResource
     * @throws Exception �擾����O
     */
    public TestCase.TestCaseResource getTestCaseResource(String scenarioGroupId, String scenarioId, String testcaseId) throws Exception;

    /**
     * �w�肳�ꂽ�V�i���I�O���[�vID�A�V�i���IID�A�e�X�g�P�[�XID��{@link jp.ossc.nimbus.service.test.TestCase.Status Status}���擾����B
     * <p>
     *
     * @param scenarioGroupId �V�i���I�O���[�vID
     * @param scenarioId �V�i���IID
     * @param testcaseId �e�X�g�P�[�XID
     * @return Status
     */
    public TestCase.Status getTestCaseStatus(String scenarioGroupId, String scenarioId, String testcaseId);

    /**
     * �w�肳�ꂽ�V�i���I�O���[�vID�A�V�i���IID�̃e�X�g���ʂ��w�肳�ꂽ�f�B���N�g���Ƀ_�E�����[�h����B
     * <p>
     *
     * @param dir �_�E�����[�h�Ώۂ̃f�B���N�g��
     * @param scenarioGroupId �V�i���I�O���[�vID
     * @param scenarioId �V�i���IID
     * @param respnseFileType ���X�|���X�^�C�v
     * @return �o�̓t�@�C��
     * @throws Exception �_�E�����[�h����O
     */
    public File downloadScenarioResult(File dir, String scenarioGroupId, String scenarioId, int respnseFileType) throws Exception;

    /**
     * �w�肳�ꂽ�V�i���I�O���[�vID�A�V�i���IID�A�e�X�g�P�[�XID�̃e�X�g���ʂ��w�肳�ꂽ�f�B���N�g���Ƀ_�E�����[�h����B
     * <p>
     *
     * @param dir �_�E�����[�h�Ώۂ̃f�B���N�g��
     * @param scenarioGroupId �V�i���I�O���[�vID
     * @param scenarioId �V�i���IID
     * @param testcaseId �e�X�g�P�[�XID
     * @param respnseFileType ���X�|���X�^�C�v
     * @return �o�̓t�@�C��
     * @throws Exception �_�E�����[�h����O
     */
    public File downloadTestCaseResult(File dir, String scenarioGroupId, String scenarioId, String testcaseId, int respnseFileType) throws Exception;

    /**
     * �w�肳�ꂽ�V�i���I�O���[�vID�̃��\�[�X���_�E�����[�h����B
     * <p>
     *
     * @param scenarioGroupId �V�i���I�O���[�vID
     * @throws Exception �_�E�����[�h����O
     */
    public void downloadTestScenarioGroupResource(String scenarioGroupId) throws Exception;

    /**
     * �w�肳�ꂽ�V�i���I�O���[�vID�A�V�i���IID�̃��\�[�X���_�E�����[�h����B
     * <p>
     *
     * @param scenarioGroupId �V�i���I�O���[�vID
     * @param scenarioId �V�i���IID
     * @throws Exception �_�E�����[�h����O
     */
    public void downloadTestScenarioResource(String scenarioGroupId, String scenarioId) throws Exception;

    /**
     * �e�X�g�R���g���[���̏�Ԃ����Z�b�g����B
     *
     * @throws Exception ���Z�b�g���̗�O
     */
    public void reset() throws Exception;

}