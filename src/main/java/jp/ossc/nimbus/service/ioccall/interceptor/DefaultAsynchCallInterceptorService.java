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
package jp.ossc.nimbus.service.ioccall.interceptor;

import java.io.*;
import javax.jms.*;

import jp.ossc.nimbus.ioc.*;
import jp.ossc.nimbus.core.*;
import jp.ossc.nimbus.service.aspect.interfaces.InterceptorException;
import jp.ossc.nimbus.service.aspect.interfaces.TargetCheckedException;
import jp.ossc.nimbus.service.aspect.interfaces.TargetUncheckedException;
import jp.ossc.nimbus.service.aop.InvocationContext;
import jp.ossc.nimbus.service.aop.MethodInvocationContext;
import jp.ossc.nimbus.service.resource.*;
import jp.ossc.nimbus.service.resource.jmsqueue.QueueTransanctionResource;

/**
 * IOC �񓯊��Ăяo����MDB�p�̃C���^�[�Z�v�^�B<p>
 * 
 * @version $Name:  $
 * @author H.Nakano
 * @since 1.0
 */
public class DefaultAsynchCallInterceptorService extends ServiceBase
 implements DefaultAsynchCallInterceptorServiceMBean,
            jp.ossc.nimbus.service.aspect.interfaces.Interceptor,
            jp.ossc.nimbus.service.aop.Interceptor{
    
    private static final long serialVersionUID = 5995567179134254095L;
    
    private ServiceName queueSessionFactoryServiceName;
    private ResourceFactory queueSessionFactory;
    
    private String deliveryModeStr = DELIVERY_MODE_PERSISTENT;
    private int deliveryMode = Message.DEFAULT_DELIVERY_MODE;
    
    private int priority = Message.DEFAULT_PRIORITY;
    private long timeToLive = Message.DEFAULT_TIME_TO_LIVE;
    
    private boolean isIgnoreRedelivery;
    
    private long redeliveryInterval;
    
    // DefaultAsynchCallInterceptorServiceMBean��JavaDoc
    public void setQueueSessionFactoryServiceName(ServiceName name){
        queueSessionFactoryServiceName = name;
    }
    // DefaultAsynchCallInterceptorServiceMBean��JavaDoc
    public ServiceName getQueueSessionFactoryServiceName(){
        return queueSessionFactoryServiceName;
    }
    
    // DefaultAsynchCallInterceptorServiceMBean��JavaDoc
    public void setDeliveryMode(String mode){
        if(DELIVERY_MODE_PERSISTENT.equals(mode)){
            deliveryMode = DeliveryMode.PERSISTENT;
        }else if(DELIVERY_MODE_NON_PERSISTENT.equals(mode)){
            deliveryMode = DeliveryMode.NON_PERSISTENT;
        }else{
            throw new IllegalArgumentException(mode);
        }
        deliveryModeStr = mode;
    }
    // DefaultAsynchCallInterceptorServiceMBean��JavaDoc
    public String getDeliveryMode(){
        return deliveryModeStr;
    }
    
    // DefaultAsynchCallInterceptorServiceMBean��JavaDoc
    public void setPriority(int priority){
        this.priority = priority;
    }
    // DefaultAsynchCallInterceptorServiceMBean��JavaDoc
    public int getPriority(){
        return priority;
    }
    
    // DefaultAsynchCallInterceptorServiceMBean��JavaDoc
    public void setTimeToLive(long millis){
        timeToLive = millis;
    }
    // DefaultAsynchCallInterceptorServiceMBean��JavaDoc
    public long getTimeToLive(){
        return timeToLive;
    }
    
    // DefaultAsynchCallInterceptorServiceMBean��JavaDoc
    public void setIgnoreRedelivery(boolean ignore){
        isIgnoreRedelivery = ignore;
    }
    // DefaultAsynchCallInterceptorServiceMBean��JavaDoc
    public boolean isIgnoreRedelivery(){
        return isIgnoreRedelivery;
    }
    
    // DefaultAsynchCallInterceptorServiceMBean��JavaDoc
    public void setRedeliveryInterval(long millis){
        redeliveryInterval = millis;
    }
    // DefaultAsynchCallInterceptorServiceMBean��JavaDoc
    public long getRedeliveryInterval(){
        return redeliveryInterval;
    }
    
    /**
     * QueueSession�擾�p��ResourceFactory��ݒ肷��B<p>
     *
     * @param factory ResourceFactory
     */
    public void setQueueSessionResourceFactory(ResourceFactory factory) {
        this.queueSessionFactory = factory;
    }
    
    /**
     * �T�[�r�X�̊J�n�������s���B<p>
     *
     * @exception Exception �T�[�r�X�̊J�n�����Ɏ��s�����ꍇ
     */
    public void startService() throws Exception{
        if(queueSessionFactoryServiceName != null){
            queueSessionFactory = (ResourceFactory)ServiceManagerFactory
                .getServiceObject(queueSessionFactoryServiceName);
        }
    }
    
    public Object invokeChain(
        Object inputObj,
        jp.ossc.nimbus.service.aspect.interfaces.InterceptorChain interceptChain
    ) throws InterceptorException, TargetCheckedException,
             TargetUncheckedException{
        try{
            return invokeInternal(inputObj, interceptChain, null);
        }catch(InterceptorException e){
            throw e;
        }catch(TargetCheckedException e){
            throw e;
        }catch(TargetUncheckedException e){
            throw e;
        }catch(Throwable th){
            throw new InterceptorException(th);
        }
    }
    
    public Object invoke(
        InvocationContext context,
        jp.ossc.nimbus.service.aop.InterceptorChain chain
    ) throws Throwable{
        return invokeInternal(context, null, chain);
    }
    
    
    protected Object invokeInternal(
        Object inputObj,
        jp.ossc.nimbus.service.aspect.interfaces.InterceptorChain interceptChain,
        jp.ossc.nimbus.service.aop.InterceptorChain chain
    ) throws Throwable{
        Object ret = null;
        Object input = inputObj;
        if(chain != null){
            input = ((MethodInvocationContext)input).getParameters()[0];
        }
        if(input instanceof ObjectMessage){
            ObjectMessage msg = (ObjectMessage)input;
            FacadeValue in = null;
            if(isIgnoreRedelivery){
                try{
                    if(msg.getJMSRedelivered()){
                        return ret;
                    }
                }catch(JMSException e) {
                    throw new InterceptorException(
                        "getJMSRedelivered JMSException",
                        e
                    );
                }
            }
            if(redeliveryInterval > 0){
                try{
                    if(msg.getJMSRedelivered()){
                        Thread.sleep(redeliveryInterval);
                    }
                }catch(InterruptedException e) {
                }catch(JMSException e) {
                    throw new InterceptorException(
                        "getJMSRedelivered JMSException",
                        e
                    );
                }
            }
            try{
                in = (FacadeValue)msg.getObject();
            }catch(JMSException e) {
                throw new InterceptorException("getObject JMSException", e);
            }
            try{
                if(interceptChain != null){
                    ret = interceptChain.invokeChain(in);
                }else{
                    final MethodInvocationContext context
                         = (MethodInvocationContext)inputObj;
                    context.setParameters(new Object[]{in});
                    ret = chain.invokeNext(context);
                }
            }catch(TargetUncheckedException e){
                Throwable th = e.getCause();
                if(th instanceof RuntimeException){
                    if(queueSessionFactory != null){
                        try{
                            Queue replyQueue = (Queue)msg.getJMSReplyTo();
                            if(replyQueue == null){
                                throw e;
                            }
                        }catch(JMSException e2){
                            throw new InterceptorException("Unexpected Exception", e2);
                        }
                    }
                    ret = th;
                }else{
                    throw new InterceptorException("Unexpected Exception", th);
                }
            }catch(TargetCheckedException e){
                //�����ɔ��ł��邱�Ƃ͂Ȃ��B
                //�R�}���h�w�ŃL���b�`���ꏈ���ς݂̂͂��B
                e.printStackTrace();
            }
            if(queueSessionFactory != null){
                try{
                    Queue replyQueue = (Queue)msg.getJMSReplyTo();
                    if(replyQueue != null){
                        QueueTransanctionResource resource = null;
                        try{
                            resource = (QueueTransanctionResource)queueSessionFactory.makeResource(null);
                            QueueSession session
                                 = (QueueSession)resource.getObject();
                            QueueSender sender = session.createSender(replyQueue);
                            sender.send(
                                session.createObjectMessage((Serializable)ret),
                                deliveryMode,
                                priority,
                                timeToLive
                            );
                        }finally{
                            if(resource != null){
                                resource.close();
                            }
                        }
                    }
                }catch(Exception e){
                    throw new InterceptorException("Unexpected Exception", e);
                }
            }
        }else{
            if(interceptChain != null){
                ret = interceptChain.invokeChain(input);
            }else{
                ret = chain.invokeNext((InvocationContext)inputObj);
            }
        }
        return ret;
    }

}
