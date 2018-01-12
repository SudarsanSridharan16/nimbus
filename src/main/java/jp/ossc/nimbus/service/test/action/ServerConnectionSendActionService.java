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
import java.io.StringWriter;
import java.io.PrintWriter;
import java.util.Map;
import java.util.HashMap;

import jp.ossc.nimbus.core.ServiceBase;
import jp.ossc.nimbus.core.ServiceName;
import jp.ossc.nimbus.core.ServiceManagerFactory;
import jp.ossc.nimbus.io.CSVReader;
import jp.ossc.nimbus.service.test.TestContext;
import jp.ossc.nimbus.service.test.TestAction;
import jp.ossc.nimbus.service.test.TestActionEstimation;
import jp.ossc.nimbus.service.test.ChainTestAction;
import jp.ossc.nimbus.service.publish.ServerConnectionFactory;
import jp.ossc.nimbus.service.publish.ServerConnection;
import jp.ossc.nimbus.service.publish.Message;
import jp.ossc.nimbus.service.interpreter.Interpreter;

/**
 * {@link ServerConnection}��{@link Message}�𑗐M����e�X�g�A�N�V�����B<p>
 * ����̏ڍׂ́A{@link #execute(TestContext, String, Reader)}���Q�ƁB<br>
 * 
 * @author M.Takata
 */
public class ServerConnectionSendActionService extends ServiceBase implements TestAction, ChainTestAction.TestActionProcess, TestActionEstimation, ServerConnectionSendActionServiceMBean{
    
    private static final long serialVersionUID = -5342444233381889876L;
    protected double expectedCost = 0d;
    
    protected ServiceName serverConnectionFactoryServiceName;
    protected ServerConnectionFactory serverConnectionFactory;
    protected ServiceName interpreterServiceName;
    protected Interpreter interpreter;
    protected String fileEncoding;
    
    public void setServerConnectionFactoryServiceName(ServiceName name){
        serverConnectionFactoryServiceName = name;
    }
    public ServiceName getServerConnectionFactoryServiceName(){
        return serverConnectionFactoryServiceName;
    }
    
    public void setInterpreterServiceName(ServiceName name){
        interpreterServiceName = name;
    }
    public ServiceName getInterpreterServiceName(){
        return interpreterServiceName;
    }
    
    public void setFileEncoding(String encoding){
        fileEncoding = encoding;
    }
    public String getFileEncoding(){
        return fileEncoding;
    }
    
    public void setServerConnectionFactory(ServerConnectionFactory factory){
        serverConnectionFactory = factory;
    }
    
    public void setInterpreter(Interpreter interpreter){
        this.interpreter = interpreter;
    }
    
    public void startService() throws Exception{
        if(serverConnectionFactory == null && serverConnectionFactoryServiceName == null){
            throw new IllegalArgumentException("ServerConnectionFactory is null.");
        }
        if(interpreterServiceName != null){
            interpreter = (Interpreter)ServiceManagerFactory.getServiceObject(interpreterServiceName);
        }
    }
    
    /**
     * ���\�[�X�̓��e��ǂݍ���ŁA{@link ServerConnection}��{@link Message}�𑗐M����B<p>
     * ���\�[�X�̃t�H�[�}�b�g�́A�ȉ��B<br>
     * <pre>
     * subject,key
     * 
     * objectId
     * objectScript
     * </pre>
     * subject�́A{@link Message}�ɐݒ肷��T�u�W�F�N�g���w�肷��Bkey�́A{@link Message}�ɐݒ肷��L�[���w�肷��B�T�u�W�F�N�g�𕡐��ݒ肷��ꍇ�́A���s���Ďw�肷��B�T�u�W�F�N�g�w��̏I���ɂ́A��s��}������B<br>
     * objectId�́A{@link Message}�ɐݒ肷��I�u�W�F�N�g���w�肷����̂ŁA����e�X�g�P�[�X���ɁA����TestAction���O�ɁA�I�u�W�F�N�g��߂��e�X�g�A�N�V���������݂���ꍇ�́A���̃A�N�V����ID���w�肷��B�܂��A����V�i���I���ɁA����TestAction���O�ɁA�I�u�W�F�N�g��߂��e�X�g�A�N�V���������݂���ꍇ�́A�e�X�g�P�[�XID�ƃA�N�V����ID���J���}��؂�Ŏw�肷��B��s���w�肵���ꍇ�́A�I�u�W�F�N�g��TestAction�̌��ʂ���擾���Ȃ��B<br>
     * objectScript�́A{@link Message}�ɐݒ肷��I�u�W�F�N�g�𐶐�����X�N���v�g���w�肷��B�X�N���v�g�́A{@link Interpreter#evaluate(String,Map)}�ŕ]������A�����̕ϐ��}�b�v�ɂ́A"context"��{@link TestContext}���n�����B<br>
     *
     * @param context �R���e�L�X�g
     * @param actionId �A�N�V����ID
     * @param resource ���\�[�X
     * @return ���M�������b�Z�[�W
     */
    public Object execute(TestContext context, String actionId, Reader resource) throws Exception{
        return execute(context, actionId, null, resource);
    }
    
    /**
     * ���\�[�X�̓��e��ǂݍ���ŁA{@link ServerConnection}��{@link Message}�𑗐M����B<p>
     * ���\�[�X�̃t�H�[�}�b�g�́A�ȉ��B<br>
     * <pre>
     * subject,key
     * 
     * objectId
     * objectScript
     * </pre>
     * subject�́A{@link Message}�ɐݒ肷��T�u�W�F�N�g���w�肷��Bkey�́A{@link Message}�ɐݒ肷��L�[���w�肷��B�T�u�W�F�N�g�𕡐��ݒ肷��ꍇ�́A���s���Ďw�肷��B�T�u�W�F�N�g�w��̏I���ɂ́A��s��}������B<br>
     * objectId�́A{@link Message}�ɐݒ肷��I�u�W�F�N�g���w�肷����̂ŁA����e�X�g�P�[�X���ɁA����TestAction���O�ɁA�I�u�W�F�N�g��߂��e�X�g�A�N�V���������݂���ꍇ�́A���̃A�N�V����ID���w�肷��B�܂��A����V�i���I���ɁA����TestAction���O�ɁA�I�u�W�F�N�g��߂��e�X�g�A�N�V���������݂���ꍇ�́A�e�X�g�P�[�XID�ƃA�N�V����ID���J���}��؂�Ŏw�肷��B��s���w�肵���ꍇ�́A�I�u�W�F�N�g��TestAction�̌��ʂ���擾���Ȃ��B<br>
     * objectScript�́A{@link Message}�ɐݒ肷��I�u�W�F�N�g�𐶐�����X�N���v�g���w�肷��B�X�N���v�g�́A{@link Interpreter#evaluate(String,Map)}�ŕ]������A�����̕ϐ��}�b�v�ɂ́A"context"��{@link TestContext}�A"preResult"��preResult���n�����B<br>
     * objectId�AobjectScript�̗������w�肳��Ă��Ȃ��ꍇ�́ApreResult���g�p����B<br>
     *
     * @param context �R���e�L�X�g
     * @param actionId �A�N�V����ID
     * @param preResult �X�V�N�G���ɑ΂�������I�u�W�F�N�g
     * @param resource ���\�[�X
     * @return ���M�������b�Z�[�W
     */
    public Object execute(TestContext context, String actionId, Object preResult, Reader resource) throws Exception{
        BufferedReader br = new BufferedReader(resource);
        Message message = null;
        Object object = preResult;
        
        ServerConnectionFactory scf = serverConnectionFactory;
        if(serverConnectionFactory == null && serverConnectionFactoryServiceName != null){
            scf = (ServerConnectionFactory)ServiceManagerFactory.getServiceObject(serverConnectionFactoryServiceName);
        }
        
        final ServerConnection con = scf.getServerConnection();
        try{
            String subjectAndKey = br.readLine();
            if(subjectAndKey == null || subjectAndKey.length() == 0){
                throw new Exception("Unexpected EOF on subject and key");
            }
            do{
                String[] subjectAndKeyArray = CSVReader.toArray(
                    subjectAndKey,
                    ',',
                    '\\',
                    null,
                    null,
                    true,
                    false,
                    true,
                    true
                );
                if(subjectAndKeyArray == null || subjectAndKeyArray.length == 0 || subjectAndKeyArray.length > 2){
                    throw new Exception("Illegal subject and key format. subjectAndKey=" + subjectAndKey);
                }
                if(message == null){
                    message = con.createMessage(subjectAndKeyArray[0], subjectAndKeyArray.length == 2 ? subjectAndKeyArray[1] : null);
                }else{
                    message.setSubject(subjectAndKeyArray[0], subjectAndKeyArray.length == 2 ? subjectAndKeyArray[1] : null);
                }
            }while((subjectAndKey = br.readLine()) != null && subjectAndKey.length() != 0);
            
            final String objectId = br.readLine();
            if(objectId != null && objectId.length() != 0){
                Object actionResult = null;
                if(objectId.indexOf(",") == -1){
                    actionResult = context.getTestActionResult(objectId);
                }else{
                    String[] ids = objectId.split(",");
                    if(ids.length != 2){
                        throw new Exception("Illegal objectId format. id=" + objectId);
                    }
                    actionResult = context.getTestActionResult(ids[0], ids[1]);
                }
                if(actionResult == null){
                    throw new Exception("TestActionResult not found. id=" + objectId);
                }
                object = actionResult;
            }
            String objectScript = null;
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            try{
                String line = null;
                while((line = br.readLine()) != null){
                    pw.println(line);
                }
                pw.flush();
                objectScript = sw.toString();
                if(objectScript.length() == 0){
                    objectScript = null;
                }
            }finally{
                sw.close();
                pw.close();
            }
            if(objectScript != null){
                if(interpreter == null){
                    throw new UnsupportedOperationException("Interpreter is null.");
                }
                final Map params = new HashMap();
                params.put("context", context);
                params.put("preResult", preResult);
                object = interpreter.evaluate(objectScript, params);
            }
            if(object != null){
                message.setObject(object);
            }
            con.send(message);
        }finally{
            br.close();
            br = null;
        }
        return message;
    }
    
    public void setExpectedCost(double cost) {
        expectedCost = cost;
    }
    
    public double getExpectedCost() {
        return expectedCost;
    }
}
