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

import jp.ossc.nimbus.core.ServiceBase;

/**
 * �L���b�V���T�C�Y���ӂꌟ�؃T�[�r�X�B<p>
 * �ő�L���b�V�����ł��ӂ�����؂���OverflowValidator�ł���B<br>
 * �ȉ��ɁA�L���b�V���T�C�Y��10�𒴂���Ƃ��ӂ�邠�ӂꌟ�؃T�[�r�X�̃T�[�r�X��`��������B<br>
 * <pre>
 * &lt;?xml version="1.0" encoding="Shift_JIS"?&gt;
 * 
 * &lt;server&gt;
 *     
 *     &lt;manager name="Sample"&gt;
 *         
 *         &lt;service name="CacheSizeOverflowValidator"
 *                  code="jp.ossc.nimbus.service.cache.CacheSizeOverflowValidatorService"&gt;
 *             &lt;attribute name="MaxSize"&gt;10&lt;/attribute&gt;
 *         &lt;/service&gt;
 *         
 *     &lt;/manager&gt;
 *     
 * &lt;/server&gt;
 * </pre>
 *
 * @author M.Takata
 */
public class CacheSizeOverflowValidatorService extends ServiceBase
 implements OverflowValidator, CacheRemoveListener, java.io.Serializable,
            CacheSizeOverflowValidatorServiceMBean{
    
    private static final long serialVersionUID = -2810585852541528435L;
    
    /**
     * �ő�L���b�V�����B<p>
     */
    private int maxSize;
    
    /**
     * ���ӂ�臒l�B<p>
     */
    private int overflowThreshold;
    
    /**
     * �L���b�V���Q�Ƃ̏W���B<p>
     */
    private Set references;
    
    // CacheSizeOverflowValidatorServiceMBean��JavaDoc
    public int size(){
        return references == null ? 0 : references.size();
    }
    
    /**
     * �T�[�r�X�̐����������s���B<p>
     * �C���X�^���X�ϐ��̏��������s���B<br>
     * 
     * @exception Exception �T�[�r�X�̐����Ɏ��s�����ꍇ
     */
    public void createService() throws Exception{
        references = Collections.synchronizedSet(new HashSet());
    }
    
    /**
     * �T�[�r�X�̔j���������s���B<p>
     * {@link #reset()}���Ăяo���B�܂��A�C���X�^���X�ϐ��̎Q�Ƃ�j������B<br>
     *
     * @exception Exception �T�[�r�X�̔j���Ɏ��s�����ꍇ
     */
    public void destroyService() throws Exception{
        reset();
        references = null;
    }
    
    // CacheSizeOverflowValidatorServiceMBean��JavaDoc
    public void setMaxSize(int size) throws IllegalArgumentException{
        if(size < 0){
            throw new IllegalArgumentException("Invalid size : " + size);
        }
        maxSize = size;
    }
    
    // CacheSizeOverflowValidatorServiceMBean��JavaDoc
    public int getMaxSize(){
        return maxSize;
    }
    
    // CacheSizeOverflowValidatorServiceMBean��JavaDoc
    public void setOverflowThreshold(int threshold){
        overflowThreshold = threshold;
    }
    
    // CacheSizeOverflowValidatorServiceMBean��JavaDoc
    public int getOverflowThreshold(){
        return overflowThreshold;
    }
    
    /**
     * �L���b�V�������ő�L���b�V�����𒴂��Ă��邩���؂���B<p>
     * �ő�L���b�V�����́A{@link #setMaxSize(int)}�Őݒ肳�ꂽ�l�B<br>
     *
     * @return �L���b�V�������ő�L���b�V�����𒴂��Ă���ꍇ�A�����Ă��鐔��Ԃ��B�����Ă��Ȃ��ꍇ�́A0��Ԃ�
     */
    public int validate(){
        if(references == null || references.size() == 0){
            return 0;
        }
        if(getState() != STARTED){
            return 0;
        }
        if(getMaxSize() == 0){
            return 0;
        }
        int overflowSize = references.size() - getMaxSize();
        if(overflowSize > 0 && getOverflowThreshold() > 0){
            overflowSize = getMaxSize() - getOverflowThreshold();
        }
        return overflowSize > 0 ? overflowSize : 0;
    }
    
    // OverflowValidator��JavaDoc
    public void add(CachedReference ref){
        if(references == null || ref == null){
            return;
        }
        if(!references.contains(ref)){
            references.add(ref);
            ref.addCacheRemoveListener(this);
        }
    }
    
    // OverflowValidator��JavaDoc
    public void remove(CachedReference ref){
        if(references == null || ref == null){
            return;
        }
        if(references.contains(ref)){
            references.remove(ref);
            ref.removeCacheRemoveListener(this);
        }
    }
    
    // OverflowValidator��JavaDoc
    public void reset(){
        if(references != null){
            final Object[] refs = references.toArray();
            for(int i = 0; i < refs.length; i++){
                final CachedReference ref = (CachedReference)refs[i];
                remove(ref);
            }
        }
    }
    
    /**
     * {@link #add(CachedReference)}�Œǉ����ꂽ{@link CachedReference}�̃L���b�V���I�u�W�F�N�g���폜���ꂽ�ꍇ�ɌĂяo�����B<p>
     * �폜���ꂽ�L���b�V���Q�Ƃ�{@link #remove(CachedReference)}�ŁA����OverflowValidator������폜����B<br>
     *
     * @param ref �폜���ꂽ�L���b�V���I�u�W�F�N�g�̃L���b�V���Q��
     */
    public void removed(CachedReference ref){
        if(references == null){
            return;
        }
        remove(ref);
    }
}
