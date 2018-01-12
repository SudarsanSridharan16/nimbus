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
package jp.ossc.nimbus.service.msgresource;

import jp.ossc.nimbus.core.*;
/**
 * @author y-tokuda
 *
 * ���̐������ꂽ�R�����g�̑}�������e���v���[�g��ύX���邽��
 * �E�B���h�E > �ݒ� > Java > �R�[�h���� > �R�[�h�ƃR�����g
 */
public interface MessageResourceFactoryServiceMBean extends ServiceBaseMBean{
	/**
	 * ��`�t�@�C���i�[�f�B���N�g���̃Z�b�^�[
	 * @param dir
	 */
	public void setDefineFileDir(String dir);
	/** 
	 * ��`�t�@�C���i�[�f�B���N�g���̃Q�b�^�[
	 * 
	 * @return
	 */
	public String getDefineFineDir();
	/**
	 * ��`�t�@�C���g���q�̃Z�b�^�[
	 *
	 */
	public void setDefineFileExt(String ext);
	/**
	 * ��`�t�@�C���g���q�̃Q�b�^�[
	 *
	 */
	public String getDefineFileExt();
	/**
	 * �o�C�g�R���o�[�^�[�T�[�r�X���̃Z�b�^�[
	 *	
	 */
	public void setByteConverterServiceName(ServiceName name);
	/**
	 * �o�C�g�R���o�[�^�[�T�[�r�X���̃Q�b�^�[
	 * 
	 */
	public ServiceName getByteConverterServiceName();
}
