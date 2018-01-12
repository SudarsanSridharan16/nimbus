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
package jp.ossc.nimbus.service.ga;

import jp.ossc.nimbus.core.ServiceName;
import jp.ossc.nimbus.core.ServiceBaseMBean;

/**
 * {@link SimpleGeneticAlgorithmService}��MBean�C���^�t�F�[�X<p>
 * 
 * @author M.Takata
 * @see SimpleGeneticAlgorithmService
 */
public interface SimpleGeneticAlgorithmServiceMBean extends ServiceBaseMBean{
    
    /**
     * {@link SeedMatchMaker}�T�[�r�X�̃T�[�r�X����ݒ肷��B<p>
     *
     * @param name SeedMatchMaker�T�[�r�X�̃T�[�r�X��
     */
    public void setSeedMatchMakerServiceName(ServiceName name);
    
    /**
     * {@link SeedMatchMaker}�T�[�r�X�̃T�[�r�X�����擾����B<p>
     *
     * @return SeedMatchMaker�T�[�r�X�̃T�[�r�X��
     */
    public ServiceName getSeedMatchMakerServiceName();
    
    /**
     * ���㋣�����̊e�V�[�h�̓K���l�v�Z�����ɍs���ꍇ�Ɏg�p����{@link jp.ossc.nimbus.service.queue.QueueHandlerContainer QueueHandlerContainer}�T�[�r�X�̃T�[�r�X����ݒ肷��B<p>
     *
     * @param name QueueHandlerContainer�T�[�r�X�̃T�[�r�X��
     */
    public void setQueueHandlerContainerServiceName(ServiceName name);
    
    /**
     * ���㋣�����̊e�V�[�h�̓K���l�v�Z�����ɍs���ꍇ�Ɏg�p����{@link jp.ossc.nimbus.service.queue.QueueHandlerContainer QueueHandlerContainer}�T�[�r�X�̃T�[�r�X�����擾����B<p>
     *
     * @return QueueHandlerContainer�T�[�r�X�̃T�[�r�X��
     */
    public ServiceName getQueueHandlerContainerServiceName();
    
    /**
     * ���㋣�����̊e�V�[�h�̓K���l�v�Z�����ɍs���ꍇ�̃X���b�h����ݒ肷��B<p>
     * {@link #setQueueHandlerContainerServiceName(ServiceName)}���w�肳��Ă���ꍇ�́A����{@link jp.ossc.nimbus.service.queue.QueueHandlerContainer QueueHandlerContainer}�T�[�r�X��{@link jp.ossc.nimbus.service.queue.QueueHandlerContainer#getQueueHandlerSize() getQueueHandlerSize()}���D�悳���B<br>
     * �f�t�H���g�́A0�Œ���ɓK���l�v�Z���s���B<br>
     *
     * @param num �V�[�h�̓K���l�v�Z�̕���x
     */
    public void setParallelThreadNum(int num);
    
    /**
     * ���㋣�����̊e�V�[�h�̓K���l�v�Z�����ɍs���ꍇ�̃X���b�h�����擾����B<p>
     *
     * @return �V�[�h�̓K���l�v�Z�̕���x
     */
    public int getParallelThreadNum();
    
    /**
     * ���㋣�����̊e�V�[�h�̓K���l�v�Z�����ɍs���ꍇ�̉����҂�����[ms]��ݒ肷��B<p>
     * �f�t�H���g�́A-1�Ŗ����҂��B<br>
     *
     * @param timeout �����҂�����[ms]
     */
    public void setParallelResponseTimout(long timeout);
    
    /**
     * ���㋣�����̊e�V�[�h�̓K���l�v�Z�����ɍs���ꍇ�̉����҂�����[ms]���擾����B<p>
     *
     * @return �����҂�����[ms]
     */
    public long getParallelResponseTimout();
    
    /**
     * ������𐶐�������ɁA�����V�[�h��r�����A�V�����V�[�h�Ɠ���ւ��铑�����s�����ǂ�����ݒ肷��B<p>
     * �f�t�H���g�́Afalse�œ����͍s��Ȃ��B<br>
     *
     * @param isSelection ��������ꍇ�Atrue
     */
    public void setSeedSelection(boolean isSelection);
    
    /**
     * ������𐶐�������ɁA�����V�[�h��r�����A�V�����V�[�h�Ɠ���ւ��铑�����s�����ǂ����𔻒肷��B<p>
     *
     * @return true�̏ꍇ�A��������
     */
    public boolean isSeedSelection();
}
