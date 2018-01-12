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
 * this list of conditions and the following disclaimer.
 * 2. Redistributions in binary form must reproduce the above copyright notice,
 * this list of conditions and the following disclaimer in the documentation
 * and/or other materials provided with the distribution.
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
package jp.ossc.nimbus.recset;
// �C���|�[�g
import java.sql.Types;
/**
 * ��X�L�[�}�N���X<p>
 * ��X�L�[�}�����Ǘ�����B
 * @version $Name:  $
 * @author H.Nakano
 * @since 1.0
 */
public class FieldSchema implements java.io.Serializable{
	
    private static final long serialVersionUID = 750474574072043812L;
    
    /** �f�[�^�^�w�蕶���萔�@VARCHAR�@*/
	public static final String C_FIELD_TYPE_VARCHAR = "VARCHAR" ;
	/** �f�[�^�^�w�蕶���萔�@CHAR�@*/
	public static final String C_FIELD_TYPE_CHAR = "CHAR" ;
	/** �f�[�^�^�w�蕶���萔�@NUMBER�@*/
	public static final String C_FIELD_TYPE_LONG = "LONG" ;
	/** �f�[�^�^�w�蕶���萔�@NUMBER�@*/
	public static final String C_FIELD_TYPE_INT = "INT" ;
	/** �f�[�^�^�w�蕶���萔�@NUMBER�@*/
	public static final String C_FIELD_TYPE_FLOAT = "FLOAT" ;
	/** �f�[�^�^�w�蕶���萔�@NUMBER�@*/
	public static final String C_FIELD_TYPE_DOUBLE = "DOUBLE" ;
	/** �f�[�^�^�w�蕶���萔�@DATE�@*/
	public static final String C_FIELD_TYPE_DATE = "DATE" ;
	/** �f�[�^�^�w�蕶���萔�@TIMESTAMP�@*/
	public static final String C_FIELD_TYPE_TIMESTAMP = "TIMESTAMP" ;
	/** �f�[�^�^�w�蕶���萔�@BLOB�@*/
	public static final String C_FIELD_TYPE_BLOB = "BLOB" ;
	/** �f�[�^�^�w�蕶���萔�@CLOB�@*/
	public static final String C_FIELD_TYPE_CLOB = "CLOB" ;
	
    /** �t�B�[���hKEY�萔 */
	static public final int C_KEY_UNIQUE = 0;			// ���j�[�N��L�[
	static public final int C_KEY_ROW_VERSION = 1;		// Row�o�[�W������L�[
	static public final int C_KEY_READ = 2; 			// �Q�Ɨ�L�[
	static public final int C_KEY_UPDATE = 3;			// �X�V��L�[
	static public final int C_KEY_DUMMY = 4;			// �_�~�[��L�[
	/** ��^�萔 */
	static public final int C_TYPE_NONE = -1;
	static public final int C_TYPE_INT = 1;
	static public final int C_TYPE_LONG = 8;
	static public final int C_TYPE_STRING = 2;
	static public final int C_TYPE_CHAR = 3;
	static public final int C_TYPE_DATE = 4;
	static public final int C_TYPE_FLOAT = 5;
	static public final int C_TYPE_DOUBLE = 7;
	static public final int C_TYPE_TIMESTAMP = 9;
	static public final int C_TYPE_BLOB = 10;
	static public final int C_TYPE_CLOB = 11;
	/** ̨���ޖ� */
	private String mFieldName = null;
	/** SQL������ */
	private String mPysicalName = null ;
	/** �ϐ��^ */
	private int mFieldType = C_TYPE_NONE;
	/** �ϐ��� */
	private int mFieldLength =-1;
	/** �񏇈�INDEX */
	private int mIndex =-1;
	/** �񏇈�INDEX */
	private boolean mIsCrypt = false;
	/** �񏇈�INDEX */
	private int mSqlType = -1;
	/** �t�B�[���hKEY */
	private int mFieldKey = -1;
	/**
	 * �񒷂��o�͂���B
	 * @return ��
	 */
	public int getFieldLength(){
		return mFieldLength;
	}
	/**
	 * �񖼂��o�͂���B
	 * @return ��
	 */
	public String getFieldName(){
		return mFieldName;
	}
	/**
	 * �񖼂��o�͂���B
	 * @return ��
	 */
	public String getPysicalName(){
		return mPysicalName;
	}
	/**
	 * ��^���o�͂���B
	 * @return�@��^
	 */
	public int getFieldType(){
		return mFieldType;
	}
	/**
	 * ��̃L�[�������o�͂���B
	 * @return ��̃L�[����
	 */
	public int getFieldKey(){
		return mFieldKey;
	}
	/**
	 * �s��INDEX���o�͂���B
	 * @return �s��INDEX
	 */
	public int getIndex(){
		return mIndex;
	}
	/**
	 * �Í����L�����o�͂���B
	 * @return �Í����L��
	 */
	public boolean isCrypt(){
		return mIsCrypt && (mFieldType == C_TYPE_STRING || mFieldType == C_TYPE_CHAR);
	}
	/**
	 * ���j�[�N�񂩏o�͂���B
	 * @return�@���j�[�N��Ώ�boolean
	 */
	public boolean isUniqueKey(){
		return (mFieldKey == C_KEY_UNIQUE);
	}
	/**
	 * �X�V�Ώۏ]���񂩏o�͂���B
	 * @return�@���j�[�N��Ώ�boolean
	 */
	public boolean isUpdateField(){
		return ((mFieldKey == C_KEY_UNIQUE) ||
				(mFieldKey == C_KEY_ROW_VERSION) ||
				(mFieldKey == C_KEY_UPDATE));
	}
	/**
	 * Row�o�[�W�����񂩏o�͂���B
	 * @return�@���j�[�N��Ώ�boolean
	 */
	public boolean isRowVersionField(){
		return (mFieldKey == C_KEY_ROW_VERSION);
	}
	/**
	 * Field����ݒ肷��B
	 * @param l�@Field��
	 */
	public void setFieldLength(int l){
		if(mFieldLength == -1){
			mFieldLength = l;
		}
	}
	/**
	 * �񖼂�ݒ肷��B
	 * @param name�@��
	 */
	public void setFieldName(String name){
		if(mFieldName == null){
		    final int index = name.lastIndexOf(' ');
		    if(index == -1 || index == name.length() - 1){
		        mFieldName = name;
		    }else{
		        mPysicalName = name.substring(0, index);
			    mFieldName = name.substring(index + 1);
		    }
		}
	}
	/**
	 * ��^�C�v��ݒ肷��B
	 * @param type
	 */
	public void setFieldType(int type){
		if(mFieldType != C_TYPE_NONE){
			return;
		}
		switch(type){
			case C_TYPE_INT:
				mSqlType = Types.INTEGER;
				break;
			case C_TYPE_LONG:
				mSqlType = Types.INTEGER;
				break;
			case C_TYPE_STRING:
				mSqlType = Types.VARCHAR;
				break;
			case C_TYPE_CHAR:
				mSqlType = Types.CHAR;
				break;
			case C_TYPE_DATE:
				mSqlType = Types.DATE;
				break;
			case C_TYPE_TIMESTAMP:
				mSqlType = Types.TIMESTAMP;
				break;
			case C_TYPE_FLOAT:
				mSqlType = Types.FLOAT;
				break;
			case C_TYPE_DOUBLE:
				mSqlType = Types.DOUBLE;
				break;
			case C_TYPE_BLOB:
				mSqlType = Types.BLOB;
				break;
			case C_TYPE_CLOB:
				mSqlType = Types.CLOB;
				break;
			default:
				mFieldType = C_TYPE_NONE;
				return;
		}
		mFieldType = type;
	}
	/**
	 * ��L�[��ݒ肷��B
	 * @param key
	 */
	public void setFieldKey(int key){
		if(mFieldKey ==-1){
			switch(key){
				case C_KEY_UNIQUE:
				case C_KEY_ROW_VERSION:
				case C_KEY_READ:
				case C_KEY_UPDATE:
				case C_KEY_DUMMY:
					mFieldKey = key;
					break;
				default:
			}
		}
	}
	/**
	 * ��ԍ���ݒ肷��B
	 * @param l
	 */
	public void setIndex(int l){
		if(mIndex ==-1){
			mIndex = l;
		}
	}
	/**
	 * �Í����L����ݒ肷��B
	 * @param val �Í����L��
	 */
	public void setCrypt(boolean val){
		mIsCrypt = val;
	}
	/**
	 *	setSqlType
	 *	@param	arg
	 */
	public void setSqlType(int arg){
		mSqlType = arg;
	}
	/**
	 *	getSqlType
	 *	@return	int
	 */
	public int getSqlType(){
		return mSqlType;
	}
    
	public static int getFieldTypeValue(String type){
		if(C_FIELD_TYPE_VARCHAR.equals(type)){
			return C_TYPE_STRING; 
		}else if(C_FIELD_TYPE_CHAR.equals(type)){
			return C_TYPE_CHAR;
		}else if(C_FIELD_TYPE_LONG.equals(type)){
			return C_TYPE_LONG;
		}else if(C_FIELD_TYPE_INT.equals(type)){
			return C_TYPE_INT;
		}else if(C_FIELD_TYPE_DOUBLE.equals(type)){
			return C_TYPE_DOUBLE;
		}else if(C_FIELD_TYPE_FLOAT.equals(type)){
			return C_TYPE_FLOAT;
		}else if(C_FIELD_TYPE_DATE.equals(type)){
			return C_TYPE_DATE;
		}else if(C_FIELD_TYPE_TIMESTAMP.equals(type)){
			return C_TYPE_TIMESTAMP;
		}else if(C_FIELD_TYPE_BLOB.equals(type)){
			return C_TYPE_BLOB;
		}else if(C_FIELD_TYPE_CLOB.equals(type)){
			return C_TYPE_CLOB;
		}else{
			throw new InvalidSchemaException("Unknown type : " + type);
		}
	}
	
	public static String getFieldTypeString(int type){
		switch(type){
		case C_TYPE_STRING:
			return C_FIELD_TYPE_VARCHAR; 
		case C_TYPE_CHAR:
			return C_FIELD_TYPE_CHAR;
		case C_TYPE_LONG:
			return C_FIELD_TYPE_LONG;
		case C_TYPE_INT:
			return C_FIELD_TYPE_INT;
		case C_TYPE_DOUBLE:
			return C_FIELD_TYPE_DOUBLE;
		case C_TYPE_FLOAT:
			return C_FIELD_TYPE_FLOAT;
		case C_TYPE_DATE:
			return C_FIELD_TYPE_DATE;
		case C_TYPE_TIMESTAMP:
			return C_FIELD_TYPE_TIMESTAMP;
		case C_TYPE_BLOB:
			return C_FIELD_TYPE_BLOB;
		case C_TYPE_CLOB:
			return C_FIELD_TYPE_CLOB;
		default:
			throw new InvalidSchemaException("Unknown type : " + type);
		}
	}
}
