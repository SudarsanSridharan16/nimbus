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
package jp.ossc.nimbus.service.ejb;

import jp.ossc.nimbus.core.*;

/**
 * {@link InvocationEJBFactoryFactoryService}��MBean�C���^�t�F�[�X<p>
 * 
 * @author M.Takata
 * @see InvocationEJBFactoryFactoryService
 */
public interface InvocationEJBFactoryFactoryServiceMBean
 extends FactoryServiceBaseMBean{
    
    /**
     * EJBObject���L���b�V������{@link jp.ossc.nimbus.service.cache.CacheMap CacheMap}�̃T�[�r�X�����擾����B<p>
     * �L���b�V�����g�p���Ȃ��ꍇ�́Anull��Ԃ��B<br>
     *
     * @return CacheMap�̃T�[�r�X��
     * @see jp.ossc.nimbus.service.cache.CacheMap CacheMap
     * @see #setRemoteCacheMapServiceName(ServiceName)
     */
    public ServiceName getRemoteCacheMapServiceName();
    
    /**
     * EJBObject���L���b�V������{@link jp.ossc.nimbus.service.cache.CacheMap CacheMap}�̃T�[�r�X����ݒ肷��B<p>
     * �ݒ肵�Ȃ��ꍇ�ɂ́AEJBObject�̃L���b�V�����s��Ȃ��B<br>
     * 
     * @param serviceName CacheMap�̃T�[�r�X��
     * @see jp.ossc.nimbus.service.cache.CacheMap CacheMap
     * @see #getRemoteCacheMapServiceName()
     */
    public void setRemoteCacheMapServiceName(ServiceName serviceName);
    
    /**
     * EJBHome��lookup����{@link jp.ossc.nimbus.service.jndi.JndiFinder JndiFinder}�̃T�[�r�X�����擾����B<p>
     * JndiFinder�T�[�r�X���g�p���Ȃ��ꍇ�́Anull��Ԃ��B<br>
     *
     * @return JndiFinder�̃T�[�r�X��
     * @see jp.ossc.nimbus.service.jndi.JndiFinder JndiFinder
     * @see #setJndiFinderServiceName(ServiceName)
     */
    public ServiceName getJndiFinderServiceName();
    
    /**
     * EJBHome��lookup����{@link jp.ossc.nimbus.service.jndi.JndiFinder JndiFinder}�̃T�[�r�X����ݒ肷��B<p>
     * �K���ݒ肷��K�v������B�L���ȃT�[�r�X�����ݒ肵�Ă��Ȃ��ꍇ�ɂ́A�T�[�r�X�̐��������ŗ�O��throw����ꍇ������B<br>
     * 
     * @param serviceName JndiFinder�̃T�[�r�X��
     * @see jp.ossc.nimbus.service.jndi.JndiFinder JndiFinder
     * @see #getJndiFinderServiceName()
     */
    public void setJndiFinderServiceName(ServiceName serviceName);
}
