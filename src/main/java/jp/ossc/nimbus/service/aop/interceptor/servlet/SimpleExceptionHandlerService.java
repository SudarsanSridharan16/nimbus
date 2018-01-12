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

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import jp.ossc.nimbus.core.ServiceBase;
import jp.ossc.nimbus.core.ServiceManagerFactory;
import jp.ossc.nimbus.core.ServiceName;
import jp.ossc.nimbus.service.journal.Journal;
import jp.ossc.nimbus.service.journal.editorfinder.EditorFinder;
import jp.ossc.nimbus.service.log.Logger;

/**
 * �Ȉ՗�O�n���h���T�[�r�X�B<p>
 * ���O�o�͂ƃW���[�i���o�͂��s���ȈՂȗ�O�n���h���ł���B<br>
 *
 * @author M.Takata
 */
public class SimpleExceptionHandlerService extends ServiceBase
 implements SimpleExceptionHandlerServiceMBean, ExceptionHandler {
    
    private static final long serialVersionUID = 6936095762222810644L;
    
    protected ServiceName journalServiceName;
    protected Journal journal;
    protected String logMessageCode;
    protected boolean isOutputStackTraceLog;
    protected String exceptionJournalKey = DEFAULT_EXCEPTION_JOURNAL_KEY;
    protected ServiceName exceptionEditorFinderServiceName;
    protected EditorFinder exceptionEditorFinder;
    
    // SimpleExceptionHandlerServiceMBean��JavaDoc
    public void setJournalServiceName(ServiceName name){
        journalServiceName = name;
    }
    // SimpleExceptionHandlerServiceMBean��JavaDoc
    public ServiceName getJournalServiceName(){
        return journalServiceName;
    }
    
    // SimpleExceptionHandlerServiceMBean��JavaDoc
    public void setLogMessageCode(String code){
        logMessageCode = code;
    }
    // SimpleExceptionHandlerServiceMBean��JavaDoc
    public String getLogMessageCode(){
        return logMessageCode;
    }
    
    // SimpleExceptionHandlerServiceMBean��JavaDoc
    public void setOutputStackTraceLog(boolean isOutput){
        isOutputStackTraceLog = isOutput;
    }
    // SimpleExceptionHandlerServiceMBean��JavaDoc
    public boolean isOutputStackTraceLog(){
        return isOutputStackTraceLog;
    }
    
    // SimpleExceptionHandlerServiceMBean��JavaDoc
    public void setExceptionJournalKey(String key){
        exceptionJournalKey = key;
    }
    // SimpleExceptionHandlerServiceMBean��JavaDoc
    public String getExceptionJournalKey(){
        return exceptionJournalKey;
    }
    
    // SimpleExceptionHandlerServiceMBean��JavaDoc
    public void setExceptionEditorFinderServiceName(ServiceName name){
        exceptionEditorFinderServiceName = name;
    }
    // SimpleExceptionHandlerServiceMBean��JavaDoc
    public ServiceName getExceptionEditorFinderServiceName(){
        return exceptionEditorFinderServiceName;
    }
    
    /**
     * �T�[�r�X�̊J�n�������s���B<p>
     *
     * @exception Exception �T�[�r�X�̊J�n�Ɏ��s�����ꍇ
     */
    public void startService() throws Exception{
        if(journalServiceName != null){
            journal = (Journal)ServiceManagerFactory
                .getServiceObject(journalServiceName);
        }
        
        if(exceptionEditorFinderServiceName != null){
            exceptionEditorFinder = (EditorFinder)ServiceManagerFactory
                .getServiceObject(exceptionEditorFinderServiceName);
        }
    }
    
    public void handleException(
        Throwable th,
        ServletRequest request,
        ServletResponse response
    ) throws Throwable {
        if(logMessageCode != null){
            final Logger log = super.getLogger();
            if(isOutputStackTraceLog){
                log.write(logMessageCode, th);
            }else{
                log.write(logMessageCode);
            }
        }
        if(journal != null){
            journal.addInfo(
                exceptionJournalKey,
                th,
                exceptionEditorFinder
            );
        }
    }
}
