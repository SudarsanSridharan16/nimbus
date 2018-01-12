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
 * {@link ExceptionConsumeInterceptorService}��MBean�C���^�t�F�[�X<p>
 * 
 * @author N.Saisho
 * @see ExceptionConsumeInterceptorService
 */
public interface ExceptionConsumeInterceptorServiceMBean extends ServiceBaseMBean{
    
    /**
     * �L���b�`�����O�N���X��ݒ肷��B<p>
     *
     * @param classnames �u�L���b�`�����O�N���X�v��ݒ肷��B
     */
    public void setExceptionClassNames(String[] classnames);
    
    /**
     * �L���b�`�����O�N���X���擾����B<p>
     *
     * @return �L���b�`�����O�N���X���z��
     */
    public String[] getExceptionClassNames();
    
    /**
     * �߂�l�����郁�\�b�h�Ăяo���̗�O������Ԃ������ɕԂ��߂�l��ݒ肷��B<p>
     *
     * @param val �߂�l
     */
    public void setReturnValue(Object val);
    
    /**
     * �߂�l�����郁�\�b�h�Ăяo���̗�O������Ԃ������ɕԂ��߂�l���擾����B<p>
     *
     * @return �߂�l
     */
    public Object getReturnValue();
    
    /**
     * log���b�Z�[�W�𐶐�����{@link jp.ossc.nimbus.service.log Logger}�T�[�r�X�̃T�[�r�X����ݒ肷��B<p>
     *
     * @param name Log�T�[�r�X�̃T�[�r�X��
     */
    public void setLoggerServiceName(ServiceName name);
    
    /**
     * log���b�Z�[�W�𐶐�����{@link jp.ossc.nimbus.service.log Logger}�T�[�r�X�̃T�[�r�X�����擾����B<p>
     *
     * @return Log�T�[�r�X�̃T�[�r�X��
     */
    public ServiceName getLoggerServiceName();
    
    /**
     * {@link jp.ossc.nimbus.service.log Logger}�T�[�r�X�ŏo�͂��郍�O���b�Z�[�W�̃R�[�h��ݒ肷��B<p>
     *
     * @param key ���O���b�Z�[�W�̃R�[�h
     */
    public void setLoggerMessageCode(String key);
    
    /**
     * {@link jp.ossc.nimbus.service.log Logger}�T�[�r�X�ŏo�͂��郍�O���b�Z�[�W�̃R�[�h���擾����B<p>
     *
     * @return key ���O���b�Z�[�W�̃R�[�h
     */
    public String getLoggerMessageCode();
    
    /**
     * {@link jp.ossc.nimbus.service.log Logger}�T�[�r�X�ŏo�͂��郍�O���b�Z�[�W�ւ̖��ߍ��ݕ�����ݒ肷��B<p>
     *
     * @param args ���O���b�Z�[�W�ւ̖��ߍ��ݕ�����z��
     */
    public void setLoggerMessageArgs(String[] args);
    
    /**
     * {@link jp.ossc.nimbus.service.log Logger}�T�[�r�X�ŏo�͂��郍�O���b�Z�[�W�ւ̖��ߍ��ݕ������擾����B<p>
     *
     * @return ���O���b�Z�[�W�ւ̖��ߍ��ݕ�����z��
     */
    public String[] getLoggerMessageArgs();
    
    /**
     * {@link jp.ossc.nimbus.service.log Logger}�T�[�r�X�ŏo�͂��郍�O���b�Z�[�W�̃��P�[����ݒ肷��B<p>
     *
     * @param locale ���O���b�Z�[�W�̃��P�[��
     */
    public void setLoggerMessageLocale(java.util.Locale locale);
    
    /**
     * {@link jp.ossc.nimbus.service.log Logger}�T�[�r�X�ŏo�͂��郍�O���b�Z�[�W�̃��P�[�����擾����B<p>
     *
     * @return ���O���b�Z�[�W�̃��P�[��
     */
    public java.util.Locale getLoggerMessageLocale();
    
    /**
     * ����Ԃ�����O�����O�ɏo�͂��邩�ǂ�����ݒ肷��B<p>
     *
     * @param isLogging ��O�����O�ɏo�͂���ꍇ�́Atrue
     */
    public void setLoggingException(boolean isLogging);
    
    /**
     * ����Ԃ�����O�����O�ɏo�͂��邩�ǂ����𔻒肷��B<p>
     *
     * @return true�̏ꍇ�A��O�����O�ɏo�͂���
     */
    public boolean isLoggingException();
}
