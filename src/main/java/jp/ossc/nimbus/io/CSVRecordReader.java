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
package jp.ossc.nimbus.io;

import java.io.*;
import java.util.*;

import jp.ossc.nimbus.beans.dataset.Record;
import jp.ossc.nimbus.beans.dataset.RecordSchema;
import jp.ossc.nimbus.beans.dataset.PropertySetException;

/**
 * CSV�`���̃X�g���[����{@link Record}�Ƃ��ēǂݍ���Reader�N���X�B<p>
 * <pre>
 * import java.io.*;
 * import jp.ossc.nimbus.io.CSVRecordReader;
 * import jp.ossc.nimbus.beans.dataset.Record;
 * import jp.ossc.nimbus.beans.dataset.RecordSchema;
 *
 * FileInputStream fis = new FileInputStream("sample.csv");
 * InputStreamReader isr = new InputStreamReader(fis, "UTF-8");
 * CSVRecordReader reader = new CSVRecordReader(isr);
 * Record record = new Record(":a,java.lang.String\n:b,int");
 * reader.setRecordSchema(record.getRecordSchema());
 * try{
 *     while((record = reader.readRecord(record)) != null){
 *         String a = record.getStringProperty("a");
 *         int b = record.getIntProperty("b");
 *             :
 *     }
 * }finally{
 *     reader.close();
 * }
 * </pre>
 * 
 * @author M.Takata
 */
public class CSVRecordReader extends CSVReader{
    
    private RecordSchema schema;
    private List workList;
    
    /**
     * �f�t�H���g�̓ǂݍ��݃o�b�t�@�T�C�Y�������ڑ��̃C���X�^���X�𐶐�����B<p>
     */
    public CSVRecordReader(){
        super();
    }
    
    /**
     * �f�t�H���g�̓ǂݍ��݃o�b�t�@�T�C�Y�����C���X�^���X�𐶐�����B<p>
     *
     * @param reader �ǂݍ��݌���Reader
     */
    public CSVRecordReader(Reader reader){
        super(reader);
    }
    
    /**
     * �f�t�H���g�̓ǂݍ��݃o�b�t�@�T�C�Y�����C���X�^���X�𐶐�����B<p>
     *
     * @param reader �ǂݍ��݌���Reader
     * @param schema ���R�[�h�X�L�[�}
     */
    public CSVRecordReader(Reader reader, RecordSchema schema){
        super(reader);
        setRecordSchema(schema);
    }
    
    /**
     * �w�肳�ꂽ�ǂݍ��݃o�b�t�@�T�C�Y�������ڑ��̃C���X�^���X�𐶐�����B<p>
     *
     * @param size �ǂݍ��݃o�b�t�@�T�C�Y
     */
    public CSVRecordReader(int size){
        super(size);
    }
    
    /**
     * �w�肳�ꂽ�ǂݍ��݃o�b�t�@�T�C�Y�����C���X�^���X�𐶐�����B<p>
     *
     * @param reader �ǂݍ��݌���Reader
     * @param size �ǂݍ��݃o�b�t�@�T�C�Y
     */
    public CSVRecordReader(Reader reader, int size){
        super(reader, size);
    }
    
    /**
     * �w�肳�ꂽ�ǂݍ��݃o�b�t�@�T�C�Y�����C���X�^���X�𐶐�����B<p>
     *
     * @param reader �ǂݍ��݌���Reader
     * @param schema ���R�[�h�X�L�[�}
     * @param size �ǂݍ��݃o�b�t�@�T�C�Y
     */
    public CSVRecordReader(Reader reader, RecordSchema schema, int size){
        super(reader, size);
        setRecordSchema(schema);
    }
    
    /**
     * �ǂݍ���CSV�`��������̃X�L�[�}��ݒ肷��B<p>
     *
     * @param schema �X�L�[�}
     * @see #readRecord()
     */
    public void setRecordSchema(RecordSchema schema){
        this.schema = schema;
        workList = new ArrayList(schema.getPropertySize());
    }
    
    /**
     * �ǂݍ���CSV�`��������̃��R�[�h�X�L�[�}���擾����B<p>
     *
     * @return �X�L�[�}
     */
    public RecordSchema getRecordSchema(){
        return schema;
    }
    
    /**
     * �\�ߐݒ肳�ꂽ���R�[�h�X�L�[�}���g���āACSV�s��1�s�A���R�[�h�Ƃ��ēǂݍ��ށB<p>
     *
     * @return CSV�v�f���i�[�������R�[�h
     * @exception IOException ���o�̓G���[�����������ꍇ
     * @exception PropertySetException CSV�`���̗v�f������̃p�[�X�Ɏ��s�����ꍇ
     * @see #setRecordSchema(RecordSchema)
     */
    public Record readRecord() throws IOException, PropertySetException{
        return readRecord(null);
    }
    
    /**
     * CSV�s��1�s�A���R�[�h�Ƃ��ēǂݍ��ށB<p>
     * CSV�v�f�̒l���i�[���郌�R�[�h���ė��p���邽�߂̃��\�b�h�ł���B<br>
     *
     * @param record CSV�v�f�̒l���i�[���郌�R�[�h
     * @return CSV�v�f���i�[�������R�[�h
     * @exception IOException ���o�̓G���[�����������ꍇ
     * @exception PropertySetException CSV�`���̗v�f������̃p�[�X�Ɏ��s�����ꍇ
     */
    public Record readRecord(Record record) throws IOException, PropertySetException{
        if(workList == null){
            workList = new ArrayList();
        }
        List csv = readCSVLineList(workList);
        if(csv == null){
            return null;
        }
        if(record == null){
            record = new Record(getRecordSchema());
        }else{
            record.clear();
        }
        for(int i = 0, imax = Math.min(csv.size(), record.size()); i < imax; i++){
            String element = (String)csv.get(i);
            record.setParseProperty(i, element);
        }
        return record;
    }
    
    /**
     * ���ڑ��̕����𐶐�����B<p>
     *
     * @return ���ڑ��̕���
     */
    public CSVReader cloneReader(){
        return cloneReader(new CSVRecordReader());
    }
    
    /**
     * ���ڑ��̕����𐶐�����B<p>
     *
     * @param clone ���ڑ��̃C���X�^���X
     * @return ���ڑ��̕���
     */
    protected CSVReader cloneReader(CSVReader clone){
        super.cloneReader(clone);
        ((CSVRecordReader)clone).schema = schema;
        return clone;
    }
}
