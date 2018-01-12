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
package jp.ossc.nimbus.service.test.report;

import java.io.File;
import java.io.IOException;

import jp.ossc.nimbus.core.ServiceBaseMBean;

/**
 * {@link CSVTestReporterService}��MBean�C���^�t�F�[�X<p>
 * 
 * @author M.Takata
 * @see CSVTestReporterService
 */
public interface CSVTestReporterServiceMBean extends ServiceBaseMBean{
    
    /**
     * �o�͂��郌�|�[�gCSV�t�@�C����ݒ肷��B<p>
     *
     * @param file �o�͂��郌�|�[�gCSV�t�@�C��
     * @Exception IOException �w�肵���t�@�C���̃p�X���擾�ł��Ȃ��ꍇ
     */
    public void setOutputFile(File file) throws IOException;
    
    /**
     * �o�͂��郌�|�[�gCSV�t�@�C�����擾����B<p>
     *
     * @param file �o�͂��郌�|�[�gCSV�t�@�C��
     */
    public File getOutputFile();
    
    /**
     * �o�͂��郌�|�[�gCSV�t�@�C���̕����G���R�[�f�B���O��ݒ肷��B<p>
     * �ݒ肵�Ȃ��ꍇ�́AOS�̕����G���R�[�f�B���O�ɏ]���B<br>
     *
     * @param encoding �����G���R�[�f�B���O
     */
    public void setEncoding(String encoding);
    
    /**
     * �o�͂��郌�|�[�gCSV�t�@�C���̕����G���R�[�f�B���O���擾����B<p>
     *
     * @return �����G���R�[�f�B���O
     */
    public String getEncoding();
    
    /**
     * ���|�[�gCSV�t�@�C���ɏo�͂���������̃t�H�[�}�b�g��ݒ肷��B<p>
     * �ݒ肵�Ȃ��ꍇ�́Ayyyy/MM/dd HH:mm:ss.SSS�B<br>
     *
     * @param format �������̃t�H�[�}�b�g
     */
    public void setDateFormat(String format);
    
    /**
     * ���|�[�gCSV�t�@�C���ɏo�͂���������̃t�H�[�}�b�g���擾����B<p>
     *
     * @return �������̃t�H�[�}�b�g
     */
    public String getDateFormat();
}
