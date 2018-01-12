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
package jp.ossc.nimbus.service.writer.log4j;

import jp.ossc.nimbus.core.ServiceBaseMBean;
import jp.ossc.nimbus.service.writer.*;

/**
 * {@link WriterAppenderWriterService}�T�[�r�X��MBean�C���^�t�F�[�X�B<p>
 * 
 * @author M.Takata
 */
public interface WriterAppenderWriterServiceMBean
 extends ServiceBaseMBean, MessageWriter{
    
    /**
     * ����t���b�V�����邩��ݒ肷��B<p>
     *
     * @param flush ����t���b�V������ꍇtrue
     */
    public void setImmediateFlush(boolean flush);
    
    /**
     * ����t���b�V�����邩�𒲂ׂ�B<p>
     *
     * @return true�̏ꍇ�A����t���b�V������
     */
    public boolean isImmediateFlush();
    
    /**
     * �o�̓t�@�C���̕����G���R�[�f�B���O��ݒ肷��B<p>
     *
     * @param encoding �����G���R�[�f�B���O
     */
    public void setEncoding(String encoding);
    
    /**
     * �o�̓t�@�C���̕����G���R�[�f�B���O���擾����B<p>
     *
     * @return �����G���R�[�f�B���O
     */
    public String getEncoding();
    
    /**
     * ���C�A�E�g�̃w�b�_�������ݒ肷��B<p>
     *
     * @param header �w�b�_������
     */
    public void setHeader(String header);
    
    /**
     * ���C�A�E�g�̃w�b�_��������擾����B<p>
     *
     * @return �w�b�_������
     */
    public String getHeader();
    
    /**
     * ���C�A�E�g�̃t�b�^�������ݒ肷��B<p>
     *
     * @param footer �t�b�^������
     */
    public void setFooter(String footer);
    
    /**
     * ���C�A�E�g�̃t�b�^��������擾����B<p>
     *
     * @return �t�b�^������
     */
    public String getFooter();
    
    /**
     * �����I�ɏ������ނ��ǂ�����ݒ肷��B<p>
     * �f�t�H���g�́Afalse�œ��������Ȃ��B<br>
     *
     * @param isSynch �����I�ɏ������ޏꍇ�Atrue
     */
    public void setSynchronized(boolean isSynch);
    
    /**
     * �����I�ɏ������ނ��ǂ����𔻒肷��B<p>
     *
     * @return true�̏ꍇ�A�����I�ɏ�������
     */
    public boolean isSynchronized();
}