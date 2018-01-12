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
package jp.ossc.nimbus.service.publish;

import java.util.Set;

import jp.ossc.nimbus.core.ServiceName;

/**
 * {@link MessageForwardingService}��MBean�C���^�t�F�[�X�B<p>
 *
 * @author M.Ishida
 * @see MessageForwardingService
 */
public interface MessageForwardingServiceMBean extends MessageReceiverServiceMBean {
    
    public static final String MSG_ID_SEND_ERROR            = "PMFS_00001";
    public static final String MSG_ID_FORWARD_ERROR         = "PMFS_00002";
    
    /**
     * �]���悩��v�����ꂽ������]�����ɑ��M�ł��Ȃ������ꍇ�ɏo�͂��郍�O���b�Z�[�WID���擾����B<p>
     *
     * @return ���O���b�Z�[�WID
     */
    public String getSendErrorMessageId();
    
    /**
     * �]���悩��v�����ꂽ������]�����ɑ��M�ł��Ȃ������ꍇ�ɏo�͂��郍�O���b�Z�[�WID��ݒ肷��B<p>
     *
     * @param id ���O���b�Z�[�WID
     */
    public void setSendErrorMessageId(String id);
    
    /**
     * �]��������̔z�M��]����ɑ��M�ł��Ȃ������ꍇ�ɏo�͂��郍�O���b�Z�[�WID���擾����B<p>
     *
     * @return ���O���b�Z�[�WID
     */
    public String getForwardErrorMessageId();
    
    /**
     * �]��������̔z�M��]����ɑ��M�ł��Ȃ������ꍇ�ɏo�͂��郍�O���b�Z�[�WID��ݒ肷��B<p>
     *
     * @param id ���O���b�Z�[�WID
     */
    public void setForwardErrorMessageId(String id);
    
    /**
     * �]�����{@link ServerConnectionFactory}�T�[�r�X�̃T�[�r�X�����擾����B<p>
     *
     * @return ServerConnectionFactory�T�[�r�X�̃T�[�r�X��
     */
    public ServiceName getServerConnectionFactoryServiceName();
    
    /**
     * �]�����{@link ServerConnectionFactory}�T�[�r�X�̃T�[�r�X����ݒ肷��B<p>
     *
     * @param name ServerConnectionFactory�T�[�r�X�̃T�[�r�X��
     */
    public void setServerConnectionFactoryServiceName(ServiceName name);
    
    /**
     * �]����ɔ񓯊����M���邩�ǂ�����ݒ肷��B<p>
     * true�̏ꍇ�A�]�����{@link ServerConnection}��{@link ServerConnection#sendAsynch(Message)}�ő��M����B<br>
     * false�̏ꍇ�A�]�����ServerConnection��{@link ServerConnection#send(Message)}�ő��M����B<br>
     * �f�t�H���g�́Afalse�œ������M�B<br>
     * 
     * @param isAsynch �񓯊����M����ꍇtrue
     */
    public void setAsynchSend(boolean isAsynch);
    
    /**
     * �]����ɔ񓯊����M���邩�ǂ����𔻒肷��B<p>
     * 
     * @return true�̏ꍇ�A�񓯊����M����
     */
    public boolean isAsynchSend();
    
    /**
     * �]������T�u�W�F�N�g��o�^����B<p>
     *
     * @param subject �T�u�W�F�N�g
     */
    public void addSubject(String subject);
    
    /**
     * �]������T�u�W�F�N�g�ƃL�[��o�^����B<p>
     *
     * @param subject �T�u�W�F�N�g
     * @param keys �L�[�̔z��
     */
    public void addSubject(String subject, String[] keys);
    
    /**
     * �o�^����Ă���T�u�W�F�N�g���擾����B<p>
     *
     * @return �o�^����Ă���T�u�W�F�N�g�̏W��
     */
    public Set getSubjects();
    
    /**
     * �w�肵���T�u�W�F�N�g�ɓo�^����Ă���L�[���擾����B<p>
     *
     * @return �o�^����Ă���L�[�̏W��
     */
    public Set getKeys(String subject);
}
