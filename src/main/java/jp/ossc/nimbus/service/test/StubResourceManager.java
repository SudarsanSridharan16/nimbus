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
 * �X�^�u���\�[�X�Ǘ��B<p>
 * 
 * @author M.Takata
 */
public interface StubResourceManager{
    
    /**
     * �w�肳�ꂽ�V�i���I�̃X�^�u���\�[�X���A�b�v���[�h����B<p>
     *
     * @param dir �A�b�v���[�h�Ώۂ̃��\�[�X�t�H���_
     * @param scenarioGroupId �V�i���I�O���[�vID
     * @param scenarioId �V�i���IID
     * @param stubId �X�^�uID
     * @exception Exception �A�b�v���[�h���ɗ�O�����������ꍇ
     */
    public void uploadScenarioResource(File dir, String scenarioGroupId, String scenarioId, String stubId) throws Exception;
    
    /**
     * �w�肳�ꂽ�V�i���I�̃X�^�u���\�[�X���_�E�����[�h����B<p>
     *
     * @param dir �_�E�����[�h��̃��\�[�X�t�H���_
     * @param scenarioGroupId �V�i���I�O���[�vID
     * @param scenarioId �V�i���IID
     * @param stubId �X�^�uID
     * @exception Exception �_�E�����[�h���ɗ�O�����������ꍇ
     */
    public void downloadScenarioResource(File dir, String scenarioGroupId, String scenarioId, String stubId) throws Exception;
    
    /**
     * �w�肳�ꂽ�e�X�g�P�[�X�̃X�^�u���\�[�X���A�b�v���[�h����B<p>
     *
     * @param dir �A�b�v���[�h�Ώۂ̃��\�[�X�t�H���_
     * @param scenarioGroupId �V�i���I�O���[�vID
     * @param scenarioId �V�i���IID
     * @param testcaseId �e�X�g�P�[�XID
     * @param stubId �X�^�uID
     * @exception Exception �A�b�v���[�h���ɗ�O�����������ꍇ
     */
    public void uploadTestCaseResource(File dir, String scenarioGroupId, String scenarioId, String testcaseId, String stubId) throws Exception;
    
    /**
     * �w�肳�ꂽ�e�X�g�P�[�X�̃X�^�u���\�[�X���_�E�����[�h����B<p>
     *
     * @param dir �_�E�����[�h��̃��\�[�X�t�H���_
     * @param scenarioGroupId �V�i���I�O���[�vID
     * @param scenarioId �V�i���IID
     * @param testcaseId �e�X�g�P�[�XID
     * @param stubId �X�^�uID
     * @exception Exception �_�E�����[�h���ɗ�O�����������ꍇ
     */
    public void downloadTestCaseResource(File dir, String scenarioGroupId, String scenarioId, String testcaseId, String stubId) throws Exception;
}
