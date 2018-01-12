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
package jp.ossc.nimbus.service.resource;

import jp.ossc.nimbus.core.*;

/**
 * {@link PooledResourceFactoryService}��MBean�C���^�t�F�[�X<p>
 * 
 * @author M.Takata
 * @see PooledResourceFactoryService
 */
public interface PooledResourceFactoryServiceMBean extends ServiceBaseMBean{
    
    /**
     * �v�[�����g���s���ꂽ���̏����̎�ʂŁA�V���ȃI�u�W�F�N�g�����p�ł���܂ł܂��� �ő�ҋ@���� �ɒB����܂ŁA�v�[�����o���v�����ҋ@�����B<p>
     */
    public static final String WHEN_EXHAUSTED_BLOCK = "WHEN_EXHAUSTED_BLOCK";
    
    /**
     * �v�[�����g���s���ꂽ���̏����̎�ʂŁA�v�[�����o���v�������s���A{@link java.util.NoSuchElementException}�𓊂���B<p>
     */
    public static final String WHEN_EXHAUSTED_FAIL = "WHEN_EXHAUSTED_FAIL";
    
    /**
     * �v�[�����g���s���ꂽ���̏����̎�ʂŁA�V���ȃI�u�W�F�N�g�����������B<p>
     */
    public static final String WHEN_EXHAUSTED_GROW = "WHEN_EXHAUSTED_GROW";
    
    /**
     * �v�[������I�u�W�F�N�g�𐶐�����{@link org.apache.commons.pool.PoolableObjectFactory PoolableObjectFactory}�C���^�t�F�[�X�̎����N���X���w�肷��B<p>
     *
     * @param clazz {@link org.apache.commons.pool.PoolableObjectFactory PoolableObjectFactory}�C���^�t�F�[�X�̎����N���X
     */
    public void setPoolableObjectFactoryClass(Class clazz);
    
    /**
     * �v�[������I�u�W�F�N�g�𐶐�����{@link org.apache.commons.pool.PoolableObjectFactory PoolableObjectFactory}�C���^�t�F�[�X�̎����N���X���擾����B<p>
     *
     * @return {@link org.apache.commons.pool.PoolableObjectFactory PoolableObjectFactory}�C���^�t�F�[�X�̎����N���X
     */
    public Class getPoolableObjectFactoryClass();
    
    /**
     * �v�[������I�u�W�F�N�g�𐶐�����{@link org.apache.commons.pool.PoolableObjectFactory PoolableObjectFactory}�C���^�t�F�[�X�̎����T�[�r�X�����w�肷��B<p>
     *
     * @param name {@link org.apache.commons.pool.PoolableObjectFactory PoolableObjectFactory}�C���^�t�F�[�X�̎����T�[�r�X��
     */
    public void setPoolableObjectFactoryServiceName(ServiceName name);
    
    /**
     * �v�[������I�u�W�F�N�g�𐶐�����{@link org.apache.commons.pool.PoolableObjectFactory PoolableObjectFactory}�C���^�t�F�[�X�̎����T�[�r�X�����擾����B<p>
     *
     * @return {@link org.apache.commons.pool.PoolableObjectFactory PoolableObjectFactory}�C���^�t�F�[�X�̎����T�[�r�X��
     */
    public ServiceName getPoolableObjectFactoryServiceName();
    
    /**
     * �����Ƀv�[��������o�����Ƃ̂ł���I�u�W�F�N�g�̍ő吔��ݒ肷��B<p>
     * �f�t�H���g�́A{@link org.apache.commons.pool.impl.GenericObjectPool#DEFAULT_MAX_ACTIVE DEFAULT_MAX_ACTIVE}�B<br>
     *
     * @param max �����Ƀv�[��������o�����Ƃ̂ł���I�u�W�F�N�g�̍ő吔
     */
    public void setMaxActive(int max);
    
    /**
     * �����Ƀv�[��������o�����Ƃ̂ł���I�u�W�F�N�g�̍ő吔���擾����B<p>
     *
     * @return �����Ƀv�[��������o�����Ƃ̂ł���I�u�W�F�N�g�̍ő吔
     */
    public int getMaxActive();
    
    /**
     * �v�[�����ɕێ��ł��関�g�p�̃I�u�W�F�N�g�̍ő吔��ݒ肷��B<p>
     * �f�t�H���g�́A{@link org.apache.commons.pool.impl.GenericObjectPool#DEFAULT_MAX_IDLE DEFAULT_MAX_IDLE}�B<br>
     *
     * @param max �v�[�����ɕێ��ł��関�g�p�̃I�u�W�F�N�g�̍ő吔
     */
    public void setMaxIdle(int max);
    
    /**
     * �v�[�����ɕێ��ł��関�g�p�̃I�u�W�F�N�g�̍ő吔���擾����B<p>
     *
     * @return �v�[�����ɕێ��ł��関�g�p�̃I�u�W�F�N�g�̍ő吔
     */
    public int getMaxIdle();
    
    /**
     * �v�[�����ɕێ�����関�g�p�̃I�u�W�F�N�g�̍ŏ�����ݒ肷��B<p>
     * ���̒l�ɒB���Ȃ��ꍇ�ɂ͔r�������X���b�h�ɂĐV���ȃI�u�W�F�N�g�̐������s���B<br>
     * �f�t�H���g�́A{@link org.apache.commons.pool.impl.GenericObjectPool#DEFAULT_MIN_IDLE DEFAULT_MIN_IDLE}�B<br>
     *
     * @param min �v�[�����ɕێ�����関�g�p�̃I�u�W�F�N�g�̍ŏ���
     */
    public void setMinIdle(int min);
    
    /**
     * �v�[�����ɕێ�����関�g�p�̃I�u�W�F�N�g�̍ŏ������擾����B<p>
     *
     * @return �v�[�����ɕێ�����関�g�p�̃I�u�W�F�N�g�̍ŏ���
     */
    public int getMinIdle();
    
    /**
     * �v�[�����g���s����Ă��āA{@link #setWhenExhaustedAction(String) setWhenExhaustedAction(WHEN_EXHAUSTED_BLOCK)}���ݒ肳��Ă���ꍇ�� {@link PooledResourceFactoryService#makeResource(String) makeResource(String)}���\�b�h����O�𓊂���܂ł̍Œ��ҋ@����(�~���b)��ݒ肷��B<p>
     * 0��菬���Ȓl���ݒ肳�ꂽ�ꍇ�AmakeResource(String)���\�b�h�͖������ɑҋ@����B<br>
     * �f�t�H���g�́A{@link org.apache.commons.pool.impl.GenericObjectPool#DEFAULT_MAX_WAIT DEFAULT_MAX_WAIT}�B<br>
     *
     * @param maxMillis �v�[�����g���s���ꂽ�ꍇ�̎擾�ł̍Œ��ҋ@����(�~���b)
     */
    public void setMaxWaitTime(long maxMillis);
    
    /**
     * �v�[�����g���s����Ă��āA{@link #setWhenExhaustedAction(String) setWhenExhaustedAction(WHEN_EXHAUSTED_BLOCK)}���ݒ肳��Ă���ꍇ�� {@link PooledResourceFactoryService#makeResource(String) makeResource(String)}���\�b�h����O�𓊂���܂ł̍Œ��ҋ@����(�~���b)���擾����B<p>
     *
     * @return �v�[�����g���s���ꂽ�ꍇ�̎擾�ł̍Œ��ҋ@����(�~���b)
     */
    public long getMaxWaitTime();
    
    /**
     * �I�u�W�F�N�g���v�[�����ɖ��g�p��Ԃł����鎞�Ԃ̍ŏ��l��ݒ肷��B<p>
     * ���g�p��Ԃł��鎞�Ԃ��A���̒l�ɒB����Ɣr�������̑ΏۂƂȂ�B<br>
     * �f�t�H���g�́A{@link org.apache.commons.pool.impl.GenericObjectPool#DEFAULT_MIN_EVICTABLE_IDLE_TIME_MILLIS DEFAULT_MIN_EVICTABLE_IDLE_TIME_MILLIS}�B<br>
     *
     * @param minMillis �I�u�W�F�N�g���v�[�����ɖ��g�p��Ԃł����鎞�Ԃ̍ŏ��l
     */
    public void setMinEvictableIdleTime(long minMillis);
    
    /**
     * �I�u�W�F�N�g���v�[�����ɖ��g�p��Ԃł����鎞�Ԃ̍ŏ��l���擾����B<p>
     *
     * @return �I�u�W�F�N�g���v�[�����ɖ��g�p��Ԃł����鎞�Ԃ̍ŏ��l
     */
    public long getMinEvictableIdleTime();
    
    /**
     * 1�x�̃I�u�W�F�N�g�r�������Ŕr���X���b�h�Ƀ`�F�b�N�����I�u�W�F�N�g�̐���ݒ肷��B<p> 
     * ���̒l���ݒ肳�ꂽ�ꍇ�Aceil(getNumIdle())/abs(getNumTestsPerEvictionRun()) ��̃`�F�b�N�����{����B�Ⴆ�� -n ���ݒ肳�ꂽ�ꍇ�ɂ́A1/n �̖��g�p�I�u�W�F�N�g��1�x�̃I�u�W�F�N�g�r�������Ń`�F�b�N�����B<br>
     * �f�t�H���g�́A{@link org.apache.commons.pool.impl.GenericObjectPool#DEFAULT_NUM_TESTS_PER_EVICTION_RUN DEFAULT_NUM_TESTS_PER_EVICTION_RUN}�B<br>
     *
     * @param num 1�x�̃I�u�W�F�N�g�r�������Ŕr���X���b�h�Ƀ`�F�b�N�����I�u�W�F�N�g�̐�
     */
    public void setNumTestsPerEvictionRun(int num);
    
    /**
     * 1�x�̃I�u�W�F�N�g�r�������Ŕr���X���b�h�Ƀ`�F�b�N�����I�u�W�F�N�g�̐����擾����B<p> 
     *
     * @return 1�x�̃I�u�W�F�N�g�r�������Ŕr���X���b�h�Ƀ`�F�b�N�����I�u�W�F�N�g�̐�
     */
    public int getNumTestsPerEvictionRun();
    
    /**
     * �v�[��������o�����O�� �v�[�����̃I�u�W�F�N�g���L�����ǂ����̊m�F���s�����ǂ�����ݒ肷��B<p>
     * �L���łȂ��Ɣ��f���ꂽ�ꍇ�A�I�u�W�F�N�g�̓v�[������j������A���̃I�u�W�F�N�g�����o�����B<br>
     * �f�t�H���g�́A{@link org.apache.commons.pool.impl.GenericObjectPool#DEFAULT_TEST_ON_BORROW DEFAULT_TEST_ON_BORROW}�B<br>
     *
     * @param isTest true�̏ꍇ�A�L�����ǂ����̊m�F���s��
     */
    public void setTestOnBorrow(boolean isTest);
    
    /**
     * �v�[��������o�����O�� �v�[�����̃I�u�W�F�N�g���L�����ǂ����̊m�F���s�����ǂ����𔻒肷��B<p>
     *
     * @return true�̏ꍇ�A�L�����ǂ����̊m�F���s��
     */
    public boolean isTestOnBorrow();
    
    /**
     * �v�[���ɖ߂��O�� �I�u�W�F�N�g���L�����ǂ����̊m�F���s�����ǂ�����ݒ肷��B<p>
     * �L���łȂ��Ɣ��f���ꂽ�ꍇ�A�I�u�W�F�N�g�̓v�[������j�������B<br>
     * �f�t�H���g�́A{@link org.apache.commons.pool.impl.GenericObjectPool#DEFAULT_TEST_ON_RETURN DEFAULT_TEST_ON_RETURN}�B<br>
     *
     * @param isTest true�̏ꍇ�A�L�����ǂ����̊m�F���s��
     */
    public void setTestOnReturn(boolean isTest);
    
    /**
     * �v�[���ɖ߂��O�� �I�u�W�F�N�g���L�����ǂ����̊m�F���s�����ǂ����𔻒肷��B<p>
     *
     * @return true�̏ꍇ�A�L�����ǂ����̊m�F���s��
     */
    public boolean isTestOnReturn();
    
    /**
     * �I�u�W�F�N�g�r���������Ƀv�[�����̃I�u�W�F�N�g���L�����ǂ����̊m�F���s�����ǂ�����ݒ肷��B<p>
     * �L���łȂ��Ɣ��f���ꂽ�I�u�W�F�N�g�̓v�[������j�������B<br>
     * �f�t�H���g�́A{@link org.apache.commons.pool.impl.GenericObjectPool#DEFAULT_TEST_WHILE_IDLE DEFAULT_TEST_WHILE_IDLE}�B<br>
     *
     * @param isTest true�̏ꍇ�A�L�����ǂ����̊m�F���s��
     */
    public void setTestWhileIdle(boolean isTest);
    
    /**
     * �I�u�W�F�N�g�r���������Ƀv�[�����̃I�u�W�F�N�g���L�����ǂ����̊m�F���s�����ǂ����𔻒肷��B<p>
     *
     * @return true�̏ꍇ�A�L�����ǂ����̊m�F���s��
     */
    public boolean isTestWhileIdle();
    
    /**
     * ���g�p�I�u�W�F�N�g�r�����������̎��s�܂ł̊ԃX���[�v���鎞��(�~���b)��ݒ肷��B<p>
     * ���̒l���ݒ肳�ꂽ�ꍇ�A�r���X���b�h�͋N�����Ȃ��B<br>
     * �f�t�H���g�́A{@link org.apache.commons.pool.impl.GenericObjectPool#DEFAULT_TIME_BETWEEN_EVICTION_RUNS_MILLIS DEFAULT_TIME_BETWEEN_EVICTION_RUNS_MILLIS}�B<br>
     *
     * @param millis ���g�p�I�u�W�F�N�g�r�����������̎��s�܂ł̊ԃX���[�v���鎞��(�~���b)
     */
    public void setTimeBetweenEvictionRuns(long millis);
    
    /**
     * ���g�p�I�u�W�F�N�g�r�����������̎��s�܂ł̊ԃX���[�v���鎞��(�~���b)���擾����B<p>
     *
     * @return ���g�p�I�u�W�F�N�g�r�����������̎��s�܂ł̊ԃX���[�v���鎞��(�~���b)
     */
    public long getTimeBetweenEvictionRuns();
    
    /**
     * �v�[�����g���s����Ă���ꍇ(���o�����Ƃ̂ł���I�u�W�F�N�g���ő吔�ɒB�����ꍇ)�ɍs�������̎�ʂ�ݒ肷��B<p>
     *
     * @param action �v�[�����g���s����Ă���ꍇ(���o�����Ƃ̂ł���I�u�W�F�N�g���ő吔�ɒB�����ꍇ)�ɍs�������̎��
     * @exception IllegalArgumentException �������s���Ȏ�ʂ̏ꍇ
     * @see #WHEN_EXHAUSTED_BLOCK
     * @see #WHEN_EXHAUSTED_FAIL
     * @see #WHEN_EXHAUSTED_GROW
     */
    public void setWhenExhaustedAction(String action)
     throws IllegalArgumentException;
    
    /**
     * �v�[�����g���s����Ă���ꍇ(���o�����Ƃ̂ł���I�u�W�F�N�g���ő吔�ɒB�����ꍇ)�ɍs�������̎�ʂ��擾����B<p>
     *
     * @return �v�[�����g���s����Ă���ꍇ(���o�����Ƃ̂ł���I�u�W�F�N�g���ő吔�ɒB�����ꍇ)�ɍs�������̎��
     */
    public String getWhenExhaustedAction();
    
    /**
     * �v�[�����ɂ���g�p����Ă��Ȃ��I�u�W�F�N�g���폜���A�֘A���郊�\�[�X���J������B<p>
     *
     * @exception Exception ���炩�̗��R�Ŏ��s�����ꍇ
     */
    public void clear() throws Exception;
    
    /**
     * ���݃v�[��������o����Ďg�p���̃I�u�W�F�N�g�̐����擾����B<p>
     *
     * @return ���݃v�[��������o����Ďg�p���̃I�u�W�F�N�g�̐�
     */
    public int getActiveNum();
    
    /**
     * ���݃v�[������Ă��āA���g�p�̃I�u�W�F�N�g�̐����擾����B<p>
     *
     * @return ���݃v�[������Ă��āA���g�p�̃I�u�W�F�N�g�̐�
     */
    public int getIdleNum();
}
