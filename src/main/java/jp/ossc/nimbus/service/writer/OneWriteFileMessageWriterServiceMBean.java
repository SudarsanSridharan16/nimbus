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
package jp.ossc.nimbus.service.writer;

import jp.ossc.nimbus.core.*;

/**
 * {@link OneWriteFileMessageWriterService}�T�[�r�X��MBean�C���^�t�F�[�X�B<p>
 * 
 * @author M.Takata
 */
public interface OneWriteFileMessageWriterServiceMBean
 extends ServiceBaseMBean{
    
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
    
    /**
     * �o�͐�t�@�C�����̃v���t�B�N�X��ݒ肷��B<p>
     * �v���t�B�N�X�ɂ́A�Œ蕶���̑��ɁA%�ň͂񂾃L�[���w�肷�鎖���ł���B<br>
     * �L�[�w�肳�ꂽ�l�́A�R���e�L�X�g�܂��͓��͂�WritableRecord����擾�����B<br>
     *
     * @param prefix �o�͐�t�@�C�����̃v���t�B�N�X
     */
    public void setFilePrefix(String prefix);
    
    /**
     * �o�͐�t�@�C�����̃v���t�B�N�X���擾����B<p>
     *
     * @return �o�͐�t�@�C�����̃v���t�B�N�X
     */
    public String getFilePrefix();
    
    /**
     * �o�͐�t�@�C�����̃|�X�g�t�B�N�X��ݒ肷��B<p>
     * �|�X�g�t�B�N�X�ɂ́A�Œ蕶���̑��ɁA%�ň͂񂾃L�[���w�肷�鎖���ł���B<br>
     * �L�[�w�肳�ꂽ�l�́A�R���e�L�X�g�܂��͓��͂�WritableRecord����擾�����B<br>
     *
     * @param postfix �o�͐�t�@�C�����̃|�X�g�t�B�N�X
     */
    public void setFilePostfix(String postfix);
    
    /**
     * �o�͐�t�@�C�����̃|�X�g�t�B�N�X���擾����B<p>
     *
     * @return �o�͐�t�@�C�����̃|�X�g�t�B�N�X
     */
    public String getFilePostfix();
    
    /**
     * �t�@�C���ɒǋL���邩�ǂ�����ݒ肷��B<p>
     * 
     * @param isAppend �t�@�C���ɒǋL����ꍇ�Atrue
     */
    public void setAppend(boolean isAppend);
    
    /**
     * �t�@�C���ɒǋL���邩�ǂ������擾����B<p>
     * 
     * @return true�̏ꍇ�A�t�@�C���ɒǋL����B
     */
    public boolean isAppend();
    
    /**
     * �t�@�C���̃w�b�_��ݒ肷��B<p>
     * �t�@�C�������݂��Ȃ����A�܂��́A�ǉ������݂łȂ��ꍇ�ɏo�͂���B<br>
     *
     * @param header �w�b�_
     */
    public void setHeader(String header);
    
    /**
     * �t�@�C���̃w�b�_���擾����B<p>
     *
     * @return �w�b�_
     */
    public String getHeader();
    
    /**
     * 
     * ����o�̓X�g���[������邩�ǂ�����ݒ肷��B<p>
     * true���w�肵���ꍇ�A����X�g���[�����J����Bfalse���w�肵���ꍇ�́A�X�g���[���͊J���������ł���B<br>
     * �A���A�L�[�w����܂ޏo�͐�t�@�C�����̃v���t�B�N�X�y�у|�X�g�t�B�N�X���w�肳��Ă���ꍇ�́A�t�@�C���������I�ɂȂ�\�������邽�߁Afalse�ɂ͂ł��Ȃ��B<br>
     *
     * @param isClose ����o�̓X�g���[�������ꍇ�Atrue
     */
    public void setEveryTimeCloseStream(boolean isClose);
    
    /**
     * ����o�̓X�g���[������邩�ǂ����𔻒肷��B<p>
     * 
     * @return true�̏ꍇ�A����o�̓X�g���[�������
     */
    public boolean isEveryTimeCloseStream();
    
    /**
     * �R���e�L�X�g�T�[�r�X����ݒ肷��B<p>
     *
     * @param name �R���e�L�X�g�T�[�r�X��
     */
    public void setContextServiceName(ServiceName name);
    
    /**
     * �R���e�L�X�g�T�[�r�X�����擾����B<p>
     *
     * @return �R���e�L�X�g�T�[�r�X��
     */
    public ServiceName getContextServiceName();
    
    /**
     * �t�@�C�����Ɏg�p����WritableElement���t�@�C���ɏo�͂��邩�ǂ�����ݒ肷��B<p>
     * �f�t�H���g�́Atrue�ŏo�͂���B<br>
     *
     * @param isOutput �t�@�C�����Ɏg�p����WritableElement���t�@�C���ɏo�͂���ꍇtrue
     */
    public void setOutputKey(boolean isOutput);
    
    /**
     * �t�@�C�����Ɏg�p����WritableElement���t�@�C���ɏo�͂��邩�ǂ����𔻒肷��B<p>
     *
     * @return true�̏ꍇ�A�t�@�C�����Ɏg�p����WritableElement���t�@�C���ɏo�͂���
     */
    public boolean isOutputKey();
    
    /**
     * �ǋL���邽�тɍŌ�ɏ������܂��Z�p���[�^��ݒ肷��B<p>
     * �ǋL����ݒ�̏ꍇ�̂ݎg�p����܂��B
     * 
     * @param separator �Z�p���[�^
     */
    public void setSeparator(String separator);
    
    /**
     * �ǋL���邽�тɍŌ�ɏ������܂��Z�p���[�^���擾����B<p>
     * 
     * @return �Z�p���[�^
     */
    public String getSeparator();
}
