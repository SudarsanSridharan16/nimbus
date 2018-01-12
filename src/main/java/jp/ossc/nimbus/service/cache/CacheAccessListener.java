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
 * �L���b�V���A�N�Z�X���X�i�B<p>
 * �L���b�V���I�u�W�F�N�g���A�N�Z�X���ꂽ�������m���郊�X�i�B<br>
 * ���̃��X�i��o�^����{@link CachedReference}��{@link CachedReference#get()}��A{@link CachedReference#get(Object)}�A{@link CachedReference#get(Object, boolean) CachedReference.get(obj, true)}�ŁA�L���b�V���I�u�W�F�N�g���A�N�Z�X�����ƁA{@link #accessed(CachedReference)}���Ăяo�����B<br>
 *
 * @author M.Takata
 */
public interface CacheAccessListener{
    
    /**
     * {@link CachedReference}�̃L���b�V���I�u�W�F�N�g���A�N�Z�X���ꂽ�ꍇ�ɌĂяo�����B<p>
     * {@link CachedReference#get(Object)}��A{@link CachedReference#get(Object, boolean)}�̑�������true�ŌĂяo���ꂽ�ꍇ�ɁA{@link CachedReference#addCacheAccessListener(CacheAccessListener)}�œo�^���ꂽCacheAccessListener�́A���̃��\�b�h���Ăяo�����B<br>
     *
     * @param ref �A�N�Z�X���ꂽ�L���b�V���I�u�W�F�N�g�̃L���b�V���Q��
     */
    public void accessed(CachedReference ref);
}
