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
package jp.ossc.nimbus.service.journal.editor;

/**
 * {@link UnitOfWorkJournalEditorService}��MBean�C���^�t�F�[�X�B<p>
 * 
 * @author M.Takata
 * @see UnitOfWorkJournalEditorService
 */
public interface UnitOfWorkJournalEditorServiceMBean
 extends BlockJournalEditorServiceBaseMBean{
    
    /**
     * ���s�X�e�[�^�X���o�͂��邩�ǂ�����ݒ肷��B<p>
     * �f�t�H���g�ł́Atrue�B<br>
     *
     * @param isOutput �o�͂���ꍇ�́Atrue
     */
    public void setOutputStatus(boolean isOutput);
    
    /**
     * ���s�X�e�[�^�X���o�͂��邩�ǂ����𔻒肷��B<p>
     *
     * @return true�̏ꍇ�́A�o�͂���
     */
    public boolean isOutputStatus();
    
    /**
     * {@link jp.ossc.nimbus.ioc.UnitOfWork UnitOfWork}�̐����o�͂��邩�ǂ�����ݒ肷��B<p>
     * �f�t�H���g�ł́Atrue�B<br>
     *
     * @param isOutput �o�͂���ꍇ�́Atrue
     */
    public void setOutputUnitOfWorkSize(boolean isOutput);
    
    /**
     * {@link jp.ossc.nimbus.ioc.UnitOfWork UnitOfWork}�̐����o�͂��邩�ǂ����𔻒肷��B<p>
     *
     * @return true�̏ꍇ�́A�o�͂���
     */
    public boolean isOutputUnitOfWorkSize();
    
    /**
     * {@link jp.ossc.nimbus.ioc.UnitOfWork UnitOfWork}�̎��s�����o�͂��邩�ǂ�����ݒ肷��B<p>
     * �f�t�H���g�ł́Atrue�B<br>
     *
     * @param isOutput �o�͂���ꍇ�́Atrue
     */
    public void setOutputUnitOfWorkExecuteSize(boolean isOutput);
    
    /**
     * {@link jp.ossc.nimbus.ioc.UnitOfWork UnitOfWork}�̎��s�����o�͂��邩�ǂ����𔻒肷��B<p>
     *
     * @return true�̏ꍇ�́A�o�͂���
     */
    public boolean isOutputUnitOfWorkExecuteSize();
    
    /**
     * {@link jp.ossc.nimbus.ioc.Command Command}�̐����o�͂��邩�ǂ�����ݒ肷��B<p>
     * �f�t�H���g�ł́Atrue�B<br>
     *
     * @param isOutput �o�͂���ꍇ�́Atrue
     */
    public void setOutputCommandSize(boolean isOutput);
    
    /**
     * {@link jp.ossc.nimbus.ioc.Command Command}�̐����o�͂��邩�ǂ����𔻒肷��B<p>
     *
     * @return true�̏ꍇ�́A�o�͂���
     */
    public boolean isOutputCommandSize();
    
    /**
     * {@link jp.ossc.nimbus.ioc.Command Command}�̎��s�����o�͂��邩�ǂ�����ݒ肷��B<p>
     * �f�t�H���g�ł́Atrue�B<br>
     *
     * @param isOutput �o�͂���ꍇ�́Atrue
     */
    public void setOutputCommandExecuteSize(boolean isOutput);
    
    /**
     * {@link jp.ossc.nimbus.ioc.Command Command}�̎��s�����o�͂��邩�ǂ����𔻒肷��B<p>
     *
     * @return true�̏ꍇ�́A�o�͂���
     */
    public boolean isOutputCommandExecuteSize();
    
    /**
     * ����������O�̐����o�͂��邩�ǂ�����ݒ肷��B<p>
     * �f�t�H���g�ł́Atrue�B<br>
     *
     * @param isOutput �o�͂���ꍇ�́Atrue
     */
    public void setOutputExceptionCount(boolean isOutput);
    
    /**
     * ����������O�̐����o�͂��邩�ǂ����𔻒肷��B<p>
     *
     * @return true�̏ꍇ�́A�o�͂���
     */
    public boolean isOutputExceptionCount();
    
    /**
     * ����������O���o�͂��邩�ǂ�����ݒ肷��B<p>
     * �f�t�H���g�ł́Atrue�B<br>
     *
     * @param isOutput �o�͂���ꍇ�́Atrue
     */
    public void setOutputExceptions(boolean isOutput);
    
    /**
     * ����������O���o�͂��邩�ǂ����𔻒肷��B<p>
     *
     * @return true�̏ꍇ�́A�o�͂���
     */
    public boolean isOutputExceptions();
    
    /**
     * {@link jp.ossc.nimbus.ioc.CommandBase CommandBase}���o�͂��邩�ǂ�����ݒ肷��B<p>
     * �f�t�H���g�ł́Atrue�B<br>
     *
     * @param isOutput �o�͂���ꍇ�́Atrue
     */
    public void setOutputCommandBases(boolean isOutput);
    
    /**
     * {@link jp.ossc.nimbus.ioc.CommandBase CommandBase}���o�͂��邩�ǂ����𔻒肷��B<p>
     *
     * @return true�̏ꍇ�́A�o�͂���
     */
    public boolean isOutputCommandBases();
    
}