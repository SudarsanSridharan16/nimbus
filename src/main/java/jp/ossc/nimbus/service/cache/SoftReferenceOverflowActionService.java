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
import java.lang.ref.*;

import jp.ossc.nimbus.core.*;
import jp.ossc.nimbus.daemon.*;

/**
 * �\�t�g�Q�Ƃ��ӂꓮ��T�[�r�X�B<p>
 * ���ӂꂽ�L���b�V���I�u�W�F�N�g���A���Q�Ƃ���\�t�g�Q�ƂɕύX���A�����ɉi�����L���b�V���ɉi�������邠�ӂꓮ��ł���B<br>
 * �ȉ��ɁA�i�����L���b�V���Ƃ��ăt�@�C���L���b�V�����g�p����\�t�g�Q�Ƃ��ӂꓮ��T�[�r�X�̃T�[�r�X��`��������B<br>
 * <pre>
 * &lt;?xml version="1.0" encoding="Shift_JIS"?&gt;
 * 
 * &lt;server&gt;
 *     
 *     &lt;manager name="Sample"&gt;
 *         
 *         &lt;service name="SoftReferenceOverflowAction"
 *                  code="jp.ossc.nimbus.service.cache.SoftReferenceOverflowActionService"&gt;
 *             &lt;attribute name="PersistCacheServiceName"&gt;#FileCache&lt;/attribute&gt;
 *             &lt;depends&gt;FileCache&lt;/depends&gt;
 *         &lt;/service&gt;
 *         
 *         &lt;service name="FileCache"
 *                  code="jp.ossc.nimbus.service.cache.FileCacheService"/&gt;
 *         
 *     &lt;/manager&gt;
 *     
 * &lt;/server&gt;
 * </pre>
 *
 * @author M.Takata
 */
public class SoftReferenceOverflowActionService extends ServiceBase
 implements OverflowAction, LinkedReference, CacheRemoveListener,
            CacheChangeListener, DaemonRunnable, java.io.Serializable,
            SoftReferenceOverflowActionServiceMBean{
    
    private static final long serialVersionUID = 6278424846147595060L;
    
    // ���b�Z�[�WID��`
    private static final String SROA_ = "SROA_";
    private static final String SROA_0 = SROA_ + 0;
    private static final String SROA_00 = SROA_0 + 0;
    private static final String SROA_000 = SROA_00 + 0;
    private static final String SROA_0000 = SROA_000 + 0;
    private static final String SROA_00001 = SROA_0000 + 1;
    private static final String SROA_00002 = SROA_0000 + 2;
    
    private ServiceName cacheServiceName;
    private Cache cache;
    
    private ServiceName cacheMapServiceName;
    private CacheMap cacheMap;
    
    private OverflowController controller;
    
    private Map references;
    
    private ReferenceQueue refQueue;
    
    /**
     * {@link Daemon}�I�u�W�F�N�g�B<p>
     */
    protected Daemon daemon;
    
    // SoftReferenceOverflowActionServiceMBean��JavaDoc
    public void setPersistCacheServiceName(ServiceName name){
        cacheServiceName = name;
    }
    
    // SoftReferenceOverflowActionServiceMBean��JavaDoc
    public ServiceName getPersistCacheServiceName(){
        return cacheServiceName;
    }
    
    // SoftReferenceOverflowActionServiceMBean��JavaDoc
    public void setPersistCacheMapServiceName(ServiceName name){
        cacheMapServiceName = name;
    }
    
    // SoftReferenceOverflowActionServiceMBean��JavaDoc
    public ServiceName getPersistCacheMapServiceName(){
        return cacheMapServiceName;
    }
    
    /**
     * Cache��ݒ肷��B
     */
    public void setCache(Cache cache) {
        this.cache = cache;
    }
    /**
     * CacheMap��ݒ肷��B
     */
    public void setCacheMap(CacheMap cacheMap) {
        this.cacheMap = cacheMap;
    }

    /**
     * �T�[�r�X�̐����������s���B<p>
     * �C���X�^���X�ϐ��̏��������s���B
     *
     * @exception Exception �T�[�r�X�̐��������Ɏ��s�����ꍇ
     */
    public void createService() throws Exception{
        references = Collections.synchronizedMap(new HashMap());
        refQueue = new ReferenceQueue();
        daemon = new Daemon(this);
        daemon.setName("Nimbus SoftReferenceOverflowActionDaemon " + getServiceNameObject());
    }
    
    /**
     * �T�[�r�X�̊J�n�������s���B<p>
     * �ޔ��̃L���b�V���T�[�r�X�̎擾�A�y�у\�t�g�Q�Ƃɂ����L���b�V���I�u�W�F�N�g���K�x�[�W�R���N�g�����̂��Ď�����f�[�����X���b�h�̊J�n���s���B<br>
     *
     * @exception Exception �T�[�r�X�̊J�n�����Ɏ��s�����ꍇ
     */
    public void startService() throws Exception{
        if(cacheServiceName != null){
            cache = (Cache)ServiceManagerFactory
                .getServiceObject(cacheServiceName);
        }
        if(cacheMapServiceName != null){
            cacheMap = (CacheMap)ServiceManagerFactory
                .getServiceObject(cacheMapServiceName);
        }
        
        // �f�[�����N��
        daemon.start();
    }
    
    /**
     * �T�[�r�X�̒�~�������s���B<p>
     * �ޔ��̃L���b�V���T�[�r�X�Q�Ƃ̊J���A�y�у\�t�g�Q�Ƃɂ����L���b�V���I�u�W�F�N�g���K�x�[�W�R���N�g�����̂��Ď�����f�[�����X���b�h�̒�~���s���B<br>
     *
     * @exception Exception �T�[�r�X�̒�~�����Ɏ��s�����ꍇ
     */
    public void stopService() throws Exception{
        
        // �f�[������~
        daemon.stop();
        
        cache = null;
        cacheMap = null;
    }
    
    /**
     * �T�[�r�X�̔j���������s���B<p>
     * �C���X�^���X�ϐ��̊J�����s���B
     *
     * @exception Exception �T�[�r�X�̔j�������Ɏ��s�����ꍇ
     */
    public void destroyService() throws Exception{
        references = null;
        refQueue = null;
        daemon = null;
    }
    
    // OverflowAction��JavaDoc
    public void setOverflowController(OverflowController controller){
        this.controller = controller;
    }
    
    /**
     * ���ӂꂽ�L���b�V���I�u�W�F�N�g�����Q�Ƃ���\�t�g�Q�ƂɕύX����Ɠ����ɁA�i�����L���b�V���ɑޔ�����B<p>
     *
     * @param validator ���ӂꌟ�؂��s����OverflowValidator
     * @param algorithm ���ӂ�L���b�V���Q�Ƃ����肵��OverflowAlgorithm
     * @param ref ���ӂꂽ�L���b�V���Q��
     */
    public void action(
        OverflowValidator validator,
        OverflowAlgorithm algorithm,
        CachedReference ref
    ){
        if(ref == null || references == null){
            return;
        }
        synchronized(ref){
            final Object obj = ref.get(this, false);
            if(obj == null){
                return;
            }
            CachedReference persistRef = null;
            if(ref instanceof KeyCachedReference && cacheMap != null){
                final KeyCachedReference keyRef = (KeyCachedReference)ref;
                final Object key = keyRef.getKey();
                if(key != null && obj != null){
                    cacheMap.put(key, obj);
                    persistRef = cacheMap.getCachedReference(key);
                }
            }else if(cache != null){
                if(obj != null){
                    persistRef = cache.add(obj);
                }
            }
            try{
                final CachedReference newRef = new SoftCachedReference(
                    obj,
                    ref,
                    persistRef,
                    refQueue
                );
                references.put(ref, newRef);
                
                ref.addLinkedReference(this);
                ref.addCacheRemoveListener(this);
                ref.addCacheChangeListener(this);
                ref.set(this, null);
                
                if(validator != null){
                    validator.remove(ref);
                }
                if(algorithm != null){
                    algorithm.remove(ref);
                }
            }catch(IllegalCachedReferenceException e){
                getLogger().write(SROA_00001, e);
                if(persistRef != null){
                    persistRef.remove(this);
                }
                return;
            }
            if(persistRef != null && ref.isRemoved()){
                persistRef.remove(this);
            }
        }
    }
    
    /**
     * ���ӂꓮ������s���邽�߂ɕێ����Ă����������������B<p>
     */
    public void reset(){
        if(references != null){
            references.clear();
        }
    }
    
    public int size(){
        return references == null ? 0 : references.size();
    }
    
    /**
     * �\�t�g�Q�Ƃ܂��͉i�����L���b�V������L���b�V���I�u�W�F�N�g���擾����B<p>
     *
     * @param ref �Q�ƌ��̃L���b�V���Q��
     * @return �L���b�V���I�u�W�F�N�g
     */
    public Object get(CachedReference ref){
        if(ref == null || references == null){
            return null;
        }
        synchronized(ref){
            final CachedReference newRef
                 = (CachedReference)references.get(ref);
            Object obj = null;
            if(newRef != null){
                obj = newRef.get(this);
                try{
                    ref.set(this, obj);
                    newRef.remove(this);
                    references.remove(ref);
                    ref.removeLinkedReference(this);
                    ref.removeCacheRemoveListener(this);
                    ref.removeCacheChangeListener(this);
                    if(controller != null){
                        controller.control(ref);
                    }
                }catch(IllegalCachedReferenceException e){
                    getLogger().write(SROA_00002, obj, e);
                }
            }
            return obj;
        }
    }
    
    /**
     * �L���b�V������폜���ꂽ�L���b�V���Q�Ƃ̒ʒm���󂯂�B<p>
     * �폜���ꂽ�L���b�V���Q�ƂɃ����N����\�t�g�L���b�V���Q�Ƃ��폜����B<br>
     *
     * @param ref �L���b�V������폜���ꂽ�L���b�V���Q��
     */
    public void removed(CachedReference ref){
        if(references == null){
            return;
        }
        synchronized(ref){
            if(references.containsKey(ref)){
                final CachedReference newRef
                     = (CachedReference)references.remove(ref);
                newRef.remove(this);
                ref.removeLinkedReference(this);
                ref.removeCacheRemoveListener(this);
                ref.removeCacheChangeListener(this);
            }
        }
    }
    
    /**
     * �L���b�V���Q�Ƃ̃L���b�V���I�u�W�F�N�g���ύX���ꂽ�ʒm���󂯂�B<p>
     * �ύX���ꂽ�L���b�V���Q�ƂɃ����N����\�t�g�L���b�V���Q�Ƃ��폜����B<br>
     *
     * @param ref �ύX���ꂽ�L���b�V���I�u�W�F�N�g�̃L���b�V���Q��
     * @param obj �ύX��̃L���b�V���I�u�W�F�N�g
     */
    public void changed(CachedReference ref, Object obj){
        if(references == null){
            return;
        }
        synchronized(ref){
            if(references != null && references.containsKey(ref)){
                final CachedReference newRef
                     = (CachedReference)references.remove(ref);
                newRef.remove(this);
                ref.removeLinkedReference(this);
                ref.removeCacheRemoveListener(this);
                ref.removeCacheChangeListener(this);
            }
        }
    }
    
    /**
     * �f�[�������J�n�������ɌĂяo�����B<p>
     * 
     * @return ���true��Ԃ�
     */
    public boolean onStart() {
        return true;
    }
    
    /**
     * �f�[��������~�������ɌĂяo�����B<p>
     * 
     * @return ���true��Ԃ�
     */
    public boolean onStop() {
        return true;
    }
    
    /**
     * �f�[���������f�������ɌĂяo�����B<p>
     * 
     * @return ���true��Ԃ�
     */
    public boolean onSuspend() {
        return true;
    }
    
    /**
     * �f�[�������ĊJ�������ɌĂяo�����B<p>
     * 
     * @return ���true��Ԃ�
     */
    public boolean onResume() {
        return true;
    }
    
    /**
     * �K�x�[�W���ꂽ�\�t�g�Q�Ƃ��o�^�����Q�ƃL���[���L���[�҂����āA�P���o���ĕԂ��B<p>
     * 
     * @param ctrl DaemonControl�I�u�W�F�N�g
     * @return �K�x�[�W���ꂽ�\�t�g�Q��
     */
    public Object provide(DaemonControl ctrl){
        if(refQueue == null){
            return null;
        }
        try{
            return refQueue.remove();
        }catch(InterruptedException e){
            return null;
        }
    }
    
    /**
     * �\�t�g�Q�Ƃ��K�x�[�W�����ƁA�������̎g�p�󋵂��ύX����Ă���\�������邽�߁A���ӂꐧ������s����B<p>
     *
     * @param dequeued �K�x�[�W���ꂽ�\�t�g�Q��
     * @param ctrl DaemonControl�I�u�W�F�N�g
     */
    public void consume(Object dequeued, DaemonControl ctrl){
        if(dequeued == null || controller == null){
            return;
        }
        CachedSoftReference ref = (CachedSoftReference)dequeued;
        if(ref.getPersistCachedReference() == null){
            ref.getSourceCachedReference().remove(this);
        }
        controller.control(null);
    }
    
    /**
     * �������Ȃ��B<p>
     */
    public void garbage(){
    }
    
    /**
     * �\�t�g�L���b�V���Q�ƁB<p>
     * �L���b�V���I�u�W�F�N�g���\�t�g�Q�Ƃɂ���Ɠ����ɁA�i�����L���b�V���ŊǗ�����L���b�V���Q�Ƃł���B<br>
     *
     * @author M.Takata
     */
    protected static class SoftCachedReference extends DefaultCachedReference
     implements java.io.Serializable{
        
        private static final long serialVersionUID = -6567323403396424209L;
        
        /**
         * �i�����L���b�V���ɃL���b�V�������L���b�V���I�u�W�F�N�g�̃L���b�V���Q�ƁB<p>
         */
        protected CachedReference persistRef;
        
        /**
         * �L���b�V���I�u�W�F�N�g���\�t�g�Q�Ƃɂ���Ɠ����ɁA�i�����L���b�V���ŊǗ�����L���b�V���Q�Ƃ𐶐�����B<p>
         *
         * @param obj �L���b�V���I�u�W�F�N�g
         * @param source ���ӂ�ΏۂƂȂ����L���b�V���I�u�W�F�N�g�̃L���b�V���Q��
         * @param persist �i�����L���b�V���ɃL���b�V�������L���b�V���I�u�W�F�N�g�̃L���b�V���Q��
         * @param refQueue �Q�ƃL���[
         */
        public SoftCachedReference(Object obj, CachedReference source, CachedReference persist, ReferenceQueue refQueue){
            super(new CachedSoftReference(source, persist, obj, refQueue));
            persistRef = persist;
        }
        
        /**
         * �L���b�V�����ꂽ�I�u�W�F�N�g���擾����B<p>
         * ��������true�̏ꍇ�́A{@link #addCacheAccessListener(CacheAccessListener)}�œo�^���ꂽ{@link CacheAccessListener}�ɒʒm����B�A���A�������œn���ꂽ�Ăяo�����I�u�W�F�N�g���ʒm���CacheAccessListener�̃C���X�^���X�Ɠ������ꍇ�́A�ʒm���Ȃ��B<br>
         * ���g���ێ�����\�t�g�Q�Ƃ��K�x�[�W����Ă��Ȃ��ꍇ�́A�����Ԃ��B�K�x�[�W����Ă���ꍇ�́A�i�����L���b�V������擾���ĕԂ��B�i�����L���b�V��������擾�ł��Ȃ��ꍇ�́A{@link #addLinkedReference(LinkedReference)}�œo�^���ꂽ{@link LinkedReference}����擾�����݂�B<br>
         *
         * @param source �L���b�V�����擾���邱�̃��\�b�h�̌Ăяo�����I�u�W�F�N�g
         * @param notify �L���b�V���A�N�Z�X���X�i�ɒʒm����ꍇ��true
         * @return �L���b�V���I�u�W�F�N�g
         */
        public Object get(Object source, boolean notify){
            Object obj = ((SoftReference)cacheObj).get();
            if(obj == null && persistRef != null){
                obj = persistRef.get(this, notify);
            }
            if(obj == null){
                obj = getLinkedObject();
            }
            return obj;
        }
        
        /**
         * �L���b�V���I�u�W�F�N�g��ݒ肷��B<p>
         * �T�|�[�g���Ȃ��B<br>
         *
         * @param source �L���b�V���I�u�W�F�N�g��ύX���邱�̃��\�b�h�̌Ăяo�����I�u�W�F�N�g
         * @param obj �ݒ肷��L���b�V���I�u�W�F�N�g
         * @exception UnsupportedOperationException ���T�|�[�g�̂��ߕK��throw����
         */
        public void set(Object source, Object obj){
            throw new UnsupportedOperationException();
        }
        
        /**
         * �L���b�V���I�u�W�F�N�g���폜����B<p>
         * �\�t�g�Q�ƂƁA�i�����L���b�V���̗������폜����B<br>
         * {@link #addCacheRemoveListener(CacheRemoveListener)}�œo�^���ꂽ{@link CacheRemoveListener}�ɒʒm����B�A���A�������œn���ꂽ�Ăяo�����I�u�W�F�N�g���ʒm���CacheChangeListener�̃C���X�^���X�Ɠ������ꍇ�́A�ʒm���Ȃ��B<br>
         *
         * @param source �L���b�V���I�u�W�F�N�g���폜���邱�̃��\�b�h�̌Ăяo�����I�u�W�F�N�g
         */
        public void remove(Object source){
            super.remove(source);
            if(persistRef != null){
                persistRef.remove(this);
                persistRef = null;
            }
        }
    }
    
    /**
     * �L���b�V���\�t�g�Q�ƁB<p>
     * �\�t�g�Q�Ƃɂ����L���b�V���Q�ƂƁA����ɂ��i�������ꂽ�i�����L���b�V���Q�Ƃ�ێ�����B<br>
     *
     * @author M.Takata
     */
    protected static class CachedSoftReference extends SoftReference{
        
        /**
         * �\�t�g�Q�Ƃɂ����L���b�V���Q�ƁB<p>
         */
        protected CachedReference sourceRef;
        
        /**
         * �i�������ꂽ�i�����L���b�V���Q�ƁB<p>
         */
        protected CachedReference persistRef;
        
        /**
         * �C���X�^���X�𐶐�����B<p>
         *
         * @param source ���ӂ�ΏۂƂȂ����L���b�V���I�u�W�F�N�g�̃L���b�V���Q��
         * @param persist �i�����L���b�V���ɃL���b�V�������L���b�V���I�u�W�F�N�g�̃L���b�V���Q��
         * @param obj �L���b�V���I�u�W�F�N�g
         * @param refQueue �Q�ƃL���[
         */
        public CachedSoftReference(
            CachedReference source,
            CachedReference persist,
            Object obj,
            ReferenceQueue refQueue
        ){
            super(obj, refQueue);
            sourceRef = source;
            persistRef = persist;
        }
        
        /**
         * �\�t�g�Q�Ƃɂ����L���b�V���Q�Ƃ��擾����B<p>
         * 
         * @return �L���b�V���Q��
         */
        public CachedReference getSourceCachedReference(){
            return sourceRef;
        }
        
        /**
         * �i�������ꂽ�i�����L���b�V���Q�Ƃ��擾����B<p>
         * 
         * @return �L���b�V���Q��
         */
        public CachedReference getPersistCachedReference(){
            return persistRef;
        }
    }
}
