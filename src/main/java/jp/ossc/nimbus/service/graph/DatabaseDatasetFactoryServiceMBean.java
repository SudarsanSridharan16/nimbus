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
import jp.ossc.nimbus.service.connection.ConnectionFactory;

/**
 * {@link DatabaseDatasetFactoryService}��MBean�C���^�t�F�[�X�B<p>
 *
 * @author M.Takata
 */
public interface DatabaseDatasetFactoryServiceMBean
    extends ServiceBaseMBean {

    /**
     * �R�l�N�V�����t�@�N�g����ݒ肷��B<p>
     *
     * @param connFactory �R�l�N�V�����t�@�N�g��
     */
    public void setConnectionFactory(ConnectionFactory connFactory);

    /**
     * �R�l�N�V�����t�@�N�g�����擾����B<p>
     *
     * @return �R�l�N�V�����t�@�N�g��
     */
    public ConnectionFactory getConnectionFactory();

    /**
     * �f�[�^�Z�b�g�����ۂɕK�v�ȃf�[�^���擾����SQL������z���ݒ肷��B<p>
     * [�V���[�Y��=SQL]�Ƃ���������Őݒ肳��Ă��܂��B
     *
     * @param sqls [�V���[�Y��=SQL]�̔z��
     */
    public void setSqls(String[] sqls);

    /**
     * �f�[�^�Z�b�g�����ۂɕK�v�ȃf�[�^���擾����SQL������z���ݒ肷��B<p>
     * [�V���[�Y��=SQL]�Ƃ���������Őݒ肳��Ă��܂��B
     *
     * @return [�V���[�Y��=SQL]�̔z��
     */
    public String[] getSqls();

    /**
     * �f�[�^�Z�b�g������ǉ�����B<p>
     * 
     * @param dsCondition �f�[�^�Z�b�g����
     */
    public void addDatasetCondition(DatasetCondition dsCondition);

    /**
     * �f�[�^�Z�b�g�����̔z����擾����B<p>
     * 
     * @return �f�[�^�Z�b�g�����̔z��
     */
    public DatasetCondition[] getDatasetConditions();

    /**
     * �t�F�b�`�T�C�Y��ݒ肷��B<p>
     * 
     * @param size �t�F�b�`�T�C�Y
     */
    public void setFetchSize(int size);
    
    /**
     * �t�F�b�`�T�C�Y���擾����B<p>
     * 
     * @return �t�F�b�`�T�C�Y
     */
    public int getFetchSize();

}
