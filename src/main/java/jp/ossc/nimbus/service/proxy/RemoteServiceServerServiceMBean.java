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
package jp.ossc.nimbus.service.proxy;

import jp.ossc.nimbus.core.*;

/**
 * {@link RemoteServiceServerService}��MBean�C���^�t�F�[�X<p>
 * 
 * @author M.Takata
 * @see RemoteServiceServerService
 */
public interface RemoteServiceServerServiceMBean extends ServiceBaseMBean{
    
    /**
     * �����[�g�Ăяo�������T�[�r�X�̃T�[�r�X����ݒ肷��B<p>
     * �����Ŏw�肳�ꂽ�T�[�r�X���́A{@link jp.ossc.nimbus.service.aop.InvocationContext#getTargetObject() InvocationContext.getTargetObject()}�ŁA�Ăяo���Ώۂ̃T�[�r�X���w�肳��Ă���ꍇ�́A���̃T�[�r�X���ƈ�v���邩�ǂ����̃`�F�b�N�ɗp�����A��v���Ȃ��ꍇ�́AIllegalAccessException��throw����B<br>
     * �܂��AInvocationContext.getTargetObject()�ŁA�Ăяo���Ώۂ̃T�[�r�X���w�肳��Ă��Ȃ��ꍇ�́A�����Ŏw�肳�ꂽ�T�[�r�X���Ăяo���B<br>
     * 
     * @param name �����[�g�Ăяo�������T�[�r�X�̃T�[�r�X��
     */
    public void setRemoteServiceName(ServiceName name);
    
    /**
     * �����[�g�Ăяo�������T�[�r�X�̃T�[�r�X�����擾����B<p>
     * 
     * @return �����[�g�Ăяo�������T�[�r�X�̃T�[�r�X��
     */
    public ServiceName getRemoteServiceName();
    
    /**
     * �����[�g�Ăяo�������T�[�r�X�ɋ��ݍ���{@link jp.ossc.nimbus.service.aop.InterceptorChainList InterceptorChainList}�T�[�r�X�̃T�[�r�X����ݒ肷��B<p>
     * �C���^�[�Z�v�^�����ݍ��܂Ȃ��ꍇ�́A�ݒ肵�Ȃ��Ă��ǂ��B<br>
     *
     * @param name InterceptorChainList�T�[�r�X�̃T�[�r�X��
     */
    public void setInterceptorChainListServiceName(ServiceName name);
    
    /**
     * �����[�g�Ăяo�������T�[�r�X�ɋ��ݍ���{@link jp.ossc.nimbus.service.aop.InterceptorChainList InterceptorChainList}�T�[�r�X�̃T�[�r�X�����擾����B<p>
     *
     * @return InterceptorChainList�T�[�r�X�̃T�[�r�X��
     */
    public ServiceName getInterceptorChainListServiceName();
    
    /**
     * �����[�g�Ăяo�������T�[�r�X���Ăяo��{@link jp.ossc.nimbus.service.aop.Invoker Invoker}�T�[�r�X�̃T�[�r�X����ݒ肷��B<p>
     * �f�t�H���g�́A{@link jp.ossc.nimbus.service.aop.invoker.MethodReflectionCallInvokerService MethodReflectionCallInvokerService}�T�[�r�X�������Ő�������g�p�����B<br>
     *
     * @param name Invoker�T�[�r�X�̃T�[�r�X��
     */
    public void setInvokerServiceName(ServiceName name);
    
    /**
     * �����[�g�Ăяo�������T�[�r�X���Ăяo��{@link jp.ossc.nimbus.service.aop.Invoker Invoker}�T�[�r�X�̃T�[�r�X�����擾����B<p>
     *
     * @return Invoker�T�[�r�X�̃T�[�r�X��
     */
    public ServiceName getInvokerServiceName();
    
    /**
     * �����[�g�Ăяo�������T�[�r�X�ɋ��ݍ���{@link jp.ossc.nimbus.service.aop.InterceptorChain InterceptorChain}�𐶐�����{@link jp.ossc.nimbus.service.aop.InterceptorChainFactory InterceptorChainFactory}�̃T�[�r�X����ݒ肷��B<p>
     *
     * @param name InterceptorChainFactory�T�[�r�X�̃T�[�r�X��
     */
    public void setInterceptorChainFactoryServiceName(ServiceName name);
    
    /**
     * �����[�g�Ăяo�������T�[�r�X�ɋ��ݍ���{@link jp.ossc.nimbus.service.aop.InterceptorChain InterceptorChain}�𐶐�����{@link jp.ossc.nimbus.service.aop.InterceptorChainFactory InterceptorChainFactory}�̃T�[�r�X�����擾����B<p>
     *
     * @return InterceptorChainFactory�T�[�r�X�̃T�[�r�X��
     */
    public ServiceName getInterceptorChainFactoryServiceName();
    
    /**
     * {@link RemoteServerInvoker}��JNDI�Ƀo�C���h����ۂ�JNDI����ݒ肷��B<p>
     * ���̑�����ݒ肵�ĂȂ��A{@link #setRemoteServiceName(ServiceName)}���ݒ肳��Ă���ꍇ�́A�����Őݒ肳�ꂽ�����[�g�Ăяo�������T�[�r�X��{@link ServiceName}����A"�}�l�[�W����/�T�[�r�X��"�Ƃ���JNDI�����K�p�����B<br>
     * �ǂ�����ݒ肳��Ă��Ȃ��ꍇ�́A�T�[�r�X�̊J�n�ŗ�O����������B<br>
     *
     * @param name RemoteServerInvoker��JNDI�Ƀo�C���h����ۂ�JNDI��
     */
    public void setJndiName(String name);
    
    /**
     * {@link RemoteServerInvoker}��JNDI�Ƀo�C���h����ۂ�JNDI�����擾����B<p>
     *
     * @return RemoteServerInvoker��JNDI�Ƀo�C���h����ۂ�JNDI��
     */
    public String getJndiName();
    
    /**
     * {@link RemoteServerInvoker}��JNDI�Ƀo�C���h����{@link jp.ossc.nimbus.service.repository.Repository Repository}�T�[�r�X�̃T�[�r�X����ݒ肷��B<p>
     *
     * @param name Repository�T�[�r�X�̃T�[�r�X��
     */
    public void setJndiRepositoryServiceName(ServiceName name);
    
    /**
     * {@link RemoteServerInvoker}��JNDI�Ƀo�C���h����{@link jp.ossc.nimbus.service.repository.Repository Repository}�T�[�r�X�̃T�[�r�X�����擾����B<p>
     *
     * @return Repository�T�[�r�X�̃T�[�r�X��
     */
    public ServiceName getJndiRepositoryServiceName();
    
    /**
     * {@link RemoteServerInvoker}�ɑ΂���RMI�Ăяo�������鎞�̃|�[�g�ԍ���ݒ肷��B<p>
     * �f�t�H���g�́A0�ŔC�ӂ̃|�[�g���g�p�����B<br>
     *
     * @param port �|�[�g�ԍ�
     */
    public void setRMIPort(int port);
    
    /**
     * {@link RemoteServerInvoker}�ɑ΂���RMI�Ăяo�������鎞�̃|�[�g�ԍ����擾����B<p>
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
     * {@link jp.ossc.nimbus.service.performance.ResourceUsage}�T�[�r�X�̃T�[�r�X����ݒ肷��B<p>
     * �ݒ肵�Ȃ��ꍇ�́A{@link RemoteServerInvoker#getResourceUsage()}�̖߂�l�́Anull�B<br>
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
     * �T�[�r�X�̃����[�g�Ăяo��������鎞�́A�����y�і߂�l�𒼗񉻂���{@link jp.ossc.nimbus.service.io.Externalizer Externalizer}�T�[�r�X�̃T�[�r�X����ݒ肷��B<p>
     *
     * @param name Externalizer�T�[�r�X�̃T�[�r�X��
     */
    public void setExternalizerServiceName(ServiceName name);
    
    /**
     * �T�[�r�X�̃����[�g�Ăяo��������鎞�́A�����y�і߂�l�𒼗񉻂���{@link jp.ossc.nimbus.service.io.Externalizer Externalizer}�T�[�r�X�̃T�[�r�X�����擾����B<p>
     *
     * @return Externalizer�T�[�r�X�̃T�[�r�X��
     */
    public ServiceName getExternalizerServiceName();
}
