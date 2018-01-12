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
package jp.ossc.nimbus.service.keepalive.smtp;

import jp.ossc.nimbus.core.*;
//
/**
 * SMTP�T�[�o�`�F�b�J�[�̊Ǘ��C���^�[�t�F�C�X�B<p>
 *
 * @author H.Nakano
 * @version  1.00 �쐬: 2003/10/09 - H.Nakano
 */
public interface SmtpCheckerServiceMBean extends ServiceBaseMBean, SmtpKeepAliveChecker{
    
    /**
     * SMTP�T�[�o�����`�F�b�N�ŁASMTP�T�[�o�����񂾎��ɏo�͂���郍�O�̃��b�Z�[�WID�B<p>
     */
    public static final String SMTP_SERVER_DEAD_MSG_ID = "SMTP_00001";
    
    /**
     * SMTP�T�[�o�����`�F�b�N�ŁASMTP�T�[�o�����A�������ɏo�͂���郍�O�̃��b�Z�[�WID�B<p>
     */
    public static final String SMTP_SERVER_RECOVER_MSG_ID = "SMTP_00002";
    
    /**
     * SMTP�T�[�o�̃z�X�g����ݒ肷��B<p>
     *
     * @param hostName SMTP�T�[�o�̃z�X�g��
     */
    public void setHostName(String hostName) throws java.net.UnknownHostException;
    
    /**
     * SMTP�T�[�o�̃z�X�g�����擾����B<p>
     *
     * @return SMTP�T�[�o�̃z�X�g��
     */
    public String getHostName();
    
    /**
     * SMTP�T�[�o��Port�ԍ���ݒ肷��B<p>
     *
     * @param port SMTP�T�[�o��Port�ԍ�
     */
    public void setPort(int port);
    
    /**
     * SMTP�T�[�o��Port�ԍ����擾����B<p>
     *
     * @return SMTP�T�[�o��Port�ԍ�
     */
    public int getPort();
    
    /**
     * �ڑ��҂��̃^�C���A�E�g[ms]��ݒ肷��B<p>
     * �f�t�H���g�́A0�Ŗ����҂��B<br>
     *
     * @param milisec �^�C���A�E�g[ms]
     */
    public void setConnectionTimeoutMillis(int milisec);
    
    /**
     * �ڑ��҂��̃^�C���A�E�g[ms]���擾����B<p>
     *
     * @return �^�C���A�E�g[ms]
     */
    public int getConnectionTimeoutMillis();
    
    /**
     * �����҂��̃^�C���A�E�g[ms]��ݒ肷��B<p>
     * �f�t�H���g�́A1�b�B<br>
     *
     * @param milisec �^�C���A�E�g[ms]
     */
    public void setTimeoutMillis(int milisec);
    
    /**
     * �����҂��̃^�C���A�E�g[ms]���擾����B<p>
     *
     * @return �^�C���A�E�g[ms]
     */
    public int getTimeoutMillis();
    
    /**
     * EOF�����m�����ꍇ�ɏo�͂��郍�O�̃��b�Z�[�WID��ݒ肷��B<p>
     *
     * @param id ���O�̃��b�Z�[�WID
     */
    public void setEOFLogMessageId(String id);
    
    /**
     * EOF�����m�����ꍇ�ɏo�͂��郍�O�̃��b�Z�[�WID���擾����B<p>
     *
     * @return ���O�̃��b�Z�[�WID
     */
    public String getEOFLogMessageId();
    
    /**
     * �T�[�o����G���[��Ԃ���M�����ꍇ�ɏo�͂��郍�O�̃��b�Z�[�WID��ݒ肷��B<p>
     *
     * @param id ���O�̃��b�Z�[�WID
     */
    public void setErrorStateLogMessageId(String id);
    
    /**
     * �T�[�o����G���[��Ԃ���M�����ꍇ�ɏo�͂��郍�O�̃��b�Z�[�WID���擾����B<p>
     *
     * @return ���O�̃��b�Z�[�WID
     */
    public String getErrorStateLogMessageId();
    
    /**
     * �T�[�o���琳���Ԃ���M�����ꍇ�ɏo�͂��郍�O�̃��b�Z�[�WID��ݒ肷��B<p>
     *
     * @param id ���O�̃��b�Z�[�WID
     */
    public void setNormalStateLogMessageId(String id);
    
    /**
     * �T�[�o���琳���Ԃ���M�����ꍇ�ɏo�͂��郍�O�̃��b�Z�[�WID���擾����B<p>
     *
     * @return ���O�̃��b�Z�[�WID
     */
    public String getNormalStateLogMessageId();
    
    /**
     * �T�[�o����̉����҂��Ń^�C���A�E�g�����ꍇ�ɏo�͂��郍�O�̃��b�Z�[�WID��ݒ肷��B<p>
     *
     * @param id ���O�̃��b�Z�[�WID
     */
    public void setTimeoutLogMessageId(String id);
    
    /**
     * �T�[�o����̉����҂��Ń^�C���A�E�g�����ꍇ�ɏo�͂��郍�O�̃��b�Z�[�WID���擾����B<p>
     *
     * @return ���O�̃��b�Z�[�WID
     */
    public String getTimeoutLogMessageId();
    
    /**
     * TCP���x���ł̃v���g�R���G���[�����������ꍇ�ɏo�͂��郍�O�̃��b�Z�[�WID��ݒ肷��B<p>
     *
     * @param id ���O�̃��b�Z�[�WID
     */
    public void setProtocolErrorLogMessageId(String id);
    
    /**
     * TCP���x���ł̃v���g�R���G���[�����������ꍇ�ɏo�͂��郍�O�̃��b�Z�[�WID���擾����B<p>
     *
     * @return ���O�̃��b�Z�[�WID
     */
    public String getProtocolErrorLogMessageId();
    
    /**
     * ���o�̓G���[�����������ꍇ�ɏo�͂��郍�O�̃��b�Z�[�WID��ݒ肷��B<p>
     *
     * @param id ���O�̃��b�Z�[�WID
     */
    public void setIOErrorLogMessageId(String id);
    
    /**
     * ���o�̓G���[�����������ꍇ�ɏo�͂��郍�O�̃��b�Z�[�WID���擾����B<p>
     *
     * @return ���O�̃��b�Z�[�WID
     */
    public String getIOErrorLogMessageId();
    
    /**
     * EOF�����m�����ꍇ�Ƀ��O���o�͂��邩�ǂ�����ݒ肷��B<p>
     * �f�t�H���g�ł́Afalse�B<br>
     *
     * @param isOutput �o�͂���ꍇ�́Atrue
     */
    public void setOutputEOFLogMessage(boolean isOutput);
    
    /**
     * EOF�����m�����ꍇ�Ƀ��O���o�͂��邩�ǂ����𔻒肷��B<p>
     *
     * @return true�̏ꍇ�́A�o�͂���
     */
    public boolean isOutputEOFLogMessage();
    
    /**
     * �T�[�o����G���[��Ԃ���M�����ꍇ�Ƀ��O���o�͂��邩�ǂ�����ݒ肷��B<p>
     * �f�t�H���g�ł́Afalse�B<br>
     *
     * @param isOutput �o�͂���ꍇ�́Atrue
     */
    public void setOutputErrorStateLogMessage(boolean isOutput);
    
    /**
     * �T�[�o����G���[��Ԃ���M�����ꍇ�Ƀ��O���o�͂��邩�ǂ����𔻒肷��B<p>
     *
     * @return true�̏ꍇ�́A�o�͂���
     */
    public boolean isOutputErrorStateLogMessage();
    
    /**
     * �T�[�o���琳���Ԃ���M�����ꍇ�Ƀ��O���o�͂��邩�ǂ�����ݒ肷��B<p>
     * �f�t�H���g�ł́Afalse�B<br>
     *
     * @param isOutput �o�͂���ꍇ�́Atrue
     */
    public void setOutputNormalStateLogMessage(boolean isOutput);
    
    /**
     * �T�[�o���琳���Ԃ���M�����ꍇ�Ƀ��O���o�͂��邩�ǂ����𔻒肷��B<p>
     *
     * @return true�̏ꍇ�́A�o�͂���
     */
    public boolean isOutputNormalStateLogMessage();
    
    /**
     * �T�[�o����̉����҂��Ń^�C���A�E�g�����ꍇ�Ƀ��O���o�͂��邩�ǂ�����ݒ肷��B<p>
     * �f�t�H���g�ł́Afalse�B<br>
     *
     * @param isOutput �o�͂���ꍇ�́Atrue
     */
    public void setOutputTimeoutLogMessage(boolean isOutput);
    
    /**
     * �T�[�o����̉����҂��Ń^�C���A�E�g�����ꍇ�Ƀ��O���o�͂��邩�ǂ����𔻒肷��B<p>
     *
     * @return true�̏ꍇ�́A�o�͂���
     */
    public boolean isOutputTimeoutLogMessage();
    
    /**
     * TCP���x���ł̃v���g�R���G���[�����������ꍇ�Ƀ��O���o�͂��邩�ǂ�����ݒ肷��B<p>
     * �f�t�H���g�ł́Afalse�B<br>
     *
     * @param isOutput �o�͂���ꍇ�́Atrue
     */
    public void setOutputProtocolErrorLogMessage(boolean isOutput);
    
    /**
     * TCP���x���ł̃v���g�R���G���[�����������ꍇ�Ƀ��O���o�͂��邩�ǂ����𔻒肷��B<p>
     *
     * @return true�̏ꍇ�́A�o�͂���
     */
    public boolean isOutputProtocolErrorLogMessage();
    
    /**
     * ���o�̓G���[�����������ꍇ�Ƀ��O���o�͂��邩�ǂ�����ݒ肷��B<p>
     * �f�t�H���g�ł́Afalse�B<br>
     *
     * @param isOutput �o�͂���ꍇ�́Atrue
     */
    public void setOutputIOErrorLogMessage(boolean isOutput);
    
    /**
     * ���o�̓G���[�����������ꍇ�Ƀ��O���o�͂��邩�ǂ����𔻒肷��B<p>
     *
     * @return true�̏ꍇ�́A�o�͂���
     */
    public boolean isOutputIOErrorLogMessage();
    
    /**
     * SMTP�T�[�o�̐����`�F�b�N���s�����ǂ�����ݒ肷��B<p>
     * true�ɐݒ肳�ꂽ�ꍇ�A{@link #setAliveCheckSMTPServerInterval(long)}�Őݒ肳�ꂽ�Ԋu�ŁA"HELO"���b�Z�[�W�𑗐M���āASMTP�T�[�o�̐����`�F�b�N���s���B<br>
     * "HELO"���b�Z�[�W�̉���������łȂ��ꍇ�A�G���[���O���o�͂���B�܂��A���퉞���ł���悤�ɂȂ����ꍇ�A�ʒm���O���o�͂���B<br>
     *
     * @param isCheck �����`�F�b�N���s���ꍇ��true
     */
    public void setAliveCheckSMTPServer(boolean isCheck);
    
    /**
     * SMTP�T�[�o�̐����`�F�b�N���s�����ǂ�����ݒ肷��B<p>
     *
     * @return �����`�F�b�N���s���ꍇ��true
     */
    public boolean isAliveCheckSMTPServer();
    
    /**
     * SMTP�T�[�o�̐����`�F�b�N���s���Ԋu[msec]��ݒ肷��B<p>
     * �f�t�H���g�́A60000[msec]�B
     * 
     * @param interval SMTP�T�[�o�̐����`�F�b�N���s���Ԋu[msec]
     */
    public void setAliveCheckSMTPServerInterval(long interval);
    
    /**
     * SMTP�T�[�o�̐����`�F�b�N���s���Ԋu[msec]���擾����B<p>
     * 
     * @return SMTP�T�[�o�̐����`�F�b�N���s���Ԋu[msec]
     */
    public long getAliveCheckSMTPServerInterval();
    
    /**
     * SMTP�T�[�o���������Ă��邩�ǂ����𒲂ׂ�B<p>
     * {@link #isAliveCheckSMTPServer()}��true��Ԃ��ꍇ�́A�Ō�Ƀ`�F�b�N�������̏�Ԃ�Ԃ��B<br>
     * isAliveCheckSMTPServer()��false��Ԃ��ꍇ�́A�����Ƀ`�F�b�N���Č��ʂ�Ԃ��B�A���A�T�[�r�X���J�n���Ă��Ȃ��ꍇ�́Afalse��Ԃ��B<br>
     * 
     * @return SMTP�T�[�o���������Ă���ꍇtrue
     */
    public boolean isAliveSMTPServer();
    
    /**
     * SMTP�T�[�o���_�E�������������m�����|�̃��O���o�͂��邩�ǂ�����ݒ肷��B<p>
     * �f�t�H���g�́Atrue�B<br>
     *
     * @param isOutput ���O���o�͂���ꍇtrue
     */
    public void setLoggingDeadSMTPServer(boolean isOutput);
    
    /**
     * SMTP�T�[�o���_�E�������������m�����|�̃��O���o�͂��邩�ǂ����𔻒肷��B<p>
     *
     * @return ���O���o�͂���ꍇtrue
     */
    public boolean isLoggingDeadSMTPServer();
    
    /**
     * SMTP�T�[�o�����A�����������m�����|�̃��O���o�͂��邩�ǂ�����ݒ肷��B<p>
     * �f�t�H���g�́Atrue�B<br>
     *
     * @param isOutput ���O���o�͂���ꍇtrue
     */
    public void setLoggingRecoverSMTPServer(boolean isOutput);
    
    /**
     * SMTP�T�[�o�����A�����������m�����|�̃��O���o�͂��邩�ǂ����𔻒肷��B<p>
     *
     * @return ���O���o�͂���ꍇtrue
     */
    public boolean isLoggingRecoverSMTPServer();
    
    /**
     * SMTP�T�[�o���_�E�������������m�����|�̃��O�o�͂̃��b�Z�[�WID��ݒ肷��B<p>
     * �f�t�H���g�́A{@link #SMTP_SERVER_DEAD_MSG_ID}�B<br>
     *
     * @param id ���O�o�͂̃��b�Z�[�WID
     */
    public void setDeadSMTPServerLogMessageId(String id);
    
    /**
     * SMTP�T�[�o���_�E�������������m�����|�̃��O�o�͂̃��b�Z�[�WID���擾����B<p>
     *
     * @return ���O�o�͂̃��b�Z�[�WID
     */
    public String getDeadSMTPServerLogMessageId();
    
    /**
     * SMTP�T�[�o�����A�����������m�����|�̃��O�o�͂̃��b�Z�[�WID��ݒ肷��B<p>
     * �f�t�H���g�́A{@link #SMTP_SERVER_RECOVER_MSG_ID}�B<br>
     *
     * @param id ���O�o�͂̃��b�Z�[�WID
     */
    public void setRecoverSMTPServerLogMessageId(String id);
    
    /**
     * SMTP�T�[�o�����A�����������m�����|�̃��O�o�͂̃��b�Z�[�WID���擾����B<p>
     *
     * @return ���O�o�͂̃��b�Z�[�WID
     */
    public String getRecoverSMTPServerLogMessageId();
}
