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
// �p�b�P�[�W
package jp.ossc.nimbus.ioc;
//�C���|�[�g

/**
 * EJB���s�R�}���h�C���^�[�t�F�C�X<p>
 * @version $Name:  $
 * @author H.Nakano
 * @since 1.0
 */
public interface Command extends CommandBase {
	/**
	 * ���̓I�u�W�F�N�g��ݒ肷��
	 * @param obj�@���̓I�u�W�F�N�g
	 */
	public void setInputObject(Object obj) ;
	/**
	 * �o�̓I�u�W�F�N�g��ݒ肷��
	 * @param obj	�o�̓I�u�W�F�N�g
	 */
	public void setOutObject(Object obj) ;
	/**
	 * �o�̓I�u�W�F�N�g���o�͂���
	 * @return	�o�̓I�u�W�F�N�g
	 */
	public Object getOutputObject() ;	
	/**
	 * ���̓I�u�W�F�N�g����͂���
	 * @return	���̓I�u�W�F�N�g
	 */
	public Object getInputObject() ;
	/**
	 * ������O��ݒ肷��
	 * @param e
	 */
	public void setException(Throwable e) ;	
	/**
	 * ������O���擾����
	 * @return ������O
	 */
	public Throwable getException() ;	
	/**
	 * ���s����Bean�t���[�̃t���[�L�[���o�͂���
	 * @return	�t���[�L�[
	 */
	public String getFlowKey() ;
	/**
	 * ���s����Bean�t���[�̃t���[�L�[��ݒ肷��
	 * @param flowKey	�t���[�L�[
	 */
	public void setFlowKey(String flowKey) ;
}