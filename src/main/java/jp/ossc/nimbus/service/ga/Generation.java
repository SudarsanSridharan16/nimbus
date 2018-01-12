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

import java.util.Random;

import jp.ossc.nimbus.service.queue.QueueHandlerContainer;

/**
 * ����B<p>
 *
 * @author M.Takata
 */
public interface Generation{
    
    /**
     * �����������w�肷��B<p>
     *
     * @param condition ��������
     */
    public void setConvergenceCondition(ConvergenceCondition condition);
    
    /**
     * ���㋣�������ɍs���ꍇ��{@link QueueHandlerContainer}���w�肷��B<p>
     * �w�肵�Ȃ��ꍇ�́A{@link #compete(int, long)}�̎��s���ɓ����Ő�������B<br>
     *
     * @param qhc ���㋣�������ɍs���ۂɎg�p����QueueHandlerContainer
     */
    public void setQueueHandlerContainer(QueueHandlerContainer qhc);
    
    /**
     * ���㋣�������ɍs���ꍇ��{@link QueueHandlerContainer}���擾����B<p>
     *
     * @return ���㋣�������ɍs���ۂɎg�p����QueueHandlerContainer
     */
    public QueueHandlerContainer getQueueHandlerContainer();
    
    /**
     * �K���l�̕��я���ݒ肷��B<p>
     *
     * @param isAsc �����̏ꍇ�Atrue
     */
    public void setFitnessOrder(boolean isAsc);
    
    /**
     * �K���l�̕��я����擾����B<p>
     *
     * @return true�̏ꍇ�A����
     */
    public boolean getFitnessOrder();
    
    /**
     * ����ԍ����擾����B<p>
     *
     * @return ����ԍ�
     */
    public int getGenerationNo();
    
    /**
     * ��������𐶐�����B<p>
     *
     * @param random �����V�[�h
     * @param seed �e���v���[�g�ƂȂ�V�[�h
     * @param num �V�[�h��
     */
    public void init(Random random, Seed seed, int num);
    
    /**
     * �V�[�h�����ւ���B<p>
     *
     * @param seeds �V�[�h�z��
     */
    public void setSeeds(Seed[] seeds);
    
    /**
     * ���̐��オ���S�ẴV�[�h���擾����B<p>
     *
     * @return �V�[�h�z��
     */
    public Seed[] getSeeds();
    
    /**
     * ���㋣������B<p>
     *
     * @exception Exception ���㋣���Ɏ��s�����ꍇ
     */
    public void compete() throws Exception;
    
    /**
     * ���񏈗��Ő��㋣������B<p>
     *
     * @param threadNum ����x�B{@link #setQueueHandlerContainer(QueueHandlerContainer)}��QueueHandlerContainer���w�肳��Ă���ꍇ�́A���̐ݒ�ɏ]�����ߖ���
     * @param timeout ���񏈗�������ꍇ�ɁA�S�Ă̕��񏈗��X���b�h�̏I���҂�������^�C���A�E�g[ms]
     * @exception Exception ���㋣���Ɏ��s�����ꍇ
     */
    public void compete(int threadNum, long timeout) throws Exception;
    
    /**
     * ������𐶐�����B<p>
     *
     * @param random �����V�[�h
     * @param matchMaker �V�[�h���l
     * @return ������B���������ɓ��B�����ꍇ�́Anull
     */
    public Generation next(Random random, SeedMatchMaker matchMaker);
    
    /**
     * �����҂���œK���҂��擾����B<p>
     *
     * @return �V�[�h
     */
    public Seed getSurvivor();
}