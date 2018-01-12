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

import jp.ossc.nimbus.service.log.Logger;
import jp.ossc.nimbus.service.repository.*;
import jp.ossc.nimbus.service.message.MessageRecordFactory;
/**
 * �T�[�r�X�Ǘ��C���^�t�F�[�X�B<p>
 * {@link Service}�C���X�^���X�ɖ��O��t���ĊǗ����A�񋟂���C���^�t�F�[�X�ł���B<br>
 * �T�[�r�X�̓o�^�A�o�^�����A�擾�Ȃǂ̃T�[�r�X��񋟂��郁�\�b�h�ƁA�T�[�r�X�̓o�^��ƂȂ�{@link Repository}��ݒ肷�郁�\�b�h�����B<br>
 * �܂��A{@link Service}�̃T�u�C���^�t�F�[�X�ƂȂ��Ă��邽�߁A�����N���X�̓T�[�r�X�Ƃ��Ď��������B�X�ɁA{@link RegistrationBroadcaster}�̃T�u�C���^�t�F�[�X�ƂȂ��Ă���A�����N���X�̓T�[�r�X�̓o�^�E�o�^������{@link RegistrationListener}�ɒʒm����ӔC�𕉂��B<br>
 *
 * @author M.Takata
 */
public interface ServiceManager
 extends ServiceBaseMBean, RegistrationBroadcaster{
    
    /**
     * ���̃T�[�r�X�̃f�t�H���g�̃T�[�r�X���B<p>
     */
    public static final String DEFAULT_NAME = "Nimbus";
    
    /**
     * �w�肵�����O�̃T�[�r�X���擾����B<p>
     * ���̃��\�b�h�Ŏ擾�ł���I�u�W�F�N�g�́A�T�[�r�X��񋟂���C���^�t�F�[�X�ɃL���X�g�ł���Ƃ͌���Ȃ��B<br>
     * �T�[�r�X�̐����E�N���E��~�E�j���Ȃǂ̑�����s�������ꍇ�ɁA���̃��\�b�h�ŃC���X�^���X���擾����B<br>
     *
     * @param name �T�[�r�X�̖��O
     * @return Service�I�u�W�F�N�g
     * @exception ServiceNotFoundException �T�[�r�X��������Ȃ��ꍇ
     */
    public Service getService(String name) throws ServiceNotFoundException;
    
    /**
     * �w�肵�����O�̃T�[�r�X��񋟂���I�u�W�F�N�g���擾����B<p>
     * ���̃��\�b�h�Ŏ擾�ł���I�u�W�F�N�g�́A�T�[�r�X��񋟂���C���^�t�F�[�X�ɃL���X�g�ł��鎖��ۏ؂���B<br>
     * �T�[�r�X�̐����E�N���E��~�E�j���Ȃǂ̑�����s�������ꍇ�́A{@link #getService(String)}�ŃC���X�^���X���擾����B<br>
     *
     * @param name �T�[�r�X�̖��O
     * @return Service�I�u�W�F�N�g
     * @exception ServiceNotFoundException �T�[�r�X��������Ȃ��ꍇ
     */
    public Object getServiceObject(String name) throws ServiceNotFoundException;
    
    /**
     * �w�肵�����O�̃T�[�r�X�̏�ԕύX��ʒm����{@link ServiceStateBroadcaster}���擾����B<p>
     * �o�^����Ă��Ȃ��T�[�r�X��ServiceStateBroadcaster�͎擾�ł��Ȃ��B<br>
     * �܂��A{@link Service}�C���^�t�F�[�X�𒼐ڎ��������T�[�r�X��o�^�����ꍇ�A���̃N���X��ServiceStateBroadcaster���������Ă��Ȃ��Ǝ擾�ł��Ȃ��B<br>
     *
     * @param name �T�[�r�X�̖��O
     * @return ServiceStateBroadcaster�I�u�W�F�N�g
     * @exception ServiceNotFoundException �T�[�r�X��������Ȃ��ꍇ
     */
    public ServiceStateBroadcaster getServiceStateBroadcaster(String name)
     throws ServiceNotFoundException;
    
    /**
     * �w�肵�����O�̃T�[�r�X�̒�`�����擾����B<p>
     * {@link ServiceLoader}�Ń��[�h�����T�[�r�X�̒�`�����擾����B<br>
     * 
     * @param name �T�[�r�X�̖��O
     * @return �T�[�r�X��`���
     * @exception ServiceNotFoundException �T�[�r�X��������Ȃ��ꍇ
     */
    public ServiceMetaData getServiceMetaData(String name)
     throws ServiceNotFoundException;
    
    /**
     * �T�[�r�X��o�^����B<p>
     * obj�Ɏw�肷��I�u�W�F�N�g��{@link Service}�C���^�t�F�[�X���������Ă��Ȃ��ꍇ�́AService�C���^�t�F�[�X�����������I�u�W�F�N�g�Ƀ��b�v����ēo�^�����B����ɂ��A�C�ӂ̃I�u�W�F�N�g���T�[�r�X�Ɠ��l�Ɉ��������ł���B<br>
     * �A���A�T�[�r�X�̎����Ɉˑ����ē���I�Ȑ����͔�������B�ȉ��ɁA�T�[�r�X�Ƃ��ēo�^����I�u�W�F�N�g���A��������������@�̏��ŁA��������B<br>
     * <ol>
     *   <li>{@link ServiceBase}�̃T�u�N���X<br>�����Ȃ��̃R���X�g���N�^�Ő��������B���[�h���ɁA{@link ServiceBase#setServiceName(String)}�A{@link ServiceBase#createService()}�A{@link ServiceBase#startService()}���Ăяo���B�܂��A{@link ServiceManager#create()}�A{@link ServiceManager#start()}�A{@link ServiceManager#stop()}�A{@link ServiceManager#destroy()}���Ăяo���ƁA�o�^����Ă���Service�̑Ή����郁�\�b�h���Ăяo���BServiceBase�́AJMX�ɑΉ����Ă���{@link ServiceBaseMBean}�C���^�t�F�[�X��MBean�C���^�t�F�[�X�Ƃ��Ď����Ă���B</li>
     *   <li>{@link FactoryServiceBase}�̃T�u�N���X<br>FactoryServiceBase�́AServiceBase�̃T�u�N���X�ł��邽�߁A��L��ServiceBase�̃T�u�N���X�ɏ�������B�A���A{@link ServiceManager#getServiceObject(String)}���Ăяo���ꂽ�ꍇ�AFactoryServiceBase�̎����N���X�̃C���X�^���X�͕Ԃ����ɁA{@link FactoryService#newInstance()}�Ő��������I�u�W�F�N�g��Ԃ��B</li>
     *  <li>{@link ServiceBaseSupport}�C���^�t�F�[�X�����N���X<br>�����Ȃ��̃R���X�g���N�^�Ő�������AServiceBase�N���X�Ń��b�v����āAServiceManager�ɓo�^�����B���[�h���ɁA{@link ServiceBase#createService()}�A{@link ServiceBase#startService()}���Ăяo���B�܂��AServiceManager.create()�AServiceManager.start()�AServiceManager.stop()�AServiceManager.destroy()���Ăяo���ƁAServiceManager�ɓo�^����Ă���ServiceBase�I�u�W�F�N�g��ʂ��āA{@link ServiceBaseSupport#createService()}�A{@link ServiceBaseSupport#startService()}�A{@link ServiceBaseSupport#stopService()}�A{@link ServiceBaseSupport#destroyService()}���Ăяo���B���b�p�[�ł���ServiceBase���AJMX�ɑΉ����Ă��邽�߁AServiceManager�ɓo�^�����I�u�W�F�N�g�́AServiceBaseMBean�C���^�t�F�[�X��MBean�C���^�t�F�[�X�Ƃ��Ď��B�܂��A{@link FactoryService}�C���^�t�F�[�X����������΁A��L��FactoryServiceBase�̃T�u�N���X�ɏ����������������B</li>
     *   <li>{@link Service}�C���^�t�F�[�X�����N���X<br>�����Ȃ��̃R���X�g���N�^�Ő��������B���[�h���ɁA{@link Service#setServiceName(String)}�A{@link Service#create()}�A{@link Service#start()}���Ăяo���B�܂��AServiceManager.create()�AServiceManager.start()�AServiceManager.stop()�AServiceManager.destroy()���Ăяo���ƁAService�̑Ή����郁�\�b�h���Ăяo���B�A���A{@link Service#getState()}�ɑ΂�������̐ӔC�𕉂��K�v������BJMX�ɂ́A�Ή����Ă��Ȃ��̂ŁA�Ή����邽�߂ɂ́A�Ǝ���MBean�������s���K�v������B</li>
     *   <li>��L�ȊO�̃N���X<br>�����Ȃ��̃R���X�g���N�^�Ő��������BServiceBaseSupport�C���^�t�F�[�X�Ń��b�v����A�X��ServiceBase�N���X�Ƀ��b�v����āAServiceManager�ɓo�^�����BService.create()�AService.start()�AService.stop()�AService.destroy()�Ɠ����V�O�j�`���̃��\�b�h�������Ă���ꍇ�́A��L��ServiceBase�̃T�u�N���X�Ɠ������������B����ȊO�̏ꍇ�́AServiceManager.create()���Ăяo���ƁAServiceManager�֎������g��o�^���鎖�ƁAServiceManager.destroy()���Ăяo���ƁAServiceManager���玩�����g���폜����B�܂��AService#getState()�́A{@link Service#CREATED}�A{@link Service#DESTROYED}�ȊO�̏�Ԃ�{@link Service#UNKNOWN}�ƂȂ�B</li>
     * </ol>
     *
     * @param name �T�[�r�X�̖��O
     * @param obj �T�[�r�X��񋟂���I�u�W�F�N�g
     * @exception Exception �T�[�r�X�̓o�^�����Ɏ��s�����ꍇ
     * @return �o�^�ł����ꍇtrue
     */
    public boolean registerService(String name, Object obj) throws Exception;
    
    /**
     * �w�肳�ꂽ�T�[�r�X���w�肳�ꂽ�T�[�r�X���œo�^����B<p>
     *
     * @param name �T�[�r�X�̖��O
     * @param service �T�[�r�X��񋟂���T�[�r�X�I�u�W�F�N�g
     * @exception Exception �T�[�r�X�̓o�^�����Ɏ��s�����ꍇ
     * @return �o�^�ł����ꍇtrue
     */
    public boolean registerService(String name, Service service) throws Exception;
    
    /**
     * �w�肳�ꂽ�T�[�r�X��`���^�f�[�^�ɏ]�����T�[�r�X���A�w�肳�ꂽ�T�[�r�X���ŃT�[�r�X�Ƃ��ēo�^����B<p>
     *
     * @param serviceData �T�[�r�X��`���^�f�[�^
     * @return �o�^�ł����ꍇtrue
     * @exception Exception �T�[�r�X�̃C���X�^���X���Ɏ��s�����ꍇ
     */
    public boolean registerService(ServiceMetaData serviceData) throws Exception;
    
    /**
     * �w�肳�ꂽ�T�[�r�X���̃T�[�r�X��o�^��������B<p>
     *
     * @param name �T�[�r�X��
     * @return �o�^�����ł����ꍇtrue
     */
    public boolean unregisterService(String name);
    
    /**
     * �w�肳�ꂽ�T�[�r�X���̃T�[�r�X���o�^����Ă��邩���ׂ�B<p>
     *
     * @param name �T�[�r�X��
     * @return �o�^����Ă����ꍇtrue
     */
    public boolean isRegisteredService(String name);
    
    /**
     * �o�^����Ă���T�[�r�X���O�̏W�����擾����B<p>
     *
     * @return �o�^����Ă���T�[�r�X���̏W��
     */
    public Set serviceNameSet();
    
    /**
     * �o�^����Ă���{@link Service}�̏W�����擾����B<p>
     *
     * @return �o�^����Ă���Service�̏W��
     */
    public Set serviceSet();
    
    /**
     * �o�^����Ă���T�[�r�X�I�u�W�F�N�g�̏W�����擾����B<p>
     *
     * @return �o�^����Ă���T�[�r�X�I�u�W�F�N�g�̏W��
     */
    public Set serviceObjectSet();
    
    /**
     * ���̃}�l�[�W���ŊǗ�����T�[�r�X�̓o�^���{@link Repository}��ݒ肷��B<p>
     * Repository�T�[�r�X�̒�~���ɁA�����I�Ƀf�t�H���g�̃��|�W�g���ɐ؂�ւ��܂��B<br>
     *
     * @param manager Repository�C���^�t�F�[�X�����������T�[�r�X���o�^����Ă���}�l�[�W����
     * @param service Repository�C���^�t�F�[�X�����������T�[�r�X�̃T�[�r�X��
     * @return �o�^���Ă���T�[�r�X���w�肳�ꂽRepository�ɑS�ēo�^���������ꍇ�Atrue
     */
    public boolean setServiceRepository(String manager, String service);
    
    /**
     * ���̃}�l�[�W���ŊǗ�����T�[�r�X�̓o�^���{@link Repository}��ݒ肷��B<p>
     *
     * @param repository Repository�C���^�t�F�[�X�����������I�u�W�F�N�g
     * @return �o�^���Ă���T�[�r�X���w�肳�ꂽRepository�ɑS�ēo�^���������ꍇ�Atrue
     */
    public boolean setServiceRepository(Repository repository);
    
    /**
     * �w�肳�ꂽ�I�u�W�F�N�g��{@link Service}�C���^�t�F�[�X�����������I�u�W�F�N�g�ɕϊ�����B<p>
     * obj�Ŏw�肳�ꂽ�I�u�W�F�N�g���AService�C���^�t�F�[�X����������ꍇ�ɂ́A���̂܂ܕԂ��B<br>
     *
     * @param name �T�[�r�X��
     * @param obj Service�ɕϊ��������I�u�W�F�N�g
     * @return Service�C���^�t�F�[�X�����������I�u�W�F�N�g
     */
    public Service convertObjectToService(String name, Object obj);
    
    /**
     * ���̃}�l�[�W���ɊǗ������T�[�r�X�����[�h����{@link ServiceLoader}��o�^����B<p>
     *
     * @param loader ���̃}�l�[�W���ɊǗ������T�[�r�X�����[�h����ServiceLoader
     */
    public void addServiceLoader(ServiceLoader loader);
    
    /**
     * ���̃}�l�[�W���ɊǗ������T�[�r�X�����[�h����{@link ServiceLoader}���폜����B<p>
     *
     * @param loader ���̃}�l�[�W���ɊǗ������T�[�r�X�����[�h����ServiceLoader
     */
    public void removeServiceLoader(ServiceLoader loader);
    
    /**
     * ���̃}�l�[�W���ɊǗ������T�[�r�X�����[�h����{@link ServiceLoader}�̏W�����擾����B<p>
     * 
     * @return ���̃}�l�[�W���ɊǗ������T�[�r�X�����[�h����{@link ServiceLoader}�̏W��
     */
    public Set getServiceLoaders();
    
    /**
     * ���̃}�l�[�W����{@link ManagerMetaData}�̏W�����擾����B<p>
     * 
     * @return ���̃}�l�[�W����{@link ManagerMetaData}�̏W��
     */
    public Set getManagerMetaDatas();
    
    /**
     * �}�l�[�W���v���p�e�B���擾����B<p>
     *
     * @param name �v���p�e�B��
     * @return �v���p�e�B�l
     */
    public String getProperty(String name);
    
    /**
     * �w�肳�ꂽ�T�[�r�X���C���X�^���X������B<p>
     *
     * @param data �T�[�r�X��`���
     * @return �C���X�^���X�������T�[�r�X
     * @exception Exception ���������Ɏ��s�����ꍇ
     */
    public Service instanciateService(ServiceMetaData data) throws Exception;
    
    /**
     * �w�肳�ꂽ�I�u�W�F�N�g�𐶐�����B<p>
     *
     * @param data �T�[�r�X��`���
     * @return ���������I�u�W�F�N�g
     * @exception Exception ���������Ɏ��s�����ꍇ
     */
    public Object createObject(ServiceMetaData data) throws Exception;
    
    /**
     * �w�肳�ꂽ�T�[�r�X�𐶐�����B<p>
     *
     * @param name �T�[�r�X��
     * @exception Exception ���������Ɏ��s�����ꍇ
     */
    public void createService(String name) throws Exception;
    
    /**
     * �w�肳�ꂽ�T�[�r�X�𐶐�����B<p>
     *
     * @param name �T�[�r�X��
     * @param completed �������ꂽ�T�[�r�X���̏W���B�ˑ��֌W�ɂ�萶�����ꂽ�T�[�r�X���܂ށB
     * @exception Exception ���������Ɏ��s�����ꍇ
     */
    public void createService(String name, Set completed) throws Exception;
    
    /**
     * �w�肳�ꂽ�T�[�r�X�W���𐶐�����B<p>
     *
     * @param names �T�[�r�X���̏W��
     */
    public void createService(Set names);
    
    /**
     * �w�肳�ꂽ�o�^����Ă��Ȃ��T�[�r�X�𐶐�����B<p>
     * �ˑ��֌W�̉����͍s��Ȃ��B
     *
     * @param service �o�^����Ă��Ȃ��T�[�r�X
     * @param serviceData �T�[�r�X�̒�`���
     * @exception Exception �T�[�r�X�̊J�n�Ɏ��s�����ꍇ
     */
    public void createService(Service service, ServiceMetaData serviceData)
     throws Exception;
    
    /**
     * �o�^����Ă���S�ẴT�[�r�X�𐶐�����B<p>
     * �A���A���ɐ�������Ă���T�[�r�X�́A��������Ȃ��B<br>
     */
    public void createAllService();
    
    /**
     * �w�肳�ꂽ�T�[�r�X���J�n����B<p>
     *
     * @param name �T�[�r�X��
     * @exception Exception �J�n�����Ɏ��s�����ꍇ
     */
    public void startService(String name) throws Exception;
    
    /**
     * �w�肳�ꂽ�T�[�r�X���J�n����B<p>
     *
     * @param name �T�[�r�X��
     * @param completed �J�n���ꂽ�T�[�r�X���̏W���B�ˑ��֌W�ɂ��J�n���ꂽ�T�[�r�X���܂ށB
     * @exception Exception �J�n�����Ɏ��s�����ꍇ
     */
    public void startService(String name, Set completed) throws Exception;
    
    /**
     * �w�肳�ꂽ�T�[�r�X�W�����J�n����B<p>
     *
     * @param names �T�[�r�X���̏W��
     */
    public void startService(Set names);
    
    /**
     * �w�肳�ꂽ�o�^����Ă��Ȃ��T�[�r�X���J�n����B<p>
     * �ˑ��֌W�̉����͍s��Ȃ��B
     *
     * @param service �o�^����Ă��Ȃ��T�[�r�X
     * @param serviceData �T�[�r�X�̒�`���
     * @exception Exception �T�[�r�X�̊J�n�Ɏ��s�����ꍇ
     */
    public void startService(Service service, ServiceMetaData serviceData)
     throws Exception;
    
    /**
     * �o�^����Ă���S�ẴT�[�r�X���J�n����B<p>
     * �A���A���ɊJ�n����Ă���T�[�r�X�́A�J�n����Ȃ��B<br>
     */
    public void startAllService();
    
    /**
     * �w�肳�ꂽ�T�[�r�X���ĊJ�n����B<p>
     *
     * @param name �T�[�r�X��
     * @exception Exception �ĊJ�n�����Ɏ��s�����ꍇ
     */
    public void restartService(String name) throws Exception;
    
    /**
     * �w�肳�ꂽ�T�[�r�X���ĊJ�n����B<p>
     *
     * @param name �T�[�r�X��
     * @param completed �ĊJ�n���ꂽ�T�[�r�X���̏W���B�ˑ��֌W�ɂ��ĊJ�n���ꂽ�T�[�r�X���܂ށB
     * @exception Exception �ĊJ�n�����Ɏ��s�����ꍇ
     */
    public void restartService(String name, Set completed) throws Exception;
    
    /**
     * �w�肳�ꂽ�T�[�r�X�W�����ĊJ�n����B<p>
     *
     * @param names �T�[�r�X���̏W��
     */
    public void restartService(Set names);
    
    /**
     * �w�肳�ꂽ�o�^����Ă��Ȃ��T�[�r�X���ĊJ�n����B<p>
     * �ˑ��֌W�̉����͍s��Ȃ��B
     *
     * @param service �o�^����Ă��Ȃ��T�[�r�X
     * @param serviceData �T�[�r�X�̒�`���
     * @exception Exception �T�[�r�X�̍ĊJ�n�Ɏ��s�����ꍇ
     */
    public void restartService(Service service, ServiceMetaData serviceData)
     throws Exception;
    
    /**
     * �o�^����Ă���S�ẴT�[�r�X���ĊJ�n����B<p>
     */
    public void restartAllService();
    
    /**
     * �w�肳�ꂽ�T�[�r�X���~����B<p>
     *
     * @param name �T�[�r�X��
     */
    public void stopService(String name);
    
    /**
     * �w�肳�ꂽ�T�[�r�X���~����B<p>
     *
     * @param name �T�[�r�X��
     * @param completed ��~���ꂽ�T�[�r�X���̏W���B�ˑ��֌W�ɂ���~���ꂽ�T�[�r�X���܂ށB
     */
    public void stopService(String name, Set completed);
    
    /**
     * �w�肳�ꂽ�T�[�r�X�W�����~����B<p>
     *
     * @param names �T�[�r�X���̏W��
     */
    public void stopService(Set names);
    
    /**
     * �w�肳�ꂽ�o�^����Ă��Ȃ��T�[�r�X���~����B<p>
     * �ˑ��֌W�̉����͍s��Ȃ��B
     *
     * @param service �o�^����Ă��Ȃ��T�[�r�X
     * @param serviceData �T�[�r�X�̒�`���
     */
    public void stopService(Service service, ServiceMetaData serviceData);
    
    /**
     * �o�^����Ă���S�ẴT�[�r�X���~����B<p>
     */
    public void stopAllService();
    
    /**
     * �w�肳�ꂽ�T�[�r�X��j������B<p>
     *
     * @param name �T�[�r�X��
     */
    public void destroyService(String name);
    
    /**
     * �w�肳�ꂽ�T�[�r�X��j������B<p>
     *
     * @param name �T�[�r�X��
     * @param completed �j�����ꂽ�T�[�r�X���̏W���B�ˑ��֌W�ɂ��j�����ꂽ�T�[�r�X�Ȃǂ��܂ށB
     */
    public void destroyService(String name, Set completed);
    
    /**
     * �w�肳�ꂽ�T�[�r�X�W����j������B<p>
     *
     * @param names �T�[�r�X���̏W��
     */
    public void destroyService(Set names);
    
    /**
     * �w�肳�ꂽ�o�^����Ă��Ȃ��T�[�r�X��j������B<p>
     * �ˑ��֌W�̉����͍s��Ȃ��B
     *
     * @param service �o�^����Ă��Ȃ��T�[�r�X
     * @param serviceData �T�[�r�X�̒�`���
     */
    public void destroyService(Service service, ServiceMetaData serviceData);
    
    /**
     * �o�^����Ă���S�ẴT�[�r�X��j������B<p>
     */
    public void destroyAllService();
    
    /**
     * �w�肵���f�v���C�ҋ@���̃T�[�r�X���A�ҋ@���Ă��錴���ƂȂ��Ă���T�[�r�X�̏W�����擾����B<p>
     *
     * @param waitService �ҋ@���̃T�[�r�X��
     * @return �ҋ@���Ă��錴���ƂȂ��Ă���T�[�r�X�̏W��
     */
    public Set getWaitingCauses(String waitService);
    
    /**
     * �Ǘ����Ă���ҋ@���̃T�[�r�X���N���A����B<p>
     */
    public void clearWaitingServices();
    
    /**
     * �ҋ@���̃T�[�r�X�����݂��邩���ׂ�B<p>
     *
     * @return �ҋ@���̃T�[�r�X�����݂���ꍇtrue
     */
    public boolean existWaitingService();
    
    /**
     * �ҋ@���̃T�[�r�X�̖��O�̏W�����擾����B<p>
     *
     * @return �ҋ@���̃T�[�r�X�̖��O�̏W��
     */
    public Set getWaitingServices();
    
    /**
     * �w�肵���f�v���C�Ɏ��s�����T�[�r�X���A�f�v���C�ł��Ȃ����������ƂȂ��Ă����O���擾����B<p>
     *
     * @param failedService �f�v���C�Ɏ��s�����T�[�r�X��
     * @return �f�v���C�ł��Ȃ����������ƂȂ��Ă����O
     */
    public Throwable getFailedCause(String failedService);
    
    /**
     * �Ǘ����Ă���f�v���C�Ɏ��s�����T�[�r�X�̏W�����N���A����B<p>
     */
    public void clearFailedServices();
    
    /**
     * �f�v���C�Ɏ��s�����T�[�r�X�����݂��邩���ׂ�B<p>
     *
     * @return �f�v���C�Ɏ��s�����T�[�r�X�����݂���ꍇtrue
     */
    public boolean existFailedService();
    
    /**
     * �f�v���C�Ɏ��s�����T�[�r�X�̖��O�̏W�����擾����B<p>
     *
     * @return �f�v���C�Ɏ��s�����T�[�r�X�̖��O�̏W��
     */
    public Set getFailedServices();
     
    /**
     * �o�^���ꂽService���̃��O�o�͂Ɏg�p����{@link jp.ossc.nimbus.service.log.Logger}�T�[�r�X���擾����B<p>
     *
     * @return �o�^���ꂽService���̃��O�o�͂Ɏg�p����{@link jp.ossc.nimbus.service.log.Logger}�T�[�r�X
     */
    public Logger getLogger();
    
    /**
     * Service���ł̃��b�Z�[�W�擾�Ɏg�p����{@link jp.ossc.nimbus.service.message.MessageRecordFactory}�T�[�r�X���擾����B<p>
     *
     * @return Service���ł̃��b�Z�[�W�擾�Ɏg�p����{@link jp.ossc.nimbus.service.message.MessageRecordFactory}�T�[�r�X
     */
    public MessageRecordFactory getMessageRecordFactory();
    
    /**
     * �T�[�r�X�̏�Ԃ��ύX���ꂽ�����Ď�����ServiceStateListener��ǉ�����B<p>
     * �w�肵���T�[�r�X���o�^����Ă��Ȃ��ꍇ�ARegistrationListener��o�^����B�T�[�r�X���o�^�����ƁAServiceStateListener���o�^�����B<br>
     * �܂��A�w�肳�ꂽ�T�[�r�X��{@link ServiceStateBroadcaster}���������Ă��Ȃ��ꍇ�AServiceStateListener��o�^�ł��Ȃ����߉������Ȃ��B<br>
     *
     * @param name �T�[�r�X��
     * @param listener ServiceStateListener�I�u�W�F�N�g
     */
    public void addServiceStateListener(
        String name,
        ServiceStateListener listener
    );
    
    /**
     * �T�[�r�X�̏�Ԃ��ύX���ꂽ�����Ď�����ServiceStateListener���폜����B<p>
     *
     * @param name �T�[�r�X��
     * @param listener ServiceStateListener�I�u�W�F�N�g
     */
    public void removeServiceStateListener(
        String name,
        ServiceStateListener listener
    );
}