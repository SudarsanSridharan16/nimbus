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

import jp.ossc.nimbus.beans.IndexPropertyAccessException;
import jp.ossc.nimbus.beans.IndexNotFoundException;

/**
 * {@link SharedContext ���L�R���e�L�X�g}�̌����r���[�B<p>
 *
 * @author M.Takata
 * @see SharedContext
 */
public interface SharedContextView{
    
    /**
     * �������ʂ̃L�[�W�����擾����B<p>
     *
     * @return �������ʂ̃L�[�W��
     */
    public Set getResultSet();
    
    /**
     * �_�����Z��Ԃ�_���ρiAND�j�ɂ���B<p>
     * �f�t�H���g�̘_�����Z��Ԃł��B<br>
     *
     * @return ���̃r���[
     */
    public SharedContextView and();
    
    /**
     * �_�����Z��Ԃ�_���a�iOR�j�ɂ���B<p>
     *
     * @return ���̃r���[
     */
    public SharedContextView or();
    
    /**
     * �_�����Z��Ԃ�ے�_���ρiNAND�j�ɂ���B<p>
     *
     * @return ���̃r���[
     */
    public SharedContextView nand();
    
    /**
     * �_�����Z��Ԃ�ے�_���a�iNOR�j�ɂ���B<p>
     *
     * @return ���̃r���[
     */
    public SharedContextView nor();
    
    /**
     * �_�����Z��Ԃ�r���I�_���a�iXOR�j�ɂ���B<p>
     *
     * @return ���̃r���[
     */
    public SharedContextView xor();
    
    /**
     * �_�����Z��Ԃ�r���I�ے�_���a�iXNOR�j�ɂ���B<p>
     *
     * @return ���̃r���[
     */
    public SharedContextView xnor();
    
    /**
     * �_�����Z��Ԃ�_����܁iIMP�j�ɂ���B<p>
     *
     * @return ���̃r���[
     */
    public SharedContextView imp();
    
    /**
     * �_�����Z��Ԃ�ے�_����܁iNIMP�j�ɂ���B<p>
     *
     * @return ���̃r���[
     */
    public SharedContextView nimp();
    
    /**
     * �_�����Z��Ԃ��t�_����܁iCIMP�j�ɂ���B<p>
     *
     * @return ���̃r���[
     */
    public SharedContextView cimp();
    
    /**
     * �_�����Z��Ԃ��t�ے�_����܁iCNIMP�j�ɂ���B<p>
     *
     * @return ���̃r���[
     */
    public SharedContextView cnimp();
    
    /**
     * ���̌����r���[�̋t�W�����Ƃ�B<p>
     * 
     * @return �t�W�����Ƃ������ʂ̂��̃r���[
     */
    public SharedContextView not();
    
    /**
     * ���̌����r���[�Ɏw�肳�ꂽ�����r���[��AND�A������B<p>
     * 
     * @param view �����r���[
     * @return �A�����ꂽ���ʂ̂��̃r���[
     */
    public SharedContextView and(SharedContextView view);
    
    /**
     * ���̌����r���[�Ɏw�肳�ꂽ�����r���[��OR�A������B<p>
     * 
     * @param view �����r���[
     * @return �A�����ꂽ���ʂ̂��̃r���[
     */
    public SharedContextView or(SharedContextView view);
    
    /**
     * ���̌����r���[�Ǝw�肳�ꂽ�����r���[�̔ے�_���ρiNAND�j���s���B<p>
     *
     * @param view �����r���[
     * @return ���ʂƂȂ邱�̃r���[
     */
    public SharedContextView nand(SharedContextView view);
    
    /**
     * ���̌����r���[�Ǝw�肳�ꂽ�����r���[�̔ے�_���a�iNOR�j���s���B<p>
     *
     * @param view �����r���[
     * @return ���ʂƂȂ邱�̃r���[
     */
    public SharedContextView nor(SharedContextView view);
    
    /**
     * ���̌����r���[�Ǝw�肳�ꂽ�����r���[�̔r���I�_���a�iXOR�j���s���B<p>
     *
     * @param view �����r���[
     * @return ���ʂƂȂ邱�̃r���[
     */
    public SharedContextView xor(SharedContextView view);
    
    /**
     * ���̌����r���[�Ǝw�肳�ꂽ�����r���[�̔ے�r���I�_���a�iXNOR�j���s���B<p>
     *
     * @param view �����r���[
     * @return ���ʂƂȂ邱�̃r���[
     */
    public SharedContextView xnor(SharedContextView view);
    
    /**
     * ���̌����r���[�Ǝw�肳�ꂽ�����r���[�̘_����܁iIMP�j���s���B<p>
     *
     * @param view �����r���[
     * @return ���ʂƂȂ邱�̃r���[
     */
    public SharedContextView imp(SharedContextView view);
    
    /**
     * ���̌����r���[�Ǝw�肳�ꂽ�����r���[�̔ے�_����܁iNIMP�j���s���B<p>
     *
     * @param view �����r���[
     * @return ���ʂƂȂ邱�̃r���[
     */
    public SharedContextView nimp(SharedContextView view);
    
    /**
     * ���̌����r���[�Ǝw�肳�ꂽ�����r���[�̋t�_����܁iCIMP�j���s���B<p>
     *
     * @param view �����r���[
     * @return ���ʂƂȂ邱�̃r���[
     */
    public SharedContextView cimp(SharedContextView view);
    
    /**
     * ���̌����r���[�Ǝw�肳�ꂽ�����r���[�̔ے�t�_����܁iCNIMP�j���s���B<p>
     *
     * @param view �����r���[
     * @return ���ʂƂȂ邱�̃r���[
     */
    public SharedContextView cnimp(SharedContextView view);
    
    /**
     * �w�肳�ꂽ�C���f�b�N�X�܂��̓v���p�e�B�W���ɑ΂���C���f�b�N�X�̃L�[�v�f�̏W������������B<p>
     * �L�[�����̈��ł���A�P���C���f�b�N�X�ƕ����C���f�b�N�X�ɑ΂��ėL���B<br>
     *
     * @param indexName �C���f�b�N�X��
     * @param propNames �v���p�e�B���z��
     * @return �������ʂ̂��̃r���[
     * @exception IndexNotFoundException �Y������C���f�b�N�X�����݂��Ȃ����A�܂��͕����C���f�b�N�X�̏ꍇ
     * @exception SharedContextSendException ���U�T�[�o�ւ̃��b�Z�[�W���M�Ɏ��s�����ꍇ
     * @exception SharedContextTimeoutException ���U�T�[�o����̉����҂��Ń^�C���A�E�g�����ꍇ
     */
    public SharedContextView searchKey(String indexName, String[] propNames) throws IndexNotFoundException, SharedContextSendException, SharedContextTimeoutException;
    
    /**
     * �w�肳�ꂽ�C���f�b�N�X�܂��̓v���p�e�B�W���ɑ΂���C���f�b�N�X�̃L�[�v�f�̏W������������B<p>
     * �L�[�����̈��ł���A�P���C���f�b�N�X�ƕ����C���f�b�N�X�ɑ΂��ėL���B<br>
     *
     * @param timeout ���C���A�E�g
     * @param indexName �C���f�b�N�X��
     * @param propNames �v���p�e�B���z��
     * @return �������ʂ̂��̃r���[
     * @exception IndexNotFoundException �Y������C���f�b�N�X�����݂��Ȃ����A�܂��͕����C���f�b�N�X�̏ꍇ
     * @exception SharedContextSendException ���U�T�[�o�ւ̃��b�Z�[�W���M�Ɏ��s�����ꍇ
     * @exception SharedContextTimeoutException ���U�T�[�o����̉����҂��Ń^�C���A�E�g�����ꍇ
     */
    public SharedContextView searchKey(long timeout, String indexName, String[] propNames) throws IndexNotFoundException, SharedContextSendException, SharedContextTimeoutException;
    
    /**
     * ����̃v���p�e�B��null�ƂȂ�Bean�W������������B<p>
     * ��v�����̈��ł���A�P���C���f�b�N�X�ɑ΂��Ă̂ݗL���B<br>
     *
     * @param indexName �C���f�b�N�X��
     * @param propName �v���p�e�B��
     * @return �������ʂ̂��̃r���[
     * @exception IndexNotFoundException �Y������C���f�b�N�X�����݂��Ȃ����A�܂��͕����C���f�b�N�X�̏ꍇ
     * @exception SharedContextSendException ���U�T�[�o�ւ̃��b�Z�[�W���M�Ɏ��s�����ꍇ
     * @exception SharedContextTimeoutException ���U�T�[�o����̉����҂��Ń^�C���A�E�g�����ꍇ
     */
    public SharedContextView searchNull(String indexName, String propName) throws IndexNotFoundException, SharedContextSendException, SharedContextTimeoutException;
    
    /**
     * ����̃v���p�e�B��null�ƂȂ�Bean�W������������B<p>
     * ��v�����̈��ł���A�P���C���f�b�N�X�ɑ΂��Ă̂ݗL���B<br>
     *
     * @param timeout �^�C���A�E�g
     * @param indexName �C���f�b�N�X��
     * @param propName �v���p�e�B��
     * @return �������ʂ̂��̃r���[
     * @exception IndexNotFoundException �Y������C���f�b�N�X�����݂��Ȃ����A�܂��͕����C���f�b�N�X�̏ꍇ
     * @exception SharedContextSendException ���U�T�[�o�ւ̃��b�Z�[�W���M�Ɏ��s�����ꍇ
     * @exception SharedContextTimeoutException ���U�T�[�o����̉����҂��Ń^�C���A�E�g�����ꍇ
     */
    public SharedContextView searchNull(long timeout, String indexName, String propName) throws IndexNotFoundException, SharedContextSendException, SharedContextTimeoutException;
    
    /**
     * ����̃v���p�e�B����null�ƂȂ�Bean�W������������B<p>
     * ��v�����̈��ł���A�P���C���f�b�N�X�ɑ΂��Ă̂ݗL���B<br>
     *
     * @param indexName �C���f�b�N�X��
     * @param propName �v���p�e�B��
     * @return �������ʂ̂��̃r���[
     * @exception IndexNotFoundException �Y������C���f�b�N�X�����݂��Ȃ����A�܂��͕����C���f�b�N�X�̏ꍇ
     * @exception SharedContextSendException ���U�T�[�o�ւ̃��b�Z�[�W���M�Ɏ��s�����ꍇ
     * @exception SharedContextTimeoutException ���U�T�[�o����̉����҂��Ń^�C���A�E�g�����ꍇ
     */
    public SharedContextView searchNotNull(String indexName, String propName) throws IndexNotFoundException, SharedContextSendException, SharedContextTimeoutException;
    
    /**
     * ����̃v���p�e�B����null�ƂȂ�Bean�W������������B<p>
     * ��v�����̈��ł���A�P���C���f�b�N�X�ɑ΂��Ă̂ݗL���B<br>
     *
     * @param timeout �^�C���A�E�g
     * @param indexName �C���f�b�N�X��
     * @param propName �v���p�e�B��
     * @return �������ʂ̂��̃r���[
     * @exception IndexNotFoundException �Y������C���f�b�N�X�����݂��Ȃ����A�܂��͕����C���f�b�N�X�̏ꍇ
     * @exception SharedContextSendException ���U�T�[�o�ւ̃��b�Z�[�W���M�Ɏ��s�����ꍇ
     * @exception SharedContextTimeoutException ���U�T�[�o����̉����҂��Ń^�C���A�E�g�����ꍇ
     */
    public SharedContextView searchNotNull(long timeout, String indexName, String propName) throws IndexNotFoundException, SharedContextSendException, SharedContextTimeoutException;
    
    /**
     * ����̃v���p�e�B���w�肵��Bean�̊Y������v���p�e�B�ƈ�v����Bean�W������������B<p>
     * ��v�����̈��ł���A�P���C���f�b�N�X�ƕ����C���f�b�N�X�ɑ΂��ėL���B<br>
     *
     * @param value �����L�[�ƂȂ�Bean
     * @param indexName �C���f�b�N�X��
     * @param propNames �v���p�e�B���z��
     * @return �������ʂ̂��̃r���[
     * @exception IndexNotFoundException �Y������C���f�b�N�X�����݂��Ȃ��ꍇ
     * @exception IndexPropertyAccessException �w�肳�ꂽ�v���p�e�B�̎擾�ŗ�O�����������ꍇ
     * @exception SharedContextSendException ���U�T�[�o�ւ̃��b�Z�[�W���M�Ɏ��s�����ꍇ
     * @exception SharedContextTimeoutException ���U�T�[�o����̉����҂��Ń^�C���A�E�g�����ꍇ
     */
    public SharedContextView searchBy(
        Object value,
        String indexName,
        String[] propNames
    ) throws IndexNotFoundException, IndexPropertyAccessException, SharedContextSendException, SharedContextTimeoutException;
    
    /**
     * ����̃v���p�e�B���w�肵��Bean�̊Y������v���p�e�B�ƈ�v����Bean�W������������B<p>
     * ��v�����̈��ł���A�P���C���f�b�N�X�ƕ����C���f�b�N�X�ɑ΂��ėL���B<br>
     *
     * @param timeout �^�C���A�E�g
     * @param value �����L�[�ƂȂ�Bean
     * @param indexName �C���f�b�N�X��
     * @param propNames �v���p�e�B���z��
     * @return �������ʂ̂��̃r���[
     * @exception IndexNotFoundException �Y������C���f�b�N�X�����݂��Ȃ��ꍇ
     * @exception IndexPropertyAccessException �w�肳�ꂽ�v���p�e�B�̎擾�ŗ�O�����������ꍇ
     * @exception SharedContextSendException ���U�T�[�o�ւ̃��b�Z�[�W���M�Ɏ��s�����ꍇ
     * @exception SharedContextTimeoutException ���U�T�[�o����̉����҂��Ń^�C���A�E�g�����ꍇ
     */
    public SharedContextView searchBy(
        long timeout,
        Object value,
        String indexName,
        String[] propNames
    ) throws IndexNotFoundException, IndexPropertyAccessException, SharedContextSendException, SharedContextTimeoutException;
    
    /**
     * ����̃v���p�e�B���w�肵��������Bean�̊Y������v���p�e�B�ƈ�v����Bean�W������������B<p>
     * ��v�����̈��ł���A�P���C���f�b�N�X�ƕ����C���f�b�N�X�ɑ΂��ėL���B<br>
     *
     * @param indexName �C���f�b�N�X��
     * @param propNames �v���p�e�B���z��
     * @param values �����L�[�ƂȂ�Bean�z��
     * @return �������ʂ̂��̃r���[
     * @exception IndexNotFoundException �Y������C���f�b�N�X�����݂��Ȃ��ꍇ
     * @exception IndexPropertyAccessException �w�肳�ꂽ�v���p�e�B�̎擾�ŗ�O�����������ꍇ
     * @exception SharedContextSendException ���U�T�[�o�ւ̃��b�Z�[�W���M�Ɏ��s�����ꍇ
     * @exception SharedContextTimeoutException ���U�T�[�o����̉����҂��Ń^�C���A�E�g�����ꍇ
     */
    public SharedContextView searchIn(
        String indexName,
        String[] propNames,
        Object[] values
    ) throws IndexNotFoundException, IndexPropertyAccessException, SharedContextSendException, SharedContextTimeoutException;
    
    /**
     * ����̃v���p�e�B���w�肵��������Bean�̊Y������v���p�e�B�ƈ�v����Bean�W������������B<p>
     * ��v�����̈��ł���A�P���C���f�b�N�X�ƕ����C���f�b�N�X�ɑ΂��ėL���B<br>
     *
     * @param timeout �^�C���A�E�g
     * @param indexName �C���f�b�N�X��
     * @param propNames �v���p�e�B���z��
     * @param values �����L�[�ƂȂ�Bean�z��
     * @return �������ʂ̂��̃r���[
     * @exception IndexNotFoundException �Y������C���f�b�N�X�����݂��Ȃ��ꍇ
     * @exception IndexPropertyAccessException �w�肳�ꂽ�v���p�e�B�̎擾�ŗ�O�����������ꍇ
     * @exception SharedContextSendException ���U�T�[�o�ւ̃��b�Z�[�W���M�Ɏ��s�����ꍇ
     * @exception SharedContextTimeoutException ���U�T�[�o����̉����҂��Ń^�C���A�E�g�����ꍇ
     */
    public SharedContextView searchIn(
        long timeout,
        String indexName,
        String[] propNames,
        Object[] values
    ) throws IndexNotFoundException, IndexPropertyAccessException, SharedContextSendException, SharedContextTimeoutException;
    
    /**
     * ����̃v���p�e�B���w�肵���l�ƈ�v����Bean�W������������B<p>
     * ��v�����̈��ł���A�P���C���f�b�N�X�ɑ΂��Ă̂ݗL���B<br>
     *
     * @param prop �����L�[�ƂȂ�l
     * @param indexName �C���f�b�N�X��
     * @param propName �v���p�e�B��
     * @return �������ʂ̂��̃r���[
     * @exception IndexNotFoundException �Y������C���f�b�N�X�����݂��Ȃ����A�܂��͕����C���f�b�N�X�̏ꍇ
     * @exception SharedContextSendException ���U�T�[�o�ւ̃��b�Z�[�W���M�Ɏ��s�����ꍇ
     * @exception SharedContextTimeoutException ���U�T�[�o����̉����҂��Ń^�C���A�E�g�����ꍇ
     */
    public SharedContextView searchByProperty(
        Object prop,
        String indexName,
        String propName
    ) throws IndexNotFoundException, SharedContextSendException, SharedContextTimeoutException;
    
    /**
     * ����̃v���p�e�B���w�肵���l�ƈ�v����Bean�W������������B<p>
     * ��v�����̈��ł���A�P���C���f�b�N�X�ɑ΂��Ă̂ݗL���B<br>
     *
     * @param timeout �^�C���A�E�g
     * @param prop �����L�[�ƂȂ�l
     * @param indexName �C���f�b�N�X��
     * @param propName �v���p�e�B��
     * @return �������ʂ̂��̃r���[
     * @exception IndexNotFoundException �Y������C���f�b�N�X�����݂��Ȃ����A�܂��͕����C���f�b�N�X�̏ꍇ
     * @exception SharedContextSendException ���U�T�[�o�ւ̃��b�Z�[�W���M�Ɏ��s�����ꍇ
     * @exception SharedContextTimeoutException ���U�T�[�o����̉����҂��Ń^�C���A�E�g�����ꍇ
     */
    public SharedContextView searchByProperty(
        long timeout,
        Object prop,
        String indexName,
        String propName
    ) throws IndexNotFoundException, SharedContextSendException, SharedContextTimeoutException;
    
    /**
     * ����̃v���p�e�B���w�肵�������̒l�ƈ�v����Bean�W������������B<p>
     * ��v�����̈��ł���A�P���C���f�b�N�X�ɑ΂��Ă̂ݗL���B<br>
     *
     * @param indexName �C���f�b�N�X��
     * @param propName �v���p�e�B��
     * @param props �����L�[�ƂȂ�l�z��
     * @return �������ʂ̂��̃r���[
     * @exception IndexNotFoundException �Y������C���f�b�N�X�����݂��Ȃ����A�܂��͕����C���f�b�N�X�̏ꍇ
     * @exception SharedContextSendException ���U�T�[�o�ւ̃��b�Z�[�W���M�Ɏ��s�����ꍇ
     * @exception SharedContextTimeoutException ���U�T�[�o����̉����҂��Ń^�C���A�E�g�����ꍇ
     */
    public SharedContextView searchInProperty(
        String indexName,
        String propName,
        Object[] props
    ) throws IndexNotFoundException, SharedContextSendException, SharedContextTimeoutException;
    
    /**
     * ����̃v���p�e�B���w�肵�������̒l�ƈ�v����Bean�W������������B<p>
     * ��v�����̈��ł���A�P���C���f�b�N�X�ɑ΂��Ă̂ݗL���B<br>
     *
     * @param timeout �^�C���A�E�g
     * @param indexName �C���f�b�N�X��
     * @param propName �v���p�e�B��
     * @param props �����L�[�ƂȂ�l�z��
     * @return �������ʂ̂��̃r���[
     * @exception IndexNotFoundException �Y������C���f�b�N�X�����݂��Ȃ����A�܂��͕����C���f�b�N�X�̏ꍇ
     * @exception SharedContextSendException ���U�T�[�o�ւ̃��b�Z�[�W���M�Ɏ��s�����ꍇ
     * @exception SharedContextTimeoutException ���U�T�[�o����̉����҂��Ń^�C���A�E�g�����ꍇ
     */
    public SharedContextView searchInProperty(
        long timeout,
        String indexName,
        String propName,
        Object[] props
    ) throws IndexNotFoundException, SharedContextSendException, SharedContextTimeoutException;
    
    /**
     * ����̃v���p�e�B���w�肵���l�ƈ�v����Bean�W������������B<p>
     * ��v�����̈��ł���A�P���C���f�b�N�X�ƕ����C���f�b�N�X�ɑ΂��ėL���B<br>
     *
     * @param props �����L�[�ƂȂ�v���p�e�B���ƒl�̃}�b�s���O
     * @param indexName �C���f�b�N�X��
     * @return �������ʂ̂��̃r���[
     * @exception IndexNotFoundException �Y������C���f�b�N�X�����݂��Ȃ��ꍇ
     * @exception IllegalArgumentException �w�肳�ꂽ�C���f�b�N�X���w�肳�ꂽ�v���p�e�B�Ɋ֘A���Ȃ��ꍇ
     * @exception SharedContextSendException ���U�T�[�o�ւ̃��b�Z�[�W���M�Ɏ��s�����ꍇ
     * @exception SharedContextTimeoutException ���U�T�[�o����̉����҂��Ń^�C���A�E�g�����ꍇ
     */
    public SharedContextView searchByProperty(
        Map props,
        String indexName
    ) throws IndexNotFoundException, IllegalArgumentException, SharedContextSendException, SharedContextTimeoutException;
    
    /**
     * ����̃v���p�e�B���w�肵���l�ƈ�v����Bean�W������������B<p>
     * ��v�����̈��ł���A�P���C���f�b�N�X�ƕ����C���f�b�N�X�ɑ΂��ėL���B<br>
     *
     * @param timeout �^�C���A�E�g
     * @param props �����L�[�ƂȂ�v���p�e�B���ƒl�̃}�b�s���O
     * @param indexName �C���f�b�N�X��
     * @return �������ʂ̂��̃r���[
     * @exception IndexNotFoundException �Y������C���f�b�N�X�����݂��Ȃ��ꍇ
     * @exception IllegalArgumentException �w�肳�ꂽ�C���f�b�N�X���w�肳�ꂽ�v���p�e�B�Ɋ֘A���Ȃ��ꍇ
     * @exception SharedContextSendException ���U�T�[�o�ւ̃��b�Z�[�W���M�Ɏ��s�����ꍇ
     * @exception SharedContextTimeoutException ���U�T�[�o����̉����҂��Ń^�C���A�E�g�����ꍇ
     */
    public SharedContextView searchByProperty(
        long timeout,
        Map props,
        String indexName
    ) throws IndexNotFoundException, IllegalArgumentException, SharedContextSendException, SharedContextTimeoutException;
    
    /**
     * ����̃v���p�e�B���w�肵�������̒l�ƈ�v����Bean�W������������B<p>
     * ��v�����̈��ł���A�P���C���f�b�N�X�ƕ����C���f�b�N�X�ɑ΂��ėL���B<br>
     *
     * @param indexName �C���f�b�N�X��
     * @param props �����L�[�ƂȂ�v���p�e�B���ƒl�̃}�b�s���O�̔z��
     * @return �������ʂ̂��̃r���[
     * @exception IndexNotFoundException �Y������C���f�b�N�X�����݂��Ȃ��ꍇ
     * @exception IllegalArgumentException �w�肳�ꂽ�C���f�b�N�X���w�肳�ꂽ�v���p�e�B�Ɋ֘A���Ȃ��ꍇ
     * @exception SharedContextSendException ���U�T�[�o�ւ̃��b�Z�[�W���M�Ɏ��s�����ꍇ
     * @exception SharedContextTimeoutException ���U�T�[�o����̉����҂��Ń^�C���A�E�g�����ꍇ
     */
    public SharedContextView searchInProperty(
        String indexName,
        Map[] props
    ) throws IndexNotFoundException, IllegalArgumentException, SharedContextSendException, SharedContextTimeoutException;
    
    /**
     * ����̃v���p�e�B���w�肵�������̒l�ƈ�v����Bean�W������������B<p>
     * ��v�����̈��ł���A�P���C���f�b�N�X�ƕ����C���f�b�N�X�ɑ΂��ėL���B<br>
     *
     * @param timeout �^�C���A�E�g
     * @param indexName �C���f�b�N�X��
     * @param props �����L�[�ƂȂ�v���p�e�B���ƒl�̃}�b�s���O�̔z��
     * @return �������ʂ̂��̃r���[
     * @exception IndexNotFoundException �Y������C���f�b�N�X�����݂��Ȃ��ꍇ
     * @exception IllegalArgumentException �w�肳�ꂽ�C���f�b�N�X���w�肳�ꂽ�v���p�e�B�Ɋ֘A���Ȃ��ꍇ
     * @exception SharedContextSendException ���U�T�[�o�ւ̃��b�Z�[�W���M�Ɏ��s�����ꍇ
     * @exception SharedContextTimeoutException ���U�T�[�o����̉����҂��Ń^�C���A�E�g�����ꍇ
     */
    public SharedContextView searchInProperty(
        long timeout,
        String indexName,
        Map[] props
    ) throws IndexNotFoundException, IllegalArgumentException, SharedContextSendException, SharedContextTimeoutException;
    
    /**
     * ����̃v���p�e�B���w�肵��Bean�̊Y������v���p�e�B���傫��Bean�W������������B<p>
     * �͈͌����̈��ł���A�P���C���f�b�N�X�ɑ΂��Ă̂ݗL���B<br>
     *
     * @param fromValue 臒l������Bean
     * @param indexName �C���f�b�N�X��
     * @param propName �v���p�e�B��
     * @return �������ʂ̂��̃r���[
     * @exception IndexNotFoundException �Y������C���f�b�N�X�����݂��Ȃ����A�����C���f�b�N�X�̏ꍇ
     * @exception IndexPropertyAccessException �w�肳�ꂽ�v���p�e�B�̎擾�ŗ�O�����������ꍇ
     * @exception SharedContextSendException ���U�T�[�o�ւ̃��b�Z�[�W���M�Ɏ��s�����ꍇ
     * @exception SharedContextTimeoutException ���U�T�[�o����̉����҂��Ń^�C���A�E�g�����ꍇ
     */
    public SharedContextView searchFrom(
        Object fromValue,
        String indexName,
        String propName
    ) throws IndexNotFoundException, IndexPropertyAccessException, SharedContextSendException, SharedContextTimeoutException;
    
    /**
     * ����̃v���p�e�B���w�肵��Bean�̊Y������v���p�e�B���傫��Bean�W������������B<p>
     * �͈͌����̈��ł���A�P���C���f�b�N�X�ɑ΂��Ă̂ݗL���B<br>
     *
     * @param fromValue 臒l������Bean
     * @param inclusive �������ʂ�臒l���܂ނ��ǂ����B�܂ޏꍇ��true
     * @param indexName �C���f�b�N�X��
     * @param propName �v���p�e�B��
     * @return �������ʂ̂��̃r���[
     * @exception IndexNotFoundException �Y������C���f�b�N�X�����݂��Ȃ����A�����C���f�b�N�X�̏ꍇ
     * @exception IndexPropertyAccessException �w�肳�ꂽ�v���p�e�B�̎擾�ŗ�O�����������ꍇ
     * @exception SharedContextSendException ���U�T�[�o�ւ̃��b�Z�[�W���M�Ɏ��s�����ꍇ
     * @exception SharedContextTimeoutException ���U�T�[�o����̉����҂��Ń^�C���A�E�g�����ꍇ
     */

    public SharedContextView searchFrom(
        Object fromValue,
        boolean inclusive,
        String indexName,
        String propName
    ) throws IndexNotFoundException, IndexPropertyAccessException, SharedContextSendException, SharedContextTimeoutException;

    
    /**
     * ����̃v���p�e�B���w�肵��Bean�̊Y������v���p�e�B���傫��Bean�W������������B<p>
     * �͈͌����̈��ł���A�P���C���f�b�N�X�ɑ΂��Ă̂ݗL���B<br>
     *
     * @param timeout �^�C���A�E�g
     * @param fromValue 臒l������Bean
     * @param indexName �C���f�b�N�X��
     * @param propName �v���p�e�B��
     * @return �������ʂ̂��̃r���[
     * @exception IndexNotFoundException �Y������C���f�b�N�X�����݂��Ȃ����A�����C���f�b�N�X�̏ꍇ
     * @exception IndexPropertyAccessException �w�肳�ꂽ�v���p�e�B�̎擾�ŗ�O�����������ꍇ
     * @exception SharedContextSendException ���U�T�[�o�ւ̃��b�Z�[�W���M�Ɏ��s�����ꍇ
     * @exception SharedContextTimeoutException ���U�T�[�o����̉����҂��Ń^�C���A�E�g�����ꍇ
     */
    public SharedContextView searchFrom(
        long timeout,
        Object fromValue,
        String indexName,
        String propName
    ) throws IndexNotFoundException, IndexPropertyAccessException, SharedContextSendException, SharedContextTimeoutException;
    
    /**
     * ����̃v���p�e�B���w�肵��Bean�̊Y������v���p�e�B���傫��Bean�W������������B<p>
     * �͈͌����̈��ł���A�P���C���f�b�N�X�ɑ΂��Ă̂ݗL���B<br>
     *
     * @param timeout �^�C���A�E�g
     * @param fromValue 臒l������Bean
     * @param inclusive �������ʂ�臒l���܂ނ��ǂ����B�܂ޏꍇ��true
     * @param indexName �C���f�b�N�X��
     * @param propName �v���p�e�B��
     * @return �������ʂ̂��̃r���[
     * @exception IndexNotFoundException �Y������C���f�b�N�X�����݂��Ȃ����A�����C���f�b�N�X�̏ꍇ
     * @exception IndexPropertyAccessException �w�肳�ꂽ�v���p�e�B�̎擾�ŗ�O�����������ꍇ
     * @exception SharedContextSendException ���U�T�[�o�ւ̃��b�Z�[�W���M�Ɏ��s�����ꍇ
     * @exception SharedContextTimeoutException ���U�T�[�o����̉����҂��Ń^�C���A�E�g�����ꍇ
     */

    public SharedContextView searchFrom(
        long timeout,
        Object fromValue,
        boolean inclusive,
        String indexName,
        String propName
    ) throws IndexNotFoundException, IndexPropertyAccessException, SharedContextSendException, SharedContextTimeoutException;

    
    /**
     * ����̃v���p�e�B���w�肵���l���傫��Bean�W������������B<p>
     * �͈͌����̈��ł���A�P���C���f�b�N�X�ɑ΂��Ă̂ݗL���B<br>
     *
     * @param fromProp 臒l
     * @param indexName �C���f�b�N�X��
     * @param propName �v���p�e�B��
     * @return �������ʂ̂��̃r���[
     * @exception IndexNotFoundException �Y������C���f�b�N�X�����݂��Ȃ����A�����C���f�b�N�X�̏ꍇ
     * @exception SharedContextSendException ���U�T�[�o�ւ̃��b�Z�[�W���M�Ɏ��s�����ꍇ
     * @exception SharedContextTimeoutException ���U�T�[�o����̉����҂��Ń^�C���A�E�g�����ꍇ
     */
    public SharedContextView searchFromProperty(
        Object fromProp,
        String indexName,
        String propName
    ) throws IndexNotFoundException, SharedContextSendException, SharedContextTimeoutException;
    
    /**
     * ����̃v���p�e�B���w�肵���l���傫��Bean�W������������B<p>
     * �͈͌����̈��ł���A�P���C���f�b�N�X�ɑ΂��Ă̂ݗL���B<br>
     *
     * @param fromProp 臒l
     * @param inclusive �������ʂ�臒l���܂ނ��ǂ����B�܂ޏꍇ��true
     * @param indexName �C���f�b�N�X��
     * @param propName �v���p�e�B��
     * @return �������ʂ̂��̃r���[
     * @exception IndexNotFoundException �Y������C���f�b�N�X�����݂��Ȃ����A�����C���f�b�N�X�̏ꍇ
     * @exception SharedContextSendException ���U�T�[�o�ւ̃��b�Z�[�W���M�Ɏ��s�����ꍇ
     * @exception SharedContextTimeoutException ���U�T�[�o����̉����҂��Ń^�C���A�E�g�����ꍇ
     */

    public SharedContextView searchFromProperty(
        Object fromProp,
        boolean inclusive,
        String indexName,
        String propName
    ) throws IndexNotFoundException, SharedContextSendException, SharedContextTimeoutException;

    
    /**
     * ����̃v���p�e�B���w�肵���l���傫��Bean�W������������B<p>
     * �͈͌����̈��ł���A�P���C���f�b�N�X�ɑ΂��Ă̂ݗL���B<br>
     *
     * @param timeout �^�C���A�E�g
     * @param fromProp 臒l
     * @param indexName �C���f�b�N�X��
     * @param propName �v���p�e�B��
     * @return �������ʂ̂��̃r���[
     * @exception IndexNotFoundException �Y������C���f�b�N�X�����݂��Ȃ����A�����C���f�b�N�X�̏ꍇ
     * @exception SharedContextSendException ���U�T�[�o�ւ̃��b�Z�[�W���M�Ɏ��s�����ꍇ
     * @exception SharedContextTimeoutException ���U�T�[�o����̉����҂��Ń^�C���A�E�g�����ꍇ
     */
    public SharedContextView searchFromProperty(
        long timeout,
        Object fromProp,
        String indexName,
        String propName
    ) throws IndexNotFoundException, SharedContextSendException, SharedContextTimeoutException;
    
    /**
     * ����̃v���p�e�B���w�肵���l���傫��Bean�W������������B<p>
     * �͈͌����̈��ł���A�P���C���f�b�N�X�ɑ΂��Ă̂ݗL���B<br>
     *
     * @param timeout �^�C���A�E�g
     * @param fromProp 臒l
     * @param inclusive �������ʂ�臒l���܂ނ��ǂ����B�܂ޏꍇ��true
     * @param indexName �C���f�b�N�X��
     * @param propName �v���p�e�B��
     * @return �������ʂ̂��̃r���[
     * @exception IndexNotFoundException �Y������C���f�b�N�X�����݂��Ȃ����A�����C���f�b�N�X�̏ꍇ
     * @exception SharedContextSendException ���U�T�[�o�ւ̃��b�Z�[�W���M�Ɏ��s�����ꍇ
     * @exception SharedContextTimeoutException ���U�T�[�o����̉����҂��Ń^�C���A�E�g�����ꍇ
     */

    public SharedContextView searchFromProperty(
        long timeout,
        Object fromProp,
        boolean inclusive,
        String indexName,
        String propName
    ) throws IndexNotFoundException, SharedContextSendException, SharedContextTimeoutException;

    
    /**
     * ����̃v���p�e�B���w�肵��Bean�̊Y������v���p�e�B��菬����Bean�W������������B<p>
     * �͈͌����̈��ł���A�P���C���f�b�N�X�ɑ΂��Ă̂ݗL���B<br>
     *
     * @param toValue 臒l������Bean
     * @param indexName �C���f�b�N�X��
     * @param propName �v���p�e�B��
     * @return �������ʂ̂��̃r���[
     * @exception IndexNotFoundException �Y������C���f�b�N�X�����݂��Ȃ����A�����C���f�b�N�X�̏ꍇ
     * @exception IndexPropertyAccessException �w�肳�ꂽ�v���p�e�B�̎擾�ŗ�O�����������ꍇ
     * @exception SharedContextSendException ���U�T�[�o�ւ̃��b�Z�[�W���M�Ɏ��s�����ꍇ
     * @exception SharedContextTimeoutException ���U�T�[�o����̉����҂��Ń^�C���A�E�g�����ꍇ
     */
    public SharedContextView searchTo(
        Object toValue,
        String indexName,
        String propName
    ) throws IndexNotFoundException, IndexPropertyAccessException, SharedContextSendException, SharedContextTimeoutException;
    
    /**
     * ����̃v���p�e�B���w�肵��Bean�̊Y������v���p�e�B��菬����Bean�W������������B<p>
     * �͈͌����̈��ł���A�P���C���f�b�N�X�ɑ΂��Ă̂ݗL���B<br>
     *
     * @param toValue 臒l������Bean
     * @param inclusive �������ʂ�臒l���܂ނ��ǂ����B�܂ޏꍇ��true
     * @param indexName �C���f�b�N�X��
     * @param propName �v���p�e�B��
     * @return �������ʂ̂��̃r���[
     * @exception IndexNotFoundException �Y������C���f�b�N�X�����݂��Ȃ����A�����C���f�b�N�X�̏ꍇ
     * @exception IndexPropertyAccessException �w�肳�ꂽ�v���p�e�B�̎擾�ŗ�O�����������ꍇ
     * @exception SharedContextSendException ���U�T�[�o�ւ̃��b�Z�[�W���M�Ɏ��s�����ꍇ
     * @exception SharedContextTimeoutException ���U�T�[�o����̉����҂��Ń^�C���A�E�g�����ꍇ
     */

    public SharedContextView searchTo(
        Object toValue,
        boolean inclusive,
        String indexName,
        String propName
    ) throws IndexNotFoundException, IndexPropertyAccessException, SharedContextSendException, SharedContextTimeoutException;

    
    /**
     * ����̃v���p�e�B���w�肵��Bean�̊Y������v���p�e�B��菬����Bean�W������������B<p>
     * �͈͌����̈��ł���A�P���C���f�b�N�X�ɑ΂��Ă̂ݗL���B<br>
     *
     * @param timeout �^�C���A�E�g
     * @param toValue 臒l������Bean
     * @param indexName �C���f�b�N�X��
     * @param propName �v���p�e�B��
     * @return �������ʂ̂��̃r���[
     * @exception IndexNotFoundException �Y������C���f�b�N�X�����݂��Ȃ����A�����C���f�b�N�X�̏ꍇ
     * @exception IndexPropertyAccessException �w�肳�ꂽ�v���p�e�B�̎擾�ŗ�O�����������ꍇ
     * @exception SharedContextSendException ���U�T�[�o�ւ̃��b�Z�[�W���M�Ɏ��s�����ꍇ
     * @exception SharedContextTimeoutException ���U�T�[�o����̉����҂��Ń^�C���A�E�g�����ꍇ
     */
    public SharedContextView searchTo(
        long timeout,
        Object toValue,
        String indexName,
        String propName
    ) throws IndexNotFoundException, IndexPropertyAccessException, SharedContextSendException, SharedContextTimeoutException;
    
    /**
     * ����̃v���p�e�B���w�肵��Bean�̊Y������v���p�e�B��菬����Bean�W������������B<p>
     * �͈͌����̈��ł���A�P���C���f�b�N�X�ɑ΂��Ă̂ݗL���B<br>
     *
     * @param timeout �^�C���A�E�g
     * @param toValue 臒l������Bean
     * @param inclusive �������ʂ�臒l���܂ނ��ǂ����B�܂ޏꍇ��true
     * @param indexName �C���f�b�N�X��
     * @param propName �v���p�e�B��
     * @return �������ʂ̂��̃r���[
     * @exception IndexNotFoundException �Y������C���f�b�N�X�����݂��Ȃ����A�����C���f�b�N�X�̏ꍇ
     * @exception IndexPropertyAccessException �w�肳�ꂽ�v���p�e�B�̎擾�ŗ�O�����������ꍇ
     * @exception SharedContextSendException ���U�T�[�o�ւ̃��b�Z�[�W���M�Ɏ��s�����ꍇ
     * @exception SharedContextTimeoutException ���U�T�[�o����̉����҂��Ń^�C���A�E�g�����ꍇ
     */

    public SharedContextView searchTo(
        long timeout,
        Object toValue,
        boolean inclusive,
        String indexName,
        String propName
    ) throws IndexNotFoundException, IndexPropertyAccessException, SharedContextSendException, SharedContextTimeoutException;

    
    /**
     * ����̃v���p�e�B���w�肵���l��菬����Bean�W������������B<p>
     * �͈͌����̈��ł���A�P���C���f�b�N�X�ɑ΂��Ă̂ݗL���B<br>
     *
     * @param toProp 臒l
     * @param indexName �C���f�b�N�X��
     * @param propName �v���p�e�B��
     * @return �������ʂ̂��̃r���[
     * @exception IndexNotFoundException �Y������C���f�b�N�X�����݂��Ȃ����A�����C���f�b�N�X�̏ꍇ
     * @exception SharedContextSendException ���U�T�[�o�ւ̃��b�Z�[�W���M�Ɏ��s�����ꍇ
     * @exception SharedContextTimeoutException ���U�T�[�o����̉����҂��Ń^�C���A�E�g�����ꍇ
     */
    public SharedContextView searchToProperty(
        Object toProp,
        String indexName,
        String propName
    ) throws IndexNotFoundException, SharedContextSendException, SharedContextTimeoutException;
    
    /**
     * ����̃v���p�e�B���w�肵���l��菬����Bean�W������������B<p>
     * �͈͌����̈��ł���A�P���C���f�b�N�X�ɑ΂��Ă̂ݗL���B<br>
     *
     * @param toProp 臒l
     * @param inclusive �������ʂ�臒l���܂ނ��ǂ����B�܂ޏꍇ��true
     * @param indexName �C���f�b�N�X��
     * @param propName �v���p�e�B��
     * @return �������ʂ̂��̃r���[
     * @exception IndexNotFoundException �Y������C���f�b�N�X�����݂��Ȃ����A�����C���f�b�N�X�̏ꍇ
     * @exception SharedContextSendException ���U�T�[�o�ւ̃��b�Z�[�W���M�Ɏ��s�����ꍇ
     * @exception SharedContextTimeoutException ���U�T�[�o����̉����҂��Ń^�C���A�E�g�����ꍇ
     */

    public SharedContextView searchToProperty(
        Object toProp,
        boolean inclusive,
        String indexName,
        String propName
    ) throws IndexNotFoundException, SharedContextSendException, SharedContextTimeoutException;

    
    /**
     * ����̃v���p�e�B���w�肵���l��菬����Bean�W������������B<p>
     * �͈͌����̈��ł���A�P���C���f�b�N�X�ɑ΂��Ă̂ݗL���B<br>
     *
     * @param timeout �^�C���A�E�g
     * @param toProp 臒l
     * @param indexName �C���f�b�N�X��
     * @param propName �v���p�e�B��
     * @return �������ʂ̂��̃r���[
     * @exception IndexNotFoundException �Y������C���f�b�N�X�����݂��Ȃ����A�����C���f�b�N�X�̏ꍇ
     * @exception SharedContextSendException ���U�T�[�o�ւ̃��b�Z�[�W���M�Ɏ��s�����ꍇ
     * @exception SharedContextTimeoutException ���U�T�[�o����̉����҂��Ń^�C���A�E�g�����ꍇ
     */
    public SharedContextView searchToProperty(
        long timeout,
        Object toProp,
        String indexName,
        String propName
    ) throws IndexNotFoundException, SharedContextSendException, SharedContextTimeoutException;
    
    /**
     * ����̃v���p�e�B���w�肵���l��菬����Bean�W������������B<p>
     * �͈͌����̈��ł���A�P���C���f�b�N�X�ɑ΂��Ă̂ݗL���B<br>
     *
     * @param timeout �^�C���A�E�g
     * @param toProp 臒l
     * @param inclusive �������ʂ�臒l���܂ނ��ǂ����B�܂ޏꍇ��true
     * @param indexName �C���f�b�N�X��
     * @param propName �v���p�e�B��
     * @return �������ʂ̂��̃r���[
     * @exception IndexNotFoundException �Y������C���f�b�N�X�����݂��Ȃ����A�����C���f�b�N�X�̏ꍇ
     * @exception SharedContextSendException ���U�T�[�o�ւ̃��b�Z�[�W���M�Ɏ��s�����ꍇ
     * @exception SharedContextTimeoutException ���U�T�[�o����̉����҂��Ń^�C���A�E�g�����ꍇ
     */

    public SharedContextView searchToProperty(
        long timeout,
        Object toProp,
        boolean inclusive,
        String indexName,
        String propName
    ) throws IndexNotFoundException, SharedContextSendException, SharedContextTimeoutException;

    
    /**
     * ����̃v���p�e�B���w�肵��Bean�̊Y������v���p�e�B�͈͓̔��ƂȂ�Bean�W������������B<p>
     * �͈͌����̈��ł���A�P���C���f�b�N�X�ɑ΂��Ă̂ݗL���B<br>
     *
     * @param fromValue �͈͂̍ŏ�臒l������Bean
     * @param toValue �͈͂̍ő�臒l������Bean
     * @param indexName �C���f�b�N�X��
     * @param propName �v���p�e�B��
     * @return �������ʂ̂��̃r���[
     * @exception IndexNotFoundException �Y������C���f�b�N�X�����݂��Ȃ����A�����C���f�b�N�X�̏ꍇ
     * @exception IndexPropertyAccessException �w�肳�ꂽ�v���p�e�B�̎擾�ŗ�O�����������ꍇ
     * @exception SharedContextSendException ���U�T�[�o�ւ̃��b�Z�[�W���M�Ɏ��s�����ꍇ
     * @exception SharedContextTimeoutException ���U�T�[�o����̉����҂��Ń^�C���A�E�g�����ꍇ
     */
    public SharedContextView searchRange(
        Object fromValue,
        Object toValue,
        String indexName,
        String propName
    ) throws IndexNotFoundException, IndexPropertyAccessException, SharedContextSendException, SharedContextTimeoutException;
    
    /**
     * ����̃v���p�e�B���w�肵��Bean�̊Y������v���p�e�B�͈͓̔��ƂȂ�Bean�W������������B<p>
     * �͈͌����̈��ł���A�P���C���f�b�N�X�ɑ΂��Ă̂ݗL���B<br>
     *
     * @param fromValue �͈͂̍ŏ�臒l������Bean
     * @param fromInclusive �������ʂɍŏ�臒l���܂ނ��ǂ����B�܂ޏꍇ��true
     * @param toValue �͈͂̍ő�臒l������Bean
     * @param toInclusive �������ʂɍő�臒l���܂ނ��ǂ����B�܂ޏꍇ��true
     * @param indexName �C���f�b�N�X��
     * @param propName �v���p�e�B��
     * @return �������ʂ̂��̃r���[
     * @exception IndexNotFoundException �Y������C���f�b�N�X�����݂��Ȃ����A�����C���f�b�N�X�̏ꍇ
     * @exception IndexPropertyAccessException �w�肳�ꂽ�v���p�e�B�̎擾�ŗ�O�����������ꍇ
     * @exception SharedContextSendException ���U�T�[�o�ւ̃��b�Z�[�W���M�Ɏ��s�����ꍇ
     * @exception SharedContextTimeoutException ���U�T�[�o����̉����҂��Ń^�C���A�E�g�����ꍇ
     */

    public SharedContextView searchRange(
        Object fromValue,
        boolean fromInclusive,
        Object toValue,
        boolean toInclusive,
        String indexName,
        String propName
    ) throws IndexNotFoundException, IndexPropertyAccessException, SharedContextSendException, SharedContextTimeoutException;

    
    /**
     * ����̃v���p�e�B���w�肵��Bean�̊Y������v���p�e�B�͈͓̔��ƂȂ�Bean�W������������B<p>
     * �͈͌����̈��ł���A�P���C���f�b�N�X�ɑ΂��Ă̂ݗL���B<br>
     *
     * @param timeout �^�C���A�E�g
     * @param fromValue �͈͂̍ŏ�臒l������Bean
     * @param toValue �͈͂̍ő�臒l������Bean
     * @param indexName �C���f�b�N�X��
     * @param propName �v���p�e�B��
     * @return �������ʂ̂��̃r���[
     * @exception IndexNotFoundException �Y������C���f�b�N�X�����݂��Ȃ����A�����C���f�b�N�X�̏ꍇ
     * @exception IndexPropertyAccessException �w�肳�ꂽ�v���p�e�B�̎擾�ŗ�O�����������ꍇ
     * @exception SharedContextSendException ���U�T�[�o�ւ̃��b�Z�[�W���M�Ɏ��s�����ꍇ
     * @exception SharedContextTimeoutException ���U�T�[�o����̉����҂��Ń^�C���A�E�g�����ꍇ
     */
    public SharedContextView searchRange(
        long timeout,
        Object fromValue,
        Object toValue,
        String indexName,
        String propName
    ) throws IndexNotFoundException, IndexPropertyAccessException, SharedContextSendException, SharedContextTimeoutException;
    
    /**
     * ����̃v���p�e�B���w�肵��Bean�̊Y������v���p�e�B�͈͓̔��ƂȂ�Bean�W������������B<p>
     * �͈͌����̈��ł���A�P���C���f�b�N�X�ɑ΂��Ă̂ݗL���B<br>
     *
     * @param timeout �^�C���A�E�g
     * @param fromValue �͈͂̍ŏ�臒l������Bean
     * @param fromInclusive �������ʂɍŏ�臒l���܂ނ��ǂ����B�܂ޏꍇ��true
     * @param toValue �͈͂̍ő�臒l������Bean
     * @param toInclusive �������ʂɍő�臒l���܂ނ��ǂ����B�܂ޏꍇ��true
     * @param indexName �C���f�b�N�X��
     * @param propName �v���p�e�B��
     * @return �������ʂ̂��̃r���[
     * @exception IndexNotFoundException �Y������C���f�b�N�X�����݂��Ȃ����A�����C���f�b�N�X�̏ꍇ
     * @exception IndexPropertyAccessException �w�肳�ꂽ�v���p�e�B�̎擾�ŗ�O�����������ꍇ
     * @exception SharedContextSendException ���U�T�[�o�ւ̃��b�Z�[�W���M�Ɏ��s�����ꍇ
     * @exception SharedContextTimeoutException ���U�T�[�o����̉����҂��Ń^�C���A�E�g�����ꍇ
     */

    public SharedContextView searchRange(
        long timeout,
        Object fromValue,
        boolean fromInclusive,
        Object toValue,
        boolean toInclusive,
        String indexName,
        String propName
    ) throws IndexNotFoundException, IndexPropertyAccessException, SharedContextSendException, SharedContextTimeoutException;

    
    /**
     * ����̃v���p�e�B���w�肵���l�͈͓̔��ƂȂ�Bean�W������������B<p>
     * �͈͌����̈��ł���A�P���C���f�b�N�X�ɑ΂��Ă̂ݗL���B<br>
     *
     * @param fromProp �͈͂̍ŏ�臒l
     * @param toProp �͈͂̍ő�臒l
     * @param indexName �C���f�b�N�X��
     * @param propName �v���p�e�B��
     * @return �������ʂ̂��̃r���[
     * @exception IndexNotFoundException �Y������C���f�b�N�X�����݂��Ȃ����A�����C���f�b�N�X�̏ꍇ
     * @exception SharedContextSendException ���U�T�[�o�ւ̃��b�Z�[�W���M�Ɏ��s�����ꍇ
     * @exception SharedContextTimeoutException ���U�T�[�o����̉����҂��Ń^�C���A�E�g�����ꍇ
     */
    public SharedContextView searchRangeProperty(
        Object fromProp, 
        Object toProp, 
        String indexName,
        String propName
    ) throws IndexNotFoundException, SharedContextSendException, SharedContextTimeoutException;
    
    /**
     * ����̃v���p�e�B���w�肵���l�͈͓̔��ƂȂ�Bean�W������������B<p>
     * �͈͌����̈��ł���A�P���C���f�b�N�X�ɑ΂��Ă̂ݗL���B<br>
     *
     * @param fromProp �͈͂̍ŏ�臒l
     * @param fromInclusive �������ʂɍŏ�臒l���܂ނ��ǂ����B�܂ޏꍇ��true
     * @param toProp �͈͂̍ő�臒l
     * @param toInclusive �������ʂɍő�臒l���܂ނ��ǂ����B�܂ޏꍇ��true
     * @param indexName �C���f�b�N�X��
     * @param propName �v���p�e�B��
     * @return �������ʂ̂��̃r���[
     * @exception IndexNotFoundException �Y������C���f�b�N�X�����݂��Ȃ����A�����C���f�b�N�X�̏ꍇ
     * @exception SharedContextSendException ���U�T�[�o�ւ̃��b�Z�[�W���M�Ɏ��s�����ꍇ
     * @exception SharedContextTimeoutException ���U�T�[�o����̉����҂��Ń^�C���A�E�g�����ꍇ
     */

    public SharedContextView searchRangeProperty(
        Object fromProp, 
        boolean fromInclusive,
        Object toProp, 
        boolean toInclusive,
        String indexName,
        String propName
    ) throws IndexNotFoundException, SharedContextSendException, SharedContextTimeoutException;

    
    /**
     * ����̃v���p�e�B���w�肵���l�͈͓̔��ƂȂ�Bean�W������������B<p>
     * �͈͌����̈��ł���A�P���C���f�b�N�X�ɑ΂��Ă̂ݗL���B<br>
     *
     * @param timeout �^�C���A�E�g
     * @param fromProp �͈͂̍ŏ�臒l
     * @param toProp �͈͂̍ő�臒l
     * @param indexName �C���f�b�N�X��
     * @param propName �v���p�e�B��
     * @return �������ʂ̂��̃r���[
     * @exception IndexNotFoundException �Y������C���f�b�N�X�����݂��Ȃ����A�����C���f�b�N�X�̏ꍇ
     * @exception SharedContextSendException ���U�T�[�o�ւ̃��b�Z�[�W���M�Ɏ��s�����ꍇ
     * @exception SharedContextTimeoutException ���U�T�[�o����̉����҂��Ń^�C���A�E�g�����ꍇ
     */
    public SharedContextView searchRangeProperty(
        long timeout,
        Object fromProp, 
        Object toProp, 
        String indexName,
        String propName
    ) throws IndexNotFoundException, SharedContextSendException, SharedContextTimeoutException;
    
    /**
     * ����̃v���p�e�B���w�肵���l�͈͓̔��ƂȂ�Bean�W������������B<p>
     * �͈͌����̈��ł���A�P���C���f�b�N�X�ɑ΂��Ă̂ݗL���B<br>
     *
     * @param timeout �^�C���A�E�g
     * @param fromProp �͈͂̍ŏ�臒l
     * @param fromInclusive �������ʂɍŏ�臒l���܂ނ��ǂ����B�܂ޏꍇ��true
     * @param toProp �͈͂̍ő�臒l
     * @param toInclusive �������ʂɍő�臒l���܂ނ��ǂ����B�܂ޏꍇ��true
     * @param indexName �C���f�b�N�X��
     * @param propName �v���p�e�B��
     * @return �������ʂ̂��̃r���[
     * @exception IndexNotFoundException �Y������C���f�b�N�X�����݂��Ȃ����A�����C���f�b�N�X�̏ꍇ
     * @exception SharedContextSendException ���U�T�[�o�ւ̃��b�Z�[�W���M�Ɏ��s�����ꍇ
     * @exception SharedContextTimeoutException ���U�T�[�o����̉����҂��Ń^�C���A�E�g�����ꍇ
     */

    public SharedContextView searchRangeProperty(
        long timeout,
        Object fromProp, 
        boolean fromInclusive,
        Object toProp, 
        boolean toInclusive,
        String indexName,
        String propName
    ) throws IndexNotFoundException, SharedContextSendException, SharedContextTimeoutException;

    
    /**
     * ���̃r���[�̕��������B<p>
     * �����̘_�����Z��Ԃ́A�f�t�H���g�l�ƂȂ�B<br>
     *
     * @return ���̃r���[�̕���
     */
    public Object clone();
}
