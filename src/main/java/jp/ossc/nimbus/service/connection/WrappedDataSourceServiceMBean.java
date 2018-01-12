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
package jp.ossc.nimbus.service.connection;

import java.util.Map;

import jp.ossc.nimbus.core.*;

/**
 * {@link WrappedDataSourceService}��MBean�C���^�t�F�[�X<p>
 * 
 * @author M.Takata
 * @see WrappedDataSourceService
 */
public interface WrappedDataSourceServiceMBean extends ServiceBaseMBean{
    
    /**
     * ���b�v����DataSource��JNDI����ݒ肷��B<p>
     *
     * @param name JNDI��
     */
    public void setSourceJNDIName(String name);
    
    /**
     * ���b�v����DataSource��JNDI�����擾����B<p>
     *
     * @return JNDI��
     */
    public String getSourceJNDIName();
    
    /**
     * ���b�v���ꂽDataSource��JNDI����ݒ肷��B<p>
     *
     * @param name JNDI��
     */
    public void setWrappedJNDIName(String name);
    
    /**
     * ���b�v���ꂽDataSource��JNDI�����擾����B<p>
     *
     * @return JNDI��
     */
    public String getWrappedJNDIName();
    
    /**
     * JNDI��{@link jp.ossc.nimbus.service.repository.Repository}�T�[�r�X�̃T�[�r�X����ݒ肷��B<p>
     *
     * @param name Repository�T�[�r�X�̃T�[�r�X��
     */
    public void setJNDIRepositoryServiceName(ServiceName name);
    
    /**
     * JNDI��{@link jp.ossc.nimbus.service.repository.Repository}�T�[�r�X�̃T�[�r�X�����擾����B<p>
     *
     * @return Repository�T�[�r�X�̃T�[�r�X��
     */
    public ServiceName getJNDIRepositoryServiceName();
    
    /**
     * {@link java.sql.Connection}�����b�v����N���X�̃N���X����ݒ肷��B<p>
     * �����Ŏw��ł���N���X�́Ajava.sql.Connection�C���^�t�F�[�X���������Ă���A������java.sql.Connection�����R���X�g���N�^���������Ă���N���X�ł���B<br>
     *
     * @param className �N���X��
     */
    public void setConnectionWrapperClassName(String className);
    
    /**
     * {@link java.sql.Connection}�����b�v����N���X�̃N���X�����擾����B<p>
     *
     * @return �N���X��
     */
    public String getConnectionWrapperClassName();
    
    /**
     * {@link java.sql.Connection}�����b�v����N���X�̃C���X�^���X�ɐݒ肷��v���p�e�B��ݒ肷��B<p>
     * Connection�����b�v����N���X�̃C���X�^���X�́A�w�肳�ꂽ�}�b�v�̃L�[���ɊY������setter���g���āA�L�[���ɊY������l��ݒ肷��B<br>
     *
     * @param prop Connection�����b�v����N���X�̃C���X�^���X�ɐݒ肷��v���p�e�B�}�b�v
     */
    public void setConnectionWrapperProperties(Map prop);
    
    /**
     * {@link java.sql.Connection}�����b�v����N���X�̃C���X�^���X�ɐݒ肷��v���p�e�B���擾����B<p>
     *
     * @return Connection�����b�v����N���X�̃C���X�^���X�ɐݒ肷��v���p�e�B�}�b�v
     */
    public Map getConnectionWrapperProperties();
}