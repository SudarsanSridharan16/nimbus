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
package jp.ossc.nimbus.service.ejb.transaction;

/**
 * EJB�R���e�i���ł̃g�����U�N�V�����R���g���[���N���X<p>
 * ���̃N���X��EJB�R���e�i���̃g�����U�N�V�����}�l�[�W�����g��<br>
 * �O���[�o���g�����U�N�V�����̃R���g���[�����s���B
 * @author   H.Nakano
 * @version  1.00 �쐬: 2003/11/28 -�@H.Nakano
 * @since	1.00
 * @see	javax.transaction.TransactionManager
 **/
public interface TransactionControl {
	/**
	 * �O���[�o���g�����U�N�V�����𒆒f����B<p>
	 * ���łɒ��f���X�e�[�g�ł���ꍇ�͖��������B
	 */
	public void suspend() ;
	/**
	 * �O���[�o���g�����U�N�V�������ĊJ����B<p>
	 */
	public void resume() ;
	/**
	 * �V�����O���[�o���g�����U�N�V�������J�n����B<p>
	 * ���̃��\�b�h�̓J�����g�̃O���[�o���g�����U�N�V������<br>
	 * �T�X�y���h������ԂŗL���ɂȂ�B
	 * @see #suspend()
	 */
	public void beginNewTransaction() ;
	/**
	 * �J�n����Ă���V�����g�����U�N�V�����ɂ��ăR�~�b�g����B<P>
	 */
	public void commitNewTransaction() ;
	/**
	 * �J�n����Ă���V�����g�����U�N�V�����ɂ��ă��[���o�b�N����B<P>
	 */
	public void rollBackNewTransaction() ;
	public void terminateTransactioinControl();
	/**
	 * ���݂̃g�����U�N�V�����̏�Ԃ��o�͂���B<p>
	 * @return �g�����U�N�V�������
	 * @see	#INIT_STATE
	 * @see	#SUSPEND_STATE
	 **/
	public int getState() ;
	/** �������<p>*/
	public static final int INIT_STATE = 0 ;
	/** �T�X�y���h���<p>*/
	public static final int SUSPEND_STATE = 1 ;
}
