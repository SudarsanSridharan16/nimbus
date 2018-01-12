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
 * {@link DistributedQueueService}��MBean�C���^�t�F�[�X<p>
 * 
 * @author M.Takata
 * @see DistributedQueueService
 */
public interface DistributedQueueServiceMBean extends ServiceBaseMBean{
    
    public void setDistributedQueueSelectorServiceName(ServiceName name);
    
    public ServiceName getDistributedQueueSelectorServiceName();
    
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
}
