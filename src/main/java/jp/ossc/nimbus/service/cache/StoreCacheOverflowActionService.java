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
 * �X�g�A�L���b�V�����ӂꓮ��T�[�r�X�B<p>
 * ���ӂꂽ�L���b�V���I�u�W�F�N�g���A�ʂ̃L���b�V���ɑޔ����邠�ӂꓮ��ł���B<br>
 * �ȉ��ɁA���ӂꂽ�L���b�V���I�u�W�F�N�g���t�@�C���L���b�V���ɑޔ�����X�g�A�L���b�V�����ӂꓮ��T�[�r�X�̃T�[�r�X��`��������B<br>
 * <pre>
 * &lt;?xml version="1.0" encoding="Shift_JIS"?&gt;
 * 
 * &lt;server&gt;
 *     
 *     &lt;manager name="Sample"&gt;
 *         
 *         &lt;service name="StoreCacheOverflowAction"
 *                  code="jp.ossc.nimbus.service.cache.StoreCacheOverflowActionService"&gt;
 *             &lt;attribute name="CacheServiceName"&gt;#FileCache&lt;/attribute&gt;
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
public class StoreCacheOverflowActionService extends ServiceBase
 implements OverflowAction, LinkedReference, CacheRemoveListener,
            CacheChangeListener, java.io.Serializable,
            StoreCacheOverflowActionServiceMBean{
    
    private static final long serialVersionUID = 7281680512746664647L;
    
    // ���b�Z�[�WID��`
    private static final String SCOA_ = "SCOA_";
    private static final String SCOA_0 = SCOA_ + 0;
    private static final String SCOA_00 = SCOA_0 + 0;
    private static final String SCOA_000 = SCOA_00 + 0;
    private static final String SCOA_0000 = SCOA_000 + 0;
    private static final String SCOA_00001 = SCOA_0000 + 1;
    private static final String SCOA_00002 = SCOA_0000 + 2;
    
    private ServiceName cacheServiceName;
    private Cache cache;
    
    private ServiceName cacheMapServiceName;
    private CacheMap cacheMap;
    
    private OverflowController controller;
    
    private MemoryCacheService defaultCache;
    
    private Map references;
    
    // StoreCacheOverflowActionServiceMBean��JavaDoc
    public void setCacheServiceName(ServiceName name){
        cacheServiceName = name;
    }
    // StoreCacheOverflowActionServiceMBean��JavaDoc
    public ServiceName getCacheServiceName(){
        return cacheServiceName;
    }
    
    // StoreCacheOverflowActionServiceMBean��JavaDoc
    public void setCacheMapServiceName(ServiceName name){
        cacheMapServiceName = name;
    }
    // StoreCacheOverflowActionServiceMBean��JavaDoc
    public ServiceName getCacheMapServiceName(){
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
    }
    
    /**
     * �T�[�r�X�̊J�n�������s���B<p>
     * �ޔ��̃L���b�V���T�[�r�X�̎擾���s���B<br>
     *
     * @exception Exception �T�[�r�X�̊J�n�����Ɏ��s�����ꍇ
     */
    public void startService() throws Exception{
        if(cacheServiceName != null){
            cache = (Cache)ServiceManagerFactory
                .getServiceObject(cacheServiceName);
        }else{
            cache = getDefaultCacheService();
        }
        if(cacheMapServiceName != null){
            cacheMap = (CacheMap)ServiceManagerFactory
                .getServiceObject(cacheMapServiceName);
        }
    }
    
    /**
     * �T�[�r�X�̒�~�������s���B<p>
     * �ޔ��̃L���b�V���T�[�r�X�Q�Ƃ̊J�����s���B<br>
     *
     * @exception Exception �T�[�r�X�̒�~�����Ɏ��s�����ꍇ
     */
    public void stopService() throws Exception{
        if(defaultCache != null && cache == defaultCache){
            defaultCache.stop();
        }
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
        if(defaultCache != null){
            defaultCache.destroy();
            defaultCache = null;
        }
        references = null;
    }
    
    /**
     * �ޔ��̃L���b�V���T�[�r�X���ݒ肳��Ă��Ȃ��ꍇ�̃f�t�H���g�̃L���b�V���T�[�r�X���擾����B<p>
     *
     * @return �f�t�H���g�̃L���b�V���T�[�r�X�i{@link MemoryCacheService}�j
     * @exception Exception �f�t�H���g�̃L���b�V���T�[�r�X�̐����E�J�n�Ɏ��s�����ꍇ
     */
    protected Cache getDefaultCacheService() throws Exception{
        if(defaultCache == null){
            final MemoryCacheService c = new MemoryCacheService();
            c.create();
            c.start();
            defaultCache = c;
        }else if(defaultCache.getState() != STARTED){
            defaultCache.start();
        }
        return defaultCache;
    }
    
    // OverflowAction��JavaDoc
    public void setOverflowController(OverflowController controller){
        this.controller = controller;
    }
    
    /**
     * ���ӂꂽ�L���b�V���I�u�W�F�N�g��ޔ��L���b�V���ɑޔ�����B<p>
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
            CachedReference newRef = null;
            if(ref instanceof KeyCachedReference && cacheMap != null){
                final KeyCachedReference keyRef = (KeyCachedReference)ref;
                final Object key = keyRef.getKey();
                if(key != null && obj != null){
                    cacheMap.put(key, obj);
                    newRef = cacheMap.getCachedReference(key);
                }
            }else{
                newRef = cache.add(obj);
            }
            if(newRef != null){
                try{
                    ref.set(this, null);
                    ref.addLinkedReference(this);
                    ref.addCacheRemoveListener(this);
                    ref.addCacheChangeListener(this);
                    references.put(ref, newRef);
                    if(validator != null){
                        validator.remove(ref);
                    }
                    if(algorithm != null){
                        algorithm.remove(ref);
                    }
                }catch(IllegalCachedReferenceException e){
                    getLogger().write(SCOA_00001, e);
                    newRef.remove(this);
                }
                if(ref.isRemoved()){
                    newRef.remove(this);
                }
            }else{
                if(algorithm != null){
                    algorithm.remove(ref);
                }
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
    
    /**
     * �ޔ��L���b�V������L���b�V���I�u�W�F�N�g���擾����B<p>
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
                    getLogger().write(SCOA_00002, obj, e);
                }
            }
            return obj;
        }
    }
    
    /**
     * �L���b�V������폜���ꂽ�L���b�V���Q�Ƃ̒ʒm���󂯂�B<p>
     * �폜���ꂽ�L���b�V���Q�ƂɃ����N����ޔ��̃L���b�V�����폜����B<br>
     *
     * @param ref �L���b�V������폜���ꂽ�L���b�V���Q��
     */
    public void removed(CachedReference ref){
        if(references == null){
            return;
        }
        synchronized(ref){
            if(references != null && references.containsKey(ref)){
                final CachedReference newRef
                     = (CachedReference)references.remove(ref);
                newRef.remove(this);
            }
        }
    }
    
    /**
     * �L���b�V���Q�Ƃ̃L���b�V���I�u�W�F�N�g���ύX���ꂽ�ʒm���󂯂�B<p>
     * �ύX���ꂽ�L���b�V���Q�ƂɃ����N����ޔ��̃L���b�V�����폜����B<br>
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
}
