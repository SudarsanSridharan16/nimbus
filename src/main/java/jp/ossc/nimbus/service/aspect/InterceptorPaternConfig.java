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
package jp.ossc.nimbus.service.aspect;
//�C���|�[�g
import jp.ossc.nimbus.core.*;
import java.util.regex.*;

/**
 * �C���^�[�Z�v�^�[���̃L�[�p�^�[�����Ǘ�����N���X<p>
 * @version $Name:  $
 * @author H.Nakano
 * @since 1.0
 */
public class InterceptorPaternConfig {
	// �C���^�[�Z�v�^��
	protected ServiceName mInterceptorServiceName = null;
	// �p�^�[���z��
	protected Pattern[] mPatterns = null;
	/**
	 * �R���X�g���N�^<br>
	 */
	public InterceptorPaternConfig(){
		super();
	}
	/**
	 * �C���^�[�Z�v�^�����i�[����<br>
	 * @param interceptorServiceName		�C���^�[�Z�v�^��
	 */
	public void setInterceptorServiceName(ServiceName interceptorServiceName){
		mInterceptorServiceName = interceptorServiceName;
	}
	/**
	 * �C���^�[�Z�v�^����ԋp����<br>
	 * @return interceptorServiceName		�C���^�[�Z�v�^��
	 */
	public ServiceName getInterceptorServiceName(){
		return mInterceptorServiceName;
	}
	/**
	 * �p�^�[��������z����R���p�C�����p�^�[���z����쐬����br>
	 * @param String[]		�p�^�[��������z��
	 */
	public void setPatterns(String[] patternStrings){
		mPatterns = null;
		if(patternStrings == null){
			return;
		}
		mPatterns = new Pattern[patternStrings.length];
		for(int icnt = 0; icnt < patternStrings.length; icnt++){
			mPatterns[icnt] = Pattern.compile(patternStrings[icnt]);
		}
	}
	/**
	 * �p�^�[���z����i�[����<br>
	 * @param Pattern[]		�p�^�[���z��
	 */
	public void setPatterns(Pattern[] patterns){
		mPatterns = patterns;
	}
	/**
	 * �p�^�[���z���ԋp����<br>
	 * @return Pattern[]		�p�^�[���z��
	 */
	public Pattern[] getPatterns(){
		return mPatterns;
	}
	/**
	 * �����Ŏ󂯎����������ƃp�^�[���z��̃p�^�[���}�b�`���O���s���A<br>
	 * ��ł��}�b�`�����ꍇtrue��ԋp<br>
	 * @param String			������(�G�C���A�X)
	 * @return boolean			true:�}�b�`����/false:�}�b�`���Ȃ�����
	 */
	public boolean isMatch(String inString){
		// �p�^�[���z��null�̏ꍇ
		if(mPatterns == null){
			// �s��v�ŕԋp
			return false;
		}
		// �p�^�[���z��Ń��[�v
		for(int icnt = 0; icnt < mPatterns.length; icnt++){
			// �p�^�[�����ƂɃ}�b�`�����쐬
			final Matcher matcher = mPatterns[icnt].matcher(inString);
			// �p�^�[���}�b�`���O�Ń}�b�`�����ꍇ
			if(matcher.matches()){
				// ��v�ŕԋp
				return true;
			}
		}
		// �p�^�[���}�b�`���O�Ń}�b�`���Ȃ������וs��v�ŕԋp
		return false;
	}

}
