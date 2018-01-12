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
package jp.ossc.nimbus.service.http;

import java.io.*;
import java.util.*;

/**
 * HTTP���N�G�X�g�B<p>
 *
 * @author M.Takata
 */
public interface HttpRequest{
    
    /**
     * ���N�G�X�g����ӂɎ��ʂ���_���A�N�V���������擾����B<p>
     *
     * @return �A�N�V������
     */
    public String getActionName();
    
    /**
     * ���N�G�X�g����URL���擾����B<p>
     *
     * @return URL
     */
    public String getURL();
    
    /**
     * ���N�G�X�g����URL��ݒ肷��B<p>
     *
     * @param url URL
     */
    public void setURL(String url);
    
    /**
     * ���N�G�X�g����HTTP�̃o�[�W�������擾����B<p>
     *
     * @return HTTP�̃o�[�W����
     */
    public String getHttpVersion();
    
    /**
     * ���N�G�X�g����HTTP�̃o�[�W������ݒ肷��B<p>
     *
     * @param version HTTP�̃o�[�W����
     */
    public void setHttpVersion(String version);
    
    /**
     * ���N�G�X�g����HTTP�w�b�_���̏W�����擾����B<p>
     *
     * @return HTTP�w�b�_���̏W��
     */
    public Set getHeaderNameSet();
    
    /**
     * �w�肳�ꂽ���O��HTTP�w�b�_���擾����B<p>
     * ����w�b�_���ŕ����̒l������ꍇ�́A�ŏ��̒l�B<br>
     *
     * @return HTTP�w�b�_
     */
    public String getHeader(String name);
    
    /**
     * �w�肳�ꂽ���O��HTTP�w�b�_���擾����B<p>
     *
     * @return HTTP�w�b�_�z��
     */
    public String[] getHeaders(String name);
    
    /**
     * HTTP�w�b�_��ݒ肷��B<p>
     *
     * @param name HTTP�w�b�_��
     * @param value HTTP�w�b�_
     */
    public void setHeader(String name, String value);
    
    /**
     * HTTP�w�b�_��ݒ肷��B<p>
     *
     * @param name HTTP�w�b�_��
     * @param value HTTP�w�b�_�z��
     */
    public void setHeaders(String name, String[] value);
    
    /**
     * HTTP�w�b�_��ǉ�����B<p>
     *
     * @param name HTTP�w�b�_��
     * @param value HTTP�w�b�_
     */
    public void addHeader(String name, String value);
    
    /**
     * ���N�G�X�g�̃R���e���g�^�C�v���擾����B<p>
     *
     * @return �R���e���g�^�C�v
     */
    public String getContentType();
    
    /**
     * ���N�G�X�g�̃R���e���g�^�C�v��ݒ肷��B<p>
     *
     * @param type �R���e���g�^�C�v
     */
    public void setContentType(String type);
    
    /**
     * ���N�G�X�g�̕����G���R�[�f�B���O���擾����B<p>
     *
     * @return �����G���R�[�f�B���O
     */
    public String getCharacterEncoding();
    
    /**
     * ���N�G�X�g�̕����G���R�[�f�B���O��ݒ肷��B<p>
     *
     * @param encoding �����G���R�[�f�B���O
     */
    public void setCharacterEncoding(String encoding);
    
    /**
     * ���N�G�X�g�̃N�G�����擾����B<p>
     *
     * @return �N�G��������
     */
    public String getQueryString();
    
    /**
     * ���N�G�X�g�̃N�G����ݒ肷��B<p>
     *
     * @param query �N�G��������
     */
    public void setQueryString(String query);
    
    /**
     * ���N�G�X�g�p�����[�^���̏W�����擾����B<p>
     *
     * @return ���N�G�X�g�p�����[�^���̏W��
     */
    public Set getParameterNameSet();
    
    /**
     * �w�肳�ꂽ���O�̃��N�G�X�g�p�����[�^���擾����B<p>
     * ���ꃊ�N�G�X�g�p�����[�^���ŕ����̒l������ꍇ�́A�ŏ��̒l�B<br>
     *
     * @return ���N�G�X�g�p�����[�^
     */
    public String getParameter(String name);
    
    /**
     * �w�肳�ꂽ���O�̃��N�G�X�g�p�����[�^���擾����B<p>
     *
     * @return ���N�G�X�g�p�����[�^
     */
    public String[] getParameters(String name);
    
    /**
     * ���N�G�X�g�p�����[�^��ݒ肷��B<p>
     *
     * @param name ���N�G�X�g�p�����[�^��
     * @param value ���N�G�X�g�p�����[�^
     */
    public void setParameter(String name, String value);
    
    /**
     * ���N�G�X�g�p�����[�^��ݒ肷��B<p>
     *
     * @param name ���N�G�X�g�p�����[�^��
     * @param value ���N�G�X�g�p�����[�^
     */
    public void setParameters(String name, String[] value);
    
    /**
     * ���N�G�X�g�p�����[�^��ݒ肷��B<p>
     *
     * @param name ���N�G�X�g�p�����[�^��
     * @param file ���M�t�@�C��
     */
    public void setFileParameter(String name, File file) throws java.io.FileNotFoundException;
    
    /**
     * ���N�G�X�g�p�����[�^��ݒ肷��B<p>
     *
     * @param name ���N�G�X�g�p�����[�^��
     * @param file ���M�t�@�C��
     * @param fileName ���M�t�@�C����
     * @param contentType �R���e���g�^�C�v
     */
    public void setFileParameter(String name, File file, String fileName, String contentType) throws java.io.FileNotFoundException;
    
    /**
     * ���N�G�X�g�X�g���[���ɏ������ނ��߂̓��̓X�g���[����ݒ肷��B<p>
     *
     * @param is ���̓X�g���[��
     */
    public void setInputStream(InputStream is);
    
    /**
     * ���N�G�X�g�X�g���[�����擾����B<p>
     *
     * @return ���N�G�X�g�X�g���[��
     */
    public OutputStream getOutputStream();
    
    /**
     * ���N�G�X�g�X�g���[���ɏ������ނ��߂̓��̓I�u�W�F�N�g��ݒ肷��B<p>
     *
     * @param input ���̓I�u�W�F�N�g
     */
    public void setObject(Object input);
    
    /**
     * ���N�G�X�g�X�g���[���ɏ������ނ��߂̓��̓I�u�W�F�N�g���擾����B<p>
     *
     * @return ���̓I�u�W�F�N�g
     */
    public Object getObject();
}