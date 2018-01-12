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

import jp.ossc.nimbus.core.ServiceBaseMBean;

/**
 * {@link JaxRpcServiceFactoryService}��MBean�C���^�t�F�[�X�B<p>
 *
 * @author M.Takata
 */
public interface JaxRpcServiceFactoryServiceMBean extends ServiceBaseMBean {
    
    /**
     * WSDL URL���擾����B<p>
     * 
     * @return WSDL URL
     */
    public URL getWsdlURL();
    
    /**
     * WSDL URL��ݒ肷��B<p>
     * 
     * @param url WSDL��URL
     */
    public void setWsdlURL(URL url);
    
    /**
     * WSDL�t�@�C���̃p�X���擾����B<p>
     * 
     * @return WSDL�t�@�C���̃p�X
     */
    public String getWsdlPath();
    
    /**
     * WSDL�t�@�C���̃p�X��ݒ肷��B<p>
     * 
     * @param path WSDL�t�@�C���̃p�X
     */
    public void setWsdlPath(String path);
    
    /**
     * JAX-RPC�T�[�r�X�����擾����B
     * 
     * @return JAX-RPC�T�[�r�X��
     */
    public String getJaxRpcServiceName();

    /**
     * JAX-RPC�T�[�r�X����ݒ肷��B<p>
     * 
     * @param jaxRpcServiceName JAX-RPC�T�[�r�X��
     */
    public void setJaxRpcServiceName(String jaxRpcServiceName);
    
    /**
     * �l�[���X�y�[�X�����擾����B<p>
     * 
     * @return �l�[���X�y�[�X��
     */
    public String getNameSpace();
    
    /**
     * �l�[���X�y�[�X��ݒ肷��B<p>
     * 
     * @param nameSpace �l�[���X�y�[�X
     */
    public void setNameSpace(String nameSpace);
    
    /**
     * JAX-RPC�T�[�r�X�ɓo�^����^�}�b�s���O��ݒ肷��B<p>
     *
     * @param encodingStyleURI �G���R�[�h����肷��URI
     * @param mapping �^�}�b�s���O
     */
    public void setTypeMapping(String encodingStyleURI, TypeMapping mapping);
    
    /**
     * �w�肵��URI�œ��肳���JAX-RPC�T�[�r�X�ɓo�^����^�}�b�s���O���擾����B<p>
     *
     * @param encodingStyleURI �G���R�[�h����肷��URI
     * @return �^�}�b�s���O
     */
    public TypeMapping getTypeMapping(String encodingStyleURI);
    
    public void setServiceFactoryClassName(String name);
    public String getServiceFactoryClassName();
}
