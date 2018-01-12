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

import java.util.*;

import javax.servlet.http.Cookie;

import jp.ossc.nimbus.core.*;

/**
 * {@link HttpServletResponseSetInterceptorService}��MBean�C���^�t�F�[�X�B<p>
 * 
 * @author M.Takata
 * @see HttpServletResponseSetInterceptorService
 */
public interface HttpServletResponseSetInterceptorServiceMBean
 extends ServletResponseSetInterceptorServiceMBean{
    
    /**
     * {@link javax.servlet.http.HttpServletResponse#setHeader(String, String)}�Őݒ肷��HTTP�w�b�_�̃}�b�v��ݒ肷��B<p>
     *
     * @param headers HTTP�w�b�_�̃}�b�v
     */
    public void setSetHeaders(Map headers);
    
    /**
     * {@link javax.servlet.http.HttpServletResponse#setHeader(String, String)}�Őݒ肷��HTTP�w�b�_�̃}�b�v���擾����B<p>
     *
     * @return HTTP�w�b�_�̃}�b�v
     */
    public Map getSetHeaders();
    
    /**
     * {@link javax.servlet.http.HttpServletResponse#setHeader(String, String)}�Őݒ肷��HTTP�w�b�_��ݒ肷��B<p>
     *
     * @param name HTTP�w�b�_��
     * @param value HTTP�w�b�_
     */
    public void setSetHeader(String name, String value);
    
    /**
     * {@link javax.servlet.http.HttpServletResponse#setHeader(String, String)}�Őݒ肷��HTTP�w�b�_���擾����B<p>
     *
     * @param name HTTP�w�b�_��
     * @return HTTP�w�b�_
     */
    public String getSetHeader(String name);
    
    /**
     * {@link javax.servlet.http.HttpServletResponse#setHeader(String, String)}�Őݒ肷��HTTP�w�b�_���폜����B<p>
     *
     * @param name HTTP�w�b�_��
     */
    public void removeSetHeader(String name);
    
    /**
     * {@link javax.servlet.http.HttpServletResponse#setHeader(String, String)}�Őݒ肷��HTTP�w�b�_��S�č폜����B<p>
     */
    public void clearSetHeaders();
    
    /**
     * {@link jp.ossc.nimbus.service.context.Context Context}����擾�����l��HTTP�w�b�_�Ƃ��āA{@link javax.servlet.http.HttpServletResponse#setHeader(String, String)}�Őݒ肷��ۂ́AContext�L�[����HTTP�w�b�_���̃}�b�s���O��ݒ肷��B<p>
     *
     * @param keys Context�L�[����HTTP�w�b�_���̃}�b�s���O
     */
    public void setSetHeaderContextKeys(Properties keys);
    
    /**
     * {@link jp.ossc.nimbus.service.context.Context Context}����擾�����l��HTTP�w�b�_�Ƃ��āA{@link javax.servlet.http.HttpServletResponse#setHeader(String, String)}�Őݒ肷��ۂ́AContext�L�[����HTTP�w�b�_���̃}�b�s���O���擾����B<p>
     *
     * @return Context�L�[����HTTP�w�b�_���̃}�b�s���O
     */
    public Properties getSetHeaderContextKeys();
    
    /**
     * {@link javax.servlet.http.HttpServletResponse#addHeader(String, String)}�Őݒ肷��HTTP�w�b�_��ݒ肷��B<p>
     *
     * @param name HTTP�w�b�_��
     * @param value HTTP�w�b�_
     */
    public void setAddHeader(String name, String value);
    
    /**
     * {@link javax.servlet.http.HttpServletResponse#addHeader(String, String)}�Őݒ肷��HTTP�w�b�_�̃}�b�v���擾����B<p>
     *
     * @return HTTP�w�b�_�̃}�b�v
     */
    public String[] getAddHeaders(String name);
    
    /**
     * {@link javax.servlet.http.HttpServletResponse#addHeader(String, String)}�Őݒ肷��HTTP�w�b�_���폜����B<p>
     *
     * @param name HTTP�w�b�_��
     */
    public void removeAddHeader(String name);
    
    /**
     * {@link javax.servlet.http.HttpServletResponse#addHeader(String, String)}�Őݒ肷��HTTP�w�b�_��S�č폜����B<p>
     */
    public void clearAddHeaders();
    
    /**
     * {@link jp.ossc.nimbus.service.context.Context Context}����擾�����l��HTTP�w�b�_�Ƃ��āA{@link javax.servlet.http.HttpServletResponse#addHeader(String, String)}�Őݒ肷��ۂ́AContext�L�[����HTTP�w�b�_���̃}�b�s���O��ݒ肷��B<p>
     *
     * @param keys Context�L�[����HTTP�w�b�_���̃}�b�s���O
     */
    public void setAddHeaderContextKeys(Properties keys);
    
    /**
     * {@link jp.ossc.nimbus.service.context.Context Context}����擾�����l��HTTP�w�b�_�Ƃ��āA{@link javax.servlet.http.HttpServletResponse#addHeader(String, String)}�Őݒ肷��ۂ́AContext�L�[����HTTP�w�b�_���̃}�b�s���O���擾����B<p>
     *
     * @return Context�L�[����HTTP�w�b�_���̃}�b�s���O
     */
    public Properties getAddHeaderContextKeys();
    
    /**
     * {@link javax.servlet.http.HttpServletResponse#addCookie(Cookie)}�Őݒ肷��Cookie��ǉ�����B<p>
     *
     * @param cookie Cookie
     */
    public void addCookie(Cookie cookie);
    
    /**
     * {@link javax.servlet.http.HttpServletResponse#addCookie(Cookie)}�Őݒ肷��Cookie���폜����B<p>
     *
     * @param name Cookie�̖��O
     */
    public void removeCookie(String name);
    
    /**
     * {@link javax.servlet.http.HttpServletResponse#addCookie(Cookie)}�Őݒ肷��Cookie��S�č폜����B<p>
     */
    public void clearCookies();
    
    /**
     * {@link jp.ossc.nimbus.service.context.Context Context}����擾�����l��HTTP�w�b�_�Ƃ��Đݒ肷��ۂ́AContext�T�[�r�X����ݒ肷��B<p>
     *
     * @param name Context�T�[�r�X��
     */
    public void setContextServiceName(ServiceName name);
    
    /**
     * {@link jp.ossc.nimbus.service.context.Context Context}����擾�����l��HTTP�w�b�_�Ƃ��Đݒ肷��ۂ́AContext�T�[�r�X�����擾����B<p>
     *
     * @return Context�T�[�r�X��
     */
    public ServiceName getContextServiceName();
}