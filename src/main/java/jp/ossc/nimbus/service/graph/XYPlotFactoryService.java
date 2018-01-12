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

import java.awt.Font;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.Plot;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.general.Dataset;
import org.jfree.data.xy.XYDataset;

import jp.ossc.nimbus.beans.ServiceNameEditor;
import jp.ossc.nimbus.core.ServiceBase;
import jp.ossc.nimbus.core.ServiceManagerFactory;
import jp.ossc.nimbus.core.ServiceName;

/**
 * XY�v���b�g�t�@�N�g���T�[�r�X�B<p>
 *
 * @author k2-taniguchi
 */
public class XYPlotFactoryService extends ServiceBase
    implements XYPlotFactoryServiceMBean, PlotFactory {
    
    private static final long serialVersionUID = 7687375902291200266L;
    
    /** �v���b�g�� */
    private String name;
    /** �f�[�^�Z�b�g�T�[�r�X���̔z�� */
    private ServiceName[] dsFactoryServiceNames;
    /** �L�[�Ƀf�[�^�Z�b�g���A�l�Ƀf�[�^�Z�b�g�t�@�N�g���̃}�b�v */
    private Map dsFactoryMap;

    /** �����T�[�r�X���̔z�� */
    private ServiceName[] domainAxisServiceNames;
    /** �c���T�[�r�X���̔z�� */
    private ServiceName[] rangeAxisServiceNames;
    /** �L�[�ɉ����A�l�ɉ����C���f�b�N�X */
    private Map domainAxisIndexMap;
    /** �L�[�ɏc���A�l�ɏc���C���f�b�N�X */
    private Map rangeAxisIndexMap;
    /** �ڐ��蒲�� */
    protected TickUnitAdjuster[] adjusters;
    /** �ڐ��蒲�߃T�[�r�X�� */
    protected ServiceName[] tickUnitAdjusterServiceNames;

    /** �e���v���[�g�p�v���b�g */
    protected XYPlot tmpPlot;
    /** �v���p�e�B : �f�[�^�Z�b�g��=�����_���[�� */
    private Properties dsRendererNames;
    /** �v���p�e�B : �f�[�^�Z�b�g��=������ */
    private Properties dsDomainAxisNames;
    /** �v���p�e�B : �f�[�^�Z�b�g��=�c���� */
    private Properties dsRangeAxisNames;

    // XYPlotFactoryServiceMBean��JavaDoc
    public void setDatasetFactoryServiceNames(ServiceName[] names) {
        dsFactoryServiceNames = names;
    }

    // XYPlotFactoryServiceMBean��JavaDoc
    public ServiceName[] getDatasetFactoryServiceNames() {
        return dsFactoryServiceNames;
    }

    // XYPlotFactoryServiceMBean��JavaDoc
    public void setDatasetRendererServiceNames(Properties names) {
        dsRendererNames = names;
    }

    // XYPlotFactoryServiceMBean��JavaDoc
    public Properties getDatasetRendererServiceNames() {
        return dsRendererNames;
    }

    // XYPlotFactoryServiceMBean��JavaDoc
    public void setDatasetDomainAxisNames(Properties names) {
        dsDomainAxisNames = names;
    }

    // XYPlotFactoryServiceMBean��JavaDoc
    public Properties getDatasetDomainAxisNames() {
        return dsDomainAxisNames;
    }

    // XYPlotFactoryServiceMBean��JavaDoc
    public void setDatasetRangeAxisNames(Properties names) {
        dsRangeAxisNames = names;
    }

    // XYPlotFactoryServiceMBean��JavaDoc
    public Properties getDatasetRangeAxisNames() {
        return dsRangeAxisNames;
    }

    // XYPlotFactoryServiceMBean��JavaDoc
    public void setDomainAxisServiceNames(ServiceName[] serviceNames) {
        domainAxisServiceNames = serviceNames;
    }

    // XYPlotFactoryServiceMBean��JavaDoc
    public ServiceName[] getDomainAxisServiceNames() {
        return domainAxisServiceNames;
    }

    // XYPlotFactoryServiceMBean��JavaDoc
    public void setRangeAxisServiceNames(ServiceName[] serviceNames) {
        rangeAxisServiceNames = serviceNames;
    }

    // XYPlotFactoryServiceMBean��JavaDoc
    public ServiceName[] getRangeAxisServiceNames() {
        return rangeAxisServiceNames;
    }

    // XYPlotFactoryServiceMBean��JavaDoc
    public void setTickUnitAdjusters(TickUnitAdjuster[] adjusters) {
        this.adjusters = adjusters;
    }

    // XYPlotFactoryServiceMBean��JavaDoc
    public TickUnitAdjuster[] getTickUnitAdjusters() {
        return adjusters;
    }

    // XYPlotFactoryServiceMBean��JavaDoc
    public void setTickUnitAdjusterServiceNames(ServiceName[] names){
        tickUnitAdjusterServiceNames = names;
    }

    // XYPlotFactoryServiceMBean��JavaDoc
    public ServiceName[] getTickUnitAdjusterNames(){
        return tickUnitAdjusterServiceNames;
    }
    
    public void addDatasetFactory(DatasetFactory factory){
        dsFactoryMap.put(factory.getName(), factory);
    }

    // ServiceBase��JavaDoc
    public void createService() throws Exception {
        dsFactoryMap = new LinkedHashMap();
        domainAxisIndexMap = new HashMap();
        rangeAxisIndexMap = new HashMap();

        tmpPlot = new XYPlot(
                      null,
                      new NumberAxis(),
                      new NumberAxis(),
                      new XYLineAndShapeRenderer(true, false)
                  );
    }

    // ServiceBase��JavaDoc
    public void startService() throws Exception {
        if (name == null || name.length() == 0) {
            // �T�[�r�X��`�Őݒ肳��Ȃ������ꍇ
            name = getServiceName();
        }

        if (dsFactoryServiceNames != null && dsFactoryServiceNames.length != 0) {
            for (int i = 0; i < dsFactoryServiceNames.length; i++) {
                // ���̃v���b�g�Ɋ֘A����f�[�^�Z�b�g�t�@�N�g���T�[�r�X���擾
                DatasetFactory dsFactory =
                    (DatasetFactory) ServiceManagerFactory.getServiceObject(dsFactoryServiceNames[i]);

                if (dsFactory == null) {
                    throw new IllegalArgumentException(
                        "DatasetFactory[" + dsFactoryServiceNames[i].getServiceName() + "] is null."
                    );
                } else {
                    // �L�[�Ƀf�[�^�Z�b�g���A�l�Ƀf�[�^�Z�b�g�t�@�N�g��
                    dsFactoryMap.put(dsFactory.getName(), dsFactory);
                }
            }
        }
        if(dsFactoryMap.size() == 0){
            throw new IllegalArgumentException(
                "DatasetFactory must be specified."
            );
        }


        // ����
        if (domainAxisServiceNames != null && domainAxisServiceNames.length > 0) {
            for (int i = 0; i < domainAxisServiceNames.length; i++) {
                domainAxisIndexMap.put(
                    domainAxisServiceNames[i].getServiceName(), new Integer(i)
                );
            }
        }

        // �c��
        if (rangeAxisServiceNames != null && rangeAxisServiceNames.length > 0) {
            for (int i = 0; i < rangeAxisServiceNames.length; i++) {
                rangeAxisIndexMap.put(
                    rangeAxisServiceNames[i].getServiceName(), new Integer(i)
                );
            }
        }
        
        if(tickUnitAdjusterServiceNames != null
             && tickUnitAdjusterServiceNames.length != 0){
            adjusters = new TickUnitAdjuster[tickUnitAdjusterServiceNames.length];
            for(int i = 0; i < adjusters.length; i++){
                adjusters[i] = (TickUnitAdjuster)ServiceManagerFactory
                    .getServiceObject(tickUnitAdjusterServiceNames[i]);
            }
        }
    }

    // ServiceBase��JavaDoc
    public void stopService() throws Exception {
        domainAxisIndexMap.clear();
        rangeAxisIndexMap.clear();
    }

    // ServiceBase��JavaDoc
    public void destroyService() throws Exception {
        dsFactoryMap = null;
        domainAxisIndexMap = null;
        rangeAxisIndexMap = null;

        tmpPlot = null;
    }
    
    protected XYPlot newXYPlot(){
        return new XYPlot(
                    null,
                    (domainAxisServiceNames == null || domainAxisServiceNames.length == 0) ? new NumberAxis() : null,
                    (rangeAxisServiceNames == null || rangeAxisServiceNames.length == 0) ? new NumberAxis() : null,
                    (dsRendererNames == null || dsRendererNames.size() == 0) ? new XYLineAndShapeRenderer(true, false) : null
                );
    }

    /**
     * �e���v���[�g�p�v���b�g����l���R�s�[�����v���b�g���쐬����B<p>
     *
     * @return XY�v���b�g
     */
    protected XYPlot copyXYPlot() {
        XYPlot xyPlot = newXYPlot();

        xyPlot.setAxisOffset(tmpPlot.getAxisOffset());
        xyPlot.setBackgroundAlpha(tmpPlot.getBackgroundAlpha());
        xyPlot.setBackgroundImage(tmpPlot.getBackgroundImage());
        xyPlot.setBackgroundImageAlignment(tmpPlot.getBackgroundImageAlignment());
        xyPlot.setBackgroundPaint(tmpPlot.getBackgroundPaint());
        xyPlot.setDatasetRenderingOrder(tmpPlot.getDatasetRenderingOrder());

        if (tmpPlot.getDomainAxisCount() > 0) {
            for (int i = 0; i < tmpPlot.getDomainAxisCount(); i++) {
                try {
                    if(tmpPlot.getDomainAxis(i) != null){
                        xyPlot.setDomainAxis(i, (ValueAxis) tmpPlot.getDomainAxis(i).clone());
                    }
                } catch (CloneNotSupportedException e) {
                }
            }
        }

        xyPlot.setDomainAxisLocation(tmpPlot.getDomainAxisLocation());
        xyPlot.setDomainCrosshairLockedOnData(tmpPlot.isDomainCrosshairLockedOnData());
        xyPlot.setDomainCrosshairPaint(tmpPlot.getDomainCrosshairPaint());
        xyPlot.setDomainCrosshairStroke(tmpPlot.getDomainCrosshairStroke());
        xyPlot.setDomainCrosshairValue(tmpPlot.getDomainCrosshairValue());
        xyPlot.setDomainCrosshairVisible(tmpPlot.isDomainCrosshairVisible());
        xyPlot.setDomainGridlinePaint(tmpPlot.getDomainGridlinePaint());
        xyPlot.setDomainGridlineStroke(tmpPlot.getDomainCrosshairStroke());
        xyPlot.setDomainGridlinesVisible(tmpPlot.isDomainGridlinesVisible());
        xyPlot.setDomainTickBandPaint(tmpPlot.getDomainTickBandPaint());
        xyPlot.setDrawingSupplier(tmpPlot.getDrawingSupplier());
        xyPlot.setFixedDomainAxisSpace(tmpPlot.getFixedDomainAxisSpace());
        xyPlot.setFixedLegendItems(tmpPlot.getFixedLegendItems());
        xyPlot.setFixedRangeAxisSpace(tmpPlot.getFixedRangeAxisSpace());
        xyPlot.setForegroundAlpha(tmpPlot.getForegroundAlpha());
        xyPlot.setInsets(tmpPlot.getInsets());
        xyPlot.setNoDataMessage(tmpPlot.getNoDataMessage());
        xyPlot.setNoDataMessageFont(tmpPlot.getNoDataMessageFont());
        xyPlot.setNoDataMessagePaint(tmpPlot.getNoDataMessagePaint());
        xyPlot.setOrientation(tmpPlot.getOrientation());
        xyPlot.setOutlinePaint(tmpPlot.getOutlinePaint());
        xyPlot.setOutlineStroke(tmpPlot.getOutlineStroke());
        xyPlot.setQuadrantOrigin(tmpPlot.getQuadrantOrigin());

        for (int i = 0; i < 4; i++) {
            // QuadrantPaint�̓T�C�Y4�̔z��ŕێ�����Ă��܂��B
            xyPlot.setQuadrantPaint(i, tmpPlot.getQuadrantPaint(i));
        }

        if (tmpPlot.getRangeAxisCount() > 0) {
            for (int i = 0; i < tmpPlot.getRangeAxisCount(); i++) {
                try {
                    if(tmpPlot.getRangeAxis(i) != null){
                        xyPlot.setRangeAxis(i, (ValueAxis) tmpPlot.getRangeAxis(i).clone());
                    }
                } catch (CloneNotSupportedException e) {
                }
            }
        }

        xyPlot.setRangeAxisLocation(tmpPlot.getRangeAxisLocation());
        xyPlot.setRangeCrosshairLockedOnData(tmpPlot.isRangeCrosshairLockedOnData());
        xyPlot.setRangeCrosshairPaint(tmpPlot.getRangeCrosshairPaint());
        xyPlot.setRangeCrosshairStroke(tmpPlot.getRangeCrosshairStroke());
        xyPlot.setRangeCrosshairValue(tmpPlot.getRangeCrosshairValue());
        xyPlot.setRangeCrosshairVisible(tmpPlot.isRangeCrosshairVisible());
        xyPlot.setRangeGridlinePaint(tmpPlot.getRangeGridlinePaint());
        xyPlot.setRangeGridlineStroke(tmpPlot.getRangeGridlineStroke());
        xyPlot.setRangeGridlinesVisible(tmpPlot.isRangeGridlinesVisible());
        xyPlot.setRangeTickBandPaint(tmpPlot.getRangeTickBandPaint());
        xyPlot.setRangeZeroBaselinePaint(tmpPlot.getRangeZeroBaselinePaint());
        xyPlot.setRangeZeroBaselineStroke(tmpPlot.getRangeZeroBaselineStroke());
        xyPlot.setRangeZeroBaselineVisible(tmpPlot.isRangeZeroBaselineVisible());
        xyPlot.setSeriesRenderingOrder(tmpPlot.getSeriesRenderingOrder());
        xyPlot.setWeight(tmpPlot.getWeight());

        return xyPlot;
    }

    /**
     * �����̃v���b�g��������A�v���b�g������v������̂�1�̃v���b�g�����Ƀ}�[�W����B<p>
     *
     * @param plotConditions �v���b�g�����̔z��
     * @return 1�Ƀ}�[�W�����v���b�g����
     */
    protected XYPlotConditionImpl mergeXYPlotCondition(PlotCondition[] plotConditions) {
        if (plotConditions == null || plotConditions.length == 0) {
            return null;
        }

        XYPlotConditionImpl xyPlotCondition = null;
        if (plotConditions.length == 1) {
            xyPlotCondition = (XYPlotConditionImpl) plotConditions[0];
        } else {
            for (int i = 0; i < plotConditions.length; i++) {
                if (!name.equals(plotConditions[i].getName())
                    || !(plotConditions[i] instanceof XYPlotConditionImpl)) {
                    continue;
                }
                if(xyPlotCondition == null){
                    xyPlotCondition = new XYPlotConditionImpl();
                    xyPlotCondition.setName(name);
                }
                XYPlotConditionImpl plotCondition =  (XYPlotConditionImpl) plotConditions[i];
                Set enableDatasetNameSet = plotCondition.getEnableDatasetNameSet();

                if (enableDatasetNameSet != null) {
                    xyPlotCondition.setEnableDatasetNameSet(enableDatasetNameSet);
                }

                LinkedHashSet orders = plotCondition.getDatasetNameOrderSet();
                if (orders != null) {
                    xyPlotCondition.setDatasetNameOrderSet(orders);
                }

                Map map = plotCondition.getDatasetConditionMap();
                if (map != null) {
                    xyPlotCondition.addDatasetConditionMap(map);
                }

                map = plotCondition.getRangeAxisVisibleMap();
                if (map != null) {
                    xyPlotCondition.addRangeAxisVisibleMap(map);
                }

                map = plotCondition.getDomainAxisLabelFontNameMap();
                if (map != null) {
                    xyPlotCondition.addDomainAxisLabelFontNameMap(map);
                }
                map = plotCondition.getDomainAxisLabelFontStyleMap();
                if (map != null) {
                    xyPlotCondition.addDomainAxisLabelFontStyleMap(map);
                }
                map = plotCondition.getDomainAxisLabelFontSizeMap();
                if (map != null) {
                    xyPlotCondition.addDomainAxisLabelFontSizeMap(map);
                }

                map = plotCondition.getRangeAxisTickLabelFontNameMap();
                if (map != null) {
                    xyPlotCondition.addRangeAxisTickLabelFontNameMap(map);
                }
                map = plotCondition.getRangeAxisTickLabelFontStyleMap();
                if (map != null) {
                    xyPlotCondition.addRangeAxisTickLabelFontStyleMap(map);
                }
                map = plotCondition.getRangeAxisTickLabelFontSizeMap();
                if (map != null) {
                    xyPlotCondition.addRangeAxisTickLabelFontSizeMap(map);
                }

                map = plotCondition.getDomainAxisLabelFontNameMap();
                if (map != null) {
                    xyPlotCondition.addDomainAxisLabelFontNameMap(map);
                }
                map = plotCondition.getDomainAxisLabelFontStyleMap();
                if (map != null) {
                    xyPlotCondition.addDomainAxisLabelFontStyleMap(map);
                }
                map = plotCondition.getDomainAxisLabelFontSizeMap();
                if (map != null) {
                    xyPlotCondition.addDomainAxisLabelFontSizeMap(map);
                }

                map = plotCondition.getRangeAxisLabelFontNameMap();
                if (map != null) {
                    xyPlotCondition.addRangeAxisLabelFontNameMap(map);
                }
                map = plotCondition.getRangeAxisLabelFontStyleMap();
                if (map != null) {
                    xyPlotCondition.addRangeAxisLabelFontStyleMap(map);
                }
                map = plotCondition.getRangeAxisLabelFontSizeMap();
                if (map != null) {
                    xyPlotCondition.addRangeAxisLabelFontSizeMap(map);
                }

                String fontName = plotCondition.getDefaultDomainAxisTickLabelFontName();
                if(fontName != null){
                    xyPlotCondition.setDefaultDomainAxisTickLabelFontName(fontName);
                }
                int fontStyle = plotCondition.getDefaultDomainAxisTickLabelFontStyle();
                if(fontStyle != Integer.MIN_VALUE){
                    xyPlotCondition.setDefaultDomainAxisTickLabelFontStyle(fontStyle);
                }
                int fontSize = plotCondition.getDefaultDomainAxisTickLabelFontSize();
                if(fontSize != Integer.MIN_VALUE){
                    xyPlotCondition.setDefaultDomainAxisTickLabelFontSize(fontSize);
                }

                fontName = plotCondition.getDefaultRangeAxisTickLabelFontName();
                if(fontName != null){
                    xyPlotCondition.setDefaultRangeAxisTickLabelFontName(fontName);
                }
                fontStyle = plotCondition.getDefaultRangeAxisTickLabelFontStyle();
                if(fontStyle != Integer.MIN_VALUE){
                    xyPlotCondition.setDefaultRangeAxisTickLabelFontStyle(fontStyle);
                }
                fontSize = plotCondition.getDefaultRangeAxisTickLabelFontSize();
                if(fontSize != Integer.MIN_VALUE){
                    xyPlotCondition.setDefaultRangeAxisTickLabelFontSize(fontSize);
                }

                fontName = plotCondition.getDefaultDomainAxisLabelFontName();
                if(fontName != null){
                    xyPlotCondition.setDefaultDomainAxisLabelFontName(fontName);
                }
                fontStyle = plotCondition.getDefaultDomainAxisLabelFontStyle();
                if(fontStyle != Integer.MIN_VALUE){
                    xyPlotCondition.setDefaultDomainAxisLabelFontStyle(fontStyle);
                }
                fontSize = plotCondition.getDefaultDomainAxisLabelFontSize();
                if(fontSize != Integer.MIN_VALUE){
                    xyPlotCondition.setDefaultDomainAxisLabelFontSize(fontSize);
                }

                fontName = plotCondition.getDefaultRangeAxisLabelFontName();
                if(fontName != null){
                    xyPlotCondition.setDefaultRangeAxisLabelFontName(fontName);
                }
                fontStyle = plotCondition.getDefaultRangeAxisLabelFontStyle();
                if(fontStyle != Integer.MIN_VALUE){
                    xyPlotCondition.setDefaultRangeAxisLabelFontStyle(fontStyle);
                }
                fontSize = plotCondition.getDefaultRangeAxisLabelFontSize();
                if(fontSize != Integer.MIN_VALUE){
                    xyPlotCondition.setDefaultRangeAxisLabelFontSize(fontSize);
                }
            }
        }

        return xyPlotCondition;
    }

    // PlotFactory��JavaDoc
    public Plot createPlot(PlotCondition[] plotConditions)
        throws PlotCreateException {

        // �����̃v���b�g������1�Ƀ}�[�W
        XYPlotConditionImpl xyPlotCondition = mergeXYPlotCondition(plotConditions);
        if (xyPlotCondition == null) {
            return new XYPlot(
                        null,
                        new NumberAxis(),
                        new NumberAxis(),
                        new XYLineAndShapeRenderer(true, false)
                    );
        }

        // �e���v���[�g�̃v���b�g����l���R�s�[�����v���b�g�쐬
        XYPlot xyPlot = copyXYPlot();
        // �f�[�^�Z�b�g���X�g
        List dsFactoryList = new ArrayList();
        // �L���ȃf�[�^�Z�b�g�����擾
        String[] enableDsNames = xyPlotCondition.getEnableDatasetNames();
        // �ݒ菇�̃f�[�^�Z�b�g�����擾
        String[] dsNamesOrder = xyPlotCondition.getDatasetNameOrder();
        // �f�[�^�Z�b�g�����ɐݒ肳�ꂽ�Ƃ��̂ݓK�p
        if (dsNamesOrder != null && dsNamesOrder.length > 0) {
            for (int j = 0; j < dsNamesOrder.length; j++) {
                // �f�[�^�Z�b�g��
                String dsName = dsNamesOrder[j];
                boolean isEnabled = false;
                if(enableDsNames != null && enableDsNames.length > 0) {
                    for (int k = 0; k < enableDsNames.length; k++) {
                        if (dsName.equals(enableDsNames[k])) {
                            isEnabled = true;
                            break;
                        }
                    }

                    if (isEnabled) {
                        if (dsFactoryMap.containsKey(dsName)) {
                            // �L���ȃf�[�^�Z�b�g
                            dsFactoryList.add(dsFactoryMap.get(dsName));
                        }
                    }

                }
            }
        } else {
            /*
             * �f�[�^�Z�b�g�����Ƀf�[�^�Z�b�g�����A�L���f�[�^�Z�b�g����
             * �ݒ肳��Ȃ������ꍇ�́A�T�[�r�X��`�̏����Ńf�[�^�Z�b�g��ݒ�
             */
            dsFactoryList.addAll(dsFactoryMap.values());
        }

        for (int j = 0; j < dsFactoryList.size(); j++) {
            DatasetFactory dsFactory =
                (DatasetFactory) dsFactoryList.get(j);

            String dsName = dsFactory.getName();
            DatasetCondition[] dsConditions =
                xyPlotCondition.getDatasetConditions();

            Dataset ds = null;
            try {
                ds = dsFactory.createDataset(dsConditions);
            } catch (DatasetCreateException e) {
                // �f�[�^�Z�b�g�������s
                throw new PlotCreateException(e);
            }

            // �T�[�r�X���G�f�B�^
            ServiceNameEditor editor = new ServiceNameEditor();
            editor.setServiceManagerName(getServiceManagerName());
            // �f�[�^�Z�b�g�̃��[�v
            if (ds != null && (ds instanceof XYDataset)) {
                // �f�[�^�Z�b�g
                xyPlot.setDataset(j, (XYDataset) ds);

                // ���̃f�[�^�Z�b�g���������鉡����
                if (dsDomainAxisNames != null && dsDomainAxisNames.size() > 0) {
                    String domainAxisName = dsDomainAxisNames.getProperty(dsName);
                    if (domainAxisName != null
                        && domainAxisIndexMap.containsKey(domainAxisName)
                    ) {
                        Integer domainAxisIndex = (Integer) domainAxisIndexMap.get(domainAxisName);
                        xyPlot.mapDatasetToDomainAxis(j, domainAxisIndex.intValue());
                    }
                }

                // ���̃f�[�^�Z�b�g����������c����
                if (dsRangeAxisNames != null && dsRangeAxisNames.size() > 0) {
                    String rangeAxisName = dsRangeAxisNames.getProperty(dsName);
                    if (rangeAxisName != null && rangeAxisIndexMap.containsKey(rangeAxisName)) {
                        Integer rangeAxisIndex = (Integer) rangeAxisIndexMap.get(rangeAxisName);
                        xyPlot.mapDatasetToRangeAxis(j, rangeAxisIndex.intValue());
                    }
                }

                // �����_���[
                XYItemRenderer renderer = null;
                if (dsRendererNames != null && dsRendererNames.size() > 0) {
                    String rendererNameStr = dsRendererNames.getProperty(dsName);
                    editor.setAsText(rendererNameStr);
                    ServiceName rendererName = (ServiceName) editor.getValue();

                    renderer =
                        (XYItemRenderer) ServiceManagerFactory.getServiceObject(rendererName);
                }
                if (renderer != null) {
                    xyPlot.setRenderer(j, renderer);
                } else {
                    xyPlot.setRenderer(j, new XYLineAndShapeRenderer(true, false));
                }
            }
        }

        // ����
        if (domainAxisServiceNames != null && domainAxisServiceNames.length > 0) {
            for (int j = 0; j < domainAxisServiceNames.length; j++) {
                ValueAxis domainAxis =
                    (ValueAxis) ServiceManagerFactory.getServiceObject(domainAxisServiceNames[j]);

                // �������x���t�H���g
                if (xyPlotCondition.getDefaultDomainAxisLabelFontName() != null
                    || xyPlotCondition.getDefaultDomainAxisLabelFontStyle() != Integer.MIN_VALUE
                    || xyPlotCondition.getDefaultDomainAxisLabelFontSize() != Integer.MIN_VALUE
                ) {
                    domainAxis.setLabelFont(
                        mergeFont(
                            domainAxis.getLabelFont(),
                            xyPlotCondition.getDefaultDomainAxisLabelFontName(),
                            xyPlotCondition.getDefaultDomainAxisLabelFontStyle(),
                            xyPlotCondition.getDefaultDomainAxisLabelFontSize()
                        )
                    );
                } else if (xyPlotCondition.getDomainAxisLabelFontName(j) != null
                            || xyPlotCondition.getDomainAxisLabelFontStyle(j) != Integer.MIN_VALUE
                            || xyPlotCondition.getDomainAxisLabelFontSize(j) != Integer.MIN_VALUE
                ) {
                    domainAxis.setLabelFont(
                        mergeFont(
                            domainAxis.getLabelFont(),
                            xyPlotCondition.getDomainAxisLabelFontName(j),
                            xyPlotCondition.getDomainAxisLabelFontStyle(j),
                            xyPlotCondition.getDomainAxisLabelFontSize(j)
                        )
                    );
                }

                // ����Tick���x���t�H���g
                if (xyPlotCondition.getDefaultDomainAxisTickLabelFontName() != null
                    || xyPlotCondition.getDefaultDomainAxisTickLabelFontStyle() != Integer.MIN_VALUE
                    || xyPlotCondition.getDefaultDomainAxisTickLabelFontSize() != Integer.MIN_VALUE
                ) {
                    domainAxis.setTickLabelFont(
                        mergeFont(
                            domainAxis.getTickLabelFont(),
                            xyPlotCondition.getDefaultDomainAxisTickLabelFontName(),
                            xyPlotCondition.getDefaultDomainAxisTickLabelFontStyle(),
                            xyPlotCondition.getDefaultDomainAxisTickLabelFontSize()
                        )
                    );
                } else if (xyPlotCondition.getDomainAxisTickLabelFontName(j) != null
                            || xyPlotCondition.getDomainAxisTickLabelFontStyle(j) != Integer.MIN_VALUE
                            || xyPlotCondition.getDomainAxisTickLabelFontSize(j) != Integer.MIN_VALUE
                ) {
                    domainAxis.setTickLabelFont(
                        mergeFont(
                            domainAxis.getTickLabelFont(),
                            xyPlotCondition.getDomainAxisTickLabelFontName(j),
                            xyPlotCondition.getDomainAxisTickLabelFontStyle(j),
                            xyPlotCondition.getDomainAxisTickLabelFontSize(j)
                        )
                    );
                }

                xyPlot.setDomainAxis(j, domainAxis);
            }
        }

        // �c��
        if (rangeAxisServiceNames != null && rangeAxisServiceNames.length > 0) {
            for (int j = 0; j < rangeAxisServiceNames.length; j++) {
                ValueAxis rangeAxis =
                    (ValueAxis) ServiceManagerFactory.getServiceObject(rangeAxisServiceNames[j]);

                // �c�����x���t�H���g
                if (xyPlotCondition.getDefaultRangeAxisLabelFontName() != null
                    || xyPlotCondition.getDefaultRangeAxisLabelFontStyle() != Integer.MIN_VALUE
                    || xyPlotCondition.getDefaultRangeAxisLabelFontSize() !=  Integer.MIN_VALUE
                ) {
                    rangeAxis.setLabelFont(
                        mergeFont(
                            rangeAxis.getLabelFont(),
                            xyPlotCondition.getDefaultRangeAxisLabelFontName(),
                            xyPlotCondition.getDefaultRangeAxisLabelFontStyle(),
                            xyPlotCondition.getDefaultRangeAxisLabelFontSize()
                        )
                    );
                } else if (xyPlotCondition.getRangeAxisLabelFontName(j) != null
                            || xyPlotCondition.getRangeAxisLabelFontStyle(j) != Integer.MIN_VALUE
                            || xyPlotCondition.getRangeAxisLabelFontSize(j) !=  Integer.MIN_VALUE
                ) {
                    rangeAxis.setLabelFont(
                        mergeFont(
                            rangeAxis.getLabelFont(),
                            xyPlotCondition.getRangeAxisLabelFontName(j),
                            xyPlotCondition.getRangeAxisLabelFontStyle(j),
                            xyPlotCondition.getRangeAxisLabelFontSize(j)
                        )
                    );
                }

                // �c��Tick���x���t�H���g
                if (xyPlotCondition.getDefaultRangeAxisTickLabelFontName() != null
                    || xyPlotCondition.getDefaultRangeAxisTickLabelFontStyle() != Integer.MIN_VALUE
                    || xyPlotCondition.getDefaultRangeAxisTickLabelFontSize() != Integer.MIN_VALUE
                ) {
                    rangeAxis.setTickLabelFont(
                        mergeFont(
                            rangeAxis.getTickLabelFont(),
                            xyPlotCondition.getDefaultRangeAxisTickLabelFontName(),
                            xyPlotCondition.getDefaultRangeAxisTickLabelFontStyle(),
                            xyPlotCondition.getDefaultRangeAxisTickLabelFontSize()
                        )
                    );
                } else if (xyPlotCondition.getRangeAxisTickLabelFontName(j) != null
                            || xyPlotCondition.getRangeAxisTickLabelFontStyle(j) != Integer.MIN_VALUE
                            || xyPlotCondition.getRangeAxisTickLabelFontSize(j) !=  Integer.MIN_VALUE
                ) {
                    rangeAxis.setTickLabelFont(
                        mergeFont(
                            rangeAxis.getTickLabelFont(),
                            xyPlotCondition.getRangeAxisTickLabelFontName(j),
                            xyPlotCondition.getRangeAxisTickLabelFontStyle(j),
                            xyPlotCondition.getRangeAxisTickLabelFontSize(j)
                        )
                    );
                }

                // �c���̉���Ԑݒ�
                if (xyPlotCondition.isRangeAxisVisible(j) != null) {
                    rangeAxis.setVisible(
                        xyPlotCondition.isRangeAxisVisible(j).booleanValue()
                    );
                }

                xyPlot.setRangeAxis(j, rangeAxis);
            }
        }

        if (adjusters != null) {
            // �ڐ��蒲��
            for(int i = 0; i < adjusters.length; i++){
                adjusters[i].adjust(xyPlot);
            }
        }
        return xyPlot;
    }

    /**
     * �w�肳�ꂽ�t�H���g��
     * �w�肳�ꂽ[�t�H���g���A�t�H���g�X�^�C���A�t�H���g�T�C�Y]���}�[�W����B<p>
     *
     * @param orgFont �t�H���g
     * @param fontName �t�H���g��
     * @param fontStyle �t�H���g�X�^�C��
     * @param fontSize �t�H���g�T�C�Y
     * @return �}�[�W�����t�H���g
     */
    protected Font mergeFont(
        Font orgFont,
        String fontName,
        int fontStyle,
        int fontSize
    ) {
        if (orgFont == null) {
            return new Font(fontName, fontStyle, fontSize);
        }

        String newName = orgFont.getName();
        int newStyle = orgFont.getStyle();
        int newSize = orgFont.getSize();
        if (fontName != null) {
            newName = fontName;
        }
        if (fontStyle != Integer.MIN_VALUE) {
            newStyle = fontStyle;
        }
        if (fontSize != Integer.MIN_VALUE) {
            newSize = fontSize;
        }
        return new Font(newName, newStyle, newSize);
    }

    // PlotFactory��JavaDoc
    public Plot getPlot() {
        return tmpPlot;
    }

    // PlotFactory��JavaDoc
    public void setName(String name) {
        this.name = name;
    }

    // PlotFactory��JavaDoc
    public String getName() {
        return name;
    }

}
