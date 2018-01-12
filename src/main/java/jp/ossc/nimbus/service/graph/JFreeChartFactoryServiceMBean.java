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

import org.jfree.chart.title.Title;

import jp.ossc.nimbus.core.ServiceBaseMBean;
import jp.ossc.nimbus.core.ServiceName;

/**
 * {@link JFreeChartFactoryService}��MBean�C���^�t�F�[�X�B<p>
 *
 * @author k2-taniguchi
 */
public interface JFreeChartFactoryServiceMBean
    extends ServiceBaseMBean {

    /**
     * �v���b�g�t�@�N�g���T�[�r�X����ݒ肷��B<p>
     *
     * @param serviceName �v���b�g�t�@�N�g���T�[�r�X��
     */
    public void setPlotFactoryServiceName(ServiceName serviceName);

    /**
     * �v���b�g�t�@�N�g���T�[�r�X�����擾����B<p>
     *
     * @return �v���b�g�t�@�N�g���T�[�r�X��
     */
    public ServiceName getPlotFactoryServiceName();
    
    /**
     * �T�u�^�C�g����ǉ�����B<p>
     * 
     * @param title �T�u�^�C�g��
     */
    public void addSubtitle(Title title);
    
    /**
     * ���W�F���h�𐶐����邩�ǂ����ݒ肷��B<p>
     * 
     * @param createLegend true:���W�F���h����/false:���W�F���h�������Ȃ�
     */
    public void setCreateLegend(boolean createLegend);
}
