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

import java.io.*;

/**
 * ������̃X�g���[���R���o�[�^�B<p>
 * 
 * @author M.Takata
 */
public class StringStreamConverter extends BufferedStreamConverter implements StreamStringConverter, Serializable{
    
    private static final long serialVersionUID = -4431451590828201935L;
    
    /**
     * �����񁨃X�g���[����\���ϊ���ʒ萔�B<p>
     */
    public static final int STRING_TO_STREAM = OBJECT_TO_STREAM;
    
    /**
     * �X�g���[�����������\���ϊ���ʒ萔�B<p>
     */
    public static final int STREAM_TO_STRING = STREAM_TO_OBJECT;
    
    /**
     * �ϊ���ʁB<p>
     */
    protected int convertType;
    
    /**
     * �����񁨃X�g���[���ϊ����Ɏg�p���镶���G���R�[�f�B���O�B<p>
     */
    protected String characterEncodingToStream;
    
    /**
     * �X�g���[����������ϊ����Ɏg�p���镶���G���R�[�f�B���O�B<p>
     */
    protected String characterEncodingToObject;
    
    /**
     * �����񁨃X�g���[���ϊ����s���R���o�[�^�𐶐�����B<p>
     */
    public StringStreamConverter(){
        this(STRING_TO_STREAM);
    }
    
    /**
     * �w�肳�ꂽ�ϊ���ʂ̃R���o�[�^�𐶐�����B<p>
     *
     * @param type �ϊ����
     * @see #STRING_TO_STREAM
     * @see #STREAM_TO_STRING
     */
    public StringStreamConverter(int type){
        convertType = type;
    }
    
    /**
     * �ϊ���ʂ�ݒ肷��B<p>
     *
     * @param type �ϊ����
     * @see #getConvertType()
     * @see #STRING_TO_STREAM
     * @see #STREAM_TO_STRING
     */
    public void setConvertType(int type){
        convertType = type;
    }
    
    /**
     * �ϊ���ʂ��擾����B<p>
     *
     * @return �ϊ����
     * @see #setConvertType(int)
     */
    public int getConvertType(){
        return convertType;
    }
    
    /**
     * �����񁨃X�g���[���ϊ����Ɏg�p���镶���G���R�[�f�B���O��ݒ肷��B<p>
     * 
     * @param encoding �����G���R�[�f�B���O
     */
    public void setCharacterEncodingToStream(String encoding){
        characterEncodingToStream = encoding;
    }
    
    /**
     * �����񁨃X�g���[���ϊ����Ɏg�p���镶���G���R�[�f�B���O���擾����B<p>
     * 
     * @return �����G���R�[�f�B���O
     */
    public String getCharacterEncodingToStream(){
        return characterEncodingToStream;
    }
    
    /**
     * �X�g���[����������ϊ����Ɏg�p���镶���G���R�[�f�B���O��ݒ肷��B<p>
     * 
     * @param encoding �����G���R�[�f�B���O
     */
    public void setCharacterEncodingToObject(String encoding){
        characterEncodingToObject = encoding;
    }
    
    /**
     * �X�g���[����������ϊ����Ɏg�p���镶���G���R�[�f�B���O���擾����B<p>
     * 
     * @return �����G���R�[�f�B���O
     */
    public String getCharacterEncodingToObject(){
        return characterEncodingToObject;
    }
    
    public StreamStringConverter cloneCharacterEncodingToStream(String encoding){
        if((encoding == null && characterEncodingToStream == null)
            || (encoding != null && encoding.equals(characterEncodingToStream))){
            return this;
        }
        try{
            StreamStringConverter clone = (StreamStringConverter)super.clone();
            clone.setCharacterEncodingToStream(encoding);
            return clone;
        }catch(CloneNotSupportedException e){
            return null;
        }
    }
    
    public StreamStringConverter cloneCharacterEncodingToObject(String encoding){
        if((encoding == null && characterEncodingToObject == null)
            || (encoding != null && encoding.equals(characterEncodingToObject))){
            return this;
        }
        try{
            StreamStringConverter clone = (StreamStringConverter)super.clone();
            clone.setCharacterEncodingToObject(encoding);
            return clone;
        }catch(CloneNotSupportedException e){
            return null;
        }
    }
    
    /**
     * �w�肳�ꂽ�I�u�W�F�N�g��ϊ�����B<p>
     *
     * @param obj �ϊ��Ώۂ̃I�u�W�F�N�g
     * @return �ϊ���̃I�u�W�F�N�g
     * @exception ConvertException �ϊ��Ɏ��s�����ꍇ
     */
    public Object convert(Object obj) throws ConvertException{
        if(obj == null){
            return null;
        }
        switch(convertType){
        case STRING_TO_STREAM:
            return convertToStream(obj);
        case STREAM_TO_STRING:
            if(!(obj instanceof InputStream)){
                throw new ConvertException(
                    "Invalid input type : " + obj.getClass()
                );
            }
            return convertToObject((InputStream)obj);
        default:
            throw new ConvertException(
                "Invalid convert type : " + convertType
            );
        }
    }
    
    /**
     * �����񂩂�o�C�g�z��ɕϊ�����B<p>
     *
     * @param obj ������
     * @return �o�C�g�z��
     * @exception ConvertException �ϊ��Ɏ��s�����ꍇ
     */
    protected byte[] convertToByteArray(Object obj) throws ConvertException{
        byte[] bytes = null;
        if(characterEncodingToStream == null){
            bytes = ((String)obj).getBytes();
        }else{
            try{
                bytes = ((String)obj).getBytes(characterEncodingToStream);
            }catch(UnsupportedEncodingException e){
                throw new ConvertException(e);
            }
        }
        return bytes;
    }
    
    /**
     * �X�g���[�����當����ɕϊ�����B<p>
     *
     * @param is �X�g���[��
     * @return ������
     * @exception ConvertException �ϊ��Ɏ��s�����ꍇ
     */
    public Object convertToObject(InputStream is) throws ConvertException{
        return toString(toBytes(is));
    }
    
    protected byte[] toBytes(InputStream is) throws ConvertException{
        byte[] bytes = new byte[1024];
        final ByteArrayOutputStream baos = new ByteArrayOutputStream();
        int length = 0;
        try{
            while((length = is.read(bytes)) != -1){
                baos.write(bytes, 0, length);
            }
        }catch(IOException e){
            throw new ConvertException(e);
        }
        return baos.toByteArray();
    }
    
    protected String toString(byte[] bytes) throws ConvertException{
        if(characterEncodingToObject == null){
            return new String(bytes);
        }else{
            try{
                return new String(bytes, characterEncodingToObject);
            }catch(UnsupportedEncodingException e){
                throw new ConvertException(e);
            }
        }
    }
}
