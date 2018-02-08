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

import java.io.*;
import java.util.*;

import jp.ossc.nimbus.io.*;
import jp.ossc.nimbus.beans.dataset.*;

/**
 * ���R�[�h���X�g��CSV�R���o�[�^�B<p>
 * �A���A�l�X�g�������R�[�h���X�g�́A�T�|�[�g���Ȃ��B<br>
 * 
 * @author M.Takata
 */
public class RecordListCSVConverter implements BindingStreamConverter, StreamStringConverter, Cloneable{
    
    /**
     * ���R�[�h���X�g��CSV��\���ϊ���ʒ萔�B<p>
     */
    public static final int RECORDLIST_TO_CSV = OBJECT_TO_STREAM;
    
    /**
     * CSV�����R�[�h���X�g��\���ϊ���ʒ萔�B<p>
     */
    public static final int CSV_TO_RECORDLIST = STREAM_TO_OBJECT;
    
    /**
     * �ϊ���ʁB<p>
     */
    protected int convertType;
    
    /**
     * ���R�[�h���X�g��CSV�ϊ����Ɏg�p���镶���G���R�[�f�B���O�B<p>
     */
    protected String characterEncodingToStream;
    
    /**
     * CSV�����R�[�h���X�g�ϊ����Ɏg�p���镶���G���R�[�f�B���O�B<p>
     */
    protected String characterEncodingToObject;
    
    /**
     * �X�L�[�}��`���肩�ǂ����̃t���O�B<p>
     * ���R�[�h���X�g��CSV�ϊ����s���ۂɁACSV�ɃX�L�[�}��`�����邩�ǂ���������킷�Btrue�̏ꍇ�A�X�L�[�}��`����B�f�t�H���g�́Afalse�B<br>
     */
    protected boolean isExistsSchema;
    
    /**
     * CSV�w�b�_���肩�ǂ����̃t���O�B<p>
     * ���R�[�h���X�g��CSV�ϊ����s���ۂɁACSV�Ƀw�b�_�����邩�ǂ���������킷�Btrue�̏ꍇ�A�w�b�_����B�f�t�H���g�́Afalse�B<br>
     */
    protected boolean isExistsHeader;
    
    /**
     * �X�L�[�}���ɑ��݂��Ȃ��v���p�e�B�𖳎����邩�ǂ����̃t���O�B<p>
     * �f�t�H���g�́Afalse�ŁA�ϊ��G���[�Ƃ���B<br>
     */
    protected boolean isIgnoreUnknownProperty;
    
    protected char separator = CSVWriter.DEFAULT_SEPARATOR;
    protected char separatorEscape = CSVWriter.DEFAULT_SEPARATOR_ESCAPE;
    protected char enclosure = CSVWriter.DEFAULT_ENCLOSURE;
    protected boolean isEnclose;
    protected String lineSeparator = CSVWriter.DEFAULT_LINE_SEPARATOR;
    protected String nullValue;
    
    protected boolean isIgnoreEmptyLine;
    protected boolean isIgnoreLineEndSeparator;
    protected CSVReader csvReader;
    protected CSVWriter csvWriter;
    
    /**
     * ���R�[�h���X�g��CSV�ϊ����s���R���o�[�^�𐶐�����B<p>
     */
    public RecordListCSVConverter(){
        this(RECORDLIST_TO_CSV);
    }
    
    /**
     * �w�肳�ꂽ�ϊ���ʂ̃R���o�[�^�𐶐�����B<p>
     *
     * @param type �ϊ����
     * @see #RECORDLIST_TO_CSV
     * @see #CSV_TO_RECORDLIST
     */
    public RecordListCSVConverter(int type){
        convertType = type;
    }
    
    /**
     * �ϊ���ʂ�ݒ肷��B<p>
     *
     * @param type �ϊ����
     * @see #RECORDLIST_TO_CSV
     * @see #CSV_TO_RECORDLIST
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
     * ���R�[�h���X�g��CSV�ϊ����Ɏg�p���镶���G���R�[�f�B���O��ݒ肷��B<p>
     * 
     * @param encoding �����G���R�[�f�B���O
     */
    public void setCharacterEncodingToStream(String encoding){
        characterEncodingToStream = encoding;
    }
    
    /**
     * ���R�[�h���X�g��CSV�ϊ����Ɏg�p���镶���G���R�[�f�B���O���擾����B<p>
     * 
     * @return �����G���R�[�f�B���O
     */
    public String getCharacterEncodingToStream(){
        return characterEncodingToStream;
    }
    
    /**
     * CSV�����R�[�h���X�g�ϊ����Ɏg�p���镶���G���R�[�f�B���O��ݒ肷��B<p>
     * 
     * @param encoding �����G���R�[�f�B���O
     */
    public void setCharacterEncodingToObject(String encoding){
        characterEncodingToObject = encoding;
    }
    
    /**
     * CSV�����R�[�h���X�g�ϊ����Ɏg�p���镶���G���R�[�f�B���O���擾����B<p>
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
     * ���R�[�h���X�g��CSV�ϊ����s���ۂɁACSV�ɃX�L�[�}��`�����邩�ǂ�����ݒ肷��B<p>
     * �f�t�H���g�́Afalse�B<br>
     *
     * @param isExists �X�L�[�}��`������ꍇ��true
     */
    public void setExistsSchema(boolean isExists){
        isExistsSchema = isExists;
    }
    
    /**
     * ���R�[�h���X�g��CSV�ϊ����s���ۂɁACSV�ɃX�L�[�}��`�����邩�ǂ����𔻒肷��B<p>
     *
     * @return true�̏ꍇ�X�L�[�}��`������
     */
    public boolean isExistsSchema(){
        return isExistsSchema;
    }
    
    /**
     * ���R�[�h���X�g��CSV�ϊ����s���ۂɁACSV�Ƀw�b�_�����邩�ǂ�����ݒ肷��B<p>
     * �f�t�H���g�́Afalse�B<br>
     *
     * @param isExists �w�b�_������ꍇ��true
     */
    public void setExistsHeader(boolean isExists){
        isExistsHeader = isExists;
    }
    
    /**
     * ���R�[�h���X�g��CSV�ϊ����s���ۂɁACSV�Ƀw�b�_�����邩�ǂ����𔻒肷��B<p>
     *
     * @return true�̏ꍇ�w�b�_������
     */
    public boolean isExistsHeader(){
        return isExistsHeader;
    }
    
    /**
     * �X�L�[�}���ɑ��݂��Ȃ��v���p�e�B�𖳎����邩�ǂ�����ݒ肷��B<p>
     * �f�t�H���g�́Afalse�ŁA�ϊ��G���[�ƂȂ�B<br>
     * 
     * @param isIgnore true�̏ꍇ�A��������
     */
    public void setIgnoreUnknownProperty(boolean isIgnore){
        isIgnoreUnknownProperty = isIgnore;
    }
    
    /**
     * �X�L�[�}���ɑ��݂��Ȃ��v���p�e�B�𖳎����邩�ǂ����𔻒肷��B<p>
     * 
     * @return true�̏ꍇ�A��������
     */
    public boolean isIgnoreUnknownProperty(){
        return isIgnoreUnknownProperty;
    }
    
    /**
     * �Z�p���[�^��ݒ肷��B<p>
     *
     * @param separator �Z�p���[�^
     */
    public void setSeparator(char separator){
        this.separator = separator;
    }
    
    /**
     * �Z�p���[�^���擾����B<p>
     *
     * @return �Z�p���[�^
     */
    public char getSeparator(){
         return separator;
    }
    
    /**
     * �Z�p���[�^�̃G�X�P�[�v������ݒ肷��B<p>
     *
     * @param escape �G�X�P�[�v����
     */
    public void setSeparatorEscape(char escape){
        separatorEscape = escape;
    }
    
    /**
     * �Z�p���[�^�̃G�X�P�[�v�������擾����B<p>
     *
     * @return �G�X�P�[�v����
     */
    public char getSeparatorEscape(){
         return separatorEscape;
    }
    
    /**
     * ���s�Z�p���[�^��ݒ肷��B<p>
     *
     * @param separator ���s�Z�p���[�^
     */
    public void setLineSeparator(String separator){
        this.lineSeparator = separator;
    }
    
    /**
     * ���s�Z�p���[�^���擾����B<p>
     *
     * @return ���s�Z�p���[�^
     */
    public String getLineSeparator(){
         return lineSeparator;
    }
    
    /**
     * �͂ݕ�����ݒ肷��B<p>
     *
     * @param enclosure �͂ݕ���
     */
    public void setEnclosure(char enclosure){
        this.enclosure = enclosure;
    }
    
    /**
     * �͂ݕ������擾����B<p>
     *
     * @return �͂ݕ���
     */
    public char getEnclosure(){
         return enclosure;
    }
    
    /**
     * CSV�̗v�f���͂ݕ����ň͂ނ��ǂ�����ݒ肷��B<p>
     * �f�t�H���g�́Afalse�ň͂܂Ȃ��B<br>
     *
     * @param isEnclose �͂ݕ����ň͂ޏꍇtrue
     */
    public void setEnclose(boolean isEnclose){
        this.isEnclose = isEnclose;
    }
    
    /**
     * CSV�̗v�f���͂ݕ����ň͂ނ��ǂ����𔻒肷��B<p>
     *
     * @return true�̏ꍇ�A�͂ݕ����ň͂�
     */
    public boolean isEnclose(){
         return isEnclose;
    }
    
    /**
     * null��CSV�v�f�Ƃ��ď����������Ƃ����ꍇ�ɁA�o�͂��镶�����ݒ肷��B<p>
     * �ݒ肵�Ȃ��ꍇ�́ANullPointerException����������B<br>
     *
     * @param value ������
     */
    public void setNullValue(String value){
        nullValue = value;
    }
    
    /**
     * null��CSV�v�f�Ƃ��ď����������Ƃ����ꍇ�ɁA�o�͂��镶������擾����B<p>
     *
     * @return ������
     */
    public String getNullValue(){
        return nullValue;
    }
    
    /**
     * ��s�𖳎����邩�ǂ�����ݒ肷��B<p>
     * ��s�𖳎�����悤�ɐݒ肵���ꍇ�A��s�͍s���Ƃ��Ă��J�E���g����Ȃ��B<br>
     * �f�t�H���g�́Afalse�Ŗ������Ȃ��B<br>
     *
     * @param isIgnore ��s�𖳎�����ꍇtrue
     */
    public void setIgnoreEmptyLine(boolean isIgnore){
        isIgnoreEmptyLine = isIgnore;
    }
    
    /**
     * ��s�𖳎����邩�ǂ����𔻒肷��B<p>
     *
     * @return true�̏ꍇ�A��s�𖳎�����
     */
    public boolean isIgnoreEmptyLine(){
         return isIgnoreEmptyLine;
    }
    
    /**
     * �s�̍Ō�̃Z�p���[�^�𖳎����邩�ǂ�����ݒ肷��B<p>
     * �f�t�H���g�́Afalse�Ŗ������Ȃ��B<br>
     *
     * @param isIgnore �s�̍Ō�̃Z�p���[�^�𖳎�����ꍇtrue
     */
    public void setIgnoreLineEndSeparator(boolean isIgnore){
        isIgnoreLineEndSeparator = isIgnore;
    }
    
    /**
     * �s�̍Ō�̃Z�p���[�^�𖳎����邩�ǂ����𔻒肷��B<p>
     *
     * @return true�̏ꍇ�A�s�̍Ō�̃Z�p���[�^�𖳎�����
     */
    public boolean isIgnoreLineEndSeparator(){
         return isIgnoreLineEndSeparator;
    }
    
    /**
     * CSV�t�@�C����ǂݍ��ލۂɎg�p����{@link CSVReader}��ݒ肷��B<p>
     *
     * @param reader CSVReader
     */
    public void setCSVReader(CSVReader reader){
        csvReader = reader;
    }
    
    /**
     * CSV�t�@�C�����������ލۂɎg�p����{@link CSVWriter}��ݒ肷��B<p>
     *
     * @param writer CSVWriter
     */
    public void setCSVWriter(CSVWriter writer){
        csvWriter = writer;
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
        case RECORDLIST_TO_CSV:
            return convertToStream(obj);
        case CSV_TO_RECORDLIST:
            if(obj instanceof File){
                return toRecordList((File)obj);
            }else if(obj instanceof InputStream){
                return toRecordList((InputStream)obj);
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
     * ���R�[�h���X�g����CSV�X�g���[���֕ϊ�����B<p>
     *
     * @param obj ���R�[�h���X�g
     * @return �ϊ����ʂ�ǂݎ����̓X�g���[��
     * @exception ConvertException �ϊ��Ɏ��s�����ꍇ
     */
    public InputStream convertToStream(Object obj) throws ConvertException{
        if(obj instanceof Record){
            RecordList list = new RecordList();
            list.add(obj);
            obj = list;
        }
        if(!(obj instanceof List)){
            throw new ConvertException("Input is not List." + obj);
        }
        List list = (List)obj;
        if(list.size() == 0){
            return new ByteArrayInputStream(new byte[0]);
        }
        RecordSchema schema = null;
        if(!(list instanceof RecordList)){
            if(!(list.get(0) instanceof Record)){
                throw new ConvertException("Input is not RecordList." + obj.getClass());
            }
            schema = ((Record)list.get(0)).getRecordSchema();
        }else{
            schema = ((RecordList)list).getRecordSchema();
            if(schema == null){
                schema = ((Record)list.get(0)).getRecordSchema();
            }
        }
        if(schema == null){
            throw new ConvertException("Schema is null." + list);
        }
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try{
            OutputStreamWriter osw = null;
            if(characterEncodingToStream == null){
                osw = new OutputStreamWriter(baos);
            }else{
                osw = new OutputStreamWriter(baos, characterEncodingToStream);
            }
            CSVWriter writer = csvWriter == null ? new CSVWriter() : csvWriter.cloneWriter();
            writer.setWriter(osw);
            if(csvWriter == null){
                writer.setSeparator(separator);
                writer.setSeparatorEscape(separatorEscape);
                writer.setEnclosure(enclosure);
                writer.setEnclose(isEnclose);
                writer.setLineSeparator(lineSeparator);
                writer.setNullValue(nullValue);
            }
            if(isExistsSchema){
                writer.writeElement(schema.getSchema());
                writer.newLine();
            }else if(isExistsHeader){
                for(int i = 0, imax = schema.getPropertySize(); i < imax; i++){
                    writer.writeElement(schema.getPropertyName(i));
                }
                writer.newLine();
            }
            for(int i = 0, imax = list.size(); i < imax; i++){
                Record record = (Record)list.get(i);
                if(record != null){
                    for(int j = 0, jmax = schema.getPropertySize(); j < jmax; j++){
                        writer.writeElement(record.getFormatProperty(j));
                    }
                    if(i != imax - 1){
                        writer.newLine();
                    }
                }
            }
            writer.close();
        }catch(IOException e){
            throw new ConvertException(e);
        }catch(DataSetException e){
            throw new ConvertException(e);
        }
        return new ByteArrayInputStream(baos.toByteArray());
    }
    
    /**
     * CSV�X�g���[�����烌�R�[�h���X�g�֕ϊ�����B<p>
     *
     * @param is ���̓X�g���[��
     * @return ���R�[�h���X�g
     * @exception ConvertException �ϊ��Ɏ��s�����ꍇ
     */
    public Object convertToObject(InputStream is) throws ConvertException{
        return toRecordList(is);
    }
    
    protected RecordList toRecordList(File file) throws ConvertException{
        try{
            return toRecordList(new FileInputStream(file));
        }catch(IOException e){
            throw new ConvertException(e);
        }
    }
    
    protected RecordList toRecordList(InputStream is) throws ConvertException{
        return toRecordList(is, null);
    }
    
    protected RecordList toRecordList(InputStream is, RecordList recList)
     throws ConvertException{
        try{
            InputStreamReader isr = null;
            if(characterEncodingToObject == null){
                isr = new InputStreamReader(is);
            }else{
                isr = new InputStreamReader(is, characterEncodingToObject);
            }
            CSVReader reader = csvReader == null ? new CSVReader() : csvReader.cloneReader();
            reader.setReader(isr);
            if(csvReader == null){
                reader.setSeparator(separator);
                reader.setSeparatorEscape(separatorEscape);
                reader.setEnclosure(enclosure);
                reader.setIgnoreEmptyLine(isIgnoreEmptyLine);
                reader.setIgnoreLineEndSeparator(isIgnoreLineEndSeparator);
                reader.setEnclosed(isEnclose);
                reader.setNullValue(nullValue);
            }
            if(recList == null){
                recList = new RecordList();
            }
            RecordSchema schema = recList.getRecordSchema();
            List csv = new ArrayList();
            List propertyNames = new ArrayList();
            if(isExistsSchema){
                csv = reader.readCSVLineList(csv);
                if(csv == null){
                    return recList;
                }
                if(csv.size() != 0){
                    if(schema == null){
                        recList.setSchema((String)csv.get(0));
                        schema = recList.getRecordSchema();
                    }else{
                        schema = RecordSchema.getInstance((String)csv.get(0));
                    }
                }
            }else{
                if(schema == null || isExistsHeader){
                    csv = reader.readCSVLineList(csv);
                    if(csv == null){
                        return recList;
                    }
                    final StringBuilder schemaBuf = new StringBuilder();
                    for(int i = 0, imax = csv.size(); i < imax; i++){
                        schemaBuf.append(':');
                        if(isExistsHeader){
                            schemaBuf.append(csv.get(i));
                        }else{
                            schemaBuf.append(i);
                        }
                        schemaBuf.append(',');
                        schemaBuf.append(String.class.getName());
                        if(i != imax - 1){
                            schemaBuf.append('\n');
                        }
                    }
                    if(schema == null){
                        recList.setSchema(schemaBuf.toString());
                        schema = recList.getRecordSchema();
                    }else if(isExistsHeader){
                        schema = RecordSchema.getInstance(schemaBuf.toString());
                    }
                    if(!isExistsHeader){
                        Record record = recList.createRecord();
                        for(int i = 0, imax = csv.size(); i < imax; i++){
                            record.setProperty(i, csv.get(i));
                        }
                        recList.addRecord(record);
                    }
                }
            }
            for(int i = 0, imax = schema.getPropertySize(); i < imax; i++){
                propertyNames.add(schema.getPropertyName(i));
            }
            RecordSchema targetSchema = recList.getRecordSchema();
            while((csv = reader.readCSVLineList(csv)) != null){
                Record record = recList.createRecord();
                int size = csv.size();
                for(int i = 0, imax = propertyNames.size(); i < imax; i++){
                    if(i >= size){
                        continue;
                    }
                    String name = (String)propertyNames.get(i);
                    if(targetSchema.getPropertyIndex(name) == -1){
                        if(isIgnoreUnknownProperty){
                            continue;
                        }
                    }
                    record.setParseProperty(
                        name,
                        csv.get(i)
                    );
                }
                recList.addRecord(record);
            }
            reader.close();
        }catch(IOException e){
            throw new ConvertException(e);
        }catch(DataSetException e){
            throw new ConvertException(e);
        }
        return recList;
    }
    
    /**
     * �w�肳�ꂽ���R�[�h���X�g�֕ϊ�����B<p>
     * 
     * @param is ���̓X�g���[��
     * @param returnType �ϊ��Ώۂ̃��R�[�h���X�g
     * @return �ϊ����ꂽ���R�[�h���X�g
     * @throws ConvertException �ϊ��Ɏ��s�����ꍇ
     */
    public Object convertToObject(InputStream is, Object returnType)
     throws ConvertException{
        if(returnType != null && !(returnType instanceof RecordList)){
            throw new ConvertException("ReturnType is not RecordList." + returnType);
        }
        return toRecordList(is, (RecordList)returnType);
    }
}