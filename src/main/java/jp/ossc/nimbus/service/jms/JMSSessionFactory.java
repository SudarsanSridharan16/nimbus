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

import javax.jms.*;

/**
 * JMS�Z�b�V�����t�@�N�g���B<p>
 * 
 * @author M.Takata
 */
public interface JMSSessionFactory{
    
    /**
     * ���̃t�@�N�g�����ێ����Ă���Connection���擾����B<p>
     * getSession���\�b�h�ŁASession�𐶐�����ۂɁA������Connection���w�肵�Ȃ������ꍇ�́A����Connection���g�p�����B<br>
     *
     * @return ���̃t�@�N�g�����ێ����Ă���Connection�BConnection��ێ����Ă��Ȃ��ꍇ��null�B
     */
    public Connection getConnection();
    
    /**
     * ���̃t�@�N�g����Connection�̎擾�Ɏg�p����{@link JMSConnectionFactory}�T�[�r�X���擾����B<p>
     *
     * @return {@link JMSConnectionFactory}�T�[�r�X
     */
    public JMSConnectionFactory getConnectionFactory();
    
    /**
     * JMS�Z�b�V�������擾����B<p>
     * {@link #getConnection()}�Ŏ擾�����Connection����A{@link Connection#createSession(boolean, int)}���\�b�h�Ő�������B<br>
     * getConnection()��null��Ԃ��ꍇ�́AJMSSessionCreateException��throw����B<br>
     * �����́A�T�[�r�X�̎����Ɉˑ����邪�A���ɐݒ肪�Ȃ����false�ASession.AUTO_ACKNOWLEDGE�B<br>
     *
     * @return JMS�Z�b�V����
     * @exception JMSSessionCreateException JMS�Z�b�V�����̐����Ɏ��s�����ꍇ
     */
    public Session getSession() throws JMSSessionCreateException;
    
    /**
     * JMS�Z�b�V�������擾����B<p>
     * {@link #getConnection()}�Ŏ擾�����Connection����A{@link Connection#createSession(boolean, int)}���\�b�h�Ő�������B<br>
     * getConnection()��null��Ԃ��ꍇ�́AJMSSessionCreateException��throw����B<br>
     *
     * @param transactionMode �g�����U�N�V�������T�|�[�g����ꍇ�Atrue
     * @param ackMode MessageConsumer��JMS�N���C�A���g�����b�Z�[�W����M��������ACK�̕Ԃ����̃��[�h
     * @return JMS�Z�b�V����
     * @exception JMSSessionCreateException JMS�Z�b�V�����̐����Ɏ��s�����ꍇ
     */
    public Session getSession(
        boolean transactionMode,
        int ackMode
    ) throws JMSSessionCreateException;
    
    /**
     * JMS�Z�b�V�������擾����B<p>
     * �����Ŏw�肳�ꂽConnection����A{@link Connection#createSession(boolean, int)}���\�b�h�Ő�������B<br>
     * �����́A�T�[�r�X�̎����Ɉˑ����邪�A���ɐݒ肪�Ȃ����false�ASession.AUTO_ACKNOWLEDGE�B<br>
     *
     * @param con Connection
     * @return JMS�Z�b�V����
     * @exception JMSSessionCreateException JMS�Z�b�V�����̐����Ɏ��s�����ꍇ
     */
    public Session getSession(Connection con) throws JMSSessionCreateException;
    
    /**
     * JMS�Z�b�V�������擾����B<p>
     * �����Ŏw�肳�ꂽConnection����A{@link Connection#createSession(boolean, int)}���\�b�h�Ő�������B<br>
     *
     * @param con Connection
     * @param transactionMode �g�����U�N�V�������T�|�[�g����ꍇ�Atrue
     * @param ackMode MessageConsumer��JMS�N���C�A���g�����b�Z�[�W����M��������ACK�̕Ԃ����̃��[�h
     * @return JMS�Z�b�V����
     * @exception JMSSessionCreateException JMS�Z�b�V�����̐����Ɏ��s�����ꍇ
     */
    public Session getSession(
        Connection con,
        boolean transactionMode,
        int ackMode
    ) throws JMSSessionCreateException;
}
