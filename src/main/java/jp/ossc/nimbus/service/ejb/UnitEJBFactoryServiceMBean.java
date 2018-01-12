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
package jp.ossc.nimbus.service.ejb;

/**
 * {@link UnitEJBFactoryService}��MBean�C���^�t�F�[�X<p>
 * 
 * @author M.Takata
 * @see UnitEJBFactoryService
 */
public interface UnitEJBFactoryServiceMBean extends InvocationEJBFactoryServiceMBean{
    
    /**
     * ����EJB�t�@�N�g���Ő�������EJB��EJBHome�̊��S�C���N���X�����擾����B<p>
     * �ݒ肳��ĂȂ��ꍇ�́Ajavax.ejb.EJBHome��Ԃ��B
     *
     * @return EJBHome�̊��S�C���N���X��
     * @see #setHomeType(String)
     */
    public String getHomeType();
    
    /**
     * ����EJB�t�@�N�g���Ő�������EJB��EJBHome�̊��S�C���N���X����ݒ肷��B<p>
     *
     * @param className EJBHome�̊��S�C���N���X��
     * @see #getHomeType()
     */
    public void setHomeType(String className);
    
    /**
     * ����EJB�t�@�N�g���Ő�������EJB��EJBLocalHome�̊��S�C���N���X�����擾����B<p>
     * �ݒ肳��ĂȂ��ꍇ�́Ajavax.ejb.EJBLocalHome��Ԃ��B
     *
     * @return EJBLocalHome�̊��S�C���N���X��
     * @see #setLocalHomeType(String)
     */
    public String getLocalHomeType();
    
    /**
     * ����EJB�t�@�N�g���Ő�������EJB��EJBLocalHome�̊��S�C���N���X����ݒ肷��B<p>
     *
     * @param className EJBLocalHome�̊��S�C���N���X��
     * @see #getLocalHomeType()
     */
    public void setLocalHomeType(String className);
    
    /**
     * ����EJB�t�@�N�g���Ő�������EJB��EJBObject�̊��S�C���N���X�����擾����B<p>
     * �ݒ肳��ĂȂ��ꍇ�́Ajavax.ejb.EJBObject��Ԃ��B
     *
     * @return EJBObject�̊��S�C���N���X��
     * @see #setRemoteType(String)
     */
    public String getRemoteType();
    
    /**
     * ����EJB�t�@�N�g���Ő�������EJB��EJBObject�̊��S�C���N���X����ݒ肷��B<p>
     *
     * @param className EJBObject�̊��S�C���N���X��
     * @see #getRemoteType()
     */
    public void setRemoteType(String className);
    
    /**
     * ����EJB�t�@�N�g���Ő�������EJB��EJBLocalObject�̊��S�C���N���X�����擾����B<p>
     * �ݒ肳��ĂȂ��ꍇ�́Ajavax.ejb.EJBLocalObject��Ԃ��B
     *
     * @return EJBObject�̊��S�C���N���X��
     * @see #setLocalType(String)
     */
    public String getLocalType();
    
    /**
     * ����EJB�t�@�N�g���Ő�������EJB��EJBLocalObject�̊��S�C���N���X����ݒ肷��B<p>
     *
     * @param className EJBLocalObject�̊��S�C���N���X��
     * @see #getLocalType()
     */
    public void setLocalType(String className);
    
    /**
     * ����EJB�t�@�N�g���Ő�������EJB��EJBHome�y��EJBLocalHome��create���\�b�h�ɓn�������̊��S�C���N���X���𕶎���z��Ƃ��Ď擾����B<p>
     * �ݒ肳��ĂȂ��ꍇ�́Anull��Ԃ��B
     *
     * @return EJBHome�y��EJBLocalHome��create���\�b�h�ɓn�������̊��S�C���N���X���̕�����z��
     * @see #setCreateMethodParamTypes(String[])
     */
    public String[] getCreateMethodParamTypes();
    
    /**
     * ����EJB�t�@�N�g���Ő�������EJB��EJBHome�y��EJBLocalHome��create���\�b�h�ɓn�������̊��S�C���N���X���𕶎���z��Ƃ��Đݒ肷��B<p>
     * �������Ȃ��ꍇ�́A�ݒ肷��K�v�͂Ȃ��B
     *
     * @param params EJBHome�y��EJBLocalHome��create���\�b�h�ɓn�������̊��S�C���N���X���̕�����z��
     * @see #getCreateMethodParamTypes()
     */
    public void setCreateMethodParamTypes(String[] params);
}
