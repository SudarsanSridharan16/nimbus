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

import java.util.*;

import jp.ossc.nimbus.core.*;

/**
 * {@link AbstractCacheMapFactoryService}��MBean�C���^�t�F�[�X<p>
 * 
 * @author M.Takata
 * @see AbstractCacheMapFactoryService
 */
public interface AbstractCacheMapFactoryServiceMBean
 extends FactoryServiceBaseMBean{
    
    /**
     * ���ӂꐧ����s��OverflowController�C���^�t�F�[�X�����������T�[�r�X�̃T�[�r�X���̔z���ݒ肷��B<p>
     *
     * @param names ���ӂꐧ����s��OverflowController�C���^�t�F�[�X�����������T�[�r�X�̃T�[�r�X���̔z��
     */
    public void setOverflowControllerServiceNames(ServiceName[] names);
    
    /**
     * ���ӂꐧ����s��OverflowController�C���^�t�F�[�X�����������T�[�r�X�̃T�[�r�X���̔z����擾����B<p>
     *
     * @return ���ӂꐧ����s��OverflowController�C���^�t�F�[�X�����������T�[�r�X�̃T�[�r�X���̔z��
     */
    public ServiceName[] getOverflowControllerServiceNames();
    
    /**
     * �T�[�r�X�̒�~���ɃL���b�V�����N���A���邩�ǂ�����ݒ肷��B<p>
     * �f�t�H���g�́Afalse�B<br>
     *
     * @param isClear �T�[�r�X�̒�~���ɃL���b�V�����N���A����ꍇ�́Atrue
     */
    public void setClearOnStop(boolean isClear);
    
    /**
     * �T�[�r�X�̒�~���ɃL���b�V�����N���A���邩�ǂ����𒲂ׂ�B<p>
     *
     * @return �T�[�r�X�̒�~���ɃL���b�V�����N���A����ꍇ�́Atrue
     */
    public boolean isClearOnStop();
    
    /**
     * �T�[�r�X�̔j�����ɃL���b�V�����N���A���邩�ǂ�����ݒ肷��B<p>
     * �f�t�H���g�́Atrue�B<br>
     *
     * @param isClear �T�[�r�X�̔j�����ɃL���b�V�����N���A����ꍇ�́Atrue
     */
    public void setClearOnDestroy(boolean isClear);
    
    /**
     * �T�[�r�X�̔j�����ɃL���b�V�����N���A���邩�ǂ����𒲂ׂ�B<p>
     *
     * @return �T�[�r�X�̔j�����ɃL���b�V�����N���A����ꍇ�́Atrue
     */
    public boolean isClearOnDestroy();
    
    /**
     * �Ǘ��Ώۂ̃L���b�V���̒��Ɏw�肳�ꂽ�L�[���܂ރL���b�V�������݂��邩�ǂ����𒲂ׂ�B<p>
     *
     * @param key �L���b�V���̃L�[
     * @return �Ǘ��Ώۂ̃L���b�V���̒��Ɏw�肳�ꂽ�L�[���܂ރL���b�V�������݂���ꍇ�́Atrue
     */
    public boolean containsKey(Object key);
    
    /**
     * �Ǘ��Ώۂ̃L���b�V���̒��Ɏw�肳�ꂽ�l���܂ރL���b�V�������݂��邩�ǂ����𒲂ׂ�B<p>
     *
     * @param value �L���b�V���̒l
     * @return �Ǘ��Ώۂ̃L���b�V���̒��Ɏw�肳�ꂽ�l���܂ރL���b�V�������݂���ꍇ�́Atrue
     */
    public boolean containsValue(Object value);
    
    /**
     * �Ǘ��Ώۂ̃L���b�V���̒�����w�肳�ꂽ�L�[�̃L���b�V���Q�Ƃ��擾����B<p>
     * �w�肳�ꂽ�L�[���L���b�V������Ǘ��ΏۃL���b�V�������݂���ꍇ�A���̍ŏ��Ɍ��������L���b�V�����ێ�����L���b�V���Q�Ƃ�Ԃ��B�w�肳�ꂽ�L�[���L���b�V������Ǘ��ΏۃL���b�V�������݂��Ȃ��ꍇ�Anull��Ԃ��B<br>
     *
     * @param key �L���b�V���̃L�[
     * @return �L�[�ɑΉ�����L���b�V���Q�Ƃ̈��
     */
    public KeyCachedReference getCachedReference(Object key);
    
    /**
     * �Ǘ��Ώۂ̃L���b�V���̒�����w�肳�ꂽ�L�[�̃L���b�V���l���擾����B<p>
     * �w�肳�ꂽ�L�[���L���b�V������Ǘ��ΏۃL���b�V�������݂���ꍇ�A���̍ŏ��Ɍ��������L���b�V�����ێ�����L���b�V���l��Ԃ��B�w�肳�ꂽ�L�[���L���b�V������Ǘ��ΏۃL���b�V�������݂��Ȃ��ꍇ�Anull��Ԃ��B<br>
     *
     * @param key �L���b�V���̃L�[
     * @return �L�[�ɑΉ�����L���b�V���l�̈��
     */
    public Object get(Object key);
    
    /**
     * �w�肳�ꂽ�I�u�W�F�N�g���w�肳�ꂽ�L�[�Ɗ֘A�t���āA�Ǘ��Ώۂ̑S�ẴL���b�V���ɃL���b�V������B<p>
     *
     * @param key �L���b�V���̃L�[
     * @param value �L���b�V���l
     */
    public void put(Object key, Object value);
    
    /**
     * �w�肳�ꂽ�}�b�v���A�Ǘ��Ώۂ̑S�ẴL���b�V���ɃL���b�V������B<p>
     *
     * @param map �L���b�V������L�[�ƒl�̃}�b�v
     */
    public void putAll(Map map);
    
    /**
     * �w�肳�ꂽ�L�[�ɊY������L���b�V���l���A�Ǘ��Ώۂ̑S�ẴL���b�V������폜����B<p>
     *
     * @param key �L���b�V���̃L�[
     */
    public void remove(Object key);
    
    /**
     * �S�ẴL���b�V���l���A�Ǘ��Ώۂ̑S�ẴL���b�V������폜����B<p>
     */
    public void clear();
}
