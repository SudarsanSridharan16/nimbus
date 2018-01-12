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

import java.io.*;
import java.net.*;
import java.util.*;

import junit.framework.TestCase;

//import jp.ossc.nimbus.service.message.MessageRecordFactory;
//import jp.ossc.nimbus.service.log.Logger;
import jp.ossc.nimbus.service.repository.Repository;

public class ServiceManagerFactoryTest extends TestCase{
   
    public ServiceManagerFactoryTest(String arg0) {
        super(arg0);
    }
    
    public static void main(String[] args) {
        junit.textui.TestRunner.run(ServiceManagerFactoryTest.class);
    }
    
    /**
     * �N���X�p�X��̃f�t�H���g�T�[�r�X��`�t�@�C���̃��[�h�ƃA�����[�h�̃e�X�g�B<p>
     * �����F
     * <ul>
     *   <li>�f�t�H���g���̃T�[�r�X��`�t�@�C��<a href="resources/nimbus-service.xml">"nimbus-service.xml"</a>���N���X�p�X��ɒu���B</li>
     * </ul>
     * �m�F�F
     * <ul>
     *   <li>ServiceManagerFactory#loadManager()��true��Ԃ��B</li>
     *   <li>ServiceManagerFactory#checkLoadManagerCompleted()��true��Ԃ��B</li>
     *   <li>ServiceManagerFactory#findManager()���Ăяo����ServiceManager���擾�ł���B</li>
     *   <li>ServiceManager#getService(String)���Ăяo���ăT�[�r�X��"Service0"�̃T�[�r�X���擾�ł���B</li>
     *   <li>ServiceManagerFactory#unloadManager()�̌Ăяo����́AServiceManager#getService(String)���Ăяo���ăT�[�r�X��"Service0"�̃T�[�r�X���擾�ł��Ȃ��B</li>
     * </ul>
     */
    public void testLoadOnClassPath1() throws Exception {
        File def = null;
        try{
            def = TestUtility.copyOnClassPath("nimbus-service.xml");
            assertTrue(ServiceManagerFactory.loadManager());
            assertTrue(ServiceManagerFactory.checkLoadManagerCompleted());
            final ServiceManager manager = ServiceManagerFactory.findManager();
            assertNotNull(manager);
            try{
                manager.getService("Service0");
            }catch(ServiceNotFoundException e){
                fail(e.getMessage());
            }
            ServiceManagerFactory.unloadManager();
            try{
                manager.getService("Service0");
                fail("Service0 was not unloaded.");
            }catch(ServiceNotFoundException e){
            }
        }finally{
            ServiceManagerFactory.unloadManager();
            if(def != null){
                def.delete();
            }
        }
    }
    
    /**
     * ���݂��Ȃ��N���X�p�X��̃f�t�H���g�T�[�r�X��`�t�@�C���̃��[�h�ƃA�����[�h�̃e�X�g�B<p>
     * �����F
     * <ul>
     *   <li>�f�t�H���g���̃T�[�r�X��`�t�@�C��<a href="resources/nimbus-service.xml">"nimbus-service.xml"</a>���N���X�p�X��ɒu���Ȃ��B</li>
     * </ul>
     * �m�F�F
     * <ul>
     *   <li>ServiceManagerFactory#loadManager()��false��Ԃ��B</li>
     *   <li>ServiceManagerFactory#checkLoadManagerCompleted()��true��Ԃ��B</li>
     *   <li>ServiceManagerFactory#findManager()�̖߂�l��null�B</li>
     *   <li>ServiceManagerFactory#unloadManager()�̌Ăяo�����s����B</li>
     * </ul>
     */
    public void testLoadOnClassPath2() throws Exception {
        try{
            assertFalse(ServiceManagerFactory.loadManager());
            assertTrue(ServiceManagerFactory.checkLoadManagerCompleted());
            final ServiceManager manager = ServiceManagerFactory.findManager();
            assertNull(manager);
        }finally{
            ServiceManagerFactory.unloadManager();
        }
    }
    
    /**
     * �N���X�p�X��̔C�ӂ̃T�[�r�X��`�t�@�C���̃��[�h�ƃA�����[�h�̃e�X�g�B<p>
     * �����F
     * <ul>
     *   <li>�f�t�H���g���ł͂Ȃ��T�[�r�X��`�t�@�C��<a href="resources/nimbus-service1.xml">"nimbus-service1.xml"</a>���N���X�p�X��ɒu���B</li>
     * </ul>
     * �m�F�F
     * <ul>
     *   <li>ServiceManagerFactory#loadManager(String)�̈����Ƀf�t�H���g���ł͂Ȃ��T�[�r�X��`�t�@�C��"nimbus-service1.xml"���w�肵�ČĂяo���A�߂�l��true��Ԃ��B</li>
     *   <li>ServiceManagerFactory#checkLoadManagerCompleted()��true��Ԃ��B</li>
     *   <li>ServiceManagerFactory#findManager()���Ăяo����ServiceManager���擾�ł���B</li>
     *   <li>ServiceManager#getService(String)���Ăяo���ăT�[�r�X��"Service1"�̃T�[�r�X���擾�ł���B</li>
     *   <li>ServiceManagerFactory#unloadManager(String)�̌Ăяo����́AServiceManager#getService(String)���Ăяo���ăT�[�r�X��"Service1"�̃T�[�r�X���擾�ł��Ȃ��B</li>
     * </ul>
     */
    public void testLoadOnClassPath3() throws Exception {
        File def = null;
        try{
            def = TestUtility.copyOnClassPath("nimbus-service1.xml");
            assertTrue(
                ServiceManagerFactory.loadManager("nimbus-service1.xml")
            );
            assertTrue(ServiceManagerFactory.checkLoadManagerCompleted());
            final ServiceManager manager = ServiceManagerFactory.findManager();
            assertNotNull(manager);
            try{
                manager.getService("Service1");
            }catch(ServiceNotFoundException e){
                fail(e.getMessage());
            }
            ServiceManagerFactory.unloadManager("nimbus-service1.xml");
            try{
                manager.getService("Service1");
                fail();
            }catch(ServiceNotFoundException e){
            }
        }finally{
            ServiceManagerFactory.unloadManager("nimbus-service1.xml");
            if(def != null){
                def.delete();
            }
        }
    }
    
    /**
     * �N���X�p�X��̔C�ӂ̑��݂��Ȃ��T�[�r�X��`�t�@�C���̃��[�h�ƃA�����[�h�̃e�X�g�B<p>
     * �����F
     * <ul>
     *   <li>�f�t�H���g���ł͂Ȃ��T�[�r�X��`�t�@�C��<a href="resources/nimbus-service1.xml">"nimbus-service1.xml"</a>���N���X�p�X��ɒu���Ȃ��B</li>
     * </ul>
     * �m�F�F
     * <ul>
     *   <li>ServiceManagerFactory#loadManager(String)�̈����ɑ��݂��Ȃ��T�[�r�X��`�t�@�C��"nimbus-service1.xml"���w�肵�ČĂяo���A�߂�l��false��Ԃ��B</li>
     *   <li>ServiceManagerFactory#checkLoadManagerCompleted()��true��Ԃ��B</li>
     *   <li>ServiceManagerFactory#findManager()���Ăяo����ServiceManager���擾�ł��Ȃ��B</li>
     *   <li>ServiceManagerFactory#unloadManager(String)�̌Ăяo�����ł���B</li>
     * </ul>
     */
    public void testLoadOnClassPath4() throws Exception {
        try{
            assertFalse(
                ServiceManagerFactory.loadManager("nimbus-service10.xml")
            );
            assertTrue(ServiceManagerFactory.checkLoadManagerCompleted());
            final ServiceManager manager = ServiceManagerFactory.findManager();
            assertNull(manager);
        }finally{
            ServiceManagerFactory.unloadManager("nimbus-service10.xml");
        }
    }
    
    /**
     * DTD���g�����������T�[�r�X��`�t�@�C���̌��؂̃e�X�g�B<p>
     * �����F
     * <ul>
     *   <li>�f�t�H���g���̃T�[�r�X��`�t�@�C��<a href="resources/nimbus-service.xml">"nimbus-service.xml"</a>���N���X�p�X��ɒu���B</li>
     * </ul>
     * �m�F�F
     * <ul>
     *   <li>ServiceManagerFactory#loadManager(String, boolean, boolean)�̈����Ƀf�t�H���g���̃T�[�r�X��`�t�@�C��"nimbus-service.xml"�Afalse�Atrue���w�肵�āA�Ăяo�����߂�l��true��Ԃ��B</li>
     *   <li>ServiceManagerFactory#checkLoadManagerCompleted()��true��Ԃ��B</li>
     *   <li>ServiceManagerFactory#findManager(String)�ŁA"Nimbus"���w�肵�ČĂяo���AServiceManager���擾�ł���B</li>
     *   <li>ServiceManager#getService(String)���Ăяo���ăT�[�r�X��"Service0"�̃T�[�r�X���擾�ł���B</li>
     *   <li>ServiceManagerFactory#unloadManager(String)�̌Ăяo����́AServiceManager#getService(String)���Ăяo���ăT�[�r�X��"Service0"�̃T�[�r�X���擾�ł��Ȃ��B</li>
     * </ul>
     */
    public void testLoadWithValidate1() throws Exception{
        File def = null;
        try{
            def = TestUtility.copyOnClassPath("nimbus-service.xml");
            assertTrue(
                ServiceManagerFactory.loadManager(
                    "nimbus-service.xml",
                    false,
                    true
                )
            );
            assertTrue(ServiceManagerFactory.checkLoadManagerCompleted());
            final ServiceManager manager
                 = ServiceManagerFactory.findManager(ServiceManager.DEFAULT_NAME);
            assertNotNull(manager);
            try{
                manager.getService("Service0");
            }catch(ServiceNotFoundException e){
                fail(e.getMessage());
            }
            ServiceManagerFactory.unloadManager();
            try{
                manager.getService("Service0");
                fail("Service0 was not unloaded.");
            }catch(ServiceNotFoundException e){
            }
        }finally{
            ServiceManagerFactory.unloadManager();
            if(def != null){
                def.delete();
            }
        }
    }
    
    /**
     * DTD���g����������T�[�r�X��`�t�@�C���̌��؂̃e�X�g�B<p>
     * �����F
     * <ul>
     *   <li>�f�t�H���g���̃T�[�r�X��`�t�@�C��<a href="resources/nimbus-service11.xml">"nimbus-service.xml"</a>���N���X�p�X��ɒu���B</li>
     * </ul>
     * �m�F�F
     * <ul>
     *   <li>ServiceManagerFactory#loadManager(String, boolean, boolean)�̈����Ƀf�t�H���g���̃T�[�r�X��`�t�@�C��"nimbus-service.xml"�Afalse�Atrue���w�肵�āA�Ăяo�����߂�l��false��Ԃ��B</li>
     *   <li>ServiceManagerFactory#checkLoadManagerCompleted()��true��Ԃ��B</li>
     *   <li>ServiceManagerFactory#findManager()�̖߂�l��null�B</li>
     *   <li>ServiceManagerFactory#unloadManager()�̌Ăяo�����s����B</li>
     * </ul>
     */
    public void testLoadWithValidate2() throws Exception{
        File def = null;
        try{
            def = TestUtility.copyOnClassPath(
                "nimbus-service11.xml",
                "nimbus-service.xml"
            );
            assertFalse(
                ServiceManagerFactory.loadManager(
                    "nimbus-service.xml",
                    false,
                    true
                )
            );
            assertTrue(ServiceManagerFactory.checkLoadManagerCompleted());
            final ServiceManager manager = ServiceManagerFactory.findManager();
            assertNull(manager);
        }finally{
            ServiceManagerFactory.unloadManager();
            if(def != null){
                def.delete();
            }
        }
    }
    
    /**
     * DTD���g��Ȃ�������T�[�r�X��`�t�@�C���̌��؂̃e�X�g�B������v�f�͖��������B<p>
     * �����F
     * <ul>
     *   <li>�f�t�H���g���̃T�[�r�X��`�t�@�C��<a href="resources/nimbus-service11.xml">"nimbus-service.xml"</a>���N���X�p�X��ɒu���B</li>
     * </ul>
     * �m�F�F
     * <ul>
     *   <li>ServiceManagerFactory#loadManager(String, boolean, boolean)�̈����Ƀf�t�H���g���̃T�[�r�X��`�t�@�C��"nimbus-service.xml"�Afalse�Afalse���w�肵�āA�Ăяo�����߂�l��true��Ԃ��B</li>
     *   <li>ServiceManagerFactory#checkLoadManagerCompleted()��true��Ԃ��B</li>
     *   <li>ServiceManagerFactory#findManager()���Ăяo���AServiceManager���擾�ł���B</li>
     *   <li>ServiceManager#getService(String)���Ăяo���ăT�[�r�X��"Service0"�̃T�[�r�X���擾�ł��Ȃ��B</li>
     *   <li>ServiceManagerFactory#unloadManager()�̌Ăяo�����s����B</li>
     * </ul>
     */
    public void testLoadWithValidate3() throws Exception{
        File def = null;
        try{
            def = TestUtility.copyOnClassPath(
                "nimbus-service11.xml",
                "nimbus-service.xml"
            );
            assertTrue(
                ServiceManagerFactory.loadManager(
                    "nimbus-service.xml",
                    false,
                    false
                )
            );
            assertTrue(ServiceManagerFactory.checkLoadManagerCompleted());
            final ServiceManager manager
                 = ServiceManagerFactory.findManager();
            assertNotNull(manager);
            try{
                manager.getService("Service0");
                fail("Service0 was not unloaded.");
            }catch(ServiceNotFoundException e){
            }
        }finally{
            ServiceManagerFactory.unloadManager();
            if(def != null){
                def.delete();
            }
        }
    }
    
    /**
     * ���[�J���p�X��̔C�ӂ̃T�[�r�X��`�t�@�C���̃��[�h�ƃA�����[�h�̃e�X�g�B<p>
     * �����F
     * <ul>
     *   <li>�f�t�H���g���ł͂Ȃ��T�[�r�X��`�t�@�C��<a href="resources/nimbus-service1.xml">"nimbus-service1.xml"</a>���e���|�����̈�ɒu���B</li>
     * </ul>
     * �m�F�F
     * <ul>
     *   <li>ServiceManagerFactory#loadManager(String)�̈����Ƀe���|�����ɃR�s�[�����T�[�r�X��`�t�@�C�������w�肵�ČĂяo���A�߂�l��true��Ԃ��B</li>
     *   <li>ServiceManagerFactory#checkLoadManagerCompleted()��true��Ԃ��B</li>
     *   <li>ServiceManagerFactory#findManager()���Ăяo����ServiceManager���擾�ł���B</li>
     *   <li>ServiceManager#getService(String)���Ăяo���ăT�[�r�X��"Service1"�̃T�[�r�X���擾�ł���B</li>
     *   <li>ServiceManagerFactory#unloadManager(String)�̌Ăяo����́AServiceManager#getService(String)���Ăяo���ăT�[�r�X��"Service1"�̃T�[�r�X���擾�ł��Ȃ��B</li>
     * </ul>
     */
    public void testLoadOnDir1() throws Exception {
        final File def = TestUtility.copyOnTemp("nimbus-service1.xml");
        try{
            assertTrue(
                ServiceManagerFactory.loadManager(def.getCanonicalPath())
            );
            assertTrue(ServiceManagerFactory.checkLoadManagerCompleted());
            final ServiceManager manager = ServiceManagerFactory.findManager();
            assertNotNull(manager);
            try{
                manager.getService("Service1");
            }catch(ServiceNotFoundException e){
                fail(e.getMessage());
            }
            ServiceManagerFactory.unloadManager(def.getCanonicalPath());
            try{
                manager.getService("Service1");
                fail("Service1 was not unloaded.");
            }catch(ServiceNotFoundException e){
            }
        }finally{
            ServiceManagerFactory.unloadManager(def.getCanonicalPath());
            if(def != null){
                def.delete();
            }
        }
    }
    
    /**
     * �T�[�r�X�̑����ݒ�̃e�X�g�B<p>
     * �����F
     * <ul>
     *   <li>�f�t�H���g���ł͂Ȃ��T�[�r�X��`�t�@�C��<a href="resources/nimbus-service2.xml">"nimbus-service2.xml"</a>���e���|�����̈�ɒu���B</li>
     * </ul>
     * �m�F�F
     * <ul>
     *   <li>ServiceManagerFactory#loadManager(String)�̈����Ƀe���|�����ɃR�s�[�����T�[�r�X��`�t�@�C�������w�肵�ČĂяo���A�߂�l��true��Ԃ��B</li>
     *   <li>ServiceManagerFactory#checkLoadManagerCompleted()��true��Ԃ��B</li>
     *   <li>ServiceManagerFactory#findManager()���Ăяo����ServiceManager���擾�ł���B</li>
     *   <li>ServiceManager#getService(String)���Ăяo���ăT�[�r�X��"Service2"�̃T�[�r�X���擾�ł���B</li>
     *   <li>�擾�����T�[�r�X��Service#getState()�̒l��Service#STARTED�ł���B</li>
     *   <li>ServiceManager#getServiceObject(String)���Ăяo���ăT�[�r�X��"Service2"�̃T�[�r�X�I�u�W�F�N�g���擾�ł���B</li>
     *   <li>�擾�����T�[�r�X�I�u�W�F�N�g����A�T�[�r�X��`�Őݒ肵�������̒l���擾�ł���B</li>
     * </ul>
     */
    public void testServiceAttributeSetting1() throws Exception {
        final File def = TestUtility.copyOnTemp("nimbus-service2.xml");
        try{
            assertTrue(
                ServiceManagerFactory.loadManager(def.getCanonicalPath())
            );
            assertTrue(ServiceManagerFactory.checkLoadManagerCompleted());
            final ServiceManager manager = ServiceManagerFactory.findManager();
            assertNotNull(manager);
            Service service2 = null;
            try{
                service2 = manager.getService("Service2");
            }catch(ServiceNotFoundException e){
                fail(e.getMessage());
            }
            assertEquals(Service.STARTED, service2.getState());
            TestServiceBase serviceObj2 = null;
            try{
                serviceObj2 = (TestServiceBase)manager.getServiceObject(
                    "Service2"
                );
            }catch(ServiceNotFoundException e){
                fail(e.getMessage());
            }
            assertEquals("AAA", serviceObj2.getString());
            final String[] stringArray = serviceObj2.getStringArray();
            assertEquals("AAA", stringArray[0]);
            assertEquals("BBB", stringArray[1]);
            assertEquals("CCC", stringArray[2]);
            assertEquals(100, serviceObj2.getInt());
            assertEquals(
                new java.net.URL("http://nimbus.org/index.html"),
                serviceObj2.getURL()
            );
            final Properties prop = serviceObj2.getProperties();
            assertEquals("1", prop.getProperty("key1"));
            assertEquals("2", prop.getProperty("key2"));
            assertEquals("3", prop.getProperty("key3"));
            assertEquals(200l, serviceObj2.longField);
        }finally{
            ServiceManagerFactory.unloadManager(def.getCanonicalPath());
            if(def != null){
                def.delete();
            }
        }
    }
    
    /**
     * �T�[�r�X�̑����ݒ�̃e�X�g�B�����attribute�v�f���܂ށB<p>
     * �����F
     * <ul>
     *   <li>�f�t�H���g���ł͂Ȃ��T�[�r�X��`�t�@�C��<a href="resources/nimbus-service3.xml">"nimbus-service3.xml"</a>���e���|�����̈�ɒu���B</li>
     * </ul>
     * �m�F�F
     * <ul>
     *   <li>ServiceManagerFactory#loadManager(String)�̈����Ƀe���|�����ɃR�s�[�����T�[�r�X��`�t�@�C�������w�肵�ČĂяo���A�߂�l��true��Ԃ��B</li>
     *   <li>ServiceManagerFactory#checkLoadManagerCompleted()��true��Ԃ��B</li>
     *   <li>ServiceManagerFactory#findManager()���Ăяo����ServiceManager���擾�ł���B</li>
     *   <li>ServiceManager#getService(String)���Ăяo���ăT�[�r�X��"Service2"�̃T�[�r�X���擾�ł���B</li>
     *   <li>�擾�����T�[�r�X��Service#getState()�̒l��Service#STARTED�ł���B</li>
     *   <li>ServiceManager#getServiceObject(String)���Ăяo���ăT�[�r�X��"Service2"�̃T�[�r�X�I�u�W�F�N�g���擾�ł���B</li>
     *   <li>�擾�����T�[�r�X�I�u�W�F�N�g����A�T�[�r�X��`�Ő������ݒ肵�������̒l���ݒ肵���ʂ�Ɏ擾�ł���B</li>
     *   <li>�擾�����T�[�r�X�I�u�W�F�N�g����A�T�[�r�X��`�Ō���Đݒ肵�������̒l���f�t�H���g�l�Ƃ��Ď擾�ł���B</li>
     * </ul>
     */
    public void testServiceAttributeSetting2() throws Exception {
        final File def = TestUtility.copyOnTemp("nimbus-service3.xml");
        try{
            assertTrue(
                ServiceManagerFactory.loadManager(def.getCanonicalPath())
            );
            assertTrue(ServiceManagerFactory.checkLoadManagerCompleted());
            final ServiceManager manager = ServiceManagerFactory.findManager();
            assertNotNull(manager);
            Service service2 = null;
            try{
                service2 = manager.getService("Service2");
            }catch(ServiceNotFoundException e){
                fail(e.getMessage());
            }
            assertEquals(Service.STARTED, service2.getState());
            TestServiceBase serviceObj2 = null;
            try{
                serviceObj2 = (TestServiceBase)manager.getServiceObject(
                    "Service2"
                );
            }catch(ServiceNotFoundException e){
                fail(e.getMessage());
            }
            assertEquals(0, serviceObj2.getInt());
            assertEquals(
                new java.net.URL("http://nimbus.org/index.html"),
                serviceObj2.getURL()
            );
        }finally{
            ServiceManagerFactory.unloadManager(def.getCanonicalPath());
            if(def != null){
                def.delete();
            }
        }
    }
    
    /**
     * �T�[�r�X�̈ˑ��֌W�̃e�X�g�BService3��Service1�Ɉˑ����Ă���B<p>
     * �����F
     * <ul>
     *   <li>�T�[�r�X��`�t�@�C��<a href="resources/nimbus-service4.xml">"nimbus-service4.xml"</a>���e���|�����̈�ɒu���B</li>
     *   <li>�T�[�r�X��`�t�@�C��<a href="resources/nimbus-service1.xml">"nimbus-service1.xml"</a>���e���|�����̈�ɒu���B</li>
     * </ul>
     * �m�F�F
     * <ul>
     *   <li>ServiceManagerFactory#loadManager(String)�̈����Ƀe���|�����ɃR�s�[�����T�[�r�X��`�t�@�C��"nimbus-service4.xml"�̃t�@�C�������w�肵�ČĂяo���A�߂�l��true��Ԃ��B</li>
     *   <li>ServiceManagerFactory#checkLoadManagerCompleted()��false��Ԃ��B</li>
     *   <li>ServiceManager#getService(String)���Ăяo���ăT�[�r�X��"Service3"�̃T�[�r�X���擾�ł���B</li>
     *   <li>�擾����"Service3"�̃T�[�r�X��Service#getState()�̒l��Service#DESTROYED�ł���B</li>
     *   <li>ServiceManager#getServiceObject(String)���Ăяo���ăT�[�r�X��"Service3"�̃T�[�r�X�I�u�W�F�N�g���擾�ł���B</li>
     *   <li>�擾����"Service3"�̃T�[�r�X�I�u�W�F�N�g��TestServiceBase#getString()���Ăяo���āA�T�[�r�X��`�Őݒ肵���l���擾�ł��Ȃ��B</li>
     *   <li>ServiceManager#getService(String)���Ăяo���ăT�[�r�X��"Service1"�̃T�[�r�X���擾�ł��Ȃ��B</li>
     *   <li>�T�[�r�X��`�t�@�C��"nimbus-service1.xml"�����[�h������A�擾����"Service3"�̃T�[�r�X��Service#getState()�̒l��Service#STARTED�ł���B</li>
     *   <li>ServiceManagerFactory#loadManager(String)�̈����Ƀe���|�����ɃR�s�[�����T�[�r�X��`�t�@�C��"nimbus-service1.xml"�̃t�@�C�������w�肵�ČĂяo���A�߂�l��true��Ԃ��B</li>
     *   <li>ServiceManagerFactory#checkLoadManagerCompleted()��true��Ԃ��B</li>
     *   <li>"Service3"�̃T�[�r�X��Service#getState()�̒l��Service#STARTED�ł���B</li>
     *   <li>"Service3"�̃T�[�r�X�I�u�W�F�N�g��TestServiceBase#getString()���Ăяo���āA�T�[�r�X��`�Őݒ肵���l���擾�ł���B</li>
     *   <li>ServiceManager#getService(String)���Ăяo���ăT�[�r�X��"Service1"�̃T�[�r�X���擾�ł���B</li>
     *   <li>�擾����"Service1"�̃T�[�r�X��Service#getState()�̒l��Service#STARTED�ł���B</li>
     *   <li>�T�[�r�X��`�t�@�C��"nimbus-service4.xml"��ServiceManagerFactory#unloadManager(String)�ŃA�����[�h������AServiceManager#getService(String)���Ăяo���ăT�[�r�X��"Service3"�̃T�[�r�X���擾�ł��Ȃ��B</li>
     *   <li>�T�[�r�X��`�t�@�C��"nimbus-service4.xml"��ServiceManagerFactory#unloadManager(String)�ŃA�����[�h������AServiceManager#getService(String)���Ăяo���ăT�[�r�X��"Service1"�̃T�[�r�X���擾�ł���B</li>
     *   <li>�T�[�r�X��`�t�@�C��"nimbus-service1.xml"��ServiceManagerFactory#unloadManager(String)�ŃA�����[�h������AServiceManager#getService(String)���Ăяo���ăT�[�r�X��"Service1"�̃T�[�r�X���擾�ł��Ȃ��B</li>
     * </ul>
     */
    public void testServiceDependency1() throws Exception {
        final File def4 = TestUtility.copyOnTemp("nimbus-service4.xml");
        final File def1 = TestUtility.copyOnTemp("nimbus-service1.xml");
        try{
            assertTrue(
                ServiceManagerFactory.loadManager(def4.getCanonicalPath())
            );
            assertFalse(ServiceManagerFactory.checkLoadManagerCompleted());
            final ServiceManager manager = ServiceManagerFactory.findManager();
            assertNotNull(manager);
            Service service3 = null;
            try{
                service3 = manager.getService("Service3");
            }catch(ServiceNotFoundException e){
                fail(e.getMessage());
            }
            assertEquals(Service.CREATED, service3.getState());
            TestServiceBase serviceObj3 = null;
            try{
                serviceObj3 = (TestServiceBase)manager.getServiceObject(
                    "Service3"
                );
            }catch(ServiceNotFoundException e){
                fail(e.getMessage());
            }
            assertNotNull(serviceObj3.getString());
            Service service1 = null;
            try{
                service1 = manager.getService("Service1");
            }catch(ServiceNotFoundException e){
                service1 = null;
            }
            assertNull(service1);
            
            assertTrue(
                ServiceManagerFactory.loadManager(def1.getCanonicalPath())
            );
            assertTrue(ServiceManagerFactory.checkLoadManagerCompleted());
            assertEquals(Service.STARTED, service3.getState());
            assertEquals(ServiceManager.DEFAULT_NAME, serviceObj3.getString());
            try{
                service1 = manager.getService("Service1");
            }catch(ServiceNotFoundException e){
                fail(e.getMessage());
            }
            assertNotNull(service1);
            assertEquals(Service.STARTED, service1.getState());
            ServiceManagerFactory.unloadManager(def4.getCanonicalPath());
            try{
                service3 = manager.getService("Service3");
            }catch(ServiceNotFoundException e){
                service3 = null;
            }
            assertNull(service3);
            try{
                service1 = manager.getService("Service1");
            }catch(ServiceNotFoundException e){
                fail(e.getMessage());
            }
            assertNotNull(service1);
            assertEquals(Service.STARTED, service1.getState());
            ServiceManagerFactory.unloadManager(def1.getCanonicalPath());
            try{
                service1 = manager.getService("Service1");
            }catch(ServiceNotFoundException e){
                service1 = null;
            }
            assertNull(service1);
        }finally{
            ServiceManagerFactory.unloadManager(def1.getCanonicalPath());
            ServiceManagerFactory.unloadManager(def4.getCanonicalPath());
            if(def1 != null){
                def1.delete();
            }
            if(def4 != null){
                def4.delete();
            }
        }
    }
    
    /**
     * �ˑ��֌W�̂���T�[�r�X�̍ăf�v���C�̃e�X�g�B<p>
     * �����F
     * <ul>
     *   <li>�f�t�H���g���ł͂Ȃ��T�[�r�X��`�t�@�C��<a href="resources/nimbus-service4.xml">"nimbus-service4.xml"</a>���e���|�����̈�ɒu���B</li>
     *   <li>�f�t�H���g���ł͂Ȃ��T�[�r�X��`�t�@�C��<a href="resources/nimbus-service1.xml">"nimbus-service1.xml"</a>���e���|�����̈�ɒu���B</li>
     * </ul>
     * �m�F�F
     * <ul>
     *   <li>ServiceManagerFactory#loadManager(String)�̈����Ƀe���|�����ɃR�s�[�����T�[�r�X��`�t�@�C��"nimbus-service4.xml"�̃t�@�C�������w�肵�ČĂяo���A�߂�l��true��Ԃ��B</li>
     *   <li>ServiceManagerFactory#checkLoadManagerCompleted()��false��Ԃ��B</li>
     *   <li>ServiceManagerFactory#findManager()���Ăяo����ServiceManager���擾�ł���B</li>
     *   <li>ServiceManager#getService(String)���Ăяo���ăT�[�r�X��"Service3"�̃T�[�r�X���擾�ł���B</li>
     *   <li>ServiceManager#getServiceObject(String)���Ăяo���ăT�[�r�X��"Service3"�̃T�[�r�X�I�u�W�F�N�g���擾�ł���B</li>
     *   <li>�擾����"Service3"�̃T�[�r�X�I�u�W�F�N�g��TestServiceBase#getString()���Ăяo���āA�T�[�r�X��`�Őݒ肵���l���擾�ł��Ȃ��B</li>
     *   <li>�擾����"Service3"�̃T�[�r�X��Service#getState()�̒l��Service#DESTROYED�ł���B</li>
     *   <li>ServiceManager#getService(String)���Ăяo���ăT�[�r�X��"Service1"�̃T�[�r�X���擾�ł��Ȃ��B</li>
     *   <li>ServiceManagerFactory#loadManager(String)�̈����Ƀe���|�����ɃR�s�[�����T�[�r�X��`�t�@�C��"nimbus-service1.xml"�̃t�@�C�������w�肵�ČĂяo���A�߂�l��true��Ԃ��B</li>
     *   <li>ServiceManagerFactory#checkLoadManagerCompleted()��true��Ԃ��B</li>
     *   <li>�擾����"Service3"�̃T�[�r�X�I�u�W�F�N�g��TestServiceBase#getString()���Ăяo���āA�T�[�r�X��`�Őݒ肵���l���擾�ł���B</li>
     *   <li>�擾����"Service3"�̃T�[�r�X��Service#getState()�̒l��Service#STARTED�ł���B</li>
     *   <li>ServiceManager#getService(String)���Ăяo���ăT�[�r�X��"Service1"�̃T�[�r�X���擾�ł���B</li>
     *   <li>�擾����"Service1"�̃T�[�r�X��Service#getState()�̒l��Service#STARTED�ł���B</li>
     *   <li>�T�[�r�X��`�t�@�C��"nimbus-service4.xml"��ServiceManagerFactory#unloadManager(String)�ŃA�����[�h������AServiceManager#getService(String)���Ăяo���ăT�[�r�X��"Service1"�A"Service3"�̃T�[�r�X���擾�ł��Ȃ��B</li>
     *   <li>ServiceManagerFactory#loadManager(String)�̈����Ƀe���|�����ɃR�s�[�����T�[�r�X��`�t�@�C��"nimbus-service1.xml"�̃t�@�C�������w�肵�ČĂяo���A�߂�l��true��Ԃ��B</li>
     *   <li>ServiceManagerFactory#checkLoadManagerCompleted()��true��Ԃ��B</li>
     *   <li>ServiceManager#getService(String)���Ăяo���ăT�[�r�X��"Service1"��"Service3"�̃T�[�r�X���擾�ł���B</li>
     *   <li>�T�[�r�X��`�t�@�C��"nimbus-service1.xml"��"nimbus-service4.xml"��ServiceManagerFactory#unloadManager(String)�ŃA�����[�h������AServiceManager#getService(String)���Ăяo���ăT�[�r�X��"Service1"��"Service3"�̃T�[�r�X���擾�ł��Ȃ��B</li>
     * </ul>
     */
    public void testServiceDependency2() throws Exception {
        final File def4 = TestUtility.copyOnTemp("nimbus-service4.xml");
        final File def1 = TestUtility.copyOnTemp("nimbus-service1.xml");
        try{
            assertTrue(
                ServiceManagerFactory.loadManager(def4.getCanonicalPath())
            );
            assertFalse(ServiceManagerFactory.checkLoadManagerCompleted());
            final ServiceManager manager = ServiceManagerFactory.findManager();
            assertNotNull(manager);
            Service service3 = null;
            try{
                service3 = manager.getService("Service3");
            }catch(ServiceNotFoundException e){
                fail(e.getMessage());
            }
            assertNotNull(service3);
            TestServiceBase serviceObj3 = null;
            try{
                serviceObj3 = (TestServiceBase)manager
                    .getServiceObject("Service3");
            }catch(ServiceNotFoundException e){
                fail(e.getMessage());
            }
            assertNotNull(serviceObj3);
            assertNotNull(serviceObj3.getString());
            assertEquals(Service.CREATED, service3.getState());
            Service service1 = null;
            try{
                service1 = manager.getService("Service1");
            }catch(ServiceNotFoundException e){
            }
            assertNull(service1);
            
            assertTrue(
                ServiceManagerFactory.loadManager(def1.getCanonicalPath())
            );
            assertTrue(ServiceManagerFactory.checkLoadManagerCompleted());
            assertEquals(ServiceManager.DEFAULT_NAME, serviceObj3.getString());
            assertEquals(Service.STARTED, service3.getState());
            try{
                service1 = manager.getService("Service1");
            }catch(ServiceNotFoundException e){
                fail(e.getMessage());
            }
            assertNotNull(service1);
            assertEquals(Service.STARTED, service1.getState());
            ServiceManagerFactory.unloadManager(def1.getCanonicalPath());
            service3 = null;
            try{
                service3 = manager.getService("Service3");
            }catch(ServiceNotFoundException e){
            }
            assertNotNull(service3);
            assertEquals(Service.STOPPED, service3.getState());
            service1 = null;
            try{
                service1 = manager.getService("Service1");
            }catch(ServiceNotFoundException e){
            }
            assertNull(service1);
            ServiceManagerFactory.loadManager(def1.getCanonicalPath());
            assertTrue(ServiceManagerFactory.checkLoadManagerCompleted());
            service3 = null;
            try{
                service3 = manager.getService("Service3");
            }catch(ServiceNotFoundException e){
            }
            assertNotNull(service3);
            service1 = null;
            try{
                service1 = manager.getService("Service1");
            }catch(ServiceNotFoundException e){
            }
            assertNotNull(service1);
            ServiceManagerFactory.unloadManager(def4.getCanonicalPath());
            ServiceManagerFactory.unloadManager(def1.getCanonicalPath());
            service3 = null;
            try{
                service3 = manager.getService("Service3");
            }catch(ServiceNotFoundException e){
            }
            assertNull(service3);
            service1 = null;
            try{
                service1 = manager.getService("Service1");
            }catch(ServiceNotFoundException e){
            }
            assertNull(service1);
        }finally{
            ServiceManagerFactory.unloadManager(def1.getCanonicalPath());
            ServiceManagerFactory.unloadManager(def4.getCanonicalPath());
            if(def1 != null){
                def1.delete();
            }
            if(def4 != null){
                def4.delete();
            }
        }
    }
    
    /**
     * Map�C���^�t�F�[�X�����������T�[�r�X�̃e�X�g�B<p>
     * �����F
     * <ul>
     *   <li>�f�t�H���g���̃T�[�r�X��`�t�@�C��<a href="resources/nimbus-service7.xml">"nimbus-service7.xml"</a>���N���X�p�X��ɒu���B</li>
     * </ul>
     * �m�F�F
     * <ul>
     *   <li>ServiceManagerFactory#loadManager()���Ăяo���A�߂�l��true��Ԃ��B</li>
     *   <li>ServiceManagerFactory#checkLoadManagerCompleted()��true��Ԃ��B</li>
     *   <li>ServiceManagerFactory#findManager()���Ăяo����ServiceManager���擾�ł���B</li>
     *   <li>ServiceManager#getServiceObject(String)���Ăяo���ăT�[�r�X��"Service1"�̃T�[�r�X���擾�ł���B</li>
     *   <li>�擾�����T�[�r�X"Service1"��Map�ɃL���X�g�ł���B</li>
     *   <li>�T�[�r�X��`�ɒ�`���������̒l���AMap����擾�ł���B</li>
     * </ul>
     */
    public void testMapService() throws Exception {
        File def = null;
        try{
            def = TestUtility.copyOnClassPath(
                "nimbus-service7.xml",
                "nimbus-service.xml"
            );
            ServiceManagerFactory.loadManager();
            ServiceManagerFactory.checkLoadManagerCompleted();
            final ServiceManager manager
                 = ServiceManagerFactory.findManager();
            final Map map = (Map)manager.getServiceObject("Service1");
            assertEquals("Nimbus1", map.get("string1"));
            assertEquals("Nimbus2", map.get("string2"));
            assertEquals(new Integer(100), map.get("int"));
            assertEquals(
                new URL("http://nimbus.org/index.html"),
                map.get("URL")
            );
        }finally{
            ServiceManagerFactory.unloadManager();
            if(def != null){
                def.delete();
            }
        }
    }
    
    /**
     * ServiceBase���p��������Service�C���^�t�F�[�X�𒼐ڎ��������T�[�r�X�̃e�X�g�B<p>
     * �����F
     * <ul>
     *   <li>�f�t�H���g���ł͂Ȃ��T�[�r�X��`�t�@�C��<a href="resources/nimbus-service8.xml">"nimbus-service8.xml"</a>���e���|�����̈�ɒu���B</li>
     * </ul>
     * �m�F�F
     * <ul>
     *   <li>ServiceManagerFactory#loadManager(String)�̈����Ƀe���|�����ɃR�s�[�����T�[�r�X��`�t�@�C��"nimbus-service8.xml"�̃t�@�C�������w�肵�ČĂяo���A�߂�l��true��Ԃ��B</li>
     *   <li>ServiceManagerFactory#checkLoadManagerCompleted()��true��Ԃ��B</li>
     *   <li>ServiceManager#getService(String)���Ăяo���ăT�[�r�X��"Service2"�̃T�[�r�X���擾�ł���B</li>
     *   <li>ServiceManager#getServiceObject(String)���Ăяo���ăT�[�r�X��"Service2"�̃T�[�r�X�I�u�W�F�N�g���擾�ł���B</li>
     *   <li>�擾�����T�[�r�X��Service#getState()�̒l��Service#STARTED�ł���B</li>
     *   <li>�擾�����T�[�r�X�I�u�W�F�N�g����A�T�[�r�X��`�Őݒ肵�������̒l���擾�ł���B</li>
     * </ul>
     */
    public void testService() throws Exception {
        final File def = TestUtility.copyOnTemp("nimbus-service8.xml");
        try{
            ServiceManagerFactory.loadManager(def.getCanonicalPath());
            ServiceManagerFactory.checkLoadManagerCompleted();
            final ServiceManager manager
                 = ServiceManagerFactory.findManager();
            final Service service1 = manager.getService("Service1");
            final TestService serviceObj1
                 = (TestService)manager.getServiceObject("Service1");
            assertEquals(Service.STARTED, service1.getState());
            assertEquals("AAA", serviceObj1.getString());
            final String[] stringArray = serviceObj1.getStringArray();
            assertEquals("AAA", stringArray[0]);
            assertEquals("BBB", stringArray[1]);
            assertEquals("CCC", stringArray[2]);
            assertEquals(100, serviceObj1.getInt());
            assertEquals(
                new java.net.URL("http://nimbus.org/index.html"),
                serviceObj1.getURL()
            );
            final Properties prop = serviceObj1.getProperties();
            assertEquals("1", prop.getProperty("key1"));
            assertEquals("2", prop.getProperty("key2"));
            assertEquals("3", prop.getProperty("key3"));
        }finally{
            ServiceManagerFactory.unloadManager(def.getCanonicalPath());
            if(def != null){
                def.delete();
            }
        }
    }
    
    /**
     * Service�C���^�t�F�[�X���������Ă��Ȃ�POJO�T�[�r�X�̃e�X�g�B<p>
     * �����F
     * <ul>
     *   <li>�f�t�H���g���ł͂Ȃ��T�[�r�X��`�t�@�C��<a href="resources/nimbus-service9.xml">"nimbus-service9.xml"</a>���e���|�����̈�ɒu���B</li>
     * </ul>
     * �m�F�F
     * <ul>
     *   <li>ServiceManagerFactory#loadManager(String)�̈����Ƀe���|�����ɃR�s�[�����T�[�r�X��`�t�@�C��"nimbus-service9.xml"�̃t�@�C�������w�肵�ČĂяo���A�߂�l��true��Ԃ��B</li>
     *   <li>ServiceManagerFactory#checkLoadManagerCompleted()��true��Ԃ��B</li>
     *   <li>ServiceManager#getService(String)���Ăяo���ăT�[�r�X��"Service2"�̃T�[�r�X���擾�ł���B</li>
     *   <li>ServiceManager#getServiceObject(String)���Ăяo���ăT�[�r�X��"Service2"�̃T�[�r�X�I�u�W�F�N�g���擾�ł���B</li>
     *   <li>�擾�����T�[�r�X��Service#getState()�̒l��Service#STARTED�ł���B</li>
     *   <li>�擾�����T�[�r�X�I�u�W�F�N�g����A�T�[�r�X��`�Őݒ肵�������̒l���擾�ł���B</li>
     * </ul>
     */
    public void testPOJOService() throws Exception {
        final File def = TestUtility.copyOnTemp("nimbus-service9.xml");
        try{
            ServiceManagerFactory.loadManager(def.getCanonicalPath());
            ServiceManagerFactory.checkLoadManagerCompleted();
            final ServiceManager manager
                 = ServiceManagerFactory.findManager();
            final Service service1 = manager.getService("Service1");
            final TestObject serviceObj1
                 = (TestObject)manager.getServiceObject("Service1");
            assertEquals(Service.STARTED, service1.getState());
            assertEquals("AAA", serviceObj1.getString());
            final String[] stringArray = serviceObj1.getStringArray();
            assertEquals("AAA", stringArray[0]);
            assertEquals("BBB", stringArray[1]);
            assertEquals("CCC", stringArray[2]);
            assertEquals(100, serviceObj1.getInt());
            assertEquals(
                new java.net.URL("http://nimbus.org/index.html"),
                serviceObj1.getURL()
            );
            final Properties prop = serviceObj1.getProperties();
            assertEquals("1", prop.getProperty("key1"));
            assertEquals("2", prop.getProperty("key2"));
            assertEquals("3", prop.getProperty("key3"));
        }finally{
            ServiceManagerFactory.unloadManager(def.getCanonicalPath());
            if(def != null){
                def.delete();
            }
        }
    }
    
    /**
     * ServiceBaseSupport�C���^�t�F�[�X�����������T�[�r�X�̃e�X�g�B<p>
     * �����F
     * <ul>
     *   <li>�f�t�H���g���ł͂Ȃ��T�[�r�X��`�t�@�C��<a href="resources/nimbus-service10.xml">"nimbus-service10.xml"</a>���e���|�����̈�ɒu���B</li>
     * </ul>
     * �m�F�F
     * <ul>
     *   <li>ServiceManagerFactory#loadManager(String)�̈����Ƀe���|�����ɃR�s�[�����T�[�r�X��`�t�@�C��"nimbus-service10.xml"�̃t�@�C�������w�肵�ČĂяo���A�߂�l��true��Ԃ��B</li>
     *   <li>ServiceManagerFactory#checkLoadManagerCompleted()��true��Ԃ��B</li>
     *   <li>ServiceManager#getService(String)���Ăяo���ăT�[�r�X��"Service2"�̃T�[�r�X���擾�ł���B</li>
     *   <li>ServiceManager#getServiceObject(String)���Ăяo���ăT�[�r�X��"Service2"�̃T�[�r�X�I�u�W�F�N�g���擾�ł���B</li>
     *   <li>�擾�����T�[�r�X��Service#getState()�̒l��Service#STARTED�ł���B</li>
     *   <li>�擾�����T�[�r�X�I�u�W�F�N�g����A�T�[�r�X��`�Őݒ肵�������̒l���擾�ł���B</li>
     * </ul>
     */
    public void testServiceBaseSupport() throws Exception {
        final File def = TestUtility.copyOnTemp("nimbus-service10.xml");
        try{
            ServiceManagerFactory.loadManager(def.getCanonicalPath());
            ServiceManagerFactory.checkLoadManagerCompleted();
            final ServiceManager manager
                 = ServiceManagerFactory.findManager();
            final Service service1 = manager.getService("Service1");
            final TestServiceBaseSupport serviceObj1
                 = (TestServiceBaseSupport)manager.getServiceObject("Service1");
            assertEquals(Service.STARTED, service1.getState());
            assertEquals("AAA", serviceObj1.getString());
            final String[] stringArray = serviceObj1.getStringArray();
            assertEquals("AAA", stringArray[0]);
            assertEquals("BBB", stringArray[1]);
            assertEquals("CCC", stringArray[2]);
            assertEquals(100, serviceObj1.getInt());
            assertEquals(
                new java.net.URL("http://nimbus.org/index.html"),
                serviceObj1.getURL()
            );
            final Properties prop = serviceObj1.getProperties();
            assertEquals("1", prop.getProperty("key1"));
            assertEquals("2", prop.getProperty("key2"));
            assertEquals("3", prop.getProperty("key3"));
        }finally{
            ServiceManagerFactory.unloadManager(def.getCanonicalPath());
            if(def != null){
                def.delete();
            }
        }
    }
    
    /**
     * �T�[�r�X��`�t�@�C���̃����[�h�̃e�X�g�B<p>
     * �����F
     * <ul>
     *   <li>�T�[�r�X��`�t�@�C��<a href="resources/nimbus-service.xml">"nimbus-service.xml"</a>���N���X�p�X��ɒu���B</li>
     *   <li>�����[�h�O�ɁA�T�[�r�X��`�t�@�C��<a href="resources/nimbus-service1.xml">"nimbus-service.xml"</a>���N���X�p�X��ɏ㏑������B</li>
     * </ul>
     * �m�F�F
     * <ul>
     *   <li>ServiceManagerFactory#loadManager(String)�̈����ɃT�[�r�X��`�t�@�C��"nimbus-service.xml"���w�肵�ČĂяo���A�߂�l��true��Ԃ��B</li>
     *   <li>ServiceManagerFactory#checkLoadManagerCompleted()��true��Ԃ��B</li>
     *   <li>ServiceManagerFactory#findManager()���Ăяo����ServiceManager���擾�ł���B</li>
     *   <li>ServiceManager#getService(String)���Ăяo���ăT�[�r�X��"Service0"�̃T�[�r�X���擾�ł���B</li>
     *   <li>ServiceManagerFactory#loadManager(String, boolean)�̈����ɃT�[�r�X��`�t�@�C��"nimbus-service.xml"�ƁAtrue���w�肵�ČĂяo���A�߂�l��true��Ԃ��B</li>
     *   <li>ServiceManagerFactory#checkLoadManagerCompleted()��true��Ԃ��B</li>
     *   <li>ServiceManagerFactory#findManager()���Ăяo����ServiceManager���擾�ł���B</li>
     *   <li>ServiceManager#getService(String)���Ăяo���ăT�[�r�X��"Service0"�̃T�[�r�X���擾�ł��Ȃ��B</li>
     *   <li>ServiceManager#getService(String)���Ăяo���ăT�[�r�X��"Service1"�̃T�[�r�X���擾�ł���B</li>
     *   <li>ServiceManagerFactory#unloadManager(String)�̌Ăяo�����s����B</li>
     * </ul>
     */
    public void testReload() throws Exception {
        File def1 = null;
        File def2 = null;
        try{
            def1 = TestUtility.copyOnClassPath("nimbus-service.xml");
            assertTrue(
                ServiceManagerFactory.loadManager("nimbus-service.xml")
            );
            assertTrue(ServiceManagerFactory.checkLoadManagerCompleted());
            ServiceManager manager = ServiceManagerFactory.findManager();
            assertNotNull(manager);
            Service service = null;
            try{
                service = manager.getService("Service0");
            }catch(ServiceNotFoundException e){
                fail(e.getMessage());
            }
            assertNotNull(service);
            def2 = TestUtility.copyOnClassPath(
                "nimbus-service1.xml",
                "nimbus-service.xml"
            );
            assertTrue(
                ServiceManagerFactory.loadManager("nimbus-service.xml", true)
            );
            assertTrue(ServiceManagerFactory.checkLoadManagerCompleted());
            manager = ServiceManagerFactory.findManager();
            assertNotNull(manager);
            try{
                service = manager.getService("Service0");
            }catch(ServiceNotFoundException e){
                service = null;
            }
            assertNull(service);
            service = null;
            try{
                service = manager.getService("Service1");
            }catch(ServiceNotFoundException e){
                fail(e.getMessage());
            }
            assertNotNull(service);
            ServiceManagerFactory.unloadManager("nimbus-service.xml");
        }finally{
            ServiceManagerFactory.unloadManager("nimbus-service.xml");
            if(def1 != null){
                def1.delete();
            }
            if(def2 != null){
                def2.delete();
            }
        }
    }
    
    /**
     * �N���ł��Ȃ������T�[�r�X�̑��݊m�F�̃e�X�g�B<p>
     * �����F
     * <ul>
     *   <li>�T�[�r�X��`�t�@�C��<a href="resources/nimbus-service4.xml">"nimbus-service4.xml"</a>���e���|�����̈�ɒu���B</li>
     * </ul>
     * �m�F�F
     * <ul>
     *   <li>ServiceManagerFactory#loadManager(String)�̈����Ƀe���|�����ɃR�s�[�����T�[�r�X��`�t�@�C��"nimbus-service4.xml"�̃t�@�C�������w�肵�ČĂяo���A�߂�l��true��Ԃ��B</li>
     *   <li>ServiceManagerFactory#checkLoadManagerCompleted(Set)��false��Ԃ��B</li>
     *   <li>ServiceManagerFactory#checkLoadManagerCompleted(Set)�̈����ɓn����S     *   <li>ServiceManagerFactory#checkLoadManagerCompleted(Set)�̈����ɓn����Set�̃T�C�Y���P�ł���B</li>
     * </ul>
     */
    public void testCheckLoadManagerCompleted1() throws Exception {
        final File def = TestUtility.copyOnTemp("nimbus-service4.xml");
        try{
            assertTrue(
                ServiceManagerFactory.loadManager(def.getCanonicalPath())
            );
            final Set fails = new HashSet();
            assertFalse(
                ServiceManagerFactory.checkLoadManagerCompleted(fails)
            );
            assertTrue(fails.contains(new ServiceName(ServiceManager.DEFAULT_NAME, "Service3")));
            assertEquals(1, fails.size());
        }finally{
            ServiceManagerFactory.unloadManager(def.getCanonicalPath());
            if(def != null){
                def.delete();
            }
        }
    }
    
    /**
     * �N���ł��Ȃ������T�[�r�X�̑��݊m�F�̃e�X�g�B<p>
     * �����F
     * <ul>
     *   <li>�T�[�r�X��`�t�@�C��<a href="resources/nimbus-service1.xml">"nimbus-service1.xml"</a>���e���|�����̈�ɒu���B</li>
     * </ul>
     * �m�F�F
     * <ul>
     *   <li>ServiceManagerFactory#loadManager(String)�̈����Ƀe���|�����ɃR�s�[�����T�[�r�X��`�t�@�C��"nimbus-service1.xml"�̃t�@�C�������w�肵�ČĂяo���A�߂�l��true��Ԃ��B</li>
     *   <li>ServiceManagerFactory#checkLoadManagerCompleted(Set)��true��Ԃ��B</li>
     *   <li>ServiceManagerFactory#checkLoadManagerCompleted(Set)�̈����ɓn����Set�̃T�C�Y���O�ł���B</li>
     * </ul>
     */
    public void testCheckLoadManagerCompleted2() throws Exception {
        final File def = TestUtility.copyOnTemp("nimbus-service1.xml");
        try{
            assertTrue(
                ServiceManagerFactory.loadManager(def.getCanonicalPath())
            );
            final Set fails = new HashSet();
            assertTrue(
                ServiceManagerFactory.checkLoadManagerCompleted(fails)
            );
            assertEquals(0, fails.size());
        }finally{
            ServiceManagerFactory.unloadManager(def.getCanonicalPath());
            if(def != null){
                def.delete();
            }
        }
    }
    
    /**
     * ServiceManager�Q�̎擾�e�X�g�B<p>
     * �����F
     * <ul>
     *   <li>�T�[�r�X��`�t�@�C��<a href="resources/nimbus-service.xml">"nimbus-service.xml"</a>���e���|�����̈�ɒu���B</li>
     *   <li>�T�[�r�X��`�t�@�C��<a href="resources/nimbus-service12.xml">"nimbus-service12.xml"</a>���e���|�����̈�ɒu���B</li>
     * </ul>
     * �m�F�F
     * <ul>
     *   <li>ServiceManagerFactory#loadManager(String)�̈����Ƀe���|�����ɃR�s�[�����T�[�r�X��`�t�@�C��"nimbus-service.xml"�̃t�@�C�������w�肵�ČĂяo���A�߂�l��true��Ԃ��B</li>
     *   <li>ServiceManagerFactory#loadManager(String)�̈����Ƀe���|�����ɃR�s�[�����T�[�r�X��`�t�@�C��"nimbus-service12.xml"�̃t�@�C�������w�肵�ČĂяo���A�߂�l��true��Ԃ��B</li>
     *   <li>ServiceManagerFactory#findManagers()�ŁA"Nimbus"��"Nimbus2"��ServiceManager���擾�ł���B</li>
     * </ul>
     */
    public void testFindManagers1() throws Exception {
        final File def1 = TestUtility.copyOnTemp("nimbus-service.xml");
        final File def2 = TestUtility.copyOnTemp("nimbus-service12.xml");
        try{
            assertTrue(
                ServiceManagerFactory.loadManager(def1.getCanonicalPath())
            );
            assertTrue(
                ServiceManagerFactory.loadManager(def2.getCanonicalPath())
            );
            final ServiceManager[] managers
                 = ServiceManagerFactory.findManagers();
            assertNotNull(managers);
            assertEquals(2, managers.length);
            final Set names = new HashSet();
            names.add(managers[0].getServiceName());
            names.add(managers[1].getServiceName());
            assertTrue(names.contains(ServiceManager.DEFAULT_NAME));
            assertTrue(names.contains("Nimbus2"));
        }finally{
            ServiceManagerFactory.unloadManager(def1.getCanonicalPath());
            ServiceManagerFactory.unloadManager(def2.getCanonicalPath());
            if(def1 != null){
                def1.delete();
            }
            if(def2 != null){
                def2.delete();
            }
        }
    }
    
    /**
     * ServiceManager�Q�̎擾�e�X�g�B<p>
     * �����F
     * <ul>
     *   <li>�T�[�r�X��`�t�@�C��<a href="resources/nimbus-service.xml">"nimbus-service.xml"</a>���e���|�����̈�ɒu���B</li>
     *   <li>�T�[�r�X��`�t�@�C��<a href="resources/nimbus-service1.xml">"nimbus-service12.xml"</a>���e���|�����̈�ɒu���B</li>
     * </ul>
     * �m�F�F
     * <ul>
     *   <li>ServiceManagerFactory#loadManager(String)�̈����Ƀe���|�����ɃR�s�[�����T�[�r�X��`�t�@�C��"nimbus-service.xml"�̃t�@�C�������w�肵�ČĂяo���A�߂�l��true��Ԃ��B</li>
     *   <li>ServiceManagerFactory#loadManager(String)�̈����Ƀe���|�����ɃR�s�[�����T�[�r�X��`�t�@�C��"nimbus-service1.xml"�̃t�@�C�������w�肵�ČĂяo���A�߂�l��true��Ԃ��B</li>
     *   <li>ServiceManagerFactory#findManagers()�ŁA"Nimbus"��ServiceManager���擾�ł���B</li>
     * </ul>
     */
    public void testFindManagers2() throws Exception {
        final File def1 = TestUtility.copyOnTemp("nimbus-service.xml");
        final File def2 = TestUtility.copyOnTemp("nimbus-service1.xml");
        try{
            assertTrue(
                ServiceManagerFactory.loadManager(def1.getCanonicalPath())
            );
            assertTrue(
                ServiceManagerFactory.loadManager(def2.getCanonicalPath())
            );
            final ServiceManager[] managers
                 = ServiceManagerFactory.findManagers();
            assertNotNull(managers);
            assertEquals(1, managers.length);
            assertEquals(ServiceManager.DEFAULT_NAME, managers[0].getServiceName());
        }finally{
            ServiceManagerFactory.unloadManager(def1.getCanonicalPath());
            ServiceManagerFactory.unloadManager(def2.getCanonicalPath());
            if(def1 != null){
                def1.delete();
            }
            if(def2 != null){
                def2.delete();
            }
        }
    }
    
    /**
     * ServiceManager�Q�̎擾�e�X�g�B<p>
     * �����F
     * <ul>
     *   <li>�T�[�r�X��`�t�@�C����p�ӂ��Ȃ��B</li>
     * </ul>
     * �m�F�F
     * <ul>
     *   <li>ServiceManagerFactory#findManagers()�ŁA�����O��ServiceManager�z�񂪎擾�ł���B</li>
     * </ul>
     */
    public void testFindManagers3() throws Exception {
        final ServiceManager[] managers = ServiceManagerFactory.findManagers();
        assertNotNull(managers);
        assertEquals(0, managers.length);
    }
    
    /**
     * �f�t�H���gServiceManager�̎擾�e�X�g�B<p>
     * �����F
     * <ul>
     *   <li>�T�[�r�X��`�t�@�C��<a href="resources/nimbus-service.xml">"nimbus-service.xml"</a>���e���|�����̈�ɒu���B</li>
     *   <li>�T�[�r�X��`�t�@�C��<a href="resources/nimbus-service12.xml">"nimbus-service12.xml"</a>���e���|�����̈�ɒu���B</li>
     * </ul>
     * �m�F�F
     * <ul>
     *   <li>ServiceManagerFactory#loadManager(String)�̈����Ƀe���|�����ɃR�s�[�����T�[�r�X��`�t�@�C��"nimbus-service.xml"�̃t�@�C�������w�肵�ČĂяo���A�߂�l��true��Ԃ��B</li>
     *   <li>ServiceManagerFactory#loadManager(String)�̈����Ƀe���|�����ɃR�s�[�����T�[�r�X��`�t�@�C��"nimbus-service12.xml"�̃t�@�C�������w�肵�ČĂяo���A�߂�l��true��Ԃ��B</li>
     *   <li>ServiceManagerFactory#findManager()�ŁA"Nimbus"��ServiceManager���擾�ł���B</li>
     * </ul>
     */
    public void testFindManager1() throws Exception {
        final File def1 = TestUtility.copyOnTemp("nimbus-service.xml");
        final File def2 = TestUtility.copyOnTemp("nimbus-service12.xml");
        try{
            assertTrue(
                ServiceManagerFactory.loadManager(def1.getCanonicalPath())
            );
            assertTrue(
                ServiceManagerFactory.loadManager(def2.getCanonicalPath())
            );
            final ServiceManager manager = ServiceManagerFactory.findManager();
            assertNotNull(manager);
            assertEquals(ServiceManager.DEFAULT_NAME, manager.getServiceName());
        }finally{
            ServiceManagerFactory.unloadManager(def1.getCanonicalPath());
            ServiceManagerFactory.unloadManager(def2.getCanonicalPath());
            if(def1 != null){
                def1.delete();
            }
            if(def2 != null){
                def2.delete();
            }
        }
    }
    
    /**
     * �f�t�H���gServiceManager�̎擾�e�X�g�B<p>
     * �����F
     * <ul>
     *   <li>�T�[�r�X��`�t�@�C��<a href="resources/nimbus-service.xml">"nimbus-service.xml"</a>���e���|�����̈�ɒu���B</li>
     *   <li>�T�[�r�X��`�t�@�C��<a href="resources/nimbus-service1.xml">"nimbus-service12.xml"</a>���e���|�����̈�ɒu���B</li>
     * </ul>
     * �m�F�F
     * <ul>
     *   <li>ServiceManagerFactory#loadManager(String)�̈����Ƀe���|�����ɃR�s�[�����T�[�r�X��`�t�@�C��"nimbus-service.xml"�̃t�@�C�������w�肵�ČĂяo���A�߂�l��true��Ԃ��B</li>
     *   <li>ServiceManagerFactory#loadManager(String)�̈����Ƀe���|�����ɃR�s�[�����T�[�r�X��`�t�@�C��"nimbus-service1.xml"�̃t�@�C�������w�肵�ČĂяo���A�߂�l��true��Ԃ��B</li>
     *   <li>ServiceManagerFactory#findManager()�ŁA"Nimbus"��ServiceManager���擾�ł���B</li>
     * </ul>
     */
    public void testFindManager2() throws Exception {
        final File def1 = TestUtility.copyOnTemp("nimbus-service.xml");
        final File def2 = TestUtility.copyOnTemp("nimbus-service1.xml");
        try{
            assertTrue(
                ServiceManagerFactory.loadManager(def1.getCanonicalPath())
            );
            assertTrue(
                ServiceManagerFactory.loadManager(def2.getCanonicalPath())
            );
            final ServiceManager manager = ServiceManagerFactory.findManager();
            assertNotNull(manager);
            assertEquals(ServiceManager.DEFAULT_NAME, manager.getServiceName());
        }finally{
            ServiceManagerFactory.unloadManager(def1.getCanonicalPath());
            ServiceManagerFactory.unloadManager(def2.getCanonicalPath());
            if(def1 != null){
                def1.delete();
            }
            if(def2 != null){
                def2.delete();
            }
        }
    }
    
    /**
     * �f�t�H���gServiceManager�̎擾�e�X�g�B<p>
     * �����F
     * <ul>
     *   <li>�T�[�r�X��`�t�@�C����p�ӂ��Ȃ��B</li>
     * </ul>
     * �m�F�F
     * <ul>
     *   <li>ServiceManagerFactory#findManager()�ŁAnull���擾�ł���B</li>
     * </ul>
     */
    public void testFindManager3() throws Exception {
        final ServiceManager manager = ServiceManagerFactory.findManager();
        assertNull(manager);
    }
    
    /**
     * ServiceManager�̎擾�e�X�g�B<p>
     * �����F
     * <ul>
     *   <li>�T�[�r�X��`�t�@�C��<a href="resources/nimbus-service12.xml">"nimbus-service12.xml"</a>���e���|�����̈�ɒu���B</li>
     * </ul>
     * �m�F�F
     * <ul>
     *   <li>ServiceManagerFactory#loadManager(String)�̈����Ƀe���|�����ɃR�s�[�����T�[�r�X��`�t�@�C��"nimbus-service12.xml"�̃t�@�C�������w�肵�ČĂяo���A�߂�l��true��Ԃ��B</li>
     *   <li>ServiceManagerFactory#findManager(String)�ŁA������null���w�肵�ČĂяo���Anull���擾�ł���B</li>
     *   <li>ServiceManagerFactory#findManager(String)�ŁA������"Nimbus2"���w�肵�ČĂяo���AServiceManager"Nimbus2"���擾�ł���B</li>
     * </ul>
     */
    public void testFindManager4() throws Exception {
        final File def1 = TestUtility.copyOnTemp("nimbus-service12.xml");
        try{
            assertTrue(
                ServiceManagerFactory.loadManager(def1.getCanonicalPath())
            );
            ServiceManager manager = ServiceManagerFactory.findManager(null);
            assertNull(manager);
            manager = ServiceManagerFactory.findManager("Nimbus2");
            assertNotNull(manager);
            assertEquals("Nimbus2", manager.getServiceName());
        }finally{
            ServiceManagerFactory.unloadManager(def1.getCanonicalPath());
            if(def1 != null){
                def1.delete();
            }
        }
    }
    
    /**
     * ServiceManager�̓o�^�E�o�^�����e�X�g�B<p>
     * �����F
     * <ul>
     *   <li>�T�[�r�X��`�t�@�C��<a href="resources/nimbus-service1.xml">"nimbus-service1.xml"</a>���e���|�����̈�ɒu���B</li>
     * </ul>
     * �m�F�F
     * <ul>
     *   <li>ServiceManagerFactory#loadManager(String)�̈����Ƀe���|�����ɃR�s�[�����T�[�r�X��`�t�@�C��"nimbus-service1.xml"�̃t�@�C�������w�肵�ČĂяo���A�߂�l��true��Ԃ��B</li>
     *   <li>ServiceManagerFactory#findManager()�ŁAServiceManager���擾�ł���B</li>
     *   <li>�擾����ServiceManager��ServiceManagerFactory#unregisterManager(String)���Ăяo���āA�߂�ltrue��Ԃ��B</li>
     *   <li>ServiceManagerFactory#findManager()�ŁAServiceManager���擾�ł��Ȃ��B</li>
     *   <li>�擾����ServiceManager��ServiceManagerFactory#registerManager(String, ServiceManager)���Ăяo���āA�߂�ltrue��Ԃ��B</li>
     *   <li>ServiceManagerFactory#findManager()�ŁAServiceManager���擾�ł���B</li>
     *   <li>�o�^����ServiceManager�ƁA�擾����ServiceManager���������B</li>
     * </ul>
     */
    public void testManagerRegisterAndUnregister1() throws Exception {
        final File def = TestUtility.copyOnTemp("nimbus-service1.xml");
        try{
            assertTrue(
                ServiceManagerFactory.loadManager(def.getCanonicalPath())
            );
            final ServiceManager manager = ServiceManagerFactory.findManager();
            assertNotNull(manager);
            assertTrue(ServiceManagerFactory
                .unregisterManager(manager.getServiceName()));
            ServiceManager tmpManager = ServiceManagerFactory.findManager();
            assertNull(tmpManager);
            assertTrue(ServiceManagerFactory
                .registerManager(manager.getServiceName(), manager));
            tmpManager = ServiceManagerFactory.findManager();
            assertNotNull(tmpManager);
            assertEquals(manager, tmpManager);
        }finally{
            ServiceManagerFactory.unloadManager(def.getCanonicalPath());
            if(def != null){
                def.delete();
            }
        }
    }
    
    /**
     * ServiceManager�̓o�^�E�o�^�����e�X�g�B<p>
     * �����F
     * <ul>
     *   <li>�T�[�r�X��`�t�@�C��<a href="resources/nimbus-service1.xml">"nimbus-service1.xml"</a>���e���|�����̈�ɒu���B</li>
     * </ul>
     * �m�F�F
     * <ul>
     *   <li>ServiceManagerFactory#loadManager(String)�̈����Ƀe���|�����ɃR�s�[�����T�[�r�X��`�t�@�C��"nimbus-service1.xml"�̃t�@�C�������w�肵�ČĂяo���A�߂�l��true��Ԃ��B</li>
     *   <li>ServiceManagerFactory#findManager()�ŁAServiceManager���擾�ł���B</li>
     *   <li>�擾����ServiceManager��ServiceManagerFactory#registerManager(String, ServiceManager)���Ăяo���āA�߂�lfalse��Ԃ��B</li>
     *   <li>�擾����ServiceManager��ServiceManagerFactory#unregisterManager(String)���Ăяo���āA�߂�ltrue��Ԃ��B</li>
     *   <li>ServiceManagerFactory#findManager()�ŁAServiceManager���擾�ł��Ȃ��B</li>
     *   <li>�擾����ServiceManager��ServiceManagerFactory#unregisterManager(String)���Ăяo���āA�߂�lfalse��Ԃ��B</li>
     * </ul>
     */
    public void testManagerRegisterAndUnregister2() throws Exception {
        final File def = TestUtility.copyOnTemp("nimbus-service1.xml");
        try{
            assertTrue(
                ServiceManagerFactory.loadManager(def.getCanonicalPath())
            );
            final ServiceManager manager = ServiceManagerFactory.findManager();
            assertNotNull(manager);
            assertFalse(ServiceManagerFactory
                .registerManager(manager.getServiceName(), manager));
            assertTrue(ServiceManagerFactory
                .unregisterManager(manager.getServiceName()));
            ServiceManager tmpManager = ServiceManagerFactory.findManager();
            assertNull(tmpManager);
            assertTrue(ServiceManagerFactory
                .unregisterManager(manager.getServiceName()));
        }finally{
            ServiceManagerFactory.unloadManager(def.getCanonicalPath());
            if(def != null){
                def.delete();
            }
        }
    }
    
    /**
     * ServiceManager�̓o�^�m�F�e�X�g�B<p>
     * �����F
     * <ul>
     *   <li>�T�[�r�X��`�t�@�C��<a href="resources/nimbus-service1.xml">"nimbus-service1.xml"</a>���e���|�����̈�ɒu���B</li>
     * </ul>
     * �m�F�F
     * <ul>
     *   <li>ServiceManagerFactory#isRegisteredManager(String)�̈�����"Nimbus"���w�肵�ČĂяo���A�߂�l��false���Ԃ�B</li>
     *   <li>ServiceManagerFactory#loadManager(String)�̈����Ƀe���|�����ɃR�s�[�����T�[�r�X��`�t�@�C��"nimbus-service1.xml"�̃t�@�C�������w�肵�ČĂяo���A�߂�l��true��Ԃ��B</li>
     *   <li>ServiceManagerFactory#isRegisteredManager(String)�̈�����"Nimbus"���w�肵�ČĂяo���A�߂�l��true���Ԃ�B</li>
     *   <li>ServiceManagerFactory#findManager()�ŁAServiceManager"Nimbus"���擾�ł���B</li>
     *   <li>ServiceManagerFactory#isRegisteredManager(String)�̈�����"Nimbus2"���w�肵�ČĂяo���A�߂�l��false���Ԃ�B</li>
     *   <li>ServiceManagerFactory#findManager()�ŁAServiceManager"Nimbus2"���擾�ł��Ȃ��B</li>
     * </ul>
     */
    public void testIsRegisteredManager() throws Exception {
        final File def = TestUtility.copyOnTemp("nimbus-service1.xml");
        try{
            assertFalse(ServiceManagerFactory
                .isRegisteredManager(ServiceManager.DEFAULT_NAME));
            assertTrue(
                ServiceManagerFactory.loadManager(def.getCanonicalPath())
            );
            assertTrue(ServiceManagerFactory
                .isRegisteredManager(ServiceManager.DEFAULT_NAME));
            ServiceManager manager = ServiceManagerFactory.findManager();
            assertNotNull(manager);
            assertEquals(ServiceManager.DEFAULT_NAME, manager.getServiceName());
            assertFalse(ServiceManagerFactory.isRegisteredManager("Nimbus2"));
            manager = ServiceManagerFactory.findManager("Nimbus2");
            assertNull(manager);
        }finally{
            ServiceManagerFactory.unloadManager(def.getCanonicalPath());
            if(def != null){
                def.delete();
            }
        }
    }
    
    /**
     * Service�̎擾�e�X�g�B<p>
     * �����F
     * <ul>
     *   <li>�T�[�r�X��`�t�@�C��<a href="resources/nimbus-service.xml">"nimbus-service.xml"</a>���N���X�p�X��ɒu���B</li>
     * </ul>
     * �m�F�F
     * <ul>
     *   <li>ServiceManagerFactory#loadManager()���Ăяo���A�߂�l��true��Ԃ��B</li>
     *   <li>ServiceManagerFactory#getService(String, String)�̈�����"Nimbus"��"Service0"���w�肵�ČĂяo���AService"Nimbus#Service0"���擾�ł���B</li>
     *   <li>ServiceManagerFactory#getService(String, String)�̈�����"Nimbus2"��"Service0"���w�肵�ČĂяo���AServiceNotFoundException����������B</li>
     *   <li>��������ServiceNotFoundException����A�擾���悤�Ƃ����T�[�r�X�����擾�ł���B</li>
     *   <li>ServiceManagerFactory#getService(String, String)�̈�����"Nimbus"��"Service1"���w�肵�ČĂяo���AServiceNotFoundException����������B</li>
     *   <li>��������ServiceNotFoundException����A�擾���悤�Ƃ����T�[�r�X�����擾�ł���B</li>
     * </ul>
     */
    public void testGetService1() throws Exception {
        final File def = TestUtility.copyOnClassPath("nimbus-service.xml");
        try{
            assertTrue(ServiceManagerFactory.loadManager());
            Service service = null;
            try{
                service = ServiceManagerFactory.getService(
                    ServiceManager.DEFAULT_NAME,
                    "Service0"
                );
            }catch(ServiceNotFoundException e){
                fail(e.getMessage());
            }
            assertNotNull(service);
            assertEquals(
                ServiceManager.DEFAULT_NAME,
                service.getServiceManagerName()
            );
            assertEquals("Service0", service.getServiceName());
            service = null;
            try{
                service = ServiceManagerFactory.getService(
                    "Nimbus2",
                    "Service0"
                );
                fail("ServiceNotFoundException must throw.");
            }catch(ServiceNotFoundException e){
                assertEquals("Nimbus2", e.getServiceManagerName());
                assertEquals("Service0", e.getServiceName());
            }
            assertNull(service);
            service = null;
            try{
                service = ServiceManagerFactory.getService(
                    ServiceManager.DEFAULT_NAME,
                    "Service1"
                );
                fail("ServiceNotFoundException must throw.");
            }catch(ServiceNotFoundException e){
                assertEquals(
                    ServiceManager.DEFAULT_NAME,
                    e.getServiceManagerName()
                );
                assertEquals("Service1", e.getServiceName());
            }
            assertNull(service);
        }finally{
            ServiceManagerFactory.unloadManager();
            if(def != null){
                def.delete();
            }
        }
    }
    
    /**
     * Service�̎擾�e�X�g�B<p>
     * �����F
     * <ul>
     *   <li>�T�[�r�X��`�t�@�C��<a href="resources/nimbus-service.xml">"nimbus-service.xml"</a>���N���X�p�X��ɒu���B</li>
     * </ul>
     * �m�F�F
     * <ul>
     *   <li>ServiceManagerFactory#loadManager()���Ăяo���A�߂�l��true��Ԃ��B</li>
     *   <li>ServiceManagerFactory#getService(ServiceName)�̈�����ServiceName"Nimbus#Service0"���w�肵�ČĂяo���AService"Nimbus#Service0"���擾�ł���B</li>
     *   <li>ServiceManagerFactory#getService(ServiceName)�̈�����ServiceName"Nimbus2#Service0"���w�肵�ČĂяo���AServiceNotFoundException����������B</li>
     *   <li>��������ServiceNotFoundException����A�擾���悤�Ƃ����T�[�r�X�����擾�ł���B</li>
     *   <li>ServiceManagerFactory#getService(ServiceName)�̈�����ServiceName"Nimbus#Service1"���w�肵�ČĂяo���AServiceNotFoundException����������B</li>
     *   <li>��������ServiceNotFoundException����A�擾���悤�Ƃ����T�[�r�X�����擾�ł���B</li>
     * </ul>
     */
    public void testGetService2() throws Exception {
        final File def = TestUtility.copyOnClassPath("nimbus-service.xml");
        try{
            assertTrue(ServiceManagerFactory.loadManager());
            Service service = null;
            try{
                service = ServiceManagerFactory.getService(
                    new ServiceName(ServiceManager.DEFAULT_NAME, "Service0")
                );
            }catch(ServiceNotFoundException e){
                fail(e.getMessage());
            }
            assertNotNull(service);
            assertEquals(
                ServiceManager.DEFAULT_NAME,
                service.getServiceManagerName()
            );
            assertEquals("Service0", service.getServiceName());
            service = null;
            try{
                service = ServiceManagerFactory.getService(
                    new ServiceName("Nimbus2", "Service0")
                );
                fail("ServiceNotFoundException must throw.");
            }catch(ServiceNotFoundException e){
                assertEquals("Nimbus2", e.getServiceManagerName());
                assertEquals("Service0", e.getServiceName());
            }
            assertNull(service);
            service = null;
            try{
                service = ServiceManagerFactory.getService(
                    new ServiceName(ServiceManager.DEFAULT_NAME, "Service1")
                );
                fail("ServiceNotFoundException must throw.");
            }catch(ServiceNotFoundException e){
                assertEquals(
                    ServiceManager.DEFAULT_NAME,
                    e.getServiceManagerName()
                );
                assertEquals("Service1", e.getServiceName());
            }
            assertNull(service);
        }finally{
            ServiceManagerFactory.unloadManager();
            if(def != null){
                def.delete();
            }
        }
    }
    
    /**
     * Service�̎擾�e�X�g�B<p>
     * �����F
     * <ul>
     *   <li>�T�[�r�X��`�t�@�C��<a href="resources/nimbus-service.xml">"nimbus-service.xml"</a>���N���X�p�X��ɒu���B</li>
     * </ul>
     * �m�F�F
     * <ul>
     *   <li>ServiceManagerFactory#loadManager()���Ăяo���A�߂�l��true��Ԃ��B</li>
     *   <li>ServiceManagerFactory#getService(String)�̈�����"Service0"���w�肵�ČĂяo���AService"Nimbus#Service0"���擾�ł���B</li>
     *   <li>ServiceManagerFactory#getService(String)�̈�����"Service1"���w�肵�ČĂяo���AServiceNotFoundException����������B</li>
     *   <li>��������ServiceNotFoundException����A�擾���悤�Ƃ����T�[�r�X�����擾�ł���B</li>
     * </ul>
     */
    public void testGetService3() throws Exception {
        final File def = TestUtility.copyOnClassPath("nimbus-service.xml");
        try{
            assertTrue(ServiceManagerFactory.loadManager());
            Service service = null;
            try{
                service = ServiceManagerFactory.getService("Service0");
            }catch(ServiceNotFoundException e){
                fail(e.getMessage());
            }
            assertNotNull(service);
            assertEquals(
                ServiceManager.DEFAULT_NAME,
                service.getServiceManagerName()
            );
            assertEquals("Service0", service.getServiceName());
            service = null;
            try{
                service = ServiceManagerFactory.getService("Service1");
                fail("ServiceNotFoundException must throw.");
            }catch(ServiceNotFoundException e){
                assertEquals(
                    ServiceManager.DEFAULT_NAME,
                    e.getServiceManagerName()
                );
                assertEquals("Service1", e.getServiceName());
            }
            assertNull(service);
        }finally{
            ServiceManagerFactory.unloadManager();
            if(def != null){
                def.delete();
            }
        }
    }
    
    /**
     * ServiceMetaData�̎擾�e�X�g�B<p>
     * �����F
     * <ul>
     *   <li>�T�[�r�X��`�t�@�C��<a href="resources/nimbus-service.xml">"nimbus-service.xml"</a>���N���X�p�X��ɒu���B</li>
     * </ul>
     * �m�F�F
     * <ul>
     *   <li>ServiceManagerFactory#loadManager()���Ăяo���A�߂�l��true��Ԃ��B</li>
     *   <li>ServiceManagerFactory#getServiceMetaData(String, String)�̈�����"Nimbus"��"Service0"���w�肵�ČĂяo���AService"Nimbus#Service0"��ServiceMetaData���擾�ł���B</li>
     *   <li>ServiceManagerFactory#getServiceMetaData(String, String)�̈�����"Nimbus2"��"Service0"���w�肵�ČĂяo���AServiceNotFoundException����������B</li>
     *   <li>��������ServiceNotFoundException����A�擾���悤�Ƃ����T�[�r�X�����擾�ł���B</li>
     *   <li>ServiceManagerFactory#getServiceMetaData(String, String)�̈�����"Nimbus"��"Service1"���w�肵�ČĂяo���AServiceNotFoundException����������B</li>
     *   <li>��������ServiceNotFoundException����A�擾���悤�Ƃ����T�[�r�X�����擾�ł���B</li>
     * </ul>
     */
    public void testGetServiceMetaData1() throws Exception {
        final File def = TestUtility.copyOnClassPath("nimbus-service.xml");
        try{
            assertTrue(ServiceManagerFactory.loadManager());
            ServiceMetaData data = null;
            try{
                data = ServiceManagerFactory.getServiceMetaData(
                    ServiceManager.DEFAULT_NAME,
                    "Service0"
                );
            }catch(ServiceNotFoundException e){
                fail(e.getMessage());
            }
            assertNotNull(data);
            assertEquals("Service0", data.getName());
            data = null;
            try{
                data = ServiceManagerFactory.getServiceMetaData(
                    "Nimbus2",
                    "Service0"
                );
                fail("ServiceNotFoundException must throw.");
            }catch(ServiceNotFoundException e){
                assertEquals("Nimbus2", e.getServiceManagerName());
                assertEquals("Service0", e.getServiceName());
            }
            assertNull(data);
            data = null;
            try{
                data = ServiceManagerFactory.getServiceMetaData(
                    ServiceManager.DEFAULT_NAME,
                    "Service1"
                );
                fail("ServiceNotFoundException must throw.");
            }catch(ServiceNotFoundException e){
                assertEquals(
                    ServiceManager.DEFAULT_NAME,
                    e.getServiceManagerName()
                );
                assertEquals("Service1", e.getServiceName());
            }
            assertNull(data);
        }finally{
            ServiceManagerFactory.unloadManager();
            if(def != null){
                def.delete();
            }
        }
    }
    
    /**
     * ServiceMetaData�̎擾�e�X�g�B<p>
     * �����F
     * <ul>
     *   <li>�T�[�r�X��`�t�@�C��<a href="resources/nimbus-service.xml">"nimbus-service.xml"</a>���N���X�p�X��ɒu���B</li>
     * </ul>
     * �m�F�F
     * <ul>
     *   <li>ServiceManagerFactory#loadManager()���Ăяo���A�߂�l��true��Ԃ��B</li>
     *   <li>ServiceManagerFactory#getServiceMetaData(ServiceName)�̈�����ServiceName"Nimbus#Service0"���w�肵�ČĂяo���AService"Nimbus#Service0"��ServiceMetaData���擾�ł���B</li>
     *   <li>ServiceManagerFactory#getServiceMetaData(ServiceName)�̈�����ServiceName"Nimbus2#Service0"���w�肵�ČĂяo���AServiceNotFoundException����������B</li>
     *   <li>��������ServiceNotFoundException����A�擾���悤�Ƃ����T�[�r�X�����擾�ł���B</li>
     *   <li>ServiceManagerFactory#getServiceMetaData(ServiceName)�̈�����ServiceName"Nimbus#Service1"���w�肵�ČĂяo���AServiceNotFoundException����������B</li>
     *   <li>��������ServiceNotFoundException����A�擾���悤�Ƃ����T�[�r�X�����擾�ł���B</li>
     *   <li>ServiceManagerFactory#getServiceMetaData(ServiceName)�̈�����null���w�肵�ČĂяo���AServiceNotFoundException����������B</li>
     *   <li>��������ServiceNotFoundException����A�擾���悤�Ƃ����T�[�r�X�����擾�ł��Ȃ��B</li>
     * </ul>
     */
    public void testGetServiceMetaData2() throws Exception {
        final File def = TestUtility.copyOnClassPath("nimbus-service.xml");
        try{
            assertTrue(ServiceManagerFactory.loadManager());
            ServiceMetaData data = null;
            try{
                data = ServiceManagerFactory.getServiceMetaData(
                    new ServiceName(ServiceManager.DEFAULT_NAME, "Service0")
                );
            }catch(ServiceNotFoundException e){
                fail(e.getMessage());
            }
            assertNotNull(data);
            assertEquals("Service0", data.getName());
            data = null;
            try{
                data = ServiceManagerFactory.getServiceMetaData(
                    new ServiceName("Nimbus2", "Service0")
                );
                fail("ServiceNotFoundException must throw.");
            }catch(ServiceNotFoundException e){
                assertEquals("Nimbus2", e.getServiceManagerName());
                assertEquals("Service0", e.getServiceName());
            }
            assertNull(data);
            data = null;
            try{
                data = ServiceManagerFactory.getServiceMetaData(
                    new ServiceName(ServiceManager.DEFAULT_NAME, "Service1")
                );
                fail("ServiceNotFoundException must throw.");
            }catch(ServiceNotFoundException e){
                assertEquals(
                    ServiceManager.DEFAULT_NAME,
                    e.getServiceManagerName()
                );
                assertEquals("Service1", e.getServiceName());
            }
            assertNull(data);
            data = null;
            try{
                data = ServiceManagerFactory.getServiceMetaData(
                    (ServiceName)null
                );
                fail("ServiceNotFoundException must throw.");
            }catch(ServiceNotFoundException e){
                assertEquals(null, e.getServiceManagerName());
                assertEquals(null, e.getServiceName());
            }
            assertNull(data);
        }finally{
            ServiceManagerFactory.unloadManager();
            if(def != null){
                def.delete();
            }
        }
    }
    
    /**
     * ServiceMetaData�̎擾�e�X�g�B<p>
     * �����F
     * <ul>
     *   <li>�T�[�r�X��`�t�@�C��<a href="resources/nimbus-service.xml">"nimbus-service.xml"</a>���N���X�p�X��ɒu���B</li>
     * </ul>
     * �m�F�F
     * <ul>
     *   <li>ServiceManagerFactory#loadManager()���Ăяo���A�߂�l��true��Ԃ��B</li>
     *   <li>ServiceManagerFactory#getServiceMetaData(String)�̈�����"Service0"���w�肵�ČĂяo���AService"Nimbus#Service0"��ServiceMetaData���擾�ł���B</li>
     *   <li>ServiceManagerFactory#getServiceMetaData(String)�̈�����"Service1"���w�肵�ČĂяo���AServiceNotFoundException����������B</li>
     *   <li>��������ServiceNotFoundException����A�擾���悤�Ƃ����T�[�r�X�����擾�ł���B</li>
     *   <li>ServiceManagerFactory#unloadManager()���Ăяo���A����ɃA�����[�h����B</li>
     *   <li>ServiceManagerFactory#getServiceMetaData(String)�̈�����"Service0"���w�肵�ČĂяo���AServiceNotFoundException����������B</li>
     *   <li>��������ServiceNotFoundException����A�擾���悤�Ƃ����T�[�r�X�����擾�ł���B</li>
     * </ul>
     */
    public void testGetServiceMetaData3() throws Exception {
        final File def = TestUtility.copyOnClassPath("nimbus-service.xml");
        try{
            assertTrue(ServiceManagerFactory.loadManager());
            ServiceMetaData data = null;
            try{
                data = ServiceManagerFactory.getServiceMetaData("Service0");
            }catch(ServiceNotFoundException e){
                fail(e.getMessage());
            }
            assertNotNull(data);
            assertEquals("Service0", data.getName());
            data = null;
            try{
                data = ServiceManagerFactory.getServiceMetaData("Service1");
                fail("ServiceNotFoundException must throw.");
            }catch(ServiceNotFoundException e){
                assertEquals("Nimbus", e.getServiceManagerName());
                assertEquals("Service1", e.getServiceName());
            }
            assertNull(data);
            ServiceManagerFactory.unloadManager();
            data = null;
            try{
                data = ServiceManagerFactory.getServiceMetaData("Service0");
                fail("ServiceNotFoundException must throw.");
            }catch(ServiceNotFoundException e){
                assertEquals("Nimbus", e.getServiceManagerName());
                assertEquals("Service0", e.getServiceName());
            }
            assertNull(data);
        }finally{
            ServiceManagerFactory.unloadManager();
            if(def != null){
                def.delete();
            }
        }
    }
    
    /**
     * Service�I�u�W�F�N�g�̎擾�e�X�g�B<p>
     * �����F
     * <ul>
     *   <li>�T�[�r�X��`�t�@�C��<a href="resources/nimbus-service.xml">"nimbus-service.xml"</a>���N���X�p�X��ɒu���B</li>
     * </ul>
     * �m�F�F
     * <ul>
     *   <li>ServiceManagerFactory#loadManager()���Ăяo���A�߂�l��true��Ԃ��B</li>
     *   <li>ServiceManagerFactory#getServiceObject(String, String)�̈�����"Nimbus"��"Service0"���w�肵�ČĂяo���AService�I�u�W�F�N�g"Nimbus#Service0"���擾�ł���B</li>
     *   <li>ServiceManagerFactory#getServiceObject(String, String)�̈�����"Nimbus2"��"Service0"���w�肵�ČĂяo���AServiceNotFoundException����������B</li>
     *   <li>��������ServiceNotFoundException����A�擾���悤�Ƃ����T�[�r�X�����擾�ł���B</li>
     *   <li>ServiceManagerFactory#getServiceObject(String, String)�̈�����"Nimbus"��"Service1"���w�肵�ČĂяo���AServiceNotFoundException����������B</li>
     *   <li>��������ServiceNotFoundException����A�擾���悤�Ƃ����T�[�r�X�����擾�ł���B</li>
     * </ul>
     */
    public void testGetServiceObject1() throws Exception {
        final File def = TestUtility.copyOnClassPath("nimbus-service.xml");
        try{
            assertTrue(ServiceManagerFactory.loadManager());
            Object serviceObj = null;
            try{
                serviceObj = ServiceManagerFactory.getServiceObject(
                    ServiceManager.DEFAULT_NAME,
                    "Service0"
                );
            }catch(ServiceNotFoundException e){
                fail(e.getMessage());
            }
            assertNotNull(serviceObj);
            assertEquals(TestServiceBase.class, serviceObj.getClass());
            serviceObj = null;
            try{
                serviceObj = ServiceManagerFactory.getServiceObject(
                    "Nimbus2",
                    "Service0"
                );
                fail("ServiceNotFoundException must throw.");
            }catch(ServiceNotFoundException e){
                assertEquals("Nimbus2", e.getServiceManagerName());
                assertEquals("Service0", e.getServiceName());
            }
            assertNull(serviceObj);
            serviceObj = null;
            try{
                serviceObj = ServiceManagerFactory.getServiceObject(
                    ServiceManager.DEFAULT_NAME,
                    "Service1"
                );
                fail("ServiceNotFoundException must throw.");
            }catch(ServiceNotFoundException e){
                assertEquals(
                    ServiceManager.DEFAULT_NAME,
                    e.getServiceManagerName()
                );
                assertEquals("Service1", e.getServiceName());
            }
            assertNull(serviceObj);
        }finally{
            ServiceManagerFactory.unloadManager();
            if(def != null){
                def.delete();
            }
        }
    }
    
    /**
     * Service�I�u�W�F�N�g�̎擾�e�X�g�B<p>
     * �����F
     * <ul>
     *   <li>�T�[�r�X��`�t�@�C��<a href="resources/nimbus-service.xml">"nimbus-service.xml"</a>���N���X�p�X��ɒu���B</li>
     * </ul>
     * �m�F�F
     * <ul>
     *   <li>ServiceManagerFactory#loadManager()���Ăяo���A�߂�l��true��Ԃ��B</li>
     *   <li>ServiceManagerFactory#getServiceObject(ServiceName)�̈�����ServiceName"Nimbus#Service0"���w�肵�ČĂяo���AService�I�u�W�F�N�g"Nimbus#Service0"���擾�ł���B</li>
     *   <li>ServiceManagerFactory#getServiceObject(ServiceName)�̈�����ServiceName"Nimbus2#Service0"���w�肵�ČĂяo���AServiceNotFoundException����������B</li>
     *   <li>��������ServiceNotFoundException����A�擾���悤�Ƃ����T�[�r�X�����擾�ł���B</li>
     *   <li>ServiceManagerFactory#getServiceObject(ServiceName)�̈�����ServiceName"Nimbus#Service1"���w�肵�ČĂяo���AServiceNotFoundException����������B</li>
     *   <li>��������ServiceNotFoundException����A�擾���悤�Ƃ����T�[�r�X�����擾�ł���B</li>
     *   <li>ServiceManagerFactory#getServiceObject(ServiceName)�̈�����null���w�肵�ČĂяo���AServiceNotFoundException����������B</li>
     *   <li>��������ServiceNotFoundException����A�擾���悤�Ƃ����T�[�r�X�����擾�ł��Ȃ��B</li>
     * </ul>
     */
    public void testGetServiceObject2() throws Exception {
        final File def = TestUtility.copyOnClassPath("nimbus-service.xml");
        try{
            assertTrue(ServiceManagerFactory.loadManager());
            Object serviceObj = null;
            try{
                serviceObj = ServiceManagerFactory.getServiceObject(
                    new ServiceName(ServiceManager.DEFAULT_NAME, "Service0")
                );
            }catch(ServiceNotFoundException e){
                fail(e.getMessage());
            }
            assertNotNull(serviceObj);
            assertEquals(TestServiceBase.class, serviceObj.getClass());
            serviceObj = null;
            try{
                serviceObj = ServiceManagerFactory.getServiceObject(
                    new ServiceName("Nimbus2", "Service0")
                );
                fail("ServiceNotFoundException must throw.");
            }catch(ServiceNotFoundException e){
                assertEquals("Nimbus2", e.getServiceManagerName());
                assertEquals("Service0", e.getServiceName());
            }
            assertNull(serviceObj);
            serviceObj = null;
            try{
                serviceObj = ServiceManagerFactory.getServiceObject(
                    new ServiceName(ServiceManager.DEFAULT_NAME, "Service1")
                );
                fail("ServiceNotFoundException must throw.");
            }catch(ServiceNotFoundException e){
                assertEquals(
                    ServiceManager.DEFAULT_NAME,
                    e.getServiceManagerName()
                );
                assertEquals("Service1", e.getServiceName());
            }
            assertNull(serviceObj);
            serviceObj = null;
            try{
                serviceObj = ServiceManagerFactory.getServiceObject(
                    (ServiceName)null
                );
                fail("ServiceNotFoundException must throw.");
            }catch(ServiceNotFoundException e){
                assertEquals(null, e.getServiceManagerName());
                assertEquals(null, e.getServiceName());
            }
            assertNull(serviceObj);
        }finally{
            ServiceManagerFactory.unloadManager();
            if(def != null){
                def.delete();
            }
        }
    }
    
    /**
     * Service�̎擾�e�X�g�B<p>
     * �����F
     * <ul>
     *   <li>�T�[�r�X��`�t�@�C��<a href="resources/nimbus-service.xml">"nimbus-service.xml"</a>���N���X�p�X��ɒu���B</li>
     * </ul>
     * �m�F�F
     * <ul>
     *   <li>ServiceManagerFactory#loadManager()���Ăяo���A�߂�l��true��Ԃ��B</li>
     *   <li>ServiceManagerFactory#getService(String)�̈�����"Service0"���w�肵�ČĂяo���AService"Nimbus#Service0"���擾�ł���B</li>
     *   <li>ServiceManagerFactory#getService(String)�̈�����"Service1"���w�肵�ČĂяo���AServiceNotFoundException����������B</li>
     *   <li>��������ServiceNotFoundException����A�擾���悤�Ƃ����T�[�r�X�����擾�ł���B</li>
     *   <li>ServiceManagerFactory#unloadManager()���Ăяo���A����ɃA�����[�h����B</li>
     *   <li>ServiceManagerFactory#getService(String)�̈�����"Service0"���w�肵�ČĂяo���AServiceNotFoundException����������B</li>
     *   <li>��������ServiceNotFoundException����A�擾���悤�Ƃ����T�[�r�X�����擾�ł���B</li>
     * </ul>
     */
    public void testGetServiceObject3() throws Exception {
        final File def = TestUtility.copyOnClassPath("nimbus-service.xml");
        try{
            assertTrue(ServiceManagerFactory.loadManager());
            Object serviceObj = null;
            try{
                serviceObj = ServiceManagerFactory.getServiceObject("Service0");
            }catch(ServiceNotFoundException e){
                fail(e.getMessage());
            }
            assertNotNull(serviceObj);
            assertEquals(TestServiceBase.class, serviceObj.getClass());
            serviceObj = null;
            try{
                serviceObj = ServiceManagerFactory.getServiceObject("Service1");
                fail("ServiceNotFoundException must throw.");
            }catch(ServiceNotFoundException e){
                assertEquals(
                    ServiceManager.DEFAULT_NAME,
                    e.getServiceManagerName()
                );
                assertEquals("Service1", e.getServiceName());
            }
            assertNull(serviceObj);
            ServiceManagerFactory.unloadManager();
            serviceObj = null;
            try{
                serviceObj = ServiceManagerFactory.getServiceObject("Service0");
                fail("ServiceNotFoundException must throw.");
            }catch(ServiceNotFoundException e){
                assertEquals(
                    ServiceManager.DEFAULT_NAME,
                    e.getServiceManagerName()
                );
                assertEquals("Service0", e.getServiceName());
            }
            assertNull(serviceObj);
        }finally{
            ServiceManagerFactory.unloadManager();
            if(def != null){
                def.delete();
            }
        }
    }
    
    /**
     * ServiceStateBroadcaster�̎擾�e�X�g�B<p>
     * �����F
     * <ul>
     *   <li>�T�[�r�X��`�t�@�C��<a href="resources/nimbus-service.xml">"nimbus-service.xml"</a>���N���X�p�X��ɒu���B</li>
     * </ul>
     * �m�F�F
     * <ul>
     *   <li>ServiceManagerFactory#loadManager()���Ăяo���A�߂�l��true��Ԃ��B</li>
     *   <li>ServiceManagerFactory#getServiceStateBroadcaster(String, String)�̈�����"Nimbus"��"Service0"���w�肵�ČĂяo���A"Service0"��ServiceStateBroadcaster���擾�ł���B</li>
     *   <li>�擾����ServiceStateBroadcaster��ServiceStateBroadcaster#addServiceStateListener(ServiceStateListener)���Ăяo���āA�Ǝ�ServiceStateListener��o�^����B</li>
     *   <li>ServiceManagerFactory#getServiceStateBroadcaster(String, String)�̈�����"Nimbus2"��"Service0"���w�肵�ČĂяo���AServiceNotFoundException����������B</li>
     *   <li>��������ServiceNotFoundException����A�擾���悤�Ƃ����T�[�r�X�����擾�ł���B</li>
     *   <li>ServiceManagerFactory#getServiceStateBroadcaster(String, String)�̈�����"Nimbus"��"Service1"���w�肵�ČĂяo���AServiceNotFoundException����������B</li>
     *   <li>��������ServiceNotFoundException����A�擾���悤�Ƃ����T�[�r�X�����擾�ł���B</li>
     *   <li>ServiceManagerFactory#unloadManager()���Ăяo���A����ɃA�����[�h����B</li>
     *   <li>�Ǝ�ServiceStateListener��ServiceStateListener#stateChanged(ServiceStateChangeEvent)���Ăяo����A�T�[�r�X"Service0"����~���ꂽ�̂����m����B</li>
     * </ul>
     */
    public void testGetServiceStateBroadcaster1() throws Exception {
        final File def = TestUtility.copyOnClassPath("nimbus-service.xml");
        class MyServiceStateListener implements ServiceStateListener{
            public boolean isStateChanged = false;
            public void stateChanged(ServiceStateChangeEvent e)
             throws Exception{
                final Service service = ServiceManagerFactory
                    .getService("Service0");
                assertEquals(service, e.getService());
                assertEquals(Service.STOPPED, e.getService().getState());
                isStateChanged = true;
            }
            public boolean isEnabledState(int state){
                return state == Service.STOPPED;
            }
        }
        MyServiceStateListener listener = null;
        try{
            assertTrue(ServiceManagerFactory.loadManager());
            ServiceStateBroadcaster ssb = null;
            try{
                ssb = ServiceManagerFactory.getServiceStateBroadcaster(
                    ServiceManager.DEFAULT_NAME,
                    "Service0"
                );
            }catch(ServiceNotFoundException e){
                fail(e.getMessage());
            }
            assertNotNull(ssb);
            listener = new MyServiceStateListener();
            ssb.addServiceStateListener(listener);
            ssb = null;
            try{
                ssb = ServiceManagerFactory.getServiceStateBroadcaster(
                    "Nimbus2",
                    "Service0"
                );
                fail("ServiceNotFoundException must throw.");
            }catch(ServiceNotFoundException e){
                assertEquals("Nimbus2", e.getServiceManagerName());
                assertEquals("Service0", e.getServiceName());
            }
            assertNull(ssb);
            ssb = null;
            try{
                ssb = ServiceManagerFactory.getServiceStateBroadcaster(
                    ServiceManager.DEFAULT_NAME,
                    "Service1"
                );
                fail("ServiceNotFoundException must throw.");
            }catch(ServiceNotFoundException e){
                assertEquals(
                    ServiceManager.DEFAULT_NAME,
                    e.getServiceManagerName()
                );
                assertEquals("Service1", e.getServiceName());
            }
            assertNull(ssb);
            ServiceManagerFactory.unloadManager(def.getCanonicalPath());
            if(listener != null){
                assertTrue(listener.isStateChanged);
            }
        }finally{
            ServiceManagerFactory.unloadManager(def.getCanonicalPath());
            if(def != null){
                def.delete();
            }
        }
    }
    
    /**
     * ServiceStateBroadcaster�̎擾�e�X�g�B<p>
     * �����F
     * <ul>
     *   <li>�T�[�r�X��`�t�@�C��<a href="resources/nimbus-service.xml">"nimbus-service.xml"</a>���N���X�p�X��ɒu���B</li>
     * </ul>
     * �m�F�F
     * <ul>
     *   <li>ServiceManagerFactory#loadManager()���Ăяo���A�߂�l��true��Ԃ��B</li>
     *   <li>ServiceManagerFactory#getServiceStateBroadcaster(ServiceName)�̈�����"Nimbus#Service0"���w�肵�ČĂяo���A"Service0"��ServiceStateBroadcaster���擾�ł���B</li>
     *   <li>�擾����ServiceStateBroadcaster��ServiceStateBroadcaster#addServiceStateListener(ServiceStateListener)���Ăяo���āA�Ǝ�ServiceStateListener��o�^����B</li>
     *   <li>ServiceManagerFactory#getServiceStateBroadcaster(ServiceName)�̈�����"Nimbus2#Service0"���w�肵�ČĂяo���AServiceNotFoundException����������B</li>
     *   <li>��������ServiceNotFoundException����A�擾���悤�Ƃ����T�[�r�X�����擾�ł���B</li>
     *   <li>ServiceManagerFactory#getServiceStateBroadcaster(ServiceName)�̈�����"Nimbus#Service1"���w�肵�ČĂяo���AServiceNotFoundException����������B</li>
     *   <li>��������ServiceNotFoundException����A�擾���悤�Ƃ����T�[�r�X�����擾�ł���B</li>
     *   <li>ServiceManagerFactory#getServiceStateBroadcaster(ServiceName)�̈�����null���w�肵�ČĂяo���AServiceNotFoundException����������B</li>
     *   <li>��������ServiceNotFoundException����A�擾���悤�Ƃ����T�[�r�X�����擾�ł��Ȃ��B</li>
     *   <li>ServiceManagerFactory#unloadManager()���Ăяo���A����ɃA�����[�h����B</li>
     *   <li>�Ǝ�ServiceStateListener��ServiceStateListener#stateChanged(ServiceStateChangeEvent)���Ăяo����A�T�[�r�X"Service0"����~���ꂽ�̂����m����B</li>
     * </ul>
     */
    public void testGetServiceStateBroadcaster2() throws Exception {
        final File def = TestUtility.copyOnClassPath("nimbus-service.xml");
        class MyServiceStateListener implements ServiceStateListener{
            public boolean isStateChanged = false;
            public void stateChanged(ServiceStateChangeEvent e)
             throws Exception{
                final Service service = ServiceManagerFactory
                    .getService("Service0");
                assertEquals(service, e.getService());
                assertEquals(Service.STOPPED, e.getService().getState());
                isStateChanged = true;
            }
            public boolean isEnabledState(int state){
                return state == Service.STOPPED;
            }
        }
        MyServiceStateListener listener = null;
        try{
            assertTrue(ServiceManagerFactory.loadManager());
            ServiceStateBroadcaster ssb = null;
            try{
                ssb = ServiceManagerFactory.getServiceStateBroadcaster(
                    new ServiceName(
                        ServiceManager.DEFAULT_NAME,
                        "Service0"
                    )
                );
            }catch(ServiceNotFoundException e){
                fail(e.getMessage());
            }
            assertNotNull(ssb);
            listener = new MyServiceStateListener();
            ssb.addServiceStateListener(listener);
            ssb = null;
            try{
                ssb = ServiceManagerFactory.getServiceStateBroadcaster(
                    new ServiceName("Nimbus2", "Service0")
                );
                fail("ServiceNotFoundException must throw.");
            }catch(ServiceNotFoundException e){
                assertEquals("Nimbus2", e.getServiceManagerName());
                assertEquals("Service0", e.getServiceName());
            }
            assertNull(ssb);
            ssb = null;
            try{
                ssb = ServiceManagerFactory.getServiceStateBroadcaster(
                    new ServiceName(
                        ServiceManager.DEFAULT_NAME,
                        "Service1"
                    )
                );
                fail("ServiceNotFoundException must throw.");
            }catch(ServiceNotFoundException e){
                assertEquals(
                    ServiceManager.DEFAULT_NAME,
                    e.getServiceManagerName()
                );
                assertEquals("Service1", e.getServiceName());
            }
            assertNull(ssb);
            ssb = null;
            try{
                ssb = ServiceManagerFactory.getServiceStateBroadcaster(
                    (ServiceName)null
                );
                fail("ServiceNotFoundException must throw.");
            }catch(ServiceNotFoundException e){
                assertEquals(null, e.getServiceManagerName());
                assertEquals(null, e.getServiceName());
            }
            assertNull(ssb);
            ServiceManagerFactory.unloadManager();
            if(listener != null){
                assertTrue(listener.isStateChanged);
            }
        }finally{
            ServiceManagerFactory.unloadManager();
            if(def != null){
                def.delete();
            }
        }
    }
    
    /**
     * ServiceStateBroadcaster�̎擾�e�X�g�B<p>
     * �����F
     * <ul>
     *   <li>�T�[�r�X��`�t�@�C��<a href="resources/nimbus-service.xml">"nimbus-service.xml"</a>���N���X�p�X��ɒu���B</li>
     * </ul>
     * �m�F�F
     * <ul>
     *   <li>ServiceManagerFactory#loadManager()���Ăяo���A�߂�l��true��Ԃ��B</li>
     *   <li>ServiceManagerFactory#getServiceStateBroadcaster(String)�̈�����"Service0"���w�肵�ČĂяo���A"Service0"��ServiceStateBroadcaster���擾�ł���B</li>
     *   <li>�擾����ServiceStateBroadcaster��ServiceStateBroadcaster#addServiceStateListener(ServiceStateListener)���Ăяo���āA�Ǝ�ServiceStateListener��o�^����B</li>
     *   <li>ServiceManagerFactory#getServiceStateBroadcaster(String)�̈�����"Service1"���w�肵�ČĂяo���AServiceNotFoundException����������B</li>
     *   <li>��������ServiceNotFoundException����A�擾���悤�Ƃ����T�[�r�X�����擾�ł���B</li>
     *   <li>ServiceManagerFactory#unloadManager()���Ăяo���A����ɃA�����[�h����B</li>
     *   <li>�Ǝ�ServiceStateListener��ServiceStateListener#stateChanged(ServiceStateChangeEvent)���Ăяo����A�T�[�r�X"Service0"����~���ꂽ�̂����m����B</li>
     *   <li>ServiceManagerFactory#getServiceStateBroadcaster(String)�̈�����"Service0"���w�肵�ČĂяo���AServiceNotFoundException����������B</li>
     *   <li>��������ServiceNotFoundException����A�擾���悤�Ƃ����T�[�r�X�����擾�ł���B</li>
     * </ul>
     */
    public void testGetServiceStateBroadcaster3() throws Exception {
        final File def = TestUtility.copyOnClassPath("nimbus-service.xml");
        class MyServiceStateListener implements ServiceStateListener{
            public boolean isStateChanged = false;
            public void stateChanged(ServiceStateChangeEvent e)
             throws Exception{
                final Service service = ServiceManagerFactory
                    .getService("Service0");
                assertEquals(service, e.getService());
                assertEquals(Service.STOPPED, e.getService().getState());
                isStateChanged = true;
            }
            public boolean isEnabledState(int state){
                return state == Service.STOPPED;
            }
        }
        MyServiceStateListener listener = null;
        try{
            assertTrue(ServiceManagerFactory.loadManager());
            ServiceStateBroadcaster ssb = null;
            try{
                ssb = ServiceManagerFactory.getServiceStateBroadcaster(
                    "Service0"
                );
            }catch(ServiceNotFoundException e){
                fail(e.getMessage());
            }
            assertNotNull(ssb);
            listener = new MyServiceStateListener();
            ssb.addServiceStateListener(listener);
            ssb = null;
            try{
                ssb = ServiceManagerFactory.getServiceStateBroadcaster(
                    "Service1"
                );
                fail("ServiceNotFoundException must throw.");
            }catch(ServiceNotFoundException e){
                assertEquals(
                    ServiceManager.DEFAULT_NAME,
                    e.getServiceManagerName()
                );
                assertEquals("Service1", e.getServiceName());
            }
            assertNull(ssb);
            ServiceManagerFactory.unloadManager(def.getCanonicalPath());
            if(listener != null){
                assertTrue(listener.isStateChanged);
            }
            ssb = null;
            try{
                ssb = ServiceManagerFactory.getServiceStateBroadcaster(
                    "Service0"
                );
                fail("ServiceNotFoundException must throw.");
            }catch(ServiceNotFoundException e){
                assertEquals(
                    ServiceManager.DEFAULT_NAME,
                    e.getServiceManagerName()
                );
                assertEquals("Service0", e.getServiceName());
            }
            assertNull(ssb);
        }finally{
            ServiceManagerFactory.unloadManager(def.getCanonicalPath());
            if(def != null){
                def.delete();
            }
        }
    }
    
    /**
     * Service�̓o�^�E�o�^�����e�X�g�B<p>
     * �����F
     * <ul>
     *   <li>�T�[�r�X��`�t�@�C��<a href="resources/nimbus-service.xml">"nimbus-service.xml"</a>���N���X�p�X��ɒu���B</li>
     * </ul>
     * �m�F�F
     * <ul>
     *   <li>ServiceManagerFactory#loadManager()���Ăяo���A�߂�l��true��Ԃ��B</li>
     *   <li>ServiceManagerFactory#getService(String)�ŁAService"Service0"���擾�ł���B</li>
     *   <li>�擾����Service��ServiceManagerFactory#unregisterService(String, String)���A������"Nimbus"��"Service0"���w�肵�ČĂяo���āA�߂�ltrue��Ԃ��B</li>
     *   <li>ServiceManagerFactory#getService(String)�ŁAService"Service0"���擾�ł��Ȃ��B</li>
     *   <li>ServiceManagerFactory#registerService(String, String, Service)�̈�����"Nimbus"�A"Service0"�A�擾����Service���w�肵�ČĂяo���āA�߂�ltrue��Ԃ��B</li>
     *   <li>ServiceManagerFactory#getService(String)�ŁAService"Service0"���擾�ł���B</li>
     *   <li>�o�^����Service�ƁA�擾����Service���������B</li>
     * </ul>
     */
    public void testServiceRegisterAndUnregister1() throws Exception {
        final File def = TestUtility.copyOnClassPath("nimbus-service.xml");
        try{
            assertTrue(ServiceManagerFactory.loadManager());
            Service service = null;
            try{
                service = ServiceManagerFactory.getService("Service0");
            }catch(ServiceNotFoundException e){
                fail(e.getMessage());
            }
            assertNotNull(service);
            assertTrue(
                ServiceManagerFactory.unregisterService(
                    service.getServiceManagerName(),
                    service.getServiceName()
                )
            );
            Service tmpService = null;
            try{
                tmpService = ServiceManagerFactory.getService("Service0");
            }catch(ServiceNotFoundException e){
            }
            assertNull(tmpService);
            assertTrue(
                ServiceManagerFactory.registerService(
                    service.getServiceManagerName(),
                    service.getServiceName(),
                    service
                )
            );
            try{
                tmpService = ServiceManagerFactory.getService("Service0");
            }catch(ServiceNotFoundException e){
                fail(e.getMessage());
            }
            assertNotNull(tmpService);
            assertEquals(service, tmpService);
        }finally{
            ServiceManagerFactory.unloadManager();
            if(def != null){
                def.delete();
            }
        }
    }
    
    /**
     * Service�̓o�^�E�o�^�����e�X�g�B<p>
     * �����F
     * <ul>
     *   <li>�T�[�r�X��`�t�@�C��<a href="resources/nimbus-service1.xml">"nimbus-service1.xml"</a>���N���X�p�X��ɒu���B</li>
     * </ul>
     * �m�F�F
     * <ul>
     *   <li>ServiceManagerFactory#loadManager()���Ăяo���A�߂�l��true��Ԃ��B</li>
     *   <li>ServiceManagerFactory#getService(String)�Ɉ���"Service0"���w�肵�ČĂяo���AService"Service0"���擾�ł���B</li>
     *   <li>�擾����Service��ServiceManagerFactory#registerService(String, String, Service)���Ăяo���āA�߂�lfalse��Ԃ��B</li>
     *   <li>�擾����Service��ServiceManagerFactory#unregisterService(String, String)���A������"Nimbus"��"Service0"���w�肵�ČĂяo���āA�߂�ltrue��Ԃ��B</li>
     *   <li>ServiceManagerFactory#getService(String)�Ɉ���"Service0"���w�肵�ČĂяo���AService"Service0"���擾�ł��Ȃ��B</li>
     *   <li>�擾����Service��ServiceManagerFactory#unregisterService(String, String)���A������"Nimbus"��"Service0"���w�肵�ČĂяo���āA�߂�ltrue��Ԃ��B</li>
     * </ul>
     */
    public void testServiceRegisterAndUnregister2() throws Exception {
        final File def = TestUtility.copyOnClassPath("nimbus-service.xml");
        try{
            assertTrue(ServiceManagerFactory.loadManager());
            Service service = null;
            try{
                service = ServiceManagerFactory.getService("Service0");
            }catch(ServiceNotFoundException e){
                fail(e.getMessage());
            }
            assertNotNull(service);
            assertFalse(
                ServiceManagerFactory.registerService(
                    service.getServiceManagerName(),
                    service.getServiceName(),
                    service
                )
            );
            assertTrue(
                ServiceManagerFactory.unregisterService(
                    service.getServiceManagerName(),
                    service.getServiceName()
                )
            );
            Service tmpService = null;
            try{
                tmpService = ServiceManagerFactory.getService("Service0");
            }catch(ServiceNotFoundException e){
            }
            assertNull(tmpService);
            assertTrue(
                ServiceManagerFactory.unregisterService(
                    service.getServiceManagerName(),
                    service.getServiceName()
                )
            );
        }finally{
            ServiceManagerFactory.unloadManager();
            if(def != null){
                def.delete();
            }
        }
    }
    
    /**
     * Service�I�u�W�F�N�g�̓o�^�E�o�^�����e�X�g�B<p>
     * �����F
     * <ul>
     *   <li>�T�[�r�X��`�t�@�C��<a href="resources/nimbus-service.xml">"nimbus-service.xml"</a>���N���X�p�X��ɒu���B</li>
     * </ul>
     * �m�F�F
     * <ul>
     *   <li>ServiceManagerFactory#loadManager()���Ăяo���A�߂�l��true��Ԃ��B</li>
     *   <li>ServiceManagerFactory#getServiceObject(String)�ŁAService�I�u�W�F�N�g"Service0"���擾�ł���B</li>
     *   <li>�擾����Service�I�u�W�F�N�g��ServiceManagerFactory#unregisterService(String, String)���A������"Nimbus"��"Service0"���w�肵�ČĂяo���āA�߂�ltrue��Ԃ��B</li>
     *   <li>ServiceManagerFactory#getServiceObject(String)�ŁAService�I�u�W�F�N�g"Service0"���擾�ł��Ȃ��B</li>
     *   <li>ServiceManagerFactory#registerService(String, String, Service)�̈�����"Nimbus"�A"Service0"�A�擾����Service�I�u�W�F�N�g���w�肵�ČĂяo���āA�߂�ltrue��Ԃ��B</li>
     *   <li>ServiceManagerFactory#getServiceObject(String)�ŁAService�I�u�W�F�N�g"Service0"���擾�ł���B</li>
     *   <li>�o�^����Service�I�u�W�F�N�g�ƁA�擾����Service�I�u�W�F�N�g���������B</li>
     * </ul>
     */
    public void testServiceRegisterAndUnregister3() throws Exception {
        final File def = TestUtility.copyOnClassPath("nimbus-service.xml");
        try{
            assertTrue(ServiceManagerFactory.loadManager());
            Object serviceObj = null;
            try{
                serviceObj = ServiceManagerFactory.getServiceObject("Service0");
            }catch(ServiceNotFoundException e){
                fail(e.getMessage());
            }
            assertNotNull(serviceObj);
            assertTrue(
                ServiceManagerFactory.unregisterService(
                    ServiceManager.DEFAULT_NAME,
                    "Service0"
                )
            );
            Object tmpObj = null;
            try{
                tmpObj = ServiceManagerFactory.getServiceObject("Service0");
            }catch(ServiceNotFoundException e){
            }
            assertNull(tmpObj);
            assertTrue(
                ServiceManagerFactory.registerService(
                    ServiceManager.DEFAULT_NAME,
                    "Service0",
                    serviceObj
                )
            );
            try{
                tmpObj = ServiceManagerFactory.getServiceObject("Service0");
            }catch(ServiceNotFoundException e){
                fail(e.getMessage());
            }
            assertNotNull(tmpObj);
            assertEquals(serviceObj, tmpObj);
        }finally{
            ServiceManagerFactory.unloadManager();
            if(def != null){
                def.delete();
            }
        }
    }
    
    /**
     * Service�C���^�t�F�[�X���������Ă��Ȃ�Service�I�u�W�F�N�g�̓o�^�E�o�^�����e�X�g�B<p>
     * �����F
     * <ul>
     *   <li>�T�[�r�X��`�t�@�C��<a href="resources/nimbus-service9.xml">"nimbus-service.xml"</a>���N���X�p�X��ɒu���B</li>
     * </ul>
     * �m�F�F
     * <ul>
     *   <li>ServiceManagerFactory#loadManager()���Ăяo���A�߂�l��true��Ԃ��B</li>
     *   <li>ServiceManagerFactory#getServiceObject(String)�ŁAService�I�u�W�F�N�g"Service1"���擾�ł���B</li>
     *   <li>�擾����Service�I�u�W�F�N�g��ServiceManagerFactory#unregisterService(String, String)���A������"Nimbus"��"Service1"���w�肵�ČĂяo���āA�߂�ltrue��Ԃ��B</li>
     *   <li>ServiceManagerFactory#getServiceObject(String)�ŁAService�I�u�W�F�N�g"Service0"���擾�ł��Ȃ��B</li>
     *   <li>ServiceManagerFactory#registerService(String, String, Service)�̈�����"Nimbus"�A"Service1"�A�擾����Service�I�u�W�F�N�g���w�肵�ČĂяo���āA�߂�ltrue��Ԃ��B</li>
     *   <li>ServiceManagerFactory#getServiceObject(String)�ŁAService�I�u�W�F�N�g"Service1"���擾�ł���B</li>
     *   <li>�o�^����Service�I�u�W�F�N�g�ƁA�擾����Service�I�u�W�F�N�g���������B</li>
     * </ul>
     */
    public void testServiceRegisterAndUnregister4() throws Exception {
        final File def = TestUtility.copyOnClassPath(
            "nimbus-service9.xml",
            "nimbus-service.xml"
        );
        try{
            assertTrue(ServiceManagerFactory.loadManager());
            Object serviceObj = null;
            try{
                serviceObj = ServiceManagerFactory.getServiceObject("Service1");
            }catch(ServiceNotFoundException e){
                fail(e.getMessage());
            }
            assertNotNull(serviceObj);
            assertTrue(
                ServiceManagerFactory.unregisterService(
                    ServiceManager.DEFAULT_NAME,
                    "Service1"
                )
            );
            Object tmpObj = null;
            try{
                tmpObj = ServiceManagerFactory.getServiceObject("Service1");
            }catch(ServiceNotFoundException e){
            }
            assertNull(tmpObj);
            assertTrue(
                ServiceManagerFactory.registerService(
                    ServiceManager.DEFAULT_NAME,
                    "Service1",
                    serviceObj
                )
            );
            try{
                tmpObj = ServiceManagerFactory.getServiceObject("Service1");
            }catch(ServiceNotFoundException e){
                fail(e.getMessage());
            }
            assertNotNull(tmpObj);
            assertEquals(serviceObj, tmpObj);
        }finally{
            ServiceManagerFactory.unloadManager();
            if(def != null){
                def.delete();
            }
        }
    }
    
    /**
     * Service�̓o�^�m�F�e�X�g�B<p>
     * �����F
     * <ul>
     *   <li>�T�[�r�X��`�t�@�C��<a href="resources/nimbus-service.xml">"nimbus-service.xml"</a>���N���X�p�X��ɒu���B</li>
     * </ul>
     * �m�F�F
     * <ul>
     *   <li>ServiceManagerFactory#isRegisteredService(String, String)�̈�����"Nimbus"��"Service0"���w�肵�ČĂяo���A�߂�l��false���Ԃ�B</li>
     *   <li>ServiceManagerFactory#loadManager()���Ăяo���A�߂�l��true��Ԃ��B</li>
     *   <li>ServiceManagerFactory#isRegisteredService(String, String)�̈�����"Nimbus"��"Service0"���w�肵�ČĂяo���A�߂�l��true���Ԃ�B</li>
     *   <li>ServiceManagerFactory#getService(String)�̈�����"Service0"���w�肵�ČĂяo���AService"Service0"���擾�ł���B</li>
     *   <li>ServiceManagerFactory#isRegisteredService(String, String)�̈�����"Nimbus2"��"Service0"���w�肵�ČĂяo���A�߂�l��false���Ԃ�B</li>
     *   <li>ServiceManagerFactory#getService(String, String)�̈�����"Nimbus2"��"Service0"���w�肵�ČĂяo���AService"Service0"���擾�ł��Ȃ��B</li>
     *   <li>ServiceManagerFactory#isRegisteredService(String, String)�̈�����"Nimbus"��"Service1"���w�肵�ČĂяo���A�߂�l��false���Ԃ�B</li>
     *   <li>ServiceManagerFactory#getService(String)�̈�����"Service1"���w�肵�ČĂяo���AService"Service1"���擾�ł��Ȃ��B</li>
     *   <li>ServiceManagerFactory#unloadManager()���Ăяo���A����ɃA�����[�h����B</li>
     *   <li>ServiceManagerFactory#isRegisteredService(String, String)�̈�����"Nimbus"��"Service0"���w�肵�ČĂяo���A�߂�l��false���Ԃ�B</li>
     *   <li>ServiceManagerFactory#getService(String)�̈�����"Service0"���w�肵�ČĂяo���AService"Service0"���擾�ł��Ȃ��B</li>
     * </ul>
     */
    public void testIsRegisteredService1() throws Exception {
        final File def = TestUtility.copyOnClassPath("nimbus-service.xml");
        try{
            assertFalse(
                ServiceManagerFactory.isRegisteredService(
                    ServiceManager.DEFAULT_NAME,
                    "Service0"
                )
            );
            assertTrue(
                ServiceManagerFactory.loadManager()
            );
            assertTrue(
                ServiceManagerFactory.isRegisteredService(
                    ServiceManager.DEFAULT_NAME,
                    "Service0"
                )
            );
            Service service = null;
            try{
                service = ServiceManagerFactory.getService("Service0");
            }catch(ServiceNotFoundException e){
                fail(e.getMessage());
            }
            assertNotNull(service);
            assertEquals(
                ServiceManager.DEFAULT_NAME,
                service.getServiceManagerName()
            );
            assertEquals("Service0", service.getServiceName());
            assertFalse(
                ServiceManagerFactory.isRegisteredService(
                    "Nimbus2",
                    "Service0"
                )
            );
            service = null;
            try{
                service = ServiceManagerFactory.getService(
                    "Nimbus2",
                    "Service0"
                );
            }catch(ServiceNotFoundException e){
            }
            assertNull(service);
            assertFalse(
                ServiceManagerFactory.isRegisteredService(
                    "Nimbus",
                    "Service1"
                )
            );
            service = null;
            try{
                service = ServiceManagerFactory.getService("Service1");
            }catch(ServiceNotFoundException e){
            }
            assertNull(service);
            ServiceManagerFactory.unloadManager();
            assertFalse(
                ServiceManagerFactory.isRegisteredService(
                    ServiceManager.DEFAULT_NAME,
                    "Service0"
                )
            );
            service = null;
            try{
                service = ServiceManagerFactory.getService(
                    ServiceManager.DEFAULT_NAME,
                    "Service0"
                );
            }catch(ServiceNotFoundException e){
            }
            assertNull(service);
        }finally{
            ServiceManagerFactory.unloadManager();
            if(def != null){
                def.delete();
            }
        }
    }
    
    /**
     * Service�̓o�^�m�F�e�X�g�B<p>
     * �����F
     * <ul>
     *   <li>�T�[�r�X��`�t�@�C��<a href="resources/nimbus-service.xml">"nimbus-service.xml"</a>���N���X�p�X��ɒu���B</li>
     * </ul>
     * �m�F�F
     * <ul>
     *   <li>ServiceManagerFactory#isRegisteredService(ServiceName)�̈�����"Nimbus#Service0"���w�肵�ČĂяo���A�߂�l��false���Ԃ�B</li>
     *   <li>ServiceManagerFactory#loadManager()���Ăяo���A�߂�l��true��Ԃ��B</li>
     *   <li>ServiceManagerFactory#isRegisteredService(ServiceName)�̈�����"Nimbus#Service0"���w�肵�ČĂяo���A�߂�l��true���Ԃ�B</li>
     *   <li>ServiceManagerFactory#getService(String)�̈�����"Service0"���w�肵�ČĂяo���AService"Service0"���擾�ł���B</li>
     *   <li>ServiceManagerFactory#isRegisteredService(ServiceName)�̈�����"Nimbus2#Service0"���w�肵�ČĂяo���A�߂�l��false���Ԃ�B</li>
     *   <li>ServiceManagerFactory#getService(String, String)�̈�����"Nimbus2"��"Service0"���w�肵�ČĂяo���AService"Service0"���擾�ł��Ȃ��B</li>
     *   <li>ServiceManagerFactory#isRegisteredService(ServiceName)�̈�����"Nimbus#Service1"���w�肵�ČĂяo���A�߂�l��false���Ԃ�B</li>
     *   <li>ServiceManagerFactory#getService(String)�̈�����"Service1"���w�肵�ČĂяo���AService"Service1"���擾�ł��Ȃ��B</li>
     *   <li>ServiceManagerFactory#isRegisteredService(ServiceName)�̈�����null���w�肵�ČĂяo���A�߂�l��false���Ԃ�B</li>
     *   <li>ServiceManagerFactory#unloadManager()���Ăяo���A����ɃA�����[�h����B</li>
     *   <li>ServiceManagerFactory#isRegisteredService(ServiceName)�̈�����"Nimbus#Service0"���w�肵�ČĂяo���A�߂�l��false���Ԃ�B</li>
     *   <li>ServiceManagerFactory#getService(String)�̈�����"Service0"���w�肵�ČĂяo���AService"Service0"���擾�ł��Ȃ��B</li>
     * </ul>
     */
    public void testIsRegisteredService2() throws Exception {
        final File def = TestUtility.copyOnClassPath("nimbus-service.xml");
        try{
            assertFalse(
                ServiceManagerFactory.isRegisteredService(
                    new ServiceName(ServiceManager.DEFAULT_NAME, "Service0")
                )
            );
            assertTrue(
                ServiceManagerFactory.loadManager()
            );
            assertTrue(
                ServiceManagerFactory.isRegisteredService(
                    new ServiceName(ServiceManager.DEFAULT_NAME, "Service0")
                )
            );
            Service service = null;
            try{
                service = ServiceManagerFactory.getService("Service0");
            }catch(ServiceNotFoundException e){
                fail(e.getMessage());
            }
            assertNotNull(service);
            assertEquals(
                ServiceManager.DEFAULT_NAME,
                service.getServiceManagerName()
            );
            assertEquals("Service0", service.getServiceName());
            assertFalse(
                ServiceManagerFactory.isRegisteredService(
                    new ServiceName("Nimbus2", "Service0")
                )
            );
            service = null;
            try{
                service = ServiceManagerFactory.getService(
                    "Nimbus2",
                    "Service0"
                );
            }catch(ServiceNotFoundException e){
            }
            assertNull(service);
            assertFalse(
                ServiceManagerFactory.isRegisteredService(
                    new ServiceName(ServiceManager.DEFAULT_NAME, "Service1")
                )
            );
            service = null;
            try{
                service = ServiceManagerFactory.getService("Service1");
            }catch(ServiceNotFoundException e){
            }
            assertNull(service);
            assertFalse(ServiceManagerFactory.isRegisteredService(null));
            ServiceManagerFactory.unloadManager();
            assertFalse(
                ServiceManagerFactory.isRegisteredService(
                    new ServiceName(ServiceManager.DEFAULT_NAME, "Service0")
                )
            );
            service = null;
            try{
                service = ServiceManagerFactory.getService(
                    ServiceManager.DEFAULT_NAME,
                    "Service0"
                );
            }catch(ServiceNotFoundException e){
            }
            assertNull(service);
        }finally{
            ServiceManagerFactory.unloadManager();
            if(def != null){
                def.delete();
            }
        }
    }
    
    private class MyRepository implements Repository{
        private final Map managerMap = new Hashtable();
        boolean isNormalRegister = true;
        boolean isNormalUnregister = true;
        boolean isNormalGet = true;
        public Object get(String name){
            if(!isNormalGet){
                return null;
            }
            return (Service)managerMap.get(name);
        }
        public boolean register(String name, Object manager){
            if(!isNormalRegister){
                return false;
            }
            if(managerMap.containsKey(name)){
                return false;
            }
            managerMap.put(name, manager);
            return true;
        }
        public boolean unregister(String name){
            if(!isNormalUnregister){
                return false;
            }
            managerMap.remove(name);
            return true;
        }
        public boolean isRegistered(String name){
            if(!isNormalGet){
                return false;
            }
            return managerMap.containsKey(name);
        }
        public Set nameSet(){
            if(!isNormalGet){
                return new HashSet();
            }
            return new HashSet(managerMap.keySet());
        }
        public Set registeredSet(){
            if(!isNormalGet){
                return new HashSet();
            }
            return new HashSet(managerMap.values());
        }
    }
    
    /**
     * �}�l�[�W��Repository�̕ύX�e�X�g�B<p>
     * �����F
     * <ul>
     *   <li>�T�[�r�X��`�t�@�C��<a href="resources/nimbus-service.xml">"nimbus-service.xml"</a>���N���X�p�X��ɒu���B</li>
     * </ul>
     * �m�F�F
     * <ul>
     *   <li>ServiceManagerFactory#loadManager()���Ăяo���A�߂�l��true��Ԃ��B</li>
     *   <li>ServiceManagerFactory#isRegisteredManager(String)�̈�����"Nimbus"���w�肵�ČĂяo���A�߂�l��true���Ԃ�B</li>
     *   <li>ServiceManagerFactory#setManagerRepository(Repository)�̈����ɓƎ�Repository�I�u�W�F�N�g�P���w�肵�ČĂяo���A�߂�l��true���Ԃ�B</li>
     *   <li>ServiceManagerFactory#isRegisteredManager(String)�̈�����"Nimbus"���w�肵�ČĂяo���A�߂�l��true���Ԃ�B</li>
     *   <li>�Ǝ�Repository�I�u�W�F�N�g�P��Repository#isRegistered(String)�̈�����"Nimbus"���w�肵�ČĂяo���A�߂�l��true���Ԃ�B</li>
     *   <li>ServiceManagerFactory#setManagerRepository(Repository)�̈����ɓƎ�Repository�I�u�W�F�N�g�Q���w�肵�ČĂяo���A�߂�l��true���Ԃ�B</li>
     *   <li>ServiceManagerFactory#isRegisteredManager(String)�̈�����"Nimbus"���w�肵�ČĂяo���A�߂�l��true���Ԃ�B</li>
     *   <li>�Ǝ�Repository�I�u�W�F�N�g�Q��Repository#isRegistered(String)�̈�����"Nimbus"���w�肵�ČĂяo���A�߂�l��true���Ԃ�B</li>
     *   <li>�Ǝ�Repository�I�u�W�F�N�g�P��Repository#isRegistered(String)�̈�����"Nimbus"���w�肵�ČĂяo���A�߂�l��false���Ԃ�B</li>
     * </ul>
     */
    public void testSetManagerRepository1() throws Exception {
        final File def = TestUtility.copyOnClassPath("nimbus-service.xml");
        try{
            assertTrue(
                ServiceManagerFactory.loadManager()
            );
            assertTrue(
                ServiceManagerFactory.isRegisteredManager(
                    ServiceManager.DEFAULT_NAME
                )
            );
            final MyRepository repository1 = new MyRepository();
            assertTrue(ServiceManagerFactory.setManagerRepository(repository1));
            assertTrue(
                ServiceManagerFactory.isRegisteredManager(
                    ServiceManager.DEFAULT_NAME
                )
            );
            assertTrue(repository1.isRegistered(ServiceManager.DEFAULT_NAME));
            
            final MyRepository repository2 = new MyRepository();
            assertTrue(ServiceManagerFactory.setManagerRepository(repository2));
            assertTrue(
                ServiceManagerFactory.isRegisteredManager(
                    ServiceManager.DEFAULT_NAME
                )
            );
            assertTrue(repository2.isRegistered(ServiceManager.DEFAULT_NAME));
            assertFalse(repository1.isRegistered(ServiceManager.DEFAULT_NAME));
        }finally{
            ServiceManagerFactory.unloadManager();
            if(def != null){
                def.delete();
            }
        }
    }
    
    /**
     * �o�^�ł��Ȃ��}�l�[�W��Repository�ւ̕ύX�e�X�g�B<p>
     * �����F
     * <ul>
     *   <li>�T�[�r�X��`�t�@�C��<a href="resources/nimbus-service.xml">"nimbus-service.xml"</a>���N���X�p�X��ɒu���B</li>
     * </ul>
     * �m�F�F
     * <ul>
     *   <li>ServiceManagerFactory#loadManager()���Ăяo���A�߂�l��true��Ԃ��B</li>
     *   <li>ServiceManagerFactory#isRegisteredManager(String)�̈�����"Nimbus"���w�肵�ČĂяo���A�߂�l��true���Ԃ�B</li>
     *   <li>ServiceManagerFactory#setManagerRepository(Repository)�̈����ɓƎ�Repository�I�u�W�F�N�g�P���w�肵�ČĂяo���A�߂�l��true���Ԃ�B</li>
     *   <li>ServiceManagerFactory#isRegisteredManager(String)�̈�����"Nimbus"���w�肵�ČĂяo���A�߂�l��true���Ԃ�B</li>
     *   <li>�Ǝ�Repository�I�u�W�F�N�g�P��Repository#isRegistered(String)�̈�����"Nimbus"���w�肵�ČĂяo���A�߂�l��true���Ԃ�B</li>
     *   <li>ServiceManagerFactory#setManagerRepository(Repository)�̈����ɓƎ�Repository�I�u�W�F�N�g�Q���w�肵�ČĂяo���A�߂�l��true���Ԃ�B</li>
     *   <li>ServiceManagerFactory#isRegisteredManager(String)�̈�����"Nimbus"���w�肵�ČĂяo���A�߂�l��true���Ԃ�B</li>
     *   <li>�Ǝ�Repository�I�u�W�F�N�g�Q��Repository#isRegistered(String)�̈�����"Nimbus"���w�肵�ČĂяo���A�߂�l��true���Ԃ�B</li>
     *   <li>�Ǝ�Repository�I�u�W�F�N�g�P��Repository#isRegistered(String)�̈�����"Nimbus"���w�肵�ČĂяo���A�߂�l��false���Ԃ�B</li>
     *   <li>�Ǝ�Repository�I�u�W�F�N�g�P���ARepository#register(String, Object)�œo�^�ł��Ȃ���Ԃɂ���B</li>
     *   <li>ServiceManagerFactory#setManagerRepository(Repository)�̈����ɓƎ�Repository�I�u�W�F�N�g�P���w�肵�ČĂяo���A�߂�l��false���Ԃ�B</li>
     *   <li>ServiceManagerFactory#isRegisteredManager(String)�̈�����"Nimbus"���w�肵�ČĂяo���A�߂�l��true���Ԃ�B</li>
     *   <li>�Ǝ�Repository�I�u�W�F�N�g�Q��Repository#isRegistered(String)�̈�����"Nimbus"���w�肵�ČĂяo���A�߂�l��true���Ԃ�B</li>
     *   <li>�Ǝ�Repository�I�u�W�F�N�g�P��Repository#isRegistered(String)�̈�����"Nimbus"���w�肵�ČĂяo���A�߂�l��false���Ԃ�B</li>
     * </ul>
     */
    public void testSetManagerRepository2() throws Exception {
        final File def = TestUtility.copyOnClassPath("nimbus-service.xml");
        try{
            assertTrue(
                ServiceManagerFactory.loadManager()
            );
            assertTrue(
                ServiceManagerFactory.isRegisteredManager(
                    ServiceManager.DEFAULT_NAME
                )
            );
            final MyRepository repository1 = new MyRepository();
            assertTrue(ServiceManagerFactory.setManagerRepository(repository1));
            assertTrue(
                ServiceManagerFactory.isRegisteredManager(
                    ServiceManager.DEFAULT_NAME
                )
            );
            assertTrue(repository1.isRegistered(ServiceManager.DEFAULT_NAME));
            
            final MyRepository repository2 = new MyRepository();
            assertTrue(ServiceManagerFactory.setManagerRepository(repository2));
            assertTrue(
                ServiceManagerFactory.isRegisteredManager(
                    ServiceManager.DEFAULT_NAME
                )
            );
            assertTrue(repository2.isRegistered(ServiceManager.DEFAULT_NAME));
            assertFalse(repository1.isRegistered(ServiceManager.DEFAULT_NAME));
            
            repository1.isNormalRegister = false;
            assertFalse(
                ServiceManagerFactory.setManagerRepository(repository1)
            );
            assertTrue(
                ServiceManagerFactory.isRegisteredManager(
                    ServiceManager.DEFAULT_NAME
                )
            );
            assertFalse(repository1.isRegistered(ServiceManager.DEFAULT_NAME));
            assertTrue(repository2.isRegistered(ServiceManager.DEFAULT_NAME));
        }finally{
            ServiceManagerFactory.unloadManager();
            if(def != null){
                def.delete();
            }
        }
    }
    
    /**
     * �擾�ł��Ȃ��}�l�[�W��Repository����̐���ȃ}�l�[�W��Repository�ւ̕ύX�e�X�g�B<p>
     * �����F
     * <ul>
     *   <li>�T�[�r�X��`�t�@�C��<a href="resources/nimbus-service.xml">"nimbus-service.xml"</a>���N���X�p�X��ɒu���B</li>
     * </ul>
     * �m�F�F
     * <ul>
     *   <li>ServiceManagerFactory#loadManager()���Ăяo���A�߂�l��true��Ԃ��B</li>
     *   <li>ServiceManagerFactory#isRegisteredManager(String)�̈�����"Nimbus"���w�肵�ČĂяo���A�߂�l��true���Ԃ�B</li>
     *   <li>ServiceManagerFactory#setManagerRepository(Repository)�̈����ɓƎ�Repository�I�u�W�F�N�g�P���w�肵�ČĂяo���A�߂�l��true���Ԃ�B</li>
     *   <li>ServiceManagerFactory#isRegisteredManager(String)�̈�����"Nimbus"���w�肵�ČĂяo���A�߂�l��true���Ԃ�B</li>
     *   <li>�Ǝ�Repository�I�u�W�F�N�g�P��Repository#isRegistered(String)�̈�����"Nimbus"���w�肵�ČĂяo���A�߂�l��true���Ԃ�B</li>
     *   <li>ServiceManagerFactory#setManagerRepository(Repository)�̈����ɓƎ�Repository�I�u�W�F�N�g�Q���w�肵�ČĂяo���A�߂�l��true���Ԃ�B</li>
     *   <li>ServiceManagerFactory#isRegisteredManager(String)�̈�����"Nimbus"���w�肵�ČĂяo���A�߂�l��true���Ԃ�B</li>
     *   <li>�Ǝ�Repository�I�u�W�F�N�g�Q��Repository#isRegistered(String)�̈�����"Nimbus"���w�肵�ČĂяo���A�߂�l��true���Ԃ�B</li>
     *   <li>�Ǝ�Repository�I�u�W�F�N�g�P��Repository#isRegistered(String)�̈�����"Nimbus"���w�肵�ČĂяo���A�߂�l��false���Ԃ�B</li>
     *   <li>�Ǝ�Repository�I�u�W�F�N�g�Q���ARepository#get(String)�Ŏ擾�ł��Ȃ���Ԃɂ���B</li>
     *   <li>ServiceManagerFactory#setManagerRepository(Repository)�̈����ɓƎ�Repository�I�u�W�F�N�g�P���w�肵�ČĂяo���A�߂�l��true���Ԃ�B</li>
     *   <li>ServiceManagerFactory#isRegisteredManager(String)�̈�����"Nimbus"���w�肵�ČĂяo���A�߂�l��false���Ԃ�B</li>
     *   <li>�Ǝ�Repository�I�u�W�F�N�g�Q���ARepository#get(String)�Ŏ擾�ł����Ԃɂ���B</li>
     *   <li>�Ǝ�Repository�I�u�W�F�N�g�Q��Repository#isRegistered(String)�̈�����"Nimbus"���w�肵�ČĂяo���A�߂�l��false���Ԃ�B</li>
     *   <li>�Ǝ�Repository�I�u�W�F�N�g�P��Repository#isRegistered(String)�̈�����"Nimbus"���w�肵�ČĂяo���A�߂�l��true���Ԃ�B</li>
     * </ul>
     */
    public void testSetManagerRepository3() throws Exception {
        final File def = TestUtility.copyOnClassPath("nimbus-service.xml");
        try{
            assertTrue(
                ServiceManagerFactory.loadManager()
            );
            assertTrue(
                ServiceManagerFactory.isRegisteredManager(
                    ServiceManager.DEFAULT_NAME
                )
            );
            final MyRepository repository1 = new MyRepository();
            assertTrue(ServiceManagerFactory.setManagerRepository(repository1));
            assertTrue(
                ServiceManagerFactory.isRegisteredManager(
                    ServiceManager.DEFAULT_NAME
                )
            );
            assertTrue(repository1.isRegistered(ServiceManager.DEFAULT_NAME));
            
            final MyRepository repository2 = new MyRepository();
            assertTrue(ServiceManagerFactory.setManagerRepository(repository2));
            assertTrue(
                ServiceManagerFactory.isRegisteredManager(
                    ServiceManager.DEFAULT_NAME
                )
            );
            assertTrue(repository2.isRegistered(ServiceManager.DEFAULT_NAME));
            assertFalse(repository1.isRegistered(ServiceManager.DEFAULT_NAME));
            
            repository2.isNormalGet = false;
            assertTrue(
                ServiceManagerFactory.setManagerRepository(repository1)
            );
            assertFalse(
                ServiceManagerFactory.isRegisteredManager(
                    ServiceManager.DEFAULT_NAME
                )
            );
            assertFalse(repository1.isRegistered(ServiceManager.DEFAULT_NAME));
            repository2.isNormalGet = true;
            assertTrue(repository2.isRegistered(ServiceManager.DEFAULT_NAME));
        }finally{
            ServiceManagerFactory.unloadManager();
            if(def != null){
                def.delete();
            }
        }
    }
    
    /**
     * �o�^�����ł��Ȃ��}�l�[�W��Repository�ւ̕ύX�e�X�g�B<p>
     * �����F
     * <ul>
     *   <li>�T�[�r�X��`�t�@�C��<a href="resources/nimbus-service.xml">"nimbus-service.xml"</a>���N���X�p�X��ɒu���B</li>
     * </ul>
     * �m�F�F
     * <ul>
     *   <li>ServiceManagerFactory#loadManager()���Ăяo���A�߂�l��true��Ԃ��B</li>
     *   <li>ServiceManagerFactory#isRegisteredManager(String)�̈�����"Nimbus"���w�肵�ČĂяo���A�߂�l��true���Ԃ�B</li>
     *   <li>ServiceManagerFactory#setManagerRepository(Repository)�̈����ɓƎ�Repository�I�u�W�F�N�g�P���w�肵�ČĂяo���A�߂�l��true���Ԃ�B</li>
     *   <li>ServiceManagerFactory#isRegisteredManager(String)�̈�����"Nimbus"���w�肵�ČĂяo���A�߂�l��true���Ԃ�B</li>
     *   <li>�Ǝ�Repository�I�u�W�F�N�g�P��Repository#isRegistered(String)�̈�����"Nimbus"���w�肵�ČĂяo���A�߂�l��true���Ԃ�B</li>
     *   <li>ServiceManagerFactory#setManagerRepository(Repository)�̈����ɓƎ�Repository�I�u�W�F�N�g�Q���w�肵�ČĂяo���A�߂�l��true���Ԃ�B</li>
     *   <li>ServiceManagerFactory#isRegisteredManager(String)�̈�����"Nimbus"���w�肵�ČĂяo���A�߂�l��true���Ԃ�B</li>
     *   <li>�Ǝ�Repository�I�u�W�F�N�g�Q��Repository#isRegistered(String)�̈�����"Nimbus"���w�肵�ČĂяo���A�߂�l��true���Ԃ�B</li>
     *   <li>�Ǝ�Repository�I�u�W�F�N�g�P��Repository#isRegistered(String)�̈�����"Nimbus"���w�肵�ČĂяo���A�߂�l��false���Ԃ�B</li>
     *   <li>�Ǝ�Repository�I�u�W�F�N�g�Q���ARepository#unregister(String)�œo�^�����ł��Ȃ���Ԃɂ���B</li>
     *   <li>ServiceManagerFactory#setManagerRepository(Repository)�̈����ɓƎ�Repository�I�u�W�F�N�g�P���w�肵�ČĂяo���A�߂�l��true���Ԃ�B</li>
     *   <li>ServiceManagerFactory#isRegisteredManager(String)�̈�����"Nimbus"���w�肵�ČĂяo���A�߂�l��true���Ԃ�B</li>
     *   <li>�Ǝ�Repository�I�u�W�F�N�g�Q��Repository#isRegistered(String)�̈�����"Nimbus"���w�肵�ČĂяo���A�߂�l��true���Ԃ�B</li>
     *   <li>�Ǝ�Repository�I�u�W�F�N�g�P��Repository#isRegistered(String)�̈�����"Nimbus"���w�肵�ČĂяo���A�߂�l��true���Ԃ�B</li>
     * </ul>
     */
    public void testSetManagerRepository4() throws Exception {
        final File def = TestUtility.copyOnClassPath("nimbus-service.xml");
        try{
            assertTrue(ServiceManagerFactory.loadManager());
            assertTrue(
                ServiceManagerFactory.isRegisteredManager(
                    ServiceManager.DEFAULT_NAME
                )
            );
            final MyRepository repository1 = new MyRepository();
            assertTrue(ServiceManagerFactory.setManagerRepository(repository1));
            assertTrue(
                ServiceManagerFactory.isRegisteredManager(
                    ServiceManager.DEFAULT_NAME
                )
            );
            assertTrue(repository1.isRegistered(ServiceManager.DEFAULT_NAME));
            
            final MyRepository repository2 = new MyRepository();
            assertTrue(ServiceManagerFactory.setManagerRepository(repository2));
            assertTrue(
                ServiceManagerFactory.isRegisteredManager(
                    ServiceManager.DEFAULT_NAME
                )
            );
            assertTrue(repository2.isRegistered(ServiceManager.DEFAULT_NAME));
            assertFalse(repository1.isRegistered(ServiceManager.DEFAULT_NAME));
            
            repository2.isNormalUnregister = false;
            assertTrue(
                ServiceManagerFactory.setManagerRepository(repository1)
            );
            assertTrue(
                ServiceManagerFactory.isRegisteredManager(
                    ServiceManager.DEFAULT_NAME
                )
            );
            assertTrue(repository1.isRegistered(ServiceManager.DEFAULT_NAME));
            assertTrue(repository2.isRegistered(ServiceManager.DEFAULT_NAME));
        }finally{
            ServiceManagerFactory.unloadManager();
            if(def != null){
                def.delete();
            }
        }
    }
    
    /**
     * �����}�l�[�W��Repository�ւ̕ύX�e�X�g�B<p>
     * �����F
     * <ul>
     *   <li>�T�[�r�X��`�t�@�C��<a href="resources/nimbus-service.xml">"nimbus-service.xml"</a>���N���X�p�X��ɒu���B</li>
     * </ul>
     * �m�F�F
     * <ul>
     *   <li>ServiceManagerFactory#loadManager()���Ăяo���A�߂�l��true��Ԃ��B</li>
     *   <li>ServiceManagerFactory#isRegisteredManager(String)�̈�����"Nimbus"���w�肵�ČĂяo���A�߂�l��true���Ԃ�B</li>
     *   <li>ServiceManagerFactory#setManagerRepository(Repository)�̈����ɓƎ�Repository�I�u�W�F�N�g�P���w�肵�ČĂяo���A�߂�l��true���Ԃ�B</li>
     *   <li>ServiceManagerFactory#isRegisteredManager(String)�̈�����"Nimbus"���w�肵�ČĂяo���A�߂�l��true���Ԃ�B</li>
     *   <li>�Ǝ�Repository�I�u�W�F�N�g�P��Repository#isRegistered(String)�̈�����"Nimbus"���w�肵�ČĂяo���A�߂�l��true���Ԃ�B</li>
     *   <li>ServiceManagerFactory#setManagerRepository(Repository)�̈����ɓƎ�Repository�I�u�W�F�N�g�Q���w�肵�ČĂяo���A�߂�l��true���Ԃ�B</li>
     *   <li>ServiceManagerFactory#isRegisteredManager(String)�̈�����"Nimbus"���w�肵�ČĂяo���A�߂�l��true���Ԃ�B</li>
     *   <li>�Ǝ�Repository�I�u�W�F�N�g�Q��Repository#isRegistered(String)�̈�����"Nimbus"���w�肵�ČĂяo���A�߂�l��true���Ԃ�B</li>
     *   <li>�Ǝ�Repository�I�u�W�F�N�g�P��Repository#isRegistered(String)�̈�����"Nimbus"���w�肵�ČĂяo���A�߂�l��false���Ԃ�B</li>
     *   <li>ServiceManagerFactory#setManagerRepository(Repository)�̈����ɓƎ�Repository�I�u�W�F�N�g�Q���w�肵�ČĂяo���A�߂�l��true���Ԃ�B</li>
     *   <li>ServiceManagerFactory#isRegisteredManager(String)�̈�����"Nimbus"���w�肵�ČĂяo���A�߂�l��true���Ԃ�B</li>
     *   <li>�Ǝ�Repository�I�u�W�F�N�g�Q��Repository#isRegistered(String)�̈�����"Nimbus"���w�肵�ČĂяo���A�߂�l��true���Ԃ�B</li>
     * </ul>
     */
    public void testSetManagerRepository5() throws Exception {
        final File def = TestUtility.copyOnClassPath("nimbus-service.xml");
        try{
            assertTrue(
                ServiceManagerFactory.loadManager()
            );
            assertTrue(
                ServiceManagerFactory.isRegisteredManager(
                    ServiceManager.DEFAULT_NAME
                )
            );
            final MyRepository repository1 = new MyRepository();
            assertTrue(ServiceManagerFactory.setManagerRepository(repository1));
            assertTrue(
                ServiceManagerFactory.isRegisteredManager(
                    ServiceManager.DEFAULT_NAME
                )
            );
            assertTrue(repository1.isRegistered(ServiceManager.DEFAULT_NAME));
            
            final MyRepository repository2 = new MyRepository();
            assertTrue(ServiceManagerFactory.setManagerRepository(repository2));
            assertTrue(
                ServiceManagerFactory.isRegisteredManager(
                    ServiceManager.DEFAULT_NAME
                )
            );
            assertTrue(repository2.isRegistered(ServiceManager.DEFAULT_NAME));
            assertFalse(repository1.isRegistered(ServiceManager.DEFAULT_NAME));
            
            assertTrue(
                ServiceManagerFactory.setManagerRepository(repository2)
            );
            assertTrue(
                ServiceManagerFactory.isRegisteredManager(
                    ServiceManager.DEFAULT_NAME
                )
            );
            assertTrue(repository2.isRegistered(ServiceManager.DEFAULT_NAME));
        }finally{
            ServiceManagerFactory.unloadManager();
            if(def != null){
                def.delete();
            }
        }
    }
    
    /**
     * �f�t�H���g�̃}�l�[�W��Repository�ւ̕ύX�e�X�g�B<p>
     * �����F
     * <ul>
     *   <li>�T�[�r�X��`�t�@�C��<a href="resources/nimbus-service.xml">"nimbus-service.xml"</a>���N���X�p�X��ɒu���B</li>
     * </ul>
     * �m�F�F
     * <ul>
     *   <li>ServiceManagerFactory#loadManager()���Ăяo���A�߂�l��true��Ԃ��B</li>
     *   <li>ServiceManagerFactory#isRegisteredManager(String)�̈�����"Nimbus"���w�肵�ČĂяo���A�߂�l��true���Ԃ�B</li>
     *   <li>ServiceManagerFactory#setManagerRepository(Repository)�̈����ɓƎ�Repository�I�u�W�F�N�g�P���w�肵�ČĂяo���A�߂�l��true���Ԃ�B</li>
     *   <li>ServiceManagerFactory#isRegisteredManager(String)�̈�����"Nimbus"���w�肵�ČĂяo���A�߂�l��true���Ԃ�B</li>
     *   <li>�Ǝ�Repository�I�u�W�F�N�g�P��Repository#isRegistered(String)�̈�����"Nimbus"���w�肵�ČĂяo���A�߂�l��true���Ԃ�B</li>
     *   <li>ServiceManagerFactory#setManagerRepository(Repository)�̈����ɓƎ�Repository�I�u�W�F�N�g�Q���w�肵�ČĂяo���A�߂�l��true���Ԃ�B</li>
     *   <li>ServiceManagerFactory#isRegisteredManager(String)�̈�����"Nimbus"���w�肵�ČĂяo���A�߂�l��true���Ԃ�B</li>
     *   <li>�Ǝ�Repository�I�u�W�F�N�g�Q��Repository#isRegistered(String)�̈�����"Nimbus"���w�肵�ČĂяo���A�߂�l��true���Ԃ�B</li>
     *   <li>�Ǝ�Repository�I�u�W�F�N�g�P��Repository#isRegistered(String)�̈�����"Nimbus"���w�肵�ČĂяo���A�߂�l��false���Ԃ�B</li>
     *   <li>ServiceManagerFactory#setManagerRepository(Repository)�̈�����null���w�肵�ČĂяo���A�߂�l��true���Ԃ�B</li>
     *   <li>ServiceManagerFactory#isRegisteredManager(String)�̈�����"Nimbus"���w�肵�ČĂяo���A�߂�l��true���Ԃ�B</li>
     *   <li>�Ǝ�Repository�I�u�W�F�N�g�Q��Repository#isRegistered(String)�̈�����"Nimbus"���w�肵�ČĂяo���A�߂�l��false���Ԃ�B</li>
     * </ul>
     */
    public void testSetManagerRepository6() throws Exception {
        final File def = TestUtility.copyOnClassPath("nimbus-service.xml");
        try{
            assertTrue(
                ServiceManagerFactory.loadManager()
            );
            assertTrue(
                ServiceManagerFactory.isRegisteredManager(
                    ServiceManager.DEFAULT_NAME
                )
            );
            final MyRepository repository1 = new MyRepository();
            assertTrue(ServiceManagerFactory.setManagerRepository(repository1));
            assertTrue(
                ServiceManagerFactory.isRegisteredManager(
                    ServiceManager.DEFAULT_NAME
                )
            );
            assertTrue(repository1.isRegistered(ServiceManager.DEFAULT_NAME));
            
            final MyRepository repository2 = new MyRepository();
            assertTrue(ServiceManagerFactory.setManagerRepository(repository2));
            assertTrue(
                ServiceManagerFactory.isRegisteredManager(
                    ServiceManager.DEFAULT_NAME
                )
            );
            assertTrue(repository2.isRegistered(ServiceManager.DEFAULT_NAME));
            assertFalse(repository1.isRegistered(ServiceManager.DEFAULT_NAME));
            
            assertTrue(
                ServiceManagerFactory.setManagerRepository((Repository)null)
            );
            assertTrue(
                ServiceManagerFactory.isRegisteredManager(
                    ServiceManager.DEFAULT_NAME
                )
            );
            assertFalse(repository2.isRegistered(ServiceManager.DEFAULT_NAME));
        }finally{
            ServiceManagerFactory.unloadManager();
            if(def != null){
                def.delete();
            }
        }
    }
    
    /**
     * ServiceLoader�̓o�^�E�o�^�����̃e�X�g�B<p>
     * �����F
     * <ul>
     *   <li>�T�[�r�X��`�t�@�C��<a href="resources/nimbus-service.xml">"nimbus-service.xml"</a>���N���X�p�X��ɒu���B</li>
     * </ul>
     * �m�F�F
     * <ul>
     *   <li>ServiceManagerFactory#loadManager()���Ăяo���A�߂�l��true��Ԃ��B</li>
     *   <li>ServiceManagerFactory#getLoaders()���Ăяo���A�߂�l�̏W���̃T�C�Y��1�ł���B</li>
     *   <li>ServiceManagerFactory#getLoader(URL)�̈�����"nimbus-service.xml"��URL���w�肵�ČĂяo���A�߂�l��null�łȂ��B</li>
     *   <li>ServiceManagerFactory#unregisterLoader(ServiceLoader)�̈����Ɏ擾����ServiceLoader���w�肵�ČĂяo���B</li>
     *   <li>ServiceManagerFactory#getLoader(URL)�̈�����"nimbus-service.xml"��URL���w�肵�ČĂяo���A�߂�l��null�ł���B</li>
     *   <li>ServiceManagerFactory#registerLoader(ServiceLoader)�̈����Ɏ擾����ServiceLoader���w�肵�ČĂяo���B</li>
     *   <li>ServiceManagerFactory#getLoader(URL)�̈�����"nimbus-service.xml"��URL���w�肵�ČĂяo���A�߂�l��null�łȂ��B</li>
     *   <li>ServiceManagerFactory#unloadManager()���Ăяo���B</li>
     *   <li>ServiceManagerFactory#getLoader(URL)�̈�����"nimbus-service.xml"��URL���w�肵�ČĂяo���A�߂�l��null�ł���B</li>
     * </ul>
     */
    public void testRegisterAndUnregisterLoader() throws Exception {
        final File def = TestUtility.copyOnClassPath("nimbus-service.xml");
        try{
            assertTrue(ServiceManagerFactory.loadManager());
            Collection loaders = ServiceManagerFactory.getLoaders();
            assertEquals(1, loaders.size());
            final ServiceLoader loader = ServiceManagerFactory.getLoader(
                def.toURL()
            );
            assertNotNull(loader);
            ServiceManagerFactory.unregisterLoader(loader);
            assertNull(ServiceManagerFactory.getLoader(def.toURL()));
            ServiceManagerFactory.registerLoader(loader);
            assertNotNull(ServiceManagerFactory.getLoader(def.toURL()));
            ServiceManagerFactory.unloadManager();
            assertNull(ServiceManagerFactory.getLoader(def.toURL()));
        }finally{
            ServiceManagerFactory.unloadManager();
            if(def != null){
                def.delete();
            }
        }
    }
    
    public static class MyServiceLoader extends DefaultServiceLoaderService{

        private static final long serialVersionUID = -8181574796579007327L;
    }
    
    /**
     * ServiceLoader�����N���X�ݒ�̃e�X�g�B<p>
     * �����F
     * <ul>
     *   <li>�T�[�r�X��`�t�@�C��<a href="resources/nimbus-service.xml">"nimbus-service.xml"</a>���N���X�p�X��ɒu���B</li>
     * </ul>
     * �m�F�F
     * <ul>
     *   <li>ServiceManagerFactory#setServiceLoaderClass(Class)�̈�����ServiceLoader�C���^�t�F�[�X���������Ă��Ȃ��N���XMyDummyServiceLoader���w�肵�ČĂяo���AIllegalArgumentException����������B</li>
     *   <li>ServiceManagerFactory#setServiceLoaderClass(Class)�̈�����ServiceLoader�C���^�t�F�[�X�����������N���XMyServiceLoader���w�肵�ČĂяo���A����ɐݒ�ł���B</li>
     *   <li>ServiceManagerFactory#getServiceLoaderClass()���Ăяo���A�߂�l��MyServiceLoader�N���X���擾�ł���B</li>
     *   <li>ServiceManagerFactory#loadManager()���Ăяo���A�߂�l��true��Ԃ��B</li>
     *   <li>ServiceManagerFactory#getLoader(URL)�̈�����"nimbus-service.xml"��URL���w�肵�ČĂяo���A�߂�l��MyServiceLoader�̃C���X�^���X�ł���B</li>
     *   <li>ServiceManagerFactory#setServiceLoaderClass(Class)�̈�����ServiceLoader�C���^�t�F�[�X���������Ă��邪�A�O������C���X�^���X���ł��Ȃ����[�J���N���XLocalMyServiceLoader���w�肵�ČĂяo���A����ɐݒ�ł���B</li>
     *   <li>ServiceManagerFactory#getServiceLoaderClass()���Ăяo���A�߂�l��LocalMyServiceLoader�N���X���擾�ł���B</li>
     *   <li>ServiceManagerFactory#loadManager()���Ăяo���A�߂�l��false��Ԃ��B</li>
     * </ul>
     */
    public void testServiceLoaderClass1() throws Exception {
        final File def = TestUtility.copyOnClassPath("nimbus-service.xml");
        class MyDummyServiceLoader{}
        class LocalMyServiceLoader extends DefaultServiceLoaderService{
            private static final long serialVersionUID = -5571466369298423736L;
        }
        try{
            try{
                ServiceManagerFactory.setServiceLoaderClass(
                    MyDummyServiceLoader.class
                );
                fail("Argument of method setServiceLoaderClass(Class) must be ServiceLoader.");
            }catch(IllegalArgumentException e){
            }
            ServiceManagerFactory.setServiceLoaderClass(MyServiceLoader.class);
            assertEquals(
                MyServiceLoader.class, 
                ServiceManagerFactory.getServiceLoaderClass()
            );
            assertTrue(ServiceManagerFactory.loadManager());
            ServiceLoader loader = ServiceManagerFactory.getLoader(
                def.toURL()
            );
            assertTrue((loader instanceof MyServiceLoader));
            ServiceManagerFactory.unloadManager();
            
            ServiceManagerFactory.setServiceLoaderClass(
                LocalMyServiceLoader.class
            );
            assertEquals(
                LocalMyServiceLoader.class, 
                ServiceManagerFactory.getServiceLoaderClass()
            );
            assertFalse(ServiceManagerFactory.loadManager());
        }finally{
            ServiceManagerFactory.unloadManager();
            ServiceManagerFactory.setServiceLoaderClass(null);
            if(def != null){
                def.delete();
            }
        }
    }
    
    /**
     * �V�X�e���v���p�e�B�ɂ��ServiceLoader�����N���X�ݒ�̃e�X�g�B<p>
     * �����F
     * <ul>
     *   <li>�T�[�r�X��`�t�@�C��<a href="resources/nimbus-service.xml">"nimbus-service.xml"</a>���N���X�p�X��ɒu���B</li>
     * </ul>
     * �m�F�F
     * <ul>
     *   <li>System.setProperty(String, String)�̈�����"jp.ossc.nimbus.core.loader"��ServiceLoader�C���^�t�F�[�X���������Ă��Ȃ��N���XMyDummyServiceLoader�̃N���X�����w�肵�ČĂяo���B</li>
     *   <li>ServiceManagerFactory#loadManager()���Ăяo���A�߂�l��true��Ԃ��B</li>
     *   <li>ServiceManagerFactory#getLoader(URL)�̈�����"nimbus-service.xml"��URL���w�肵�ČĂяo���A�߂�l��MyDummyServiceLoader�̃C���X�^���X�łȂ��B</li>
     *   <li>ServiceManagerFactory#unloadManager()���Ăяo���B</li>
     *   <li>System.setProperty(String, String)�̈�����"jp.ossc.nimbus.core.loader"�Ƒ��݂��Ȃ��N���XUsoUsoServiceLoader�̃N���X�����w�肵�ČĂяo���B</li>
     *   <li>ServiceManagerFactory#loadManager()���Ăяo���A�߂�l��true��Ԃ��B</li>
     *   <li>ServiceManagerFactory#getLoader(URL)�̈�����"nimbus-service.xml"��URL���w�肵�ČĂяo���A�߂�l��UsoUsoServiceLoader�̃C���X�^���X�łȂ��B</li>
     *   <li>ServiceManagerFactory#unloadManager()���Ăяo���B</li>
     *   <li>System.setProperty(String, String)�̈�����"jp.ossc.nimbus.core.loader"��ServiceLoader�C���^�t�F�[�X�����������N���XMyServiceLoader�̃N���X�����w�肵�ČĂяo���B</li>
     *   <li>ServiceManagerFactory#loadManager()���Ăяo���A�߂�l��true��Ԃ��B</li>
     *   <li>ServiceManagerFactory#getLoader(URL)�̈�����"nimbus-service.xml"��URL���w�肵�ČĂяo���A�߂�l��MyServiceLoader�̃C���X�^���X�ł���B</li>
     * </ul>
     */
    public void testServiceLoaderClass2() throws Exception {
        final File def = TestUtility.copyOnClassPath("nimbus-service.xml");
        class MyDummyServiceLoader{}
        try{
            System.setProperty(
                "jp.ossc.nimbus.core.loader",
                MyDummyServiceLoader.class.getName()
            );
            assertTrue(ServiceManagerFactory.loadManager());
            ServiceLoader loader = ServiceManagerFactory.getLoader(
                def.toURL()
            );
            assertFalse((loader instanceof MyDummyServiceLoader));
            ServiceManagerFactory.unloadManager();
            
            System.setProperty(
                "jp.ossc.nimbus.core.loader",
                "UsoUsoServiceLoader"
            );
            assertTrue(ServiceManagerFactory.loadManager());
            loader = ServiceManagerFactory.getLoader(
                def.toURL()
            );
            assertFalse(
                "UsoUsoServiceLoader".equals(loader.getClass().getName())
            );
            ServiceManagerFactory.unloadManager();
            
            System.setProperty(
                "jp.ossc.nimbus.core.loader",
                MyServiceLoader.class.getName()
            );
            assertTrue(ServiceManagerFactory.loadManager());
            loader = ServiceManagerFactory.getLoader(
                def.toURL()
            );
            assertTrue((loader instanceof MyServiceLoader));
            System.setProperty(
                "jp.ossc.nimbus.core.loader",
                ""
            );
        }finally{
            ServiceManagerFactory.unloadManager();
            ServiceManagerFactory.setServiceLoaderClass(null);
            if(def != null){
                def.delete();
            }
        }
    }
    
    public static class MyServiceManager extends DefaultServiceManagerService{
        private static final long serialVersionUID = -4462377972355321537L;
    }
    
    /**
     * ServiceManager�����N���X�ݒ�̃e�X�g�B<p>
     * �����F
     * <ul>
     *   <li>�T�[�r�X��`�t�@�C��<a href="resources/nimbus-service.xml">"nimbus-service.xml"</a>���N���X�p�X��ɒu���B</li>
     * </ul>
     * �m�F�F
     * <ul>
     *   <li>ServiceManagerFactory#setServiceManagerClass(Class)�̈�����ServiceManager�C���^�t�F�[�X���������Ă��Ȃ��N���XMyDummyServiceManager���w�肵�ČĂяo���AIllegalArgumentException����������B</li>
     *   <li>ServiceManagerFactory#setServiceManagerClass(Class)�̈�����ServiceManager�C���^�t�F�[�X�����������N���XMyServiceManager���w�肵�ČĂяo���A����ɐݒ�ł���B</li>
     *   <li>ServiceManagerFactory#getServiceManagerClass()���Ăяo���A�߂�l��MyServiceManager�N���X���擾�ł���B</li>
     *   <li>ServiceManagerFactory#loadManager()���Ăяo���A�߂�l��true��Ԃ��B</li>
     *   <li>ServiceManagerFactory#findManager()���Ăяo���A�߂�l��MyServiceManager�̃C���X�^���X�ł���B</li>
     *   <li>ServiceManagerFactory#setServiceManagerClass(Class)�̈�����ServiceManager�C���^�t�F�[�X���������Ă��邪�A�O������C���X�^���X���ł��Ȃ����[�J���N���XLocalMyServiceManager���w�肵�ČĂяo���A����ɐݒ�ł���B</li>
     *   <li>ServiceManagerFactory#getServiceManagerClass()���Ăяo���A�߂�l��LocalMyServiceManager�N���X���擾�ł���B</li>
     *   <li>ServiceManagerFactory#loadManager()���Ăяo���A�߂�l��false��Ԃ��B</li>
     * </ul>
     */
    public void testServiceManagerClass1() throws Exception {
        final File def = TestUtility.copyOnClassPath("nimbus-service.xml");
        class MyDummyServiceManager{}
        class LocalMyServiceManager extends DefaultServiceManagerService{
            private static final long serialVersionUID = 6353430722805581225L;
        }
        try{
            try{
                ServiceManagerFactory.setServiceManagerClass(
                    MyDummyServiceManager.class
                );
                fail("Argument of method setServiceManagerClass(Class) must be ServiceManager.");
            }catch(IllegalArgumentException e){
            }
            ServiceManagerFactory.setServiceManagerClass(
                MyServiceManager.class
            );
            assertEquals(
                MyServiceManager.class, 
                ServiceManagerFactory.getServiceManagerClass()
            );
            assertTrue(ServiceManagerFactory.loadManager());
            ServiceManager manager = ServiceManagerFactory.findManager();
            assertTrue((manager instanceof MyServiceManager));
            ServiceManagerFactory.unloadManager();
            
            ServiceManagerFactory.setServiceManagerClass(
                LocalMyServiceManager.class
            );
            assertEquals(
                LocalMyServiceManager.class, 
                ServiceManagerFactory.getServiceManagerClass()
            );
            assertFalse(ServiceManagerFactory.loadManager());
        }finally{
            ServiceManagerFactory.unloadManager();
            ServiceManagerFactory.setServiceManagerClass(null);
            if(def != null){
                def.delete();
            }
        }
    }
    
    /**
     * �V�X�e���v���p�e�B�ɂ��ServiceManager�����N���X�ݒ�̃e�X�g�B<p>
     * �����F
     * <ul>
     *   <li>�T�[�r�X��`�t�@�C��<a href="resources/nimbus-service.xml">"nimbus-service.xml"</a>���N���X�p�X��ɒu���B</li>
     * </ul>
     * �m�F�F
     * <ul>
     *   <li>System.setProperty(String, String)�̈�����"jp.ossc.nimbus.core.manager"��ServiceManager�C���^�t�F�[�X���������Ă��Ȃ��N���XMyDummyServiceManager�̃N���X�����w�肵�ČĂяo���B</li>
     *   <li>ServiceManagerFactory#loadManager()���Ăяo���A�߂�l��true��Ԃ��B</li>
     *   <li>ServiceManagerFactory#findManager()���Ăяo���A�߂�l��MyDummyServiceManager�̃C���X�^���X�łȂ��B</li>
     *   <li>ServiceManagerFactory#unloadManager()���Ăяo���B</li>
     *   <li>System.setProperty(String, String)�̈�����"jp.ossc.nimbus.core.manager"�Ƒ��݂��Ȃ��N���XUsoUsoServiceManager�̃N���X�����w�肵�ČĂяo���B</li>
     *   <li>ServiceManagerFactory#loadManager()���Ăяo���A�߂�l��true��Ԃ��B</li>
     *   <li>ServiceManagerFactory#findManager()���Ăяo���A�߂�l��UsoUsoServiceManager�̃C���X�^���X�łȂ��B</li>
     *   <li>ServiceManagerFactory#unloadManager()���Ăяo���B</li>
     *   <li>System.setProperty(String, String)�̈�����"jp.ossc.nimbus.core.manager"��ServiceManager�C���^�t�F�[�X�����������N���XMyServiceManager�̃N���X�����w�肵�ČĂяo���B</li>
     *   <li>ServiceManagerFactory#loadManager()���Ăяo���A�߂�l��true��Ԃ��B</li>
     *   <li>ServiceManagerFactory#findManager()���Ăяo���A�߂�l��MyServiceManager�̃C���X�^���X�ł���B</li>
     * </ul>
     */
    public void testServiceManagerClass2() throws Exception {
        final File def = TestUtility.copyOnClassPath("nimbus-service.xml");
        class MyDummyServiceManager{}
        try{
            System.setProperty(
                "jp.ossc.nimbus.core.manager",
                MyDummyServiceManager.class.getName()
            );
            assertTrue(ServiceManagerFactory.loadManager());
            ServiceManager manager = ServiceManagerFactory.findManager();
            assertFalse((manager instanceof MyDummyServiceManager));
            ServiceManagerFactory.unloadManager();
            
            System.setProperty(
                "jp.ossc.nimbus.core.manager",
                "UsoUsoServiceManager"
            );
            assertTrue(ServiceManagerFactory.loadManager());
            manager = ServiceManagerFactory.findManager();
            assertFalse(
                "UsoUsoServiceManager".equals(manager.getClass().getName())
            );
            ServiceManagerFactory.unloadManager();
            
            System.setProperty(
                "jp.ossc.nimbus.core.manager",
                MyServiceManager.class.getName()
            );
            assertTrue(ServiceManagerFactory.loadManager());
            manager = ServiceManagerFactory.findManager();
            assertTrue((manager instanceof MyServiceManager));
            System.setProperty(
                "jp.ossc.nimbus.core.manager",
                ""
            );
        }finally{
            ServiceManagerFactory.unloadManager();
            ServiceManagerFactory.setServiceManagerClass(null);
            if(def != null){
                def.delete();
            }
        }
    }
    
    /**
     * �f�t�H���gLogger�̃e�X�g�B<p>
     * �����F
     * <ul>
     *   <li>�f�t�H���g���̃T�[�r�X��`�t�@�C��<a href="resources/nimbus-service5.xml">"nimbus-service.xml"</a>���N���X�p�X��ɒu���B</li>
     * </ul>
     * �m�F�F
     * <ul>
     *   <li>ServiceManagerFactory#loadManager()���Ăяo���A�߂�l��true��Ԃ��B</li>
     *   <li>ServiceManagerFactory#checkLoadManagerCompleted()��true��Ԃ��B</li>
     *   <li>ServiceManagerFactory#findManager()���Ăяo����ServiceManager���擾�ł���B</li>
     *   <li>ServiceManager#getServiceObject(String)���Ăяo���ăT�[�r�X��"Service0"�̃T�[�r�X�I�u�W�F�N�g���擾�ł���B</li>
     *   <li>ServiceManagerFactory#getLogger()���Ăяo����Logger�I�u�W�F�N�g���擾�ł���B</li>
     *   <li>�擾����Logger�ŁALogger#debug(String)���Ăяo���A�W���o�͂Ƀf�o�b�O���b�Z�[�W���o�͂����B</li>
     *   <li>ServiceManager#getLogger()���Ăяo����Logger�I�u�W�F�N�g���擾�ł���B</li>
     *   <li>�擾����Logger�ŁALogger#debug(String)���Ăяo���A�W���o�͂Ƀf�o�b�O���b�Z�[�W���o�͂����B</li>
     *   <li>TestServiceBase#getLogger()���Ăяo����Logger�I�u�W�F�N�g���擾�ł���B</li>
     *   <li>�擾����Logger�ŁALogger#write(String)���Ăяo���A�W���o�͂Ɏw�肵�����b�Z�[�WID�̃��b�Z�[�W���o�͂����B</li>
     * </ul>
     */
/*    public void testDefaultLogger() throws Exception {
        File def = null;
        PrintStream ps = null;
        final PrintStream out = System.out;
        final PipedInputStream pis = new PipedInputStream();
        final Set fails = new HashSet();
        try{
            def = TestUtility.copyOnClassPath(
                "nimbus-service5.xml",
                "nimbus-service.xml"
            );
            assertTrue(ServiceManagerFactory.loadManager());
            assertTrue(ServiceManagerFactory.checkLoadManagerCompleted());
            ps = new PrintStream(new PipedOutputStream(pis));
            ServiceManagerFactory.DEFAULT_LOGGER.stop();
            System.setOut(ps);
            ServiceManagerFactory.DEFAULT_LOGGER.start();
            final BufferedReader br = new BufferedReader(
                new InputStreamReader(pis)
            );
            final ServiceManager manager = ServiceManagerFactory.findManager();
            assertNotNull(manager);
            TestServiceBase serviceObj0 = null;
            try{
                serviceObj0 = (TestServiceBase)manager.getServiceObject(
                    "Service0"
                );
            }catch(ServiceNotFoundException e){
                fail(e.getMessage());
            }
            Logger logger = ServiceManagerFactory.getLogger();
            assertNotNull(logger);
            logger.debug("�e�X�g�J�n");
            logger = manager.getLogger();
            assertNotNull(logger);
            logger.debug("�e�X�g");
            logger = serviceObj0.getLogger();
            assertNotNull(logger);
            logger.write("SVCM_00035");
            MessageRecordFactory msgRecFactory
                 = ServiceManagerFactory.getMessageRecordFactory();
            assertNotNull(msgRecFactory);
            final String message = msgRecFactory.findMessage("SVCM_00035");
            assertNotNull(message);
            final Thread thread = new Thread(
                new Runnable(){
                    public void run(){
                        try{
                            String line = null;
                            while((line = br.readLine()) != null){
                                if(line.indexOf("�e�X�g�J�n") != -1){
                                    break;
                                }
                            }
                            line = br.readLine();
                            assertNotNull(line);
                            assertTrue(line.endsWith(",,DEBUG,�e�X�g"));
                            line = br.readLine();
                            assertNotNull(line);
                            assertTrue(
                                line.endsWith(
                                    ",SVCM_00035,SYSTEM_ERROR," + message
                                )
                            );
                        }catch(Exception e){
                            final StringWriter sw = new StringWriter();
                            e.printStackTrace(new PrintWriter(sw));
                            fail(sw.toString());
                        }catch(junit.framework.AssertionFailedError e){
                            fails.add(e);
                        }
                    }
                }
            );
            thread.setDaemon(true);
            thread.start();
            final Thread currentThread = Thread.currentThread();
            final Thread watcher = new Thread(
                new Runnable(){
                    public void run(){
                        try{
                            Thread.sleep(1000);
                            currentThread.interrupt();
                        }catch(InterruptedException e){
                        }
                    }
                }
            );
            watcher.start();
            try{
                thread.join();
                watcher.interrupt();
            }catch(InterruptedException e){
            }
        }finally{
            ServiceManagerFactory.unloadManager();
            if(def != null){
                def.delete();
            }
            ServiceManagerFactory.DEFAULT_LOGGER.stop();
            System.setOut(out);
            ServiceManagerFactory.DEFAULT_LOGGER.start();
            if(ps != null){
                ps.close();
            }
            pis.close();
            if(fails.size() != 0){
                throw (Error)fails.iterator().next();
            }
        }
    }*/
    
    /**
     * �}�l�[�W��Logger�̃e�X�g�B<p>
     * �����F
     * <ul>
     *   <li>�f�t�H���g���̃T�[�r�X��`�t�@�C��<a href="resources/nimbus-service13.xml">"nimbus-service.xml"</a>���N���X�p�X��ɒu���B</li>
     * </ul>
     * �m�F�F
     * <ul>
     *   <li>ServiceManagerFactory#loadManager()���Ăяo���A�߂�l��true��Ԃ��B</li>
     *   <li>ServiceManagerFactory#checkLoadManagerCompleted()��true��Ԃ��B</li>
     *   <li>ServiceManagerFactory#getLogger()���Ăяo����Logger�I�u�W�F�N�g���擾�ł���B</li>
     *   <li>�擾����Logger�ŁALogger#debug(String)���Ăяo���A�W���o�͂Ƀf�o�b�O���b�Z�[�W���o�͂����B</li>
     *   <li>ServiceManagerFactory#findManager()���Ăяo����ServiceManager�I�u�W�F�N�g���擾�ł���B</li>
     *   <li>�擾����ServiceManager�ŁAServiceManager#getLogger()���Ăяo����Logger�I�u�W�F�N�g���擾�ł���B</li>
     *   <li>�擾����Logger�ŁALogger#write(String)���Ăяo���A�t�@�C���Ɏw�肵�����b�Z�[�WID�̃��b�Z�[�W���o�͂����B</li>
     *   <li>ServiceManager#getServiceObject(String)���Ăяo���ăT�[�r�X��"Service0"�̃T�[�r�X�I�u�W�F�N�g���擾�ł���B</li>
     *   <li>ServiceBase#getLogger()���Ăяo����Logger�I�u�W�F�N�g���擾�ł���B</li>
     *   <li>�擾����Logger�ŁALogger#write(String)���Ăяo���A�t�@�C���Ɏw�肵�����b�Z�[�WID�̃��b�Z�[�W���o�͂����B</li>
     * </ul>
     */
/*    public void testManagerLogger() throws Exception {
        File def = null;
        File messageDef = null;
        PrintStream ps = null;
        final PrintStream out = System.out;
        final PipedInputStream pis = new PipedInputStream();
        final Set fails = new HashSet();
        try{
            def = TestUtility.copyOnClassPath(
                "nimbus-service13.xml",
                "nimbus-service.xml"
            );
            messageDef = TestUtility.copyOnClassPath("Message1.def");
            assertTrue(ServiceManagerFactory.loadManager());
            assertTrue(ServiceManagerFactory.checkLoadManagerCompleted());
            ps = new PrintStream(new PipedOutputStream(pis));
            ServiceManagerFactory.DEFAULT_LOGGER.stop();
            System.setOut(ps);
            ServiceManagerFactory.DEFAULT_LOGGER.start();
            final BufferedReader br = new BufferedReader(
                new InputStreamReader(pis)
            );
            Logger logger = ServiceManagerFactory.getLogger();
            assertNotNull(logger);
            logger.debug("�e�X�g�J�n");
            final ServiceManager manager = ServiceManagerFactory.findManager();
            assertNotNull(manager);
            logger = manager.getLogger();
            assertNotNull(logger);
            logger.write("TEST39999");
            TestServiceBase serviceObj0 = null;
            try{
                serviceObj0 = (TestServiceBase)manager.getServiceObject(
                    "Service0"
                );
            }catch(ServiceNotFoundException e){
                fail(e.getMessage());
            }
            logger = serviceObj0.getLogger();
            assertNotNull(logger);
            logger.write("TEST49999");
            final Thread thread = new Thread(
                new Runnable(){
                    public void run(){
                        try{
                            String line = null;
                            while((line = br.readLine()) != null){
                                if(line.indexOf("�e�X�g�J�n") != -1){
                                    break;
                                }
                            }
                        }catch(Exception e){
                            final StringWriter sw = new StringWriter();
                            e.printStackTrace(new PrintWriter(sw));
                            fail(sw.toString());
                        }catch(junit.framework.AssertionFailedError e){
                            fails.add(e);
                        }
                    }
                }
            );
            thread.setDaemon(true);
            thread.start();
            final Thread currentThread = Thread.currentThread();
            final Thread watcher = new Thread(
                new Runnable(){
                    public void run(){
                        try{
                            Thread.sleep(1000);
                            currentThread.interrupt();
                        }catch(InterruptedException e){
                        }
                    }
                }
            );
            watcher.start();
            try{
                thread.join();
                watcher.interrupt();
            }catch(InterruptedException e){
            }
            MessageRecordFactory msgRecFactory = null;
            try{
                msgRecFactory = (MessageRecordFactory)ServiceManagerFactory
                    .getServiceObject("ApplicationMessage");
            }catch(ServiceNotFoundException e){
                fail(e.getMessage());
            }
            assertNotNull(msgRecFactory);
            BufferedReader br2 = null;
            try{
                br2 = new BufferedReader(
                    new FileReader("temp/ServiceManagerFactoryTest.log")
                );
                String message = msgRecFactory.findMessage("TEST39999");
                assertNotNull(message);
                String line = br2.readLine();
                assertNotNull(line);
                assertTrue(
                    line.endsWith(",TEST39999,APPLICATION_ERROR," + message)
                );
                message = msgRecFactory.findMessage("TEST49999");
                assertNotNull(message);
                line = br2.readLine();
                assertNotNull(line);
                assertTrue(
                    line.endsWith(",TEST49999,APPLICATION_FATAL," + message)
                );
            }finally{
                if(br2 != null){
                    br2.close();
                }
            }
        }finally{
            ServiceManagerFactory.unloadManager();
            if(def != null){
                def.delete();
            }
            if(messageDef != null){
                messageDef.delete();
            }
            ServiceManagerFactory.DEFAULT_LOGGER.stop();
            System.setOut(out);
            ServiceManagerFactory.DEFAULT_LOGGER.start();
            if(ps != null){
                ps.close();
            }
            pis.close();
            if(fails.size() != 0){
                throw (Error)fails.iterator().next();
            }
        }
    }*/
}