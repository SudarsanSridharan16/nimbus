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
package jp.ossc.nimbus.service.beancontrol.interfaces;

import jp.ossc.nimbus.service.beancontrol.BeanFlowMonitor;
import jp.ossc.nimbus.service.beancontrol.BeanFlowCoverage;
import jp.ossc.nimbus.service.beancontrol.BeanFlowAsynchInvokeCallback;

/**
 * Bean�t���[���s�C���^�t�F�[�X�B<p>
 * 
 * @author H.Nakano
 */
public interface BeanFlowInvoker{
    
    /**
     * Bean�t���[�����s����B<p>
     * 
     * @param obj Bean�t���[�ւ̈���
     * @return Bean�t���[�̎��s����
     * @exception Exception Bean�t���[�̎��s���ɗ�O�����������ꍇ
     */
    public Object invokeFlow(Object obj) throws Exception;
    
    /**
     * Bean�t���[�����s����B<p>
     * 
     * @param obj Bean�t���[�ւ̈���
     * @param monitor ���j�^�[
     * @return Bean�t���[�̎��s����
     * @exception Exception Bean�t���[�̎��s���ɗ�O�����������ꍇ
     */
    public Object invokeFlow(Object obj, BeanFlowMonitor monitor) throws Exception;
    
    /**
     * Bean�t���[��񓯊����s����B<p>
     * 
     * @param obj Bean�t���[�ւ̈���
     * @param monitor ���j�^�[
     * @param isReply �������K�v�ȏꍇ�́Atrue
     * @param maxAsynchWait �ő�񓯊����s�ҋ@��
     * @return Bean�t���[�̎��s�R���e�L�X�g
     * @exception Exception Bean�t���[�̔񓯊����s�����ŗ�O�����������ꍇ
     */
    public Object invokeAsynchFlow(Object obj, BeanFlowMonitor monitor, boolean isReply, int maxAsynchWait) throws Exception;
    
    /**
     * Bean�t���[�̔񓯊����s�̌��ʂ��擾����B<p>
     * 
     * @param context Bean�t���[�̎��s�R���e�L�X�g
     * @param monitor ���j�^�[
     * @param timeout �^�C���A�E�g[ms]�B-1���w�肵���ꍇ�́A�����҂�
     * @param isCancel �^�C���A�E�g���ɔ񓯊����s���L�����Z�����邩�ǂ����Btrue���w�肵���ꍇ�́A�L�����Z������
     * @return Bean�t���[�̔񓯊����s����
     * @exception BeanFlowAsynchTimeoutException �w�肳�ꂽ�^�C���A�E�g���߂��Ă��������Ȃ��ꍇ
     * @exception Exception Bean�t���[�̎��s���ɗ�O�����������ꍇ
     */
    public Object getAsynchReply(Object context, BeanFlowMonitor monitor, long timeout, boolean isCancel) throws BeanFlowAsynchTimeoutException, Exception;
    
    /**
     * Bean�t���[��񓯊����s����B<p>
     * 
     * @param obj Bean�t���[�ւ̈���
     * @param monitor ���j�^�[
     * @param callback �R�[���o�b�N
     * @param maxAsynchWait �ő�񓯊����s�ҋ@��
     * @return Bean�t���[�̎��s�R���e�L�X�g
     * @exception Exception Bean�t���[�̔񓯊����s�����ŗ�O�����������ꍇ
     */
    public Object invokeAsynchFlow(Object obj, BeanFlowMonitor monitor, BeanFlowAsynchInvokeCallback callback, int maxAsynchWait) throws Exception;
    
    /**
     * Bean�t���[�Ď��I�u�W�F�N�g�𐶐�����B<p>
     *
     * @return ����Bean�t���[���Ď�����I�u�W�F�N�g
     */
    public BeanFlowMonitor createMonitor();
    
    /**
     * Bean�t���[�̖��̂��擾����B<p>
     * 
     * @return Bean�t���[�̖���
     */
    public String getFlowName();
    
    /**
     * �㏑��Bean�t���[�����擾����B<p>
     *
     * @return �㏑��Bean�t���[���̔z��
     */
    public String[] getOverwrideFlowNames();
    
    /**
     * �J�o���b�W���擾����B<p>
     *
     * @return �J�o���b�W
     */
    public BeanFlowCoverage getBeanFlowCoverage();
    
    /**
     * Bean�t���[����`����Ă��郊�\�[�X�p�X���擾����B<p>
     *
     * @return ���\�[�X�p�X
     */
    public String getResourcePath();
    
    /**
     * �I������B<p>
     */
    public void end();
}
