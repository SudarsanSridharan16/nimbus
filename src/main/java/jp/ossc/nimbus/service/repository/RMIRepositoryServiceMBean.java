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
package jp.ossc.nimbus.service.repository;

import jp.ossc.nimbus.core.*;

/**
 * {@link RMIRepositoryService}��MBean�C���^�t�F�[�X�B<p>
 *
 * @author M.Takata
 */
public interface RMIRepositoryServiceMBean
 extends ServiceBaseMBean, Repository{
    
    /**
     * RMI�T�[�o�̃z�X�g����ݒ肷��B<p>
     * �w�肵�Ȃ��ꍇ�́A���[�J���z�X�g�B<br>
     *
     * @param host �z�X�g��
     */
    public void setHostName(String host);
    
    /**
     * RMI�T�[�o�̃z�X�g�����擾����B<p>
     *
     * @return �z�X�g��
     */
    public String getHostName();
    
    /**
     * RMI�T�[�o�̃|�[�g�ԍ���ݒ肷��B<p>
     * �w�肵�Ȃ��ꍇ�́A1099�B<br>
     *
     * @param port �|�[�g�ԍ�
     */
    public void setPort(int port);
    
    /**
     * RMI�T�[�o�̃|�[�g�ԍ����擾����B<p>
     *
     * @return �|�[�g�ԍ�
     */
    public int getPort();
    
    /**
     * ���[�J����RMI�T�[�o���쐬���邩�ǂ����𔻒肷��B<p>
     *
     * @return true�̏ꍇ�A�쐬����
     */
    public boolean isCreateRegistry();
    
    /**
     * ���[�J����RMI�T�[�o���쐬���邩�ǂ�����ݒ肷��B<p>
     * �f�t�H���g�́Afalse�ō쐬���Ȃ��B<br>
     *
     * @param isCreate �쐬����ꍇ�Atrue
     */
    public void setCreateRegistry(boolean isCreate);
}