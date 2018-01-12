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
package jp.ossc.nimbus.service.scp;

import jp.ossc.nimbus.core.ServiceBaseMBean;
import jp.ossc.nimbus.core.ServiceName;


/**
 * {@link ClusterSCPClientFactoryService}��MBean�C���^�t�F�[�X<p>
 * 
 * @author M.Takata
 * @see ClusterSCPClientFactoryService
 */
public interface ClusterSCPClientFactoryServiceMBean extends ServiceBaseMBean{
    
    /**
     * ������SCP�T�[�o�̂����A�P��݂̂��ғ��n�ƂȂ郂�[�h�B<p>
     * �N���X�^�����ꂽSCP�T�[�o�̂�����1��ɂ����ڑ�����B<br>
     * �ڑ��̗D�揇�ʂ́A{@link #setSCPClientFactoryServiceNames(ServiceName[])}�̏����Ɉˑ�����B<br>
     */
    public static final int CLUSTER_MODE_ACTIVE_STANDBY = 1;
    
    /**
     * ������SCP�T�[�o�̑S�Ă��ғ��n�ƂȂ郂�[�h�B<p>
     * �N���X�^�����ꂽSCP�T�[�o�̑S�Ăɐڑ�����B<br>
     * �ڑ��Ɏ��s����SCP�T�[�o�����݂��Ă��A���Ȃ��Ƃ�1��ɐڑ��ł���Ώ����͑��s����B<br>
     */
    public static final int CLUSTER_MODE_ACTIVE_ACTIVE  = 2;
    
    /**
     * �f�t�H���g�̐ڑ����s���̃��O���b�Z�[�WID�B<p>
     */
    public static final String MSG_ID_CONNECT_ERROR  = "CSCP_00001";
    
    /**
     * �f�t�H���g�̏����X�L�b�v���̃��O���b�Z�[�WID�B<p>
     */
    public static final String MSG_ID_SKIP           = "CSCP_00002";
    
    /**
     * �N���X�^������{@link SCPClientFactory}�T�[�r�X�̃T�[�r�X���z���ݒ肷��B<p>
     *
     * @param names {@link SCPClientFactory}�T�[�r�X�̃T�[�r�X���z��
     */
    public void setSCPClientFactoryServiceNames(ServiceName[] names);
    
    /**
     * �N���X�^������{@link SCPClientFactory}�T�[�r�X�̃T�[�r�X�����擾����B<p>
     *
     * @return {@link SCPClientFactory}�T�[�r�X�̃T�[�r�X���z��
     */
    public ServiceName[] getSCPClientFactoryServiceNames();
    
    /**
     * �N���X�^���[�h��ݒ肷��B<p>
     * �f�t�H���g�́A{@link #CLUSTER_MODE_ACTIVE_STANDBY}�B<br>
     *
     * @param mode �N���X�^���[�h
     * @see #CLUSTER_MODE_ACTIVE_STANDBY
     * @see #CLUSTER_MODE_ACTIVE_ACTIVE
     */
    public void setClusterMode(int mode) throws IllegalArgumentException;
    
    /**
     * �N���X�^���[�h���擾����B<p>
     *
     * @return �N���X�^���[�h
     */
    public int getClusterMode();
    
    /**
     * �ڑ����s���̃��O���b�Z�[�WID��ݒ肷��B<p>
     *
     * @param id ���O���b�Z�[�WID
     * @see #MSG_ID_CONNECT_ERROR
     */
    public void setConnectErrorMessageId(String id);
    
    /**
     * �ڑ����s���̃��O���b�Z�[�WID���擾����B<p>
     *
     * @return ���O���b�Z�[�WID
     */
    public String getConnectErrorMessageId();
    
    /**
     * �����X�L�b�v���̃��O���b�Z�[�WID��ݒ肷��B<p>
     *
     * @param id ���O���b�Z�[�WID
     * @see #MSG_ID_CONNECT_ERROR
     */
    public void setSkipMessageId(String id);
    
    /**
     * �����X�L�b�v���̃��O���b�Z�[�WID���擾����B<p>
     *
     * @return ���O���b�Z�[�WID
     */
    public String getSkipMessageId();
}
