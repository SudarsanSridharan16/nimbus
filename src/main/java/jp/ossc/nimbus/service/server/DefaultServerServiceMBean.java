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
 * {@link DefaultServerService}��MBean�C���^�t�F�[�X�B<p>
 * 
 * @author M.Takata
 * @see DefaultServerService
 */
public interface DefaultServerServiceMBean extends ServiceBaseMBean{
    
    /**
     * �T�[�o���o�C���h����z�X�g����ݒ肷��B<p>
     * �ݒ肵�Ȃ��ꍇ�́A���[�J���z�X�g�Ƀo�C���h����B
     *
     * @param name �z�X�g��
     */
    public void setHostName(String name);
    
    /**
     * �T�[�o���o�C���h����z�X�g�����擾����B<p>
     *
     * @return �z�X�g��
     */
    public String getHostName();
    
    /**
     * �T�[�o���o�C���h����|�[�g�ԍ���ݒ肷��B<p>
     * �f�t�H���g�́A10000�B
     *
     * @param port �|�[�g�ԍ�
     */
    public void setPort(int port);
    
    /**
     * �T�[�o���o�C���h����|�[�g�ԍ����擾����B<p>
     *
     * @return �|�[�g�ԍ�
     */
    public int getPort();
    
    /**
     * SO_REUSEADDR �\�P�b�g�I�v�V������L���܂��͖����ɂ���B<p>
     * �f�t�H���g�́Atrue�ŗL���B
     *
     * @param isReuse �L���ɂ���ꍇtrue
     */
    public void setReuseAddress(boolean isReuse);
    
    /**
     * SO_REUSEADDR �\�P�b�g�I�v�V�������L�������肷��B<p>
     *
     * @return true�̏ꍇ�A�L��
     */
    public boolean isReuseAddress();
    
    /**
     * ��M�o�b�t�@�o�C�g����ݒ肷��B<p>
     *
     * @param size ��M�o�b�t�@�o�C�g��
     */
    public void setReceiveBufferSize(int size);
    
    /**
     * ��M�o�b�t�@�o�C�g�����擾����B<p>
     *
     * @return ��M�o�b�t�@�o�C�g��
     */
    public int getReceiveBufferSize();
    
    /**
     * ��M�^�C���A�E�g[ms]��ݒ肷��B<p>
     *
     * @param timeout ��M�^�C���A�E�g
     */
    public void setSoTimeout(int timeout);
    
    /**
     * ��M�^�C���A�E�g[ms]���擾����B<p>
     *
     * @return ��M�^�C���A�E�g
     */
    public int getSoTimeout();
    
    /**
     * {@link jp.ossc.nimbus.service.queue.QueueHandlerContainer QueueHandlerContainer}�T�[�r�X�̃T�[�r�X����ݒ肷��B<p>
     *
     * @param name QueueHandlerContainer�T�[�r�X�̃T�[�r�X��
     */
    public void setQueueHandlerContainerServiceName(ServiceName name);
    
    /**
     * {@link jp.ossc.nimbus.service.queue.QueueHandlerContainer QueueHandlerContainer}�T�[�r�X�̃T�[�r�X�����擾����B<p>
     *
     * @return QueueHandlerContainer�T�[�r�X�̃T�[�r�X��
     */
    public ServiceName getQueueHandlerContainerServiceName();
    
    /**
     * ���N�G�X�gID�𔭍s����{@link jp.ossc.nimbus.service.sequence.Sequence Sequence}�T�[�r�X�̃T�[�r�X����ݒ肷��B<p>
     *
     * @param name Sequence�T�[�r�X�̃T�[�r�X��
     */
    public void setSequenceServiceName(ServiceName name);
    
    /**
     * ���N�G�X�gID�𔭍s����{@link jp.ossc.nimbus.service.sequence.Sequence Sequence}�T�[�r�X�̃T�[�r�X�����擾����B<p>
     *
     * @return Sequence�T�[�r�X�̃T�[�r�X��
     */
    public ServiceName getSequenceServiceName();
    
    /**
     * �T�[�o�\�P�b�g�t�@�N�g���T�[�r�X�̃T�[�r�X����ݒ肷��B<br>
     *
     * @param name �T�[�o�\�P�b�g�t�@�N�g���T�[�r�X�̃T�[�r�X��
     */
    public void setServerSocketFactoryServiceName(ServiceName name);
    
    /**
     * �T�[�o�\�P�b�g�t�@�N�g���T�[�r�X�̃T�[�r�X�����擾����B<br>
     *
     * @return �T�[�o�\�P�b�g�t�@�N�g���T�[�r�X�̃T�[�r�X��
     */
    public ServiceName getServerSocketFactoryServiceName();
    
    /**
     * �\�P�b�g�t�@�N�g���T�[�r�X�̃T�[�r�X����ݒ肷��B<br>
     *
     * @param name �\�P�b�g�t�@�N�g���T�[�r�X�̃T�[�r�X��
     */
    public void setSocketFactoryServiceName(ServiceName name);
    
    /**
     * �\�P�b�g�t�@�N�g���T�[�r�X�̃T�[�r�X�����擾����B<br>
     *
     * @return �\�P�b�g�t�@�N�g���T�[�r�X�̃T�[�r�X��
     */
    public ServiceName getSocketFactoryServiceName();
    
    /**
     * �\�P�b�g��t���̏������s�����ǂ�����ݒ肷��B<p>
     * �f�t�H���g�́Afalse�ŁA�\�P�b�g��t��A�N���C�A���g����̏������݂�҂B<br>
     *
     * @param isHandle �������s���ꍇ�Atrue
     */
    public void setHandleAccept(boolean isHandle);
    
    /**
     * �\�P�b�g��t���̏������s�����ǂ����𔻒肷��B<p>
     *
     * @return true�̏ꍇ�A�������s��
     */
    public boolean isHandleAccept();
}
