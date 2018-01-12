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

import jp.ossc.nimbus.core.ServiceName;

/**
 * {@link SharedContextService}��MBean�C���^�t�F�[�X�B<p>
 * 
 * @author M.Takata
 * @see SharedContextService
 */
public interface SharedContextServiceMBean extends DefaultContextServiceMBean{
    
    /**
     * �f�t�H���g�̃T�u�W�F�N�g�B<p>
     */
    public static final String DEFAULT_SUBJECT = "SharedContext";
    
    /**
     * �N���C�A���g���[�h�̃T�u�W�F�N�g��u���B<p>
     */
    public static final String CLIENT_SUBJECT_SUFFIX = ".Client";
    
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
     * �C���^�[�v���^���s�񓯊������p��{@link jp.ossc.nimbus.service.queue.Queue Queue}�T�[�r�X�̃T�[�r�X����ݒ肷��B<p>
     * �C���^�[�v���^���s���T�|�[�g����ꍇ�ɁA�C���^�[�v���^���s��񓯊��ɂ����ꍇ�̗v���L���[��ݒ肷��B�w�肵�Ȃ��ꍇ�́A�����L���[���g�p�����B<br>
     * 
     * @param name Queue�T�[�r�X�̃T�[�r�X��
     */
    public void setExecuteQueueServiceName(ServiceName name);
    
    /**
     * �C���^�[�v���^���s�񓯊������p��{@link jp.ossc.nimbus.service.queue.Queue Queue}�T�[�r�X�̃T�[�r�X�����擾����B<p>
     * 
     * @return Queue�T�[�r�X�̃T�[�r�X��
     */
    public ServiceName getExecuteQueueServiceName();
    
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
     * �T�[�r�X�̊J�n���ɑ��̃R���e�L�X�g�Ƃ̃f�[�^�������s�����ǂ�����ݒ肷��B<p>
     * �f�t�H���g�́Atrue�Ńf�[�^��������B<br>
     *
     * @param isSynch �f�[�^��������ꍇ�Atrue
     */
    public void setSynchronizeOnStart(boolean isSynch);
    
    /**
     * �T�[�r�X�̊J�n���ɑ��̃R���e�L�X�g�Ƃ̃f�[�^�������s�����ǂ����𔻒肷��B<p>
     *
     * @return true�ꍇ�A�f�[�^��������
     */
    public boolean isSynchronizeOnStart();
    
    /**
     * ��ƂȂ�m�[�h�݂̂��R���e�L�X�g�̕ۑ����s�����ǂ�����ݒ肷��B<p>
     * �f�t�H���g�́Afalse�ŁA�N���C�A���g���[�h�ȊO�̑S�Ẵm�[�h���ۑ�����B<br>
     *
     * @param isSave ��ƂȂ�m�[�h�݂̂��R���e�L�X�g�̕ۑ����s���ꍇ�Atrue
     */
    public void setSaveOnlyMain(boolean isSave);
    
    /**
     * ��ƂȂ�m�[�h�݂̂��R���e�L�X�g�̕ۑ����s�����ǂ����𔻒肷��B<p>
     *
     * @return true�̏ꍇ�A��ƂȂ�m�[�h�݂̂��R���e�L�X�g�̕ۑ����s��
     */
    public boolean isSaveOnlyMain();
    
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
     * �R���e�L�X�g�������s���B<p>
     * �N���C�A���g���[�h�̏ꍇ�́A���[�J���̃R���e�L�X�g���N���A����B�܂��A�T�[�o���[�h�Ŏ�m�[�h�̏ꍇ�́A�S�Ẵm�[�h�ɓ������߂��o���B<br>
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
     * �S�Ĕ񓯊��ō폜����B<p>
     *
     * @exception SharedContextSendException ���U�T�[�o�ւ̃��b�Z�[�W���M�Ɏ��s�����ꍇ
     */
    public void clearAsynch() throws SharedContextSendException;
    
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
     * @param timeout ���U�T�[�o�ւ̒ʐM�^�C���A�E�g[ms]
     * @exception Exception �R���e�L�X�g�ǂݍ��݂Ɏ��s�����ꍇ
     */
    public void loadKey(long timeout) throws Exception;
    
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
     * ��m�[�h�łȂ��ꍇ�A��m�[�h�ɕۑ����˗�����B�A���A{@link #setSaveOnlyMain(boolean) setSaveOnlyMain(false)}�̏ꍇ�́A�N���C�A���g�ȊO�͑S�ĕۑ�����B<br>
     *
     * @param timeout ���U�T�[�o�ւ̒ʐM�^�C���A�E�g[ms]
     * @exception Exception �R���e�L�X�g�ۑ��Ɏ��s�����ꍇ
     */
    public void save(long timeout) throws Exception;
    
    /**
     * �w�肵���L�[�ɊY������l�̃R���e�L�X�g�ۑ����s���B<p>
     * ��m�[�h�łȂ��ꍇ�A��m�[�h�ɕۑ����˗�����B�A���A{@link #setSaveOnlyMain(boolean) setSaveOnlyMain(false)}�̏ꍇ�́A�N���C�A���g�ȊO�͑S�ĕۑ�����B<br>
     *
     * @param key �L�[
     * @param timeout ���U�T�[�o�ւ̒ʐM�^�C���A�E�g[ms]
     * @exception Exception �R���e�L�X�g�ۑ��Ɏ��s�����ꍇ
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
