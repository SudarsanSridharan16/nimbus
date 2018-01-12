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
 * �`���[�g�����B<p>
 *
 * @author k2-taniguchi
 */
public interface ChartCondition {

    /**
     * �v���b�g������ǉ�����B<p>
     *
     * @param plotCondition �v���b�g����
     */
    public void addPlotCondition(PlotCondition plotCondition);

    /**
     * �w�肳�ꂽ�v���b�g���̃v���b�g�����z����擾����B<p>
     *
     * @param plotName �v���b�g��
     * @return �v���b�g�����z��
     */
    public PlotCondition[] getPlotConditions(String plotName);

    /**
     * �v���b�g�����z����擾����B<p>
     *
     * @return �v���b�g�����z��
     */
    public PlotCondition[] getPlotConditions();

    /**
     * �v���b�g���̃C�e���[�^���擾����B<p>
     *
     * @return �v���b�g���̃C�e���[�^
     */
    public Iterator getPlotNames();

    /**
     * JFreeChart�̃^�C�g����ݒ肷��B<p>
     *
     * @param title �^�C�g��
     */
    public void setTitle(String title);

    /**
     * JFreeChart�̃^�C�g�����擾����B<p>
     *
     * @return �^�C�g��
     */
    public String getTitle();

    /**
     * JFreeChart�̃^�C�g���t�H���g����ݒ肷��B<p>
     *
     * @param name �t�H���g��
     */
    public void setTitleFontName(String name);

    /**
     * JFreeChart�̃^�C�g���t�H���g�����擾����B<p>
     *
     * @return �t�H���g��
     */
    public String getTitleFontName();

    /**
     * JFreeChart�̃^�C�g���t�H���g�X�^�C����ݒ肷��B<p>
     *
     * @param style �t�H���g�X�^�C��
     */
    public void setTitleFontStyle(int style);

    /**
     * JFreeChart�̃^�C�g���t�H���g�X�^�C�����擾����B<p>
     *
     * @return �t�H���g�X�^�C��
     */
    public int getTitleFontStyle();

    /**
     * JFreeChart�̃^�C�g���t�H���g�T�C�Y��ݒ肷��B<p>
     *
     * @param size �t�H���g�T�C�Y
     */
    public void setTitleFontSize(int size);

    /**
     * JFreeChart�̃^�C�g���t�H���g�T�C�Y���擾����B<p>
     *
     * @return �t�H���g�T�C�Y
     */
    public int getTitleFontSize();

    /**
     * �f�t�H���g�̃T�u�^�C�g���t�H���g����ݒ肷��B<p>
     *
     * @param name �t�H���g��
     */
    public void setDefaultSubtitleFontName(String name);

    /**
     * �f�t�H���g�̃T�u�^�C�g���t�H���g�����擾����B<p>
     *
     * @return �t�H���g��
     */
    public String getDefaultSubtitleFontName();

    /**
     * �f�t�H���g�̃T�u�^�C�g���t�H���g�X�^�C����ݒ肷��B<p>
     *
     * @param style �t�H���g�X�^�C��
     */
    public void setDefaultSubtitleFontStyle(int style);

    /**
     * �f�t�H���g�̃T�u�^�C�g���t�H���g�X�^�C�����擾����B<p>
     *
     * @return �t�H���g�X�^�C��
     */
    public int getDefaultSubtitleFontStyle();

    /**
     * �f�t�H���g�̃T�u�^�C�g���t�H���g�T�C�Y��ݒ肷��B<p>
     *
     * @param size �t�H���g�T�C�Y
     */
    public void setDefaultSubtitleFontSize(int size);

    /**
     * �f�t�H���g�̃T�u�^�C�g���t�H���g�T�C�Y���擾����B<p>
     *
     * @return �t�H���g�T�C�Y
     */
    public int getDefaultSubtitleFontSize();

    /**
     * �w�肳�ꂽ�C���f�b�N�X�̃T�u�^�C�g���t�H���g����ݒ肷��B<p>
     *
     * @param index �T�u�^�C�g���C���f�b�N�X
     * @param name �t�H���g��
     */
    public void setSubtitleFontName(int index, String name);

    /**
     * �w�肳�ꂽ�C���f�b�N�X�̃T�u�^�C�g���t�H���g�����擾����B<p>
     *
     * @param index �T�u�^�C�g���C���f�b�N�X
     * @return �t�H���g��
     */
    public String getSubtitleFontName(int index);

    /**
     * �w�肳�ꂽ�C���f�b�N�X�̃T�u�^�C�g���t�H���g�X�^�C����ݒ肷��B<p>
     *
     * @param index �T�u�^�C�g���C���f�b�N�X
     * @param style �t�H���g�X�^�C��
     */
    public void setSubtitleFontStyle(int index, int style);

    /**
     * �w�肳�ꂽ�C���f�b�N�X�̃T�u�^�C�g���t�H���g�X�^�C�����擾����B<p>
     *
     * @param index �T�u�^�C�g���C���f�b�N�X
     * @return �t�H���g�X�^�C��
     */
    public int getSubtitleFontStyle(int index);

    /**
     * �w�肳�ꂽ�C���f�b�N�X�̃T�u�^�C�g���t�H���g�T�C�Y��ݒ肷��B<p>
     *
     * @param index �T�u�^�C�g���C���f�b�N�X
     * @param size �t�H���g�T�C�Y
     */
    public void setSubtitleFontSize(int index, int size);

    /**
     * �w�肳�ꂽ�C���f�b�N�X�̃T�u�^�C�g���t�H���g�T�C�Y���擾����B<p>
     *
     * @param index �T�u�^�C�g���C���f�b�N�X
     * @return �t�H���g�T�C�Y
     */
    public int getSubtitleFontSize(int index);

}
