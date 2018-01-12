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
 * {@link ServletRequestExchangeInterceptorService}��MBean�C���^�t�F�[�X�B<p>
 * 
 * @author M.Takata
 * @see ServletRequestExchangeInterceptorService
 */
public interface ServletRequestExchangeInterceptorServiceMBean
 extends ServletFilterInterceptorServiceMBean{
    
    /**
     * �f�t�H���g�̗v���I�u�W�F�N�g�̃��N�G�X�g�������B<p>
     */
    public static final String DEFAULT_REQUEST_OBJECT_ATTRIBUTE_NAME = StreamExchangeInterceptorService.DEFAULT_REQUEST_OBJECT_ATTRIBUTE_NAME;
    
    /**
     * �f�t�H���g�̗v���I�u�W�F�N�g�̃R���e�L�X�g�L�[���B<p>
     */
    public static final String DEFAULT_REQUEST_OBJECT_CONTEXT_KEY = StreamExchangeInterceptorService.DEFAULT_REQUEST_OBJECT_CONTEXT_KEY;
    
    /**
     * �f�t�H���g�̃W���[�i���̃��[�g�X�e�b�v���B<p>
     */
    public static final String DEFAULT_EXCHANGE_JOURNAL_KEY = "Exchange";
    
    /**
     * �f�t�H���g�̃W���[�i���̗v���I�u�W�F�N�g�v�f���B<p>
     */
    public static final String DEFAULT_REQUEST_OBJECT_JOURNAL_KEY = "RequestObject";
    
    /**
     * �f�t�H���g�̃W���[�i���̗�O�v�f���B<p>
     */
    public static final String DEFAULT_EXCEPTION_JOURNAL_KEY = "Exception";
    
    /**
     * �T�[�u���b�g���N�G�X�g��v���I�u�W�F�N�g�ɕϊ�����{@link jp.ossc.nimbus.util.converter.Converter Converter}�T�[�r�X����ݒ肷��B<p>
     *
     * @param name Converter�T�[�r�X��
     */
    public void setConverterServiceName(ServiceName name);
    
    /**
     * �T�[�u���b�g���N�G�X�g��v���I�u�W�F�N�g�ɕϊ�����{@link jp.ossc.nimbus.util.converter.Converter Converter}�T�[�r�X�����擾����B<p>
     *
     * @return Converter�T�[�r�X��
     */
    public ServiceName getConverterServiceName();
    
    /**
     * �v���I�u�W�F�N�g���悹��{@link jp.ossc.nimbus.service.context.Context Context}�T�[�r�X����ݒ肷��B<p>
     * ���̑�����ݒ肵�Ȃ��ꍇ�́A���N�G�X�g�����݂̂ɏ悹��B<br>
     *
     * @param name Context�T�[�r�X��
     */
    public void setThreadContextServiceName(ServiceName name);
    
    /**
     * �v���I�u�W�F�N�g���悹��{@link jp.ossc.nimbus.service.context.Context Context}�T�[�r�X�����擾����B<p>
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
}