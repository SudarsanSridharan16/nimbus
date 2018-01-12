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
package jp.ossc.nimbus.service.semaphore;

/**
 * �Z�}�t�H�C���^�[�t�F�C�X�B<p>
 * 
 * @author H.Nakano
 */
public interface Semaphore{
    
    /**
     * �Z�}�t�H�̃��\�[�X�l���҂�������B<p>
     * 
     * @param timeOutMiliSecond �ő�҂�����[ms]
     * @param maxWaitCount �ő哯���l���҂���
     * @param forceFreeMiliSecond �����J������[ms]
     * @return �l�������̏ꍇ�Atrue
     */
    public boolean getResource(
        long timeOutMiliSecond,
        int maxWaitCount,
        long forceFreeMiliSecond
    );
    
    /**
     * �Z�}�t�H�̃��\�[�X�l���҂�������B<p>
     * 
     * @param timeOutMiliSecond �ő�҂�����[ms]
     * @param maxWaitCount �ő哯���l���҂���
     * @return �l�������̏ꍇ�Atrue
     */
    public boolean getResource(long timeOutMiliSecond, int maxWaitCount);
    
    /**
     * �Z�}�t�H�̃��\�[�X�l���҂�������B<p>
     * 
     * @param timeOutMiliSecond �ő�҂�����[ms]
     * @return �l�������̏ꍇ�Atrue
     */
    public boolean getResource(long timeOutMiliSecond);
    
    /**
     * �Z�}�t�H�̃��\�[�X�l���҂�������B<p>
     * 
     * @param maxWaitCount �ő哯���l���҂���
     * @return �l�������̏ꍇ�Atrue
     */
    public boolean getResource(int maxWaitCount);
    
    /**
     * �Z�}�t�H�̃��\�[�X�l�������҂�������B<p>
     * 
     * @return �l�������̏ꍇ�Atrue
     */
    public boolean getResource();
    
    /**
     * �l�������Z�}�t�H�̃��\�[�X���J������B<p>
     */
    public void freeResource();
    
    /**
     * �Z�}�t�H�̃��\�[�X�������擾����B<p>
     *
     * @return �Z�}�t�H�̃��\�[�X����
     */
    public int getResourceCapacity();
    
    /**
     * �Z�}�t�H�̃��\�[�X������ݒ肷��B<p>
     *
     * @param capa �Z�}�t�H�̃��\�[�X����
     */
    public void setResourceCapacity(int capa);
    
    /**
     * �Z�}�t�H�ɑ΂��Ė����l���҂�������X���b�h��sleep���鎞�Ԃ�ݒ肷��B<p>
     * �������Z�}�t�H�҂��̐擪�łȂ��ꍇ�́A�Ă�sleep����B<br>
     *
     * @param millis �Z�}�t�H�ɑ΂��Ė����l���҂�������X���b�h��sleep���鎞��[ms]
     */
    public void setSleepTime(long millis);
    
    /**
     * �Z�}�t�H�ɑ΂��Ė����l���҂�������X���b�h��sleep���鎞�Ԃ��擾����B<p>
     *
     * @return �Z�}�t�H�ɑ΂��Ė����l���҂�������X���b�h��sleep���鎞��[ms]
     */
    public long getSleepTime();
    
    /**
     * ���\�[�X���󂢂Ă��邩����I�Ƀ`�F�b�N���鎞�ԊԊu[ms]��ݒ肷��B<p>
     * ���\�[�X���󂢂Ă��đ҂��Ă���X���b�h������ꍇ�́A���̃X���b�h���N�����B<br>
     * �f�t�H���g�́A�`�F�b�N���Ȃ��B
     *
     * @param millis ���\�[�X���󂢂Ă��邩����I�Ƀ`�F�b�N���鎞�ԊԊu[ms]
     */
    public void setCheckInterval(long millis);
    
    /**
     * ���\�[�X���󂢂Ă��邩����I�Ƀ`�F�b�N���鎞�ԊԊu[ms]���擾����B<p>
     *
     * @return ���\�[�X���󂢂Ă��邩����I�Ƀ`�F�b�N���鎞�ԊԊu[ms]
     */
    public long getCheckInterval();
    
    /**
     * ���[�\�[�X�̎擾�Ɖ���̃X���b�h���֘A�t���邩�ǂ�����ݒ肷��B<p>
     * �f�t�H���g��true�ŁA���\�[�X���擾�����X���b�h����̉�������󂯕t���Ȃ��B<br>
     * �܂��Afalse�ɂ����ꍇ�́A��������^�C���A�E�g�͖����ƂȂ�B<br>
     *
     * @param isBinding ���\�[�X���擾�����X���b�h����̉�������󂯕t���Ȃ��悤�ɂ���ꍇ�́Atrue
     */
    public void setThreadBinding(boolean isBinding);
    
    /**
     * ���[�\�[�X�̎擾�Ɖ���̃X���b�h���֘A�t���邩�ǂ����𔻒肷��B<p>
     *
     * @return true�̏ꍇ�́A���\�[�X���擾�����X���b�h����̉�������󂯕t���Ȃ�
     */
    public boolean isThreadBinding();
    
    /**
     * �Z�}�t�H�̎c�胊�\�[�X�����擾����B<p>
     * 
     * @return �Z�}�t�H�̎c�胊�\�[�X��
     */
    public int getResourceRemain();
    
    /**
     * �Z�}�t�H�l���҂��X���b�h���J�����A�Z�}�t�H�l���҂����󂯕t���Ȃ��悤�ɂ���B<p>
     */
    public void release();
    
    /**
     * �Z�}�t�H�l���҂��̎�t���J�n����B<p>
     * {@link #release()}�ďo����ɁA�Z�}�t�H�l���҂����󂯕t����悤�ɂ���B
     */
    public void accept();
    
    /**
     * �Z�}�t�H�l���҂������Ă���X���b�h�����擾����B<p>
     * 
     * @return �Z�}�t�H�l���҂������Ă���X���b�h��
     */
    public int getWaitingCount();
    
    /**
     * �Z�}�t�H�̍ő�g�p���т��擾����B<p>
     *
     * @return �ő�g�p����
     */
    public int getMaxUsedResource();
    
    /**
     * �Z�}�t�H�̍ő�҂������т��擾����B<p>
     *
     * @return �ő�҂�������
     */
    public int getMaxWaitedCount();
}
