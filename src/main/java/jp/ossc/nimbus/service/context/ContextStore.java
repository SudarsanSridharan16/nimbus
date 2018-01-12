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
 * �R���e�L�X�g�X�g�A�B<p>
 *
 * @author M.Takata
 */
public interface ContextStore{
    
    /**
     * �X�g�A���S�č폜����B<p>
     *
     * @exception Exception ���s�����ꍇ
     */
    public void clear() throws Exception;
    
    /**
     * �R���e�L�X�g��ۑ�����B<p>
     *
     * @param context �R���e�L�X�g
     * @exception Exception ���s�����ꍇ
     */
    public void save(Context context) throws Exception;
    
    /**
     * �R���e�L�X�g��̎w�肳�ꂽ�L�[�ɊY������l��ۑ�����B<p>
     *
     * @param context �R���e�L�X�g
     * @param key �L�[
     * @exception Exception ���s�����ꍇ
     */
    public void save(Context context, Object key) throws Exception;
    
    /**
     * �R���e�L�X�g�ɓǂݍ��ށB<p>
     *
     * @param context �R���e�L�X�g
     * @exception Exception ���s�����ꍇ
     */
    public void load(Context context) throws Exception;
    
    /**
     * �R���e�L�X�g�ɃL�[��ǂݍ��ށB<p>
     *
     * @param context �R���e�L�X�g
     * @exception Exception ���s�����ꍇ
     */
    public void loadKey(Context context) throws Exception;
    
    /**
     * �w�肳�ꂽ�L�[�ɊY������l���R���e�L�X�g�ɓǂݍ��ށB<p>
     *
     * @param context �R���e�L�X�g
     * @param key �L�[
     * @exception Exception ���s�����ꍇ
     */
    public void load(Context context, Object key) throws Exception;
}
