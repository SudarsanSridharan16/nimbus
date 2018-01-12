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
 * First In First Out���ӂ�A���S���Y���T�[�r�X�B<p>
 * ������o��(First In First Out)�ł��ӂ��L���b�V���I�u�W�F�N�g�����肷��OverflowAlgorithm�ł���B<br>
 * �ȉ��ɁAFIFO�ł��ӂ�ΏۂƂȂ�L���b�V���I�u�W�F�N�g�����肷�邠�ӂ�A���S���Y���T�[�r�X�̃T�[�r�X��`��������B<br>
 * <pre>
 * &lt;?xml version="1.0" encoding="Shift_JIS"?&gt;
 * 
 * &lt;server&gt;
 *     
 *     &lt;manager name="Sample"&gt;
 *         
 *         &lt;service name="FIFOOverflowAlgorithm"
 *                  code="jp.ossc.nimbus.service.cache.FIFOOverflowAlgorithmService"/&gt;
 *         
 *     &lt;/manager&gt;
 *     
 * &lt;/server&gt;
 * </pre>
 *
 * @author M.Takata
 */
public class FIFOOverflowAlgorithmService extends ServiceBase
 implements OverflowAlgorithm, CacheRemoveListener, java.io.Serializable,
            FIFOOverflowAlgorithmServiceMBean{
    
    private static final long serialVersionUID = -8206003382892774163L;
    
    /**
     * �L���b�V������Ă���L���b�V���Q�Ƃ̃��X�g�B<p>
     */
    private List referenceList;
    
    // FIFOOverflowAlgorithmServiceMBean��JavaDoc
    public int size(){
        return referenceList == null ? 0 : referenceList.size();
    }
    
    /**
     * �T�[�r�X�̐����������s���B<p>
     * �C���X�^���X�ϐ�������������B<br>
     *
     * @exception Exception �T�[�r�X�̐��������Ɏ��s�����ꍇ
     */
    public void createService() throws Exception{
        referenceList = Collections.synchronizedList(new ArrayList());
    }
    
    /**
     * �T�[�r�X�̔j���������s���B<p>
     * {@link #reset()}���Ăяo���B<br>
     * �C���X�^���X�ϐ���j������B<br>
     *
     * @exception Exception �T�[�r�X�̔j�������Ɏ��s�����ꍇ
     */
    public void destroyService() throws Exception{
        reset();
        referenceList = null;
    }
    
    /**
     * �L���b�V���Q�Ƃ�ǉ�����B<p>
     * �����œn���ꂽ�L���b�V���Q�Ƃ�ێ�����B�����ɁA{@link CachedReference#addCacheRemoveListener(CacheRemoveListener)}�ŁA{@link CacheRemoveListener}�Ƃ��Ď������g��o�^����B<br>
     *
     * @param ref �L���b�V���Q��
     */
    public void add(CachedReference ref){
        if(referenceList == null || ref == null){
            return;
        }
        synchronized(referenceList){
            if(!referenceList.contains(ref)){
                referenceList.add(ref);
                ref.addCacheRemoveListener(this);
            }
        }
    }
    
    /**
     * �L���b�V���Q�Ƃ��폜����B<p>
     * �����œn���ꂽ�L���b�V���Q�Ƃ�����ŕێ����Ă���ꍇ�́A�j������B�����ɁA{@link CachedReference#removeCacheRemoveListener(CacheRemoveListener)}�ŁA{@link CacheRemoveListener}�Ƃ��Ď������g��o�^��������B<br>
     *
     * @param ref �L���b�V���Q��
     */
    public void remove(CachedReference ref){
        if(referenceList == null || ref == null){
            return;
        }
        synchronized(referenceList){
            if(referenceList.contains(ref)){
                referenceList.remove(ref);
                ref.removeCacheRemoveListener(this);
            }
        }
    }
    
    /**
     * ������o���ŁA���ӂ��L���b�V���Q�Ƃ����肷��B<p>
     * {@link #add(CachedReference)}�œn���ꂽ�L���b�V���Q�Ƃ̒�����A�ŏ��ɒǉ����ꂽ�L���b�V���Q�Ƃ��A���ӂ�L���b�V���Q�ƂƂ��ĕԂ��B<br>
     *
     * @return ������o���̃A���S���Y���Ō��肳�ꂽ���ӂ�L���b�V���Q��
     */
    public CachedReference overflow(){
        if(referenceList == null){
            return null;
        }
        synchronized(referenceList){
            if(referenceList.size() != 0){
                return (CachedReference)referenceList.remove(0);
            }
            return null;
        }
    }
    
    /**
     * ������o���ŁA���ӂ��L���b�V���Q�Ƃ����肷��B<p>
     * {@link #add(CachedReference)}�œn���ꂽ�L���b�V���Q�Ƃ̒�����A�ŏ��ɒǉ����ꂽ�L���b�V���Q�Ƃ��A���ӂ�L���b�V���Q�ƂƂ��ĕԂ��B<br>
     *
     * @param size ���ӂꐔ
     * @return ������o���̃A���S���Y���Ō��肳�ꂽ���ӂ�L���b�V���Q��
     */
    public CachedReference[] overflow(int size){
        if(referenceList == null || referenceList.size() == 0){
            return null;
        }
        synchronized(referenceList){
            if(referenceList.size() != 0){
                final CachedReference[] result = new CachedReference[Math.min(referenceList.size(), size)];
                for(int i = 0; i < result.length; i++){
                    result[i] = (CachedReference)referenceList.remove(0);
                }
                return result;
            }
            return null;
        }
    }
    
    /**
     * ���ӂ�A���S���Y�������s���邽�߂ɕێ����Ă����������������B<p>
     * {@link #add(CachedReference)}�œn���ꂽ�L���b�V���Q�Ƃ�S�Ĕj������B<br>
     */
    public void reset(){
        if(referenceList != null){
            referenceList.clear();
        }
    }
    
    /**
     * �L���b�V������폜���ꂽ�L���b�V���Q�Ƃ̒ʒm���󂯂�B<p>
     * {@link #remove(CachedReference)}���Ăяo���B<br>
     *
     * @param ref �L���b�V������폜���ꂽ�L���b�V���Q��
     */
    public void removed(CachedReference ref){
        remove(ref);
    }
}
