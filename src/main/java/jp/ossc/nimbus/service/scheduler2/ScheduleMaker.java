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

/**
 * �X�P�W���[���쐬�B<p>
 * �X�P�W���[���}�X�^����w�肳�ꂽ���t�̃X�P�W���[�����쐬����B<p>
 * �X�P�W���[���}�X�^�́A���t�̊T�O�������Ȃ����߁A�w�肳�ꂽ���t�ł͕K�v�̂Ȃ��X�P�W���[���ł���ꍇ������B<br>
 * �܂��A�X�P�W���[���}�X�^�́A�J��Ԃ��̊T�O�����Ă邽�߁A�X�P�W���[���}�X�^�ƃX�P�W���[���̊֌W�́A1:n�ɂȂ�B<br>
 *
 * @author M.Takata
 */
public interface ScheduleMaker{
    
    /**
     * �w�肳�ꂽ�X�P�W���[���}�X�^����X�P�W���[�����쐬����B<p>
     *
     * @param date �쐬��
     * @param master �X�P�W���[���}�X�^
     * @return �X�P�W���[���̔z��
     * @exception ScheduleMakeException �X�P�W���[���̍쐬�Ɏ��s�����ꍇ
     */
    public Schedule[] makeSchedule(Date date, ScheduleMaster master)
     throws ScheduleMakeException;
     
    /**
     * �w�肳�ꂽ�X���t�Ɏw�肳�ꂽ�X�P�W���[���}�X�^����X�P�W���[�����쐬���邩�ǂ����𔻒肷��B<p>
     *
     * @param date �쐬��
     * @param master �X�P�W���[���}�X�^
     * @return �X�P�W���[�����쐬����ꍇtrue
     * @exception ScheduleMakeException �X�P�W���[���̍쐬����Ɏ��s�����ꍇ
     */
    public boolean isMakeSchedule(Date date, ScheduleMaster master)
     throws ScheduleMakeException;
}