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

import java.lang.reflect.*;

/**
 * Bean�̃v���p�e�B�ɔėp�I�ɃA�N�Z�X���邽�߂̃C���^�t�F�[�X�B<p>
 *
 * @author M.Takata
 */
public interface Property{
    
    /**
     * ���̃v���p�e�B���\���v���p�e�B�����擾����B<p>
     *
     * @return �v���p�e�B��
     */
    public String getPropertyName();
    
    /**
     * �w�肵���I�u�W�F�N�g�̃N���X����A���̃v���p�e�B���\���v���p�e�B�^���擾����B<p>
     *
     * @param clazz �ΏۂƂȂ�Bean�̃N���X
     * @return �v���p�e�B�^
     * @exception NoSuchPropertyException �w�肳�ꂽBean���A���̃v���p�e�B���\���A�N�Z�X�\�ȃv���p�e�B�������Ă��Ȃ��ꍇ
     * @exception InvocationTargetException �w�肳�ꂽBean�̃A�N�Z�T���Ăяo�������ʁA��O��throw���ꂽ�ꍇ
     */
    public Class getPropertyType(Class clazz) throws NoSuchPropertyException;
    
    /**
     * �w�肵���I�u�W�F�N�g����A���̃v���p�e�B���\���v���p�e�B�^���擾����B<p>
     *
     * @param obj �ΏۂƂȂ�Bean
     * @return �v���p�e�B�^
     * @exception NoSuchPropertyException �w�肳�ꂽBean���A���̃v���p�e�B���\���A�N�Z�X�\�ȃv���p�e�B�������Ă��Ȃ��ꍇ
     * @exception InvocationTargetException �w�肳�ꂽBean�̃A�N�Z�T���Ăяo�������ʁA��O��throw���ꂽ�ꍇ
     */
    public Class getPropertyType(Object obj)
     throws NoSuchPropertyException, InvocationTargetException;
    
    /**
     * �w�肵���I�u�W�F�N�g����A���̃v���p�e�B���\���v���p�e�B�l���擾����B<p>
     *
     * @param obj �ΏۂƂȂ�Bean
     * @return �v���p�e�B�l
     * @exception NoSuchPropertyException �w�肳�ꂽBean���A���̃v���p�e�B���\���A�N�Z�X�\�ȃv���p�e�B�������Ă��Ȃ��ꍇ
     * @exception InvocationTargetException �w�肳�ꂽBean�̃A�N�Z�T���Ăяo�������ʁA��O��throw���ꂽ�ꍇ
     */
    public Object getProperty(Object obj)
     throws NoSuchPropertyException, InvocationTargetException;
    
    /**
     * �w�肵���I�u�W�F�N�g�ɁA���̃v���p�e�B���\���v���p�e�B�l��ݒ肷��B<p>
     *
     * @param obj �ΏۂƂȂ�Bean
     * @param value �ݒ肷��v���p�e�B�l
     * @exception NoSuchPropertyException �w�肳�ꂽBean���A���̃v���p�e�B���\���A�N�Z�X�\�ȃv���p�e�B�������Ă��Ȃ��ꍇ
     * @exception InvocationTargetException �w�肳�ꂽBean�̃A�N�Z�T���Ăяo�������ʁA��O��throw���ꂽ�ꍇ
     */
    public void setProperty(Object obj, Object value)
     throws NoSuchPropertyException, InvocationTargetException;
    
    /**
     * �w�肵���I�u�W�F�N�g�ɁA���̃v���p�e�B���\���v���p�e�B�l��ݒ肷��B<p>
     *
     * @param obj �ΏۂƂȂ�Bean
     * @param type �v���p�e�B�̌^
     * @param value �ݒ肷��v���p�e�B�l
     * @exception NoSuchPropertyException �w�肳�ꂽBean���A���̃v���p�e�B���\���A�N�Z�X�\�ȃv���p�e�B�������Ă��Ȃ��ꍇ
     * @exception InvocationTargetException �w�肳�ꂽBean�̃A�N�Z�T���Ăяo�������ʁA��O��throw���ꂽ�ꍇ
     */
    public void setProperty(Object obj, Class type, Object value)
     throws NoSuchPropertyException, InvocationTargetException;
    
    /**
     * �w�肵���v���p�e�B���������͂���B<p>
     *
     * @param prop �v���p�e�B������
     * @exception IllegalArgumentException �w�肳�ꂽ�v���p�e�B����������̃v���p�e�B�I�u�W�F�N�g����͂ł��Ȃ��ꍇ
     */
    public void parse(String prop) throws IllegalArgumentException;
    
    /**
     * �ǂݍ��݉\���ǂ������肷��B<p>
     *
     * @param clazz �ΏۂƂȂ�Bean�̃N���X
     * @return �ǂݍ��݉\�ȏꍇtrue
     */
    public boolean isReadable(Class clazz);
    
    /**
     * �ǂݍ��݉\���ǂ������肷��B<p>
     *
     * @param obj �ΏۂƂȂ�Bean
     * @return �ǂݍ��݉\�ȏꍇtrue
     */
    public boolean isReadable(Object obj);
    
    /**
     * �������݉\���ǂ������肷��B<p>
     *
     * @param targetClass �ΏۂƂȂ�Bean�̃N���X
     * @param clazz �ݒ肷��v���p�e�B�̌^
     * @return �������݉\�ȏꍇtrue
     */
    public boolean isWritable(Class targetClass, Class clazz);
    
    /**
     * �������݉\���ǂ������肷��B<p>
     *
     * @param obj �ΏۂƂȂ�Bean
     * @param clazz �ݒ肷��v���p�e�B�̌^
     * @return �������݉\�ȏꍇtrue
     */
    public boolean isWritable(Object obj, Class clazz);
    
    /**
     * null�Q�Ƃ̃v���p�e�B���擾�g�p�Ƃ����ꍇ�ɁA��O��throw���邩�ǂ�����ݒ肷��B<p>
     * �f�t�H���g�́Afalse�B<br>
     *
     * @param isIgnore null�Q�Ƃ̎��ɗ�O��throw���Ȃ��ꍇ��true
     */
    public void setIgnoreNullProperty(boolean isIgnore);
    
    /**
     * null�Q�Ƃ̃v���p�e�B���擾�g�p�Ƃ����ꍇ�ɁA��O��throw���邩�ǂ����𔻒肷��B<p>
     *
     * @return true�̏ꍇ�Anull�Q�Ƃ̎��ɗ�O��throw���Ȃ�
     */
    public boolean isIgnoreNullProperty();
}
