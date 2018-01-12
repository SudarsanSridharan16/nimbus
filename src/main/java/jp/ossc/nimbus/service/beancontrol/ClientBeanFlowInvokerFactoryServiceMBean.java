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
package jp.ossc.nimbus.service.beancontrol;

import jp.ossc.nimbus.core.*;

/**
 * {@link ClientBeanFlowInvokerFactoryService}��MBean�C���^�t�F�[�X<p>
 * 
 * @author M.Takata
 * @see ClientBeanFlowInvokerFactoryService
 */
public interface ClientBeanFlowInvokerFactoryServiceMBean extends ServiceBaseMBean{
    
    public static final String MSG_ID_ASYNCH_INVOKE_ERROR = "CBFI_00001";
    
    /**
     * {@link jp.ossc.nimbus.service.keepalive.ClusterService �N���X�^}�T�[�r�X�̃T�[�r�X����ݒ肷��B<p>
     * {@link BeanFlowInvokerServer}���N���X�^�T�[�r�X�̃����o�[��񂩂�擾����B<br>
     *
     * @param name �N���X�^�T�[�r�X�̃T�[�r�X��
     */
    public void setClusterServiceName(ServiceName name);
    
    /**
     * {@link jp.ossc.nimbus.service.keepalive.ClusterService �N���X�^}�T�[�r�X�̃T�[�r�X�����擾����B<p>
     *
     * @return �N���X�^�T�[�r�X�̃T�[�r�X��
     */
    public ServiceName getClusterServiceName();
    
    /**
     * {@link jp.ossc.nimbus.service.keepalive.ClusterService �N���X�^}�T�[�r�X�̃����o�[���̃I�v�V�����L�[��ݒ肷��B<p>
     *
     * @param key �I�v�V�����L�[
     */
    public void setClusterOptionKey(String key);
    
    /**
     * {@link jp.ossc.nimbus.service.keepalive.ClusterService �N���X�^}�T�[�r�X�̃����o�[���̃I�v�V�����L�[���擾����B<p>
     *
     * @return �I�v�V�����L�[
     */
    public String getClusterOptionKey();
    
    /**
     * {@link jp.ossc.nimbus.service.beancontrol.BeanFlowInvokerServer#invokeFlow(Object, Object, Map)}�̑�R�����Ɏw�肷��R���e�L�X�g�����擾����{@link jp.ossc.nimbus.service.context.Context Context}�̃T�[�r�X����ݒ肷��B<p>
     *
     * @param name Context�T�[�r�X�̃T�[�r�X��
     */
    public void setContextServiceName(ServiceName name);
    
    /**
     * {@link jp.ossc.nimbus.service.beancontrol.BeanFlowInvokerServer#invokeFlow(Object, Object, Map)}�̑�R�����Ɏw�肷��R���e�L�X�g�����擾����{@link jp.ossc.nimbus.service.context.Context Context}�̃T�[�r�X�����擾����B<p>
     *
     * @return Context�T�[�r�X�̃T�[�r�X��
     */
    public ServiceName getContextServiceName();
    
    /**
     * {@link jp.ossc.nimbus.service.beancontrol.BeanFlowInvokerServer#invokeFlow(Object, Object, Map)}�̑�R�����Ɏw�肷��R���e�L�X�g���̃L�[��ݒ肷��B<p>
     * �w�肵�Ȃ��ꍇ�́A�S�ẴR���e�L�X�g���������n���B<br>
     *
     * @param keys �R���e�L�X�g���̃L�[
     */
    public void setContextKeys(String[] keys);
    
    /**
     * {@link jp.ossc.nimbus.service.beancontrol.BeanFlowInvokerServer#invokeFlow(Object, Object, Map)}�̑�R�����Ɏw�肷��R���e�L�X�g���̃L�[���擾����B<p>
     *
     * @return �R���e�L�X�g���̃L�[
     */
    public String[] getContextKeys();
    
    /**
     * {@link jp.ossc.nimbus.service.queue.QueueHandlerContainer QueueHandlerContainer}�T�[�r�X�̃T�[�r�X����ݒ肷��B<p>
     *
     * @param name QueueHandlerContainer�T�[�r�X�̃T�[�r�X��
     */
    public void setAsynchInvokeQueueHandlerContainerServiceName(ServiceName name);
    
    /**
     * {@link jp.ossc.nimbus.service.queue.QueueHandlerContainer QueueHandlerContainer}�T�[�r�X�̃T�[�r�X�����擾����B<p>
     *
     * @return QueueHandlerContainer�T�[�r�X�̃T�[�r�X��
     */
    public ServiceName getAsynchInvokeQueueHandlerContainerServiceName();
    
    /**
     * �񓯊����s�̗v���Ɏ��s�����ꍇ�ɏo�͂��郍�O���b�Z�[�WID��ݒ肷��B<br>
     * �f�t�H���g�́A{@link #MSG_ID_ASYNCH_INVOKE_ERROR}�B<br>
     *
     * @param id ���O���b�Z�[�WID
     */
    public void setAsynchInvokeErrorMessageId(String id);
    
    /**
     * �񓯊����s�̗v���Ɏ��s�����ꍇ�ɏo�͂��郍�O���b�Z�[�WID���擾����B<br>
     *
     * @return ���O���b�Z�[�WID
     */
    public String getAsynchInvokeErrorMessageId();
}
