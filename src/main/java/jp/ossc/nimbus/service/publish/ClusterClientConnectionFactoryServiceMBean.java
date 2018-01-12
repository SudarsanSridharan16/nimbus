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
 * {@link ClusterClientConnectionFactoryService}��MBean�C���^�t�F�[�X<p>
 * 
 * @author M.Takata
 * @see ClusterClientConnectionFactoryService
 */
public interface ClusterClientConnectionFactoryServiceMBean extends ServiceBaseMBean{
    
    public static final String MSG_ID_CONNECT_ERROR = "PCCF_00001";
    
    /**
     * {@link jp.ossc.nimbus.service.keepalive.ClusterService ClusterService}�T�[�r�X�̃T�[�r�X����ݒ肷��B<p>
     * �N���X�^�̃����o�[��񂩂�A{@link ClientConnectionFactory}�����[�g�I�u�W�F�N�g���擾���鎖�ŁA�N���X�^�ɎQ�����Ă���ClientConnectionFactory���N���C�A���g���ŋ��L����B<br>
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
    
    public void setConnectErrorMessageId(String id);
    public String getConnectErrorMessageId();
}
