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
package jp.ossc.nimbus.service.publish;

import jp.ossc.nimbus.core.ServiceBaseMBean;
import jp.ossc.nimbus.core.ServiceName;

/**
 * {@link ThinOutServerConnectionFactoryService}��MBean�C���^�t�F�[�X<p>
 * 
 * @author M.Takata
 * @see ThinOutServerConnectionFactoryService
 */
public interface ThinOutServerConnectionFactoryServiceMBean extends ServiceBaseMBean{
    
    /**
     * {@link ServerConnectionFactory}�T�[�r�X�̃T�[�r�X����ݒ肷��B<p>
     *
     * @param name ServerConnectionFactory�T�[�r�X�̃T�[�r�X��
     */
    public void setServerConnectionFactoryServiceName(ServiceName name);
    
    /**
     * {@link ServerConnectionFactory}�T�[�r�X�̃T�[�r�X�����擾����B<p>
     *
     * @return ServerConnectionFactory�T�[�r�X�̃T�[�r�X��
     */
    public ServiceName getServerConnectionFactoryServiceName();
    
    /**
     * {@link ThinOutFilter}�T�[�r�X�̃T�[�r�X���z���ݒ肷��B<p>
     *
     * @param name ThinOutFilter�T�[�r�X�̃T�[�r�X���z��
     */
    public void setThinOutFilterServiceNames(ServiceName[] name);
    
    /**
     * {@link ThinOutFilter}�T�[�r�X�̃T�[�r�X���z����擾����B<p>
     *
     * @return ThinOutFilter�T�[�r�X�̃T�[�r�X���z��
     */
    public ServiceName[] getThinOutFilterServiceNames();
    
    /**
     * �Ԉ����ΏۂƂȂ������b�Z�[�W���Ō�̃��b�Z�[�W�������ꍇ�ɑ��M���邽�߂̊Ď��Ԋu[ms]��ݒ肷��B<p>
     * �f�t�H���g�́A1�b�B<br>
     *
     * @param interval �Ď��Ԋu[ms]
     */
    public void setThinOutTimeoutCheckInterval(long interval);
    
    /**
     * �Ԉ����ΏۂƂȂ������b�Z�[�W���Ō�̃��b�Z�[�W�������ꍇ�ɑ��M���邽�߂̊Ď��Ԋu[ms]���擾����B<p>
     *
     * @return �Ď��Ԋu[ms]
     */
    public long getThinOutTimeoutCheckInterval();
    
    /**
     * �Ԉ����ΏۂƂȂ������b�Z�[�W���Ō�̃��b�Z�[�W�������ꍇ�ɑ��M���邽�߂́A�Ԉ����^�C���A�E�g[ms]��ݒ肷��B<p>
     * �f�t�H���g�́A3�b�B<br>
     *
     * @param timeout �Ԉ����^�C���A�E�g[ms]
     */
    public void setThinOutTimeout(long timeout);
    
    /**
     * �Ԉ����ΏۂƂȂ������b�Z�[�W���Ō�̃��b�Z�[�W�������ꍇ�ɑ��M���邽�߂́A�Ԉ����^�C���A�E�g[ms]���擾����B<p>
     *
     * @return �Ԉ����^�C���A�E�g[ms]
     */
    public long getThinOutTimeout();
}
