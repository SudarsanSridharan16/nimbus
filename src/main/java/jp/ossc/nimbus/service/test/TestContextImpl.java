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

import java.util.HashMap;
import java.util.Map;
import java.io.File;

/**
 * �e�X�g�R���e�L�X�g�N���X�B<p>
 * �e�X�g�����s���Ă���ۂ̃R���e�L�X�g�����i�[����B<br>
 * 
 * @author M.Ishida
 */
public class TestContextImpl implements TestContext {
    
    private String testPhase;
    private TestScenarioGroup testScenarioGroup;
    private TestScenario testScenario;
    private TestCase testCase;
    private File currentDirectory;
    
    private Map resultMap = new HashMap();
    
    /**
     * �e�X�g�t�F�[�Y��ݒ肷��B<p>
     *
     * @param phase �e�X�g�t�F�[�Y
     */
    public void setTestPhase(String phase) {
        testPhase = phase;
    }
    
    public String getTestPhase() {
        return testPhase;
    }
    
    public File getCurrentDirectory(){
        return currentDirectory;
    }
    
    /**
     * ���s�f�B���N�g����ݒ肷��B<p>
     *
     * @param dir ���s�f�B���N�g��
     */
    public void setCurrentDirectory(File dir){
        currentDirectory = dir;
    }
    
    /**
     * �e�X�g�V�i���I�O���[�v��ݒ肷��B<p>
     *
     * @param testScenarioGroup �e�X�g�V�i���I�O���[�v
     */
    public void setTestScenarioGroup(TestScenarioGroup testScenarioGroup) {
        this.testScenarioGroup = testScenarioGroup;
        this.testScenario = null;
        this.testCase = null;
    }
    
    public TestScenarioGroup getTestScenarioGroup() {
        return testScenarioGroup;
    }
    
    /**
     * �e�X�g�V�i���I��ݒ肷��B<p>
     *
     * @param testScenario �e�X�g�V�i���I
     */
    public void setTestScenario(TestScenario testScenario) {
        this.testScenarioGroup = null;
        this.testScenario = testScenario;
        this.testCase = null;
    }
    
    public TestScenario getTestScenario() {
        return testScenario;
    }
    
    /**
     * �e�X�g�P�[�X��ݒ肷��B<p>
     *
     * @param testCase �e�X�g�P�[�X
     */
    public void setTestCase(TestCase testCase) {
        this.testScenarioGroup = null;
        this.testScenario = null;
        this.testCase = testCase;
    }
    
    public TestCase getTestCase() {
        return testCase;
    }
    
    public Object getTestActionResult(String actionId) {
        String targetId = null;
        if (testCase != null) {
            targetId = testCase.getTestCaseId();
        } else if (testScenario != null) {
            targetId = testScenario.getScenarioId();
        } else if (testScenarioGroup != null) {
            targetId = testScenarioGroup.getScenarioGroupId();
        } else {
            return null;
        }
        if (resultMap.containsKey(targetId)) {
            Map map = (Map) resultMap.get(targetId);
            return map.get(actionId);
        }
        return null;
    }
    
    public Object getTestActionResult(String testcaseId, String actionId) {
        if (resultMap.containsKey(testcaseId)) {
            Map map = (Map) resultMap.get(testcaseId);
            return map.get(actionId);
        }
        return null;
    }
    
    public void setTestActionResult(String actionId, Object result) {
        String targetId = null;
        if (testCase != null) {
            targetId = testCase.getTestCaseId();
        } else if (testScenario != null) {
            targetId = testScenario.getScenarioId();
        } else if (testScenarioGroup != null) {
            targetId = testScenarioGroup.getScenarioGroupId();
        } else {
            return;
        }
        if (!resultMap.containsKey(targetId)) {
            resultMap.put(targetId, new HashMap());
        }
        Map map = (Map) resultMap.get(targetId);
        map.put(actionId, result);
    }
}
