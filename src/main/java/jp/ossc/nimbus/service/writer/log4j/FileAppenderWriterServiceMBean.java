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

/**
 * {@link FileAppenderWriterService}�T�[�r�X��MBean�C���^�t�F�[�X�B<p>
 * 
 * @author M.Takata
 */
public interface FileAppenderWriterServiceMBean
 extends WriterAppenderWriterServiceMBean{
    
    /**
     * �t�@�C���ǉ��������݃��[�h��ݒ肷��B<p>
     *
     * @param append �ǉ��������݂��s���ꍇtrue
     */
    public void setAppend(boolean append);
    
    /**
     * �t�@�C���ǉ��������݃��[�h���ǂ����𒲂ׂ�B<p>
     *
     * @return true�̏ꍇ�A�ǉ��������݂��s��
     */
    public boolean isAppend();
    
    /**
     * �t�@�C���������݂Ńo�b�t�@�����O���s������ݒ肷��B<p>
     *
     * @param bufferedIO BufferedWriter���g�p���ăo�b�t�@�����O����ꍇtrue
     */
    public void setBufferedIO(boolean bufferedIO);
    
    /**
     * �o�b�t�@�����O���ăt�@�C���������݂��s�������ׂ�B<p>
     *
     * @return true�̏ꍇ�A�o�b�t�@�����O����
     */
    public boolean isBufferedIO();
    
    /**
     * �o�b�t�@�T�C�Y��ݒ肷��B<p>
     * {@link #isBufferedIO()}��true�̏ꍇ�A�L���ɂȂ�B<br>
     *
     * @param bufferSize �o�b�t�@�T�C�Y
     */
    public void setBufferSize(int bufferSize);
    
    /**
     * �o�b�t�@�T�C�Y���擾����B<p>
     *
     * @return �o�b�t�@�T�C�Y
     */
    public int getBufferSize();
    
    /**
     * �o�͐�̃t�@�C�������w�肷��B<p>
     *
     * @param file �o�͐�t�@�C����
     */
    public void setFile(String file);
    
    /**
     * �o�͐�t�@�C�������擾����B<p>
     *
     * @return �o�͐�t�@�C����
     */
    public String getFile();
}