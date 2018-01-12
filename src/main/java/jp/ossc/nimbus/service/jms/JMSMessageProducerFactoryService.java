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
 * JMS���b�Z�[�W�v���f���[�T�t�@�N�g���B<p>
 * javax.jms.Session�����b�v���AMessageProducer�̐������ȗ�������B<br>
 * Queue��Topi�̃C���^�t�F�[�X���������ꂽJMS 1.1�ɑΉ����Ă��܂��BJMS 1.1�ȑO�̃o�[�W�����Ŏg�p����ꍇ�ɂ́A�T�u�N���X��{@link JMSQueueSenderFactoryService}��A{@link JMSTopicPublisherFactoryService}���g�p���ĉ������B<br>
 * 
 * @author M.Takata
 */
public class JMSMessageProducerFactoryService extends ServiceBase
 implements JMSMessageProducerFactory, JMSMessageProducerFactoryServiceMBean{
    
    private static final long serialVersionUID = 8090980996008836232L;
    
    protected ServiceName jmsSessionFactoryServiceName;
    protected JMSSessionFactory jmsSessionFactory;
    
    protected ServiceName destinationFinderServiceName;
    protected JndiFinder destinationFinder;
    
    protected String destinationName;
    protected Destination destination;
    
    protected Session session;
    protected boolean isSessionCreate;
    protected boolean isCloseSession;
    protected int deliveryMode = Message.DEFAULT_DELIVERY_MODE;
    protected int priority = Message.DEFAULT_PRIORITY;
    protected long timeToLive = Message.DEFAULT_TIME_TO_LIVE;
    
    // JMSMessageProducerFactoryServiceMBean��JavaDoc
    public void setJMSSessionFactoryServiceName(ServiceName name){
        jmsSessionFactoryServiceName = name;
    }
    // JMSMessageProducerFactoryServiceMBean��JavaDoc
    public ServiceName getJMSSessionFactoryServiceName(){
        return jmsSessionFactoryServiceName;
    }
    
    // JMSMessageProducerFactoryServiceMBean��JavaDoc
    public void setDestinationFinderServiceName(ServiceName name){
        destinationFinderServiceName = name;
    }
    // JMSMessageProducerFactoryServiceMBean��JavaDoc
    public ServiceName getDestinationFinderServiceName(){
        return destinationFinderServiceName;
    }
    
    // JMSMessageProducerFactoryServiceMBean��JavaDoc
    public void setDestinationName(String name){
        destinationName = name;
    }
    // JMSMessageProducerFactoryServiceMBean��JavaDoc
    public String getDestinationName(){
        return destinationName;
    }
    
    // JMSMessageProducerFactoryServiceMBean��JavaDoc
    public void setSessionCreate(boolean isCreate){
        isSessionCreate = isCreate;
    }
    // JMSMessageProducerFactoryServiceMBean��JavaDoc
    public boolean isSessionCreate(){
        return isSessionCreate;
    }
    
    // JMSMessageProducerFactoryServiceMBean��JavaDoc
    public void setCloseSession(boolean isClose){
        isCloseSession = isClose;
    }
    // JMSMessageProducerFactoryServiceMBean��JavaDoc
    public boolean isCloseSession(){
        return isCloseSession;
    }
    
    // JMSMessageProducerFactoryServiceMBean��JavaDoc
    public void setDeliveryMode(int mode){
        deliveryMode = mode;
    }
    // JMSMessageProducerFactoryServiceMBean��JavaDoc
    public int getDeliveryMode(){
        return deliveryMode;
    }
    
    // JMSMessageProducerFactoryServiceMBean��JavaDoc
    public void setPriority(int priority){
        this.priority = priority;
    }
    // JMSMessageProducerFactoryServiceMBean��JavaDoc
    public int getPriority(){
        return priority;
    }
    
    // JMSMessageProducerFactoryServiceMBean��JavaDoc
    public void setTimeToLive(long ttl){
        this.timeToLive = ttl;
    }
    // JMSMessageProducerFactoryServiceMBean��JavaDoc
    public long getTimeToLive(){
        return timeToLive;
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
        
        destination = null;
    }
    
    // JMSMessageProducerFactory��JavaDoc
    public Session getSession(){
        return session;
    }
    
    // JMSMessageProducerFactory��JavaDoc
    public Destination getDestination(){
        return destination;
    }
    
    // JMSMessageProducerFactory��JavaDoc
    public JMSSessionFactory getSessionFactory(){
        return jmsSessionFactory;
    }
    
    // JMSMessageProducerFactory��JavaDoc
    public MessageProducer createProducer()
     throws JMSMessageProducerCreateException{
        return createProducer(destination);
    }
    
    // JMSMessageProducerFactory��JavaDoc
    public MessageProducer createProducer(Destination dest)
     throws JMSMessageProducerCreateException{
        Session session = this.session;
        if(session == null){
            try{
                session = jmsSessionFactory.getSession();
            }catch(JMSSessionCreateException e){
                throw new JMSMessageProducerCreateException(e);
            }
        }
        return createProducer(session, dest);
    }
    
    // JMSMessageProducerFactory��JavaDoc
    public MessageProducer createProducer(Session session, Destination dest)
     throws JMSMessageProducerCreateException{
        if(session == null){
            throw new JMSMessageProducerCreateException("Session is null.");
        }
        if(dest == null){
            throw new JMSMessageProducerCreateException("Destination is null.");
        }
        try{
            MessageProducer mp = session.createProducer(dest);
            mp.setDeliveryMode(deliveryMode);
            mp.setPriority(priority);
            mp.setTimeToLive(timeToLive);
            return mp;
        }catch(JMSException e){
            throw new JMSMessageProducerCreateException(e);
        }
    }
 }
