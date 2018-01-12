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

import java.lang.reflect.*;
import java.io.*;
import java.util.List;

import jp.ossc.nimbus.util.converter.PaddingStringConverter;

/**
 * FLV�`����Writer�N���X�B<p>
 * <pre>
 * import java.io.*;
 * import jp.ossc.nimbus.io.FLVWriter;
 *
 * FileOutputStream fos = new FileOutputStream("sample.csv");
 * OutputStreamWriter osw = new OutputStreamWriter(fos, "UTF-8");
 * FLVWriter writer = new FLVWriter(
 *     osw,
 *     new PaddingStringConverter[]{
 *         new PaddingStringConverter(10),
 *         new PaddingStringConverter(12),
 *     }
 * );
 * String[] flv = new String[2];
 * try{
 *     flv[0] = "hoge";
 *     flv[1] = "100";
 *     writer.writeFLV(flv);
 *        :
 * }finally{
 *     writer.close();
 * }
 * </pre>
 * 
 * @author M.Takata
 */
public class FLVWriter extends BufferedWriter{
    
    /**
     * �f�t�H���g�̉��s�����B<p>
     */
    public static final String DEFAULT_LINE_SEPARATOR
         = System.getProperty("line.separator");
    protected String lineSeparator = DEFAULT_LINE_SEPARATOR;
    protected boolean isAppendElement;
    protected int fieldIndex;
    protected String nullValue;
    protected PaddingStringConverter[] converters;
    
    protected WriterWrapper writerWrapper;
    
    /**
     * �f�t�H���g�̏������݃o�b�t�@�T�C�Y�������ڑ��̃C���X�^���X�𐶐�����B<p>
     */
    public FLVWriter(){
        this(new WriterWrapper());
    }
    
    /**
     * �f�t�H���g�̏������݃o�b�t�@�T�C�Y�����C���X�^���X�𐶐�����B<p>
     *
     * @param writer �������ݐ��Writer
     */
    public FLVWriter(Writer writer){
        this(writer, null);
    }
    
    /**
     * �f�t�H���g�̏������݃o�b�t�@�T�C�Y�����C���X�^���X�𐶐�����B<p>
     *
     * @param writer �������ݐ��Writer
     * @param convs �t�B�[���h���p�f�B���O����R���o�[�^�z��
     */
    public FLVWriter(Writer writer, PaddingStringConverter[] convs){
        super(writer instanceof WriterWrapper ? writer : new WriterWrapper(writer));
        writerWrapper = (WriterWrapper)lock;
        converters = convs;
    }
    
    /**
     * �w�肳�ꂽ�������݃o�b�t�@�T�C�Y�������ڑ��̃C���X�^���X�𐶐�����B<p>
     *
     * @param size �������݃o�b�t�@�T�C�Y
     */
    public FLVWriter(int size){
        this(new WriterWrapper(), size);
    }
    
    /**
     * �w�肳�ꂽ�������݃o�b�t�@�T�C�Y�����C���X�^���X�𐶐�����B<p>
     *
     * @param writer �������ݐ��Writer
     * @param size �������݃o�b�t�@�T�C�Y
     */
    public FLVWriter(Writer writer, int size){
        this(writer, null, size);
    }
    
    /**
     * �w�肳�ꂽ�������݃o�b�t�@�T�C�Y�����C���X�^���X�𐶐�����B<p>
     *
     * @param writer �������ݐ��Writer
     * @param convs �t�B�[���h���p�f�B���O����R���o�[�^�z��
     * @param size �������݃o�b�t�@�T�C�Y
     */
    public FLVWriter(Writer writer, PaddingStringConverter[] convs, int size){
        super(writer instanceof WriterWrapper ? writer : new WriterWrapper(writer), size);
        writerWrapper = (WriterWrapper)lock;
        converters = convs;
    }
    
    /**
     * Writer��ݒ肷��B<p>
     *
     * @param writer Writer
     */
    public void setWriter(Writer writer){
        writerWrapper.setWriter(writer);
        isAppendElement = false;
        fieldIndex = 0;
    }
    
    /**
     * �e�t�B�[���h�̃p�f�B���O���s���R���o�[�^��ݒ肷��B<p>
     *
     * @param convs �p�f�B���O���s���R���o�[�^�̔z��
     */
    public void setFieldPaddingStringConverter(PaddingStringConverter[] convs){
        converters = convs;
    }
    
    /**
     * �e�t�B�[���h�̃p�f�B���O���s���R���o�[�^���擾����B<p>
     *
     * @return �p�f�B���O���s���R���o�[�^�̔z��
     */
    public PaddingStringConverter[] getFieldPaddingStringConverter(){
        return converters;
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
     * �s��؂蕶�����������ށB<p>
     * �s��؂蕶���́A{@link #getLineSeparator()}���g�p����B<br>
     * 
     * @exception IOException ���o�̓G���[�����������ꍇ
     */
    public void newLine() throws IOException{
        super.write(lineSeparator);
        isAppendElement = false;
        fieldIndex = 0;
    }
    
    /**
     * FLV�v�f��������������ށB<p>
     * �p�f�B���O�����������ōs���B<br>
     * 
     * @param element FLV�v�f������
     * @exception IOException ���o�̓G���[�����������ꍇ
     */
    public void writeElement(String element) throws IOException{
        if(converters != null && converters.length != 0 && converters[fieldIndex] != null){
            element = converters[fieldIndex].padding(element);
        }
        super.write(element);
        isAppendElement = true;
        fieldIndex++;
    }
    
    /**
     * FLV�v�f���������ށB<p>
     * 
     * @param element FLV�v�f
     * @exception IOException ���o�̓G���[�����������ꍇ
     * @see #writeElement(String)
     */
    public void writeElement(boolean element) throws IOException{
        writeElement(Boolean.toString(element));
    }
    
    /**
     * FLV�v�f���������ށB<p>
     * 
     * @param element FLV�v�f
     * @exception IOException ���o�̓G���[�����������ꍇ
     * @see #writeElement(String)
     */
    public void writeElement(byte element) throws IOException{
        writeElement(Byte.toString(element));
    }
    
    /**
     * FLV�v�f���������ށB<p>
     * 
     * @param element FLV�v�f
     * @exception IOException ���o�̓G���[�����������ꍇ
     * @see #writeElement(String)
     */
    public void writeElement(char element) throws IOException{
        writeElement(Character.toString(element));
    }
    
    /**
     * FLV�v�f���������ށB<p>
     * 
     * @param element FLV�v�f
     * @exception IOException ���o�̓G���[�����������ꍇ
     * @see #writeElement(String)
     */
    public void writeElement(short element) throws IOException{
        writeElement(Short.toString(element));
    }
    
    /**
     * FLV�v�f���������ށB<p>
     * 
     * @param element FLV�v�f
     * @exception IOException ���o�̓G���[�����������ꍇ
     * @see #writeElement(String)
     */
    public void writeElement(int element) throws IOException{
        writeElement(Integer.toString(element));
    }
    
    /**
     * FLV�v�f���������ށB<p>
     * 
     * @param element FLV�v�f
     * @exception IOException ���o�̓G���[�����������ꍇ
     * @see #writeElement(String)
     */
    public void writeElement(long element) throws IOException{
        writeElement(Long.toString(element));
    }
    
    /**
     * FLV�v�f���������ށB<p>
     * 
     * @param element FLV�v�f
     * @exception IOException ���o�̓G���[�����������ꍇ
     * @see #writeElement(String)
     */
    public void writeElement(float element) throws IOException{
        writeElement(Float.toString(element));
    }
    
    /**
     * FLV�v�f���������ށB<p>
     * 
     * @param element FLV�v�f
     * @exception IOException ���o�̓G���[�����������ꍇ
     * @see #writeElement(String)
     */
    public void writeElement(double element) throws IOException{
        writeElement(Double.toString(element));
    }
    
    /**
     * FLV�v�f���������ށB<p>
     * 
     * @param element FLV�v�f
     * @exception IOException ���o�̓G���[�����������ꍇ
     * @see #writeElement(String)
     */
    public void writeElement(Object element) throws IOException{
        writeElement(element == null ? (String)null : element.toString());
    }
    
    /**
     * �w�肳�ꂽ������z���FLV�Ƃ��ď������ށB<p>
     * ���s�����̒ǉ��A�Z�p���[�^�̒ǉ��A�Z�p���[�^�������܂܂�Ă���ꍇ�̃G�X�P�[�v�A�͂ݕ����ł̈͂ݏ����������ōs���B<br>
     *
     * @param elements FLV�`���ŏo�͂��镶����z��
     * @exception IOException ���o�̓G���[�����������ꍇ
     */
    public void writeFLV(String[] elements) throws IOException{
        for(int i = 0; i < elements.length; i++){
            writeElement(elements[i]);
        }
        newLine();
    }
    
    /**
     * �w�肳�ꂽ�z���FLV�Ƃ��ď������ށB<p>
     *
     * @param elements FLV�`���ŏo�͂���z��
     * @exception IOException ���o�̓G���[�����������ꍇ
     * @see #writeFLV(String[])
     */
    public void writeFLV(Object[] elements) throws IOException{
        for(int i = 0; i < elements.length; i++){
            writeElement(elements[i]);
        }
        newLine();
    }
    
    /**
     * �w�肳�ꂽ���X�g��FLV�Ƃ��ď������ށB<p>
     * ���s�����̒ǉ��A�Z�p���[�^�̒ǉ��A�Z�p���[�^�������܂܂�Ă���ꍇ�̃G�X�P�[�v�A�͂ݕ����ł̈͂ݏ����������ōs���B<br>
     *
     * @param elements FLV�`���ŏo�͂��郊�X�g
     * @exception IOException ���o�̓G���[�����������ꍇ
     */
    public void writeFLV(List elements) throws IOException{
        for(int i = 0, imax = elements.size(); i < imax; i++){
            writeElement(elements.get(i));
        }
        newLine();
    }
    
    /**
     * ���ڑ��̕����𐶐�����B<p>
     *
     * @return ���ڑ��̕���
     */
    public FLVWriter cloneWriter(){
        return cloneWriter(new FLVWriter());
    }
    
    /**
     * ���ڑ��̕����𐶐�����B<p>
     *
     * @param clone ���ڑ��̃C���X�^���X
     * @return ���ڑ��̕���
     */
    protected FLVWriter cloneWriter(FLVWriter clone){
        clone.lineSeparator = lineSeparator;
        if(converters != null && converters.length != 0){
            clone.converters = new PaddingStringConverter[converters.length];
            System.arraycopy(converters, 0, clone.converters, 0, converters.length);
        }
        return clone;
    }
    
    private static class WriterWrapper extends Writer{
        
        private Writer realWriter;
        private static Method APPEND1 = null;
        private static Method APPEND2 = null;
        private static Method APPEND3 = null;
        static{
            try{
                APPEND1 = Writer.class.getMethod(
                    "append",
                    new Class[]{CharSequence.class}
                );
            }catch(NoSuchMethodException e){
            }
            try{
                APPEND2 = Writer.class.getMethod(
                    "append",
                    new Class[]{CharSequence.class, Integer.TYPE, Integer.TYPE}
                );
            }catch(NoSuchMethodException e){
            }
            try{
                APPEND3 = Writer.class.getMethod(
                    "append",
                    new Class[]{Character.TYPE}
                );
            }catch(NoSuchMethodException e){
            }
        }
        
        public WriterWrapper(){
        }
        
        public WriterWrapper(Writer writer){
            realWriter = writer;
        }
        
        public Writer getWriter(){
            return realWriter;
        }
        
        public void setWriter(Writer writer){
            realWriter = writer;
        }
        
        public void write(int c) throws IOException{
            if(realWriter == null){
                throw new IOException("Writer is null.");
            }
            realWriter.write(c);
        }
        
        public void write(char[] cbuf) throws IOException{
            if(realWriter == null){
                throw new IOException("Writer is null.");
            }
            realWriter.write(cbuf);
        }
        
        public void write(char[] cbuf, int off, int len) throws IOException{
            if(realWriter == null){
                throw new IOException("Writer is null.");
            }
            realWriter.write(cbuf, off, len);
        }
        
        public void write(String str) throws IOException{
            if(realWriter == null){
                throw new IOException("Writer is null.");
            }
            realWriter.write(str);
        }
        
        public void write(String str, int off, int len) throws IOException{
            if(realWriter == null){
                throw new IOException("Writer is null.");
            }
            realWriter.write(str, off, len);
        }
        
        public Writer append(CharSequence csq) throws IOException{
            if(realWriter == null){
                throw new IOException("Writer is null.");
            }
            if(APPEND1 == null){
                throw new UnsupportedOperationException("No such method.");
            }
            try{
                return (Writer)APPEND1.invoke(realWriter, new Object[]{csq});
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
        
        public Writer append(CharSequence csq, int off, int len) throws IOException{
            if(realWriter == null){
                throw new IOException("Writer is null.");
            }
            if(APPEND2 == null){
                throw new UnsupportedOperationException("No such method.");
            }
            try{
                return (Writer)APPEND2.invoke(
                    realWriter,
                    new Object[]{csq, new Integer(off), new Integer(len)});
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
        
        public Writer append(char c) throws IOException{
            if(realWriter == null){
                throw new IOException("Writer is null.");
            }
            if(APPEND3 == null){
                throw new UnsupportedOperationException("No such method.");
            }
            try{
                return (Writer)APPEND3.invoke(
                    realWriter,
                    new Object[]{new Character(c)}
                );
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
        
        public void flush() throws IOException{
            if(realWriter != null){
                realWriter.flush();
            }
        }
        
        public void close() throws IOException{
            if(realWriter != null){
                realWriter.close();
            }
        }
    }
}