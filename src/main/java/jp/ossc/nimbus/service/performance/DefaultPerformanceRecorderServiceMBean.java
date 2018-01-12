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
package jp.ossc.nimbus.service.performance;

import jp.ossc.nimbus.core.*;

/**
 * {@link DefaultPerformanceRecorderService}��MBean�C���^�t�F�[�X<p>
 * 
 * @author M.Takata
 * @see DefaultPerformanceRecorderService
 */
public interface DefaultPerformanceRecorderServiceMBean extends ServiceBaseMBean{
    
    /**
     * �o�͂���p�t�H�[�}���X���}�b�v�̃L�[�F�L�^�J�n�����B<p>
     */
    public static final String RECORD_KEY_TIMESTAMP       = "Timestamp";
    
    /**
     * �o�͂���p�t�H�[�}���X���}�b�v�̃L�[�F����L�^�����B<p>
     */
    public static final String RECORD_KEY_FIRST_TIMESTAMP = "FirstTimestamp";
    
    /**
     * �o�͂���p�t�H�[�}���X���}�b�v�̃L�[�F�ŏI�L�^�����B<p>
     */
    public static final String RECORD_KEY_LAST_TIMESTAMP  = "LastTimestamp";
    
    /**
     * �o�͂���p�t�H�[�}���X���}�b�v�̃L�[�F�L�^�񐔁B<p>
     */
    public static final String RECORD_KEY_COUNT           = "Count";
    
    /**
     * �o�͂���p�t�H�[�}���X���}�b�v�̃L�[�F�ō��������ԁB<p>
     */
    public static final String RECORD_KEY_BEST            = "Best";
    
    /**
     * �o�͂���p�t�H�[�}���X���}�b�v�̃L�[�F�ŒᏈ�����ԁB<p>
     */
    public static final String RECORD_KEY_WORST           = "Worst";
    
    /**
     * �o�͂���p�t�H�[�}���X���}�b�v�̃L�[�F���Ϗ������ԁB<p>
     */
    public static final String RECORD_KEY_AVERAGE         = "Average";
    
    /**
     * �o�͂���p�t�H�[�}���X���}�b�v�̃L�[�F�����������ԁB<p>
     */
    public static final String RECORD_KEY_MEDIAN          = "Median";
    
    /**
     * �L�^�����p�t�H�[�}���X�����Z�b�g����Ԋu[ms]��ݒ肷��B<p>
     * �f�t�H���g�́A60�b�B<br>
     *
     * @param millis ���Z�b�g����Ԋu[ms]
     */
    public void setResetInterval(long millis);
    
    /**
     * �L�^�����p�t�H�[�}���X�����Z�b�g����Ԋu[ms]���擾����B<p>
     *
     * @return ���Z�b�g����Ԋu[ms]
     */
    public long getResetInterval();
    
    /**
     * �p�t�H�[�}���X���L�^��������o�b�t�@�̗e�ʂ�ݒ肷��B<p>
     * �f�t�H���g�́A10�B<br>
     *
     * @param capa �����o�b�t�@�̗e��
     */
    public void setInitialCapacity(int capa);
    
    /**
     * �p�t�H�[�}���X���L�^��������o�b�t�@�̗e�ʂ��擾����B<p>
     *
     * @return �����o�b�t�@�̗e��
     */
    public int getInitialCapacity();
    
    /**
     * �p�t�H�[�}���X���L�^����ΏۃX���b�h�̍ő吔��ݒ肷��B<p>
     * �f�t�H���g�́A-1�Ő������Ȃ��B<br>
     *
     * @param max �ΏۃX���b�h�̍ő吔
     */
    public void setMaxThread(int max);
    
    /**
     * �p�t�H�[�}���X���L�^����ΏۃX���b�h�̍ő吔���擾����B<p>
     *
     * @return �ΏۃX���b�h�̍ő吔
     */
    public int getMaxThread();
    
    /**
     * ���Z�b�g�̃^�C�~���O�ŁA���̊Ԃ̃p�t�H�[�}���X�����o�͂���{@link jp.ossc.nimbus.service.writer.Category Category}�T�[�r�X�̃T�[�r�X����ݒ肷��B<p>
     * �ݒ肵�Ȃ��ꍇ�́A�o�͂��Ȃ��B<br>
     *
     * @param name Category�T�[�r�X�̃T�[�r�X��
     */
    public void setCategoryServiceName(ServiceName name);
    
    /**
     * ���Z�b�g�̃^�C�~���O�ŁA���̊Ԃ̃p�t�H�[�}���X�����o�͂���{@link jp.ossc.nimbus.service.writer.Category Category}�T�[�r�X�̃T�[�r�X�����擾����B<p>
     *
     * @return Category�T�[�r�X�̃T�[�r�X��
     */
    public ServiceName getCategoryServiceName();
    
    /**
     * �p�t�H�[�}���X���L�^����Ȃ������Ԃ̃p�t�H�[�}���X���o�͂��邩�ǂ�����ݒ肷��B<p>
     * �f�t�H���g�́Atrue�ŏo�͂���B<br>
     *
     * @param isOutput �o�͂���ꍇ�Atrue
     */
    public void setOutputNoAccessTime(boolean isOutput);
    
    /**
     * �p�t�H�[�}���X���L�^����Ȃ������Ԃ̃p�t�H�[�}���X���o�͂��邩�ǂ����𔻒肷��B<p>
     *
     * @return true�̏ꍇ�A�o�͂���
     */
    public boolean isOutputNoAccessTime();
    
    /**
     * {@link jp.ossc.nimbus.service.writer.Category Category}�T�[�r�X�ɏo�͂���p�t�H�[�}���X���̂����A{@link #RECORD_KEY_TIMESTAMP}���o�͂��邩�ǂ�����ݒ肷��B<p>
     * �f�t�H���g�́Atrue�ŏo�͂���B<br>
     *
     * @param isOutput �o�͂���ꍇ�Atrue
     */
    public void setOutputTimestamp(boolean isOutput);
    
    /**
     * {@link jp.ossc.nimbus.service.writer.Category Category}�T�[�r�X�ɏo�͂���p�t�H�[�}���X���̂����A{@link #RECORD_KEY_TIMESTAMP}���o�͂��邩�ǂ����𔻒肷��B<p>
     *
     * @return true�̏ꍇ�A�o�͂���
     */
    public boolean isOutputTimestamp();
    
    /**
     * {@link jp.ossc.nimbus.service.writer.Category Category}�T�[�r�X�ɏo�͂���p�t�H�[�}���X���̂����A{@link #RECORD_KEY_COUNT}���o�͂��邩�ǂ�����ݒ肷��B<p>
     * �f�t�H���g�́Atrue�ŏo�͂���B<br>
     *
     * @param isOutput �o�͂���ꍇ�Atrue
     */
    public void setOutputCount(boolean isOutput);
    
    /**
     * {@link jp.ossc.nimbus.service.writer.Category Category}�T�[�r�X�ɏo�͂���p�t�H�[�}���X���̂����A{@link #RECORD_KEY_COUNT}���o�͂��邩�ǂ����𔻒肷��B<p>
     *
     * @return true�̏ꍇ�A�o�͂���
     */
    public boolean isOutputCount();
    
    /**
     * {@link jp.ossc.nimbus.service.writer.Category Category}�T�[�r�X�ɏo�͂���p�t�H�[�}���X���̂����A{@link #RECORD_KEY_BEST}���o�͂��邩�ǂ�����ݒ肷��B<p>
     * �f�t�H���g�́Atrue�ŏo�͂���B<br>
     *
     * @param isOutput �o�͂���ꍇ�Atrue
     */
    public void setOutputBestPerformance(boolean isOutput);
    
    /**
     * {@link jp.ossc.nimbus.service.writer.Category Category}�T�[�r�X�ɏo�͂���p�t�H�[�}���X���̂����A{@link #RECORD_KEY_BEST}���o�͂��邩�ǂ����𔻒肷��B<p>
     *
     * @return true�̏ꍇ�A�o�͂���
     */
    public boolean isOutputBestPerformance();
    
    /**
     * {@link jp.ossc.nimbus.service.writer.Category Category}�T�[�r�X�ɏo�͂���p�t�H�[�}���X���̂����A{@link #RECORD_KEY_WORST}���o�͂��邩�ǂ�����ݒ肷��B<p>
     * �f�t�H���g�́Atrue�ŏo�͂���B<br>
     *
     * @param isOutput �o�͂���ꍇ�Atrue
     */
    public void setOutputWorstPerformance(boolean isOutput);
    
    /**
     * {@link jp.ossc.nimbus.service.writer.Category Category}�T�[�r�X�ɏo�͂���p�t�H�[�}���X���̂����A{@link #RECORD_KEY_WORST}���o�͂��邩�ǂ����𔻒肷��B<p>
     *
     * @return true�̏ꍇ�A�o�͂���
     */
    public boolean isOutputWorstPerformance();
    
    /**
     * {@link jp.ossc.nimbus.service.writer.Category Category}�T�[�r�X�ɏo�͂���p�t�H�[�}���X���̂����A{@link #RECORD_KEY_AVERAGE}���o�͂��邩�ǂ�����ݒ肷��B<p>
     * �f�t�H���g�́Atrue�ŏo�͂���B<br>
     *
     * @param isOutput �o�͂���ꍇ�Atrue
     */
    public void setOutputAveragePerformance(boolean isOutput);
    
    /**
     * {@link jp.ossc.nimbus.service.writer.Category Category}�T�[�r�X�ɏo�͂���p�t�H�[�}���X���̂����A{@link #RECORD_KEY_AVERAGE}���o�͂��邩�ǂ����𔻒肷��B<p>
     *
     * @return true�̏ꍇ�A�o�͂���
     */
    public boolean isOutputAveragePerformance();
    
    /**
     * {@link jp.ossc.nimbus.service.writer.Category Category}�T�[�r�X�ɏo�͂���p�t�H�[�}���X���̂����A{@link #RECORD_KEY_MEDIAN}���o�͂��邩�ǂ�����ݒ肷��B<p>
     * �f�t�H���g�́Atrue�ŏo�͂���B<br>
     *
     * @param isOutput �o�͂���ꍇ�Atrue
     */
    public void setOutputMedianPerformance(boolean isOutput);
    
    /**
     * {@link jp.ossc.nimbus.service.writer.Category Category}�T�[�r�X�ɏo�͂���p�t�H�[�}���X���̂����A{@link #RECORD_KEY_MEDIAN}���o�͂��邩�ǂ����𔻒肷��B<p>
     *
     * @return true�̏ꍇ�A�o�͂���
     */
    public boolean isOutputMedianPerformance();
    
    /**
     * {@link jp.ossc.nimbus.service.writer.Category Category}�T�[�r�X�ɏo�͂���p�t�H�[�}���X���̂����A{@link #RECORD_KEY_FIRST_TIMESTAMP}���o�͂��邩�ǂ�����ݒ肷��B<p>
     * �f�t�H���g�́Atrue�ŏo�͂���B<br>
     *
     * @param isOutput �o�͂���ꍇ�Atrue
     */
    public void setOutputFirstTimestamp(boolean isOutput);
    
    /**
     * {@link jp.ossc.nimbus.service.writer.Category Category}�T�[�r�X�ɏo�͂���p�t�H�[�}���X���̂����A{@link #RECORD_KEY_FIRST_TIMESTAMP}���o�͂��邩�ǂ����𔻒肷��B<p>
     *
     * @return true�̏ꍇ�A�o�͂���
     */
    public boolean isOutputFirstTimestamp();
    
    /**
     * {@link jp.ossc.nimbus.service.writer.Category Category}�T�[�r�X�ɏo�͂���p�t�H�[�}���X���̂����A{@link #RECORD_KEY_LAST_TIMESTAMP}���o�͂��邩�ǂ�����ݒ肷��B<p>
     * �f�t�H���g�́Atrue�ŏo�͂���B<br>
     *
     * @param isOutput �o�͂���ꍇ�Atrue
     */
    public void setOutputLastTimestamp(boolean isOutput);
    
    /**
     * {@link jp.ossc.nimbus.service.writer.Category Category}�T�[�r�X�ɏo�͂���p�t�H�[�}���X���̂����A{@link #RECORD_KEY_LAST_TIMESTAMP}���o�͂��邩�ǂ����𔻒肷��B<p>
     *
     * @return true�̏ꍇ�A�o�͂���
     */
    public boolean isOutputLastTimestamp();
    
    /**
     * ���݂̃p�t�H�[�}���X����\������B<p>
     *
     * @return �p�t�H�[�}���X���
     */
    public String display();
}
