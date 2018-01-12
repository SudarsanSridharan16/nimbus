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

import java.util.List;

import jp.ossc.nimbus.core.*;

/**
 * {@link AbstractKeepAliveCheckerSelectorService}��MBean�C���^�t�F�[�X<p>
 * 
 * @author M.Takata
 * @see AbstractKeepAliveCheckerSelectorService
 */
public interface AbstractKeepAliveCheckerSelectorServiceMBean
 extends ServiceBaseMBean{
    
    public static final String DEFAULT_ALIVE_LOG_MSG_ID = "KACS_00001";
    public static final String DEFAULT_DEAD_LOG_MSG_ID = "KACS_00002";
    
    /**
     * �I���\��{@link KeepAliveChecker}�T�[�r�X�̃T�[�r�X���z���ݒ肷��B<p>
     *
     * @param names �I���\��{@link KeepAliveChecker}�T�[�r�X�̃T�[�r�X���z��
     */
    public void setSelectableCheckerServiceNames(ServiceName[] names);
    
    /**
     * �I���\��{@link KeepAliveChecker}�T�[�r�X�̃T�[�r�X���z����擾����B<p>
     *
     * @return �I���\��{@link KeepAliveChecker}�T�[�r�X�̃T�[�r�X���z��
     */
    public ServiceName[] getSelectableCheckerServiceNames();
    
    /**
     * ����I��{@link KeepAliveChecker}�ɐ����m�F���s���Ԋu[ms]��ݒ肷��B<p>
     * ���̑�����ݒ肵�Ȃ��ꍇ�́A����₢���킹���s���B�A���A{@link #setClusterServiceName(ServiceName)}���w�肵�Ă���ꍇ�́A�����B<br>
     * �f�t�H���g�ł́A����₢���킹���s���B<br>
     *
     * @param millis KeepAliveChecker�ɐ����m�F���s���Ԋu
     */
    public void setCheckInterval(long millis);
    
    /**
     * ����I��{@link KeepAliveChecker}�ɐ����m�F���s���Ԋu[ms]���擾����B<p>
     *
     * @return KeepAliveChecker�ɐ����m�F���s���Ԋu
     */
    public long getCheckInterval();
    
    /**
     * {@link KeepAliveChecker}�̉ғ���Ԃ���~��Ԃ��瑖�s��Ԃɕω��������ɏo�͂��郍�O�̃��b�Z�[�WID��ݒ肷��B<p>
     *
     * @param id ���O�̃��b�Z�[�WID
     */
    public void setAliveLogMessageId(String id);
    
    /**
     * {@link KeepAliveChecker}�̉ғ���Ԃ���~��Ԃ��瑖�s��Ԃɕω��������ɏo�͂��郍�O�̃��b�Z�[�WID���擾����B<p>
     *
     * @return ���O�̃��b�Z�[�WID
     */
    public String getAliveLogMessageId();
    
    /**
     * {@link KeepAliveChecker}�̉ғ���Ԃ����s��Ԃ����~��Ԃɕω��������ɏo�͂��郍�O�̃��b�Z�[�WID��ݒ肷��B<p>
     *
     * @param id ���O�̃��b�Z�[�WID
     */
    public void setDeadLogMessageId(String id);
    
    /**
     * {@link KeepAliveChecker}�̉ғ���Ԃ����s��Ԃ����~��Ԃɕω��������ɏo�͂��郍�O�̃��b�Z�[�WID���擾����B<p>
     *
     * @return ���O�̃��b�Z�[�WID
     */
    public String getDeadLogMessageId();
    
    /**
     * {@link KeepAliveChecker}�̉ғ���Ԃ���~��Ԃ��瑖�s��Ԃɕω��������Ƀ��O���o�͂��邩�ǂ�����ݒ肷��B<p>
     * �f�t�H���g�́Atrue�B<br>
     *
     * @param isOutput �o�͂���ꍇ�́Atrue
     */
    public void setOutputAliveLogMessage(boolean isOutput);
    
    /**
     * {@link KeepAliveChecker}�̉ғ���Ԃ���~��Ԃ��瑖�s��Ԃɕω��������Ƀ��O���o�͂��邩�ǂ����𔻒肷��B<p>
     *
     * @return true�̏ꍇ�́A�o�͂���
     */
    public boolean isOutputAliveLogMessage();
    
    /**
     * {@link KeepAliveChecker}�̉ғ���Ԃ����s��Ԃ����~��Ԃɕω��������Ƀ��O���o�͂��邩�ǂ�����ݒ肷��B<p>
     * �f�t�H���g�́Atrue�B<br>
     *
     * @param isOutput �o�͂���ꍇ�́Atrue
     */
    public void setOutputDeadLogMessage(boolean isOutput);
    
    /**
     * {@link KeepAliveChecker}�̉ғ���Ԃ����s��Ԃ����~��Ԃɕω��������Ƀ��O���o�͂��邩�ǂ����𔻒肷��B<p>
     *
     * @return true�̏ꍇ�́A�o�͂���
     */
    public boolean isOutputDeadLogMessage();
    
    /**
     * {@link KeepAliveCheckerSelector#getSelectableCheckers()}�ŕԂ����{@link KeepAliveChecker}�̏�����ۂ��ǂ�����ݒ肷��B<p>
     * �f�t�H���g�́Afalse�ŁA������ۂ��Ȃ��B�ғ�����~���ғ��ƂȂ����ꍇ�A��ԍŌ�ɕ��ׂ���B<br>
     * 
     * @param isKeep ������ۂ��������ꍇ�́Atrue
     */
    public void setKeepOrder(boolean isKeep);
    
    /**
     * {@link KeepAliveCheckerSelector#getSelectableCheckers()}�ŕԂ����{@link KeepAliveChecker}�̏�����ۂ��ǂ����𔻒肷��B<p>
     * 
     * @return true�̏ꍇ�́A������ۂ�
     */
    public boolean isKeepOrder();
    
    /**
     * {@link ClusterService �N���X�^}�T�[�r�X�̃T�[�r�X����ݒ肷��B<p>
     * ���̑�����ݒ肵���ꍇ�́A{@link KeepAliveChecker}���N���X�^�T�[�r�X�̃����o�[��񂩂�擾����B<br>
     *
     * @param name �N���X�^�T�[�r�X�̃T�[�r�X��
     */
    public void setClusterServiceName(ServiceName name);
    
    /**
     * {@link ClusterService �N���X�^}�T�[�r�X�̃T�[�r�X�����擾����B<p>
     *
     * @return �N���X�^�T�[�r�X�̃T�[�r�X��
     */
    public ServiceName getClusterServiceName();
    
    /**
     * {@link ClusterService �N���X�^}�T�[�r�X�̃����o�[���̃I�v�V�����L�[��ݒ肷��B<p>
     *
     * @param key �I�v�V�����L�[
     */
    public void setClusterOptionKey(String key);
    
    /**
     * {@link ClusterService �N���X�^}�T�[�r�X�̃����o�[���̃I�v�V�����L�[���擾����B<p>
     *
     * @return �I�v�V�����L�[
     */
    public String getClusterOptionKey();
    
    /**
     * ���ݑI���\�Ȑ������Ă���{@link KeepAliveChecker}�T�[�r�X�̃T�[�r�X�����X�g���擾����B<br>
     *
     * @return KeepAliveChecker�T�[�r�X�̃T�[�r�X�����X�g
     */
    public List getAliveCheckers();
}
