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
import java.io.PrintWriter;
import java.io.Reader;
import java.io.StringWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.jms.TopicSession;

import jp.ossc.nimbus.core.ServiceBase;
import jp.ossc.nimbus.core.ServiceManagerFactory;
import jp.ossc.nimbus.core.ServiceName;
import jp.ossc.nimbus.service.codemaster.CodeMasterNotifyBean;
import jp.ossc.nimbus.service.interpreter.Interpreter;
import jp.ossc.nimbus.service.jms.JMSSessionFactory;
import jp.ossc.nimbus.service.jndi.JndiFinder;
import jp.ossc.nimbus.service.publish.ServerConnectionFactory;
import jp.ossc.nimbus.service.test.TestAction;
import jp.ossc.nimbus.service.test.TestActionEstimation;
import jp.ossc.nimbus.service.test.TestContext;

/**
 * {@link jp.ossc.nimbus.service.codemaster.CodeMasterFinder CodeMasterFinder}�ɍX�V�ʒm�𑗐M����e�X�g�A�N�V�����B<p>
 * ����̏ڍׂ́A{@link #execute(TestContext, String, Reader)}���Q�ƁB<br>
 * 
 * @author M.Takata
 */
public class CodeMasterNotifyActionService extends ServiceBase implements TestAction, TestActionEstimation, CodeMasterNotifyActionServiceMBean{
    
    private static final long serialVersionUID = 8118616880730155539L;
    
    protected ServiceName jndiFinderServiceName;
    protected JndiFinder jndiFinder;
    protected ServiceName jmsTopicSessionFactoryServiceName;
    protected JMSSessionFactory jmsTopicSessionFactory;
    protected String topicName;
    
    protected ServiceName serverConnectionFactoryServiceName;
    protected ServerConnectionFactory serverConnectionFactory;
    protected String subject;
    
    protected ServiceName interpreterServiceName;
    protected Interpreter interpreter;
    
    protected double expectedCost = 0d;
    
    public void setJndiFinderServiceName(ServiceName name){
        jndiFinderServiceName = name;
    }
    public ServiceName getJndiFinderServiceName(){
        return jndiFinderServiceName;
    }
    
    public void setJMSTopicSessionFactoryServiceName(ServiceName name){
        jmsTopicSessionFactoryServiceName = name;
    }
    public ServiceName getJMSTopicSessionFactoryServiceName(){
        return jmsTopicSessionFactoryServiceName;
    }
    
    public void setTopicName(String name){
        topicName = name;
    }
    public String getTopicName(){
        return topicName;
    }
    
    public void setServerConnectionFactoryServiceName(ServiceName name){
        serverConnectionFactoryServiceName = name;
    }
    public ServiceName getServerConnectionFactoryServiceName(){
        return serverConnectionFactoryServiceName;
    }
    
    public void setSubject(String subject){
        this.subject = subject;
    }
    public String getSubject(){
        return subject;
    }
    
    public void setInterpreterServiceName(ServiceName name){
        interpreterServiceName = name;
    }
    public ServiceName getInterpreterServiceName(){
        return interpreterServiceName;
    }
    
    public void setJndiFinder(JndiFinder jndiFinder){
        this.jndiFinder = jndiFinder;
    }
    
    public void setJMSTopicSessionFactory(JMSSessionFactory factory){
        jmsTopicSessionFactory = factory;
    }
    
    public void setServerConnectionFactory(ServerConnectionFactory factory){
        serverConnectionFactory = factory;
    }
    
    public void setInterpreter(Interpreter interpreter){
        this.interpreter = interpreter;
    }
    
    public void startService() throws Exception{
        if(jndiFinderServiceName != null){
            jndiFinder = (JndiFinder)ServiceManagerFactory.getServiceObject(jndiFinderServiceName);
        }
        if(jmsTopicSessionFactoryServiceName != null){
            jmsTopicSessionFactory = (JMSSessionFactory)ServiceManagerFactory.getServiceObject(jmsTopicSessionFactoryServiceName);
        }
        if(serverConnectionFactory == null && serverConnectionFactoryServiceName == null){
            throw new IllegalArgumentException("ServerConnectionFactory is null.");
        }
        if((jndiFinder == null || jmsTopicSessionFactory == null || topicName == null)
            && ((serverConnectionFactory == null && serverConnectionFactoryServiceName == null) || subject == null)){
            throw new IllegalArgumentException("JndiFinder and JMSTopicSessionFactory and TopicName, or ServerConnectionFactory and Subject must be specified.");
        }
        if(interpreterServiceName != null){
            interpreter = (Interpreter)ServiceManagerFactory.getServiceObject(interpreterServiceName);
        }
    }
    
    /**
     * {@link jp.ossc.nimbus.service.codemaster.CodeMasterFinder CodeMasterFinder}�ɍX�V�ʒm�𑗐M����B<p>
     * ���\�[�X�̃t�H�[�}�b�g�́A�ȉ��B<br>
     * <pre>
     * masterName
     * dateId
     * dataId
     * dataAndDataBindScript
     * </pre>
     * masterName�́A�X�V�ʒm�̑ΏۂƂȂ�}�X�^�����w�肷��B<br>
     * dateId�́A{@link CodeMasterNotifyBean}�ɐݒ肷��X�V������Date�I�u�W�F�N�g��ݒ肷����̂ŁA����e�X�g�P�[�X���ɁA����TestAction���O�ɁA�X�V������Date�I�u�W�F�N�g��߂��e�X�g�A�N�V���������݂���ꍇ�́A���̃A�N�V����ID���w�肷��B�܂��A����V�i���I���ɁA����TestAction���O�ɁA�X�V������Date�I�u�W�F�N�g��߂��e�X�g�A�N�V���������݂���ꍇ�́A�e�X�g�P�[�XID�ƃA�N�V����ID���J���}��؂�Ŏw�肷��B��s���w�肵���ꍇ�́A�X�V������Date�I�u�W�F�N�g��TestAction�̌��ʂ���擾���Ȃ��B<br>
     * dataId�́A{@link CodeMasterNotifyBean}�ɐݒ肷��X�V�����̃I�u�W�F�N�g��ݒ肷����̂ŁA����e�X�g�P�[�X���ɁA����TestAction���O�ɁA�X�V�����̃I�u�W�F�N�g��߂��e�X�g�A�N�V���������݂���ꍇ�́A���̃A�N�V����ID���w�肷��B�܂��A����V�i���I���ɁA����TestAction���O�ɁA�X�V�����̃I�u�W�F�N�g��߂��e�X�g�A�N�V���������݂���ꍇ�́A�e�X�g�P�[�XID�ƃA�N�V����ID���J���}��؂�Ŏw�肷��B��s���w�肵���ꍇ�́A�X�V�����̃I�u�W�F�N�g��TestAction�̌��ʂ���擾���Ȃ��B<br>
     * dataAndDataBindScript�́A{@link CodeMasterNotifyBean}�ɐݒ肷��X�V�����ƍX�V������ݒ肷��X�N���v�g���w�肷��B�X�N���v�g�́A{@link Interpreter#evaluate(String,Map)}�ŕ]������A�����̕ϐ��}�b�v�ɂ́A"context"��{@link TestContext}�A"notifyBean"��{@link CodeMasterNotifyBean}���n�����B<br>
     *
     * @param context �R���e�L�X�g
     * @param actionId �A�N�V����ID
     * @param resource ���\�[�X
     * @return ���M�Ɏg�p����{@link CodeMasterNotifyBean}
     */
    public Object execute(TestContext context, String actionId, Reader resource) throws Exception{
        BufferedReader br = new BufferedReader(resource);
        CodeMasterNotifyBean notifyBean = new CodeMasterNotifyBean();
        TopicSession session = null;
        if(jndiFinder != null && jmsTopicSessionFactory != null){
            notifyBean.setJndiFinder(jndiFinder);
            session = (TopicSession)jmsTopicSessionFactory.getSession();
            notifyBean.setResource(session);
            notifyBean.setTopicName(topicName);
        }else{
            ServerConnectionFactory scf = serverConnectionFactory;
            if(serverConnectionFactory == null && serverConnectionFactoryServiceName != null){
                scf = (ServerConnectionFactory)ServiceManagerFactory.getServiceObject(serverConnectionFactoryServiceName);
            }
            notifyBean.setServerConnection(scf.getServerConnection());
            notifyBean.setSubject(subject);
        }
        try{
            String masterName = br.readLine();
            if(masterName == null || masterName.length() == 0){
                throw new Exception("Unexpected EOF on masterName");
            }
            notifyBean.setMasterFlowKey(masterName);
            final String dateId = br.readLine();
            if(dateId != null && dateId.length() != 0){
                Object actionResult = null;
                if(dateId.indexOf(",") == -1){
                    actionResult = context.getTestActionResult(dateId);
                }else{
                    String[] ids = dateId.split(",");
                    if(ids.length != 2){
                        throw new Exception("Illegal dateId format. id=" + dateId);
                    }
                    actionResult = context.getTestActionResult(ids[0], ids[1]);
                }
                if(actionResult == null){
                    throw new Exception("TestActionResult not found. id=" + dateId);
                }
                if(!(actionResult instanceof Date)){
                    throw new Exception("TestActionResult is not instance of Date. type=" + actionResult.getClass());
                }
                notifyBean.setDate((Date)actionResult);
            }
            final String dataId = br.readLine();
            if(dataId != null && dataId.length() != 0){
                Object actionResult = null;
                if(dataId.indexOf(",") == -1){
                    actionResult = context.getTestActionResult(dataId);
                }else{
                    String[] ids = dataId.split(",");
                    if(ids.length != 2){
                        throw new Exception("Illegal dataId format. id=" + dataId);
                    }
                    actionResult = context.getTestActionResult(ids[0], ids[1]);
                }
                if(actionResult == null){
                    throw new Exception("TestActionResult not found. id=" + dataId);
                }
                notifyBean.setData(actionResult);
            }
            String dataAndDataBindScript = null;
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            try{
                String line = null;
                while((line = br.readLine()) != null){
                    pw.println(line);
                }
                pw.flush();
                dataAndDataBindScript = sw.toString();
                if(dataAndDataBindScript.length() == 0){
                    dataAndDataBindScript = null;
                }
            }finally{
                sw.close();
                pw.close();
            }
            if(dataAndDataBindScript != null){
                if(interpreter == null){
                    throw new UnsupportedOperationException("Interpreter is null.");
                }
                final Map params = new HashMap();
                params.put("context", context);
                params.put("notifyBean", notifyBean);
                interpreter.evaluate(dataAndDataBindScript, params);
            }
            notifyBean.addMessageAndSend();
        }finally{
            br.close();
            br = null;
            if(session != null){
                session.close();
            }
        }
        return notifyBean;
    }
    
    public void setExpectedCost(double cost) {
        expectedCost = cost;
    }
    
    public double getExpectedCost() {
        return expectedCost;
    }
}