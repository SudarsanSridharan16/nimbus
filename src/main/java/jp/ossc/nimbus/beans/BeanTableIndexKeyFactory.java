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
package jp.ossc.nimbus.beans;

import java.util.Set;
import java.util.Map;

/**
 * {@link BeanTableIndex Bean�e�[�u���C���f�b�N�X}�̃C���f�b�N�X�L�[�̃t�@�N�g���B<p>
 *
 * @author M.Takata
 * @see BeanTableIndex
 */
public interface BeanTableIndexKeyFactory{
    
    /**
     * �C���f�b�N�X�ΏۂƂȂ�v���p�e�B���̏W�����擾����B<p>
     * Bean�̃v���p�e�B��ҏW����ꍇ�́A�{���̃v���p�e�B���Ɣ��Ȃ��ʖ���Ԃ��K�v������B<br>
     *
     * @return �C���f�b�N�X�ΏۂƂȂ�v���p�e�B���̏W��
     */
    public Set getPropertyNames();
    
    /**
     * �w�肳�ꂽBean����C���f�b�N�X�L�[�𐶐�����B<p>
     * �w�肳�ꂽBean����A{@link #getPropertyNames()}�ŕԂ��v���p�e�B�ɊY������l���擾�y�ѕҏW���āA�C���f�b�N�X�̃L�[�ƂȂ�I�u�W�F�N�g�𐶐�����B<br>
     *
     * @param element �e�[�u���̗v�f�ƂȂ�Bean
     * @return �C���f�b�N�X�L�[
     * @exception IndexPropertyAccessException �w�肳�ꂽ�v���p�e�B�̎擾�ŗ�O�����������ꍇ
     */
    public Object createIndexKey(Object element) throws IndexPropertyAccessException;
    
    /**
     * �w�肳�ꂽ�v���p�e�B���ƒl�̃}�b�v����C���f�b�N�X�L�[�𐶐�����B<p>
     * �w�肳�ꂽ�v���p�e�B���ƒl�̃}�b�v����A{@link #getPropertyNames()}�ŕԂ��v���p�e�B�ɊY������l���擾�y�ѕҏW���āA�C���f�b�N�X�̃L�[�ƂȂ�I�u�W�F�N�g�𐶐�����B<br>
     *
     * @param props �v���p�e�B���ƒl�̃}�b�v
     * @return �C���f�b�N�X�L�[
     * @exception IllegalArgumentException �w�肳�ꂽ�v���p�e�B���ƒl�̃}�b�v�ɕK�v�ȃL�[���܂܂�Ă��Ȃ��ꍇ
     */
    public Object createIndexKeyByProperties(Map props) throws IllegalArgumentException;
}