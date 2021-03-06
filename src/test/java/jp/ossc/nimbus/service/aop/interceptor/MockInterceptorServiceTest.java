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
package jp.ossc.nimbus.service.aop.interceptor;

import java.lang.reflect.Method;

import junit.framework.TestCase;

import jp.ossc.nimbus.core.ServiceManagerFactory;
import jp.ossc.nimbus.core.ServiceName;
import jp.ossc.nimbus.service.aop.Interceptor;
import jp.ossc.nimbus.service.aop.InterceptorChain;
import jp.ossc.nimbus.service.aop.InvocationContext;
import jp.ossc.nimbus.service.aop.MethodInvocationContext;
import jp.ossc.nimbus.service.aop.DefaultMethodInvocationContext;
import jp.ossc.nimbus.service.aop.DefaultInterceptorChain;
import jp.ossc.nimbus.service.aop.DefaultInterceptorChainList;
import jp.ossc.nimbus.service.aop.MockFactory;
import jp.ossc.nimbus.service.aop.invoker.MethodReflectionCallInvokerService;

public class MockInterceptorServiceTest extends TestCase{
    
    public MockInterceptorServiceTest(String arg0) {
        super(arg0);
    }
    
    public static void main(String[] args) {
        junit.textui.TestRunner.run(MockInterceptorServiceTest.class);
    }
    
    public void test1() throws Throwable{
        ServiceManagerFactory.registerManager("Test");
        MockInterceptorService interceptor = new MockInterceptorService();
        ServiceManagerFactory.registerService(
            "Test",
            "MockInterceptor",
            interceptor
        );
        final Method targetMethod = String.class.getMethod("toString", (Class[])null);
        MockFactory mockFactory = new MockFactory(){
            public Object createMock(InvocationContext context){
                assertNotNull(context);
                assertTrue(context instanceof MethodInvocationContext);
                MethodInvocationContext ctx = (MethodInvocationContext)context;
                assertEquals("Real", ctx.getTargetObject());
                assertEquals(
                    targetMethod,
                    ctx.getTargetMethod()
                );
                return "Mock";
            }
        };
        ServiceManagerFactory.registerService(
            "Test",
            "MockFactory",
            mockFactory
        );
        try{
            ServiceManagerFactory.findManager("Test").createAllService();
            interceptor.setMockFactoryServiceName(
                new ServiceName("Test", "MockFactory")
            );
            ServiceManagerFactory.findManager("Test").startAllService();
            final InterceptorChain chain = new DefaultInterceptorChain(
                new DefaultInterceptorChainList(
                    new Interceptor[]{
                        interceptor
                    }
                ),
                new MethodReflectionCallInvokerService()
            );
            assertEquals(
                "Mock",
                chain.invokeNext(
                    new DefaultMethodInvocationContext(
                        "Real",
                        targetMethod,
                        null
                    )
                )
            );
            
        }finally{
            ServiceManagerFactory.findManager("Test").stopAllService();
            ServiceManagerFactory.findManager("Test").destroyAllService();
            ServiceManagerFactory.unregisterManager("Test");
        }
    }
    
    public void test2() throws Throwable{
        ServiceManagerFactory.registerManager("Test");
        MockInterceptorService interceptor = new MockInterceptorService();
        ServiceManagerFactory.registerService(
            "Test",
            "MockInterceptor",
            interceptor
        );
        final Method targetMethod = String.class.getMethod("toString", (Class[])null);
        MockFactory mockFactory = new MockFactory(){
            public Object createMock(InvocationContext context){
                assertNotNull(context);
                assertTrue(context instanceof MethodInvocationContext);
                MethodInvocationContext ctx = (MethodInvocationContext)context;
                assertEquals("Real", ctx.getTargetObject());
                assertEquals(
                    targetMethod,
                    ctx.getTargetMethod()
                );
                return "Mock";
            }
        };
        try{
            ServiceManagerFactory.findManager("Test").createAllService();
            interceptor.setMockFactory(mockFactory);
            ServiceManagerFactory.findManager("Test").startAllService();
            final InterceptorChain chain = new DefaultInterceptorChain(
                new DefaultInterceptorChainList(
                    new Interceptor[]{
                        interceptor
                    }
                ),
                new MethodReflectionCallInvokerService()
            );
            assertEquals(
                "Mock",
                chain.invokeNext(
                    new DefaultMethodInvocationContext(
                        "Real",
                        targetMethod,
                        null
                    )
                )
            );
            
        }finally{
            ServiceManagerFactory.findManager("Test").stopAllService();
            ServiceManagerFactory.findManager("Test").destroyAllService();
            ServiceManagerFactory.unregisterManager("Test");
        }
    }
    
    public void test3() throws Throwable{
        ServiceManagerFactory.registerManager("Test");
        MockInterceptorService interceptor = new MockInterceptorService();
        ServiceManagerFactory.registerService(
            "Test",
            "MockInterceptor",
            interceptor
        );
        final Method targetMethod = String.class.getMethod("toString", (Class[])null);
        ServiceManagerFactory.registerService(
            "Test",
            "Mock",
            "Mock"
        );
        try{
            ServiceManagerFactory.findManager("Test").createAllService();
            interceptor.setMockServiceName(
                new ServiceName("Test", "Mock")
            );
            ServiceManagerFactory.findManager("Test").startAllService();
            final InterceptorChain chain = new DefaultInterceptorChain(
                new DefaultInterceptorChainList(
                    new Interceptor[]{
                        interceptor
                    }
                ),
                new MethodReflectionCallInvokerService()
            );
            assertEquals(
                "Mock",
                chain.invokeNext(
                    new DefaultMethodInvocationContext(
                        "Real",
                        targetMethod,
                        null
                    )
                )
            );
            
        }finally{
            ServiceManagerFactory.findManager("Test").stopAllService();
            ServiceManagerFactory.findManager("Test").destroyAllService();
            ServiceManagerFactory.unregisterManager("Test");
        }
    }
    
    public void test4() throws Throwable{
        ServiceManagerFactory.registerManager("Test");
        MockInterceptorService interceptor = new MockInterceptorService();
        ServiceManagerFactory.registerService(
            "Test",
            "MockInterceptor",
            interceptor
        );
        final Method targetMethod = String.class.getMethod("toString", (Class[])null);
        try{
            ServiceManagerFactory.findManager("Test").createAllService();
            interceptor.setMock("Mock");
            ServiceManagerFactory.findManager("Test").startAllService();
            final InterceptorChain chain = new DefaultInterceptorChain(
                new DefaultInterceptorChainList(
                    new Interceptor[]{
                        interceptor
                    }
                ),
                new MethodReflectionCallInvokerService()
            );
            assertEquals(
                "Mock",
                chain.invokeNext(
                    new DefaultMethodInvocationContext(
                        "Real",
                        targetMethod,
                        null
                    )
                )
            );
            
        }finally{
            ServiceManagerFactory.findManager("Test").stopAllService();
            ServiceManagerFactory.findManager("Test").destroyAllService();
            ServiceManagerFactory.unregisterManager("Test");
        }
    }
}
