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

import jp.ossc.nimbus.service.aop.InvocationContext;

/**
 * �񓯊��Ăяo���̖߂�����i�[����N���X�B<p>
 *
 * @author M.Takata
 * @see MethodAsynchronousInterceptorService
 */
public class AsynchronousResponse implements java.io.Serializable{
    
    private static final long serialVersionUID = -6354918070435188105L;
    
    private InvocationContext context;
    private Object returnObject;
    private boolean throwException;
    
    /**
     * �w�肳�ꂽ�߂�l�����C���X�^���X�𐶐�����B<p>
     *
     * @param context �Ăяo���R���e�L�X�g���
     * @param ret �߂�l
     */
    protected AsynchronousResponse(InvocationContext context, Object ret){
        this(context, ret, false);
    }
    
    /**
     * �w�肳�ꂽ�߂�������C���X�^���X�𐶐�����B<p>
     *
     * @param context �Ăяo���R���e�L�X�g���
     * @param ret �߂�l�܂���throw���ꂽ��O
     * @param throwException ��O��throw���ꂽ���ǂ����̃t���O
     */
    protected AsynchronousResponse(
        InvocationContext context,
        Object ret,
        boolean throwException
    ){
        this.context = context;
        returnObject = ret;
        this.throwException = throwException;
    }
    
    /**
     * �Ăяo���R���e�L�X�g�����擾����B<p>
     *
     * @return �Ăяo���R���e�L�X�g���
     */
    public InvocationContext getInvocationContext(){
        return context;
    }
    
    /**
     * �߂�l���擾����B<p>
     *
     * @return �߂�l�B��O��throw���ꂽ�ꍇ��null
     */
    public Object getReturnObject(){
        return throwException ? null : returnObject;
    }
    
    /**
     * ��O��throw���ꂽ���ǂ����𔻒肷��B<p>
     *
     * @return ��O��throw���ꂽ�ꍇ��true
     */
    public boolean isThrownException(){
        return throwException;
    }
    
    /**
     * throw���ꂽ��O���擾����B<p>
     *
     * @return throw���ꂽ��O�B��O��throw����Ă��Ȃ��ꍇ�́Anull
     */
    public Throwable getThrownException(){
        return throwException ? (Throwable)returnObject : null;
    }
}
