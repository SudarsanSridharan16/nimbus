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
package jp.ossc.nimbus.service.ioccall.interceptor;

import jp.ossc.nimbus.core.*;

/**
 * {@link ThreadContextImportInterceptorService}��MBean�C���^�t�F�[�X�B<p>
 *
 * @author M.Takata
 */
public interface ThreadContextImportInterceptorServiceMBean
 extends ServiceBaseMBean{
    
    /**
     * �X���b�h�R���e�L�X�g�T�[�r�X�̃T�[�r�X����ݒ肷��B<p>
     * {@link jp.ossc.nimbus.ioc.FacadeValue FacadeValue}�̃w�b�_���ڂ��X���b�h�R���e�L�X�g�ɐݒ肷��B<br>
     *
     * @param name �X���b�h�R���e�L�X�g�T�[�r�X�̃T�[�r�X��
     */
    public void setThreadContextServiceName(ServiceName name);
    
    /**
     * �X���b�h�R���e�L�X�g�T�[�r�X�̃T�[�r�X�����擾����B<p>
     *
     * @return �X���b�h�R���e�L�X�g�T�[�r�X�̃T�[�r�X��
     */
    public ServiceName getThreadContextServiceName();
    
    /**
     * �X���b�h�R���e�L�X�g�ɐݒ肷��{@link jp.ossc.nimbus.ioc.FacadeValue FacadeValue}�̃w�b�_���ږ���ݒ肷��B<p>
     * ���̑�����ݒ肵�Ȃ��ꍇ�́A�S�Ẵw�b�_���ڂ��X���b�h�R���e�L�X�g�ɐݒ肷��B<br>
     *
     * @param keys �w�b�_���ږ��z��
     */
    public void setHeaderKeys(String[] keys);
    
    /**
     * �X���b�h�R���e�L�X�g�ɐݒ肷��{@link jp.ossc.nimbus.ioc.FacadeValue FacadeValue}�̃w�b�_���ږ����擾����B<p>
     *
     * @return �w�b�_���ږ��z��
     */
    public String[] getHeaderKeys();
}