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
package jp.ossc.nimbus.service.resource.jmsqueue;

import jp.ossc.nimbus.core.*;

/**
 *  JMS�L���[�Z�b�V�����T�[�r�X��MBean�C���^�[�t�F�C�X	
 *	@author	y-tokuda
 *	@version	1.00 �쐬�F2003/10/24�| y-tokuda<BR>
 *				�X�V�F2006/03/31 M.Kameda
 */
public interface JmsQueueSessionServiceMBean extends ServiceBaseMBean{
	
	/** �R�l�N�V�����L���b�V���}�b�v�ւ̃f�t�H���g�i�[�L�[ */
	public static final String DEFAULT_CONNECTION_CACHE_KEY = "QueueConnection";
	
	/**
	 * JNDI�t�@�C���_�[�T�[�r�X���̃Z�b�^�[
	 * @param name
	 */
	public void setJndiFinderServiceName(ServiceName name);
	/**
	 * JNDI�t�@�C���_�[�T�[�r�X���̃Q�b�^�[
	 * @return JNDI�t�@�C���_�[�T�[�r�X��
	 */
	public ServiceName getJndiFinderServiceName();
	/**
	 * QueueSession�������́A�g�����U���N�V�������[�h�̃Z�b�^�[
	 * @param mode
	 */
	public void setTransanctionMode(boolean mode);
	/**
	 * QueueSession�������́A�g�����U���N�V�������[�h�̃Q�b�^�[
	 * @return �g�����U���N�V�������[�h
	 */
	public boolean getTransanctionMode();
	/**
	 * Acknowledge���[�h�̃Z�b�^�[�B�ȉ�3��ނ̂����ꂩ��ݒ肷��B
	 * �i�Ȃɂ��w�肵�Ȃ���΁ASession.AUTO_ACKNOWLEDGE�j
	 * 1(=Session.AUTO_ACKNOWLEDGE)
	 * 2(=Session.CLIENT_ACKNOWLEDGE)
	 * 3(=Session.DUPS_OK_ACKNOWLEDGE)
	 * @param mode
	 */
	public void  setAcknowledgeMode(int mode);
	/**
	 * Acknowledge���[�h�̃Q�b�^�[
	 * @return Acknowledge���[�h
	 */
	public int getAcknowledgeMode();
	/**
	 * �Z�}�t�H�T�[�r�X�̃Z�b�^�[
	 */
	public void setSemaphoreFactoryServiceName(ServiceName name);
	/**
	 * �Z�b�V�����L���p�V�e�B�̃Z�b�^�[
	 */
	public void setCapacity(int cap);
    
    /**
     * QueueConnection�𐶐����鎞�̃��[�U����ݒ肷��B<p>
     *
     * @param name ���[�U��
     */
    public void setUserName(String name);
    
    /**
     * QueueConnection�𐶐����鎞�̃��[�U�����擾����B<p>
     *
     * @return ���[�U��
     */
    public String getUserName();
    
    /**
     * QueueConnection�𐶐����鎞�̃p�X���[�h��ݒ肷��B<p>
     *
     * @param password �p�X���[�h
     */
    public void setPassword(String password);
    
    /**
     * QueueConnection�𐶐����鎞�̃p�X���[�h���擾����B<p>
     *
     * @return �p�X���[�h
     */
    public String getPassword();
    
    /**
     * QueueConnectionFactory��JNDI����ݒ肷��B<p>
     *
     * @param name QueueConnectionFactory��JNDI��
     */
    public void setConnectionFactoryName(String name);
    
    /**
     * QueueConnectionFactory��JNDI�����擾����B<p>
     *
     * @return QueueConnectionFactory��JNDI��
     */
    public String getConnectionFactoryName();
    
    /**
     * �L���b�V���}�b�v�ւ̃R�l�N�V�����i�[�L�[��ݒ�<p>
     * �}�b�v�Ɋi�[����ׂ̐ݒ�L�[�B�ݒ肪�����ꍇ�́A�f�t�H���g(QueueConnection)���̗p�B
     * @param key �i�[�L�[
     */
    public void setConnectionCacheKey(String key);
    /**
     * �L���b�V���}�b�v�ւ̃R�l�N�V�����i�[�L�[���擾<p>
     * �}�b�v�Ɋi�[����ׂ̐ݒ�L�[���擾�B
     * @return �i�[�L�[
     */
    public String getConnectionCacheKey();
    
    /**
     * �R�l�N�V�����L���b�V���}�b�v�T�[�r�X�̃T�[�r�X����ݒ�<p>
     * @param name �T�[�r�X��
     */
    public void setConnectionCacheMapServiceName(ServiceName name);
    /**
     * �R�l�N�V�����L���b�V���}�b�v�T�[�r�X�̃T�[�r�X�����擾<p>
     * @return �T�[�r�X��
     */
    public ServiceName getConnectionCacheMapServiceName();
}
