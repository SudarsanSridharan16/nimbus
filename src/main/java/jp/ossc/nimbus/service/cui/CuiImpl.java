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

import java.util.*;
import jp.ossc.nimbus.core.ServiceBase;
import jp.ossc.nimbus.core.ServiceBaseMBean;
import jp.ossc.nimbus.lang.*;

/**
 * @author y-tokuda
 * CuiOperator�C���^�[�t�F�C�X�����N���X
 * CuiOperator�C���^�[�t�F�C�X��Cui�C���^�[�t�F�C�X���p�����Ă��āA
 * �A�v�����ɂ́ACui�C���^�[�t�F�C�X�Ƃ��ė��p������B
 * CuiOperator�C���^�[�t�F�C�X�́A�t�@�N�g���[�p�C���^�[�t�F�C�X�B
 */
public class CuiImpl extends ServiceBase implements ServiceBaseMBean,CuiOperator,CuiTagDefine{
	
    private static final long serialVersionUID = 2060576109868585440L;
    
    //�����o�ϐ�
	/** Step����DataInputStep�I�u�W�F�N�g���֘A�t����n�b�V�� */
	private HashMap mStepHash = null;
	/** Step�̏��Ԃ�ێ�����ArrayList */
	private ArrayList mStepArrayList = null;
	/** ���͌��ʂ�ێ����� Hash */
	private HashMap mResultHash = null;
	/**
	 * ���͌��ʃn�b�V���擾
	 */
	public HashMap getResult(){
		return mResultHash;
	}
	 
	/**
	 * �R���X�g���N�^
	 *
	 */
	public CuiImpl(){
		//����������B
		mStepHash = new HashMap();
		mStepArrayList = new ArrayList();
		mResultHash = new HashMap();
	}
	/**
	 * �J�n
	 */
	public void startService(){
		;
	}
	/**
	 * �R�}���h���͎�t���\�b�h
	 *
	 */
	public void invoke(){
		//���͌��ʂ�ێ�����n�b�V���̃N���A
		mResultHash.clear();
		//ArrayList�̍ŏ���DataInputStep���N������B
		DataInputStep theFirst = (DataInputStep)mStepArrayList.get(0);
		String key = null;
		String nextStepKey = null;
		key = theFirst.invoke();
		mResultHash.put(theFirst.getName(),theFirst.getSelectedValue());
		while(true){
			if(key.equals(INTERRUPT)){
				//���f
				mResultHash.clear();
				break;
			}
			if(key.equals(REDO)){
				//�ŏ������蒼��
				mResultHash.clear();
				key = theFirst.invoke();
				mResultHash.put(theFirst.getName(),theFirst.getSelectedValue());
				continue;
			}
			DataInputStep Step = (DataInputStep)mStepHash.get(key);
			if(Step == null){
				//May be Invalid Definition
				throw new ServiceException("CUIFACTORYSERVICE040",key + "is not valid step name.");
			}
			nextStepKey = Step.invoke();
			//�n�b�V���ɑI�����ꂽ�l���i�[
			mResultHash.put(key,Step.getSelectedValue());
			key = nextStepKey;
			if(nextStepKey.equals(END)){
				System.out.println(Step.getEndMessage());
				break;
			}

		}
	}
	/**
	 * �o�b�`�����pInvoke
	 *
	 */
	public void invoke(ArrayList list){
		//���͌��ʂ�ێ�����n�b�V���̃N���A
		mResultHash.clear();
		//�ŏ���DataInputStep�����
		DataInputStep theFirst = (DataInputStep)mStepArrayList.get(0);
		mResultHash.put(theFirst.getName(),list.get(0));
		//�ŏ���DataInputStep�ɁA�^����ꂽ���͒l�����A����DataInputStep��
		//�擾����L�[�𓾂�B
		String key = theFirst.invoke((String)list.get(0));
		for(int rCnt=1;rCnt<mStepArrayList.size();rCnt++){
			DataInputStep step = (DataInputStep)mStepHash.get(key);
			key = step.invoke((String)list.get(rCnt));
			mResultHash.put(step.getName(),step.getSelectedValue());
			if(key.equals(END)){
				break;
			}
		}		
	}
	/**
	 * DataInputStep��ǉ�����B
	 */
	public void addStep(String key,DataInputStep step){
		mStepHash.put(key,step);
		mStepArrayList.add(step);
	}
	
}
