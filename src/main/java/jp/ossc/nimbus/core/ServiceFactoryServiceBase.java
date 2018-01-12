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
 * �T�[�r�X�t�@�N�g���T�[�r�X���N���X�B<p>
 * {@link FactoryService}�̒��ł��A�T�[�r�X�𐶐�����FactoryService����������ۂ̊��N���X�ł���B<p>
 *
 * @author M.Takata
 */
public abstract class ServiceFactoryServiceBase extends FactoryServiceBase{
    
    private static final long serialVersionUID = 3957308112143950640L;
    
    /**
     * �R���X�g���N�^�B<p>
     */
    public ServiceFactoryServiceBase(){
        super();
    }
    
    public void release(Object obj){
        final Service service = (Service)obj;
        service.stop();
        service.destroy();
        super.release(service);
    }
    
    /**
     * ���̃t�@�N�g�����񋟂���I�u�W�F�N�g�̃C���X�^���X�𐶐�����B<p>
     * {@link #createServiceInstance()}�Ő��������T�[�r�X�̃C���X�^���X�ɑ΂��āA�ȉ��̏������s���B<br>
     * <ol>
     *   <li>{@link #isManagement()}��true�̏ꍇ�A���������T�[�r�X�ɃT�[�r�X���ƃT�[�r�X�}�l�[�W������ݒ肷��B���̍ہA�T�[�r�X���́A���̃t�@�N�g���T�[�r�X�̃T�[�r�X���̌���"$" + "�Ǘ�����Ă��鐶�������T�[�r�X�̒ʂ��ԍ�"��t�^�������̂ł���B�܂��A�T�[�r�X�}�l�[�W�����́A���̃t�@�N�g���T�[�r�X�̃T�[�r�X�}�l�[�W�����Ɠ����ł���B</li>
     *   <li>���������T�[�r�X�̐��������i{@link Service#create()}�j�B</li>
     *   <li>���������T�[�r�X�̊J�n�����i{@link Service#start()}�j�B</li>
     *   <li>���������T�[�r�X��{@link ServiceBase}���p�����Ă���ꍇ�́A���̃t�@�N�g���T�[�r�X�ɐݒ肳��Ă���{@link jp.ossc.nimbus.service.log.Logger Logger}��{@link jp.ossc.nimbus.service.message.MessageRecordFactory MessageRecordFactory}���A���������T�[�r�X�ɂ��ݒ肷��B</li>
     * </ol>
     *
     * @return ���̃t�@�N�g�����񋟂���I�u�W�F�N�g�̃C���X�^���X
     * @exception Exception �������ɗ�O�����������ꍇ
     */
    protected final Object createInstance() throws Exception{
        return createInstance(false);
    }
    
    protected Object createTemplate() throws Exception{
        return createInstance(true);
    }
    
    protected final Object createInstance(boolean isTemplate) throws Exception{
        final Service service = createServiceInstance();
        if(service == null){
            return null;
        }
        if(!isTemplate && isManagement() && getServiceManagerName() != null){
            service.setServiceName(
                getServiceName() + '$' + getManagedInstanceSet().size()
            );
            service.setServiceManagerName(getServiceManagerName());
        }
        if(service.getState() == DESTROYED){
            service.create();
        }
        if(service instanceof ServiceBase){
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
        if(service.getState() == CREATED){
            service.start();
        }
        return service;
    }
    
    /**
     * ���̃t�@�N�g�����񋟂���T�[�r�X�̃C���X�^���X�𐶐�����B<p>
     *
     * @return ���̃t�@�N�g�����񋟂���T�[�r�X�̃C���X�^���X
     * @exception Exception �������ɗ�O�����������ꍇ
     */
    protected abstract Service createServiceInstance() throws Exception;
    
    /**
     * ���̃t�@�N�g���̊J�n�㏈�����s���B<p>
     * {@link #getManagedInstanceSet()}���Ăяo���āA���̃t�@�N�g�����Ǘ����Ă���T�[�r�X���擾���A�J�n������B<br>
     * 
     * @exception Exception ���̃t�@�N�g�����Ǘ����Ă���T�[�r�X�̊J�n�Ɏ��s�����ꍇ
     */
    protected void postStartService() throws Exception{
        
        super.postStartService();
        
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
     * ���̃t�@�N�g���̒�~�㏈�����s���B<p>
     * {@link #getManagedInstanceSet()}���Ăяo���āA���̃t�@�N�g�����Ǘ����Ă���T�[�r�X���擾���A��~������B<br>
     * 
     * @exception Exception ���̃t�@�N�g�����Ǘ����Ă���T�[�r�X�̒�~�Ɏ��s�����ꍇ
     */
    protected void postStopService() throws Exception{
        final Set managedInstances = getManagedInstanceSet();
        if(managedInstances != null){
            final Iterator services = managedInstances.iterator();
            while(services.hasNext()){
                final Service service = (Service)services.next();
                service.stop();
            }
        }
        super.postStopService();
    }
    
    /**
     * ���̃t�@�N�g���̔j���㏈�����s���B<p>
     * {@link #getManagedInstanceSet()}���Ăяo���āA���̃t�@�N�g�����Ǘ����Ă���T�[�r�X���擾���A�j��������B<br>
     * 
     * @exception Exception ���̃t�@�N�g�����Ǘ����Ă���T�[�r�X�̔j���Ɏ��s�����ꍇ
     */
    protected void postDestroyService() throws Exception{
        final Set managedInstances = getManagedInstanceSet();
        if(managedInstances != null){
            final Iterator services = managedInstances.iterator();
            while(services.hasNext()){
                final Service service = (Service)services.next();
                service.destroy();
            }
        }
        super.postDestroyService();
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
}
