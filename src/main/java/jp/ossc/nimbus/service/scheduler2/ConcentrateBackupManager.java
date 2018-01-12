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
package jp.ossc.nimbus.service.scheduler2;

import java.util.Date;
import java.io.File;

/**
 * �W�z�M�o�b�N�A�b�v�Ǘ��B<p>
 * 
 * @author M.Takata
 */
public interface ConcentrateBackupManager{
    
    
    /**
     * �w�肳�ꂽ�t�@�C�����o�b�N�A�b�v����B<br>
     *
     * @param group �o�b�N�A�b�v�O���[�v��
     * @param date �o�b�N�A�b�v���t
     * @param key �o�b�N�A�b�v�L�[
     * @param file �o�b�N�A�b�v�Ώۂ̃t�@�C��
     * @param compressed �o�b�N�A�b�v�Ώۂ̊e�t�@�C�������k����Ă��邩�ǂ����������t���O
     * @return �o�b�N�A�b�v���
     * @exception ConcentrateBackupException �o�b�N�A�b�v���Ɉُ킪���������ꍇ
     */
    public Object backup(String group, Date date, String key, File file, boolean compressed) throws ConcentrateBackupException;
    
    /**
     * �w�肳�ꂽ�t�@�C�����o�b�N�A�b�v����B<br>
     *
     * @param group �o�b�N�A�b�v�O���[�v��
     * @param date �o�b�N�A�b�v���t
     * @param key �o�b�N�A�b�v�L�[
     * @param file �o�b�N�A�b�v�Ώۂ̃t�@�C��
     * @param compressed �o�b�N�A�b�v�Ώۂ̊e�t�@�C�������k����Ă��邩�ǂ����������t���O
     * @param result �o�b�N�A�b�v���B�J��Ԃ��Ăяo���p�B
     * @return �o�b�N�A�b�v���
     * @exception ConcentrateBackupException �o�b�N�A�b�v���Ɉُ킪���������ꍇ
     */
    public Object backup(String group, Date date, String key, File file, boolean compressed, Object result) throws ConcentrateBackupException;
    
    /**
     * �o�b�N�A�b�v��S�č폜����B<p>
     *
     * @return �폜�ł����ꍇ�́Atrue
     * @exception ConcentrateBackupException �o�b�N�A�b�v�폜���Ɉُ킪���������ꍇ
     */
    public boolean clear() throws ConcentrateBackupException;
    
    /**
     * �w�肵���o�b�N�A�b�v�O���[�v�̃o�b�N�A�b�v��S�č폜����B<p>
     *
     * @param group �o�b�N�A�b�v�O���[�v��
     * @return �폜�ł����ꍇ�́Atrue
     * @exception ConcentrateBackupException �o�b�N�A�b�v�폜���Ɉُ킪���������ꍇ
     */
    public boolean remove(String group) throws ConcentrateBackupException;
    
    /**
     * �w�肵���o�b�N�A�b�v���t�̃o�b�N�A�b�v��S�č폜����B<p>
     *
     * @param date �o�b�N�A�b�v���t
     * @return �폜�ł����ꍇ�́Atrue
     * @exception ConcentrateBackupException �o�b�N�A�b�v�폜���Ɉُ킪���������ꍇ
     */
    public boolean remove(Date date) throws ConcentrateBackupException;
    
    /**
     * �w�肵���o�b�N�A�b�v�O���[�v���o�b�N�A�b�v���t�̃o�b�N�A�b�v��S�č폜����B<p>
     *
     * @param group �o�b�N�A�b�v�O���[�v��
     * @param date �o�b�N�A�b�v���t
     * @return �폜�ł����ꍇ�́Atrue
     * @exception ConcentrateBackupException �o�b�N�A�b�v�폜���Ɉُ킪���������ꍇ
     */
    public boolean remove(String group, Date date) throws ConcentrateBackupException;
    
    /**
     * �w�肵���o�b�N�A�b�v���t�܂ł̃o�b�N�A�b�v��S�č폜����B<p>
     *
     * @param date �o�b�N�A�b�v���t
     * @return �폜�ł����ꍇ�́Atrue
     * @exception ConcentrateBackupException �o�b�N�A�b�v�폜���Ɉُ킪���������ꍇ
     */
    public boolean removeTo(Date date) throws ConcentrateBackupException;
    
    /**
     * �w�肵���o�b�N�A�b�v�O���[�v���o�b�N�A�b�v���t�܂ł̃o�b�N�A�b�v��S�č폜����B<p>
     *
     * @param group �o�b�N�A�b�v�O���[�v��
     * @param date �o�b�N�A�b�v���t
     * @return �폜�ł����ꍇ�́Atrue
     * @exception ConcentrateBackupException �o�b�N�A�b�v�폜���Ɉُ킪���������ꍇ
     */
    public boolean removeTo(String group, Date date) throws ConcentrateBackupException;
    
    /**
     * �w�肵���o�b�N�A�b�v�O���[�v�A�o�b�N�A�b�v���t�A�o�b�N�A�b�v�L�[�̃o�b�N�A�b�v��S�č폜����B<p>
     *
     * @param group �o�b�N�A�b�v�O���[�v��
     * @param date �o�b�N�A�b�v���t
     * @param key �o�b�N�A�b�v�L�[
     * @return �폜�ł����ꍇ�́Atrue
     * @exception ConcentrateBackupException �o�b�N�A�b�v�폜���Ɉُ킪���������ꍇ
     */
    public boolean remove(String group, Date date, String key) throws ConcentrateBackupException;
}
