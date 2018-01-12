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
 * {@link JMSSessionFactoryService}��MBean�C���^�t�F�[�X<p>
 * 
 * @author M.Takata
 * @see JMSSessionFactoryService
 */
public interface JMSSessionFactoryServiceMBean
 extends ServiceBaseMBean{
    
    /**
     * AcknowledgeMode�����̐ݒ�l ����ACK���[�h�B<p>
     */
    public static final String AUTO_ACKNOWLEDGE = "AUTO_ACKNOWLEDGE";
    
    /**
     * AcknowledgeMode�����̐ݒ�l �N���C�A���gACK���[�h�B<p>
     */
    public static final String CLIENT_ACKNOWLEDGE = "CLIENT_ACKNOWLEDGE";
    
    /**
     * AcknowledgeMode�����̐ݒ�l �d�������e����ACK���[�h�B<p>
     */
    public static final String DUPS_OK_ACKNOWLEDGE = "DUPS_OK_ACKNOWLEDGE";
    
    /**
     * ��������JMS�Z�b�V�������Ǘ����邩�ǂ�����ݒ肷��B<p>
     * true��ݒ肵���ꍇ�A��������JMS�Z�b�V�����́A���̃T�[�r�X�ɂ���ĕێ�����Ă���A�T�[�r�X�̒�~�Ƌ���JMS�Z�b�V�����̏I���������s����B
     * ���\�[�X�̊J���R���h�����߂̋@�\�ł���B<br>
     * �f�t�H���g�́Afalse�B<br>
     *
     * @param isManaged ��������JMS�Z�b�V�������Ǘ�����ꍇtrue
     */
    public void setSessionManagement(boolean isManaged);
    
    /**
     * ��������JMS�Z�b�V�������Ǘ����邩�ǂ����𔻒肷��B<p>
     *
     * @return true�̏ꍇ�A��������JMS�Z�b�V�������Ǘ�����
     */
    public boolean isSessionManagement();
    
    /**
     * {@link JMSConnectionFactory}�T�[�r�X�̃T�[�r�X����ݒ肷��B<p>
     * ConnectionCreate������true�̏ꍇ�A�T�[�r�X�̊J�n���ɁA�����Őݒ肳�ꂽJMSConnectionFactory�T�[�r�X���g���āAConnection�𐶐����ێ�����B<br>
     *
     * @param name JMSConnectionFactory�T�[�r�X�̃T�[�r�X��
     */
    public void setJMSConnectionFactoryServiceName(ServiceName name);
    
    /**
     * {@link JMSConnectionFactory}�T�[�r�X�̃T�[�r�X�����擾����B<p>
     *
     * @return JMSConnectionFactory�T�[�r�X�̃T�[�r�X��
     */
    public ServiceName getJMSConnectionFactoryServiceName();
    
    /**
     * �T�[�r�X�̊J�n����Connection�𐶐����ĕێ����邩�ǂ�����ݒ肷��B<p>
     * true��ݒ肷��ꍇ�AJMSConnectionFactoryServiceName������ݒ肵�Ȃ���΂Ȃ�Ȃ��B<br>
     * �f�t�H���g�́Atrue�B<br>
     *
     * @param isCreate �T�[�r�X�̊J�n����Connection�𐶐����ĕێ�����ꍇtrue
     */
    public void setConnectionCreate(boolean isCreate);
    
    /**
     * �T�[�r�X�̊J�n����Connection�𐶐����ĕێ����邩�ǂ����𔻒肷��B<p>
     *
     * @return true�̏ꍇ�A�T�[�r�X�̊J�n����Connection�𐶐����ĕێ�����
     */
    public boolean isConnectionCreate();
    
    /**
     * Connection�̊J�n���������邩�ǂ�����ݒ肷��B<p>
     * Connection��ێ�����ꍇ�́A�T�[�r�X�̊J�n���ɊJ�n���������邩�ǂ�����ݒ肷��B<br>
     * Connection��ێ����Ȃ��ꍇ�́ASession�𐶐����鎞�ɊJ�n���������邩�ǂ�����ݒ肷��B<br>
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
     * �T�[�r�X�̒�~���ɕێ����Ă���Connection�̒�~���������邩�ǂ�����ݒ肷��B<p>
     * �f�t�H���g�́Afalse�B<br>
     *
     * @param isStop �T�[�r�X�̒�~���ɕێ����Ă���Connection�̒�~����������ꍇtrue
     */
    public void setStopConnection(boolean isStop);
    
    /**
     * �T�[�r�X�̒�~���ɕێ����Ă���Connection�̒�~���������邩�ǂ����𔻒肷��B<p>
     *
     * @return true�̏ꍇ�A�T�[�r�X�̒�~���ɕێ����Ă���Connection�̒�~����������
     */
    public boolean isStopConnection();
    
    /**
     * �T�[�r�X�̒�~���ɕێ����Ă���Connection�̃N���[�Y���������邩�ǂ�����ݒ肷��B<p>
     * �f�t�H���g�́Afalse�B<br>
     *
     * @param isClose �T�[�r�X�̒�~���ɕێ����Ă���Connection�̃N���[�Y����������ꍇtrue
     */
    public void setCloseConnection(boolean isClose);
    
    /**
     * �T�[�r�X�̒�~���ɕێ����Ă���Connection�̃N���[�Y���������邩�ǂ����𔻒肷��B<p>
     *
     * @return true�̏ꍇ�A�T�[�r�X�̒�~���ɕێ����Ă���Connection�̃N���[�Y����������
     */
    public boolean isCloseConnection();
    
    /**
     * MessageConsumer��JMS�N���C�A���g�����b�Z�[�W����M��������ACK�̕Ԃ����̃��[�h��ݒ肷��B<p>
     * �f�t�H���g�́A{@link #AUTO_ACKNOWLEDGE}�B<br>
     *
     * @param mode ACK�̕Ԃ����̃��[�h������
     * @see #AUTO_ACKNOWLEDGE
     * @see #CLIENT_ACKNOWLEDGE
     * @see #DUPS_OK_ACKNOWLEDGE
     */
    public void setAcknowledgeMode(String mode);
    
    /**
     * MessageConsumer��JMS�N���C�A���g�����b�Z�[�W����M��������ACK�̕Ԃ����̃��[�h���擾����B<p>
     *
     * @return ACK�̕Ԃ����̃��[�h������
     */
    public String getAcknowledgeMode();
    
    /**
     * �g�����U�N�V�������T�|�[�g���邩�ǂ�����ݒ肷��B<p>
     * �f�t�H���g�́Afalse�B<br>
     *
     * @param isTransacted �g�����U�N�V�������T�|�[�g����ꍇ�Atrue
     */
    public void setTransactionMode(boolean isTransacted);
    
    /**
     * �g�����U�N�V�������T�|�[�g���邩�ǂ����𔻒肷��B<p>
     *
     * @return true�̏ꍇ�A�g�����U�N�V�������T�|�[�g����
     */
    public boolean getTransactionMode();
}
