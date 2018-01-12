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
package jp.ossc.nimbus.service.publish;

import java.util.Map;
import java.util.List;

import jp.ossc.nimbus.core.ServiceBaseMBean;
import jp.ossc.nimbus.core.ServiceName;

/**
 * {@link GroupConnectionFactoryService}��MBean�C���^�t�F�[�X<p>
 * 
 * @author M.Takata
 * @see GroupConnectionFactoryService
 */
public interface GroupConnectionFactoryServiceMBean extends ServiceBaseMBean{
    
    /**
     * �O���[�s���O�����R�l�N�V�����𐶐�����{@link ClientConnectionFactory}���o�C���h����JNDI����ݒ肷��B<p>
     * �f�t�H���g�́A{@link ClientConnectionFactory#DEFAULT_JNDI_NAME}�B<br>
     *
     * @param name JNDI��
     * @see ClientConnectionFactory#DEFAULT_JNDI_NAME
     */
    public void setJndiName(String name);
    
    /**
     * �O���[�s���O�����R�l�N�V�����𐶐�����{@link ClientConnectionFactory}���o�C���h����JNDI�����擾����B<p>
     *
     * @return JNDI��
     */
    public String getJndiName();
    
    /**
     * �O���[�s���O�����R�l�N�V�����𐶐�����{@link ClientConnectionFactory}���o�C���h����{@link jp.ossc.nimbus.service.repository.Repository Repository}�T�[�r�X�̃T�[�r�X����ݒ肷��B<p>
     *
     * @param name Repository�T�[�r�X�̃T�[�r�X��
     */
    public void setJndiRepositoryServiceName(ServiceName name);
    
    /**
     * �O���[�s���O�����R�l�N�V�����𐶐�����{@link ClientConnectionFactory}���o�C���h����{@link jp.ossc.nimbus.service.repository.Repository Repository}�T�[�r�X�̃T�[�r�X�����擾����B<p>
     *
     * @return Repository�T�[�r�X�̃T�[�r�X��
     */
    public ServiceName getJndiRepositoryServiceName();
    
    /**
     * �����[�g�I�u�W�F�N�g���Ăяo������M����|�[�g�ԍ���ݒ肷��B<p>
     * �w�肵�Ȃ��ꍇ�́A�����|�[�g���g�p�����B<br>
     *
     * @param port �|�[�g�ԍ�
     */
    public void setRMIPort(int port);
    
    /**
     * �����[�g�I�u�W�F�N�g���Ăяo������M����|�[�g�ԍ����擾����B<p>
     *
     * @return �|�[�g�ԍ�
     */
    public int getRMIPort();
    
    /**
     * �w�肳�ꂽ�T�u�W�F�N�g�Ƀ}�b�s���O����Ă���{@link GroupConnectionFactoryService.SubjectMapping SubjectMapping}�̃��X�g���擾����B<p>
     * 
     * @return SubjectMapping�̃��X�g
     */
    public List getSubjectMappings(String subject);
    
    /**
     * �T�u�W�F�N�g�̃}�b�s���O���擾����B<p>
     * 
     * @return �L�[���T�u�W�F�N�g�A�l��SubjectMapping�̃��X�g�ƂȂ�}�b�v
     */
    public Map getSubjectMappingMap();
}
