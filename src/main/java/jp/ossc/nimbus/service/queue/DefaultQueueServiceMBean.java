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

import java.util.*;

import jp.ossc.nimbus.core.*;

/**
 * {@link DefaultQueueService}��MBean�C���^�t�F�[�X<p>
 * 
 * @author M.Takata
 * @see DefaultQueueService
 */
public interface DefaultQueueServiceMBean extends ServiceBaseMBean{
    
    /**
     * �L���[�̏����e�ʂ�ݒ肷��B<p>
     * �T�[�r�X�̐������Ɏg�p����鑮���Ȃ̂ŁA������̕ύX�͂ł��Ȃ��B<br>
     * 0�ȏ�̒l��ݒ肷��ƗL���ɂȂ�B�f�t�H���g�l�́A-1�Łu�����e�ʂ��w�肵�Ȃ��v�ł���B<br>
     *
     * @param initial �L���[�̏����e��
     */
    public void setInitialCapacity(int initial);
    
    /**
     * �L���[�̏����e�ʂ��擾����B<p>
     *
     * @return �L���[�̏����e��
     */
    public int getInitialCapacity();
    
    /**
     * �L���[�̗v�f�����e�ʂ��z�������ɁA����������e�ʂ�ݒ肷��B<p>
     * �T�[�r�X�̐������Ɏg�p����鑮���Ȃ̂ŁA������̕ύX�͂ł��Ȃ��B<br>
     * 0�ȏ�̒l��ݒ肷��ƗL���ɂȂ�B�܂��A�L���ȏ����e�ʂ��ݒ肳��Ă��Ȃ��ꍇ�́A�����ƂȂ�B�f�t�H���g�l�́A-1�Łu�����e�ʂ��w�肵�Ȃ��v�ł���B<br>
     *
     * @param increment �����e��
     */
    public void setCapacityIncrement(int increment);
    
    /**
     * �L���[�̗v�f�����e�ʂ��z�������ɁA����������e�ʂ��擾����B<p>
     *
     * @return �����e��
     */
    public int getCapacityIncrement();
    
    /**
     * �L���[�v�f���L���b�V������L���b�V���T�[�r�X����ݒ肷��B<p>
     * ���̑������ݒ肳��Ă���ꍇ�A�w�肳�ꂽ�L���b�V���T�[�r�X�ɁA�L���[�v�f���L���b�V������B�L���[�����ɂ́A{@link jp.ossc.nimbus.service.cache.CachedReference CachedReference}���ێ�����邽�߁A�L���[�v�f�̐����̓L���b�V���T�[�r�X�Ɉς˂���B<br>
     *
     * @param name {@link jp.ossc.nimbus.service.cache.Cache Cache}�T�[�r�X��
     */
    public void setCacheServiceName(ServiceName name);
    
    /**
     * �L���[�v�f���L���b�V������L���b�V���T�[�r�X�����擾����B<p>
     *
     * @return {@link jp.ossc.nimbus.service.cache.Cache Cache}�T�[�r�X��
     */
    public ServiceName getCacheServiceName();
    
    /**
     * �L���[�ɑ΂��Ė����擾�҂�������X���b�h��sleep���鎞�Ԃ�ݒ肷��B<p>
     * �������L���[�҂��̐擪�łȂ��ꍇ��A�L���[�ɗ��܂��ĂȂ��ꍇ�́A�Ă�sleep����B<br>
     * �f�t�H���g�́A10�b�B
     *
     * @param millis �L���[�ɑ΂��Ė����擾�҂�������X���b�h��sleep���鎞��[ms]
     */
    public void setSleepTime(long millis);
    
    /**
     * �L���[�ɑ΂��Ė����擾�҂�������X���b�h��sleep���鎞�Ԃ��擾����B<p>
     *
     * @return �L���[�ɑ΂��Ė����擾�҂�������X���b�h��sleep���鎞��[ms]
     */
    public long getSleepTime();
    
    /**
     * �L���[�̍ő�臒l��ݒ肷��B<p>
     * �L���[�̐[�����ő�臒l�ɓ��B����ƁA�L���[�ւ̓����͑҂�����A�L���[����̈��������Ɠ��������B<br>
     * �f�t�H���g�́A-1�ŁA�ő�臒l�Ȃ��̏�Ԃł���B<br>
     *
     * @param size �L���[�̍ő�臒l
     */
    public void setMaxThresholdSize(int size);
    
    /**
     * �L���[�̍ő�臒l���擾����B<p>
     *
     * @return �L���[�̍ő�臒l
     */
    public int getMaxThresholdSize();
    
    /**
     * �L���[�v�f���擾���ɗ��������ǂ���ɓn������ۏ؂��邩�ǂ����𔻒肷��B<p>
     *
     * @return true�̏ꍇ�A�ۏ؂���
     */
    public boolean isSafeGetOrder();
    
    /**
     * �L���[�v�f���擾���ɗ��������ǂ���ɓn������ۏ؂��邩�ǂ�����ݒ肷��B<p>
     * �f�t�H���g�́Atrue�ŕۏ؂���B<br>
     *
     * @param isSafe �ۏ؂���ꍇ�Atrue
     */
    public void setSafeGetOrder(boolean isSafe);
    
    /**
     * �����Ŏg�p����{@link jp.ossc.nimbus.util.SynchronizeMonitor SynchronizeMonitor}�̎����N���X��ݒ肷��B<p>
     *
     * @param clazz SynchronizeMonitor�̎����N���X
     */
    public void setSynchronizeMonitorClass(Class clazz);
    
    /**
     * �����Ŏg�p����{@link jp.ossc.nimbus.util.SynchronizeMonitor SynchronizeMonitor}�̎����N���X���擾����B<p>
     *
     * @return SynchronizeMonitor�̎����N���X
     */
    public Class getSynchronizeMonitorClass();
    
    /**
     * �L���[�̌��݂̗v�f���X�g���擾����B<p>
     * �����Ŏ擾���ꂽ�L���[�v�f�́A���̑���ł̓L���[����폜����Ȃ��B<br>
     *
     * @return �L���[�̌��݂̗v�f���X�g
     */
    public List elements();
    
    /**
     * �L���[������������B <p>
     */
    public void clear();
    
    /**
     * ����܂łɃL���[�Ɋi�[���ꂽ�����擾����B<p>
     *
     * @return ����܂łɃL���[�Ɋi�[���ꂽ��
     */
    public long getCount();
    
    /**
     * �O��₢���킹����L���[�Ɋi�[���ꂽ�����擾����B<p>
     *
     * @return �O��₢���킹����L���[�Ɋi�[���ꂽ��
     */
    public long getCountDelta();
    
    /**
     * �Ō�ɃL���[�Ɋi�[���ꂽ�������擾����B<p>
     *
     * @return �Ō�ɃL���[�Ɋi�[���ꂽ����
     */
    public long getLastPushedTimeMillis();
    
    /**
     * �Ō�ɃL���[�Ɋi�[���ꂽ�������擾����B<p>
     *
     * @return �Ō�ɃL���[�Ɋi�[���ꂽ����
     */
    public Date getLastPushedTime();
    
    /**
     * ���݂̃L���[�̐[�����擾����B<p>
     *
     * @return ���݂̃L���[�̐[��
     */
    public long getDepth();
    
    /**
     * �O��₢���킹����̃L���[�̐[�����擾����B<p>
     *
     * @return �O��₢���킹����̃L���[�̐[��
     */
    public long getDepthDelta();
    
    /**
     * �ő哞�B���̃L���[�̐[�����擾����B<p>
     *
     * @return �ő哞�B���̃L���[�̐[��
     */
    public long getMaxDepth();
    
}
