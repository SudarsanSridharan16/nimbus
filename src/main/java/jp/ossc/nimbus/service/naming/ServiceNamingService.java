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
package jp.ossc.nimbus.service.naming;

import java.io.*;
import java.util.*;

import jp.ossc.nimbus.core.*;

/**
 * �T�[�r�X�l�[�~���O�T�[�r�X�B<p>
 * �w�肵�����O�ɑΉ�����{@link Service}���擾����l�[�~���O�T�[�r�X�B<br>
 * �w�肳�ꂽ���O�ɑΉ�����T�[�r�X���o�^����Ă���{@link ServiceManager}��{@link ServiceManager#getService(String)}�Ŏ擾�����I�u�W�F�N�g��Ԃ��B<br>
 * �w�肳�ꂽ���O�ɑΉ�����T�[�r�X�̌����́A�ȉ��̏����ōs����B<br>
 * <ol>
 *   <li>{@link #setServiceNameReferences(ServiceNameRef[])}�Őݒ肳�ꂽ�Q�Ɩ��z��ɁA�w�肵�����O�Ɠ����Q�Ɩ�������΁A�Q�Ɩ����\���T�[�r�X���擾����B</li>
 *   <li>{@link #setBootServicePath(String[])}�Őݒ肳�ꂽ{@link ServiceManager}�̖��O�̔z�񂩂�A�Y������ServiceManager���擾���āA�w�肵�����O�̃T�[�r�X����������B</li>
 *   <li>�������g���o�^����Ă���ServiceManager���擾���āA�w�肵�����O�̃T�[�r�X����������B</li>
 *   <li>{@link #setServicePath(String[])}�Őݒ肳�ꂽ{@link ServiceManager}�̖��O�̔z�񂩂�A�Y������ServiceManager���擾���āA�w�肵�����O�̃T�[�r�X����������B</li>
 * </ol>
 * 
 * @author M.Takata
 */
public class ServiceNamingService extends ServiceBase
 implements ServiceNamingServiceMBean, Serializable{
    
    private static final long serialVersionUID = 3146925421831880202L;
    
    private String[] servicePath;
    private String[] bootServicePath;
    private ServiceNameRef[] serviceNameRefs;
    private Map nameRefMap = new HashMap();
    
    // ServiceNamingServiceMBean��JavaDoc
    public void setServicePath(String[] path){
        servicePath = path;
    }
    
    // ServiceNamingServiceMBean��JavaDoc
    public String[] getServicePath(){
        return servicePath;
    }
    
    // ServiceNamingServiceMBean��JavaDoc
    public void setBootServicePath(String[] path){
        bootServicePath = path;
    }
    
    // ServiceNamingServiceMBean��JavaDoc
    public String[] getBootServicePath(){
        return bootServicePath;
    }
    
    // ServiceNamingServiceMBean��JavaDoc
    public void setServiceNameReferences(ServiceNameRef[] refs){
        if(refs == null){
            return;
        }
        final Map map = new HashMap();
        for(int i = 0, max = refs.length; i < max; i++){
            map.put(
                refs[i].getReferenceServiceName(),
                refs[i].getServiceName()
            );
        }
        nameRefMap = map;
        serviceNameRefs = refs;
    }
    
    // ServiceNamingServiceMBean��JavaDoc
    public ServiceNameRef[] getServiceNameReferences(){
        return serviceNameRefs;
    }
    
    /**
     * �w�肳�ꂽ���O�ɑΉ�����{@link Service}���擾����B<p>
     * �w�肳�ꂽ���O�ɑΉ�����Service��������Ȃ��ꍇ�Anull��Ԃ��B<br>
     *
     * @param name ���O
     * @return �w�肵�����O�ɑΉ�����Service
     */
    public Object find(String name){
        if(name == null){
            return null;
        }
        if(nameRefMap.containsKey(name)){
            return findObject((ServiceName)nameRefMap.get(name));
        }
        Object obj = null;
        if(bootServicePath != null){
            for(int i = 0, max = bootServicePath.length; i < max; i++){
                obj = findObject(bootServicePath[i], name);
                if(obj != null){
                    return obj;
                }
            }
        }
        obj = findObject(getServiceManagerName(), name);
        if(obj != null){
            return obj;
        }
        if(servicePath != null){
            for(int i = 0, max = servicePath.length; i < max; i++){
                obj = findObject(servicePath[i], name);
                if(obj != null){
                    return obj;
                }
            }
        }
        return null;
    }
    
    /**
     * ���̃T�[�r�X�ŕԂ��I�u�W�F�N�g���擾����B<p>
     *
     * @param name ServiceName
     * @return {@link ServiceName}�ɑΉ�����I�u�W�F�N�g
     */
    protected Object findObject(ServiceName name){
        try{
            return ServiceManagerFactory.getService(name);
        }catch(ServiceNotFoundException e){
            return null;
        }
    }
    
    /**
     * ���̃T�[�r�X�ŕԂ��I�u�W�F�N�g���擾����B<p>
     *
     * @param manager {@link ServiceManager}�̖��O
     * @param name {@link Service}�̖��O
     * @return �����ɊY������I�u�W�F�N�g
     */
    protected Object findObject(String manager, String name){
        try{
            return ServiceManagerFactory.getService(manager, name);
        }catch(ServiceNotFoundException e){
            return null;
        }
    }
}