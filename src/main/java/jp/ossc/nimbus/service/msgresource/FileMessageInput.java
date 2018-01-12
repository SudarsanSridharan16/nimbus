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

import java.util.*;
import java.io.*;
import jp.ossc.nimbus.util.*;
import jp.ossc.nimbus.lang.*;
/**
 *	File���b�Z�[�W�C���v�b�g�N���X
 *	@author	y-tokuda
 *	@version	1.00 �쐬�F2003/11/05�| y-tokuda<BR>
 *				�X�V�F
 */
public class FileMessageInput implements MessageInput,MessageResourceDefine{
	//�����o�ϐ�
	/** �y�C���[�h������ */
	private String[] mPayloadArray = null;
	/** �v���p�e�B*/
	private Properties[] mPropertiesArray = null;
	/** ���ݍs */
	private int mCurrentline = 0;
	/** ���R�[�h�� */
	private int mRecordNum = 0;
	/** �v���p�e�B���ƃy�C���[�h���̃Z�p���[�^�[ */
	private final String SEP = ":";
	/**
	 * �R���X�g���N�^
	 */
	public FileMessageInput(String filename){
		//�w�肳�ꂽ�t�@�C�����J���A�f�[�^���L���b�V������B
		ArrayList payloadDefs = new ArrayList();
		ArrayList propertyDefs = new ArrayList();
		try{
			LineNumberReader reader =  new LineNumberReader(new FileReader(filename));
			String line = null;
			mRecordNum = 0;
			String payloadStr = "";
			String propStr = "";
			while( (line = reader.readLine()) != null){
				//���s�݂̂̍s�͑��݂��Ȃ����̂Ƃ��Ĉ���
				if(line.equals("")){
					continue;
				}
				//�R�����g�s�͑��݂��Ȃ����̂Ƃ��Ĉ���
				if(line.charAt(0) == '#'){
					continue;
				}
				//�v���p�e�B���ƃy�C���[�h���𕪊�����B
				int pos = line.indexOf(SEP);				
				if(pos == -1){
					//�Z�p���[�^�����̏ꍇ�s���t�H�[�}�b�g�Ƃ���ServiceException�𔭐�
					throw new ServiceException("MESSAGERESOURCESERVICE901","line no."+reader.getLineNumber() + " is invalid. filename is " + filename+ ".");
				}
				else if(pos == 0){
					//�Z�p���[�^�擪�̏ꍇ�A�v���p�e�B�w�薳���Ɖ��߁B
					propStr = "";
					payloadStr=line.substring(1);
					
				}
				//�Z�p���[�^�������Ōゾ������A�y�C���[�h�w�薳���Ɖ���
				else if(pos == (line.length() -1)){
					propStr=line.substring(0,pos);
					payloadStr="";
				}
				else{
					propStr = line.substring(0,pos);
					payloadStr = line.substring(pos+1);
				}
				//�v���p�e�B�𐶐����Ă���
				Properties jmsProp = new Properties();
				CsvArrayList props = new CsvArrayList();
				props.split(propStr);
				if(propStr.equals("")){
					//�������Ȃ��BjmsProp�͋�̂܂�
					;
				}
				else{
					for(int rCnt=0;rCnt<props.size();rCnt++){
						String keyAndVal = (String)props.get(rCnt);
						int separatePos = keyAndVal.indexOf("=");
						if( (separatePos>0 ) && (separatePos < keyAndVal.length() -1)){
							String key = keyAndVal.substring(0,separatePos);
							String val = keyAndVal.substring(separatePos + 1);
							jmsProp.put(key,val);
						}
						else{
							throw new ServiceException("MESSAGERESOURCESERVICE902",
														"line no."+reader.getLineNumber() + 
														" has invalid property definition. " + 
														filename+ ".");
						}
						
					}
				}
				propertyDefs.add(jmsProp);
				payloadDefs.add(payloadStr);
				mRecordNum++;
			}
		}
		catch(Exception e){
			//�f�[�^�t�@�C���̓ǂݍ��݂Ɏ��s������Exception���グ��B
			//e.printStackTrace();
			throw new ServiceException("MESSAGERESOURCESERVICE900","Exception were thrown while reading "+filename+" .",e);
		}
		//�z��ɋl�߂Ȃ���
		mPayloadArray = new String[mRecordNum];
		mPropertiesArray = new Properties[mRecordNum];
		for(int rCnt=0;rCnt<mRecordNum;rCnt++){
			mPayloadArray[rCnt] = (String)payloadDefs.get(rCnt);
			mPropertiesArray[rCnt] = (Properties)propertyDefs.get(rCnt);
		}
	}
	
	/**
	 * 1�s�i�߂�B�ŏI�s�ɒB������A�擪�ɖ߂�B
	 */
	public void nextLine(){
		if( (mRecordNum -1) > mCurrentline){
			mCurrentline++;
		}
		else{
			mCurrentline = 0;
		}
	}
	/**
	 * �y�C���[�h�f�[�^�������Ԃ�
	 */
	public String getInputString(){
		return mPayloadArray[mCurrentline];
	}
	/**
	 * �v���p�e�B�f�[�^�������Ԃ�
	 *	
	 */
	public Properties getMessageHeadProp(){
		return mPropertiesArray[mCurrentline];
	}
}
