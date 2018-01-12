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
package jp.ossc.nimbus.service.journal;

import java.io.*;

import jp.ossc.nimbus.service.journal.editorfinder.*;
import jp.ossc.nimbus.core.*;

/**
 * {@link JournalRecord}�����N���X�B<p>
 * 
 * @author H.Nakano
 */
public class JournalRecordImpl implements JournalRecord, java.io.Serializable{
    
    private static final long serialVersionUID = 4377038814532613910L;
    
    /** �L�[ */
    private String mKey ;
    /** �G�f�B�^�[�^�C�v */
    private transient EditorFinder mFinder ;
    private ServiceName mFinderName ;
    private Object mEditObj ;
    /** �o�^�I�u�W�F�N�g*/
    private transient Object mParamObj ;
    
    /**
     * ��̃C���X�^���X�𐶐�����B<p>
     */
    public JournalRecordImpl(){
        super();
        this.mKey = null;
        this.mFinder = null;
        this.mEditObj = null;
        this.mParamObj = null;
    }
    
    // JournalRecord ��JavaDoc
    public String getKey(){
        return mKey;
    }
    
    /**
     * �W���[�i�����̃L�[��ݒ肷��B<p>
     * 
     * @param key �W���[�i�����̃L�[
     * @see #getKey()
     */
    public void setKey(String key){
        mKey = key;
    }
    
    /**
     * �W���[�i������ҏW����{@link JournalEditor}��ݒ肷��B<p>
     * 
     * @param finder �W���[�i������ҏW����JournalEditor
     * @see #getFinder()
     */
    public void setEditorFinder(EditorFinder finder){
        mFinder = finder ;
        if(mFinder instanceof ServiceBase){
            mFinderName = ((ServiceBase)mFinder).getServiceNameObject();
        }else if(mFinder instanceof Service){
            final Service service = (Service)mFinder;
            mFinderName = new ServiceName(
                service.getServiceManagerName(),
                service.getServiceName()
            );
        }
    }
    
    /**
     * {@link RequestJournal}��ݒ肷��B<p>
     *
     * @param obj RequestJournal
     */
    public void setParamObj(Object obj){
        mParamObj = obj ;
    }
    
    /**
     * �W���[�i������ݒ肷��B<p>
     *
     * @param obj �W���[�i�����
     */
    public void setInfoObj(Object obj){
        mParamObj = obj;
        JournalEditor editor = getJournalEditor();
        this.mEditObj = editor.toObject(mFinder,mKey,obj) ;
    }
    
    // JournalRecord ��JavaDoc
    public Object toObject(){
        if(this.mEditObj == null){
            JournalEditor editor = getJournalEditor() ;
            mEditObj = editor.toObject(mFinder,mKey,mParamObj) ;
        }
        return mEditObj ;
    }
    
    // JournalRecord ��JavaDoc
    public Object toObject( EditorFinder finder ){
        Object ret = null;
        if(finder != null && mEditObj == null){
            final JournalEditor editor = finder.findEditor(mKey, mParamObj);
            ret = editor.toObject(finder,mKey,mParamObj) ;
        }
        return ret;
    }
    
    /**
     * �W���[�i�������擾����B<p>
     *
     * @return �W���[�i�����
     */
    public Object getObject(){
        return mParamObj ;
    }
    
    /**
     * �W���[�i������ҏW����{@link JournalEditor}����������{@link EditorFinder}���擾����B<p>
     * 
     * @return �W���[�i������ҏW����JournalEditor����������EditorFinder
     */
    public EditorFinder getFinder(){
        return mFinder ;
    }
    
    // JournalRecord ��JavaDoc
    public JournalEditor getJournalEditor(){
        
        return mFinder == null ? null : mFinder.findEditor(mKey, mParamObj) ;
    }
    
    // JournalRecord ��JavaDoc
    public boolean isStep(){
        return (mParamObj != null && mParamObj instanceof RequestJournal);
    }
    
    private void writeObject(ObjectOutputStream out) throws IOException{
        out.defaultWriteObject();
        if(mFinderName == null && mFinder != null){
            out.writeObject(mFinder);
        }
        if(mParamObj != null && mParamObj instanceof RequestJournal){
            out.writeObject(mParamObj);
        }else{
            out.writeObject(null);
        }
    }
    
    private void readObject(ObjectInputStream in)
     throws IOException, ClassNotFoundException{
        in.defaultReadObject();
        if(mFinderName == null){
            mFinder = (EditorFinder)in.readObject();
        }else{
            try{
                mFinder = (EditorFinder)ServiceManagerFactory
                    .getServiceObject(mFinderName);
            }catch(ServiceNotFoundException e){
            }
        }
        mParamObj = in.readObject();
    }
}
