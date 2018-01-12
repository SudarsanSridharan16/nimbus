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
import java.util.Collection;
import java.util.List;
import java.util.ArrayList;
import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectOutput;
import java.io.ObjectInput;

import jp.ossc.nimbus.beans.dataset.Record;
import jp.ossc.nimbus.beans.dataset.RecordList;
import jp.ossc.nimbus.beans.dataset.RecordSchema;
import jp.ossc.nimbus.beans.dataset.PropertySchemaDefineException;

/**
 * ���L�R���e�L�X�g�p�̃��R�[�h���X�g�B<p>
 * �����X�V���T�|�[�g����B<br>
 *
 * @author M.Takata
 */
public class SharedContextRecordList extends RecordList implements SharedContextValueDifferenceSupport{
    
    private static final long serialVersionUID = -1910946166181855454l;
    
    protected int updateVersion;
    
    /**
     * ����`�̃��R�[�h���X�g�𐶐�����B<p>
     */
    public SharedContextRecordList(){
    }
    
    /**
     * ����`�̃��R�[�h���X�g�𐶐�����B<p>
     * 
     * @param isSynch ����������ꍇtrue
     */
    public SharedContextRecordList(boolean isSynch){
        super(isSynch);
    }
    
    /**
     * ����`�̃��R�[�h���X�g�𐶐�����B<p>
     *
     * @param name ���R�[�h��
     */
    public SharedContextRecordList(String name){
        super(name);
    }
    
    /**
     * ����`�̃��R�[�h���X�g�𐶐�����B<p>
     *
     * @param name ���R�[�h��
     * @param isSynch ����������ꍇtrue
     */
    public SharedContextRecordList(String name, boolean isSynch){
        super(name, isSynch);
    }
    
    /**
     * ��̃��R�[�h���X�g�𐶐�����B<p>
     *
     * @param name ���R�[�h��
     * @param schema �X�L�[�}������
     * @exception PropertySchemaDefineException �v���p�e�B�̃X�L�[�}��`�Ɏ��s�����ꍇ
     */
    public SharedContextRecordList(String name, String schema)
     throws PropertySchemaDefineException{
        super(name, schema);
    }
    
    /**
     * ��̃��R�[�h���X�g�𐶐�����B<p>
     *
     * @param name ���R�[�h��
     * @param schema �X�L�[�}������
     * @param isSynch ����������ꍇtrue
     * @exception PropertySchemaDefineException �v���p�e�B�̃X�L�[�}��`�Ɏ��s�����ꍇ
     */
    public SharedContextRecordList(String name, String schema, boolean isSynch)
     throws PropertySchemaDefineException{
        super(name, schema, isSynch);
    }
    
    /**
     * ��̃��R�[�h���X�g�𐶐�����B<p>
     *
     * @param name ���R�[�h��
     * @param schema �X�L�[�}
     * @exception PropertySchemaDefineException �v���p�e�B�̃X�L�[�}��`�Ɏ��s�����ꍇ
     */
    public SharedContextRecordList(String name, RecordSchema schema)
     throws PropertySchemaDefineException{
        super(name, schema);
    }
    
    /**
     * ��̃��R�[�h���X�g�𐶐�����B<p>
     *
     * @param name ���R�[�h��
     * @param schema �X�L�[�}
     * @param isSynch ����������ꍇtrue
     * @exception PropertySchemaDefineException �v���p�e�B�̃X�L�[�}��`�Ɏ��s�����ꍇ
     */
    public SharedContextRecordList(String name, RecordSchema schema, boolean isSynch)
     throws PropertySchemaDefineException{
        super(name, schema, isSynch);
    }
    
    /**
     * ��̃��R�[�h���X�g�𐶐�����B<p>
     *
     * @param name ���R�[�h��
     * @param clazz ���R�[�h�N���X
     * @exception PropertySchemaDefineException �v���p�e�B�̃X�L�[�}��`�Ɏ��s�����ꍇ
     */
    public SharedContextRecordList(String name, Class clazz)
     throws PropertySchemaDefineException{
        super(name, clazz);
    }
    
    /**
     * ��̃��R�[�h���X�g�𐶐�����B<p>
     *
     * @param name ���R�[�h��
     * @param clazz ���R�[�h�N���X
     * @param isSynch ����������ꍇtrue
     * @exception PropertySchemaDefineException �v���p�e�B�̃X�L�[�}��`�Ɏ��s�����ꍇ
     */
    public SharedContextRecordList(String name, Class clazz, boolean isSynch)
     throws PropertySchemaDefineException{
        super(name, clazz, isSynch);
    }
    
    public void setUpdateVersion(int version){
        updateVersion = version;
    }
    
    public int getUpdateVersion(){
        return updateVersion;
    }
    
    public Record createRecord(){
        if(recordClass == null){
            return new SharedContextRecord(recordSchema);
        }else{
            return super.createRecord();
        }
    }
    
    /**
     * �w�肳�ꂽ���R�[�h��ǉ������ꍇ�̍��������擾����B<p>
     *
     * @param record ���R�[�h
     * @param diff ����
     * @return ����
     * @exception SharedContextUpdateException �������̎擾�Ɏ��s�����ꍇ
     */
    public SharedContextValueDifference updateAdd(Record record, SharedContextValueDifference diff) throws SharedContextUpdateException{
        if(diff == null){
            diff = new Difference();
        }else if(!(diff instanceof Difference)){
            throw new SharedContextUpdateException("Unsupported type. class=" + diff.getClass().getName());
        }
        ((Difference)diff).add(this, record);
        return diff;
    }
    
    /**
     * �w�肳�ꂽ���R�[�h���A�w�肵���C���f�b�N�X�ɑ}�������ꍇ�̍��������擾����B<p>
     *
     * @param index �C���f�b�N�X
     * @param record ���R�[�h
     * @param diff ����
     * @return ����
     * @exception SharedContextUpdateException �������̎擾�Ɏ��s�����ꍇ
     */
    public SharedContextValueDifference updateAdd(int index, Record record, SharedContextValueDifference diff) throws SharedContextUpdateException{
        if(diff == null){
            diff = new Difference();
        }else if(!(diff instanceof Difference)){
            throw new SharedContextUpdateException("Unsupported type. class=" + diff.getClass().getName());
        }
        ((Difference)diff).add(this, index, record);
        return diff;
    }
    
    /**
     * �w�肳�ꂽ���R�[�h���A�w�肵���C���f�b�N�X�̃��R�[�h�ƍ����ւ����ꍇ�̍��������擾����B<p>
     *
     * @param index �C���f�b�N�X
     * @param record ���R�[�h
     * @param diff ����
     * @return ����
     * @exception SharedContextUpdateException �������̎擾�Ɏ��s�����ꍇ
     */
    public SharedContextValueDifference updateSet(int index, Record record, SharedContextValueDifference diff) throws SharedContextUpdateException{
        if(diff == null){
            diff = new Difference();
        }else if(!(diff instanceof Difference)){
            throw new SharedContextUpdateException("Unsupported type. class=" + diff.getClass().getName());
        }
        ((Difference)diff).set(this, index, record);
        return diff;
    }
    
    /**
     * �w�肳�ꂽ�C���f�b�N�X�̃��R�[�h���폜�����ꍇ�̍��������擾����B<p>
     *
     * @param index �C���f�b�N�X
     * @param diff ����
     * @return ����
     * @exception SharedContextUpdateException �������̎擾�Ɏ��s�����ꍇ
     */
    public SharedContextValueDifference updateRemove(int index, SharedContextValueDifference diff) throws SharedContextUpdateException{
        if(diff == null){
            diff = new Difference();
        }else if(!(diff instanceof Difference)){
            throw new SharedContextUpdateException("Unsupported type. class=" + diff.getClass().getName());
        }
        ((Difference)diff).remove(this, index);
        return diff;
    }
    
    public SharedContextValueDifference updateRemove(Record record, SharedContextValueDifference diff) throws SharedContextUpdateException{
        if(diff == null){
            diff = new Difference();
        }else if(!(diff instanceof Difference)){
            throw new SharedContextUpdateException("Unsupported type. class=" + diff.getClass().getName());
        }
        ((Difference)diff).remove(this, record);
        return diff;
    }
    
    public SharedContextValueDifference updateClear(SharedContextValueDifference diff) throws SharedContextUpdateException{
        if(diff == null){
            diff = new Difference();
        }else if(!(diff instanceof Difference)){
            throw new SharedContextUpdateException("Unsupported type. class=" + diff.getClass().getName());
        }
        ((Difference)diff).clear(this);
        return diff;
    }
    
    public int update(SharedContextValueDifference diff) throws SharedContextUpdateException{
        if(!(diff instanceof Difference)){
            throw new SharedContextUpdateException("Unsupported type. class=" + diff.getClass().getName());
        }
        return ((Difference)diff).updateRecordList(this);
    }
    
    public void writeExternal(ObjectOutput out) throws IOException{
        super.writeExternal(out);
        SharedContextRecordList.writeInt(out, updateVersion);
    }
    
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException{
        super.readExternal(in);
        updateVersion = SharedContextRecordList.readInt(in);
    }
    
    /**
     * ���R�[�h���X�g�������B<p>
     *
     * @author M.Takata
     */
    public static class Difference implements SharedContextValueDifference, Externalizable{
        
        private static final long serialVersionUID = -3722608370963750486l;
        
        private int updateVersion;
        private Map recordDiffMap;
        private List transactionList;
        
        public int getUpdateVersion(){
            return updateVersion;
        }
        
        /**
         * ���R�[�h��ǉ�����X�V���i�[����B<p>
         *
         * @param list ���R�[�h���X�g
         * @param record �ǉ����郌�R�[�h
         * @exception SharedContextUpdateException �X�V�̊i�[�Ɏ��s�����ꍇ
         */
        public void add(SharedContextRecordList list, Record record) throws SharedContextUpdateException{
            if(transactionList == null){
                transactionList = new ArrayList();
            }
            transactionList.add(new AddTransaction(record));
            updateVersion = list.getUpdateVersion() + 1;
        }
        
        /**
         * ���R�[�h��ǉ�����X�V���i�[����B<p>
         *
         * @param list ���R�[�h���X�g
         * @param index �ǉ����郌�R�[�h�̃C���f�b�N�X
         * @param record �ǉ����郌�R�[�h
         * @exception SharedContextUpdateException �X�V�̊i�[�Ɏ��s�����ꍇ
         */
        public void add(SharedContextRecordList list, int index, Record record) throws SharedContextUpdateException{
            if(transactionList == null){
                transactionList = new ArrayList();
            }
            if(index < 0 || index > list.size() - 1){
                throw new SharedContextUpdateException("Illegal index. index=" + index + ", size=" + list.size());
            }
            AddTransaction addTran = new AddTransaction(index, record);
            for(int i = 0; i < transactionList.size(); i++){
                Transaction tran = (Transaction)transactionList.get(i);
                if(tran instanceof CollectionTransaction){
                    throw new SharedContextUpdateException("It is not possible to execute a single operation after a set operation.");
                }
                addTran.updateIndexDifference(tran.getIndexDifference());
            }
            transactionList.add(addTran);
            updateVersion = list.getUpdateVersion() + 1;
        }
        
        /**
         * ���R�[�h�������ւ�����X�V���i�[����B<p>
         *
         * @param list ���R�[�h���X�g
         * @param index �����ւ����郌�R�[�h�̃C���f�b�N�X
         * @param record �����ւ����郌�R�[�h
         * @exception SharedContextUpdateException �X�V�̊i�[�Ɏ��s�����ꍇ
         */
        public void set(SharedContextRecordList list, int index, Record record) throws SharedContextUpdateException{
            if(transactionList == null){
                transactionList = new ArrayList();
            }
            if(index < 0 || index > list.size() - 1){
                throw new SharedContextUpdateException("Illegal index. index=" + index + ", size=" + list.size());
            }
            SetTransaction setTran = new SetTransaction(index, record);
            for(int i = 0; i < transactionList.size(); i++){
                Transaction tran = (Transaction)transactionList.get(i);
                if(tran instanceof CollectionTransaction){
                    throw new SharedContextUpdateException("It is not possible to execute a single operation after a set operation.");
                }
                setTran.updateIndexDifference(tran.getIndexDifference());
            }
            transactionList.add(setTran);
            updateVersion = list.getUpdateVersion() + 1;
        }
        
        /**
         * ���R�[�h���폜����X�V���i�[����B<p>
         * �w�肳�ꂽ���R�[�h�����R�[�h���X�g�Ɋ܂܂�Ă��Ȃ��ꍇ�́A��������B<br>
         *
         * @param list ���R�[�h���X�g
         * @param val �폜���郌�R�[�h
         * @exception SharedContextUpdateException �X�V�̊i�[�Ɏ��s�����ꍇ
         */
        public void remove(SharedContextRecordList list, Object val) throws SharedContextUpdateException{
            if(transactionList == null){
                transactionList = new ArrayList();
            }
            int index = -1;
            if(val != null){
                SharedContextRecord record = (SharedContextRecord)val;
                index = record.getIndex();
            }
            if(index == -1){
                index = list.indexOf(val);
            }
            if(index != -1){
                RemoveIndexTransaction removeTran = new RemoveIndexTransaction(index);
                for(int i = 0; i < transactionList.size(); i++){
                    Transaction tran = (Transaction)transactionList.get(i);
                    if(tran instanceof CollectionTransaction){
                        throw new SharedContextUpdateException("It is not possible to execute a single operation after a set operation.");
                    }
                    removeTran.updateIndexDifference(tran.getIndexDifference());
                }
                transactionList.add(removeTran);
            }
            updateVersion = list.getUpdateVersion() + 1;
        }
        
        /**
         * ���R�[�h���폜����X�V���i�[����B<p>
         *
         * @param list ���R�[�h���X�g
         * @param index �폜���郌�R�[�h�̃C���f�b�N�X
         * @exception SharedContextUpdateException �X�V�̊i�[�Ɏ��s�����ꍇ
         */
        public void remove(SharedContextRecordList list, int index) throws SharedContextUpdateException{
            if(transactionList == null){
                transactionList = new ArrayList();
            }
            if(index < 0 || index > list.size() - 1){
                throw new SharedContextUpdateException("Illegal index. index=" + index + ", size=" + list.size());
            }
            RemoveIndexTransaction removeTran = new RemoveIndexTransaction(index);
            for(int i = 0; i < transactionList.size(); i++){
                Transaction tran = (Transaction)transactionList.get(i);
                if(tran instanceof CollectionTransaction){
                    throw new SharedContextUpdateException("It is not possible to execute a single operation after a set operation.");
                }
                removeTran.updateIndexDifference(tran.getIndexDifference());
            }
            transactionList.add(removeTran);
            updateVersion = list.getUpdateVersion() + 1;
        }
        
        /**
         * ���R�[�h���X�g��S�č폜����X�V���i�[����B<p>
         *
         * @param list ���R�[�h���X�g
         * @exception SharedContextUpdateException �X�V�̊i�[�Ɏ��s�����ꍇ
         */
        public void clear(SharedContextRecordList list) throws SharedContextUpdateException{
            if(transactionList == null){
                transactionList = new ArrayList();
            }
            transactionList.add(new ClearTransaction());
            updateVersion = list.getUpdateVersion() + 1;
        }
        
        /**
         * ���R�[�h�̏W����ǉ�����X�V���i�[����B<p>
         *
         * @param list ���R�[�h���X�g
         * @param c �ǉ����郌�R�[�h�̏W��
         * @exception SharedContextUpdateException �X�V�̊i�[�Ɏ��s�����ꍇ
         */
        public void addAll(SharedContextRecordList list, Collection c) throws SharedContextUpdateException{
            if(transactionList == null){
                transactionList = new ArrayList();
            }
            transactionList.add(new AddAllTransaction(c));
            updateVersion = list.getUpdateVersion() + 1;
        }
        
        /**
         * ���R�[�h�̏W����ǉ�����X�V���i�[����B<p>
         *
         * @param list ���R�[�h���X�g
         * @param index �ǉ�����C���f�b�N�X
         * @param c �ǉ����郌�R�[�h�̏W��
         * @exception SharedContextUpdateException �X�V�̊i�[�Ɏ��s�����ꍇ
         */
        public void addAll(SharedContextRecordList list, int index, Collection c) throws SharedContextUpdateException{
            if(transactionList == null){
                transactionList = new ArrayList();
            }
            if(index < 0 || index > list.size() - 1){
                throw new SharedContextUpdateException("Illegal index. index=" + index + ", size=" + list.size());
            }
            transactionList.add(new AddAllTransaction(index, c));
            updateVersion = list.getUpdateVersion() + 1;
        }
        
        /**
         * ���R�[�h�̏W�����폜����X�V���i�[����B<p>
         *
         * @param list ���R�[�h���X�g
         * @param c �폜���郌�R�[�h�̏W��
         * @exception SharedContextUpdateException �X�V�̊i�[�Ɏ��s�����ꍇ
         */
        public void removeAll(SharedContextRecordList list, Collection c) throws SharedContextUpdateException{
            if(transactionList == null){
                transactionList = new ArrayList();
            }
            transactionList.add(new RemoveAllTransaction(c));
            updateVersion = list.getUpdateVersion() + 1;
        }
        
        /**
         * �w�肳�ꂽ���R�[�h�̏W���݂̂��c���X�V���i�[����B<p>
         *
         * @param list ���R�[�h���X�g
         * @param c �c�����R�[�h�̏W��
         * @exception SharedContextUpdateException �X�V�̊i�[�Ɏ��s�����ꍇ
         */
        public void retainAll(SharedContextRecordList list, Collection c) throws SharedContextUpdateException{
            if(transactionList == null){
                transactionList = new ArrayList();
            }
            transactionList.add(new RetainAllTransaction(c));
            updateVersion = list.getUpdateVersion() + 1;
        }
        
        /**
         * �w�肳�ꂽ���R�[�h�̍������X�V���i�[����B<p>
         *
         * @param list ���R�[�h���X�g
         * @param index ���R�[�h�̃C���f�b�N�X
         * @param diff ���R�[�h�̍������
         * @exception SharedContextUpdateException �X�V�̊i�[�Ɏ��s�����ꍇ
         */
        public void updateRecord(SharedContextRecordList list, int index, SharedContextRecord.Difference diff) throws SharedContextUpdateException{
            if(transactionList == null){
                transactionList = new ArrayList();
            }
            if(index < 0 || index > list.size() - 1){
                throw new SharedContextUpdateException("Illegal index. index=" + index + ", size=" + list.size());
            }
            if(recordDiffMap == null){
                recordDiffMap = new HashMap();
            }
            Object key = new Integer(index);
            if(!recordDiffMap.containsKey(key)){
                UpdateTransaction updateTran = new UpdateTransaction(index, diff);
                for(int i = 0; i < transactionList.size(); i++){
                    Transaction tran = (Transaction)transactionList.get(i);
                    if(tran instanceof CollectionTransaction){
                        throw new SharedContextUpdateException("It is not possible to execute a single operation after a set operation.");
                    }
                    updateTran.updateIndexDifference(tran.getIndexDifference());
                }
                recordDiffMap.put(key, diff);
                transactionList.add(updateTran);
            }
            updateVersion = list.getUpdateVersion() + 1;
        }
        
        /**
         * �w�肳�ꂽ���R�[�h�̍��������擾����B<p>
         *
         * @param index ���R�[�h�̃C���f�b�N�X
         * @return �������B�������Ȃ��ꍇ�́Anull
         */
        protected SharedContextRecord.Difference getRecordDifference(int index){
            return recordDiffMap == null ? null : (SharedContextRecord.Difference)recordDiffMap.get(new Integer(index));
        }
        
        /**
         * �w�肳�ꂽ���R�[�h���X�g�ɍX�V�𔽉f����B<p>
         *
         * @param list �X�V�Ώۂ̃��R�[�h���X�g
         * @return �S�čX�V���ꂽ�ꍇ�A1�B�X�V���ꂽ���̂ƁA�X�V����K�v���Ȃ��������̂����݂���ꍇ�A0�B����������ꂸ�ɁA�X�V�ł��Ȃ����̂����݂���ꍇ�A-1�B
         * @exception SharedContextUpdateException �X�V�̔��f�Ɏ��s�����ꍇ
         */
        public int updateRecordList(SharedContextRecordList list) throws SharedContextUpdateException{
            int result = 1;
            if(transactionList != null && transactionList.size() != 0){
                if(SharedContextRecord.compareToUpdateVersion(list.getUpdateVersion(), updateVersion) >= 0){
                    return 0;
                }else if(list.getUpdateVersion() + 1 != updateVersion){
                    return -1;
                }
                for(int i = 0, imax = transactionList.size(); i < imax; i++){
                    final int ret = ((Transaction)transactionList.get(i)).execute(list);
                    switch(ret){
                    case 0:
                        if(result != -1){
                            result = 0;
                        }
                        break;
                    case -1:
                        result = -1;
                        break;
                    case 1:
                        break;
                    }
                }
            }
            list.setUpdateVersion(updateVersion);
            return result;
        }
        
        /**
         * �X�V���ꂽ���𔻒肷��B<p>
         *
         * @return �X�V���ꂽ�ꍇ�́Atrue
         */
        public boolean isUpdate(){
            return transactionList != null && transactionList.size() != 0;
        }
        
        /**
         * �X�V�̃g�����U�N�V�������X�g���擾����B<p>
         *
         * @return �X�V�g�����U�N�V�����̃��X�g
         */
        public List getTransactionList(){
            return transactionList;
        }
        
        /**
         * �w�肳�ꂽ��ʂ̃g�����U�N�V�������X�g���擾����B<p>
         * 
         * @param type �g�����U�N�V�������
         * @return �X�V�g�����U�N�V�����̃��X�g�B�w�肳�ꂽ��ʂ̃g�����U�N�V���������݂��Ȃ��ꍇ�́Anull
         */
        protected List getTransactionList(int type){
            if(transactionList == null || transactionList.size() == 0){
                return null;
            }
            List result = null;
            for(int i = 0, imax = transactionList.size(); i < imax; i++){
                Transaction transaction = (Transaction)transactionList.get(i);
                if(transaction.getType() == type){
                    if(result == null){
                        result = new ArrayList();
                    }
                    result.add(transaction);
                }
            }
            return result;
        }
        
        /**
         * �ǉ��g�����U�N�V�����̃��X�g���擾����B<p>
         *
         * @return �ǉ��g�����U�N�V�����̃��X�g�B���݂��Ȃ��ꍇ�́Anull
         */
        public List getAddTransactionList(){
            return getTransactionList(Transaction.ADD);
        }
        
        /**
         * �����ւ��g�����U�N�V�����̃��X�g���擾����B<p>
         *
         * @return �����ւ��g�����U�N�V�����̃��X�g�B���݂��Ȃ��ꍇ�́Anull
         */
        public List getSetTransactionList(){
            return getTransactionList(Transaction.SET);
        }
        
        /**
         * �폜�g�����U�N�V�����̃��X�g���擾����B<p>
         *
         * @return �폜�g�����U�N�V�����̃��X�g�B���݂��Ȃ��ꍇ�́Anull
         */
        public List getRemoveTransactionList(){
            return getTransactionList(Transaction.REMOVE);
        }
        
        /**
         * �C���f�b�N�X�w��폜�g�����U�N�V�����̃��X�g���擾����B<p>
         *
         * @return �C���f�b�N�X�w��폜�g�����U�N�V�����̃��X�g�B���݂��Ȃ��ꍇ�́Anull
         */
        public List getRemoveIndexTransactionList(){
            return getTransactionList(Transaction.REMOVEINDEX);
        }
        
        /**
         * �S�ǉ��g�����U�N�V�����̃��X�g���擾����B<p>
         *
         * @return �S�ǉ��g�����U�N�V�����̃��X�g�B���݂��Ȃ��ꍇ�́Anull
         */
        public List getAddAllTransactionList(){
            return getTransactionList(Transaction.ADDALL);
        }
        
        /**
         * �S�폜�g�����U�N�V�����̃��X�g���擾����B<p>
         *
         * @return �S�폜�g�����U�N�V�����̃��X�g�B���݂��Ȃ��ꍇ�́Anull
         */
        public List getRemoveAllTransactionList(){
            return getTransactionList(Transaction.REMOVEALL);
        }
        
        /**
         * �w��v�f�W���݂̂��c���g�����U�N�V�����̃��X�g���擾����B<p>
         *
         * @return �w��v�f�W���݂̂��c���g�����U�N�V�����̃��X�g�B���݂��Ȃ��ꍇ�́Anull
         */
        public List getRetainAllTransactionList(){
            return getTransactionList(Transaction.RETAINALL);
        }
        
        /**
         * ���R�[�h�X�V�g�����U�N�V�����̃��X�g���擾����B<p>
         *
         * @return ���R�[�h�X�V�g�����U�N�V�����̃��X�g�B���݂��Ȃ��ꍇ�́Anull
         */
        public List getUpdateTransactionList(){
            return getTransactionList(Transaction.UPDATE);
        }
        
        /**
         * �S���폜�g�����U�N�V���������݂��邩���肷��B<p>
         *
         * @return �S���폜�g�����U�N�V���������݂���ꍇ�́Atrue
         */
        public boolean isClear(){
            List trans = getTransactionList(Transaction.CLEAR);
            return trans == null || trans.size() == 0 ? false : true;
        }
        
        public void writeExternal(ObjectOutput out) throws IOException{
            if(transactionList == null || transactionList.size() == 0){
                SharedContextRecordList.writeInt(out, 0);
            }else{
                SharedContextRecordList.writeInt(out, transactionList.size());
                for(int i = 0, imax = transactionList.size(); i < imax; i++){
                    Transaction tran = (Transaction)transactionList.get(i);
                    out.write(tran.getType());
                    tran.writeExternal(out);
                }
            }
            SharedContextRecordList.writeInt(out, updateVersion);
        }
        
        public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException{
            int size = SharedContextRecordList.readInt(in);
            if(size > 0){
                transactionList = new ArrayList(size);
                for(int i = 0; i < size; i++){
                    final int type = in.read();
                    Transaction tran = null;
                    switch(type){
                    case Transaction.ADD:
                        tran = new AddTransaction();
                        break;
                    case Transaction.SET:
                        tran = new SetTransaction();
                        break;
                    case Transaction.REMOVE:
                        tran = new RemoveTransaction();
                        break;
                    case Transaction.CLEAR:
                        tran = new ClearTransaction();
                        break;
                    case Transaction.ADDALL:
                        tran = new AddAllTransaction();
                        break;
                    case Transaction.REMOVEALL:
                        tran = new RemoveAllTransaction();
                        break;
                    case Transaction.RETAINALL:
                        tran = new RetainAllTransaction();
                        break;
                    case Transaction.UPDATE:
                        tran = new UpdateTransaction();
                        break;
                    case Transaction.REMOVEINDEX:
                        tran = new RemoveIndexTransaction();
                        break;
                    default:
                        throw new IOException("Unknown transaction. type=" + type);
                    }
                    tran.readExternal(in);
                    transactionList.add(tran);
                }
            }
            updateVersion = SharedContextRecordList.readInt(in);
        }
        
        /**
         * �g�����U�N�V�����B<p>
         *
         * @author M.Takata
         */
        public static interface Transaction extends Externalizable{
            
            /**
             * �X�V��ʁF�ǉ��B<p>
             */
            public static final byte ADD         = 1;
            
            /**
             * �X�V��ʁF�����ւ��B<p>
             */
            public static final byte SET         = 2;
            
            /**
             * �X�V��ʁF�폜�B<p>
             */
            public static final byte REMOVE      = 3;
            
            /**
             * �X�V��ʁF�S�폜�B<p>
             */
            public static final byte CLEAR       = 4;
            
            /**
             * �X�V��ʁF�W���ǉ��B<p>
             */
            public static final byte ADDALL      = 5;
            
            /**
             * �X�V��ʁF�W���폜�B<p>
             */
            public static final byte REMOVEALL   = 6;
            
            /**
             * �X�V��ʁF�W���c���B<p>
             */
            public static final byte RETAINALL   = 7;
            
            /**
             * �X�V��ʁF���R�[�h�X�V�B<p>
             */
            public static final byte UPDATE      = 8;
            
            /**
             * �X�V��ʁF�C���f�b�N�X�w��폜�B<p>
             */
            public static final byte REMOVEINDEX = 9;
            
            /**
             * �g�����U�N�V������ʂ��擾����B<p>
             *
             * @return �g�����U�N�V�������
             * @see #ADD
             * @see #SET
             * @see #REMOVE
             * @see #CLEAR
             * @see #ADDALL
             * @see #REMOVEALL
             * @see #RETAINALL
             * @see #UPDATE
             * @see #REMOVEINDEX
             */
            public byte getType();
            
            /**
             * ���R�[�h���X�g�Ƀg�����U�N�V�����𔽉f����B<p>
             *
             * @param list ���R�[�h���X�g
             * @return �X�V���ꂽ�ꍇ�A1�B�X�V����K�v���Ȃ������ꍇ�A0�B����������ꂸ�ɁA�X�V�ł��Ȃ��ꍇ�A-1�B
             * @exception SharedContextUpdateException �X�V�̔��f�Ɏ��s�����ꍇ
             */
            public int execute(SharedContextRecordList list) throws SharedContextUpdateException;
            
            public int getIndexDifference();
            public void updateIndexDifference(int diff);
        }
        
        /**
         * �W���g�����U�N�V�����B<p>
         *
         * @author M.Takata
         */
        public static interface CollectionTransaction extends Transaction{
        }
        
        /**
         * �ǉ��g�����U�N�V�����B<p>
         *
         * @author M.Takata
         */
        public static class AddTransaction implements Transaction{
            
            private static final long serialVersionUID = 8128732623093350969l;
            
            private int index = -1;
            private Record record;
            public AddTransaction(){
            }
            public AddTransaction(Record record){
                this.record = record;
            }
            public AddTransaction(int index, Record record){
                this.index = index;
                this.record = record;
            }
            public byte getType(){
                return ADD;
            }
            public int execute(SharedContextRecordList list) throws SharedContextUpdateException{
                if(index == -1){
                    list.add(record);
                }else{
                    list.add(index, record);
                }
                return 1;
            }
            
            /**
             * �ǉ����郌�R�[�h�̃C���f�b�N�X���擾����B<p>
             *
             * @return ���R�[�h�̃C���f�b�N�X�B�����ɒǉ�����ꍇ�́A-1
             */
            public int getIndex(){
                return index;
            }
            
            /**
             * �ǉ����郌�R�[�h���擾����B<p>
             *
             * @return ���R�[�h
             */
            public Record getRecord(){
                return record;
            }
            public int getIndexDifference(){
                return index == -1 ? 0 : index + 1;
            }
            public void updateIndexDifference(int diff){
                if(diff != 0){
                    if(diff > 0){
                        if(index != -1 && index >= (diff - 1)){
                            index++;
                        }
                    }else if(diff < 0){
                        if(index != -1 && index >= (-diff - 1)){
                            index--;
                        }
                    }
                }
            }
            
            public void writeExternal(ObjectOutput out) throws IOException{
                SharedContextRecordList.writeInt(out, index);
                out.writeObject(record);
            }
            public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException{
                index = SharedContextRecordList.readInt(in);
                record = (Record)in.readObject();
            }
        }
        
        /**
         * �����ւ��g�����U�N�V�����B<p>
         *
         * @author M.Takata
         */
        public static class SetTransaction implements Transaction{
            
            private static final long serialVersionUID = -5758065331528848206l;
            
            private int index;
            private Record record;
            public SetTransaction(){
            }
            public SetTransaction(int index, Record record){
                this.index = index;
                this.record = record;
            }
            public byte getType(){
                return SET;
            }
            public int execute(SharedContextRecordList list) throws SharedContextUpdateException{
                list.set(index, record);
                return 1;
            }
            
            /**
             * �����ւ��郌�R�[�h�̃C���f�b�N�X���擾����B<p>
             *
             * @return ���R�[�h�̃C���f�b�N�X
             */
            public int getIndex(){
                return index;
            }
            
            /**
             * �����ւ��郌�R�[�h���擾����B<p>
             *
             * @return ���R�[�h
             */
            public Record getRecord(){
                return record;
            }
            public int getIndexDifference(){
                return 0;
            }
            public void updateIndexDifference(int diff){
                if(diff != 0){
                    if(diff > 0){
                        if(index != -1 && index >= (diff - 1)){
                            index++;
                        }
                    }else if(diff < 0){
                        if(index != -1 && index >= (-diff - 1)){
                            index--;
                        }
                    }
                }
            }
            
            public void writeExternal(ObjectOutput out) throws IOException{
                SharedContextRecordList.writeInt(out, index);
                out.writeObject(record);
            }
            public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException{
                index = SharedContextRecordList.readInt(in);
                record = (Record)in.readObject();
            }
        }
        
        /**
         * �폜�g�����U�N�V�����B<p>
         *
         * @author M.Takata
         */
        public static class RemoveTransaction implements Transaction{
            
            private static final long serialVersionUID = 1033170205471015665l;
            
            private Object obj;
            private transient int index;
            public RemoveTransaction(){
            }
            public RemoveTransaction(Object obj){
                this.obj = obj;
            }
            public byte getType(){
                return REMOVE;
            }
            public int execute(SharedContextRecordList list) throws SharedContextUpdateException{
                index = list.indexOf(obj);
                list.remove(obj);
                return 1;
            }
            
            /**
             * �폜���郌�R�[�h���擾����B<p>
             *
             * @return ���R�[�h
             */
            public Object getObject(){
                return obj;
            }
            public int getIndexDifference(){
                return -(index + 1);
            }
            public void updateIndexDifference(int diff){
                if(diff != 0){
                    if(diff > 0){
                        if(index != -1 && index >= (diff - 1)){
                            index++;
                        }
                    }else if(diff < 0){
                        if(index != -1 && index >= (-diff - 1)){
                            index--;
                        }
                    }
                }
            }
            
            public void writeExternal(ObjectOutput out) throws IOException{
                out.writeObject(obj);
            }
            public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException{
                obj = in.readObject();
            }
        }
        
        /**
         * �C���f�b�N�X�w��폜�g�����U�N�V�����B<p>
         *
         * @author M.Takata
         */
        public static class RemoveIndexTransaction implements Transaction{
            
            private static final long serialVersionUID = 2195335463770024605l;
            
            private int index;
            public RemoveIndexTransaction(){
            }
            public RemoveIndexTransaction(int index){
                this.index = index;
            }
            public byte getType(){
                return REMOVEINDEX;
            }
            public int execute(SharedContextRecordList list) throws SharedContextUpdateException{
                list.remove(index);
                return 1;
            }
            
            /**
             * �폜���R�[�h�̃C���f�b�N�X���擾����B<p>
             *
             * @return ���R�[�h�̃C���f�b�N�X
             */
            public int getIndex(){
                return index;
            }
            public int getIndexDifference(){
                return -(index + 1);
            }
            public void updateIndexDifference(int diff){
                if(diff != 0){
                    if(diff > 0){
                        if(index != -1 && index >= (diff - 1)){
                            index++;
                        }
                    }else if(diff < 0){
                        if(index != -1 && index >= (-diff - 1)){
                            index--;
                        }
                    }
                }
            }
            
            public void writeExternal(ObjectOutput out) throws IOException{
                SharedContextRecordList.writeInt(out, index);
            }
            public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException{
                index = SharedContextRecordList.readInt(in);
            }
        }
        
        /**
         * �S�폜�g�����U�N�V�����B<p>
         *
         * @author M.Takata
         */
        public static class ClearTransaction implements CollectionTransaction{
            
            private static final long serialVersionUID = 3750183579398518562l;
            
            public ClearTransaction(){
            }
            public byte getType(){
                return CLEAR;
            }
            public int execute(SharedContextRecordList list) throws SharedContextUpdateException{
                list.clear();
                return 1;
            }
            public int getIndexDifference(){
                return 0;
            }
            public void updateIndexDifference(int diff){
            }
            public void writeExternal(ObjectOutput out) throws IOException{
            }
            public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException{
            }
        }
        
        /**
         * �W���ǉ��g�����U�N�V�����B<p>
         *
         * @author M.Takata
         */
        public static class AddAllTransaction implements CollectionTransaction{
            
            private static final long serialVersionUID = 3963122806966988635l;
            
            private int index = -1;
            private Collection c;
            public AddAllTransaction(){
            }
            public AddAllTransaction(Collection c){
                this.c = new ArrayList(c);
            }
            public AddAllTransaction(int index, Collection c){
                this.index = index;
                this.c = new ArrayList(c);
            }
            public byte getType(){
                return ADDALL;
            }
            public int execute(SharedContextRecordList list) throws SharedContextUpdateException{
                if(index == -1){
                    list.addAll(c);
                }else{
                    list.addAll(index, c);
                }
                return 1;
            }
            
            /**
             * �ǉ����郌�R�[�h�W���̃C���f�b�N�X���擾����B<p>
             *
             * @return ���R�[�h�W���̃C���f�b�N�X�B�����ɒǉ�����ꍇ�́A-1
             */
            public int getIndex(){
                return index;
            }
            
            /**
             * �ǉ����郌�R�[�h�W�����擾����B<p>
             *
             * @return ���R�[�h�W��
             */
            public Collection getRecords(){
                return c;
            }
            
            public int getIndexDifference(){
                return 0;
            }
            public void updateIndexDifference(int diff){
            }
            
            public void writeExternal(ObjectOutput out) throws IOException{
                SharedContextRecordList.writeInt(out, index);
                out.writeObject(c);
            }
            public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException{
                index = SharedContextRecordList.readInt(in);
                c = (Collection)in.readObject();
            }
        }
        
        /**
         * �W���폜�g�����U�N�V�����B<p>
         *
         * @author M.Takata
         */
        public static class RemoveAllTransaction implements CollectionTransaction{
            
            private static final long serialVersionUID = 6369556618192183260l;
            
            private Collection c;
            public RemoveAllTransaction(){
            }
            public RemoveAllTransaction(Collection c){
                this.c = new ArrayList(c);
            }
            public byte getType(){
                return REMOVEALL;
            }
            public int execute(SharedContextRecordList list) throws SharedContextUpdateException{
                list.removeAll(c);
                return 1;
            }
            
            /**
             * �폜���郌�R�[�h�W�����擾����B<p>
             *
             * @return ���R�[�h�W��
             */
            public Collection getRecords(){
                return c;
            }
            public int getIndexDifference(){
                return 0;
            }
            public void updateIndexDifference(int diff){
            }
            
            public void writeExternal(ObjectOutput out) throws IOException{
                out.writeObject(c);
            }
            public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException{
                c = (Collection)in.readObject();
            }
        }
        
        /**
         * �W���c���g�����U�N�V�����B<p>
         *
         * @author M.Takata
         */
        public static class RetainAllTransaction implements CollectionTransaction{
            
            private static final long serialVersionUID = -5032406110083319746l;
            
            private Collection c;
            public RetainAllTransaction(){
            }
            public RetainAllTransaction(Collection c){
                this.c = new ArrayList(c);
            }
            public byte getType(){
                return RETAINALL;
            }
            public int execute(SharedContextRecordList list) throws SharedContextUpdateException{
                list.retainAll(c);
                return 1;
            }
            
            /**
             * �c�����R�[�h�W�����擾����B<p>
             *
             * @return ���R�[�h�W��
             */
            public Collection getRecords(){
                return c;
            }
            public int getIndexDifference(){
                return 0;
            }
            public void updateIndexDifference(int diff){
            }
            
            public void writeExternal(ObjectOutput out) throws IOException{
                out.writeObject(c);
            }
            public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException{
                c = (Collection)in.readObject();
            }
        }
        
        /**
         * ���R�[�h�X�V�g�����U�N�V�����B<p>
         *
         * @author M.Takata
         */
        public static class UpdateTransaction implements Transaction{
            
            private static final long serialVersionUID = 490945073091204092l;
            
            private int index;
            private SharedContextRecord.Difference diff;
            public UpdateTransaction(){
            }
            public UpdateTransaction(int index, SharedContextRecord.Difference diff){
                this.index = index;
                this.diff = diff;
            }
            public byte getType(){
                return UPDATE;
            }
            
            /**
             * �X�V���ꂽ���𔻒肷��B<p>
             *
             * @return �X�V���ꂽ�ꍇ�́Atrue
             */
            public boolean isUpdate(){
                return diff.isUpdate();
            }
            
            public int execute(SharedContextRecordList list) throws SharedContextUpdateException{
                SharedContextRecord record = null;
                try{
                    record = (SharedContextRecord)list.getRecord(index);
                }catch(IndexOutOfBoundsException e){
                    throw new SharedContextUpdateException(e);
                }
                return record.update(diff);
            }
            
            /**
             * �X�V���郌�R�[�h�̃C���f�b�N�X���擾����B<p>
             *
             * @return ���R�[�h�̃C���f�b�N�X
             */
            public int getIndex(){
                return index;
            }
            
            /**
             * �X�V���郌�R�[�h�̍��������擾����B<p>
             *
             * @return ���R�[�h�̍������
             */
            public SharedContextRecord.Difference getRecordDifference(){
                return diff;
            }
            
            public int getIndexDifference(){
                return 0;
            }
            public void updateIndexDifference(int diff){
                if(diff != 0){
                    if(diff > 0){
                        if(index != -1 && index >= (diff - 1)){
                            index++;
                        }
                    }else if(diff < 0){
                        if(index != -1 && index >= (-diff - 1)){
                            index--;
                        }
                    }
                }
            }
            
            public void writeExternal(ObjectOutput out) throws IOException{
                SharedContextRecordList.writeInt(out, index);
                out.writeObject(diff);
            }
            public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException{
                index = SharedContextRecordList.readInt(in);
                diff = (SharedContextRecord.Difference)in.readObject();
            }
        }
    }
}