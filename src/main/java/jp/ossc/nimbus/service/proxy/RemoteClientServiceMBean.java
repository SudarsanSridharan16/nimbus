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
 * {@link RemoteClientService}��MBean�C���^�t�F�[�X<p>
 * 
 * @author M.Takata
 * @see RemoteClientService
 */
public interface RemoteClientServiceMBean extends FactoryServiceBaseMBean{
    
    /**
     * �v���L�V����T�[�r�X�̃C���^�t�F�[�X����ݒ肷��B<p>
     *
     * @param className �v���L�V����T�[�r�X�̃C���^�t�F�[�X��
     */
    public void setRemoteInterfaceClassName(String className);
    
    /**
     * �v���L�V����T�[�r�X�̃C���^�t�F�[�X�����擾����B<p>
     *
     * @return �v���L�V����T�[�r�X�̃C���^�t�F�[�X��
     */
    public String getRemoteInterfaceClassName();
    
    /**
     * �v���L�V����T�[�r�X�̃T�[�r�X����ݒ肷��B<p>
     * �����Ŏw�肳�ꂽ�T�[�r�X���́A{@link jp.ossc.nimbus.service.aop.InvocationContext#setTargetObject(Object) InvocationContext.setTargetObject(Object)}�ŁA�Ăяo���Ώۂ̃T�[�r�X�Ƃ��ē`�d�����B<br>
     *
     * @param name �v���L�V����T�[�r�X�̃T�[�r�X��
     */
    public void setRemoteServiceName(ServiceName name);
    
    /**
     * �v���L�V����T�[�r�X�̃T�[�r�X�����擾����B<p>
     *
     * @return �v���L�V����T�[�r�X�̃T�[�r�X��
     */
    public ServiceName getRemoteServiceName();
    
    /**
     * �v���L�V�ɋ��ݍ���{@link jp.ossc.nimbus.service.aop.InterceptorChainList InterceptorChainList}�T�[�r�X�̃T�[�r�X����ݒ肷��B<p>
     * �C���^�[�Z�v�^�����ݍ��܂Ȃ��ꍇ�́A�ݒ肵�Ȃ��Ă��ǂ��B<br>
     *
     * @param name InterceptorChainList�T�[�r�X�̃T�[�r�X��
     */
    public void setInterceptorChainListServiceName(ServiceName name);
    
    /**
     * �v���L�V�ɋ��ݍ���{@link jp.ossc.nimbus.service.aop.InterceptorChainList InterceptorChainList}�T�[�r�X�̃T�[�r�X�����擾����B<p>
     *
     * @return InterceptorChainList�T�[�r�X�̃T�[�r�X��
     */
    public ServiceName getInterceptorChainListServiceName();
    
    /**
     * �v���L�V����T�[�r�X���Ăяo��{@link jp.ossc.nimbus.service.aop.Invoker Invoker}�T�[�r�X�̃T�[�r�X����ݒ肷��B<p>
     *
     * @param name Invoker�T�[�r�X�̃T�[�r�X��
     */
    public void setInvokerServiceName(ServiceName name);
    
    /**
     * �v���L�V����T�[�r�X���Ăяo��{@link jp.ossc.nimbus.service.aop.Invoker Invoker}�T�[�r�X�̃T�[�r�X�����擾����B<p>
     *
     * @return Invoker�T�[�r�X�̃T�[�r�X��
     */
    public ServiceName getInvokerServiceName();
    
    /**
     * �v���L�V�ɋ��ݍ���{@link jp.ossc.nimbus.service.aop.InterceptorChain InterceptorChain}�𐶐�����{@link jp.ossc.nimbus.service.aop.InterceptorChainFactory InterceptorChainFactory}�̃T�[�r�X����ݒ肷��B<p>
     *
     * @param name InterceptorChainFactory�T�[�r�X�̃T�[�r�X��
     */
    public void setInterceptorChainFactoryServiceName(ServiceName name);
    
    /**
     * �v���L�V�ɋ��ݍ���{@link jp.ossc.nimbus.service.aop.InterceptorChain InterceptorChain}�𐶐�����{@link jp.ossc.nimbus.service.aop.InterceptorChainFactory InterceptorChainFactory}�̃T�[�r�X�����擾����B<p>
     *
     * @return InterceptorChainFactory�T�[�r�X�̃T�[�r�X��
     */
    public ServiceName getInterceptorChainFactoryServiceName();
    
    /**
     * �v���L�V�𖈉񐶐����邩�ǂ�����ݒ肷��B<p>
     * �f�t�H���g�́Afalse�B<br>
     * 
     * @param isCreate ���񐶐�����ꍇtrue
     */
    public void setCreateNewProxy(boolean isCreate);
    
    /**
     * �v���L�V�𖈉񐶐����邩�ǂ����𔻒肷��B<p>
     * 
     * @return true�̏ꍇ�A���񐶐�����
     */
    public boolean isCreateNewProxy();
    
    /**
     * �v���L�V�ɕR�t���ăC���^�[�Z�v�^�`�F�C���𐶐����邩�ǂ�����ݒ肷��B<p>
     * �f�t�H���g�́Afalse�B<br>
     * 
     * @param isCreate �R�t����ꍇtrue
     */
    public void setCreateInterceptorChainByProxy(boolean isCreate);
    
    /**
     * �v���L�V�ɕR�t���ăC���^�[�Z�v�^�`�F�C���𐶐����邩�ǂ����𔻒肷��B<p>
     * 
     * @return true�̏ꍇ�A�R�t����
     */
    public boolean isCreateInterceptorChainByProxy();
}
