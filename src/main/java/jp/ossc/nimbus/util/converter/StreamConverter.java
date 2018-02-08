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

import java.io.InputStream;

/**
 * �X�g���[���R���o�[�^�̃C���^�t�F�[�X�B<p>
 * 
 * @author M.Takata
 */
public interface StreamConverter extends ReversibleConverter{
    
    /**
     * �I�u�W�F�N�g����X�g���[���ւ̕ϊ���\���ϊ���ʒ萔�B<p>
     */
    public static final int OBJECT_TO_STREAM = POSITIVE_CONVERT;
    
    /**
     * �X�g���[������I�u�W�F�N�g�ւ̕ϊ���\���ϊ���ʒ萔�B<p>
     */
    public static final int STREAM_TO_OBJECT = REVERSE_CONVERT;
    
    /**
     * �I�u�W�F�N�g����X�g���[���֕ϊ�����B<p>
     *
     * @param obj �I�u�W�F�N�g
     * @return �ϊ����ʂ�ǂݎ����̓X�g���[��
     * @exception ConvertException �ϊ��Ɏ��s�����ꍇ
     */
    public InputStream convertToStream(Object obj) throws ConvertException;
    
    /**
     * �X�g���[������I�u�W�F�N�g�֕ϊ�����B<p>
     *
     * @param is ���̓X�g���[��
     * @return �I�u�W�F�N�g
     * @exception ConvertException �ϊ��Ɏ��s�����ꍇ
     */
    public Object convertToObject(InputStream is) throws ConvertException;
}