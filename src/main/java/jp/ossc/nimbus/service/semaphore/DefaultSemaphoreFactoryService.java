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
package jp.ossc.nimbus.service.semaphore;

import jp.ossc.nimbus.core.*;
import jp.ossc.nimbus.lang.ServiceException;

/**
 * �Z�}�t�H�t�@�N�g���T�[�r�X�B<p>
 * 
 * @author H.Nakano
 */
public class DefaultSemaphoreFactoryService extends ServiceBase
 implements SemaphoreFactory, DefaultSemaphoreFactoryServiceMBean {
    
    private static final long serialVersionUID = 3492118724467267733L;
    
    /** �C���v�������g�N���X�� */
    protected String mImplClassName = MemorySemaphore.class.getName();
    
    /** �C���v�������g�N���X�� */
    protected Class mClassObj = MemorySemaphore.class;
    
    /** ��O���b�Z�[�W */
    static final String C_EXCPT = "SEMAPHOREFACTORY"; //$NON-NLS-1$
    
    // SemaphoreFactory��JavaDoc
    public Semaphore createSemaphore() throws ServiceException {
        try{
            synchronized(mImplClassName){
                return (Semaphore)mClassObj.newInstance() ;
            }
        }catch(Exception e){
            throw new ServiceException(
                C_EXCPT + "001",
                "CLASS_NOT_INSTANCE",
                e
            ); //$NON-NLS-1$ //$NON-NLS-2$
        }
    }
    
    // SemaphoreFactory��JavaDoc
    public Semaphore createSemaphore(int capa) throws ServiceException{
        try{
            Semaphore ret = null ;
            synchronized(mImplClassName){
                ret = (Semaphore)mClassObj.newInstance() ;
            }
            ret.setResourceCapacity(capa) ;
            return ret ;
        }catch(Exception e){
            throw new ServiceException(
                C_EXCPT + "002",
                "CLASS_NOT_INSTANCE",
                e
            ); //$NON-NLS-1$ //$NON-NLS-2$
        }
    }
    
    // DefaultSemaphoreFactoryServiceMBean��JavaDoc
    public void setImplementClassName(String className)
     throws ServiceException {
        synchronized(mImplClassName){
            mImplClassName = className;
            try{
                mClassObj = Class.forName(
                    mImplClassName,
                    true,
                    NimbusClassLoader.getInstance()
                );
            }catch(Exception e){
                throw new ServiceException(
                    C_EXCPT + "003",
                    "CLASS_NOT_FOUND",
                    e
                );  //$NON-NLS-1$//$NON-NLS-2$
            }
        }
    }
    
    // DefaultSemaphoreFactoryServiceMBean��JavaDoc
    public String getImplementClassName(){
        return mImplClassName;
    }
}
