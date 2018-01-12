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
import java.util.Enumeration;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.ObjectMessage;
import javax.jms.TextMessage;

import jp.ossc.nimbus.core.ServiceBase;
import jp.ossc.nimbus.service.test.TestAction;
import jp.ossc.nimbus.service.test.TestActionEstimation;
import jp.ossc.nimbus.service.test.TestContext;

/**
 * {@link JMSMessageReceiverListenActionService.MessageListener}����M����{@link java.jms.Message Message}���擾����e�X�g�A�N�V�����B<p>
 * ����̏ڍׂ́A{@link #execute(TestContext, String, Reader)}���Q�ƁB<br>
 *
 * @author Y.Nakashima
 * @see JMSMessageReceiverListenActionService
 */
public class JMSMessageReceiverGetActionService extends ServiceBase implements TestAction, TestActionEstimation, JMSMessageReceiverGetActionServiceMBean{

    private static final long serialVersionUID = 6779163909892607718L;
    protected double expectedCost = 0d;

    /* �f�t�H���g���O����ݒ�ł��� */
    protected String defaultGetType = "message";
    protected long defaultTimeout = 10000;
    protected int defaultCount = 1;
    protected boolean defaultIsClose = true;

    public void setDefaultGetType(String defaultGetType) {
        this.defaultGetType = defaultGetType;
    }

    public void setDefaultTimeout(long defaultTimeout) {
        this.defaultTimeout = defaultTimeout;
    }

    public void setDefaultCount(int defaultCount) {
        this.defaultCount = defaultCount;
    }

    public void setDefaultIsClose(boolean defaultClose) {
        this.defaultIsClose = defaultClose;
    }

    /**
     * ���\�[�X�̓��e��ǂݍ���ŁA{@link JMSMessageReceiverListenActionService.MessageListener}����M����{@link ava.jms.Message Message}���擾����B<p>
     * ���\�[�X�̃t�H�[�}�b�g�́A�ȉ��B<br>
     * <pre>
     * listenerId
     * getType
     * timeout
     * count
     * close
     * </pre>
     * listenerId�́A���b�Z�[�W����M���Ă���{@link JMSMessageReceiverListenActionService}�̃A�N�V����ID���w�肷����̂ŁA����e�X�g�P�[�X���ɁA����TestAction���O�ɁA{@link JMSMessageReceiverListenActionService.MessageListener}��߂��e�X�g�A�N�V���������݂���ꍇ�́A���̃A�N�V����ID���w�肷��B�܂��A����V�i���I���ɁA����TestAction���O�ɁA{@link JMSMessageReceiverListenActionService.MessageListener}��߂��e�X�g�A�N�V���������݂���ꍇ�́A�e�X�g�P�[�XID�ƃA�N�V����ID���J���}��؂�Ŏw�肷��B<br>
     * getType�́A"message","text","object"�܂���"map"�B�f�t�H���g�w��\�B�f�t�H���g��"message"�B<br>
     * getType���̕ϊ��͈ȉ��̒ʂ�<br>
     *   "message"��javax.jms.Message���擾���A���̂܂ܕԂ��B<br>
     *   "text"��javax.jms.TextMessage���擾���AString�ɕϊ����ĕԂ�<br>
     *   "object"��javax.jms.ObjectMessage���擾���AObject�ɕϊ����ĕԂ�<br>
     *   "map"��javax.jms.MapMessage���擾���AMap�ɕϊ����ĕԂ�<br>
     * timeout�́A���b�Z�[�W�̎�M�҂��^�C���A�E�g[ms]���w�肷��B�f�t�H���g�w��\�B�f�t�H���g��10,000[ms]<br>
     * count�́A�擾���郁�b�Z�[�W�����w�肷��B�f�t�H���g�w��\�B�f�t�H���g��1�B<br>
     * close�́A{@link JMSMessageReceiverListenActionService.MessageListener#close()}���Ăяo�����ǂ������Atrue�܂���false�Ŏw�肷��B�f�t�H���g�w��\�B�f�t�H���g��true�B<br>
     *
     * @param context �R���e�L�X�g
     * @param actionId �A�N�V����ID
     * @param resource ���\�[�X
     * @return javax.jms.Message��getType���ɕϊ��������X�g
     */
    public Object execute(TestContext context, String actionId, Reader resource) throws Exception{
        BufferedReader br = new BufferedReader(resource);
        JMSMessageReceiverListenActionService.MessageListener listener = null;
        String getType = null;
        long timeout = 0;
        int count = 1;
        boolean isClose = true;

        // resource�̃p�[�X
        try{
            final String listenerId = br.readLine();
            if(listenerId == null || listenerId.length() == 0){
                throw new Exception("Unexpected EOF on listenerId");
            }
            Object actionResult = null;
            if(listenerId.indexOf(",") == -1){
                actionResult = context.getTestActionResult(listenerId);
            }else{
                String[] ids = listenerId.split(",");
                if(ids.length != 2){
                    throw new Exception("Illegal listenerId format. id=" + listenerId);
                }
                actionResult = context.getTestActionResult(ids[0], ids[1]);
            }
            if(actionResult == null){
                throw new Exception("TestActionResult not found. id=" + listenerId);
            }
            if(!(actionResult instanceof JMSMessageReceiverListenActionService.MessageListener)){
                throw new Exception("TestActionResult is not JMSMessageReceiverListenActionService.MessageListener. type=" + actionResult.getClass());
            }
            listener = (JMSMessageReceiverListenActionService.MessageListener)actionResult;
            getType = br.readLine();
            if(getType == null || getType.length() == 0){
                getType = defaultGetType;
            }

            // stream��byte�̓T�|�[�g���Ȃ�
            if(!"message".equals(getType) && !"text".equals(getType) && !"map".equals(getType) && !"object".equals(getType)){
                throw new UnsupportedOperationException("Illegal getType : " + getType);
            }

            final String timeoutStr = br.readLine();
            if(timeoutStr == null || timeoutStr.length() == 0){
                timeout = defaultTimeout;
            } else {
                try{
                    timeout = Long.parseLong(timeoutStr);
                }catch(NumberFormatException e){
                    throw new Exception("Illegal timeout format. timeout=" + timeoutStr);
                }
            }

            final String countStr = br.readLine();
            if(countStr == null || countStr.length() == 0){
                count = defaultCount;
            } else {
                try{
                    count = Integer.parseInt(countStr);
                }catch(NumberFormatException e){
                    throw new Exception("Illegal timeout format. count=" + countStr);
                }
            }

            final String close = br.readLine();
            if(close == null || close.length() == 0){
                isClose = defaultIsClose;
            } else {
                isClose = Boolean.valueOf(close).booleanValue();
            }
        }finally{
            br.close();
            br = null;
        }

        // Message���擾���AList�ɒǉ����ĕԂ�
        // �ȉ��̂悤��getType���ɕԂ�����ς���
        //   text: TextMessage �� List<String>
        //   object: ObjectMessage �� List<Object>
        //   map: MapMessage �� List<Map>

        List result = null;
        List msgList = null;

        try{
            if(listener.waitMessage(count, timeout)){
                msgList = listener.getReceiveMessageList();
            }

            if(msgList != null && msgList.size() != 0) {
                result = new ArrayList();
                for(int i=0; i<msgList.size(); i++) {
                    Message message = (Message) msgList.get(i);
                    if("text".equals(getType)) {
                        result.add(parseTextMessageToString(message));
                    } else if("map".equals(getType)) {
                        result.add(parseMapMessageToMap(message));
                    } else if ("object".equals(getType)) {
                        result.add(parseObjectMessageToObject(message));
                    } else {
                        result.add(message);
                    }
                }
            }

        }finally{
            if(isClose){
                listener.close();
            }
        }
        return result;
    }

    private static String parseTextMessageToString(Message message) throws JMSException {
        TextMessage textMessage = (TextMessage) message;
        return textMessage.getText();
    }

    private static Object parseObjectMessageToObject(Message message) throws JMSException {
        ObjectMessage objectMessage = (ObjectMessage) message;
        return objectMessage.getObject();
    }

    private static Map parseMapMessageToMap(Message message) throws JMSException {
        Map ret = new LinkedHashMap();

        MapMessage mapMessage = (MapMessage) message;
        Enumeration keys =  mapMessage.getMapNames();
        while(keys.hasMoreElements()) {
            String key = keys.nextElement().toString();
            Object value =  mapMessage.getObject(key);
            ret.put(key, value);
        }

        return ret;
    }

    public void setExpectedCost(double cost) {
        expectedCost = cost;
    }

    public double getExpectedCost() {
        return expectedCost;
    }
}
