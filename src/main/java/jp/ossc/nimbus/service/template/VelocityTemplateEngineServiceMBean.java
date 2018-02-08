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
package jp.ossc.nimbus.service.template;

import java.io.File;
import java.util.Properties;

import jp.ossc.nimbus.core.ServiceBaseMBean;

/**
 * {@link VelocityTemplateEngineService}��MBean�C���^�t�F�[�X<p>
 * 
 * @author M.Takata
 * @see VelocityTemplateEngineService
 */
public interface VelocityTemplateEngineServiceMBean extends ServiceBaseMBean{
    
    /**
     * �e���v���[�g�t�@�C���̃��[�g�f�B���N�g����ݒ肷��B<p>
     * �f�t�H���g�ł́A"."�B<br>
     *
     * @param dir �e���v���[�g�t�@�C���̃��[�g�f�B���N�g��
     */
    public void setTemplateFileRootDirectory(File dir);
    
    /**
     * �e���v���[�g�t�@�C���̃��[�g�f�B���N�g�����擾����B<p>
     *
     * @return �e���v���[�g�t�@�C���̃��[�g�f�B���N�g��
     */
    public File getTemplateFileRootDirectory();
    
    /**
     * Velocity�̏������v���p�e�B��ݒ肷��B<p>
     *
     * @param props �������v���p�e�B
     */
    public void setProperties(Properties props);
    
    /**
     * Velocity�̏������v���p�e�B���擾����B<p>
     *
     * @return �������v���p�e�B
     */
    public Properties getProperties();
    
    /**
     * �f�t�H���g�̕����R�[�h��ݒ肷��B<p>
     * 
     * @param encoding �����R�[�h
     */
    public void setCharacterEncoding(String encoding);
    
    /**
     * �f�t�H���g�̕����R�[�h���擾����B<p>
     * 
     * @return �����R�[�h
     */
    public String getCharacterEncoding();
}