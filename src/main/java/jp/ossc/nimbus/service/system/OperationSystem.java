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
package jp.ossc.nimbus.service.system;

/**
 * �I�y���[�V�����V�X�e���B<p>
 *
 * @author M.Takata
 */
public interface OperationSystem{
    
    /**
     * �I�y���[�V�����V�X�e�������擾����B<p>
     *
     * @return �I�y���[�V�����V�X�e����
     */
    public String getName();
    
    /**
     * CPU�����擾����B<p>
     *
     * @return CPU��
     */
    public int getCpuNumbers();
    
    /**
     * CPU�̃N���b�N�����擾����B<p>
     *
     * @return CPU�N���b�N��
     */
    public long getCpuFrequency();
    
    /**
     * �I�y���[�V�����V�X�e�����N�����Ă���̌o�ߎ���[s]���擾����B<p>
     *
     * @return �I�y���[�V�����V�X�e�����N�����Ă���̌o�ߎ���[s]
     */
    public long getUptimeInSeconds();
    
    /**
     * CPU�̎g�p���Ԃ��擾����B<p>
     *
     * @return CPU�g�p����
     */
    public CpuTimes getCpuTimes();
    
    /**
     * �O��CPU�g�p���Ԃ��擾����������̍���CPU�g�p���Ԃ��擾����B<p>
     *
     * @return ����CPU�g�p����
     */
    public CpuTimes getCpuTimesDelta();
    
    /**
     * �����Ŏw�肵��CPU�g�p���Ԃ���̍���CPU�g�p���Ԃ��擾����B<p>
     *
     * @return ����CPU�g�p����
     */
    public CpuTimes getCpuTimesDelta(CpuTimes prev);
    
    /**
     * �����������̎g�p�󋵂��擾����B<p>
     *
     * @return �����������g�p��
     */
    public MemoryInfo getPhysicalMemoryInfo();
    
    /**
     * �X���b�v�������̎g�p�󋵂��擾����B<p>
     *
     * @return �X���b�v�������g�p��
     */
    public MemoryInfo getSwapMemoryInfo();
    
    /**
     * ����Java�v���Z�X�̃v���Z�XID���擾����B<p>
     *
     * @return �v���Z�XID
     */
    public int getPid();
    
    /**
     * �w�肳�ꂽ�v���Z�X���I��������B<p>
     *
     * @param pid �v���Z�XID
     * @return �v���Z�X�����݂��Ȃ��ꍇfalse
     */
    public boolean kill(int pid);
    
    /**
     * �w�肳�ꂽ�v���Z�X�̏����擾����B<p>
     *
     * @param pid �v���Z�XID
     * @return �v���Z�X���
     */
    public ProcessInfo getProcessInfo(int pid);
    
    /**
     * �w�肳�ꂽ�v���Z�X���I��������B<p>
     *
     * @param command �R�}���h�̐��K�\��
     * @return �v���Z�X�����݂��Ȃ��ꍇfalse
     */
    public boolean kill(String command);
    
    /**
     * �w�肳�ꂽ�v���Z�X�̏����擾����B<p>
     *
     * @param command �R�}���h�̐��K�\��
     * @return �v���Z�X���
     */
    public ProcessInfo getProcessInfo(String command);
    
    /**
     * �S�Ẵv���Z�X�̏����擾����B<p>
     *
     * @return �v���Z�X���̔z��
     */
    public ProcessInfo[] getProcessInfos();
}
