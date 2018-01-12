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
package jp.ossc.nimbus.service.performance;
//�C���|�[�g
import jp.ossc.nimbus.core.*;

//
/**
 *	�X�^�e�B�X�e�B�N�X�Ǘ��N���X�B<BR>
 *	�X�^�e�B�X�e�B�N�X�̌����A�o�^���s���B<BR>
 * @author H.Nakano
 * @version 1.00 
 */
public interface FileReportPerformanceStatisticsServiceMBean extends ServiceBaseMBean{
	/**	�L�[���\�[�g						*/		
	static public final int C_NAME = 0;
	static public final String C_NAME_STR = "NAME";
	/**	�x�X�g�p�t�H�[�}���X�\�[�g			*/
	static public final int	C_BEST = 1;
	static public final String C_BEST_STR = "BEST";
	/**	���[�X�g�p�t�H�[�}���X�\�[�g		*/	
	static public final int C_WORST = 2;
	static public final String C_WORST_STR = "WORST";
	/**	���σp�t�H�[�}���X�\�[�g			*/	
	static public final int C_AVERAGE = 3;
	static public final String C_AVERAGE_STR = "AVERAGE";
	/**	�R�[���񐔃\�[�g					*/	
	static public final int C_COUNT = 4;
	static public final String C_COUNT_STR = "COUNT";
	//
	/**
	 *	�p�t�H�[�}���XHASH���N���A����B
	 */
	public void clear() ;
	//
	/**
	 * �����o�̓��\�b�h<BR>
	 * �w��̃\�[�g�L�[�Ń\�[�g���s���B<BR>
	 * @param sortKey	�\�[�g�L�[
	 * @param isUpset		�����A�~���̎w��
	 * @return String[] �\�[�g����
	 */
	public String[] toStringAry (int sortKey,boolean isUpset);
	/**
	 * Method setRecordClassName.
	 * @param className
	 */
	public void setRecordClassName(String className);
	/**
	 * Method getRecordClassName.
	 * @return String
	 */
	public String getRecordClassName() ;
	/**
	 * �L���[���i�R���|�[�l���g����ݒ肷��.
	 * @param name - �L���[���i�R���|�[�l���g��
	 */
	public void setQueueServiceName(ServiceName name) ;
	/**
	 * �L���[���i�R���|�[�l���g�����擾����B
	 * @return ServiceName - �L���[���i�R���|�[�l���g��
	 */
	public ServiceName getQueueServiceName() ;
	/**
	 * �t�@�C�������o���R���|�[�l���g����ݒ肷��B
	 * @param name - �t�@�C�������o���R���|�[�l���g��
	 */
	public void setWriterServiceName(ServiceName name) ;
	/**
	 * �t�@�C�������o���R���|�[�l���g����ݒ肷��B
	 * @return - �t�@�C�������o���R���|�[�l���g��
	 */
	public ServiceName getWriterServiceName() ;
	/**
	 * �t�@�C�������o�����R�[�h�t�@�N�g���R���|�[�l���g����ݒ肷��B
	 * @param name - �t�@�C�������o�����R�[�h�t�@�N�g���R���|�[�l���g��
	 */
	public void setWriteableRecordFactoryServiceName(ServiceName name) ;
	/**
	 * �t�@�C�������o�����R�[�h�t�@�N�g���R���|�[�l���g�����擾����B
	 * @return ComponentName - �t�@�C�������o�����R�[�h�t�@�N�g���R���|�[�l���g��
	 */
	public ServiceName getWriteableRecordFactoryServiceName() ;
	/**
	 * �t�@�C�������o���C���^�[�o��(�b)��ݒ肷��B
	 * @param intervalSec - �C���^�[�o��(�b)
	 */
	public void setWritableInterval(String intervalSec);
	/**
	 * �t�@�C�������o���C���^�[�o��(�b)���擾����B
	 * @return String - �C���^�[�o��(�b)
	 */
	public String getWritableInterval();
	/**
	 * �\�[�g�L�[��ݒ肷��B("NAME","BEST","WORST","AVERAGE","COUNT")
	 * @param sortKey - �\�[�g�L�[
	 */
	public void setSortKey(String sortKey);
	/**
	 * �\�[�g�L�[���擾����B("NAME","BEST","WORST","AVERAGE","COUNT")
	 * @return �\�[�g�L�[
	 */
	public String getSortKey();
}
