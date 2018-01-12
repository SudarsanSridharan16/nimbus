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
package jp.ossc.nimbus.service.context;

import java.util.Set;
import java.util.List;

import jp.ossc.nimbus.core.ServiceBaseMBean;
import jp.ossc.nimbus.core.ServiceName;

/**
 * {@link DistributedSharedContextService}��MBean�C���^�t�F�[�X�B<p>
 * 
 * @author M.Takata
 * @see DistributedSharedContextService
 */
public interface DistributedSharedContextServiceMBean extends ServiceBaseMBean{
    
    /**
     * �f�t�H���g�̃T�u�W�F�N�g�B<p>
     */
    public static final String DEFAULT_SUBJECT = "DistributedSharedContext";
    
    /**
     * �N���C�A���g���[�h�̃T�u�W�F�N�g��u���B<p>
     */
    public static final String CLIENT_SUBJECT_SUFFIX = ".Client";
    
    /**
     * �f�[�^�̕��U����ݒ肷��B<p>
     * �f�t�H���g�́A2�B<br>
     *
     * @param size ���U��
     */
    public void setDistributedSize(int size) throws IllegalArgumentException;
    
    /**
     * �f�[�^�̕��U�����擾����B<p>
     *
     * @return ���U��
     */
    public int getDistributedSize();
    
    /**
     * �f�[�^�̕�������ݒ肷��B<p>
     * �f�t�H���g�́A2�B<br>
     *
     * @param size ������
     */
    public void setReplicationSize(int size) throws IllegalArgumentException;
    
    /**
     * �f�[�^�̕��������擾����B<p>
     *
     * @return ������
     */
    public int getReplicationSize();
    
    /**
     * {@link SharedContextKeyDistributor}�T�[�r�X�̃T�[�r�X����ݒ肷��B<p>
     * �w�肵�Ȃ��ꍇ�́A{@link MD5HashSharedContextKeyDistributorService}���K�p�����B<br>
     * 
     * @param name SharedContextKeyDistributor�T�[�r�X�̃T�[�r�X��
     */
    public void setSharedContextKeyDistributorServiceName(ServiceName name);
    
    /**
     * {@link SharedContextKeyDistributor}�T�[�r�X�̃T�[�r�X�����擾����B<p>
     * 
     * @return SharedContextKeyDistributor�T�[�r�X�̃T�[�r�X��
     */
    public ServiceName getSharedContextKeyDistributorServiceName();
    
    /**
     * {@link jp.ossc.nimbus.service.publish.RequestConnectionFactoryService RequestConnectionFactoryService}�T�[�r�X�̃T�[�r�X����ݒ肷��B<p>
     * 
     * @param name RequestConnectionFactoryService�T�[�r�X�̃T�[�r�X��
     */
    public void setRequestConnectionFactoryServiceName(ServiceName name);
    
    /**
     * {@link jp.ossc.nimbus.service.publish.RequestConnectionFactoryService RequestConnectionFactoryService}�T�[�r�X�̃T�[�r�X�����擾����B<p>
     * 
     * @return RequestConnectionFactoryService�T�[�r�X�̃T�[�r�X��
     */
    public ServiceName getRequestConnectionFactoryServiceName();
    
    /**
     * {@link jp.ossc.nimbus.service.keepalive.ClusterService ClusterService}�T�[�r�X�̃T�[�r�X����ݒ肷��B<p>
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
     * �N���C�A���g���[�h����{@link jp.ossc.nimbus.service.cache.CacheMap CacheMap}�T�[�r�X�̃T�[�r�X����ݒ肷��B<p>
     * �R���e�L�X�g����CacheMap�Ɋi�[���ăf�[�^�̂��ӂꐧ����s���ꍇ�A�ݒ肷��B<br>
     * 
     * @param name CacheMap�T�[�r�X�̃T�[�r�X��
     */
    public void setClientCacheMapServiceName(ServiceName name);
    
    /**
     * �N���C�A���g���[�h����{@link jp.ossc.nimbus.service.cache.CacheMap CacheMap}�T�[�r�X�̃T�[�r�X�����擾����B<p>
     * 
     * @return CacheMap�T�[�r�X�̃T�[�r�X��
     */
    public ServiceName getClientCacheMapServiceName();
    
    /**
     * �T�[�o���[�h����{@link jp.ossc.nimbus.service.cache.CacheMap CacheMap}�T�[�r�X�̃T�[�r�X����ݒ肷��B<p>
     * �R���e�L�X�g����CacheMap�Ɋi�[����ꍇ�A�ݒ肷��B<br>
     * 
     * @param name CacheMap�T�[�r�X�̃T�[�r�X��
     */
    public void setServerCacheMapServiceName(ServiceName name);
    
    /**
     * �T�[�o���[�h����{@link jp.ossc.nimbus.service.cache.CacheMap CacheMap}�T�[�r�X�̃T�[�r�X�����擾����B<p>
     * 
     * @return CacheMap�T�[�r�X�̃T�[�r�X��
     */
    public ServiceName getServerCacheMapServiceName();
    
    /**
     * {@link ContextStore}�T�[�r�X�̃T�[�r�X����ݒ肷��B<p>
     * 
     * @param name ContextStore�T�[�r�X�̃T�[�r�X��
     */
    public void setContextStoreServiceName(ServiceName name);
    
    /**
     * {@link ContextStore}�T�[�r�X�̃T�[�r�X�����擾����B<p>
     * 
     * @return ContextStore�T�[�r�X�̃T�[�r�X��
     */
    public ServiceName getContextStoreServiceName();
    
    /**
     * {@link jp.ossc.nimbus.service.interpreter.Interpreter Interpreter}�T�[�r�X�̃T�[�r�X����ݒ肷��B<p>
     * �C���^�[�v���^���s���T�|�[�g����ꍇ�ɁA�N�G�������߂���Interpreter��ݒ肷��B<br>
     * 
     * @param name Interpreter�T�[�r�X�̃T�[�r�X��
     */
    public void setInterpreterServiceName(ServiceName name);
    
    /**
     * {@link jp.ossc.nimbus.service.interpreter.Interpreter Interpreter}�T�[�r�X�̃T�[�r�X�����擾����B<p>
     * 
     * @return Interpreter�T�[�r�X�̃T�[�r�X��
     */
    public ServiceName getInterpreterServiceName();
    
    /**
     * �C���^�[�v���^���s���s���ۂɁA�N�G�����Ŏg�p����R���e�L�X�g�̕ϐ�����ݒ肷��B<p>
     * �f�t�H���g�́A"context"�B<br>
     * 
     * @param name �N�G�����Ŏg�p����R���e�L�X�g�̕ϐ���
     */
    public void setInterpretContextVariableName(String name);
    
    /**
     * �C���^�[�v���^���s���s���ۂɁA�N�G�����Ŏg�p����R���e�L�X�g�̕ϐ������擾����B<p>
     * 
     * @return �N�G�����Ŏg�p����R���e�L�X�g�̕ϐ���
     */
    public String getInterpretContextVariableName();
    
    /**
     * �C���^�[�v���^���s���s���X���b�h����ݒ肷��B<p>
     * �f�t�H���g�́A0�ŗv����M�X���b�h�ł��̂܂܏�������B<br>
     *
     * @param size �C���^�[�v���^���s�X���b�h��
     */
    public void setExecuteThreadSize(int size);
    
    /**
     * �C���^�[�v���^���s���s���X���b�h�����擾����B<p>
     *
     * @return �C���^�[�v���^���s�X���b�h��
     */
    public int getExecuteThreadSize();
    
    /**
     * �C���^�[�v���^���s�����ɏ������邽�߂�{@link jp.ossc.nimbus.service.queue.Queue Queue}�T�[�r�X�̃T�[�r�X����ݒ肷��B<p>
     * �C���^�[�v���^���s���T�|�[�g����ꍇ�ɁA�C���^�[�v���^���s��񓯊��ɂ����ꍇ�̗v���L���[��ݒ肷��B�w�肵�Ȃ��ꍇ�́A�����L���[���g�p�����B<br>
     * 
     * @param name Queue�T�[�r�X�̃T�[�r�X��
     */
    public void setExecuteQueueServiceName(ServiceName name);
    
    /**
     * �C���^�[�v���^���s�����ɏ������邽�߂�{@link jp.ossc.nimbus.service.queue.Queue Queue}�T�[�r�X�̃T�[�r�X�����擾����B<p>
     * 
     * @return Queue�T�[�r�X�̃T�[�r�X��
     */
    public ServiceName getExecuteQueueServiceName();
    
    /**
     * ���U�����m�[�h�ɕ���ŗv�����s���X���b�h����ݒ肷��B<p>
     * �f�t�H���g�́A0�ŗv���X���b�h�Œ���ɏ�������B<br>
     *
     * @param size ����ŗv�����s���X���b�h��
     */
    public void setParallelRequestThreadSize(int size);
    
    /**
     * ���U�����m�[�h�ɕ���ŗv�����s���X���b�h�����擾����B<p>
     *
     * @return ����ŗv�����s���X���b�h��
     */
    public int getParallelRequestThreadSize();
    
    /**
     * ���U�����m�[�h�ɕ���ŗv�����s�����߂Ɏg�p����{@link jp.ossc.nimbus.service.queue.Queue Queue}�T�[�r�X�̃T�[�r�X����ݒ肷��B<p>
     * 
     * @param name Queue�T�[�r�X�̃T�[�r�X��
     */
    public void setParallelRequestQueueServiceName(ServiceName name);
    
    /**
     * ���U�����m�[�h�ɕ���ŗv�����s�����߂Ɏg�p����{@link jp.ossc.nimbus.service.queue.Queue Queue}�T�[�r�X�̃T�[�r�X�����擾����B<p>
     * 
     * @return Queue�T�[�r�X�̃T�[�r�X��
     */
    public ServiceName getParallelRequestQueueServiceName();
    
    /**
     * {@link SharedContextTransactionManager}�T�[�r�X�̃T�[�r�X����ݒ肷��B<p>
     * �g�����U�N�V�������s���T�|�[�g����ꍇ�ɐݒ肷��B�w�肵�Ȃ��ꍇ�́A�g�����U�N�V�����ɎQ�����Ȃ��B<br>
     * 
     * @param name SharedContextTransactionManager�T�[�r�X�̃T�[�r�X��
     */
    public void setSharedContextTransactionManagerServiceName(ServiceName name);
    
    /**
     * {@link SharedContextTransactionManager}�T�[�r�X�̃T�[�r�X�����擾����B<p>
     * 
     * @return SharedContextTransactionManager�T�[�r�X�̃T�[�r�X��
     */
    public ServiceName getSharedContextTransactionManagerServiceName();
    
    /**
     * �T�u�W�F�N�g��ݒ肷��B<p>
     * �f�t�H���g�́A{@link #DEFAULT_SUBJECT}�B
     *
     * @param subject �T�u�W�F�N�g
     */
    public void setSubject(String subject);
    
    /**
     * �T�u�W�F�N�g���擾����B<p>
     *
     * @return �T�u�W�F�N�g
     */
    public String getSubject();
    
    /**
     * �N���C�A���g/�T�[�o���[�h��ݒ肷��B<p>
     * �f�t�H���g�́Afalse�ŃT�[�o���[�h�B<br>
     *
     * @param isClient �N���C�A���g���[�h�̏ꍇ�Atrue
     * @exception SharedContextSendException ���U�T�[�o�ւ̃��b�Z�[�W���M�Ɏ��s�����ꍇ
     * @exception SharedContextTimeoutException ���U�T�[�o����̉����҂��Ń^�C���A�E�g�����ꍇ
     */
    public void setClient(boolean isClient) throws SharedContextSendException, SharedContextTimeoutException;
    
    /**
     * �N���C�A���g/�T�[�o���[�h�𔻒肷��B<p>
     *
     * @return true�̏ꍇ�A�N���C�A���g���[�h
     */
    public boolean isClient();
    
    /**
     * ���n�b�V�����L�����ǂ�����ݒ肷��B<p>
     * �f�t�H���g�́Atrue�ŗL���B<br>
     *
     * @param isEnabled �L���ɂ���ꍇ�Atrue
     * @exception SharedContextSendException ���U�T�[�o�ւ̃��b�Z�[�W���M�Ɏ��s�����ꍇ
     * @exception SharedContextTimeoutException ���U�T�[�o����̉����҂��Ń^�C���A�E�g�����ꍇ
     */
    public void setRehashEnabled(boolean isEnabled) throws SharedContextSendException, SharedContextTimeoutException;
    
    /**
     * ���n�b�V�����L�����ǂ����𔻒肷��B<p>
     *
     * @return true�̏ꍇ�A�N���C�A���g���[�h
     */
    public boolean isRehashEnabled();
    
    /**
     * �������̃^�C���A�E�g[ms]��ݒ肷��B<p>
     * �f�t�H���g�́A5000[ms]�B<br>
     *
     * @param timeout �^�C���A�E�g[ms]
     */
    public void setSynchronizeTimeout(long timeout);
    
    /**
     * �������̃^�C���A�E�g[ms]���擾����B<p>
     *
     * @return �^�C���A�E�g[ms]
     */
    public long getSynchronizeTimeout();
    
    /**
     * �R���e�L�X�g���U�̍Ĕz�u���̃^�C���A�E�g[ms]��ݒ肷��B<p>
     * �f�t�H���g�́A10000[ms]�B<br>
     *
     * @param timeout �^�C���A�E�g[ms]
     */
    public void setRehashTimeout(long timeout);
    
    /**
     * �R���e�L�X�g���U�̍Ĕz�u���̃^�C���A�E�g[ms]���擾����B<p>
     *
     * @return �^�C���A�E�g[ms]
     */
    public long getRehashTimeout();
    
    /**
     * �^�C���A�E�g���w�肵�Ȃ����\�b�h���Ăяo�����ꍇ�ɓK�p�����^�C���A�E�g[ms]��ݒ肷��B<p>
     * �f�t�H���g�́A1000[ms]�B<br>
     *
     * @param timeout �^�C���A�E�g[ms]
     */
    public void setDefaultTimeout(long timeout);
    
    /**
     * �^�C���A�E�g���w�肵�Ȃ����\�b�h���Ăяo�����ꍇ�ɓK�p�����^�C���A�E�g[ms]���擾����B<p>
     *
     * @return �^�C���A�E�g[ms]
     */
    public long getDefaultTimeout();
    
    /**
     * �f�[�^�m�[�h���T�[�r�X�Ƃ��ēo�^���邩�ǂ�����ݒ肷��B<p>
     * �f�t�H���g�́Afalse�œo�^���Ȃ��B<br>
     *
     * @param isManage �o�^����ꍇ�́Atrue
     */
    public void setManagedDataNode(boolean isManage);
    
    /**
     * �f�[�^�m�[�h���T�[�r�X�Ƃ��ēo�^���邩�ǂ����𔻒肷��B<p>
     *
     * @return true�̏ꍇ�A�o�^����
     */
    public boolean isManagedDataNode();
    
    /**
     * �T�[�r�X�̊J�n���ɑ��݂���m�[�h���S�Đڑ������̂�ҋ@���邩�ǂ�����ݒ肷��B<p>
     * �f�t�H���g�́Afalse�ŁA���C���m�[�h�̐ڑ��̂ݑҋ@����B<br>
     *
     * @param isWait �T�[�r�X�̊J�n���ɑ��݂���m�[�h���S�Đڑ������̂�ҋ@����ꍇ�Atrue
     */
    public void setWaitConnectAllOnStart(boolean isWait);
    
    /**
     * �T�[�r�X�̊J�n���ɑ��݂���m�[�h���S�Đڑ������̂�ҋ@���邩�ǂ����𔻒肷��B<p>
     *
     * @return true�̏ꍇ�A�T�[�r�X�̊J�n���ɑ��݂���m�[�h���S�Đڑ������̂�ҋ@����
     */
    public boolean isWaitConnectAllOnStart();
    
    /**
     * �T�[�r�X�̊J�n���ɁA����m�[�h���ڑ�����̂�ҋ@���鎞��[ms]��ݒ肷��B<p>
     * �f�t�H���g�́A60�b�B<br>
     *
     * @param timeout ����m�[�h���ڑ�����̂�ҋ@���鎞��[ms]
     */
    public void setWaitConnectTimeout(long timeout);
    
    /**
     * �T�[�r�X�̊J�n���ɁA����m�[�h���ڑ�����̂�ҋ@���鎞��[ms]���擾����B<p>
     *
     * @return ����m�[�h���ڑ�����̂�ҋ@���鎞��[ms]
     */
    public long getWaitConnectTimeout();
    
    /**
     * �T�[�r�X�̊J�n���ɁA{@link ContextStore}�T�[�r�X���g���ēǂݍ��ݏ������s�����ǂ�����ݒ肷��B<p>
     * �f�t�H���g�́Afalse�B<br>
     *
     * @param isLoad �ǂݍ��ݏ������s���ꍇ�Atrue
     */
    public void setLoadOnStart(boolean isLoad);
    
    /**
     * �T�[�r�X�̊J�n���ɁA{@link ContextStore}�T�[�r�X���g���ēǂݍ��ݏ������s�����ǂ����𔻒肷��B<p>
     *
     * @return true�̏ꍇ�A�ǂݍ��ݏ������s��
     */
    public boolean isLoadOnStart();
    
    /**
     * �T�[�r�X�̊J�n���ɁA{@link ContextStore}�T�[�r�X���g���ăL�[�̓ǂݍ��ݏ������s�����ǂ�����ݒ肷��B<p>
     * �f�t�H���g�́Afalse�B<br>
     *
     * @param isLoad �ǂݍ��ݏ������s���ꍇ�Atrue
     */
    public void setLoadKeyOnStart(boolean isLoad);
    
    /**
     * �T�[�r�X�̊J�n���ɁA{@link ContextStore}�T�[�r�X���g���ăL�[�̓ǂݍ��ݏ������s�����ǂ����𔻒肷��B<p>
     *
     * @return true�̏ꍇ�A�ǂݍ��ݏ������s��
     */
    public boolean isLoadKeyOnStart();
    
    /**
     * �R���e�L�X�g�̕ۑ��̑O�ɃX�g�A���N���A���邩�ǂ�����ݒ肷��B<p>
     * �f�t�H���g�́Atrue�ŃN���A����B<br>
     *
     * @param isClear �N���A����ꍇ�Atrue
     */
    public void setClearBeforeSave(boolean isClear);
    
    /**
     * �R���e�L�X�g�̕ۑ��̑O�ɃX�g�A���N���A���邩�ǂ����𔻒肷��B<p>
     *
     * @return true�̏ꍇ�A�N���A����
     */
    public boolean isClearBeforeSave();
    
    /**
     * ��m�[�h�𕪎U������悤�ɂ��邩�ǂ�����ݒ肷��B<p>
     * �f�t�H���g�́Afalse�ŁA���U�����Ȃ��B<br>
     * ���U�����Ȃ��ꍇ�́A�N���X�^�̎Q�������Ɉˑ����āA�Q����������̃m�[�h�ŁA�f�[�^�m�[�h�ƂȂ��Ă���m�[�h����m�[�h�ɂȂ�B<br>
     * ���U������ꍇ�́A�N���X�^�̎Q�������Ɉˑ����āA�N���X�^�����o�̑����J�n�_���m�[�h�ԍ��ł��炵�āA�Q����������̃m�[�h�ŁA�f�[�^�m�[�h�ƂȂ��Ă���m�[�h����m�[�h�ɂȂ�B��m�[�h�̕��U�̋ϓ�����ۏ؂�����̂ł͂Ȃ��B<br>
     *
     * @param isDistributed ���U������ꍇ�Atrue
     */
    public void setMainDistributed(boolean isDistributed);
    
    /**
     * ��m�[�h�𕪎U������悤�ɂ��邩�ǂ����𔻒肷��B<p>
     *
     * @return true�̏ꍇ�́A���U������
     */
    public boolean isMainDistributed();
    
    /**
     * {@link SharedContextUpdateListener}�T�[�r�X�̃T�[�r�X���z���ݒ肷��B<p>
     * 
     * @param names SharedContextUpdateListener�T�[�r�X�̃T�[�r�X���z��
     */
    public void setSharedContextUpdateListenerServiceNames(ServiceName[] names);
    
    /**
     * {@link SharedContextUpdateListener}�T�[�r�X�̃T�[�r�X���z����擾����B<p>
     * 
     * @return SharedContextUpdateListener�T�[�r�X�̃T�[�r�X���z��
     */
    public ServiceName[] getSharedContextUpdateListenerServiceNames();
    
    /**
     * �C���f�b�N�X��ݒ肷��B<p>
     * �R���e�L�X�g�l�I�u�W�F�N�g�̃v���p�e�B���L�[�Ƃ��ăC���f�b�N�X�𒣂�A�R���e�L�X�g�l�ɑ΂��Ă̌������s����悤�ɂ���B<br>
     * �����Őݒ肵���C���f�b�N�X���g���āA{@link SharedContext#createView()}�Ō������\�ɂȂ�B<br>
     *
     * @param name �C���f�b�N�X��
     * @param keyProps �C���f�b�N�X�̃L�[�Ƃ���R���e�L�X�g�l�I�u�W�F�N�g�̃v���p�e�B���z��
     * @see SharedContext#createView()
     */
    public void setIndex(String name, String[] keyProps);
    
    /**
     * �w�肵���C���f�b�N�X���폜����B<p>
     *
     * @param name �C���f�b�N�X��
     */
    public void removeIndex(String name);
    
    /**
     * �w�肵���C���f�b�N�X�𒣂�Ȃ����B<p>
     *
     * @param name �C���f�b�N�X��
     */
    public void analyzeIndex(String name);
    
    /**
     * �R���e�L�X�g���U�̍Ĕz�u���s���B<p>
     * ��m�[�h�̏ꍇ�́A�S�Ẵm�[�h�ɍĔz�u���߂��o���B��m�[�h�łȂ��ꍇ�́A��m�[�h�ɍĔz�u�𑣂��B<br>
     *
     * @exception SharedContextSendException ���U�T�[�o�ւ̃��b�Z�[�W���M�Ɏ��s�����ꍇ
     * @exception SharedContextTimeoutException ���U�T�[�o����̉����҂��Ń^�C���A�E�g�����ꍇ
     */
    public void rehash() throws SharedContextSendException, SharedContextTimeoutException;
    
    /**
     * �R���e�L�X�g���U�̍Ĕz�u���s���B<p>
     * ��m�[�h�̏ꍇ�́A�S�Ẵm�[�h�ɍĔz�u���߂��o���B��m�[�h�łȂ��ꍇ�́A��m�[�h�ɍĔz�u�𑣂��B<br>
     *
     * @param timeout �^�C���A�E�g
     * @exception SharedContextSendException ���U�T�[�o�ւ̃��b�Z�[�W���M�Ɏ��s�����ꍇ
     * @exception SharedContextTimeoutException ���U�T�[�o����̉����҂��Ń^�C���A�E�g�����ꍇ
     */
    public void rehash(long timeout) throws SharedContextSendException, SharedContextTimeoutException;
    
    /**
     * �R���e�L�X�g�������s���B<p>
     * �N���C�A���g���[�h�̏ꍇ�́A���[�J���̃R���e�L�X�g���N���A����B�܂��A�T�[�o���[�h�Ŏ�m�[�h�̏ꍇ�́A�S�Ẵm�[�h�ɓ������߂��o���B��m�[�h�łȂ��ꍇ�́A��m�[�h�ɓ����𑣂��B<br>
     *
     * @exception SharedContextSendException ���U�T�[�o�ւ̃��b�Z�[�W���M�Ɏ��s�����ꍇ
     * @exception SharedContextTimeoutException ���U�T�[�o����̉����҂��Ń^�C���A�E�g�����ꍇ
     */
    public void synchronize() throws SharedContextSendException, SharedContextTimeoutException;
    
    /**
     * �R���e�L�X�g�������s���B<p>
     * �N���C�A���g���[�h�̏ꍇ�́A���[�J���̃R���e�L�X�g���N���A����B�܂��A�T�[�o���[�h�Ŏ�m�[�h�̏ꍇ�́A�S�Ẵm�[�h�ɓ������߂��o���B<br>
     *
     * @param timeout �^�C���A�E�g
     * @exception SharedContextSendException ���U�T�[�o�ւ̃��b�Z�[�W���M�Ɏ��s�����ꍇ
     * @exception SharedContextTimeoutException ���U�T�[�o����̉����҂��Ń^�C���A�E�g�����ꍇ
     */
    public void synchronize(long timeout) throws SharedContextSendException, SharedContextTimeoutException;
    
    /**
     * �w�肳�ꂽ�L�[�̃��b�N���J������B<p>
     *
     * @param key �L�[
     * @return ���b�N�J���ł����ꍇ�́Atrue
     * @exception SharedContextSendException ���U�T�[�o�ւ̃��b�Z�[�W���M�Ɏ��s�����ꍇ
     */
    public boolean unlock(Object key) throws SharedContextSendException;
    
    /**
     * �w�肳�ꂽ�L�[�̃��b�N���J������B<p>
     *
     * @param key �L�[
     * @param force �����t���O
     * @return ���b�N�J���ł����ꍇ�́Atrue
     * @exception SharedContextSendException ���U�T�[�o�ւ̃��b�Z�[�W���M�Ɏ��s�����ꍇ
     */
    public boolean unlock(Object key, boolean force) throws SharedContextSendException;
    
    /**
     * �L�[�̏W�����擾����B<p>
     *
     * @return �L�[�̏W��
     * @exception SharedContextSendException ���U�T�[�o�ւ̃��b�Z�[�W���M�Ɏ��s�����ꍇ
     * @exception SharedContextTimeoutException ���U�T�[�o����̉����҂��Ń^�C���A�E�g�����ꍇ
     */
    public Set keySet() throws SharedContextSendException, SharedContextTimeoutException;
    
    /**
     * �o�^����Ă���L�[�̌������擾����B<p>
     *
     * @return �L�[�̌���
     * @exception SharedContextSendException ���U�T�[�o�ւ̃��b�Z�[�W���M�Ɏ��s�����ꍇ
     * @exception SharedContextTimeoutException ���U�T�[�o����̉����҂��Ń^�C���A�E�g�����ꍇ
     */
    public int size() throws SharedContextSendException, SharedContextTimeoutException;
    
    /**
     * ���[�J���ɓo�^����Ă���L�[�̌������擾����B<p>
     *
     * @return �L�[�̌���
     */
    public int sizeLocal();
    
    /**
     * �S�č폜����B<p>
     *
     * @exception SharedContextSendException ���U�T�[�o�ւ̃��b�Z�[�W���M�Ɏ��s�����ꍇ
     * @exception SharedContextTimeoutException ���U�T�[�o����̉����҂��Ń^�C���A�E�g�����ꍇ
     */
    public void clear() throws SharedContextSendException, SharedContextTimeoutException;
    
    /**
     * �S�č폜����B<p>
     *
     * @param timeout �^�C���A�E�g[ms]
     * @exception SharedContextSendException ���U�T�[�o�ւ̃��b�Z�[�W���M�Ɏ��s�����ꍇ
     * @exception SharedContextTimeoutException ���U�T�[�o����̉����҂��Ń^�C���A�E�g�����ꍇ
     */
    public void clear(long timeout) throws SharedContextSendException, SharedContextTimeoutException;
    
    /**
     * �񓯊��őS�č폜����B<p>
     *
     * @exception SharedContextSendException ���U�T�[�o�ւ̃��b�Z�[�W���M�Ɏ��s�����ꍇ
     */
    public void clearAsynch() throws SharedContextSendException;
    
    /**
     * �w�肳�ꂽ�L�[���ǂ̃f�[�^�m�[�h�Ɋi�[����邩�̃C���f�b�N�X���擾����B<p>
     *
     * @param key �L�[
     * @return �f�[�^�m�[�h�̃C���f�b�N�X
     */
    public int getDataNodeIndex(Object key);
    
    /**
     * �w�肳�ꂽ�C���f�b�N�X�̃f�[�^�m�[�h�ɓo�^����Ă���L�[�̐����擾����B<p>
     *
     * @param nodeIndex �f�[�^�m�[�h�̃C���f�b�N�X
     * @return �L�[�̐�
     * @exception SharedContextSendException ���U�T�[�o�ւ̃��b�Z�[�W���M�Ɏ��s�����ꍇ
     * @exception SharedContextTimeoutException ���U�T�[�o����̉����҂��Ń^�C���A�E�g�����ꍇ
     */
    public int size(int nodeIndex) throws SharedContextSendException, SharedContextTimeoutException;
    
    /**
     * �w�肳�ꂽ�C���f�b�N�X�̃f�[�^�m�[�h�̃L�[�̏W�����擾����B<p>
     *
     * @param nodeIndex �f�[�^�m�[�h�̃C���f�b�N�X
     * @return �L�[�̏W��
     * @exception SharedContextSendException ���U�T�[�o�ւ̃��b�Z�[�W���M�Ɏ��s�����ꍇ
     * @exception SharedContextTimeoutException ���U�T�[�o����̉����҂��Ń^�C���A�E�g�����ꍇ
     */
    public Set keySet(int nodeIndex) throws SharedContextSendException, SharedContextTimeoutException;
    
    /**
     * �w�肳�ꂽ�C���f�b�N�X�̃f�[�^�m�[�h���N���C�A���g���[�h���ǂ����𔻒肷��B<p>
     *
     * @param nodeIndex �f�[�^�m�[�h�̃C���f�b�N�X
     * @return true�̏ꍇ�A�N���C�A���g���[�h
     */
    public boolean isClient(int nodeIndex);
    
    /**
     * �w�肳�ꂽ�C���f�b�N�X�̃f�[�^�m�[�h����m�[�h���ǂ����𔻒肷��B<p>
     *
     * @param nodeIndex �f�[�^�m�[�h�̃C���f�b�N�X
     * @return true�̏ꍇ�A��m�[�h
     */
    public boolean isMain(int nodeIndex);
    
    /**
     * �w�肳�ꂽ�L�[����m�[�h�Ƃ��ĕێ����邩�ǂ����𔻒肷��B<p>
     *
     * @param key �L�[
     * @return true�̏ꍇ�A��m�[�h�Ƃ��ĕێ�����
     */
    public boolean isMain(Object key);
    
    /**
     * �f�[�^�m�[�h�̐����擾����B<p>
     *
     * @return �f�[�^�m�[�h�̐�
     */
    public int getNodeCount();
    
    /**
     * ��m�[�h�ƂȂ��Ă���f�[�^�m�[�h�̐����擾����B<p>
     *
     * @return ��m�[�h�ƂȂ��Ă���f�[�^�m�[�h�̐�
     */
    public int getMainNodeCount();
    
    /**
     * ���U�T�[�o�̃f�[�^�m�[�h�̃N���C�A���g/�T�[�o���[�h�̏�Ԃ�\������B<p>
     *
     * @return ���U�T�[�o�̃f�[�^�m�[�h�̃N���C�A���g/�T�[�o���[�h�̏�Ԃ�\��������
     * @exception SharedContextSendException ���U�T�[�o�ւ̃��b�Z�[�W���M�Ɏ��s�����ꍇ
     * @exception SharedContextTimeoutException ���U�T�[�o����̉����҂��Ń^�C���A�E�g�����ꍇ
     */
    public String displayDistributeInfo() throws SharedContextSendException, SharedContextTimeoutException;
    
    /**
     * �R���e�L�X�g�ǂݍ��݂��s���B<p>
     * ��m�[�h�łȂ��ꍇ�A��m�[�h�ɓǂݍ��݂��˗�����B<br>
     *
     * @exception Exception �R���e�L�X�g�ǂݍ��݂Ɏ��s�����ꍇ
     */
    public void load() throws Exception;
    
    /**
     * �R���e�L�X�g�ǂݍ��݂��s���B<p>
     * ��m�[�h�łȂ��ꍇ�A��m�[�h�ɓǂݍ��݂��˗�����B<br>
     *
     * @param timeout ���U�T�[�o�ւ̒ʐM�^�C���A�E�g[ms]
     * @exception Exception �R���e�L�X�g�ǂݍ��݂Ɏ��s�����ꍇ
     */
    public void load(long timeout) throws Exception;
    
    /**
     * �R���e�L�X�g�̃L�[�̓ǂݍ��݂��s���B<p>
     * ��m�[�h�łȂ��ꍇ�A��m�[�h�ɓǂݍ��݂��˗�����B<br>
     *
     * @exception Exception �R���e�L�X�g�ǂݍ��݂Ɏ��s�����ꍇ
     */
    public void loadKey() throws Exception;
    
    /**
     * �R���e�L�X�g�̃L�[�̓ǂݍ��݂��s���B<p>
     * ��m�[�h�łȂ��ꍇ�A��m�[�h�ɓǂݍ��݂��˗�����B<br>
     *
     * @param timeout ���U�T�[�o�ւ̒ʐM�^�C���A�E�g[ms]
     * @exception Exception �R���e�L�X�g�ǂݍ��݂Ɏ��s�����ꍇ
     */
    public void loadKey(long timeout) throws Exception;
    
    /**
     * �w�肵���L�[�ɊY������l�̃R���e�L�X�g�ǂݍ��݂��s���B<p>
     * ��m�[�h�łȂ��ꍇ�A��m�[�h�ɓǂݍ��݂��˗�����B<br>
     *
     * @param key �L�[
     * @exception Exception �R���e�L�X�g�ǂݍ��݂Ɏ��s�����ꍇ
     */
    public void load(Object key) throws Exception;
    
    /**
     * �w�肵���L�[�ɊY������l�̃R���e�L�X�g�ǂݍ��݂��s���B<p>
     * ��m�[�h�łȂ��ꍇ�A��m�[�h�ɓǂݍ��݂��˗�����B<br>
     *
     * @param key �L�[
     * @param timeout ���U�T�[�o�ւ̒ʐM�^�C���A�E�g[ms]
     * @exception Exception �R���e�L�X�g�ǂݍ��݂Ɏ��s�����ꍇ
     */
    public void load(Object key, long timeout) throws Exception;
    
    /**
     * �R���e�L�X�g�ۑ����s���B<p>
     * ��m�[�h�łȂ��ꍇ�A��m�[�h�ɕۑ����˗�����B<br>
     *
     * @param timeout ���U�T�[�o�ւ̒ʐM�^�C���A�E�g[ms]
     * @exception Exception �R���e�L�X�g�ۑ��Ɏ��s�����ꍇ
     */
    public void save(long timeout) throws Exception;
    
    /**
     * �w�肳�ꂽ�L�[�ɊY������l��{@link ContextStore}�T�[�r�X���g���ď����ݏ������s���B<p>
     *
     * @param key �L�[
     * @param timeout ���U�T�[�o�ւ̒ʐM�^�C���A�E�g[ms]
     * @exception Exception �ǂݍ��ݏ����Ɏ��s�����ꍇ
     */
    public void save(Object key, long timeout) throws Exception;
    
    /**
     * ��m�[�h���ǂ����𔻒肷��B<p>
     *
     * @return ��m�[�h�̏ꍇtrue
     */
    public boolean isMain();
    
    /**
     * �m�[�hID���擾����B<p>
     *
     * @return �m�[�hID
     */
    public Object getId();
    
    /**
     * ��m�[�h�̃m�[�hID���擾����B<p>
     *
     * @return �m�[�hID
     */
    public Object getMainId();
    
    /**
     * �S�m�[�h�̃m�[�hID�̃��X�g���擾����B<p>
     *
     * @return �m�[�hID�̃��X�g
     */
    public List getMemberIdList();
    
    /**
     * �N���C�A���g�m�[�h�̃m�[�hID�̏W�����擾����B<p>
     *
     * @return �m�[�hID�̏W��
     */
    public Set getClientMemberIdSet();
    
    /**
     * �T�[�o�m�[�h�̃m�[�hID�̏W�����擾����B<p>
     *
     * @return �m�[�hID�̏W��
     */
    public Set getServerMemberIdSet();
    
    /**
     * �L���b�V���̃q�b�g�����擾����B<p>
     *
     * @return �q�b�g��
     */
    public float getCacheHitRatio();
    
    /**
     * �L���b�V���̃q�b�g�������Z�b�g����B<p>
     */
    public void resetCacheHitRatio();
}