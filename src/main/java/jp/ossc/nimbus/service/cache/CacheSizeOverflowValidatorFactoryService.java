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
package jp.ossc.nimbus.service.cache;

import java.util.*;

import jp.ossc.nimbus.core.*;

/**
 * �L���b�V���T�C�Y���ӂꌟ�؃t�@�N�g���B<p>
 * {@link CacheSizeOverflowValidatorService}�𐶐�����t�@�N�g���T�[�r�X�ł���B<br>
 *
 * @author M.Takata
 * @see CacheSizeOverflowValidatorService
 */
public class CacheSizeOverflowValidatorFactoryService
 extends ServiceFactoryServiceBase
 implements CacheSizeOverflowValidatorFactoryServiceMBean, java.io.Serializable{
    
    private static final long serialVersionUID = -2348982019212469124L;
    
    private final CacheSizeOverflowValidatorService template
         = new CacheSizeOverflowValidatorService();
    
    /**
     * {@link CacheSizeOverflowValidatorService}�T�[�r�X�𐶐�����B<p>
     *
     * @return CacheSizeOverflowValidatorService�T�[�r�X
     * @exception Exception CacheSizeOverflowValidatorService�̐����E�N���Ɏ��s�����ꍇ
     * @see CacheSizeOverflowValidatorService
     */
    protected Service createServiceInstance() throws Exception{
        final CacheSizeOverflowValidatorService validator
             = new CacheSizeOverflowValidatorService();
        validator.setMaxSize(template.getMaxSize());
        return validator;
    }
    
    // CacheSizeOverflowValidatorFactoryServiceMBean��JavaDoc
    public void setMaxSize(int size) throws IllegalArgumentException{
        template.setMaxSize(size);
        final Set instanceSet = getManagedInstanceSet();
        final Iterator instances = instanceSet.iterator();
        while(instances.hasNext()){
            final CacheSizeOverflowValidatorService validator
                 = (CacheSizeOverflowValidatorService)instances.next();
            validator.setMaxSize(size);
        }
    }
    
    // CacheSizeOverflowValidatorFactoryServiceMBean��JavaDoc
    public int getMaxSize(){
        return template.getMaxSize();
    }
    
    // CacheSizeOverflowValidatorFactoryServiceMBean��JavaDoc
    public void reset(){
        final Set instanceSet = getManagedInstanceSet();
        final Iterator instances = instanceSet.iterator();
        while(instances.hasNext()){
            final CacheSizeOverflowValidatorService validator
                 = (CacheSizeOverflowValidatorService)instances.next();
            validator.reset();
        }
    }
}
