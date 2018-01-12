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
package jp.ossc.nimbus.service.test.stub.http;

import java.io.File;

import jp.ossc.nimbus.core.ServiceName;
import jp.ossc.nimbus.service.http.proxy.HttpProcessServiceBaseMBean;
import jp.ossc.nimbus.service.test.TestStub;

/**
 * {@link HttpTestStubService}��MBean�C���^�t�F�[�X<p>
 * 
 * @author M.Takata
 * @see HttpTestStubService
 */
public interface HttpTestStubServiceMBean extends HttpProcessServiceBaseMBean, TestStub{
    
    /**
     * �X�^�uID��ݒ肷��B<p>
     *
     * @param id �X�^�uID
     */
    public void setId(String id);
    
    /**
     * �X�^�uID���擾����B<p>
     *
     * @return �X�^�uID
     */
    public String getId();
    
    /**
     * ���\�[�X�t�@�C���̕����G���R�[�f�B���O��ݒ肷��B<p>
     *
     * @param encoding �����G���R�[�f�B���O
     */
    public void setFileEncoding(String encoding);
    
    /**
     * ���\�[�X�t�@�C���̕����G���R�[�f�B���O���擾����B<p>
     *
     * @return �����G���R�[�f�B���O
     */
    public String getFileEncoding();
    
    /**
     * {@link jp.ossc.nimbus.service.test.StubResourceManager StubResourceManager}�T�[�r�X�̃T�[�r�X����ݒ肷��B<p>
     *
     * @param name StubResourceManager�T�[�r�X�̃T�[�r�X��
     */
    public void setStubResourceManagerServiceName(ServiceName name);
    
    /**
     * {@link jp.ossc.nimbus.service.test.StubResourceManager StubResourceManager}�T�[�r�X�̃T�[�r�X�����擾����B<p>
     *
     * @return StubResourceManager�T�[�r�X�̃T�[�r�X��
     */
    public ServiceName getStubResourceManagerServiceName();
    
    /**
     * {@link jp.ossc.nimbus.service.interpreter.Interpreter Interpreter}�T�[�r�X�̃T�[�r�X����ݒ肷��B<p>
     *
     * @param name Interpreter�T�[�r�X�̃T�[�r�X��
     */
    public void setInterpreterServiceName(ServiceName name);
    
    /**
     * {@link jp.ossc.nimbus.service.interpreter.Interpreter Interpreter}�T�[�r�X�̃T�[�r�X�����擾����B<p>
     *
     * @return Interpreter�T�[�r�X�̃T�[�r�X��
     */
    public ServiceName getInterpreterServiceName();
    
    /**
     * StubResourceManager����_�E�����[�h�������\�[�X�t�@�C����z�u����f�B���N�g����ݒ肷��B<p>
     * �f�t�H���g�́A�T�[�r�X��`�t�@�C���̏ꏊ�ɃX�^�uID�Ńf�B���N�g����z�u����B<br>
     *
     * @param dir �f�B���N�g��
     */
    public void setResourceDirectory(File dir);
    
    /**
     * StubResourceManager����_�E�����[�h�������\�[�X�t�@�C����z�u����f�B���N�g�����擾����B<p>
     *
     * @return �f�B���N�g��
     */
    public File getResourceDirectory();
    
    /**
     * HTTP���X�|���X�Ɏw�肷��HTTP�o�[�W������ݒ肷��B<p>
     *
     * @param version HTTP�o�[�W����
     */
    public void setHttpVersion(String version);
    
    /**
     * HTTP���X�|���X�Ɏw�肷��HTTP�o�[�W�������擾����B<p>
     *
     * @return HTTP�o�[�W����
     */
    public String getHttpVersion();
    
    /**
     * ���ʂŐݒ肷��HTTP���X�|���X�w�b�_��ݒ肷��B<p>
     *
     * @param name �w�b�_��
     * @param values �w�b�_�l�̔z��
     */
    public void setHttpHeaders(String name, String[] values);
    
    /**
     * ���ʂŐݒ肷��HTTP���X�|���X�w�b�_���擾����B<p>
     *
     * @param name �w�b�_��
     * @return �w�b�_�l�̔z��
     */
    public String[] getHttpHeaders(String name);
    
    /**
     * ���ʂŐݒ肷��HTTP���X�|���X�w�b�_��������擾����B<p>
     *
     * @return HTTP�w�b�_������
     */
    public String getHttpHeader();
    
    /**
     * �o�C�i���Ŏw�肷�郌�X�|���X�t�@�C���̊g���q��ݒ肷��B<p>
     *
     * @param exts �g���q�̔z��
     */
    public void setBinaryFileExtensions(String[] exts);
    
    /**
     * �o�C�i���Ŏw�肷�郌�X�|���X�t�@�C���̊g���q���擾����B<p>
     *
     * @return �g���q�̔z��
     */
    public String[] getBinaryFileExtensions();
    
    /**
     * �������N�G�X�g�̌J��Ԃ����������ǂ����𔻒肷��B<p>
     *
     * @return true�̏ꍇ�A����
     */
    public boolean isAllowRepeatRequest();
    /**
     * �������N�G�X�g�̌J��Ԃ����������ǂ�����ݒ肷��B<p>
     *
     * @param isAllow �����ꍇ�Atrue
     */
    public void setAllowRepeatRequest(boolean isAllow);
    
    /**
     * �}���`�X���b�h���������S�ɍs�����ǂ����𔻒肷��B<p>
     *
     * @return true�̏ꍇ�A���S�ɍs��
     */
    public boolean isSafeMultithread();
    /**
     * �}���`�X���b�h���������S�ɍs�����ǂ�����ݒ肷��B<p>
     * �f�t�H���g�́Atrue�B<br>
     *
     * @param isSafe ���S�ɍs���ꍇ�Atrue
     */
    public void setSafeMultithread(boolean isSafe);
    
    /**
     * ���N�G�X�g���t�@�C���ɕۑ����邩�ǂ����𔻒肷��B<p>
     *
     * @return true�̏ꍇ�A�ۑ�����
     */
    public boolean isSaveRequestFile();
    /**
     * ���N�G�X�g���t�@�C���ɕۑ����邩�ǂ�����ݒ肷��B<p>
     * �f�t�H���g�́Atrue�B<br>
     *
     * @param isSave �ۑ�����ꍇ�Atrue
     */
    public void setSaveRequestFile(boolean isSave);
    
    /**
     * �ǂݍ��񂾃��X�|���X���L���b�V�����邩�ǂ����𔻒肷��B<p>
     *
     * @return true�̏ꍇ�A�L���b�V������
     */
    public boolean isCacheResponse();
    
    /**
     * �ǂݍ��񂾃��X�|���X���L���b�V�����邩�ǂ�����ݒ肷��B<p>
     * �f�t�H���g�́Afalse�ŁA�L���b�V�����Ȃ��B<br>
     *
     * @param isCache �L���b�V������ꍇ�Atrue
     */
    public void setCacheResponse(boolean isCache);
}
