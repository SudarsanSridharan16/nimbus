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

import java.util.*;

/**
 * �R���e�L�X�g�B<p>
 * ��ӂȃL�[�Ɋ֘A�t����ꂽ�R���e�L�X�g����ێ�����B<br>
 *
 * @author H.Nakano
 */
public interface Context extends Map{
    
    /**
     * �ێ����Ă���R���e�L�X�g���̐����擾����B<p>
     *
     * @return �ێ����Ă���R���e�L�X�g���̐�
     */
    public int size();
    
    /**
     * �R���e�L�X�g����ێ����Ă��Ȃ������ׂ�B<p>
     *
     * @return �R���e�L�X�g����ێ����Ă��Ȃ��ꍇtrue
     */
    public boolean isEmpty();
    
    /**
     * �w�肳�ꂽ�L�[�Ɋ֘A�t����ꂽ�R���e�L�X�g��񂪑��݂��邩���ׂ�B<p>
     *
     * @param key �L�[
     * @return �w�肳�ꂽ�L�[�Ɋ֘A�t����ꂽ�R���e�L�X�g��񂪑��݂���ꍇtrue
     */
    public boolean containsKey(Object key);
    
    /**
     * �w�肳�ꂽ�R���e�L�X�g��񂪑��݂��邩���ׂ�B<p>
     *
     * @param value �R���e�L�X�g���
     * @return �w�肳�ꂽ�R���e�L�X�g��񂪑��݂���ꍇtrue
     */
    public boolean containsValue(Object value);
    
    /**
     * �w�肳�ꂽ�L�[�Ɋ֘A�t����ꂽ�R���e�L�X�g�����擾����B<p>
     *
     * @param key �L�[
     * @return �L�[�Ɋ֘A�t����ꂽ�R���e�L�X�g���B�Y������R���e�L�X�g��񂪂Ȃ��ꍇ�́Anull
     */
    public Object get(Object key);
    
    /**
     * �w�肳�ꂽ�R���e�L�X�g�����w�肳�ꂽ�L�[���Ɋ֘A�t���Đݒ肷��B<p>
     * 
     * @param key �L�[
     * @param value �R���e�L�X�g���
     * @return �w�肳�ꂽ�L�[�Ɋ֘A�t�����Ă����R���e�L�X�g���B���݂��Ȃ��ꍇ�́Anull
     */
    public Object put(Object key, Object value);
    
    /**
     * �w�肳�ꂽ�L�[�Ɋ֘A�t����ꂽ�R���e�L�X�g�����폜����B<p>
     *
     * @param key �L�[
     * @return �폜���ꂽ�R���e�L�X�g���B�폜����R���e�L�X�g��񂪂Ȃ��ꍇ�́Anull
     */
    public Object remove(Object key);
    
    /**
     * �w�肳�ꂽ�}�b�v�Ɋ܂܂��S�ẴL�[�ƒl���R���e�L�X�g���Ƃ��Đݒ肷��B<p>
     *
     * @param t �R���e�L�X�g���Ƃ��Đݒ肷��}�b�v
     */
    public void putAll(Map t);
    
    /**
     * �S�ẴR���e�L�X�g�����폜����B<p>
     */
    public void clear();
    
    /**
     * �R���e�L�X�g���̃L�[�W�����擾����B<p>
     *
     * @return �R���e�L�X�g���̃L�[�W��
     */
    public Set keySet();
    
    /**
     * �R���e�L�X�g���̏W�����擾����B<p>
     *
     * @return �R���e�L�X�g���̏W��
     */
    public Collection values();
    
    /**
     * �R���e�L�X�g���̃G���g���W�����擾����B<p>
     *
     * @return �R���e�L�X�g���̃G���g���W��
     */
    public Set entrySet();
    
    /**
     * �w�肳�ꂽ�I�u�W�F�N�g�Ɠ���������r����B<p>
     *
     * @return �������ꍇtrue
     */
    public boolean equals(Object o);
    
    /**
     * ���̃R���e�L�X�g�̃n�b�V���l���擾����B<p>
     *
     * @return �n�b�V���l
     */
    public int hashCode();
    
    /**
     * ���̃R���e�L�X�g�̓��e��S�Ċ܂ރ}�b�v���擾����B<p>
     *
     * @return �R���e�L�X�g�̓��e��S�Ċ܂ރ}�b�v
     */
    public Map all();
    
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
     * @param key �L�[
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
     * �w�肳�ꂽ�L�[�ɊY������l��{@link ContextStore}�T�[�r�X���g���ď����ݏ������s���B<p>
     *
     * @param key �L�[
     * @exception Exception �ǂݍ��ݏ����Ɏ��s�����ꍇ
     */
    public void save(Object key) throws Exception;
}
