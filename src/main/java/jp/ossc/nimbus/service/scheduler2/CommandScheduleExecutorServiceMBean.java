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

/**
 * {@link CommandScheduleExecutorService}��MBean�C���^�t�F�[�X�B<p>
 * 
 * @author M.Takata
 */
public interface CommandScheduleExecutorServiceMBean extends AbstractScheduleExecutorServiceMBean{
    
    /**
     * �f�t�H���g�̃X�P�W���[�����s��ʁB<p>
     */
    public static final String DEFAULT_EXECUTOR_TYPE = "COMMAND";
    
    /**
     * ��ƃf�B���N�g����ݒ肷��B<p>
     * �w�肵�Ȃ��ꍇ�́AJavaVM�̍�ƃf�B���N�g���B<br>
     *
     * @param path ��ƃf�B���N�g���̃p�X
     */
    public void setWorkDirectory(String path);
    
    /**
     * ��ƃf�B���N�g�����擾����B<p>
     *
     * @return ��ƃf�B���N�g���̃p�X
     */
    public String getWorkDirectory();
    
    /**
     * ���ϐ���ݒ肷��B<p>
     *
     * @param env ���ϐ��̔z��
     */
    public void setEnvironmentVariables(String[] env);
    
    /**
     * ���ϐ����擾����B<p>
     *
     * @return ���ϐ��̔z��
     */
    public String[] getEnvironmentVariables();
    
    /**
     * �v���Z�X�̏I���҂�������ꍇ�ɁA�v���Z�X���I�����������`�F�b�N����Ԋu[ms]��ݒ肷��B<p>
     * �f�t�H���g�́A1�b�B<br>
     *
     * @param interval �`�F�b�N�Ԋu[ms]
     */
    public void setCheckInterval(long interval);
    
    /**
     * �v���Z�X�̏I���҂�������ꍇ�ɁA�v���Z�X���I�����������`�F�b�N����Ԋu[ms]���擾����B<p>
     *
     * @return �`�F�b�N�Ԋu[ms]
     */
    public long getCheckInterval();
}
