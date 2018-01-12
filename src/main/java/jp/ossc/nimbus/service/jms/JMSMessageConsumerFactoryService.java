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

import jp.ossc.nimbus.core.*;
import jp.ossc.nimbus.service.jndi.*;

/**
 * JMS���b�Z�[�W�R���V���[�}�t�@�N�g���B<p>
 * javax.jms.Session�����b�v���AMessageConsumer�̐������ȗ�������B<br>
 * Queue��Topi�̃C���^�t�F�[�X���������ꂽJMS 1.1�ɑΉ����Ă��܂��BJMS 1.1�ȑO�̃o�[�W�����Ŏg�p����ꍇ�ɂ́A�T�u�N���X��{@link JMSQueueReceiverFactoryService}��A{@link JMSTopicSubscriberFactoryService}���g�p���ĉ������B<br>
 * 
 * @author M.Takata
 */
public class JMSMessageConsumerFactoryService extends ServiceBase
 implements JMSMessageConsumerFactory, JMSMessageConsumerFactoryServiceMBean{
    
    private static final long serialVersionUID = 2488707181233003821L;
    
    protected ServiceName jmsSessionFactoryServiceName;
    protected JMSSessionFactory jmsSessionFactory;
    
    protected ServiceName destinationFinderServiceName;
    protected JndiFinder destinationFinder;
    
    protected String destinationName;
    protected Destination destination;
    
    protected String messageSelector;
    
    protected boolean isNoLocal;
    
    protected Session session;
    protected boolean isSessionCreate;
    protected boolean isCloseSession;
    
    // JMSMessageConsumerFactoryServiceMBean��JavaDoc
    public void setJMSSessionFactoryServiceName(ServiceName name){
        jmsSessionFactoryServiceName = name;
    }
    // JMSMessageConsumerFactoryServiceMBean��JavaDoc
    public ServiceName getJMSSessionFactoryServiceName(){
        return jmsSessionFactoryServiceName;
    }
    
    // JMSMessageConsumerFactoryServiceMBean��JavaDoc
    public void setDestinationFinderServiceName(ServiceName name){
        destinationFinderServiceName = name;
    }
    // JMSMessageConsumerFactoryServiceMBean��JavaDoc
    public ServiceName getDestinationFinderServiceName(){
        return destinationFinderServiceName;
    }
    
    // JMSMessageConsumerFactoryServiceMBean��JavaDoc
    public void setDestinationName(String name){
        destinationName = name;
    }
    // JMSMessageConsumerFactoryServiceMBean��JavaDoc
    public String getDestinationName(){
        return destinationName;
    }
    
    // JMSMessageConsumerFactoryServiceMBean��JavaDoc
    public void setMessageSelector(String selector){
        messageSelector = selector;
    }
    // JMSMessageConsumerFactoryServiceMBean��JavaDoc
    public String getMessageSelector(){
        return messageSelector;
    }
    
    // JMSMessageConsumerFactoryServiceMBean��JavaDoc
    public void setNoLocal(boolean isNoLocal){
        this.isNoLocal = isNoLocal;
    }
    // JMSMessageConsumerFactoryServiceMBean��JavaDoc
    public boolean isNoLocal(){
        return isNoLocal;
    }
    
    // JMSMessageConsumerFactoryServiceMBean��JavaDoc
    public void setSessionCreate(boolean isCreate){
        isSessionCreate = isCreate;
    }
    // JMSMessageConsumerFactoryServiceMBean��JavaDoc
    public boolean isSessionCreate(){
        return isSessionCreate;
    }
    
    // JMSMessageConsumerFactoryServiceMBean��JavaDoc
    public void setCloseSession(boolean isClose){
        isCloseSession = isClose;
    }
    // JMSMessageConsumerFactoryServiceMBean��JavaDoc
    public boolean isCloseSession(){
        return isCloseSession;
    }
    
    /**
     * {@link jp.ossc.nimbus.service.jndi.JndiFinder JndiFinder}�T�[�r�X��ݒ肷��B<p>
     * �����Őݒ肳�ꂽJndiFinder�T�[�r�X���g���āAJNDI�T�[�o����javax.jms.Destination��lookup����B<br>
     *
     * @param destinationFinder JndiFinder�T�[�r�X
     */
    public void setJndiFinder(JndiFinder destinationFinder) {
        this.destinationFinder = destinationFinder;
    }
    
    /**
     * {@link JMSSessionFactory}�T�[�r�X��ݒ肷��B<p>
     * SessionCreate������true�̏ꍇ�A�T�[�r�X�̊J�n���ɁA�����Őݒ肳�ꂽJMSSessionFactory�T�[�r�X���g���āASession�𐶐����ێ�����B<br>
     *
     * @param jmsSessionFactory JMSSessionFactory�T�[�r�X��
     */
    public void setJMSSessionFactory(JMSSessionFactory jmsSessionFactory) {
        this.jmsSessionFactory = jmsSessionFactory;
    }
    
    /**
     * �T�[�r�X�̊J�n�������s���B<p>
     *
     * @exception Exception �J�n�����Ɏ��s�����ꍇ
     */
    public void startService() throws Exception{
        
        if(jmsSessionFactoryServiceName != null){
            jmsSessionFactory = (JMSSessionFactory)ServiceManagerFactory
                .getServiceObject(jmsSessionFactoryServiceName);
        }
        
        if(destinationFinderServiceName != null){
            destinationFinder
                 = (JndiFinder)ServiceManagerFactory
                    .getServiceObject(destinationFinderServiceName);
            if(destinationName == null){
                destination = (Destination)destinationFinder.lookup();
            }else{
                destination = (Destination)destinationFinder
                    .lookup(destinationName);
            }
        }
        
        if(isSessionCreate){
            if(jmsSessionFactory == null){
                throw new IllegalArgumentException(
                    "jmsSessionFactoryServiceName must be specified."
                );
            }
            session = jmsSessionFactory.getSession();
        }
    }
    
    /**
     * �T�[�r�X�̒�~�������s���B<p>
     *
     * @exception Exception ��~�����Ɏ��s�����ꍇ
     */
    public void stopService() throws Exception{
        if(isCloseSession && session != null){
            try{
                session.close();
            }catch(JMSException e){
            }
        }
        session = null;
        destination = null;
    }
    
    // JMSMessageConsumerFactory��JavaDoc
    public Session getSession(){
        return session;
    }
    
    // JMSMessageConsumerFactory��JavaDoc
    public Destination getDestination(){
        return destination;
    }
    
    // JMSMessageConsumerFactory��JavaDoc
    public JMSSessionFactory getSessionFactory(){
        return jmsSessionFactory;
    }
    
    // JMSMessageConsumerFactory��JavaDoc
    public MessageConsumer createConsumer()
     throws JMSMessageConsumerCreateException{
        return createConsumer(messageSelector);
    }
    
    // JMSMessageConsumerFactory��JavaDoc
    public MessageConsumer createConsumer(
        String messageSelector
    ) throws JMSMessageConsumerCreateException{
        Session session = this.session;
        if(session == null){
            try{
                session = jmsSessionFactory.getSession();
            }catch(JMSSessionCreateException e){
                throw new JMSMessageConsumerCreateException(e);
            }
        }
        return createConsumer(session, messageSelector);
    }
    
    // JMSMessageConsumerFactory��JavaDoc
    public MessageConsumer createConsumer(
        String messageSelector,
        boolean noLocal
    ) throws JMSMessageConsumerCreateException{
        Session session = this.session;
        if(session == null){
            try{
                session = jmsSessionFactory.getSession();
            }catch(JMSSessionCreateException e){
                throw new JMSMessageConsumerCreateException(e);
            }
        }
        return createConsumer(session, messageSelector, noLocal);
    }
    
    // JMSMessageConsumerFactory��JavaDoc
    public MessageConsumer createConsumer(Destination destination)
     throws JMSMessageConsumerCreateException{
        return createConsumer(destination, messageSelector);
    }
    
    // JMSMessageConsumerFactory��JavaDoc
    public MessageConsumer createConsumer(
        Destination destination,
        String messageSelector
    ) throws JMSMessageConsumerCreateException{
        Session session = this.session;
        if(session == null){
            try{
                session = jmsSessionFactory.getSession();
            }catch(JMSSessionCreateException e){
                throw new JMSMessageConsumerCreateException(e);
            }
        }
        return createConsumer(session, destination, messageSelector);
    }
    
    // JMSMessageConsumerFactory��JavaDoc
    public MessageConsumer createConsumer(
        Destination destination,
        String messageSelector,
        boolean noLocal
    ) throws JMSMessageConsumerCreateException{
        Session session = this.session;
        if(session == null){
            try{
                session = jmsSessionFactory.getSession();
            }catch(JMSSessionCreateException e){
                throw new JMSMessageConsumerCreateException(e);
            }
        }
        return createConsumer(session, destination, messageSelector, noLocal);
    }
    
    // JMSMessageConsumerFactory��JavaDoc
    public MessageConsumer createConsumer(Session session)
     throws JMSMessageConsumerCreateException{
        return createConsumer(session, destination);
    }
    
    // JMSMessageConsumerFactory��JavaDoc
    public MessageConsumer createConsumer(
        Session session,
        String messageSelector
    ) throws JMSMessageConsumerCreateException{
        return createConsumer(session, destination, messageSelector);
    }
    
    // JMSMessageConsumerFactory��JavaDoc
    public MessageConsumer createConsumer(
        Session session,
        String messageSelector,
        boolean noLocal
    ) throws JMSMessageConsumerCreateException{
        return createConsumer(session, destination, messageSelector, noLocal);
    }
    
    // JMSMessageConsumerFactory��JavaDoc
    public MessageConsumer createConsumer(
        Session session,
        Destination destination
    ) throws JMSMessageConsumerCreateException{
        return createConsumer(session, destination, messageSelector);
    }
    
    // JMSMessageConsumerFactory��JavaDoc
    public MessageConsumer createConsumer(
        Session session,
        Destination destination,
        String messageSelector
    ) throws JMSMessageConsumerCreateException{
        if(session == null){
            throw new JMSMessageConsumerCreateException("Session is null.");
        }
        if(destination == null){
            throw new JMSMessageConsumerCreateException("Destination is null.");
        }
        try{
            return session.createConsumer(
                destination,
                messageSelector
            );
        }catch(JMSException e){
            throw new JMSMessageConsumerCreateException(e);
        }
    }
    
    // JMSMessageConsumerFactory��JavaDoc
    public MessageConsumer createConsumer(
        Session session,
        Destination destination,
        String messageSelector,
        boolean noLocal
    ) throws JMSMessageConsumerCreateException{
        if(session == null){
            throw new JMSMessageConsumerCreateException("Session is null.");
        }
        if(destination == null){
            throw new JMSMessageConsumerCreateException("Destination is null.");
        }
        try{
            return session.createConsumer(
                destination,
                messageSelector,
                noLocal
            );
        }catch(JMSException e){
            throw new JMSMessageConsumerCreateException(e);
        }
    }
    
    // JMSMessageConsumerFactory��JavaDoc
    public TopicSubscriber createDurableSubscriber(String name)
     throws JMSMessageConsumerCreateException{
        return createDurableSubscriber(
            session,
            name
        );
    }
    
    // JMSMessageConsumerFactory��JavaDoc
    public TopicSubscriber createDurableSubscriber(
        Topic topic,
        String name
    ) throws JMSMessageConsumerCreateException{
        Session session = this.session;
        if(session == null){
            try{
                session = jmsSessionFactory.getSession();
            }catch(JMSSessionCreateException e){
                throw new JMSMessageConsumerCreateException(e);
            }
        }
        return createDurableSubscriber(
            session,
            topic,
            name
        );
    }
    
    // JMSMessageConsumerFactory��JavaDoc
    public TopicSubscriber createDurableSubscriber(
        Session session,
        String name
    ) throws JMSMessageConsumerCreateException{
        if(destination == null){
            throw new JMSMessageConsumerCreateException("Topic is null.");
        }
        if(!(destination instanceof Topic)){
            throw new JMSMessageConsumerCreateException("Destination is not topic.");
        }
        return createDurableSubscriber(
            session,
            (Topic)destination,
            name
        );
    }
    
    // JMSMessageConsumerFactory��JavaDoc
    public TopicSubscriber createDurableSubscriber(
        Session session,
        Topic topic,
        String name
    ) throws JMSMessageConsumerCreateException{
        if(session == null){
            throw new JMSMessageConsumerCreateException("Session is null.");
        }
        if(topic == null){
            throw new JMSMessageConsumerCreateException("Topic is null.");
        }
        try{
            return session.createDurableSubscriber(topic, name);
        }catch(JMSException e){
            throw new JMSMessageConsumerCreateException(e);
        }
    }
    
    // JMSMessageConsumerFactory��JavaDoc
    public TopicSubscriber createDurableSubscriber(
        String name,
        String messageSelector,
        boolean noLocal
    ) throws JMSMessageConsumerCreateException{
        Session session = this.session;
        if(session == null){
            try{
                session = jmsSessionFactory.getSession();
            }catch(JMSSessionCreateException e){
                throw new JMSMessageConsumerCreateException(e);
            }
        }
        return createDurableSubscriber(
            session,
            name,
            messageSelector,
            noLocal
        );
    }
    
    // JMSMessageConsumerFactory��JavaDoc
    public TopicSubscriber createDurableSubscriber(
        Topic topic,
        String name,
        String messageSelector,
        boolean noLocal
    ) throws JMSMessageConsumerCreateException{
        Session session = this.session;
        if(session == null){
            try{
                session = jmsSessionFactory.getSession();
            }catch(JMSSessionCreateException e){
                throw new JMSMessageConsumerCreateException(e);
            }
        }
        return createDurableSubscriber(
            session,
            topic,
            name,
            messageSelector,
            noLocal
        );
    }
    
    // JMSMessageConsumerFactory��JavaDoc
    public TopicSubscriber createDurableSubscriber(
        Session session,
        String name,
        String messageSelector,
        boolean noLocal
    ) throws JMSMessageConsumerCreateException{
        if(destination == null){
            throw new JMSMessageConsumerCreateException("Topic is null.");
        }
        if(!(destination instanceof Topic)){
            throw new JMSMessageConsumerCreateException("Destination is not topic.");
        }
        return createDurableSubscriber(
            session,
            (Topic)destination,
            name,
            messageSelector,
            noLocal
        );
    }
    
    // JMSMessageConsumerFactory��JavaDoc
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
        if(topic == null){
            throw new JMSMessageConsumerCreateException("Topic is null.");
        }
        try{
            return session.createDurableSubscriber(
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
