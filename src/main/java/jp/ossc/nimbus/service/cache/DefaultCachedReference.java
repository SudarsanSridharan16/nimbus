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
import java.io.*;

import jp.ossc.nimbus.core.*;

/**
 * �f�t�H���g�L���b�V���Q�ƁB<p>
 * {@link CachedReference}�̃f�t�H���g�����ł���B<br>
 * �L���b�V���I�u�W�F�N�g�����Q�Ƃŕێ�����B<br>
 *
 * @author M.Takata
 */
public class DefaultCachedReference
 implements CachedReference, Serializable{
    
    private static final long serialVersionUID = 5006344811694728118L;
    
    /**
     * �L���b�V���I�u�W�F�N�g�B<p>
     */
    protected Object cacheObj;
    
    /**
     * �����N�Q�ƃ��X�g�B<p>
     * {@link #addLinkedReference(LinkedReference)}�Œǉ����ꂽ{@link LinkedReference}�̃��X�g�B<br>
     */
    protected transient Set linkedReferences;
    
    /**
     * �L���b�V���폜���X�i�̃��X�g�B<p>
     * {@link #addCacheRemoveListener(CacheRemoveListener)}�Œǉ����ꂽ{@link CacheRemoveListener}�̃��X�g�B<br>
     */
    protected transient Set removeListeners;
    
    /**
     * �L���b�V���A�N�Z�X���X�i�̃��X�g�B<p>
     * {@link #addCacheAccessListener(CacheAccessListener)}�Œǉ����ꂽ{@link CacheAccessListener}�̃��X�g�B<br>
     */
    protected transient Set accessListeners;
    
    /**
     * �L���b�V���ύX���X�i�̃��X�g�B<p>
     * {@link #addCacheChangeListener(CacheChangeListener)}�Œǉ����ꂽ{@link CacheChangeListener}�̃��X�g�B<br>
     */
    protected transient Set changeListeners;
    
    /**
     * �폜�t���O�B<p>
     * �폜���ꂽ�ꍇ�́Atrue�B
     */
    protected boolean isRemoved;
    
    /**
     * �w�肳�ꂽ�I�u�W�F�N�g�̃L���b�V���Q�Ƃ𐶐�����B<p>
     *
     * @param obj �L���b�V���I�u�W�F�N�g
     */
    public DefaultCachedReference(Object obj){
        cacheObj = obj;
    }
    
    // CachedReference��JavaDoc
    public Object get(){
        return get(null, true);
    }
    
    // CachedReference��JavaDoc
    public Object get(Object source){
        return get(source, true);
    }
    
    // CachedReference��JavaDoc
    public Object get(Object source, boolean notify){
        if(notify){
            notifyAccessed(source);
        }
        if(cacheObj == null){
            cacheObj = getLinkedObject();
        }
        return cacheObj;
    }
    
    // CachedReference��JavaDoc
    public void set(Object obj) throws IllegalCachedReferenceException{
        set(null, obj);
    }
    
    // CachedReference��JavaDoc
    public void set(Object source, Object obj)
     throws IllegalCachedReferenceException{
        notifyChange(source, obj);
        cacheObj = obj;
    }
    
    /**
     * �����N�Q�Ƃ���L���b�V���I�u�W�F�N�g���擾����B<p>
     * {@link #addLinkedReference(LinkedReference)}�Œǉ����ꂽ{@link LinkedReference}����A{@link LinkedReference#get(CachedReference)}�ŃL���b�V���I�u�W�F�N�g���擾����B���̖߂�l��null�̏ꍇ�́A�����N�Q�ƃ��X�g�������H���āA�����������J��Ԃ��B�S�Ẵ����N�Q�Ƃ̖߂�l��null�̏ꍇ�́Anull��Ԃ��B<br>
     *
     * @return �L���b�V���I�u�W�F�N�g
     */
    protected Object getLinkedObject(){
        if(linkedReferences == null || linkedReferences.size() == 0){
            return null;
        }
        final Object[] links = linkedReferences.toArray();
        for(int i = 0; i < links.length; i++){
            final Object obj = ((LinkedReference)links[i]).get(this);
            if(obj != null){
                return obj;
            }
        }
        return null;
    }
    
    // CachedReference��JavaDoc
    public void remove(){
        remove(null);
    }
    
    // CachedReference��JavaDoc
    public void remove(Object source){
        notifyRemoved(source);
        cacheObj = null;
        if(linkedReferences != null){
            linkedReferences.clear();
        }
        isRemoved = true;
    }
    
    // CachedReference��JavaDoc
    public boolean isRemoved(){
        return isRemoved;
    }
    
    /**
     * ���̃L���b�V���Q�Ƃ̃L���b�V���I�u�W�F�N�g���폜���ꂽ�����L���b�V���폜���X�i�ɒʒm����B<p>
     * �A���A�ʒm��̃L���b�V���폜���X�i���A�ʒm���I�u�W�F�N�g�Ɠ����C���X�^���X�̏ꍇ�́A�ʒm���Ȃ��B<br>
     *
     * @param source �폜���I�u�W�F�N�g
     */
    protected void notifyRemoved(Object source){
        if(removeListeners == null || removeListeners.size() == 0){
            return;
        }
        final Object[] listeners = removeListeners.toArray();
        for(int i = 0; i < listeners.length; i++){
            final CacheRemoveListener listener
                 = (CacheRemoveListener)listeners[i];
            if(source != listener){
                listener.removed(this);
            }
        }
    }
    
    /**
     * ���̃L���b�V���Q�Ƃ̃L���b�V���I�u�W�F�N�g���A�N�Z�X���ꂽ�����L���b�V���A�N�Z�X���X�i�ɒʒm����B<p>
     * �A���A�ʒm��̃L���b�V���A�N�Z�X���X�i���A�ʒm���I�u�W�F�N�g�Ɠ����C���X�^���X�̏ꍇ�́A�ʒm���Ȃ��B<br>
     *
     * @param source �A�N�Z�X���I�u�W�F�N�g
     */
    protected void notifyAccessed(Object source){
        if(accessListeners == null || accessListeners.size() == 0){
            return;
        }
        final Object[] listeners = accessListeners.toArray();
        for(int i = 0; i < listeners.length; i++){
            final CacheAccessListener listener
                 = (CacheAccessListener)listeners[i];
            if(source != listener){
                listener.accessed(this);
            }
        }
    }
    
    /**
     * ���̃L���b�V���Q�Ƃ̃L���b�V���I�u�W�F�N�g���ύX���ꂽ�����L���b�V���ύX���X�i�ɒʒm����B<p>
     * �A���A�ʒm��̃L���b�V���ύX���X�i���A�ʒm���I�u�W�F�N�g�Ɠ����C���X�^���X�̏ꍇ�́A�ʒm���Ȃ��B<br>
     *
     * @param source �ύX���I�u�W�F�N�g
     * @param obj �ύX��̃L���b�V���I�u�W�F�N�g
     */
    protected void notifyChange(Object source, Object obj){
        if(changeListeners == null || changeListeners.size() == 0){
            return;
        }
        final Object[] listeners = changeListeners.toArray();
        for(int i = 0; i < listeners.length; i++){
            final CacheChangeListener listener
                 = (CacheChangeListener)listeners[i];
            if(source != listener){
                listener.changed(this, obj);
            }
        }
    }
    
    // CachedReference��JavaDoc
    public void addLinkedReference(LinkedReference ref){
        if(linkedReferences == null){
            linkedReferences = Collections.synchronizedSet(new HashSet());
        }
        linkedReferences.add(ref);
    }
    
    // CachedReference��JavaDoc
    public void removeLinkedReference(LinkedReference ref){
        if(linkedReferences == null){
            return;
        }
        linkedReferences.remove(ref);
    }
    
    // CachedReference��JavaDoc
    public void addCacheRemoveListener(CacheRemoveListener listener){
        if(removeListeners == null){
            removeListeners = Collections.synchronizedSet(new HashSet());
        }
        removeListeners.add(listener);
    }
    
    // CachedReference��JavaDoc
    public void removeCacheRemoveListener(CacheRemoveListener listener){
        if(removeListeners == null){
            return;
        }
        removeListeners.remove(listener);
    }
    
    // CachedReference��JavaDoc
    public void addCacheAccessListener(CacheAccessListener listener){
        if(accessListeners == null){
            accessListeners = Collections.synchronizedSet(new HashSet());
        }
        accessListeners.add(listener);
    }
    
    // CachedReference��JavaDoc
    public void removeCacheAccessListener(CacheAccessListener listener){
        if(accessListeners == null){
            return;
        }
        accessListeners.remove(listener);
    }
    
    // CachedReference��JavaDoc
    public void addCacheChangeListener(CacheChangeListener listener){
        if(changeListeners == null){
            changeListeners = Collections.synchronizedSet(new HashSet());
        }
        changeListeners.add(listener);
    }
    
    // CachedReference��JavaDoc
    public void removeCacheChangeListener(CacheChangeListener listener){
        if(changeListeners == null){
            return;
        }
        changeListeners.remove(listener);
    }
    
    private void writeObject(ObjectOutputStream out) throws IOException{
        out.defaultWriteObject();
        writeSet(out, linkedReferences);
        writeSet(out, removeListeners);
        writeSet(out, accessListeners);
        writeSet(out, changeListeners);
    }
    
    protected void writeSet(ObjectOutputStream out, Set set) throws IOException{
        if(set != null && set.size() != 0){
            synchronized(set){
                final Object[] objs = set.toArray();
                final Set outSet = new HashSet();
                for(int i = 0; i < objs.length; i++){
                    final Object obj = objs[i];
                    final ServiceName name = getServiceName(obj);
                    if(name != null){
                        outSet.add(name);
                    }else{
                        outSet.add(obj);
                    }
                }
                out.writeObject(outSet);
            }
        }else{
            out.writeObject(null);
        }
    }
    
    protected ServiceName getServiceName(Object obj){
        if(obj instanceof ServiceBase){
            return ((ServiceBase)obj).getServiceNameObject();
        }else if(obj instanceof Service){
            final Service service = (Service)obj;
            if(service.getServiceManagerName() != null
                 && service.getServiceName() != null){
                return new ServiceName(
                    service.getServiceManagerName(),
                    service.getServiceName()
                );
            }else{
                return null;
            }
        }else{
            return null;
        }
    }
    
    private void readObject(ObjectInputStream in)
     throws IOException, ClassNotFoundException{
        in.defaultReadObject();
        linkedReferences = readSet(in);
        removeListeners = readSet(in);
        accessListeners = readSet(in);
        changeListeners = readSet(in);
    }
    
    protected Set readSet(ObjectInputStream in)
     throws IOException, ClassNotFoundException{
        final Object o = in.readObject();
        final Set set = (Set)o;
        Set result = null;
        if(set != null){
            if(result == null){
                result = Collections.synchronizedSet(new HashSet());
            }
            final Iterator iterator = set.iterator();
            while(iterator.hasNext()){
                final Object obj = iterator.next();
                if(obj instanceof ServiceName){
                    result.add(
                        ServiceManagerFactory.getServiceObject(
                            (ServiceName)obj
                        )
                    );
                }else{
                    result.add(obj);
                }
            }
        }
        return result;
    }
}