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
package jp.ossc.nimbus.service.log;

import jp.ossc.nimbus.core.*;

/**
 * {@link DefaultCommonsLogFactoryService}��MBean�C���^�t�F�[�X�B<p>
 * 
 * @author M.Takata
 */
public interface DefaultCommonsLogFactoryServiceMBean extends LogServiceMBean{
    
    public static final String CATEGORY_COMMONS_TRACE
     = "jp.ossc.nimbus.service.log.DefaultCommonsLogService.CATEGORY_COMMONS_TRACE";
    public static final String CATEGORY_COMMONS_DEBUG
     = "jp.ossc.nimbus.service.log.DefaultCommonsLogService.CATEGORY_COMMONS_DEBUG";
    public static final String CATEGORY_COMMONS_INFO
     = "jp.ossc.nimbus.service.log.DefaultCommonsLogService.CATEGORY_COMMONS_INFO";
    public static final String CATEGORY_COMMONS_WARN
     = "jp.ossc.nimbus.service.log.DefaultCommonsLogService.CATEGORY_COMMONS_WARN";
    public static final String CATEGORY_COMMONS_ERROR
     = "jp.ossc.nimbus.service.log.DefaultCommonsLogService.CATEGORY_COMMONS_ERROR";
    public static final String CATEGORY_COMMONS_FATAL
     = "jp.ossc.nimbus.service.log.DefaultCommonsLogService.CATEGORY_COMMONS_FATAL";
    
    public static final int PRIORITY_COMMONS_TRACE_MIN = 0;
    public static final int PRIORITY_COMMONS_TRACE_MAX = 9;
    public static final int PRIORITY_COMMONS_TRACE = 5;
    
    public static final int PRIORITY_COMMONS_DEBUG_MIN = 10;
    public static final int PRIORITY_COMMONS_DEBUG_MAX = 19;
    public static final int PRIORITY_COMMONS_DEBUG = 15;
    
    public static final int PRIORITY_COMMONS_INFO_MIN = 20;
    public static final int PRIORITY_COMMONS_INFO_MAX = 29;
    public static final int PRIORITY_COMMONS_INFO = 25;
    
    public static final int PRIORITY_COMMONS_WARN_MIN = 30;
    public static final int PRIORITY_COMMONS_WARN_MAX = 39;
    public static final int PRIORITY_COMMONS_WARN = 35;
    
    public static final int PRIORITY_COMMONS_ERROR_MIN = 40;
    public static final int PRIORITY_COMMONS_ERROR_MAX = 49;
    public static final int PRIORITY_COMMONS_ERROR = 45;
    
    public static final int PRIORITY_COMMONS_FATAL_MIN = 50;
    public static final int PRIORITY_COMMONS_FATAL_MAX = 59;
    public static final int PRIORITY_COMMONS_FATAL = 55;
    
    public static final String LABEL_COMMONS_TRACE = "TRACE";
    public static final String LABEL_COMMONS_DEBUG = "DEBUG";
    public static final String LABEL_COMMONS_INFO = "INFO";
    public static final String LABEL_COMMONS_WARN = "WARN";
    public static final String LABEL_COMMONS_ERROR = "ERROR";
    public static final String LABEL_COMMONS_FATAL = "FATAL";
    
    /**
     * ���O�T�[�r�X�̃N���C�A���g�����ʂ���L�[��\���o�̓t�H�[�}�b�g�̃L�[�B<p> 
     */
    public static final String FORMAT_CLIENT_KEY = "CLIENT";
    
    /**
     * �f�t�H���g�t�H�[�}�b�g�B<p>
     * "%DATE%,%CLIENT%,%PRIORITY%,%MESSAGE%"
     */
    public static final String DEFAULT_FORMAT
         = '%' + FORMAT_DATE_KEY + "%,%" + FORMAT_CLIENT_KEY + "%,%"
          + FORMAT_PRIORITY_KEY + "%,%" + FORMAT_MESSAGE_KEY + '%';
    
    /**
     * �w�肳�ꂽ���O�N���C�A���g����̃��O�������o�͂���悤�ɐݒ肷��B<p>
     *
     * @param clients {@link org.apache.commons.logging.LogFactory#getLog(String)}�̈����̕�����A�܂���{@link org.apache.commons.logging.LogFactory#getLog(Class)}�̈����̃N���X�̃p�b�P�[�W�����������N���X��
     */
    public void setEnabledClients(String[] clients);
    
    /**
     * �L���ȃ��O�N���C�A���g�̃L�[���z����擾����B<p>
     *
     * @return �L���ȃ��O�N���C�A���g�̃L�[���z��
     */
    public String[] getEnabledClients();
    
    /**
     * {@link org.apache.commons.logging.Log#trace(Object)}�̃��O�o�͂��s�����ǂ�����ݒ肷��B<p>
     *
     * @param isEnabled �o�͂���ꍇ true
     */
    public void setCommonsTraceEnabled(boolean isEnabled);
    
    /**
     * {@link org.apache.commons.logging.Log#trace(Object)}�̃��O�o�͂��s�����ǂ����𒲂ׂ�B<p>
     *
     * @return �o�͂���ꍇ true
     */
    public boolean isCommonsTraceEnabled();
    
    /**
     * {@link org.apache.commons.logging.Log#debug(Object)}�̃��O�o�͂��s�����ǂ�����ݒ肷��B<p>
     *
     * @param isEnabled �o�͂���ꍇ true
     */
    public void setCommonsDebugEnabled(boolean isEnabled);
    
    /**
     * {@link org.apache.commons.logging.Log#debug(Object)}�̃��O�o�͂��s�����ǂ����𒲂ׂ�B<p>
     *
     * @return �o�͂���ꍇ true
     */
    public boolean isCommonsDebugEnabled();
    
    /**
     * {@link org.apache.commons.logging.Log#info(Object)}�̃��O�o�͂��s�����ǂ�����ݒ肷��B<p>
     *
     * @param isEnabled �o�͂���ꍇ true
     */
    public void setCommonsInfoEnabled(boolean isEnabled);
    
    /**
     * {@link org.apache.commons.logging.Log#info(Object)}�̃��O�o�͂��s�����ǂ����𒲂ׂ�B<p>
     *
     * @return �o�͂���ꍇ true
     */
    public boolean isCommonsInfoEnabled();
    
    /**
     * {@link org.apache.commons.logging.Log#warn(Object)}�̃��O�o�͂��s�����ǂ�����ݒ肷��B<p>
     *
     * @param isEnabled �o�͂���ꍇ true
     */
    public void setCommonsWarnEnabled(boolean isEnabled);
    
    /**
     * {@link org.apache.commons.logging.Log#warn(Object)}�̃��O�o�͂��s�����ǂ����𒲂ׂ�B<p>
     *
     * @return �o�͂���ꍇ true
     */
    public boolean isCommonsWarnEnabled();
    
    /**
     * {@link org.apache.commons.logging.Log#error(Object)}�̃��O�o�͂��s�����ǂ�����ݒ肷��B<p>
     *
     * @param isEnabled �o�͂���ꍇ true
     */
    public void setCommonsErrorEnabled(boolean isEnabled);
    
    /**
     * {@link org.apache.commons.logging.Log#error(Object)}�̃��O�o�͂��s�����ǂ����𒲂ׂ�B<p>
     *
     * @return �o�͂���ꍇ true
     */
    public boolean isCommonsErrorEnabled();
    
    /**
     * {@link org.apache.commons.logging.Log#fatal(Object)}�̃��O�o�͂��s�����ǂ�����ݒ肷��B<p>
     *
     * @param isEnabled �o�͂���ꍇ true
     */
    public void setCommonsFatalEnabled(boolean isEnabled);
    
    /**
     * {@link org.apache.commons.logging.Log#fatal(Object)}�̃��O�o�͂��s�����ǂ����𒲂ׂ�B<p>
     *
     * @return �o�͂���ꍇ true
     */
    public boolean isCommonsFatalEnabled();
    
    /**
     * {@link org.apache.commons.logging.Log#trace(Object)}�̃��O�o�͂��s��{@link jp.ossc.nimbus.service.writer.MessageWriter MessageWriter}�T�[�r�X����ݒ肷��B<p>
     *
     * @param name MessageWriter�T�[�r�X��
     */
    public void setCommonsTraceMessageWriterServiceName(ServiceName name);
    
    /**
     * {@link org.apache.commons.logging.Log#trace(Object)}�̃��O�o�͂��s��{@link jp.ossc.nimbus.service.writer.MessageWriter MessageWriter}�T�[�r�X�����擾����B<p>
     *
     * @return MessageWriter�T�[�r�X��
     */
    public ServiceName getCommonsTraceMessageWriterServiceName();
    
    /**
     * {@link org.apache.commons.logging.Log#debug(Object)}�̃��O�o�͂��s��{@link jp.ossc.nimbus.service.writer.MessageWriter MessageWriter}�T�[�r�X����ݒ肷��B<p>
     *
     * @param name MessageWriter�T�[�r�X��
     */
    public void setCommonsDebugMessageWriterServiceName(ServiceName name);
    
    /**
     * {@link org.apache.commons.logging.Log#debug(Object)}�̃��O�o�͂��s��{@link jp.ossc.nimbus.service.writer.MessageWriter MessageWriter}�T�[�r�X�����擾����B<p>
     *
     * @return MessageWriter�T�[�r�X��
     */
    public ServiceName getCommonsDebugMessageWriterServiceName();
    
    /**
     * {@link org.apache.commons.logging.Log#info(Object)}�̃��O�o�͂��s��{@link jp.ossc.nimbus.service.writer.MessageWriter MessageWriter}�T�[�r�X����ݒ肷��B<p>
     *
     * @param name MessageWriter�T�[�r�X��
     */
    public void setCommonsInfoMessageWriterServiceName(ServiceName name);
    
    /**
     * {@link org.apache.commons.logging.Log#info(Object)}�̃��O�o�͂��s��{@link jp.ossc.nimbus.service.writer.MessageWriter MessageWriter}�T�[�r�X�����擾����B<p>
     *
     * @return MessageWriter�T�[�r�X��
     */
    public ServiceName getCommonsInfoMessageWriterServiceName();
    
    /**
     * {@link org.apache.commons.logging.Log#warn(Object)}�̃��O�o�͂��s��{@link jp.ossc.nimbus.service.writer.MessageWriter MessageWriter}�T�[�r�X����ݒ肷��B<p>
     *
     * @param name MessageWriter�T�[�r�X��
     */
    public void setCommonsWarnMessageWriterServiceName(ServiceName name);
    
    /**
     * {@link org.apache.commons.logging.Log#warn(Object)}�̃��O�o�͂��s��{@link jp.ossc.nimbus.service.writer.MessageWriter MessageWriter}�T�[�r�X�����擾����B<p>
     *
     * @return MessageWriter�T�[�r�X��
     */
    public ServiceName getCommonsWarnMessageWriterServiceName();
    
    /**
     * {@link org.apache.commons.logging.Log#error(Object)}�̃��O�o�͂��s��{@link jp.ossc.nimbus.service.writer.MessageWriter MessageWriter}�T�[�r�X����ݒ肷��B<p>
     *
     * @param name MessageWriter�T�[�r�X��
     */
    public void setCommonsErrorMessageWriterServiceName(ServiceName name);
    
    /**
     * {@link org.apache.commons.logging.Log#error(Object)}�̃��O�o�͂��s��{@link jp.ossc.nimbus.service.writer.MessageWriter MessageWriter}�T�[�r�X�����擾����B<p>
     *
     * @return MessageWriter�T�[�r�X��
     */
    public ServiceName getCommonsErrorMessageWriterServiceName();
    
    /**
     * {@link org.apache.commons.logging.Log#fatal(Object)}�̃��O�o�͂��s��{@link jp.ossc.nimbus.service.writer.MessageWriter MessageWriter}�T�[�r�X����ݒ肷��B<p>
     *
     * @param name MessageWriter�T�[�r�X��
     */
    public void setCommonsFatalMessageWriterServiceName(ServiceName name);
    
    /**
     * {@link org.apache.commons.logging.Log#fatal(Object)}�̃��O�o�͂��s��{@link jp.ossc.nimbus.service.writer.MessageWriter MessageWriter}�T�[�r�X�����擾����B<p>
     *
     * @return MessageWriter�T�[�r�X��
     */
    public ServiceName getCommonsFatalMessageWriterServiceName();
    
    /**
     * {@link org.apache.commons.logging.Log#trace(Object)}�̃��O�o�̓t�H�[�}�b�g���s��{@link jp.ossc.nimbus.service.writer.WritableRecordFactory WritableRecordFactory}�T�[�r�X����ݒ肷��B<p>
     *
     * @param name WritableRecordFactory�T�[�r�X��
     */
    public void setCommonsTraceWritableRecordFactoryServiceName(
        ServiceName name
    );
    
    /**
     * {@link org.apache.commons.logging.Log#trace(Object)}�̃��O�o�̓t�H�[�}�b�g���s��{@link jp.ossc.nimbus.service.writer.WritableRecordFactory WritableRecordFactory}�T�[�r�X�����擾����B<p>
     *
     * @return WritableRecordFactory�T�[�r�X��
     */
    public ServiceName getCommonsTraceWritableRecordFactoryServiceName();
    
    /**
     * {@link org.apache.commons.logging.Log#debug(Object)}�̃��O�o�̓t�H�[�}�b�g���s��{@link jp.ossc.nimbus.service.writer.WritableRecordFactory WritableRecordFactory}�T�[�r�X����ݒ肷��B<p>
     *
     * @param name WritableRecordFactory�T�[�r�X��
     */
    public void setCommonsDebugWritableRecordFactoryServiceName(
        ServiceName name
    );
    
    /**
     * {@link org.apache.commons.logging.Log#debug(Object)}�̃��O�o�̓t�H�[�}�b�g���s��{@link jp.ossc.nimbus.service.writer.WritableRecordFactory WritableRecordFactory}�T�[�r�X�����擾����B<p>
     *
     * @return WritableRecordFactory�T�[�r�X��
     */
    public ServiceName getCommonsDebugWritableRecordFactoryServiceName();
    
    /**
     * {@link org.apache.commons.logging.Log#info(Object)}�̃��O�o�̓t�H�[�}�b�g���s��{@link jp.ossc.nimbus.service.writer.WritableRecordFactory WritableRecordFactory}�T�[�r�X����ݒ肷��B<p>
     *
     * @param name WritableRecordFactory�T�[�r�X��
     */
    public void setCommonsInfoWritableRecordFactoryServiceName(
        ServiceName name
    );
    
    /**
     * {@link org.apache.commons.logging.Log#info(Object)}�̃��O�o�̓t�H�[�}�b�g���s��{@link jp.ossc.nimbus.service.writer.WritableRecordFactory WritableRecordFactory}�T�[�r�X�����擾����B<p>
     *
     * @return WritableRecordFactory�T�[�r�X��
     */
    public ServiceName getCommonsInfoWritableRecordFactoryServiceName();
    
    /**
     * {@link org.apache.commons.logging.Log#warn(Object)}�̃��O�o�̓t�H�[�}�b�g���s��{@link jp.ossc.nimbus.service.writer.WritableRecordFactory WritableRecordFactory}�T�[�r�X����ݒ肷��B<p>
     *
     * @param name WritableRecordFactory�T�[�r�X��
     */
    public void setCommonsWarnWritableRecordFactoryServiceName(
        ServiceName name
    );
    
    /**
     * {@link org.apache.commons.logging.Log#warn(Object)}�̃��O�o�̓t�H�[�}�b�g���s��{@link jp.ossc.nimbus.service.writer.WritableRecordFactory WritableRecordFactory}�T�[�r�X�����擾����B<p>
     *
     * @return WritableRecordFactory�T�[�r�X��
     */
    public ServiceName getCommonsWarnWritableRecordFactoryServiceName();
    
    /**
     * {@link org.apache.commons.logging.Log#error(Object)}�̃��O�o�̓t�H�[�}�b�g���s��{@link jp.ossc.nimbus.service.writer.WritableRecordFactory WritableRecordFactory}�T�[�r�X����ݒ肷��B<p>
     *
     * @param name WritableRecordFactory�T�[�r�X��
     */
    public void setCommonsErrorWritableRecordFactoryServiceName(
        ServiceName name
    );
    
    /**
     * {@link org.apache.commons.logging.Log#error(Object)}�̃��O�o�̓t�H�[�}�b�g���s��{@link jp.ossc.nimbus.service.writer.WritableRecordFactory WritableRecordFactory}�T�[�r�X�����擾����B<p>
     *
     * @return WritableRecordFactory�T�[�r�X��
     */
    public ServiceName getCommonsErrorWritableRecordFactoryServiceName();
    
    /**
     * {@link org.apache.commons.logging.Log#fatal(Object)}�̃��O�o�̓t�H�[�}�b�g���s��{@link jp.ossc.nimbus.service.writer.WritableRecordFactory WritableRecordFactory}�T�[�r�X����ݒ肷��B<p>
     *
     * @param name WritableRecordFactory�T�[�r�X��
     */
    public void setCommonsFatalWritableRecordFactoryServiceName(
        ServiceName name
    );
    
    /**
     * {@link org.apache.commons.logging.Log#fatal(Object)}�̃��O�o�̓t�H�[�}�b�g���s��{@link jp.ossc.nimbus.service.writer.WritableRecordFactory WritableRecordFactory}�T�[�r�X�����擾����B<p>
     *
     * @return WritableRecordFactory�T�[�r�X��
     */
    public ServiceName getCommonsFatalWritableRecordFactoryServiceName();
}
