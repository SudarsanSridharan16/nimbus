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

import java.io.ByteArrayInputStream;
import java.io.*;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.Properties;
import jp.ossc.nimbus.io.* ;
//
/**
*	�W���̃v���p�e�B�I�u�W�F�N�g�̕��������̖����������<BR>
*	���{��\�̃v���p�e�B�I�u�W�F�N�g�ł��B<BR>
*	�܂��AString�A�t�@�C�����w���load���\�ł��B<BR>
*	@author		Hirotaka.Nakano
*	@version	1.00 �쐬�F2001.06.21 �| H.Nakano<BR>
*				�X�V�F
*/
public class EncodedProperties extends Properties {
	
    private static final long serialVersionUID = -3138996569732225373L;
    
    //## �����o�[�ϐ��錾 ##
	/** �V���N���i�C�Y�I�u�W�F�N�g	*/
	private Boolean	mSyncObj = new Boolean(true) ;
	/** �G���R�[�h������			*/
	private String	mEncoding  ;
	/** �v���p�e�B�G���R�[�h	*/
	static public final String ENCODE_PORP = "ISO-8859-1" ; //$NON-NLS-1$
	/** �v���p�e�B�G���R�[�h	*/
	static public final String ENCODE_UTF8 = "UTF-8" ; //$NON-NLS-1$
	static public final String EQUALS = "=" ; //$NON-NLS-1$
	//
	//
	/**
	 *	�R���X�g���N�^�B<BR>
	 */
	public EncodedProperties() {
		super();
		mEncoding = ENCODE_PORP ;
	}
	//
	/**
	 *	�R���X�g���N�^�B<BR>
	 *	@param	prop �w�肳�ꂽ�f�t�H���g�l������̃v���p�e�B���X�g���쐬���܂��B
	 */
	public EncodedProperties(Properties prop) {
		super((Properties)prop);
		mEncoding = ENCODE_PORP ;
	}
	//
	/**
	 *	�R���X�g���N�^�B<BR>
	 *	@param encodeName �t�@�C���ǂݍ��݃G���R�[�h
	 */
	public EncodedProperties(String encodeName) {
		super();
		mEncoding = encodeName ;
	}

	//	
	/**
	 *	���̓X�g���[������L�[�Ɨv�f���΂ɂȂ����v���p�e�B���X�g��ǂݍ��݂܂�<BR>
	 *	���̃��[�h�͕���������������鎖���ł��܂��B
	 *	@param	inStream ���̓X�g���[��
	 */
	public void load(InputStream inStream) throws IOException {
		synchronized(mSyncObj){
			try{
				if(ENCODE_PORP.equals(this.getEncoding())){
					super.load(inStream);
				}else{
					this.readStream(inStream) ;
				}
			}finally{
				inStream.close();
			}
		}
	}

	private void makeKey(String rec){
		int index = rec.indexOf(EQUALS) ;
		
		// �R�����g�s
		if(rec.indexOf("#") == 0 ||
			rec.indexOf("!") == 0) {
			return ;
		}
		
		if(index != -1){
			String key = rec.substring(0,index) ;
			key = key.trim();
			String value = rec.substring(index+1) ;
			value = value.trim();
			super.setProperty(key,value) ;
		}
	}

	private void readStream(InputStream stream) throws IOException{
		UnicodeHexBufferedReader in = null;
		in	= new UnicodeHexBufferedReader(new InputStreamReader(stream,this.getEncoding()));

		/** �e�[�u���쐬 */
		String rec = "";
		String buf = "";
		try{
			while((buf = in.readLine()) != null) {
				int len = buf.length();
				if(len > 1){
					if(buf.lastIndexOf("\\") == len - 1){
						buf = buf.trim();
						len = buf.length();
						buf = buf.substring(0, len - 1);
						rec += buf;
						continue;
					}
					else{
						buf = buf.trim();
						rec += buf;
					}
					makeKey(rec) ;
				}
				rec = "";
			}
		}finally{
			in.close() ;
			stream.close() ;
		}
	}

	//
	/**
	 *	�w��t�@�C������L�[�Ɨv�f���΂ɂȂ����v���p�e�B���X�g��ǂݍ��݂܂�<BR>
	 *	���̃��[�h�͕���������������鎖���ł��܂��B
	 *	@param	filePath	�v���p�e�B�t�@�C���t���p�X
	 */
	public void loadFromFile(String filePath) throws IOException {
		synchronized(mSyncObj){
			FileInputStream propStream = new FileInputStream(filePath);
			InputStream in = (InputStream)propStream;
			this.load(in);
			in.close();
			propStream.close() ;
		}
	}
	//
	/**
	 *	���΃v���p�e�B�w��ŗv�f���΂ɂȂ����v���p�e�B���X�g��ǂݍ��݂܂�<BR>
	 *	���̃��[�h�͕���������������鎖���ł��܂��B
	 *	@param	propString ���\�[�X�o���h���̃I�u�W�F�N�g�C���X�^���X
	 */
	public void loadFromString(String propString) throws IOException {
		synchronized(mSyncObj){
			ByteArrayInputStream propStream;
			
			String buf = getEncoding();
			setEncoding(ENCODE_UTF8);
			propString = UnicodeHexBufferedReader.convertUnicode(propString);
			try {
				propStream =new ByteArrayInputStream(propString.getBytes(this.getEncoding()));
				
			} catch (UnsupportedEncodingException e) {
				throw new IOException("Unsupport Encoding = " + mEncoding);
			}

			InputStream in = (InputStream)propStream;
			this.load(in);
			in.close();
			propStream.close() ;
			setEncoding(buf);
		}
	}
	//
	/**
	 *	�G���R�[�f�B���O���o��<BR>
	 *	@return	�G���R�[�f�B���O������
	 */
	public String getEncoding()  {
		return this.mEncoding ;
	}
	//
	//
	/**
	 *	�G���R�[�f�B���O���ݒ�<BR>
	 *	@param	encoding �G���R�[�f�B���O������
	 */
	public void setEncoding(String encoding)  {
		this.mEncoding = encoding ;
	}
}
