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

/**
 *	Cui�萔��`�N���X
 *	@author	y-tokuda
 *	@version	1.00 �쐬�F2003/10/31�| y-tokuda<BR>
 *				�X�V�F
 */
public interface CuiTagDefine {
	/** �ŏ������蒼����\���\��� �iStep���ɂ͎g���Ȃ��j*/
	public static final String REDO ="Redo";
	/** ���f��\���\��� (Step���ɂ͎g���Ȃ��j*/
	public static final String INTERRUPT = "Interrupt";
	/** �I����\���\��� �iStep���ɂ͎g���Ȃ��j */
	public static final String END = "End";
	/** dataInput�^�O�@�q�v�f��step������*/
	public static final String DATAINPUT_TAG = "dataInput";
	/** step�^�O�@�q�v�f�ɁAdisplay,input,goto,end������ */
	public static final String STEP_TAG ="step";
	/** display�^�O�@*/
	public static final String DISPLAY_TAG = "display";
	/** input�^�O�@���̓`�F�b�N���`����B*/
	public static final String INPUT_TAG ="input";
	/** input�^�O��type�����B�l�Ƃ��āA"text","service"������ */
	public static final String INPUT_TAG_TYPE_ATT = "type";
	/** input�^�O�����ŁA�T�[�r�X���w�肷��Ƃ��̕����� */
	public static final String INPUT_TYPE_SERVICE = "service";
	/** �I���^�O */
	public static final String END_TAG = "end";
	/** goto�^�O */
	public static final String GOTO_TAG = "goto";
	/** dataInput�^�O�́A�L�[���� */
	public static final String DATAINPUT_TAG_KEY_ATT = "key";
	/** display�^�O�̃^�C�v�����B "service"�܂���"text"���w�肷�� */
	public static final String DISPLAY_TAG_TYPE_ATT = "type";
	/** step�^�O��name���� */
	public static final String STEP_TAG_NAME_ATT = "name";
	/** goto�^�O��value���� */
	public static final String GOTO_TAG_VALUE_ATT = "value";
	/** display�^�O�� �^�C�v�����ɁA�T�[�r�X���w�肷��ꍇ */
	public static final String DISPLAY_TYPE_SERVICE = "service";
	/** ����step���ɁAinput�������w�肳�ꂽ�ꍇ�̃G���[���b�Z�[�W */
	public static final String INPUT_MULTIDEF_ERR = "input multi defined (in a step ) err";
	/** ��`����Ă��Ȃ�Step�ɑJ�ڂ��悤�Ƃ����ꍇ�̃G���[���b�Z�[�W */
	public static final String NOT_DEF_GOTODIST = "not defined goto distination.";
	/** ����step���ɏI���^�O���������݂����ꍇ�̃G���[���b�Z�[�W */
	public static final String END_MULTI_DEF_ERR = "end multi defined (in a step) err";
	/** �I���^�O�̃^�C�v���� */
	public static final String END_TAG_TYPE_ATT = "type";
	/** �I���^�O�̃^�C�v�����Ɏw�肷��l�i�����I���j*/
	public static final String END_FORCE = "force";
}
