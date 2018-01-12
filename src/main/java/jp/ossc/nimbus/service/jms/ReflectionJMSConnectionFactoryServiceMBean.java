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

import java.lang.reflect.*;

import jp.ossc.nimbus.core.*;

/**
 * {@link ReflectionJMSConnectionFactoryService}��MBean�C���^�t�F�[�X<p>
 * 
 * @author M.Takata
 * @see ReflectionJMSConnectionFactoryService
 */
public interface ReflectionJMSConnectionFactoryServiceMBean
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
     * ConnectionFactory�𐶐�����t�@�N�g���N���X�̃R���X�g���N�^��ݒ肷��B<p>
     * �t�@�N�g���N���X��static���\�b�h���Ăԏꍇ�́A�w�肷��K�v�͂Ȃ��B<br>
     * 
     * @param c �t�@�N�g���N���X�̃R���X�g���N�^
     */
    public void setFactoryConstructor(Constructor c);
    
    /**
     * ConnectionFactory�𐶐�����t�@�N�g���N���X�̃R���X�g���N�^���擾����B<p>
     * 
     * @return �t�@�N�g���N���X�̃R���X�g���N�^
     */
    public Constructor getFactoryConstructor();
    
    /**
     * ConnectionFactory�𐶐�����t�@�N�g���N���X�̃R���X�g���N�^�̈�����ݒ肷��B<p>
     * 
     * @param params �t�@�N�g���N���X�̃R���X�g���N�^�̈���
     */
    public void setFactoryConstructorParameters(Object[] params);
    
    /**
     * ConnectionFactory�𐶐�����t�@�N�g���N���X�̃R���X�g���N�^�̈������擾����B<p>
     * 
     * @return �t�@�N�g���N���X�̃R���X�g���N�^�̈���
     */
    public Object[] getFactoryConstructorParameters();
    
    /**
     * ConnectionFactory�𐶐�����t�@�N�g���N���X�̃t�@�N�g�����\�b�h��ݒ肷��B<p>
     * 
     * @param m �t�@�N�g���N���X�̃t�@�N�g�����\�b�h
     */
    public void setFactoryMethod(Method m);
    
    /**
     * ConnectionFactory�𐶐�����t�@�N�g���N���X�̃t�@�N�g�����\�b�h���擾����B<p>
     * 
     * @return �t�@�N�g���N���X�̃t�@�N�g�����\�b�h
     */
    public Method getFactoryMethod();
    
    /**
     * ConnectionFactory�𐶐�����t�@�N�g���N���X�̃t�@�N�g�����\�b�h�̈�����ݒ肷��B<p>
     * 
     * @param params �t�@�N�g���N���X�̃t�@�N�g�����\�b�h�̈���
     */
    public void setFactoryMethodParameters(Object[] params);
    
    /**
     * ConnectionFactory�𐶐�����t�@�N�g���N���X�̃t�@�N�g�����\�b�h�̈������擾����B<p>
     * 
     * @return �t�@�N�g���N���X�̃t�@�N�g�����\�b�h�̈���
     */
    public Object[] getFactoryMethodParameters();
    
    /**
     * �t�@�N�g���̃C���X�^���X��ݒ肷��B<p>
     *
     * @param fac �t�@�N�g��
     */
    public void setFactory(Object fac);
    
    /**
     * �t�@�N�g���̃C���X�^���X���擾����B<p>
     *
     * @return �t�@�N�g��
     */
    public Object getFactory();
    
    /**
     * ConnectionFactory�N���X�̃R���X�g���N�^��ݒ肷��B<p>
     * static���\�b�h���Ăԏꍇ�́A�w�肷��K�v�͂Ȃ��B<br>
     * 
     * @param c �R���X�g���N�^
     */
    public void setConnectionFactoryConstructor(Constructor c);
    
    /**
     * ConnectionFactory�N���X�̃R���X�g���N�^���擾����B<p>
     * 
     * @return �R���X�g���N�^
     */
    public Constructor getConnectionFactoryConstructor();
    
    /**
     * ConnectionFactory�N���X�̃R���X�g���N�^�̈�����ݒ肷��B<p>
     * 
     * @param params �R���X�g���N�^�̈���
     */
    public void setConnectionFactoryConstructorParameters(Object[] params);
    
    /**
     * ConnectionFactory�N���X�̃R���X�g���N�^�̈������擾����B<p>
     * 
     * @return �R���X�g���N�^�̈���
     */
    public Object[] getConnectionFactoryConstructorParameters();
    
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
     * �����Đڑ����[�h��ݒ肷��B<p>
     * �f�t�H���g�́A{@link #AUTO_RECONNECT_MODE_NON}�ŁA�Đڑ����Ȃ��B<br>
     * �Đڑ����s�����[�h�ɐݒ肵���ꍇ�́AJMS�T�[�o�̐��������m���āA�����Đڑ����s���B<br>
     * �܂����̏ꍇ�́A{@link #setKeepAliveCheckerServiceName(ServiceName)}�ŁAJMS�T�[�o�̐��������m����{@link jp.ossc.nimbus.service.keepalive.KeepAliveChecker KeepAliveChecker}�T�[�r�X��ݒ肵�Ȃ���΂Ȃ�Ȃ��B<br>
     *
     * @param mode �����Đڑ����[�h
     * @see #AUTO_RECONNECT_MODE_NON
     * @see #AUTO_RECONNECT_MODE_ON_RECOVER
     * @see #AUTO_RECONNECT_MODE_ON_DEAD
     * @see #setKeepAliveCheckerServiceName(ServiceName)
     */
    public void setAutoReconnectMode(int mode);
    
    /**
     * �����Đڑ����[�h��ݒ肷��B<p>
     *
     * @return �����Đڑ����[�h
     */
    public int getAutoReconnectMode();
    
    /**
     * JMS�T�[�o�̐��������m����{@link jp.ossc.nimbus.service.keepalive.KeepAliveChecker KeepAliveChecker}�T�[�r�X�̃T�[�r�X����ݒ肷��B<p>
     *
     * @param name KeepAliveChecker�T�[�r�X�̃T�[�r�X��
     */
    public void setKeepAliveCheckerServiceName(ServiceName name);
    
    /**
     * JMS�T�[�o�̐��������m����{@link jp.ossc.nimbus.service.keepalive.KeepAliveChecker KeepAliveChecker}�T�[�r�X�̃T�[�r�X�����擾����B<p>
     *
     * @return KeepAliveChecker�T�[�r�X�̃T�[�r�X��
     */
    public ServiceName getKeepAliveCheckerServiceName();
    
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
