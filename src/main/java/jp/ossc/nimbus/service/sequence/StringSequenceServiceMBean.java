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
package jp.ossc.nimbus.service.sequence;

import jp.ossc.nimbus.core.*;

/**
 * {@link StringSequenceService}��MBean�C���^�t�F�[�X�B<p>
 * 
 * @author H.Nakano
 */
public interface StringSequenceServiceMBean extends ServiceBaseMBean {
    
    /**
     * ���Ԃ���ԍ��̃t�H�[�}�b�g��ݒ肷��B<p>
     * �t�H�[�}�b�g�̎w����@�́A�ȉ��B<br>
     * <ul>
     *   <li>�Œ�v�f�́A���̂܂܎w�肷��B</li>
     *   <li>�����v�f�́A"�J�n����,�I������"�Ŏw�肷��B�܂��A�J�n�����A�I����������1�����Ŏw�肵�Ȃ���΂Ȃ�Ȃ��B</li>
     *   <li>�R���e�L�X�g�ϐ��v�f�́A"%�R���e�L�X�g�L�[��%"�Ŏw�肷��B</li>
     *   <li>�����ʔԗv�f�́A"TIME_SEQ(�����t�H�[�}�b�g,�ʔԌ���)"�Ŏw�肷��B</li>
     * </ul>
     * �܂��A�e�v�f�́A;�ŋ�؂�B<br>
     * <pre>
     *  �ݒ��F
     *    ID_;%HOST_NAME%;_;0,9;0,9;_;TIME_SEQ(HHmmss,3)
     *    
     *  ���Ԍ��ʁF
     *    ID_server1_00001
     * </pre>
     * 
     * @param format ���Ԃ���ԍ��̃t�H�[�}�b�g������
     */
    public void setFormat(String format);
    
    /**
     * ���Ԃ���ԍ��̃t�H�[�}�b�g���擾����B<p>
     * 
     * @return ���Ԃ���ԍ��̃t�H�[�}�b�g������
     */
    public String getFormat();
    
    /**
     * {@link jp.ossc.nimbus.service.context.Context Context}�T�[�r�X�̃T�[�r�X����ݒ肷��B<p>
     * ���Ԃ���ԍ��ɃR���e�L�X�g�ϐ��v�f���g���ꍇ�ɁA�擾����Context�T�[�r�X��ݒ肷��B<br>
     *
     * @param name Context�T�[�r�X�̃T�[�r�X��
     */
    public void setContextServiceName(ServiceName name);
    
    /**
     * {@link jp.ossc.nimbus.service.context.Context Context}�T�[�r�X�̃T�[�r�X�����擾����B<p>
     *
     * @return Context�T�[�r�X�̃T�[�r�X��
     */
    public ServiceName getContextServiceName();
    
    /**
     * ���Ԃ����ԍ����i��������t�@�C������ݒ肷��B<p>
     * ���̑�����ݒ肷��ƁA���Ԃ����ԍ����t�@�C���ɉi��������B<br>
     * �܂��A�T�[�r�X�̊J�n���ɁA�i�����t�@�C�������݂���ꍇ�́A�ǂݍ���Ŕԍ��𕜌����āA�ŏI���Ԕԍ��Ƃ���B<br>
     *
     * @param file ���Ԃ����ԍ����i��������t�@�C����
     */
    public void setPersistFile(String file);
    
    /**
     * ���Ԃ����ԍ����i��������t�@�C�������擾����B<p>
     *
     * @return ���Ԃ����ԍ����i��������t�@�C����
     */
    public String getPersistFile();
    
    /**
     * �ԍ��𔭔Ԃ��閈�ɉi�������邩�ǂ�����ݒ肷��B<p>
     * {@link #setPersistFile(String)}�ŉi�����t�@�C����ݒ肵�Ă���ꍇ�̂݁A�L���ł���B<br>
     * �f�t�H���g�́Afalse�ŁA�T�[�r�X�̒�~���̂݉i��������B���̏ꍇ�A�v���Z�X��kill����ȂǁA�T�[�r�X�̒�~�����������Ȃ������ꍇ�ɂ́A�i��������Ȃ���������B<br>
     *
     * @param isEveryTime �ԍ��𔭔Ԃ��閈�ɉi��������ꍇ��true
     */
    public void setPersistEveryTime(boolean isEveryTime);
    
    /**
     * �ԍ��𔭔Ԃ��閈�ɉi�������邩�ǂ����𔻒肷��B<p>
     *
     * @return true�̏ꍇ�A�ԍ��𔭔Ԃ��閈�ɉi��������
     */
    public boolean isPersistEveryTime();
}
