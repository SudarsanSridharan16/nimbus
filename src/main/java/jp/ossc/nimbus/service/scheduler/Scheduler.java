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

/**
 * �X�P�W���[���B<p>
 * �X�P�W���[�����������ׂ��C���^�t�F�[�X�ł���B<br>
 *
 * @author M.Takata
 */
public interface Scheduler{
    
    /**
     * �X�P�W���[�����J�n����B<p>
     *
     * @param key ���s����X�P�W���[���̃L�[
     */
    public void startSchedule(Object key);
    
    /**
     * �X�P�W���[�����~����B<p>
     */
    public void stopSchedule();
    
    /**
     * �X�P�W���[�����I������܂őҋ@����B<p>
     */
    public void waitUntilScheduleClose();
    
    /**
     * �X�P�W���[�����I������܂őҋ@����B<p>
     *
     * @param timeout �^�C���A�E�g[ms]
     * @return �^�C���A�E�g�����ꍇfalse
     */
    public boolean waitUntilScheduleClose(long timeout);
    
    /**
     * �w�肳�ꂽ�L�[�ŃX�P�W���[�������X�P�W���[������w�肳�ꂽ���O�̃X�P�W���[�����擾����B<p>
     *
     * @param key �X�P�W���[���̃L�[
     * @param name �X�P�W���[����
     * @return �X�P�W���[��
     */
    public Schedule getSchedule(Object key, String name);
    
    /**
     * �w�肳�ꂽ�L�[�ŃX�P�W���[�������S�ẴX�P�W���[�����擾����B<p>
     *
     * @param key �X�P�W���[���̃L�[
     * @return �X�P�W���[���̔z��
     */
    public Schedule[] getSchedules(Object key);
    
    /**
     * ���݃X�P�W���[������Ă���X�P�W���[������w�肳�ꂽ���O�̃X�P�W���[�����擾����B<p>
     *
     * @param name �X�P�W���[����
     * @return �X�P�W���[��
     */
    public Schedule getSchedule(String name);
    
    /**
     * ���݃X�P�W���[������Ă���S�ẴX�P�W���[�����擾����B<p>
     *
     * @return �X�P�W���[���̔z��
     */
    public Schedule[] getSchedules();
    
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
}