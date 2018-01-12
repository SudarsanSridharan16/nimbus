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
package jp.ossc.nimbus.service.queue;

import java.util.Map;

import jp.ossc.nimbus.core.*;

/**
 * {@link BeanFlowInvokerCallQueueHandlerService}��MBean�C���^�t�F�[�X�B<p>
 * 
 * @author M.Takata
 * @see BeanFlowInvokerCallQueueHandlerService
 */
public interface BeanFlowInvokerCallQueueHandlerServiceMBean extends ServiceBaseMBean{
    
    /**
     * BeanFlow�L�[�����Bean���猈�肷�鎞�̃v���p�e�B����ݒ肷��B<p>
     * ����Bean���g�́A"input"�ƕ\�����A���̃v���p�e�B���Q�Ƃ���ꍇ�́AJava Beans�̃v���p�e�B�̊T�O���L���A{@link jp.ossc.nimbus.beans.PropertyFactory PropertyFactory}�̋K��ɏ]���B<br>
     * ����Bean���g��BeanFlow�L�[�ł���ꍇ�́A"input"�Ƃ̂ݐݒ肷��B<br>
     *
     * @param prop �v���p�e�B��
     */
    public void setBeanFlowKeyByInput(String prop);
    
    /**
     * BeanFlow�L�[�����Bean���猈�肷�鎞�̃v���p�e�B�����擾����B<p>
     *
     * @return �v���p�e�B��
     */
    public String getBeanFlowKeyByInput();
    
    /**
     * BeanFlow�̈��������Bean���猈�肷�鎞�̃v���p�e�B����ݒ肷��B<p>
     * ����Bean���g�́A"input"�ƕ\�����A���̃v���p�e�B���Q�Ƃ���ꍇ�́AJava Beans�̃v���p�e�B�̊T�O���L���A{@link jp.ossc.nimbus.beans.PropertyFactory PropertyFactory}�̋K��ɏ]���B<br>
     * ����Bean���g��BeanFlow�̈����ł���ꍇ�́A"input"�Ƃ̂ݐݒ肷��B<br>
     *
     * @param prop �v���p�e�B��
     */
    public void setBeanFlowInputByInput(String prop);
    
    /**
     * BeanFlow�̈��������Bean���猈�肷�鎞�̃v���p�e�B�����擾����B<p>
     *
     * @return �v���p�e�B��
     */
    public String getBeanFlowInputByInput();
    
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
    
    /**
     * �Ăяo��BeanFlow��������Ȃ��ꍇ�ɁA��O��throw���邩�ǂ�����ݒ肷��B<p>
     * �f�t�H���g�́Afalse�ŁA��U�肷��B<br>
     * true�ɂ���ƁABeanFlowInvokerCallQueueHandlerService.BeanFlowNotFoundException��throw����B<br>
     * 
     * @param isThrow ��O��throw����ꍇ�Atrue
     */
    public void setThrowOnNotFoundBeanFlow(boolean isThrow);
    
    /**
     * �Ăяo��BeanFlow��������Ȃ��ꍇ�ɁA��O��throw���邩�ǂ����𔻒肷��B<p>
     * 
     * @return true�̏ꍇ�A��O��throw����
     */
    public boolean isThrowOnNotFoundBeanFlow();
    
    /**
     * �n���h�����O���ɃG���[�����������ꍇ�ɏo�͂��郍�O�̃��b�Z�[�WID��ݒ肷��B<p>
     * �f�t�H���g�́Anull�ŁA���O���o�͂��Ȃ��B<br>
     *
     * @param id ���O�̃��b�Z�[�WID
     */
    public void setErrorLogMessageId(String id);
    
    /**
     * �n���h�����O���ɃG���[�����������ꍇ�ɏo�͂��郍�O�̃��b�Z�[�WID���擾����B<p>
     * 
     * @return ���O�̃��b�Z�[�WID
     */
    public String getErrorLogMessageId();
    
    /**
     * �n���h�����O���ɃG���[���������A�K��̃��g���C�񐔂��z�����ꍇ�ɏo�͂��郍�O�̃��b�Z�[�WID��ݒ肷��B<p>
     * �f�t�H���g�́Anull�ŁA���O���o�͂��Ȃ��B<br>
     *
     * @param id ���O�̃��b�Z�[�WID
     */
    public void setRetryOverErrorLogMessageId(String id);
    
    /**
     * �n���h�����O���ɃG���[���������A�K��̃��g���C�񐔂��z�����ꍇ�ɏo�͂��郍�O�̃��b�Z�[�WID���擾����B<p>
     * 
     * @return ���O�̃��b�Z�[�WID
     */
    public String getRetryOverErrorLogMessageId();
    
    /**
     * {@link jp.ossc.nimbus.service.context.Context}�T�[�r�X�̃T�[�r�X����ݒ肷��B<p>
     * ���̑������w�肵�Ă����āA�n���h�����O�̈�����{@link AsynchContext}�̏ꍇ�A{@link AsynchContext#applyThreadContext(jp.ossc.nimbus.service.context.Context) applyThreadContext(Context)}���Ăяo���B<br>
     * 
     * @param name Context�T�[�r�X�̃T�[�r�X��
     */
    public void setThreadContextServiceName(ServiceName name);
    
    /**
     * {@link jp.ossc.nimbus.service.context.Context}�T�[�r�X�̃T�[�r�X�����擾����B<p>
     * 
     * @return Context�T�[�r�X�̃T�[�r�X��
     */
    public ServiceName getThreadContextServiceName();
    
    /**
     * �X���b�h�R���e�L�X�g�����������邩�ǂ����𔻒肷��B<p>
     *
     * @return true�̏ꍇ�A�X���b�h�R���e�L�X�g������������
     */
    public boolean isClearThreadContext();
    
    /**
     * �X���b�h�R���e�L�X�g�����������邩�ǂ�����ݒ肷��B<p>
     * �f�t�H���g�́Atrue�ŏ���������B<br>
     *
     * @param isClear �X���b�h�R���e�L�X�g������������ꍇ�Atrue
     */
    public void setClearThreadContext(boolean isClear);
}