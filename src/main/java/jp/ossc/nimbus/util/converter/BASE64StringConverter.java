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
package jp.ossc.nimbus.util.converter;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;

import javax.mail.MessagingException;
import javax.mail.internet.MimeUtility;

/**
 * BASE64������R���o�[�^�B<p>
 * javax.mail.internet.MimeUtility���g����BASE64�G���R�[�h���s���B<br>
 *
 * @author M.Takata
 */
public class BASE64StringConverter extends StringStreamConverter implements StringConverter, ReversibleConverter, Serializable{
    
    private static final long serialVersionUID = 1193510073044683299L;

    /**
     * BASE64�ւ̃G���R�[�h�ϊ���\���ϊ���ʒ萔�B<p>
     */
    public static final int ENCODE = POSITIVE_CONVERT;
    
    /**
     * BASE64����̃f�R�[�h�ϊ���\���ϊ���ʒ萔�B<p>
     */
    public static final int DECODE = REVERSE_CONVERT;
    
    /**
     * �����񁨃G���R�[�h���X�g���[����\���ϊ���ʒ萔�B<p>
     */
    public static final int ENCODE_STRING_TO_STREAM = 3;
    
    /**
     * �����񁨃f�R�[�h���X�g���[����\���ϊ���ʒ萔�B<p>
     */
    public static final int DECODE_STRING_TO_STREAM = 4;
    
    /**
     * �X�g���[�����G���R�[�h���������\���ϊ���ʒ萔�B<p>
     */
    public static final int ENCODE_STREAM_TO_STRING = 5;
    
    /**
     * �X�g���[�����f�R�[�h���������\���ϊ���ʒ萔�B<p>
     */
    public static final int DECODE_STREAM_TO_STRING = 6;
    
    private int convertType = ENCODE;
    
    /**
     * �ϊ����Ɏg�p���镶���G���R�[�f�B���O�B<p>
     */
    private String characterEncoding;
    
    /**
     * �����񁨃X�g���[���ϊ����Ɏg�p���镶���G���R�[�f�B���O�B<p>
     */
    private String characterEncodingToStream;
    
    /**
     * �X�g���[����������ϊ����Ɏg�p���镶���G���R�[�f�B���O�B<p>
     */
    private String characterEncodingToObject;
    
    /**
     * �ϊ���ʂ�ݒ肷��B<p>
     *
     * @param type �ϊ����
     * @see #ENCODE
     * @see #DECODE
     * @see #ENCODE_STRING_TO_STREAM
     * @see #DECODE_STRING_TO_STREAM
     * @see #ENCODE_STREAM_TO_STRING
     * @see #DECODE_STREAM_TO_STRING
     */
    public void setConvertType(int type){
        convertType = type;
    }
    
    /**
     * �ϊ���ʂ��擾����B<p>
     *
     * @return �ϊ����
     * @see #ENCODE
     * @see #DECODE
     * @see #ENCODE_STRING_TO_STREAM
     * @see #DECODE_STRING_TO_STREAM
     * @see #ENCODE_STREAM_TO_STRING
     * @see #DECODE_STREAM_TO_STRING
     */
    public int getConvertType(){
        return convertType;
    }
    
    /**
     * �ϊ����Ɏg�p���镶���G���R�[�f�B���O��ݒ肷��B<p>
     * 
     * @param encoding �����G���R�[�f�B���O
     */
    public void setCharacterEncoding(String encoding){
        characterEncoding = encoding;
    }
    
    /**
     * �ϊ����Ɏg�p���镶���G���R�[�f�B���O���擾����B<p>
     * 
     * @return �����G���R�[�f�B���O
     */
    public String getCharacterEncoding(){
        return characterEncoding;
    }
    
    public Object convert(Object obj) throws ConvertException{
        if(obj == null){
            return null;
        }
        switch(convertType){
        case ENCODE:
        case DECODE:
            return convert(obj.toString());
        case ENCODE_STRING_TO_STREAM:
        case DECODE_STRING_TO_STREAM:
            return convertToStream(obj);
        case ENCODE_STREAM_TO_STRING:
        case DECODE_STREAM_TO_STRING:
            return convertToObject((InputStream)obj);
        default:
            throw new ConvertException("Illegal convert type : " + convertType);
        }
    }
    
    public String convert(String str) throws ConvertException{
        if(str == null){
            return null;
        }
        switch(convertType){
        case ENCODE:
            return encode(str);
        case DECODE:
            return decode(str);
        default:
            throw new ConvertException("Illegal convert type : " + convertType);
        }
    }
    
    protected byte[] convertToByteArray(Object obj) throws ConvertException{
        switch(convertType){
        case ENCODE:
        case ENCODE_STRING_TO_STREAM:
            return encodeBytes(super.convertToByteArray(obj));
        case DECODE:
        case DECODE_STRING_TO_STREAM:
            return decodeBytes(super.convertToByteArray(obj));
        default:
            throw new ConvertException("Illegal convert type : " + convertType);
        }
    }
    
    public Object convertToObject(InputStream is) throws ConvertException{
        
        byte[] bytes = toBytes(is);
        switch(convertType){
        case ENCODE:
        case ENCODE_STREAM_TO_STRING:
            return toString(encodeBytes(bytes));
        case DECODE:
        case DECODE_STREAM_TO_STRING:
            return toString(decodeBytes(bytes));
        default:
            throw new ConvertException("Illegal convert type : " + convertType);
        }
    }
    
    /**
     * �w�肳�ꂽ�������BASE64�G���R�[�h����B<p>
     *
     * @param str ������
     * @return BASE64�G���R�[�h���ꂽ������
     * @exception ConvertException �G���R�[�h�Ɏ��s�����ꍇ
     */
    public String encode(String str) throws ConvertException{
        return encode(str, characterEncoding);
    }
    
    /**
     * �w�肳�ꂽ��������A�w�肳�ꂽ�����G���R�[�f�B���O�Ńo�C�g�z��ɕϊ�����BASE64�G���R�[�h����B<p>
     *
     * @param str ������
     * @param encoding �����G���R�[�f�B���O
     * @return BASE64�G���R�[�h���ꂽ������
     * @exception ConvertException �G���R�[�h�Ɏ��s�����ꍇ
     */
    public static String encode(String str, String encoding) throws ConvertException{
        byte[] bytes = null;
        if(encoding == null){
            bytes = str.getBytes();
        }else{
            try{
                bytes = str.getBytes(encoding);
            }catch(IOException e){
                throw new ConvertException(e);
            }
        }
        return new String(encodeBytes(bytes));
    }
    
    /**
     * �w�肳�ꂽ�o�C�g�z���BASE64�G���R�[�h����B<p>
     *
     * @param bytes �o�C�g�z��
     * @return BASE64�G���R�[�h���ꂽ�o�C�g�z��
     * @exception ConvertException �G���R�[�h�Ɏ��s�����ꍇ
     */
    public static byte[] encodeBytes(byte[] bytes) throws ConvertException{
        final ByteArrayOutputStream baos = new ByteArrayOutputStream();
        OutputStream os = null;
        try{
            os = MimeUtility.encode(baos, "base64");
            os.write(bytes);
        }catch(MessagingException e){
            throw new ConvertException(e);
        }catch(IOException e){
            throw new ConvertException(e);
        }finally{
            if(os != null){
                try{
                    os.close();
                }catch(IOException e){}
            }
        }
        return baos.toByteArray();
    }
    
    /**
     * �w�肳�ꂽBASE64��������f�R�[�h����B<p>
     *
     * @param str BASE64�G���R�[�h���ꂽ������
     * @return �f�R�[�h���ꂽ������
     * @exception ConvertException �f�R�[�h�Ɏ��s�����ꍇ
     */
    public String decode(String str) throws ConvertException{
        return decode(str, characterEncoding);
    }
    
    /**
     * �w�肳�ꂽBASE64��������f�R�[�h���A�w�肳�ꂽ�����G���R�[�f�B���O�̕�����ɕϊ�����B<p>
     *
     * @param str BASE64������
     * @param encoding �����G���R�[�f�B���O
     * @return �f�R�[�h���ꂽ������
     * @exception ConvertException �f�R�[�h�Ɏ��s�����ꍇ
     */
    public static String decode(String str, String encoding) throws ConvertException{
        byte[] bytes = decodeBytes(str.getBytes());
        if(encoding == null){
            return new String(bytes);
        }else{
            try{
                return new String(bytes, encoding);
            }catch(IOException e){
                throw new ConvertException(e);
            }
        }
    }
    
    /**
     * �w�肳�ꂽBASE64�o�C�g�z����f�R�[�h����B<p>
     *
     * @param bytes BASE64�o�C�g�z��
     * @return �f�R�[�h���ꂽ�o�C�g�z��
     * @exception ConvertException �f�R�[�h�Ɏ��s�����ꍇ
     */
    public static byte[] decodeBytes(byte[] bytes) throws ConvertException{
        final ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
        byte[] outBytes = null;
        InputStream is = null;
        try{
            is = MimeUtility.decode(bais, "base64");
            byte[] tmpBytes = new byte[bytes.length];
            int length = is.read(tmpBytes);
            outBytes = new byte[length];
            System.arraycopy(tmpBytes, 0, outBytes, 0, length);
        }catch(MessagingException e){
            throw new ConvertException(e);
        }catch(IOException e){
            throw new ConvertException(e);
        }finally{
            if(is != null){
                try{
                    is.close();
                }catch(IOException e){}
            }
        }
        return outBytes;
    }
}
