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
import java.math.*;
import java.util.*;
import java.nio.*;
import java.lang.reflect.*;

import jp.ossc.nimbus.util.converter.PaddingStringConverter;

/**
 * FLV�iFixed Length Value�j�`����Reader�N���X�B<p>
 * <pre>
 * import java.io.*;
 * import jp.ossc.nimbus.io.FLVReader;
 *
 * FileInputStream fis = new FileInputStream("sample.csv");
 * InputStreamReader isr = new InputStreamReader(fis, "UTF-8");
 * FLVReader reader = new FLVReader(isr);
 * reader.setFieldLength(new int[]{5,10});
 * reader.setEncoding("UTF-8");
 * try{
 *     String[] flv = null;
 *     while((flv = reader.readFLVLine()) != null){
 *           :
 *     }
 * }finally{
 *     reader.close();
 * }
 * </pre>
 * 
 * @author M.Takata
 */
public class FLVReader extends LineNumberReader{
    
    /**
     * �f�t�H���g�̉��s�����B<p>
     */
    public static final String LINE_SEPARATOR
         = System.getProperty("line.separator");
    
    protected String encoding;
    protected int[] fieldLength;
    protected PaddingStringConverter[] converters;
    
    protected boolean isIgnoreEmptyLine;
    protected String commentPrefix;
    
    protected FLVIterator iterator;
    
    protected ReaderWrapper readerWrapper;
    
    /**
     * �f�t�H���g�̓ǂݍ��݃o�b�t�@�T�C�Y�������ڑ��̃C���X�^���X�𐶐�����B<p>
     */
    public FLVReader(){
        this(new ReaderWrapper());
    }
    
    /**
     * �f�t�H���g�̓ǂݍ��݃o�b�t�@�T�C�Y�������ڑ��̃C���X�^���X�𐶐�����B<p>
     *
     * @param fieldLen �t�B�[���h���̔z��
     */
    public FLVReader(int[] fieldLen){
        this(fieldLen, null);
    }
    
    /**
     * �f�t�H���g�̓ǂݍ��݃o�b�t�@�T�C�Y�������ڑ��̃C���X�^���X�𐶐�����B<p>
     *
     * @param fieldLen �t�B�[���h���̔z��
     * @param encoding �����G���R�[�f�B���O
     */
    public FLVReader(int[] fieldLen, String encoding){
        this(new ReaderWrapper(), fieldLen, encoding);
    }
    
    /**
     * �f�t�H���g�̓ǂݍ��݃o�b�t�@�T�C�Y�������ڑ��̃C���X�^���X�𐶐�����B<p>
     *
     * @param fieldLen �t�B�[���h���̔z��
     * @param convs �t�B�[���h�̃p�f�B���O����������R���o�[�^�z��
     * @param encoding �����G���R�[�f�B���O
     */
    public FLVReader(int[] fieldLen, PaddingStringConverter[] convs, String encoding){
        this(new ReaderWrapper(), fieldLen, convs, encoding);
    }
    
    /**
     * �f�t�H���g�̓ǂݍ��݃o�b�t�@�T�C�Y�����C���X�^���X�𐶐�����B<p>
     *
     * @param reader �ǂݍ��݌���Reader
     */
    public FLVReader(Reader reader){
        this(reader, null);
    }
    
    /**
     * �f�t�H���g�̓ǂݍ��݃o�b�t�@�T�C�Y�����C���X�^���X�𐶐�����B<p>
     *
     * @param reader �ǂݍ��݌���Reader
     * @param fieldLen �t�B�[���h���̔z��
     */
    public FLVReader(Reader reader, int[] fieldLen){
        this(reader, fieldLen, null);
    }
    
    /**
     * �f�t�H���g�̓ǂݍ��݃o�b�t�@�T�C�Y�����C���X�^���X�𐶐�����B<p>
     *
     * @param reader �ǂݍ��݌���Reader
     * @param fieldLen �t�B�[���h���̔z��
     * @param encoding �����G���R�[�f�B���O
     */
    public FLVReader(Reader reader, int[] fieldLen, String encoding){
        this(reader, fieldLen, null, encoding);
    }
    
    /**
     * �f�t�H���g�̓ǂݍ��݃o�b�t�@�T�C�Y�����C���X�^���X�𐶐�����B<p>
     *
     * @param reader �ǂݍ��݌���Reader
     * @param fieldLen �t�B�[���h���̔z��
     * @param convs �t�B�[���h�̃p�f�B���O����������R���o�[�^�z��
     * @param encoding �����G���R�[�f�B���O
     */
    public FLVReader(Reader reader, int[] fieldLen, PaddingStringConverter[] convs, String encoding){
        super(reader instanceof ReaderWrapper ? reader : new ReaderWrapper(reader));
        readerWrapper = (ReaderWrapper)lock;
        fieldLength = fieldLen;
        converters = convs;
        this.encoding = encoding;
    }
    
    /**
     * �w�肳�ꂽ�ǂݍ��݃o�b�t�@�T�C�Y�������ڑ��̃C���X�^���X�𐶐�����B<p>
     *
     * @param size �ǂݍ��݃o�b�t�@�T�C�Y
     */
    public FLVReader(int size){
        this(size, null);
    }
    
    /**
     * �w�肳�ꂽ�ǂݍ��݃o�b�t�@�T�C�Y�������ڑ��̃C���X�^���X�𐶐�����B<p>
     *
     * @param size �ǂݍ��݃o�b�t�@�T�C�Y
     * @param fieldLen �t�B�[���h���̔z��
     */
    public FLVReader(int size, int[] fieldLen){
        this(size, fieldLen, null);
    }
    
    /**
     * �w�肳�ꂽ�ǂݍ��݃o�b�t�@�T�C�Y�������ڑ��̃C���X�^���X�𐶐�����B<p>
     *
     * @param size �ǂݍ��݃o�b�t�@�T�C�Y
     * @param fieldLen �t�B�[���h���̔z��
     * @param encoding �����G���R�[�f�B���O
     */
    public FLVReader(int size, int[] fieldLen, String encoding){
        this(size, fieldLen, null, encoding);
    }
    
    /**
     * �w�肳�ꂽ�ǂݍ��݃o�b�t�@�T�C�Y�������ڑ��̃C���X�^���X�𐶐�����B<p>
     *
     * @param size �ǂݍ��݃o�b�t�@�T�C�Y
     * @param fieldLen �t�B�[���h���̔z��
     * @param convs �t�B�[���h�̃p�f�B���O����������R���o�[�^�z��
     * @param encoding �����G���R�[�f�B���O
     */
    public FLVReader(int size, int[] fieldLen, PaddingStringConverter[] convs, String encoding){
        this(new ReaderWrapper(), size, fieldLen, convs, encoding);
    }
    
    /**
     * �w�肳�ꂽ�ǂݍ��݃o�b�t�@�T�C�Y�����C���X�^���X�𐶐�����B<p>
     *
     * @param reader �ǂݍ��݌���Reader
     * @param size �ǂݍ��݃o�b�t�@�T�C�Y
     */
    public FLVReader(Reader reader, int size){
        this(reader, size, null);
    }
    
    /**
     * �w�肳�ꂽ�ǂݍ��݃o�b�t�@�T�C�Y�����C���X�^���X�𐶐�����B<p>
     *
     * @param reader �ǂݍ��݌���Reader
     * @param size �ǂݍ��݃o�b�t�@�T�C�Y
     * @param fieldLen �t�B�[���h���̔z��
     */
    public FLVReader(Reader reader, int size, int[] fieldLen){
        this(reader, size, fieldLen, null);
    }
    
    /**
     * �w�肳�ꂽ�ǂݍ��݃o�b�t�@�T�C�Y�����C���X�^���X�𐶐�����B<p>
     *
     * @param reader �ǂݍ��݌���Reader
     * @param size �ǂݍ��݃o�b�t�@�T�C�Y
     * @param fieldLen �t�B�[���h���̔z��
     * @param encoding �����G���R�[�f�B���O
     */
    public FLVReader(Reader reader, int size, int[] fieldLen, String encoding){
        this(reader, size, fieldLen, null, encoding);
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
    public FLVReader(Reader reader, int size, int[] fieldLen, PaddingStringConverter[] convs, String encoding){
        super(reader instanceof ReaderWrapper ? reader : new ReaderWrapper(reader), size);
        readerWrapper = (ReaderWrapper)lock;
        fieldLength = fieldLen;
        converters = convs;
        this.encoding = encoding;
    }
    
    /**
     * �e�t�B�[���h�̃o�C�g����ݒ肷��B<p>
     *
     * @param length �t�B�[���h���̔z��
     */
    public void setFieldLength(int[] length){
        fieldLength = length;
    }
    
    /**
     * �e�t�B�[���h�̃o�C�g�����擾����B<p>
     *
     * @return �t�B�[���h���̔z��
     */
    public int[] getFieldLength(){
        return fieldLength;
    }
    
    /**
     * �e�t�B�[���h�̃p�f�B���O�̉������s���R���o�[�^��ݒ肷��B<p>
     *
     * @param convs �p�f�B���O�̉������s���R���o�[�^�̔z��
     */
    public void setFieldPaddingStringConverter(PaddingStringConverter[] convs){
        converters = convs;
    }
    
    /**
     * �e�t�B�[���h�̃p�f�B���O�̉������s���R���o�[�^���擾����B<p>
     *
     * @return �p�f�B���O�̉������s���R���o�[�^�̔z��
     */
    public PaddingStringConverter[] getFieldPaddingStringConverter(){
        return converters;
    }
    
    /**
     * �����G���R�[�f�B���O��ݒ肷��B<p>
     *
     * @param encoding �����G���R�[�f�B���O
     */
    public void setEncoding(String encoding){
        this.encoding = encoding;
    }
    
    /**
     * �����G���R�[�f�B���O���擾����B<p>
     *
     * @return �����G���R�[�f�B���O
     */
    public String getEncoding(){
        return encoding;
    }
    
    /**
     * Reader��ݒ肷��B<p>
     *
     * @param reader Reader
     */
    public void setReader(Reader reader){
        readerWrapper.setReader(reader);
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
     * �R�����g�s�̑O�u�������ݒ肷��B<p>
     *
     * @param value �R�����g�s�̑O�u������
     */
    public void setCommentPrefix(String value){
        commentPrefix = value;
    }
    
    /**
     * �R�����g�s�̑O�u��������擾����B<p>
     *
     * @return �R�����g�s�̑O�u������
     */
    public String getCommentPrefix(){
        return commentPrefix;
    }
    
    /**
     * �w�肳�ꂽ�s�����X�L�b�v����B<p>
     *
     * @param line �X�L�b�v����s��
     * @return �X�L�b�v���ꂽ�s��
     * @exception IOException ���o�̓G���[�����������ꍇ
     */
    public long skipLine(long line) throws IOException{
        int result = 0;
        for(result = 0; result < line; result++){
            if(super.readLine() == null){
                break;
            }
        }
        return result;
    }
    
    /**
     * �w�肳�ꂽFLV�s�����X�L�b�v����B<p>
     * {@link #isIgnoreEmptyLine()}��true�̏ꍇ�́A��s�̓X�L�b�v�s���̃J�E���g���珜�����B<br>
     *
     * @param line �X�L�b�v����s��
     * @return �X�L�b�v���ꂽ�s��
     * @exception IOException ���o�̓G���[�����������ꍇ
     */
    public long skipFLVLine(long line) throws IOException{
        List flv = null;
        int result = 0;
        for(result = 0; result < line; result++){
            flv = readFLVLineList(flv);
            if(flv == null){
                break;
            }
        }
        return result;
    }
    
    /**
     * FLV�s��1�s�ǂݍ��ށB<p>
     *
     * @return FLV�v�f�̕�����z��
     * @exception IOException ���o�̓G���[�����������ꍇ
     */
    public String[] readFLVLine() throws IOException{
        final List flv = readFLVLineList();
        return flv == null ? null
             : (String[])flv.toArray(new String[flv.size()]);
    }
    
    /**
     * FLV�s��1�s�ǂݍ��ށB<p>
     *
     * @return FLV�v�f�̕����񃊃X�g
     * @exception IOException ���o�̓G���[�����������ꍇ
     */
    public List readFLVLineList() throws IOException{
        return readFLVLineList(null);
    }
    
    /**
     * FLV�s��1�s�ǂݍ��ށB<p>
     * FLV�v�f�̕�������i�[���郊�X�g���ė��p���邽�߂̃��\�b�h�ł���B<br>
     *
     * @param flv FLV�v�f�̕�������i�[���郊�X�g
     * @return FLV�v�f�̕����񃊃X�g
     * @exception IOException ���o�̓G���[�����������ꍇ
     */
    public List readFLVLineList(List flv) throws IOException{
        String line = null;
        do{
            line = readLine();
            if(line == null){
                if(flv != null){
                    flv.clear();
                }
                return null;
            }
            if((isIgnoreEmptyLine && line.length() == 0)
                    || (commentPrefix != null && line.startsWith(commentPrefix))
            ){
                line = null;
                setLineNumber(getLineNumber() - 1);
            }
        }while(line == null);
        if(flv == null){
            flv = new ArrayList();
        }else{
            flv.clear();
        }
        if(line.length() == 0){
            return flv;
        }
        byte[] bytes = null;
        if(encoding == null){
            bytes = line.getBytes();
        }else{
            bytes = line.getBytes(encoding);
        }
        int offset = 0;
        for(int i = 0; i < fieldLength.length; i++){
            if(bytes.length < offset + fieldLength[i]){
                throw new EOFException();
            }
            String element = null;
            if(encoding == null){
                element = new String(bytes, offset, fieldLength[i]);
            }else{
                element = new String(bytes, offset, fieldLength[i], encoding);
            }
            if(converters != null && converters.length != 0 && converters[i] != null){
                element = converters[i].parse(element);
            }
            flv.add(element);
            offset += fieldLength[i];
        }
        return flv;
    }
    
    public String readLine() throws IOException{
        if(readerWrapper.getReader() instanceof BufferedReader){
            return ((BufferedReader)readerWrapper.getReader()).readLine();
        }else{
            return super.readLine();
        }
    }
    
    /**
     * {@link FLVReader.FLVElements}�̌J��Ԃ����擾����B<p>
     *
     * @return FLVElements�̌J��Ԃ�
     */
    public FLVIterator iterator(){
        if(iterator == null){
            iterator = new FLVIterator();
        }
        return iterator;
    }
    
    /**
     * {@link FLVReader.FLVElements}�̌J��Ԃ��B<p>
     *
     * @author M.Takata
     */
    public class FLVIterator{
        private boolean hasNext = false;
        private FLVElements elements = new FLVElements();
        
        private FLVIterator(){}
        
        /**
         * ����FLV�v�f�����邩�ǂ����𔻒肷��B<p>
         *
         * @return ����FLV�v�f������ꍇ��true
         * @exception IOException �ǂݍ��݂Ɏ��s�����ꍇ
         */
        public boolean hasNext() throws IOException{
            if(hasNext){
                return hasNext;
            }
            List result = readFLVLineList(elements);
            hasNext = result != null;
            return hasNext;
        }
        
        /**
         * ����FLV�v�f���擾����B<p>
         *
         * @return ����FLV�v�f�B����FLV�v�f���Ȃ��ꍇ��null
         * @exception IOException �ǂݍ��݂Ɏ��s�����ꍇ
         * @see #nextElements()
         */
        public Object next() throws IOException{
            return nextElements();
        }
        
        /**
         * ����FLV�v�f���擾����B<p>
         * �����Ŏ擾�����{@link FLVReader.FLVElements}�́A����ė��p�����B<br>
         *
         * @return ����FLV�v�f�B����FLV�v�f���Ȃ��ꍇ��null
         * @exception IOException �ǂݍ��݂Ɏ��s�����ꍇ
         */
        public FLVElements nextElements() throws IOException{
            if(!hasNext){
                if(!hasNext()){
                    return null;
                }
            }
            hasNext = false;
            return elements;
        }
    }
    
    /**
     * ���ڑ��̕����𐶐�����B<p>
     *
     * @return ���ڑ��̕���
     */
    public FLVReader cloneReader(){
        return cloneReader(new FLVReader());
    }
    
    /**
     * ���ڑ��̕����𐶐�����B<p>
     *
     * @param clone ���ڑ��̃C���X�^���X
     * @return ���ڑ��̕���
     */
    protected FLVReader cloneReader(FLVReader clone){
        clone.encoding = encoding;
        clone.fieldLength = fieldLength;
        clone.isIgnoreEmptyLine = isIgnoreEmptyLine;
        clone.commentPrefix = commentPrefix;
        if(converters != null && converters.length != 0){
            clone.converters = new PaddingStringConverter[converters.length];
            System.arraycopy(converters, 0, clone.converters, 0, converters.length);
        }
        return clone;
    }
    
    /**
     * FLV�`���f�[�^��1�s��\��FLV�v�f�B<p>
     * 
     * @author M.Takata
     */
    public class FLVElements extends ArrayList{
        
        private static final long serialVersionUID = 4888164167750345490L;
        
        private boolean wasNull;
        
        private FLVElements(){}
        
        /**
         * ����FLV�v�f���N���A����B<p>
         */
        public void clear(){
            wasNull = false;
            super.clear();
        }
        
        /**
         * �擾�����l��null���������ǂ����𔻒肷��B<p>
         * {@link #getInt(int)}�Ȃǂ́A���l�n��getter�Œl���擾�����ꍇ�A�l��null��󕶎��������ꍇ�ɁA0��Ԃ��B���̎��A�l��0�������̂�null�܂��͋󕶎��������̂��𔻒f����̂Ɏg�p����B<br>
         *
         * @return �擾�����l��null�������ꍇtrue
         */
        public boolean wasNull(){
            return wasNull;
        }
        
        /**
         * �w�肳�ꂽ�C���f�b�N�X�̗v�f���擾����B<p>
         *
         * @param index �C���f�b�N�X
         * @return �w�肳�ꂽ�C���f�b�N�X�̗v�f
         */
        public Object get(int index){
            Object obj = super.get(index);
            wasNull = obj == null;
            return obj;
        }
        
        /**
         * �w�肳�ꂽ�C���f�b�N�X�̗v�f��������擾����B<p>
         *
         * @param index �C���f�b�N�X
         * @return �w�肳�ꂽ�C���f�b�N�X�̗v�f������
         */
        public String getString(int index){
            String str = (String)get(index);
            wasNull = str == null;
            return str;
        }
        
        /**
         * �w�肳�ꂽ�C���f�b�N�X�̗v�f�o�C�g���擾����B<p>
         *
         * @param index �C���f�b�N�X
         * @return �w�肳�ꂽ�C���f�b�N�X�̗v�f�o�C�g
         * @exception NumberFormatException �v�f���o�C�g������łȂ��ꍇ
         */
        public byte getByte(int index) throws NumberFormatException{
            return getByte(index, 10);
        }
        
        /**
         * �w�肳�ꂽ�C���f�b�N�X�̗v�f�o�C�g���擾����B<p>
         * �w�肳�ꂽ�v�f��null�܂��͋󕶎��̏ꍇ�́A0��Ԃ��A{@link #wasNull()}��true��Ԃ��B<br>
         *
         * @param index �C���f�b�N�X
         * @param radix �
         * @return �w�肳�ꂽ�C���f�b�N�X�̗v�f�o�C�g
         * @exception NumberFormatException �v�f���o�C�g������łȂ��ꍇ
         */
        public byte getByte(int index, int radix) throws NumberFormatException{
            String str = getString(index);
            if(str != null && str.length() == 0){
                str = str.trim();
            }
            if(str == null || str.length() == 0){
                wasNull = true;
                return (byte)0;
            }
            return Byte.parseByte(str, radix);
        }
        
        /**
         * �w�肳�ꂽ�C���f�b�N�X�̗v�f���l���擾����B<p>
         * �w�肳�ꂽ�v�f��null�܂��͋󕶎��̏ꍇ�́A0��Ԃ��A{@link #wasNull()}��true��Ԃ��B<br>
         *
         * @param index �C���f�b�N�X
         * @return �w�肳�ꂽ�C���f�b�N�X�̗v�f���l
         * @exception NumberFormatException �v�f�����l������łȂ��ꍇ
         */
        public short getShort(int index) throws NumberFormatException{
            String str = getString(index);
            if(str != null && str.length() == 0){
                str = str.trim();
            }
            if(str == null || str.length() == 0){
                wasNull = true;
                return (short)0;
            }
            return Short.parseShort(str);
        }
        
        /**
         * �w�肳�ꂽ�C���f�b�N�X�̗v�f�������擾����B<p>
         * �w�肳�ꂽ�v�f��null�܂��͋󕶎��̏ꍇ�́A0��Ԃ��A{@link #wasNull()}��true��Ԃ��B<br>
         * �܂��A�w�肳�ꂽ�v�f���A�����������琬��ꍇ�́A1�����ڂ�Ԃ��B<br>
         *
         * @param index �C���f�b�N�X
         * @return �w�肳�ꂽ�C���f�b�N�X�̗v�f����
         */
        public char getChar(int index){
            final String str = getString(index);
            if(str == null || str.length() == 0){
                wasNull = true;
                return (char)0;
            }
            return str.charAt(0);
        }
        
        /**
         * �w�肳�ꂽ�C���f�b�N�X�̗v�f���l���擾����B<p>
         * �w�肳�ꂽ�v�f��null�܂��͋󕶎��̏ꍇ�́A0��Ԃ��A{@link #wasNull()}��true��Ԃ��B<br>
         *
         * @param index �C���f�b�N�X
         * @return �w�肳�ꂽ�C���f�b�N�X�̗v�f���l
         * @exception NumberFormatException �v�f�����l������łȂ��ꍇ
         */
        public int getInt(int index) throws NumberFormatException{
            String str = getString(index);
            if(str != null && str.length() == 0){
                str = str.trim();
            }
            if(str == null || str.length() == 0){
                wasNull = true;
                return (int)0;
            }
            return Integer.parseInt(str);
        }
        
        /**
         * �w�肳�ꂽ�C���f�b�N�X�̗v�f���l���擾����B<p>
         * �w�肳�ꂽ�v�f��null�܂��͋󕶎��̏ꍇ�́A0��Ԃ��A{@link #wasNull()}��true��Ԃ��B<br>
         *
         * @param index �C���f�b�N�X
         * @return �w�肳�ꂽ�C���f�b�N�X�̗v�f���l
         * @exception NumberFormatException �v�f�����l������łȂ��ꍇ
         */
        public long getLong(int index) throws NumberFormatException{
            String str = getString(index);
            if(str != null && str.length() == 0){
                str = str.trim();
            }
            if(str == null || str.length() == 0){
                wasNull = true;
                return 0l;
            }
            return Long.parseLong(str);
        }
        
        /**
         * �w�肳�ꂽ�C���f�b�N�X�̗v�f���l���擾����B<p>
         * �w�肳�ꂽ�v�f��null�܂��͋󕶎��̏ꍇ�́A0��Ԃ��A{@link #wasNull()}��true��Ԃ��B<br>
         *
         * @param index �C���f�b�N�X
         * @return �w�肳�ꂽ�C���f�b�N�X�̗v�f���l
         * @exception NumberFormatException �v�f�����l������łȂ��ꍇ
         */
        public float getFloat(int index) throws NumberFormatException{
            String str = getString(index);
            if(str != null && str.length() == 0){
                str = str.trim();
            }
            if(str == null || str.length() == 0){
                wasNull = true;
                return 0.0f;
            }
            return Float.parseFloat(str);
        }
        
        /**
         * �w�肳�ꂽ�C���f�b�N�X�̗v�f���l���擾����B<p>
         * �w�肳�ꂽ�v�f��null�܂��͋󕶎��̏ꍇ�́A0��Ԃ��A{@link #wasNull()}��true��Ԃ��B<br>
         *
         * @param index �C���f�b�N�X
         * @return �w�肳�ꂽ�C���f�b�N�X�̗v�f���l
         * @exception NumberFormatException �v�f�����l������łȂ��ꍇ
         */
        public double getDouble(int index) throws NumberFormatException{
            String str = getString(index);
            if(str != null && str.length() == 0){
                str = str.trim();
            }
            if(str == null || str.length() == 0){
                wasNull = true;
                return 0.0d;
            }
            return Double.parseDouble(str);
        }
        
        /**
         * �w�肳�ꂽ�C���f�b�N�X�̗v�f�t���O���擾����B<p>
         * �w�肳�ꂽ�v�f��null�܂��͋󕶎��̏ꍇ�́Afalse��Ԃ��A{@link #wasNull()}��true��Ԃ��B<br>
         *
         * @param index �C���f�b�N�X
         * @return �w�肳�ꂽ�C���f�b�N�X�̗v�f�t���O
         */
        public boolean getBoolean(int index){
            String str = getString(index);
            if(str != null && str.length() == 0){
                str = str.trim();
            }
            if(str == null || str.length() == 0){
                wasNull = true;
                return false;
            }
            return Boolean.valueOf(str).booleanValue();
        }
        
        /**
         * �w�肳�ꂽ�C���f�b�N�X�̗v�f���l���擾����B<p>
         * �w�肳�ꂽ�v�f��null�܂��͋󕶎��̏ꍇ�́Anull��Ԃ��A{@link #wasNull()}��true��Ԃ��B<br>
         *
         * @param index �C���f�b�N�X
         * @return �w�肳�ꂽ�C���f�b�N�X�̗v�f���l
         * @exception NumberFormatException �v�f�����l������łȂ��ꍇ
         */
        public BigInteger getBigInteger(int index) throws NumberFormatException{
            String str = getString(index);
            if(str != null && str.length() == 0){
                str = str.trim();
            }
            if(str == null || str.length() == 0){
                wasNull = true;
                return null;
            }
            return new BigInteger(str);
        }
        
        /**
         * �w�肳�ꂽ�C���f�b�N�X�̗v�f���l���擾����B<p>
         * �w�肳�ꂽ�v�f��null�܂��͋󕶎��̏ꍇ�́Anull��Ԃ��A{@link #wasNull()}��true��Ԃ��B<br>
         *
         * @param index �C���f�b�N�X
         * @return �w�肳�ꂽ�C���f�b�N�X�̗v�f���l
         * @exception NumberFormatException �v�f�����l������łȂ��ꍇ
         */
        public BigDecimal getBigDecimal(int index) throws NumberFormatException{
            String str = getString(index);
            if(str != null && str.length() == 0){
                str = str.trim();
            }
            if(str == null || str.length() == 0){
                wasNull = true;
                return null;
            }
            return new BigDecimal(str);
        }
    }
    
    private static class ReaderWrapper extends Reader{
        
        private Reader realReader;
        private static Method READ_CHARBUFFER_METHOD = null;
        static{
            try{
                READ_CHARBUFFER_METHOD = Reader.class.getMethod(
                    "read",
                    new Class[]{CharBuffer.class}
                );
            }catch(NoSuchMethodException e){
            }
        }
        
        public ReaderWrapper(){
        }
        
        public ReaderWrapper(Reader reader){
            realReader = reader;
        }
        
        public Reader getReader(){
            return realReader;
        }
        
        public void setReader(Reader reader){
            realReader = reader;
        }
        
        public int read(CharBuffer target) throws IOException{
            if(READ_CHARBUFFER_METHOD == null){
                throw new UnsupportedOperationException("No such method.");
            }
            if(realReader == null){
                return -1;
            }else{
                try{
                    return ((Integer)READ_CHARBUFFER_METHOD.invoke(realReader, new Object[]{target})).intValue();
                }catch(InvocationTargetException e){
                    Throwable th = e.getTargetException();
                    if(th instanceof IOException){
                        throw (IOException)th;
                    }else if(th instanceof RuntimeException){
                        throw (RuntimeException)th;
                    }else if(th instanceof Error){
                        throw (Error)th;
                    }else{
                        throw new UndeclaredThrowableException(th);
                    }
                }catch(IllegalAccessException e){
                    throw new UnsupportedOperationException(e.toString());
                }
            }
        }
        
        public int read() throws IOException{
            if(realReader == null){
                return -1;
            }else{
                return realReader.read();
            }
        }
        
        public int read(char[] cbuf) throws IOException{
            if(realReader == null){
                return -1;
            }else{
                return realReader.read(cbuf);
            }
        }
        
        public int read(char[] cbuf, int off, int len) throws IOException{
            if(realReader == null){
                return -1;
            }else{
                return realReader.read(cbuf, off, len);
            }
        }
        
        public long skip(long n) throws IOException{
            if(realReader == null){
                return 0;
            }else{
                return realReader.skip(n);
            }
        }
        
        public boolean ready() throws IOException{
            if(realReader == null){
                return false;
            }else{
                return realReader.ready();
            }
        }
        
        public boolean markSupported(){
            if(realReader == null){
                return false;
            }else{
                return realReader.markSupported();
            }
        }
        
        public void mark(int readAheadLimit) throws IOException{
            if(realReader == null){
                throw new IOException("Reader is null.");
            }else{
                realReader.mark(readAheadLimit);
            }
        }
        
        public void reset() throws IOException{
            if(realReader != null){
                realReader.reset();
            }
        }
        
        public void close() throws IOException{
            if(realReader != null){
                realReader.close();
            }
        }
    }
}
