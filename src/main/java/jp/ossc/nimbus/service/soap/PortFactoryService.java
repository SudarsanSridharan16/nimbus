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
package jp.ossc.nimbus.service.soap;

import java.util.*;

import javax.xml.namespace.QName;
import javax.xml.rpc.Service;
import javax.xml.rpc.Stub;
import javax.xml.rpc.handler.HandlerInfo;

import jp.ossc.nimbus.core.ServiceBase;
import jp.ossc.nimbus.core.ServiceManagerFactory;
import jp.ossc.nimbus.core.ServiceName;

/**
 * �|�[�g�t�@�N�g���[�T�[�r�X�B<p>
 *
 * @author M.Takata
 */
public class PortFactoryService extends ServiceBase
 implements PortFactory, PortFactoryServiceMBean {
    
    private static final long serialVersionUID = 7074638390846720787L;
    
    // �萔
    /** �|�[�g�G�C���A�X�v���p�e�B��؂蕶�� */
    private static final String SEPARATOR = ",";
    /** �|�[�g�� */
    private static final int PORT_NAME = 0;
    /** �T�[�r�X�G���h�|�C���g�C���^�[�t�F�[�X�� */
    private static final int ENDPOINT_INTERFACE_NAME = 1;
    
    // �����o�[�ϐ�
    /** JAX-RPC�T�[�r�X�t�@�N�g���[�� */
    private ServiceName jaxRpcServiceFactoryName;
    /** �l�[���X�y�[�X */
    private String nameSpace;
    /** JaxRpc�T�[�r�X */
    private Service jaxRpcService;
    private List handlerInfos;
    private Map stubProperties;
    
    /**
     * �|�[�g�G�C���A�X�v���p�e�B
     * �L�[��[�|�[�g�G�C���A�X��]�A�l��[�|�[�g��,�T�[�r�X�G���h�|�C���g�C���^�[�t�F�[�X��]�̃v���p�e�B
     */
    private Properties portAliasProp;
    
    public void createService() throws Exception {
        handlerInfos = new ArrayList();
        stubProperties = new HashMap();
    }
    
    public void startService() throws Exception {
        if (jaxRpcServiceFactoryName == null) {
            // JAX-RPC�T�[�r�X�t�@�N�g���[�����ݒ肳��Ă��Ȃ�
            throw new IllegalArgumentException("jaxRpcServiceFactoryName must be specified.");
        }
        if (portAliasProp == null) {
            // �|�[�g�G�C���A�X���v���p�e�B���ݒ肳��Ă��Ȃ�
            throw new IllegalArgumentException("portAliasProp must be specified.");
        }
        
        // JAX-RPC�T�[�r�X�t�@�N�g���[���擾
        JaxRpcServiceFactory jaxRpcServiceFactory =
            (JaxRpcServiceFactory)ServiceManagerFactory.getServiceObject(jaxRpcServiceFactoryName);
        // �l�[���X�y�[�X
        nameSpace = jaxRpcServiceFactory.getNameSpace();
        // JAX-RPC�T�[�r�X
        jaxRpcService = jaxRpcServiceFactory.getService();
    }
    
    public void stopService() throws Exception {
    }
    
    public void destroyService() throws Exception {
        nameSpace = null;
        jaxRpcService = null;
        handlerInfos = null;
        stubProperties = null;
    }
    
    // PortFactory��JavaDoc
    public Object getPort(String portAlias) throws PortException {
        // [�|�[�g��,�T�[�r�X�G���h�|�C���g�C���^�[�t�F�[�X��]���擾
        String portNameClassName = portAliasProp.getProperty(portAlias);
        // [�|�[�g��]��[�T�[�r�X�G���h�|�C���g�C���^�[�t�F�[�X��]�ɕ���
        String[] names = portNameClassName.split(SEPARATOR);
        
        if (names.length < 2) {
            return new PortException(
                "port name or endpoint interface name is illegal : " + portNameClassName
            );
        }
        
        try {
            // �T�[�r�X�G���h�|�C���g�C���^�[�t�F�[�X
            Class endpointInterface = Class.forName(names[ENDPOINT_INTERFACE_NAME]);
            QName portQN = new QName(nameSpace, names[PORT_NAME]);
            if(handlerInfos != null && handlerInfos.size() != 0){
                jaxRpcService.getHandlerRegistry().setHandlerChain(
                    portQN,
                    handlerInfos
                );
            }
            Object port = jaxRpcService.getPort(portQN, endpointInterface);
            if(stubProperties != null && stubProperties.size() != 0 && (port instanceof Stub)){
                Stub stub = (Stub)port;
                Iterator entries = stubProperties.entrySet().iterator();
                while(entries.hasNext()){
                    Map.Entry entry = (Map.Entry)entries.next();
                    stub._setProperty((String)entry.getKey(), entry.getValue());
                }
            }
            return port;
        } catch (Exception e) {
            throw new PortException(e);
        }
    }
    
    // PortFactoryServiceMBean��JavaDoc
    public void setHandlerInfos(List infos){
        handlerInfos = infos;
    }
    
    // PortFactoryServiceMBean��JavaDoc
    public void addHandlerInfo(HandlerInfo info){
        if(handlerInfos != null){
            handlerInfos.add(info);
        }
    }
    
    // PortFactoryServiceMBean��JavaDoc
    public List getHandlerInfos(){
        return handlerInfos;
    }
    
    // PortFactoryServiceMBean��JavaDoc
    public void clearHandlerInfos(){
        if(handlerInfos != null){
            handlerInfos.clear();
        }
    }
    
    // PortFactoryServiceMBean��JavaDoc
    public Properties getPortAliasProp() {
        return portAliasProp;
    }
    
    // PortFactoryServiceMBean��JavaDoc
    public void setPortAliasProp(Properties prop) {
        portAliasProp = prop;
    }
    
    // PortFactoryServiceMBean��JavaDoc
    public ServiceName getJaxRpcServiceFactoryName() {
        return jaxRpcServiceFactoryName;
    }
    
    // PortFactoryServiceMBean��JavaDoc
    public void setJaxRpcServiceFactoryName(ServiceName serviceName) {
        jaxRpcServiceFactoryName = serviceName;
    }
    
    // PortFactoryServiceMBean��JavaDoc
    public void setStubProperty(String name, Object value){
        stubProperties.put(name, value);
    }
    // PortFactoryServiceMBean��JavaDoc
    public Object getStubProperty(String name){
        return stubProperties == null ? null : stubProperties.get(name);
    }
    // PortFactoryServiceMBean��JavaDoc
    public Map getStubPropertyMap(){
        return stubProperties;
    }
}
