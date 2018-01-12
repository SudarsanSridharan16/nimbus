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
package jp.ossc.nimbus.service.aop;

import java.util.Map;

import jp.ossc.nimbus.core.*;

/**
 * {@link SelectableServletFilterInterceptorChainListService}��MBean�C���^�t�F�[�X<p>
 * 
 * @author M.Takata
 * @see SelectableServletFilterInterceptorChainListService
 */
public interface SelectableServletFilterInterceptorChainListServiceMBean
 extends ServiceBaseMBean{
    
    /**
     * �w�肵��URL�ɍ��v�����ꍇ�Ɏg�p����{@link InterceptorChainList}�T�[�r�X�̃T�[�r�X�����}�b�s���O����B<p>
     * ���N�G�X�gURL���w�肳�ꂽURL�ɊY������ꍇ�ɁA����URL�Ƀ}�b�s���O���ꂽInterceptorChainList�T�[�r�X���I�������B<br>
     *
     * @param mapping URL�i���K�\���j��InterceptorChainList�T�[�r�X�̃T�[�r�X���̃}�b�s���O�BURL=�T�[�r�X��
     */
    public void setEnabledURLMapping(Map mapping);
    
    /**
     * �w�肵��URL�ɍ��v�����ꍇ�Ɏg�p����{@link InterceptorChainList}�T�[�r�X�̃T�[�r�X���̃}�b�s���O���擾����B<p>
     *
     * @return URL�i���K�\���j��InterceptorChainList�T�[�r�X�̃T�[�r�X���̃}�b�s���O
     */
    public Map getEnabledURLMapping();
    
    /**
     * �w�肵��URI�ɍ��v�����ꍇ�Ɏg�p����{@link InterceptorChainList}�T�[�r�X�̃T�[�r�X�����}�b�s���O����B<p>
     * ���N�G�X�gURI���w�肳�ꂽURI�ɊY������ꍇ�ɁA����URI�Ƀ}�b�s���O���ꂽInterceptorChainList�T�[�r�X���I�������B<br>
     *
     * @param mapping URI�i���K�\���j��InterceptorChainList�T�[�r�X�̃T�[�r�X���̃}�b�s���O�BURI=�T�[�r�X��
     */
    public void setEnabledURIMapping(Map mapping);
    
    /**
     * �w�肵��URI�ɍ��v�����ꍇ�Ɏg�p����{@link InterceptorChainList}�T�[�r�X�̃T�[�r�X���̃}�b�s���O���擾����B<p>
     *
     * @return URI�i���K�\���j��InterceptorChainList�T�[�r�X�̃T�[�r�X���̃}�b�s���O
     */
    public Map getEnabledURIMapping();
    
    /**
     * �w�肵�����N�G�X�g�T�[�u���b�g�p�X�ɍ��v�����ꍇ�Ɏg�p����{@link InterceptorChainList}�T�[�r�X�̃T�[�r�X�����}�b�s���O����B<p>
     * ���N�G�X�g�T�[�u���b�g�p�X���w�肳�ꂽ�T�[�u���b�g�p�X�ɊY������ꍇ�ɁA���̃T�[�u���b�g�p�X�Ƀ}�b�s���O���ꂽInterceptorChainList�T�[�r�X���I�������B<br>
     *
     * @param mapping �T�[�u���b�g�p�X�i���K�\���j��InterceptorChainList�T�[�r�X�̃T�[�r�X���̃}�b�s���O�B�T�[�u���b�g�p�X=�T�[�r�X��
     */
    public void setEnabledPathMapping(Map mapping);
    
    /**
     * �w�肵�����N�G�X�g�T�[�u���b�g�p�X�ɍ��v�����ꍇ�Ɏg�p����{@link InterceptorChainList}�T�[�r�X�̃T�[�r�X���̃}�b�s���O���擾����B<p>
     *
     * @return �T�[�u���b�g�p�X�i���K�\���j��InterceptorChainList�T�[�r�X�̃T�[�r�X���̃}�b�s���O
     */
    public Map getEnabledPathMapping();
    
    /**
     * �w�肵��URL�ɍ��v���Ȃ��ꍇ�Ɏg�p����{@link InterceptorChainList}�T�[�r�X�̃T�[�r�X�����}�b�s���O����B<p>
     * URL���w�肳�ꂽURL�ɊY�����Ȃ��ꍇ�ɁA����URL�Ƀ}�b�s���O���ꂽInterceptorChainList�T�[�r�X���I�������B<br>
     *
     * @param mapping URL�i���K�\���j��InterceptorChainList�T�[�r�X�̃T�[�r�X���̃}�b�s���O�BURL=�T�[�r�X��
     */
    public void setDisabledURLMapping(Map mapping);
    
    /**
     * �w�肵��URL�ɍ��v���Ȃ��ꍇ�Ɏg�p����{@link InterceptorChainList}�T�[�r�X�̃T�[�r�X���̃}�b�s���O���擾����B<p>
     *
     * @return URL�i���K�\���j��InterceptorChainList�T�[�r�X�̃T�[�r�X���̃}�b�s���O
     */
    public Map getDisabledURLMapping();
    
    /**
     * �w�肵��URI�ɍ��v���Ȃ��ꍇ�Ɏg�p����{@link InterceptorChainList}�T�[�r�X�̃T�[�r�X�����}�b�s���O����B<p>
     * URI���w�肳�ꂽURI�ɊY�����Ȃ��ꍇ�ɁA����URI�Ƀ}�b�s���O���ꂽInterceptorChainList�T�[�r�X���I�������B<br>
     *
     * @param mapping URI�i���K�\���j��InterceptorChainList�T�[�r�X�̃T�[�r�X���̃}�b�s���O�BURI=�T�[�r�X��
     */
    public void setDisabledURIMapping(Map mapping);
    
    /**
     * �w�肵��URI�ɍ��v���Ȃ��ꍇ�Ɏg�p����{@link InterceptorChainList}�T�[�r�X�̃T�[�r�X���̃}�b�s���O���擾����B<p>
     *
     * @return URI�i���K�\���j��InterceptorChainList�T�[�r�X�̃T�[�r�X���̃}�b�s���O
     */
    public Map getDisabledURIMapping();
    
    /**
     * �w�肵�����N�G�X�g�T�[�u���b�g�p�X�ɍ��v���Ȃ��ꍇ�Ɏg�p����{@link InterceptorChainList}�T�[�r�X�̃T�[�r�X�����}�b�s���O����B<p>
     * ���N�G�X�g�T�[�u���b�g�p�X���w�肳�ꂽ�T�[�u���b�g�p�X�ɊY�����Ȃ��ꍇ�ɁA���̃T�[�u���b�g�p�X�Ƀ}�b�s���O���ꂽInterceptorChainList�T�[�r�X���I�������B<br>
     *
     * @param mapping �T�[�u���b�g�p�X�i���K�\���j��InterceptorChainList�T�[�r�X�̃T�[�r�X���̃}�b�s���O�B�T�[�u���b�g�p�X=�T�[�r�X��
     */
    public void setDisabledPathMapping(Map mapping);
    
    /**
     * �w�肵�����N�G�X�g�T�[�u���b�g�p�X�ɍ��v���Ȃ��ꍇ�Ɏg�p����{@link InterceptorChainList}�T�[�r�X�̃T�[�r�X���̃}�b�s���O���擾����B<p>
     *
     * @return �T�[�u���b�g�p�X�i���K�\���j��InterceptorChainList�T�[�r�X�̃T�[�r�X���̃}�b�s���O
     */
    public Map getDisabledPathMapping();
    
    /**
     * �Y������{@link InterceptorChainList}�����݂��Ȃ��ꍇ�ɑI�������InterceptorChainList�T�[�r�X�̃T�[�r�X����ݒ肷��B<p>
     *
     * @param name InterceptorChainList�T�[�r�X�̃T�[�r�X��
     */
    public void setDefaultInterceptorChainListServiceName(ServiceName name);
    
    /**
     * �Y������{@link InterceptorChainList}�����݂��Ȃ��ꍇ�ɑI�������InterceptorChainList�T�[�r�X�̃T�[�r�X�����擾����B<p>
     *
     * @return InterceptorChainList�T�[�r�X�̃T�[�r�X��
     */
    public ServiceName getDefaultInterceptorChainListServiceName();
}
