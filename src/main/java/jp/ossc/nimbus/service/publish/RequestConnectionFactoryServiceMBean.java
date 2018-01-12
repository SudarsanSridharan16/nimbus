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
 * {@link RequestConnectionFactoryService}��MBean�C���^�t�F�[�X<p>
 * 
 * @author M.Takata
 * @see RequestConnectionFactoryService
 */
public interface RequestConnectionFactoryServiceMBean extends ServiceBaseMBean{
    
    public static final String MSG_ID_RESPONSE_ERROR_RETRY      = "PRCF_00001";
    public static final String MSG_ID_RESPONSE_ERROR            = "PRCF_00002";
    public static final String MSG_ID_READ_MESSAGE_ERROR        = "PRCF_00003";
    
    /**
     * �v�����b�Z�[�W�y�щ������b�Z�[�W�̑��M�Ɏg�p����{@link ServerConnectionFactory}�T�[�r�X�̃T�[�r�X����ݒ肷��B<p>
     *
     * @param name ServerConnectionFactory�T�[�r�X�̃T�[�r�X��
     */
    public void setServerConnectionFactoryServiceName(ServiceName name);
    
    /**
     * �v�����b�Z�[�W�y�щ������b�Z�[�W�̑��M�Ɏg�p����{@link ServerConnectionFactory}�T�[�r�X�̃T�[�r�X�����擾����B<p>
     *
     * @return ServerConnectionFactory�T�[�r�X�̃T�[�r�X��
     */
    public ServiceName getServerConnectionFactoryServiceName();
    
    /**
     * �v�����b�Z�[�W�y�щ������b�Z�[�W�̎�M�Ɏg�p����{@link MessageReceiver}�T�[�r�X�̃T�[�r�X����ݒ肷��B<p>
     *
     * @param name MessageReceiver�T�[�r�X�̃T�[�r�X��
     */
    public void setMessageReceiverServiceName(ServiceName name);
    
    /**
     * �v�����b�Z�[�W�y�щ������b�Z�[�W�̎�M�Ɏg�p����{@link MessageReceiver}�T�[�r�X�̃T�[�r�X�����擾����B<p>
     *
     * @return MessageReceiver�T�[�r�X�̃T�[�r�X��
     */
    public ServiceName getMessageReceiverServiceName();
    
    /**
     * �������b�Z�[�W�̑��M��񓯊��ōs�����ǂ�����ݒ肷��B<p>
     * �f�t�H���g�́Afalse�œ������M����B<br>
     *
     * @param isAsynch �񓯊����M����ꍇ�́Atrue
     */
    public void setAsynchResponse(boolean isAsynch);
    
    /**
     * �������b�Z�[�W�̑��M��񓯊��ōs�����ǂ����𔻒肷��B<p>
     *
     * @return true�̏ꍇ�A�񓯊����M����
     */
    public boolean isAsynchResponse();
    
    /**
     * �������b�Z�[�W�̑��M�̃��g���C�񐔂�ݒ肷��B<p>
     * �f�t�H���g�́A1�B<br>
     *
     * @param count ���g���C��
     */
    public void setResponseRetryCount(int count);
    
    /**
     * �������b�Z�[�W�̑��M�̃��g���C�񐔂��擾����B<p>
     *
     * @return ���g���C��
     */
    public int getResponseRetryCount();
    
    /**
     * �������b�Z�[�W�̑��M�̃��g���C�Ԋu[ms]��ݒ肷��B<p>
     * �f�t�H���g�́A50[ms]�B<br>
     *
     * @param interval ���g���C�Ԋu[ms]
     */
    public void setResponseRetryInterval(long interval);
    
    /**
     * �������b�Z�[�W�̑��M�̃��g���C�Ԋu[ms]���擾����B<p>
     *
     * @return ���g���C�Ԋu[ms]
     */
    public long getResponseRetryInterval();
    
    /**
     * �N���C�A���g����̗v���ɉ�������ۂɁA���M�G���[�����������g���C����ꍇ�ɏo�͂��郍�O���b�Z�[�WID��ݒ肷��B<p>
     *
     * @param id ���O���b�Z�[�WID
     */
    public void setResponseErrorRetryMessageId(String id);
    
    /**
     * �N���C�A���g����̗v���ɉ�������ۂɁA���M�G���[�����������g���C����ꍇ�ɏo�͂��郍�O���b�Z�[�WID���擾����B<p>
     *
     * @return ���O���b�Z�[�WID
     */
    public String getResponseErrorRetryMessageId();
    
    /**
     * �N���C�A���g����̗v���ɉ�������ۂɁA���M�G���[�����������ꍇ�ɏo�͂��郍�O���b�Z�[�WID��ݒ肷��B<p>
     *
     * @param id ���O���b�Z�[�WID
     */
    public void setResponseErrorMessageId(String id);
    
    /**
     * �N���C�A���g����̗v���ɉ�������ۂɁA���M�G���[�����������ꍇ�ɏo�͂��郍�O���b�Z�[�WID���擾����B<p>
     *
     * @return ���O���b�Z�[�WID
     */
    public String getResponseErrorMessageId();
    
    /**
     * ��M�������b�Z�[�W�̓ǂݍ��݂Ɏ��s�����ꍇ�ɏo�͂��郍�O���b�Z�[�WID��ݒ肷��B<p>
     *
     * @param id ���O���b�Z�[�WID
     */
    public void setReadMessageErrorMessageId(String id);
    
    /**
     * ��M�������b�Z�[�W�̓ǂݍ��݂Ɏ��s�����ꍇ�ɏo�͂��郍�O���b�Z�[�WID���擾����B<p>
     *
     * @return ���O���b�Z�[�WID
     */
    public String getReadMessageErrorMessageId();
    
    /**
     * ���ϑ��M����[ms]���擾����B<p>
     *
     * @return ���ϑ��M����[ms]
     */
    public double getAverageSendProcessTime();
    
    /**
     * ���ω�������[ms]���擾����B<p>
     *
     * @return ���ω�������[ms]
     */
    public double getAverageResponseProcessTime();
    
    /**
     * ���ώ�M��������[ms]���擾����B<p>
     *
     * @return ���ώ�M��������[ms]
     */
    public double getAverageReceiveProcessTime();
    
    /**
     * ���ώ�M��������[ms]���擾����B<p>
     *
     * @return ���ώ�M��������[ms]
     */
    public double getAverageReceiveSendProcessTime();
}