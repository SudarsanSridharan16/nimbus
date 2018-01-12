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
package jp.ossc.nimbus.service.aop;

import java.util.*;

/**
 * {@link InterceptorChainList}�̃f�t�H���g�����B<p>
 *
 * @author M.Takata
 */
public class DefaultInterceptorChainList implements InterceptorChainList, java.io.Serializable{
    
    private static final long serialVersionUID = -2068320773199878293L;
    
    private final List interceptorList;
    
    /**
     * �C���^�[�Z�v�^���o�^����Ă��Ȃ����X�g�𐶐�����B<p>
     */
    public DefaultInterceptorChainList(){
        interceptorList = new ArrayList();
    }
    
    /**
     * �w�肳�ꂽ�C���^�[�Z�v�^�z����`�F�[���Ƃ��Ď����X�g�𐶐�����B<p>
     *
     * @param interceptors �o�^����C���^�[�Z�v�^�̔z��
     */
    public DefaultInterceptorChainList(Interceptor[] interceptors){
        this();
        if(interceptors != null){
            for(int i = 0; i < interceptors.length; i++){
                interceptorList.add(interceptors[i]);
            }
        }
    }
    
    /**
     * �w�肳�ꂽ�C���^�[�Z�v�^���`�F�[���ɒǉ�����B<p>
     *
     * @param interceptor �C���^�[�Z�v�^
     */
    public void addInterceptor(Interceptor interceptor){
        interceptorList.add(interceptor);
    }
    
    /**
     * �w�肳�ꂽ�C���^�[�Z�v�^���`�F�[���̎w�肳�ꂽ�C���f�b�N�X�ɑ}������B<p>
     *
     * @param index �`�F�[�����̃C���f�b�N�X
     * @param interceptor �C���^�[�Z�v�^
     */
    public void addInterceptor(int index, Interceptor interceptor){
        interceptorList.add(index, interceptor);
    }
    
    // InterceptorChainList��JavaDoc
    public Interceptor getInterceptor(InvocationContext context, int index){
        if(interceptorList.size() <= index){
            return null;
        }
        return (Interceptor)interceptorList.get(index);
    }
    
    /**
     * �C���^�[�Z�v�^�̃��X�g���擾����B<p>
     *
     * @return �C���^�[�Z�v�^�̃��X�g
     */
    public List getInterceptors(){
        return interceptorList;
    }
    
    /**
     * �w�肳�ꂽ�C���^�[�Z�v�^�����X�g����폜����B<p>
     *
     * @param interceptor �폜����C���^�[�Z�v�^
     */
    public void removeInterceptor(Interceptor interceptor){
        interceptorList.remove(interceptor);
    }
    
    /**
     * �w�肳�ꂽ�C���f�b�N�X�̃C���^�[�Z�v�^�����X�g����폜����B<p>
     *
     * @param index �`�F�[�����̃C���f�b�N�X
     */
    public void removeInterceptor(int index){
        interceptorList.remove(index);
    }
    
    /**
     * �C���^�[�Z�v�^��S�č폜����B<p>
     */
    public void clearInterceptor(){
        interceptorList.clear();
    }
    
    /**
     * ���̃��X�g�ɓo�^����Ă���C���^�[�Z�v�^�̐����擾����B<p>
     *
     * @return ���̃��X�g�ɓo�^����Ă���C���^�[�Z�v�^�̐�
     */
    public int size(){
        return interceptorList.size();
    }
}
