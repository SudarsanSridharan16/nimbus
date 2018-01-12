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
package jp.ossc.nimbus.service.scheduler;

import jp.ossc.nimbus.core.*;

/**
 * {@link IOCCallScheduleTaskService}�T�[�r�X��MBean�C���^�t�F�[�X�B<p>
 * 
 * @author M.Takata
 */
public interface IOCCallScheduleTaskServiceMBean extends ServiceBaseMBean{
    
    /**
     * IOC�Ăяo����ʃL�[ �����ꊇ�B<p>
     */
    public static final String IOC_CALL_TYPE_SYNCH = "Synch";
    
    /**
     * IOC�Ăяo����ʃL�[ ��������B<p>
     */
    public static final String IOC_CALL_TYPE_SYNCH_PARALLEL = "SynchParallel";
    
    /**
     * IOC�Ăяo����ʃL�[ ��������B<p>
     */
    public static final String IOC_CALL_TYPE_SYNCH_SEQUENCE = "SynchSequence";
    
    /**
     * IOC�Ăяo����ʃL�[ �񓯊��ꊇ�B<p>
     */
    public static final String IOC_CALL_TYPE_ASYNCH = "Asynch";
    
    /**
     * IOC�Ăяo����ʃL�[ �񓯊�����B<p>
     */
    public static final String IOC_CALL_TYPE_ASYNCH_SEQUENCE = "AsynchSequence";
    
    /**
     * {@link jp.ossc.nimbus.service.ioccall.FacadeCaller FacadeCaller}�T�[�r�X�̃T�[�r�X����ݒ肷��B<p>
     *
     * @param name FacadeCaller�T�[�r�X�̃T�[�r�X��
     */
    public void setFacadeCallerServiceName(ServiceName name);
    
    /**
     * {@link jp.ossc.nimbus.service.ioccall.FacadeCaller FacadeCaller}�T�[�r�X�̃T�[�r�X�����擾����B<p>
     *
     * @return FacadeCaller�T�[�r�X�̃T�[�r�X��
     */
    public ServiceName getFacadeCallerServiceName();
    
    /**
     * ���s����Ɩ��t���[����ݒ肷��B<p>
     *
     * @param names �Ɩ��t���[��
     */
    public void setBeanFlowNames(String[] names);
    
    /**
     * ���s����Ɩ��t���[�����擾����B<p>
     *
     * @return �Ɩ��t���[��
     */
    public String[] getBeanFlowNames();
    
    /**
     * ���s����Ɩ��t���[�ւ̓��̓I�u�W�F�N�g��ݒ肷��B<p>
     *
     * @param in �Ɩ��t���[�ւ̓��̓I�u�W�F�N�g
     */
    public void setBeanFlowInputs(Object[] in);
    
    /**
     * ���s����Ɩ��t���[�ւ̓��̓I�u�W�F�N�g���擾����B<p>
     *
     * @return �Ɩ��t���[�ւ̓��̓I�u�W�F�N�g
     */
    public Object[] getBeanFlowInputs();
    
    /**
     * IOC�Ăяo����ʂ�ݒ肷��B<p>
     * �f�t�H���g�ł́A{@link #IOC_CALL_TYPE_SYNCH}�B<br>
     *
     * @param type IOC�Ăяo����ʃL�[
     * @see #IOC_CALL_TYPE_SYNCH
     * @see #IOC_CALL_TYPE_SYNCH_PARALLEL
     * @see #IOC_CALL_TYPE_SYNCH_SEQUENCE
     * @see #IOC_CALL_TYPE_ASYNCH
     * @see #IOC_CALL_TYPE_ASYNCH_SEQUENCE
     */
    public void setIOCCallType(String type);
    
    /**
     * IOC�Ăяo����ʂ��擾����B<p>
     *
     * @return IOC�Ăяo����ʃL�[
     */
    public String getIOCCallType();
}