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
import java.lang.reflect.*;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.*;
import javax.xml.transform.stream.*;
import org.w3c.dom.*;
import org.xml.sax.*;

import jp.ossc.nimbus.core.*;

/**
 * DOM��XML�R���o�[�^�B<p>
 * 
 * @author M.Takata
 */
public class DOMXMLConverter extends BufferedStreamConverter implements StreamStringConverter, Serializable{
    
    private static final long serialVersionUID = -7589887444564629172L;
    
    private static final String METHOD_NAME_SET_XML_VERSION = "setXmlVersion";
    private static final Class[] METHOD_ARGS_SET_XML_VERSION = new Class[]{String.class};
    
    /**
     * DOM��XML��\���ϊ���ʒ萔�B<p>
     */
    public static final int DOM_TO_XML = OBJECT_TO_STREAM;
    
    /**
     * XML��DOM��\���ϊ���ʒ萔�B<p>
     */
    public static final int XML_TO_DOM = STREAM_TO_OBJECT;
    
    /**
     * �ϊ���ʁB<p>
     */
    protected int convertType;
    
    /**
     * DOM��XML�ϊ����Ɏg�p����XSL�t�@�C���̃p�X�B<p>
     */
    protected String xslFilePath;
    
    /**
     * DOM��XML�ϊ����Ɏg�p���镶���G���R�[�f�B���O�B<p>
     */
    protected String characterEncodingToStream;
    
    /**
     * XML��DOM�ϊ����Ɏg�p���镶���G���R�[�f�B���O�B<p>
     */
    protected String characterEncodingToObject;
    
    /**
     * DOM�̃p�[�X�𓯊��I�ɍs�����ǂ����̃t���O�B<p>
     * �f�t�H���g�́Afalse�ŁA�������Ȃ��B<br>
     */
    protected boolean isSynchronizedDomParse;
    
    /**
     * DocumentBuilderFactory�̎����N���X�B<p>
     */
    protected String documentBuilderFactoryClass;
    
    /**
     * DOM��XML�ϊ����s���ۂɏo�͂���XML�̃o�[�W�����B<p>
     */
    protected String xmlVersion;
    
    /**
     * DOM��XML�ϊ����s���R���o�[�^�𐶐�����B<p>
     */
    public DOMXMLConverter(){
        this(DOM_TO_XML);
    }
    
    /**
     * DocumentBuilderFactory�̎����N���X��ݒ肷��B<p>
     *
     * @param clazz DocumentBuilderFactory�̎����N���X
     */
    public void setDocumentBuilderFactoryClassName(String clazz){
        documentBuilderFactoryClass = clazz;
    }
    
    /**
     * �w�肳�ꂽ�ϊ���ʂ̃R���o�[�^�𐶐�����B<p>
     *
     * @param type �ϊ����
     * @see #DOM_TO_XML
     * @see #XML_TO_DOM
     */
    public DOMXMLConverter(int type){
        convertType = type;
    }
    
    /**
     * �ϊ���ʂ�ݒ肷��B<p>
     *
     * @param type �ϊ����
     * @see #getConvertType()
     * @see #DOM_TO_XML
     * @see #XML_TO_DOM
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
     * DOM��XML�ϊ����Ɏg�p����XSL�t�@�C���̃p�X��ݒ肷��B<p>
     *
     * @param path XSL�t�@�C���̃p�X
     */
    public void setXSLFilePath(String path){
        xslFilePath = path;
    }
    
    /**
     * DOM��XML�ϊ����Ɏg�p����XSL�t�@�C���̃p�X���擾����B<p>
     *
     * @return XSL�t�@�C���̃p�X
     */
    public String getXSLFilePath(){
        return xslFilePath;
    }
    
    /**
     * DOM��XML�ϊ����Ɏg�p���镶���G���R�[�f�B���O��ݒ肷��B<p>
     * 
     * @param encoding �����G���R�[�f�B���O
     */
    public void setCharacterEncodingToStream(String encoding){
        characterEncodingToStream = encoding;
    }
    
    /**
     * DOM��XML�ϊ����Ɏg�p���镶���G���R�[�f�B���O���擾����B<p>
     * 
     * @return �����G���R�[�f�B���O
     */
    public String getCharacterEncodingToStream(){
        return characterEncodingToStream;
    }
    
    /**
     * XML��DOM�ϊ����Ɏg�p���镶���G���R�[�f�B���O��ݒ肷��B<p>
     * 
     * @param encoding �����G���R�[�f�B���O
     */
    public void setCharacterEncodingToObject(String encoding){
        characterEncodingToObject = encoding;
    }
    
    /**
     * XML��DOM�ϊ����Ɏg�p���镶���G���R�[�f�B���O���擾����B<p>
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
     * DOM�̃p�[�X�𓯊��I�ɍs�����ǂ�����ݒ肷��B<p>
     * �f�t�H���g�́Afalse�ŁA�������Ȃ��B<br>
     * 
     * @param isSync ��������ꍇ�́Atrue
     */
    public void setSynchronizedDomParse(boolean isSync){
        isSynchronizedDomParse = isSync;
    }
    
    /**
     * DOM�̃p�[�X�𓯊��I�ɍs�����ǂ����𔻒肷��B<p>
     * 
     * @return true�̏ꍇ�A��������
     */
    public boolean isSynchronizedDomParse(){
        return isSynchronizedDomParse;
    }
    
    /**
     * DOM��XML�ϊ����s���ۂɏo�͂���XML�̃o�[�W������ݒ肷��B<p>
     * �f�t�H���g�́Anull�ŁA�p�[�T�[�̃f�t�H���g�l�ɏ]���B<br>
     *
     * @param version XML�̃o�[�W����
     */
    public void setXmlVersion(String version) throws IllegalArgumentException{
        try{
            Method method = Document.class.getMethod(METHOD_NAME_SET_XML_VERSION, METHOD_ARGS_SET_XML_VERSION);
        }catch(NoSuchMethodException e){
            throw new IllegalArgumentException("DOM version is old. Not support to change xml version.");
        }
        xmlVersion = version;
    }
    
    /**
     * DOM��XML�ϊ����s���ۂɏo�͂���XML�̃o�[�W�������擾����B<p>
     *
     * @return XML�̃o�[�W����
     */
    public String getXmlVersion(){
        return xmlVersion;
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
        case DOM_TO_XML:
            return convertToStream(obj);
        case XML_TO_DOM:
            if(obj instanceof File){
                return toDOM((File)obj);
            }else if(obj instanceof InputStream){
                return toDOM((InputStream)obj);
            }else{
                throw new ConvertException(
                    "Invalid input type : " + obj.getClass()
                );
            }
        default:
            throw new ConvertException(
                "Invalid convert type : " + convertType
            );
        }
    }
    
    /**
     * org.w3c.dom.Document����XML�o�C�g�z��ɕϊ�����B<p>
     *
     * @param obj org.w3c.dom.Document
     * @return XML�o�C�g�z��
     * @exception ConvertException �ϊ��Ɏ��s�����ꍇ
     */
    protected byte[] convertToByteArray(Object obj) throws ConvertException{
        if(obj instanceof Document){
            return toXML((Document)obj);
        }else{
            throw new ConvertException(
                "Invalid input type : " + obj.getClass()
            );
        }
    }
    
    /**
     * XML�X�g���[������org.w3c.dom.Document�ɕϊ�����B<p>
     *
     * @param is XML�X�g���[��
     * @return org.w3c.dom.Document
     * @exception ConvertException �ϊ��Ɏ��s�����ꍇ
     */
    public Object convertToObject(InputStream is) throws ConvertException{
        return toDOM(is);
    }
    
    protected Document toDOM(InputStream is) throws ConvertException{
        Document doc = null;
        try{
            final InputSource inputSource = new InputSource(is);
            if(characterEncodingToObject != null){
                String encoding = (String)DOMHTMLConverter.IANA2JAVA_ENCODING_MAP
                    .get(characterEncodingToObject);
                if(encoding == null){
                    encoding = characterEncodingToObject;
                }
                inputSource.setEncoding(encoding);
            }
            DocumentBuilderFactory domFactory = null;
            if(documentBuilderFactoryClass == null){
                domFactory = DocumentBuilderFactory.newInstance();
            }else{
                try{
                    domFactory = (DocumentBuilderFactory)Class.forName(
                        documentBuilderFactoryClass,
                        true,
                        NimbusClassLoader.getInstance()
                    ).newInstance();
                }catch(InstantiationException e){
                    throw new ConvertException(e);
                }catch(IllegalAccessException e){
                    throw new ConvertException(e);
                }catch(ClassNotFoundException e){
                    throw new ConvertException(e);
                }
            }
            final DocumentBuilder builder = domFactory.newDocumentBuilder();
            if(isSynchronizedDomParse){
                final Object lock = builder.getClass();
                synchronized(lock){
                    doc = builder.parse(inputSource);
                }
            }else{
                doc = builder.parse(inputSource);
            }
        }catch(IOException e){
            throw new ConvertException(e);
        }catch(ParserConfigurationException e){
            throw new ConvertException(e);
        }catch(SAXException e){
            throw new ConvertException(e);
        }
        return doc;
    }
    
    protected Document toDOM(File file) throws ConvertException{
        try{
            return toDOM(new FileInputStream(file));
        }catch(IOException e){
            throw new ConvertException(e);
        }
    }
    
    protected byte[] toXML(Document document) throws ConvertException{
        byte[] result = null;
        try{
            if(xmlVersion != null){
                try{
                    Method method = Document.class.getMethod(METHOD_NAME_SET_XML_VERSION, METHOD_ARGS_SET_XML_VERSION);
                    method.invoke(document, new Object[]{xmlVersion});
                }catch(NoSuchMethodException e){
                    throw new ConvertException("DOM version is old. Not support to change xml version.", e);
                }catch(IllegalAccessException e){
                    throw new ConvertException("DOM version is old. Not support to change xml version.", e);
                }catch(InvocationTargetException e){
                    throw new ConvertException(e);
                }
            }
            
            final TransformerFactory tFactory
                 = TransformerFactory.newInstance();
            Transformer transformer = null;
            if(xslFilePath == null){
                transformer = tFactory.newTransformer();
            }else{
                transformer = tFactory.newTransformer(
                    new StreamSource(xslFilePath)
                );
            }
            if(characterEncodingToStream != null){
                String encoding = (String)DOMHTMLConverter.IANA2JAVA_ENCODING_MAP
                    .get(characterEncodingToStream);
                if(encoding == null){
                    encoding = characterEncodingToStream;
                }
                transformer.setOutputProperty(
                    OutputKeys.ENCODING,
                    encoding
                );
            }
            final ByteArrayOutputStream baos = new ByteArrayOutputStream();
            transformer.transform(
                new DOMSource(document),
                new StreamResult(baos)
            );
            result = baos.toByteArray();
        }catch(TransformerConfigurationException e){
            throw new ConvertException(e);
        }catch(TransformerException e){
            throw new ConvertException(e);
        }
        return result;
    }
}
