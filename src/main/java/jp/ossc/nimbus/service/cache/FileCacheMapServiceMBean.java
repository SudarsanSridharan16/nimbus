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
package jp.ossc.nimbus.service.cache;

import jp.ossc.nimbus.core.ServiceName;

/**
 * {@link FileCacheMapService}��MBean�C���^�t�F�[�X<p>
 * 
 * @author M.Takata
 * @see FileCacheMapService
 */
public interface FileCacheMapServiceMBean extends AbstractCacheMapServiceMBean{
    
    /**
     * �L���b�V�������I�u�W�F�N�g���V���A���C�Y���ăt�@�C���Ƃ��ďo�͂���ۂ̃t�@�C�����̃f�t�H���g�T�t�B�b�N�X�B<p>
     */
    public static final String DEFAULT_SUFFIX = ".obj";
    
    /**
     * �L���b�V�������I�u�W�F�N�g���V���A���C�Y���ăt�@�C���Ƃ��ďo�͂���ۂ̏o�͐�f�B���N�g����ݒ肷��B<p>
     * �o�͐�f�B���N�g�����w�肳��Ă��Ȃ��ꍇ�́AJVM�̃e���|�����f�B���N�g�����g�p����B�A���A{@link #setFileShared(boolean)}��true���ݒ肳��Ă���ꍇ�́A�o�͐�f�B���N�g����K���w�肵�Ȃ���΂Ȃ�Ȃ��B<br>
     *
     * @param path �o�̓f�B���N�g���p�X
     * @exception IllegalArgumentException �w�肳�ꂽ�p�X�̃f�B���N�g�������݂��Ȃ��ꍇ
     */
    public void setOutputDirectory(String path)
     throws IllegalArgumentException;
    
    /**
     * �L���b�V�������I�u�W�F�N�g���V���A���C�Y���ăt�@�C���Ƃ��ďo�͂���ۂ̏o�͐�f�B���N�g�����擾����B<p>
     *
     * @return �o�̓f�B���N�g���p�X
     */
    public String getOutputDirectory();
    
    /**
     * �L���b�V�������I�u�W�F�N�g���V���A���C�Y���ăt�@�C���Ƃ��ďo�͂����L���b�V���t�@�C���𑼂�{@link FileCacheMapService}�Ƌ��L���邩�ǂ�����ݒ肷��B<p>
     * true���w�肷��ƁA�L���b�V���t�@�C�������L����B���̂��߁A���̃T�[�r�X�ɂ���āA�L���b�V���t�@�C�����폜���ꂽ��A�ǉ����ꂽ�肷��̂ŁA�L���b�V�����Q�Ƃ���s�x�A�L���b�V���̍ŐV�����s���B<br>
     *
     * @param isShared �L���b�V���t�@�C�������L����ꍇtrue
     */
    public void setFileShared(boolean isShared);
    
    /**
     * �L���b�V�������I�u�W�F�N�g���V���A���C�Y���ăt�@�C���Ƃ��ďo�͂����L���b�V���t�@�C���𑼂�{@link FileCacheMapService}�Ƌ��L���邩�ǂ����𔻒肷��B<p>
     *
     * @return �L���b�V���t�@�C�������L����ꍇtrue
     */
    public boolean isFileShared();
    
    /**
     * �L���b�V�������I�u�W�F�N�g���V���A���C�Y���ăt�@�C���Ƃ��ďo�͂���ۂ̃t�@�C�����̃v���t�B�N�X��ݒ肷��B<p>
     * ���̏o�̓t�@�C���v���t�B�N�X���w�肳��Ă��Ȃ��ꍇ�́A�L���b�V������I�u�W�F�N�g��toString()���g�p�����B<br>
     *
     * @param prefix �o�̓t�@�C���v���t�B�N�X
     */
    public void setOutputPrefix(String prefix);
    
    /**
     * �L���b�V�������I�u�W�F�N�g���V���A���C�Y���ăt�@�C���Ƃ��ďo�͂���ۂ̃t�@�C�����̃v���t�B�N�X���擾����B<p>
     *
     * @return �o�̓t�@�C���v���t�B�N�X
     */
    public String getOutputPrefix();
    
    /**
     * �L���b�V�������I�u�W�F�N�g���V���A���C�Y���ăt�@�C���Ƃ��ďo�͂���ۂ̃t�@�C�����̃T�t�B�b�N�X��ݒ肷��B<p>
     * ���̏o�̓t�@�C���T�t�B�b�N�X���w�肳��Ă��Ȃ��ꍇ�́A".obj"���g�p�����B<br>
     *
     * @param suffix �o�̓t�@�C���T�t�B�b�N�X
     */
    public void setOutputSuffix(String suffix);
    
    /**
     * �L���b�V�������I�u�W�F�N�g���V���A���C�Y���ăt�@�C���Ƃ��ďo�͂���ۂ̃t�@�C�����̃v���t�B�N�X���擾����B<p>
     *
     * @return �o�̓t�@�C���v���t�B�N�X
     */
    public String getOutputSuffix();
    
    /**
     * �L���b�V�������I�u�W�F�N�g���V���A���C�Y���ăt�@�C���Ƃ��ďo�͂����L���b�V���t�@�C�����T�[�r�X�̊J�n���Ƀ��[�h���邩�ǂ�����ݒ肷��B<p>
     * �f�t�H���g�́Afalse�ŁA�T�[�r�X�̊J�n���Ƀ��[�h���Ȃ��B<br>
     *
     * @param isLoad �T�[�r�X�̊J�n���Ƀ��[�h����ꍇtrue
     */
    public void setLoadOnStart(boolean isLoad);
    
    /**
     * �L���b�V�������I�u�W�F�N�g���V���A���C�Y���ăt�@�C���Ƃ��ďo�͂����L���b�V���t�@�C�����T�[�r�X�̊J�n���Ƀ��[�h���邩�ǂ����𔻒肷��B<p>
     *
     * @return �T�[�r�X�̊J�n���Ƀ��[�h����ꍇtrue
     */
    public boolean isLoadOnStart();
    
    /**
     * �L���b�V�������I�u�W�F�N�g���V���A���C�Y���ăt�@�C���Ƃ��ďo�͂����L���b�V���t�@�C����JVM�̏I�����ɍ폜���邩�ǂ�����ݒ肷��B<p>
     * �f�t�H���g�́Atrue�ŁAJVM�I�����ɍ폜����B<br>
     *
     * @param isDeleteOnExit �폜����ꍇtrue
     */
    public void setDeleteOnExitWithJVM(boolean isDeleteOnExit);
    
    /**
     * �L���b�V�������I�u�W�F�N�g���V���A���C�Y���ăt�@�C���Ƃ��ďo�͂����L���b�V���t�@�C����JVM�̏I�����ɍ폜���邩�ǂ����𔻒肷��B<p>
     *
     * @return true�̏ꍇ�A�폜����
     */
    public boolean isDeleteOnExitWithJVM();
    
    /**
     * �L���b�V���t�@�C���̃��[�h�Ɏ��s�����ꍇ�ɍ폜���邩�ǂ�����ݒ肷��B<p>
     * �f�t�H���g�́Afalse�ŁA�폜���Ȃ��B<br>
     *
     * @param isDelete �폜����ꍇtrue
     */
    public void setDeleteOnLoadError(boolean isDelete);
    
    /**
     * �L���b�V���t�@�C���̃��[�h�Ɏ��s�����ꍇ�ɍ폜���邩�ǂ����𔻒肷��B<p>
     *
     * @return true�̏ꍇ�A�폜����
     */
    public boolean isDeleteOnLoadError();
    
    /**
     * �t�@�C���ɒ��񉻂���ۂɒ��񉻂��s��{@link jp.ossc.nimbus.service.io.Externalizer Externalizer}�T�[�r�X�̃T�[�r�X����ݒ肷��B<p>
     *
     * @param name Externalizer�T�[�r�X�̃T�[�r�X��
     */
    public void setExternalizerServiceName(ServiceName name);
    
    /**
     * �t�@�C���ɒ��񉻂���ۂɒ��񉻂��s��{@link jp.ossc.nimbus.service.io.Externalizer Externalizer}�T�[�r�X�̃T�[�r�X�����擾����B<p>
     *
     * @return Externalizer�T�[�r�X�̃T�[�r�X��
     */
    public ServiceName getExternalizerServiceName();
}
