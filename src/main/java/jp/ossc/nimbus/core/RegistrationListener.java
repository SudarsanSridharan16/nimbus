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
package jp.ossc.nimbus.core;

import java.util.*;

/**
 * �o�^��Ԃ��Ď����郊�X�i�C���^�t�F�[�X�B<p>
 * {@link RegistrationBroadcaster}�ɁA���̃��X�i��o�^���鎖�ŁARegistrationBroadcaster�ŊǗ������C�ӂ̃I�u�W�F�N�g�̓o�^��Ԃ��Ď����鎖���ł���B<br>
 * �Ď��ł����Ԃ́A�o�^�A�폜�ŁA���ꂼ��A{@link #registered(RegistrationEvent)}�A{@link #unregistered(RegistrationEvent)}�Œʒm�����B<br>
 *
 * @author M.Takata
 * @see RegistrationBroadcaster
 * @see RegistrationEvent
 */
public interface RegistrationListener extends EventListener{
    
    /**
     * RegistrationBroadcaster�ŊǗ������C�ӂ̃I�u�W�F�N�g���o�^���ꂽ���ɌĂяo�����B<p>
     *
     * @param e RegistrationEvent�I�u�W�F�N�g
     */
    public void registered(RegistrationEvent e);
    
    /**
     * RegistrationBroadcaster�ŊǗ������C�ӂ̃I�u�W�F�N�g���폜���ꂽ���ɌĂяo�����B<p>
     *
     * @param e RegistrationEvent�I�u�W�F�N�g
     */
    public void unregistered(RegistrationEvent e);
}