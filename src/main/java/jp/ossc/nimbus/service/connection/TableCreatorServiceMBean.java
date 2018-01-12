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
package jp.ossc.nimbus.service.connection;

import java.sql.SQLException;
import java.io.IOException;

import jp.ossc.nimbus.core.*;
import jp.ossc.nimbus.beans.dataset.RecordList;
import jp.ossc.nimbus.util.converter.ConvertException;

/**
 * {@link TableCreatorService}��MBean�C���^�t�F�[�X<p>
 * 
 * @author M.Takata
 * @see TableCreatorService
 */
public interface TableCreatorServiceMBean extends ServiceBaseMBean{
    
    /**
     * {@link ConnectionFactory}�T�[�r�X�̃T�[�r�X����ݒ肷��B<p>
     *
     * @param name ConnectionFactory�̃T�[�r�X��
     */
    public void setConnectionFactoryServiceName(ServiceName name);
    
    /**
     * {@link ConnectionFactory}�T�[�r�X�̃T�[�r�X�����擾����B<p>
     *
     * @return ConnectionFactory�̃T�[�r�X��
     */
    public ServiceName getConnectionFactoryServiceName();
    
    /**
     * {@link jp.ossc.nimbus.util.converter.StreamConverter StreamConverter}�T�[�r�X�̃T�[�r�X����ݒ肷��B<p>
     * ����StreamConverter���g���āA{@link #setInsertRecords(String)}��{@link #setInsertRecordsFilePath(String)}�Ŏw�肳�ꂽ�}�����R�[�h�������{@link jp.ossc.nimbus.beans.dataset.RecordList RecordList}�ɕϊ����āA�e�[�u����INSERT���Ă����B<br>
     * �܂��A{@link #setBackupFilePath(String)}�Ŏw�肳�ꂽ�t�@�C���Ƀo�b�N�A�b�v����ۂ�A���l�ɂ��̃t�@�C�����畜������ۂɂ��g�p����B<br>
     *
     * @param name StreamConverter�̃T�[�r�X��
     */
    public void setRecordListConverterServiceName(ServiceName name);
    
    /**
     * {@link jp.ossc.nimbus.util.converter.StreamConverter StreamConverter}�T�[�r�X�̃T�[�r�X�����擾����B<p>
     *
     * @return StreamConverter�̃T�[�r�X��
     */
    public ServiceName getRecordListConverterServiceName();
    
    /**
     * �ΏۂƂȂ�e�[�u���̑��݊m�F������SQL��ݒ肷��B<p>
     * ���ʂƂ��āA�e�[�u���̌�����Ԃ�SQL�ɂ���K�v������B<br>
     *
     * @param query �e�[�u���̑��݊m�F������SQL
     */
    public void setExistsTableQuery(String query);
    
    /**
     * �ΏۂƂȂ�e�[�u���̑��݊m�F������SQL���擾����B<p>
     *
     * @return �e�[�u���̑��݊m�F������SQL
     */
    public String getExistsTableQuery();
    
    /**
     * �o�b�N�A�b�v�Ώۂ̃��R�[�h����������SQL��ݒ肷��B<p>
     *
     * @param query �o�b�N�A�b�v�Ώۂ̃��R�[�h����������SQL
     */
    public void setSelectQuery(String query);
    
    /**
     * �o�b�N�A�b�v�Ώۂ̃��R�[�h����������SQL���擾����B<p>
     *
     * @return �o�b�N�A�b�v�Ώۂ̃��R�[�h����������SQL
     */
    public String getSelectQuery();
    
    /**
     * �ΏۂƂȂ�e�[�u�����쐬����SQL��ݒ肷��B<p>
     *
     * @param query �e�[�u�����쐬����SQL
     */
    public void setCreateTableQuery(String query);
    
    /**
     * �ΏۂƂȂ�e�[�u�����쐬����SQL���擾����B<p>
     *
     * @return �e�[�u�����쐬����SQL
     */
    public String getCreateTableQuery();
    
    /**
     * �ΏۂƂȂ�e�[�u�����쐬���钼�O�Ɏ��s����SQL��ݒ肷��B<p>
     *
     * @param queries �e�[�u�����쐬���钼�O�Ɏ��s����SQL
     */
    public void setPreCreateTableQueries(String[] queries);
    
    /**
     * �ΏۂƂȂ�e�[�u�����쐬���钼�O�Ɏ��s����SQL���擾����B<p>
     *
     * @return �e�[�u�����쐬���钼�O�Ɏ��s����SQL
     */
    public String[] getPreCreateTableQueries();
    
    /**
     * �ΏۂƂȂ�e�[�u�����쐬��������Ɏ��s����SQL��ݒ肷��B<p>
     *
     * @param queries �e�[�u�����쐬��������Ɏ��s����SQL
     */
    public void setPostCreateTableQueries(String[] queries);
    
    /**
     * �ΏۂƂȂ�e�[�u�����쐬��������Ɏ��s����SQL���擾����B<p>
     *
     * @return �e�[�u�����쐬��������Ɏ��s����SQL
     */
    public String[] getPostCreateTableQueries();
    
    /**
     * �ΏۂƂȂ�e�[�u�����폜����SQL��ݒ肷��B<p>
     *
     * @param query �e�[�u�����폜����SQL
     */
    public void setDropTableQuery(String query);
    
    /**
     * �ΏۂƂȂ�e�[�u�����폜����SQL���擾����B<p>
     *
     * @return �e�[�u�����폜����SQL
     */
    public String getDropTableQuery();
    
    /**
     * �ΏۂƂȂ�e�[�u�����폜���钼�O�Ɏ��s����SQL��ݒ肷��B<p>
     *
     * @param queries �e�[�u�����폜���钼�O�Ɏ��s����SQL
     */
    public void setPreDropTableQueries(String[] queries);
    
    /**
     * �ΏۂƂȂ�e�[�u�����폜���钼�O�Ɏ��s����SQL���擾����B<p>
     *
     * @return �e�[�u�����폜���钼�O�Ɏ��s����SQL
     */
    public String[] getPreDropTableQueries();
    
    /**
     * �ΏۂƂȂ�e�[�u�����폜��������Ɏ��s����SQL��ݒ肷��B<p>
     *
     * @param queries �e�[�u�����폜��������Ɏ��s����SQL
     */
    public void setPostDropTableQueries(String[] queries);
    
    /**
     * �ΏۂƂȂ�e�[�u�����폜��������Ɏ��s����SQL���擾����B<p>
     *
     * @return �e�[�u�����폜��������Ɏ��s����SQL
     */
    public String[] getPostDropTableQueries();
    
    /**
     * �ΏۂƂȂ�e�[�u���̃��R�[�h���폜����SQL��ݒ肷��B<p>
     *
     * @param query �e�[�u���̃��R�[�h���폜����SQL
     */
    public void setDeleteQuery(String query);
    
    /**
     * �ΏۂƂȂ�e�[�u���̃��R�[�h���폜����SQL���擾����B<p>
     *
     * @return �e�[�u���̃��R�[�h���폜����SQL
     */
    public String getDeleteQuery();
    
    /**
     * �ΏۂƂȂ�e�[�u���̃��R�[�h��}�����閄�ߍ���SQL��ݒ肷��B<p>
     *
     * @param query �e�[�u���̃��R�[�h��}�����閄�ߍ���SQL
     */
    public void setInsertQuery(String query);
    
    /**
     * �ΏۂƂȂ�e�[�u���̃��R�[�h��}�����閄�ߍ���SQL���擾����B<p>
     *
     * @return �e�[�u���̃��R�[�h��}�����閄�ߍ���SQL
     */
    public String getInsertQuery();
    
    /**
     * �}�����郌�R�[�h�������ݒ肷��B<p>
     * ���R�[�h������́A�o�C�g�X�g���[���ɕϊ�����A{@link #setRecordListConverterServiceName(ServiceName)}�Őݒ肳�ꂽ{@link jp.ossc.nimbus.util.converter.StreamConverter StreamConverter}�T�[�r�X�ɂ���āA{@link jp.ossc.nimbus.beans.dataset.RecordList RecordList}�ɕϊ������B<br>
     * �ϊ����ꂽRecordList����{@link jp.ossc.nimbus.beans.dataset.Record Record}���A{@link #setInsertQuery(String)}�Ŏw�肳�ꂽ���ߍ���SQL�ɂ���āA�e�[�u���ɑ}�������B<br>
     * ���R�[�h������̎w����O���t�@�C���ōs�������ꍇ�́A{@link #setInsertRecordsFilePath(String)}�Őݒ肷��B<br>
     *
     * @param records ���R�[�h������
     */
    public void setInsertRecords(String records);
    
    /**
     * �}�����郌�R�[�h��������擾����B<p>
     *
     * @return ���R�[�h������
     */
    public String getInsertRecords();
    
    /**
     * �}�����郌�R�[�h�t�@�C���̃p�X��ݒ肷��B<p>
     * �t�@�C���́A�X�g���[���ɕϊ�����A{@link #setRecordListConverterServiceName(ServiceName)}�Őݒ肳�ꂽ{@link jp.ossc.nimbus.util.converter.StreamConverter StreamConverter}�T�[�r�X�ɂ���āA{@link jp.ossc.nimbus.beans.dataset.RecordList RecordList}�ɕϊ������B<br>
     * �ϊ����ꂽRecordList����{@link jp.ossc.nimbus.beans.dataset.Record Record}���A{@link #setInsertQuery(String)}�Ŏw�肳�ꂽ���ߍ���SQL�ɂ���āA�e�[�u���ɑ}�������B<br>
     * ���R�[�h������̎w��𒼐ڍs�������ꍇ�́A{@link #setInsertRecords(String)}�Őݒ肷��B<br>
     *
     * @param path ���R�[�h�t�@�C���p�X
     */
    public void setInsertRecordsFilePath(String path);
    
    /**
     * �}�����郌�R�[�h�t�@�C���̃p�X��ݒ肷��B<p>
     *
     * @return ���R�[�h�t�@�C���̃p�X
     */
    public String getInsertRecordsFilePath();
    
    /**
     * �}�����郌�R�[�h�t�@�C���y�уo�b�N�A�b�v�t�@�C���̕����G���R�[�f�B���O��ݒ肷��B<p>
     *
     * @param enc �����G���R�[�f�B���O
     */
    public void setFileEncoding(String enc);
    
    /**
     * �}�����郌�R�[�h�t�@�C���y�уo�b�N�A�b�v�t�@�C���̕����G���R�[�f�B���O���擾����B<p>
     *
     * @return �����G���R�[�f�B���O
     */
    public String getFileEncoding();
    
    /**
     * ���R�[�h�������ǂݍ���{@link jp.ossc.nimbus.beans.dataset.RecordList RecordList}�̃X�L�[�}��ݒ肷��B<p>
     *
     * @param schema �X�L�[�}������
     */
    public void setRecordListSchema(String schema);
    
    /**
     * ���R�[�h�������ǂݍ���{@link jp.ossc.nimbus.beans.dataset.RecordList RecordList}�̃X�L�[�}���擾����B<p>
     *
     * @return �X�L�[�}������
     */
    public String getRecordListSchema();
    
    /**
     * ���R�[�h�������ǂݍ���{@link jp.ossc.nimbus.beans.dataset.RecordList RecordList}��ݒ肷��B<p>
     *
     * @param list RecordList
     */
    public void setRecordList(RecordList list);
    
    /**
     * ���R�[�h�������ǂݍ���{@link jp.ossc.nimbus.beans.dataset.RecordList RecordList}���擾����B<p>
     *
     * @return RecordList
     */
    public RecordList getRecordList();
    
    /**
     * �e�[�u���Ƀ��R�[�h���o�b�`���s�ő}������ꍇ�̃o�b�`������ݒ肷��B<p>
     * �f�t�H���g�́A0�Ńo�b�`���s���Ȃ��B<br>
     *
     * @param size �o�b�`����
     */
    public void setInsertBatchSize(int size);
    
    /**
     * �e�[�u���Ƀ��R�[�h���o�b�`���s�ő}������ꍇ�̃o�b�`�������擾����B<p>
     *
     * @return �o�b�`����
     */
    public int getInsertBatchSize();
    
    /**
     * �e�[�u�����烌�R�[�h����������ۂ̃t�F�b�`�T�C�Y��ݒ肷��B<p>
     *
     * @param size �t�F�b�`�T�C�Y
     */
    public void setFetchSize(int size);
    
    /**
     * �e�[�u�����烌�R�[�h����������ۂ̃t�F�b�`�T�C�Y���擾����B<p>
     *
     * @return �t�F�b�`�T�C�Y
     */
    public int getFetchSize();
    
    /**
     * �T�[�r�X�̊J�n���ɁA���݂̃e�[�u���̓��e���o�b�N�A�b�v���邩�ǂ�����ݒ肷��B<p>
     * �f�t�H���g�́Afalse�Ńo�b�N�A�b�v���Ȃ��B<br>
     *
     * @param isBackup �o�b�N�A�b�v����ꍇ�́Atrue
     */
    public void setBackupOnStart(boolean isBackup);
    
    /**
     * �T�[�r�X�̊J�n���ɁA���݂̃e�[�u���̓��e���o�b�N�A�b�v���邩�ǂ����𔻒肷��B<p>
     *
     * @return true�̏ꍇ�́A�o�b�N�A�b�v����
     */
    public boolean isBackupOnStart();
    
    /**
     * �T�[�r�X�̊J�n���ɁA���݂̃e�[�u���̓��e�𕜌����邩�ǂ�����ݒ肷��B<p>
     * �f�t�H���g�́Afalse�ŕ������Ȃ��B<br>
     *
     * @param isRestore ��������ꍇ�́Atrue
     */
    public void setRestoreOnStart(boolean isRestore);
    
    /**
     * �T�[�r�X�̊J�n���ɁA���݂̃e�[�u���̓��e�𕜌����邩�ǂ����𔻒肷��B<p>
     *
     * @return true�̏ꍇ�́A��������
     */
    public boolean isRestoreOnStart();
    
    /**
     * �T�[�r�X�̊J�n���ɁA�e�[�u�����폜���邩�ǂ�����ݒ肷��B<p>
     * �f�t�H���g�́Afalse�ō폜���Ȃ��B<br>
     *
     * @param isDrop �폜����ꍇ�́Atrue
     */
    public void setDropTableOnStart(boolean isDrop);
    
    /**
     * �T�[�r�X�̊J�n���ɁA�e�[�u�����폜���邩�ǂ����𔻒肷��B<p>
     *
     * @return true�̏ꍇ�́A�폜����
     */
    public boolean isDropTableOnStart();
    
    /**
     * �T�[�r�X�̊J�n���ɁA�e�[�u�����쐬���邩�ǂ�����ݒ肷��B<p>
     * �f�t�H���g�́Afalse�ō쐬���Ȃ��B<br>
     *
     * @param isCreate �쐬����ꍇ�́Atrue
     */
    public void setCreateTableOnStart(boolean isCreate);
    
    /**
     * �T�[�r�X�̊J�n���ɁA�e�[�u�����쐬���邩�ǂ����𔻒肷��B<p>
     *
     * @return true�̏ꍇ�́A�쐬����
     */
    public boolean isCreateTableOnStart();
    
    /**
     * �T�[�r�X�̊J�n���ɁA�e�[�u���̃��R�[�h���폜���邩�ǂ�����ݒ肷��B<p>
     * �f�t�H���g�́Afalse�ō폜���Ȃ��B<br>
     *
     * @param isDelete �폜����ꍇ�́Atrue
     */
    public void setDeleteOnStart(boolean isDelete);
    
    /**
     * �T�[�r�X�̊J�n���ɁA�e�[�u���̃��R�[�h���폜���邩�ǂ����𔻒肷��B<p>
     *
     * @return true�̏ꍇ�́A�폜����
     */
    public boolean isDeleteOnStart();
    
    /**
     * �T�[�r�X�̊J�n���ɁA�e�[�u���̃��R�[�h��}�����邩�ǂ�����ݒ肷��B<p>
     * �f�t�H���g�́Afalse�ő}�����Ȃ��B<br>
     *
     * @param isInsert �}������ꍇ�́Atrue
     */
    public void setInsertOnStart(boolean isInsert);
    
    /**
     * �T�[�r�X�̊J�n���ɁA�e�[�u���̃��R�[�h��}�����邩�ǂ����𔻒肷��B<p>
     *
     * @return true�̏ꍇ�́A�}������
     */
    public boolean isInsertOnStart();
    
    /**
     * �T�[�r�X�̒�~���ɁA�e�[�u���̃��R�[�h���폜���邩�ǂ�����ݒ肷��B<p>
     * �f�t�H���g�́Afalse�ō폜���Ȃ��B<br>
     *
     * @param isDelete �폜����ꍇ�́Atrue
     */
    public void setDeleteOnStop(boolean isDelete);
    
    /**
     * �T�[�r�X�̒�~���ɁA�e�[�u���̃��R�[�h���폜���邩�ǂ����𔻒肷��B<p>
     *
     * @return true�̏ꍇ�́A�폜����
     */
    public boolean isDeleteOnStop();
    
    /**
     * �T�[�r�X�̒�~���ɁA�e�[�u���̃��R�[�h��}�����邩�ǂ�����ݒ肷��B<p>
     * �f�t�H���g�́Afalse�ő}�����Ȃ��B<br>
     *
     * @param isInsert �}������ꍇ�́Atrue
     */
    public void setInsertOnStop(boolean isInsert);
    
    /**
     * �T�[�r�X�̒�~���ɁA�e�[�u���̃��R�[�h��}�����邩�ǂ����𔻒肷��B<p>
     *
     * @return true�̏ꍇ�́A�}������
     */
    public boolean isInsertOnStop();
    
    /**
     * �T�[�r�X�̒�~���ɁA�e�[�u�����폜���邩�ǂ�����ݒ肷��B<p>
     * �f�t�H���g�́Afalse�ō폜���Ȃ��B<br>
     *
     * @param isDrop �폜����ꍇ�́Atrue
     */
    public void setDropTableOnStop(boolean isDrop);
    
    /**
     * �T�[�r�X�̒�~���ɁA�e�[�u�����폜���邩�ǂ����𔻒肷��B<p>
     *
     * @return true�̏ꍇ�́A�폜����
     */
    public boolean isDropTableOnStop();
    
    /**
     * �T�[�r�X�̒�~���ɁA�e�[�u���𕜌����邩�ǂ�����ݒ肷��B<p>
     * �f�t�H���g�́Afalse�ŕ������Ȃ��B<br>
     *
     * @param isRestore ��������ꍇ�́Atrue
     */
    public void setRestoreOnStop(boolean isRestore);
    
    /**
     * �T�[�r�X�̒�~���ɁA�e�[�u���𕜌����邩�ǂ����𔻒肷��B<p>
     *
     * @return true�̏ꍇ�́A��������
     */
    public boolean isRestoreOnStop();
    
    /**
     * �T�[�r�X�̒�~���ɁA���݂̃e�[�u�����o�b�N�A�b�v���邩�ǂ�����ݒ肷��B<p>
     * �f�t�H���g�́Afalse�Ńo�b�N�A�b�v���Ȃ��B<br>
     *
     * @param isBackup �o�b�N�A�b�v����ꍇ�́Atrue
     */
    public void setBackupOnStop(boolean isBackup);
    
    /**
     * �T�[�r�X�̒�~���ɁA���݂̃e�[�u�����o�b�N�A�b�v���邩�ǂ����𔻒肷��B<p>
     *
     * @return true�̏ꍇ�́A�o�b�N�A�b�v����
     */
    public boolean isBackupOnStop();
    
    /**
     * �e�[�u�����폜����ۂɖ�������SQLException�̃G���[�R�[�h��ݒ肷��B<p>
     * �f�t�H���g�ł́A�S�Ă�SQLException�𖳎����Ȃ��B<br>
     *
     * @param code �G���[�R�[�h�̔z��
     */
    public void setIgnoreSQLExceptionErrorCodeOnDropTable(int[] code);
    
    /**
     * �e�[�u�����폜����ۂɖ�������SQLException�̃G���[�R�[�h���擾����B<p>
     *
     * @return �G���[�R�[�h�̔z��
     */
    public int[] getIgnoreSQLExceptionErrorCodeOnDropTable();
    
    /**
     * �e�[�u���̃��R�[�h���폜����ۂɖ�������SQLException�̃G���[�R�[�h��ݒ肷��B<p>
     * �f�t�H���g�ł́A�S�Ă�SQLException�𖳎����Ȃ��B<br>
     *
     * @param code �G���[�R�[�h�̔z��
     */
    public void setIgnoreSQLExceptionErrorCodeOnDelete(int[] code);
    
    /**
     * �e�[�u���̃��R�[�h���폜����ۂɖ�������SQLException�̃G���[�R�[�h���擾����B<p>
     *
     * @return �G���[�R�[�h�̔z��
     */
    public int[] getIgnoreSQLExceptionErrorCodeOnDelete();
    
    /**
     * �e�[�u�����쐬����ۂɖ�������SQLException�̃G���[�R�[�h��ݒ肷��B<p>
     * �f�t�H���g�ł́A�S�Ă�SQLException�𖳎����Ȃ��B<br>
     *
     * @param code �G���[�R�[�h�̔z��
     */
    public void setIgnoreSQLExceptionErrorCodeOnCreateTable(int[] code);
    
    /**
     * �e�[�u�����쐬����ۂɖ�������SQLException�̃G���[�R�[�h���擾����B<p>
     *
     * @return �G���[�R�[�h�̔z��
     */
    public int[] getIgnoreSQLExceptionErrorCodeOnCreateTable();
    
    /**
     * �e�[�u���̃��R�[�h��}������ۂɖ�������SQLException�̃G���[�R�[�h��ݒ肷��B<p>
     * �f�t�H���g�ł́A�S�Ă�SQLException�𖳎����Ȃ��B<br>
     *
     * @param code �G���[�R�[�h�̔z��
     */
    public void setIgnoreSQLExceptionErrorCodeOnInsert(int[] code);
    
    /**
     * �e�[�u���̃��R�[�h��}������ۂɖ�������SQLException�̃G���[�R�[�h���擾����B<p>
     *
     * @return �G���[�R�[�h�̔z��
     */
    public int[] getIgnoreSQLExceptionErrorCodeOnInsert();
    
    /**
     * ��A�̃e�[�u��������g�����U�N�V�����I�Ɏ��s���邩�ǂ�����ݒ肷��B<p>
     * ��A�̃e�[�u������Ƃ́A�T�[�r�X�̊J�n���y�ђ�~���̃e�[�u������A{@link #executeAllQuery()}�Ăяo�����̑���ł���B<br>
     *
     * @param isTransacted �g�����U�N�V�����I�Ɏ��s����ꍇtrue
     */
    public void setTransacted(boolean isTransacted);
    
    /**
     * ��A�̃e�[�u��������g�����U�N�V�����I�Ɏ��s���邩�ǂ����𔻒肷��B<p>
     *
     * @return true�̏ꍇ�A�g�����U�N�V�����I�Ɏ��s����
     */
    public boolean isTransacted();
    
    /**
     * Java�̌^�ɑ΂���AJDBC�̌^��ݒ肷��B<p>
     * �e�[�u���Ƀ��R�[�h��}�����鎞�ɁA�l��null�̏ꍇ�ɁA���̃}�b�s���O��p���āA{@link java.sql.PreparedStatement#setNull(int, int)}���Ăяo���B<br>
     *
     * @param type Java�̌^
     * @param sqlType JDBC�̌^�B{@link java.sql.Types}�̒萔�l
     */
    public void setSqlType(Class type, int sqlType);
    
    /**
     * �o�b�N�A�b�v�t�@�C���̃p�X��ݒ肷��B<p>
     * ���̃p�X���w�肵�Ȃ��ꍇ�́A���������Ƀo�b�N�A�b�v�����B<br>
     * ��������ߖ񂵂����ꍇ��A�o�b�N�A�b�v���i�����������ꍇ�ȂǂɎw�肷��B<br>
     *
     * @param path �o�b�N�A�b�v�t�@�C���̃p�X
     */
    public void setBackupFilePath(String path);
    
    /**
     * �o�b�N�A�b�v�t�@�C���̃p�X���擾����B<p>
     *
     * @return �o�b�N�A�b�v�t�@�C���̃p�X
     */
    public String getBackupFilePath();
    
    /**
     * �e�[�u�����폜����B<p>
     * {@link #setDropTableQuery(String)}��ݒ肳��Ă��Ȃ��ꍇ�́A�������Ȃ��B<br>
     *
     * @exception ConnectionFactoryException JDBC�R�l�N�V�����̎擾�Ɏ��s�����ꍇ
     * @exception SQLException SQL�̎��s�Ɏ��s�����ꍇ
     */
    public void dropTable() throws ConnectionFactoryException, SQLException;
    
    /**
     * �e�[�u���̃��R�[�h���폜����B<p>
     * {@link #setDeleteQuery(String)}��ݒ肳��Ă��Ȃ��ꍇ�́A�������Ȃ��B<br>
     *
     * @exception ConnectionFactoryException JDBC�R�l�N�V�����̎擾�Ɏ��s�����ꍇ
     * @exception SQLException SQL�̎��s�Ɏ��s�����ꍇ
     */
    public void deleteRecords() throws ConnectionFactoryException, SQLException;
    
    /**
     * �e�[�u�����쐬����B<p>
     * {@link #setCreateTableQuery(String)}��ݒ肳��Ă��Ȃ��ꍇ�́A�������Ȃ��B<br>
     *
     * @exception ConnectionFactoryException JDBC�R�l�N�V�����̎擾�Ɏ��s�����ꍇ
     * @exception SQLException SQL�̎��s�Ɏ��s�����ꍇ
     */
    public void createTable() throws ConnectionFactoryException, SQLException;
    
    /**
     * �e�[�u���̃��R�[�h��}������B<p>
     * {@link #setInsertQuery(String)}�ƁA{@link #setInsertRecords(String)}�܂���{@link #setInsertRecordsFilePath(String)}��ݒ肳��Ă��Ȃ��ꍇ�́A�������Ȃ��B<br>
     *
     * @exception ConnectionFactoryException JDBC�R�l�N�V�����̎擾�Ɏ��s�����ꍇ
     * @exception SQLException SQL�̎��s�Ɏ��s�����ꍇ
     * @exception ConvertException ���R�[�h������̕ϊ��Ɏ��s�����ꍇ
     * @exception IOException ���R�[�h�t�@�C�������݂��Ȃ��ꍇ��A�ǂݍ��݂Ɏ��s�����ꍇ
     */
    public void insertRecords()
     throws ConnectionFactoryException, SQLException,
            ConvertException, IOException;
    
    /**
     * �e�[�u���̃��R�[�h���o�b�N�A�b�v����B<p>
     * {@link #setSelectQuery(String)}�ƁA{@link #setRecordListSchema(String)}�܂���{@link #setRecordList(jp.ossc.nimbus.beans.dataset.RecordList)}��ݒ肳��Ă��Ȃ��ꍇ�́A�������Ȃ��B<br>
     * �ʏ�A�o�b�N�A�b�v�́A���������ɍs���邪�A{@link #setBackupFilePath(String)}��ݒ肵�Ă���ꍇ�́A�t�@�C���Ƀo�b�N�A�b�v�����B<br>
     * �o�b�N�A�b�v�������R�[�h�́A{@link #restoreRecords()}�ŕ������鎖���ł���B<br>
     *
     * @exception ConnectionFactoryException JDBC�R�l�N�V�����̎擾�Ɏ��s�����ꍇ
     * @exception SQLException SQL�̎��s�Ɏ��s�����ꍇ
     * @exception ConvertException ���R�[�h������̕ϊ��Ɏ��s�����ꍇ
     * @exception IOException �o�b�N�A�b�v�t�@�C���̃p�X�����݂��Ȃ��ꍇ��A�������݂Ɏ��s�����ꍇ
     */
    public void backupRecords()
     throws ConnectionFactoryException, SQLException,
            IOException, ConvertException;
    
    /**
     * �e�[�u���̃��R�[�h�𕜌�����B<p>
     * {@link #setInsertQuery(String)}�ƁA{@link #setInsertRecords(String)}�܂���{@link #setInsertRecordsFilePath(String)}��ݒ肳��Ă��Ȃ��ꍇ�́A�������Ȃ��B<br>
     * �ʏ�A�����́A������������s���邪�A{@link #setBackupFilePath(String)}��ݒ肵�Ă���ꍇ�́A�t�@�C�����畜�������B<br>
     *
     * @exception ConnectionFactoryException JDBC�R�l�N�V�����̎擾�Ɏ��s�����ꍇ
     * @exception SQLException SQL�̎��s�Ɏ��s�����ꍇ
     * @exception ConvertException ���R�[�h������̕ϊ��Ɏ��s�����ꍇ
     * @exception IOException �o�b�N�A�b�v�t�@�C�������݂��Ȃ��ꍇ��A�ǂݍ��݂Ɏ��s�����ꍇ
     */
    public void restoreRecords()
     throws ConnectionFactoryException, SQLException,
            IOException, ConvertException;
    
    /**
     * �e�[�u���̍폜�A���R�[�h�̍폜�A�e�[�u���̍쐬�A���R�[�h�̑}���������s���B<p>
     *
     * @exception ConnectionFactoryException JDBC�R�l�N�V�����̎擾�Ɏ��s�����ꍇ
     * @exception SQLException SQL�̎��s�Ɏ��s�����ꍇ
     * @exception ConvertException ���R�[�h������̕ϊ��Ɏ��s�����ꍇ
     * @exception IOException �t�@�C�������݂��Ȃ��ꍇ��A�ǂݍ��݂Ɏ��s�����ꍇ
     */
    public void executeAllQuery()
     throws ConnectionFactoryException, SQLException,
            ConvertException, IOException;
}
