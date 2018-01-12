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
package jp.ossc.nimbus.service.writer.mail;

import java.util.*;

import jp.ossc.nimbus.core.*;

/**
 * {@link MailWriterService}��MBean�C���^�t�F�[�X<p>
 * 
 * @author M.Takata
 * @see MailWriterService
 */
public interface MailWriterServiceMBean extends ServiceBaseMBean{
    
    /**
     * javax.mail.Session��JNDI����lookup���鎞�̃f�t�H���g��JNDI���B<p>
     */
    public static final String DEFAULT_MAIL_SESSION_JNDI_NAME = "java:/Mail";
    
    /**
     * javax.mail.Session�̃v���p�e�B��ݒ肷��B<p>
     *
     * @param prop javax.mail.Session�̃v���p�e�B
     */
    public void setSessionProperties(Properties prop);
    
    /**
     * javax.mail.Session�̃v���p�e�B���擾����B<p>
     *
     * @return javax.mail.Session�̃v���p�e�B
     */
    public Properties getSessionProperties();
    
    /**
     * javax.mail.Authenticator�T�[�r�X�̃T�[�r�X����ݒ肷��B<p>
     *
     * @param name javax.mail.Authenticator�T�[�r�X�̃T�[�r�X��
     */
    public void setAuthenticatorServiceName(ServiceName name);
    
    /**
     * javax.mail.Authenticator�T�[�r�X�̃T�[�r�X�����擾����B<p>
     *
     * @return javax.mail.Authenticator�T�[�r�X�̃T�[�r�X��
     */
    public ServiceName getAuthenticatorServiceName();
    
    /**
     * javax.mail.Message�̃w�b�_��ݒ肷��B<p>
     *
     * @param prop javax.mail.Message�̃w�b�_
     */
    public void setHeaders(Properties prop);
    
    /**
     * javax.mail.Message�̃w�b�_���擾����B<p>
     *
     * @return javax.mail.Message�̃w�b�_
     */
    public Properties getHeaders();
    
    /**
     * javax.mail.Message�̃w�b�_��{@link jp.ossc.nimbus.service.writer.WritableRecord WritableRecord}����擾���Đݒ肷��ۂ́AWritableRecord���̃L�[����ݒ肷��B<p>
     *
     * @param keys WritableRecord���̃L�[���z��
     */
    public void setHeaderKeys(String[] keys);
    
    /**
     * javax.mail.Message�̃w�b�_��{@link jp.ossc.nimbus.service.writer.WritableRecord WritableRecord}����擾���Đݒ肷��ۂ́AWritableRecord���̃L�[����s�擾����B<p>
     *
     * @return WritableRecord���̃L�[���z��
     */
    public String[] getHeaderKeys();
    
    /**
     * javax.mail.Session�̃v���p�e�B"mail.smtp.from"��{@link jp.ossc.nimbus.service.writer.WritableRecord WritableRecord}����擾���Đݒ肷��ۂ́AWritableRecord���̃L�[����ݒ肷��B<p>
     *
     * @param key WritableRecord���̃L�[��
     */
    public void setEnvelopeFromAddressKey(String key);
    
    /**
     * javax.mail.Session�̃v���p�e�B"mail.smtp.from"��{@link jp.ossc.nimbus.service.writer.WritableRecord WritableRecord}����擾���Đݒ肷��ۂ́AWritableRecord���̃L�[�����擾����B<p>
     *
     * @return WritableRecord���̃L�[��
     */
    public String getEnvelopeFromAddressKey();
    
    /**
     * javax.mail.Session�̃v���p�e�B"mail.smtp.from"��ݒ肷��B<p>
     *
     * @param address javax.mail.Session�̃v���p�e�B"mail.smtp.from"
     */
    public void setEnvelopeFromAddress(String address);
    
    /**
     * javax.mail.Session�̃v���p�e�B"mail.smtp.from"���擾����B<p>
     *
     * @return javax.mail.Session�̃v���p�e�B"mail.smtp.from"
     */
    public String getEnvelopeFromAddress();
    
    /**
     * javax.mail.Session�̃v���p�e�B"mail.smtp.from"�����[���A�h���X�Ƃ��Đ����������؂��邩�ǂ�����ݒ肷��B<p>
     *
     * @param isValidate ���؂���ꍇtrue
     */
    public void setEnvelopeFromAddressValidate(boolean isValidate);
    
    /**
     * javax.mail.Session�̃v���p�e�B"mail.smtp.from"�����[���A�h���X�Ƃ��Đ����������؂��邩�ǂ����𔻒肷��B<p>
     *
     * @return true�̏ꍇ�A���؂���
     */
    public boolean isEnvelopeFromAddressValidate();
    
    /**
     * javax.mail.Message��From�A�h���X��{@link jp.ossc.nimbus.service.writer.WritableRecord WritableRecord}����擾���Đݒ肷��ۂ́AWritableRecord���̃L�[����ݒ肷��B<p>
     *
     * @param key WritableRecord���̃L�[��
     */
    public void setFromAddressKey(String key);
    
    /**
     * javax.mail.Message��From�A�h���X��{@link jp.ossc.nimbus.service.writer.WritableRecord WritableRecord}����擾���Đݒ肷��ۂ́AWritableRecord���̃L�[�����擾����B<p>
     *
     * @return WritableRecord���̃L�[��
     */
    public String getFromAddressKey();
    
    /**
     * javax.mail.Message��From�A�h���X��ݒ肷��B<p>
     *
     * @param address javax.mail.Message��From�A�h���X
     */
    public void setFromAddress(String address);
    
    /**
     * javax.mail.Message��From�A�h���X���擾����B<p>
     *
     * @return javax.mail.Message��From�A�h���X
     */
    public String getFromAddress();
    
    /**
     * javax.mail.Message��From�A�h���X�̕\������{@link jp.ossc.nimbus.service.writer.WritableRecord WritableRecord}����擾���Đݒ肷��ۂ́AWritableRecord���̃L�[����ݒ肷��B<p>
     *
     * @param key WritableRecord���̃L�[��
     */
    public void setFromPersonalKey(String key);
    
    /**
     * javax.mail.Message��From�A�h���X�̕\������{@link jp.ossc.nimbus.service.writer.WritableRecord WritableRecord}����擾���Đݒ肷��ۂ́AWritableRecord���̃L�[�����擾����B<p>
     *
     * @return WritableRecord���̃L�[��
     */
    public String getFromPersonalKey();
    
    /**
     * javax.mail.Message��From�A�h���X�̕\������ݒ肷��B<p>
     *
     * @param personal javax.mail.Message��From�A�h���X�̕\����
     */
    public void setFromPersonal(String personal);
    
    /**
     * javax.mail.Message��From�A�h���X�̕\�������擾����B<p>
     *
     * @return javax.mail.Message��From�A�h���X�̕\����
     */
    public String getFromPersonal();
    
    /**
     * javax.mail.Message��From�A�h���X�̕\���������G���R�[�f�B���O��{@link jp.ossc.nimbus.service.writer.WritableRecord WritableRecord}����擾���Đݒ肷��ۂ́AWritableRecord���̃L�[����ݒ肷��B<p>
     *
     * @param key WritableRecord���̃L�[��
     */
    public void setFromPersonalEncodingKey(String key);
    
    /**
     * javax.mail.Message��From�A�h���X�̕\���������G���R�[�f�B���O��{@link jp.ossc.nimbus.service.writer.WritableRecord WritableRecord}����擾���Đݒ肷��ۂ́AWritableRecord���̃L�[�����擾����B<p>
     *
     * @return WritableRecord���̃L�[��
     */
    public String getFromPersonalEncodingKey();
    
    /**
     * javax.mail.Message��From�A�h���X�̕\���������G���R�[�f�B���O��ݒ肷��B<p>
     *
     * @param encoding javax.mail.Message��From�A�h���X�̕\���������G���R�[�f�B���O
     */
    public void setFromPersonalEncoding(String encoding);
    
    /**
     * javax.mail.Message��From�A�h���X�̕\���������G���R�[�f�B���O���擾����B<p>
     *
     * @return javax.mail.Message��From�A�h���X�̕\���������G���R�[�f�B���O
     */
    public String getFromPersonalEncoding();
    
    /**
     * javax.mail.Message��From�A�h���X�����[���A�h���X�Ƃ��Đ����������؂��邩�ǂ�����ݒ肷��B<p>
     *
     * @param isValidate ���؂���ꍇtrue
     */
    public void setFromAddressValidate(boolean isValidate);
    
    /**
     * javax.mail.Message��From�A�h���X�����[���A�h���X�Ƃ��Đ����������؂��邩�ǂ����𔻒肷��B<p>
     *
     * @return true�̏ꍇ�A���؂���
     */
    public boolean isFromAddressValidate();
    
    /**
     * javax.mail.Message��Sender�A�h���X��{@link jp.ossc.nimbus.service.writer.WritableRecord WritableRecord}����擾���Đݒ肷��ۂ́AWritableRecord���̃L�[����ݒ肷��B<p>
     *
     * @param key WritableRecord���̃L�[��
     */
    public void setSenderAddressKey(String key);
    
    /**
     * javax.mail.Message��Sender�A�h���X��{@link jp.ossc.nimbus.service.writer.WritableRecord WritableRecord}����擾���Đݒ肷��ۂ́AWritableRecord���̃L�[�����擾����B<p>
     *
     * @return WritableRecord���̃L�[��
     */
    public String getSenderAddressKey();
    
    /**
     * javax.mail.Message��Sender�A�h���X��ݒ肷��B<p>
     *
     * @param address javax.mail.Message��Sender�A�h���X
     */
    public void setSenderAddress(String address);
    
    /**
     * javax.mail.Message��Sender�A�h���X���擾����B<p>
     *
     * @return javax.mail.Message��Sender�A�h���X
     */
    public String getSenderAddress();
    
    /**
     * javax.mail.Message��Sender�A�h���X�̕\������{@link jp.ossc.nimbus.service.writer.WritableRecord WritableRecord}����擾���Đݒ肷��ۂ́AWritableRecord���̃L�[����ݒ肷��B<p>
     *
     * @param key WritableRecord���̃L�[��
     */
    public void setSenderPersonalKey(String key);
    
    /**
     * javax.mail.Message��Sender�A�h���X�̕\������{@link jp.ossc.nimbus.service.writer.WritableRecord WritableRecord}����擾���Đݒ肷��ۂ́AWritableRecord���̃L�[�����擾����B<p>
     *
     * @return WritableRecord���̃L�[��
     */
    public String getSenderPersonalKey();
    
    /**
     * javax.mail.Message��Sender�A�h���X�̕\������ݒ肷��B<p>
     *
     * @param personal javax.mail.Message��Sender�A�h���X�̕\����
     */
    public void setSenderPersonal(String personal);
    
    /**
     * javax.mail.Message��Sender�A�h���X�̕\�������擾����B<p>
     *
     * @return javax.mail.Message��Sender�A�h���X�̕\����
     */
    public String getSenderPersonal();
    
    /**
     * javax.mail.Message��Sender�A�h���X�̕\���������G���R�[�f�B���O��{@link jp.ossc.nimbus.service.writer.WritableRecord WritableRecord}����擾���Đݒ肷��ۂ́AWritableRecord���̃L�[����ݒ肷��B<p>
     *
     * @param key WritableRecord���̃L�[��
     */
    public void setSenderPersonalEncodingKey(String key);
    
    /**
     * javax.mail.Message��Sender�A�h���X�̕\���������G���R�[�f�B���O��{@link jp.ossc.nimbus.service.writer.WritableRecord WritableRecord}����擾���Đݒ肷��ۂ́AWritableRecord���̃L�[�����擾����B<p>
     *
     * @return WritableRecord���̃L�[��
     */
    public String getSenderPersonalEncodingKey();
    
    /**
     * javax.mail.Message��Sender�A�h���X�̕\���������G���R�[�f�B���O��ݒ肷��B<p>
     *
     * @param encoding javax.mail.Message��Sender�A�h���X�̕\���������G���R�[�f�B���O
     */
    public void setSenderPersonalEncoding(String encoding);
    
    /**
     * javax.mail.Message��Sender�A�h���X�̕\���������G���R�[�f�B���O���擾����B<p>
     *
     * @return javax.mail.Message��Sender�A�h���X�̕\���������G���R�[�f�B���O
     */
    public String getSenderPersonalEncoding();
    
    /**
     * javax.mail.Message��Sender�A�h���X�����[���A�h���X�Ƃ��Đ����������؂��邩�ǂ�����ݒ肷��B<p>
     *
     * @param isValidate ���؂���ꍇtrue
     */
    public void setSenderAddressValidate(boolean isValidate);
    
    /**
     * javax.mail.Message��Sender�A�h���X�����[���A�h���X�Ƃ��Đ����������؂��邩�ǂ����𔻒肷��B<p>
     *
     * @return true�̏ꍇ�A���؂���
     */
    public boolean isSenderAddressValidate();
    
    /**
     * javax.mail.Message��To�A�h���X��{@link jp.ossc.nimbus.service.writer.WritableRecord WritableRecord}����擾���Đݒ肷��ۂ́AWritableRecord���̃L�[����ݒ肷��B<p>
     *
     * @param key WritableRecord���̃L�[��
     */
    public void setToAddressKey(String key);
    
    /**
     * javax.mail.Message��To�A�h���X��{@link jp.ossc.nimbus.service.writer.WritableRecord WritableRecord}����擾���Đݒ肷��ۂ́AWritableRecord���̃L�[�����擾����B<p>
     *
     * @return WritableRecord���̃L�[��
     */
    public String getToAddressKey();
    
    /**
     * javax.mail.Message��To�A�h���X��ݒ肷��B<p>
     *
     * @param address javax.mail.Message��To�A�h���X�z��
     */
    public void setToAddress(String[] address);
    
    /**
     * javax.mail.Message��To�A�h���X���擾����B<p>
     *
     * @return javax.mail.Message��To�A�h���X�z��
     */
    public String[] getToAddress();
    
    /**
     * javax.mail.Message��To�A�h���X�̕\������{@link jp.ossc.nimbus.service.writer.WritableRecord WritableRecord}����擾���Đݒ肷��ۂ́AWritableRecord���̃L�[����ݒ肷��B<p>
     *
     * @param key WritableRecord���̃L�[��
     */
    public void setToPersonalKey(String key);
    
    /**
     * javax.mail.Message��To�A�h���X�̕\������{@link jp.ossc.nimbus.service.writer.WritableRecord WritableRecord}����擾���Đݒ肷��ۂ́AWritableRecord���̃L�[�����擾����B<p>
     *
     * @return WritableRecord���̃L�[��
     */
    public String getToPersonalKey();
    
    /**
     * javax.mail.Message��To�A�h���X�̕\������ݒ肷��B<p>
     *
     * @param personal javax.mail.Message��To�A�h���X�̕\�����z��
     */
    public void setToPersonals(String[] personal);
    
    /**
     * javax.mail.Message��To�A�h���X�̕\�������擾����B<p>
     *
     * @return javax.mail.Message��To�A�h���X�̕\�����z��
     */
    public String[] getToPersonals();
    
    /**
     * javax.mail.Message��To�A�h���X�̕\���������G���R�[�f�B���O��{@link jp.ossc.nimbus.service.writer.WritableRecord WritableRecord}����擾���Đݒ肷��ۂ́AWritableRecord���̃L�[����ݒ肷��B<p>
     *
     * @param key WritableRecord���̃L�[��
     */
    public void setToPersonalEncodingKey(String key);
    
    /**
     * javax.mail.Message��To�A�h���X�̕\���������G���R�[�f�B���O��{@link jp.ossc.nimbus.service.writer.WritableRecord WritableRecord}����擾���Đݒ肷��ۂ́AWritableRecord���̃L�[�����擾����B<p>
     *
     * @return WritableRecord���̃L�[��
     */
    public String getToPersonalEncodingKey();
    
    /**
     * javax.mail.Message��To�A�h���X�̕\���������G���R�[�f�B���O��ݒ肷��B<p>
     *
     * @param encoding javax.mail.Message��To�A�h���X�̕\���������G���R�[�f�B���O�z��
     */
    public void setToPersonalEncodings(String[] encoding);
    
    /**
     * javax.mail.Message��To�A�h���X�̕\���������G���R�[�f�B���O���擾����B<p>
     *
     * @return javax.mail.Message��To�A�h���X�̕\���������G���R�[�f�B���O�z��
     */
    public String[] getToPersonalEncodings();
    
    /**
     * javax.mail.Message��To�A�h���X�̕\���������G���R�[�f�B���O��ݒ肷��B<p>
     *
     * @param encoding javax.mail.Message��To�A�h���X�̕\���������G���R�[�f�B���O
     */
    public void setToPersonalEncoding(String encoding);
    
    /**
     * javax.mail.Message��To�A�h���X�̕\���������G���R�[�f�B���O���擾����B<p>
     *
     * @return javax.mail.Message��To�A�h���X�̕\���������G���R�[�f�B���O
     */
    public String getToPersonalEncoding();
    
    /**
     * javax.mail.Message��To�A�h���X�����[���A�h���X�Ƃ��Đ����������؂��邩�ǂ�����ݒ肷��B<p>
     *
     * @param isValidate ���؂���ꍇtrue
     */
    public void setToAddressValidate(boolean isValidate);
    
    /**
     * javax.mail.Message��To�A�h���X�����[���A�h���X�Ƃ��Đ����������؂��邩�ǂ����𔻒肷��B<p>
     *
     * @return true�̏ꍇ�A���؂���
     */
    public boolean isToAddressValidate();
    
    /**
     * javax.mail.Message��Cc�A�h���X��{@link jp.ossc.nimbus.service.writer.WritableRecord WritableRecord}����擾���Đݒ肷��ۂ́AWritableRecord���̃L�[����ݒ肷��B<p>
     *
     * @param key WritableRecord���̃L�[��
     */
    public void setCcAddressKey(String key);
    
    /**
     * javax.mail.Message��Cc�A�h���X��{@link jp.ossc.nimbus.service.writer.WritableRecord WritableRecord}����擾���Đݒ肷��ۂ́AWritableRecord���̃L�[�����擾����B<p>
     *
     * @return WritableRecord���̃L�[��
     */
    public String getCcAddressKey();
    
    /**
     * javax.mail.Message��Cc�A�h���X��ݒ肷��B<p>
     *
     * @param address javax.mail.Message��Cc�A�h���X�z��
     */
    public void setCcAddress(String[] address);
    
    /**
     * javax.mail.Message��Cc�A�h���X���擾����B<p>
     *
     * @return javax.mail.Message��Cc�A�h���X�z��
     */
    public String[] getCcAddress();
    
    /**
     * javax.mail.Message��Cc�A�h���X�̕\������{@link jp.ossc.nimbus.service.writer.WritableRecord WritableRecord}����擾���Đݒ肷��ۂ́AWritableRecord���̃L�[����ݒ肷��B<p>
     *
     * @param key WritableRecord���̃L�[��
     */
    public void setCcPersonalKey(String key);
    
    /**
     * javax.mail.Message��Cc�A�h���X�̕\������{@link jp.ossc.nimbus.service.writer.WritableRecord WritableRecord}����擾���Đݒ肷��ۂ́AWritableRecord���̃L�[�����擾����B<p>
     *
     * @return WritableRecord���̃L�[��
     */
    public String getCcPersonalKey();
    
    /**
     * javax.mail.Message��Cc�A�h���X�̕\������ݒ肷��B<p>
     *
     * @param personal javax.mail.Message��Cc�A�h���X�̕\�����z��
     */
    public void setCcPersonals(String[] personal);
    
    /**
     * javax.mail.Message��Cc�A�h���X�̕\�������擾����B<p>
     *
     * @return javax.mail.Message��Cc�A�h���X�̕\�����z��
     */
    public String[] getCcPersonals();
    
    /**
     * javax.mail.Message��Cc�A�h���X�̕\���������G���R�[�f�B���O��{@link jp.ossc.nimbus.service.writer.WritableRecord WritableRecord}����擾���Đݒ肷��ۂ́AWritableRecord���̃L�[����ݒ肷��B<p>
     *
     * @param key WritableRecord���̃L�[��
     */
    public void setCcPersonalEncodingKey(String key);
    
    /**
     * javax.mail.Message��Cc�A�h���X�̕\���������G���R�[�f�B���O��{@link jp.ossc.nimbus.service.writer.WritableRecord WritableRecord}����擾���Đݒ肷��ۂ́AWritableRecord���̃L�[�����擾����B<p>
     *
     * @return WritableRecord���̃L�[��
     */
    public String getCcPersonalEncodingKey();
    
    /**
     * javax.mail.Message��Cc�A�h���X�̕\���������G���R�[�f�B���O��ݒ肷��B<p>
     *
     * @param encoding javax.mail.Message��Cc�A�h���X�̕\���������G���R�[�f�B���O�z��
     */
    public void setCcPersonalEncodings(String[] encoding);
    
    /**
     * javax.mail.Message��Cc�A�h���X�̕\���������G���R�[�f�B���O���擾����B<p>
     *
     * @return javax.mail.Message��Cc�A�h���X�̕\���������G���R�[�f�B���O
     */
    public String[] getCcPersonalEncodings();
    
    /**
     * javax.mail.Message��Cc�A�h���X�̕\���������G���R�[�f�B���O��ݒ肷��B<p>
     *
     * @param encoding javax.mail.Message��Cc�A�h���X�̕\���������G���R�[�f�B���O
     */
    public void setCcPersonalEncoding(String encoding);
    
    /**
     * javax.mail.Message��Cc�A�h���X�̕\���������G���R�[�f�B���O���擾����B<p>
     *
     * @return javax.mail.Message��Cc�A�h���X�̕\���������G���R�[�f�B���O
     */
    public String getCcPersonalEncoding();
    
    /**
     * javax.mail.Message��Cc�A�h���X�����[���A�h���X�Ƃ��Đ����������؂��邩�ǂ�����ݒ肷��B<p>
     *
     * @param isValidate ���؂���ꍇtrue
     */
    public void setCcAddressValidate(boolean isValidate);
    
    /**
     * javax.mail.Message��Cc�A�h���X�����[���A�h���X�Ƃ��Đ����������؂��邩�ǂ����𔻒肷��B<p>
     *
     * @return true�̏ꍇ�A���؂���
     */
    public boolean isCcAddressValidate();
    
    /**
     * javax.mail.Message��Bcc�A�h���X��{@link jp.ossc.nimbus.service.writer.WritableRecord WritableRecord}����擾���Đݒ肷��ۂ́AWritableRecord���̃L�[����ݒ肷��B<p>
     *
     * @param key WritableRecord���̃L�[��
     */
    public void setBccAddressKey(String key);
    
    /**
     * javax.mail.Message��Bcc�A�h���X��{@link jp.ossc.nimbus.service.writer.WritableRecord WritableRecord}����擾���Đݒ肷��ۂ́AWritableRecord���̃L�[�����擾����B<p>
     *
     * @return WritableRecord���̃L�[��
     */
    public String getBccAddressKey();
    
    /**
     * javax.mail.Message��Bcc�A�h���X��ݒ肷��B<p>
     *
     * @param address javax.mail.Message��Bcc�A�h���X�z��
     */
    public void setBccAddress(String[] address);
    
    /**
     * javax.mail.Message��Bcc�A�h���X���擾����B<p>
     *
     * @return javax.mail.Message��Bcc�A�h���X�z��
     */
    public String[] getBccAddress();
    
    /**
     * javax.mail.Message��Bcc�A�h���X�̕\������{@link jp.ossc.nimbus.service.writer.WritableRecord WritableRecord}����擾���Đݒ肷��ۂ́AWritableRecord���̃L�[����ݒ肷��B<p>
     *
     * @param key WritableRecord���̃L�[��
     */
    public void setBccPersonalKey(String key);
    
    /**
     * javax.mail.Message��Bcc�A�h���X�̕\������{@link jp.ossc.nimbus.service.writer.WritableRecord WritableRecord}����擾���Đݒ肷��ۂ́AWritableRecord���̃L�[�����擾����B<p>
     *
     * @return WritableRecord���̃L�[��
     */
    public String getBccPersonalKey();
    
    /**
     * javax.mail.Message��Bcc�A�h���X�̕\������ݒ肷��B<p>
     *
     * @param personal javax.mail.Message��Bcc�A�h���X�̕\�����z��
     */
    public void setBccPersonals(String[] personal);
    
    /**
     * javax.mail.Message��Bcc�A�h���X�̕\�������擾����B<p>
     *
     * @return javax.mail.Message��Bcc�A�h���X�̕\�����z��
     */
    public String[] getBccPersonals();
    
    /**
     * javax.mail.Message��Bcc�A�h���X�̕\���������G���R�[�f�B���O��{@link jp.ossc.nimbus.service.writer.WritableRecord WritableRecord}����擾���Đݒ肷��ۂ́AWritableRecord���̃L�[����ݒ肷��B<p>
     *
     * @param key WritableRecord���̃L�[��
     */
    public void setBccPersonalEncodingKey(String key);
    
    /**
     * javax.mail.Message��Bcc�A�h���X�̕\���������G���R�[�f�B���O��{@link jp.ossc.nimbus.service.writer.WritableRecord WritableRecord}����擾���Đݒ肷��ۂ́AWritableRecord���̃L�[�����擾����B<p>
     *
     * @return WritableRecord���̃L�[��
     */
    public String getBccPersonalEncodingKey();
    
    /**
     * javax.mail.Message��Bcc�A�h���X�̕\���������G���R�[�f�B���O��ݒ肷��B<p>
     *
     * @param encoding javax.mail.Message��Bcc�A�h���X�̕\���������G���R�[�f�B���O�z��
     */
    public void setBccPersonalEncodings(String[] encoding);
    
    /**
     * javax.mail.Message��Bcc�A�h���X�̕\���������G���R�[�f�B���O���擾����B<p>
     *
     * @return javax.mail.Message��Bcc�A�h���X�̕\���������G���R�[�f�B���O
     */
    public String[] getBccPersonalEncodings();
    
    /**
     * javax.mail.Message��Bcc�A�h���X�̕\���������G���R�[�f�B���O��ݒ肷��B<p>
     *
     * @param encoding javax.mail.Message��Bcc�A�h���X�̕\���������G���R�[�f�B���O
     */
    public void setBccPersonalEncoding(String encoding);
    
    /**
     * javax.mail.Message��Bcc�A�h���X�̕\���������G���R�[�f�B���O���擾����B<p>
     *
     * @return javax.mail.Message��Bcc�A�h���X�̕\���������G���R�[�f�B���O
     */
    public String getBccPersonalEncoding();
    
    /**
     * javax.mail.Message��Bcc�A�h���X�����[���A�h���X�Ƃ��Đ����������؂��邩�ǂ�����ݒ肷��B<p>
     *
     * @param isValidate ���؂���ꍇtrue
     */
    public void setBccAddressValidate(boolean isValidate);
    
    /**
     * javax.mail.Message��Bcc�A�h���X�����[���A�h���X�Ƃ��Đ����������؂��邩�ǂ����𔻒肷��B<p>
     *
     * @return true�̏ꍇ�A���؂���
     */
    public boolean isBccAddressValidate();
    
    /**
     * javax.mail.Message��ReplyTo�A�h���X��{@link jp.ossc.nimbus.service.writer.WritableRecord WritableRecord}����擾���Đݒ肷��ۂ́AWritableRecord���̃L�[����ݒ肷��B<p>
     *
     * @param key WritableRecord���̃L�[��
     */
    public void setReplyToAddressKey(String key);
    
    /**
     * javax.mail.Message��ReplyTo�A�h���X��{@link jp.ossc.nimbus.service.writer.WritableRecord WritableRecord}����擾���Đݒ肷��ۂ́AWritableRecord���̃L�[�����擾����B<p>
     *
     * @return WritableRecord���̃L�[��
     */
    public String getReplyToAddressKey();
    
    /**
     * javax.mail.Message��ReplyTo�A�h���X��ݒ肷��B<p>
     *
     * @param address javax.mail.Message��ReplyTo�A�h���X�z��
     */
    public void setReplyToAddress(String[] address);
    
    /**
     * javax.mail.Message��ReplyTo�A�h���X���擾����B<p>
     *
     * @return javax.mail.Message��ReplyTo�A�h���X�z��
     */
    public String[] getReplyToAddress();
    
    /**
     * javax.mail.Message��ReplyTo�A�h���X�̕\������{@link jp.ossc.nimbus.service.writer.WritableRecord WritableRecord}����擾���Đݒ肷��ۂ́AWritableRecord���̃L�[����ݒ肷��B<p>
     *
     * @param key WritableRecord���̃L�[��
     */
    public void setReplyToPersonalKey(String key);
    
    /**
     * javax.mail.Message��ReplyTo�A�h���X�̕\������{@link jp.ossc.nimbus.service.writer.WritableRecord WritableRecord}����擾���Đݒ肷��ۂ́AWritableRecord���̃L�[�����擾����B<p>
     *
     * @return WritableRecord���̃L�[��
     */
    public String getReplyToPersonalKey();
    
    /**
     * javax.mail.Message��ReplyTo�A�h���X�̕\������ݒ肷��B<p>
     *
     * @param personal javax.mail.Message��ReplyTo�A�h���X�̕\�����z��
     */
    public void setReplyToPersonals(String[] personal);
    
    /**
     * javax.mail.Message��ReplyTo�A�h���X�̕\�������擾����B<p>
     *
     * @return javax.mail.Message��ReplyTo�A�h���X�̕\�����z��
     */
    public String[] getReplyToPersonals();
    
    /**
     * javax.mail.Message��ReplyTo�A�h���X�̕\���������G���R�[�f�B���O��{@link jp.ossc.nimbus.service.writer.WritableRecord WritableRecord}����擾���Đݒ肷��ۂ́AWritableRecord���̃L�[����ݒ肷��B<p>
     *
     * @param key WritableRecord���̃L�[��
     */
    public void setReplyToPersonalEncodingKey(String key);
    
    /**
     * javax.mail.Message��ReplyTo�A�h���X�̕\���������G���R�[�f�B���O��{@link jp.ossc.nimbus.service.writer.WritableRecord WritableRecord}����擾���Đݒ肷��ۂ́AWritableRecord���̃L�[�����擾����B<p>
     *
     * @return WritableRecord���̃L�[��
     */
    public String getReplyToPersonalEncodingKey();
    
    /**
     * javax.mail.Message��ReplyTo�A�h���X�̕\���������G���R�[�f�B���O��ݒ肷��B<p>
     *
     * @param encoding javax.mail.Message��ReplyTo�A�h���X�̕\���������G���R�[�f�B���O�z��
     */
    public void setReplyToPersonalEncodings(String[] encoding);
    
    /**
     * javax.mail.Message��ReplyTo�A�h���X�̕\���������G���R�[�f�B���O���擾����B<p>
     *
     * @return javax.mail.Message��ReplyTo�A�h���X�̕\���������G���R�[�f�B���O
     */
    public String[] getReplyToPersonalEncodings();
    
    /**
     * javax.mail.Message��ReplyTo�A�h���X�̕\���������G���R�[�f�B���O��ݒ肷��B<p>
     *
     * @param encoding javax.mail.Message��ReplyTo�A�h���X�̕\���������G���R�[�f�B���O
     */
    public void setReplyToPersonalEncoding(String encoding);
    
    /**
     * javax.mail.Message��ReplyTo�A�h���X�̕\���������G���R�[�f�B���O���擾����B<p>
     *
     * @return javax.mail.Message��ReplyTo�A�h���X�̕\���������G���R�[�f�B���O
     */
    public String getReplyToPersonalEncoding();
    
    /**
     * javax.mail.Message��ReplyTo�A�h���X�����[���A�h���X�Ƃ��Đ����������؂��邩�ǂ�����ݒ肷��B<p>
     *
     * @param isValidate ���؂���ꍇtrue
     */
    public void setReplyToAddressValidate(boolean isValidate);
    
    /**
     * javax.mail.Message��ReplyTo�A�h���X�����[���A�h���X�Ƃ��Đ����������؂��邩�ǂ����𔻒肷��B<p>
     *
     * @return true�̏ꍇ�A���؂���
     */
    public boolean isReplyToAddressValidate();
    
    /**
     * javax.mail.Message��Subject��{@link jp.ossc.nimbus.service.writer.WritableRecord WritableRecord}����擾���Đݒ肷��ۂ́AWritableRecord���̃L�[����ݒ肷��B<p>
     *
     * @param key WritableRecord���̃L�[��
     */
    public void setSubjectKey(String key);
    
    /**
     * javax.mail.Message��Subject��{@link jp.ossc.nimbus.service.writer.WritableRecord WritableRecord}����擾���Đݒ肷��ۂ́AWritableRecord���̃L�[�����擾����B<p>
     *
     * @return WritableRecord���̃L�[��
     */
    public String getSubjectKey();
    
    /**
     * javax.mail.Message��Subject��ݒ肷��B<p>
     *
     * @param subject javax.mail.Message��Subject
     */
    public void setSubject(String subject);
    
    /**
     * javax.mail.Message��Subject���擾����B<p>
     *
     * @return javax.mail.Message��Subject
     */
    public String getSubject();
    
    /**
     * javax.mail.Message��Subject�̕����G���R�[�f�B���O��{@link jp.ossc.nimbus.service.writer.WritableRecord WritableRecord}����擾���Đݒ肷��ۂ́AWritableRecord���̃L�[����ݒ肷��B<p>
     *
     * @param key WritableRecord���̃L�[��
     */
    public void setSubjectEncodingKey(String key);
    
    /**
     * javax.mail.Message��Subject�̕����G���R�[�f�B���O��{@link jp.ossc.nimbus.service.writer.WritableRecord WritableRecord}����擾���Đݒ肷��ۂ́AWritableRecord���̃L�[�����擾����B<p>
     *
     * @return WritableRecord���̃L�[��
     */
    public String getSubjectEncodingKey();
    
    /**
     * javax.mail.Message��Subject�̕����G���R�[�f�B���O��ݒ肷��B<p>
     *
     * @param encoding javax.mail.Message��Subject�̕����G���R�[�f�B���O
     */
    public void setSubjectEncoding(String encoding);
    
    /**
     * javax.mail.Message��Subject�̕����G���R�[�f�B���O���擾����B<p>
     *
     * @return javax.mail.Message��Subject�̕����G���R�[�f�B���O
     */
    public String getSubjectEncoding();
    
    /**
     * javax.mail.Message��ContentID��{@link jp.ossc.nimbus.service.writer.WritableRecord WritableRecord}����擾���Đݒ肷��ۂ́AWritableRecord���̃L�[����ݒ肷��B<p>
     *
     * @param key WritableRecord���̃L�[��
     */
    public void setContentIDKey(String key);
    
    /**
     * javax.mail.Message��ContentID��{@link jp.ossc.nimbus.service.writer.WritableRecord WritableRecord}����擾���Đݒ肷��ۂ́AWritableRecord���̃L�[�����擾����B<p>
     *
     * @return WritableRecord���̃L�[��
     */
    public String getContentIDKey();
    
    /**
     * javax.mail.Message��ContentID��ݒ肷��B<p>
     *
     * @param id javax.mail.Message��ContentID
     */
    public void setContentID(String id);
    
    /**
     * javax.mail.Message��ContentID���擾����B<p>
     *
     * @return javax.mail.Message��ContentID
     */
    public String getContentID();
    
    /**
     * javax.mail.Message��ContentLanguage��{@link jp.ossc.nimbus.service.writer.WritableRecord WritableRecord}����擾���Đݒ肷��ۂ́AWritableRecord���̃L�[����ݒ肷��B<p>
     *
     * @param key WritableRecord���̃L�[��
     */
    public void setContentLanguageKey(String key);
    
    /**
     * javax.mail.Message��ContentLanguage��{@link jp.ossc.nimbus.service.writer.WritableRecord WritableRecord}����擾���Đݒ肷��ۂ́AWritableRecord���̃L�[�����擾����B<p>
     *
     * @return WritableRecord���̃L�[��
     */
    public String getContentLanguageKey();
    
    /**
     * javax.mail.Message��ContentLanguage��ݒ肷��B<p>
     *
     * @param lang javax.mail.Message��ContentLanguage
     */
    public void setContentLanguage(String[] lang);
    
    /**
     * javax.mail.Message��ContentLanguage���擾����B<p>
     *
     * @return javax.mail.Message��ContentLanguage
     */
    public String[] getContentLanguage();
    
    /**
     * javax.mail.Message��ContentMD5��{@link jp.ossc.nimbus.service.writer.WritableRecord WritableRecord}����擾���Đݒ肷��ۂ́AWritableRecord���̃L�[����ݒ肷��B<p>
     *
     * @param key WritableRecord���̃L�[��
     */
    public void setContentMD5Key(String key);
    
    /**
     * javax.mail.Message��ContentMD5��{@link jp.ossc.nimbus.service.writer.WritableRecord WritableRecord}����擾���Đݒ肷��ۂ́AWritableRecord���̃L�[�����擾����B<p>
     *
     * @return WritableRecord���̃L�[��
     */
    public String getContentMD5Key();
    
    /**
     * javax.mail.Message��ContentMD5��ݒ肷��B<p>
     *
     * @param val javax.mail.Message��ContentMD5
     */
    public void setContentMD5(String val);
    
    /**
     * javax.mail.Message��ContentMD5���擾����B<p>
     *
     * @return javax.mail.Message��ContentMD5
     */
    public String getContentMD5();
    
    /**
     * javax.mail.Message��Description��{@link jp.ossc.nimbus.service.writer.WritableRecord WritableRecord}����擾���Đݒ肷��ۂ́AWritableRecord���̃L�[����ݒ肷��B<p>
     *
     * @param key WritableRecord���̃L�[��
     */
    public void setDescriptionKey(String key);
    
    /**
     * javax.mail.Message��Description��{@link jp.ossc.nimbus.service.writer.WritableRecord WritableRecord}����擾���Đݒ肷��ۂ́AWritableRecord���̃L�[�����擾����B<p>
     *
     * @return WritableRecord���̃L�[��
     */
    public String getDescriptionKey();
    
    /**
     * javax.mail.Message��Description��ݒ肷��B<p>
     *
     * @param val javax.mail.Message��Description
     */
    public void setDescription(String val);
    
    /**
     * javax.mail.Message��Description���擾����B<p>
     *
     * @return javax.mail.Message��Description
     */
    public String getDescription();
    
    /**
     * javax.mail.Message��Description�̕����G���R�[�f�B���O��{@link jp.ossc.nimbus.service.writer.WritableRecord WritableRecord}����擾���Đݒ肷��ۂ́AWritableRecord���̃L�[����ݒ肷��B<p>
     *
     * @param key WritableRecord���̃L�[��
     */
    public void setDescriptionEncodingKey(String key);
    
    /**
     * javax.mail.Message��Description�̕����G���R�[�f�B���O��{@link jp.ossc.nimbus.service.writer.WritableRecord WritableRecord}����擾���Đݒ肷��ۂ́AWritableRecord���̃L�[�����擾����B<p>
     *
     * @return WritableRecord���̃L�[��
     */
    public String getDescriptionEncodingKey();
    
    /**
     * javax.mail.Message��Description�̕����G���R�[�f�B���O��ݒ肷��B<p>
     *
     * @param encoding javax.mail.Message��Description�̕����G���R�[�f�B���O
     */
    public void setDescriptionEncoding(String encoding);
    
    /**
     * javax.mail.Message��Description�̕����G���R�[�f�B���O���擾����B<p>
     *
     * @return javax.mail.Message��Description�̕����G���R�[�f�B���O
     */
    public String getDescriptionEncoding();
    
    /**
     * javax.mail.Message��Disposition��{@link jp.ossc.nimbus.service.writer.WritableRecord WritableRecord}����擾���Đݒ肷��ۂ́AWritableRecord���̃L�[����ݒ肷��B<p>
     *
     * @param key WritableRecord���̃L�[��
     */
    public void setDispositionKey(String key);
    
    /**
     * javax.mail.Message��Disposition��{@link jp.ossc.nimbus.service.writer.WritableRecord WritableRecord}����擾���Đݒ肷��ۂ́AWritableRecord���̃L�[�����擾����B<p>
     *
     * @return WritableRecord���̃L�[��
     */
    public String getDispositionKey();
    
    /**
     * javax.mail.Message��Disposition��ݒ肷��B<p>
     *
     * @param val javax.mail.Message��Disposition
     */
    public void setDisposition(String val);
    
    /**
     * javax.mail.Message��Disposition���擾����B<p>
     *
     * @return javax.mail.Message��Disposition
     */
    public String getDisposition();
    
    /**
     * �Y�t�t�@�C����{@link jp.ossc.nimbus.service.writer.WritableRecord WritableRecord}����擾���Đݒ肷��ۂ́AWritableRecord���̃L�[����ݒ肷��B<p>
     *
     * @param key WritableRecord���̃L�[��
     */
    public void setFilePartKey(String key);
    
    /**
     * �Y�t�t�@�C����{@link jp.ossc.nimbus.service.writer.WritableRecord WritableRecord}����擾���Đݒ肷��ۂ́AWritableRecord���̃L�[�����擾����B<p>
     *
     * @return WritableRecord���̃L�[��
     */
    public String getFilePartKey();
    
    /**
     * �Y�t�t�@�C���̃t�@�C�����̕����R�[�h��{@link jp.ossc.nimbus.service.writer.WritableRecord WritableRecord}����擾���Đݒ肷��ۂ́AWritableRecord���̃L�[����ݒ肷��B<p>
     *
     * @param key WritableRecord���̃L�[��
     */
    public void setFileCharsetKey(String key);
    
    /**
     * �Y�t�t�@�C���̃t�@�C�����̕����R�[�h��{@link jp.ossc.nimbus.service.writer.WritableRecord WritableRecord}����擾���Đݒ肷��ۂ́AWritableRecord���̃L�[�����擾����B<p>
     *
     * @return WritableRecord���̃L�[��
     */
    public String getFileCharsetKey();
    
    /**
     * �Y�t�t�@�C���̃t�@�C�����̕����R�[�h����ݒ肷��B<p>
     *
     * @param charset �����R�[�h
     */
    public void setFileCharset(String charset);
    
    /**
     * �Y�t�t�@�C���̃t�@�C�����̕����R�[�h���擾����B<p>
     *
     * @return �����R�[�h
     */
    public String getFileCharset();
    
    /**
     * �Y�t�t�@�C���̃t�@�C�����̌���R�[�h��{@link jp.ossc.nimbus.service.writer.WritableRecord WritableRecord}����擾���Đݒ肷��ۂ́AWritableRecord���̃L�[����ݒ肷��B<p>
     *
     * @param key WritableRecord���̃L�[��
     */
    public void setFileLanguageKey(String key);
    
    /**
     * �Y�t�t�@�C���̃t�@�C�����̌���R�[�h��{@link jp.ossc.nimbus.service.writer.WritableRecord WritableRecord}����擾���Đݒ肷��ۂ́AWritableRecord���̃L�[�����擾����B<p>
     *
     * @return WritableRecord���̃L�[��
     */
    public String getFileLanguageKey();
    
    /**
     * �Y�t�t�@�C���̃t�@�C�����̌���R�[�h����ݒ肷��B<p>
     *
     * @param lang ����R�[�h
     */
    public void setFileLanguage(String lang);
    
    /**
     * �Y�t�t�@�C���̃t�@�C�����̌���R�[�h���擾����B<p>
     *
     * @return ����R�[�h
     */
    public String getFileLanguage();
    
    /**
     * ���[���̖{����{@link jp.ossc.nimbus.service.writer.WritableRecord WritableRecord}����擾���Đݒ肷��ۂ́AWritableRecord���̊J�n�C���f�b�N�X���w�肷��{@link jp.ossc.nimbus.service.writer.WritableElement WritableElement}�̃L�[����ݒ肷��B<p>
     *
     * @param key WritableRecord���̊J�n�C���f�b�N�X���w�肷��WritableElement�̃L�[��
     */
    public void setBodyIndexKey(String key);
    
    /**
     * ���[���̖{����{@link jp.ossc.nimbus.service.writer.WritableRecord WritableRecord}����擾���Đݒ肷��ۂ́AWritableRecord���̊J�n�C���f�b�N�X���w�肷��{@link jp.ossc.nimbus.service.writer.WritableElement WritableElement}�̃L�[�����擾����B<p>
     *
     * @return WritableRecord���̊J�n�C���f�b�N�X���w�肷��WritableElement�̃L�[��
     */
    public String getBodyIndexKey();
    
    /**
     * ���[���̖{����{@link jp.ossc.nimbus.service.writer.WritableRecord WritableRecord}����擾���Đݒ肷��ۂ́AWritableRecord���̊J�n�C���f�b�N�X��ݒ肷��B<p>
     *
     * @param index WritableRecord���̊J�n�C���f�b�N�X
     */
    public void setBodyIndex(int index);
    
    /**
     * ���[���̖{����{@link jp.ossc.nimbus.service.writer.WritableRecord WritableRecord}����擾���Đݒ肷��ۂ́AWritableRecord���̊J�n�C���f�b�N�X���擾����B<p>
     *
     * @return WritableRecord���̊J�n�C���f�b�N�X
     */
    public int getBodyIndex();
    
    /**
     * ���[���̖{����ݒ肷��B<p>
     *
     * @param text ���[���̖{��
     */
    public void setBodyText(String text);
    
    /**
     * ���[���̖{�����擾����B<p>
     *
     * @return ���[���̖{��
     */
    public String getBodyText();
    
    /**
     * ���[���̖{���̕����G���R�[�f�B���O��ݒ肷��B<p>
     *
     * @param encoding ���[���̖{���̕����G���R�[�f�B���O
     */
    public void setBodyEncoding(String encoding);
    
    /**
     * ���[���̖{���̕����G���R�[�f�B���O���擾����B<p>
     *
     * @return ���[���̖{���̕����G���R�[�f�B���O
     */
    public String getBodyEncoding();
    
    /**
     * SMTP�T�[�o�̃z�X�g����ݒ肷��B<p>
     *
     * @param name SMTP�T�[�o�̃z�X�g��
     */
    public void setSmtpHostName(String name);
    
    /**
     * SMTP�T�[�o�̃z�X�g�����擾����B<p>
     *
     * @return SMTP�T�[�o�̃z�X�g��
     */
    public String getSmtpHostName();
    
    /**
     * SMTP�T�[�o�̃|�[�g�ԍ���ݒ肷��B<p>
     *
     * @param port SMTP�T�[�o�̃|�[�g�ԍ�
     */
    public void setSmtpPort(int port);
    
    /**
     * SMTP�T�[�o�̃|�[�g�ԍ����擾����B<p>
     *
     * @return SMTP�T�[�o�̃|�[�g�ԍ�
     */
    public int getSmtpPort();
    
    /**
     * {@link jp.ossc.nimbus.service.keepalive.smtp.SmtpKeepAliveChecker SmtpKeepAliveChecker}��I������{@link jp.ossc.nimbus.service.keepalive.KeepAliveCheckerSelector KeepAliveCheckerSelector}�T�[�r�X�̃T�[�r�X����ݒ肷��B<p>
     *
     * @param name KeepAliveCheckerSelector�T�[�r�X�̃T�[�r�X��
     */
    public void setSmtpKeepAliveCheckerSelectorServiceName(ServiceName name);
    
    /**
     * {@link jp.ossc.nimbus.service.keepalive.smtp.SmtpKeepAliveChecker SmtpKeepAliveChecker}��I������{@link jp.ossc.nimbus.service.keepalive.KeepAliveCheckerSelector KeepAliveCheckerSelector}�T�[�r�X�̃T�[�r�X�����擾����B<p>
     *
     * @return KeepAliveCheckerSelector�T�[�r�X�̃T�[�r�X��
     */
    public ServiceName getSmtpKeepAliveCheckerSelectorServiceName();
    
    /**
     * ���[�����M���g���C�񐔂�ݒ肷��B<p>
     *
     * @param count ���[�����M���g���C��
     */
    public void setRetryCount(int count);
    
    /**
     * ���[�����M���g���C�񐔂��擾����B<p>
     *
     * @return ���[�����M���g���C��
     */
    public int getRetryCount();
    
    /**
     * ���[�����M���g���C�Ԋu[ms]��ݒ肷��B<p>
     *
     * @param millis ���[�����M���g���C�Ԋu[ms]
     */
    public void setRetryInterval(long millis);
    
    /**
     * ���[�����M���g���C�Ԋu[ms]���擾����B<p>
     *
     * @return ���[�����M���g���C�Ԋu[ms]
     */
    public long getRetryInterval();
    
    /**
     * javax.mail.Session��JNDI����lookup����ۂɎg�p����{@link jp.ossc.nimbus.service.jndi.JndiFinder JndiFinder}�T�[�r�X�̃T�[�r�X����ݒ肷��B<p>
     *
     * @param name JndiFinder�T�[�r�X�̃T�[�r�X��
     */
    public void setJndiFinderServiceName(ServiceName name);
    
    /**
     * javax.mail.Session��JNDI����lookup����ۂɎg�p����{@link jp.ossc.nimbus.service.jndi.JndiFinder JndiFinder}�T�[�r�X�̃T�[�r�X�����擾����B<p>
     *
     * @return JndiFinder�T�[�r�X�̃T�[�r�X��
     */
    public ServiceName getJndiFinderServiceName();
    
    /**
     * javax.mail.Session��JNDI����lookup����ۂɎg�p����JNDI����ݒ肷��B<p>
     *
     * @param name javax.mail.Session��JNDI��
     */
    public void setMailSessionJndiName(String name);
    
    /**
     * javax.mail.Session��JNDI����lookup����ۂɎg�p����JNDI�����擾����B<p>
     *
     * @return javax.mail.Session��JNDI��
     */
    public String getMailSessionJndiName();
}