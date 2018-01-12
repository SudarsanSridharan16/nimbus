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

import java.sql.Connection;
import java.util.Map;

/**
 * �i���Ǘ��B<p>
 *
 * @author M.Takata
 */
public interface PersistentManager{
    
    /**
     * �f�[�^�x�[�X����ǂݍ��ށB<p>
     * �w�肳�ꂽquery�Ɏw�肳�ꂽinput�̏��𖄂ߍ��ݎ��s���āA���s���ʂ�ResultSet���w�肳�ꂽoutput�ɋl�߂ĕԂ��B<br>
     * �p�����[�^query�́A���ߍ��݃N�G���ŁASQL��input���ǂ��n����output�ɂǂ��l�߂ĕԂ������w�肷��B�p�����[�^input�̏��𖄂ߍ��ޏꍇ�́A"<-{�v���p�e�B��}"�Ŗ��ߍ��ށB�܂��A���s���ʂ�ResultSet����A�o��Bean�ɋl�߂�ɂ́A"->{�v���p�e�B��}"�Ŗ��ߍ��ށB<br>
     * <pre>
     *   ��Fselect USER.NAME->{Header(user).name}, MAIL.ADDRESS->{RecordList(mail).address}, from USER, MAIL where USER.ID = ?<-{Id} and USER.ID = MAIL.ID
     * </pre>
     *
     * @param con �R�l�N�V����
     * @param query ���ߍ��݃N�G��
     * @param input ����Bean
     * @param output �o��Bean
     * @return �f�[�^�x�[�X����ǂݍ��񂾏o��Bean
     * @exception PersistentException �ǂݍ��݂Ɏ��s�����ꍇ
     */
    public Object loadQuery(Connection con, String query, Object input, Object output) throws PersistentException;
    
    /**
     * �f�[�^�x�[�X����ǂݍ��ށB<p>
     * �w�肳�ꂽquery�Ɏw�肳�ꂽinput�̏��𖄂ߍ��ݎ��s���āA���s���ʂ�ResultSet���w�肳�ꂽoutput�ɋl�߂ĕԂ��B<br>
     * �p�����[�^query�́A���ߍ��݃N�G���ŁASQL��input���ǂ��n����output�ɂǂ��l�߂ĕԂ������w�肷��B�p�����[�^input�̏��𖄂ߍ��ޏꍇ�́A"<-{�v���p�e�B��}"�Ŗ��ߍ��ށB�܂��A���s���ʂ�ResultSet����A�o��Bean�ɋl�߂�ɂ́A"->{�v���p�e�B��}"�Ŗ��ߍ��ށB<br>
     * <pre>
     *   ��Fselect USER.NAME->{Header(user).name}, MAIL.ADDRESS->{RecordList(mail).address}, from USER, MAIL where USER.ID = ?<-{Id} and USER.ID = MAIL.ID
     * </pre>
     *
     * @param con �R�l�N�V����
     * @param query ���ߍ��݃N�G��
     * @param input ����Bean
     * @param output �o��Bean
     * @param statementProps java.sql.Statement�ɑ΂���v���p�e�B�̃}�b�v
     * @param resultSetProps java.sql.ResultSet�ɑ΂���v���p�e�B�̃}�b�v
     * @return �f�[�^�x�[�X����ǂݍ��񂾏o��Bean
     * @exception PersistentException �ǂݍ��݂Ɏ��s�����ꍇ
     */
    public Object loadQuery(Connection con, String query, Object input, Object output, Map statementProps, Map resultSetProps) throws PersistentException;
    
    /**
     * �f�[�^�x�[�X����ǂݍ��ށB<p>
     *
     * @param con �R�l�N�V����
     * @param sql ���ߍ���SQL
     * @param input ����Bean
     * @param inputProps ����Bean�Ɠ��̓p�����[�^�̃}�b�s���O�B�v���p�e�B�������String�AString[]�A�܂���List<String>
     * @param output �o��Bean
     * @param outputProps �o��Bean�Ɨ񖼂̃}�b�s���O�B�v���p�e�B�������String�AString[]�A�܂���List<String>�A�܂��̓L�[���񖼂Œl���v���p�e�B������ƂȂ�Map<String, String>
     * @return �f�[�^�x�[�X����ǂݍ��񂾏o��Bean
     * @exception PersistentException �ǂݍ��݂Ɏ��s�����ꍇ
     */
    public Object load(Connection con, String sql, Object input, Object inputProps, Object output, Object outputProps) throws PersistentException;
    
    /**
     * �f�[�^�x�[�X����ǂݍ��ށB<p>
     *
     * @param con �R�l�N�V����
     * @param sql ���ߍ���SQL
     * @param input ����Bean
     * @param inputProps ����Bean�Ɠ��̓p�����[�^�̃}�b�s���O�B�v���p�e�B�������String�AString[]�A�܂���List<String>
     * @param output �o��Bean
     * @param outputProps �o��Bean�Ɨ񖼂̃}�b�s���O�B�v���p�e�B�������String�AString[]�A�܂���List<String>�A�܂��̓L�[���񖼂Œl���v���p�e�B������ƂȂ�Map<String, String>
     * @param statementProps java.sql.Statement�ɑ΂���v���p�e�B�̃}�b�v
     * @param resultSetProps java.sql.ResultSet�ɑ΂���v���p�e�B�̃}�b�v
     * @return �f�[�^�x�[�X����ǂݍ��񂾏o��Bean
     * @exception PersistentException �ǂݍ��݂Ɏ��s�����ꍇ
     */
    public Object load(Connection con, String sql, Object input, Object inputProps, Object output, Object outputProps, Map statementProps, Map resultSetProps) throws PersistentException;
    
    /**
     * �f�[�^�x�[�X����ǂݍ��ރJ�[�\���𐶐�����B<p>
     *
     * @param con �R�l�N�V����
     * @param query ���ߍ��݃N�G��
     * @param input ����Bean
     * @return �J�[�\��
     * @exception PersistentException �J�[�\���̍쐬�Ɏ��s�����ꍇ
     * @see #loadQuery(Connection, String, Object, Object)
     */
    public Cursor createQueryCursor(Connection con, String query, Object input) throws PersistentException;
    
    /**
     * �f�[�^�x�[�X����ǂݍ��ރJ�[�\���𐶐�����B<p>
     *
     * @param con �R�l�N�V����
     * @param query ���ߍ��݃N�G��
     * @param input ����Bean
     * @param statementProps java.sql.Statement�ɑ΂���v���p�e�B�̃}�b�v
     * @param resultSetProps java.sql.ResultSet�ɑ΂���v���p�e�B�̃}�b�v
     * @return �J�[�\��
     * @exception PersistentException �J�[�\���̍쐬�Ɏ��s�����ꍇ
     * @see #loadQuery(Connection, String, Object, Object)
     */
    public Cursor createQueryCursor(Connection con, String query, Object input, Map statementProps, Map resultSetProps) throws PersistentException;
    
    /**
     * �f�[�^�x�[�X����ǂݍ��ރJ�[�\���𐶐�����B<p>
     *
     * @param con �R�l�N�V����
     * @param sql ���ߍ���SQL
     * @param input ����Bean
     * @param inputProps ����Bean�Ɠ��̓p�����[�^�̃}�b�s���O�B�v���p�e�B�������String�AString[]�A�܂���List<String>
     * @param outputProps �o��Bean�Ɨ񖼂̃}�b�s���O�B�v���p�e�B�������String�AString[]�A�܂���List<String>�A�܂��̓L�[���񖼂Œl���v���p�e�B������ƂȂ�Map<String, String>
     * @return �J�[�\��
     * @exception PersistentException �J�[�\���̍쐬�Ɏ��s�����ꍇ
     * @see #load(Connection, String, Object, Object, Object, Object)
     */
    public Cursor createCursor(Connection con, String sql, Object input, Object inputProps, Object outputProps) throws PersistentException;
    
    /**
     * �f�[�^�x�[�X����ǂݍ��ރJ�[�\���𐶐�����B<p>
     *
     * @param con �R�l�N�V����
     * @param sql ���ߍ���SQL
     * @param input ����Bean
     * @param inputProps ����Bean�Ɠ��̓p�����[�^�̃}�b�s���O�B�v���p�e�B�������String�AString[]�A�܂���List<String>
     * @param outputProps �o��Bean�Ɨ񖼂̃}�b�s���O�B�v���p�e�B�������String�AString[]�A�܂���List<String>�A�܂��̓L�[���񖼂Œl���v���p�e�B������ƂȂ�Map<String, String>
     * @param statementProps java.sql.Statement�ɑ΂���v���p�e�B�̃}�b�v
     * @param resultSetProps java.sql.ResultSet�ɑ΂���v���p�e�B�̃}�b�v
     * @return �J�[�\��
     * @exception PersistentException �J�[�\���̍쐬�Ɏ��s�����ꍇ
     * @see #load(Connection, String, Object, Object, Object, Object)
     */
    public Cursor createCursor(Connection con, String sql, Object input, Object inputProps, Object outputProps, Map statementProps, Map resultSetProps) throws PersistentException;
    
    /**
     * �f�[�^�x�[�X�ɏ������ށB<p>
     * �w�肳�ꂽsql�Ɏw�肳�ꂽinput�̏��𖄂ߍ��ݎ��s���āA�X�V������Ԃ��B<br>
     * �p�����[�^query�́A���ߍ��݃N�G���ŁASQL��input���ǂ��n�������A"<-{�v���p�e�B��}"�Ŗ��ߍ��ށB<br>
     * <pre>
     *   ��Fupdate MAIL set ADDRESS = ?<-{RecordList(mail).address} where USER.ID = ?<-{Header(user).Id}
     * </pre>
     *
     * @param con �R�l�N�V����
     * @param query ���ߍ��݃N�G��
     * @param input ����Bean
     * @return �X�V����
     * @exception PersistentException �������݂Ɏ��s�����ꍇ
     */
    public int persistQuery(Connection con, String query, Object input) throws PersistentException;
    
    /**
     * �f�[�^�x�[�X�ɏ������ށB<p>
     * �w�肳�ꂽsql�Ɏw�肳�ꂽinput�̏��𖄂ߍ��ݎ��s���āA�X�V������Ԃ��B<br>
     * �p�����[�^query�́A���ߍ��݃N�G���ŁASQL��input���ǂ��n�������A"<-{�v���p�e�B��}"�Ŗ��ߍ��ށB<br>
     * <pre>
     *   ��Fupdate MAIL set ADDRESS = ?<-{RecordList(mail).address} where USER.ID = ?<-{Header(user).Id}
     * </pre>
     *
     * @param con �R�l�N�V����
     * @param query ���ߍ��݃N�G��
     * @param input ����Bean
     * @param statementProps java.sql.Statement�ɑ΂���v���p�e�B�̃}�b�v
     * @return �X�V����
     * @exception PersistentException �������݂Ɏ��s�����ꍇ
     */
    public int persistQuery(Connection con, String query, Object input, Map statementProps) throws PersistentException;
    
    /**
     * �f�[�^�x�[�X�ɏ������ށB<p>
     *
     * @param con �R�l�N�V����
     * @param sql ���ߍ���SQL
     * @param input ���̓p�����[�^�̔z��
     * @param inputProps ����Bean�Ɠ��̓p�����[�^�̃}�b�s���O�B�v���p�e�B�������String�AString[]�A�܂���List<String>
     * @return �X�V����
     * @exception PersistentException �������݂Ɏ��s�����ꍇ
     */
    public int persist(Connection con, String sql, Object input, Object inputProps) throws PersistentException;
    
    /**
     * �f�[�^�x�[�X�ɏ������ށB<p>
     *
     * @param con �R�l�N�V����
     * @param sql ���ߍ���SQL
     * @param input ���̓p�����[�^�̔z��
     * @param inputProps ����Bean�Ɠ��̓p�����[�^�̃}�b�s���O�B�v���p�e�B�������String�AString[]�A�܂���List<String>
     * @param statementProps java.sql.Statement�ɑ΂���v���p�e�B�̃}�b�v
     * @return �X�V����
     * @exception PersistentException �������݂Ɏ��s�����ꍇ
     */
    public int persist(Connection con, String sql, Object input, Object inputProps, Map statementProps) throws PersistentException;
    
    /**
     * �f�[�^�x�[�X�Ƀo�b�`���s���s��BatchExecutor�𐶐�����B<p>
     *
     * @param con �R�l�N�V����
     * @param query ���ߍ��݃N�G��
     * @return BatchExecutor
     * @exception PersistentException BatchExecutor�̐����Ɏ��s�����ꍇ
     */
    public BatchExecutor createQueryBatchExecutor(Connection con, String query) throws PersistentException;
    
    /**
     * �f�[�^�x�[�X�Ƀo�b�`���s���s��BatchExecutor�𐶐�����B<p>
     *
     * @param con �R�l�N�V����
     * @param query ���ߍ��݃N�G��
     * @param statementProps java.sql.Statement�ɑ΂���v���p�e�B�̃}�b�v
     * @return BatchExecutor
     * @exception PersistentException BatchExecutor�̐����Ɏ��s�����ꍇ
     */
    public BatchExecutor createQueryBatchExecutor(Connection con, String query, Map statementProps) throws PersistentException;
    
    /**
     * �f�[�^�x�[�X�Ƀo�b�`���s���s��BatchExecutor�𐶐�����B<p>
     *
     * @param con �R�l�N�V����
     * @param sql ���ߍ���SQL
     * @param inputProps ����Bean�Ɠ��̓p�����[�^�̃}�b�s���O�B�v���p�e�B�������String�AString[]�A�܂���List<String>
     * @return BatchExecutor
     * @exception PersistentException BatchExecutor�̐����Ɏ��s�����ꍇ
     */
    public BatchExecutor createBatchExecutor(Connection con, String sql, Object inputProps) throws PersistentException;
    
    /**
     * �f�[�^�x�[�X�Ƀo�b�`���s���s��BatchExecutor�𐶐�����B<p>
     *
     * @param con �R�l�N�V����
     * @param sql ���ߍ���SQL
     * @param statementProps java.sql.Statement�ɑ΂���v���p�e�B�̃}�b�v
     * @param inputProps ����Bean�Ɠ��̓p�����[�^�̃}�b�s���O�B�v���p�e�B�������String�AString[]�A�܂���List<String>
     * @return BatchExecutor
     * @exception PersistentException BatchExecutor�̐����Ɏ��s�����ꍇ
     */
    public BatchExecutor createBatchExecutor(Connection con, String sql, Object inputProps, Map statementProps) throws PersistentException;
    
    /**
     * �ǂݍ��݃J�[�\���B<p>
     *
     * @author M.Takata
     */
    public interface Cursor{
        
        /**
         * ���̍s�Ɉړ�����B<p>
         *
         * @return ���̍s�����݂����ꍇ�́Atrue
         * @exception PersistentException �ړ��Ɏ��s�����ꍇ
         */
        public boolean next() throws PersistentException;
        
        /**
         * �O�̍s�Ɉړ�����B<p>
         *
         * @return �O�̍s�����݂����ꍇ�́Atrue
         * @exception PersistentException �ړ��Ɏ��s�����ꍇ
         */
        public boolean previous() throws PersistentException;
        
        /**
         * �擪�̍s�Ɉړ�����B<p>
         *
         * @return �擪�̍s�����݂����ꍇ�́Atrue
         * @exception PersistentException �ړ��Ɏ��s�����ꍇ
         */
        public boolean first() throws PersistentException;
        
        /**
         * �Ō�̍s�Ɉړ�����B<p>
         *
         * @return �Ō�̍s�����݂����ꍇ�́Atrue
         * @exception PersistentException �ړ��Ɏ��s�����ꍇ
         */
        public boolean last() throws PersistentException;
        
        /**
         * �擪�̍s�̑O�Ɉړ�����B<p>
         *
         * @exception PersistentException �ړ��Ɏ��s�����ꍇ
         */
        public void beforeFirst() throws PersistentException;
        
        /**
         * �Ō�̍s�̌�Ɉړ�����B<p>
         *
         * @exception PersistentException �ړ��Ɏ��s�����ꍇ
         */
        public void afterLast() throws PersistentException;
        
        /**
         * �w�肳�ꂽ�s�Ɉړ�����B<p>
         *
         * @param row �s�ԍ�
         * @exception PersistentException �ړ��Ɏ��s�����ꍇ
         */
        public boolean absolute(int row) throws PersistentException;
        
        /**
         * �w�肳�ꂽ�s�������ړ�����B<p>
         *
         * @param rows �s��
         * @exception PersistentException �ړ��Ɏ��s�����ꍇ
         */
        public boolean relative(int rows) throws PersistentException;
        
        /**
         * ���݂̍s���擪�����肷��B<p>
         *
         * @return �擪�̏ꍇ�Atrue
         * @exception PersistentException ����Ɏ��s�����ꍇ
         */
        public boolean isFirst() throws PersistentException;
        
        /**
         * ���݂̍s���Ōォ���肷��B<p>
         *
         * @return �Ō�̏ꍇ�Atrue
         * @exception PersistentException ����Ɏ��s�����ꍇ
         */
        public boolean isLast() throws PersistentException;
        
        /**
         * ���݂̍s���擪�̑O�����肷��B<p>
         *
         * @return �擪�̑O�̏ꍇ�Atrue
         * @exception PersistentException ����Ɏ��s�����ꍇ
         */
        public boolean isBeforeFirst() throws PersistentException;
        
        /**
         * ���݂̍s���Ō�̌ォ���肷��B<p>
         *
         * @return �Ō�̌�̏ꍇ�Atrue
         * @exception PersistentException ����Ɏ��s�����ꍇ
         */
        public boolean isAfterLast() throws PersistentException;
        
        /**
         * �t�F�b�`���������ݒ肷��B<p>
         *
         * @param direction �t�F�b�`�������
         * @exception PersistentException �ݒ�Ɏ��s�����ꍇ
         * @see java.sql.ResultSet#FETCH_FORWARD
         * @see java.sql.ResultSet#FETCH_REVERSE
         * @see java.sql.ResultSet#FETCH_UNKNOWN
         */
        public void setFetchDirection(int direction) throws PersistentException;
        
        /**
         * �t�F�b�`����������擾����B<p>
         *
         * @return �t�F�b�`�������
         * @exception PersistentException �擾�Ɏ��s�����ꍇ
         * @see java.sql.ResultSet#FETCH_FORWARD
         * @see java.sql.ResultSet#FETCH_REVERSE
         * @see java.sql.ResultSet#FETCH_UNKNOWN
         */
        public int getFetchDirection() throws PersistentException;
        
        /**
         * �t�F�b�`����s����ݒ肷��B<p>
         *
         * @param rows �t�F�b�`����s��
         * @exception PersistentException �ݒ�Ɏ��s�����ꍇ
         */
        public void setFetchSize(int rows) throws PersistentException;
        
        /**
         * �t�F�b�`����s�����擾����B<p>
         *
         * @return �t�F�b�`����s��
         * @exception PersistentException �擾�Ɏ��s�����ꍇ
         */
        public int getFetchSize() throws PersistentException;
        
        /**
         * ���݂̍s�ԍ����擾����B<p>
         *
         * @return ���݂̍s�ԍ�
         * @exception PersistentException �擾�Ɏ��s�����ꍇ
         */
        public int getRow() throws PersistentException;
        
        /**
         * �f�[�^�x�[�X����ǂݍ��ށB<p>
         *
         * @param output �o��Bean
         * @return �f�[�^�x�[�X����ǂݍ��񂾏o��Bean
         * @exception PersistentException �ǂݍ��݂Ɏ��s�����ꍇ
         */
        public Object load(Object output) throws PersistentException;
        
        /**
         * ���\�[�X���J���������ǂ������肷��B<p>
         * 
         * @return ���\�[�X���J�����Ă����ꍇtrue
         */
        public boolean isClosed();
        
        /**
         * ���\�[�X���J������B<p>
         */
        public void close();
    }
    
    /**
     * �o�b�`���s�B<p>
     *
     * @author M.Takata
     */
    public interface BatchExecutor{
        
        /**
         * �����o�b�`���s�̌�����ݒ肷��B<p>
         * �o�b�`���s���ɁA�w�茏���̃o�b�`�o�^�����܂�Ǝ����I�Ƀo�b�`���s���s���B<br>
         *
         * @param count �����o�b�`���s����
         */
        public void setAutoBatchPersistCount(int count);
        
        /**
         * �����o�b�`���s�̌������擾����B<p>
         *
         * @return �����o�b�`���s����
         */
        public int getAutoBatchPersistCount();
        
        /**
         * �o�b�`���s���ɁA�����I�ɃR�~�b�g���s�����ǂ�����ݒ肷��B<p>
         * �f�t�H���g��false�ŁA�����R�~�b�g���s��Ȃ��B<br>
         *
         * @param isCommit �����I�ɃR�~�b�g���s���ꍇ��true
         */
        public void setAutoCommitOnPersist(boolean isCommit);
        
        /**
         * �o�b�`���s���ɁA�����I�ɃR�~�b�g���s�����ǂ����𔻒肷��B<p>
         *
         * @return true�̏ꍇ�A�����I�ɃR�~�b�g���s��
         */
        public boolean isAutoCommitOnPersist();
        
        /**
         * �o�b�`�o�^���s���B<p>
         *
         * @param input ����Bean
         * @return �����o�b�`���s�̏ꍇ�ŁA�o�b�`���s���s��ꂽ���̍X�V����
         * @exception PersistentException �o�b�`�o�^�Ɏ��s�����ꍇ
         */
        public int addBatch(Object input) throws PersistentException;
        
        /**
         * �f�[�^�x�[�X�Ƀo�b�`���s�ŏ������ށB<p>
         *
         * @return �X�V����
         * @exception PersistentException �o�b�`���s�Ɏ��s�����ꍇ
         */
        public int persist() throws PersistentException;
        
        /**
         * �o�b�`�o�^���N���A����B<p>
         * 
         * @exception PersistentException �o�b�`�o�^�̃N���A�Ɏ��s�����ꍇ
         */
        public void clearBatch() throws PersistentException;
        
        /**
         * ���\�[�X���J������B<p>
         */
        public void close();
    }
}