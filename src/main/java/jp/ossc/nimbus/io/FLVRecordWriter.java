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

import jp.ossc.nimbus.beans.dataset.Record;
import jp.ossc.nimbus.beans.dataset.PropertyGetException;
import jp.ossc.nimbus.util.converter.PaddingStringConverter;

/**
 * {@link Record}��FLV�`���̃X�g���[���Ƃ��ď�������Writer�N���X�B<p>
 * <pre>
 * import java.io.*;
 * import jp.ossc.nimbus.io.FLVRecordWriter;
 * import jp.ossc.nimbus.beans.dataset.Record;
 * import jp.ossc.nimbus.beans.dataset.RecordSchema;
 *
 * FileOutputStream fos = new FileOutputStream("sample.csv");
 * OutputStreamWriter osw = new OutputStreamWriter(fos, "UTF-8");
 * FLVRecordWriter writer = new FLVRecordWriter(
 *     osw,
 *     new PaddingStringConverter[]{
 *         new PaddingStringConverter(10),
 *         new PaddingStringConverter(12),
 *     }
 * );
 * Record record = new Record(":a,java.lang.String\n:b,int");
 * try{
 *     record.setProperty("a", "hoge");
 *     record.setProperty("b", 100);
 *     writer.writeRecord(record);
 *        :
 * }finally{
 *     writer.close();
 * }
 * </pre>
 * 
 * @author M.Takata
 */
public class FLVRecordWriter extends FLVWriter{
    
    /**
     * �f�t�H���g�̏������݃o�b�t�@�T�C�Y�������ڑ��̃C���X�^���X�𐶐�����B<p>
     */
    public FLVRecordWriter(){
        super();
    }
    
    /**
     * �f�t�H���g�̏������݃o�b�t�@�T�C�Y�����C���X�^���X�𐶐�����B<p>
     *
     * @param writer �������ݐ��Writer
     */
    public FLVRecordWriter(Writer writer){
        super(writer);
    }
    
    /**
     * �f�t�H���g�̏������݃o�b�t�@�T�C�Y�����C���X�^���X�𐶐�����B<p>
     *
     * @param writer �������ݐ��Writer
     * @param convs �t�B�[���h���p�f�B���O����R���o�[�^�z��
     */
    public FLVRecordWriter(Writer writer, PaddingStringConverter[] convs){
        super(writer, convs);
    }
    
    /**
     * �w�肳�ꂽ�������݃o�b�t�@�T�C�Y�������ڑ��̃C���X�^���X�𐶐�����B<p>
     *
     * @param size �������݃o�b�t�@�T�C�Y
     */
    public FLVRecordWriter(int size){
        super(size);
    }
    
    /**
     * �w�肳�ꂽ�������݃o�b�t�@�T�C�Y�����C���X�^���X�𐶐�����B<p>
     *
     * @param writer �������ݐ��Writer
     * @param size �������݃o�b�t�@�T�C�Y
     */
    public FLVRecordWriter(Writer writer, int size){
        super(writer, size);
    }
    
    /**
     * �w�肳�ꂽ�������݃o�b�t�@�T�C�Y�����C���X�^���X�𐶐�����B<p>
     *
     * @param writer �������ݐ��Writer
     * @param convs �t�B�[���h���p�f�B���O����R���o�[�^�z��
     * @param size �������݃o�b�t�@�T�C�Y
     */
    public FLVRecordWriter(Writer writer, PaddingStringConverter[] convs, int size){
        super(writer, convs, size);
    }
    
    /**
     * �w�肳�ꂽ���R�[�h��FLV�Ƃ��ď������ށB<p>
     * ���s�����̒ǉ��A�Z�p���[�^�̒ǉ��A�Z�p���[�^�������܂܂�Ă���ꍇ�̃G�X�P�[�v�A�͂ݕ����ł̈͂ݏ����������ōs���B<br>
     *
     * @param record FLV�`���ŏo�͂��郌�R�[�h
     * @exception IOException ���o�̓G���[�����������ꍇ
     * @exception PropertyGetException FLV�`���̗v�f������̃t�H�[�}�b�g�Ɏ��s�����ꍇ
     */
    public void writeRecord(Record record) throws IOException, PropertyGetException{
        for(int i = 0, imax = record.size(); i < imax; i++){
            writeElement(record.getFormatProperty(i));
        }
        newLine();
    }
    
    /**
     * ���ڑ��̕����𐶐�����B<p>
     *
     * @return ���ڑ��̕���
     */
    public FLVWriter cloneWriter(){
        return cloneWriter(new FLVRecordWriter());
    }
}