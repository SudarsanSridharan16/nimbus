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
package jp.ossc.nimbus.service.aop.interceptor.servlet;

import java.io.*;
import java.util.*;
import java.util.zip.*;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;

import jp.ossc.nimbus.core.*;
import jp.ossc.nimbus.beans.dataset.*;
import jp.ossc.nimbus.service.aop.*;
import jp.ossc.nimbus.service.context.*;
import jp.ossc.nimbus.service.journal.*;
import jp.ossc.nimbus.service.journal.editorfinder.*;
import jp.ossc.nimbus.service.beancontrol.interfaces.*;
import jp.ossc.nimbus.servlet.BeanFlowSelector;
import jp.ossc.nimbus.servlet.DefaultBeanFlowSelectorService;
import jp.ossc.nimbus.util.converter.*;

/**
 * �X�g���[�������C���^�[�Z�v�^�B<p>
 *
 * @author M.Takata
 */
public class StreamExchangeInterceptorService
 extends ServletFilterInterceptorService
 implements StreamExchangeInterceptorServiceMBean{
    
    private static final long serialVersionUID = 7618395554145055608L;
    
    /** �w�b�_�[ : Content-Encoding */
    protected static final String HEADER_CONTENT_ENCODING = "Content-Encoding";
    /** Content-Encoding : deflate */
    protected static final String CONTENT_ENCODING_DEFLATE = "deflate";
    /** Content-Encoding : gzip */
    protected static final String CONTENT_ENCODING_GZIP = "gzip";
    /** Content-Encoding : x-zip */
    protected static final String CONTENT_ENCODING_X_GZIP = "x-gzip";
    
    protected ServiceName requestStreamConverterServiceName;
    protected StreamConverter requestStreamConverter;
    
    protected ServiceName responseStreamConverterServiceName;
    protected StreamConverter responseStreamConverter;
    
    protected ServiceName threadContextServiceName;
    protected Context threadContext;
    
    protected ServiceName journalServiceName;
    protected Journal journal;
    
    protected ServiceName exchangeEditorFinderServiceName;
    protected EditorFinder exchangeEditorFinder;
    
    protected ServiceName exchangeRequestEditorFinderServiceName;
    protected EditorFinder exchangeRequestEditorFinder;
    
    protected ServiceName exchangeResponseEditorFinderServiceName;
    protected EditorFinder exchangeResponseEditorFinder;
    
    protected ServiceName requestBytesEditorFinderServiceName;
    protected EditorFinder requestBytesEditorFinder;
    
    protected ServiceName requestObjectEditorFinderServiceName;
    protected EditorFinder requestObjectEditorFinder;
    
    protected ServiceName responseBytesEditorFinderServiceName;
    protected EditorFinder responseBytesEditorFinder;
    
    protected ServiceName responseObjectEditorFinderServiceName;
    protected EditorFinder responseObjectEditorFinder;
    
    protected ServiceName exceptionEditorFinderServiceName;
    protected EditorFinder exceptionEditorFinder;
    
    protected String responseContentType;
    
    protected String requestObjectAttributeName
         = DEFAULT_REQUEST_OBJECT_ATTRIBUTE_NAME;
    protected String responseObjectAttributeName
         = DEFAULT_RESPONSE_OBJECT_ATTRIBUTE_NAME;
    
    protected String requestObjectContextKey
         = DEFAULT_REQUEST_OBJECT_CONTEXT_KEY;
    protected String responseObjectContextKey
         = DEFAULT_RESPONSE_OBJECT_CONTEXT_KEY;
    
    protected boolean isRequestStreamInflate = true;
    
    protected String exchangeJournalKey = DEFAULT_EXCHANGE_JOURNAL_KEY;
    protected String exchangeRequestJournalKey = DEFAULT_EXCHANGE_REQ_JOURNAL_KEY;
    protected String exchangeResponseJournalKey = DEFAULT_EXCHANGE_RES_JOURNAL_KEY;
    protected String requestBytesJournalKey = DEFAULT_REQUEST_BYTES_JOURNAL_KEY;
    protected String requestObjectJournalKey = DEFAULT_REQUEST_OBJECT_JOURNAL_KEY;
    protected String responseBytesJournalKey = DEFAULT_RESPONSE_BYTES_JOURNAL_KEY;
    protected String responseObjectJournalKey = DEFAULT_RESPONSE_OBJECT_JOURNAL_KEY;
    protected String exceptionJournalKey = DEFAULT_EXCEPTION_JOURNAL_KEY;
    
    protected ServiceName beanFlowInvokerFactoryServiceName;
    protected BeanFlowInvokerFactory beanFlowInvokerFactory;
    protected ServiceName beanFlowSelectorServiceName;
    protected BeanFlowSelector beanFlowSelector;
    protected Map requestObjectTypeMap;
    protected String requestObjectFlowNamePrefix = DEFAULT_REQUEST_OBJECT_FLOW_NAME_PREFIX;
    
    // StreamExchangeInterceptorServiceMBean ��JavaDoc
    public void setRequestStreamConverterServiceName(ServiceName name){
        requestStreamConverterServiceName = name;
    }
    // StreamExchangeInterceptorServiceMBean ��JavaDoc
    public ServiceName getRequestStreamConverterServiceName(){
        return requestStreamConverterServiceName;
    }
    
    // StreamExchangeInterceptorServiceMBean ��JavaDoc
    public void setResponseStreamConverterServiceName(ServiceName name){
        responseStreamConverterServiceName = name;
    }
    // StreamExchangeInterceptorServiceMBean ��JavaDoc
    public ServiceName getResponseStreamConverterServiceName(){
        return responseStreamConverterServiceName;
    }
    
    // StreamExchangeInterceptorServiceMBean ��JavaDoc
    public void setThreadContextServiceName(ServiceName name){
        threadContextServiceName = name;
    }
    // StreamExchangeInterceptorServiceMBean ��JavaDoc
    public ServiceName getThreadContextServiceName(){
        return threadContextServiceName;
    }
    
    // StreamExchangeInterceptorServiceMBean ��JavaDoc
    public void setJournalServiceName(ServiceName name){
        journalServiceName = name;
    }
    // StreamExchangeInterceptorServiceMBean ��JavaDoc
    public ServiceName getJournalServiceName(){
        return journalServiceName;
    }
    
    // StreamExchangeInterceptorServiceMBean ��JavaDoc
    public void setExchangeEditorFinderServiceName(ServiceName name){
        exchangeEditorFinderServiceName = name;
    }
    // StreamExchangeInterceptorServiceMBean ��JavaDoc
    public ServiceName getExchangeEditorFinderServiceName(){
        return exchangeEditorFinderServiceName;
    }
    
    // StreamExchangeInterceptorServiceMBean ��JavaDoc
    public void setExchangeRequestEditorFinderServiceName(ServiceName name){
        exchangeRequestEditorFinderServiceName = name;
    }
    // StreamExchangeInterceptorServiceMBean ��JavaDoc
    public ServiceName getExchangeRequestEditorFinderServiceName(){
        return exchangeRequestEditorFinderServiceName;
    }
    
    // StreamExchangeInterceptorServiceMBean ��JavaDoc
    public void setExchangeResponseEditorFinderServiceName(ServiceName name){
        exchangeResponseEditorFinderServiceName = name;
    }
    // StreamExchangeInterceptorServiceMBean ��JavaDoc
    public ServiceName getExchangeResponseEditorFinderServiceName(){
        return exchangeResponseEditorFinderServiceName;
    }
    
    // StreamExchangeInterceptorServiceMBean ��JavaDoc
    public void setRequestBytesEditorFinderServiceName(ServiceName name){
        requestBytesEditorFinderServiceName = name;
    }
    // StreamExchangeInterceptorServiceMBean ��JavaDoc
    public ServiceName getRequestBytesEditorFinderServiceName(){
        return requestBytesEditorFinderServiceName;
    }
    
    // StreamExchangeInterceptorServiceMBean ��JavaDoc
    public void setRequestObjectEditorFinderServiceName(ServiceName name){
        requestObjectEditorFinderServiceName = name;
    }
    // StreamExchangeInterceptorServiceMBean ��JavaDoc
    public ServiceName getRequestObjectEditorFinderServiceName(){
        return requestObjectEditorFinderServiceName;
    }
    
    // StreamExchangeInterceptorServiceMBean ��JavaDoc
    public void setResponseBytesEditorFinderServiceName(ServiceName name){
        responseBytesEditorFinderServiceName = name;
    }
    // StreamExchangeInterceptorServiceMBean ��JavaDoc
    public ServiceName getResponseBytesEditorFinderServiceName(){
        return responseBytesEditorFinderServiceName;
    }
    
    // StreamExchangeInterceptorServiceMBean ��JavaDoc
    public void setResponseObjectEditorFinderServiceName(ServiceName name){
        responseObjectEditorFinderServiceName = name;
    }
    // StreamExchangeInterceptorServiceMBean ��JavaDoc
    public ServiceName getResponseObjectEditorFinderServiceName(){
        return responseObjectEditorFinderServiceName;
    }
    
    // StreamExchangeInterceptorServiceMBean ��JavaDoc
    public void setExceptionEditorFinderServiceName(ServiceName name){
        exceptionEditorFinderServiceName = name;
    }
    // StreamExchangeInterceptorServiceMBean ��JavaDoc
    public ServiceName getExceptionEditorFinderServiceName(){
        return exceptionEditorFinderServiceName;
    }
    
    // StreamExchangeInterceptorServiceMBean ��JavaDoc
    public void setResponseContentType(String type){
        responseContentType = type;
    }
    // StreamExchangeInterceptorServiceMBean ��JavaDoc
    public String getResponseContentType(){
        return responseContentType;
    }
    
    // StreamExchangeInterceptorServiceMBean ��JavaDoc
    public void setRequestObjectAttributeName(String name){
        requestObjectAttributeName = name;
    }
    // StreamExchangeInterceptorServiceMBean ��JavaDoc
    public String getRequestObjectAttributeName(){
        return requestObjectAttributeName;
    }
    
    // StreamExchangeInterceptorServiceMBean ��JavaDoc
    public void setResponseObjectAttributeName(String name){
        responseObjectAttributeName = name;
    }
    // StreamExchangeInterceptorServiceMBean ��JavaDoc
    public String getResponseObjectAttributeName(){
        return responseObjectAttributeName;
    }
    
    // StreamExchangeInterceptorServiceMBean ��JavaDoc
    public void setRequestObjectContextKey(String key){
        requestObjectContextKey = key;
    }
    // StreamExchangeInterceptorServiceMBean ��JavaDoc
    public String getRequestObjectContextKey(){
        return requestObjectContextKey;
    }
    
    // StreamExchangeInterceptorServiceMBean ��JavaDoc
    public void setResponseObjectContextKey(String key){
        responseObjectContextKey = key;
    }
    // StreamExchangeInterceptorServiceMBean ��JavaDoc
    public String getResponseObjectContextKey(){
        return responseObjectContextKey;
    }
    
    // StreamExchangeInterceptorServiceMBean ��JavaDoc
    public void setRequestStreamInflate(boolean isInflate){
        isRequestStreamInflate = isInflate;
    }
    
    // StreamExchangeInterceptorServiceMBean ��JavaDoc
    public boolean isRequestStreamInflate(){
        return isRequestStreamInflate;
    }
    
    // StreamExchangeInterceptorServiceMBean ��JavaDoc
    public void setExchangeJournalKey(String key){
        exchangeJournalKey = key;
    }
    // StreamExchangeInterceptorServiceMBean ��JavaDoc
    public String getExchangeJournalKey(){
        return exchangeJournalKey;
    }
    
    // StreamExchangeInterceptorServiceMBean ��JavaDoc
    public void setExchangeRequestJournalKey(String key){
        exchangeRequestJournalKey = key;
    }
    // StreamExchangeInterceptorServiceMBean ��JavaDoc
    public String getExchangeRequestJournalKey(){
        return exchangeRequestJournalKey;
    }
    
    // StreamExchangeInterceptorServiceMBean ��JavaDoc
    public void setExchangeResponseJournalKey(String key){
        exchangeResponseJournalKey = key;
    }
    // StreamExchangeInterceptorServiceMBean ��JavaDoc
    public String getExchangeResponseJournalKey(){
        return exchangeResponseJournalKey;
    }
    
    // StreamExchangeInterceptorServiceMBean ��JavaDoc
    public void setRequestBytesJournalKey(String key){
        requestBytesJournalKey = key;
    }
    // StreamExchangeInterceptorServiceMBean ��JavaDoc
    public String getRequestBytesJournalKey(){
        return requestBytesJournalKey;
    }
    
    // StreamExchangeInterceptorServiceMBean ��JavaDoc
    public void setRequestObjectJournalKey(String key){
        requestObjectJournalKey = key;
    }
    // StreamExchangeInterceptorServiceMBean ��JavaDoc
    public String getRequestObjectJournalKey(){
        return requestObjectJournalKey;
    }
    
    // StreamExchangeInterceptorServiceMBean ��JavaDoc
    public void setResponseBytesJournalKey(String key){
        responseBytesJournalKey = key;
    }
    // StreamExchangeInterceptorServiceMBean ��JavaDoc
    public String getResponseBytesJournalKey(){
        return responseBytesJournalKey;
    }
    
    // StreamExchangeInterceptorServiceMBean ��JavaDoc
    public void setResponseObjectJournalKey(String key){
        responseObjectJournalKey = key;
    }
    // StreamExchangeInterceptorServiceMBean ��JavaDoc
    public String getResponseObjectJournalKey(){
        return responseObjectJournalKey;
    }
    
    // StreamExchangeInterceptorServiceMBean ��JavaDoc
    public void setExceptionJournalKey(String key){
        exceptionJournalKey = key;
    }
    // StreamExchangeInterceptorServiceMBean ��JavaDoc
    public String getExceptionJournalKey(){
        return exceptionJournalKey;
    }
    
    // StreamExchangeInterceptorServiceMBean ��JavaDoc
    public Map getRequestObjectTypeMap(){
        return requestObjectTypeMap;
    }
    
    // StreamExchangeInterceptorServiceMBean ��JavaDoc
    public void setBeanFlowInvokerFactoryServiceName(ServiceName name){
        beanFlowInvokerFactoryServiceName = name;
    }
    // StreamExchangeInterceptorServiceMBean ��JavaDoc
    public ServiceName getBeanFlowInvokerFactoryServiceName(){
        return beanFlowInvokerFactoryServiceName;
    }
    
    // StreamExchangeInterceptorServiceMBean ��JavaDoc
    public void setRequestObjectFlowNamePrefix(String prefix){
        requestObjectFlowNamePrefix = prefix;
    }
    // StreamExchangeInterceptorServiceMBean ��JavaDoc
    public String getRequestObjectFlowNamePrefix(){
        return requestObjectFlowNamePrefix;
    }
    
    // StreamExchangeInterceptorServiceMBean ��JavaDoc
    public void setBeanFlowSelectorServiceName(ServiceName name){
        beanFlowSelectorServiceName = name;
    }
    // StreamExchangeInterceptorServiceMBean ��JavaDoc
    public ServiceName getBeanFlowSelectorServiceName(){
        return beanFlowSelectorServiceName;
    }
    
    /**
     * �T�[�r�X�̐����������s���B<p>
     *
     * @exception Exception �T�[�r�X�̐����Ɏ��s�����ꍇ
     */
    public void createService() throws Exception{
        requestObjectTypeMap = new HashMap();
    }
    
    /**
     * �T�[�r�X�̊J�n�������s���B<p>
     *
     * @exception Exception �T�[�r�X�̊J�n�Ɏ��s�����ꍇ
     */
    public void startService() throws Exception{
        if(requestStreamConverterServiceName == null
             && requestStreamConverter == null 
             && responseStreamConverterServiceName == null
             && responseStreamConverter == null){
            throw new IllegalArgumentException("It is necessary to specify RequestStreamConverterServiceName or RequestStreamConverter or ResponseStreamConverterServiceName or ResponseStreamConverter.");
        }
        if(requestStreamConverterServiceName != null){
            requestStreamConverter = (StreamConverter)ServiceManagerFactory
                .getServiceObject(requestStreamConverterServiceName);
        }
        if(responseStreamConverterServiceName != null){
            responseStreamConverter = (StreamConverter)ServiceManagerFactory
                .getServiceObject(responseStreamConverterServiceName);
        }
        
        if(threadContextServiceName != null){
            threadContext = (Context)ServiceManagerFactory
                .getServiceObject(threadContextServiceName);
        }
        
        if(journalServiceName != null){
            journal = (Journal)ServiceManagerFactory
                .getServiceObject(journalServiceName);
        }
        
        if(exchangeEditorFinderServiceName != null){
            exchangeEditorFinder = (EditorFinder)ServiceManagerFactory
                .getServiceObject(
                    exchangeEditorFinderServiceName
                );
        }
        
        if(exchangeRequestEditorFinderServiceName != null){
            exchangeRequestEditorFinder = (EditorFinder)ServiceManagerFactory
                .getServiceObject(
                    exchangeRequestEditorFinderServiceName
                );
        }
        
        if(exchangeResponseEditorFinderServiceName != null){
            exchangeResponseEditorFinder = (EditorFinder)ServiceManagerFactory
                .getServiceObject(
                    exchangeResponseEditorFinderServiceName
                );
        }
        
        if(requestBytesEditorFinderServiceName != null){
            requestBytesEditorFinder = (EditorFinder)ServiceManagerFactory
                .getServiceObject(
                    requestBytesEditorFinderServiceName
                );
        }
        
        if(requestObjectEditorFinderServiceName != null){
            requestObjectEditorFinder = (EditorFinder)ServiceManagerFactory
                .getServiceObject(
                    requestObjectEditorFinderServiceName
                );
        }
        
        if(responseBytesEditorFinderServiceName != null){
            responseBytesEditorFinder = (EditorFinder)ServiceManagerFactory
                .getServiceObject(
                    responseBytesEditorFinderServiceName
                );
        }
        
        if(responseObjectEditorFinderServiceName != null){
            responseObjectEditorFinder = (EditorFinder)ServiceManagerFactory
                .getServiceObject(
                    responseObjectEditorFinderServiceName
                );
        }
        
        if(exceptionEditorFinderServiceName != null){
            exceptionEditorFinder = (EditorFinder)ServiceManagerFactory
                .getServiceObject(
                    exceptionEditorFinderServiceName
                );
        }
        
        if(beanFlowInvokerFactoryServiceName != null){
            beanFlowInvokerFactory = (BeanFlowInvokerFactory)ServiceManagerFactory
                .getServiceObject(
                    beanFlowInvokerFactoryServiceName
                );
        }
        
        if(beanFlowInvokerFactory != null){
            if(beanFlowSelectorServiceName != null){
                beanFlowSelector = (BeanFlowSelector)ServiceManagerFactory
                    .getServiceObject(
                        beanFlowSelectorServiceName
                    );
            }
            if(beanFlowSelector == null){
                beanFlowSelector = new DefaultBeanFlowSelectorService();
                ((DefaultBeanFlowSelectorService)beanFlowSelector).create();
                ((DefaultBeanFlowSelectorService)beanFlowSelector).start();
            }
        }
    }
    
    /**
     * �T�[�r�X�̔j���������s���B<p>
     *
     * @exception Exception �T�[�r�X�̔j���Ɏ��s�����ꍇ
     */
    public void destroyService() throws Exception{
        requestObjectTypeMap = null;
    }
    
    /**
     * �T�[�u���b�g�p�X�ɑ΂���v���I�u�W�F�N�g�܂��͂��̃N���X��ݒ肷��B<p>
     *
     * @param path �T�[�u���b�g�p�X
     * @param type �v���I�u�W�F�N�g�܂��͂��̃N���X
     */
    public void setRequestObjectType(String path, Object type){
        if(!(type instanceof Class) && !(type instanceof Cloneable)){
            throw new IllegalArgumentException("Not cloneable. type=" + type);
        }
        requestObjectTypeMap.put(path, type);
    }
    
    /**
     * �v���I�u�W�F�N�g��BeanFlow�Ŏ擾����ꍇ�Ɏg�p����{@link BeanFlowInvokerFactory}��ݒ肷��B<p>
     *
     * @param factory BeanFlowInvokerFactory
     */
    public void setBeanFlowInvokerFactory(BeanFlowInvokerFactory factory){
        beanFlowInvokerFactory = factory;
    }
    
    /**
     * �v���I�u�W�F�N�g��BeanFlow�Ŏ擾����ꍇ�Ƀt���[������肷��{@link BeanFlowSelector}��ݒ肷��B<p>
     *
     * @param selector BeanFlowSelector
     */
    public void setBeanFlowSelector(BeanFlowSelector selector){
        beanFlowSelector = selector;
    }
    
    /**
     * �v���X�g���[����v���I�u�W�F�N�g�ɕϊ�����{@link jp.ossc.nimbus.util.converter.StreamConverter StreamConverter}�T�[�r�X��ݒ肷��B<p>
     *
     * @param conv StreamConverter�T�[�r�X
     */
    public void setRequestStreamConverter(StreamConverter conv){
        requestStreamConverter = conv;
    }
    
    /**
     * �v���X�g���[����v���I�u�W�F�N�g�ɕϊ�����{@link jp.ossc.nimbus.util.converter.StreamConverter StreamConverter}�T�[�r�X���擾����B<p>
     *
     * @return StreamConverter�T�[�r�X
     */
    public StreamConverter getRequestStreamConverter(){
        return requestStreamConverter;
    }
    
    /**
     * �����I�u�W�F�N�g�������X�g���[���ɕϊ�����{@link jp.ossc.nimbus.util.converter.StreamConverter StreamConverter}�T�[�r�X��ݒ肷��B<p>
     *
     * @param conv StreamConverter�T�[�r�X
     */
    public void setResponseStreamConverter(StreamConverter conv){
        responseStreamConverter = conv;
    }
    
    /**
     * �����I�u�W�F�N�g�������X�g���[���ɕϊ�����{@link jp.ossc.nimbus.util.converter.StreamConverter StreamConverter}�T�[�r�X���擾����B<p>
     *
     * @return StreamConverter�T�[�r�X
     */
    public StreamConverter getResponseStreamConverter(){
        return responseStreamConverter;
    }
    
    /**
     * �v���I�u�W�F�N�g�y�щ����I�u�W�F�N�g���悹��{@link jp.ossc.nimbus.service.context.Context Context}�T�[�r�X��ݒ肷��B<p>
     *
     * @param context Context�T�[�r�X
     */
    public void setThreadContext(Context context){
        threadContext = context;
    }
    
    /**
     * �v���I�u�W�F�N�g�y�щ����I�u�W�F�N�g���悹��{@link jp.ossc.nimbus.service.context.Context Context}�T�[�r�X���擾����B<p>
     *
     * @return Context�T�[�r�X
     */
    public Context getThreadContext(){
        return threadContext;
    }
    
    /**
     * �W���[�i�����o�͂���{@link jp.ossc.nimbus.service.journal.Journal Journal}�T�[�r�X��ݒ肷��B<p>
     *
     * @param journal Journal�T�[�r�X
     */
    public void setJournal(Journal journal){
        this.journal = journal;
    }
    
    /**
     * �W���[�i�����o�͂���{@link jp.ossc.nimbus.service.journal.Journal Journal}�T�[�r�X���擾����B<p>
     *
     * @return Journal�T�[�r�X
     */
    public Journal getJournal(){
        return journal;
    }
    
    /**
     * �W���[�i���̃��[�g�X�e�b�v��ҏW����{@link jp.ossc.nimbus.service.journal.JournalEditor JournalEditor}����������{@link jp.ossc.nimbus.service.journal.editorfinder.EditorFinder EditorFinder}�T�[�r�X��ݒ肷��B<p>
     *
     * @param finder EditorFinder�T�[�r�X
     */
    public void setExchangeEditorFinder(EditorFinder finder){
        exchangeEditorFinder = finder;
    }
    
    /**
     * �W���[�i���̃��[�g�X�e�b�v��ҏW����{@link jp.ossc.nimbus.service.journal.JournalEditor JournalEditor}����������{@link jp.ossc.nimbus.service.journal.editorfinder.EditorFinder EditorFinder}�T�[�r�X���擾����B<p>
     *
     * @return EditorFinder�T�[�r�X
     */
    public EditorFinder getExchangeEditorFinder(){
        return exchangeEditorFinder;
    }
    
    /**
     * �W���[�i���̗v���X�e�b�v��ҏW����{@link jp.ossc.nimbus.service.journal.JournalEditor JournalEditor}����������{@link jp.ossc.nimbus.service.journal.editorfinder.EditorFinder EditorFinder}�T�[�r�X��ݒ肷��B<p>
     *
     * @param finder EditorFinder�T�[�r�X
     */
    public void setExchangeRequestEditorFinder(EditorFinder finder){
        exchangeRequestEditorFinder = finder;
    }
    
    /**
     * �W���[�i���̗v���X�e�b�v��ҏW����{@link jp.ossc.nimbus.service.journal.JournalEditor JournalEditor}����������{@link jp.ossc.nimbus.service.journal.editorfinder.EditorFinder EditorFinder}�T�[�r�X���擾����B<p>
     *
     * @return EditorFinder�T�[�r�X
     */
    public EditorFinder getExchangeRequestEditorFinder(){
        return exchangeRequestEditorFinder;
    }
    
    /**
     * �W���[�i���̉����X�e�b�v��ҏW����{@link jp.ossc.nimbus.service.journal.JournalEditor JournalEditor}����������{@link jp.ossc.nimbus.service.journal.editorfinder.EditorFinder EditorFinder}�T�[�r�X��ݒ肷��B<p>
     *
     * @param finder EditorFinder�T�[�r�X
     */
    public void setExchangeResponseEditorFinder(EditorFinder finder){
        exchangeResponseEditorFinder = finder;
    }
    
    /**
     * �W���[�i���̉����X�e�b�v��ҏW����{@link jp.ossc.nimbus.service.journal.JournalEditor JournalEditor}����������{@link jp.ossc.nimbus.service.journal.editorfinder.EditorFinder EditorFinder}�T�[�r�X���擾����B<p>
     *
     * @return EditorFinder�T�[�r�X
     */
    public EditorFinder getExchangeResponseEditorFinder(){
        return exchangeResponseEditorFinder;
    }
    
    /**
     * �W���[�i���̗v���o�C�g�z���ҏW����{@link jp.ossc.nimbus.service.journal.JournalEditor JournalEditor}����������{@link jp.ossc.nimbus.service.journal.editorfinder.EditorFinder EditorFinder}�T�[�r�X��ݒ肷��B<p>
     *
     * @param finder EditorFinder�T�[�r�X
     */
    public void setRequestBytesEditorFinder(EditorFinder finder){
        requestBytesEditorFinder = finder;
    }
    
    /**
     * �W���[�i���̗v���o�C�g�z���ҏW����{@link jp.ossc.nimbus.service.journal.JournalEditor JournalEditor}����������{@link jp.ossc.nimbus.service.journal.editorfinder.EditorFinder EditorFinder}�T�[�r�X���擾����B<p>
     *
     * @return EditorFinder�T�[�r�X
     */
    public EditorFinder getRequestBytesEditorFinder(){
        return requestBytesEditorFinder;
    }
    
    /**
     * �W���[�i���̗v���I�u�W�F�N�g��ҏW����{@link jp.ossc.nimbus.service.journal.JournalEditor JournalEditor}����������{@link jp.ossc.nimbus.service.journal.editorfinder.EditorFinder EditorFinder}�T�[�r�X��ݒ肷��B<p>
     *
     * @param finder EditorFinder�T�[�r�X
     */
    public void setRequestObjectEditorFinder(EditorFinder finder){
        requestObjectEditorFinder = finder;
    }
    
    /**
     * �W���[�i���̗v���I�u�W�F�N�g��ҏW����{@link jp.ossc.nimbus.service.journal.JournalEditor JournalEditor}����������{@link jp.ossc.nimbus.service.journal.editorfinder.EditorFinder EditorFinder}�T�[�r�X���擾����B<p>
     *
     * @return EditorFinder�T�[�r�X
     */
    public EditorFinder getRequestObjectEditorFinder(){
        return requestObjectEditorFinder;
    }
    
    /**
     * �W���[�i���̉����o�C�g�z���ҏW����{@link jp.ossc.nimbus.service.journal.JournalEditor JournalEditor}����������{@link jp.ossc.nimbus.service.journal.editorfinder.EditorFinder EditorFinder}�T�[�r�X��ݒ肷��B<p>
     *
     * @param finder EditorFinder�T�[�r�X
     */
    public void setResponseBytesEditorFinder(EditorFinder finder){
        responseBytesEditorFinder = finder;
    }
    
    /**
     * �W���[�i���̉����o�C�g�z���ҏW����{@link jp.ossc.nimbus.service.journal.JournalEditor JournalEditor}����������{@link jp.ossc.nimbus.service.journal.editorfinder.EditorFinder EditorFinder}�T�[�r�X���擾����B<p>
     *
     * @return EditorFinder�T�[�r�X
     */
    public EditorFinder getResponseBytesEditorFinder(){
        return responseBytesEditorFinder;
    }
    
    /**
     * �W���[�i���̉����I�u�W�F�N�g��ҏW����{@link jp.ossc.nimbus.service.journal.JournalEditor JournalEditor}����������{@link jp.ossc.nimbus.service.journal.editorfinder.EditorFinder EditorFinder}�T�[�r�X��ݒ肷��B<p>
     *
     * @param finder EditorFinder�T�[�r�X
     */
    public void setResponseObjectEditorFinder(EditorFinder finder){
        responseObjectEditorFinder = finder;
    }
    
    /**
     * �W���[�i���̉����I�u�W�F�N�g��ҏW����{@link jp.ossc.nimbus.service.journal.JournalEditor JournalEditor}����������{@link jp.ossc.nimbus.service.journal.editorfinder.EditorFinder EditorFinder}�T�[�r�X���擾����B<p>
     *
     * @return EditorFinder�T�[�r�X
     */
    public EditorFinder getResponseObjectEditorFinder(){
        return responseObjectEditorFinder;
    }
    
    /**
     * �W���[�i���̗�O��ҏW����{@link jp.ossc.nimbus.service.journal.JournalEditor JournalEditor}����������{@link jp.ossc.nimbus.service.journal.editorfinder.EditorFinder EditorFinder}�T�[�r�X��ݒ肷��B<p>
     *
     * @param finder EditorFinder�T�[�r�X
     */
    public void setExceptionEditorFinder(EditorFinder finder){
        exceptionEditorFinder = finder;
    }
    
    /**
     * �W���[�i���̗�O��ҏW����{@link jp.ossc.nimbus.service.journal.JournalEditor JournalEditor}����������{@link jp.ossc.nimbus.service.journal.editorfinder.EditorFinder EditorFinder}�T�[�r�X���擾����B<p>
     *
     * @return EditorFinder�T�[�r�X
     */
    public EditorFinder getExceptionEditorFinder(){
        return exceptionEditorFinder;
    }
    
    /**
     * Converter���g���ăX�g���[���Ɠ���I�u�W�F�N�g�̌������s���B<p>
     * ServletRequest#getInputStream()�Ŏ擾�������̓X�g���[����StreamConverter�œ���̃I�u�W�F�N�g�ɕϊ����āA���N�G�X�g�̑����ɐݒ肵�A���̃C���^�[�Z�v�^���Ăяo���B<br>
     * �܂��A���̃C���^�[�Z�v�^�̌Ăяo�������������ꍇ�́A���N�G�X�g�̑�������擾�����I�u�W�F�N�g��StreamConverter�ŃX�g���[���ɕϊ����āAServletResponse#getOutputStream()�Ŏ擾�����o�̓X�g���[���ɏ������ށB<br>
     * �T�[�r�X���J�n����Ă��Ȃ��ꍇ�́A���������Ɏ��̃C���^�[�Z�v�^���Ăяo���B<br>
     *
     * @param context �Ăяo���̃R���e�L�X�g���
     * @param chain ���̃C���^�[�Z�v�^���Ăяo�����߂̃`�F�[��
     * @return �Ăяo�����ʂ̖߂�l
     * @exception Throwable �Ăяo����ŗ�O�����������ꍇ�A�܂��͂��̃C���^�[�Z�v�^�ŔC�ӂ̗�O�����������ꍇ�B�A���A�{���Ăяo����鏈����throw���Ȃ�RuntimeException�ȊO�̗�O��throw���Ă��A�Ăяo�����ɂ͓`�d����Ȃ��B
     */
    public Object invokeFilter(
        ServletFilterInvocationContext context,
        InterceptorChain chain
    ) throws Throwable{
        if(getState() != STARTED){
            return chain.invokeNext(context);
        }
        try{
            if(journal != null){
                journal.startJournal(exchangeJournalKey, exchangeEditorFinder);
            }
            final ServletRequest request = context.getServletRequest();
            final ByteArrayOutputStream baos = new ByteArrayOutputStream();
            byte[] bytes = new byte[2048];
            if(requestStreamConverter != null){
                try{
                    if(journal != null){
                        journal.addStartStep(
                            exchangeRequestJournalKey,
                            exchangeRequestEditorFinder
                        );
                    }
                    final ServletInputStream sis = request.getInputStream();
                    try{
                        int size = 0;
                        while((size = sis.read(bytes)) != -1){
                            baos.write(bytes, 0, size);
                        }
                    }finally{
                        if(sis != null){
                            sis.close();
                        }
                    }
                    if(journal != null){
                        journal.addInfo(
                            requestBytesJournalKey,
                            baos.toByteArray(),
                            requestBytesEditorFinder
                        );
                    }
                    InputStream is = new ByteArrayInputStream(baos.toByteArray());
                    if(isRequestStreamInflate
                         && request instanceof HttpServletRequest){
                        is = decompress((HttpServletRequest)request, is);
                    }
                    StreamConverter rsc = requestStreamConverter;
                    if(request.getCharacterEncoding() != null
                        && rsc instanceof StreamStringConverter
                        && !request.getCharacterEncoding().equals(((StreamStringConverter)rsc).getCharacterEncodingToObject())){
                        rsc = ((StreamStringConverter)rsc)
                            .cloneCharacterEncodingToObject(
                                request.getCharacterEncoding());
                    }
                    Object requestObj = null;
                    if(rsc instanceof BindingStreamConverter
                        && request instanceof HttpServletRequest
                    ){
                        final HttpServletRequest httpReq = (HttpServletRequest)request;
                        if(requestObjectTypeMap.size() != 0){
                            String reqPath = httpReq.getServletPath();
                            if(httpReq.getPathInfo() != null){
                                reqPath = reqPath + httpReq.getPathInfo();
                            }
                            requestObj = requestObjectTypeMap.get(reqPath);
                            if(!(requestObj instanceof Class)){
                                if(requestObj instanceof DataSet){
                                    requestObj = ((DataSet)requestObj).cloneSchema();
                                }else if(requestObj instanceof RecordList){
                                    requestObj = ((RecordList)requestObj).cloneSchema();
                                }else if(requestObj instanceof Record){
                                    requestObj = ((Record)requestObj).cloneSchema();
                                }else{
                                    requestObj = requestObj.getClass().getMethod("clone", (Class[])null).invoke(requestObj, (Object[])null);
                                }
                            }
                        }
                        if(requestObj == null && beanFlowInvokerFactory != null){
                            String requestObjectFlowName = beanFlowSelector.selectBeanFlow(httpReq);
                            if(requestObjectFlowNamePrefix != null){
                                requestObjectFlowName = requestObjectFlowNamePrefix + requestObjectFlowName;
                            }
                            if(beanFlowInvokerFactory.containsFlow(requestObjectFlowName)){
                                final BeanFlowInvoker beanFlowInvoker
                                    = beanFlowInvokerFactory.createFlow(requestObjectFlowName);
                                requestObj = beanFlowInvoker.invokeFlow(context);
                            }
                        }
                        requestObj = ((BindingStreamConverter)rsc).convertToObject(is, requestObj);
                    }else{
                        requestObj = rsc.convertToObject(is);
                    }
                    if(journal != null){
                        journal.addInfo(
                            requestObjectJournalKey,
                            requestObj,
                            requestObjectEditorFinder
                        );
                    }
                    request.setAttribute(requestObjectAttributeName, requestObj);
                    if(threadContext != null){
                        threadContext.put(requestObjectContextKey, requestObj);
                    }
                }catch(Exception e){
                    if(journal != null){
                        journal.addInfo(
                            exceptionJournalKey,
                            e,
                            exceptionEditorFinder
                        );
                    }
                    throw new InputExchangeException(e);
                }catch(Throwable th){
                    if(journal != null){
                        journal.addInfo(
                            exceptionJournalKey,
                            th,
                            exceptionEditorFinder
                        );
                    }
                    throw th;
                }finally{
                   if(journal != null){
                        journal.addEndStep();
                    }
                }
            }
            
            final Object ret = chain.invokeNext(context);
            
            if(responseStreamConverter != null){
                final ServletResponse response = context.getServletResponse();
                if(!response.isCommitted()){
                    try{
                        if(journal != null){
                            journal.addStartStep(
                                exchangeResponseJournalKey,
                                exchangeResponseEditorFinder
                            );
                        }
                        if(responseContentType != null){
                            response.setContentType(responseContentType);
                        }
                        Object responseObj = request.getAttribute(responseObjectAttributeName);
                        if(responseObj == null && threadContext != null){
                            responseObj = threadContext.get(responseObjectContextKey);
                        }
                        if(journal != null){
                            journal.addInfo(
                                responseObjectJournalKey,
                                responseObj,
                                responseObjectEditorFinder
                            );
                        }
                        if(responseObj != null){
                            StreamConverter rsc = responseStreamConverter;
                            if(response.getCharacterEncoding() != null
                                && rsc instanceof StreamStringConverter
                                && !response.getCharacterEncoding().equals(((StreamStringConverter)rsc).getCharacterEncodingToStream())){
                                rsc = ((StreamStringConverter)rsc)
                                    .cloneCharacterEncodingToStream(
                                        response.getCharacterEncoding());
                            }
                            final InputStream is = rsc.convertToStream(responseObj);
                            final ServletOutputStream sos = response.getOutputStream();
                            int readLen = 0;
                            baos.reset();
                            while((readLen = is.read(bytes)) != -1){
                                baos.write(bytes, 0, readLen);
                                sos.write(bytes, 0, readLen);
                            }
                            if(journal != null){
                                journal.addInfo(
                                    responseBytesJournalKey,
                                    baos.toByteArray(),
                                    responseBytesEditorFinder
                                );
                            }
                        }
                    }catch(Exception e){
                        if(journal != null){
                            journal.addInfo(
                                exceptionJournalKey,
                                e,
                                exceptionEditorFinder
                            );
                        }
                        throw new OutputExchangeException(e);
                    }catch(Throwable th){
                        if(journal != null){
                            journal.addInfo(
                                exceptionJournalKey,
                                th,
                                exceptionEditorFinder
                            );
                        }
                        throw th;
                    }finally{
                        if(journal != null){
                            journal.addEndStep();
                        }
                    }
                }
            }
            return ret;
        }catch(Throwable th){
           if(journal != null){
                journal.addInfo(
                    exceptionJournalKey,
                    th,
                    exceptionEditorFinder
                );
           }
           throw th;
        }finally{
            if(journal != null){
                journal.endJournal();
            }
        }
    }
    
    /**
     * ���̓X�g���[���̈��k����������B<p>
     * (Content-Encoding�Ɏw�肳�ꂽ�t���ŉ���)
     * 
     * @param request HTTP���N�G�X�g
     * @param is ���̓X�g���[��
     * @return ���k�������ꂽ���̓X�g���[��
     * @throws IOException �T�|�[�g���Ă��Ȃ����k�`��(deflate, gzip�ȊO)���w�肳�ꂽ�ꍇ
     */
    protected InputStream decompress(HttpServletRequest request, InputStream is) throws IOException {
        // �w�b�_�[[Content-Encoding]�̒l���擾
        Enumeration encodeEnum = request.getHeaders(HEADER_CONTENT_ENCODING);
        if(encodeEnum == null || !encodeEnum.hasMoreElements()){
            return is;
        }
        InputStream in = is;
        // ���k���ꂽ�t���ŉ�
        List encodes = new ArrayList();
        while(encodeEnum.hasMoreElements()){
            encodes.add(encodeEnum.nextElement());
        }
        for(int i = (encodes.size() - 1); i >= 0; i--){
            final String encode = (String)encodes.get(i);
            if(encode != null){
                if(encode.indexOf(CONTENT_ENCODING_DEFLATE) != -1){
                    // deflate���k����
                    in = new InflaterInputStream(in);
                }else if(encode.indexOf(CONTENT_ENCODING_GZIP) != -1
                            || encode.indexOf(CONTENT_ENCODING_X_GZIP) != -1){
                    // gzip���k����
                    in = new GZIPInputStream(in);
                }else{
                    throw new IOException("Can not decompress. [" + encode + "]");
                }
            }
        }
        final ByteArrayOutputStream baos = new ByteArrayOutputStream();
        final byte[] bytes = new byte[1024];
        int length = 0;
        while((length = in.read(bytes)) != -1){
            baos.write(bytes, 0, length);
        }
        byte[] outputBytes = baos.toByteArray();
        final ByteArrayInputStream bais
             = new ByteArrayInputStream(outputBytes);
        return bais;
    }
}