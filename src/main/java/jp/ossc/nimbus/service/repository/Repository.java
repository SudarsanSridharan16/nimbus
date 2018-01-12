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
package jp.ossc.nimbus.service.repository;

import java.util.*;

/**
 * ���|�W�g���C���^�t�F�[�X�B<p>
 * �I�u�W�F�N�g��o�^���郊�|�W�g���Ƃ��ĕK�v�ȃC���^�t�F�[�X���`����B<br>
 *
 * @author M.Takata
 */
public interface Repository{
    
    /**
     * �w�肵�����O�̃I�u�W�F�N�g�����|�W�g��������擾����B<p>
     *
     * @param name �o�^��
     * @return �o�^���ꂽ�I�u�W�F�N�g
     */
    public Object get(String name);
    
    /**
     * �w�肵�����O�̃I�u�W�F�N�g�����|�W�g���ɓo�^����B<p>
     *
     * @param name �o�^��
     * @param obj �o�^����I�u�W�F�N�g
     * @return �o�^���ꂽ�ꍇtrue
     */
    public boolean register(String name, Object obj);
    
    /**
     * �w�肵�����O�̃I�u�W�F�N�g�����|�W�g��������폜����B<p>
     *
     * @param name �o�^��
     * @return �폜���ꂽ�ꍇtrue
     */
    public boolean unregister(String name);
    
    /**
     * �w�肵�����O�̃I�u�W�F�N�g�����|�W�g�����ɓo�^����Ă��邩���ׂ�B<p>
     *
     * @param name �o�^��
     * @return �o�^����Ă���ꍇtrue
     */
    public boolean isRegistered(String name);
    
    /**
     * ���|�W�g���ɓo�^����Ă���I�u�W�F�N�g�̖��O�̏W�����擾����B<p>
     *
     * @return �o�^����Ă���I�u�W�F�N�g�̖��O�̏W��
     */
    public Set nameSet();
    
    /**
     * ���|�W�g���ɓo�^����Ă���I�u�W�F�N�g�̏W�����擾����B<p>
     *
     * @return �o�^����Ă���I�u�W�F�N�g�̏W��
     */
    public Set registeredSet();
}