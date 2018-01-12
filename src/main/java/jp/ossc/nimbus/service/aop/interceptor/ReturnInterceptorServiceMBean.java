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
package jp.ossc.nimbus.service.aop.interceptor;

import jp.ossc.nimbus.core.*;

/**
 * {@link ReturnInterceptorService}��MBean�C���^�t�F�[�X�B<p>
 * 
 * @author M.Takata
 * @see ReturnInterceptorService
 */
public interface ReturnInterceptorServiceMBean
 extends ServiceBaseMBean{
    
    /**
     * �L��/������ݒ肷��B<p>
     *
     * @param enabled �L���ɂ���ꍇ�́Atrue
     */
    public void setEnabled(boolean enabled);
    
    /**
     * �L��/�����𔻒肷��B<p>
     *
     * @return true�̏ꍇ�A�L��
     */
    public boolean isEnabled();
    
    /**
     * �Ăяo�����{@link jp.ossc.nimbus.service.aop.InvocationContext InvocationContext}�ɑ΂�������Ɩ߂�l��ݒ肷��B<p>
     * �������́AThe Apache Jakarta Project�� Commons Jexl(http://jakarta.apache.org/commons/jexl/)���g�p����B<br>
     * InvocationContext�̃v���p�e�B���Q�Ƃ���ꍇ�́A�v���p�e�B��\�����镶�����"@"�ň͂�Ŏw�肷��B�����Ō����A�v���p�e�B�̊T�O�́AJava Beans�̃v���p�e�B�̊T�O���L���A{@link jp.ossc.nimbus.beans.PropertyFactory PropertyFactory}�̋K��ɏ]���B<br>
     *
     * @param condition ������
     * @param value �߂�l
     */
    public void setReturnValue(String condition, Object value);
    
    /**
     * �Ăяo�����{@link jp.ossc.nimbus.service.aop.InvocationContext InvocationContext}�ɑ΂�������ɑΉ�����߂�l���擾����B<p>
     *
     * @param condition ������
     * @return �߂�l
     */
    public Object getReturnValue(String condition);
    
    /**
     * �߂�l��ݒ肷��B<p>
     *
     * @param value �߂�l
     */
    public void setReturnValue(Object value);
    
    /**
     * �߂�l���擾����B<p>
     *
     * @return �߂�l
     */
    public Object getReturnValue();
    
    /**
     * �Ăяo�����{@link jp.ossc.nimbus.service.aop.InvocationContext InvocationContext}�ɑ΂�������Ɩ߂�T�[�r�X����ݒ肷��B<p>
     * �������́AThe Apache Jakarta Project�� Commons Jexl(http://jakarta.apache.org/commons/jexl/)���g�p����B<br>
     * InvocationContext�̃v���p�e�B���Q�Ƃ���ꍇ�́A�v���p�e�B��\�����镶�����"@"�ň͂�Ŏw�肷��B�����Ō����A�v���p�e�B�̊T�O�́AJava Beans�̃v���p�e�B�̊T�O���L���A{@link jp.ossc.nimbus.beans.PropertyFactory PropertyFactory}�̋K��ɏ]���B<br>
     *
     * @param condition ������
     * @param name �߂�T�[�r�X��
     */
    public void setReturnServiceName(String condition, ServiceName name);
    
    /**
     * �Ăяo�����{@link jp.ossc.nimbus.service.aop.InvocationContext InvocationContext}�ɑ΂�������ɑΉ�����߂�T�[�r�X�����擾����B<p>
     *
     * @param condition ������
     * @return �߂�T�[�r�X��
     */
    public ServiceName getReturnServiceName(String condition);
    
    /**
     * �߂�T�[�r�X����ݒ肷��B<p>
     *
     * @param name �߂�T�[�r�X��
     */
    public void setReturnServiceName(ServiceName name);
    
    /**
     * �߂�T�[�r�X�����擾����B<p>
     *
     * @return �߂�T�[�r�X��
     */
    public ServiceName getReturnServiceName();
    
    /**
     * �߂�l�����b�v����ۂ̃C���^�[�t�F�[�X��ݒ肷��B<p>
     *
     * @param clazz �C���^�[�t�F�[�X
     */
    public void setReturnInterfaceClass(Class clazz);
    
    /**
     * �߂�l�����b�v����ۂ̃C���^�[�t�F�[�X���擾����B<p>
     *
     * @return �C���^�[�t�F�[�X
     */
    public Class getReturnInterfaceClass();
    
    /**
     * �߂�l�����b�v�����v���L�V�ɋ��ݍ���{@link jp.ossc.nimbus.service.aop.InterceptorChainList InterceptorChainList}�T�[�r�X�̃T�[�r�X����ݒ肷��B<p>
     *
     * @param name InterceptorChainList�T�[�r�X�̃T�[�r�X��
     */
    public void setInterceptorChainListServiceName(ServiceName name);
    
    /**
     * �߂�l�����b�v�����v���L�V�ɋ��ݍ���{@link jp.ossc.nimbus.service.aop.InterceptorChainList InterceptorChainList}�T�[�r�X�̃T�[�r�X�����擾����B<p>
     *
     * @return InterceptorChainList�T�[�r�X�̃T�[�r�X��
     */
    public ServiceName getInterceptorChainListServiceName();
}