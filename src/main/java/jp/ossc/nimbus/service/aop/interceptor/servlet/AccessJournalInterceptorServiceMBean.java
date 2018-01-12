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
 * {@link AccessJournalInterceptorService}��MBean�C���^�t�F�[�X�B<p>
 * 
 * @author M.Takata
 * @see AccessJournalInterceptorService
 */
public interface AccessJournalInterceptorServiceMBean
 extends ServletFilterInterceptorServiceMBean{
    
    /**
     * �A�N�Z�X�W���[�i���̃��[�g�X�e�b�v�̃L�[�̃f�t�H���g�l�B<p>
     */
    public static final String DEFAULT_ACCESS_JOURNAL_KEY = "Access";
    
    /**
     * �A�N�Z�X�W���[�i���̃X���b�h���̃L�[�l�B<p>
     */
    public static final String THREAD_NAME_JOURNAL_KEY = "ThreadName";
    
    /**
     * �A�N�Z�X�W���[�i���̃X���b�hID�̃L�[�l�B<p>
     */
    public static final String THREAD_ID_JOURNAL_KEY = "ThreadId";
    
    /**
     * �A�N�Z�X�W���[�i���̃��N�G�X�g�X�e�b�v�̃L�[�̃f�t�H���g�l�B<p>
     */
    public static final String DEFAULT_REQUEST_JOURNAL_KEY = "Request";
    
    /**
     * �A�N�Z�X�W���[�i���̃��X�|���X�X�e�b�v�̃L�[�̃f�t�H���g�l�B<p>
     */
    public static final String DEFAULT_RESPONSE_JOURNAL_KEY = "Response";
    
    /**
     * �A�N�Z�X�W���[�i����ServletRequest�v�f�̃L�[�̃f�t�H���g�l�B<p>
     */
    public static final String DEFAULT_SERVLET_REQUEST_JOURNAL_KEY
         = "ServletRequest";
    
    /**
     * �A�N�Z�X�W���[�i����ServletResponse�v�f�̃L�[�̃f�t�H���g�l�B<p>
     */
    public static final String DEFAULT_SERVLET_RESPONSE_JOURNAL_KEY
         = "ServletResponse";
    
    /**
     * �A�N�Z�X�W���[�i����HttpSession�v�f�̃L�[�̃f�t�H���g�l�B<p>
     */
    public static final String DEFAULT_HTTP_SESSION_JOURNAL_KEY
         = "HttpSession";
    
    /**
     * �A�N�Z�X�W���[�i�����L�^���ł��鎖���������N�G�X�g�������B<p>
     */
    public static final String ACCESS_JOURNAL_RECORDED
         = AccessJournalInterceptorService.class.getName() + ".Recorded";
    
    /**
     * {@link jp.ossc.nimbus.service.context.Context Context}�T�[�r�X���烊�N�G�X�gID���擾����ꍇ�́A���N�G�X�gID�̃L�[�̃f�t�H���g�l�B<p>
     */
    public static final String DEFAULT_REQUEST_ID_KEY
         = ThreadContextKey.REQUEST_ID;
    
    /**
     * �A�N�Z�X�W���[�i���̃��[�g�X�e�b�v�̃L�[��ݒ肷��B<p>
     * �f�t�H���g�́A{@link #DEFAULT_ACCESS_JOURNAL_KEY}�B<br>
     *
     * @param key ���[�g�X�e�b�v�̃L�[
     * @see #DEFAULT_ACCESS_JOURNAL_KEY
     */
    public void setAccessJournalKey(String key);
    
    /**
     * �A�N�Z�X�W���[�i���̃��[�g�X�e�b�v�̃L�[���擾����B<p>
     *
     * @return ���[�g�X�e�b�v�̃L�[
     */
    public String getAccessJournalKey();
    
    /**
     * �A�N�Z�X�W���[�i���̃��N�G�X�g�X�e�b�v�̃L�[��ݒ肷��B<p>
     * �f�t�H���g�́A{@link #DEFAULT_REQUEST_JOURNAL_KEY}�B<br>
     *
     * @param key ���N�G�X�g�X�e�b�v�̃L�[
     * @see #DEFAULT_REQUEST_JOURNAL_KEY
     */
    public void setRequestJournalKey(String key);
    
    /**
     * �A�N�Z�X�W���[�i���̃��N�G�X�g�X�e�b�v�̃L�[���擾����B<p>
     *
     * @return ���N�G�X�g�X�e�b�v�̃L�[
     */
    public String getRequestJournalKey();
    
    /**
     * �A�N�Z�X�W���[�i���̃��X�|���X�X�e�b�v�̃L�[��ݒ肷��B<p>
     * �f�t�H���g�́A{@link #DEFAULT_RESPONSE_JOURNAL_KEY}�B<br>
     *
     * @param key ���X�|���X�X�e�b�v�̃L�[
     * @see #DEFAULT_RESPONSE_JOURNAL_KEY
     */
    public void setResponseJournalKey(String key);
    
    /**
     * �A�N�Z�X�W���[�i���̃��X�|���X�X�e�b�v�̃L�[���擾����B<p>
     *
     * @return ���X�|���X�X�e�b�v�̃L�[
     */
    public String getResponseJournalKey();
    
    /**
     * �A�N�Z�X�W���[�i����ServletRequest�v�f�̃L�[��ݒ肷��B<p>
     * �f�t�H���g�́A{@link #DEFAULT_SERVLET_REQUEST_JOURNAL_KEY}�B<br>
     *
     * @param key ServletRequest�v�f�̃L�[
     * @see #DEFAULT_SERVLET_REQUEST_JOURNAL_KEY
     */
    public void setServletRequestJournalKey(String key);
    
    /**
     * �A�N�Z�X�W���[�i����ServletRequest�v�f�̃L�[���擾����B<p>
     *
     * @return ServletRequest�v�f�̃L�[
     */
    public String getServletRequestJournalKey();
    
    /**
     * �A�N�Z�X�W���[�i����ServletResponse�v�f�̃L�[��ݒ肷��B<p>
     * �f�t�H���g�́A{@link #DEFAULT_SERVLET_RESPONSE_JOURNAL_KEY}�B<br>
     *
     * @param key ServletResponse�v�f�̃L�[
     * @see #DEFAULT_SERVLET_RESPONSE_JOURNAL_KEY
     */
    public void setServletResponseJournalKey(String key);
    
    /**
     * �A�N�Z�X�W���[�i����ServletResponse�v�f�̃L�[���擾����B<p>
     *
     * @return ServletResponse�v�f�̃L�[
     */
    public String getServletResponseJournalKey();
    
    /**
     * �A�N�Z�X�W���[�i����HttpSession�v�f�̃L�[��ݒ肷��B<p>
     * �f�t�H���g�́A{@link #DEFAULT_HTTP_SESSION_JOURNAL_KEY}�B<br>
     *
     * @param key HttpSession�v�f�̃L�[
     * @see #DEFAULT_HTTP_SESSION_JOURNAL_KEY
     */
    public void setHttpSessionJournalKey(String key);
    
    /**
     * �A�N�Z�X�W���[�i����HttpSession�v�f�̃L�[���擾����B<p>
     *
     * @return HttpSession�v�f�̃L�[
     */
    public String getHttpSessionJournalKey();
    
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
     * �A�N�Z�X�W���[�i���̃��[�g�g�X�e�b�v��ҏW����{@link jp.ossc.nimbus.service.journal.editorfinder.EditorFinder EditorFinder}�T�[�r�X�̃T�[�r�X����ݒ肷��B<p>
     *
     * @param name EditorFinder�T�[�r�X�̃T�[�r�X��
     */
    public void setAccessEditorFinderServiceName(ServiceName name);
    
    /**
     * �A�N�Z�X�W���[�i���̃��[�g�X�e�b�v��ҏW����{@link jp.ossc.nimbus.service.journal.editorfinder.EditorFinder EditorFinder}�T�[�r�X�̃T�[�r�X�����擾����B<p>
     *
     * @return EditorFinder�T�[�r�X�̃T�[�r�X��
     */
    public ServiceName getAccessEditorFinderServiceName();
    
    /**
     * �A�N�Z�X�W���[�i���̃��N�G�X�g�X�e�b�v��ҏW����{@link jp.ossc.nimbus.service.journal.editorfinder.EditorFinder EditorFinder}�T�[�r�X�̃T�[�r�X����ݒ肷��B<p>
     *
     * @param name EditorFinder�T�[�r�X�̃T�[�r�X��
     */
    public void setRequestEditorFinderServiceName(ServiceName name);
    
    /**
     * �A�N�Z�X�W���[�i���̃��N�G�X�g�X�e�b�v��ҏW����{@link jp.ossc.nimbus.service.journal.editorfinder.EditorFinder EditorFinder}�T�[�r�X�̃T�[�r�X�����擾����B<p>
     *
     * @return EditorFinder�T�[�r�X�̃T�[�r�X��
     */
    public ServiceName getRequestEditorFinderServiceName();
    
    /**
     * �A�N�Z�X�W���[�i���̃��X�|���X�X�e�b�v��ҏW����{@link jp.ossc.nimbus.service.journal.editorfinder.EditorFinder EditorFinder}�T�[�r�X�̃T�[�r�X����ݒ肷��B<p>
     *
     * @param name EditorFinder�T�[�r�X�̃T�[�r�X��
     */
    public void setResponseEditorFinderServiceName(ServiceName name);
    
    /**
     * �A�N�Z�X�W���[�i���̃��X�|���X�X�e�b�v��ҏW����{@link jp.ossc.nimbus.service.journal.editorfinder.EditorFinder EditorFinder}�T�[�r�X�̃T�[�r�X�����擾����B<p>
     *
     * @return EditorFinder�T�[�r�X�̃T�[�r�X��
     */
    public ServiceName getResponseEditorFinderServiceName();
    
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
    
    /**
     * ���X�|���X�����b�v���邩�ǂ�����ݒ肷��B<p>
     * �f�t�H���g�́Afalse�B<br>
     * ���X�|���X�����b�v����ƁA���X�|���X�̏ڍׂȏ����L�^�ł���B<br>
     * �L�^�ł�����̏ڍׂ́A{@link jp.ossc.nimbus.service.journal.editor.JournalServletResponseWrapper JournalServletResponseWrapper}�y��{@link jp.ossc.nimbus.service.journal.editor.JournalHttpServletResponseWrapper JournalHttpServletResponseWrapper}���Q�ƁB<br>
     *
     * @param isWrap ���b�v����ꍇ�́Atrue
     */
    public void setResponseWrap(boolean isWrap);
    
    /**
     * ���X�|���X�����b�v���邩�ǂ����𔻒肷��B<p>
     *
     * @return true�̏ꍇ�́A���b�v����
     */
    public boolean isResponseWrap();
    
    /**
     * ���X�|���X�����b�v����ꍇ�ɁA���X�|���X�ւ̏������݂��o�b�t�@�����O���邩�ǂ�����ݒ肷��B<p>
     * �f�t�H���g�́Afalse�B<br>
     *
     * @param isBuffered �o�b�t�@�����O����ꍇ�́Atrue
     */
    public void setResponseBufferedOutput(boolean isBuffered);
    
    /**
     * ���X�|���X�����b�v����ꍇ�ɁA���X�|���X�ւ̏������݂��o�b�t�@�����O���邩�ǂ����𔻒肷��B<p>
     *
     * @return true�̏ꍇ�́A�o�b�t�@�����O����
     */
    public boolean isResponseBufferedOutput();
    
    /**
     * 1���N�G�X�g�������ɕ�����ʉ߂����ꍇ�ɁA2��ڈȍ~�̃W���[�i�����L�^���Ȃ����ǂ�����ݒ肷��B<p>
     * �f�t�H���g�́Afalse�B<br>
     *
     * @param isBlock �L�^���Ȃ��ꍇ�́Atrue
     */
    public void setBushingRequestBlock(boolean isBlock);
    
    /**
     * 1���N�G�X�g�������ɕ�����ʉ߂����ꍇ�ɁA2��ڈȍ~�̃W���[�i�����L�^���Ȃ����ǂ����𔻒肷��B<p>
     * �f�t�H���g�́Afalse�B<br>
     *
     * @return true�̏ꍇ�́A�L�^���Ȃ�
     */
    public boolean isBushingRequestBlock();
    
    /**
     * ���N�G�X�g���̃Z�b�V�������W���[�i���ɏo�͂��邩�ǂ�����ݒ肷��B<p>
     * �f�t�H���g�́Afalse�ŏo�͂��Ȃ��B<br>
     *
     * @param isOutput �o�͂���ꍇtrue
     */
    public void setOutputRequestSession(boolean isOutput);
    
    /**
     * ���N�G�X�g���̃Z�b�V�������W���[�i���ɏo�͂��邩�ǂ����𔻒肷��B<p>
     *
     * @return true�̏ꍇ�o�͂���
     */
    public boolean isOutputRequestSession();
    
    /**
     * ���X�|���X���̃Z�b�V�������W���[�i���ɏo�͂��邩�ǂ�����ݒ肷��B<p>
     * �f�t�H���g�́Afalse�ŏo�͂��Ȃ��B<br>
     *
     * @param isOutput �o�͂���ꍇtrue
     */
    public void setOutputResponseSession(boolean isOutput);
    
    /**
     * ���X�|���X���̃Z�b�V�������W���[�i���ɏo�͂��邩�ǂ����𔻒肷��B<p>
     *
     * @return true�̏ꍇ�o�͂���
     */
    public boolean isOutputResponseSession();
}