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
package jp.ossc.nimbus.service.sequence;

import jp.ossc.nimbus.core.*;
import jp.ossc.nimbus.service.context.Context;

/**
 * Context�����Ǘ��N���X�B<p>
 * {@link Context}�T�[�r�X����l���擾���Č��ݒl�Ƃ���B
 * 
 * @author M.Takata
 */
public class ContextSequenceVariable
 implements SequenceVariable, java.io.Serializable{
    
    private static final long serialVersionUID = -1199668787624672114L;
    
    public static final char DELIMITER = '%';
    private static final String EMPTY = "";
    
    private String key;
    private ServiceName contextServiceName;
    
    /**
     * �R���X�g���N�^�B<p>
     *
     * @param key �R���e�L�X�g�L�[�B%�R���e�L�X�g�L�[%�Ŏw�肷��
     * @param context {@link Context}�T�[�r�X�̃T�[�r�X��
     */
    public ContextSequenceVariable(
        String key,
        ServiceName context
    ){
        this.key = key.substring(1, key.length() - 1);
        contextServiceName = context;
    }
    
    /**
     * �������Ȃ��B<p>
     *
     * @return �K��true
     */
    public boolean increment(){
        return true;
    }
    
    /**
     * �������Ȃ��B<p>
     */
    public void clear(){
    }
    
    /**
     * ���ݒl���擾����B<p>
     * {@link Context}�T�[�r�X����l���擾���Č��ݒl�Ƃ���B
     *
     * @return ���ݒl
     */
    public String getCurrent(){
        if(contextServiceName == null){
            return EMPTY;
        }
        final Context context = (Context)ServiceManagerFactory
            .getServiceObject(contextServiceName);
        final Object val = context.get(key);
        return val == null ? EMPTY : val.toString();
    }
}
