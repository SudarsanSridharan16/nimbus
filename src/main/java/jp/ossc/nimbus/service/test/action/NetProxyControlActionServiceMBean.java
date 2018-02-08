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
 * {@link NetProxyControlActionService}��MBean�C���^�t�F�[�X
 * <p>
 *
 * @author M.Ishida
 * @see NetProxyControlActionService
 */
public interface NetProxyControlActionServiceMBean extends ServiceBaseMBean {

    /**
     * NetProxy��open���邽�߂̒萔
     */
    public static final String OPERATION_OPEN = "open";

    /**
     * NetProxy��close���邽�߂̒萔
     */
    public static final String OPERATION_CLOSE = "close";

    /**
     * NetProxy��reopen���邽�߂̒萔
     */
    public static final String OPERATION_REOPEN = "reopen";

    /**
     * NetProxy��freeze���邽�߂̒萔
     */
    public static final String OPERATION_FREEZE = "freeze";

    /**
     * NetProxy��unfreeze���邽�߂̒萔
     */
    public static final String OPERATION_UNFREEZE = "unfreeze";

    /**
     * {@link jp.ossc.nimbus.service.test.proxy.NetProxy NetProxy}
     * �T�[�r�X�̃T�[�r�X����ݒ肷��B
     * <p>
     *
     * @param name NetProxy�T�[�r�X�̃T�[�r�X��
     */
    public void setNetProxyServiceName(ServiceName name);

    /**
     * {@link jp.ossc.nimbus.service.test.proxy.NetProxy NetProxy}
     * �T�[�r�X�̃T�[�r�X�����擾����B
     * <p>
     *
     * @return NetProxy�T�[�r�X�̃T�[�r�X��
     */
    public ServiceName getNetProxyServiceName();

    /**
     * ���̃A�N�V�����̃��\�[�X��`���쐬����ۂ̃f�t�H���g�̑z��R�X�g��ݒ肷��B
     * <p>
     *
     * @param cost �z��R�X�g
     */
    public void setExpectedCost(double cost);

    /**
     * ���̃A�N�V�����̃��\�[�X��`���쐬����ۂ̃f�t�H���g�̑z��R�X�g���擾����B
     * <p>
     *
     * @return �z��R�X�g
     */
    public double getExpectedCost();
}