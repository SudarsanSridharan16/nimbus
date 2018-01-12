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
package jp.ossc.nimbus.util.converter;

import java.io.File;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 * HttpServletRequest��FileItem�R���o�[�^�B<p>
 * �ϊ���I�u�W�F�N�g��FileItem��List�ƂȂ�B<br>
 *
 * @author M.Ishida
 */
public class HttpServletRequestFileConverter implements Converter {

    // HttpServletRequest����File�ɕϊ�����ۂ̈ꎞ�t�@�C������������ŊǗ��ł���f�[�^�T�C�Y�̏���B
    private int sizeThreshold = DiskFileItemFactory.DEFAULT_SIZE_THRESHOLD;
    // �f�B�X�N��Ɉꎞ�I�ɕۑ�����ۂ̃f�B���N�g���B
    private String repositoryPath;
    // Request�̃f�[�^�T�C�Y�̏���B
    private long requestSizeThreshold = -1L;
    private String headerEncoding;

    /**
     * ��������ŊǗ�����f�[�^�T�C�Y�i����j��ݒ肷��B
     * <p>
     * �f�t�H���g��{@link DiskFileItemFactory#DEFAULT_SIZE_THRESHOLD}�B
     * ���̒l�𒴂���ƁA�f�B�X�N��Ɉꎞ�I�ɕۑ������B<br>
     * �����ŕۑ����ꂽ�t�@�C���͓K���ȃ^�C�~���O�ŏ���ɏ������B<br>
     *
     * @see DiskFileItemFactory#setSizeThreshold(int)
     * @param size ����T�C�Y
     */
    public void setSizeThreshold(int size) {
        sizeThreshold = size;
    }

    /**
     * ��������ŊǗ�����f�[�^�T�C�Y�i����j���擾����B
     * <p>
     *
     * @return ����T�C�Y
     */
    public int getSizeThreshold() {
        return sizeThreshold;
    }

    /**
     * �f�B�X�N��Ɉꎞ�I�ɕۑ�����ۂ̃f�B���N�g�����w�肷��B
     * <p>
     *
     * @see DiskFileItemFactory#setRepository(File)
     * @param path �f�B�X�N��Ɉꎞ�I�ɕۑ�����ۂ̃f�B���N�g���p�X
     */
    public void setRepositoryPath(String path) {
        repositoryPath = path;
    }

    /**
     * �f�B�X�N��Ɉꎞ�I�ɕۑ�����ۂ̃f�B���N�g�����擾����B
     * <p>
     *
     * @return �f�B�X�N��Ɉꎞ�I�ɕۑ�����ۂ̃f�B���N�g���p�X
     */
    public String getRepositoryPath() {
        return repositoryPath;
    }

    /**
     * HttpServletRequest��ContentLength�̍ő�l��ݒ肷��B
     * <p>
     *
     * @param size ContentLength�̍ő�l
     */
    public void setRequestSizeThreshold(long size) {
        requestSizeThreshold = size;
    }

    /**
     * HttpServletRequest��ContentLength�̍ő�l���擾����B
     * <p>
     *
     * @return ContentLength�̍ő�l
     */
    public long getRequestSizeThreshold() {
        return requestSizeThreshold;
    }

    /**
     * HTTP�w�b�_�̕����R�[�h��ݒ肷��B<p>
     *
     * @param encoding �����R�[�h
     */
    public void setHeaderEncoding(String encoding){
        headerEncoding = encoding;
    }
    
    /**
     * HTTP�w�b�_�̕����R�[�h���擾����B<p>
     *
     * @return �����R�[�h
     */
    public String getHeaderEncoding(){
        return headerEncoding;
    }
    
    public Object convert(Object obj) throws ConvertException {
        if (!(obj instanceof HttpServletRequest)) {
            throw new ConvertException("Parameter is not instancce of HttpServletRequest.");
        }
        DiskFileItemFactory factory = new DiskFileItemFactory();
        if (repositoryPath != null) {
            factory.setRepository(new File(repositoryPath));
        }
        factory.setSizeThreshold(sizeThreshold);
        ServletFileUpload upload = new ServletFileUpload(factory);
        upload.setSizeMax(requestSizeThreshold);
        if(headerEncoding != null){
            upload.setHeaderEncoding(headerEncoding);
        }
        try {
            return upload.parseRequest((HttpServletRequest) obj);
        } catch (FileUploadException e) {
            throw new ConvertException(e);
        }
    }

}
