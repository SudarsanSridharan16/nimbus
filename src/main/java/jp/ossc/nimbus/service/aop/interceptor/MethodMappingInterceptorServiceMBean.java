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

import java.util.Properties;

import jp.ossc.nimbus.core.*;

/**
 * {@link MethodMappingInterceptorService}��MBean�C���^�t�F�[�X<p>
 * 
 * @author M.Takata
 * @see MethodMappingInterceptorService
 */
public interface MethodMappingInterceptorServiceMBean extends ServiceBaseMBean{
    
    /**
     * ���\�b�h�ƃC���^�[�Z�v�^�T�[�r�X���̃}�b�s���O��ݒ肷��B<p>
     * �w�肳�ꂽ���\�b�h���Ăяo���ꂽ�ꍇ�ɁA�Ή�����C���^�[�Z�v�^�T�[�r�X���Ăяo���悤�ɐݒ肷��B<br>
     * �N���X��#���\�b�h��(�����^,�����^,�c)=�C���^�[�Z�v�^�T�[�r�X��<br>
     * �N���X���A���\�b�h���A�����^�ɂ́A���K�\�����w�肷�鎖���ł���B�܂��A��������v���邩��r���Ȃ��ꍇ�́A*���w�肷��B<br>
     * ��v����}�b�s���O����������ꍇ�̓���͕ۏ؂��Ȃ��B<br>
     *
     * @param mapping ���\�b�h�ƃC���^�[�Z�v�^�T�[�r�X���̃}�b�s���O
     */
    public void setTargetMethodMapping(Properties mapping);
    
    /**
     * ���\�b�h�ƃC���^�[�Z�v�^�T�[�r�X���̃}�b�s���O���擾����B<p>
     *
     * @return ���\�b�h�ƃC���^�[�Z�v�^�T�[�r�X���̃}�b�s���O
     */
    public Properties getTargetMethodMapping();
    
    /**
     * ���\�b�h�ƃR���e�L�X�g�L�[���̃}�b�s���O��ݒ肷��B<p>
     * �w�肳�ꂽ���\�b�h���Ăяo���ꂽ�ꍇ�ɁA�Ή�����R���e�L�X�g�L�[���̒l���R���e�L�X�g����擾���ĕԂ��悤�ɐݒ肷��B<br>
     * �N���X��#���\�b�h��(�����^,�����^,�c)=�R���e�L�X�g�L�[��<br>
     * �N���X���A���\�b�h���A�����^�ɂ́A���K�\�����w�肷�鎖���ł���B�܂��A��������v���邩��r���Ȃ��ꍇ�́A*���w�肷��B<br>
     * ��v����}�b�s���O����������ꍇ�̓���͕ۏ؂��Ȃ��B<br>
     *
     * @param mapping ���\�b�h�ƃR���e�L�X�g�L�[���̃}�b�s���O
     */
    public void setTargetMethodReturnMapping(Properties mapping);
    
    /**
     * ���\�b�h�ƃR���e�L�X�g�L�[���̃}�b�s���O���擾����B<p>
     *
     * @return ���\�b�h�ƃR���e�L�X�g�L�[���̃}�b�s���O
     */
    public Properties getTargetMethodReturnMapping();
    
    /**
     * �R���e�L�X�g�T�[�r�X����ݒ肷��B<p>
     *
     * @param name �R���e�L�X�g�T�[�r�X��
     */
    public void setContextServiceName(ServiceName name);
    
    /**
     * �R���e�L�X�g�T�[�r�X�����擾����B<p>
     *
     * @return �R���e�L�X�g�T�[�r�X��
     */
    public ServiceName getContextServiceName();
}
