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

import jp.ossc.nimbus.core.ServiceBaseMBean;
import jp.ossc.nimbus.core.ServiceName;

/**
 * {@link ClusterConnectionFactoryService}��MBean�C���^�t�F�[�X<p>
 * 
 * @author M.Takata
 * @see ClusterConnectionFactoryService
 */
public interface ClusterConnectionFactoryServiceMBean extends ServiceBaseMBean{
    
    public static final String MSG_ID_CONNECT_ERROR = "PCCF_00001";
    public static final String MSG_ID_RECONNECT     = "PCCF_00003";
    public static final String MSG_ID_NOCONNECT_ERROR = "PCCF_00004";
    public static final String MSG_ID_CONNECTION_GET_ERROR = "PCCF_00005";
    
    /**
     * {@link ClientConnectionFactory ClientConnectionFactory}�����[�g�I�u�W�F�N�g��JNDI����ݒ肷��B<p>
     * �f�t�H���g�́A{@link ClientConnectionFactory#DEFAULT_JNDI_NAME}�B<br>
     *
     * @param name JNDI��
     */
    public void setJndiName(String name);
    
    /**
     * {@link ClientConnectionFactory}�����[�g�I�u�W�F�N�g��JNDI�����擾����B<p>
     *
     * @return JNDI��
     */
    public String getJndiName();
    
    /**
     * {@link ClientConnectionFactory}�����[�g�I�u�W�F�N�g���o�C���h����{@link jp.ossc.nimbus.service.repository.Repository Repository}�T�[�r�X�̃T�[�r�X����ݒ肷��B<p>
     * 
     * @param name Repository�T�[�r�X�̃T�[�r�X��
     */
    public void setJndiRepositoryServiceName(ServiceName name);
    
    /**
     * {@link ClientConnectionFactory}�����[�g�I�u�W�F�N�g���o�C���h����{@link jp.ossc.nimbus.service.repository.Repository Repository}�T�[�r�X�̃T�[�r�X�����擾����B<p>
     * 
     * @return Repository�T�[�r�X�̃T�[�r�X��
     */
    public ServiceName getJndiRepositoryServiceName();
    
    /**
     * {@link ClientConnectionFactory}�����[�g�I�u�W�F�N�g��RMI�ʐM�|�[�g�ԍ���ݒ肷��B<p>
     *
     * @param port �|�[�g�ԍ�
     */
    public void setRMIPort(int port);
    
    /**
     * {@link ClientConnectionFactory}�����[�g�I�u�W�F�N�g��RMI�ʐM�|�[�g�ԍ����擾����B<p>
     *
     * @return �|�[�g�ԍ�
     */
    public int getRMIPort();
    
    /**
     * {@link jp.ossc.nimbus.service.keepalive.ClusterService ClusterService}�T�[�r�X�̃T�[�r�X����ݒ肷��B<p>
     * �N���X�^�̃����o�[���ɁA{@link ClientConnectionFactory}�����[�g�I�u�W�F�N�g��ݒ肷�鎖�ŁA�N���X�^�ɎQ�����Ă���ClientConnectionFactory���N���C�A���g���ŋ��L����B<br>
     *
     * @param name ClusterService�T�[�r�X�̃T�[�r�X��
     */
    public void setClusterServiceName(ServiceName name);
    
    /**
     * {@link jp.ossc.nimbus.service.keepalive.ClusterService ClusterService}�T�[�r�X�̃T�[�r�X�����擾����B<p>
     *
     * @return ClusterService�T�[�r�X�̃T�[�r�X��
     */
    public ServiceName getClusterServiceName();
    
    /**
     * {@link jp.ossc.nimbus.service.keepalive.ClusterService �N���X�^}�T�[�r�X�̃����o�[���̃I�v�V�����L�[��ݒ肷��B<p>
     *
     * @param key �I�v�V�����L�[
     */
    public void setClusterOptionKey(String key);
    
    /**
     * {@link jp.ossc.nimbus.service.keepalive.ClusterService �N���X�^}�T�[�r�X�̃����o�[���̃I�v�V�����L�[���擾����B<p>
     *
     * @return �I�v�V�����L�[
     */
    public String getClusterOptionKey();
    
    /**
     * {@link jp.ossc.nimbus.service.keepalive.ClusterService �N���X�^}�T�[�r�X�̃N���X�^�Q���𐧌䂷�邩�ǂ�����ݒ肷��B<p>
     * �f�t�H���g�́Atrue�Ő��䂷��B<br>
     *
     * @param isJoin ���䂷��ꍇ�Atrue
     */
    public void setClusterJoin(boolean isJoin);
    
    /**
     * {@link jp.ossc.nimbus.service.keepalive.ClusterService �N���X�^}�T�[�r�X�̃N���X�^�Q���𐧌䂷�邩�ǂ����𔻒肷��B<p>
     *
     * @return true�̏ꍇ�A���䂷��
     */
    public boolean isClusterJoin();
    
    /**
     * �N���X�^��������{@link ClientConnectionFactory}�T�[�r�X�̃T�[�r�X����ݒ肷��B<p>
     *
     * @param name ClientConnectionFactory�T�[�r�X�̃T�[�r�X��
     */
    public void setClientConnectionFactoryServiceName(ServiceName name);
    
    /**
     * �N���X�^��������{@link ClientConnectionFactory}�T�[�r�X�̃T�[�r�X�����擾����B<p>
     *
     * @return ClientConnectionFactory�T�[�r�X�̃T�[�r�X��
     */
    public ServiceName getClientConnectionFactoryServiceName();
    
    /**
     * ���U�N���X�^�ɂ��邩�ǂ�����ݒ肷��B<p>
     * true�ɂ���ƕ��U�N���X�^�ƂȂ�A�N���C�A���g�́A�ڑ��䐔�̏��Ȃ��N���X�^�����o�ɐڑ����A�T�[�o�ɑ΂��ĕ��U���Đڑ�����B<br>
     * �f�t�H���g��false�ŁA�S�ẴN���C�A���g����n�ƂȂ��Ă���N���X�^�����o�ɐڑ�����B<br>
     *
     * @param isDistribute ���U�N���X�^�ɂ���ꍇtrue
     */
    public void setDistribute(boolean isDistribute);
    
    /**
     * ���U�N���X�^�ɂ��邩�ǂ����𔻒肷��B<p>
     *
     * @return true�̏ꍇ�A���U�N���X�^
     */
    public boolean isDistribute();
    
    /**
     * ���d�T�[�o�z�M���邩�ǂ�����ݒ肷��B<p>
     * true�ɂ���Ƒ��d�T�[�o�z�M�ƂȂ�A�N���C�A���g�͕����̃T�[�o���烁�b�Z�[�W�𓯎��Ɏ�M����B<br>
     * �f�t�H���g��false�ŁA�N���C�A���g�́A�ǂ����P�̃T�[�o�����M����B<br>
     *
     * @param isMultiple ���d�T�[�o�z�M����ꍇ�Atrue
     */
    public void setMultiple(boolean isMultiple);
    
    /**
     * ���d�T�[�o�z�M���邩�ǂ����𔻒肷��B<p>
     *
     * @return true�̏ꍇ�A���d�T�[�o�z�M����
     */
    public boolean isMultiple();
    
    /**
     * �������g����̃��b�Z�[�W����M���邩�ǂ�����ݒ肷��B<p>
     * �f�t�H���g�́Afalse�Ŏ�M���Ȃ��B<br>
     * 
     * @param isReceive ��M����ꍇtrue
     */
    public void setReceiveOwnMessage(boolean isReceive);
    
    /**
     * �������g����̃��b�Z�[�W����M���邩�ǂ����𔻒肷��B<p>
     * 
     * @return true�̏ꍇ�A��M����
     */
    public boolean isReceiveOwnMessage();
    
    /**
     * {@link ClientConnection#connect()}���s���ɁA�N���X�^�����o�����݂��Ȃ��Ă��ڑ��\�ȏ_��Ȑڑ��Ƃ��邩�ǂ�����ݒ肷��B<p>
     * �f�t�H���g�́Afalse�ŁA�N���X�^�����o�����݂��Ȃ��ꍇ�́A�ڑ��ł��Ȃ��B<br>
     *
     * @param isFlexible �_��Ȑڑ��Ƃ���ꍇ�Atrue
     */
    public void setFlexibleConnect(boolean isFlexible);
    
    /**
     * {@link ClientConnection#connect()}���s���ɁA�N���X�^�����o�����݂��Ȃ��Ă��ڑ��\�ȏ_��Ȑڑ��Ƃ��邩�ǂ����𔻒肷��B<p>
     *
     * @return true�̏ꍇ�A�_��Ȑڑ�
     */
    public boolean isFlexibleConnect();
    
    /**
     * �t�F�C���I�[�o�[���ɁA�ǂ̂��炢�̎���[ms]�����k���čđ��v�����o������ݒ肷��B<p>
     * �f�t�H���g�́A0[ms]�ők�炸�đ��v������B<br>
     *
     * @param time �k�鎞��[ms]
     */
    public void setFailoverBufferTime(long time);
    
    /**
     * �t�F�C���I�[�o�[���ɁA�ǂ̂��炢�̎���[ms]�����k���čđ��v�����o�������擾����B<p>
     *
     * @return �k�鎞��[ms]
     */
    public long getFailoverBufferTime();
    
    /**
     * �t�F�C���I�[�o�[���ɁA�Ō�Ɏ�M�������b�Z�[�W�̎�M�������N�_�ɁA�đ��v�������邩�ǂ�����ݒ肷��B<p>
     * �f�t�H���g�́Atrue�ŁA�Ō�Ɏ�M�������b�Z�[�W�̎�M�������N�_�ɁA�đ��v��������Bfalse�ɂ���ƁA���ݎ������N�_�ɂ���B<br>
     * 
     * @param isStartReceive �Ō�Ɏ�M�������b�Z�[�W�̎�M�������N�_�ɂ���ꍇ�Atrue
     */
    public void setStartReceiveFromLastReceiveTime(boolean isStartReceive);
    
    /**
     * �t�F�C���I�[�o�[���ɁA�Ō�Ɏ�M�������b�Z�[�W�̎�M�������N�_�ɁA�đ��v�������邩�ǂ����𔻒肷��B<p>
     * 
     * @return true�̏ꍇ�A�Ō�Ɏ�M�������b�Z�[�W�̎�M�������N�_�ɂ���
     */
    public boolean isStartReceiveFromLastReceiveTime();
    
    public void setClientConnectErrorMessageId(String id);
    public String getClientConnectErrorMessageId();
    
    public void setClientReconnectMessageId(String id);
    public String getClientReconnectMessageId();
    
    public void setClientNoConnectErrorMessageId(String id);
    public String getClientNoConnectErrorMessageId();
    
    public void setClientConnectionGetErrorMessageId(String id);
    public String getClientConnectionGetErrorMessageId();
}
