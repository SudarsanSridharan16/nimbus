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

/**
 * {@link SelectableServletFilterInterceptorService}��MBean�C���^�t�F�[�X�B<p>
 * 
 * @author M.Takata
 * @see SelectableServletFilterInterceptorService
 */
public interface SelectableServletFilterInterceptorServiceMBean
 extends ServletFilterInterceptorServiceMBean{
    
    /**
     * �I���\��URL��{@link jp.ossc.nimbus.service.aop.Interceptor Interceptor}�T�[�r�X���̃}�b�s���O��ݒ肷��B<p>
     *
     * @param mapping �I���\��URL��Interceptor�T�[�r�X���̃}�b�s���O�BURL�i���K�\���j=Interceptor�T�[�r�X��
     */
    public void setURLAndInterceptorServiceNameMapping(String[] mapping);
    
    /**
     * �I���\��URL��{@link jp.ossc.nimbus.service.aop.Interceptor Interceptor}�T�[�r�X���̃}�b�s���O���擾����B<p>
     *
     * @return �I���\��URL��Interceptor�T�[�r�X���̃}�b�s���O
     */
    public String[] getURLAndInterceptorServiceNameMapping();
    
    /**
     * �I���\��URI��{@link jp.ossc.nimbus.service.aop.Interceptor Interceptor}�T�[�r�X���̃}�b�s���O��ݒ肷��B<p>
     *
     * @param mapping �I���\��URI��Interceptor�T�[�r�X���̃}�b�s���O�BURI�i���K�\���j=Interceptor�T�[�r�X��
     */
    public void setURIAndInterceptorServiceNameMapping(String[] mapping);
    
    /**
     * �I���\��URI��{@link jp.ossc.nimbus.service.aop.Interceptor Interceptor}�T�[�r�X���̃}�b�s���O���擾����B<p>
     *
     * @return �I���\��URI��Interceptor�T�[�r�X���̃}�b�s���O
     */
    public String[] getURIAndInterceptorServiceNameMapping();
    
    /**
     * �I���\�ȃT�[�u���b�g�p�X��{@link jp.ossc.nimbus.service.aop.Interceptor Interceptor}�T�[�r�X���̃}�b�s���O��ݒ肷��B<p>
     *
     * @param mapping �I���\�ȃT�[�u���b�g�p�X��Interceptor�T�[�r�X���̃}�b�s���O�B�T�[�u���b�g�p�X�i���K�\���j=Interceptor�T�[�r�X��
     */
    public void setPathAndInterceptorServiceNameMapping(String[] mapping);
    
    /**
     * �I���\�ȃT�[�u���b�g�p�X��{@link jp.ossc.nimbus.service.aop.Interceptor Interceptor}�T�[�r�X���̃}�b�s���O���擾����B<p>
     *
     * @return �I���\�ȃT�[�u���b�g�p�X��Interceptor�T�[�r�X���̃}�b�s���O
     */
    public String[] getPathAndInterceptorServiceNameMapping();
}