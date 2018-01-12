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

import jp.ossc.nimbus.core.*;

/**
 * {@link DistributedQueueHandlerContainerService}��MBean�C���^�t�F�[�X<p>
 * 
 * @author M.Takata
 * @see DistributedQueueHandlerContainerService
 */
public interface DistributedQueueHandlerContainerServiceMBean extends ServiceBaseMBean{
    
    public static final String DEFAULT_HANDLING_ERROR_MESSAGE_ID = "QHC__00001";
    public static final String DEFAULT_RETRY_OVER_ERROR_MESSAGE_ID = "QHC__00002";
    
    public void setDistributedQueueSelectorServiceName(ServiceName name);
    public ServiceName getDistributedQueueSelectorServiceName();
    
    /**
     * {@link QueueHandler}�T�[�r�X�̃T�[�r�X����ݒ肷��B<p>
     *
     * @param name QueueHandler�T�[�r�X�̃T�[�r�X��
     */
    public void setQueueHandlerServiceName(ServiceName name);
    
    /**
     * {@link QueueHandler}�T�[�r�X�̃T�[�r�X�����擾����B<p>
     *
     * @return QueueHandler�T�[�r�X�̃T�[�r�X��
     */
    public ServiceName getQueueHandlerServiceName();
    
    /**
     * �T�[�r�X�̒�~����{@link Queue#release()}���Ăяo���AQueue���J�����邩�ǂ�����ݒ肷��B<p>
     * �f�t�H���g�́Atrue�B�P��Queue�𕡐��̃R���e�i�ŎQ�Ƃ���ꍇ�́Afalse�ɂ���ׂ��ł���B<br>
     *
     * @param isRelease �J������ꍇ��true
     */
    public void setReleaseQueue(boolean isRelease);
    
    /**
     * �T�[�r�X�̒�~����{@link Queue#release()}���Ăяo���AQueue���J�����邩�ǂ����𔻒肷��B<p>
     *
     * @return true�̏ꍇ�́A�J������
     */
    public boolean isReleaseQueue();
    
    /**
     * �L���[��҂��󂯂�ő厞��[ms]��ݒ肷��B<p>
     * �^�C���A�E�g�����ꍇ�́A{@link QueueHandler#handleDequeuedObject(Object)}��null��n���B<br>
     *
     * @param timeout �L���[��҂��󂯂�ő厞��[ms]
     */
    public void setWaitTimeout(long timeout);
    
    /**
     * �L���[��҂��󂯂�ő厞��[ms]���擾����B<p>
     *
     * @return �L���[��҂��󂯂�ő厞��[ms]
     */
    public long getWaitTimeout();
    
    /**
     * �n���h�����O�̍ۂɃG���[�����������ꍇ�Ƀ��g���C����񐔂�ݒ肷��B<p>
     * �f�t�H���g�́A0�Ń��g���C���Ȃ��B<br>
     *
     * @param count ���g���C��
     */
    public void setMaxRetryCount(int count);
    
    /**
     * �n���h�����O�̍ۂɃG���[�����������ꍇ�Ƀ��g���C����񐔂��擾����B<p>
     *
     * @return ���g���C��
     */
    public int getMaxRetryCount();
    
    /**
     * �n���h�����O�̍ۂɃG���[�����������ꍇ�Ƀ��g���C����Ԋu��ݒ肷��B<p>
     * �f�t�H���g�́A1000[ms]�B<br>
     *
     * @param interval ���g���C�Ԋu
     */
    public void setRetryInterval(long interval);
    
    /**
     * �n���h�����O�̍ۂɃG���[�����������ꍇ�Ƀ��g���C����Ԋu���擾����B<p>
     *
     * @return ���g���C�Ԋu
     */
    public long getRetryInterval();
    
    /**
     * �n���h�����O�̍ۂɃG���[��������������ʒm���郍�O�̃��b�Z�[�WID��ݒ肷��B<p>
     * �f�t�H���g�́A{@link #DEFAULT_HANDLING_ERROR_MESSAGE_ID}�B<br>
     *
     * @param id ���O�̃��b�Z�[�WID
     */
    public void setHandlingErrorMessageId(String id);
    
    /**
     * �n���h�����O�̍ۂɃG���[��������������ʒm���郍�O�̃��b�Z�[�WID���擾����B<p>
     *
     * @return ���O�̃��b�Z�[�WID
     */
    public String getHandlingErrorMessageId();
    
    /**
     * �n���h�����O�̍ۂɃG���[���������A���g���C�񐔂��z��������ʒm���郍�O�̃��b�Z�[�WID��ݒ肷��B<p>
     * �f�t�H���g�́A{@link #DEFAULT_RETRY_OVER_ERROR_MESSAGE_ID}�B<br>
     *
     * @param id ���O�̃��b�Z�[�WID
     */
    public void setRetryOverErrorMessageId(String id);
    
    /**
     * �n���h�����O�̍ۂɃG���[���������A���g���C�񐔂��z��������ʒm���郍�O�̃��b�Z�[�WID���擾����B<p>
     *
     * @return ���O�̃��b�Z�[�WID
     */
    public String getRetryOverErrorMessageId();
    
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
     * �L���[��҂��󂯂�{@link QueueHandler}�X���b�h���f�[�����X���b�h�ɂ��邩�ǂ�����ݒ肷��B<p>
     * �f�t�H���g�́Atrue�B<br>
     *
     * @param isDaemon �f�[�����X���b�h�ɂ���ꍇ�́Atrue
     */
    public void setDaemonQueueHandler(boolean isDaemon);
    
    /**
     * �L���[��҂��󂯂�{@link QueueHandler}�X���b�h���f�[�����X���b�h�ɂ��邩�ǂ����𔻒肷��B<p>
     *
     * @return true�̏ꍇ�A�f�[�����X���b�h�ɂ���
     */
    public boolean isDaemonQueueHandler();
    
    /**
     * �L���[��҂��󂯂�{@link QueueHandler}�X���b�h�̗D�揇�ʂ�ݒ肷��B<p>
     * �f�t�H���g�́A-1�Őݒ肵�Ȃ��B<br>
     *
     * @param newPriority �X���b�h�̗D�揇��
     */
    public void setQueueHandlerThreadPriority(int newPriority);
    
    /**
     * �L���[��҂��󂯂�{@link QueueHandler}�X���b�h�̗D�揇�ʂ��擾����B<p>
     *
     * @return �X���b�h�̗D�揇��
     */
    public int getQueueHandlerThreadPriority();
    
    /**
     * {@link Queue}����擾�����v�f��null�̏ꍇ�ɖ������邩�ǂ�����ݒ肷��B<p>
     * �f�t�H���g�́Afalse�Ŗ������Ȃ��B<br>
     *
     * @param isIgnore ��������ꍇtrue
     */
    public void setIgnoreNullElement(boolean isIgnore);
    
    /**
     * {@link Queue}����擾�����v�f��null�̏ꍇ�ɖ������邩�ǂ����𔻒肷��B<p>
     *
     * @return true�̏ꍇ�A��������
     */
    public boolean isIgnoreNullElement();
    
    /**
     * �L���[������������B<p>
     */
    public void clear();
    
    /**
     * �L���[�T�C�Y���擾����B<p>
     * 
     * @return �L���[�i�[����
     */
    public int size();
    
    /**
     * �L���[�ɓ������ꂽ�������擾����B<p>
     *
     * @return �L���[��������
     */
    public long getCount();
    
    /**
     * ���݂̃L���[�̐[�����擾����B<p>
     *
     * @return ���݂̃L���[�̐[��
     */
    public long getDepth();
    
    /**
     * �L���[���������������̏������Ԃ̕��ς��擾����B<p>
     *
     * @return ���Ϗ�������[ms]
     */
    public long getAverageHandleProcessTime();
    
    /**
     * �ĊJ����B<p>
     */
    public void resume();
    
    /**
     * ���f����B<p>
     */
    public void suspend();
    
    /**
     * ���f���Ă��邩�ǂ����𔻒肷��B<p>
     *
     * @return ���f���Ă���ꍇ�Atrue
     */
    public boolean isSuspend();
}