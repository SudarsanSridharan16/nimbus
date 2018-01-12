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
package jp.ossc.nimbus.service.validator;

import jp.ossc.nimbus.core.*;
import jp.ossc.nimbus.recset.RecordSet;
import jp.ossc.nimbus.beans.dataset.RecordList;

/**
 * {@link MasterValidatorService}�T�[�r�XMBean�C���^�t�F�[�X�B<p>
 *
 * @author M.Takata
 */
public interface MasterValidatorServiceMBean extends ServiceBaseMBean{
    
    /**
     * {@link #setBindData(int, String)}�̃o�C���h�ϐ����w�肷��ۂ̌��ؒl���̂������L�[���B<br>
     */
    public static final String BIND_DATA_VALUE_KEY = "VALUE";
    
    /**
     * {@link jp.ossc.nimbus.service.connection.ConnectionFactory ConnectionFactory}�T�[�r�X�̃T�[�r�X����ݒ肷��B<p>
     *
     * @param name ConnectionFactory�T�[�r�X�̃T�[�r�X��
     */
    public void setConnectionFactoryServiceName(ServiceName name);
    
    /**
     * {@link jp.ossc.nimbus.service.connection.ConnectionFactory ConnectionFactory}�T�[�r�X�̃T�[�r�X�����擾����B<p>
     *
     * @return ConnectionFactory�T�[�r�X�̃T�[�r�X��
     */
    public ServiceName getConnectionFactoryServiceName();
    
    /**
     * {@link jp.ossc.nimbus.service.connection.PersistentManager PersistentManager}�T�[�r�X�̃T�[�r�X����ݒ肷��B<p>
     *
     * @param name PersistentManager�T�[�r�X�̃T�[�r�X��
     */
    public void setPersistentManagerServiceName(ServiceName name);
    
    /**
     * {@link jp.ossc.nimbus.service.connection.PersistentManager PersistentManager}�T�[�r�X�̃T�[�r�X�����擾����B<p>
     *
     * @return PersistentManager�T�[�r�X�̃T�[�r�X��
     */
    public ServiceName getPersistentManagerServiceName();
    
    /**
     * �N�G���������ݒ肷��B<p>
     * {@link jp.ossc.nimbus.service.connection.PersistentManager#loadQuery(java.sql.Connection, String, Object, Object) PersistentManager.loadQuery()}���\�b�h�̑�����query�Ɏg�p����B<br>
     *
     * @param query �N�G��������
     */
    public void setQuery(String query);
    
    /**
     * �N�G����������擾����B<p>
     *
     * @return �N�G��������
     */
    public String getQuery();
    
    /**
     * �}�X�^�̌����Ɏg�p���郌�R�[�h�Z�b�g��ݒ肷��B<p>
     * ���̐ݒ���s�����ꍇ�́A���؂̓s�x�f�[�^�x�[�X����������B�]���āA�}�X�^�̍X�V�p�x�������ꍇ�ɁA�K���Ă���B<br>
     * <p>
     * �����̍ۂɁA�R�l�N�V�������K�v�Ȃ��߁A{@link #setConnectionFactoryServiceName(ServiceName)}��ݒ肷��K�v������B<br>
     * <p>
     * �܂��A�}�X�^�̌��������ɁA���؂���l���܂߂�K�v�����邽�߁A{@link RecordSet#setWhere(String)}�����g���āA���؂���l�𖄂ߍ��ޏ������ݒ肷��K�v������B<br>
     * ������́A���ߍ��ݏ�����ƂȂ�ׂ��ŁA���ߍ��ݏ�����̉��Ԗڂ̖��ߍ��݃p�����[�^�Ɍ��؂���l�܂��͌��؂���l�̃v���p�e�B�𖄂ߍ��ނ���{@link #setBindData(int, String)}�Őݒ肷��K�v������B<br>
     *
     * @param recset �}�X�^�̌����Ɏg�p���郌�R�[�h�Z�b�g
     */
    public void setRecordSet(RecordSet recset);
    
    /**
     * �}�X�^�̌����Ɏg�p���郌�R�[�h�Z�b�g���擾����B<p>
     *
     * @return �}�X�^�̌����Ɏg�p���郌�R�[�h�Z�b�g
     */
    public RecordSet getRecordSet();
    
    /**
     * ���؂���l�܂��͂��̃v���p�e�B���A�}�X�^�̌���������̉��Ԗڂ̖��ߍ��݃p�����[�^�Ƃ��邩��ݒ肷��B<p>
     * ���؂���l�́A{@link #BIND_DATA_VALUE_KEY}�ŎQ�Ƃ���B<br>
     * ���؂���l�̃v���p�e�B�́A{@link jp.ossc.nimbus.beans.PropertyFactory PropertyFactory}�̋K��ɏ]���A�Q�Ɖ\�ł���B<br>
     * ��FVALUE.hoge<br>
     *
     * @param index ���ߍ��݃p�����[�^�̃C���f�b�N�X
     * @param valueKey ���؂���l�܂��͂��̃v���p�e�B��\���L�[������
     */
    public void setBindData(int index, String valueKey);
    
    /**
     * �}�X�^�̌���������̎w�肳�ꂽ���ߍ��݃p�����[�^�C���f�b�N�X�ɁA�ǂ̂悤�Ȓl���o�C���h���邩���擾����B<p>
     *
     * @param index ���ߍ��݃p�����[�^�̃C���f�b�N�X
     * @return ���؂���l�܂��͂��̃v���p�e�B��\���L�[������
     */
    public String getBindData(int index);
    
    /**
     * {@link jp.ossc.nimbus.service.codemaster.CodeMasterFinder CodeMasterFinder}�T�[�r�X�̃T�[�r�X����ݒ肷��B<p>
     * �R�[�h�}�X�^���X���b�h�R���e�L�X�g��ɐݒ肳��Ă��Ȃ��ꍇ�ɁA����CodeMasterFinder���g���ăR�[�h�}�X�^���擾���A{@link #setCodeMasterName(String)}�Őݒ肳�ꂽ���O�̃}�X�^RecordSet���g���āARecordSet���𓮓I�������Č��؂���B<br>
     *
     * @param name CodeMasterFinder�T�[�r�X�̃T�[�r�X��
     */
    public void setCodeMasterFinderServiceName(ServiceName name);
    
    /**
     * {@link jp.ossc.nimbus.service.codemaster.CodeMasterFinder CodeMasterFinder}�T�[�r�X�̃T�[�r�X�����擾����B<p>
     *
     * @return CodeMasterFinder�T�[�r�X�̃T�[�r�X��
     */
    public ServiceName getCodeMasterFinderServiceName();
    
    /**
     * {@link jp.ossc.nimbus.service.context.ThreadContextService ThreadContextService}�̃T�[�r�X����ݒ肷��B<p>
     * {@link #setCodeMasterThreadContextKey(String)}�Őݒ肳�ꂽ�L�[���ŁA���̃X���b�h�R���e�L�X�g����R�[�h�}�X�^���擾���A{@link #setCodeMasterName(String)}�Őݒ肳�ꂽ���O�̃}�X�^RecordSet���g���āARecordSet���𓮓I�������Č��؂���B<br>
     *
     * @param name ThreadContextService�̃T�[�r�X��
     */
    public void setThreadContextServiceName(ServiceName name);
    
    /**
     * {@link jp.ossc.nimbus.service.context.ThreadContextService ThreadContextService}�̃T�[�r�X�����擾����B<p>
     *
     * @return ThreadContextService�̃T�[�r�X��
     */
    public ServiceName getThreadContextServiceName();
    
    /**
     * �X���b�h�R���e�L�X�g����R�[�h�}�X�^���擾����ۂ̃L�[����ݒ肷��B<p>
     * �f�t�H���g�́A{@link jp.ossc.nimbus.service.aop.interceptor.ThreadContextKey#CODEMASTER}�B<br>
     *
     * @param key �R�[�h�}�X�^�L�[��
     */
    public void setCodeMasterThreadContextKey(String key);
    
    /**
     * �X���b�h�R���e�L�X�g����R�[�h�}�X�^���擾����ۂ̃L�[�����擾����B<p>
     *
     * @return �R�[�h�}�X�^�L�[��
     */
    public String getCodeMasterThreadContextKey();
    
    /**
     * �R�[�h�}�X�^����}�X�^RecordSet���擾����ۂ̃}�X�^����ݒ肷��B<p>
     *
     * @param name �}�X�^��
     */
    public void setCodeMasterName(String name);
    
    /**
     * �R�[�h�}�X�^����}�X�^RecordSet���擾����ۂ̃}�X�^�����擾����B<p>
     *
     * @return �}�X�^��
     */
    public String getCodeMasterName();
    
    /**
     * �}�X�^RecordSet�̓��I����������ݒ肷��B<p>
     * ���I���������́A���ߍ��ݏ����ƂȂ�ׂ��ŁA���ߍ��ݏ����̂ǂ̖��ߍ��݃p�����[�^�Ɍ��؂���l�܂��͌��؂���l�̃v���p�e�B�𖄂ߍ��ނ���{@link #setBindDataMap(String, String)}�Őݒ肷��K�v������B<br>
     * ��FCOLUMN1 &gt; VALUE<br>
     * ���I���������̏����́A{@link RecordSet#searchDynamicConditionReal(String, java.util.Map)}���Q�ƁB<br>
     *
     * @param condition ���I��������
     * @deprecated �ʂ̃��\�b�h�ɒu���������܂��� {@link #setSearchCondition(String)}
     */
    public void setRecordSetSearchCondition(String condition);
    
    /**
     * �}�X�^RecordSet�̓��I�����������擾����B<p>
     *
     * @return ���I��������
     * @deprecated �ʂ̃��\�b�h�ɒu���������܂��� {@link #getSearchCondition()}
     */
    public String getRecordSetSearchCondition();
    
    /**
     * �}�X�^RecordSet�܂���RecordList�̓��I����������ݒ肷��B<p>
     * ���I���������́A���ߍ��ݏ����ƂȂ�ׂ��ŁA���ߍ��ݏ����̂ǂ̖��ߍ��݃p�����[�^�Ɍ��؂���l�܂��͌��؂���l�̃v���p�e�B�𖄂ߍ��ނ���{@link #setBindDataMap(String, String)}�Őݒ肷��K�v������B<br>
     * ��FCOLUMN1 &gt; VALUE<br>
     * ���I���������̏����́A{@link RecordSet#searchDynamicConditionReal(String, java.util.Map)}�A{@link RecordList#realSearch(String, java.util.Map)}���Q�ƁB<br>
     *
     * @param condition ���I��������
     */
    public void setSearchCondition(String condition);
    
    /**
     * �}�X�^RecordSet�܂���RecordList�̓��I�����������擾����B<p>
     *
     * @return ���I��������
     */
    public String getSearchCondition();
    
    /**
     * ���؂���l�܂��͂��̃v���p�e�B���A�}�X�^RecordSet�̓��I���������̂ǂ̖��ߍ��݃p�����[�^�Ƃ��邩��ݒ肷��B<p>
     * ���؂���l�́A{@link #BIND_DATA_VALUE_KEY}�ŎQ�Ƃ���B<br>
     * ���؂���l�̃v���p�e�B�́A{@link jp.ossc.nimbus.beans.PropertyFactory PropertyFactory}�̋K��ɏ]���A�Q�Ɖ\�ł���B<br>
     * ��FVALUE.hoge<br>
     *
     * @param name ���ߍ��݃p�����[�^��
     * @param valueKey ���؂���l�܂��͂��̃v���p�e�B��\���L�[������
     */
    public void setBindDataMap(String name, String valueKey);
    
    /**
     * �}�X�^RecordSet�̓��I���������̎w�肳�ꂽ���ߍ��݃p�����[�^���ɁA�ǂ̂悤�Ȓl���o�C���h���邩���擾����B<p>
     *
     * @param name ���ߍ��݃p�����[�^��
     * @return ���؂���l�܂��͂��̃v���p�e�B��\���L�[������
     */
    public String getBindDataMap(String name);
}
