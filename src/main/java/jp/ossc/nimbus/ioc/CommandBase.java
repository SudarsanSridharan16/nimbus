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
//�p�b�P�[�W
package jp.ossc.nimbus.ioc;
// �C���|�[�g
/**
 * �R�}���h�̊�{�C���^�[�t�F�C�X<p>
 * @version $Name:  $
 * @author H.Nakano
 * @since 1.0
 * @see	Command
 * @see	UnitOfWorkImpl 
 */
public interface CommandBase {
	/** �R�}���h�̎��s�O�X�e�[�^�X */
	static public final int C_STATUS_BEFORE = -1 ;
	/** �R�}���h�̐���I���X�e�[�^�X */
	static public final int C_STATUS_COMPLETE = 0 ;
	/** �R�}���h�ُ̈�I���X�e�[�^�X */
	static public final int C_STATUS_ERROR = 1 ;
	/**
	 * �R�}���h�������o�͂���
	 * @return�@�R�}���h�����Ȃ�ture
	 */
	public boolean isCommand() ;
	/**
	 * ����������O�������o�͂���
	 * @return�@����������O����
	 */
	public int getExceptionCount() ;
	/**
	 * ����������O��z��ŏo�͂���B
	 * @return�@��O�z��
	 */
	public Throwable[] getExceptions();
	/**
	 * ��O�̔��������R�}���h����������
	 * @param e�@����������O�I�u�W�F�N�g
	 * @return	�R�}���h�I�u�W�F�N�g
	 * @see	Command
	 */
	public Command findErrorCommand(Throwable e) ;
	/**
	 * �i�[����Ă���R�}���h�̑���
	 * @return	�R�}���h����
	 */
	public int commandSize() ;
	/**
	 * ���s�����R�}���h�̑���
	 * @return	���s�R�}���h��
	 */
	public int commandExecuteSize() ;
	
	/**
	 * ���s���ʂ��o�͂���
	 * 
	 * @return �R�}���h�̎��s�O�X�e�[�^�X 		C_STATUS_BEFORE <BR> 
	 *   		�R�}���h�̐���I���X�e�[�^�X 	C_STATUS_COMPLETE <BR>
	 *   		�R�}���h�ُ̈�I���X�e�[�^�X 	C_STATUS_ERROR<BR> 
	 */
	public int getStatus() ;
	/**
	 * �i�[����Ă��郆�j�b�g�I�u���[�N�̑������o�͂���
	 * @return	�i�[����Ă��郆�j�b�g�I�u���[�N�̑���
	 */
	public int unitOfWorkSize() ;
	/**
	 * ������s���ꂽ���j�b�g�I�u���[�N�̑������o�͂���
	 * @return	������s���ꂽ���j�b�g�I�u���[�N�̑���
	 */
	public int unitOfWorkExecuteSize() ;
}
