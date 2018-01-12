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
 * ���b�Z�[�W��M�C���^�t�F�[�X�B<p>
 * ������{@link MessageListener}��o�^�Ǘ����āA�S�̂Ƃ��ĕK�v�ȃT�u�W�F�N�g�̓o�^�Ǘ����s���A�eMessageListener�ɂ��ꂼ�ꂪ�K�v�Ƃ��郁�b�Z�[�W��z�M����B<br>
 * 
 * @author M.Takata
 */
public interface MessageReceiver{
    
    /**
     * �w�肵��{@link MessageListener}�ɑ΂��āA�w�肵���T�u�W�F�N�g�̃��b�Z�[�W��z�M����悤�ɓo�^����B<p>
     *
     * @param listener ���b�Z�[�W���X�i
     * @param subject �T�u�W�F�N�g
     * @exception MessageSendException �T�u�W�F�N�g�o�^�̃��N�G�X�g���M�Ɏ��s�����ꍇ
     */
    public void addSubject(MessageListener listener, String subject) throws MessageSendException;
    
    /**
     * �w�肵��{@link MessageListener}�ɑ΂��āA�w�肵���T�u�W�F�N�g���w�肵���L�[�̃��b�Z�[�W��z�M����悤�ɓo�^����B<p>
     *
     * @param listener ���b�Z�[�W���X�i
     * @param subject �T�u�W�F�N�g
     * @param keys �L�[
     * @exception MessageSendException �T�u�W�F�N�g�o�^�̃��N�G�X�g���M�Ɏ��s�����ꍇ
     */
    public void addSubject(MessageListener listener, String subject, String[] keys) throws MessageSendException;
    
    /**
     * �w�肵��{@link MessageListener}�ɑ΂��āA�w�肵���T�u�W�F�N�g�̃��b�Z�[�W�z�M����������B<p>
     *
     * @param listener ���b�Z�[�W���X�i
     * @param subject �T�u�W�F�N�g
     * @exception MessageSendException �T�u�W�F�N�g�����̃��N�G�X�g���M�Ɏ��s�����ꍇ
     */
    public void removeSubject(MessageListener listener, String subject) throws MessageSendException;
    
    /**
     * �w�肵��{@link MessageListener}�ɑ΂��āA�w�肵���T�u�W�F�N�g���w�肵���L�[�̃��b�Z�[�W�z�M����������B<p>
     *
     * @param listener ���b�Z�[�W���X�i
     * @param subject �T�u�W�F�N�g
     * @param keys �L�[
     * @exception MessageSendException �T�u�W�F�N�g�����̃��N�G�X�g���M�Ɏ��s�����ꍇ
     */
    public void removeSubject(MessageListener listener, String subject, String[] keys) throws MessageSendException;
    
    /**
     * �w�肵��{@link MessageListener}�ɑ΂���S�Ẵ��b�Z�[�W�z�M����������B<p>
     *
     * @param listener ���b�Z�[�W���X�i
     * @exception MessageSendException �T�u�W�F�N�g�����̃��N�G�X�g���M�Ɏ��s�����ꍇ
     */
    public void removeMessageListener(MessageListener listener) throws MessageSendException;
    
    /**
     * �w�肵��{@link MessageListener}�ɑ΂��ēo�^����Ă���T�u�W�F�N�g���擾����B<p>
     *
     * @param listener ���b�Z�[�W���X�i
     * @return �T�u�W�F�N�g�̏W��
     */
    public Set getSubjects(MessageListener listener);
    
    /**
     * �w�肵��{@link MessageListener}�A�w�肳�ꂽ�T�u�W�F�N�g�ɑ΂��ēo�^����Ă���L�[���擾����B<p>
     *
     * @param listener ���b�Z�[�W���X�i
     * @param subject �T�u�W�F�N�g
     * @return �L�[�̏W��
     */
    public Set getKeys(MessageListener listener, String subject);
    
    /**
     * {@link ClientConnection}���擾����B<p>
     *
     * @return ClientConnection
     */
    public ClientConnection getClientConnection();
    
    /**
     * {@link ClientConnection}��ڑ�����B<p>
     *
     * @exception Exception �ڑ��Ɏ��s�����ꍇ
     */
    public void connect() throws Exception;
    
    /**
     * {@link ClientConnection}��ؒf����B<p>
     */
    public void close();
    
    /**
     * �ڑ����Ă��邩�ǂ����𔻒肷��B<p>
     *
     * @return �ڑ����Ă���ꍇtrue
     */
    public boolean isConnected();
    
    /**
     * ���b�Z�[�W�̎�M���J�n����B<br>
     *
     * @exception MessageSendException ��M�J�n�̃��N�G�X�g���M�Ɏ��s�����ꍇ
     */
    public void startReceive() throws MessageSendException;
    
    /**
     * ���b�Z�[�W�̎�M���~����B<br>
     *
     * @exception MessageSendException ��M��~�̃��N�G�X�g���M�Ɏ��s�����ꍇ
     */
    public void stopReceive() throws MessageSendException;
    
    /**
     * �z�M�J�n���Ă��邩�ǂ����𔻒肷��B<br>
     *
     * @return �z�M�J�n���Ă���ꍇtrue
     */
    public boolean isStartReceive();
    
    /**
     * �g�p���Ă���ڑ���ID���擾����B<p>
     *
     * @return �g�p���Ă���ڑ���ID
     */
    public Object getId();
}