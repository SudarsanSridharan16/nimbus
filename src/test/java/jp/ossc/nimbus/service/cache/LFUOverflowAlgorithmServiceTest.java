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
package jp.ossc.nimbus.service.cache;

import junit.framework.*;

/**
 * LFUあふれアルゴリズムサービステスト。<p>
 *
 * @author M.Takata
 */
public class LFUOverflowAlgorithmServiceTest extends TestCase{
    
    public LFUOverflowAlgorithmServiceTest(String theName){
        super(theName);
    }
    
    public static void main(String[] theArgs){
        junit.swingui.TestRunner.main(
            new String[]{LFUOverflowAlgorithmServiceTest.class.getName()}
        );
    }
    
    public static Test suite(){
        return new TestSuite(LFUOverflowAlgorithmServiceTest.class);
    }
    
    public void testDefault1() throws Exception{
        final LFUOverflowAlgorithmService algorithm
             = new LFUOverflowAlgorithmService();
        algorithm.create();
        algorithm.start();
        for(int i = 0; i < 10; i++){
            final String str = "TEST" + i;
            final CachedReference ref = new DefaultCachedReference(str);
            algorithm.add(ref);
            for(int j = 0; j < i; j++){
                ref.get();
            }
        }
        for(int i = 0; i < 10; i++){
            final CachedReference overflow = algorithm.overflow();
            assertNotNull(overflow);
            assertEquals("TEST" + i, overflow.get(this));
        }
        algorithm.stop();
        algorithm.destroy();
    }
    
    public void testDefault2() throws Exception{
        final LFUOverflowAlgorithmService algorithm
             = new LFUOverflowAlgorithmService();
        algorithm.create();
        algorithm.start();
        for(int i = 0; i < 10; i++){
            final String str = "TEST" + i;
            final CachedReference ref = new DefaultCachedReference(str);
            algorithm.add(ref);
            for(int j = 0; j < 9 - i; j++){
                ref.get();
            }
        }
        for(int i = 10; --i >= 0;){
            final CachedReference overflow = algorithm.overflow();
            assertNotNull(overflow);
            assertEquals("TEST" + i, overflow.get(this));
        }
        algorithm.stop();
        algorithm.destroy();
    }
    
    public void testReset() throws Exception{
        final LFUOverflowAlgorithmService algorithm
             = new LFUOverflowAlgorithmService();
        algorithm.create();
        algorithm.start();
        for(int i = 0; i < 10; i++){
            final String str = "TEST" + i;
            final CachedReference ref = new DefaultCachedReference(str);
            algorithm.add(ref);
            for(int j = 0; j < i; j++){
                ref.get();
            }
            if(i == 4){
                algorithm.reset();
            }
        }
        for(int i = 0; i < 10; i++){
            final CachedReference overflow = algorithm.overflow();
            if(i < 5){
                assertNotNull(overflow);
                assertEquals("TEST" + (i + 5), overflow.get(this));
            }else{
                assertNull(overflow);
            }
        }
        algorithm.stop();
        algorithm.destroy();
    }
}
