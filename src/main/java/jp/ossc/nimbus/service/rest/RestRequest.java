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
package jp.ossc.nimbus.service.rest;

import java.util.Map;

import javax.servlet.http.*;

/**
 * REST���N�G�X�g�B<p>
 *
 * @author M.Takata
 */
public class RestRequest{
    
    /**
     * HTTP���N�G�X�g�B<p>
     */
    protected HttpServletRequest request;
    
    /**
     * ���N�G�X�gURI�B<p>
     */
    protected String uri;
    
    /**
     * �p�X�p�����[�^�}�b�v�B<p>
     */
    protected Map pathParameterMap;
    
    /**
     * ���N�G�X�g�I�u�W�F�N�g�B<p>
     */
    protected Object requestObject;
    
    /**
     * ��̃C���X�^���X�𐶐�����B<p>
     */
    public RestRequest(){
    }
    
    /**
     * �w�肳�ꂽHTTP���N�G�X�g�ɕR�Â��C���X�^���X�𐶐�����B<p>
     *
     * @param request HTTP���N�G�X�g
     */
    public RestRequest(HttpServletRequest request){
        this.request = request;
        final String contextPath = request.getContextPath();
        final String requestURI = request.getRequestURI();
        uri = requestURI.startsWith(contextPath) ? requestURI.substring(contextPath.length()) : requestURI;
    }
    
    /**
     * HTTP���N�G�X�g���擾����B<p>
     *
     * @return HTTP���N�G�X�g
     */
    public HttpServletRequest getRequest(){
        return request;
    }
    
    /**
     * ���N�G�X�gURI���擾����B<p>
     *
     * @return ���N�G�X�gURI
     */
    public String getURI(){
        return uri;
    }
    
    /**
     * �p�X�p�����[�^�}�b�v��ݒ肷��B<p>
     *
     * @param map �p�X�p�����[�^�}�b�v
     */
    protected void setPathParameterMap(Map map){
        pathParameterMap = map;
    }
    
    /**
     * �p�X�p�����[�^�}�b�v���擾����B<p>
     *
     * @return �p�X�p�����[�^�}�b�v
     */
    public Map getPathParameterMap(){
        return pathParameterMap;
    }
    
    /**
     * �w�肵���p�X�p�����[�^���擾����B<p>
     *
     * @param name �p�����[�^��
     * @return �p�X�p�����[�^
     */
    public String getPathParameter(String name){
        return pathParameterMap == null ? null : (String)pathParameterMap.get(name);
    }
    
    /**
     * ���N�G�X�g�I�u�W�F�N�g��ݒ肷��B<p>
     *
     * @param requestObj ���N�G�X�g�I�u�W�F�N�g
     */
    protected void setRequestObject(Object requestObj){
        requestObject = requestObj;
    }
    
    /**
     * ���N�G�X�g�I�u�W�F�N�g���擾����B<p>
     *
     * @return ���N�G�X�g�I�u�W�F�N�g
     */
    public Object getRequestObject(){
        return requestObject;
    }
}