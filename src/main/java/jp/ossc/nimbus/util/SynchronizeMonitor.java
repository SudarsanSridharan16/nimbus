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
package jp.ossc.nimbus.util;

/**
 * �������j�^�B<p>
 * 
 * @author M.Takata
 */
public interface SynchronizeMonitor{
    
    /**
     * �Ăяo���X���b�h�ɑ΂��郂�j�^������������B<p>
     * {@link #waitMonitor()}�A{@link #waitMonitor(long)}���Ăяo���O�ɁA���̃��\�b�h���ĂԕK�v������B<br>
     *
     * @return ���j�^������������O�ɒʒm����Ă����true
     */
    public boolean initMonitor();
    
    /**
     * �w�肵���X���b�h�ɑ΂��郂�j�^������������B<p>
     * �w�肵���X���b�h���A{@link #waitMonitor()}�A{@link #waitMonitor(long)}���Ăяo���O�ɁA���̃��\�b�h���ĂԕK�v������B<br>
     *
     * @param thread ���̃��j�^�ɑ΂��đҋ@����X���b�h
     * @return ���j�^������������O�ɒʒm����Ă����true
     */
    public boolean initMonitor(Thread thread);
    
    /**
     * �Ăяo���X���b�h�ɑ΂��郂�j�^���������B<p>
     * ����X���b�h�ł��̃��j�^���ė��p����ꍇ�ɂ́A���̃��\�b�h���Ăяo���Ȃ��Ă��ǂ��B<br>
     */
    public void releaseMonitor();
    
    /**
     * �S�Ẵ��j�^���������B<p>
     */
    public void releaseAllMonitor();
    
    /**
     * �ʒm������܂őҋ@����B<p>
     * {@link #notifyMonitor()}�A{@link #notifyAllMonitor()}�ɂ���Ēʒm�����܂őҋ@����B<br>
     *
     * @exception InterruptedException ���肱�܂ꂽ�ꍇ
     */
    public void initAndWaitMonitor() throws InterruptedException;
    
    /**
     * �ʒm�����邩�A�w�肳�ꂽ���Ԃ��o�߂���܂őҋ@����B<p>
     * {@link #notifyMonitor()}�A{@link #notifyAllMonitor()}�ɂ���Ēʒm�����܂őҋ@����B<br>
     *
     * @return �ʒm�ɂ���ċN�����ꂽ�ꍇtrue�B�^�C���A�E�g�����ꍇfalse
     * @exception InterruptedException ���肱�܂ꂽ�ꍇ
     */
    public boolean initAndWaitMonitor(long timeout) throws InterruptedException;
    
    /**
     * �ʒm������܂őҋ@����B<p>
     * {@link #notifyMonitor()}�A{@link #notifyAllMonitor()}�ɂ���Ēʒm�����܂őҋ@����B<br>
     *
     * @exception InterruptedException ���肱�܂ꂽ�ꍇ
     */
    public void waitMonitor() throws InterruptedException;
    
    /**
     * �ʒm�����邩�A�w�肳�ꂽ���Ԃ��o�߂���܂őҋ@����B<p>
     * {@link #notifyMonitor()}�A{@link #notifyAllMonitor()}�ɂ���Ēʒm�����܂őҋ@����B<br>
     *
     * @return �ʒm�ɂ���ċN�����ꂽ�ꍇtrue�B�^�C���A�E�g�����ꍇfalse
     * @exception InterruptedException ���肱�܂ꂽ�ꍇ
     */
    public boolean waitMonitor(long timeout) throws InterruptedException;
    
    /**
     * �ҋ@���Ă���ŏ��̃X���b�h�ɒʒm����B<p>
     */
    public void notifyMonitor();
    
    /**
     * �ҋ@���Ă���S�ẴX���b�h�ɒʒm����B<p>
     */
    public void notifyAllMonitor();
    
    /**
     * ���̃X���b�h���ʒm�ɂ���ċN�����ꂽ���ǂ����𔻒肷��B<p>
     * 
     * @return �ʒm�ɂ���ċN�����ꂽ�ꍇ��true
     */
    public boolean isNotify();
    
    /**
     * �ŏ��ɑҋ@���Ă���X���b�h�����݂̃X���b�h���ǂ����𔻒肷��B<p>
     * 
     * @return �ŏ��ɑҋ@���Ă���X���b�h�����݂̃X���b�h�ł���ꍇ��true
     */
    public boolean isFirst();
    
    /**
     * �ҋ@���Ă���X���b�h�����݂��邩�ǂ����𔻒肷��B<p>
     * 
     * @return �ҋ@���Ă���X���b�h�����݂���ꍇ��true
     */
    public boolean isWait();
    
    /**
     * �ҋ@���Ă���X���b�h�̐����擾����B<p>
     * 
     * @return �ҋ@���Ă���X���b�h�̐�
     */
    public int getWaitCount();
    
    /**
     * �ҋ@���Ă���X���b�h���擾����B<p>
     * 
     * @return �ҋ@���Ă���X���b�h�̔z��
     */
    public Thread[] getWaitThreads();
    
    /**
     * �I������B<p>
     * �ҋ@���Ă���S�ẴX���b�h�ɒʒm���A���̃��j�^�𖳌��ɂ���B<br>
     */
    public void close();
}