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
import java.util.zip.*;

/**
 * Serializable�I�u�W�F�N�g�̃X�g���[���R���o�[�^�B<p>
 * 
 * @author M.Takata
 */
public class SerializeStreamConverter extends BufferedStreamConverter implements StreamConverter, Serializable{
    
    private static final long serialVersionUID = -4260884667278852436L;
    
    /**
     * �ϊ���ʁB<p>
     */
    protected int convertType;
    
    /**
     * ���k���邩�ǂ����̃t���O�B<p>
     */
    protected boolean isCompress;
    
    /**
     * Serializable�I�u�W�F�N�g���X�g���[���ϊ����s���R���o�[�^�𐶐�����B<p>
     */
    public SerializeStreamConverter(){
        this(OBJECT_TO_STREAM);
    }
    
    /**
     * �w�肳�ꂽ�ϊ���ʂ̃R���o�[�^�𐶐�����B<p>
     *
     * @param type �ϊ����
     * @see #OBJECT_TO_STREAM
     * @see #STREAM_TO_OBJECT
     */
    public SerializeStreamConverter(int type){
        convertType = type;
    }
    
    /**
     * �ϊ���ʂ�ݒ肷��B<p>
     *
     * @param type �ϊ����
     * @see #getConvertType()
     * @see #OBJECT_TO_STREAM
     * @see #STREAM_TO_OBJECT
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
     * ���k���邩�ǂ�����ݒ肷��B<p>
     * true�̏ꍇ�AGZIP���k����B�f�t�H���g�́Afalse�B<br>
     * 
     * @param compress ���k����ꍇtrue
     */
    public void setCompress(boolean compress){
        isCompress = compress;
    }
    
    /**
     * ���k���邩�ǂ����𔻒肷��B<p>
     * 
     * @return true�̏ꍇ�A���k����
     */
    public boolean isCompress(){
        return isCompress;
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
        case OBJECT_TO_STREAM:
            return convertToStream(obj);
        case STREAM_TO_OBJECT:
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
     * Serializable�I�u�W�F�N�g����o�C�g�z��ɕϊ�����B<p>
     *
     * @param obj Serializable�I�u�W�F�N�g
     * @return �o�C�g�z��
     * @exception ConvertException �ϊ��Ɏ��s�����ꍇ
     */
    protected byte[] convertToByteArray(Object obj) throws ConvertException{
        final ByteArrayOutputStream baos = new ByteArrayOutputStream();
        if(obj != null){
            OutputStream os = baos;
            try{
                if(isCompress){
                    os = new GZIPOutputStream(os);
                }
                final ObjectOutputStream oos = new ObjectOutputStream(os);
                oos.writeObject((Serializable)obj);
                oos.flush();
                if(isCompress){
                    ((GZIPOutputStream)os).finish();
                }
            }catch(IOException e){
                throw new ConvertException(e);
            }finally{
            }
        }
        return baos.toByteArray();
    }
    
    /**
     * �X�g���[������Serializable�I�u�W�F�N�g�ɕϊ�����B<p>
     *
     * @param is �X�g���[��
     * @return Serializable�I�u�W�F�N�g
     * @exception ConvertException �ϊ��Ɏ��s�����ꍇ
     */
    public Object convertToObject(InputStream is) throws ConvertException{
        try{
            if(isCompress){
                is = new GZIPInputStream(is);
            }
            final ObjectInputStream ois = new ObjectInputStream(is);
            return ois.readObject();
        }catch(IOException e){
            throw new ConvertException(e);
        }catch(ClassNotFoundException e){
            throw new ConvertException(e);
        }
    }
}
