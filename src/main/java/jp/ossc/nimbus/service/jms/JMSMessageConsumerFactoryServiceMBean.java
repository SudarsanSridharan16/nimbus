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
 * {@link JMSMessageConsumerFactoryService}��MBean�C���^�t�F�[�X<p>
 * 
 * @author M.Takata
 * @see JMSMessageConsumerFactoryService
 */
public interface JMSMessageConsumerFactoryServiceMBean
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
     * �z�M���ƂȂ�javax.jms.Destination��JNDI����ݒ肷��B<p>
     * DestinationFinderServiceName�����Őݒ肳�ꂽ{@link jp.ossc.nimbus.service.jndi.JndiFinder JndiFinder}�T�[�r�X���g���āA�z�M���ƂȂ�javax.jms.Destination���A�����Őݒ肳�ꂽJNDI����lookup����B<br>
     *
     * @param name �z�M���ƂȂ�javax.jms.Destination��JNDI��
     */
    public void setDestinationName(String name);
    
    /**
     * �z�M���ƂȂ�javax.jms.Destination��JNDI�����擾����B<p>
     *
     * @return �z�M���ƂȂ�javax.jms.Destination��JNDI��
     */
    public String getDestinationName();
    
    /**
     * ��M���b�Z�[�W��I�����邽�߂̃��b�Z�[�W�Z���N�^�������ݒ肷��B<p>
     * �f�t�H���g�́Anull�B<br>
     *
     * @param selector ���b�Z�[�W�Z���N�^������
     */
    public void setMessageSelector(String selector);
    
    /**
     * ��M���b�Z�[�W��I�����邽�߂̃��b�Z�[�W�Z���N�^��������擾����B<p>
     *
     * @return ���b�Z�[�W�Z���N�^������
     */
    public String getMessageSelector();
    
    /**
     * ���[�J�����瑗�M���ꂽ���b�Z�[�W�̎�M�����ۂ��邩�ǂ�����ݒ肷��B<p>
     * �f�t�H���g�́Afalse�B<br>
     * 
     * @param isNoLocal Destination��Topic�ŁAtrue��ݒ肵���ꍇ�A���[�J�����瑗�M���ꂽ���b�Z�[�W�͎�M���Ȃ��悤�ɂȂ�BDestination��Queue�̏ꍇ�̓���͋K�肳��Ă��Ȃ��B
     */
    public void setNoLocal(boolean isNoLocal);
    
    /**
     * ���[�J�����瑗�M���ꂽ���b�Z�[�W�̎�M�����ۂ��邩�ǂ����𔻒肷��B<p>
     * 
     * @return Destination��Topic�ŁAtrue�̏ꍇ�A���[�J�����瑗�M���ꂽ���b�Z�[�W�͎�M���Ȃ��悤�ɂȂ�BDestination��Queue�̏ꍇ�̓���͋K�肳��Ă��Ȃ��B
     */
    public boolean isNoLocal();
    
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
}
