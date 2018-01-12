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
package jp.ossc.nimbus.service.cache;

import jp.ossc.nimbus.core.FactoryServiceBaseMBean;
import jp.ossc.nimbus.core.ServiceName;

/**
 * {@link DefaultOverflowControllerFactoryService}��MBean�C���^�t�F�[�X<p>
 * 
 * @author M.Takata
 * @see DefaultOverflowControllerFactoryService
 */
public interface DefaultOverflowControllerFactoryServiceMBean
 extends FactoryServiceBaseMBean{
    
    /**
     * ���ӂꌟ�؂��s��OverflowValidator�T�[�r�X�̃T�[�r�X����ݒ肷��B<p>
     * �ݒ肵�Ȃ��ꍇ�́A���ӂꐧ�䂪�s���Ȃ��B<br>
     *
     * @param name �T�[�r�X��
     */
    public void setOverflowValidatorServiceName(ServiceName name);
    
    /**
     * ���ӂꌟ�؂��s��OverflowValidator�T�[�r�X�̃T�[�r�X�����擾����B<p>
     *
     * @return �T�[�r�X��
     */
    public ServiceName getOverflowValidatorServiceName();
    
    /**
     * ���ӂꌟ�،��ʂɏ]���Ă��ӂ��L���b�V���I�u�W�F�N�g�����肷��OverflowAlgorithm�T�[�r�X�̃T�[�r�X����ݒ肷��B<p>
     * {@link #setOverflowValidatorServiceName(ServiceName)}�ł��ӂꌟ�؃T�[�r�X���ݒ肳��Ă���ꍇ�́A���̑������K���ݒ肵�Ȃ���΂Ȃ�Ȃ��B<br>
     *
     * @param name �T�[�r�X��
     */
    public void setOverflowAlgorithmServiceName(ServiceName name);
    
    /**
     * ���ӂꌟ�،��ʂɏ]���Ă��ӂ��L���b�V���I�u�W�F�N�g�����肷��OverflowAlgorithm�T�[�r�X�̃T�[�r�X�����擾����B<p>
     *
     * @return �T�[�r�X��
     */
    public ServiceName getOverflowAlgorithmServiceName();
    
    /**
     * ���ӂ�A���S���Y���ɂ���Č��肳�ꂽ���ӂ�L���b�V���I�u�W�F�N�g�����ӂꂳ����OverflowAction�T�[�r�X�̃T�[�r�X����ݒ肷��B<p>
     * �ݒ肵�Ȃ��ꍇ�ɂ́A{@link RemoveOverflowActionService}���g�p�����B<br>
     *
     * @param name �T�[�r�X��
     */
    public void setOverflowActionServiceName(ServiceName name);
    
    /**
     * ���ӂ�A���S���Y���ɂ���Č��肳�ꂽ���ӂ�L���b�V���I�u�W�F�N�g�����ӂꂳ����OverflowAction�T�[�r�X�̃T�[�r�X�����擾����B<p>
     *
     * @return �T�[�r�X��
     */
    public ServiceName getOverflowActionServiceName();
    
    /**
     * ���ӂꐧ��̗v����ʃX���b�h�ŏ������邽�߂ɁA��U�L���[�ɗ��߂邽�߂�Queue�T�[�r�X�̃T�[�r�X����ݒ肷��B<p>
     * �ݒ肵�Ȃ��ꍇ�ɂ́A{@link jp.ossc.nimbus.service.queue.DefaultQueueService DefaultQueueService}���g�p�����B<br>
     *
     * @param name �T�[�r�X��
     */
    public void setQueueServiceName(ServiceName name);
    
    /**
     * ���ӂꐧ��̗v����ʃX���b�h�ŏ������邽�߂ɁA��U�L���[�ɗ��߂邽�߂�Queue�T�[�r�X�̃T�[�r�X�����擾����B<p>
     *
     * @return �T�[�r�X��
     */
    public ServiceName getQueueServiceName();
    
    /**
     * ����I�ɂ��ӂꐧ����s�����ԊԊu[ms]��ݒ肷��B<p>
     * �f�t�H���g��0�ŁA����I�Ȃ��ӂꐧ��͍s��Ȃ��B<br>
     *
     * @param time ����I�ɂ��ӂꐧ����s�����ԊԊu[ms]
     */
    public void setPeriodicOverflowIntervalTime(long time);
    
    /**
     * ����I�ɂ��ӂꐧ����s�����ԊԊu[ms]���擾����B<p>
     *
     * @return ����I�ɂ��ӂꐧ����s�����ԊԊu[ms]
     */
    public long getPeriodicOverflowIntervalTime();
    
    /**
     * ���ӂꐧ����s�����߂ɕێ����Ă����������������B<p>
     */
    public void reset();
}
