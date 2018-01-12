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
package jp.ossc.nimbus.service.transaction;

import java.lang.reflect.*;

import jp.ossc.nimbus.core.*;

/**
 * {@link ReflectionTransactionManagerFactoryService}��MBean�C���^�t�F�[�X<p>
 * 
 * @author M.Takata
 * @see ReflectionTransactionManagerFactoryService
 */
public interface ReflectionTransactionManagerFactoryServiceMBean
 extends ServiceBaseMBean{
    
    /**
     * TransactionManager�𐶐�����t�@�N�g���N���X�̃R���X�g���N�^��ݒ肷��B<p>
     * �t�@�N�g���N���X��static���\�b�h���Ăԏꍇ�́A�w�肷��K�v�͂Ȃ��B<br>
     * 
     * @param c �t�@�N�g���N���X�̃R���X�g���N�^
     */
    public void setFactoryConstructor(Constructor c);
    
    /**
     * TransactionManager�𐶐�����t�@�N�g���N���X�̃R���X�g���N�^���擾����B<p>
     * 
     * @return �t�@�N�g���N���X�̃R���X�g���N�^
     */
    public Constructor getFactoryConstructor();
    
    /**
     * TransactionManager�𐶐�����t�@�N�g���N���X�̃R���X�g���N�^�̈�����ݒ肷��B<p>
     * 
     * @param params �t�@�N�g���N���X�̃R���X�g���N�^�̈���
     */
    public void setFactoryConstructorParameters(Object[] params);
    
    /**
     * TransactionManager�𐶐�����t�@�N�g���N���X�̃R���X�g���N�^�̈������擾����B<p>
     * 
     * @return �t�@�N�g���N���X�̃R���X�g���N�^�̈���
     */
    public Object[] getFactoryConstructorParameters();
    
    /**
     * TransactionManager�𐶐�����t�@�N�g���N���X�̃t�@�N�g�����\�b�h��ݒ肷��B<p>
     * 
     * @param m �t�@�N�g���N���X�̃t�@�N�g�����\�b�h
     */
    public void setFactoryMethod(Method m);
    
    /**
     * TransactionManager�𐶐�����t�@�N�g���N���X�̃t�@�N�g�����\�b�h���擾����B<p>
     * 
     * @return �t�@�N�g���N���X�̃t�@�N�g�����\�b�h
     */
    public Method getFactoryMethod();
    
    /**
     * TransactionManager�𐶐�����t�@�N�g���N���X�̃t�@�N�g�����\�b�h�̈�����ݒ肷��B<p>
     * 
     * @param params �t�@�N�g���N���X�̃t�@�N�g�����\�b�h�̈���
     */
    public void setFactoryMethodParameters(Object[] params);
    
    /**
     * TransactionManager�𐶐�����t�@�N�g���N���X�̃t�@�N�g�����\�b�h�̈������擾����B<p>
     * 
     * @return �t�@�N�g���N���X�̃t�@�N�g�����\�b�h�̈���
     */
    public Object[] getFactoryMethodParameters();
    
    /**
     * �t�@�N�g���̃C���X�^���X��ݒ肷��B<p>
     *
     * @param fac �t�@�N�g��
     */
    public void setFactory(Object fac);
    
    /**
     * �t�@�N�g���̃C���X�^���X���擾����B<p>
     *
     * @return �t�@�N�g��
     */
    public Object getFactory();
    
    /**
     * TransactionManager�N���X�̃R���X�g���N�^��ݒ肷��B<p>
     * static���\�b�h���Ăԏꍇ�́A�w�肷��K�v�͂Ȃ��B<br>
     * 
     * @param c �R���X�g���N�^
     */
    public void setTransactionManagerConstructor(Constructor c);
    
    /**
     * TransactionManager�N���X�̃R���X�g���N�^���擾����B<p>
     * 
     * @return �R���X�g���N�^
     */
    public Constructor getTransactionManagerConstructor();
    
    /**
     * TransactionManager�N���X�̃R���X�g���N�^�̈�����ݒ肷��B<p>
     * 
     * @param params �R���X�g���N�^�̈���
     */
    public void setTransactionManagerConstructorParameters(Object[] params);
    
    /**
     * TransactionManager�N���X�̃R���X�g���N�^�̈������擾����B<p>
     * 
     * @return �R���X�g���N�^�̈���
     */
    public Object[] getTransactionManagerConstructorParameters();
}
