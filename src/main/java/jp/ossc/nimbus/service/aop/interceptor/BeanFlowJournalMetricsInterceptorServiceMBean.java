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
package jp.ossc.nimbus.service.aop.interceptor;

import java.util.Properties;
import java.util.Map;

import jp.ossc.nimbus.core.*;

/**
 * {@link BeanFlowJournalMetricsInterceptorService}��MBean�C���^�t�F�[�X<p>
 * 
 * @author M.Takata
 * @see BeanFlowJournalMetricsInterceptorService
 */
public interface BeanFlowJournalMetricsInterceptorServiceMBean extends ServiceBaseMBean{
    
    public static final String RECORD_KEY_TIMESTAMP = "Timestamp";
    public static final String RECORD_KEY_ORDER = "Order";
    public static final String RECORD_KEY_FLOW = "Flow";
    public static final String RECORD_KEY_COUNT = "Count";
    public static final String RECORD_KEY_EXCEPTION_COUNT = "ExceptionCount";
    public static final String RECORD_KEY_ERROR_COUNT = "ErrorCount";
    public static final String RECORD_KEY_LAST_TIME = "LastTime";
    public static final String RECORD_KEY_LAST_EXCEPTION_TIME = "LastExceptionTime";
    public static final String RECORD_KEY_LAST_ERROR_TIME = "LastErrorTime";
    public static final String RECORD_KEY_BEST_JOURNAL_SIZE = "BestJournalSize";
    public static final String RECORD_KEY_BEST_JOURNAL_SIZE_TIME = "BestJournalSizeTime";
    public static final String RECORD_KEY_WORST_JOURNAL_SIZE = "WorstJournalSize";
    public static final String RECORD_KEY_WORST_JOURNAL_SIZE_TIME = "WorstJournalSizeTime";
    public static final String RECORD_KEY_AVERAGE_JOURNAL_SIZE = "AverageJournalSize";
    
    /**
     * �f�t�H���g�̓��t�t�H�[�}�b�g�B<p>
     */
    public static final String DEFAULT_DATE_FORMAT = "HH:mm:ss.SSS";
    
    /**
     * {@link jp.ossc.nimbus.service.journal.Journal Journal}�T�[�r�X�̃T�[�r�X����ݒ肷��B<p>
     *
     * @param name Journal�T�[�r�X�̃T�[�r�X��
     * @see #getJournalServiceName()
     */
    public void setJournalServiceName(ServiceName name);
    
    /**
     * {@link jp.ossc.nimbus.service.journal.Journal Journal}�T�[�r�X�̃T�[�r�X�����擾����B
     *
     * @return Journal�T�[�r�X�̃T�[�r�X��
     * @see #setJournalServiceName(ServiceName)
     */
    public ServiceName getJournalServiceName();
    
    /**
     * {@link jp.ossc.nimbus.service.journal.editorfinder.EditorFinder EditorFinder}�T�[�r�X�̃T�[�r�X����ݒ肷��B<p>
     *
     * @param name EditorFinder�T�[�r�X�̃T�[�r�X��
     * @see #getEditorFinderServiceName()
     */
    public void setEditorFinderServiceName(ServiceName name);
    
    /**
     * {@link jp.ossc.nimbus.service.journal.editorfinder.EditorFinder EditorFinder}�T�[�r�X�̃T�[�r�X�����擾����B
     *
     * @return EditorFinder�T�[�r�X�̃T�[�r�X��
     * @see #setEditorFinderServiceName(ServiceName)
     */
    public ServiceName getEditorFinderServiceName();
    
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
     * �w�肳�ꂽ�Ɩ��t���[�Ɋւ��郁�g���N�X���擾����B<p>
     *
     * @param flow �Ɩ��t���[
     * @return ���g���N�X
     */
    public MetricsInfo getMetricsInfo(String flow);
    
    /**
     * �S�Ẵ��g���N�X���擾����B<p>
     *
     * @return �L�[���Ɩ��t���[���A�l�����g���N�X��Map
     */
    public Map getMetricsInfos();
    
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
     * �Ɩ��t���[���̃��g���N�X�̏o�͐�}�b�s���O��ݒ肷��B<p>
     * �Ɩ��t���[=Category�T�[�r�X�̃T�[�r�X��
     *
     * @param mapping �Ɩ��t���[�Əo�͐�ƂȂ�Category�T�[�r�X�̃T�[�r�X���̃}�b�s���O
     */
    public void setFlowAndCategoryServiceNameMapping(Properties mapping);
    
    /**
     * �Ɩ��t���[���̃��g���N�X�̏o�͐�}�b�s���O���擾����B<p>
     *
     * @return �Ɩ��t���[�Əo�͐�ƂȂ�Category�T�[�r�X�̃T�[�r�X���̃}�b�s���O
     */
    public Properties getFlowAndCategoryServiceNameMapping();
    
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
     * �p�t�H�[�}���X���L�^����{@link jp.ossc.nimbus.service.performance.PerformanceRecorder PerformanceRecorder}�T�[�r�X�̃T�[�r�X����ݒ肷��B<p>
     *
     * @param name �p�t�H�[�}���X���L�^����PerformanceRecorder�T�[�r�X�̃T�[�r�X��
     */
    public void setPerformanceRecorderServiceName(ServiceName name);
    
    /**
     * �p�t�H�[�}���X���L�^����{@link jp.ossc.nimbus.service.performance.PerformanceRecorder PerformanceRecorder}�T�[�r�X�̃T�[�r�X�����擾����B<p>
     *
     * @return �p�t�H�[�}���X���L�^����PerformanceRecorder�T�[�r�X�̃T�[�r�X��
     */
    public ServiceName getPerformanceRecorderServiceName();
    
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
     * �Ɩ��t���[�Ăяo���񐔁i���퉞���j���o�͂��邩�ǂ�����ݒ肷��B
     * �f�t�H���g��true�B
     *
     * @param isOutput �o�͂���ꍇ��true
     */
    public void setOutputCount(boolean isOutput);
    
    /**
     * �Ɩ��t���[�Ăяo���񐔁i���퉞���j���o�͂��邩�ǂ����𔻒肷��B
     *
     * @return true�̏ꍇ�͏o�͂���
     */
    public boolean isOutputCount();
    
    /**
     * �Ɩ��t���[�Ăяo���񐔁i��O�����j���o�͂��邩�ǂ�����ݒ肷��B
     * �f�t�H���g��false�B
     *
     * @param isOutput �o�͂���ꍇ��true
     */
    public void setOutputExceptionCount(boolean isOutput);
    
    /**
     * �Ɩ��t���[�Ăяo���񐔁i��O�����j���o�͂��邩�ǂ����𔻒肷��B
     *
     * @return true�̏ꍇ�͏o�͂���
     */
    public boolean isOutputExceptionCount();
    
    /**
     * �Ɩ��t���[�Ăяo���񐔁i�G���[�����j���o�͂��邩�ǂ�����ݒ肷��B
     * �f�t�H���g��false�B
     *
     * @param isOutput �o�͂���ꍇ��true
     */
    public void setOutputErrorCount(boolean isOutput);
    
    /**
     * �Ɩ��t���[�Ăяo���񐔁i�G���[�����j���o�͂��邩�ǂ����𔻒肷��B
     *
     * @return true�̏ꍇ�͏o�͂���
     */
    public boolean isOutputErrorCount();
    
    /**
     * �Ɩ��t���[�Ăяo���ŏI�������o�͂��邩�ǂ�����ݒ肷��B
     * �f�t�H���g��false�B
     *
     * @param isOutput �o�͂���ꍇ��true
     */
    public void setOutputLastTime(boolean isOutput);
    
    /**
     * �Ɩ��t���[�Ăяo���ŏI�������o�͂��邩�ǂ����𔻒肷��B
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
    public void setOutputBestJournalSize(boolean isOutput);
    
    /**
     * �ō��������Ԃ��o�͂��邩�ǂ����𔻒肷��B
     *
     * @return true�̏ꍇ�͏o�͂���
     */
    public boolean isOutputBestJournalSize();
    
    /**
     * �ō������������o�͂��邩�ǂ�����ݒ肷��B
     * �f�t�H���g��false�B
     *
     * @param isOutput �o�͂���ꍇ��true
     */
    public void setOutputBestJournalSizeTime(boolean isOutput);
    
    /**
     * �ō������������o�͂��邩�ǂ����𔻒肷��B
     *
     * @return true�̏ꍇ�͏o�͂���
     */
    public boolean isOutputBestJournalSizeTime();
    
    /**
     * �ŒᏈ�����Ԃ��o�͂��邩�ǂ�����ݒ肷��B
     * �f�t�H���g��true�B
     *
     * @param isOutput �o�͂���ꍇ��true
     */
    public void setOutputWorstJournalSize(boolean isOutput);
    
    /**
     * �ŒᏈ�����Ԃ��o�͂��邩�ǂ����𔻒肷��B
     *
     * @return true�̏ꍇ�͏o�͂���
     */
    public boolean isOutputWorstJournalSize();
    
    /**
     * �ŒᏈ���������o�͂��邩�ǂ�����ݒ肷��B
     * �f�t�H���g��false�B
     *
     * @param isOutput �o�͂���ꍇ��true
     */
    public void setOutputWorstJournalSizeTime(boolean isOutput);
    
    /**
     * �ŒᏈ���������o�͂��邩�ǂ����𔻒肷��B
     *
     * @return true�̏ꍇ�͏o�͂���
     */
    public boolean isOutputWorstJournalSizeTime();
    
    /**
     * ���Ϗ������Ԃ��o�͂��邩�ǂ�����ݒ肷��B
     * �f�t�H���g��true�B
     *
     * @param isOutput �o�͂���ꍇ��true
     */
    public void setOutputAverageJournalSize(boolean isOutput);
    
    /**
     * ���Ϗ������Ԃ��o�͂��邩�ǂ����𔻒肷��B
     *
     * @return true�̏ꍇ�͏o�͂���
     */
    public boolean isOutputAverageJournalSize();
}