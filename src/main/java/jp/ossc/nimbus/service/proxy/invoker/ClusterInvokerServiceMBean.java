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
package jp.ossc.nimbus.service.proxy.invoker;

import jp.ossc.nimbus.core.*;

/**
 * {@link ClusterInvokerService}��MBean�C���^�t�F�[�X<p>
 * 
 * @author M.Takata
 * @see ClusterInvokerService
 */
public interface ClusterInvokerServiceMBean
 extends ServiceBaseMBean{
    
    /**
     * �Ăяo���Ώۂ��X���b�h�R���e�L�X�g�Ŏw�肵�ČĂяo���ۂɁA�Ăяo���Ώۂ��w�肷��R���e�L�X�g�L�[�B<p>
     * �X���b�h�R���e�L�X�g�̒l�́A{@link jp.ossc.nimbus.service.proxy.invoker.KeepAliveCheckInvoker KeepAliveCheckInvoker}�̃T�[�r�X���A�܂��́A{@link jp.ossc.nimbus.service.proxy.invoker.KeepAliveCheckInvoker#getHostInfo() KeepAliveCheckInvoker.getHostInfo()}�̒l�B<br>
     */
    public static final String CONTEXT_KEY_INVOKE_TARGET = ClusterInvokerServiceMBean.class.getName().replace('.', '_') + "_TARGET";
    
    /**
     * �Ăяo�����_�Ő������Ă���S�Ă̌Ăяo���Ώۂ��X���b�h�R���e�L�X�g�Ŏw�肵�ČĂяo���ۂɁA�w�肷��R���e�L�X�g�L�[�B<p>
     * �X���b�h�R���e�L�X�g�̒l�́ABoolean.TRUE�܂���Boolean.valueOf(String)��true��Ԃ�������B<br>
     */
    public static final String CONTEXT_KEY_INVOKE_BROADCAST = ClusterInvokerServiceMBean.class.getName().replace('.', '_') + "_BROADCAST";
    
    /**
     * {@link jp.ossc.nimbus.service.proxy.invoker.KeepAliveCheckInvoker KeepAliveCheckInvoker}��I������{@link jp.ossc.nimbus.service.keepalive.KeepAliveCheckerSelector KeepAliveCheckerSelector}�T�[�r�X�̃T�[�r�X����ݒ肷��B<p>
     *
     * @param name KeepAliveCheckerSelector�T�[�r�X�̃T�[�r�X��
     */
    public void setKeepAliveCheckerSelectorServiceName(ServiceName name);
    
    /**
     * {@link jp.ossc.nimbus.service.proxy.invoker.KeepAliveCheckInvoker KeepAliveCheckInvoker}��I������{@link jp.ossc.nimbus.service.keepalive.KeepAliveCheckerSelector KeepAliveCheckerSelector}�T�[�r�X�̃T�[�r�X����ݒ肷��B<p>
     *
     * @return KeepAliveCheckerSelector�T�[�r�X�̃T�[�r�X��
     */
    public ServiceName getKeepAliveCheckerSelectorServiceName();
    
    /**
     * ���g���C�����O�̃N���X���Ƃ��̏�����ݒ肷��B<p>
     * ���̏�����ݒ肵�Ȃ��ꍇ�́A{@link jp.ossc.nimbus.service.proxy.RemoteServiceCallException}���L���b�`�����ꍇ�Ƀ��g���C����B<br>
     * �������́AThe Apache Jakarta Project�� Commons Jexl(http://jakarta.apache.org/commons/jexl/)���g�p����B<br>
     * ��O�̃v���p�e�B���Q�Ƃ���ꍇ�́A�v���p�e�B��\�����镶�����"@"�ň͂�Ŏw�肷��B�����Ō����A�v���p�e�B�̊T�O�́AJava Beans�̃v���p�e�B�̊T�O���L���A{@link jp.ossc.nimbus.beans.PropertyFactory PropertyFactory}�̋K��ɏ]���B<br>
     *
     * @param conditions ��O�N���X��:�������i�������K�v�Ȃ��ꍇ�́A:�ȉ����ȗ��\�j�̕�����z��
     */
    public void setExceptionConditions(String[] conditions);
    
    /**
     * ���g���C�����O�̃N���X���Ƃ��̏������擾����B<p>
     *
     * @return ��O�N���X��:�������i�������K�v�Ȃ��ꍇ�́A:�ȉ����ȗ��\�j�̕�����z��
     */
    public String[] getExceptionConditions();
    
    /**
     * ���g���C����񐔂�ݒ肷��B<p>
     * �f�t�H���g�́A0�Ń��g���C���Ȃ��B<br>
     *
     * @param count ���g���C�����
     */
    public void setMaxRetryCount(int count);
    
    /**
     * ���g���C����񐔂��擾����B<p>
     *
     * @return ���g���C�����
     */
    public int getMaxRetryCount();
    
    /**
     * ���g���C����Ԋu[ms]��ݒ肷��B<p>
     * �f�t�H���g�́A0�ŊԊu���������Ƀ��g���C����B<br>
     *
     * @param interval ���g���C����Ԋu[ms]
     */
    public void setRetryInterval(long interval);
    
    /**
     * ���g���C����Ԋu[ms]���擾����B<p>
     *
     * @return ���g���C����Ԋu[ms]
     */
    public long getRetryInterval();
    
    /**
     * �Ăяo�����_�Ő������Ă���S�Ă�{@link jp.ossc.nimbus.service.proxy.invoker.KeepAliveCheckInvoker KeepAliveCheckInvoker}���Ăяo���悤�ɂ��邩�ǂ�����ݒ肷��B<p>
     * true�̏ꍇ�A�Ăяo�����_�Ő������Ă���S�Ă�KeepAliveCheckInvoker�������Ăяo���B���̍ہA���g���C�@�\�͖����ƂȂ�A�ǂꂩ�P��KeepAliveCheckInvoker�̌Ăяo���ŗ�O����������ƁA�����ŏI������B<br>
     *
     * @param isBroadcast �S�Ă�KeepAliveCheckInvoker���Ăяo���ꍇ�́Atrue
     */
    public void setBroadcast(boolean isBroadcast);
    
    /**
     * �Ăяo�����_�Ő������Ă���S�Ă�{@link jp.ossc.nimbus.service.proxy.invoker.KeepAliveCheckInvoker KeepAliveCheckInvoker}���Ăяo���悤�ɂ��邩�ǂ����𔻒肷��B<p>
     *
     * @return true�̏ꍇ�A�S�Ă�KeepAliveCheckInvoker���Ăяo��
     */
    public boolean isBroadcast();
    
    /**
     * {@link jp.ossc.nimbus.service.context.Context Context}�T�[�r�X�̃T�[�r�X����ݒ肷��B<p>
     *
     * @param name Context�T�[�r�X�̃T�[�r�X��
     */
    public void setThreadContextServiceName(ServiceName name);
    
    /**
     * {@link jp.ossc.nimbus.service.context.Context Context}�T�[�r�X�̃T�[�r�X�����擾����B<p>
     *
     * @return Context�T�[�r�X�̃T�[�r�X��
     */
    public ServiceName getThreadContextServiceName();
}