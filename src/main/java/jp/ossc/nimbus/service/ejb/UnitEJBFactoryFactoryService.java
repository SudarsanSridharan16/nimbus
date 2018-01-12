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
package jp.ossc.nimbus.service.ejb;

import java.util.*;
import jp.ossc.nimbus.core.*;

/**
 * �P��EJB�t�@�N�g���t�@�N�g���B<p>
 * {@link UnitEJBFactoryService}�𐶐�����t�@�N�g���T�[�r�X�ł���B<br>
 *
 * @author M.Takata
 * @see UnitEJBFactoryService
 */
public class UnitEJBFactoryFactoryService
 extends InvocationEJBFactoryFactoryService
 implements UnitEJBFactoryFactoryServiceMBean{
    
    private static final long serialVersionUID = 4257700818893908192L;
    
    /**
     * {@link UnitEJBFactoryService}�T�[�r�X�𐶐�����B<p>
     *
     * @return UnitEJBFactory�T�[�r�X
     * @exception Exception UnitEJBFactory�̐����E�N���Ɏ��s�����ꍇ
     * @see UnitEJBFactoryService
     */
    protected Service createServiceInstance() throws Exception{
        UnitEJBFactoryService ejbFactory = new UnitEJBFactoryService();
        ejbFactory.setRemoteCacheMapServiceName(
            getRemoteCacheMapServiceName()
        );
        ejbFactory.setJndiFinderServiceName(getJndiFinderServiceName());
        ejbFactory.setRemoteCacheMapServiceName(getRemoteCacheMapServiceName());
        ejbFactory.setJndiFinderServiceName(getJndiFinderServiceName());
        ejbFactory.setHomeType(getHomeType());
        ejbFactory.setLocalHomeType(getLocalHomeType());
        ejbFactory.setRemoteType(getRemoteType());
        ejbFactory.setLocalType(getLocalType());
        ejbFactory.setCreateMethodParamTypes(getCreateMethodParamTypes());
        return ejbFactory;
    }
    
    protected synchronized InvocationEJBFactoryService getTemplate(){
        if(template == null){
            template = new UnitEJBFactoryService();
        }
        return template;
    }
    
    // UnitEJBFactoryFactoryMBean��JavaDoc
    public void setHomeType(String className){
        ((UnitEJBFactoryService)getTemplate()).setHomeType(className);
        final Set instanceSet = getManagedInstanceSet();
        final Iterator instances = instanceSet.iterator();
        while(instances.hasNext()){
            final UnitEJBFactoryService ejbFactory
                 = (UnitEJBFactoryService)instances.next();
            ejbFactory.setHomeType(className);
        }
    }
    
    // UnitEJBFactoryFactoryMBean��JavaDoc
    public String getHomeType(){
        return ((UnitEJBFactoryService)getTemplate()).getHomeType();
    }
    
    // UnitEJBFactoryFactoryMBean��JavaDoc
    public void setLocalHomeType(String className){
        ((UnitEJBFactoryService)getTemplate()).setLocalHomeType(className);
        final Set instanceSet = getManagedInstanceSet();
        final Iterator instances = instanceSet.iterator();
        while(instances.hasNext()){
            final UnitEJBFactoryService ejbFactory
                 = (UnitEJBFactoryService)instances.next();
            ejbFactory.setLocalHomeType(className);
        }
    }
    
    // UnitEJBFactoryFactoryMBean��JavaDoc
    public String getLocalHomeType(){
        return ((UnitEJBFactoryService)getTemplate()).getLocalHomeType();
    }
    
    // UnitEJBFactoryFactoryMBean��JavaDoc
    public void setRemoteType(String className){
        ((UnitEJBFactoryService)getTemplate()).setRemoteType(className);
        final Set instanceSet = getManagedInstanceSet();
        final Iterator instances = instanceSet.iterator();
        while(instances.hasNext()){
            final UnitEJBFactoryService ejbFactory
                 = (UnitEJBFactoryService)instances.next();
            ejbFactory.setRemoteType(className);
        }
    }
    
    // UnitEJBFactoryFactoryMBean��JavaDoc
    public String getRemoteType(){
        return ((UnitEJBFactoryService)getTemplate()).getRemoteType();
    }
    
    // UnitEJBFactoryFactoryMBean��JavaDoc
    public void setLocalType(String className){
        ((UnitEJBFactoryService)getTemplate()).setLocalType(className);
        final Set instanceSet = getManagedInstanceSet();
        final Iterator instances = instanceSet.iterator();
        while(instances.hasNext()){
            final UnitEJBFactoryService ejbFactory
                 = (UnitEJBFactoryService)instances.next();
            ejbFactory.setLocalType(className);
        }
    }
    
    // UnitEJBFactoryFactoryMBean��JavaDoc
    public String getLocalType(){
        return ((UnitEJBFactoryService)getTemplate()).getLocalType();
    }
    
    // UnitEJBFactoryFactoryMBean��JavaDoc
    public void setCreateMethodParamTypes(String[] params){
        ((UnitEJBFactoryService)getTemplate())
            .setCreateMethodParamTypes(params);
        final Set instanceSet = getManagedInstanceSet();
        final Iterator instances = instanceSet.iterator();
        while(instances.hasNext()){
            final UnitEJBFactoryService ejbFactory
                 = (UnitEJBFactoryService)instances.next();
            ejbFactory.setCreateMethodParamTypes(params);
        }
    }
    
    // UnitEJBFactoryFactoryMBean��JavaDoc
    public String[] getCreateMethodParamTypes(){
        return ((UnitEJBFactoryService)getTemplate())
            .getCreateMethodParamTypes();
    }
}
