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

import jp.ossc.nimbus.core.*;

/**
 * {@link ServletFilterInterceptorService}��MBean�C���^�t�F�[�X�B<p>
 * 
 * @author M.Takata
 * @see ServletFilterInterceptorService
 */
public interface ServletFilterInterceptorServiceMBean
 extends ServiceBaseMBean{
    
    /**
     * ���̃C���^�[�Z�v�^��L���ɂ���URL��ݒ肷��B<p>
     * ���N�G�X�gURL���w�肳�ꂽURL�ɊY������ꍇ�����A�C���^�[�Z�v�^�̏������s����B<br>
     * �ݒ肵�Ȃ��ꍇ�́A�S�Ă�URL�ɑ΂��ėL���ɂȂ�B<br>
     *
     * @param urls ���̃C���^�[�Z�v�^��L���ɂ���URL�i���K�\���j�̔z��
     */
    public void setEnabledURLs(String[] urls);
    
    /**
     * ���̃C���^�[�Z�v�^��L���ɂ���URL���擾����B<p>
     *
     * @return ���̃C���^�[�Z�v�^��L���ɂ���URL�i���K�\���j�̔z��
     */
    public String[] getEnabledURLs();
    
    /**
     * ���̃C���^�[�Z�v�^�𖳌��ɂ���URL��ݒ肷��B<p>
     * ���N�G�X�gURL���w�肳�ꂽURL�ɊY������ꍇ�����A�C���^�[�Z�v�^�̏������s���Ȃ��B<br>
     * �ݒ肵�Ȃ��ꍇ�́A�S�Ă�URL�ɑ΂��ėL���ɂȂ�B<br>
     *
     * @param urls ���̃C���^�[�Z�v�^�𖳌��ɂ���URL�i���K�\���j�̔z��
     */
    public void setDisabledURLs(String[] urls);
    
    /**
     * ���̃C���^�[�Z�v�^�𖳌��ɂ���URL���擾����B<p>
     *
     * @return ���̃C���^�[�Z�v�^�𖳌��ɂ���URL�i���K�\���j�̔z��
     */
    public String[] getDisabledURLs();
    
    /**
     * ���̃C���^�[�Z�v�^��L���ɂ���URI��ݒ肷��B<p>
     * ���N�G�X�gURI���w�肳�ꂽURI�ɊY������ꍇ�����A�C���^�[�Z�v�^�̏������s����B<br>
     * �ݒ肵�Ȃ��ꍇ�́A�S�Ă�URI�ɑ΂��ėL���ɂȂ�B<br>
     *
     * @param uris ���̃C���^�[�Z�v�^��L���ɂ���URI�i���K�\���j�̔z��
     */
    public void setEnabledURIs(String[] uris);
    
    /**
     * ���̃C���^�[�Z�v�^��L���ɂ���URI���擾����B<p>
     *
     * @return ���̃C���^�[�Z�v�^��L���ɂ���URI�i���K�\���j�̔z��
     */
    public String[] getEnabledURIs();
    
    /**
     * ���̃C���^�[�Z�v�^�𖳌��ɂ���URI��ݒ肷��B<p>
     * ���N�G�X�gURI���w�肳�ꂽURI�ɊY������ꍇ�����A�C���^�[�Z�v�^�̏������s���Ȃ��B<br>
     * �ݒ肵�Ȃ��ꍇ�́A�S�Ă�URI�ɑ΂��ėL���ɂȂ�B<br>
     *
     * @param uris ���̃C���^�[�Z�v�^�𖳌��ɂ���URI�i���K�\���j�̔z��
     */
    public void setDisabledURIs(String[] uris);
    
    /**
     * ���̃C���^�[�Z�v�^�𖳌��ɂ���URI���擾����B<p>
     *
     * @return ���̃C���^�[�Z�v�^�𖳌��ɂ���URI�i���K�\���j�̔z��
     */
    public String[] getDisabledURIs();
    
    /**
     * ���̃C���^�[�Z�v�^��L���ɂ���T�[�u���b�g�p�X��ݒ肷��B<p>
     * ���N�G�X�g�T�[�u���b�g�p�X���w�肳�ꂽ�T�[�u���b�g�p�X�ɊY������ꍇ�����A�C���^�[�Z�v�^�̏������s����B<br>
     * �ݒ肵�Ȃ��ꍇ�́A�S�ẴT�[�u���b�g�p�X�ɑ΂��ėL���ɂȂ�B<br>
     *
     * @param paths ���̃C���^�[�Z�v�^��L���ɂ���T�[�u���b�g�p�X�i���K�\���j�̔z��
     */
    public void setEnabledPaths(String[] paths);
    
    /**
     * ���̃C���^�[�Z�v�^��L���ɂ���T�[�u���b�g�p�X���擾����B<p>
     *
     * @return ���̃C���^�[�Z�v�^��L���ɂ���T�[�u���b�g�p�X�i���K�\���j�̔z��
     */
    public String[] getEnabledPaths();
    
    /**
     * ���̃C���^�[�Z�v�^�𖳌��ɂ���T�[�u���b�g�p�X��ݒ肷��B<p>
     * ���N�G�X�g�T�[�u���b�g�p�X���w�肳�ꂽ�T�[�u���b�g�p�X�ɊY������ꍇ�����A�C���^�[�Z�v�^�̏������s���Ȃ��B<br>
     * �ݒ肵�Ȃ��ꍇ�́A�S�ẴT�[�u���b�g�p�X�ɑ΂��ėL���ɂȂ�B<br>
     *
     * @param paths ���̃C���^�[�Z�v�^�𖳌��ɂ���T�[�u���b�g�p�X�i���K�\���j�̔z��
     */
    public void setDisabledPaths(String[] paths);
    
    /**
     * ���̃C���^�[�Z�v�^�𖳌��ɂ���T�[�u���b�g�p�X���擾����B<p>
     *
     * @return ���̃C���^�[�Z�v�^�𖳌��ɂ���T�[�u���b�g�p�X�i���K�\���j�̔z��
     */
    public String[] getDisabledPaths();
}
