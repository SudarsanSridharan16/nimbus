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

/**
 * ���b�Z�[�W����M�p�̃R�l�N�V�����C���^�t�F�[�X�B<p>
 *
 * @author M.Takata
 */
public interface RequestServerConnection extends ServerConnection{
    
    /**
     * �v�����b�Z�[�W�𑗐M���āA�������b�Z�[�W����M����B<br>
     *
     * @param message ���b�Z�[�W
     * @param replyCount �K�v�ȉ�������
     * @param timeout �^�C���A�E�g
     * @return �������b�Z�[�W�z��
     * @exception MessageSendException ���b�Z�[�W�̑��M�Ɏ��s�����ꍇ
     * @exception RequestTimeoutException ���b�Z�[�W�̉����҂��Ń^�C���A�E�g�����ꍇ
     */
    public Message[] request(Message message, int replyCount, long timeout) throws MessageSendException, RequestTimeoutException;
    
    /**
     * �v�����b�Z�[�W�𑗐M���āA�������b�Z�[�W����M����B<br>
     *
     * @param message ���b�Z�[�W
     * @param responseSubject �����T�u�W�F�N�g
     * @param responseKey �����L�[
     * @param replyCount �K�v�ȉ�������
     * @param timeout �^�C���A�E�g
     * @return �������b�Z�[�W�z��
     * @exception MessageSendException ���b�Z�[�W�̑��M�Ɏ��s�����ꍇ
     * @exception RequestTimeoutException ���b�Z�[�W�̉����҂��Ń^�C���A�E�g�����ꍇ
     */
    public Message[] request(Message message, String responseSubject, String responseKey, int replyCount, long timeout) throws MessageSendException, RequestTimeoutException;
    
    /**
     * �v�����b�Z�[�W�𑗐M����B<br>
     *
     * @param message ���b�Z�[�W
     * @param replyCount �K�v�ȉ�������
     * @param timeout �^�C���A�E�g
     * @return �v���ʔ�
     * @exception MessageSendException ���b�Z�[�W�̑��M�Ɏ��s�����ꍇ
     * @exception RequestTimeoutException ���b�Z�[�W�̑��M�Ń^�C���A�E�g�����ꍇ
     */
    public int sendRequest(Message message, int replyCount, long timeout) throws MessageSendException, RequestTimeoutException;
    
    /**
     * �v�����b�Z�[�W�𑗐M����B<br>
     *
     * @param message ���b�Z�[�W
     * @param responseSubject �����T�u�W�F�N�g
     * @param responseKey �����L�[
     * @param replyCount �K�v�ȉ�������
     * @param timeout �^�C���A�E�g
     * @return �v���ʔ�
     * @exception MessageSendException ���b�Z�[�W�̑��M�Ɏ��s�����ꍇ
     * @exception RequestTimeoutException ���b�Z�[�W�̑��M�Ń^�C���A�E�g�����ꍇ
     */
    public int sendRequest(Message message, String responseSubject, String responseKey, int replyCount, long timeout) throws MessageSendException, RequestTimeoutException;
    
    /**
     * �������b�Z�[�W����M����B<br>
     *
     * @param sequence �v���ʔ�
     * @param timeout �^�C���A�E�g
     * @return �������b�Z�[�W�z��
     * @exception MessageSendException ���b�Z�[�W�̑��M�Ɏ��s�����ꍇ
     * @exception RequestTimeoutException ���b�Z�[�W�̉����҂��Ń^�C���A�E�g�����ꍇ
     */
    public Message[] getReply(int sequence, long timeout) throws MessageSendException, RequestTimeoutException;
    
    /**
     * �v�����b�Z�[�W�𑗐M���āA�������b�Z�[�W���R�[���o�b�N��M����B<br>
     *
     * @param message ���b�Z�[�W
     * @param replyCount �K�v�ȉ�������
     * @param timeout �^�C���A�E�g
     * @param callback �������b�Z�[�W��M�p�̃R�[���o�b�N
     * @exception MessageSendException ���b�Z�[�W�̑��M�Ɏ��s�����ꍇ
     */
    public void request(Message message, int replyCount, long timeout, ResponseCallBack callback) throws MessageSendException;
    
    /**
     * �v�����b�Z�[�W�𑗐M���āA�������b�Z�[�W���R�[���o�b�N��M����B<br>
     *
     * @param message ���b�Z�[�W
     * @param responseSubject �����T�u�W�F�N�g
     * @param responseKey �����L�[
     * @param replyCount �K�v�ȉ�������
     * @param timeout �^�C���A�E�g
     * @param callback �������b�Z�[�W��M�p�̃R�[���o�b�N
     * @exception MessageSendException ���b�Z�[�W�̑��M�Ɏ��s�����ꍇ
     */
    public void request(Message message, String responseSubject, String responseKey, int replyCount, long timeout, ResponseCallBack callback) throws MessageSendException;
    
    /**
     * �������b�Z�[�W�𑗐M����B<p>
     *
     * @param sourceId ���M��ID
     * @param sequence �ʔ�
     * @param message �������b�Z�[�W
     * @exception MessageSendException ���b�Z�[�W�̑��M�Ɏ��s�����ꍇ
     */
    public void response(Object sourceId, int sequence, Message message) throws MessageSendException;
    
    /**
     * �������b�Z�[�W��M�p�̃R�[���o�b�N�C���^�t�F�[�X�B<p>
     *
     * @author M.Takata
     */
    public interface ResponseCallBack{
        
        /**
         * �������ꂽ���b�Z�[�W����M����B<p>
         *
         * @param sourceId ������ID
         * @param message �������b�Z�[�W�B�^�C���A�E�g�����ꍇ�A�܂��̓��b�Z�[�W��v�����鑊�肪���Ȃ��ꍇ�́Anull
         * @param isLast �ŏI�������b�Z�[�W�̏ꍇ��true��Ԃ��B�܂��A�^�C���A�E�g�����ꍇ�A�܂��̓��b�Z�[�W��v�����鑊�肪���Ȃ��ꍇ��true
         */
        public void onResponse(Object sourceId, Message message, boolean isLast);
    }
}