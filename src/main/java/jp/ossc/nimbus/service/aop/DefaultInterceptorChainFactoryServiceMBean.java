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
package jp.ossc.nimbus.service.aop;

import java.util.Map;

import jp.ossc.nimbus.core.*;

/**
 * {@link DefaultInterceptorChainFactoryService}��MBean�C���^�t�F�[�X<p>
 * 
 * @author M.Takata
 * @see DefaultInterceptorChainFactoryService
 */
public interface DefaultInterceptorChainFactoryServiceMBean
 extends ServiceBaseMBean{
    
    /**
     * �f�t�H���g�̓��t�t�H�[�}�b�g�B<p>
     */
    public static final String DEFAULT_DATE_FORMAT = "HH:mm:ss.SSS";
    
    /**
     * {@link InterceptorChainFactory#getInterceptorChain(Object)}�̈����Ŏw�肷��L�[������Ƃ��Đ��K�\����L���ɂ��邩�ǂ�����ݒ肷��B<p>
     * �f�t�H���g�ł́Afalse�B<br>
     *
     * @param isEnable ���K�\����L���ɂ���ꍇtrue
     */
    public void setRegexEnabled(boolean isEnable);
    
    /**
     * {@link InterceptorChainFactory#getInterceptorChain(Object)}�̈����Ŏw�肷��L�[������Ƃ��Đ��K�\����L���ɂ��邩�ǂ����𔻒肷��B<p>
     *
     * @return true�̏ꍇ�A���K�\����L���ɂ���
     */
    public boolean isRegexEnabled();
    
    /**
     * ���K�\����r���s���ꍇ�Ɏg�p����}�b�`�t���O��ݒ肷��B<p>
     * �A���A{@link #isRegexEnabled()}��true�̏ꍇ�̂ݗL���ł���B<br>
     * �f�t�H���g�́A0�B<br>
     *
     * @param flag �}�b�`�t���O
     * @see java.util.regex.Pattern#CANON_EQ
     * @see java.util.regex.Pattern#CASE_INSENSITIVE
     * @see java.util.regex.Pattern#DOTALL
     * @see java.util.regex.Pattern#MULTILINE
     * @see java.util.regex.Pattern#UNICODE_CASE
     * @see java.util.regex.Pattern#UNIX_LINES
     */
    public void setRegexMatchFlag(int flag);
    
    /**
     * ���K�\����r���s���ꍇ�Ɏg�p����}�b�`�t���O���擾����B<p>
     *
     * @return �}�b�`�t���O
     */
    public int getRegexMatchFlag();
    
    /**
     * �L�[�ɊY������{@link InterceptorChainList}�T�[�r�X����ݒ肷��B<p>
     *
     * @param mapping �L�[�������InterceptorChainList�T�[�r�X���̃}�b�s���O�B�L�[������=InterceptorChainList�T�[�r�X���ŕ����w�肷��
     */
    public void setInterceptorChainListMapping(Map mapping);
    
    /**
     * �L�[�ɊY������{@link InterceptorChainList}�T�[�r�X�����擾����B<p>
     *
     * @return �L�[�������InterceptorChainList�T�[�r�X���̃}�b�s���O
     */
    public Map getInterceptorChainListMapping();
    
    /**
     * �L�[�ɊY������{@link Interceptor}�T�[�r�X����ݒ肷��B<p>
     * �����L�[�ɊY������{@link #setInterceptorChainListMapping(Map)}�̐ݒ肪����ꍇ�́A�����炪�D�悳���B<br>
     *
     * @param mapping �L�[�������Interceptor�T�[�r�X���̃}�b�s���O�B�L�[������=Interceptor�T�[�r�X���ŕ����w�肷��
     */
    public void setInterceptorMapping(Map mapping);
    
    /**
     * �L�[�ɊY������{@link Interceptor}�T�[�r�X�����擾����B<p>
     *
     * @return �L�[�������Interceptor�T�[�r�X���̃}�b�s���O
     */
    public Map getInterceptorMapping();
    
    /**
     * {@link #getInterceptorChainListMapping()}�ɊY������{@link InterceptorChainList}�T�[�r�X�̃}�b�s���O�����݂��Ȃ��ꍇ�Ɏg�p����InterceptorChainList�T�[�r�X�̃T�[�r�X����ݒ肷��B<p>
     *
     * @param name InterceptorChainList�T�[�r�X�̃T�[�r�X��
     */
    public void setDefaultInterceptorChainListServiceName(ServiceName name);
    
    /**
     * {@link #getInterceptorChainListMapping()}�ɊY������{@link InterceptorChainList}�T�[�r�X�̃}�b�s���O�����݂��Ȃ��ꍇ�Ɏg�p����InterceptorChainList�T�[�r�X�̃T�[�r�X�����擾����B<p>
     *
     * @return InterceptorChainList�T�[�r�X�̃T�[�r�X��
     */
    public ServiceName getDefaultInterceptorChainListServiceName();
    
    /**
     * �L�[�ɊY������{@link Invoker}�T�[�r�X����ݒ肷��B<p>
     *
     * @param mapping �L�[�������Invoker�T�[�r�X���̃}�b�s���O�B�L�[������=Invoker�T�[�r�X���ŕ����w�肷��
     */
    public void setInvokerMapping(Map mapping);
    
    /**
     * �L�[�ɊY������{@link Invoker}�T�[�r�X�����擾����B<p>
     *
     * @return �L�[�������Invoker�T�[�r�X���̃}�b�s���O
     */
    public Map getInvokerMapping();
    
    /**
     * {@link #getInvokerMapping()}�ɊY������{@link Invoker}�T�[�r�X�̃}�b�s���O�����݂��Ȃ��ꍇ�Ɏg�p����Invoker�T�[�r�X�̃T�[�r�X����ݒ肷��B<p>
     * �w�肵�Ȃ��ꍇ�́A{@link jp.ossc.nimbus.service.aop.invoker.MethodReflectionCallInvokerService MethodReflectionCallInvokerService}���g�p�����B<br>
     *
     * @param name Invoker�T�[�r�X�̃T�[�r�X��
     */
    public void setDefaultInvokerServiceName(ServiceName name);
    
    /**
     * {@link #getInvokerMapping()}�ɊY������{@link Invoker}�T�[�r�X�̃}�b�s���O�����݂��Ȃ��ꍇ�Ɏg�p����Invoker�T�[�r�X�̃T�[�r�X�����擾����B<p>
     *
     * @return Invoker�T�[�r�X�̃T�[�r�X��
     */
    public ServiceName getDefaultInvokerServiceName();
    
    /**
     * {@link InterceptorChainFactory#getInterceptorChain(Object)}�̖߂�l���L���b�V������{@link jp.ossc.nimbus.service.cache.CacheMap CacheMap}�T�[�r�X�̃T�[�r�X����ݒ肷��B<p>
     * {@link DefaultInterceptorChainFactoryService}����������{@link InterceptorChain}�́A{@link DefaultThreadLocalInterceptorChain}�Ȃ̂ŁA�X���b�h�P�ʂł̍ė��p���\�ł���B<br>
     * ���̑������w�肵�Ȃ��ꍇ�́A�L���b�V�������ɖ��񐶐�����B<br>
     *
     * @param name CacheMap�T�[�r�X�̃T�[�r�X��
     */
    public void setInterceptorChainCacheMapServiceName(ServiceName name);
    
    /**
     * {@link InterceptorChainFactory#getInterceptorChain(Object)}�̖߂�l���L���b�V������{@link jp.ossc.nimbus.service.cache.CacheMap CacheMap}�T�[�r�X�̃T�[�r�X�����擾����B<p>
     *
     * @return CacheMap�T�[�r�X�̃T�[�r�X��
     */
    public ServiceName getInterceptorChainCacheMapServiceName();
    
    /**
     * {@link InterceptorChain}�̎����N���X�Ƃ��āA{@link DefaultThreadLocalInterceptorChain}���g�p���邩�ǂ�����ݒ肷��B<p>
     * false�̏ꍇ�́A{@link DefaultInterceptorChain}���g�p����B
     *
     * @param isUse �g�p����ꍇtrue
     */
    public void setUseThreadLocalInterceptorChain(boolean isUse);
    
    /**
     * {@link InterceptorChain}�̎����N���X�Ƃ��āA{@link DefaultThreadLocalInterceptorChain}���g�p���邩�ǂ����𔻒肷��B<p>
     *
     * @return true�̏ꍇ�A�g�p����
     */
    public boolean isUseThreadLocalInterceptorChain();
    
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
     * �f�t�H���g�ł́Afalse�B
     *
     * @param isGet ���g���N�X�擾���s���ꍇtrue
     */
    public void setGetMetrics(boolean isGet);
    
    /**
     * ���g���N�X�擾���s�����ǂ����𔻒肷��B<p>
     *
     * @return ���g���N�X�擾���s���ꍇtrue
     */
    public boolean isGetMetrics();
    
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
     * ���g���N�X�ɏo�͂��鎞���̃t�H�[�}�b�g��ݒ肷��B<p>
     *
     * @param format ���t�t�H�[�}�b�g
     */
    public void setDateFormat(String format);
    
    /**
     * ���g���N�X�ɏo�͂��鎞���̃t�H�[�}�b�g���擾����B<p>
     *
     * @return ���t�t�H�[�}�b�g
     */
    public String getDateFormat();
    
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
     * �Ăяo���񐔁i���퉞���j���o�͂��邩�ǂ�����ݒ肷��B
     * �f�t�H���g��true�B
     *
     * @param isOutput �o�͂���ꍇ��true
     */
    public void setOutputCount(boolean isOutput);
    
    /**
     * �Ăяo���񐔁i���퉞���j���o�͂��邩�ǂ����𔻒肷��B
     *
     * @return true�̏ꍇ�͏o�͂���
     */
    public boolean isOutputCount();
    
    /**
     * �Ăяo���񐔁i��O�����j���o�͂��邩�ǂ�����ݒ肷��B
     * �f�t�H���g��false�B
     *
     * @param isOutput �o�͂���ꍇ��true
     */
    public void setOutputExceptionCount(boolean isOutput);
    
    /**
     * �Ăяo���񐔁i��O�����j���o�͂��邩�ǂ����𔻒肷��B
     *
     * @return true�̏ꍇ�͏o�͂���
     */
    public boolean isOutputExceptionCount();
    
    /**
     * �Ăяo���񐔁i�G���[�����j���o�͂��邩�ǂ�����ݒ肷��B
     * �f�t�H���g��false�B
     *
     * @param isOutput �o�͂���ꍇ��true
     */
    public void setOutputErrorCount(boolean isOutput);
    
    /**
     * �Ăяo���񐔁i�G���[�����j���o�͂��邩�ǂ����𔻒肷��B
     *
     * @return true�̏ꍇ�͏o�͂���
     */
    public boolean isOutputErrorCount();
    
    /**
     * �Ăяo���ŏI�������o�͂��邩�ǂ�����ݒ肷��B
     * �f�t�H���g��false�B
     *
     * @param isOutput �o�͂���ꍇ��true
     */
    public void setOutputLastTime(boolean isOutput);
    
    /**
     * �Ăяo���ŏI�������o�͂��邩�ǂ����𔻒肷��B
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
}