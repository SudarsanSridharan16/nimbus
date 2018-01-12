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
package jp.ossc.nimbus.service.queue;

/**
 * Queue�C���^�t�F�[�X�B<p>
 * 
 * @author H.Nakano
 */
public interface Queue{
    
    /**
     * �L���[�Ƀf�[�^�𓊓�����B<p>
     * 
     * @param item �����I�u�W�F�N�g
     */
    public void push(Object item);
    
    /**
     * �L���[�Ƀf�[�^�𓊓�����B<p>
     * 
     * @param item �����I�u�W�F�N�g
     * @param timeout �^�C���A�E�g[ms]
     * @return �^�C���A�E�g�����ꍇfalse
     */
    public boolean push(Object item, long timeout);
    
    /**
     * �L���[����f�[�^�����o���B<p>
     * �L���[����f�[�^���擾�ł���܂ŁA�����ɑ҂B<br>
     * 
     * @return �L���[�擾�I�u�W�F�N�g
     */
    public Object get();
    
    /**
     * �L���[����f�[�^�����o���B<p>
     * �w�肵�����Ԃ��߂���܂łɃL���[����f�[�^���擾�ł��Ȃ��ꍇ�́Anull���Ԃ�B<br>
     * 
     * @param timeOutMs �^�C���A�E�g[ms]
     * @return �L���[�擾�I�u�W�F�N�g
     */
    public Object get(long timeOutMs);
    
    /**
     * �L���[����f�[�^��ǂށB<p>
     * �Q�Ƃ��邾���ŁA�L���[����f�[�^�͎��o���Ȃ��B<br>
     * 
     * @return �L���[�擾�I�u�W�F�N�g
     */
    public Object peek();
    
    /**
     * �L���[����f�[�^��ǂށB<br>
     * �Q�Ƃ��邾���ŁA�L���[����f�[�^�͎��o���Ȃ��B<br>
     * �w�肵�����Ԃ��߂���܂łɃL���[����f�[�^���ǂ߂Ȃ��ꍇ�́Anull���Ԃ�B<br>
     * 
     * @param timeOutMs �^�C���A�E�g[ms]
     * @return �L���[�擾�I�u�W�F�N�g
     */
    public Object peek(long timeOutMs);
    
    /**
     * �L���[����w�肵���f�[�^���폜����B<p>
     *
     * @param item �폜�Ώۂ̃I�u�W�F�N�g
     * @return �폜���ꂽ�I�u�W�F�N�g
     */
    public Object remove(Object item);
    
    /**
     * �L���[������������B<p>
     */
    public void clear();
    
    /**
     * �L���[�T�C�Y���擾����B<p>
     * 
     * @return �L���[�i�[����
     */
    public int size();
    
    /**
     * �L���[�ɓ������ꂽ�������擾����B<p>
     *
     * @return �L���[��������
     */
    public long getCount();
    
    /**
     * �L���[�擾�҂������擾����B<p>
     *
     * @return �L���[�擾�҂���
     */
    public int getWaitCount();
    
    /**
     * �L���[�擾�҂����J�n����B<p>
     * {@link #release()}�ďo����ɁA�L���[�擾�҂����󂯕t����悤�ɂ���B
     */
    public void accept();
    
    /**
     * �L���[�擾�҂����J�����A�L���[�擾�҂����󂯕t���Ȃ��悤�ɂ���B<p>
     */
    public void release();
}
