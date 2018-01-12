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

import java.lang.reflect.*;
import java.util.*;

import jp.ossc.nimbus.core.*;
import jp.ossc.nimbus.service.aop.*;
import jp.ossc.nimbus.service.context.*;
import jp.ossc.nimbus.service.journal.*;
import jp.ossc.nimbus.service.journal.editor.*;
import jp.ossc.nimbus.service.journal.editorfinder.*;

/**
 * ���\�b�h�W���[�i���C���^�[�Z�v�^�B<p>
 * ���\�b�h�Ăяo���̃W���[�i�����擾����C���^�[�Z�v�^�ł���B�W���[�i���̏o�͂́A�ʓr�W���[�i���T�[�r�X�̒�`���K�v�ł���B<br>
 * ���̃C���^�[�Z�v�^�ŏo�͂����W���[�i�����́A���\�b�h�Ăяo�����i{@link MethodCallJournalData}�j�A���\�b�h�߂�l���i{@link MethodReturnJournalData}�j�A���\�b�h��O���i{@link MethodThrowJournalData}�j�ł���B<br>
 * �ȉ��ɁA���\�b�h�̌Ăяo���W���[�i�����R���\�[���ɏo�͂���C���^�[�Z�v�^�̃T�[�r�X��`��������B<br>
 * <pre>
 * &lt;?xml version="1.0" encoding="Shift_JIS"?&gt;
 * 
 * &lt;server&gt;
 *     
 *     &lt;manager name="Sample"&gt;
 *         
 *         &lt;service name="MethodJournalInterceptor"
 *                  code="jp.ossc.nimbus.service.aop.interceptor.MethodJournalInterceptorService"&gt;
 *             &lt;attribute name="JournalServiceName"&gt;#Journal&lt;/attribute&gt;
 *             &lt;depends&gt;Journal&lt;/depends&gt;
 *         &lt;/service&gt;
 * &lt;!-- �ȉ��̓W���[�i���T�[�r�X��` --&gt;
 *         &lt;service name="Journal"
 *                  code="jp.ossc.nimbus.service.journal.ThreadManagedJournalService"&gt;
 *             &lt;attribute name="EditorFinderName"&gt;#JournalEditorFinder&lt;/attribute&gt;
 *             &lt;attribute name="WritableElementKey"&gt;Journal for Sample&lt;/attribute&gt;
 *             &lt;attribute name="CategoryServiceNames"&gt;#JournalCategory&lt;/attribute&gt;
 *             &lt;depends&gt;JournalEditorFinder&lt;/depends&gt;
 *             &lt;depends&gt;JournalCategory&lt;/depends&gt;
 *         &lt;/service&gt;
 *         
 *         &lt;service name="JournalCategory"
 *                  code="jp.ossc.nimbus.service.writer.SimpleCategoryService"&gt;
 *             &lt;attribute name="MessageWriterServiceName"&gt;#JournalWriter&lt;/attribute&gt;
 *             &lt;attribute name="WritableRecordFactoryServiceName"&gt;#JournalWritableRecordFactory&lt;/attribute&gt;
 *             &lt;attribute name="CategoryServiceNames"&gt;#JournalCategory&lt;/attribute&gt;
 *             &lt;depends&gt;JournalWriter&lt;/depends&gt;
 *             &lt;depends&gt;JournalWritableRecordFactory&lt;/depends&gt;
 *         &lt;/service&gt;
 *         
 *         &lt;service name="JournalWritableRecordFactory"
 *                  code="jp.ossc.nimbus.service.writer.WritableRecordFactoryService"&gt;
 *             &lt;attribute name="Format"&gt;%Journal for Sample%&lt;/attribute&gt;
 *         &lt;/service&gt;
 *         
 *         &lt;service name="JournalWriter"
 *                  code="jp.ossc.nimbus.service.writer.ConsoleWriterService"/&gt;
 *         
 *         &lt;service name="JournalEditorFinder"
 *                  code="jp.ossc.nimbus.service.journal.editorfinder.ObjectMappedEditorFinderService"&gt;
 *             &lt;attribute name="EditorProperties"&gt;
 *                 java.lang.Object=#ObjectJournalEditor
 *                 java.lang.Class=#ClassJournalEditor
 *                 java.util.Date=#DateJournalEditor
 *                 jp.ossc.nimbus.service.journal.RequestJournal=#RequestJournalEditor
 *                 jp.ossc.nimbus.service.journal.editor.MethodJournalData=#MethodJournalEditor
 *                 jp.ossc.nimbus.service.journal.editor.MethodCallJournalData=#MethodCallJournalEditor
 *                 jp.ossc.nimbus.service.journal.editor.MethodReturnJournalData=#MethodReturnJournalEditor
 *             &lt;/attribute&gt;
 *             &lt;depends&gt;ObjectJournalEditor&lt;/depends&gt;
 *             &lt;depends&gt;ClassJournalEditor&lt;/depends&gt;
 *             &lt;depends&gt;DateJournalEditor&lt;/depends&gt;
 *             &lt;depends&gt;RequestJournalEditor&lt;/depends&gt;
 *             &lt;depends&gt;MethodJournalEditor&lt;/depends&gt;
 *             &lt;depends&gt;MethodCallJournalEditor&lt;/depends&gt;
 *             &lt;depends&gt;MethodReturnJournalEditor&lt;/depends&gt;
 *         &lt;/service&gt;
 *         
 *         &lt;service name="ObjectJournalEditor"
 *                  code="jp.ossc.nimbus.service.journal.editor.ObjectJournalEditorService"/&gt;
 *         
 *         &lt;service name="ClassJournalEditor"
 *                  code="jp.ossc.nimbus.service.journal.editor.ClassJournalEditorService"&gt;
 *             &lt;attribute name="ShortClassName"&gt;true&lt;/attribute&gt;
 *         &lt;/service&gt;
 *         
 *         &lt;service name="DateJournalEditor"
 *                  code="jp.ossc.nimbus.service.journal.editor.DateJournalEditorService"&gt;
 *             &lt;attribute name="Format"&gt;yyyy/MM/dd HH:mm:ss.SSS&lt;/attribute&gt;
 *         &lt;/service&gt;
 *         
 *         &lt;service name="RequestJournalEditor"
 *                  code="jp.ossc.nimbus.service.journal.editor.SimpleRequestJournalEditorService"/&gt;
 *         
 *         &lt;service name="MethodJournalEditor"
 *                  code="jp.ossc.nimbus.service.journal.editor.MethodJournalEditorService"/&gt;
 *         
 *         &lt;service name="MethodCallJournalEditor"
 *                  code="jp.ossc.nimbus.service.journal.editor.MethodCallJournalEditorService"/&gt;
 *         
 *         &lt;service name="MethodReturnJournalEditor"
 *                  code="jp.ossc.nimbus.service.journal.editor.MethodReturnJournalEditorService"/&gt;
 *         
 *     &lt;/manager&gt;
 *     
 * &lt;/server&gt;
 * </pre>
 *
 * @author M.Takata
 * @see Journal
 * @see EditorFinder
 * @see Context
 */
public class MethodJournalInterceptorService extends ServiceBase
 implements Interceptor, MethodJournalInterceptorServiceMBean{
    
    private static final long serialVersionUID = 6121765320688713719L;
    
    private ServiceName threadContextName;
    private Context threadContext;
    
    private ServiceName journalName;
    private Journal journal;
    
    private ServiceName requestEditorFinderName;
    private ServiceName methodCallEditorFinderName;
    private ServiceName methodReturnEditorFinderName;
    private EditorFinder requestEditorFinder;
    private EditorFinder methodCallEditorFinder;
    private EditorFinder methodReturnEditorFinder;
    
    private String requestJournalKey = DEFAULT_REQUEST_JOURNAL_KEY;
    private String methodCallJournalKey = DEFAULT_METHOD_CALL_JOURNAL_KEY;
    private String methodReturnJournalKey = DEFAULT_METHOD_RETURN_JOURNAL_KEY;
    
    private String requestIdKey = ThreadContextKey.REQUEST_ID;
    private boolean isEnabled = true;
    private boolean isBushingCallBlock = false;
    private Map contextJournalMap;
    private Map invocationContextJournalMap;
    
    protected ThreadLocal callStack;
    
    // MethodJournalInterceptorServiceMBean��JavaDoc
    public void setRequestIdKey(String key){
        requestIdKey = key;
    }
    
    // MethodJournalInterceptorServiceMBean��JavaDoc
    public String getRequestIdKey(){
        return requestIdKey;
    }
    
    // MethodJournalInterceptorServiceMBean��JavaDoc
    public void setThreadContextServiceName(ServiceName name){
        threadContextName = name;
    }
    
    // MethodJournalInterceptorServiceMBean��JavaDoc
    public ServiceName getThreadContextServiceName(){
        return threadContextName;
    }
    
    // MethodJournalInterceptorServiceMBean��JavaDoc
    public void setJournalServiceName(ServiceName name){
        journalName = name;
    }
    
    // MethodJournalInterceptorServiceMBean��JavaDoc
    public ServiceName getJournalServiceName(){
        return journalName;
    }
    
    // MethodJournalInterceptorServiceMBean��JavaDoc
    public void setRequestEditorFinderServiceName(ServiceName name){
        requestEditorFinderName = name;
    }
    
    // MethodJournalInterceptorServiceMBean��JavaDoc
    public ServiceName getRequestEditorFinderServiceName(){
        return requestEditorFinderName;
    }
    
    // MethodJournalInterceptorServiceMBean��JavaDoc
    public void setMethodCallEditorFinderServiceName(ServiceName name){
        methodCallEditorFinderName = name;
    }
    
    // MethodJournalInterceptorServiceMBean��JavaDoc
    public ServiceName getMethodCallEditorFinderServiceName(){
        return methodCallEditorFinderName;
    }
    
    // MethodJournalInterceptorServiceMBean��JavaDoc
    public void setMethodReturnEditorFinderServiceName(ServiceName name){
        methodReturnEditorFinderName = name;
    }
    
    // MethodJournalInterceptorServiceMBean��JavaDoc
    public ServiceName getMethodReturnEditorFinderServiceName(){
        return methodReturnEditorFinderName;
    }
    
    // MethodJournalInterceptorServiceMBean��JavaDoc
    public void setRequestJournalKey(String key){
        requestJournalKey = key;
    }
    
    // MethodJournalInterceptorServiceMBean��JavaDoc
    public String getRequestJournalKey(){
        return requestJournalKey;
    }
    
    // MethodJournalInterceptorServiceMBean��JavaDoc
    public void setMethodCallJournalKey(String key){
        methodCallJournalKey = key;
    }
    
    // MethodJournalInterceptorServiceMBean��JavaDoc
    public String getMethodCallJournalKey(){
        return methodCallJournalKey;
    }
    
    // MethodJournalInterceptorServiceMBean��JavaDoc
    public void setMethodReturnJournalKey(String key){
        methodReturnJournalKey = key;
    }
    
    // MethodJournalInterceptorServiceMBean��JavaDoc
    public String getMethodReturnJournalKey(){
        return methodReturnJournalKey;
    }
    
    // MethodJournalInterceptorServiceMBean��JavaDoc
    public void setEnabled(boolean enable){
        isEnabled = enable;
    }
    
    // MethodJournalInterceptorServiceMBean��JavaDoc
    public boolean isEnabled(){
        return isEnabled;
    }
    
    // MethodJournalInterceptorServiceMBean��JavaDoc
    public void setBushingCallBlock(boolean isBlock){
        isBushingCallBlock = isBlock;
    }
    
    // MethodJournalInterceptorServiceMBean��JavaDoc
    public boolean isBushingCallBlock(){
        return isBushingCallBlock;
    }
    
    // MethodJournalInterceptorServiceMBean��JavaDoc
    public void setContextJournalMapping(String contextKey, String journalKey){
        contextJournalMap.put(contextKey, journalKey);
    }
    
    // MethodJournalInterceptorServiceMBean��JavaDoc
    public String getContextJournalMapping(String contextKey){
        return (String)contextJournalMap.get(contextKey);
    }
    
    // MethodJournalInterceptorServiceMBean��JavaDoc
    public Map getContextJournalMap(){
        return contextJournalMap;
    }
    
    // MethodJournalInterceptorServiceMBean��JavaDoc
    public void setInvocationContextJournalMapping(String attributeName, String journalKey){
        invocationContextJournalMap.put(attributeName, journalKey);
    }
    
    // MethodJournalInterceptorServiceMBean��JavaDoc
    public String getInvocationContextJournalMapping(String attributeName){
        return (String)invocationContextJournalMap.get(attributeName);
    }
    
    // MethodJournalInterceptorServiceMBean��JavaDoc
    public Map getInvocationContextJournalMap(){
        return invocationContextJournalMap;
    }
    
    /**
     * �W���[�i�����o�͂���{@link jp.ossc.nimbus.service.journal.Journal Journal}��ݒ肷��B<p>
     *
     * @param journal Journal
     */
    public void setJournal(Journal journal) {
        this.journal = journal;
    }
    
    /**
     * ���\�b�h�Ăяo���̃W���[�i���ҏW�Ɏg�p����{@link jp.ossc.nimbus.service.journal.editorfinder.EditorFinder EditorFinder}��ݒ肷��B<p>
     *
     * @param editorFinder EditorFinder
     */
    public void setMethodCallEditorFinder(EditorFinder editorFinder) {
        methodCallEditorFinder = editorFinder;
    }
    
    /**
     * ���\�b�h�߂�̃W���[�i���ҏW�Ɏg�p����{@link jp.ossc.nimbus.service.journal.editorfinder.EditorFinder EditorFinder}��ݒ肷��B<p>
     *
     * @param editorFinder EditorFinder
     */
    public void setMethodReturnEditorFinder(EditorFinder editorFinder) {
        methodReturnEditorFinder = editorFinder;
    }
    
    /**
     * �W���[�i���J�n�̃W���[�i���ҏW�Ɏg�p����{@link jp.ossc.nimbus.service.journal.editorfinder.EditorFinder EditorFinder}��ݒ肷��B<p>
     *
     * @param editorFinder EditorFinder
     */
    public void setRequestEditorFinder(EditorFinder editorFinder) {
        requestEditorFinder = editorFinder;
    }
    
    /**
     * ���N�G�X�gID���擾����{@link jp.ossc.nimbus.service.context.Context}��ݒ肷��B<p>
     *
     * @param context Context
     */
    public void setThreadContext(Context context) {
        threadContext = context;
    }
    
    /**
     * �T�[�r�X�̐����������s���B<p>
     *
     * @exception Exception ���������Ɏ��s�����ꍇ
     */
    public void createService() throws Exception{
        contextJournalMap = new HashMap();
        invocationContextJournalMap = new HashMap();
    }
    
    /**
     * �T�[�r�X�̊J�n�������s���B<p>
     *
     * @exception Exception �w�肳�ꂽ{@link Journal}�A�y��{@link EditorFinder}�A{@link Context}�T�[�r�X��������Ȃ��ꍇ
     */
    public void startService() throws Exception{
        if(journalName != null){
            journal = (Journal)ServiceManagerFactory.getServiceObject(journalName);
        }
        if(requestEditorFinderName != null){
            requestEditorFinder = (EditorFinder)ServiceManagerFactory
                .getServiceObject(requestEditorFinderName);
        }
        if(methodCallEditorFinderName != null){
            methodCallEditorFinder = (EditorFinder)ServiceManagerFactory
                .getServiceObject(methodCallEditorFinderName);
        }
        if(methodReturnEditorFinderName != null){
            methodReturnEditorFinder = (EditorFinder)ServiceManagerFactory
                .getServiceObject(methodReturnEditorFinderName);
        }
        if(threadContextName != null){
            threadContext = (Context)ServiceManagerFactory
                .getServiceObject(threadContextName);
        }
        if(isBushingCallBlock){
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
     * @exception Exception ��~�����Ɏ��s�����ꍇ
     */
    public void stopService() throws Exception{
        callStack = null;
    }
    
    /**
     * �T�[�r�X�̔j���������s���B<p>
     *
     * @exception Exception �j�������Ɏ��s�����ꍇ
     */
    public void destroyService(){
        journal = null;
        requestEditorFinder = null;
        methodCallEditorFinder = null;
        methodReturnEditorFinder = null;
        contextJournalMap = null;
        invocationContextJournalMap = null;
    }
    
    /**
     * ���\�b�h�Ăяo���J�n�̃W���[�i�����o�͂��āA���̃C���^�[�Z�v�^���Ăяo���A�߂��Ă����Ƃ���ŁA���\�b�h�Ăяo���I���̃W���[�i�����o�͂���B<p>
     * �T�[�r�X���J�n����Ă��Ȃ��ꍇ�́A�W���[�i���o�͂��s�킸�Ɏ��̃C���^�[�Z�v�^���Ăяo���B<br>
     *
     * @param context �Ăяo���̃R���e�L�X�g���
     * @param chain ���̃C���^�[�Z�v�^���Ăяo�����߂̃`�F�[��
     * @return �Ăяo�����ʂ̖߂�l
     * @exception Throwable �Ăяo����ŗ�O�����������ꍇ�A�܂��͂��̃C���^�[�Z�v�^�ŔC�ӂ̗�O�����������ꍇ�B�A���A�{���Ăяo����鏈����throw���Ȃ�RuntimeException�ȊO�̗�O��throw���Ă��A�Ăяo�����ɂ͓`�d����Ȃ��B
     */
    public Object invoke(
        InvocationContext context,
        InterceptorChain chain
    ) throws Throwable{
        final MethodInvocationContext ctx = (MethodInvocationContext)context;
        
        if(getState() == STARTED && isEnabled()
            && (callStack == null
                 || ((CallStack)callStack.get()).stackIndex == 0)){
            Object ret = null;
            try{
                preNext(ctx);
                if(callStack != null){
                    ((CallStack)callStack.get()).stackIndex++;
                }
                ret = chain.invokeNext(ctx);
                postNext(ctx, ret);
            }catch(RuntimeException e){
                throw throwRuntimeException(ctx, e);
            }catch(Exception e){
                throw throwException(ctx, e);
            }catch(Error e){
                throw throwError(ctx, e);
            }finally{
                if(callStack != null){
                    ((CallStack)callStack.get()).stackIndex--;
                }
                finallyNext(ctx, ret);
            }
            return ret;
        }else{
            return chain.invokeNext(ctx);
        }
    }
    
    /**
     * ���̃C���^�[�Z�v�^���Ăяo���O�������s���B<p>
     * �W���[�i�����R�[�h���J�n����B�܂��A{@link #setThreadContextServiceName(ServiceName)}�ŁA{@link Context}�T�[�r�X���ݒ肳��Ă���ꍇ�́A{@link #setRequestIdKey(String)}�Őݒ肳�ꂽ�L�[��Context�T�[�r�X���烊�N�G�X�gID���擾���āA�W���[�i�����R�[�h�ɐݒ�i{@link Journal#setRequestId(String)}�j����B<br>
     * {@link #setMethodCallJournalKey(String)}�Őݒ肳�ꂽ�L�[�ŁA{@link MethodCallJournalData}���W���[�i���ɏo�́i{@link Journal#addInfo(String, Object)}�j����B<br>
     *
     * @param context �Ăяo���̃R���e�L�X�g���
     * @exception Throwable �O�����Ɏ��s�����ꍇ
     */
    protected void preNext(MethodInvocationContext context) throws Throwable{
        if(journal == null){
            return;
        }
        journal.startJournal(requestJournalKey, requestEditorFinder);
        if(threadContext != null && requestIdKey != null){
            journal.setRequestId((String)threadContext.get(requestIdKey));
        }
        if(threadContext != null && contextJournalMap.size() != 0){
            Iterator entries = contextJournalMap.entrySet().iterator();
            while(entries.hasNext()){
                Map.Entry entry = (Map.Entry)entries.next();
                journal.addInfo((String)entry.getValue(), threadContext.get((String)entry.getKey()));
            }
        }
        if(invocationContextJournalMap.size() != 0){
            Iterator entries = invocationContextJournalMap.entrySet().iterator();
            while(entries.hasNext()){
                Map.Entry entry = (Map.Entry)entries.next();
                journal.addInfo((String)entry.getValue(), context.getAttribute((String)entry.getKey()));
            }
        }
        
        final Method method = context.getTargetMethod();
        final MethodCallJournalData data = new MethodCallJournalData(
            context.getTargetObject(),
            method.getDeclaringClass(),
            method.getName(),
            method.getParameterTypes(),
            context.getParameters()
        );
        journal.addInfo(methodCallJournalKey, data, methodCallEditorFinder);
    }
    
    /**
     * ���̃C���^�[�Z�v�^���Ăяo�����㏈�����s���B<p>
     * {@link #setMethodReturnJournalKey(String)}�Őݒ肳�ꂽ�L�[�ŁA{@link MethodReturnJournalData}���W���[�i���ɏo�́i{@link Journal#addInfo(String, Object)}�j����B<br>
     *
     * @param context �Ăяo���̃R���e�L�X�g���
     * @param ret �Ăяo���̖߂�l
     * @exception Throwable �㏈���Ɏ��s�����ꍇ
     */
    protected void postNext(MethodInvocationContext context, Object ret)
     throws Throwable{
        if(journal == null){
            return;
        }
        
        final Method method = context.getTargetMethod();
        final MethodReturnJournalData data = new MethodReturnJournalData(
            context.getTargetObject(),
            method.getDeclaringClass(),
            method.getName(),
            method.getParameterTypes(),
            ret
        );
        journal.addInfo(methodReturnJournalKey, data, methodReturnEditorFinder);
    }
    
    /**
     * ���̃C���^�[�Z�v�^���Ăяo��������RuntimeException�����������ꍇ�̌㏈�����s���B<p>
     * {@link #setMethodReturnJournalKey(String)}�Őݒ肳�ꂽ�L�[�ŁA{@link MethodThrowJournalData}���W���[�i���ɏo�́i{@link Journal#addInfo(String, Object)}�j����B<br>
     *
     * @param context �Ăяo���̃R���e�L�X�g���
     * @param e �Ăяo�����ɔ�������RuntimeException
     * @return �����Ŏw�肳�ꂽRuntimeException
     * @exception Throwable �㏈���Ɏ��s�����ꍇ
     */
    protected RuntimeException throwRuntimeException(
        MethodInvocationContext context,
        RuntimeException e
    ) throws Throwable{
        if(journal == null){
            return e;
        }
        final Method method = context.getTargetMethod();
        final MethodThrowJournalData data = new MethodThrowJournalData(
            context.getTargetObject(),
            method.getDeclaringClass(),
            method.getName(),
            method.getParameterTypes(),
            e
        );
        journal.addInfo(methodReturnJournalKey, data, methodReturnEditorFinder);
        return e;
    }
    
    /**
     * ���̃C���^�[�Z�v�^���Ăяo��������RuntimeException�ȊO��Exception�����������ꍇ�̌㏈�����s���B<p>
     * {@link #setMethodReturnJournalKey(String)}�Őݒ肳�ꂽ�L�[�ŁA{@link MethodThrowJournalData}���W���[�i���ɏo�́i{@link Journal#addInfo(String, Object)}�j����B<br>
     *
     * @param context �Ăяo���̃R���e�L�X�g���
     * @param e �Ăяo�����ɔ�������Exception
     * @return �����Ŏw�肳�ꂽException
     * @exception Throwable �㏈���Ɏ��s�����ꍇ
     */
    protected Exception throwException(
        MethodInvocationContext context,
        Exception e
    ) throws Throwable{
        if(journal == null){
            return e;
        }
        final Method method = context.getTargetMethod();
        final MethodThrowJournalData data = new MethodThrowJournalData(
            context.getTargetObject(),
            method.getDeclaringClass(),
            method.getName(),
            method.getParameterTypes(),
            e
        );
        journal.addInfo(methodReturnJournalKey, data, methodReturnEditorFinder);
        return e;
    }
    
    /**
     * ���̃C���^�[�Z�v�^���Ăяo��������Error�����������ꍇ�̌㏈�����s���B<p>
     * {@link #setMethodReturnJournalKey(String)}�Őݒ肳�ꂽ�L�[�ŁA{@link MethodThrowJournalData}���W���[�i���ɏo�́i{@link Journal#addInfo(String, Object)}�j����B<br>
     *
     * @param context �Ăяo���̃R���e�L�X�g���
     * @param error �Ăяo�����ɔ�������Error
     * @return �����Ŏw�肳�ꂽError
     * @exception Throwable �㏈���Ɏ��s�����ꍇ
     */
    protected Error throwError(
        MethodInvocationContext context,
        Error error
    ) throws Throwable{
        if(journal == null){
            return error;
        }
        final Method method = context.getTargetMethod();
        final MethodThrowJournalData data = new MethodThrowJournalData(
            context.getTargetObject(),
            method.getDeclaringClass(),
            method.getName(),
            method.getParameterTypes(),
            error
        );
        journal.addInfo(methodReturnJournalKey, data, methodReturnEditorFinder);
        return error;
    }
    
    /**
     * ���̃C���^�[�Z�v�^���Ăяo�������finally�߂ł̏������s���B<p>
     * �W���[�i�����R�[�h���I������B<br>
     *
     * @param context �Ăяo���̃R���e�L�X�g���
     * @param ret �Ăяo���̖߂�l
     * @exception Throwable �㏈���Ɏ��s�����ꍇ
     */
    protected void finallyNext(MethodInvocationContext context, Object ret)
     throws Throwable{
        if(journal == null){
            return;
        }
        
        journal.endJournal();
    }
    
    protected static class CallStack{
        public int stackIndex;
    }
}