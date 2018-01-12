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

import jp.ossc.nimbus.core.ServiceBaseMBean;
import jp.ossc.nimbus.core.ServiceName;

/**
 * {@link TransactionSynchronizerService}��MBean�C���^�t�F�[�X<p>
 * 
 * @author M.Takata
 * @see TransactionSynchronizerService
 */
public interface TransactionSynchronizerServiceMBean extends ServiceBaseMBean{
    
    /**
     * �g�����U�N�V�������O�e�[�u���̗�u�����������ǂ����������t���O�v�̗񖼂̃f�t�H���g�l�B<p>
     */
    public static final String DEFAULT_COLUMN_NAME_SYNCHRONIZE = "SYNC";
    
    /**
     * ��������{@link ConnectionFactory}�T�[�r�X�̃T�[�r�X����ݒ肷��B<p>
     *
     * @param name ConnectionFactory�T�[�r�X�̃T�[�r�X��
     */
    public void setSourceConnectionFactoryServiceName(ServiceName name);
    
    /**
     * ��������{@link ConnectionFactory}�T�[�r�X�̃T�[�r�X�����擾����B<p>
     *
     * @return ConnectionFactory�T�[�r�X�̃T�[�r�X��
     */
    public ServiceName getSourceConnectionFactoryServiceName();
    
    /**
     * �������{@link ConnectionFactory}�T�[�r�X�̃T�[�r�X����ݒ肷��B<p>
     *
     * @param name ConnectionFactory�T�[�r�X�̃T�[�r�X��
     */
    public void setDestinationConnectionFactoryServiceName(ServiceName name);
    
    /**
     * �������{@link ConnectionFactory}�T�[�r�X�̃T�[�r�X�����擾����B<p>
     *
     * @return ConnectionFactory�T�[�r�X�̃T�[�r�X��
     */
    public ServiceName getDestinationConnectionFactoryServiceName();
    
    /**
     * �g�����U�N�V�������O�e�[�u���̃e�[�u������ݒ肷��B<p>
     * �f�t�H���g�́A{@link jp.ossc.nimbus.util.sql.TransactionLoggingConnection#DEFAULT_TRANSACTION_TABLE_NAME}�B<br>
     *
     * @param name �e�[�u����
     */
    public void setTransactionTableName(String name);
    
    /**
     * �g�����U�N�V�������O�e�[�u���̃e�[�u�������擾����B<p>
     *
     * @return �e�[�u����
     */
    public String getTransactionTableName();
    
    /**
     * �g�����U�N�V�����p�����[�^���O�e�[�u���̃e�[�u������ݒ肷��B<p>
     * �f�t�H���g�́A{@link jp.ossc.nimbus.util.sql.TransactionLoggingConnection#DEFAULT_TRANSACTION_PARAM_TABLE_NAME}�B<br>
     *
     * @param name �e�[�u����
     */
    public void setTransactionParamTableName(String name);
    
    /**
     * �g�����U�N�V�����p�����[�^���O�e�[�u���̃e�[�u�������擾����B<p>
     *
     * @return �e�[�u����
     */
    public String getTransactionParamTableName();
    
    /**
     * {@link jp.ossc.nimbus.service.transaction.TransactionManagerFactory}�T�[�r�X�̃T�[�r�X����ݒ肷��B<p>
     * �ݒ肵���ꍇ�A������ւ̃g�����U�N�V�������s�Ɠ������̃g�����U�N�V�������O�̍폜�𓯈�g�����U�N�V�����Ŏ��s����B<br>
     *
     * @param name TransactionManagerFactory�T�[�r�X�̃T�[�r�X��
     */
    public void setTransactionManagerFactoryServiceName(ServiceName name);
    
    /**
     * {@link jp.ossc.nimbus.service.transaction.TransactionManagerFactory}�T�[�r�X�̃T�[�r�X�����擾����B<p>
     *
     * @return TransactionManagerFactory�T�[�r�X�̃T�[�r�X��
     */
    public ServiceName getTransactionManagerFactoryServiceName();
    
    /**
     * �g�����U�N�V�������O�e�[�u���̗�u�����������ǂ����������t���O�v�̗񖼂�ݒ肷��B<p>
     * �f�t�H���g�́A{@link #DEFAULT_COLUMN_NAME_SYNCHRONIZE}�B<br>
     *
     * @param name ��
     */
    public void setSynchronizeColumnName(String name);
    
    /**
     * �g�����U�N�V�������O�e�[�u���̗�u�����������ǂ����������t���O�v�̗񖼂��擾����B<p>
     *
     * @return ��
     */
    public String getSynchronizeColumnName();
    
    /**
     * �������ɁA���������g�����U�N�V�������O���폜���邩�ǂ�����ݒ肷��B<p>
     * �폜���Ȃ��ꍇ�́A�g�����U�N�V�������O�e�[�u���̗�u�����������ǂ����������t���O�v�ɁA"1"���X�V����B<br>
     * �f�t�H���g�́Atrue�ō폜����B<br>
     * 
     * @param isDelete �폜����ꍇ�́Atrue
     */
    public void setDeleteOnSynchronize(boolean isDelete);
    
    /**
     * �������ɁA���������g�����U�N�V�������O���폜���邩�ǂ����𔻒肷��B<p>
     * 
     * @return true�̏ꍇ�A�폜����
     */
    public boolean isDeleteOnSynchronize();
    
    /**
     * ���������g�����U�N�V�������O��|������ۂɁA�폜�����ƂȂ�g�����U�N�V�������O�e�[�u���̗�u�����������ǂ����������t���O�v�̗񖼂�ݒ肷��B<p>
     * �w�肳��Ă��Ȃ��ꍇ�́A{@link #getSynchronizeColumnName()}�݂̂�ΏۂƂ���B�w�肳�ꂽ�ꍇ�́A{@link #getSynchronizeColumnName()}�Ǝw�肳�ꂽ���ΏۂƂ���B<br>
     *
     * @param names �񖼂̔z��
     */
    public void setGarbageSynchronizeColumnNames(String[] names);
    
    /**
     * ���������g�����U�N�V�������O��|������ۂɁA�폜�����ƂȂ�g�����U�N�V�������O�e�[�u���̗�u�����������ǂ����������t���O�v�̗񖼂��擾����B<p>
     *
     * @return �񖼂̔z��
     */
    public String[] getGarbageSynchronizeColumnNames();
    
    /**
     * ���������g�����U�N�V�������O�̗L������[ms]��ݒ肷��B<p>
     * {@link #isDeleteOnSynchronize()}��true�̏ꍇ�̂ݗL���ŁA�g�����U�N�V�������O�̍X�V���������ݎ��������w�莞��[ms]�ȏ�O�̃��O�ŁA�����ς݂̂��̂��폜����B<br>
     * �f�t�H���g�́A-1�ő|�����Ȃ��B<br>
     * 
     * @param millis �L������[ms]
     */
    public void setGarbageTime(long millis);
    
    /**
     * ���������g�����U�N�V�������O�̗L������[ms]���擾����B<p>
     * 
     * @return �L������[ms]
     */
    public long getGarbageTime();
    
    /**
     * �X�V���[�U����ݒ肷��B<p>
     * �f�t�H���g�́A�z�X�g���B<br>
     *
     * @param name �X�V���[�U��
     */
    public void setUpdateUser(String name);
    
    /**
     * �X�V���[�U�����擾����B<p>
     *
     * @return �X�V���[�U��
     */
    public String getUpdateUser();
    
    /**
     * �T�[�r�X�̋N�����ɓ������s�����ǂ�����ݒ肷��B<p>
     * �f�t�H���g�́Afalse�ŁA�N�����ɓ������Ȃ��B<br>
     * 
     * @param isSynchronize �������s���ꍇ�́Atrue
     */
    public void setSynchronizeOnStart(boolean isSynchronize);
    
    /**
     * �T�[�r�X�̋N�����ɓ������s�����ǂ����𔻒肷��B<p>
     * 
     * @return true�̏ꍇ�A�������s��
     */
    public boolean isSynchronizeOnStart();
    
    /**
     * �ő�o�b�`���s������ݒ肷��B<p>
     * �����������s���ꍇ�ɁA�����N�G���̃g�����U�N�V��������������o�b�`���s���s�����A
     * ���[���o�b�N�Z�O�����g���s������\��������̂ŁA���̍ő匏���܂Ńo�b�`�����܂�ƈ�U�R�~�b�g�����B<br>
     * �f�t�H���g�́A10���B<br>
     * 
     * @param max �ő�o�b�`���s����
     */
    public void setMaxBatchCount(int max);
    
    /**
     * �ő�o�b�`���s�������擾����B<p>
     * 
     * @return �ő�o�b�`���s����
     */
    public int getMaxBatchCount();
    
    /**
     * �f�[�^�x�[�X�𓯊�����B<p>
     * �������̃g�����U�N�V�������O��ǂݍ���ŁA������Ƀg�����U�N�V���������s����B���̍ہA���s�����g�����U�N�V�������O�͍폜����B<br>
     *
     * @return ���������g�����U�N�V�����̌���
     * @exception Exception �������ɗ�O�����������ꍇ
     */
    public int synchronize() throws Exception;
    
    /**
     * �������̌��݂̃g�����U�N�V�������O�������擾����B<p>
     *
     * @return �g�����U�N�V�������O����
     * @exception Exception �g�����U�N�V�������O�����̎擾�Ɏ��s�����ꍇ
     */
    public long countTransactionLog() throws Exception;
}