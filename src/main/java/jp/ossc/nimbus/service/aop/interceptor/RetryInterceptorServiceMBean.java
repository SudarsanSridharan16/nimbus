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
 * {@link RetryInterceptorService}��MBean�C���^�t�F�[�X�B<p>
 * 
 * @author M.Takata
 * @see RetryInterceptorService
 */
public interface RetryInterceptorServiceMBean
 extends ServiceBaseMBean{
    
    public static final String DEFAULT_RETRY_COUNT_ATTRIBUTE_NAME
         = RetryInterceptorService.class.getName().replaceAll("\\.", "_") + "_RETRY_COUNT";
    
    /**
     * ���g���C����߂�l�̏�����ݒ肷��B<p>
     * �������́AThe Apache Jakarta Project�� Commons Jexl(http://jakarta.apache.org/commons/jexl/)���g�p����B<br>
     * �߂�l���̂��Q�Ƃ���ꍇ�́A"value"�Ƃ����\�����g�p����B<br>
     * �߂�l�̃v���p�e�B���Q�Ƃ���ꍇ�́A�v���p�e�B��\�����镶�����"@"�ň͂�Ŏw�肷��B�����Ō����A�v���p�e�B�̊T�O�́AJava Beans�̃v���p�e�B�̊T�O���L���A{@link jp.ossc.nimbus.beans.PropertyFactory PropertyFactory}�̋K��ɏ]���B<br>
     *
     * @param conditions �������z��
     */
    public void setReturnConditions(String[] conditions);
    
    /**
     * ���g���C����߂�l�̏������擾����B<p>
     *
     * @return �������z��
     */
    public String[] getReturnConditions();
    
    /**
     * ���g���C�����O�̃N���X���Ƃ��̏�����ݒ肷��B<p>
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
     * �f�t�H���g�́A1�B<br>
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
     * ���݂̃��g���C�񐔂�ێ�����{@link jp.ossc.nimbus.service.aop.InvocationContext InvocationContext}�̑�������ݒ肷��B<p>
     * �f�t�H���g�́A{@link #DEFAULT_RETRY_COUNT_ATTRIBUTE_NAME}�B<br>
     *
     * @param name ���݂̃��g���C�񐔂�ێ�����InvocationContext�̑�����
     */
    public void setRetryCountAttributeName(String name);
    
    /**
     * ���݂̃��g���C�񐔂�ێ�����{@link jp.ossc.nimbus.service.aop.InvocationContext InvocationContext}�̑��������擾����B<p>
     *
     * @return ���݂̃��g���C�񐔂�ێ�����InvocationContext�̑�����
     */
    public String getRetryCountAttributeName();
    
    /**
     * ���g���C����Ԋu[ms]��ݒ肷��B<p>
     * �f�t�H���g�́A0�ŊԊu���������Ƀ��g���C����B<br>
     *
     * @param millis ���g���C����Ԋu[ms]
     */
    public void setRetryInterval(long millis);
    
    /**
     * ���g���C����Ԋu[ms]���擾����B<p>
     *
     * @return ���g���C����Ԋu[ms]
     */
    public long getRetryInterval();
}