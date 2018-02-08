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

import java.net.*;
import java.io.File;
import java.lang.reflect.Array;

/**
 * Core���[�e�B���e�B�B<p>
 * 
 * @author M.Takata
 */
public class Utility{
    /**
     * �V�X�e���v���p�e�B�Q�ƊJ�n������B<p>
     */
    public static final String SYSTEM_PROPERTY_START = "${";
    /**
     * �V�X�e���v���p�e�B�Q�ƏI��������B<p>
     */
    public static final String SYSTEM_PROPERTY_END = "}";
    
    /**
     * �f�t�H���g�̃T�[�r�X��`�t�@�C����URL���V�X�e���v���p�e�B�Ŏw�肷�邽�߂̃v���p�e�B���B<p>
     */
    private static final String DEFAULT_SERVICE_FILE_PROPERTY_KEY
         = "jp.ossc.nimbus.service.url";
    
    /**
     * �f�t�H���g�̃T�[�r�X��`�t�@�C�����B<p>
     */
    private static final String DEFAULT_SERVICE_FILE
         = "nimbus-service.xml";
    
    private static final String ARRAY_CLASS_SUFFIX = "[]";
    
    /**
     * �T�[�r�X��`URL�̃f�t�H���g�̒l���擾����B<p>
     * �f�t�H���g��URL�̌���́A�ȉ��̏����ōs����B<br>
     * <ol>
     *   <li>�V�X�e���v���p�e�Bjp.ossc.nimbus.service.url�Ŏw�肳�ꂽ�l���A{@link #convertServicePathToURL(String)}��URL�ɕϊ������l</li>
     *   <li>���̃N���X�̃N���X�t�@�C�������[�h���ꂽ�N���X�p�X�ォ��Animbus-service.xml��{@link ClassLoader#getResource(String)}�Ń��\�[�X�Ƃ��Ď擾����URL</li>
     * </ol>
     * 
     * @return �f�t�H���g��URL
     */
    public static URL getDefaultServiceURL(){
        final String urlString = System.getProperty(
            DEFAULT_SERVICE_FILE_PROPERTY_KEY
        );
        
        URL url = null;
        if(urlString != null){
            try{
                url = convertServicePathToURL(urlString);
            }catch(IllegalArgumentException e){
            }
            if(url != null){
                return url;
            }
        }
        
        url = Utility.class.getClassLoader().getResource(
            DEFAULT_SERVICE_FILE
        );
        return url;
    }
    
    /**
     * �w�肳�ꂽ�T�[�r�X��`�̃p�X��URL�ɕϊ�����B<p>
     * �ȉ��̏��ŁA�ϊ����s���B<br>
     * <ol>
     *   <li>�w�肳�ꂽ�p�X��null�A�܂��͋󕶎��̏ꍇ�A{@link #getDefaultServiceURL()}�Ŏ擾�����URL</li>
     *   <li>�w�肳�ꂽ�p�X�����[�J���t�@�C���Ƃ��đ��݂���ꍇ�A���[�J���p�X��URL�ɕϊ�����URL</li>
     *   <li>�w�肳�ꂽ�p�X�����̃N���X�����[�h�����N���X���[�_�̃��\�[�X�Ƃ��đ��݂���ꍇ�A����URL</li>
     *   <li>��L�S�Ăɓ��Ă͂܂�Ȃ��ꍇ�A��O��throw����B</li>
     * </ol>
     * 
     * @param path �T�[�r�X��`�̃p�X
     * @return �T�[�r�X��`��URL
     * @exception IllegalArgumentException �w�肳�ꂽpath���s���ȏꍇ
     */
    public static URL convertServicePathToURL(String path)
     throws IllegalArgumentException{
        if(path == null || path.length() == 0){
            return getDefaultServiceURL();
        }
        
        URL url = null;
        final File localFile = new File(path);
        if(localFile.exists()){
            if(!localFile.isFile()){
                throw new IllegalArgumentException(
                    "ServicePath must be file : " + localFile
                );
            }
            try{
                url = localFile.toURL();
            }catch(MalformedURLException e){
                // ���̗�O�͔������Ȃ��͂�
            }
        }else{
            final ClassLoader classLoader
                 = Thread.currentThread().getContextClassLoader();
            final URL resource = classLoader.getResource(path);
            if(resource != null){
                url = resource;
            }
        }
        if(url == null){
            throw new IllegalArgumentException(
                "ServicePath not found : " + path
            );
        }
        return url;
    }
    
    /**
     * �w�肳�ꂽ��������̃v���p�e�B�Q�ƕ�������V�X�e���v���p�e�B�̒l�ɒu������B<p>
     *
     * @param str ������
     * @return �v���p�e�B�Q�ƕ�������V�X�e���v���p�e�B�̒l�ɒu������������
     */
    public static String replaceSystemProperty(String str){
        String result = str;
        if(result == null){
            return null;
        }
        final int startIndex = result.indexOf(SYSTEM_PROPERTY_START);
        if(startIndex == -1){
            return result;
        }
        final int endIndex = result.indexOf(SYSTEM_PROPERTY_END, startIndex);
        if(endIndex == -1){
            return result;
        }
        final String propStr = result.substring(
            startIndex + SYSTEM_PROPERTY_START.length(),
            endIndex
        );
        String prop = null;
        if(propStr != null && propStr.length() != 0){
            prop = System.getProperty(propStr);
        }
        if(prop == null){
            return result.substring(0, endIndex + SYSTEM_PROPERTY_END.length())
             + replaceSystemProperty(
                result.substring(endIndex + SYSTEM_PROPERTY_END.length())
             );
        }else{
            result = result.substring(0, startIndex) + prop
                 + result.substring(endIndex + SYSTEM_PROPERTY_END.length());
        }
        if(result.indexOf(SYSTEM_PROPERTY_START) != -1){
            return replaceSystemProperty(result);
        }
        return result;
    }
    
    /**
     * �w�肳�ꂽ��������̃v���p�e�B�Q�ƕ�������}�l�[�W���v���p�e�B�̒l�ɒu������B<p>
     *
     * @param str ������
     * @return �v���p�e�B�Q�ƕ�������}�l�[�W���v���p�e�B�̒l�ɒu������������
     */
    public static String replaceManagerProperty(
        ServiceManager manager,
        String str
    ){
        String result = str;
        if(result == null){
            return null;
        }
        final int startIndex = result.indexOf(SYSTEM_PROPERTY_START);
        if(startIndex == -1){
            return result;
        }
        final int endIndex = result.indexOf(SYSTEM_PROPERTY_END, startIndex);
        if(endIndex == -1){
            return result;
        }
        final String propStr = result.substring(
            startIndex + SYSTEM_PROPERTY_START.length(),
            endIndex
        );
        String prop = null;
        if(propStr != null && propStr.length() != 0){
            prop = manager.getProperty(propStr);
        }
        if(prop == null){
            return result.substring(0, endIndex + SYSTEM_PROPERTY_END.length())
             + replaceManagerProperty(
                manager,
                result.substring(endIndex + SYSTEM_PROPERTY_END.length())
             );
        }else{
            result = result.substring(0, startIndex) + prop
                 + result.substring(endIndex + SYSTEM_PROPERTY_END.length());
        }
        if(result.indexOf(SYSTEM_PROPERTY_START) != -1){
            return replaceManagerProperty(manager, result);
        }
        return result;
    }
    
    /**
     * �w�肳�ꂽ��������̃v���p�e�B�Q�ƕ�������}�l�[�W���v���p�e�B�̒l�ɒu������B<p>
     *
     * @param str ������
     * @return �v���p�e�B�Q�ƕ�������}�l�[�W���v���p�e�B�̒l�ɒu������������
     */
    public static String replaceManagerProperty(
        ManagerMetaData manager,
        String str
    ){
        String result = str;
        if(result == null){
            return null;
        }
        final int startIndex = result.indexOf(SYSTEM_PROPERTY_START);
        if(startIndex == -1){
            return result;
        }
        final int endIndex = result.indexOf(SYSTEM_PROPERTY_END, startIndex);
        if(endIndex == -1){
            return result;
        }
        final String propStr = result.substring(
            startIndex + SYSTEM_PROPERTY_START.length(),
            endIndex
        );
        String prop = null;
        if(propStr != null && propStr.length() != 0){
            prop = manager.getProperty(propStr);
        }
        if(prop == null){
            return result.substring(0, endIndex + SYSTEM_PROPERTY_END.length())
             + replaceManagerProperty(
                manager,
                result.substring(endIndex + SYSTEM_PROPERTY_END.length())
             );
        }else{
            result = result.substring(0, startIndex) + prop
                 + result.substring(endIndex + SYSTEM_PROPERTY_END.length());
        }
        if(result.indexOf(SYSTEM_PROPERTY_START) != -1){
            return replaceManagerProperty(manager, result);
        }
        return result;
    }
    
    /**
     * �w�肳�ꂽ��������̃v���p�e�B�Q�ƕ�������T�[�r�X���[�h�\���v���p�e�B�̒l�ɒu������B<p>
     *
     * @param str ������
     * @return �v���p�e�B�Q�ƕ�������T�[�r�X���[�h�\���v���p�e�B�̒l�ɒu������������
     */
    public static String replaceServiceLoderConfig(
        String str,
        ServiceLoaderConfig config
    ){
        if(config == null){
            return str;
        }
        String result = str;
        if(result == null){
            return null;
        }
        final int startIndex = result.indexOf(SYSTEM_PROPERTY_START);
        if(startIndex == -1){
            return result;
        }
        final int endIndex = result.indexOf(SYSTEM_PROPERTY_END, startIndex);
        if(endIndex == -1){
            return result;
        }
        final String propStr = result.substring(
            startIndex + SYSTEM_PROPERTY_START.length(),
            endIndex
        );
        String prop = null;
        if(propStr != null && propStr.length() != 0){
            prop = config.getProperty(propStr);
        }
        if(prop == null){
            return result.substring(0, endIndex + SYSTEM_PROPERTY_END.length())
             + replaceServiceLoderConfig(
                result.substring(endIndex + SYSTEM_PROPERTY_END.length()),
                config
             );
        }else{
            result = result.substring(0, startIndex) + prop
                 + result.substring(endIndex + SYSTEM_PROPERTY_END.length());
        }
        if(result.indexOf(SYSTEM_PROPERTY_START) != -1){
            return replaceServiceLoderConfig(result, config);
        }
        return result;
    }
    
    /**
     * �w�肳�ꂽ��������̃v���p�e�B�Q�ƕ�������T�[�o�v���p�e�B�̒l�ɒu������B<p>
     *
     * @param str ������
     * @return �v���p�e�B�Q�ƕ�������T�[�o�v���p�e�B�̒l�ɒu������������
     */
    public static String replaceServerProperty(String str){
        String result = str;
        if(result == null){
            return null;
        }
        final int startIndex = result.indexOf(SYSTEM_PROPERTY_START);
        if(startIndex == -1){
            return result;
        }
        final int endIndex = result.indexOf(SYSTEM_PROPERTY_END, startIndex);
        if(endIndex == -1){
            return result;
        }
        final String propStr = result.substring(
            startIndex + SYSTEM_PROPERTY_START.length(),
            endIndex
        );
        String prop = null;
        if(propStr != null && propStr.length() != 0){
            prop = ServiceManagerFactory.getProperty(propStr);
        }
        if(prop == null){
            return result.substring(0, endIndex + SYSTEM_PROPERTY_END.length())
             + replaceServerProperty(
                result.substring(endIndex + SYSTEM_PROPERTY_END.length())
             );
        }else{
            result = result.substring(0, startIndex) + prop
                 + result.substring(endIndex + SYSTEM_PROPERTY_END.length());
        }
        if(result.indexOf(SYSTEM_PROPERTY_START) != -1){
            return replaceServerProperty(result);
        }
        return result;
    }
    
    /**
     * ���ϐ��v���p�e�B���擾����B<p>
     * {@link System#getProperty(String)} &gt; {@link ServiceLoaderConfig#getProperty(String)} &gt; {@link ServiceManager#getProperty(String)} &gt; {@link ServiceManagerFactory#getProperty(String)}
     *
     * @param name �v���p�e�B��
     * @param config ServiceLoaderConfig
     * @param manager ServiceManager
     * @param metaData ���^�f�[�^
     */
    public static String getProperty(
        String name,
        ServiceLoaderConfig config,
        ServiceManager manager,
        MetaData metaData
    ){
        String prop = System.getProperty(name);
        if(prop != null){
            return prop;
        }
        if(config != null){
            prop = config.getProperty(name);
            if(prop != null){
                return prop;
            }
        }
        if(manager != null){
            prop = manager.getProperty(name);
            if(prop != null){
                return prop;
            }
        }
        if(metaData != null){
            ServerMetaData serverData = null;
            ManagerMetaData mngData = null;
            MetaData parent = metaData;
            do{
                if(parent == null){
                    break;
                }else if(mngData == null
                     && parent instanceof ManagerMetaData){
                    mngData = (ManagerMetaData)parent;
                }else if(serverData == null
                     && parent instanceof ServerMetaData){
                    serverData = (ServerMetaData)parent;
                    break;
                }
            }while((parent = parent.getParent()) != null);
            if(mngData != null){
                prop = mngData.getProperty(name);
                if(prop != null){
                    return prop;
                }
            }
            if(serverData != null){
                prop = serverData.getProperty(name);
                if(prop != null){
                    return prop;
                }
            }
        }
        return ServiceManagerFactory.getProperty(name);
    }
    
    public static Class convertStringToClass(String typeStr)
     throws ClassNotFoundException{
        return Utility.convertStringToClass(typeStr, false);
    }
    
    /**
     * �����񂩂�N���X�ɕϊ�����B<p>
     *
     * @param typeStr ���S�C���N���X��
     * @param isWrapp �v���~�e�B�u�^�̃��b�p�ɕϊ����邩�̃t���O
     */
    public static Class convertStringToClass(
        String typeStr,
        boolean isWrapp
    ) throws ClassNotFoundException{
        Class type = null;
        if(typeStr != null){
            if(Byte.TYPE.getName().equals(typeStr)){
                type = isWrapp ? Byte.class : Byte.TYPE;
            }else if(Character.TYPE.getName().equals(typeStr)){
                type = isWrapp ? Character.class : Character.TYPE;
            }else if(Short.TYPE.getName().equals(typeStr)){
                type = isWrapp ? Short.class : Short.TYPE;
            }else if(Integer.TYPE.getName().equals(typeStr)){
                type = isWrapp ? Integer.class : Integer.TYPE;
            }else if(Long.TYPE.getName().equals(typeStr)){
                type = isWrapp ? Long.class : Long.TYPE;
            }else if(Float.TYPE.getName().equals(typeStr)){
                type = isWrapp ? Float.class : Float.TYPE;
            }else if(Double.TYPE.getName().equals(typeStr)){
                type = isWrapp ? Double.class : Double.TYPE;
            }else if(Boolean.TYPE.getName().equals(typeStr)){
                type = isWrapp ? Boolean.class : Boolean.TYPE;
            }else{
                if(typeStr.endsWith(ARRAY_CLASS_SUFFIX)
                    && typeStr.length() > 2){
                    final Class elementType = convertStringToClass(
                        typeStr.substring(0, typeStr.length() - 2),
                        false
                    );
                    type = Array.newInstance(elementType, 0).getClass();
                }else{
                    type = Class.forName(
                        typeStr,
                        true,
                        NimbusClassLoader.getInstance()
                    );
                }
            }
        }
        return type;
    }
}