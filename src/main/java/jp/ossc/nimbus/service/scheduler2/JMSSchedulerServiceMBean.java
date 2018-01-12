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
package jp.ossc.nimbus.service.scheduler2;

import jp.ossc.nimbus.core.*;

/**
 * {@link JMSSchedulerService}��MBean�C���^�t�F�[�X�B<p>
 * 
 * @author M.Takata
 */
public interface JMSSchedulerServiceMBean
 extends AbstractSchedulerServiceMBean{
    
    /**
     * JMS��Session�𐶐�����{@link jp.ossc.nimbus.service.jms.JMSSessionFactory JMSSessionFactory}�T�[�r�X�̃T�[�r�X����ݒ肷��B<p>
     *
     * @param name JMSSessionFactory�T�[�r�X�̃T�[�r�X��
     */
    public void setJMSSessionFactoryServiceName(ServiceName name);
    
    /**
     * JMS��Session�𐶐�����{@link jp.ossc.nimbus.service.jms.JMSSessionFactory JMSSessionFactory}�T�[�r�X�̃T�[�r�X�����擾����B<p>
     *
     * @return JMSSessionFactory�T�[�r�X�̃T�[�r�X��
     */
    public ServiceName getJMSSessionFactoryServiceName();
    
    /**
     * JMS��MessageProducer�𐶐�����{@link jp.ossc.nimbus.service.jms.JMSMessageProducerFactory JMSMessageProducerFactory}�T�[�r�X�̃T�[�r�X����ݒ肷��B<p>
     *
     * @param name JMSMessageProducerFactory�T�[�r�X�̃T�[�r�X��
     */
    public void setJMSMessageProducerFactoryServiceName(ServiceName name);
    
    /**
     * JMS��MessageProducer�𐶐�����{@link jp.ossc.nimbus.service.jms.JMSMessageProducerFactory JMSMessageProducerFactory}�T�[�r�X�̃T�[�r�X�����擾����B<p>
     *
     * @return JMSMessageProducerFactory�T�[�r�X�̃T�[�r�X��
     */
    public ServiceName getJMSMessageProducerFactoryServiceName();
    
    /**
     * JMS��MessageConsumer�𐶐�����{@link jp.ossc.nimbus.service.jms.JMSMessageConsumerFactory JMSMessageConsumerFactory}�T�[�r�X�̃T�[�r�X����ݒ肷��B<p>
     *
     * @param name JMSMessageConsumerFactory�T�[�r�X�̃T�[�r�X��
     */
    public void setJMSMessageConsumerFactoryServiceName(ServiceName name);
    
    /**
     * JMS��MessageConsumer�𐶐�����{@link jp.ossc.nimbus.service.jms.JMSMessageConsumerFactory JMSMessageConsumerFactory}�T�[�r�X�̃T�[�r�X�����擾����B<p>
     *
     * @return JMSMessageConsumerFactory�T�[�r�X�̃T�[�r�X��
     */
    public ServiceName getJMSMessageConsumerFactoryServiceName();
    
    /**
     * JMS�̈��悩�瓊�����ꂽ�X�P�W���[�������o��MessageConsumer�X���b�h�̐���ݒ肷��B<p>
     * �f�t�H���g�́A1�B<br>
     *
     * @param size MessageConsumer�X���b�h�̐�
     */
    public void setMessageConsumerSize(int size);
    
    /**
     * JMS�̈��悩�瓊�����ꂽ�X�P�W���[�������o��MessageConsumer�X���b�h�̐����擾����B<p>
     *
     * @return MessageConsumer�X���b�h�̐�
     */
    public int getMessageConsumerSize();
    
    /**
     * �X�P�W���[����JMS�̈���ɓ������鎞�̔z�M���[�h��ݒ肷��B<p>
     * �f�t�H���g�́A{@link javax.jms.Message#DEFAULT_DELIVERY_MODE}�B<br>
     *
     * @param mode �z�M���[�h
     */
    public void setDeliveryMode(int mode);
    
    /**
     * �X�P�W���[����JMS�̈���ɓ������鎞�̔z�M���[�h���擾����B<p>
     *
     * @return �z�M���[�h
     */
    public int getDeliveryMode();
    
    /**
     * �X�P�W���[����JMS�̈���ɓ������鎞�̗D�揇�ʂ�ݒ肷��B<p>
     * �f�t�H���g�́A{@link javax.jms.Message#DEFAULT_PRIORITY}�B<br>
     *
     * @param priority �D�揇��
     */
    public void setPriority(int priority);
    
    /**
     * �X�P�W���[����JMS�̈���ɓ������鎞�̗D�揇�ʂ��擾����B<p>
     *
     * @return �D�揇��
     */
    public int getPriority();
    
    /**
     * �X�P�W���[����JMS�̈���ɓ������鎞�̐�������[ms]��ݒ肷��B<p>
     * �f�t�H���g�́A{@link javax.jms.Message#DEFAULT_TIME_TO_LIVE}�B<br>
     *
     * @param millis ��������[ms]
     */
    public void setTimeToLive(long millis);
    
    /**
     * �X�P�W���[����JMS�̈���ɓ������鎞�̐�������[ms]���擾����B<p>
     *
     * @return ��������[ms]
     */
    public long getTimeToLive();
}
