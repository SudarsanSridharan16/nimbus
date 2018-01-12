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
package jp.ossc.nimbus.service.beancontrol;

import java.rmi.*;
import java.util.Set;
import java.util.Map;

import jp.ossc.nimbus.service.beancontrol.interfaces.InvalidConfigurationException;

/**
 * �Ɩ��t���[���s�T�[�o�B<p>
 *
 * @author M.Takata
 */
public interface BeanFlowInvokerServer extends Remote{
    
    /**
     * ���̃T�[�o�����N�G�X�g��t�\���𔻒肷��B<p>
     *
     * @return ���N�G�X�g��t�\�ȏꍇtrue
     * @exception RemoteException �����[�g�Ăяo���Ɏ��s�����ꍇ
     */
    public boolean isAcceptable() throws RemoteException;
    
    /**
     * ���̃T�[�o�ɐ�������Ă���Bean�t���[�̐����擾����B<p>
     *
     * @return ��������Ă���Bean�t���[�̐�
     * @exception RemoteException �����[�g�Ăяo���Ɏ��s�����ꍇ
     */
    public int getCurrentFlowCount() throws RemoteException;
    
    /**
     * ���̃T�[�o�̃��\�[�X���p�ʂ��擾����B<p>
     *
     * @return ���\�[�X���p��
     * @exception RemoteException �����[�g�Ăяo���Ɏ��s�����ꍇ
     */
    public Comparable getResourceUsage() throws RemoteException;
    
    /**
     * ���̋Ɩ��t���[���s�T�[�o���ێ����Ă���Bean�t���[���̏W�����擾����B<p>
     *
     * @return Bean�t���[���̏W��
     * @exception RemoteException �����[�g�Ăяo���Ɏ��s�����ꍇ
     */
    public Set getBeanFlowNameSet() throws RemoteException;
    
    /**
     * �w�肳�ꂽBean�t���[�����̋Ɩ��t���[���s�T�[�o���ێ����Ă��邩�ǂ����𔻒肷��B<p>
     *
     * @param name Bean�t���[��
     * @return ���̋Ɩ��t���[���s�T�[�o���ێ����Ă���ꍇtrue
     * @exception RemoteException �����[�g�Ăяo���Ɏ��s�����ꍇ
     */
    public boolean containsFlow(String name) throws RemoteException;
    
    /**
     * �T�[�o���Bean�t���[�𐶐�����B<p>
     *
     * @param flowName Bean�t���[��
     * @param caller �Ăяo������Bean�t���[��
     * @param isOverwride �I�[�o�[���C�h����Ă��邩�ǂ���
     * @return Bean�t���[�����s����ۂ̎��sID
     * @exception RemoteException �����[�g�Ăяo���Ɏ��s�����ꍇ
     * @exception InvalidConfigurationException �w�肳�ꂽBean�t���[�����݂��Ȃ��ꍇ
     */
    public Object createFlow(String flowName, String caller, boolean isOverwride) throws RemoteException, InvalidConfigurationException;
    
    /**
     * �w�肳�ꂽ���sID��Bean�t���[���T�[�o��ɐ�������Ă��邩�𔻒肷��B<p>
     *
     * @param id ���sID
     * @return Bean�t���[����������Ă���ꍇtrue
     * @exception RemoteException �����[�g�Ăяo���Ɏ��s�����ꍇ
     */
    public boolean isExistsFlow(Object id) throws RemoteException;
    
    /**
     * �w�肳�ꂽ���sID��Bean�t���[�̏㏑���t���[�����擾����B<p>
     *
     * @param id ���sID
     * @return �㏑���t���[���̔z��
     * @exception RemoteException �����[�g�Ăяo���Ɏ��s�����ꍇ
     * @exception NoSuchBeanFlowIdException �w�肳�ꂽ���sID��Bean�t���[�����݂��Ȃ��ꍇ
     */
    public String[] getOverwrideFlowNames(Object id) throws RemoteException, NoSuchBeanFlowIdException;
    
    /**
     * �J�o���b�W���擾����B<p>
     *
     * @param id ���sID
     * @return �J�o���b�W
     * @exception RemoteException �����[�g�Ăяo���Ɏ��s�����ꍇ
     * @exception NoSuchBeanFlowIdException �w�肳�ꂽ���sID��Bean�t���[�����݂��Ȃ��ꍇ
     */
    public BeanFlowCoverage getBeanFlowCoverage(Object id) throws RemoteException, NoSuchBeanFlowIdException;
    
    /**
     * Bean�t���[����`����Ă��郊�\�[�X�p�X���擾����B<p>
     *
     * @param id ���sID
     * @return ���\�[�X�p�X
     * @exception RemoteException �����[�g�Ăяo���Ɏ��s�����ꍇ
     * @exception NoSuchBeanFlowIdException �w�肳�ꂽ���sID��Bean�t���[�����݂��Ȃ��ꍇ
     */
    public String getResourcePath(Object id) throws RemoteException, NoSuchBeanFlowIdException;
    
    /**
     * Bean�t���[�����s����B<p>
     * 
     * @param id ���sID
     * @param input Bean�t���[�ւ̈���
     * @param context �R���e�L�X�g���
     * @return Bean�t���[�̎��s����
     * @exception RemoteException �����[�g�Ăяo���Ɏ��s�����ꍇ
     * @exception NoSuchBeanFlowIdException �w�肳�ꂽ���sID��Bean�t���[�����݂��Ȃ��ꍇ
     * @exception Exception Bean�t���[�̎��s���ɗ�O�����������ꍇ
     */
    public Object invokeFlow(Object id, Object input, Map context) throws NoSuchBeanFlowIdException, RemoteException, Exception;
    
    /**
     * Bean�t���[��񓯊����s����B<p>
     * 
     * @param id ���sID
     * @param input Bean�t���[�ւ̈���
     * @param context �R���e�L�X�g���
     * @param callback �R�[���o�b�N
     * @param maxAsynchWait �ő�񓯊����s�ҋ@��
     * @exception RemoteException �����[�g�Ăяo���Ɏ��s�����ꍇ
     * @exception NoSuchBeanFlowIdException �w�肳�ꂽ���sID��Bean�t���[�����݂��Ȃ��ꍇ
     * @exception Exception Bean�t���[�̎��s���ɗ�O�����������ꍇ
     */
    public void invokeAsynchFlow(Object id, Object input, Map context, BeanFlowAsynchInvokeCallback callback, int maxAsynchWait) throws NoSuchBeanFlowIdException, RemoteException, Exception;
    
    /**
     * �w�肳�ꂽ���sID��Bean�t���[���ꎞ��~������B<p>
     *
     * @param id ���sID
     * @exception RemoteException �����[�g�Ăяo���Ɏ��s�����ꍇ
     * @exception NoSuchBeanFlowIdException �w�肳�ꂽ���sID��Bean�t���[�����݂��Ȃ��ꍇ
     */
    public void suspendFlow(Object id) throws RemoteException, NoSuchBeanFlowIdException;
    
    /**
     * �w�肳�ꂽ���sID��Bean�t���[���ꎞ��~���߂��󂯂Ă��邩�𔻒肷��B<p>
     *
     * @param id ���sID
     * @return �ꎞ��~���߂��󂯂Ă���ꍇ�Atrue
     * @exception RemoteException �����[�g�Ăяo���Ɏ��s�����ꍇ
     * @exception NoSuchBeanFlowIdException �w�肳�ꂽ���sID��Bean�t���[�����݂��Ȃ��ꍇ
     */
    public boolean isSuspendFlow(Object id) throws RemoteException, NoSuchBeanFlowIdException;
    
    /**
     * �w�肳�ꂽ���sID��Bean�t���[���ꎞ��~���Ă��邩�𔻒肷��B<p>
     *
     * @param id ���sID
     * @return �ꎞ��~���Ă���ꍇ�Atrue
     * @exception RemoteException �����[�g�Ăяo���Ɏ��s�����ꍇ
     * @exception NoSuchBeanFlowIdException �w�肳�ꂽ���sID��Bean�t���[�����݂��Ȃ��ꍇ
     */
    public boolean isSuspendedFlow(Object id) throws RemoteException, NoSuchBeanFlowIdException;
    
    /**
     * �w�肳�ꂽ���sID��Bean�t���[���ĊJ������B<p>
     *
     * @param id ���sID
     * @exception RemoteException �����[�g�Ăяo���Ɏ��s�����ꍇ
     * @exception NoSuchBeanFlowIdException �w�肳�ꂽ���sID��Bean�t���[�����݂��Ȃ��ꍇ
     */
    public void resumeFlow(Object id) throws RemoteException, NoSuchBeanFlowIdException;
    
    /**
     * �w�肳�ꂽ���sID��Bean�t���[���~������B<p>
     *
     * @param id ���sID
     * @exception RemoteException �����[�g�Ăяo���Ɏ��s�����ꍇ
     */
    public void stopFlow(Object id) throws RemoteException;
    
    /**
     * �w�肳�ꂽ���sID��Bean�t���[����~���߂��󂯂Ă��邩�𔻒肷��B<p>
     *
     * @param id ���sID
     * @return ��~���߂��󂯂Ă���ꍇ�Atrue
     * @exception RemoteException �����[�g�Ăяo���Ɏ��s�����ꍇ
     */
    public boolean isStopFlow(Object id) throws RemoteException;
    
    /**
     * �w�肳�ꂽ���sID��Bean�t���[����~���Ă��邩�𔻒肷��B<p>
     *
     * @param id ���sID
     * @return ��~���Ă���ꍇ�Atrue
     * @exception RemoteException �����[�g�Ăяo���Ɏ��s�����ꍇ
     */
    public boolean isStoppedFlow(Object id) throws RemoteException;
    
    /**
     * �w�肳�ꂽ���sID��Bean�t���[�̃t���[�����擾����B<p>
     *
     * @param id ���sID
     * @return �t���[��
     * @exception RemoteException �����[�g�Ăяo���Ɏ��s�����ꍇ
     * @exception NoSuchBeanFlowIdException �w�肳�ꂽ���sID��Bean�t���[�����݂��Ȃ��ꍇ
     */
    public String getFlowName(Object id) throws RemoteException, NoSuchBeanFlowIdException;
    
    /**
     * �w�肳�ꂽ���sID��Bean�t���[�����ݎ��s���Ă���t���[�����擾����B<p>
     *
     * @param id ���sID
     * @return ���ݎ��s���Ă���t���[��
     * @exception RemoteException �����[�g�Ăяo���Ɏ��s�����ꍇ
     * @exception NoSuchBeanFlowIdException �w�肳�ꂽ���sID��Bean�t���[�����݂��Ȃ��ꍇ
     */
    public String getCurrentFlowName(Object id) throws RemoteException, NoSuchBeanFlowIdException;
    
    /**
     * �w�肳�ꂽ���sID��Bean�t���[�����ݎ��s���Ă���X�e�b�v�����擾����B<p>
     *
     * @param id ���sID
     * @return ���ݎ��s���Ă���X�e�b�v��
     * @exception RemoteException �����[�g�Ăяo���Ɏ��s�����ꍇ
     * @exception NoSuchBeanFlowIdException �w�肳�ꂽ���sID��Bean�t���[�����݂��Ȃ��ꍇ
     */
    public String getCurrentStepName(Object id) throws RemoteException, NoSuchBeanFlowIdException;
    
    /**
     * �w�肳�ꂽ���sID��Bean�t���[�̃��j�^������������B<p>
     *
     * @param id ���sID
     * @exception RemoteException �����[�g�Ăяo���Ɏ��s�����ꍇ
     * @exception NoSuchBeanFlowIdException �w�肳�ꂽ���sID��Bean�t���[�����݂��Ȃ��ꍇ
     */
    public void clearMonitor(Object id) throws RemoteException, NoSuchBeanFlowIdException;
    
    /**
     * �w�肳�ꂽ���sID��Bean�t���[�̎��s���������B<p>
     *
     * @param id ���sID
     * @exception RemoteException �����[�g�Ăяo���Ɏ��s�����ꍇ
     */
    public void cancel(Object id) throws RemoteException;
    
    /**
     * �w�肳�ꂽ���sID��Bean�t���[���I������B<p>
     *
     * @param id ���sID
     * @exception RemoteException �����[�g�Ăяo���Ɏ��s�����ꍇ
     */
    public void end(Object id) throws RemoteException;
}
