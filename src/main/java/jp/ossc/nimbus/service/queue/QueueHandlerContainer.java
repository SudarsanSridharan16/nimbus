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
package jp.ossc.nimbus.service.queue;

/**
 * QueueHandler�R���e�i�B<p>
 * 
 * @author M.Takata
 */
public interface QueueHandlerContainer extends Queue{
    
    /**
     * QueueHandler��ݒ肷��B<p>
     *
     * @param handler QueueHandler
     */
    public void setQueueHandler(QueueHandler handler);
    
    /**
     * QueueHandler���擾����B<p>
     *
     * @return QueueHandler
     */
    public QueueHandler getQueueHandler();
    
    /**
     * �n���h�����O�̍ۂɃG���[��������������ʒm���郍�O�̃��b�Z�[�WID��ݒ肷��B<p>
     *
     * @param id ���O�̃��b�Z�[�WID
     */
    public void setHandlingErrorMessageId(String id);
    
    /**
     * �n���h�����O�̍ۂɃG���[���������A���g���C�񐔂��z��������ʒm���郍�O�̃��b�Z�[�WID��ݒ肷��B<p>
     *
     * @param id ���O�̃��b�Z�[�WID
     */
    public void setRetryOverErrorMessageId(String id);
    
    /**
     * �n���h�����O�̍ۂɃG���[�����������ꍇ�Ƀ��g���C����񐔂�ݒ肷��B<p>
     *
     * @param count ���g���C��
     */
    public void setMaxRetryCount(int count);
    
    /**
     * �n���h�����O�̍ۂɃG���[�����������ꍇ�Ƀ��g���C����Ԋu��ݒ肷��B<p>
     *
     * @param interval ���g���C�Ԋu
     */
    public void setRetryInterval(long interval);
    
    /**
     * �J�n����B<p>
     */
    public void start() throws Exception;
    
    /**
     * ��~����B<p>
     */
    public void stop();
    
    /**
     * �L���[��҂��󂯂�{@link QueueHandler}�X���b�h�̐����擾����B<p>
     *
     * @return QueueHandler�X���b�h�̐�
     */
    public int getQueueHandlerSize();
    
    /**
     * ���݃n���h�����O���̃X���b�h�����擾����B<p>
     *
     * @return ���݃n���h�����O���̃X���b�h��
     */
    public int getActiveQueueHandlerSize();
    
    /**
     * ���ݑҋ@���̃X���b�h�����擾����B<p>
     *
     * @return ���ݑҋ@���̃X���b�h��
     */
    public int getStandbyQueueHandlerSize();
    
    /**
     * �L���[���������������̏������Ԃ̕��ς��擾����B<p>
     *
     * @return ���Ϗ�������[ms]
     */
    public long getAverageHandleProcessTime();
}
