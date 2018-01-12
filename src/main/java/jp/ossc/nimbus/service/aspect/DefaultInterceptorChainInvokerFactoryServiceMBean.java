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
package jp.ossc.nimbus.service.aspect;
//�C���|�[�g
import jp.ossc.nimbus.core.*;
/**
 * �C���^�[�Z�v�^�[�`�F�[���C���{�[�J�[�t�@�N�g��MBean�C���^�[�t�F�C�X
 * @version $Name:  $
 * @author H.Nakano
 * @since 1.0
 */
public interface DefaultInterceptorChainInvokerFactoryServiceMBean
	extends ServiceBaseMBean {
	/** 
	 * �C���^�[�Z�v�^��`�t�@�C����ݒ�<br>
	 * @param fileNames - �C���^�[�Z�v�^��`
	 * @see #getInterceptConfigFileName()
	 */
	public void setInterceptorConfigFileNames(String[] fileNames);
	//
	/**
	 * �C���^�[�Z�v�^��`�t�@�C����ԋp����B<br>
	 * @return String[] - �C���^�[�Z�v�^��`�t�@�C��
	 * @see #setInterceptorConfigFileNames(String)
	 */
	public String[] getInterceptorConfigFileNames();
	/**
	 * �R�[���o�b�N�Ώۂ̃N���X����ݒ肷��<br>
	 * @param String			�R�[���o�b�N�Ώۂ̃N���X��
	 */
	public void setCallbackClassName(String callbackClassName);
	/**
	 * �R�[���o�b�N�Ώۂ̃N���X����ԋp����<br>
	 * @return String			�R�[���o�b�N�Ώۂ̃N���X��
	 */
	public String getCallbackClassName();
	/**
	 * �R�[���o�b�N���\�b�h����ݒ肷��<br>
	 * @param String			�R�[���o�b�N���\�b�h��
	 */
	public void setCallbackMethodName(String callbackMethodName);
	/**
	 * �R�[���o�b�N���\�b�h����ԋp����<br>
	 * @return String			�R�[���o�b�N���\�b�h��
	 */
	public String getCallbackMethodName();
	/**
	 * �R�[���o�b�N���\�b�h�p���[���^�N���X���z���ݒ肷��<br>
	 * @param String[]			�R�[���o�b�N���\�b�h�p���[���^�N���X���z��
	 */
	public void setCallbackMethodParamClassNames(String[] callbackMethodParamClassNames);
	/**
	 * �R�[���o�b�N���\�b�h����ԋp����<br>
	 * @return String			�R�[���o�b�N���\�b�h��
	 */
	public String[] getCallbackMethodParamClassNames();
	/**
	 * �C���^�[�Z�v�^�`�F�C���N���X����ݒ肷��<br>
	 * @param String			�C���^�[�Z�v�^�`�F�C���N���X��
	 */
	public void setInterceptorInvokerClassName(String interceptorInvokerClassName);
	/**
	 * �C���^�[�Z�v�^�`�F�C���N���X����ԋp����<br>
	 * @return String			�C���^�[�Z�v�^�`�F�C���N���X��
	 */
	public String getInterceptorInvokerClassName();
	/**
	 * ��`�t�@�C���E�ϊ��t�@�C����ǂݍ���<br>
	 */
	public void loadConfig() throws InvalidConfigurationException;
	/**
	 * ���O�o�͂̃T�[�r�X���̐ݒ���s��<br>
	 * @param name �T�[�r�X��
	 */
	public void setLoggerServiceName(ServiceName name) ;
}
