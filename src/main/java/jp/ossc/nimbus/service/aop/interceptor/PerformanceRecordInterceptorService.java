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

import jp.ossc.nimbus.core.*;
import jp.ossc.nimbus.service.aop.*;
import jp.ossc.nimbus.service.performance.PerformanceRecorder;

/**
 * �p�t�H�[�}���X�L�^�C���^�[�Z�v�^�B<p>
 * ���\�b�h�̌Ăяo���ɑ΂��āA{@link PerformanceRecorder}�Ńp�t�H�[�}���X���L�^����C���^�[�Z�v�^�ł���B<br>
 *
 * @author M.Takata
 */
public class PerformanceRecordInterceptorService extends ServiceBase
 implements Interceptor, PerformanceRecordInterceptorServiceMBean{
    
    private static final long serialVersionUID = -3489905667343158982L;
    
    private boolean isEnabled = true;
    private ServiceName performanceRecorderServiceName;
    private PerformanceRecorder performanceRecorder;
    
    // PerformanceRecordInterceptorServiceMBean��JavaDoc
    public void setEnabled(boolean enable){
        isEnabled = enable;
    }
    // PerformanceRecordInterceptorServiceMBean��JavaDoc
    public boolean isEnabled(){
        return isEnabled;
    }
    
    // PerformanceRecordInterceptorServiceMBean��JavaDoc
    public void setPerformanceRecorderServiceName(ServiceName name){
        performanceRecorderServiceName = name;
    }
    // PerformanceRecordInterceptorServiceMBean��JavaDoc
    public ServiceName getPerformanceRecorderServiceName(){
        return performanceRecorderServiceName;
    }
    
    /**
     * �T�[�r�X�̊J�n�������s���B<p>
     *
     * @exception Exception �J�n�����Ɏ��s�����ꍇ
     */
    public void startService() throws Exception{
        if(performanceRecorderServiceName == null){
            throw new IllegalArgumentException("PerformanceRecorderServiceName is null.");
        }
        performanceRecorder = (PerformanceRecorder)ServiceManagerFactory
            .getServiceObject(performanceRecorderServiceName);
    }
    
    /**
     * ���̃C���^�[�Z�v�^���Ăяo���āA�p�t�H�[�}���X���L�^����B<p>
     * �T�[�r�X���J�n����Ă��Ȃ��ꍇ��{@link #setEnabled(boolean) setEnabled(false)}�ɐݒ肳��Ă���ꍇ�́A�p�t�H�[�}���X���L�^�����Ɏ��̃C���^�[�Z�v�^���Ăяo���B<br>
     *
     * @param context �Ăяo���̃R���e�L�X�g���
     * @param chain ���̃C���^�[�Z�v�^���Ăяo�����߂̃`�F�[��
     * @return �Ăяo�����ʂ̖߂�l
     * @exception Throwable �Ăяo����ŗ�O�����������ꍇ�A�܂��͂��̃C���^�[�Z�v�^�ŔC�ӂ̗�O�����������ꍇ�B�A���A�{���Ăяo����鏈����throw���Ȃ�RuntimeException�ȊO�̗�O��throw���Ă��A�Ăяo�����ɂ͓`�d����Ȃ��B
     */
    public Object invoke(
        InvocationContext context,
        InterceptorChain chain
    ) throws Throwable{
        long start = 0;
        boolean isRecord = getState() == STARTED && isEnabled();
        if(isRecord){
            start = System.currentTimeMillis();
        }
        try{
            return chain.invokeNext(context);
        }finally{
            if(isRecord){
                long end = System.currentTimeMillis();
                performanceRecorder.record(start, end);
            }
        }
    }
}
