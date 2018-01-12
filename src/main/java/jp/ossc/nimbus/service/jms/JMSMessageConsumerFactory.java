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
 * JMS���b�Z�[�W�R���V���[�}�t�@�N�g���B<p>
 * 
 * @author M.Takata
 */
public interface JMSMessageConsumerFactory{
    
    /**
     * ���̃t�@�N�g�����ێ����Ă���Session���擾����B<p>
     * createConsumer���\�b�h�ŁAMessageConsumer�𐶐�����ۂɁA������Session���w�肵�Ȃ������ꍇ�́A����Session���g�p�����B<br>
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
     * createConsumer���\�b�h�ŁAMessageConsumer�𐶐�����ۂɁA������Destination���w�肵�Ȃ������ꍇ�́A����Destination���g�p�����B<br>
     *
     * @return ���̃t�@�N�g�����ێ����Ă���Destination�BDestination��ێ����Ă��Ȃ��ꍇ��null�B
     */
    public Destination getDestination();
    
    /**
     * MessageConsumer�𐶐�����B<p>
     * {@link #getSession()}�Ŏ擾�����Session����A{@link Session#createConsumer(Destination, String, boolean)}���\�b�h�Ő�������B<br>
     * getSession()��null��Ԃ��ꍇ�́AJMSMessageConsumerCreateException��throw����B<br>
     * �܂��A������Destination�́A{@link #getDestination()}�Ŏ擾�����Destination���g�p�����BgetDestination()��null��Ԃ��ꍇ�́AJMSMessageConsumerCreateException��throw����B<br>
     * ��Q�����Ƒ�R�����́A�T�[�r�X�̎����Ɉˑ����邪�A���ɐݒ肪�Ȃ����null�Afalse�B<br>
     *
     * @return MessageConsumer
     * @exception JMSMessageConsumerCreateException MessageConsumer�̐����Ɏ��s�����ꍇ
     */
    public MessageConsumer createConsumer()
     throws JMSMessageConsumerCreateException;
    
    /**
     * MessageConsumer�𐶐�����B<p>
     * {@link #getSession()}�Ŏ擾�����Session����A{@link Session#createConsumer(Destination, String, boolean)}���\�b�h�Ő�������B<br>
     * getSession()��null��Ԃ��ꍇ�́AJMSMessageConsumerCreateException��throw����B<br>
     * �܂��A������Destination�́A{@link #getDestination()}�Ŏ擾�����Destination���g�p�����BgetDestination()��null��Ԃ��ꍇ�́AJMSMessageConsumerCreateException��throw����B<br>
     * ��R�����́A�T�[�r�X�̎����Ɉˑ����邪�A���ɐݒ肪�Ȃ����false�B<br>
     *
     * @param messageSelector ��M���b�Z�[�W��I�����邽�߂̃��b�Z�[�W�Z���N�^������
     * @return MessageConsumer
     * @exception JMSMessageConsumerCreateException MessageConsumer�̐����Ɏ��s�����ꍇ
     */
    public MessageConsumer createConsumer(String messageSelector)
     throws JMSMessageConsumerCreateException;
    
    /**
     * MessageConsumer�𐶐�����B<p>
     * {@link #getSession()}�Ŏ擾�����Session����A{@link Session#createConsumer(Destination, String, boolean)}���\�b�h�Ő�������B<br>
     * getSession()��null��Ԃ��ꍇ�́AJMSMessageConsumerCreateException��throw����B<br>
     * �܂��A������Destination�́A{@link #getDestination()}�Ŏ擾�����Destination���g�p�����BgetDestination()��null��Ԃ��ꍇ�́AJMSMessageConsumerCreateException��throw����B<br>
     *
     * @param messageSelector ��M���b�Z�[�W��I�����邽�߂̃��b�Z�[�W�Z���N�^������
     * @param noLocal Destination��Topic�ŁAtrue��ݒ肵���ꍇ�A���[�J�����瑗�M���ꂽ���b�Z�[�W�͎�M���Ȃ��悤�ɂȂ�BDestination��Queue�̏ꍇ�̓���͋K�肳��Ă��Ȃ��B
     * @return MessageConsumer
     * @exception JMSMessageConsumerCreateException MessageConsumer�̐����Ɏ��s�����ꍇ
     */
    public MessageConsumer createConsumer(
        String messageSelector,
        boolean noLocal
    ) throws JMSMessageConsumerCreateException;
    
    /**
     * MessageConsumer�𐶐�����B<p>
     * {@link #getSession()}�Ŏ擾�����Session����A{@link Session#createConsumer(Destination, String, boolean)}���\�b�h�Ő�������B<br>
     * getSession()��null��Ԃ��ꍇ�́AJMSMessageConsumerCreateException��throw����B<br>
     * ��Q�����Ƒ�R�����́A�T�[�r�X�̎����Ɉˑ����邪�A���ɐݒ肪�Ȃ����null�Afalse�B<br>
     *
     * @param destination �z�M����Queue�܂���Topic
     * @return MessageConsumer
     * @exception JMSMessageConsumerCreateException MessageConsumer�̐����Ɏ��s�����ꍇ
     */
    public MessageConsumer createConsumer(Destination destination)
     throws JMSMessageConsumerCreateException;
    
    /**
     * MessageConsumer�𐶐�����B<p>
     * {@link #getSession()}�Ŏ擾�����Session����A{@link Session#createConsumer(Destination, String, boolean)}���\�b�h�Ő�������B<br>
     * getSession()��null��Ԃ��ꍇ�́AJMSMessageConsumerCreateException��throw����B<br>
     * ��R�����́A�T�[�r�X�̎����Ɉˑ����邪�A���ɐݒ肪�Ȃ����false�B<br>
     *
     * @param destination �z�M����Queue�܂���Topic
     * @param messageSelector ��M���b�Z�[�W��I�����邽�߂̃��b�Z�[�W�Z���N�^������
     * @return MessageConsumer
     * @exception JMSMessageConsumerCreateException MessageConsumer�̐����Ɏ��s�����ꍇ
     */
    public MessageConsumer createConsumer(
        Destination destination,
        String messageSelector
    ) throws JMSMessageConsumerCreateException;
    
    /**
     * MessageConsumer�𐶐�����B<p>
     * {@link #getSession()}�Ŏ擾�����Session����A{@link Session#createConsumer(Destination, String, boolean)}���\�b�h�Ő�������B<br>
     * getSession()��null��Ԃ��ꍇ�́AJMSMessageConsumerCreateException��throw����B<br>
     *
     * @param destination �z�M����Queue�܂���Topic
     * @param messageSelector ��M���b�Z�[�W��I�����邽�߂̃��b�Z�[�W�Z���N�^������
     * @param noLocal Destination��Topic�ŁAtrue��ݒ肵���ꍇ�A���[�J�����瑗�M���ꂽ���b�Z�[�W�͎�M���Ȃ��悤�ɂȂ�BDestination��Queue�̏ꍇ�̓���͋K�肳��Ă��Ȃ��B
     * @return MessageConsumer
     * @exception JMSMessageConsumerCreateException MessageConsumer�̐����Ɏ��s�����ꍇ
     */
    public MessageConsumer createConsumer(
        Destination destination,
        String messageSelector,
        boolean noLocal
    ) throws JMSMessageConsumerCreateException;
    
    /**
     * MessageConsumer�𐶐�����B<p>
     * �w�肳�ꂽSession��{@link Session#createConsumer(Destination, String, boolean)}���\�b�h�Ő�������B<br>
     * ������Destination�́A{@link #getDestination()}�Ŏ擾�����Destination���g�p�����BgetDestination()��null��Ԃ��ꍇ�́AJMSMessageConsumerCreateException��throw����B<br>
     * ��Q�����Ƒ�R�����́A�T�[�r�X�̎����Ɉˑ����邪�A���ɐݒ肪�Ȃ����null�Afalse�B<br>
     *
     * @param session Session
     * @return MessageConsumer
     * @exception JMSMessageConsumerCreateException MessageConsumer�̐����Ɏ��s�����ꍇ
     */
    public MessageConsumer createConsumer(Session session)
     throws JMSMessageConsumerCreateException;
    
    /**
     * MessageConsumer�𐶐�����B<p>
     * �w�肳�ꂽSession��{@link Session#createConsumer(Destination, String, boolean)}���\�b�h�Ő�������B<br>
     * ������Destination�́A{@link #getDestination()}�Ŏ擾�����Destination���g�p�����BgetDestination()��null��Ԃ��ꍇ�́AJMSMessageConsumerCreateException��throw����B<br>
     * ��R�����́A�T�[�r�X�̎����Ɉˑ����邪�A���ɐݒ肪�Ȃ����false�B<br>
     *
     * @param session Session
     * @param messageSelector ��M���b�Z�[�W��I�����邽�߂̃��b�Z�[�W�Z���N�^������
     * @return MessageConsumer
     * @exception JMSMessageConsumerCreateException MessageConsumer�̐����Ɏ��s�����ꍇ
     */
    public MessageConsumer createConsumer(
        Session session,
        String messageSelector
    ) throws JMSMessageConsumerCreateException;
    
    /**
     * MessageConsumer�𐶐�����B<p>
     * �w�肳�ꂽSession��{@link Session#createConsumer(Destination, String, boolean)}���\�b�h�Ő�������B<br>
     * ������Destination�́A{@link #getDestination()}�Ŏ擾�����Destination���g�p�����BgetDestination()��null��Ԃ��ꍇ�́AJMSMessageConsumerCreateException��throw����B<br>
     *
     * @param session Session
     * @param messageSelector ��M���b�Z�[�W��I�����邽�߂̃��b�Z�[�W�Z���N�^������
     * @param noLocal Destination��Topic�ŁAtrue��ݒ肵���ꍇ�A���[�J�����瑗�M���ꂽ���b�Z�[�W�͎�M���Ȃ��悤�ɂȂ�BDestination��Queue�̏ꍇ�̓���͋K�肳��Ă��Ȃ��B
     * @return MessageConsumer
     * @exception JMSMessageConsumerCreateException MessageConsumer�̐����Ɏ��s�����ꍇ
     */
    public MessageConsumer createConsumer(
        Session session,
        String messageSelector,
        boolean noLocal
    ) throws JMSMessageConsumerCreateException;
    
    /**
     * MessageConsumer�𐶐�����B<p>
     * �w�肳�ꂽSession��{@link Session#createConsumer(Destination, String, boolean)}���\�b�h�Ő�������B<br>
     * ��Q�����Ƒ�R�����́A�T�[�r�X�̎����Ɉˑ����邪�A���ɐݒ肪�Ȃ����null�Afalse�B<br>
     *
     * @param session Session
     * @param destination �z�M����Queue�܂���Topic
     * @return MessageConsumer
     * @exception JMSMessageConsumerCreateException MessageConsumer�̐����Ɏ��s�����ꍇ
     */
    public MessageConsumer createConsumer(
        Session session,
        Destination destination
    ) throws JMSMessageConsumerCreateException;
    
    /**
     * MessageConsumer�𐶐�����B<p>
     * �w�肳�ꂽSession��{@link Session#createConsumer(Destination, String, boolean)}���\�b�h�Ő�������B<br>
     * ��R�����́A�T�[�r�X�̎����Ɉˑ����邪�A���ɐݒ肪�Ȃ����false�B<br>
     *
     * @param session Session
     * @param destination �z�M����Queue�܂���Topic
     * @param messageSelector ��M���b�Z�[�W��I�����邽�߂̃��b�Z�[�W�Z���N�^������
     * @return MessageConsumer
     * @exception JMSMessageConsumerCreateException MessageConsumer�̐����Ɏ��s�����ꍇ
     */
    public MessageConsumer createConsumer(
        Session session,
        Destination destination,
        String messageSelector
    ) throws JMSMessageConsumerCreateException;
    
    /**
     * MessageConsumer�𐶐�����B<p>
     * �w�肳�ꂽSession��{@link Session#createConsumer(Destination, String, boolean)}���\�b�h�Ő�������B<br>
     *
     * @param session Session
     * @param destination �z�M����Queue�܂���Topic
     * @param messageSelector ��M���b�Z�[�W��I�����邽�߂̃��b�Z�[�W�Z���N�^������
     * @param noLocal Destination��Topic�ŁAtrue��ݒ肵���ꍇ�A���[�J�����瑗�M���ꂽ���b�Z�[�W�͎�M���Ȃ��悤�ɂȂ�BDestination��Queue�̏ꍇ�̓���͋K�肳��Ă��Ȃ��B
     * @return MessageConsumer
     * @exception JMSMessageConsumerCreateException MessageConsumer�̐����Ɏ��s�����ꍇ
     */
    public MessageConsumer createConsumer(
        Session session,
        Destination destination,
        String messageSelector,
        boolean noLocal
    ) throws JMSMessageConsumerCreateException;
    
    /**
     * TopicSubscriber�𐶐�����B<p>
     * {@link #getSession()}�Ŏ擾�����Session����A{@link Session#createDurableSubscriber(Topic, String)}���\�b�h�Ő�������B<br>
     * getSession()��null��Ԃ��ꍇ�́AJMSMessageConsumerCreateException��throw����B<br>
     * �܂��A������Topic�́A{@link #getDestination()}�Ŏ擾�����Destination���g�p�����BgetDestination()��null��Ԃ��ꍇ�A�܂���getDestination()��Topic�łȂ��ꍇ�́AJMSMessageConsumerCreateException��throw����B<br>
     *
     * @param name TopicSubscriber�����ʂ��閼�O
     * @return TopicSubscriber
     * @exception JMSMessageConsumerCreateException TopicSubscriber�̐����Ɏ��s�����ꍇ
     */
    public TopicSubscriber createDurableSubscriber(String name)
     throws JMSMessageConsumerCreateException;
    
    /**
     * TopicSubscriber�𐶐�����B<p>
     * {@link #getSession()}�Ŏ擾�����Session����A{@link Session#createDurableSubscriber(Topic, String)}���\�b�h�Ő�������B<br>
     * getSession()��null��Ԃ��ꍇ�́AJMSMessageConsumerCreateException��throw����B<br>
     *
     * @param topic �z�M����Topic
     * @param name TopicSubscriber�����ʂ��閼�O
     * @return TopicSubscriber
     * @exception JMSMessageConsumerCreateException TopicSubscriber�̐����Ɏ��s�����ꍇ
     */
    public TopicSubscriber createDurableSubscriber(
        Topic topic,
        String name
    ) throws JMSMessageConsumerCreateException;
    
    /**
     * TopicSubscriber�𐶐�����B<p>
     * �w�肳�ꂽSession����A{@link Session#createDurableSubscriber(Topic, String)}���\�b�h�Ő�������B<br>
     * ������Topic�́A{@link #getDestination()}�Ŏ擾�����Destination���g�p�����BgetDestination()��null��Ԃ��ꍇ�A�܂���getDestination()��Topic�łȂ��ꍇ�́AJMSMessageConsumerCreateException��throw����B<br>
     *
     * @param session Session
     * @param name TopicSubscriber�����ʂ��閼�O
     * @return TopicSubscriber
     * @exception JMSMessageConsumerCreateException TopicSubscriber�̐����Ɏ��s�����ꍇ
     */
    public TopicSubscriber createDurableSubscriber(
        Session session,
        String name
    ) throws JMSMessageConsumerCreateException;
    
    /**
     * TopicSubscriber�𐶐�����B<p>
     * �w�肳�ꂽSession����A{@link Session#createDurableSubscriber(Topic, String)}���\�b�h�Ő�������B<br>
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
    ) throws JMSMessageConsumerCreateException;
    
    /**
     * TopicSubscriber�𐶐�����B<p>
     * {@link #getSession()}�Ŏ擾�����Session����A{@link Session#createDurableSubscriber(Topic, String, String, boolean)}���\�b�h�Ő�������B<br>
     * getSession()��null��Ԃ��ꍇ�́AJMSMessageConsumerCreateException��throw����B<br>
     * �܂��A��P������Topic�́A{@link #getDestination()}�Ŏ擾�����Destination���g�p�����BgetDestination()��null��Ԃ��ꍇ�A�܂���getDestination()��Topic�łȂ��ꍇ�́AJMSMessageConsumerCreateException��throw����B<br>
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
    ) throws JMSMessageConsumerCreateException;
    
    /**
     * TopicSubscriber�𐶐�����B<p>
     * {@link #getSession()}�Ŏ擾�����Session����A{@link Session#createDurableSubscriber(Topic, String, String, boolean)}���\�b�h�Ő�������B<br>
     * getSession()��null��Ԃ��ꍇ�́AJMSMessageConsumerCreateException��throw����B<br>
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
    ) throws JMSMessageConsumerCreateException;
    
    /**
     * TopicSubscriber�𐶐�����B<p>
     * �w�肳�ꂽSession����A{@link Session#createDurableSubscriber(Topic, String, String, boolean)}���\�b�h�Ő�������B<br>
     * ��P������Topic�́A{@link #getDestination()}�Ŏ擾�����Destination���g�p�����BgetDestination()��null��Ԃ��ꍇ�A�܂���getDestination()��Topic�łȂ��ꍇ�́AJMSMessageConsumerCreateException��throw����B<br>
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
    ) throws JMSMessageConsumerCreateException;
    
    /**
     * TopicSubscriber�𐶐�����B<p>
     * �w�肳�ꂽSession����A{@link Session#createDurableSubscriber(Topic, String, String, boolean)}���\�b�h�Ő�������B<br>
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
    ) throws JMSMessageConsumerCreateException;
}
