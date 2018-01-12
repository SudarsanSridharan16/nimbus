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
package jp.ossc.nimbus.service.validator;

import java.util.Map;

import jp.ossc.nimbus.core.*;

/**
 * {@link BeanFlowValidatorService}�T�[�r�XMBean�C���^�t�F�[�X�B<p>
 *
 * @author M.Takata
 */
public interface BeanFlowValidatorServiceMBean extends ServiceBaseMBean {
    
    /**
     * ����Bean�̃N���X���ɑ΂��Ďg�p����BeanFlow�̃}�b�s���O��ݒ肷��B<p>
     *
     * @param mapping ����Bean�̃N���X��=BeanFlow�̃L�[�ō\�������}�b�v
     */
    public void setClassMapping(Map mapping);
    
    /**
     * ����Bean�̃N���X���ɑ΂��Ďg�p����BeanFlow�̃}�b�s���O���擾����B<p>
     *
     * @return ����Bean�̃N���X��=BeanFlow�̃L�[�ō\�������}�b�v
     */
    public Map getClassMapping();
    
    /**
     * ����Bean�ɑ΂���������Ɏg�p����BeanFlow�̃}�b�s���O��ݒ肷��B<p>
     * �������́AThe Apache Jakarta Project�� Commons Jexl(http://jakarta.apache.org/commons/jexl/)���g�p����B<br>
     * ����Bean���̂��Q�Ƃ���ꍇ�́A"value"�Ƃ����\�����g�p����B<br>
     * ����Bean�̃v���p�e�B���Q�Ƃ���ꍇ�́A�v���p�e�B��\�����镶�����"@"�ň͂�Ŏw�肷��B�����Ō����A�v���p�e�B�̊T�O�́AJava Beans�̃v���p�e�B�̊T�O���L���A{@link jp.ossc.nimbus.beans.PropertyFactory PropertyFactory}�̋K��ɏ]���B<br>
     *
     * @param conditions ����Bean�ɑ΂��������=BeanFlow�̃L�[�ō\�����������z��
     */
    public void setConditions(String[] conditions);
    
    /**
     * ����Bean�ɑ΂���������Ɏg�p����BeanFlow�̃}�b�s���O���擾����B<p>
     *
     * @return ����Bean�ɑ΂��������=BeanFlow�̃L�[�ō\�����������z��
     */
    public String[] getConditions();
    
    /**
     * �ǂ̏����ɂ����v���Ȃ��ꍇ�Ɏg�p����BeanFlow�̃L�[��ݒ肷��B<p>
     *
     * @param beanFlowKey BeanFlow�̃L�[
     */
    public void setDefaultBeanFlowKey(String beanFlowKey);
    
    /**
     * �ǂ̏����ɂ����v���Ȃ��ꍇ�Ɏg�p����BeanFlow�̃L�[���擾����B<p>
     *
     * @return BeanFlow�̃L�[
     */
    public String getDefaultBeanFlowKey();
    
    /**
     * Bean�̕ϊ����s��BeanFlow���擾����{@link jp.ossc.nimbus.service.beancontrol.interfaces.BeanFlowInvokerFactory BeanFlowInvokerFactory}�T�[�r�X�̃T�[�r�X����ݒ肷��B<p>
     *
     * @param name BeanFlowInvokerFactory�T�[�r�X�̃T�[�r�X��
     */
    public void setBeanFlowInvokerFactoryServiceName(ServiceName name);
    
    /**
     * Bean�̕ϊ����s��BeanFlow���擾����{@link jp.ossc.nimbus.service.beancontrol.interfaces.BeanFlowInvokerFactory BeanFlowInvokerFactory}�T�[�r�X�̃T�[�r�X�����擾����B<p>
     *
     * @return BeanFlowInvokerFactory�T�[�r�X�̃T�[�r�X��
     */
    public ServiceName getBeanFlowInvokerFactoryServiceName();
}