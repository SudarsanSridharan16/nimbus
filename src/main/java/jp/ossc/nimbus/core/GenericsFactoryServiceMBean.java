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
package jp.ossc.nimbus.core;

/**
 * {@link GenericsFactoryService}��MBean�C���^�t�F�[�X<p>
 * 
 * @author M.Takata
 * @see GenericsFactoryService
 */
public interface GenericsFactoryServiceMBean
 extends FactoryServiceBaseMBean{
    
    /**
     * ��������I�u�W�F�N�g�̃N���X��ݒ肷��B<p>
     *
     * @param clazz ��������I�u�W�F�N�g�̃N���X
     */
    public void setInstantiateClass(Class clazz);
    
    /**
     * ��������I�u�W�F�N�g�̃N���X���擾����B<p>
     *
     * @return ��������I�u�W�F�N�g�̃N���X
     */
    public Class getInstantiateClass();
    
    /**
     * {@link ServiceName}�^�̑������ݒ肳�ꂽ�ꍇ�ɁA��������I�u�W�F�N�g�̊Y�����鑮���ɁA�Y������T�[�r�X���擾���Đݒ肷�邩�ǂ�����ݒ肷��B<p>
     *
     * @param flg �C���W�F�N�V��������ꍇ��true
     */
    public void setServiceInjection(boolean flg);
    
    /**
     * {@link ServiceName}�^�̑������ݒ肳�ꂽ�ꍇ�ɁA��������I�u�W�F�N�g�̊Y�����鑮���ɁA�Y������T�[�r�X���擾���Đݒ肷�邩�ǂ����𔻒肷��B<p>
     *
     * @return true�̏ꍇ�́A�C���W�F�N�V��������
     */
    public boolean isServiceInjection();
    
    /**
     * �w�肳�ꂽ�����̒l���擾����B<p>
     *
     * @param attributeName ������
     * @return �w�肳�ꂽ�����̒l
     */
    public Object getAttribute(String attributeName);
    
    /**
     * �w�肳�ꂽ�T�[�r�X�������̃T�[�r�X���擾����B<p>
     *
     * @param attributeName �T�[�r�X��������
     * @return �w�肳�ꂽ�T�[�r�X�������̃T�[�r�X
     * @exception ServiceNotFoundException �w�肳�ꂽ�T�[�r�X�������̃T�[�r�X��������Ȃ��ꍇ
     */
    public Service getService(String attributeName)
     throws ServiceNotFoundException;
    
    /**
     * �w�肳�ꂽ�T�[�r�X�������̃T�[�r�X�I�u�W�F�N�g���擾����B<p>
     *
     * @param attributeName �T�[�r�X��������
     * @return �w�肳�ꂽ�T�[�r�X�������̃T�[�r�X�I�u�W�F�N�g
     * @exception ServiceNotFoundException �w�肳�ꂽ�T�[�r�X�������̃T�[�r�X�I�u�W�F�N�g��������Ȃ��ꍇ
     */
    public Object getServiceObject(String attributeName)
     throws ServiceNotFoundException;
}