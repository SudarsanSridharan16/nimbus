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
package jp.ossc.nimbus.service.graph;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

import jp.ossc.nimbus.core.ServiceBase;

import org.jfree.data.general.Dataset;
import org.jfree.data.time.Day;
import org.jfree.data.time.FixedMillisecond;
import org.jfree.data.time.Hour;
import org.jfree.data.time.Year;
import org.jfree.data.time.Millisecond;
import org.jfree.data.time.Minute;
import org.jfree.data.time.Month;
import org.jfree.data.time.Quarter;
import org.jfree.data.time.Second;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.time.Week;
import org.jfree.data.time.RegularTimePeriod;

/**
 * TimeSeriesCollection�f�[�^�Z�b�g�t�@�N�g���B<p>
 *
 * @author M.Takata
 */
public abstract class TimeSeriesCollectionFactoryService extends ServiceBase
 implements DatasetFactory, TimeSeriesCollectionFactoryServiceMBean{
    
    private static final long serialVersionUID = -2875237240430766743L;
    
    /** ���� : �~���b */
    protected static final int PERIOD_MILLISECOND = 1;
    /** ���� : Fixed�~���b */
    protected static final int PERIOD_FIXEDMILLISECOND = 2;
    /** ���� : �b */
    protected static final int PERIOD_SECOND = 3;
    /** ���� : �� */
    protected static final int PERIOD_MINUTE = 4;
    /** ���� : �� */
    protected static final int PERIOD_HOUR = 5;
    /** ���� : �� */
    protected static final int PERIOD_DAY = 6;
    /** ���� : �T */
    protected static final int PERIOD_WEEK = 7;
    /** ���� : �� */
    protected static final int PERIOD_MONTH = 8;
    /** ���� : �l���� */
    protected static final int PERIOD_QUARTER = 9;
    /** ���� : �N */
    protected static final int PERIOD_YEAR = 10;
    
    /** �f�[�^�Z�b�g�� */
    protected String dataSetName;
    /** TimePeriod�N���X�}�b�v */
    protected Map timePeriodClassMap;
    /** �����^�C�v */
    protected int collateDataType;
    /** ���l�𖳎����邩 */
    protected boolean isIgnoreSameValue;
    /** �l���܂Ƃ߂���ԃt�B�[���h */
    protected int collateDataField = Calendar.MILLISECOND;
    /** �l���܂Ƃ߂���Ԃ̒��� */
    protected int collateDataPeriod = 1;
    /** ���̓f�[�^�̊��ԃt�B�[���h */
    protected int inputDataField = Calendar.MILLISECOND;
    /** ���̓f�[�^�̊��Ԃ̒��� */
    protected int inputDataPeriod = 1;
    /** ���������邩 */
    protected boolean isAutoTimesharing;
    /** �����̗̍p���@ */
    protected int collateDataDateType = COLLATE_DATA_DATE_TYPE_START;
    
    // TimeSeriesCollectionFactoryServiceMBean��JavaDoc
    public void setName(String name){
        dataSetName = name;
    }
    
    // TimeSeriesCollectionFactoryServiceMBean��JavaDoc
    public String getName(){
        return dataSetName;
    }
    
    // TimeSeriesCollectionFactoryServiceMBean��JavaDoc
    public void setTimePeriodClass(String seriesName, Class clazz){
        timePeriodClassMap.put(seriesName, clazz);
    }
    
    // TimeSeriesCollectionFactoryServiceMBean��JavaDoc
    public Class getTimePeriodClass(String seriesName){
        return (Class)timePeriodClassMap.get(seriesName);
    }
    
    // TimeSeriesCollectionFactoryServiceMBean��JavaDoc
    public void setCollateDataType(int type){
        collateDataType = type;
    }
    
    // TimeSeriesCollectionFactoryServiceMBean��JavaDoc
    public int getCollateDataType(){
        return collateDataType;
    }
    
    // TimeSeriesCollectionFactoryServiceMBean��JavaDoc
    public boolean isIgnoreSameValue(){
        return isIgnoreSameValue;
    }
    
    // TimeSeriesCollectionFactoryServiceMBean��JavaDoc
    public void setIgnoreSameValue(boolean isIgnore){
        isIgnoreSameValue = isIgnore;
    }
    
    // TimeSeriesCollectionFactoryServiceMBean��JavaDoc
    public void setCollateDataPeriod(int field, int period){
        collateDataField = field;
        collateDataPeriod = period;
    }
    
    // TimeSeriesCollectionFactoryServiceMBean��JavaDoc
    public void setInputDataPeriod(int field, int period){
        inputDataField = field;
        inputDataPeriod = period;
    }
        
    // TimeSeriesCollectionFactoryServiceMBean��JavaDoc
    public void setAutoTimesharing(boolean isAuto){
        isAutoTimesharing = isAuto;
    }
    
    // TimeSeriesCollectionFactoryServiceMBean��JavaDoc
    public boolean isAutoTimesharing(){
        return isAutoTimesharing;
    }
    
    // DatabaseOHLCDatasetFactoryServiceMBean��JavaDoc
    public void setCollateDataDateType(int type){
        collateDataDateType = type;
    }
    
    // DatabaseOHLCDatasetFactoryServiceMBean��JavaDoc
    public int getCollateDataDateType(){
        return collateDataDateType;
    }
    
    // ServiceBase��JavaDoc
    public void preCreateService() throws Exception{
        super.preCreateService();
        timePeriodClassMap = new HashMap();
    }
    
    public void preStartService() throws Exception{
        super.preStartService();
        if(dataSetName == null || dataSetName.length() == 0){
            // �T�[�r�X��`�Őݒ肳��Ȃ������ꍇ
            dataSetName = getServiceName();
        }
    }
    
    // ServiceBase��JavaDoc
    public void postDestroyService() throws Exception{
        timePeriodClassMap = null;
        super.postDestroyService();
    }
    
    /**
     * �f�[�^�Z�b�g�𐶐�����B<p>
     *
     * @param dsConditions �f�[�^�Z�b�g�����z��
     * @return �f�[�^�Z�b�g
     * @exception DatasetCreateException
     */
    public Dataset createDataset(DatasetCondition[] dsConditions)
     throws DatasetCreateException{
        
        DatasetConnection connection = createConnection(dsConditions);
        TimeSeriesCollection dataset = new TimeSeriesCollection();
        try{
            List cursors = connection.getSeriesCursorList();
            if(cursors == null){
                return dataset;
            }
            Calendar workCal = Calendar.getInstance();
            Holder inOut = new Holder();
            Record record = new Record();
            DoubleList sameDateValues = null;
            OHLCList ohlcList = null;
            
            for(int i = 0, imax = cursors.size(); i < imax; i++){
                TimeSeriesCursor cursor
                    = (TimeSeriesCursor)cursors.get(i);
                String series = cursor.getSeriesName();
                
                Class timePeriodClass = (Class)timePeriodClassMap.get(series);
                int periodType = 0;
                
                TimeSeries timeSeries = null;
                if(collateDataType != 0){
                    if(timePeriodClass == null){
                        timePeriodClass = Millisecond.class;
                    }
                    timeSeries = new TimeSeries(series, timePeriodClass);
                }else{
                    if (timePeriodClass != null){
                        timeSeries = new TimeSeries(series, timePeriodClass);
                    }else{
                        timeSeries = new TimeSeries(series);
                        timePeriodClass = timeSeries.getTimePeriodClass();
                        if(timePeriodClass == null){
                            timePeriodClass = Millisecond.class;
                        }
                    }
                }
                periodType = convertPeriodType(timePeriodClass);
                
                double value = 0d;
                if(sameDateValues != null){
                    sameDateValues.clear();
                }
                if(ohlcList != null){
                    ohlcList.clear();
                }
                
                inOut.clear();
                record.clear();
                Date date = null;
                boolean hasNext = cursor.next();
                while(hasNext){
                    // ���l�̍Ō�̃f�[�^��ǉ�����ۂɎg�����t
                    if(inOut.date == null || inOut.preDate == null){
                        inOut.preDate = inOut.date;
                    }else{
                        inOut.preDate.setTime(inOut.date.getTime());
                    }
                    date = cursor.getDate();
                    if(date == null){
                        throw new DatasetCreateException("date is null.");
                    }
                    
                    value = cursor.getValue();
                    boolean wasNull = cursor.wasNull();
                    if(!wasNull){
                        inOut.date = date;
                        
                        if(isAutoTimesharing){
                            // �������������s��
                            if(inOut.preDate != null && inOut.preDate.equals(date)){
                                // �������Ԃ̒l�𗭂ߍ���
                                record.setDate(date);
                                record.add(value);
                                hasNext = cursor.next();
                                if(hasNext){
                                    continue;
                                }
                            }else{
                                record.setPeriodMillis(getPeriodMillis(workCal, inOut.lastDate, inputDataField, inputDataPeriod));
                                // ���ߍ��񂾓������Ԃ̒l��TimeSeries�ɒǉ�
                                inOut.date = inOut.preDate;
                                double tmpValue = Double.NaN;
                                while(record.hasNext()){
                                    if(inOut.date == null || inOut.preDate == null){
                                        inOut.preDate = (Date)inOut.date.clone();
                                    }else{
                                        inOut.preDate.setTime(inOut.date.getTime());
                                    }
                                    inOut.date = record.nextDate();
                                    tmpValue = record.nextValue();
                                    addTimeSeries(date, tmpValue, workCal, timeSeries, periodType, false, inOut);
                                }
                                record.clear();
                                inOut.date = date;
                            }
                        }
                    }
                    
                    if(hasNext){
                        hasNext = cursor.next();
                    }
                    
                    if(!hasNext){
                        
                        // collateDataType���ݒ肳��Ă��Ȃ�(0)���͂��łɂ��ׂĂ̒l���ǉ�����Ă���̂ŏ�������K�v�Ȃ�
                        if(collateDataType != 0){
                            if(isAutoTimesharing && record.size() != 0){
                                record.setPeriodMillis(getPeriodMillis(workCal, inOut.lastDate, inputDataField, inputDataPeriod));
                                // ���ߍ��񂾓������Ԃ̒l��TimeSeries�ɒǉ�
                                inOut.date = inOut.preDate;
                                double tmpValue = Double.NaN;
                                while(record.hasNext()){
                                    if(inOut.date == null || inOut.preDate == null){
                                        inOut.preDate = (Date)inOut.date.clone();
                                    }else{
                                        inOut.preDate.setTime(inOut.date.getTime());
                                    }
                                    inOut.date = record.nextDate();
                                    tmpValue = record.nextValue();
                                    addTimeSeries(date, tmpValue, workCal, timeSeries, periodType, wasNull && !record.hasNext(), inOut);
                                }

                                record.clear();
                                inOut.date = date;
                            }else{
                                // ���݂̒l��ǉ�
                                addTimeSeries(date, value, workCal, timeSeries, periodType, false, inOut);
                            }
                        }
                        if(!wasNull){
                            // �Ō�̊��Ԃ̒l��ǉ�
                            addTimeSeries(date, value, workCal, timeSeries, periodType, true, inOut);
                        }
                    }else if(!wasNull){
                        // ���݂̒l��ǉ�
                        addTimeSeries(date, value, workCal, timeSeries, periodType, false, inOut);
                    }
                }
                dataset.addSeries(timeSeries);
                timePeriodClass = null;
                cursor.close();
            }
        }finally{
            connection.close();
        }
        return dataset;
    }
    
    protected abstract DatasetConnection createConnection(DatasetCondition[] dsConditions)
     throws DatasetCreateException;
    
    /**
     * �l���܂Ƃ߂���Ԃ̊J�n[ms]���擾����B<p>
     * 
     * @param cal �J�����_�[
     * @param date ���t
     * @return �l���܂Ƃ߂���Ԃ̊J�n[ms]
     */
    protected long getStartMillis(Calendar cal, Date date){
        cal.setTime(date);
        int currVal = cal.get(collateDataField);
        
        switch(collateDataField){
        case Calendar.SECOND:
            cal.set(Calendar.MILLISECOND, 0);
            break;
        case Calendar.MINUTE:
            cal.set(Calendar.SECOND, 0);
            cal.set(Calendar.MILLISECOND, 0);
            break;
        case Calendar.HOUR:
        case Calendar.HOUR_OF_DAY:
            cal.set(Calendar.MINUTE, 0);
            cal.set(Calendar.SECOND, 0);
            cal.set(Calendar.MILLISECOND, 0);
            break;
        case Calendar.MONTH:
        case Calendar.DAY_OF_MONTH:
            cal.set(Calendar.HOUR_OF_DAY, 0);
            cal.set(Calendar.MINUTE, 0);
            cal.set(Calendar.SECOND, 0);
            cal.set(Calendar.MILLISECOND, 0);
            break;
        case Calendar.YEAR:
            cal.set(Calendar.MONTH, Calendar.JANUARY);
            cal.set(Calendar.HOUR_OF_DAY, 0);
            cal.set(Calendar.MINUTE, 0);
            cal.set(Calendar.SECOND, 0);
            cal.set(Calendar.MILLISECOND, 0);
            break;
        case Calendar.MILLISECOND:
        default:
            break;
        }
        
        cal.set(collateDataField, currVal - (currVal % collateDataPeriod));
        return cal.getTimeInMillis();
    }
    
    /**
     * TimeSeries�ɒl��ǉ�����B<p>
     * 
     * @param realDate DB�̖{���̓��t
     * @param value ���݂̒l
     * @param workCal ���[�N�p�J�����_�[
     * @param timeSeries TimeSeries
     * @param periodType ���Ԃ̃^�C�v
     * @param isFinish �Ō�̃��\�b�h�R�[�����ǂ���
     * @param inOut ���o�͂Ŏg���f�[�^��ێ����Ă���N���X
     */
    protected void addTimeSeries(
        Date realDate,
        double value,
        Calendar workCal,
        TimeSeries timeSeries,
        int periodType,
        boolean isFinish,
        Holder inOut
    ){
        long period = -1L;
        if(collateDataType != 0){
            period = getPeriodMillis(workCal, inOut.date, collateDataField, collateDataPeriod);
            // �l���܂Ƃ߂��Ԃ̊J�n���擾
            long startMillis = getStartMillis(workCal, inOut.date);
            int lastIndex = 0;
            switch(collateDataType){
            case COLLATE_DATA_TYPE_START:
                // �f�[�^��1���ڂ͕K��TimeSeries�ɒǉ�����Ă���̂�TimeSeries�ւ̒ǉ������͂��̕�����ł͍s��Ȃ�
                if (inOut.lastStartMillis != -1 && inOut.lastStartMillis == startMillis && !isFinish){
                    if(Double.isNaN(inOut.validValue)){
                        inOut.validValue = value;
                    }
                    return;
                }
                inOut.validValue = Double.NaN;
                inOut.date = new Date(startMillis);
                break;
            case COLLATE_DATA_TYPE_END:
                if (inOut.lastStartMillis != -1 && inOut.lastStartMillis == startMillis && !isFinish){
                    inOut.validValue = value;
                    return;
                }else if(timeSeries.getItemCount() != 0){
                    if (!Double.isNaN(inOut.validValue)){
                        lastIndex = timeSeries.getItemCount() - 1;
                        if(!isIgnoreSameValue || isFinish || inOut.lastValue != inOut.validValue){
                            // ���l�����ݒ�ł͂Ȃ��A�Ō�̋�ԁA�܂��͓��l�����ݒ�œ��l�ł͂Ȃ�
                            // ���ł�1�_�ڂ͒ǉ�����Ă���̂ŁA����1�_�ڂ��X�V����B
                            timeSeries.update(lastIndex, new Double(inOut.validValue));
                        }else{
                            // ���l�����ݒ�œ��l
                            timeSeries.delete(lastIndex, lastIndex);
                        }
                        inOut.validValue = Double.NaN;
                    }
                }
                inOut.date = new Date(startMillis);                
                break;
            case COLLATE_DATA_TYPE_ALL:
                if(inOut.lastStartMillis != -1 && inOut.lastStartMillis == startMillis && !isFinish){
                    if(inOut.sameDateValues == null){
                        inOut.sameDateValues = new DoubleList();
                    }
                    if(isIgnoreSameValue){
                        if(!Double.isNaN(inOut.lastValueForAll)
                            && inOut.lastValueForAll == value){
                            inOut.existSameValueForAll = true;
                        }else{
                            if(inOut.existSameValue){
                                addTimeSeries(
                                    timeSeries,
                                    inOut.preDate,
                                    inOut.lastValue,
                                    periodType,
                                    period,
                                    inOut,
                                    false
                                );
                                inOut.existSameValue = false;
                            }
                            if(inOut.existSameValueForAll){
                                inOut.sameDateValues.add(inOut.lastValueForAll);
                                inOut.existSameValueForAll = false;
                            }
                            inOut.sameDateValues.add(value);
                        }
                    }else{
                        inOut.sameDateValues.add(value);
                    }
                    inOut.lastValueForAll = value;
                    return;
                }else{
                    // �ꔭ�ڂ܂��͋�ԕς��܂��͍Ō�̃��\�b�h�R�[���̏ꍇ
                    if(inOut.sameDateValues != null && inOut.sameDateValues.size() > 0){
                        // �C���^�[�o���̎Z�o
                        // ���
                        // �@�ʏ�͎Z�o������Ԃ��g�p�B
                        // �@�Ō�̃f�[�^�ǉ��̏ꍇ�́A�Ō�̓��t�����Ԃ̊J�n���t�����������̂���ԂƎg�p����B(�\���̍Ō�̓��t��{���̓��t�ŏo������)
                        // ���鑤�̒l (���l�f�[�^���X�g�̃T�C�Y)
                        // �@�ʏ�́u���l�f�[�^���X�g�T�C�Y�v�Ɂu���łɒǉ�����Ă���1�_�v�𑫂�
                        // �@�Ō�̃f�[�^�ǉ��̏ꍇ�́A���l�f�[�^���X�g�T�C�Y�����̂܂܎g��
                        long interval = (isFinish ? (inOut.date.getTime() - startMillis) : period) / (isFinish ? inOut.sameDateValues.size() : inOut.sameDateValues.size() + 1);
                        long additionalTime = inOut.lastStartMillis;
                        DoubleList.DoubleIterator vals = inOut.sameDateValues.iterator();
                        int count = 0;
                        while(vals.hasNext() && count < period){
                            additionalTime += interval;
                            if(inOut.preDate == null){
                                inOut.preDate = new Date(additionalTime);
                            }else{
                                inOut.preDate.setTime(additionalTime);
                            }
                            
                            if(interval == 0){
                                addTimeSeries(
                                    timeSeries,
                                    inOut.preDate,
                                    vals.next(),
                                    periodType,
                                    period,
                                    inOut,
                                    true
                                );
                            }else{
                                addTimeSeries(
                                    timeSeries,
                                    inOut.preDate,
                                    vals.next(),
                                    periodType,
                                    period,
                                    inOut,
                                    false
                                );
                            }
                            count++;
                        }
                        inOut.sameDateValues.clear();
                    }else if(isFinish && isIgnoreSameValue && inOut.existSameValue){
                        // �Ō�̋�Ԃ̃f�[�^��1���R�[�h�̂������̂�
                        // �����ōŌ�̒l��ǉ�
                        addTimeSeries(
                            timeSeries,
                            inOut.date,
                            value,
                            periodType,
                            period,
                            inOut,
                            false
                        );
                    }
                    inOut.date = new Date(startMillis);
                    inOut.lastValueForAll = value;
                }
                break;
            case COLLATE_DATA_TYPE_AVERAGE:
            case COLLATE_DATA_TYPE_SUM:
                if (inOut.lastStartMillis != -1 && inOut.lastStartMillis == startMillis && !isFinish){
                    if(inOut.sameDateValues == null){
                        inOut.sameDateValues = new DoubleList();
                    }
                    inOut.sameDateValues.add(value);
                    return;
                }else{
                    if ((inOut.sameDateValues != null && inOut.sameDateValues.size() > 0) || isFinish){
                        if(isFinish){
                            // �Ō�̃f�[�^�ǉ�����
                            if(inOut.sameDateValues != null && inOut.sameDateValues.size() != 0){
                                // �Ō�̒l�ǉ�
                                if(isIgnoreSameValue){
                                    DoubleList.DoubleIterator vals = inOut.sameDateValues.iterator();
                                    double tmpLastValue = inOut.lastValue;
                                    while(vals.hasNext()){
                                        double val = vals.next();
                                        if(!Double.isNaN(tmpLastValue) && tmpLastValue == val){
                                            // ���l���폜(���̃f�[�^���Ȃ��ꍇ�͍폜���Ȃ�)
                                            if(vals.hasNext()){
                                                vals.remove();
                                            }
                                        }else if(inOut.existSameValue){
                                            // ���߂̒l��ǉ����Ă���
                                            addTimeSeries(
                                                timeSeries,
                                                inOut.preDate,
                                                inOut.lastValue,
                                                periodType,
                                                period,
                                                inOut,
                                                false
                                            );
                                            inOut.existSameValue = false;
                                        }
                                        tmpLastValue = val;
                                    }
                                    vals.reset();
                                }
                                long interval = (inOut.date.getTime() - startMillis) / inOut.sameDateValues.size();
                                if(interval == 0){
                                    DoubleList.DoubleIterator vals = inOut.sameDateValues.iterator();
                                    lastIndex = timeSeries.getItemCount() - 1;
                                    double sum = inOut.lastValue;
                                    while(vals.hasNext()){
                                        sum += vals.next();
                                    }
                                    double sumOrAverage = sum;
                                    if(collateDataType == COLLATE_DATA_TYPE_AVERAGE){
                                        // ���łɒǉ�����Ă���1�_�ڂ�sameDateValues�T�C�Y�ɑ������l�ŕ��ς��Z�o
                                        sumOrAverage = sum / (double)(inOut.sameDateValues.size() + 1);
                                    }
                                    if(!isIgnoreSameValue || !inOut.existSameValue){
                                        // ���łɒǉ�����Ă���1�_�ڂ��Z�o�������ϒl�ōX�V
                                        timeSeries.update(lastIndex, new Double(sumOrAverage));
                                        inOut.lastValue = sumOrAverage;
                                    }else{
                                        addTimeSeries(
                                            timeSeries,
                                            inOut.preDate,
                                            sumOrAverage,
                                            periodType,
                                            period,
                                            inOut,
                                            false
                                        );
                                        inOut.existSameValue = false;
                                    }
                                }else{
                                    long additionalTime = inOut.lastStartMillis;
                                    int count = 0;
                                    DoubleList.DoubleIterator vals = inOut.sameDateValues.iterator();
                                    // �܂Ƃ߂���Ԉȓ��ɂ����Ȃ������l�����ׂĒǉ�
                                    while(vals.hasNext() && count < (inOut.date.getTime() - startMillis)){
                                        additionalTime += interval;
                                        if(inOut.preDate == null){
                                            inOut.preDate = new Date(additionalTime);
                                        }else{
                                            inOut.preDate.setTime(additionalTime);
                                        }
                                        addTimeSeries(
                                            timeSeries,
                                            inOut.preDate,
                                            vals.next(),
                                            periodType,
                                            period,
                                            inOut,
                                            false
                                        );
                                        count++;
                                    }
                                }
                            }else{
                                if(isIgnoreSameValue && inOut.existSameValue){
                                    // ���l����Ɉ����������Ă��āA�Ō�̃f�[�^��ǉ��ł��Ă��Ȃ��̂�
                                    // �����Œǉ�����
                                    addTimeSeries(
                                        timeSeries,
                                        inOut.preDate,
                                        inOut.lastValue,
                                        periodType,
                                        period,
                                        inOut,
                                        false
                                    );
                                    inOut.existSameValue = false;
                                }else{
                                    // �Ō�̃��R�[�h�ǉ�����
                                    // �Ō�̋�Ԃ̃f�[�^��1���R�[�h�����Ȃ�
                                    // ���łɒǉ�����Ă���Ō�̒l���폜
                                    deleteLastTimeSeries(timeSeries);
                                    
                                    addTimeSeries(
                                        timeSeries,
                                        inOut.preDate,
                                        value,
                                        periodType,
                                        period,
                                        inOut,
                                        false
                                    );
                                }
                            }
                        }else if(inOut.sameDateValues != null && inOut.sameDateValues.size() > 0){
                            // �Ō�̃f�[�^�ǉ��ł͂Ȃ��ꍇ
                            DoubleList.DoubleIterator vals = inOut.sameDateValues.iterator();
                            lastIndex = timeSeries.getItemCount() - 1;
                            double sum = inOut.lastValue;
                            while(vals.hasNext()){
                                sum += vals.next();
                            }
                            double sumOrAverage = sum;
                            if(collateDataType == COLLATE_DATA_TYPE_AVERAGE){
                                // ���łɑł���Ă���1�_�ڂ�sameDateValues�T�C�Y�ɑ������l�ŕ��ς��v�Z
                                sumOrAverage = sum / (double)(inOut.sameDateValues.size() + 1);
                            }
                            if(!isIgnoreSameValue || isFinish || inOut.lastValue != inOut.validValue){
                                // ���l�����ݒ�ł͂Ȃ��A�Ō�̋�ԁA�܂��͓��l�����ݒ�œ��l�ł͂Ȃ�
                                // ���ł�1�_�ڂ͒ǉ�����Ă���̂ŁA����1�_�ڂ��X�V����B
                                timeSeries.update(lastIndex, new Double(sumOrAverage));
                            }else{
                                // ���l�����ݒ�œ��l
                                timeSeries.delete(lastIndex, lastIndex);
                            }
                            inOut.sameDateValues.clear();
                        }
                    }
                    
                    inOut.date = new Date(startMillis);
                }
                break;
            case COLLATE_DATA_TYPE_OHLC:
                if (inOut.lastStartMillis != -1 && inOut.lastStartMillis == startMillis && !isFinish){
                    if(inOut.ohlcList == null){
                        inOut.ohlcList = new OHLCList();
                    }
                    if(inOut.ohlcList.size() == 0){
                        inOut.ohlcList.add(inOut.lastValue);
                    }
                    inOut.ohlcList.add(value);
                    return;
                }else{
                    if ((inOut.ohlcList != null && inOut.ohlcList.size() > 0) || isFinish){
                        if(inOut.ohlcList != null && inOut.ohlcList.size() > 0){
                            // �C���^�[�o���͋�Ԃ�OHLC���X�g�̃T�C�Y�Ŋ��邱�ƂŎZ�o����B
                            // ��Ԃɂ���
                            // �@�ʏ�͎Z�o������Ԃ��g�p�B
                            // �@�Ō�̃f�[�^�ǉ��̏ꍇ�́A�Ō�̓��t�����Ԃ̊J�n���t�����������̂���ԂƎg�p����B(�\���̍Ō�̓��t��{���̓��t�ŏo������)
                            // OHLC���X�g�T�C�Y�ɂ���(���鑤�̒l)
                            // �@�ʏ��OHLC���X�g�T�C�Y�����̂܂܎g�p����B
                            // �@�Ō�̃f�[�^�ǉ��̏ꍇ�́A���ł�1�_�ǉ�����Ă���̂�OHLC���X�g�T�C�Y����1�������l���g�p����B
                            long interval = (isFinish ? (inOut.date.getTime() - startMillis) : period) / (isFinish ? inOut.ohlcList.size() - 1 : inOut.ohlcList.size());
                            long additionalTime = inOut.lastStartMillis;
                            OHLCList.OHLCIterator vals = inOut.ohlcList.iterator();
                            int count = 0;
                            while(vals.hasNext() && count < period){
                                double tmpValue = vals.next();
                                if(inOut.preDate == null){
                                    inOut.preDate = new Date(additionalTime);
                                }else{
                                    inOut.preDate.setTime(additionalTime);
                                }
                                if(count == 0){
                                    if((isIgnoreSameValue && inOut.existSameValue)
                                            && (inOut.ohlcList.size() != 2 || inOut.ohlcList.open != inOut.ohlcList.close)){
                                        addTimeSeries(
                                            timeSeries,
                                            inOut.preDate,
                                            tmpValue,
                                            periodType,
                                            period,
                                            inOut,
                                            false
                                        );
                                        inOut.existSameValue = false;
                                    }
                                }else{
                                    if((isIgnoreSameValue && inOut.lastValue == tmpValue) && (vals.hasNext() || !isFinish)){
                                        // �u���l�����ݒ�ŁA���O�ɒǉ����ꂽ�l�ƍ��̒l�����l�v
                                        // ���u���̒l���I�l�ł͂Ȃ��A���ŏI��Ԃ̃f�[�^�ǉ��ł͂Ȃ��ꍇ�v
                                        // (�Ō�̋�Ԃ̏����ŁA���I�l�̃��[�v�������ꍇ�́A�K���Ō�̓_�Ƃ��Ēl��
                                        //  �ǉ����Ȃ��Ƃ����Ȃ��̂ł��̏����ɂ͓���Ȃ��悤�ɂ��Ă���B)
                                        inOut.existSameValue = true;
                                        if(inOut.lastDate == null){
                                            inOut.lastDate = (Date)inOut.preDate.clone();
                                        }else{
                                            inOut.lastDate.setTime(inOut.preDate.getTime());
                                        }
                                    }else{
                                        if(interval == 0){
                                            addTimeSeries(
                                                timeSeries,
                                                inOut.preDate,
                                                tmpValue,
                                                periodType,
                                                period,
                                                inOut,
                                                true
                                            );
                                        }else{
                                            addTimeSeries(
                                                timeSeries,
                                                inOut.preDate,
                                                tmpValue,
                                                periodType,
                                                period,
                                                inOut,
                                                false
                                            );
                                        }
                                    }
                                }
                                additionalTime += interval;
                                count++;
                            }
                        }else if(isFinish){
                            if(isIgnoreSameValue && inOut.existSameValue){
                                // �Ō�̃f�[�^�ŁA���l�����ݒ�A�����l�����݂����ꍇ�̂ݍŌ�̃f�[�^��ǉ�����B
                                // (���l�����݂��Ȃ������ꍇ�́A���łɍŌ�̃f�[�^�͒ǉ�����Ă���)
                                addTimeSeries(
                                    timeSeries,
                                    inOut.date,
                                    value,
                                    periodType,
                                    period,
                                    inOut,
                                    false
                                );
                            }else{
                                // �Ō�̃��R�[�h�ǉ�����
                                // �Ō�̋�Ԃ̃f�[�^��1���R�[�h�����Ȃ�
                                // ���łɒǉ�����Ă���Ō�̒l���폜
                                deleteLastTimeSeries(timeSeries);
                                // �Ō��1���R�[�h�̖{���̎��ԂŒl��o�^
                                addTimeSeries(
                                    timeSeries,
                                    realDate,
                                    value,
                                    periodType,
                                    period,
                                    inOut,
                                    false
                                );
                            }
                        }
                        if(inOut.ohlcList != null){
                            inOut.ohlcList.clear();
                        }
                    }
                    
                    inOut.date = new Date(startMillis);
                }
                break;
            default:
            }
            
            // ���O�̊J�n����[ms]�����̊J�n����[ms]�ŏ㏑��
            inOut.lastStartMillis = startMillis;
        }
        
        if(!isFinish){
            if(isIgnoreSameValue){
                switch(collateDataType){
                case COLLATE_DATA_TYPE_START:
                case COLLATE_DATA_TYPE_ALL:
                case COLLATE_DATA_TYPE_OHLC:
                    if(!Double.isNaN(inOut.lastValue)
                        && inOut.lastValue == value){
                        // ���l��2�ȏ����
                        inOut.existSameValue = true;
                    }else{
                        if(inOut.existSameValue){
                            // 2�ȏ���񂾓��l�̍Ō��ǉ�
                            addTimeSeries(
                                timeSeries,
                                inOut.preDate,
                                inOut.lastValue,
                                periodType,
                                period,
                                inOut,
                                false
                            );
                            inOut.existSameValue = false;
                        }
        
                        // ���݂̒l��ǉ�
                        addTimeSeries(
                            timeSeries,
                            inOut.date,
                            value,
                            periodType,
                            period,
                            inOut,
                            false
                        );
                    }
                    break;
                case COLLATE_DATA_TYPE_END:
                case COLLATE_DATA_TYPE_AVERAGE:
                case COLLATE_DATA_TYPE_SUM:
                    // ���݂̒l��ǉ�
                    addTimeSeries(
                        timeSeries,
                        inOut.date,
                        value,
                        periodType,
                        period,
                        inOut,
                        false
                    );
                default:
                }
            }else{
                addTimeSeries(
                    timeSeries,
                    inOut.date,
                    value,
                    periodType,
                    period,
                    inOut,
                    false
                );
            }
            
            // ���O�̓��t���X�V
            if(inOut.preDate == null){
                inOut.preDate = (Date)inOut.date.clone();
            }else{
                inOut.preDate.setTime(inOut.date.getTime());
            }
        }
    }
    
    /**
     * ���Ԃ̒���[ms]���擾����B<p>
     * 
     * @param cal �J�����_�[
     * @param date ���t
     * @param field ���ԃt�B�[���h
     * @param period ���Ԃ̒���
     * @return ���Ԃ̒���[ms]
     */
    protected long getPeriodMillis(Calendar cal, Date date, int field, int period){
        switch(field){
        case Calendar.SECOND:
            return 1000 * period;
        case Calendar.MINUTE:
            return 60 * 1000 * period;
        case Calendar.HOUR:
            return 60 * 60 * 1000 * period;
        case Calendar.DAY_OF_MONTH:
            return 24 * 60 * 60 * 1000 * period;
        case Calendar.MONTH:
            cal.setTime(date);
            int dayOfMonth = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
            return dayOfMonth * 24 * 60 * 60 * 1000 * period;
        case Calendar.YEAR:
            cal.setTime(date);
            int dayOfYear = cal.getActualMaximum(Calendar.DAY_OF_YEAR);
            return dayOfYear * 24 * 60 * 60 * 1000 * period;
        case Calendar.MILLISECOND:
        default:
            return 1 * period;
        }
    }
    
    /**
     * TimePeriod�N���X�ɑΉ�����萔�ɕϊ�����B<p>
     * 
     * @param timePeriodClass TimePeriod�N���X
     * @return TimePeriod�N���X�ɑΉ�����萔
     */
    protected int convertPeriodType(Class timePeriodClass){
        if (timePeriodClass.equals(Millisecond.class)){
            return PERIOD_MILLISECOND;
        }else if (timePeriodClass.equals(FixedMillisecond.class)){
            return PERIOD_FIXEDMILLISECOND;
        }else if (timePeriodClass.equals(Second.class)){
            return PERIOD_SECOND;
        }else if (timePeriodClass.equals(Minute.class)){
            return PERIOD_MINUTE;
        }else if (timePeriodClass.equals(Hour.class)){
            return PERIOD_HOUR;
        }else if (timePeriodClass.equals(Day.class)){
            return PERIOD_DAY;
        }else if (timePeriodClass.equals(Week.class)){
            return PERIOD_WEEK;
        }else if (timePeriodClass.equals(Month.class)){
            return PERIOD_MONTH;
        }else if (timePeriodClass.equals(Quarter.class)){
            return PERIOD_QUARTER;
        }else if (timePeriodClass.equals(Year.class)){
            return PERIOD_YEAR;
        }
        return 0;
    }
    
    /**
     * TimeSeries�ɒl��ǉ�����B<p>
     *
     * @param series TimeSeries
     * @param date ���t
     * @param value �l
     * @param periodType TimePeriod�^�C�v
     * @param inOut ���\�b�h�R�[���̓��o�͂ŕK�v�ȃf�[�^��ێ������N���X
     * @return TimeSeries
     */
    protected TimeSeries addTimeSeries(
        TimeSeries series,
        Date date,
        double value,
        int periodType,
        long period,
        Holder inOut,
        boolean isAddOrUpdate
    ){
        switch(collateDataType){
        case COLLATE_DATA_TYPE_START:
        case COLLATE_DATA_TYPE_END:
        case COLLATE_DATA_TYPE_AVERAGE:
        case COLLATE_DATA_TYPE_SUM:
            date = createCollateDate(date, period);
            break;
        case COLLATE_DATA_TYPE_ALL:
        case COLLATE_DATA_TYPE_OHLC:
        default:
            break;
        }
        RegularTimePeriod regTimePeriod = null;
        switch(periodType){
        case PERIOD_MILLISECOND:
            regTimePeriod = new Millisecond(date);
            break;
        case PERIOD_FIXEDMILLISECOND:
            regTimePeriod = new FixedMillisecond(date);
            break;
        case PERIOD_SECOND:
            regTimePeriod = new Second(date);
            break;
        case PERIOD_MINUTE:
            regTimePeriod = new Minute(date);
            break;
        case PERIOD_HOUR:
            regTimePeriod = new Hour(date);
            break;
        case PERIOD_DAY:
            regTimePeriod = new Day(date);
            break;
        case PERIOD_WEEK:
            regTimePeriod = new Week(date);
            break;
        case PERIOD_MONTH:
            regTimePeriod = new Month(date);
            break;
        case PERIOD_QUARTER:
            regTimePeriod = new Quarter(date);
            break;
        case PERIOD_YEAR:
            regTimePeriod = new Year(date);
            break;
        default:
        }
        if(isAddOrUpdate){
            series.addOrUpdate(regTimePeriod, value);
        }else{
            series.add(regTimePeriod, value);
        }
        if(inOut.lastDate == null){
            inOut.lastDate = (Date)date.clone();
        }else{
            inOut.lastDate.setTime(date.getTime());
        }
        inOut.lastValue = value;
        return series;
    }
    
    /**
     * �Ō�̃f�[�^��TimeSeries����폜����B<p>
     * 
     * @param series TimeSeries
     * @return TimeSeries
     */
    protected TimeSeries deleteLastTimeSeries(TimeSeries series){
        final int lastIndex = series.getItemCount() - 1;
        if(lastIndex >= 0){
            series.delete(lastIndex, lastIndex);
        }
        return series;
    }
    
    protected Date createCollateDate(Date date, long period){
        Date result = date;
        switch(collateDataDateType){
        case COLLATE_DATA_DATE_TYPE_END:
            result = (Date)result.clone();
            result.setTime((long)(result.getTime() + period - (period / collateDataPeriod)));
            break;
        case COLLATE_DATA_DATE_TYPE_START:
        default:
            break;
        }
        return result;
    }
    
    protected abstract class TimeSeriesCursor extends SeriesCursor{
        public TimeSeriesCursor(String seriesName){
            super(seriesName);
        }
        public abstract Date getDate() throws DatasetCreateException;
        public abstract double getValue() throws DatasetCreateException;
        public abstract boolean wasNull() throws DatasetCreateException;
    }
    
    /**
     * �������Ԃ̒l��ێ�����N���X
     * 1�̎���(Date)�ƕ����̒l(double)��ێ�����
     */
    protected static class Record{
        /** ���t */
        protected Date date;
        /** �������t�̒l(double)�̃��X�g */
        protected DoubleList doubleList = new DoubleList();
        /** �܂Ƃ߂���Ԃ̒���[ms] */
        protected long periodMillis = -1;
        
        public void add(double val){
            doubleList.add(val);
        }
        
        public void setDate(Date date){
            this.date = date;
        }
        
        public void setPeriodMillis(long millis){
            periodMillis = millis;
        }
        
        public Date nextDate(){
            long millis = date.getTime();
            long interval = periodMillis / (size() + 1);
            date.setTime(millis + interval);
            return date;
        }
        
        public double nextValue(){
            return doubleList.iterator().next();
        }
        
        public boolean hasNext(){
            return doubleList.iterator().hasNext();
        }
        
        public int size(){
            return doubleList.size();
        }
        
        public void clear(){
            date = null;
            doubleList.clear();
            periodMillis = -1;
        }
    }
    
    /**
     * TimeSeries�ɒl��ǉ�����ۂɕK�v�Ȓl��ێ�����N���X<p>
     */
    protected static class Holder{
        // ���́A�o�̗͂����Ŏg���l
        public boolean existSameValue;
        public double validValue = Double.NaN;
        public DoubleList sameDateValues;
        public OHLCList ohlcList;
        public double lastValueForAll = Double.NaN;;
        public boolean existSameValueForAll;
        public long lastStartMillis = -1;
        public Date date;
        // ���O�̃��R�[�h�̓��t(TimeSeries�ɒǉ�����Ă��A�ǉ�����Ȃ��Ă��X�V�����)
        public Date preDate;
        // ���O��*�ǉ����ꂽ*���R�[�h�̓��t
        public Date lastDate;
        // ���O��*�ǉ����ꂽ*���R�[�h�̒l
        public double lastValue = Double.NaN;
        
        public void clear(){
            existSameValue = false;;
            validValue = Double.NaN;
            sameDateValues = null;
            ohlcList = null;
            lastValueForAll = Double.NaN;;
            existSameValueForAll = false;
            lastStartMillis = -1;
            date = null;
            preDate = null;
            lastDate = null;
            lastValue = Double.NaN;
        }
    }
    
    /**
     * double�̃��X�g�B<p>
     */
    protected static class DoubleList{
        /** �����T�C�Y */
        protected static final int INIT_SIZE = 10;
        /** ������ */
        protected static final int CAPACITY_INCREMENT_SIZE = 10;
        protected double[] vals = new double[INIT_SIZE];
        protected int index;
        protected DoubleIterator doubleIterator = new DoubleIterator();
        
        public void add(double val){
            if(vals.length <= index){
                double[] tmpVals = new double[vals.length + CAPACITY_INCREMENT_SIZE];
                System.arraycopy(vals, 0, tmpVals, 0, vals.length);
                vals = tmpVals;
            }
            vals[index++] = val;
        }
        
        public int size(){
            return index;
        }
        
        public DoubleIterator iterator(){
            return doubleIterator;
        }
         
        public void clear(){
            index = 0;
            doubleIterator.reset();
        }
         
        protected class DoubleIterator{
            protected int iteratorIndex = 0;
            public boolean hasNext(){
                return index > iteratorIndex;
            }
            public double next(){
                return vals[iteratorIndex++];
            }
            public void remove(){
                System.arraycopy(vals, iteratorIndex, vals, iteratorIndex - 1, index - iteratorIndex);
                iteratorIndex--;
                index--;
            }
            public void reset(){
                iteratorIndex = 0;
            }
        }
    }
    
    /**
     * OHLC���X�g�B<p>
     */
    protected static class OHLCList{
        protected double open = Double.NaN;
        protected double high = Double.NaN;
        protected double low = Double.NaN;
        protected double close = Double.NaN;
        /** ���l�̂ق������l��莞�Ԃ��悩 */
        protected boolean isHighLow = true;
        protected OHLCIterator ohlcIterator = new OHLCIterator();
        
        public void add(double val){
            if (Double.isNaN(open)){
                open = val;
                high = val;
                low = val;
            }
            
            if(val > high){
                high = val;
                isHighLow = false;
            }
            
            if(val < low){
                low = val;
                isHighLow = true;
            }
            
            close = val;
        }
        
        public int size(){
            if (Double.isNaN(open)){
                return 0;
            }
            
            int size = 2;
            if ((high != open && high != close)
                    || (high == open && high != close && !isHighLow)
                    || (high != open && high == close && isHighLow)){
                size++;
            }
            
            if ((low != open && low != close)
                    || (low == open && low != close && isHighLow)
                    || (low != open && low == close && !isHighLow)){
                size++;
            }
            
            return size;
        }
        
        public OHLCIterator iterator(){
           return ohlcIterator; 
        }
        
        public void clear(){
            open = Double.NaN;
            close = Double.NaN;
            high = Double.NaN;
            low = Double.NaN;
            ohlcIterator.reset();
        }
        
        protected class OHLCIterator{
            protected int index = 0;
            protected int maxSize;
            public boolean hasNext(){
                if(index == 0){
                    maxSize = size();
                }
                return maxSize > index;
            }
            
            public double next(){
                switch(index++){
                case 0:
                    return open;
                case 1:
                    if(high == low){
                        return close;
                    }
                    if (isHighLow){
                        if(open == high){
                            return low;
                        }else{
                            return high;
                        }
                    }else{
                        if(open == low){
                            return high;
                        }else{
                            return low;
                        }
                    }
                case 2:
                    if (isHighLow){
                        if(open == high){
                            return close;
                        }else{
                            return low;
                        }
                    }else{
                        if(open == low){
                            return close;
                        }else{
                            return high;
                        }
                    }
                case 3:
                default:
                    return close;
                }
            }
            
            public void reset(){
                index = 0;
                maxSize = 0;
            }
        }
    }
}
