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
import jp.ossc.nimbus.service.message.*;
import jp.ossc.nimbus.service.queue.*;
import jp.ossc.nimbus.service.context.*;

/**
 * {@link LogService}�T�[�r�XMBean�C���^�t�F�[�X�B<p>
 *
 * @author Y.Tokuda
 */
public interface LogServiceMBean extends ServiceBaseMBean {
    
    /** debug���\�b�h�J�e�S���� */
    public static final String DEBUG_METHOD_CATEGORY
         = "jp.ossc.nimbus.service.log.DEBUG_METHOD_CATEGORY";
    /** debug���\�b�h�J�e�S���̗D��x�͈͂̍ŏ��l */
    public static final int DEBUG_METHOD_CATEGORY_PRIORITY_MIN = -1;
    /** debug���\�b�h�J�e�S���̗D��x�͈͂̍ő�l */
    public static final int DEBUG_METHOD_CATEGORY_PRIORITY_MAX = -1;
    /** debug���\�b�h�J�e�S���̂̏o�̓��x�� */
    public static final String DEBUG_METHOD_CATEGORY_LABEL = "DEBUG";
    
    /** �V�X�e��DEBUG�J�e�S���� */
    public static final String SYSTEM_DEBUG_CATEGORY
         = "jp.ossc.nimbus.service.log.SYSTEM_DEBUG_CATEGORY";
    /** �V�X�e��DEBUG�J�e�S���̗D��x�͈͂̍ŏ��l */
    public static final int SYSTEM_DEBUG_CATEGORY_PRIORITY_MIN = 0;
    /** �V�X�e��DEBUG�J�e�S���̗D��x�͈͂̍ő�l */
    public static final int SYSTEM_DEBUG_CATEGORY_PRIORITY_MAX = 49;
    /** �V�X�e��DEBUG�J�e�S���̏o�̓��x�� */
    public static final String SYSTEM_DEBUG_CATEGORY_LABEL = "SYSTEM_DEBUG";
    
    /** �V�X�e��INFO�J�e�S���� */
    public static final String SYSTEM_INFO_CATEGORY
         = "jp.ossc.nimbus.service.log.SYSTEM_INFO_CATEGORY";
    /** �V�X�e��INFO�J�e�S���̗D��x�͈͂̍ŏ��l */
    public static final int SYSTEM_INFO_CATEGORY_PRIORITY_MIN = 50;
    /** �V�X�e��INFO�J�e�S���̗D��x�͈͂̍ő�l */
    public static final int SYSTEM_INFO_CATEGORY_PRIORITY_MAX = 99;
    /** �V�X�e��DEBUG�J�e�S���̏o�̓��x�� */
    public static final String SYSTEM_INFO_CATEGORY_LABEL = "SYSTEM_INFO";
    
    /** �V�X�e��WARN�J�e�S���� */
    public static final String SYSTEM_WARN_CATEGORY
         = "jp.ossc.nimbus.service.log.SYSTEM_WARN_CATEGORY";
    /** �V�X�e��WARN�J�e�S���̗D��x�͈͂̍ŏ��l */
    public static final int SYSTEM_WARN_CATEGORY_PRIORITY_MIN = 100;
    /** �V�X�e��WARN�J�e�S���̗D��x�͈͂̍ő�l */
    public static final int SYSTEM_WARN_CATEGORY_PRIORITY_MAX = 149;
    /** �V�X�e��WARN�J�e�S���̏o�̓��x�� */
    public static final String SYSTEM_WARN_CATEGORY_LABEL = "SYSTEM_WARN";
    
    /** �V�X�e��ERROR�J�e�S���� */
    public static final String SYSTEM_ERROR_CATEGORY
         = "jp.ossc.nimbus.service.log.SYSTEM_ERROR_CATEGORY";
    /** �V�X�e��ERROR�J�e�S���̗D��x�͈͂̍ŏ��l */
    public static final int SYSTEM_ERROR_CATEGORY_PRIORITY_MIN = 150;
    /** �V�X�e��ERROR�J�e�S���̗D��x�͈͂̍ő�l */
    public static final int SYSTEM_ERROR_CATEGORY_PRIORITY_MAX = 199;
    /** �V�X�e��ERROR�J�e�S���̏o�̓��x�� */
    public static final String SYSTEM_ERROR_CATEGORY_LABEL = "SYSTEM_ERROR";
    
    /** �V�X�e��FATAL�J�e�S���� */
    public static final String SYSTEM_FATAL_CATEGORY
         = "jp.ossc.nimbus.service.log.SYSTEM_FATAL_CATEGORY";
    /** �V�X�e��FATAL�J�e�S���̗D��x�͈͂̍ŏ��l */
    public static final int SYSTEM_FATAL_CATEGORY_PRIORITY_MIN = 200;
    /** �V�X�e��FATAL�J�e�S���̗D��x�͈͂̍ő�l */
    public static final int SYSTEM_FATAL_CATEGORY_PRIORITY_MAX = 249;
    /** �V�X�e��FATAL�J�e�S���̏o�̓��x�� */
    public static final String SYSTEM_FATAL_CATEGORY_LABEL = "SYSTEM_FATAL";
    
    /** �J�e�S����\���o�̓t�H�[�}�b�g�̃L�[ */
    public static final String FORMAT_CATEGORY_KEY = "CATEGORY";
    /** �R�[�h��\���o�̓t�H�[�}�b�g�̃L�[ */
    public static final String FORMAT_CODE_KEY = "CODE";
    /** ���t��\���o�̓t�H�[�}�b�g�̃L�[ */
    public static final String FORMAT_DATE_KEY = "DATE";
    /** �D��x��\���o�̓t�H�[�}�b�g�̃L�[ */
    public static final String FORMAT_PRIORITY_KEY = "PRIORITY";
    /** ���b�Z�[�W��\���o�̓t�H�[�}�b�g�̃L�[ */
    public static final String FORMAT_MESSAGE_KEY = "MESSAGE";
    
    /**
     * �f�t�H���g�t�H�[�}�b�g�B<p>
     * "%DATE%,%PRIORITY%,[%CODE%]%MESSAGE%"
     */
    public static final String DEFAULT_FORMAT
         = '%' + FORMAT_DATE_KEY + "%,%" + FORMAT_PRIORITY_KEY + "%,[%"
             + FORMAT_CODE_KEY + "%]%" + FORMAT_MESSAGE_KEY + '%';
    
    /**
     * �o�͂��郍�O�̃J�e�S�����`����{@link LogCategory}�T�[�r�X�̖��O��ݒ肷��B<p>
     * 
     * @param names LogCategory�T�[�r�X���̔z��
     */
    public void setCategoryServiceNames(ServiceName[] names);
    
    /**
     * �o�͂��郍�O�̃J�e�S�����`����{@link LogCategory}�T�[�r�X�̖��O���擾����B<p>
     * 
     * @return LogCategory�T�[�r�X���̔z��
     */
    public ServiceName[] getCategoryServiceNames();
    
    /**
     * �o�͂��郍�O�̃J�e�S�����`����{@link LogCategory}�T�[�r�X��ݒ肷��B<p>
     * 
     * @param categories LogCategory�T�[�r�X�̔z��
     */
    public void setCategoryServices(LogCategory[] categories);
    
    /**
     * �o�͂��郍�O�̃J�e�S�����`����{@link LogCategory}�T�[�r�X���擾����B<p>
     * 
     * @return LogCategory�T�[�r�X�̔z��
     */
    public LogCategory[] getCategoryServices();
    
    /**
     * �o�͂��郍�O�̃J�e�S�����`����{@link LogCategory}�T�[�r�X��ǉ�����B<p>
     * 
     * @param category LogCategory�T�[�r�X
     */
    public void addCategoryService(LogCategory category);
    
    /**
     * �w�肳�ꂽ�J�e�S���������J�e�S�����`����{@link LogCategory}�T�[�r�X���擾����B<p>
     * 
     * @param name �J�e�S����
     * @return LogCategory�T�[�r�X
     */
    public LogCategory getCategoryService(String name);
    
    /**
     * �f�t�H���g��{@link jp.ossc.nimbus.service.writer.MessageWriter MessageWriter}�T�[�r�X����ݒ肷��B<p>
     * �f�t�H���g�̃J�e�S����MessageWriter�T�[�r�X���w�肳��Ă��Ȃ��ꍇ�Ɏg�p����B<br>
     *
     * @param name MessageWriter�T�[�r�X��
     */
    public void setDefaultMessageWriterServiceName(ServiceName name);
    
    /**
     * �f�t�H���g��{@link jp.ossc.nimbus.service.writer.MessageWriter MessageWriter}�T�[�r�X�����擾����B<p>
     * �f�t�H���g�̃J�e�S����MessageWriter�T�[�r�X���w�肳��Ă��Ȃ��ꍇ�Ɏg�p����B<br>
     *
     * @return MessageWriter�T�[�r�X��
     */
    public ServiceName getDefaultMessageWriterServiceName();
    
    /**
     * �f�t�H���g��{@link jp.ossc.nimbus.service.writer.WritableRecordFactory WritableRecordFactory}�T�[�r�X����ݒ肷��B<p>
     * �f�t�H���g�̃J�e�S����WritableRecordFactory�T�[�r�X���w�肳��Ă��Ȃ��ꍇ�Ɏg�p����B<br>
     *
     * @param name WritableRecordFactory�T�[�r�X��
     */
    public void setDefaultWritableRecordFactoryServiceName(ServiceName name);
    
    /**
     * �f�t�H���g��{@link jp.ossc.nimbus.service.writer.WritableRecordFactory WritableRecordFactory}�T�[�r�X�����擾����B<p>
     * �f�t�H���g�̃J�e�S����WritableRecordFactory�T�[�r�X���w�肳��Ă��Ȃ��ꍇ�Ɏg�p����B<br>
     *
     * @return WritableRecordFactory�T�[�r�X��
     */
    public ServiceName getDefaultWritableRecordFactoryServiceName();
    
    /**
     * ���O�o�͂Ɏg�p���郁�b�Z�[�W���Ǘ�����{@link MessageRecordFactory}�T�[�r�X����ݒ肷��B<p>
     * 
     * @param name MessageRecordFactory�T�[�r�X��
     */
    public void setMessageRecordFactoryServiceName(ServiceName name);
    
    /**
     * ���O�o�͂Ɏg�p���郁�b�Z�[�W���Ǘ�����{@link MessageRecordFactory}�T�[�r�X��ݒ肷��B<p>
     * 
     * @param message MessageRecordFactory�T�[�r�X
     */
    public void setMessageRecordFactoryService(MessageRecordFactory message);
    
    /**
     * ���O�o�͂Ɏg�p���郁�b�Z�[�W���Ǘ�����{@link MessageRecordFactory}�T�[�r�X�����擾����B<p>
     * 
     * @return MessageRecordFactory�T�[�r�X��
     */
    public ServiceName getMessageRecordFactoryServiceName();
    
    /**
     * ���O�o�͂Ɏg�p���郁�b�Z�[�W���Ǘ�����{@link MessageRecordFactory}�T�[�r�X���擾����B<p>
     * 
     * @return MessageRecordFactory�T�[�r�X
     */
    public MessageRecordFactory getMessageRecordFactoryService();
    
    /**
     * �˗����ꂽ���O�o�͂���U�L���[�C���O����{@link Queue}�𐶐�����{@link Queue}�T�[�r�X����ݒ肷��B<p>
     * 
     * @param name Queue�T�[�r�X��
     */
    public void setQueueServiceName(ServiceName name);
    
    /**
     * �˗����ꂽ���O�o�͂���U�L���[�C���O����{@link Queue}�T�[�r�X��ݒ肷��B<p>
     * 
     * @param queue Queue�T�[�r�X��
     */
    public void setQueueService(Queue queue);
    
    /**
     * �˗����ꂽ���O�o�͂���U�L���[�C���O����{@link Queue}�T�[�r�X�����擾����B<p>
     * 
     * @return Queue�T�[�r�X��
     */
    public ServiceName getQueueServiceName();
    
    /**
     * �˗����ꂽ���O�o�͂���U�L���[�C���O����{@link Queue}�𐶐�����{@link Queue}�T�[�r�X���擾����B<p>
     * 
     * @return Queue�T�[�r�X
     */
    public Queue getQueueService();
    
    /**
     * ���O�̎��ʏ���ێ�����{@link Context}�T�[�r�X����ݒ肷��B<p>
     * 
     * @param name Context�T�[�r�X��
     */
    public void setContextServiceName(ServiceName name);
    
    /**
     * ���O�̎��ʏ���ێ�����{@link Context}�T�[�r�X��ݒ肷��B<p>
     * 
     * @param context Context�T�[�r�X
     */
    public void setContextService(Context context);
    
    /**
     * ���O�̎��ʏ���ێ�����{@link Context}�T�[�r�X�����擾����B<p>
     * 
     * @return Context�T�[�r�X��
     */
    public ServiceName getContextServiceName();
    
    /**
     * ���O�̎��ʏ���ێ�����{@link Context}�T�[�r�X���擾����B<p>
     * 
     * @return Context�T�[�r�X
     */
    public Context getContextService();
    
    /**
     * {@link Context}�T�[�r�X����擾����t�H�[�}�b�g���̃L�[����ݒ肷��B<p>
     * 
     * @param keys Context�T�[�r�X����擾����t�H�[�}�b�g���̃L�[���z��
     */
    public void setContextFormatKeys(String[] keys);
    
    /**
     * {@link Context}�T�[�r�X����擾����t�H�[�}�b�g���̃L�[����ǉ�����B<p>
     * 
     * @param key Context�T�[�r�X����擾����t�H�[�}�b�g���̃L�[��
     */
    public void addContextFormatKey(String key);
    
    /**
     * {@link Context}�T�[�r�X����擾����t�H�[�}�b�g���̃L�[�����폜����B<p>
     * 
     * @param key Context�T�[�r�X����擾����t�H�[�}�b�g���̃L�[��
     */
    public void removeContextFormatKey(String key);
    
    /**
     * {@link Context}�T�[�r�X����擾����t�H�[�}�b�g���̃L�[����S�č폜����B<p>
     */
    public void clearContextFormatKeys();
    
    /**
     * {@link Context}�T�[�r�X����擾����t�H�[�}�b�g���̃L�[�����擾����B<p>
     * 
     * @return Context�T�[�r�X����擾����t�H�[�}�b�g���̃L�[��
     */
    public String[] getContextFormatKeys();
    
    /**
     * {@link Logger#debug(Object)}���\�b�h�̃��O���o�͂��邩�ǂ�����ݒ肷��B<p>
     * 
     * @param isEnabled �o�͂���ꍇ true
     */
    public void setDebugEnabled(boolean isEnabled);
    
    /**
     * {@link Logger#debug(Object)}���\�b�h�̃��O���o�͂��邩�ǂ����𒲂ׂ�B<p>
     * 
     * @return �o�͂���ꍇ true
     */
    public boolean isDebugEnabled();
    
    /**
     * {@link #SYSTEM_DEBUG_CATEGORY}�J�e�S���̃��O�o�͂��s�����ǂ�����ݒ肷��B<p>
     *
     * @param isEnabled �o�͂���ꍇ true
     */
    public void setSystemDebugEnabled(boolean isEnabled);
    
    /**
     * {@link #SYSTEM_DEBUG_CATEGORY}�J�e�S���̃��O�o�͂��s�����ǂ����𒲂ׂ�B<p>
     *
     * @return �o�͂���ꍇ true
     */
    public boolean isSystemDebugEnabled();
    
    /**
     * {@link #SYSTEM_INFO_CATEGORY}�J�e�S���̃��O�o�͂��s�����ǂ�����ݒ肷��B<p>
     *
     * @param isEnabled �o�͂���ꍇ true
     */
    public void setSystemInfoEnabled(boolean isEnabled);
    
    /**
     * {@link #SYSTEM_INFO_CATEGORY}�J�e�S���̃��O�o�͂��s�����ǂ����𒲂ׂ�B<p>
     *
     * @return �o�͂���ꍇ true
     */
    public boolean isSystemInfoEnabled();
    
    /**
     * {@link #SYSTEM_WARN_CATEGORY}�J�e�S���̃��O�o�͂��s�����ǂ�����ݒ肷��B<p>
     *
     * @param isEnabled �o�͂���ꍇ true
     */
    public void setSystemWarnEnabled(boolean isEnabled);
    
    /**
     * {@link #SYSTEM_WARN_CATEGORY}�J�e�S���̃��O�o�͂��s�����ǂ����𒲂ׂ�B<p>
     *
     * @return �o�͂���ꍇ true
     */
    public boolean isSystemWarnEnabled();
    
    /**
     * {@link #SYSTEM_ERROR_CATEGORY}�J�e�S���̃��O�o�͂��s�����ǂ�����ݒ肷��B<p>
     *
     * @param isEnabled �o�͂���ꍇ true
     */
    public void setSystemErrorEnabled(boolean isEnabled);
    
    /**
     * {@link #SYSTEM_ERROR_CATEGORY}�J�e�S���̃��O�o�͂��s�����ǂ����𒲂ׂ�B<p>
     *
     * @return �o�͂���ꍇ true
     */
    public boolean isSystemErrorEnabled();
    
    /**
     * {@link #SYSTEM_FATAL_CATEGORY}�J�e�S���̃��O�o�͂��s�����ǂ�����ݒ肷��B<p>
     *
     * @param isEnabled �o�͂���ꍇ true
     */
    public void setSystemFatalEnabled(boolean isEnabled);
    
    /**
     * {@link #SYSTEM_FATAL_CATEGORY}�J�e�S���̃��O�o�͂��s�����ǂ����𒲂ׂ�B<p>
     *
     * @return �o�͂���ꍇ true
     */
    public boolean isSystemFatalEnabled();
    
    /**
     * {@link #DEBUG_METHOD_CATEGORY}�J�e�S���̃��O�o�͂��s��{@link jp.ossc.nimbus.service.writer.MessageWriter MessageWriter}�T�[�r�X����ݒ肷��B<p>
     *
     * @param name MessageWriter�T�[�r�X��
     */
    public void setDebugMessageWriterServiceName(ServiceName name);
    
    /**
     * {@link #DEBUG_METHOD_CATEGORY}�J�e�S���̃��O�o�͂��s��{@link jp.ossc.nimbus.service.writer.MessageWriter MessageWriter}�T�[�r�X�����擾����B<p>
     *
     * @return MessageWriter�T�[�r�X��
     */
    public ServiceName getDebugMessageWriterServiceName();
    
    /**
     * {@link #SYSTEM_DEBUG_CATEGORY}�J�e�S���̃��O�o�͂��s��{@link jp.ossc.nimbus.service.writer.MessageWriter MessageWriter}�T�[�r�X����ݒ肷��B<p>
     *
     * @param name MessageWriter�T�[�r�X��
     */
    public void setSystemDebugMessageWriterServiceName(ServiceName name);
    
    /**
     * {@link #SYSTEM_DEBUG_CATEGORY}�J�e�S���̃��O�o�͂��s��{@link jp.ossc.nimbus.service.writer.MessageWriter MessageWriter}�T�[�r�X�����擾����B<p>
     *
     * @return MessageWriter�T�[�r�X��
     */
    public ServiceName getSystemDebugMessageWriterServiceName();
    
    /**
     * {@link #SYSTEM_INFO_CATEGORY}�J�e�S���̃��O�o�͂��s��{@link jp.ossc.nimbus.service.writer.MessageWriter MessageWriter}�T�[�r�X����ݒ肷��B<p>
     *
     * @param name MessageWriter�T�[�r�X��
     */
    public void setSystemInfoMessageWriterServiceName(ServiceName name);
    
    /**
     * {@link #SYSTEM_INFO_CATEGORY}�J�e�S���̃��O�o�͂��s��{@link jp.ossc.nimbus.service.writer.MessageWriter MessageWriter}�T�[�r�X�����擾����B<p>
     *
     * @return MessageWriter�T�[�r�X��
     */
    public ServiceName getSystemInfoMessageWriterServiceName();
    
    /**
     * {@link #SYSTEM_WARN_CATEGORY}�J�e�S���̃��O�o�͂��s��{@link jp.ossc.nimbus.service.writer.MessageWriter MessageWriter}�T�[�r�X����ݒ肷��B<p>
     *
     * @param name MessageWriter�T�[�r�X��
     */
    public void setSystemWarnMessageWriterServiceName(ServiceName name);
    
    /**
     * {@link #SYSTEM_WARN_CATEGORY}�J�e�S���̃��O�o�͂��s��{@link jp.ossc.nimbus.service.writer.MessageWriter MessageWriter}�T�[�r�X�����擾����B<p>
     *
     * @return MessageWriter�T�[�r�X��
     */
    public ServiceName getSystemWarnMessageWriterServiceName();
    
    /**
     * {@link #SYSTEM_ERROR_CATEGORY}�J�e�S���̃��O�o�͂��s��{@link jp.ossc.nimbus.service.writer.MessageWriter MessageWriter}�T�[�r�X����ݒ肷��B<p>
     *
     * @param name MessageWriter�T�[�r�X��
     */
    public void setSystemErrorMessageWriterServiceName(ServiceName name);
    
    /**
     * {@link #SYSTEM_ERROR_CATEGORY}�J�e�S���̃��O�o�͂��s��{@link jp.ossc.nimbus.service.writer.MessageWriter MessageWriter}�T�[�r�X�����擾����B<p>
     *
     * @return MessageWriter�T�[�r�X��
     */
    public ServiceName getSystemErrorMessageWriterServiceName();
    
    /**
     * {@link #SYSTEM_FATAL_CATEGORY}�J�e�S���̃��O�o�͂��s��{@link jp.ossc.nimbus.service.writer.MessageWriter MessageWriter}�T�[�r�X����ݒ肷��B<p>
     *
     * @param name MessageWriter�T�[�r�X��
     */
    public void setSystemFatalMessageWriterServiceName(ServiceName name);
    
    /**
     * {@link #SYSTEM_FATAL_CATEGORY}�J�e�S���̃��O�o�͂��s��{@link jp.ossc.nimbus.service.writer.MessageWriter MessageWriter}�T�[�r�X�����擾����B<p>
     *
     * @return MessageWriter�T�[�r�X��
     */
    public ServiceName getSystemFatalMessageWriterServiceName();
    
    /**
     * {@link #DEBUG_METHOD_CATEGORY}�J�e�S���̃��O�o�̓t�H�[�}�b�g���s��{@link jp.ossc.nimbus.service.writer.WritableRecordFactory WritableRecordFactory}�T�[�r�X����ݒ肷��B<p>
     *
     * @param name WritableRecordFactory�T�[�r�X��
     */
    public void setDebugWritableRecordFactoryServiceName(
        ServiceName name
    );
    
    /**
     * {@link #DEBUG_METHOD_CATEGORY}�J�e�S���̃��O�o�̓t�H�[�}�b�g���s��{@link jp.ossc.nimbus.service.writer.WritableRecordFactory WritableRecordFactory}�T�[�r�X�����擾����B<p>
     *
     * @return WritableRecordFactory�T�[�r�X��
     */
    public ServiceName getDebugWritableRecordFactoryServiceName();
    
    /**
     * {@link #SYSTEM_DEBUG_CATEGORY}�J�e�S���̃��O�o�̓t�H�[�}�b�g���s��{@link jp.ossc.nimbus.service.writer.WritableRecordFactory WritableRecordFactory}�T�[�r�X����ݒ肷��B<p>
     *
     * @param name WritableRecordFactory�T�[�r�X��
     */
    public void setSystemDebugWritableRecordFactoryServiceName(
        ServiceName name
    );
    
    /**
     * {@link #SYSTEM_DEBUG_CATEGORY}�J�e�S���̃��O�o�̓t�H�[�}�b�g���s��{@link jp.ossc.nimbus.service.writer.WritableRecordFactory WritableRecordFactory}�T�[�r�X�����擾����B<p>
     *
     * @return WritableRecordFactory�T�[�r�X��
     */
    public ServiceName getSystemDebugWritableRecordFactoryServiceName();
    
    /**
     * {@link #SYSTEM_INFO_CATEGORY}�J�e�S���̃��O�o�̓t�H�[�}�b�g���s��{@link jp.ossc.nimbus.service.writer.WritableRecordFactory WritableRecordFactory}�T�[�r�X����ݒ肷��B<p>
     *
     * @param name WritableRecordFactory�T�[�r�X��
     */
    public void setSystemInfoWritableRecordFactoryServiceName(ServiceName name);
    
    /**
     * {@link #SYSTEM_INFO_CATEGORY}�J�e�S���̃��O�o�̓t�H�[�}�b�g���s��{@link jp.ossc.nimbus.service.writer.WritableRecordFactory WritableRecordFactory}�T�[�r�X�����擾����B<p>
     *
     * @return WritableRecordFactory�T�[�r�X��
     */
    public ServiceName getSystemInfoWritableRecordFactoryServiceName();
    
    /**
     * {@link #SYSTEM_WARN_CATEGORY}�J�e�S���̃��O�o�̓t�H�[�}�b�g���s��{@link jp.ossc.nimbus.service.writer.WritableRecordFactory WritableRecordFactory}�T�[�r�X����ݒ肷��B<p>
     *
     * @param name WritableRecordFactory�T�[�r�X��
     */
    public void setSystemWarnWritableRecordFactoryServiceName(ServiceName name);
    
    /**
     * {@link #SYSTEM_WARN_CATEGORY}�J�e�S���̃��O�o�̓t�H�[�}�b�g���s��{@link jp.ossc.nimbus.service.writer.WritableRecordFactory WritableRecordFactory}�T�[�r�X�����擾����B<p>
     *
     * @return WritableRecordFactory�T�[�r�X��
     */
    public ServiceName getSystemWarnWritableRecordFactoryServiceName();
    
    /**
     * {@link #SYSTEM_ERROR_CATEGORY}�J�e�S���̃��O�o�̓t�H�[�}�b�g���s��{@link jp.ossc.nimbus.service.writer.WritableRecordFactory WritableRecordFactory}�T�[�r�X����ݒ肷��B<p>
     *
     * @param name WritableRecordFactory�T�[�r�X��
     */
    public void setSystemErrorWritableRecordFactoryServiceName(
        ServiceName name
    );
    
    /**
     * {@link #SYSTEM_ERROR_CATEGORY}�J�e�S���̃��O�o�̓t�H�[�}�b�g���s��{@link jp.ossc.nimbus.service.writer.WritableRecordFactory WritableRecordFactory}�T�[�r�X�����擾����B<p>
     *
     * @return WritableRecordFactory�T�[�r�X��
     */
    public ServiceName getSystemErrorWritableRecordFactoryServiceName();
    
    /**
     * {@link #SYSTEM_FATAL_CATEGORY}�J�e�S���̃��O�o�̓t�H�[�}�b�g���s��{@link jp.ossc.nimbus.service.writer.WritableRecordFactory WritableRecordFactory}�T�[�r�X����ݒ肷��B<p>
     *
     * @param name WritableRecordFactory�T�[�r�X��
     */
    public void setSystemFatalWritableRecordFactoryServiceName(
        ServiceName name
    );
    
    /**
     * {@link #SYSTEM_FATAL_CATEGORY}�J�e�S���̃��O�o�̓t�H�[�}�b�g���s��{@link jp.ossc.nimbus.service.writer.WritableRecordFactory WritableRecordFactory}�T�[�r�X�����擾����B<p>
     *
     * @return WritableRecordFactory�T�[�r�X��
     */
    public ServiceName getSystemFatalWritableRecordFactoryServiceName();
    
    /**
     * ���O�o�̓X���b�h���f�[�����X���b�h�ɂ��邩�ǂ�����ݒ肷��B<p>
     * �f�t�H���g�́Atrue�B<br>
     *
     * @param isDaemon �f�[�����X���b�h�ɂ���ꍇtrue
     */
    public void setDaemon(boolean isDaemon);
    
    /**
     * ���O�o�̓X���b�h���f�[�����X���b�h���ǂ����𔻒肷��B<p>
     *
     * @return true�̏ꍇ�́A�f�[�����X���b�h
     */
    public boolean isDaemon();
}
