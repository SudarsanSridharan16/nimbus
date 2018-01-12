/*
 * This software is distributed under following license based on modified BSD
 * style license.
 * ----------------------------------------------------------------------
 * 
 * Copyright 2009 The Nimbus2 Project. All rights reserved.
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
 * policies, either expressed or implied, of the Nimbus2 Project.
 */
package jp.ossc.nimbus.service.queue;

import java.util.*;

import jp.ossc.nimbus.core.*;
import jp.ossc.nimbus.service.context.SharedContextServiceMBean;

/**
 * {@link SharedQueueService}��MBean�C���^�t�F�[�X<p>
 * 
 * @author M.Takata
 * @see SharedQueueService
 */
public interface SharedQueueServiceMBean extends SharedContextServiceMBean{
    
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
     * �f�t�H���g�́Afalse�ŕۏ؂��Ȃ��B<br>
     *
     * @param isSafe �ۏ؂���ꍇ�Atrue
     */
    public void setSafeGetOrder(boolean isSafe);
    
    /**
     * �L���[�v�f�����L�R���e�L�X�g�Ɋi�[����ۂ̃L�[�i{����}+{�ʔ�}+{UID}�j�̎����t�H�[�}�b�g��ݒ肷��B<p>
     * �f�t�H���g�́A"yyyyMMddHHmmssSSS"�B<br>
     *
     * @param format �����t�H�[�}�b�g
     */
    public void setSequenceTimestampFormat(String format);
    
    /**
     * �L���[�v�f�����L�R���e�L�X�g�Ɋi�[����ۂ̃L�[�i{����}+{�ʔ�}+{UID}�j�̎����t�H�[�}�b�g���擾����B<p>
     *
     * @return �����t�H�[�}�b�g
     */
    public String getSequenceTimestampFormat();
    
    /**
     * �L���[�v�f�����L�R���e�L�X�g�Ɋi�[����ۂ̃L�[�i{����}+{�ʔ�}+{UID}�j�̒ʔԌ�����ݒ肷��B<p>
     * �f�t�H���g�́A5�B<br>
     *
     * @param digit �ʔԌ���
     */
    public void setSequenceDigit(int digit);
    
    /**
     * �L���[�v�f�����L�R���e�L�X�g�Ɋi�[����ۂ̃L�[�i{����}+{�ʔ�}+{UID}�j�̒ʔԌ������擾����B<p>
     *
     * @return �ʔԌ���
     */
    public int getSequenceDigit();
    
    /**
     * ����ɃL���[�̗v�f���擾���鎞�ɁA�擾�\�ȗv�f��T������[����ݒ肷��B<p>
     * �f�t�H���g�́A2�B<br>
     *
     * @param size �T������[��
     */
    public void setSeekDepth(int size);
    
    /**
     * ����ɃL���[�̗v�f���擾���鎞�ɁA�擾�\�ȗv�f��T������[�����擾����B<p>
     *
     * @return �T������[��
     */
    public int getSeekDepth();
    
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