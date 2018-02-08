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
package jp.ossc.nimbus.service.context;

import java.util.*;

import jp.ossc.nimbus.core.*;

/**
 * �f�t�H���g�R���e�L�X�g�t�@�N�g���B<p>
 * {@link DefaultContextService}�𐶐�����t�@�N�g���T�[�r�X�ł���B<br>
 *
 * @author M.Takata
 * @see DefaultContextService
 */
public class DefaultContextFactoryService
 extends ServiceFactoryServiceBase
 implements DefaultContextFactoryServiceMBean{
    
    private static final long serialVersionUID = -9069810211539838348L;
    
    private final DefaultContextService template = new DefaultContextService();
    
    /**
     * {@link DefaultContextService}�T�[�r�X�𐶐�����B<p>
     *
     * @return DefaultContextService�T�[�r�X
     * @exception Exception DefaultContextService�̐����E�N���Ɏ��s�����ꍇ
     * @see DefaultContextService
     */
    protected Service createServiceInstance() throws Exception{
        final DefaultContextService context = new DefaultContextService();
        context.putAll(template);
        return context;
    }
    
    // DefaultContextFactoryServiceMBean��JavaDoc
    public void put(Object key, Object value){
        template.put(key, value);
        final Set instanceSet = getManagedInstanceSet();
        final Iterator instances = instanceSet.iterator();
        while(instances.hasNext()){
            final DefaultContextService context
                 = (DefaultContextService)instances.next();
            context.put(key, value);
        }
    }
    
    // DefaultContextFactoryServiceMBean��JavaDoc
    public void put(String key, String value){
        put(key, value);
    }
}