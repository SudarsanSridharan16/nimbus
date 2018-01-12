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

import java.util.Map;

/**
 * {@link CalculateMemorySizeOverflowValidatorService}��MBean�C���^�t�F�[�X<p>
 * 
 * @author M.Takata
 * @see CalculateMemorySizeOverflowValidatorService
 */
public interface CalculateMemorySizeOverflowValidatorServiceMBean
 extends ServiceBaseMBean{
    
    /**
     * �g�p�������̍ő�T�C�Y��ݒ肷��B<p>
     * �L���Ȓl�͈̔͂́A0�ȏ�B�f�t�H���g��{@link Runtime#maxMemory()}�Ŏ擾�ł���l / 2�B���̃��\�b�h���T�|�[�g����Ă��Ȃ��ꍇ�́A32MByte�B<br>
     * �P�ʂ��w�肵�Ȃ��ꍇ�́A�o�C�g�P�ʁB�P�ʂ��w�肷��ꍇ�́A"K"�̏ꍇ�̓L���o�C�g�P�ʁB"M"�̏ꍇ�̓��K�o�C�g�P�ʁB"G"�̏ꍇ�̓M�K�o�C�g�P�ʁB
     *
     * @param size �g�p�������̍ő�T�C�Y
     * @exception IllegalArgumentException ���l�łȂ�������A���̒l�A���e����Ȃ��P�ʕ������w�肵���ꍇ
     */
    public void setMaxMemorySize(String size) throws IllegalArgumentException;
    
    /**
     * �g�p�������̍ő�T�C�Y���擾����B<p>
     *
     * @return �g�p�������̍ő�T�C�Y
     */
    public String getMaxMemorySize();
    
    /**
     * �I�u�W�F�N�g�ɐ錾����Ă���getter���\�b�h�Ŏ擾�ł���primitive�^�ȊO�̃I�u�W�F�N�g�̃T�C�Y���v�Z���ĉ��Z���邩�ǂ�����ݒ肷��B<p>
     * �f�t�H���g��false�ŉ��Z���Ȃ��B<br>
     *
     * @param isCalculate ���Z����ꍇ��true
     */
    public void setCalculateProperty(boolean isCalculate);
    
    /**
     * �I�u�W�F�N�g�ɐ錾����Ă���getter���\�b�h�Ŏ擾�ł���primitive�^�ȊO�̃I�u�W�F�N�g�̃T�C�Y���v�Z���ĉ��Z���邩�ǂ����𔻒肷��B<p>
     *
     * @return true�̏ꍇ�A���Z����
     */
    public boolean isCalculateProperty();
    
    /**
     * ���ӂꌟ�؎��Ƀ������g�p�ʂ��v�Z���������ǂ�����ݒ肷��B<p>
     * �f�t�H���g��false�ŁA�L���b�V�����ꂽ���_�Ń������g�p�ʂ��v�Z����B<br>
     * ���̏ꍇ�A�L���b�V����Ɏg�p�ʂ��ς���Ă��v�Z����Ȃ��B<br>
     *
     * @param isCalculate ���ӂꌟ�؎��Ƀ������g�p�ʂ��v�Z����ꍇ��true
     */
    public void setCalculateOnValidate(boolean isCalculate);
    
    /**
     * ���ӂꌟ�؎��Ƀ������g�p�ʂ��v�Z���������ǂ����𔻒肷��B<p>
     *
     * @return true�̏ꍇ�A���ӂꌟ�؎��Ƀ������g�p�ʂ��v�Z����
     */
    public boolean isCalculateOnValidate();
    
    /**
     * ���ӂꌟ�؂����s���邽�߂ɕێ����Ă����������������B<p>
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
    
    /**
     * ���݂̃������g�p��[byte]���擾����B<p>
     *
     * @return ���݂̃������g�p��[byte]
     */
    public long getCurrentUsedMemorySize();
    
    /**
     * �w�肵���N���X�̃������T�C�Y�𗝘_�l�Ƃ��Đݒ肷��B<p>
     * �P�ʂ��w�肵�Ȃ��ꍇ�́A�o�C�g�P�ʁB�P�ʂ��w�肷��ꍇ�́A"K"�̏ꍇ�̓L���o�C�g�P�ʁB"M"�̏ꍇ�̓��K�o�C�g�P�ʁB"G"�̏ꍇ�̓M�K�o�C�g�P�ʁB
     *
     * @param className �N���X��
     * @param size �������T�C�Y
     * @exception ClassNotFoundException �w�肳�ꂽ�N���X��������Ȃ��ꍇ
     */
    public void setMemorySize(String className, String size)
     throws ClassNotFoundException;
    
    /**
     * �w�肵���N���X�̃������T�C�Y�̗��_�l���擾����B<p>
     *
     * @param className �N���X��
     * @return ���_�������T�C�Y
     * @exception ClassNotFoundException �w�肳�ꂽ�N���X��������Ȃ��ꍇ
     */
    public String getMemorySize(String className) throws ClassNotFoundException;
    
    /**
     * �o�^����Ă���N���X�ƃ������T�C�Y���_�l�̃}�b�s���O���擾����B<p>
     *
     * @return �N���X�ƃ������T�C�Y���_�l�̃}�b�s���O
     */
    public Map getMemorySizeMap();
}
