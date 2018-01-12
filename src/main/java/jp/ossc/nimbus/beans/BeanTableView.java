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
package jp.ossc.nimbus.beans;

import java.util.Set;
import java.util.HashSet;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.lang.reflect.InvocationTargetException;
import java.io.Externalizable;
import java.io.ObjectOutput;
import java.io.ObjectInput;
import java.io.IOException;

import jp.ossc.nimbus.beans.dataset.Record;
import jp.ossc.nimbus.beans.dataset.RecordList;

/**
 * {@link BeanTable Bean�e�[�u��}�̌����r���[�B<p>
 *
 * @author M.Takata
 * @see BeanTable
 */
public class BeanTableView implements Cloneable{
    
    protected static final int OPERATOR_AND   = 1;
    protected static final int OPERATOR_OR    = 2;
    protected static final int OPERATOR_NAND  = 3;
    protected static final int OPERATOR_NOR   = 4;
    protected static final int OPERATOR_XOR   = 5;
    protected static final int OPERATOR_XNOR  = 6;
    protected static final int OPERATOR_IMP   = 7;
    protected static final int OPERATOR_NIMP  = 8;
    protected static final int OPERATOR_CIMP  = 9;
    protected static final int OPERATOR_CNIMP = 10;
    
    protected BeanTableIndexManager indexManager;
    protected Set resultSet;
    protected int operator = OPERATOR_AND;
    
    /**
     * �w�肳�ꂽ�C���f�b�N�X�Ǘ����g���Č������s���r���[�𐶐�����B<p>
     *
     * @param manager �C���f�b�N�X�Ǘ�
     */
    public BeanTableView(BeanTableIndexManager manager){
        indexManager = manager;
    }
    
    /**
     * �������ʂ�Bean�W�����擾����B<p>
     *
     * @return �������ʂ�Bean�W��
     */
    public Set getResultSet(){
        return resultSet == null ? indexManager.elements() : resultSet;
    }
    
    /**
     * �������ʂ�Bean���X�g���擾����B<p>
     *
     * @return �������ʂ�Bean���X�g
     */
    public List getResultList(){
        return new ArrayList(resultSet == null ? indexManager.elements() : resultSet);
    }
    
    /**
     * �������ʂ�Bean�W�����w�肵���v���p�e�B���ŏ����\�[�g���Ď擾����B<p>
     *
     * @param propNames �\�[�g����Bean�̃v���p�e�B���z��
     * @return �������ʂ̃\�[�g�ς�Bean���X�g
     * @exception IndexPropertyAccessException �w�肳�ꂽ�v���p�e�B�̎擾�ŗ�O�����������ꍇ
     */
    public List getResultList(String[] propNames) throws IndexPropertyAccessException{
        return getResultList(propNames, null);
    }
    
    /**
     * �������ʂ�Bean�W�����w�肵��Bean�̃v���p�e�B���Ŏw�肳�ꂽ�\�[�g�����Ƀ\�[�g���Ď擾����B<p>
     *
     * @param propNames �\�[�g����Bean�̃v���p�e�B���z��
     * @param isAsc propNames�Ŏw�肵���v���p�e�B���̃\�[�g�����������z��Btrue���w�肷��Ə���
     * @return �������ʂ̃\�[�g�ς�Bean���X�g
     * @exception IndexPropertyAccessException �w�肳�ꂽ�v���p�e�B�̎擾�ŗ�O�����������ꍇ
     */
    public List getResultList(String[] propNames, boolean[] isAsc) throws IndexPropertyAccessException{
        List result = new ArrayList();
        result.addAll(resultSet == null ? indexManager.elements() : resultSet);
        if(result.size() < 2 || propNames == null || propNames.length == 0){
            return result;
        }
        if(Record.class.isAssignableFrom(indexManager.getElementClass())){
            RecordList.sort(result, propNames, isAsc);
        }else{
            Collections.sort(result, new BeanComparator(indexManager.getElementClass(), propNames, isAsc));
        }
        return result;
    }
    
    /**
     * �������ʂ�Bean�W������w�肵��Bean�̃v���p�e�B���d���폜�����l�̏W�����擾����B<p>
     *
     * @param propName �v���p�e�B��
     * @return �w�肵��Bean�̃v���p�e�B���d���폜�����l�̏W��
     * @exception IndexPropertyAccessException �w�肳�ꂽ�v���p�e�B�̎擾�ŗ�O�����������ꍇ
     */
    public Set getResultDistinctValueSet(String propName) throws IndexPropertyAccessException{
        return (Set)getResultDistinctValueCollection(propName, false, false);
    }
    
    /**
     * �������ʂ�Bean�W������w�肵��Bean�̃v���p�e�B���d���폜�����l�̃��X�g���擾����B<p>
     *
     * @param propName �v���p�e�B��
     * @param isAsc propName�Ŏw�肵���v���p�e�B���̃\�[�g�����������t���O�Btrue���w�肷��Ə���
     * @return �w�肵��Bean�̃v���p�e�B���d���폜�����l�̃��X�g
     * @exception IndexPropertyAccessException �w�肳�ꂽ�v���p�e�B�̎擾�ŗ�O�����������ꍇ
     */
    public List getResultDistinctValueList(String propName, boolean isAsc) throws IndexPropertyAccessException{
        return (List)getResultDistinctValueCollection(propName, true, isAsc);
    }
    
    protected Collection getResultDistinctValueCollection(String propName, boolean isSort, boolean isAsc) throws IndexPropertyAccessException{
        SimpleProperty prop = new SimpleProperty(propName);
        Set distinctSet = new HashSet();
        try{
            Set elements = resultSet == null ? indexManager.elements() : resultSet;
            Iterator itr = elements.iterator();
            while(itr.hasNext()){
                Object element = itr.next();
                distinctSet.add(prop.getProperty(element));
            }
        }catch(NoSuchPropertyException e){
            throw new IndexPropertyAccessException(
                indexManager.getElementClass(),
                propName,
                e
            );
        }catch(InvocationTargetException e){
            throw new IndexPropertyAccessException(
                indexManager.getElementClass(),
                propName,
                ((InvocationTargetException)e).getTargetException()
            );
        }
        if(isSort){
            List result = new ArrayList();
            result.addAll(distinctSet);
            if(result.size() < 2){
                return result;
            }
            Collections.sort(result, new BeanComparator(isAsc));
            return result;
        }else{
            return distinctSet;
        }
    }
    
    /**
     * �������ʂ�Bean�W������w�肵��Bean�̃v���p�e�B�̍ő�l���擾����B<p>
     *
     * @param propName �v���p�e�B��
     * @return �w�肵��Bean�̃v���p�e�B�̍ő�l
     * @exception IndexPropertyAccessException �w�肳�ꂽ�v���p�e�B�̎擾�ŗ�O�����������ꍇ
     */
    public Object getResultMaxValue(String propName) throws IndexPropertyAccessException{
        List sortedList = getResultList(new String[]{propName}, new boolean[]{false});
        if(sortedList.size() == 0){
            return null;
        }else{
            try{
                return new SimpleProperty(propName).getProperty(sortedList.get(0));
            }catch(NoSuchPropertyException e){
                throw new IndexPropertyAccessException(
                    indexManager.getElementClass(),
                    propName,
                    e
                );
            }catch(InvocationTargetException e){
                throw new IndexPropertyAccessException(
                    indexManager.getElementClass(),
                    propName,
                    ((InvocationTargetException)e).getTargetException()
                );
            }
        }
    }
    
    /**
     * �������ʂ�Bean�W������w�肵��Bean�̃v���p�e�B�̍ŏ��l���擾����B<p>
     *
     * @param propName �v���p�e�B��
     * @return �w�肵��Bean�̃v���p�e�B�̍ŏ��l
     * @exception IndexPropertyAccessException �w�肳�ꂽ�v���p�e�B�̎擾�ŗ�O�����������ꍇ
     */
    public Object getResultMinValue(String propName) throws IndexPropertyAccessException{
        List sortedList = getResultList(new String[]{propName}, new boolean[]{true});
        if(sortedList.size() == 0){
            return null;
        }else{
            try{
                return new SimpleProperty(propName).getProperty(sortedList.get(0));
            }catch(NoSuchPropertyException e){
                throw new IndexPropertyAccessException(
                    indexManager.getElementClass(),
                    propName,
                    e
                );
            }catch(InvocationTargetException e){
                throw new IndexPropertyAccessException(
                    indexManager.getElementClass(),
                    propName,
                    ((InvocationTargetException)e).getTargetException()
                );
            }
        }
    }
    
    protected static Comparator sort(Class elementClass, List list, String[] propNames, boolean[] isAsc) throws IndexPropertyAccessException{
        if(list.size() <= 1){
            return Record.class.isAssignableFrom(elementClass)
                ? (Comparator)new RecordList.RecordComparator(propNames, isAsc)
                    : (Comparator)new BeanComparator(elementClass, propNames, isAsc);
        }
        Comparator c = Record.class.isAssignableFrom(elementClass)
            ? (Comparator)new RecordList.RecordComparator(((Record)list.get(0)).getRecordSchema(), propNames, isAsc)
                : (Comparator)new BeanComparator(elementClass, propNames, isAsc);
        Collections.sort(list, c);
        return c;
    }
    
    /**
     * �_�����Z��Ԃ�_���ρiAND�j�ɂ���B<p>
     * �f�t�H���g�̘_�����Z��Ԃł��B<br>
     *
     * @return ���̃r���[
     */
    public BeanTableView and(){
        operator = OPERATOR_AND;
        return this;
    }
    
    /**
     * �_�����Z��Ԃ�_���a�iOR�j�ɂ���B<p>
     *
     * @return ���̃r���[
     */
    public BeanTableView or(){
        operator = OPERATOR_OR;
        return this;
    }
    
    /**
     * �_�����Z��Ԃ�ے�_���ρiNAND�j�ɂ���B<p>
     *
     * @return ���̃r���[
     */
    public BeanTableView nand(){
        operator = OPERATOR_NAND;
        return this;
    }
    
    /**
     * �_�����Z��Ԃ�ے�_���a�iNOR�j�ɂ���B<p>
     *
     * @return ���̃r���[
     */
    public BeanTableView nor(){
        operator = OPERATOR_NOR;
        return this;
    }
    
    /**
     * �_�����Z��Ԃ�r���I�_���a�iXOR�j�ɂ���B<p>
     *
     * @return ���̃r���[
     */
    public BeanTableView xor(){
        operator = OPERATOR_XOR;
        return this;
    }
    
    /**
     * �_�����Z��Ԃ�r���I�ے�_���a�iXNOR�j�ɂ���B<p>
     *
     * @return ���̃r���[
     */
    public BeanTableView xnor(){
        operator = OPERATOR_XNOR;
        return this;
    }
    
    /**
     * �_�����Z��Ԃ�_����܁iIMP�j�ɂ���B<p>
     *
     * @return ���̃r���[
     */
    public BeanTableView imp(){
        operator = OPERATOR_IMP;
        return this;
    }
    
    /**
     * �_�����Z��Ԃ�ے�_����܁iNIMP�j�ɂ���B<p>
     *
     * @return ���̃r���[
     */
    public BeanTableView nimp(){
        operator = OPERATOR_NIMP;
        return this;
    }
    
    /**
     * �_�����Z��Ԃ��t�_����܁iCIMP�j�ɂ���B<p>
     *
     * @return ���̃r���[
     */
    public BeanTableView cimp(){
        operator = OPERATOR_CIMP;
        return this;
    }
    
    /**
     * �_�����Z��Ԃ��t�ے�_����܁iCNIMP�j�ɂ���B<p>
     *
     * @return ���̃r���[
     */
    public BeanTableView cnimp(){
        operator = OPERATOR_CNIMP;
        return this;
    }
    
    protected void operate(Set elements){
        if(elements == null){
            elements = new HashSet(0);
        }
        switch(operator){
        case OPERATOR_OR:
            resultSet.addAll(elements);
            break;
        case OPERATOR_NAND:
            resultSet.retainAll(elements);
            Set all = indexManager.elements();
            all.removeAll(resultSet);
            resultSet = all;
            break;
        case OPERATOR_NOR:
            resultSet.addAll(elements);
            all = indexManager.elements();
            all.removeAll(resultSet);
            resultSet = all;
            break;
        case OPERATOR_XOR:
            Set tmpSet = new HashSet(resultSet);
            tmpSet.retainAll(elements);
            resultSet.addAll(elements);
            resultSet.removeAll(tmpSet);
            break;
        case OPERATOR_XNOR:
            tmpSet = new HashSet(resultSet);
            tmpSet.retainAll(elements);
            resultSet.addAll(elements);
            resultSet.removeAll(tmpSet);
            tmpSet = indexManager.elements();
            tmpSet.removeAll(resultSet);
            resultSet = tmpSet;
            break;
        case OPERATOR_IMP:
            all = indexManager.elements();
            all.removeAll(resultSet);
            all.addAll(elements);
            resultSet = all;
            break;
        case OPERATOR_NIMP:
            resultSet.removeAll(elements);
            break;
        case OPERATOR_CIMP:
            all = indexManager.elements();
            all.removeAll(elements);
            all.addAll(resultSet);
            resultSet = all;
            break;
        case OPERATOR_CNIMP:
            Set targetSet = new HashSet(elements);
            targetSet.removeAll(resultSet);
            resultSet = targetSet;
            break;
        case OPERATOR_AND:
        default:
            resultSet.retainAll(elements);
        }
    }
    
    /**
     * ���̌����r���[�̋t�W�����Ƃ�B<p>
     * 
     * @return �t�W�����Ƃ������ʂ̂��̃r���[
     */
    public BeanTableView not(){
        Set all = indexManager.elements();
        all.removeAll(resultSet);
        resultSet = all;
        return this;
    }
    
    /**
     * ���̌����r���[�Ɏw�肳�ꂽ�����r���[��AND�A������B<p>
     * 
     * @param view �����r���[
     * @return �A�����ꂽ���ʂ̂��̃r���[
     */
    public BeanTableView and(BeanTableView view){
        resultSet.retainAll(view.getResultSet());
        return this;
    }
    
    /**
     * ���̌����r���[�Ɏw�肳�ꂽ�����r���[��OR�A������B<p>
     * 
     * @param view �����r���[
     * @return �A�����ꂽ���ʂ̂��̃r���[
     */
    public BeanTableView or(BeanTableView view){
        resultSet.addAll(view.getResultSet());
        return this;
    }
    
    /**
     * ���̌����r���[�Ǝw�肳�ꂽ�����r���[�̔ے�_���ρiNAND�j���s���B<p>
     *
     * @param view �����r���[
     * @return ���ʂƂȂ邱�̃r���[
     */
    public BeanTableView nand(BeanTableView view){
        resultSet.retainAll(view.getResultSet());
        Set all = indexManager.elements();
        all.removeAll(resultSet);
        resultSet = all;
        return this;
    }
    
    /**
     * ���̌����r���[�Ǝw�肳�ꂽ�����r���[�̔ے�_���a�iNOR�j���s���B<p>
     *
     * @param view �����r���[
     * @return ���ʂƂȂ邱�̃r���[
     */
    public BeanTableView nor(BeanTableView view){
        resultSet.addAll(view.getResultSet());
        Set all = indexManager.elements();
        all.removeAll(resultSet);
        resultSet = all;
        return this;
    }
    
    /**
     * ���̌����r���[�Ǝw�肳�ꂽ�����r���[�̔r���I�_���a�iXOR�j���s���B<p>
     *
     * @param view �����r���[
     * @return ���ʂƂȂ邱�̃r���[
     */
    public BeanTableView xor(BeanTableView view){
        Set andSet = new HashSet(resultSet);
        andSet.retainAll(view.getResultSet());
        resultSet.addAll(view.getResultSet());
        resultSet.removeAll(andSet);
        return this;
    }
    
    /**
     * ���̌����r���[�Ǝw�肳�ꂽ�����r���[�̔ے�r���I�_���a�iXNOR�j���s���B<p>
     *
     * @param view �����r���[
     * @return ���ʂƂȂ邱�̃r���[
     */
    public BeanTableView xnor(BeanTableView view){
        Set tmpSet = new HashSet(resultSet);
        tmpSet.retainAll(view.getResultSet());
        resultSet.addAll(view.getResultSet());
        resultSet.removeAll(tmpSet);
        tmpSet = indexManager.elements();
        tmpSet.removeAll(resultSet);
        resultSet = tmpSet;
        return this;
    }
    
    /**
     * ���̌����r���[�Ǝw�肳�ꂽ�����r���[�̘_����܁iIMP�j���s���B<p>
     *
     * @param view �����r���[
     * @return ���ʂƂȂ邱�̃r���[
     */
    public BeanTableView imp(BeanTableView view){
        Set all = indexManager.elements();
        all.removeAll(resultSet);
        all.addAll(view.getResultSet());
        resultSet = all;
        return this;
    }
    
    /**
     * ���̌����r���[�Ǝw�肳�ꂽ�����r���[�̔ے�_����܁iNIMP�j���s���B<p>
     *
     * @param view �����r���[
     * @return ���ʂƂȂ邱�̃r���[
     */
    public BeanTableView nimp(BeanTableView view){
        resultSet.removeAll(view.getResultSet());
        return this;
    }
    
    /**
     * ���̌����r���[�Ǝw�肳�ꂽ�����r���[�̋t�_����܁iCIMP�j���s���B<p>
     *
     * @param view �����r���[
     * @return ���ʂƂȂ邱�̃r���[
     */
    public BeanTableView cimp(BeanTableView view){
        Set all = indexManager.elements();
        all.removeAll(view.getResultSet());
        all.addAll(resultSet);
        resultSet = all;
        return this;
    }
    
    /**
     * ���̌����r���[�Ǝw�肳�ꂽ�����r���[�̔ے�t�_����܁iCNIMP�j���s���B<p>
     *
     * @param view �����r���[
     * @return ���ʂƂȂ邱�̃r���[
     */
    public BeanTableView cnimp(BeanTableView view){
        Set targetSet = new HashSet(view.getResultSet());
        targetSet.removeAll(resultSet);
        resultSet = targetSet;
        return this;
    }
    
    /**
     * �w�肳�ꂽ�C���f�b�N�X�܂��̓v���p�e�B�W���ɑ΂���C���f�b�N�X�̃L�[�v�f�̏W������������B<p>
     * �L�[�����̈��ł���A�P���C���f�b�N�X�ƕ����C���f�b�N�X�ɑ΂��ėL���B<br>
     *
     * @param indexName �C���f�b�N�X��
     * @param propNames �v���p�e�B���z��
     * @return �������ʂ̂��̃r���[
     * @exception IndexNotFoundException �Y������C���f�b�N�X�����݂��Ȃ����A�܂��͕����C���f�b�N�X�̏ꍇ
     */
    public BeanTableView searchKeyElement(String indexName, String[] propNames) throws IndexNotFoundException{
        if(resultSet == null){
            resultSet = indexManager.searchKeyElement(resultSet, indexName, propNames);
            if(resultSet == null){
                resultSet = new HashSet();
            }
        }else{
            operate(indexManager.searchKeyElement(indexName, propNames));
        }
        return this;
    }
    
    /**
     * ����̃v���p�e�B��null�ƂȂ�Bean�W������������B<p>
     * ��v�����̈��ł���A�P���C���f�b�N�X�ɑ΂��Ă̂ݗL���B<br>
     *
     * @param indexName �C���f�b�N�X��
     * @param propName �v���p�e�B��
     * @return �������ʂ̂��̃r���[
     * @exception IndexNotFoundException �Y������C���f�b�N�X�����݂��Ȃ����A�܂��͕����C���f�b�N�X�̏ꍇ
     */
    public BeanTableView searchNull(String indexName, String propName) throws IndexNotFoundException{
        if(resultSet == null){
            resultSet = indexManager.searchNull(new HashSet(), indexName, propName);
            if(resultSet == null){
                resultSet = new HashSet();
            }
        }else{
            operate(indexManager.searchNull(indexName, propName));
        }
        return this;
    }
    
    /**
     * ����̃v���p�e�B����null�ƂȂ�Bean�W������������B<p>
     * ��v�����̈��ł���A�P���C���f�b�N�X�ɑ΂��Ă̂ݗL���B<br>
     *
     * @param indexName �C���f�b�N�X��
     * @param propName �v���p�e�B��
     * @return �������ʂ̂��̃r���[
     * @exception IndexNotFoundException �Y������C���f�b�N�X�����݂��Ȃ����A�܂��͕����C���f�b�N�X�̏ꍇ
     */
    public BeanTableView searchNotNull(String indexName, String propName) throws IndexNotFoundException{
        if(resultSet == null){
            resultSet = indexManager.searchNotNull(new HashSet(), indexName, propName);
            if(resultSet == null){
                resultSet = new HashSet();
            }
        }else{
            operate(indexManager.searchNotNull(indexName, propName));
        }
        return this;
    }
    
    /**
     * ����̃v���p�e�B���w�肵��Bean�̊Y������v���p�e�B�ƈ�v����Bean�W������������B<p>
     * ��v�����̈��ł���A�P���C���f�b�N�X�ƕ����C���f�b�N�X�ɑ΂��ėL���B<br>
     *
     * @param element �����L�[�ƂȂ�Bean
     * @param indexName �C���f�b�N�X��
     * @param propNames �v���p�e�B���z��
     * @return �������ʂ̂��̃r���[
     * @exception IndexNotFoundException �Y������C���f�b�N�X�����݂��Ȃ��ꍇ
     * @exception IndexPropertyAccessException �w�肳�ꂽ�v���p�e�B�̎擾�ŗ�O�����������ꍇ
     */
    public BeanTableView searchByElement(
        Object element,
        String indexName,
        String[] propNames
    ) throws IndexNotFoundException, IndexPropertyAccessException{
        if(resultSet == null){
            resultSet = indexManager.searchByElement(new HashSet(), element, indexName, propNames);
            if(resultSet == null){
                resultSet = new HashSet();
            }
        }else{
            operate(indexManager.searchByElement(element, indexName, propNames));
        }
        return this;
    }
    
    /**
     * ����̃v���p�e�B���w�肵��������Bean�̊Y������v���p�e�B�ƈ�v����Bean�W������������B<p>
     * ��v�����̈��ł���A�P���C���f�b�N�X�ƕ����C���f�b�N�X�ɑ΂��ėL���B<br>
     *
     * @param indexName �C���f�b�N�X��
     * @param propNames �v���p�e�B���z��
     * @param elements �����L�[�ƂȂ�Bean�z��
     * @return �������ʂ̂��̃r���[
     * @exception IndexNotFoundException �Y������C���f�b�N�X�����݂��Ȃ��ꍇ
     * @exception IndexPropertyAccessException �w�肳�ꂽ�v���p�e�B�̎擾�ŗ�O�����������ꍇ
     */
    public BeanTableView searchInElement(
        String indexName,
        String[] propNames,
        Object[] elements
    ) throws IndexNotFoundException, IndexPropertyAccessException{
        if(resultSet == null){
            resultSet = indexManager.searchInElement(resultSet, indexName, propNames, elements);
            if(resultSet == null){
                resultSet = new HashSet();
            }
        }else{
            operate(indexManager.searchInElement(indexName, propNames, elements));
        }
        return this;
    }
    
    /**
     * ����̃v���p�e�B���w�肵���l�ƈ�v����Bean�W������������B<p>
     * ��v�����̈��ł���A�P���C���f�b�N�X�ɑ΂��Ă̂ݗL���B<br>
     *
     * @param value �����L�[�ƂȂ�l
     * @param indexName �C���f�b�N�X��
     * @param propName �v���p�e�B��
     * @return �������ʂ̂��̃r���[
     * @exception IndexNotFoundException �Y������C���f�b�N�X�����݂��Ȃ����A�܂��͕����C���f�b�N�X�̏ꍇ
     */
    public BeanTableView searchBy(
        Object value,
        String indexName,
        String propName
    ){
        if(resultSet == null){
            resultSet = indexManager.searchBy(new HashSet(), value, indexName, propName);
            if(resultSet == null){
                resultSet = new HashSet();
            }
        }else{
            operate(indexManager.searchBy(value, indexName, propName));
        }
        return this;
    }
    
    /**
     * ����̃v���p�e�B���w�肵�������̒l�ƈ�v����Bean�W������������B<p>
     * ��v�����̈��ł���A�P���C���f�b�N�X�ɑ΂��Ă̂ݗL���B<br>
     *
     * @param indexName �C���f�b�N�X��
     * @param propName �v���p�e�B��
     * @param values �����L�[�ƂȂ�l�z��
     * @return �������ʂ̂��̃r���[
     * @exception IndexNotFoundException �Y������C���f�b�N�X�����݂��Ȃ����A�܂��͕����C���f�b�N�X�̏ꍇ
     */
    public BeanTableView searchIn(
        String indexName,
        String propName,
        Object[] values
    ){
        if(resultSet == null){
            resultSet = indexManager.searchIn(resultSet, indexName, propName, values);
            if(resultSet == null){
                resultSet = new HashSet();
            }
        }else{
            operate(indexManager.searchIn(indexName, propName, values));
        }
        return this;
    }
    
    /**
     * ����̃v���p�e�B���w�肵���l�ƈ�v����Bean�W������������B<p>
     * ��v�����̈��ł���A�P���C���f�b�N�X�ƕ����C���f�b�N�X�ɑ΂��ėL���B<br>
     *
     * @param keys �����L�[�ƂȂ�v���p�e�B���ƒl�̃}�b�s���O
     * @param indexName �C���f�b�N�X��
     * @return �������ʂ̂��̃r���[
     * @exception IndexNotFoundException �Y������C���f�b�N�X�����݂��Ȃ��ꍇ
     * @exception IllegalArgumentException �w�肳�ꂽ�C���f�b�N�X���w�肳�ꂽ�v���p�e�B�Ɋ֘A���Ȃ��ꍇ
     */
    public BeanTableView searchBy(
        Map keys,
        String indexName
    ) throws IndexNotFoundException, IllegalArgumentException{
        if(resultSet == null){
            resultSet = indexManager.searchBy(new HashSet(), keys, indexName);
            if(resultSet == null){
                resultSet = new HashSet();
            }
        }else{
            operate(indexManager.searchBy(keys, indexName));
        }
        return this;
    }
    
    /**
     * ����̃v���p�e�B���w�肵�������̒l�ƈ�v����Bean�W������������B<p>
     * ��v�����̈��ł���A�P���C���f�b�N�X�ƕ����C���f�b�N�X�ɑ΂��ėL���B<br>
     *
     * @param indexName �C���f�b�N�X��
     * @param keys �����L�[�ƂȂ�v���p�e�B���ƒl�̃}�b�s���O�̔z��
     * @return �������ʂ̂��̃r���[
     * @exception IndexNotFoundException �Y������C���f�b�N�X�����݂��Ȃ��ꍇ
     * @exception IllegalArgumentException �w�肳�ꂽ�C���f�b�N�X���w�肳�ꂽ�v���p�e�B�Ɋ֘A���Ȃ��ꍇ
     */
    public BeanTableView searchIn(
        String indexName,
        Map[] keys
    ) throws IndexNotFoundException, IllegalArgumentException{
        if(resultSet == null){
            resultSet = indexManager.searchIn(resultSet, indexName, keys);
            if(resultSet == null){
                resultSet = new HashSet();
            }
        }else{
            operate(indexManager.searchIn(indexName, keys));
        }
        return this;
    }
    
    protected BeanTableIndex createTmporaryIndex(
        String indexName,
        String[] propNames
    )throws IndexNotFoundException{
        BeanTableIndex index = null;
        if(indexName != null){
            index = indexManager.getIndex(indexName);
        }
        if(index == null && propNames != null && propNames.length != 0){
            index = indexManager.getIndexBy(propNames);
        }
        if(index == null){
            throw new IndexNotFoundException();
        }
        index = index.cloneEmpty(false);
        Iterator itr = resultSet.iterator();
        while(itr.hasNext()){
            Object element = itr.next();
            index.add(element);
        }
        return index;
    }
    
    /**
     * ����̃v���p�e�B���w�肵��Bean�̊Y������v���p�e�B���傫��Bean�W������������B<p>
     * �͈͌����̈��ł���A�P���C���f�b�N�X�ɑ΂��Ă̂ݗL���B<br>
     *
     * @param from 臒l������Bean
     * @param indexName �C���f�b�N�X��
     * @param propName �v���p�e�B��
     * @return �������ʂ̂��̃r���[
     * @exception IndexNotFoundException �Y������C���f�b�N�X�����݂��Ȃ����A�����C���f�b�N�X�̏ꍇ
     * @exception IndexPropertyAccessException �w�肳�ꂽ�v���p�e�B�̎擾�ŗ�O�����������ꍇ
     */
    public BeanTableView searchFromElement(
        Object from,
        String indexName,
        String propName
    ) throws IndexNotFoundException, IndexPropertyAccessException{
        if(resultSet == null){
            resultSet = indexManager.searchFromElement(resultSet, from, indexName, propName);
            if(resultSet == null){
                resultSet = new HashSet();
            }
        }else{
            BeanTableIndex index = createTmporaryIndex(indexName, new String[]{propName});
            operate(index.searchFromElement(from));
        }
        return this;
    }
    
    /**
     * ����̃v���p�e�B���w�肵��Bean�̊Y������v���p�e�B���傫��Bean�W������������B<p>
     * �͈͌����̈��ł���A�P���C���f�b�N�X�ɑ΂��Ă̂ݗL���B<br>
     *
     * @param from 臒l������Bean
     * @param inclusive �������ʂ�臒l���܂ނ��ǂ����B�܂ޏꍇ��true
     * @param indexName �C���f�b�N�X��
     * @param propName �v���p�e�B��
     * @return �������ʂ̂��̃r���[
     * @exception IndexNotFoundException �Y������C���f�b�N�X�����݂��Ȃ����A�����C���f�b�N�X�̏ꍇ
     * @exception IndexPropertyAccessException �w�肳�ꂽ�v���p�e�B�̎擾�ŗ�O�����������ꍇ
     */

    public BeanTableView searchFromElement(
        Object from,
        boolean inclusive,
        String indexName,
        String propName
    ) throws IndexNotFoundException, IndexPropertyAccessException{
        if(resultSet == null){
            resultSet = indexManager.searchFromElement(resultSet, from, inclusive, indexName, propName);
            if(resultSet == null){
                resultSet = new HashSet();
            }
        }else{
            BeanTableIndex index = createTmporaryIndex(indexName, new String[]{propName});
            operate(index.searchFromElement(from, inclusive));
        }
        return this;
    }

    
    /**
     * ����̃v���p�e�B���w�肵���l���傫��Bean�W������������B<p>
     * �͈͌����̈��ł���A�P���C���f�b�N�X�ɑ΂��Ă̂ݗL���B<br>
     *
     * @param from 臒l
     * @param indexName �C���f�b�N�X��
     * @param propName �v���p�e�B��
     * @return �������ʂ̂��̃r���[
     * @exception IndexNotFoundException �Y������C���f�b�N�X�����݂��Ȃ����A�����C���f�b�N�X�̏ꍇ
     */
    public BeanTableView searchFrom(
        Object from,
        String indexName,
        String propName
    ) throws IndexNotFoundException{
        if(resultSet == null){
            resultSet = indexManager.searchFrom(resultSet, from, indexName, propName);
            if(resultSet == null){
                resultSet = new HashSet();
            }
        }else{
            BeanTableIndex index = createTmporaryIndex(indexName, new String[]{propName});
            operate(index.searchFrom(from));
        }
        return this;
    }
    
    /**
     * ����̃v���p�e�B���w�肵���l���傫��Bean�W������������B<p>
     * �͈͌����̈��ł���A�P���C���f�b�N�X�ɑ΂��Ă̂ݗL���B<br>
     *
     * @param from 臒l
     * @param inclusive �������ʂ�臒l���܂ނ��ǂ����B�܂ޏꍇ��true
     * @param indexName �C���f�b�N�X��
     * @param propName �v���p�e�B��
     * @return �������ʂ̂��̃r���[
     * @exception IndexNotFoundException �Y������C���f�b�N�X�����݂��Ȃ����A�����C���f�b�N�X�̏ꍇ
     */

    public BeanTableView searchFrom(
        Object from,
        boolean inclusive,
        String indexName,
        String propName
    ) throws IndexNotFoundException{
        if(resultSet == null){
            resultSet = indexManager.searchFrom(resultSet, from, inclusive, indexName, propName);
            if(resultSet == null){
                resultSet = new HashSet();
            }
        }else{
            BeanTableIndex index = createTmporaryIndex(indexName, new String[]{propName});
            operate(index.searchFrom(from, inclusive));
        }
        return this;
    }

    
    /**
     * ����̃v���p�e�B���w�肵��Bean�̊Y������v���p�e�B��菬����Bean�W������������B<p>
     * �͈͌����̈��ł���A�P���C���f�b�N�X�ɑ΂��Ă̂ݗL���B<br>
     *
     * @param to 臒l������Bean
     * @param indexName �C���f�b�N�X��
     * @param propName �v���p�e�B��
     * @return �������ʂ̂��̃r���[
     * @exception IndexNotFoundException �Y������C���f�b�N�X�����݂��Ȃ����A�����C���f�b�N�X�̏ꍇ
     * @exception IndexPropertyAccessException �w�肳�ꂽ�v���p�e�B�̎擾�ŗ�O�����������ꍇ
     */
    public BeanTableView searchToElement(
        Object to,
        String indexName,
        String propName
    ) throws IndexNotFoundException, IndexPropertyAccessException{
        if(resultSet == null){
            resultSet = indexManager.searchToElement(resultSet, to, indexName, propName);
            if(resultSet == null){
                resultSet = new HashSet();
            }
        }else{
            BeanTableIndex index = createTmporaryIndex(indexName, new String[]{propName});
            operate(index.searchToElement(to));
        }
        return this;
    }
    
    /**
     * ����̃v���p�e�B���w�肵��Bean�̊Y������v���p�e�B��菬����Bean�W������������B<p>
     * �͈͌����̈��ł���A�P���C���f�b�N�X�ɑ΂��Ă̂ݗL���B<br>
     *
     * @param to 臒l������Bean
     * @param inclusive �������ʂ�臒l���܂ނ��ǂ����B�܂ޏꍇ��true
     * @param indexName �C���f�b�N�X��
     * @param propName �v���p�e�B��
     * @return �������ʂ̂��̃r���[
     * @exception IndexNotFoundException �Y������C���f�b�N�X�����݂��Ȃ����A�����C���f�b�N�X�̏ꍇ
     * @exception IndexPropertyAccessException �w�肳�ꂽ�v���p�e�B�̎擾�ŗ�O�����������ꍇ
     */

    public BeanTableView searchToElement(
        Object to,
        boolean inclusive,
        String indexName,
        String propName
    ) throws IndexNotFoundException, IndexPropertyAccessException{
        if(resultSet == null){
            resultSet = indexManager.searchToElement(resultSet, to, inclusive, indexName, propName);
            if(resultSet == null){
                resultSet = new HashSet();
            }
        }else{
            BeanTableIndex index = createTmporaryIndex(indexName, new String[]{propName});
            operate(index.searchToElement(to, inclusive));
        }
        return this;
    }

    
    /**
     * ����̃v���p�e�B���w�肵���l��菬����Bean�W������������B<p>
     * �͈͌����̈��ł���A�P���C���f�b�N�X�ɑ΂��Ă̂ݗL���B<br>
     *
     * @param to 臒l
     * @param indexName �C���f�b�N�X��
     * @param propName �v���p�e�B��
     * @return �������ʂ̂��̃r���[
     * @exception IndexNotFoundException �Y������C���f�b�N�X�����݂��Ȃ����A�����C���f�b�N�X�̏ꍇ
     */
    public BeanTableView searchTo(
        Object to,
        String indexName,
        String propName
    ) throws IndexNotFoundException{
        if(resultSet == null){
            resultSet = indexManager.searchTo(resultSet, to, indexName, propName);
            if(resultSet == null){
                resultSet = new HashSet();
            }
        }else{
            BeanTableIndex index = createTmporaryIndex(indexName, new String[]{propName});
            operate(index.searchTo(to));
        }
        return this;
    }
    
    /**
     * ����̃v���p�e�B���w�肵���l��菬����Bean�W������������B<p>
     * �͈͌����̈��ł���A�P���C���f�b�N�X�ɑ΂��Ă̂ݗL���B<br>
     *
     * @param to 臒l
     * @param inclusive �������ʂ�臒l���܂ނ��ǂ����B�܂ޏꍇ��true
     * @param indexName �C���f�b�N�X��
     * @param propName �v���p�e�B��
     * @return �������ʂ̂��̃r���[
     * @exception IndexNotFoundException �Y������C���f�b�N�X�����݂��Ȃ����A�����C���f�b�N�X�̏ꍇ
     */

    public BeanTableView searchTo(
        Object to,
        boolean inclusive,
        String indexName,
        String propName
    ) throws IndexNotFoundException{
        if(resultSet == null){
            resultSet = indexManager.searchTo(resultSet, to, inclusive, indexName, propName);
            if(resultSet == null){
                resultSet = new HashSet();
            }
        }else{
            BeanTableIndex index = createTmporaryIndex(indexName, new String[]{propName});
            operate(index.searchTo(to, inclusive));
        }
        return this;
    }

    
    /**
     * ����̃v���p�e�B���w�肵��Bean�̊Y������v���p�e�B�͈͓̔��ƂȂ�Bean�W������������B<p>
     * �͈͌����̈��ł���A�P���C���f�b�N�X�ɑ΂��Ă̂ݗL���B<br>
     *
     * @param from �͈͂̍ŏ�臒l������Bean
     * @param to �͈͂̍ő�臒l������Bean
     * @param indexName �C���f�b�N�X��
     * @param propName �v���p�e�B��
     * @return �������ʂ̂��̃r���[
     * @exception IndexNotFoundException �Y������C���f�b�N�X�����݂��Ȃ����A�����C���f�b�N�X�̏ꍇ
     * @exception IndexPropertyAccessException �w�肳�ꂽ�v���p�e�B�̎擾�ŗ�O�����������ꍇ
     */
    public BeanTableView searchRangeElement(
        Object from,
        Object to,
        String indexName,
        String propName
    ) throws IndexNotFoundException, IndexPropertyAccessException{
        if(resultSet == null){
            resultSet = indexManager.searchRangeElement(resultSet, from, to, indexName, propName);
            if(resultSet == null){
                resultSet = new HashSet();
            }
        }else{
            BeanTableIndex index = createTmporaryIndex(indexName, new String[]{propName});
            operate(index.searchRangeElement(from, to));
        }
        return this;
    }
    
    /**
     * ����̃v���p�e�B���w�肵��Bean�̊Y������v���p�e�B�͈͓̔��ƂȂ�Bean�W������������B<p>
     * �͈͌����̈��ł���A�P���C���f�b�N�X�ɑ΂��Ă̂ݗL���B<br>
     *
     * @param from �͈͂̍ŏ�臒l������Bean
     * @param fromInclusive �������ʂɍŏ�臒l���܂ނ��ǂ����B�܂ޏꍇ��true
     * @param to �͈͂̍ő�臒l������Bean
     * @param toInclusive �������ʂɍő�臒l���܂ނ��ǂ����B�܂ޏꍇ��true
     * @param indexName �C���f�b�N�X��
     * @param propName �v���p�e�B��
     * @return �������ʂ̂��̃r���[
     * @exception IndexNotFoundException �Y������C���f�b�N�X�����݂��Ȃ����A�����C���f�b�N�X�̏ꍇ
     * @exception IndexPropertyAccessException �w�肳�ꂽ�v���p�e�B�̎擾�ŗ�O�����������ꍇ
     */

    public BeanTableView searchRangeElement(
        Object from,
        boolean fromInclusive,
        Object to,
        boolean toInclusive,
        String indexName,
        String propName
    ) throws IndexNotFoundException, IndexPropertyAccessException{
        if(resultSet == null){
            resultSet = indexManager.searchRangeElement(resultSet, from, fromInclusive, to, toInclusive, indexName, propName);
            if(resultSet == null){
                resultSet = new HashSet();
            }
        }else{
            BeanTableIndex index = createTmporaryIndex(indexName, new String[]{propName});
            operate(index.searchRangeElement(from, fromInclusive, to, toInclusive));
        }
        return this;
    }

    
    /**
     * ����̃v���p�e�B���w�肵���l�͈͓̔��ƂȂ�Bean�W������������B<p>
     * �͈͌����̈��ł���A�P���C���f�b�N�X�ɑ΂��Ă̂ݗL���B<br>
     *
     * @param from �͈͂̍ŏ�臒l
     * @param to �͈͂̍ő�臒l
     * @param indexName �C���f�b�N�X��
     * @param propName �v���p�e�B��
     * @return �������ʂ̂��̃r���[
     * @exception IndexNotFoundException �Y������C���f�b�N�X�����݂��Ȃ����A�����C���f�b�N�X�̏ꍇ
     */
    public BeanTableView searchRange(
        Object from, 
        Object to, 
        String indexName,
        String propName
    ) throws IndexNotFoundException{
        if(resultSet == null){
            resultSet = indexManager.searchRange(resultSet, from, to, indexName, propName);
            if(resultSet == null){
                resultSet = new HashSet();
            }
        }else{
            BeanTableIndex index = createTmporaryIndex(indexName, new String[]{propName});
            operate(index.searchRange(from, to));
        }
        return this;
    }
    
    /**
     * ����̃v���p�e�B���w�肵���l�͈͓̔��ƂȂ�Bean�W������������B<p>
     * �͈͌����̈��ł���A�P���C���f�b�N�X�ɑ΂��Ă̂ݗL���B<br>
     *
     * @param from �͈͂̍ŏ�臒l
     * @param fromInclusive �������ʂɍŏ�臒l���܂ނ��ǂ����B�܂ޏꍇ��true
     * @param to �͈͂̍ő�臒l
     * @param toInclusive �������ʂɍő�臒l���܂ނ��ǂ����B�܂ޏꍇ��true
     * @param indexName �C���f�b�N�X��
     * @param propName �v���p�e�B��
     * @return �������ʂ̂��̃r���[
     * @exception IndexNotFoundException �Y������C���f�b�N�X�����݂��Ȃ����A�����C���f�b�N�X�̏ꍇ
     */

    public BeanTableView searchRange(
        Object from, 
        boolean fromInclusive,
        Object to, 
        boolean toInclusive,
        String indexName,
        String propName
    ) throws IndexNotFoundException{
        if(resultSet == null){
            resultSet = indexManager.searchRange(resultSet, from, fromInclusive, to, toInclusive, indexName, propName);
            if(resultSet == null){
                resultSet = new HashSet();
            }
        }else{
            BeanTableIndex index = createTmporaryIndex(indexName, new String[]{propName});
            operate(index.searchRange(from, fromInclusive, to, toInclusive));
        }
        return this;
    }

    
    /**
     * ���̃r���[�̕��������B<p>
     * �����̘_�����Z��Ԃ́A�f�t�H���g�l�ƂȂ�B<br>
     *
     * @return ���̃r���[�̕���
     */
    public Object clone(){
        BeanTableView clone = null;
        try{
            clone = (BeanTableView)super.clone();
        }catch(CloneNotSupportedException e){
        }
        if(resultSet != null){
            clone.resultSet = new HashSet(resultSet);
        }
        operator = OPERATOR_AND;
        return clone;
    }
    
    protected static class BeanComparator implements Comparator, Externalizable{
        
        protected SimpleProperty[] properties;
        protected boolean[] isAsc;
        protected Class elementClass;
        
        public BeanComparator(){
        }
        
        public BeanComparator(boolean isAsc){
            this.isAsc = new boolean[]{isAsc};
        }
        
        public BeanComparator(Class elementClass, String[] propNames){
            this(elementClass, propNames, null);
        }
        
        public BeanComparator(Class elementClass, String[] propNames, boolean[] isAsc){
            if(propNames == null || propNames.length == 0){
                throw new IllegalArgumentException("Property name array is empty.");
            }
            if(isAsc != null && propNames.length != isAsc.length){
                throw new IllegalArgumentException("Length of property name array and sort flag array is unmatch.");
            }
            this.elementClass = elementClass;
            this.properties = new SimpleProperty[propNames.length];
            for(int i = 0; i < propNames.length; i++){
                properties[i] = new SimpleProperty(propNames[i]);
                if(!properties[i].isReadable(elementClass)){
                    throw new IllegalArgumentException("No such readable property. property=" + propNames[i]);
                }
            }
            this.isAsc = isAsc;
        }
        
        public int compare(Object bean1, Object bean2){
            if(elementClass == null){
                if(bean1 != null && bean2 == null){
                    return isAsc[0] ? 1 : -1;
                }
                if(bean1 == null && bean2 != null){
                    return isAsc[0] ? -1 : 1;
                }
                if(bean1 != null && bean2 != null){
                    int comp = 0;
                    if(bean1 instanceof Comparable){
                        comp = ((Comparable)bean1).compareTo(bean2);
                    }else{
                        comp = bean1.hashCode() - bean2.hashCode();
                    }
                    if(comp != 0){
                        return isAsc[0] ? comp : -1 * comp;
                    }
                }
            }else{
                if(bean1 == null && bean2 == null){
                    return 0;
                }
                if(bean1 != null && bean2 == null){
                    return 1;
                }
                if(bean1 == null && bean2 != null){
                    return -1;
                }
                for(int i = 0; i < properties.length; i++){
                    Object val1 = null;
                    try{
                        val1 = properties[i].getProperty(bean1);
                    }catch(NoSuchPropertyException e){
                        throw new IndexPropertyAccessException(
                            elementClass,
                            properties[i].getPropertyName(),
                            e
                        );
                    }catch(InvocationTargetException e){
                        throw new IndexPropertyAccessException(
                            elementClass,
                            properties[i].getPropertyName(),
                            ((InvocationTargetException)e).getTargetException()
                        );
                    }
                    Object val2 = null;
                    try{
                        val2 = properties[i].getProperty(bean2);
                    }catch(NoSuchPropertyException e){
                    }catch(InvocationTargetException e){
                    }
                    if(val1 != null && val2 == null){
                        return (isAsc == null || isAsc[i]) ? 1 : -1;
                    }
                    if(val1 == null && val2 != null){
                        return (isAsc == null || isAsc[i]) ? -1 : 1;
                    }
                    if(val1 != null && val2 != null){
                        int comp = 0;
                        if(val1 instanceof Comparable){
                            comp = ((Comparable)val1).compareTo(val2);
                        }else{
                            comp = val1.hashCode() - val2.hashCode();
                        }
                        if(comp != 0){
                            return (isAsc == null || isAsc[i]) ? comp : -1 * comp;
                        }
                    }
                }
            }
            return 0;
        }
        
        public void writeExternal(ObjectOutput out) throws IOException{
            if(properties == null || properties.length == 0){
                out.writeInt(0);
            }else{
                out.writeInt(properties.length);
                for(int i = 0; i < properties.length; i++){
                    SimpleProperty prop = properties[i];
                    out.writeObject(prop.getPropertyName());
                }
            }
            if(isAsc == null || isAsc.length == 0){
                out.writeInt(0);
            }else{
                out.writeInt(isAsc.length);
                for(int i = 0; i < isAsc.length; i++){
                    boolean flg = isAsc[i];
                    out.writeBoolean(flg);
                }
            }
            out.writeObject(elementClass);
        }
        public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException{
            int len = in.readInt();
            if(len > 0){
                properties = new SimpleProperty[len];
                for(int i = 0; i < len; i++){
                    properties[i] = new SimpleProperty((String)in.readObject());
                }
            }
            len = in.readInt();
            if(len > 0){
                isAsc = new boolean[len];
                for(int i = 0; i < len; i++){
                    isAsc[i] = in.readBoolean();
                }
            }
            elementClass = (Class)in.readObject();
        }
    }
}
