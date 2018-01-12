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
 * JMS Topic �T�u�X�N���C�o�t�@�N�g���B<p>
 * JMS1.0�ł́AQueueSession��TopicSession�̃C���^�t�F�[�X�����ꂳ��Ă��Ȃ��������߁ATopicSession�����TopicSubscriber�������s���B<br>
 * 
 * @author M.Takata
 */
public class JMSTopicSubscriberFactoryService
 extends JMSMessageConsumerFactoryService{
    
    private static final long serialVersionUID = -8754717063746944540L;
    
    /**
     * TopicSubscriber�𐶐�����B<p>
     * {@link #createConsumer(Session, Destination, String, boolean)}���\�b�h���Ăяo���̂Ɠ����B��S�����́ANoLocal�����̒l���K�p�����B<br>
     *
     * @param session TopicSession
     * @param destination �z�M����Topic
     * @param messageSelector ��M���b�Z�[�W��I�����邽�߂̃��b�Z�[�W�Z���N�^������
     * @return TopicSubscriber
     * @exception JMSMessageConsumerCreateException TopicSubscriber�̐����Ɏ��s�����ꍇ
     */
    public MessageConsumer createConsumer(
        Session session,
        Destination destination,
        String messageSelector
    ) throws JMSMessageConsumerCreateException{
        return createConsumer(session, destination, messageSelector, isNoLocal);
    }
    
    /**
     * TopicSubscriber�𐶐�����B<p>
     * �w�肳�ꂽTopicSession��{@link TopicSession#createSubscriber(Topic, String, boolean)}���\�b�h�Ő�������B<br>
     *
     * @param session TopicSession
     * @param destination �z�M����Topic
     * @param messageSelector ��M���b�Z�[�W��I�����邽�߂̃��b�Z�[�W�Z���N�^������
     * @param noLocal true��ݒ肵���ꍇ�A���[�J�����瑗�M���ꂽ���b�Z�[�W�͎�M���Ȃ��悤�ɂȂ�
     * @return TopicSubscriber
     * @exception JMSMessageConsumerCreateException TopicSubscriber�̐����Ɏ��s�����ꍇ
     */
    public MessageConsumer createConsumer(
        Session session,
        Destination destination,
        String messageSelector,
        boolean noLocal
    ) throws JMSMessageConsumerCreateException{
        if(session == null){
            throw new JMSMessageConsumerCreateException("Session is null.");
        }
        if(!(session instanceof TopicSession)){
            throw new JMSMessageConsumerCreateException("Session is not TopicSession.");
        }
        if(destination == null){
            throw new JMSMessageConsumerCreateException("Destination is null.");
        }
        if(!(destination instanceof Topic)){
            throw new JMSMessageConsumerCreateException("Destination is not Topic.");
        }
        try{
            return ((TopicSession)session).createSubscriber(
                (Topic)destination,
                messageSelector,
                noLocal
            );
        }catch(JMSException e){
            throw new JMSMessageConsumerCreateException(e);
        }
    }
    
    /**
     * TopicSubscriber�𐶐�����B<p>
     * �w�肳�ꂽSession����A{@link TopicSession#createDurableSubscriber(Topic, String)}���\�b�h�Ő�������B<br>
     *
     * @param session TopicSession
     * @param topic �z�M����Topic
     * @param name TopicSubscriber�����ʂ��閼�O
     * @return TopicSubscriber
     * @exception JMSMessageConsumerCreateException TopicSubscriber�̐����Ɏ��s�����ꍇ
     */
    public TopicSubscriber createDurableSubscriber(
        Session session,
        Topic topic,
        String name
    ) throws JMSMessageConsumerCreateException{
        if(session == null){
            throw new JMSMessageConsumerCreateException("Session is null.");
        }
        if(!(session instanceof TopicSession)){
            throw new JMSMessageConsumerCreateException("Session is not TopicSession.");
        }
        if(topic == null){
            throw new JMSMessageConsumerCreateException("Topic is null.");
        }
        try{
            return ((TopicSession)session).createDurableSubscriber(topic, name);
        }catch(JMSException e){
            throw new JMSMessageConsumerCreateException(e);
        }
    }
    
    /**
     * TopicSubscriber�𐶐�����B<p>
     * �w�肳�ꂽTopicSession����A{@link TopicSession#createDurableSubscriber(Topic, String, String, boolean)}���\�b�h�Ő�������B<br>
     *
     * @param session TopicSession
     * @param topic �z�M����Topic
     * @param name TopicSubscriber�����ʂ��閼�O
     * @param messageSelector ��M���b�Z�[�W��I�����邽�߂̃��b�Z�[�W�Z���N�^������
     * @param noLocal true��ݒ肵���ꍇ�A���[�J�����瑗�M���ꂽ���b�Z�[�W�͎�M���Ȃ��悤�ɂȂ�B
     * @return TopicSubscriber
     * @exception JMSMessageConsumerCreateException TopicSubscriber�̐����Ɏ��s�����ꍇ
     */
    public TopicSubscriber createDurableSubscriber(
        Session session,
        Topic topic,
        String name,
        String messageSelector,
        boolean noLocal
    ) throws JMSMessageConsumerCreateException{
        if(session == null){
            throw new JMSMessageConsumerCreateException("Session is null.");
        }
        if(!(session instanceof TopicSession)){
            throw new JMSMessageConsumerCreateException("Session is not TopicSession.");
        }
        if(topic == null){
            throw new JMSMessageConsumerCreateException("Topic is null.");
        }
        try{
            return ((TopicSession)session).createDurableSubscriber(
                topic,
                name,
                messageSelector,
                noLocal
            );
        }catch(JMSException e){
            throw new JMSMessageConsumerCreateException(e);
        }
    }
}
