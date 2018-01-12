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
package jp.ossc.nimbus.service.cache;

import java.util.*;
import java.lang.reflect.Array;

import jp.ossc.nimbus.core.*;

/**
 * ���ۃL���b�V���}�b�v�T�[�r�X�B<p>
 *
 * @author M.Takata
 */
public abstract class AbstractCacheMapService extends ServiceBase
 implements CacheRemoveListener, AbstractCacheMapServiceMBean{
    
    private static final long serialVersionUID = 6779186037980520151L;
    
    /**
     * �L���b�V���̃L�[�ƃL���b�V���Q�Ƃ̃}�b�v�B<p>
     */
    protected Map references;
    
    /**
     * OverflowController�T�[�r�X�̃T�[�r�X���z��B<p>
     */
    protected ServiceName[] overflowControllerServiceNames;
    
    /**
     * OverflowController�T�[�r�X�̃��X�g�B<p>
     */
    protected List overflowControllers;
    
    /**
     * �T�[�r�X��~���ɁA�L���b�V�����N���A���邩�ǂ����̃t���O�B<p>
     * �f�t�H���g�́Afalse�B<br>
     */
    protected boolean isClearOnStop;
    
    /**
     * �T�[�r�X�j�����ɁA�L���b�V�����N���A���邩�ǂ����̃t���O�B<p>
     * �f�t�H���g�́Atrue�B<br>
     */
    protected boolean isClearOnDestroy = true;
    
    // AbstractCacheMapServiceMBean��JavaDoc
    public void setOverflowControllerServiceNames(ServiceName[] names){
        overflowControllerServiceNames = names;
    }
    // AbstractCacheMapServiceMBean��JavaDoc
    public ServiceName[] getOverflowControllerServiceNames(){
        return overflowControllerServiceNames;
    }
    
    // AbstractCacheMapServiceMBean��JavaDoc
    public void setClearOnStop(boolean isClear){
        isClearOnStop = isClear;
    }
    // AbstractCacheMapServiceMBean��JavaDoc
    public boolean isClearOnStop(){
        return isClearOnStop;
    }
    
    // AbstractCacheMapServiceMBean��JavaDoc
    public void setClearOnDestroy(boolean isClear){
        isClearOnDestroy = isClear;
    }
    // AbstractCacheMapServiceMBean��JavaDoc
    public boolean isClearOnDestroy(){
        return isClearOnDestroy;
    }
    
    /**
     * OverflowController��ݒ肷��B
     */
    public void setOverflowControllers(List overflowControllers) {
		this.overflowControllers = overflowControllers;
	}
    
	/**
     * �T�[�r�X�̐����O�������s���B<p>
     * �C���X�^���X�ϐ��̏��������s���B<br>
     *
     * @exception �����O�����Ɏ��s�����ꍇ
     */
    public void preCreateService() throws Exception{
        super.preCreateService();
        references = Collections.synchronizedMap(new HashMap());
        overflowControllers = new ArrayList();
    }
    
    /**
     * �T�[�r�X�̊J�n�O�������s���B<p>
     * QverflowController�T�[�r�X�̎擾���s���B<br>
     *
     * @exception �J�n�O�����Ɏ��s�����ꍇ
     */
    public void preStartService() throws Exception{
        super.preStartService();
        if(overflowControllerServiceNames != null){
            for(int i = 0; i < overflowControllerServiceNames.length; i++){
                overflowControllers.add(
                    (OverflowController)ServiceManagerFactory
                        .getServiceObject(overflowControllerServiceNames[i])
                );
            }
        }
    }
    
    /**
     * �T�[�r�X�̒�~�㏈�����s���B<p>
     * QverflowController�T�[�r�X�̎Q�Ƃ�j������B<br>
     *
     * @exception ��~�㏈���Ɏ��s�����ꍇ
     */
    public void postStopService() throws Exception{
        if(isClearOnStop()){
            clear();
        }
        if(overflowControllers != null){
            overflowControllers.clear();
        }
        super.postStopService();
    }
    
    /**
     * �T�[�r�X�̔j���㏈�����s���B<p>
     * �C���X�^���X�ϐ��̎Q�Ƃ�j������B<br>
     *
     * @exception �j���㏈���Ɏ��s�����ꍇ
     */
    public void postDestroyService() throws Exception{
        if(isClearOnDestroy()){
            clear();
        }
        references = null;
        overflowControllers = null;
        super.postDestroyService();
    }
    
    // CacheMap��JavaDoc
    public KeyCachedReference getCachedReference(Object key){
        if(references == null){
            return null;
        }
        return (KeyCachedReference)references.get(key);
    }
    
    // CacheMap��JavaDoc
    public int size(){
        if(references == null){
            return 0;
        }
        return references.size();
    }
    
    // CacheMap��JavaDoc
    public boolean isEmpty(){
        if(references == null){
            return true;
        }
        return references.isEmpty();
    }
    
    // CacheMap��JavaDoc
    public boolean containsKey(Object key){
        if(references == null){
            return false;
        }
        return references.containsKey(key);
    }
    
    // CacheMap��JavaDoc
    public boolean containsValue(Object value){
        if(references == null){
            return false;
        }
        synchronized(references){
            final Iterator keys = references.keySet().iterator();
            while(keys.hasNext()){
                final Object obj = get(keys.next(), false);
                if(value == null){
                    if(obj == null){
                        return true;
                    }
                }else if(value.equals(obj)){
                    return true;
                }
            }
        }
        return false;
    }
    
    // CacheMap��JavaDoc
    public Object get(Object key){
        return get(key, true);
    }
    
    /**
     * �w�肳�ꂽ�L�[�̃L���b�V�����擾����B<p>
     *
     * @param key �L���b�V���̃L�[
     * @param notify �L���b�V���ɃA�N�Z�X�����������X�i�ɒʒm���邩�ǂ����̃t���O�B�ʒm����ꍇ�Atrue
     */
    protected Object get(Object key, boolean notify){
        if(references == null){
            return null;
        }
        final CachedReference ref = (CachedReference)references.get(key);
        if(ref == null){
            return null;
        }
        return ref.get(this, notify);
    }
    
    // CacheMap��JavaDoc
    public Object put(Object key, Object value){
        if(references == null){
            return null;
        }
        Object oldVal = null;
        if(references.containsKey(key)){
            oldVal = remove(key);
        }
        final KeyCachedReference ref = createKeyCachedReference(key, value);
        put(key, ref);
        return oldVal;
    }
    
    /**
     * �w�肵���L�[�̃L���b�V���Q�Ƃ�ǉ�����B<p>
     *
     * @param key �L���b�V���̃L�[
     * @param ref �L���b�V���Q��
     */
    protected void put(Object key, KeyCachedReference ref){
        if(references == null || getState() > STOPPED || ref == null){
            return;
        }
        ref.addCacheRemoveListener(this);
        references.put(key, ref);
        if(overflowControllers.size() != 0){
            final Iterator controllers = overflowControllers.iterator();
            while(controllers.hasNext()){
                final OverflowController controller
                     = (OverflowController)controllers.next();
                controller.control(ref);
            }
        }
    }
    
    /**
     * �L�[�t���L���b�V���Q�Ƃ𐶐�����B<p>
     *
     * @param key �L���b�V���̃L�[
     * @param obj �L���b�V������I�u�W�F�N�g
     * @return �L�[�t���L���b�V���Q��
     */
    protected abstract KeyCachedReference createKeyCachedReference(
        Object key,
        Object obj
    );
    
    // CacheMap��JavaDoc
    public Object remove(Object key){
        if(references == null){
            return null;
        }
        Object val = null;
        final CachedReference ref
             = (CachedReference)references.remove(key);
        if(ref != null){
            val = ref.get(this, false);
            ref.remove(this);
        }
        return val;
    }
    
    // CacheMap��JavaDoc
    public void putAll(Map map){
        if(references == null || map == null || map.size() == 0){
            return;
        }
        final Iterator keys = map.keySet().iterator();
        while(keys.hasNext()){
            final Object key = keys.next();
            put(key, map.get(key));
        }
    }
    
    // CacheMap��JavaDoc
    public void clear(){
        if(references == null || references.size() == 0){
            return;
        }
        final Object[] keys = references.keySet().toArray();
        for(int i = 0; i < keys.length; i++){
            remove(keys[i]);
        }
    }
    
    // CacheMap��JavaDoc
    public Set keySet(){
        return new KeySet();
    }
    
    // CacheMap��JavaDoc
    public Collection values(){
        return new ValuesCollection();
    }
    
    // CacheMap��JavaDoc
    public Set entrySet(){
        return new EntrySet();
    }
    
    // CacheRemoveListener��JavaDoc
    public void removed(CachedReference ref){
        if(references != null && ref instanceof KeyCachedReference){
            final KeyCachedReference keyRef = (KeyCachedReference)ref;
            references.remove(keyRef.getKey());
        }
    }
    
    /**
     * �L���b�V���}�b�v�̃L�[�W���B<p>
     *
     * @author M.Takata
     * @see AbstractCacheMapService#keySet()
     */
    protected class KeySet
     implements Set, java.io.Serializable{
        
        private static final long serialVersionUID = 8251697022788153149L;
        
        private Collection collection;
        
        /**
         * �C���X�^���X�𐶐�����B<p>
         */
        public KeySet(){
            if(references != null){
                collection = references.keySet();
            }
        }
        
        // Set��JavaDoc
        public int size(){
            return AbstractCacheMapService.this.size();
        }
        
        // Set��JavaDoc
        public boolean isEmpty(){
            return AbstractCacheMapService.this.isEmpty();
        }
        
        // Set��JavaDoc
        public boolean contains(Object o){
            return AbstractCacheMapService.this.containsKey(o);
        }
        
        // Set��JavaDoc
        public boolean containsAll(Collection c){
            final Iterator keys = c.iterator();
            boolean result = true;
            while(keys.hasNext()){
                result &= AbstractCacheMapService.this.containsKey(
                    keys.next()
                );
                if(!result){
                    break;
                }
            }
            return result;
        }
        
        // Set��JavaDoc
        public Iterator iterator(){
            return new KeyIterator();
        }
        
        // Set��JavaDoc
        public Object[] toArray(){
            if(references == null || references.size() == 0){
                return new Object[0];
            }
            Object[] result = null;
            synchronized(references){
                int count = 0;
                result = new Object[references.size()];
                final Iterator keys = references.keySet().iterator();
                while(keys.hasNext()){
                    result[count++] = keys.next();
                }
            }
            return result;
        }
        
        // Set��JavaDoc
        public Object[] toArray(Object[] a){
            if(references == null || references.size() == 0){
                if(a.length == 0){
                    return a;
                }else{
                    for(int i = 0; i < a.length; i++){
                        a[i] = null;
                    }
                    return a;
                }
            }
            Object[] result = null;
            synchronized(references){
                final int length = references.size();
                if(a.length >= length){
                    result = a;
                }else{
                    result = (Object[])Array.newInstance(
                        a.getClass().getComponentType(),
                        length
                    );
                }
                int count = 0;
                final Iterator keys = references.keySet().iterator();
                while(keys.hasNext()){
                    result[count++] = keys.next();
                }
            }
            return result;
        }
        
        // Set��JavaDoc
        public boolean add(Object o){
            throw new UnsupportedOperationException();
        }
        
        // Set��JavaDoc
        public boolean addAll(Collection c){
            throw new UnsupportedOperationException();
        }
        
        // Set��JavaDoc
        public boolean remove(Object o){
            if(references == null || references.size() == 0){
                return false;
            }
            final Object removed = AbstractCacheMapService.this.remove(o);
            return removed != null;
        }
        
        // Set��JavaDoc
        public boolean removeAll(Collection c){
            final Iterator keys = c.iterator();
            boolean result = false;
            while(keys.hasNext()){
                result |= remove(keys.next());
            }
            return result;
        }
        
        // Set��JavaDoc
        public boolean retainAll(Collection c){
            if(references == null || references.size() == 0){
                return false;
            }
            boolean result = false;
            final Object[] keys = references.keySet().toArray();
            for(int i = 0; i < keys.length; i++){
                if(!c.contains(keys[i])){
                    result = remove(keys[i]);
                }
            }
            return result;
        }
        
        // Set��JavaDoc
        public void clear(){
            AbstractCacheMapService.this.clear();
        }
        
        // Set��JavaDoc
        public boolean equals(Object o){
            if(o == null){
                return false;
            }
            if(o == this){
                return true;
            }
            if(o instanceof KeySet){
                final KeySet cmp = (KeySet)o;
                if(collection == null){
                    return cmp.collection == null;
                }else if(collection.equals(cmp.collection)){
                    return true;
                }
            }
            return false;
        }
        
        // Set��JavaDoc
        public int hashCode(){
            return collection == null ? 0 : collection.hashCode();
        }
        
        public String toString(){
            return collection == null
                 ? super.toString() : collection.toString();
        }
    }
    
    /**
     * �L���b�V���}�b�v�̃L�[�J��Ԃ��B<p>
     *
     * @author M.Takata
     * @see AbstractCacheMapService.KeySet#iterator()
     */
    protected class KeyIterator
     implements Iterator, java.io.Serializable{
        
        private static final long serialVersionUID = 8251697022788153149L;
        
        private Iterator iterator;
        private Object current;
        
        /**
         * �C���X�^���X�𐶐�����B<p>
         */
        public KeyIterator(){
            if(references != null){
                iterator = new HashSet(references.keySet()).iterator();
            }
        }
        
        // Iterator��JavaDoc
        public boolean hasNext(){
            return iterator == null ? false : iterator.hasNext();
        }
        
        // Iterator��JavaDoc
        public Object next(){
            if(iterator == null){
                throw new NoSuchElementException();
            }
            current = null;
            current = iterator.next();
            return current;
        }
        
        // Iterator��JavaDoc
        public void remove(){
            if(current == null){
                throw new IllegalStateException();
            }
            AbstractCacheMapService.this.remove(current);
            iterator.remove();
            if(!iterator.hasNext()){
                current = null;
            }
        }
        
        public String toString(){
            return iterator == null
                 ? super.toString() : iterator.toString();
        }
    }
    
    /**
     * �L���b�V���}�b�v�̒l�W���B<p>
     *
     * @author M.Takata
     * @see AbstractCacheMapService#values()
     */
    protected class ValuesCollection
     implements Collection, java.io.Serializable{
        
        private static final long serialVersionUID = -3991603731459237593L;
        
        private Collection collection;
        
        /**
         * �C���X�^���X�𐶐�����B<p>
         */
        public ValuesCollection(){
            if(references != null){
                collection = references.values();
            }
        }
        
        // Collection��JavaDoc
        public int size(){
            return AbstractCacheMapService.this.size();
        }
        
        // Collection��JavaDoc
        public boolean isEmpty(){
            return AbstractCacheMapService.this.isEmpty();
        }
        
        // Collection��JavaDoc
        public boolean contains(Object o){
            return AbstractCacheMapService.this.containsValue(o);
        }
        
        // Collection��JavaDoc
        public boolean containsAll(Collection c){
            final Iterator values = c.iterator();
            boolean result = true;
            while(values.hasNext()){
                result &= AbstractCacheMapService.this.containsValue(
                    values.next()
                );
            }
            return result;
        }
        
        // Collection��JavaDoc
        public Iterator iterator(){
            return new ValuesIterator();
        }
        
        // Collection��JavaDoc
        public Object[] toArray(){
            if(references == null || references.size() == 0){
                return new Object[0];
            }
            Object[] result = null;
            synchronized(references){
                result = new Object[references.size()];
                int count = 0;
                final Iterator values = references.values().iterator();
                while(values.hasNext()){
                    final CachedReference ref = (CachedReference)values.next();
                    if(ref != null){
                        result[count++] = ref.get(this);
                    }else{
                        result[count++] = null;
                    }
                }
            }
            return result;
        }
        
        // Collection��JavaDoc
        public Object[] toArray(Object[] a){
            if(references == null || references.size() == 0){
                if(a.length == 0){
                    return a;
                }else{
                    for(int i = 0; i < a.length; i++){
                        a[i] = null;
                    }
                    return a;
                }
            }
            Object[] result = null;
            synchronized(references){
                final int length = references.size();
                if(a.length >= length){
                    result = a;
                }else{
                    result = (Object[])Array.newInstance(
                        a.getClass().getComponentType(),
                        length
                    );
                }
                int count = 0;
                final Iterator values = references.values().iterator();
                while(values.hasNext()){
                    final CachedReference ref = (CachedReference)values.next();
                    if(ref != null){
                        result[count++] = ref.get(this);
                    }else{
                        result[count++] = null;
                    }
                }
            }
            return result;
        }
        
        // Collection��JavaDoc
        public boolean add(Object o){
            throw new UnsupportedOperationException();
        }
        
        // Collection��JavaDoc
        public boolean addAll(Collection c){
            throw new UnsupportedOperationException();
        }
        
        // Collection��JavaDoc
        public boolean remove(Object o){
            if(references == null || references.size() == 0){
                return false;
            }
            boolean result = false;
            final Object[] values = references.values().toArray();
            for(int i = 0; i < values.length; i++){
                final KeyCachedReference ref
                     = (KeyCachedReference)values[i];
                Object val = null;
                if(ref != null){
                    val = ref.get(this, false);
                }
                Object removed = null;
                if(val == null){
                    if(o == null){
                        if(ref != null){
                            removed = AbstractCacheMapService.this
                                .remove(ref.getKey());
                        }
                    }
                }else if(val.equals(o)){
                    if(ref != null){
                        removed = AbstractCacheMapService.this
                            .remove(ref.getKey());
                    }
                }
                if(removed != null){
                    result = true;
                }
            }
            return result;
        }
        
        // Collection��JavaDoc
        public boolean removeAll(Collection c){
            final Iterator values = c.iterator();
            boolean result = false;
            while(values.hasNext()){
                result |= remove(values.next());
            }
            return result;
        }
        
        // Collection��JavaDoc
        public boolean retainAll(Collection c){
            if(references == null || references.size() == 0){
                return false;
            }
            boolean result = false;
            synchronized(references){
                final Object[] values = references.values().toArray();
                for(int i = 0; i < values.length; i++){
                    final KeyCachedReference ref
                         = (KeyCachedReference)values[i];
                    Object val = null;
                    if(ref != null){
                        val = ref.get(this, false);
                    }
                    if(!c.contains(val)){
                        if(ref != null){
                            if(AbstractCacheMapService.this
                                .remove(ref.getKey()) != null){
                                result = true;
                            }
                        }
                    }
                }
            }
            return result;
        }
        
        // Collection��JavaDoc
        public void clear(){
            AbstractCacheMapService.this.clear();
        }
        
        // Collection��JavaDoc
        public boolean equals(Object o){
            if(o == null){
                return false;
            }
            if(o == this){
                return true;
            }
            if(o instanceof ValuesCollection){
                final ValuesCollection cmp = (ValuesCollection)o;
                if(collection == null){
                    return cmp.collection == null;
                }else if(collection.equals(cmp.collection)){
                    return true;
                }
            }
            return false;
        }
        
        // Collection��JavaDoc
        public int hashCode(){
            return collection == null ? 0 : collection.hashCode();
        }
        
        public String toString(){
            return collection == null
                 ? super.toString() : collection.toString();
        }
    }
    
    /**
     * �L���b�V���}�b�v�̒l�J��Ԃ��B<p>
     *
     * @author M.Takata
     * @see AbstractCacheMapService.ValuesCollection#iterator()
     */
    protected class ValuesIterator
     implements Iterator, java.io.Serializable{
        
        private static final long serialVersionUID = 1885683078757668887L;
        
        private Iterator iterator;
        private KeyCachedReference current;
        
        /**
         * �C���X�^���X�𐶐�����B<p>
         */
        public ValuesIterator(){
            if(references != null){
                iterator = new HashSet(references.values()).iterator();
            }
        }
        
        // Iterator��JavaDoc
        public boolean hasNext(){
            return iterator == null ? false : iterator.hasNext();
        }
        
        // Iterator��JavaDoc
        public Object next(){
            if(iterator == null){
                throw new NoSuchElementException();
            }
            current = null;
            current = (KeyCachedReference)iterator.next();
            return current.get(this);
        }
        
        // Iterator��JavaDoc
        public void remove(){
            if(current == null){
                throw new IllegalStateException();
            }
            AbstractCacheMapService.this.remove(current.getKey());
            iterator.remove();
            if(!iterator.hasNext()){
                current = null;
            }
        }
        
        public String toString(){
            return iterator == null
                 ? super.toString() : iterator.toString();
        }
    }
    
    /**
     * �L���b�V���}�b�v�̃}�b�v�G���g���W���B<p>
     *
     * @author M.Takata
     * @see AbstractCacheMapService#entrySet()
     */
    protected class EntrySet
     implements Set, java.io.Serializable{
        
        private static final long serialVersionUID = -6274837740283895811L;
        
        private Collection collection;
        
        /**
         * �C���X�^���X�𐶐�����B<p>
         */
        public EntrySet(){
            if(references != null){
                collection = references.keySet();
            }
        }
        
        // Set��JavaDoc
        public int size(){
            return AbstractCacheMapService.this.size();
        }
        
        // Set��JavaDoc
        public boolean isEmpty(){
            return AbstractCacheMapService.this.isEmpty();
        }
        
        // Set��JavaDoc
        public boolean contains(Object o){
            if(o instanceof Map.Entry){
                final Map.Entry entry = (Map.Entry)o;
                return AbstractCacheMapService.this.containsKey(entry.getKey());
            }
            return false;
        }
        
        // Set��JavaDoc
        public boolean containsAll(Collection c){
            final Iterator keys = c.iterator();
            boolean result = true;
            while(keys.hasNext()){
                result &= contains(keys.next());
            }
            return result;
        }
        
        // Set��JavaDoc
        public Iterator iterator(){
            return new EntryIterator();
        }
        
        // Set��JavaDoc
        public Object[] toArray(){
            if(references == null || references.size() == 0){
                return new Object[0];
            }
            Entry[] result = null;
            synchronized(references){
                result = new Entry[references.size()];
                int count = 0;
                final Iterator keys = references.keySet().iterator();
                while(keys.hasNext()){
                    result[count++] = new Entry(keys.next());
                }
            }
            return result;
        }
        
        // Set��JavaDoc
        public Object[] toArray(Object[] a){
            if(references == null || references.size() == 0){
                if(a.length == 0){
                    return a;
                }else{
                    for(int i = 0; i < a.length; i++){
                        a[i] = null;
                    }
                    return a;
                }
            }
            Object[] result = null;
            synchronized(references){
                final int length = references.size();
                if(a.length >= length){
                    result = a;
                }else{
                    result = (Object[])Array.newInstance(
                        a.getClass().getComponentType(),
                        length
                    );
                }
                int count = 0;
                final Iterator keys = references.keySet().iterator();
                while(keys.hasNext()){
                    result[count++] = new Entry(keys.next());
                }
            }
            return result;
        }
        
        // Set��JavaDoc
        public boolean add(Object o){
            throw new UnsupportedOperationException();
        }
        
        // Set��JavaDoc
        public boolean addAll(Collection c){
            throw new UnsupportedOperationException();
        }
        
        // Set��JavaDoc
        public boolean remove(Object o){
            if(references == null || references.size() == 0){
                return false;
            }
            if(o instanceof Map.Entry){
                final Map.Entry entry = (Map.Entry)o;
                final Object removed
                     = AbstractCacheMapService.this.remove(entry.getKey());
                return removed != null;
            }
            return false;
        }
        
        // Set��JavaDoc
        public boolean removeAll(Collection c){
            final Iterator entries = c.iterator();
            boolean result = false;
            while(entries.hasNext()){
                result |= remove(entries.next());
            }
            return result;
        }
        
        // Set��JavaDoc
        public boolean retainAll(Collection c){
            if(references == null || references.size() == 0){
                return false;
            }
            boolean result = false;
            synchronized(references){
                final Object[] keys = references.keySet().toArray();
                final Object[] entries = c.toArray();
                final Set retainsKeys = new HashSet();
                for(int i = 0; i < entries.length; i++){
                    if(entries[i] instanceof Map.Entry){
                        final Map.Entry entry = (Map.Entry)entries[i];
                        retainsKeys.add(entry.getKey());
                    }
                }
                for(int i = 0; i < keys.length; i++){
                    if(!retainsKeys.contains(keys[i])){
                        final Object removed = AbstractCacheMapService.
                            this.remove(keys[i]);
                        result |= removed != null;
                    }
                }
            }
            return result;
        }
        
        // Set��JavaDoc
        public void clear(){
            AbstractCacheMapService.this.clear();
        }
        
        // Set��JavaDoc
        public boolean equals(Object o){
            if(o == null){
                return false;
            }
            if(o == this){
                return true;
            }
            if(o instanceof EntrySet){
                final EntrySet cmp = (EntrySet)o;
                if(collection == null){
                    return cmp.collection == null;
                }else if(collection.equals(cmp.collection)){
                    return true;
                }
            }
            return false;
        }
        
        // Set��JavaDoc
        public int hashCode(){
            return collection == null ? 0 : collection.hashCode();
        }
    }
    
    /**
     * �L���b�V���}�b�v�̃}�b�v�G���g���J��Ԃ��B<p>
     *
     * @author M.Takata
     * @see AbstractCacheMapService.EntrySet#iterator()
     */
    protected class EntryIterator
     implements Iterator, java.io.Serializable{
        
        private static final long serialVersionUID = -5858884191202944380L;
        
        private Iterator iterator;
        private Object current;
        
        /**
         * �C���X�^���X�𐶐�����B<p>
         */
        public EntryIterator(){
            if(references != null){
                iterator = new HashSet(references.keySet()).iterator();
            }
        }
        
        // Iterator��JavaDoc
        public boolean hasNext(){
            return iterator == null ? false : iterator.hasNext();
        }
        
        // Iterator��JavaDoc
        public Object next(){
            if(iterator == null){
                throw new NoSuchElementException();
            }
            current = null;
            current = iterator.next();
            return new Entry(current);
        }
        
        // Iterator��JavaDoc
        public void remove(){
            if(current == null){
                throw new IllegalStateException();
            }
            AbstractCacheMapService.this.remove(current);
            iterator.remove();
            if(!iterator.hasNext()){
                current = null;
            }
        }
    }
    
    /**
     * �L���b�V���}�b�v�̃}�b�v�G���g��<p>
     *
     * @author M.Takata
     * @see AbstractCacheMapService.EntryIterator#next()
     */
    protected class Entry implements Map.Entry, java.io.Serializable{
        
        private static final long serialVersionUID = -2047042147063848911L;
        
        private Object key;
        
        /**
         * �C���X�^���X�𐶐�����B<p>
         */
        public Entry(Object key){
            this.key = key;
        }
        
        // Map.Entry��JavaDoc
        public Object getKey(){
            return key;
        }
        
        // Map.Entry��JavaDoc
        public Object getValue(){
            return AbstractCacheMapService.this.get(key);
        }
        
        // Map.Entry��JavaDoc
        public Object setValue(Object value){
            final Object result = AbstractCacheMapService.this.remove(key);
            AbstractCacheMapService.this.put(key, value);
            return result;
        }
        
        // Map.Entry��JavaDoc
        public boolean equals(Object o){
            if(o == null){
                return false;
            }
            if(o == this){
                return true;
            }
            if(o instanceof Entry){
                final Entry cmp = (Entry)o;
                if(key.equals(cmp.key)){
                    return true;
                }
            }
            return false;
        }
        
        // Map.Entry��JavaDoc
        public int hashCode(){
            return key.hashCode();
        }
    }
}
