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

import jp.ossc.nimbus.core.*;

/**
 * �L���b�V���T�[�r�X���ۃN���X�B<p>
 *
 * @author M.Takata
 */
public abstract class AbstractCacheService extends ServiceBase
 implements CacheRemoveListener, AbstractCacheServiceMBean{
    
    private static final long serialVersionUID = 4327482025283418963L;
    
    /**
     * �L���b�V���Q�Ƃ̏W���B<p>
     */
    protected Set references;
    
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
    
    // AbstractCacheServiceMBean��JavaDoc
    public void setOverflowControllerServiceNames(ServiceName[] names){
        overflowControllerServiceNames = names;
    }
    
    // AbstractCacheServiceMBean��JavaDoc
    public ServiceName[] getOverflowControllerServiceNames(){
        return overflowControllerServiceNames;
    }
    
    // AbstractCacheServiceMBean��JavaDoc
    public void setClearOnStop(boolean isClear){
        isClearOnStop = isClear;
    }
    
    // AbstractCacheServiceMBean��JavaDoc
    public boolean isClearOnStop(){
        return isClearOnStop;
    }
    
    // AbstractCacheServiceMBean��JavaDoc
    public void setClearOnDestroy(boolean isClear){
        isClearOnDestroy = isClear;
    }
    
    // AbstractCacheServiceMBean��JavaDoc
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
        references = Collections.synchronizedSet(new LinkedHashSet());
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
    
    // Cache��JavaDoc
    public CachedReference add(Object obj){
        final CachedReference ref = createCachedReference(obj);
        add(ref);
        return ref;
    }
    
    /**
     * �w�肳�ꂽ�L���b�V���Q�Ƃ�ǉ�����B<p>
     *
     * @param ref �L���b�V���Q��
     */
    protected void add(CachedReference ref){
        if(ref == null || references == null || getState() > STOPPED){
            return;
        }
        ref.addCacheRemoveListener(this);
        references.add(ref);
        if(overflowControllers.size() != 0){
            final Object[] controllers = overflowControllers.toArray();
            for(int i = 0, max = controllers.length; i < max; i++){
                final OverflowController controller
                     = (OverflowController)controllers[i];
                controller.control(ref);
            }
        }
        return;
    }
    
    /**
     * �w�肳�ꂽ�I�u�W�F�N�g�̃L���b�V���Q�Ƃ𐶐�����B<p>
     *
     * @param obj �L���b�V������I�u�W�F�N�g
     * @return �L���b�V���Q��
     */
    protected abstract CachedReference createCachedReference(Object obj);
    
    // Cache��JavaDoc
    public Iterator iterator(){
        return new CachedReferenceIterator();
    }
    
    // Cache��JavaDoc
    public boolean contains(CachedReference ref){
        if(references == null){
            return false;
        }
        return references.contains(ref);
    }
    
    // Cache��JavaDoc
    public boolean containsAll(Collection c){
        if(references == null){
            return false;
        }
        return references.containsAll(c);
    }
    
    // Cache��JavaDoc
    public boolean isEmpty(){
        if(references == null){
            return true;
        }
        return references.isEmpty();
    }
    
    // Cache��JavaDoc
    public boolean remove(CachedReference ref){
        boolean result = false;
        if(references != null){
            result = references.remove(ref);
            if(result){
                ref.remove(this);
            }
        }
        return result;
    }
    
    // Cache��JavaDoc
    public boolean removeAll(Collection c){
        final Iterator refs = c.iterator();
        boolean result = false;
        while(refs.hasNext()){
            final Object obj = refs.next();
            if(obj instanceof CachedReference){
                result |= remove((CachedReference)obj);
            }
        }
        return result;
    }
    
    // Cache��JavaDoc
    public boolean retainAll(Collection c){
        if(references == null){
            return false;
        }
        boolean result = false;
        synchronized(references){
            final Iterator refs = references.iterator();
            while(refs.hasNext()){
                final CachedReference ref = (CachedReference)refs.next();
                if(!c.contains(ref)){
                    result |= remove(ref);
                }
            }
        }
        return result;
    }
    
    // Cache��JavaDoc
    public int size(){
        if(references == null){
            return 0;
        }else{
            return references.size();
        }
    }
    
    // Cache��JavaDoc
    public void clear(){
        if(references != null){
            final Object[] refs = references.toArray();
            for(int i = 0; i < refs.length; i++){
                remove((CachedReference)refs[i]);
            }
        }
    }
    
    // Cache��JavaDoc
    public CachedReference[] toArray(){
        if(references == null){
            return new CachedReference[0];
        }
        return (CachedReference[])references.toArray(
            new CachedReference[references.size()]
        );
    }
    
    // Cache��JavaDoc
    public CachedReference[] toArray(CachedReference[] refs){
        if(references == null){
            return new CachedReference[0];
        }
        return (CachedReference[])references.toArray(refs);
    }
    
    // CacheRemoveListener��JavaDoc
    public void removed(CachedReference ref){
        if(references != null && references.contains(ref)){
            references.remove(ref);
        }
    }
    
    /**
     * �L���b�V���̃L���b�V���Q�ƌJ��Ԃ��B<p>
     *
     * @author M.Takata
     * @see AbstractCacheService#iterator()
     */
    private class CachedReferenceIterator
     implements Iterator, java.io.Serializable{
        
        private static final long serialVersionUID = -6125109804226184416L;
        
        private Iterator iterator;
        private Object current;
        
        /**
         * �C���X�^���X�𐶐�����B<p>
         */
        public CachedReferenceIterator(){
            if(references != null){
                iterator = new LinkedHashSet(references).iterator();
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
            iterator.remove();
            AbstractCacheService.this.remove((CachedReference)current);
            if(!iterator.hasNext()){
                current = null;
            }
        }
    }
}
