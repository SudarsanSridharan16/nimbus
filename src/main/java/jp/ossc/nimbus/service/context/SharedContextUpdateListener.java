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

/**
 * ���L�R���e�L�X�g�X�V���X�i�[�B<p>
 *
 * @author M.Takata
 */
public interface SharedContextUpdateListener{
    
    /**
     * ���L�R���e�L�X�g�ɒǉ������O�ɌĂяo�����B<p>
     *
     * @param context ���L�R���e�L�X�g
     * @param isLocal ���[�J������̒ǉ��̏ꍇ�Atrue�B�����[�g����̒ǉ��̏ꍇ�Afalse�B
     * @param key �ǉ������L�[
     * @param value �ǉ������l
     * @return ���L�R���e�L�X�g�ɒǉ������Ȃ��ꍇ�́Afalse
     */
    public boolean onPutBefore(SharedContext context, boolean isLocal, Object key, Object value);
    
    /**
     * ���L�R���e�L�X�g�ɒǉ����ꂽ��ɌĂяo�����B<p>
     *
     * @param context ���L�R���e�L�X�g
     * @param isLocal ���[�J������̒ǉ��̏ꍇ�Atrue�B�����[�g����̒ǉ��̏ꍇ�Afalse�B
     * @param key �ǉ����ꂽ�L�[
     * @param value �ǉ����ꂽ�l
     * @param old �ȑO�̒l�B�A���A�N���C�A���g���[�h�ȂǂŁA�����ɒl�����݂��Ȃ��ꍇ��null
     */
    public void onPutAfter(SharedContext context, boolean isLocal, Object key, Object value, Object old);
    
    /**
     * �����ɂ���ċ��L�R���e�L�X�g�ɒǉ������O�ɌĂяo�����B<p>
     *
     * @param context ���L�R���e�L�X�g
     * @param key �ǉ������L�[
     * @param value �ǉ������l
     * @return ���L�R���e�L�X�g�ɒǉ������Ȃ��ꍇ�́Afalse
     */
    public boolean onPutSynchronize(SharedContext context, Object key, Object value);
    
    /**
     * ���L�R���e�L�X�g�ɍX�V�����O�ɌĂяo�����B<p>
     *
     * @param context ���L�R���e�L�X�g
     * @param isLocal ���[�J������̍X�V�̏ꍇ�Atrue�B�����[�g����̍X�V�̏ꍇ�Afalse�B
     * @param key �X�V�����L�[
     * @param diff �X�V����鍷��
     * @return ���L�R���e�L�X�g�ɍX�V�����Ȃ��ꍇ�́Afalse
     */
    public boolean onUpdateBefore(SharedContext context, boolean isLocal, Object key, SharedContextValueDifference diff);
    
    /**
     * ���L�R���e�L�X�g�ɍX�V���ꂽ��ɌĂяo�����B<p>
     *
     * @param context ���L�R���e�L�X�g
     * @param isLocal ���[�J������̍X�V�̏ꍇ�Atrue�B�����[�g����̍X�V�̏ꍇ�Afalse�B
     * @param key �X�V���ꂽ�L�[
     * @param diff �X�V���ꂽ����
     */
    public void onUpdateAfter(SharedContext context, boolean isLocal, Object key, SharedContextValueDifference diff);
    
    /**
     * ���L�R���e�L�X�g����폜�����O�ɌĂяo�����B<p>
     *
     * @param context ���L�R���e�L�X�g
     * @param isLocal ���[�J������̍폜�̏ꍇ�Atrue�B�����[�g����̍폜�̏ꍇ�Afalse�B
     * @param key �폜�����L�[
     * @return ���L�R���e�L�X�g����폜�����Ȃ��ꍇ�́Afalse
     */
    public boolean onRemoveBefore(SharedContext context, boolean isLocal, Object key);
    
    /**
     * ���L�R���e�L�X�g����폜���ꂽ��ɌĂяo�����B<p>
     *
     * @param context ���L�R���e�L�X�g
     * @param isLocal ���[�J������̍폜�̏ꍇ�Atrue�B�����[�g����̍폜�̏ꍇ�Afalse�B
     * @param key �폜���ꂽ�L�[
     * @param removed �폜���ꂽ�l�B�A���A�N���C�A���g���[�h�ȂǂŁA�����ɒl�����݂��Ȃ��ꍇ��null
     */
    public void onRemoveAfter(SharedContext context, boolean isLocal, Object key, Object removed);
    
    /**
     * �����ɂ���ċ��L�R���e�L�X�g���N���A�����O�ɌĂяo�����B<p>
     *
     * @param context ���L�R���e�L�X�g
     */
    public void onClearSynchronize(SharedContext context);
    
    /**
     * �����o�̕ύX�ɂ���Ď�m�[�h�ƂȂ�O�ɌĂяo�����B<p>
     *
     * @param context ���L�R���e�L�X�g
     */
    public void onChangeMain(SharedContext context);
    
    /**
     * �����o�̕ύX�ɂ���ď]�m�[�h�ƂȂ�O�ɌĂяo�����B<p>
     *
     * @param context ���L�R���e�L�X�g
     */
    public void onChangeSub(SharedContext context);
}