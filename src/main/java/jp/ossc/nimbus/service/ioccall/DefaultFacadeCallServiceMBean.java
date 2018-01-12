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
package jp.ossc.nimbus.service.ioccall;

import java.util.Map;

import jp.ossc.nimbus.core.ServiceBaseMBean;
import jp.ossc.nimbus.core.*;

/**
 * {@link DefaultFacadeCallService}��MBean�C���^�t�F�[�X�B<p>
 * 
 * @version $Name:  $
 * @author H.Nakano
 * @since 1.0
 */
public interface DefaultFacadeCallServiceMBean extends ServiceBaseMBean{
    
    public static final String DELIVERY_MODE_PERSISTENT = "PERSISTENT";
    public static final String DELIVERY_MODE_NON_PERSISTENT = "NON_PERSISTENT";
    
    /**
     * Nimbus IOC ��Facade EJB�̎Q�Ƃ��擾����{@link jp.ossc.nimbus.service.ejb.EJBFactory EJBFactory}�T�[�r�X�̃T�[�r�X����ݒ肷��B<p>
     * 
     * @param name EJBFactory�T�[�r�X�̃T�[�r�X��
     */
    public void setEjbFactoryServieName(ServiceName name);
    
    /**
     * Nimbus IOC ��Facade EJB�̎Q�Ƃ��擾����{@link jp.ossc.nimbus.service.ejb.EJBFactory EJBFactory}�T�[�r�X�̃T�[�r�X�����擾����B<p>
     * 
     * @return EJBFactory�T�[�r�X�̃T�[�r�X��
     */
    public ServiceName getEjbFactoryServieName();
    
    /**
     * Nimbus IOC ��Facade EJB�̖��O��ݒ肷��B<p>
     * {@link #setEjbFactoryServieName(ServiceName)}�Őݒ肳�ꂽEJBFactory�T�[�r�X�ŁANimbus IOC ��Facade EJB�̎Q�Ƃ��擾����ۂɁA�����œn��EJB�̖��O��ݒ肷��B<br>
     * �f�t�H���g�́A�󕶎��B<br>
     * 
     * @param name Nimbus IOC ��Facade EJB�̖��O
     */
    public void setFacadeEjbName(String name);
    
    /**
     * Nimbus IOC ��Facade EJB�̖��O���擾����B<p>
     * 
     * @return Nimbus IOC ��Facade EJB�̖��O
     */
    public String getFacadeEjbName();
    
    /**
     * Nimbus IOC ��UnitOfWork EJB�̎Q�Ƃ��擾����{@link jp.ossc.nimbus.service.ejb.EJBFactory EJBFactory}�T�[�r�X�̃T�[�r�X����ݒ肷��B<p>
     * 
     * @param name EJBFactory�T�[�r�X�̃T�[�r�X��
     */
    public void setUnitOfWorkEjbFactoryServieName(ServiceName name);
    
    /**
     * Nimbus IOC ��UnitOfWork EJB�̎Q�Ƃ��擾����{@link jp.ossc.nimbus.service.ejb.EJBFactory EJBFactory}�T�[�r�X�̃T�[�r�X�����擾����B<p>
     * 
     * @return EJBFactory�T�[�r�X�̃T�[�r�X��
     */
    public ServiceName getUnitOfWorkEjbFactoryServieName();
    
    /**
     * Nimbus IOC ��UnitOfWork EJB�̖��O��ݒ肷��B<p>
     * {@link #setUnitOfWorkEjbFactoryServieName(ServiceName)}�Őݒ肳�ꂽEJBFactory�T�[�r�X�ŁANimbus IOC ��UnitOfWork EJB�̎Q�Ƃ��擾����ۂɁA�����œn��EJB�̖��O��ݒ肷��B<br>
     * �f�t�H���g�́A�󕶎��B<br>
     * 
     * @param name Nimbus IOC ��UnitOfWork EJB�̖��O
     */
    public void setUnitOfWorkEjbName(String name);
    
    /**
     * Nimbus IOC ��UnitOfWork EJB�̖��O���擾����B<p>
     * 
     * @return Nimbus IOC ��UnitOfWork EJB�̖��O
     */
    public String getUnitOfWorkEjbName();
    
    /**
     * Nimbus IOC ��Command EJB�̎Q�Ƃ��擾����{@link jp.ossc.nimbus.service.ejb.EJBFactory EJBFactory}�T�[�r�X�̃T�[�r�X����ݒ肷��B<p>
     * 
     * @param name EJBFactory�T�[�r�X�̃T�[�r�X��
     */
    public void setCommandEjbFactoryServieName(ServiceName name) ;
    
    /**
     * Nimbus IOC ��Command EJB�̎Q�Ƃ��擾����{@link jp.ossc.nimbus.service.ejb.EJBFactory EJBFactory}�T�[�r�X�̃T�[�r�X�����擾����B<p>
     * 
     * @return EJBFactory�T�[�r�X�̃T�[�r�X��
     */
    public ServiceName getCommandEjbFactoryServieName();
    
    /**
     * Nimbus IOC ��Command EJB�̖��O��ݒ肷��B<p>
     * {@link #setCommandEjbFactoryServieName(ServiceName)}�Őݒ肳�ꂽEJBFactory�T�[�r�X�ŁANimbus IOC ��Command EJB�̎Q�Ƃ��擾����ۂɁA�����œn��EJB�̖��O��ݒ肷��B<br>
     * �f�t�H���g�́A�󕶎��B<br>
     * 
     * @param name Nimbus IOC ��Command EJB�̖��O
     */
    public void setCommandEjbName(String name);
    
    /**
     * Nimbus IOC ��Command EJB�̖��O���擾����B<p>
     * 
     * @return Nimbus IOC ��Command EJB�̖��O
     */
    public String getCommandEjbName();
    
    /**
     * {@link jp.ossc.nimbus.service.queue.Queue Queue}�T�[�r�X�̃T�[�r�X����ݒ肷��B<p>
     * �ݒ肳�ꂽQueue�T�[�r�X�ŁA���s�����y�є񓯊��������s���B<br>
     * ���s�����y�є񓯊����������Ȃ��ꍇ�́A�ݒ肷��K�v�͂Ȃ��B<br>
     *
     * @param name Queue�T�[�r�X�̃T�[�r�X��
     */
    public void setQueueServiceName(ServiceName name);
    
    /**
     * {@link jp.ossc.nimbus.service.queue.Queue Queue}�T�[�r�X�̃T�[�r�X�����擾����B<p>
     *
     * @return Queue�T�[�r�X�̃T�[�r�X��
     */
    public ServiceName getQueueServiceName();
    
    /**
     * {@link jp.ossc.nimbus.service.jndi.JndiFinder JndiFinder}�T�[�r�X�̃T�[�r�X����ݒ肷��B<p>
     * �ݒ肳�ꂽJndiFinder�T�[�r�X�ŁA���s�����y�є񓯊������Ɏg�p����JMS Queue���擾����B<br>
     * ���s�����y�є񓯊����������Ȃ��ꍇ�́A�ݒ肷��K�v�͂Ȃ��B<br>
     *
     * @param name JndiFinder�T�[�r�X�̃T�[�r�X��
     */
    public void setQueueFinderServiceName(ServiceName name);
    
    /**
     * {@link jp.ossc.nimbus.service.jndi.JndiFinder JndiFinder}�T�[�r�X�̃T�[�r�X�����擾����B<p>
     *
     * @return JndiFinder�T�[�r�X�̃T�[�r�X��
     */
    public ServiceName  getQueueFinderServiceName();
    
    /**
     * JMS Queue�̃L���[����ݒ肷��B<p>
     * {@link #setQueueFinderServiceName(ServiceName)}�Őݒ肳�ꂽ{@link jp.ossc.nimbus.service.jndi.JndiFinder JndiFinder}�T�[�r�X���g���āAJMS Queue��lookup����ۂɎg�p���閼�O��ݒ肷��B<br>
     * lookup���ꂽQueue�́A���s�����y�є񓯊������Ɏg�p����B<br>
     * ���s�����y�є񓯊����������Ȃ��ꍇ�́A�ݒ肷��K�v�͂Ȃ��B<br>
     *
     * @param name JMS Queue�̃L���[��
     */
    public void setQueueName(String name);
    
    /**
     * JMS Queue�̃L���[�����擾����B<p>
     * 
     * @return JMS Queue�̃L���[��
     */
    public String getQueueName();
    
    /**
     * JMS QueueSession�𐶐�����{@link jp.ossc.nimbus.service.resource.ResourceFactory ResourceFactory}�T�[�r�X�̃T�[�r�X����ݒ肷��B<p>
     * �ݒ肳�ꂽResourceFactory�T�[�r�X�ŁA���s�����y�є񓯊������Ɏg�p����JMS QueueSession�𐶐�����B<br>
     * ���s�����y�є񓯊����������Ȃ��ꍇ�́A�ݒ肷��K�v�͂Ȃ��B<br>
     * 
     * @param name JMS QueueSession�𐶐�����ResourceFactory�T�[�r�X�̃T�[�r�X��
     */
    public void setQueueSessionFactoryServiceName(ServiceName name);
    
    /**
     * JMS QueueSession�𐶐�����{@link jp.ossc.nimbus.service.resource.ResourceFactory ResourceFactory}�T�[�r�X�̃T�[�r�X�����擾����B<p>
     * 
     * @return JMS QueueSession�𐶐�����ResourceFactory�T�[�r�X�̃T�[�r�X��
     */
    public ServiceName getQueueSessionFactoryServiceName();
    
    /**
     * JMS Queue�ɑ��M����ۂ̔z�M���[�h��ݒ肷��B<p>
     * ���s�����y�є񓯊����������Ȃ��ꍇ�́A�ݒ肷��K�v�͂Ȃ��B<br>
     *
     * @param mode �z�M���[�h
     * @see #DELIVERY_MODE_PERSISTENT
     * @see #DELIVERY_MODE_NON_PERSISTENT
     */
    public void setDeliveryMode(String mode);
    
    /**
     * JMS Queue�ɑ��M����ۂ̔z�M���[�h���擾����B<p>
     *
     * @return �z�M���[�h
     */
    public String getDeliveryMode();
    
    /**
     * JMS Queue�ɑ��M����ۂ̃��b�Z�[�W�D�揇�ʂ�ݒ肷��B<p>
     * ���s�����y�є񓯊����������Ȃ��ꍇ�́A�ݒ肷��K�v�͂Ȃ��B<br>
     *
     * @param priority ���b�Z�[�W�D�揇��
     */
    public void setPriority(int priority);
    
    /**
     * JMS Queue�ɑ��M����ۂ̃��b�Z�[�W�D�揇�ʂ��擾����B<p>
     *
     * @return ���b�Z�[�W�D�揇��
     */
    public int getPriority();
    
    /**
     * JMS Queue�ɑ��M����ۂ̃��b�Z�[�W������ݒ肷��B<p>
     * ���s�����y�є񓯊����������Ȃ��ꍇ�́A�ݒ肷��K�v�͂Ȃ��B<br>
     *
     * @param millis ���b�Z�[�W����[ms]
     */
    public void setTimeToLive(long millis);
    
    /**
     * JMS Queue�ɑ��M����ۂ̃��b�Z�[�W�������擾����B<p>
     *
     * @return ���b�Z�[�W����[ms]
     */
    public long getTimeToLive();
    
    /**
     * �X���b�h�R���e�L�X�g�T�[�r�X�̃T�[�r�X����ݒ肷��B<p>
     * �X���b�h�R���e�L�X�g�ɐݒ肳��Ă���R���e�L�X�g����{@link jp.ossc.nimbus.ioc.FacadeValue FacadeValue}�̃w�b�_���ڂƂ��Đݒ肷��B<br>
     *
     * @param name �X���b�h�R���e�L�X�g�T�[�r�X�̃T�[�r�X��
     */
    public void setThreadContextServiceName(ServiceName name);
    
    /**
     * �X���b�h�R���e�L�X�g�T�[�r�X�̃T�[�r�X�����擾����B<p>
     *
     * @return �X���b�h�R���e�L�X�g�T�[�r�X�̃T�[�r�X��
     */
    public ServiceName getThreadContextServiceName();
    
    /**
     * {@link jp.ossc.nimbus.ioc.FacadeValue FacadeValue}�̃w�b�_���ڂɐݒ肷��X���b�h�R���e�L�X�g�L�[����ݒ肷��B<p>
     * ���̑�����ݒ肵�Ȃ��ꍇ�́A�S�ẴR���e�L�X�g����FacadeValue�̃w�b�_���ڂɐݒ肷��B<br>
     *
     * @param keys �X���b�h�R���e�L�X�g�L�[���z��
     */
    public void setThreadContextKeys(String[] keys);
    
    /**
     * {@link jp.ossc.nimbus.ioc.FacadeValue FacadeValue}�̃w�b�_���ڂɐݒ肷��X���b�h�R���e�L�X�g�L�[�����擾����B<p>
     *
     * @return �X���b�h�R���e�L�X�g�L�[���z��
     */
    public String[] getThreadContextKeys();
    
    /**
     * JMS Queue���g�p���Ĕ񓯊�IOC�Ăяo��������ꍇ�ɁAJMS��Message�ɐݒ肷��v���p�e�B��ݒ肷��B<p>
     *
     * @param name �v���p�e�B��
     * @param value �l
     */
    public void setJMSMessageProperty(String name, Object value);
    
    /**
     * JMS Queue���g�p���Ĕ񓯊�IOC�Ăяo��������ꍇ�ɁAJMS��Message�ɐݒ肷��v���p�e�B���擾����B<p>
     *
     * @param name �v���p�e�B��
     * @return �l
     */
    public Object getJMSMessageProperty(String name);
    
    /**
     * JMS Queue���g�p���Ĕ񓯊�IOC�Ăяo��������ꍇ�ɁAJMS��Message�ɐݒ肷��v���p�e�B���擾����B<p>
     *
     * @return �v���p�e�B���ƒl�̃}�b�v
     */
    public Map getJMSMessageProperties();
    
    /**
     * JMS Queue���g�p���Ĕ񓯊�IOC�Ăяo��������ꍇ�ɁA���͂ł���{@link jp.ossc.nimbus.ioc.FacadeValue}�̃w�b�_��JMS��Message�v���p�e�B�Ƃ��Đݒ肷��w�b�_���z���ݒ肷��B<p>
     *
     * @param names �w�b�_���z��
     */
    public void setHeaderNamesForJMSMessageProperty(String[] names);
    
    /**
     * JMS Queue���g�p���Ĕ񓯊�IOC�Ăяo��������ꍇ�ɁA���͂ł���{@link jp.ossc.nimbus.ioc.FacadeValue}�̃w�b�_��JMS��Message�v���p�e�B�Ƃ��Đݒ肷��w�b�_���z����擾����B<p>
     *
     * @return �w�b�_���z��
     */
    public String[] getHeaderNamesForJMSMessageProperty();
    
    /**
     * JMS Queue���g�p���Ĕ񓯊�IOC�Ăяo��������ꍇ��JMS��Message�ɐݒ肷��JMS�^�C�v��ݒ肷��B<p>
     *
     * @param type JMS�^�C�v
     */
    public void setJMSType(String type);
    
    /**
     * JMS Queue���g�p���Ĕ񓯊�IOC�Ăяo��������ꍇ��JMS��Message�ɐݒ肷��JMS�^�C�v���擾����B<p>
     *
     * @return JMS�^�C�v
     */
    public String getJMSType();
    
    /**
     * JMS Queue���g�p���Ĕ񓯊�IOC�Ăяo��������ꍇ��JMS��Message�ɐݒ肷��L������[ms]��ݒ肷��B<p>
     *
     * @param expiration �L������[ms]
     */
    public void setJMSExpiration(long expiration);
    
    /**
     * JMS Queue���g�p���Ĕ񓯊�IOC�Ăяo��������ꍇ��JMS��Message�ɐݒ肷��L������[ms]���擾����B<p>
     *
     * @return �L������[ms]
     */
    public long getJMSExpiration();
    
    /**
     * EJB���[�J���Ăяo�����s�����ǂ�����ݒ肷��B<p>
     * �f�t�H���g�́Afalse�Ń����[�g�Ăяo������B<br>
     *
     * @param isLocal EJB���[�J���Ăяo�����s���ꍇtrue
     */
    public void setLocal(boolean isLocal);
    
    /**
     * EJB���[�J���Ăяo�����s�����ǂ����𔻒肷��B<p>
     *
     * @return true�̏ꍇ�AEJB���[�J���Ăяo�����s��
     */
    public boolean isLocal();
}
