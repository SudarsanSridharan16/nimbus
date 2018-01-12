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
package jp.ossc.nimbus.service.keepalive.smtp;

import java.net.*;
import java.io.*;
import java.util.*;

import jp.ossc.nimbus.core.*;
import jp.ossc.nimbus.daemon.*;
import jp.ossc.nimbus.service.keepalive.KeepAliveListener;

//
/**
 * SMTP�T�[�o�`�F�b�J�[�T�[�r�X�B<p>
 *
 * @author H.Nakano
 * @version  1.00 �쐬: 2003/10/09 - H.Nakano
 */
public class SmtpCheckerService extends ServiceBase
 implements SmtpCheckerServiceMBean, DaemonRunnable{
    
    private static final long serialVersionUID = -1543463563116884001L;
    
    protected static final String C_HELLOW = "HELO localhost\r\n" ; //$NON-NLS-1$
    protected static final String C_EOF_KEY = "WOUGN0600002002" ; //$NON-NLS-1$
    protected static final String C_WRONG_SIGN = "2" ; //$NON-NLS-1$
    protected static final String C_ERRSTATE_KEY = "WOUGN0600002003" ; //$NON-NLS-1$
    protected static final String C_NORMALSTATE_KEY = "WOUGN0600002004" ; //$NON-NLS-1$
    protected static final String C_QUITE = "QUIT\r\n" ; //$NON-NLS-1$
    protected static final String C_TIMEOUT_KEY = "WOUGN0600002005" ; //$NON-NLS-1$
    protected static final String C_PROTOCOL_ERROR_KEY = "WOUGN0600002006" ; //$NON-NLS-1$
    protected static final String C_IOERROR_KEY = "WOUGN0600002007" ; //$NON-NLS-1$
    
    protected String mHostName;
    protected InetAddress mIp;
    protected volatile int mPort = 0;
    protected volatile int mConnectionTimeOut = 0;
    protected volatile int mTimeOut = 1000;
    
    protected String eofLogMessageId = C_EOF_KEY;
    protected String errorStateLogMessageId = C_ERRSTATE_KEY;
    protected String normalStateLogMessageId = C_NORMALSTATE_KEY;
    protected String timeoutLogMessageId = C_TIMEOUT_KEY;
    protected String protocolErrorLogMessageId = C_PROTOCOL_ERROR_KEY;
    protected String ioErrorLogMessageId = C_IOERROR_KEY;
    
    protected boolean isOutputEOFLogMessage;
    protected boolean isOutputErrorStateLogMessage;
    protected boolean isOutputNormalStateLogMessage;
    protected boolean isOutputTimeoutLogMessage;
    protected boolean isOutputProtocolErrorLogMessage;
    protected boolean isOutputIOErrorLogMessage;
    protected List keepAliveListeners;
    
    /**
     * JNDI�T�[�o�̐����m�F�����邩�ǂ����̃t���O�B<p>
     */
    protected boolean isAliveCheckSMTPServer;
    
    /**
     * SMTP�T�[�o�̐������Ă��邩�ǂ����̃t���O�B<p>
     */
    protected boolean isAliveSMTPServer;
    
    /**
     * SMTP�T�[�o�̐����m�F������Ԋu[msec]�B<p>
     */
    protected long aliveCheckSMTPServerInterval = 60000;
    
    /**
     * {@link Daemon}�I�u�W�F�N�g�B<p>
     */
    protected Daemon daemon;
    
    protected boolean isLoggingDeadSMTPServer = true;
    
    protected boolean isLoggingRecoverSMTPServer = true;
    
    protected String deadSMTPServerLogMessageId = SMTP_SERVER_DEAD_MSG_ID;
    
    protected String recoverSMTPServerLogMessageId = SMTP_SERVER_RECOVER_MSG_ID;
    
    // SmtpChekerServiceMBean��JavaDoc
    public void setHostName(String hostName) throws UnknownHostException{
        mHostName = hostName;
        mIp = InetAddress.getByName(hostName);
    }
    
    // SmtpChekerServiceMBean��JavaDoc
    public String getHostName(){
        return mHostName;
    }
    
    // SmtpChekerServiceMBean��JavaDoc
    public void setPort(int port){
        mPort = port;
    }
    
    // SmtpChekerServiceMBean��JavaDoc
    public int getPort(){
        return mPort;
    }
    
    // SmtpChekerServiceMBean��JavaDoc
    public void setConnectionTimeoutMillis(int milisec){
        mConnectionTimeOut = milisec;
    }
    
    // SmtpChekerServiceMBean��JavaDoc
    public int getConnectionTimeoutMillis(){
        return mConnectionTimeOut;
    }
    
    // SmtpChekerServiceMBean��JavaDoc
    public void setTimeoutMillis(int milisec){
        mTimeOut = milisec;
    }
    
    // SmtpChekerServiceMBean��JavaDoc
    public int getTimeoutMillis(){
        return mTimeOut;
    }
    
    // SmtpChekerServiceMBean��JavaDoc
    public void setEOFLogMessageId(String id){
        eofLogMessageId = id;
    }
    
    // SmtpChekerServiceMBean��JavaDoc
    public String getEOFLogMessageId(){
        return eofLogMessageId;
    }
    
    // SmtpChekerServiceMBean��JavaDoc
    public void setErrorStateLogMessageId(String id){
        errorStateLogMessageId = id;
    }
    
    // SmtpChekerServiceMBean��JavaDoc
    public String getErrorStateLogMessageId(){
        return errorStateLogMessageId;
    }
    
    // SmtpChekerServiceMBean��JavaDoc
    public void setNormalStateLogMessageId(String id){
        normalStateLogMessageId = id;
    }
    
    // SmtpChekerServiceMBean��JavaDoc
    public String getNormalStateLogMessageId(){
        return normalStateLogMessageId;
    }
    
    // SmtpChekerServiceMBean��JavaDoc
    public void setTimeoutLogMessageId(String id){
        timeoutLogMessageId = id;
    }
    
    // SmtpChekerServiceMBean��JavaDoc
    public String getTimeoutLogMessageId(){
        return timeoutLogMessageId;
    }
    
    // SmtpChekerServiceMBean��JavaDoc
    public void setProtocolErrorLogMessageId(String id){
        protocolErrorLogMessageId = id;
    }
    
    // SmtpChekerServiceMBean��JavaDoc
    public String getProtocolErrorLogMessageId(){
        return protocolErrorLogMessageId;
    }
    
    // SmtpChekerServiceMBean��JavaDoc
    public void setIOErrorLogMessageId(String id){
        ioErrorLogMessageId = id;
    }
    
    // SmtpChekerServiceMBean��JavaDoc
    public String getIOErrorLogMessageId(){
        return ioErrorLogMessageId;
    }
    
    // SmtpChekerServiceMBean��JavaDoc
    public void setOutputEOFLogMessage(boolean isOutput){
        isOutputEOFLogMessage = isOutput;
    }
    
    // SmtpChekerServiceMBean��JavaDoc
    public boolean isOutputEOFLogMessage(){
        return isOutputEOFLogMessage;
    }
    
    // SmtpChekerServiceMBean��JavaDoc
    public void setOutputErrorStateLogMessage(boolean isOutput){
        isOutputErrorStateLogMessage = isOutput;
    }
    
    // SmtpChekerServiceMBean��JavaDoc
    public boolean isOutputErrorStateLogMessage(){
        return isOutputErrorStateLogMessage;
    }
    
    // SmtpChekerServiceMBean��JavaDoc
    public void setOutputNormalStateLogMessage(boolean isOutput){
        isOutputNormalStateLogMessage = isOutput;
    }
    
    // SmtpChekerServiceMBean��JavaDoc
    public boolean isOutputNormalStateLogMessage(){
        return isOutputNormalStateLogMessage;
    }
    
    // SmtpChekerServiceMBean��JavaDoc
    public void setOutputTimeoutLogMessage(boolean isOutput){
        isOutputTimeoutLogMessage = isOutput;
    }
    
    // SmtpChekerServiceMBean��JavaDoc
    public boolean isOutputTimeoutLogMessage(){
        return isOutputTimeoutLogMessage;
    }
    
    // SmtpChekerServiceMBean��JavaDoc
    public void setOutputProtocolErrorLogMessage(boolean isOutput){
        isOutputProtocolErrorLogMessage = isOutput;
    }
    
    // SmtpChekerServiceMBean��JavaDoc
    public boolean isOutputProtocolErrorLogMessage(){
        return isOutputProtocolErrorLogMessage;
    }
    
    // SmtpChekerServiceMBean��JavaDoc
    public void setOutputIOErrorLogMessage(boolean isOutput){
        isOutputIOErrorLogMessage = isOutput;
    }
    
    // SmtpChekerServiceMBean��JavaDoc
    public boolean isOutputIOErrorLogMessage(){
        return isOutputIOErrorLogMessage;
    }
    
    // SmtpChekerServiceMBean��JavaDoc
    public void setLoggingDeadSMTPServer(boolean isOutput){
        isLoggingDeadSMTPServer = isOutput;
    }
    
    // SmtpChekerServiceMBean��JavaDoc
    public boolean isLoggingDeadSMTPServer(){
        return isLoggingDeadSMTPServer;
    }
    
    // SmtpChekerServiceMBean��JavaDoc
    public void setLoggingRecoverSMTPServer(boolean isOutput){
        isLoggingRecoverSMTPServer = isOutput;
    }
    
    // SmtpChekerServiceMBean��JavaDoc
    public boolean isLoggingRecoverSMTPServer(){
        return isLoggingRecoverSMTPServer;
    }
    
    // SmtpChekerServiceMBean��JavaDoc
    public void setDeadSMTPServerLogMessageId(String id){
        deadSMTPServerLogMessageId = id;
    }
    
    // SmtpChekerServiceMBean��JavaDoc
    public String getDeadSMTPServerLogMessageId(){
        return deadSMTPServerLogMessageId;
    }
    
    // SmtpChekerServiceMBean��JavaDoc
    public void setRecoverSMTPServerLogMessageId(String id){
        recoverSMTPServerLogMessageId = id;
    }
    
    // SmtpChekerServiceMBean��JavaDoc
    public String getRecoverSMTPServerLogMessageId(){
        return recoverSMTPServerLogMessageId;
    }
    
    // SmtpChekerServiceMBean��JavaDoc
    public void setAliveCheckSMTPServer(boolean isCheck){
        isAliveCheckSMTPServer = isCheck;
        if(isCheck && getState() == STARTED && !daemon.isRunning()){
            daemon.start();
        }
    }
    
    // SmtpChekerServiceMBean��JavaDoc
    public boolean isAliveCheckSMTPServer(){
        return isAliveCheckSMTPServer;
    }
    
    // SmtpChekerServiceMBean��JavaDoc
    public void setAliveCheckSMTPServerInterval(long interval){
        aliveCheckSMTPServerInterval = interval;
    }
    
    // SmtpChekerServiceMBean��JavaDoc
    public long getAliveCheckSMTPServerInterval(){
        return aliveCheckSMTPServerInterval;
    }
    
    // SmtpChekerServiceMBean��JavaDoc
    public boolean isAliveSMTPServer(){
        if(getState() != STARTED){
            return false;
        }else if(isAliveCheckSMTPServer){
            return isAliveSMTPServer;
        }else{
            return isAlive();
        }
    }
    
    // SmtpKeepAliveChecker��JavaDoc
    public String getHostIp(){
        return mIp == null ? null : mIp.getHostAddress();
    }
    
    // SmtpKeepAliveChecker��JavaDoc
    public int getHostPort(){
        return mPort;
    }
    
    public void createService() throws Exception{
        keepAliveListeners = new ArrayList();
        daemon = new Daemon(this);
        daemon.setName("Nimbus SMTPCheckDaemon " + getServiceNameObject());
    }
    
    public void startService() throws Exception{
        
        isAliveSMTPServer = isAlive();
        
        if(isAliveCheckSMTPServer){
            // �f�[�����N��
            daemon.start();
        }
    }
    
    public void stopService() throws Exception{
        
        // �f�[������~
        daemon.stop();
    }
    
    public void destroyService() throws Exception{
        keepAliveListeners = null;
    }
    
    // KeepAliveChecker��JavaDoc
    public boolean isAlive(){
        return isAliveInternal() == null ? true : false;
    }
    protected Object isAliveInternal(){
        Object ret = null;
        Socket sock = null;
        try{
            final int len = 1024;
            // �\�P�b�g�𐶐����ēǂݏ����̃X�g���[�����I�[�v��
            sock = new Socket();
            sock.connect(new InetSocketAddress(mIp, mPort), mConnectionTimeOut);
            BufferedInputStream in = new BufferedInputStream(
                sock.getInputStream(),
                len
            );
            BufferedOutputStream out = new BufferedOutputStream(
                sock.getOutputStream(),
                len
            );
            
            // HELLO���M
            out.write(C_HELLOW.getBytes(), 0, C_HELLOW.getBytes().length);
            out.flush();
            
            // �������^�C���A�E�g�҂��ɂ���B
            sock.setSoTimeout(mTimeOut);
            
            // ������ǂ�
            byte[] resBuf = new byte[len];
            int readLen = in.read(resBuf, 0, len);
            
            if(readLen == -1){    // ��~��
                if(isOutputEOFLogMessage){
                    getLogger().write(eofLogMessageId, getSMTPServerInfo());
                }
                ret = "Response reading detect EOF.";
            }else {                // �ғ���
                String retCode = new String(resBuf, 0, readLen);
                if(!retCode.startsWith(C_WRONG_SIGN)) {    // ���q����
                    if(isOutputErrorStateLogMessage){
                        String[] wd = new String[2];
                        wd[0] = getSMTPServerInfo();
                        wd[1] = retCode;
                        getLogger().write(errorStateLogMessageId, wd);
                    }
                    ret = "Return code is : " + retCode;
                }else{
                    if(isOutputNormalStateLogMessage){
                        getLogger().write(normalStateLogMessageId, getSMTPServerInfo());
                    }
                }
                
                // �\�P�b�g�ؒf
                out.write(C_QUITE.getBytes(), 0, C_QUITE.getBytes().length);
                out.flush();
            }
        }catch(InterruptedIOException e){ // �^�C���A�E�g
            if(isOutputTimeoutLogMessage){
                getLogger().write(timeoutLogMessageId, getSMTPServerInfo());
            }
            ret = e;
        }catch(SocketException e){ // �v���g�R���G���[
            if(isOutputProtocolErrorLogMessage){
                getLogger().write(protocolErrorLogMessageId, getSMTPServerInfo(), e);
            }
            ret = e;
        }catch(IOException e){ // �\�P�b�g�ǂݏ����G���[
            if(isOutputIOErrorLogMessage){
                getLogger().write(ioErrorLogMessageId, getSMTPServerInfo(), e);
            }
            ret = e;
        }finally{
            try{
                if(sock != null){
                    sock.close();
                }
            }catch(IOException ex){
                if(isOutputIOErrorLogMessage){
                    getLogger().write(ioErrorLogMessageId, getSMTPServerInfo(), ex);
                }
                ret = ex;
            }
        }
        // �`�F�b�N���ʍX�V
        return ret;
    }
    
    // KeepAliveChecker��JavaDoc
    public void addKeepAliveListener(KeepAliveListener listener){
        synchronized(keepAliveListeners){
            keepAliveListeners.add(listener);
        }
    }
    
    // KeepAliveChecker��JavaDoc
    public void removeKeepAliveListener(KeepAliveListener listener){
        synchronized(keepAliveListeners){
            keepAliveListeners.remove(listener);
        }
    }
    
    // KeepAliveChecker��JavaDoc
    public void clearKeepAliveListener(){
        synchronized(keepAliveListeners){
            keepAliveListeners.clear();
        }
    }
    
    /**
     * �f�[�������J�n�������ɌĂяo�����B<p>
     * 
     * @return ���true��Ԃ�
     */
    public boolean onStart(){
        return true;
    }
    
    /**
     * �f�[��������~�������ɌĂяo�����B<p>
     * 
     * @return ���true��Ԃ�
     */
    public boolean onStop(){
        return true;
    }
    
    /**
     * �f�[���������f�������ɌĂяo�����B<p>
     * 
     * @return ���true��Ԃ�
     */
    public boolean onSuspend(){
        return true;
    }
    
    /**
     * �f�[�������ĊJ�������ɌĂяo�����B<p>
     * 
     * @return ���true��Ԃ�
     */
    public boolean onResume(){
        return true;
    }
    
    /**
     * ��莞��sleep������AisAliveInternal()�����s���āA���̌��ʂ�Ԃ��B<p>
     * 
     * @param ctrl DaemonControl�I�u�W�F�N�g
     * @return isAlive()�̌���
     */
    public Object provide(DaemonControl ctrl){
        try{
            ctrl.sleep(aliveCheckSMTPServerInterval, true);
        }catch(InterruptedException e){
            Thread.interrupted();
        }
        return isAliveInternal();
    }
    
    /**
     * ����lookupedObj�œn���ꂽ�I�u�W�F�N�g�������B<p>
     * isAliveSMTPServer��true�̏�ԂŁAlookupedObj != null �̏ꍇ�ASMTP�T�[�o�����񂾎|�̃G���[���O���o�͂���B<br>
     * isAliveSMTPServer��false�̏�ԂŁAlookupedObj == null �̏ꍇ�ASMTP�T�[�o�����A�����|�̒ʒm���O���o�͂���B<br>
     *
     * @param lookupedObj isAlive()�̌���
     * @param ctrl DaemonControl�I�u�W�F�N�g
     */
    public void consume(Object lookupedObj, DaemonControl ctrl){
        if(!isAliveCheckSMTPServer){
            return;
        }
        if(isAliveSMTPServer){
            if(lookupedObj != null){
                isAliveSMTPServer = false;
                synchronized(keepAliveListeners){
                    final Iterator itr = keepAliveListeners.iterator();
                    while(itr.hasNext()){
                        final KeepAliveListener keepAliveListener
                             = (KeepAliveListener)itr.next();
                        keepAliveListener.onDead(this);
                    }
                }
                // �G���[���O�o��
                if(isLoggingDeadSMTPServer){
                    if(lookupedObj instanceof Throwable){
                        getLogger().write(
                            deadSMTPServerLogMessageId,
                            new Object[]{
                                getSMTPServerInfo(),
                                ((Throwable)lookupedObj).getMessage()
                            },
                            (Throwable)lookupedObj
                        );
                    }else{
                        getLogger().write(
                            deadSMTPServerLogMessageId,
                            new Object[]{
                                getSMTPServerInfo(),
                                lookupedObj
                            }
                        );
                    }
                }
            }
        }else{
            if(lookupedObj == null){
                isAliveSMTPServer = true;
                synchronized(keepAliveListeners){
                    final Iterator itr = keepAliveListeners.iterator();
                    while(itr.hasNext()){
                        final KeepAliveListener keepAliveListener
                             = (KeepAliveListener)itr.next();
                        keepAliveListener.onRecover(this);
                    }
                }
                if(isLoggingRecoverSMTPServer){
                    // �ʒm���O�o��
                    getLogger().write(
                        recoverSMTPServerLogMessageId,
                        getSMTPServerInfo()
                    );
                }
            }
        }
    }
    
    protected String getSMTPServerInfo(){
        return getHostName() + ':' + getHostPort();
    }
    
    /**
     * �������Ȃ��B<p>
     */
    public void garbage(){
    }

    public Object getHostInfo() {
        return mIp;
    }
}
