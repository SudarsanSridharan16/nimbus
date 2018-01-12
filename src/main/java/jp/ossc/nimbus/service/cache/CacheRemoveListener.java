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
 * �L���b�V���폜���X�i�B<p>
 * �L���b�V���I�u�W�F�N�g���폜���ꂽ�������m���郊�X�i�B<br>
 * ���̃��X�i��o�^����{@link CachedReference}��{@link CachedReference#remove()}��A{@link CachedReference#remove(Object)}�ŁA�L���b�V���I�u�W�F�N�g���폜�����ƁA{@link #removed(CachedReference)}���Ăяo�����B<br>
 *
 * @author M.Takata
 */
public interface CacheRemoveListener{
    
    /**
     * {@link CachedReference}�̃L���b�V���I�u�W�F�N�g���폜���ꂽ�ꍇ�ɌĂяo�����B<p>
     * {@link CachedReference#remove(Object)}���Ăяo���ꂽ�ꍇ�ɁA{@link CachedReference#addCacheRemoveListener(CacheRemoveListener)}�œo�^���ꂽCacheRemoveListener�́A���̃��\�b�h���Ăяo�����B<br>
     *
     * @param ref �폜���ꂽ�L���b�V���I�u�W�F�N�g�̃L���b�V���Q��
     */
    public void removed(CachedReference ref);
}
