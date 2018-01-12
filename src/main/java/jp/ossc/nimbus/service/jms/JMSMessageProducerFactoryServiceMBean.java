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
package jp.ossc.nimbus.service.jms;

import jp.ossc.nimbus.core.*;

/**
 * {@link JMSMessageProducerFactoryService}��MBean�C���^�t�F�[�X<p>
 * 
 * @author M.Takata
 * @see JMSMessageProducerFactoryService
 */
public interface JMSMessageProducerFactoryServiceMBean
 extends ServiceBaseMBean{
    
    /**
     * {@link JMSSessionFactory}�T�[�r�X�̃T�[�r�X����ݒ肷��B<p>
     * SessionCreate������true�̏ꍇ�A�T�[�r�X�̊J�n���ɁA�����Őݒ肳�ꂽJMSSessionFactory�T�[�r�X���g���āASession�𐶐����ێ�����B<br>
     *
     * @param name JMSSessionFactory�T�[�r�X�̃T�[�r�X��
     */
    public void setJMSSessionFactoryServiceName(ServiceName name);
    
    /**
     * {@link JMSSessionFactory}�T�[�r�X�̃T�[�r�X�����擾����B<p>
     *
     * @return JMSSessionFactory�T�[�r�X�̃T�[�r�X��
     */
    public ServiceName getJMSSessionFactoryServiceName();
    
    /**
     * {@link jp.ossc.nimbus.service.jndi.JndiFinder JndiFinder}�T�[�r�X�̃T�[�r�X����ݒ肷��B<p>
     * �����Őݒ肳�ꂽJndiFinder�T�[�r�X���g���āAJNDI�T�[�o����javax.jms.Destination��lookup����B<br>
     *
     * @param name JndiFinder�T�[�r�X�̃T�[�r�X��
     */
    public void setDestinationFinderServiceName(ServiceName name);
    
    /**
     * {@link jp.ossc.nimbus.service.jndi.JndiFinder JndiFinder}�T�[�r�X�̃T�[�r�X�����擾����B<p>
     *
     * @return JndiFinder�T�[�r�X�̃T�[�r�X��
     */
    public ServiceName getDestinationFinderServiceName();
    
    /**
     * ����ƂȂ�javax.jms.Destination��JNDI����ݒ肷��B<p>
     * DestinationFinderServiceName�����Őݒ肳�ꂽ{@link jp.ossc.nimbus.service.jndi.JndiFinder JndiFinder}�T�[�r�X���g���āA����ƂȂ�javax.jms.Destination���A�����Őݒ肳�ꂽJNDI����lookup����B<br>
     *
     * @param name ����ƂȂ�javax.jms.Destination��JNDI��
     */
    public void setDestinationName(String name);
    
    /**
     * ����ƂȂ�javax.jms.Destination��JNDI�����擾����B<p>
     *
     * @return ����ƂȂ�javax.jms.Destination��JNDI��
     */
    public String getDestinationName();
    
    /**
     * �T�[�r�X�̊J�n����Session�𐶐����ĕێ����邩�ǂ�����ݒ肷��B<p>
     * true��ݒ肷��ꍇ�AJMSSessionFactoryServiceName������ݒ肵�Ȃ���΂Ȃ�Ȃ��B<br>
     * �f�t�H���g�́Afalse�B<br>
     *
     * @param isCreate �T�[�r�X�̊J�n����Session�𐶐����ĕێ�����ꍇtrue
     */
    public void setSessionCreate(boolean isCreate);
    
    /**
     * �T�[�r�X�̊J�n����Session�𐶐����ĕێ����邩�ǂ����𔻒肷��B<p>
     *
     * @return true�̏ꍇ�A�T�[�r�X�̊J�n����Session�𐶐����ĕێ�����
     */
    public boolean isSessionCreate();
    
    /**
     * �T�[�r�X�̒�~����Session���N���[�Y���邩�ǂ�����ݒ肷��B<p>
     * �f�t�H���g�́Afalse�B<br>
     *
     * @param isClose �T�[�r�X�̒�~����Session���N���[�Y����ꍇtrue
     */
    public void setCloseSession(boolean isClose);
    
    /**
     * �T�[�r�X�̒�~����Session���N���[�Y���邩�ǂ����𔻒肷��B<p>
     *
     * @return true�̏ꍇ�A�T�[�r�X�̒�~����Session���N���[�Y����
     */
    public boolean isCloseSession();
    
    /**
     * JMS�̈���ɑ��M���鎞�̔z�M���[�h��ݒ肷��B<p>
     * �f�t�H���g�́A{@link javax.jms.Message#DEFAULT_DELIVERY_MODE}�B<br>
     *
     * @param mode �z�M���[�h
     */
    public void setDeliveryMode(int mode);
    
    /**
     * JMS�̈���ɑ��M���鎞�̔z�M���[�h���擾����B<p>
     *
     * @return �z�M���[�h
     */
    public int getDeliveryMode();
    
    /**
     * JMS�̈���ɑ��M���鎞�̗D�揇�ʂ�ݒ肷��B<p>
     * �f�t�H���g�́A{@link javax.jms.Message#DEFAULT_PRIORITY}�B<br>
     *
     * @param priority �D�揇��
     */
    public void setPriority(int priority);
    
    /**
     * JMS�̈���ɑ��M���鎞�̗D�揇�ʂ��擾����B<p>
     *
     * @return �D�揇��
     */
    public int getPriority();
    
    /**
     * JMS�̈���ɑ��M���鎞�̐�������[ms]��ݒ肷��B<p>
     * �f�t�H���g�́A{@link javax.jms.Message#DEFAULT_TIME_TO_LIVE}�B<br>
     *
     * @param millis ��������[ms]
     */
    public void setTimeToLive(long millis);
    
    /**
     * JMS�̈���ɑ��M���鎞�̐�������[ms]���擾����B<p>
     *
     * @return ��������[ms]
     */
    public long getTimeToLive();
}
