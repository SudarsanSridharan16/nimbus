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
package jp.ossc.nimbus.service.scheduler2;

import jp.ossc.nimbus.core.*;

/**
 * {@link DatabaseConditionScheduleMakerService}��MBean�C���^�t�F�[�X�B<p>
 * 
 * @author M.Takata
 */
public interface DatabaseConditionScheduleMakerServiceMBean
 extends DefaultScheduleMakerServiceMBean{
    
    /**
     * {jp.ossc.nimbus.service.connection.ConnectionFactory ConnectionFactory}�T�[�r�X�̃T�[�r�X����ݒ肷��B<p>
     *
     * @param name ConnectionFactory�T�[�r�X�̃T�[�r�X��
     */
    public void setConnectionFactoryServiceName(ServiceName name);
    
    /**
     * {jp.ossc.nimbus.service.connection.ConnectionFactory ConnectionFactory}�T�[�r�X�̃T�[�r�X�����擾����B<p>
     *
     * @return ConnectionFactory�T�[�r�X�̃T�[�r�X��
     */
    public ServiceName getConnectionFactoryServiceName();
    
    /**
     * �X�P�W���[���̍쐬�L���𔻒f����SQL��ݒ肷��B<p>
     * �K���A�X�P�W���[���쐬���t�𖄂ߍ��݃p�����[�^�Ƃ��Ď����A���ʂ�Boolean�^�A���l�^�A������^�̂����ꂩ�ɂȂ�SQL�Ƃ��鎖�B<br>
     * Boolean�^�̏ꍇ�́Atrue�̏ꍇ�A�X�P�W���[�����쐬����B<br>
     * ���l�^�̏ꍇ�́A0�ȊO�̒l�̏ꍇ�A�X�P�W���[�����쐬����B<br>
     * ������^�̏ꍇ�́A"0"�ȊO�̒l�̏ꍇ�A�X�P�W���[�����쐬����B<br>
     * <pre>
     *  ��F�c�Ɠ��ł���΃X�P�W���[�����쐬����ꍇ
     *   select count(1) from businessday_calendar where date = ?
     * </pre>
     *
     * @param query SQL
     */
    public void setQuery(String query);
    
    /**
     * �X�P�W���[���̍쐬�L���𔻒f����SQL���擾����B<p>
     *
     * @return SQL
     */
    public String getQuery();
    
    /**
     * ���t�t�H�[�}�b�g��ݒ肷��B<p>
     * {@link #setQuery(String)}�Ŏw�肵��SQL���ɖ��ߍ��ރX�P�W���[���쐬���t�𕶎���Ƃ��ēn�������ꍇ�ɁA���̓��t�t�H�[�}�b�g���w�肷��B<br>
     * 
     * @param format ���t�t�H�[�}�b�g
     */
    public void setDateFormat(String format);
    
    /**
     * ���t�t�H�[�}�b�g���擾����B<p>
     * 
     * @return ���t�t�H�[�}�b�g
     */
    public String getDateFormat();
}