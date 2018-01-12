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
package jp.ossc.nimbus.beans.dataset;

import java.util.*;
import java.lang.reflect.*;

/**
 * �ėp�I�ȃf�[�^�W����\��Bean�B<p>
 * ������{@link Header �w�b�_�[}�ƁA������{@link RecordList ���R�[�h���X�g}�𖼑O�ƕR�t���ĊǗ�����B<br>
 * �w�b�_�[�͕����̃v���p�e�B������Bean�ŁA���R�[�h���X�g�́A�w�b�_�[�Ɠ��l�ɕ����̃v���p�e�B������{@link Record ���R�[�h}�Ƃ���Bean�����X�g������Bean�ł���B<br>
 * �w�b�_�[�A�y�у��R�[�h���X�g�̃��R�[�h�́A�ǂ̂悤��Bean�ɂ���̂��i�v���p�e�B���A�^�Ȃǁj�����R�[�h�X�L�[�}�Œ�`���āA���I��Bean����鎖���ł���B<br>
 * {@link RecordSchema ���R�[�h�X�L�[�}}�́A{@link PropertySchema �v���p�e�B�X�L�[�}}�̏W���ł���A<br>
 * <pre>
 *   �v���p�e�B�X�L�[�}�̎����N���X��:�v���p�e�B�X�L�[�}��`
 *   �v���p�e�B�X�L�[�}�̎����N���X��:�v���p�e�B�X�L�[�}��`
 *                   :
 * </pre>
 * �Ƃ����悤�ɁA�v���p�e�B�̐��������s��؂�Œ�`����B<br>
 * �܂��A�v���p�e�B�X�L�[�}�̎����N���X���͏ȗ��\�ŁA�ȗ������ꍇ�́A{@link DefaultPropertySchema}���K�p�����B<br>
 * �ȉ��ɃT���v���R�[�h�������B<br>
 * <pre>
 *     import jp.ossc.nimbus.beans.dataset.*;
 *     
 *     // �f�[�^�Z�b�g�𐶐�
 *     DataSet dataSet = new DataSet("sample");
 *     
 *     // �f�[�^�Z�b�g�̃X�L�[�}���ȉ��̂悤�ɒ�`����
 *     // �w�b�_�F
 *     //   �v���p�e�B��  �^
 *     //        A        java.lang.String
 *     //        B        long
 *     // 
 *     // ���R�[�h���X�g�F
 *     //   �v���p�e�B��  �^
 *     //        C        int
 *     //        D        java.lang.String
 *     //        E        java.lang.String
 *     dataSet.setSchema(
 *         ":A,java.lang.String\n"
 *             + ":B,long",
 *         ":C,int\n"
 *             + ":D,java.lang.String\n"
 *             + ":E,java.lang.String"
 *     );
 *     
 *     // �w�b�_���擾���Ēl��ݒ肷��
 *     Header header = dataSet.getHeader();
 *     header.setProperty("A", "hoge");
 *     header.setProperty("B", 100l);
 *     
 *     // ���R�[�h���X�g���擾����
 *     RecordList recordList = dataSet.getRecordList();
 *     // ���R�[�h1�𐶐����āA�l��ݒ肷��
 *     Record record1 = recordList.createRecord();
 *     record1.setProperty("C", 1);
 *     record1.setProperty("D", "hoge1");
 *     record1.setProperty("E", "fuga1");
 *     recordList.addRecord(record1);
 *     // ���R�[�h2�𐶐����āA�l��ݒ肷��
 *     Record record2 = recordList.createRecord();
 *     record2.setProperty("C", 2);
 *     record2.setProperty("D", "hoge2");
 *     record2.setProperty("E", "fuga2");
 *     recordList.addRecord(record2);
 * </pre>
 *
 * @author M.Takata
 */
public class DataSet implements java.io.Serializable, Cloneable{
    
    private static final long serialVersionUID = 452460154073106633L;
    
    /**
     * �f�[�^�Z�b�g�̖��O�B<p>
     */
    protected String name;
    
    /**
     * �w�b�_�[�̃}�b�v�B<p>
     * �L�[�̓w�b�_�[���A�l��{@link Header �w�b�_�[}
     */
    protected Map headerMap;
    
    /**
     * ���R�[�h���X�g�̃}�b�v�B<p>
     * �L�[�̓��R�[�h���X�g���A�l��{@link RecordList ���R�[�h���X�g}
     */
    protected Map recordListMap;
    
    /**
     * �l�X�g���ꂽ���R�[�h���X�g�̃X�L�[�}�̃}�b�v�B<p>
     * �L�[�̓��R�[�h���X�g���A�l��{@link RecordSchema ���R�[�h�X�L�[�}}
     */
    protected transient Map nestedRecordListMap;
    
    /**
     * �l�X�g���ꂽ���R�[�h���X�g�̃N���X�̃}�b�v�B<p>
     * �L�[�̓��R�[�h���X�g���A�l�̓N���X
     */
    protected transient Map nestedRecordListClassMap;
    
    /**
     * �l�X�g���ꂽ���R�[�h�̃X�L�[�}�̃}�b�v�B<p>
     * �L�[�̓��R�[�h���A�l��{@link RecordSchema ���R�[�h�X�L�[�}}
     */
    protected transient Map nestedRecordMap;
    
    /**
     * �l�X�g���ꂽ���R�[�h�̃N���X�̃}�b�v�B<p>
     * �L�[�̓��R�[�h���A�l�̓N���X
     */
    protected transient Map nestedRecordClassMap;
    
    /**
     * ���������邩�ǂ����̃t���O�B<p>
     * �f�t�H���g�́Atrue�B<br>
     */
    protected boolean isSynchronized = true;
    
    /**
     * ��̃f�[�^�Z�b�g�𐶐�����B<p>
     */
    public DataSet(){
        this(true);
    }
    
    /**
     * ��̃f�[�^�Z�b�g�𐶐�����B<p>
     *
     * @param isSynch ����������ꍇtrue
     */
    public DataSet(boolean isSynch){
        isSynchronized = isSynch;
    }
    
    /**
     * ���O�t���̃f�[�^�Z�b�g�𐶐�����B<p>
     *
     * @param name ���O
     */
    public DataSet(String name){
        this(name, true);
    }
    
    /**
     * ���O�t���̃f�[�^�Z�b�g�𐶐�����B<p>
     *
     * @param name ���O
     * @param isSynch ����������ꍇtrue
     */
    public DataSet(String name, boolean isSynch){
        this.name = name;
        isSynchronized = isSynch;
    }
    
    /**
     * �f�[�^�Z�b�g�����擾����B<p>
     *
     * @return �f�[�^�Z�b�g��
     */
    public String getName(){
        return name;
    }
    
    /**
     * �f�[�^�Z�b�g����ݒ肷��B<p>
     *
     * @param name �f�[�^�Z�b�g��
     */
    public void setName(String name){
        this.name = name;
    }
    
    /**
     * ���O�������Ȃ�{@link Header �w�b�_�[}�̃X�L�[�}��ݒ肷��B<p>
     * {@link #setHeaderSchema(String, String) setHeaderSchema(null, schema)}���Ăяo���̂Ɠ����B<br>
     *
     * @param schema �X�L�[�}
     * @exception PropertySchemaDefineException �v���p�e�B�̃X�L�[�}��`�Ɏ��s�����ꍇ
     */
    public void setHeaderSchema(String schema)
     throws PropertySchemaDefineException{
        setHeaderSchema(null, schema);
    }
    
    /**
     * �w�肵�����O��{@link Header �w�b�_�[}�𐶐�����B<p>
     *
     * @param name �w�b�_�[��
     * @param schema �X�L�[�}
     * @return �w�b�_�[
     * @exception PropertySchemaDefineException �v���p�e�B�̃X�L�[�}��`�Ɏ��s�����ꍇ
     */
    protected Header createHeader(String name, String schema)
     throws PropertySchemaDefineException{
        return new Header(name, schema);
    }
    
    /**
     * �w�肵�����O��{@link Header �w�b�_�[}�𐶐�����B<p>
     *
     * @param name �w�b�_�[��
     * @param schema �X�L�[�}
     * @return �w�b�_�[
     * @exception PropertySchemaDefineException �v���p�e�B�̃X�L�[�}��`�Ɏ��s�����ꍇ
     */
    protected Header createHeader(String name, RecordSchema schema)
     throws PropertySchemaDefineException{
        return new Header(name, schema);
    }
    
    /**
     * �w�肵�����O��{@link Header �w�b�_�[}�̃X�L�[�}��ݒ肷��B<p>
     *
     * @param name �w�b�_�[��
     * @param schema �X�L�[�}
     * @exception PropertySchemaDefineException �v���p�e�B�̃X�L�[�}��`�Ɏ��s�����ꍇ
     */
    public void setHeaderSchema(String name, String schema)
     throws PropertySchemaDefineException{
        if(headerMap == null){
            headerMap = isSynchronized ? Collections.synchronizedMap(new LinkedHashMap()) : new LinkedHashMap();
        }
        headerMap.put(name, createHeader(name, schema));
    }
    
    /**
     * ���O�������Ȃ�{@link Header �w�b�_�[}�̃X�L�[�}��ݒ肷��B<p>
     * {@link #setHeaderSchema(String, RecordSchema) setHeaderSchema(null, schema)}���Ăяo���̂Ɠ����B<br>
     *
     * @param schema �X�L�[�}
     */
    public void setHeaderSchema(RecordSchema schema){
        setHeaderSchema(null, schema);
    }
    
    /**
     * �w�肵�����O��{@link Header �w�b�_�[}�̃X�L�[�}��ݒ肷��B<p>
     *
     * @param name �w�b�_�[��
     * @param schema �X�L�[�}
     */
    public void setHeaderSchema(String name, RecordSchema schema){
        if(headerMap == null){
            headerMap = isSynchronized ? Collections.synchronizedMap(new LinkedHashMap()) : new LinkedHashMap();
        }
        headerMap.put(name, createHeader(name, schema));
    }
    
    /**
     * �w�肵�����O��{@link Header �w�b�_�[}���w�肵���N���X�Őݒ肷��B<p>
     *
     * @param name �w�b�_�[��
     * @param clazz �w�b�_�[�N���X
     * @exception PropertySchemaDefineException �v���p�e�B�̃X�L�[�}��`�Ɏ��s�����ꍇ
     */
    public void setHeaderClass(String name, Class clazz)
     throws PropertySchemaDefineException{
        Header header = null;
        try{
            Constructor c = clazz.getConstructor(new Class[]{String.class});
            header = (Header)c.newInstance(new Object[]{name});
        }catch(NoSuchMethodException e){
            try{
                header = (Header)clazz.newInstance();
            }catch(InstantiationException e2){
                throw new PropertySchemaDefineException(null, e2);
            }catch(IllegalAccessException e2){
                throw new PropertySchemaDefineException(null, e2);
            }
        }catch(InstantiationException e){
            throw new PropertySchemaDefineException(null, e);
        }catch(IllegalAccessException e){
            throw new PropertySchemaDefineException(null, e);
        }catch(InvocationTargetException e){
            throw new PropertySchemaDefineException(null, e.getTargetException());
        }catch(ClassCastException e){
            throw new PropertySchemaDefineException(null, e);
        }
        if(headerMap == null){
            headerMap = isSynchronized ? Collections.synchronizedMap(new LinkedHashMap()) : new LinkedHashMap();
        }
        headerMap.put(name, header);
    }
    
    /**
     * ���O�������Ȃ�{@link Header �w�b�_�[}���w�肵���N���X�Őݒ肷��B<p>
     * {@link #setHeaderClass(String, Class) setHeaderClass(null, clazz)}���Ăяo���̂Ɠ����B<br>
     *
     * @param clazz �w�b�_�[�N���X
     */
    public void setHeaderClass(Class clazz){
        setHeaderClass(null, clazz);
    }
    
    /**
     * ���O�������Ȃ�{@link RecordList ���R�[�h���X�g}�̃X�L�[�}��ݒ肷��B<p>
     * {@link #setRecordListSchema(String, String) setRecordListSchema(null, schema)}���Ăяo���̂Ɠ����B<br>
     *
     * @param schema �X�L�[�}
     * @exception PropertySchemaDefineException �v���p�e�B�̃X�L�[�}��`�Ɏ��s�����ꍇ
     */
    public void setRecordListSchema(String schema)
     throws PropertySchemaDefineException{
        setRecordListSchema(null, schema);
    }
    
    /**
     * �w�肵�����O��{@link RecordList ���R�[�h���X�g}�𐶐�����B<p>
     *
     * @param name ���R�[�h���X�g��
     * @param schema �X�L�[�}
     * @return ���R�[�h���X�g
     * @exception PropertySchemaDefineException �v���p�e�B�̃X�L�[�}��`�Ɏ��s�����ꍇ
     */
    protected RecordList createRecordList(String name, String schema)
     throws PropertySchemaDefineException{
        return new RecordList(name, schema, isSynchronized);
    }
    
    /**
     * �w�肵�����O��{@link RecordList ���R�[�h���X�g}�𐶐�����B<p>
     *
     * @param name ���R�[�h���X�g��
     * @param schema �X�L�[�}
     * @return ���R�[�h���X�g
     * @exception PropertySchemaDefineException �v���p�e�B�̃X�L�[�}��`�Ɏ��s�����ꍇ
     */
    protected RecordList createRecordList(String name, RecordSchema schema)
     throws PropertySchemaDefineException{
        return new RecordList(name, schema, isSynchronized);
    }
    
    /**
     * �w�肵�����O��{@link RecordList ���R�[�h���X�g}�̃X�L�[�}��ݒ肷��B<p>
     *
     * @param name ���R�[�h���X�g��
     * @param schema �X�L�[�}
     * @exception PropertySchemaDefineException �v���p�e�B�̃X�L�[�}��`�Ɏ��s�����ꍇ
     */
    public void setRecordListSchema(String name, String schema)
     throws PropertySchemaDefineException{
        
        if(recordListMap == null){
            recordListMap = isSynchronized ? Collections.synchronizedMap(new LinkedHashMap()) : new LinkedHashMap();
        }
        recordListMap.put(
            name,
            createRecordList(name, schema)
        );
    }
    
    /**
     * ���O�������Ȃ�{@link RecordList ���R�[�h���X�g}�̃X�L�[�}��ݒ肷��B<p>
     * {@link #setRecordListSchema(String, RecordSchema) setRecordListSchema(null, schema)}���Ăяo���̂Ɠ����B<br>
     *
     * @param schema �X�L�[�}
     */
    public void setRecordListSchema(RecordSchema schema){
        setRecordListSchema(null, schema);
    }
    
    /**
     * �w�肵�����O��{@link RecordList ���R�[�h���X�g}�̃X�L�[�}��ݒ肷��B<p>
     *
     * @param name ���R�[�h���X�g��
     * @param schema �X�L�[�}
     */
    public void setRecordListSchema(String name, RecordSchema schema){
        
        if(recordListMap == null){
            recordListMap = isSynchronized ? Collections.synchronizedMap(new LinkedHashMap()) : new LinkedHashMap();
        }
        recordListMap.put(
            name,
            createRecordList(name, schema)
        );
    }
    
    /**
     * �w�肵�����O�̃l�X�g����{@link RecordList ���R�[�h���X�g}�̃X�L�[�}��ݒ肷��B<p>
     *
     * @param name ���R�[�h���X�g��
     * @param schema �X�L�[�}
     * @exception PropertySchemaDefineException �v���p�e�B�̃X�L�[�}��`�Ɏ��s�����ꍇ
     */
    public void setNestedRecordListSchema(String name, String schema)
     throws PropertySchemaDefineException{
        
        if(nestedRecordListMap == null){
            nestedRecordListMap = isSynchronized ? Collections.synchronizedMap(new LinkedHashMap()) : new LinkedHashMap();
        }
        RecordSchema recSchema = RecordSchema.getInstance(schema);
        nestedRecordListMap.put(
            name,
            recSchema.getSchema()
        );
    }
    
    /**
     * �w�肵�����O�̃l�X�g����{@link RecordList ���R�[�h���X�g}�̃N���X��ݒ肷��B<p>
     *
     * @param name ���R�[�h���X�g��
     * @param clazz �N���X
     * @exception PropertySchemaDefineException �v���p�e�B�̃X�L�[�}��`�Ɏ��s�����ꍇ
     */
    public void setNestedRecordListClass(String name, Class clazz)
     throws PropertySchemaDefineException{
        RecordList list = null;
        try{
            Constructor c = clazz.getConstructor(new Class[]{String.class});
            list = (RecordList)c.newInstance(new Object[]{name});
        }catch(NoSuchMethodException e){
            try{
                list = (RecordList)clazz.newInstance();
            }catch(InstantiationException e2){
                throw new PropertySchemaDefineException(null, e2);
            }catch(IllegalAccessException e2){
                throw new PropertySchemaDefineException(null, e2);
            }
        }catch(InstantiationException e){
            throw new PropertySchemaDefineException(null, e);
        }catch(IllegalAccessException e){
            throw new PropertySchemaDefineException(null, e);
        }catch(InvocationTargetException e){
            throw new PropertySchemaDefineException(null, e.getTargetException());
        }catch(ClassCastException e){
            throw new PropertySchemaDefineException(null, e);
        }
        if(nestedRecordListMap == null){
            nestedRecordListMap = isSynchronized ? Collections.synchronizedMap(new LinkedHashMap()) : new LinkedHashMap();
        }
        nestedRecordListMap.put(
            name,
            list.getSchema()
        );
        if(nestedRecordListClassMap == null){
            nestedRecordListClassMap = isSynchronized ? Collections.synchronizedMap(new LinkedHashMap()) : new LinkedHashMap();
        }
        nestedRecordListClassMap.put(
            name,
            clazz
        );
    }
    
    /**
     * �w�肵�����O��{@link RecordList ���R�[�h���X�g}���w�肵���N���X�Őݒ肷��B<p>
     *
     * @param name ���R�[�h���X�g��
     * @param clazz ���R�[�h���X�g�N���X
     * @exception PropertySchemaDefineException �v���p�e�B�̃X�L�[�}��`�Ɏ��s�����ꍇ
     */
    public void setRecordListClass(String name, Class clazz)
     throws PropertySchemaDefineException{
        RecordList list = null;
        try{
            Constructor c = clazz.getConstructor(new Class[]{String.class});
            list = (RecordList)c.newInstance(new Object[]{name});
        }catch(NoSuchMethodException e){
            try{
                list = (RecordList)clazz.newInstance();
            }catch(InstantiationException e2){
                throw new PropertySchemaDefineException(null, e2);
            }catch(IllegalAccessException e2){
                throw new PropertySchemaDefineException(null, e2);
            }
        }catch(InstantiationException e){
            throw new PropertySchemaDefineException(null, e);
        }catch(IllegalAccessException e){
            throw new PropertySchemaDefineException(null, e);
        }catch(InvocationTargetException e){
            throw new PropertySchemaDefineException(null, e.getTargetException());
        }catch(ClassCastException e){
            throw new PropertySchemaDefineException(null, e);
        }
        if(recordListMap == null){
            recordListMap = isSynchronized ? Collections.synchronizedMap(new LinkedHashMap()) : new LinkedHashMap();
        }
        recordListMap.put(name, list);
    }
    
    /**
     * ���O�������Ȃ�{@link RecordList ���R�[�h���X�g}���w�肵���N���X�Őݒ肷��B<p>
     * {@link #setRecordListClass(String, Class) setRecordListClass(null, clazz)}���Ăяo���̂Ɠ����B<br>
     *
     * @param clazz ���R�[�h���X�g�N���X
     */
    public void setRecordListClass(Class clazz){
        setRecordListClass(null, clazz);
    }
    
    /**
     * �w�肵�����O�̃l�X�g����{@link RecordList ���R�[�h���X�g}�̃X�L�[�}��ݒ肷��B<p>
     *
     * @param name ���R�[�h���X�g��
     * @param schema �X�L�[�}
     */
    public void setNestedRecordListSchema(String name, RecordSchema schema){
        
        if(nestedRecordListMap == null){
            nestedRecordListMap = isSynchronized ? Collections.synchronizedMap(new LinkedHashMap()) : new LinkedHashMap();
        }
        nestedRecordListMap.put(
            name,
            schema.getSchema()
        );
    }
    
    /**
     * �w�肵�����O�̃l�X�g����{@link RecordList ���R�[�h���X�g}�̃X�L�[�}���擾����B<p>
     *
     * @param name ���R�[�h���X�g��
     * @return �X�L�[�}
     */
    public RecordSchema getNestedRecordListSchema(String name)
     throws PropertySchemaDefineException{
        if(nestedRecordListMap == null){
            return null;
        }
        final String schema = (String)nestedRecordListMap.get(name);
        return schema == null ? null : RecordSchema.getInstance(schema);
    }
    
    /**
     * ��`���ꂽ���ɕ��񂾃l�X�g�������R�[�h���X�g���z����擾����B<p>
     *
     * @return �l�X�g�������R�[�h���X�g���z��
     */
    public String[] getNestedRecordListSchemaNames(){
        return nestedRecordListMap == null ? new String[0] : (String[])nestedRecordListMap.keySet().toArray(new String[nestedRecordListMap.size()]);
    }
    
    /**
     * �l�X�g�������R�[�h���X�g�̐����擾����B<p>
     *
     * @return �l�X�g�������R�[�h���X�g�̐�
     */
    public int getNestedRecordListSchemaSize(){
        return nestedRecordListMap == null ? 0 : nestedRecordListMap.size();
    }
    
    /**
     * �l�X�g�������R�[�h���X�g�̃}�b�v���擾����B<p>
     *
     * @return �l�X�g�������R�[�h���X�g�̃}�b�v�B�L�[�̓��R�[�h���X�g���A�l�̓X�L�[�}������
     */
    public Map getNestedRecordListSchemaMap(){
        if(nestedRecordListMap == null){
            nestedRecordListMap = isSynchronized ? Collections.synchronizedMap(new LinkedHashMap()) : new LinkedHashMap();
        }
        return nestedRecordListMap;
    }
    
    /**
     * �w�肵�����O�̃l�X�g����{@link Record ���R�[�h}�̃X�L�[�}��ݒ肷��B<p>
     *
     * @param name ���R�[�h��
     * @param schema �X�L�[�}
     * @exception PropertySchemaDefineException �v���p�e�B�̃X�L�[�}��`�Ɏ��s�����ꍇ
     */
    public void setNestedRecordSchema(String name, String schema)
     throws PropertySchemaDefineException{
        
        if(nestedRecordMap == null){
            nestedRecordMap = isSynchronized ? Collections.synchronizedMap(new LinkedHashMap()) : new LinkedHashMap();
        }
        RecordSchema recSchema = RecordSchema.getInstance(schema);
        nestedRecordMap.put(
            name,
            recSchema.getSchema()
        );
    }
    
    /**
     * �w�肵�����O�̃l�X�g����{@link Record ���R�[�h}�̃X�L�[�}��ݒ肷��B<p>
     *
     * @param name ���R�[�h��
     * @param schema �X�L�[�}
     */
    public void setNestedRecordSchema(String name, RecordSchema schema){
        
        if(nestedRecordMap == null){
            nestedRecordMap = isSynchronized ? Collections.synchronizedMap(new LinkedHashMap()) : new LinkedHashMap();
        }
        nestedRecordMap.put(
            name,
            schema.getSchema()
        );
    }
    
    /**
     * �w�肵�����O�̃l�X�g����{@link Record ���R�[�h}�̃N���X��ݒ肷��B<p>
     *
     * @param name ���R�[�h��
     * @param clazz �N���X
     * @exception PropertySchemaDefineException �v���p�e�B�̃X�L�[�}��`�Ɏ��s�����ꍇ
     */
    public void setNestedRecordClass(String name, Class clazz)
     throws PropertySchemaDefineException{
        Record record = null;
        try{
            record = (Record)clazz.newInstance();
        }catch(InstantiationException e){
            throw new PropertySchemaDefineException(null, e);
        }catch(IllegalAccessException e){
            throw new PropertySchemaDefineException(null, e);
        }catch(ClassCastException e){
            throw new PropertySchemaDefineException(null, e);
        }
        if(nestedRecordMap == null){
            nestedRecordMap = isSynchronized ? Collections.synchronizedMap(new LinkedHashMap()) : new LinkedHashMap();
        }
        nestedRecordMap.put(
            name,
            record.getSchema()
        );
        if(nestedRecordClassMap == null){
            nestedRecordClassMap = isSynchronized ? Collections.synchronizedMap(new LinkedHashMap()) : new LinkedHashMap();
        }
        nestedRecordClassMap.put(
            name,
            clazz
        );
    }
    
    /**
     * �w�肵�����O�̃l�X�g����{@link Record ���R�[�h}�̃X�L�[�}���擾����B<p>
     *
     * @param name ���R�[�h��
     * @return �X�L�[�}
     */
    public RecordSchema getNestedRecordSchema(String name)
     throws PropertySchemaDefineException{
        if(nestedRecordMap == null){
            return null;
        }
        final String schema = (String)nestedRecordMap.get(name);
        return schema == null ? null : RecordSchema.getInstance(schema);
    }
    
    /**
     * ��`���ꂽ���ɕ��񂾃l�X�g�������R�[�h���z����擾����B<p>
     *
     * @return �l�X�g�������R�[�h���z��
     */
    public String[] getNestedRecordSchemaNames(){
        return nestedRecordMap == null ? new String[0] : (String[])nestedRecordMap.keySet().toArray(new String[nestedRecordMap.size()]);
    }
    
    /**
     * �l�X�g�������R�[�h�̐����擾����B<p>
     *
     * @return �l�X�g�������R�[�h�̐�
     */
    public int getNestedRecordSchemaSize(){
        return nestedRecordMap == null ? 0 : nestedRecordMap.size();
    }
    
    /**
     * �l�X�g�������R�[�h�̃}�b�v���擾����B<p>
     *
     * @return �l�X�g�������R�[�h�̃}�b�v�B�L�[�̓��R�[�h���A�l�̓X�L�[�}������
     */
    public Map getNestedRecordSchemaMap(){
        if(nestedRecordMap == null){
            nestedRecordMap = isSynchronized ? Collections.synchronizedMap(new LinkedHashMap()) : new LinkedHashMap();
        }
        return nestedRecordMap;
    }
    
    /**
     * ���O�������Ȃ�{@link Header �w�b�_�[}��{@link RecordList ���R�[�h���X�g}�̃X�L�[�}��ݒ肷��B<p>
     * {@link #setHeaderSchema(String, String) setHeaderSchema(null, schema)}��{@link #setRecordListSchema(String, String) setRecordListSchema(null, schema)}���Ăяo���̂Ɠ����B<br>
     *
     * @param headerSchema �w�b�_�[�̃X�L�[�}
     * @param recordListSchema ���R�[�h���X�g�̃X�L�[�}
     * @exception PropertySchemaDefineException �v���p�e�B�̃X�L�[�}��`�Ɏ��s�����ꍇ
     */
    public void setSchema(String headerSchema, String recordListSchema)
     throws PropertySchemaDefineException{
        setSchema(null, headerSchema, recordListSchema);
    }
    
    /**
     * �w�肵�����O��{@link Header �w�b�_�[}��{@link RecordList ���R�[�h���X�g}�̃X�L�[�}��ݒ肷��B<p>
     *
     * @param name �w�b�_�[���y�у��R�[�h���X�g��
     * @param headerSchema �w�b�_�[�̃X�L�[�}
     * @param recordListSchema ���R�[�h���X�g�̃X�L�[�}
     */
    public void setSchema(
        String name,
        RecordSchema headerSchema,
        RecordSchema recordListSchema
    ){
        if(headerSchema != null){
            setHeaderSchema(name, headerSchema);
        }
        if(recordListSchema != null){
            setRecordListSchema(name, recordListSchema);
        }
    }
    
    /**
     * ���O�������Ȃ�{@link Header �w�b�_�[}��{@link RecordList ���R�[�h���X�g}�̃X�L�[�}��ݒ肷��B<p>
     * {@link #setHeaderSchema(String, RecordSchema) setHeaderSchema(null, schema)}��{@link #setRecordListSchema(String, RecordSchema) setRecordListSchema(null, schema)}���Ăяo���̂Ɠ����B<br>
     *
     * @param headerSchema �w�b�_�[�̃X�L�[�}
     * @param recordListSchema ���R�[�h���X�g�̃X�L�[�}
     */
    public void setSchema(RecordSchema headerSchema, RecordSchema recordListSchema){
        setSchema(null, headerSchema, recordListSchema);
    }
    
    /**
     * �w�肵�����O��{@link Header �w�b�_�[}��{@link RecordList ���R�[�h���X�g}�̃X�L�[�}��ݒ肷��B<p>
     *
     * @param name �w�b�_�[���y�у��R�[�h���X�g��
     * @param headerSchema �w�b�_�[�̃X�L�[�}
     * @param recordListSchema ���R�[�h���X�g�̃X�L�[�}
     * @exception PropertySchemaDefineException �v���p�e�B�̃X�L�[�}��`�Ɏ��s�����ꍇ
     */
    public void setSchema(
        String name,
        String headerSchema,
        String recordListSchema
    ) throws PropertySchemaDefineException{
        if(headerSchema != null){
            setHeaderSchema(name, headerSchema);
        }
        if(recordListSchema != null){
            setRecordListSchema(name, recordListSchema);
        }
    }
    
    /**
     * ���O�������Ȃ�{@link Header �w�b�_�[}���擾����B<p>
     * {@link #getHeader(String) getHeader(null)}���Ăяo���̂Ɠ����B<br>
     *
     * @return �w�b�_�[
     */
    public Header getHeader(){
        return getHeader(null);
    }
    
    /**
     * �w�肵�����O��{@link Header �w�b�_�[}���擾����B<p>
     *
     * @param name �w�b�_�[��
     * @return �w�b�_�[
     */
    public Header getHeader(String name){
        return headerMap == null ? null : (Header)headerMap.get(name);
    }
    
    /**
     * ��`���ꂽ���ɕ��񂾃w�b�_�[���z����擾����B<p>
     *
     * @return �w�b�_�[���z��
     */
    public String[] getHeaderNames(){
        return headerMap == null ? new String[0] : (String[])headerMap.keySet().toArray(new String[headerMap.size()]);
    }
    
    /**
     * �w�b�_�[�̐����擾����B<p>
     *
     * @return �w�b�_�[�̐�
     */
    public int getHeaderSize(){
        return headerMap == null ? 0 : headerMap.size();
    }
    
    /**
     * �w�b�_�[�̃}�b�v���擾����B<p>
     *
     * @return �w�b�_�[�̃}�b�v�B�L�[�̓w�b�_�[���A�l��{@link Header �w�b�_�[}
     */
    public Map getHeaderMap(){
        if(headerMap == null){
            headerMap = isSynchronized ? Collections.synchronizedMap(new LinkedHashMap()) : new LinkedHashMap();
        }
        return headerMap;
    }
    
    /**
     * �w�b�_�[��ݒ肷��B<p>
     *
     * @param name �w�b�_�[��
     * @param header �w�b�_�[
     */
    public void setHeader(String name, Header header){
        if(headerMap == null){
            headerMap = isSynchronized ? Collections.synchronizedMap(new LinkedHashMap()) : new LinkedHashMap();
        }
        if(header != null){
            header.setName(name);
        }
        headerMap.put(name, header);
    }
    
    /**
     * �w�b�_�[��ǉ�����B<p>
     *
     * @param header �w�b�_�[
     */
    public void addHeader(Header header){
        if(headerMap == null){
            headerMap = isSynchronized ? Collections.synchronizedMap(new LinkedHashMap()) : new LinkedHashMap();
        }
        headerMap.put(header.getName(), header);
    }
    
    /**
     * ���O�������Ȃ�{@link RecordList ���R�[�h���X�g}���擾����B<p>
     * {@link #getRecordList(String) getRecordList(null)}���Ăяo���̂Ɠ����B<br>
     *
     * @return ���R�[�h���X�g
     */
    public RecordList getRecordList(){
        return getRecordList(null);
    }
    
    /**
     * �w�肵�����O��{@link RecordList ���R�[�h���X�g}���擾����B<p>
     *
     * @param name ���R�[�h���X�g��
     * @return ���R�[�h���X�g
     */
    public RecordList getRecordList(String name){
        return recordListMap == null ? null : (RecordList)recordListMap.get(name);
    }
    
    /**
     * ��`���ꂽ���ɕ��񂾃��R�[�h���X�g���z����擾����B<p>
     *
     * @return ���R�[�h���X�g���z��
     */
    public String[] getRecordListNames(){
        return recordListMap == null ? new String[0] : (String[])recordListMap.keySet().toArray(new String[recordListMap.size()]);
    }
    
    /**
     * ���R�[�h���X�g�̐����擾����B<p>
     *
     * @return ���R�[�h���X�g�̐�
     */
    public int getRecordListSize(){
        return recordListMap == null ? 0 : recordListMap.size();
    }
    
    /**
     * ���R�[�h���X�g�̃}�b�v���擾����B<p>
     *
     * @return ���R�[�h���X�g�̃}�b�v�B�L�[�̓��R�[�h���X�g���A�l��{@link RecordList ���R�[�h���X�g}
     */
    public Map getRecordListMap(){
        if(recordListMap == null){
            recordListMap = isSynchronized ? Collections.synchronizedMap(new LinkedHashMap()) : new LinkedHashMap();
        }
        return recordListMap;
    }
    
    /**
     * ���R�[�h���X�g��ǉ�����B<p>
     *
     * @param recList ���R�[�h���X�g
     */
    public void addRecordList(RecordList recList){
        if(recordListMap == null){
            recordListMap = isSynchronized ? Collections.synchronizedMap(new LinkedHashMap()) : new LinkedHashMap();
        }
        recordListMap.put(recList.getName(), recList);
    }
    
    /**
     * �w�肵�����O�̃l�X�g����{@link RecordList ���R�[�h���X�g}�𐶐�����B<p>
     *
     * @param name ���R�[�h���X�g��
     * @return ���R�[�h���X�g
     */
    public RecordList createNestedRecordList(String name){
        if(nestedRecordListMap == null
             || !nestedRecordListMap.containsKey(name)){
            return null;
        }
        if(nestedRecordListClassMap != null){
            Class clazz = (Class)nestedRecordListClassMap.get(name);
            if(clazz != null){
                try{
                    Constructor c = clazz.getConstructor(new Class[]{String.class});
                    return (RecordList)c.newInstance(new Object[]{name});
                }catch(NoSuchMethodException e){
                    try{
                        return (RecordList)clazz.newInstance();
                    }catch(Exception e2){
                    }
                }catch(Exception e){
                }
            }
        }
        return createRecordList(
            name,
            RecordSchema.getInstance((String)nestedRecordListMap.get(name))
        );
    }
    
    /**
     * �w�肵�����O�̃l�X�g����{@link Record ���R�[�h}�𐶐�����B<p>
     *
     * @param name ���R�[�h��
     * @return ���R�[�h
     */
    public Record createNestedRecord(String name){
        if(nestedRecordMap == null
             || !nestedRecordMap.containsKey(name)){
            return null;
        }
        if(nestedRecordClassMap != null){
            Class clazz = (Class)nestedRecordClassMap.get(name);
            if(clazz != null){
                try{
                    return (Record)clazz.newInstance();
                }catch(Exception e){
                }
            }
        }
        return new Record(
            RecordSchema.getInstance((String)nestedRecordMap.get(name))
        );
    }
    
    /**
     * �f�[�^�Z�b�g���N���A����B<p>
     * �w�b�_�[�̃f�[�^�ƃ��R�[�h���X�g�̃��R�[�h���폜����B<br>
     */
    public void clear(){
        if(headerMap != null && headerMap.size() != 0){
            final String[] headerNames = (String[])headerMap.keySet()
                .toArray(new String[headerMap.size()]);
            for(int i = 0; i < headerNames.length; i++){
                final Header header = getHeader(headerNames[i]);
                if(header != null){
                    header.clear();
                }
            }
        }
        if(recordListMap != null && recordListMap.size() != 0){
            final String[] recListNames = (String[])recordListMap.keySet()
                .toArray(new String[recordListMap.size()]);
            for(int i = 0; i < recListNames.length; i++){
                final RecordList recList = getRecordList(recListNames[i]);
                if(recList != null){
                    recList.clear();
                }
            }
        }
    }
    
    /**
     * ���O�������Ȃ�{@link Header �w�b�_�[}�����؂���B<p>
     *
     * @return ���،��ʁBtrue�̏ꍇ�A���ؐ���
     * @exception PropertyGetException �v���p�e�B�̎擾�Ɏ��s�����ꍇ
     * @exception PropertyValidateException �v���p�e�B�̌��؎��ɗ�O�����������ꍇ
     */
    public boolean validateHeader() throws PropertyGetException, PropertyValidateException{
        Header header = getHeader();
        if(header == null){
            return false;
        }
        return header.validate();
    }
    
    /**
     * �w�肳�ꂽ{@link Header �w�b�_�[}�����؂���B<p>
     *
     * @param name �w�b�_�[��
     * @return ���،��ʁBtrue�̏ꍇ�A���ؐ���
     * @exception PropertyGetException �v���p�e�B�̎擾�Ɏ��s�����ꍇ
     * @exception PropertyValidateException �v���p�e�B�̌��؎��ɗ�O�����������ꍇ
     */
    public boolean validateHeader(String name) throws PropertyGetException, PropertyValidateException{
        Header header = getHeader(name);
        if(header == null){
            return false;
        }
        return header.validate();
    }
    
    /**
     * �S�Ă�{@link Header �w�b�_�[}�����؂���B<p>
     *
     * @return ���،��ʁBtrue�̏ꍇ�A���ؐ���
     * @exception PropertyGetException �v���p�e�B�̎擾�Ɏ��s�����ꍇ
     * @exception PropertyValidateException �v���p�e�B�̌��؎��ɗ�O�����������ꍇ
     */
    public boolean validateHeaders() throws PropertyGetException, PropertyValidateException{
        if(headerMap == null || headerMap.size() == 0){
            return true;
        }
        Iterator headers = headerMap.values().iterator();
        while(headers.hasNext()){
            Header header = (Header)headers.next();
            if(!header.validate()){
                return false;
            }
        }
        return true;
    }
    
    /**
     * ���O�������Ȃ�{@link RecordList ���R�[�h���X�g}�����؂���B<p>
     *
     * @return ���،��ʁBtrue�̏ꍇ�A���ؐ���
     * @exception PropertyGetException �v���p�e�B�̎擾�Ɏ��s�����ꍇ
     * @exception PropertyValidateException �v���p�e�B�̌��؎��ɗ�O�����������ꍇ
     */
    public boolean validateRecordList() throws PropertyGetException, PropertyValidateException{
        RecordList recordList = getRecordList();
        if(recordList == null){
            return false;
        }
        return recordList.validate();
    }
    
    /**
     * �w�肳�ꂽ{@link RecordList ���R�[�h���X�g}�����؂���B<p>
     *
     * @param name �w�b�_�[��
     * @return ���،��ʁBtrue�̏ꍇ�A���ؐ���
     * @exception PropertyGetException �v���p�e�B�̎擾�Ɏ��s�����ꍇ
     * @exception PropertyValidateException �v���p�e�B�̌��؎��ɗ�O�����������ꍇ
     */
    public boolean validateRecordList(String name) throws PropertyGetException, PropertyValidateException{
        RecordList recordList = getRecordList(name);
        if(recordList == null){
            return false;
        }
        return recordList.validate();
    }
    
    /**
     * �S�Ă�{@link RecordList ���R�[�h���X�g}�����؂���B<p>
     *
     * @return ���،��ʁBtrue�̏ꍇ�A���ؐ���
     * @exception PropertyGetException �v���p�e�B�̎擾�Ɏ��s�����ꍇ
     * @exception PropertyValidateException �v���p�e�B�̌��؎��ɗ�O�����������ꍇ
     */
    public boolean validateRecordLists() throws PropertyGetException, PropertyValidateException{
        if(recordListMap == null || recordListMap.size() == 0){
            return true;
        }
        Iterator recordLists = recordListMap.values().iterator();
        while(recordLists.hasNext()){
            RecordList recordList = (RecordList)recordLists.next();
            if(!recordList.validate()){
                return false;
            }
        }
        return true;
    }
    
    /**
     * �S�Ă�{@link Header �w�b�_�[}�y��{@link RecordList ���R�[�h���X�g}�����؂���B<p>
     *
     * @return ���،��ʁBtrue�̏ꍇ�A���ؐ���
     * @exception PropertyGetException �v���p�e�B�̎擾�Ɏ��s�����ꍇ
     * @exception PropertyValidateException �v���p�e�B�̌��؎��ɗ�O�����������ꍇ
     */
    public boolean validate() throws PropertyGetException, PropertyValidateException{
        if(!validateHeaders()){
            return false;
        }
        if(!validateRecordLists()){
            return false;
        }
        return true;
    }
    
    /**
     * �f�[�^�Z�b�g�𕡐�����B<p>
     *
     * @return ���������f�[�^�Z�b�g
     */
    public Object clone(){
        return cloneDataSet();
    }
    
    /**
     * �����X�L�[�}�������f�[�^�������Ȃ���̃f�[�^�Z�b�g�𕡐�����B<p>
     *
     * @return ����������̃f�[�^�Z�b�g
     */
    public DataSet cloneSchema(){
        return cloneDataSet(false);
    }
    
    /**
     * �f�[�^�Z�b�g�𕡐�����B<p>
     *
     * @return ���������f�[�^�Z�b�g
     */
    public DataSet cloneDataSet(){
        return cloneDataSet(true);
    }
    
    /**
     * �f�[�^�Z�b�g�𕡐�����B<p>
     *
     * @param hasData �f�[�^����������ꍇtrue
     * @return ���������f�[�^�Z�b�g
     */
    protected DataSet cloneDataSet(boolean hasData){
        DataSet dataSet = null;
        try{
            dataSet = (DataSet)super.clone();
            dataSet.headerMap = null;
            dataSet.recordListMap = null;
            dataSet.nestedRecordListMap = null;
            dataSet.nestedRecordListClassMap = null;
            dataSet.nestedRecordMap = null;
            dataSet.nestedRecordClassMap = null;
        }catch(CloneNotSupportedException e){
            return null;
        }
        if(headerMap != null && headerMap.size() != 0){
            final String[] headerNames = (String[])headerMap.keySet()
                .toArray(new String[headerMap.size()]);
            dataSet.headerMap
                 = isSynchronized ? Collections.synchronizedMap(new LinkedHashMap()) : new LinkedHashMap();
            for(int i = 0; i < headerNames.length; i++){
                final Header header = getHeader(headerNames[i]);
                if(header != null){
                    dataSet.headerMap.put(
                        headerNames[i],
                        hasData ? header.cloneRecord() : header.cloneSchema()
                    );
                }
            }
        }
        if(recordListMap != null && recordListMap.size() != 0){
            final String[] recListNames = (String[])recordListMap.keySet()
                .toArray(new String[recordListMap.size()]);
            dataSet.recordListMap
                 = isSynchronized ? Collections.synchronizedMap(new LinkedHashMap()) : new LinkedHashMap();
            for(int i = 0; i < recListNames.length; i++){
                final RecordList recList = getRecordList(recListNames[i]);
                if(recList != null){
                    dataSet.recordListMap.put(
                        recListNames[i],
                        hasData ? recList.cloneRecordList()
                             : recList.cloneSchema()
                    );
                }
            }
        }
        if(nestedRecordListMap != null && nestedRecordListMap.size() != 0){
            dataSet.nestedRecordListMap
                 = isSynchronized ? Collections.synchronizedMap(new LinkedHashMap()) : new LinkedHashMap();
            dataSet.nestedRecordListMap.putAll(nestedRecordListMap);
        }
        if(nestedRecordListClassMap != null && nestedRecordListClassMap.size() != 0){
            dataSet.nestedRecordListClassMap
                 = isSynchronized ? Collections.synchronizedMap(new LinkedHashMap()) : new LinkedHashMap();
            dataSet.nestedRecordListClassMap.putAll(nestedRecordListClassMap);
        }
        if(nestedRecordMap != null && nestedRecordMap.size() != 0){
            dataSet.nestedRecordMap
                 = isSynchronized ? Collections.synchronizedMap(new LinkedHashMap()) : new LinkedHashMap();
            dataSet.nestedRecordMap.putAll(nestedRecordMap);
        }
        if(nestedRecordClassMap != null && nestedRecordClassMap.size() != 0){
            dataSet.nestedRecordClassMap
                 = isSynchronized ? Collections.synchronizedMap(new LinkedHashMap()) : new LinkedHashMap();
            dataSet.nestedRecordClassMap.putAll(nestedRecordClassMap);
        }
        return dataSet;
    }
    
    /**
     * ���̃f�[�^�Z�b�g�̕�����\�����擾����B<p>
     *
     * @return ������\��
     */
    public String toString(){
        return super.toString() + "{name=" + name + '}';
    }
}