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
package jp.ossc.nimbus.service.test.action;

import java.io.BufferedReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

import javax.jms.Destination;
import javax.jms.JMSException;

import jp.ossc.nimbus.core.ServiceBase;
import jp.ossc.nimbus.core.ServiceName;
import jp.ossc.nimbus.core.ServiceManagerFactory;
import jp.ossc.nimbus.service.test.TestContext;
import jp.ossc.nimbus.util.SynchronizeMonitor;
import jp.ossc.nimbus.util.WaitSynchronizeMonitor;
import jp.ossc.nimbus.service.test.TestAction;
import jp.ossc.nimbus.service.test.TestActionEstimation;
import jp.ossc.nimbus.service.jms.JMSMessageConsumerFactory;
import jp.ossc.nimbus.service.jndi.JndiFinder;

import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.Session;

/**
 * {@link javax.jms.MessageConsumer}����{@link java.jms.Message}����M����e�X�g�A�N�V�����B<p>
 * ����̏ڍׂ́A{@link #execute(TestContext, String, Reader)}���Q�ƁB<br>
 *
 * @author Y.Nakashima
 */
public class JMSMessageReceiverListenActionService extends ServiceBase implements TestAction, TestActionEstimation, JMSMessageReceiverListenActionServiceMBean{

    private static final long serialVersionUID = -165396344130216716L;
    protected ServiceName jMSMessageConsumerFactoryServiceName;
    protected JMSMessageConsumerFactory jMSMessageConsumerFactory;

    protected JndiFinder jndiFinder;
    protected ServiceName jndiFinderServiceName;

    protected MessageConsumer consumer;
    protected Session session;

    protected double expectedCost = 0d;

    public void setJMSMessageConsumerFactoryServiceName(ServiceName name) {
        jMSMessageConsumerFactoryServiceName = name;
    }

    public void setJMSMessageConsumerFactory(JMSMessageConsumerFactory factory) {
        jMSMessageConsumerFactory = factory;
    }

    public void setJndiFinder(JndiFinder finder) {
        jndiFinder = finder;
    }

    public void setJndiFinderServiceName(ServiceName name) {
        jndiFinderServiceName = name;
    }

    public ServiceName getJMSMessageConsumerFactoryServiceName() {
        return jMSMessageConsumerFactoryServiceName;
    }

    public ServiceName getJndiFinderServiceName() {
        return jndiFinderServiceName;
    }

    public void setExpectedCost(double cost) {
        expectedCost = cost;
    }

    public double getExpectedCost() {
        return expectedCost;
    }


    private static Object getService(ServiceName name, boolean isThrowException) {
        if(name == null) {
            return null;
        }

        Object ret = ServiceManagerFactory.getServiceObject(name);
        if(ret != null){
            return ret;
        }

        if(isThrowException) {
            throw new IllegalArgumentException(name + " is null.");
        }

        return null;
    }


    public void startService() throws Exception{
        jndiFinder = (JndiFinder)getService(jndiFinderServiceName ,false);

        jMSMessageConsumerFactory = (JMSMessageConsumerFactory)getService(jMSMessageConsumerFactoryServiceName ,true);
    }

    public void stopService() throws Exception{
        if(consumer != null) {
            consumer.close();
        }

        if(session != null) {
            session.close();
        }
    }

    /**
     * ���\�[�X�̓��e��ǂݍ���ŁA{@link javax.jms.MessageConsumer}��{@link MessageListener}��o�^���āA��M�o�^���s���B<p>
     * {@link javax.jms.Session}��execute���Ă΂�邽�тɐ�������A{@link MessageListener#close()}���Ă΂���close�����B
     * ���\�[�X�̃t�H�[�}�b�g�́A�ȉ��B<br>
     * <pre>
     * destinationName
     * messageSelector
     * </pre>
     * destinationName�́A{@link javax.jms.MessageConsumer}�Ɏ�M�o�^����z�M����w�肷��B�f�t�H���g��null�B<br>
     * messageSelector�́A{@link javax.jms.MessageConsumer}��selector���w�肵�A��M�I������B�f�t�H���gnull�B<br>
     *
     * @param context �R���e�L�X�g
     * @param actionId �A�N�V����ID
     * @param resource ���\�[�X
     * @return �o�^����{@link MessageListener}
     */
    public Object execute(TestContext context, String actionId, Reader resource) throws Exception{
        BufferedReader br = new BufferedReader(resource);
        final MessageListener messageListener = new MessageListener();

        String destinationName = null;
        String messageSelector = null;

        try{
            destinationName = br.readLine();
            messageSelector = br.readLine();
        }finally{
            br.close();
            br = null;
        }
        if(destinationName != null && jndiFinder == null){
            throw new Exception("Destination illegal settings. destinationName or jndiFinder must be set.");
        }

        Destination destination = null;
        if(destinationName != null && destinationName.length() != 0){
            destination = (Destination) jndiFinder.lookup(destinationName);
        }
        if(destination == null) {
            destination = jMSMessageConsumerFactory.getDestination();
        }

        // ���̃A�N�V���������s����邽�т�session�𐶐����Alistener��close�����session��close����
        session = jMSMessageConsumerFactory.getSessionFactory().getSession();

        if(session == null) {
            throw new Exception("Can't get session.");
        }

        if(destination != null && messageSelector != null) {
            consumer = (MessageConsumer)jMSMessageConsumerFactory.createConsumer(session, destination, messageSelector);
        } else if(destination != null){
            consumer = (MessageConsumer)jMSMessageConsumerFactory.createConsumer(session, destination);
        } else {
            consumer = (MessageConsumer)jMSMessageConsumerFactory.createConsumer(session);
        }
        consumer.setMessageListener(messageListener);

        return messageListener;
    }

    public class MessageListener implements javax.jms.MessageListener{
        protected List receiveMessageList = new ArrayList();
        protected SynchronizeMonitor waitMonitor = new WaitSynchronizeMonitor();
        protected int waitCount = 1;

        public void onMessage(Message message){
            synchronized(receiveMessageList){
                receiveMessageList.add(message);
                if(waitMonitor.isWait() && waitCount <= receiveMessageList.size()){
                    waitMonitor.notifyAllMonitor();
                }
            }
        }

        public boolean waitMessage(long timeout) throws InterruptedException{
            return waitMessage(1, timeout);
        }

        public boolean waitMessage(int count, long timeout) throws InterruptedException{
            synchronized(receiveMessageList){
                if(count <= receiveMessageList.size()){
                    return true;
                }
                waitCount = count;
                waitMonitor.initMonitor();
            }
            return waitMonitor.waitMonitor(timeout);
        }

        public List getReceiveMessageList(){
            List result = new ArrayList();
            synchronized(receiveMessageList){
                for(int i = 0; i < receiveMessageList.size(); i++){
                    result.add(receiveMessageList.get(i));
                }
            }
            return result;
        }

        public void close(){
            if(consumer != null){
                try{
                    consumer.setMessageListener(null);
                    consumer.close();
                }catch(Exception e){}
            }

            if(session != null) {
                try {
                    session.close();
                } catch (JMSException e) {}
            }
        }

        protected void finalize() throws Throwable{
            close();
        }
    }

}
