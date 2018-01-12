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
package jp.ossc.nimbus.service.aop.interceptor;

import java.util.*;

import jp.ossc.nimbus.core.*;

/**
 * {@link NoCalledMethodMetricsInterceptorService}��MBean�C���^�t�F�[�X<p>
 * 
 * @author M.Takata
 * @see NoCalledMethodMetricsInterceptorService
 */
public interface NoCalledMethodMetricsInterceptorServiceMBean
 extends ServiceBaseMBean{
    
    /**
     * ���v�����Ώۂ̃N���X�̃N���X�C���q���w�肷��B<p>
     * �����Ŏw�肳�ꂽ�N���X�C���q�̃N���X�����v�����ΏۂƂȂ�B�w�肵�Ȃ��ꍇ�́A�N���X�C���q�͌��肳��Ȃ��B<br>
     * �C���q��ے肷��ꍇ�́A�e�C���q�̑O��"!"��t�^���邱�ƁB
     *
     * @param modifiers �N���X�C���q������
     */
    public void setTargetClassModifiers(String modifiers);
    
    /**
     * ���v�����Ώۂ̃N���X�̃N���X�C���q���擾����B<p>
     *
     * @return �N���X�C���q������
     */
    public String getTargetClassModifiers();
    
    /**
     * ���v�����Ώۂ̃N���X�����w�肷��B<p>
     * �����Ŏw�肳�ꂽ�N���X���̃N���X�����v�����ΏۂƂȂ�B�w�肵�Ȃ��ꍇ�́A�N���X���͌��肳��Ȃ��B�܂��A���K�\�����w�肷�鎖���\�ł���B
     *
     * @param name �p�b�P�[�W�����܂ފ��S�C���N���X���B���K�\�����B
     */
    public void setTargetClassName(String name);
    
    /**
     * ���v�����Ώۂ̃N���X�����擾����B<p>
     *
     * @return �p�b�P�[�W�����܂ފ��S�C���N���X���B���K�\�����B
     */
    public String getTargetClassName();
    
    /**
     * ���v�����Ώۂ̃C���X�^���X�̃N���X�����w�肷��B<p>
     * �����Ŏw�肳�ꂽ�N���X���̃C���X�^���X�����v�����ΏۂƂȂ�B�w�肵�Ȃ��ꍇ�́A�C���X�^���X�͌��肳��Ȃ��B
     *
     * @param name �p�b�P�[�W�����܂ފ��S�C���N���X��
     */
    public void setTargetInstanceClassName(String name);
    
    /**
     * ���v�����Ώۂ̃C���X�^���X�̃N���X�����擾����B<p>
     *
     * @return �p�b�P�[�W�����܂ފ��S�C���N���X���B
     */
    public String getTargetInstanceClassName();
    
    /**
     * ���v�����Ώۂ̃��\�b�h�̃��\�b�h�C���q���w�肷��B<p>
     * �����Ŏw�肳�ꂽ���\�b�h�C���q�̃��\�b�h�����v�����ΏۂƂȂ�B�w�肵�Ȃ��ꍇ�́A���\�b�h�C���q�͌��肳��Ȃ��B<br>
     * �C���q��ے肷��ꍇ�́A�e�C���q�̑O��"!"��t�^���邱�ƁB
     *
     * @param modifiers ���\�b�h�C���q������
     */
    public void setTargetMethodModifiers(String modifiers);
    
    /**
     * ���v�����Ώۂ̃��\�b�h�̃��\�b�h�C���q���擾����B<p>
     *
     * @return ���\�b�h�C���q������
     */
    public String getTargetMethodModifiers();
    
    /**
     * ���v�����Ώۂ̃��\�b�h�����w�肷��B<p>
     * �����Ŏw�肳�ꂽ���\�b�h���̃��\�b�h�����v�����ΏۂƂȂ�B�w�肵�Ȃ��ꍇ�́A���\�b�h���͌��肳��Ȃ��B�܂��A���K�\�����w�肷�鎖���\�ł���B
     *
     * @param name ���\�b�h���B���K�\�����B
     */
    public void setTargetMethodName(String name);
    
    /**
     * ���v�����Ώۂ̃��\�b�h�����擾����B<p>
     *
     * @return ���\�b�h���B���K�\�����B
     */
    public String getTargetMethodName();
    
    /**
     * ���v�����Ώۂ̃��\�b�h�̈����̌^��\���N���X�����w�肷��B<p>
     * �����Ŏw�肳�ꂽ�����^�������\�b�h�����v�����ΏۂƂȂ�B�w�肵�Ȃ��ꍇ�́A�����^�͌��肳��Ȃ��B�܂��A���K�\�����w�肷�鎖���\�ł���B
     *
     * @param paramTypes ���\�b�h�̈����̌^��\���N���X���̔z��B���K�\�����B
     */
    public void setTargetParameterTypes(String[] paramTypes);
    
    /**
     * ���v�����Ώۂ̃��\�b�h�̈����̌^��\���N���X�����擾����B<p>
     *
     * @return ���\�b�h�̈����̌^��\���N���X���̔z��B���K�\�����B
     */
    public String[] getTargetParameterTypes();
    
    /**
     * ���v�����Ώۂ̃N���X�ɐ錾����Ă��郁�\�b�h������Ώۂɂ��邩�ǂ�����ݒ肷��B<p>
     * �f�t�H���g�́Afalse�ŁA�p�����ꂽ���\�b�h���Ώۂɂ���
     *
     * @param isDeclaring ���v�����Ώۂ̃N���X�ɐ錾����Ă��郁�\�b�h������Ώۂɂ���ꍇ�Atrue
     */
    public void setDeclaringMethod(boolean isDeclaring);
    
    /**
     * ���v�����Ώۂ̃N���X�ɐ錾����Ă��郁�\�b�h������Ώۂɂ��邩�ǂ����𔻒肷��B<p>
     *
     * @return true�̏ꍇ�A���v�����Ώۂ̃N���X�ɐ錾����Ă��郁�\�b�h������Ώۂɂ���
     */
    public boolean isDeclaringMethod();
    
    /**
     * JVM�̃N���X�p�X�ɉ����āA�N���X�p�X��ǉ�����B<p>
     * ���΃p�X���w�肵���ꍇ�́A���s�p�X����̑��΃p�X�ɉ����āA���̃T�[�r�X����`����Ă���T�[�r�X��`�t�@�C������̑��΃p�X�ɂ��p�X�������s���B<br>
     *
     * @param paths �N���X�p�X�̔z��
     */
    public void setClassPaths(String[] paths);
    
    /**
     * JVM�̃N���X�p�X�ɉ������N���X�p�X���擾����B<p>
     *
     * @return �N���X�p�X�̔z��
     */
    public String[] getClassPaths();
    
    /**
     * ���v�����Ώۂ̃��\�b�h�W�����擾����B<p>
     *
     * @return ���v�����Ώۂ�Method�I�u�W�F�N�g�̏W��
     */
    public Set getTargetMethodSet();
    
    /**
     * ���v�����Ώۂ̃��\�b�h�ꗗ��������擾����B<p>
     *
     * @return ���v�����Ώۂ̃��\�b�h�ꗗ������
     */
    public String getTargetMethodString();
    
    /**
     * ���v���擾�������ʁA�Ώۂ̃��\�b�h�̂����ŌĂяo����Ă��Ȃ��������\�b�h�W�����擾����B<p>
     *
     * @return ���v�Ώۂ̃��\�b�h�̂����ŌĂяo����Ă��Ȃ��������\�b�h�W��
     */
    public Set getNoCalledMethodSet();
    
    /**
     * ���v���擾�������ʁA�Ώۂ̃��\�b�h�̂����ŌĂяo����Ă��Ȃ��������\�b�h�ꗗ��������擾����B<p>
     *
     * @return ���v�Ώۂ̃��\�b�h�̂����ŌĂяo����Ă��Ȃ��������\�b�h�ꗗ������
     */
    public String getNoCalledMethodString();
    
    /**
     * ���v���ʂ��T�[�r�X�̒�~���ɕW���o�͂ɏo�͂��邩�ǂ�����ݒ肷��B<p>
     * �f�t�H���g�́Atrue�B<br>
     *
     * @param isOutput �o�͂���ꍇ�́Atrue
     */
    public void setOutputSystemOut(boolean isOutput);
    
    /**
     * ���v���ʂ��T�[�r�X�̒�~���ɕW���o�͂ɏo�͂��邩�ǂ����𔻒肷��B<p>
     *
     * @return true�̏ꍇ�A�o�͂���
     */
    public boolean isOutputSystemOut();
}
