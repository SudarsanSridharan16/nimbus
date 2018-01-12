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

/**
 * �L�[�t���L���b�V���Q�ƁB<p>
 * {@link KeyCachedReference}�̃f�t�H���g�����ł���B<br>
 * �L���b�V���I�u�W�F�N�g�����Q�Ƃŕێ�����B<br>
 * {@link DefaultCachedReference}�̋@�\�ɉ����āA�L���b�V���̃L�[����ێ�����B<br>
 *
 * @author M.Takata
 */
public class DefaultKeyCachedReference extends DefaultCachedReference
 implements KeyCachedReference, java.io.Serializable{
    
    private static final long serialVersionUID = -8916648261683575827L;
    
    protected Object cacheKey;
    
    /**
     * �L���b�V���̃L�[�ƃL���b�V���I�u�W�F�N�g��ێ�����L���b�V���Q�Ƃ𐶐�����B<p>
     *
     * @param key �L���b�V���̃L�[
     * @param obj �L���b�V���I�u�W�F�N�g
     */
    public DefaultKeyCachedReference(Object key, Object obj){
        super(obj);
        cacheKey = key;
    }
    
    // KeyCachedReferenc��JavaDoc
    public Object getKey(){
        return cacheKey;
    }
    
    private void writeObject(ObjectOutputStream out) throws IOException{
        out.defaultWriteObject();
        writeSet(out, linkedReferences);
        writeSet(out, removeListeners);
        writeSet(out, accessListeners);
        writeSet(out, changeListeners);
    }
    
    private void readObject(ObjectInputStream in)
     throws IOException, ClassNotFoundException{
        in.defaultReadObject();
        linkedReferences = readSet(in);
        removeListeners = readSet(in);
        accessListeners = readSet(in);
        changeListeners = readSet(in);
    }
}