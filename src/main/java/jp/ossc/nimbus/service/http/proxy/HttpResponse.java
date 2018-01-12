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
package jp.ossc.nimbus.service.http.proxy;

import java.io.*;
import java.util.*;
import java.util.zip.*;
import java.text.*;

/**
 * HTTP���X�|���X�B<p>
 * 
 * @author M.Takata
 */
public class HttpResponse{
    
    private static final String HEADER_NAME_CONTENT_LENGTH = "Content-Length";
    private static final String HEADER_NAME_CONTENT_TYPE = "Content-Type";
    private static final String HEADER_NAME_CONTENT_ENCODING = "Content-Encoding";
    private static final String HEADER_NAME_TRANSFER_ENCODING = "Transfer-Encoding";
    private static final String HEADER_NAME_CONNECTION = "Connection";
    private static final String HEADER_NAME_PROXY_CONNECTION = "Proxy-Connection";
    
    private static final String CONTENT_ENCODING_DEFLATE = "deflate";
    private static final String CONTENT_ENCODING_GZIP = "gzip";
    private static final String CONTENT_ENCODING_X_GZIP = "x-gzip";
    private static final String CONTENT_ENCODING_ALL = "*";
    
    private static final String CHARSET = "charset";
    private static final String DEFAULT_CHARACTER_ENCODING = "ISO8859_1";
    private static final String DEFAULT_HTTP_VERSION = "HTTP/1.1";
    private static final int DEFAULT_HTTP_STATUS = 200;
    private static final String DEFAULT_HTTP_STATUS_MESSAGE = "OK";
    
    private String version = DEFAULT_HTTP_VERSION;
    private int statusCode = DEFAULT_HTTP_STATUS;
    private String statusMessage = DEFAULT_HTTP_STATUS_MESSAGE;
    private Map headerMap = new LinkedHashMap();
    
    private ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    
    /**
     * ��̃C���X�^���X�𐶐�����B<p>
     */
    public HttpResponse(){
    }
    
    /**
     * HTTP�̃o�[�W������ݒ肷��B<p>
     * �f�t�H���g�́AHTTP/1.1�B<br>
     *
     * @param version HTTP�̃o�[�W����
     */
    public void setVersion(String version){
        this.version = version;
    }
    
    /**
     * HTTP�̃o�[�W�������擾����B<p>
     *
     * @return HTTP�̃o�[�W����
     */
    public String getVersion(){
        return version;
    }
    
    /**
     * HTTP�̃X�e�[�^�X�R�[�h��ݒ肷��B<p>
     * �f�t�H���g�́A200�B<br>
     *
     * @param code HTTP�̃X�e�[�^�X�R�[�h
     */
    public void setStatusCode(int code){
       statusCode = code;
    }
    
    /**
     * HTTP�̃X�e�[�^�X�R�[�h���擾����B<p>
     *
     * @return HTTP�̃X�e�[�^�X�R�[�h
     */
    public int getStatusCode(){
       return statusCode;
    }
    
    /**
     * HTTP�̃X�e�[�^�X���b�Z�[�W��ݒ肷��B<p>
     * �f�t�H���g�́A"OK"�B<br>
     *
     * @param message HTTP�̃X�e�[�^�X���b�Z�[�W
     */
    public void setStatusMessage(String message){
        statusMessage = message;
    }
    
    /**
     * HTTP�̃X�e�[�^�X���b�Z�[�W���擾����B<p>
     *
     * @return HTTP�̃X�e�[�^�X���b�Z�[�W
     */
    public String getStatusMessage(){
        return statusMessage;
    }
    
    /**
     * �w�b�_��ݒ肷��B<p>
     *
     * @param name �w�b�_��
     * @param val �w�b�_�l
     */
    public void setHeader(String name, String val){
        String[] vals = (String[])headerMap.get(name);
        if(vals == null){
            vals = new String[1];
            vals[0] = val;
            headerMap.put(name, vals);
        }else{
            final String[] newVals = new String[vals.length + 1];
            System.arraycopy(vals, 0, newVals, 0, vals.length);
            newVals[newVals.length - 1] = val;
            headerMap.put(name, newVals);
        }
    }
    
    /**
     * �����w�b�_��ݒ肷��B<p>
     *
     * @param name �w�b�_��
     * @param date ����
     */
    public void setDateHeader(String name, Date date){
        SimpleDateFormat format = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss zzz", Locale.ENGLISH);
        setHeader(name, format.format(date));
    }
    
    /**
     * �w�b�_��ݒ肷��B<p>
     *
     * @param name �w�b�_��
     * @param vals �w�b�_�l�z��
     */
    public void setHeaders(String name, String[] vals){
        headerMap.put(name, vals);
    }
    
    /**
     * �w�b�_���̏W�����擾����B<p>
     *
     * @return �w�b�_���̏W��
     */
    public Set getHeaderNameSet(){
        return headerMap.keySet();
    }
    
    /**
     * �w�b�_���擾����B<p>
     *
     * @param name �w�b�_��
     * @return �w�b�_�l
     */
    public String getHeader(String name){
        final String[] vals = (String[])headerMap.get(name);
        return vals == null ? null : vals[0];
    }
    
    /**
     * �w�b�_���擾����B<p>
     *
     * @param name �w�b�_��
     * @return �w�b�_�l�z��
     */
    public String[] getHeaders(String name){
        return (String[])headerMap.get(name);
    }
    
    /**
     * �w�b�_���폜����B<p>
     *
     * @param name �w�b�_��
     */
    public void removeHeader(String name){
        headerMap.remove(name);
    }
    
    /**
     * Content-Length�w�b�_���擾����B<p>
     *
     * @return Content-Length�w�b�_�̒l�B���݂��Ȃ��ꍇ�́A-1
     */
    public int getContentLength(){
        final String contentLengthStr
             = getHeader(HEADER_NAME_CONTENT_LENGTH);
        if(contentLengthStr == null){
            return -1;
        }
        int contentLength = -1;
        try{
            contentLength = Integer.parseInt(contentLengthStr);
        }catch(NumberFormatException e){
        }
        return contentLength;
    }
    
    /**
     * Content-Type�w�b�_��charset�̒l���擾����B<p>
     *
     * @return Content-Type�w�b�_��charset�̒l�B���݂��Ȃ��ꍇ�́AISO8859_1
     */
    public String getCharacterEncoding(){
        String characterEncoding = DEFAULT_CHARACTER_ENCODING;
        final String contentType
             = getHeader(HEADER_NAME_CONTENT_TYPE);
        if(contentType == null){
            return characterEncoding;
        }
        final StringTokenizer tokens
             = new StringTokenizer(contentType, ";");
        while(tokens.hasMoreTokens()){
            final String token = tokens.nextToken();
            if(token.indexOf(CHARSET) != -1){
                final int index = token.indexOf('=');
                if(index <= 0
                     || index == token.length() - 1){
                    continue;
                }
                final String charset = token.substring(index + 1).trim();
                if(charset.length() != 0){
                    characterEncoding = charset;
                    break;
                }
            }
        }
        return characterEncoding;
    }
    
    /**
     * Content-Encoding�w�b�_�̒l���擾����B<p>
     *
     * @return Content-Encoding�w�b�_�̒l
     */
    public String getContentEncoding(){
        final String contentEncoding
             = getHeader(HEADER_NAME_CONTENT_ENCODING);
        return contentEncoding;
    }
    
    /**
     * Transfer-Encoding�w�b�_�̒l���擾����B<p>
     *
     * @return Transfer-Encoding�w�b�_�̒l
     */
    public String getTransferEncoding(){
        final String transferEncoding
             = getHeader(HEADER_NAME_TRANSFER_ENCODING);
        return transferEncoding;
    }
    
    /**
     * Connection�w�b�_�܂���Proxy-Connection�w�b�_�̒l���擾����B<p>
     *
     * @return Connection�w�b�_�܂���Proxy-Connection�w�b�_�̒l
     */
    public String getConnection(){
        String connection = getHeader(HEADER_NAME_CONNECTION);
        if(connection == null){
            connection = getHeader(HEADER_NAME_PROXY_CONNECTION);
        }
        return connection;
    }
    
    /**
     * HTTP���X�|���X�̃{�f�B�̏o�̓X�g���[�����擾����B<p>
     *
     * @return HTTP���X�|���X�̃{�f�B�̏o�̓X�g���[��
     */
    public OutputStream getOutputStream(){
        return outputStream;
    }
    
    /**
     * HTTP���X�|���X��HTTP�w�b�_�Ɏw�肳�ꂽContent-Encoding���AHTTP���N�G�X�g��HTTP�w�b�_�Ɏw�肳�ꂽAccept-Encoding�Ɋ܂܂�Ă��邩�𔻒肷��B<p>
     *
     * @param contentEncoding HTTP���X�|���X��HTTP�w�b�_�Ɏw�肳�ꂽContent-Encoding
     * @param acceptEncoding HTTP���N�G�X�g��HTTP�w�b�_�Ɏw�肳�ꂽAccept-Encoding
     * @return �܂܂�Ă���ꍇ�́Atrue
     */
    protected boolean isAppropriateEncoding(
        String contentEncoding,
        String acceptEncoding
    ){
        if(acceptEncoding == null){
            return false;
        }
        if(acceptEncoding.indexOf(';') == -1){
            if(acceptEncoding.indexOf(contentEncoding) != -1
                 || acceptEncoding.indexOf(CONTENT_ENCODING_ALL) != -1){
                return true;
            }else{
                return false;
            }
        }
        final String[] encodes = acceptEncoding.split(",");
        for(int i = 0; i < encodes.length; i++){
            String encode = encodes[i].trim();;
            if(encode.startsWith(contentEncoding)
                || encode.startsWith(CONTENT_ENCODING_ALL)
            ){
                int index = encode.indexOf(';');
                double qValue = 1.0d;
                if(index == -1){
                    return true;
                }else{
                    String qValueStr = encode.substring(index + 1);
                    encode = encode.substring(0, index).trim();
                    index = qValueStr.indexOf('=');
                    if(index != -1){
                        qValueStr = qValueStr.substring(index + 1);
                        try{
                            qValue = Double.parseDouble(qValueStr);
                        }catch(NumberFormatException e){
                        }
                    }
                    if(qValue != 0.0d){
                        return true;
                    }else if(contentEncoding.equals(encode)){
                        return false;
                    }
                }
            }else{
                continue;
            }
        }
        return false;
    }
    
    /**
     * ����HTTP���X�|���X�̃w�b�_�y�у{�f�B���o�̓X�g���[���ɏ������ށB<p>
     *
     * @param request HTTP���N�G�X�g
     * @param os HTTP���X�|���X�o�̓X�g���[��
     * @exception IOException �������݂Ɏ��s�����ꍇ
     */
    public void writeResponse(HttpRequest request, OutputStream os) throws IOException{
        byte[] bodyBytes = outputStream.toByteArray();
        final PrintWriter pw = new PrintWriter(
            new OutputStreamWriter(os, getCharacterEncoding())
        );
        try{
            pw.print(
                version + ' '
                 + statusCode + ' '
                 + statusMessage
                 + '\r' + '\n'
            );
            final String contentEncoding = getContentEncoding();
            if(contentEncoding != null){
                if(bodyBytes.length == 0){
                    removeHeader(HEADER_NAME_CONTENT_ENCODING);
                }else{
                    final String acceptEncoding
                         = request == null ? null : request.header.getAcceptEncoding();
                    if(!isAppropriateEncoding(contentEncoding, acceptEncoding)){
                        removeHeader(HEADER_NAME_CONTENT_ENCODING);
                    }
                }
            }
            final Iterator headerNames = getHeaderNameSet().iterator();
            while(headerNames.hasNext()){
                final String name = (String)headerNames.next();
                if(HEADER_NAME_CONNECTION.equals(name) || HEADER_NAME_PROXY_CONNECTION.equals(name)){
                    continue;
                }
                final String[] vals = getHeaders(name);
                for(int i = 0; i < vals.length; i++){
                    pw.print(name + ": " + vals[i] + '\r' + '\n');
                }
            }
            pw.print(HEADER_NAME_CONNECTION + ": close\r\n");
            int contentLength = getContentLength();
            String transferEncoding = getTransferEncoding();
            if(transferEncoding == null
                && bodyBytes != null && bodyBytes.length != contentLength
            ){
                bodyBytes = compress(bodyBytes, getContentEncoding());
            }
            String method = request == null ? null : request.header.method;
            if(transferEncoding == null
                && statusCode > 199
                && statusCode != 204
                && !"CONNECT".equals(method)
            ){
                if(contentLength == -1){
                    if(bodyBytes != null){
                        contentLength = bodyBytes.length;
                    }else{
                        contentLength = 0;
                    }
                }
                pw.print(HEADER_NAME_CONTENT_LENGTH + ": " + contentLength + '\r' + '\n');
            }
            pw.print('\r');
            pw.print('\n');
            pw.flush();
            if(bodyBytes != null && bodyBytes.length != 0){
                os.write(bodyBytes);
                os.flush();
            }
        }finally{
            String method = request == null ? null : request.header.method;
            if(!("CONNECT".equals(method) && 200 <= statusCode && statusCode < 300)){
                pw.close();
            }
        }
    }
    
    /**
     * �w�肳�ꂽ�o�C�g�z����w�肳�ꂽContent-Encoding�ň��k����B<p>
     * �Ή����Ă���Content-Encoding�́Adeflate�Agzip�Ax-gzip�ł���B<br>
     *
     * @param bytes ���̓o�C�g�z��
     * @param contentEncoding Content-Encoding�w�b�_
     * @return ���k���ꂽ�o�C�g�z��B���k����K�v���Ȃ��ꍇ�́A���̂܂ܕԂ�
     * @exception IOExceptioon ���k�Ɏ��s�����ꍇ�B�Ή����Ă��Ȃ�Content-Encoding���w�肳��Ă����ꍇ�B
     */
    protected byte[] compress(
        byte[] bytes,
        String contentEncoding
    ) throws IOException {
        if(contentEncoding == null){
            return bytes;
        }
        final ByteArrayOutputStream baos = new ByteArrayOutputStream();
        OutputStream out = baos;
        if(contentEncoding.indexOf(CONTENT_ENCODING_DEFLATE) != -1){
            // deflate���k
            out = new DeflaterOutputStream(out);
        }else if(contentEncoding.indexOf(CONTENT_ENCODING_GZIP) != -1
                    || contentEncoding.indexOf(CONTENT_ENCODING_X_GZIP) != -1){
            // gzip���k
            out = new GZIPOutputStream(out);
        }else{
            throw new IOException("Can not compress. [" + contentEncoding + "]");
        }
        out.write(bytes);
        out.flush();
        ((DeflaterOutputStream)out).finish();
        out.close();
        return baos.toByteArray();
    }
}
