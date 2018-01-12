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
package jp.ossc.nimbus.service.context;

import jp.ossc.nimbus.core.*;

/**
 * {@link GroupContextService}��MBean�C���^�t�F�[�X�B<p>
 * 
 * @author M.Takata
 * @see GroupContextService
 */
public interface GroupContextServiceMBean extends ServiceBaseMBean, Context {
    
    /**
     * �O���[�s���O����R���e�L�X�g�T�[�r�X�̃T�[�r�X����ݒ肷��B<p>
     *
     * @param names �O���[�s���O����R���e�L�X�g�T�[�r�X�̃T�[�r�X���z��
     */
    public void setContextServiceNames(ServiceName[] names);
    
    /**
     * �O���[�s���O����R���e�L�X�g�T�[�r�X�̃T�[�r�X�����擾����B<p>
     *
     * @return �O���[�s���O����R���e�L�X�g�T�[�r�X�̃T�[�r�X���z��
     */
    public ServiceName[] getContextServiceNames();
    
    /**
     * �w�肳�ꂽ�L�[�Ɋ֘A�t����ꂽ�R���e�L�X�g�����擾����B<p>
     *
     * @param key �L�[
     * @return �L�[�Ɋ֘A�t����ꂽ�R���e�L�X�g���B�Y������R���e�L�X�g��񂪂Ȃ��ꍇ�́Anull
     */
    public Object get(String key);
    
    /**
     * �w�肳�ꂽ�L�[�Ɋ֘A�t����ꂽ�R���e�L�X�g�����폜����B<p>
     *
     * @param key �L�[
     * @return �폜���ꂽ�R���e�L�X�g���B�폜����R���e�L�X�g��񂪂Ȃ��ꍇ�́Anull
     */
    public Object remove(String key);
    
    /**
     * �u���O(key��toString()) : �l(value��toString()) ���s�v�Ƃ����`���Ń��X�g�o�͂���B<p>
     *
     * @return ���X�g������
     */
    public String list();
}
