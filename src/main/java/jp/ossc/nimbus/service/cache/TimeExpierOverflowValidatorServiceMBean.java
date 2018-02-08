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
package jp.ossc.nimbus.service.cache;

import jp.ossc.nimbus.core.ServiceBaseMBean;

/**
 * {@link TimeExpierOverflowValidatorService}��MBean�C���^�t�F�[�X<p>
 * 
 * @author M.Takata
 * @see TimeExpierOverflowValidatorService
 */
public interface TimeExpierOverflowValidatorServiceMBean
 extends ServiceBaseMBean{
    
    /**
     * �L���b�V���̗L������[ms]��ݒ肷��B<p>
     * �L���Ȓl�͈̔͂́A0�ȏ�B�f�t�H���g��0�ŁA���Ȃ��B<br>
     *
     * @param millis �L���b�V���̗L������[ms]
     */
    public void setExpierTerm(long millis) throws IllegalArgumentException;
    
    /**
     * �L���b�V���̗L������[ms]���擾����B<p>
     *
     * @return �L���b�V���̗L������[ms]
     */
    public long getExpierTerm();
    
    /**
     * �L���b�V���̗L����؂�[ms]��ݒ肷��B<p>
     * 
     * @param millis �L���b�V���̗L����؂�[ms]
     * @throws IllegalArgumentException
     */
    public void setPeriod(long millis) throws IllegalArgumentException;
    
    /**
     * �L���b�V���̗L����؂�[ms]���擾����B<p>
     * 
     * @return �L���b�V���̗L����؂�[ms]
     */
    public long getPeriod();

    /**
     * �L���b�V���������؂��邽�߂ɕێ����Ă����������������B<p>
     */
    public void reset();
    
    /**
     * ���ӂꌟ�؂��s���B<p>
     *
     * @return ���ӂꌟ�؂��s�������ʂ��ӂꂪ��������ꍇ�A���ӂꐔ��Ԃ��B���ӂ�Ȃ��ꍇ�́A0��Ԃ�
     */
    public int validate();
    
    /**
     * ���ӂꌟ�ؑΏۂɂȂ��Ă���L���b�V�������擾����B<p>
     *
     * @return �L���b�V����
     */
    public int size();
}