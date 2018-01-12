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
package jp.ossc.nimbus.service.jmx;

import java.util.Map;
import java.util.List;
import javax.management.QueryExp;
import javax.management.MalformedObjectNameException;

import jp.ossc.nimbus.core.ServiceBaseMBean;
import jp.ossc.nimbus.core.ServiceName;

/**
 * {@link MBeanWatcherService}��MBean�C���^�t�F�[�X�B<p>
 *
 * @author M.Takata
 */
public interface MBeanWatcherServiceMBean extends ServiceBaseMBean{

    public static final String MSG_ID_GET_VALUE_ERROR = "MBW__00001";
    public static final String MSG_ID_CONNECT_ERROR   = "MBW__00002";
    public static final String MSG_ID_WRITE_ERROR     = "MBW__00003";
    public static final String MSG_ID_CHECK_WARN      = "MBW__00004";
    public static final String MSG_ID_CHECK_ERROR     = "MBW__00005";
    public static final String MSG_ID_CHECK_FATAL     = "MBW__00006";

    /**
     * javax.management.MBeanServerConnection��JNDI���̃f�t�H���g�l�B<p>
     */
    public static final String DEFAULT_JMX_RMI_ADAPTOR_NAME = "jmx/invoker/RMIAdaptor";

    /**
     * javax.management.MBeanServerConnection��JNDI����lookup����{@link jp.ossc.nimbus.service.jndi.JndiFinder JndiFinder}�T�[�r�X�̃T�[�r�X����ݒ肷��B<p>
     *
     * @param name JndiFinder�T�[�r�X�̃T�[�r�X��
     */
    public void setJndiFinderServiceName(ServiceName name);

    /**
     * javax.management.MBeanServerConnection��JNDI����lookup����{@link jp.ossc.nimbus.service.jndi.JndiFinder JndiFinder}�T�[�r�X�̃T�[�r�X�����擾����B<p>
     *
     * @return JndiFinder�T�[�r�X�̃T�[�r�X��
     */
    public ServiceName getJndiFinderServiceName();

    /**
     * javax.management.MBeanServerConnection��JNDI����ݒ肷��B<p>
     * �f�t�H���g�́A{@link #DEFAULT_JMX_RMI_ADAPTOR_NAME}�B<br>
     *
     * @param name javax.management.MBeanServerConnection��JNDI��
     */
    public void setRMIAdaptorName(String name);

    /**
     * javax.management.MBeanServerConnection��JNDI�����擾����B<p>
     *
     * @return javax.management.MBeanServerConnection��JNDI��
     */
    public String getRMIAdaptorName();

    /**
     * �ڑ�����JMX�T�[�o�̃T�[�r�XURL��ݒ肷��B<p>
     *
     * @param url �T�[�r�XURL
     */
    public void setServiceURL(String url);

    /**
     * �ڑ�����JMX�T�[�o�̃T�[�r�XURL���擾����B<p>
     *
     * @return �T�[�r�XURL
     */
    public String getServiceURL();

    /**
     * �ڑ�����JMX�T�[�o�̐ڑ����ϐ���ݒ肷��B<p>
     *
     * @param env �ڑ����ϐ�
     */
    public void setJMXConnectorEnvironment(Map env);

    /**
     * �ڑ�����JMX�T�[�o�̐ڑ����ϐ����擾����B<p>
     *
     * @return �ڑ����ϐ�
     */
    public Map getJMXConnectorEnvironment();

    /**
     * �Ď��Ԋu��ݒ肷��B<p>
     * �f�t�H���g��0�ŁA����I�ɊĎ����Ȃ��B<br>
     *
     * @param interval �Ď��Ԋu[ms]
     */
    public void setInterval(long interval);

    /**
     * �Ď��Ԋu���擾����B<p>
     *
     * @return �Ď��Ԋu[ms]
     */
    public long getInterval();

    /**
     * �Ď����ʂ��o�͂���{@link jp.ossc.nimbus.service.writer.Category Category}�T�[�r�X�̃T�[�r�X����ݒ肷��B<p>
     * �w�肵�Ȃ��ꍇ�́A�Ď����ʂ͏o�͂��Ȃ��B<br>
     *
     * @param name Category�T�[�r�X�̃T�[�r�X��
     */
    public void setCategoryServiceName(ServiceName name);

    /**
     * �Ď����ʂ��o�͂���{@link jp.ossc.nimbus.service.writer.Category Category}�T�[�r�X�̃T�[�r�X�����擾����B<p>
     *
     * @return Category�T�[�r�X�̃T�[�r�X��
     */
    public ServiceName getCategoryServiceName();

    /**
     * �T�[�r�X�̊J�n����JMX�ڑ����m�����Ă������ǂ�����ݒ肷��B<p>
     * �f�t�H���g�́Afalse�ŁA�Ď����ɖ���ڑ����s���BRMIAdaptor�o�R�ł̐ڑ��̏ꍇ�́A�����B<br>
     *
     * @param isConnect �T�[�r�X�̊J�n����JMX�ڑ����m������ꍇ�́Atrue
     */
    public void setConnectOnStart(boolean isConnect);

    /**
     * �T�[�r�X�̊J�n����JMX�ڑ����m�����Ă������ǂ����𔻒肷��B<p>
     *
     * @return true�̏ꍇ�A�T�[�r�X�̊J�n����JMX�ڑ����m������
     */
    public boolean isConnectOnStart();

    /**
     * �Ď��Ώۂ̒l���擾�����ۂɃG���[�����������ꍇ�ɏo�͂��郍�O�̃��b�Z�[�WID��ݒ肷��B<p>
     * �f�t�H���g�́A{@link #MSG_ID_GET_VALUE_ERROR}�B<br>
     *
     * @param id ���O�̃��b�Z�[�WID
     */
    public void setGetValueErrorMessageId(String id);

    /**
     * �Ď��Ώۂ̒l���擾�����ۂɃG���[�����������ꍇ�ɏo�͂��郍�O�̃��b�Z�[�WID���擾����B<p>
     *
     * @return ���O�̃��b�Z�[�WID
     */
    public String getGetValueErrorMessageId();

    /**
     * �Ď��Ώۂ̒l���擾����ۂ�JMX�T�[�o�ւ̐ڑ��G���[�����������ꍇ�ɏo�͂��郍�O�̃��b�Z�[�WID��ݒ肷��B<p>
     * �f�t�H���g�́A{@link #MSG_ID_CONNECT_ERROR}�B<br>
     *
     * @param id ���O�̃��b�Z�[�WID
     */
    public void setConnectErrorMessageId(String id);

    /**
     * �Ď��Ώۂ̒l���擾����ۂ�JMX�T�[�o�ւ̐ڑ��G���[�����������ꍇ�ɏo�͂��郍�O�̃��b�Z�[�WID���擾����B<p>
     *
     * @return ���O�̃��b�Z�[�WID
     */
    public String getConnectErrorMessageId();

    /**
     * �Ď����ʂ��o�͂���ۂɃG���[�����������ꍇ�ɏo�͂��郍�O�̃��b�Z�[�WID��ݒ肷��B<p>
     * �f�t�H���g�́A{@link #MSG_ID_WRITE_ERROR}�B<br>
     *
     * @param id ���O�̃��b�Z�[�WID
     */
    public void setWriteErrorMessageId(String id);

    /**
     * �Ď����ʂ��o�͂���ۂɃG���[�����������ꍇ�ɏo�͂��郍�O�̃��b�Z�[�WID���擾����B<p>
     *
     * @return ���O�̃��b�Z�[�WID
     */
    public String getWriteErrorMessageId();

    /**
     * �Ď��Ώۂ�Managed Bean���W���Ƃ��Ĉ������ǂ�����ݒ肷��B<p>
     * �f�t�H���g�́Afalse�ŁA�Ď��Ώۂ́A��ӂ�Managed Bean�B<br>
     *
     * @param isSet �Ď��Ώۂ�Managed Bean���W���Ƃ��Ĉ����ꍇtrue
     */
    public void setMBeanSet(boolean isSet);

    /**
     * �Ď��Ώۂ�Managed Bean���W���Ƃ��Ĉ������ǂ����𔻒肷��B<p>
     *
     * @return true�̏ꍇ�A�Ď��Ώۂ�Managed Bean���W���Ƃ��Ĉ���
     */
    public boolean isMBeanSet();

    /**
     * �Ď��Ώۂ�Managed Bean�̖��O��ݒ肷��B<p>
     * {@link #setMBeanSet(boolean) setMBeanSet(false)}�Ɛݒ肵�Ă���ꍇ�́AManaged Bean����ӂɓ��肷�銮�S�����w�肷��B<br>
     * {@link #setMBeanSet(boolean) setMBeanSet(true)}�Ɛݒ肵�Ă���ꍇ�́AManaged Bean�̏W������肷��I�u�W�F�N�g�����w�肷��B<br>
     *
     * @param name Managed Bean�̖��O��JMX�̃I�u�W�F�N�g���`���Ŏw�肷��
     * @exception MalformedObjectNameException �I�u�W�F�N�g�����s���ȏꍇ
     */
    public void setObjectName(String name) throws MalformedObjectNameException;

    /**
     * �Ď��Ώۂ�Managed Bean�̖��O���擾����B<p>
     *
     * @return Managed Bean�̖��O��JMX�̃I�u�W�F�N�g���`���Ŏw�肷��
     */
    public String getObjectName();

    /**
     * �Ď��Ώۂ�Managed Bean���i�荞�ޏ�������ݒ肷��B<p>
     * {@link #setMBeanSet(boolean) setMBeanSet(true)}�̏ꍇ�̂ݗL���B<br>
     *
     * @param exp ������
     */
    public void setQueryExp(QueryExp exp);

    /**
     * �Ď��Ώۂ�Managed Bean���i�荞�ޏ��������擾����B<p>
     *
     * @return ������
     */
    public QueryExp getQueryExp();

    /**
     * �T�[�r�X�J�n���ɊĎ��Ώۂ�Reset���邩���擾����B<p>
     *
     * @return �T�[�r�X�J�n���ɊĎ��Ώۂ�Reset���邩
     */
    public boolean isResetOnStart();

    /**
     * �T�[�r�X�J�n���ɊĎ��Ώۂ�Reset���邩��ݒ肷��B<p>
     *
     * @param isResetOnStart true�̏ꍇ�A�T�[�r�X�J�n���ɊĎ��Ώۂ�Reset����
     */
    public void setResetOnStart(boolean isResetOnStart);

    /**
     * �Ď��Ώۂ̃��X�g���擾����B<p>
     *
     * @return {@link MBeanWatcherService.Target �Ď��Ώ�}�̃��X�g
     */
    public List getTargetList();

    /**
     * �Ď������s����B<p>
     *
     * @return �L�[���Ď��ΏۃL�[�A�l���Ď��Ώۂ̒l�ƂȂ�Ď����ʃ}�b�v
     * @exception Exception �Ď����s�Ɏ��s�����ꍇ
     */
    public Map watch() throws Exception;

    /**
     * �Ď������s���A�Ď����ʂ��o�͂���B<p>
     * {@link jp.ossc.nimbus.service.writer.Category Category}�T�[�r�X���ݒ肳��Ă��Ȃ��ꍇ�́A�Ď����ʂ̏o�͍͂s���Ȃ��B<br>
     *
     * @exception Exception �Ď����s�܂��͊Ď����ʂ̏o�͂Ɏ��s�����ꍇ
     */
    public void write() throws Exception;

    /**
     * �Ď���Ԃ����Z�b�g����B<p>
     */
    public void reset();
}