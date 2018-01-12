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
 * ���ۃL���b�V���t�@�N�g���B<p>
 * {@link AbstractCacheService}�𐶐�����t�@�N�g���T�[�r�X�ł���B<br>
 *
 * @author M.Takata
 * @see AbstractCacheService
 */
public abstract class AbstractCacheFactoryService
 extends ServiceFactoryServiceBase
 implements AbstractCacheFactoryServiceMBean{
    
    private static final long serialVersionUID = 5428773891480587972L;
    
    private AbstractCacheService template;;
    
    /**
     * {@link AbstractCacheService}�T�[�r�X�𐶐�����B<p>
     *
     * @return AbstractCacheService�T�[�r�X
     * @exception Exception AbstractCacheService�̐����E�N���Ɏ��s�����ꍇ
     * @see AbstractCacheService
     */
    protected abstract AbstractCacheService createAbstractCacheService()
     throws Exception;
    
    /**
     * {@link AbstractCacheService}�T�[�r�X�𐶐�����B<p>
     *
     * @return AbstractCacheService�T�[�r�X
     * @exception Exception AbstractCacheService�̐����E�N���Ɏ��s�����ꍇ
     * @see AbstractCacheService
     */
    protected Service createServiceInstance() throws Exception{
        final AbstractCacheService templateCache = getTemplate();
        if(templateCache == null){
            return null;
        }
        final AbstractCacheService cache
             = createAbstractCacheService();
        if(cache == null){
            return null;
        }
        cache.setOverflowControllerServiceNames(
            templateCache.getOverflowControllerServiceNames()
        );
        cache.setClearOnStop(templateCache.isClearOnStop());
        cache.setClearOnDestroy(templateCache.isClearOnDestroy());
        return cache;
    }
    
    // AbstractCacheFactoryServiceMBean��JavaDoc
    public void setOverflowControllerServiceNames(ServiceName[] names){
        final AbstractCacheService templateCache = getTemplate();
        if(templateCache == null){
            return;
        }
        templateCache.setOverflowControllerServiceNames(names);
        final Set instanceSet = getManagedInstanceSet();
        final Iterator instances = instanceSet.iterator();
        while(instances.hasNext()){
            final AbstractCacheService cache
                 = (AbstractCacheService)instances.next();
            cache.setOverflowControllerServiceNames(names);
        }
    }
    
    // AbstractCacheFactoryServiceMBean��JavaDoc
    public ServiceName[] getOverflowControllerServiceNames(){
        final AbstractCacheService templateCache = getTemplate();
        if(templateCache == null){
            return null;
        }
        return templateCache.getOverflowControllerServiceNames();
    }
    
    // AbstractCacheFactoryServiceMBean��JavaDoc
    public void setClearOnStop(boolean isClear){
        final AbstractCacheService templateCache = getTemplate();
        if(templateCache == null){
            return;
        }
        templateCache.setClearOnStop(isClear);
        final Set instanceSet = getManagedInstanceSet();
        final Iterator instances = instanceSet.iterator();
        while(instances.hasNext()){
            final AbstractCacheService cache
                 = (AbstractCacheService)instances.next();
            cache.setClearOnStop(isClear);
        }
    }
    
    // AbstractCacheFactoryServiceMBean��JavaDoc
    public boolean isClearOnStop(){
        final AbstractCacheService templateCache = getTemplate();
        if(templateCache == null){
            return false;
        }
        return templateCache.isClearOnStop();
    }
    
    // AbstractCacheFactoryServiceMBean��JavaDoc
    public void setClearOnDestroy(boolean isClear){
        final AbstractCacheService templateCache = getTemplate();
        if(templateCache == null){
            return;
        }
        templateCache.setClearOnDestroy(isClear);
        final Set instanceSet = getManagedInstanceSet();
        final Iterator instances = instanceSet.iterator();
        while(instances.hasNext()){
            final AbstractCacheService cache
                 = (AbstractCacheService)instances.next();
            cache.setClearOnDestroy(isClear);
        }
    }
    
    // AbstractCacheFactoryServiceMBean��JavaDoc
    public boolean isClearOnDestroy(){
        final AbstractCacheService templateCache = getTemplate();
        if(templateCache == null){
            return false;
        }
        return templateCache.isClearOnDestroy();
    }
    
    // AbstractCacheFactoryServiceMBean��JavaDoc
    public void clear(){
        final Set instanceSet = getManagedInstanceSet();
        final Iterator instances = instanceSet.iterator();
        while(instances.hasNext()){
            final AbstractCacheService cache
                 = (AbstractCacheService)instances.next();
            cache.clear();
        }
    }
    
    /**
     * �e���v���[�g�ƂȂ�L���b�V���T�[�r�X���擾����B<p>
     *
     * @return �e���v���[�g�ƂȂ�L���b�V���T�[�r�X
     */
    protected synchronized AbstractCacheService getTemplate(){
        if(template == null){
            try{
                template = createAbstractCacheService();
            }catch(Exception e){
                return null;
            }
        }
        return template;
    }
}
