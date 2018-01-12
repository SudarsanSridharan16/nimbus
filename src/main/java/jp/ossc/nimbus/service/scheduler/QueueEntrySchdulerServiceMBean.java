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
package jp.ossc.nimbus.service.scheduler;
//�C���|�[�g
import jp.ossc.nimbus.core.*;
/**
 * �^�C���X�P�W���[���[�N���X<p>
 * ��莞�Ԗ��ɖ₢���킹���グ�Ďw�肳�ꂽ�R�}���h��Queue�ɓ����
 * @version $Name:  $
 * @author H.Nakano
 * @since 1.0
 */
public interface QueueEntrySchdulerServiceMBean extends ServiceBaseMBean {
	/**
	 * Facade�Ăяo���T�[�r�X���ݒ�
	 * @param name
	 */
	public void setFacadeServiceName(ServiceName name) ;
	/**
	 * Facade�Ăяo���T�[�r�X���o��
	 * @return ServiceName
	 */
	public ServiceName getFacadeServiceName() ;
	/**
	 * �₢���킹�C���^�[�o���ݒ�
	 * @param msecs �~���b
	 */
	public void setInterval(long msecs) ;
	/**
	 * �₢���킹�C���^�[�o���o��
	 * @return long �~���b
	 */
	public long getInterval() ;
	/**
	 * �₢���킹�T�[�r�X���ݒ�
	 * @param name
	 */
	public void setGetTaskFlowKey(String name) ;
	/**
	 * �₢���킹�T�[�r�X���o��
	 * @return �₢���킹�T�[�r�X��
	 */
	public String getGetTaskFlowKey() ;
	/**
	 * ���O�T�[�r�X���Z�b�^�[
	 * @param name
	 */
	public void setLogServiceName(ServiceName name) ;
	/**
	 * �V�[�P���X�T�[�r�X���o��
	 * @return�@�V�[�P���X�T�[�r�X��
	 */
	public ServiceName getSequenceServiceName() ;

	/**
	 * ���[�UID�Q�b�^�[
	 * @return ���[�UID
	 */
	public String getUserId() ;

	/**
	 * �V�[�P���X�T�[�r�X���Z���^�[
	 * @param name
	 */
	public void setSequenceServiceName(ServiceName name);

	/**
	 * ���[�UID�Q�b�^�[
	 * @param string
	 */
	public void setUserId(String string) ;
}
