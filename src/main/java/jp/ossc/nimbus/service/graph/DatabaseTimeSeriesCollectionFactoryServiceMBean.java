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

import jp.ossc.nimbus.core.ServiceName;

/**
 * {@link DatabaseTimeSeriesCollectionFactoryService}��MBean�C���^�t�F�[�X�B<p>
 */
public interface DatabaseTimeSeriesCollectionFactoryServiceMBean
 extends TimeSeriesCollectionFactoryServiceMBean{
    
    /** �f�t�H���g�t�F�b�`�T�C�Y */
    public static final int DEFAULT_FETCH_SIZE = 10000;
    
    /**
     * ���t�t�H�[�}�b�g�p�^�[����ݒ肷��B<p>
     * 
     * @param pattern ���t�t�H�[�}�b�g�p�^�[��
     */
    public void setDateFormatPattern(String pattern);
    
    /**
     * ���t�t�H�[�}�b�g�p�^�[�����擾����B<p>
     * 
     * @return ���t�t�H�[�}�b�g�p�^�[��
     */
    public String getDateFormatPattern();
    
    /**
     * ���t�t�H�[�}�b�g�T�[�r�X����ݒ肷��B<p>
     * 
     * @param name �T�[�r�X��
     */
    public void setDateFormatServiceName(ServiceName name);
    
    /**
     * ���t�t�H�[�}�b�g�T�[�r�X�����擾����B<p>
     * 
     * @return �T�[�r�X��
     */
    public ServiceName getDateFormatServiceName();
    
    /**
     * ���t�J��������ݒ肷��B<p>
     * 
     * @param columnName ���t�J������
     */
    public void setDateColumnName(String columnName);
    
    /**
     * ���t�J���������擾����B<p>
     * 
     * @return ���t�J������
     */
    public String getDateColumnName();
    
    /**
     * �����J��������ݒ肷��B<p>
     * 
     * @param columnName �����J������
     */
    public void setTimeColumnName(String columnName);
    
    /**
     * �����J���������擾����B<p>
     * 
     * @return �����J������
     */
    public String getTimeColumnName();
    
    /**
     * �l�J��������ݒ肷��B<p>
     * 
     * @param columnName �l�J������
     */
    public void setValueColumnName(String columnName);
    
    /**
     * �l�J���������擾����B<p>
     * 
     * @return �l�J������
     */
    public String getValueColumnName();
    
    /**
     * ���t�J�����C���f�b�N�X��ݒ肷��B<p>
     * 
     * @param index �J�����C���f�b�N�X
     */
    public void setDateColumnIndex(int index);
    
    /**
     * ���t�J�����C���f�b�N�X���擾����B<p>
     * 
     * @return �J�����C���f�b�N�X
     */
    public int getDateColumnIndex();
    
    /**
     * �����J�����C���f�b�N�X��ݒ肷��B<p>
     * 
     * @param index �J�����C���f�b�N�X
     */
    public void setTimeColumnIndex(int index);
    
    /**
     * �����J�����C���f�b�N�X���擾����B<p>
     * 
     * @return �J�����C���f�b�N�X
     */
    public int getTimeColumnIndex();
    
    /**
     * �l�J�����C���f�b�N�X��ݒ肷��B<p>
     * 
     * @param index �J�����C���f�b�N�X
     */
    public void setValueColumnIndex(int index);
    
    /**
     * �l�J�����C���f�b�N�X���擾����B<p>
     * 
     * @return �J�����C���f�b�N�X
     */
    public int getValueColumnIndex();
}
