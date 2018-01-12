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
package jp.ossc.nimbus.service.scheduler2;

import java.util.Date;

import jp.ossc.nimbus.core.*;
import jp.ossc.nimbus.service.scheduler.DateKey;
import jp.ossc.nimbus.service.scheduler.DateEvaluator;


/**
 * DateKey�����X�P�W���[���쐬�T�[�r�X�B<p>
 * �X�P�W���[���̍쐬�L���̔�����X�P�W���[���^�C�v��DateKey�Ɖ��߂��ď�������B<br>
 *
 * @author M.Takata
 */
public class DateKeyConditionScheduleMakerService
 extends DefaultScheduleMakerService
 implements DateKeyConditionScheduleMakerServiceMBean{
    
    private static final long serialVersionUID = 1985942168084980639L;
    
    private String dateKeyStr;
    private DateKey dateKey;
    private ServiceName dateEvaluatorServiceName;
    private DateEvaluator dateEvaluator;
    
    // DateMappingScheduleFactoryServiceMBean��JavaDoc
    public void setDateKey(String key){
        dateKeyStr = key;
    }
    // DateMappingScheduleFactoryServiceMBean��JavaDoc
    public String getDateKey(){
        return dateKeyStr;
    }
    
    // DateMappingScheduleFactoryServiceMBean��JavaDoc
    public void setDateEvaluatorServiceName(ServiceName name){
        dateEvaluatorServiceName = name;
    }
    
    // DateMappingScheduleFactoryServiceMBean��JavaDoc
    public ServiceName getDateEvaluatorServiceName(){
        return dateEvaluatorServiceName;
    }
    
    /**
     * �T�[�r�X�̊J�n�������s���B<p>
     *
     * @exception Exception �T�[�r�X�̊J�n�����Ɏ��s�����ꍇ
     */
    public void startService() throws Exception{
        if(dateEvaluatorServiceName != null){
            dateEvaluator = (DateEvaluator)ServiceManagerFactory
                .getServiceObject(dateEvaluatorServiceName);
        }
        dateKey = new DateKey(dateKeyStr, dateEvaluator);
        super.startService();
    }
    
    /**
     * {@link DateEvaluator}��ݒ肷��B<p>
     * ���t�g���L�[���g�p����ꍇ�ɐݒ肷��B<br>
     *
     * @param evaluator DateEvaluator
     */
    public void setDateEvaluator(DateEvaluator evaluator){
        dateEvaluator = evaluator;
    }
    
    /**
     * ���̓��t�ŁA�X�P�W���[�����쐬����K�v�����邩�ǂ����𔻒肷��B<p>
     * �����̍쐬���Ɛݒ肳�ꂽ{@link DateKey}�����v����ꍇ�A�X�P�W���[�����쐬����B<br>
     *
     * @param date �쐬��
     * @param master �X�P�W���[���}�X�^
     * @return true�̏ꍇ�A���K�v������
     * @exception ScheduleMakeException ����Ɏ��s�����ꍇ
     */
    protected boolean isNecessaryMake(Date date, ScheduleMaster master)
     throws ScheduleMakeException{
        try{
            return dateKey.equalsDate(date);
        }catch(Exception e){
            throw new ScheduleMakeException(e);
        }
    }
}