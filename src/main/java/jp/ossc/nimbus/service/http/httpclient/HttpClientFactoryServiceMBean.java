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
package jp.ossc.nimbus.service.http.httpclient;

import java.util.Map;

import jp.ossc.nimbus.core.*;

/**
 * {@link HttpClientFactoryService}��MBean�C���^�t�F�[�X<p>
 * 
 * @author M.Takata
 * @see HttpClientFactoryService
 */
public interface HttpClientFactoryServiceMBean extends ServiceBaseMBean{
    
    public static final String JOURNAL_ACCESS = "Access";
    public static final String JOURNAL_ACCESS_EXCEPTION = "Exception";
    public static final String JOURNAL_REQUEST = "Request";
    public static final String JOURNAL_REQUEST_ACTION = "Action";
    public static final String JOURNAL_REQUEST_URI = "URI";
    public static final String JOURNAL_REQUEST_COOKIES = "Cookies";
    public static final String JOURNAL_REQUEST_HEADERS = "Headers";
    public static final String JOURNAL_REQUEST_PARAMS = "Parameters";
    public static final String JOURNAL_REQUEST_OBJECT = "InputObject";
    public static final String JOURNAL_REQUEST_BODY = "Body";
    public static final String JOURNAL_RESPONSE = "Response";
    public static final String JOURNAL_RESPONSE_STATUS = "Status";
    public static final String JOURNAL_RESPONSE_HEADERS = "Headers";
    public static final String JOURNAL_RESPONSE_BODY = "Body";
    public static final String JOURNAL_RESPONSE_OBJECT = "OutputObject";
    
    /**
     * HTTP�̃\�P�b�g��ڑ�����ۂ̃^�C���A�E�g��ݒ肷��B<p>
     *
     * @param millis �^�C���A�E�g[ms]
     */
    public void setConnectionTimeout(int millis);
    
    /**
     * HTTP�̃\�P�b�g��ڑ�����ۂ̃^�C���A�E�g���擾����B<p>
     *
     * @return �^�C���A�E�g[ms]
     */
    public int getConnectionTimeout();
    
    /**
     * HTTP�̃\�P�b�g������ꂽ�ꍇ�ɁA�\�P�b�g�Ɏc���Ă�f�[�^�̑��M��ҋ@����^�C���A�E�g��ݒ肷��B<p>
     *
     * @param millis �^�C���A�E�g[ms]
     */
    public void setLinger(int millis);
    
    /**
     * HTTP�̃\�P�b�g������ꂽ�ꍇ�ɁA�\�P�b�g�Ɏc���Ă�f�[�^�̑��M��ҋ@����^�C���A�E�g���擾����B<p>
     *
     * @return �^�C���A�E�g[ms]
     */
    public int getLinger();
    
    /**
     * HTTP�̃\�P�b�g�̎�M�o�b�t�@�T�C�Y��ݒ肷��B<p>
     *
     * @param size HTTP�̃\�P�b�g�̎�M�o�b�t�@�T�C�Y
     */
    public void setReceiveBufferSize(int size);
    
    /**
     * HTTP�̃\�P�b�g�̎�M�o�b�t�@�T�C�Y���擾����B<p>
     *
     * @return HTTP�̃\�P�b�g�̎�M�o�b�t�@�T�C�Y
     */
    public int getReceiveBufferSize();
    
    /**
     * HTTP�̃\�P�b�g�̑��M�o�b�t�@�T�C�Y��ݒ肷��B<p>
     *
     * @param size HTTP�̃\�P�b�g�̑��M�o�b�t�@�T�C�Y
     */
    public void setSendBufferSize(int size);
    
    /**
     * HTTP�̃\�P�b�g�̑��M�o�b�t�@�T�C�Y���擾����B<p>
     *
     * @return HTTP�̃\�P�b�g�̑��M�o�b�t�@�T�C�Y
     */
    public int getSendBufferSize();
    
    /**
     * HTTP�̃\�P�b�g�̎�M�^�C���A�E�g��ݒ肷��B<p>
     *
     * @param millis HTTP�̃\�P�b�g�̎�M�^�C���A�E�g[ms]
     */
    public void setSoTimeout(int millis);
    
    /**
     * HTTP�̃\�P�b�g�̎�M�^�C���A�E�g���擾����B<p>
     *
     * @return HTTP�̃\�P�b�g�̎�M�^�C���A�E�g[ms]
     */
    public int getSoTimeout();
    
    /**
     * ���N�G�X�g�̃R���e���g�^�C�v���擾����B<p>
     *
     * @return �R���e���g�^�C�v
     */
    public String getRequestContentType();
    
    /**
     * ���N�G�X�g�̃R���e���g�^�C�v��ݒ肷��B<p>
     * HTTP���N�G�X�g�Ɋ��ɐݒ肳��Ă���ꍇ�́A�����炪�D�悳���B<br>
     *
     * @param type �R���e���g�^�C�v
     */
    public void setRequestContentType(String type);
    
    /**
     * ���N�G�X�g�̕����G���R�[�f�B���O���擾����B<p>
     *
     * @return �����G���R�[�f�B���O
     */
    public String getRequestCharacterEncoding();
    
    /**
     * ���N�G�X�g�̕����G���R�[�f�B���O��ݒ肷��B<p>
     * HTTP���N�G�X�g�Ɋ��ɐݒ肳��Ă���ꍇ�́A�����炪�D�悳���B<br>
     *
     * @param encoding �����G���R�[�f�B���O
     */
    public void setRequestCharacterEncoding(String encoding);
    
    /**
     * HTTP�̃o�[�W�������擾����B<p>
     *
     * @return HTTP�̃o�[�W����
     */
    public String getHttpVersion();
    
    /**
     * HTTP�̃o�[�W������ݒ肷��B<p>
     * HTTP���N�G�X�g�Ɋ��ɐݒ肳��Ă���ꍇ�́A�����炪�D�悳���B<br>
     *
     * @param version HTTP�̃o�[�W����
     */
    public void setHttpVersion(String version);
    
    /**
     * ���N�G�X�g�̃w�b�_��ݒ肷��B<p>
     * HTTP���N�G�X�g�Ɋ��ɐݒ肳��Ă���ꍇ�́A�����炪�D�悳���B<br>
     *
     * @param name �w�b�_��
     * @param values �w�b�_�l�̔z��
     */
    public void setRequestHeaders(String name, String[] values);
    
    /**
     * ���N�G�X�g�̃w�b�_���擾����B<p>
     *
     * @param name �w�b�_��
     * @return �w�b�_�l�̔z��
     */
    public String[] getRequestHeaders(String name);
    
    /**
     * �v���L�V�̃z�X�g���ƃ|�[�g�ԍ���ݒ肷��B<p>
     *
     * @param proxy �z�X�g��:�|�[�g�ԍ�
     */
    public void setProxy(String proxy);
    
    /**
     * �v���L�V�̃z�X�g���ƃ|�[�g�ԍ����擾����B<p>
     *
     * @return �z�X�g��:�|�[�g�ԍ�
     */
    public String getProxy();
    
    /**
     * ���[�J���A�h���X��ݒ肷��B<p>
     *
     * @param address ���[�J���A�h���X
     * @exception java.net.UnknownHostException �w�肳�ꂽ�A�h���X���s���ȏꍇ
     */
    public void setLocalAddress(String address)
     throws java.net.UnknownHostException;
    
    /**
     * ���[�J���A�h���X���擾����B<p>
     *
     * @return ���[�J���A�h���X
     */
    public String getLocalAddress();
    
    /**
     * Jakarta HttpClient�̃p�����[�^��ݒ肷��B<p>
     *
     * @param name �p�����[�^��
     * @param value �l
     */
    public void setHttpClientParam(String name, Object value);
    
    /**
     * Jakarta HttpClient�̃p�����[�^���擾����B<p>
     *
     * @param name �p�����[�^��
     * @return �l
     */
    public Object getHttpClientParam(String name);
    
    /**
     * Jakarta HttpClient�̃p�����[�^�̃}�b�v���擾����B<p>
     *
     * @return Jakarta HttpClient�̃p�����[�^�̃}�b�v
     */
    public Map getHttpClientParamMap();
    
    /**
     * ���N�G�X�g�̃X�g���[�������k����ꍇ��臒l[byte]��ݒ肷��B<p>
     * �ݒ肵�Ȃ��ꍇ�́A�X�g���[���̃T�C�Y�Ɋւ�炸���k����B<br>
     *
     * @param length 臒l[byte]
     */
    public void setRequestDeflateLength(int length);
    
    /**
     * ���N�G�X�g�̃X�g���[�������k����ꍇ��臒l[byte]���擾����B<p>
     *
     * @return 臒l[byte]
     */
    public int getRequestDeflateLength();
    
    /**
     * HTTP���N�G�X�g�ɐݒ肳�ꂽ���̓I�u�W�F�N�g���X�g���[���ɕϊ�����{@link jp.ossc.nimbus.util.converter.StreamConverter StreamConverter}�T�[�r�X�̃T�[�r�X����ݒ肷��B<p>
     * HTTP���N�G�X�g�Ɋ��ɐݒ肳��Ă���ꍇ�́A�����炪�D�悳���B<br>
     *
     * @param name StreamConverter�T�[�r�X�̃T�[�r�X��
     */
    public void setRequestStreamConverterServiceName(ServiceName name);
    
    /**
     * HTTP���N�G�X�g�ɐݒ肳�ꂽ���̓I�u�W�F�N�g���X�g���[���ɕϊ�����{@link jp.ossc.nimbus.util.converter.StreamConverter StreamConverter}�T�[�r�X�̃T�[�r�X�����擾����B<p>
     *
     * @return StreamConverter�T�[�r�X�̃T�[�r�X��
     */
    public ServiceName getRequestStreamConverterServiceName();
    
    /**
     * HTTP���X�|���X�̃X�g���[�����o�̓I�u�W�F�N�g�ɕϊ�����{@link jp.ossc.nimbus.util.converter.StreamConverter StreamConverter}�T�[�r�X�̃T�[�r�X����ݒ肷��B<p>
     * HTTP���X�|���X�Ɋ��ɐݒ肳��Ă���ꍇ�́A�����炪�D�悳���B<br>
     *
     * @param name StreamConverter�T�[�r�X�̃T�[�r�X��
     */
    public void setResponseStreamConverterServiceName(ServiceName name);
    
    /**
     * HTTP���X�|���X�̃X�g���[�����o�̓I�u�W�F�N�g�ɕϊ�����{@link jp.ossc.nimbus.util.converter.StreamConverter StreamConverter}�T�[�r�X�̃T�[�r�X�����擾����B<p>
     *
     * @return StreamConverter�T�[�r�X�̃T�[�r�X��
     */
    public ServiceName getResponseStreamConverterServiceName();
    
    /**
     * �W���[�i�����o�͂���{@link jp.ossc.nimbus.service.journal.Journal Journal}�T�[�r�X�̃T�[�r�X����ݒ肷��B<p>
     *
     * @param name Journal�T�[�r�X�̃T�[�r�X��
     */
    public void setJournalServiceName(ServiceName name);
    
    /**
     * �W���[�i�����o�͂���{@link jp.ossc.nimbus.service.journal.Journal Journal}�T�[�r�X�̃T�[�r�X�����擾����B<p>
     *
     * @return Journal�T�[�r�X�̃T�[�r�X��
     */
    public ServiceName getJournalServiceName();
    
    /**
     * �W���[�i���ɐݒ肷��ʔԂ𔭍s����{@link jp.ossc.nimbus.service.sequence.Sequence Sequence}�T�[�r�X�̃T�[�r�X����ݒ肷��B<p>
     *
     * @param name Sequence�T�[�r�X�̃T�[�r�X��
     */
    public void setSequenceServiceName(ServiceName name);
    
    /**
     * �W���[�i���ɐݒ肷��ʔԂ𔭍s����{@link jp.ossc.nimbus.service.sequence.Sequence Sequence}�T�[�r�X�̃T�[�r�X�����擾����B<p>
     *
     * @return Sequence�T�[�r�X�̃T�[�r�X��
     */
    public ServiceName getSequenceServiceName();
    
    /**
     * �W���[�i���ɐݒ肷��ʔԂ��擾����{@link jp.ossc.nimbus.service.context.Context Context}�T�[�r�X�̃T�[�r�X����ݒ肷��B<p>
     *
     * @param name Context�T�[�r�X�̃T�[�r�X��
     */
    public void setThreadContextServiceName(ServiceName name);
    
    /**
     * �W���[�i���ɐݒ肷��ʔԂ��擾����{@link jp.ossc.nimbus.service.context.Context Context}�T�[�r�X�̃T�[�r�X�����擾����B<p>
     *
     * @return Context�T�[�r�X�̃T�[�r�X��
     */
    public ServiceName getThreadContextServiceName();
    
    /**
     * {@link jp.ossc.nimbus.service.http.HttpClient HttpClient}�𐶐�����ۂɎg�p����{@link jp.ossc.nimbus.service.semaphore.Semaphore Semaphore}�T�[�r�X�̃T�[�r�X����ݒ肷��B<p>
     *
     * @param name Semaphore�T�[�r�X�̃T�[�r�X��
     */
    public void setSemaphoreServiceName(ServiceName name);
    
    /**
     * {@link jp.ossc.nimbus.service.http.HttpClient HttpClient}�𐶐�����ۂɎg�p����{@link jp.ossc.nimbus.service.semaphore.Semaphore Semaphore}�T�[�r�X�̃T�[�r�X�����擾����B<p>
     *
     * @return Semaphore�T�[�r�X�̃T�[�r�X��
     */
    public ServiceName getSemaphoreServiceName();
    
    /**
     * �p�t�H�[�}���X���L�^����{@link jp.ossc.nimbus.service.performance.PerformanceRecorder PerformanceRecorder}�T�[�r�X�̃T�[�r�X����ݒ肷��B<p>
     *
     * @param name �p�t�H�[�}���X���L�^����PerformanceRecorder�T�[�r�X�̃T�[�r�X��
     */
    public void setPerformanceRecorderServiceName(ServiceName name);
    
    /**
     * �p�t�H�[�}���X���L�^����{@link jp.ossc.nimbus.service.performance.PerformanceRecorder PerformanceRecorder}�T�[�r�X�̃T�[�r�X�����擾����B<p>
     *
     * @return �p�t�H�[�}���X���L�^����PerformanceRecorder�T�[�r�X�̃T�[�r�X��
     */
    public ServiceName getPerformanceRecorderServiceName();
    
    /**
     * org.apache.commons.httpclient.HttpConnectionManager�̃N���X��ݒ肷��B<p>
     *
     * @param clazz HttpConnectionManager�̃N���X
     */
    public void setHttpConnectionManagerClass(Class clazz);
    
    /**
     * org.apache.commons.httpclient.HttpConnectionManager�̃N���X���擾����B<p>
     *
     * @return HttpConnectionManager�̃N���X
     */
    public Class getHttpConnectionManagerClass();
    
    /**
     * org.apache.commons.httpclient.HttpConnectionManager���g���ăR�l�N�V�������v�[������ꍇ�ɁA�ҋ@��Ԃ̃R�l�N�V���������܂ł̃^�C���A�E�g[ms]��ݒ肷��B<p>
     * �f�t�H���g�́A0�őҋ@��Ԃ̃R�l�N�V�����͕��Ȃ��B<br>
     *
     * @param timeout �^�C���A�E�g[ms]
     */
    public void setIdleConnectionTimeout(long timeout);
    
    /**
     * org.apache.commons.httpclient.HttpConnectionManager���g���ăR�l�N�V�������v�[������ꍇ�ɁA�ҋ@��Ԃ̃R�l�N�V���������܂ł̃^�C���A�E�g[ms]���擾����B<p>
     *
     * @return �^�C���A�E�g[ms]
     */
    public long getIdleConnectionTimeout();
    
    /**
     * org.apache.commons.httpclient.HttpConnectionManager���g���ăR�l�N�V�������v�[������ꍇ�ɁA�ҋ@��Ԃ̃R�l�N�V�����̑ҋ@���Ԃ��`�F�b�N����Ԋu[ms]��ݒ肷��B<p>
     * �f�t�H���g�́A0��org.apache.commons.httpclient.util.IdleConnectionTimeoutThread�̃f�t�H���g�l�ɏ�����B<br>
     *
     * @param interval �`�F�b�N�Ԋu[ms]
     */
    public void setIdleConnectionCheckInterval(long interval);
    
    /**
     * org.apache.commons.httpclient.HttpConnectionManager���g���ăR�l�N�V�������v�[������ꍇ�ɁA�ҋ@��Ԃ̃R�l�N�V�����̑ҋ@���Ԃ��`�F�b�N����Ԋu[ms]���擾����B<p>
     *
     * @return �`�F�b�N�Ԋu[ms]
     */
    public long getIdleConnectionCheckInterval();
    
    /**
     * �W���[�i����{@link jp.ossc.nimbus.service.http.HttpResponse#getObject()}���o�͂��邩�ǂ�����ݒ肷��B<p>
     * �f�t�H���g�́Atrue�ŏo�͂���B{@link jp.ossc.nimbus.service.http.HttpResponse#getObject(Object)}���g�p����ꍇ�́Afalse�ɂ���K�v������B<br>
     *
     * @param isOutput �o�͂���ꍇ�Atrue
     */
    public void setOutputJournalResponseObject(boolean isOutput);
    
    /**
     * �W���[�i����{@link jp.ossc.nimbus.service.http.HttpResponse#getObject()}���o�͂��邩�ǂ����𔻒肷��B<p>
     *
     * @return true�̏ꍇ�A�o�͂���
     */
    public boolean isOutputJournalResponseObject();
    
    /**
     * org.apache.commons.httpclient.MultiThreadedHttpConnectionManager���g�p���Ă���ꍇ�̌��݂̃R�l�N�V�����v�[�������擾����B<p>
     *
     * @return �R�l�N�V�����v�[����
     */
    public int getConnectionsInPool();
    
    /**
     * org.apache.commons.httpclient.MultiThreadedHttpConnectionManager���g�p���Ă���ꍇ�̌��݂̃R�l�N�V�����v�[���g�p�����擾����B<p>
     *
     * @return �R�l�N�V�����v�[���g�p��
     */
    public int getConnectionsInUse();
}