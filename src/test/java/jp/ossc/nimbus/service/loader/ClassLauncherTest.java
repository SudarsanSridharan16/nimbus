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
package jp.ossc.nimbus.service.loader;

import junit.framework.TestCase;
import jp.ossc.nimbus.core.*;
import java.lang.reflect.Method;
// �C���|�[�g
//
/**
 *  
 * @author   nakano
 * @version  1.00 �쐬: 2003/11/04 -�@H.Nakano
 */
public class ClassLauncherTest extends TestCase {
	/**
	 * Constructor for ClassLauncherTest.
	 * @param arg0
	 */
	public ClassLauncherTest(String arg0) {
		super(arg0);
		ServiceManagerFactory.loadManager("jp/ossc/nimbus/service/loader/nimbus-service.xml");
	}
	public void testLoadClass() throws Exception {
		ClassLauncher tmp = (ClassLauncher)ServiceManagerFactory.getServiceObject("tstruts","cl1") ;
		Class clazz = tmp.loadClass("GetMessage2") ;
		Object obj = clazz.newInstance();
		Method method = clazz.getMethod("mesPrint",(Class[])null);
		String ret = (String)method.invoke(obj,(Object[])null);
		assertEquals("Before:Jar-File",ret);
	}
	public void testLoadNewInstance() throws Exception {
		ClassLauncher tmp = (ClassLauncher)ServiceManagerFactory.getServiceObject("tstruts","cl2") ;
		Class clazz = tmp.loadClass("ReMessage2") ;
		Object obj = clazz.newInstance();
		Method method = clazz.getMethod("mes",(Class[])null);
		String ret = (String)method.invoke(obj,(Object[])null);
		assertEquals("After:Jar-File",ret);
	}
}