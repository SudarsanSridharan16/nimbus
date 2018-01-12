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
 * {@link BeanFlowScheduleExecutorService}��MBean�C���^�t�F�[�X�B<p>
 * 
 * @author M.Takata
 */
public interface BeanFlowScheduleExecutorServiceMBean extends AbstractScheduleExecutorServiceMBean{
    
    /**
     * �f�t�H���g�̃X�P�W���[�����s��ʁB<p>
     */
    public static final String DEFAULT_EXECUTOR_TYPE = "BEANFLOW";
    
    /**
     * �X�P�W���[�����ꎞ��~�����ꍇ�̃��O���b�Z�[�WID�B<p>
     */
    public static final String MSG_ID_PAUSE = "BFSE_00001";
    
    /**
     * �X�P�W���[�����ĊJ�����ꍇ�̃��O���b�Z�[�WID�B<p>
     */
    public static final String MSG_ID_RESUME = "BFSE_00002";
    
    /**
     * {@link jp.ossc.nimbus.service.beancontrol.interfaces.BeanFlowInvokerFactory BeanFlowInvokerFactory}�T�[�r�X�̃T�[�r�X����ݒ肷��B<p>
     *
     * @param name BeanFlowInvokerFactory�T�[�r�X�̃T�[�r�X��
     */
    public void setBeanFlowInvokerFactoryServiceName(ServiceName name);
    
    /**
     * {@link jp.ossc.nimbus.service.beancontrol.interfaces.BeanFlowInvokerFactory BeanFlowInvokerFactory}�T�[�r�X�̃T�[�r�X�����擾����B<p>
     *
     * @return BeanFlowInvokerFactory�T�[�r�X�̃T�[�r�X��
     */
    public ServiceName getBeanFlowInvokerFactoryServiceName();
    
    /**
     * �X�P�W���[���̐����Ԃ̕ύX�����ۂɌ��������ǂ������m�F����Ԋu[ms]��ݒ肷��B<p>
     * �f�t�H���g�́A500[ms]�B<br>
     *
     * @param interval �Ԋu[ms]
     */
    public void setControlStateChangingWaitInterval(long interval);
    
    /**
     * �X�P�W���[���̐����Ԃ̕ύX�����ۂɌ��������ǂ������m�F����Ԋu[ms]���擾����B<p>
     *
     * @return �Ԋu[ms]
     */
    public long getControlStateChangingWaitInterval();
    
    /**
     * �X�P�W���[���̐����Ԃ̕ύX�����ۂɌ��������ǂ������m�F�҂����鎞��[ms]��ݒ肷��B<p>
     * �f�t�H���g�́A-1�Ŗ����҂��B<br>
     *
     * @param timeout �^�C���A�E�g[ms]
     */
    public void setControlStateChangingWaitTimeout(long timeout);
    
    /**
     * �X�P�W���[���̐����Ԃ̕ύX�����ۂɌ��������ǂ������m�F�҂����鎞��[ms]���擾����B<p>
     *
     * @return �^�C���A�E�g[ms]
     */
    public long getControlStateChangingWaitTimeout();
}
