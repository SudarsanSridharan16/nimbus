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
package jp.ossc.nimbus.service.journal;

import java.util.*;
import jp.ossc.nimbus.service.journal.editorfinder.*;

/**
 * �W���[�i���C���^�t�F�[�X�B<p>
 * �ȉ��̂悤�ȃW���[�i�����擾����B<br>
 * <ul>
 *   <li>�A�N�Z�X�J�n����</li>
 *   <li>�A�N�Z�X�I������</li>
 *   <li>�A�N�Z�X����</li>
 *   <li>�A�N�Z�X���ʎq</li>
 *   <li>�A�N�Z�X�ɂ����o�͏��</li>
 * </ul>
 * <p>
 * �܂��A�����K�w�̃V�X�e���Ŋe�K�w�ł̃W���[�i�����o�͂��A�������A�̃W���[�i���Ƃ��ďo�͂��鎖���������邽�߂ɁA�W���[�i���̓���q�\�����T�|�[�g����B<br>
 * �P����1�K�w�̃W���[�i���擾�́A�ȉ��̂悤�ɍs���B<br>
 * <pre>
 *   :
 * Journal journal = (Journal)ServiceManagerFactory.getServiceObject("Journal");
 * try{
 *     journal.startJournal("Request");
 *       :
 *     journal.addInfo("Input", input);
 *       :
 *     Fuga output = hoge.getFuga(input);
 *       :
 *     journal.addInfo("Output", output);
 *       :
 * }finally{
 *     journal.endJournal();
 * }
 * </pre>
 * ���̃R�[�h�ɂ��擾�����W���[�i���́A"Request"�Ƃ����W���[�i���̒���"Input"��"Output"���܂܂��Ƃ����\���ɂȂ�B<br>
 * �܂��A��L��hoge.getFuga()���\�b�h�̐�̉��ʂ̊K�w�ŁA�ȉ��̂悤�ȓ��l�̃R�[�h���������ꍇ�A<br>
 * <pre>
 *   :
 * Journal journal = (Journal)ServiceManagerFactory.getServiceObject("Journal");
 * try{
 *     journal.addStartStep("Request2");
 *       :
 *     journal.addInfo("Input2", input);
 *       :
 *     journal.addInfo("Output2", output);
 *       :
 * }finally{
 *     journal.addEndStep();
 * }
 * </pre>
 * ����2�K�w�̃R�[�h�ɂ��擾�����W���[�i���́A"Request"�Ƃ����W���[�i���̒���"Input"��"Request2"��"Output"���܂܂�A�X��"Request2"�ɂ́A"Input2"��"Output2"���܂܂��Ƃ�������q�\���ɂȂ�B<br>
 *
 * @author H.Nakano
 */
public interface Journal {
    //���x�������i�K�����
    //�L���ȃ��x��
    
    /**
     * Journal�o�̓��x�� DEBUG�B<p>
     */
    public static final int JOURNAL_LEVEL_DEBUG = 0;
    
    /**
     * Journal�o�̓��x�� INFO�B<p>
     */
    public static final int JOURNAL_LEVEL_INFO = 25;
    
    /**
     * Journal�o�̓��x�� WARN�B<p>
     */
    public static final int JOURNAL_LEVEL_WARN = 50;
    
    /**
     * Journal�o�̓��x�� ERROR�B<p>
     */
    public static final int JOURNAL_LEVEL_ERROR = 75;
    
    /**
     * Journal�o�̓��x�� FATAL�B<p>
     */
    public static final int JOURNAL_LEVEL_FATAL = 100;
    
    /**
     * �W���[�i���ɏo�͂���郊�N�G�X�gID���擾����B<p>
     * 
     * @return �W���[�i���ɏo�͂���郊�N�G�X�gID
     */
    public String getRequestId();
    
    /**
     * �W���[�i���ɏo�͂���郊�N�G�X�gID��ݒ肷��B<p>
     * 
     * @param requestID �W���[�i���ɏo�͂���郊�N�G�X�gID
     */
    public void setRequestId(String requestID);
    
    /**
     * �W���[�i���̎擾���J�n����B<p>
     * {@link #startJournal(String, Date, EditorFinder) startJournal(key, null, null)}�ŌĂяo���̂Ɠ����ł���B<br>
     * 
     * @param key �擾����W���[�i�����̃L�[
     * @see #startJournal(String, Date, EditorFinder)
     */
    public void startJournal(String key);
    
    /**
     * �W���[�i���̎擾���J�n����B<p>
     * {@link #startJournal(String, Date, EditorFinder) startJournal(key, null, finder)}�ŌĂяo���̂Ɠ����ł���B<br>
     * 
     * @param key �擾����W���[�i�����̃L�[
     * @param finder �W���[�i����ҏW����{@link JournalEditor}����������{@link EditorFinder}
     * @see #startJournal(String, Date, EditorFinder)
     */
    public void startJournal(String key, EditorFinder finder);
    
    /**
     * �W���[�i���̎擾���J�n����B<p>
     * {@link #startJournal(String, Date, EditorFinder) startJournal(key, startTime, null)}�ŌĂяo���̂Ɠ����ł���B<br>
     * 
     * @param key �擾����W���[�i�����̃L�[
     * @param startTime �W���[�i���擾�J�n����
     * @see #startJournal(String, Date, EditorFinder)
     */
    public void startJournal(String key, Date startTime);
    
    /**
     * �W���[�i���̎擾���J�n����B<p>
     * �W���[�i���̃��[�g�X�e�b�v���쐬����B<br>
     * ���Ƀ��[�g�X�e�b�v���쐬����Ă���ꍇ�́A���̎q�X�e�b�v���쐬����B<br>
     * �쐬���ꂽ�X�e�b�v�ɒǉ����ꂽ�W���[�i������q�X�e�b�v�́A���ꂼ��œ��Ɏw�肪�Ȃ���΁A�����Ŏw�肳�ꂽ{@link EditorFinder}��{@link JournalEditor}����������A�ҏW�����B<br>
     * EditorFinder���w�肳��Ă��Ȃ��ꍇ�́A�e�X�e�b�v���J�n�������Ɏw�肳�ꂽEditorFinder���K�p�����B����Ƀ��[�g�X�e�b�v�̏ꍇ�́A�w�肪�Ȃ���΁AJournal�T�[�r�X�̃f�t�H���g��EditorFinder���K�p�����B<br>
     * 
     * @param key �擾����W���[�i�����̃L�[
     * @param startTime �W���[�i���擾�J�n����
     * @param finder �W���[�i����ҏW����{@link JournalEditor}����������{@link EditorFinder}
     */
    public void startJournal(
        String key,
        Date startTime,
        EditorFinder finder
    );
    
    /**
     * �W���[�i���̎擾���I������B<p>
     * {@link #endJournal(Date) endJournal(null)}�ŌĂяo���̂Ɠ����ł���B<br>
     *
     * @see #endJournal(Date)
     */
    public void endJournal();
    
    /**
     * �W���[�i���̎擾���I������B<p>
     * �J�����g�̃X�e�b�v���I������B<br>
     * �܂��A�J�����g�̃X�e�b�v�����[�g�X�e�b�v�̏ꍇ�ɂ́A�W���[�i���o�͂��s���A�X�e�b�v���N���A����B<br>
     * 
     * @param endTime �W���[�i���擾�I������
     */
    public void endJournal(Date endTime);
    
    /**
     * �J�����g�̃X�e�b�v�ɃW���[�i������ǉ�����B<p>
     * {@link #addInfo(String, Object, EditorFinder) addInfo(key, value, null)}�ŌĂяo���̂Ɠ����ł���B<br>
     * 
     * @param key �X�e�b�v�ɒǉ�����W���[�i�����̃L�[
     * @param value �ǉ�����W���[�i�����
     * @see #addInfo(String, Object, EditorFinder)
     */
    public void addInfo(String key, Object value);
    
    /**
     * �J�����g�̃X�e�b�v�ɃW���[�i������ǉ�����B<p>
     * �ǉ����ꂽ�W���[�i�����́A�w�肳�ꂽ{@link EditorFinder}��{@link JournalEditor}����������A�ҏW�����B<br>
     * EditorFinder���w�肳��Ă��Ȃ��ꍇ�́A�J�����g�̃X�e�b�v���J�n�������Ɏw�肳�ꂽEditorFinder���K�p�����B<br>
     * 
     * @param key �X�e�b�v�ɒǉ�����W���[�i�����̃L�[
     * @param value �ǉ�����W���[�i�����
     * @param finder �W���[�i����ҏW����{@link JournalEditor}����������{@link EditorFinder}
     */
    public void addInfo(
        String key,
        Object value,
        EditorFinder finder
    );
    
    /**
     * �J�����g�̃X�e�b�v�ɃW���[�i������ǉ�����B<p>
     * �o�̓��x������̌�ɁA{@link #addInfo(String, Object, EditorFinder) addInfo(key, value, null)}���Ăяo���B<br>
     * 
     * @param key �X�e�b�v�ɒǉ�����W���[�i�����̃L�[
     * @param value �ǉ�����W���[�i�����
     * @param level �o�̓��x��
     * @see #addInfo(String, Object, EditorFinder)
     */
    public void addInfo(String key, Object value, int level);
    
    /**
     * �J�����g�̃X�e�b�v�ɃW���[�i������ǉ�����B<p>
     * �o�̓��x������̌�ɁA{@link #addInfo(String, Object, EditorFinder) addInfo(key, value, finder)}���Ăяo���B<br>
     * 
     * @param key �X�e�b�v�ɒǉ�����W���[�i�����̃L�[
     * @param value �ǉ�����W���[�i�����
     * @param finder �W���[�i����ҏW����{@link JournalEditor}����������{@link EditorFinder}
     * @param level �o�̓��x��
     * @see #addInfo(String, Object, EditorFinder)
     */
    public void addInfo(
        String key,
        Object value,
        EditorFinder finder,
        int level
    );
    
    /**
     * �J�����g�̃X�e�b�v�ɒǉ����ꂽ�W���[�i�����̂����A�w�肳�ꂽ�C���f�b�N�X�ȍ~���폜����B<p>
     *
     * @param from �C���f�b�N�X
     */
    public void removeInfo(int from);
    
    /**
     * �q�X�e�b�v�̃W���[�i���̎擾���J�n����B<p>
     * {@link #addStartStep(String, Date, EditorFinder) addStartStep(key, null, null)}�ŌĂяo���̂Ɠ����ł���B<br>
     * 
     * @param key �擾����W���[�i�����̃L�[
     * @see #addStartStep(String, Date, EditorFinder)
     */
    public void addStartStep(String key);
    
    /**
     * �q�X�e�b�v�̃W���[�i���̎擾���J�n����B<p>
     * {@link #addStartStep(String, Date, EditorFinder) addStartStep(key, null, finder)}�ŌĂяo���̂Ɠ����ł���B<br>
     * 
     * @param key �擾����W���[�i�����̃L�[
     * @param finder �W���[�i����ҏW����{@link JournalEditor}����������{@link EditorFinder}
     * @see #addStartStep(String, Date, EditorFinder)
     */
    public void addStartStep(String key, EditorFinder finder);
    
    /**
     * �q�X�e�b�v�̃W���[�i���̎擾���J�n����B<p>
     * {@link #addStartStep(String, Date, EditorFinder) addStartStep(key, startTime, null)}�ŌĂяo���̂Ɠ����ł���B<br>
     * 
     * @param key �擾����W���[�i�����̃L�[
     * @param startTime �W���[�i���擾�J�n����
     * @see #addStartStep(String, Date, EditorFinder)
     */
    public void addStartStep(String key, Date startTime);
    
    /**
     * �q�X�e�b�v�̃W���[�i���̎擾���J�n����B<p>
     * �W���[�i���̎q�X�e�b�v���쐬����B<br>
     * ���[�g�X�e�b�v���쐬����Ă��Ȃ��ꍇ�́A�������Ȃ��B<br>
     * �쐬���ꂽ�X�e�b�v�ɒǉ����ꂽ�W���[�i������q�X�e�b�v�́A���ꂼ��œ��Ɏw�肪�Ȃ���΁A�����Ŏw�肳�ꂽ{@link EditorFinder}��{@link JournalEditor}����������A�ҏW�����B<br>
     * EditorFinder���w�肳��Ă��Ȃ��ꍇ�́A�e�X�e�b�v���J�n�������Ɏw�肳�ꂽEditorFinder���K�p�����B<br>
     * 
     * @param key �擾����W���[�i�����̃L�[
     * @param startTime �W���[�i���擾�J�n����
     * @param finder �W���[�i����ҏW����{@link JournalEditor}����������{@link EditorFinder}
     */
    public void addStartStep(
        String key,
        Date startTime,
        EditorFinder finder
    );
    
    /**
     * �q�X�e�b�v�̃W���[�i���̎擾���I������B<p>
     * {@link #addEndStep(Date) addEndStep(null)}�ŌĂяo���̂Ɠ����ł���B<br>
     *
     * @see #addEndStep(Date)
     */
    public void addEndStep();
    
    /**
     * �q�X�e�b�v�̃W���[�i���̎擾���I������B<p>
     * �J�����g�̃X�e�b�v���I������B<br>
     * 
     * @param endTime �W���[�i���擾�I������
     */
    public void addEndStep(Date endTime);
    
    /**
     * ���݂̃W���[�i���o�͕�������擾����B<p>
     * 
     * @param finder �W���[�i����ҏW����{@link JournalEditor}����������{@link EditorFinder}
     * @return ���݂̃W���[�i���o�͕�����
     */
    public String getCurrentJournalString(EditorFinder finder);
    
    /**
     * �W���[�i�����J�n����Ă��邩�ǂ����𔻒肷��B<p>
     *
     * @return �W���[�i�����J�n����Ă���ꍇ�́Atrue
     */
    public boolean isStartJournal();
}
