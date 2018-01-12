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
import java.util.*;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.*;
import javax.xml.transform.stream.*;
import org.w3c.dom.*;
import org.xml.sax.*;

import jp.ossc.nimbus.core.*;
import jp.ossc.nimbus.beans.dataset.*;

/**
 * �f�[�^�Z�b�g��XML�R���o�[�^�B<p>
 * 
 * @author M.Takata
 */
public class DataSetXMLConverter extends BufferedStreamConverter implements BindingStreamConverter, StreamStringConverter{
    
    private static final long serialVersionUID = -7027099857625192227L;
    
    private static final String ELEMENT_DATASET = "dataSet";
    private static final String ELEMENT_SCHEMA = "schema";
    private static final String ELEMENT_HEADER = "header";
    private static final String ELEMENT_RECORD_LIST = "recordList";
    private static final String ELEMENT_NESTED_RECORD = "nestedRecord";
    private static final String ELEMENT_NESTED_RECORD_LIST = "nestedRecordList";
    private static final String ELEMENT_RECORD = "record";
    private static final String ATTRIBUTE_NAME = "name";
    private static final String METHOD_NAME_SET_XML_VERSION = "setXmlVersion";
    private static final Class[] METHOD_ARGS_SET_XML_VERSION = new Class[]{String.class};
    
    /**
     * �f�[�^�Z�b�g��XML��\���ϊ���ʒ萔�B<p>
     */
    public static final int DATASET_TO_XML = OBJECT_TO_STREAM;
    
    /**
     * XML���f�[�^�Z�b�g��\���ϊ���ʒ萔�B<p>
     */
    public static final int XML_TO_DATASET = STREAM_TO_OBJECT;
    
    /**
     * �ϊ���ʁB<p>
     */
    protected int convertType;
    
    /**
     * �f�[�^�Z�b�g�}�b�s���O�B<p>
     */
    protected Map dataSetMap = new HashMap();
    
    /**
     * �X�L�[�}�����o�͂��邩�ǂ����̃t���O�B<p>
     * �f�[�^�Z�b�g��XML�ϊ����s���ۂɁAXML��schema�v�f���o�͂��邩�ǂ���������킷�Btrue�̏ꍇ�A�o�͂���B�f�t�H���g�́Atrue�B<br>
     */
    protected boolean isOutputSchema = true;
    
    /**
     * �f�[�^�Z�b�g��XML�ϊ����Ɏg�p����XSL�t�@�C���̃p�X�B<p>
     */
    protected String xslFilePath;
    
    /**
     * �f�[�^�Z�b�g��XML�ϊ����Ɏg�p���镶���G���R�[�f�B���O�B<p>
     */
    protected String characterEncodingToStream;
    
    /**
     * XML���f�[�^�Z�b�g�ϊ����Ɏg�p���镶���G���R�[�f�B���O�B<p>
     */
    protected String characterEncodingToObject;
    
    /**
     * �X�L�[�}���ɑ��݂��Ȃ��v�f�𖳎����邩�ǂ����̃t���O�B<p>
     * �f�t�H���g�́Afalse�ŁA�ϊ��G���[�Ƃ���B<br>
     */
    protected boolean isIgnoreUnknownElement;
    
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
     * �f�[�^�Z�b�g��XML�ϊ����s���ۂɏo�͂���XML�̃o�[�W�����B<p>
     */
    protected String xmlVersion;
    
    /**
     * �f�[�^�Z�b�g��XML�ϊ����s���R���o�[�^�𐶐�����B<p>
     */
    public DataSetXMLConverter(){
        this(DATASET_TO_XML);
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
     * @see #DATASET_TO_XML
     * @see #XML_TO_DATASET
     */
    public DataSetXMLConverter(int type){
        convertType = type;
    }
    
    /**
     * �ϊ���ʂ�ݒ肷��B<p>
     *
     * @param type �ϊ����
     * @see #getConvertType()
     * @see #DATASET_TO_XML
     * @see #XML_TO_DATASET
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
     * �f�[�^�Z�b�g���ƃf�[�^�Z�b�g�̃}�b�s���O��ݒ肷��B<p>
     * JSON���f�[�^�Z�b�g�ϊ����s���ۂɁAJSON��schema�v�f���Ȃ��ꍇ�ɁA�f�[�^�Z�b�g������f�[�^�Z�b�g����肷��̂Ɏg�p����B<br>
     * 
     * @param dataSet �f�[�^�Z�b�g
     */
    public void setDataSet(DataSet dataSet){
        if(dataSet.getName() == null){
            throw new IllegalArgumentException("DataSet name is null. dataSet=" + dataSet);
        }
        dataSetMap.put(dataSet.getName(), dataSet);
    }
    
    /**
     * �f�[�^�Z�b�g���ƃf�[�^�Z�b�g�̃}�b�s���O��ݒ肷��B<p>
     * XML���f�[�^�Z�b�g�ϊ����s���ۂɁAXML��schema�v�f���Ȃ��ꍇ�ɁA�f�[�^�Z�b�g������f�[�^�Z�b�g����肷��̂Ɏg�p����B<br>
     * 
     * @param name �f�[�^�Z�b�g��
     * @param dataSet �f�[�^�Z�b�g
     */
    public void setDataSet(String name, DataSet dataSet){
        if(dataSet.getName() == null){
            dataSet.setName(name);
        }
        dataSetMap.put(name, dataSet);
    }
    
    /**
     * �X�L�[�}�����o�͂��邩�ǂ�����ݒ肷��B<p>
     * �f�[�^�Z�b�g��XML�ϊ����s���ۂɁAXML��schema�v�f���o�͂��邩�ǂ�����ݒ肷��Btrue�̏ꍇ�A�o�͂���B�f�t�H���g�́Atrue�B<br>
     *
     * @param isOutput �X�L�[�}�����o�͂���ꍇ��true
     */
    public void setOutputSchema(boolean isOutput){
        isOutputSchema = isOutput;
    }
    
    /**
     * �X�L�[�}�����o�͂��邩�ǂ����𔻒肷��B<p>
     *
     * @return true�̏ꍇ�X�L�[�}�����o�͂���
     */
    public boolean isOutputSchema(){
        return isOutputSchema;
    }
    
    /**
     * �f�[�^�Z�b�g��XML�ϊ����Ɏg�p����XSL�t�@�C���̃p�X��ݒ肷��B<p>
     *
     * @param path XSL�t�@�C���̃p�X
     */
    public void setXSLFilePath(String path){
        xslFilePath = path;
    }
    
    /**
     * �f�[�^�Z�b�g��XML�ϊ����Ɏg�p����XSL�t�@�C���̃p�X���擾����B<p>
     *
     * @return XSL�t�@�C���̃p�X
     */
    public String getXSLFilePath(){
        return xslFilePath;
    }
    
    /**
     * �f�[�^�Z�b�g��XML�ϊ����Ɏg�p���镶���G���R�[�f�B���O��ݒ肷��B<p>
     * 
     * @param encoding �����G���R�[�f�B���O
     */
    public void setCharacterEncodingToStream(String encoding){
        characterEncodingToStream = encoding;
    }
    
    /**
     * �f�[�^�Z�b�g��XML�ϊ����Ɏg�p���镶���G���R�[�f�B���O���擾����B<p>
     * 
     * @return �����G���R�[�f�B���O
     */
    public String getCharacterEncodingToStream(){
        return characterEncodingToStream;
    }
    
    /**
     * XML���f�[�^�Z�b�g�ϊ����Ɏg�p���镶���G���R�[�f�B���O��ݒ肷��B<p>
     * 
     * @param encoding �����G���R�[�f�B���O
     */
    public void setCharacterEncodingToObject(String encoding){
        characterEncodingToObject = encoding;
    }
    
    /**
     * XML���f�[�^�Z�b�g�ϊ����Ɏg�p���镶���G���R�[�f�B���O���擾����B<p>
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
     * �X�L�[�}���ɑ��݂��Ȃ��v�f�𖳎����邩�ǂ�����ݒ肷��B<p>
     * �f�t�H���g�́Afalse�ŁA�ϊ��G���[�ƂȂ�B<br>
     * 
     * @param isIgnore true�̏ꍇ�A��������
     */
    public void setIgnoreUnknownElement(boolean isIgnore){
        isIgnoreUnknownElement = isIgnore;
    }
    
    /**
     * �X�L�[�}���ɑ��݂��Ȃ��v�f�𖳎����邩�ǂ����𔻒肷��B<p>
     * 
     * @return true�̏ꍇ�A��������
     */
    public boolean isIgnoreUnknownElement(){
        return isIgnoreUnknownElement;
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
     * �f�[�^�Z�b�g��XML�ϊ����s���ۂɏo�͂���XML�̃o�[�W������ݒ肷��B<p>
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
     * �f�[�^�Z�b�g��XML�ϊ����s���ۂɏo�͂���XML�̃o�[�W�������擾����B<p>
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
        case DATASET_TO_XML:
            return convertToStream(obj);
        case XML_TO_DATASET:
            if(obj instanceof File){
                return toDataSet((File)obj);
            }else if(obj instanceof InputStream){
                return toDataSet((InputStream)obj);
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
     * {@link DataSet}����XML�o�C�g�z��ɕϊ�����B<p>
     *
     * @param obj DataSet
     * @return XML�o�C�g�z��
     * @exception ConvertException �ϊ��Ɏ��s�����ꍇ
     */
    protected byte[] convertToByteArray(Object obj) throws ConvertException{
        if(obj instanceof DataSet){
            return toXML((DataSet)obj);
        }else{
            throw new ConvertException(
                "Invalid input type : " + obj.getClass()
            );
        }
    }
    
    /**
     * XML�X�g���[������{@link DataSet}�ɕϊ�����B<p>
     *
     * @param is XML�X�g���[��
     * @return DataSet
     * @exception ConvertException �ϊ��Ɏ��s�����ꍇ
     */
    public Object convertToObject(InputStream is) throws ConvertException{
        return toDataSet(is);
    }
    
    /**
     * �w�肳�ꂽ�I�u�W�F�N�g�֕ϊ�����B<p>
     *
     * @param is ���̓X�g���[��
     * @param returnType �ϊ��Ώۂ̃I�u�W�F�N�g
     * @return �ϊ����ꂽ�I�u�W�F�N�g
     * @throws ConvertException �ϊ��Ɏ��s�����ꍇ
     */
    public Object convertToObject(InputStream is, Object returnType)
     throws ConvertException{
        if(returnType != null && !(returnType instanceof DataSet)){
            throw new ConvertException("ReturnType is not DataSet." + returnType);
        }
        return toDataSet(is, (DataSet)returnType);
    }
    
    protected DataSet toDataSet(InputStream is) throws ConvertException{
        return toDataSet(is, null);
    }
    
    protected DataSet toDataSet(InputStream is, DataSet dataSet) throws ConvertException{
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
            Document doc = null;
            if(isSynchronizedDomParse){
                final Object lock = builder.getClass();
                synchronized(lock){
                    doc = builder.parse(inputSource);
                }
            }else{
                doc = builder.parse(inputSource);
            }
            final Element dataSetElement = doc.getDocumentElement();
            final String dataSetName = MetaData.getOptionalAttribute(
                dataSetElement,
                ATTRIBUTE_NAME
            );
            
            if(dataSet == null){
                // �f�[�^�Z�b�g����肷��
                dataSet = (DataSet)dataSetMap.get(dataSetName);
                if(dataSet != null){
                    dataSet = dataSet.cloneSchema();
                }else{
                    // �X�L�[�}��ǂݍ���
                    final Element schemaElement = MetaData.getOptionalChild(
                        dataSetElement,
                        ELEMENT_SCHEMA
                    );
                    if(schemaElement == null){
                        throw new ConvertException(
                            "Dataset is not found. name=" + dataSetName
                        );
                    }
                    dataSet = new DataSet(dataSetName);
                    final Iterator headerElements = MetaData.getChildrenByTagName(
                        schemaElement,
                        ELEMENT_HEADER
                    );
                    while(headerElements.hasNext()){
                        final Element headerElement
                             = (Element)headerElements.next();
                        final String headerName = MetaData.getOptionalAttribute(
                            headerElement,
                            ATTRIBUTE_NAME
                        );
                        final String schema
                             = MetaData.getElementContent(headerElement);
                        dataSet.setHeaderSchema(headerName, schema);
                    }
                    Iterator recListElements = MetaData.getChildrenByTagName(
                        schemaElement,
                        ELEMENT_RECORD_LIST
                    );
                    while(recListElements.hasNext()){
                        final Element recListElement
                             = (Element)recListElements.next();
                        final String recListName = MetaData.getOptionalAttribute(
                            recListElement,
                            ATTRIBUTE_NAME
                        );
                        final String schema
                             = MetaData.getElementContent(recListElement);
                        dataSet.setRecordListSchema(recListName, schema);
                    }
                    Iterator recElements = MetaData.getChildrenByTagName(
                        schemaElement,
                        ELEMENT_NESTED_RECORD
                    );
                    while(recElements.hasNext()){
                        final Element recElement
                             = (Element)recElements.next();
                        final String recName = MetaData.getUniqueAttribute(
                            recElement,
                            ATTRIBUTE_NAME
                        );
                        final String schema
                             = MetaData.getElementContent(recElement);
                        dataSet.setNestedRecordSchema(recName, schema);
                    }
                    recListElements = MetaData.getChildrenByTagName(
                        schemaElement,
                        ELEMENT_NESTED_RECORD_LIST
                    );
                    while(recListElements.hasNext()){
                        final Element recListElement
                             = (Element)recListElements.next();
                        final String recListName = MetaData.getUniqueAttribute(
                            recListElement,
                            ATTRIBUTE_NAME
                        );
                        final String schema
                             = MetaData.getElementContent(recListElement);
                        dataSet.setNestedRecordListSchema(recListName, schema);
                    }
                }
            }else{
                dataSet = dataSet.cloneSchema();
            }
            
            // �w�b�_��ǂݍ���
            final Iterator headerElements = MetaData.getChildrenByTagName(
                dataSetElement,
                ELEMENT_HEADER
            );
            while(headerElements.hasNext()){
                final Element headerElement
                     = (Element)headerElements.next();
                readHeader(dataSet, headerElement);
            }
            
            // ���R�[�h���X�g��ǂݍ���
            final Iterator recListElements = MetaData.getChildrenByTagName(
                dataSetElement,
                ELEMENT_RECORD_LIST
            );
            while(recListElements.hasNext()){
                final Element recListElement
                     = (Element)recListElements.next();
                readRecordList(dataSet, recListElement);
            }
        }catch(IOException e){
            throw new ConvertException(e);
        }catch(ParserConfigurationException e){
            throw new ConvertException(e);
        }catch(SAXException e){
            throw new ConvertException(e);
        }catch(DeploymentException e){
            throw new ConvertException(e);
        }catch(DataSetException e){
            throw new ConvertException(e);
        }
        return dataSet;
    }
    
    private DataSet readHeader(
        DataSet dataSet,
        Element headerElement
    ) throws DeploymentException{
        final String headerName = MetaData.getOptionalAttribute(
            headerElement,
            ATTRIBUTE_NAME
        );
        final Header header = dataSet.getHeader(headerName);
        if(header == null){
            if(isIgnoreUnknownElement){
                return dataSet;
            }else{
                throw new ConvertException("Unknown header : " + headerName);
            }
        }
        return readRecord(dataSet, header, headerElement);
    }
    
    private DataSet readRecord(
        DataSet dataSet,
        Record record,
        Element recordElement
    ) throws DeploymentException{
        final Iterator propElements = MetaData.getChildren(
            recordElement
        );
        RecordSchema recSchema = record.getRecordSchema();
        while(propElements.hasNext()){
            final Element propElement
                 = (Element)propElements.next();
            final String propName = propElement.getTagName();
            PropertySchema propSchema = recSchema.getPropertySchema(propName);
            if(propSchema == null && isIgnoreUnknownElement){
                continue;
            }
            if(propSchema instanceof RecordPropertySchema){
                RecordPropertySchema recPropSchema
                     = (RecordPropertySchema)propSchema;
                Element recElement = MetaData.getOptionalChild(
                    propElement,
                    ELEMENT_RECORD
                );
                if(recElement != null){
                    Record rec = dataSet.createNestedRecord(
                        recPropSchema.getRecordName()
                    );
                    if(rec != null){
                        readRecord(dataSet, rec, recElement);
                        record.setProperty(propName, rec);
                    }
                }
            }else if(propSchema instanceof RecordListPropertySchema){
                RecordListPropertySchema recListPropSchema
                     = (RecordListPropertySchema)propSchema;
                Element recListElement = MetaData.getOptionalChild(
                    propElement,
                    ELEMENT_RECORD_LIST
                );
                if(recListElement != null){
                    RecordList recList = dataSet.createNestedRecordList(
                        recListPropSchema.getRecordListName()
                    );
                    if(recList != null){
                        readRecordList(dataSet, recList, recListElement);
                        record.setProperty(propName, recList);
                    }
                }
            }else{
                String val = MetaData.getElementContent(propElement);
                record.setParseProperty(
                    propName,
                    val == null ? "" : val
                );
            }
        }
        return dataSet;
    }
    
    private DataSet readRecordList(
        DataSet dataSet,
        Element recListElement
    ) throws DeploymentException{
        final String recListName = MetaData.getOptionalAttribute(
            recListElement,
            ATTRIBUTE_NAME
        );
        final RecordList recList
             = dataSet.getRecordList(recListName);
        if(recList == null){
            if(isIgnoreUnknownElement){
                return dataSet;
            }else{
                throw new ConvertException("Unknown recordList : " + recListName);
            }
        }
        return readRecordList(dataSet, recList, recListElement);
    }
    
    private DataSet readRecordList(
        DataSet dataSet,
        RecordList recList,
        Element recListElement
    ) throws DeploymentException{
        final Iterator recordElements = MetaData.getChildrenByTagName(
            recListElement,
            ELEMENT_RECORD
        );
        while(recordElements.hasNext()){
            final Element recordElement
                 = (Element)recordElements.next();
            final Record record = recList.createRecord();
            readRecord(dataSet, record, recordElement);
            recList.addRecord(record);
        }
        return dataSet;
    }
    
    protected DataSet toDataSet(File file) throws ConvertException{
        try{
            return toDataSet(new FileInputStream(file));
        }catch(IOException e){
            throw new ConvertException(e);
        }
    }
    
    protected byte[] toXML(DataSet dataSet) throws ConvertException{
        byte[] result = null;
        try{
            DocumentBuilderFactory dbFactory = null;
            if(documentBuilderFactoryClass == null){
                dbFactory = DocumentBuilderFactory.newInstance();
            }else{
                try{
                    dbFactory = (DocumentBuilderFactory)Class.forName(
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
            final DocumentBuilder docBuilder = dbFactory.newDocumentBuilder();
            final Document document = docBuilder.newDocument();
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
            final Element dataSetElement
                 = document.createElement(ELEMENT_DATASET);
            if(dataSet.getName() != null){
                dataSetElement.setAttribute(ATTRIBUTE_NAME, dataSet.getName());
            }
            document.appendChild(dataSetElement);
            
            // �X�L�[�}�o��
            if(isOutputSchema){
                final Element schemaElement
                     = document.createElement(ELEMENT_SCHEMA);
                dataSetElement.appendChild(schemaElement);
                
                // �w�b�_�̃X�L�[�}�o��
                final String[] headerNames = dataSet.getHeaderNames();
                for(int i = 0; i < headerNames.length; i++){
                    final Header header = dataSet.getHeader(headerNames[i]);
                    final Element headerElement
                         = document.createElement(ELEMENT_HEADER);
                    if(headerNames[i] != null){
                        headerElement.setAttribute(ATTRIBUTE_NAME, headerNames[i]);
                    }
                    schemaElement.appendChild(headerElement);
                    final Text schemaNode
                         = document.createTextNode(header.getSchema());
                    headerElement.appendChild(schemaNode);
                }
                
                // ���R�[�h���X�g�̃X�L�[�}�o��
                String[] recListNames = dataSet.getRecordListNames();
                for(int i = 0; i < recListNames.length; i++){
                    final RecordList recList
                         = dataSet.getRecordList(recListNames[i]);
                    final Element recListElement
                         = document.createElement(ELEMENT_RECORD_LIST);
                    if(recListNames[i] != null){
                        recListElement.setAttribute(
                            ATTRIBUTE_NAME,
                            recListNames[i]
                        );
                    }
                    schemaElement.appendChild(recListElement);
                    final Text schemaNode
                         = document.createTextNode(recList.getSchema());
                    recListElement.appendChild(schemaNode);
                }
                
                // �l�X�g���R�[�h�̃X�L�[�}�o��
                String[] recNames = dataSet.getNestedRecordSchemaNames();
                for(int i = 0; i < recNames.length; i++){
                    final RecordSchema recSchema
                         = dataSet.getNestedRecordSchema(recNames[i]);
                    final Element recElement
                         = document.createElement(ELEMENT_NESTED_RECORD);
                    recElement.setAttribute(
                        ATTRIBUTE_NAME,
                        recNames[i]
                    );
                    schemaElement.appendChild(recElement);
                    final Text schemaNode
                         = document.createTextNode(recSchema.getSchema());
                    recElement.appendChild(schemaNode);
                }
                
                // �l�X�g���R�[�h���X�g�̃X�L�[�}�o��
                recListNames = dataSet.getNestedRecordListSchemaNames();
                for(int i = 0; i < recListNames.length; i++){
                    final RecordSchema recSchema
                         = dataSet.getNestedRecordListSchema(recListNames[i]);
                    final Element recListElement
                         = document.createElement(ELEMENT_NESTED_RECORD_LIST);
                    recListElement.setAttribute(
                        ATTRIBUTE_NAME,
                        recListNames[i]
                    );
                    schemaElement.appendChild(recListElement);
                    final Text schemaNode
                         = document.createTextNode(recSchema.getSchema());
                    recListElement.appendChild(schemaNode);
                }
            }
            
            // �w�b�_�o��
            final String[] headerNames = dataSet.getHeaderNames();
            for(int i = 0; i < headerNames.length; i++){
                final Header header = dataSet.getHeader(headerNames[i]);
                appendRecord(
                    dataSet,
                    document,
                    dataSetElement,
                    header,
                    ELEMENT_HEADER
                );
            }
            
            // ���R�[�h���X�g�o��
            final String[] recListNames = dataSet.getRecordListNames();
            for(int i = 0; i < recListNames.length; i++){
                final RecordList recList
                     = dataSet.getRecordList(recListNames[i]);
                appendRecordList(
                    dataSet,
                    document,
                    dataSetElement,
                    recList,
                    ELEMENT_RECORD_LIST
                );
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
        }catch(ParserConfigurationException e){
            throw new ConvertException(e);
        }catch(TransformerConfigurationException e){
            throw new ConvertException(e);
        }catch(TransformerException e){
            throw new ConvertException(e);
        }catch(DataSetException e){
            throw new ConvertException(e);
        }
        return result;
    }
    
    private Element appendRecord(
        DataSet dataSet,
        Document document,
        Element parent,
        Record record,
        String elementName
    ){
        final Element recordElement
             = document.createElement(elementName);
        if(record instanceof Header){
            String headerName = ((Header)record).getName();
            if(headerName != null){
                recordElement.setAttribute(ATTRIBUTE_NAME, headerName);
            }
        }
        parent.appendChild(recordElement);
        final RecordSchema recSchema = record.getRecordSchema();
        for(int j = 0, jmax = recSchema.getPropertySize();
                j < jmax; j++){
            PropertySchema propSchema = recSchema.getPropertySchema(j);
            final Element propElement
                 = document.createElement(propSchema.getName());
            if(propSchema instanceof RecordPropertySchema){
                Record rec
                     = (Record)record.getProperty(propSchema.getName());
                if(rec != null){
                    appendRecord(
                        dataSet,
                        document,
                        propElement,
                        rec,
                        ELEMENT_RECORD
                    );
                    recordElement.appendChild(propElement);
                }
            }else if(propSchema instanceof RecordListPropertySchema){
                RecordList recList
                     = (RecordList)record.getProperty(propSchema.getName());
                if(recList != null && recList.size() != 0){
                    appendRecordList(
                        dataSet,
                        document,
                        propElement,
                        recList,
                        ELEMENT_RECORD_LIST
                    );
                    recordElement.appendChild(propElement);
                }
            }else{
                final Object prop
                     = record.getFormatProperty(propSchema.getName());
                if(prop != null){
                    final Text valNode
                         = document.createTextNode(prop.toString());
                    propElement.appendChild(valNode);
                    recordElement.appendChild(propElement);
                }
            }
        }
        return parent;
    }
    
    private Element appendRecordList(
        DataSet dataSet,
        Document document,
        Element parent,
        RecordList recList,
        String elementName
    ){
        if(recList == null || recList.size() == 0){
            return parent;
        }
        final Element recListElement = document.createElement(elementName);
        if(recList.getName() != null){
            recListElement.setAttribute(ATTRIBUTE_NAME, recList.getName());
        }
        parent.appendChild(recListElement);
        for(int j = 0, jmax = recList.size(); j < jmax; j++){
            final Record record = recList.getRecord(j);
            appendRecord(
                dataSet,
                document,
                recListElement,
                record,
                ELEMENT_RECORD
            );
        }
        return parent;
    }
}
