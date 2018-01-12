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
package jp.ossc.nimbus.service.scheduler;

import java.util.*;

import jp.ossc.nimbus.core.*;

/**
 * {@link TimerSchedulerService}�T�[�r�X��MBean�C���^�t�F�[�X�B<p>
 * 
 * @author M.Takata
 */
public interface TimerSchedulerServiceMBean extends ServiceBaseMBean{
    
    /**
     * Timer�X���b�h���f�[�����X���b�h�ɂ��邩�ǂ�����ݒ肷��B<p>
     * �f�t�H���g�́Atrue�B<br>
     *
     * @param isDaemon �f�[�����X���b�h�ɂ���ꍇ��true
     */
    public void setDaemon(boolean isDaemon);
    
    /**
     * Timer�X���b�h���f�[�����X���b�h�ɂ��邩�ǂ����𔻒肷��B<p>
     *
     * @return true�̏ꍇ�́A�f�[�����X���b�h
     */
    public boolean isDaemon();
    
    /**
     * �T�[�r�X�̊J�n���ɃX�P�W���[�����J�n���邩�ǂ�����ݒ肷��B<p>
     * �f�t�H���g��false�B<p>
     *
     * @param isSchedule �T�[�r�X�̊J�n���ɃX�P�W���[�����J�n����ꍇtrue
     */
    public void setScheduleOnStart(boolean isSchedule);
    
    /**
     * �T�[�r�X�̊J�n���ɃX�P�W���[�����J�n���邩�ǂ����𔻒肷��B<p>
     *
     * @return true�̏ꍇ�A�T�[�r�X�̊J�n���ɃX�P�W���[�����J�n����
     */
    public boolean isScheduleOnStart();
    
    /**
     * {@link ScheduleFactory#getSchedules(Object)}�̈����Ŏw�肷��L�[��ݒ肷��B<p>
     * ���̑�����ݒ肵�Ȃ��ꍇ�́A�T�[�r�X�N�����_��java.util.Date�I�u�W�F�N�g���g�p����B<br>
     * �A���A{@link ScheduleFactory}��ݒ肵�Ă��Ȃ��ꍇ�͖����ł���B<br>
     *
     * @param key �X�P�W���[���t�@�N�g���ɓn���L�[
     */
    public void setScheduleFactoryKey(Object key);
    
    /**
     * {@link ScheduleFactory#getSchedules(Object)}�̈����Ŏw�肷��L�[���擾����B<p>
     *
     * @return �X�P�W���[���t�@�N�g���ɓn���L�[
     */
    public Object getScheduleFactoryKey();
    
    /**
     * �X�P�W���[���t�@�N�g���T�[�r�X�̃T�[�r�X����ݒ肷��B<p>
     *
     * @param name �X�P�W���[���t�@�N�g���T�[�r�X�̃T�[�r�X��
     */
    public void setScheduleFactoryServiceName(ServiceName name);
    
    /**
     * �X�P�W���[���t�@�N�g���T�[�r�X�̃T�[�r�X�����擾����B<p>
     *
     * @return �X�P�W���[���t�@�N�g���T�[�r�X�̃T�[�r�X��
     */
    public ServiceName getScheduleFactoryServiceName();
    
    /**
     * �X�P�W���[�����擾����B<p>
     *
     * @return �X�P�W���[���̔z��
     */
    public Schedule[] getSchedules();
    
    /**
     * �X�P�W���[���T�[�r�X�̃T�[�r�X����ݒ肷��B<p>
     *
     * @param names �X�P�W���[���T�[�r�X�̃T�[�r�X���z��
     */
    public void setScheduleServiceNames(ServiceName[] names);
    
    /**
     * �X�P�W���[���T�[�r�X�̃T�[�r�X�����擾����B<p>
     *
     * @return �X�P�W���[���T�[�r�X�̃T�[�r�X���z��
     */
    public ServiceName[] getScheduleServiceNames();
    
    /**
     * �X�P�W���[���̏�Ԃ��Ǘ�����{@link ScheduleStateManager}�T�[�r�X�̃T�[�r�X����ݒ肷��B<p>
     *
     * @param name ScheduleStateManager�T�[�r�X�̃T�[�r�X��
     */
    public void setScheduleStateManagerServiceName(ServiceName name);
    
    /**
     * �X�P�W���[���̏�Ԃ��Ǘ�����{@link ScheduleStateManager}�T�[�r�X�̃T�[�r�X�����擾����B<p>
     *
     * @return ScheduleStateManager�T�[�r�X�̃T�[�r�X��
     */
    public ServiceName getScheduleStateManagerServiceName();
    
    /**
     * �X�P�W���[����ǉ�����B<p>
     *
     * @param schedule �X�P�W���[��
     */
    public void addSchedule(TimerSchedule schedule);
    
    /**
     * �X�P�W���[�����擾����B<p>
     *
     * @param name �X�P�W���[����
     * @return �X�P�W���[��
     */
    public Schedule getSchedule(String name);
    
    /**
     * �X�P�W���[�����ēǂݍ��݂���B<p>
     *
     * @param name �X�P�W���[����
     */
    public void reloadSchedule(String name);
    
    /**
     * �X�P�W���[�����폜����B<p>
     *
     * @param name �X�P�W���[����
     */
    public void removeSchedule(String name);
    
    /**
     * �X�P�W���[�����������B<p>
     *
     * @param name �X�P�W���[����
     */
    public void cancelSchedule(String name);
    
    /**
     * �X�P�W���[����L���ɂ���B<p>
     *
     * @param name �X�P�W���[����
     */
    public void validateSchedule(String name);
    
    /**
     * �X�P�W���[���𖳌��ɂ���B<p>
     *
     * @param name �X�P�W���[����
     */
    public void invalidateSchedule(String name);
    
    /**
     * �X�P�W���[���������I�Ɏ��s����B<p>
     *
     * @param name �X�P�W���[����
     */
    public void executeSchedule(String name);
    
    /**
     * �X�P�W���[���������I�ɒx�����s����B<p>
     *
     * @param name �X�P�W���[����
     * @param delay �x������
     */
    public void executeSchedule(String name, long delay);
    
    /**
     * �X�P�W���[���������I�ɃX�P�W���[�����s����B<p>
     *
     * @param name �X�P�W���[����
     * @param time ���s����
     */
    public void executeSchedule(String name, Date time);
    
    /**
     * ���s���̃X�P�W���[���̖��O���擾����B<p>
     *
     * @return ���s���̃X�P�W���[���̖��O�W��
     */
    public Collection runningScheduleNames();
    
    /**
     * ���s���̃X�P�W���[�����擾����B<p>
     *
     * @return ���s���̃X�P�W���[���̏W��
     */
    public Collection runningSchedules();
    
    /**
     * ���������X�P�W���[���̖��O���擾����B<p>
     *
     * @return ���������X�P�W���[���̖��O�W��
     */
    public Collection closedScheduleNames();
    
    /**
     * ���������X�P�W���[�����擾����B<p>
     *
     * @return ���������X�P�W���[���̏W��
     */
    public Collection closedSchedules();
    
    /**
     * ���ݗL���ȃX�P�W���[�����擾����B<p>
     *
     * @return ���ݗL���ȃX�P�W���[���̏W��
     */
    public Collection validSchedules();
    
    /**
     * ���ݗL���ȃX�P�W���[���̖��O���擾����B<p>
     *
     * @return ���ݗL���ȃX�P�W���[���̖��O�W��
     */
    public Collection validScheduleNames();
    
    /**
     * �G���[�I�������X�P�W���[�����擾����B<p>
     *
     * @return �G���[�I�������X�P�W���[���̏W��
     */
    public Collection errorSchedules();
    
    /**
     * �G���[�I�������X�P�W���[���̖��O���擾����B<p>
     *
     * @return �G���[�I�������X�P�W���[���̖��O�W��
     */
    public Collection errorScheduleNames();
}