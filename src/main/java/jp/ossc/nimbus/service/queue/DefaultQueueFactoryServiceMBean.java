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

import jp.ossc.nimbus.core.*;

/**
 * {@link DefaultQueueFactoryService}��MBean�C���^�t�F�[�X�B<p>
 * 
 * @author M.Takata
 * @see DefaultQueueService
 */
public interface DefaultQueueFactoryServiceMBean
 extends FactoryServiceBaseMBean{
    
    /**
     * �L���[�̏����e�ʂ�ݒ肷��B<p>
     * �T�[�r�X�̐������Ɏg�p����鑮���Ȃ̂ŁA������̕ύX�͂ł��Ȃ��B<br>
     * 0�ȏ�̒l��ݒ肷��ƗL���ɂȂ�B�f�t�H���g�l�́A-1�Łu�����e�ʂ��w�肵�Ȃ��v�ł���B<br>
     *
     * @param initial �L���[�̏����e��
     */
    public void setInitialCapacity(int initial);
    
    /**
     * �L���[�̏����e�ʂ��擾����B<p>
     *
     * @return �L���[�̏����e��
     */
    public int getInitialCapacity();
    
    /**
     * �L���[�̗v�f�����e�ʂ��z�������ɁA����������e�ʂ�ݒ肷��B<p>
     * �T�[�r�X�̐������Ɏg�p����鑮���Ȃ̂ŁA������̕ύX�͂ł��Ȃ��B<br>
     * 0�ȏ�̒l��ݒ肷��ƗL���ɂȂ�B�܂��A�L���ȏ����e�ʂ��ݒ肳��Ă��Ȃ��ꍇ�́A�����ƂȂ�B�f�t�H���g�l�́A-1�Łu�����e�ʂ��w�肵�Ȃ��v�ł���B<br>
     *
     * @param increment �����e��
     */
    public void setCapacityIncrement(int increment);
    
    /**
     * �L���[�̗v�f�����e�ʂ��z�������ɁA����������e�ʂ��擾����B<p>
     *
     * @return �����e��
     */
    public int getCapacityIncrement();
    
    /**
     * �L���[�v�f���L���b�V������L���b�V���T�[�r�X����ݒ肷��B<p>
     * ���̑������ݒ肳��Ă���ꍇ�A�w�肳�ꂽ�L���b�V���T�[�r�X�ɁA�L���[�v�f���L���b�V������B�L���[�����ɂ́A{@link jp.ossc.nimbus.service.cache.CachedReference CachedReference}���ێ�����邽�߁A�L���[�v�f�̐����̓L���b�V���T�[�r�X�Ɉς˂���B<br>
     *
     * @param name {@link jp.ossc.nimbus.service.cache.Cache Cache}�T�[�r�X��
     */
    public void setCacheServiceName(ServiceName name);
    
    /**
     * �L���[�v�f���L���b�V������L���b�V���T�[�r�X�����擾����B<p>
     *
     * @return {@link jp.ossc.nimbus.service.cache.Cache Cache}�T�[�r�X��
     */
    public ServiceName getCacheServiceName();
    
    /**
     * �L���[������������B <p>
     */
    public void clear();
}
