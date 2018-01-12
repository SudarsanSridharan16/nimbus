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
import java.util.List;

import jp.ossc.nimbus.core.ServiceBase;
import jp.ossc.nimbus.service.test.TestAction;
import jp.ossc.nimbus.service.test.TestActionEstimation;
import jp.ossc.nimbus.service.test.TestContext;

/**
 * {@link MessageReceiverListenActionService.MessageListener}����M����{@link jp.ossc.nimbus.service.publish.Message Message}���擾����e�X�g�A�N�V�����B<p>
 * ����̏ڍׂ́A{@link #execute(TestContext, String, Reader)}���Q�ƁB<br>
 * 
 * @author M.Takata
 * @see MessageReceiverListenActionService
 */
public class MessageReceiverGetActionService extends ServiceBase implements TestAction, TestActionEstimation, MessageReceiverGetActionServiceMBean{
    
    private static final long serialVersionUID = 6779163909892607718L;
    protected double expectedCost = 0d;
    
    /**
     * ���\�[�X�̓��e��ǂݍ���ŁA{@link MessageReceiverListenActionService.MessageListener}����M����{@link jp.ossc.nimbus.service.publish.Message Message}���擾����B<p>
     * ���\�[�X�̃t�H�[�}�b�g�́A�ȉ��B<br>
     * <pre>
     * listenerId
     * getType
     * timeout
     * close
     * </pre>
     * listenerId�́A���b�Z�[�W����M���Ă���{@link MessageReceiverListenActionService}�̃A�N�V����ID���w�肷����̂ŁA����e�X�g�P�[�X���ɁA����TestAction���O�ɁA{@link MessageReceiverListenActionService.MessageListener}��߂��e�X�g�A�N�V���������݂���ꍇ�́A���̃A�N�V����ID���w�肷��B�܂��A����V�i���I���ɁA����TestAction���O�ɁA{@link MessageReceiverListenActionService.MessageListener}��߂��e�X�g�A�N�V���������݂���ꍇ�́A�e�X�g�P�[�XID�ƃA�N�V����ID���J���}��؂�Ŏw�肷��B<br>
     * getType�́A"message"�܂���"object"�B"message"�̏ꍇ�́A{@link jp.ossc.nimbus.service.publish.Message Message}�̃��X�g���擾����B"object"�̏ꍇ�́A{@link jp.ossc.nimbus.service.publish.Message#getObject() Message.getObject()}�̃��X�g���擾����B<br>
     * timeout�́A���b�Z�[�W�̎�M�҂��^�C���A�E�g[ms]���w�肷��B<br>
     * close�́A{@link MessageReceiverListenActionService.MessageListener#close()}���Ăяo�����ǂ������Atrue�܂���false�Ŏw�肷��B�w�肵�Ȃ��ꍇ�́A�N���[�Y����B<br>
     *
     * @param context �R���e�L�X�g
     * @param actionId �A�N�V����ID
     * @param resource ���\�[�X
     * @return {@link jp.ossc.nimbus.service.publish.Message Message}�̃��X�g�A�܂���{@link jp.ossc.nimbus.service.publish.Message#getObject() Message.getObject()}�̃��X�g
     */
    public Object execute(TestContext context, String actionId, Reader resource) throws Exception{
        BufferedReader br = new BufferedReader(resource);
        MessageReceiverListenActionService.MessageListener listener = null;
        String getType = null;
        long timeout = 0;
        boolean isClose = true;
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
            if(!(actionResult instanceof MessageReceiverListenActionService.MessageListener)){
                throw new Exception("TestActionResult is not MessageReceiverListenActionService.MessageListener. type=" + actionResult.getClass());
            }
            listener = (MessageReceiverListenActionService.MessageListener)actionResult;
            getType = br.readLine();
            if(getType == null || getType.length() == 0){
                throw new Exception("Unexpected EOF on getType");
            }
            if(!"message".equals(getType) && !"object".equals(getType)){
                throw new Exception("Illegal getType : " + getType);
            }
            final String timeoutStr = br.readLine();
            if(timeoutStr == null || timeoutStr.length() == 0){
                throw new Exception("Unexpected EOF on timeout");
            }
            try{
                timeout = Long.parseLong(timeoutStr);
            }catch(NumberFormatException e){
                throw new Exception("Illegal timeout format. timeout=" + timeoutStr);
            }
            final String close = br.readLine();
            if(close != null && close.length() != 0){
                isClose = Boolean.valueOf(close).booleanValue();
            }
        }finally{
            br.close();
            br = null;
        }
        List result = null;
        try{
            if(listener.waitMessage(timeout)){
                if("message".equals(getType)){
                    result = listener.getReceiveMessageList();
                }else{
                    result = listener.getReceiveMessageObjectList();
                }
            }
        }finally{
            if(isClose){
                listener.close();
            }
        }
        return result;
    }
    
    public void setExpectedCost(double cost) {
        expectedCost = cost;
    }
    
    public double getExpectedCost() {
        return expectedCost;
    }
}
