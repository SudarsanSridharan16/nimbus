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
package jp.ossc.nimbus.util;

import java.util.*;

/**
 * �N���X�ɑ΂���C�ӂ̃I�u�W�F�N�g�̃}�b�s���O�c���[�B<p>
 * ����N���X�ɑΉ�����C�ӂ̃I�u�W�F�N�g���A�N���X�̌p���֌W�̃c���[�Ń}�b�s���O���ĊǗ�����B<br>
 * {@link #add(Class, Object)}�ŃN���X�ƔC�ӂ̃I�u�W�F�N�g�̃}�b�s���O��o�^�ł���B{@link #getValue(Class)}�ŃN���X���w�肷��ƑΉ�����I�u�W�F�N�g���擾�ł���B�����Ō����Ή�����I�u�W�F�N�g�Ƃ́A�w�肵���N���X�Ɉ�v����N���X�Ƀ}�b�s���O����Ă���I�u�W�F�N�g�A�܂��́A��v����N���X���Ȃ��ꍇ�́A�o�^����Ă���N���X�̒��ōł��߂��p���֌W�ɂ���X�[�p�[�N���X�Ƀ}�b�s���O���ꂽ�I�u�W�F�N�g���w���B�܂��A�����Ō����N���X�ɂ́A�C���^�t�F�[�X���܂܂��B<br>
 * 
 * @author M.Takata
 */
public class ClassMappingTree implements java.io.Serializable{
    
    private static final long serialVersionUID = 4471328701464216810L;
    
    private final TreeElement rootElement;
    
    private final Map classMap = new HashMap();
    
    /**
     * null��{@link java.lang.Object}�N���X�Ƀ}�b�s���O���āA�}�b�s���O�c���[�̃��[�g�Ƃ��āA���̃N���X�̃C���X�^���X�𐶐�����B<p>
     */
    public ClassMappingTree(){
        this(null);
    }
    
    /**
     * �w�肳�ꂽ�I�u�W�F�N�g��{@link java.lang.Object}�N���X�Ƀ}�b�s���O���āA�}�b�s���O�c���[�̃��[�g�Ƃ��āA���̃N���X�̃C���X�^���X�𐶐�����B<p>
     *
     * @param root java.lang.Object�N���X�ɑΉ�����I�u�W�F�N�g
     */
    public ClassMappingTree(Object root){
        rootElement = new TreeElement(java.lang.Object.class, root);
        classMap.put(java.lang.Object.class, rootElement);
    }
    
    /**
     * �N���X�ƔC�ӂ̃I�u�W�F�N�g�̃}�b�s���O��o�^����B<p>
     *
     * @param clazz �N���X�I�u�W�F�N�g
     * @param value �C�ӂ̃I�u�W�F�N�g
     */
    public void add(Class clazz, Object value){
        add(clazz, value, false);
    }
    
    /**
     * �N���X�ƔC�ӂ̃I�u�W�F�N�g�̃}�b�s���O��o�^����B<p>
     *
     * @param clazz �N���X�I�u�W�F�N�g
     * @param value �C�ӂ̃I�u�W�F�N�g
     * @param replace ���ɓ����N���X�ɑ΂��ă}�b�s���O���ꂽ�I�u�W�F�N�g������ꍇ�ɁA����ƒu��������ꍇ�́Atrue�B�N���X�ɑ΂��ă}�b�s���O���ꂽ�I�u�W�F�N�g�𕡐����e���āA�ǉ�����ꍇ�́Afalse
     */
    public void add(Class clazz, Object value, boolean replace){
        if(classMap.containsKey(clazz)){
            final TreeElement element = (TreeElement)classMap.get(clazz);
            if(replace){
                element.setTarget(value);
            }else{
                element.addTarget(value);
            }
        }else{
            TreeElement element = new TreeElement(clazz, value);
            final TreeElement nearestParent
                 = rootElement.getNearestParentElement(clazz);
            if(nearestParent != null){
                if(nearestParent.hasChild()){
                    final Iterator children
                         = nearestParent.getChildElements().iterator();
                    while(children.hasNext()){
                        final TreeElement child = (TreeElement)children.next();
                        if(child.isChildOf(clazz)){
                            nearestParent.moveChild(element, child);
                        }
                    }
                }
                element = nearestParent.addChild(element);
            }
            classMap.put(clazz, element);
        }
    }
    
    /**
     * �w�肳�ꂽ�N���X�ɑΉ�����I�u�W�F�N�g���擾����B<p>
     * �����Ō����Ή�����I�u�W�F�N�g�Ƃ́A�w�肵���N���X�Ɉ�v����N���X�Ƀ}�b�s���O����Ă���I�u�W�F�N�g���w���B<br>
     * �Ή�����I�u�W�F�N�g���������݂���ꍇ�ɂ́A�ŏ��ɓo�^���ꂽ�I�u�W�F�N�g��Ԃ��B<br>
     *
     * @param clazz �N���X�I�u�W�F�N�g
     * @return �w�肳�ꂽ�N���X�ɑΉ�����I�u�W�F�N�g
     * @see #getValuesOf(Class)
     */
    public Object getValueOf(Class clazz){
        final Object[] values = getValuesOf(clazz);
        return values != null && values.length > 0 ? values[0] : null;
    }
    
    /**
     * �w�肳�ꂽ�N���X�ɑΉ�����I�u�W�F�N�g�z����擾����B<p>
     *
     * @param clazz �N���X�I�u�W�F�N�g
     * @return �w�肳�ꂽ�N���X�ɑΉ�����I�u�W�F�N�g�z��
     */
    public Object[] getValuesOf(Class clazz){
        final List list = getValueListOf(clazz);
        return list == null ? null : list.toArray();
    }
    
    /**
     * �w�肳�ꂽ�N���X�ɑΉ�����I�u�W�F�N�g���X�g���擾����B<p>
     *
     * @param clazz �N���X�I�u�W�F�N�g
     * @return �w�肳�ꂽ�N���X�ɑΉ�����I�u�W�F�N�g���X�g
     */
    public List getValueListOf(Class clazz){
        if(classMap.containsKey(clazz)){
            return ((TreeElement)classMap.get(clazz)).getTargets();
        }else{
            return null;
        }
    }
    
    /**
     * �w�肳�ꂽ�N���X�ɑΉ�����I�u�W�F�N�g���擾����B<p>
     * �����Ō����Ή�����I�u�W�F�N�g�Ƃ́A�w�肵���N���X�Ɉ�v����N���X�Ƀ}�b�s���O����Ă���I�u�W�F�N�g�A�܂��́A��v����N���X���Ȃ��ꍇ�́A�o�^����Ă���N���X�̒��ōł��߂��p���֌W�ɂ���X�[�p�[�N���X�Ƀ}�b�s���O���ꂽ�I�u�W�F�N�g���w���B<br>
     * �Ή�����I�u�W�F�N�g���������݂���ꍇ�ɂ́A�ŏ��ɓo�^���ꂽ�I�u�W�F�N�g��Ԃ��B<br>
     *
     * @param clazz �N���X�I�u�W�F�N�g
     * @return �w�肳�ꂽ�N���X�ɑΉ�����I�u�W�F�N�g
     * @see #getValues(Class)
     */
    public Object getValue(Class clazz){
        final Object[] values = getValues(clazz);
        return values != null && values.length > 0 ? values[0] : null;
    }
    
    /**
     * �w�肳�ꂽ�N���X�ɑΉ�����I�u�W�F�N�g�z����擾����B<p>
     *
     * @param clazz �N���X�I�u�W�F�N�g
     * @return �w�肳�ꂽ�N���X�ɑΉ�����I�u�W�F�N�g�z��
     */
    public Object[] getValues(Class clazz){
        return getValueList(clazz).toArray();
    }
    
    /**
     * �w�肳�ꂽ�N���X�ɑΉ�����I�u�W�F�N�g���X�g���擾����B<p>
     *
     * @param clazz �N���X�I�u�W�F�N�g
     * @return �w�肳�ꂽ�N���X�ɑΉ�����I�u�W�F�N�g���X�g
     */
    public List getValueList(Class clazz){
        if(clazz == null){
            return rootElement.getTargets();
        }
        if(classMap.containsKey(clazz)){
            return ((TreeElement)classMap.get(clazz)).getTargets();
        }else{
            final TreeElement nearestParent
                 = rootElement.getNearestParentElement(clazz);
            if(nearestParent == null){
                return rootElement.getTargets();
            }
            return nearestParent.getTargets();
        }
    }
    
    /**
     * �w�肳�ꂽ�N���X�Ƀ}�b�s���O����Ă���w��I�u�W�F�N�g���폜����B<p>
     *
     * @param clazz �N���X�I�u�W�F�N�g
     * @param value �C�ӂ̃I�u�W�F�N�g
     */
    public void remove(Class clazz, Object value){
        if(classMap.containsKey(clazz)){
            final TreeElement removeElement
                 = (TreeElement)classMap.get(clazz);
            removeElement.removeTarget(value);
            if(removeElement.getTargets().size() == 0){
                remove(clazz);
            }
        }
    }
    
    /**
     * �w�肳�ꂽ�N���X�Ƀ}�b�s���O����Ă���S�ẴI�u�W�F�N�g���폜����B<p>
     *
     * @param clazz �N���X�I�u�W�F�N�g
     */
    public void remove(Class clazz){
        if(!Object.class.equals(clazz) && classMap.containsKey(clazz)){
            final TreeElement removedElement
                 = (TreeElement)classMap.remove(clazz);
            if(removedElement.parent != null){
                removedElement.parent.removeChild(removedElement);
            }
        }
    }
    
    /**
     * �}�b�s���O����Ă���S�ẴI�u�W�F�N�g���폜����B<p>
     */
    public void clear(){
        final Iterator classes = new HashSet(classMap.keySet()).iterator();
        while(classes.hasNext()){
            final Class clazz = (Class)classes.next();
            remove(clazz);
        }
    }
    
    /**
     * �o�^����Ă���N���X�}�b�s���O�̕�����\����Ԃ��B<p>
     *
     * @return �N���X�}�b�s���O�̕�����\��
     */
    public String toString(){
        return super.toString() + classMap;
    }
    
    private static class TreeElement implements java.io.Serializable{
        
        private static final long serialVersionUID = 4875545362697617699L;
        
        TreeElement parent;
        private Map children;
        Class clazz;
        private List targets = new ArrayList();
        public TreeElement(Class clazz, Object target){
            this(null, clazz, target);
        }
        public TreeElement(TreeElement parent, Class clazz, Object target){
            this.parent = parent;
            this.clazz = clazz;
            setTarget(target);
        }
        public TreeElement addChild(TreeElement child){
            if(children == null){
                children = new HashMap();
            }
            if(children.containsKey(child.clazz)){
                final TreeElement element
                     = (TreeElement)children.get(child.clazz);
                element.addTargets(child.getTargets());
                return element;
            }else{
                children.put(child.clazz, child);
                child.parent = this;
                return child;
            }
        }
        public TreeElement getChild(Class clazz){
            if(children == null){
                return null;
            }
            return (TreeElement)children.get(clazz);
        }
        public Collection getChildElements(){
            return children == null ? null : new HashSet(children.values());
        }
        public int childrenNumber(){
            return children == null ? 0 : children.size();
        }
        public void moveChild(TreeElement newParent, TreeElement child){
            children.remove(child.clazz);
            newParent.addChild(child);
        }
        public void removeChild(TreeElement child){
            final TreeElement removedChild
                 = (TreeElement)children.remove(child.clazz);
            if(removedChild != null && removedChild.hasChild()
                && parent != null && parent.children != null){
                parent.children.putAll(removedChild.children);
            }
        }
        public void setTarget(Object target){
            targets.clear();
            if(target != null){
                targets.add(target);
            }
        }
        public void addTarget(Object target){
            if(target != null){
                targets.add(target);
            }
        }
        public void addTargets(List targets){
            targets.addAll(targets);
        }
        public List getTargets(){
            return targets;
        }
        public void removeTarget(Object target){
            targets.remove(target);
        }
        public boolean hasChild(){
            return children != null && children.size() != 0;
        }
        public TreeElement getNearestParentElement(Class clazz){
            if(!isParentOf(clazz)){
                return null;
            }
            if(!hasChild()){
                return this;
            }
            TreeElement nearestElement = null;
            final Iterator elements = children.values().iterator();
            while(elements.hasNext()){
                final TreeElement element = (TreeElement)elements.next();
                nearestElement = element.getNearestParentElement(clazz);
                if(nearestElement != null){
                    return nearestElement;
                }
            }
            return this;
        }
        public boolean isChildOf(Class clazz){
            return clazz.isAssignableFrom(this.clazz);
        }
        public boolean isParentOf(Class clazz){
            return this.clazz.isAssignableFrom(clazz);
        }
        public Object getTarget(){
            return targets.size() == 0 ? null : targets.get(0);
        }
        public boolean equals(Object obj){
            if(obj == null){
                return false;
            }
            if(obj == this){
                return true;
            }
            if(!(obj instanceof TreeElement)){
                return false;
            }
            final TreeElement element = (TreeElement)obj;
            if(clazz == null){
                return element.clazz == null;
            }
            return clazz.equals(element.clazz);
        }
        public int hashCode(){
            return clazz == null ? 0 : clazz.hashCode();
        }
        public String toString(){
            return targets.toString();
        }
    }
}
