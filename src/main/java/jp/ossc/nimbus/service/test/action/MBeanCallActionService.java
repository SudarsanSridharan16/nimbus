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
import java.util.Set;
import java.util.List;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.HashMap;
import java.util.Iterator;
import java.io.BufferedReader;
import java.io.Reader;
import java.io.StringWriter;
import java.io.PrintWriter;
import javax.management.Attribute;
import javax.management.ObjectName;
import javax.management.remote.JMXConnector;
import javax.management.MBeanServerConnection;

import jp.ossc.nimbus.beans.NimbusPropertyEditorManager;
import jp.ossc.nimbus.core.ServiceBase;
import jp.ossc.nimbus.core.ServiceName;
import jp.ossc.nimbus.core.ServiceManagerFactory;
import jp.ossc.nimbus.core.Utility;
import jp.ossc.nimbus.service.jmx.MBeanServerConnectionFactory;
import jp.ossc.nimbus.service.interpreter.Interpreter;
import jp.ossc.nimbus.service.test.TestAction;
import jp.ossc.nimbus.service.test.TestActionEstimation;
import jp.ossc.nimbus.service.test.TestContext;
import jp.ossc.nimbus.service.test.ChainTestAction;

/**
 * JMX��MBean���Ăяo���e�X�g�A�N�V�����B<p>
 * ����̏ڍׂ́A{@link #execute(TestContext, String, Reader)}���Q�ƁB<br>
 * 
 * @author M.Takata
 */
public class MBeanCallActionService extends ServiceBase implements TestAction, ChainTestAction.TestActionProcess, TestActionEstimation, MBeanCallActionServiceMBean{
    
    private static final long serialVersionUID = 5048024056628750415L;
    protected static final String SETTER_PREFIX = "set";
    protected static final int SETTER_PREFIX_LENGTH = 3;
    protected static final String GETTER_PREFIX = "get";
    protected static final int GETTER_PREFIX_LENGTH = 3;
    protected double expectedCost = 0d;
    
    protected ServiceName mbeanServerConnectionFactoryServiceName;
    protected MBeanServerConnectionFactory mbeanServerConnectionFactory;
    protected ServiceName interpreterServiceName;
    protected Interpreter interpreter;
    
    public void setMBeanServerConnectionFactoryServiceName(ServiceName name){
        mbeanServerConnectionFactoryServiceName = name;
    }
    public ServiceName getMBeanServerConnectionFactoryServiceName(){
        return mbeanServerConnectionFactoryServiceName;
    }
    
    public void setInterpreterServiceName(ServiceName name){
        interpreterServiceName = name;
    }
    public ServiceName getInterpreterServiceName(){
        return interpreterServiceName;
    }
    
    public void setMBeanServerConnectionFactory(MBeanServerConnectionFactory factory){
        mbeanServerConnectionFactory = factory;
    }
    
    public void setInterpreter(Interpreter interpreter){
        this.interpreter = interpreter;
    }
    
    public void startService() throws Exception{
        if(mbeanServerConnectionFactoryServiceName != null){
            mbeanServerConnectionFactory = (MBeanServerConnectionFactory)ServiceManagerFactory.getServiceObject(mbeanServerConnectionFactoryServiceName);
        }
        if(mbeanServerConnectionFactory == null){
            throw new IllegalArgumentException("MBeanServerConnectionFactory is null.");
        }
        if(interpreterServiceName != null){
            interpreter = (Interpreter)ServiceManagerFactory.getServiceObject(interpreterServiceName);
        }
    }
    
    /**
     * JMX��MBean���Ăяo���āA�߂�l��Ԃ��B<p>
     * ���\�[�X�̃t�H�[�}�b�g�́A�ȉ��B<br>
     * <pre>
     * targetType
     * target
     * methodSigniture
     * argumentType
     * argument
     * </pre>
     * targetType�́A�Ăяo���Ώۂ�MBean�̓�����@�ŁA"objectName"�A"objectNameQuery"�A"interpreter"�̂����ꂩ���w�肷��B<br>
     * target�́AtargetType�ɂ���āA�L�q���@���قȂ�B<br>
     * <ul>
     * <li>targetType��"objectName"�̏ꍇ<br>new javax.management.ObjectName(String)�̈����ɂ����镶������w�肷��B����ObjectName�ŁA1��MBean����肷��B</li>
     * <li>targetType��"objectNameQuery"�̏ꍇ<br>new javax.management.ObjectName(String)�̈����ɂ����镶������w�肷��B����ObjectName�ŁA������MBean�𒊏o����B</li>
     * <li>targetType��"interpreter"�̏ꍇ<br>�Ăяo���Ώۂ�MBean��ObjectName�܂��͂��̏W�����擾����X�N���v�g��������L�q����B�X�N���v�g������́A{@link Interpreter#evaluate(String,java.util.Map)}�ŕ]������A���̖߂�l��MBean�Ƃ��Ďg�p�����B�X�N���v�g���ł́A�ϐ�"connection"�ŁAMBeanServerConnection���Q�Ƃł���B�܂��A�ϐ�"context"�ŁATestContext���Q�Ƃł���Btarget�̏I���́A��s������B</li>
     * </ul>
     * methodSigniture�́A�Ăяo�����\�b�h�̃V�O�j�`�����w�肷��B�V�O�j�`���́A���\�b�h��(�����^1,�����^2,�c)�Ŏw�肷��B<br>
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
     * @return JMX��MBean���Ăяo�����߂�l
     */
    public Object execute(TestContext context, String actionId, Reader resource) throws Exception{
        return execute(context, actionId, null, resource);
    }
    
    /**
     * JMX��MBean���Ăяo���āA�߂�l��Ԃ��B<p>
     * ���\�[�X�̃t�H�[�}�b�g�́A�ȉ��B<br>
     * <pre>
     * targetType
     * target
     * methodSigniture
     * argumentType
     * argument
     * </pre>
     * targetType�́A�Ăяo���Ώۂ�MBean�̓�����@�ŁA"objectName"�A"objectNameQuery"�A"interpreter"�̂����ꂩ���w�肷��B<br>
     * target�́AtargetType�ɂ���āA�L�q���@���قȂ�B<br>
     * <ul>
     * <li>targetType��"objectName"�̏ꍇ<br>new javax.management.ObjectName(String)�̈����ɂ����镶������w�肷��B����ObjectName�ŁA1��MBean����肷��B</li>
     * <li>targetType��"objectNameQuery"�̏ꍇ<br>new javax.management.ObjectName(String)�̈����ɂ����镶������w�肷��B����ObjectName�ŁA������MBean�𒊏o����B</li>
     * <li>targetType��"interpreter"�̏ꍇ<br>�Ăяo���Ώۂ�MBean��ObjectName�܂��͂��̏W�����擾����X�N���v�g��������L�q����B�X�N���v�g������́A{@link Interpreter#evaluate(String,java.util.Map)}�ŕ]������A���̖߂�l��MBean�Ƃ��Ďg�p�����B�X�N���v�g���ł́A�ϐ�"connection"�ŁAMBeanServerConnection���Q�Ƃł���B�܂��A�ϐ�"context"�ŁATestContext���Q�Ƃł���Btarget�̏I���́A��s������B</li>
     * </ul>
     * methodSigniture�́A�Ăяo�����\�b�h�̃V�O�j�`�����w�肷��B�V�O�j�`���́A���\�b�h��(�����^1,�����^2,�c)�Ŏw�肷��B<br>
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
     * @return JMX��MBean���Ăяo�����߂�l
     */
    public Object execute(TestContext context, String actionId, Object preResult, Reader resource) throws Exception{
        JMXConnector connector = mbeanServerConnectionFactory.getJMXConnector();
        connector.connect();
        BufferedReader br = new BufferedReader(resource);
        Object result = null;
        try{
            final MBeanServerConnection connection = connector.getMBeanServerConnection();
            final String targetType = br.readLine();
            if(targetType == null){
                throw new Exception("Unexpected EOF on targetType");
            }
            String target = null;
            List objectNames = new ArrayList();
            if(targetType.equals("objectName")){
                target = br.readLine();
                if(target == null){
                    throw new Exception("Unexpected EOF on target");
                }
                final ObjectName objectName = new ObjectName(target);
                objectNames.add(objectName);
            }else if(targetType.equals("objectNameQuery")){
                target = br.readLine();
                if(target == null){
                    throw new Exception("Unexpected EOF on target");
                }
                Set objectNameSet = connection.queryNames(new ObjectName(target), null);
                objectNames.addAll(objectNameSet);
            }else if(targetType.equals("interpreter")){
                if(interpreter == null){
                    throw new UnsupportedOperationException("Interpreter is null.");
                }
                StringWriter sw = new StringWriter();
                PrintWriter pw = new PrintWriter(sw);
                String line = null;
                try{
                    while((line = br.readLine()) != null && line.length() != 0){
                        pw.println(line);
                    }
                    pw.flush();
                    target = sw.toString();
                }finally{
                    pw.close();
                }
                if(line == null){
                    throw new Exception("Unexpected EOF on target");
                }
                final Map params = new HashMap();
                params.put("connection", connection);
                params.put("context", context);
                Object ret = interpreter.evaluate(target, params);
                if(ret == null){
                    throw new Exception("Illegal target : " + ret);
                }else if(ret instanceof ObjectName){
                    objectNames.add(ret);
                }else if(ret instanceof Collection){
                    final Iterator itr = ((Collection)ret).iterator();
                    while(itr.hasNext()){
                        final Object obj = itr.next();
                        if(obj == null){
                            continue;
                        }
                        if(!(obj instanceof ObjectName)){
                            throw new Exception("Illegal target : " + obj.getClass());
                        }
                        objectNames.add(obj);
                    }
                }else{
                    throw new Exception("Illegal target : " + ret);
                }
            }else{
                throw new Exception("Unknown targetType : " + targetType);
            }
            if(objectNames.size() == 0){
                throw new Exception("MBean not found. target=" + target);
            }
            final String methodSigniture = br.readLine();
            if(methodSigniture == null){
                throw new Exception("Unexpected EOF on methodSigniture");
            }
            String methodName = null;
            String[] paramTypeNames = null;
            Class[] paramTypes = null;
            int index = methodSigniture.indexOf("(");
            if(index == -1){
                methodName = methodSigniture;
            }else{
                if(methodSigniture.charAt(methodSigniture.length() - 1) != ')'){
                    throw new Exception("Illegal methodSigniture : " + methodSigniture);
                }
                methodName = methodSigniture.substring(0, index);
                final String args = methodSigniture.substring(index + 1, methodSigniture.length() - 1).trim();
                if(args.length() != 0){
                    paramTypeNames = args.split(",");
                    paramTypes = new Class[paramTypeNames.length];
                    for(int i = 0; i < paramTypeNames.length; i++){
                        paramTypeNames[i] = paramTypeNames[i].trim();
                        paramTypes[i] = Utility.convertStringToClass(paramTypeNames[i], false);
                    }
                }
            }
            Object[] arguments = null;
            if(paramTypes != null && paramTypes.length != 0){
                arguments = paramTypes == null || paramTypes.length == 0 ? null : new Object[paramTypes.length];
                
                String argumentType = null;
                index = 0;
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
            
            if(methodName.length() > SETTER_PREFIX_LENGTH
                 && methodName.startsWith(SETTER_PREFIX)
                 && arguments != null && arguments.length == 1){
                for(int i = 0; i < objectNames.size(); i++){
                    final Attribute attr = new Attribute(
                        methodName.substring(SETTER_PREFIX_LENGTH),
                        arguments[0]
                    );
                    connection.setAttribute((ObjectName)objectNames.get(i), attr);
                }
            }else if(methodName.length() > GETTER_PREFIX_LENGTH
                 && methodName.startsWith(GETTER_PREFIX)
                 && (arguments == null || arguments.length == 0)){
                List resultList = new ArrayList(objectNames.size());
                for(int i = 0; i < objectNames.size(); i++){
                    resultList.add(
                        connection.getAttribute(
                            (ObjectName)objectNames.get(i),
                            methodName.substring(GETTER_PREFIX_LENGTH)
                        )
                    );
                }
                result = resultList.size() == 1 ? resultList.get(0) : resultList;
            }else{
                List resultList = new ArrayList(objectNames.size());
                for(int i = 0; i < objectNames.size(); i++){
                    resultList.add(
                        connection.invoke(
                            (ObjectName)objectNames.get(i),
                            methodName,
                            arguments,
                            paramTypeNames
                        )
                    );
                }
                result = resultList.size() == 1 ? resultList.get(0) : resultList;
            }
        }finally{
            try{
                br.close();
            }catch(Exception e){
            }
            try{
                connector.close();
            }catch(Exception e){
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
