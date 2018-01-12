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

import java.util.Date;
import java.util.Set;

import jp.ossc.nimbus.core.*;

/**
 * {@link BeanFlowInvokerServerService}��MBean�C���^�t�F�[�X<p>
 * 
 * @author M.Takata
 * @see BeanFlowInvokerServerService
 */
public interface BeanFlowInvokerServerServiceMBean extends ServiceBaseMBean{
    
    public static final String DEFAULT_JNDI_NAME = "nimbus/BeanFlowInvokerServer";
    
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
     * {@link jp.ossc.nimbus.service.beancontrol.interfaces.BeanFlowInvoker#invokeFlow(Object, BeanFlowMonitor)}�̌Ăяo���ɋ��ݍ���{@link jp.ossc.nimbus.service.aop.InterceptorChain InterceptorChain}�𐶐�����{@link jp.ossc.nimbus.service.aop.InterceptorChainFactory InterceptorChainFactory}�̃T�[�r�X����ݒ肷��B<p>
     *
     * @param name InterceptorChainFactory�T�[�r�X�̃T�[�r�X��
     */
    public void setInterceptorChainFactoryServiceName(ServiceName name);
    
    /**
     * {@link jp.ossc.nimbus.service.beancontrol.interfaces.BeanFlowInvoker#invokeFlow(Object, BeanFlowMonitor)}�̌Ăяo���ɋ��ݍ���{@link jp.ossc.nimbus.service.aop.InterceptorChain InterceptorChain}�𐶐�����{@link jp.ossc.nimbus.service.aop.InterceptorChainFactory InterceptorChainFactory}�̃T�[�r�X�����擾����B<p>
     *
     * @return InterceptorChainFactory�T�[�r�X�̃T�[�r�X��
     */
    public ServiceName getInterceptorChainFactoryServiceName();
    
    /**
     * {@link jp.ossc.nimbus.service.beancontrol.BeanFlowInvokerServer#invokeFlow(Object, Object, Map)}�̑�R�����Ŏw�肳�ꂽ�R���e�L�X�g������肱��{@link jp.ossc.nimbus.service.context.Context Context}�̃T�[�r�X����ݒ肷��B<p>
     *
     * @param name Context�T�[�r�X�̃T�[�r�X��
     */
    public void setContextServiceName(ServiceName name);
    
    /**
     * {@link jp.ossc.nimbus.service.beancontrol.BeanFlowInvokerServer#invokeFlow(Object, Object, Map)}�̑�R�����Ŏw�肳�ꂽ�R���e�L�X�g������肱��{@link jp.ossc.nimbus.service.context.Context Context}�̃T�[�r�X�����擾����B<p>
     *
     * @return Context�T�[�r�X�̃T�[�r�X��
     */
    public ServiceName getContextServiceName();
    
    /**
     * {@link jp.ossc.nimbus.service.performance.ResourceUsage}�T�[�r�X�̃T�[�r�X����ݒ肷��B<p>
     * �ݒ肵�Ȃ��ꍇ�́A{@link jp.ossc.nimbus.service.beancontrol.BeanFlowInvokerServer#getResourceUsage() getResourceUsage()}�̖߂�l�́A{@link jp.ossc.nimbus.service.beancontrol.BeanFlowInvokerServer#getCurrentFlowCount() getCurrentFlowCount()}�Ɠ����B<br>
     *
     * @param name ResourceUsage�T�[�r�X�̃T�[�r�X��
     */
    public void setResourceUsageServiceName(ServiceName name);
    
    /**
     * {@link jp.ossc.nimbus.service.performance.ResourceUsage}�T�[�r�X�̃T�[�r�X�����擾����B<p>
     *
     * @return ResourceUsage�T�[�r�X�̃T�[�r�X��
     */
    public ServiceName getResourceUsageServiceName();
    
    /**
     * {@link BeanFlowInvokerServer}��JNDI�Ƀo�C���h����ۂ�JNDI����ݒ肷��B<p>
     * �f�t�H���g�́A{@link #DEFAULT_JNDI_NAME}�B<br>
     *
     * @param name BeanFlowInvokerServer��JNDI�Ƀo�C���h����ۂ�JNDI��
     */
    public void setJndiName(String name);
    
    /**
     * {@link BeanFlowInvokerServer}��JNDI�Ƀo�C���h����ۂ�JNDI�����擾����B<p>
     *
     * @return BeanFlowInvokerServer��JNDI�Ƀo�C���h����ۂ�JNDI��
     */
    public String getJndiName();
    
    /**
     * {@link BeanFlowInvokerServer}��JNDI�Ƀo�C���h����{@link jp.ossc.nimbus.service.repository.Repository Repository}�T�[�r�X�̃T�[�r�X����ݒ肷��B<p>
     *
     * @param name Repository�T�[�r�X�̃T�[�r�X��
     */
    public void setJndiRepositoryServiceName(ServiceName name);
    
    /**
     * {@link BeanFlowInvokerServer}��JNDI�Ƀo�C���h����{@link jp.ossc.nimbus.service.repository.Repository Repository}�T�[�r�X�̃T�[�r�X�����擾����B<p>
     *
     * @return Repository�T�[�r�X�̃T�[�r�X��
     */
    public ServiceName getJndiRepositoryServiceName();
    
    /**
     * {@link BeanFlowInvokerServer}�ɑ΂���RMI�Ăяo�������鎞�̃|�[�g�ԍ���ݒ肷��B<p>
     * �f�t�H���g�́A0�ŔC�ӂ̃|�[�g���g�p�����B<br>
     *
     * @param port �|�[�g�ԍ�
     */
    public void setRMIPort(int port);
    
    /**
     * {@link BeanFlowInvokerServer}�ɑ΂���RMI�Ăяo�������鎞�̃|�[�g�ԍ����擾����B<p>
     *
     * @return �|�[�g�ԍ�
     */
    public int getRMIPort();
    
    /**
     * {@link jp.ossc.nimbus.service.keepalive.ClusterService �N���X�^}�T�[�r�X�̃T�[�r�X����ݒ肷��B<p>
     * ���̑�����ݒ肵���ꍇ�́A�N���X�^�T�[�r�X�̃����o�[���̃I�v�V������{@link jp.ossc.nimbus.service.proxy.invoker.KeepAliveCheckInvoker KeepAliveCheckInvoker}��ݒ肷�鎖�ŁA�N���X�^�T�[�r�X�o�R�ł̃����[�g�Ăяo�����T�|�[�g����B<br>
     * �N���X�^�T�[�r�X�̃N���X�^�ւ̎Q���́A���̃T�[�r�X�̏�ԂƘA������K�v�����邽�߁A{@link jp.ossc.nimbus.service.keepalive.ClusterService#setJoinOnStart(boolean) ClusterService.setJoinOnStart(false)}�ɂ��Ă����K�v������B<br>
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
     * {@link jp.ossc.nimbus.service.keepalive.ClusterService �N���X�^}�T�[�r�X�̃N���X�^�Q���𐧌䂷�邩�ǂ�����ݒ肷��B<p>
     * �f�t�H���g�́Atrue�Ő��䂷��B<br>
     *
     * @param isJoin ���䂷��ꍇ�Atrue
     */
    public void setClusterJoin(boolean isJoin);
    
    /**
     * {@link jp.ossc.nimbus.service.keepalive.ClusterService �N���X�^}�T�[�r�X�̃N���X�^�Q���𐧌䂷�邩�ǂ����𔻒肷��B<p>
     *
     * @return true�̏ꍇ�A���䂷��
     */
    public boolean isClusterJoin();
    
    /**
     * {@link BeanFlowInvokerServer#createFlow(String, String, boolean)}�Ő����������sID�Ɋ܂܂��^�C���X�^���v�t�H�[�}�b�g��ݒ肷��B<p>
     * �f�t�H���g�́A"HHmmssSSS"�Ȃ̂ŁA24���Ԉȏ㑱�����������݂���ꍇ�́A���sID�̈�Ӑ����ۂ���Ȃ��Ȃ�B<br>
     *
     * @param format �^�C���X�^���v�t�H�[�}�b�g
     */
    public void setSequenceTimestampFormat(String format);
    
    /**
     * {@link BeanFlowInvokerServer#createFlow(String, String, boolean)}�Ő����������sID�Ɋ܂܂��P�ʎ��Ԃ̃^�C���X�^���v�t�H�[�}�b�g���擾����B<p>
     *
     * @return �^�C���X�^���v�t�H�[�}�b�g
     */
    public String getSequenceTimestampFormat();
    
    /**
     * {@link BeanFlowInvokerServer#createFlow(String, String, boolean)}�Ő����������sID�Ɋ܂܂��P�ʎ��Ԃ�����̒ʔԌ�����ݒ肷��B<p>
     * �f�t�H���g�́A�P�ʎ��Ԃ��~���b�ŁA�ʔԌ�����3�Ȃ̂ŁA1�~���b������999���ȏ�̏�������������ꍇ�́A���sID�̈�Ӑ����ۂ���Ȃ��Ȃ�B<br>
     *
     * @param digit �ʔԌ���
     */
    public void setSequenceDigit(int digit);
    
    /**
     * {@link BeanFlowInvokerServer#createFlow(String, String, boolean)}�Ő����������sID�Ɋ܂܂��P�ʎ��Ԃ�����̒ʔԌ������擾����B<p>
     *
     * @return �ʔԌ���
     */
    public int getSequenceDigit();
    
    /**
     * ���̃T�[�o�����N�G�X�g��t�\���𔻒肷��B<p>
     *
     * @return ���N�G�X�g��t�\�ȏꍇtrue
     */
    public boolean isAcceptable();
    
    /**
     * ���̃T�[�o�����N�G�X�g��t�\����ݒ肷��B<p>
     *
     * @param isAcceptable true�̏ꍇ�A���N�G�X�g��t�\
     */
    public void setAcceptable(boolean isAcceptable);
    
    /**
     * ���ݐ�������Ă���BeanFlow�̐����擾����B<p>
     *
     * @return ���ݐ�������Ă���BeanFlow�̐�
     */
    public int getCurrentFlowCount();
    
    /**
     * ���ݐ�������Ă���BeanFlow�̎��sID�̏W�����擾����B<p>
     *
     * @return ���sID�̏W��
     */
    public Set getCurrentFlowIdSet();
    
    /**
     * �w�肳�ꂽ���sID��BeanFlow�̎��s�J�n�������擾����B<p>
     *
     * @return ���s�J�n����
     */
    public Date getFlowStartTime(String id);
    
    /**
     * �w�肳�ꂽ���sID��BeanFlow�̏�������[ms]���擾����B<p>
     *
     * @return ��������
     */
    public long getFlowCurrentProcessTime(String id);
    
    /**
     * java.rmi.server.RMIClientSocketFactory�T�[�r�X�̃T�[�r�X����ݒ肷��B<p>
     *
     * @param name RMIClientSocketFactory�T�[�r�X�̃T�[�r�X��
     */
    public void setRMIClientSocketFactoryServiceName(ServiceName name);
    
    /**
     * java.rmi.server.RMIClientSocketFactory�T�[�r�X�̃T�[�r�X�����擾����B<p>
     *
     * @return RMIClientSocketFactory�T�[�r�X�̃T�[�r�X��
     */
    public ServiceName getRMIClientSocketFactoryServiceName();
    
    /**
     * java.rmi.server.RMIServerSocketFactory�T�[�r�X�̃T�[�r�X����ݒ肷��B<p>
     *
     * @param name RMIServerSocketFactory�T�[�r�X�̃T�[�r�X��
     */
    public void setRMIServerSocketFactoryServiceName(ServiceName name);
    
    /**
     * java.rmi.server.RMIServerSocketFactory�T�[�r�X�̃T�[�r�X�����擾����B<p>
     *
     * @return RMIServerSocketFactory�T�[�r�X�̃T�[�r�X��
     */
    public ServiceName getRMIServerSocketFactoryServiceName();
    
    /**
     * ���\�[�X�̎g�p�ʂ��擾����B<p>
     *
     * @return ���\�[�X�̎g�p��
     */
    public Comparable getResourceUsage();
}
