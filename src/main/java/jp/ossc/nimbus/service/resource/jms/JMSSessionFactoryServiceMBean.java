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
package jp.ossc.nimbus.service.resource.jms;

import jp.ossc.nimbus.core.*;

/**
 * {@link JMSSessionFactoryService}��MBean�C���^�t�F�[�X<p>
 * 
 * @author M.Takata
 * @see JMSSessionFactoryService
 */
public interface JMSSessionFactoryServiceMBean extends ServiceBaseMBean{
    
    /**
     * {@link jp.ossc.nimbus.service.jms.JMSSessionFactory JMSSessionFactory}�T�[�r�X�̃T�[�r�X����ݒ肷��B<p>
     *
     * @param name JMSSessionFactory�T�[�r�X�̃T�[�r�X��
     */
    public void setJMSSessionFactoryServiceName(ServiceName name);
    
    /**
     * {@link jp.ossc.nimbus.service.jms.JMSSessionFactory JMSSessionFactory}�T�[�r�X�̃T�[�r�X�����擾����B<p>
     *
     * @return JMSSessionFactory�T�[�r�X�̃T�[�r�X��
     */
    public ServiceName getJMSSessionFactoryServiceName();
    
    /**
     * MessageConsumer��JMS�N���C�A���g�����b�Z�[�W����M��������ACK�̕Ԃ����̃��[�h��ݒ肷��B<p>
     * {@link #setAcknowledgeMode(int)}�A{@link #setTransactionMode(boolean)}�̗�����ݒ肵�Ă��Ȃ��ꍇ�́A{@link jp.ossc.nimbus.service.jms.JMSSessionFactory}�̐ݒ�ɏ]���B{@link #setTransactionMode(boolean)}�����ݒ肵���ꍇ�́A�f�t�H���g��{@link javax.jms.Session#AUTO_ACKNOWLEDGE}�B<br>
     *
     * @param mode ACK�̕Ԃ����̃��[�h������
     * @see javax.jms.Session#AUTO_ACKNOWLEDGE
     * @see javax.jms.Session#CLIENT_ACKNOWLEDGE
     * @see javax.jms.Session#DUPS_OK_ACKNOWLEDGE
     */
    public void setAcknowledgeMode(int mode);
    
    /**
     * MessageConsumer��JMS�N���C�A���g�����b�Z�[�W����M��������ACK�̕Ԃ����̃��[�h���擾����B<p>
     *
     * @return ACK�̕Ԃ����̃��[�h������
     */
    public int getAcknowledgeMode();
    
    /**
     * �g�����U�N�V�������T�|�[�g���邩�ǂ�����ݒ肷��B<p>
     * {@link #setAcknowledgeMode(int)}�A{@link #setTransactionMode(boolean)}�̗�����ݒ肵�Ă��Ȃ��ꍇ�́A{@link jp.ossc.nimbus.service.jms.JMSSessionFactory}�̐ݒ�ɏ]���B{@link #setTransactionMode(boolean)}�����ݒ肵���ꍇ�́A�f�t�H���g��false�B<br>
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
