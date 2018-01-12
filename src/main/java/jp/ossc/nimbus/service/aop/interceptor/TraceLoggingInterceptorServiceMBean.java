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

/**
 * {@link TraceLoggingInterceptorService}��MBean�C���^�t�F�[�X�B<p>
 * 
 * @author M.Takata
 * @see TraceLoggingInterceptorService
 */
public interface TraceLoggingInterceptorServiceMBean extends ServiceBaseMBean{
    
    public static final String DEFAULT_TRACE_REQUEST_MESSAGE_ID  = "TLIS_00001";
    public static final String DEFAULT_TRACE_RESPONSE_MESSAGE_ID = "TLIS_00002";
    
    /**
     * �g���[�X���O�o�͂��s�����ǂ�����ݒ肷��B<p>
     * �f�t�H���g�ł́Atrue�B
     *
     * @param enable �g���[�X���O�o�͂��s���ꍇtrue
     * @see #isEnabled()
     */
    public void setEnabled(boolean enable);
    
    /**
     * �g���[�X���O�o�͂��s�����ǂ����𔻒肷��B<p>
     *
     * @return �g���[�X���O�o�͂��s���ꍇtrue
     * @see #setEnabled(boolean)
     */
    public boolean isEnabled();
    
    /**
     * �Ăяo�����ɏo�͂��郍�O�̃��b�Z�[�WID��ݒ肷��B<p>
     *
     * @param id �o�͂��郍�O�̃��b�Z�[�WID
     */
    public void setRequestMessageId(String id);
    
    /**
     * �Ăяo�����ɏo�͂��郍�O�̃��b�Z�[�WID���擾����B<p>
     *
     * @return �o�͂��郍�O�̃��b�Z�[�WID
     */
    public String getRequestMessageId();
    
    /**
     * �������ɏo�͂��郍�O�̃��b�Z�[�WID��ݒ肷��B<p>
     *
     * @param id �o�͂��郍�O�̃��b�Z�[�WID
     */
    public void setResponseMessageId(String id);
    
    /**
     * �������ɏo�͂��郍�O�̃��b�Z�[�WID���擾����B<p>
     *
     * @return �o�͂��郍�O�̃��b�Z�[�WID
     */
    public String getResponseMessageId();
    
    /**
     * �Ăяo�����Ƀ��O���o�͂��邩�ǂ�����ݒ肷��B<p>
     * �f�t�H���g�́Atrue�ŏo�͂���B<br>
     *
     * @param isOutput �o�͂���ꍇtrue
     */
    public void setOutputRequestLog(boolean isOutput);
    
    /**
     * �Ăяo�����Ƀ��O���o�͂��邩�ǂ����𔻒肷��B<p>
     *
     * @return true�̏ꍇ�A�o�͂���
     */
    public boolean  isOutputRequestLog();
    
    /**
     * �������Ƀ��O���o�͂��邩�ǂ�����ݒ肷��B<p>
     * �f�t�H���g�́Afalse�ŏo�͂��Ȃ��B<br>
     *
     * @param isOutput �o�͂���ꍇtrue
     */
    public void setOutputResponseLog(boolean isOutput);
    
    /**
     * �������Ƀ��O���o�͂��邩�ǂ����𔻒肷��B<p>
     *
     * @return true�̏ꍇ�A�o�͂���
     */
    public boolean  isOutputResponseLog();
    
    /**
     * �Ăяo�����̃��O�o�͂ŌĂяo���Ώۂ��o�͂��邩�ǂ�����ݒ肷��B<p>
     * �f�t�H���g�́Atrue�ŏo�͂���B<br>
     *
     * @param isOutput �o�͂���ꍇtrue
     */
    public void setOutputTarget(boolean isOutput);
    
    /**
     * �Ăяo�����̃��O�o�͂ŌĂяo���Ώۂ��o�͂��邩�ǂ����𔻒肷��B<p>
     *
     * @return true�̏ꍇ�A�o�͂���
     */
    public boolean  isOutputTarget();
    
    /**
     * �Ăяo�����̃��O�o�͂ŌĂяo���Ώۂ���v���p�e�B���w�肵�ďo�͂���悤�ɐݒ肷��B<p>
     *
     * @param props �o�͂���Ώۂ̃v���p�e�B�z��
     */
    public void setOutputTargetProperties(String[] props);
    
    /**
     * �Ăяo�����̃��O�o�͂ŌĂяo���Ώۂ���v���p�e�B���w�肵�ďo�͂���悤�Ɏ擾����B<p>
     *
     * @return �o�͂���Ώۂ̃v���p�e�B�z��
     */
    public String[] getOutputTargetProperties();
    
    /**
     * �Ăяo�����̃��O�o�͂ŌĂяo�����\�b�h���o�͂��邩�ǂ�����ݒ肷��B<p>
     * �f�t�H���g�́Atrue�ŏo�͂���B<br>
     *
     * @param isOutput �o�͂���ꍇtrue
     */
    public void setOutputMethod(boolean isOutput);
    
    /**
     * �Ăяo�����̃��O�o�͂ŌĂяo�����\�b�h���o�͂��邩�ǂ����𔻒肷��B<p>
     *
     * @return true�̏ꍇ�A�o�͂���
     */
    public boolean  isOutputMethod();
    
    /**
     * �Ăяo�����̃��O�o�͂ŌĂяo�����\�b�h�������o�͂��邩�ǂ�����ݒ肷��B<p>
     * �f�t�H���g�́Afalse�ŏo�͂��Ȃ��B<br>
     *
     * @param isOutput �o�͂���ꍇtrue
     */
    public void setOutputParameter(boolean isOutput);
    
    /**
     * �Ăяo�����̃��O�o�͂ŌĂяo�����\�b�h�������o�͂��邩�ǂ����𔻒肷��B<p>
     *
     * @return true�̏ꍇ�A�o�͂���
     */
    public boolean  isOutputParameter();
    
    /**
     * �Ăяo�����̃��O�o�͂ŌĂяo�����\�b�h��������v���p�e�B���w�肵�ďo�͂���悤�ɐݒ肷��B<p>
     *
     * @param props �o�͂��郁�\�b�h�����̃v���p�e�B�z��
     */
    public void setOutputParameterProperties(String[] props);
    
    /**
     * �Ăяo�����̃��O�o�͂ŌĂяo�����\�b�h��������v���p�e�B���w�肵�ďo�͂���悤�Ɏ擾����B<p>
     *
     * @return �o�͂��郁�\�b�h�����̃v���p�e�B�z��
     */
    public String[] getOutputParameterProperties();
    
    /**
     * �Ăяo�����̃��O�o�͂ŌĂяo���X�^�b�N���o�͂��邩�ǂ�����ݒ肷��B<p>
     * �f�t�H���g�́Afalse�ŏo�͂��Ȃ��B<br>
     *
     * @param isOutput �o�͂���ꍇtrue
     */
    public void setOutputCallStackTrace(boolean isOutput);
    
    /**
     * �Ăяo�����̃��O�o�͂ŌĂяo�����\�b�h�������o�͂��邩�ǂ����𔻒肷��B<p>
     *
     * @return true�̏ꍇ�A�o�͂���
     */
    public boolean  isOutputCallStackTrace();
    
    /**
     * �������̃��O�o�͂Ŗ߂�l���o�͂��邩�ǂ�����ݒ肷��B<p>
     * �f�t�H���g�́Atrue�ŏo�͂���B<br>
     *
     * @param isOutput �o�͂���ꍇtrue
     */
    public void setOutputReturn(boolean isOutput);
    
    /**
     * �������̃��O�o�͂Ŗ߂�l���o�͂��邩�ǂ����𔻒肷��B<p>
     *
     * @return true�̏ꍇ�A�o�͂���
     */
    public boolean  isOutputReturn();
    
    /**
     * �������̃��O�o�͂ŏ�������[ms]���o�͂��邩�ǂ�����ݒ肷��B<p>
     * �f�t�H���g�́Afalse�ŏo�͂��Ȃ��B<br>
     *
     * @param isOutput �o�͂���ꍇtrue
     */
    public void setOutputPerformance(boolean isOutput);
    
    /**
     * �������̃��O�o�͂ŏ�������[ms]���o�͂��邩�ǂ����𔻒肷��B<p>
     *
     * @return true�̏ꍇ�A�o�͂���
     */
    public boolean  isOutputPerformance();
    
    /**
     * �������̃��O�o�͂ŌĂяo���Ώۂ��o�͂��邩�ǂ�����ݒ肷��B<p>
     * �f�t�H���g�́Afalse�ŏo�͂��Ȃ��B<br>
     *
     * @param isOutput �o�͂���ꍇtrue
     */
    public void setOutputTargetOnResponse(boolean isOutput);
    
    /**
     * �������̃��O�o�͂ŌĂяo���Ώۂ��o�͂��邩�ǂ����𔻒肷��B<p>
     *
     * @return true�̏ꍇ�A�o�͂���
     */
    public boolean  isOutputTargetOnResponse();
    
    /**
     * �������̃��O�o�͂ŌĂяo�����\�b�h���o�͂��邩�ǂ�����ݒ肷��B<p>
     * �f�t�H���g�́Afalse�ŏo�͂��Ȃ��B<br>
     *
     * @param isOutput �o�͂���ꍇtrue
     */
    public void setOutputMethodOnResponse(boolean isOutput);
    
    /**
     * �������̃��O�o�͂ŌĂяo�����\�b�h���o�͂��邩�ǂ����𔻒肷��B<p>
     *
     * @return true�̏ꍇ�A�o�͂���
     */
    public boolean  isOutputMethodOnResponse();
    
    /**
     * �������̃��O�o�͂ŌĂяo�����\�b�h�������o�͂��邩�ǂ�����ݒ肷��B<p>
     * �f�t�H���g�́Afalse�ŏo�͂��Ȃ��B<br>
     *
     * @param isOutput �o�͂���ꍇtrue
     */
    public void setOutputParameterOnResponse(boolean isOutput);
    
    /**
     * �������̃��O�o�͂ŌĂяo�����\�b�h�������o�͂��邩�ǂ����𔻒肷��B<p>
     *
     * @return true�̏ꍇ�A�o�͂���
     */
    public boolean  isOutputParameterOnResponse();
    
    /**
     * �Ăяo�����̃��O�o�͂Ŗ߂�l����v���p�e�B���w�肵�ďo�͂���悤�ɐݒ肷��B<p>
     *
     * @param props �o�͂���߂�l�̃v���p�e�B�z��
     */
    public void setOutputReturnProperties(String[] props);
    
    /**
     * �Ăяo�����̃��O�o�͂Ŗ߂�l����v���p�e�B���w�肵�ďo�͂���悤�Ɏ擾����B<p>
     *
     * @return �o�͂���߂�l�̃v���p�e�B�z��
     */
    public String[] getOutputReturnProperties();
    
    /**
     * �������̃��O�o�͂Ŕ���������O���o�͂��邩�ǂ�����ݒ肷��B<p>
     * �f�t�H���g�́Afalse�ŏo�͂��Ȃ��B<br>
     *
     * @param isOutput �o�͂���ꍇtrue
     */
    public void setOutputThrowable(boolean isOutput);
    
    /**
     * �������̃��O�o�͂Ŕ���������O���o�͂��邩�ǂ����𔻒肷��B<p>
     *
     * @return true�̏ꍇ�A�o�͂���
     */
    public boolean  isOutputThrowable();
    
    /**
     * �g���[�X���O�ɕt�^����A�Ԃ𔭔Ԃ���{@link jp.ossc.nimbus.service.sequence.Sequence Sequence}�T�[�r�X�̃T�[�r�X����ݒ肷��B<p>
     *
     * @param name Sequence�T�[�r�X�̃T�[�r�X��
     */
    public void setSequenceServiceName(ServiceName name);
    
    /**
     * �g���[�X���O�ɕt�^����A�Ԃ𔭔Ԃ���{@link jp.ossc.nimbus.service.sequence.Sequence Sequence}�T�[�r�X�̃T�[�r�X�����擾����B<p>
     *
     * @return Sequence�T�[�r�X�̃T�[�r�X��
     */
    public ServiceName getSequenceServiceName();
}