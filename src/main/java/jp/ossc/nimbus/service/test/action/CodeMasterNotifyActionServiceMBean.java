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
package jp.ossc.nimbus.service.test.action;

import jp.ossc.nimbus.core.ServiceName;
import jp.ossc.nimbus.core.ServiceBaseMBean;

/**
 * {@link CodeMasterNotifyActionService}��MBean�C���^�t�F�[�X<p>
 * 
 * @author M.Takata
 * @see CodeMasterNotifyActionService
 */
public interface CodeMasterNotifyActionServiceMBean extends ServiceBaseMBean{
    
    /**
     * {@link jp.ossc.nimbus.service.jndi.JndiFinder JndiFinder}�T�[�r�X�̃T�[�r�X����ݒ肷��B<p>
     *
     * @param name JndiFinder�T�[�r�X�̃T�[�r�X��
     */
    public void setJndiFinderServiceName(ServiceName name);
    
    /**
     * {@link jp.ossc.nimbus.service.jndi.JndiFinder JndiFinder}�T�[�r�X�̃T�[�r�X�����擾����B<p>
     *
     * @return JndiFinder�T�[�r�X�̃T�[�r�X��
     */
    public ServiceName getJndiFinderServiceName();
    
    /**
     * {@link jp.ossc.nimbus.service.jms.JMSSessionFactory JMSSessionFactory}�T�[�r�X�̃T�[�r�X����ݒ肷��B<p>
     *
     * @param name JMSSessionFactory�T�[�r�X�̃T�[�r�X��
     */
    public void setJMSTopicSessionFactoryServiceName(ServiceName name);
    
    /**
     * {@link jp.ossc.nimbus.service.jms.JMSSessionFactory JMSSessionFactory}�T�[�r�X�̃T�[�r�X�����擾����B<p>
     *
     * @return JMSSessionFactory�T�[�r�X�̃T�[�r�X��
     */
    public ServiceName getJMSTopicSessionFactoryServiceName();
    
    /**
     * �X�V�ʒm�𑗐M���鈶��ƂȂ�JMS�g�s�b�N����ݒ肷��B<p>
     *
     * @param name JMS�g�s�b�N��
     */
    public void setTopicName(String name);
    
    /**
     * �X�V�ʒm�𑗐M���鈶��ƂȂ�JMS�g�s�b�N�����擾����B<p>
     *
     * @return JMS�g�s�b�N��
     */
    public String getTopicName();
    
    /**
     * {@link jp.ossc.nimbus.service.publish.ServerConnectionFactory ServerConnectionFactory}�T�[�r�X�̃T�[�r�X����ݒ肷��B<p>
     *
     * @param name ServerConnectionFactory�T�[�r�X�̃T�[�r�X��
     */
    public void setServerConnectionFactoryServiceName(ServiceName name);
    
    /**
     * {@link jp.ossc.nimbus.service.publish.ServerConnectionFactory ServerConnectionFactory}�T�[�r�X�̃T�[�r�X�����擾����B<p>
     *
     * @return ServerConnectionFactory�T�[�r�X�̃T�[�r�X��
     */
    public ServiceName getServerConnectionFactoryServiceName();
    
    /**
     * �X�V�ʒm�𑗐M����{@link jp.ossc.nimbus.service.publish.Message Message}�̈����ݒ肷��B<p>
     *
     * @param subject ����
     */
    public void setSubject(String subject);
    
    /**
     * �X�V�ʒm�𑗐M����{@link jp.ossc.nimbus.service.publish.Message Message}�̈�����擾����B<p>
     *
     * @return ����
     */
    public String getSubject();
    
    /**
     * {@link jp.ossc.nimbus.service.interpreter.Interpreter Interpreter}�T�[�r�X�̃T�[�r�X����ݒ肷��B<p>
     *
     * @param name Interpreter�T�[�r�X�̃T�[�r�X��
     */
    public void setInterpreterServiceName(ServiceName name);
    
    /**
     * {@link jp.ossc.nimbus.service.interpreter.Interpreter Interpreter}�T�[�r�X�̃T�[�r�X�����擾����B<p>
     *
     * @return Interpreter�T�[�r�X�̃T�[�r�X��
     */
    public ServiceName getInterpreterServiceName();
    
    /**
     * ���̃A�N�V�����̃��\�[�X��`���쐬����ۂ̃f�t�H���g�̑z��R�X�g��ݒ肷��B<p>
     * 
     * @param cost �z��R�X�g
     */
    public void setExpectedCost(double cost);
    
    /**
     * ���̃A�N�V�����̃��\�[�X��`���쐬����ۂ̃f�t�H���g�̑z��R�X�g���擾����B<p>
     * 
     * @return �z��R�X�g
     */
    public double getExpectedCost();

}
