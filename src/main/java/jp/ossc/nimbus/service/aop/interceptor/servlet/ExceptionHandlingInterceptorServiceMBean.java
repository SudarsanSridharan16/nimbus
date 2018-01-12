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
package jp.ossc.nimbus.service.aop.interceptor.servlet;

import java.util.Map;

import jp.ossc.nimbus.core.ServiceName;

/**
 * {@link ExceptionHandlingInterceptorService}��MBean�C���^�t�F�[�X�B<p>
 * 
 * @author M.Takata
 * @see ExceptionHandlingInterceptorService
 */
public interface ExceptionHandlingInterceptorServiceMBean
 extends ServletFilterInterceptorServiceMBean{
    
    /**
     * ��O�N���X����{@link ExceptionHandler}�T�[�r�X�̃T�[�r�X���̃}�b�s���O��ݒ肷��B<p>
     * ��O�N���X�̎����ɑ΂��āA������t�������ꍇ�́A��O�N���X��(������)�̏����Ŏw��ł���B<br>
     * �������́AThe Apache Jakarta Project�� Commons Jexl(http://jakarta.apache.org/commons/jexl/)���g�p����B<br>
     * ��O�̃v���p�e�B���Q�Ƃ���ꍇ�́A�v���p�e�B��\�����镶�����"@exception."��"@"�ň͂�Ŏw�肷��B<br>
     * ServletRequest�̃v���p�e�B���Q�Ƃ���ꍇ�́A�v���p�e�B��\�����镶�����"@request."��"@"�ň͂�Ŏw�肷��B<br>
     * ServletResponse�̃v���p�e�B���Q�Ƃ���ꍇ�́A�v���p�e�B��\�����镶�����"@response."��"@"�ň͂�Ŏw�肷��B<br>
     * �����Ō����A�v���p�e�B�̊T�O�́AJava Beans�̃v���p�e�B�̊T�O���L���A{@link jp.ossc.nimbus.beans.PropertyFactory PropertyFactory}�̋K��ɏ]���B<br>
     * <pre>
     *  ��F"java.sql.SQLException(@exception.ErrorCode@ == 1013)"=Nimbus#SQLExceptionHandler
     * </pre>
     *
     * @param map ��O�N���X����ExceptionHandler�T�[�r�X�̃T�[�r�X���̃}�b�s���O�B��O�N���X��=ExceptionHandler�T�[�r�X�̃T�[�r�X��
     */
    public void setExceptionAndHandlerMapping(Map map);
    
    /**
     * ��O�N���X����{@link ExceptionHandler}�T�[�r�X�̃T�[�r�X���̃}�b�s���O���擾����B<p>
     *
     * @return ��O�N���X����ExceptionHandler�T�[�r�X�̃T�[�r�X���̃}�b�s���O
     */
    public Map getExceptionAndHandlerMapping();
    
    /**
     * ����������O�Ƀ}�b�s���O���ꂽ{@link ExceptionHandler}�T�[�r�X���Ȃ��ꍇ�Ɏg�p�����ExceptionHandler�T�[�r�X�̃T�[�r�X����ݒ肷��B<p>
     *
     * @param name ExceptionHandler�T�[�r�X�̃T�[�r�X��
     */
    public void setDefaultExceptionHandlerServiceName(ServiceName name);
    
    /**
     * ����������O�Ƀ}�b�s���O���ꂽ{@link ExceptionHandler}�T�[�r�X���Ȃ��ꍇ�Ɏg�p�����ExceptionHandler�T�[�r�X�̃T�[�r�X�����擾����B<p>
     *
     * @return ExceptionHandler�T�[�r�X�̃T�[�r�X��
     */
    public ServiceName getDefaultExceptionHandlerServiceName();
}
