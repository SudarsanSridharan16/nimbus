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
package jp.ossc.nimbus.core;

import java.io.*;
import org.w3c.dom.*;

/**
 * �T�[�r�X�Q�ƒ�`&lt;service-ref&gt;�v�f���^�f�[�^�B<p>
 * �T�[�r�X��`�t�@�C����&lt;service-ref&gt;�v�f�ɋL�q���ꂽ���e���i�[���郁�^�f�[�^�R���e�i�ł���B<p>
 *
 * @author M.Takata
 * @see <a href="nimbus-service_1_0.dtd">�T�[�r�X��`�t�@�C��DTD</a>
 */
public class ServiceRefMetaData extends ServiceNameMetaData
 implements Serializable{
    
    private static final long serialVersionUID = -5823860625416503269L;
    
    /**
     * &lt;service-ref&gt;�v�f�̗v�f��������B<p>
     */
    public static final String SERIVCE_REF_TAG_NAME = "service-ref";
    
    /**
     * �e�v�f�̃��^�f�[�^�����C���X�^���X�𐶐�����B<p>
     * 
     * @param parent �e�v�f�̃��^�f�[�^
     */
    public ServiceRefMetaData(MetaData parent){
        this(parent, null);
    }
    
    /**
     * �e�v�f�̃��^�f�[�^�����C���X�^���X�𐶐�����B<p>
     * 
     * @param parent �e�v�f�̃��^�f�[�^
     * @param manager �T�[�r�X���o�^�����{@link ServiceManager}�̖��O
     */
    public ServiceRefMetaData(MetaData parent, String manager){
        this(parent, manager, null);
    }
    
    /**
     * �e�v�f�̃��^�f�[�^�����C���X�^���X�𐶐�����B<p>
     * 
     * @param parent �e�v�f�̃��^�f�[�^
     * @param manager �T�[�r�X���o�^�����{@link ServiceManager}�̖��O
     * @param service �T�[�r�X�̖��O
     */
    public ServiceRefMetaData(MetaData parent, String manager, String service){
        super(parent, SERIVCE_REF_TAG_NAME, manager, service);
    }
    
    /**
     * �T�[�r�X����\���v�f��Element���p�[�X���āA�������g�̏��������s���B<p>
     *
     * @param element �T�[�r�X����\���v�f��Element
     * @exception DeploymentException �T�[�r�X����\���v�f�̉�͂Ɏ��s�����ꍇ
     */
    public void importXML(Element element) throws DeploymentException{
        
        if(!element.getTagName().equals(SERIVCE_REF_TAG_NAME)){
            throw new DeploymentException(
                "Tag must be " + SERIVCE_REF_TAG_NAME + " : "
                 + element.getTagName()
            );
        }
        super.importXML(element);
    }
    
    /**
     * ���̃C���X�^���X�̕�����\�����擾����B<p>
     *
     * @return ������\��
     */
    public String toString(){
        final StringBuffer buf = new StringBuffer();
        buf.append(super.toString());
        buf.append('{');
        if(managerName != null){
            buf.append(managerName);
        }
        buf.append('#');
        buf.append(serviceName);
        buf.append('}');
        return buf.toString();
    }
}