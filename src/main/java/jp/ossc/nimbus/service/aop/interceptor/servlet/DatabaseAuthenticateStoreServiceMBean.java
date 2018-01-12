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
package jp.ossc.nimbus.service.aop.interceptor.servlet;

import jp.ossc.nimbus.core.ServiceBaseMBean;
import jp.ossc.nimbus.core.ServiceName;

/**
 * {@link DatabaseAuthenticateStoreService}��MBean�C���^�t�F�[�X�B
 * <p>
 *
 * @author M.Takata
 * @see DatabaseAuthenticateStoreService
 */
public interface DatabaseAuthenticateStoreServiceMBean extends ServiceBaseMBean {
    
    /**
     * PersistentManager�ւ̓��̓I�u�W�F�N�g�ł���}�b�v�Ɋi�[�����F�؏��܂��͔F�؃L�[�̃L�[���B
     * <p>
     */
    public static final String INPUT_KEY_AUTH = "Auth";
    
    /**
     * PersistentManager�ւ̓��̓I�u�W�F�N�g�ł���}�b�v�Ɋi�[�����HTTP�Z�b�V����ID�̃L�[���B
     * <p>
     */
    public static final String INPUT_KEY_HTTP_SESSION_ID = "SessionId";
    
    /**
     * PersistentManager�ւ̓��̓I�u�W�F�N�g�ł���}�b�v�Ɋi�[�����^�C���X�^���v�̃L�[���B
     * <p>
     */
    public static final String INPUT_KEY_TIMESTAMP = "Timestamp";
    
    /**
     * PersistentManager�ւ̓��̓I�u�W�F�N�g�ł���}�b�v�Ɋi�[�����z�X�g���̃L�[���B
     * <p>
     */
    public static final String INPUT_KEY_HOST = "Host";
    
    /**
     * {@link jp.ossc.nimbus.service.connection.ConnectionFactory
     * ConnectionFactory}�T�[�r�X�̃T�[�r�X����ݒ肷��B
     * <p>
     *
     * @param name ConnectionFactory�T�[�r�X�̃T�[�r�X��
     */
    public void setConnectionFactoryServiceName(ServiceName name);
    
    /**
     * {@link jp.ossc.nimbus.service.connection.ConnectionFactory
     * ConnectionFactory}�T�[�r�X�̃T�[�r�X�����擾����B
     * <p>
     *
     * @return ConnectionFactory�T�[�r�X�̃T�[�r�X��
     */
    public ServiceName getConnectionFactoryServiceName();
    
    /**
     * {@link jp.ossc.nimbus.service.connection.PersistentManager
     * PersistentManager}�T�[�r�X�̃T�[�r�X����ݒ肷��B
     * <p>
     *
     * @param name PersistentManager�T�[�r�X�̃T�[�r�X��
     */
    public void setPersistentManagerServiceName(ServiceName name);
    
    /**
     * {@link jp.ossc.nimbus.service.connection.PersistentManager
     * PersistentManager}�T�[�r�X�̃T�[�r�X�����擾����B
     * <p>
     *
     * @return PersistentManager�T�[�r�X�̃T�[�r�X��
     */
    public ServiceName getPersistentManagerServiceName();
    
    /**
     * �f�[�^�x�[�X���畜������F�؏��̃N���X�I�u�W�F�N�g��ݒ肷��B
     * <p>
     *
     * @param clazz �F�؏��̃N���X�I�u�W�F�N�g
     */
    public void setAuthenticatedInfoClass(Class clazz);
    
    /**
     * �f�[�^�x�[�X���畜������F�؏��̃N���X�I�u�W�F�N�g���擾����B
     * <p>
     *
     * @return �F�؏��̃N���X�I�u�W�F�N�g
     */
    public Class getAuthenticatedInfoClass();
    
    /**
     * �f�[�^�x�[�X���畜������F�؏��̃I�u�W�F�N�g��ݒ肷��B
     * <p>
     * ���̃I�u�W�F�N�g�́ACloneable��public��clone()���\�b�h����������K�v������B<br>
     *
     * @param template �F�؏��̃I�u�W�F�N�g
     */
    public void setAuthenticatedInfoTemplate(Object template);
    
    /**
     * �f�[�^�x�[�X���畜������F�؏��̃I�u�W�F�N�g���擾����B
     * <p>
     *
     * @return �F�؏��̃I�u�W�F�N�g
     */
    public Object getAuthenticatedInfoTemplate();
    
    /**
     * PersistentManager�ւ̓��̓I�u�W�F�N�g�ł���}�b�v�Ɋi�[����z�X�g����ݒ肷��B
     * <p>
     * �ݒ肵�Ȃ��ꍇ�A�����I�Ƀ��[�J���z�X�g�̃z�X�g�����g�p�����B<br>
     *
     * @param name �z�X�g��
     */
    public void setHostName(String name);
    
    /**
     * PersistentManager�ւ̓��̓I�u�W�F�N�g�ł���}�b�v�Ɋi�[����z�X�g�����擾����B
     * <p>
     *
     * @return �z�X�g��
     */
    public String getHostName();
    
    /**
     * �F�؏��𐶐����ėǂ�������������N�G����ݒ肷��B
     * <p>
     *
     * @param query �N�G��
     */
    public void setSelectQueryOnCreateUser(String query);
    
    /**
     * �F�؏��𐶐����ėǂ�������������N�G�����擾����B
     * <p>
     *
     * @return �N�G��
     */
    public String getSelectQueryOnCreateUser();
    
    /**
     * �F�؏�����������N�G����ݒ肷��B
     * <p>
     *
     * @param query �N�G��
     */
    public void setSelectQueryOnFindUser(String query);
    
    /**
     * �F�؏�����������N�G�����擾����B
     * <p>
     *
     * @return �N�G��
     */
    public String getSelectQueryOnFindUser();
    
    /**
     * �F�؏���}������N�G����ݒ肷��B
     * <p>
     *
     * @param query �N�G��
     */
    public void setInsertQuery(String query);
    
    /**
     * �F�؏���}������N�G�����擾����B
     * <p>
     *
     * @return �N�G��
     */
    public String getInsertQuery();
    
    /**
     * �F�؏����X�g�A����ۂɁA���ɊY������F�؏�񂪑��݂���ꍇ�ɁA�F�؏����X�V����N�G����ݒ肷��B
     * <p>
     *
     * @param query �N�G��
     */
    public void setUpdateQueryOnCreate(String query);
    
    /**
     * �F�؏����X�g�A����ۂɁA���ɊY������F�؏�񂪑��݂���ꍇ�ɁA�F�؏����X�V����N�G�����擾����B
     * <p>
     *
     * @return �N�G��
     */
    public String getUpdateQueryOnCreate();
    
    /**
     * �F�؏����X�g�A����ۂɁA���ɊY������F�؏�񂪑��݂���ꍇ�ɁA�F�؏����폜����N�G����ݒ肷��B
     * <p>
     *
     * @param query �N�G��
     */
    public void setDeleteQueryOnCreate(String query);
    
    /**
     * �F�؏����X�g�A����ۂɁA���ɊY������F�؏�񂪑��݂���ꍇ�ɁA�F�؏����폜����N�G�����擾����B
     * <p>
     *
     * @return �N�G��
     */
    public String getDeleteQueryOnCreate();
    
    /**
     * �F�؏��𕜌�����ۂɁA�F�؏����X�V����N�G����ݒ肷��B
     * <p>
     *
     * @param query �N�G��
     */
    public void setUpdateQueryOnActivate(String query);
    
    /**
     * �F�؏��𕜌�����ۂɁA�F�؏����X�V����N�G�����擾����B
     * <p>
     *
     * @return �N�G��
     */
    public String getUpdateQueryOnActivate();
    
    /**
     * �F�؏���񊈐�������ۂɁA�F�؏����X�V����N�G����ݒ肷��B
     * <p>
     *
     * @param query �N�G��
     */
    public void setUpdateQueryOnDeactivate(String query);
    
    /**
     * �F�؏���񊈐�������ۂɁA�F�؏����X�V����N�G�����擾����B
     * <p>
     *
     * @return �N�G��
     */
    public String getUpdateQueryOnDeactivate();
    
    /**
     * �F�؏����폜����N�G����ݒ肷��B
     * <p>
     *
     * @param query �N�G��
     */
    public void setDeleteQuery(String query);
    
    /**
     * �F�؏����폜����N�G�����擾����B
     * <p>
     *
     * @return �N�G��
     */
    public String getDeleteQuery();
    
    /**
     * �F�؏����X�g�A����ۂɁA���ɊY������F�؏�񂪑��݂���ꍇ�ɁA�F�؏����폜���邩��ݒ肷��B
     * <p>
     *
     * @param isDelete �폜�v��
     */
    public void setDeleteFindUser(boolean isDelete);
    
    /**
     * �F�؏����X�g�A����ۂɁA���ɊY������F�؏�񂪑��݂���ꍇ�ɁA�F�؏����폜���邩���擾����B
     * <p>
     *
     * @return �폜�v��
     */
    public boolean isDeleteFindUser();
    
}