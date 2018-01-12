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

/**
 * �X�g���[���𕶎���Ƃ��ĉ��߂��ăI�u�W�F�N�g�Ƃ̑��ݕϊ����s���R���o�[�^�̃C���^�t�F�[�X�B<p>
 * 
 * @author M.Takata
 */
public interface StreamStringConverter extends StreamConverter{
    
    /**
     * �I�u�W�F�N�g����X�g���[���֕ϊ�����ۂ̕����G���R�[�f�B���O��ݒ肷��B<p>
     *
     * @param encoding �I�u�W�F�N�g����X�g���[���֕ϊ�����ۂ̕����G���R�[�f�B���O
     */
    public void setCharacterEncodingToStream(String encoding);
    
    /**
     * �I�u�W�F�N�g����X�g���[���֕ϊ�����ۂ̕����G���R�[�f�B���O���擾����B<p>
     *
     * @return �I�u�W�F�N�g����X�g���[���֕ϊ�����ۂ̕����G���R�[�f�B���O
     */
    public String getCharacterEncodingToStream();
    
    /**
     * �X�g���[������I�u�W�F�N�g�֕ϊ�����ۂ̕����G���R�[�f�B���O��ݒ肷��B<p>
     *
     * @param encoding �X�g���[������I�u�W�F�N�g�֕ϊ�����ۂ̕����G���R�[�f�B���O
     */
    public void setCharacterEncodingToObject(String encoding);
    
    /**
     * �X�g���[������I�u�W�F�N�g�֕ϊ�����ۂ̕����G���R�[�f�B���O���擾����B<p>
     *
     * @return �X�g���[������I�u�W�F�N�g�֕ϊ�����ۂ̕����G���R�[�f�B���O
     */
    public String getCharacterEncodingToObject();
    
    /**
     * �I�u�W�F�N�g����X�g���[���֕ϊ�����ۂ̕����G���R�[�f�B���O��ݒ肵���������쐬����B<p>
     *
     * @param encoding �I�u�W�F�N�g����X�g���[���֕ϊ�����ۂ̕����G���R�[�f�B���O
     * @return �����B�A���A�w�肵��encoding���ݒ肳��Ă���G���R�[�f�B���O�Ɠ������ꍇ�ɂ́A�������Ȃ��B
     */
    public StreamStringConverter cloneCharacterEncodingToStream(String encoding);
    
    /**
     * �X�g���[������I�u�W�F�N�g�֕ϊ�����ۂ̕����G���R�[�f�B���O��ݒ肵���������쐬����B�B<p>
     *
     * @param encoding �X�g���[������I�u�W�F�N�g�֕ϊ�����ۂ̕����G���R�[�f�B���O
     * @return �����B�A���A�w�肵��encoding���ݒ肳��Ă���G���R�[�f�B���O�Ɠ������ꍇ�ɂ́A�������Ȃ��B
     */
    public StreamStringConverter cloneCharacterEncodingToObject(String encoding);
}
