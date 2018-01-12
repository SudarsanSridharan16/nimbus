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
package jp.ossc.nimbus.service.test;

import jp.ossc.nimbus.core.ServiceBaseMBean;
import jp.ossc.nimbus.core.ServiceName;
import jp.ossc.nimbus.service.test.ChainTestAction.TestActionProcess;
import jp.ossc.nimbus.service.test.ChainEvaluateTestAction.EvaluateTestActionProcess;

/**
 * {@link ChainEvaluateTestActionService}��MBean�C���^�t�F�[�X<p>
 * 
 * @author M.Ishida
 * @see ChainEvaluateTestActionService
 */
public interface ChainEvaluateTestActionServiceMBean extends ServiceBaseMBean {
    
    /**
     * �A��������e�X�g�A�N�V�����T�[�r�X�̃T�[�r�X���̔z����擾����B<p>
     *
     * @return �e�X�g�A�N�V�����T�[�r�X�̃T�[�r�X���̔z��
     */
    public ServiceName[] getActionServiceNames();
    
    /**
     * �A��������e�X�g�A�N�V�����T�[�r�X�̃T�[�r�X���̔z���ݒ肷��B<p>
     * �A���\�ȃe�X�g�A�N�V�����́A�ȉ��B<br>
     * <ul>
     *     <li>{@link TestAction}</li>
     *     <li>{@link EvaluateTestAction}</li>
     *     <li>{@link TestActionProcess}</li>
     *     <li>{@link EvaluateTestActionProcess}</li>
     * </ul>
     *
     * @param names �e�X�g�A�N�V�����T�[�r�X�̃T�[�r�X���̔z��
     */
    public void setActionServiceNames(ServiceName[] names);
    
    /**
     * �A���̍Ō�̕]���e�X�g�A�N�V�����T�[�r�X�̃T�[�r�X�����擾����B<p>
     *
     * @return �]���e�X�g�A�N�V�����T�[�r�X�̃T�[�r�X��
     */
    public ServiceName getEndEvaluateTestActionServiceName();
    
    /**
     * �A���̍Ō�̕]���e�X�g�A�N�V�����T�[�r�X�̃T�[�r�X����ݒ肷��B<p>
     * �ݒ�\�ȕ]���e�X�g�A�N�V�����́A�ȉ��B<br>
     * <ul>
     *     <li>{@link EvaluateTestAction}</li>
     *     <li>{@link EvaluateTestActionProcess}</li>
     * </ul>
     *
     * @param name �]���e�X�g�A�N�V�����T�[�r�X�̃T�[�r�X��
     */
    public void setEndEvaluateTestActionServiceName(ServiceName name);
    
    /**
     * ���̃A�N�V�����̃��\�[�X��`���쐬����ۂ̃f�t�H���g�̑z��R�X�g���擾����B<p>
     * 
     * @return �A�����ꂽ�e�X�g�A�N�V�����̑z��R�X�g�̑��a
     */
    public double getExpectedCost();
}
