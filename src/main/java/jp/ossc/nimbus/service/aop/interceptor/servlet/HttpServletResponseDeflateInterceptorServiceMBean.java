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
package jp.ossc.nimbus.service.aop.interceptor.servlet;

import jp.ossc.nimbus.core.ServiceName;

/**
 * {@link HttpServletResponseDeflateInterceptorService}��MBean�C���^�t�F�[�X�B<p>
 * 
 * @author M.Takata
 * @see HttpServletResponseDeflateInterceptorService
 */
public interface HttpServletResponseDeflateInterceptorServiceMBean
 extends ServletFilterInterceptorServiceMBean{
    
    public void setEnabledContentTypes(String[] contentTypes);
    public String[] getEnabledContentTypes();
    
    public void setDisabledContentTypes(String[] contentTypes);
    public String[] getDisabledContentTypes();
    
    public void setDeflateLength(int length);
    public int getDeflateLength();
    
    /**
     * ���k���Ԃ��L�^����{@link jp.ossc.nimbus.service.performance.PerformanceRecorder PerformanceRecorder}�T�[�r�X�̃T�[�r�X����ݒ肷��B<p>
     *
     * @param name PerformanceRecorder�T�[�r�X�̃T�[�r�X��
     */
    public void setPerformanceRecorderServiceName(ServiceName name);
    
    /**
     * ���k���Ԃ��L�^����{@link jp.ossc.nimbus.service.performance.PerformanceRecorder PerformanceRecorder}�T�[�r�X�̃T�[�r�X�����擾����B<p>
     *
     * @return PerformanceRecorder�T�[�r�X�̃T�[�r�X��
     */
    public ServiceName getPerformanceRecorderServiceName();
    
    /**
     * ���k�O�o�C�g�����L�^����{@link jp.ossc.nimbus.service.performance.PerformanceRecorder PerformanceRecorder}�T�[�r�X�̃T�[�r�X����ݒ肷��B<p>
     *
     * @param name PerformanceRecorder�T�[�r�X�̃T�[�r�X��
     */
    public void setBeforeCompressSizePerformanceRecorderServiceName(ServiceName name);
    
    /**
     * ���k�O�o�C�g�����L�^����{@link jp.ossc.nimbus.service.performance.PerformanceRecorder PerformanceRecorder}�T�[�r�X�̃T�[�r�X�����擾����B<p>
     *
     * @return PerformanceRecorder�T�[�r�X�̃T�[�r�X��
     */
    public ServiceName getBeforeCompressSizePerformanceRecorderServiceName();
    
    /**
     * ���k��o�C�g�����L�^����{@link jp.ossc.nimbus.service.performance.PerformanceRecorder PerformanceRecorder}�T�[�r�X�̃T�[�r�X����ݒ肷��B<p>
     *
     * @param name PerformanceRecorder�T�[�r�X�̃T�[�r�X��
     */
    public void setAfterCompressSizePerformanceRecorderServiceName(ServiceName name);
    
    /**
     * ���k��o�C�g�����L�^����{@link jp.ossc.nimbus.service.performance.PerformanceRecorder PerformanceRecorder}�T�[�r�X�̃T�[�r�X�����擾����B<p>
     *
     * @return PerformanceRecorder�T�[�r�X�̃T�[�r�X��
     */
    public ServiceName getAfterCompressSizePerformanceRecorderServiceName();
    
    /**
     * �������������񐔂��擾����B<p>
     *
     * @return ������
     */
    public long getResponseCount();
    
    /**
     * �������������񐔂̂����A���k�ΏۂƂ����񐔂��擾����B<p>
     *
     * @return ���k�ΏۂƂ�����
     */
    public long getCompressCount();
    
    /**
     * ���k�ΏۂƂ����񐔂̂����A���k�����񐔂��擾����B<p>
     *
     * @return ���k������
     */
    public long getCompressedCount();
    
    /**
     * �������������񐔂̂����A���k�ΏۂƂ����񐔂̔䗦���擾����B<p>
     *
     * @return ���k�ΏۂƂ����񐔂̔䗦
     */
    public double getCompressRate();
    
    /**
     * ���k�ΏۂƂ����񐔂̂����A���k�����񐔂̔䗦���擾����B<p>
     *
     * @return ���k�����񐔂̔䗦
     */
    public double getCompressedRate();
    
    /**
     * ���k�������̕��ψ��k�����擾����B<p>
     *
     * @return ���ψ��k��
     */
    public double getAverageCompressionRate();
}