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
package jp.ossc.nimbus.service.cache;

import java.io.*;

import jp.ossc.nimbus.core.*;
import jp.ossc.nimbus.service.io.Externalizer;

/**
 * �L�[�t���t�@�C���L���b�V���Q�ƁB<p>
 *
 * @author M.Takata
 */
public class FileKeyCachedReference extends DefaultKeyCachedReference
 implements Serializable{
    
    private static final long serialVersionUID = 1386986712271917526L;
    
    private transient Externalizer externalizer;
    
    /**
     * �����̃L���b�V���t�@�C������L�[�t���t�@�C���L���b�V���Q�Ƃ𐶐�����B<p>
     * �w�肳�ꂽ�L���b�V���t�@�C����ǂݍ���ŃL���b�V���L�[���擾����B<br>
     * 
     * @param file �L���b�V���t�@�C��
     * @exception IOException �L���b�V���t�@�C���̕����Ɏ��s�����ꍇ
     * @exception ClassNotFoundException �L���b�V���t�@�C���̕������ʂ̃N���X�����݂��Ȃ��ꍇ
     */
    public FileKeyCachedReference(File file)
     throws IOException, ClassNotFoundException{
        super(null, file);
        cacheKey = ((MapEntry)deserializeObject(file)).getKey();
    }
    
    /**
     * �����̃L���b�V���t�@�C������L�[�t���t�@�C���L���b�V���Q�Ƃ𐶐�����B<p>
     * �w�肳�ꂽ�L���b�V���t�@�C����ǂݍ���ŃL���b�V���L�[���擾����B<br>
     * 
     * @param file �L���b�V���t�@�C��
     * @param ext ���񉻂��s��Externalizer
     * @exception IOException �L���b�V���t�@�C���̕����Ɏ��s�����ꍇ
     * @exception ClassNotFoundException �L���b�V���t�@�C���̕������ʂ̃N���X�����݂��Ȃ��ꍇ
     */
    public FileKeyCachedReference(File file, Externalizer ext)
     throws IOException, ClassNotFoundException{
        super(null, file);
        externalizer = ext;
        cacheKey = ((MapEntry)deserializeObject(file)).getKey();
    }
    
    /**
     * �w�肳�ꂽ�L���b�V���L�[�A�L���b�V���t�@�C���A�L���b�V���I�u�W�F�N�g��ێ�����V�����L�[�t���t�@�C���L���b�V���Q�Ƃ𐶐�����B<p>
     * �L���b�V���I�u�W�F�N�g�́A���񉻂��ăL���b�V���t�@�C���ɕۑ�����B<br>
     * 
     * @param key �L���b�V���L�[
     * @param file �L���b�V���t�@�C��
     * @param obj �L���b�V���I�u�W�F�N�g
     * @exception IOException �L���b�V���I�u�W�F�N�g�̒��񉻂Ɏ��s�����ꍇ
     */
    public FileKeyCachedReference(Object key, File file, Object obj)
     throws IOException{
        super(key, file);
        serializeObject(file, new MapEntry(key, obj));
    }
    
    /**
     * �w�肳�ꂽ�L���b�V���L�[�A�L���b�V���t�@�C���A�L���b�V���I�u�W�F�N�g��ێ�����V�����L�[�t���t�@�C���L���b�V���Q�Ƃ𐶐�����B<p>
     * �L���b�V���I�u�W�F�N�g�́A���񉻂��ăL���b�V���t�@�C���ɕۑ�����B<br>
     * 
     * @param key �L���b�V���L�[
     * @param file �L���b�V���t�@�C��
     * @param obj �L���b�V���I�u�W�F�N�g
     * @exception IOException �L���b�V���I�u�W�F�N�g�̒��񉻂Ɏ��s�����ꍇ
     */
    public FileKeyCachedReference(Object key, File file, Object obj, Externalizer ext)
     throws IOException{
        super(key, file);
        externalizer = ext;
        serializeObject(file, new MapEntry(key, obj));
    }
    
    /**
     * �I�u�W�F�N�g���w��t�@�C���ɃV���A���C�Y���Ċi�[����B<p>
     * 
     * @param file �L���b�V���t�@�C��
     * @param obj �L���b�V���I�u�W�F�N�g
     * @exception IOException �L���b�V���I�u�W�F�N�g�̒��񉻂Ɏ��s�����ꍇ
     */
    protected void serializeObject(File file, Object obj)
     throws IOException{
        OutputStream os = new BufferedOutputStream(new FileOutputStream(file));
        try{
            if(externalizer == null){
                ObjectOutputStream oos = new ObjectOutputStream(os);
                if(obj != null){
                    synchronized(obj){
                        oos.writeObject(obj);
                    }
                }else{
                    oos.writeObject(obj);
                }
                oos.flush();
            }else{
                if(obj != null){
                    synchronized(obj){
                        externalizer.writeExternal(obj, os);
                    }
                }else{
                    externalizer.writeExternal(obj, os);
                }
            }
        }finally{
            if(os!=null){
                try{
                    os.close();
                }catch(IOException e){}
            }
        }
    }
    
    /**
     * �w�肳�ꂽ�t�@�C������I�u�W�F�N�g�𕜌�����B<p>
     * 
     * @param file �L���b�V���t�@�C��
     * @return ���������L���b�V���I�u�W�F�N�g
     * @exception IOException �L���b�V���t�@�C���̕����Ɏ��s�����ꍇ
     * @exception ClassNotFoundException �L���b�V���t�@�C���̕������ʂ̃N���X�� ���݂��Ȃ��ꍇ
     */
    protected Object deserializeObject(File file)
     throws IOException, ClassNotFoundException{
        Object entry = null;
        InputStream is = new BufferedInputStream(new FileInputStream(file));
        try{
            if(externalizer == null){
                ObjectInputStream ois = new ObjectInputStream(is);
                entry = ois.readObject();
            }else{
                entry = externalizer.readExternal(is);
            }
        }finally{
            if(is!=null){
                try{
                    is.close() ;
                }catch(IOException ex){
                }
            }
        }
        return entry;
    }
    
    /**
     * �L���b�V���t�@�C�����擾����B<p>
     *
     * @param source �擾���̎Q��
     * @return �L���b�V���t�@�C��
     */
    public File getFile(Object source){
        return (File)super.get(source, false);
    }
    
    /**
     * �L���b�V�����ꂽ�I�u�W�F�N�g���L���b�V���t�@�C�����畜�����Ď擾����B<p>
     * ��������true�̏ꍇ�́A{@link #addCacheAccessListener(CacheAccessListener)}�œo�^���ꂽ{@link CacheAccessListener}�ɒʒm����B�A���A�������œn���ꂽ�Ăяo�����I�u�W�F�N�g���ʒm���CacheAccessListener�̃C���X�^���X�Ɠ������ꍇ�́A�ʒm���Ȃ��B<br>
     * ���g���ێ�����L���b�V���I�u�W�F�N�g��null�łȂ��ꍇ�́A�����Ԃ��Bnull�̏ꍇ�́A{@link #addLinkedReference(LinkedReference)}�œo�^���ꂽ{@link LinkedReference}����擾�����݂�B<br>
     *
     * @param source �L���b�V�����擾���邱�̃��\�b�h�̌Ăяo�����I�u�W�F�N�g
     * @param notify �L���b�V���A�N�Z�X���X�i�ɒʒm����ꍇ��true
     * @return �L���b�V���I�u�W�F�N�g
     */
    public Object get(Object source, boolean notify){
        final Object obj = super.get(source, notify);
        if(obj instanceof File){
            File file = (File)obj;
            if(file.exists() && file.canRead()){
                MapEntry entry = null;
                try{
                    entry = ((MapEntry)deserializeObject(file));
                }catch(IOException e){
                    return null;
                }catch(ClassNotFoundException e){
                    return null;
                }
                return entry.getValue();
            }else{
                return null;
            }
        }else{
            return obj;
        }
    }
    
    /**
     * �L���b�V���I�u�W�F�N�g�𒼗񉻂��ăt�@�C���ɕۑ�����B<p>
     * {@link #addCacheChangeListener(CacheChangeListener)}�œo�^���ꂽ{@link CacheChangeListener}�ɒʒm����B�A���A�������œn���ꂽ�Ăяo�����I�u�W�F�N�g���ʒm���CacheChangeListener�̃C���X�^���X�Ɠ������ꍇ�́A�ʒm���Ȃ��B<br>
     *
     * @param source �L���b�V���I�u�W�F�N�g��ύX���邱�̃��\�b�h�̌Ăяo�����I�u�W�F�N�g
     * @param obj �ݒ肷��L���b�V���I�u�W�F�N�g
     * @exception IllegalCachedReferenceException �L���b�V���Q�Ƃ̏�Ԃ��s���Ȉ׃L���b�V���I�u�W�F�N�g�̐ݒ�Ɏ��s�����ꍇ
     */
    public void set(Object source, Object obj)
     throws IllegalCachedReferenceException{
        notifyChange(source, obj);
        if(obj != null){
            try{
                serializeObject((File)cacheObj, obj);
            }catch(IOException e){
                throw new IllegalCachedReferenceException(e);
            }
        }else{
            ((File)cacheObj).delete();
        }
    }
    
    /**
     * �L���b�V���I�u�W�F�N�g��ۑ������L���b�V���t�@�C�����폜����B<p>
     * {@link #addCacheRemoveListener(CacheRemoveListener)}�œo�^���ꂽ{@link CacheRemoveListener}�ɒʒm����B�A���A�������œn���ꂽ�Ăяo�����I�u�W�F�N�g���ʒm���CacheChangeListener�̃C���X�^���X�Ɠ������ꍇ�́A�ʒm���Ȃ��B<br>
     *
     * @param source �L���b�V���I�u�W�F�N�g���폜���邱�̃��\�b�h�̌Ăяo�����I�u�W�F�N�g
     */
    public void remove(Object source){
        if(cacheObj != null){
            notifyRemoved(source);
            File file = (File)cacheObj;
            if(file.exists() && file.canRead()){
                ((File)cacheObj).delete();
            }
            cacheObj = null;
            if(linkedReferences != null){
                linkedReferences.clear();
            }
        }
    }
    
    /**
     * �L���b�V���L�[�ƃL���b�V���I�u�W�F�N�g��ێ����钼�񉻗p�N���X�B<p>
     *
     * @author M.Takata
     */
    public static class MapEntry implements Serializable{
        
        private static final long serialVersionUID = 4635469653838112700L;
        
        /**
         * �L���b�V���L�[�B<p>
         */
        protected Object key;
        
        /**
         * �L���b�V���I�u�W�F�N�g�B<p>
         */
        protected Object value;
        
        /**
         * �L���b�V���L�[�ƃL���b�V���I�u�W�F�N�g��ێ�����L���b�V���G���g���𐶐�����B<p>
         *
         * @param k �L���b�V���L�[
         * @param val �L���b�V���I�u�W�F�N�g
         */
        public MapEntry(Object k, Object val){
            key = k;
            value = val;
        }
        
        /**
         * �L���b�V���L�[���擾����B<p>
         * 
         * @return �L���b�V���L�[
         */
        public Object getKey(){
            return key;
        }
        
        /**
         * �L���b�V���I�u�W�F�N�g���擾����B<p>
         * 
         * @return �L���b�V���I�u�W�F�N�g
         */
        public Object getValue(){
            return value;
        }
    }
    
    private void writeObject(ObjectOutputStream out) throws IOException{
        out.defaultWriteObject();
        writeSet(out, linkedReferences);
        writeSet(out, removeListeners);
        writeSet(out, accessListeners);
        writeSet(out, changeListeners);
        ServiceName name = null;
        if(externalizer != null){
            name = getServiceName(externalizer);
        }
        if(name != null){
            out.writeObject(name);
        }else{
            out.writeObject(externalizer);
        }
    }
    
    private void readObject(ObjectInputStream in)
     throws IOException, ClassNotFoundException{
        in.defaultReadObject();
        linkedReferences = readSet(in);
        removeListeners = readSet(in);
        accessListeners = readSet(in);
        changeListeners = readSet(in);
        Object obj = in.readObject();
        if(obj != null){
            if(obj instanceof ServiceName){
                externalizer = (Externalizer)ServiceManagerFactory.getServiceObject(
                    (ServiceName)obj
                );
            }else{
                externalizer = (Externalizer)obj;
            }
        }
    }
}