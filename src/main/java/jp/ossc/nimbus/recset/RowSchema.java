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
package jp.ossc.nimbus.recset;
//�C���|�[�g
import java.util.*;
import jp.ossc.nimbus.util.*;
/**
 * �s�X�L�[�}�Ǘ��N���X<p>
 * �t�B�[���h�X�L�[�}�̏W�����Ǘ�����B
 * @version $Name:  $
 * @author H.Nakano
 * @since 1.0
 */
public class RowSchema implements java.io.Serializable{
	
    private static final long serialVersionUID = 2519505358076142786L;
    
	/** �񖼊Ǘ��n�b�V�� */
	private HashMap mFieldHash = new HashMap();
	/** �񏇊Ǘ����X�g */
	private ArrayList mFieldAry = new ArrayList() ;
	/** UniqueKey��Ǘ����X�g */
	private ArrayList mUniqueKeyAry = new ArrayList() ;
	/** �n�b�V���R�[�h */
	private int mHashCode = 0 ;
	
	/**
	 * �R���X�g���N�^
	 */
	public RowSchema(){
	}
	/**
	 * UniqueKey�̐����o�͂���
	 * @return UniqueKey�̐�
	 */
	public int getUniqueKeySize(){
		return this.mUniqueKeyAry.size();
	}
	/**
	 * UniqueKey�̗�����o�͂���B
	 * @param index
	 * @return�@FieldSchema
	 */
	public FieldSchema getUniqueFieldSchema(int index){
		return (FieldSchema)mUniqueKeyAry.get(index) ;
	}
	/**
	 * �X�V�pRowSchema���쐬���������܂��B
	 * @return �X�V�pRowSchema
	 */
	public RowSchema makeGoneSchema(){
		RowSchema ret = new RowSchema() ;
		for(int rcnt= 0 ;rcnt<this.mFieldAry.size();rcnt++){
			FieldSchema fs = this.get(rcnt) ;
			FieldSchema newFs = new FieldSchema();
			newFs.setFieldName(fs.getFieldName());
			newFs.setFieldType(fs.getFieldType());
			newFs.setFieldLength(fs.getFieldLength());
			newFs.setFieldKey(fs.getFieldKey());
			newFs.setCrypt(fs.isCrypt());
			if(fs.isUpdateField()){
				ret.add(newFs);
			}
		}
		return ret ;
	}
	
	/**
	 * �n�b�V���R�[�h���������܂��B
	 * @return �n�b�V���R�[�h
	 */
	public Integer getHashCodeObject() {
		return new Integer(this.mHashCode) ;	
	}

	/**
	 * �n�b�V���R�[�h���������܂��B
	 * @return �n�b�V���R�[�h
	 * @see java.lang.Object#hashCode()
	 */
	public int	hashCode() {
		return this.mHashCode ;
	}
	
	/**
	 * RowSchema�����l���ǂ������������܂��B
	 * RowSchema�̃n�b�V���R�[�h���r�������ʂ��������܂��B
	 * @param another ��r����I�u�W�F�N�g
	 * @return true ���l<br>
	 * 			false �񓹒l
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	public boolean equals(Object another){
		if(another == null){
			return false ;
		}else{
			if(this == another){
				return true ;
			}
			if(another instanceof  RowSchema){
				if(this.mHashCode == another.hashCode()){
					return true ;
				}
			}
		}
		return false ;
	}
	
	/**
	 * �X�L�[�}���𐶐�����B
	 * �X�L�[�}�̋L�q���@�͈ȉ��̒ʂ�B
	 * fieldName,[VARCHAR,CAHR,NUMBER,DATE],FIELDLENGTH,[0:KEY,1:ROWVERSION,2:������C3�X�V��]
	 * ��
	 * REC_ID,CHAR,5,1
	 * SALARY,INT,0,0
	 * INSDATE,DATE,0,0
	 * ROW_VERSIONINT,0,2
	 * @param schema
	 */
	public void initSchema(String schema){
		if(mFieldAry.size() == 0){
			CsvArrayList parser = new CsvArrayList() ;
			parser.splitCL(schema) ;
			for(ListIterator ite = parser.listIterator();ite.hasNext();){
				String fieldStr = (String)ite.next() ; 
				FieldSchema field = makeFieldSchema(fieldStr) ;
				if(field !=null){
					add(field) ;
				}
			}
			this.mHashCode = schema.hashCode() ;
		}
		mFieldAry.trimToSize();
		mUniqueKeyAry.trimToSize();
	}
	
	/**
	 * ������w�肩���X�L�[�}�𐶐�����B
	 * @param fieldInf�@��w�蕶����
	 * @return�@FieldSchema
	 */
	private FieldSchema makeFieldSchema(String fieldInf){
		if(fieldInf == null || fieldInf.length() == 0){
			return null ;
		}
		CsvArrayList colInf = new CsvArrayList() ;
		final int sz = colInf.split(fieldInf);
		if(sz < 4){
			throw new InvalidSchemaException(fieldInf + "invalid") ; 
		}
		FieldSchema field = new FieldSchema() ;
		// �񖼐ݒ�
		field.setFieldName(colInf.getStr(0)) ;
		// �f�[�^�^�ݒ�
		String tp = colInf.getStr(1);
		field.setFieldType(FieldSchema.getFieldTypeValue(tp)); 
		//�����O�X�ݒ�
		field.setFieldLength(Integer.parseInt(colInf.getStr(2))) ;
		//�j�d�x�����ݒ�
		field.setFieldKey(Integer.parseInt(colInf.getStr(3))) ;
		if(sz > 4){
			field.setCrypt(Integer.parseInt(colInf.getStr(4)) > 0);
		}
		return field ;
	}

	/**
	 * ��T�C�Y���o�͂���B
	 * @return�@��
	 */
	public int size(){
		if(mFieldAry == null){
			return 0 ;
		}else{
			return mFieldAry.size() ;
		}
	}

	/**
	 * INDEX�ԍ��w��ŗ�X�L�[�}���o�͂���B
	 * @param index
	 * @return�@FieldSchema
	 */
	public FieldSchema get(int index){
        if(index < 0 || index >= mFieldAry.size()){
            return null;
        }
		return (FieldSchema)mFieldAry.get(index) ;
	}
	/**
	 * �񖼎w��ŗ�X�L�[�}���o�͂���B
	 * @param name
	 * @return�@FieldSchema
	 */
	public FieldSchema get(String name){
		return (FieldSchema)mFieldHash.get(name) ;
	}
	
	/**
	 * ��X�L�[�}�̃��X�g��ListIterator�ŉ������܂��B
	 * @return ��X�L�[�}���X�g
	 */
	public ListIterator listIterator(){
		return mFieldAry.listIterator();
	}
	
	/**
	 * ��X�L�[�}��ǉ����܂��B
	 * @param scm
	 */
	private void add(FieldSchema scm){
		if(mFieldHash.containsKey(scm.getFieldName())){
			throw new InvalidSchemaException(scm.getFieldName() + "is duplicate") ;
		}else{
			mFieldHash.put(scm.getFieldName(),scm) ;
			mFieldAry.add(scm) ;
			scm.setIndex(mFieldAry.size() -1) ;
			if(scm.isUniqueKey()){
				this.mUniqueKeyAry.add(scm) ;
			}
		}
	}
	
}
