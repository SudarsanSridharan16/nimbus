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
package jp.ossc.nimbus.service.log;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogConfigurationException;

/**
 * Jakarta Commons Logging�p�̃��O�t�@�N�g���C���^�t�F�[�X�B<p>
 * 
 * @author M.Takata
 */
public interface CommonsLogFactory{
    
    /**
     * �����Ŏw�肵���N���X�I�u�W�F�N�g�Ɋ֘A�t����{@link Log}�C���X�^���X���擾����B<p>
     *
     * @param clazz �擾����Log�C���X�^���X�����ʂ���L�[�ƂȂ�N���X�I�u�W�F�N�g
     * @return �����Ŏw�肵���N���X�I�u�W�F�N�g�Ɋ֘A�t����{@link Log}�C���X�^���X
     * @exception LogConfigurationException Log�C���X�^���X�̍쐬�Ɏ��s�����ꍇ
     */
    public Log getInstance(Class clazz) throws LogConfigurationException;
    
    /**
     * �����Ŏw�肵�����O�Ɋ֘A�t����{@link Log}�C���X�^���X���擾����B<p>
     *
     * @param name �擾����Log�C���X�^���X�����ʂ��閼�O
     * @return �����Ŏw�肵�����O�Ɋ֘A�t����{@link Log}�C���X�^���X
     * @exception LogConfigurationException Log�C���X�^���X�̍쐬�Ɏ��s�����ꍇ
     */
    public Log getInstance(String name) throws LogConfigurationException;
    
    /**
     * �쐬����{@link Log}�C���X�^���X���J������B<p>
     */
    public void release();
    
    /**
     * �����l���擾����B<p>
     * "commons-logging.properties"�Őݒ肵���v���p�e�B�������Ƃ��Ċi�[�����B<p>
     *
     * @param name ������
     * @return �����l
     * @see #getAttributeNames()
     * @see #removeAttribute(String)
     * @see #setAttribute(String, Object)
     */
    public Object getAttribute(String name);
    
    /**
     * �������̔z����擾����B<p>
     * "commons-logging.properties"�Őݒ肵���v���p�e�B�������Ƃ��Ċi�[�����B<p>
     *
     * @return �������̔z��
     * @see #getAttribute(String)
     * @see #removeAttribute(String)
     * @see #setAttribute(String, Object)
     */
    public String[] getAttributeNames();
    
    /**
     * �������폜����B<p>
     * "commons-logging.properties"�Őݒ肵���v���p�e�B�������Ƃ��Ċi�[�����B<p>
     *
     * @param name ������
     * @see #getAttribute(String)
     * @see #getAttributeNames()
     * @see #setAttribute(String, Object)
     */
    public void removeAttribute(String name);
    
    /**
     * ������ݒ肷��B<p>
     * "commons-logging.properties"�Őݒ肵���v���p�e�B�������Ƃ��Ċi�[�����B<p>
     *
     * @param name ������
     * @param value �����l
     * @see #getAttribute(String)
     * @see #getAttributeNames()
     * @see #removeAttribute(String)
     */
    public void setAttribute(String name, Object value);
}
