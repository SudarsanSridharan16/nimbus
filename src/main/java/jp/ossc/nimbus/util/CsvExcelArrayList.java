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

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
*	CSV�`��Excel�t�@�C���I�u�W�F�N�g
*	@author		s3-ito
*	@version	1.00 �쐬�F2004.01.15 �| s3-ito<BR>
*				�X�V�F
*/
public class CsvExcelArrayList extends CsvArrayList {
    
    private static final long serialVersionUID = -6160765413905533998L;
    
	/** �����p�I�u�W�F�N�g		*/
    private Object mObjSync ;
	
	/**
	 *	�R���X�g���N�^<br>
	 */
	public CsvExcelArrayList() {
		super();
		mObjSync = new Object();
		
	}
	/**
	 *	�R���X�g���N�^<br>
	 *	@param			file	CSV�`��Excel�t�@�C��
	 */
	public CsvExcelArrayList(FileReader file) throws IOException {
		super();
		mObjSync = new Object();
		readExcelFile(file);
	}
	/**
	 *	Excel�t�@�C���ǂݍ��݃��\�b�h<br>
	 *	Excel�t�@�C����ǂݍ���CsvArrayList�N���XObject�����X�g�ɐݒ肷��
	 *	@param			file	CSV�`��Excel�t�@�C��
	 */
	public void readExcelFile(FileReader file) throws IOException {
		synchronized(mObjSync) {
			BufferedReader buffer = new BufferedReader(file);
			String line = null;
			for(;;) {
				// �sObject����
				CsvArrayList cols = new CsvArrayList();
				line = buffer.readLine();
				if(line != null) {
					int index = 0;
					while((index = cols.getData(line, index)) != -1)
						;
				}
				else {
					break;
				}
				// ���X�g�ɍsObject��ǉ�
				this.add(cols);
			}
			buffer.close();
		}
	}
	/**
	 *	������擾<br>
	 *	�w��ʒu�̕�������擾����
	 *	@return		String	�w��ʒu�̕�����
	 *	@param			line	�s
	 *	@param			col		��
	 */
	public String getStr(int line, int col) {
		synchronized(mObjSync) {
			CsvArrayList array = (CsvArrayList)get(line);
			return (String)array.get(col);
		}
	}
	/**
	 *	�����񐔎擾<br>
	 *	�w��s�̕����񐔂��擾����
	 *	@return		int		������
	 *	@param			line	�s
	 */
	public int size(int line) {
		synchronized(mObjSync) {
			CsvArrayList array = (CsvArrayList)get(line);		
			return array.size();
		}
	}
}
