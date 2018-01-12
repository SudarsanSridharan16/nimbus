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

import java.io.IOException;

import javax.servlet.http.*;

/**
 * REST���X�|���X�B<p>
 *
 * @author M.Takata
 */
public class RestResponse{
    
    /**
     * HTTP���X�|���X�B<p>
     */
    protected HttpServletResponse response;
    
    /**
     * ���X�|���X�I�u�W�F�N�g�B<p>
     */
    protected Object responseObject;
    
    /**
     * ���X�|���X�I�u�W�F�N�g�̃N���X�B<p>
     */
    protected Class responseObjectClass;
    
    /**
     * HTTP�X�e�[�^�X�B<p>
     */
    protected int status = HttpServletResponse.SC_OK;
    
    /**
     * ��̃C���X�^���X�𐶐�����B<p>
     */
    public RestResponse(){
    }
    
    /**
     * �w�肳�ꂽHTTP���X�|���X�ɕR�Â��C���X�^���X�𐶐�����B<p>
     *
     * @param response HTTP���X�|���X
     */
    public RestResponse(HttpServletResponse response){
        this.response = response;
    }
    
    /**
     * HTTP���X�|���X���擾����B<p>
     *
     * @return HTTP���X�|���X
     */
    public HttpServletResponse getResponse(){
        return response;
    }
    
    /**
     * �������ʂ�ݒ肷��B<p>
     *
     * @param status HTTP�X�e�[�^�X
     */
    public void setResult(int status){
        response.setStatus(status);
        this.status = status;
    }
    
    /**
     * �������ʂ�ݒ肷��B<p>
     *
     * @param status HTTP�X�e�[�^�X
     * @param message ���b�Z�[�W
     */
    public void setResult(int status, String message) throws IOException{
        response.sendError(status, message);
        this.status = status;
    }
    
    /**
     * �������ʂ�HTTP�X�e�[�^�X���擾����B<p>
     *
     * @return HTTP�X�e�[�^�X
     */
    public int getResultStatus(){
        return status;
    }
    
    /**
     * ���X�|���X�I�u�W�F�N�g�̃N���X��ݒ肷��B<p>
     *
     * @param clazz ���X�|���X�I�u�W�F�N�g�̃N���X
     */
    protected void setResponseObjectClass(Class clazz){
        responseObjectClass = clazz;
    }
    
    /**
     * ���X�|���X�I�u�W�F�N�g�̃N���X���擾����B<p>
     *
     * @return ���X�|���X�I�u�W�F�N�g�̃N���X
     */
    public Class getResponseObjectClass(){
        return responseObjectClass;
    }
    
    /**
     * ���X�|���X�I�u�W�F�N�g�𐶐�����B<p>
     *
     * @return ���X�|���X�I�u�W�F�N�g
     * @exception InstantiationException �����Ɏ��s�����ꍇ
     * @exception IllegalAccessException �����Ȃ��̃R���X�g���N�^�ɃA�N�Z�X�ł��Ȃ��ꍇ
     */
    public Object createResponseObject() throws InstantiationException, IllegalAccessException{
        responseObject = responseObjectClass == null ? null : responseObjectClass.newInstance();
        return responseObject;
    }
    
    /**
     * ���X�|���X�I�u�W�F�N�g��ݒ肷��B<p>
     *
     * @param responseObj ���X�|���X�I�u�W�F�N�g
     * @exception IllegalArgumentException �w�肵�����X�|���X�I�u�W�F�N�g�̌^���s���ȏꍇ
     */
    public void setResponseObject(Object responseObj) throws IllegalArgumentException{
        if(responseObj != null && responseObjectClass != null && !responseObjectClass.equals(responseObj.getClass())){
            throw new IllegalArgumentException("ResponseObject is not " + responseObjectClass.getName());
        }
        responseObject = responseObj;
    }
    
    /**
     * ���X�|���X�I�u�W�F�N�g���擾����B<p>
     *
     * @return ���X�|���X�I�u�W�F�N�g
     */
    public Object getResponseObject(){
        return responseObject;
    }
}