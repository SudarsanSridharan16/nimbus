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

package jp.ossc.nimbus.service.log;

import java.util.*;

import jp.ossc.nimbus.lang.*;
import jp.ossc.nimbus.service.message.MessageRecordFactory;
import jp.ossc.nimbus.service.writer.MessageWriteException;

/**
 * �ȈՃ��O�J�e�S���T�[�r�X�B<p>
 * ���O�o�͐�𕪗ނ���J�e�S���T�[�r�X�̊ȈՎ����N���X�B<br>
 *
 * @author M.Takata
 */
public class SimpleCategoryService
 extends jp.ossc.nimbus.service.writer.SimpleCategoryService
 implements SimpleCategoryServiceMBean{
    
    private static final long serialVersionUID = 7967745897491812488L;
    
    // ���b�Z�[�WID��`
    private static final String SCGRY = "SCGRY";
    private static final String SCGRY0 = SCGRY + 0;
    private static final String SCGRY00 = SCGRY0 + 0;
    private static final String SCGRY000 = SCGRY00 + 0;
    private static final String SCGRY0000 = SCGRY000 + 0;
    private static final String SCGRY00001 = SCGRY0000 + 1;
    private static final String SCGRY00002 = SCGRY0000 + 2;
    private static final String SCGRY00003 = SCGRY0000 + 3;
    private static final String SCGRY00004 = SCGRY0000 + 4;
    
    private static final String PRIORITY_RANGE_DELIMITER = ":";
    
    /**
     * �J�e�S�����B<p>
     */
    protected String categoryName;
    
    /**
     * ���O�̗D�揇�ʔ͈́B<p>
     */
    private Range priorityRange;
    
    /**
     * �D�揇�ʂƃ��x���̃}�b�s���O�B<p>
     * <table border="1">
     *   <tr bgcolor="#CCCCFF"><th colspan="2">�L�[</th><th colspan="2">�l</th></tr>
     *   <tr bgcolor="#CCCCFF"><th>�^</th><th>���e</th><th>�^</th><th>���e</th></tr>
     *   <tr><td>Range</td><td>�D�揇�ʔ͈�</td><td>String</td><td>���x��</td></tr>
     * </table>
     */
    private Map labelMap = new HashMap();
    
    public void startService() throws Exception{
        super.startService();
        if(categoryName == null){
            final MessageRecordFactory message = getMessageRecordFactory();
            throw new ServiceException(
                SCGRY00004,
                message.findMessage(SCGRY00004)
            );
        }
        if(priorityRange == null){
            priorityRange = new Range(-1, -1);
        }
    }
    
    // LogCategory��JavaDoc
    public String getCategoryName(){
        return categoryName;
    }
    
    // SimpleCategoryServiceMBean��JavaDoc
    public void setCategoryName(String name){
        categoryName = name;
    }
    
    // LogCategory��JavaDoc
    public boolean isValidPriorityRange(int priority){
        return priorityRange.contains(priority);
    }
    
    /**
     * ���̃J�e�S���ŏo�͂���郍�O�̗D�揇�ʔ͈͂̍ŏ��l���擾����B<p>
     *
     * @return �D�揇�ʔ͈͂̍ŏ��l
     */
    public int getPriorityRangeMin(){
        return priorityRange.min;
    }
    
    /**
     * ���̃J�e�S���ŏo�͂���郍�O�̗D�揇�ʔ͈͂̍ő�l���擾����B<p>
     *
     * @return �D�揇�ʔ͈͂̍ő�l
     */
    public int getPriorityRangeMax(){
        return priorityRange.max;
    }
    
    /**
     * ���̃J�e�S���ŏo�͂���郍�O�̗D�揇�ʔ͈͂�ݒ肷��B<p>
     *
     * @param min �D�揇�ʔ͈͂̍ŏ��l
     * @param max �D�揇�ʔ͈͂̍ő�l
     * @exception IllegalArgumentException �D�揇�ʔ͈͂̎w�肪�s���ȏꍇ�B
     */
    public void setPriorityRangeValue(int min, int max)
     throws IllegalArgumentException{
        if(min > max){
            final MessageRecordFactory message = getMessageRecordFactory();
            throw new IllegalArgumentException(
                message.findMessage(SCGRY00002)
            );
        }
        priorityRange = new Range(min, max);
    }
    
    // SimpleCategoryServiceMBean��JavaDoc
    public void setPriorityRange(String range)
     throws IllegalArgumentException{
        priorityRange = parseRange(range);
    }
    
    // SimpleCategoryServiceMBean��JavaDoc
    public String getPriorityRange(){
        return priorityRange == null ? null : priorityRange.toString();
    }
    
    private Range parseRange(String range) throws IllegalArgumentException{
        final MessageRecordFactory message = getMessageRecordFactory();
        final StringTokenizer tokens
             = new StringTokenizer(range, PRIORITY_RANGE_DELIMITER);
        if(tokens.countTokens() != 2){
            throw new IllegalArgumentException(
                message.findMessage(SCGRY00001)
            );
        }
        final String minStr = tokens.nextToken();
        final String maxStr = tokens.nextToken();
        int min = 0;
        int max = 0;
        try{
            min = Integer.parseInt(minStr);
            max = Integer.parseInt(maxStr);
        }catch(NumberFormatException e){
            throw new IllegalArgumentException(
                message.findMessage(SCGRY00001)
            );
        }
        if(min > max){
            throw new IllegalArgumentException(
                message.findMessage(SCGRY00002)
            );
        }
        return new Range(min, max);
    }
    
    // Category��JavaDoc
    public String getLabel(int priority){
        final Iterator ranges = labelMap.keySet().iterator();
        while(ranges.hasNext()){
            final Range range = (Range)ranges.next();
            if(range.contains(priority)){
                return (String)labelMap.get(range);
            }
        }
        return null;
    }
    
    // SimpleCategoryServiceMBean��JavaDoc
    public void setLabels(Properties labels) throws IllegalArgumentException{
        final Iterator ranges = labels.keySet().iterator();
        while(ranges.hasNext()){
            final String rangeStr = (String)ranges.next();
            final Range range = parseRange(rangeStr);
            if(labelMap.containsKey(new Integer(range.min))
                || labelMap.containsKey(new Integer(range.max))){
                final MessageRecordFactory message = getMessageRecordFactory();
                throw new IllegalArgumentException(
                    message.findMessage(SCGRY00003)
                );
            }
            labelMap.put(range, labels.get(rangeStr));
        }
    }
    
    // SimpleCategoryServiceMBean��JavaDoc
    public Properties getLabels(){
        Properties props = new Properties();
        final Iterator entries = labelMap.entrySet().iterator();
        while(entries.hasNext()){
            final Map.Entry entry = (Map.Entry)entries.next();
            final Range range = (Range)entry.getKey();
            props.setProperty(range.toString(), (String)entry.getValue());
        }
        return props;
    }
    
    /**
     * ���O�̗D�揇�ʂɑΉ����郉�x����ݒ肷��B<p>
     *
     * @param min �D�揇�ʔ͈͂̍ŏ��l
     * @param max �D�揇�ʔ͈͂̍ő�l
     * @param label ���x��
     * @exception IllegalArgumentException �D�揇�ʔ͈͂̎w�肪�s���ȏꍇ�B
     */
    public void setLabel(int min, int max, String label)
     throws IllegalArgumentException{
        if(min > max){
            final MessageRecordFactory message = getMessageRecordFactory();
            throw new IllegalArgumentException(
                message.findMessage(SCGRY00002)
            );
        }
        final Range range = new Range(min, max);
        labelMap.put(range, label);
    }
    
    // Category��JavaDoc
    public void write(int priority, Map elements) throws MessageWriteException{
        if(isValidPriorityRange(priority)){
            super.write(elements);
        }
    }
    
    private class Range implements Comparable{
        private final int min;
        private final int max;
        public Range(int min, int max){
            this.min = min;
            this.max = max;
        }
        public boolean contains(int val){
            return min <= val && val <= max;
        }
        public boolean contains(Range range){
            return min <= range.min && range.max <= max;
        }
        public boolean overlaps(Range range){
            return contains(range.min) || contains(range.max);
        }
        public int compareTo(Object o){
            if(o == Range.this){
                return 0;
            }
            if(o instanceof Range){
                final Range comp = (Range)o;
                if(comp.min == min){
                    if(comp.max == max){
                        return 0;
                    }else if(comp.max > max){
                        return -1;
                    }else{
                        return 1;
                    }
                }else if(comp.min > min){
                    return -1;
                }else{
                    return 1;
                }
            }
            return -1;
        }
        public boolean equals(Object o){
            if(o == Range.this){
                return true;
            }
            if(o instanceof Range){
                final Range comp = (Range)o;
                return comp.min == min && comp.max == max;
            }
            return false;
        }
        public int hashCode(){
            return min + max;
        }
        public String toString(){
            return min + ":" + max;
        }
    }
}
