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

import jp.ossc.nimbus.lang.*;
import java.util.*;
import java.io.*;
/**
 *  DataInputStep�N���X
 *	1�̃R�}���h����Step�ɑΉ�����B
 *	@author	y-tokuda
 *	@version	1.00 �쐬�F2003/10/29�| y-tokuda<BR>
 *				�X�V�F
 */
public class DataInputStep 
	implements CuiTagDefine{
	//�����o�ϐ�
	/** ���� */
	private String mMyName;
	/** ���j���[�\������у`�F�b�N�C���^�[�t�F�C�X */
	private ArrayList mDisplayList = null;
	/** �f�[�^�`�F�b�N�I�u�W�F�N�g */
	private InputChecker mChecker = null;
	/** �J�ڐ�n�b�V�� */
	private HashMap mWhereToGoHash = null;
	/** ���̃X�e�b�v�őI�����ꂽ�l */
	private String mSelectedValue = null;
	/** ���̃X�e�b�v */
	private String mNextStepName = null;
	/** �I�����\�����b�Z�[�W */
	private String mEndMessage = "";
	/**
	 * 
	 * �R���X�g���N�^
	 */
	public DataInputStep(String name){
		mMyName = name;
		mDisplayList = new ArrayList() ;
		mWhereToGoHash = new HashMap() ;
		mEndMessage = "";
	}
	/**
	 * ���s
	 * ���Ɏ��s����Step�̖��̂�Ԃ�
	 */
	public String invoke() {
		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
		while(true){
			//�I��l��������
			mSelectedValue = null;
			//���j���[�\��
			for(ListIterator iterator = this.mDisplayList.listIterator();iterator.hasNext();){
				DisplayConstructer ds = (DisplayConstructer)iterator.next() ;
				System.out.println(ds.display());
			}
			//���͎�t
			String answer = null;
			try{
				answer = input.readLine();
			}
			catch(IOException e){
				//readLine����Exception��������������������Ǔ��͑҂��ɂ���B
				continue;
			}
			//���f�������͍ŏ������蒼�����w��������͂�������A���̂܂�return
			if (answer.equals(REDO) || answer.equals(INTERRUPT)){
				return answer;
			}
			//���͒l�`�F�b�N	
			mSelectedValue = mChecker.check(answer);
			if (mSelectedValue == null){
				//�����ȓ��́B������x
				continue;
			}
			String distination = (String)mWhereToGoHash.get(mSelectedValue);
			if(distination != null){
				//�I��l�ɉ�����Step�ɑJ��
				return distination;
			}else{
				//���̃X�e�b�v�ɑJ��
				return mNextStepName;
			}
		}
	}
	/**
	 * �o�b�`�pinvoke���\�b�h
	 * 
	 */
	public String invoke(String in){
		while(true){
			//�L���ȓ��͒l���`�F�b�N
			mSelectedValue = mChecker.check(in);
			if (mSelectedValue == null){
				//�o�b�`�Ŗ����ȓ��͒l�ł���΁A�����^�C���G���[
				throw new ServiceException();
			}
			String distination = (String)mWhereToGoHash.get(mSelectedValue);
			if(distination != null){
				//�I��l�ɉ�����Step�ɑJ��
				return distination;
			}else{
				//���̃X�e�b�v�ɑJ��
				return mNextStepName;
			}
		}
	}
	
	/**
	 * ���̃X�e�b�v�őI�����ꂽ�l��Ԃ�
	 *				
	 */
	public String getSelectedValue(){
		return mSelectedValue;
	}
	/**
	 * Display�I�u�W�F�N�g��ǉ�����B
	 * @param display
	 */
	public void addDisplay(DisplayConstructer display){
		mDisplayList.add(display);
	}
	/**
	 * ��Step��ݒ肷��B
	 * ���͒l��key�ɁAmWhereToGo�n�b�V������l���擾�ł��Ȃ�����
	 * �Ƃ��A���̕ϐ��Ɏw�肳�ꂽStep�ɑJ�ڂ��邱�ƂɂȂ�B
	 */
	public void setNextStepName(String name){
		mNextStepName = name;
	}
	/**
	 * ��Step����Ԃ��B
	 * @return
	 */
	public String getNextStepName(){
		return mNextStepName;
	}
	/**
	 * �J�ڐ�n�b�V����ݒ肷��B
	 * @param condition
	 * @param gotoStepName
	 */
	public void addWhereToGo(String condition,String gotoStepName){
		mWhereToGoHash.put(condition,gotoStepName);
	}
	/**
	 * �I�������b�Z�[�W��ݒ肷��B
	 *	
	 */
	public void setEndMessage(String msg){
		mEndMessage = msg;
	}
	/**
	 * �I�������b�Z�[�W���擾����B
	 * @return
	 */
	public String getEndMessage(){
		return mEndMessage;
	}
	/**
	 * �����̖��O��Ԃ�
	 * @return
	 */
	public String getName(){
		return mMyName;
	}
	/**
	 * ���̓`�F�b�N�I�u�W�F�N�g�̃Q�b�^�[
	 */
	public InputChecker getChecker() {
		return mChecker;
	}

	/**
	 * ���̓`�F�b�N�I�u�W�F�N�g�̃Z�b�^�[
	 */
	public void setChecker(InputChecker checker) {
		mChecker = checker;
	}

}
