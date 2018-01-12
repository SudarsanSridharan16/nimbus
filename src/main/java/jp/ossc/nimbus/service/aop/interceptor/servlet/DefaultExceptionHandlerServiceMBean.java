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

import jp.ossc.nimbus.core.ServiceBaseMBean;
import jp.ossc.nimbus.core.ServiceName;

/**
 * {@link DefaultExceptionHandlerService}��MBean�C���^�t�F�[�X�B<p>
 * 
 * @author M.Takata
 * @see DefaultExceptionHandlerService
 */
public interface DefaultExceptionHandlerServiceMBean
 extends ServiceBaseMBean{
    
    /**
     * ����������O���W���[�i���ɏo�͂���ۂ̃W���[�i���L�[�̃f�t�H���g�l�B<p>
     */
    public static final String DEFAULT_EXCEPTION_JOURNAL_KEY = "Exception";
    
    /**
     * ����������O���W���[�i���ɏo�͂���{@link jp.ossc.nimbus.service.journal.Journal Journal}�T�[�r�X�̃T�[�r�X����ݒ肷��B<p>
     * �ݒ肵�Ȃ��ꍇ�́A�W���[�i���o�͂��Ȃ��B<br>
     *
     * @param name Journal�T�[�r�X�̃T�[�r�X��
     */
    public void setJournalServiceName(ServiceName name);
    
    /**
     * ����������O���W���[�i���ɏo�͂���{@link jp.ossc.nimbus.service.journal.Journal Journal}�T�[�r�X�̃T�[�r�X�����擾����B<p>
     *
     * @return Journal�T�[�r�X�̃T�[�r�X��
     */
    public ServiceName getJournalServiceName();
    
    /**
     * ����������O���W���[�i���ɏo�͂���ۂ̃W���[�i���̃L�[��ݒ肷��B<p>
     * �f�t�H���g�ł́A{@link #DEFAULT_EXCEPTION_JOURNAL_KEY}�B<br>
     *
     * @param key ����������O�̃W���[�i���L�[
     */
    public void setExceptionJournalKey(String key);
    
    /**
     * ����������O���W���[�i���ɏo�͂���ۂ̃W���[�i���̃L�[���擾����B<p>
     *
     * @return ����������O�̃W���[�i���L�[
     */
    public String getExceptionJournalKey();
    
    /**
     * ����������O���W���[�i���ɏo�͂���ۂ�{@link jp.ossc.nimbus.service.journal.editorfinder.EditorFinder EditorFinder}�T�[�r�X�̃T�[�r�X����ݒ肷��B<p>
     * �ݒ肵�Ȃ��ꍇ�́A�W���[�i���T�[�r�X�ɐݒ肳��Ă���EditorFinder���K�p�����B<br>
     *
     * @param name EditorFinder�T�[�r�X�̃T�[�r�X��
     */
    public void setExceptionEditorFinderServiceName(ServiceName name);
    
    /**
     * ����������O���W���[�i���ɏo�͂���ۂ�{@link jp.ossc.nimbus.service.journal.editorfinder.EditorFinder EditorFinder}�T�[�r�X�̃T�[�r�X�����擾����B<p>
     *
     * @return EditorFinder�T�[�r�X�̃T�[�r�X��
     */
    public ServiceName getExceptionEditorFinderServiceName();
    
    /**
     * ����������O�����O�ɏo�͂���ۂ̃��b�Z�[�WID��ݒ肷��B<p>
     * �ݒ肵�Ȃ��ꍇ�́A���O�o�͂��Ȃ��B<br>
     *
     * @param id ����������O�����O�ɏo�͂���ۂ̃��b�Z�[�WID
     */
    public void setLogMessageCode(String id);
    
    /**
     * ����������O�����O�ɏo�͂���ۂ̃��b�Z�[�WID���擾����B<p>
     *
     * @return ����������O�����O�ɏo�͂���ۂ̃��b�Z�[�WID
     */
    public String getLogMessageCode();
    
    /**
     * ����������O�����O�ɏo�͂���ۂ̖��ߍ��݃p�����[�^��ݒ肷��B<p>
     *
     * @param args ����������O�����O�ɏo�͂���ۂ̖��ߍ��݃p�����[�^
     */
    public void setLogMessageArguments(String[] args);
    
    /**
     * ����������O�����O�ɏo�͂���ۂ̖��ߍ��݃p�����[�^���擾����B<p>
     *
     * @return ����������O�����O�ɏo�͂���ۂ̖��ߍ��݃p�����[�^
     */
    public String[] getLogMessageArguments();
    
    /**
     * ����������O�����O�ɏo�͂���ۂɁA��O�̃X�^�b�N�g���[�X�����O�ɏo�͂��邩�ǂ�����ݒ肷��B<p>
     * �f�t�H���g�ł́Atrue�B<br>
     *
     * @param isOutput ��O�̃X�^�b�N�g���[�X�����O�ɏo�͂���ꍇtrue
     */
    public void setOutputStackTraceLog(boolean isOutput);
    
    /**
     * ����������O�����O�ɏo�͂���ۂɁA��O�̃X�^�b�N�g���[�X�����O�ɏo�͂��邩�ǂ����𔻒肷��B<p>
     *
     * @return true�̏ꍇ�A��O�̃X�^�b�N�g���[�X�����O�ɏo�͂���
     */
    public boolean isOutputStackTraceLog();
    
    /**
     * HTTP���X�|���X�̃X�e�[�^�X��ݒ肷��B<p>
     * �ݒ肵�Ȃ��ꍇ�́A�X�e�[�^�X�͕ύX���Ȃ��B<br>
     *
     * @param status HTTP���X�|���X�̃X�e�[�^�X
     */
    public void setHttpResponseStatus(int status);
    
    /**
     * HTTP���X�|���X�̃X�e�[�^�X���擾����B<p>
     *
     * @return HTTP���X�|���X�̃X�e�[�^�X
     */
    public int getHttpResponseStatus();
    
    /**
     * HTTP���X�|���X�̃X�e�[�^�X���b�Z�[�W��ݒ肷��B<p>
     * HTTP���X�|���X�̃X�e�[�^�X���ݒ肳��Ă��Ȃ��ꍇ�́A�����B<br>
     *
     * @param message HTTP���X�|���X�̃X�e�[�^�X���b�Z�[�W
     */
    public void setHttpResponseStatusMessage(String message);
    
    /**
     * HTTP���X�|���X�̃X�e�[�^�X���b�Z�[�W���擾����B<p>
     *
     * @return HTTP���X�|���X�̃X�e�[�^�X���b�Z�[�W
     */
    public String getHttpResponseStatusMessage();
    
    /**
     * �t�H���[�h����p�X��ݒ肷��B<p>
     * �ݒ肵�Ȃ��ꍇ�́A�t�H���[�h���Ȃ��B<br>
     *
     * @param path �t�H���[�h����p�X
     */
    public void setForwardPath(String path);
    
    /**
     * �t�H���[�h����p�X���擾����B<p>
     *
     * @return �t�H���[�h����p�X
     */
    public String getForwardPath();
    
    /**
     * ���_�C���N�g����p�X��ݒ肷��B<p>
     * �ݒ肵�Ȃ��ꍇ�́A���_�C���N�g���Ȃ��B<br>
     *
     * @param path ���_�C���N�g����p�X
     */
    public void setRedirectPath(String path);
    
    /**
     * ���_�C���N�g����p�X���擾����B<p>
     *
     * @return ���_�C���N�g����p�X
     */
    public String getRedirectPath();
    
    /**
     * ��O��throw���邩�ǂ�����ݒ肷��B<p>
     * �f�t�H���g�́Afalse�B<br>
     *
     * @param isThrow throw����ꍇ�́Atrue
     */
    public void setThrowException(boolean isThrow);
    
    /**
     * ��O��throw���邩�ǂ����𔻒肷��B<p>
     *
     * @return true�̏ꍇ�́Athrow����
     */
    public boolean isThrowException();
    
    /**
     * �����I�u�W�F�N�g�����N�G�X�g�����ɐݒ肷�鎞�Ɏg�p���鑮������ݒ肷��B<p>
     * �f�t�H���g�l�́A{@link StreamExchangeInterceptorServiceMBean#DEFAULT_RESPONSE_OBJECT_ATTRIBUTE_NAME}�B<br>
     *
     * @param name ������
     * @see StreamExchangeInterceptorServiceMBean#DEFAULT_RESPONSE_OBJECT_ATTRIBUTE_NAME
     */
    public void setResponseObjectAttributeName(String name);
    
    /**
     * �����I�u�W�F�N�g�����N�G�X�g�����ɐݒ肷�鎞�Ɏg�p���鑮�������擾����B<p>
     *
     * @return ������
     */
    public String getResponseObjectAttributeName();
    
    /**
     * ���N�G�X�g�����ɐݒ肷�鉞���I�u�W�F�N�g��ݒ肷��B<p>
     *
     * @param obj �����I�u�W�F�N�g
     */
    public void setResponseObject(Object obj);
    
    /**
     * ���N�G�X�g�����ɐݒ肷�鉞���I�u�W�F�N�g���擾����B<p>
     *
     * @return �����I�u�W�F�N�g
     */
    public Object getResponseObject();
    
    /**
     * ���N�G�X�g�����ɐݒ肷�鉞���I�u�W�F�N�g�̃T�[�r�X����ݒ肷��B<p>
     *
     * @param name �����I�u�W�F�N�g�̃T�[�r�X��
     */
    public void setResponseObjectServiceName(ServiceName name);
    
    /**
     * ���N�G�X�g�����ɐݒ肷�鉞���I�u�W�F�N�g�̃T�[�r�X�����擾����B<p>
     *
     * @return �����I�u�W�F�N�g�̃T�[�r�X��
     */
    public ServiceName getResponseObjectServiceName();
    
    /**
     * ��O����v���p�e�B�l���擾���ĉ����I�u�W�F�N�g�̃v���p�e�B�ɐݒ肷��}�b�s���O��ݒ肷��B<p>
     *
     * @param mapping ��O����擾����v���p�e�B=�����I�u�W�F�N�g�ɐݒ肷��v���p�e�B
     */
    public void setExceptionAndResponseObjectPropertyMapping(Map mapping);
    
    /**
     * ��O����v���p�e�B�l���擾���ĉ����I�u�W�F�N�g�̃v���p�e�B�ɐݒ肷��}�b�s���O���擾����B<p>
     *
     * @return ��O����擾����v���p�e�B=�����I�u�W�F�N�g�ɐݒ肷��v���p�e�B
     */
    public Map getExceptionAndResponseObjectPropertyMapping();
}
