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
package jp.ossc.nimbus.service.publish;

import java.util.Set;

/**
 * ���b�Z�[�W�C���^�t�F�[�X�B<p>
 * 
 * @author M.Takata
 */
public interface Message{
    
    /**
     * �T�u�W�F�N�g���擾����B<br>
     *
     * @return �T�u�W�F�N�g
     */
    public String getSubject();
    
    /**
     * �T�u�W�F�N�g�W�����擾����B<br>
     *
     * @return �T�u�W�F�N�g�W��
     */
    public Set getSubjects();
    
    /**
     * �T�u�W�F�N�g��ݒ肷��B<br>
     *
     * @param sbj �T�u�W�F�N�g
     * @param key �L�[
     */
    public void setSubject(String sbj, String key);
    
    /**
     * �L�[���擾����B<br>
     *
     * @return �L�[
     */
    public String getKey();
    
    /**
     * �L�[���擾����B<br>
     *
     * @param sbj �T�u�W�F�N�g
     * @return �L�[
     */
    public String getKey(String sbj);
    
    /**
     * ���b�Z�[�W�̃f�[�^��ݒ肷��B<br>
     *
     * @param obj ���b�Z�[�W�̃f�[�^�I�u�W�F�N�g
     * @exception MessageException �f�[�^�I�u�W�F�N�g�̐ݒ�Ɏ��s�����ꍇ
     */
    public void setObject(Object obj) throws MessageException;
    
    /**
     * ���b�Z�[�W�̃f�[�^���擾����B<br>
     *
     * @return �f�[�^�I�u�W�F�N�g
     * @exception MessageException �f�[�^�I�u�W�F�N�g�̎擾�Ɏ��s�����ꍇ
     */
    public Object getObject() throws MessageException;
    
    /**
     * ���b�Z�[�W�̒��񉻃o�C�g�z���ݒ肷��B<br>
     *
     * @param bytes ���b�Z�[�W�̒��񉻃o�C�g�z��
     */
    public void setSerializedBytes(byte[] bytes);
    
    /**
     * ���b�Z�[�W�̒��񉻃o�C�g�z����擾����B<br>
     *
     * @return ���b�Z�[�W�̒��񉻃o�C�g�z��
     */
    public byte[] getSerializedBytes();
    
    /**
     * ���M�������擾����B<p>
     *
     * @return ���M����
     */
    public long getSendTime();
    
    /**
     * ��M�������擾����B<p>
     *
     * @return ��M����
     */
    public long getReceiveTime();
    
    /**
     * ���M��ID�̏W�����擾����B<br>
     *
     * @return ���M��ID�̏W��
     */
    public Set getDestinationIds();
    
    /**
     * ���M��ID�̏W����ݒ肷��B<br>
     *
     * @param ids ���M��ID�̏W��
     */
    public void setDestinationIds(Set ids);
    
    /**
     * ���M��ID��ǉ�����B<br>
     *
     * @param id ���M��ID
     */
    public void addDestinationId(Object id);
    
    /**
     * ���M��ID���폜����B<br>
     *
     * @param id ���M��ID
     */
    public void removeDestinationId(Object id);
    
    /**
     * ���M��ID���N���A����B<br>
     */
    public void clearDestinationIds();
    
    /**
     * �w�肵��ID�����M��ID�Ɋ܂܂�Ă��邩���肷��B<br>
     *
     * @param id ID
     * @return ���M��ID�Ɋ܂܂��ꍇtrue
     */
    public boolean containsDestinationId(Object id);
    
    /**
     * ���̃I�u�W�F�N�g���ė��p����悤�ɑ����B<br>
     */
    public void recycle();
}