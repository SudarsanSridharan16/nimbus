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

/**
 * REST�R���e�L�X�g�B<p>
 *
 * @author M.Takata
 */
public class RestContext{
    
    /**
     * REST���N�G�X�g�B<p>
     */
    protected RestRequest request;
    
    /**
     * REST���X�|���X�B<p>
     */
    protected RestResponse response;
    
    /**
     * ��̃C���X�^���X�𐶐�����B<p>
     */
    public RestContext(){
    }
    
    /**
     * REST���N�G�X�g��REST���X�|���X���������C���X�^���X�𐶐�����B<p>
     *
     * @param request REST���N�G�X�g
     * @param response REST���X�|���X
     */
    public RestContext(RestRequest request, RestResponse response){
        this.request = request;
        this.response = response;
    }
    
    /**
     * REST���N�G�X�g���擾����B<p>
     * 
     * @return REST���N�G�X�g
     */
    public RestRequest getRequest(){
        return request;
    }
    
    /**
     * REST���N�G�X�g��ݒ肷��B<p>
     * 
     * @param request REST���N�G�X�g
     */
    public void setRequest(RestRequest request){
        this.request = request;
    }
    
    /**
     * REST���X�|���X���擾����B<p>
     * 
     * @return REST���X�|���X
     */
    public RestResponse getResponse(){
        return response;
    }
    
    /**
     * REST���X�|���X��ݒ肷��B<p>
     * 
     * @param response REST���X�|���X
     */
    public void setResponse(RestResponse response){
        this.response = response;
    }
}