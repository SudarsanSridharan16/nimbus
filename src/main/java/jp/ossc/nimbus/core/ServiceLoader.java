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

import java.net.*;
import java.util.*;
import java.beans.PropertyEditor;

/**
 * �T�[�r�X���[�_�C���^�t�F�[�X�B<p>
 * �T�[�r�X��`��ǂݍ��݁A�T�[�r�X��o�^���郍�[�_�̃C���^�t�F�[�X�ł���B<br>
 * ���̃C���^�t�F�[�X����������T�[�r�X���[�_�́A�T�[�r�X{@link Service}�Ƃ��Ď�������A�T�[�r�X�̐����A�N���Ƌ��ɁA�ȉ��̏������s���B<br>
 * <ol>
 *   <li>�T�[�r�X��`�̓ǂݍ���</li>
 *   <li>{@link ServiceManager}�̃C���X�^���X����</li>
 *   <li>ServiceManager�ւ�ServiceLoader�̓o�^</li>
 *   <li>�eService�̃C���X�^���X����</li>
 *   <li>�eService�̃��^�f�[�^�����A�Ǘ�</li>
 *   <li>�eService�̈ˑ��֌W�̊Ǘ�</li>
 *   <li>ServiceManager�ւ̊eService�̓o�^</li>
 *   <li>ServiceManager�̃T�[�r�X�Ƃ��ẮA�����A�N��</li>
 * </ol>
 * <p>
 * �T�[�r�X���[�_�̋N���ɂ���āA�T�[�r�X��Ղ��N�����āA�����ɔz�u�����e�T�[�r�X���z�X�e�B���O�����B�]���āA�T�[�r�X���[�_�́A�T�[�r�X��Ղ̋N�_�ƂȂ�B<br>
 * �T�[�r�X���[�_�̋N�����@�́A�Q�p�ӂ���Ă���B<p>
 * <ol>
 *   <li>{@link ServiceManagerFactory#loadManager}<br>ServiceManagerFactory.loadManager()���Ăяo���ƁAServiceLoader����������A{@link ServiceLoader#create()}�A{@link ServiceLoader#start()}���Ăяo�����B<br>��ɁA�N���C�A���g�T�C�h�Ŏg�p����ꍇ�ɗp����N�����@�ł���B<br></li>
 *   <li>{@link DefaultServiceLoaderService}<br>DefaultServiceLoaderService��JBoss�̃T�[�r�X�Ƃ��āAjboss-service.xml�ɒ�`���āAJBoss�Ƀf�v���C����BDefaultServiceLoaderService�̋N���A�����ɔ����AServiceLoader����������AServiceLoader.create()�AServiceLoader.start()���Ăяo�����B<br>��ɁA�T�[�o�T�C�h�Ŏg�p����ꍇ�ɗp����N�����@�ł���BDefaultServiceLoaderService�́AJBoss�̃T�[�r�X�Ƃ��ăf�v���C�\�ł��邪�AJBoss�̒񋟂���C���^�t�F�[�X���������Ă����ł͂Ȃ��BJBoss�ȊO�̃A�v���P�[�V�����T�[�o�ł��A�T�[�r�X�Ƃ����T�O������΁ADefaultServiceLoaderService�����b�v�A�܂���DefaultServiceLoaderService�����̎������s�����ŁA�T�[�r�X���[�_���N���ł���B<br></li>
 * </ol>
 * 
 * @author M.Takata
 * @see DefaultServiceLoaderService
 * @see ServiceManagerFactory
 * @see ServiceManager
 * @see Service
 * @see <a href="nimbus-service_1_0.dtd">�T�[�r�X��`�t�@�C��DTD</a>
 */
public interface ServiceLoader extends Service{
    
    /**
     * &lt;server&gt;�v�f�̃��^�f�[�^���擾����B<p>
     *
     * @return �T�[�r�X��`&lt;server&gt;�v�f���^�f�[�^
     */
    public ServerMetaData getServerMetaData();
    
    /**
     * &lt;server&gt;�v�f�̃��^�f�[�^��ݒ肷��B<p>
     *
     * @param data �T�[�r�X��`&lt;server&gt;�v�f���^�f�[�^
     */
    public void setServerMetaData(ServerMetaData data);
    
    /**
     * &lt;service&gt;�v�f�̃��^�f�[�^���擾����B<p>
     * �w�肵���T�[�r�X���A���̃T�[�r�X���[�_�����[�h�����T�[�r�X��`�ɑ��݂��Ȃ��ꍇ�́Anull��Ԃ��B
     *
     * @param managerName �T�[�r�X���o�^����Ă���ServiceManager�̖��O
     * @param serviceName �T�[�r�X��
     * @return �T�[�r�X��`&lt;service&gt;�v�f���^�f�[�^
     */
    public ServiceMetaData getServiceMetaData(
        String managerName,
        String serviceName
    );
    
    /**
     * �w�肵���T�[�r�X�̃��^�f�[�^��ݒ肷��B<p>
     *
     * @param managerName �T�[�r�X���o�^�����ServiceManager�̖��O
     * @param serviceData �T�[�r�X��`���^�f�[�^
     */
    public void setServiceMetaData(
        String managerName,
        ServiceMetaData serviceData
    );
    
    /**
     * ���̃T�[�r�X���[�_�����[�h����ServiceManager�̏W�����擾����B<p>
     * ServiceManager�́A�T�[�r�X��`��&lt;manager&gt;�v�f�ɑΉ�����B<br>
     * �����̃T�[�r�X��`�Ɍׂ��Ē�`���ꂽ&lt;manager&gt;�v�f�ɑΉ�����ServiceManager�́A�ǂ̃T�[�r�X��`�����[�h�����T�[�r�X���[�_����擾���Ă��A�����C���X�^���X���擾�ł���B<br>
     * &lt;manager&gt;�v�f����`����Ă��Ȃ��ꍇ�́A��̏W����Ԃ��B<br>
     *
     * @return ServiceManager�̏W��
     */
    public Set getServiceManagers();
    
    /**
     * &lt;service&gt;�v�f�̎q�v�f�Ƃ��Ē�`���ꂽ&lt;depends&gt;�v�f�̃��^�f�[�^�̃��X�g���擾����B<p>
     * �w�肳�ꂽServiceManager���A���̃T�[�r�X���[�_�ɂ���ă��[�h����Ă��Ȃ��ꍇ�́Anull��Ԃ��B�܂��A�w�肳�ꂽ�T�[�r�X���̃T�[�r�X���A���̃T�[�r�X���[�_�ɂ���ă��[�h����Ă��Ȃ��ꍇ�́Anull��Ԃ��B�܂��A�w�肳�ꂽ�T�[�r�X����name�����Ƃ��Ď���&lt;service&gt;�v�f�ɁA&lt;depends&gt;�v�f����`����Ă��Ȃ��ꍇ�́A��̃��X�g��Ԃ��B<br>
     *
     * @param managerName �T�[�r�X���o�^����Ă���ServiceManager�̖��O
     * @param serviceName �T�[�r�X��
     * @return {@link ServiceMetaData.DependsMetaData}�̃��X�g
     */
    public List getDepends(
        String managerName,
        String serviceName
    );
    
    /**
     * �w�肳�ꂽ�T�[�r�X��&lt;depends&gt;�v�f�Ɏ���&lt;service&gt;�v�f�̃��^�f�[�^�̃��X�g���擾����B<p>
     * �A���A���̃T�[�r�X���[�_�Ƀ��[�h���ꂽ�T�[�r�X�݂̂��ΏۂƂȂ�B<br>
     * �w�肳�ꂽ�T�[�r�X��&lt;depends&gt;�v�f�Ɏ��T�[�r�X����`����Ă��Ȃ��ꍇ�́A��̃��X�g��Ԃ��B<br>
     *
     * @param managerName �T�[�r�X���o�^����Ă���ServiceManager�̖��O
     * @param serviceName �T�[�r�X��
     * @return {@link ServiceMetaData}�̃��X�g
     */
    public List getDependedServices(
        String managerName,
        String serviceName
    );
    
    /**
     * �T�[�r�X��`�t�@�C����URL��ݒ肷��B<p>
     *
     * @param url �T�[�r�X��`�t�@�C����URL
     * @exception IllegalArgumentException �w�肳�ꂽURL���L����URL�łȂ��ꍇ
     * @see #getServiceURL()
     */
    public void setServiceURL(URL url) throws IllegalArgumentException;
    
    /**
     * �T�[�r�X��`�t�@�C����URL���擾����B<p>
     *
     * @return �T�[�r�X��`�t�@�C����URL
     * @see #setServiceURL(URL)
     */
    public URL getServiceURL();
    
    /**
     * �T�[�r�X��`�t�@�C���̃p�X��ݒ肷��B<p>
     * �����Ŏw�肳�ꂽ�p�X�́A�ȉ��̎菇��URL�ɕύX����āA{@link #setServiceURL(URL)}�����B<br>
     * �p�X��URL�ϊ��́A�ȉ��̏����ōs����B<br>
     * <ol>
     *   <li>�w�肳�ꂽ�p�X��null�A�܂��͋󕶎��̏ꍇ�A�f�t�H���gURL�i��q�j</li>
     *   <li>�w�肳�ꂽ�p�X�����[�J���t�@�C���Ƃ��đ��݂���ꍇ�A���[�J���p�X��URL�ɕϊ�����URL</li>
     *   <li>�w�肳�ꂽ�p�X�����̃N���X�����[�h�����N���X���[�_�̃��\�[�X�Ƃ��đ��݂���ꍇ�A����URL</li>
     *   <li>��L�S�Ăɓ��Ă͂܂�Ȃ��ꍇ�A��O��throw����B</li>
     * </ol>
     * �f�t�H���gURL�̌���́A�ȉ��̏����ōs����B<br>
     * <ol>
     *   <li>�V�X�e���v���p�e�Bjp.ossc.nimbus.service.url�Ŏw�肳�ꂽ�l���A��L�̃p�X��URL�ϊ���URL�ɕϊ������l</li>
     *   <li>���̃N���X�̃N���X�t�@�C�������[�h���ꂽ�N���X�p�X�ォ��Animbus-service.xml��{@link ClassLoader#getResource(String)}�Ń��\�[�X�Ƃ��Ď擾����URL�B���̃N���X�̃N���X�t�@�C����Jar�t�@�C���Ɋi�[����Ă���ꍇ�́A����Jar�t�@�C���Ɠ����p�X���nimbus-service.xml��URL</li>
     * </ol>
     *
     * @param path �T�[�r�X��`�t�@�C���̃p�X
     * @exception IllegalArgumentException �w�肳�ꂽ�p�X���L���ȃp�X�łȂ��ꍇ
     * @see #setServiceURL(URL)
     */ 
    public void setServicePath(String path) throws IllegalArgumentException;
    
    /**
     * &lt;manager&gt;�v�f��\��{@link ServiceManager}�C���^�t�F�[�X�̎����N���X����ݒ肷��B<p>
     * �N���X���́A���S�C�����Őݒ肷��B<br>
     * �܂��A�ݒ肵�Ȃ��ꍇ�́A�f�t�H���g�̎����N���X���g�p�����B�f�t�H���g�́Ajp.ossc.nimbus.core.ServiceManagerImpl�ł���B<br>
     *
     * @param className ServiceManager�C���^�t�F�[�X�̎����N���X��
     * @exception ClassNotFoundException �w�肵���N���X���̃N���X��������Ȃ��ꍇ
     * @exception IllegalArgumentException �w�肵���N���X���̃N���X��ServiceManager�C���^�t�F�[�X���������Ă��Ȃ��ꍇ
     * @see #getServiceManagerClassName()
     * @see ServiceManager
     */
    public void setServiceManagerClassName(String className)
     throws ClassNotFoundException, IllegalArgumentException;
    
    /**
     * &lt;manager&gt;�v�f��\��{@link ServiceManager}�C���^�t�F�[�X�̎����N���X�����擾����B<p>
     *
     * @return ServiceManager�C���^�t�F�[�X�̎����N���X��
     * @see #setServiceManagerClassName(String)
     * @see ServiceManager
     */
    public String getServiceManagerClassName();
    
    /**
     * �w�肵���T�[�r�X�����[�h����B<p>
     * �A���A�����Ŏw�肷��T�[�r�X�́A���̃��[�_�[���f�v���C�����T�[�r�X�łȂ���΂Ȃ�Ȃ��B<br>
     *
     * @param managerName �T�[�r�X���o�^����Ă���ServiceManager�̖��O
     * @param serviceName �T�[�r�X��
     * @exception DeploymentException ���[�h�Ɏ��s�����ꍇ
     */
    public void loadService(String managerName, String serviceName)
     throws DeploymentException;
    
    /**
     * �w�肵���T�[�r�X��`&lt;service&gt;�v�f���^�f�[�^���f�v���C����B<p>
     * �A���A�����Ŏw�肷��serviceData�́A���̃��[�_�[�����[�h����ServiceManager�ɓo�^�������̂łȂ���΂Ȃ�Ȃ��B<br>
     *
     * @param serviceData �T�[�r�X��`&lt;service&gt;�v�f���^�f�[�^
     * @exception DeploymentException �f�v���C�Ɏ��s�����ꍇ
     */
    public void deployService(ServiceMetaData serviceData)
     throws DeploymentException;
    
    /**
     * �w�肵���^��Bean�����̕ҏW���s��PropertyEditor���擾����B<p>
     * ���݂��Ȃ��ꍇ�́Anull��Ԃ��B
     * 
     * @param type �ҏW���s��Bean�����̌^
     * @return PropertyEditor�I�u�W�F�N�g
     */
    public PropertyEditor findEditor(Class type);
    
    /**
     * �T�[�r�X��`�t�@�C����]�����邩�ǂ������w�肷��B<p>
     * �f�t�H���g�ł́A�]�����Ȃ��B<br>
     *
     * @param validate �]������ꍇtrue�B
     */
    public void setValidate(boolean validate);
    
    /**
     * �T�[�r�X��`�t�@�C����]�����邩�ǂ����𒲂ׂ�B<p>
     *
     * @return �]������ꍇtrue�B
     */
    public boolean isValidate();
    
    /**
     * �T�[�r�X��`�t�@�C���̃��[�h���Ɏg�p����\������ݒ肷��B<p>
     * 
     * @param config �T�[�r�X���[�h�\�����
     */
    public void setConfig(ServiceLoaderConfig config);
    
    /**
     * �T�[�r�X��`�t�@�C���̃��[�h���Ɏg�p����\�������擾����B<p>
     * 
     * @return �T�[�r�X���[�h�\�����
     */
    public ServiceLoaderConfig getConfig();
}