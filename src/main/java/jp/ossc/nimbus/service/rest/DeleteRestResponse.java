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

import javax.servlet.http.*;

/**
 * DELETE���\�b�hREST���X�|���X�B<p>
 *
 * @author M.Takata
 */
public class DeleteRestResponse extends BodyRestResponse{
    
    /**
     * ��̃C���X�^���X�𐶐�����B<p>
     */
    public DeleteRestResponse(){
    }
    
    /**
     * �w�肳�ꂽHTTP���X�|���X�ɕR�Â��C���X�^���X�𐶐�����B<p>
     * HTTP�X�e�[�^�X�̏����l�́AHttpServletResponse.SC_NO_CONTENT�B<br>
     *
     * @param response HTTP���X�|���X
     */
    public DeleteRestResponse(HttpServletResponse response){
        super(response);
        setResultOfDeleted();
    }
    
    /**
     * �폜�ɐ��������|�̏������ʂ�ݒ肷��B<p>
     * HttpServletResponse.SC_NO_CONTENT�ɐݒ肷��B<br>
     */
    public void setResultOfDeleted(){
        setResult(HttpServletResponse.SC_NO_CONTENT);
    }
    
    /**
     * �폜�Ώۂ����݂��Ȃ��|�̏������ʂ�ݒ肷��B<p>
     * HttpServletResponse.SC_NOT_FOUND�ɐݒ肷��B<br>
     */
    public void setResultOfNotFound(){
        setResult(HttpServletResponse.SC_NOT_FOUND);
    }
}