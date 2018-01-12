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
package jp.ossc.nimbus.service.soap;

import java.util.*;

import javax.xml.rpc.handler.HandlerInfo;

import jp.ossc.nimbus.core.ServiceBaseMBean;
import jp.ossc.nimbus.core.ServiceName;

/**
 * {@link PortFactoryService}��MBean�C���^�t�F�[�X�B<p>
 *
 * @author M.Takata
 */
public interface PortFactoryServiceMBean
 extends ServiceBaseMBean {
    
    /**
     * JAX-RPC�T�[�r�X�t�@�N�g������ݒ肷��B<p>
     * 
     * @return JAX-RPC�T�[�r�X�t�@�N�g����
     */
    public ServiceName getJaxRpcServiceFactoryName();

    /**
     * JAX-RPC�T�[�r�X�t�@�N�g������ݒ肷��B<p>
     * 
     * @param serviceName JAX-RPC�T�[�r�X�t�@�N�g����
     */
    public void setJaxRpcServiceFactoryName(ServiceName serviceName);

    /**
     * �|�[�g�G�C���A�X�v���p�e�B���擾����B<p>
     * 
     * @return �L�[��[�|�[�g�G�C���A�X��]�A�l��[�|�[�g��,�T�[�r�X�G���h�|�C���g�C���^�[�t�F�[�X��]�̃v���p�e�B
     */
    public Properties getPortAliasProp();
    
    /**
     * �|�[�g�G�C���A�X�v���p�e�B��ݒ肷��B<p>
     * 
     * @param prop �L�[��[�|�[�g�G�C���A�X��]�A�l��[�|�[�g��,�T�[�r�X�G���h�|�C���g�C���^�[�t�F�[�X��]�̃v���p�e�B
     */
    public void setPortAliasProp(Properties prop);
    
    /**
     * �n���h�����̃��X�g��ݒ肷��B<p>
     *
     * @param infos �n���h�����̃��X�g
     */
    public void setHandlerInfos(List infos);
    
    /**
     * �n���h������ǉ�����B<p>
     *
     * @param info �n���h�����
     */
    public void addHandlerInfo(HandlerInfo info);
    
    /**
     * �n���h�����̃��X�g���擾����B<p>
     *
     * @return �n���h�����̃��X�g
     */
    public List getHandlerInfos();
    
    /**
     * �n���h�������N���A����B<p>
     */
    public void clearHandlerInfos();
    
    /**
     * �X�^�u�ɑ΂��Đݒ肷��v���p�e�B��ݒ肷��B<p>
     *
     * @param name �v���p�e�B��
     * @param value �v���p�e�B�l
     */
    public void setStubProperty(String name, Object value);
    
    /**
     * �X�^�u�ɑ΂��Đݒ肷��v���p�e�B���擾����B<p>
     *
     * @param name �v���p�e�B��
     * @return �v���p�e�B�l
     */
    public Object getStubProperty(String name);
    
    /**
     * �X�^�u�ɑ΂��Đݒ肷��v���p�e�B�̃}�b�v���擾����B<p>
     *
     * @return �v���p�e�B�̃}�b�v
     */
    public Map getStubPropertyMap();
}
