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
package jp.ossc.nimbus.core;

import java.util.*;
import java.net.*;
import java.io.*;
import java.security.*;
import java.security.cert.Certificate;

/**
 * Nimbus�N���X���[�_�B<p>
 * Nimbus�̃T�[�r�X�N���X�̃��[�h���s���N���X���[�_�ł���B<br>
 * ���̃N���X���[�_�̃C���X�^���X�́A{@link NimbusClassLoader#getInstance()}�Ŏ擾���邱�Ƃ��ł���B���̃C���X�^���X�̓X���b�h�R���e�L�X�g�N���X���[�_�P�ʂɐ��������B�������ꂽ�C���X�^���X�́A�X���b�h�R���e�L�X�g�N���X���[�_���L�[�Ɏ�Q�Ƃŕێ�����邽�߁A�X���b�h�R���e�L�X�g�N���X���[�_���j�������Ǝ����I�ɔj�������B<br>
 * <p>
 * �܂��A���̃N���X���[�_�ɂ́A{@link AspectTranslator}��o�^���鎖���\�ł���BAspectTranslator���o�^���ꂽ��ԂŁA�N���X�̃��[�h���˗������ƁAAspectTranslator�ɂ��N���X�t�@�C���̕ϊ����s���āA�N���X�����[�h�����B<br>
 * �A���A�N���X�̃��[�h���s����̂́A�N���X�t�@�C���̕ϊ��ΏۂɂȂ��Ă���N���X�����ŁA���[�h���ꂽ�N���X���ŎQ�Ƃ����ʂ̃N���X�́A�N���X�t�@�C���̕ϊ��ΏۂłȂ���΁A���̃N���X���[�_�Ń��[�h���s���Ȃ��B<br>
 * ����́A�A�v���P�[�V�����T�[�o�Ȃǂ̕��G�ȃN���X���[�_�\�������R���e�i�ɑ΂���z���ł���B�X�^���h�A���[���̃A�v���P�[�V�����ł́A[@link #setLoadNotTransformClass(boolean) setLoadNotTransformClass(true)}���Ăяo�����ŁA�ϊ��ΏۊO�̃N���X�̃��[�h���A���̃N���X���[�_�ōs����悤�ɂȂ�B<br>
 * 
 * @author M.Takata
 * @see AspectTranslator
 */
public class NimbusClassLoader extends ClassLoader{
    
    /**
     * �X���b�h�R���e�L�X�g�N���X���[�_�Ɋ֘A�t���āA���̃N���X���[�_�̃C���X�^���X��ێ������Q�ƃ}�b�v�B<p>
     */
    protected static final Map classLoader = new WeakHashMap();
    
    /**
     * VM���x���œo�^���ꂽ{@link AspectTranslator}�̃��X�g�B<p>
     */
    protected static final Map vmTranslators = new HashMap();
    
    /**
     * ThreadContext���x���œo�^���ꂽ{@link AspectTranslator}�̃��X�g�B<p>
     */
    protected final Map translators = new HashMap();
    
    private static final String CLASS_EXTEND = ".class";
    private boolean isLoadNotTransformClass = false;
    
    /**
     * �w�肳�ꂽ�N���X���[�_��e�Ɏ��C���X�^���X�𐶐�����B<p>
     *
     * @param parent �e�N���X���[�_
     */
    protected NimbusClassLoader(ClassLoader parent){
        super(parent);
    }
    
    /**
     * �N���X���[�_�̃C���X�^���X���擾����B<p>
     * ���̃��\�b�h�Ŏ擾�����N���X���[�_�́A�X���b�h�R���e�L�X�g�N���X���[�_��e�Ɏ��B<br>
     * 
     * @return �X���b�h�R���e�L�X�g�N���X���[�_�Ɋ֘A�t����ꂽNimbus�N���X���[�_�̃C���X�^���X
     */
    public static synchronized NimbusClassLoader getInstance(){
        final ClassLoader contextLoader
             = Thread.currentThread().getContextClassLoader();
        if(contextLoader instanceof NimbusClassLoader){
            return (NimbusClassLoader)contextLoader;
        }
        NimbusClassLoader loader
             = (NimbusClassLoader)classLoader.get(contextLoader);
        if(loader == null){
            loader = new NimbusClassLoader(contextLoader);
            classLoader.put(contextLoader, loader);
        }
        return loader;
    }
    
    /**
     * VM���x���ŃN���X���[�h���ɃN���X�t�@�C���ϊ����s��AspectTranslator��o�^����B<p>
     *
     * @param translator �o�^����AspectTranslator
     */
    public static void addVMAspectTranslator(AspectTranslator translator){
        synchronized(vmTranslators){
            List list = null;
            if(vmTranslators.containsKey(translator.getAspectKey())){
                list = (List)vmTranslators.get(translator.getAspectKey());
            }else{
                list = new ArrayList();
                vmTranslators.put(translator.getAspectKey(), list);
            }
            if(!list.contains(translator)){
                list.add(translator);
            }
        }
    }
    
    /**
     * VM���x���ŃN���X���[�h���ɃN���X�t�@�C���ϊ����s��AspectTranslator��o�^��������B<p>
     *
     * @param translator �o�^��������AspectTranslator
     */
    public static void removeVMAspectTranslator(AspectTranslator translator){
        synchronized(vmTranslators){
            if(vmTranslators.containsKey(translator.getAspectKey())){
                final List list = (List)vmTranslators
                    .get(translator.getAspectKey());
                list.remove(translator);
                if(list.size() == 0){
                    vmTranslators.remove(translator.getAspectKey());
                }
            }
        }
    }
    
    /**
     * VM���x���œo�^����Ă���AspectTranslator���擾����B<p>
     *
     * @return AspectTranslator�̔z��
     */
    public static AspectTranslator[] getVMAspectTranslators(){
        synchronized(vmTranslators){
            final AspectTranslator[] result
                 = new AspectTranslator[vmTranslators.size()];
            final List[] lists = (List[])vmTranslators.values()
                .toArray(new List[vmTranslators.size()]);
            for(int i = 0; i < lists.length; i++){
                result[i] = (AspectTranslator)lists[i].get(0);
            }
            return result;
        }
    }
    
    /**
     * ThreadContext���x���ŃN���X���[�h���ɃN���X�t�@�C���ϊ����s��AspectTranslator��o�^����B<p>
     *
     * @param translator �o�^����AspectTranslator
     */
    public void addAspectTranslator(AspectTranslator translator){
        synchronized(translators){
            List list = null;
            if(translators.containsKey(translator.getAspectKey())){
                list = (List)translators.get(translator.getAspectKey());
            }else{
                list = new ArrayList();
                translators.put(translator.getAspectKey(), list);
            }
            if(!list.contains(translator)){
                list.add(translator);
            }
        }
    }
    
    /**
     * ThreadContext���x���ŃN���X���[�h���ɃN���X�t�@�C���ϊ����s��AspectTranslator��o�^��������B<p>
     *
     * @param translator �o�^��������AspectTranslator
     */
    public void removeAspectTranslator(AspectTranslator translator){
        synchronized(translators){
            if(translators.containsKey(translator.getAspectKey())){
                final List list = (List)translators
                    .get(translator.getAspectKey());
                list.remove(translator);
                if(list.size() == 0){
                    translators.remove(translator.getAspectKey());
                }
            }
        }
    }
    
    /**
     * ThreadContext���x���œo�^����Ă���AspectTranslator���擾����B<p>
     *
     * @return AspectTranslator�̔z��
     */
    public AspectTranslator[] getAspectTranslators(){
        synchronized(translators){
            final AspectTranslator[] result
                 = new AspectTranslator[translators.size()];
            final List[] lists = (List[])translators.values()
                .toArray(new List[translators.size()]);
            for(int i = 0; i < lists.length; i++){
                result[i] = (AspectTranslator)lists[i].get(0);
            }
            return result;
        }
    }
    
    /**
     * {@link AspectTranslator}�̕ϊ��ΏۂłȂ��N���X�����[�h���邩�ǂ�����ݒ肷��B<p>
     *
     * @param isLoad {@link AspectTranslator}�̕ϊ��ΏۂłȂ��N���X�����[�h����ꍇtrue�B�f�t�H���g��false
     */
    public void setLoadNotTransformClass(boolean isLoad){
        isLoadNotTransformClass = isLoad;
    }
    
    /**
     * {@link AspectTranslator}�̕ϊ��ΏۂłȂ��N���X�����[�h���邩�ǂ����𔻒肷��B<p>
     *
     * @return true�̏ꍇ{@link AspectTranslator}�̕ϊ��ΏۂłȂ��N���X�����[�h����
     */
    public boolean isLoadNotTransformClass(){
        return isLoadNotTransformClass;
    }
    
    /**
     * �w�肳�ꂽ�N���X�����̃N���X���[�_�Ń��[�h����B<p>
     * {@link #isLoadNotTransformClass()}�̒l�Ɋւ�炸�A�ϊ��ΏۂłȂ��N���X�����̃N���X���[�_�Ŗ����I�Ƀ��[�h����B<br>
     * 
     * @param name �N���X��
     * @return ���[�h�����N���X�I�u�W�F�N�g
     * @exception ClassNotFoundException �w�肳�ꂽ�N���X��������Ȃ��ꍇ
     */
    public synchronized Class loadClassLocally(String name)
     throws ClassNotFoundException{
        return loadClass(name, false);
    }
    
    /**
     * �w�肳�ꂽ�N���X�����̃N���X���[�_�Ń��[�h����B<p>
     * {@link #isLoadNotTransformClass()}�̒l�Ɋւ�炸�A�ϊ��ΏۂłȂ��N���X�����̃N���X���[�_�Ŗ����I�Ƀ��[�h����B<br>
     * 
     * @param name �N���X��
     * @param resolve true �̏ꍇ�́A�N���X�����ߏ�������
     * @return ���[�h�����N���X�I�u�W�F�N�g
     * @exception ClassNotFoundException �w�肳�ꂽ�N���X��������Ȃ��ꍇ
     */
    public synchronized Class loadClassLocally(String name, boolean resolve)
     throws ClassNotFoundException{
        return loadClass(name, resolve, true);
    }
    
    /**
     * �w�肳�ꂽ�N���X�����[�h����B<p>
     * �o�^���ꂽ{@link AspectTranslator}�̕ϊ��ΏۂƂȂ��Ă���N���X�́A���̃N���X���[�_�Ń��[�h����B�����łȂ��N���X�́A�e�N���X���[�_�ł���X���b�h�R���e�L�X�g���[�_�ɈϏ�����B�A���A{@link #isLoadNotTransformClass()}�̒l��true�̏ꍇ�́A�ϊ��ΏۂłȂ��N���X�����̃N���X���[�_�Ń��[�h����B<br>
     * 
     * @param name �N���X��
     * @param resolve true �̏ꍇ�́A�N���X�����ߏ�������
     * @return ���[�h�����N���X�I�u�W�F�N�g
     * @exception ClassNotFoundException �w�肳�ꂽ�N���X��������Ȃ��ꍇ
     */
    protected synchronized Class loadClass(String name, boolean resolve)
     throws ClassNotFoundException{
        return loadClass(name, resolve, false);
    }
    
    /**
     * �w�肳�ꂽ�N���X�����[�h����B<p>
     * 
     * @param name �N���X��
     * @param resolve true �̏ꍇ�́A�N���X�����ߏ�������
     * @param isLocally true�̏ꍇ�́A{@link #isLoadNotTransformClass()}�̒l�Ɋւ�炸�A�ϊ��ΏۂłȂ��N���X�����̃N���X���[�_�Ŗ����I�Ƀ��[�h����
     * @return ���[�h�����N���X�I�u�W�F�N�g
     * @exception ClassNotFoundException �w�肳�ꂽ�N���X��������Ȃ��ꍇ
     */
    protected synchronized Class loadClass(
        String name,
        boolean resolve,
        boolean isLocally
    ) throws ClassNotFoundException{
        if(vmTranslators.size() == 0 && translators.size() == 0
             && !isLoadNotTransformClass){
            return super.loadClass(name, resolve);
        }
        if(isNonLoadableClassName(name)){
            return super.loadClass(name, resolve);
        }
        final boolean isNonTranslatableClass = isNonTranslatableClassName(name);
        if(isNonTranslatableClass && !isLoadNotTransformClass){
            return super.loadClass(name, resolve);
        }
        final Class loadedClass = findLoadedClass(name);
        if(loadedClass != null){
            return loadedClass;
        }
        
        final URL classUrl = getClassURL(name);
        if(classUrl == null){
            return super.loadClass(name, resolve);
        }
        final byte[] bytecode = loadByteCode(classUrl);
        if(bytecode == null){
            return super.loadClass(name, resolve);
        }
        final URL codeSourceUrl = getCodeSourceURL(name, classUrl);
        if(codeSourceUrl == null){
            return super.loadClass(name, resolve);
        }
        final ProtectionDomain domain = getProtectionDomain(codeSourceUrl);
        boolean isTransform = false;
        byte[] transformedBytes = bytecode;
        if(!isNonTranslatableClass){
            synchronized(vmTranslators){
                final Object[] keys = vmTranslators.keySet().toArray();
                for(int i = 0, max = keys.length; i < max; i++){
                    final AspectTranslator translator = (AspectTranslator)
                        ((List)vmTranslators.get(keys[i])).get(0);
                    final byte[] tmpBytes = translator.transform(
                        this,
                        name,
                        domain,
                        transformedBytes
                    );
                    if(tmpBytes != null){
                        isTransform = true;
                        transformedBytes = tmpBytes;
                    }
                }
            }
            synchronized(translators){
                final Object[] keys = translators.keySet().toArray();
                for(int i = 0, max = keys.length; i < max; i++){
                    final AspectTranslator translator = (AspectTranslator)
                        ((List)translators.get(keys[i])).get(0);
                    final byte[] tmpBytes = translator.transform(
                        this,
                        name,
                        domain,
                        transformedBytes
                    );
                    if(tmpBytes != null){
                        isTransform = true;
                        transformedBytes = tmpBytes;
                    }
                }
            }
        }
        if(isTransform || isLocally || isLoadNotTransformClass){
            definePackage(name);
            final Class clazz = defineClass(
                name,
                transformedBytes,
                0,
                transformedBytes.length,
                domain
            );
            if(resolve){
                resolveClass(clazz);
            }
            return clazz;
        }else{
            int innerClassIndex = name.lastIndexOf('$');
            if(innerClassIndex != -1
                && name.length() - 1 != innerClassIndex
                && findLoadedClass(name.substring(0, innerClassIndex)) != null
            ){
                return loadClass(name, resolve, true);
            }
            return super.loadClass(name, resolve);
        }
    }
    
    /**
     * ���[�h�ΏۂƂȂ�Ȃ��N���X�𔻒肷��B<p>
     * �ȉ��̃N���X�́A�@���Ȃ�ꍇ���ϊ��ΏۂƂȂ�Ȃ��B<br>
     * <ul>
     *   <li>"org.omg."����n�܂�N���X</li>
     *   <li>"org.w3c."����n�܂�N���X</li>
     *   <li>"org.xml.sax."����n�܂�N���X</li>
     *   <li>"sunw."����n�܂�N���X</li>
     *   <li>"sun."����n�܂�N���X</li>
     *   <li>"java."����n�܂�N���X</li>
     *   <li>"javax."����n�܂�N���X</li>
     *   <li>"com.sun."����n�܂�N���X</li>
     *   <li>"javassist."����n�܂�N���X</li>
     * </ul>
     * 
     * @param classname �N���X��
     * @return ���[�h�ΏۂƂȂ�Ȃ��N���X�̏ꍇ�Atrue
     */
    public static boolean isNonLoadableClassName(String classname){
      return classname.startsWith("org.omg.")
              || classname.startsWith("org.w3c.")
              || classname.startsWith("org.xml.sax.")
              || classname.startsWith("sunw.")
              || classname.startsWith("sun.")
              || classname.startsWith("java.")
              || classname.startsWith("javax.")
              || classname.startsWith("com.sun.")
              || classname.equals("jp.ossc.nimbus.core.NimbusClassLoader")
              || classname.equals("jp.ossc.nimbus.core.AspectTranslator");
    }
    
    /**
     * �ϊ��ΏۂƂȂ�Ȃ��N���X�𔻒肷��B<p>
     * �ȉ��̃N���X�́A�@���Ȃ�ꍇ���ϊ��ΏۂƂȂ�Ȃ��B<br>
     * <ul>
     *   <li>"jp.ossc.nimbus.service.aop."����n�܂�N���X</li>
     * </ul>
     * 
     * @param classname �N���X��
     * @return �ϊ��ΏۂƂȂ�Ȃ��N���X�̏ꍇ�Atrue
     */
    public static boolean isNonTranslatableClassName(String classname){
      return classname.startsWith("jp.ossc.nimbus.service.aop.");
    }
    
    /**
     * �w�肳�ꂽ�N���X���̃p�b�P�[�W���`����B<p>
     * �p�b�P�[�W�����ɑ��݂���ꍇ�ɂ́A�������Ȃ��B
     *
     * @param className �N���X��
     */
    protected void definePackage(String className){
        int index = className.lastIndexOf('.');
        if(index == -1){
            return;
        }
        try{
            definePackage(
                className.substring(0, index),
                null,
                null,
                null,
                null,
                null,
                null,
                null
            );
        }catch(IllegalArgumentException alreadyDone){
        }
    }
    
    /**
     * �w�肳�ꂽ�N���X�̃N���X�t�@�C����URL���擾����B<p>
     *
     * @param classname �N���X��
     * @return �N���X�t�@�C����URL
     */
    protected URL getClassURL(String classname){
        final String classRsrcName = classname.replace('.', '/') + CLASS_EXTEND;
        return getResource(classRsrcName);
    }
    
    /**
     * �w�肳�ꂽURL�̃N���X�t�@�C���̃o�C�g�R�[�h���擾����B<p>
     *
     * @param classURL �N���X�t�@�C����URL
     * @return �N���X�t�@�C���̃o�C�g�R�[�h�B�ǂݍ��߂Ȃ��ꍇ��null��Ԃ��B
     */
    protected byte[] loadByteCode(URL classURL){
        byte[] bytecode = null;
        InputStream is = null;
        try{
            is = classURL.openStream();
            final ByteArrayOutputStream baos = new ByteArrayOutputStream();
            byte[] tmp = new byte[1024];
            int read = 0;
            while((read = is.read(tmp)) > 0){
                baos.write(tmp, 0, read);
            }
            bytecode = baos.toByteArray();
        }catch(IOException e){
            return null;
        }finally{
            if(is != null){
                try{
                    is.close();
                }catch(IOException e){
                }
            }
        }
        return bytecode;
    }
    
    /**
     * �w�肳�ꂽ�N���X�̃N���X�t�@�C����{@link CodeSource}��URL���擾����B<p>
     * 
     * @param classname �N���X
     * @param classURL �N���X�t�@�C����URL
     * @return CodeSource�̈ʒu�����߂�URL
     */
    protected URL getCodeSourceURL(String classname, URL classURL){
        final String classRsrcName = classname.replace('.', '/') + CLASS_EXTEND;
        String urlAsString = classURL.toString();
        final int index = urlAsString.indexOf(classRsrcName);
        if(index == -1){
            return classURL;
        }
        urlAsString = urlAsString.substring(0, index);
        try{
            return new URL(urlAsString);
        }catch(MalformedURLException e){
            return null;
        }
    }
    
    /**
     * �w�肳�ꂽURL��{@link CodeSource}�ɑΉ�����{@link PermissionCollection}��������{@link ProtectionDomain}���擾����B<p>
     *
     * @param codesourceUrl CodeSource�̈ʒu�����߂�URL
     * @return �w�肳�ꂽURL��CodeSource�ɑΉ�����PermissionCollection
     */
    protected ProtectionDomain getProtectionDomain(URL codesourceUrl){
    	Certificate[] certificates = null;
        final CodeSource cs = new CodeSource(codesourceUrl, certificates);
        final PermissionCollection permissions
             = Policy.getPolicy().getPermissions(cs);
        return new ProtectionDomain(cs, permissions);
    }
}