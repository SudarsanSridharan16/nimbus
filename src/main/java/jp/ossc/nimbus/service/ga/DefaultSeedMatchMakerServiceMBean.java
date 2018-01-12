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
package jp.ossc.nimbus.service.ga;

import jp.ossc.nimbus.core.ServiceBaseMBean;

/**
 * {@link DefaultSeedMatchMakerService}��MBean�C���^�t�F�[�X<p>
 * 
 * @author M.Takata
 * @see DefaultSeedMatchMakerService
 */
public interface DefaultSeedMatchMakerServiceMBean extends ServiceBaseMBean{
    
    /**
     * ���l�����F�����_���B<p>
     */
    public static final int MATCH_MAKE_METHOD_RANDOM   = 1;
    
    /**
     * ���l�����F���[���b�g�B<p>
     */
    public static final int MATCH_MAKE_METHOD_ROULETTE = 2;
    
    /**
     * �G���[�g����ݒ肷��B<p>
     * �f�t�H���g�́A0.0�B<br>
     *
     * @param rate �G���[�g���B0.0 &gt;= rate &gt; 1.0
     * @exception IllegalArgumentException �w�肳�ꂽ�l���s���ȏꍇ
     */
    public void setEliteRate(float rate) throws IllegalArgumentException;
    
    /**
     * �G���[�g�����擾����B<p>
     *
     * @return �G���[�g��
     */
    public float getEliteRate();
    
    /**
     * �h���b�v����ݒ肷��B<p>
     * �f�t�H���g�́A0.0�B<br>
     *
     * @param rate �h���b�v���B0.0 &gt;= rate &gt; 1.0
     * @exception IllegalArgumentException �w�肳�ꂽ�l���s���ȏꍇ
     */
    public void setDropRate(float rate) throws IllegalArgumentException;
    
    /**
     * �h���b�v�����擾����B<p>
     *
     * @return �h���b�v��
     */
    public float getDropRate();
    
    /**
     * �V�K����ݒ肷��B<p>
     * �f�t�H���g�́A0.0�B<br>
     *
     * @param rate �V�K���B0.0 &gt;= rate &gt; 1.0
     * @exception IllegalArgumentException �w�肳�ꂽ�l���s���ȏꍇ
     */
    public void setNewRate(float rate) throws IllegalArgumentException;
    
    /**
     * �V�K�����擾����B<p>
     *
     * @return �V�K��
     */
    public float getNewRate();
    
    /**
     * ���l���@��ݒ肷��B<p>
     * �f�t�H���g�́A{@link #MATCH_MAKE_METHOD_RANDOM �����_��}�B<br>
     *
     * @param method ���l���@
     * @see #MATCH_MAKE_METHOD_RANDOM �����_��
     * @see #MATCH_MAKE_METHOD_ROULETTE ���[���b�g
     */
    public void setMatchMakeMethod(int method) throws IllegalArgumentException;
    
    /**
     * ���l���@���擾����B<p>
     *
     * @return ���l���@
     */
    public int getMatchMakeMethod();
    
    /**
     * �G���[�g�������ΏۂɊ܂߂邩�ǂ�����ݒ肷��B<p>
     * �f�t�H���g�́Atrue�Ō����ΏۂɊ܂߂�B<br>
     *
     * @param isContanis �����ΏۂɊ܂߂�ꍇtrue
     */
    public void setContanisEliteInMatchMake(boolean isContanis);
    
    /**
     * �G���[�g�������ΏۂɊ܂߂邩�ǂ����𔻒肷��B<p>
     *
     * @return true�̏ꍇ�A�����ΏۂɊ܂߂�
     */
    public boolean isContanisEliteInMatchMake();
}
