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
package jp.ossc.nimbus.daemon;

/**
 * �f�[�����X���b�h�Ŏ��s�����鏈�����������邽�߂̃C���^�t�F�[�X�B<p>
 *
 * @author H.Nakano
 * @see Daemon
 */
public interface DaemonRunnable{
    
    /**
     * �f�[�����X���b�h���J�n���悤�Ƃ��鎞�Ɏ��s���鏈�����s���B<p>
     *
     * @return true�̏ꍇ�A�f�[�����X���b�h���J�n����Bfalse�̏ꍇ�A�f�[�����X���b�h�͊J�n���Ȃ��B
     */
    public boolean onStart() ;
    
    /**
     * �f�[�����X���b�h����~���悤�Ƃ��鎞�Ɏ��s���鏈�����s���B<p>
     *
     * @return true�̏ꍇ�A�f�[�����X���b�h����~����Bfalse�̏ꍇ�A�f�[�����X���b�h�͒�~���Ȃ��B
     */
    public boolean onStop();
    
    /**
     * �f�[�����X���b�h���ꎞ��~���悤�Ƃ��鎞�Ɏ��s���鏈�����s���B<p>
     *
     * @return true�̏ꍇ�A�f�[�����X���b�h���ꎞ��~����Bfalse�̏ꍇ�A�f�[�����X���b�h�͈ꎞ��~���Ȃ��B
     */
    public boolean onSuspend();
    
    /**
     * �f�[�����X���b�h���ĊJ���悤�Ƃ��鎞�Ɏ��s���鏈�����s���B<p>
     *
     * @return true�̏ꍇ�A�f�[�����X���b�h���ĊJ����Bfalse�̏ꍇ�A�f�[�����X���b�h�͍ĊJ���Ȃ��B
     */
    public boolean onResume();
    
    /**
     * �f�[�����X���b�h�ŏ�������C�ӂ̃I�u�W�F�N�g����������B<p>
     * 
     * @param ctrl �f�[�����X���b�h�̎��s�𐧌䂷��DaemonControl
     * @return �C�ӂ̃I�u�W�F�N�g
     * @exception Throwable �I�u�W�F�N�g�̋����ɂ����Ė�肪���������ꍇ
     */
    public Object provide(DaemonControl ctrl) throws Throwable;
    
    /**
     * {@link #provide(DaemonControl)}�ɂ���ċ������ꂽ�C�ӂ̃I�u�W�F�N�g�������B<p>
     * 
     * @param paramObj �������ꂽ�C�ӂ̃I�u�W�F�N�g
     * @param ctrl �f�[�����X���b�h�̎��s�𐧌䂷��DaemonControl
     * @exception Throwable �I�u�W�F�N�g�̏���ɂ����Ė�肪���������ꍇ
     */
    public void consume(Object paramObj, DaemonControl ctrl) throws Throwable;
    
    /**
     * �f�[�����X���b�h����~���鎞�ɃK�x�[�W�������s���B<p>
     */
    public void garbage();
}
