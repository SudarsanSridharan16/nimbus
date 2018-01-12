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
package jp.ossc.nimbus.service.crypt;

import jp.ossc.nimbus.core.*;

import java.security.*;
import java.security.spec.*;

/**
 * {@link CipherCryptService}��MBean�C���^�t�F�[�X<p>
 * 
 * @author M.Takata
 * @see CipherCryptService
 */
public interface CipherCryptServiceMBean extends ServiceBaseMBean{
    
    /**
     * �f�t�H���g�̕ϊ����B<p>
     */
    public static final String DEFAULT_TRANSFORMATION = "DES/ECB/PKCS5Padding";
    
    /**
     * �f�t�H���g�̕����G���R�[�f�B���O�B<p>
     */
    public static final String DEFAULT_ENCODING = "ISO_8859-1";
    
    /**
     * �f�t�H���g�̃n�b�V���A���S���Y�����B<p>
     */
    public static final String DEFAULT_HASH_ALGORITHM = "MD5";
    
    /**
     * �Í���/�������Ɏg�p����ϊ�����ݒ肷��B<p>
     * �f�t�H���g�́A{@link #DEFAULT_TRANSFORMATION}�B<br>
     *
     * @param trans �ϊ���
     */
    public void setTransformation(String trans);
    
    /**
     * �Í���/�������Ɏg�p����ϊ������擾����B<p>
     *
     * @return �ϊ���
     */
    public String getTransformation();
    
    /**
     * ����ݒ肷��B<p>
     *
     * @param k ��
     */
    public void setKey(Key k);
    
    /**
     * �����擾����B<p>
     *
     * @return ��
     */
    public Key getKey();
    
    /**
     * ���X�g�A�̃p�X��ݒ肷��B<p>
     *
     * @param path �p�X
     */
    public void setStorePath(String path);
    
    /**
     * ���X�g�A�̃p�X���擾����B<p>
     *
     * @return �p�X
     */
    public String getStorePath();
    
    /**
     * ���X�g�A�̎�ʂ�ݒ肷��B<p>
     *
     * @param type ���
     */
    public void setStoreType(String type);
    
    /**
     * ���X�g�A�̎�ʂ��擾����B<p>
     *
     * @return ���
     */
    public String getStoreType();
    
    /**
     * ���X�g�A�v���o�C�_�̖��O��ݒ肷��B<p>
     *
     * @param name ���X�g�A�v���o�C�_�̖��O
     */
    public void setStoreProviderName(String name);
    
    /**
     * ���X�g�A�v���o�C�_�̖��O���擾����B<p>
     *
     * @return ���X�g�A�v���o�C�_�̖��O
     */
    public String getStoreProviderName();
    
    /**
     * ���X�g�A�v���o�C�_��ݒ肷��B<p>
     *
     * @param provider ���X�g�A�v���o�C�_
     */
    public void setStoreProvider(Provider provider);
    
    /**
     * ���X�g�A�v���o�C�_���擾����B<p>
     *
     * @return ���X�g�A�v���o�C�_
     */
    public Provider getStoreProvider();
    
    /**
     * ���X�g�A�̃p�X���[�h��ݒ肷��B<p>
     *
     * @param password ���X�g�A�̃p�X���[�h
     */
    public void setStorePassword(String password);
    
    /**
     * ���X�g�A�̃p�X���[�h���擾����B<p>
     *
     * @return ���X�g�A�̃p�X���[�h
     */
    public String getStorePassword();
    
    /**
     * ���̃G�C���A�X��ݒ肷��B<p>
     *
     * @param alias ���̃G�C���A�X
     */
    public void setKeyAlias(String alias);
    
    /**
     * ���̃G�C���A�X���擾����B<p>
     *
     * @return ���̃G�C���A�X
     */
    public String getKeyAlias();
    
    /**
     * ���̃p�X���[�h��ݒ肷��B<p>
     *
     * @param password ���̃p�X���[�h
     */
    public void setKeyPassword(String password);
    
    /**
     * ���̃p�X���[�h���擾����B<p>
     *
     * @return ���̃p�X���[�h
     */
    public String getKeyPassword();
    
    /**
     * javax.crypto.Cipher���擾���邽�߂̃v���o�C�_��ݒ肷��B<p>
     * ���̑�����ݒ肵�Ȃ��ꍇ�́A�f�t�H���g�̃v���o�C�_���g�p����܂��B<br>
     *
     * @param p �v���o�C�_
     */
    public void setCipherProvider(Provider p);
    
    /**
     * javax.crypto.Cipher���擾���邽�߂̃v���o�C�_���擾����B<p>
     *
     * @return �v���o�C�_
     */
    public Provider getCipherProvider();
    
    /**
     * javax.crypto.Cipher���擾���邽�߂̃v���o�C�_����ݒ肷��B<p>
     * ���̑�����ݒ肵�Ȃ��ꍇ�́A�f�t�H���g�̃v���o�C�_���g�p����܂��B<br>
     *
     * @param name �v���o�C�_��
     */
    public void setCipherProviderName(String name);
    
    /**
     * javax.crypto.Cipher���擾���邽�߂̃v���o�C�_�����擾����B<p>
     *
     * @return �v���o�C�_��
     */
    public String getCipherProviderName();
    
    /**
     * javax.crypto.Cipher�̏������Ɏg�p����A���S���Y���p�����[�^��ݒ肷��B<p>
     * ���̑�����ݒ肵�Ȃ��ꍇ�́A�f�t�H���g�̃A���S���Y���p�����[�^���g�p����܂��B<br>
     *
     * @param params �A���S���Y���p�����[�^
     */
    public void setAlgorithmParameters(AlgorithmParameters params);
    
    /**
     * javax.crypto.Cipher�̏������Ɏg�p����A���S���Y���p�����[�^���擾����B<p>
     *
     * @return �A���S���Y���p�����[�^
     */
    public AlgorithmParameters getAlgorithmParameters();
    
    /**
     * javax.crypto.Cipher�̏������Ɏg�p����A���S���Y���p�����[�^��ݒ肷��B<p>
     * ���̑�����ݒ肵�Ȃ��ꍇ�́A�f�t�H���g�̃A���S���Y���p�����[�^���g�p����܂��B<br>
     *
     * @param params �A���S���Y���p�����[�^
     */
    public void setAlgorithmParameterSpec(AlgorithmParameterSpec params);
    
    /**
     * javax.crypto.Cipher�̏������Ɏg�p����A���S���Y���p�����[�^���擾����B<p>
     *
     * @return �A���S���Y���p�����[�^
     */
    public AlgorithmParameterSpec getAlgorithmParameterSpec();
    
    /**
     * javax.crypto.Cipher�̏������Ɏg�p���闐����������ݒ肷��B<p>
     * ���̑�����ݒ肵�Ȃ��ꍇ�́A�f�t�H���g�̗������������g�p����܂��B<br>
     *
     * @param random ����������
     */
    public void setSecureRandom(SecureRandom random);
    
    /**
     * javax.crypto.Cipher�̏������Ɏg�p���闐�����������擾����B<p>
     *
     * @return ����������
     */
    public SecureRandom getSecureRandom();
    
    /**
     * �����G���R�[�f�B���O��ݒ肷��B<p>
     * �Í�������ۂɕ����񂩂�o�C�g�z��ւ̕ϊ��Ɏg�p����B�܂��A����������ۂɃo�C�g�z�񂩂當����ւ̕ϊ��Ɏg�p����B<br>
     * �܂��A�n�b�V������ۂɕ����񂩂�o�C�g�z��ւ̕ϊ��Ɏg�p����B<br>
     * �f�t�H���g�́A{@link #DEFAULT_ENCODING}�B<br>
     *
     * @param enc �����G���R�[�f�B���O
     */
    public void setEncoding(String enc);
    
    /**
     * �����G���R�[�f�B���O���擾����B<p>
     *
     * @return �����G���R�[�f�B���O
     */
    public String getEncoding();
    
    /**
     * �n�b�V���̃A���S���Y������ݒ肷��B<p>
     * �f�t�H���g�́A{@link #DEFAULT_HASH_ALGORITHM}�B<br>
     * 
     * @param algorithm �n�b�V���̃A���S���Y����
     */
    public void setHashAlgorithm(String algorithm);
    
    /**
     * �n�b�V���̃A���S���Y�������擾����B<p>
     * 
     * @return �n�b�V���̃A���S���Y����
     */
    public String getHashAlgorithm();
    
    /**
     * javax.crypto.MessageDigest���擾���邽�߂̃v���o�C�_��ݒ肷��B<p>
     * ���̑�����ݒ肵�Ȃ��ꍇ�́A�f�t�H���g�̃v���o�C�_���g�p����܂��B<br>
     *
     * @param p �v���o�C�_
     */
    public void setMessageDigestProvider(Provider p);
    
    /**
     * javax.crypto.MessageDigest���擾���邽�߂̃v���o�C�_���擾����B<p>
     *
     * @return �v���o�C�_
     */
    public Provider getMessageDigestProvider();
    
    /**
     * javax.crypto.MessageDigest���擾���邽�߂̃v���o�C�_����ݒ肷��B<p>
     * ���̑�����ݒ肵�Ȃ��ꍇ�́A�f�t�H���g�̃v���o�C�_���g�p����܂��B<br>
     *
     * @param name �v���o�C�_��
     */
    public void setMessageDigestProviderName(String name);
    
    /**
     * javax.crypto.MessageDigest���擾���邽�߂̃v���o�C�_�����擾����B<p>
     *
     * @return �v���o�C�_��
     */
    public String getMessageDigestProviderName();
    
    /**
     * �ϊ���ʂ�ݒ肷��B<p>
     * {@link jp.ossc.nimbus.util.converter.ReversibleConverter ReversibleConverter}�Ƃ��Ďg�p����ꍇ�ɐݒ肷��B<br>
     *
     * @param type �ϊ����
     * @see jp.ossc.nimbus.util.converter.ReversibleConverter#POSITIVE_CONVERT �Í���
     * @see jp.ossc.nimbus.util.converter.ReversibleConverter#REVERSE_CONVERT ������
     */
    public void setConvertType(int type);
    
    /**
     * �ϊ���ʂ��擾����B<p>
     *
     * @return �ϊ����
     */
    public int getConvertType();
}
