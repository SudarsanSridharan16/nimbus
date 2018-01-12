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

import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.XYPlot;

import jp.ossc.nimbus.core.ServiceBase;
import jp.ossc.nimbus.core.ServiceManagerFactory;
import jp.ossc.nimbus.core.ServiceName;

/**
 * �ڐ��蒲�߃T�[�r�X�B<p>
 */
public abstract class AbstractTickUnitAdjusterService
    extends ServiceBase
    implements TickUnitAdjuster, AbstractTickUnitAdjusterServiceMBean {
    
    private static final long serialVersionUID = 4013207082825995188L;
    /** �\������ڐ���̐� */
    protected int displayGraduationCount;
    /** ���j�b�g�J�E���g���� */
    protected double unitCountCommonDivisor = Double.NaN;
    /** �c�����ǂ��� */
    protected boolean isDomain;
    /** ���̃C���f�b�N�X */
    protected int axisIndex = -1;
    /** TickUnit���񐔃}�b�v�T�[�r�X�� */
    protected ServiceName tickUnitAdjustCommonDivisorMapServiceName;
    /** TickUnit���񐔃}�b�v�T�[�r�X */
    protected TickUnitAdjustCommonDivisorMap tickUnitAdjustCommonDivisorMap;
    /** �����ŏ��͈̓T�C�Y�ݒ� */
    protected boolean autoRangeMinimumSizeEnabled;

    // AbstractTickUnitAdjusterMBean��JavaDoc
    public void setDisplayGraduationCount(int count) {
        displayGraduationCount = count;
    }

    // AbstractTickUnitAdjusterMBean��JavaDoc
    public int getDisplayGraduationCount() {
        return displayGraduationCount;
    }

    // AbstractTickUnitAdjusterMBean��JavaDoc
    public void setUnitCountCommonDivisor(double divisor) {
        unitCountCommonDivisor = divisor;
    }

    // AbstractTickUnitAdjusterMBean��JavaDoc
    public double getUnitCountCommonDivisor() {
        return unitCountCommonDivisor;
    }

    // AbstractTickUnitAdjusterMBean��JavaDoc
    public void setDomain(boolean isDomain) {
        this.isDomain = isDomain;
    }

    // AbstractTickUnitAdjusterMBean��JavaDoc
    public boolean isDomain() {
        return isDomain;
    }

    // AbstractTickUnitAdjusterMBean��JavaDoc
    public void setAxisIndex(int index) {
        axisIndex = index;
    }

    // AbstractTickUnitAdjusterMBean��JavaDoc
    public int getAxisIndex() {
        return axisIndex;
    }
    
    public void setAutoRangeMinimumSizeEnabled(boolean enabled) {
        autoRangeMinimumSizeEnabled = enabled;
    }
    public boolean getAutoRangeMinimumSizeEnabled() {
        return autoRangeMinimumSizeEnabled;
    }

    public void setTickUnitAdjustCommonDivisorMapServiceName(ServiceName serviceName) {
        tickUnitAdjustCommonDivisorMapServiceName = serviceName;
    }
    public ServiceName getTickUnitAdjustCommonDivisorMapServiceName() {
        return tickUnitAdjustCommonDivisorMapServiceName;
    }
    
    // ServiceBase��JavaDoc
    public void createService() throws Exception {
    }

    // ServiceBase��JavaDoc
    public void startService() throws Exception {
        if (displayGraduationCount <= 0) {
            throw new IllegalArgumentException(
                "displayGraduationCount must be specified."
            );
        }

        if (axisIndex < 0) {
            throw new IllegalArgumentException(
                "axisIndex must be specified."
            );
        }

        if (tickUnitAdjustCommonDivisorMapServiceName != null) {
            tickUnitAdjustCommonDivisorMap =
                (TickUnitAdjustCommonDivisorMap) ServiceManagerFactory
                .getServiceObject(tickUnitAdjustCommonDivisorMapServiceName);
        }
        
    }

    // ServiceBase��JavaDoc
    public void stopService() throws Exception {
    }

    // ServiceBase��JavaDoc
    public void destroyService() throws Exception {
    }

    // TickUnitAdjuster��JavaDoc
    public void adjust(XYPlot xyPlot) {
        ValueAxis axis = null;
        if (isDomain()) {
            // ����
            axis = xyPlot.getDomainAxis(getAxisIndex());
        } else {
            // �c��
            axis = xyPlot.getRangeAxis(getAxisIndex());
        }

        // �ڐ��蒲��
        adjust(axis);
    }
    
    /**
     * ���j�b�g�J�E���g�����񐔂ɂ���Ē��߂���B<p>
     * 
     * @param axis ��
     * @param unitCount ���j�b�g�J�E���g
     * @return ���j�b�g�J�E���g
     */
    protected double adjustUnitCountByCommonDivisor(ValueAxis axis, double unitCount) {

        double commonDivisor = unitCountCommonDivisor;
        if (tickUnitAdjustCommonDivisorMap != null) {
            commonDivisor =
                tickUnitAdjustCommonDivisorMap.getCommonDivisor(
                    axis.getRange().getLowerBound() + unitCount
                );
        }
        
        if (!Double.isNaN(commonDivisor)) {
            // ���j�b�g�J�E���g���ڐ�����񐔂ł͂Ȃ��ꍇ
            if ((unitCount % commonDivisor) != 0d) {
                // �ڐ�����񐔂Œ���
                unitCount += commonDivisor - (unitCount % commonDivisor);
            }
        }
        
        return unitCount;
    }

    /**
     * �w�肳�ꂽ���̖ڐ���𒲐߂���B<p>
     *
     * @param axis ��
     */
    abstract protected void adjust(ValueAxis axis);

}
