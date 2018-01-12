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
package jp.ossc.nimbus.service.sequence;

import java.text.NumberFormat;

import jp.ossc.nimbus.core.*;

/**
 * {@link NumberSequenceService}��MBean�C���^�t�F�[�X�B<p>
 * 
 * @author M.Takata
 */
public interface NumberSequenceServiceMBean extends ServiceBaseMBean{
    
    /**
     * �����l��ݒ肷��B<p>
     * ������ >= 0�̏ꍇ�́A�ő�l�ȉ��̒l�ɂ��Ȃ���΂Ȃ�Ȃ��B<br>
     * ������ < 0�̏ꍇ�́A�ŏ��l�ȏ�̒l�ɂ��Ȃ���΂Ȃ�Ȃ��B<br>
     * �f�t�H���g�́A0�B<p>
     *
     * @param value �����l
     */
    public void setInitialValue(long value);
    
    /**
     * �����l���擾����B<p>
     *
     * @return �����l
     */
    public long getInitialValue();
    
    /**
     * �ŏ��l��ݒ肷��B<p>
     * �ő�l��菬���Ȓl�ɂ��Ȃ���΂Ȃ�Ȃ��B<br>
     * �f�t�H���g�́A0�B<p>
     *
     * @param value �ŏ��l
     */
    public void setMinValue(long value);
    
    /**
     * �ŏ��l���擾����B<p>
     *
     * @return �ŏ��l
     */
    public long getMinValue();
    
    /**
     * �ő�l��ݒ肷��B<p>
     * �ŏ��l���傫�Ȓl�ɂ��Ȃ���΂Ȃ�Ȃ��B<br>
     * �f�t�H���g�́A0�B<p>
     *
     * @param value �ő�l
     */
    public void setMaxValue(long value);
    
    /**
     * �ő�l���擾����B<p>
     *
     * @return �ő�l
     */
    public long getMaxValue();
    
    /**
     * �����ʂ�ݒ肷��B<p>
     * �������������ꍇ�́A���̒l��ݒ肷��B<br>
     * �f�t�H���g�́A1�B<p>
     *
     * @param value ������
     */
    public void setIncrementValue(long value);
    
    /**
     * �����ʂ��擾����B<p>
     *
     * @return ������
     */
    public long getIncrementValue();
    
    /**
     * �t�H�[�}�b�g��������w�肷��B<p>
     * �w�肳�ꂽ�t�H�[�}�b�g������ŁAjava.text.DecimalFormat���g���ăt�H�[�}�b�g����B<br>
     * �w�肵�Ȃ��ꍇ�́AString.valueOf(long)�ŕ�����ɕϊ������B<br>
     *
     * @param format �t�H�[�}�b�g������
     */
    public void setFormat(String format);
    
    /**
     * �t�H�[�}�b�g��������擾����B<p>
     *
     * @return �t�H�[�}�b�g������
     */
    public String getFormat();
    
    /**
     * �t�H�[�}�b�g���w�肷��B<p>
     * �w�肵�Ȃ��ꍇ�́AString.valueOf(long)�ŕ�����ɕϊ������B<br>
     *
     * @param format �t�H�[�}�b�g
     */
    public void setNumberFormat(NumberFormat format);
    
    /**
     * �t�H�[�}�b�g���擾����B<p>
     *
     * @return �t�H�[�}�b�g
     */
    public NumberFormat getNumberFormat();
    
    /** 
     * ���ݔ��ԍς݂̍ŐV�̔ԍ��l���擾����B<p>
     * 
     * @return ���ݔ��ԍς݂̍ŐV�̔ԍ��l
     */ 
    public long getCurrentValue();
    
    /**
     * �J�n�ԍ����擾����B<p>
     * 
     * @return �J�n�ԍ�
     */
    public String getInitial();
    
    /** 
     * ���ݔ��ԍς݂̍ŐV�̔ԍ����擾����B<p>
     * 
     * @return ���ݔ��ԍς݂̍ŐV�̔ԍ�
     */ 
    public String getCurrent();
    
    /**
     * ���Ԃ�����������B<p>
     */
    public void reset();
}
