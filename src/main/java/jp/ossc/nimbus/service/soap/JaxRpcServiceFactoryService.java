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

import java.net.URL;
import java.net.MalformedURLException;
import java.io.File;
import java.util.*;

import javax.xml.namespace.QName;
import javax.xml.rpc.Service;
import javax.xml.rpc.ServiceFactory;
import javax.xml.rpc.encoding.TypeMappingRegistry;

import jp.ossc.nimbus.core.ServiceBase;
import jp.ossc.nimbus.core.ServiceMetaData;
import jp.ossc.nimbus.core.ServiceManagerFactory;

/**
 * JAX-RPC�T�[�r�X�t�@�N�g���[�T�[�r�X�B<p>
 *
 * @author M.Takata
 */
public class JaxRpcServiceFactoryService extends ServiceBase
  implements JaxRpcServiceFactory, JaxRpcServiceFactoryServiceMBean {
    
    private static final long serialVersionUID = -8319395042566600989L;
    
    // �����o�[�ϐ�
    /** �l�[���X�y�[�X */
    private String nameSpace;
    /** JAX-RPC�T�[�r�X�� */
    private String jaxRpcServiceName;
    /** WSDL URL */
    private URL wsdlURL;
    /** JAX-RPC�T�[�r�X */
    private ServiceFactory jaxRpcFactory;
    /** �^�}�b�s���O */
    private Map typeMappings;
    private String serviceFactoryClassName;
    private String wsdlPath;
    
    // ServiceFactoryServiceMBean��JavaDoc
    public URL getWsdlURL() {
        return wsdlURL;
    }
    
    // ServiceFactoryServiceMBean��JavaDoc
    public void setWsdlURL(URL url) {
        wsdlURL = url;
    }
    
    // ServiceFactoryServiceMBean��JavaDoc
    public String getWsdlPath() {
        return wsdlPath;
    }
    
    // ServiceFactoryServiceMBean��JavaDoc
    public void setWsdlPath(String path) {
        wsdlPath = path;
    }
    
    // ServiceFactoryServiceMBean��JavaDoc
    public void setJaxRpcServiceName(String jaxRpcServiceName) {
        this.jaxRpcServiceName = jaxRpcServiceName;
    }
    
    // ServiceFactoryServiceMBean��JavaDoc
    public void setNameSpace(String nameSpace) {
        this.nameSpace = nameSpace;
    }
    
    // ServiceFactoryServiceMBean��JavaDoc
    public void setTypeMapping(String encodingStyleURI, TypeMapping mapping){
        typeMappings.put(encodingStyleURI, mapping);
    }
    
    // ServiceFactoryServiceMBean��JavaDoc
    public TypeMapping getTypeMapping(String encodingStyleURI){
        return (TypeMapping)typeMappings.get(encodingStyleURI);
    }
    
    public void setServiceFactoryClassName(String name){
        serviceFactoryClassName = name;
    }
    public String getServiceFactoryClassName(){
        return serviceFactoryClassName;
    }
    
    public void createService() throws Exception {
        typeMappings = new HashMap();
    }
    
    public void startService() throws Exception {
        if (nameSpace == null) {
            // �l�[���X�y�[�X���ݒ肳��Ă��Ȃ��B
            throw new IllegalArgumentException("nameSpace must be specified.");
        }
        if (jaxRpcServiceName == null) {
            // JAX-RPC�T�[�r�X�����ݒ肳��Ă��Ȃ��B
            throw new IllegalArgumentException("jaxRpcServiceName must be specified.");
        }
        if(wsdlPath != null){
            URL url = null;
            File localFile = new File(wsdlPath);
            if(localFile.exists()){
                if(!localFile.isFile()){
                    throw new IllegalArgumentException(
                        "WsdlPath must be file : " + localFile
                    );
                }
                try{
                    wsdlURL = localFile.toURL();
                }catch(MalformedURLException e){
                    // ���̗�O�͔������Ȃ��͂�
                }
            }else{
                File serviceDefDir = null;
                if(getServiceNameObject() != null){
                    ServiceMetaData metaData = ServiceManagerFactory.getServiceMetaData(getServiceNameObject());
                    if(metaData != null){
                        jp.ossc.nimbus.core.ServiceLoader loader = metaData.getServiceLoader();
                        if(loader != null){
                            String filePath = loader.getServiceURL().getFile();
                            if(filePath != null){
                                serviceDefDir = new File(filePath).getParentFile();
                            }
                        }
                    }
                }
                localFile = new File(serviceDefDir, wsdlPath);
                if(localFile.exists()){
                    if(!localFile.isFile()){
                        throw new IllegalArgumentException(
                            "WsdlPath must be file : " + localFile
                        );
                    }
                    try{
                        wsdlURL = localFile.toURL();
                    }catch(MalformedURLException e){
                        // ���̗�O�͔������Ȃ��͂�
                    }
                }else{
                    
                    final ClassLoader classLoader
                         = Thread.currentThread().getContextClassLoader();
                    final URL resource = classLoader.getResource(wsdlPath);
                    if(resource != null){
                        wsdlURL = resource;
                    }
                }
            }
            
            if (wsdlURL == null) {
                // WSDL��URL���ݒ肳��Ă��Ȃ��B
                throw new IllegalArgumentException("WsdlPath could not find. path=" + wsdlURL);
            }
        }
        
        if (wsdlURL == null) {
            // WSDL��URL���ݒ肳��Ă��Ȃ��B
            throw new IllegalArgumentException("wsdlURL must be specified.");
        }
        
        if(serviceFactoryClassName != null){
            System.setProperty(ServiceFactory.SERVICEFACTORY_PROPERTY, serviceFactoryClassName);
        }
        
        // JAX-RPC�T�[�r�X�t�@�N�g���[ 
        jaxRpcFactory = ServiceFactory.newInstance();
        
        getService();
    }
    
    public void stopService() throws Exception {
    }
    
    public void destroyService() throws Exception {
        jaxRpcFactory = null;
        typeMappings = null;
    }
    
    // ServiceFactory��JavaDoc
    public Service getService() throws JaxRpcServiceException {
        
        final QName serviceQN = new QName(nameSpace, jaxRpcServiceName);
        
        // JAX-RPC�T�[�r�X����
        Service jaxRpcService = null;
        try{
            jaxRpcService = jaxRpcFactory.createService(
                wsdlURL,
                serviceQN
            );
        }catch(javax.xml.rpc.ServiceException e){
            throw new JaxRpcServiceException(e);
        }
        
        if(typeMappings.size() != 0){
            final TypeMappingRegistry registry
                 = jaxRpcService.getTypeMappingRegistry();
            final Iterator keys = typeMappings.keySet().iterator();
            while(keys.hasNext()){
                final String encodingStyleURI = (String)keys.next();
                final TypeMapping mapping
                     = (TypeMapping)typeMappings.get(encodingStyleURI);
                registry.register(
                    encodingStyleURI,
                    mapping.cloneTypeMapping(registry, encodingStyleURI)
                );
            }
        }
        
        return jaxRpcService;
    }
    
    // ServiceFactory��JavaDoc
    public String getJaxRpcServiceName() {
        return jaxRpcServiceName;
    }
    
    // ServiceFactory��JavaDoc
    public String getNameSpace() {
        return nameSpace;
    }
}
