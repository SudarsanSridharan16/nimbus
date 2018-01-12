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
package jp.ossc.nimbus.service.beancontrol;

import jp.ossc.nimbus.core.*;

/**
 * {@link ConsoleBeanFlowCoverageRepoterService}��MBean�C���^�t�F�[�X�B<p>
 * 
 * @author M.Takata
 */
public interface ConsoleBeanFlowCoverageRepoterServiceMBean
 extends ServiceBaseMBean{
    
    /**
     * {@link jp.ossc.nimbus.service.beancontrol.interfaces.BeanFlowInvokerFactory BeanFlowInvokerFactory}�̃T�[�r�X�����擾����B<p>
     *
     * @return BeanFlowInvokerFactory�̃T�[�r�X��
     */
    public ServiceName getBeanFlowInvokerFactoryServiceName();
    
    /**
     * {@link jp.ossc.nimbus.service.beancontrol.interfaces.BeanFlowInvokerFactory BeanFlowInvokerFactory}�̃T�[�r�X����ݒ肷��B<p>
     *
     * @param name BeanFlowInvokerFactory�̃T�[�r�X��
     */
    public void setBeanFlowInvokerFactoryServiceName(ServiceName name);
    
    /**
     * �T�[�r�X�̒�~���Ƀ��|�[�g���o�͂��邩�ǂ�����ݒ肷��B<p>
     * �f�t�H���g�́Afalse�ŏo�͂��Ȃ��B<br>
     *
     * @param isReport �o�͂���ꍇ�Atrue
     */
    public void setReportOnStop(boolean isReport);
    
    /**
     * �T�[�r�X�̒�~���Ƀ��|�[�g���o�͂��邩�ǂ����𔻒肷��B<p>
     *
     * @return true�̏ꍇ�A�o�͂���
     */
    public boolean isReportOnStop();
    
    /**
     * �ڍׂ����|�[�g�ɏo�͂��邩�ǂ�����ݒ肷��B<p>
     * �f�t�H���g�́Afalse�ŏo�͂��Ȃ��B<br>
     *
     * @param isDetail �o�͂���ꍇ�Atrue
     */
    public void setDetail(boolean isDetail);
    
    /**
     * �ڍׂ����|�[�g�ɏo�͂��邩�ǂ����𔻒肷��B<p>
     *
     * @return true�̏ꍇ�A�o�͂���
     */
    public boolean isDetail();
    
    /**
     * ���|�[�g���擾����B<p>
     *
     * @return ���|�[�g
     */
    public String displayReport();
    
    /**
     * ���|�[�g���o�͂���B<p>
     *
     * @exception Exception �o�͂Ɏ��s�����ꍇ
     */
    public void report() throws Exception;
}
