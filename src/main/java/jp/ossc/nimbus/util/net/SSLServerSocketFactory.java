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
package jp.ossc.nimbus.util.net;

import java.util.*;
import java.io.File;
import java.io.InputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.UndeclaredThrowableException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.security.KeyStore;
import java.security.SecureRandom;
import java.security.Principal;
import java.security.PrivateKey;
import javax.net.ssl.SSLServerSocket;
import javax.net.ssl.SSLContext;
import javax.net.ssl.KeyManager;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509KeyManager;
import java.security.cert.X509Certificate;

import jp.ossc.nimbus.beans.*;

/**
 * SSL�T�[�o�\�P�b�g�t�@�N�g���B<p>
 * java.security.KeyStore�̌��Əؖ������g����SSL�ʐM���s��SSLServerSocket�𐶐�����t�@�N�g���B<br>
 * ���̃t�@�N�g�����琶�������T�[�o�\�P�b�g�́A{@link #setServerSocketProperty(String, Object)}�ŁA�\�ߐݒ肳�ꂽ�v���p�e�B���ݒ肳���B<br>
 *
 * @author M.Takata
 */
public class SSLServerSocketFactory extends javax.net.ssl.SSLServerSocketFactory{
    
    /**
     * �g�p����Z�L���A�\�P�b�g�v���g�R���̃f�t�H���g�l�B<p>
     */
    public static final String DEFAULT_PROTOCOL = "TLS";
    
    /**
     * �L�[�X�g�A�`���̃f�t�H���g�l�B<p>
     */
    public static final String DEFAULT_KEYSTORE_TYPE = "JKS";
    
    /**
     * javax.net.ssl.KeyManagerFactory�Ɏw�肷��A���S���Y���̃f�t�H���g�l�B<p>
     */
    public static final String DEFAULT_ALGORITHM = "SunX509";
    
    protected javax.net.ssl.SSLServerSocketFactory serverSocketFactory;
    
    protected Map serverSocketProperties;
    protected Map socketProperties;
    
    protected String protocol = DEFAULT_PROTOCOL;
    
    protected String keyAlias;
    protected String keyStoreType = DEFAULT_KEYSTORE_TYPE;
    protected String keyStoreAlgorithm = DEFAULT_ALGORITHM;
    protected String keyStoreFile = System.getProperty("user.home") + "/.keystore";
    protected String keyStorePassword = "changeit";
    protected String keyPassword = "";
    
    protected String trustKeyStoreType = DEFAULT_KEYSTORE_TYPE;
    protected String trustKeyStoreAlgorithm = DEFAULT_ALGORITHM;
    protected String trustKeyStoreFile;
    protected String trustKeyStorePassword;
    
    protected boolean initialized = false;
    
    /**
     * �g�p����Z�L���A�\�P�b�g�v���g�R����ݒ肷��B<p>
     * �f�t�H���g�́A{@link #DEFAULT_PROTOCOL}�B<br>
     *
     * @param protocol �Z�L���A�\�P�b�g�v���g�R��
     */
    public void setProtocol(String protocol){
        this.protocol = protocol;
    }
    
    /**
     * �g�p����Z�L���A�\�P�b�g�v���g�R�����擾����B<p>
     *
     * @return �Z�L���A�\�P�b�g�v���g�R��
     */
    public String getProtocol(){
        return protocol;
    }
    
    /**
     * �L�[�X�g�A�`����ݒ肷��B<p>
     * �f�t�H���g�́A{@link #DEFAULT_KEYSTORE_TYPE}�B<br>
     *
     * @param storeType �L�[�X�g�A�`��
     */
    public void setKeyStoreType(String storeType){
        keyStoreType = storeType;
    }
    
    /**
     * �L�[�X�g�A�`�����擾����B<p>
     *
     * @return �L�[�X�g�A�`��
     */
    public String getKeyStoreType(){
        return keyStoreType;
    }
    
    /**
     * javax.net.ssl.KeyManagerFactory�Ɏw�肷��A���S���Y����ݒ肷��B<p>
     * �f�t�H���g�́A{@link #DEFAULT_ALGORITHM}�B<br>
     *
     * @param algorithm �A���S���Y��
     */
    public void setKeyStoreAlgorithm(String algorithm){
        keyStoreAlgorithm = algorithm;
    }
    
    /**
     * javax.net.ssl.KeyManagerFactory�Ɏw�肷��A���S���Y�����擾����B<p>
     *
     * @return �A���S���Y��
     */
    public String getKeyStoreAlgorithm(){
        return keyStoreAlgorithm;
    }
    
    /**
     * �L�[�X�g�A�t�@�C���̃p�X��ݒ肷��B<p>
     * �f�t�H���g�́A���[�U�z�[���f�B���N�g����.keystore�B<br>
     *
     * @param path �L�[�X�g�A�t�@�C���̃p�X
     */
    public void setKeyStoreFile(String path){
        keyStoreFile = path;
    }
    
    /**
     * �L�[�X�g�A�t�@�C���̃p�X���擾����B<p>
     *
     * @return �L�[�X�g�A�t�@�C���̃p�X
     */
    public String getKeyStoreFile(){
        return keyStoreFile;
    }
    
    /**
     * �L�[�X�g�A�̃p�X���[�h��ݒ肷��B<p>
     * �f�t�H���g�́Achangeit�B<br>
     *
     * @param password �L�[�X�g�A�̃p�X���[�h
     */
    public void setKeyStorePassword(String password){
        keyStorePassword = password;
    }
    
    /**
     * �L�[�X�g�A�̃p�X���[�h���擾����B<p>
     *
     * @return �L�[�X�g�A�̃p�X���[�h
     */
    public String getKeyStorePassword(){
        return keyStorePassword;
    }
    
    /**
     * �T�[�o�[���̃Z�L���A�\�P�b�g��F�؂���Ƃ��̔閧���̕ʖ���ݒ肷��B<p>
     * ���̕ʖ����w�肵�Ȃ��ꍇ�́A���J���̃^�C�v����уs�A�ɂ���ĔF�������ؖ������s�ǂ̃��X�g�Ɋ�Â��āA�閧�����I�������B<br>
     *
     * @param alias �閧���̕ʖ�
     */
    public void setKeyAlias(String alias){
        this.keyAlias = alias;
    }
    
    /**
     * �T�[�o�[���̃Z�L���A�\�P�b�g��F�؂���Ƃ��̔閧���̕ʖ����擾����B<p>
     *
     * @return �閧���̕ʖ�
     */
    public String getKeyAlias(){
        return keyAlias;
    }
    
    /**
     * �閧�����L�[�X�g�A����ǂݏo�����Ɏg�p����A�閧���̃p�X���[�h��ݒ肷��B<p>
     *
     * @param password �閧���̃p�X���[�h
     */
    public void setKeyPassword(String password){
        keyPassword = password;
    }
    
    /**
     * �閧�����L�[�X�g�A����ǂݏo�����Ɏg�p����A�閧���̃p�X���[�h���擾����B<p>
     *
     * @return �閧���̃p�X���[�h
     */
    public String getKeyPassword(){
        return keyPassword;
    }
    
    /**
     * �ؖ������s�ǂƊ֘A����M���f�[�^�̃\�[�X�ƂȂ�L�[�X�g�A�̌`����ݒ肷��B<p>
     * �f�t�H���g�́A{@link #DEFAULT_KEYSTORE_TYPE}�B<br>
     *
     * @param storeType �L�[�X�g�A�`��
     */
    public void setTrustKeyStoreType(String storeType){
        trustKeyStoreType = storeType;
    }
    
    /**
     * �ؖ������s�ǂƊ֘A����M���f�[�^�̃\�[�X�ƂȂ�L�[�X�g�A�̌`�����擾����B<p>
     *
     * @return �L�[�X�g�A�`��
     */
    public String getTrustKeyStoreType(){
        return trustKeyStoreType;
    }
    
    /**
     * javax.net.ssl.TrustManagerFactory�Ɏw�肷��A���S���Y����ݒ肷��B<p>
     * �f�t�H���g�́A{@link #DEFAULT_ALGORITHM}�B<br>
     *
     * @param algorithm �A���S���Y��
     */
    public void setTrustKeyStoreAlgorithm(String algorithm){
        trustKeyStoreAlgorithm = algorithm;
    }
    
    /**
     * javax.net.ssl.TrustManagerFactory�Ɏw�肷��A���S���Y�����擾����B<p>
     *
     * @return �A���S���Y��
     */
    public String getTrustKeyStoreAlgorithm(){
        return trustKeyStoreAlgorithm;
    }
    
    /**
     * �ؖ������s�ǂƊ֘A����M���f�[�^�̃\�[�X�ƂȂ�L�[�X�g�A�t�@�C���̃p�X��ݒ肷��B<p>
     * �f�t�H���g�́A�V�X�e���v���p�e�B"javax.net.ssl.trustStore"�B<br>
     *
     * @param path �L�[�X�g�A�t�@�C���̃p�X
     */
    public void setTrustKeyStoreFile(String path){
        trustKeyStoreFile = path;
    }
    
    /**
     * �ؖ������s�ǂƊ֘A����M���f�[�^�̃\�[�X�ƂȂ�L�[�X�g�A�t�@�C���̃p�X���擾����B<p>
     *
     * @return �L�[�X�g�A�t�@�C���̃p�X
     */
    public String getTrustKeyStoreFile(){
        return trustKeyStoreFile;
    }
    
    /**
     * �ؖ������s�ǂƊ֘A����M���f�[�^�̃\�[�X�ƂȂ�L�[�X�g�A�̃p�X���[�h��ݒ肷��B<p>
     * �f�t�H���g�́A�V�X�e���v���p�e�B"javax.net.ssl.trustStorePassword"�B<br>
     *
     * @param password �L�[�X�g�A�̃p�X���[�h
     */
    public void setTrustKeyStorePassword(String password){
        trustKeyStorePassword = password;
    }
    
    /**
     * �ؖ������s�ǂƊ֘A����M���f�[�^�̃\�[�X�ƂȂ�L�[�X�g�A�̃p�X���[�h���擾����B<p>
     *
     * @return �L�[�X�g�A�̃p�X���[�h
     */
    public String getTrustKeyStorePassword(){
        return trustKeyStorePassword;
    }
    
    /**
     * {@link SSLServerSocket}�Ƀv���p�e�B��ݒ肷��B<p>
     *
     * @param props �v���p�e�B�}�b�v
     */
    public void setServerSocketProperties(Map props){
        if(props == null || props.size() == 0){
            if(serverSocketProperties != null){
                serverSocketProperties = null;
            }
            return;
        }
        final Iterator names = props.keySet().iterator();
        while(names.hasNext()){
            String name = (String)names.next();
            setServerSocketProperty(name, props.get(name));
        }
    }
    
    /**
     * {@link SSLServerSocket}�Ƀv���p�e�B��ݒ肷��B<p>
     *
     * @param name �v���p�e�B��
     * @param value �l
     */
    public void setServerSocketProperty(String name, Object value){
        if(serverSocketProperties == null){
            serverSocketProperties = new LinkedHashMap();
        }
        final Property prop = PropertyFactory.createProperty(name);
        serverSocketProperties.put(prop, value);
    }
    
    /**
     * {@link SSLServerSocket}�̃v���p�e�B���擾����B<p>
     *
     * @param name �v���p�e�B��
     * @return �l
     */
    public Object getServerSocketProperty(String name){
        if(serverSocketProperties == null){
            return null;
        }
        final Iterator props = serverSocketProperties.keySet().iterator();
        while(props.hasNext()){
            final Property prop = (Property)props.next();
            if(prop.getPropertyName().equals(name)){
                return serverSocketProperties.get(prop);
            }
        }
        return null;
    }
    
    protected synchronized void init() throws IOException{
        if(initialized){
            return;
        }
        try{
            SSLContext context = SSLContext.getInstance(protocol); 
            context.init(
                getKeyManagers(),
                getTrustManagers(),
                new SecureRandom()
            );
            serverSocketFactory = context.getServerSocketFactory();
        }catch(RuntimeException e){
            throw e;
        }catch(Exception e){
            if(e instanceof IOException){
                throw (IOException)e;
            }
            e.printStackTrace();
            throw new IOException(e.toString());
        }
        initialized = true;
    }
    
    protected KeyManager[] getKeyManagers() throws Exception {
        
        KeyManager[] keyManager = null;
        
        KeyStore store = getKeyStore();
        
        if(keyAlias != null && !store.isKeyEntry(keyAlias)) {
            throw new IOException("KeyAlias is not entried. " + keyAlias);
        }
        
        KeyManagerFactory keyManagerFactory = KeyManagerFactory.getInstance(keyStoreAlgorithm);
        keyManagerFactory.init(store, keyPassword.toCharArray());
        
        keyManager = keyManagerFactory.getKeyManagers();
        if(keyAlias != null){
            if(DEFAULT_KEYSTORE_TYPE.equals(keyStoreType)) {
                keyAlias = keyAlias.toLowerCase();
            }
            for(int i = 0; i < keyManager.length; i++) {
                keyManager[i] = new X509KeyManagerWrapper((X509KeyManager)keyManager[i], keyAlias);
            }
        }
        
        return keyManager;
    }
    
    protected TrustManager[] getTrustManagers() throws Exception{
        TrustManager[] trustManager = null;
        
        KeyStore trustStore = getTrustStore();
        if(trustStore != null) {
            TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance(trustKeyStoreAlgorithm);
            trustManagerFactory.init(trustStore);
            trustManager = trustManagerFactory.getTrustManagers();
        }
        
        return trustManager;
    }
    
    protected KeyStore getKeyStore() throws IOException{
        return getStore(keyStoreType, keyStoreFile, keyStorePassword);
    }
    
    protected KeyStore getTrustStore() throws IOException{
        KeyStore trustStore = null;
        
        if(trustKeyStoreFile == null){
            trustKeyStoreFile = System.getProperty("javax.net.ssl.trustStore");
        }
        if(trustKeyStorePassword == null){
            trustKeyStorePassword = System.getProperty("javax.net.ssl.trustStorePassword");
        }
        if(trustKeyStorePassword == null){
            trustKeyStorePassword = keyStorePassword;
        }
        if(trustKeyStoreFile != null && trustKeyStorePassword != null){
            trustStore = getStore(
                trustKeyStoreType,
                trustKeyStoreFile,
                trustKeyStorePassword
            );
        }
        return trustStore;
    }
    
    private KeyStore getStore(
        String type,
        String path,
        String password
    ) throws IOException{
        
        KeyStore keyStore = null;
        InputStream is = null;
        try{
            keyStore = KeyStore.getInstance(type);
            File keyStoreFile = new File(path);
            is = new FileInputStream(keyStoreFile);
            
            keyStore.load(is, password.toCharArray());
            is.close();
            is = null;
        }catch(IOException e){
            throw e;
        }catch(Exception e){
            throw new IOException(
                "Exception trying to load keystore " + path
                    + " : " + e.toString()
            );
        }finally{
            if(is != null){
                try{
                    is.close();
                }catch(IOException e){}
            }
        }
        return keyStore;
    }
    
    public ServerSocket createServerSocket() throws IOException{
        if(!initialized){
            init();
        }
        return applyServerSocketProperties(
            new SSLServerSocketWrapper(
                (SSLServerSocket)serverSocketFactory.createServerSocket()
            )
        );
    }
    
    public ServerSocket createServerSocket(int port) throws IOException{
        if(!initialized){
            init();
        }
        return applyServerSocketProperties(
            new SSLServerSocketWrapper(
                (SSLServerSocket)serverSocketFactory.createServerSocket(port)
            )
        );
    }
    
    public ServerSocket createServerSocket(int port, int backlog) throws IOException{
        if(!initialized){
            init();
        }
        return applyServerSocketProperties(
            new SSLServerSocketWrapper(
                (SSLServerSocket)serverSocketFactory.createServerSocket(port, backlog)
            )
        );
    }
    
    public ServerSocket createServerSocket(int port, int backlog, InetAddress bindAddr) throws IOException{
        if(!initialized){
            init();
        }
        return applyServerSocketProperties(
            new SSLServerSocketWrapper(
                (SSLServerSocket)serverSocketFactory.createServerSocket(port, backlog, bindAddr)
            )
        );
    }
    
    public String[] getDefaultCipherSuites(){
        if(!initialized){
            try{
                init();
            }catch(IOException e){
                return new String[0];
            }
        }
        return serverSocketFactory.getDefaultCipherSuites();
    }
    
    public String[] getSupportedCipherSuites(){
        if(!initialized){
            try{
                init();
            }catch(IOException e){
                return new String[0];
            }
        }
        return serverSocketFactory.getSupportedCipherSuites();
    }
    
    protected ServerSocket applyServerSocketProperties(
        SSLServerSocketWrapper serverSocket
    ) throws IOException{
        try{
            if(socketProperties != null && socketProperties.size() != 0){
                final Iterator names = socketProperties.keySet().iterator();
                while(names.hasNext()){
                    final String name = (String)names.next();
                    serverSocket.setSocketProperty(
                        name,
                        socketProperties.get(name)
                    );
                }
            }
            if(serverSocketProperties != null && serverSocketProperties.size() != 0){
                final Iterator props = serverSocketProperties.keySet().iterator();
                while(props.hasNext()){
                    final Property prop = (Property)props.next();
                    prop.setProperty(serverSocket, serverSocketProperties.get(prop));
                }
            }
        }catch(InvocationTargetException e){
            Throwable target = e.getTargetException();
            if(target instanceof IOException){
                throw (IOException)target;
            }else if(target instanceof RuntimeException){
                throw (RuntimeException)target;
            }else if(target instanceof Error){
                throw (Error)target;
            }else{
                throw new UndeclaredThrowableException(target);
            }
        }catch(NoSuchPropertyException e){
            throw new UndeclaredThrowableException(e);
        }
        return serverSocket;
    }
    
    private static class X509KeyManagerWrapper implements X509KeyManager{
        
        private X509KeyManager keyManager;
        private String serverKeyAlias;
        
        public X509KeyManagerWrapper(X509KeyManager mgr, String serverKeyAlias){
            keyManager = mgr;
            this.serverKeyAlias = serverKeyAlias;
        }
        
        public String chooseClientAlias(
            String[] keyType,
            Principal[] issuers,
            Socket socket
        ){
            return keyManager.chooseClientAlias(keyType, issuers, socket);
        }
        
        public String chooseServerAlias(
            String keyType,
            Principal[] issuers,
            Socket socket
        ){
            return serverKeyAlias;
        }
        
        public X509Certificate[] getCertificateChain(String alias){
            return keyManager.getCertificateChain(alias);
        }
        
        public String[] getClientAliases(String keyType, Principal[] issuers){
            return keyManager.getClientAliases(keyType, issuers);
        }
        
        public String[] getServerAliases(String keyType, Principal[] issuers){
            return keyManager.getServerAliases(keyType, issuers);
        }
        
        public PrivateKey getPrivateKey(String alias) {
            return keyManager.getPrivateKey(alias);
        }
    }
}