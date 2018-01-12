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

import java.util.Properties;
import jp.ossc.nimbus.core.ServiceBaseMBean;
import jp.ossc.nimbus.core.ServiceName;

/**
 * {@link XYPlotFactoryService}��MBean�C���^�t�F�[�X�B<p>
 *
 * @author k2-taniguchi
 */
public interface XYPlotFactoryServiceMBean
    extends ServiceBaseMBean {

    /**
     * [�f�[�^�Z�b�g��=�����_���[��]�̃v���p�e�B��ݒ肷��B<p>
     *
     * @param names [�f�[�^�Z�b�g��=�����_���[��]�̃v���p�e�B
     */
    public void setDatasetRendererServiceNames(Properties names);

    /**
     * [�f�[�^�Z�b�g��=�����_���[��]�̃v���p�e�B���擾����B<p>
     *
     * @return [�f�[�^�Z�b�g��=�����_���[�T�[�r�X��]�̃v���p�e�B
     */
    public Properties getDatasetRendererServiceNames();

    /**
     * [�f�[�^�Z�b�g��=������]�̃v���p�e�B��ݒ肷��B<p>
     *
     * @param names [�f�[�^�Z�b�g��=������]�̃v���p�e�B
     */
    public void setDatasetDomainAxisNames(Properties names);

    /**
     * [�f�[�^�Z�b�g��=������]�̃v���p�e�B���擾����B<p>
     *
     * @return [�f�[�^�Z�b�g��=������]�̃v���p�e�B
     */
    public Properties getDatasetDomainAxisNames();

    /**
     * [�f�[�^�Z�b�g��=�c����]�̃v���p�e�B��ݒ肷��B<p>
     *
     * @param names [�f�[�^�Z�b�g��=�c����]�̃v���p�e�B
     */
    public void setDatasetRangeAxisNames(Properties names);

    /**
     * [�f�[�^�Z�b�g��=�c����]�̃v���p�e�B���擾����B<p>
     *
     * @return [�f�[�^�Z�b�g��=�c����]�̃v���p�e�B
     */
    public Properties getDatasetRangeAxisNames();

    /**
     * �f�[�^�Z�b�g�t�@�N�g���T�[�r�X���̔z���ݒ肷��B<p>
     *
     * @param names �f�[�^�Z�b�g�t�@�N�g���T�[�r�X���̔z��
     */
    public void setDatasetFactoryServiceNames(ServiceName[] names);

    /**
     * �f�[�^�Z�b�g�t�@�N�g���T�[�r�X���̔z����擾����B<p>
     *
     * @return �f�[�^�Z�b�g�t�@�N�g���T�[�r�X���̔z��
     */
    public ServiceName[] getDatasetFactoryServiceNames();

    /**
     * �����T�[�r�X���̔z���ݒ肷��B<p>
     *
     * @param serviceNames �����T�[�r�X���̔z��
     */
    public void setDomainAxisServiceNames(ServiceName[] serviceNames);

    /**
     * �����T�[�r�X���̔z����擾����B<p>
     *
     * @return �����T�[�r�X���̔z��
     */
    public ServiceName[] getDomainAxisServiceNames();

    /**
     * �c���T�[�r�X���̔z���ݒ肷��B<p>
     *
     * @param serviceNames �c���T�[�r�X���̔z��
     */
    public void setRangeAxisServiceNames(ServiceName[] serviceNames);

    /**
     * �c���T�[�r�X���̔z����擾����B<p>
     *
     * @return �c���T�[�r�X���̔z��
     */
    public ServiceName[] getRangeAxisServiceNames();

    /**
     * �ڐ��蒲�߃T�[�r�X��ݒ肷��B<p>
     *
     * @param adjusters �ڐ��蒲�߃T�[�r�X
     */
    public void setTickUnitAdjusters(TickUnitAdjuster[] adjusters);

    /**
     * �ڐ��蒲�߃T�[�r�X���擾����B<p>
     *
     * @return �ڐ��蒲�߃T�[�r�X
     */
    public TickUnitAdjuster[] getTickUnitAdjusters();

    /**
     * �ڐ��蒲�߃T�[�r�X�̃T�[�r�X����ݒ肷��B<p>
     *
     * @param names �ڐ��蒲�߃T�[�r�X�̃T�[�r�X��
     */
    public void setTickUnitAdjusterServiceNames(ServiceName[] names);

    /**
     * �ڐ��蒲�߃T�[�r�X�̃T�[�r�X�����擾����B<p>
     *
     * @return �ڐ��蒲�߃T�[�r�X�̃T�[�r�X��
     */
    public ServiceName[] getTickUnitAdjusterNames();

}
