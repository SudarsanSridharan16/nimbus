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

import javax.jms.*;

/**
 * JMS ���b�Z�[�W�v���f���[�T�t�@�N�g���B<p>
 * 
 * @author M.Takata
 */
public interface JMSMessageProducerFactory{
    
    /**
     * ���̃t�@�N�g�����ێ����Ă���Session���擾����B<p>
     * createProducer���\�b�h�ŁAMessageProducer�𐶐�����ۂɁA������Session���w�肵�Ȃ������ꍇ�́A����Session���g�p�����B<br>
     *
     * @return ���̃t�@�N�g�����ێ����Ă���Session�BSession��ێ����Ă��Ȃ��ꍇ��null�B
     */
    public Session getSession();
    
    /**
     * ���̃t�@�N�g����Session�̎擾�Ɏg�p����{@link JMSSessionFactory}�T�[�r�X���擾����B<p>
     *
     * @return {@link JMSSessionFactory}�T�[�r�X
     */
    public JMSSessionFactory getSessionFactory();
    
    /**
     * ���̃t�@�N�g�����ێ����Ă���Destination���擾����B<p>
     * createProducer���\�b�h�ŁAMessageProducer�𐶐�����ۂɁA������Destination���w�肵�Ȃ������ꍇ�́A����Destination���g�p�����B<br>
     *
     * @return ���̃t�@�N�g�����ێ����Ă���Destination�BDestination��ێ����Ă��Ȃ��ꍇ��null�B
     */
    public Destination getDestination();
    
    /**
     * MessageProducer�𐶐�����B<p>
     * {@link #getSession()}�Ŏ擾�����Session����A{@link Session#createProducer(Destination)}���\�b�h�Ő�������B<br>
     * getSession()��null��Ԃ��ꍇ�́AJMSMessageProducerCreateException��throw����B<br>
     * �܂��A������Destination�́A{@link #getDestination()}�Ŏ擾�����Destination���g�p�����BgetDestination()��null��Ԃ��ꍇ�́AJMSMessageProducerCreateException��throw����B<br>
     *
     * @return MessageProducer
     * @exception JMSMessageProducerCreateException MessageProducer�̐����Ɏ��s�����ꍇ
     */
    public MessageProducer createProducer()
     throws JMSMessageProducerCreateException;
    
    /**
     * MessageProducer�𐶐�����B<p>
     * {@link #getSession()}�Ŏ擾�����Session����A{@link Session#createProducer(Destination)}���\�b�h�Ő�������B<br>
     * getSession()��null��Ԃ��ꍇ�́AJMSMessageProducerCreateException��throw����B<br>
     *
     * @param dest ����ƂȂ�Destination
     * @return MessageProducer
     * @exception JMSMessageProducerCreateException MessageProducer�̐����Ɏ��s�����ꍇ
     */
    public MessageProducer createProducer(Destination dest)
     throws JMSMessageProducerCreateException;
    
    /**
     * MessageProducer�𐶐�����B<p>
     * �w�肳�ꂽSession����A{@link Session#createProducer(Destination)}���\�b�h�Ő�������B<br>
     *
     * @param session Session
     * @param dest ����ƂȂ�Destination
     * @return MessageProducer
     * @exception JMSMessageProducerCreateException MessageProducer�̐����Ɏ��s�����ꍇ
     */
    public MessageProducer createProducer(Session session, Destination dest)
     throws JMSMessageProducerCreateException;
}