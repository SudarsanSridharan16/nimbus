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

import java.util.*;
import java.lang.reflect.InvocationTargetException;

/**
 * �v���p�e�B�A�N�Z�X�B<p>
 * ��x�g�p����{@link Property}���L���b�V�����čė��p����B<br>
 *
 * @author M.Takata
 */
public class PropertyAccess{
    
    private boolean isIgnoreNullProperty = false;
    
    private Map propertyCache = Collections.synchronizedMap(new HashMap());
    
    private static PropertyAccess instance;
    
    private static PropertyAccess instanceForIgnoreNullProperty;
    
    /**
     * �C���X�^���X�𐶐�����B<p>
     */
    public PropertyAccess(){
    }
    
    /**
     * �V���O���g���ȃC���X�^���X���擾����B<p>
     *
     * @param isIgnoreNullProperty null�Q�Ƃ̎��ɗ�O��throw���Ȃ��ꍇ��true
     * @return �V���O���g���ȃC���X�^���X
     */
    public static synchronized PropertyAccess getInstance(boolean isIgnoreNullProperty){
        if(isIgnoreNullProperty){
            if(instance == null){
                instance = new PropertyAccess(){
                    public void setIgnoreNullProperty(boolean isIgnore){
                    }
                };
            }
            return instance;
        }else{
            if(instanceForIgnoreNullProperty == null){
                instanceForIgnoreNullProperty = new PropertyAccess(){
                    public void setIgnoreNullProperty(boolean isIgnore){
                    }
                };
                instanceForIgnoreNullProperty.isIgnoreNullProperty = true;
            }
            return instanceForIgnoreNullProperty;
        }
    }
    
    /**
     * null�Q�Ƃ̃v���p�e�B���擾�g�p�Ƃ����ꍇ�ɁA��O��throw���邩�ǂ�����ݒ肷��B<p>
     * �f�t�H���g�́Afalse�B<br>
     *
     * @param isIgnore null�Q�Ƃ̎��ɗ�O��throw���Ȃ��ꍇ��true
     */
    public void setIgnoreNullProperty(boolean isIgnore){
        if(this == instance || this == instanceForIgnoreNullProperty){
            return;
        }
        isIgnoreNullProperty = isIgnore;
    }
    
    /**
     * null�Q�Ƃ̃v���p�e�B���擾�g�p�Ƃ����ꍇ�ɁA��O��throw���邩�ǂ����𔻒肷��B<p>
     *
     * @return true�̏ꍇ�Anull�Q�Ƃ̎��ɗ�O��throw���Ȃ�
     */
    public boolean isIgnoreNullProperty(){
        return isIgnoreNullProperty;
    }
    
    /**
     * �w�肵���I�u�W�F�N�g�̎w�肵���v���p�e�B�̒l���擾����B<p>
     *
     * @param target �v���p�e�B�̎擾�ΏۂƂȂ�Bean
     * @param prop �v���p�e�B��
     * @return �v���p�e�B�l
     * @exception IllegalArgumentException �w�肳�ꂽ�v���p�e�B���̃t�H�[�}�b�g���������Ȃ��ꍇ
     * @exception NoSuchPropertyException �w�肳�ꂽBean���A�w�肵���v���p�e�B���\���A�N�Z�X�\�ȃv���p�e�B�������Ă��Ȃ��ꍇ
     * @exception InvocationTargetException �w�肳�ꂽBean�̃A�N�Z�T���Ăяo�������ʁA��O��throw���ꂽ�ꍇ
     */
    public Object get(Object target, String prop) throws IllegalArgumentException, NoSuchPropertyException, InvocationTargetException{
        return getProperty(prop).getProperty(target);
    }
    
    /**
     * �w�肵���I�u�W�F�N�g�̎w�肵���v���p�e�B�ɒl��ݒ肷��B<p>
     *
     * @param target �v���p�e�B�̐ݒ�ΏۂƂȂ�Bean
     * @param prop �v���p�e�B��
     * @param value �ݒ肷��v���p�e�B�l
     * @exception IllegalArgumentException �w�肳�ꂽ�v���p�e�B���̃t�H�[�}�b�g���������Ȃ��ꍇ
     * @exception NoSuchPropertyException �w�肳�ꂽBean���A�w�肵���v���p�e�B���\���A�N�Z�X�\�ȃv���p�e�B�������Ă��Ȃ��ꍇ
     * @exception InvocationTargetException �w�肳�ꂽBean�̃A�N�Z�T���Ăяo�������ʁA��O��throw���ꂽ�ꍇ
     */
    public void set(Object target, String prop, Object value) throws IllegalArgumentException, NoSuchPropertyException, InvocationTargetException{
        getProperty(prop).setProperty(target, value);
    }
    
    /**
     * �w�肵���v���p�e�B���̃v���p�e�B���擾����B<p>
     *
     * @param prop �v���p�e�B��
     * @return �v���p�e�B
     * @exception IllegalArgumentException �w�肳�ꂽ�v���p�e�B���̃t�H�[�}�b�g���������Ȃ��ꍇ
     */
    public Property getProperty(String prop) throws IllegalArgumentException{
        Property property = (Property)propertyCache.get(prop);
        if(property == null){
            property = PropertyFactory.createProperty(prop);
            property.setIgnoreNullProperty(isIgnoreNullProperty);
            Property exists = (Property)propertyCache.put(prop, property);
            if(exists != null){
                property = exists;
            }
        }
        return property;
    }
    
    /**
     * �L���b�V�����Ă���v���p�e�B���N���A����B<p>
     */
    public void clear(){
        propertyCache.clear();
    }
}