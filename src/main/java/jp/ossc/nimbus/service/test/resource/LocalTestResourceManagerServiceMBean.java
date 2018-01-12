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
package jp.ossc.nimbus.service.test.resource;

import java.io.File;

import jp.ossc.nimbus.core.ServiceBaseMBean;
import jp.ossc.nimbus.core.ServiceName;

/**
 * {@link LocalTestResourceManagerService}��MBean�C���^�t�F�[�X<p>
 * 
 * @author M.Aono
 * @see LocalTestResourceManagerService
 */
public interface LocalTestResourceManagerServiceMBean extends ServiceBaseMBean{
    
    public static final String DEFAULT_TEMPLATE_LINK_FILE_EXTENTION = ".tln";
    
    /**
     * �e�X�g���\�[�X�����݂���f�B���N�g�����擾����B<p>
     *
     * @return �f�B���N�g��
     */
    public File getTestResourceDirectory();
    
    /**
     * �e�X�g���\�[�X�����݂���f�B���N�g����ݒ肷��B<p>
     * �w�肵�Ȃ��ꍇ�́A���̃T�[�r�X�̃T�[�r�X��`�t�@�C�������݂���f�B���N�g���B<br>
     *
     * @param path �f�B���N�g��
     */
    public void setTestResourceDirectory(File path);
    
    /**
     * {@link jp.ossc.nimbus.service.test.TemplateEngine TemplateEngine}�T�[�r�X�̃T�[�r�X����ݒ肷��B<p>
     *
     * @param name TemplateEngine�T�[�r�X�̃T�[�r�X��
     */
    public void setTemplateEngineServiceName(ServiceName name);
    
    /**
     * {@link jp.ossc.nimbus.service.test.TemplateEngine TemplateEngine}�T�[�r�X�̃T�[�r�X�����擾����B<p>
     *
     * @return TemplateEngine�T�[�r�X�̃T�[�r�X��
     */
    public ServiceName getTemplateEngineServiceName();
    
    /**
     * �e���v���[�g�����N�t�@�C���̊g���q��ݒ肷��B<p>
     * �f�t�H���g�́A{@link #DEFAULT_TEMPLATE_LINK_FILE_EXTENTION}�B<br>
     *
     * @param ext �g���q
     */
    public void setTemplateLinkFileExtention(String ext);
    
    /**
     * �e���v���[�g�����N�t�@�C���̊g���q���擾����B<p>
     *
     * @return �g���q
     */
    public String getTemplateLinkFileExtention();
    
    /**
     * �e���v���[�g�����N�t�@�C���̕����G���R�[�f�B���O��ݒ肷��B<p>
     *
     * @param encoding �����G���R�[�f�B���O
     */
    public void setTemplateLinkFileEncoding(String encoding);
    
    /**
     * �e���v���[�g�����N�t�@�C���̕����G���R�[�f�B���O���擾����B<p>
     *
     * @return �����G���R�[�f�B���O
     */
    public String getTemplateLinkFileEncoding();
}