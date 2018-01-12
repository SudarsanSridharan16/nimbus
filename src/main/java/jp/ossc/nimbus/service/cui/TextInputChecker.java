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
package jp.ossc.nimbus.service.cui;

import jp.ossc.nimbus.util.*;

/**
 *	InputChecker�C���^�[�t�F�C�X�����N���X
 *  XML�t�@�C���ɑ��l�œ��͗L���l��������Ă���ꍇ
 * �iinput�^�O��type�������A"text"�̂Ƃ����������B
 *	@author	y-tokuda
 *	@version	1.00 �쐬�F2003/10/31�| y-tokuda<BR>
 *				�X�V�F
 */
public class TextInputChecker implements InputChecker {
	//�����o�ϐ�
	/** ���͉\�l */
	private CsvArrayList mValidValues = null;
	/** ���͉\�͈͍ő� */
	private int mValidValueMax;
	/** ���͉\�͈͍ŏ� */
	private int mValidValueMin;
	/** �͈͎w�胂�[�h */
	private boolean mMaxMinDefMode = false;
	/** �͈͎w����s���ہA�ő�l�ƍŏ��l�̊Ԃɓ���镶���� */
	private String mFromToStr = "-";
	/**
	 * �R���X�g���N�^
	 */
	public TextInputChecker(){
		mValidValues = new CsvArrayList();
	}
	/**
	 * ���͒l�`�F�b�N���\�b�h
	 */
	public String check(String input) {
		if(mMaxMinDefMode){
			//�ő�l�E�ŏ��l���w�肳��Ă��郂�[�h
			int tmp;
			try{
				tmp = Integer.parseInt(input);
			}
			catch(NumberFormatException e){
				return null;
			}
			if( (mValidValueMin <= tmp) && ( tmp <= mValidValueMax) ){
				return input;
			}
			return null;
		}
		else{
			//�L���Ȓl���X�ɗ^�����Ă��郂�[�h
			if (mValidValues.contains(input)){
				//for debug
				return input;
			}
			return null;
		}
	}
	/**
	 * �L���ȓ��͒l�̃Z�b�^�[
	 * @param inputdef �iXML��`�t�@�C������<input>�^�O�̒��g�j
	 */
	public void setValidInput(String inputdef) throws NumberFormatException{
		mMaxMinDefMode = isMaxMinTypeDefinition(inputdef);
		if(!mMaxMinDefMode){
			//�J���}�ŋ�؂��ē��͉\�l�̃��X�g�Ɋi�[
			mValidValues.split(inputdef);
		}
		
	}
	/**
	 * �L���ȓ��͒l���`���镶���񂪁A�ő�l�A�ŏ��l���w�肵�Ă���
	 * �^�C�v���ǂ����A���肷��B�ő�l�A�ŏ��l���w�肵�Ă���^�C�v��
	 * ����΁A�����o�ϐ��ɍő�l�A�ŏ��l���i�[����B
	 * 
	 */
	protected boolean isMaxMinTypeDefinition(String def) throws NumberFormatException{
		//TODO��������
		int separatePos = -1;
		if( (separatePos = def.indexOf(mFromToStr)) < 0 ){
			//�ő�l�A�ŏ��l����؂镶����������Ȃ����false��Ԃ�
			return false;
		}
		String minStr = def.substring(0,separatePos);
		String maxStr =def.substring(separatePos+mFromToStr.length());
		mValidValueMin = Integer.parseInt(minStr);
		mValidValueMax = Integer.parseInt(maxStr);	
		return true;
	}
	/**
	 * �ő�l�E�ŏ��l�̊Ԃɓ��镶����̃Z�b�^�[
	 * @param fromto
	 */
	public void setFromToString(String fromto){
		mFromToStr = fromto;
	}
	

}
