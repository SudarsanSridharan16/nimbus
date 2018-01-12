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
package jp.ossc.nimbus.service.jms;

import jp.ossc.nimbus.core.*;

/**
 * {@link JMSConnectionFactoryService}��MBean�C���^�t�F�[�X<p>
 * 
 * @author M.Takata
 * @see JMSConnectionFactoryService
 */
public interface JMSConnectionFactoryServiceMBean
 extends ServiceBaseMBean{
    
    /**
     * �����Đڑ����[�h�F�Đڑ����Ȃ��B<p>
     */
    public static int AUTO_RECONNECT_MODE_NON = 0;
    
    /**
     * �����Đڑ����[�h�FJNDI�T�[�o�񕜌��m���ɍĐڑ�����B<p>
     */
    public static int AUTO_RECONNECT_MODE_ON_RECOVER = ReconnectableConnection.RECONNECT_MODE_ON_RECOVER;
    
    /**
     * �����Đڑ����[�h�FJNDI�T�[�o�_�E�����m���ɍĐڑ�����B<p>
     */
    public static int AUTO_RECONNECT_MODE_ON_DEAD = ReconnectableConnection.RECONNECT_MODE_ON_DEAD;
    
    /**
     * ConnectionFactoryName�����̃f�t�H���g�l�B<p>
     * �f�t�H���g�ł́AJ2EE�R���e�i�̃��[�J����XA�ڑ����g�p����B
     */
    public static final String DEFAULT_CONNECTION_FACTORY_NAME
         = "java:XAConnectionFactory";
    
    /**
     * ConnectionKey�����̃f�t�H���g�l�B<p>
     */
    public static final String DEFAULT_CONNECTION_KEY
         = "JMSConnection";
    
    /**
     * JMS�R�l�N�V�����̃C���X�^���X���P�����������邩�ǂ�����ݒ肷��B<p>
     * JMS�R�l�N�V�����́A�����I�Ȑڑ����\���I�u�W�F�N�g�ł��邽�߁A�ʏ�C���X�^���X�́A�P�����������Ďg�p���ׂ��ł���B<br>
     * �f�t�H���g�́Atrue�B<br>
     *
     * @param isSingle JMS�R�l�N�V�����̃C���X�^���X���P������������ꍇ��true
     */
    public void setSingleConnection(boolean isSingle);
    
    /**
     * JMS�R�l�N�V�����̃C���X�^���X���P�����������邩�ǂ����𔻒肷��B<p>
     *
     * @return true�̏ꍇ�AJMS�R�l�N�V�����̃C���X�^���X���P������������
     */
    public boolean isSingleConnection();
    
    /**
     * ��������JMS�R�l�N�V�������Ǘ����邩�ǂ�����ݒ肷��B<p>
     * true��ݒ肵���ꍇ�A��������JMS�R�l�N�V�����́A���̃T�[�r�X�ɂ���ĕێ�����Ă���A�T�[�r�X�̒�~�Ƌ���JMS�R�l�N�V�����̏I���������s����B
     * ���\�[�X�̊J���R���h�����߂̋@�\�ł���B<br>
     * �A���ASingleConnection������true�ɐݒ肵�Ă���ꍇ�́A���̑�����true�ɂ��Ȃ��Ă����l�̏������s����B<br>
     * �f�t�H���g�́Afalse�B<br>
     *
     * @param isManaged ��������JMS�R�l�N�V�������Ǘ�����ꍇtrue
     */
    public void setConnectionManagement(boolean isManaged);
    
    /**
     * ��������JMS�R�l�N�V�������Ǘ����邩�ǂ����𔻒肷��B<p>
     *
     * @return true�̏ꍇ�A��������JMS�R�l�N�V�������Ǘ�����
     */
    public boolean isConnectionManagement();
    
    /**
     * Connection�𐶐����鎞��Connection�̊J�n���������邩�ǂ�����ݒ肷��B<p>
     * �f�t�H���g�́Afalse�B<br>
     *
     * @param isStart Connection�̊J�n����������ꍇtrue
     */
    public void setStartConnection(boolean isStart);
    
    /**
     * Connection�̊J�n���������邩�ǂ����𔻒肷��B<p>
     *
     * @return true�̏ꍇ�AConnection�̊J�n����������
     */
    public boolean isStartConnection();
    
    /**
     * {@link jp.ossc.nimbus.service.jndi.JndiFinder JndiFinder}�T�[�r�X�̃T�[�r�X����ݒ肷��B<p>
     * �����Őݒ肳�ꂽJndiFinder�T�[�r�X���g���āAJNDI�T�[�o����javax.jms.ConnectionFactory��lookup����B<br>
     *
     * @param name JndiFinder�T�[�r�X�̃T�[�r�X��
     */
    public void setJndiFinderServiceName(ServiceName name);
    
    /**
     * {@link jp.ossc.nimbus.service.jndi.JndiFinder JndiFinder}�T�[�r�X�̃T�[�r�X�����擾����B<p>
     *
     * @return JndiFinder�T�[�r�X�̃T�[�r�X��
     */
    public ServiceName getJndiFinderServiceName();
    
    /**
     * javax.jms.ConnectionFactory��JNDI����ݒ肷��B<p>
     * �f�t�H���g�́A{@link #DEFAULT_CONNECTION_FACTORY_NAME}�B<br>
     *
     * @param name javax.jms.ConnectionFactory��JNDI��
     * @see #DEFAULT_CONNECTION_FACTORY_NAME
     */
    public void setConnectionFactoryName(String name);
    
    /**
     * javax.jms.ConnectionFactory��JNDI�����擾����B<p>
     *
     * @return javax.jms.ConnectionFactory��JNDI��
     */
    public String getConnectionFactoryName();
    
    /**
     * JMS�ڑ����[�U����ݒ肷��B<p>
     * J2EE�R���e�i����JMS�ڑ��ɑ΂��ăZ�L�����e�B�ݒ���s���Ă���ꍇ�ɁA�ݒ肷��B<br>
     *
     * @param name JMS�ڑ����[�U��
     */
    public void setUserName(String name);
    
    /**
     * JMS�ڑ����[�U�����擾����B<p>
     *
     * @return JMS�ڑ����[�U��
     */
    public String getUserName();
    
    /**
     * JMS�ڑ��p�X���[�h��ݒ肷��B<p>
     * J2EE�R���e�i����JMS�ڑ��ɑ΂��ăZ�L�����e�B�ݒ���s���Ă���ꍇ�ɁA�ݒ肷��B<br>
     *
     * @param passwd JMS�ڑ��p�X���[�h
     */
    public void setPassword(String passwd);
    
    /**
     * JMS�ڑ��p�X���[�h���擾����B<p>
     *
     * @return JMS�ڑ��p�X���[�h
     */
    public String getPassword();
    
    /**
     * {@link jp.ossc.nimbus.service.cache.CacheMap CacheMap}�T�[�r�X�̃T�[�r�X����ݒ肷��B<p>
     * SingleConnection������true�ɐݒ肵�Ă���ꍇ�̂ݗL���ŁA��������JMS�R�l�N�V�������A�����Őݒ肳�ꂽCacheMap�T�[�r�X�ɃL���b�V������B
     * ���̍ۂ̃L���b�V���L�[�́AConnectionKey�����̒l���g�p�����B<br>
     * �L���b�V������JMS�R�l�N�V�������L���b�V���A�E�g�����ƁA��������m����JMS�R�l�N�V�����̏I���������s���B<br>
     * �ʏ�A{@link jp.ossc.nimbus.service.jndi.CachedJndiFinderService CachedJndiFinderService}��CacheMapServiceName�����Őݒ肵��CacheMap�T�[�r�X�����p���ACachedJndiFinderService��JNDI�T�[�o�_�E�������m�����ۂɁA�L���b�V�����N���A����@�\�𗘗p���āAJMS�R�l�N�V�����̍Đڑ����s���B<br>
     *
     * @param name CacheMap�T�[�r�X�̃T�[�r�X��
     */
    public void setConnectionCacheMapServiceName(ServiceName name);
    
    /**
     * {@link jp.ossc.nimbus.service.cache.CacheMap CacheMap}�T�[�r�X�̃T�[�r�X�����擾����B<p>
     *
     * @return CacheMap�T�[�r�X�̃T�[�r�X��
     */
    public ServiceName getConnectionCacheMapServiceName();
    
    /**
     * JMS�R�l�N�V�������L���b�V������ۂ̃L���b�V���L�[��ݒ肷��B<p>
     * SingleConnection������true�ɂ��āAConnectionCacheMapServiceName������ݒ肵�Ă���ꍇ�ɁAJMS�R�l�N�V������{@link jp.ossc.nimbus.service.cache.CacheMap CacheMap}�T�[�r�X�ɃL���b�V������ۂ̃L�[��ݒ肷��B<br>
     * �f�t�H���g�́A{@link #DEFAULT_CONNECTION_KEY}�ł���B<br>
     *
     * @param key JMS�R�l�N�V�������L���b�V������ۂ̃L���b�V���L�[
     * @see #DEFAULT_CONNECTION_KEY
     */
    public void setConnectionKey(String key);
    
    /**
     * JMS�R�l�N�V�������L���b�V������ۂ̃L���b�V���L�[���擾����B<p>
     *
     * @return JMS�R�l�N�V�������L���b�V������ۂ̃L���b�V���L�[
     */
    public String getConnectionKey();
    
    /**
     * �����Đڑ����[�h��ݒ肷��B<p>
     * �f�t�H���g�́A{@link #AUTO_RECONNECT_MODE_NON}�ŁA�Đڑ����Ȃ��B<br>
     * �Đڑ����s�����[�h�ɐݒ肵���ꍇ�́AJNDI�T�[�o�̐��������m���āA�����Đڑ����s���B<br>
     * �܂����̏ꍇ�́A{@link #setJndiKeepAliveCheckerServiceName(ServiceName)}�ŁAJNDI�T�[�o�̐��������m����{@link jp.ossc.nimbus.service.keepalive.KeepAliveChecker KeepAliveChecker}�T�[�r�X��ݒ肵�Ȃ���΂Ȃ�Ȃ��B<br>
     *
     * @param mode �����Đڑ����[�h
     * @see #AUTO_RECONNECT_MODE_NON
     * @see #AUTO_RECONNECT_MODE_ON_RECOVER
     * @see #AUTO_RECONNECT_MODE_ON_DEAD
     * @see #setJndiKeepAliveCheckerServiceName(ServiceName)
     */
    public void setAutoReconnectMode(int mode);
    
    /**
     * �����Đڑ����[�h��ݒ肷��B<p>
     *
     * @return �����Đڑ����[�h
     */
    public int getAutoReconnectMode();
    
    /**
     * JNDI�T�[�o�̐��������m����{@link jp.ossc.nimbus.service.keepalive.KeepAliveChecker KeepAliveChecker}�T�[�r�X�̃T�[�r�X����ݒ肷��B<p>
     *
     * @param name KeepAliveChecker�T�[�r�X�̃T�[�r�X��
     */
    public void setJndiKeepAliveCheckerServiceName(ServiceName name);
    
    /**
     * JNDI�T�[�o�̐��������m����{@link jp.ossc.nimbus.service.keepalive.KeepAliveChecker KeepAliveChecker}�T�[�r�X�̃T�[�r�X�����擾����B<p>
     *
     * @return KeepAliveChecker�T�[�r�X�̃T�[�r�X��
     */
    public ServiceName getJndiKeepAliveCheckerServiceName();
    
    /**
     * �����Đڑ����ɁA�ڑ��Ɏ��s�����ꍇ�ɏo�͂��郍�O�̃��b�Z�[�WID��ݒ肷��B<p>
     * �f�t�H���g�́Anull�ŁA���O�o�͍͂s���Ȃ��B<br>
     * 
     * @param id ���O�̃��b�Z�[�WID
     */
    public void setAutoReconnectErrorLogMessageId(String id);
    
    /**
     * �����Đڑ����ɁA�ڑ��Ɏ��s�����ꍇ�ɏo�͂��郍�O�̃��b�Z�[�WID���擾����B<p>
     * 
     * @return ���O�̃��b�Z�[�WID
     */
    public String getAutoReconnectErrorLogMessageId();
    
    /**
     * �����Đڑ����ɁA�ڑ��Ɏ��s�����ꍇ�Ƀ��g���C�������s���񐔂�ݒ肷��B<p>
     * �f�t�H���g�́A0�Ń��g���C���Ȃ��B<br>
     *
     * @param count ���g���C��
     */
    public void setAutoReconnectMaxRetryCount(int count);
    
    /**
     * �����Đڑ����ɁA�ڑ��Ɏ��s�����ꍇ�Ƀ��g���C�������s���񐔂��擾����B<p>
     *
     * @return ���g���C��
     */
    public int getAutoReconnectMaxRetryCount();
    
    /**
     * �����Đڑ����ɁA�ڑ��Ɏ��s�����ꍇ�Ƀ��g���C�������s���Ԋu[ms]��ݒ肷��B<p>
     * �f�t�H���g�́A1000[ms]�B<br>
     *
     * @param interval ���g���C�Ԋu
     */
    public void setAutoReconnectRetryInterval(long interval);
    
    /**
     * �����Đڑ����ɁA�ڑ��Ɏ��s�����ꍇ�Ƀ��g���C�������s���Ԋu[ms]���擾����B<p>
     *
     * @return ���g���C�Ԋu
     */
    public long getAutoReconnectRetryInterval();
}
