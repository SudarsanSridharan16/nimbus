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
 * ���񉻃L���b�V���Q�ƁB<p>
 * �L���b�V���I�u�W�F�N�g�𒼗񉻂��ă������ɕێ�����B<br>
 *
 * @author M.Takata
 */
public class SerializeCachedReference extends DefaultCachedReference
 implements Serializable{
    
    private static final long serialVersionUID = -1425349493190241680L;
    
    private transient Externalizer externalizer;
    
    /**
     * �w�肳�ꂽ�L���b�V���I�u�W�F�N�g�𒼗񉻂��ĕێ�����L���b�V���Q�Ƃ𐶐�����B<p>
     *
     * @param obj �L���b�V���I�u�W�F�N�g
     * @exception IOException ���񉻂Ɏ��s�����ꍇ
     */
    public SerializeCachedReference(Object obj) throws IOException{
        super(null);
        serializeObject(obj);
    }
    
    /**
     * �w�肳�ꂽ�L���b�V���I�u�W�F�N�g�𒼗񉻂��ĕێ�����L���b�V���Q�Ƃ𐶐�����B<p>
     *
     * @param obj �L���b�V���I�u�W�F�N�g
     * @param ext ���񉻂��s��Externalizer
     * @exception IOException ���񉻂Ɏ��s�����ꍇ
     */
    public SerializeCachedReference(Object obj, Externalizer ext)
     throws IOException{
        super(null);
        externalizer = ext;
        serializeObject(obj);
    }
    
    /**
     * �I�u�W�F�N�g���V���A���C�Y���Ċi�[����B<p>
     * 
     * @param obj �L���b�V���I�u�W�F�N�g
     * @exception IOException ���񉻂Ɏ��s�����ꍇ
     */
    protected void serializeObject(Object obj)
     throws IOException{
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        if(externalizer == null){
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            oos.writeObject(obj);
            oos.flush();
        }else{
            externalizer.writeExternal(obj, baos);
        }
        cacheObj = baos.toByteArray();
    }
    
    /**
     * �I�u�W�F�N�g�𕜌�����B<p>
     * 
     * @param bytes ���񉻃o�C�g�z��
     * @return �����I�u�W�F�N�g
     * @exception IOException �L���b�V���t�@�C���̕����Ɏ��s�����ꍇ
     * @exception ClassNotFoundException �L���b�V���t�@�C���̕������ʂ̃N���X�� ���݂��Ȃ��ꍇ
     */
    protected Object deserializeObject(byte[] bytes)
     throws IOException, ClassNotFoundException{
        ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
        Object entry = null;
        if(externalizer == null){
            ObjectInputStream ois = new ObjectInputStream(bais);
            entry = ois.readObject();
        }else{
            entry = externalizer.readExternal(bais);
        }
        return entry;
    }
    
    /**
     * ���񉻂����L���b�V���I�u�W�F�N�g���擾����B<p>
     *
     * @return ���񉻂����L���b�V���I�u�W�F�N�g
     */
    public byte[] getBytes(){
        return (byte[])super.get(null, false);
    }
    
    /**
     * �L���b�V�����ꂽ�I�u�W�F�N�g���擾����B<p>
     * ��������true�̏ꍇ�́A{@link #addCacheAccessListener(CacheAccessListener)}�œo�^���ꂽ{@link CacheAccessListener}�ɒʒm����B�A���A�������œn���ꂽ�Ăяo�����I�u�W�F�N�g���ʒm���CacheAccessListener�̃C���X�^���X�Ɠ������ꍇ�́A�ʒm���Ȃ��B<br>
     * ���g���ێ�����L���b�V���I�u�W�F�N�g��null�łȂ��ꍇ�́AFile�I�u�W�F�N�g�ɃL���X�g����{@link #deserializeObject(byte[])}���Ăяo���Ė{���̃L���b�V���I�u�W�F�N�g�𕜌����ĕԂ��B�A���A�����Ɏ��s�����ꍇ�́Anull��Ԃ��B���g���ێ�����L���b�V���I�u�W�F�N�g��null�̏ꍇ�́A{@link #addLinkedReference(LinkedReference)}�œo�^���ꂽ{@link LinkedReference}����擾�����݂�B�擾�ł����ꍇ�́A���l�ɕ������ĕԂ��B�擾�ł��Ȃ������ꍇ�́Anull��Ԃ��B<br>
     *
     * @param source �L���b�V�����擾���邱�̃��\�b�h�̌Ăяo�����I�u�W�F�N�g
     * @param notify �L���b�V���A�N�Z�X���X�i�ɒʒm����ꍇ��true
     * @return �L���b�V���I�u�W�F�N�g
     */
    public Object get(Object source, boolean notify){
        final Object obj = super.get(source, notify);
        if(obj instanceof byte[]){
            try{
                return deserializeObject((byte[])obj);
            }catch(IOException e){
                return null;
            }catch(ClassNotFoundException e){
                return null;
            }
        }else{
            return obj;
        }
    }
    
    public void set(Object source, Object obj)
     throws IllegalCachedReferenceException{
        notifyChange(source, obj);
        if(obj != null){
            try{
                serializeObject(obj);
            }catch(IOException e){
                throw new IllegalCachedReferenceException(e);
            }
        }else{
            cacheObj = null;
        }
    }
    
    public void remove(Object source){
        if(cacheObj != null){
            notifyRemoved(source);
            cacheObj = null;
            if(linkedReferences != null){
                linkedReferences.clear();
            }
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