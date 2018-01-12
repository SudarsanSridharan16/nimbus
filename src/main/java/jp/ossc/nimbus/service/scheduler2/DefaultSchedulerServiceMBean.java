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
 * {@link DefaultSchedulerService}��MBean�C���^�t�F�[�X�B<p>
 * 
 * @author M.Takata
 */
public interface DefaultSchedulerServiceMBean
 extends AbstractSchedulerServiceMBean{
    
    /**
     * �X�P�W���[���𓊓�����{@link jp.ossc.nimbus.service.queue.Queue Queue}�T�[�r�X�̃T�[�r�X����ݒ肷��B<p>
     *
     * @param name Queue�T�[�r�X�̃T�[�r�X��
     */
    public void setQueueServiceName(ServiceName name);
    
    /**
     * �X�P�W���[���𓊓�����{@link jp.ossc.nimbus.service.queue.Queue Queue}�T�[�r�X�̃T�[�r�X�����擾����B<p>
     *
     * @return Queue�T�[�r�X�̃T�[�r�X��
     */
    public ServiceName getQueueServiceName();
    
    /**
     * �L���[��҂��󂯂�X�P�W���[���f�B�X�p�b�`�X���b�h�̐���ݒ肷��B<p>
     * �f�t�H���g�́A1�B<br>
     *
     * @param size �X�P�W���[���f�B�X�p�b�`�X���b�h�̐�
     */
    public void setScheduleDispatcherSize(int size);
    
    /**
     * �L���[��҂��󂯂�X�P�W���[���f�B�X�p�b�`�X���b�h�̐����擾����B<p>
     *
     * @return �X�P�W���[���f�B�X�p�b�`�X���b�h�̐�
     */
    public int getScheduleDispatcherSize();
    
    /**
     * �L���[��҂��󂯂�X�P�W���[���f�B�X�p�b�`�X���b�h���f�[�����X���b�h�ɂ��邩�ǂ�����ݒ肷��B<p>
     * �f�t�H���g�́Atrue�B<br>
     *
     * @param isDaemon �f�[�����X���b�h�ɂ���ꍇ�́Atrue
     */
    public void setDaemonScheduleDispatcher(boolean isDaemon);
    
    /**
     * �L���[��҂��󂯂�X�P�W���[���f�B�X�p�b�`�X���b�h���f�[�����X���b�h�ɂ��邩�ǂ����𔻒肷��B<p>
     *
     * @return true�̏ꍇ�A�f�[�����X���b�h�ɂ���
     */
    public boolean isDaemonScheduleDispatcher();
    
    /**
     * �T�[�r�X�̒�~���ɃX�P�W���[���f�B�X�p�b�`�X���b�h�̏I����ҋ@����^�C���A�E�g[ms]��ݒ肷��B<p>
     * �^�C���A�E�g�����ꍇ�́A����ȍ~�̃X���b�h�̏I���́A{@link jp.ossc.nimbus.daemon.Daemon#stopNoWait()}�����s����B<br>
     * �f�t�H���g��-1�ŁA�S�ẴX���b�h�ɑ΂���{@link jp.ossc.nimbus.daemon.Daemon#stop()}�����s����B<br>
     *
     * @param timeout �^�C���A�E�g[ms]
     */
    public void setStopWaitTimeout(long timeout);
    
    /**
     * �T�[�r�X�̒�~���ɃX�P�W���[���f�B�X�p�b�`�X���b�h�̏I����ҋ@����^�C���A�E�g[ms]���擾����B<p>
     *
     * @return �^�C���A�E�g[ms]
     */
    public long getStopWaitTimeout();
    
    /**
     * ���݃X�P�W���[�����s���̃X���b�h�����擾����B<p>
     *
     * @return ���݃X�P�W���[�����s���̃X���b�h��
     */
    public int getActiveScheduleDispatcherSize();
}