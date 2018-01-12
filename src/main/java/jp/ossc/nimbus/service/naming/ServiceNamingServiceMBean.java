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

import jp.ossc.nimbus.core.*;

/**
 * {@link ServiceNamingService}��MBean�C���^�t�F�[�X<p>
 * 
 * @author M.Takata
 * @see ServiceNamingService
 */
public interface ServiceNamingServiceMBean extends Service, Naming{
    
    /**
     * {@link jp.ossc.nimbus.core.Service Service}�̌����p�X��ݒ肷��B<p>
     * �����Ŏw�肷�镶����z��ɂ́A{@link jp.ossc.nimbus.core.ServiceManager ServiceManager}��\��&lt;manager&gt;�v�f�̖��O�z����w�肷��B�������g���o�^����Ă���ServiceManager����Service�̎擾�����݂���A�w�肵�����O�z��̏��ŁA�Y������ServiceManager����Service�̎擾�����݂�B�������g���o�^����Ă���ServiceManager�����D�悵�Č���������ServiceManager������ꍇ�́A{@link #setBootServicePath(String[])}�Őݒ肷��K�v������B<br>
     *
     * @param path &lt;manager&gt;�v�f�̖��O�z��
     * @see #getServicePath()
     * @see #setBootServicePath(String[])
     */
    public void setServicePath(String[] path);
    
    /**
     * {@link jp.ossc.nimbus.core.Service Service}�̌����p�X���擾����B<p>
     * 
     * @return Service�̌����p�X�ƂȂ�&lt;manager&gt;�v�f�̖��O�z��
     * @see #setServicePath(String[])
     */
    public String[] getServicePath();
    
    /**
     * {@link jp.ossc.nimbus.core.Service Service}�̃u�[�g�����p�X��ݒ肷��B<p>
     * �����Ŏw�肷�镶����z��ɂ́A{@link jp.ossc.nimbus.core.ServiceManager ServiceManager}��\��&lt;manager&gt;�v�f�̖��O�z����w�肷��B�w�肵�����O�z��̏��ŁA�Y������ServiceManager����Service�̎擾�����݂�B���̌�A�������g���o�^����Ă���ServiceManager����Service�̎擾�����݂�B�������g���o�^����Ă���ServiceManager�̌�Ɍ���������ServiceManager������ꍇ�́A{@link #setServicePath(String[])}�Őݒ肷��K�v������B<br>
     *
     * @param path &lt;manager&gt;�v�f�̖��O�z��
     * @see #getBootServicePath()
     * @see #setServicePath(String[])
     */
    public void setBootServicePath(String[] path);
    
    /**
     * {@link jp.ossc.nimbus.core.Service Service}�̃u�[�g�����p�X���擾����B<p>
     *
     * @return Service�̌����p�X�ƂȂ�&lt;manager&gt;�v�f�̖��O�z��
     * @see #setBootServicePath(String[])
     */
    public String[] getBootServicePath();
    
    /**
     * {@link jp.ossc.nimbus.core.Service Service}���Q�Ɩ��ŎQ�Ƃ���{@link ServiceNameRef}�̔z���ݒ肷��B<p>
     * ServiceNameRef��&lt;attribute&gt;�v�f�Ŏw�肷��ɂ́A�ȉ��̃t�H�[�}�b�g�Ŏw�肷��B<br>
     * <pre>
     * [�Q�Ɩ�]=[&lt;manager&gt;�v�f��name�����̒l]#[&lt;service&gt;�v�f��name�����̒l]
     * </pre>
     * '='�̉E���̕�����́AServiceName�̎w����@�ɏ�������B<br>
     * �X�ɁA�z��̎w��́A���s���ď�L�̎w��𕡐��s���B<br>
     * ���̎w��ɂ���āA{@link #find(String)}�ɎQ�Ɩ����w�肷�鎖�ŁA�Ή�����Service���擾���鎖���ł���B<br>
     *
     * @param refs ServiceNameRef�̔z��
     * @see #getServiceNameReferences()
     */
    public void setServiceNameReferences(ServiceNameRef[] refs);
    
    /**
     * {@link jp.ossc.nimbus.core.Service Service}���Q�Ɩ��ŎQ�Ƃ���{@link ServiceNameRef}�̔z����擾����B<p>
     *
     * @return ServiceNameRef�̔z��
     * @see #setServiceNameReferences(ServiceNameRef[])
     */
    public ServiceNameRef[] getServiceNameReferences();
}