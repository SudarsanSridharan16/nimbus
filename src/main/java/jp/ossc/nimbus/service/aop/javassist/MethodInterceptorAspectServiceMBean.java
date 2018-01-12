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
package jp.ossc.nimbus.service.aop.javassist;

import jp.ossc.nimbus.core.*;

/**
 * {@link MethodInterceptorAspectService}��MBean�C���^�t�F�[�X<p>
 * 
 * @author M.Takata
 * @see MethodInterceptorAspectService
 */
public interface MethodInterceptorAspectServiceMBean extends ServiceBaseMBean{
    
    /**
     * Interceptor������Ώۂ̃N���X�̃N���X�C���q���w�肷��B<p>
     * �����Ŏw�肳�ꂽ�N���X�C���q�̃N���X��Interceptor������ΏۂƂȂ�B�w�肵�Ȃ��ꍇ�́A�N���X�C���q�͌��肳��Ȃ��B<br>
     * �C���q��ے肷��ꍇ�́A�e�C���q�̑O��"!"��t�^���邱�ƁB
     *
     * @param modifiers �N���X�C���q������
     */
    public void setTargetClassModifiers(String modifiers);
    
    /**
     * Interceptor������Ώۂ̃N���X�̃N���X�C���q���擾����B<p>
     *
     * @return �N���X�C���q������
     */
    public String getTargetClassModifiers();
    
    /**
     * Interceptor������Ώۂ̃N���X�����w�肷��B<p>
     * �����Ŏw�肳�ꂽ�N���X���̃N���X��Interceptor������ΏۂƂȂ�B�w�肵�Ȃ��ꍇ�́A�N���X���͌��肳��Ȃ��B�܂��A���K�\�����w�肷�鎖���\�ł���B
     *
     * @param name �p�b�P�[�W�����܂ފ��S�C���N���X���B���K�\�����B
     */
    public void setTargetClassName(String name);
    
    /**
     * Interceptor������Ώۂ̃N���X�����擾����B<p>
     *
     * @return �p�b�P�[�W�����܂ފ��S�C���N���X���B���K�\�����B
     */
    public String getTargetClassName();
    
    /**
     * Interceptor�����Ȃ��Ώۂ̃N���X�����w�肷��B<p>
     * �����Ŏw�肳�ꂽ�N���X���̃N���X��Interceptor������ΏۂƂȂ�Ȃ��B�܂��A���K�\�����w�肷�鎖���\�ł���B
     *
     * @param name �p�b�P�[�W�����܂ފ��S�C���N���X���B���K�\�����B
     */
    public void setNoTargetClassName(String name);
    
    /**
     * Interceptor�����Ȃ��Ώۂ̃N���X�����擾����B<p>
     *
     * @return �p�b�P�[�W�����܂ފ��S�C���N���X���B���K�\�����B
     */
    public String getNoTargetClassName();
    
    /**
     * Interceptor������Ώۂ̃C���X�^���X�̃N���X�����w�肷��B<p>
     * �����Ŏw�肳�ꂽ�N���X���̃C���X�^���X��Interceptor������ΏۂƂȂ�B�w�肵�Ȃ��ꍇ�́A�C���X�^���X�͌��肳��Ȃ��B
     *
     * @param name �p�b�P�[�W�����܂ފ��S�C���N���X��
     */
    public void setTargetInstanceClassName(String name);
    
    /**
     * Interceptor������Ώۂ̃C���X�^���X�̃N���X�����擾����B<p>
     *
     * @return �p�b�P�[�W�����܂ފ��S�C���N���X���B
     */
    public String getTargetInstanceClassName();
    
    /**
     * Interceptor������Ώۂ̃��\�b�h�̃��\�b�h�C���q���w�肷��B<p>
     * �����Ŏw�肳�ꂽ���\�b�h�C���q�̃��\�b�h��Interceptor������ΏۂƂȂ�B�w�肵�Ȃ��ꍇ�́A���\�b�h�C���q�͌��肳��Ȃ��B<br>
     * �C���q��ے肷��ꍇ�́A�e�C���q�̑O��"!"��t�^���邱�ƁB
     *
     * @param modifiers ���\�b�h�C���q������
     */
    public void setTargetMethodModifiers(String modifiers);
    
    /**
     * Interceptor������Ώۂ̃��\�b�h�̃��\�b�h�C���q���擾����B<p>
     *
     * @return ���\�b�h�C���q������
     */
    public String getTargetMethodModifiers();
    
    /**
     * Interceptor������Ώۂ̃��\�b�h�����w�肷��B<p>
     * �����Ŏw�肳�ꂽ���\�b�h���̃��\�b�h��Interceptor������ΏۂƂȂ�B�w�肵�Ȃ��ꍇ�́A���\�b�h���͌��肳��Ȃ��B�܂��A���K�\�����w�肷�鎖���\�ł���B
     *
     * @param name ���\�b�h���B���K�\�����B
     */
    public void setTargetMethodName(String name);
    
    /**
     * Interceptor������Ώۂ̃��\�b�h�����擾����B<p>
     *
     * @return ���\�b�h���B���K�\�����B
     */
    public String getTargetMethodName();
    
    /**
     * Interceptor������Ώۂ̃��\�b�h�̈����̌^��\���N���X�����w�肷��B<p>
     * �����Ŏw�肳�ꂽ�����^�������\�b�h��Interceptor������ΏۂƂȂ�B�w�肵�Ȃ��ꍇ�́A�����^�͌��肳��Ȃ��B�܂��A���K�\�����w�肷�鎖���\�ł���B
     *
     * @param paramTypes ���\�b�h�̈����̌^��\���N���X���̔z��B���K�\�����B
     */
    public void setTargetParameterTypes(String[] paramTypes);
    
    /**
     * Interceptor������Ώۂ̃��\�b�h�̈����̌^��\���N���X�����擾����B<p>
     *
     * @return ���\�b�h�̈����̌^��\���N���X���̔z��B���K�\�����B
     */
    public String[] getTargetParameterTypes();
    
    /**
     * �}������Interceptor�`�F�[���̃��X�g�ł���{@link jp.ossc.nimbus.service.aop.InterceptorChainList InterceptorChainList}�T�[�r�X�̃T�[�r�X�����w�肷��B<p>
     *
     * @param name InterceptorChainList�T�[�r�X�̃T�[�r�X��
     */
    public void setInterceptorChainListServiceName(ServiceName name);
    
    /**
     * �}������Interceptor�`�F�[���̃��X�g�ł���{@link jp.ossc.nimbus.service.aop.InterceptorChainList InterceptorChainList}�T�[�r�X�̃T�[�r�X�����擾����B<p>
     *
     * @return InterceptorChainList�T�[�r�X�̃T�[�r�X��
     */
    public ServiceName getInterceptorChainListServiceName();
    
    /**
     * �C���^�[�Z�v�g�����Ώۂ��Ăяo��{@link jp.ossc.nimbus.service.aop.Invoker Invoker}�T�[�r�X�̃T�[�r�X����ݒ肷��B<p>
     * �f�t�H���g�ł́A{@link WrappedMethodReflectionCallInvokerService}�������I�ɐ�������g�p�����B<br>
     *
     * @param name InterceptorChainList�T�[�r�X�̃T�[�r�X��
     */
    public void setInvokerServiceName(ServiceName name);
    
    /**
     * �C���^�[�Z�v�g�����Ώۂ��Ăяo��{@link jp.ossc.nimbus.service.aop.Invoker Invoker}�T�[�r�X�̃T�[�r�X�����擾����B<p>
     *
     * @return Invoker�T�[�r�X�̃T�[�r�X��
     */
    public ServiceName getInvokerServiceName();
    
    /**
     * ���̃T�[�r�X��ÓI�R���p�C���Ɏg�p���邩�ǂ�����ݒ肷��B<p>
     * �f�t�H���g�́Afalse�B<br>
     *
     * @param isStatic �ÓI�R���p�C���Ɏg�p����ꍇ�́Atrue
     */
    public void setStaticCompile(boolean isStatic);
    
    /**
     * ���̃T�[�r�X��ÓI�R���p�C���Ɏg�p���邩�ǂ����𔻒�ݒ肷��B<p>
     *
     * @return true�̏ꍇ�A�ÓI�R���p�C���Ɏg�p����
     */
    public boolean isStaticCompile();
    
    /**
     * ���̃A�X�y�N�g�ϊ������ʂ���A�X�y�N�g�̃L�[��ݒ肷��B<p>
     * �����L�[�����A�X�y�N�g�́A�d�����ăA�X�y�N�g����Ȃ��B<br>
     * �f�t�H���g�ł́A�T�[�r�X�����g�p�����B<br>
     *
     * @param key �A�X�y�N�g�̃L�[
     */
    public void setAspectKey(String key);
    
    /**
     * ���̃A�X�y�N�g�ϊ������ʂ���A�X�y�N�g�̃L�[���擾����B<p>
     *
     * @return �A�X�y�N�g�̃L�[
     */
    public String getAspectKey();
    
    /**
     * NimbusClassLoader��VM���x����AspectTranslator��o�^���邩�ǂ�����ݒ肷��B<p>
     * �f�t�H���g��true�ŁAVM���x����AspectTranslator��o�^����Bfalse�ɐݒ肷��ƁAThreadContext���x����AspectTranslator��o�^����B
     *
     * @param isRegister VM���x����AspectTranslator��o�^����ꍇ�́Atrue
     */
    public void setRegisterVMClassLoader(boolean isRegister);
    
    /**
     * NimbusClassLoader��VM���x����AspectTranslator��o�^���邩�ǂ����𔻒肷��B<p>
     *
     * @return true�̏ꍇ�́AVM���x����AspectTranslator��o�^����Bfalse�̏ꍇ�́AThreadContext���x����AspectTranslator��o�^����
     */
    public boolean isRegisterVMClassLoader();
}
