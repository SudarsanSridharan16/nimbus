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
import org.xml.sax.*;

/**
 * Nimbus�pEntityResolver�B<p>
 * �O���G���e�B�e�B�̌��J���ʎq"-//Nimbus//DTD Nimbus 1.0//JA"�ɑ΂��āA<a href="nimbus-service_1_0.dtd">"jp/ossc/nimbus/core/nimbus-service_1_0.dtd"</a>���}�b�s���O����EntityResolver�ł���B
 *
 * @author M.Takata
 */
public class NimbusEntityResolver implements EntityResolver{
    
    private static Map dtds = new Hashtable();
    
    static{
        registerDTD(
            "-//Nimbus//DTD Nimbus 1.0//JA",
            "jp/ossc/nimbus/core/nimbus-service_1_0.dtd"
        );
    }
    
    /**
     * �w�肵���O���G���e�B�e�B�̌��J���ʎq�ɑ΂��āADTD�̃��\�[�X����o�^����B<p>
     *
     * @param publicId �O���G���e�B�e�B�̌��J���ʎq
     * @param resource DTD�̃��\�[�X��
     */
    public static void registerDTD(String publicId, String resource){
        dtds.put(publicId, resource);
    }
    
    /**
     * �w�肵���O���G���e�B�e�B�̌��J���ʎq�̓o�^����������B<p>
     *
     * @param publicId �O���G���e�B�e�B�̌��J���ʎq
     */
    public static void unregisterDTD(String publicId){
        dtds.remove(publicId);
    }
    
    /**
     * �w�肵���O���G���e�B�e�B�̌��J���ʎq���o�^����Ă��邩���ׂ�B<p>
     *
     * @param publicId �O���G���e�B�e�B�̌��J���ʎq
     * @return �o�^����Ă���ꍇ�Atrue
     */
    public static boolean isRegisteredDTD(String publicId){
        return dtds.containsKey(publicId);
    }
    
    /**
     * �A�v���P�[�V�������O���G���e�B�e�B�������ł���悤�ɂ���B<p>
     * �w�肳�ꂽ���J���ʎq��{@link #registerDTD(String, String)}�œo�^���ꂽ���J���ʎq�ł������ꍇ�́A�o�^���ꂽ���\�[�X���ŃN���X�p�X�ォ��DTD�t�@�C������������B�����łȂ��ꍇ�́A�V�X�e�����ʎq�Ŏw�肳�ꂽURL��DTD�t�@�C������������B<br>
     * 
     * @param publicId �Q�Ƃ����O���G���e�B�e�B�̌��J���ʎq�B�񋟂���Ȃ������ꍇ�� null
     * @param systemId �Q�Ƃ����O���G���e�B�e�B�̃V�X�e�����ʎq
     */
    public InputSource resolveEntity(String publicId, String systemId){
        if(publicId == null || !dtds.containsKey(publicId)){
            return resolveEntity(systemId);
        }
        final String dtd = (String)dtds.get(publicId);
        if(dtd == null){
            return resolveEntity(systemId);
        }
        final ClassLoader loader
             = Thread.currentThread().getContextClassLoader();
        final InputStream dtdStream = loader.getResourceAsStream(dtd);
        if(dtdStream == null){
            return resolveEntity(systemId);
        }
        final InputSource inputSource = new InputSource(dtdStream);
        return inputSource;
    }
    
    private InputSource resolveEntity(String systemId){
        if(systemId == null){
            return null;
        }
        try{
            final URL url = new URL(systemId);
            final InputStream dtdStream = url.openStream();
            final InputSource inputSource = new InputSource(dtdStream);
            return inputSource;
        }catch(MalformedURLException e){
            // ��������
        }catch(IOException e){
            // ��������
        }
        return null;
   }
}
