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
package jp.ossc.nimbus.service.aop.interceptor;

import jp.ossc.nimbus.core.*;

/**
 * {@link FlowControlInterceptorService}��MBean�C���^�t�F�[�X<p>
 * 
 * @author M.Takata
 * @see FlowControlInterceptorService
 */
public interface FlowControlInterceptorServiceMBean extends ServiceBaseMBean{
    
    /**
     * �Z�}�t�H�T�[�r�X�̃T�[�r�X����ݒ肷��B<p>
     *
     * @param name �Z�}�t�H�T�[�r�X�̃T�[�r�X��
     */
    public void setSemaphoreServiceName(ServiceName name);
    
    /**
     * �Z�}�t�H�T�[�r�X�̃T�[�r�X�����擾����B<p>
     * 
     * @return �Z�}�t�H�T�[�r�X�̃T�[�r�X��
     */
    public ServiceName getSemaphoreServiceName();
    
    /**
     * �Z�}�t�H�l���҂��^�C���A�E�g��ݒ肷��B<p>
     * 0�ȉ��̒l��ݒ肷��Ɩ����ɃZ�}�t�H�l���҂�����B�f�t�H���g�́A-1�B
     *
     * @param timeout �^�C���A�E�g[ms]
     */
    public void setTimeout(long timeout);
    
    /**
     * �Z�}�t�H�l���҂��^�C���A�E�g���擾����B<p>
     *
     * @return �^�C���A�E�g[ms]
     */
    public long getTimeout();
    
    /**
     * �Z�}�t�H�l���҂��ő吔��ݒ肷��B<p>
     * 0�ȉ��̒l��ݒ肷��ƃZ�}�t�H�l���҂����𐧌����Ȃ��B�f�t�H���g�́A-1�B
     *
     * @param count �Z�}�t�H�l���҂��ő吔
     */
    public void setMaxWaitingCount(int count);
    
    /**
     * �Z�}�t�H�l���҂��ő吔���擾����B<p>
     *
     * @return �Z�}�t�H�l���҂��ő吔
     */
    public int getMaxWaitingCount();
    
    /**
     * �Z�}�t�H�l����̋����Z�}�t�H�J������[ms]��ݒ肷��B<p>
     * 0�ȉ��̒l��ݒ肷��Ƌ����Z�}�t�H�J�����s��Ȃ��B�f�t�H���g�́A-1�B
     *
     * @param timeout �����Z�}�t�H�J������
     */
    public void setForceFreeTimeout(long timeout);
    
    /**
     * �Z�}�t�H�l����̋����Z�}�t�H�J������[ms]���擾����B<p>
     *
     * @return �����Z�}�t�H�J������
     */
    public long getForceFreeTimeout();
    
    /**
     * �Z�}�t�H�l���Ɏ��s�����ꍇ�ɗ�O��throw���邩�ǂ�����ݒ肷��B<p>
     * �f�t�H���g��true�B
     * 
     * @param isThrow �Z�}�t�H�l���Ɏ��s�����ꍇ�ɗ�O��throw����ꍇtrue
     */
    public void setFailToObtainSemaphore(boolean isThrow);
    
    /**
     * �Z�}�t�H�l���Ɏ��s�����ꍇ�ɗ�O��throw���邩�ǂ����𔻒肷��B<p>
     * 
     * @return �Z�}�t�H�l���Ɏ��s�����ꍇ�ɗ�O��throw����ꍇtrue
     */
    public boolean isFailToObtainSemaphore();
}
