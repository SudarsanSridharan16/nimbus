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
package jp.ossc.nimbus.core;

/**
 * �T�[�r�X���T�|�[�g�C���^�t�F�[�X�B<p>
 * {@link ServiceBase}�N���X���p�����Ȃ��Ă��AServiceBase�̎����𗘗p�ł���悤�ɂ��邽�߂̃C���^�t�F�[�X�ł���B<br>
 * ���̃C���^�t�F�[�X�����������N���X�́A{@link ServiceManager}�ɓo�^����ۂɁAServiceBase�N���X�Ń��b�v����ēo�^�����B�o�^���ꂽ���̃N���X�̃C���X�^���X���A{@link ServiceManager#getService(String)}�ŁA�擾���Ďg�p����ꍇ�ɂ́A���b�v���ꂽ�I�u�W�F�N�g���擾����AServiceBase���p�������N���X�Ɠ����̋@�\���g�p�ł���B<br>
 * 
 * @author M.Takata
 * @see ServiceBase
 */
public interface ServiceBaseSupport{
    
    /**
     * ���̃T�[�r�X�����b�v����{@link ServiceBase}��ݒ肷��B<p>
     * 
     * @param service ���̃T�[�r�X�����b�v����ServiceBase
     */
    public void setServiceBase(ServiceBase service);
    
    /**
     * �T�[�r�X�𐶐�����B<p>
     * ���̃T�[�r�X�ɕK�v�ȃI�u�W�F�N�g�̐����Ȃǂ̏������������s���B<br>
     * ���̃C���^�t�F�[�X��implements���ăT�[�r�X����������T�[�r�X�J���҂́A�T�[�r�X�̐����������A���̃��\�b�h�Ɏ������邱�ƁB<br>
     *
     * @exception Exception �T�[�r�X�̐��������Ɏ��s�����ꍇ
     * @see ServiceBase#create()
     */
    public void createService() throws Exception;
    
    /**
     * �T�[�r�X���J�n����B<p>
     * ���̃T�[�r�X�𗘗p�\�ȏ�Ԃɂ���B���̃��\�b�h�̌Ăяo����́A���̃T�[�r�X�̋@�\�𗘗p�ł��鎖���ۏ؂����B<br>
     * ���̃C���^�t�F�[�X��implements���ăT�[�r�X����������T�[�r�X�J���҂́A�T�[�r�X�̊J�n�������A���̃��\�b�h�Ɏ������邱�ƁB<br>
     *
     * @exception Exception �T�[�r�X�̊J�n�����Ɏ��s�����ꍇ
     * @see ServiceBase#start()
     */
    public void startService() throws Exception;
    
    /**
     * �T�[�r�X���~����B<p>
     * ���̃T�[�r�X�𗘗p�s�\�ȏ�Ԃɂ���B���̃��\�b�h�̌Ăяo����́A���̃T�[�r�X�̋@�\�𗘗p�ł��鎖�͕ۏ؂���Ȃ��B<br>
     * ���̃C���^�t�F�[�X��implements���ăT�[�r�X����������T�[�r�X�J���҂́A�T�[�r�X�̒�~�������A���̃��\�b�h�Ɏ������邱�ƁB<br>
     *
     * @exception Exception �T�[�r�X�̒�~�����Ɏ��s�����ꍇ�B�A���Astop()�ň���ׂ���āA�����͑��s�����B
     * @see ServiceBase#stop()
     */
    public void stopService() throws Exception;
    
    /**
     * �T�[�r�X��j������B<p>
     * ���̃T�[�r�X�Ŏg�p���郊�\�[�X���J������B���̃��\�b�h�̌Ăяo����́A���̃T�[�r�X�̋@�\�𗘗p�ł��鎖�͕ۏ؂���Ȃ��B<br>
     * ���̃C���^�t�F�[�X��implements���ăT�[�r�X����������T�[�r�X�J���҂́A�T�[�r�X�̔j���������A���̃��\�b�h�Ɏ������邱�ƁB<br>
     *
     * @exception Exception �T�[�r�X�̔j�������Ɏ��s�����ꍇ�B�A���Adestroy()�ň���ׂ���āA�����͑��s�����B
     * @see ServiceBase#destroy()
     */
    public void destroyService() throws Exception;
}