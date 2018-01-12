/*
 * This software is distributed under following license based on modified BSD
 * style license.
 * ----------------------------------------------------------------------
 * 
 * Copyright 2009 The Nimbus2 Project. All rights reserved.
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
 * policies, either expressed or implied, of the Nimbus2 Project.
 */
package jp.ossc.nimbus.servlet;

import java.io.*;

import javax.servlet.*;
import javax.servlet.http.*;
import jp.ossc.nimbus.beans.ServiceNameEditor;
import jp.ossc.nimbus.core.ServiceManagerFactory;
import jp.ossc.nimbus.core.ServiceName;
import jp.ossc.nimbus.service.rest.*;

/**
 * RESTful���N�G�X�g����������T�[�u���b�g�B<p>
 * RESTful���N�G�X�g���󂯕t���āA{@link RestServer}���Ăяo���B<br>
 * <p>
 * �ȉ��ɁA�T�[�u���b�g��web.xml��`��������B<br>
 * <pre>
 * &lt;servlet&gt;
 *     &lt;servlet-name&gt;RestServlet&lt;/servlet-name&gt;
 *     &lt;servlet-class&gt;jp.ossc.nimbus.servlet.RestServlet&lt;/servlet-class&gt;
 *     &lt;init-param&gt;
 *         &lt;param-name&gt;RestServerServiceName&lt;/param-name&gt;
 *         &lt;param-value&gt;Nimbus#RestServer&lt;/param-value&gt;
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
public class RestServlet extends HttpServlet{
    
    private static final long serialVersionUID = 2746072267952924971L;
    
    /**
     * {@link RestServer}�T�[�r�X���̏������p�����[�^���B<p>
     */
    public static final String INIT_PARAM_NAME_REST_SERVER_SERVICE_NAME = "RestServerServiceName";
    
    /**
     * {@link RestServer}�T�[�r�X�B<p>
     */
    protected RestServer restServer;
    
    /**
     * �������p�����[�^{@link #INIT_PARAM_NAME_REST_SERVER_SERVICE_NAME}�Ŏw�肳�ꂽ{@link RestServer}�T�[�r�X�̃T�[�r�X�����擾����B<p>
     *
     * @return RestServer�T�[�r�X�̃T�[�r�X��
     */
    protected ServiceName getRestServerServiceName(){
        final ServletConfig config = getServletConfig();
        final String serviceNameStr = config.getInitParameter(INIT_PARAM_NAME_REST_SERVER_SERVICE_NAME);
        if(serviceNameStr == null){
            return null;
        }
        final ServiceNameEditor editor = new ServiceNameEditor();
        editor.setAsText(serviceNameStr);
        return (ServiceName)editor.getValue();
    }
    
    public void init() throws ServletException{
        final ServiceName restServerServiceName = getRestServerServiceName();
        if(restServerServiceName == null){
            throw new ServletException("RestServerServiceName is null.");
        }
        restServer = (RestServer)ServiceManagerFactory
                .getServiceObject(restServerServiceName);
    }
    
    /**
     * PUT���\�b�h�Ăяo������������B<p>
     * 
     * @param req HTTP���N�G�X�g
     * @param resp HTTP���X�|���X
     * @exception ServletException
     * @exception IOException
     */
    protected void doPut(
        HttpServletRequest req,
        HttpServletResponse resp
    ) throws ServletException, IOException{
        try{
            restServer.processPut(new PutRestRequest(req), new PutRestResponse(resp));
        }catch(Throwable e){
            throw new ServletException(e);
        }
    }
    
    /**
     * GET���\�b�h�Ăяo������������B<p>
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
        try{
            restServer.processGet(new GetRestRequest(req), new GetRestResponse(resp));
        }catch(Throwable e){
            throw new ServletException(e);
        }
    }
    
    /**
     * HEAD���\�b�h�Ăяo������������B<p>
     *
     * @param req HTTP���N�G�X�g
     * @param resp HTTP���X�|���X
     * @exception ServletException
     * @exception IOException
     */
    protected void doHead(
        HttpServletRequest req,
        HttpServletResponse resp
    ) throws ServletException, IOException{
        try{
            restServer.processHead(new HeadRestRequest(req), new HeadRestResponse(resp));
        }catch(Throwable e){
            throw new ServletException(e);
        }
    }
    
    /**
     * POST���\�b�h�Ăяo������������B<p>
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
        try{
            restServer.processPost(new PostRestRequest(req), new PostRestResponse(resp));
        }catch(Throwable e){
            throw new ServletException(e);
        }
    }
    
    /**
     * DELETE���\�b�h�Ăяo������������B<p>
     * 
     * @param req HTTP���N�G�X�g
     * @param resp HTTP���X�|���X
     * @exception ServletException
     * @exception IOException
     */
    protected void doDelete(
        HttpServletRequest req,
        HttpServletResponse resp
    ) throws ServletException, IOException{
        try{
            restServer.processDelete(new DeleteRestRequest(req), new DeleteRestResponse(resp));
        }catch(Throwable e){
            throw new ServletException(e);
        }
    }
    
    /**
     * OPTIONS���\�b�h�Ăяo������������B<p>
     * 
     * @param req HTTP���N�G�X�g
     * @param resp HTTP���X�|���X
     * @exception ServletException
     * @exception IOException
     */
    protected void doOptions(
        HttpServletRequest req,
        HttpServletResponse resp
    ) throws ServletException, IOException{
        String reqPath = req.getServletPath();
        if(req.getPathInfo() != null){
            reqPath = reqPath + req.getPathInfo();
        }
        if(reqPath.equals("/*")){
            OptionsRestResponse orr = new OptionsRestResponse(resp);
            orr.allowPut();
            orr.allowGet();
            orr.allowHead();
            orr.allowPost();
            orr.allowDelete();
            orr.allowOptions();
        }else{
            try{
                restServer.processOptions(new OptionsRestRequest(req), new OptionsRestResponse(resp));
            }catch(Throwable e){
                throw new ServletException(e);
            }
        }
    }
}