/*
 * This software is distributed under following license based on modified BSD
 * style license.
 * ----------------------------------------------------------------------
 * 
 * Copyright 2009 The Nimbus2 Project. All rights reserved.
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
 * policies, either expressed or implied, of the Nimbus2 Project.
 */
package jp.ossc.nimbus.service.rest;

import java.util.Map;

import jp.ossc.nimbus.core.ServiceBaseMBean;
import jp.ossc.nimbus.core.ServiceName;

/**
 * {@link BeanFlowRestServerService}��MBean�C���^�t�F�[�X�B<p>
 *
 * @author M.Takata
 */
public interface BeanFlowRestServerServiceMBean extends ServiceBaseMBean{
    
    /**
     * ����BeanFlow�̑O�u���̃f�t�H���g�l�B<p>
     */
    public static final String DEFAULT_VALIDATE_FLOW_PREFIX = "validate";
    
    /**
     * POST���\�b�h�pBeanFlow�̌�u���̃f�t�H���g�l�B<p>
     */
    public static final String DEFAULT_POST_METHOD_FLOW_POSTFIX = "$POST";
    
    /**
     * GET���\�b�h�pBeanFlow�̌�u���̃f�t�H���g�l�B<p>
     */
    public static final String DEFAULT_GET_METHOD_FLOW_POSTFIX = "$GET";
    
    /**
     * HEAD���\�b�h�pBeanFlow�̌�u���̃f�t�H���g�l�B<p>
     */
    public static final String DEFAULT_HEAD_METHOD_FLOW_POSTFIX = "$HEAD";
    
    /**
     * PUT���\�b�h�pBeanFlow�̌�u���̃f�t�H���g�l�B<p>
     */
    public static final String DEFAULT_PUT_METHOD_FLOW_POSTFIX = "$PUT";
    
    /**
     * DELETE���\�b�h�pBeanFlow�̌�u���̃f�t�H���g�l�B<p>
     */
    public static final String DEFAULT_DELETE_METHOD_FLOW_POSTFIX = "$DELETE";
    
    public static final String JOURNAL_KEY_REST_PROCESS = "RestProcess";
    public static final String JOURNAL_KEY_REQUEST_URI = "RequestURI";
    public static final String JOURNAL_KEY_METHOD = "Method";
    public static final String JOURNAL_KEY_RESULT_STATUS = "ResultStatus";
    public static final String JOURNAL_KEY_EXCEPTION = "Exception";
    public static final String JOURNAL_KEY_ACCEPT_HEADER = "Accept";
    public static final String JOURNAL_KEY_ACCEPT_CHARSET_HEADER = "AcceptCharset";
    public static final String JOURNAL_KEY_CONTENT_TYPE_HEADER = "ContentType";
    public static final String JOURNAL_KEY_RESOURCE_PATH = "ResourcePath";
    public static final String JOURNAL_KEY_PATH_PARAMETERS = "PathParamsters";
    public static final String JOURNAL_KEY_REQUEST_PARAMETERS = "RequestParamsters";
    public static final String JOURNAL_KEY_REQUEST_BODY = "RequestBody";
    public static final String JOURNAL_KEY_REQUEST_OBJECT = "RequestObject";
    public static final String JOURNAL_KEY_VALIDATE_FLOW = "ValidateFlow";
    public static final String JOURNAL_KEY_FLOW = "Flow";
    public static final String JOURNAL_KEY_RESPONSE_BODY = "ResponseBody";
    public static final String JOURNAL_KEY_RESPONSE_OBJECT = "ResponseObject";
    
    /**
     * REST�T�[�o��`�t�@�C���̃p�X��ݒ肷��B<p>
     * �p�X�́A��΃p�X�A�T�[�r�X��`�t�@�C������̑��΃p�X�A�N���X�p�X���w��ł���B<br>
     *
     * @param path REST�T�[�o��`�t�@�C���̃p�X
     */
    public void setServerDefinitionPath(String path);
    
    /**
     * REST�T�[�o��`�t�@�C���̃p�X���擾����B<p>
     *
     * @return REST�T�[�o��`�t�@�C���̃p�X
     */
    public String getServerDefinitionPath();
    
    /**
     * REST�T�[�o��`�t�@�C�����p�[�X����javax.xml.parsers.DocumentBuilderFactory�̃N���X����ݒ肷��B<p>
     * �w�肵�Ȃ��ꍇ�́ADocumentBuilderFactory.newInstance()��DocumentBuilderFactory�𐶐�����B<br>
     *
     * @param name DocumentBuilderFactory�̃N���X��
     */
    public void setDocumentBuilderFactoryClassName(String name);
    
    /**
     * REST�T�[�o��`�t�@�C�����p�[�X����javax.xml.parsers.DocumentBuilderFactory�̃N���X�����擾����B<p>
     *
     * @return DocumentBuilderFactory�̃N���X��
     */
    public String getDocumentBuilderFactoryClassName();
    
    /**
     * REST�T�[�o��`�t�@�C�������؂��邩�ǂ�����ݒ肷��B<p>
     * �f�t�H���g�́Afalse�Ō��؂��Ȃ��B<br>
     * 
     * @param validate ���؂���ꍇ�Atrue
     */
    public void setValidate(boolean validate);
    
    /**
     * REST�T�[�o��`�t�@�C�������؂��邩�ǂ����𔻒肷��B<p>
     * 
     * @return true�̏ꍇ�A���؂���
     */
    public boolean isValidate();
    
    /**
     * ���N�G�X�g�I�u�W�F�N�g���؃t���[�̃t���[���̑O�u����ݒ肷��B<p>
     * �f�t�H���g�́A{@link #DEFAULT_VALIDATE_FLOW_PREFIX}�B<br>
     *
     * @param prefix �t���[���̑O�u��
     */
    public void setValidateFlowPrefix(String prefix);
    
    /**
     * ���N�G�X�g�I�u�W�F�N�g���؃t���[�̃t���[���̑O�u�����擾����B<p>
     *
     * @return �t���[���̑O�u��
     */
    public String getValidateFlowPrefix();
    
    /**
     * POST���\�b�h�����t���[�̃t���[���̌�u����ݒ肷��B<p>
     * �f�t�H���g�́A{@link #DEFAULT_POST_METHOD_FLOW_POSTFIX}�B<br>
     *
     * @param postfix �t���[���̌�u��
     */
    public void setPostMethodFlowPostfix(String postfix);
    
    /**
     * POST���\�b�h�����t���[�̃t���[���̌�u�����擾����B<p>
     *
     * @return �t���[���̌�u��
     */
    public String getPostMethodFlowPostfix();
    
    /**
     * GET���\�b�h�����t���[�̃t���[���̌�u����ݒ肷��B<p>
     * �f�t�H���g�́A{@link #DEFAULT_GET_METHOD_FLOW_POSTFIX}�B<br>
     *
     * @param postfix �t���[���̌�u��
     */
    public void setGetMethodFlowPostfix(String postfix);
    
    /**
     * GET���\�b�h�����t���[�̃t���[���̌�u�����擾����B<p>
     *
     * @return �t���[���̌�u��
     */
    public String getGetMethodFlowPostfix();
    
    /**
     * HEAD���\�b�h�����t���[�̃t���[���̌�u����ݒ肷��B<p>
     * �f�t�H���g�́A{@link #DEFAULT_HEAD_METHOD_FLOW_POSTFIX}�B<br>
     *
     * @param postfix �t���[���̌�u��
     */
    public void setHeadMethodFlowPostfix(String postfix);
    
    /**
     * HEAD���\�b�h�����t���[�̃t���[���̌�u�����擾����B<p>
     *
     * @return �t���[���̌�u��
     */
    public String getHeadMethodFlowPostfix();
    
    /**
     * PUT���\�b�h�����t���[�̃t���[���̌�u����ݒ肷��B<p>
     * �f�t�H���g�́A{@link #DEFAULT_PUT_METHOD_FLOW_POSTFIX}�B<br>
     *
     * @param postfix �t���[���̌�u��
     */
    public void setPutMethodFlowPostfix(String postfix);
    
    /**
     * PUT���\�b�h�����t���[�̃t���[���̌�u�����擾����B<p>
     *
     * @return �t���[���̌�u��
     */
    public String getPutMethodFlowPostfix();
    
    /**
     * DELETE���\�b�h�����t���[�̃t���[���̌�u����ݒ肷��B<p>
     * �f�t�H���g�́A{@link #DEFAULT_DELETE_METHOD_FLOW_POSTFIX}�B<br>
     *
     * @param postfix �t���[���̌�u��
     */
    public void setDeleteMethodFlowPostfix(String postfix);
    
    /**
     * DELETE���\�b�h�����t���[�̃t���[���̌�u�����擾����B<p>
     *
     * @return �t���[���̌�u��
     */
    public String getDeleteMethodFlowPostfix();
    
    /**
     * {@link jp.ossc.nimbus.service.beancontrol.interfaces.BeanFlowInvokerFactory BeanFlowInvokerFactory}�T�[�r�X�̃T�[�r�X����ݒ肷��B<p>
     *
     * @param name BeanFlowFactory�T�[�r�X�̃T�[�r�X��
     */
    public void setBeanFlowInvokerFactoryServiceName(ServiceName name);
    
    /**
     * {@link jp.ossc.nimbus.service.beancontrol.interfaces.BeanFlowInvokerFactory BeanFlowInvokerFactory}�T�[�r�X�̃T�[�r�X�����擾����B<p>
     *
     * @return BeanFlowFactory�T�[�r�X�̃T�[�r�X��
     */
    public ServiceName getBeanFlowInvokerFactoryServiceName();
    
    /**
     * {@link jp.ossc.nimbus.service.journal.Journal Journal}�T�[�r�X�̃T�[�r�X����ݒ肷��B<p>
     *
     * @param name Journal�T�[�r�X�̃T�[�r�X��
     */
    public void setJournalServiceName(ServiceName name);
    
    /**
     * {@link jp.ossc.nimbus.service.journal.Journal Journal}�T�[�r�X�̃T�[�r�X�����擾����B<p>
     *
     * @return Journal�T�[�r�X�̃T�[�r�X��
     */
    public ServiceName getJournalServiceName();
    
    /**
     * {@link jp.ossc.nimbus.service.journal.editorfinder.EditorFinder EditorFinder}�T�[�r�X�̃T�[�r�X����ݒ肷��B<p>
     *
     * @param name EditorFinder�T�[�r�X�̃T�[�r�X��
     */
    public void setEditorFinderServiceName(ServiceName name);
    
    /**
     * {@link jp.ossc.nimbus.service.journal.editorfinder.EditorFinder EditorFinder}�T�[�r�X�̃T�[�r�X�����擾����B<p>
     *
     * @return EditorFinder�T�[�r�X�̃T�[�r�X��
     */
    public ServiceName getEditorFinderServiceName();
    
    /**
     * {@link jp.ossc.nimbus.service.sequence.Sequence Sequence}�T�[�r�X�̃T�[�r�X����ݒ肷��B<p>
     *
     * @param name Sequence�T�[�r�X�̃T�[�r�X��
     */
    public void setSequenceServiceName(ServiceName name);
    
    /**
     * {@link jp.ossc.nimbus.service.sequence.Sequence Sequence}�T�[�r�X�̃T�[�r�X�����擾����B<p>
     *
     * @return Sequence�T�[�r�X�̃T�[�r�X��
     */
    public ServiceName getSequenceServiceName();
    
    /**
     * {@link jp.ossc.nimbus.service.context.Context Context}�T�[�r�X�̃T�[�r�X����ݒ肷��B<p>
     *
     * @param name Context�T�[�r�X�̃T�[�r�X��
     */
    public void setContextServiceName(ServiceName name);
    
    /**
     * {@link jp.ossc.nimbus.service.context.Context Context}�T�[�r�X�̃T�[�r�X�����擾����B<p>
     *
     * @return Context�T�[�r�X�̃T�[�r�X��
     */
    public ServiceName getContextServiceName();
    
    /**
     * Context�T�[�r�X�ɐݒ肳�ꂽ���N�G�X�gID�̃L�[����ݒ肷��B<p>
     * �f�t�H���g�́A{@link jp.ossc.nimbus.service.aop.interceptor.ThreadContextKey#REQUEST_ID}�B<br>
     *
     * @param key ���N�G�X�gID�̃L�[��
     * @see #getRequestIdKey()
     */
    public void setRequestIdKey(String key);
    
    /**
     * Context�T�[�r�X�ɐݒ肳�ꂽ���N�G�X�gID�̃L�[�����擾����B<p>
     *
     * @return ���N�G�X�gID�̃L�[��
     * @see #setRequestIdKey(String)
     */
    public String getRequestIdKey();
    
    /**
     * ���f�B�A�^�C�v���̃��N�G�X�g�I�u�W�F�N�g�̕ϊ����s��{@link jp.ossc.nimbus.util.converter.BindingStreamConverter BindingStreamConverter}�T�[�r�X�̃T�[�r�X���̃}�b�s���O��ݒ肷��B<p>
     *
     * @param mapping ���f�B�A�^�C�v��BindingStreamConverter�T�[�r�X�̃T�[�r�X���̃}�b�s���O�B���f�B�A�^�C�v=BindingStreamConverter�T�[�r�X�̃T�[�r�X��
     */
    public void setRequestConverterServiceNames(Map mapping);
    
    /**
     * ���f�B�A�^�C�v���̃��N�G�X�g�I�u�W�F�N�g�̕ϊ����s��{@link jp.ossc.nimbus.util.converter.BindingStreamConverter BindingStreamConverter}�T�[�r�X�̃T�[�r�X���̃}�b�s���O���擾����B<p>
     *
     * @return ���f�B�A�^�C�v��BindingStreamConverter�T�[�r�X�̃T�[�r�X���̃}�b�s���O
     */
    public Map getRequestConverterServiceNames();
    
    /**
     * �w�肵�����f�B�A�^�C�v�̃��N�G�X�g�I�u�W�F�N�g�̕ϊ����s��{@link jp.ossc.nimbus.util.converter.BindingStreamConverter BindingStreamConverter}�T�[�r�X�̃T�[�r�X����ݒ肷��B<p>
     *
     * @param mediaType ���f�B�A�^�C�v
     * @param name BindingStreamConverter�T�[�r�X�̃T�[�r�X��
     */
    public void setRequestConverterServiceName(String mediaType, ServiceName name);
    
    /**
     * �w�肵�����f�B�A�^�C�v�̃��N�G�X�g�I�u�W�F�N�g�̕ϊ����s��{@link jp.ossc.nimbus.util.converter.BindingStreamConverter BindingStreamConverter}�T�[�r�X�̃T�[�r�X�����擾����B<p>
     *
     * @param mediaType ���f�B�A�^�C�v
     * @return BindingStreamConverter�T�[�r�X�̃T�[�r�X��
     */
    public ServiceName getRequestConverterServiceName(String mediaType);
    
    /**
     * ���f�B�A�^�C�v���̃��X�|���X�I�u�W�F�N�g�̕ϊ����s��{@link jp.ossc.nimbus.util.converter.StreamConverter StreamConverter}�T�[�r�X�̃T�[�r�X���̃}�b�s���O��ݒ肷��B<p>
     *
     * @param mapping ���f�B�A�^�C�v��StreamConverter�T�[�r�X�̃T�[�r�X���̃}�b�s���O�B���f�B�A�^�C�v=StreamConverter�T�[�r�X�̃T�[�r�X��
     */
    public void setResponseConverterServiceNames(Map mapping);
    
    /**
     * ���f�B�A�^�C�v���̃��X�|���X�I�u�W�F�N�g�̕ϊ����s��{@link jp.ossc.nimbus.util.converter.StreamConverter StreamConverter}�T�[�r�X�̃T�[�r�X���̃}�b�s���O���擾����B<p>
     *
     * @return ���f�B�A�^�C�v��StreamConverter�T�[�r�X�̃T�[�r�X���̃}�b�s���O
     */
    public Map getResponseConverterServiceNames();
    
    /**
     * �w�肵�����f�B�A�^�C�v�̃��X�|���X�I�u�W�F�N�g�̕ϊ����s��{@link jp.ossc.nimbus.util.converter.StreamConverter StreamConverter}�T�[�r�X�̃T�[�r�X����ݒ肷��B<p>
     *
     * @param mediaType ���f�B�A�^�C�v
     * @param name StreamConverter�T�[�r�X�̃T�[�r�X��
     */
    public void setResponseConverterServiceName(String mediaType, ServiceName name);
    
    /**
     * �w�肵�����f�B�A�^�C�v�̃��X�|���X�I�u�W�F�N�g�̕ϊ����s��{@link jp.ossc.nimbus.util.converter.StreamConverter StreamConverter}�T�[�r�X�̃T�[�r�X�����擾����B<p>
     *
     * @param mediaType ���f�B�A�^�C�v
     * @return StreamConverter�T�[�r�X�̃T�[�r�X��
     */
    public ServiceName getResponseConverterServiceName(String mediaType);
    
    /**
     * REST�T�[�o��`�t�@�C�����ēǂݍ��݂���B<p>
     *
     * @exception Exception �ēǂݍ��݂Ɏ��s�����ꍇ
     */
    public void reload() throws Exception;
}
