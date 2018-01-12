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
 * �o�^��Ԃ�ʒm����C�x���g�B<p>
 * {@link RegistrationBroadcaster}���Ǘ�����I�u�W�F�N�g���A�o�^�܂��͍폜���ꂽ�����ARegistrationBroadcaster�ɓo�^���ꂽ{@link RegistrationListener}�ɒʒm���邽�߂̃C�x���g�ł���B<br>
 *
 * @author M.Takata
 * @see RegistrationBroadcaster
 * @see RegistrationListener
 */
public class RegistrationEvent extends EventObject{
    
    private static final long serialVersionUID = 4756863502680600441L;
    
    /**
     * �o�^��Ԃ��ύX���ꂽ�I�u�W�F�N�g�����C�x���g�𐶐�����B<p>
     *
     * @param obj �o�^��Ԃ��ύX���ꂽ�I�u�W�F�N�g
     */
    public RegistrationEvent(Object obj){
        super(obj);
    }
    
    /**
     * �o�^��Ԃ��ύX���ꂽ�I�u�W�F�N�g���擾����B<p>
     *
     * @return �o�^��Ԃ��ύX���ꂽ�I�u�W�F�N�g
     */
    public Object getRegistration(){
        return source;
    }
}