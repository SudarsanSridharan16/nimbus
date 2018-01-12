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
package jp.ossc.nimbus.service.proxy.invoker;

import java.util.*;

import jp.ossc.nimbus.core.*;

/**
 * {@link JMXClientRMICallInvokerService}��MBean�C���^�t�F�[�X<p>
 * 
 * @author M.Takata
 * @see JMXClientRMICallInvokerService
 */
public interface JMXClientRMICallInvokerServiceMBean
 extends ServiceBaseMBean{
    
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
     * JMX API �R�l�N�^�T�[�o�[�̃A�h���X��ݒ肷��B<p>
     *
     * @param url JMX API �R�l�N�^�T�[�o�[�̃A�h���X
     */
    public void setServiceURL(String url);
    
    /**
     * JMX API �R�l�N�^�T�[�o�[�̃A�h���X���擾����B<p>
     *
     * @return JMX API �R�l�N�^�T�[�o�[�̃A�h���X
     */
    public String getServiceURL();
    
    /**
     * JMX API �R�l�N�^�ڑ��̊m�����@������t���鑮���̃Z�b�g��ݒ肷��B<p>
     *
     * @param env �����̃Z�b�g
     */
    public void setJMXConnectorEnvironment(Map env);
    
    /**
     * JMX API �R�l�N�^�ڑ��̊m�����@������t���鑮���̃Z�b�g���擾����B<p>
     *
     * @return �����̃Z�b�g
     */
    public Map getJMXConnectorEnvironment();
    
    /**
     * �Ăяo��MBean��JMX�I�u�W�F�N�g����ݒ肷��B<p>
     *
     * @param name JMX�I�u�W�F�N�g��
     */
    public void setObjectName(String name);
    
    /**
     * �Ăяo��MBean��JMX�I�u�W�F�N�g�����擾����B<p>
     *
     * @return JMX�I�u�W�F�N�g��
     */
    public String getObjectName();
    
    /**
     * �Ăяo��MBean��JMX�h���C������ݒ肷��B<p>
     * {@link #setObjectName(String)}��ݒ肵�Ă���ꍇ�́A�s�v�B<br>
     *
     * @param domain JMX�h���C����
     */
    public void setObjectNameDomain(String domain);
    
    /**
     * �Ăяo��MBean��JMX�h���C�������擾����B<p>
     *
     * @return JMX�h���C����
     */
    public String getObjectNameDomain();
    
    /**
     * �Ăяo��MBean��JMX�I�u�W�F�N�g���̃v���p�e�B��ݒ肷��B<p>
     * {@link #setObjectName(String)}��ݒ肵�Ă���ꍇ�́A�s�v�B<br>
     *
     * @param prop JMX�I�u�W�F�N�g���̃v���p�e�B
     */
    public void setObjectNameProperties(Properties prop);
    
    /**
     * �Ăяo��MBean��JMX�I�u�W�F�N�g���̃v���p�e�B���擾����B<p>
     *
     * @return JMX�I�u�W�F�N�g���̃v���p�e�B
     */
    public Properties getObjectNameProperties();
    
    /**
     * �Ăяo��MBean��JMX�I�u�W�F�N�g������肷�邽�߂̃N�G���[��ݒ肷��B<p>
     * 
     * @param query �N�G���[
     */
    public void setMBeanQuery(String query);
    
    /**
     * �Ăяo��MBean��JMX�I�u�W�F�N�g������肷�邽�߂̃N�G���[���擾����B<p>
     * 
     * @return �N�G���[
     */
    public String getMBeanQuery();
    
    /**
     * �Ăяo��MBean��JMX�I�u�W�F�N�g������肷�邽�߂̐��K�\����ݒ肷��B<p>
     * 
     * @param regex ���K�\��
     */
    public void setObjectNameRegex(String regex);
    
    /**
     * �Ăяo��MBean��JMX�I�u�W�F�N�g������肷�邽�߂̐��K�\�����擾����B<p>
     * 
     * @return ���K�\��
     */
    public String getObjectNameRegex();

}
