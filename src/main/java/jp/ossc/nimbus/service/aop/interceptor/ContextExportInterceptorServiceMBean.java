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
 * {@link ContextExportInterceptorService}��MBean�C���^�t�F�[�X<p>
 * 
 * @author M.Takata
 * @see ContextExportInterceptorService
 */
public interface ContextExportInterceptorServiceMBean extends ServiceBaseMBean{
    
    /**
     * �G�N�X�|�[�g����{@link jp.ossc.nimbus.service.context.Context Context}�̏����i�[����}�b�v��{@link jp.ossc.nimbus.service.aop.InvocationContext InvocationContext}�̑����Ƃ��Đݒ肷�鎞�̑������̃f�t�H���g�l�B<p>
     * ���������f�t�H���g�ȊO�̒l���g�p�������ꍇ�́A{@link #setAttributeName(String)}�Őݒ肷��B<br>
     * 
     * @see #setAttributeName(String)
     */
    public static final String DEFAULT_ATTRIBUTE_NAME
         = ContextExportInterceptorService.class.getName() + ".Context";
    
    /**
     * �G�N�X�|�[�g����{@link jp.ossc.nimbus.service.context.Context Context}�T�[�r�X�̃T�[�r�X����ݒ肷��B<p>
     *
     * @param name Context�T�[�r�X�̃T�[�r�X��
     */
    public void setContextServiceName(ServiceName name);
    
    /**
     * �G�N�X�|�[�g����{@link jp.ossc.nimbus.service.context.Context Context}�T�[�r�X�̃T�[�r�X�����擾����B<p>
     *
     * @return Context�T�[�r�X�̃T�[�r�X��
     */
    public ServiceName getContextServiceName();
    
    /**
     * �G�N�X�|�[�g����{@link jp.ossc.nimbus.service.context.Context Context}�̏����i�[����}�b�v��{@link jp.ossc.nimbus.service.aop.InvocationContext InvocationContext}�̑����Ƃ��Đݒ肷�鎞�̑�������ݒ肷��B<p>
     * ���̑�����ݒ肵�Ȃ��ꍇ�́A�f�t�H���g�Ƃ��āA{@link #DEFAULT_ATTRIBUTE_NAME}���g�p�����B<p>
     *
     * @param name �G�N�X�|�[�g����{@link jp.ossc.nimbus.service.context.Context Context}�̏����i�[����}�b�v��{@link jp.ossc.nimbus.service.aop.InvocationContext InvocationContext}�̑����Ƃ��Đݒ肷�鎞�̑�����
     * @see #DEFAULT_ATTRIBUTE_NAME
     */
    public void setAttributeName(String name);
    
    /**
     * �G�N�X�|�[�g����{@link jp.ossc.nimbus.service.context.Context Context}�̏����i�[����}�b�v��{@link jp.ossc.nimbus.service.aop.InvocationContext InvocationContext}�̑����Ƃ��Đݒ肷�鎞�̑��������擾����B<p>
     *
     * @return �G�N�X�|�[�g����{@link jp.ossc.nimbus.service.context.Context Context}�̏����i�[����}�b�v��{@link jp.ossc.nimbus.service.aop.InvocationContext InvocationContext}�̑����Ƃ��Đݒ肷�鎞�̑�����
     */
    public String getAttributeName();
    
    /**
     * �G�N�X�|�[�g����{@link jp.ossc.nimbus.service.context.Context Context}�T�[�r�X�̃L�[�z���ݒ肷��B<p>
     * ���̑�����ݒ肵�Ȃ��ꍇ�́AContext�Ɋi�[���ꂽ�S�Ă̏�񂪃G�N�X�|�[�g�����B<br>
     *
     * @param keys �G�N�X�|�[�g����{@link jp.ossc.nimbus.service.context.Context Context}�T�[�r�X�̃L�[�z��
     */
    public void setContextKeys(String[] keys);
    
    /**
     * �G�N�X�|�[�g����{@link jp.ossc.nimbus.service.context.Context Context}�T�[�r�X�̃L�[�z����擾����B<p>
     *
     * @return �G�N�X�|�[�g����{@link jp.ossc.nimbus.service.context.Context Context}�T�[�r�X�̃L�[�z��
     */
    public String[] getContextKeys();
}
