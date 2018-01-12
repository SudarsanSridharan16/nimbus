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
 * �ėp�T�[�r�X�t�@�N�g���T�[�r�X�B<p>
 *
 * @author M.Takata
 */
public class GenericsServiceFactoryService extends GenericsFactoryService{
    
    private static final long serialVersionUID = 2328082601184640004L;
    
    /**
     * ���̃t�@�N�g���̊J�n�������s���B<p>
     * {@link #getManagedInstanceSet()}���Ăяo���āA���̃t�@�N�g�����Ǘ����Ă���T�[�r�X���擾���A�J�n������B<br>
     * 
     * @exception Exception ���̃t�@�N�g�����Ǘ����Ă���T�[�r�X�̊J�n�Ɏ��s�����ꍇ
     */
    public void startService() throws Exception{
        
        super.startService();
        
        final Set managedInstances = getManagedInstanceSet();
        if(managedInstances != null){
            final Iterator services = managedInstances.iterator();
            while(services.hasNext()){
                final Service service = (Service)services.next();
                service.start();
            }
        }
    }
    
    /**
     * ���̃t�@�N�g���̒�~�������s���B<p>
     * {@link #getManagedInstanceSet()}���Ăяo���āA���̃t�@�N�g�����Ǘ����Ă���T�[�r�X���擾���A��~������B<br>
     * 
     * @exception Exception ���̃t�@�N�g�����Ǘ����Ă���T�[�r�X�̒�~�Ɏ��s�����ꍇ
     */
    public void stopService() throws Exception{
        final Set managedInstances = getManagedInstanceSet();
        if(managedInstances != null){
            final Iterator services = managedInstances.iterator();
            while(services.hasNext()){
                final Service service = (Service)services.next();
                service.stop();
            }
        }
        super.stopService();
    }
    
    /**
     * ���̃t�@�N�g���̔j���������s���B<p>
     * {@link #getManagedInstanceSet()}���Ăяo���āA���̃t�@�N�g�����Ǘ����Ă���T�[�r�X���擾���A�j��������B<br>
     * 
     * @exception Exception ���̃t�@�N�g�����Ǘ����Ă���T�[�r�X�̔j���Ɏ��s�����ꍇ
     */
    public void destroyService() throws Exception{
        final Set managedInstances = getManagedInstanceSet();
        if(managedInstances != null){
            final Iterator services = managedInstances.iterator();
            while(services.hasNext()){
                final Service service = (Service)services.next();
                service.destroy();
            }
        }
        super.destroyService();
    }
    
    /**
     * Service���̃��O�o�͂Ɏg�p����{@link jp.ossc.nimbus.service.log.Logger}�T�[�r�X�̖��O��ݒ肷��B<p>
     * {@link #getManagedInstanceSet()}���Ăяo���āA���̃t�@�N�g�����Ǘ����Ă���T�[�r�X���擾���A�T�[�r�X��{@link ServiceBase}�̃C���X�^���X�ł���΁A{@link ServiceBase#setSystemLoggerServiceName(ServiceName)}���Ăяo���B<br>
     *
     * @param name Service���̃��O�o�͂Ɏg�p����{@link jp.ossc.nimbus.service.log.Logger}�T�[�r�X�̖��O
     * @see #getSystemLoggerServiceName()
     */
    public void setSystemLoggerServiceName(ServiceName name){
        super.setSystemLoggerServiceName(name);
        final Set managedInstances = getManagedInstanceSet();
        if(managedInstances != null){
            final Iterator services = managedInstances.iterator();
            while(services.hasNext()){
                final Service service = (Service)services.next();
                if(service instanceof ServiceBase){
                    ((ServiceBase)service).setSystemLoggerServiceName(name);
                }
            }
        }
    }
    
    /**
     * Service���ł̃��b�Z�[�W�擾�Ɏg�p����{@link jp.ossc.nimbus.service.message.MessageRecordFactory}�T�[�r�X�̖��O��ݒ肷��B<p>
     * {@link #getManagedInstanceSet()}���Ăяo���āA���̃t�@�N�g�����Ǘ����Ă���T�[�r�X���擾���A�T�[�r�X��{@link ServiceBase}�̃C���X�^���X�ł���΁A{@link ServiceBase#setSystemMessageRecordFactoryServiceName(ServiceName)}���Ăяo���B<br>
     *
     * @param name Service���ł̃��b�Z�[�W�擾�Ɏg�p����{@link jp.ossc.nimbus.service.message.MessageRecordFactory}�T�[�r�X�̖��O
     * @see #getSystemMessageRecordFactoryServiceName()
     */
    public void setSystemMessageRecordFactoryServiceName(
        final ServiceName name
    ){
        super.setSystemMessageRecordFactoryServiceName(name);
        final Set managedInstances = getManagedInstanceSet();
        if(managedInstances != null){
            final Iterator services = managedInstances.iterator();
            while(services.hasNext()){
                final Service service = (Service)services.next();
                if(service instanceof ServiceBase){
                    ((ServiceBase)service)
                        .setSystemMessageRecordFactoryServiceName(name);
                }
            }
        }
    }
    
    /**
     * ���̃t�@�N�g�����񋟂���I�u�W�F�N�g�̃C���X�^���X�𐶐�����B<p>
     *
     * @return ���̃t�@�N�g�����񋟂���I�u�W�F�N�g�̃C���X�^���X
     * @exception Exception �������ɗ�O�����������ꍇ
     */
    protected Object createInstance() throws Exception{
        final Service service = (Service)instantiateClass.newInstance();
        if(service == null){
            return null;
        }
        if(isManagement() && getServiceManagerName() != null){
            service.setServiceName(
                getServiceName() + '$' + getManagedInstanceSet().size()
            );
            service.setServiceManagerName(getServiceManagerName());
        }else  if(service instanceof ServiceBase){
            final ServiceBase base = (ServiceBase)service;
            if(manager != null){
                base.logger.setDefaultLogger(manager.getLogger());
                if(getSystemLoggerServiceName() == null){
                    base.logger.setLogger(manager.getLogger());
                }
                base.message.setDefaultMessageRecordFactory(
                    manager.getMessageRecordFactory()
                );
                if(getSystemMessageRecordFactoryServiceName() == null){
                    base.message.setMessageRecordFactory(
                        manager.getMessageRecordFactory()
                    );
                }
            }
            if(getSystemLoggerServiceName() != null){
                base.setSystemLoggerServiceName(
                    getSystemLoggerServiceName()
                );
            }
            if(getSystemMessageRecordFactoryServiceName() != null){
                base.setSystemMessageRecordFactoryServiceName(
                    getSystemMessageRecordFactoryServiceName()
                );
            }
        }
        service.create();
        setAttributes(service);
        service.start();
        return service;
    }
    
    public void release(Object obj){
        final Service service = (Service)obj;
        service.stop();
        service.destroy();
        super.release(service);
    }
}