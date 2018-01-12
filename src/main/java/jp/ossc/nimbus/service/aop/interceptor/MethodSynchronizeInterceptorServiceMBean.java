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
 * {@link MethodSynchronizeInterceptorService}��MBean�C���^�t�F�[�X<p>
 * 
 * @author M.Takata
 * @see MethodSynchronizeInterceptorService
 */
public interface MethodSynchronizeInterceptorServiceMBean extends ServiceBaseMBean{
    
    /**
     * VM�P�ʂœ������铯���X�R�[�v�B<p>
     */
    public static final String SCOPE_VM = "VM";
    
    /**
     * �N���X�P�ʂœ������铯���X�R�[�v�B<p>
     */
    public static final String SCOPE_CLASS = "CLASS";
    
    /**
     * ���\�b�h�P�ʂœ������铯���X�R�[�v�B<p>
     */
    public static final String SCOPE_METHOD = "METHOD";
    
    /**
     * �C���X�^���X�P�ʂœ������铯���X�R�[�v�B<p>
     */
    public static final String SCOPE_INSTANCE = "INSTANCE";
    
    /**
     * ���������X�R�[�v��ݒ肷��B<p>
     *
     * @param scope �����X�R�[�v
     * @exception IllegalArgumentException �w�肳�ꂽ�����X�R�[�v���s���ȏꍇ
     * @see #SCOPE_VM
     * @see #SCOPE_CLASS
     * @see #SCOPE_METHOD
     * @see #SCOPE_INSTANCE
     */
    public void setScope(String scope) throws IllegalArgumentException;
    
    /**
     * ���������X�R�[�v���擾����B<p>
     *
     * @return �����X�R�[�v
     */
    public String getScope();
}
