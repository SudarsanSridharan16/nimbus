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
package jp.ossc.nimbus.service.context;

import jp.ossc.nimbus.core.ServiceBaseMBean;
import jp.ossc.nimbus.core.ServiceName;

/**
 * {@link DefaultContextService}��MBean�C���^�t�F�[�X�B<p>
 * 
 * @author H.Nakano
 * @see DefaultContextService
 */
public interface DefaultContextServiceMBean extends ServiceBaseMBean, Context{
    
    /**
     * {@link ContextStore}�T�[�r�X�̃T�[�r�X����ݒ肷��B<p>
     *
     * @param name ContextStore�T�[�r�X�̃T�[�r�X��
     */
    public void setContextStoreServiceName(ServiceName name);
    
    /**
     * {@link ContextStore}�T�[�r�X�̃T�[�r�X�����擾����B<p>
     *
     * @return ContextStore�T�[�r�X�̃T�[�r�X��
     */
    public ServiceName getContextStoreServiceName();
    
    /**
     * �T�[�r�X�̊J�n���ɁA{@link ContextStore}�T�[�r�X���g���ēǂݍ��ݏ������s�����ǂ�����ݒ肷��B<p>
     * �f�t�H���g�́Afalse�B<br>
     *
     * @param isLoad �ǂݍ��ݏ������s���ꍇ�Atrue
     */
    public void setLoadOnStart(boolean isLoad);
    
    /**
     * �T�[�r�X�̊J�n���ɁA{@link ContextStore}�T�[�r�X���g���ēǂݍ��ݏ������s�����ǂ����𔻒肷��B<p>
     *
     * @return true�̏ꍇ�A�ǂݍ��ݏ������s��
     */
    public boolean isLoadOnStart();
    
    /**
     * �T�[�r�X�̊J�n���ɁA{@link ContextStore}�T�[�r�X���g���ăL�[�̓ǂݍ��ݏ������s�����ǂ�����ݒ肷��B<p>
     * �f�t�H���g�́Afalse�B<br>
     *
     * @param isLoad �ǂݍ��ݏ������s���ꍇ�Atrue
     */
    public void setLoadKeyOnStart(boolean isLoad);
    
    /**
     * �T�[�r�X�̊J�n���ɁA{@link ContextStore}�T�[�r�X���g���ăL�[�̓ǂݍ��ݏ������s�����ǂ����𔻒肷��B<p>
     *
     * @return true�̏ꍇ�A�ǂݍ��ݏ������s��
     */
    public boolean isLoadKeyOnStart();
    
    /**
     * �T�[�r�X�̒�~���ɁA{@link ContextStore}�T�[�r�X���g���ď������ݏ������s�����ǂ�����ݒ肷��B<p>
     * �f�t�H���g�́Afalse�B<br>
     *
     * @param isSave �������ݏ������s���ꍇ�Atrue
     */
    public void setSaveOnStop(boolean isSave);
    
    /**
     * �T�[�r�X�̊J�n���ɁA{@link ContextStore}�T�[�r�X���g���ď������ݏ������s�����ǂ����𔻒肷��B<p>
     *
     * @return true�̏ꍇ�A�������ݏ������s��
     */
    public boolean isSaveOnStop();
    
    /**
     * �R���e�L�X�g�̏������ݏ����̑O�ɃX�g�A���N���A���邩�ǂ�����ݒ肷��B<p>
     * �f�t�H���g�́Atrue�ŃN���A����B<br>
     *
     * @param isClear �N���A����ꍇ�Atrue
     */
    public void setClearBeforeSave(boolean isClear);
    
    /**
     * �R���e�L�X�g�̏������ݏ����̑O�ɃX�g�A���N���A���邩�ǂ����𔻒肷��B<p>
     *
     * @return true�̏ꍇ�A�N���A����
     */
    public boolean isClearBeforeSave();
    
    /**
     * �w�肳�ꂽ�L�[�Ɋ֘A�t����ꂽ�R���e�L�X�g�����擾����B<p>
     *
     * @param key �L�[
     * @return �L�[�Ɋ֘A�t����ꂽ�R���e�L�X�g���B�Y������R���e�L�X�g��񂪂Ȃ��ꍇ�́Anull
     */
    public Object get(String key);
    
    /**
     * �w�肳�ꂽ�L�[�Ɋ֘A�t����ꂽ�R���e�L�X�g�����폜����B<p>
     *
     * @param key �L�[
     * @return �폜���ꂽ�R���e�L�X�g���B�폜����R���e�L�X�g��񂪂Ȃ��ꍇ�́Anull
     */
    public Object remove(String key);
    
    /**
     * �u���O(key��toString()) : �l(value��toString()) ���s�v�Ƃ����`���Ń��X�g�o�͂���B<p>
     *
     * @return ���X�g������
     */
    public String list();
    
    /**
     * {@link ContextStore}�T�[�r�X���g���ēǂݍ��ݏ������s���B<p>
     *
     * @exception Exception �ǂݍ��ݏ����Ɏ��s�����ꍇ
     */
    public void load() throws Exception;
    
    /**
     * {@link ContextStore}�T�[�r�X���g���ăL�[�̓ǂݍ��ݏ������s���B<p>
     *
     * @exception Exception �ǂݍ��ݏ����Ɏ��s�����ꍇ
     */
    public void loadKey() throws Exception;
    
    /**
     * �w�肳�ꂽ�L�[�ɊY������l��{@link ContextStore}�T�[�r�X���g���ēǂݍ��ݏ������s���B<p>
     *
     * @exception Exception �ǂݍ��ݏ����Ɏ��s�����ꍇ
     */
    public void load(Object key) throws Exception;
    
    /**
     * {@link ContextStore}�T�[�r�X���g���ď������ݏ������s���B<p>
     *
     * @exception Exception �������ݏ����Ɏ��s�����ꍇ
     */
    public void save() throws Exception;
    
    /**
     * �w�肳�ꂽ�L�[�ɊY������l��{@link ContextStore}�T�[�r�X���g���ď������ݏ������s���B<p>
     *
     * @exception Exception �������ݏ����Ɏ��s�����ꍇ
     */
    public void save(Object key) throws Exception;
    
    /**
     * �w�肳�ꂽ�R���e�L�X�g�����w�肳�ꂽ�L�[���Ɋ֘A�t���Đݒ肷��B<p>
     * 
     * @param key �L�[
     * @param value �R���e�L�X�g���
     * @return �w�肳�ꂽ�L�[�Ɋ֘A�t�����Ă����R���e�L�X�g���B���݂��Ȃ��ꍇ�́Anull
     */
    public Object put(String key, String value);
    
}
