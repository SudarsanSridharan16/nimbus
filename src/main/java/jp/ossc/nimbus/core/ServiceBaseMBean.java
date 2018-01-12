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
 * �T�[�r�X���N���XMBean�C���^�t�F�[�X�B<p>
 * {@link ServiceBase}�N���X��MBean�Ƃ��Ď������邽�߂̃C���^�t�F�[�X�ł���B<br>
 * ���̃C���^�t�F�[�X�����������N���X�́A{@link Service}�C���^�t�F�[�X��MBean�C���^�t�F�[�X�Ƃ��ĔF�������B<br>
 * 
 * @author M.Takata
 * @see ServiceBase
 */
public interface ServiceBaseMBean extends Service{
    
    /**
     * �T�[�r�X���ċN������B<p>
     *
     * @exception IllegalStateException �T�[�r�X��ԃ`�F�b�N�Ɏ��s�����ꍇ
     * @exception Exception start()�Astop()�ŗ�O�����������ꍇ
     * @see #start()
     * @see #stop()
     */
    public void restart() throws Exception;
    
    /**
     * Service���̃��O�o�͂Ɏg�p����{@link jp.ossc.nimbus.service.log.Logger}�T�[�r�X�̖��O��ݒ肷��B<p>
     *
     * @param name Service���̃��O�o�͂Ɏg�p����{@link jp.ossc.nimbus.service.log.Logger}�T�[�r�X�̖��O
     * @see #getSystemLoggerServiceName()
     */
    public void setSystemLoggerServiceName(ServiceName name);
    
    /**
     * Service���̃��O�o�͂Ɏg�p����{@link jp.ossc.nimbus.service.log.Logger}�T�[�r�X�̖��O���擾����B<p>
     *
     * @return Service���̃��O�o�͂Ɏg�p����{@link jp.ossc.nimbus.service.log.Logger}�T�[�r�X�̖��O
     * @see #setSystemLoggerServiceName(ServiceName)
     */
    public ServiceName getSystemLoggerServiceName();
    
    /**
     * Service���ł̃��b�Z�[�W�擾�Ɏg�p����{@link jp.ossc.nimbus.service.message.MessageRecordFactory}�T�[�r�X�̖��O��ݒ肷��B<p>
     *
     * @param name Service���ł̃��b�Z�[�W�擾�Ɏg�p����{@link jp.ossc.nimbus.service.message.MessageRecordFactory}�T�[�r�X�̖��O
     * @see #getSystemMessageRecordFactoryServiceName()
     */
    public void setSystemMessageRecordFactoryServiceName(ServiceName name);
    
    /**
     * Service���ł̃��b�Z�[�W�擾�Ɏg�p����{@link jp.ossc.nimbus.service.message.MessageRecordFactory}�T�[�r�X�̖��O���擾����B<p>
     *
     * @return Service���ł̃��b�Z�[�W�擾�Ɏg�p����{@link jp.ossc.nimbus.service.message.MessageRecordFactory}�T�[�r�X�̖��O
     * @see #setSystemMessageRecordFactoryServiceName(ServiceName)
     */
    public ServiceName getSystemMessageRecordFactoryServiceName();
}
