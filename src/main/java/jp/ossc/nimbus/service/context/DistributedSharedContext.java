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

import java.util.Set;
import java.util.Map;

import jp.ossc.nimbus.service.interpreter.EvaluateException;

/**
 * ���U���L�R���e�L�X�g�B<p>
 *
 * @author M.Takata
 */
public interface DistributedSharedContext extends SharedContext{
    
    /**
     * ���n�b�V�����L�����ǂ�����ݒ肷��B<p>
     * �f�t�H���g�́Atrue�ŗL���B<br>
     *
     * @param isEnabled �L���ɂ���ꍇ�Atrue
     * @exception SharedContextSendException ���U�T�[�o�ւ̃��b�Z�[�W���M�Ɏ��s�����ꍇ
     * @exception SharedContextTimeoutException ���U�T�[�o����̉����҂��Ń^�C���A�E�g�����ꍇ
     */
    public void setRehashEnabled(boolean isEnabled) throws SharedContextSendException, SharedContextTimeoutException;
    
    /**
     * ���n�b�V�����L�����ǂ����𔻒肷��B<p>
     *
     * @return true�̏ꍇ�A�N���C�A���g���[�h
     */
    public boolean isRehashEnabled();
    
    /**
     * �R���e�L�X�g���U�̍Ĕz�u���s���B<p>
     * ��m�[�h�̏ꍇ�́A�S�Ẵm�[�h�ɍĔz�u���߂��o���B��m�[�h�łȂ��ꍇ�́A��m�[�h�ɍĔz�u�𑣂��B<br>
     *
     * @exception SharedContextSendException ���U�T�[�o�ւ̃��b�Z�[�W���M�Ɏ��s�����ꍇ
     * @exception SharedContextTimeoutException ���U�T�[�o����̉����҂��Ń^�C���A�E�g�����ꍇ
     */
    public void rehash() throws SharedContextSendException, SharedContextTimeoutException;
    
    /**
     * �R���e�L�X�g���U�̍Ĕz�u���s���B<p>
     * ��m�[�h�̏ꍇ�́A�S�Ẵm�[�h�ɍĔz�u���߂��o���B��m�[�h�łȂ��ꍇ�́A��m�[�h�ɍĔz�u�𑣂��B<br>
     *
     * @param timeout �^�C���A�E�g
     * @exception SharedContextSendException ���U�T�[�o�ւ̃��b�Z�[�W���M�Ɏ��s�����ꍇ
     * @exception SharedContextTimeoutException ���U�T�[�o����̉����҂��Ń^�C���A�E�g�����ꍇ
     */
    public void rehash(long timeout) throws SharedContextSendException, SharedContextTimeoutException;
    
    /**
     * �f�[�^�m�[�h�̐����擾����B<p>
     *
     * @return �f�[�^�m�[�h�̐�
     */
    public int getNodeCount();
    
    /**
     * ��m�[�h�ƂȂ��Ă���f�[�^�m�[�h�̐����擾����B<p>
     *
     * @return ��m�[�h�ƂȂ��Ă���f�[�^�m�[�h�̐�
     */
    public int getMainNodeCount();
    
    /**
     * �w�肳�ꂽ�L�[���ǂ̃f�[�^�m�[�h�Ɋi�[����邩�̃C���f�b�N�X���擾����B<p>
     *
     * @param key �L�[
     * @return �f�[�^�m�[�h�̃C���f�b�N�X
     */
    public int getDataNodeIndex(Object key);
    
    /**
     * �w�肳�ꂽ�C���f�b�N�X�̃f�[�^�m�[�h�ɓo�^����Ă���L�[�̐����擾����B<p>
     *
     * @param nodeIndex �f�[�^�m�[�h�̃C���f�b�N�X
     * @return �L�[�̐�
     * @exception SharedContextSendException ���U�T�[�o�ւ̃��b�Z�[�W���M�Ɏ��s�����ꍇ
     * @exception SharedContextTimeoutException ���U�T�[�o����̉����҂��Ń^�C���A�E�g�����ꍇ
     */
    public int size(int nodeIndex) throws SharedContextSendException, SharedContextTimeoutException;
    
    /**
     * �w�肳�ꂽ�C���f�b�N�X�̃f�[�^�m�[�h�̃L�[�̏W�����擾����B<p>
     *
     * @param nodeIndex �f�[�^�m�[�h�̃C���f�b�N�X
     * @return �L�[�̏W��
     * @exception SharedContextSendException ���U�T�[�o�ւ̃��b�Z�[�W���M�Ɏ��s�����ꍇ
     * @exception SharedContextTimeoutException ���U�T�[�o����̉����҂��Ń^�C���A�E�g�����ꍇ
     */
    public Set keySet(int nodeIndex) throws SharedContextSendException, SharedContextTimeoutException;
    
    /**
     * ��m�[�h�ƂȂ��Ă���f�[�^�m�[�h�̃L�[�̏W�����擾����B<p>
     *
     * @return �L�[�̏W��
     * @exception SharedContextSendException ���U�T�[�o�ւ̃��b�Z�[�W���M�Ɏ��s�����ꍇ
     * @exception SharedContextTimeoutException ���U�T�[�o����̉����҂��Ń^�C���A�E�g�����ꍇ
     */
    public Set keySetMain() throws SharedContextSendException, SharedContextTimeoutException;
    
    /**
     * �w�肳�ꂽ�C���f�b�N�X�̃f�[�^�m�[�h���N���C�A���g���[�h���ǂ����𔻒肷��B<p>
     *
     * @param nodeIndex �f�[�^�m�[�h�̃C���f�b�N�X
     * @return true�̏ꍇ�A�N���C�A���g���[�h
     */
    public boolean isClient(int nodeIndex);
    
    /**
     * �w�肳�ꂽ�C���f�b�N�X�̃f�[�^�m�[�h����m�[�h���ǂ����𔻒肷��B<p>
     *
     * @param nodeIndex �f�[�^�m�[�h�̃C���f�b�N�X
     * @return true�̏ꍇ�A��m�[�h
     */
    public boolean isMain(int nodeIndex);
    
    /**
     * �w�肳�ꂽ�L�[����m�[�h�Ƃ��ĕێ����邩�ǂ����𔻒肷��B<p>
     *
     * @param key �L�[
     * @return true�̏ꍇ�A��m�[�h�Ƃ��ĕێ�����
     */
    public boolean isMain(Object key);
    
    /**
     * �N�G���𕪎U�����f�[�^�m�[�h�ŃC���^�[�v���^���s����B<p>
     * �N�G���̕��@�́A{@link jp.ossc.nimbus.service.interpreter.Interpreter Interpreter}�̎����Ɉˑ�����B<br>
     * �N�G�����ł́A�R���e�L�X�g��ϐ���"context"�ŎQ�Ƃł���B�}�[�W�p�̃N�G�����ł́A�ϐ���"results"�ŁA�e�f�[�^�m�[�h�̏������ʂ��i�[����java.util.List���Q�Ƃł���B<br>
     *
     * @param query �N�G��
     * @param mergeQuery �}�[�W�p�̃N�G��
     * @param variables �N�G�����Ŏg�p����ϐ��}�b�v
     * @return ���s����
     * @exception EvaluateException �N�G���̎��s�ŗ�O�����������ꍇ
     * @exception SharedContextSendException ���U�T�[�o�ւ̃��b�Z�[�W���M�Ɏ��s�����ꍇ
     * @exception SharedContextTimeoutException ���U�T�[�o����̉����҂��Ń^�C���A�E�g�����ꍇ
     */
    public Object executeInterpretQuery(String query, String mergeQuery, Map variables) throws EvaluateException, SharedContextSendException, SharedContextTimeoutException;
    
    /**
     * �N�G���𕪎U�����f�[�^�m�[�h�ŃC���^�[�v���^���s����B<p>
     * �N�G���̕��@�́A{@link jp.ossc.nimbus.service.interpreter.Interpreter Interpreter}�̎����Ɉˑ�����B<br>
     * �N�G�����ł́A�R���e�L�X�g��ϐ���"context"�ŎQ�Ƃł���B�}�[�W�p�̃N�G�����ł́A�ϐ���"results"�ŁA�e�f�[�^�m�[�h�̏������ʂ��i�[����java.util.List���Q�Ƃł���B<br>
     *
     * @param query �N�G��
     * @param mergeQuery �}�[�W�p�̃N�G��
     * @param variables �N�G�����Ŏg�p����ϐ��}�b�v
     * @param timeout �^�C���A�E�g
     * @return ���s����
     * @exception EvaluateException �N�G���̎��s�ŗ�O�����������ꍇ
     * @exception SharedContextSendException ���U�T�[�o�ւ̃��b�Z�[�W���M�Ɏ��s�����ꍇ
     * @exception SharedContextTimeoutException ���U�T�[�o����̉����҂��Ń^�C���A�E�g�����ꍇ
     */
    public Object executeInterpretQuery(String query, String mergeQuery, Map variables, long timeout) throws EvaluateException, SharedContextSendException, SharedContextTimeoutException;
}
