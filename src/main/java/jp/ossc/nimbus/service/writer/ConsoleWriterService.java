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
package jp.ossc.nimbus.service.writer;

import jp.ossc.nimbus.core.ServiceBase;
import java.io.*;

/**
 * �W���o�́E�W���G���[�o�͂ւ̏o�͂��s��{@link MessageWriter}�T�[�r�X�B<p>
 * �ȉ��ɁA�T�[�r�X��`��������B<br>
 * <pre>
 * &lt;?xml version="1.0" encoding="Shift_JIS"?&gt;
 * 
 * &lt;server&gt;
 *     
 *     &lt;manager name="Sample"&gt;
 *         
 *         &lt;service name="ConsoleWriter"
 *                  code="jp.ossc.nimbus.service.writer.ConsoleWriterService"&gt;
 *             &lt;attribute name="Output"&gt;STDOUT&lt;/attribute&gt;
 *         &lt;/service&gt;
 *         
 *     &lt;/manager&gt;
 *     
 * &lt;/server&gt;
 * </pre>
 *
 * @author y-tokuda
 */
public class ConsoleWriterService extends ServiceBase
 implements ConsoleWriterServiceMBean, MessageWriter {
    
    private static final long serialVersionUID = 4209363683339544273L;
    
    /** �o�͎�� */
    private String mOutputKind = OUTPUT_STDOUT;
    
    /** �o�̓X�g���[�� */
    private PrintStream mOutputStream;
    
    // ConsoleWriterServiceMBean ��JavaDoc
    public void setOutput(String kind){
        if(OUTPUT_STDOUT.equals(kind)
            || OUTPUT_STDERR.equals(kind)){
            mOutputKind = kind;
        }
        else{
            throw new IllegalArgumentException(kind);
        }
    }
    
    // ConsoleWriterServiceMBean ��JavaDoc
    public String getOutput(){
        return mOutputKind;
    }
    
    /**
     * �T�[�r�X�̊J�n�������s���B<p>
     */
    public void startService(){
        if(mOutputKind.equals(OUTPUT_STDERR)){
            mOutputStream = System.err;                
        }
        else{
            mOutputStream = System.out;
        }
    }
    
    /**
     * �T�[�r�X�̒�~�������s���B<p>
     */
    public void stopService(){
        mOutputStream = null;
    }
    
    // MessageWriter ��JavaDoc
    public void write(WritableRecord rec){
        mOutputStream.println(rec.toString());
    }
}