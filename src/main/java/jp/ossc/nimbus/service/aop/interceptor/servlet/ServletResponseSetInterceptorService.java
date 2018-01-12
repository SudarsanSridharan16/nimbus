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
package jp.ossc.nimbus.service.aop.interceptor.servlet;

import java.util.*;
import javax.servlet.*;

import jp.ossc.nimbus.service.aop.*;

/**
 * ���X�|���X�ݒ�C���^�[�Z�v�^�B<p>
 *
 * @author M.Takata
 */
public class ServletResponseSetInterceptorService
 extends ServletFilterInterceptorService
 implements ServletResponseSetInterceptorServiceMBean{
    
    private static final long serialVersionUID = 8599129621419714729L;
    
    protected int bufferSize = -1;
    protected String characterEncoding;
    protected String contentType;
    protected Locale locale;
    
    public void setBufferSize(int size){
        bufferSize = size;
    }
    public int getBufferSize(){
        return bufferSize;
    }
    
    public void setCharacterEncoding(String charset){
        characterEncoding = charset;
    }
    public String getCharacterEncoding(){
        return characterEncoding;
    }
    
    public void setContentType(String type){
        contentType = type;
    }
    public String getContentType(){
        return contentType;
    }
    
    public void setLocale(Locale loc){
        locale = loc;
    }
    public Locale getLocale(){
        return locale;
    }
    
    /**
     * ���X�|���X�ɐݒ肵�āA���̃C���^�[�Z�v�^���Ăяo���B<p>
     * �T�[�r�X���J�n����Ă��Ȃ��ꍇ�́A���������Ɏ��̃C���^�[�Z�v�^���Ăяo���B<br>
     *
     * @param context �Ăяo���̃R���e�L�X�g���
     * @param chain ���̃C���^�[�Z�v�^���Ăяo�����߂̃`�F�[��
     * @return �Ăяo�����ʂ̖߂�l
     * @exception Throwable �Ăяo����ŗ�O�����������ꍇ�A�܂��͂��̃C���^�[�Z�v�^�ŔC�ӂ̗�O�����������ꍇ�B�A���A�{���Ăяo����鏈����throw���Ȃ�RuntimeException�ȊO�̗�O��throw���Ă��A�Ăяo�����ɂ͓`�d����Ȃ��B
     */
    public Object invokeFilter(
        ServletFilterInvocationContext context,
        InterceptorChain chain
    ) throws Throwable{
        if(getState() == STARTED){
            
            final ServletResponse response = context.getServletResponse();
            if(bufferSize != -1){
                response.setBufferSize(bufferSize);
            }
            if(characterEncoding != null){
                response.setCharacterEncoding(characterEncoding);
            }
            if(contentType != null){
                response.setContentType(contentType);
            }
            if(locale != null){
                response.setLocale(locale);
            }
        }
        return chain.invokeNext(context);
    }
}
