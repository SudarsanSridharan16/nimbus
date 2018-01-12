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
package jp.ossc.nimbus.service.jndi;

import javax.naming.*;

/**
 * Jndi�t�@�C���_�[�C���^�[�t�F�C�X
 * 
 * @author Y.Tokuda
 */
public interface JndiFinder{
    
    /**
     * JNDI���烊�\�[�X�����b�N�A�b�v����B<p>
     * 
     * @param name JNDI��
     */
    public Object lookup(String name) throws NamingException;
    
    /**
     * JNDI���烊�\�[�X�����b�N�A�b�v����B<p>
     */
    public Object lookup() throws NamingException;
    
    /**
     * �L���b�V�����������[�g�I�u�W�F�N�g��S�ăN���A����B<p>
     */
    public void clearCache();
    
    /**
     * �w�肵��JNDI���̂��������[�g�I�u�W�F�N�g�̃L���b�V�����N���A����B<p>
     * 
     * @param jndiName �L���b�V������폜���郊���[�g�I�u�W�F�N�g��JNDI��
     */
    public void clearCache(String jndiName);
}
