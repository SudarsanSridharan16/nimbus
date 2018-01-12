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
 * HTTP���X�|���X�B<p>
 *
 * @author M.Takata
 */
public interface HttpResponse{
    
    /**
     * ���X�|���X��HTTP�w�b�_���̏W�����擾����B<p>
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
     * ���X�|���X�̕����G���R�[�f�B���O���擾����B<p>
     *
     * @return �����G���R�[�f�B���O
     */
    public String getCharacterEncoding();
    
    /**
     * ���X�|���X��HTTP�X�e�[�^�X���擾����B<p>
     *
     * @return HTTP�X�e�[�^�X
     */
    public int getStatusCode();
    
    /**
     * ���X�|���X��HTTP�X�e�[�^�X���b�Z�[�W���擾����B<p>
     *
     * @return HTTP�X�e�[�^�X���b�Z�[�W
     */
    public String getStatusMessage();
    
    /**
     * ���X�|���X�X�g���[�����擾����B<p>
     *
     * @return ���X�|���X�X�g���[��
     */
    public InputStream getInputStream() throws IOException;
    
    /**
     * ���X�|���X�X�g���[������ǂݍ��񂾉����I�u�W�F�N�g���擾����B<p>
     *
     * @return �����I�u�W�F�N�g
     */
    public Object getObject();
    
    /**
     * ���X�|���X�X�g���[������ǂݍ��񂾉����I�u�W�F�N�g���擾����B<p>
     *
     * @param bind �����I�u�W�F�N�g
     * @return �����I�u�W�F�N�g
     */
    public Object getObject(Object bind);
    
    /**
     * �����I�ɐڑ���ؒf����B<p>
     */
    public void close();
}