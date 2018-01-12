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
package jp.ossc.nimbus.service.proxy.invoker;

import jp.ossc.nimbus.core.*;

/**
 * {@link RemoteClientMethodCallInvokerService}��MBean�C���^�t�F�[�X<p>
 * 
 * @author M.Takata
 * @see RemoteClientMethodCallInvokerService
 */
public interface RemoteClientMethodCallInvokerServiceMBean
 extends ServiceBaseMBean{
    
    /**
     * {@link jp.ossc.nimbus.service.proxy.RemoteServerInvoker RemoteServerInvoker}�C���^�t�F�[�X����������RMI�I�u�W�F�N�g��lookup����{@link jp.ossc.nimbus.service.jndi.JndiFinder JndiFinder}�T�[�r�X�̃T�[�r�X����ݒ肷��B<p>
     *
     * @param name JndiFinder�T�[�r�X�̃T�[�r�X��
     */
    public void setJndiFinderServiceName(ServiceName name);
    
    /**
     * {@link jp.ossc.nimbus.service.proxy.RemoteServerInvoker RemoteServerInvoker}�C���^�t�F�[�X����������RMI�I�u�W�F�N�g��lookup����{@link jp.ossc.nimbus.service.jndi.JndiFinder JndiFinder}�T�[�r�X�̃T�[�r�X����ݒ肷��B<p>
     *
     * @return JndiFinder�T�[�r�X�̃T�[�r�X��
     */
    public ServiceName getJndiFinderServiceName();
    
    /**
     * {@link jp.ossc.nimbus.service.proxy.RemoteServerInvoker RemoteServerInvoker}�C���^�t�F�[�X����������RMI�I�u�W�F�N�g��lookup����{@link jp.ossc.nimbus.service.repository.Repository Repository}�T�[�r�X�̃T�[�r�X����ݒ肷��B<p>
     *
     * @param name Repository�T�[�r�X�̃T�[�r�X��
     */
    public void setJndiRepositoryServiceName(ServiceName name);
    
    /**
     * {@link jp.ossc.nimbus.service.proxy.RemoteServerInvoker RemoteServerInvoker}�C���^�t�F�[�X����������RMI�I�u�W�F�N�g��lookup����{@link jp.ossc.nimbus.service.repository.Repository Repository}�T�[�r�X�̃T�[�r�X�����擾����B<p>
     *
     * @return Repository�T�[�r�X�̃T�[�r�X��
     */
    public ServiceName getJndiRepositoryServiceName();
    
    /**
     * {@link jp.ossc.nimbus.service.proxy.RemoteServerInvoker RemoteServerInvoker}�C���^�t�F�[�X����������RMI�I�u�W�F�N�g��JNDI����ݒ肷��B<p>
     * ���̑�����ݒ肵�ĂȂ��A{@link #setRemoteServiceName(ServiceName)}���ݒ肳��Ă���ꍇ�́A�����Őݒ肳�ꂽ�����[�g�Ăяo������T�[�r�X��{@link ServiceName}����A"�}�l�[�W����/�T�[�r�X��"�Ƃ���JNDI�����K�p�����B<br>
     * �ǂ�����ݒ肳��Ă��Ȃ��ꍇ�ł��A{@link jp.ossc.nimbus.service.aop.InvocationContext#getTargetObject() InvocationContext.getTargetObject()}�Ń����[�g�Ăяo������T�[�r�X�̃T�[�r�X�����擾�ł���΁A��L�Ɠ����悤��JNDI�����K�p�����B������̕��@�ł��T�[�r�X�����擾�ł��Ȃ��ꍇ�́A�Ăяo�����ɗ�O����������B<br>
     *
     * @param name RMI�I�u�W�F�N�g��JNDI��
     */
    public void setRemoteServerJndiName(String name);
    
    /**
     * {@link jp.ossc.nimbus.service.proxy.RemoteServerInvoker RemoteServerInvoker}�C���^�t�F�[�X����������RMI�I�u�W�F�N�g��JNDI�����擾����B<p>
     *
     * @return RMI�I�u�W�F�N�g��JNDI��
     */
    public String getRemoteServerJndiName();
    
    /**
     * �Ăяo�����������[�g�T�[�o�̃T�[�r�X�̃T�[�r�X����ݒ肷��B<p>
     * �����Ŏw�肳�ꂽ�T�[�r�X���́A{@link jp.ossc.nimbus.service.aop.InvocationContext#setTargetObject(Object) InvocationContext.setTargetObject(Object)}�ŁA�Ăяo���Ώۂ̃T�[�r�X�Ƃ��ē`�d�����B<br>
     *
     * @param name �Ăяo�����������[�g�T�[�o�̃T�[�r�X�̃T�[�r�X��
     */
    public void setRemoteServiceName(ServiceName name);
    
    /**
     * �Ăяo�����������[�g�T�[�o�̃T�[�r�X�̃T�[�r�X�����擾����B<p>
     *
     * @return �Ăяo�����������[�g�T�[�o�̃T�[�r�X�̃T�[�r�X��
     */
    public ServiceName getRemoteServiceName();
    
    /**
     * �����[�g�̃T�[�r�X���Ăяo�����́A�����y�і߂�l�𒼗񉻂���{@link jp.ossc.nimbus.service.io.Externalizer Externalizer}�T�[�r�X�̃T�[�r�X����ݒ肷��B<p>
     *
     * @param name Externalizer�T�[�r�X�̃T�[�r�X��
     */
    public void setExternalizerServiceName(ServiceName name);
    
    /**
     * �����[�g�̃T�[�r�X���Ăяo�����́A�����y�і߂�l�𒼗񉻂���{@link jp.ossc.nimbus.service.io.Externalizer Externalizer}�T�[�r�X�̃T�[�r�X�����擾����B<p>
     *
     * @return Externalizer�T�[�r�X�̃T�[�r�X��
     */
    public ServiceName getExternalizerServiceName();
    
    /**
     * ����Invoker���������Ă��邩�ǂ����𔻒肷��B<p>
     *
     * @return �������Ă���ꍇtrue
     */
    public boolean isAlive();
}
