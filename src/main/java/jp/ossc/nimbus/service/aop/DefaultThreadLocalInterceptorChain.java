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

import jp.ossc.nimbus.core.*;

/**
 * �f�t�H���g�X���b�h�P�ʃC���^�[�Z�v�^�`�F�[���B<p>
 * ���݌Ăяo����Ă���C���^�[�Z�v�^�̏����X���b�h�P�ʂɃC���X�^���X�ϐ��Ɋi�[����̂ŁA�X���b�h�Z�[�t�ȃC���^�[�Z�v�^�`�F�[���ł���B<br>
 *
 * @author M.Takata
 */
public class DefaultThreadLocalInterceptorChain
 extends DefaultInterceptorChain implements java.io.Serializable{
    
    private static final long serialVersionUID = 5302115451138234378L;
    
    /**
     * ���݌Ăяo�����̏����X���b�h�P�ʂɕێ�����ThreadLocal�B<p>
     */
    protected transient ThreadLocal state = new ThreadLocal(){
        protected synchronized Object initialValue(){
            return new InterceptorChainState();
        }
    };
    
    /**
     * ��̃C���^�[�Z�v�^�`�F�[���𐶐�����B<p>
     */
    public DefaultThreadLocalInterceptorChain(){
        super();
    }
    
    /**
     * �w�肳�ꂽ{@link InterceptorChainList}��{@link Invoker}�̃C���^�[�Z�v�^�`�F�[���𐶐�����B<p>
     *
     * @param list �`�F�[������C���^�[�Z�v�^�̃��X�g
     * @param invoker �{���̌Ăяo������Ăяo��Invoker
     */
    public DefaultThreadLocalInterceptorChain(
        InterceptorChainList list,
        Invoker invoker
    ){
        super(list, invoker);
    }
    
    /**
     * �w�肳�ꂽ{@link InterceptorChainList}�T�[�r�X��{@link Invoker}�T�[�r�X�̃C���^�[�Z�v�^�`�F�[���𐶐�����B<p>
     *
     * @param listServiceName �`�F�[������C���^�[�Z�v�^�̃��X�gInterceptorChainList�T�[�r�X�̃T�[�r�X��
     * @param invokerServiceName �{���̌Ăяo������Ăяo��Invoker�T�[�r�X�̃T�[�r�X��
     */
    public DefaultThreadLocalInterceptorChain(
        ServiceName listServiceName,
        ServiceName invokerServiceName
    ){
        super(listServiceName, invokerServiceName);
    }
    
    // InterceptorChain��JavaDoc
    public int getCurrentInterceptorIndex(){
        InterceptorChainState chainState = (InterceptorChainState)state.get();
        return chainState.currentIndex;
    }
    
    /**
     * ���݂̃C���^�[�Z�v�^�̂��̃C���^�[�Z�v�^�`�F�[����̃C���f�b�N�X��ݒ肷��B<p>
     *
     * @param index ���݂̃C���^�[�Z�v�^�̂��̃C���^�[�Z�v�^�`�F�[����̃C���f�b�N�X
     */
    public void setCurrentInterceptorIndex(int index){
        InterceptorChainState chainState = (InterceptorChainState)state.get();
        chainState.currentIndex = index;
    }
    
    // InterceptorChain��JavaDoc
    public InterceptorChain cloneChain(){
        DefaultThreadLocalInterceptorChain clone
             = (DefaultThreadLocalInterceptorChain)super.cloneChain();
        clone.state = new ThreadLocal(){
            protected synchronized Object initialValue(){
                return new InterceptorChainState();
            }
        };
        return clone;
    }
    
    /**
     * ���݌Ăяo�����̃C���^�[�Z�v�^�̏����i�[����N���X�B<p>
     * 
     * @author M.Takata
     */
    protected static class InterceptorChainState
     implements java.io.Serializable{
        
        private static final long serialVersionUID = -24647693558335555L;
        
        /**
         * {@link InterceptorChainList}���́A���݂̏�������{@link Interceptor}�̃C���f�b�N�X�B<p>
         * �����l�́A-1�B
         */
        public int currentIndex = -1;
    }
}
