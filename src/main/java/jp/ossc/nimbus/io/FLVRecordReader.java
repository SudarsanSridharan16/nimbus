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
import jp.ossc.nimbus.util.converter.PaddingStringConverter;

/**
 * FLV�iFixed Length Value�j�`���̃X�g���[����{@link Record}�Ƃ��ēǂݍ���Reader�N���X�B<p>
 * <pre>
 * import java.io.*;
 * import jp.ossc.nimbus.io.FLVRecordReader;
 * import jp.ossc.nimbus.beans.dataset.Record;
 * import jp.ossc.nimbus.beans.dataset.RecordSchema;
 *
 * FileInputStream fis = new FileInputStream("sample.csv");
 * InputStreamReader isr = new InputStreamReader(fis, "UTF-8");
 * FLVRecordReader reader = new FLVRecordReader(isr);
 * reader.setFieldLength(new int[]{5,10});
 * reader.setEncoding("UTF-8");
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
public class FLVRecordReader extends FLVReader{
    
    private RecordSchema schema;
    private List workList;
    
    /**
     * �f�t�H���g�̓ǂݍ��݃o�b�t�@�T�C�Y�������ڑ��̃C���X�^���X�𐶐�����B<p>
     */
    public FLVRecordReader(){
        super();
    }
    
    /**
     * �f�t�H���g�̓ǂݍ��݃o�b�t�@�T�C�Y�������ڑ��̃C���X�^���X�𐶐�����B<p>
     *
     * @param fieldLen �t�B�[���h���̔z��
     */
    public FLVRecordReader(int[] fieldLen){
        super(fieldLen);
    }
    
    /**
     * �f�t�H���g�̓ǂݍ��݃o�b�t�@�T�C�Y�������ڑ��̃C���X�^���X�𐶐�����B<p>
     *
     * @param fieldLen �t�B�[���h���̔z��
     * @param encoding �����G���R�[�f�B���O
     */
    public FLVRecordReader(int[] fieldLen, String encoding){
        super(fieldLen, encoding);
    }
    
    /**
     * �f�t�H���g�̓ǂݍ��݃o�b�t�@�T�C�Y�������ڑ��̃C���X�^���X�𐶐�����B<p>
     *
     * @param fieldLen �t�B�[���h���̔z��
     * @param convs �t�B�[���h�̃p�f�B���O����������R���o�[�^�z��
     * @param encoding �����G���R�[�f�B���O
     */
    public FLVRecordReader(int[] fieldLen, PaddingStringConverter[] convs, String encoding){
        super(fieldLen, convs, encoding);
    }
    
    /**
     * �f�t�H���g�̓ǂݍ��݃o�b�t�@�T�C�Y�������ڑ��̃C���X�^���X�𐶐�����B<p>
     *
     * @param fieldLen �t�B�[���h���̔z��
     * @param encoding �����G���R�[�f�B���O
     * @param schema ���R�[�h�X�L�[�}
     */
    public FLVRecordReader(int[] fieldLen, String encoding, RecordSchema schema){
        super(fieldLen, encoding);
        setRecordSchema(schema);
    }
    
    /**
     * �f�t�H���g�̓ǂݍ��݃o�b�t�@�T�C�Y�������ڑ��̃C���X�^���X�𐶐�����B<p>
     *
     * @param fieldLen �t�B�[���h���̔z��
     * @param convs �t�B�[���h�̃p�f�B���O����������R���o�[�^�z��
     * @param encoding �����G���R�[�f�B���O
     * @param schema ���R�[�h�X�L�[�}
     */
    public FLVRecordReader(int[] fieldLen, PaddingStringConverter[] convs, String encoding, RecordSchema schema){
        super(fieldLen, convs, encoding);
        setRecordSchema(schema);
    }
    
    /**
     * �f�t�H���g�̓ǂݍ��݃o�b�t�@�T�C�Y�����C���X�^���X�𐶐�����B<p>
     *
     * @param reader �ǂݍ��݌���Reader
     */
    public FLVRecordReader(Reader reader){
        super(reader);
    }
    
    /**
     * �f�t�H���g�̓ǂݍ��݃o�b�t�@�T�C�Y�����C���X�^���X�𐶐�����B<p>
     *
     * @param reader �ǂݍ��݌���Reader
     * @param fieldLen �t�B�[���h���̔z��
     */
    public FLVRecordReader(Reader reader, int[] fieldLen){
        super(reader, fieldLen);
    }
    
    /**
     * �f�t�H���g�̓ǂݍ��݃o�b�t�@�T�C�Y�����C���X�^���X�𐶐�����B<p>
     *
     * @param reader �ǂݍ��݌���Reader
     * @param fieldLen �t�B�[���h���̔z��
     * @param encoding �����G���R�[�f�B���O
     */
    public FLVRecordReader(Reader reader, int[] fieldLen, String encoding){
        super(reader, fieldLen, encoding);
    }
    
    /**
     * �f�t�H���g�̓ǂݍ��݃o�b�t�@�T�C�Y�����C���X�^���X�𐶐�����B<p>
     *
     * @param reader �ǂݍ��݌���Reader
     * @param fieldLen �t�B�[���h���̔z��
     * @param convs �t�B�[���h�̃p�f�B���O����������R���o�[�^�z��
     * @param encoding �����G���R�[�f�B���O
     */
    public FLVRecordReader(Reader reader, int[] fieldLen, PaddingStringConverter[] convs, String encoding){
        super(reader, fieldLen, convs, encoding);
    }
    
    /**
     * �f�t�H���g�̓ǂݍ��݃o�b�t�@�T�C�Y�����C���X�^���X�𐶐�����B<p>
     *
     * @param reader �ǂݍ��݌���Reader
     * @param fieldLen �t�B�[���h���̔z��
     * @param encoding �����G���R�[�f�B���O
     * @param schema ���R�[�h�X�L�[�}
     */
    public FLVRecordReader(Reader reader, int[] fieldLen, String encoding, RecordSchema schema){
        super(reader, fieldLen, encoding);
        setRecordSchema(schema);
    }
    
    /**
     * �f�t�H���g�̓ǂݍ��݃o�b�t�@�T�C�Y�����C���X�^���X�𐶐�����B<p>
     *
     * @param reader �ǂݍ��݌���Reader
     * @param fieldLen �t�B�[���h���̔z��
     * @param convs �t�B�[���h�̃p�f�B���O����������R���o�[�^�z��
     * @param encoding �����G���R�[�f�B���O
     * @param schema ���R�[�h�X�L�[�}
     */
    public FLVRecordReader(Reader reader, int[] fieldLen, PaddingStringConverter[] convs, String encoding, RecordSchema schema){
        super(reader, fieldLen, convs, encoding);
        setRecordSchema(schema);
    }
    
    /**
     * �w�肳�ꂽ�ǂݍ��݃o�b�t�@�T�C�Y�������ڑ��̃C���X�^���X�𐶐�����B<p>
     *
     * @param size �ǂݍ��݃o�b�t�@�T�C�Y
     */
    public FLVRecordReader(int size){
        super(size);
    }
    
    /**
     * �w�肳�ꂽ�ǂݍ��݃o�b�t�@�T�C�Y�������ڑ��̃C���X�^���X�𐶐�����B<p>
     *
     * @param size �ǂݍ��݃o�b�t�@�T�C�Y
     * @param fieldLen �t�B�[���h���̔z��
     */
    public FLVRecordReader(int size, int[] fieldLen){
        super(size, fieldLen);
    }
    
    /**
     * �w�肳�ꂽ�ǂݍ��݃o�b�t�@�T�C�Y�������ڑ��̃C���X�^���X�𐶐�����B<p>
     *
     * @param size �ǂݍ��݃o�b�t�@�T�C�Y
     * @param fieldLen �t�B�[���h���̔z��
     * @param encoding �����G���R�[�f�B���O
     */
    public FLVRecordReader(int size, int[] fieldLen, String encoding){
        super(size, fieldLen, encoding);
    }
    
    /**
     * �w�肳�ꂽ�ǂݍ��݃o�b�t�@�T�C�Y�������ڑ��̃C���X�^���X�𐶐�����B<p>
     *
     * @param size �ǂݍ��݃o�b�t�@�T�C�Y
     * @param fieldLen �t�B�[���h���̔z��
     * @param convs �t�B�[���h�̃p�f�B���O����������R���o�[�^�z��
     * @param encoding �����G���R�[�f�B���O
     */
    public FLVRecordReader(int size, int[] fieldLen, PaddingStringConverter[] convs, String encoding){
        super(size, fieldLen, convs, encoding);
    }
    
    /**
     * �w�肳�ꂽ�ǂݍ��݃o�b�t�@�T�C�Y�������ڑ��̃C���X�^���X�𐶐�����B<p>
     *
     * @param size �ǂݍ��݃o�b�t�@�T�C�Y
     * @param fieldLen �t�B�[���h���̔z��
     * @param encoding �����G���R�[�f�B���O
     * @param schema ���R�[�h�X�L�[�}
     */
    public FLVRecordReader(int size, int[] fieldLen, String encoding, RecordSchema schema){
        super(size, fieldLen, encoding);
        setRecordSchema(schema);
    }
    
    /**
     * �w�肳�ꂽ�ǂݍ��݃o�b�t�@�T�C�Y�������ڑ��̃C���X�^���X�𐶐�����B<p>
     *
     * @param size �ǂݍ��݃o�b�t�@�T�C�Y
     * @param fieldLen �t�B�[���h���̔z��
     * @param convs �t�B�[���h�̃p�f�B���O����������R���o�[�^�z��
     * @param encoding �����G���R�[�f�B���O
     * @param schema ���R�[�h�X�L�[�}
     */
    public FLVRecordReader(int size, int[] fieldLen, PaddingStringConverter[] convs, String encoding, RecordSchema schema){
        super(size, fieldLen, convs, encoding);
        setRecordSchema(schema);
    }
    
    /**
     * �w�肳�ꂽ�ǂݍ��݃o�b�t�@�T�C�Y�����C���X�^���X�𐶐�����B<p>
     *
     * @param reader �ǂݍ��݌���Reader
     * @param size �ǂݍ��݃o�b�t�@�T�C�Y
     */
    public FLVRecordReader(Reader reader, int size){
        super(reader, size);
    }
    
    /**
     * �w�肳�ꂽ�ǂݍ��݃o�b�t�@�T�C�Y�����C���X�^���X�𐶐�����B<p>
     *
     * @param reader �ǂݍ��݌���Reader
     * @param size �ǂݍ��݃o�b�t�@�T�C�Y
     * @param fieldLen �t�B�[���h���̔z��
     */
    public FLVRecordReader(Reader reader, int size, int[] fieldLen){
        super(reader, size, fieldLen);
    }
    
    /**
     * �w�肳�ꂽ�ǂݍ��݃o�b�t�@�T�C�Y�����C���X�^���X�𐶐�����B<p>
     *
     * @param reader �ǂݍ��݌���Reader
     * @param size �ǂݍ��݃o�b�t�@�T�C�Y
     * @param fieldLen �t�B�[���h���̔z��
     * @param encoding �����G���R�[�f�B���O
     */
    public FLVRecordReader(Reader reader, int size, int[] fieldLen, String encoding){
        super(reader, size, fieldLen, encoding);
    }
    
    /**
     * �w�肳�ꂽ�ǂݍ��݃o�b�t�@�T�C�Y�����C���X�^���X�𐶐�����B<p>
     *
     * @param reader �ǂݍ��݌���Reader
     * @param size �ǂݍ��݃o�b�t�@�T�C�Y
     * @param fieldLen �t�B�[���h���̔z��
     * @param convs �t�B�[���h�̃p�f�B���O����������R���o�[�^�z��
     * @param encoding �����G���R�[�f�B���O
     */
    public FLVRecordReader(Reader reader, int size, int[] fieldLen, PaddingStringConverter[] convs, String encoding){
        super(reader, size, fieldLen, convs, encoding);
    }
    
    /**
     * �w�肳�ꂽ�ǂݍ��݃o�b�t�@�T�C�Y�����C���X�^���X�𐶐�����B<p>
     *
     * @param reader �ǂݍ��݌���Reader
     * @param size �ǂݍ��݃o�b�t�@�T�C�Y
     * @param fieldLen �t�B�[���h���̔z��
     * @param encoding �����G���R�[�f�B���O
     * @param schema ���R�[�h�X�L�[�}
     */
    public FLVRecordReader(Reader reader, int size, int[] fieldLen, String encoding, RecordSchema schema){
        super(reader, size, fieldLen, encoding);
        setRecordSchema(schema);
    }
    
    /**
     * �w�肳�ꂽ�ǂݍ��݃o�b�t�@�T�C�Y�����C���X�^���X�𐶐�����B<p>
     *
     * @param reader �ǂݍ��݌���Reader
     * @param size �ǂݍ��݃o�b�t�@�T�C�Y
     * @param fieldLen �t�B�[���h���̔z��
     * @param convs �t�B�[���h�̃p�f�B���O����������R���o�[�^�z��
     * @param encoding �����G���R�[�f�B���O
     * @param schema ���R�[�h�X�L�[�}
     */
    public FLVRecordReader(Reader reader, int size, int[] fieldLen, PaddingStringConverter[] convs, String encoding, RecordSchema schema){
        super(reader, size, fieldLen, convs, encoding);
        setRecordSchema(schema);
    }
    
    /**
     * �ǂݍ���FLV�`��������̃X�L�[�}��ݒ肷��B<p>
     *
     * @param schema �X�L�[�}
     * @see #readRecord()
     */
    public void setRecordSchema(RecordSchema schema){
        this.schema = schema;
        workList = new ArrayList(schema.getPropertySize());
    }
    
    /**
     * �ǂݍ���FLV�`��������̃��R�[�h�X�L�[�}���擾����B<p>
     *
     * @return �X�L�[�}
     */
    public RecordSchema getRecordSchema(){
        return schema;
    }
    
    /**
     * �\�ߐݒ肳�ꂽ���R�[�h�X�L�[�}���g���āAFLV�s��1�s�A���R�[�h�Ƃ��ēǂݍ��ށB<p>
     *
     * @return FLV�v�f���i�[�������R�[�h
     * @exception IOException ���o�̓G���[�����������ꍇ
     * @exception PropertySetException FLV�`���̗v�f������̃p�[�X�Ɏ��s�����ꍇ
     * @see #setRecordSchema(RecordSchema)
     */
    public Record readRecord() throws IOException, PropertySetException{
        return readRecord(null);
    }
    
    /**
     * FLV�s��1�s�A���R�[�h�Ƃ��ēǂݍ��ށB<p>
     * FLV�v�f�̒l���i�[���郌�R�[�h���ė��p���邽�߂̃��\�b�h�ł���B<br>
     *
     * @param record FLV�v�f�̒l���i�[���郌�R�[�h
     * @return FLV�v�f���i�[�������R�[�h
     * @exception IOException ���o�̓G���[�����������ꍇ
     * @exception PropertySetException FLV�`���̗v�f������̃p�[�X�Ɏ��s�����ꍇ
     */
    public Record readRecord(Record record) throws IOException, PropertySetException{
        if(workList == null){
            workList = new ArrayList();
        }
        List flv = readFLVLineList(workList);
        if(flv == null){
            return null;
        }
        if(record == null){
            record = new Record(getRecordSchema());
        }else{
            record.clear();
        }
        for(int i = 0, imax = Math.min(flv.size(), record.size()); i < imax; i++){
            String element = (String)flv.get(i);
            record.setParseProperty(i, element);
        }
        return record;
    }
    
    /**
     * ���ڑ��̕����𐶐�����B<p>
     *
     * @return ���ڑ��̕���
     */
    public FLVReader cloneReader(){
        return cloneReader(new FLVRecordReader());
    }
    
    /**
     * ���ڑ��̕����𐶐�����B<p>
     *
     * @param clone ���ڑ��̃C���X�^���X
     * @return ���ڑ��̕���
     */
    protected FLVReader cloneReader(FLVReader clone){
        clone = super.cloneReader(clone);
        ((FLVRecordReader)clone).schema = schema;
        return clone;
    }
}
