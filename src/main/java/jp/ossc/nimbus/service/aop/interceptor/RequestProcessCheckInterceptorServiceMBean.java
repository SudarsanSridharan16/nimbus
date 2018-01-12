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

import java.util.Map;

import jp.ossc.nimbus.core.*;

/**
 * {@link RequestProcessCheckInterceptorService}��MBean�C���^�t�F�[�X<p>
 * 
 * @author M.Takata
 * @see RequestProcessCheckInterceptorService
 */
public interface RequestProcessCheckInterceptorServiceMBean
 extends ServiceBaseMBean{
    
    public static final String DEBUG_MESSAGE_ID = "RPC__00001";
    public static final String INFO_MESSAGE_ID  = "RPC__00002";
    public static final String ERROR_MESSAGE_ID = "RPC__00003";
    public static final String FATAL_MESSAGE_ID = "RPC__00004";
    
    /**
     * ���O�o�͂���{@link jp.ossc.nimbus.service.log.Logger Logger}�T�[�r�X�̃T�[�r�X����ݒ肷��B<p>
     * �f�t�H���g�ł́A{@link RequestProcessCheckInterceptorService#getLogger()}�Ŏ擾�ł���Logger���g�p����B<br>
     *
     * @param name Logger�T�[�r�X��
     */
    public void setReportingLoggerServiceName(ServiceName name);
    
    /**
     * ���O�o�͂���{@link jp.ossc.nimbus.service.log.Logger Logger}�T�[�r�X�̃T�[�r�X�����擾����B<p>
     *
     * @return Logger�T�[�r�X��
     */
    public ServiceName getReportingLoggerServiceName();
    
    /**
     * �������Ԃ�臒l�Əo�͂��郍�O�̃��b�Z�[�WID�̃}�b�s���O��ݒ肷��B<p>
     * �������Ԃ�臒l[ms]=�o�͂��郍�O�̃��b�Z�[�WID,���O�̏o�͊Ԋu[ms]<br>
     * ���O�̏o�͊Ԋu���w�肵�Ȃ��ꍇ�A�܂���0���w�肵���ꍇ�́A�`�F�b�N���閈�ɏo�͂���B���̐��l���w�肵���ꍇ�́A�P�񂾂��o�͂���B0���傫�����l���w�肵���ꍇ�ɂ́A���̊Ԋu[ms]�Ń��O���o�͂���B<br>
     *
     * @param threshold �������Ԃ�臒l�Əo�͂��郍�O�̃��b�Z�[�WID�̃}�b�s���O
     */
    public void setThreshold(Map threshold);
    
    /**
     * �������Ԃ�臒l�Əo�͂��郍�O�̃��b�Z�[�WID�̃}�b�s���O���擾����B<p>
     *
     * @return �������Ԃ�臒l�Əo�͂��郍�O�̃��b�Z�[�WID�̃}�b�s���O
     */
    public Map getThreshold();
    
    /**
     * �������Ԃ̃`�F�b�N���s���Ԋu[ms]��ݒ肷��B<p>
     *
     * @param interval �`�F�b�N�Ԋu
     */
    public void setCheckInterval(long interval);
    
    /**
     * �������Ԃ̃`�F�b�N���s���Ԋu[ms]���擾����B<p>
     *
     * @return �`�F�b�N�Ԋu
     */
    public long getCheckInterval();
    
    /**
     * ���݂̃��N�G�X�g�̃��|�[�g���o�͂���B<p>
     * 
     * @return ���݂̃��N�G�X�g�̃��|�[�g
     */
    public String displayCurrentReport();
    
    /**
     * �������Ԃ̃`�F�b�N�𒆒f����B<p>
     */
    public void suspendChecker();
    
    /**
     * �������Ԃ̃`�F�b�N���ĊJ����B<p>
     */
    public void resumeChecker();
    
    /**
     * �w�肳�ꂽ���N�G�X�g���X���b�h���C���^�[���v�g����B<p>
     *
     * @param groupName �X���b�h�̃O���[�v��
     * @param threadName �X���b�h��
     * @return �C���^�[���v�g�ł����ꍇ�́Atrue
     */
    public boolean interruptRequest(String groupName, String threadName);
    
    /**
     * �w�肳�ꂽ���N�G�X�g���X���b�h���`�F�b�N�Ώۂ���폜����B<p>
     *
     * @param groupName �X���b�h�̃O���[�v��
     * @param threadName �X���b�h��
     * @return �폜�ł����ꍇ�́Atrue
     */
    public boolean removeRequest(String groupName, String threadName);
    
    /**
     * ���N�G�X�g���X���b�h���`�F�b�N�Ώۂ���S�č폜����B<p>
     */
    public void clearRequest();
    
    /**
     * ���N�G�X�g���̃X���b�h�����擾����B<p>
     *
     * @return ���N�G�X�g���̃X���b�h��
     */
    public int getRequestCount();
}