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

import java.util.Map;

import jp.ossc.nimbus.core.ServiceBaseMBean;
import jp.ossc.nimbus.core.ServiceName;

/**
 * {@link DefaultQuerySearchManagerService}��MBean�C���^�t�F�[�X<p>
 * 
 * @author M.Takata
 * @see DefaultQuerySearchManagerService
 */
public interface DefaultQuerySearchManagerServiceMBean extends ServiceBaseMBean{
    
    /**
     * �����N�G����ݒ肷��B<p>
     * �����N�G���́A{@link PersistentManager}�̎d�l�ɏ�����B<br>
     *
     * @param query �����N�G��
     */
    public void setQuery(String query);
    
    /**
     * �����N�G�����擾����B<p>
     *
     * @return �����N�G��
     */
    public String getQuery();
    
    /**
     * �����N�G�������s����ۂ�java.sql.Statement�ɐݒ肷��v���p�e�B��ݒ肷��B<p>
     *
     * @param name �v���p�e�B��
     * @param value �v���p�e�B�l
     */
    public void setStatementProperty(String name, Object value);
    
    /**
     * �����N�G�������s����ۂ�java.sql.Statement�ɐݒ肷��v���p�e�B��ݒ肷��B<p>
     *
     * @param props �����N�G�����s����java.sql.Statement�ɐݒ肷��v���p�e�B
     */
    public void setStatementProperties(Map props);
    
    /**
     * �����N�G�������s����ۂ�java.sql.Statement�ɐݒ肷��v���p�e�B���擾����B<p>
     *
     * @return �����N�G�����s����java.sql.Statement�ɐݒ肷��v���p�e�B
     */
    public Map getStatementProperties();
    
    /**
     * �����N�G�������s����ۂ�java.sql.ResultSet�ɐݒ肷��v���p�e�B��ݒ肷��B<p>
     *
     * @param name �v���p�e�B��
     * @param value �v���p�e�B�l
     */
    public void setResultSetProperty(String name, Object value);
    
    /**
     * �����N�G�������s����ۂ�java.sql.ResultSet�ɐݒ肷��v���p�e�B��ݒ肷��B<p>
     *
     * @param props �����N�G�����s����java.sql.ResultSet�ɐݒ肷��v���p�e�B
     */
    public void setResultSetProperties(Map props);
    
    /**
     * �����N�G�������s����ۂ�java.sql.ResultSet�ɐݒ肷��v���p�e�B���擾����B<p>
     *
     * @return �����N�G�����s����java.sql.ResultSet�ɐݒ肷��v���p�e�B
     */
    public Map getResultSetProperties();
    
    /**
     * �������ʂ��o�C���h����I�u�W�F�N�g�̃N���X��ݒ肷��B<p>
     * �����Ŏw�肷��N���X�́A�����Ȃ��̃R���X�g���N�^�����K�v������B<br>
     *
     * @param clazz �������ʂ��o�C���h����I�u�W�F�N�g�̃N���X
     */
    public void setOutputClass(Class clazz);
    
    /**
     * �������ʂ��o�C���h����I�u�W�F�N�g�̃N���X���擾����B<p>
     *
     * @return �������ʂ��o�C���h����I�u�W�F�N�g�̃N���X
     */
    public Class getOutputClass();
    
    /**
     * �������ʂ��K����ӂɂȂ邩�ǂ�����ݒ肷��B<p>
     * �f�t�H���g�́Afalse�ŁA�������ʂ̓��X�g�ɂȂ�B<br>
     *
     * @param isUnique ��ӂɂȂ�ꍇtrue
     */
    public void setUnique(boolean isUnique);
    
    /**
     * �������ʂ��K����ӂɂȂ邩�ǂ����𔻒肷��B<p>
     *
     * @return true�̏ꍇ�A��ӂɂȂ�
     */
    public boolean isUnique();
    
    /**
     * �����N�G�������s����ۂɎg�p����JDBC�ڑ����擾����{@link ConnectionFactory}�T�[�r�X�̃T�[�r�X����ݒ肷��B<p>
     *
     * @param name ConnectionFactory�T�[�r�X�̃T�[�r�X��
     */
    public void setConnectionFactoryServiceName(ServiceName name);
    
    /**
     * �����N�G�������s����ۂɎg�p����JDBC�ڑ����擾����{@link ConnectionFactory}�T�[�r�X�̃T�[�r�X�����擾����B<p>
     *
     * @return ConnectionFactory�T�[�r�X�̃T�[�r�X��
     */
    public ServiceName getConnectionFactoryServiceName();
    
    /**
     * �����N�G�������s����{@link PersistentManager}�T�[�r�X�̃T�[�r�X����ݒ肷��B<p>
     *
     * @param name PersistentManager�T�[�r�X�̃T�[�r�X��
     */
    public void setPersistentManagerServiceName(ServiceName name);
    
    /**
     * �����N�G�������s����{@link PersistentManager}�T�[�r�X�̃T�[�r�X�����擾����B<p>
     *
     * @return PersistentManager�T�[�r�X�̃T�[�r�X��
     */
    public ServiceName getPersistentManagerServiceName();
    
    /**
     * �������ʂ��L���b�V������{@link jp.ossc.nimbus.service.cache.CacheMap CacheMap}�T�[�r�X�̃T�[�r�X����ݒ肷��B<p>
     * �ݒ肵�Ȃ��ꍇ�́A�������ʂ̓L���b�V������Ȃ��B<br>
     *
     * @param name CacheMap�T�[�r�X�̃T�[�r�X��
     */
    public void setCacheMapServiceName(ServiceName name);
    
    /**
     * �������ʂ��L���b�V������{@link jp.ossc.nimbus.service.cache.CacheMap CacheMap}�T�[�r�X�̃T�[�r�X�����擾����B<p>
     *
     * @return CacheMap�T�[�r�X�̃T�[�r�X��
     */
    public ServiceName getCacheMapServiceName();
    
    /**
     * �L���b�V���̃q�b�g�����擾����B<p>
     *
     * @return �q�b�g��
     */
    public float getCacheHitRatio();
    
    /**
     * �L���b�V���̃q�b�g�������Z�b�g����B<p>
     */
    public void resetCacheHitRatio();
}
