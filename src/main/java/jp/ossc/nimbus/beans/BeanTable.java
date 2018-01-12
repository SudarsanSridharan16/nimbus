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

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.ConcurrentModificationException;
import java.util.Comparator;
import java.util.RandomAccess;
import java.io.Serializable;

/**
 * Bean�e�[�u���B<p>
 * Bean���e�[�u���̃��R�[�h�ɁABean�̃v���p�e�B���J�����Ɍ����āA�C���f�b�N�X�𒣂�A�������s����悤�ɂ������X�g�ł��B<br>
 * <pre>
 * class User{
 *     private String firstName;
 *     private String lastName;
 *     private String sex;
 *     private int age;
 *     public User(){}
 *     public User(String firstName, String lastName, String sex, int age){
 *         this.firstName = firstName;
 *         this.lastName = lastName;
 *         this.sex = sex;
 *         this.age = age;
 *     }
 *     public String getFirstName(){
 *         return firstName;
 *     }
 *     public String getLastName(){
 *         return lastName;
 *     }
 *     public String getSex(){
 *         return sex;
 *     }
 *     public int getAge(){
 *         return age;
 *     }
 * }
 * 
 * BeanTable table = new BeanTable(User.class);
 * 
 * // User�N���X�̃v���p�e�B"lastName"��"sex"�ɑ΂��ăC���f�b�N�X�𒣂�
 * table.setIndex("INDEX_LASTNAME_SEX", "lastName", "sex");
 * 
 * // User�N���X�̃v���p�e�B"age"�ɑ΂��ăC���f�b�N�X�𒣂�
 * table.setIndex("INDEX_AGE", "age");
 * 
 * // ���R�[�h��ǉ�����
 * table.add(new User("kotarou", "fuga", "male", 50));
 * table.add(new User("satoko", "fuga", "female", 49));
 * table.add(new User("tarou", "hoge", "male", 30));
 * table.add(new User("hanako", "hoge", "female", 28));
 * table.add(new User("junko", "hoge", "female", 20));
 * table.add(new User("mika", "hoge", "female", 10));
 * table.add(new User("ichirou, "hoge"", "male", 5));
 * 
 * // �����p�̃r���[���쐬����
 * BeanTableView view = table.createView();
 * 
 * // �����L�[�𐶐�����
 * User key = new User();
 * key.setLastName("hoge");
 * key.setSex("female");
 * key.setAge(20);
 * 
 * // "lastName"��"sex"�̍��v�������Ɍ���
 * // �X��"age"��20�ȏ�Ō���
 * // ���̌��ʂ�"age"��"sex"�ō~���\�[�g���Ď擾����
 * // �C���f�b�N�X�̎w��́A�C���f�b�N�X���܂��̓v���p�e�B���Ŏw�肷��
 * List resultSet = view.searchByElement(key, "INDEX_LASTNAME_SEX")
 *                            .searchFromElement(key, true, null, "age")
 *                            .getResultList(new String[]{"age", "sex"}, new boolean{false, false});
 * </pre>
 *
 * @author M.Takata
 */
public class BeanTable implements List, RandomAccess, Serializable, Cloneable{
    
    private static final long serialVersionUID = 619195842574715977L;
    
    protected BeanTableIndexManager indexManager;
    protected final boolean isSynchronized;
    protected List list;
    protected int modCount = 0;
    protected Comparator sortedComparator;
    
    /**
     * �w�肳�ꂽBean�N���X���i�[����e�[�u�����쐬����B<p>
     * ����������Ȃ��B<br>
     *
     * @param elementClass �e�[�u���Ɋi�[���郌�R�[�h�ƂȂ�Bean�̃N���X�I�u�W�F�N�g
     */
    public BeanTable(Class elementClass){
        this(elementClass, false);
    }
    
    /**
     * �w�肳�ꂽBean�N���X���i�[����e�[�u�����쐬����B<p>
     *
     * @param elementClass �e�[�u���Ɋi�[���郌�R�[�h�ƂȂ�Bean�̃N���X�I�u�W�F�N�g
     * @param isSynchronized ���������邩�ǂ����B����������ꍇ�́Atrue
     */
    public BeanTable(Class elementClass, boolean isSynchronized){
        indexManager = new BeanTableIndexManager(elementClass, isSynchronized);
        this.isSynchronized = isSynchronized;
        list = isSynchronized ? Collections.synchronizedList(new ArrayList()) : new ArrayList();
    }
    
    /**
     * �w�肳�ꂽBean�N���X���i�[����e�[�u�����쐬����B<p>
     * ����������Ȃ��B<br>
     *
     * @param elementClass �e�[�u���Ɋi�[���郌�R�[�h�ƂȂ�Bean�̃N���X�I�u�W�F�N�g
     * @param c �������R�[�h�ƂȂ�Bean�̏W��
     */
    public BeanTable(Class elementClass, Collection c){
        this(elementClass, c, false);
    }
    
    /**
     * �w�肳�ꂽBean�N���X���i�[����e�[�u�����쐬����B<p>
     *
     * @param elementClass �e�[�u���Ɋi�[���郌�R�[�h�ƂȂ�Bean�̃N���X�I�u�W�F�N�g
     * @param c �������R�[�h�ƂȂ�Bean�̏W��
     * @param isSynchronized ���������邩�ǂ����B����������ꍇ�́Atrue
     */
    public BeanTable(Class elementClass, Collection c, boolean isSynchronized){
        this(elementClass, isSynchronized);
        addAll(c);
    }
    
    /**
     * �w�肳�ꂽBean�N���X���i�[����e�[�u�����쐬����B<p>
     * ����������Ȃ��B<br>
     *
     * @param elementClass �e�[�u���Ɋi�[���郌�R�[�h�ƂȂ�Bean�̃N���X�I�u�W�F�N�g
     * @param initialCapacity �����e��
     */
    public BeanTable(Class elementClass, int initialCapacity){
        this(elementClass, initialCapacity, false);
    }
    
    /**
     * �w�肳�ꂽBean�N���X���i�[����e�[�u�����쐬����B<p>
     *
     * @param elementClass �e�[�u���Ɋi�[���郌�R�[�h�ƂȂ�Bean�̃N���X�I�u�W�F�N�g
     * @param initialCapacity �����e��
     * @param isSynchronized ���������邩�ǂ����B����������ꍇ�́Atrue
     */
    public BeanTable(Class elementClass, int initialCapacity, boolean isSynchronized){
        indexManager = new BeanTableIndexManager(elementClass, isSynchronized);
        this.isSynchronized = isSynchronized;
        list = isSynchronized ? Collections.synchronizedList(new ArrayList(initialCapacity)) : new ArrayList(initialCapacity);
    }
    
    /**
     * �e�[�u���Ɋi�[���郌�R�[�h�ƂȂ�Bean�̃N���X�I�u�W�F�N�g���擾����B<p>
     *
     * @return ���R�[�h�ƂȂ�Bean�̃N���X�I�u�W�F�N�g
     */
    public Class getElementClass(){
        return indexManager.getElementClass();
    }
    
    /**
     * �C���f�b�N�X��ǉ�����B<p>
     * �C���f�b�N�ɂ́A�P��̃v���p�e�B�ō\�������P���C���f�b�N�X�ƁA�����̃v���p�e�B�ō\������镡���C���f�b�N�X�����݂���B<br>
     * �����C���f�b�N�X��ǉ������ꍇ�́A�����I�ɂ��̗v�f�ƂȂ�P��v���p�e�B�̒P���C���f�b�N�X�������I�ɐ��������B<p>
     * �A���A�����������ꂽ�P��C���f�b�N�X�́A�C���f�b�N�X���������Ȃ����߁A�C���f�b�N�X���ł͎w��ł����A�v���p�e�B���Ŏw�肵�Ďg�p����B<br>
     * �C���f�b�N�X�̎�ނɂ���āA�g�p�ł��錟���@�\���قȂ�B�P���C���f�b�N�X�́A��v�����Ɣ͈͌����̗������\�����A�����C���f�b�N�X�́A��v�����̂݉\�ł���B<br>
     *
     * @param name �C���f�b�N�X��
     * @param props �C���f�b�N�X�𒣂�Bean�̃v���p�e�B���z��
     * @exception NoSuchPropertyException �w�肳�ꂽ�v���p�e�B��Bean�ɑ��݂��Ȃ��ꍇ
     */
    public void setIndex(String name, String[] props) throws NoSuchPropertyException{
        indexManager.setIndex(name, props);
    }
    
    /**
     * �J�X�^�}�C�Y�����C���f�b�N�X��ǉ�����B<p>
     *
     * @param name �C���f�b�N�X��
     * @param keyFactory �C���f�b�N�X�̃L�[�𐶐�����t�@�N�g��
     * @see #setIndex(String, String[])
     */
    public void setIndex(String name, BeanTableIndexKeyFactory keyFactory){
        indexManager.setIndex(name, keyFactory);
    }
    
    /**
     * �C���f�b�N�X���폜����B<p>
     *
     * @param name �C���f�b�N�X��
     */
    public void removeIndex(String name){
        indexManager.removeIndex(name);
    }
    
    /**
     * �C���f�b�N�X���ĉ�͂���B<p>
     */
    public void analyzeIndex(){
        indexManager.clear();
        indexManager.addAll(list);
    }
    
    /**
     * �������s���r���[���쐬����B<p>
     * 
     * @return �����r���[
     */
    public BeanTableView createView(){
        return new BeanTableView(indexManager);
    }
    
    /**
     * ���̃e�[�u�����̂��w�肵��Bean�̃v���p�e�B���ŏ����\�[�g����B<p>
     *
     * @param propNames �\�[�g����Bean�̃v���p�e�B���z��
     */
    public void sort(String[] propNames){
        sort(propNames, null);
    }
    
    /**
     * ���̃e�[�u�����̂��w�肵��Bean�̃v���p�e�B���Ŏw�肳�ꂽ�\�[�g�����Ƀ\�[�g����B<p>
     *
     * @param propNames �\�[�g����Bean�̃v���p�e�B���z��
     * @param isAsc propNames�Ŏw�肵���v���p�e�B���̃\�[�g�����������z��Btrue���w�肷��Ə���
     */
    public void sort(String[] propNames, boolean[] isAsc){
        sortedComparator = BeanTableView.sort(getElementClass(), list, propNames, isAsc);
    }
    
    /**
     * �o�C�i���T�[�`�A���S���Y�����g�p���āA�w�肳�ꂽ�I�u�W�F�N�g���������܂��B<p>
     * ���X�g�́A���̌Ăяo���̑O�ɁA{@link #sort(String[])}���\�b�h���g�p���āA�\�[�g���Ȃ���΂����܂���B
     * ���X�g���\�[�g����Ă��Ȃ��ꍇ�A���ʂ͒�`����܂���B
     * �w�肳�ꂽ�I�u�W�F�N�g�Ɠ������v�f�����X�g�ɕ�������ꍇ�A�ǂꂪ�����邩�͕ۏ؂���܂���B
     *
     * @param key ���������L�[�v�f
     * @return ���X�g���Ɍ����L�[������ꍇ�͌����L�[�̃C���f�b�N�X�B����ȊO�̏ꍇ�� (-(�}���|�C���g) - 1)�B�}���|�C���g�́A���̃L�[�����X�g�ɑ}�������|�C���g�Ƃ��Ē�`�����B�܂�A���̃L�[�����傫�ȍŏ��̗v�f�̃C���f�b�N�X�B���X�g���̂��ׂĂ̗v�f���w�肳�ꂽ�L�[�����������ꍇ�� size()�B����ɂ��A�L�[�����������ꍇ�ɂ̂ݖ߂�l�� >= 0 �ɂȂ邱�Ƃ��ۏ؂����B
     */
    public int binarySearch(Object key){
        return Collections.binarySearch(list, key, sortedComparator);
    }
    
    public int size(){
        return list.size();
    }
    
    public boolean isEmpty(){
        return list.isEmpty();
    }
    
    public boolean contains(Object o){
        return list.contains(o);
    }
    
    public Iterator iterator(){
        return new BeanTableIterator();
    }
    
    public ListIterator listIterator(){
        return listIterator(0);
    }
    
    public ListIterator listIterator(int index){
        return new BeanTableListIterator(index);
    }
    
    public List subList(int fromIndex, int toIndex){
        return Collections.unmodifiableList(list.subList(fromIndex, toIndex));
    }
    
    public Object[] toArray(){
        return list.toArray();
    }
    
    public Object[] toArray(Object[] a){
        return list.toArray(a);
    }
    
    public Object get(int index){
        return list.get(index);
    }
    
    public int indexOf(Object o){
        return list.indexOf(o);
    }
    
    public int lastIndexOf(Object o){
        return list.lastIndexOf(o);
    }
    
    public boolean containsAll(Collection c){
        return list.containsAll(c);
    }
    
    public boolean add(Object element){
        if(element == null){
            return false;
        }
        indexManager.add(element);
        boolean isAdd = list.add(element);
        if(isAdd){
            modCount++;
        }
        return isAdd;
    }
    
    public void add(int index, Object element){
        if(element == null){
            return;
        }
        indexManager.add(element);
        list.add(index, element);
        modCount++;
    }
    
    public Object set(int index, Object element){
        Object old = list.set(index, element);
        indexManager.replace(old, element);
        modCount++;
        return old;
    }
    
    public boolean addAll(Collection c){
        if(list.addAll(c)){
            indexManager.addAll(c);
            modCount++;
            return true;
        }else{
            return false;
        }
    }
    
    public boolean addAll(int index, Collection c){
        if(list.addAll(index, c)){
            indexManager.addAll(c);
            modCount++;
            return true;
        }else{
            return false;
        }
    }
    
    public boolean remove(Object o){
        boolean isRemoved = list.remove(o);
        if(isRemoved){
            indexManager.remove(o);
            modCount++;
        }
        return isRemoved;
    }
    
    public Object remove(int index){
        Object ret = list.remove(index);
        indexManager.remove(ret);
        modCount++;
        return ret;
    }
    
    public boolean removeAll(Collection c){
        if(list.removeAll(c)){
            Iterator itr = c.iterator();
            while(itr.hasNext()){
                Object element = itr.next();
                indexManager.remove(element);
            }
            modCount++;
            return true;
        }else{
            return false;
        }
    }
    
    public boolean retainAll(Collection c){
        if(list.retainAll(c)){
            indexManager.retainAll(c);
            modCount++;
            return true;
        }else{
            return false;
        }
    }
    
    public void clear(){
        indexManager.clear();
        list.clear();
        modCount++;
    }
    
    public Object clone(){
        BeanTable clone = null;
        try{
            clone = (BeanTable)super.clone();
        }catch(CloneNotSupportedException e){
        }
        clone.list = isSynchronized ? Collections.synchronizedList(new ArrayList()) : new ArrayList();
        clone.indexManager = new BeanTableIndexManager(getElementClass(), isSynchronized);
        clone.addAll(this);
        return clone;
    }
    
    protected class BeanTableIterator implements Iterator, Serializable{
        
        private static final long serialVersionUID = -7202550703286618072L;
        
        protected int cursor = 0;
        protected int lastRet = -1;
        protected int expectedModCount = modCount;
        
        public boolean hasNext(){
            return cursor != size();
        }
        
        public Object next(){
            checkForComodification();
            try{
                Object next = get(cursor);
                lastRet = cursor++;
                return next;
            }catch(IndexOutOfBoundsException e){
                checkForComodification();
                throw new NoSuchElementException();
            }
        }
        
        public void remove(){
            if(lastRet == -1){
                throw new IllegalStateException();
            }
            checkForComodification();
            
            try{
                BeanTable.this.remove(lastRet);
                if(lastRet < cursor){
                    cursor--;
                }
                lastRet = -1;
                expectedModCount++;
            }catch(IndexOutOfBoundsException e){
                throw new ConcurrentModificationException();
            }
        }
        
        final void checkForComodification(){
            if(modCount != expectedModCount){
                throw new ConcurrentModificationException();
            }
        }
    }
    
    protected class BeanTableListIterator extends BeanTableIterator implements ListIterator{
        
        private static final long serialVersionUID = 8909520626834440479L;
        
        public BeanTableListIterator(int index){
            cursor = index;
        }
        
        public boolean hasPrevious(){
            return cursor != 0;
        }
        
        public Object previous(){
            checkForComodification();
            try{
                int i = cursor - 1;
                Object previous = get(i);
                lastRet = cursor = i;
                return previous;
            }catch(IndexOutOfBoundsException e){
                checkForComodification();
                throw new NoSuchElementException();
            }
        }
        
        public int nextIndex(){
            return cursor;
        }
        
        public int previousIndex(){
            return cursor - 1;
        }
        
        public void set(Object o){
            if(lastRet == -1){
                throw new IllegalStateException();
            }
            checkForComodification();
            
            try{
                BeanTable.this.set(lastRet, o);
                expectedModCount++;
            }catch(IndexOutOfBoundsException e){
                throw new ConcurrentModificationException();
            }
        }
        
        public void add(Object o){
            checkForComodification();
            
            try{
                BeanTable.this.add(cursor++, o);
                lastRet = -1;
                expectedModCount++;
            }catch(IndexOutOfBoundsException e){
                throw new ConcurrentModificationException();
            }
        }
    }
}