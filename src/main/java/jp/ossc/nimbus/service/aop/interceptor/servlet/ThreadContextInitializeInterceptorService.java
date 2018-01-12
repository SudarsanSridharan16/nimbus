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

import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;

import jp.ossc.nimbus.core.*;
import jp.ossc.nimbus.service.aop.*;
import jp.ossc.nimbus.service.context.*;
import jp.ossc.nimbus.service.sequence.*;
import jp.ossc.nimbus.service.codemaster.*;

/**
 * �X���b�h�R���e�L�X�g�������C���^�[�Z�v�^�B<p>
 *
 * @author M.Takata
 */
public class ThreadContextInitializeInterceptorService
 extends ServletFilterInterceptorService
 implements ThreadContextInitializeInterceptorServiceMBean{
    
    private static final long serialVersionUID = -3154621046378825548L;
    
    protected ServiceName threadContextServiceName;
    protected Context threadContext;
    protected ServiceName sequenceServiceName;
    protected Sequence sequence;
    protected ServiceName codeMasterFinderServiceName;
    protected CodeMasterFinder codeMasterFinder;
    protected ServiceNameRef[] contextValueServiceNames;
    protected Properties contextValueRequestParameter;
    protected Map contextValueMapping;
    
    protected boolean isOutputContextPath = true;
    protected boolean isOutputServletPath = true;
    protected boolean isOutputSessionID = true;
    protected boolean isNewSession = false;
    protected boolean isOutputThreadName = true;
    protected boolean isOutputThreadGroupName = true;
    protected boolean isInitializeRecursiveCall = true;
    protected boolean isClear = true;
    
    protected ThreadLocal callStack;
    
    // ThreadContextInitializeInterceptorServiceMBean��JavaDoc
    public void setThreadContextServiceName(ServiceName name){
        threadContextServiceName = name;
    }
    
    // ThreadContextInitializeInterceptorServiceMBean��JavaDoc
    public ServiceName getThreadContextServiceName(){
        return threadContextServiceName;
    }
    
    // ThreadContextInitializeInterceptorServiceMBean��JavaDoc
    public void setCodeMasterFinderServiceName(ServiceName name){
        codeMasterFinderServiceName = name;
    }
    
    // ThreadContextInitializeInterceptorServiceMBean��JavaDoc
    public ServiceName getCodeMasterFinderServiceName(){
        return codeMasterFinderServiceName;
    }
    
    // ThreadContextInitializeInterceptorServiceMBean��JavaDoc
    public void setSequenceServiceName(ServiceName name){
        sequenceServiceName = name;
    }
    
    // ThreadContextInitializeInterceptorServiceMBean��JavaDoc
    public ServiceName getSequenceServiceName(){
        return sequenceServiceName;
    }
    
    // ThreadContextInitializeInterceptorServiceMBean��JavaDoc
    public void setContextValueServiceNames(ServiceNameRef[] names){
        contextValueServiceNames = names;
    }
    
    // ThreadContextInitializeInterceptorServiceMBean��JavaDoc
    public ServiceNameRef[] getContextValueServiceNames(){
        return contextValueServiceNames;
    }
    
    // ThreadContextInitializeInterceptorServiceMBean��JavaDoc
    public void setContextValueRequestParameter(Properties map){
        contextValueRequestParameter = map;
    }
    
    // ThreadContextInitializeInterceptorServiceMBean��JavaDoc
    public Properties getContextValueRequestParameter(){
        return contextValueRequestParameter;
    }
    
    // ThreadContextInitializeInterceptorServiceMBean��JavaDoc
    public void setContextValueMapping(Map mapping){
        contextValueMapping = mapping;
    }
    
    // ThreadContextInitializeInterceptorServiceMBean��JavaDoc
    public Map getContextValueMapping(){
        return contextValueMapping;
    }
    
    // ThreadContextInitializeInterceptorServiceMBean��JavaDoc
    public void setContextValue(String key, Object value){
        if(contextValueMapping == null){
            contextValueMapping = new HashMap();
        }
        contextValueMapping.put(key, value);
    }
    
    // ThreadContextInitializeInterceptorServiceMBean��JavaDoc
    public Object getContextValue(String key){
        if(contextValueMapping == null){
            return null;
        }
        return contextValueMapping.get(key);
    }
    
    // ThreadContextInitializeInterceptorServiceMBean��JavaDoc
    public void setOutputContextPath(boolean isOutput){
        isOutputContextPath = isOutput;
    }
    
    // ThreadContextInitializeInterceptorServiceMBean��JavaDoc
    public boolean isOutputContextPath(){
        return isOutputContextPath;
    }
    
    // ThreadContextInitializeInterceptorServiceMBean��JavaDoc
    public void setOutputServletPath(boolean isOutput){
        isOutputServletPath = isOutput;
    }
    
    // ThreadContextInitializeInterceptorServiceMBean��JavaDoc
    public boolean isOutputServletPath(){
        return isOutputServletPath;
    }
    
    // ThreadContextInitializeInterceptorServiceMBean��JavaDoc
    public void setOutputSessionID(boolean isOutput){
        isOutputSessionID = isOutput;
    }
    
    // ThreadContextInitializeInterceptorServiceMBean��JavaDoc
    public boolean isOutputSessionID(){
        return isOutputSessionID;
    }
    
    // ThreadContextInitializeInterceptorServiceMBean��JavaDoc
    public void setOutputThreadName(boolean isOutput){
        isOutputThreadName = isOutput;
    }
    
    // ThreadContextInitializeInterceptorServiceMBean��JavaDoc
    public boolean isOutputThreadName(){
        return isOutputThreadName;
    }
    
    // ThreadContextInitializeInterceptorServiceMBean��JavaDoc
    public void setOutputThreadGroupName(boolean isOutput){
        isOutputThreadGroupName = isOutput;
    }
    
    // ThreadContextInitializeInterceptorServiceMBean��JavaDoc
    public boolean isOutputThreadGroupName(){
        return isOutputThreadGroupName;
    }
    
    // ThreadContextInitializeInterceptorServiceMBean��JavaDoc
    public void setNewSession(boolean isNew){
        isNewSession = isNew;
    }
    
    // ThreadContextInitializeInterceptorServiceMBean��JavaDoc
    public boolean isNewSession(){
        return isNewSession;
    }
    
    // ThreadContextInitializeInterceptorServiceMBean��JavaDoc
    public boolean isInitializeRecursiveCall(){
        return isInitializeRecursiveCall;
    }
    
    // ThreadContextInitializeInterceptorServiceMBean��JavaDoc
    public void setInitializeRecursiveCall(boolean isInitialize){
        isInitializeRecursiveCall = isInitialize;
    }
    
    // ThreadContextInitializeInterceptorServiceMBean��JavaDoc
    public boolean isClear(){
        return isClear;
    }
    
    // ThreadContextInitializeInterceptorServiceMBean��JavaDoc
    public void setClear(boolean isClear){
        this.isClear = isClear;
    }
    
    /**
     * �T�[�r�X�̊J�n�������s���B<p>
     *
     * @exception Exception �T�[�r�X�̊J�n�Ɏ��s�����ꍇ
     */
    public void startService() throws Exception{
        if(threadContextServiceName == null){
            throw new IllegalArgumentException(
                "threadContextServiceName must be specified."
            );
        }
        threadContext = (Context)ServiceManagerFactory
            .getServiceObject(threadContextServiceName);
        if(sequenceServiceName != null){
            sequence = (Sequence)ServiceManagerFactory
                .getServiceObject(sequenceServiceName);
        }
        if(codeMasterFinderServiceName != null){
            codeMasterFinder = (CodeMasterFinder)ServiceManagerFactory
                .getServiceObject(codeMasterFinderServiceName);
        }
        if(!isInitializeRecursiveCall){
            callStack = new ThreadLocal(){
                protected Object initialValue(){
                    return new CallStack();
                }
            };
        }
    }
    
    /**
     * �T�[�r�X�̒�~�������s���B<p>
     *
     * @exception Exception �T�[�r�X�̒�~�Ɏ��s�����ꍇ
     */
    public void stopService() throws Exception{
        callStack = null;
    }
    
    /**
     * �X���b�h�R���e�L�X�g�����������āA���̃C���^�[�Z�v�^���Ăяo���B<p>
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
        if(getState() == STARTED
            && (callStack == null
                 || ((CallStack)callStack.get()).stackIndex == 0)){
            
            final ServletRequest request = context.getServletRequest();
            if(isClear){
                threadContext.clear();
            }
            
            if(sequence != null){
                final String requestId = sequence.increment();
                threadContext.put(ThreadContextKey.REQUEST_ID, requestId);
            }
            
            if(contextValueServiceNames != null){
                for(int i = 0; i < contextValueServiceNames.length; i++){
                    threadContext.put(
                        contextValueServiceNames[i]
                            .getReferenceServiceName(),
                        ServiceManagerFactory.getServiceObject(
                            contextValueServiceNames[i].getServiceName()
                        )
                    );
                }
            }
            
            if(contextValueRequestParameter != null
                 && contextValueRequestParameter.size() != 0){
                final Iterator keys = contextValueRequestParameter.keySet().iterator();
                while(keys.hasNext()){
                    final String key = (String)keys.next();
                    final String parameterName
                         = contextValueRequestParameter.getProperty(key);
                    String[] params = request.getParameterValues(parameterName);
                    if(params != null){
                        if(params.length == 1){
                            threadContext.put(
                                key,
                                params[0]
                            );
                        }else{
                            threadContext.put(
                                key,
                                params
                            );
                        }
                    }
                }
            }
            
            if(contextValueMapping != null
                && contextValueMapping.size() != 0){
                final Iterator keys = contextValueMapping.keySet().iterator();
                while(keys.hasNext()){
                    final String key = (String)keys.next();
                    threadContext.put(
                        key,
                        contextValueMapping.get(key)
                    );
                }
            }
            
            if(request instanceof HttpServletRequest){
                final HttpServletRequest httpReq = (HttpServletRequest)request;
                
                if(isOutputContextPath){
                    threadContext.put(
                        ThreadContextKey.CONTEXT_PATH,
                        httpReq.getContextPath()
                    );
                }
                if(isOutputServletPath){
                    threadContext.put(
                        ThreadContextKey.SERVLET_PATH,
                        httpReq.getServletPath()
                    );
                }
                final HttpSession session = httpReq.getSession(isNewSession);
                if(isOutputSessionID){
                    if(session != null){
                        threadContext.put(
                            ThreadContextKey.SESSION_ID,
                            session.getId()
                        );
                    }
                }
            }
            
            final Thread thread = Thread.currentThread();
            if(isOutputThreadName){
                threadContext.put(
                    ThreadContextKey.THREAD_NAME,
                    thread.getName()
                );
            }
            if(isOutputThreadGroupName){
                final ThreadGroup threadGroup = thread.getThreadGroup();
                threadContext.put(
                    ThreadContextKey.THREAD_GROUP_NAME,
                    threadGroup.getName()
                );
            }
            if(codeMasterFinder != null){
                final Map codeMasters = codeMasterFinder.getCodeMasters();
                threadContext.put(ThreadContextKey.CODEMASTER, codeMasters);
            }
        }
        try{
            if(callStack != null){
                ((CallStack)callStack.get()).stackIndex++;
            }
            return chain.invokeNext(context);
        }finally{
            if(callStack != null){
                ((CallStack)callStack.get()).stackIndex--;
                if(isClear && ((CallStack)callStack.get()).stackIndex == 0){
                    threadContext.clear();
                }
            }else{
                if(isClear){
                    threadContext.clear();
                }
            }
        }
    }
    
    /**
     * �X���b�h�R���e�L�X�g�Ăяo���X�^�b�N�B<p>
     *
     * @author M.Takata
     */
    protected static class CallStack{
        
        /**
         * �X���b�h�R���e�L�X�g�������C���^�[�Z�v�^������q�ŌĂяo���ꂽ�ꍇ�ɁA���̓���q�̐[���������C���f�b�N�X�B<p>
         */
        public int stackIndex;
    }
}
