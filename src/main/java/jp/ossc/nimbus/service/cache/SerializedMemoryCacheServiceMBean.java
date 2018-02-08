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
 * {@link SerializedMemoryCacheService}��MBean�C���^�t�F�[�X<p>
 * 
 * @author M.Takata
 * @see SerializedMemoryCacheService
 */
public interface SerializedMemoryCacheServiceMBean
 extends AbstractCacheServiceMBean{
    
    /**
     * ����/�񒼗񉻂��s��{@link jp.ossc.nimbus.service.io.Externalizer}�T�[�r�X�̃T�[�r�X����ݒ肷��B<p>
     *
     * @param name Externalizer�T�[�r�X�̃T�[�r�X��
     */
    public void setExternalizerServiceName(ServiceName name);
    
    /**
     * ����/�񒼗񉻂��s��{@link jp.ossc.nimbus.service.io.Externalizer}�T�[�r�X�̃T�[�r�X�����擾����B<p>
     *
     * @return Externalizer�T�[�r�X�̃T�[�r�X��
     */
    public ServiceName getExternalizerServiceName();
    
    /**
     * �i������ƂȂ�{@link Cache}�T�[�r�X�̃T�[�r�X����ݒ肷��B<p>
     *
     * @param name Cache�T�[�r�X�̃T�[�r�X��
     */
    public void setPersistCacheServiceName(ServiceName name);
    
    /**
     * �i������ƂȂ�{@link Cache}�T�[�r�X�̃T�[�r�X�����擾����B<p>
     *
     * @return Cache�T�[�r�X�̃T�[�r�X��
     */
    public ServiceName getPersistCacheServiceName();
    
    /**
     * �T�[�r�X�̊J�n���ɁA�i������ƂȂ�{@link Cache}����L���b�V���G���g�����擾���āA���̃L���b�V���ɓǂݍ��݂��s�����ǂ�����ݒ肷��B<p>
     * �f�t�H���g�́Atrue�œǂݍ��݂��s���B<br>
     *
     * @param isLoad �ǂݍ��݂��s���ꍇ��true
     */
    public void setLoadOnStart(boolean isLoad);
    
    /**
     * �T�[�r�X�̊J�n���ɁA�i������ƂȂ�{@link Cache}����L���b�V���G���g�����擾���āA���̃L���b�V���ɓǂݍ��݂��s�����ǂ����𔻒肷��B<p>
     *
     * @return true�̏ꍇ�A�ǂݍ��݂��s��
     */
    public boolean isLoadOnStart();
    
    /**
     * �T�[�r�X�̒�~���ɁA�i������ƂȂ�{@link Cache}�ɃL���b�V���G���g����ۑ����邩�ǂ�����ݒ肷��B<p>
     * �f�t�H���g�́Atrue�ŕۑ�����B<br>
     *
     * @param isSave �ۑ�����ꍇ��true
     */
    public void setSaveOnStop(boolean isSave);
    
    /**
     * �T�[�r�X�̒�~���ɁA�i������ƂȂ�{@link Cache}�ɃL���b�V���G���g����ۑ����邩�ǂ����𔻒肷��B<p>
     *
     * @return true�̏ꍇ�́A�ۑ�����
     */
    public boolean isSaveOnStop();
    
    /**
     * �i������ƂȂ�{@link Cache}����L���b�V���G���g�����擾���āA���̃L���b�V���ɓǂݍ��݂��s���B<p>
     *
     * @exception Exception �ǂݍ��݂Ɏ��s�����ꍇ
     */
    public void load() throws Exception;
    
    /**
     * �i������ƂȂ�{@link Cache}�ɃL���b�V���G���g����ۑ�����B<p>
     *
     * @exception Exception �ۑ��Ɏ��s�����ꍇ
     */
    public void save() throws Exception;
}