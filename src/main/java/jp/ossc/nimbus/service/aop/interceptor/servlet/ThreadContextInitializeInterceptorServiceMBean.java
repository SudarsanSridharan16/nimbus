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
import java.util.Properties;

import jp.ossc.nimbus.core.*;

/**
 * {@link ThreadContextInitializeInterceptorService}��MBean�C���^�t�F�[�X�B<p>
 * 
 * @author M.Takata
 * @see ThreadContextInitializeInterceptorService
 */
public interface ThreadContextInitializeInterceptorServiceMBean
 extends ServletFilterInterceptorServiceMBean{
    
    /**
     * �X���b�h�P�ʂ�{@link jp.ossc.nimbus.service.context.Context Context}�T�[�r�X�̃T�[�r�X����ݒ肷��B<p>
     *
     * @param name Context�T�[�r�X�̃T�[�r�X��
     */
    public void setThreadContextServiceName(ServiceName name);
    
    /**
     * �X���b�h�P�ʂ�{@link jp.ossc.nimbus.service.context.Context Context}�T�[�r�X�̃T�[�r�X�����擾����B<p>
     *
     * @return Context�T�[�r�X�̃T�[�r�X��
     */
    public ServiceName getThreadContextServiceName();
    
    /**
     * �R�[�h�}�X�^��{@link jp.ossc.nimbus.service.context.Context Context}�T�[�r�X�ɐݒ肷�邽�߂�{@link jp.ossc.nimbus.service.codemaster.CodeMasterFinder CodeMasterFinder}�T�[�r�X�̃T�[�r�X����ݒ肷��B<p>
     *
     * @param name CodeMasterFinder�T�[�r�X�̃T�[�r�X��
     * @see ThreadContextKey#CODEMASTER
     */
    public void setCodeMasterFinderServiceName(ServiceName name);
    
    /**
     * {@link jp.ossc.nimbus.service.codemaster.CodeMasterFinder CodeMasterFinder}�T�[�r�X�̃T�[�r�X�����擾����B<p>
     *
     * @return CodeMasterFinder�T�[�r�X�̃T�[�r�X��
     */
    public ServiceName getCodeMasterFinderServiceName();
    
    /**
     * ���N�G�X�gID��{@link jp.ossc.nimbus.service.context.Context Context}�T�[�r�X�ɐݒ肷�邽�߂�{@link jp.ossc.nimbus.service.sequence.Sequence Sequence}�T�[�r�X�̃T�[�r�X����ݒ肷��B<p>
     *
     * @param name Sequence�T�[�r�X�̃T�[�r�X��
     * @see ThreadContextKey#REQUEST_ID
     */
    public void setSequenceServiceName(ServiceName name);
    
    /**
     * {@link jp.ossc.nimbus.service.sequence.Sequence Sequence}�T�[�r�X�̃T�[�r�X�����擾����B<p>
     *
     * @return Sequence�T�[�r�X�̃T�[�r�X��
     */
    public ServiceName getSequenceServiceName();
    
    /**
     * {@link jp.ossc.nimbus.service.context.Context Context}�T�[�r�X�ɐݒ肷��T�[�r�X��ݒ肷��B<p>
     *
     * @param names Context�T�[�r�X�ɐݒ肷��L�[�ƃT�[�r�X�̃T�[�r�X���̃}�b�s���O�B�R���e�L�X�g�L�[=�T�[�r�X��
     */
    public void setContextValueServiceNames(ServiceNameRef[] names);
    
    /**
     * {@link jp.ossc.nimbus.service.context.Context Context}�T�[�r�X�ɐݒ肷��T�[�r�X���擾����B<p>
     *
     * @return Context�T�[�r�X�ɐݒ肷��L�[�ƃT�[�r�X�̃T�[�r�X���̃}�b�s���O
     */
    public ServiceNameRef[] getContextValueServiceNames();
    
    /**
     * ���N�G�X�g�p�����[�^��{@link jp.ossc.nimbus.service.context.Context Context}�T�[�r�X�ɐݒ肷��}�b�s���O��ݒ肷��B<p>
     *
     * @param map Context�T�[�r�X�ɐݒ肷��L�[�ƃ��N�G�X�g�p�����[�^���̃}�b�s���O�B�R���e�L�X�g�L�[=���N�G�X�g�p�����[�^��
     */
    public void setContextValueRequestParameter(Properties map);
    
    /**
     * ���N�G�X�g�p�����[�^��{@link jp.ossc.nimbus.service.context.Context Context}�T�[�r�X�ɐݒ肷��}�b�s���O���擾����B<p>
>
     *
     * @return Context�T�[�r�X�ɐݒ肷��L�[�ƃ��N�G�X�g�p�����[�^���̃}�b�s���O
     */
    public Properties getContextValueRequestParameter();
    
    /**
     * {@link jp.ossc.nimbus.service.context.Context Context}�T�[�r�X�ɐݒ肷��l��ݒ肷��B<p>
     *
     * @param mapping Context�T�[�r�X�ɐݒ肷��L�[�ƒl�̃}�b�s���O�B�R���e�L�X�g�L�[=�l
     */
    public void setContextValueMapping(Map mapping);
    
    /**
     * {@link jp.ossc.nimbus.service.context.Context Context}�T�[�r�X�ɐݒ肷��l���擾����B<p>
     *
     * @return Context�T�[�r�X�ɐݒ肷��L�[�ƒl�̃}�b�s���O
     */
    public Map getContextValueMapping();
    
    /**
     * {@link jp.ossc.nimbus.service.context.Context Context}�T�[�r�X�ɐݒ肷��L�[�ƒl��ݒ肷��B<p>
     *
     * @param key �L�[
     * @param value �l
     */
    public void setContextValue(String key, Object value);
    
    /**
     * {@link jp.ossc.nimbus.service.context.Context Context}�T�[�r�X�ɐݒ肷��l���擾����B<p>
     *
     * @param key �L�[
     * @return �l
     */
    public Object getContextValue(String key);
    
    /**
     * �R���e�L�X�g�p�X��{@link jp.ossc.nimbus.service.context.Context Context}�T�[�r�X�ɐݒ肷�邩�ǂ�����ݒ肷��B<p>
     * �f�t�H���g�́Atrue�B<br>
     *
     * @param isOutput Context�T�[�r�X�ɐݒ肷��ꍇtrue
     * @see ThreadContextKey#CONTEXT_PATH
     */
    public void setOutputContextPath(boolean isOutput);
    
    /**
     * �R���e�L�X�g�p�X��{@link jp.ossc.nimbus.service.context.Context Context}�T�[�r�X�ɐݒ肷�邩�ǂ����𔻒肷��B<p>
     *
     * @return true�̏ꍇ�AContext�T�[�r�X�ɐݒ肷��
     */
    public boolean isOutputContextPath();
    
    /**
     * �T�[�u���b�g�p�X��{@link jp.ossc.nimbus.service.context.Context Context}�T�[�r�X�ɐݒ肷�邩�ǂ�����ݒ肷��B<p>
     * �f�t�H���g�́Atrue�B<br>
     *
     * @param isOutput Context�T�[�r�X�ɐݒ肷��ꍇtrue
     * @see ThreadContextKey#SERVLET_PATH
     */
    public void setOutputServletPath(boolean isOutput);
    
    /**
     * �T�[�u���b�g�p�X��{@link jp.ossc.nimbus.service.context.Context Context}�T�[�r�X�ɐݒ肷�邩�ǂ����𔻒肷��B<p>
     *
     * @return true�̏ꍇ�AContext�T�[�r�X�ɐݒ肷��
     */
    public boolean isOutputServletPath();
    
    /**
     * �Z�b�V����ID��{@link jp.ossc.nimbus.service.context.Context Context}�T�[�r�X�ɐݒ肷�邩�ǂ�����ݒ肷��B<p>
     * �f�t�H���g�́Atrue�B<br>
     *
     * @param isOutput Context�T�[�r�X�ɐݒ肷��ꍇtrue
     * @see ThreadContextKey#SESSION_ID
     */
    public void setOutputSessionID(boolean isOutput);
    
    /**
     * �Z�b�V����ID��{@link jp.ossc.nimbus.service.context.Context Context}�T�[�r�X�ɐݒ肷�邩�ǂ����𔻒肷��B<p>
     *
     * @return true�̏ꍇ�AContext�T�[�r�X�ɐݒ肷��
     */
    public boolean isOutputSessionID();
    
    /**
     * �X���b�h�O���[�v����{@link jp.ossc.nimbus.service.context.Context Context}�T�[�r�X�ɐݒ肷�邩�ǂ�����ݒ肷��B<p>
     * �f�t�H���g�́Atrue�B<br>
     *
     * @param isOutput Context�T�[�r�X�ɐݒ肷��ꍇtrue
     * @see ThreadContextKey#THREAD_GROUP_NAME
     */
    public void setOutputThreadGroupName(boolean isOutput);
    
    /**
     * �X���b�h�O���[�v����{@link jp.ossc.nimbus.service.context.Context Context}�T�[�r�X�ɐݒ肷�邩�ǂ����𔻒肷��B<p>
     *
     * @return true�̏ꍇ�AContext�T�[�r�X�ɐݒ肷��
     */
    public boolean isOutputThreadGroupName();
    
    /**
     * �X���b�h����{@link jp.ossc.nimbus.service.context.Context Context}�T�[�r�X�ɐݒ肷�邩�ǂ�����ݒ肷��B<p>
     * �f�t�H���g�́Atrue�B<br>
     *
     * @param isOutput Context�T�[�r�X�ɐݒ肷��ꍇtrue
     * @see ThreadContextKey#THREAD_NAME
     */
    public void setOutputThreadName(boolean isOutput);
    
    /**
     * �X���b�h����{@link jp.ossc.nimbus.service.context.Context Context}�T�[�r�X�ɐݒ肷�邩�ǂ����𔻒肷��B<p>
     *
     * @return true�̏ꍇ�AContext�T�[�r�X�ɐݒ肷��
     */
    public boolean isOutputThreadName();
    
    /**
     * �Z�b�V�����̏����擾���邽�߂ɃZ�b�V���������݂��Ȃ��ꍇ�́A�Z�b�V�����𐶐����邩�ǂ�����ݒ肷��B<p>
     * �f�t�H���g�́Afalse�B<br>
     *
     * @param isNew �Z�b�V�����𐶐�����ꍇ�́Atrue
     */
    public void setNewSession(boolean isNew);
    
    /**
     * �Z�b�V�����̏����擾���邽�߂ɃZ�b�V���������݂��Ȃ��ꍇ�́A�Z�b�V�����𐶐����邩�ǂ����𔻒肷��B<p>
     *
     * @return true�̏ꍇ�A�Z�b�V�����𐶐�����
     */
    public boolean isNewSession();
    
    /**
     * �ċA�I�ɌĂяo���ꂽ�ꍇ�ɁA�X���b�h�R���e�L�X�g�̏��������s�����ǂ�����ݒ肷��B<p>
     * �f�t�H���g��true�ŏ���������B<br>
     *
     * @param isInitialize ����������ꍇ�́Atrue
     */
    public void setInitializeRecursiveCall(boolean isInitialize);
    
    /**
     * �ċA�I�ɌĂяo���ꂽ�ꍇ�ɁA�X���b�h�R���e�L�X�g�̏��������s�����ǂ����𔻒肷��B<p>
     *
     * @return true�̏ꍇ�́A����������
     */
    public boolean isInitializeRecursiveCall();
    
    /**
     * {@link jp.ossc.nimbus.service.context.Context Context}�T�[�r�X��{@link jp.ossc.nimbus.service.context.Context#clear() clear()}���Ăяo�����ǂ�����ݒ肷��B<br>
     * �f�t�H���g�́Atrue��clear����B<br>
     *
     * @param isClear clear����ꍇ�́Atrue
     */
    public void setClear(boolean isClear);
    
    /**
     * {@link jp.ossc.nimbus.service.context.Context Context}�T�[�r�X��{@link jp.ossc.nimbus.service.context.Context#clear() clear()}���Ăяo�����ǂ����𔻒肷��B<br>
     *
     * @return true�̏ꍇ�́Aclear����
     */
    public boolean isClear();
}
