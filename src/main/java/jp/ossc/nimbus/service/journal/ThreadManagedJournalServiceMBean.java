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

import jp.ossc.nimbus.core.*;

/**
 * {@link ThreadManagedJournalService}��MBean�C���^�t�F�[�X�B<p>
 * 
 * @author H.Nakano
 * @see ThreadManagedJournalService
 */
public interface ThreadManagedJournalServiceMBean extends ServiceBaseMBean{
    
    /**
     * �W���[�i�����ꂽ�I�u�W�F�N�g��ҏW����{@link JournalEditor}���o�^����Ă���{@link jp.ossc.nimbus.service.journal.editorfinder.EditorFinder EditorFinder}�̃T�[�r�X����ݒ肷��B<p>
     *
     * @param name EditorFinder�̃T�[�r�X��
     */
    public void setEditorFinderName(ServiceName name);
    
    /**
     * �W���[�i�����ꂽ�I�u�W�F�N�g��ҏW����{@link JournalEditor}���o�^����Ă���{@link jp.ossc.nimbus.service.journal.editorfinder.EditorFinder EditorFinder}�̃T�[�r�X�����擾����B<p>
     *
     * @return EditorFinder�̃T�[�r�X��
     */
    public ServiceName getEditorFinderName();
    
    /**
     * �W���[�i���̒ʔԂ𕥂��o��{@link jp.ossc.nimbus.service.sequence.Sequence Sequence}�T�[�r�X�̃T�[�r�X����ݒ肷��B<p>
     *
     * @param name Sequence�T�[�r�X�̃T�[�r�X��
     */
    public void setSequenceServiceName(ServiceName name);
    
    /**
     * �W���[�i���̒ʔԂ𕥂��o��{@link jp.ossc.nimbus.service.sequence.Sequence Sequence}�T�[�r�X�̃T�[�r�X�����擾����B<p>
     *
     * @return Sequence�T�[�r�X�̃T�[�r�X��
     */
    public ServiceName getSequenceServiceName();
    
    /**
     * �W���[�i����񓯊��ɏo�͂��邽�߂̃L���[�𐶐�����{@link jp.ossc.nimbus.service.queue.Queue Queue}�T�[�r�X�̃T�[�r�X����ݒ肷��B<p>
     *
     * @param name Queue�T�[�r�X�̃T�[�r�X��
     */
    public void setQueueServiceName(ServiceName name);
    
    /**
     * �W���[�i����񓯊��ɏo�͂��邽�߂̃L���[�𐶐�����{@link jp.ossc.nimbus.service.queue.Queue Queue}�T�[�r�X�̃T�[�r�X�����擾����B<p>
     *
     * @return Queue�T�[�r�X�̃T�[�r�X��
     */
    public ServiceName getQueueServiceName();
    
    /**
     * �W���[�i���̏o�͏���{@link jp.ossc.nimbus.service.writer.WritableRecord WritableRecord}�ɒǉ����鎞�̃L�[����ݒ肷��B<p>
     *
     * @param key �W���[�i���̏o�͏���WritableRecord�ɒǉ����鎞�̃L�[��
     */
    public void setWritableElementKey(String key);
    
    /**
     * �W���[�i���̏o�͏���{@link jp.ossc.nimbus.service.writer.WritableRecord WritableRecord}�ɒǉ����鎞�̃L�[�����擾����B<p>
     *
     * @return �W���[�i���̏o�͏���WritableRecord�ɒǉ����鎞�̃L�[��
     */
    public String getWritableElementKey();
    
    /**
     * �o�͂���W���[�i���̃J�e�S�����`����{@link jp.ossc.nimbus.service.writer.Category Category}�T�[�r�X�̖��O��ݒ肷��B<p>
     * 
     * @param names Category�T�[�r�X���̔z��
     */
    public void setCategoryServiceNames(ServiceName[] names);
    
    /**
     * �o�͂���W���[�i���̃J�e�S�����`����{@link jp.ossc.nimbus.service.writer.Category Category}�T�[�r�X�̖��O���擾����B<p>
     * 
     * @return Category�T�[�r�X���̔z��
     */
    public ServiceName[] getCategoryServiceNames();
    
    /**
     * Journal�o�̓��x����ݒ肷��B<p>
     * �����Œ�`���ꂽ���x���ȏ�̃W���[�i���̂ݏo�͂���B
     * 
     * @param level �o�̓��x��
     * @see Journal#JOURNAL_LEVEL_DEBUG
     * @see Journal#JOURNAL_LEVEL_INFO
     * @see Journal#JOURNAL_LEVEL_WARN
     * @see Journal#JOURNAL_LEVEL_ERROR
     * @see Journal#JOURNAL_LEVEL_FATAL
     */
    public void setJournalLevel(int level);
    
    /**
     * Journal�o�̓��x�����擾����B<p>
     * 
     * @return Journal�o�̓��x��
     */
    public int getJournalLevel();
    
    public void setWriteDaemonSize(int size);
    public int getWriteDaemonSize();
}
