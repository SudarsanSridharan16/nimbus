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

import jp.ossc.nimbus.beans.dataset.Record;
import jp.ossc.nimbus.core.ServiceBaseMBean;
import jp.ossc.nimbus.core.ServiceName;

/**
 * {@link DatabaseSharedContextKeyDistributorService}��MBean�C���^�t�F�[�X�B<p>
 * 
 * @author M.Takata
 * @see DatabaseSharedContextKeyDistributorService
 */
public interface DatabaseSharedContextKeyDistributorServiceMBean extends ServiceBaseMBean{
    
    /**
     * {@link jp.ossc.nimbus.service.connection.ConnectionFactory ConnectionFactory}�T�[�r�X�̃T�[�r�X����ݒ肷��B<p>
     * 
     * @param name ConnectionFactory�T�[�r�X�̃T�[�r�X��
     */
    public void setConnectionFactoryServiceName(ServiceName name);
    
    /**
     * {@link jp.ossc.nimbus.service.connection.ConnectionFactory ConnectionFactory}�T�[�r�X�̃T�[�r�X�����擾����B<p>
     * 
     * @return ConnectionFactory�T�[�r�X�̃T�[�r�X��
     */
    public ServiceName getConnectionFactoryServiceName();
    
    /**
     * {@link jp.ossc.nimbus.service.connection.PersistentManager PersistentManager}�T�[�r�X�̃T�[�r�X����ݒ肷��B<p>
     * 
     * @param name PersistentManager�T�[�r�X�̃T�[�r�X��
     */
    public void setPersistentManagerServiceName(ServiceName name);
    
    /**
     * {@link jp.ossc.nimbus.service.connection.PersistentManager PersistentManager}�T�[�r�X�̃T�[�r�X�����擾����B<p>
     * 
     * @return PersistentManager�T�[�r�X�̃T�[�r�X��
     */
    public ServiceName getPersistentManagerServiceName();
    
    /**
     * �L�[���������錟���N�G����ݒ肷��B<p>
     *
     * @param query �N�G��
     */
    public void setKeySelectQuery(String query);
    
    /**
     * �L�[���������錟���N�G�����擾����B<p>
     *
     * @return �N�G��
     */
    public String getKeySelectQuery();
    
    /**
     * �f�[�^�x�[�X���猟�������L�[��ǂݍ��ރ��R�[�h��ݒ肷��B<p>
     * PersistentManager�Ƀ��R�[�h�Ƀ��[�h��������A����̃v���p�e�B����肾���ăL�[�Ƃ���ꍇ�́A{@link #setKeyPropertyName(String)}�Ƒg�ݍ��킹�Đݒ肷��B<br>
     *
     * @param record �ǂݍ��݃��R�[�h
     */
    public void setDatabaseRecord(Record record);
    
    /**
     * �f�[�^�x�[�X���猟�������L�[��ǂݍ��ރ��R�[�h���擾����B<p>
     *
     * @return �ǂݍ��݃��R�[�h
     */
    public Record getDatabaseRecord();
    
    /**
     * �f�[�^�x�[�X���猟�������L�[��ǂݍ���Bean�̃N���X��ݒ肷��B<p>
     * PersistentManager�ɂ���Bean�𒼐ڃ��[�h������ꍇ�́A���̑����̂ݐݒ肷��B<br>
     * PersistentManager�Ƀ��R�[�h�Ƀ��[�h��������A����̃v���p�e�B�����R�[�h����擾���āA����Bean�ɐݒ肵�����ꍇ�́A{@link #setKeyPropertyMapping(String, String)}�Ƒg�ݍ��킹�Đݒ肷��B<br>
     * 
     * @param clazz �ǂݍ���Bean�̃N���X
     */
    public void setKeyClass(Class clazz);
    
    /**
     * �f�[�^�x�[�X���猟�������L�[��ǂݍ���Bean�̃N���X���擾����B<p>
     *
     * @return �ǂݍ���Bean�̃N���X
     */
    public Class getKeyClass();
    
    /**
     * ���R�[�h����擾����L�[�̃v���p�e�B����ݒ肷��B<p>
     *
     * @param name �v���p�e�B��
     * @see #setDatabaseRecord(Record)
     */
    public void setKeyPropertyName(String name);
    
    /**
     * ���R�[�h����擾����L�[�̃v���p�e�B�����擾����B<p>
     *
     * @return �v���p�e�B��
     */
    public String getKeyPropertyName();
    
    
    /**
     * ���R�[�h����擾����Bean�ɐݒ肷��v���p�e�B���̃}�b�s���O��ݒ肷��B<p>
     *
     * @param getProperty ���R�[�h����擾����v���p�e�B��
     * @param setProperty Bean�ɐݒ肷��v���p�e�B��
     * @see #setKeyClass(Class)
     */
    public void setKeyPropertyMapping(String getProperty, String setProperty);
}