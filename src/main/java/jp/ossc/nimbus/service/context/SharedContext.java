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

import java.util.Map;
import java.util.Set;
import java.util.List;
import java.util.Collection;

import jp.ossc.nimbus.beans.BeanTableIndexKeyFactory;
import jp.ossc.nimbus.service.interpreter.EvaluateException;

/**
 * ���L�R���e�L�X�g�B<p>
 *
 * @author M.Takata
 */
public interface SharedContext extends Context{
    
    /**
     * �w�肳�ꂽ�L�[�̃��b�N���l������B<p>
     *
     * @param key �L�[
     * @exception SharedContextSendException ���U�T�[�o�ւ̃��b�Z�[�W���M�Ɏ��s�����ꍇ
     * @exception SharedContextTimeoutException ���U�T�[�o����̉����҂��Ń^�C���A�E�g�����ꍇ
     */
    public void lock(Object key) throws SharedContextSendException, SharedContextTimeoutException;
    
    /**
     * �w�肳�ꂽ�L�[�̃��b�N���l������B<p>
     *
     * @param key �L�[
     * @param timeout �^�C���A�E�g[ms]
     * @exception SharedContextSendException ���U�T�[�o�ւ̃��b�Z�[�W���M�Ɏ��s�����ꍇ
     * @exception SharedContextTimeoutException ���U�T�[�o����̉����҂��Ń^�C���A�E�g�����ꍇ
     */
    public void lock(Object key, long timeout) throws SharedContextSendException, SharedContextTimeoutException;
    
    /**
     * �w�肳�ꂽ�L�[�̃��b�N���l������B<p>
     *
     * @param key �L�[
     * @param ifAcquireable ���b�N��ҋ@�����Ɋl���\�ȏꍇ�̂݊l�����鎞�́Atrue�B�ҋ@���Ăł��l�����鎞�́Afalse
     * @param ifExist �w�肳�ꂽ�L�[�����݂���ꍇ�̂݃��b�N���l������ꍇ�Atrue�B�L�[�����݂��Ȃ��Ă����b�N���擾����ꍇ�́Afalse
     * @param timeout �^�C���A�E�g[ms]
     * @return ifAcquireable��true�ŁA���b�N���l���ł��Ȃ������ꍇ�́Afalse
     * @exception SharedContextSendException ���U�T�[�o�ւ̃��b�Z�[�W���M�Ɏ��s�����ꍇ
     * @exception SharedContextTimeoutException ���U�T�[�o����̉����҂��Ń^�C���A�E�g�����ꍇ
     */
    public boolean lock(Object key, boolean ifAcquireable, boolean ifExist, long timeout) throws SharedContextSendException, SharedContextTimeoutException;
    
    /**
     * �w�肳�ꂽ�L�[�̃��b�N���J������B<p>
     *
     * @param key �L�[
     * @return ���b�N�J���ł����ꍇ�́Atrue
     * @exception SharedContextSendException ���U�T�[�o�ւ̃��b�Z�[�W���M�Ɏ��s�����ꍇ
     */
    public boolean unlock(Object key) throws SharedContextSendException;
    
    /**
     * �w�肳�ꂽ�L�[�̃��b�N���J������B<p>
     *
     * @param key �L�[
     * @param force �����t���O
     * @return ���b�N�J���ł����ꍇ�́Atrue
     * @exception SharedContextSendException ���U�T�[�o�ւ̃��b�Z�[�W���M�Ɏ��s�����ꍇ
     */
    public boolean unlock(Object key, boolean force) throws SharedContextSendException;
    
    /**
     * �w�肳�ꂽ�L�[�̃��b�N��ۗL���Ă���m�[�h��ID���擾����B<p>
     *
     * @param key �L�[
     * @return ���b�N��ۗL���Ă���m�[�h��ID
     */
    public Object getLockOwner(Object key);
    
    /**
     * �w�肳�ꂽ�L�[�̃��b�N��҂��Ă鐔���擾����B<p>
     *
     * @param key �L�[
     * @return ���b�N��҂��Ă鐔
     */
    public int getLockWaitCount(Object key);
    
    /**
     * �w�肵���L�[�ŁA�w�肵���l��ǉ�����B<p>
     *
     * @param key �L�[
     * @param value �l
     * @param timeout �^�C���A�E�g[ms]
     * @return �Â��l
     * @exception SharedContextSendException ���U�T�[�o�ւ̃��b�Z�[�W���M�Ɏ��s�����ꍇ
     * @exception SharedContextTimeoutException ���U�T�[�o����̉����҂��Ń^�C���A�E�g�����ꍇ
     */
    public Object put(Object key, Object value, long timeout) throws SharedContextSendException, SharedContextTimeoutException;
    
    /**
     * �w�肵���L�[�ŁA�w�肵���l�����[�J���ɒǉ�����B<p>
     *
     * @param key �L�[
     * @param value �l
     * @return �Â��l
     */
    public Object putLocal(Object key, Object value);
    
    /**
     * �w�肵���L�[�ŁA�w�肵���l��񓯊��ɒǉ�����B<p>
     *
     * @param key �L�[
     * @param value �l
     * @exception SharedContextSendException ���U�T�[�o�ւ̃��b�Z�[�W���M�Ɏ��s�����ꍇ
     */
    public void putAsynch(Object key, Object value) throws SharedContextSendException;
    
    /**
     * �w�肵���L�[�ŁA�w�肵���������X�V����B<p>
     *
     * @param key �L�[
     * @param diff ����
     * @exception SharedContextSendException ���U�T�[�o�ւ̃��b�Z�[�W���M�Ɏ��s�����ꍇ
     */
    public void update(Object key, SharedContextValueDifference diff) throws SharedContextSendException;
    
    /**
     * �w�肵���L�[�ŁA�w�肵���������X�V����B<p>
     *
     * @param key �L�[
     * @param diff ����
     * @param timeout �^�C���A�E�g[ms]
     * @exception SharedContextSendException ���U�T�[�o�ւ̃��b�Z�[�W���M�Ɏ��s�����ꍇ
     * @exception SharedContextTimeoutException ���U�T�[�o����̉����҂��Ń^�C���A�E�g�����ꍇ
     */
    public void update(Object key, SharedContextValueDifference diff, long timeout) throws SharedContextSendException, SharedContextTimeoutException;
    
    /**
     * �w�肵���L�[�ŁA�w�肵�����������[�J���ɍX�V����B<p>
     *
     * @param key �L�[
     * @param diff ����
     * @exception SharedContextUpdateException �X�V�Ɏ��s�����ꍇ
     */
    public void updateLocal(Object key, SharedContextValueDifference diff) throws SharedContextUpdateException;
    
    /**
     * �w�肵���L�[�ŁA�w�肵�������Ŕ񓯊��ɍX�V����B<p>
     *
     * @param key �L�[
     * @param diff ����
     * @exception SharedContextSendException ���U�T�[�o�ւ̃��b�Z�[�W���M�Ɏ��s�����ꍇ
     */
    public void updateAsynch(Object key, SharedContextValueDifference diff) throws SharedContextSendException;
    
    /**
     * �w�肵���L�[�����݂���΁A�w�肵���������X�V����B<p>
     *
     * @param key �L�[
     * @param diff ����
     * @exception SharedContextSendException ���U�T�[�o�ւ̃��b�Z�[�W���M�Ɏ��s�����ꍇ
     */
    public void updateIfExists(Object key, SharedContextValueDifference diff) throws SharedContextSendException;
    
    /**
     * �w�肵���L�[�����݂���΁A�w�肵���������X�V����B<p>
     *
     * @param key �L�[
     * @param diff ����
     * @param timeout �^�C���A�E�g[ms]
     * @exception SharedContextSendException ���U�T�[�o�ւ̃��b�Z�[�W���M�Ɏ��s�����ꍇ
     * @exception SharedContextTimeoutException ���U�T�[�o����̉����҂��Ń^�C���A�E�g�����ꍇ
     */
    public void updateIfExists(Object key, SharedContextValueDifference diff, long timeout) throws SharedContextSendException, SharedContextTimeoutException;
    
    /**
     * �w�肵���L�[�����݂���΁A�w�肵�����������[�J���ɍX�V����B<p>
     *
     * @param key �L�[
     * @param diff ����
     * @exception SharedContextUpdateException �X�V�Ɏ��s�����ꍇ
     */
    public void updateLocalIfExists(Object key, SharedContextValueDifference diff) throws SharedContextUpdateException;
    
    /**
     * �w�肵���L�[�����݂���΁A�w�肵�������Ŕ񓯊��ɍX�V����B<p>
     *
     * @param key �L�[
     * @param diff ����
     * @exception SharedContextSendException ���U�T�[�o�ւ̃��b�Z�[�W���M�Ɏ��s�����ꍇ
     */
    public void updateAsynchIfExists(Object key, SharedContextValueDifference diff) throws SharedContextSendException;
    
    /**
     * �w�肵���}�b�v��ǉ�����B<p>
     *
     * @param t �}�b�v
     * @param timeout �^�C���A�E�g[ms]
     * @exception SharedContextSendException ���U�T�[�o�ւ̃��b�Z�[�W���M�Ɏ��s�����ꍇ
     * @exception SharedContextTimeoutException ���U�T�[�o����̉����҂��Ń^�C���A�E�g�����ꍇ
     */
    public void putAll(Map t, long timeout) throws SharedContextSendException, SharedContextTimeoutException;
    
    /**
     * �w�肵���}�b�v�����[�J���ɒǉ�����B<p>
     *
     * @param t �}�b�v
     */
    public void putAllLocal(Map t);
    
    /**
     * �w�肵���}�b�v��񓯊��Œǉ�����B<p>
     *
     * @param t �}�b�v
     * @exception SharedContextSendException ���U�T�[�o�ւ̃��b�Z�[�W���M�Ɏ��s�����ꍇ
     */
    public void putAllAsynch(Map t) throws SharedContextSendException;
    
    /**
     * �w�肵���L�[�̒l���擾����B<p>
     *
     * @param key �L�[
     * @param timeout �^�C���A�E�g[ms]
     * @return �l
     * @exception SharedContextSendException ���U�T�[�o�ւ̃��b�Z�[�W���M�Ɏ��s�����ꍇ
     * @exception SharedContextTimeoutException ���U�T�[�o����̉����҂��Ń^�C���A�E�g�����ꍇ
     */
    public Object get(Object key, long timeout) throws SharedContextSendException, SharedContextTimeoutException;
    
    /**
     * �w�肵���L�[�̒l���擾����B<p>
     *
     * @param key �L�[
     * @param timeout �^�C���A�E�g[ms]
     * @param withTransaction true�̏ꍇ�A�g�����U�N�V�������l������
     * @return �l
     * @exception SharedContextSendException ���U�T�[�o�ւ̃��b�Z�[�W���M�Ɏ��s�����ꍇ
     * @exception SharedContextTimeoutException ���U�T�[�o����̉����҂��Ń^�C���A�E�g�����ꍇ
     */
    public Object get(Object key, long timeout, boolean withTransaction) throws SharedContextSendException, SharedContextTimeoutException;
    
    /**
     * �w�肵���L�[�̃��[�J���l���擾����B<p>
     *
     * @param key �L�[
     * @return �l
     */
    public Object getLocal(Object key);
    
    /**
     * �w�肵���L�[���폜����B<p>
     *
     * @param key �L�[
     * @param timeout �^�C���A�E�g[ms]
     * @return �폜�����l
     * @exception SharedContextSendException ���U�T�[�o�ւ̃��b�Z�[�W���M�Ɏ��s�����ꍇ
     * @exception SharedContextTimeoutException ���U�T�[�o����̉����҂��Ń^�C���A�E�g�����ꍇ
     */
    public Object remove(Object key, long timeout) throws SharedContextSendException, SharedContextTimeoutException;
    
    /**
     * �w�肵���L�[�����[�J������폜����B<p>
     *
     * @param key �L�[
     * @return �폜�����l
     */
    public Object removeLocal(Object key);
    
    /**
     * �w�肵���L�[��񓯊��ō폜����B<p>
     *
     * @param key �L�[
     * @exception SharedContextSendException ���U�T�[�o�ւ̃��b�Z�[�W���M�Ɏ��s�����ꍇ
     */
    public void removeAsynch(Object key) throws SharedContextSendException;
    
    /**
     * �S�č폜����B<p>
     *
     * @param timeout �^�C���A�E�g[ms]
     * @exception SharedContextSendException ���U�T�[�o�ւ̃��b�Z�[�W���M�Ɏ��s�����ꍇ
     * @exception SharedContextTimeoutException ���U�T�[�o����̉����҂��Ń^�C���A�E�g�����ꍇ
     */
    public void clear(long timeout) throws SharedContextSendException, SharedContextTimeoutException;
    
    /**
     * ���[�J����S�č폜����B<p>
     */
    public void clearLocal();
    
    /**
     * �S�Ĕ񓯊��ō폜����B<p>
     *
     * @exception SharedContextSendException ���U�T�[�o�ւ̃��b�Z�[�W���M�Ɏ��s�����ꍇ
     */
    public void clearAsynch() throws SharedContextSendException;
    
    /**
     * �L�[�̏W�����擾����B<p>
     *
     * @param timeout �^�C���A�E�g[ms]
     * @return �L�[�̏W��
     * @exception SharedContextSendException ���U�T�[�o�ւ̃��b�Z�[�W���M�Ɏ��s�����ꍇ
     * @exception SharedContextTimeoutException ���U�T�[�o����̉����҂��Ń^�C���A�E�g�����ꍇ
     */
    public Set keySet(long timeout) throws SharedContextSendException, SharedContextTimeoutException;
    
    /**
     * ���[�J���̃L�[�̏W�����擾����B<p>
     *
     * @return �L�[�̏W��
     */
    public Set keySetLocal();
    
    /**
     * �o�^����Ă���L�[�̌������擾����B<p>
     *
     * @param timeout �^�C���A�E�g[ms]
     * @return �L�[�̌���
     * @exception SharedContextSendException ���U�T�[�o�ւ̃��b�Z�[�W���M�Ɏ��s�����ꍇ
     * @exception SharedContextTimeoutException ���U�T�[�o����̉����҂��Ń^�C���A�E�g�����ꍇ
     */
    public int size(long timeout) throws SharedContextSendException, SharedContextTimeoutException;
    
    /**
     * ���[�J���ɓo�^����Ă���L�[�̌������擾����B<p>
     *
     * @return �L�[�̌���
     */
    public int sizeLocal();
    
    /**
     * �󂩂ǂ������肷��B<p>
     *
     * @return true�̏ꍇ�A��
     * @exception SharedContextSendException ���U�T�[�o�ւ̃��b�Z�[�W���M�Ɏ��s�����ꍇ
     */
    public boolean isEmpty() throws SharedContextSendException;
    
    /**
     * ���[�J�����󂩂ǂ������肷��B<p>
     *
     * @return true�̏ꍇ�A��
     */
    public boolean isEmptyLocal();
    
    /**
     * �w�肳�ꂽ�L�[���o�^����Ă��邩�𔻒肷��B<p>
     *
     * @param key �L�[
     * @param timeout �^�C���A�E�g[ms]
     * @return �o�^����Ă���ꍇtrue
     * @exception SharedContextSendException ���U�T�[�o�ւ̃��b�Z�[�W���M�Ɏ��s�����ꍇ
     * @exception SharedContextTimeoutException ���U�T�[�o����̉����҂��Ń^�C���A�E�g�����ꍇ
     */
    public boolean containsKey(Object key, long timeout) throws SharedContextSendException, SharedContextTimeoutException;
    
    /**
     * �w�肳�ꂽ�L�[�����[�J���ɓo�^����Ă��邩�𔻒肷��B<p>
     *
     * @param key �L�[
     * @return �o�^����Ă���ꍇtrue
     */
    public boolean containsKeyLocal(Object key);
    
    /**
     * �w�肳�ꂽ�l���o�^����Ă��邩�𔻒肷��B<p>
     *
     * @param value �l
     * @param timeout �^�C���A�E�g[ms]
     * @return �o�^����Ă���ꍇtrue
     * @exception SharedContextSendException ���U�T�[�o�ւ̃��b�Z�[�W���M�Ɏ��s�����ꍇ
     * @exception SharedContextTimeoutException ���U�T�[�o����̉����҂��Ń^�C���A�E�g�����ꍇ
     */
    public boolean containsValue(Object value, long timeout) throws SharedContextSendException, SharedContextTimeoutException;
    
    /**
     * �w�肳�ꂽ�l�����[�J���ɓo�^����Ă��邩�𔻒肷��B<p>
     *
     * @param value �l
     * @return �o�^����Ă���ꍇtrue
     */
    public boolean containsValueLocal(Object value);
    
    /**
     * ���̃R���e�L�X�g�̃��[�J���̓��e��S�Ċ܂ރ}�b�v���擾����B<p>
     *
     * @return �R���e�L�X�g�̃��[�J���̓��e��S�Ċ܂ރ}�b�v
     */
    public Map allLocal();
    
    /**
     * ���̃R���e�L�X�g�̃��[�J���̃G���g���[�W�����擾����B<p>
     *
     * @return �R���e�L�X�g�̃��[�J���̃G���g���[�W��
     */
    public Set entrySetLocal();
    
    /**
     * ���̃R���e�L�X�g�̃��[�J���̒l�̏W�����擾����B<p>
     *
     * @return �R���e�L�X�g�̃��[�J���̒l�̏W��
     */
    public Collection valuesLocal();
    
    /**
     * �R���e�L�X�g�������s���B<p>
     * �N���C�A���g���[�h�̏ꍇ�́A���[�J���̃R���e�L�X�g���N���A����B�܂��A�T�[�o���[�h�Ŏ�m�[�h�̏ꍇ�́A�S�Ẵm�[�h�ɓ������߂��o���B<br>
     *
     * @exception SharedContextSendException ���U�T�[�o�ւ̃��b�Z�[�W���M�Ɏ��s�����ꍇ
     * @exception SharedContextTimeoutException ���U�T�[�o����̉����҂��Ń^�C���A�E�g�����ꍇ
     */
    public void synchronize() throws SharedContextSendException, SharedContextTimeoutException;
    
    /**
     * �R���e�L�X�g�������s���B<p>
     * �N���C�A���g���[�h�̏ꍇ�́A���[�J���̃R���e�L�X�g���N���A����B�܂��A�T�[�o���[�h�Ŏ�m�[�h�̏ꍇ�́A�S�Ẵm�[�h�ɓ������߂��o���B<br>
     *
     * @param timeout �^�C���A�E�g
     * @exception SharedContextSendException ���U�T�[�o�ւ̃��b�Z�[�W���M�Ɏ��s�����ꍇ
     * @exception SharedContextTimeoutException ���U�T�[�o����̉����҂��Ń^�C���A�E�g�����ꍇ
     */
    public void synchronize(long timeout) throws SharedContextSendException, SharedContextTimeoutException;
    
    /**
     * �R���e�L�X�g�ǂݍ��݂��s���B<p>
     * ��m�[�h�łȂ��ꍇ�A��m�[�h�ɓǂݍ��݂��˗�����B<br>
     *
     * @param timeout ���U�T�[�o�ւ̒ʐM�^�C���A�E�g[ms]
     * @exception Exception �R���e�L�X�g�ǂݍ��݂Ɏ��s�����ꍇ
     */
    public void load(long timeout) throws Exception;
    
    /**
     * �R���e�L�X�g�̃L�[�̓ǂݍ��݂��s���B<p>
     * ��m�[�h�łȂ��ꍇ�A��m�[�h�ɓǂݍ��݂��˗�����B<br>
     *
     * @param timeout ���U�T�[�o�ւ̒ʐM�^�C���A�E�g[ms]
     * @exception Exception �R���e�L�X�g�ǂݍ��݂Ɏ��s�����ꍇ
     */
    public void loadKey(long timeout) throws Exception;
    
    /**
     * �w�肵���L�[�ɊY������l�̃R���e�L�X�g�ǂݍ��݂��s���B<p>
     * ��m�[�h�łȂ��ꍇ�A��m�[�h�ɓǂݍ��݂��˗�����B<br>
     *
     * @param key �L�[
     * @param timeout ���U�T�[�o�ւ̒ʐM�^�C���A�E�g[ms]
     * @exception Exception �R���e�L�X�g�ǂݍ��݂Ɏ��s�����ꍇ
     */
    public void load(Object key, long timeout) throws Exception;
    
    /**
     * �R���e�L�X�g�ۑ����s���B<p>
     * ��m�[�h�łȂ��ꍇ�A��m�[�h�ɕۑ����˗�����B<br>
     *
     * @param timeout ���U�T�[�o�ւ̒ʐM�^�C���A�E�g[ms]
     * @exception Exception �R���e�L�X�g�ۑ��Ɏ��s�����ꍇ
     */
    public void save(long timeout) throws Exception;
    
    /**
     * �w�肵���L�[�ɊY������l�̃R���e�L�X�g�ۑ����s���B<p>
     * ��m�[�h�łȂ��ꍇ�A��m�[�h�ɕۑ����˗�����B<br>
     *
     * @param key �L�[
     * @param timeout ���U�T�[�o�ւ̒ʐM�^�C���A�E�g[ms]
     * @exception Exception �R���e�L�X�g�ۑ��Ɏ��s�����ꍇ
     */
    public void save(Object key, long timeout) throws Exception;
    
    /**
     * �N���C�A���g/�T�[�o���[�h�𔻒肷��B<p>
     *
     * @return true�̏ꍇ�A�N���C�A���g���[�h
     */
    public boolean isClient();
    
    /**
     * ��m�[�h���ǂ����𔻒肷��B<p>
     *
     * @return ��m�[�h�̏ꍇtrue
     */
    public boolean isMain();
    
    /**
     * �m�[�hID���擾����B<p>
     *
     * @return �m�[�hID
     */
    public Object getId();
    
    /**
     * ��m�[�h�̃m�[�hID���擾����B<p>
     *
     * @return �m�[�hID
     */
    public Object getMainId();
    
    /**
     * �S�m�[�h�̃m�[�hID�̃��X�g���擾����B<p>
     *
     * @return �m�[�hID�̃��X�g
     */
    public List getMemberIdList();
    
    /**
     * �N���C�A���g�m�[�h�̃m�[�hID�̏W�����擾����B<p>
     *
     * @return �m�[�hID�̏W��
     */
    public Set getClientMemberIdSet();
    
    /**
     * �T�[�o�m�[�h�̃m�[�hID�̏W�����擾����B<p>
     *
     * @return �m�[�hID�̏W��
     */
    public Set getServerMemberIdSet();
    
    /**
     * ���L�R���e�L�X�g�X�V���X�i�[��o�^����B<p>
     *
     * @param listener ���L�R���e�L�X�g�X�V���X�i�[
     */
    public void addSharedContextUpdateListener(SharedContextUpdateListener listener);
    
    /**
     * ���L�R���e�L�X�g�X�V���X�i�[���폜����B<p>
     *
     * @param listener ���L�R���e�L�X�g�X�V���X�i�[
     */
    public void removeSharedContextUpdateListener(SharedContextUpdateListener listener);
    
    /**
     * �C���f�b�N�X��ǉ�����B<p>
     * �C���f�b�N�ɂ́A�P��̃v���p�e�B�ō\�������P���C���f�b�N�X�ƁA�����̃v���p�e�B�ō\������镡���C���f�b�N�X�����݂���B<br>
     * �����C���f�b�N�X��ǉ������ꍇ�́A�����I�ɂ��̗v�f�ƂȂ�P��v���p�e�B�̒P���C���f�b�N�X�������I�ɐ��������B<p>
     * �A���A�����������ꂽ�P��C���f�b�N�X�́A�C���f�b�N�X���������Ȃ����߁A�C���f�b�N�X���ł͎w��ł����A�v���p�e�B���Ŏw�肵�Ďg�p����B<br>
     * �C���f�b�N�X�̎�ނɂ���āA�g�p�ł��錟���@�\���قȂ�B�P���C���f�b�N�X�́A��v�����Ɣ͈͌����̗������\�����A�����C���f�b�N�X�́A��v�����̂݉\�ł���B<br>
     *
     * @param name �C���f�b�N�X��
     * @param props �C���f�b�N�X�𒣂�Bean�̃v���p�e�B���z��
     */
    public void setIndex(String name, String[] props);
    
    /**
     * �J�X�^�}�C�Y�����C���f�b�N�X��ǉ�����B<p>
     *
     * @param name �C���f�b�N�X��
     * @param keyFactory �C���f�b�N�X�̃L�[�𐶐�����t�@�N�g��
     * @see #setIndex(String, String[])
     */
    public void setIndex(String name, BeanTableIndexKeyFactory keyFactory);
    
    /**
     * �C���f�b�N�X���폜����B<p>
     *
     * @param name �C���f�b�N�X��
     */
    public void removeIndex(String name);
    
    /**
     * �C���f�b�N�X���ĉ�͂���B<p>
     *
     * @param name �C���f�b�N�X��
     * @exception SharedContextSendException ���U�T�[�o�ւ̃��b�Z�[�W���M�Ɏ��s�����ꍇ
     * @exception SharedContextTimeoutException ���U�T�[�o����̉����҂��Ń^�C���A�E�g�����ꍇ
     */
    public void analyzeIndex(String name) throws SharedContextSendException, SharedContextTimeoutException;
    
    /**
     * �C���f�b�N�X���ĉ�͂���B<p>
     *
     * @param name �C���f�b�N�X��
     * @param timeout �^�C���A�E�g
     * @exception SharedContextSendException ���U�T�[�o�ւ̃��b�Z�[�W���M�Ɏ��s�����ꍇ
     * @exception SharedContextTimeoutException ���U�T�[�o����̉����҂��Ń^�C���A�E�g�����ꍇ
     */
    public void analyzeIndex(String name, long timeout) throws SharedContextSendException, SharedContextTimeoutException;
    
    /**
     * �������s���r���[���쐬����B<p>
     * 
     * @return �����r���[
     */
    public SharedContextView createView();
    
    /**
     * �N�G�����f�[�^�m�[�h�ŃC���^�[�v���^���s����B<p>
     * �N�G���̕��@�́A{@link jp.ossc.nimbus.service.interpreter.Interpreter Interpreter}�̎����Ɉˑ�����B<br>
     * �N�G�����ł́A�R���e�L�X�g��ϐ���"context"�ŎQ�Ƃł���B<br>
     *
     * @param query �N�G��
     * @param variables �N�G�����Ŏg�p����ϐ��}�b�v
     * @return ���s����
     * @exception EvaluateException �N�G���̎��s�ŗ�O�����������ꍇ
     * @exception SharedContextSendException ���U�T�[�o�ւ̃��b�Z�[�W���M�Ɏ��s�����ꍇ
     * @exception SharedContextTimeoutException ���U�T�[�o����̉����҂��Ń^�C���A�E�g�����ꍇ
     */
    public Object executeInterpretQuery(String query, Map variables) throws EvaluateException, SharedContextSendException, SharedContextTimeoutException;
    
    /**
     * �N�G�����f�[�^�m�[�h�ŃC���^�[�v���^���s����B<p>
     *
     * @param query �N�G��
     * @param variables �N�G�����Ŏg�p����ϐ��}�b�v
     * @param timeout �^�C���A�E�g
     * @return ���s����
     * @exception EvaluateException �N�G���̎��s�ŗ�O�����������ꍇ
     * @exception SharedContextSendException ���U�T�[�o�ւ̃��b�Z�[�W���M�Ɏ��s�����ꍇ
     * @exception SharedContextTimeoutException ���U�T�[�o����̉����҂��Ń^�C���A�E�g�����ꍇ
     */
    public Object executeInterpretQuery(String query, Map variables, long timeout) throws EvaluateException, SharedContextSendException, SharedContextTimeoutException;
}