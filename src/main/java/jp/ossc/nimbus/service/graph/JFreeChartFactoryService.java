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
import java.util.List;

import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.Plot;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.title.LegendTitle;
import org.jfree.chart.title.TextTitle;
import org.jfree.chart.title.Title;

import jp.ossc.nimbus.core.ServiceBase;
import jp.ossc.nimbus.core.ServiceManagerFactory;
import jp.ossc.nimbus.core.ServiceName;

/**
 * JFreeChart�t�@�N�g���T�[�r�X�B<p>
 *
 * @author k2-taniguchi
 */
public class JFreeChartFactoryService extends ServiceBase
    implements JFreeChartFactory, JFreeChartFactoryServiceMBean {
    
    private static final long serialVersionUID = -7164526648533773901L;
    
    /** �e���v���[�g�pJFreeChart */
    private JFreeChart tmpJFreeChart;
    /** �v���b�g�t�@�N�g���T�[�r�X�� */
    private ServiceName plotFactoryServiceName;
    /** �v���b�g�t�@�N�g�� */
    private PlotFactory plotFactory;
    private List subtitles;
    /** ���W�F���h�𐶐����邩(�f�t�H���gtrue) */
    private boolean createLegend = true;

    // ServiceBase��JavaDoc
    public void createService() throws Exception {
        tmpJFreeChart = new JFreeChart(new XYPlot());
        subtitles = new ArrayList();
    }

    // ServiceBase��JavaDoc
    public void startService() throws Exception {
        if (plotFactoryServiceName != null) {
            plotFactory = (PlotFactory) ServiceManagerFactory.getServiceObject(plotFactoryServiceName);
        }
        if(plotFactory == null){
            throw new IllegalArgumentException(
                "PlotFactory must be specified."
            );
        }

    }

    // ServiceBase��JavaDoc
    public void stopService() throws Exception {
        subtitles.clear();
    }

    // ServiceBase��JavaDoc
    public void destroyService() throws Exception {
        tmpJFreeChart = null;
        subtitles = null;
    }

    // JFreeChartFactory��JavaDoc
    public JFreeChart getJFreeChart() {
        return tmpJFreeChart;
    }

    // JFreeChartFactory��JavaDoc
    public JFreeChart createChart(ChartCondition chartCondition)
        throws JFreeChartCreateException {
        Plot plot = null;
        try {
            plot = plotFactory.createPlot(chartCondition.getPlotConditions());
        } catch (PlotCreateException e) {
            // �v���b�g�������s
            throw new JFreeChartCreateException(e);
        }

        JFreeChart chart = copyJFreeChart(plot);
        // �^�C�g���̐ݒ�
        if (chartCondition.getTitle() != null
            && (chartCondition.getTitleFontName() != null
                || chartCondition.getTitleFontStyle() != Integer.MIN_VALUE
                || chartCondition.getTitleFontSize() != Integer.MIN_VALUE)
        ) {
            Font newFont = null;
            TextTitle orgTitle = chart.getTitle();
            if (orgTitle != null) {
                newFont = mergeFont(
                    orgTitle.getFont(),
                    chartCondition.getTitleFontName(),
                    chartCondition.getTitleFontStyle(),
                    chartCondition.getTitleFontSize()
                );
            }

            if (newFont != null) {
                chart.setTitle(
                    new TextTitle(
                        chartCondition.getTitle(),
                        newFont
                    )
                );
            }else{
                chart.setTitle(chartCondition.getTitle());
            }
        } else if (chartCondition.getTitle() != null) {
            // �^�C�g��������̂ݐݒ肳�ꂽ
            chart.setTitle(chartCondition.getTitle());
        }

        if (chart.getSubtitleCount() > 0) {
            List subList = chart.getSubtitles();
            String defaultFontName = chartCondition.getDefaultSubtitleFontName();
            int defaultFontStyle = chartCondition.getDefaultSubtitleFontStyle();
            int defaultFontSize = chartCondition.getDefaultSubtitleFontSize();
            if (defaultFontName != null
                || defaultFontStyle != Integer.MIN_VALUE
                || defaultFontSize != Integer.MIN_VALUE
            ) {
                // �T�u�^�C�g�����ׂĂɃf�t�H���g�̃t�H���g��ݒ肷��B
                for (int i = 0; i < subList.size(); i++) {
                    Object subtitle = subList.get(i);
                    if (subtitle instanceof LegendTitle) {
                        LegendTitle legendTitle = (LegendTitle) subtitle;
                        legendTitle.setItemFont(
                            mergeFont(
                                legendTitle.getItemFont(),
                                defaultFontName,
                                defaultFontStyle,
                                defaultFontSize
                            )
                        );
                    } else if (subtitle instanceof TextTitle) {
                        TextTitle textTitle = (TextTitle) subtitle;
                        textTitle.setFont(
                            mergeFont(
                                textTitle.getFont(),
                                defaultFontName,
                                defaultFontStyle,
                                defaultFontSize
                            )
                        );
                    }
                }
            } else {
                // �X�̃T�u�^�C�g���Ƀt�H���g��ݒ�
                for (int i = 0; i < subList.size(); i++) {
                    String subFontName = chartCondition.getSubtitleFontName(i);
                    int subFontStyle = chartCondition.getSubtitleFontStyle(i);
                    int subFontSize = chartCondition.getSubtitleFontSize(i);
                    if (subFontName == null
                        && subFontStyle == Integer.MIN_VALUE
                        && subFontSize == Integer.MIN_VALUE
                    ) {
                        continue;
                    }

                    Title subtitle = chart.getSubtitle(i);
                    if (subtitle instanceof LegendTitle) {
                        LegendTitle legendTitle = (LegendTitle) subtitle;
                        legendTitle.setItemFont(
                            mergeFont(
                                legendTitle.getItemFont(),
                                subFontName,
                                subFontStyle,
                                subFontSize
                            )
                        );
                    } else if (subtitle instanceof TextTitle) {
                        TextTitle textTitle = (TextTitle) subtitle;
                        textTitle.setFont(
                            mergeFont(
                                textTitle.getFont(),
                                subFontName,
                                subFontStyle,
                                subFontSize
                            )
                        );
                    }
                }
            }
        }

        return chart;
    }

    /**
     * ���̃t�H���g�Ǝw�肳�ꂽ�t�H���g���A�t�H���g�X�^�C���A�t�H���g�T�C�Y���}�[�W����B<p>
     * �t�H���g���A�t�H���g�X�^�C���A�t�H���g�T�C�Y���w�肳��Ă��Ȃ���΁A
     * ���̃t�H���g�̒l�������p���܂��B
     *
     * @param orgFont ���̃t�H���g
     * @param fontName �t�H���g��
     * @param fontStyle �t�H���g�X�^�C��
     * @param fontSize �t�H���g�T�C�Y
     * @return �}�[�W�����t�H���g
     */
    private Font mergeFont(
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

    /**
     * �e���v���[�g�pJFreeChart����l���R�s�[����JFreeChart�𐶐�����B<p>
     *
     * @param plot �v���b�g
     * @return JFreeChart
     */
    private JFreeChart copyJFreeChart(Plot plot) {
        JFreeChart chart = new JFreeChart(null, null, plot, createLegend);
        chart.setAntiAlias(tmpJFreeChart.getAntiAlias());
        chart.setBackgroundImage(tmpJFreeChart.getBackgroundImage());
        chart.setBackgroundImageAlignment(tmpJFreeChart.getBackgroundImageAlignment());
        chart.setBackgroundImageAlpha(tmpJFreeChart.getBackgroundImageAlpha());
        chart.setBackgroundPaint(tmpJFreeChart.getBackgroundPaint());
        chart.setBorderPaint(tmpJFreeChart.getBorderPaint());
        chart.setBorderStroke(tmpJFreeChart.getBorderStroke());
        chart.setBorderVisible(tmpJFreeChart.isBorderVisible());
        chart.setNotify(tmpJFreeChart.isNotify());
        chart.setPadding(tmpJFreeChart.getPadding());
        chart.setRenderingHints(tmpJFreeChart.getRenderingHints());
        if (subtitles.size() > 0) {
            chart.setSubtitles(subtitles);
        }
        chart.setTitle(tmpJFreeChart.getTitle());
        if(chart.getLegend() != null){
            chart.getLegend().setItemFont(tmpJFreeChart.getLegend().getItemFont());
        }
        return chart;
    }

    // JFreeChartFactoryServiceMBean��JavaDoc
    public ServiceName getPlotFactoryServiceName() {
        return plotFactoryServiceName;
    }

    // JFreeChartFactoryServiceMBean��JavaDoc
    public void setPlotFactoryServiceName(ServiceName serviceName) {
        plotFactoryServiceName = serviceName;
    }

    // JFreeChartFactoryServiceMBean��JavaDoc
    public void addSubtitle(Title title) {
        subtitles.add(title);
    }

    // JFreeChartFactoryServiceMBean��JavaDoc
    public void setCreateLegend(boolean createLegend) {
        this.createLegend = createLegend;
    }

    public void setPlotFactory(PlotFactory factory){
        plotFactory = factory;
    }

}
