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
package jp.ossc.nimbus.service.aop.javassist;

import java.lang.reflect.*;
import java.io.*;

import jp.ossc.nimbus.service.aop.*;

/**
 * �A�X�y�N�g�ɂ���ă��b�v���ꂽ���\�b�h�Ăяo���̌Ăяo����������{@link MethodInvocationContext}�̎����N���X�B<p>
 * 
 * @author M.Takata
 */
public class WrappedMethodInvocationContext
 extends DefaultMethodInvocationContext
 implements MethodInvocationContext, java.io.Serializable{
    
    private static final long serialVersionUID = 1372865160145034983L;
    
    /**
     * �A�X�y�N�g�ɂ���ă��b�v���ꂽ���\�b�h�̃��\�b�h�I�u�W�F�N�g�B<p>
     */
    protected transient Method wrappedTargetMethod;
    
    /**
     * �A�X�y�N�g�ɂ���ă��b�v���ꂽ���\�b�h�Ăяo���̌Ăяo�����𐶐�����B<p>
     *
     * @param target �Ăяo���Ώۂ̃I�u�W�F�N�g
     * @param method �Ăяo���Ώۂ̃��\�b�h
     * @param wrappedMethod �A�X�y�N�g�ɂ���ă��b�v���ꂽ���\�b�h
     * @param params �Ăяo���Ώۂ̃��\�b�h����
     */
    public WrappedMethodInvocationContext(
        Object target,
        Method method,
        Method wrappedMethod,
        Object[] params
    ){
        super(target, method, params);
        wrappedTargetMethod = wrappedMethod;
    }
    
    /**
     * �A�X�y�N�g�ɂ���ă��b�v���ꂽ���\�b�h���擾����B<p>
     *
     * @return �A�X�y�N�g�ɂ���ă��b�v���ꂽ���\�b�h�̃��\�b�h�I�u�W�F�N�g
     */
    public Method getWrappedTargetMethod(){
        return wrappedTargetMethod;
    }
    
    /**
     * �C���^�[�Z�v�g���ꂽ�Ăяo���Ώۂ̃��\�b�h�I�u�W�F�N�g��ݒ肷��B<p>
     * �����ɁA�A�X�y�N�g�ɂ���ă��b�v���ꂽ���\�b�h�ɂ��ݒ肷��B
     *
     * @param method �C���^�[�Z�v�g���ꂽ�Ăяo���Ώۂ̃��\�b�h�I�u�W�F�N�g
     */
    public void setTargetMethod(Method method){
        super.setTargetMethod(method);
        wrappedTargetMethod = method;
    }
    
    private void writeObject(ObjectOutputStream out) throws IOException{
        out.defaultWriteObject();
        out.writeObject(new SerializableMethod(targetMethod));
        out.writeObject(new SerializableMethod(wrappedTargetMethod));
    }
    
    private void readObject(ObjectInputStream in)
     throws IOException, ClassNotFoundException{
        in.defaultReadObject();
        targetMethod = ((SerializableMethod)in.readObject()).getMethod();
        wrappedTargetMethod = ((SerializableMethod)in.readObject()).getMethod();
    }
}
