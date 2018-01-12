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
import jp.ossc.nimbus.beans.ServiceNameEditor;

/**
 * �L�[�}�b�s���O�X�P�W���[���t�@�N�g���B<p>
 *
 * @author M.Takata
 */
public class KeyMappingScheduleFactoryService extends ServiceBase
 implements KeyMappingScheduleFactoryServiceMBean, ScheduleFactory{
    
    private static final long serialVersionUID = -2802344763761233335L;
    
    protected String[] keyAndScheduleFactoryServiceName;
    protected Map attrKeyAndScheduleFactory;
    protected Map keyAndScheduleFactory;
    protected ServiceName defaultScheduleFactoryServiceName;
    protected ScheduleFactory defaultScheduleFactory;
    
    // KeyMappingScheduleFactoryServiceMBean��JavaDoc
    public void setKeyAndScheduleFactoryServiceName(String[] mapping){
        keyAndScheduleFactoryServiceName = mapping;
    }
    // KeyMappingScheduleFactoryServiceMBean��JavaDoc
    public String[] getKeyAndScheduleFactoryServiceName(){
        return keyAndScheduleFactoryServiceName;
    }
    
    // KeyMappingScheduleFactoryServiceMBean��JavaDoc
    public void setDefaultScheduleFactoryServiceName(ServiceName name){
        defaultScheduleFactoryServiceName = name;
    }
    // KeyMappingScheduleFactoryServiceMBean��JavaDoc
    public ServiceName getDefaultScheduleFactoryServiceName(){
        return defaultScheduleFactoryServiceName;
    }
    
    /**
     * �T�[�r�X�̐����������s���B<p>
     *
     * @exception Exception �T�[�r�X�̐��������Ɏ��s�����ꍇ
     */
    public void createService() throws Exception{
        keyAndScheduleFactory = new HashMap();
    }
    
    /**
     * �T�[�r�X�̊J�n�������s���B<p>
     *
     * @exception Exception �T�[�r�X�̊J�n�����Ɏ��s�����ꍇ
     */
    public void startService() throws Exception{
        if(keyAndScheduleFactoryServiceName != null){
            ServiceNameEditor editor = new ServiceNameEditor();
            editor.setServiceManagerName(getServiceManagerName());
            for(int i = 0; i < keyAndScheduleFactoryServiceName.length; i++){
                String tmp = keyAndScheduleFactoryServiceName[i];
                int index = tmp.indexOf('=');
                if(index == -1 || index == tmp.length() - 1){
                    throw new IllegalArgumentException(
                        "keyAndScheduleFactoryServiceName is \"key=ScheduleFactoryServiceName\"." + tmp
                    );
                }
                final String key = tmp.substring(0, index);
                final String nameStr = tmp.substring(index + 1);
                editor.setAsText(nameStr);
                ServiceName name = (ServiceName)editor.getValue();
                ScheduleFactory factory = (ScheduleFactory)ServiceManagerFactory
                    .getServiceObject(name);
                keyAndScheduleFactory.put(createKey(key), factory);
            }
        }
        if(attrKeyAndScheduleFactory != null){
            final Iterator keys = attrKeyAndScheduleFactory.keySet().iterator();
            while(keys.hasNext()){
                final Object key = keys.next();
                ScheduleFactory factory
                     = (ScheduleFactory)attrKeyAndScheduleFactory.get(key);
                keyAndScheduleFactory.put(createKey(key), factory);
            }
        }
        if(defaultScheduleFactoryServiceName != null){
            defaultScheduleFactory = (ScheduleFactory)ServiceManagerFactory
                .getServiceObject(defaultScheduleFactoryServiceName);
        }
    }
    
    /**
     * �T�[�r�X�̒�~�������s���B<p>
     *
     * @exception Exception �T�[�r�X�̒�~�����Ɏ��s�����ꍇ
     */
    public void stopService() throws Exception{
        keyAndScheduleFactory.clear();
    }
    
    /**
     * �T�[�r�X�̔j���������s���B<p>
     *
     * @exception Exception �T�[�r�X�̔j�������Ɏ��s�����ꍇ
     */
    public void destroyService() throws Exception{
        keyAndScheduleFactory = null;
    }
    
    /**
     * �ݒ肳�ꂽ�L�[�I�u�W�F�N�g���A�K�؂ȃL�[�I�u�W�F�N�g�ɕϊ�����B<p>
     * �ϊ������ɂ��̂܂ܕԂ��B<br>
     * �T�u�N���X�ŕK�v�ɉ����ăI�[�o�[���C�h���邱�ƁB<br>
     *
     * @param key �ݒ肳�ꂽ�L�[�I�u�W�F�N�g
     * @return �K�؂ȃL�[�I�u�W�F�N�g
     * @exception Exception �ϊ��Ɏ��s�����ꍇ
     */
    protected Object createKey(Object key) throws Exception{
        return key;
    }
    
    /**
     * �L�[��{@link ScheduleFactory}�T�[�r�X�̃}�b�s���O��ݒ肷��B<p>
     *
     * @param mapping �L�[��ScheduleFactory�T�[�r�X�̃}�b�s���O
     */
    public void setKeyAndScheduleFactory(Map mapping){
        attrKeyAndScheduleFactory = mapping;
    }
    
    /**
     * �L�[�ɊY������X�P�W���[�����擾����B<p>
     *
     * @param key �L�[
     * @return �X�P�W���[���z��
     */
    public Schedule[] getSchedules(Object key){
        ScheduleFactory factory
             = (ScheduleFactory)keyAndScheduleFactory.get(key);
        if(factory == null && defaultScheduleFactory != null){
            factory = defaultScheduleFactory;
        }
        return factory == null ? new Schedule[0] : factory.getSchedules(key);
    }
}