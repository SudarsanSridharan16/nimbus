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
// �p�b�P�[�W
// �C���|�[�g
package jp.ossc.nimbus.service.codemaster;

import java.util.Date;

import jp.ossc.nimbus.core.ServiceBaseMBean;
import jp.ossc.nimbus.core.ServiceName;

/**
 * ��Q�Ƃɂ��L���b�V���@�\���R�[�h�}�X�^�[�T�[�r�X<p>
 * ��Q�Ƃ��g�p�����R�[�h�}�X�^�񋟂��s��
 * @version $Name:  $
 * @author K.Nagai
 * @since 1.0
 */
public interface WeakReferenceCodeMasterServiceMBean extends ServiceBaseMBean {
    /**
     * �R�[�h�}�X�^�[�̖��O��ݒ肷��B
     * @param names �R�[�h�}�X�^���z��
     */
    public void setMasterNames(String[] names) ;
    /**
     * �R�[�h�}�X�^�[�̖��O���擾�B
     * @return �R�[�h�}�X�^���z��
     */
    public String[] getMasterNames() ;
    /**
     * BeanFlowInvoker�t�@�N�g�����ݒ�
     * @param name BeanFlowInvokerFactory�T�[�r�X��
     */
    public void setBeanFlowInvokerFactoryName(ServiceName name);
    /**
     * BeanFlowInvoker�t�@�N�g�����擾
     * @return BeanFlowInvokerFactory�T�[�r�X��
     */
    public ServiceName getBeanFlowInvokerFactoryName();
    /**
     * CacheService���ݒ�
     * @param name CacheService��
     */
    public void setCacheServiceName(ServiceName name);
    /**
     * CacheService���擾
     * @return CacheService��
     */
    public ServiceName getCacheServiceName();
    /**
     * �S�}�X�^�X�V
     * @param date ���n��ɓo�^����}�X�^�̎���
     */
    public void codeMasterRefresh(Date date);
    /**
     * �S�}�X�^�X�V
     * ���n��ɓo�^�����}�X�^�̎����͌��ݎ����ƂȂ�
     */
    public void codeMasterRefresh();
    /**
     * �w��}�X�^�X�V
     * ���n��ɓo�^�����}�X�^�̎����͌��ݎ����ƂȂ�
     * @param flowName �X�V����}�X�^��
     */
    public void codeMasterRefresh( String flowName );
    /**
     * �w��}�X�^�X�V
     * @param beanflowName �X�V����}�X�^��
     * @param date ���n��ɓo�^����}�X�^�̎���
     */
    public void codeMasterRefresh(String beanflowName,Date date) ;

    /**
     * �S�R�[�h�}�X�^���X�V���鎞�ɍX�V���Ȃ��}�X�^�̃}�X�^���z���ݒ肷��B<p>
     * �w�肵�Ȃ��ꍇ�́A�S�Ẵ}�X�^���S�R�[�h�}�X�^�X�V���Ɏ擾�����B<br>
     *
     * @param names �}�X�^���z��
     */
    public void setNotUpdateAllMasterNames(String[] names);

    /**
     * �S�R�[�h�}�X�^���X�V���鎞�ɍX�V���Ȃ��}�X�^�̃}�X�^���z����擾����B<p>
     *
     * @return �}�X�^���z��
     */
    public String[] getNotUpdateAllMasterNames();
}
