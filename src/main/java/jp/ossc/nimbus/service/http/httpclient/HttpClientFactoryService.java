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
package jp.ossc.nimbus.service.http.httpclient;

import java.io.*;
import java.util.*;
import java.net.*;

import org.apache.commons.httpclient.*;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.auth.*;
import org.apache.commons.httpclient.params.*;
import org.apache.commons.httpclient.util.IdleConnectionTimeoutThread;

import jp.ossc.nimbus.core.*;
import jp.ossc.nimbus.service.http.*;
import jp.ossc.nimbus.service.http.HttpException;
import jp.ossc.nimbus.service.journal.Journal;
import jp.ossc.nimbus.service.context.Context;
import jp.ossc.nimbus.service.sequence.Sequence;
import jp.ossc.nimbus.service.semaphore.Semaphore;
import jp.ossc.nimbus.service.aop.interceptor.ThreadContextKey;
import jp.ossc.nimbus.service.performance.PerformanceRecorder;
import jp.ossc.nimbus.util.converter.*;

/**
 * Jakarta HttpClient���g����{@link HttpClientFactory}�T�[�r�X�B<p>
 *
 * @author M.Takata
 */
public class HttpClientFactoryService extends ServiceBase
 implements HttpClientFactory, HttpClientFactoryServiceMBean, Serializable{
    
    private static final long serialVersionUID = 4729444860053132964L;
    
    protected int connectionTimeout = -1;
    protected int linger = -1;
    protected int receiveBufferSize = -1;
    protected int sendBufferSize = -1;
    protected int soTimeout = -1;
    protected Map actionRequestMap = new HashMap();
    protected Map actionResponseMap = new HashMap();
    protected Map credentialsMap = new HashMap();
    protected Map proxyCredentialsMap = new HashMap();
    protected Map requestHeaders = new HashMap();
    protected String proxy;
    protected String proxyHost;
    protected int proxyPort;
    protected InetAddress localAddress;
    protected String localAddressStr;
    protected Map httpClientParamMap = new HashMap();
    protected ServiceName requestStreamConverterServiceName;
    protected StreamConverter requestStreamConverter;
    protected ServiceName responseStreamConverterServiceName;
    protected StreamConverter responseStreamConverter;
    protected ServiceName journalServiceName;
    protected Journal journal;
    protected ServiceName threadContextServiceName;
    protected Context threadContext;
    protected ServiceName sequenceServiceName;
    protected Sequence sequence;
    protected ServiceName performanceRecorderServiceName;
    protected PerformanceRecorder performanceRecorder;
    protected String requestContentType;
    protected String requestCharacterEncoding;
    protected int requestDeflateLength = -1;
    protected ServiceName semaphoreServiceName;
    protected Semaphore semaphore;
    protected String httpVersion;
    protected Class httpConnectionManagerClass;
    protected HttpConnectionManager httpConnectionManager;
    protected boolean isOutputJournalResponseObject = true;
    protected long idleConnectionTimeout;
    protected long idleConnectionCheckInterval;
    protected IdleConnectionTimeoutThread idleConnectionTimeoutThread;
    
    // HttpClientFactoryServiceMBean��JavaDoc
    public void setConnectionTimeout(int millis){
        connectionTimeout = millis;
    }
    
    // HttpClientFactoryServiceMBean��JavaDoc
    public int getConnectionTimeout(){
        return connectionTimeout;
    }
    
    // HttpClientFactoryServiceMBean��JavaDoc
    public void setLinger(int millis){
        linger = millis;
    }
    
    // HttpClientFactoryServiceMBean��JavaDoc
    public int getLinger(){
        return linger;
    }
    
    // HttpClientFactoryServiceMBean��JavaDoc
    public void setReceiveBufferSize(int size){
        receiveBufferSize = size;
    }
    
    // HttpClientFactoryServiceMBean��JavaDoc
    public int getReceiveBufferSize(){
        return receiveBufferSize;
    }
    
    // HttpClientFactoryServiceMBean��JavaDoc
    public void setSendBufferSize(int size){
        sendBufferSize = size;
    }
    
    // HttpClientFactoryServiceMBean��JavaDoc
    public int getSendBufferSize(){
        return sendBufferSize;
    }
    
    // HttpClientFactoryServiceMBean��JavaDoc
    public void setSoTimeout(int millis){
        soTimeout = millis;
    }
    
    // HttpClientFactoryServiceMBean��JavaDoc
    public int getSoTimeout(){
        return soTimeout;
    }
    
    // HttpClientFactoryServiceMBean��JavaDoc
    public String getRequestContentType(){
        return requestContentType;
    }
    
    // HttpClientFactoryServiceMBean��JavaDoc
    public void setRequestContentType(String type){
        requestContentType = type;
    }
    
    // HttpClientFactoryServiceMBean��JavaDoc
    public String getRequestCharacterEncoding(){
        return requestCharacterEncoding;
    }
    
    // HttpClientFactoryServiceMBean��JavaDoc
    public void setRequestCharacterEncoding(String encoding){
        requestCharacterEncoding = encoding;
    }
    
    // HttpClientFactoryServiceMBean��JavaDoc
    public String getHttpVersion(){
        return httpVersion;
    }
    
    // HttpClientFactoryServiceMBean��JavaDoc
    public void setHttpVersion(String version){
        httpVersion = version;
    }
    
    // HttpClientFactoryServiceMBean��JavaDoc
    public void setRequestHeaders(String name, String[] values){
        requestHeaders.put(name, values);
    }
    
    // HttpClientFactoryServiceMBean��JavaDoc
    public String[] getRequestHeaders(String name){
        return (String[])requestHeaders.get(name);
    }
    
    // HttpClientFactoryServiceMBean��JavaDoc
    public void setProxy(String proxy){
        if(proxy == null){
            proxyHost = null;
            proxyPort = 0;
        }else{
            final int index = proxy.indexOf(':');
            if(index <= 0
                 || index == proxy.length() - 1){
                throw new IllegalArgumentException("Illegal proxy : " + proxy);
            }
            proxyHost = proxy.substring(0, index);
            try{
                proxyPort = Integer.parseInt(proxy.substring(index + 1));
            }catch(NumberFormatException e){
                throw new IllegalArgumentException("Illegal proxy port : " + proxy);
            }
        }
        this.proxy = proxy;
    }
    
    // HttpClientFactoryServiceMBean��JavaDoc
    public String getProxy(){
        return proxy;
    }
    
    // HttpClientFactoryServiceMBean��JavaDoc
    public void setLocalAddress(String address) throws UnknownHostException{
        if(address == null){
            localAddress = null;
            localAddressStr = null;
        }else{
            localAddress = InetAddress.getByName(address);
            localAddressStr = address;
        }
    }
    
    // HttpClientFactoryServiceMBean��JavaDoc
    public String getLocalAddress(){
        return localAddressStr;
    }
    
    // HttpClientFactoryServiceMBean��JavaDoc
    public void setHttpClientParam(String name, Object value){
       httpClientParamMap.put(name, value);
    }
    
    // HttpClientFactoryServiceMBean��JavaDoc
    public Object getHttpClientParam(String name){
        return httpClientParamMap.get(name);
    }
    
    // HttpClientFactoryServiceMBean��JavaDoc
    public Map getHttpClientParamMap(){
        return httpClientParamMap;
    }
    
    // HttpClientFactoryServiceMBean��JavaDoc
    public void setRequestDeflateLength(int length){
        requestDeflateLength = length;
    }
    
    // HttpClientFactoryServiceMBean��JavaDoc
    public int getRequestDeflateLength(){
        return requestDeflateLength;
    }
    
    // HttpClientFactoryServiceMBean��JavaDoc
    public void setRequestStreamConverterServiceName(ServiceName name){
        requestStreamConverterServiceName = name;
    }
    
    // HttpClientFactoryServiceMBean��JavaDoc
    public ServiceName getRequestStreamConverterServiceName(){
        return requestStreamConverterServiceName;
    }
    
    // HttpClientFactoryServiceMBean��JavaDoc
    public void setResponseStreamConverterServiceName(ServiceName name){
        responseStreamConverterServiceName = name;
    }
    
    // HttpClientFactoryServiceMBean��JavaDoc
    public ServiceName getResponseStreamConverterServiceName(){
        return responseStreamConverterServiceName;
    }
    
    // HttpClientFactoryServiceMBean��JavaDoc
    public void setJournalServiceName(ServiceName name){
        journalServiceName = name;
    }
    
    // HttpClientFactoryServiceMBean��JavaDoc
    public ServiceName getJournalServiceName(){
        return journalServiceName;
    }
    
    // HttpClientFactoryServiceMBean��JavaDoc
    public void setSequenceServiceName(ServiceName name){
        sequenceServiceName = name;
    }
    
    // HttpClientFactoryServiceMBean��JavaDoc
    public ServiceName getSequenceServiceName(){
        return sequenceServiceName;
    }
    
    // HttpClientFactoryServiceMBean��JavaDoc
    public void setThreadContextServiceName(ServiceName name){
        threadContextServiceName = name;
    }
    
    // HttpClientFactoryServiceMBean��JavaDoc
    public ServiceName getThreadContextServiceName(){
        return threadContextServiceName;
    }
    
    // HttpClientFactoryServiceMBean��JavaDoc
    public void setSemaphoreServiceName(ServiceName name){
        semaphoreServiceName = name;
    }
    
    // HttpClientFactoryServiceMBean��JavaDoc
    public ServiceName getSemaphoreServiceName(){
        return semaphoreServiceName;
    }
    
    // HttpClientFactoryServiceMBean��JavaDoc
    public void setPerformanceRecorderServiceName(ServiceName name){
        performanceRecorderServiceName = name;
    }
    // HttpClientFactoryServiceMBean��JavaDoc
    public ServiceName getPerformanceRecorderServiceName(){
        return performanceRecorderServiceName;
    }
    
    // HttpClientFactoryServiceMBean��JavaDoc
    public void setHttpConnectionManagerClass(Class clazz){
        httpConnectionManagerClass = clazz;
    }
    // HttpClientFactoryServiceMBean��JavaDoc
    public Class getHttpConnectionManagerClass(){
        return httpConnectionManagerClass;
    }
    
    // HttpClientFactoryServiceMBean��JavaDoc
    public boolean isOutputJournalResponseObject(){
        return isOutputJournalResponseObject;
    }
    // HttpClientFactoryServiceMBean��JavaDoc
    public void setOutputJournalResponseObject(boolean isOutput){
        isOutputJournalResponseObject = isOutput;
    }
    
    // HttpClientFactoryServiceMBean��JavaDoc
    public void setIdleConnectionTimeout(long timeout){
        idleConnectionTimeout = timeout;
    }
    // HttpClientFactoryServiceMBean��JavaDoc
    public long getIdleConnectionTimeout(){
        return idleConnectionTimeout;
    }
    
    // HttpClientFactoryServiceMBean��JavaDoc
    public void setIdleConnectionCheckInterval(long interval){
        idleConnectionCheckInterval = interval;
    }
    // HttpClientFactoryServiceMBean��JavaDoc
    public long getIdleConnectionCheckInterval(){
        return idleConnectionCheckInterval;
    }
    
    // HttpClientFactoryServiceMBean��JavaDoc
    public int getConnectionsInPool(){
        if(httpConnectionManager != null && (httpConnectionManager instanceof MultiThreadedHttpConnectionManager)){
            return ((MultiThreadedHttpConnectionManager)httpConnectionManager).getConnectionsInPool();
        }else{
            return -1;
        }
    }
    
    // HttpClientFactoryServiceMBean��JavaDoc
    public int getConnectionsInUse(){
        if(httpConnectionManager != null && (httpConnectionManager instanceof MultiThreadedHttpConnectionManager)){
            return ((MultiThreadedHttpConnectionManager)httpConnectionManager).getConnectionsInUse();
        }else{
            return -1;
        }
    }
    
    /**
     * HTTP���N�G�X�g�ɐݒ肳�ꂽ���̓I�u�W�F�N�g���X�g���[���ɕϊ�����{@link jp.ossc.nimbus.util.converter.StreamConverter StreamConverter}��ݒ肷��B<p>
     * HTTP���N�G�X�g�Ɋ��ɐݒ肳��Ă���ꍇ�́A�����炪�D�悳���B<br>
     *
     * @param converter StreamConverter
     */
    public void setRequestStreamConverter(StreamConverter converter){
        requestStreamConverter = converter;
    }
    
    /**
     * HTTP���N�G�X�g�ɐݒ肳�ꂽ���̓I�u�W�F�N�g���X�g���[���ɕϊ�����{@link jp.ossc.nimbus.util.converter.StreamConverter StreamConverter}���擾����B<p>
     *
     * @return StreamConverter
     */
    public StreamConverter getRequestStreamConverter(){
        return requestStreamConverter;
    }
    
    /**
     * HTTP���X�|���X�̃X�g���[�����o�̓I�u�W�F�N�g�ɕϊ�����{@link jp.ossc.nimbus.util.converter.StreamConverter StreamConverter}��ݒ肷��B<p>
     * HTTP���X�|���X�Ɋ��ɐݒ肳��Ă���ꍇ�́A�����炪�D�悳���B<br>
     *
     * @param converter StreamConverter
     */
    public void setResponseStreamConverter(StreamConverter converter){
        responseStreamConverter = converter;
    }
    
    /**
     * HTTP���X�|���X�̃X�g���[�����o�̓I�u�W�F�N�g�ɕϊ�����{@link jp.ossc.nimbus.util.converter.StreamConverter StreamConverter}���擾����B<p>
     *
     * @return StreamConverter
     */
    public StreamConverter getResponseStreamConverter(){
        return responseStreamConverter;
    }
    
    /**
     *  ���N�G�X�g����ӂɎ��ʂ���_���A�N�V��������HTTP���N�G�X�g�̃}�b�s���O��ݒ肷��B<p>
     *
     * @param action �A�N�V������
     * @param request HTTP���N�G�X�g
     */
    public void setRequest(String action, HttpRequestImpl request){
        if(actionRequestMap == null){
            actionRequestMap = new HashMap();
        }
        request.setActionName(action);
        actionRequestMap.put(action, request);
    }
    
    /**
     *  �w�肳�ꂽ�_���A�N�V�������ɊY������HTTP���N�G�X�g���擾����B<p>
     *
     * @param action �A�N�V������
     * @return HTTP���N�G�X�g
     */
    public HttpRequestImpl getRequest(String action){
        if(actionRequestMap == null){
            return null;
        }
        return (HttpRequestImpl)actionRequestMap.get(action);
    }
    
    /**
     *  ���N�G�X�g����ӂɎ��ʂ���_���A�N�V��������HTTP���X�|���X�̃}�b�s���O��ݒ肷��B<p>
     * �ݒ肳��Ă��Ȃ��ꍇ�́A�f�t�H���g��{@link HttpResponseImpl}���g�p�����B<br>
     *
     * @param action �A�N�V������
     * @param response HTTP���X�|���X
     */
    public void setResponse(String action, HttpResponseImpl response){
        if(actionResponseMap == null){
            actionResponseMap = new HashMap();
        }
        actionResponseMap.put(action, response);
    }
    
    /**
     *  �w�肳�ꂽ�_���A�N�V�������ɊY������HTTP���X�|���X���擾����B<p>
     *
     * @param action �A�N�V������
     * @return HTTP���X�|���X
     */
    public HttpResponseImpl getResponse(String action){
        if(actionResponseMap == null){
            return null;
        }
        return (HttpResponseImpl)actionResponseMap.get(action);
    }
    
    /**
     * �F�؏���ݒ肷��B<p>
     *
     * @param authscope �F�؂̃X�R�[�v
     * @param credentials �F�؏��
     */
    public void setCredentials(AuthScope authscope, Credentials credentials){
        credentialsMap.put(authscope, credentials);
    }
    
    /**
     * �v���L�V�F�؏���ݒ肷��B<p>
     *
     * @param authscope �F�؂̃X�R�[�v
     * @param credentials �F�؏��
     */
    public void setProxyCredentials(AuthScope authscope, Credentials credentials){
        proxyCredentialsMap.put(authscope, credentials);
    }
    
    /**
     * �T�[�r�X�̊J�n�������s���B<p>
     *
     * @exception Exception �T�[�r�X�̊J�n�Ɏ��s�����ꍇ
     */
    public void startService() throws Exception{
        if(journalServiceName != null){
            journal = (Journal)ServiceManagerFactory.getServiceObject(
                journalServiceName
            );
        }
        if(sequenceServiceName != null){
            sequence = (Sequence)ServiceManagerFactory.getServiceObject(
                sequenceServiceName
            );
        }
        if(threadContextServiceName != null){
            threadContext = (Context)ServiceManagerFactory.getServiceObject(
                threadContextServiceName
            );
        }
        if(semaphoreServiceName != null){
            semaphore = (Semaphore)ServiceManagerFactory.getServiceObject(
                semaphoreServiceName
            );
            semaphore.accept();
        }
        if(performanceRecorderServiceName != null){
            performanceRecorder = (PerformanceRecorder)ServiceManagerFactory
                .getServiceObject(performanceRecorderServiceName);
        }
        if(httpConnectionManagerClass != null){
            httpConnectionManager = (HttpConnectionManager)httpConnectionManagerClass.newInstance();
            
            if(idleConnectionTimeout > 0){
                idleConnectionTimeoutThread = new IdleConnectionTimeoutThread();
                idleConnectionTimeoutThread.addConnectionManager(httpConnectionManager);
                idleConnectionTimeoutThread.setConnectionTimeout(idleConnectionTimeout);
                if(idleConnectionCheckInterval > 0){
                    idleConnectionTimeoutThread.setTimeoutInterval(idleConnectionCheckInterval);
                }
                idleConnectionTimeoutThread.start();
            }
        }
    }
    
    /**
     * �T�[�r�X�̒�~�������s���B<p>
     *
     * @exception Exception �T�[�r�X�̒�~�Ɏ��s�����ꍇ
     */
    public void stopService() throws Exception{
        if(semaphore != null){
            semaphore.release();
        }
        if(idleConnectionTimeoutThread != null){
            idleConnectionTimeoutThread.shutdown();
            idleConnectionTimeoutThread = null;
        }
        if(httpConnectionManager != null){
            if(httpConnectionManager instanceof MultiThreadedHttpConnectionManager){
                ((MultiThreadedHttpConnectionManager)httpConnectionManager).shutdown();
            }else if(httpConnectionManager instanceof SimpleHttpConnectionManager){
                ((SimpleHttpConnectionManager)httpConnectionManager).shutdown();
            }
            httpConnectionManager = null;
        }
    }
    
    // HttpClientFactory��JavaDoc
    public HttpRequest createRequest(String action)
     throws HttpRequestCreateException{
        HttpRequestImpl request = (HttpRequestImpl)actionRequestMap.get(action);
        if(request == null){
            throw new HttpRequestCreateException("No action.");
        }
        try{
            request = (HttpRequestImpl)request.clone();
            if(request.getContentType() == null
                && requestContentType != null){
                request.setContentType(requestContentType);
            }
            if(request.getCharacterEncoding() == null
                && requestCharacterEncoding != null){
                request.setCharacterEncoding(requestCharacterEncoding);
            }
            if(request.getHttpVersion() == null
                && httpVersion != null){
                request.setHttpVersion(httpVersion);
            }
            if(request.getStreamConverter() == null
                 && request.getStreamConverterServiceName() == null){
                if(requestStreamConverter != null){
                    request.setStreamConverter(requestStreamConverter);
                }else if(requestStreamConverterServiceName != null){
                    request.setStreamConverterServiceName(
                        requestStreamConverterServiceName
                    );
                }
            }
            if(requestHeaders.size() != 0){
                final Set headerNameSet = request.getHeaderNameSet();
                final Iterator names = requestHeaders.keySet().iterator();
                while(names.hasNext()){
                    final String headerName = (String)names.next();
                    if(!headerNameSet.contains(headerName)){
                        request.setHeaders(
                            headerName,
                            (String[])requestHeaders.get(headerName)
                        );
                    }
                }
            }
            if(request.getDeflateLength() <= 0
                && requestDeflateLength != -1){
                request.setDeflateLength(requestDeflateLength);
            }
            return request;
        }catch(CloneNotSupportedException e){
            throw new HttpRequestCreateException(e);
        }
    }
    
    // HttpClientFactory��JavaDoc
    public jp.ossc.nimbus.service.http.HttpClient createHttpClient()
     throws HttpException{
        if(semaphore == null){
            return new HttpClientImpl();
        }else if(semaphore.getResource()){
            try{
                return new HttpClientImpl();
            }catch(HttpException e){
                semaphore.freeResource();
                throw e;
            }catch(Throwable th){
                semaphore.freeResource();
                if(th instanceof RuntimeException){
                    throw (RuntimeException)th;
                }else{
                    throw (Error)th;
                }
            }
        }else{
            throw new HttpClientCreateTimeoutException(getServiceNameObject() == null ? "" : getServiceNameObject().toString());
        }
    }
    
    /**
     * Jakarta HttpClient���g����{@link jp.ossc.nimbus.service.http.HttpClient HttpClient}�����N���X�B<p>
     *
     * @author M.Takata
     */
    public class HttpClientImpl
     implements jp.ossc.nimbus.service.http.HttpClient{
        
        protected HttpClient client;
        protected HttpMethodBase method;
        
        /**
         * �C���X�^���X�𐶐�����B<p>
         */
        public HttpClientImpl(){
            
            final HttpClientParams params = new HttpClientParams();
            final Iterator names = httpClientParamMap.keySet().iterator();
            while(names.hasNext()){
                final String name = (String)names.next();
                final Object value = httpClientParamMap.get(name);
                params.setParameter(name, value);
            }
            
            client = httpConnectionManager == null ? new HttpClient(params) : new HttpClient(params, httpConnectionManager);
            
            final HostConfiguration hostConfig = client.getHostConfiguration();
            if(proxy != null){
                hostConfig.setProxy(proxyHost, proxyPort);
            }
            if(localAddress != null){
                hostConfig.setLocalAddress(localAddress);
            }
            
            final HttpConnectionManagerParams conParams
                 = client.getHttpConnectionManager().getParams();
            if(connectionTimeout != -1){
                conParams.setConnectionTimeout(connectionTimeout);
            }
            if(linger != -1){
                conParams.setLinger(linger);
            }
            if(receiveBufferSize != -1){
                conParams.setReceiveBufferSize(receiveBufferSize);
            }
            if(sendBufferSize != -1){
                conParams.setSendBufferSize(sendBufferSize);
            }
            if(soTimeout != -1){
                conParams.setSoTimeout(soTimeout);
            }
            
            
            Iterator entries = credentialsMap.entrySet().iterator();
            while(entries.hasNext()){
                final Map.Entry entry = (Map.Entry)entries.next();
                client.getState().setCredentials(
                    (AuthScope)entry.getKey(),
                    (Credentials)entry.getValue()
                );
            }
            entries = proxyCredentialsMap.entrySet().iterator();
            while(entries.hasNext()){
                final Map.Entry entry = (Map.Entry)entries.next();
                client.getState().setProxyCredentials(
                    (AuthScope)entry.getKey(),
                    (Credentials)entry.getValue()
                );
            }
        }
        
        // HttpClient��JavaDoc
        public void addCookie(javax.servlet.http.Cookie cookie){
            if(client == null){
                return;
            }
            final Cookie result = new Cookie(
                cookie.getDomain(),
                cookie.getName(),
                cookie.getValue()
            );
            result.setComment(cookie.getComment());
            if(cookie.getMaxAge() > 0){
                result.setExpiryDate(
                    new Date(System.currentTimeMillis() + cookie.getMaxAge())
                );
            }
            result.setPath(cookie.getPath());
            result.setSecure(cookie.getSecure());
            result.setVersion(cookie.getVersion());
            client.getState().addCookie(result);
        }
        
        // HttpClient��JavaDoc
        public javax.servlet.http.Cookie[] getCookies(){
            if(client == null){
                return new javax.servlet.http.Cookie[0];
            }
            final Cookie[] cookies = client.getState().getCookies();
            if(cookies == null || cookies.length == 0){
                return new javax.servlet.http.Cookie[0];
            }
            javax.servlet.http.Cookie[] result
                 = new javax.servlet.http.Cookie[cookies.length];
            for(int i = 0; i < cookies.length; i++){
                result[i] = new CookieImpl(cookies[i]);
            }
            return result;
        }
        
        // HttpClient��JavaDoc
        public HttpResponse executeRequest(HttpRequest request)
         throws HttpException{
            if(client == null){
                throw new HttpException("Closed.");
            }
            HttpResponseImpl response = null;
            long start = System.currentTimeMillis();
            try{
                if(journal != null){
                    journal.startJournal(JOURNAL_ACCESS);
                    String requestId = null;
                    if(sequence != null){
                        requestId = sequence.increment();
                    }else if(threadContext != null){
                        requestId = (String)threadContext.get(
                            ThreadContextKey.REQUEST_ID
                        );
                    }
                    if(requestId != null){
                        journal.setRequestId(requestId);
                    }
                }
                int status = 0;
                try{
                    if(journal != null){
                        journal.addStartStep(JOURNAL_REQUEST);
                    }
                    final HttpRequestImpl req = (HttpRequestImpl)request;
                    if(journal != null){
                        journal.addInfo(
                            JOURNAL_REQUEST_ACTION,
                            req.getActionName()
                        );
                        journal.addInfo(JOURNAL_REQUEST_URI, req.getURL());
                        journal.addInfo(
                            JOURNAL_REQUEST_COOKIES,
                            getCookies()
                        );
                        if(req.getHeaderMap() != null){
                            journal.addInfo(
                                JOURNAL_REQUEST_HEADERS,
                                req.getHeaderMap()
                            );
                        }
                        if(req.getParameterMap() != null){
                            journal.addInfo(
                                JOURNAL_REQUEST_PARAMS,
                                req.getParameterMap()
                            );
                        }
                        if(req.getObject() != null){
                            journal.addInfo(
                                JOURNAL_REQUEST_OBJECT,
                                req.getObject()
                            );
                        }
                    }
                    if(method != null){
                        method.releaseConnection();
                        method = null;
                    }
                    method = req.createHttpMethod();
                    if(journal != null){
                        if(req.getInputBytes() != null){
                            String body = null;
                            if(req.getCharacterEncoding() == null){
                                body = new String(req.getInputBytes());
                            }else{
                                body = new String(
                                    req.getInputBytes(),
                                    req.getCharacterEncoding()
                                );
                            }
                            journal.addInfo(JOURNAL_REQUEST_BODY, body);
                        }
                    }
                    
                    status = client.executeMethod(method);
                }finally{
                    if(journal != null){
                        journal.addEndStep();
                    }
                }
                try{
                    if(journal != null){
                        journal.addStartStep(JOURNAL_RESPONSE);
                        journal.addInfo(
                            JOURNAL_RESPONSE_STATUS,
                            new Integer(status)
                        );
                    }
                    response = (HttpResponseImpl)actionResponseMap
                            .get(request.getActionName());
                    if(response == null){
                        response = new HttpResponseImpl();
                    }else{
                        response = (HttpResponseImpl)response.clone();
                    }
                    if(response.getStreamConverter() == null
                         && response.getStreamConverterServiceName() == null){
                        if(responseStreamConverter != null){
                            response.setStreamConverter(
                                responseStreamConverter
                            );
                        }else if(responseStreamConverterServiceName != null){
                            response.setStreamConverterServiceName(
                                responseStreamConverterServiceName
                            );
                        }
                    }
                    response.setStatusCode(status);
                    response.setHttpMethod(method);
                    if(journal != null){
                        if(response.getHeaderMap() != null){
                            journal.addInfo(
                                JOURNAL_RESPONSE_HEADERS,
                                response.getHeaderMap()
                            );
                        }
                        if(response.getOutputBytes() != null){
                            String body = null;
                            final String encoding
                                 = response.getCharacterEncoding();
                            if(encoding == null){
                                body = new String(response.getOutputBytes());
                            }else{
                                body = new String(
                                    response.getOutputBytes(),
                                    encoding
                                );
                            }
                            journal.addInfo(JOURNAL_RESPONSE_BODY, body);
                        }
                        if(isOutputJournalResponseObject){
                            try{
                                if(response.getObject() != null){
                                    journal.addInfo(
                                        JOURNAL_RESPONSE_OBJECT,
                                        response.getObject()
                                    );
                                }
                            }catch(Exception e){
                            }
                        }
                    }
                    
                    return response;
                }finally{
                    if(journal != null){
                        journal.addEndStep();
                    }
                }
            }catch(ConnectTimeoutException e){
                if(journal != null){
                    journal.addInfo(JOURNAL_ACCESS_EXCEPTION, e);
                }
                throw new HttpClientConnectTimeoutException(e);
            }catch(CloneNotSupportedException e){
                if(journal != null){
                    journal.addInfo(JOURNAL_ACCESS_EXCEPTION, e);
                }
                throw new HttpException(e);
            }catch(SocketTimeoutException e){
                if(journal != null){
                    journal.addInfo(JOURNAL_ACCESS_EXCEPTION, e);
                }
                throw new HttpClientSocketTimeoutException(e);
            }catch(IOException e){
                if(journal != null){
                    journal.addInfo(JOURNAL_ACCESS_EXCEPTION, e);
                }
                throw new HttpException(e);
            }catch(RuntimeException e){
                if(journal != null){
                    journal.addInfo(JOURNAL_ACCESS_EXCEPTION, e);
                }
                throw e;
            }catch(Error e){
                if(journal != null){
                    journal.addInfo(JOURNAL_ACCESS_EXCEPTION, e);
                }
                throw e;
            }finally{
                if(response != null){
                    if(response.isConnectionClose()
                        && response.getContentLength() > 0){
                        response.close();
                    }
                }
                if(journal != null){
                    journal.endJournal();
                }
                if(performanceRecorder != null){
                    performanceRecorder.record(start, System.currentTimeMillis());
                }
            }
        }
        
        // HttpClient��JavaDoc
        public void close() throws HttpException{
            if(semaphore != null){
                semaphore.freeResource();
            }
            if(method != null){
                method.releaseConnection();
                method = null;
            }
            if(client != null){
                HttpConnectionManager connectionManager = client.getHttpConnectionManager();
                if(connectionManager instanceof SimpleHttpConnectionManager){
                    try{
                        ((SimpleHttpConnectionManager)connectionManager).shutdown();
                    }catch(Throwable e){}
                }
            }
            client = null;
        }
        
        /**
         * Jakarta HttpClient�̃C���X�^���X���擾����B<p>
         *
         * @return Jakarta HttpClient�̃C���X�^���X
         */
        public HttpClient getHttpClient(){
            return client;
        }
    }
    
    public static class CookieImpl extends javax.servlet.http.Cookie{
        
        private Cookie cookie;
        
        public CookieImpl(Cookie cookie){
            super(cookie.getName(), cookie.getValue());
            this.cookie = cookie;
            if(cookie.getComment() != null){
                super.setComment(cookie.getComment());
            }
            if(cookie.getDomain() != null){
                super.setDomain(cookie.getDomain());
            }
            if(cookie.getExpiryDate() == null){
                super.setMaxAge(-1);
            }else{
                final long expiry = cookie.getExpiryDate().getTime();
                super.setMaxAge((int)(expiry - System.currentTimeMillis()));
            }
            if(cookie.getPath() != null){
                super.setPath(cookie.getPath());
            }
            super.setSecure(cookie.getSecure());
            super.setVersion(cookie.getVersion());
        }
        
        public void setComment(String purpose){
            super.setComment(purpose);
            cookie.setComment(purpose);
        }
        
        public void setDomain(String pattern){
            super.setDomain(pattern);
            cookie.setDomain(pattern);
        }
        
        public void setMaxAge(int expiry){
            super.setMaxAge(expiry);
            cookie.setExpiryDate(new Date(expiry + System.currentTimeMillis()));
        }
        
        public void setPath(String uri){
            super.setPath(uri);
            cookie.setPath(uri);
        }
        
        public void setSecure(boolean flag){
            super.setSecure(flag);
            cookie.setSecure(flag);
        }
        
        public void setValue(String newValue){
            super.setValue(newValue);
            cookie.setValue(newValue);
        }
        
        public void setVersion(int v){
            super.setVersion(v);
            cookie.setVersion(v);
        }
    }
}