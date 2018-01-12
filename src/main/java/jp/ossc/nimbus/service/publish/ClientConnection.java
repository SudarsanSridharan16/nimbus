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
 * ���b�Z�[�W��M�p�̃N���C�A���g�R�l�N�V�����C���^�t�F�[�X�B<p>
 * ���b�Z�[�W��M���s���N���C�A���g���̃R�l�N�V�����C���^�t�F�[�X�B<br>
 * 
 * @author M.Takata
 */
public interface ClientConnection{
    
    /**
     * ���̃R�l�N�V�������T�[�r�X�Ƃ��ēo�^����{@link jp.ossc.nimbus.core.ServiceManager ServiceManager}�̖��O��ݒ肷��B<p>
     *
     * @param name ServiceManager�̖��O
     */
    public void setServiceManagerName(String name);
    
    /**
     * �T�[�o�Ɛڑ�����B<p>
     *
     * @exception ConnectException �T�[�o�Ƃ̐ڑ��Ɏ��s�����ꍇ
     */
    public void connect() throws ConnectException;
    
    /**
     * �T�[�o�Ɛڑ�����B<p>
     *
     * @param id �N���C�A���g�����ʂ���ID
     * @exception ConnectException �T�[�o�Ƃ̐ڑ��Ɏ��s�����ꍇ
     */
    public void connect(Object id) throws ConnectException;
    
    /**
     * �z�M���ė~�����T�u�W�F�N�g���T�[�o�ɗv������B<br>
     *
     * @param subject �T�u�W�F�N�g
     * @exception MessageSendException �T�[�o�ւ̗v���Ɏ��s�����ꍇ
     */
    public void addSubject(String subject) throws MessageSendException;
    
    /**
     * �z�M���ė~�����T�u�W�F�N�g�ƃL�[���T�[�o�ɗv������B<br>
     *
     * @param subject �T�u�W�F�N�g
     * @param keys �L�[
     * @exception MessageSendException �T�[�o�ւ̗v���Ɏ��s�����ꍇ
     */
    public void addSubject(String subject, String[] keys) throws MessageSendException;
    
    /**
     * �z�M���������ė~�����T�u�W�F�N�g���T�[�o�ɗv������B<br>
     *
     * @param subject �T�u�W�F�N�g
     * @exception MessageSendException �T�[�o�ւ̗v���Ɏ��s�����ꍇ
     */
    public void removeSubject(String subject) throws MessageSendException;
    
    /**
     * �z�M���������ė~�����T�u�W�F�N�g�ƃL�[���T�[�o�ɗv������B<br>
     *
     * @param subject �T�u�W�F�N�g
     * @param keys �L�[
     * @exception MessageSendException �T�[�o�ւ̗v���Ɏ��s�����ꍇ
     */
    public void removeSubject(String subject, String[] keys) throws MessageSendException;
    
    /**
     * �z�M�J�n���T�[�o�ɗv������B<br>
     *
     * @exception MessageSendException �T�[�o�ւ̗v���Ɏ��s�����ꍇ
     */
    public void startReceive() throws MessageSendException;
    
    /**
     * �w�肵���ߋ��̎��Ԃ̃f�[�^����z�M�J�n���T�[�o�ɗv������B<br>
     *
     * @param from �J�n����
     * @exception MessageSendException �T�[�o�ւ̗v���Ɏ��s�����ꍇ
     */
    public void startReceive(long from) throws MessageSendException;
    
    /**
     * �z�M��~���T�[�o�ɗv������B<br>
     *
     * @exception MessageSendException �T�[�o�ւ̗v���Ɏ��s�����ꍇ
     */
    public void stopReceive() throws MessageSendException;
    
    /**
     * �z�M�J�n���Ă��邩�ǂ����𔻒肷��B<br>
     *
     * @return �z�M�J�n���Ă���ꍇtrue
     */
    public boolean isStartReceive();
    
    /**
     * �o�^����Ă���T�u�W�F�N�g���擾����B<p>
     *
     * @return �T�u�W�F�N�g�̏W��
     */
    public Set getSubjects();
    
    /**
     * �w�肳�ꂽ�T�u�W�F�N�g�ɑ΂��ēo�^����Ă���L�[���擾����B<p>
     *
     * @param subject �T�u�W�F�N�g
     * @return �L�[�̏W��
     */
    public Set getKeys(String subject);
    
    /**
     * ���b�Z�[�W��M�̒ʒm��ł���{@link MessageListener ���b�Z�[�W���X�i}��ݒ肷��B<br>
     *
     * @param listener ���b�Z�[�W���X�i
     */
    public void setMessageListener(MessageListener listener);
    
    /**
     * �ڑ����Ă��邩�ǂ����𔻒肷��B<p>
     *
     * @return �ڑ����Ă���ꍇtrue
     */
    public boolean isConnected();
    
    /**
     * �T�[�o������ؒf�v�����󂯂����ǂ����𔻒肷��B<p>
     *
     * @return �T�[�o������ؒf�v�����󂯂��ꍇtrue
     */
    public boolean isServerClosed();
    
    /**
     * ���̐ڑ���ID���擾����B<p>
     *
     * @return ���̐ڑ���ID
     */
    public Object getId();
    
    /**
     * �T�[�o�Ɛؒf����B<p>
     */
    public void close();
}