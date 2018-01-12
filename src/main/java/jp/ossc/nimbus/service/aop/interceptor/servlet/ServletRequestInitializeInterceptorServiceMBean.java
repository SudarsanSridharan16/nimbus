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
package jp.ossc.nimbus.service.aop.interceptor.servlet;

import java.util.Map;

import jp.ossc.nimbus.core.*;

/**
 * {@link ServletRequestInitializeInterceptorService}��MBean�C���^�t�F�[�X�B<p>
 * 
 * @author M.Takata
 * @see ServletRequestInitializeInterceptorService
 */
public interface ServletRequestInitializeInterceptorServiceMBean
 extends ServletFilterInterceptorServiceMBean{
    
    /**
     * {@link jp.ossc.nimbus.service.context.Context Context}�T�[�r�X�̃T�[�r�X����ݒ肷��B<p>
     *
     * @param name Context�T�[�r�X�̃T�[�r�X��
     */
    public void setContextServiceName(ServiceName name);
    
    /**
     * {@link jp.ossc.nimbus.service.context.Context Context}�T�[�r�X�̃T�[�r�X�����擾����B<p>
     *
     * @return Context�T�[�r�X�̃T�[�r�X��
     */
    public ServiceName getContextServiceName();
    
    /**
     * {@link jp.ossc.nimbus.service.context.Context Context}�T�[�r�X���烊�N�G�X�g�����ɐݒ肷��L�[��ݒ肷��B<p>
     *
     * @param keys ���N�G�X�g�����ɐݒ肷��R���e�L�X�g�L�[�̔z��
     */
    public void setContextKeys(String[] keys);
    
    /**
     * {@link jp.ossc.nimbus.service.context.Context Context}�T�[�r�X���烊�N�G�X�g�����ɐݒ肷��L�[���擾����B<p>
     *
     * @return ���N�G�X�g�����ɐݒ肷��R���e�L�X�g�L�[�̔z��
     */
    public String[] getContextKeys();
    
    /**
     * ���N�G�X�g�����ɐݒ肷�鑮�����ƃT�[�r�X�̃T�[�r�X���̃}�b�s���O��ݒ肷��B<p>
     *
     * @param names �������ƃT�[�r�X���̃}�b�s���O�z��B������=�T�[�r�X��
     */
    public void setRequestAttributeServiceNames(ServiceNameRef[] names);
    
    /**
     * ���N�G�X�g�����ɐݒ肷�鑮�����ƃT�[�r�X�̃T�[�r�X���̃}�b�s���O���擾����B<p>
     *
     * @return �������ƃT�[�r�X���̃}�b�s���O�z��
     */
    public ServiceNameRef[] getRequestAttributeServiceNames();
    
    /**
     * ���N�G�X�g�����ɐݒ肷�鑮�����ƃI�u�W�F�N�g�̃}�b�s���O��ݒ肷��B<p>
     *
     * @param attrs �������ƃI�u�W�F�N�g�̃}�b�s���O
     */
    public void setRequestAttributes(Map attrs);
    
    /**
     * ���N�G�X�g�����ɐݒ肷�鑮�����ƃI�u�W�F�N�g�̃}�b�s���O���擾����B<p>
     *
     * @return �������ƃI�u�W�F�N�g�̃}�b�s���O
     */
    public Map getRequestAttributes();
    
    /**
     * ���N�G�X�g�����ɐݒ肷�鑮�����ƃI�u�W�F�N�g��ݒ肷��B<p>
     *
     * @param name ������
     * @param attr �����l
     */
    public void setRequestAttribute(String name, Object attr);
    
    /**
     * ���N�G�X�g�����ɐݒ肷��I�u�W�F�N�g���擾����B<p>
     *
     * @param name ������
     * @return �����l
     */
    public Object getRequestAttribute(String name);
    
    /**
     * {@link #setRequestAttributeServiceNames(ServiceNameRef[])}�Őݒ肳�ꂽ�T�[�r�X���擾�ł��Ȃ��ꍇ�ɁA{@link ServiceNotFoundException}��throw���邩�ǂ����𔻒肷��B<p>
     *
     * @return true�̏ꍇthrow����
     */
    public boolean isThrowServiceNotFoundException();
    
    /**
     * {@link #setRequestAttributeServiceNames(ServiceNameRef[])}�Őݒ肳�ꂽ�T�[�r�X���擾�ł��Ȃ��ꍇ�ɁA{@link ServiceNotFoundException}��throw���邩�ǂ�����ݒ肷��B<p>
     * �f�t�H���g�́Atrue��throw����B<br>
     *
     * @param isThrow throw����ꍇ�́Atrue
     */
    public void setThrowServiceNotFoundException(boolean isThrow);
}
