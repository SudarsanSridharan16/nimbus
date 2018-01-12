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

import java.util.*;
import jp.ossc.nimbus.service.journal.editorfinder.*;

/**
 * {@link RequestJournal}�����N���X�B<p>
 * 
 * @author   H.Nakano
 */
public class RequestJournalImpl implements RequestJournal, java.io.Serializable{
    
    private static final long serialVersionUID = 687167860749686268L;
    
    /**
     * �W���[�i�����R�[�h�̃��X�g�B<p>
     */
    protected List mRequestAry;
    
    /**
     * �X�e�b�v�J�n�����B<p>
     */
    protected Date mStartTime;
    
    /**
     * �X�e�b�v�I�������B<p>
     */
    protected Date mEndTime;
    
    /**
     * �X�e�b�v�̃L�[�B<p>
     */
    protected String mKey;
    
    /**
     * ���N�G�X�gID�B<p>
     */
    protected String mRequestId;
    
    /**
     * �e�X�e�b�v��JournalRecord�B<p>
     */
    protected JournalRecordImpl mStepRoot;
    
    /**
     * ���݂̃X�e�b�v��JournalRecord�B<p>
     */
    protected JournalRecordImpl mCurrentRoot;
    
    /**
     * �X�e�b�v���ǂ����̃t���O�B<p>
     * true�̏ꍇ�X�e�b�v�B<br>
     */
    protected boolean mIsStep = false ;
    
    /**
     * �C���X�^���X�𐶐�����B<p>
     *
     * @param isStep �X�e�b�v�̏ꍇtrue
     */
    public RequestJournalImpl(boolean isStep) {
        super();
        mRequestAry = new ArrayList();
        mStepRoot = null;
        this.mCurrentRoot = null;
        mIsStep = isStep;
    }
    
    /**
     * �X�e�b�v���ǂ����𔻒肷��B<p>
     *
     * @return �X�e�b�v�̏ꍇtrue
     */
    public boolean isStep(){
        return this.mIsStep;
    }
    
    /**
     * �X�e�b�v�̃L�[��ݒ肷��B<p>
     *
     * @param key �X�e�b�v�̃L�[
     */
    public void setKey(String key){
        mKey=key;
    }
    
    /**
     * ���N�G�X�gID��ݒ肷��B<p>
     *
     * @param id ���N�G�X�gID
     */
    public void setRequestId(String id){
        mRequestId=id;
    }
    
    /**
     * �X�e�b�v�̊J�n������ݒ肷��B<p>
     *
     * @param dt �X�e�b�v�̊J�n����
     */
    public void setStartTime(Date dt){
        mStartTime = dt;
    }
    
    /**
     * ���ݎ������X�e�b�v�̊J�n�����ɐݒ肷��B<p>
     */
    public void setStartTime(){
        mStartTime = new Date();
    }
    
    /**
     * ���ݎ������X�e�b�v�̏I�������ɐݒ肷��B<p>
     */
    public void setEndTime(){
        mEndTime = new Date();
    }
    
    /**
     * �X�e�b�v�̏I��������ݒ肷��B<p>
     *
     * @param time �X�e�b�v�̏I������
     */
    public void setEndTime(Date time){
        mEndTime = time;
    }
    
    /**
     * �X�e�b�v��{@link RequestJournal}��ݒ肷��B<p>
     * 
     * @param key �L�[
     * @param finder RequestJournal��ҏW����{@link JournalEditor}����������{@link EditorFinder}
     * @param obj RequestJournal�I�u�W�F�N�g
     * @return RequestJournal���i�[����{@link JournalRecord}
     */
    public JournalRecord setParamObj(
        String key,
        EditorFinder finder,
        Object obj
    ){
        JournalRecordImpl rec = new JournalRecordImpl();
        rec.setEditorFinder(finder);
        rec.setKey(key);
        rec.setParamObj(obj);
        this.mRequestAry.add(rec);
        return rec;
    }
    
    /**
     * �X�e�b�v�̃W���[�i������ݒ肷��B<p>
     * 
     * @param key �L�[
     * @param finder �W���[�i������ҏW����{@link JournalEditor}����������{@link EditorFinder}
     * @param obj �W���[�i�����
     * @return �W���[�i�������i�[����{@link JournalRecord}
     */
    public JournalRecord setInfoObj(
        String key,
        EditorFinder finder,
        Object obj
    ){
        JournalRecordImpl rec = new JournalRecordImpl();
        rec.setEditorFinder(finder);
        rec.setKey(key);
        rec.setInfoObj(obj);
        this.mRequestAry.add(rec);
        return rec;
    }
    
    // RequestJournal ��JavaDoc
    public String getKey(){
        return this.mKey;
    }
    
    // RequestJournal ��JavaDoc
    public String getRequestId(){
        return this.mRequestId;
    }
    
    // RequestJournal ��JavaDoc
    public Date getStartTime(){
        return this.mStartTime;
    }
    
    // RequestJournal ��JavaDoc
    public Date getEndTime(){
        if(mEndTime == null){
            mEndTime = new Date();
        }
        return this.mEndTime;
    }
    
    // RequestJournal ��JavaDoc
    public long getPerformance(){
        if(mEndTime == null){
            mEndTime = new Date();
        }
        return this.mEndTime.getTime() - this.mStartTime.getTime();
    }
    
    // RequestJournal ��JavaDoc
    public JournalRecord[] getParamAry(){
        JournalRecord[] ret = new JournalRecord[this.mRequestAry.size()];
        for(int cnt = 0 ;cnt < ret.length; cnt++){
            ret[cnt] = (JournalRecord)this.mRequestAry.get(cnt);
        }
        return ret;
    }
    
    /**
     * �e�X�e�b�v�ƌ��݂̃X�e�b�v��{@link JournalRecord}��ݒ肷��B<p>
     *
     * @param stepRoot �e�X�e�b�v
     * @param curRoot ���݃X�e�b�v
     */
    public void setRoot(
        JournalRecordImpl stepRoot,
        JournalRecordImpl curRoot
    ){
        this.mStepRoot = stepRoot;
        this.mCurrentRoot =curRoot;
    }
    
    /**
     * �e�X�e�b�v��{@link JournalRecord}���擾����B<p>
     *
     * @return �e�X�e�b�v��JournalRecord
     */
    public JournalRecordImpl getStepRoot(){
        return this.mStepRoot;
    }
    
    /**
     * ���݃X�e�b�v��{@link JournalRecord}���擾����B<p>
     *
     * @return ���݃X�e�b�v��JournalRecord
     */
    public JournalRecordImpl getCurRoot(){
        return this.mCurrentRoot;
    }
    
    // RequestJournal ��JavaDoc
    public boolean isRoot(){
        return getCurRoot() == null;
    }
    
    // RequestJournal ��JavaDoc
    public JournalRecord[] findParamArys(String key){
        List retAry = new ArrayList();
        for(Iterator iterator = this.mRequestAry.iterator(); iterator.hasNext(); ){
            JournalRecord tmp =(JournalRecord)iterator.next();
            if(tmp.getKey().equals(key)){
                retAry.add(tmp);
            }
        }        
        JournalRecord[] ret = new JournalRecord[retAry.size()];
        for(int cnt = 0; cnt < ret.length; cnt++){
            ret[cnt] = (JournalRecord)retAry.get(cnt);
        }
        return ret;
    }
    
    public void clearParam(int from){
        for(int i = from, imax = this.mRequestAry.size(); i < imax; i++){
            this.mRequestAry.remove(from);
        }
    }
}