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
package jp.ossc.nimbus.service.publish.tcp;

import java.util.Set;
import java.util.Map;

import jp.ossc.nimbus.core.*;

/**
 * {@link ConnectionFactoryService}��MBean�C���^�t�F�[�X�B<p>
 * 
 * @author M.Takata
 */
public interface ConnectionFactoryServiceMBean extends ServiceBaseMBean{
    
    public static final String MSG_ID_SEND_ERROR            = "PCFT_00001";
    public static final String MSG_ID_SEND_ERROR_RETRY_OVER = "PCFT_00002";
    public static final String MSG_ID_RECEIVE_WARN          = "PCFT_00003";
    public static final String MSG_ID_RECEIVE_ERROR         = "PCFT_00004";
    public static final String MSG_ID_SERVER_CLOSE          = "PCFT_00008";
    public static final String MSG_ID_CLIENT_CONNECT        = "PCFT_00009";
    public static final String MSG_ID_CLIENT_CLOSED         = "PCFT_00010";
    public static final String MSG_ID_CLIENT_CLOSE          = "PCFT_00011";
    
    /**
     * �N���C�A���g���Ńo�C���h���郍�[�J���A�h���X���w�肷��V�X�e���v���p�e�B����ݒ肷��B<br>
     * �f�t�H���g�́A�w��Ȃ��ŁA�N���C�A���g���̃��[�v�o�b�N�A�h���X�Ƀo�C���h����B<br>
     *
     * @param name �V�X�e���v���p�e�B��
     */
    public void setClientAddressPropertyName(String name);
    
    /**
     * �N���C�A���g���Ńo�C���h���郍�[�J���A�h���X���w�肷��V�X�e���v���p�e�B�����擾����B<br>
     *
     * @return �V�X�e���v���p�e�B��
     */
    public String getClientAddressPropertyName();
    
    /**
     * �N���C�A���g���Ńo�C���h���郍�[�J���|�[�g���w�肷��V�X�e���v���p�e�B����ݒ肷��B<br>
     * �f�t�H���g�́A�w��Ȃ��ŁA�N���C�A���g���̔C�ӂ̃|�[�g�Ƀo�C���h����B<br>
     *
     * @param name �V�X�e���v���p�e�B��
     */
    public void setClientPortPropertyName(String name);
    
    /**
     * �N���C�A���g���Ńo�C���h���郍�[�J���|�[�g���w�肷��V�X�e���v���p�e�B�����擾����B<br>
     *
     * @return �V�X�e���v���p�e�B��
     */
    public String getClientPortPropertyName();
    
    /**
     * �N���C�A���g���Őڑ��ؒf�����m�����ꍇ�̍Đڑ����s�񐔂�ݒ肷��B<br>
     * �f�t�H���g�́A0�ōĐڑ����Ȃ��B<br>
     * �Đڑ�����ꍇ�́A{@link #setServerPort(int)}�ŃT�[�o���̃|�[�g�ԍ����Œ�ɂ���K�v������B<br>
     *
     * @param count �Đڑ����s��
     */
    public void setClientReconnectCount(int count);
    
    /**
     * �N���C�A���g���Őڑ��ؒf�����m�����ꍇ�̍Đڑ����s�񐔂��擾����B<br>
     *
     * @return �Đڑ����s��
     */
    public int getClientReconnectCount();
    
    /**
     * �N���C�A���g���Őڑ��ؒf�����m�����ꍇ�̍Đڑ����s�Ԋu[ms]��ݒ肷��B<br>
     * �f�t�H���g�́A0�B<br>
     *
     * @param interval �Đڑ����s�Ԋu[ms]
     */
    public void setClientReconnectInterval(long interval);
    
    /**
     * �N���C�A���g���Őڑ��ؒf�����m�����ꍇ�̍Đڑ����s�Ԋu[ms]���擾����B<br>
     *
     * @return �Đڑ����s�Ԋu[ms]
     */
    public long getClientReconnectInterval();
    
    /**
     * �N���C�A���g���Őڑ��ؒf�����m�����ꍇ�ɍŌ�Ɏ�M�������b�Z�[�W�̎�M��������ǂ̂��炢�̎���[ms]�����k���čđ��v�����o������ݒ肷��B<p>
     * �f�t�H���g�́A0[ms]�ōŏI��M��������đ��v������B<br>
     *
     * @param time ��M��������k�鎞��[ms]
     */
    public void setClientReconnectBufferTime(long time);
    
    /**
     * �N���C�A���g���Őڑ��ؒf�����m�����ꍇ�ɍŌ�Ɏ�M�������b�Z�[�W�̎�M��������ǂ̂��炢�̎���[ms]�����k���čđ��v�����o�������擾����B<p>
     *
     * @return ��M��������k�鎞��[ms]
     */
    public long getClientReconnectBufferTime();
    
    /**
     * �T�[�o���̐ڑ��҂��󂯃A�h���X��ݒ肷��B<br>
     * �w�肵�Ȃ��ꍇ�́A���[�J���A�h���X�B<br>
     *
     * @param address �A�h���X
     */
    public void setServerAddress(String address);
    
    /**
     * �T�[�o���̐ڑ��҂��󂯃A�h���X���擾����B<br>
     *
     * @return �A�h���X
     */
    public String getServerAddress();
    
    /**
     * �T�[�o���̐ڑ��҂��󂯃|�[�g�ԍ���ݒ肷��B<br>
     * �f�t�H���g�́A0�ŔC�ӂ̃|�[�g�ԍ��B<br>
     * 
     *
     * @param port �|�[�g�ԍ�
     */
    public void setServerPort(int port);
    
    /**
     * �T�[�o���̐ڑ��҂��󂯃|�[�g�ԍ����擾����B<br>
     *
     * @return �|�[�g�ԍ�
     */
    public int getServerPort();
    
    /**
     * �T�[�o�\�P�b�g�̃o�b�N���O��ݒ肷��B<br>
     * �f�t�H���g��0�B<br>
     * 
     * @param backlog �o�b�N���O
     */
    public void setServerBacklog(int backlog);
    
    /**
     * �T�[�o�\�P�b�g�̃o�b�N���O���擾����B<br>
     * 
     * @return �o�b�N���O
     */
    public int getServerBacklog();
    
    /**
     * �T�[�o�\�P�b�g�t�@�N�g���T�[�r�X�̃T�[�r�X����ݒ肷��B<br>
     *
     * @param name �T�[�o�\�P�b�g�t�@�N�g���T�[�r�X�̃T�[�r�X��
     */
    public void setServerSocketFactoryServiceName(ServiceName name);
    
    /**
     * �T�[�o�\�P�b�g�t�@�N�g���T�[�r�X�̃T�[�r�X�����擾����B<br>
     *
     * @return �T�[�o�\�P�b�g�t�@�N�g���T�[�r�X�̃T�[�r�X��
     */
    public ServiceName getServerSocketFactoryServiceName();
    
    /**
     * �\�P�b�g�t�@�N�g���T�[�r�X�̃T�[�r�X����ݒ肷��B<br>
     *
     * @param name �\�P�b�g�t�@�N�g���T�[�r�X�̃T�[�r�X��
     */
    public void setSocketFactoryServiceName(ServiceName name);
    
    /**
     * �\�P�b�g�t�@�N�g���T�[�r�X�̃T�[�r�X�����擾����B<br>
     *
     * @return �\�P�b�g�t�@�N�g���T�[�r�X�̃T�[�r�X��
     */
    public ServiceName getSocketFactoryServiceName();
    
    /**
     * ��java.nio���g�����ڑ����s���ꍇ�̃T�[�o���̃\�P�b�g�ɓK�p����\�P�b�g�t�@�N�g���T�[�r�X�̃T�[�r�X����ݒ肷��B<br>
     *
     * @param name �\�P�b�g�t�@�N�g���T�[�r�X�̃T�[�r�X��
     */
    public void setNIOSocketFactoryServiceName(ServiceName name);
    
    /**
     * ��java.nio���g�����ڑ����s���ꍇ�̃T�[�o���̃\�P�b�g�ɓK�p����\�P�b�g�t�@�N�g���T�[�r�X�̃T�[�r�X�����擾����B<br>
     *
     * @return �\�P�b�g�t�@�N�g���T�[�r�X�̃T�[�r�X��
     */
    public ServiceName getNIOSocketFactoryServiceName();
    
    /**
     * �T�[�o���̃\�P�b�g��java.nio���g�����ڑ����s�����ǂ�����ݒ肷��B<p>
     * �f�t�H���g�́Afalse�B<br>
     *
     * @param isNIO java.nio���g�����ڑ����s���ꍇtrue
     */
    public void setNIO(boolean isNIO);
    
    /**
     * �T�[�o���̃\�P�b�g��java.nio���g�����ڑ����s�����ǂ����𔻒肷��B<p>
     *
     * @return true�̏ꍇ�Ajava.nio���g�����ڑ����s��
     */
    public boolean isNIO();
    
    /**
     * {@link jp.ossc.nimbus.service.publish.ClientConnectionFactory ClientConnectionFactory}�����[�g�I�u�W�F�N�g��JNDI����ݒ肷��B<p>
     * �f�t�H���g�́A{@link jp.ossc.nimbus.service.publish.ClientConnectionFactory#DEFAULT_JNDI_NAME}�B<br>
     *
     * @param name JNDI��
     */
    public void setJndiName(String name);
    
    /**
     * {@link jp.ossc.nimbus.service.publish.ClientConnectionFactory ClientConnectionFactory}�����[�g�I�u�W�F�N�g��JNDI�����擾����B<p>
     *
     * @return JNDI��
     */
    public String getJndiName();
    
    /**
     * {@link jp.ossc.nimbus.service.publish.ClientConnectionFactory ClientConnectionFactory}�����[�g�I�u�W�F�N�g���o�C���h����{@link jp.ossc.nimbus.service.repository.Repository Repository}�T�[�r�X�̃T�[�r�X����ݒ肷��B<p>
     * 
     * @param name Repository�T�[�r�X�̃T�[�r�X��
     */
    public void setJndiRepositoryServiceName(ServiceName name);
    
    /**
     * {@link jp.ossc.nimbus.service.publish.ClientConnectionFactory ClientConnectionFactory}�����[�g�I�u�W�F�N�g���o�C���h����{@link jp.ossc.nimbus.service.repository.Repository Repository}�T�[�r�X�̃T�[�r�X�����擾����B<p>
     * 
     * @return Repository�T�[�r�X�̃T�[�r�X��
     */
    public ServiceName getJndiRepositoryServiceName();
    
    /**
     * {@link jp.ossc.nimbus.service.publish.ClientConnectionFactory ClientConnectionFactory}�����[�g�I�u�W�F�N�g��RMI�ʐM�|�[�g�ԍ���ݒ肷��B<p>
     *
     * @param port �|�[�g�ԍ�
     */
    public void setRMIPort(int port);
    
    /**
     * {@link jp.ossc.nimbus.service.publish.ClientConnectionFactory ClientConnectionFactory}�����[�g�I�u�W�F�N�g��RMI�ʐM�|�[�g�ԍ����擾����B<p>
     *
     * @return �|�[�g�ԍ�
     */
    public int getRMIPort();
    
    /**
     * {@link jp.ossc.nimbus.service.publish.ServerConnection#send(Message)}�œ������M������ۂɁA�e���M��֕���ɑ��M�������s���X���b�h�̐���ݒ肷��B<p>
     * �f�t�H���g��1�ŁA�e���M��֒���ɑ��M�������s���B<br>
     *
     * @param threadSize �X���b�h��
     */
    public void setSendThreadSize(int threadSize);
    
    /**
     * {@link jp.ossc.nimbus.service.publish.ServerConnection#send(Message)}�œ������M������ۂɁA�e���M��֕���ɑ��M�������s���X���b�h�̐����擾����B<p>
     *
     * @return �X���b�h��
     */
    public int getSendThreadSize();
    
    /**
     * {@link jp.ossc.nimbus.service.publish.ServerConnection#send(Message)}�œ������M������ۂɁA���񑗐M���s�����߂�{@link jp.ossc.nimbus.service.queue.Queue Queue}�̃T�[�r�X����ݒ肷��B<p>
     * {@link #setSendThreadSize(int)}�ɁA2�ȏ���w�肵�Ȃ��ꍇ�́A���񑗐M���Ȃ����߁AQueue�͎g�p���Ȃ��B<br>
     * 2�ȏ���w�肵���ꍇ�ŁA���̑������w�肵�Ȃ��ꍇ�́A������Queue�𐶐�����B<br>
     *
     * @param name ���񑗐M�p��Queue�T�[�r�X��
     */
    public void setSendQueueServiceName(ServiceName name);
    
    /**
     * {@link jp.ossc.nimbus.service.publish.ServerConnection#send(Message)}�œ������M������ۂɁA���񑗐M���s�����߂�{@link jp.ossc.nimbus.service.queue.Queue Queue}�̃T�[�r�X�����擾����B<p>
     *
     * @return ���񑗐M�p��Queue�T�[�r�X��
     */
    public ServiceName getSendQueueServiceName();
    
    /**
     * {@link jp.ossc.nimbus.service.publish.ServerConnection#sendAsynch(Message)}�Ŕ񓯊����M������ۂɁA�e���M��֕���ɑ��M�������s���X���b�h�̐���ݒ肷��B<p>
     * �f�t�H���g��0�ŁA�񓯊����M���T�|�[�g���Ȃ��B<br>
     *
     * @param threadSize �X���b�h��
     */
    public void setAsynchSendThreadSize(int threadSize);
    
    /**
     * {@link jp.ossc.nimbus.service.publish.ServerConnection#sendAsynch(Message)}�Ŕ񓯊����M������ۂɁA�e���M��֕���ɑ��M�������s���X���b�h�̐����擾����B<p>
     *
     * @return �X���b�h��
     */
    public int getAsynchSendThreadSize();
    
    /**
     * {@link jp.ossc.nimbus.service.publish.ServerConnection#sendAsynch(Message)}�Ŕ񓯊����M������ۂɎg�p����{@link jp.ossc.nimbus.service.queue.Queue Queue}�̃T�[�r�X����ݒ肷��B<p>
     * {@link #setAsynchSendThreadSize(int)}�ɁA1�ȏ���w�肵�Ȃ��ꍇ�́A�񓯊����M���T�|�[�g���Ȃ����߁AQueue�͎g�p���Ȃ��B<br>
     * 1�ȏ���w�肵���ꍇ�ŁA���̑������w�肵�Ȃ��ꍇ�́A������Queue�𐶐�����B<br>
     *
     * @param name �񓯊����M�p��Queue�T�[�r�X��
     */
    public void setAsynchSendQueueServiceName(ServiceName name);
    
    /**
     * {@link jp.ossc.nimbus.service.publish.ServerConnection#sendAsynch(Message)}�Ŕ񓯊����M������ۂɎg�p����{@link jp.ossc.nimbus.service.queue.Queue Queue}�̃T�[�r�X�����擾����B<p>
     *
     * @return �񓯊����M�p��Queue�T�[�r�X��
     */
    public ServiceName getAsynchSendQueueServiceName();
    
    /**
     * {@link jp.ossc.nimbus.service.publish.ServerConnection#sendAsynch(Message)}�Ŕ񓯊����M������ۂɁA���񑗐M���s�����߂�{@link jp.ossc.nimbus.service.queue.Queue Queue}�̃t�@�N�g���T�[�r�X����ݒ肷��B<p>
     * {@link #setAsynchSendThreadSize(int)}�ɁA1�ȏ���w�肵�Ȃ��ꍇ�́A�񓯊����M���T�|�[�g���Ȃ����߁AQueue�͎g�p���Ȃ��B<br>
     * 1�ȏ���w�肵���ꍇ�ŁA���̑������w�肵�Ȃ��ꍇ�́A������Queue�𐶐�����B<br>
     *
     * @param name ���񑗐M�p��Queue�t�@�N�g���T�[�r�X��
     */
    public void setAsynchSendQueueFactoryServiceName(ServiceName name);
    
    /**
     * {@link jp.ossc.nimbus.service.publish.ServerConnection#sendAsynch(Message)}�Ŕ񓯊����M������ۂɁA���񑗐M���s�����߂�{@link jp.ossc.nimbus.service.queue.Queue Queue}�̃t�@�N�g���T�[�r�X�����擾����B<p>
     *
     * @return ���񑗐M�p��Queue�t�@�N�g���T�[�r�X��
     */
    public ServiceName getAsynchSendQueueFactoryServiceName();
    
    /**
     * ���M�Ɏ��s�����ꍇ�ɍđ��M���s���񐔂�ݒ肷��B<p>
     * �f�t�H���g�́A0�ōđ��M���Ȃ��B<br>
     * 
     * @param count �đ��M��
     */
    public void setMaxSendRetryCount(int count);
    
    /**
     * ���M�Ɏ��s�����ꍇ�ɍđ��M���s���񐔂��擾����B<p>
     *
     * @return �đ��M��
     */
    public int getMaxSendRetryCount();
    
    /**
     * ���M���b�Z�[�W�̃L���b�V�����Ԃ�ݒ肷��B<p>
     * {@link jp.ossc.nimbus.service.publish.ClientConnection#startReceive(long)}�ők���Ď�M���邽�߂ɁA���M���ő��M�L���b�V���ɁA���M�������b�Z�[�W�𑗐M������������ǂ̂��炢�̊Ԏc���Ă�������ݒ肷��B<br>
     * �f�t�H���g�́A5000[ms]�B<br>
     *
     * @param time �L���b�V������[ms]
     */
    public void setSendMessageCacheTime(long time);
    
    /**
     * ���M���b�Z�[�W�̃L���b�V�����Ԃ��擾����B<p>
     *
     * @return �L���b�V������[ms]
     */
    public long getSendMessageCacheTime();
    
    /**
     * ���M���b�Z�[�W���܂Ƃ߂đ��M���邽�߂̑ؗ����Ԃ�ݒ肷��B<p>
     * �f�t�H���g�́A0�őؗ������Ȃ��B<br>
     *
     * @param time ���M�ؗ�����[ms]
     */
    public void setSendBufferTime(long time);
    
    /**
     * ���M���b�Z�[�W���܂Ƃ߂đ��M���邽�߂̑ؗ����Ԃ��擾����B<p>
     *
     * @return ���M�ؗ�����[ms]
     */
    public long getSendBufferTime();
    
    /**
     * ���M���b�Z�[�W���܂Ƃ߂đ��M���邽�߂̑ؗ��o�C�g����ݒ肷��B<p>
     * �f�t�H���g�́A0�őؗ������Ȃ��B<br>
     *
     * @param bytes ���M�ؗ��o�C�g��
     */
    public void setSendBufferSize(long bytes);
    
    /**
     * ���M���b�Z�[�W���܂Ƃ߂đ��M���邽�߂̑ؗ��o�C�g�����擾����B<p>
     *
     * @return ���M�ؗ��o�C�g��
     */
    public long getSendBufferSize();
    
    /**
     * ���M���b�Z�[�W���܂Ƃ߂đ��M����ꍇ�ɁA���M�ؗ��`�F�b�N�Ԋu[ms]��ݒ肷��B<p>
     * �f�t�H���g�́A1000[ms]�B<br>
     *
     * @param interval ���M�ؗ��`�F�b�N�Ԋu[ms]
     */
    public void setSendBufferTimeoutInterval(long interval);
    
    /**
     * ���M���b�Z�[�W���܂Ƃ߂đ��M����ꍇ�ɁA���M�ؗ��`�F�b�N�Ԋu[ms]���擾����B<p>
     *
     * @return ���M�ؗ��`�F�b�N�Ԋu[ms]
     */
    public long getSendBufferTimeoutInterval();
    
    /**
     * ���M�悩��̗v���ɑ΂��āA������Ԃ����ǂ�����ݒ肷��B<p>
     * �f�t�H���g�ł́Afalse�ŉ�����Ԃ��Ȃ��B<br>
     *
     * @param isAck ������Ԃ��ꍇtrue
     */
    public void setAcknowledge(boolean isAck);
    
    /**
     * ���M�悩��̗v���ɑ΂��āA������Ԃ����ǂ����𔻒肷��B<p>
     *
     * @return true�̏ꍇ�A������Ԃ�
     */
    public boolean isAcknowledge();
    
    /**
     * �N���C�A���g���T�[�o����̉�����҂^�C���A�E�g[ms]��ݒ肷��B<p>
     * �f�t�H���g�́A30�b�B<br>
     *
     * @param timeout �^�C���A�E�g
     */
    public void setClientResponseTimeout(long timeout);
    
    /**
     * �N���C�A���g���T�[�o����̉�����҂^�C���A�E�g[ms]���擾����B<p>
     *
     * @return �^�C���A�E�g
     */
    public long getClientResponseTimeout();
    
    /**
     * ���M�G���[�����������ꍇ�ɏo�͂��郍�O���b�Z�[�WID��ݒ肷��B<br>
     * �f�t�H���g�́A�Ȃ��Ń��O�o�͂��Ȃ��B<br>
     * 
     * @param id ���O���b�Z�[�WID
     */
    public void setServerSendErrorMessageId(String id);
    
    /**
     * ���M�G���[�����������ꍇ�ɏo�͂��郍�O���b�Z�[�WID���擾����B<br>
     * 
     * @return ���O���b�Z�[�WID
     */
    public String getServerSendErrorMessageId();
    
    /**
     * ���M�G���[���������A�K��̃��g���C�񐔃��g���C���Ă��������Ȃ������ꍇ�ɏo�͂��郍�O���b�Z�[�WID��ݒ肷��B<br>
     * �f�t�H���g�́A�Ȃ��Ń��O�o�͂��Ȃ��B<br>
     * 
     * @param id ���O���b�Z�[�WID
     */
    public void setServerSendErrorRetryOverMessageId(String id);
    
    /**
     * ���M�G���[���������A�K��̃��g���C�񐔃��g���C���Ă��������Ȃ������ꍇ�ɏo�͂��郍�O���b�Z�[�WID���擾����B<br>
     * 
     * @return ���O���b�Z�[�WID
     */
    public String getServerSendErrorRetryOverMessageId();
    
    /**
     * �N���C�A���g���ڑ����ɂ����ꍇ�ɏo�͂��郍�O���b�Z�[�WID��ݒ肷��B<p>
     *
     * @param id ���O���b�Z�[�WID
     */
    public void setClientConnectMessageId(String id);
    
    /**
     * �N���C�A���g���ڑ����ɂ����ꍇ�ɏo�͂��郍�O���b�Z�[�WID���擾����B<p>
     *
     * @return ���O���b�Z�[�WID
     */
    public String getClientConnectMessageId();
    
    /**
     * �N���C�A���g���ؒf���ɂ����ꍇ�ɏo�͂��郍�O���b�Z�[�WID��ݒ肷��B<p>
     *
     * @param id ���O���b�Z�[�WID
     */
    public void setClientClosedMessageId(String id);
    
    /**
     * �N���C�A���g���ؒf���ɂ����ꍇ�ɏo�͂��郍�O���b�Z�[�WID���擾����B<p>
     *
     * @return ���O���b�Z�[�WID
     */
    public String getClientClosedMessageId();
    
    /**
     * �N���C�A���g��ؒf�����ꍇ�ɏo�͂��郍�O���b�Z�[�WID��ݒ肷��B<p>
     *
     * @param id ���O���b�Z�[�WID
     */
    public void setClientCloseMessageId(String id);
    
    /**
     * �N���C�A���g��ؒf�����ꍇ�ɏo�͂��郍�O���b�Z�[�WID���擾����B<p>
     *
     * @return ���O���b�Z�[�WID
     */
    public String getClientCloseMessageId();
    
    /**
     * �T�[�o���ő��M���b�Z�[�W���ė��p����ۂ̑��M���b�Z�[�W�o�b�t�@����ݒ肷��B<p>
     * �f�t�H���g�́A100�B<br>
     *
     * @param size ���M���b�Z�[�W�o�b�t�@��
     */
    public void setServerMessageRecycleBufferSize(int size);
    
    /**
     * �T�[�o���ő��M���b�Z�[�W���ė��p����ۂ̑��M���b�Z�[�W�o�b�t�@�����擾����B<p>
     *
     * @return ���M���b�Z�[�W�o�b�t�@��
     */
    public int getServerMessageRecycleBufferSize();
    
    /**
     * �T�[�o�����I�������ꍇ�ɏo�͂��郍�O���b�Z�[�WID��ݒ肷��B<p>
     *
     * @param id ���O���b�Z�[�WID
     */
    public void setClientServerCloseMessageId(String id);
    
    /**
     * �T�[�o�����I�������ꍇ�ɏo�͂��郍�O���b�Z�[�WID���擾����B<p>
     *
     * @return ���O���b�Z�[�WID
     */
    public String getClientServerCloseMessageId();
    
    /**
     * {@link jp.ossc.nimbus.service.publish.MessageListener#onMessage(jp.ossc.nimbus.service.publish.Message)}�Ŏ�M����ۂɁA��M�G���[�����������ꍇ�ɏo�͂��郍�O���b�Z�[�WID��ݒ肷��B<p>
     *
     * @param id ���O���b�Z�[�WID
     */
    public void setClientReceiveWarnMessageId(String id);
    
    /**
     * {@link jp.ossc.nimbus.service.publish.MessageListener#onMessage(jp.ossc.nimbus.service.publish.Message)}�Ŏ�M����ۂɁA��M�G���[�����������ꍇ�ɏo�͂��郍�O���b�Z�[�WID���擾����B<p>
     *
     * @return ���O���b�Z�[�WID
     */
    public String getClientReceiveWarnMessageId();
    
    /**
     * {@link jp.ossc.nimbus.service.publish.MessageListener#onMessage(jp.ossc.nimbus.service.publish.Message)}�Ŏ�M����ۂɁA��M�G���[���������A���g���C�A�E�g�����ꍇ�ɏo�͂��郍�O���b�Z�[�WID��ݒ肷��B<p>
     *
     * @param id ���O���b�Z�[�WID
     */
    public void setClientReceiveErrorMessageId(String id);
    
    /**
     * {@link jp.ossc.nimbus.service.publish.MessageListener#onMessage(jp.ossc.nimbus.service.publish.Message)}�Ŏ�M����ۂɁA��M�G���[���������A���g���C�A�E�g�����ꍇ�ɏo�͂��郍�O���b�Z�[�WID���擾����B<p>
     *
     * @return ���O���b�Z�[�WID
     */
    public String getClientReceiveErrorMessageId();
    
    /**
     * �N���C�A���g���Ŏ�M���b�Z�[�W���ė��p����ۂ̎�M���b�Z�[�W�o�b�t�@����ݒ肷��B<p>
     * �f�t�H���g�́A100�B<br>
     *
     * @param size ��M���b�Z�[�W�o�b�t�@��
     */
    public void setClientMessageRecycleBufferSize(int size);
    
    /**
     * �N���C�A���g���Ŏ�M���b�Z�[�W�o�b�t�@���ė��p����ۂ̎�M���b�Z�[�W�o�b�t�@�o�b�t�@�����擾����B<p>
     *
     * @return ��M���b�Z�[�W�o�b�t�@�o�b�t�@��
     */
    public int getClientMessageRecycleBufferSize();
    
    /**
     * ���b�Z�[�W�̒���/�񒼗񉻂Ɏg�p����{@link jp.ossc.nimbus.service.io.Externalizer Externalizer}�T�[�r�X�̃T�[�r�X����ݒ肷��B<p>
     * �w�肵�Ȃ��ꍇ�́Ajava.io.ObjectOutputStream�Ń��b�Z�[�W�̒���/�񒼗񉻂��s���B<br>
     *
     * @param name Externalizer�T�[�r�X�̃T�[�r�X��
     */
    public void setExternalizerServiceName(ServiceName name);
    
    /**
     * ���b�Z�[�W�̒���/�񒼗񉻂Ɏg�p����{@link jp.ossc.nimbus.service.io.Externalizer Externalizer}�T�[�r�X�̃T�[�r�X�����擾����B<p>
     *
     * @return Externalizer�T�[�r�X�̃T�[�r�X��
     */
    public ServiceName getExternalizerServiceName();
    
    /**
     * {@link jp.ossc.nimbus.service.publish.ServerConnectionListener ServerConnectionListener}�̃T�[�r�X����ݒ肷��B<p>
     *
     * @param names ServerConnectionListener�̃T�[�r�X���̔z��
     */
    public void setServerConnectionListenerServiceNames(ServiceName[] names);
    
    /**
     * {@link jp.ossc.nimbus.service.publish.ServerConnectionListener ServerConnectionListener}�̃T�[�r�X�����擾����B<p>
     *
     * @return ServerConnectionListener�̃T�[�r�X���̔z��
     */
    public ServiceName[] getServerConnectionListenerServiceNames();
    
    /**
     * ���M�������擾����B<p>
     *
     * @return ���M����
     */
    public long getSendCount();
    
    /**
     * ���M���������Z�b�g����B<p>
     */
    public void resetSendCount();
    
    /**
     * ���ϑ��M���Ԃ��擾����B<p>
     *
     * @return ���ϑ��M����[ms]
     */
    public double getAverageSendProcessTime();
    
    /**
     * ���ϑ��M�o�C�g��[byte]���擾����B<p>
     *
     * @return ���ϑ��M�o�C�g��[byte]
     */
    public double getAverageSendBytes();
    
    /**
     * �ڑ����̃N���C�A���g��java.net.InetSocketAddress�̏W�����擾����B<br>
     *
     * @return �N���C�A���g��java.net.InetSocketAddress�̏W��
     */
    public Set getClients();
    
    /**
     * �ڑ����̃N���C�A���g�̐����擾����B<br>
     *
     * @return �N���C�A���g�̐�
     */
    public int getClientSize();
    
    /**
     * �ڑ����̃N���C�A���g�Ŕz�M���s���ΏۂƂȂ�N���C�A���g��java.net.InetSocketAddress�̏W�����擾����B<br>
     *
     * @return �N���C�A���g��java.net.InetSocketAddress�̏W��
     */
    public Set getEnabledClients();
    
    /**
     * �ڑ����̃N���C�A���g�Ŕz�M���s��Ȃ��ΏۂƂȂ�N���C�A���g��java.net.InetSocketAddress�̏W�����擾����B<br>
     *
     * @return �N���C�A���g��java.net.InetSocketAddress�̏W��
     */
    public Set getDisabledClients();
    
    /**
     * �w�肳�ꂽ�A�h���X�A�|�[�g�ԍ��̃N���C�A���g�̔z�M��L���ɂ���B<p>
     * �|�[�g�ԍ��̎w�肪0�ȉ��̏ꍇ�́A�|�[�g�ԍ��͔C�ӂƂ݂Ȃ��B<br>
     *
     * @param address �N���C�A���g�̃A�h���X�܂��̓z�X�g��
     * @param port �N���C�A���g�̃|�[�g�ԍ�
     */
    public void enabledClient(String address, int port);
    
    /**
     * �w�肳�ꂽ�A�h���X�A�|�[�g�ԍ��̃N���C�A���g�̔z�M�𖳌��ɂ���B<p>
     * �|�[�g�ԍ��̎w�肪0�ȉ��̏ꍇ�́A�|�[�g�ԍ��͔C�ӂƂ݂Ȃ��B<br>
     *
     * @param address �N���C�A���g�̃A�h���X�܂��̓z�X�g��
     * @param port �N���C�A���g�̃|�[�g�ԍ�
     */
    public void disabledClient(String address, int port);
    
    /**
     * �ڑ����̃N���C�A���g���̑��M�������擾����B<br>
     *
     * @return �N���C�A���g���̑��M�����B�L�[���N���C�A���g��java.net.InetSocketAddress�A�l�����M�����̃}�b�v
     */
    public Map getSendCountsByClient();
    
    /**
     * �ڑ����̃N���C�A���g���̕��ϑ��M���Ԃ��擾����B<br>
     *
     * @return �N���C�A���g���̕��ϑ��M����[ms]�B�L�[���N���C�A���g��java.net.InetSocketAddress�A�l�����ϑ��M���Ԃ̃}�b�v
     */
    public Map getAverageSendProcessTimesByClient();
    
    /**
     * �ڑ����̃N���C�A���g���̕��ϑ��M�o�C�g�����擾����B<br>
     *
     * @return �N���C�A���g���̕��ϑ��M�o�C�g��[byte]�B�L�[���N���C�A���g��java.net.InetSocketAddress�A�l�����ϑ��M�o�C�g���̃}�b�v
     */
    public Map getAverageSendBytesByClient();
    
    /**
     * �ڑ����̃N���C�A���g���̑��M���������Z�b�g����B<br>
     */
    public void resetSendCountsByClient();
    
    /**
     * �w�肳�ꂽ�A�h���X�A�|�[�g�ԍ��̃N���C�A���g���o�^���Ă���T�u�W�F�N�g���擾����B<p>
     *
     * @param address �N���C�A���g�̃A�h���X�܂��̓z�X�g��
     * @param port �N���C�A���g�̃|�[�g�ԍ�
     * @return �T�u�W�F�N�g�̏W��
     */
    public Set getSubjects(String address, int port);
    
    /**
     * �w�肳�ꂽ�A�h���X�A�|�[�g�ԍ��̃N���C�A���g���A�w�肳�ꂽ�T�u�W�F�N�g�ɑ΂��ēo�^����Ă���L�[���擾����B<p>
     *
     * @param address �N���C�A���g�̃A�h���X�܂��̓z�X�g��
     * @param port �N���C�A���g�̃|�[�g�ԍ�
     * @param subject �T�u�W�F�N�g
     * @return �L�[�̏W��
     */
    public Set getKeys(String address, int port, String subject);
}