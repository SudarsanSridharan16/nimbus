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

import java.util.*;

import jp.ossc.nimbus.core.*;
import jp.ossc.nimbus.ioc.*;
import jp.ossc.nimbus.service.ioccall.FacadeCaller;

/**
 * IOC�Ăяo���X�P�W���[���^�X�N�B<p>
 *
 * @author M.Takata
 */
public class IOCCallScheduleTaskService extends ServiceBase
 implements ScheduleTask, IOCCallScheduleTaskServiceMBean{
    
    private static final long serialVersionUID = 2146486759345788960L;
    
    /**
     * IOC�Ăяo����� �����ꊇ�B<p>
     */
    protected static final int IOC_CALL_TYPE_SYNCH_VAL = 1;
    /**
     * IOC�Ăяo����� ��������B<p>
     */
    protected static final int IOC_CALL_TYPE_SYNCH_PARALLEL_VAL = 2;
    /**
     * IOC�Ăяo����� ��������B<p>
     */
    protected static final int IOC_CALL_TYPE_SYNCH_SEQUENCE_VAL = 3;
    /**
     * IOC�Ăяo����� �񓯊��ꊇ�B<p>
     */
    protected static final int IOC_CALL_TYPE_ASYNCH_VAL = 4;
    /**
     * IOC�Ăяo����� �񓯊�����B<p>
     */
    protected static final int IOC_CALL_TYPE_ASYNCH_SEQUENCE_VAL = 5;
    
    /**
     * {@link FacadeCaller}�T�[�r�X�̃T�[�r�X���B<p>
     */
    protected ServiceName facadeCallerServiceName;
    
    /**
     * {@link FacadeCaller}�B<p>
     */
    protected FacadeCaller facadeCaller;
    
    /**
     * ���s����Ɩ��t���[���z��B<p>
     */
    protected String[] beanFlowNames;
    
    /**
     * ���s����Ɩ��t���[�ɓn�����̓I�u�W�F�N�g�z��B<p>
     */
    protected Object[] beanFlowInputs;
    
    /**
     * IOC�Ăяo����ʕ�����B<p>
     */
    protected String iocCallType = IOC_CALL_TYPE_SYNCH;
    
    /**
     * IOC�Ăяo����ʁB<p>
     */
    protected int iocCallTypeValue = IOC_CALL_TYPE_SYNCH_VAL;
    
    // IOCCallScheduleTaskServiceMBean��JavaDoc
    public void setFacadeCallerServiceName(ServiceName name){
        facadeCallerServiceName = name;
    }
    // IOCCallScheduleTaskServiceMBean��JavaDoc
    public ServiceName getFacadeCallerServiceName(){
        return facadeCallerServiceName;
    }
    
    // IOCCallScheduleTaskServiceMBean��JavaDoc
    public void setBeanFlowNames(String[] names){
        beanFlowNames = names;
    }
    // IOCCallScheduleTaskServiceMBean��JavaDoc
    public String[] getBeanFlowNames(){
        return beanFlowNames;
    }
    
    // IOCCallScheduleTaskServiceMBean��JavaDoc
    public void setBeanFlowInputs(Object[] in){
        beanFlowInputs = in;
    }
    // IOCCallScheduleTaskServiceMBean��JavaDoc
    public Object[] getBeanFlowInputs(){
        return beanFlowInputs;
    }
    
    // IOCCallScheduleTaskServiceMBean��JavaDoc
    public void setIOCCallType(String type){
        if(IOC_CALL_TYPE_SYNCH.equals(type)){
            iocCallTypeValue = IOC_CALL_TYPE_SYNCH_VAL;
        }else if(IOC_CALL_TYPE_SYNCH_PARALLEL.equals(type)){
            iocCallTypeValue = IOC_CALL_TYPE_SYNCH_PARALLEL_VAL;
        }else if(IOC_CALL_TYPE_SYNCH_SEQUENCE.equals(type)){
            iocCallTypeValue = IOC_CALL_TYPE_SYNCH_SEQUENCE_VAL;
        }else if(IOC_CALL_TYPE_ASYNCH.equals(type)){
            iocCallTypeValue = IOC_CALL_TYPE_ASYNCH_VAL;
        }else if(IOC_CALL_TYPE_ASYNCH_SEQUENCE.equals(type)){
            iocCallTypeValue = IOC_CALL_TYPE_ASYNCH_SEQUENCE_VAL;
        }else{
            throw new IllegalArgumentException(
                "Illegal IOCCallType : " + type
            );
        }
        iocCallType = type;
    }
    // IOCCallScheduleTaskServiceMBean��JavaDoc
    public String getIOCCallType(){
        return iocCallType;
    }
    
    /**
     * �T�[�r�X�̊J�n�������s���B<p>
     *
     * @exception Exception �T�[�r�X�̊J�n�����Ɏ��s�����ꍇ
     */
    public void startService() throws Exception{
        if(facadeCallerServiceName != null){
            facadeCaller
                 = (FacadeCaller)ServiceManagerFactory
                    .getServiceObject(facadeCallerServiceName);
        }
        if(facadeCaller == null){
            throw new IllegalArgumentException(
                "facadeCaller is null"
            );
        }
        if(beanFlowNames == null || beanFlowNames.length == 0){
            throw new IllegalArgumentException(
                "beanFlowNames must be specified."
            );
        }
        if(beanFlowInputs != null
             && beanFlowNames.length != beanFlowInputs.length){
            throw new IllegalArgumentException(
                "beanFlowInputs is illegal."
            );
        }
    }
    
    /**
     * {@link FacadeCaller}��ݒ肷��B<p>
     *
     * @param caller FacadeCaller
     */
    public void setFacadeCaller(FacadeCaller caller){
        facadeCaller = caller;
    }
    
    // ScheduleTask��JavaDoc
    public void run() throws Exception{
        
        try{
            boolean isSynch = false;
            boolean isSequence = false;
            boolean isSynchParallel = false;
            switch(iocCallTypeValue){
            case IOC_CALL_TYPE_SYNCH_VAL:
                isSynch = true;
                isSequence = false;
                break;
            case IOC_CALL_TYPE_ASYNCH_VAL:
                isSynch = false;
                isSequence = false;
                break;
            case IOC_CALL_TYPE_SYNCH_SEQUENCE_VAL:
                isSynch = true;
                isSequence = true;
                break;
            case IOC_CALL_TYPE_ASYNCH_SEQUENCE_VAL:
                isSynch = false;
                isSequence = true;
                break;
            case IOC_CALL_TYPE_SYNCH_PARALLEL_VAL:
                isSynch = true;
                isSequence = false;
                isSynchParallel = true;
                break;
            default:
            }
            
            FacadeValue fv = null;
            List fvs = null;
            for(int i = 0; i < beanFlowNames.length; i++){
                if(fv == null || isSequence || isSynchParallel){
                    fv = FacadeValueAccess.createCommandsValue();
                }
                final Command command = FacadeValueAccess.createCommand(
                    beanFlowNames[i],
                    beanFlowInputs == null ? null : beanFlowInputs[i]
                );
                fv.addCommand(command);
                if(isSynchParallel){
                    if(fvs == null){
                        fvs = new ArrayList();
                    }
                    fvs.add(fv);
                }
                if(isSequence){
                    if(isSynch){
                        FacadeValue ret = facadeCaller.syncFacadeCall(fv);
                        if(ret != null
                             && ret.getStatus() == CommandBase.C_STATUS_ERROR){
                            throw ret.getExceptions()[0];
                        }
                    }else{
                        facadeCaller.unsyncFacadeCall(fv);
                    }
                }
            }
            if((fv != null || fvs != null) && !isSequence){
                if(isSynch){
                    if(isSynchParallel){
                        final FacadeValue[] vals = (FacadeValue[])fvs
                            .toArray(new FacadeValue[fvs.size()]);
                        FacadeValue[] rets
                             = facadeCaller.syncParallelFacadeCall(vals);
                        if(rets != null){
                            for(int i = 0; i < rets.length; i++){
                                final FacadeValue ret = rets[i];
                                if(ret != null
                                     && ret.getStatus()
                                             == CommandBase.C_STATUS_ERROR
                                ){
                                    throw ret.getExceptions()[0];
                                }
                            }
                        }
                    }else{
                        FacadeValue ret = facadeCaller.syncFacadeCall(fv);
                        if(ret != null
                             && ret.getStatus() == CommandBase.C_STATUS_ERROR){
                            throw ret.getExceptions()[0];
                        }
                    }
                }else{
                    facadeCaller.unsyncFacadeCall(fv);
                }
            }
        }catch(Throwable th){
            if(th instanceof Exception){
                throw (Exception)th;
            }else{
                throw (Error)th;
            }
        }
    }
}