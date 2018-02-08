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
package jp.ossc.nimbus.service.http.httpclient;

import java.io.*;
import java.util.*;
import java.util.zip.*;

import org.apache.commons.httpclient.*;

import jp.ossc.nimbus.core.*;
import jp.ossc.nimbus.service.http.*;
import jp.ossc.nimbus.util.converter.*;


import org.xerial.snappy.SnappyInputStream;
import net.jpountz.lz4.LZ4BlockInputStream;


/**
 * Jakarta HttpClient���g����HTTP���X�|���X�N���X�B<p>
 *
 * @author M.Takata
 */
public class HttpResponseImpl implements HttpResponse, Cloneable{
    /** �w�b�_�[ : Content-Type */
    protected static final String HEADER_CONTENT_TYPE = "Content-Type";
    /** �w�b�_�[ : charset */
    protected static final String HEADER_CHARSET = "charset";
    /** �w�b�_�[ : Content-Encoding */
    protected static final String HEADER_CONTENT_ENCODING = "Content-Encoding";
    /** �w�b�_�[ : Connection */
    protected static final String HEADER_CONNECTION = "Connection";
    /** �w�b�_�[ : Content-Length */
    protected static final String HEADER_CONTENT_LENGTH = "Content-Length";
    /** �w�b�_�[ : Transfer-Encoding */
    protected static final String HEADER_TRANSFER_ENCODING = "Transfer-Encoding";
    /** Content-Encoding : deflate */
    protected static final String CONTENT_ENCODING_DEFLATE = "deflate";
    /** Content-Encoding : gzip */
    protected static final String CONTENT_ENCODING_GZIP = "gzip";
    /** Content-Encoding : x-zip */
    protected static final String CONTENT_ENCODING_X_GZIP = "x-gzip";

    protected static final String CONTENT_ENCODING_SNAPPY = "snappy";
    protected static final String CONTENT_ENCODING_LZ4 = "lz4";

    /** Connection : close */
    protected static final String CONNECTION_CLOSE = "close";
    /** Transfer-Encoding : chunked */
    protected static final String TRANSFER_ENCODING_CHUNKED = "chunked";
    /** �f�t�H���g���X�|���Xcharset */
    protected static final String DEFAULT_RESPONSE_CHARSET = "ISO8859_1";
    
    protected int statusCode;
    protected String statusMessage;
    protected HttpMethodBase method;
    protected Map headerMap;
    protected InputStream inputStream;
    protected Object outputObject;
    protected ServiceName streamConverterServiceName;
    protected StreamConverter streamConverter;
    protected byte[] outputBytes;
    
    /**
     * HTTP���\�b�h��ݒ肷��B<p>
     *
     * @param method HTTP���\�b�h
     * @exception IOException ���X�|���X�X�g���[���̓ǂݍ��݂Ɏ��s�����ꍇ
     */
    public void setHttpMethod(HttpMethodBase method)throws IOException{
        this.method = method;
        statusMessage = method.getStatusText();
        // ���X�|���X�����k����Ă���Ή���
        inputStream = decompress(
            method.getResponseBodyAsStream()
        );
    }
    
    /**
     * HTTP���X�|���X�̃X�g���[�����o�̓I�u�W�F�N�g���X�g���[���ɕϊ�����{@link jp.ossc.nimbus.util.converter.StreamConverter StreamConverter}�T�[�r�X�̃T�[�r�X����ݒ肷��B<p>
     *
     * @param name StreamConverter�T�[�r�X�̃T�[�r�X��
     */
    public void setStreamConverterServiceName(ServiceName name){
        streamConverterServiceName = name;
    }
    
    /**
     * HTTP���X�|���X�̃X�g���[�����o�̓I�u�W�F�N�g���X�g���[���ɕϊ�����{@link jp.ossc.nimbus.util.converter.StreamConverter StreamConverter}�T�[�r�X�̃T�[�r�X�����擾����B<p>
     *
     * @return StreamConverter�T�[�r�X�̃T�[�r�X��
     */
    public ServiceName getStreamConverterServiceName(){
        return streamConverterServiceName;
    }
    
    /**
     * HTTP���X�|���X�̃X�g���[�����o�̓I�u�W�F�N�g�ɕϊ�����{@link jp.ossc.nimbus.util.converter.StreamConverter StreamConverter}��ݒ肷��B<p>
     *
     * @param converter StreamConverter
     */
    public void setStreamConverter(StreamConverter converter){
        streamConverter = converter;
    }
    
    /**
     * HTTP���X�|���X�̃X�g���[�����o�̓I�u�W�F�N�g�ɕϊ�����{@link jp.ossc.nimbus.util.converter.StreamConverter StreamConverter}���擾����B<p>
     *
     * @return StreamConverter
     */
    public StreamConverter getStreamConverter(){
        return streamConverter;
    }
    
    /**
     * ���̓X�g���[���̈��k����������B<p>
     * (Content-Encoding�Ɏw�肳�ꂽ�t���ŉ���)
     * 
     * @param is ���̓X�g���[��
     * @return ���k�������ꂽ���̓X�g���[��
     * @throws IOException �T�|�[�g���Ă��Ȃ����k�`��(deflate, gzip�ȊO)���w�肳�ꂽ�ꍇ
     */
    protected InputStream decompress(InputStream is) throws IOException {
        if(is == null){
            return null;
        }
        // �w�b�_�[[Content-Encoding]�̒l���擾
        String encode = getHeader(HEADER_CONTENT_ENCODING);
        InputStream in = is;
        if(encode != null){
            
            if(encode.indexOf(CONTENT_ENCODING_DEFLATE) != -1){
                // deflate���k����
                in = new InflaterInputStream(in);
            }else if(encode.indexOf(CONTENT_ENCODING_GZIP) != -1
                        || encode.indexOf(CONTENT_ENCODING_X_GZIP) != -1){
                // gzip���k����
                in = new GZIPInputStream(in);

            }else if(encode.indexOf(CONTENT_ENCODING_SNAPPY) != -1){
                in = new SnappyInputStream(in);
            }else if(encode.indexOf(CONTENT_ENCODING_LZ4) != -1){
                in = new LZ4BlockInputStream(in);

            }else{
                throw new IOException("Can not decompress. [" + encode + "]");
            }
        }
        String transferEncoding = getHeader(HEADER_TRANSFER_ENCODING);
        if(isConnectionClose()
            && (getContentLength() > 0
                || (transferEncoding != null && transferEncoding.indexOf(TRANSFER_ENCODING_CHUNKED) != -1)) 
        ){
            final ByteArrayOutputStream baos = new ByteArrayOutputStream();
            final byte[] bytes = new byte[1024];
            int length = 0;
            while((length = in.read(bytes)) != -1){
                baos.write(bytes, 0, length);
            }
            outputBytes = baos.toByteArray();
            final ByteArrayInputStream bais
                 = new ByteArrayInputStream(outputBytes);
            return bais;
        }else{
            return in;
        }
    }
    
    // HttpResponse��JavaDoc
    public Set getHeaderNameSet(){
        return getHeaderMap().keySet();
    }
    
    // HttpResponse��JavaDoc
    public String getHeader(String name){
        if(headerMap != null){
            String[] vals = (String[])headerMap.get(name);
            if(vals == null){
                final Iterator entries = headerMap.entrySet().iterator();
                while(entries.hasNext()){
                    final Map.Entry entry = (Map.Entry)entries.next();
                    String headerName = (String)entry.getKey();
                    if(headerName.equalsIgnoreCase(name)){
                        vals = (String[])entry.getValue();
                        break;
                    }
                }
            }
            return vals == null || vals.length == 0 ? null : vals[0];
        }
        final Header header = method.getResponseHeader(name);
        return header == null ? null : header.getValue();
    }
    
    // HttpResponse��JavaDoc
    public String[] getHeaders(String name){
        if(headerMap != null){
            String[] vals = (String[])headerMap.get(name);
            if(vals == null){
                final Iterator entries = headerMap.entrySet().iterator();
                while(entries.hasNext()){
                    final Map.Entry entry = (Map.Entry)entries.next();
                    String headerName = (String)entry.getKey();
                    if(headerName.equalsIgnoreCase(name)){
                        vals = (String[])entry.getValue();
                        break;
                    }
                }
            }
            return vals;
        }
        final Header[] headers = method.getResponseHeaders(name);
        if(headers == null){
            return null;
        }
        final String[] vals = new String[headers.length];
        for(int i = 0; i < headers.length; i++){
            vals[i] = headers[i].getValue();
        }
        return vals;
    }
    
    public int getContentLength(){
        final String lenStr = getHeader(HEADER_CONTENT_LENGTH);
        int length = 0;
        if(lenStr != null && lenStr.length() != 0){
            try{
                length = Integer.parseInt(lenStr);
            }catch(NumberFormatException e){
            }
        }
        return length;
    }
    
    /**
     * HTTP�w�b�_�̃}�b�v���擾����B<p>
     *
     * @return HTTP�w�b�_�̃}�b�v
     */
    public Map getHeaderMap(){
        if(headerMap == null){
            headerMap = new LinkedHashMap();
            final Header[] headers = method.getResponseHeaders();
            if(headers != null){
                for(int i = 0; i < headers.length; i++){
                    String name = headers[i].getName();
                    String value = headers[i].getValue();
                    String[] vals = (String[])headerMap.get(name);
                    if(vals == null){
                        vals = new String[]{value};
                        headerMap.put(name, vals);
                    }else{
                        final String[] newVals = new String[vals.length + 1];
                        System.arraycopy(vals, 0, newVals, 0, vals.length);
                        newVals[newVals.length - 1] = value;
                        headerMap.put(name, newVals);
                    }
                }
            }
        }
        return headerMap;
    }
    
    /**
     * HTTP�w�b�_�̃}�b�v��ݒ肷��B<p>
     *
     * @param map HTTP�w�b�_�̃}�b�v
     */
    public void setHeaderMap(Map map){
        headerMap = map;
    }
    
    public void addHeader(String name, String value){
        if(headerMap == null){
            headerMap = new LinkedHashMap();
        }
        String[] vals = (String[])headerMap.get(name);
        if(vals == null){
            vals = new String[]{value};
            headerMap.put(name, vals);
        }else{
            final String[] newVals = new String[vals.length + 1];
            System.arraycopy(vals, 0, newVals, 0, vals.length);
            newVals[newVals.length - 1] = value;
            headerMap.put(name, newVals);
        }
    }
    
    // HttpResponse��JavaDoc
    public InputStream getInputStream() throws IOException{
        return inputStream;
    }
    
    /**
     * ���X�|���X�X�g���[����ݒ肷��B<p>
     *
     * @param in ���X�|���X�X�g���[��
     */
    public void setInputStream(InputStream in){
        inputStream = in;
    }
    
    // HttpResponse��JavaDoc
    public Object getObject() throws ConvertException{
        return getObject(null);
    }
    
    // HttpResponse��JavaDoc
    public Object getObject(Object bind) throws ConvertException{
        if(outputObject == null
             && (streamConverter != null || streamConverterServiceName != null)){
            StreamConverter converter = streamConverter;
            if(streamConverterServiceName != null){
                converter = (StreamConverter)ServiceManagerFactory
                    .getServiceObject(streamConverterServiceName);
            }
            if(converter instanceof StreamStringConverter){
                converter = ((StreamStringConverter)converter).cloneCharacterEncodingToObject(
                    getCharacterEncoding()
                );
            }
            if(inputStream != null){
                try{
                    if(bind != null && converter instanceof BindingStreamConverter){
                        outputObject = ((BindingStreamConverter)converter).convertToObject(inputStream, bind);
                    }else{
                        outputObject = converter.convertToObject(inputStream);
                    }
                }finally{
                    try{
                        inputStream.reset();
                    }catch(IOException e){}
                }
            }
        }
        return outputObject;
    }
    
    /**
     * �����I�u�W�F�N�g��ݒ肷��B<p>
     *
     * @param object �����I�u�W�F�N�g
     */
    public void setObject(Object object){
        outputObject = object;
    }
    
    // HttpResponse��JavaDoc
    public String getCharacterEncoding(){
        final String contentType = getHeader(HEADER_CONTENT_TYPE);
        if(contentType == null){
            return DEFAULT_RESPONSE_CHARSET;
        }
        
        final int index = contentType.indexOf(HEADER_CHARSET);
        if(index == -1){
            return DEFAULT_RESPONSE_CHARSET;
        }else{
            return contentType.substring(
                index + HEADER_CHARSET.length() + 1
            );
        }
    }
    
    // HttpResponse��JavaDoc
    public int getStatusCode(){
        return statusCode;
    }
    
    /**
     * ���X�|���X��HTTP�X�e�[�^�X��ݒ肷��B<p>
     *
     * @param code HTTP�X�e�[�^�X
     */
    public void setStatusCode(int code){
        statusCode = code;
    }
    
    // HttpResponse��JavaDoc
    public String getStatusMessage(){
        return statusMessage;
    }
    
    /**
     * ���X�|���X��HTTP�X�e�[�^�X���b�Z�[�W��ݒ肷��B<p>
     *
     * @param message HTTP�X�e�[�^�X���b�Z�[�W
     */
    public void setStatusMessage(String message){
        statusMessage = message;
    }
    
    /**
     * ���X�|���X�X�g���[�����o�̓I�u�W�F�N�g�ɕϊ������ۂ̃o�C�g�z����擾����B<p>
     *
     * @return ���X�|���X�X�g���[�����o�̓I�u�W�F�N�g�ɕϊ������ۂ̃o�C�g�z��
     */
    public byte[] getOutputBytes(){
        return outputBytes;
    }
    
    /**
     * �����𐶐�����B<p>
     *
     * @return ����
     * @exception CloneNotSupportedException �����Ɏ��s�����ꍇ
     */
    public Object clone() throws CloneNotSupportedException{
        return (HttpResponseImpl)super.clone();
    }
    
    public void close(){
        if(method != null){
            method.releaseConnection();
        }
    }
    
    /**
     * �ڑ���؂��ėǂ������f����B<p>
     *
     * @return Connection�w�b�_��close�������͑��݂��Ȃ��ꍇ�́Atrue�B<p>
     */
    public boolean isConnectionClose(){
        String connection = getHeader(HEADER_CONNECTION);
        return connection == null || CONNECTION_CLOSE.equalsIgnoreCase(connection);
    }
}