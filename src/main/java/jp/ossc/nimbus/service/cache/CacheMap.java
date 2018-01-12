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

/**
 * �L���b�V���}�b�v�B<p>
 * {@link Map}�C���^�t�F�[�X�����������L���b�V���B<br>
 *
 * @author M.Takata
 */
public interface CacheMap extends Map{
    
    /**
     * �w�肳�ꂽ�L�[�Ɋ֘A�t����ꂽ�L���b�V���Q�Ƃ��擾����B<p>
     *
     * @param key �L���b�V���̃L�[
     * @return �L���b�V���Q��
     */
    public KeyCachedReference getCachedReference(Object key);
    
    /**
     * �S�ẴL���b�V�����폜����B<p>
     */
    public void clear();
    
    /**
     * �w�肳�ꂽ�L�[�Ɋ֘A�t����ꂽ�L���b�V���I�u�W�F�N�g�����݂��邩���ׂ�B<p>
     *
     * @param key �L���b�V���I�u�W�F�N�g�Ɋ֘A�t�����L�[
     * @return �w�肳�ꂽ�L�[�Ɋ֘A�t����ꂽ�L���b�V���I�u�W�F�N�g�����݂���ꍇtrue
     */
    public boolean containsKey(Object key);
    
    /**
     * �w�肳�ꂽ�L���b�V���I�u�W�F�N�g�����݂��邩���ׂ�B<p>
     *
     * @param value �L���b�V���I�u�W�F�N�g
     * @return �w�肳�ꂽ�L���b�V���I�u�W�F�N�g�����݂���ꍇtrue
     */
    public boolean containsValue(Object value);
    
    /**
     * �L���b�V���}�b�v�Ɋ܂܂�Ă���G���g���̏W�����擾����B<p>
     * ���̏W���̓L���b�V���}�b�v�ƘA�����Ă���̂ŁA�L���b�V���}�b�v�ɑ΂���ύX�͏W���ɔ��f����A�܂��A�W���ɑ΂���ύX�̓L���b�V���}�b�v�ɔ��f�����B<br>
     * ���̏W���ɑ΂��锽���̏������ɃL���b�V���}�b�v���ύX���ꂽ�ꍇ�́A�����̌��ʂ͕ۏ؂���Ȃ��B<br>
     * ���̏W���́AIterator.remove�ASet.remove�AremoveAll�AretainAll�A����� clear �̊e�I�y���[�V�������g���ăL���b�V���}�b�v����Ή�����}�b�s���O���폜����v�f�폜�������T�|�[�g����B
     * add �I�y���[�V������ addAll �I�y���[�V�����́A���̏W���ł̓T�|�[�g����Ă��Ȃ��B<br>
     *
     * @return �L���b�V���}�b�v���ɕێ�����Ă���G���g���̏W��
     */
    public Set entrySet();
    
    /**
     * �w�肳�ꂽ�L�[�Ɋ֘A�t����ꂽ�L���b�V���I�u�W�F�N�g���擾����B<p>
     *
     * @param key �L���b�V���I�u�W�F�N�g�Ɋ֘A�t�����L�[
     * @return �w�肳�ꂽ�L�[�Ɋ֘A�t����ꂽ�L���b�V���I�u�W�F�N�g�B���݂��Ȃ��ꍇ��null
     */
    public Object get(Object key);
    
    /**
     * �L���b�V���}�b�v���󂩂ǂ������ׂ�B<p>
     *
     * @return �L���b�V���}�b�v����̏ꍇ��true
     */
    public boolean isEmpty();
    
    /**
     * �L���b�V���}�b�v�Ɋ܂܂�Ă���L�[�̏W�����擾����B<p>
     * ���̏W���̓L���b�V���}�b�v�ƘA�����Ă���̂ŁA�L���b�V���}�b�v�ɑ΂���ύX�͏W���ɔ��f����A�܂��A�W���ɑ΂���ύX�̓L���b�V���}�b�v�ɔ��f�����B<br>
     * ���̏W���ɑ΂��锽���̏������ɃL���b�V���}�b�v���ύX���ꂽ�ꍇ�́A�����̌��ʂ͕ۏ؂���Ȃ��B<br>
     * ���̏W���́AIterator.remove�ASet.remove�AremoveAll�AretainAll�A����� clear �̊e�I�y���[�V�������g���ăL���b�V���}�b�v����Ή�����}�b�s���O���폜����v�f�폜�������T�|�[�g����B<br>
     * add �I�y���[�V������ addAll �I�y���[�V�����́A���̏W���ł̓T�|�[�g����Ă��Ȃ��B<br>
     * 
     * @return �L���b�V���}�b�v�Ɋ܂܂�Ă���L�[�̏W��
     */
    public Set keySet();
    
    /**
     * �w�肳�ꂽ�L���b�V���I�u�W�F�N�g���w�肳�ꂽ�L�[�Ɋ֘A�t���ăL���b�V������B<p>
     *
     * @param key �L���b�V���I�u�W�F�N�g�Ɋ֘A�t����L�[
     * @param value �L���b�V���I�u�W�F�N�g
     * @return �w�肳�ꂽ�L�[�Ɋ֘A�t�����Ă����Â��L���b�V���I�u�W�F�N�g�B�Â��L���b�V���I�u�W�F�N�g�����݂��Ȃ��ꍇ��null
     */
    public Object put(Object key, Object value);
    
    /**
     * �w�肳�ꂽ�}�b�v�̂��ׂẴ}�b�s���O�����̃L���b�V���}�b�v�ɃR�s�[����B<p>
     * 
     * @param map ���̃L���b�V���}�b�v�Ɋi�[�����}�b�s���O
     */
    public void putAll(java.util.Map map);
    
    /**
     * �w�肳�ꂽ�L�[�Ɋ֘A�t����ꂽ�L���b�V���I�u�W�F�N�g���폜����B<p>
     *
     * @param key �L���b�V���I�u�W�F�N�g�Ɋ֘A�t�����L�[
     * @return �폜�����L���b�V���I�u�W�F�N�g�B�Y������L���b�V���I�u�W�F�N�g�����݂��Ȃ��ꍇ��null
     */
    public Object remove(Object key);
    
    /**
     * �L���b�V������Ă���I�u�W�F�N�g�̐����擾����B<p>
     *
     * @return �L���b�V������Ă���I�u�W�F�N�g�̐�
     */
    public int size();
    
    /**
     * �L���b�V���}�b�v�Ɋ܂܂�Ă���L���b�V���I�u�W�F�N�g�̏W�����擾����B<p>
     * ���̏W���̓L���b�V���}�b�v�ƘA�����Ă���̂ŁA�L���b�V���}�b�v�ɑ΂���ύX�͏W���ɔ��f����A�܂��A�W���ɑ΂���ύX�̓L���b�V���}�b�v�ɔ��f�����B<br>
     * ���̏W���ɑ΂��锽���̏������ɃL���b�V���}�b�v���ύX���ꂽ�ꍇ�A�����̌��ʂ͕ۏ؂���Ȃ��B<br>
     * ���̏W���́AIterator.remove�ACollection.remove�AremoveAll�AretainAll�A����� clear �̊e�I�y���[�V�������g���ăL���b�V���}�b�v����Ή�����}�b�s���O���폜����v�f�폜�������T�|�[�g����B<br>
     * add �I�y���[�V������ addAll �I�y���[�V�����́A���̏W���ł̓T�|�[�g����Ă��Ȃ��B<br>
     *
     * @return �L���b�V���}�b�v���ɕێ�����Ă���L���b�V���I�u�W�F�N�g�̏W��
     */
    public Collection values();
}
