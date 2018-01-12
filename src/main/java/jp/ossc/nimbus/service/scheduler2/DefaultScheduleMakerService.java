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

import java.util.*;

import jp.ossc.nimbus.core.*;


/**
 * �f�t�H���g�X�P�W���[���쐬�T�[�r�X�B<p>
 *
 * @author M.Takata
 */
public class DefaultScheduleMakerService extends ServiceBase
 implements ScheduleMaker, DefaultScheduleMakerServiceMBean{
    
    private static final long serialVersionUID = -8603198587673383956L;
    
    /**
     * �w�肳�ꂽ�X�P�W���[���}�X�^����X�P�W���[�����쐬����B<p>
     * �X�P�W���[���}�X�^�̐��������`�F�b�N���A�X�P�W���[���}�X�^�Ɏw�肳�ꂽ���t��K�p���A�X�P�W���[�����쐬����B<br>
     * �X�P�W���[���̍쐬�ł́A�J��Ԃ��̃X�P�W���[���}�X�^�̏ꍇ�́A�w�肳�ꂽ�J��Ԃ��Ԋu�ŃX�P�W���[�����������炵���X�P�W���[�����A�w�肳�ꂽ�I�������܂ō쐬����B<br>
     * �J��Ԃ��̃X�P�W���[���}�X�^�łȂ��ꍇ�́A�P�����X�P�W���[�����쐬����B<br>
     * {@link Schedule}�̎����N���X�Ƃ���{@link DefaultSchedule}���g�p����B<br>
     *
     * @param date �쐬��
     * @param master �X�P�W���[���}�X�^
     * @return �X�P�W���[���̔z��
     * @exception ScheduleMakeException �X�P�W���[���̍쐬�Ɏ��s�����ꍇ
     */
    public Schedule[] makeSchedule(Date date, ScheduleMaster master)
     throws ScheduleMakeException{
        
        if(!master.isEnabled() || master.isTemplate()){
            return new Schedule[0];
        }
        
        if(!isNecessaryMake(date, master)){
            return new Schedule[0];
        }
        
        master.applyDate(date);
        
        checkScheduleMaster(master);
        
        if(master.getEndTime() == null
             || master.getStartTime().equals(master.getEndTime())
             || (master.getRepeatInterval() <= 0
                  && master.getRetryInterval() > 0)
        ){
            return new Schedule[]{
                makeSingleSchedule(master)
            };
        }else{
            return makeRepeatSchedule(master);
        }
    }
     
    public boolean isMakeSchedule(Date date, ScheduleMaster master)
     throws ScheduleMakeException{
        return isNecessaryMake(date, master);
    }
    
    /**
     * �X�P�W���[���}�X�^�̐��������`�F�b�N����B<p>
     * <ul>
     *   <li>�}�X�^ID�̕K�{�`�F�b�N�B</li>
     *   <li>�^�X�N���̕K�{�`�F�b�N�B</li>
     *   <li>�J�n�����̕K�{�`�F�b�N�B</li>
     *   <li>�I���������w�肳��Ă���ꍇ�ɁA�J�n����&lt;=�I�������ƂȂ��Ă��邩�̃`�F�b�N�B</li>
     *   <li>�I���������w�肳��Ă��āA�J�n����&lt;�I�������ƂȂ��Ă���ꍇ�ɁA�J��Ԃ��Ԋu&gt;0�ƂȂ��Ă��邩�̃`�F�b�N�B</li>
     *   <li>���g���C�I���������w�肳��Ă���ꍇ�ɁA�J�n����&lt;=���g���C�I�������ƂȂ��Ă��邩�̃`�F�b�N�B</li>
     *   <li>���g���C�Ԋu&gt;0�̏ꍇ�ɁA���g���C�I���������w�肳��Ă��邩�̃`�F�b�N�B</li>
     *   <li>���g���C�I���������w�肳��Ă���ꍇ�ɁA���g���C�Ԋu&lt;=0�ł��邩�̃`�F�b�N�B</li>
     *   <li>���g���C�I���������w�肳��Ă��ă��g���C�Ԋu&gt;0�̏ꍇ�ɁA�ŏ��̃��g���C����&lt;=���g���C�I�������ƂȂ��Ă��邩�̃`�F�b�N�B</li>
     * </ul>
     *
     * @param master �X�P�W���[���}�X�^
     * @exception IllegalScheduleMasterException �X�P�W���[���}�X�^���������Ȃ��ꍇ
     */
    protected void checkScheduleMaster(ScheduleMaster master)
     throws IllegalScheduleMasterException{
        
        if(master.getId() == null){
            throw new IllegalScheduleMasterException("Id is null.");
        }
        if(master.getTaskName() == null){
            throw new IllegalScheduleMasterException("TaskName is null. id=" + master.getId());
        }
        if(master.getStartTime() == null){
            throw new IllegalScheduleMasterException("StartTime is null. id=" + master.getId());
        }
        if(master.getEndTime() != null
            && master.getEndTime().before(master.getStartTime())){
            throw new IllegalScheduleMasterException("EndTime is before StartTime. id=" + master.getId());
        }
        if((master.getEndTime() != null
            && master.getEndTime().after(master.getStartTime()))
            && master.getRepeatInterval() <= 0){
            throw new IllegalScheduleMasterException("RepeatInterval <= 0. id=" + master.getId());
        }
        if(master.getRetryEndTime() != null
            && !master.getRetryEndTime().after(master.getStartTime())){
            throw new IllegalScheduleMasterException("RetryEndTime is before StartTime. id=" + master.getId());
        }
        if(master.getRetryInterval() <= 0 && master.getRetryEndTime() != null){
            throw new IllegalScheduleMasterException("RetryInterval <= 0. id=" + master.getId());
        }
        if(master.getRetryInterval() > 0 && master.getRetryEndTime() != null){
            final Calendar offset = Calendar.getInstance();
            offset.setTime(master.getStartTime());
            final Calendar end = Calendar.getInstance();
            end.setTime(master.getRetryEndTime());
            final Date firstRetryTime = calculateNextDate(
                offset,
                master.getRetryInterval(),
                end
            );
            if(firstRetryTime == null){
                throw new IllegalScheduleMasterException("First RetryTime is after RetryEndTime. id=" + master.getId());
            }
        }
    }
    
    /**
     * ���̓��t�ŁA�X�P�W���[�����쐬����K�v�����邩�ǂ����𔻒肷��B<p>
     * ���̎����ł́A�K��true��Ԃ��B<br>
     * ���t�ɑ΂��āA�X�P�W���[���̍쐬�L���𔻒f����K�v������ꍇ�́A�T�u�N���X�ŃI�[�o�[���C�h���鎖�B<br>
     *
     * @param date �쐬��
     * @param master �X�P�W���[���}�X�^
     * @return true�̏ꍇ�A���K�v������
     * @exception ScheduleMakeException ����Ɏ��s�����ꍇ
     */
    protected boolean isNecessaryMake(Date date, ScheduleMaster master)
     throws ScheduleMakeException{
        return true;
    }
    
    /**
     * �w�肳�ꂽ�X�P�W���[���}�X�^����A�J��Ԃ��Ȃ��X�P�W���[�����쐬����B<p>
     *
     * @param master �X�P�W���[���}�X�^
     * @return �X�P�W���[��
     */
    protected Schedule makeSingleSchedule(ScheduleMaster master)
     throws ScheduleMakeException{
        return new DefaultSchedule(
            master.getId(),
            master.getGroupIds(),
            master.getStartTime(),
            master.getTaskName(),
            master.getInput(),
            master.getDepends(),
            master.getDependsInGroupMap(),
            master.getDependsOnGroup(),
            master.getGroupDependsOnGroupMap(),
            master.getExecutorKey(),
            master.getExecutorType(),
            master.getRetryInterval(),
            master.getRetryEndTime(),
            master.getMaxDelayTime()
        );
    }
    
    /**
     * �w�肳�ꂽ�X�P�W���[���}�X�^����A�J��Ԃ��X�P�W���[�����쐬����B<p>
     *
     * @param master �X�P�W���[���}�X�^
     * @return �X�P�W���[���z��
     */
    protected Schedule[] makeRepeatSchedule(ScheduleMaster master)
     throws ScheduleMakeException{
        final List result = new ArrayList();
        Date time = master.getStartTime();
        final Calendar offset = Calendar.getInstance();
        offset.setTime(master.getStartTime());
        final Calendar end = Calendar.getInstance();
        end.setTime(master.getEndTime());
        do{
            result.add(
                new DefaultSchedule(
                    master.getId(),
                    master.getGroupIds(),
                    time,
                    master.getTaskName(),
                    master.getInput(),
                    master.getDepends(),
                    master.getDependsInGroupMap(),
                    master.getDependsOnGroup(),
                    master.getGroupDependsOnGroupMap(),
                    master.getExecutorKey(),
                    master.getExecutorType(),
                    master.getRetryInterval(),
                    master.getRetryEndTime(),
                    master.getMaxDelayTime()
                )
            );
        }while((time = calculateNextDate(offset, master.getRepeatInterval(), end)) != null);
        return (Schedule[])result.toArray(new Schedule[result.size()]);
    }
    
    /**
     * �J��Ԃ��X�P�W���[���̎��̃X�P�W���[���������v�Z����B<p>
     * ���̃X�P�W���[���������I���������߂����ꍇ�ɂ́Anull��Ԃ��B<br>
     *
     * @param offset ���݂̃X�P�W���[������
     * @param interval �J��Ԃ��Ԋu[ms]
     * @param end �X�P�W���[���̏I������
     * @return ���̃X�P�W���[������
     */
    protected Date calculateNextDate(
        Calendar offset,
        long interval,
        Calendar end
    ){
        if(interval > Integer.MAX_VALUE){
            long offsetInterval = interval;
            int tmpInterval = 0;
            do{
                if(offsetInterval >= Integer.MAX_VALUE){
                    tmpInterval = Integer.MAX_VALUE;
                }else{
                    tmpInterval = (int)offsetInterval;
                }
                offset.add(Calendar.MILLISECOND, tmpInterval);
                offsetInterval -= Integer.MAX_VALUE;
            }while(offsetInterval > 0);
        }else{
            offset.add(Calendar.MILLISECOND, (int)interval);
        }
        if(offset.after(end)){
            return null;
        }else{
            return offset.getTime();
        }
    }
}