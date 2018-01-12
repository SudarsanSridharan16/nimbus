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

import jp.ossc.nimbus.core.*;

/**
 * {@link StreamExchangeInterceptorService}��MBean�C���^�t�F�[�X�B<p>
 * 
 * @author M.Takata
 * @see StreamExchangeInterceptorService
 */
public interface StreamExchangeInterceptorServiceMBean
 extends ServletFilterInterceptorServiceMBean{
    
    /**
     * �f�t�H���g�̗v���I�u�W�F�N�g�̃��N�G�X�g�������B<p>
     */
    public static final String DEFAULT_REQUEST_OBJECT_ATTRIBUTE_NAME = StreamExchangeInterceptorService.class.getName().replaceAll("\\.", "_") + "_REQUEST";
    
    /**
     * �f�t�H���g�̉����I�u�W�F�N�g�̃��N�G�X�g�������B<p>
     */
    public static final String DEFAULT_RESPONSE_OBJECT_ATTRIBUTE_NAME = StreamExchangeInterceptorService.class.getName().replaceAll("\\.", "_") + "_RESPONSE";
    
    /**
     * �f�t�H���g�̗v���I�u�W�F�N�g�̃R���e�L�X�g�L�[���B<p>
     */
    public static final String DEFAULT_REQUEST_OBJECT_CONTEXT_KEY = StreamExchangeInterceptorService.class.getName().replaceAll("\\.", "_") + "_REQUEST";
    
    /**
     * �f�t�H���g�̉����I�u�W�F�N�g�̃R���e�L�X�g�L�[���B<p>
     */
    public static final String DEFAULT_RESPONSE_OBJECT_CONTEXT_KEY = StreamExchangeInterceptorService.class.getName().replaceAll("\\.", "_") + "_RESPONSE";
    
    /**
     * �f�t�H���g�̃W���[�i���̃��[�g�X�e�b�v���B<p>
     */
    public static final String DEFAULT_EXCHANGE_JOURNAL_KEY = "Exchange";
    
    /**
     * �f�t�H���g�̃W���[�i���̗v���X�e�b�v���B<p>
     */
    public static final String DEFAULT_EXCHANGE_REQ_JOURNAL_KEY = "Request";
    
    /**
     * �f�t�H���g�̃W���[�i���̉����X�e�b�v���B<p>
     */
    public static final String DEFAULT_EXCHANGE_RES_JOURNAL_KEY = "Response";
    
    /**
     * �f�t�H���g�̃W���[�i���̗v���o�C�g�z��v�f���B<p>
     */
    public static final String DEFAULT_REQUEST_BYTES_JOURNAL_KEY = "RequestBytes";
    
    /**
     * �f�t�H���g�̃W���[�i���̗v���I�u�W�F�N�g�v�f���B<p>
     */
    public static final String DEFAULT_REQUEST_OBJECT_JOURNAL_KEY = "RequestObject";
    
    /**
     * �f�t�H���g�̃W���[�i���̉����o�C�g�z��v�f���B<p>
     */
    public static final String DEFAULT_RESPONSE_BYTES_JOURNAL_KEY = "ResponseBytes";
    
    /**
     * �f�t�H���g�̃W���[�i���̉����I�u�W�F�N�g�v�f���B<p>
     */
    public static final String DEFAULT_RESPONSE_OBJECT_JOURNAL_KEY = "ResponseObject";
    
    /**
     * �f�t�H���g�̃W���[�i���̗�O�v�f���B<p>
     */
    public static final String DEFAULT_EXCEPTION_JOURNAL_KEY = "Exception";
    
    /**
     * �f�t�H���g�̗v���I�u�W�F�N�gBeanFlow���̑O�u���B<p>
     */
    public static final String DEFAULT_REQUEST_OBJECT_FLOW_NAME_PREFIX = "request";
    
    /**
     * �v���X�g���[����v���I�u�W�F�N�g�ɕϊ�����{@link jp.ossc.nimbus.util.converter.StreamConverter StreamConverter}�T�[�r�X����ݒ肷��B<p>
     *
     * @param name StreamConverter�T�[�r�X��
     */
    public void setRequestStreamConverterServiceName(ServiceName name);
    
    /**
     * �v���X�g���[����v���I�u�W�F�N�g�ɕϊ�����{@link jp.ossc.nimbus.util.converter.StreamConverter StreamConverter}�T�[�r�X�����擾����B<p>
     *
     * @return StreamConverter�T�[�r�X��
     */
    public ServiceName getRequestStreamConverterServiceName();
    
    /**
     * �����I�u�W�F�N�g�������X�g���[���ɕϊ�����{@link jp.ossc.nimbus.util.converter.StreamConverter StreamConverter}�T�[�r�X����ݒ肷��B<p>
     *
     * @param name StreamConverter�T�[�r�X��
     */
    public void setResponseStreamConverterServiceName(ServiceName name);
    
    /**
     * �����I�u�W�F�N�g�������X�g���[���ɕϊ�����{@link jp.ossc.nimbus.util.converter.StreamConverter StreamConverter}�T�[�r�X�����擾����B<p>
     *
     * @return StreamConverter�T�[�r�X��
     */
    public ServiceName getResponseStreamConverterServiceName();
    
    /**
     * �v���I�u�W�F�N�g�y�щ����I�u�W�F�N�g���悹��{@link jp.ossc.nimbus.service.context.Context Context}�T�[�r�X����ݒ肷��B<p>
     * ���̑�����ݒ肵�Ȃ��ꍇ�́A���N�G�X�g�����݂̂ɏ悹��B<br>
     *
     * @param name Context�T�[�r�X��
     */
    public void setThreadContextServiceName(ServiceName name);
    
    /**
     * �v���I�u�W�F�N�g�y�щ����I�u�W�F�N�g���悹��{@link jp.ossc.nimbus.service.context.Context Context}�T�[�r�X�����擾����B<p>
     *
     * @return Context�T�[�r�X��
     */
    public ServiceName getThreadContextServiceName();
    
    /**
     * �W���[�i�����o�͂���{@link jp.ossc.nimbus.service.journal.Journal Journal}�T�[�r�X����ݒ肷��B<p>
     *
     * @param name Journal�T�[�r�X��
     */
    public void setJournalServiceName(ServiceName name);
    
    /**
     * �W���[�i�����o�͂���{@link jp.ossc.nimbus.service.journal.Journal Journal}�T�[�r�X�����擾����B<p>
     *
     * @return Journal�T�[�r�X��
     */
    public ServiceName getJournalServiceName();
    
    /**
     * �W���[�i���̃��[�g�X�e�b�v��ҏW����{@link jp.ossc.nimbus.service.journal.JournalEditor JournalEditor}����������{@link jp.ossc.nimbus.service.journal.editorfinder.EditorFinder EditorFinder}�T�[�r�X����ݒ肷��B<p>
     *
     * @param name EditorFinder�T�[�r�X��
     */
    public void setExchangeEditorFinderServiceName(ServiceName name);
    
    /**
     * �W���[�i���̃��[�g�X�e�b�v��ҏW����{@link jp.ossc.nimbus.service.journal.JournalEditor JournalEditor}����������{@link jp.ossc.nimbus.service.journal.editorfinder.EditorFinder EditorFinder}�T�[�r�X�����擾����B<p>
     *
     * @return EditorFinder�T�[�r�X��
     */
    public ServiceName getExchangeEditorFinderServiceName();
    
    /**
     * �W���[�i���̗v���X�e�b�v��ҏW����{@link jp.ossc.nimbus.service.journal.JournalEditor JournalEditor}����������{@link jp.ossc.nimbus.service.journal.editorfinder.EditorFinder EditorFinder}�T�[�r�X����ݒ肷��B<p>
     *
     * @param name EditorFinder�T�[�r�X��
     */
    public void setExchangeRequestEditorFinderServiceName(ServiceName name);
    
    /**
     * �W���[�i���̗v���X�e�b�v��ҏW����{@link jp.ossc.nimbus.service.journal.JournalEditor JournalEditor}����������{@link jp.ossc.nimbus.service.journal.editorfinder.EditorFinder EditorFinder}�T�[�r�X�����擾����B<p>
     *
     * @return EditorFinder�T�[�r�X��
     */
    public ServiceName getExchangeRequestEditorFinderServiceName();
    
    /**
     * �W���[�i���̉����X�e�b�v��ҏW����{@link jp.ossc.nimbus.service.journal.JournalEditor JournalEditor}����������{@link jp.ossc.nimbus.service.journal.editorfinder.EditorFinder EditorFinder}�T�[�r�X����ݒ肷��B<p>
     *
     * @param name EditorFinder�T�[�r�X��
     */
    public void setExchangeResponseEditorFinderServiceName(ServiceName name);
    
    /**
     * �W���[�i���̉����X�e�b�v��ҏW����{@link jp.ossc.nimbus.service.journal.JournalEditor JournalEditor}����������{@link jp.ossc.nimbus.service.journal.editorfinder.EditorFinder EditorFinder}�T�[�r�X�����擾����B<p>
     *
     * @return EditorFinder�T�[�r�X��
     */
    public ServiceName getExchangeResponseEditorFinderServiceName();
    
    /**
     * �W���[�i���̗v���o�C�g�z���ҏW����{@link jp.ossc.nimbus.service.journal.JournalEditor JournalEditor}����������{@link jp.ossc.nimbus.service.journal.editorfinder.EditorFinder EditorFinder}�T�[�r�X����ݒ肷��B<p>
     *
     * @param name EditorFinder�T�[�r�X��
     */
    public void setRequestBytesEditorFinderServiceName(ServiceName name);
    
    /**
     * �W���[�i���̗v���o�C�g�z���ҏW����{@link jp.ossc.nimbus.service.journal.JournalEditor JournalEditor}����������{@link jp.ossc.nimbus.service.journal.editorfinder.EditorFinder EditorFinder}�T�[�r�X�����擾����B<p>
     *
     * @return EditorFinder�T�[�r�X��
     */
    public ServiceName getRequestBytesEditorFinderServiceName();
    
    /**
     * �W���[�i���̗v���I�u�W�F�N�g��ҏW����{@link jp.ossc.nimbus.service.journal.JournalEditor JournalEditor}����������{@link jp.ossc.nimbus.service.journal.editorfinder.EditorFinder EditorFinder}�T�[�r�X����ݒ肷��B<p>
     *
     * @param name EditorFinder�T�[�r�X��
     */
    public void setRequestObjectEditorFinderServiceName(ServiceName name);
    
    /**
     * �W���[�i���̗v���I�u�W�F�N�g��ҏW����{@link jp.ossc.nimbus.service.journal.JournalEditor JournalEditor}����������{@link jp.ossc.nimbus.service.journal.editorfinder.EditorFinder EditorFinder}�T�[�r�X�����擾����B<p>
     *
     * @return EditorFinder�T�[�r�X��
     */
    public ServiceName getRequestObjectEditorFinderServiceName();
    
    /**
     * �W���[�i���̉����o�C�g�z���ҏW����{@link jp.ossc.nimbus.service.journal.JournalEditor JournalEditor}����������{@link jp.ossc.nimbus.service.journal.editorfinder.EditorFinder EditorFinder}�T�[�r�X����ݒ肷��B<p>
     *
     * @param name EditorFinder�T�[�r�X��
     */
    public void setResponseBytesEditorFinderServiceName(ServiceName name);
    
    /**
     * �W���[�i���̉����o�C�g�z���ҏW����{@link jp.ossc.nimbus.service.journal.JournalEditor JournalEditor}����������{@link jp.ossc.nimbus.service.journal.editorfinder.EditorFinder EditorFinder}�T�[�r�X�����擾����B<p>
     *
     * @return EditorFinder�T�[�r�X��
     */
    public ServiceName getResponseBytesEditorFinderServiceName();
    
    /**
     * �W���[�i���̉����I�u�W�F�N�g��ҏW����{@link jp.ossc.nimbus.service.journal.JournalEditor JournalEditor}����������{@link jp.ossc.nimbus.service.journal.editorfinder.EditorFinder EditorFinder}�T�[�r�X����ݒ肷��B<p>
     *
     * @param name EditorFinder�T�[�r�X��
     */
    public void setResponseObjectEditorFinderServiceName(ServiceName name);
    
    /**
     * �W���[�i���̉����I�u�W�F�N�g��ҏW����{@link jp.ossc.nimbus.service.journal.JournalEditor JournalEditor}����������{@link jp.ossc.nimbus.service.journal.editorfinder.EditorFinder EditorFinder}�T�[�r�X�����擾����B<p>
     *
     * @return EditorFinder�T�[�r�X��
     */
    public ServiceName getResponseObjectEditorFinderServiceName();
    
    /**
     * �W���[�i���̗�O��ҏW����{@link jp.ossc.nimbus.service.journal.JournalEditor JournalEditor}����������{@link jp.ossc.nimbus.service.journal.editorfinder.EditorFinder EditorFinder}�T�[�r�X����ݒ肷��B<p>
     *
     * @param name EditorFinder�T�[�r�X��
     */
    public void setExceptionEditorFinderServiceName(ServiceName name);
    
    /**
     * �W���[�i���̗�O��ҏW����{@link jp.ossc.nimbus.service.journal.JournalEditor JournalEditor}����������{@link jp.ossc.nimbus.service.journal.editorfinder.EditorFinder EditorFinder}�T�[�r�X�����擾����B<p>
     *
     * @return EditorFinder�T�[�r�X��
     */
    public ServiceName getExceptionEditorFinderServiceName();
    
    /**
     * �W���[�i���̃��[�g�X�e�b�v�̃L�[����ݒ肷��B<p>
     * �f�t�H���g�l�́A{@link #DEFAULT_EXCHANGE_JOURNAL_KEY}�B<br>
     *
     * @param key �L�[��
     * @see #DEFAULT_EXCHANGE_JOURNAL_KEY
     */
    public void setExchangeJournalKey(String key);
    
    /**
     * �W���[�i���̃��[�g�X�e�b�v�̃L�[�����擾����B<p>
     *
     * @return �L�[��
     */
    public String getExchangeJournalKey();
    
    /**
     * �W���[�i���̗v���X�e�b�v�̃L�[����ݒ肷��B<p>
     * �f�t�H���g�l�́A{@link #DEFAULT_EXCHANGE_REQ_JOURNAL_KEY}�B<br>
     *
     * @param key �L�[��
     * @see #DEFAULT_EXCHANGE_REQ_JOURNAL_KEY
     */
    public void setExchangeRequestJournalKey(String key);
    
    /**
     * �W���[�i���̗v���X�e�b�v�̃L�[�����擾����B<p>
     *
     * @return �L�[��
     */
    public String getExchangeRequestJournalKey();
    
    /**
     * �W���[�i���̉����X�e�b�v�̃L�[����ݒ肷��B<p>
     * �f�t�H���g�l�́A{@link #DEFAULT_EXCHANGE_RES_JOURNAL_KEY}�B<br>
     *
     * @param key �L�[��
     * @see #DEFAULT_EXCHANGE_RES_JOURNAL_KEY
     */
    public void setExchangeResponseJournalKey(String key);
    
    /**
     * �W���[�i���̉����X�e�b�v�̃L�[�����擾����B<p>
     *
     * @return �L�[��
     */
    public String getExchangeResponseJournalKey();
    
    /**
     * �W���[�i���̗v���o�C�g�z��̃L�[����ݒ肷��B<p>
     * �f�t�H���g�l�́A{@link #DEFAULT_REQUEST_BYTES_JOURNAL_KEY}�B<br>
     *
     * @param key �L�[��
     * @see #DEFAULT_REQUEST_BYTES_JOURNAL_KEY
     */
    public void setRequestBytesJournalKey(String key);
    
    /**
     * �W���[�i���̗v���o�C�g�z��̃L�[�����擾����B<p>
     *
     * @return �L�[��
     */
    public String getRequestBytesJournalKey();
    
    /**
     * �W���[�i���̗v���I�u�W�F�N�g�̃L�[����ݒ肷��B<p>
     * �f�t�H���g�l�́A{@link #DEFAULT_REQUEST_OBJECT_JOURNAL_KEY}�B<br>
     *
     * @param key �L�[��
     * @see #DEFAULT_REQUEST_OBJECT_JOURNAL_KEY
     */
    public void setRequestObjectJournalKey(String key);
    
    /**
     * �W���[�i���̗v���I�u�W�F�N�g�̃L�[�����擾����B<p>
     *
     * @return �L�[��
     */
    public String getRequestObjectJournalKey();
    
    /**
     * �W���[�i���̉����o�C�g�z��̃L�[����ݒ肷��B<p>
     * �f�t�H���g�l�́A{@link #DEFAULT_RESPONSE_BYTES_JOURNAL_KEY}�B<br>
     *
     * @param key �L�[��
     * @see #DEFAULT_RESPONSE_BYTES_JOURNAL_KEY
     */
    public void setResponseBytesJournalKey(String key);
    
    /**
     * �W���[�i���̉����o�C�g�z��̃L�[�����擾����B<p>
     *
     * @return �L�[��
     */
    public String getResponseBytesJournalKey();
    
    /**
     * �W���[�i���̉����I�u�W�F�N�g�̃L�[����ݒ肷��B<p>
     * �f�t�H���g�l�́A{@link #DEFAULT_RESPONSE_OBJECT_JOURNAL_KEY}�B<br>
     *
     * @param key �L�[��
     * @see #DEFAULT_RESPONSE_OBJECT_JOURNAL_KEY
     */
    public void setResponseObjectJournalKey(String key);
    
    /**
     * �W���[�i���̉����I�u�W�F�N�g�̃L�[�����擾����B<p>
     *
     * @return �L�[��
     */
    public String getResponseObjectJournalKey();
    
    /**
     * �W���[�i���̗�O�̃L�[����ݒ肷��B<p>
     * �f�t�H���g�l�́A{@link #DEFAULT_EXCEPTION_JOURNAL_KEY}�B<br>
     *
     * @param key �L�[��
     * @see #DEFAULT_EXCEPTION_JOURNAL_KEY
     */
    public void setExceptionJournalKey(String key);
    
    /**
     * �W���[�i���̗�O�̃L�[�����擾����B<p>
     *
     * @return �L�[��
     */
    public String getExceptionJournalKey();
    
    /**
     * HTTP���X�|���X�̃R���e���g�^�C�v��ݒ肷��B<p>
     *
     * @param type �R���e���g�^�C�v
     */
    public void setResponseContentType(String type);
    
    /**
     * HTTP���X�|���X�̃R���e���g�^�C�v���擾����B<p>
     *
     * @return �R���e���g�^�C�v
     */
    public String getResponseContentType();
    
    /**
     * �v���I�u�W�F�N�g�����N�G�X�g�����ɐݒ肷�鎞�Ɏg�p���鑮������ݒ肷��B<p>
     * �f�t�H���g�l�́A{@link #DEFAULT_REQUEST_OBJECT_ATTRIBUTE_NAME}�B<br>
     *
     * @param name ������
     * @see #DEFAULT_REQUEST_OBJECT_ATTRIBUTE_NAME
     */
    public void setRequestObjectAttributeName(String name);
    
    /**
     * �v���I�u�W�F�N�g�����N�G�X�g�����ɐݒ肷�鎞�Ɏg�p���鑮�������擾����B<p>
     *
     * @return ������
     */
    public String getRequestObjectAttributeName();
    
    /**
     * �����I�u�W�F�N�g�����N�G�X�g�����ɐݒ肷�鎞�Ɏg�p���鑮������ݒ肷��B<p>
     * �f�t�H���g�l�́A{@link #DEFAULT_RESPONSE_OBJECT_ATTRIBUTE_NAME}�B<br>
     *
     * @param name ������
     * @see #DEFAULT_RESPONSE_OBJECT_ATTRIBUTE_NAME
     */
    public void setResponseObjectAttributeName(String name);
    
    /**
     * �����I�u�W�F�N�g�����N�G�X�g�����ɐݒ肷�鎞�Ɏg�p���鑮�������擾����B<p>
     *
     * @return ������
     */
    public String getResponseObjectAttributeName();
    
    /**
     * �v���I�u�W�F�N�g���R���e�L�X�g�ɐݒ肷�鎞�Ɏg�p����L�[����ݒ肷��B<p>
     * �f�t�H���g�l�́A{@link #DEFAULT_REQUEST_OBJECT_CONTEXT_KEY}�B<br>
     *
     * @param key �L�[��
     * @see #DEFAULT_REQUEST_OBJECT_CONTEXT_KEY
     */
    public void setRequestObjectContextKey(String key);
    
    /**
     * �v���I�u�W�F�N�g���R���e�L�X�g�ɐݒ肷�鎞�Ɏg�p����L�[�����擾����B<p>
     *
     * @return �L�[��
     */
    public String getRequestObjectContextKey();
    
    /**
     * �����I�u�W�F�N�g���R���e�L�X�g�ɐݒ肷�鎞�Ɏg�p����L�[����ݒ肷��B<p>
     * �f�t�H���g�l�́A{@link #DEFAULT_RESPONSE_OBJECT_CONTEXT_KEY}�B<br>
     *
     * @param key �L�[��
     * @see #DEFAULT_RESPONSE_OBJECT_CONTEXT_KEY
     */
    public void setResponseObjectContextKey(String key);
    
    /**
     * �����I�u�W�F�N�g���R���e�L�X�g�ɐݒ肷�鎞�Ɏg�p����L�[�����擾����B<p>
     *
     * @return �L�[��
     */
    public String getResponseObjectContextKey();
    
    /**
     * ���N�G�X�g�X�g���[���̉𓀂��s�����ǂ�����ݒ肷��B<p>
     * �f�t�H���g�́Atrue�B<br>
     *
     * @param isInflate �𓀂��s���ꍇ�́Atrue
     */
    public void setRequestStreamInflate(boolean isInflate);
    
    /**
     * ���N�G�X�g�X�g���[���̉𓀂��s�����ǂ����𔻒肷��B<p>
     *
     * @return true�̏ꍇ�A�𓀂��s��
     */
    public boolean isRequestStreamInflate();
    
    /**
     * �v���I�u�W�F�N�g��BeanFlow�Ŏ擾����ꍇ�Ɏg�p����{@link jp.ossc.nimbus.service.beancontrol.interfaces.BeanFlowInvokerFactory BeanFlowInvokerFactory}�̃T�[�r�X����ݒ肷��B<p>
     *
     * @param name BeanFlowInvokerFactory�̃T�[�r�X��
     */
    public void setBeanFlowInvokerFactoryServiceName(ServiceName name);
    
    /**
     * �v���I�u�W�F�N�g��BeanFlow�Ŏ擾����ꍇ�Ɏg�p����{@link jp.ossc.nimbus.service.beancontrol.interfaces.BeanFlowInvokerFactory BeanFlowInvokerFactory}�̃T�[�r�X�����擾����B<p>
     *
     * @return BeanFlowInvokerFactory�̃T�[�r�X��
     */
    public ServiceName getBeanFlowInvokerFactoryServiceName();
    
    /**
     * �v���I�u�W�F�N�g��BeanFlow�Ŏ擾����ꍇ�ɁA�Ăяo���t���[���Ƃ��āA���N�G�X�g���ꂽ�T�[�u���b�g�p�X�̑O�ɕt������v���t�B�N�X��ݒ肷��B<p>
     *
     * @param prefix �v���t�B�N�X
     * @see #DEFAULT_REQUEST_OBJECT_FLOW_NAME_PREFIX
     */
    public void setRequestObjectFlowNamePrefix(String prefix);
    
    /**
     * �v���I�u�W�F�N�g��BeanFlow�Ŏ擾����ꍇ�ɁA�Ăяo���t���[���Ƃ��āA���N�G�X�g���ꂽ�T�[�u���b�g�p�X�̑O�ɕt������v���t�B�N�X���擾����B<p>
     *
     * @return �v���t�B�N�X
     */
    public String getRequestObjectFlowNamePrefix();
    
    /**
     * �v���I�u�W�F�N�g��BeanFlow�Ŏ擾����ꍇ�Ƀt���[������肷��{@link jp.ossc.nimbus.servlet.BeanFlowSelector BeanFlowSelector}�̃T�[�r�X����ݒ肷��B<p>
     * �w�肵�Ȃ��ꍇ�́A{@link jp.ossc.nimbus.servlet.DefaultBeanFlowSelectorService DefaultBeanFlowSelectorService}���K�p�����B
     *
     * @param name BeanFlowSelector�̃T�[�r�X��
     */
    public void setBeanFlowSelectorServiceName(ServiceName name);
    
    /**
     * �v���I�u�W�F�N�g��BeanFlow�Ŏ擾����ꍇ�Ƀt���[������肷��{@link jp.ossc.nimbus.servlet.BeanFlowSelector BeanFlowSelector}�̃T�[�r�X�����擾����B<p>
     *
     * @return BeanFlowSelector�̃T�[�r�X��
     */
    public ServiceName getBeanFlowSelectorServiceName();
}