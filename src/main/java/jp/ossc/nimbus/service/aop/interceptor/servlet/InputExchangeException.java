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
package jp.ossc.nimbus.service.aop.interceptor.servlet;

/**
 * ���̓f�[�^�����Ɏ��s�����ꍇ��throw������O�B<p>
 * 
 * @author M.Takata
 */
public class InputExchangeException extends ExchangeException{
    
    private static final long serialVersionUID = -8434275603511739109L;
    
    /**
     * ��̗�O�C���X�^���X�𐶐�����B<p>
     */
    public InputExchangeException(){
        super();
    }
    
    /**
     * �w�肳�ꂽ���b�Z�[�W����������O�C���X�^���X�𐶐�����B<p>
     *
     * @param message ���b�Z�[�W
     */
    public InputExchangeException(String message){
        super(message);
    }
    
    /**
     * ���̗�O�̌����ƂȂ�����O���������C���X�^���X�𐶐�����R���X�g���N�^�B<p>
     *
     * @param cause �����ƂȂ�����O
     */
    public InputExchangeException(Throwable cause){
        super(cause);
    }
    
    /**
     * �G���[���b�Z�[�W�ƁA���̗�O�̌����ƂȂ�����O���������C���X�^���X�𐶐�����R���X�g���N�^�B<p>
     *
     * @param message �G���[���b�Z�[�W
     * @param cause �����ƂȂ�����O
     */
    public InputExchangeException(String message, Throwable cause){
        super(message, cause);
    }
}
