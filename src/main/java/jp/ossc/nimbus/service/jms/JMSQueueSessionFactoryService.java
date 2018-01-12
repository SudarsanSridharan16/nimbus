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
 * JMS Queue�Z�b�V�����t�@�N�g���B<p>
 * JMS1.0�ł́AQueue��Topic�̃C���^�t�F�[�X�����ꂳ��Ă��Ȃ��������߁AQueue��p�̃Z�b�V�����������s���B<br>
 * 
 * @author M.Takata
 */
public class JMSQueueSessionFactoryService extends JMSSessionFactoryService{
    
    private static final long serialVersionUID = -4113317724617492287L;
    
    /**
     * JMS Queue�Z�b�V�������擾����B<p>
     * �����Ŏw�肳�ꂽQueueConnection����A{@link QueueConnection#createQueueSession(boolean, int)}���\�b�h�Ő�������B<br>
     *
     * @param con QueueConnection
     * @param transactionMode �g�����U�N�V�������T�|�[�g����ꍇ�Atrue
     * @param ackMode MessageConsumer��JMS�N���C�A���g�����b�Z�[�W����M��������ACK�̕Ԃ����̃��[�h
     * @return JMS Queue�Z�b�V����
     * @exception JMSSessionCreateException JMS Queue�Z�b�V�����̐����Ɏ��s�����ꍇ
     */
    public Session getSession(
        Connection con,
        boolean transactionMode,
        int ackMode
    ) throws JMSSessionCreateException{
        if(con != null && !(con instanceof QueueConnection)){
            throw new JMSSessionCreateException(
                "Connection is not QueueConnection."
            );
        }
        try{
            final Session session = ((QueueConnection)con).createQueueSession(
                transactionMode,
                ackMode
            );
            if(isSessionManagement){
                sessions.add(session);
            }
            return session;
        }catch(JMSException e){
            throw new JMSSessionCreateException(e);
        }
    }
}
