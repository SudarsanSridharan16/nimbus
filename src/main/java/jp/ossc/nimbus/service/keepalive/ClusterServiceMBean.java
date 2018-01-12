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
package jp.ossc.nimbus.service.keepalive;

import java.util.List;
import java.util.Set;

import jp.ossc.nimbus.core.*;

/**
 * {@link ClusterService}��MBean�C���^�t�F�[�X<p>
 * 
 * @author M.Takata
 * @see ClusterService
 */
public interface ClusterServiceMBean extends ServiceBaseMBean{
    
    /**
     * ���̃N���X�^���ғ��n�ɐ؂�ւ�����|�̃��O���b�Z�[�WID�B<p>
     */
    public static final String MSG_ID_CHANGE_OPERATION_SYSTEM = "CLST_00001";
    
    /**
     * ���̃N���X�^���ҋ@�n�ɐ؂�ւ�����|�̃��O���b�Z�[�WID�B<p>
     */
    public static final String MSG_ID_CHANGE_STANDBY_SYSTEM = "CLST_00002";
    
    /**
     * ���̃N���X�^���ғ��n�ɐ؂�ւ�Ȃ������|�̃��O���b�Z�[�WID�B<p>
     */
    public static final String MSG_ID_FAILED_CHANGE_ACTIVE_SYSTEM = "CLST_00003";
    
    /**
     * �N���X�^�Ԃ̃��b�Z�[�W����M�Ɏ��s�����|�̃��O���b�Z�[�WID�B<p>
     */
    public static final String MSG_ID_MESSAGE_IO_ERROR = "CLST_00004";
    
    /**
     * �����o���ǉ����ꂽ�|�̃��O���b�Z�[�WID�B<p>
     */
    public static final String MSG_ID_MESSAGE_MEMBER_ADD = "CLST_00005";
    
    /**
     * �����o���폜���ꂽ�|�̃��O���b�Z�[�WID�B<p>
     */
    public static final String MSG_ID_MESSAGE_MEMBER_REMOVE = "CLST_00006";
    
    /**
     * �����o���ύX���ꂽ�|�̃��O���b�Z�[�WID�B<p>
     */
    public static final String MSG_ID_MESSAGE_MEMBER_CHANGE = "CLST_00007";
    
    /**
     * �N���C�A���g���ǉ����ꂽ�|�̃��O���b�Z�[�WID�B<p>
     */
    public static final String MSG_ID_MESSAGE_CLIENT_ADD = "CLST_00008";
    
    /**
     * �N���C�A���g���폜���ꂽ�|�̃��O���b�Z�[�WID�B<p>
     */
    public static final String MSG_ID_MESSAGE_CLIENT_REMOVE = "CLST_00009";
    
    /**
     * �����o���������ꂽ�|�̃��O���b�Z�[�WID�B<p>
     */
    public static final String MSG_ID_MESSAGE_MEMBAER_MERGE = "CLST_00010";
    
    /**
     * �N���X�^��g�ރT�[�r�X�̃T�[�r�X����ݒ肷��B<p>
     *
     * @param name �T�[�r�X��
     */
    public void setTargetServiceName(ServiceName name);
    
    /**
     * �N���X�^��g�ރT�[�r�X�̃T�[�r�X�����擾����B<p>
     *
     * @return �T�[�r�X��
     */
    public ServiceName getTargetServiceName();
    
    /**
     * {@link ClusterListener}�T�[�r�X�̃T�[�r�X����ݒ肷��B<p>
     *
     * @param names ClusterListener�T�[�r�X�̃T�[�r�X��
     */
    public void setClusterListenerServiceNames(ServiceName[] names);
    
    /**
     * {@link ClusterListener}�T�[�r�X�̃T�[�r�X�����擾����B<p>
     *
     * @return ClusterListener�T�[�r�X�̃T�[�r�X��
     */
    public ServiceName[] getClusterListenerServiceNames();
    
    /**
     * �}���`�L���X�g�O���[�v��IP�A�h���X��ݒ肷��B<p>
     *
     * @param ip IP�A�h���X
     */
    public void setMulticastGroupAddress(String ip);
    
    /**
     * �}���`�L���X�g�O���[�v��IP�A�h���X���擾����B<p>
     *
     * @return IP�A�h���X
     */
    public String getMulticastGroupAddress();
    
    /**
     * �}���`�L���X�g�O���[�v�̃|�[�g�ԍ���ݒ肷��B<p>
     * �f�t�H���g�́A1500�B<br>
     *
     * @param port �|�[�g�ԍ�
     */
    public void setMulticastPort(int port);
    
    /**
     * �}���`�L���X�g�O���[�v�̃|�[�g�ԍ����擾����B<p>
     *
     * @return �|�[�g�ԍ�
     */
    public int getMulticastPort();
    
    /**
     * ���j�L���X�g�����o�[��IP�A�h���X�y�у|�[�g�ԍ���ݒ肷��B<p>
     *
     * @param addresses "IP�A�h���X:�|�[�g�ԍ�"�̔z��B�|�[�g�ԍ����w�肳��Ă��Ȃ��ꍇ�́A{@link #getUnicastPort()}���g�p����B
     */
    public void setUnicastMemberAddresses(String[] addresses);
    
    /**
     * ���j�L���X�g�����o�[��IP�A�h���X�y�у|�[�g�ԍ����擾����B<p>
     *
     * @return "IP�A�h���X:�|�[�g�ԍ�"�̔z��
     */
    public String[] getUnicastMemberAddresses();
    
    /**
     * ���j�L���X�g�̃|�[�g�ԍ���ݒ肷��B<p>
     * �f�t�H���g�́A1500�B<br>
     *
     * @param port �|�[�g�ԍ�
     */
    public void setUnicastPort(int port);
    
    /**
     * ���j�L���X�g�̃|�[�g�ԍ����擾����B<p>
     *
     * @return �|�[�g�ԍ�
     */
    public int getUnicastPort();
    
    /**
     * �N���C�A���g���[�h�̍ۂɁA���j�L���X�g�̎�M�|�[�g�ԍ���C�ӂ̃|�[�g�ԍ��ɂ��邩�ǂ�����ݒ肷��B<p>
     * �f�t�H���g�́Afalse�ŁA{@link #getUnicastPort()}���g�p����B<br>
     *
     * @param isAnonymous �C�ӂ̃|�[�g�ԍ��ɂ���ꍇ�Atrue
     */
    public void setAnonymousUnicastPort(boolean isAnonymous);
    
    /**
     * �N���C�A���g���[�h�̍ۂɁA���j�L���X�g�̎�M�|�[�g�ԍ���C�ӂ̃|�[�g�ԍ��ɂ��邩�ǂ����𔻒肷��B<p>
     *
     * @return true�̏ꍇ�A�C�ӂ̃|�[�g�ԍ��ɂ���
     */
    public boolean isAnonymousUnicastPort();
    
    /**
     * �\�P�b�g�̎�M�o�b�t�@��ݒ肷��B<p>
     *
     * @param size �\�P�b�g�̎�M�o�b�t�@
     */
    public void setSocketReceiveBufferSize(int size);
    
    /**
     * �\�P�b�g�̎�M�o�b�t�@���擾����B<p>
     *
     * @return �\�P�b�g�̎�M�o�b�t�@
     */
    public int getSocketReceiveBufferSize();
    
    /**
     * �\�P�b�g�̑��M�o�b�t�@��ݒ肷��B<p>
     *
     * @param size �\�P�b�g�̑��M�o�b�t�@
     */
    public void setSocketSendBufferSize(int size);
    
    /**
     * �\�P�b�g�̑��M�o�b�t�@���擾����B<p>
     *
     * @return �\�P�b�g�̑��M�o�b�t�@
     */
    public int getSocketSendBufferSize();
    
    /**
     * �p�P�b�g�̎�M�o�b�t�@�T�C�Y��ݒ肷��B<p>
     * �f�t�H���g�́A1024�o�C�g�B<br>
     *
     * @param size �o�b�t�@�T�C�Y
     */
    public void setReceiveBufferSize(int size);
    
    /**
     * �p�P�b�g�̎�M�o�b�t�@�T�C�Y���擾����B<p>
     *
     * @return �o�b�t�@�T�C�Y
     */
    public int getReceiveBufferSize();
    
    /**
     * �}���`�L���X�g�p�P�b�g�̗L�����Ԃ�ݒ肷��B<p>
     *
     * @param ttl �L������
     */
    public void setTimeToLive(int ttl);
    
    /**
     * �}���`�L���X�g�p�P�b�g�̗L�����Ԃ��擾����B<p>
     *
     * @return �L������
     */
    public int getTimeToLive();
    
    /**
     * ���[�J��IP�A�h���X��ݒ肷��B<p>
     *
     * @param ip IP�A�h���X
     */
    public void setLocalAddress(String ip);
    
    /**
     * ���[�J��IP�A�h���X���擾����B<p>
     *
     * @return IP�A�h���X
     */
    public String getLocalAddress();
    
    /**
     * �o�C���h�A�h���X��ݒ肷��B<p>
     *
     * @param ip IP�A�h���X
     */
    public void setBindAddress(String ip);
    
    /**
     * �o�C���hIP�A�h���X���擾����B<p>
     *
     * @return IP�A�h���X
     */
    public String getBindAddress();
    
    /**
     * �l�b�g���[�N�C���^�t�F�[�X��ݒ肷��B<p>
     *
     * @param names �l�b�g���[�N�C���^�t�F�[�X��
     */
    public void setNetworkInterfaces(String[] names);
    
    /**
     * �l�b�g���[�N�C���^�t�F�[�X���擾����B<p>
     *
     * @return �l�b�g���[�N�C���^�t�F�[�X��
     */
    public String[] getNetworkInterfaces();
    
    /**
     * �������g��IP�A�h���X�ȊO�̎��ʕ⑫����ݒ肷��B<p>
     *
     * @param opt ���ʕ⑫���
     */
    public void setOption(java.io.Serializable opt);
    
    /**
     * �������g��IP�A�h���X�ȊO�̎��ʕ⑫�����擾����B<p>
     *
     * @return ���ʕ⑫���
     */
    public java.io.Serializable getOption();
    
    /**
     * �������g��IP�A�h���X�ȊO�̎��ʕ⑫����ݒ肷��B<p>
     *
     * @param key �L�[
     * @param opt ���ʕ⑫���
     */
    public void setOption(String key, java.io.Serializable opt);
    
    /**
     * �������g��IP�A�h���X�ȊO�̎��ʕ⑫�����擾����B<p>
     *
     * @param key �L�[
     * @return ���ʕ⑫���
     */
    public java.io.Serializable getOption(String key);
    
    /**
     * �ׂ̃N���X�^�T�[�r�X�ƃn�[�g�r�[�g���s���Ԋu[ms]��ݒ肷��B<p>
     * �f�t�H���g�́A1000[ms]�B<br>
     *
     * @param interval �n�[�g�r�[�g���s���Ԋu[ms]
     */
    public void setHeartBeatInterval(long interval);
    
    /**
     * �ׂ̃N���X�^�T�[�r�X�ƃn�[�g�r�[�g���s���Ԋu[ms]���擾����B<p>
     *
     * @return �n�[�g�r�[�g���s���Ԋu[ms]
     */
    public long getHeartBeatInterval();
    
    /**
     * �ׂ̃N���X�^�T�[�r�X�Ƀn�[�g�r�[�g�����N�G�X�g�������̃^�C���A�E�g[ms]��ݒ肷��B<p>
     * �f�t�H���g�́A500[ms]�B<br>
     *
     * @param timeout �^�C���A�E�g[ms]
     */
    public void setHeartBeatResponseTimeout(long timeout);
    
    /**
     * �ׂ̃N���X�^�T�[�r�X�Ƀn�[�g�r�[�g�����N�G�X�g�������̃^�C���A�E�g[ms]���擾����B<p>
     *
     * @return �^�C���A�E�g[ms]
     */
    public long getHeartBeatResponseTimeout();
    
    /**
     * �ׂ̃N���X�^�T�[�r�X�Ƃ̃n�[�g�r�[�g�Ɏ��s�����ꍇ�ɁA���肪���񂾂ƌ��Ȃ��܂ł̃��g���C�񐔂�ݒ肷��B<p>
     * �f�t�H���g�́A1�B<br>
     *
     * @param count ���g���C��
     */
    public void setHeartBeatRetryCount(int count);
    
    /**
     * �ׂ̃N���X�^�T�[�r�X�Ƃ̃n�[�g�r�[�g�Ɏ��s�����ꍇ�ɁA���肪���񂾂ƌ��Ȃ��܂ł̃��g���C�񐔂��擾����B<p>
     *
     * @return ���g���C��
     */
    public int getHeartBeatRetryCount();
    
    /**
     * �N���X�^�ɎQ�����郊�N�G�X�g���s�������̃^�C���A�E�g[ms]��ݒ肷��B<p>
     * �f�t�H���g�́A500[ms]�B<br>
     *
     * @param timeout �^�C���A�E�g[ms]
     */
    public void setAddMemberResponseTimeout(long timeout);
    
    /**
     * �N���X�^�ɎQ�����郊�N�G�X�g���s�������̃^�C���A�E�g[ms]���擾����B<p>
     *
     * @return �^�C���A�E�g[ms]
     */
    public long getAddMemberResponseTimeout();
    
    /**
     * �N���X�^�ɎQ�����郊�N�G�X�g���s�������̃��g���C�񐔂�ݒ肷��B<p>
     * �f�t�H���g�́A0�Ń��g���C���Ȃ��B<br>
     *
     * @param count ���g���C��
     */
    public void setAddMemberRetryCount(int count);
    
    /**
     * �N���X�^�ɎQ�����郊�N�G�X�g���s�������̃��g���C�񐔂��擾����B<p>
     *
     * @return ���g���C��
     */
    public int getAddMemberRetryCount();
    
    /**
     * ��M�����p�P�b�g����������Ă����ꍇ�ɁA�������ꂽ�c��p�P�b�g�����������Ƃ݂Ȃ��^�C���A�E�g[ms]��ݒ肷��B<p>
     * �f�t�H���g�́A500[ms]�B<br>
     *
     * @param timeout �^�C���A�E�g[ms]
     */
    public void setLostTimeout(long timeout);
    
    /**
     * ��M�����p�P�b�g����������Ă����ꍇ�ɁA�������ꂽ�c��p�P�b�g�����������Ƃ݂Ȃ��^�C���A�E�g[ms]���擾����B<p>
     *
     * @return �^�C���A�E�g[ms]
     */
    public long getLostTimeout();
    
    /**
     * �N���X�^�N���C�A���g���ǂ�����ݒ肷��B<p>
     * �N���X�^�N���C�A���g�ɂȂ�ꍇ�́Atrue�ɐݒ肷��B�f�t�H���g�́Afalse�B<br>
     * �N���X�^�N���C�A���g�́A�N���X�^�̃����o�ɂ͎Q�������N���X�^�̏󋵒ʒm�̂ݎ󂯂�B<br>
     *
     * @param isClient �N���X�^�N���C�A���g�ɂȂ�ꍇ�́Atrue
     */
    public void setClient(boolean isClient);
    
    /**
     * �N���X�^�N���C�A���g���ǂ����𔻒肷��B<p>
     *
     * @return true�̏ꍇ�A�N���X�^�N���C�A���g
     */
    public boolean isClient();
    
    /**
     * �T�[�r�X�̊J�n���ɁA�N���X�^�ɎQ�����邩�ǂ�����ݒ肷��B<p>
     * �f�t�H���g�́Atrue�B<br>
     *
     * @param isJoin �Q������ꍇtrue
     */
    public void setJoinOnStart(boolean isJoin);
    
    /**
     * �T�[�r�X�̊J�n���ɁA�N���X�^�ɎQ�����邩�ǂ����𔻒肷��B<p>
     *
     * @return true�̏ꍇ�A�Q������
     */
    public boolean isJoinOnStart();
    
    /**
     * �N���X�^�̃X���b�h�̗D��x��ݒ肷��B<p>
     * �f�t�H���g��-1�ŁA�X���b�h�ɖ����I�ɗD��x��ݒ肵�Ȃ��B<br>
     *
     * @param priority �X���b�h�̗D��x
     */
    public void setThreadPriority(int priority);
    
    /**
     * �N���X�^�̃X���b�h�̗D��x���擾����B<p>
     *
     * @return �X���b�h�̗D��x
     */
    public int getThreadPriority();
    
    /**
     * �N���X�^�ɎQ�����Ă��邩�ǂ����𔻒肷��B<p>
     *
     * @return true�̏ꍇ�A�Q�����Ă���
     */
    public boolean isJoin();
    
    /**
     * �N���X�^�̃��C���ł��邩�ǂ����𔻒肷��B<p>
     *
     * @return true�̏ꍇ���C��
     */
    public boolean isMain();
    
    /**
     * �N���X�^�̃��C���^�f�ł��邩�ǂ����𔻒肷��B<p>
     *
     * @return true�̏ꍇ���C���^�f
     */
    public boolean isMainDoubt();
    
    /**
     * �N���X�^�̃��C���^�f�ł��邩�ǂ�����ݒ肷��B<p>
     *
     * @param isMainDoubt ���C���^�f�̏ꍇ�Atrue
     */
    public void setMainDoubt(boolean isMainDoubt);
    
    /**
     * ���݂̃N���X�^�����o��UID�̃��X�g���擾����B<p>
     *
     * @return ���݂̃N���X�^�����o
     */
    public List getMembers();
    
    /**
     * ���݂̃N���C�A���g�����o��UID�̃��X�g���擾����B<p>
     *
     * @return ���݂̃N���C�A���g�����o
     */
    public Set getClientMembers();
    
    /**
     * ���̃T�[�r�X��UID���擾����B<p>
     *
     * @return UID
     */
    public Object getUID();
    
    /**
     * �N���X�^�ɎQ������B<p>
     */
    public void join() throws Exception;
    
    /**
     * �N���X�^���痣�E����B<p>
     */
    public void leave();
    
    /**
     * 1���b�Z�[�W������̍ő啪�������擾����B<p>
     *
     * @return �ő啪����
     */
    public int getMaxWindowCount();
}