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
import java.lang.reflect.*;

import jp.ossc.nimbus.core.*;
import jp.ossc.nimbus.beans.*;

/**
 * �v�Z�������T�C�Y���ӂꌟ�؃T�[�r�X�B<p>
 * �ȉ��ɁA�������̎g�p�T�C�Y���ő僁�����̔����𒴂���Ɨ\�z���ꂽ�ꍇ�ɂ��ӂ�邠�ӂꌟ�؃T�[�r�X�̃T�[�r�X��`��������B<br>
 * <pre>
 * &lt;?xml version="1.0" encoding="Shift_JIS"?&gt;
 * 
 * &lt;server&gt;
 *     
 *     &lt;manager name="Sample"&gt;
 *         
 *         &lt;service name="CalculateMemorySizeOverflowValidator"
 *                  code="jp.ossc.nimbus.service.cache.CalculateMemorySizeOverflowValidatorService"/&gt;
 *         
 *     &lt;/manager&gt;
 *     
 * &lt;/server&gt;
 * </pre>
 * {@link MemorySizeOverflowValidatorService}�ƈقȂ�̂́AJVM�̃q�[�v�T�C�Y�ł��ӂꌟ�؂���̂ł͂Ȃ��A�L���b�V�����ꂽ�I�u�W�F�N�g���g�p���郁�����T�C�Y�̗��_�l�ł��ӂꌟ�؂���Ƃ���ł���B<br>
 * �I�u�W�F�N�g���g�p���郁�����T�C�Y�̗��_�l�̌v�Z���@�́A�ȉ��̒ʂ�ł���B<br>
 * <ul>
 *   <li>{@link #setMemorySize(String, String)}�Ŏw�肳�ꂽ���_�l������ꍇ�A������g�p����B</li>
 *   <li>�N���X�ɐ錾����Ă���S�ẴC���X�^���X�ϐ����A���̐錾����Ă���N���X�̏����g���ăT�C�Y���v�Z���ĉ��Z����B����́A�t�B�[���h�錾�ɕK�v�ȃ��������v�Z���邾���Ȃ̂ŁA���̕ϐ��̒l���̂̃������g�p�ʂ͌v�Z����Ȃ��B</li>
 *   <li>�I�u�W�F�N�g�ɐ錾����Ă���getter���\�b�h�Ŏ擾�ł���primitive�^�ȊO�̃I�u�W�F�N�g�̃T�C�Y���v�Z���ĉ��Z����B{@link #setCalculateProperty(boolean)}�ł��̉��Z��ON/OFF�𐧌�ł���B�C���X�^���X�ϐ��̒l���̂̃������g�p�ʂ����Z����B�A���Agetter�����݂��Ȃ��C���X�^���X�ϐ��̒l���̂̃������g�p�ʂ͉��Z����Ȃ��B</li>
 *   <li>�z��^�̃I�u�W�F�N�g�́A�z�� * 4 + 12�o�C�g�Ƃ��Čv�Z����B�A���A�C���X�^���X�ɃA�N�Z�X�ł��Ȃ��ꍇ�́A�z�񒷂�0�Ɖ��肷��B�܂��A�C���X�^���X�ɃA�N�Z�X�ł���ꍇ�́A�e�z��v�f�̃I�u�W�F�N�g�̃T�C�Y�����Z����B</li>
 *   <li>java.util.Collection�^��java.util.Map�^�̃I�u�W�F�N�g�́A�C���X�^���X�ɃA�N�Z�X�ł���ꍇ�͊e�v�f�̃I�u�W�F�N�g�̃T�C�Y�����Z����B</li>
 * </ul>
 * �������̗��_�l�́A���t���N�V����API�Œ��ׂ鎖���ł���͈͂܂ł����A�v�Z����Ȃ����߁A�K���������������g�p�ʂƈ�v���Ȃ��B���X�ɂ��āA���������g�p�ʂ��������Ȓl�ɂȂ�B�]���āA���̃T�[�r�X���g�p����ꍇ�́A��L�̗��_�l�łǂ��܂Ŏ��������g�p�ʂƂ̂��ꂪ�������邩��z�肷��K�v������B<br>
 *
 * @author M.Takata
 */
public class CalculateMemorySizeOverflowValidatorService extends ServiceBase
 implements OverflowValidator, CacheRemoveListener, java.io.Serializable,
            CalculateMemorySizeOverflowValidatorServiceMBean{
    
    private static final long serialVersionUID = 6454857430979865088L;
    
    private static final char KILO_UNIT = 'K';
    private static final char MEGA_UNIT = 'M';
    private static final char GIGA_UNIT = 'G';
    
    private static final long KILO_BYTE = 1024;
    private static final long MEGA_BYTE = KILO_BYTE * KILO_BYTE;
    private static final long GIGA_BYTE = MEGA_BYTE * KILO_BYTE;
    
    
    private String maxMemorySizeStr = "32M";
    private long maxMemorySize = 32 * MEGA_BYTE;
    
    private Map references;
    private Map memorySizeMap;
    private Map tmpMemorySizeMap;
    private long currentUsedMemorySize;
    private boolean isCalculateOnValidate;
    private boolean isCalculateProperty;
    
    {
        final Runtime runtime = Runtime.getRuntime();
        try{
            maxMemorySize = runtime.maxMemory() / 2;
            maxMemorySizeStr = Long.toString(maxMemorySize);
        }catch(NoSuchMethodError err){
        }
    }
    
    // CalculateMemorySizeOverflowValidatorServiceMBean��JavaDoc
    public void setMaxMemorySize(String size)
     throws IllegalArgumentException{
        maxMemorySize = convertMemorySize(size);
        maxMemorySizeStr = size;
    }
    
    // CalculateMemorySizeOverflowValidatorServiceMBean��JavaDoc
    public String getMaxMemorySize(){
        return maxMemorySizeStr;
    }
    
    // CalculateMemorySizeOverflowValidatorServiceMBean��JavaDoc
    public void setCalculateProperty(boolean isCalculate){
        isCalculateProperty = isCalculate;
    }
    
    // CalculateMemorySizeOverflowValidatorServiceMBean��JavaDoc
    public boolean isCalculateProperty(){
        return isCalculateProperty;
    }
    
    // CalculateMemorySizeOverflowValidatorServiceMBean��JavaDoc
    public void setCalculateOnValidate(boolean isCalculate){
        isCalculateOnValidate = isCalculate;
    }
    
    // CalculateMemorySizeOverflowValidatorServiceMBean��JavaDoc
    public boolean isCalculateOnValidate(){
        return isCalculateOnValidate;
    }
    
    // CalculateMemorySizeOverflowValidatorServiceMBean��JavaDoc
    public int size(){
        return references == null ? 0 : references.size();
    }
    
    // CalculateMemorySizeOverflowValidatorServiceMBean��JavaDoc
    public long getCurrentUsedMemorySize(){
        return currentUsedMemorySize;
    }
    
    // CalculateMemorySizeOverflowValidatorServiceMBean��JavaDoc
    public void setMemorySize(String className, String size)
     throws ClassNotFoundException{
        final Class clazz = Class.forName(
            className,
            true,
            NimbusClassLoader.getInstance()
        );
        long val = convertMemorySize(size);
        memorySizeMap.put(clazz, new Long(val));
    }
    
    // CalculateMemorySizeOverflowValidatorServiceMBean��JavaDoc
    public String getMemorySize(String className) throws ClassNotFoundException{
        if(memorySizeMap == null){
            return null;
        }
        final Class clazz = Class.forName(
            className,
            true,
            NimbusClassLoader.getInstance()
        );
        Number number = (Number)memorySizeMap.get(clazz);
        return number == null ? null : String.valueOf(number.longValue());
    }
    
    // CalculateMemorySizeOverflowValidatorServiceMBean��JavaDoc
    public Map getMemorySizeMap(){
        return memorySizeMap;
    }
    
    /**
     * �T�[�r�X�̐����������s���B<p>
     * �C���X�^���X�ϐ��̏��������s���B
     *
     * @exception Exception �T�[�r�X�̐��������Ɏ��s�����ꍇ
     */
    public void createService() throws Exception{
        references = Collections.synchronizedMap(new HashMap());
        memorySizeMap = Collections.synchronizedMap(new HashMap());
        memorySizeMap.put(Byte.TYPE, new Short((short)1));
        memorySizeMap.put(Boolean.TYPE, new Short((short)1));
        memorySizeMap.put(Character.TYPE, new Short((short)2));
        memorySizeMap.put(Short.TYPE, new Short((short)2));
        memorySizeMap.put(Integer.TYPE, new Short((short)4));
        memorySizeMap.put(Float.TYPE, new Short((short)4));
        memorySizeMap.put(Long.TYPE, new Short((short)8));
        memorySizeMap.put(Double.TYPE, new Short((short)8));
        memorySizeMap.put(Class.class, new Short((short)0));
        memorySizeMap.put(Method.class, new Short((short)0));
        memorySizeMap.put(Field.class, new Short((short)0));
        memorySizeMap.put(Constructor.class, new Short((short)0));
        tmpMemorySizeMap = Collections.synchronizedMap(new HashMap());
    }
    
    /**
     * �T�[�r�X�̒�~�������s���B<p>
     *
     * @exception Exception �T�[�r�X�̒�~�����Ɏ��s�����ꍇ
     */
    public void stopService() throws Exception{
        tmpMemorySizeMap.clear();
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
    
    private long calculateMemorySize(Object obj){
        if(obj == null){
            return 0l;
        }
        final Class clazz = obj.getClass();
        return calculateMemorySize(clazz, obj, true, new ArrayList());
    }
    
    private long roundUp(long val, int base){
        long tmp = val % base;
        return tmp == 0 ? val : (val + base - tmp);
    }
    
    private long calculateMemorySize(Class clazz, Object obj, boolean isRoundUp, List stack){
        if(stack.contains(clazz)){
            return 0l;
        }
        int index = 0;
        try{
            index = stack.size();
            stack.add(clazz);
            Number size = (Number)memorySizeMap.get(clazz);
            if(size != null){
                return size.longValue();
            }
            if(obj == null){
                size = (Number)tmpMemorySizeMap.get(clazz);
                if(size != null){
                    return size.longValue();
                }
            }
            long result = 0l;
            if(clazz.isInterface()){
                // �C���X�^���X�̌^��������Ȃ��̂Œ��߂�
                result = 8l;
            }else if(clazz.isArray()){
                if(obj == null){
                    // �z��̃T�C�Y��������Ȃ��̂Œ���0�Ƃ��Ē��߂�
                    result = 12l;
                }else{
                    final int length = Array.getLength(obj);
                    result = length * 4 + 12l;
                    result = roundUp(result, 8);
                    for(int i = 0; i < length; i++){
                        Object element = Array.get(obj, i);
                        if(element != null){
                            result += calculateMemorySize(
                                element.getClass(),
                                element,
                                false,
                                stack
                            );
                        }
                    }
                }
                result = roundUp(result, 8);
            }else if(String.class.equals(clazz)){
                result = calculateMemorySize(clazz, null, true, stack);
                if(obj != null){
                    final String str = (String)obj;
                    result += str.length() * (4l + 2l);
                    result = roundUp(result, 8);
                }
            }else if(Collection.class.isAssignableFrom(clazz)){
                result = calculateMemorySize(clazz, null, true, stack);
                if(obj != null){
                    final Collection col = (Collection)obj;
                    if(col.size() != 0){
                        final Iterator itr = col.iterator();
                        while(itr.hasNext()){
                            Object element = itr.next();
                            if(element != null){
                                result += calculateMemorySize(
                                    element.getClass(),
                                    element,
                                    true,
                                    stack
                                );
                            }
                        }
                    }
                }
            }else if(Map.class.isAssignableFrom(clazz)){
                result = calculateMemorySize(clazz, null, true, stack);
                if(obj != null){
                    final Map map = (Map)obj;
                    if(map.size() != 0){
                        final Iterator entries = map.entrySet().iterator();
                        while(entries.hasNext()){
                            Map.Entry entry = (Map.Entry)entries.next();
                            Object key = entry.getKey();
                            if(key != null){
                                result += calculateMemorySize(
                                    key.getClass(),
                                    key,
                                    true,
                                    stack
                                );
                            }
                            Object value = entry.getValue();
                            if(value != null){
                                result += calculateMemorySize(
                                    value.getClass(),
                                    value,
                                    true,
                                    stack
                                );
                            }
                        }
                    }
                }
            }else{
                Class tmpClass = clazz;
                result += 8;
                do{
                    final Field[] fields = tmpClass.getDeclaredFields();
                    if(fields != null){
                        for(int i = 0; i < fields.length; i++){
                            final Field field = fields[i];
                            if(Modifier.isStatic(field.getModifiers())){
                                continue;
                            }
                            final Class fieldType = field.getType();
                            result += calculateFieldMemorySize(
                                fieldType,
                                true
                            );
                        }
                        if(isRoundUp){
                            result = roundUp(result, 8);
                        }
                    }
                    tmpClass = tmpClass.getSuperclass();
                }while(tmpClass != null);
                if(obj != null && isCalculateProperty){
                    final SimpleProperty[] props
                         = SimpleProperty.getProperties(obj);
                    for(int i = 0; i < props.length; i++){
                        if(!props[i].isReadable(obj)){
                            continue;
                        }
                        Object val = null;
                        Class type = null;
                        try{
                            final Method method = props[i].getReadMethod(obj);
                            if(Modifier.isStatic(method.getModifiers())){
                                continue;
                            }
                            type = props[i].getPropertyType(obj);
                            if(type.isPrimitive()){
                                continue;
                            }
                            val = props[i].getProperty(obj);
                        }catch(InvocationTargetException e){
                        }catch(NoSuchPropertyException e){
                        }
                        if(val != null){
                            result += calculateMemorySize(
                                type,
                                val,
                                true,
                                stack
                            );
                        }
                    }
                }
            }
            if(obj == null){
                tmpMemorySizeMap.put(clazz, new Long(result));
            }
            return result;
        }finally{
            stack.remove(index);
        }
    }
    
    private long calculateFieldMemorySize(Class clazz, boolean isRoundUp){
        if(clazz.isPrimitive()){
            if(Byte.TYPE.equals(clazz) || Boolean.TYPE.equals(clazz)){
                return 1l;
            }else if(Character.TYPE.equals(clazz) || Short.TYPE.equals(clazz)){
                return 2l;
            }else if(Integer.TYPE.equals(clazz) || Float.TYPE.equals(clazz)){
                return 4l;
            }else{
                return 8l;
            }
        }else{
            if(clazz.isArray()){
                // �z��̃T�C�Y��������Ȃ��̂Œ���0�Ƃ��Ē��߂�
                return 16l;
            }else{
                return 4l;
            }
        }
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
            if(!references.containsKey(ref)){
                Long size = null;
                if(!isCalculateOnValidate){
                    size = new Long(calculateMemorySize(ref.get(this)));
                    if(currentUsedMemorySize < 0){
                        currentUsedMemorySize = 0;
                    }
                    currentUsedMemorySize += size.longValue();
                }
                references.put(ref, size);
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
            if(references.containsKey(ref)){
                final Long size = (Long)references.remove(ref);
                ref.removeCacheRemoveListener(this);
                if(!isCalculateOnValidate && size != null){
                    currentUsedMemorySize -= size.longValue();
                    if(currentUsedMemorySize < 0){
                        currentUsedMemorySize = 0;
                    }
                }
            }
        }
    }
    
    /**
     * �q�[�v�������̎g�p���ł��ӂꌟ�؂��s���B<p>
     * �ȉ��̌v�Z���ŁA���ӂꐔ���v�Z����B�A���A�v�Z���ʂ����̏ꍇ�́A0�Ƃ���B<br>
     * �i���_�g�p������ - �ő僁�����j���i���_�g�p���������L���b�V���T�C�Y�j
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
            if(isCalculateOnValidate){
                currentUsedMemorySize = 0;
                final Iterator entries = references.entrySet().iterator();
                while(entries.hasNext()){
                    final Map.Entry entry = (Map.Entry)entries.next();
                    final CachedReference ref
                         = (CachedReference)entry.getKey();
                    final long size = calculateMemorySize(ref.get(this));
                    if(currentUsedMemorySize < 0){
                        currentUsedMemorySize = 0;
                    }
                    currentUsedMemorySize += size;
                }
            }
            
            if(currentUsedMemorySize < maxMemorySize){
                return 0;
            }
            double usedAverage = currentUsedMemorySize / references.size();
            long overflowMemorySize = currentUsedMemorySize - maxMemorySize;
            final double overflowSize = overflowMemorySize / usedAverage;
            return overflowSize > 0.0 ? (int)Math.ceil(overflowSize) : 0;
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
        currentUsedMemorySize = 0;
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
