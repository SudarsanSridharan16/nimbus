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

import jp.ossc.nimbus.core.*;

/**
 * {@link MethodAsynchronousInterceptorService}��MBean�C���^�t�F�[�X<p>
 * 
 * @author M.Takata
 * @see MethodAsynchronousInterceptorService
 */
public interface MethodAsynchronousInterceptorServiceMBean extends ServiceBaseMBean{
    
    /**
     * ���N�G�X�g���i�[����{@link jp.ossc.nimbus.service.queue.Queue Queue}�T�[�r�X�̃T�[�r�X����ݒ肷��B<p>
     * �f�t�H���g�ŁA{@link jp.ossc.nimbus.service.queue.DefaultQueueService DefaultQueueService}�𐶐����A�g�p����B
     *
     * @param name Queue�T�[�r�X�̃T�[�r�X��
     */
    public void setRequestQueueServiceName(ServiceName name);
    
    /**
     * ���N�G�X�g���i�[����{@link jp.ossc.nimbus.service.queue.Queue Queue}�T�[�r�X�̃T�[�r�X�����擾����B<p>
     *
     * @return Queue�T�[�r�X�̃T�[�r�X��
     */
    public ServiceName getRequestQueueServiceName();
    
    /**
     * �񓯊��Ăяo���̖߂����莞�ԑ҂ꍇ�̃^�C���A�E�g�l[ms]��ݒ肷��B<p>
     *
     * @param timeout �^�C���A�E�g[ms]
     */
    public void setResponseTimeout(long timeout);
    
    /**
     * �񓯊��Ăяo���̖߂����莞�ԑ҂ꍇ�̃^�C���A�E�g�l[ms]���擾����B<p>
     *
     * @return �^�C���A�E�g[ms]
     */
    public long getResponseTimeout();
    
    /**
     * �񓯊��Ăяo���̖߂����莞�ԑ҂��āA�߂肪�Ԃ��Ă��Ȃ������ꍇ�ɗ�O��throw���邩�ǂ�����ݒ肷��B<p>
     * �f�t�H���g��true�B
     * 
     * @param isThrow �񓯊��Ăяo���̖߂����莞�ԑ҂��Ė߂肪�Ԃ��Ă��Ȃ������ꍇ�ɗ�O��throw����ꍇtrue
     */
    public void setFailToWaitResponseTimeout(boolean isThrow);
    
    /**
     * �񓯊��Ăяo���̖߂����莞�ԑ҂��āA�߂肪�Ԃ��Ă��Ȃ������ꍇ�ɗ�O��throw���邩�ǂ����𔻒肷��B<p>
     * 
     * @return �񓯊��Ăяo���̖߂����莞�ԑ҂��Ė߂肪�Ԃ��Ă��Ȃ������ꍇ�ɗ�O��throw����ꍇtrue
     */
    public boolean isFailToWaitResponseTimeout();
    
    /**
     * ���X�|���X���i�[����{@link jp.ossc.nimbus.service.queue.Queue Queue}�T�[�r�X�̃T�[�r�X����ݒ肷��B<p>
     * {@link #setResponseTimeout(long)}�ŗL���ȃ^�C���A�E�g�l���ݒ肳��Ă���ꍇ�́A�f�t�H���g��{@link jp.ossc.nimbus.service.queue.DefaultQueueService DefaultQueueService}�𐶐����A�g�p����B�^�C���A�E�g�l���ݒ肳��Ă��Ȃ��ꍇ�́A�f�t�H���g�ł̓��X�|���X�L���[�͂Ȃ��ŁA���X�|���X�͎̂Ă���B<br>
     *
     * @param name Queue�T�[�r�X�̃T�[�r�X��
     */
    public void setResponseQueueServiceName(ServiceName name);
    
    /**
     * ���X�|���X���i�[����{@link jp.ossc.nimbus.service.queue.Queue Queue}�T�[�r�X�̃T�[�r�X�����擾����B<p>
     *
     * @return Queue�T�[�r�X�̃T�[�r�X��
     */
    public ServiceName getResponseQueueServiceName();
    
    /**
     * �񓯊��������s���X���b�h����ݒ肷��B<p>
     * �f�t�H���g�́A1�B
     *
     * @param size �X���b�h��
     */
    public void setInvokerThreadSize(int size);
    
    /**
     * �񓯊��������s���X���b�h�����擾����B<p>
     *
     * @return �X���b�h��
     */
    public int getInvokerThreadSize();
    
    /**
     * �񓯊��������s���X���b�h���f�[�����X���b�h���ǂ�����ݒ肷��B<p>
     * �f�t�H���g�́Atrue�B
     *
     * @param isDaemon �f�[�����̏ꍇtrue
     */
    public void setInvokerThreadDaemon(boolean isDaemon);
    
    /**
     * �񓯊��������s���X���b�h���f�[�����X���b�h���ǂ����𔻒肷��B<p>
     *
     * @return true�̏ꍇ�f�[����
     */
    public boolean isInvokerThreadDaemon();
    
    /**
     * �񓯊����������s���̃X���b�h�����擾����B<p>
     *
     * @return �񓯊����������s���̃X���b�h��
     */
    public int getActiveInvokerThreadSize();
    
    /**
     * �Ăяo���X���b�h�̖߂�l�Ƃ��āA�����L���[�̖߂�l��Ԃ����ǂ�����ݒ肷��B<p>
     * �f�t�H���g�́Atrue�B<br>
     *
     * @param isReturn �߂�l��Ԃ��ꍇtrue
     */
    public void setReturnResponse(boolean isReturn);
    
    /**
     * �Ăяo���X���b�h�̖߂�l�Ƃ��āA�����L���[�̖߂�l��Ԃ����ǂ����𔻒肷��B<p>
     *
     * @return true�̏ꍇ�A�߂�l��Ԃ�
     */
    public boolean isReturnResponse();
}
