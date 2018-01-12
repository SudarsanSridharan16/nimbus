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
package jp.ossc.nimbus.service.aop.interceptor;

import java.util.Locale;

import jp.ossc.nimbus.core.*;
import jp.ossc.nimbus.service.aop.*;
import jp.ossc.nimbus.service.message.*;

/**
 * ��O�C���^�[�Z�v�^�B<p>
 * ���\�b�h�̌Ăяo���ɑ΂��āA���������ɗ�O��Ԃ��C���^�[�Z�v�^�ł���B<br>
 * �ȉ��ɁAUnsupportedOperationException��throw����C���^�[�Z�v�^�̃T�[�r�X��`��������B<br>
 * <pre>
 * &lt;?xml version="1.0" encoding="Shift_JIS"?&gt;
 * 
 * &lt;server&gt;
 *     
 *     &lt;manager name="Sample"&gt;
 *         
 *         &lt;service name="UnsupportedOperationExceptionTrowInterceptor"
 *                  code="jp.ossc.nimbus.service.aop.interceptor.ExceptionThrowInterceptorService"&gt;
 *             &lt;attribute name="ExceptionClassName"&gt;java.lang.UnsupportedOperationException&lt;/attribute&gt;
 *             &lt;attribute name="Message"&gt;���̃��\�b�h�͌Ăяo���Ă͂����܂���B&lt;/attribute&gt;
 *         &lt;/service&gt;
 *         
 *     &lt;/manager&gt;
 *     
 * &lt;/server&gt;
 * </pre>
 * 
 * @author M.Takata
 */
public class ExceptionThrowInterceptorService extends ServiceBase
 implements Interceptor, ExceptionThrowInterceptorServiceMBean, ExceptionThrow{
    
    private static final long serialVersionUID = -7750833087537700407L;
    
    private String exceptionClassName = RuntimeException.class.getName();
    private Throwable exception;
    private String message;
    private ServiceName messageRecordFactoryServiceName;
    private MessageRecordFactory messageRecordFactory;
    private String messageKey;
    private String[] messageArgs;
    private Locale messageLocale;
    private boolean isEnabled = true;
    
    // ExceptionThrowInterceptorServiceMBean��JavaDoc
    public void setEnabled(boolean enabled){
        isEnabled = enabled;
    }
    
    // ExceptionThrowInterceptorServiceMBean��JavaDoc
    public boolean isEnabled(){
        return isEnabled;
    }
    
    // ExceptionThrowInterceptorServiceMBean��JavaDoc
    public void setExceptionClassName(String className){
        exceptionClassName = className;
    }
    
    // ExceptionThrowInterceptorServiceMBean��JavaDoc
    public String getExceptionClassName(){
        return exceptionClassName;
    }
    
    // ExceptionThrowInterceptorServiceMBean��JavaDoc
    public void setMessage(String msg){
        message = msg;
    }
    
    // ExceptionThrowInterceptorServiceMBean��JavaDoc
    public String getMessage(){
        return message;
    }
    
    // ExceptionThrowInterceptorServiceMBean��JavaDoc
    public void setMessageRecordFactoryServiceName(ServiceName name){
        messageRecordFactoryServiceName = name;
    }
    
    // ExceptionThrowInterceptorServiceMBean��JavaDoc
    public ServiceName getMessageRecordFactoryServiceName(){
        return messageRecordFactoryServiceName;
    }
    
    // ExceptionThrowInterceptorServiceMBean��JavaDoc
    public void setMessageKey(String key){
        messageKey = key;
    }
    
    // ExceptionThrowInterceptorServiceMBean��JavaDoc
    public String getMessageKey(){
        return messageKey;
    }
    
    // ExceptionThrowInterceptorServiceMBean��JavaDoc
    public void setMessageArgs(String[] args){
        messageArgs = args;
    }
    
    // ExceptionThrowInterceptorServiceMBean��JavaDoc
    public String[] getMessageArgs(){
        return messageArgs;
    }
    
    // ExceptionThrowInterceptorServiceMBean��JavaDoc
    public void setMessageLocale(Locale locale){
        messageLocale = locale;
    }
    
    // ExceptionThrowInterceptorServiceMBean��JavaDoc
    public Locale getMessageLocale(){
        return messageLocale;
    }
    
    /**
     * MessageRecordFactory��ݒ肷��B<p>
     *
     * @param factory MessageRecordFactory
     */
    public void setMessageRecordFactoryService(MessageRecordFactory factory) {
        messageRecordFactory = factory;
    }
    
    public void setException(Throwable ex) {
        exception = ex;
    }
    
    /**
     * �T�[�r�X�̊J�n�������s���B<p>
     *
     * @exception Exception �w�肳�ꂽ��O�������ł��Ȃ��ꍇ
     */
    public void startService() throws Exception{
        createThrowable();
    }
    
    private Throwable createThrowable() throws Exception{
        if(exception != null){
            return exception;
        }
        final String msg = createMessage();
        if(msg == null){
            return (Throwable)Class.forName(
                exceptionClassName,
                true,
                NimbusClassLoader.getInstance()
                ).newInstance();
        }else{
            try{
                return (Throwable)Class.forName(
                    exceptionClassName,
                    true,
                    NimbusClassLoader.getInstance()
                ).getConstructor(new Class[]{String.class})
                    .newInstance(new Object[]{msg});
            }catch(java.lang.reflect.InvocationTargetException e){
                final Throwable th = e.getTargetException();
                if(th instanceof Exception){
                    throw (Exception)th;
                }else{
                    throw (Error)th;
                }
            }
        }
    }
    
    private String createMessage(){
        if(message == null){
            if(messageRecordFactoryServiceName != null){
                messageRecordFactory
                     = (MessageRecordFactory)ServiceManagerFactory
                            .getServiceObject(messageRecordFactoryServiceName);
            }
            MessageRecordFactory factory = messageRecordFactory;
            if(factory == null){
                factory = getMessageRecordFactory();
            }
            if(factory == null){
                return message;
            }
            if(messageKey != null){
                if(messageArgs == null || messageArgs.length == 0){
                    if(messageLocale == null){
                        return factory.findMessage(messageKey);
                    }else{
                        return factory.findMessage(
                            messageLocale,
                            messageKey
                        );
                    }
                }else{
                    if(messageLocale == null){
                        return factory.findEmbedMessage(
                            messageKey,
                            messageArgs
                        );
                    }else{
                        return factory.findEmbedMessage(
                            messageLocale,
                            messageKey,
                            messageArgs
                        );
                    }
                }
            }
        }
        return message;
    }
    
    /**
     * �ݒ肳�ꂽ��O��throw����B<p>
     * �T�[�r�X���J�n����Ă��Ȃ��ꍇ�́A�ݒ肳�ꂽ��O��throw�����ɁA���̃C���^�[�Z�v�^���Ăяo���B
     *
     * @param context �Ăяo���̃R���e�L�X�g���
     * @param chain ���̃C���^�[�Z�v�^���Ăяo�����߂̃`�F�[��
     * @return �Ăяo�����ʂ̖߂�l
     * @exception Throwable �ݒ肳�ꂽ��O
     */
    public Object invoke(
        InvocationContext context,
        InterceptorChain chain
    ) throws Throwable{
        if(getState() == STARTED && isEnabled){
            throw createThrowable();
        }else{
            return chain.invokeNext(context);
        }
    }
}
