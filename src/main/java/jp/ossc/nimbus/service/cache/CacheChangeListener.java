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

/**
 * �L���b�V���ύX���X�i�B<p>
 * �L���b�V���I�u�W�F�N�g���ύX���ꂽ�������m���郊�X�i�B<br>
 * ���̃��X�i��o�^����{@link CachedReference}��{@link CachedReference#set(Object)}��A{@link CachedReference#set(Object, Object)}�ŁA�L���b�V���I�u�W�F�N�g���ύX�����ƁA{@link #changed(CachedReference, Object)}���Ăяo�����B<br>
 *
 * @author M.Takata
 */
public interface CacheChangeListener{
    
    /**
     * {@link CachedReference}�̃L���b�V���I�u�W�F�N�g���ύX���ꂽ�ꍇ�ɌĂяo�����B<p>
     * {@link CachedReference#set(Object, Object)}���Ăяo���ꂽ�ꍇ�ɁA{@link CachedReference#addCacheChangeListener(CacheChangeListener)}�œo�^���ꂽCacheChangeListener�́A���̃��\�b�h���Ăяo�����B<br>
     *
     * @param ref �ύX���ꂽ�L���b�V���I�u�W�F�N�g�̃L���b�V���Q��
     * @param obj �ύX��̃L���b�V���I�u�W�F�N�g
     */
    public void changed(CachedReference ref, Object obj);
}
