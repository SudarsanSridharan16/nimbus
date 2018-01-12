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

import java.beans.PropertyEditor;
import java.io.BufferedReader;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Reader;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import jp.ossc.nimbus.beans.MethodEditor;
import jp.ossc.nimbus.beans.NimbusPropertyEditorManager;
import jp.ossc.nimbus.beans.ServiceNameEditor;
import jp.ossc.nimbus.core.ServiceBase;
import jp.ossc.nimbus.core.ServiceManagerFactory;
import jp.ossc.nimbus.core.ServiceName;
import jp.ossc.nimbus.service.interpreter.Interpreter;
import jp.ossc.nimbus.service.test.ChainTestAction;
import jp.ossc.nimbus.service.test.TestAction;
import jp.ossc.nimbus.service.test.TestActionEstimation;
import jp.ossc.nimbus.service.test.TestContext;
import jp.ossc.nimbus.service.aop.Invoker;
import jp.ossc.nimbus.service.aop.DefaultMethodInvocationContext;

/**
 * ���[�J���̃T�[�r�X���Ăяo���e�X�g�A�N�V�����B<p>
 * ����̏ڍׂ́A{@link #execute(TestContext, String, Reader)}���Q�ƁB<br>
 * 
 * @author M.Takata
 */
public class ServiceCallActionService extends ServiceBase implements TestAction, ChainTestAction.TestActionProcess, TestActionEstimation, ServiceCallActionServiceMBean{
    
    private static final long serialVersionUID = 9132878277934081894L;
    
    protected ServiceName interpreterServiceName;
    protected Interpreter interpreter;
    
    protected ServiceName invokerServiceName;
    protected Invoker invoker;
    
    protected double expectedCost = 0d;
    
    public void setInterpreterServiceName(ServiceName name){
        interpreterServiceName = name;
    }
    public ServiceName getInterpreterServiceName(){
        return interpreterServiceName;
    }
    
    public void setInvokerServiceName(ServiceName name){
        invokerServiceName = name;
    }
    public ServiceName getInvokerServiceName(){
        return invokerServiceName;
    }
    
    public void setExpectedCost(double cost) {
        expectedCost = cost;
    }
    
    public double getExpectedCost() {
        return expectedCost;
    }
    
    public void setInterpreter(Interpreter interpreter){
        this.interpreter = interpreter;
    }
    
    public void setInvoker(Invoker invoker){
        this.invoker = invoker;
    }
    
    public void startService() throws Exception{
        if(interpreterServiceName != null){
            interpreter = (Interpreter)ServiceManagerFactory.getServiceObject(interpreterServiceName);
        }
        if(invokerServiceName != null){
            invoker = (Invoker)ServiceManagerFactory.getServiceObject(invokerServiceName);
        }
    }
    
    /**
     * ���[�J���̃T�[�r�X���Ăяo���āA�߂�l��Ԃ��B<p>
     * ���\�[�X�̃t�H�[�}�b�g�́A�ȉ��B<br>
     * <pre>
     * serviceName
     * methodSigniture
     * argumentsType
     * arguments
     * </pre>
     * serviceName�́A�Ăяo���Ώۂ̃T�[�r�X�����w�肷��B�T�[�r�X���̃t�H�[�}�b�g�́A{@link ServiceNameEditor}�̎d�l�ɏ�����B<br>
     * methodSigniture�́A�Ăяo�����\�b�h�̃V�O�j�`�����w�肷��B�V�O�j�`���̃t�H�[�}�b�g�́A{@link MethodEditor}�̎d�l�ɏ�����B<br>
     * argumentType�́A�Ăяo�����\�b�h�̈����̎w����@�ŁA"id"�A"value"�A"interpreter"�̂����ꂩ���w�肷��B<br>
     * argument�́AargumentType�ɂ���āA�L�q���@���قȂ�B<br>
     * <ul>
     * <li>argumentType��"id"�̏ꍇ<br>TestAction�̖߂�l�������Ƃ��Ďg�p������̂ŁA����e�X�g�P�[�X���ɁA����TestAction���O�ɁA�����I�u�W�F�N�g��߂��e�X�g�A�N�V���������݂���ꍇ�́A���̃A�N�V����ID���w�肷��B�܂��A����V�i���I���ɁA����TestAction���O�ɁA�����I�u�W�F�N�g��߂��e�X�g�A�N�V���������݂���ꍇ�́A�e�X�g�P�[�XID�ƃA�N�V����ID���J���}��؂�Ŏw�肷��B</li>
     * <li>argumentType��"value"�̏ꍇ<br>�����𕶎���Ŏw�肷��B�������������݂���ꍇ�́A���s����B������null�ł��鎖���w�肷��ꍇ�́A"null"�Ǝw�肷��B</li>
     * <li>argumentType��"interpreter"�̏ꍇ<br>�����𐶐�����X�N���v�g��������L�q����B�X�N���v�g������́A{@link Interpreter#evaluate(String,java.util.Map)}�ŕ]������A���̖߂�l�������Ƃ��Ďg�p�����B�X�N���v�g���ł́A�ϐ�"context"�ŁATestContext���Q�Ƃł���B�X�N���v�g�̏I���́A��s�B</li>
     * </ul>
     * ��������������ꍇ�́AargumentType�Aargument���J��Ԃ��B<br>
     *
     * @param context �R���e�L�X�g
     * @param actionId �A�N�V����ID
     * @param resource ���\�[�X
     * @return �T�[�r�X���Ăяo�����߂�l
     */
    public Object execute(TestContext context, String actionId, Reader resource) throws Exception{
        return execute(context, actionId, null, resource);
    }
    /**
     * ���[�J���̃T�[�r�X���Ăяo���āA�߂�l��Ԃ��B<p>
     * ���\�[�X�̃t�H�[�}�b�g�́A�ȉ��B<br>
     * <pre>
     * serviceName
     * methodSigniture
     * argumentType
     * argument
     * </pre>
     * serviceName�́A�Ăяo���Ώۂ̃T�[�r�X�����w�肷��B�T�[�r�X���̃t�H�[�}�b�g�́A{@link ServiceNameEditor}�̎d�l�ɏ�����B<br>
     * methodSigniture�́A�Ăяo�����\�b�h�̃V�O�j�`�����w�肷��B�V�O�j�`���̃t�H�[�}�b�g�́A{@link MethodEditor}�̎d�l�ɏ�����B<br>
     * argumentType�́A�Ăяo�����\�b�h�̈����̎w����@�ŁA"id"�A"value"�A"chain"�A"interpreter"�̂����ꂩ���w�肷��B<br>
     * argument�́AargumentType�ɂ���āA�L�q���@���قȂ�B<br>
     * <ul>
     * <li>argumentType��"id"�̏ꍇ<br>TestAction�̖߂�l�������Ƃ��Ďg�p������̂ŁA����e�X�g�P�[�X���ɁA����TestAction���O�ɁA�����I�u�W�F�N�g��߂��e�X�g�A�N�V���������݂���ꍇ�́A���̃A�N�V����ID���w�肷��B�܂��A����V�i���I���ɁA����TestAction���O�ɁA�����I�u�W�F�N�g��߂��e�X�g�A�N�V���������݂���ꍇ�́A�e�X�g�P�[�XID�ƃA�N�V����ID���J���}��؂�Ŏw�肷��B</li>
     * <li>argumentType��"value"�̏ꍇ<br>�����𕶎���Ŏw�肷��B�������������݂���ꍇ�́A���s����B������null�ł��鎖���w�肷��ꍇ�́A"null"�Ǝw�肷��B</li>
     * <li>argumentType��"chain"�̏ꍇ<br>{@link ChainTestAction$TestActionProcess TestActionProcess}�Ƃ��ČĂяo����A�O�A�N�V��������������󂯎�鎖���Ӗ�����B���̏ꍇargument�̍s�͎w�肷��K�v���Ȃ��B</li>
     * <li>argumentType��"interpreter"�̏ꍇ<br>�����𐶐�����X�N���v�g��������L�q����B�X�N���v�g������́A{@link Interpreter#evaluate(String,java.util.Map)}�ŕ]������A���̖߂�l�������Ƃ��Ďg�p�����B�X�N���v�g���ł́A�ϐ�"context"�ŁATestContext���Q�Ƃł���B�X�N���v�g�̏I���́A��s�B</li>
     * </ul>
     * ��������������ꍇ�́AargumentType�Aargument���J��Ԃ��B<br>
     *
     * @param context �R���e�L�X�g
     * @param actionId �A�N�V����ID
     * @param preResult 1�O�̃A�N�V�����̖߂�l
     * @param resource ���\�[�X
     * @return �T�[�r�X���Ăяo�����߂�l
     */
    public Object execute(TestContext context, String actionId, Object preResult, Reader resource) throws Exception{
        BufferedReader br = new BufferedReader(resource);
        ServiceName targetServiceName = null;
        Method method = null;
        Object[] arguments = null;
        try{
            final String serviceNameStr = br.readLine();
            if(serviceNameStr == null || serviceNameStr.length() == 0){
                throw new Exception("Unexpected EOF on serviceName");
            }
            final ServiceNameEditor serviceNameEditor = new ServiceNameEditor();
            serviceNameEditor.setAsText(serviceNameStr);
            targetServiceName = (ServiceName)serviceNameEditor.getValue();
            
            final String methodSigniture = br.readLine();
            if(methodSigniture == null || methodSigniture.length() == 0){
                throw new Exception("Unexpected EOF on methodSigniture");
            }
            final MethodEditor methodEditor = new MethodEditor();
            methodEditor.setAsText(methodSigniture);
            method = (Method)methodEditor.getValue();
            final Class[] paramTypes = method.getParameterTypes();
            if(paramTypes.length != 0){
                arguments = paramTypes == null || paramTypes.length == 0 ? null : new Object[paramTypes.length];
                
                String argumentType = null;
                int index = 0;
                while((argumentType = br.readLine()) != null){
                    if(argumentType.length() == 0){
                        continue;
                    }
                    if(index >= paramTypes.length){
                        throw new Exception("Unmatch argument length. signitureParamLength=" + paramTypes.length + ", argumentLength>" + index);
                    }
                    if("chain".equals(argumentType)){
                        arguments[index] = preResult;
                    }else if("id".equals(argumentType)){
                        String line = br.readLine();
                        if(line == null){
                            throw new Exception("Unexpected EOF on argument");
                        }
                        if(line != null && line.length() != 0){
                            if(line.indexOf(",") == -1){
                                arguments[index] = context.getTestActionResult(line);
                            }else{
                                String[] ids = line.split(",");
                                if(ids.length != 2){
                                    throw new Exception("Illegal argument id format. id=" + line);
                                }
                                arguments[index] = context.getTestActionResult(ids[0], ids[1]);
                            }
                        }
                    }else if("value".equals(argumentType)){
                        String line = br.readLine();
                        if(line == null){
                            throw new Exception("Unexpected EOF on argument");
                        }
                        PropertyEditor editor = NimbusPropertyEditorManager.findEditor(paramTypes[index]);
                        if(editor == null){
                            throw new Exception("PropertyEditor not found. type=" + paramTypes[index]);
                        }
                        try{
                            editor.setAsText(line);
                            arguments[index] = editor.getValue();
                        }catch(Exception e){
                            throw new Exception("PropertyEditor can not edit. editor=" + editor + ", value=" + line, e);
                        }
                    }else if("interpreter".equals(argumentType)){
                        if(interpreter == null){
                            throw new UnsupportedOperationException("Interpreter is null.");
                        }
                        String script = null;
                        StringWriter sw = new StringWriter();
                        PrintWriter pw = new PrintWriter(sw);
                        String line = br.readLine();
                        if(line == null){
                            throw new Exception("Unexpected EOF on argument");
                        }
                        try{
                            do{
                                pw.println(line);
                            }while((line = br.readLine()) != null && line.length() != 0);
                            pw.flush();
                            script = sw.toString();
                        }finally{
                            pw.close();
                        }
                        if(paramTypes != null){
                            final Map params = new HashMap();
                            params.put("context", context);
                            arguments[index] = interpreter.evaluate(script, params);
                        }
                    }else{
                        throw new Exception("Unknown argumentType : " + argumentType);
                    }
                    index++;
                }
            }
        }finally{
            br.close();
            br = null;
        }
        if(invoker == null){
            return method.invoke(ServiceManagerFactory.getServiceObject(targetServiceName), arguments);
        }else{
            try{
                return invoker.invoke(
                    new DefaultMethodInvocationContext(
                        targetServiceName,
                        method,
                        arguments
                    )
                );
            }catch(Throwable th){
                if(th instanceof Exception){
                    throw (Exception)th;
                }else{
                    throw (Error)th;
                }
            }
        }
    }
}
