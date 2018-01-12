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

import jp.ossc.nimbus.core.*;

/**
 * {@link KeyMappingScheduleFactoryService}�T�[�r�X��MBean�C���^�t�F�[�X�B<p>
 * 
 * @author M.Takata
 */
public interface KeyMappingScheduleFactoryServiceMBean extends ServiceBaseMBean{
    
    /**
     * �L�[��{@link ScheduleFactory}�T�[�r�X�̃T�[�r�X���̃}�b�s���O��ݒ肷��B<p>
     * �u�L�[=ScheduleFactory�T�[�r�X�̃T�[�r�X���v�̃t�H�[�}�b�g�Ŏw�肷��B<br>
     *
     * @param mapping �L�[��ScheduleFactory�T�[�r�X�̃T�[�r�X���̃}�b�s���O
     */
    public void setKeyAndScheduleFactoryServiceName(String[] mapping);
    
    /**
     * �L�[��{@link ScheduleFactory}�T�[�r�X�̃T�[�r�X���̃}�b�s���O���擾����B<p>
     *
     * @return �L�[��ScheduleFactory�T�[�r�X�̃T�[�r�X���̃}�b�s���O
     */
    public String[] getKeyAndScheduleFactoryServiceName();
    
    /**
     * �L�[�}�b�s���O�ɊY�����Ȃ��X�P�W���[�����擾����{@link ScheduleFactory}�T�[�r�X�̃T�[�r�X����ݒ肷��B<p>
     * ���̑�����ݒ肵�Ă��Ȃ��ꍇ�́A�w�肳�ꂽ�L�[�ɊY������X�P�W���[����������Ȃ��ꍇ�́A����0�̃X�P�W���[���z���Ԃ��B<br>
     *
     * @param name ScheduleFactory�T�[�r�X�̃T�[�r�X��
     */
    public void setDefaultScheduleFactoryServiceName(ServiceName name);
    
    /**
     * �L�[�}�b�s���O�ɊY�����Ȃ��X�P�W���[�����擾����{@link ScheduleFactory}�T�[�r�X�̃T�[�r�X�����擾����B<p>
     *
     * @return ScheduleFactory�T�[�r�X�̃T�[�r�X��
     */
    public ServiceName getDefaultScheduleFactoryServiceName();
}