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
package jp.ossc.nimbus.service.connection;

import jp.ossc.nimbus.core.*;
import jp.ossc.nimbus.service.aop.interceptor.MetricsInfo;

/**
 * {@link SQLMetricsCollectorService}��MBean�C���^�t�F�[�X<p>
 * 
 * @author M.Takata
 * @see SQLMetricsCollectorService
 */
public interface SQLMetricsCollectorServiceMBean extends ServiceBaseMBean{
    
    public static final String RECORD_KEY_TIMESTAMP = "Timestamp";
    public static final String RECORD_KEY_ORDER = "Order";
    public static final String RECORD_KEY_SQL = "SQL";
    public static final String RECORD_KEY_COUNT = "Count";
    public static final String RECORD_KEY_EXCEPTION_COUNT = "ExceptionCount";
    public static final String RECORD_KEY_ERROR_COUNT = "ErrorCount";
    public static final String RECORD_KEY_LAST_TIME = "LastTime";
    public static final String RECORD_KEY_LAST_EXCEPTION_TIME = "LastExceptionTime";
    public static final String RECORD_KEY_LAST_ERROR_TIME = "LastErrorTime";
    public static final String RECORD_KEY_BEST_PERFORMANCE = "BestPerformance";
    public static final String RECORD_KEY_BEST_PERFORMANCE_TIME = "BestPerformanceTime";
    public static final String RECORD_KEY_WORST_PERFORMANCE = "WorstPerformance";
    public static final String RECORD_KEY_WORST_PERFORMANCE_TIME = "WorstPerformanceTime";
    public static final String RECORD_KEY_AVERAGE_PERFORMANCE = "AveragePerformance";
    
    /**
     * �f�t�H���g�̓��t�t�H�[�}�b�g�B<p>
     */
    public static final String DEFAULT_DATE_FORMAT = "HH:mm:ss.SSS";
    
    /**
     * ���g���N�X����\������B<p>
     *
     * @return ���g���N�X���
     */
    public String displayMetricsInfo();
    
    /**
     * �擾�������g���N�X�������Z�b�g����B<p>
     */
    public void reset();
    
    /**
     * ���g���N�X�擾���s�����ǂ�����ݒ肷��B<p>
     * �f�t�H���g�ł́Atrue�B
     *
     * @param enable ���g���N�X�擾���s���ꍇtrue
     * @see #isEnabled()
     */
    public void setEnabled(boolean enable);
    
    /**
     * ���g���N�X�擾���s�����ǂ����𔻒肷��B<p>
     *
     * @return ���g���N�X�擾���s���ꍇtrue
     * @see #setEnabled(boolean)
     */
    public boolean isEnabled();
    
    /**
     * ���퉞����Ԃ����ꍇ�����������ԓ��̌v�Z���s�����ǂ�����ݒ肷��B<p>
     * �f�t�H���g��false
     *
     * @param isCalc ���퉞����Ԃ����ꍇ�����������ԓ��̌v�Z���s���ꍇ�́Atrue
     */
    public void setCalculateOnlyNormal(boolean isCalc);
    
    /**
     * ���퉞����Ԃ����ꍇ�����������ԓ��̌v�Z���s�����ǂ����𔻒肷��B<p>
     *
     * @return true�̏ꍇ�́A���퉞����Ԃ����ꍇ�����������ԓ��̌v�Z���s��
     */
    public boolean isCalculateOnlyNormal();
    
    /**
     * �o�͂��鎞���̃t�H�[�}�b�g��ݒ肷��B<p>
     *
     * @param format ���t�t�H�[�}�b�g
     */
    public void setDateFormat(String format);
    
    /**
     * �o�͂��鎞���̃t�H�[�}�b�g���擾����B<p>
     *
     * @return ���t�t�H�[�}�b�g
     */
    public String getDateFormat();
    
    /**
     * �w�肳�ꂽSQL�Ɋւ��郁�g���N�X���擾����B<p>
     *
     * @param sql SQL
     * @return ���g���N�X
     */
    public MetricsInfo getMetricsInfo(String sql);
    
    /**
     * ���g���N�X�̏o�͎��ԊԊu[ms]��ݒ肷��B<p>
     * �f�t�H���g�́A60000[ms]�B
     *
     * @param interval �o�͎��ԊԊu
     */
    public void setOutputInterval(long interval);
    
    /**
     * ���g���N�X�̏o�͎��ԊԊu[ms]���擾����B<p>
     *
     * @return �o�͎��ԊԊu
     */
    public long getOutputInterval();
    
    /**
     * ���g���N�X�̏o�͐�ƂȂ�Category�T�[�r�X�̃T�[�r�X����ݒ肷��B<p>
     *
     * @param name ���g���N�X�̏o�͐�ƂȂ�Category�T�[�r�X�̃T�[�r�X��
     */
    public void setCategoryServiceName(ServiceName name);
    
    /**
     * ���g���N�X�̏o�͐�ƂȂ�Category�T�[�r�X�̃T�[�r�X�����擾����B<p>
     *
     * @return ���g���N�X�̏o�͐�ƂȂ�Category�T�[�r�X�̃T�[�r�X��
     */
    public ServiceName getCategoryServiceName();
    
    /**
     * ���g���N�X��Category�ɏo�͂��閈�Ƀ��g���N�X�����Z�b�g���邩�ǂ�����ݒ肷��B<p>
     * �f�t�H���g�́Afalse�ŁA���Z�b�g���Ȃ��B<br>
     *
     * @param isReset ���Z�b�g����ꍇ�́Atrue
     */
    public void setResetByOutput(boolean isReset);
    
    /**
     * ���g���N�X��Category�ɏo�͂��閈�Ƀ��g���N�X�����Z�b�g���邩�ǂ����𔻒肷��B<p>
     *
     * @return true�̏ꍇ�A���Z�b�g����
     */
    public boolean isResetByOutput();
    
    /**
     * ���g���N�X�擾�^�C���X�^���v���o�͂��邩�ǂ�����ݒ肷��B
     * �f�t�H���g��false�B
     *
     * @param isOutput �o�͂���ꍇ��true
     */
    public void setOutputTimestamp(boolean isOutput);
    
    /**
     * ���g���N�X�擾�^�C���X�^���v���o�͂��邩�ǂ����𔻒肷��B
     *
     * @return true�̏ꍇ�͏o�͂���
     */
    public boolean isOutputTimestamp();
    
    /**
     * SQL�Ăяo���񐔁i���퉞���j���o�͂��邩�ǂ�����ݒ肷��B
     * �f�t�H���g��true�B
     *
     * @param isOutput �o�͂���ꍇ��true
     */
    public void setOutputCount(boolean isOutput);
    
    /**
     * SQL�Ăяo���񐔁i���퉞���j���o�͂��邩�ǂ����𔻒肷��B
     *
     * @return true�̏ꍇ�͏o�͂���
     */
    public boolean isOutputCount();
    
    /**
     * SQL�Ăяo���񐔁i��O�����j���o�͂��邩�ǂ�����ݒ肷��B
     * �f�t�H���g��false�B
     *
     * @param isOutput �o�͂���ꍇ��true
     */
    public void setOutputExceptionCount(boolean isOutput);
    
    /**
     * SQL�Ăяo���񐔁i��O�����j���o�͂��邩�ǂ����𔻒肷��B
     *
     * @return true�̏ꍇ�͏o�͂���
     */
    public boolean isOutputExceptionCount();
    
    /**
     * SQL�Ăяo���񐔁i�G���[�����j���o�͂��邩�ǂ�����ݒ肷��B
     * �f�t�H���g��false�B
     *
     * @param isOutput �o�͂���ꍇ��true
     */
    public void setOutputErrorCount(boolean isOutput);
    
    /**
     * SQL�Ăяo���񐔁i�G���[�����j���o�͂��邩�ǂ����𔻒肷��B
     *
     * @return true�̏ꍇ�͏o�͂���
     */
    public boolean isOutputErrorCount();
    
    /**
     * SQL�Ăяo���ŏI�������o�͂��邩�ǂ�����ݒ肷��B
     * �f�t�H���g��false�B
     *
     * @param isOutput �o�͂���ꍇ��true
     */
    public void setOutputLastTime(boolean isOutput);
    
    /**
     * SQL�Ăяo���ŏI�������o�͂��邩�ǂ����𔻒肷��B
     *
     * @return true�̏ꍇ�͏o�͂���
     */
    public boolean isOutputLastTime();
    
    /**
     * ��O�����ŏI�������o�͂��邩�ǂ�����ݒ肷��B
     * �f�t�H���g��false�B
     *
     * @param isOutput �o�͂���ꍇ��true
     */
    public void setOutputLastExceptionTime(boolean isOutput);
    
    /**
     * ��O�����ŏI�������o�͂��邩�ǂ����𔻒肷��B
     *
     * @return true�̏ꍇ�͏o�͂���
     */
    public boolean isOutputLastExceptionTime();
    
    /**
     * �G���[�����ŏI�������o�͂��邩�ǂ�����ݒ肷��B
     * �f�t�H���g��false�B
     *
     * @param isOutput �o�͂���ꍇ��true
     */
    public void setOutputLastErrorTime(boolean isOutput);
    
    /**
     * �G���[�����ŏI�������o�͂��邩�ǂ����𔻒肷��B
     *
     * @return true�̏ꍇ�͏o�͂���
     */
    public boolean isOutputLastErrorTime();
    
    /**
     * �ō��������Ԃ��o�͂��邩�ǂ�����ݒ肷��B
     * �f�t�H���g��true�B
     *
     * @param isOutput �o�͂���ꍇ��true
     */
    public void setOutputBestPerformance(boolean isOutput);
    
    /**
     * �ō��������Ԃ��o�͂��邩�ǂ����𔻒肷��B
     *
     * @return true�̏ꍇ�͏o�͂���
     */
    public boolean isOutputBestPerformance();
    
    /**
     * �ō������������o�͂��邩�ǂ�����ݒ肷��B
     * �f�t�H���g��false�B
     *
     * @param isOutput �o�͂���ꍇ��true
     */
    public void setOutputBestPerformanceTime(boolean isOutput);
    
    /**
     * �ō������������o�͂��邩�ǂ����𔻒肷��B
     *
     * @return true�̏ꍇ�͏o�͂���
     */
    public boolean isOutputBestPerformanceTime();
    
    /**
     * �ŒᏈ�����Ԃ��o�͂��邩�ǂ�����ݒ肷��B
     * �f�t�H���g��true�B
     *
     * @param isOutput �o�͂���ꍇ��true
     */
    public void setOutputWorstPerformance(boolean isOutput);
    
    /**
     * �ŒᏈ�����Ԃ��o�͂��邩�ǂ����𔻒肷��B
     *
     * @return true�̏ꍇ�͏o�͂���
     */
    public boolean isOutputWorstPerformance();
    
    /**
     * �ŒᏈ���������o�͂��邩�ǂ�����ݒ肷��B
     * �f�t�H���g��false�B
     *
     * @param isOutput �o�͂���ꍇ��true
     */
    public void setOutputWorstPerformanceTime(boolean isOutput);
    
    /**
     * �ŒᏈ���������o�͂��邩�ǂ����𔻒肷��B
     *
     * @return true�̏ꍇ�͏o�͂���
     */
    public boolean isOutputWorstPerformanceTime();
    
    /**
     * ���Ϗ������Ԃ��o�͂��邩�ǂ�����ݒ肷��B
     * �f�t�H���g��true�B
     *
     * @param isOutput �o�͂���ꍇ��true
     */
    public void setOutputAveragePerformance(boolean isOutput);
    
    /**
     * ���Ϗ������Ԃ��o�͂��邩�ǂ����𔻒肷��B
     *
     * @return true�̏ꍇ�͏o�͂���
     */
    public boolean isOutputAveragePerformance();
    
    /**
     * ���g���N�X�̍ő�W�v������ݒ肷��B<p>
     * �f�t�H���g�́A�����Ȃ��B<br>
     *
     * @param max �ő�W�v����
     */
    public void setMaxMetricsSize(int max);
    
    /**
     * ���g���N�X�̍ő�W�v�������擾����B<p>
     *
     * @return �ő�W�v����
     */
    public int getMaxMetricsSize();
}
