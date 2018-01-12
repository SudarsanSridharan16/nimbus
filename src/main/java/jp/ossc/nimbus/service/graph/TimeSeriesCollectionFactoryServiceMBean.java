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
package jp.ossc.nimbus.service.graph;

import jp.ossc.nimbus.core.ServiceBaseMBean;

/**
 * {@link TimeSeriesCollectionFactoryService}��MBean�C���^�t�F�[�X�B<p>
 *
 * @author M.Takata
 */
public interface TimeSeriesCollectionFactoryServiceMBean extends ServiceBaseMBean{
    
    /** �l���܂Ƃ߂鏈���^�C�v : �J�n */
    public static final int COLLATE_DATA_TYPE_START = 1;
    /** �l���܂Ƃ߂鏈���^�C�v : �I�� */
    public static final int COLLATE_DATA_TYPE_END = 2;
    /** �l���܂Ƃ߂鏈���^�C�v : �S�� */
    public static final int COLLATE_DATA_TYPE_ALL = 3;
    /** �l���܂Ƃ߂鏈���^�C�v : ���� */
    public static final int COLLATE_DATA_TYPE_AVERAGE = 4;
    /** �l���܂Ƃ߂鏈���^�C�v : OHLC */
    public static final int COLLATE_DATA_TYPE_OHLC = 5;
    /** �l���܂Ƃ߂鏈���^�C�v : ���v */
    public static final int COLLATE_DATA_TYPE_SUM = 6;
    
    /**
     * ������Ԗ��Ƀf�[�^���W�v��1�_�ɏW�񂷂�ꍇ�ɁA����1�_�̎����Ƃ��āA���Ԃ̊J�n���Ԃ��̗p�����ʁB<p>
     */
    public static final int COLLATE_DATA_DATE_TYPE_START = 1;
    
    /**
     * ������Ԗ��Ƀf�[�^���W�v��1�_�ɏW�񂷂�ꍇ�ɁA����1�_�̎����Ƃ��āA���Ԃ̏I�����Ԃ��̗p�����ʁB<p>
     */
    public static final int COLLATE_DATA_DATE_TYPE_END = 2;
    
    /**
     * �f�[�^�Z�b�g����ݒ肷��B<p>
     * �f�t�H���g�́A�T�[�r�X���B<br>
     *
     * @param name �f�[�^�Z�b�g��
     */
    public void setName(String name);
    
    /**
     * �f�[�^�Z�b�g�����擾����B<p>
     *
     * @return �f�[�^�Z�b�g��
     */
    public String getName();
    
    /**
     * �w�肳�ꂽ�V���[�Y����TimePeriod�N���X��ݒ肷��B<p>
     * 
     * @param seriesName �V���[�Y��
     * @param clazz TimePeriod�N���X
     */
    public void setTimePeriodClass(String seriesName, Class clazz);
    
    /**
     * �w�肳�ꂽ�V���[�Y����TimePeriod�N���X���擾����B<p>
     * 
     * @param seriesName �V���[�Y��
     * @return TimePeriod�N���X
     */
    public Class getTimePeriodClass(String seriesName);
    
    /**
     * �l���܂Ƃ߂�ꍇ�̏����^�C�v��ݒ肷��B<p>
     * 
     * @param type �����^�C�v
     */
    public void setCollateDataType(int type);
    
    /**
     * �l���܂Ƃ߂�ꍇ�̏����^�C�v���擾����B<p>
     * 
     * @return �����^�C�v
     */
    public int getCollateDataType();
    
    /**
     * ���l�𖳎����邩���擾����B<p>
     * 
     * @return true:��������/false:�������Ȃ�
     */
    public boolean isIgnoreSameValue();
    
    /**
     * ���l�𖳎����邩��ݒ肷��B<p>
     * 
     * @param isIgnore true:��������/false:�������Ȃ�
     */
    public void setIgnoreSameValue(boolean isIgnore);
    
    /**
     * �w�肳�ꂽ���ԃt�B�[���h�Ǝw�肳�ꂽ���Ԃ̒����ŁA�f�[�^���܂Ƃ߂���Ԃ�ݒ肷��B<p>
     * 
     * @param field ���ԃt�B�[���h
     * @param period ���Ԃ̒���
     */
    public void setCollateDataPeriod(int field, int period);
    
    /**
     * �w�肳�ꂽ���ԃt�B�[���h�Ǝw�肳�ꂽ���Ԃ̒����ŁA���͑Ώۂ̃f�[�^���ǂ��������Ԃœ����Ă���̂���ݒ肷��B<p>
     * 
     * @param field ���ԃt�B�[���h
     * @param period ���Ԃ̒���
     */
    public void setInputDataPeriod(int field, int period);
    
    /**
     * �����������@�\���g�p���邩�ݒ肷��B<p>
     * 
     * @param isAuto true:��������������/false:�������������Ȃ�
     */
    public void setAutoTimesharing(boolean isAuto);
    
    /**
     * �����������@�\���g�p���邩���擾����B<P>
     * 
     * @return true:��������������/false:�������������Ȃ�
     */
    public boolean isAutoTimesharing();
    
    /**
     * ������Ԗ��Ƀf�[�^���W�v��1�_�ɏW�񂷂�ꍇ�ɁA����1�_�̎������ǂ̂悤�ɍ̗p���邩��ݒ肷��B<p>
     * �f�t�H���g�́A{@link #COLLATE_DATA_DATE_TYPE_START}�B<br>
     *
     * @param type �����̗̍p���@
     * @see #COLLATE_DATA_DATE_TYPE_START
     * @see #COLLATE_DATA_DATE_TYPE_END
     */
    public void setCollateDataDateType(int type);
    
    /**
     * ������Ԗ��Ƀf�[�^���W�v��1�_�ɏW�񂷂�ꍇ�ɁA����1�_�̎������ǂ̂悤�ɍ̗p���邩���擾����B<p>
     *
     * @return �����̗̍p���@
     */
    public int getCollateDataDateType();
}
