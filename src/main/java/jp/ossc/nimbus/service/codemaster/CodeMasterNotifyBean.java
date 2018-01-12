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
// �p�b�P�[�W
package jp.ossc.nimbus.service.codemaster;
//�C���|�[�g
import java.util.*;
import java.io.*;
import javax.jms.*;
import javax.naming.NamingException;
import jp.ossc.nimbus.service.jndi.*;
import jp.ossc.nimbus.service.publish.ServerConnection;
import jp.ossc.nimbus.service.publish.MessageSendException;
import jp.ossc.nimbus.service.publish.MessageException;
import jp.ossc.nimbus.service.publish.MessageCreateException;

/**
 * �R�[�h�}�X�^�[�Ǘ��Ƀ}�X�^�[����ւ����w������Bean
 * 
 * @version $Name:  $
 * @author H.Nakano
 * @since 1.0
 */
public class CodeMasterNotifyBean extends HashMap{
    
    private static final long serialVersionUID = 3508475057737920813L;
    
    /** Topic�����b�N�A�b�v����Finder */
    private JndiFinder mTopicFinder;
    /** Topic���\�[�X */
    private TopicSession mRes;
    /** �t���[�L�[*/
    private String mFlowKey;
    /** �X�V���t */
    private Date mDate;
    /** �X�V���t */
    private Object mData;
    /** Topic �� */
    private String mTopicName;
    
    private String subject;
    private ServerConnection connection;
    private Set flowNameSet = new LinkedHashSet();
    
    public void setSubject(String subject){
        this.subject = subject;
    }
    
    public void setServerConnection(ServerConnection connection){
        this.connection = connection;
    }
    
    /**
     * ���M��̃g�s�b�N����ݒ肷��B<p>
     * 
     * @param name �g�s�b�N��
     */
    public void setTopicName(String name){
        this.mTopicName = name;
    }
    
    /**
     * �g�s�b�N�Z�b�V������ݒ肷��B<p>
     * 
     * @param rc �g�s�b�N�Z�b�V����
     */
    public void setResource(TopicSession rc){
        mRes = rc;
    }
    
    /**
     * �X�V����}�X�^����ݒ肷��B<p>
     *
     * @param masterKey �}�X�^��
     */
    public void setMasterFlowKey(String masterKey){
        mFlowKey = masterKey;
    }
    
    /**
     * �X�V����}�X�^���L���ɂȂ�J�n������ݒ肷��B<p>
     * �ݒ肵�Ȃ��ꍇ�́A�g�s�b�N�ʒm�����B�����������A�L���J�n�����ƂȂ�B<br>
     *
     * @param effectiveDate �L���J�n����
     */
    public void setDate(Date effectiveDate){
        mDate = effectiveDate;
    }
    
    /**
     * �}�X�^�X�V�����ւ̓��͏���ݒ肷��B<p>
     *
     * @param data ���͏��
     */
    public void setData(Object data){
        this.mData = data;
    }
    
    /**
     * ���M��g�s�b�N��T��{@link JndiFinder}�T�[�r�X��ݒ肷��B<p>
     * 
     * @param finder JndiFinder�T�[�r�X
     */
    public void setJndiFinder(JndiFinder finder){
        this.mTopicFinder = finder;
    }
    
    /**
     * ���M���b�Z�[�W���쐬����B<p>
     */
    public void addMessage(){
        if(mFlowKey == null){
            return ;
        }
        flowNameSet.add(mFlowKey);
        this.put(mFlowKey, mData) ;
        this.put(
            mFlowKey + CodeMasterService.UPDATE_TIME_KEY,
            mDate == null ? new Date() : mDate
        ) ;
        mDate = null ;
        mFlowKey = null ;
    }
    
    /**
     * ���b�Z�[�W���g�s�b�N�ɑ��M����B<p>
     * 
     * @exception JMSException ���M�Ɏ��s�����ꍇ
     * @exception NamingException �g�s�b�N��������Ȃ��ꍇ
     * @exception MessageCreateException ���b�Z�[�W�̐����Ɏ��s�����ꍇ
     * @exception MessageSendException ���M�Ɏ��s�����ꍇ
     * @exception MessageException ���b�Z�[�W�̐����Ɏ��s�����ꍇ
     */
    public void send() throws JMSException, NamingException, MessageCreateException, MessageSendException, MessageException{
        if(size() == 0){
            return ;
        }
        if(connection == null){
            TopicSession session = mRes ;
            MapMessage msg = session.createMapMessage();
            Iterator flowNames = flowNameSet.iterator();
            while(flowNames.hasNext()){
                String flowName = (String)flowNames.next();
                
                String dateKey = flowName + CodeMasterService.UPDATE_TIME_KEY;
                setObject(msg, dateKey, get(dateKey));
                
                setObject(msg, flowName, get(flowName));
            }
            Topic tp = null ;
            if(this.mTopicName== null){
                tp = (Topic)this.mTopicFinder.lookup() ;
            }else{
                tp = (Topic)this.mTopicFinder.lookup(mTopicName) ;
            }
            TopicPublisher tpub = session.createPublisher(tp) ;
            msg.setJMSDeliveryMode(DeliveryMode.PERSISTENT) ;
            tpub.publish(msg) ;
        }else{
            Iterator flowNames = flowNameSet.iterator();
            while(flowNames.hasNext()){
                String flowName = (String)flowNames.next();
                jp.ossc.nimbus.service.publish.Message msg = connection.createMessage(
                    subject,
                    flowName
                );
                Map map = new HashMap();
                map.put(flowName, get(flowName));
                String dateKey = flowName + CodeMasterService.UPDATE_TIME_KEY;
                map.put(dateKey, get(dateKey));
                msg.setObject(map);
                connection.send(msg);
            }
        }
        flowNameSet.clear();
        this.clear();
    }
    
    private void setObject(MapMessage msg, String key, Object obj) throws JMSException{
        if(obj == null){
            msg.setString(key,null);
        }else if(obj instanceof Date){
            Date dt = (Date)obj ;
            msg.setLong(key,dt.getTime()) ;
        }else if(obj instanceof Boolean
            || obj instanceof Byte
            || obj instanceof byte[]
            || obj instanceof Character
            || obj instanceof Double
            || obj instanceof Float
            || obj instanceof Integer
            || obj instanceof Long
            || obj instanceof Short
            || obj instanceof String
        ){
            msg.setObject(key, obj);
        }else{
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream oos = null;
            try{
                oos = new ObjectOutputStream(baos);
                oos.writeObject(obj);
                oos.flush();
            }catch(IOException e){
                JMSException ex = new JMSException("Not serializable : " + obj);
                ex.setLinkedException(e);
                throw ex;
            }finally{
                if(oos != null){
                    try{
                        oos.close();
                    }catch(IOException e){
                    }
                }
            }
            msg.setBytes(key, baos.toByteArray());
        }
    }
    
    /**
     * ���b�Z�[�W�쐬�ƃg�s�b�N�ւ̑��M�̗������s���B<p>
     * 
     * @exception JMSException ���M�Ɏ��s�����ꍇ
     * @exception NamingException �g�s�b�N��������Ȃ��ꍇ
     * @exception MessageCreateException ���b�Z�[�W�̐����Ɏ��s�����ꍇ
     * @exception MessageSendException ���M�Ɏ��s�����ꍇ
     * @exception MessageException ���b�Z�[�W�̐����Ɏ��s�����ꍇ
     */
    public void addMessageAndSend() throws JMSException, NamingException, MessageCreateException, MessageSendException, MessageException{
        addMessage();
        send();
    }
}
