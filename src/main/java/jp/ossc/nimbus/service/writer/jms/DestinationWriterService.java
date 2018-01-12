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
package jp.ossc.nimbus.service.writer.jms;

import javax.jms.*;

import jp.ossc.nimbus.core.*;
import jp.ossc.nimbus.service.jms.JMSMessageProducerFactory;
import jp.ossc.nimbus.service.writer.WritableRecord;
import jp.ossc.nimbus.service.writer.MessageWriteException;

/**
 * JMS��Destination��Message����������MessageWriter�T�[�r�X�B<p>
 *
 * @author M.Takata
 */
public class DestinationWriterService extends ServiceBase
 implements DestinationWriterServiceMBean{
    
    private static final long serialVersionUID = 6016635831220272596L;
    
    // ���b�Z�[�WID��`
    private static final String JMSDW = "JMSDW";
    private static final String JMSDW0 = JMSDW + 0;
    private static final String JMSDW00 = JMSDW0 + 0;
    private static final String JMSDW000 = JMSDW00 + 0;
    private static final String JMSDW0000 = JMSDW000 + 0;
    private static final String JMSDW00001 = JMSDW0000 + 1;
    private static final String JMSDW00002 = JMSDW0000 + 2;
    private static final String JMSDW00003 = JMSDW0000 + 3;
    private static final String JMSDW00004 = JMSDW0000 + 4;
    
    protected static final String DEFAULT_MSG_CREATOR_CLASS_NAME
         = ObjectMessageCreator.class.getName();
    
    protected ServiceName jmsMessageProducerFactoryServiceName;
    protected JMSMessageProducerFactory messageProducerFactory;
    protected MessageProducer messageProducer;
    
    protected ServiceName jmsMessageCreatorServiceName;
    protected String jmsMessageCreatorClassName = DEFAULT_MSG_CREATOR_CLASS_NAME;
    protected MessageCreator messageCreator;
    
    protected int priority = Message.DEFAULT_PRIORITY;
    protected String deliveryMode;
    protected int delivery = Message.DEFAULT_DELIVERY_MODE;
    protected long lifeTime = Message.DEFAULT_TIME_TO_LIVE;
    
    // DestinationWriterServiceMBean��JavaDoc
    public void setJMSMessageProducerFactoryServiceName(ServiceName name){
        jmsMessageProducerFactoryServiceName = name;
    }
    
    // DestinationWriterServiceMBean��JavaDoc
    public ServiceName getJMSMessageProducerFactoryServiceName(){
        return jmsMessageProducerFactoryServiceName;
    }
    
    // DestinationWriterServiceMBean��JavaDoc
    public void setJMSMessageCreatorServiceName(ServiceName name){
        jmsMessageCreatorServiceName = name;
    }
    
    // DestinationWriterServiceMBean��JavaDoc
    public ServiceName getJMSMessageCreatorServiceName(){
        return jmsMessageCreatorServiceName;
    }
    
    // DestinationWriterServiceMBean��JavaDoc
    public void setJMSMessageCreatorClassName(String name){
        jmsMessageCreatorClassName = name;
    }
    
    // DestinationWriterServiceMBean��JavaDoc
    public String getJMSMessageCreatorClassName(){
        return jmsMessageCreatorClassName;
    }
    
    // DestinationWriterServiceMBean��JavaDoc
    public void setPriority(int priority){
        this.priority = priority;
    }
    
    // DestinationWriterServiceMBean��JavaDoc
    public int getPriority(){
        return priority;
    }
    
    // DestinationWriterServiceMBean��JavaDoc
    public void setDeliveryMode(String mode) throws IllegalArgumentException{
        deliveryMode = mode;
        if(DELIVERY_MODE_NON_PERSISTENT.equals(deliveryMode)){
            delivery = DeliveryMode.NON_PERSISTENT;
        }else if(DELIVERY_MODE_PERSISTENT.equals(deliveryMode)){
            delivery = DeliveryMode.PERSISTENT;
        }else{
            throw new IllegalArgumentException(
                "Unsupported delivery mode : " + mode
            );
        }
    }
    
    // DestinationWriterServiceMBean��JavaDoc
    public String getDeliveryMode(){
        return deliveryMode;
    }
    
    // DestinationWriterServiceMBean��JavaDoc
    public void setLifeTime(long lifeTime){
        this.lifeTime = lifeTime;
    }
    
    // DestinationWriterServiceMBean��JavaDoc
    public long getLifeTime(){
        return lifeTime;
    }
    
    public void setJMSMessageProducerFactory(JMSMessageProducerFactory fac) {
        messageProducerFactory = fac;
    }
    
    public void setJMSMessageCreator(MessageCreator creator) {
        messageCreator = creator;
    }
    
    public void startService() throws Exception{
        if(jmsMessageProducerFactoryServiceName != null){
            messageProducerFactory
                 = (JMSMessageProducerFactory)ServiceManagerFactory
                    .getServiceObject(jmsMessageProducerFactoryServiceName);
        }
        if(messageProducerFactory == null){
            throw new IllegalArgumentException(
                "JMSMessageProducerFactory is null"
            );
        }
        
        messageProducer = messageProducerFactory.createProducer();
        
        if(jmsMessageCreatorServiceName != null){
            messageCreator
                 = (MessageCreator)ServiceManagerFactory
                    .getServiceObject(jmsMessageCreatorServiceName);
        }
        if(messageCreator == null){
            final Class clazz = Class.forName(
                jmsMessageCreatorClassName,
                true,
                NimbusClassLoader.getInstance()
            );
            messageCreator = (MessageCreator)clazz.newInstance();
        }
    }
    
    public void stopService() throws Exception{
        try{
            messageProducer.close();
        }catch(JMSException e){
        }
        messageProducer = null;
    }
    
    public void write(WritableRecord rec) throws MessageWriteException{
        
        Session session = messageProducerFactory.getSession();
        
        Message message = null;
        try{
            message = messageCreator.createMessage(session, rec);
        }catch(Exception e){
            getLogger().write(JMSDW00001, e);
            throw new MessageWriteException(e);
        }
        try{
            if(message != null){
                send(message);
            }
        }catch(JMSException e){
            getLogger().write(JMSDW00002, e);
            try{
                session.rollback();
            }catch(Exception ex){
                getLogger().write(JMSDW00003, ex);
            }
            throw new MessageWriteException(e);
        }
        try{
            session.commit();
        }catch(Exception e){
            getLogger().write(JMSDW00004, e);
            throw new MessageWriteException(e);
        }
    }
    
    protected void send(Message message) throws JMSException{
        messageProducer.send(message, delivery, priority, lifeTime);
    }
}
