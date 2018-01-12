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
package jp.ossc.nimbus.service.aop.interceptor.servlet;

import java.util.Map;

import jp.ossc.nimbus.core.*;

/**
 * {@link BlockadeInterceptorService}��MBean�C���^�t�F�[�X�B<p>
 * 
 * @author M.Takata
 * @see BlockadeInterceptorService
 */
public interface BlockadeInterceptorServiceMBean
 extends ServletFilterInterceptorServiceMBean{
    
    public static final String DEFAULT_PROPERTY_NAME_PATH    = "path";
    public static final String DEFAULT_PROPERTY_NAME_STATE   = "state";
    public static final String DEFAULT_PROPERTY_NAME_MESSAGE = "message";
    
    /**
     * �Ǐ�ԁF�J���B<p>
     */
    public static final int BLOCKADE_STATE_OPEN        = 0;
    
    /**
     * �Ǐ�ԁF���S�ǁB<p>
     * �S�Ẵ��[�U���ǂ���Ă����ԁB<br>
     */
    public static final int BLOCKADE_STATE_ALL_CLOSE       = 1;
    
    /**
     * �Ǐ�ԁF�����ǁB<p>
     * �S�Ẵ��[�U�������ǂ���Ă����ԁB<br>
     */
    public static final int BLOCKADE_STATE_PART_CLOSE       = 2;
    
    /**
     * �Ǐ�ԁF�e�X�g�ǁB<p>
     * �������[�U�ȊO�͕ǂ���Ă����ԁB<br>
     */
    public static final int BLOCKADE_STATE_TEST_ALL_CLOSE   = 3;
    
    /**
     * �Ǐ�ԁF�e�X�g�����ǁB<p>
     * �������[�U�ȊO�͕����ǂ���Ă����ԁB<br>
     */
    public static final int BLOCKADE_STATE_TEST_PART_CLOSE   = 4;
    
    /**
     * �v���I�u�W�F�N�g�����N�G�X�g��������擾���鎞�Ɏg�p���鑮������ݒ肷��B<p>
     * �f�t�H���g�l�́A{@link StreamExchangeInterceptorServiceMBean#DEFAULT_REQUEST_OBJECT_ATTRIBUTE_NAME}�B<br>
     *
     * @param name ������
     * @see StreamExchangeInterceptorServiceMBean#DEFAULT_REQUEST_OBJECT_ATTRIBUTE_NAME
     */
    public void setRequestObjectAttributeName(String name);
    
    /**
     * �v���I�u�W�F�N�g�����N�G�X�g��������擾���鎞�Ɏg�p���鑮�������擾����B<p>
     *
     * @return ������
     */
    public String getRequestObjectAttributeName();
    
    /**
     * ���N�G�X�g�����̃��[�U����肷��v���p�e�B�ƁA�������[�U�R�[�h�}�X�^�̃��[�U����肷��v���p�e�B�̃}�b�s���O��ݒ肷��B<p>
     * �e�X�g�J�����T�|�[�g����ꍇ�́A�ݒ肷��B<br>
     *
     * @param mapping ���N�G�X�g�����̃��[�U����肷��v���p�e�B=�������[�U�R�[�h�}�X�^�̃��[�U����肷��v���p�e�B
     */
    public void setSpecialUserMapping(Map mapping);
    
    /**
     * ���N�G�X�g�����̃��[�U����肷��v���p�e�B�ƁA�������[�U�R�[�h�}�X�^�̃��[�U����肷��v���p�e�B�̃}�b�s���O���擾����B<p>
     *
     * @return ���N�G�X�g�����̃��[�U����肷��v���p�e�B=�������[�U�R�[�h�}�X�^�̃��[�U����肷��v���p�e�B
     */
    public Map getSpecialUserMapping();
    
    /**
     * ���N�G�X�g�����̕ǃ��R�[�h����肷��v���p�e�B�ƁA�ǃR�[�h�}�X�^�̕ǃ��R�[�h����肷��v���p�e�B�̃}�b�s���O��ݒ肷��B<p>
     * �ǃR�[�h�}�X�^���A���N�G�X�g��������i�荞�݂����ꍇ�ɁA�ݒ肷��B<br>
     *
     * @param mapping ���N�G�X�g�����̕ǃ��R�[�h����肷��v���p�e�B=�ǃR�[�h�}�X�^�̕ǃ��R�[�h����肷��v���p�e�B
     */
    public void setBlockadeMapping(Map mapping);
    
    /**
     * ���N�G�X�g�����̕ǃ��R�[�h����肷��v���p�e�B�ƁA�ǃR�[�h�}�X�^�̕ǃ��R�[�h����肷��v���p�e�B�̃}�b�s���O���擾����B<p>
     *
     * @return ���N�G�X�g�����̕ǃ��R�[�h����肷��v���p�e�B=�ǃR�[�h�}�X�^�̕ǃ��R�[�h����肷��v���p�e�B
     */
    public Map getBlockadeMapping();
    
    /**
     * {@link jp.ossc.nimbus.service.codemaster.CodeMasterFinder CodeMasterFinder}�T�[�r�X�̃T�[�r�X����ݒ肷��B<p>
     * �R�[�h�}�X�^�̓ǂݎ���ѐ���ۏ؂������ꍇ�́A���̑����̑���ɁA{@link #setThreadContextServiceName(ServiceName)}��ݒ肷��B<br>
     *
     * @param name CodeMasterFinder�T�[�r�X�̃T�[�r�X��
     */
    public void setCodeMasterFinderServiceName(ServiceName name);
    
    /**
     * {@link jp.ossc.nimbus.service.codemaster.CodeMasterFinder CodeMasterFinder}�T�[�r�X�̃T�[�r�X�����擾����B<p>
     *
     * @return CodeMasterFinder�T�[�r�X�̃T�[�r�X��
     */
    public ServiceName getCodeMasterFinderServiceName();
    
    /**
     * {@link jp.ossc.nimbus.service.context.ThreadContextService ThreadContextService}�T�[�r�X�̃T�[�r�X����ݒ肷��B<p>
     * �R�[�h�}�X�^�̓ǂݎ���ѐ���ۏ؂������ꍇ�́A{@link #setCodeMasterFinderServiceName(ServiceName)}�̑���ɂ��̑�����ݒ肷��B<br>
     *
     * @param name ThreadContextService�T�[�r�X�̃T�[�r�X��
     */
    public void setThreadContextServiceName(ServiceName name);
    
    /**
     * {@link jp.ossc.nimbus.service.context.ThreadContextService ThreadContextService}�T�[�r�X�̃T�[�r�X�����擾����B<p>
     *
     * @return ThreadContextService�T�[�r�X�̃T�[�r�X��
     */
    public ServiceName getThreadContextServiceName();
    
    /**
     * �ǃR�[�h�}�X�^�̃R�[�h�}�X�^�L�[��ݒ肷��B<p>
     *
     * @param key �R�[�h�}�X�^�L�[
     */
    public void setBlockadeCodeMasterKey(String key);
    
    /**
     * �ǃR�[�h�}�X�^�̃R�[�h�}�X�^�L�[���擾����B<p>
     *
     * @return �R�[�h�}�X�^�L�[
     */
    public String getBlockadeCodeMasterKey();
    
    /**
     * �������[�U�R�[�h�}�X�^�̃R�[�h�}�X�^�L�[��ݒ肷��B<p>
     * �e�X�g�J�����T�|�[�g����ꍇ�́A�ݒ肷��B<br>
     *
     * @param key �R�[�h�}�X�^�L�[
     */
    public void setSpecialUserCodeMasterKey(String key);
    
    /**
     * �������[�U�R�[�h�}�X�^�̃R�[�h�}�X�^�L�[���擾����B<p>
     *
     * @return �R�[�h�}�X�^�L�[
     */
    public String getSpecialUserCodeMasterKey();
    
    /**
     * �ǃR�[�h�}�X�^�̃p�X��\���v���p�e�B����ݒ肷��B<p>
     * �f�t�H���g�́A{@link #DEFAULT_PROPERTY_NAME_PATH}
     *
     * @param name �p�X��\���v���p�e�B��
     * @see #DEFAULT_PROPERTY_NAME_PATH
     */
    public void setPathPropertyName(String name);
    
    /**
     * �ǃR�[�h�}�X�^�̃p�X��\���v���p�e�B�����擾����B<p>
     *
     * @return �p�X��\���v���p�e�B��
     */
    public String getPathPropertyName();
    
    /**
     * �ǃR�[�h�}�X�^�̕Ǐ�Ԃ�\���v���p�e�B����ݒ肷��B<p>
     * �f�t�H���g�́A{@link #DEFAULT_PROPERTY_NAME_STATE}
     *
     * @param name �Ǐ�Ԃ�\���v���p�e�B��
     * @see #DEFAULT_PROPERTY_NAME_STATE
     */
    public void setStatePropertyName(String name);
    
    /**
     * �ǃR�[�h�}�X�^�̕Ǐ�Ԃ�\���v���p�e�B�����擾����B<p>
     *
     * @return �Ǐ�Ԃ�\���v���p�e�B��
     */
    public String getStatePropertyName();
    
    /**
     * �ǃR�[�h�}�X�^�̕ǃ��b�Z�[�W��\���v���p�e�B����ݒ肷��B<p>
     * �f�t�H���g�́A{@link #DEFAULT_PROPERTY_NAME_MESSAGE}
     *
     * @param name �ǃ��b�Z�[�W��\���v���p�e�B��
     * @see #DEFAULT_PROPERTY_NAME_MESSAGE
     */
    public void setMessagePropertyName(String name);
    
    /**
     * �ǃR�[�h�}�X�^�̕ǃ��b�Z�[�W��\���v���p�e�B�����擾����B<p>
     *
     * @return �ǃ��b�Z�[�W��\���v���p�e�B��
     */
    public String getMessagePropertyName();
    
    /**
     * "�Ǐ�ԁF�J��"��\���X�e�[�^�X�l���擾����B<p>
     * 
     * @return  "�Ǐ�ԁF�J��"��\���X�e�[�^�X�l
     */
    public int getStateOpen();
    
    /**
     * "�Ǐ�ԁF�J��"��\���X�e�[�^�X�l��ݒ肷��B<p>
     * 
     * @param state "�Ǐ�ԁF�J��"��\���X�e�[�^�X�l
     */
    public void setStateOpen(int state);
    
    /**
     * "�Ǐ�ԁF���S��"��\���X�e�[�^�X�l���擾����B<p>
     * 
     * @return  "�Ǐ�ԁF���S��"��\���X�e�[�^�X�l
     */
    public int getStateAllClose();
    
    /**
     * "�Ǐ�ԁF���S��"��\���X�e�[�^�X�l��ݒ肷��B<p>
     * 
     * @param state "�Ǐ�ԁF���S��"��\���X�e�[�^�X�l
     */
    public void setStateAllClose(int state);
    
    /**
     * "�Ǐ�ԁF������"��\���X�e�[�^�X�l���擾����B<p>
     * 
     * @return  "�Ǐ�ԁF������"��\���X�e�[�^�X�l
     */
    public int getStatePartClose();
    
    /**
     * "�Ǐ�ԁF������"��\���X�e�[�^�X�l��ݒ肷��B<p>
     * 
     * @param state "�Ǐ�ԁF������"��\���X�e�[�^�X�l
     */
    public void setStatePartClose(int state);
    
    /**
     * "�Ǐ�ԁF�e�X�g��"��\���X�e�[�^�X�l���擾����B<p>
     * 
     * @return  "�Ǐ�ԁF�e�X�g��"��\���X�e�[�^�X�l
     */
    public int getStateTestAllClose();
    
    /**
     * "�Ǐ�ԁF�e�X�g��"��\���X�e�[�^�X�l��ݒ肷��B<p>
     * 
     * @param state "�Ǐ�ԁF�e�X�g��"��\���X�e�[�^�X�l
     */
    public void setStateTestAllClose(int state);
    
    /**
     * "�Ǐ�ԁF�e�X�g������"��\���X�e�[�^�X�l���擾����B<p>
     * 
     * @return  "�Ǐ�ԁF�e�X�g������"��\���X�e�[�^�X�l
     */
    public int getStateTestPartClose();
    
    /**
     * "�Ǐ�ԁF�e�X�g������"��\���X�e�[�^�X�l��ݒ肷��B<p>
     * 
     * @param state "�Ǐ�ԁF�e�X�g������"��\���X�e�[�^�X�l
     */
    public void setStateTestPartClose(int state);
    
}