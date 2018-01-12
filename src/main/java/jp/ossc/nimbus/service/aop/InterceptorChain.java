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

/**
 * �C���^�[�Z�v�^�`�F�[���B<p>
 * {@link #invokeNext(InvocationContext)}���Ăяo�����ŁA{@link InterceptorChainList}�ɓo�^���ꂽ{@link Interceptor}�������Ăяo���B<br>
 * �܂��A�S�Ă�Interceptor���Ăяo���ƁA�{���̌Ăяo������Ăяo��{@link Invoker}���Ăяo���B<br>
 *
 * @author M.Takata
 */
public interface InterceptorChain{
    
    /**
     * ���̃C���^�[�Z�v�^���Ăяo���B�Ō�̃C���^�Z�v�^���Ăяo������́A�{���̌Ăяo������Ăяo��{@link Invoker}���Ăяo���B<p>
     *
     * @param context �Ăяo���̃R���e�L�X�g���
     * @return �Ăяo�����ʂ̖߂�l
     * @exception Throwable �Ăяo����ŗ�O�����������ꍇ
     */
    public Object invokeNext(InvocationContext context) throws Throwable;
    
    /**
     * ���݂̃C���^�[�Z�v�^�̂��̃C���^�[�Z�v�^�`�F�[����̃C���f�b�N�X���擾����B<p>
     *
     * @return ���݂̃C���^�[�Z�v�^�̂��̃C���^�[�Z�v�^�`�F�[����̃C���f�b�N�X
     */
    public int getCurrentInterceptorIndex();
    
    /**
     * ���݂̃C���^�[�Z�v�^�̂��̃C���^�[�Z�v�^�`�F�[����̃C���f�b�N�X��ݒ肷��B<p>
     *
     * @param index ���݂̃C���^�[�Z�v�^�̂��̃C���^�[�Z�v�^�`�F�[����̃C���f�b�N�X
     */
    public void setCurrentInterceptorIndex(int index);
    
    /**
     * ���̃C���^�[�Z�v�^�`�F�[�������C���^�[�Z�v�^�̃��X�g���擾����B<p>
     *
     * @return ���̃C���^�[�Z�v�^�`�F�[�������C���^�[�Z�v�^�̃��X�g
     */
    public InterceptorChainList getInterceptorChainList();
    
    /**
     * �Ō�̌Ăяo�����s��Invoker���擾����B<p>
     *
     * @return �Ō�̌Ăяo�����s��Invoker
     */
    public Invoker getInvoker();
    
    /**
     * �Ō�̌Ăяo�����s��Invoker��ݒ肷��B<p>
     *
     * @param invoker �Ō�̌Ăяo�����s��Invoker
     */
    public void setInvoker(Invoker invoker);
    
    /**
     * ���̃C���X�^���X�̕��������B<p>
     *
     * @return ����
     */
    public InterceptorChain cloneChain();
}
