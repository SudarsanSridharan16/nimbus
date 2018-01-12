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
package jp.ossc.nimbus.service.publish;

import java.util.Set;

import jp.ossc.nimbus.core.ServiceBaseMBean;
import jp.ossc.nimbus.core.ServiceName;

/**
 * {@link MessageReceiverService}��MBean�C���^�t�F�[�X<p>
 *
 * @author M.Takata
 * @see MessageReceiverService
 */
public interface MessageReceiverServiceMBean extends ServiceBaseMBean{

    /**
     * {@link MessageListener}�ւ̃p�����[�^�I�u�W�F�N�g�̃��T�C�N�����X�g�̃f�t�H���g�T�C�Y�B
     */
    public static final int DEFAULT_MESSAGE_LISTENER_PARAMETER_RECYCLE_LIST_SIZE = -1;

    /**
     * {@link ClientConnectionFactory}�����[�g�I�u�W�F�N�g��JNDI����ݒ肷��B<p>
     *
     * @param name JNDI��
     */
    public void setClientConnectionFactoryJndiName(String name);

    /**
     * {@link ClientConnectionFactory}�����[�g�I�u�W�F�N�g��JNDI�����擾����B<p>
     *
     * @return JNDI��
     */
    public String getClientConnectionFactoryJndiName();

    /**
     * {@link ClientConnectionFactory}�����[�g�I�u�W�F�N�g���o�C���h����Ă���{@link jp.ossc.nimbus.service.repository.Repository Repository}�T�[�r�X�̃T�[�r�X����ݒ肷��B<p>
     *
     * @param name Repository�T�[�r�X�̃T�[�r�X��
     */
    public void setJndiRepositoryServiceName(ServiceName name);

    /**
     * {@link ClientConnectionFactory}�����[�g�I�u�W�F�N�g���o�C���h����Ă���{@link jp.ossc.nimbus.service.repository.Repository Repository}�T�[�r�X�̃T�[�r�X�����擾����B<p>
     *
     * @return Repository�T�[�r�X�̃T�[�r�X��
     */
    public ServiceName getJndiRepositoryServiceName();

    /**
     * {@link ClientConnectionFactory}�T�[�r�X�̃T�[�r�X����ݒ肷��B<p>
     *
     * @param name ClientConnectionFactory�T�[�r�X�̃T�[�r�X��
     */
    public void setClientConnectionFactoryServiceName(ServiceName name);

    /**
     * {@link ClientConnectionFactory}�T�[�r�X�̃T�[�r�X�����擾����B<p>
     *
     * @return ClientConnectionFactory�T�[�r�X�̃T�[�r�X��
     */
    public ServiceName getClientConnectionFactoryServiceName();

    /**
     * {@link jp.ossc.nimbus.service.queue.Queue Queue}�T�[�r�X�𐶐�����t�@�N�g���T�[�r�X�̃T�[�r�X����ݒ肷��B<p>
     * �����Ŏw�肳�ꂽ�t�@�N�g���T�[�r�X���琶�����ꂽQueue�́A{@link Message}���ɔ񓯊��Ŕz�M���s���ꍇ�̔z�MQueue�Ƃ��Ďg�p����B<br>
     * �ݒ肵�Ȃ��ꍇ�́A������Queue�����������B<br>
     *
     * @param name Queue�T�[�r�X�𐶐�����t�@�N�g���T�[�r�X�̃T�[�r�X��
     */
    public void setMessageQueueFactoryServiceName(ServiceName name);

    /**
     * {@link jp.ossc.nimbus.service.queue.Queue Queue}�T�[�r�X�𐶐�����t�@�N�g���T�[�r�X�̃T�[�r�X�����擾����B<p>
     *
     * @return Queue�T�[�r�X�𐶐�����t�@�N�g���T�[�r�X�̃T�[�r�X��
     */
    public ServiceName getMessageQueueFactoryServiceName();

    /**
     * {@link Message}�𕪗����A{@link MessageListener}�ւ̔z�M��񓯊��ōs���ꍇ�̔z�M�X���b�h�̐���ݒ肷��B<p>
     * �f�t�H���g��0�ŁA�eMessage�𕪗����Ȃ��Ŕz�M����B<br>
     *
     * @param size �z�M�X���b�h�̐�
     */
    public void setMessageQueueDistributedSize(int size);

    /**
     * {@link Message}�𕪗����A{@link MessageListener}�ւ̔z�M��񓯊��ōs���ꍇ�̔z�M�X���b�h�̐����擾����B<p>
     *
     * @return �z�M�X���b�h�̐�
     */
    public int getMessageQueueDistributedSize();

    /**
     * {@link jp.ossc.nimbus.service.queue.Queue Queue}�T�[�r�X�𐶐�����t�@�N�g���T�[�r�X�̃T�[�r�X����ݒ肷��B<p>
     * �����Ŏw�肳�ꂽ�t�@�N�g���T�[�r�X���琶�����ꂽQueue�́A{@link MessageListener}���ɔ񓯊��Ŕz�M���s���ꍇ�̔z�MQueue�Ƃ��Ďg�p����B<br>
     * �ݒ肵�Ȃ��ꍇ�́A������Queue�����������B<br>
     *
     * @param name Queue�T�[�r�X�𐶐�����t�@�N�g���T�[�r�X�̃T�[�r�X��
     */
    public void setMessageListenerQueueFactoryServiceName(ServiceName name);

    /**
     * {@link jp.ossc.nimbus.service.queue.Queue Queue}�T�[�r�X�𐶐�����t�@�N�g���T�[�r�X�̃T�[�r�X�����擾����B<p>
     *
     * @return Queue�T�[�r�X�𐶐�����t�@�N�g���T�[�r�X�̃T�[�r�X��
     */
    public ServiceName getMessageListenerQueueFactoryServiceName();

    /**
     * {@link MessageListener}�ւ̔z�M��񓯊��ōs���ꍇ�̔z�M�X���b�h�̐���ݒ肷��B<p>
     * �f�t�H���g��0�ŁA�eMessageListener�ɃV���O���X���b�h�ŏ����z�M����B<br>
     *
     * @param size �z�M�X���b�h�̐�
     */
    public void setMessageListenerQueueDistributedSize(int size);

    /**
     * {@link MessageListener}�ւ̔z�M��񓯊��ōs���ꍇ�̔z�M�X���b�h�̐����擾����B<p>
     *
     * @return �z�M�X���b�h�̐�
     */
    public int getMessageListenerQueueDistributedSize();

    /**
     * �T�[�r�X�̊J�n���ɁA{@link ClientConnection}��ڑ����邩�ǂ�����ݒ肷��B<p>
     *
     * @param isConnect �ڑ�����ꍇ�Atrue
     */
    public void setConnectOnStart(boolean isConnect);

    /**
     * �T�[�r�X�̊J�n���ɁA{@link ClientConnection}��ڑ����邩�ǂ����𔻒肷��B<p>
     *
     * @return true�̏ꍇ�ڑ�����
     */
    public boolean isConnectOnStart();

    /**
     * �T�[�r�X�̊J�n���ɁA��M���J�n���邩�ǂ�����ݒ肷��B<p>
     *
     * @param isStart ��M���J�n����ꍇ�Atrue
     */
    public void setStartReceiveOnStart(boolean isStart);

    /**
     * �T�[�r�X�̊J�n���ɁA��M���J�n���邩�ǂ����𔻒肷��B<p>
     *
     * @return true�̏ꍇ�A��M���J�n����
     */
    public boolean isStartReceiveOnStart();
    
    /**
     * ���b�Z�[�W�z�M�̒x�����L�^����{@link jp.ossc.nimbus.service.performance.PerformanceRecorder PerformanceRecorder}�T�[�r�X�̃T�[�r�X����ݒ肷��B<p>
     *
     * @param name PerformanceRecorder�T�[�r�X�̃T�[�r�X��
     */
    public void setMessageLatencyPerformanceRecorderServiceName(ServiceName name);
    
    /**
     * ���b�Z�[�W�z�M�̒x�����L�^����{@link jp.ossc.nimbus.service.performance.PerformanceRecorder PerformanceRecorder}�T�[�r�X�̃T�[�r�X�����擾����B<p>
     *
     * @return PerformanceRecorder�T�[�r�X�̃T�[�r�X��
     */
    public ServiceName getMessageLatencyPerformanceRecorderServiceName();

    /**
     * {@link ClientConnection}��ڑ�����B<p>
     *
     * @exception Exception �ڑ��Ɏ��s�����ꍇ
     */
    public void connect() throws Exception;

    /**
     * {@link ClientConnection}��ؒf����B<p>
     */
    public void close();

    /**
     * �ڑ����Ă��邩�ǂ����𔻒肷��B<p>
     *
     * @return �ڑ����Ă���ꍇtrue
     */
    public boolean isConnected();

    /**
     * ��M���J�n����B<p>
     *
     * @exception MessageSendException �T�[�o�ւ̗v���Ɏ��s�����ꍇ
     */
    public void startReceive() throws MessageSendException;

    /**
     * ��M���~����B<p>
     *
     * @exception MessageSendException �T�[�o�ւ̗v���Ɏ��s�����ꍇ
     */
    public void stopReceive() throws MessageSendException;

    /**
     * �z�M�J�n���Ă��邩�ǂ����𔻒肷��B<br>
     *
     * @return �z�M�J�n���Ă���ꍇtrue
     */
    public boolean isStartReceive();

    /**
     * ��M���̃T�u�W�F�N�g�̏W�����擾����B<p>
     *
     * @return ��M���̃T�u�W�F�N�g�̏W��
     */
    public Set getSubjectNameSet();

    /**
     * ��M�������擾����B<p>
     *
     * @return ��M����
     */
    public long getReceiveCount();

    /**
     * ��M���������Z�b�g����B<p>
     */
    public void resetReceiveCount();

    /**
     * �w�肵���T�u�W�F�N�g�̎�M�������擾����B<p>
     *
     * @param subject �T�u�W�F�N�g
     * @return ��M����
     */
    public long getReceiveCount(String subject);

    /**
     * �w�肵���T�u�W�F�N�g�̎�M���������Z�b�g����B<p>
     *
     * @param subject �T�u�W�F�N�g
     */
    public void resetReceiveCount(String subject);

    /**
     * �o�^����Ă���T�u�W�F�N�g���擾����B<p>
     *
     * @return �T�u�W�F�N�g�̏W��
     */
    public Set getSubjects();

    /**
     * �w�肳�ꂽ�T�u�W�F�N�g�ɑ΂��ēo�^����Ă���L�[���擾����B<p>
     *
     * @param subject �T�u�W�F�N�g
     * @return �L�[�̏W��
     */
    public Set getKeys(String subject);

    /**
     * �o�^����Ă���{@link MessageListener}�̐����擾����B<p>
     *
     * @return MessageListener�̐�
     */
    public int getMessageListenerSize();

    /**
     * {@link Message}�𕪗����A{@link MessageListener}�ւ̔z�M��񓯊��ōs���ꍇ�̔񓯊������p�̃L���[�̓����������擾����B<p>
     *
     * @return �񓯊������p�̃L���[�̓�������
     */
    public long getMessageQueueCount();

    /**
     * {@link Message}�𕪗����A{@link MessageListener}�ւ̔z�M��񓯊��ōs���ꍇ�̔񓯊������p�̃L���[�̑ؗ��������擾����B<p>
     *
     * @return �񓯊������p�̃L���[�̑ؗ�����
     */
    public long getMessageQueueDepth();

    /**
     * {@link Message}�𕪗����A{@link MessageListener}�ւ̔z�M��񓯊��ōs���ꍇ�̔񓯊������̕��Ϗ�������[ms]���擾����B<p>
     *
     * @return �񓯊������̕��Ϗ�������[ms]
     */
    public long getMessageQueueAverageHandleProcessTime();

    /**
     * {@link MessageListener}�ւ̔z�M��񓯊��ōs���ꍇ�̔񓯊������p�̃L���[�̓����������擾����B<p>
     *
     * @return �񓯊������p�̃L���[�̓�������
     */
    public long getgetMessageListenerQueueCount();

    /**
     * {@link MessageListener}�ւ̔z�M��񓯊��ōs���ꍇ�̔񓯊������p�̃L���[�̑ؗ��������擾����B<p>
     *
     * @return �񓯊������p�̃L���[�̑ؗ�����
     */
    public long getMessageListenerQueueDepth();

    /**
     * {@link MessageListener}�ւ̔z�M��񓯊��ōs���ꍇ�̔񓯊������̕��Ϗ�������[ms]���擾����B<p>
     *
     * @return �񓯊������̕��Ϗ�������[ms]
     */
    public long getMessageListenerQueueAverageHandleProcessTime();

    /**
     * {@link MessageListener}�ւ̃p�����[�^�I�u�W�F�N�g�̃��T�C�N�����X�g�̏���T�C�Y��ݒ肷��B<p>
     *
     * @param size ���T�C�N�����X�g�̏���T�C�Y
     */
    public void setMessageListenerParameterRecycleListSize(int size);

    /**
     * {@link MessageListener}�ւ̃p�����[�^�I�u�W�F�N�g�̃��T�C�N�����X�g�̏���T�C�Y���擾����B<p>
     *
     * @return ���T�C�N�����X�g�̏���T�C�Y
     */
    public int getMessageListenerParameterRecycleListSize();

}
