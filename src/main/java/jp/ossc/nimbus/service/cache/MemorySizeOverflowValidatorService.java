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
 * �������T�C�Y���ӂꌟ�؃T�[�r�X�B<p>
 * �ȉ��ɁA�q�[�v�������̎g�p�T�C�Y���ő�q�[�v�������̔����𒴂���Ƃ��ӂ�邠�ӂꌟ�؃T�[�r�X�̃T�[�r�X��`��������B<br>
 * <pre>
 * &lt;?xml version="1.0" encoding="Shift_JIS"?&gt;
 * 
 * &lt;server&gt;
 *     
 *     &lt;manager name="Sample"&gt;
 *         
 *         &lt;service name="MemorySizeOverflowValidator"
 *                  code="jp.ossc.nimbus.service.cache.MemorySizeOverflowValidatorService"/&gt;
 *         
 *     &lt;/manager&gt;
 *     
 * &lt;/server&gt;
 * </pre>
 *
 * @author M.Takata
 */
public class MemorySizeOverflowValidatorService extends ServiceBase
 implements OverflowValidator, CacheRemoveListener, java.io.Serializable,
            MemorySizeOverflowValidatorServiceMBean{
    
    private static final long serialVersionUID = -8937874822673039671L;
    
    private static final char KILO_UNIT = 'K';
    private static final char MEGA_UNIT = 'M';
    private static final char GIGA_UNIT = 'G';
    
    private static final long KILO_BYTE = 1024;
    private static final long MEGA_BYTE = KILO_BYTE * KILO_BYTE;
    private static final long GIGA_BYTE = MEGA_BYTE * KILO_BYTE;
    
    private String maxHeapMemorySizeStr = "64M";
    private long maxHeapMemorySize = 64 * MEGA_BYTE;
    
    private String highHeapMemorySizeStr = "32M";
    private long highHeapMemorySize = 32 * MEGA_BYTE;
    
    private Set references;
    
    {
        final Runtime runtime = Runtime.getRuntime();
        try{
            maxHeapMemorySize = runtime.maxMemory();
            maxHeapMemorySizeStr = Long.toString(maxHeapMemorySize);
            highHeapMemorySize = maxHeapMemorySize / 2;
            highHeapMemorySizeStr = Long.toString(highHeapMemorySize);
        }catch(NoSuchMethodError err){
        }
    }
    
    /**
     * �T�[�r�X�̐����������s���B<p>
     * �C���X�^���X�ϐ��̏��������s���B
     *
     * @exception Exception �T�[�r�X�̐��������Ɏ��s�����ꍇ
     */
    public void createService() throws Exception{
        references = Collections.synchronizedSet(new HashSet());
    }
    
    /**
     * �T�[�r�X�̊J�n�������s���B<p>
     * �����̑Ó����`�F�b�N���s���B<br>
     *
     * @exception Exception �T�[�r�X�̊J�n�����Ɏ��s�����ꍇ
     */
    public void startService() throws Exception{
        if(maxHeapMemorySize <= highHeapMemorySize){
            throw new IllegalArgumentException(
                "maxHeapMemorySize must be larger than highHeapMemorySize."
            );
        }
    }
    
    /**
     * �T�[�r�X�̔j���������s���B<p>
     * �C���X�^���X�ϐ��̊J�����s���B
     *
     * @exception Exception �T�[�r�X�̔j�������Ɏ��s�����ꍇ
     */
    public void destroyService() throws Exception{
        reset();
        references = null;
    }
    
    // MemorySizeOverflowValidatorServiceMBean��JavaDoc
    public void setMaxHeapMemorySize(String size)
     throws IllegalArgumentException{
        maxHeapMemorySize = convertMemorySize(size);
        maxHeapMemorySizeStr = size;
    }
    
    // MemorySizeOverflowValidatorServiceMBean��JavaDoc
    public String getMaxHeapMemorySize(){
        return maxHeapMemorySizeStr;
    }
    
    // MemorySizeOverflowValidatorServiceMBean��JavaDoc
    public void setHighHeapMemorySize(String size)
     throws IllegalArgumentException{
        highHeapMemorySize = convertMemorySize(size);
        highHeapMemorySizeStr = size;
    }
    
    // MemorySizeOverflowValidatorServiceMBean��JavaDoc
    public String getHighHeapMemorySize(){
        return highHeapMemorySizeStr;
    }
    
    // MemorySizeOverflowValidatorServiceMBean��JavaDoc
    public int size(){
        return references == null ? 0 : references.size();
    }
    
    private long convertMemorySize(String size)
     throws IllegalArgumentException{
        long value = 0L;
        boolean isValid = true;
        
        if(size == null){
            isValid = false;
        }else{
            final int length = size.length();
            if(length == 0){
                isValid = false;
            }else{
                final char unit = Character.toUpperCase(
                    size.charAt(length - 1)
                );
                String tmpSize = null;
                long unitValue = 0;
                switch(unit){
                case KILO_UNIT:
                    tmpSize = size.substring(0, length - 1);
                    unitValue = KILO_BYTE;
                    break;
                case MEGA_UNIT:
                    tmpSize = size.substring(0, length - 1);
                    unitValue = MEGA_BYTE;
                    break;
                case GIGA_UNIT:
                    tmpSize = size.substring(0, length - 1);
                    unitValue = GIGA_BYTE;
                    break;
                default:
                    tmpSize = size;
                    unitValue = 1;
                }
                try{
                    value = (long)(Double.parseDouble(tmpSize) * (long)unitValue);
                }catch(NumberFormatException e){
                    isValid = false;
                }
            }
        }
        if(value < 0){
            isValid = false;
        }
        if(!isValid){
            throw new IllegalArgumentException("Invalid size : " + size);
        }
        return value;
    }
    
    /**
     * �����׃q�[�v�������T�C�Y���擾����B<p>
     *
     * @return �����׃q�[�v�������T�C�Y
     */
    protected long getHighHeapMemorySizeValue(){
        return highHeapMemorySize;
    }
    
    /**
     * �ő�q�[�v�������T�C�Y���擾����B<p>
     *
     * @return �ő�q�[�v�������T�C�Y
     */
    protected long getMaxHeapMemorySizeValue(){
        return maxHeapMemorySize;
    }
    
    /**
     * �L���b�V���Q�Ƃ�ǉ�����B<p>
     * �����œn���ꂽ�L���b�V���Q�Ƃ�ێ�����B�����ɁA{@link CachedReference#addCacheRemoveListener(CacheRemoveListener)}�ŁA{@link CacheRemoveListener}�Ƃ��Ď������g��o�^����B<br>
     *
     * @param ref �L���b�V���Q��
     */
    public void add(CachedReference ref){
        if(references == null || ref == null){
            return;
        }
        synchronized(references){
            if(!references.contains(ref)){
                references.add(ref);
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
        if(references == null || ref == null){
            return;
        }
        synchronized(references){
            if(references.contains(ref)){
                references.remove(ref);
                ref.removeCacheRemoveListener(this);
            }
        }
    }
    
    /**
     * �q�[�v�������̎g�p���ł��ӂꌟ�؂��s���B<p>
     * �ȉ��̌v�Z���ŁA���ӂꐔ���v�Z����B�A���A�v�Z���ʂ����̏ꍇ�́A0�Ƃ���B<br>
     * �L���b�V���T�C�Y�~�i�g�p�q�[�v�������]�����׃q�[�v�������j���i�ő�q�[�v�������]�����׃q�[�v�������j
     *
     * @return ���ӂꌟ�؂��s�������ʂ��ӂꂪ��������ꍇ�A���ӂꐔ��Ԃ��B���ӂ�Ȃ��ꍇ�́A0��Ԃ�
     */
    public int validate(){
        if(references == null || references.size() == 0){
            return 0;
        }
        synchronized(references){
            if(getState() != STARTED){
                return 0;
            }
            float rate = calculateOverflowRate();
            final int overflowSize = (int)(references.size() * rate);
            return overflowSize > 0 ? overflowSize : 0;
        }
    }
    
    /**
     * ���ӂꌟ�؂����s���邽�߂ɕێ����Ă����������������B<p>
     * {@link #add(CachedReference)}�œn���ꂽ�L���b�V���Q�Ƃ�S�Ĕj������B<br>
     */
    public void reset(){
        if(references != null){
            references.clear();
        }
    }
    
    public float calculateOverflowRate(){
        final Runtime runtime = Runtime.getRuntime();
        final long currentHeap = runtime.totalMemory();
        final long currentFree = runtime.freeMemory();
        final long currentUse = currentHeap - currentFree;
        final long highHeap = getHighHeapMemorySizeValue();
        if(currentUse < highHeap){
            return 0.0f;
        }
        final long maxHeap = getMaxHeapMemorySizeValue();
        float rate
             = ((float)(currentUse - highHeap)) / (maxHeap - highHeap);
        rate = Math.min(rate, 1.0F);
        return rate;
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
