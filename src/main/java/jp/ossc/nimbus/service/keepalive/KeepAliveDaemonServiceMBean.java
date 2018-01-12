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
package jp.ossc.nimbus.service.keepalive;

import jp.ossc.nimbus.core.*;

/**
 * {@link KeepAliveDaemonService}��MBean�C���^�t�F�[�X�B<p>
 *
 * @author H.Nakano
 * @version  1.00 �쐬: 2003/10/08 - H.Nakano
 */
public interface KeepAliveDaemonServiceMBean
 extends ServiceBaseMBean, QueryKeepAlive{
    
    /**
     * �`�F�b�N�ΏۂƂȂ�{@link KeepAliveChecker}�T�[�r�X���z���ݒ肷��B<p>
     *
     * @param serviceNames �`�F�b�N�ΏۂƂȂ�KeepAliveChecker�T�[�r�X���z��
     */
    public void setChekerServiceNames(ServiceName[] serviceNames);
    
    /**
     * �`�F�b�N�ΏۂƂȂ�{@link KeepAliveChecker}�T�[�r�X���z����擾����B<p>
     *
     * @return �`�F�b�N�ΏۂƂȂ�KeepAliveChecker�T�[�r�X���z��
     */
    public ServiceName[] getChekerServiceNames();
    
    /**
     * �Ď��C���^�[�o��[ms]��ݒ肷��B<p>
     *
     * @param miliseconds �Ď��C���^�[�o��[ms]
     */
    public void setIntervalTimeMillis(long miliseconds);
    
    /**
     * �Ď��C���^�[�o��[ms]���擾����B<p>
     *
     * @return �Ď��C���^�[�o��[ms]
     */
    public long getIntervalTimeMillis();
    
    /**
     * �X�e�[�^�X�����o�͂���B<p>
     *
     * @return �X�e�[�^�X���
     */
    public String[] getStatusString();
    
    /**
     * �T�[�o�̉ғ���Ԃ���~��Ԃ��瑖�s��Ԃɕω��������ɏo�͂��郍�O�̃��b�Z�[�WID��ݒ肷��B<p>
     *
     * @param id ���O�̃��b�Z�[�WID
     */
    public void setAliveLogMessageId(String id);
    
    /**
     * �T�[�o�̉ғ���Ԃ���~��Ԃ��瑖�s��Ԃɕω��������ɏo�͂��郍�O�̃��b�Z�[�WID���擾����B<p>
     *
     * @return ���O�̃��b�Z�[�WID
     */
    public String getAliveLogMessageId();
    
    /**
     * �T�[�o�̉ғ���Ԃ����s��Ԃ����~��Ԃɕω��������ɏo�͂��郍�O�̃��b�Z�[�WID��ݒ肷��B<p>
     *
     * @param id ���O�̃��b�Z�[�WID
     */
    public void setDeadLogMessageId(String id);
    
    /**
     * �T�[�o�̉ғ���Ԃ����s��Ԃ����~��Ԃɕω��������ɏo�͂��郍�O�̃��b�Z�[�WID���擾����B<p>
     *
     * @return ���O�̃��b�Z�[�WID
     */
    public String getDeadLogMessageId();
    
    /**
     * �T�[�o�̉ғ���Ԃ���~��Ԃ��瑖�s��Ԃɕω��������Ƀ��O���o�͂��邩�ǂ�����ݒ肷��B<p>
     * �f�t�H���g�́Atrue�B<br>
     *
     * @param isOutput �o�͂���ꍇ�́Atrue
     */
    public void setOutputAliveLogMessage(boolean isOutput);
    
    /**
     * �T�[�o�̉ғ���Ԃ���~��Ԃ��瑖�s��Ԃɕω��������Ƀ��O���o�͂��邩�ǂ����𔻒肷��B<p>
     *
     * @return true�̏ꍇ�́A�o�͂���
     */
    public boolean isOutputAliveLogMessage();
    
    /**
     * �T�[�o�̉ғ���Ԃ����s��Ԃ����~��Ԃɕω��������Ƀ��O���o�͂��邩�ǂ�����ݒ肷��B<p>
     * �f�t�H���g�́Atrue�B<br>
     *
     * @param isOutput �o�͂���ꍇ�́Atrue
     */
    public void setOutputDeadLogMessage(boolean isOutput);
    
    /**
     * �T�[�o�̉ғ���Ԃ����s��Ԃ����~��Ԃɕω��������Ƀ��O���o�͂��邩�ǂ����𔻒肷��B<p>
     *
     * @return true�̏ꍇ�́A�o�͂���
     */
    public boolean isOutputDeadLogMessage();
}
