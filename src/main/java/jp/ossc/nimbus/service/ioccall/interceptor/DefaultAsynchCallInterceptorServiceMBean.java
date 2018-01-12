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
package jp.ossc.nimbus.service.ioccall.interceptor;

import jp.ossc.nimbus.core.*;

/**
 * {@link DefaultAsynchCallInterceptorService}��MBean�C���^�t�F�[�X�B<p>
 * 
 * @author Y.Tokuda
 */
public interface DefaultAsynchCallInterceptorServiceMBean
 extends ServiceBaseMBean{
    
    public static final String DELIVERY_MODE_PERSISTENT = "PERSISTENT";
    public static final String DELIVERY_MODE_NON_PERSISTENT = "NON_PERSISTENT";
    
    /**
     * QueueSession�𐶐�����{@link jp.ossc.nimbus.service.resource.ResourceFactory ResourceFactory}�T�[�r�X�̃T�[�r�X����ݒ肷��B<p>
     * {@link jp.ossc.nimbus.service.ioccall.FacadeCaller#syncParallelFacadeCall}���\�b�h���g�p����ꍇ�́A�ݒ肷��K�v������B<br>
     *
     * @param name ResourceFactory�T�[�r�X�̃T�[�r�X��
     */
    public void setQueueSessionFactoryServiceName(ServiceName name);
    
    /**
     * QueueSession�𐶐�����{@link jp.ossc.nimbus.service.resource.ResourceFactory ResourceFactory}�T�[�r�X�̃T�[�r�X�����擾����B<p>
     *
     * @return ResourceFactory�T�[�r�X�̃T�[�r�X��
     */
    public ServiceName getQueueSessionFactoryServiceName();
    
    /**
     * JMS Queue�ɑ��M����ۂ̔z�M���[�h��ݒ肷��B<p>
     * ���s�����y�є񓯊����������Ȃ��ꍇ�́A�ݒ肷��K�v�͂Ȃ��B<br>
     *
     * @param mode �z�M���[�h
     * @see #DELIVERY_MODE_PERSISTENT
     * @see #DELIVERY_MODE_NON_PERSISTENT
     */
    public void setDeliveryMode(String mode);
    
    /**
     * JMS Queue�ɑ��M����ۂ̔z�M���[�h���擾����B<p>
     *
     * @return �z�M���[�h
     */
    public String getDeliveryMode();
    
    /**
     * JMS Queue�ɑ��M����ۂ̃��b�Z�[�W�D�揇�ʂ�ݒ肷��B<p>
     * ���s�����y�є񓯊����������Ȃ��ꍇ�́A�ݒ肷��K�v�͂Ȃ��B<br>
     *
     * @param priority ���b�Z�[�W�D�揇��
     */
    public void setPriority(int priority);
    
    /**
     * JMS Queue�ɑ��M����ۂ̃��b�Z�[�W�D�揇�ʂ��擾����B<p>
     *
     * @return ���b�Z�[�W�D�揇��
     */
    public int getPriority();
    
    /**
     * JMS Queue�ɑ��M����ۂ̃��b�Z�[�W������ݒ肷��B<p>
     * ���s�����y�є񓯊����������Ȃ��ꍇ�́A�ݒ肷��K�v�͂Ȃ��B<br>
     *
     * @param millis ���b�Z�[�W����[ms]
     */
    public void setTimeToLive(long millis);
    
    /**
     * JMS Queue�ɑ��M����ۂ̃��b�Z�[�W�������擾����B<p>
     *
     * @return ���b�Z�[�W����[ms]
     */
    public long getTimeToLive();
    
    /**
     * MDB�ւ̍đ����b�Z�[�W�𖳎����邩�ǂ�����ݒ肷��B<p>
     * �f�t�H���g�́Afalse�B
     *
     * @param ignore ��������ꍇtrue
     */
    public void setIgnoreRedelivery(boolean ignore);
    
    /**
     * MDB�ւ̍đ����b�Z�[�W�𖳎����邩�ǂ����𔻒肷��B<p>
     *
     * @return true�̏ꍇ�A��������
     */
    public boolean isIgnoreRedelivery();
    
    /**
     * �đ����b�Z�[�W�����s����O�Ɉ�莞�ԑҋ@����ҋ@���Ԃ�ݒ肷��B<p>
     * �f�t�H���g�́A0�őҋ@���Ȃ��B<br>
     *
     * @param millis �ҋ@����[ms]
     */
    public void setRedeliveryInterval(long millis);
    
    /**
     * �đ����b�Z�[�W�����s����O�Ɉ�莞�ԑҋ@����ҋ@���Ԃ��擾����B<p>
     *
     * @return �ҋ@����[ms]
     */
    public long getRedeliveryInterval();
}
