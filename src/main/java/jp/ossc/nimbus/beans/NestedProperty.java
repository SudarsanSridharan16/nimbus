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
 * �l�X�g�v���p�e�B�B<p>
 * �l�X�g�����v���p�e�B�ɃA�N�Z�X���邽�߂�{@link Property}�B<br>
 * �ȉ��̂悤�ȃv���p�e�B�ɃA�N�Z�X����^�C�v�Z�[�t�ȃR�[�h������B<br>
 * <pre>
 *   Fuga propValue = obj.getHoge().getFuga();
 *   obj.getHoge().setFuga(propValue);
 * </pre>
 * �l�X�g�v���p�e�B���g�����ŁA���̃R�[�h��<br>
 * <pre>
 *   NestedPropery prop = new NestedPropery(new SimpleProperty("hoge"), new SimpleProperty("fuga"));
 *   Object propValue = prop.getProperty(obj);
 *   prop.setProperty(propValue);
 * </pre>
 * �Ƃ����R�[�h�ɒu�������鎖���ł���B<br>
 * ���̃R�[�h�́A�璷�ɂȂ��Ă��邪�A�ΏۂƂȂ�Bean�̌^�⃁�\�b�h���^�C�v�Z�[�t�ɏ����Ȃ����I�ȃR�[�h�ɂȂ��Ă���B<br>
 * <p>
 * ���̃l�X�g�v���p�e�B�ł́A�ȉ��̂悤��Bean�̃v���p�e�B�ɑ΂���A�N�Z�X���@���p�ӂ���Ă���B<br>
 * <table border="1">
 *   <tr bgcolor="#CCCCFF"><th rowspan="3">�A�N�Z�X���@</th><th>Java�\��</th><th rowspan="3">�v���p�e�B������\��</th></tr>
 *   <tr bgcolor="#CCCCFF"><th>�v���p�e�B�擾</th></tr>
 *   <tr bgcolor="#CCCCFF"><th>�v���p�e�B�ݒ�</th></tr>
 *   <tr><td rowspan="2">�l�X�g�v���p�e�B</td><td>bean.getHoge().getFuga()</td><td rowspan="2">hoge.fuga</td></tr>
 *   <tr><td>bean.getHoge().setFuga(value)</td></tr>
 * </table>
 * �l�X�g�Ώۂ̂Q�̃v���p�e�B�́A{@link Property}�C���^�t�F�[�X���������Ă���Ηǂ��A�P���v���p�e�B�A�C���f�b�N�X�v���p�e�B�A�}�b�v�v���p�e�B�A�l�X�g�v���p�e�B�̂�����ł��ǂ��B<br>
 *
 * @author M.Takata
 */
public class NestedProperty implements Property, java.io.Serializable{
    
    private static final long serialVersionUID = -8976001636216478152L;
    
    private static final String MSG_00001 = "Illegal NestedProperty : ";
    private static final String MSG_00002 = "Arguments is null.";
    
    /**
     * �l�X�g�����v���p�e�B�B<p>
     */
    protected Property thisProperty;
    
    /**
     * �l�X�g����v���p�e�B�B<p>
     */
    protected Property nestProperty;
    
    /**
     * null�Q�Ƃ̃v���p�e�B���擾�g�p�Ƃ����ꍇ�ɁA��O��throw���邩�ǂ����̃t���O�B<p>
     * true�̏ꍇ�́A��O��throw���Ȃ��B�f�t�H���g�́Afalse�B<br>
     */
    protected boolean isIgnoreNullProperty;
    
    /**
     * ��̃l�X�g�v���p�e�B�𐶐�����B<p>
     */
    public NestedProperty(){
    }
    
    /**
     * �w�肵���Q�̃v���p�e�B���l�X�g�����l�X�g�v���p�e�B�𐶐�����B<p>
     *
     * @param prop �l�X�g�����v���p�e�B
     * @param nestProp �l�X�g����v���p�e�B
     * @exception IllegalArgumentException ������null���w�肵���ꍇ
     */
    public NestedProperty(Property prop, Property nestProp)
     throws IllegalArgumentException{
        if(prop == null || nestProp == null){
            throw new IllegalArgumentException(MSG_00002);
        }
        thisProperty = prop;
        nestProperty = nestProp;
    }
    
    /**
     * �l�X�g�����v���p�e�B��ݒ肷��B<p>
     * 
     * @param prop �l�X�g�����v���p�e�B
     * @exception IllegalArgumentException ������null���w�肵���ꍇ
     */
    public void setThisProperty(Property prop) throws IllegalArgumentException{
        if(prop == null){
            throw new IllegalArgumentException(MSG_00002);
        }
        thisProperty = prop;
    }
    
    /**
     * �l�X�g�����ŏ��̃v���p�e�B���擾����B<p>
     * 
     * @return �l�X�g�����ŏ��̃v���p�e�B
     */
    public Property getFirstThisProperty(){
        return getFirstThisProperty(thisProperty);
    }
    
    private Property getFirstThisProperty(Property prop){
        if(prop instanceof NestedProperty){
            return getFirstThisProperty(
                ((NestedProperty)prop).getThisProperty()
            );
        }
        return prop;
    }
    
    /**
     * �l�X�g�����v���p�e�B���擾����B<p>
     * 
     * @return �l�X�g�����v���p�e�B
     */
    public Property getThisProperty(){
        return thisProperty;
    }
    
    /**
     * �l�X�g����v���p�e�B��ݒ肷��B<p>
     * 
     * @param nestProp �l�X�g����v���p�e�B
     * @exception IllegalArgumentException ������null���w�肵���ꍇ
     */
    public void setNestedProperty(Property nestProp)
     throws IllegalArgumentException{
        if(nestProp == null){
            throw new IllegalArgumentException(MSG_00002);
        }
        nestProperty = nestProp;
    }
    
    /**
     * �l�X�g����v���p�e�B���擾����B<p>
     * 
     * @return �l�X�g����v���p�e�B
     */
    public Property getNestedProperty(){
        return nestProperty;
    }
    
    /**
     * �w�肵���v���p�e�B���������͂���B<p>
     * �����Ŏw��\�ȕ�����́A<br>
     * &nbsp;�l�X�g�����v���p�e�B��.�l�X�g����v���p�e�B��<br>
     * �ł���B<br>
     *
     * @param prop �v���p�e�B������
     * @exception IllegalArgumentException �w�肳�ꂽ�v���p�e�B����������̃v���p�e�B�I�u�W�F�N�g����͂ł��Ȃ��ꍇ
     */
    public void parse(String prop) throws IllegalArgumentException{
        final int index = prop.indexOf('.');
        if(index == -1 || index == 0 || index == prop.length() - 1){
            throw new IllegalArgumentException(MSG_00001 + prop);
        }
        thisProperty = PropertyFactory.createProperty(prop.substring(0, index));
        nestProperty = PropertyFactory.createProperty(
            prop.substring(index + 1)
        );
    }
    
    public Class getPropertyType(Class clazz) throws NoSuchPropertyException{
        return nestProperty.getPropertyType(thisProperty.getPropertyType(clazz));
    }
    
    public Class getPropertyType(Object obj)
     throws NoSuchPropertyException, InvocationTargetException{
        final Object thisObj = thisProperty.getProperty(obj);
        if(thisObj == null){
            if(isIgnoreNullProperty){
                return Object.class;
            }else{
                throw new NullNestPropertyException(
                    obj.getClass(),
                    thisProperty.getPropertyName()
                );
            }
        }else{
            return nestProperty.getPropertyType(thisObj);
        }
    }
    
    public boolean isReadable(Class clazz){
        try{
            Class thisPropType = thisProperty.getPropertyType(clazz);
            return nestProperty.isReadable(thisPropType);
        }catch(NoSuchPropertyException e){
            return false;
        }
    }
    
    public boolean isReadable(Object obj){
        Object thisObj = null;
        try{
            thisObj = thisProperty.getProperty(obj);
        }catch(NoSuchPropertyException e){
            return false;
        }catch(InvocationTargetException e){
        }
        if(thisObj == null){
            try{
                Class thisPropType = thisProperty.getPropertyType(obj);
                return nestProperty.isReadable(thisPropType);
            }catch(NoSuchPropertyException e){
                return false;
            }catch(InvocationTargetException e){
                return isReadable(obj.getClass());
            }
        }else{
            return nestProperty.isReadable(thisObj);
        }
    }
    
    public boolean isWritable(Object obj, Class clazz){
        Object thisObj = null;
        try{
            thisObj = thisProperty.getProperty(obj);
        }catch(NoSuchPropertyException e){
            return false;
        }catch(InvocationTargetException e){
            return false;
        }
        if(thisObj == null){
            return false;
        }else{
            return nestProperty.isWritable(thisObj, clazz);
        }
    }
    
    public boolean isWritable(Class targetClass, Class clazz){
        try{
            Class thisPropType = thisProperty.getPropertyType(clazz);
            return nestProperty.isWritable(thisPropType, clazz);
        }catch(NoSuchPropertyException e){
            return false;
        }
    }
    
    /**
     * �w�肵���I�u�W�F�N�g����A���̃v���p�e�B���\���v���p�e�B�l���擾����B<p>
     *
     * @param obj �ΏۂƂȂ�Bean
     * @return �v���p�e�B�l
     * @exception NullNestPropertyException �l�X�g����v���p�e�B���Anull�̏ꍇ
     * @exception NoSuchPropertyException �w�肳�ꂽBean���A���̃v���p�e�B���\���A�N�Z�X�\�ȃv���p�e�B�������Ă��Ȃ��ꍇ
     * @exception InvocationTargetException �w�肳�ꂽBean�̃A�N�Z�T���Ăяo�������ʁA��O��throw���ꂽ�ꍇ
     */
    public Object getProperty(Object obj)
     throws NoSuchPropertyException, InvocationTargetException{
        final Object thisObj = thisProperty.getProperty(obj);
        if(thisObj == null){
            if(isIgnoreNullProperty){
                return null;
            }else{
                throw new NullNestPropertyException(
                    obj.getClass(),
                    thisProperty.getPropertyName()
                );
            }
        }else{
            return nestProperty.getProperty(thisObj);
        }
    }
    
    /**
     * �w�肵���I�u�W�F�N�g�ɁA���̃v���p�e�B���\���v���p�e�B�l��ݒ肷��B<p>
     *
     * @param obj �ΏۂƂȂ�Bean
     * @param value �ݒ肷��v���p�e�B�l
     * @exception NullNestPropertyException �l�X�g����v���p�e�B���Anull�̏ꍇ
     * @exception NoSuchPropertyException �w�肳�ꂽBean���A���̃v���p�e�B���\���A�N�Z�X�\�ȃv���p�e�B�������Ă��Ȃ��ꍇ
     * @exception InvocationTargetException �w�肳�ꂽBean�̃A�N�Z�T���Ăяo�������ʁA��O��throw���ꂽ�ꍇ
     */
    public void setProperty(Object obj, Object value)
     throws NoSuchPropertyException, InvocationTargetException{
        setProperty(obj, null, value);
    }
    
    /**
     * �w�肵���I�u�W�F�N�g�ɁA���̃v���p�e�B���\���v���p�e�B�l��ݒ肷��B<p>
     *
     * @param obj �ΏۂƂȂ�Bean
     * @param type �v���p�e�B�̌^
     * @param value �ݒ肷��v���p�e�B�l
     * @exception NullNestPropertyException �l�X�g����v���p�e�B���Anull�̏ꍇ
     * @exception NoSuchPropertyException �w�肳�ꂽBean���A���̃v���p�e�B���\���A�N�Z�X�\�ȃv���p�e�B�������Ă��Ȃ��ꍇ
     * @exception InvocationTargetException �w�肳�ꂽBean�̃A�N�Z�T���Ăяo�������ʁA��O��throw���ꂽ�ꍇ
     */
    public void setProperty(Object obj, Class type, Object value)
     throws NoSuchPropertyException, InvocationTargetException{
        final Object thisObj = thisProperty.getProperty(obj);
        if(thisObj == null){
            throw new NullNestPropertyException(
                obj.getClass(),
                thisProperty.getPropertyName()
            );
        }else{
            nestProperty.setProperty(thisObj, type, value);
        }
    }
    
    /**
     * ���̃v���p�e�B���\���v���p�e�B�����擾����B<p>
     *
     * @return �l�X�g�����v���p�e�B��.�l�X�g����v���p�e�B��
     */
    public String getPropertyName(){
        return thisProperty.getPropertyName()
            + '.' + nestProperty.getPropertyName();
    }
    
    public void setIgnoreNullProperty(boolean isIgnore){
        isIgnoreNullProperty = isIgnore;
        if(thisProperty != null){
            thisProperty.setIgnoreNullProperty(isIgnoreNullProperty);
        }
        if(nestProperty != null){
            nestProperty.setIgnoreNullProperty(isIgnoreNullProperty);
        }
    }
    
    public boolean isIgnoreNullProperty(){
        return isIgnoreNullProperty;
    }
    
    /**
     * ���̃l�X�g�v���p�e�B�̕�����\�����擾����B<p>
     *
     * @return NestedProperty{�v���p�e�B��.�l�X�g����v���p�e�B��}
     */
    public String toString(){
        return "NestedProperty{"
            + (thisProperty == null ? "null" : thisProperty.toString())
            + '.' + (nestProperty == null ? "null" : nestProperty.toString()) + '}';
    }
    
    /**
     * ���̃I�u�W�F�N�g�Ƒ��̃I�u�W�F�N�g�����������ǂ����������܂��B <p>
     *
     * @param obj ��r�Ώۂ̃I�u�W�F�N�g
     * @return �����Ɏw�肳�ꂽ�I�u�W�F�N�g�Ƃ��̃I�u�W�F�N�g���������ꍇ�� true�A�����łȂ��ꍇ�� false�B
     */
    public boolean equals(Object obj){
        if(obj == null){
            return false;
        }
        if(!(obj instanceof NestedProperty)){
            return false;
        }
        final NestedProperty comp = (NestedProperty)obj;
        if(thisProperty == null && comp.thisProperty != null
            || thisProperty != null && comp.thisProperty == null){
            return false;
        }else if(thisProperty != null && comp.thisProperty != null
            && !thisProperty.equals(comp.thisProperty)){
            return false;
        }
        if(nestProperty == null && comp.nestProperty == null){
            return true;
        }else if(nestProperty == null){
            return false;
        }else{
            return nestProperty.equals(comp.nestProperty);
        }
    }
    
    /**
     * �n�b�V���l���擾����B<p>
     *
     * @return �n�b�V���l
     */
    public int hashCode(){
        return (thisProperty == null ? 0 : (thisProperty.hashCode() * 2)) + (nestProperty == null ? 0 : (nestProperty.hashCode() * 3));
    }
    
    /**
     * ���̃I�u�W�F�N�g�Ǝw�肳�ꂽ�I�u�W�F�N�g�̏������r����B<p>
     *
     * @param obj ��r�Ώۂ̃I�u�W�F�N�g
     * @return ���̃I�u�W�F�N�g���w�肳�ꂽ�I�u�W�F�N�g��菬�����ꍇ�͕��̐����A�������ꍇ�̓[���A�傫���ꍇ�͐��̐���
     */
    public int compareTo(Object obj){
        if(obj == null){
            return 1;
        }
        if(!(obj instanceof NestedProperty)){
            return 1;
        }
        final NestedProperty comp = (NestedProperty)obj;
        if(thisProperty == null && comp.thisProperty != null){
            return -1;
        }else if(thisProperty != null && comp.thisProperty == null){
            return 1;
        }else if(thisProperty != null && comp.thisProperty != null){
            if(thisProperty instanceof Comparable){
                final int val = ((Comparable)thisProperty).compareTo(comp.thisProperty);
                if(val != 0){
                    return val;
                }
            }else{
                return -1;
            }
        }
        if(nestProperty == null && comp.nestProperty == null){
            return 0;
        }else if(nestProperty == null){
            return -1;
        }else{
            if(nestProperty instanceof Comparable){
                return ((Comparable)nestProperty).compareTo(comp.nestProperty);
            }else{
                return -1;
            }
        }
    }
}
