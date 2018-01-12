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
 * �L���b�V���B<p>
 *
 * @author M.Takata
 */
public interface Cache{
    
    /**
     * �w�肳�ꂽ�I�u�W�F�N�g���L���b�V������B<p>
     *
     * @param obj �L���b�V������I�u�W�F�N�g
     * @return �L���b�V���Q��
     */
    public CachedReference add(Object obj);
    
    /**
     * �L���b�V������Ă���I�u�W�F�N�g�̃L���b�V���Q�Ƃ̔����q���擾����B<p>
     *
     * @return �L���b�V���Q�Ƃ̔����q
     */
    public Iterator iterator();
    
    /**
     * �w�肳�ꂽ�L���b�V���Q�Ƃ��܂ނ��ǂ����𒲂ׂ�B<p>
     * 
     * @param ref �L���b�V���Q��
     * @return �w�肳�ꂽ�L���b�V���Q�Ƃ��܂ޏꍇtrue
     */
    public boolean contains(CachedReference ref);
    
    /**
     * �w�肳�ꂽ�L���b�V���Q�Ƃ̏W����S�Ċ܂ނ��ǂ����𒲂ׂ�B<p>
     * 
     * @param c �L���b�V���Q�Ƃ̏W��
     * @return �w�肳�ꂽ�L���b�V���Q�Ƃ̏W����S�Ċ܂ޏꍇtrue
     */
    public boolean containsAll(Collection c);
    
    /**
     * �L���b�V�����󂩂ǂ������ׂ�B<p>
     *
     * @return �L���b�V������̏ꍇtrue
     */
    public boolean isEmpty();
    
    /**
     * �w�肳�ꂽ�L���b�V���Q�Ƃ������L���b�V���I�u�W�F�N�g���폜����B<p>
     *
     * @param ref �L���b�V���Q��
     * @return �w�肳�ꂽ�L���b�V���Q�Ƃ������L���b�V���I�u�W�F�N�g���폜���鎖�ŁA���̃L���b�V���̓��e���ύX���ꂽ�ꍇtrue
     */
    public boolean remove(CachedReference ref);
    
    /**
     * �w�肳�ꂽ�L���b�V���Q�Ƃ̏W���������L���b�V���I�u�W�F�N�g���폜����B<p>
     *
     * @param c �L���b�V���Q�Ƃ̏W��
     * @return �w�肳�ꂽ�L���b�V���Q�Ƃ̏W���������L���b�V���I�u�W�F�N�g���폜���鎖�ŁA���̃L���b�V���̓��e���ύX���ꂽ�ꍇtrue
     */
    public boolean removeAll(Collection c);
    
    /**
     * �w�肳�ꂽ�L���b�V���Q�Ƃ̏W���������L���b�V���I�u�W�F�N�g�ȊO���폜����B<p>
     *
     * @param c �L���b�V���Q�Ƃ̏W��
     * @return �w�肳�ꂽ�L���b�V���Q�Ƃ̏W���������L���b�V���I�u�W�F�N�g�ȊO���폜���鎖�ŁA���̃L���b�V���̓��e���ύX���ꂽ�ꍇtrue
     */
    public boolean retainAll(Collection c);
    
    /**
     * �L���b�V������Ă���I�u�W�F�N�g�̐����擾����B<p>
     *
     * @return �L���b�V������Ă���I�u�W�F�N�g�̐�
     */
    public int size();
    
    /**
     * �L���b�V������Ă���I�u�W�F�N�g��S�ăL���b�V������폜����B<p>
     */
    public void clear();
    
    /**
     * �L���b�V������Ă���L���b�V���Q�Ƃ̔z����擾����B<p>
     *
     * @return �L���b�V���Q�Ƃ̔z��
     */
    public CachedReference[] toArray();
    
    /**
     * �L���b�V������Ă���L���b�V���Q�Ƃ̔z����擾����B<p>
     * �����Ŏw�肳�ꂽ�L���b�V���Q�Ɣz��̒������A���̃��\�b�h�̌Ăяo���̌��ʕԂ����L���b�V���Q�Ɣz��̒����ȉ��̏ꍇ�́A�����Ŏw�肳�ꂽ�L���b�V���Q�Ɣz��Ɍ��ʂ��i�[���ĕԂ��B�����łȂ��ꍇ�́A�V�����L���b�V���Q�Ɣz��𐶐����āA���ʂ��i�[���ĕԂ��B<br>
     *
     * @param refs �L���b�V���Q�Ƃ̔z��
     * @return �L���b�V���Q�Ƃ̔z��
     */
    public CachedReference[] toArray(CachedReference[] refs);
}
