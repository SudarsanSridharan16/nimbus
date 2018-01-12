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

import java.util.*;

import jp.ossc.nimbus.core.*;

/**
 * {@link ExceptionWrapInterceptorService}��MBean�C���^�t�F�[�X<p>
 * 
 * @author M.Takata
 * @see ExceptionWrapInterceptorService
 */
public interface ExceptionWrapInterceptorServiceMBean extends ServiceBaseMBean{
    
    /**
     * �L���b�`�����O�N���X�ƃ��b�v����throw�����O�N���X�̃}�b�s���O��ݒ肷��B<p>
     *
     * @param mapping �u�L���b�`�����O�N���X=���b�v�����O�N���X�v�̃}�b�s���O
     */
    public void setWrapExceptionMapping(Properties mapping);
    
    /**
     * �L���b�`�����O�N���X�ƃ��b�v����throw�����O�N���X�̃}�b�s���O���擾����B<p>
     *
     * @return �u�L���b�`�����O�N���X=���b�v�����O�N���X�v�̃}�b�s���O
     */
    public Properties getWrapExceptionMapping();
    
    /**
     * throw�����O�̃��b�Z�[�W��ݒ肷��B<p>
     *
     * @param msg throw�����O�̃��b�Z�[�W
     */
    public void setMessage(String msg);
    
    /**
     * throw�����O�̃��b�Z�[�W���擾����B<p>
     *
     * @return throw�����O�̃��b�Z�[�W
     */
    public String getMessage();
    
    /**
     * throw�����O�̃��b�Z�[�W�𐶐�����{@link jp.ossc.nimbus.service.message.MessageRecordFactory MessageRecordFactory}�T�[�r�X�̃T�[�r�X����ݒ肷��B<p>
     *
     * @param name MessageRecordFactory�T�[�r�X�̃T�[�r�X��
     */
    public void setMessageRecordFactoryServiceName(ServiceName name);
    
    /**
     * throw�����O�̃��b�Z�[�W�𐶐�����{@link jp.ossc.nimbus.service.message.MessageRecordFactory MessageRecordFactory}�T�[�r�X�̃T�[�r�X�����擾����B<p>
     *
     * @return MessageRecordFactory�T�[�r�X�̃T�[�r�X��
     */
    public ServiceName getMessageRecordFactoryServiceName();
    
    /**
     * throw�����O�̃��b�Z�[�W�𐶐�����{@link jp.ossc.nimbus.service.message.MessageRecordFactory MessageRecordFactory}�T�[�r�X���烁�b�Z�[�W���擾���鎞�̃L�[��ݒ肷��B<p>
     *
     * @param key MessageRecordFactory�T�[�r�X���烁�b�Z�[�W���擾���鎞�̃L�[
     */
    public void setMessageKey(String key);
    
    /**
     * throw�����O�̃��b�Z�[�W�𐶐�����{@link jp.ossc.nimbus.service.message.MessageRecordFactory MessageRecordFactory}�T�[�r�X���烁�b�Z�[�W���擾���鎞�̃L�[���擾����B<p>
     *
     * @return MessageRecordFactory�T�[�r�X���烁�b�Z�[�W���擾���鎞�̃L�[
     */
    public String getMessageKey();
    
    /**
     * throw�����O�̃��b�Z�[�W�𐶐�����{@link jp.ossc.nimbus.service.message.MessageRecordFactory MessageRecordFactory}�T�[�r�X���烁�b�Z�[�W���擾���鎞�̃��b�Z�[�W�ւ̖��ߍ��ݕ������ݒ肷��B<p>
     *
     * @param args MessageRecordFactory�T�[�r�X���烁�b�Z�[�W���擾���鎞�̃��b�Z�[�W�ւ̖��ߍ��ݕ�����
     */
    public void setMessageArgs(String[] args);
    
    /**
     * throw�����O�̃��b�Z�[�W�𐶐�����{@link jp.ossc.nimbus.service.message.MessageRecordFactory MessageRecordFactory}�T�[�r�X���烁�b�Z�[�W���擾���鎞�̃��b�Z�[�W�ւ̖��ߍ��ݕ�������擾����B<p>
     *
     * @return MessageRecordFactory�T�[�r�X���烁�b�Z�[�W���擾���鎞�̃��b�Z�[�W�ւ̖��ߍ��ݕ�����
     */
    public String[] getMessageArgs();
    
    /**
     * throw�����O�̃��b�Z�[�W�𐶐�����{@link jp.ossc.nimbus.service.message.MessageRecordFactory MessageRecordFactory}�T�[�r�X���烁�b�Z�[�W���擾���鎞�̃��b�Z�[�W�̃��P�[����ݒ肷��B<p>
     *
     * @param locale MessageRecordFactory�T�[�r�X���烁�b�Z�[�W���擾���鎞�̃��b�Z�[�W�̃��P�[��
     */
    public void setMessageLocale(java.util.Locale locale);
    
    /**
     * throw�����O�̃��b�Z�[�W�𐶐�����{@link jp.ossc.nimbus.service.message.MessageRecordFactory MessageRecordFactory}�T�[�r�X���烁�b�Z�[�W���擾���鎞�̃��b�Z�[�W�̃��P�[�����擾����B<p>
     *
     * @return MessageRecordFactory�T�[�r�X���烁�b�Z�[�W���擾���鎞�̃��b�Z�[�W�̃��P�[��
     */
    public java.util.Locale getMessageLocale();
}
