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
 * �L���b�V���Q�ƁB<p>
 * �L���b�V�����ꂽ�I�u�W�F�N�g�̎Q�Ƃ�ێ�����I�u�W�F�N�g�B<br>
 * �L���b�V�����ꂽ�I�u�W�F�N�g�̎Q�Ƃ��ǂ̂悤�Ȍ`�ŕێ�����邩�́A���̃C���^�t�F�[�X�̎����N���X�Ɉˑ�����B<br>
 *
 * @author M.Takata
 */
public interface CachedReference{
    
    /**
     * �L���b�V�����ꂽ�I�u�W�F�N�g���擾����B<p>
     * {@link #get(Object, boolean) get(null, true)}�ŌĂяo���̂ɓ������B<br>
     *
     * @return �L���b�V���I�u�W�F�N�g
     * @see #get(Object, boolean)
     */
    public Object get();
    
    /**
     * �L���b�V�����ꂽ�I�u�W�F�N�g���擾����B<p>
     * {@link #get(Object, boolean) get(source, true)}�ŌĂяo���̂ɓ������B<br>
     *
     * @param source �L���b�V�����擾���邱�̃��\�b�h�̌Ăяo�����I�u�W�F�N�g
     * @return �L���b�V���I�u�W�F�N�g
     * @see #get(Object, boolean)
     */
    public Object get(Object source);
    
    /**
     * �L���b�V�����ꂽ�I�u�W�F�N�g���擾����B<p>
     * ��������true�̏ꍇ�́A{@link #addCacheAccessListener(CacheAccessListener)}�œo�^���ꂽ{@link CacheAccessListener}�ɒʒm����B�A���A�������œn���ꂽ�Ăяo�����I�u�W�F�N�g���ʒm���CacheAccessListener�̃C���X�^���X�Ɠ������ꍇ�́A�ʒm���Ȃ��B<br>
     * ���g���ێ�����L���b�V���I�u�W�F�N�g��null�łȂ��ꍇ�́A�����Ԃ��Bnull�̏ꍇ�́A{@link #addLinkedReference(LinkedReference)}�œo�^���ꂽ{@link LinkedReference}����擾�����݂�B<br>
     *
     * @param source �L���b�V�����擾���邱�̃��\�b�h�̌Ăяo�����I�u�W�F�N�g
     * @param notify �L���b�V���A�N�Z�X���X�i�ɒʒm����ꍇ��true
     * @return �L���b�V���I�u�W�F�N�g
     */
    public Object get(Object source, boolean notify);
    
    /**
     * �L���b�V���I�u�W�F�N�g��ݒ肷��B<p>
     * {@link #set(Object, Object) set(null, obj)}�ŌĂяo���̂ɓ������B<br>
     *
     * @param obj �ݒ肷��L���b�V���I�u�W�F�N�g
     * @exception IllegalCachedReferenceException �L���b�V���Q�Ƃ̏�Ԃ��s���Ȉ׃L���b�V���I�u�W�F�N�g�̐ݒ�Ɏ��s�����ꍇ
     * @see #set(Object, Object)
     */
    public void set(Object obj) throws IllegalCachedReferenceException;
    
    /**
     * �L���b�V���I�u�W�F�N�g��ݒ肷��B<p>
     * {@link #addCacheChangeListener(CacheChangeListener)}�œo�^���ꂽ{@link CacheChangeListener}�ɒʒm����B�A���A�������œn���ꂽ�Ăяo�����I�u�W�F�N�g���ʒm���CacheChangeListener�̃C���X�^���X�Ɠ������ꍇ�́A�ʒm���Ȃ��B<br>
     *
     * @param source �L���b�V���I�u�W�F�N�g��ύX���邱�̃��\�b�h�̌Ăяo�����I�u�W�F�N�g
     * @param obj �ݒ肷��L���b�V���I�u�W�F�N�g
     * @exception IllegalCachedReferenceException �L���b�V���Q�Ƃ̏�Ԃ��s���Ȉ׃L���b�V���I�u�W�F�N�g�̐ݒ�Ɏ��s�����ꍇ
     */
    public void set(Object source, Object obj) throws IllegalCachedReferenceException;
    
    /**
     * �L���b�V���I�u�W�F�N�g���폜����B<p>
     * {@link #remove(Object) remove(null)}�ŌĂяo���̂ɓ������B<br>
     *
     * @see #remove(Object)
     */
    public void remove();
    
    /**
     * �L���b�V���I�u�W�F�N�g���폜����B<p>
     * {@link #addCacheRemoveListener(CacheRemoveListener)}�œo�^���ꂽ{@link CacheRemoveListener}�ɒʒm����B�A���A�������œn���ꂽ�Ăяo�����I�u�W�F�N�g���ʒm���CacheChangeListener�̃C���X�^���X�Ɠ������ꍇ�́A�ʒm���Ȃ��B<br>
     *
     * @param source �L���b�V���I�u�W�F�N�g���폜���邱�̃��\�b�h�̌Ăяo�����I�u�W�F�N�g
     */
    public void remove(Object source);
    
    /**
     * ���̃L���b�V���I�u�W�F�N�g���폜����Ă��邩�ǂ����𔻒肷��B<p>
     *
     * @return �폜����Ă���ꍇ�Atrue
     */
    public boolean isRemoved();
    
    /**
     * �L���b�V�������N�Q�Ƃ�ǉ�����B<p>
     * ���̃L���b�V���Q�Ƃ��ێ�����L���b�V���I�u�W�F�N�g���A���炩�̗��R��{@link LinkedReference}�ɑޔ�����null�ɂȂ��Ă���ꍇ�ɁA{@link #get()}���ŃL���b�V���I�u�W�F�N�g���v�����ꂽ�ꍇ�ɁA���̃��\�b�h�œo�^���ꂽ�L���b�V�������N�Q�Ƃ�{@link LinkedReference#get(CachedReference)}���Ăяo�����B<br>
     *
     * @param link �L���b�V�������N�Q��
     */
    public void addLinkedReference(LinkedReference link);
    
    /**
     * �L���b�V�������N�Q�Ƃ��폜����B<p>
     * 
     * @param link �L���b�V�������N�Q��
     */
    public void removeLinkedReference(LinkedReference link);
    
    /**
     * �L���b�V���폜���X�i��ǉ�����B<p>
     * �L���b�V���I�u�W�F�N�g���폜���ꂽ�������m���郊�X�i��o�^����B<br>
     * {@link #remove()}��A{@link #remove(Object)}�ŁA�L���b�V���I�u�W�F�N�g���폜�����ƁA���̃��\�b�h�œo�^���ꂽ�L���b�V���폜���X�i��{@link CacheRemoveListener#removed(CachedReference)}���Ăяo�����B<br>
     * 
     * @param listener �L���b�V���폜���X�i
     */
    public void addCacheRemoveListener(CacheRemoveListener listener);
    
    /**
     * �L���b�V���폜���X�i���폜����B<p>
     * 
     * @param listener �L���b�V���폜���X�i
     */
    public void removeCacheRemoveListener(CacheRemoveListener listener);
    
    /**
     * �L���b�V���A�N�Z�X���X�i��ǉ�����B<p>
     * �L���b�V���I�u�W�F�N�g���A�N�Z�X���ꂽ�������m���郊�X�i��o�^����B<br>
     * {@link #get()}��A{@link #get(Object)}�A{@link #get(Object, boolean) get(obj, true)}�ŁA�L���b�V���I�u�W�F�N�g���A�N�Z�X�����ƁA���̃��\�b�h�œo�^���ꂽ�L���b�V���A�N�Z�X���X�i��{@link CacheAccessListener#accessed(CachedReference)}���Ăяo�����B<br>
     * 
     * @param listener �L���b�V���A�N�Z�X���X�i
     */
    public void addCacheAccessListener(CacheAccessListener listener);
    
    /**
     * �L���b�V���A�N�Z�X���X�i���폜����B<p>
     * 
     * @param listener �L���b�V���A�N�Z�X���X�i
     */
    public void removeCacheAccessListener(CacheAccessListener listener);
    
    /**
     * �L���b�V���ύX���X�i��ǉ�����B<p>
     * �L���b�V���I�u�W�F�N�g���ύX���ꂽ�������m���郊�X�i��o�^����B<br>
     * {@link #set(Object)}��A{@link #set(Object, Object)}�ŁA�L���b�V���I�u�W�F�N�g���ύX�����ƁA���̃��\�b�h�œo�^���ꂽ�L���b�V���ύX���X�i��{@link CacheChangeListener#changed(CachedReference, Object)}���Ăяo�����B<br>
     * 
     * @param listener �L���b�V���ύX���X�i
     */
    public void addCacheChangeListener(CacheChangeListener listener);
    
    /**
     * �L���b�V���ύX���X�i���폜����B<p>
     * 
     * @param listener �L���b�V���ύX���X�i
     */
    public void removeCacheChangeListener(CacheChangeListener listener);
}