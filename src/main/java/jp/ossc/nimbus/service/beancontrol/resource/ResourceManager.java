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
package jp.ossc.nimbus.service.beancontrol.resource;
//�C���|�[�g
import jp.ossc.nimbus.core.*;
/**
 * BL�t���[���ň������\�[�X���Ǘ�����B<p>
 * addResource<br>
 * getResource<br>
 * commitResource,rollBackResource<br>
 * commitAllResources,rollbbackAllResources<br>
 * �̏��ԂŎg�p����B 
 * @version $Name:  $
 * @author H.Nakano
 * @since 1.0
 */
public interface ResourceManager {
	/**
	 * ResourceManager���Ǘ����郊�\�[�X���J������B
	 */
	public void terminateResourceManager() ;
	/**
	 * ���\�[�X���m�ۂ���
	 * @param key			���\�[�X���̃L�[
	 * @param resourceKey	���\�[�X����L�[
	 * @param serviceName	���\�[�X�񋟃T�[�r�X��
	 * @param isTrnControl	�g�����U�N�V�����R���g���[�����邩
	 * @param isTrnClose	�J�����ɃN���[�Y���邩
	 */
	public void addResource(String key,
							String resourceKey,
							ServiceName serviceName,
							boolean isTrnControl,
							boolean isTrnClose) ;
	/**
	 * ���\�[�X���R�~�b�g����
	 * @param key		���\�[�X���̃L�[
	 * @param isClose	���\�[�X�R�~�b�g��N���[�Y���邩
	 */
	public void commitResource(String key,boolean isClose) ;		
	/**
	 * ���\�[�X�����[���o�b�N����
	 * @param key		���\�[�X���̃L�[
	 * @param isClose	���\�[�X�R�~�b�g��N���[�Y���邩
	 */
	public void rollBackResource(String key,boolean isClose) ;		
	/**
	 * ���ׂẴ��\�[�X���R�~�b�g����
	 */
	public void commitAllResources() ;
	/**
	 * ���ׂẴ��\�[�X�����[���o�b�N����
	 */
	public void rollbbackAllResources() ;
	/**
	 * ���\�[�X���o�͂���
	 * @param key	���\�[�X����
	 * @return	���\�[�X�I�u�W�F�N�g
	 */
	public Object getResource(String key) ;
}
