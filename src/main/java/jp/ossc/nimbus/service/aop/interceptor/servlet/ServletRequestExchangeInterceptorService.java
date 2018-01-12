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

import javax.servlet.*;

import jp.ossc.nimbus.core.*;
import jp.ossc.nimbus.service.aop.*;
import jp.ossc.nimbus.service.context.*;
import jp.ossc.nimbus.service.journal.*;
import jp.ossc.nimbus.service.journal.editorfinder.*;
import jp.ossc.nimbus.util.converter.*;

/**
 * �T�[�u���b�g���N�G�X�g�����C���^�[�Z�v�^�B<p>
 * {@link javax.servlet.ServletRequest}��{@link jp.ossc.nimbus.util.converter.Converter#convert(Object) Converter#convert(Object)}�ŔC�ӂ̃I�u�W�F�N�g�ɕϊ����āA���N�G�X�g�����ɐݒ肷��B<br>
 *
 * @author M.Takata
 */
public class ServletRequestExchangeInterceptorService
 extends ServletFilterInterceptorService
 implements ServletRequestExchangeInterceptorServiceMBean{
    
    private static final long serialVersionUID = 2844563371397261067L;
    
    protected ServiceName converterServiceName;
    protected Converter converter;
    
    protected ServiceName threadContextServiceName;
    protected Context threadContext;
    
    protected ServiceName journalServiceName;
    protected Journal journal;
    
    protected ServiceName exchangeEditorFinderServiceName;
    protected EditorFinder exchangeEditorFinder;
    
    protected ServiceName requestObjectEditorFinderServiceName;
    protected EditorFinder requestObjectEditorFinder;
    
    protected ServiceName exceptionEditorFinderServiceName;
    protected EditorFinder exceptionEditorFinder;
    
    protected String exchangeJournalKey = DEFAULT_EXCHANGE_JOURNAL_KEY;
    protected String requestObjectJournalKey = DEFAULT_REQUEST_OBJECT_JOURNAL_KEY;
    protected String exceptionJournalKey = DEFAULT_EXCEPTION_JOURNAL_KEY;
    
    protected String requestObjectAttributeName
         = DEFAULT_REQUEST_OBJECT_ATTRIBUTE_NAME;
    
    protected String requestObjectContextKey
         = DEFAULT_REQUEST_OBJECT_CONTEXT_KEY;
    
    // ServletRequestExchangeInterceptorServiceMBean ��JavaDoc
    public void setConverterServiceName(ServiceName name){
        converterServiceName = name;
    }
    // ServletRequestExchangeInterceptorServiceMBean ��JavaDoc
    public ServiceName getConverterServiceName(){
        return converterServiceName;
    }
    
    // ServletRequestExchangeInterceptorServiceMBean ��JavaDoc
    public void setThreadContextServiceName(ServiceName name){
        threadContextServiceName = name;
    }
    // ServletRequestExchangeInterceptorServiceMBean ��JavaDoc
    public ServiceName getThreadContextServiceName(){
        return threadContextServiceName;
    }
    
    // ServletRequestExchangeInterceptorServiceMBean ��JavaDoc
    public void setJournalServiceName(ServiceName name){
        journalServiceName = name;
    }
    // ServletRequestExchangeInterceptorServiceMBean ��JavaDoc
    public ServiceName getJournalServiceName(){
        return journalServiceName;
    }
    
    // ServletRequestExchangeInterceptorServiceMBean ��JavaDoc
    public void setExchangeEditorFinderServiceName(ServiceName name){
        exchangeEditorFinderServiceName = name;
    }
    // ServletRequestExchangeInterceptorServiceMBean ��JavaDoc
    public ServiceName getExchangeEditorFinderServiceName(){
        return exchangeEditorFinderServiceName;
    }
    
    // ServletRequestExchangeInterceptorServiceMBean ��JavaDoc
    public void setRequestObjectEditorFinderServiceName(ServiceName name){
        requestObjectEditorFinderServiceName = name;
    }
    // ServletRequestExchangeInterceptorServiceMBean ��JavaDoc
    public ServiceName getRequestObjectEditorFinderServiceName(){
        return requestObjectEditorFinderServiceName;
    }
    
    // ServletRequestExchangeInterceptorServiceMBean ��JavaDoc
    public void setExceptionEditorFinderServiceName(ServiceName name){
        exceptionEditorFinderServiceName = name;
    }
    // ServletRequestExchangeInterceptorServiceMBean ��JavaDoc
    public ServiceName getExceptionEditorFinderServiceName(){
        return exceptionEditorFinderServiceName;
    }
    
    // ServletRequestExchangeInterceptorServiceMBean ��JavaDoc
    public void setExchangeJournalKey(String key){
        exchangeJournalKey = key;
    }
    // ServletRequestExchangeInterceptorServiceMBean ��JavaDoc
    public String getExchangeJournalKey(){
        return exchangeJournalKey;
    }
    
    // ServletRequestExchangeInterceptorServiceMBean ��JavaDoc
    public void setRequestObjectJournalKey(String key){
        requestObjectJournalKey = key;
    }
    // ServletRequestExchangeInterceptorServiceMBean ��JavaDoc
    public String getRequestObjectJournalKey(){
        return requestObjectJournalKey;
    }
    
    // ServletRequestExchangeInterceptorServiceMBean ��JavaDoc
    public void setExceptionJournalKey(String key){
        exceptionJournalKey = key;
    }
    // ServletRequestExchangeInterceptorServiceMBean ��JavaDoc
    public String getExceptionJournalKey(){
        return exceptionJournalKey;
    }
    
    // ServletRequestExchangeInterceptorServiceMBean ��JavaDoc
    public void setRequestObjectAttributeName(String name){
        requestObjectAttributeName = name;
    }
    // ServletRequestExchangeInterceptorServiceMBean ��JavaDoc
    public String getRequestObjectAttributeName(){
        return requestObjectAttributeName;
    }
    
    // ServletRequestExchangeInterceptorServiceMBean ��JavaDoc
    public void setRequestObjectContextKey(String key){
        requestObjectContextKey = key;
    }
    // ServletRequestExchangeInterceptorServiceMBean ��JavaDoc
    public String getRequestObjectContextKey(){
        return requestObjectContextKey;
    }
    
    /**
     * �T�[�r�X�̊J�n�������s���B<p>
     *
     * @exception Exception �T�[�r�X�̊J�n�Ɏ��s�����ꍇ
     */
    public void startService() throws Exception{
        if(converterServiceName == null
             && converter == null){
            throw new IllegalArgumentException("It is necessary to specify ConverterServiceName or Converter.");
        }
        if(converterServiceName != null){
            converter = (Converter)ServiceManagerFactory
                .getServiceObject(converterServiceName);
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
        
        if(requestObjectEditorFinderServiceName != null){
            requestObjectEditorFinder = (EditorFinder)ServiceManagerFactory
                .getServiceObject(
                    requestObjectEditorFinderServiceName
                );
        }
        
        if(exceptionEditorFinderServiceName != null){
            exceptionEditorFinder = (EditorFinder)ServiceManagerFactory
                .getServiceObject(
                    exceptionEditorFinderServiceName
                );
        }
    }
    
    /**
     * �T�[�u���b�g���N�G�X�g��v���I�u�W�F�N�g�ɕϊ�����{@link jp.ossc.nimbus.util.converter.Converter Converter}�T�[�r�X��ݒ肷��B<p>
     *
     * @param conv Converter�T�[�r�X
     */
    public void setConverter(Converter conv){
        converter = conv;
    }
    
    /**
     * �T�[�u���b�g���N�G�X�g��v���I�u�W�F�N�g�ɕϊ�����{@link jp.ossc.nimbus.util.converter.Converter Converter}�T�[�r�X���擾����B<p>
     *
     * @return Converter�T�[�r�X
     */
    public Converter getConverter(){
        return converter;
    }
    
    /**
     * �v���I�u�W�F�N�g���悹��{@link jp.ossc.nimbus.service.context.Context Context}�T�[�r�X��ݒ肷��B<p>
     *
     * @param context Context�T�[�r�X
     */
    public void setThreadContext(Context context){
        threadContext = context;
    }
    
    /**
     * �v���I�u�W�F�N�g���悹��{@link jp.ossc.nimbus.service.context.Context Context}�T�[�r�X���擾����B<p>
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
     * Converter���g���ăT�[�u���b�g���N�G�X�g�Ɠ���I�u�W�F�N�g�̌������s���B<p>
     * ServletRequest��Converter�œ���̃I�u�W�F�N�g�ɕϊ����āA���N�G�X�g�̑����ɐݒ肵�A���̃C���^�[�Z�v�^���Ăяo���B<br>
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
            Object requestObj = null;
            try{
                requestObj = converter.convert(request);
            }catch(Exception e){
                throw new InputExchangeException(e);
            }
            if(journal != null){
                journal.addInfo(
                    requestObjectJournalKey,
                    requestObj,
                    requestObjectEditorFinder
                );
            }
            request.setAttribute(requestObjectAttributeName, requestObj);
                
            final Object ret = chain.invokeNext(context);
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
}