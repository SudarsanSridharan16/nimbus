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

import java.util.Locale;

/**
 * {@link ServletResponseSetInterceptorService}��MBean�C���^�t�F�[�X�B<p>
 * 
 * @author M.Takata
 * @see ServletResponseSetInterceptorService
 */
public interface ServletResponseSetInterceptorServiceMBean
 extends ServletFilterInterceptorServiceMBean{
    
    /**
     * {@link javax.servlet.ServletResponse#setBufferSize(int)}��ݒ肷��B<p>
     *
     * @param size �o�b�t�@�T�C�Y
     */
    public void setBufferSize(int size);
    
    /**
     * {@link javax.servlet.ServletResponse#setBufferSize(int)}�ɐݒ肷��l���擾����B<p>
     *
     * @return �o�b�t�@�T�C�Y
     */
    public int getBufferSize();
    
    /**
     * {@link javax.servlet.ServletResponse#setCharacterEncoding(String)}��ݒ肷��B<p>
     *
     * @param charset �����G���R�[�f�B���O
     */
    public void setCharacterEncoding(String charset);
    
    /**
     * {@link javax.servlet.ServletResponse#setCharacterEncoding(String)}�ɐݒ肷��l���擾����B<p>
     *
     * @return �����G���R�[�f�B���O
     */
    public String getCharacterEncoding();
    
    /**
     * {@link javax.servlet.ServletResponse#setContentType(String)}��ݒ肷��B<p>
     *
     * @param type Content-Type�w�b�_
     */
    public void setContentType(String type);
    
    /**
     * {@link javax.servlet.ServletResponse#setContentType(String)}�ɐݒ肷��l���擾����B<p>
     *
     * @return Content-Type�w�b�_
     */
    public String getContentType();
    
    /**
     * {@link javax.servlet.ServletResponse#setLocale(Locale)}��ݒ肷��B<p>
     *
     * @param loc ���P�[�����
     */
    public void setLocale(Locale loc);
    
    /**
     * {@link javax.servlet.ServletResponse#setLocale(Locale)}�ɐݒ肷��l���擾����B<p>
     *
     * @return ���P�[�����
     */
    public Locale getLocale();
}