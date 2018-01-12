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
package jp.ossc.nimbus.service.writer.log4j;

import org.apache.log4j.*;

/**
 * Log4J��FileAppender���g����MessageWriter�T�[�r�X�B<p>
 * 
 * @author M.Takata
 */
public class FileAppenderWriterService extends WriterAppenderWriterService
 implements FileAppenderWriterServiceMBean{
    
    private static final long serialVersionUID = -3879652668796216872L;
    
    private boolean isAppend = true;
    private boolean isBufferedIO;
    private int bufferSize = -1;
    private String file;
    
    /**
     * �C���X�^���X�𐶐�����B<p>
     */
    public FileAppenderWriterService(){
        super();
    }
    
    // FileAppenderWriterServiceMBean��JavaDoc
    public void setAppend(boolean append){
        isAppend = append;
    }
    
    // FileAppenderWriterServiceMBean��JavaDoc
    public boolean isAppend(){
        return isAppend;
    }
    
    // FileAppenderWriterServiceMBean��JavaDoc
    public void setBufferedIO(boolean bufferedIO){
        isBufferedIO = bufferedIO;
    }
    
    // FileAppenderWriterServiceMBean��JavaDoc
    public boolean isBufferedIO(){
        return isBufferedIO;
    }
    
    // FileAppenderWriterServiceMBean��JavaDoc
    public void setBufferSize(int bufferSize){
        this.bufferSize = bufferSize;
    }
    
    // FileAppenderWriterServiceMBean��JavaDoc
    public int getBufferSize(){
        return bufferSize;
    }
    
    // FileAppenderWriterServiceMBean��JavaDoc
    public void setFile(String file){
        this.file = file;
    }
    
    // FileAppenderWriterServiceMBean��JavaDoc
    public String getFile(){
        return file;
    }
    
    /**
     * Logger�C���X�^���X�𐶐�����B<p>
     */
    protected Logger createLogger(){
        return Logger.getLogger(
            getServiceManagerName() + '#' + getServiceName()
        );
    }
    
    /**
     * FileAppender�C���X�^���X�𐶐�����B<p>
     *
     * @exception Exception FileAppender�̐����Ɏ��s�����ꍇ
     */
    protected WriterAppender createWriterAppender() throws Exception{
        return new FileAppender();
    }
    
    /**
     * FileAppender������������B<p>
     *
     * @exception Exception FileAppender�̏������Ɏ��s�����ꍇ
     */
    protected void initWriterAppender(WriterAppender appender) throws Exception{
        super.initWriterAppender(appender);
        final FileAppender fileAppender = (FileAppender)appender;
        fileAppender.setFile(file);
        fileAppender.setAppend(isAppend);
        fileAppender.setBufferedIO(isBufferedIO);
        if(bufferSize >= 0){
            fileAppender.setBufferSize(bufferSize);
        }
    }
}
