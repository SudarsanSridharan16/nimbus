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
import java.util.regex.*;

/**
 * HTTP���N�G�X�g�B<p>
 * 
 * @author M.Takata
 */
public class HttpRequest{
    
    private static final String HEADER_NAME_CONTENT_LENGTH = "Content-Length";
    private static final String HEADER_NAME_CONTENT_ENCODING = "Content-Encoding";
    private static final String HEADER_NAME_CONTENT_TYPE = "Content-Type";
    private static final String HEADER_NAME_TRANSFER_ENCODING = "Transfer-Encoding";
    private static final String HEADER_ACCEPT_ENCODING = "Accept-Encoding";
    
    private static final String CONTENT_ENCODING_DEFLATE = "deflate";
    private static final String CONTENT_ENCODING_GZIP = "gzip";
    private static final String CONTENT_ENCODING_X_GZIP = "x-gzip";
    
    private static final String CHARSET = "charset";
    private static final String DEFAULT_CHARACTER_ENCODING = "ISO8859_1";
    private static final String CHUNKED = "chunked";
    private static final String HTTP_METHOD_POST = "POST";
    private static final String HTTP_METHOD_PUT = "PUT";
    
    /**
     * HTTP���N�G�X�g�̃w�b�_�B<p>
     */
    protected RequestHeader header;
    
    /**
     * HTTP���N�G�X�g�̃{�f�B�B<p>
     */
    protected RequestBody body;
    
    /**
     * ��̃C���^���X�𐶐�����B<p>
     */
    public HttpRequest(){
    }
    
    /**
     * �C���^���X�𐶐�����B<p>
     * �w�b�_�̓ǂݍ��݂܂ōs���B<br>
     * �{�f�B�́A�X�g���[���̓ǂݍ��݂͍s�킸�A�{�f�B�̊J�n�ʒu�̃X�g���[�����i�[����B<br>
     *
     * @param is HTTP���N�G�X�g�̗v���X�g���[��
     * @exception Exception �w�b�_�̓ǂݍ��݂Ɏ��s�����ꍇ
     */
    public HttpRequest(InputStream is) throws Exception{
        header = new RequestHeader();
        header.read(is);
        if(HTTP_METHOD_POST.equals(header.method)
                || HTTP_METHOD_PUT.equals(header.method)){
            body = new RequestBody(header, is);
        }
    }
    
    /**
     * HTTP���N�G�X�g�̃w�b�_���擾����B<p>
     *
     * @return HTTP���N�G�X�g�̃w�b�_
     */
    public RequestHeader getHeader(){
        return header;
    }
    
    /**
     * HTTP���N�G�X�g�̃{�f�B���擾����B<p>
     *
     * @return HTTP���N�G�X�g�̃{�f�B
     */
    public RequestBody getBody(){
        return body;
    }
    
    /**
     * HTTP���N�G�X�g�w�b�_�B<p>
     * 
     * @author M.Takata
     */
    public static class RequestHeader{
        
        /**
         * HTTP���N�G�X�g�w�b�_�̑S���B<p>
         */
        protected String header;
        
        /**
         * HTTP���\�b�h�B<p>
         */
        protected String method;
        
        /**
         * ���N�G�X�gURL�B<p>
         * �A���A�N�G��������͊܂܂Ȃ��B<br>
         */
        protected String url;
        
        /**
         * �N�G��������B<p>
         */
        protected String query;
        
        /**
         * HTTP�o�[�W�����B<p>
         */
        protected String version;
        
        /**
         * HTTP�w�b�_�}�b�v�B<p>
         */
        protected Map headerMap = new HashMap();
        
        /**
         * HTTP���\�b�h���擾����B<p>
         * 
         * @return HTTP���\�b�h
         */
        public String getMethod(){
            return method;
        }
        
        /**
         * URL���擾����B<p>
         * �A���A�N�G��������͊܂܂Ȃ��B<br>
         * 
         * @return URL
         */
        public String getURL(){
            return url;
        }
        
        /**
         * URL�̐��K�\����v�������邽�߂�java.util.Matcher���擾����B<p>
         *
         * @param url URL�̐��K�\��
         * @return ���K�\���}�b�`�G���W��
         */
        public Matcher getURLMatcher(String url){
             return Pattern.compile(url).matcher(this.url);
        }
        
        /**
         * �N�G����������擾����B<p>
         * 
         * @return �N�G��������
         */
        public String getQuery(){
            return query;
        }
        
        /**
         * �N�G��������̐��K�\����v�������邽�߂�java.util.Matcher���擾����B<p>
         *
         * @param query �N�G��������̐��K�\��
         * @return ���K�\���}�b�`�G���W��
         */
        public Matcher getQueryMatcher(String query){
             return Pattern.compile(query).matcher(this.query == null ? "" : this.query);
        }
        
        /**
         * HTTP�o�[�W�������擾����B<p>
         * 
         * @return HTTP�o�[�W����
         */
        public String getVersion(){
            return version;
        }
        
        /**
         * �w�肳�ꂽ���O��HTTP�w�b�_���擾����B<p>
         *
         * @param name �w�b�_��
         * @return �w�b�_�l
         */
        public String getHeader(String name){
            final String[] vals = (String[])headerMap.get(name);
            return vals == null ? null : vals[0];
        }
        
        /**
         * �w�肳�ꂽ���O��HTTP�w�b�_���擾����B<p>
         *
         * @param name �w�b�_��
         * @return �w�b�_�l�z��
         */
        public String[] getHeaders(String name){
            return (String[])headerMap.get(name);
        }
        
        public Map getHeaderMap(){
            return headerMap;
        }
        
        /**
         * Content-Length�w�b�_���擾����B<p>
         *
         * @return Content-Length�̒l�B������Ȃ��ꍇ��-1
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
         * Content-Length�w�b�_��ݒ肷��B<p>
         *
         * @param length Content-Length�̒l
         */
        public void setContentLength(int length){
            headerMap.put(
                HEADER_NAME_CONTENT_LENGTH,
                new String[]{String.valueOf(length)}
            );
        }
        
        /**
         * Content-Type�w�b�_��charset���擾����B<p>
         *
         * @return charset�̒l�B������Ȃ��ꍇ��ISO8859_1
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
         * Content-Encoding�w�b�_���擾����B<p>
         *
         * @return Content-Encoding�w�b�_
         */
        public String[] getContentEncoding(){
            final String[] contentEncoding
                 = getHeaders(HEADER_NAME_CONTENT_ENCODING);
            return contentEncoding;
        }
        
        /**
         * Accept-Encoding�w�b�_���擾����B<p>
         *
         * @return Accept-Encoding�w�b�_
         */
        public String getAcceptEncoding(){
            final String acceptEncoding
                 = getHeader(HEADER_ACCEPT_ENCODING);
            return acceptEncoding;
        }
        
        /**
         * Transfer-Encoding�w�b�_��chunked���w�肳��Ă��邩�𔻒肷��B<p>
         *
         * @return Transfer-Encoding�w�b�_�̒l��chunked�̏ꍇtrue
         */
        public boolean isChunked(){
            final String transferEncoding
                 = getHeader(HEADER_NAME_TRANSFER_ENCODING);
            if(transferEncoding == null){
                return false;
            }
            return CHUNKED.equals(transferEncoding.trim());
        }
        
        /**
         * ���N�G�X�g�w�b�_��ǂݍ��ށB<p>
         *
         * @param is HTTP���N�G�X�g�̓��̓X�g���[��
         * @exception Exception �ǂݍ��݋y�щ�͂Ɏ��s�����ꍇ
         */
        public void read(InputStream is) throws Exception{
            final StringWriter sw = new StringWriter();
            final PrintWriter pw = new PrintWriter(sw);
            final ByteArrayOutputStream baos = new ByteArrayOutputStream();
            String requestLine = readLine(is, baos);
            requestLine = requestLine.trim();
            pw.println(requestLine);
            String[] requests = requestLine.split(" ");
            if(requests.length != 3){
                throw new Exception("illegal request : " + requestLine);
            }
            method = requests[0];
            int index = requests[1].indexOf(';');
            if(index == -1){
                index = requests[1].indexOf('?');
                if(index == -1){
                    url = requests[1];
                }else{
                    url = requests[1].substring(0, index);
                    query = requests[1].substring(index + 1);
                }
            }else{
                url = requests[1].substring(0, index);
                index = requests[1].indexOf('?');
                if(index != -1){
                    query = requests[1].substring(index + 1);
                }
            }
            version = requests[2];
            
            String headerLine = null;
            do{
                headerLine = readLine(is, baos);
                if(headerLine == null){
                    break;
                }
                pw.println(headerLine);
                headerLine = headerLine.trim();
                if(headerLine.length() == 0){
                    break;
                }
                index = headerLine.indexOf(':');
                if(index == -1
                     || index == 0
                     || index == headerLine.length() - 1){
                    continue;
                }
                final String name = headerLine.substring(0, index).trim();
                final String val = headerLine.substring(index + 1).trim();
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
            }while(true);
            pw.close();
            header = sw.toString();
        }
        
        /**
         * �P�s�������ǂݍ��ށB<p>
         *
         * @param is ���̓X�g���[��
         * @param tmp �ꎞ�o�b�t�@�p�̏o�̓X�g���[��
         * @exception IOException �ǂݍ��݂Ɏ��s�����ꍇ
         */
        public static String readLine(InputStream is, ByteArrayOutputStream tmp) throws IOException{
            tmp.reset();
            int val = 0;
            while((val = is.read()) != -1){
                if(val == (int)'\r'){
                    val = is.read();
                    boolean isBreak = false;
                    switch(val){
                    case -1:
                        isBreak = true;
                        break;
                    case (int)'\n':
                        isBreak = true;
                        break;
                    default:
                        tmp.write((int)'\r');
                        tmp.write(val);
                    }
                    if(isBreak){
                        break;
                    }
                }else{
                    tmp.write(val);
                }
            }
            return new String(tmp.toByteArray());
        }
        
        /**
         * �w�b�_��������擾����B<p>
         *
         * @return �w�b�_������
         */
        public String toString(){
            return header;
        }
    }
    
    /**
     * HTTP���N�G�X�g�{�f�B�B<p>
     * 
     * @author M.Takata
     */
    public class RequestBody{
        
        /**
         * HTTP���N�G�X�g�̓��̓X�g���[���B<p>
         */
        protected InputStream inputStream;
        
        /**
         * ���N�G�X�g�w�b�_�B<p>
         */
        protected RequestHeader header;
        
        /**
         * HTTP���N�G�X�g�̃{�f�B�o�C�g�z��B<p>
         */
        protected byte[] body;
        
        /**
         * Content-Encoding�ɏ]�������̓X�g���[���̉𓀂��s�����ǂ����������t���O�B<p>
         * �f�t�H���g�Atrue�ŉ𓀂���B<br>
         */
        protected boolean isDecompress = true;
        
        /**
         * �C���X�^���X�𐶐�����B<p>
         *
         * @param header HTTP���N�G�X�g�w�b�_
         * @param is HTTP���N�G�X�g�̓��̓X�g���[��
         * @exception Exception ���̓X�g���[���̉𓀂Ɏ��s�����ꍇ
         */
        public RequestBody(RequestHeader header, InputStream is) throws Exception{
            this.header = header;
            inputStream = is;
            final int contentLength = header.getContentLength();
            final String[] contentEncoding = header.getContentEncoding();
            if(isDecompress && contentLength > 0 && contentEncoding != null){
                inputStream = decompress(inputStream, contentEncoding, contentLength);
            }
        }
        
        /**
         * Content-Encoding�ɏ]�������̓X�g���[���̉𓀂��s�����ǂ�����ݒ肷��B<p>
         * �f�t�H���g�́Atrue�B<br>
         *
         * @param isDecompress �𓀂���ꍇ��true
         */
        public void setDecompress(boolean isDecompress){
            this.isDecompress = isDecompress;
        }
        
        /**
         * Content-Encoding�ɏ]�������̓X�g���[���̉𓀂��s�����ǂ����𔻒肷��B<p>
         *
         * @return true�̏ꍇ�́A�𓀂���
         */
        public boolean isDecompress(){
            return isDecompress;
        }
        
        /**
         * HTTP���N�G�X�g�̓��̓X�g���[�����擾����B<p>
         *
         * @return HTTP���N�G�X�g�̓��̓X�g���[��
         */
        public InputStream getInputStream(){
            return inputStream;
        }
        
        /**
         * HTTP���N�G�X�g�̃{�f�B�𕶎���Ƃ��ēǂݍ��ށB<p>
         *
         * @exception Exception �ǂݍ��݂Ɏ��s�����ꍇ
         */
        public void read() throws Exception{
            
            if(header.isChunked()){
                int data = 0;
                int chunkSize = -1;
                ByteArrayOutputStream temp = new ByteArrayOutputStream();
                ByteArrayOutputStream result = new ByteArrayOutputStream();
                while (true) {
                    data = inputStream.read();
                    // \r
                    if (data == (int)'\r') {
                        data = inputStream.read();
                        // \n
                        if (data == (int)'\n') {
                            chunkSize = Integer.parseInt(new String(temp.toByteArray()), 16);
                            temp.reset();
                            byte[] bytes = new byte[chunkSize + 2];
                            int offset = 0;
                            int readLength = 0;
                            while ((readLength = inputStream.read(bytes, offset, bytes.length - offset)) != -1) {
                                offset += readLength;
                                if (offset >= bytes.length) {
                                    break;
                                }
                            }
                            result.write(bytes);
                            if (chunkSize == 0) {
                                break;
                            }
                        }
                    } else {
                        temp.write(data);
                    }
                }
                body = result.toByteArray();
            }else{
                final int contentLength = header.getContentLength();
                if(contentLength <= 0){
                    return;
                }
                final byte[] readBytes = new byte[contentLength + 1];
                int readLength = 0;
                int offset = 0;
                while(offset < contentLength
                     && (readLength = inputStream.read(readBytes, offset, contentLength - offset)) != -1){
                    offset += readLength;
                }
                if(readLength == -1){
                    readLength = offset;
                }else{
                    readLength = contentLength;
                }
                body = new byte[readLength];
                System.arraycopy(readBytes, 0, body, 0, readLength);
            }
        }
        
        /**
         * Content-Encoding�̎w��ɏ]���āA���̓X�g���[�����𓀂���B<p>
         * �Ή����Ă���Content-Encoding�́Adeflate�Agzip�Ax-gzip�ł���B<br>
         *
         * @param is ���̓X�g���[��
         * @param contentEncoding Content-Encoding
         * @return �𓀂��ꂽ���̓X�g���[���B�𓀂���K�v���Ȃ��ꍇ�́A���̂܂ܕԂ��B
         * @exception IOException �𓀂Ɏ��s�����ꍇ�B�Ή����Ă��Ȃ�Content-Encoding���w�肳��Ă����ꍇ�B
         */
        protected InputStream decompress(InputStream is, String[] contentEncoding, int contentLength) throws IOException {
            if(contentEncoding == null || contentEncoding.length == 0){
                return is;
            }
            
            byte[] buf = new byte[contentLength];
            int length = 0;
            int offset = 0;
            while((length = is.read(buf, offset, contentLength - offset)) != -1
                && offset < contentLength){
                offset += length;
            }
            ByteArrayInputStream bais = new ByteArrayInputStream(buf);
            InputStream in = bais;
            for(int i = (contentEncoding.length - 1); i >= 0; i--){
                final String encode = contentEncoding[i];
                if(encode != null){
                    if(encode.indexOf(CONTENT_ENCODING_DEFLATE) != -1){
                        // deflate���k����
                        in = new InflaterInputStream(in);
                    }else if(encode.indexOf(CONTENT_ENCODING_GZIP) != -1
                                || encode.indexOf(CONTENT_ENCODING_X_GZIP) != -1){
                        // gzip���k����
                        in = new GZIPInputStream(in);
                    }else{
                        throw new IOException("Can not decompress. [" + encode + "]");
                    }
                }
            }
            int data = 0;
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            while((data = in.read()) != -1){
                baos.write(data);
            }
            buf = baos.toByteArray();
            header.setContentLength(buf.length);
            bais = new ByteArrayInputStream(buf);
            return bais;
        }
        
        /**
         * HTTP���N�G�X�g�̃{�f�B��������擾����B<p>
         * �����I��{@link #read()}���Ăяo���܂ł́Anull�B<br>
         * characterEncoding�̐ݒ肪�s���ȏꍇ�́Anull�B<br>
         *
         * @return HTTP���N�G�X�g�̃{�f�B������
         */
        public String toString(){
        	if(body == null){
        		return null;
        	}
            final String characterEncoding = header.getCharacterEncoding();
            try {
				return new String(body, characterEncoding);
			} catch (UnsupportedEncodingException e) {
				return null;
			}
        }

        /**
         * HTTP���N�G�X�g�̃{�f�B��������擾����B<p>
         * �����I��{@link #read()}���Ăяo���܂ł́Anull�B<br>
         *
         * @return HTTP���N�G�X�g�̃{�f�B�̃o�C�g�z��
         */
        public byte[] toByteArray(){
            return body;
        }
        
        /**
         * �{�f�B������̐��K�\����v�������邽�߂�java.util.Matcher���擾����B<p>
         *
         * @param body �{�f�B������̐��K�\��
         * @return ���K�\���}�b�`�G���W��
         */
        public Matcher getMatcher(String body){
            String bodyString = toString();
            return Pattern.compile(body).matcher(bodyString == null ? "" : bodyString);
        }
    }
}
