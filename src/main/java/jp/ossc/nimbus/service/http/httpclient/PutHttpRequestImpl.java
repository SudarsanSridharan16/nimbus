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
package jp.ossc.nimbus.service.http.httpclient;

import java.io.*;
import java.util.*;

import org.apache.commons.httpclient.*;
import org.apache.commons.httpclient.methods.*;

/**
 * Jakarta HttpClient���g����HTTP PUT���N�G�X�g�B<p>
 *
 * @author M.Takata
 */
public class PutHttpRequestImpl extends HttpRequestImpl{
    
    /**
     * �T�|�[�g���Ȃ��B<p>
     *
     * @param name ���N�G�X�g�p�����[�^��
     * @param value ���N�G�X�g�p�����[�^
     */
    public void setParameter(String name, String value){
        throw new UnsupportedOperationException();
    }
    
    /**
     * �T�|�[�g���Ȃ��B<p>
     *
     * @param name ���N�G�X�g�p�����[�^��
     * @param value ���N�G�X�g�p�����[�^
     */
    public void setParameters(String name, String[] value){
        throw new UnsupportedOperationException();
    }
    
    /**
     * {@link PutMethod}�𐶐�����B<p>
     *
     * @return PutMethod
     * @exception Exception PutMethod�̐����Ɏ��s�����ꍇ
     */
    protected HttpMethodBase instanciateHttpMethod() throws Exception{
        return new PutMethod();
    }
    
    /**
     * �T�|�[�g���Ȃ��B<p>
     *
     * @param method HTTP���\�b�h
     * @param params ���N�G�X�g�p�����[�^
     * @exception Exception ���N�G�X�g�p�����[�^�̐ݒ�Ɏ��s�����ꍇ
     */
    protected void initParameter(
        HttpMethodBase method,
        Map params
    ) throws Exception{
        throw new UnsupportedOperationException();
    }
    
    /**
     * ���̓X�g���[������ǂݍ��񂾃f�[�^�����N�G�X�g�̃{�f�B�Ƃ��ďo�͂���B<p>
     *
     * @param method HTTP���\�b�h
     * @param is ���̓X�g���[��
     * @exception Exception ���N�G�X�g�̃{�f�B�o�͂Ɏ��s�����ꍇ
     */
    protected void initInputStream(
        HttpMethodBase method,
        InputStream is
    ) throws Exception{
        ((EntityEnclosingMethod)method).setRequestEntity(
            new InputStreamRequestEntity(is)
        );
    }
}
