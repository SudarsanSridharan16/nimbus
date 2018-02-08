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

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.StringWriter;
import java.io.PrintWriter;
import java.io.Reader;
import java.io.BufferedReader;
import java.util.Map;
import java.util.LinkedHashMap;
import java.util.Iterator;

import jp.ossc.nimbus.core.ServiceBase;
import jp.ossc.nimbus.core.ServiceName;
import jp.ossc.nimbus.core.ServiceManagerFactory;
import jp.ossc.nimbus.io.CSVReader;
import jp.ossc.nimbus.service.http.HttpClient;
import jp.ossc.nimbus.service.http.HttpClientFactory;
import jp.ossc.nimbus.service.http.HttpRequest;
import jp.ossc.nimbus.service.http.HttpResponse;
import jp.ossc.nimbus.service.interpreter.Interpreter;
import jp.ossc.nimbus.service.test.TestAction;
import jp.ossc.nimbus.service.test.TestActionEstimation;
import jp.ossc.nimbus.service.test.ChainTestAction;
import jp.ossc.nimbus.service.test.TestContext;

/**
 * HTTP���N�G�X�g�e�X�g�A�N�V�����B<p>
 * HTTP���N�G�X�g�𑗐M���āAHTTP���X�|���X���t�@�C���ɏo�͂���B<br>
 * ����̏ڍׂ́A{@link #execute(TestContext, String, Reader)}���Q�ƁB<br>
 * 
 * @author M.Takata
 */
public class HttpRequestActionService extends ServiceBase implements TestAction, ChainTestAction.TestActionProcess, TestActionEstimation, HttpRequestActionServiceMBean{
    
    private static final long serialVersionUID = -6266672726524592951L;
    protected ServiceName httpClientFactoryServiceName;
    protected HttpClientFactory httpClientFactory;
    protected ServiceName interpreterServiceName;
    protected Interpreter interpreter;
    protected double expectedCost = 0d;
    
    public void setHttpClientFactoryServiceName(ServiceName name){
        httpClientFactoryServiceName = name;
    }
    public ServiceName getHttpClientFactoryServiceName(){
        return httpClientFactoryServiceName;
    }
    
    public void setInterpreterServiceName(ServiceName name){
        interpreterServiceName = name;
    }
    public ServiceName getInterpreterServiceName(){
        return interpreterServiceName;
    }
    
    public void setHttpClientFactory(HttpClientFactory factory){
        httpClientFactory = factory;
    }
    
    public void setInterpreter(Interpreter interpreter){
        this.interpreter = interpreter;
    }
    
    public void startService() throws Exception{
        if(httpClientFactoryServiceName != null){
            httpClientFactory = (HttpClientFactory)ServiceManagerFactory.getServiceObject(httpClientFactoryServiceName);
        }
        if(httpClientFactory == null){
            throw new IllegalArgumentException("HttpClientFactory is null.");
        }
        if(interpreterServiceName != null){
            interpreter = (Interpreter)ServiceManagerFactory.getServiceObject(interpreterServiceName);
        }
    }
    
    /**
     * HTTP���N�G�X�g�𑗐M���āAHTTP���X�|���X���t�@�C���ɏo�͂���B<p>
     * ���\�[�X�̃t�H�[�}�b�g�́A�ȉ��B<br>
     * <pre>
     * clientId
     * actionName
     * replaceValueId-&gt;replaceKey
     * 
     * headerName:headerValue
     * 
     * bodyType
     * body
     * </pre>
     * clientId�́A{@link HttpClient}�I�u�W�F�N�g���ė��p����ꍇ�Ɏw�肷����̂ŁA����e�X�g�P�[�X���ɁA����TestAction���O�ɁA���̃N���X��TestAction�����݂���ꍇ�́A���̃A�N�V����ID���w�肷��B�܂��A����V�i���I���ɁA����TestAction���O�ɁA���̃N���X��TestAction�����݂���ꍇ�́A�e�X�g�P�[�XID�ƃA�N�V����ID���J���}��؂�Ŏw�肷��B�ė��p�̕K�v���Ȃ��ꍇ�́A�󕶎����w�肷��B<br>
     * actionName�́A{@link HttpClientFactory#createRequest(String)}�̈����Ɏw�肷��A�N�V���������w�肷��B<br>
     * replaceValueId�́A���N�G�X�g�w�b�_�y�у{�f�B�ɑ΂���u�����s���l��TestAction�̌��ʂ���擾���邽�߂Ɏw�肷����̂ŁA����e�X�g�P�[�X����TestAction�̌��ʂ��擾����ꍇ�́A���̃A�N�V����ID���w�肷��B�܂��A����V�i���I���̑��̃e�X�g�P�[�X��TestAction�̌��ʂ��擾����ꍇ�́A�e�X�g�P�[�XID�ƃA�N�V����ID���J���}��؂�Ŏw�肷��B����ɑ�����"-&gt;"������ŁA�u���Ώۂ̕������replaceKey�Ƃ��Ďw�肷��B���̍s�́A�����w�肪�\�Ȃ��߁A�I�����������߂ɁA��s���P�s�����B�u�����s�v�ȏꍇ�́A�w�肷��K�v�͂Ȃ��B<br>
     * headerName�́AHTTP�w�b�_�����w�肷��B����ɑ�����":"������ŁA�w�b�_�l��headerValue�Ƃ��Ďw�肷��B���̍s�́A�����w�肪�\�Ȃ��߁A�I�����������߂ɁA��s���P�s�����B�w�b�_���s�v�ȏꍇ�́A�w�肷��K�v�͂Ȃ��B<br>
     * bodyType�́A"parameter"�A"text"�A"binary"�A"object"�A"multipart"�̂����ꂩ���w�肷��BHTTP�{�f�B���K�v�Ȃ��ꍇ�́A���̍s�ȉ��͕K�v�Ȃ��B<br>
     * body�́AbodyType�ɂ���āA�L�q���@���قȂ�B<br>
     * <ul>
     * <li>bodyType��"parameter"�̏ꍇ<br>�p�����[�^��name=value�Ŏw�肷��B��������ꍇ�́A���s���Ďw�肷��B</li>
     * <li>bodyType��"text"�̏ꍇ<br>�C�ӂ̕�����Ŏw�肷��B</li>
     * <li>bodyType��"binary"�̏ꍇ<br>�o�C�i���t�@�C���̃p�X���w�肷��B�p�X�́A��΃p�X�܂��́A���΃p�X�Ŏw�肷��B</li>
     * <li>bodyType��"object"�̏ꍇ<br>{@link HttpRequest#setObject(Object)}�ɐݒ肷��I�u�W�F�N�g�𐶐�����X�N���v�g��������w�肷��B�X�N���v�g������́A{@link Interpreter#evaluate(String)}�ŕ]������A���̖߂�l���I�u�W�F�N�g�Ƃ��Ďg�p�����B</li>
     * <li>bodyType��"multipart"�̏ꍇ<br>�p�����[�^�̏ꍇ�Aname=value�Ŏw�肷��B�t�@�C���̏ꍇ�Afile:name=filePath,fileName,contentType�Ŏw�肷��BfileName,contentType�͏ȗ��\�B��������ꍇ�́A���s���Ďw�肷��B</li>
     * </ul>
     * <p>
     * HTTP���X�|���X���o�͂���t�@�C���́A�w�b�_�t�@�C���ƃ{�f�B�t�@�C���̂Q�ł���B<br>
     * �w�b�_�t�@�C���́A�A�N�V����ID�Ɋg���q".h.rsp"��t�������t�@�C�����ɂȂ�B<br>
     * �w�b�_�t�@�C���̃t�H�[�}�b�g�́A�ȉ��B<br>
     * <pre>
     * HTTP status
     * HTTP message
     * headerName:headerValue
     * </pre>
     * <p>
     * �{�f�B�t�@�C���́A�A�N�V����ID�Ɋg���q".b.rsp"��t�������t�@�C�����ɂȂ�B<br>
     * �{�f�B�t�@�C���́AHTTP�{�f�B�����̂܂܏o�͂���B<br>
     *
     * @param context �R���e�L�X�g
     * @param actionId �A�N�V����ID
     * @param resource ���\�[�X
     * @return Map�B�L�["client"�ŁAHttpClient�I�u�W�F�N�g�B�L�["response"�ŁAHttpResponse�B
     */
    public Object execute(TestContext context, String actionId, Reader resource) throws Exception{
        return execute(context, actionId, null, resource);
    }
    
    /**
     * HTTP���N�G�X�g�𑗐M���āAHTTP���X�|���X���t�@�C���ɏo�͂���B<p>
     *
     * @param context �R���e�L�X�g
     * @param actionId �A�N�V����ID
     * @param preResult 1�O�̃A�N�V�����̖߂�l
     * @param resource ���\�[�X
     * @return JMX��MBean���Ăяo�����߂�l
     * @return Map�B�L�["client"�ŁAHttpClient�I�u�W�F�N�g�B�L�["response"�ŁAHttpResponse�B
     */
    public Object execute(TestContext context, String actionId, Object preResult, Reader resource) throws Exception{
        BufferedReader br = new BufferedReader(resource);
        Map result = new LinkedHashMap();
        try{
            final String clientId = br.readLine();
            if(clientId == null){
                throw new Exception("Unexpected EOF on clientId");
            }
            HttpClient client = null;
            if(clientId.length() == 0){
                if(preResult != null && (preResult instanceof Map)){
                    client = (HttpClient)((Map)preResult).get("client");
                }else{
                    client = httpClientFactory.createHttpClient();
                }
            }else{
                Object actionResult = null;
                if(clientId.indexOf(",") == -1){
                    actionResult = context.getTestActionResult(clientId);
                }else{
                    String[] ids = clientId.split(",");
                    if(ids.length != 2){
                        throw new Exception("Illegal clientId format. id=" + clientId);
                    }
                    actionResult = context.getTestActionResult(ids[0], ids[1]);
                }
                if(actionResult == null){
                    throw new Exception("TestActionResult not found. id=" + clientId);
                }
                if(actionResult == null || !(actionResult instanceof Map)){
                    throw new Exception("TestActionResult is not Map. result=" + actionResult);
                }
                client = (HttpClient)((Map)actionResult).get("client");
            }
            result.put("client", client);
            final String actionName = br.readLine();
            if(actionName == null){
                throw new Exception("Unexpected EOF on actionName");
            }
            HttpRequest request = httpClientFactory.createRequest(actionName);
            String line = null;
            Map replaceMap = null;
            while((line = br.readLine()) != null && line.length() != 0){
                int index = line.lastIndexOf("->");
                if(index == -1){
                    break;
                }
                if(replaceMap == null){
                    replaceMap = new LinkedHashMap();
                }
                
                String replaceValueId = line.substring(0, index);
                Object replaceValue = null;
                if(replaceValueId != null && replaceValueId.length() != 0){
                    if(replaceValueId.indexOf(",") == -1){
                        replaceValue = context.getTestActionResult(replaceValueId);
                    }else{
                        String[] ids = replaceValueId.split(",");
                        if(ids.length != 2){
                            throw new Exception("Illegal replaceValueId format. id=" + replaceValueId);
                        }
                        replaceValue = context.getTestActionResult(ids[0], ids[1]);
                    }
                }
                replaceMap.put(line.substring(index + 2), replaceValue == null ? "" : replaceValue.toString());
            }
            if(line != null && line.length() == 0){
                line = br.readLine();
            }
            String bodyType = null;
            do{
                int index = line.indexOf(":");
                if(index == -1){
                    bodyType = line;
                    break;
                }
                request.setHeader(line.substring(0, index), replace(line.substring(index + 1), replaceMap));
            }while((line = br.readLine()) != null && line.length() != 0);
            
            if(line != null && bodyType == null){
                bodyType = br.readLine();
            }
            if(bodyType != null){
                if("parameter".equals(bodyType)){
                    while((line = br.readLine()) != null && line.length() != 0){
                        int index = line.indexOf("=");
                        if(index == -1){
                            throw new Exception("Illegal parameter format. parameter=" + line);
                        }
                        request.setParameter(line.substring(0, index), replace(line.substring(index + 1), replaceMap));
                    }
                }else if("text".equals(bodyType)){
                    StringWriter sw = new StringWriter();
                    PrintWriter pw = new PrintWriter(sw);
                    String text = null;
                    try{
                        while((line = br.readLine()) != null){
                            pw.println(line);
                        }
                        pw.flush();
                        text = sw.toString();
                    }finally{
                        sw.close();
                        pw.close();
                    }
                    if(request.getCharacterEncoding() != null){
                        request.getOutputStream().write(
                            replace(text, replaceMap).getBytes(request.getCharacterEncoding())
                        );
                    }else{
                        text = replace(text, replaceMap);
                        request.getOutputStream().write(
                            request.getCharacterEncoding() == null ? text.getBytes() : text.getBytes(request.getCharacterEncoding())
                        );
                    }
                }else if("binary".equals(bodyType)){
                    String filePath = br.readLine();
                    if(filePath == null){
                        throw new Exception("Unexpected EOF on body");
                    }
                    File binaryFile = new File(filePath);
                    if(!binaryFile.exists()){
                        binaryFile = new File(context.getCurrentDirectory(), filePath);
                    }
                    if(!binaryFile.exists()){
                        throw new Exception("File of body not found: " + filePath);
                    }
                    final FileInputStream fis = new FileInputStream(binaryFile);
                    try{
                        byte[] bytes = new byte[1024];
                        int len = 0;
                        while((len = fis.read(bytes)) > 0){
                            request.getOutputStream().write(bytes, 0, len);
                        }
                    }finally{
                        fis.close();
                    }
                }else if("object".equals(bodyType)){
                    if(interpreter == null){
                        throw new UnsupportedOperationException("Interpreter is null.");
                    }
                    StringWriter sw = new StringWriter();
                    PrintWriter pw = new PrintWriter(sw);
                    String script = null;
                    try{
                        while((line = br.readLine()) != null){
                            pw.println(line);
                        }
                        pw.flush();
                        script = sw.toString();
                    }finally{
                        sw.close();
                        pw.close();
                    }
                    script = replace(script, replaceMap);
                    Object requestObject = interpreter.evaluate(script);
                    request.setObject(requestObject);
                }else if("multipart".equals(bodyType)){
                    while((line = br.readLine()) != null && line.length() != 0){
                        int index = line.indexOf("=");
                        if(index == -1){
                            throw new Exception("Illegal parameter format. parameter=" + line);
                        }
                        if(line.startsWith("file:")){
                            String paramName = line.substring(5, index);
                            final String[] params = CSVReader.toArray(
                                line.substring(index + 1),
                                ',',
                                '\\',
                                '"',
                                "",
                                null,
                                true,
                                false,
                                true,
                                false
                            );
                            String filePath = params[0];
                            String fileName = (params.length > 1 && params[1].length() != 0) ? params[1] : null;
                            String contentType = (params.length > 2 && params[2].length() != 0) ? params[2] : null;
                            File file = new File(filePath);
                            if(!file.exists()){
                                file = new File(context.getCurrentDirectory(), filePath);
                            }
                            request.setFileParameter(paramName, file, fileName, contentType);
                        }else{
                            request.setParameter(line.substring(0, index), replace(line.substring(index + 1), replaceMap));
                        }
                    }
                }else{
                    throw new Exception("Unknown bodyType : " + bodyType);
                }
            }
            HttpResponse response = client.executeRequest(request);
            result.put("response", response);
            final File responseHeaderFile = new File(context.getCurrentDirectory(), actionId + ".h.rsp");
            PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(new FileOutputStream(responseHeaderFile))));
            try{
                pw.println(response.getStatusCode());
                pw.println(response.getStatusMessage());
                final Iterator headerNames = response.getHeaderNameSet().iterator();
                while(headerNames.hasNext()){
                    final String headerName = (String)headerNames.next();
                    final String[] values = response.getHeaders(headerName);
                    pw.print(headerName);
                    pw.print(": ");
                    for(int i = 0, imax = values.length; i < imax; i++){
                        pw.print(values[i]);
                        if(i == imax - 1){
                            pw.println();
                        }else{
                            pw.print("; ");
                        }
                    }
                }
                pw.flush();
            }finally{
                pw.close();
                pw = null;
            }
            final File responseBodyFile = new File(context.getCurrentDirectory(), actionId + ".b.rsp");
            final InputStream is = response.getInputStream();
            FileOutputStream fos = new FileOutputStream(responseBodyFile);
            try{
                byte[] bytes = new byte[1024];
                int len = 0;
                while((len = is.read(bytes)) > 0){
                    fos.write(bytes, 0, len);
                }
            }finally{
                fos.close();
                fos = null;
            }
        }finally{
            br.close();
        }
        return result;
    }
    
    protected String replace(String src, Map replaceMap){
        if(replaceMap == null || replaceMap.size() == 0){
            return src;
        }
        String result = src;
        final Iterator entries = replaceMap.entrySet().iterator();
        while(entries.hasNext()){
            Map.Entry entry = (Map.Entry)entries.next();
            result = result.replaceAll((String)entry.getKey(), (String)entry.getValue());
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