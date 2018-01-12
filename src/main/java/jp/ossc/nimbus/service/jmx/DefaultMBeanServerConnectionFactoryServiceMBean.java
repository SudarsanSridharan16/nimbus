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
package jp.ossc.nimbus.service.jmx;

import java.util.Map;

import jp.ossc.nimbus.core.ServiceBaseMBean;
import jp.ossc.nimbus.core.ServiceName;

/**
 * {@link DefaultMBeanServerConnectionFactoryService}��MBean�C���^�t�F�[�X�B<p>
 * 
 * @author M.Takata
 */
public interface DefaultMBeanServerConnectionFactoryServiceMBean extends ServiceBaseMBean{
    
    /**
     * javax.management.MBeanServerConnection��JNDI���̃f�t�H���g�l�B<p>
     */
    public static final String DEFAULT_JMX_RMI_ADAPTOR_NAME = "jmx/invoker/RMIAdaptor";
    
    /**
     * javax.management.MBeanServerConnection��JNDI����lookup����{@link jp.ossc.nimbus.service.jndi.JndiFinder JndiFinder}�T�[�r�X�̃T�[�r�X����ݒ肷��B<p>
     *
     * @param name JndiFinder�T�[�r�X�̃T�[�r�X��
     */
    public void setJndiFinderServiceName(ServiceName name);
    
    /**
     * javax.management.MBeanServerConnection��JNDI����lookup����{@link jp.ossc.nimbus.service.jndi.JndiFinder JndiFinder}�T�[�r�X�̃T�[�r�X�����擾����B<p>
     *
     * @return JndiFinder�T�[�r�X�̃T�[�r�X��
     */
    public ServiceName getJndiFinderServiceName();
    
    /**
     * javax.management.MBeanServerConnection��JNDI����ݒ肷��B<p>
     * �f�t�H���g�́A{@link #DEFAULT_JMX_RMI_ADAPTOR_NAME}�B<br>
     *
     * @param name javax.management.MBeanServerConnection��JNDI��
     */
    public void setRMIAdaptorName(String name);
    
    /**
     * javax.management.MBeanServerConnection��JNDI�����擾����B<p>
     *
     * @return javax.management.MBeanServerConnection��JNDI��
     */
    public String getRMIAdaptorName();
    
    /**
     * �ڑ�����JMX�T�[�o�̃T�[�r�XURL��ݒ肷��B<p>
     *
     * @param url �T�[�r�XURL
     */
    public void setServiceURL(String url);
    
    /**
     * �ڑ�����JMX�T�[�o�̃T�[�r�XURL���擾����B<p>
     *
     * @return �T�[�r�XURL
     */
    public String getServiceURL();
    
    /**
     * �ڑ�����JMX�T�[�o�̐ڑ����ϐ���ݒ肷��B<p>
     *
     * @param env �ڑ����ϐ�
     */
    public void setJMXConnectorEnvironment(Map env);
    
    /**
     * �ڑ�����JMX�T�[�o�̐ڑ����ϐ����擾����B<p>
     *
     * @return �ڑ����ϐ�
     */
    public Map getJMXConnectorEnvironment();
    
    /**
     * �T�[�r�XURL���g���Đڑ�����ꍇ�ɁA�T�[�r�X�̊J�n���ɐڑ����s�����ǂ�����ݒ肷��B<p>
     * �f�t�H���g�́Afalse�ŁA����̐ڑ��擾���ɁA�ڑ�����B<br>
     *
     * @param isConnect �T�[�r�X�̊J�n���ɐڑ����s���ꍇ�Atrue
     */
    public void setConnectOnStart(boolean isConnect);
    
    /**
     * �T�[�r�XURL���g���Đڑ�����ꍇ�ɁA�T�[�r�X�̊J�n���ɐڑ����s�����ǂ����𔻒肷��B<p>
     *
     * @return true�̏ꍇ�A�T�[�r�X�̊J�n���ɐڑ����s��
     */
    public boolean isConnectOnStart();
    
    /**
     * �T�[�r�XURL���g���Đڑ�����ꍇ�ɁA{@link DefaultMBeanServerConnectionFactoryService#getConnection() getConnection()}�Ăяo�����ɁA����V�����ڑ����쐬���邩�ǂ�����ݒ肷��B<p>
     * �f�t�H���g�́Atrue�B<br>
     *
     * @param isNew ����V�����ڑ����쐬����ꍇ�Atrue
     */
    public void setNewConnection(boolean isNew);
    
    /**
     * �T�[�r�XURL���g���Đڑ�����ꍇ�ɁA{@link DefaultMBeanServerConnectionFactoryService#getConnection() getConnection()}�Ăяo�����ɁA����V�����ڑ����쐬���邩�ǂ����𔻒肷��B<p>
     *
     * @return true�̏ꍇ�A����V�����ڑ����쐬����
     */
    public boolean isNewConnection();
}
