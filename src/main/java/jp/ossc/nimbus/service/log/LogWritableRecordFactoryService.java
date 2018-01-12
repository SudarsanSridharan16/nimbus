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
package jp.ossc.nimbus.service.log;

import jp.ossc.nimbus.service.writer.DateElement;
import jp.ossc.nimbus.service.writer.WritableElement;
import jp.ossc.nimbus.service.writer.WritableRecordFactoryService;

/**
 *	LogWritableRecord�t�@�N�g��
 *  postCreateElement���\�b�h���I�[�o�[���C�h���A
 *  ���t�^��WritableElement�ɑ΂��āA�t�H�[�}�b�g
 *  �������ݒ肷��悤�ɂ���B���t�^�v�f��setFormat(String)
 *  ���\�b�h��@���̂ŁA���t�^�̗v�f�Ƃ��āADateElement
 *  �ȊO�̃N���X���g���ꍇ�ɂ́AsetFormat(String)���\�b�h������
 *  ���邱�ƁB
 * 
 *	@author	y-tokuda
 *	@version	1.00 �쐬�F2003/11/02�| y-tokuda<BR>
 *				�X�V�F
 */
public class LogWritableRecordFactoryService extends WritableRecordFactoryService {
	
    private static final long serialVersionUID = -4184045351990790021L;
    
    //�����o�ϐ�
	/** ���t�t�H�[�}�b�g������ */
	private String mDateFormat;
	/** postCreateElement���\�b�h�̏����Ώۂł��邩�i���t�^���ǂ����j���ʂ���L�[ */
	static final String DATE_KEY = LogServiceMBean.FORMAT_DATE_KEY;
	/**
	 * ���t�t�H�[�}�b�g�̃Z�b�^�[
	 *	
	 */
	public void setDateFormat(String fmt){
		mDateFormat = fmt;
	}
	/**
	 * �t�H�[�}�b�g�̃Z�b�^�[ <BR>
	 * �L�[���[�h��%�ň͂ށB<BR>
	 * �L�[���[�h<BR>
	 * ���t:D <BR>
	 * ���b�Z�[�W�R�[�h:CODE <BR>
	 * �D��x(DEBUG,INFO��):PRIO <BR>
	 * ���b�Z�[�W:MSG <BR>
	 * �A�v���P�[�V�����w��ʔ�:SEQ <BR>
	 * �J�e�S��:CAT <BR>
	 * �� <BR>
	 * "%D%,%CODE%,%PRIO%,%MSG%,%SEQ%,%CAT%"
	 */
	public void setFormat(String fmt){
		super.setFormat(fmt);
	}
	
	public void startService() throws Exception {
		super.startService();
		if(getImplementClass(DATE_KEY) == null){
			setImplementClass(
				DATE_KEY,
				jp.ossc.nimbus.service.writer.DateElement.class.getName()
			);
		}
	}
	
	/**
	 *  createElement�̒���ɃR�[������郁�\�b�h
	 *  WritableRecordFactory�̋�������I�[�o�[���C�h
	 *  ���t�^���������ꂽ�ꍇ�A���t�^��setFormat(String)���\�b�h�����s����B
	 */
	protected void postCreateElement(WritableElement elem){
		if(mDateFormat != null && elem != null && DATE_KEY.equals(elem.getKey())){
			//DateElement�^�ɃL���X�g���āAsetFormat����B
			((DateElement)elem).setFormat(mDateFormat);
		}
	}
}
