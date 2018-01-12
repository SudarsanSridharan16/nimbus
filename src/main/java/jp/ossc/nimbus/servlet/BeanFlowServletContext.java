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

import javax.servlet.http.*;

/**
 * BeanFlow�T�[�u���b�g�̎��s�R���e�L�X�g�B<p>
 * {@link BeanFlowServlet}��BeanFlow���Ăяo�����̈����ƂȂ�I�u�W�F�N�g�B<p>
 *
 * @author M.Takata
 */
public class BeanFlowServletContext{
    
    /**
     * HTTP���N�G�X�g�B<p>
     */
    protected HttpServletRequest request;
    
    /**
     * HTTP���X�|���X�B<p>
     */
    protected HttpServletResponse response;
    
    /**
     * ���̓I�u�W�F�N�g�B<p>
     */
    protected Object input;
    
    /**
     * �o�̓I�u�W�F�N�g�B<p>
     */
    protected Object output;
    
    /**
     * �C���X�^���X�𐶐�����B<p>
     *
     * @param req HTTP���N�G�X�g
     * @param resp HTTP���X�|���X
     */
    public BeanFlowServletContext(
        HttpServletRequest req,
        HttpServletResponse resp
    ){
        request = req;
        response = resp;
    }
    
    /**
     * �C���X�^���X�𐶐�����B<p>
     *
     * @param req HTTP���N�G�X�g
     * @param resp HTTP���X�|���X
     * @param input ���̓I�u�W�F�N�g
     */
    public BeanFlowServletContext(
        HttpServletRequest req,
        HttpServletResponse resp,
        Object input
    ){
        request = req;
        response = resp;
        this.input = input;
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
     * HTTP���X�|���X���擾����B<p>
     * 
     * @return HTTP���X�|���X
     */
    public HttpServletResponse getResponse(){
        return response;
    }
    
    /**
     * ���̓I�u�W�F�N�g���擾����B<p>
     * 
     * @return ���̓I�u�W�F�N�g
     */
    public Object getInput(){
        return input;
    }
    
    /**
     * �o�̓I�u�W�F�N�g���擾����B<p>
     * 
     * @return �o�̓I�u�W�F�N�g
     */
    public Object getOutput(){
        return output;
    }
    
    /**
     * �o�̓I�u�W�F�N�g��ݒ肷��B<p>
     * 
     * @param output �o�̓I�u�W�F�N�g
     */
    public void setOutput(Object output){
        this.output = output;
    }
}