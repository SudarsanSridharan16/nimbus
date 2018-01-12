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

package jp.ossc.nimbus.util;

/**
*	VB���߃G�~�����[�V�����I�u�W�F�N�g
*	@author		Hirotaka.Nakano
*	@version	1.00 �쐬�F2001.04.04 �| H.Nakano<BR>
*				�X�V�F
*/
public class RuntimeOperator {
	public static final int C_NOAP = -10000 ;
	public static final String C_SPACE = " " ; //$NON-NLS-1$
	/**
	 *	�I���҂�EXEC���\�b�h<br>
	 *	@return		int					���s����
	 *	@param		cmdLine				�R�}���h���C��������
	 */
	public static int shellWait(String cmdLine){
		//## ���[�J���錾 ##
		int	intRet;
		Process procVM = null;
		Runtime rtEnv = Runtime.getRuntime();
		//== �R�}���h���C�������z�� ==
		CsvArrayList perse = new CsvArrayList() ;
		perse.split(cmdLine,C_SPACE) ;
		String[] commands = perse.toStringAry();
		//## Exe���s ##
		try {
			procVM = rtEnv.exec(commands);
			intRet	= procVM.waitFor();
		} catch(Exception e) {
			intRet = C_NOAP;
		}
		return intRet;
	}
	//
	/**
	 *	�����I��EXEC���\�b�h<br>
	 *	@return		boolean				true ����I�� false �ُ�I��
	 *	@param		cmdLine				�R�}���h���C��������
	 */
	public static boolean shell(String cmdLine){
		//## ���[�J���錾 ##
		Runtime rtEnv = Runtime.getRuntime();
		//== �R�}���h���C�������z�� ==
		CsvArrayList perse = new CsvArrayList() ;
		perse.split(cmdLine,C_SPACE) ;
		String[] commands = perse.toStringAry();
		//## Exe���s ##
		try {
			rtEnv.exec(commands);
		} catch(Exception e) {
			return false;
		}
		return true;
	}
	//
}
