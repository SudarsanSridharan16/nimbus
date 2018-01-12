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
package jp.ossc.nimbus.service.performance;

import java.util.ArrayList;
import jp.ossc.nimbus.core.ServiceBaseMBean;
//
/**
 *	�X�^�e�B�X�e�B�N�X�Ǘ��N���X�B<BR>
 *	�X�^�e�B�X�e�B�N�X�̌����A�o�^���s���B<BR>
 *	@author 	NRI Hirotaka.Nakano
 *				�X�V�F
 */
public interface CachedPerformanceStatisticsServiceMBean extends ServiceBaseMBean{
	/**	�L�[���\�[�g						*/		
	static public final int C_NAME = 0;
	/**	�x�X�g�p�t�H�[�}���X�\�[�g			*/
	static public final int	C_BEST = 1;
	/**	���[�X�g�p�t�H�[�}���X�\�[�g		*/	
	static public final int C_WORST = 2;
	/**	���σp�t�H�[�}���X�\�[�g			*/	
	static public final int C_AVERAGE = 3;
	/**	�R�[���񐔃\�[�g					*/	
	static public final int C_COUNT = 4;
	//
	/**
	 *	�p�t�H�[�}���XHASH���N���A����B
	 */
	public void clear() ;
	//
	/**
	 *	�����o�̓��\�b�h<BR>
	 *	�w��̃\�[�g�L�[�Ń\�[�g���s���B<BR>
	 * @param sortKey	�\�[�g�L�[
	 * @param isUpset		�����A�~���̎w��
	 * @return String[] �\�[�g����
	 */
	public String[] toStringAry (int sortKey,boolean isUpset);
	/**
	 *	List�f�[�^�擾<BR>
	 *	�w��̃\�[�g�L�[�Ń\�[�g���s���B<BR>
	 * @param sortKey	�\�[�g�L�[
	 * @param isUpset	�����A�~���̎w��
	 * @return ArrayList	�\�[�g���v���(PerformanceRecord�̔z��)
	 */
	public ArrayList toAry (int sortKey,boolean isUpset);
	/**
	 * Method setRecordClassName.
	 * @param className
	 */
	//
	public void setRecordClassName(String className) ;
	/**
	 * Method getRecordClassName.
	 * @return String
	 */
	public String getRecordClassName() ;
}
