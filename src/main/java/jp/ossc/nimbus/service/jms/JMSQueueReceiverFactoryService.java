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
 * JMS Queue ���V�[�o�t�@�N�g���B<p>
 * JMS1.0�ł́AQueueSession��TopicSession�̃C���^�t�F�[�X�����ꂳ��Ă��Ȃ��������߁AQueueSession�����QueueReceiver�������s���B<br>
 * 
 * @author M.Takata
 */
public class JMSQueueReceiverFactoryService
 extends JMSMessageConsumerFactoryService{
    
    private static final long serialVersionUID = -8866208706043342245L;
    
    /**
     * QueueReceiver�𐶐�����B<p>
     * �w�肳�ꂽSession��{@link QueueSession#createReceiver(Queue, String)}���\�b�h�Ő�������B<br>
     * ��R�����́Anull���w�肷���MessageSelector�����̒l��K�p����B<br>
     *
     * @param session QueueSession
     * @param destination �z�M����Queue
     * @param messageSelector ��M���b�Z�[�W��I�����邽�߂̃��b�Z�[�W�Z���N�^������
     * @return QueueReceiver
     * @exception JMSMessageConsumerCreateException QueueReceiver�̐����Ɏ��s�����ꍇ
     */
    public MessageConsumer createConsumer(
        Session session,
        Destination destination,
        String messageSelector
    ) throws JMSMessageConsumerCreateException{
        if(session == null){
            throw new JMSMessageConsumerCreateException("Session is null.");
        }
        if(!(session instanceof QueueSession)){
            throw new JMSMessageConsumerCreateException("Session is not QueueSession.");
        }
        if(destination == null){
            throw new JMSMessageConsumerCreateException("Destination is null.");
        }
        if(!(destination instanceof Queue)){
            throw new JMSMessageConsumerCreateException("Destination is not queue.");
        }
        try{
            return ((QueueSession)session).createReceiver(
                (Queue)destination,
                messageSelector
            );
        }catch(JMSException e){
            throw new JMSMessageConsumerCreateException(e);
        }
    }
    
    /**
     * QueueReceiver�𐶐�����B<p>
     * {@link #createConsumer(Session, Destination, String)}���\�b�h���Ăяo���̂Ɠ����ł���B<br>
     *
     * @param session QueueSession
     * @param destination �z�M����Queue
     * @param messageSelector ��M���b�Z�[�W��I�����邽�߂̃��b�Z�[�W�Z���N�^������
     * @param noLocal �w�肵�Ă�����
     * @return QueueReceiver
     * @exception JMSMessageConsumerCreateException QueueReceiver�̐����Ɏ��s�����ꍇ
     * @see #createConsumer(Session, Destination, String)
     */
    public MessageConsumer createConsumer(
        Session session,
        Destination destination,
        String messageSelector,
        boolean noLocal
    ) throws JMSMessageConsumerCreateException{
        return createConsumer(session, destination, messageSelector);
    }
    
    /**
     * �T�|�[�g����Ȃ����\�b�h�ł���B<p>
     *
     * @param name TopicSubscriber�����ʂ��閼�O
     * @return TopicSubscriber
     * @exception JMSMessageConsumerCreateException TopicSubscriber�̐����Ɏ��s�����ꍇ
     */
    public TopicSubscriber createDurableSubscriber(String name)
     throws JMSMessageConsumerCreateException{
        throw new UnsupportedOperationException();
    }
    
    /**
     * �T�|�[�g����Ȃ����\�b�h�ł���B<p>
     *
     * @param topic �z�M����Topic
     * @param name TopicSubscriber�����ʂ��閼�O
     * @return TopicSubscriber
     * @exception JMSMessageConsumerCreateException TopicSubscriber�̐����Ɏ��s�����ꍇ
     */
    public TopicSubscriber createDurableSubscriber(
        Topic topic,
        String name
    ) throws JMSMessageConsumerCreateException{
        throw new UnsupportedOperationException();
    }
    
    /**
     * �T�|�[�g����Ȃ����\�b�h�ł���B<p>
     *
     * @param session Session
     * @param name TopicSubscriber�����ʂ��閼�O
     * @return TopicSubscriber
     * @exception JMSMessageConsumerCreateException TopicSubscriber�̐����Ɏ��s�����ꍇ
     */
    public TopicSubscriber createDurableSubscriber(
        Session session,
        String name
    ) throws JMSMessageConsumerCreateException{
        throw new UnsupportedOperationException();
    }
    
    /**
     * �T�|�[�g����Ȃ����\�b�h�ł���B<p>
     *
     * @param session Session
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
        throw new UnsupportedOperationException();
    }
    
    /**
     * �T�|�[�g����Ȃ����\�b�h�ł���B<p>
     *
     * @param name TopicSubscriber�����ʂ��閼�O
     * @param messageSelector ��M���b�Z�[�W��I�����邽�߂̃��b�Z�[�W�Z���N�^������
     * @param noLocal true��ݒ肵���ꍇ�A���[�J�����瑗�M���ꂽ���b�Z�[�W�͎�M���Ȃ��悤�ɂȂ�B
     * @return TopicSubscriber
     * @exception JMSMessageConsumerCreateException TopicSubscriber�̐����Ɏ��s�����ꍇ
     */
    public TopicSubscriber createDurableSubscriber(
        String name,
        String messageSelector,
        boolean noLocal
    ) throws JMSMessageConsumerCreateException{
        throw new UnsupportedOperationException();
    }
    
    /**
     * �T�|�[�g����Ȃ����\�b�h�ł���B<p>
     *
     * @param topic �z�M����Topic
     * @param name TopicSubscriber�����ʂ��閼�O
     * @param messageSelector ��M���b�Z�[�W��I�����邽�߂̃��b�Z�[�W�Z���N�^������
     * @param noLocal true��ݒ肵���ꍇ�A���[�J�����瑗�M���ꂽ���b�Z�[�W�͎�M���Ȃ��悤�ɂȂ�B
     * @return TopicSubscriber
     * @exception JMSMessageConsumerCreateException TopicSubscriber�̐����Ɏ��s�����ꍇ
     */
    public TopicSubscriber createDurableSubscriber(
        Topic topic,
        String name,
        String messageSelector,
        boolean noLocal
    ) throws JMSMessageConsumerCreateException{
        throw new UnsupportedOperationException();
    }
    
    /**
     * �T�|�[�g����Ȃ����\�b�h�ł���B<p>
     *
     * @param session Session
     * @param name TopicSubscriber�����ʂ��閼�O
     * @param messageSelector ��M���b�Z�[�W��I�����邽�߂̃��b�Z�[�W�Z���N�^������
     * @param noLocal true��ݒ肵���ꍇ�A���[�J�����瑗�M���ꂽ���b�Z�[�W�͎�M���Ȃ��悤�ɂȂ�B
     * @return TopicSubscriber
     * @exception JMSMessageConsumerCreateException TopicSubscriber�̐����Ɏ��s�����ꍇ
     */
    public TopicSubscriber createDurableSubscriber(
        Session session,
        String name,
        String messageSelector,
        boolean noLocal
    ) throws JMSMessageConsumerCreateException{
        throw new UnsupportedOperationException();
    }
    
    /**
     * �T�|�[�g����Ȃ����\�b�h�ł���B<p>
     *
     * @param session Session
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
        throw new UnsupportedOperationException();
    }
}
