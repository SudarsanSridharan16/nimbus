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
 * {@link OHLCDatasetFactoryService}��MBean�C���^�t�F�[�X�B<p>
 *
 * @author M.Takata
 */
public interface OHLCDatasetFactoryServiceMBean extends ServiceBaseMBean{
    
    /**
     * ������Ԗ���OHLC���W�v����ꍇ�ɁAOHLC�̎����Ƃ��āA���Ԃ̊J�n���Ԃ��̗p�����ʁB<p>
     */
    public static final int COLLATE_DATA_DATE_TYPE_START = 1;
    
    /**
     * ������Ԗ���OHLC���W�v����ꍇ�ɁAOHLC�̎����Ƃ��āA���Ԃ̏I�����Ԃ��̗p�����ʁB<p>
     */
    public static final int COLLATE_DATA_DATE_TYPE_END = 2;
    
    /**
     * �w�肳�ꂽ���ԃt�B�[���h�Ǝw�肳�ꂽ���Ԃ̒����ŁA�f�[�^���܂Ƃ߂���Ԃ�ݒ肷��B<p>
     * 
     * @param field ���ԃt�B�[���h
     * @param period ���Ԃ̒���
     */
    public void setCollateDataPeriod(int field, int period);
    
    /**
     * ������Ԗ���OHLC���W�v����ꍇ�ɁAOHLC�̎������ǂ̂悤�ɍ̗p���邩��ݒ肷��B<p>
     * �f�t�H���g�́A{@link #COLLATE_DATA_DATE_TYPE_START}�B<br>
     *
     * @param type OHLC�̎����̗̍p���@
     * @see #COLLATE_DATA_DATE_TYPE_START
     * @see #COLLATE_DATA_DATE_TYPE_END
     */
    public void setCollateDataDateType(int type);
    
    /**
     * ������Ԗ���OHLC���W�v����ꍇ�ɁAOHLC�̎������ǂ̂悤�ɍ̗p���邩���擾����B<p>
     *
     * @return OHLC�̎����̗̍p���@
     */
    public int getCollateDataDateType();
}
