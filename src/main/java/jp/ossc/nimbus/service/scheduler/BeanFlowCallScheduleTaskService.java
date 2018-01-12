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
import jp.ossc.nimbus.service.beancontrol.interfaces.*;

/**
 * BeanFlow�Ăяo���X�P�W���[���^�X�N�B<p>
 *
 * @author M.Takata
 */
public class BeanFlowCallScheduleTaskService extends ServiceBase
 implements ScheduleTask, BeanFlowCallScheduleTaskServiceMBean{
    
    private static final long serialVersionUID = 7633274480901701353L;
    
    /**
     * {@link BeanFlowInvokerFactory}�T�[�r�X�̃T�[�r�X���B<p>
     */
    protected ServiceName beanFlowInvokerFactoryServiceName;
    
    /**
     * {@link BeanFlowInvokerFactory}�B<p>
     */
    protected BeanFlowInvokerFactory beanFlowInvokerFactory;
    
    /**
     * ���s����Ɩ��t���[���B<p>
     */
    protected String beanFlowName;
    
    /**
     * ���s����Ɩ��t���[�ɓn�����̓I�u�W�F�N�g�B<p>
     */
    protected Object beanFlowInput;
    
    // BeanFlowCallScheduleTaskServiceMBean��JavaDoc
    public void setBeanFlowInvokerFactoryServiceName(ServiceName name){
        beanFlowInvokerFactoryServiceName = name;
    }
    // BeanFlowCallScheduleTaskServiceMBean��JavaDoc
    public ServiceName getBeanFlowInvokerFactoryServiceName(){
        return beanFlowInvokerFactoryServiceName;
    }
    
    // BeanFlowCallScheduleTaskServiceMBean��JavaDoc
    public void setBeanFlowName(String name){
        beanFlowName = name;
    }
    // BeanFlowCallScheduleTaskServiceMBean��JavaDoc
    public String getBeanFlowName(){
        return beanFlowName;
    }
    
    // BeanFlowCallScheduleTaskServiceMBean��JavaDoc
    public void setBeanFlowInput(Object in){
        beanFlowInput = in;
    }
    // BeanFlowCallScheduleTaskServiceMBean��JavaDoc
    public Object getBeanFlowInput(){
        return beanFlowInput;
    }
    
    /**
     * �T�[�r�X�̊J�n�������s���B<p>
     *
     * @exception Exception �T�[�r�X�̊J�n�����Ɏ��s�����ꍇ
     */
    public void startService() throws Exception{
        if(beanFlowInvokerFactoryServiceName != null){
            beanFlowInvokerFactory
                 = (BeanFlowInvokerFactory)ServiceManagerFactory
                    .getServiceObject(beanFlowInvokerFactoryServiceName);
        }
        if(beanFlowInvokerFactory == null){
            throw new IllegalArgumentException(
                "beanFlowInvokerFactory is null"
            );
        }
        if(beanFlowName == null){
            throw new IllegalArgumentException(
                "beanFlowName must be specified."
            );
        }
    }
    
    /**
     * {@link BeanFlowInvokerFactory}��ݒ肷��B<p>
     *
     * @param factory BeanFlowInvokerFactory
     */
    public void setBeanFlowInvokerFactory(BeanFlowInvokerFactory factory){
        beanFlowInvokerFactory = factory;
    }
    
    // ScheduleTask��JavaDoc
    public void run() throws Exception{
        final BeanFlowInvoker flowInvoker = beanFlowInvokerFactory
            .createFlow(beanFlowName);
        flowInvoker.invokeFlow(beanFlowInput);
    }
}