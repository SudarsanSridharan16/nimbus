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
 * {@link AuthenticateInterceptorService}��MBean�C���^�t�F�[�X�B<p>
 * 
 * @author M.Takata
 * @see AuthenticateInterceptorService
 */
public interface AuthenticateInterceptorServiceMBean
 extends ServletFilterInterceptorServiceMBean{
    
    /**
     * �F�؏������N�G�X�g��������擾���鎞�y�уZ�b�V���������ɐݒ肷�鎞�Ɏg�p���鑮�����̃f�t�H���g�l�B<p>
     */
    public static final String DEFAULT_AUTH_INFO_ATTRIBUTE_NAME = AuthenticateInterceptorService.class.getName().replaceAll("\\.", "_") + "_AUTH_INFO";
    
    /**
     * �v���I�u�W�F�N�g���擾����{@link jp.ossc.nimbus.service.context.Context Context}�T�[�r�X����ݒ肷��B<p>
     * ���̑�����ݒ肵�Ȃ��ꍇ�́A���N�G�X�g��������̂ݎ擾����B<br>
     *
     * @param name Context�T�[�r�X��
     */
    public void setThreadContextServiceName(ServiceName name);
    
    /**
     * �v���I�u�W�F�N�g���擾����{@link jp.ossc.nimbus.service.context.Context Context}�T�[�r�X�����擾����B<p>
     *
     * @return Context�T�[�r�X��
     */
    public ServiceName getThreadContextServiceName();
    
    /**
     * �v���I�u�W�F�N�g�����N�G�X�g��������擾���鎞�Ɏg�p���鑮������ݒ肷��B<p>
     * �f�t�H���g�l�́A{@link StreamExchangeInterceptorServiceMBean#DEFAULT_REQUEST_OBJECT_ATTRIBUTE_NAME}�B<br>
     *
     * @param name ������
     * @see StreamExchangeInterceptorServiceMBean#DEFAULT_REQUEST_OBJECT_ATTRIBUTE_NAME
     */
    public void setRequestObjectAttributeName(String name);
    
    /**
     * �v���I�u�W�F�N�g�����N�G�X�g��������擾���鎞�Ɏg�p���鑮�������擾����B<p>
     *
     * @return ������
     */
    public String getRequestObjectAttributeName();
    
    /**
     * �v���I�u�W�F�N�g���R���e�L�X�g����擾���鎞�Ɏg�p����L�[����ݒ肷��B<p>
     * �f�t�H���g�l�́A{@link StreamExchangeInterceptorServiceMBean#DEFAULT_REQUEST_OBJECT_CONTEXT_KEY}�B<br>
     *
     * @param key �L�[��
     * @see StreamExchangeInterceptorServiceMBean#DEFAULT_REQUEST_OBJECT_CONTEXT_KEY
     */
    public void setRequestObjectContextKey(String key);
    
    /**
     * �v���I�u�W�F�N�g���R���e�L�X�g����擾���鎞�Ɏg�p����L�[�����擾����B<p>
     *
     * @return �L�[��
     */
    public String getRequestObjectContextKey();
    
    /**
     * �F�؏������N�G�X�g��������擾���鎞�y�уZ�b�V���������ɐݒ肷�鎞�Ɏg�p���鑮������ݒ肷��B<p>
     * �f�t�H���g�l�́A{@link #DEFAULT_AUTH_INFO_ATTRIBUTE_NAME}�B<br>
     *
     * @param name ������
     * @see #DEFAULT_AUTH_INFO_ATTRIBUTE_NAME
     */
    public void setAuthenticatedInfoAttributeName(String name);
    
    /**
     * �F�؏������N�G�X�g��������擾���鎞�y�уZ�b�V���������ɐݒ肷�鎞�Ɏg�p���鑮�������擾����B<p>
     *
     * @return ������
     */
    public String getAuthenticatedInfoAttributeName();
    
    /**
     * �F�؏����R���e�L�X�g����擾���鎞�Ɏg�p����L�[����ݒ肷��B<p>
     * �f�t�H���g�l�́A{@link #DEFAULT_AUTH_INFO_ATTRIBUTE_NAME}�B<br>
     *
     * @param key �L�[��
     * @see #DEFAULT_AUTH_INFO_ATTRIBUTE_NAME
     */
    public void setAuthenticatedInfoContextKey(String key);
    
    /**
     * �F�؏����R���e�L�X�g����擾���鎞�Ɏg�p����L�[�����擾����B<p>
     *
     * @return �L�[��
     */
    public String getAuthenticatedInfoContextKey();
    
    /**
     * ���̓I�u�W�F�N�g�ƔF�؏����ǂ���r���邩�̃}�b�s���O��ݒ肷��B<p>
     *
     * @param mapping ��r�ΏۂƂȂ���̓I�u�W�F�N�g�ƔF�؏��̃v���p�e�B�}�b�s���O�B���̓I�u�W�F�N�g�̃v���p�e�B=�F�؏��̃v���p�e�B
     */
    public void setAuthenticatedInfoMapping(Map mapping);
    
    /**
     * ���̓I�u�W�F�N�g�ƔF�؏����ǂ���r���邩�̃}�b�s���O���擾����B<p>
     *
     * @return ��r�ΏۂƂȂ���̓I�u�W�F�N�g�ƔF�؏��̃v���p�e�B�}�b�s���O
     */
    public Map getAuthenticatedInfoMapping();
    
    /**
     * �F�؏��𐶐����郍�O�C���̃p�X��ݒ肷��B<p>
     * ���O�C���̃��N�G�X�g�������s���A�v���P�[�V�����ŔF�؏��𐶐����A�F�؃��N�G�X�g����(��������{@link #getAuthenticatedInfoAttributeName()})�ɐݒ肷��K�v������B<br>
     *
     * @param path ���O�C���̃p�X
     */
    public void setLoginPath(String path);
    
    /**
     * �F�؏��𐶐����郍�O�C���̃p�X���擾����B<p>
     *
     * @return ���O�C���̃p�X
     */
    public String getLoginPath();
    
    /**
     * �F�؏����폜���郍�O�A�E�g�̃p�X��ݒ肷��B<p>
     *
     * @param path ���O�A�E�g�̃p�X
     */
    public void setLogoutPath(String path);
    
    /**
     * �F�؏����폜���郍�O�A�E�g�̃p�X���擾����B<p>
     *
     * @return ���O�A�E�g�̃p�X
     */
    public String getLogoutPath();
    
    /**
     * �F�؏����X�g�A����{@link AuthenticateStore}�T�[�r�X�̃T�[�r�X����ݒ肷��B<p>
     *
     * @param name AuthenticateStore�T�[�r�X�̃T�[�r�X��
     */
    public void setAuthenticateStoreServiceName(ServiceName name);
    
    /**
     * �F�؏����X�g�A����{@link AuthenticateStore}�T�[�r�X�̃T�[�r�X�����擾����B<p>
     *
     * @return AuthenticateStore�T�[�r�X�̃T�[�r�X��
     */
    public ServiceName getAuthenticateStoreServiceName();
    
    /**
     * ���O�C�����ɁA{@link AuthenticateStore#create(HttpServletRequest, Object)}���Ăяo���A�F�؏����X�g�A���邩�ǂ�����ݒ肷��B<p>
     * �f�t�H���g�́Atrue�ŔF�؏����X�g�A����B<br>
     * 
     * @param isCreate �F�؏����X�g�A����ꍇtrue
     */
    public void setStoreCreate(boolean isCreate);
    
    /**
     * ���O�C�����ɁA{@link AuthenticateStore#create(HttpServletRequest, Object)}���Ăяo���A�F�؏����X�g�A���邩�ǂ����𔻒肷��B<p>
     * 
     * @return true�̏ꍇ�A�F�؏����X�g�A����
     */
    public boolean isStoreCreate();
    
    /**
     * ���O�A�E�g���ɁA{@link AuthenticateStore#destroy(HttpServletRequest, Object)}���Ăяo���A�F�؏����X�g�A����폜���邩�ǂ�����ݒ肷��B<p>
     * �f�t�H���g�́Atrue�ŔF�؏����X�g�A����폜����B<br>
     * 
     * @param isDestroy �F�؏����X�g�A����폜����ꍇtrue
     */
    public void setStoreDestroy(boolean isDestroy);
    
    /**
     * ���O�A�E�g���ɁA{@link AuthenticateStore#destroy(HttpServletRequest, Object)}���Ăяo���A�F�؏����X�g�A����폜���邩�ǂ����𔻒肷��B<p>
     * 
     * @return true�̏ꍇ�A�F�؏����X�g�A����폜����
     */
    public boolean isStoreDestroy();
    
    /**
     * ���O�C���������ɁA�Z�b�V���������łɑ��݂���ꍇ�ɂ��̃Z�b�V�������������邩�ǂ�����ݒ肷��B<p>
     * �f�t�H���g�́Afalse�Ŗ��������Ȃ��B<br>
     * 
     * @param isInvalidate �Z�b�V�����𖳌�������ꍇtrue
     */
    public void setSessionInvalidate(boolean isInvalidate);
    
    /**
     * ���O�C���������ɁA�Z�b�V���������łɑ��݂���ꍇ�ɂ��̃Z�b�V�������������邩�ǂ����𔻒肷��B<p>
     * 
     * @return true�̏ꍇ�A�Z�b�V�����𖳌�������
     */
    public boolean isSessionInvalidate();
}