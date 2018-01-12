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
package jp.ossc.nimbus.service.aop.interceptor;

import java.util.Map;

import jp.ossc.nimbus.core.*;

/**
 * {@link MethodJournalInterceptorService}��MBean�C���^�t�F�[�X<p>
 * 
 * @author M.Takata
 * @see MethodJournalInterceptorService
 */
public interface MethodJournalInterceptorServiceMBean extends ServiceBaseMBean{
    
    /**
     * �W���[�i���J�n���̃f�t�H���g�̃W���[�i���L�[�B<p>
     */
    public static final String DEFAULT_REQUEST_JOURNAL_KEY = "Request";
    
    /**
     * ���\�b�h�Ăяo�����̃f�t�H���g�̃W���[�i���L�[�B<p>
     */
    public static final String DEFAULT_METHOD_CALL_JOURNAL_KEY
         = "MethodCall";
    
    /**
     * ���\�b�h�߂莞�̃f�t�H���g�̃W���[�i���L�[�B<p>
     */
    public static final String DEFAULT_METHOD_RETURN_JOURNAL_KEY
         = "MethodReturn";
    
    /**
     * ���N�G�X�gID���擾����{@link jp.ossc.nimbus.service.context.Context}�T�[�r�X�̃T�[�r�X����ݒ肷��B<p>
     *
     * @param name Context�T�[�r�X�̃T�[�r�X��
     * @see #getThreadContextServiceName()
     */
    public void setThreadContextServiceName(ServiceName name);
    
    /**
     * ���N�G�X�gID���擾����{@link jp.ossc.nimbus.service.context.Context}�T�[�r�X�̃T�[�r�X�����擾����B<p>
     *
     * @return Context�T�[�r�X�̃T�[�r�X��
     * @see #setThreadContextServiceName(ServiceName)
     */
    public ServiceName getThreadContextServiceName();
    
    /**
     * Context�T�[�r�X�ɐݒ肳�ꂽ���N�G�X�gID�̃L�[����ݒ肷��B<p>
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
     * {@link jp.ossc.nimbus.service.journal.Journal Journal}�T�[�r�X�̃T�[�r�X����ݒ肷��B<p>
     *
     * @param name Journal�T�[�r�X�̃T�[�r�X��
     * @see #getJournalServiceName()
     */
    public void setJournalServiceName(ServiceName name);
    
    /**
     * {@link jp.ossc.nimbus.service.journal.Journal Journal}�T�[�r�X�̃T�[�r�X�����擾����B
     *
     * @return Journal�T�[�r�X�̃T�[�r�X��
     * @see #setJournalServiceName(ServiceName)
     */
    public ServiceName getJournalServiceName();
    
    /**
     * �W���[�i���J�n�̃W���[�i���ҏW�Ɏg�p����{@link jp.ossc.nimbus.service.journal.editorfinder.EditorFinder EditorFinder}�T�[�r�X�̃T�[�r�X����ݒ肷��B<p>
     *
     * @param name EditorFinder�T�[�r�X�̃T�[�r�X��
     * @see #getRequestEditorFinderServiceName()
     */
    public void setRequestEditorFinderServiceName(ServiceName name);
    
    /**
     * �W���[�i���J�n�̃W���[�i���ҏW�Ɏg�p����{@link jp.ossc.nimbus.service.journal.editorfinder.EditorFinder EditorFinder}�T�[�r�X�̃T�[�r�X�����擾����B<p>
     *
     * @return EditorFinder�T�[�r�X�̃T�[�r�X��
     * @see #setRequestEditorFinderServiceName(ServiceName)
     */
    public ServiceName getRequestEditorFinderServiceName();
    
    /**
     * ���\�b�h�Ăяo���̃W���[�i���ҏW�Ɏg�p����{@link jp.ossc.nimbus.service.journal.editorfinder.EditorFinder EditorFinder}�T�[�r�X�̃T�[�r�X����ݒ肷��B<p>
     *
     * @param name EditorFinder�T�[�r�X�̃T�[�r�X��
     * @see #getMethodCallEditorFinderServiceName()
     */
    public void setMethodCallEditorFinderServiceName(ServiceName name);
    
    /**
     * ���\�b�h�Ăяo���̃W���[�i���ҏW�Ɏg�p����{@link jp.ossc.nimbus.service.journal.editorfinder.EditorFinder EditorFinder}�T�[�r�X�̃T�[�r�X�����擾����B<p>
     *
     * @return EditorFinder�T�[�r�X�̃T�[�r�X��
     * @see #setMethodCallEditorFinderServiceName(ServiceName)
     */
    public ServiceName getMethodCallEditorFinderServiceName();
    
    /**
     * ���\�b�h�߂�̃W���[�i���ҏW�Ɏg�p����{@link jp.ossc.nimbus.service.journal.editorfinder.EditorFinder EditorFinder}�T�[�r�X�̃T�[�r�X����ݒ肷��B<p>
     *
     * @param name EditorFinder�T�[�r�X�̃T�[�r�X��
     * @see #getMethodReturnEditorFinderServiceName()
     */
    public void setMethodReturnEditorFinderServiceName(ServiceName name);
    
    /**
     * ���\�b�h�߂�̃W���[�i���ҏW�Ɏg�p����{@link jp.ossc.nimbus.service.journal.editorfinder.EditorFinder EditorFinder}�T�[�r�X�̃T�[�r�X�����擾����B<p>
     *
     * @return EditorFinder�T�[�r�X�̃T�[�r�X��
     * @see #setMethodReturnEditorFinderServiceName(ServiceName)
     */
    public ServiceName getMethodReturnEditorFinderServiceName();
    
    /**
     * �o�͂����W���[�i���̃��[�g�v�f�̃L�[��ݒ肷��B<p>
     * �����Ŏw�肳�ꂽ�L�[�́A{@link jp.ossc.nimbus.service.journal.Journal#startJournal(String)}�̈����Ƃ��Ďg�p�����B<br>
     * �܂��A�w�肵�Ȃ��ꍇ�́A"Request"���g�p�����B<br>
     *
     * @param key �o�͂����W���[�i���̃��[�g�v�f�̃L�[
     * @see #getRequestJournalKey()
     */
    public void setRequestJournalKey(String key);
    
    /**
     * �o�͂����W���[�i���̃��[�g�v�f�̃L�[���擾����B<p>
     *
     * @return �o�͂����W���[�i���̃��[�g�v�f�̃L�[
     * @see #setRequestJournalKey(String)
     */
    public String getRequestJournalKey();
        
    /**
     * �o�͂����W���[�i���̃��\�b�h�Ăяo���v�f�̃L�[��ݒ肷��B<p>
     * �����Ŏw�肳�ꂽ�L�[�́A{@link jp.ossc.nimbus.service.journal.Journal#addInfo(String, Object)}�̑������Ƃ��Ďg�p�����B<br>
     * �܂��A�w�肵�Ȃ��ꍇ�́A"MethodCall"���g�p�����B<br>
     *
     * @param key �o�͂����W���[�i���̃��\�b�h�Ăяo���v�f�̃L�[
     * @see #getMethodCallJournalKey()
     */
    public void setMethodCallJournalKey(String key);
    
    /**
     * �o�͂����W���[�i���̃��\�b�h�Ăяo���v�f�̃L�[���擾����B<p>
     *
     * @return �o�͂����W���[�i���̃��\�b�h�Ăяo���v�f�̃L�[
     * @see #setMethodCallJournalKey(String)
     */
    public String getMethodCallJournalKey();
    
    /**
     * �o�͂����W���[�i���̃��\�b�h�߂�v�f�̃L�[��ݒ肷��B<p>
     * �����Ŏw�肳�ꂽ�L�[�́A{@link jp.ossc.nimbus.service.journal.Journal#addInfo(String, Object)}�̑������Ƃ��Ďg�p�����B<br>
     * �܂��A�w�肵�Ȃ��ꍇ�́A"MethodReturn"���g�p�����B<br>
     *
     * @param key �o�͂����W���[�i���̃��\�b�h�߂�v�f�̃L�[
     * @see #getMethodReturnJournalKey()
     */
    public void setMethodReturnJournalKey(String key);
    
    /**
     * �o�͂����W���[�i���̃��\�b�h�߂�v�f�̃L�[���擾����B<p>
     *
     * @return �o�͂����W���[�i���̃��\�b�h�߂�v�f�̃L�[
     * @see #setMethodReturnJournalKey(String)
     */
    public String getMethodReturnJournalKey();
    
    /**
     * �W���[�i���o�͂��s�����ǂ�����ݒ肷��B<p>
     * �f�t�H���g�ł́Atrue�B
     *
     * @param enable �W���[�i���o�͂��s���ꍇtrue
     * @see #isEnabled()
     */
    public void setEnabled(boolean enable);
    
    /**
     * �W���[�i���o�͂��s�����ǂ����𔻒肷��B<p>
     *
     * @return �W���[�i���o�͂��s���ꍇtrue
     * @see #setEnabled(boolean)
     */
    public boolean isEnabled();
    
    /**
     * ���\�b�h�Ăяo�����ɕ�����ʉ߂����ꍇ�ɁA2��ڈȍ~�̃W���[�i�����L�^���Ȃ����ǂ�����ݒ肷��B<p>
     * �f�t�H���g�́Afalse�B<br>
     *
     * @param isBlock �L�^���Ȃ��ꍇ�́Atrue
     */
    public void setBushingCallBlock(boolean isBlock);
    
    /**
     * ���\�b�h�Ăяo�����ɕ�����ʉ߂����ꍇ�ɁA2��ڈȍ~�̃W���[�i�����L�^���Ȃ����ǂ����𔻒肷��B<p>
     *
     * @return true�̏ꍇ�́A�L�^���Ȃ�
     */
    public boolean isBushingCallBlock();
    
    /**
     * Context�T�[�r�X����w�肳�ꂽ�L�[�Œl���擾���āA�w�肳�ꂽ�L�[�ŃW���[�i���ɏo�͂���悤�ɐݒ肷��B<p>
     *
     * @param contextKey Context�T�[�r�X����擾����L�[
     * @param journalKey �W���[�i���ɏo�͂���L�[
     */
    public void setContextJournalMapping(String contextKey, String journalKey);
    
    /**
     * �w�肳�ꂽContext�T�[�r�X����擾����L�[�ŁA�W���[�i���ɏo�͂���L�[���擾����B<p>
     *
     * @param contextKey Context�T�[�r�X����擾����L�[
     * @return �W���[�i���ɏo�͂���L�[
     */
    public String getContextJournalMapping(String contextKey);
    
    /**
     * Context�T�[�r�X����擾����L�[�ƁA�W���[�i���ɏo�͂���L�[�̃}�b�s���O���擾����B<p>
     *
     * @return Context�T�[�r�X����擾����L�[�ƁA�W���[�i���ɏo�͂���L�[�̃}�b�s���O
     */
    public Map getContextJournalMap();
    
    /**
     * InvocationContext����w�肳�ꂽ�������Œl���擾���āA�w�肳�ꂽ�L�[�ŃW���[�i���ɏo�͂���悤�ɐݒ肷��B<p>
     *
     * @param attributeName InvocationContext����擾���鑮����
     * @param journalKey �W���[�i���ɏo�͂���L�[
     */
    public void setInvocationContextJournalMapping(String attributeName, String journalKey);
    
    /**
     * �w�肳�ꂽInvocationContext����擾���鑮�����ŁA�W���[�i���ɏo�͂���L�[���擾����B<p>
     *
     * @param attributeName InvocationContext����擾���鑮����
     * @return �W���[�i���ɏo�͂���L�[
     */
    public String getInvocationContextJournalMapping(String attributeName);
    
    /**
     * InvocationContext����擾���鑮�����ƁA�W���[�i���ɏo�͂���L�[�̃}�b�s���O���擾����B<p>
     *
     * @return InvocationContext����擾���鑮�����ƁA�W���[�i���ɏo�͂���L�[�̃}�b�s���O
     */
    public Map getInvocationContextJournalMap();
}
