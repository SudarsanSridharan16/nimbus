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
package jp.ossc.nimbus.beans;

import java.beans.*;

import jp.ossc.nimbus.core.*;

/**
 * {@link ServiceNameRef}�^��PropertyEditor�N���X�B<p>
 * "[�T�[�r�X�̃G�C���A�X��]=[�T�[�r�X���o�^�����}�l�[�W����]#[�T�[�r�X��]"�̕������{@link ServiceNameRef}�^�̃I�u�W�F�N�g�ɕϊ�����B<br>
 * "="�̉E�ӂ̒l�̕ϊ��́A{@link ServiceNameEditor}�ɈϏ������B���̍ہA{@link #setServiceManagerName(String)}�Őݒ肳�ꂽ�}�l�[�W�������A{ServiceNameEditor#setServiceManagerName(String)}��ServiceNameEditor�ɐݒ肳���B<br>
 * "${"��"}"�Ɉ͂܂ꂽ������́A�����̃V�X�e���v���p�e�B�ƒu�������B<br>
 * <p>
 * ��1�F<br>
 * &nbsp;&nbsp;Ref=Manager#Service<br>
 * <br>
 * �̂悤�ȕ�����<br>
 * <br>
 * &nbsp;&nbsp;new ServiceNameRef("Ref", new ServiceName("Manager", "Service"))<br>
 * <br>
 * �̂悤�ɕϊ������B<br>
 * <p>
 * ��2�F<br>
 * &nbsp;&nbsp;Ref=Service<br>
 * <br>
 * �̂悤�ȕ�����<br>
 * <br>
 * &nbsp;&nbsp;new ServiceNameRef("Ref", new ServiceName("Service"))<br>
 * <br>
 * �̂悤�ɕϊ������B<br>
 * <br>
 * ��3�F<br>
 * &nbsp;&nbsp;Ref=#Service<br>
 * <br>
 * �̂悤�ȕ����񂪁A{@link #setServiceManagerName(String)}��"Manager"�Ɛݒ肵�Ă���΁A<br>
 * <br>
 * &nbsp;&nbsp;new ServiceNameRef("Ref", new ServiceName("Manager", "Service"))<br>
 * <br>
 * �̂悤�ɕϊ������B{@link #setServiceManagerName(String)}�Ń}�l�[�W�������ݒ肳��Ă��Ȃ��ꍇ�́A��O��throw����B<br>
 *
 * @author M.Takata
 */
public class ServiceNameRefEditor extends PropertyEditorSupport
 implements java.io.Serializable{
    
    private static final long serialVersionUID = -8308616186491317107L;
    
    private String managerName;
    
    /**
     * {@link ServiceManager}�̖��O���ȗ�����Ă���T�[�r�X���������{@link ServiceNameRef}�ɕϊ�����ꍇ�ɁA�g�p����ServiceManager�̖��O��ݒ肷��B<p>
     *
     * @param name ServiceManager�̖��O
     */
    public void setServiceManagerName(String name){
        managerName = name;
    }
    
    /**
     * �w�肳�ꂽ���������͂��ăv���p�e�B�l��ݒ肷��B<p>
     *
     * @param text ��͂���镶����
     */
    public void setAsText(String text){
        if(text == null){
            setValue(null);
            return;
        }
        final String tmpText = Utility.replaceSystemProperty(text);
        final int length = tmpText.length();
        if(tmpText == null || length <= 2){
            throw new IllegalArgumentException(tmpText);
        }
        final int index = tmpText.indexOf('=');
        if(index == -1 || index == 0 || index == length - 1){
            throw new IllegalArgumentException(tmpText);
        }
        final String refName = tmpText.substring(0, index);
        final String realName = tmpText.substring(index + 1);
        final ServiceNameEditor editor = new ServiceNameEditor();
        editor.setServiceManagerName(managerName);
        editor.setAsText(realName);
        final ServiceName serviceName = (ServiceName)editor.getValue();
        
        setValue(new ServiceNameRef(refName, serviceName));
    }
    
    /**
     * �v���p�e�B��������擾����B<p>
     *
     * @return �v���p�e�B������
     */
    public String getAsText(){
        final ServiceNameRef name = (ServiceNameRef)getValue();
        if(name == null){
            return null;
        }
        return name.toString();
    }
}
