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
package jp.ossc.nimbus.service.ejb;

import java.lang.reflect.*;
import javax.naming.*;
import javax.ejb.*;

/**
 * EJB�t�@�N�g���B<p>
 * EJBHome��JNDI�ɑ΂���lookup�A�y��EJBHome����EJBObject�̐������s���B�܂��A��������EJBObject���L���b�V������B<br>
 * EJBLocalHome��JNDI�ɑ΂���lookup�A�y��EJBLocalHome����EJBLocalObject�̐������s���B<br>
 *
 * @author  M.Takata
 */
public interface EJBFactory{
    
    /**
     * EJBHome��create���\�b�h�̃��\�b�h���B<p>
     */
    public static final String EJB_CREATE_METHOD_NAME = "create";
    
    /**
     * EJB��JNDI�����w�肵�āAEJBObject���擾����B<p>
     * �w�肳�ꂽJNDI����lookup����EJBHome�ɑ΂��āA�����Ȃ���create���\�b�h���Ăяo����EJBObject���擾����B<br>
     *
     * @param name EJB��JNDI��
     * @return �w�肵��JNDI���ɑΉ�����EJBObject
     * @exception NamingException EJBHome��lookup�Ɏ��s�����ꍇ
     * @exception CreateException EJBHome��create���\�b�h���Ăяo�����ۂɗ�O�����������ꍇ
     * @exception NoSuchMethodException EJBHome��create���\�b�h��������Ȃ��ꍇ
     * @exception IllegalAccessException EJBHome��create���\�b�h���Ăяo�������ɁA�A�N�Z�X�C���q�ɂ��A�N�Z�X�������s���ȏꍇ
     * @exception InvocationTargetException EJBHome��create���\�b�h���Ăяo�������ɁA�Ăяo����ŉ��炩�̗�O�����������ꍇ
     */
    public EJBObject get(
        String name
    ) throws NamingException, CreateException, NoSuchMethodException,
             IllegalAccessException, InvocationTargetException;
    
    /**
     * EJB��JNDI�����w�肵�āAEJBLocalObject���擾����B<p>
     * �w�肳�ꂽJNDI����lookup����EJBLocalHome�ɑ΂��āA�����Ȃ���create���\�b�h���Ăяo����EJBLocalObject���擾����B<br>
     *
     * @param name EJB��JNDI��
     * @return �w�肵��JNDI���ɑΉ�����EJBLocalObject
     * @exception NamingException EJBLocalHome��lookup�Ɏ��s�����ꍇ
     * @exception CreateException EJBLocalHome��create���\�b�h���Ăяo�����ۂɗ�O�����������ꍇ
     * @exception NoSuchMethodException EJBLocalHome��create���\�b�h��������Ȃ��ꍇ
     * @exception IllegalAccessException EJBLocalHome��create���\�b�h���Ăяo�������ɁA�A�N�Z�X�C���q�ɂ��A�N�Z�X�������s���ȏꍇ
     * @exception InvocationTargetException EJBLocalHome��create���\�b�h���Ăяo�������ɁA�Ăяo����ŉ��炩�̗�O�����������ꍇ
     */
    public EJBLocalObject getLocal(
        String name
    ) throws NamingException, CreateException, NoSuchMethodException,
             IllegalAccessException, InvocationTargetException;
    
    /**
     * EJB��JNDI�����w�肵�āAEJBObject���擾����B<p>
     * �w�肳�ꂽJNDI����lookup����EJBHome�ɑ΂��āA�w�肵��������create���\�b�h���Ăяo����EJBObject���擾����B<br>
     *
     * @param name EJB��JNDI��
     * @param params �����̔z��
     * @return �w�肵��JNDI���ɑΉ�����EJBObject
     * @exception NamingException EJBHome��lookup�Ɏ��s�����ꍇ
     * @exception CreateException EJBHome��create���\�b�h���Ăяo�����ۂɗ�O�����������ꍇ
     * @exception NoSuchMethodException EJBHome��create���\�b�h��������Ȃ��ꍇ
     * @exception IllegalAccessException EJBHome��create���\�b�h���Ăяo�������ɁA�A�N�Z�X�C���q�ɂ��A�N�Z�X�������s���ȏꍇ
     * @exception InvocationTargetException EJBHome��create���\�b�h���Ăяo�������ɁA�Ăяo����ŉ��炩�̗�O�����������ꍇ
     */
    public EJBObject get(
        String name,
        Object[] params
    ) throws NamingException, CreateException, NoSuchMethodException,
             IllegalAccessException, InvocationTargetException;
    
    /**
     * EJB��JNDI�����w�肵�āAEJBLocalObject���擾����B<p>
     * �w�肳�ꂽJNDI����lookup����EJBLocalHome�ɑ΂��āA�w�肵��������create���\�b�h���Ăяo����EJBLocalObject���擾����B<br>
     *
     * @param name EJB��JNDI��
     * @param params �����̔z��
     * @return �w�肵��JNDI���ɑΉ�����EJBLocalObject
     * @exception NamingException EJBLocalHome��lookup�Ɏ��s�����ꍇ
     * @exception CreateException EJBLocalHome��create���\�b�h���Ăяo�����ۂɗ�O�����������ꍇ
     * @exception NoSuchMethodException EJBLocalHome��create���\�b�h��������Ȃ��ꍇ
     * @exception IllegalAccessException EJBLocalHome��create���\�b�h���Ăяo�������ɁA�A�N�Z�X�C���q�ɂ��A�N�Z�X�������s���ȏꍇ
     * @exception InvocationTargetException EJBLocalHome��create���\�b�h���Ăяo�������ɁA�Ăяo����ŉ��炩�̗�O�����������ꍇ
     */
    public EJBLocalObject getLocal(
        String name,
        Object[] params
    ) throws NamingException, CreateException, NoSuchMethodException,
             IllegalAccessException, InvocationTargetException;
    
    /**
     * EJB��JNDI�����w�肵�āAEJBObject���擾����B<p>
     * �w�肳�ꂽJNDI����lookup����EJBHome�ɑ΂��āA�����Ȃ���create���\�b�h���Ăяo����EJBObject���擾����B<br>
     *
     * @param name EJB��JNDI��
     * @param homeType EJBHome�̃N���X�I�u�W�F�N�g
     * @return �w�肵��JNDI���ɑΉ�����EJBObject
     * @exception NamingException EJBHome��lookup�Ɏ��s�����ꍇ
     * @exception CreateException EJBHome��create���\�b�h���Ăяo�����ۂɗ�O�����������ꍇ
     * @exception NoSuchMethodException EJBHome��create���\�b�h��������Ȃ��ꍇ
     * @exception IllegalAccessException EJBHome��create���\�b�h���Ăяo�������ɁA�A�N�Z�X�C���q�ɂ��A�N�Z�X�������s���ȏꍇ
     * @exception InvocationTargetException EJBHome��create���\�b�h���Ăяo�������ɁA�Ăяo����ŉ��炩�̗�O�����������ꍇ
     */
    public EJBObject get(
        String name,
        Class homeType
    ) throws NamingException, CreateException, NoSuchMethodException,
             IllegalAccessException, InvocationTargetException;
    
    /**
     * EJB��JNDI�����w�肵�āAEJBLocalObject���擾����B<p>
     * �w�肳�ꂽJNDI����lookup����EJBLocalHome�ɑ΂��āA�����Ȃ���create���\�b�h���Ăяo����EJBLocalObject���擾����B<br>
     *
     * @param name EJB��JNDI��
     * @param homeType EJBLocalHome�̃N���X�I�u�W�F�N�g
     * @return �w�肵��JNDI���ɑΉ�����EJBLocalObject
     * @exception NamingException EJBLocalHome��lookup�Ɏ��s�����ꍇ
     * @exception CreateException EJBLocalHome��create���\�b�h���Ăяo�����ۂɗ�O�����������ꍇ
     * @exception NoSuchMethodException EJBLocalHome��create���\�b�h��������Ȃ��ꍇ
     * @exception IllegalAccessException EJBLocalHome��create���\�b�h���Ăяo�������ɁA�A�N�Z�X�C���q�ɂ��A�N�Z�X�������s���ȏꍇ
     * @exception InvocationTargetException EJBLocalHome��create���\�b�h���Ăяo�������ɁA�Ăяo����ŉ��炩�̗�O�����������ꍇ
     */
    public EJBLocalObject getLocal(
        String name,
        Class homeType
    ) throws NamingException, CreateException, NoSuchMethodException,
             IllegalAccessException, InvocationTargetException;
    
    /**
     * EJB��JNDI�����w�肵�āAEJBObject���擾����B<p>
     * �w�肳�ꂽJNDI����lookup����EJBHome�ɑ΂��āA�w�肵��������create���\�b�h���Ăяo����EJBObject���擾����B<br>
     *
     * @param name EJB��JNDI��
     * @param homeType EJBHome�̃N���X�I�u�W�F�N�g
     * @param paramTypes �����̌^�z��
     * @param params �����̔z��
     * @return �w�肵��JNDI���ɑΉ�����EJBObject
     * @exception NamingException EJBHome��lookup�Ɏ��s�����ꍇ
     * @exception CreateException EJBHome��create���\�b�h���Ăяo�����ۂɗ�O�����������ꍇ
     * @exception NoSuchMethodException EJBHome��create���\�b�h��������Ȃ��ꍇ
     * @exception IllegalAccessException EJBHome��create���\�b�h���Ăяo�������ɁA�A�N�Z�X�C���q�ɂ��A�N�Z�X�������s���ȏꍇ
     * @exception InvocationTargetException EJBHome��create���\�b�h���Ăяo�������ɁA�Ăяo����ŉ��炩�̗�O�����������ꍇ
     */
    public EJBObject get(
        String name,
        Class homeType,
        Class[] paramTypes,
        Object[] params
    ) throws NamingException, CreateException, NoSuchMethodException,
             IllegalAccessException, InvocationTargetException;
    
    /**
     * EJB��JNDI�����w�肵�āAEJBLocalObject���擾����B<p>
     * �w�肳�ꂽJNDI����lookup����EJBLocalHome�ɑ΂��āA�w�肵��������create���\�b�h���Ăяo����EJBLocalObject���擾����B<br>
     *
     * @param name EJB��JNDI��
     * @param homeType EJBLocalHome�̃N���X�I�u�W�F�N�g
     * @param paramTypes �����̌^�z��
     * @param params �����̔z��
     * @return �w�肵��JNDI���ɑΉ�����EJBLocalObject
     * @exception NamingException EJBLocalHome��lookup�Ɏ��s�����ꍇ
     * @exception CreateException EJBLocalHome��create���\�b�h���Ăяo�����ۂɗ�O�����������ꍇ
     * @exception NoSuchMethodException EJBLocalHome��create���\�b�h��������Ȃ��ꍇ
     * @exception IllegalAccessException EJBLocalHome��create���\�b�h���Ăяo�������ɁA�A�N�Z�X�C���q�ɂ��A�N�Z�X�������s���ȏꍇ
     * @exception InvocationTargetException EJBLocalHome��create���\�b�h���Ăяo�������ɁA�Ăяo����ŉ��炩�̗�O�����������ꍇ
     */
    public EJBLocalObject getLocal(
        String name,
        Class homeType,
        Class[] paramTypes,
        Object[] params
    ) throws NamingException, CreateException, NoSuchMethodException,
             IllegalAccessException, InvocationTargetException;
    
    /**
     * EJB��JNDI�����w�肵�āAEJBObject���擾����B<p>
     * �w�肳�ꂽJNDI����lookup����EJBHome�ɑ΂��āA�w�肵��������create���\�b�h���Ăяo����EJBObject���擾����B�܂��A�擾����EJBObject��ړI�̃^�C�v�ɃL���X�g���ĕԂ��B<br>
     *
     * @param name EJB��JNDI��
     * @param homeType EJBHome�̃N���X�I�u�W�F�N�g
     * @param remoteType EJBObject�̃N���X�I�u�W�F�N�g
     * @param paramTypes �����̌^�z��
     * @param params �����̔z��
     * @return �w�肵��JNDI���ɑΉ�����EJBObject
     * @exception NamingException EJBHome��lookup�Ɏ��s�����ꍇ
     * @exception CreateException EJBHome��create���\�b�h���Ăяo�����ۂɗ�O�����������ꍇ
     * @exception NoSuchMethodException EJBHome��create���\�b�h��������Ȃ��ꍇ
     * @exception IllegalAccessException EJBHome��create���\�b�h���Ăяo�������ɁA�A�N�Z�X�C���q�ɂ��A�N�Z�X�������s���ȏꍇ
     * @exception InvocationTargetException EJBHome��create���\�b�h���Ăяo�������ɁA�Ăяo����ŉ��炩�̗�O�����������ꍇ
     */
    public EJBObject get(
        String name,
        Class homeType,
        Class remoteType,
        Class[] paramTypes,
        Object[] params
    ) throws NamingException, CreateException, NoSuchMethodException,
             IllegalAccessException, InvocationTargetException;
    
    /**
     * EJB��JNDI�����w�肵�āAEJBLocalObject���擾����B<p>
     * �w�肳�ꂽJNDI����lookup����EJBLocalHome�ɑ΂��āA�w�肵��������create���\�b�h���Ăяo����EJBLocalObject���擾����B�܂��A�擾����EJBLocalObject��ړI�̃^�C�v�ɃL���X�g���ĕԂ��B<br>
     *
     * @param name EJB��JNDI��
     * @param homeType EJBLocalHome�̃N���X�I�u�W�F�N�g
     * @param localType EJBLocalObject�̃N���X�I�u�W�F�N�g
     * @param paramTypes �����̌^�z��
     * @param params �����̔z��
     * @return �w�肵��JNDI���ɑΉ�����EJBLocalObject
     * @exception NamingException EJBLocalHome��lookup�Ɏ��s�����ꍇ
     * @exception CreateException EJBLocalHome��create���\�b�h���Ăяo�����ۂɗ�O�����������ꍇ
     * @exception NoSuchMethodException EJBLocalHome��create���\�b�h��������Ȃ��ꍇ
     * @exception IllegalAccessException EJBLocalHome��create���\�b�h���Ăяo�������ɁA�A�N�Z�X�C���q�ɂ��A�N�Z�X�������s���ȏꍇ
     * @exception InvocationTargetException EJBLocalHome��create���\�b�h���Ăяo�������ɁA�Ăяo����ŉ��炩�̗�O�����������ꍇ
     */
    public EJBLocalObject getLocal(
        String name,
        Class homeType,
        Class localType,
        Class[] paramTypes,
        Object[] params
    ) throws NamingException, CreateException, NoSuchMethodException,
             IllegalAccessException, InvocationTargetException;
    
    /**
     * �w�肵��JNDI����EJB�̃L���b�V���𖳌�������B<p>
     * 
     * @param name EJB��JNDI��
     */
    public void invalidate(String name);
    
    /**
     * EJB�̃L���b�V���𖳌�������B<p>
     */
    public void invalidate();
}
