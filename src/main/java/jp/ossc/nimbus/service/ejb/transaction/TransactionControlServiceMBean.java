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

import jp.ossc.nimbus.core.*;
//
/**
 * �g�����U�N�V�����R���g���[���T�[�r�X�Ǘ��C���^�[�t�F�C�X<p> 
 * @author   H.Nakano
 * @version  1.00 �쐬: 2003/11/28 -�@H.Nakano
 */
public interface TransactionControlServiceMBean extends ServiceBaseMBean{
	/**
	 * �g�����U�N�V�����}�l�[�W���[�̎擾���[�h��ݒ肷��B<p>
	 * @param isJNDIMode
	 */
	public void setJNDIMode(boolean isJNDIMode) ;
	/**
	 * JNDIService����ݒ肷��B<p>
	 * @param name
	 */
	public void setJNDIServiceName(ServiceName name) ;
	/**
	 * setTransactionManagerFactoryClassName
	 * @param clsName
	 */
	public void setTransactionManagerFactoryClassName(String clsName) ;
	/**
	 * setGetTransactinManagerMethodName
	 * @param methodName
	 */
	public void setGetTransactinManagerMethodName(String methodName) ;
}