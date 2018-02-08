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
package jp.ossc.nimbus.service.test.action;

import jp.ossc.nimbus.core.ServiceBaseMBean;
import jp.ossc.nimbus.core.ServiceName;

/**
 * {@link DatabaseUpdateActionService}��MBean�C���^�t�F�[�X<p>
 * 
 * @author M.Takata
 * @see DatabaseUpdateActionService
 */
public interface DatabaseUpdateActionServiceMBean extends ServiceBaseMBean{
    
    /**
     * {@link jp.ossc.nimbus.service.connection.ConnectionFactory ConnectionFactory}�T�[�r�X�̃T�[�r�X����ݒ肷��B<p>
     *
     * @param name ConnectionFactory�T�[�r�X�̃T�[�r�X��
     */
    public void setConnectionFactoryServiceName(ServiceName name);
    
    /**
     * {@link jp.ossc.nimbus.service.connection.ConnectionFactory ConnectionFactory}�T�[�r�X�̃T�[�r�X�����擾����B<p>
     *
     * @return ConnectionFactory�T�[�r�X�̃T�[�r�X��
     */
    public ServiceName getConnectionFactoryServiceName();
    
    /**
     * {@link jp.ossc.nimbus.service.connection.PersistentManager PersistentManager}�T�[�r�X�̃T�[�r�X����ݒ肷��B<p>
     *
     * @param name PersistentManager�T�[�r�X�̃T�[�r�X��
     */
    public void setPersistentManagerServiceName(ServiceName name);
    
    /**
     * {@link jp.ossc.nimbus.service.connection.PersistentManager PersistentManager}�T�[�r�X�̃T�[�r�X�����擾����B<p>
     *
     * @return PersistentManager�T�[�r�X�̃T�[�r�X��
     */
    public ServiceName getPersistentManagerServiceName();
    
    /**
     * �o�b�`���s���s�����ǂ�����ݒ肷��B<p>
     * �f�t�H���g��false�ŁA�o�b�`���s���Ȃ��B<br>
     *
     * @param isBatch �o�b�`���s���s���ꍇ�́Atrue
     */
    public void setBatchExecute(boolean isBatch);
    
    /**
     * �o�b�`���s���s�����ǂ����𔻒肷��B<p>
     *
     * @return true�̏ꍇ�́A�o�b�`���s���s��
     */
    public boolean isBatchExecute();
    
    /**
     * �o�b�`���s���s���ꍇ�̃o�b�`���s������ݒ肷��B<p>
     *
     * @param count �o�b�`���s����
     */
    public void setBatchExecuteCount(int count);
    
    /**
     * �o�b�`���s���s���ꍇ�̃o�b�`���s�������擾����B<p>
     *
     * @return �o�b�`���s����
     */
    public int getBatchExecuteCount();
    
    /**
     * ���̃A�N�V�����̃��\�[�X��`���쐬����ۂ̃f�t�H���g�̑z��R�X�g��ݒ肷��B<p>
     * 
     * @param cost �z��R�X�g
     */
    public void setExpectedCost(double cost);
    
    /**
     * ���̃A�N�V�����̃��\�[�X��`���쐬����ۂ̃f�t�H���g�̑z��R�X�g���擾����B<p>
     * 
     * @return �z��R�X�g
     */
    public double getExpectedCost();
}