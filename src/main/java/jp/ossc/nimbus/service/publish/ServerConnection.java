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

/**
 * ���b�Z�[�W���M�p�̃T�[�o�R�l�N�V�����C���^�t�F�[�X�B<p>
 * ���b�Z�[�W���M���s���T�[�o���̃R�l�N�V�����C���^�t�F�[�X�B<br>
 *
 * @author M.Takata
 */
public interface ServerConnection{
    
    /**
     * ���b�Z�[�W�𐶐�����B<br>
     *
     * @param subject �T�u�W�F�N�g
     * @param key �L�[
     * @return ���b�Z�[�W
     * @exception MessageCreateException ���b�Z�[�W�̐����Ɏ��s�����ꍇ
     */
    public Message createMessage(String subject, String key) throws MessageCreateException;
    
    /**
     * �w�肳�ꂽ���b�Z�[�W�����̐ڑ��ő��M�\�ȃ��b�Z�[�W�ɃL���X�g����B<p>
     *
     * @param message ���b�Z�[�W
     * @return �L���X�g���ꂽ���b�Z�[�W
     * @exception MessageException ���b�Z�[�W�̃L���X�g�Ɏ��s�����ꍇ
     */
    public Message castMessage(Message message) throws MessageException;
    
    /**
     * ���b�Z�[�W�𑗐M����B<br>
     *
     * @param message ���b�Z�[�W
     * @exception MessageSendException ���b�Z�[�W�̑��M�Ɏ��s�����ꍇ
     */
    public void send(Message message) throws MessageSendException;
    
    /**
     * ���b�Z�[�W��񓯊����M����B<br>
     *
     * @param message ���b�Z�[�W
     * @exception MessageSendException ���b�Z�[�W�̔񓯊����M�Ɏ��s�����ꍇ
     */
    public void sendAsynch(Message message) throws MessageSendException;
    
    /**
     * �N���C�A���g�̏�ԕω��̒ʒm��ł���{@link ServerConnectionListener �T�[�o�R�l�N�V�������X�i}��ǉ�����B<br>
     *
     * @param listener �T�[�o�R�l�N�V�������X�i
     */
    public void addServerConnectionListener(ServerConnectionListener listener);
    
    /**
     * �N���C�A���g�̏�ԕω��̒ʒm��ł���{@link ServerConnectionListener �T�[�o�R�l�N�V�������X�i}���폜����B<br>
     *
     * @param listener �T�[�o�R�l�N�V�������X�i
     */
    public void removeServerConnectionListener(ServerConnectionListener listener);
    
    /**
     * ���ݐڑ����Ă���N���C�A���g�����擾����B<p>
     *
     * @return �N���C�A���g��
     */
    public int getClientCount();
    
    /**
     * �N���C�A���g��ID�W�����擾����B<p>
     *
     * @return �N���C�A���g��ID�W��
     */
    public Set getClientIds();
    
    /**
     * �w�肵�����b�Z�[�W����M����N���C�A���g��ID�W�����擾����B<p>
     *
     * @param message ���M���郁�b�Z�[�W
     * @return �N���C�A���g��ID�W��
     */
    public Set getReceiveClientIds(Message message);
    
    /**
     * �w�肵��ID�̃N���C�A���g���o�^���Ă���T�u�W�F�N�g���擾����B<p>
     *
     * @param id �N���C�A���gID
     * @return �T�u�W�F�N�g�̏W��
     */
    public Set getSubjects(Object id);
    
    /**
     * �w�肵��ID�̃N���C�A���g���A�w�肵���T�u�W�F�N�g�ɑ΂��ēo�^���Ă���L�[���擾����B<p>
     *
     * @param id �N���C�A���gID
     * @param subject �T�u�W�F�N�g
     * @return �L�[�̏W��
     */
    public Set getKeys(Object id, String subject);
    
    /**
     * ����������B<p>
     */
    public void reset();
}