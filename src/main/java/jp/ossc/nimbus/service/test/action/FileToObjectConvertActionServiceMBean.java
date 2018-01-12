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
package jp.ossc.nimbus.service.test.action;

import jp.ossc.nimbus.core.ServiceName;
import jp.ossc.nimbus.core.ServiceBaseMBean;

/**
 * {@link FileToObjectConvertActionService}��MBean�C���^�t�F�[�X<p>
 * 
 * @author M.Takata
 * @see FileToObjectConvertActionService
 */
public interface FileToObjectConvertActionServiceMBean extends ServiceBaseMBean{
    
    /**
     * {@link jp.ossc.nimbus.util.converter.StreamConverter StreamConverter}�T�[�r�X�̃T�[�r�X����ݒ肷��B<p>
     *
     * @param name StreamConverter�T�[�r�X�̃T�[�r�X��
     */
    public void setStreamConverterServiceName(ServiceName name);
    
    /**
     * {@link jp.ossc.nimbus.util.converter.StreamConverter StreamConverter}�T�[�r�X�̃T�[�r�X�����擾����B<p>
     *
     * @return StreamConverter�T�[�r�X�̃T�[�r�X��
     */
    public ServiceName getStreamConverterServiceName();
    
    /**
     * {@link jp.ossc.nimbus.util.converter.FormatConverter FormatConverter}�T�[�r�X�̃T�[�r�X����ݒ肷��B<p>
     *
     * @param name FormatConverter�T�[�r�X�̃T�[�r�X��
     */
    public void setFormatConverterServiceName(ServiceName name);
    
    /**
     * {@link jp.ossc.nimbus.util.converter.FormatConverter FormatConverter}�T�[�r�X�̃T�[�r�X�����擾����B<p>
     *
     * @return FormatConverter�T�[�r�X�̃T�[�r�X��
     */
    public ServiceName getFormatConverterServiceName();
    
    /**
     * {@link jp.ossc.nimbus.service.interpreter.Interpreter Interpreter}�T�[�r�X�̃T�[�r�X����ݒ肷��B<p>
     *
     * @param name Interpreter�T�[�r�X�̃T�[�r�X��
     */
    public void setInterpreterServiceName(ServiceName name);
    
    /**
     * {@link jp.ossc.nimbus.service.interpreter.Interpreter Interpreter}�T�[�r�X�̃T�[�r�X�����擾����B<p>
     *
     * @return Interpreter�T�[�r�X�̃T�[�r�X��
     */
    public ServiceName getInterpreterServiceName();
    
    /**
     * �ϊ��Ώۂ̃t�@�C���̕����G���R�[�f�B���O��ݒ肷��B<p>
     *
     * @param encoding �����G���R�[�f�B���O
     */
    public void setFileEncoding(String encoding);
    
    /**
     * �ϊ��Ώۂ̃t�@�C���̕����G���R�[�f�B���O���擾����B<p>
     *
     * @return �����G���R�[�f�B���O
     */
    public String getFileEncoding();
    
    /**
     * ���̃A�N�V�����̃��\�[�X��`���쐬����ۂ̃f�t�H���g�̑z��R�X�g��ݒ肷��B<p>
     * 
     * @param cost �z��R�X�g
     */
    public void setExpectedCost(double cost);
    
    /**
     * ���̃A�N�V�����̃��\�[�X��`���쐬����ۂ̃f�t�H���g�̑z��R�X�g���擾����B<p>
     * 
     * @return �z��R�X�g
     */
    public double getExpectedCost();
}
