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

import jp.ossc.nimbus.core.ServiceBase;

/**
 * Least Frequency Used ���ӂ�A���S���Y���T�[�r�X�B<p>
 * �ȉ��ɁALFU�ł��ӂ�ΏۂƂȂ�L���b�V���I�u�W�F�N�g�����肷�邠�ӂ�A���S���Y���T�[�r�X�̃T�[�r�X��`��������B<br>
 * <pre>
 * &lt;?xml version="1.0" encoding="Shift_JIS"?&gt;
 * 
 * &lt;server&gt;
 *     
 *     &lt;manager name="Sample"&gt;
 *         
 *         &lt;service name="LFUOverflowAlgorithm"
 *                  code="jp.ossc.nimbus.service.cache.LFUOverflowAlgorithmService"/&gt;
 *         
 *     &lt;/manager&gt;
 *     
 * &lt;/server&gt;
 * </pre>
 *
 * @author M.Takata
 */
public class LFUOverflowAlgorithmService extends ServiceBase
 implements OverflowAlgorithm, CacheRemoveListener, CacheAccessListener,
            java.io.Serializable, LFUOverflowAlgorithmServiceMBean{
    
    private static final long serialVersionUID = -8742917099381213489L;
    
    private Map referenceMap;
    private List referenceList;
    private boolean cachedRatioCompare = false;
    private long ratioUnitTime = 1000;
    private long overflowCount;
    private long overflowCachedTime;
    private long sortCurrentTime;
    
    // LFUOverflowAlgorithmServiceMBean��JavaDoc
    public int size(){
        return referenceList == null ? 0 : referenceList.size();
    }
    
    // LFUOverflowAlgorithmServiceMBean��JavaDoc
    public int getMaximumReferenceCount(){
        if(referenceMap == null){
            return 0;
        }
        synchronized(referenceMap){
            if(referenceMap.size() == 0){
                return 0;
            }
            sortCurrentTime = System.currentTimeMillis();
            Collections.sort(referenceList);
            CounterCachedReference couterRef
                 = (CounterCachedReference)referenceList.get(referenceList.size() - 1);
            return couterRef.getCount();
        }
    }
    
    // LFUOverflowAlgorithmServiceMBean��JavaDoc
    public int getMinimumReferenceCount(){
        if(referenceMap == null){
            return 0;
        }
        synchronized(referenceMap){
            if(referenceMap.size() == 0){
                return 0;
            }
            sortCurrentTime = System.currentTimeMillis();
            Collections.sort(referenceList);
            CounterCachedReference couterRef
                 = (CounterCachedReference)referenceList.get(0);
            return couterRef.getCount();
        }
    }
    
    // LFUOverflowAlgorithmServiceMBean��JavaDoc
    public long getOverflowCount(){
        return overflowCount;
    }
    
    // LFUOverflowAlgorithmServiceMBean��JavaDoc
    public long getAverageOverflowCachedTime(){
        return overflowCount == 0 ? 0l : (overflowCachedTime / overflowCount);
    }
    
    // LFUOverflowAlgorithmServiceMBean��JavaDoc
    public String displayReferenceCounts(){
        if(referenceMap == null){
            return "";
        }
        synchronized(referenceMap){
            if(referenceMap.size() == 0){
                return "";
            }
            final StringWriter sw = new StringWriter();
            final PrintWriter pw = new PrintWriter(sw);
            sortCurrentTime = System.currentTimeMillis();
            Collections.sort(referenceList);
            CounterCachedReference couterRef
                 = (CounterCachedReference)referenceList.get(referenceList.size() - 1);
            final long currentTime = System.currentTimeMillis();
            final double unitCount = couterRef.getCount() / 100.0d;
            final Iterator itr = referenceList.iterator();
            while(itr.hasNext()){
                couterRef = (CounterCachedReference)itr.next();
                CachedReference ref = couterRef.getCachedReference();
                final int count = couterRef.getCount();
                final long cachedTime = currentTime - couterRef.getCachedTime();
                final int point = (int)(count / unitCount);
                for(int i = 0; i < point; i++){
                    pw.print('*');
                }
                pw.print('�@');
                pw.print(Integer.toString(count));
                pw.print('(');
                pw.print(Long.toString(cachedTime));
                pw.print(')');
                if(ref instanceof KeyCachedReference){
                    pw.print('�@');
                    pw.print(((KeyCachedReference)ref).getKey());
                }
                pw.println("<br>");
            }
            return sw.toString();
        }
    }
    
    public boolean isCachedRatioCompare() {
        return cachedRatioCompare;
    }
    
    public void setCachedRatioCompare(boolean cachedRatioCompare) {
        this.cachedRatioCompare = cachedRatioCompare;
    }

    public void setRatioUnitTime(long ratioUnitTime) {
        this.ratioUnitTime = ratioUnitTime;
    }
    
    /**
     * �T�[�r�X�̐����������s���B<p>
     * �C���X�^���X�ϐ��̏��������s���B
     *
     * @exception Exception �T�[�r�X�̐��������Ɏ��s�����ꍇ
     */
    public void createService() throws Exception{
        referenceMap = Collections.synchronizedMap(new HashMap());
        referenceList = Collections.synchronizedList(new ArrayList());
    }
    
    /**
     * �T�[�r�X�̔j���������s���B<p>
     * �C���X�^���X�ϐ��̊J�����s���B
     *
     * @exception Exception �T�[�r�X�̔j�������Ɏ��s�����ꍇ
     */
    public void destroyService() throws Exception{
        reset();
        referenceMap = null;
        referenceList = null;
    }
    
    /**
     * �L���b�V���Q�Ƃ�ǉ�����B<p>
     * �����œn���ꂽ�L���b�V���Q�Ƃ�ێ�����B�����ɁA{@link CachedReference#addCacheAccessListener(CacheAccessListener)}�ŁA{@link CacheAccessListener}�Ƃ��Ď������g��o�^����B�܂��A{@link CachedReference#addCacheRemoveListener(CacheRemoveListener)}�ŁA{@link CacheRemoveListener}�Ƃ��Ď������g��o�^����B<br>
     *
     * @param ref �L���b�V���Q��
     */
    public void add(CachedReference ref){
        if(referenceMap == null || ref == null){
            return;
        }
        synchronized(referenceMap){
            if(!referenceMap.containsKey(ref)){
                CounterCachedReference counterRef
                     = new CounterCachedReference(ref, cachedRatioCompare, ratioUnitTime);
                referenceMap.put(ref, counterRef);
                referenceList.add(counterRef);
                ref.addCacheAccessListener(this);
                ref.addCacheRemoveListener(this);
            }
        }
    }
    
    /**
     * �L���b�V���Q�Ƃ��폜����B<p>
     * �����œn���ꂽ�L���b�V���Q�Ƃ�����ŕێ����Ă���ꍇ�́A�j������B�����ɁA{@link CachedReference#removeCacheAccessListener(CacheAccessListener)}�ŁA{@link CacheAccessListener}�Ƃ��Ď������g��o�^��������B�܂��A{@link CachedReference#removeCacheRemoveListener(CacheRemoveListener)}�ŁA{@link CacheRemoveListener}�Ƃ��Ď������g��o�^��������B<br>
     *
     * @param ref �L���b�V���Q��
     */
    public void remove(CachedReference ref){
        if(referenceMap == null || ref == null){
            return;
        }
        synchronized(referenceMap){
            if(referenceMap.containsKey(ref)){
                CounterCachedReference counterRef
                     = (CounterCachedReference)referenceMap.remove(ref);
                referenceList.remove(counterRef);
                ref.removeCacheAccessListener(this);
                ref.removeCacheRemoveListener(this);
            }
        }
    }
    
    /**
     * �ł��Q�ƕp�x���Ⴂ�L���b�V���Q�Ƃ����ӂꂳ����B<p>
     * {@link #add(CachedReference)}�œn���ꂽ�L���b�V���Q�Ƃ̒�����A�ł��Q�ƕp�x���Ⴂ�L���b�V���Q�Ƃ��A���ӂ�L���b�V���Q�ƂƂ��ĕԂ��B<br>
     *
     * @return �ł��Q�ƕp�x���Ⴂ�L���b�V���Q��
     */
    public CachedReference overflow(){
        if(referenceMap == null){
            return null;
        }
        synchronized(referenceMap){
            if(referenceMap.size() != 0){
                sortCurrentTime = System.currentTimeMillis();
                Collections.sort(referenceList);
                CounterCachedReference couterRef
                     = (CounterCachedReference)referenceList.remove(0);
                referenceMap.remove(couterRef.getCachedReference());
                overflowCachedTime += (System.currentTimeMillis() - couterRef.getCachedTime());
                overflowCount++;
                return couterRef.getCachedReference();
            }
            return null;
        }
    }
    
    /**
     * �ł��Q�ƕp�x���Ⴂ�L���b�V���Q�Ƃ����ӂꂳ����B<p>
     * {@link #add(CachedReference)}�œn���ꂽ�L���b�V���Q�Ƃ̒�����A�ł��Q�ƕp�x���Ⴂ�L���b�V���Q�Ƃ��A���ӂ�L���b�V���Q�ƂƂ��ĕԂ��B<br>
     *
     * @param size ���ӂꐔ
     * @return �ł��Q�ƕp�x���Ⴂ�L���b�V���Q��
     */
    public CachedReference[] overflow(int size){
        if(referenceMap == null || referenceMap.size() == 0){
            return null;
        }
        synchronized(referenceMap){
            if(referenceMap.size() != 0){
                final CachedReference[] result = new CachedReference[Math.min(referenceMap.size(), size)];
                sortCurrentTime = System.currentTimeMillis();
                Collections.sort(referenceList);
                for(int i = 0; i < result.length; i++){
                    CounterCachedReference couterRef
                         = (CounterCachedReference)referenceList.remove(0);
                    referenceMap.remove(couterRef.getCachedReference());
                    result[i] = couterRef.getCachedReference();
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
        if(referenceMap != null){
            referenceMap.clear();
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
    
    /**
     * �Q�Ƃ��ꂽ�L���b�V���Q�Ƃ̒ʒm���󂯂�B<p>
     * �����ŕێ����Ă���L���b�V���Q�Ƃ̃��X�g�Ɋ܂܂��ꍇ�́A�Q�Ɖ񐔂𑝉�������B<br>
     *
     * @param ref �Q�Ƃ��ꂽ�L���b�V���Q��
     */
    public void accessed(CachedReference ref){
        if(referenceMap == null){
            return;
        }
        synchronized(referenceMap){
            if(referenceMap != null && referenceMap.containsKey(ref)){
                CounterCachedReference counterRef
                     = (CounterCachedReference)referenceMap.get(ref);
                if(!counterRef.increment()){
                    final Iterator counterRefs
                         = referenceMap.values().iterator();
                    while(counterRefs.hasNext()){
                        ((CounterCachedReference)counterRefs.next()).offset();
                    }
                    counterRef.increment();
                }
            }
        }
    }
    
    private class CounterCachedReference
     implements java.io.Serializable, Comparable{
        
        private static final long serialVersionUID = -5670267780842863519L;
        
        private CachedReference reference;
        private int count = 1;
        private long cachedTime;
        private long lastAccessTime;
        private boolean cachedRatioCompare = false;
        private long ratioUnitTime;
        
        public CounterCachedReference(CachedReference ref, boolean cachedRatioCompare, long ratioUnitTime){
            reference = ref;
            lastAccessTime = System.currentTimeMillis();
            cachedTime = lastAccessTime;
            this.cachedRatioCompare = cachedRatioCompare;
            this.ratioUnitTime = ratioUnitTime;
        }
        public CachedReference getCachedReference(){
            return reference;
        }
        public void offset(){
            count/=1000;
        }
        public boolean increment(){
            if(count == Integer.MAX_VALUE){
                return false;
            }
            count++;
            lastAccessTime = System.currentTimeMillis();
            return true;
        }
        public int getCount(){
            return count;
        }
        public long getLastAccessTime(){
            return lastAccessTime;
        }
        public long getCachedTime(){
            return cachedTime;
        }
        
        public int compareTo(Object arg0) {
            if(arg0 == null || !(arg0 instanceof CounterCachedReference)){
                return 1;
            }
            if(arg0 == this){
                return 0;
            }
            CounterCachedReference comp = (CounterCachedReference)arg0;
            
            if(cachedRatioCompare){
                long currentDiffTime = sortCurrentTime - getLastAccessTime();
                long compDifftime = sortCurrentTime - comp.getLastAccessTime();
                
                double ratio = getRatio(this, currentDiffTime);
                double compRatio = getRatio(comp, compDifftime);
                
                if(ratio == compRatio){
                    return 0;
                }else if(ratio > compRatio){
                    return 1;
                }else{
                    return -1;
                }
            }else{
                
                if(comp.getCount() == getCount()){
                    return 0;
                }else if(comp.getCount() > getCount()){
                    return -1;
                }else{
                    return 1;
                }
            }
        }
        
        private double getRatio(CounterCachedReference target, long diffTime){
            
            return (double)target.getCount() / Math.max(1.0, ((double)diffTime / (double)ratioUnitTime));
        }
     }
}
