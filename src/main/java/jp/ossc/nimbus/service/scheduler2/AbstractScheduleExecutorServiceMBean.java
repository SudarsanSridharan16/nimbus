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
package jp.ossc.nimbus.service.scheduler2;

import jp.ossc.nimbus.core.*;

/**
 * {@link AbstractScheduleExecutorService}��MBean�C���^�t�F�[�X�B<p>
 * 
 * @author M.Takata
 */
public interface AbstractScheduleExecutorServiceMBean extends ServiceBaseMBean{
    
    /**
     * �X�P�W���[�������s�J�n�����ꍇ�̃��O���b�Z�[�WID�B<p>
     */
    public static final String MSG_ID_RUN = "ASE__00001";
    
    /**
     * �X�P�W���[�������s�I�������ꍇ�̃��O���b�Z�[�WID�B<p>
     */
    public static final String MSG_ID_END = "ASE__00002";
    
    /**
     * �X�P�W���[���̎��s�Ɏ��s�����ꍇ�̃��O���b�Z�[�WID�B<p>
     */
    public static final String MSG_ID_EXECUTE_ERROR = "ASE__00003";
    
    /**
     * �X�P�W���[���̍ăX�P�W���[�����s�����ꍇ�̃��O���b�Z�[�WID�B<p>
     */
    public static final String MSG_ID_RESCHEDULE = "ASE__00004";
    
    /**
     * �X�P�W���[���̍ăX�P�W���[���Ɏ��s�����ꍇ�̃��O���b�Z�[�WID�B<p>
     */
    public static final String MSG_ID_RESCHEDULE_ERROR = "ASE__00005";
    
    /**
     * �X�P�W���[���̃��g���C�I�������ɓ��B�����ꍇ�̃��O���b�Z�[�WID�B<p>
     */
    public static final String MSG_ID_RETRY_END_ERROR = "ASE__00006";
    
    /**
     * �X�P�W���[���������I�������ꍇ�̃��O���b�Z�[�WID�B<p>
     */
    public static final String MSG_ID_ABORT = "ASE__00007";
    
    /**
     * �X�P�W���[���𖳌��������ꍇ�̃��O���b�Z�[�WID�B<p>
     */
    public static final String MSG_ID_DISABLE = "ASE__00010";
    
    /**
     * �X�P�W���[���̏�ԕύX�Ɏ��s�����ꍇ�̃��O���b�Z�[�WID�B<p>
     */
    public static final String MSG_ID_STATE_CHANGE_ERROR = "ASE__00008";
    
    /**
     * �X�P�W���[���̏�ԑJ�ڂɎ��s�����ꍇ�̃��O���b�Z�[�WID�B<p>
     */
    public static final String MSG_ID_STATE_TRANS_ERROR = "ASE__00009";
    
    /**
     * �W���[�i���J�n���̃W���[�i���L�[�B<p>
     */
    public static final String JOURNAL_KEY_EXECUTE = "Execute";
    
    /**
     * ���͂̃X�P�W���[���̃W���[�i���L�[�B<p>
     */
    public static final String JOURNAL_KEY_INPUT_SCHEDULE = "InputSchedule";
    
    /**
     * �o�͂̃X�P�W���[���̃W���[�i���L�[�B<p>
     */
    public static final String JOURNAL_KEY_OUTPUT_SCHEDULE = "OutputSchedule";
    
    /**
     * ��O�������̃W���[�i���L�[�B<p>
     */
    public static final String JOURNAL_KEY_EXCEPTION = "Exception";
    
    /**
     * {@link ScheduleManager}�T�[�r�X�̃T�[�r�X����ݒ肷��B<p>
     *
     * @param name ScheduleManager�T�[�r�X�̃T�[�r�X��
     */
    public void setScheduleManagerServiceName(ServiceName name);
    
    /**
     * {@link ScheduleManager}�T�[�r�X�̃T�[�r�X�����擾����B<p>
     *
     * @return ScheduleManager�T�[�r�X�̃T�[�r�X��
     */
    public ServiceName getScheduleManagerServiceName();
    
    /**
     * ����ScheduleExecutor����肷��L�[��ݒ肷��B<p>
     * �f�t�H���g�́A�T�[�r�X���B<br>
     *
     * @param key �L�[
     */
    public void setKey(String key);
    
    /**
     * ����ScheduleExecutor����肷��L�[���擾����B<p>
     *
     * @return �L�[
     */
    public String getKey();
    
    /**
     * ScheduleExecutor�����s������X�P�W���[�����s�̎�ނ�ݒ肷��B<p>
     *
     * @param type �X�P�W���[�����s�̎��
     */
    public void setType(String type);
    
    /**
     * ScheduleExecutor�����s������X�P�W���[�����s�̎�ނ��擾����B<p>
     *
     * @return �X�P�W���[�����s�̎��
     */
    public String getType();
    
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
     * �W���[�i���ҏW�Ɏg�p����{@link jp.ossc.nimbus.service.journal.editorfinder.EditorFinder EditorFinder}�T�[�r�X�̃T�[�r�X����ݒ肷��B<p>
     *
     * @param name EditorFinder�T�[�r�X�̃T�[�r�X��
     * @see #getEditorFinderServiceName()
     */
    public void setEditorFinderServiceName(ServiceName name);
    
    /**
     * �W���[�i���ҏW�Ɏg�p����{@link jp.ossc.nimbus.service.journal.editorfinder.EditorFinder EditorFinder}�T�[�r�X�̃T�[�r�X�����擾����B<p>
     *
     * @return EditorFinder�T�[�r�X�̃T�[�r�X��
     * @see #setEditorFinderServiceName(ServiceName)
     */
    public ServiceName getEditorFinderServiceName();
    
    /**
     * {@link jp.ossc.nimbus.service.context.Context Context}�T�[�r�X�̃T�[�r�X����ݒ肷��B<p>
     * {@link jp.ossc.nimbus.service.sequence.Sequence Sequence}�T�[�r�X�Ŕ��Ԃ����ʔԂ��擾����B<br>
     *
     * @param name Context�T�[�r�X�̃T�[�r�X��
     */
    public void setThreadContextServiceName(ServiceName name);
    
    /**
     * {@link jp.ossc.nimbus.service.context.Context Context}�T�[�r�X�̃T�[�r�X�����擾����B<p>
     *
     * @return Context�T�[�r�X�̃T�[�r�X��
     */
    public ServiceName getThreadContextServiceName();
}
