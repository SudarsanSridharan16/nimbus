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
package jp.ossc.nimbus.service.writer.publish;

import java.util.Map;

import jp.ossc.nimbus.core.*;

/**
 * {@link ClientConnectionWriterService}��MBean�C���^�t�F�[�X<p>
 * 
 * @author M.Takata
 * @see ClientConnectionWriterService
 */
public interface ClientConnectionWriterServiceMBean extends ServiceBaseMBean{
    
    /**
     * ��M����{@link jp.ossc.nimbus.service.publish.ClientConnectionFactory ClientConnectionFactory}�T�[�r�X�̃T�[�r�X����ݒ肷��B<p>
     *
     * @param name ClientConnectionFactory�T�[�r�X�̃T�[�r�X��
     */
    public void setClientConnectionFactoryServiceName(ServiceName name);
    
    /**
     * ��M����{@link jp.ossc.nimbus.service.publish.ClientConnectionFactory ClientConnectionFactory}�T�[�r�X�̃T�[�r�X�����擾����B<p>
     *
     * @return ClientConnectionFactory�T�[�r�X�̃T�[�r�X��
     */
    public ServiceName getClientConnectionFactoryServiceName();
    
    /**
     * ��M����{@link jp.ossc.nimbus.service.publish.MessageReceiver MessageReceiver}�T�[�r�X�̃T�[�r�X����ݒ肷��B<p>
     *
     * @param name MessageReceiver�T�[�r�X�̃T�[�r�X��
     */
    public void setMessageReceiverServiceName(ServiceName name);
    
    /**
     * ��M����{@link jp.ossc.nimbus.service.publish.MessageReceiver MessageReceiver}�T�[�r�X�̃T�[�r�X�����擾����B<p>
     *
     * @return MessageReceiver�T�[�r�X�̃T�[�r�X��
     */
    public ServiceName getMessageReceiverServiceName();
    
    /**
     * ��M����T�u�W�F�N�g�ƃL�[��ݒ肷��B<p>
     *
     * @param subject �T�u�W�F�N�g
     * @param keys �L�[�z��
     */
    public void setSubject(String subject, String[] keys);
    
    /**
     * ��M����T�u�W�F�N�g�ƃL�[�̃}�b�v���擾����B<p>
     *
     * return �T�u�W�F�N�g�ƃL�[�z��̃}�b�v
     */
    public Map getSubjectMap();
    
    /**
     * �o�͂���{@link jp.ossc.nimbus.service.writer.MessageWriter MessageWriter}�T�[�r�X�̃T�[�r�X����ݒ肷��B<p>
     *
     * @param name MessageWriter�T�[�r�X�̃T�[�r�X��
     */
    public void setMessageWriterServiceName(ServiceName name);
    
    /**
     * �o�͂���{@link jp.ossc.nimbus.service.writer.MessageWriter MessageWriter}�T�[�r�X�̃T�[�r�X�����擾����B<p>
     *
     * @return MessageWriter�T�[�r�X�̃T�[�r�X��
     */
    public ServiceName getMessageWriterServiceName();
    
    /**
     * ��M�������b�Z�[�W�̃T�u�W�F�N�g�ƃL�[��ݒ肷��{@link jp.ossc.nimbus.service.context.Context Context}�T�[�r�X�̃T�[�r�X����ݒ肷��B<p>
     *
     * @param name Context�T�[�r�X�̃T�[�r�X��
     */
    public void setContextServiceName(ServiceName name);
    
    /**
     * ��M�������b�Z�[�W�̃T�u�W�F�N�g�ƃL�[��ݒ肷��{@link jp.ossc.nimbus.service.context.Context Context}�T�[�r�X�̃T�[�r�X�����擾����B<p>
     *
     * @return Context�T�[�r�X�̃T�[�r�X��
     */
    public ServiceName getContextServiceName();
    
    /**
     * ��M�������b�Z�[�W�̃T�u�W�F�N�g��{@link jp.ossc.nimbus.service.context.Context Context}�T�[�r�X�ɐݒ肷�鎞�̃L�[��ݒ肷��B<p>
     * �ݒ肵�Ȃ��ꍇ�́A�T�u�W�F�N�g��Context�T�[�r�X�ɐݒ肵�Ȃ��B<br>
     *
     * @param key �T�u�W�F�N�g��Context�T�[�r�X�ɐݒ肷�鎞�̃L�[
     */
    public void setSubjectContextKey(String key);
    
    /**
     * ��M�������b�Z�[�W�̃T�u�W�F�N�g��{@link jp.ossc.nimbus.service.context.Context Context}�T�[�r�X�ɐݒ肷�鎞�̃L�[���擾����B<p>
     *
     * @return �T�u�W�F�N�g��Context�T�[�r�X�ɐݒ肷�鎞�̃L�[
     */
    public String getSubjectContextKey();
    
    /**
     * ��M�������b�Z�[�W�̃L�[��{@link jp.ossc.nimbus.service.context.Context Context}�T�[�r�X�ɐݒ肷�鎞�̃L�[��ݒ肷��B<p>
     * �ݒ肵�Ȃ��ꍇ�́A�L�[��Context�T�[�r�X�ɐݒ肵�Ȃ��B<br>
     *
     * @param key �L�[��Context�T�[�r�X�ɐݒ肷�鎞�̃L�[
     */
    public void setKeyContextKey(String key);
    
    /**
     * ��M�������b�Z�[�W�̃L�[��{@link jp.ossc.nimbus.service.context.Context Context}�T�[�r�X�ɐݒ肷�鎞�̃L�[���擾����B<p>
     *
     * @return �L�[��Context�T�[�r�X�ɐݒ肷�鎞�̃L�[
     */
    public String getKeyContextKey();
}
