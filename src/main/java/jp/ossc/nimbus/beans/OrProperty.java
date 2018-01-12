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
 * OR�v���p�e�B�B<p>
 * �Q�̃v���p�e�B����Null�łȂ����̃v���p�e�B�ɃA�N�Z�X���邽�߂�{@link Property}�B<br>
 * �ȉ��̂悤�ȃv���p�e�B�ɃA�N�Z�X����^�C�v�Z�[�t�ȃR�[�h������B<br>
 * <pre>
 *   Object propValue = bean.getHoge() != null ? bean.getHoge() : bean.getFuga();
 * </pre>
 * OR�v���p�e�B���g�����ŁA���̃R�[�h��<br>
 * <pre>
 *   OrPropery prop = new OrPropery(new SimpleProperty("hoge"), new SimpleProperty("fuga"));
 *   Object propValue = prop.getProperty(obj);
 * </pre>
 * �Ƃ����R�[�h�ɒu�������鎖���ł���B<br>
 * ���̃R�[�h�́A�璷�ɂȂ��Ă��邪�A�ΏۂƂȂ�Bean�̌^�⃁�\�b�h���^�C�v�Z�[�t�ɏ����Ȃ����I�ȃR�[�h�ɂȂ��Ă���B<br>
 * <p>
 * ���̃l�X�g�v���p�e�B�ł́A�ȉ��̂悤��Bean�̃v���p�e�B�ɑ΂���A�N�Z�X���@���p�ӂ���Ă���B<br>
 * <table border="1">
 *   <tr bgcolor="#CCCCFF"><th rowspan="2">�A�N�Z�X���@</th><th>Java�\��</th><th rowspan="2">�v���p�e�B������\��</th></tr>
 *   <tr bgcolor="#CCCCFF"><th>�v���p�e�B�擾</th></tr>
 *   <tr><td>OR�v���p�e�B</td><td>bean.getHoge() != null ? bean.getHoge() : bean.getFuga()</td><td>hoge|fuga</td></tr>
 * </table>
 * OR�Ώۂ̂Q�̃v���p�e�B�́A{@link Property}�C���^�t�F�[�X���������Ă���Ηǂ��B<br>
 *
 * @author M.Takata
 */
public class OrProperty implements Property, java.io.Serializable{
    
    private static final long serialVersionUID = 4557829281768367883L;
    private static final String MSG_00001 = "Illegal OrProperty : ";
    private static final String MSG_00002 = "Arguments is null.";
    
    /**
     * ���v���p�e�B�B<p>
     */
    protected Property firstProperty;
    
    /**
     * ���v���p�e�B�B<p>
     */
    protected Property secondProperty;
    
    /**
     * null�Q�Ƃ̃v���p�e�B���擾�g�p�Ƃ����ꍇ�ɁA��O��throw���邩�ǂ����̃t���O�B<p>
     * true�̏ꍇ�́A��O��throw���Ȃ��B�f�t�H���g�́Afalse�B<br>
     */
    protected boolean isIgnoreNullProperty;
    
    /**
     * ���OR�v���p�e�B�𐶐�����B<p>
     */
    public OrProperty(){
    }
    
    /**
     * �w�肵���Q�̃v���p�e�B��OR�v���p�e�B�𐶐�����B<p>
     *
     * @param first ���v���p�e�B
     * @param second ���v���p�e�B
     * @exception IllegalArgumentException ������null���w�肵���ꍇ
     */
    public OrProperty(Property first, Property second)
     throws IllegalArgumentException{
        if(first == null || second == null){
            throw new IllegalArgumentException(MSG_00002);
        }
        firstProperty = first;
        secondProperty = second;
    }
    
    /**
     * ���v���p�e�B��ݒ肷��B<p>
     * 
     * @param prop ���v���p�e�B
     * @exception IllegalArgumentException ������null���w�肵���ꍇ
     */
    public void setFirstProperty(Property prop) throws IllegalArgumentException{
        if(prop == null){
            throw new IllegalArgumentException(MSG_00002);
        }
        firstProperty = prop;
    }
    
    /**
     * ���v���p�e�B���擾����B<p>
     * 
     * @return ���v���p�e�B
     */
    public Property getFirstProperty(){
        return firstProperty;
    }
    
    /**
     * �l�X�g�������v���p�e�B���擾����B<p>
     * 
     * @return �l�X�g�������v���p�e�B
     */
    public Property getNestedFirstProperty(){
        if(firstProperty instanceof OrProperty){
            return ((OrProperty)firstProperty).getNestedFirstProperty();
        }else{
            return firstProperty;
        }
    }
    
    /**
     * ���v���p�e�B��ݒ肷��B<p>
     * 
     * @param prop ���v���p�e�B
     * @exception IllegalArgumentException ������null���w�肵���ꍇ
     */
    public void setSecondProperty(Property prop)
     throws IllegalArgumentException{
        if(prop == null){
            throw new IllegalArgumentException(MSG_00002);
        }
        secondProperty = prop;
    }
    
    /**
     * ���v���p�e�B���擾����B<p>
     * 
     * @return ���v���p�e�B
     */
    public Property getSecondProperty(){
        return secondProperty;
    }
    
    /**
     * �w�肵���v���p�e�B���������͂���B<p>
     * �����Ŏw��\�ȕ�����́A<br>
     * &nbsp;���v���p�e�B��|���v���p�e�B��<br>
     * �ł���B<br>
     *
     * @param prop �v���p�e�B������
     * @exception IllegalArgumentException �w�肳�ꂽ�v���p�e�B����������̃v���p�e�B�I�u�W�F�N�g����͂ł��Ȃ��ꍇ
     */
    public void parse(String prop) throws IllegalArgumentException{
        final int index = prop.indexOf('|');
        if(index == -1 || index == 0 || index == prop.length() - 1){
            throw new IllegalArgumentException(MSG_00001 + prop);
        }
        firstProperty = PropertyFactory.createProperty(prop.substring(0, index).trim());
        secondProperty = PropertyFactory.createProperty(
            prop.substring(index + 1).trim()
        );
    }
    
    public Class getPropertyType(Class clazz) throws NoSuchPropertyException{
        return firstProperty.getPropertyType(clazz);
    }
    
    public Class getPropertyType(Object obj)
     throws NoSuchPropertyException, InvocationTargetException{
        Object firstObj = null;
        try{
            firstObj = firstProperty.getProperty(obj);
        }catch(NoSuchPropertyException e){}
        if(firstObj != null){
            return firstProperty.getPropertyType(obj);
        }else{
            return secondProperty.getPropertyType(obj);
        }
    }
    
    public boolean isReadable(Class clazz){
        return firstProperty.isReadable(clazz) || secondProperty.isReadable(clazz);
    }
    
    public boolean isReadable(Object obj){
        return firstProperty.isReadable(obj) || secondProperty.isReadable(obj);
    }
    
    public boolean isWritable(Object obj, Class clazz){
        return false;
    }
    
    public boolean isWritable(Class targetClass, Class clazz){
        return false;
    }
    
    /**
     * �w�肵���I�u�W�F�N�g����A���̃v���p�e�B���\���v���p�e�B�l���擾����B<p>
     *
     * @param obj �ΏۂƂȂ�Bean
     * @return �v���p�e�B�l
     * @exception NoSuchPropertyException �w�肳�ꂽBean���A���̃v���p�e�B���\���A�N�Z�X�\�ȃv���p�e�B�������Ă��Ȃ��ꍇ
     * @exception InvocationTargetException �w�肳�ꂽBean�̃A�N�Z�T���Ăяo�������ʁA��O��throw���ꂽ�ꍇ
     */
    public Object getProperty(Object obj)
     throws NoSuchPropertyException, InvocationTargetException{
        Object firstObj = null;
        try{
            firstObj = firstProperty.getProperty(obj);
        }catch(NoSuchPropertyException e){}
        if(firstObj == null){
            return secondProperty.getProperty(obj);
        }else{
            return firstObj;
        }
    }
    
    /**
     * �T�|�[�g���Ȃ��B<p>
     *
     * @param obj �ΏۂƂȂ�Bean
     * @param value �ݒ肷��v���p�e�B�l
     * @exception NoSuchPropertyException �w�肳�ꂽBean���A���̃v���p�e�B���\���A�N�Z�X�\�ȃv���p�e�B�������Ă��Ȃ��ꍇ
     * @exception InvocationTargetException �w�肳�ꂽBean�̃A�N�Z�T���Ăяo�������ʁA��O��throw���ꂽ�ꍇ
     */
    public void setProperty(Object obj, Object value)
     throws NoSuchPropertyException, InvocationTargetException{
        throw new UnsupportedOperationException();
    }
    
    /**
     * �T�|�[�g���Ȃ��B<p>
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
        throw new UnsupportedOperationException();
    }
    
    /**
     * ���̃v���p�e�B���\���v���p�e�B�����擾����B<p>
     *
     * @return ���v���p�e�B��|���v���p�e�B��
     */
    public String getPropertyName(){
        return firstProperty != null && secondProperty != null
            ? (firstProperty.getPropertyName() + '|' + secondProperty.getPropertyName()) : null;
    }
    
    public void setIgnoreNullProperty(boolean isIgnore){
        isIgnoreNullProperty = isIgnore;
        if(firstProperty != null){
            firstProperty.setIgnoreNullProperty(isIgnoreNullProperty);
        }
        if(secondProperty != null){
            secondProperty.setIgnoreNullProperty(isIgnoreNullProperty);
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
        return "OrProperty{"
            + (firstProperty == null ? "null" : firstProperty.toString())
            + '|' + (secondProperty == null ? "null" : secondProperty.toString()) + '}';
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
        if(!(obj instanceof OrProperty)){
            return false;
        }
        final OrProperty comp = (OrProperty)obj;
        if(firstProperty == null && comp.firstProperty != null
            || firstProperty != null && comp.firstProperty == null){
            return false;
        }else if(firstProperty != null && comp.firstProperty != null
            && !firstProperty.equals(comp.firstProperty)){
            return false;
        }
        if(secondProperty == null && comp.secondProperty == null){
            return true;
        }else if(secondProperty == null){
            return false;
        }else{
            return secondProperty.equals(comp.secondProperty);
        }
    }
    
    /**
     * �n�b�V���l���擾����B<p>
     *
     * @return �n�b�V���l
     */
    public int hashCode(){
        return (firstProperty == null ? 0 : (firstProperty.hashCode() * 2)) + (secondProperty == null ? 0 : (secondProperty.hashCode() * 3));
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
        if(!(obj instanceof OrProperty)){
            return 1;
        }
        final OrProperty comp = (OrProperty)obj;
        if(firstProperty == null && comp.firstProperty != null){
            return -1;
        }else if(firstProperty != null && comp.firstProperty == null){
            return 1;
        }else if(firstProperty != null && comp.firstProperty != null){
            if(firstProperty instanceof Comparable){
                final int val = ((Comparable)firstProperty).compareTo(comp.firstProperty);
                if(val != 0){
                    return val;
                }
            }else{
                return -1;
            }
        }
        if(secondProperty == null && comp.secondProperty == null){
            return 0;
        }else if(secondProperty == null){
            return -1;
        }else{
            if(secondProperty instanceof Comparable){
                return ((Comparable)secondProperty).compareTo(comp.secondProperty);
            }else{
                return -1;
            }
        }
    }
}
