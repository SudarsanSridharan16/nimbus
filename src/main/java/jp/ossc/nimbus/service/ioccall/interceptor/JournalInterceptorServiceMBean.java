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
package jp.ossc.nimbus.service.ioccall.interceptor;

import jp.ossc.nimbus.core.*;
import jp.ossc.nimbus.service.aop.interceptor.ThreadContextKey;

/**
 * {@link JournalInterceptorService}��MBean�C���^�t�F�[�X�B<p>
 * 
 * @author M.Takata
 * @see JournalInterceptorService
 */
public interface JournalInterceptorServiceMBean extends ServiceBaseMBean{
    
    /**
     * �W���[�i���̃X�e�b�v�̃L�[�̃f�t�H���g�l�B<p>
     */
    public static final String DEFAULT_STEP_JOURNAL_KEY = "Step";
    
    /**
     * �W���[�i���̓��͏��̃L�[�̃f�t�H���g�l�B<p>
     */
    public static final String DEFAULT_INPUT_JOURNAL_KEY = "Input";
    
    /**
     * �W���[�i���̏o�͏��̃L�[�̃f�t�H���g�l�B<p>
     */
    public static final String DEFAULT_OUTPUT_JOURNAL_KEY = "Output";
    
    /**
     * �W���[�i���̗�O���̃L�[�̃f�t�H���g�l�B<p>
     */
    public static final String DEFAULT_EXCEPTION_JOURNAL_KEY = "Exception";
    
    /**
     * {@link jp.ossc.nimbus.service.context.Context Context}�T�[�r�X���烊�N�G�X�gID���擾����ꍇ�́A���N�G�X�gID�̃L�[�̃f�t�H���g�l�B<p>
     */
    public static final String DEFAULT_REQUEST_ID_KEY
         = ThreadContextKey.REQUEST_ID;
    
    /**
     * �W���[�i���̃X�e�b�v�̃L�[��ݒ肷��B<p>
     * �f�t�H���g�́A{@link #DEFAULT_STEP_JOURNAL_KEY}�B<br>
     *
     * @param key �X�e�b�v�̃L�[
     * @see #DEFAULT_STEP_JOURNAL_KEY
     */
    public void setStepJournalKey(String key);
    
    /**
     * �W���[�i���̃X�e�b�v�̃L�[���擾����B<p>
     *
     * @return �X�e�b�v�̃L�[
     */
    public String getStepJournalKey();
    
    /**
     * �W���[�i���̓��͏��̃L�[��ݒ肷��B<p>
     * �f�t�H���g�́A{@link #DEFAULT_INPUT_JOURNAL_KEY}�B<br>
     *
     * @param key ���͏��̃L�[
     * @see #DEFAULT_INPUT_JOURNAL_KEY
     */
    public void setInputJournalKey(String key);
    
    /**
     * �W���[�i���̓��͏��̃L�[���擾����B<p>
     *
     * @return ���͏��̃L�[
     */
    public String getInputJournalKey();
    
    /**
     * �W���[�i���̏o�͏��̃L�[��ݒ肷��B<p>
     * �f�t�H���g�́A{@link #DEFAULT_OUTPUT_JOURNAL_KEY}�B<br>
     *
     * @param key �o�͏��̃L�[
     * @see #DEFAULT_OUTPUT_JOURNAL_KEY
     */
    public void setOutputJournalKey(String key);
    
    /**
     * �W���[�i���̏o�͏��̃L�[���擾����B<p>
     *
     * @return �o�͏��̃L�[
     */
    public String getOutputJournalKey();
    
    /**
     * �W���[�i���̗�O���̃L�[��ݒ肷��B<p>
     * �f�t�H���g�́A{@link #DEFAULT_OUTPUT_JOURNAL_KEY}�B<br>
     *
     * @param key ��O���̃L�[
     * @see #DEFAULT_EXCEPTION_JOURNAL_KEY
     */
    public void setExceptionJournalKey(String key);
    
    /**
     * �W���[�i���̗�O���̃L�[���擾����B<p>
     *
     * @return ��O���̃L�[
     */
    public String getExceptionJournalKey();
    
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
     * �W���[�i���̃g�X�e�b�v��ҏW����{@link jp.ossc.nimbus.service.journal.editorfinder.EditorFinder EditorFinder}�T�[�r�X�̃T�[�r�X����ݒ肷��B<p>
     *
     * @param name EditorFinder�T�[�r�X�̃T�[�r�X��
     */
    public void setStepEditorFinderServiceName(ServiceName name);
    
    /**
     * �W���[�i���̃X�e�b�v��ҏW����{@link jp.ossc.nimbus.service.journal.editorfinder.EditorFinder EditorFinder}�T�[�r�X�̃T�[�r�X�����擾����B<p>
     *
     * @return EditorFinder�T�[�r�X�̃T�[�r�X��
     */
    public ServiceName getStepEditorFinderServiceName();
    
    /**
     * �W���[�i���̓��͏���ҏW����{@link jp.ossc.nimbus.service.journal.editorfinder.EditorFinder EditorFinder}�T�[�r�X�̃T�[�r�X����ݒ肷��B<p>
     *
     * @param name EditorFinder�T�[�r�X�̃T�[�r�X��
     */
    public void setInputEditorFinderServiceName(ServiceName name);
    
    /**
     * �W���[�i���̓��͏���ҏW����{@link jp.ossc.nimbus.service.journal.editorfinder.EditorFinder EditorFinder}�T�[�r�X�̃T�[�r�X�����擾����B<p>
     *
     * @return EditorFinder�T�[�r�X�̃T�[�r�X��
     */
    public ServiceName getInputEditorFinderServiceName();
    
    /**
     * �W���[�i���̏o�͏���ҏW����{@link jp.ossc.nimbus.service.journal.editorfinder.EditorFinder EditorFinder}�T�[�r�X�̃T�[�r�X����ݒ肷��B<p>
     *
     * @param name EditorFinder�T�[�r�X�̃T�[�r�X��
     */
    public void setOutputEditorFinderServiceName(ServiceName name);
    
    /**
     * �W���[�i���̏o�͏���ҏW����{@link jp.ossc.nimbus.service.journal.editorfinder.EditorFinder EditorFinder}�T�[�r�X�̃T�[�r�X�����擾����B<p>
     *
     * @return EditorFinder�T�[�r�X�̃T�[�r�X��
     */
    public ServiceName getOutputEditorFinderServiceName();
    
    /**
     * �W���[�i���̗�O����ҏW����{@link jp.ossc.nimbus.service.journal.editorfinder.EditorFinder EditorFinder}�T�[�r�X�̃T�[�r�X����ݒ肷��B<p>
     *
     * @param name EditorFinder�T�[�r�X�̃T�[�r�X��
     */
    public void setExceptionEditorFinderServiceName(ServiceName name);
    
    /**
     * �W���[�i���̗�O����ҏW����{@link jp.ossc.nimbus.service.journal.editorfinder.EditorFinder EditorFinder}�T�[�r�X�̃T�[�r�X�����擾����B<p>
     *
     * @return EditorFinder�T�[�r�X�̃T�[�r�X��
     */
    public ServiceName getExceptionEditorFinderServiceName();
    
    /**
     * ���N�G�X�gID���擾���邽�߂�{@link jp.ossc.nimbus.service.sequence.Sequence Sequence}�T�[�r�X�̃T�[�r�X����ݒ肷��B<p>
     *
     * @param name Sequence�T�[�r�X�̃T�[�r�X��
     */
    public void setSequenceServiceName(ServiceName name);
    
    /**
     * ���N�G�X�gID���擾���邽�߂�{@link jp.ossc.nimbus.service.sequence.Sequence Sequence}�T�[�r�X�̃T�[�r�X�����擾����B<p>
     *
     * @return Sequence�T�[�r�X�̃T�[�r�X��
     */
    public ServiceName getSequenceServiceName();
    
    /**
     * ���N�G�X�gID���擾���邽�߂�{@link jp.ossc.nimbus.service.context.Context Context}�T�[�r�X�̃T�[�r�X����ݒ肷��B<p>
     *
     * @param name Context�T�[�r�X�̃T�[�r�X��
     */
    public void setContextServiceName(ServiceName name);
    
    /**
     * ���N�G�X�gID���擾���邽�߂�{@link jp.ossc.nimbus.service.context.Context Context}�T�[�r�X�̃T�[�r�X�����擾����B<p>
     *
     * @return Context�T�[�r�X�̃T�[�r�X��
     */
    public ServiceName getContextServiceName();
    
    /**
     * {@link jp.ossc.nimbus.service.context.Context Context}�T�[�r�X���烊�N�G�X�gID���擾����ꍇ�́A���N�G�X�gID�̃L�[��ݒ肷��B<p>
     * �f�t�H���g�́A{@link #DEFAULT_REQUEST_ID_KEY}�B<br>
     *
     * @param key Context�T�[�r�X�Ɋi�[���ꂽ���N�G�X�gID�̃L�[
     * @see #DEFAULT_REQUEST_ID_KEY
     */
    public void setRequestIDKey(String key);
    
    /**
     * {@link jp.ossc.nimbus.service.context.Context Context}�T�[�r�X���烊�N�G�X�gID���擾����ꍇ�́A���N�G�X�gID�̃L�[���擾����B<p>
     *
     * @return Context�T�[�r�X�Ɋi�[���ꂽ���N�G�X�gID�̃L�[
     */
    public String getRequestIDKey();
}