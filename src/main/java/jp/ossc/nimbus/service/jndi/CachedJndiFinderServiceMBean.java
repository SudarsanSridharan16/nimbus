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
package jp.ossc.nimbus.service.jndi;

import java.util.*;
import javax.naming.NamingException;

import jp.ossc.nimbus.core.*;

/**
 * {@link CachedJndiFinderService}��MBean�C���^�t�F�[�X<p>
 * 
 * @author Y.Tokuda
 * @see CachedJndiFinderService
 */
public interface CachedJndiFinderServiceMBean extends ServiceBaseMBean{
    
    /**
     * JNDI�T�[�o�����`�F�b�N�ŁAJNDI�T�[�o�����񂾎��ɏo�͂���郍�O�̃��b�Z�[�WID�B<p>
     */
    public static final String JNDI_SERVER_DEAD_MSG_ID = "CJF__00001";
    
    /**
     * JNDI�T�[�o�����`�F�b�N�ŁAJNDI�T�[�o�����A�������ɏo�͂���郍�O�̃��b�Z�[�WID�B<p>
     */
    public static final String JNDI_SERVER_RECOVER_MSG_ID = "CJF__00002";
    
    /**
     * ���g���C�̑ΏۂƂ����O�̃f�t�H���g�l�B<p>
     * <ul>
     *     <li>javax.naming.CommunicationException</li>
     *     <li>javax.naming.InsufficientResourcesException</li>
     *     <li>javax.naming.InterruptedNamingException</li>
     *     <li>javax.naming.TimeLimitExceededException</li>
     *     <li>javax.naming.ServiceUnavailableException</li>
     * </ul>
     */
    public static final String[] DEFAULT_RETRY_EXCXEPTION_NAME = new String[]{
        javax.naming.CommunicationException.class.getName(),
        javax.naming.InsufficientResourcesException.class.getName(),
        javax.naming.InterruptedNamingException.class.getName(),
        javax.naming.TimeLimitExceededException.class.getName(),
        javax.naming.ServiceUnavailableException.class.getName()
    };
    
    /**
     * InitialContext�̏������Ɏg�p����JNDI���ϐ���ݒ肷��B<p>
     * 
     * @param prop JNDI���ϐ����i�[�����v���p�e�B
     */
    public void setEnvironment(Properties prop);
    
    /**
     * InitialContext�̏������Ɏg�p����JNDI���ϐ����擾����B<p>
     * 
     * @return JNDI���ϐ����i�[�����v���p�e�B
     * @exception NamingException JNDI���ϐ����擾�ł��Ȃ������ꍇ
     */
    public Properties getEnvironment() throws NamingException;
    
    /**
     * lookup���Ɏg�p����JNDI�v���t�B�b�N�X��ݒ肷��B<p>
     * �f�t�H���g�́A�󕶎��B<br>
     *
     * @param prefix JNDI�v���t�B�b�N�X
     */
    public void setPrefix(String prefix);
    
    /**
     * lookup���Ɏg�p����JNDI�v���t�B�b�N�X���擾����B<p>
     *
     * @return JNDI�v���t�B�b�N�X
     */
    public String getPrefix();
    
    /**
     * lookup���������[�g�I�u�W�F�N�g���L���b�V������L���b�V���T�[�r�X����ݒ肷��B<p>
     * ���̑�����ݒ肵�Ȃ��ꍇ�́A�����[�g�I�u�W�F�N�g���L���b�V�����Ȃ��B<br>
     *
     * @param name �L���b�V���T�[�r�X��
     */
    public void setCacheMapServiceName(ServiceName name);
    
    /**
     * lookup���������[�g�I�u�W�F�N�g���L���b�V������L���b�V���T�[�r�X�����擾����B<p>
     *
     * @return �L���b�V���T�[�r�X��
     */
    public ServiceName getCacheMapServiceName();
    
    /** 
     * lookup���Ƀ��g���C��O�����������ꍇ�Ƀ��g���C����񐔂�ݒ肷��B<p>
     * �f�t�H���g�́A0�Ń��g���C���Ȃ��B<br>
     *
     * @param num ���g���C��
     * @see #setRetryExceptionClassNames(String[])
     */
    public void setRetryCount(int num);
    
    /** 
     * lookup���Ƀ��g���C��O�����������ꍇ�Ƀ��g���C����񐔂��擾����B<p>
     *
     * @return ���g���C��
     */
    public int getRetryCount();
    
    /** 
     * lookup���Ƀ��g���C��O�����������ꍇ�Ƀ��g���C����Ԋu[msec]��ݒ肷��B<p>
     * �f�t�H���g�́A1000�B<br>
     *
     * @param interval ���g���C�Ԋu
     * @see #setRetryExceptionClassNames(String[])
     */
    public void setRetryInterval(long interval);
    
    /** 
     * lookup���Ƀ��g���C��O�����������ꍇ�Ƀ��g���C����Ԋu[msec]���擾����B<p>
     *
     * @return ���g���C�Ԋu
     */
    public long getRetryInterval();
    
    /** 
     * lookup����javax.naming.NamingException�����������ꍇ�ɁA���g���C�����O�N���X����ݒ肷��B<p>
     * �f�t�H���g�́A{@link #DEFAULT_RETRY_EXCXEPTION_NAME}�B<br>
     *
     * @param classNames ���g���C�����O�N���X���z��
     */
    public void setRetryExceptionClassNames(String[] classNames);
    
    /** 
     * lookup����javax.naming.NamingException�����������ꍇ�ɁA���g���C�����O�N���X�����擾����B<p>
     *
     * @return ���g���C�����O�N���X���z��
     */
    public String[] getRetryExceptionClassNames();
    
    /**
     * JNDI�T�[�o�̐����`�F�b�N���s�����ǂ�����ݒ肷��B<p>
     * true�ɐݒ肳�ꂽ�ꍇ�A{@link #setAliveCheckJNDIServerInterval(long)}�Őݒ肳�ꂽ�Ԋu�ŁA���[�g�R���e�L�X�g�i"/"�j��lookup���āAJNDI�T�[�o�̐����`�F�b�N���s���B<br>
     * ���[�g�R���e�L�X�g���擾�ł��Ȃ��Ȃ����ꍇ�A�G���[���O���o�͂��āA�L���b�V�����N���A����B�܂��A���[�g�R���e�L�X�g���擾�ł���悤�ɂȂ����ꍇ�A�ʒm���O���o�͂���B<br>
     *
     * @param isCheck �����`�F�b�N���s���ꍇ��true
     */
    public void setAliveCheckJNDIServer(boolean isCheck);
    
    /**
     * JNDI�T�[�o�̐����`�F�b�N���s�����ǂ�����ݒ肷��B<p>
     *
     * @return �����`�F�b�N���s���ꍇ��true
     */
    public boolean isAliveCheckJNDIServer();
    
    /**
     * JNDI�T�[�o�̐����`�F�b�N���s���Ԋu[msec]��ݒ肷��B<p>
     * �f�t�H���g�́A60000[msec]�B
     * 
     * @param interval JNDI�T�[�o�̐����`�F�b�N���s���Ԋu[msec]
     */
    public void setAliveCheckJNDIServerInterval(long interval);
    
    /**
     * JNDI�T�[�o�̐����`�F�b�N���s���Ԋu[msec]���擾����B<p>
     * 
     * @return JNDI�T�[�o�̐����`�F�b�N���s���Ԋu[msec]
     */
    public long getAliveCheckJNDIServerInterval();
    
    /**
     * JNDI�T�[�o���������Ă��邩�ǂ����𒲂ׂ�B<p>
     * {@link #isAliveCheckJNDIServer()}��true��Ԃ��ꍇ�́A�Ō�Ƀ`�F�b�N�������̏�Ԃ�Ԃ��B<br>
     * isAliveCheckJNDIServer()��false��Ԃ��ꍇ�́A�����Ƀ`�F�b�N���Č��ʂ�Ԃ��B�A���A�T�[�r�X���J�n���Ă��Ȃ��ꍇ�́Afalse��Ԃ��B<br>
     * 
     * @return JNDI�T�[�o���������Ă���ꍇtrue
     */
    public boolean isAliveJNDIServer();
    
    /**
     * JNDI�T�[�o���_�E�������������m�����|�̃��O���o�͂��邩�ǂ�����ݒ肷��B<p>
     * �f�t�H���g�́Atrue�B<br>
     *
     * @param isOutput ���O���o�͂���ꍇtrue
     */
    public void setLoggingDeadJNDIServer(boolean isOutput);
    
    /**
     * JNDI�T�[�o���_�E�������������m�����|�̃��O���o�͂��邩�ǂ����𔻒肷��B<p>
     *
     * @return ���O���o�͂���ꍇtrue
     */
    public boolean isLoggingDeadJNDIServer();
    
    /**
     * JNDI�T�[�o�����A�����������m�����|�̃��O���o�͂��邩�ǂ�����ݒ肷��B<p>
     * �f�t�H���g�́Atrue�B<br>
     *
     * @param isOutput ���O���o�͂���ꍇtrue
     */
    public void setLoggingRecoverJNDIServer(boolean isOutput);
    
    /**
     * JNDI�T�[�o�����A�����������m�����|�̃��O���o�͂��邩�ǂ����𔻒肷��B<p>
     *
     * @return ���O���o�͂���ꍇtrue
     */
    public boolean isLoggingRecoverJNDIServer();
    
    /**
     * JNDI�T�[�o���_�E�������������m�����|�̃��O�o�͂̃��b�Z�[�WID��ݒ肷��B<p>
     * �f�t�H���g�́A{@link #JNDI_SERVER_DEAD_MSG_ID}�B<br>
     *
     * @param id ���O�o�͂̃��b�Z�[�WID
     */
    public void setDeadJNDIServerLogMessageId(String id);
    
    /**
     * JNDI�T�[�o���_�E�������������m�����|�̃��O�o�͂̃��b�Z�[�WID���擾����B<p>
     *
     * @return ���O�o�͂̃��b�Z�[�WID
     */
    public String getDeadJNDIServerLogMessageId();
    
    /**
     * JNDI�T�[�o�����A�����������m�����|�̃��O�o�͂̃��b�Z�[�WID��ݒ肷��B<p>
     * �f�t�H���g�́A{@link #JNDI_SERVER_RECOVER_MSG_ID}�B<br>
     *
     * @param id ���O�o�͂̃��b�Z�[�WID
     */
    public void setRecoverJNDIServerLogMessageId(String id);
    
    /**
     * JNDI�T�[�o�����A�����������m�����|�̃��O�o�͂̃��b�Z�[�WID���擾����B<p>
     *
     * @return ���O�o�͂̃��b�Z�[�WID
     */
    public String getRecoverJNDIServerLogMessageId();
    
    /**
     * �L���b�V�����������[�g�I�u�W�F�N�g��S�ăN���A����B<p>
     */
    public void clearCache();
    
    /**
     * �w�肵��JNDI���̂��������[�g�I�u�W�F�N�g�̃L���b�V�����N���A����B<p>
     * 
     * @param jndiName �L���b�V������폜���郊���[�g�I�u�W�F�N�g��JNDI��
     */
    public void clearCache(String jndiName);
    
    public String listContext() throws NamingException;
}
