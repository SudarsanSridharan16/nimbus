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
package jp.ossc.nimbus.service.interpreter;

import java.util.Map;

import jp.ossc.nimbus.core.*;

/**
 * {@link BeanShellInterpreterService}��MBean�C���^�t�F�[�X�B<p>
 * 
 * @author M.Takata
 */
public interface BeanShellInterpreterServiceMBean extends ServiceBaseMBean{
    
    /**
     * �C���^�[�v���^�ɁA�T�[�r�X�ϐ���ݒ肷��B<p>
     *
     * @param name �ϐ���
     * @param serviceName �T�[�r�X��
     */
    public void setVariableServiceName(String name, ServiceName serviceName);
    
    /**
     * �C���^�[�v���^�ɐݒ肷��T�[�r�X�ϐ����擾����B<p>
     *
     * @param name �ϐ���
     * @return �T�[�r�X��
     */
    public ServiceName getVariableServiceName(String name);
    
    /**
     * �C���^�[�v���^�ɁA�ϐ���ݒ肷��B<p>
     *
     * @param name �ϐ���
     * @param val �ϐ�
     */
    public void setVariableObject(String name, Object val);
    
    /**
     * �C���^�[�v���^�ɐݒ肷��ϐ����擾����B<p>
     *
     * @param name �ϐ���
     * @return �ϐ�
     */
    public Object getVariableObject(String name);
    
    /**
     * �C���^�[�v���^�ɁA�ϐ���ݒ肷��B<p>
     *
     * @param name �ϐ���
     * @param val �ϐ�
     */
    public void setVariableInt(String name, int val);
    
    /**
     * �C���^�[�v���^�ɐݒ肷��ϐ����擾����B<p>
     *
     * @param name �ϐ���
     * @return �ϐ�
     */
    public int getVariableInt(String name);
    
    /**
     * �C���^�[�v���^�ɁA�ϐ���ݒ肷��B<p>
     *
     * @param name �ϐ���
     * @param val �ϐ�
     */
    public void setVariableLong(String name, long val);
    
    /**
     * �C���^�[�v���^�ɐݒ肷��ϐ����擾����B<p>
     *
     * @param name �ϐ���
     * @return �ϐ�
     */
    public long getVariableLong(String name);
    
    /**
     * �C���^�[�v���^�ɁA�ϐ���ݒ肷��B<p>
     *
     * @param name �ϐ���
     * @param val �ϐ�
     */
    public void setVariableFloat(String name, float val);
    
    /**
     * �C���^�[�v���^�ɐݒ肷��ϐ����擾����B<p>
     *
     * @param name �ϐ���
     * @return �ϐ�
     */
    public float getVariableFloat(String name);
    
    /**
     * �C���^�[�v���^�ɁA�ϐ���ݒ肷��B<p>
     *
     * @param name �ϐ���
     * @param val �ϐ�
     */
    public void setVariableDouble(String name, double val);
    
    /**
     * �C���^�[�v���^�ɐݒ肷��ϐ����擾����B<p>
     *
     * @param name �ϐ���
     * @return �ϐ�
     */
    public double getVariableDouble(String name);
    
    /**
     * �C���^�[�v���^�ɁA�ϐ���ݒ肷��B<p>
     *
     * @param name �ϐ���
     * @param val �ϐ�
     */
    public void setVariableBoolean(String name, boolean val);
    
    /**
     * �C���^�[�v���^�ɐݒ肷��ϐ����擾����B<p>
     *
     * @param name �ϐ���
     * @return �ϐ�
     */
    public boolean getVariableBoolean(String name);
    
    /**
     * �C���^�[�v���^�ɐݒ肷��ϐ��}�b�v���擾����B<p>
     *
     * @return �ϐ��}�b�v
     */
    public Map getVariables();
    
    /**
     * �C���^�[�v���^���R�[�h��]������ۂɎg�p����N���X���[�_��ݒ肷��B<p>
     *
     * @param loader �N���X���[�_
     */
    public void setClassLoader(ClassLoader loader);
    
    /**
     * �C���^�[�v���^���R�[�h��]������ۂɎg�p����N���X���[�_���擾����B<p>
     *
     * @return �N���X���[�_
     */
    public ClassLoader getClassLoader();
    
    /**
     * �C���^�[�v���^�ɓǂݍ��܂������\�[�X�t�@�C������ݒ肷��B<p>
     * 
     * @param names �\�[�X�t�@�C����
     */
    public void setSourceFileNames(String[] names);
    
    /**
     * �C���^�[�v���^�ɓǂݍ��܂������\�[�X�t�@�C�������擾����B<p>
     * 
     * @return �\�[�X�t�@�C����
     */
    public String[] getSourceFileNames();
    
    /**
     * {@link Interpreter#evaluate(String)}�̂��тɃC���^�[�v���^�𐶐����邩�ǂ�����ݒ肷��B<p>
     * �f�t�H���g�́Atrue�Ŗ��񐶐�����B<br>
     * 
     * @param isNew ��������ꍇtrue
     */
    public void setNewInterpreterByEvaluate(boolean isNew);
    
    /**
     * {@link Interpreter#evaluate(String)}�̂��тɃC���^�[�v���^�𐶐����邩�ǂ����𔻒肷��B<p>
     * 
     * @return ��������ꍇtrue
     */
    public boolean isNewInterpreterByEvaluate();
    
    public Object evaluate(String code) throws EvaluateException;
}