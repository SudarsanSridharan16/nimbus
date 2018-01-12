/*
 * This software is distributed under following license based on modified BSD
 * style license.
 * ----------------------------------------------------------------------
 * 
 * Copyright 2008 The Nimbus Project. All rights reserved.
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

import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import jp.ossc.nimbus.core.NimbusClassLoader;
import jp.ossc.nimbus.beans.dataset.DataSet;
import jp.ossc.nimbus.beans.dataset.Header;
import jp.ossc.nimbus.beans.dataset.PropertySchema;
import jp.ossc.nimbus.beans.dataset.Record;
import jp.ossc.nimbus.beans.dataset.RecordList;
import jp.ossc.nimbus.beans.dataset.RecordPropertySchema;
import jp.ossc.nimbus.beans.dataset.RecordListPropertySchema;
import jp.ossc.nimbus.beans.dataset.RecordSchema;
import jp.ossc.nimbus.beans.dataset.XpathPropertySchema;

/**
 * {@link DataSet}��XPath�ŕ\�����ꂽXML�f�[�^�Ƃ̕ϊ����s��{@link Converter}�B
 * <p>
 *     <ul>
 *         <li>�v���p�e�B�X�L�[�}��{@link XpathPropertySchema}�ł���v���p�e�B�ɑ΂��ĕϊ����s���B</li>
 *         <li>XPath�́AXML�m�[�h�܂���XML�m�[�h���X�g��Ԃ��悤�ɐݒ肵�Ȃ���΂Ȃ�Ȃ��B</li>
 *     </ul>
 * </p>
 * @author T.Okada
 */
public class DataSetXpathConverter implements BindingStreamConverter, StreamStringConverter, Cloneable{

    protected int convertType;
    
    protected String characterEncodingToStream;
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
     * DocumentBuilderFactory�̎����N���X��ݒ肷��B<p>
     *
     * @param clazz DocumentBuilderFactory�̎����N���X
     */
    public void setDocumentBuilderFactoryClassName(String clazz){
        documentBuilderFactoryClass = clazz;
    }
    
    public void setConvertType(int convertType) {
        this.convertType = convertType;
    }
    
    public void setCharacterEncodingToObject(String characterEncodingToObject) {
        this.characterEncodingToObject = characterEncodingToObject;
    }
    public String getCharacterEncodingToObject(){
        return characterEncodingToObject;
    }
    
    public void setCharacterEncodingToStream(String characterEncodingToStream) {
        this.characterEncodingToStream = characterEncodingToStream;
    }
    public String getCharacterEncodingToStream(){
        return characterEncodingToStream;
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
     * �w�肳�ꂽ{@link DataSet}�T�u�N���X�̃I�u�W�F�N�g�֕ϊ�����B
     * @param inputStream ���̓X�g���[��
     * @param returnObject �ϊ��Ώۂ�{@link DataSet}�T�u�N���X
     * @return �w�肵���f�[�^�Z�b�g�T�u�N���X�ɕϊ����ꂽ�I�u�W�F�N�g
     * @throws ConvertException �ϊ��Ɏ��s�����ꍇ
     */
    public Object convertToObject(InputStream inputStream, Object returnObject) throws ConvertException {
        DataSet result = null;
        
        // �o��DataSet����
        if(returnObject != null) {
            if(DataSet.class.isAssignableFrom(returnObject.getClass())) {
                result = ((DataSet)returnObject).cloneDataSet();
            }else {
                throw new ConvertException("A return object is not a sub-class of DataSet.");
            }
        }else {
            throw new ConvertException("A return object is not specified.");
        }
        
        Document document = parseXml(inputStream);
        
        validateXml(document);
        
        // Header�v�f���o
        Iterator headers = result.getHeaderMap().values().iterator();
        while(headers.hasNext()) {
            Header header = (Header)headers.next();
            createRecord(document, result, header, header.getRecordSchema());
        }
        
        // RecordList�v�f���o
        Iterator recordLists = result.getRecordListMap().values().iterator();
        while(recordLists.hasNext()) {
            RecordList recordList = (RecordList)recordLists.next();
            createRecord(document, result, recordList, recordList.getRecordSchema());
        }
        
        return result;
    }
    
    private void createRecord(Document document, DataSet dataSet, Object target, RecordSchema recordSchema) {
        PropertySchema[] propertySchemata = recordSchema.getPropertySchemata();
        
        for(int i=0; i<propertySchemata.length; i++) {
            if(propertySchemata[i] instanceof XpathPropertySchema) {
                // PropertySchema����XPath�擾
                XpathPropertySchema xmlBindingPropertySchema = (XpathPropertySchema)propertySchemata[i];
                XPathExpression expression = xmlBindingPropertySchema.getXpathExpression();
                
                // XPath�ɂ��XML�v�f�𒊏o
                NodeList nodeList = null;
                try {
                    nodeList = (NodeList)expression.evaluate(document, XPathConstants.NODESET);
                } catch (XPathExpressionException e) {
                    throw new ConvertException("The converter failed to evaluate a XML. ", e);
                }
                
                // DataSet�֕ϊ�
                int length = nodeList.getLength();
                if(target instanceof Record) {
                    if(length > 0) {
                        Object nodeValue = nodeList.item(0).getNodeValue();
                        ((Record)target).setParseProperty(xmlBindingPropertySchema.getName(), nodeValue);
                    }
                }else if(target instanceof RecordList) {
                    RecordList targetRecordList = (RecordList)target;
                    int offset = length - targetRecordList.size();
                    if(offset>0) {
                        for(int j=0; j<offset; j++) {
                            Record record = targetRecordList.createRecord();
                            targetRecordList.addRecord(record);
                        }
                    }
                    for(int j=0; j<length; j++) {
                        Object nodeValue = nodeList.item(j).getNodeValue();
                        Record record = targetRecordList.getRecord(j);
                        record.setParseProperty(xmlBindingPropertySchema.getName(), nodeValue);
                    }
                }
            }else if(propertySchemata[i] instanceof RecordPropertySchema) {
                RecordPropertySchema recordPropertySchema = (RecordPropertySchema)propertySchemata[i];
                RecordSchema nestedRecordSchema = dataSet.getNestedRecordSchema(recordPropertySchema.getName());
                Record nestedRecord = dataSet.createNestedRecord(recordPropertySchema.getRecordName());
                createRecord(document, dataSet, target, nestedRecordSchema);
                ((Record)target).setProperty(recordPropertySchema.getName(), nestedRecord);
            }else if(propertySchemata[i] instanceof RecordListPropertySchema) {
                RecordListPropertySchema recordListPropertySchema = (RecordListPropertySchema)propertySchemata[i];
                RecordSchema nestedRecordSchema = dataSet.getNestedRecordListSchema(recordListPropertySchema.getRecordListName());
                RecordList nestedRecordList = dataSet.createNestedRecordList(recordListPropertySchema.getRecordListName());
                createRecord(document, dataSet, nestedRecordList, nestedRecordSchema);
                ((Record)target).setProperty(recordListPropertySchema.getName(), nestedRecordList);
            }
        }
    }

    public Object convertToObject(InputStream inputStream) throws ConvertException {
        return convertToObject(inputStream, null);
    }

    public InputStream convertToStream(Object object) throws ConvertException {
        // TODO: implement
        throw new IllegalAccessError("The convertToStream method is not supported yet.");
    }

    public Object convert(Object object) throws ConvertException {
        if(convertType == ReversibleConverter.POSITIVE_CONVERT) {
            return convertToStream(object);
        }else if(convertType == ReversibleConverter.REVERSE_CONVERT) {
            return convertToObject((InputStream)object);
        }
        return null;
    }
    
    /**
     * ���̓X�g���[�����p�[�X����{@link Document}�I�u�W�F�N�g�𐶐�����B
     * @param inputStream ���̓X�g���[��
     * @return �p�[�X����{@link Document}�I�u�W�F�N�g
     * @exception ConvertException �p�[�X�Ɏ��s�����ꍇ
     */
    protected Document parseXml(InputStream inputStream) throws ConvertException {
        DocumentBuilderFactory factory = null;
        if(documentBuilderFactoryClass == null){
            factory = DocumentBuilderFactory.newInstance();
        }else{
            try{
                factory = (DocumentBuilderFactory)Class.forName(
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
        DocumentBuilder builder = null;
        try {
            builder = factory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            throw new ConvertException("XML document builder could not be instanced.", e);
        }
        Document document = null;
        try {
            if(isSynchronizedDomParse){
                final Object lock = builder.getClass();
                synchronized(lock){
                    document = builder.parse(inputStream);
                }
            }else{
                document = builder.parse(inputStream);
            }
        } catch (SAXException e) {
            throw new ConvertException("The XML could not be parsed.", e);
        } catch (IOException e) {
            throw new ConvertException("The XML could not be parsed.", e);
        }
        return document;
    }
    
    /**
     * �ϊ��Ώۂ�XML�����؂���B
     * @param document �ϊ��Ώ�XML��{@link Document}�I�u�W�F�N�g�B
     * @exception ConvertException XML���s���ł������ꍇ
     */
    protected void validateXml(Document document) throws ConvertException {
    }

}
