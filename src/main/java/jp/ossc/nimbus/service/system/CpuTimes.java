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
 * CPU�g�p���ԁB<p>
 *
 * @author M.Takata
 */
public interface CpuTimes{
    
    /**
     * ���[�U�v���Z�X��CPU�g�p���Ԃ��擾����B<p>
     * 
     * @return ���[�U�v���Z�X��CPU�g�p����[ms]
     */
    public long getUserTimeMillis();
    
    /**
     * �V�X�e����CPU�g�p���Ԃ��擾����B<p>
     * 
     * @return �V�X�e����CPU�g�p����[ms]
     */
    public long getSystemTimeMillis();
    
    /**
     * CPU���g�p���Ԃ��擾����B<p>
     * 
     * @return CPU���g�p����[ms]
     */
    public long getIdleTimeMillis();
    
    /**
     * CPU���g�p���Ԃ��擾����B<p>
     * 
     * @return CPU���g�p����[ms]
     */
    public long getTotalTimeMillis();
    
    /**
     * ���[�U�v���Z�X��CPU�g�p�����擾����B<p>
     * 
     * @return ���[�U�v���Z�X��CPU�g�p��
     */
    public float getUserRate();
    
    /**
     * �V�X�e����CPU�g�p�����擾����B<p>
     * 
     * @return �V�X�e����CPU�g�p��
     */
    public float getSystemRate();
    
    /**
     * CPU���g�p�����擾����B<p>
     * 
     * @return CPU���g�p��
     */
    public float getIdleRate();
    
    /**
     * ����CPU�g�p���ԂɎw�肳�ꂽCPU�g�p���Ԃ����Z����B<p>
     *
     * @param times ���Z����CPU�g�p����
     */
    public void add(CpuTimes times);
}