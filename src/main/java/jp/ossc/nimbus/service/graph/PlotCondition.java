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

import java.util.Iterator;

/**
 * �v���b�g�����B<p>
 *
 * @author k2-taniguchi
 */
public interface PlotCondition {

    /**
     * �v���b�g�����擾����B<p>
     *
     * @return �v���b�g��
     */
    public String getName();

    /**
     * �v���b�g����ݒ肷��B<p>
     *
     * @param name �v���b�g��
     */
    public void setName(String name);

    /**
     * �f�[�^�Z�b�g������ǉ�����B<p>
     *
     * @param dsCondition �f�[�^�Z�b�g����
     */
    public void addDatasetCondition(DatasetCondition dsCondition);

    /**
     * �w�肳�ꂽ�f�[�^�Z�b�g���̃f�[�^�Z�b�g�����z����擾����B<p>
     *
     * @param dsName �f�[�^�Z�b�g��
     * @return �f�[�^�Z�b�g�����z��
     */
    public DatasetCondition[] getDatasetConditions(String dsName);

    /**
     * �f�[�^�Z�b�g�����z����擾����B<p>
     *
     * @return �f�[�^�Z�b�g�����z��
     */
    public DatasetCondition[] getDatasetConditions();

    /**
     * �f�[�^�Z�b�g���̃C�e���[�^���擾����B<p>
     *
     * @return �f�[�^�Z�b�g���̃C�e���[�^
     */
    public Iterator getDatasetNames();

    /**
     * �L���ȃf�[�^�Z�b�g���z����擾����B<p>
     *
     * @return �L���ȃf�[�^�Z�b�g���z��
     */
    public String[] getEnableDatasetNames();

    /**
     * �L���ȃf�[�^�Z�b�g����ǉ�����B<p>
     *
     * @param dsName �L���ȃf�[�^�Z�b�g��
     */
    public void addEnableDatasetName(String dsName);

    /**
     * �ݒ菇�̃f�[�^�Z�b�g���z����擾����B<p>
     *
     * @return �f�[�^�Z�b�g���z��
     */
    public String[] getDatasetNameOrder();

    /**
     * �C�ӂ̐ݒ菇�Ńf�[�^�Z�b�g����ǉ�����B<p>
     *
     * @param dsName �f�[�^�Z�b�g��
     */
    public void addDatasetNameOrder(String dsName);

}
