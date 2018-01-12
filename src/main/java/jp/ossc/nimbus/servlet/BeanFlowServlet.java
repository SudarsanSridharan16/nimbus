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
package jp.ossc.nimbus.servlet;

import java.io.*;

import javax.servlet.*;
import javax.servlet.http.*;

import jp.ossc.nimbus.beans.ServiceNameEditor;
import jp.ossc.nimbus.core.ServiceManagerFactory;
import jp.ossc.nimbus.core.ServiceName;
import jp.ossc.nimbus.service.beancontrol.interfaces.BeanFlowInvokerFactory;
import jp.ossc.nimbus.service.beancontrol.interfaces.BeanFlowInvoker;
import jp.ossc.nimbus.service.aop.interceptor.servlet.StreamExchangeInterceptorServiceMBean;
import jp.ossc.nimbus.service.journal.Journal;
import jp.ossc.nimbus.service.journal.editorfinder.EditorFinder;
import jp.ossc.nimbus.service.context.Context;
import jp.ossc.nimbus.service.aop.interceptor.ThreadContextKey;

/**
 * BeanFlow�����s����T�[�u���b�g�B<p>
 * GET�y��POST��HTTP���N�G�X�g���󂯕t���āA���N�G�X�g�p�X�ɉ������A�N�V����BeanFlow���Ăяo���B<br>
 * �܂��A���N�G�X�g�̌��؂��s������BeanFlow���p�ӂ��Ă����΁A���O�ɂ���BeanFlow���Ăяo���A���؃G���[�̏ꍇ�́A�A�N�V����BeanFlow�͌Ăяo���Ȃ��B<br>
 * <p>
 * ���N�G�X�g�ƃ��X�|���X�̕ϊ����s���C���^�[�Z�v�^�Ƒg�ݍ��킹�鎖�ŁA�C���^�[�Z�v�^���ϊ����āA���N�G�X�g�̑����ɐݒ肵�����̓I�u�W�F�N�g��{@link BeanFlowServletContext}�ɐݒ肵��BeanFlow�ւƓn�������ł���B<br>
 * �܂��ABeanFlow��{@link BeanFlowServletContext#setOutput(Object)}���Ăяo���A�o�̓I�u�W�F�N�g��ݒ肵�ĕԂ��ƁA�o�̓I�u�W�F�N�g�����N�G�X�g�����ɐݒ肵�A�ϊ��C���^�[�Z�v�^�ɓn���B<br>
 * <p>
 * �ȉ��ɁA�T�[�u���b�g��web.xml��`��������B<br>
 * <pre>
 * &lt;servlet&gt;
 *     &lt;servlet-name&gt;BeanFlowServlet&lt;/servlet-name&gt;
 *     &lt;servlet-class&gt;jp.ossc.nimbus.servlet.BeanFlowServlet&lt;/servlet-class&gt;
 *     &lt;init-param&gt;
 *         &lt;param-name&gt;Validate&lt;/param-name&gt;
 *         &lt;param-value&gt;true&lt;/param-value&gt;
 *     &lt;/init-param&gt;
 * &lt;/servlet&gt;
 * 
 * &lt;servlet-mapping&gt;
 *     &lt;servlet-name&gt;BeanFlowServlet&lt;/servlet-name&gt;
 *     &lt;url-pattern&gt;*.bf&lt;/url-pattern&gt;
 * &lt;/servlet-mapping&gt;
 * </pre>
 *
 * @author M.Takata
 */
public class BeanFlowServlet extends HttpServlet{
    
    private static final long serialVersionUID = -5548272719656324613L;
    
    /**
     * BeanFlowSelector�T�[�r�X���̏������p�����[�^���B<p>
     */
    public static final String INIT_PARAM_NAME_BEAN_FLOW_SELECTOR_SERVICE_NAME = "BeanFlowSelectorServiceName";
    
    /**
     * BeanFlowInvokerFactory�T�[�r�X���̏������p�����[�^���B<p>
     */
    public static final String INIT_PARAM_NAME_BEAN_FLOW_INVOKER_FACTORY_SERVICE_NAME = "BeanFlowInvokerFactoryServiceName";
    
    /**
     * Journal�T�[�r�X���̏������p�����[�^���B<p>
     */
    public static final String INIT_PARAM_NAME_JOURNAL_SERVICE_NAME = "JournalServiceName";
    
    /**
     * �W���[�i���J�n����EditorFinder�T�[�r�X���̏������p�����[�^���B<p>
     */
    public static final String INIT_PARAM_NAME_EDITOR_FINDER_SERVICE_NAME = "EditorFinderServiceName";
    
    /**
     * ����BeanFlow�̃W���[�i���J�n����EditorFinder�T�[�r�X���̏������p�����[�^���B<p>
     */
    public static final String INIT_PARAM_NAME_VALIDATE_EDITOR_FINDER_SERVICE_NAME = "ValidateEditorFinderServiceName";
    
    /**
     * �A�N�V����BeanFlow�̃W���[�i���J�n����EditorFinder�T�[�r�X���̏������p�����[�^���B<p>
     */
    public static final String INIT_PARAM_NAME_ACTION_EDITOR_FINDER_SERVICE_NAME = "ActionEditorFinderServiceName";
    
    /**
     * Context�T�[�r�X���̏������p�����[�^���B<p>
     */
    public static final String INIT_PARAM_NAME_CONTEXT_SERVICE_NAME = "ContextServiceName";
    
    /**
     * ����BeanFlow���s�t���O�̏������p�����[�^���B<p>
     */
    public static final String INIT_PARAM_NAME_VALIDATE = "Validate";
    
    /**
     * ����BeanFlow�̑O�u���̏������p�����[�^���B<p>
     */
    public static final String INIT_PARAM_NAME_VALIDATE_FLOW_PREFIX = "ValidateFlowPrefix";
    
    /**
     * ���̓I�u�W�F�N�g�̃��N�G�X�g�������̏������p�����[�^���B<p>
     */
    public static final String INIT_PARAM_NAME_INPUT_ATTRIBUTE_NAME = "InputAttributeName";
    
    /**
     * �o�̓I�u�W�F�N�g�̃��N�G�X�g�������̏������p�����[�^���B<p>
     */
    public static final String INIT_PARAM_NAME_OUTPUT_ATTRIBUTE_NAME = "OutputAttributeName";
    
    /**
     * ����BeanFlow�̑O�u���̃f�t�H���g�l�B<p>
     */
    public static final String DEFAULT_VALIDATE_FLOW_PREFIX = "validate";
    
    /**
     * �W���[�i���J�n���̃W���[�i���L�[�B<p>
     */
    public static final String JOURNAL_KEY_PROCESS = "Process";
    
    /**
     * �t���[���̃W���[�i���L�[�B<p>
     */
    public static final String JOURNAL_KEY_FLOW_NAME = "FlowName";
    
    /**
     * ����BeanFlow�̃W���[�i���J�n���̃W���[�i���L�[�B<p>
     */
    public static final String JOURNAL_KEY_VALIDATE = "Validate";
    
    /**
     * �A�N�V����BeanFlow�̃W���[�i���J�n���̃W���[�i���L�[�B<p>
     */
    public static final String JOURNAL_KEY_ACTION = "Action";
    
    /**
     * ���͂̃W���[�i���L�[�B<p>
     */
    public static final String JOURNAL_KEY_INPUT = "Input";
    
    /**
     * �o�͂̃W���[�i���L�[�B<p>
     */
    public static final String JOURNAL_KEY_OUTPUT = "Output";
    
    /**
     * ��O�������̃W���[�i���L�[�B<p>
     */
    public static final String JOURNAL_KEY_EXCEPTION = "Exception";
    
    /**
     * {@link BeanFlowInvokerFactory}�T�[�r�X�̃T�[�r�X���B<p>
     */
    protected ServiceName beanFlowInvokerFactoryServiceName;
    
    /**
     * {@link BeanFlowSelector}�T�[�r�X�̃T�[�r�X���B<p>
     */
    protected ServiceName beanFlowSelectorServiceName;
    
    /**
     * {@link Journal}�T�[�r�X�̃T�[�r�X���B<p>
     */
    protected ServiceName journalServiceName;
    
    /**
     * {@link EditorFinder}�T�[�r�X�̃T�[�r�X���B<p>
     */
    protected ServiceName editorFinderServiceName;
    
    /**
     * ����BeanFlow�̃W���[�i���J�n����{@link EditorFinder}�T�[�r�X�̃T�[�r�X���B<p>
     */
    protected ServiceName validateEditorFinderServiceName;
    
    /**
     * �A�N�V����BeanFlow�̃W���[�i���J�n����{@link EditorFinder}�T�[�r�X�̃T�[�r�X���B<p>
     */
    protected ServiceName actionEditorFinderServiceName;
    
    /**
     * {@link Context}�T�[�r�X�̃T�[�r�X���B<p>
     */
    protected ServiceName contextServiceName;
    
    /**
     * �f�t�H���g{@link BeanFlowSelector}�B<p>
     */
    protected DefaultBeanFlowSelectorService defaultBeanFlowSelector;
    
    /**
     * ����BeanFlow���s�t���O�B<p>
     * �f�t�H���g�́Afalse�Ō���BeanFlow�͌Ăяo���Ȃ��B<br>
     */
    protected boolean isValidate;
    
    /**
     * ����BeanFlow�̑O�u���B<p>
     * �f�t�H���g�́A{@link #DEFAULT_VALIDATE_FLOW_PREFIX}�B<br>
     */
    protected String validateFlowPrefix = DEFAULT_VALIDATE_FLOW_PREFIX;
    
    /**
     * ���̓I�u�W�F�N�g�̃��N�G�X�g�������B<p>
     * �f�t�H���g�́A{@link StreamExchangeInterceptorServiceMBean#DEFAULT_REQUEST_OBJECT_ATTRIBUTE_NAME}�B<br>
     */
    protected String inputAttributeName = StreamExchangeInterceptorServiceMBean.DEFAULT_REQUEST_OBJECT_ATTRIBUTE_NAME;
    
    /**
     * �o�̓I�u�W�F�N�g�̃��N�G�X�g�������B<p>
     * �f�t�H���g�́A{@link StreamExchangeInterceptorServiceMBean#DEFAULT_RESPONSE_OBJECT_ATTRIBUTE_NAME}�B<br>
     */
    protected String outputAttributeName = StreamExchangeInterceptorServiceMBean.DEFAULT_RESPONSE_OBJECT_ATTRIBUTE_NAME;
    
    /**
     * �T�[�u���b�g�̏��������s���B<p>
     *
     * @exception ServletException �T�[�u���b�g�̏������Ɏ��s�����ꍇ
     */
    public void init() throws ServletException{
        beanFlowInvokerFactoryServiceName
            = getBeanFlowInvokerFactoryServiceName();
        if(beanFlowInvokerFactoryServiceName == null){
            throw new ServletException("BeanFlowInvokerFactoryServiceName is null.");
        }
        beanFlowSelectorServiceName
            = getBeanFlowSelectorServiceName();
        if(beanFlowSelectorServiceName == null){
            defaultBeanFlowSelector = new DefaultBeanFlowSelectorService();
            try{
                defaultBeanFlowSelector.create();
                defaultBeanFlowSelector.start();
            }catch(Exception e){
                throw new ServletException(e);
            }
        }
        journalServiceName = getJournalServiceName();
        editorFinderServiceName = getEditorFinderServiceName();
        validateEditorFinderServiceName = getValidateEditorFinderServiceName();
        actionEditorFinderServiceName = getActionEditorFinderServiceName();
        contextServiceName = getContextServiceName();
        isValidate = isValidate();
        final String prefix = getValidateFlowPrefix();
        if(prefix != null && prefix.length() != 0){
            validateFlowPrefix = prefix;
        }
        final String inputName = getInputAttributeName();
        if(inputName != null){
            inputAttributeName = inputName;
        }
        final String outputName = getOutputAttributeName();
        if(outputName != null){
            outputAttributeName = outputName;
        }
    }
    
    /**
     * �������p�����[�^{@link #INIT_PARAM_NAME_BEAN_FLOW_SELECTOR_SERVICE_NAME}�Ŏw�肳�ꂽ{@link BeanFlowInvokerFactory}�T�[�r�X�̃T�[�r�X�����擾����B<p>
     *
     * @return BeanFlowInvokerFactory�T�[�r�X�̃T�[�r�X��
     */
    protected ServiceName getBeanFlowSelectorServiceName(){
        return getServiceName(INIT_PARAM_NAME_BEAN_FLOW_SELECTOR_SERVICE_NAME);
    }
    
    /**
     * �������p�����[�^{@link #INIT_PARAM_NAME_BEAN_FLOW_INVOKER_FACTORY_SERVICE_NAME}�Ŏw�肳�ꂽ{@link BeanFlowInvokerFactory}�T�[�r�X�̃T�[�r�X�����擾����B<p>
     *
     * @return BeanFlowInvokerFactory�T�[�r�X�̃T�[�r�X��
     */
    protected ServiceName getBeanFlowInvokerFactoryServiceName(){
        return getServiceName(INIT_PARAM_NAME_BEAN_FLOW_INVOKER_FACTORY_SERVICE_NAME);
    }
    
    /**
     * �������p�����[�^{@link #INIT_PARAM_NAME_JOURNAL_SERVICE_NAME}�Ŏw�肳�ꂽ{@link Journal}�T�[�r�X�̃T�[�r�X�����擾����B<p>
     *
     * @return Journal�T�[�r�X�̃T�[�r�X��
     */
    protected ServiceName getJournalServiceName(){
        return getServiceName(INIT_PARAM_NAME_JOURNAL_SERVICE_NAME);
    }
    
    /**
     * �������p�����[�^{@link #INIT_PARAM_NAME_EDITOR_FINDER_SERVICE_NAME}�Ŏw�肳�ꂽ{@link EditorFinder}�T�[�r�X�̃T�[�r�X�����擾����B<p>
     *
     * @return EditorFinder�T�[�r�X�̃T�[�r�X��
     */
    protected ServiceName getEditorFinderServiceName(){
        return getServiceName(INIT_PARAM_NAME_EDITOR_FINDER_SERVICE_NAME);
    }
    
    /**
     * �������p�����[�^{@link #INIT_PARAM_NAME_VALIDATE_EDITOR_FINDER_SERVICE_NAME}�Ŏw�肳�ꂽ{@link EditorFinder}�T�[�r�X�̃T�[�r�X�����擾����B<p>
     *
     * @return EditorFinder�T�[�r�X�̃T�[�r�X��
     */
    protected ServiceName getValidateEditorFinderServiceName(){
        return getServiceName(INIT_PARAM_NAME_VALIDATE_EDITOR_FINDER_SERVICE_NAME);
    }
    
    /**
     * �������p�����[�^{@link #INIT_PARAM_NAME_ACTION_EDITOR_FINDER_SERVICE_NAME}�Ŏw�肳�ꂽ{@link EditorFinder}�T�[�r�X�̃T�[�r�X�����擾����B<p>
     *
     * @return EditorFinder�T�[�r�X�̃T�[�r�X��
     */
    protected ServiceName getActionEditorFinderServiceName(){
        return getServiceName(INIT_PARAM_NAME_ACTION_EDITOR_FINDER_SERVICE_NAME);
    }
    
    /**
     * �������p�����[�^{@link #INIT_PARAM_NAME_CONTEXT_SERVICE_NAME}�Ŏw�肳�ꂽ{@link Context}�T�[�r�X�̃T�[�r�X�����擾����B<p>
     *
     * @return Context�T�[�r�X�̃T�[�r�X��
     */
    protected ServiceName getContextServiceName(){
        return getServiceName(INIT_PARAM_NAME_CONTEXT_SERVICE_NAME);
    }
    
    protected ServiceName getServiceName(String paramName){
        final ServletConfig config = getServletConfig();
        final String serviceNameStr = config.getInitParameter(paramName);
        if(serviceNameStr == null){
            return null;
        }
        final ServiceNameEditor editor = new ServiceNameEditor();
        editor.setAsText(serviceNameStr);
        return (ServiceName)editor.getValue();
    }
    
    /**
     * �������p�����[�^{@link #INIT_PARAM_NAME_VALIDATE}�Ŏw�肳�ꂽ���؃t���[�g�p�t���O���擾����B<p>
     *
     * @return ���؃t���[�g�p�t���O�Btrue�̏ꍇ�A���؃t���[���g�p����B
     */
    protected boolean isValidate(){
        final ServletConfig config = getServletConfig();
        final String isValidateStr = config.getInitParameter(INIT_PARAM_NAME_VALIDATE);
        return isValidateStr == null ? false : Boolean.valueOf(isValidateStr).booleanValue();
    }
    
    /**
     * �������p�����[�^{@link #INIT_PARAM_NAME_VALIDATE_FLOW_PREFIX}�Ŏw�肳�ꂽ����BeanFlow�O�u�����擾����B<p>
     * ���N�G�X�g�p�X�̑O�ɁA���̑O�u����t�����t���[��������BeanFlow�̃t���[���Ƃ���B<br>
     *
     * @return ����BeanFlow�O�u��
     */
    protected String getValidateFlowPrefix(){
        final ServletConfig config = getServletConfig();
        return config.getInitParameter(INIT_PARAM_NAME_VALIDATE_FLOW_PREFIX);
    }
    
    /**
     * �������p�����[�^{@link #INIT_PARAM_NAME_INPUT_ATTRIBUTE_NAME}�Ŏw�肳�ꂽ���̓I�u�W�F�N�g�̃��N�G�X�g���������擾����B<p>
     * ���̑������ŁAHTTP���N�G�X�g������̓I�u�W�F�N�g���擾���āA{@link BeanFlowServletContext}�ɐݒ肷��B<br>
     *
     * @return ���̓I�u�W�F�N�g�̃��N�G�X�g������
     */
    protected String getInputAttributeName(){
        final ServletConfig config = getServletConfig();
        return config.getInitParameter(INIT_PARAM_NAME_INPUT_ATTRIBUTE_NAME);
    }
    
    /**
     * �������p�����[�^{@link #INIT_PARAM_NAME_OUTPUT_ATTRIBUTE_NAME}�Ŏw�肳�ꂽ�o�̓I�u�W�F�N�g�̃��N�G�X�g���������擾����B<p>
     * {@link BeanFlowServletContext#getOutput()}�Ŏ擾�����o�̓I�u�W�F�N�g���A���̑������ŁAHTTP���N�G�X�g�ݒ肷��B<br>
     *
     * @return �o�̓I�u�W�F�N�g�̃��N�G�X�g������
     */
    protected String getOutputAttributeName(){
        final ServletConfig config = getServletConfig();
        return config.getInitParameter(INIT_PARAM_NAME_OUTPUT_ATTRIBUTE_NAME);
    }
    
    /**
     * GET���\�b�h�Ăяo������������B<p>
     * {@link #doService(HttpServletRequest, HttpServletResponse)}���Ăяo���B<br>
     * 
     * @param req HTTP���N�G�X�g
     * @param resp HTTP���X�|���X
     * @exception ServletException
     * @exception IOException
     */
    protected void doGet(
        HttpServletRequest req,
        HttpServletResponse resp
    ) throws ServletException, IOException{
        doService(req, resp);
    }
    
    /**
     * POST���\�b�h�Ăяo������������B<p>
     * {@link #doService(HttpServletRequest, HttpServletResponse)}���Ăяo���B<br>
     * 
     * @param req HTTP���N�G�X�g
     * @param resp HTTP���X�|���X
     * @exception ServletException
     * @exception IOException
     */
    protected void doPost(
        HttpServletRequest req,
        HttpServletResponse resp
    ) throws ServletException, IOException{
        doService(req, resp);
    }
    
    /**
     * ����BeanFlow�y�уA�N�V����BeanFlow�̌Ăяo���𐧌䂷��B<p>
     * 
     * @param req HTTP���N�G�X�g
     * @param resp HTTP���X�|���X
     * @exception ServletException
     * @exception IOException
     */
    protected void doService(
        HttpServletRequest req,
        HttpServletResponse resp
    ) throws ServletException, IOException{
        
        String flowName = processSelectBeanFlow(req, resp);
        
        if(flowName == null || flowName.length() == 0){
            handleNotFound(req, resp, flowName);
            return;
        }
        final BeanFlowInvokerFactory beanFlowInvokerFactory
            = (BeanFlowInvokerFactory)ServiceManagerFactory
                .getServiceObject(beanFlowInvokerFactoryServiceName);
        if(!beanFlowInvokerFactory.containsFlow(flowName)){
            handleNotFound(req, resp, flowName);
            return;
        }
        Journal journal = null;
        EditorFinder editorFinder = null;
        EditorFinder validateEditorFinder = null;
        EditorFinder actionEditorFinder = null;
        String requestId = null;
        if(journalServiceName != null){
            journal = (Journal)ServiceManagerFactory
                .getServiceObject(journalServiceName);
            if(editorFinderServiceName != null){
                editorFinder = (EditorFinder)ServiceManagerFactory
                    .getServiceObject(editorFinderServiceName);
            }
            if(validateEditorFinderServiceName != null){
                validateEditorFinder = (EditorFinder)ServiceManagerFactory
                    .getServiceObject(validateEditorFinderServiceName);
            }
            if(actionEditorFinderServiceName != null){
                actionEditorFinder = (EditorFinder)ServiceManagerFactory
                    .getServiceObject(actionEditorFinderServiceName);
            }
            if(contextServiceName != null){
                Context context = (Context)ServiceManagerFactory
                    .getServiceObject(contextServiceName);
                requestId = (String)context.get(ThreadContextKey.REQUEST_ID);
            }
        }
        try{
            if(journal != null){
                journal.startJournal(JOURNAL_KEY_PROCESS, editorFinder);
                if(requestId != null){
                    journal.setRequestId(requestId);
                }
            }
            final BeanFlowServletContext context = new BeanFlowServletContext(
                req,
                resp,
                req.getAttribute(inputAttributeName)
            );
            if(validateFlowPrefix != null && isValidate){
                final String validateFlowName = validateFlowPrefix + flowName;
                if(beanFlowInvokerFactory.containsFlow(validateFlowName)){
                    final BeanFlowInvoker validateFlow
                        = beanFlowInvokerFactory.createFlow(validateFlowName);
                    try{
                        if(journal != null){
                            journal.addStartStep(JOURNAL_KEY_VALIDATE, validateEditorFinder);
                            journal.addInfo(JOURNAL_KEY_FLOW_NAME, validateFlowName);
                        }
                        if(!processValidate(req, resp, context, validateFlow, journal)){
                            if(!handleValidateError(req, resp, context, journal)){
                                return;
                            }
                        }
                    }finally{
                        if(journal != null){
                            journal.addEndStep();
                        }
                    }
                }
            }
            final BeanFlowInvoker flow
                = beanFlowInvokerFactory.createFlow(flowName);
            try{
                if(journal != null){
                    journal.addStartStep(JOURNAL_KEY_ACTION, actionEditorFinder);
                    journal.addInfo(JOURNAL_KEY_FLOW_NAME, flowName);
                }
                processAction(req, resp, context, flow, journal);
            }finally{
                if(journal != null){
                    journal.addEndStep();
                }
            }
        }finally{
            if(journal != null){
                journal.endJournal();
            }
        }
    }
    
    protected String processSelectBeanFlow(
        HttpServletRequest req,
        HttpServletResponse resp
    ) throws ServletException, IOException{
        BeanFlowSelector beanFlowSelector = defaultBeanFlowSelector;
        if(beanFlowSelectorServiceName != null){
            beanFlowSelector = (BeanFlowSelector)ServiceManagerFactory
                .getServiceObject(beanFlowSelectorServiceName);
        }
        return beanFlowSelector.selectBeanFlow(req);
    }
    
    protected void handleNotFound(
        HttpServletRequest req,
        HttpServletResponse resp,
        String flowName
    ) throws ServletException, IOException{
        resp.sendError(
            HttpServletResponse.SC_NOT_FOUND,
            "Flow '" + flowName + "' is not found."
        );
    }
    
    protected boolean processValidate(
        HttpServletRequest req,
        HttpServletResponse resp,
        BeanFlowServletContext context,
        BeanFlowInvoker validateFlow,
        Journal journal
    ) throws ServletException, IOException{
        try{
            if(journal != null){
                journal.addInfo(JOURNAL_KEY_INPUT, context);
            }
            final Object ret = validateFlow.invokeFlow(context);
            if(journal != null){
                journal.addInfo(JOURNAL_KEY_OUTPUT, ret);
            }
            boolean result = false;
            if(ret != null && ret instanceof Boolean){
                result = ((Boolean)ret).booleanValue();
            }
            if(!result && context.getOutput() != null){
                req.setAttribute(outputAttributeName, context.getOutput());
            }
            return result;
        }catch(Exception e){
            return handleValidateException(req, resp, context, journal, e);
        }
    }
    
    protected boolean handleValidateException(
        HttpServletRequest req,
        HttpServletResponse resp,
        BeanFlowServletContext context,
        Journal journal,
        Exception e
    ) throws ServletException, IOException{
        if(journal != null){
            journal.addInfo(JOURNAL_KEY_EXCEPTION, e);
        }
        throw new ServletException("Validate error.", e);
    }
    
    protected boolean handleValidateError(
        HttpServletRequest req,
        HttpServletResponse resp,
        BeanFlowServletContext context,
        Journal journal
    ) throws ServletException, IOException{
        return false;
    }
    
    protected void processAction(
        HttpServletRequest req,
        HttpServletResponse resp,
        BeanFlowServletContext context,
        BeanFlowInvoker flow,
        Journal journal
    ) throws ServletException, IOException{
        try{
            if(journal != null){
                journal.addInfo(JOURNAL_KEY_INPUT, context);
            }
            final Object ret = flow.invokeFlow(context);
            if(journal != null){
                journal.addInfo(JOURNAL_KEY_OUTPUT, ret);
            }
            if(context.getOutput() == null){
                if(ret != null){
                    req.setAttribute(outputAttributeName, ret);
                }
            }else{
                req.setAttribute(outputAttributeName, context.getOutput());
            }
        }catch(Exception e){
            handleActionException(req, resp, context, journal, e);
        }
    }
    
    protected boolean handleActionException(
        HttpServletRequest req,
        HttpServletResponse resp,
        BeanFlowServletContext context,
        Journal journal,
        Exception e
    ) throws ServletException, IOException{
        if(journal != null){
            journal.addInfo(JOURNAL_KEY_EXCEPTION, e);
        }
        throw new ServletException("Flow error.", e);
    }
}