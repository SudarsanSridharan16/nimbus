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
package jp.ossc.nimbus.service.server;

import jp.ossc.nimbus.core.*;

/**
 * {@link BeanFlowInvokerCallQueueHandlerService}��MBean�C���^�t�F�[�X�B<p>
 * 
 * @author M.Takata
 * @see BeanFlowInvokerCallQueueHandlerService
 */
public interface BeanFlowInvokerCallQueueHandlerServiceMBean extends ServiceBaseMBean{
    
    /**
     * �f�t�H���g�̗v���I�u�W�F�N�gBeanFlow���̑O�u���B<p>
     */
    public static final String DEFAULT_REQUEST_OBJECT_FLOW_NAME_PREFIX = "request/";
    
    /**
     * �f�t�H���g�̃A�N�V����BeanFlow���̑O�u���B<p>
     */
    public static final String DEFAULT_ACTION_FLOW_NAME_PREFIX = "action/";
    
    /**
     * �f�t�H���g�̌��ʃX�e�[�^�X:����B<p>
     */
    public static final int DEFAULT_STATUS_NORMAL = 200;
    
    /**
     * �f�t�H���g�̌��ʃX�e�[�^�X:�t���[��������Ȃ��B<p>
     */
    public static final int DEFAULT_STATUS_NOT_FOUND = 404;
    
    /**
     * �f�t�H���g�̌��ʃX�e�[�^�X:�ُ�B<p>
     */
    public static final int DEFAULT_STATUS_ERROR = 500;
    
    public static final String JOURNAL_ACCESS = "Access";
    public static final String JOURNAL_ACCESS_EXCEPTION = "Exception";
    public static final String JOURNAL_REQUEST_ACTION = "Action";
    public static final String JOURNAL_REQUEST_DATE = "Date";
    public static final String JOURNAL_REQUEST_REMOTE_HOST = "RemoteHost";
    public static final String JOURNAL_REQUEST_REMOTE_PORT = "RemotePort";
    public static final String JOURNAL_REQUEST_OBJECT = "RequestObject";
    public static final String JOURNAL_REQUEST_BODY = "RequestBody";
    public static final String JOURNAL_RESPONSE_STATUS = "Status";
    public static final String JOURNAL_RESPONSE_BODY = "ResponseBody";
    public static final String JOURNAL_RESPONSE_OBJECT = "ResponseObject";
    
    /**
     * {@link jp.ossc.nimbus.service.beancontrol.interfaces.BeanFlowInvokerFactory BeanFlowInvokerFactory}�T�[�r�X�̃T�[�r�X����ݒ肷��B<p>
     *
     * @param name BeanFlowInvokerFactory�T�[�r�X�̃T�[�r�X��
     */
    public void setBeanFlowInvokerFactoryServiceName(ServiceName name);
    
    /**
     * {@link jp.ossc.nimbus.service.beancontrol.interfaces.BeanFlowInvokerFactory BeanFlowInvokerFactory}�T�[�r�X�̃T�[�r�X�����擾����B<p>
     *
     * @return BeanFlowInvokerFactory�T�[�r�X�̃T�[�r�X��
     */
    public ServiceName getBeanFlowInvokerFactoryServiceName();
    
    /**
     * {@link Request ���N�G�X�g}�̓��̓X�g���[����v���I�u�W�F�N�g�ɕϊ�����{@link jp.ossc.nimbus.util.converter.StreamConverter StreamConverter}�T�[�r�X�̃T�[�r�X����ݒ肷��B<p>
     *
     * @param name StreamConverter�T�[�r�X�̃T�[�r�X��
     */
    public void setRequestStreamConverterServiceName(ServiceName name);
    
    /**
     * {@link Request ���N�G�X�g}�̓��̓X�g���[����v���I�u�W�F�N�g�ɕϊ�����{@link jp.ossc.nimbus.util.converter.StreamConverter StreamConverter}�T�[�r�X�̃T�[�r�X�����擾����B<p>
     *
     * @return StreamConverter�T�[�r�X�̃T�[�r�X��
     */
    public ServiceName getRequestStreamConverterServiceName();
    
    /**
     * �����I�u�W�F�N�g��{@link Response ���X�|���X}�ւ̓��̓X�g���[���ɕϊ�����{@link jp.ossc.nimbus.util.converter.StreamConverter StreamConverter}�T�[�r�X�̃T�[�r�X����ݒ肷��B<p>
     *
     * @param name StreamConverter�T�[�r�X�̃T�[�r�X��
     */
    public void setResponseStreamConverterServiceName(ServiceName name);
    
    /**
     * �����I�u�W�F�N�g��{@link Response ���X�|���X}�ւ̓��̓X�g���[���ɕϊ�����{@link jp.ossc.nimbus.util.converter.StreamConverter StreamConverter}�T�[�r�X�̃T�[�r�X�����擾����B<p>
     *
     * @return StreamConverter�T�[�r�X�̃T�[�r�X��
     */
    public ServiceName getResponseStreamConverterServiceName();
    
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
     * ���N�G�X�gID��ݒ肷��{@link jp.ossc.nimbus.service.context.Context Context}�T�[�r�X�̃T�[�r�X����ݒ肷��B<p>
     *
     * @param name Context�T�[�r�X�̃T�[�r�X��
     */
    public void setThreadContextServiceName(ServiceName name);
    
    /**
     * ���N�G�X�gID��ݒ肷��{@link jp.ossc.nimbus.service.context.Context Context}�T�[�r�X�̃T�[�r�X�����擾����B<p>
     *
     * @return Context�T�[�r�X�̃T�[�r�X��
     */
    public ServiceName getThreadContextServiceName();
    
    /**
     * �v���I�u�W�F�N�g�����t���[���̑O�u����ݒ肷��B<p>
     * {@link #setRequestStreamConverterServiceName(ServiceName)}�Őݒ肳�ꂽ{@link jp.ossc.nimbus.util.converter.StreamConverter StreamConverter}�T�[�r�X��{@link jp.ossc.nimbus.util.converter.BindingStreamConverter BindingStreamConverter}�������ꍇ�ɁA�ϊ���I�u�W�F�N�g�𐶐����邽�߂ɋƖ��t���[���Ăяo���B���̍ۂ́A�t���[���������Őݒ肵���O�u��+{@link Request#getAction() �A�N�V����}�ƂȂ�B<br>
     * �f�t�H���g�́A{@link #DEFAULT_REQUEST_OBJECT_FLOW_NAME_PREFIX}�B
     *
     * @param prefix �O�u��
     */
    public void setRequestObjectFlowNamePrefix(String prefix);
    
    /**
     * �v���I�u�W�F�N�g�����t���[���̑O�u�����擾����B<p>
     *
     * @return �O�u��
     */
    public String getRequestObjectFlowNamePrefix();
    
    /**
     * �A�N�V�����t���[���̑O�u����ݒ肷��B<p>
     * {@link Request#getAction() �A�N�V����}�Ŏw�肳�ꂽ�Ɩ��t���[���Ăяo���ۂɁA�t���[���������Őݒ肵���O�u��+{@link Request#getAction() �A�N�V����}�ƂȂ�B<br>
     * �f�t�H���g�́A{@link #DEFAULT_ACTION_FLOW_NAME_PREFIX}�B
     *
     * @param prefix �O�u��
     */
    public void setActionFlowNamePrefix(String prefix);
    
    /**
     * �A�N�V�����t���[���̑O�u�����擾����B<p>
     *
     * @return �O�u��
     */
    public String getActionFlowNamePrefix();
    
    /**
     * ���퉞�����̃X�e�[�^�X�l��ݒ肷��B<p>
     * �f�t�H���g�́A{@link #DEFAULT_STATUS_NORMAL}�B
     *
     * @param status �X�e�[�^�X�l
     */
    public void setNormalStatus(int status);
    
    /**
     * ���퉞�����̃X�e�[�^�X�l���擾����B<p>
     *
     * @return �X�e�[�^�X�l
     */
    public int getNormalStatus();
    
    /**
     * �A�N�V�����ɊY������t���[��������Ȃ����̃X�e�[�^�X�l��ݒ肷��B<p>
     * �f�t�H���g�́A{@link #DEFAULT_STATUS_NOT_FOUND}�B
     *
     * @param status �X�e�[�^�X�l
     */
    public void setNotFoundStatus(int status);
    
    /**
     * �A�N�V�����ɊY������t���[��������Ȃ����̃X�e�[�^�X�l���擾����B<p>
     *
     * @return �X�e�[�^�X�l
     */
    public int getNotFoundStatus();
    
    /**
     * ��O�������������̃X�e�[�^�X�l��ݒ肷��B<p>
     * �f�t�H���g�́A{@link #DEFAULT_STATUS_ERROR}�B
     *
     * @param status �X�e�[�^�X�l
     */
    public void setErrorStatus(int status);
    
    /**
     * ��O�������������̃X�e�[�^�X�l���擾����B<p>
     *
     * @return �X�e�[�^�X�l
     */
    public int getErrorStatus();
    
    /**
     * �n���h�����O���ɃG���[�����������ꍇ�ɏo�͂��郍�O�̃��b�Z�[�WID��ݒ肷��B<p>
     * �f�t�H���g�́Anull�ŁA���O���o�͂��Ȃ��B<br>
     *
     * @param id ���O�̃��b�Z�[�WID
     */
    public void setErrorLogMessageId(String id);
    
    /**
     * �n���h�����O���ɃG���[�����������ꍇ�ɏo�͂��郍�O�̃��b�Z�[�WID���擾����B<p>
     * 
     * @return ���O�̃��b�Z�[�WID
     */
    public String getErrorLogMessageId();
    
    /**
     * �n���h�����O���ɃG���[���������A�K��̃��g���C�񐔂��z�����ꍇ�ɏo�͂��郍�O�̃��b�Z�[�WID��ݒ肷��B<p>
     * �f�t�H���g�́Anull�ŁA���O���o�͂��Ȃ��B<br>
     *
     * @param id ���O�̃��b�Z�[�WID
     */
    public void setRetryOverErrorLogMessageId(String id);
    
    /**
     * �n���h�����O���ɃG���[���������A�K��̃��g���C�񐔂��z�����ꍇ�ɏo�͂��郍�O�̃��b�Z�[�WID���擾����B<p>
     * 
     * @return ���O�̃��b�Z�[�WID
     */
    public String getRetryOverErrorLogMessageId();
}