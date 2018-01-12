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
package jp.ossc.nimbus.service.codemaster;

import java.io.IOException;
import java.util.Map;
import java.util.Properties;

import jp.ossc.nimbus.core.*;

/**
 * {@link CodeMasterService}��MBean�C���^�t�F�[�X�B<p>
 *
 * @author S.Yoshihara
 */
public interface CodeMasterServiceMBean extends ServiceBaseMBean, CodeMasterFinder {

    /**
     * �}�X�^���z���ݒ肷��B<p>
     *
     * @param names �}�X�^���̔z��
     */
    public void setMasterNames(String[] names);

    /**
     * �}�X�^���z����擾����B<p>
     *
     * @return �}�X�^���̔z��
     */
    public String[] getMasterNames();

    /**
     * �}�X�^�擾�̂��߂�IOC�Ăяo�����s�����߂�{@link jp.ossc.nimbus.service.ioccall.FacadeCaller FacadeCaller}�T�[�r�X�̃T�[�r�X����ݒ肷��B<p>
     * ���̑�����{@link #setBeanFlowInvokerFactoryServiceName(ServiceName)}�̂ǂ��炩����́A�K���ݒ肵�Ȃ���΂Ȃ�Ȃ��B<br>
     *
     * @param name FacadeCaller�T�[�r�X�̃T�[�r�X��
     */
    public void setFacadeCallerServiceName(ServiceName name);

    /**
     * �}�X�^�擾�̂��߂�IOC�Ăяo�����s�����߂�{@link jp.ossc.nimbus.service.ioccall.FacadeCaller FacadeCaller}�T�[�r�X�̃T�[�r�X�����擾����B<p>
     *
     * @return FacadeCaller�T�[�r�X�̃T�[�r�X��
     */
    public ServiceName getFacadeCallerServiceName();

    /**
     * {@link jp.ossc.nimbus.service.sequence.Sequence Sequence}�T�[�r�X�̃T�[�r�X����ݒ肷��B<p>
     * �}�X�^�擾�̂��߂�IOC�Ăяo�����s���ꍇ�ɁA{@link jp.ossc.nimbus.ioc.FacadeValue FacadeValue}�̃w�b�_�Ƀ��N�G�X�gID��t������̂Ɏg�p����B<br>
     *
     * @param name Sequence�T�[�r�X�̃T�[�r�X��
     */
    public void setSequenceServiceName(ServiceName name);

    /**
     * {@link jp.ossc.nimbus.service.sequence.Sequence Sequence}�T�[�r�X�̃T�[�r�X�����擾����B<p>
     *
     * @return Sequence�T�[�r�X�̃T�[�r�X��
     */
    public ServiceName getSequenceServiceName();

    /**
     * ���[�UID��ݒ肷��B<p>
     * �}�X�^�擾�̂��߂�IOC�Ăяo�����s���ꍇ�ɁA{@link jp.ossc.nimbus.ioc.FacadeValue FacadeValue}�̃w�b�_�Ƀ��[�UID��t������̂Ɏg�p����B<br>
     *
     * @param id ���[�UID
     */
    public void setUserId(String id);

    /**
     * ���[�UID���擾����B<p>
     *
     * @return ���[�UID
     */
    public String getUserId();

    /**
     * �}�X�^�擾�̂��߂�{@link jp.ossc.nimbus.service.beancontrol.interfaces.BeanFlowInvoker BeanFlowInvoker}�Ăяo�����s�����߂�{@link jp.ossc.nimbus.service.beancontrol.interfaces.BeanFlowInvokerFactory BeanFlowInvokerFactory}�T�[�r�X�̃T�[�r�X����ݒ肷��B<p>
     * ���̑�����{@link #setFacadeCallerServiceName(ServiceName)}�̂ǂ��炩����́A�K���ݒ肵�Ȃ���΂Ȃ�Ȃ��B<br>
     *
     * @param name BeanFlowInvokerFactory�T�[�r�X�̃T�[�r�X��
     */
    public void setBeanFlowInvokerFactoryServiceName(ServiceName name);

    /**
     * �}�X�^�擾�̂��߂�{@link jp.ossc.nimbus.service.beancontrol.interfaces.BeanFlowInvoker BeanFlowInvoker}�Ăяo�����s�����߂�{@link jp.ossc.nimbus.service.beancontrol.interfaces.BeanFlowInvokerFactory BeanFlowInvokerFactory}�T�[�r�X�̃T�[�r�X�����擾����B<p>
     *
     * @return BeanFlowInvokerFactory�T�[�r�X�̃T�[�r�X��
     */
    public ServiceName getBeanFlowInvokerFactoryServiceName();

    /**
     * JMS�g�s�b�N����M����javax.jms.TopicSubscriber�𐶐�����{@link jp.ossc.nimbus.service.jms.JMSMessageConsumerFactory}�T�[�r�X�̃T�[�r�X����ݒ肷��B<p>
     * JMS�g�s�b�N�ł̃}�X�^�X�V���s���ꍇ�Ɏg�p����BJMS�g�s�b�N�ł̃}�X�^�X�V���s��Ȃ��ꍇ�́A�ݒ肷��K�v�͂Ȃ��B<br>
     *
     * @param name JMSMessageConsumerFactory�T�[�r�X�̃T�[�r�X��
     */
    public void setJMSTopicSubscriberFactoryServiceName(ServiceName name);

    /**
     * JMS�g�s�b�N����M����javax.jms.TopicSubscriber�𐶐�����{@link jp.ossc.nimbus.service.jms.JMSMessageConsumerFactory}�T�[�r�X�̃T�[�r�X�����擾����B<p>
     *
     * @return JMSMessageConsumerFactory�T�[�r�X�̃T�[�r�X��
     */
    public ServiceName getJMSTopicSubscriberFactoryServiceName();

    /**
     * JMS�g�s�b�N����M����javax.jms.TopicSubscriber�𐶐�����{@link jp.ossc.nimbus.service.jms.JMSMessageConsumerFactory}�T�[�r�X�̃T�[�r�X����ݒ肷��B<p>
     * JMS�g�s�b�N�ł̃}�X�^�X�V���s���ꍇ�Ɏg�p����BJMS�g�s�b�N�ł̃}�X�^�X�V���s��Ȃ��ꍇ�́A�ݒ肷��K�v�͂Ȃ��B<br>
     *
     * @param names JMSMessageConsumerFactory�T�[�r�X�̃T�[�r�X���z��
     */
    public void setJMSTopicSubscriberFactoryServiceNames(ServiceName[] names);

    /**
     * JMS�g�s�b�N����M����javax.jms.TopicSubscriber�𐶐�����{@link jp.ossc.nimbus.service.jms.JMSMessageConsumerFactory}�T�[�r�X�̃T�[�r�X�����擾����B<p>
     *
     * @return JMSMessageConsumerFactory�T�[�r�X�̃T�[�r�X���z��
     */
    public ServiceName[] getJMSTopicSubscriberFactoryServiceNames();

    /**
     * {@link jp.ossc.nimbus.service.publish.MessageReceiver MessageReceiver}�T�[�r�X����{@link jp.ossc.nimbus.service.publish.Message Message}����M����ۂ̃T�u�W�F�N�g����ݒ肷��B<p>
     *
     * @param subject �T�u�W�F�N�g��
     */
    public void setSubjects(String[] subject);

    /**
     * {@link jp.ossc.nimbus.service.publish.MessageReceiver MessageReceiver}�T�[�r�X����{@link jp.ossc.nimbus.service.publish.Message Message}����M����ۂ̃T�u�W�F�N�g�����擾����B<p>
     *
     * @return �T�u�W�F�N�g��
     */
    public String[] getSubjects();

    /**
     * {@link jp.ossc.nimbus.service.publish.Message Message}����M����{@link jp.ossc.nimbus.service.publish.MessageReceiver MessageReceiver}�T�[�r�X�̃T�[�r�X����ݒ肷��B<p>
     * Message�ł̃}�X�^�X�V���s���ꍇ�Ɏg�p����BMessage�ł̃}�X�^�X�V���s��Ȃ��ꍇ�́A�ݒ肷��K�v�͂Ȃ��B<br>
     *
     * @param name MessageReceiver�T�[�r�X�̃T�[�r�X��
     */
    public void setMessageReceiverServiceName(ServiceName name);

    /**
     * {@link jp.ossc.nimbus.service.publish.Message Message}����M����{@link jp.ossc.nimbus.service.publish.MessageReceiver MessageReceiver}�T�[�r�X�̃T�[�r�X�����擾����B<p>
     *
     * @return MessageReceiver�T�[�r�X�̃T�[�r�X��
     */
    public ServiceName getMessageReceiverServiceName();

    /**
     * JMS�g�s�b�N�Ń}�X�^�X�V���s���ꍇ�̃}�X�^���ƒʒm�}�X�^���̃}�b�s���O��ݒ肷��B<p>
     * �}�X�^���ƒʒm�}�X�^���������ꍇ�́A�ݒ肷��K�v�͂Ȃ��B<br>
     *
     * @param mapping �}�X�^���ƒʒm�}�X�^���̃}�b�s���O�B�ʒm�}�X�^��=�}�X�^��1,�}�X�^��2,...
     */
    public void setNotifyMasterNameMapping(Properties mapping);

    /**
     * JMS�g�s�b�N�Ń}�X�^�X�V���s���ꍇ�̃}�X�^���ƒʒm�}�X�^���̃}�b�s���O���擾����B<p>
     *
     * @return �}�X�^���ƒʒm�}�X�^���̃}�b�s���O�B�ʒm�}�X�^��=�}�X�^��1,�}�X�^��2,...
     */
    public Properties getNotifyMasterNameMapping();

    /**
     * �T�[�r�X�̊J�n���Ɏ擾����}�X�^�̃}�X�^���z���ݒ肷��B<p>
     * �w�肵�Ȃ��ꍇ�́A�S�Ẵ}�X�^���T�[�r�X�̊J�n���Ɏ擾�����B<br>
     *
     * @param names �}�X�^���z��
     */
    public void setStartMasterNames(String[] names);

    /**
     * �T�[�r�X�̊J�n���Ɏ擾����}�X�^�̃}�X�^���z����擾����B<p>
     *
     * @return �}�X�^���z��
     */
    public String[] getStartMasterNames();

    /**
     * �T�[�r�X�̊J�n���Ɏ擾���Ȃ��}�X�^�̃}�X�^���z���ݒ肷��B<p>
     * �w�肵�Ȃ��ꍇ�́A�S�Ẵ}�X�^���T�[�r�X�̊J�n���Ɏ擾�����B<br>
     *
     * @param names �}�X�^���z��
     */
    public void setNotStartMasterNames(String[] names);

    /**
     * �T�[�r�X�̊J�n���Ɏ擾���Ȃ��}�X�^�̃}�X�^���z����擾����B<p>
     *
     * @return �}�X�^���z��
     */
    public String[] getNotStartMasterNames();

    /**
     * �S�R�[�h�}�X�^���X�V���鎞�ɍX�V���Ȃ��}�X�^�̃}�X�^���z���ݒ肷��B<p>
     * �w�肵�Ȃ��ꍇ�́A�S�Ẵ}�X�^���S�R�[�h�}�X�^�X�V���Ɏ擾�����B<br>
     *
     * @param names �}�X�^���z��
     */
    public void setNotUpdateAllMasterNames(String[] names);

    /**
     * �S�R�[�h�}�X�^���X�V���鎞�ɍX�V���Ȃ��}�X�^�̃}�X�^���z����擾����B<p>
     *
     * @return �}�X�^���z��
     */
    public String[] getNotUpdateAllMasterNames();

    /**
     * �T�[�r�X�̊J�n���Ɏ擾����}�X�^�̃}�X�^���Ɠ��̓I�u�W�F�N�g�̃}�b�s���O��ݒ肷��B<p>
     * ���͂��K�v�Ȃ��}�X�^�̏ꍇ�́A�}�b�s���O��ݒ肷��K�v�͂Ȃ��B<br>
     *
     * @param map �}�X�^���Ɠ��̓I�u�W�F�N�g�̃}�b�v
     */
    public void setStartMasterInputMap(Map map);

    /**
     * �T�[�r�X�̊J�n���Ɏ擾����}�X�^�̃}�X�^���Ɠ��̓I�u�W�F�N�g�̃}�b�s���O���擾����B<p>
     *
     * @return �}�X�^���Ɠ��̓I�u�W�F�N�g�̃}�b�v
     */
    public Map getStartMasterInputMap();

    /**
     * �}�X�^���t�@�C���Ƃ��ĉi��������f�B���N�g����ݒ肷��B<p>
     *
     * @param dir �i��������f�B���N�g��
     */
    public void setPersistDir(String dir);

    /**
     * �}�X�^���t�@�C���Ƃ��ĉi��������f�B���N�g�����擾����B<p>
     *
     * @return �i��������f�B���N�g��
     */
    public String getPersistDir();

    /**
     * �T�[�r�X�̊J�n���Ƀt�@�C���ɉi��������Ă���}�X�^��ǂݍ��ނ��ǂ�����ݒ肷��B<p>
     * �t�@�C������ǂݍ��܂ꂽ�ꍇ�́A�}�X�^�擾�Ɩ��t���[�͎��s���Ȃ��B<br>
     * �f�t�H���g�́Afalse�œǂݍ��܂Ȃ��B<br>
     *
     * @param isLoad �J�n���ɓǂݍ��ޏꍇ�́Atrue
     */
    public void setLoadOnStart(boolean isLoad);

    /**
     * �t�@�C���ɉi��������Ă���}�X�^���T�[�r�X�̊J�n���ɓǂݍ��ނ��ǂ����𔻒肷��B<p>
     *
     * @return true�̏ꍇ�A�J�n���ɓǂݍ���
     */
    public boolean isLoadOnStart();

    /**
     * �T�[�r�X�̒�~���Ƀ}�X�^���t�@�C���ɉi�������邩�ǂ�����ݒ肷��B<p>
     * �f�t�H���g�́Afalse�ŉi�������Ȃ��B<br>
     *
     * @param isSave ��~���ɉi��������ꍇ�́Atrue
     */
    public void setSaveOnStop(boolean isSave);

    /**
     * �T�[�r�X�̒�~���Ƀ}�X�^���t�@�C���ɉi�������邩�ǂ����𔻒肷��B<p>
     *
     * @return true�̏ꍇ�́A��~���ɉi��������
     */
    public boolean isSaveOnStop();

    /**
     * �S�Ẵ}�X�^���t�@�C���ɉi��������B<p>
     *
     * @exception IOException �i�����Ɏ��s�����ꍇ
     */
    public void save() throws IOException;

    /**
     * �w�肵���}�X�^���t�@�C���ɉi��������B<p>
     *
     * @param key �}�X�^�̃L�[
     * @return �w�肵���}�X�^�����݂��Ȃ������ꍇfalse
     * @exception IOException �i�����Ɏ��s�����ꍇ
     */
    public boolean save(String key) throws IOException;

    /**
     * �S�Ẵ}�X�^���i�������ꂽ�t�@�C������ǂݍ��ށB<p>
     *
     * @exception IOException �ǂݍ��݂Ɏ��s�����ꍇ
     * @exception ClassNotFoundException �ǂݍ��݂񂾃}�X�^�̃N���X�����݂��Ȃ��ꍇ
     */
    public void load() throws IOException, ClassNotFoundException;

    /**
     * �w�肳�ꂽ�}�X�^���i�������ꂽ�t�@�C������ǂݍ��ށB<p>
     *
     * @param key �}�X�^�̃L�[
     * @return �w�肵���}�X�^�����݂��Ȃ������ꍇfalse
     * @exception IOException �ǂݍ��݂Ɏ��s�����ꍇ
     * @exception ClassNotFoundException �ǂݍ��݂񂾃}�X�^�̃N���X�����݂��Ȃ��ꍇ
     */
    public boolean load(String key) throws IOException, ClassNotFoundException;

    /**
     * �i�����t�@�C����S�č폜����B<p>
     *
     * @exception IOException �i�����t�@�C���̍폜�Ɏ��s�����ꍇ
     */
    public void clearPersist() throws IOException;
}
