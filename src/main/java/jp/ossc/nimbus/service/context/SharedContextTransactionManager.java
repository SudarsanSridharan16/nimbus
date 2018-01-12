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
package jp.ossc.nimbus.service.context;

/**
 * ���L�R���e�L�X�g�p�̃g�����U�N�V�����Ǘ��C���^�t�F�[�X�B<p>
 *
 * @author M.Takata
 */
public interface SharedContextTransactionManager{
    
    /**
     * �g�����U�N�V�����̃��b�N���[�h�F�ߊσ��b�N�B<p>
     */
    public static final int LOCK_MODE_PESSIMISTIC = 1;
    
    /**
     * �g�����U�N�V�����̃��b�N���[�h�F�y�σ��b�N�B<p>
     */
    public static final int LOCK_MODE_OPTIMISTIC  = 2;
    
    /**
     * �g�����U�N�V�������J�n����B<p>
     * ���b�N���[�h�́A�ݒ�Ɉˑ�����B<br>
     */
    public void begin();
    
    /**
     * �g�����U�N�V�������J�n����B<p>
     *
     * @param lockMode ���b�N���[�h
     * @see #LOCK_MODE_PESSIMISTIC
     * @see #LOCK_MODE_OPTIMISTIC
     */
    public void begin(int lockMode);
    
    /**
     * �g�����U�N�V�������R�~�b�g����B<p>
     * 
     * @exception SharedContextTransactionException �g�����U�N�V�����̏����Ɏ��s�����ꍇ
     */
    public void commit() throws SharedContextTransactionException;
    
    /**
     * �g�����U�N�V���������[���o�b�N����B<p>
     * 
     * @exception SharedContextTransactionException �g�����U�N�V�����̏����Ɏ��s�����ꍇ
     */
    public void rollback() throws SharedContextTransactionException;
    
    /**
     * �g�����U�N�V�����̃^�C���A�E�g[ms]��ݒ肷��B<p>
     * �w�肵�Ȃ��ꍇ�́A�^�C���A�E�g���Ȃ��B<br>
     *
     * @param timeout �^�C���A�E�g[ms]
     */
    public void setTransactionTimeout(long timeout);
    
    /**
     * �g�����U�N�V�����̃^�C���A�E�g[ms]���擾����B<p>
     *
     * @return �^�C���A�E�g[ms]
     */
    public long getTransactionTimeout();
    
    /**
     * ���݂̃X���b�h���J�n���Ă���g�����U�N�V�������擾����B<p>
     *
     * @return �g�����U�N�V�����B�g�����U�N�V�������J�n����Ă��Ȃ��ꍇ�́Anull�B
     */
    public SharedContextTransaction getTransaction();
    
    /**
     * ���L�R���e�L�X�g�̃g�����U�N�V�����B<p>
     * 
     * @author M.Takata
     */
    public interface SharedContextTransaction{
        
        /**
         * �g�����U�N�V������ԁF�J�n�O�B<p>
         */
        public static final int STATE_BEFORE_BEGIN = 0;
        
        /**
         * �g�����U�N�V������ԁF�J�n�B<p>
         */
        public static final int STATE_BEGIN      = 1;
        
        /**
         * �g�����U�N�V������ԁF�R�~�b�g�B<p>
         */
        public static final int STATE_COMMIT     = 2;
        
        /**
         * �g�����U�N�V������ԁF���[���o�b�N�B<p>
         */
        public static final int STATE_ROLLBACK   = 3;
        
        /**
         * �g�����U�N�V������ԁF�R�~�b�g�����B<p>
         */
        public static final int STATE_COMMITTED  = 5;
        
        /**
         * �g�����U�N�V������ԁF���[���o�b�N�����B<p>
         */
        public static final int STATE_ROLLBACKED = 6;
        
        /**
         * �g�����U�N�V������ԁF���[���o�b�N���s�B<p>
         */
        public static final int STATE_ROLLBACK_FAILED = 7;
        
        /**
         * �g�����U�N�V�����̏�Ԃ��擾����B<p>
         *
         * @return �g�����U�N�V�����̏��
         */
        public int getState();
        
        /**
         * �g�����U�N�V�������R�~�b�g����B<p>
         * 
         * @exception SharedContextTransactionException �g�����U�N�V�����̏����Ɏ��s�����ꍇ
         */
        public void commit() throws SharedContextTransactionException;
        
        /**
         * �g�����U�N�V���������[���o�b�N����B<p>
         * 
         * @exception SharedContextTransactionException �g�����U�N�V�����̏����Ɏ��s�����ꍇ
         */
        public void rollback() throws SharedContextTransactionException;
        
        /**
         * �g�����U�N�V�����Ɏw�肳�ꂽ�L�[���o�^����Ă��邩�𔻒肷��B<p>
         *
         * @param context ���L�R���e�L�X�g
         * @param key �L�[
         * @return �o�^����Ă���ꍇtrue
         */
        public boolean containsKey(SharedContext context, Object key);
        
        /**
         * �g�����U�N�V�����ɓo�^���ꂽ�L�[�̒l���擾����B<p>
         *
         * @param context ���L�R���e�L�X�g
         * @param key �L�[
         * @param timeout �^�C���A�E�g[ms]
         * @return �l
         * @exception SharedContextSendException ���U�T�[�o�ւ̃��b�Z�[�W���M�Ɏ��s�����ꍇ
         * @exception SharedContextTimeoutException ���U�T�[�o����̉����҂��Ń^�C���A�E�g�����ꍇ
         */
        public Object get(SharedContext context, Object key, long timeout) throws SharedContextSendException, SharedContextTimeoutException;
        
        /**
         * �w�肵���L�[�ŁA�w�肵���l��ǉ�����悤�Ƀg�����U�N�V�������X�V����B<p>
         *
         * @param context ���L�R���e�L�X�g
         * @param key �L�[
         * @param value �l
         * @param timeout �^�C���A�E�g[ms]
         * @return �Â��l
         * @exception SharedContextSendException ���U�T�[�o�ւ̃��b�Z�[�W���M�Ɏ��s�����ꍇ
         * @exception SharedContextTimeoutException ���U�T�[�o����̉����҂��Ń^�C���A�E�g�����ꍇ
         */
        public Object put(SharedContext context, Object key, Object value, long timeout) throws SharedContextSendException, SharedContextTimeoutException;
        
        /**
         * �w�肵���L�[�ŁA�w�肵���������X�V����悤�Ƀg�����U�N�V�������X�V����B<p>
         *
         * @param context ���L�R���e�L�X�g
         * @param key �L�[
         * @param diff ����
         * @param timeout �^�C���A�E�g[ms]
         * @exception SharedContextSendException ���U�T�[�o�ւ̃��b�Z�[�W���M�Ɏ��s�����ꍇ
         * @exception SharedContextTimeoutException ���U�T�[�o����̉����҂��Ń^�C���A�E�g�����ꍇ
         */
        public void update(SharedContext context, Object key, SharedContextValueDifference diff, long timeout) throws SharedContextSendException, SharedContextTimeoutException;
        
        /**
         * �w�肵���L�[�����݂���΁A�w�肵���������X�V����悤�Ƀg�����U�N�V�������X�V����B<p>
         *
         * @param context ���L�R���e�L�X�g
         * @param key �L�[
         * @param diff ����
         * @param timeout �^�C���A�E�g[ms]
         * @exception SharedContextSendException ���U�T�[�o�ւ̃��b�Z�[�W���M�Ɏ��s�����ꍇ
         * @exception SharedContextTimeoutException ���U�T�[�o����̉����҂��Ń^�C���A�E�g�����ꍇ
         */
        public void updateIfExists(SharedContext context, Object key, SharedContextValueDifference diff, long timeout) throws SharedContextSendException, SharedContextTimeoutException;
        
        /**
         * �w�肵���L�[���폜����悤�Ƀg�����U�N�V�������X�V����B<p>
         *
         * @param context ���L�R���e�L�X�g
         * @param key �L�[
         * @param timeout �^�C���A�E�g[ms]
         * @return �폜�����l
         * @exception SharedContextSendException ���U�T�[�o�ւ̃��b�Z�[�W���M�Ɏ��s�����ꍇ
         * @exception SharedContextTimeoutException ���U�T�[�o����̉����҂��Ń^�C���A�E�g�����ꍇ
         */
        public Object remove(SharedContext context, Object key, long timeout) throws SharedContextSendException, SharedContextTimeoutException;
    }
}