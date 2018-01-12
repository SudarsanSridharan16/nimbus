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

import jp.ossc.nimbus.core.*;

/**
 * {@link MBeanServerRepositoryService}��MBean�C���^�t�F�[�X�B<p>
 *
 * @author M.Takata
 */
public interface MBeanServerRepositoryServiceMBean
 extends ServiceBaseMBean, Repository{
    
    /**
     * JMX�T�[�o�̃h���C������ݒ肷��B<p>
     * javax.management.MBeanServerFactory.findMBeanServer(String)�̈����Ƃ��Ďg�p����B�f�t�H���g�́Anull�B<br>
     *
     * @param domain JMX�T�[�o�̃h���C����
     */
    public void setMBeanServerDomain(String domain);
    
    /**
     * JMX�T�[�o�̃h���C�������擾����B<p>
     *
     * @return JMX�T�[�o�̃h���C����
     */
    public String getMBeanServerDomain();
    
    /**
     * JMX�T�[�o�̃f�t�H���g�h���C������ݒ肷��B<p>
     * javax.management.MBeanServer.getDefaultDomain()�Ɣ�r���āAJMX�T�[�o����肷��B�f�t�H���g�́Anull�B<br>
     *
     * @param domain JMX�T�[�o�̃f�t�H���g�h���C����
     */
    public void setMBeanServerDefaultDomain(String domain);
    
    /**
     * JMX�T�[�o�̃f�t�H���g�h���C�������擾����B<p>
     *
     * @return JMX�T�[�o�̃f�t�H���g�h���C����
     */
    public String getMBeanServerDefaultDomain();
    
    /**
     * JMX�T�[�o���X�g�̃C���f�b�N�X��ݒ肷��B<p>
     * javax.management.MBeanServerFactory.findMBeanServer(String)�̖߂�l�ƂȂ�List�̃C���f�b�N�X���w�肷��B�f�t�H���g�́A0�B<br>
     *
     * @param index JMX�T�[�o���X�g�̃C���f�b�N�X
     */
    public void setMBeanServerIndex(int index);
    
    /**
     * JMX�T�[�o���X�g�̃C���f�b�N�X���擾����B<p>
     *
     * @return JMX�T�[�o���X�g�̃C���f�b�N�X
     */
    public int getMBeanServerIndex();
    
    /**
     * MBean��JMX�T�[�o�ɓo�^����ۂ̃h���C������ݒ肷��B<p>
     * �f�t�H���g�́A���̃T�[�r�X���o�^����Ă���}�l�[�W�����B<br>
     *
     * @param domain �h���C����
     */
    public void setObjectNameDomain(String domain);
    
    /**
     * MBean��JMX�T�[�o�ɓo�^����ۂ̃h���C�������擾����B<p>
     *
     * @return �h���C����
     */
    public String getObjectNameDomain();
    
    /**
     * JMX�T�[�o��������Ȃ����ɁAJMX�T�[�o�𐶐����邩�ǂ�����ݒ肷��B<p>
     * �f�t�H���g�́Afalse�Ő������Ȃ��B<br>
     *
     * @param isCreate ��������ꍇ�́Atrue
     */
    public void setCreateMBeanServer(boolean isCreate);
    
    /**
     * JMX�T�[�o��������Ȃ����ɁAJMX�T�[�o�𐶐����邩�ǂ����𔻒肷��B<p>
     *
     * @return true�̏ꍇ�́A��������
     */
    public boolean isCreateMBeanServer();
}
