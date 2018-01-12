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
package jp.ossc.nimbus.service.context;

import java.util.Map;
import java.util.HashMap;
import java.util.Iterator;
import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectOutput;
import java.io.ObjectInput;

import jp.ossc.nimbus.beans.dataset.Record;
import jp.ossc.nimbus.beans.dataset.RecordSchema;
import jp.ossc.nimbus.beans.dataset.PropertySchema;
import jp.ossc.nimbus.beans.dataset.PropertySchemaDefineException;
import jp.ossc.nimbus.beans.dataset.PropertySetException;
import jp.ossc.nimbus.beans.dataset.PropertyGetException;

/**
 * ���L�R���e�L�X�g�p�̃��R�[�h�B<p>
 * �����X�V���T�|�[�g����B<br>
 *
 * @author M.Takata
 */
public class SharedContextRecord extends Record implements SharedContextValueDifferenceSupport{
    
    private static final long serialVersionUID = 1543899282652907287l;
    
    protected int updateVersion;
    
    /**
     * ����`�̃��R�[�h�𐶐�����B<p>
     */
    public SharedContextRecord(){
    }
    
    /**
     * ���R�[�h�𐶐�����B<p>
     *
     * @param schema �X�L�[�}������
     * @exception PropertySchemaDefineException �v���p�e�B�̃X�L�[�}��`�Ɏ��s�����ꍇ
     */
    public SharedContextRecord(String schema) throws PropertySchemaDefineException{
        super(schema);
    }
    
    /**
     * ���R�[�h�𐶐�����B<p>
     *
     * @param recordSchema �X�L�[�}�����񂩂琶�����ꂽ���R�[�h�X�L�[�}
     * @exception PropertySchemaDefineException �v���p�e�B�̃X�L�[�}��`�Ɏ��s�����ꍇ
     */
    public SharedContextRecord(RecordSchema recordSchema){
        super(recordSchema);
    }
    
    public void setUpdateVersion(int version){
        updateVersion = version;
    }
    
    public int getUpdateVersion(){
        return updateVersion;
    }
    
    protected static int compareToUpdateVersion(int ver1, int ver2){
        long version1 = ver1;
        long version2 = ver2;
        final long middle = ((long)Integer.MAX_VALUE - (long)Integer.MIN_VALUE) / 2l;
        
        if(version1 == version2){
            return 0;
        }else{
            if(version1 > version2){
                if((version1 - version2) > middle){
                    version1 = version1 - (long)Integer.MAX_VALUE;
                    return version1 > version2 ? 1 : -1;
                }else{
                    return 1;
                }
            }else{
                if((version2 - version1) > middle){
                    version2 = version2 - (long)Integer.MAX_VALUE;
                    return version1 > version2 ? -1 : 1;
                }else{
                    return -1;
                }
            }
        }
    }
    
    /**
     * �w�肳�ꂽ���O�̃v���p�e�B�ɁA�w�肳�ꂽ�l���X�V�����ꍇ�̍��������擾����B<p>
     *
     * @param name �v���p�e�B��
     * @param val �v���p�e�B�̒l
     * @param diff ����
     * @return ����
     * @exception PropertySetException �v���p�e�B�̐ݒ�Ɏ��s�����ꍇ
     * @exception SharedContextUpdateException �������̎擾�Ɏ��s�����ꍇ
     */
    public SharedContextValueDifference updateProperty(String name, Object val, SharedContextValueDifference diff) throws PropertySetException, SharedContextUpdateException{
        RecordSchema recordSchema = getRecordSchema();
        if(recordSchema == null){
            throw new PropertySetException(null, "Schema is not initialized.");
        }
        final PropertySchema propertySchema = recordSchema.getPropertySchema(name);
        if(propertySchema == null){
            throw new PropertySetException(null, "No such property : " + name);
        }
        return updateProperty(recordSchema.getPropertyIndex(name), val, diff);
    }
    
    /**
     * �w�肳�ꂽ�C���f�b�N�X�̃v���p�e�B�ɁA�w�肳�ꂽ�l���X�V�����ꍇ�̍��������擾����B<p>
     *
     * @param index �v���p�e�B�̃C���f�b�N�X 
     * @param val �v���p�e�B�̒l
     * @param diff ����
     * @return ����
     * @exception PropertySetException �v���p�e�B�̐ݒ�Ɏ��s�����ꍇ
     * @exception SharedContextUpdateException �������̎擾�Ɏ��s�����ꍇ
     */
    public SharedContextValueDifference updateProperty(int index, Object val, SharedContextValueDifference diff) throws PropertySetException, SharedContextUpdateException{
        if(getRecordList() == null){
            if(diff == null){
                diff = new Difference();
            }else if(!(diff instanceof Difference)){
                throw new SharedContextUpdateException("Unsupported type. class=" + diff.getClass().getName());
            }
            ((Difference)diff).updateProperty(this, index, val);
        }else{
            if(diff == null){
                diff = new SharedContextRecordList.Difference();
            }else if(!(diff instanceof SharedContextRecordList.Difference)){
                throw new SharedContextUpdateException("Unsupported type. class=" + diff.getClass().getName());
            }
            Difference recordDiff = ((SharedContextRecordList.Difference)diff).getRecordDifference(this.index);
            if(recordDiff == null){
                recordDiff = new Difference();
            }
            recordDiff.updateProperty(this, index, val);
            ((SharedContextRecordList.Difference)diff).updateRecord((SharedContextRecordList)getRecordList(), this.index, recordDiff);
        }
        return diff;
    }
    
    /**
     * �w�肳�ꂽ���O�̃v���p�e�B�ɁA�w�肳�ꂽ�l���X�V�����ꍇ�̍��������擾����B<p>
     *
     * @param name �v���p�e�B��
     * @param val �v���p�e�B�̒l
     * @param diff ����
     * @return ����
     * @exception PropertySetException �v���p�e�B�̐ݒ�Ɏ��s�����ꍇ
     * @exception SharedContextUpdateException �������̎擾�Ɏ��s�����ꍇ
     */
    public SharedContextValueDifference updateProperty(String name, boolean val, SharedContextValueDifference diff) throws PropertySetException, SharedContextUpdateException{
        return updateProperty(name, val ? Boolean.TRUE : Boolean.FALSE, diff);
    }
    
    /**
     * �w�肳�ꂽ�C���f�b�N�X�̃v���p�e�B�ɁA�w�肳�ꂽ�l���X�V�����ꍇ�̍��������擾����B<p>
     *
     * @param index �v���p�e�B�̃C���f�b�N�X 
     * @param val �v���p�e�B�̒l
     * @param diff ����
     * @return ����
     * @exception PropertySetException �v���p�e�B�̐ݒ�Ɏ��s�����ꍇ
     * @exception SharedContextUpdateException �������̎擾�Ɏ��s�����ꍇ
     */
    public SharedContextValueDifference updateProperty(int index, boolean val, SharedContextValueDifference diff) throws PropertySetException, SharedContextUpdateException{
        return updateProperty(index, val ? Boolean.TRUE : Boolean.FALSE, diff);
    }
    
    /**
     * �w�肳�ꂽ���O�̃v���p�e�B�ɁA�w�肳�ꂽ�l���X�V�����ꍇ�̍��������擾����B<p>
     *
     * @param name �v���p�e�B��
     * @param val �v���p�e�B�̒l
     * @param diff ����
     * @return ����
     * @exception PropertySetException �v���p�e�B�̐ݒ�Ɏ��s�����ꍇ
     * @exception SharedContextUpdateException �������̎擾�Ɏ��s�����ꍇ
     */
    public SharedContextValueDifference updateProperty(String name, byte val, SharedContextValueDifference diff) throws PropertySetException, SharedContextUpdateException{
        return updateProperty(name, new Byte(val), diff);
    }
    
    /**
     * �w�肳�ꂽ�C���f�b�N�X�̃v���p�e�B�ɁA�w�肳�ꂽ�l���X�V�����ꍇ�̍��������擾����B<p>
     *
     * @param index �v���p�e�B�̃C���f�b�N�X 
     * @param val �v���p�e�B�̒l
     * @param diff ����
     * @return ����
     * @exception PropertySetException �v���p�e�B�̐ݒ�Ɏ��s�����ꍇ
     * @exception SharedContextUpdateException �������̎擾�Ɏ��s�����ꍇ
     */
    public SharedContextValueDifference updateProperty(int index, byte val, SharedContextValueDifference diff) throws PropertySetException, SharedContextUpdateException{
        return updateProperty(index, new Byte(val), diff);
    }
    
    /**
     * �w�肳�ꂽ���O�̃v���p�e�B�ɁA�w�肳�ꂽ�l���X�V�����ꍇ�̍��������擾����B<p>
     *
     * @param name �v���p�e�B��
     * @param val �v���p�e�B�̒l
     * @param diff ����
     * @return ����
     * @exception PropertySetException �v���p�e�B�̐ݒ�Ɏ��s�����ꍇ
     * @exception SharedContextUpdateException �������̎擾�Ɏ��s�����ꍇ
     */
    public SharedContextValueDifference updateProperty(String name, char val, SharedContextValueDifference diff) throws PropertySetException, SharedContextUpdateException{
        return updateProperty(name, new Character(val), diff);
    }
    
    /**
     * �w�肳�ꂽ�C���f�b�N�X�̃v���p�e�B�ɁA�w�肳�ꂽ�l���X�V�����ꍇ�̍��������擾����B<p>
     *
     * @param index �v���p�e�B�̃C���f�b�N�X 
     * @param val �v���p�e�B�̒l
     * @param diff ����
     * @return ����
     * @exception PropertySetException �v���p�e�B�̐ݒ�Ɏ��s�����ꍇ
     * @exception SharedContextUpdateException �������̎擾�Ɏ��s�����ꍇ
     */
    public SharedContextValueDifference updateProperty(int index, char val, SharedContextValueDifference diff) throws PropertySetException, SharedContextUpdateException{
        return updateProperty(index, new Character(val), diff);
    }
    
    /**
     * �w�肳�ꂽ���O�̃v���p�e�B�ɁA�w�肳�ꂽ�l���X�V�����ꍇ�̍��������擾����B<p>
     *
     * @param name �v���p�e�B��
     * @param val �v���p�e�B�̒l
     * @param diff ����
     * @return ����
     * @exception PropertySetException �v���p�e�B�̐ݒ�Ɏ��s�����ꍇ
     * @exception SharedContextUpdateException �������̎擾�Ɏ��s�����ꍇ
     */
    public SharedContextValueDifference updateProperty(String name, short val, SharedContextValueDifference diff) throws PropertySetException, SharedContextUpdateException{
        return updateProperty(name, new Short(val), diff);
    }
    
    /**
     * �w�肳�ꂽ�C���f�b�N�X�̃v���p�e�B�ɁA�w�肳�ꂽ�l���X�V�����ꍇ�̍��������擾����B<p>
     *
     * @param index �v���p�e�B�̃C���f�b�N�X 
     * @param val �v���p�e�B�̒l
     * @param diff ����
     * @return ����
     * @exception PropertySetException �v���p�e�B�̐ݒ�Ɏ��s�����ꍇ
     * @exception SharedContextUpdateException �������̎擾�Ɏ��s�����ꍇ
     */
    public SharedContextValueDifference updateProperty(int index, short val, SharedContextValueDifference diff) throws PropertySetException, SharedContextUpdateException{
        return updateProperty(index, new Short(val), diff);
    }
    
    /**
     * �w�肳�ꂽ���O�̃v���p�e�B�ɁA�w�肳�ꂽ�l���X�V�����ꍇ�̍��������擾����B<p>
     *
     * @param name �v���p�e�B��
     * @param val �v���p�e�B�̒l
     * @param diff ����
     * @return ����
     * @exception PropertySetException �v���p�e�B�̐ݒ�Ɏ��s�����ꍇ
     * @exception SharedContextUpdateException �������̎擾�Ɏ��s�����ꍇ
     */
    public SharedContextValueDifference updateProperty(String name, int val, SharedContextValueDifference diff) throws PropertySetException, SharedContextUpdateException{
        return updateProperty(name, new Integer(val), diff);
    }
    
    /**
     * �w�肳�ꂽ�C���f�b�N�X�̃v���p�e�B�ɁA�w�肳�ꂽ�l���X�V�����ꍇ�̍��������擾����B<p>
     *
     * @param index �v���p�e�B�̃C���f�b�N�X 
     * @param val �v���p�e�B�̒l
     * @param diff ����
     * @return ����
     * @exception PropertySetException �v���p�e�B�̐ݒ�Ɏ��s�����ꍇ
     * @exception SharedContextUpdateException �������̎擾�Ɏ��s�����ꍇ
     */
    public SharedContextValueDifference updateProperty(int index, int val, SharedContextValueDifference diff) throws PropertySetException, SharedContextUpdateException{
        return updateProperty(index, new Integer(val), diff);
    }
    
    /**
     * �w�肳�ꂽ���O�̃v���p�e�B�ɁA�w�肳�ꂽ�l���X�V�����ꍇ�̍��������擾����B<p>
     *
     * @param name �v���p�e�B��
     * @param val �v���p�e�B�̒l
     * @param diff ����
     * @return ����
     * @exception PropertySetException �v���p�e�B�̐ݒ�Ɏ��s�����ꍇ
     * @exception SharedContextUpdateException �������̎擾�Ɏ��s�����ꍇ
     */
    public SharedContextValueDifference updateProperty(String name, long val, SharedContextValueDifference diff) throws PropertySetException, SharedContextUpdateException{
        return updateProperty(name, new Long(val), diff);
    }
    
    /**
     * �w�肳�ꂽ�C���f�b�N�X�̃v���p�e�B�ɁA�w�肳�ꂽ�l���X�V�����ꍇ�̍��������擾����B<p>
     *
     * @param index �v���p�e�B�̃C���f�b�N�X 
     * @param val �v���p�e�B�̒l
     * @param diff ����
     * @return ����
     * @exception PropertySetException �v���p�e�B�̐ݒ�Ɏ��s�����ꍇ
     * @exception SharedContextUpdateException �������̎擾�Ɏ��s�����ꍇ
     */
    public SharedContextValueDifference updateProperty(int index, long val, SharedContextValueDifference diff) throws PropertySetException, SharedContextUpdateException{
        return updateProperty(index, new Long(val), diff);
    }
    
    /**
     * �w�肳�ꂽ���O�̃v���p�e�B�ɁA�w�肳�ꂽ�l���X�V�����ꍇ�̍��������擾����B<p>
     *
     * @param name �v���p�e�B��
     * @param val �v���p�e�B�̒l
     * @param diff ����
     * @return ����
     * @exception PropertySetException �v���p�e�B�̐ݒ�Ɏ��s�����ꍇ
     * @exception SharedContextUpdateException �������̎擾�Ɏ��s�����ꍇ
     */
    public SharedContextValueDifference updateProperty(String name, float val, SharedContextValueDifference diff) throws PropertySetException, SharedContextUpdateException{
        return updateProperty(name, new Float(val), diff);
    }
    
    /**
     * �w�肳�ꂽ�C���f�b�N�X�̃v���p�e�B�ɁA�w�肳�ꂽ�l���X�V�����ꍇ�̍��������擾����B<p>
     *
     * @param index �v���p�e�B�̃C���f�b�N�X 
     * @param val �v���p�e�B�̒l
     * @param diff ����
     * @return ����
     * @exception PropertySetException �v���p�e�B�̐ݒ�Ɏ��s�����ꍇ
     * @exception SharedContextUpdateException �������̎擾�Ɏ��s�����ꍇ
     */
    public SharedContextValueDifference updateProperty(int index, float val, SharedContextValueDifference diff) throws PropertySetException, SharedContextUpdateException{
        return updateProperty(index, new Float(val), diff);
    }
    
    /**
     * �w�肳�ꂽ���O�̃v���p�e�B�ɁA�w�肳�ꂽ�l���X�V�����ꍇ�̍��������擾����B<p>
     *
     * @param name �v���p�e�B��
     * @param val �v���p�e�B�̒l
     * @param diff ����
     * @return ����
     * @exception PropertySetException �v���p�e�B�̐ݒ�Ɏ��s�����ꍇ
     * @exception SharedContextUpdateException �������̎擾�Ɏ��s�����ꍇ
     */
    public SharedContextValueDifference updateProperty(String name, double val, SharedContextValueDifference diff) throws PropertySetException, SharedContextUpdateException{
        return updateProperty(name, new Double(val), diff);
    }
    
    /**
     * �w�肳�ꂽ�C���f�b�N�X�̃v���p�e�B�ɁA�w�肳�ꂽ�l���X�V�����ꍇ�̍��������擾����B<p>
     *
     * @param index �v���p�e�B�̃C���f�b�N�X 
     * @param val �v���p�e�B�̒l
     * @param diff ����
     * @return ����
     * @exception PropertySetException �v���p�e�B�̐ݒ�Ɏ��s�����ꍇ
     * @exception SharedContextUpdateException �������̎擾�Ɏ��s�����ꍇ
     */
    public SharedContextValueDifference updateProperty(int index, double val, SharedContextValueDifference diff) throws PropertySetException, SharedContextUpdateException{
        return updateProperty(index, new Double(val), diff);
    }
    
    /**
     * �w�肳�ꂽ���O�̃v���p�e�B�ɁA�w�肳�ꂽ�l���p�[�X���čX�V�����ꍇ�̍��������擾����B<p>
     *
     * @param name �v���p�e�B��
     * @param val �v���p�e�B�̒l
     * @param diff ����
     * @return ����
     * @exception PropertySetException �v���p�e�B�̐ݒ�Ɏ��s�����ꍇ
     * @exception SharedContextUpdateException �������̎擾�Ɏ��s�����ꍇ
     */
    public SharedContextValueDifference updateParseProperty(String name, Object val, SharedContextValueDifference diff) throws PropertySetException, SharedContextUpdateException{
        RecordSchema recordSchema = getRecordSchema();
        if(recordSchema == null){
            throw new PropertySetException(null, "Schema is not initialized.");
        }
        final PropertySchema propertySchema = recordSchema.getPropertySchema(name);
        if(propertySchema == null){
            throw new PropertySetException(null, "No such property : " + name);
        }
        return updateProperty(recordSchema.getPropertyIndex(name), propertySchema.parse(val), diff);
    }
    
    /**
     * �w�肳�ꂽ�C���f�b�N�X�̃v���p�e�B�ɁA�w�肳�ꂽ�l���p�[�X���čX�V�����ꍇ�̍��������擾����B<p>
     *
     * @param index �v���p�e�B�̃C���f�b�N�X 
     * @param val �v���p�e�B�̒l
     * @param diff ����
     * @return ����
     * @exception PropertySetException �v���p�e�B�̐ݒ�Ɏ��s�����ꍇ
     * @exception SharedContextUpdateException �������̎擾�Ɏ��s�����ꍇ
     */
    public SharedContextValueDifference updateParseProperty(int index, Object val, SharedContextValueDifference diff) throws PropertySetException, SharedContextUpdateException{
        RecordSchema recordSchema = getRecordSchema();
        if(recordSchema == null){
            throw new PropertySetException(null, "Schema is not initialized.");
        }
        final PropertySchema propertySchema
             = recordSchema.getPropertySchema(index);
        if(propertySchema == null){
            throw new PropertySetException(null, "No such property : " + index);
        }
        return updateProperty(index, propertySchema.parse(val), diff);
    }
    
    public int update(SharedContextValueDifference diff) throws SharedContextUpdateException{
        if(!(diff instanceof Difference)){
            throw new SharedContextUpdateException("Unsupported type. class=" + diff.getClass().getName());
        }
        return ((Difference)diff).updateRecord(this);
    }
    
    protected static void writeInt(ObjectOutput out, int val) throws IOException{
        if(val >= Byte.MIN_VALUE && val <= Byte.MAX_VALUE){
            out.writeByte((byte)1);
            out.writeByte((byte)val);
        }else if(val >= Short.MIN_VALUE && val <= Short.MAX_VALUE){
            out.writeByte((byte)2);
            out.writeShort((short)val);
        }else{
            out.writeByte((byte)3);
            out.writeInt(val);
        }
    }
    
    protected static int readInt(ObjectInput in) throws IOException{
        final int type = in.readByte();
        switch(type){
        case 1:
            return in.readByte();
        case 2:
            return in.readShort();
        default:
            return in.readInt();
        }
    }
    
    protected void writeExternalValues(ObjectOutput out) throws IOException{
        super.writeExternalValues(out);
        SharedContextRecord.writeInt(out, updateVersion);
    }
    
    protected void readExternalValues(ObjectInput in) throws IOException, ClassNotFoundException{
        super.readExternalValues(in);
        updateVersion = SharedContextRecord.readInt(in);
    }
    
    /**
     * ���R�[�h�������B<p>
     *
     * @author M.Takata
     */
    public static class Difference implements SharedContextValueDifference, Externalizable{
        
        private static final long serialVersionUID = 7551646648749330023l;
        
        private int updateVersion;
        private Map updateValueMap;
        
        public int getUpdateVersion(){
            return updateVersion;
        }
        
        /**
         * �w�肳�ꂽ�v���p�e�B�̍X�V���i�[����B<p>
         * ���݂̃v���p�e�B�̒l�ƍ������Ȃ��ꍇ�́A���������B<br>
         *
         * @param record �X�V�Ώۂ̃��R�[�h
         * @param index �v���p�e�B�̃C���f�b�N�X
         * @param value �X�V����l
         * @exception SharedContextUpdateException �X�V�̊i�[�Ɏ��s�����ꍇ
         */
        public void updateProperty(SharedContextRecord record, int index, Object value) throws SharedContextUpdateException{
            Integer key = new Integer(index);
            try{
                Object old = record.getProperty(index);
                if((old == null && value != null)
                    || (old != null && value == null)
                    || (old != null && !old.equals(value))
                ){
                    if(updateValueMap == null){
                        updateValueMap = new HashMap();
                    }
                    updateValueMap.put(key, value);
                }else if(updateValueMap != null && updateValueMap.containsKey(key)){
                    updateValueMap.remove(key);
                }
            }catch(PropertyGetException e){
                throw new SharedContextUpdateException(e);
            }
            updateVersion = record.getUpdateVersion() + 1;
        }
        
        /**
         * �w�肳�ꂽ���R�[�h�ɍX�V�𔽉f����B<p>
         *
         * @param record �X�V�Ώۂ̃��R�[�h
         * @return �X�V���ꂽ�ꍇ�A1�B�X�V����K�v���Ȃ������ꍇ�A0�B����������ꂸ�ɁA�X�V�ł��Ȃ��ꍇ�A-1�B
         * @exception SharedContextUpdateException �X�V�̔��f�Ɏ��s�����ꍇ
         */
        public int updateRecord(SharedContextRecord record) throws SharedContextUpdateException{
            if(updateValueMap != null && updateValueMap.size() != 0){
                if(SharedContextRecord.compareToUpdateVersion(record.getUpdateVersion(), updateVersion) >= 0){
                    return 0;
                }else if(record.getUpdateVersion() + 1 != updateVersion){
                    return -1;
                }
                try{
                    for(Iterator itr = updateValueMap.entrySet().iterator(); itr.hasNext();){
                        Map.Entry entry = (Map.Entry)itr.next();
                        record.setProperty(((Integer)entry.getKey()).intValue(), entry.getValue());
                    }
                }catch(PropertySetException e){
                    throw new SharedContextUpdateException(e);
                }
            }
            record.setUpdateVersion(updateVersion);
            return 1;
        }
        
        /**
         * �X�V�̂������v���p�e�B�̃C���f�b�N�X���擾����B<p>
         *
         * @return �C���f�b�N�X�̔z��B�X�V���Ȃ��ꍇ�́Anull
         */
        public int[] getUpdatePropertyIndexs(){
            if(updateValueMap == null || updateValueMap.size() == 0){
                return null;
            }
            int[] result = new int[updateValueMap.size()];
            int index = 0;
            for(Iterator itr = updateValueMap.keySet().iterator(); itr.hasNext();){
                result[index++] = ((Integer)itr.next()).intValue();
            }
            return result;
        }
        
        /**
         * �w�肳�ꂽ�v���p�e�B�̍X�V���ꂽ�l���擾����B<p>
         *
         * @param index �v���p�e�B�̃C���f�b�N�X
         * @return �v���p�e�B�̒l�B�X�V����Ă��Ȃ��ꍇ�́Anull
         */
        public Object getUpdateProperty(int index){
            return updateValueMap == null ? null : updateValueMap.get(new Integer(index));
        }
        
        /**
         * �w�肳�ꂽ�v���p�e�B�̍X�V���폜����B<p>
         *
         * @param index �v���p�e�B�̃C���f�b�N�X
         */
        public void removeUpdateProperty(int index){
            if(updateValueMap != null){
                updateValueMap.remove(new Integer(index));
            }
        }
        
        /**
         * �w�肳�ꂽ�v���p�e�B���X�V���ꂽ���𔻒肷��B<p>
         *
         * @param index �v���p�e�B�̃C���f�b�N�X
         * @return �X�V���ꂽ�ꍇ�́Atrue
         */
        public boolean isUpdate(int index){
            return updateValueMap == null ? false : updateValueMap.containsKey(new Integer(index));
        }
        
        /**
         * �X�V���ꂽ���𔻒肷��B<p>
         *
         * @return �X�V���ꂽ�ꍇ�́Atrue
         */
        public boolean isUpdate(){
            return updateValueMap != null && updateValueMap.size() != 0;
        }
        
        public void writeExternal(ObjectOutput out) throws IOException{
            out.writeObject(updateValueMap);
            SharedContextRecord.writeInt(out, updateVersion);
        }
        
        public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException{
            updateValueMap = (Map)in.readObject();
            updateVersion = SharedContextRecord.readInt(in);
        }
    }
}