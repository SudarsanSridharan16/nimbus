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

import java.beans.*;
import java.net.*;
import java.io.*;
import java.util.*;
import javax.xml.parsers.*;
import org.w3c.dom.*;
import org.xml.sax.*;

import jp.ossc.nimbus.beans.*;
import jp.ossc.nimbus.util.*;
import jp.ossc.nimbus.service.log.*;
import jp.ossc.nimbus.service.message.MessageRecordFactory;
import jp.ossc.nimbus.service.repository.Repository;

/**
 * �T�[�r�X���[�_�B<p>
 * �T�[�r�X��`��ǂݍ��݁A�T�[�r�X��o�^���郍�[�_�ł���B<br>
 * �T�[�r�X���[�_�́A�T�[�r�X{@link Service}�Ƃ��Ď�������A�T�[�r�X�̐����A�N���Ƌ��ɁA�T�[�r�X��Ղ��N�����A�����ɔz�u�����e�T�[�r�X���z�X�e�B���O����B<br>
 * 
 * @author M.Takata
 * @see <a href="nimbus-service_1_0.dtd">�T�[�r�X��`�t�@�C��DTD</a>
 */
public class DefaultServiceLoaderService extends ServiceBase
 implements ServiceLoader, DefaultServiceLoaderServiceMBean{
    
    private static final long serialVersionUID = 7335188900913701079L;
    
    // ���b�Z�[�WID��`
    private static final String SVCL_ = "SVCL_";
    private static final String SVCL_0 = SVCL_ + 0;
    private static final String SVCL_00 = SVCL_0 + 0;
    private static final String SVCL_000 = SVCL_00 + 0;
    private static final String SVCL_0000 = SVCL_000 + 0;
    private static final String SVCL_00001 = SVCL_0000 + 1;
    private static final String SVCL_00002 = SVCL_0000 + 2;
    private static final String SVCL_00003 = SVCL_0000 + 3;
    private static final String SVCL_00004 = SVCL_0000 + 4;
    private static final String SVCL_00005 = SVCL_0000 + 5;
    private static final String SVCL_00006 = SVCL_0000 + 6;
    private static final String SVCL_00007 = SVCL_0000 + 7;
    private static final String SVCL_00008 = SVCL_0000 + 8;
    private static final String SVCL_00009 = SVCL_0000 + 9;
    private static final String SVCL_00010 = SVCL_000 + 10;
    private static final String SVCL_00011 = SVCL_000 + 11;
    private static final String SVCL_00012 = SVCL_000 + 12;
    private static final String SVCL_00013 = SVCL_000 + 13;
    private static final String SVCL_00014 = SVCL_000 + 14;
    private static final String SVCL_00015 = SVCL_000 + 15;
    private static final String SVCL_00016 = SVCL_000 + 16;
    private static final String SVCL_00017 = SVCL_000 + 17;
    private static final String SVCL_00018 = SVCL_000 + 18;
    private static final String SVCL_00019 = SVCL_000 + 19;
    private static final String SVCL_00020 = SVCL_000 + 20;
    private static final String SVCL_00021 = SVCL_000 + 21;
    private static final String SVCL_00022 = SVCL_000 + 22;
    private static final String SVCL_00023 = SVCL_000 + 23;
    private static final String SVCL_00024 = SVCL_000 + 24;
    private static final String SVCL_00025 = SVCL_000 + 25;
    private static final String SVCL_00026 = SVCL_000 + 26;
//    private static final String SVCL_00027 = SVCL_000 + 27;
    private static final String SVCL_00028 = SVCL_000 + 28;
    private static final String SVCL_00029 = SVCL_000 + 29;
    private static final String SVCL_00030 = SVCL_000 + 30;
    private static final String SVCL_00031 = SVCL_000 + 31;
    private static final String SVCL_00032 = SVCL_000 + 32;
    private static final String SVCL_00033 = SVCL_000 + 33;
    private static final String SVCL_00034 = SVCL_000 + 34;
    private static final String SVCL_00035 = SVCL_000 + 35;
    private static final String SVCL_00036 = SVCL_000 + 36;
    private static final String SVCL_00037 = SVCL_000 + 37;
    private static final String SVCL_00038 = SVCL_000 + 38;
    
    /**
     * �f�t�H���g�̃T�[�r�X���B<p>
     * {@link #setServiceName(String)}����Ȃ��ꍇ�́A���̃f�t�H���g���̌�ɁA"{�T�[�r�X��`��URL}"��t�������T�[�r�X���ƂȂ�B<br>
     *
     * @see #setServiceName(String)
     */
    protected static final String DEFAULT_NAME
     = DefaultServiceLoaderService.class.getName();
    
    /**
     * �f�t�H���g��{@link ServiceManager}�C���^�t�F�[�X�����N���X�B<p>
     * 
     * @see #setServiceManagerClassName(String)
     */
    private static final String DEFAULT_SERVICE_MANAGER_CLASS_NAME
     = jp.ossc.nimbus.core.DefaultServiceManagerService.class.getName();
    
    /**
     * VM�ɓo�^�����V���b�g�_�E���t�b�N�̃}�b�s���O�B<p>
     * &lt;manager&gt;�v�f��shutdown-hook������true���ݒ肳��Ă����ꍇ�A{@link Runtime#addShutdownHook(Thread)}�ŁAServiceManager���ɒ�~�A�j�����s���X���b�h��o�^����B<br>
     * VM���N�������܂܂ŁAServiceManager��j�������ꍇ�Ȃǂ́A�o�^�����X���b�h���s�v�ɂȂ邽�߁A�o�^�����X���b�h��static�ɊǗ����Ă���B<br>
     * <table border="1">
     *   <tr bgcolor="#CCCCFF"><th colspan="2">�L�[</th><th colspan="2">�l</th></tr>
     *   <tr bgcolor="#CCCCFF"><th>�^</th><th>���e</th><th>�^</th><th>���e</th></tr>
     *   <tr><td>String</td><td>ServiceManager�̖��O</td><td>Thread</td><td>�V���b�g�_�E���t�b�N�̃X���b�h</td></tr>
     * </table>
     */
    private static final Map shutdownHooks = new HashMap();
    
    /**
     * ���̃��[�_�����[�h����T�[�r�X��`�t�@�C����URL�B<p>
     *
     * @see #setServiceURL(URL)
     * @see #getServiceURL()
     */
    private URL serviceURL;
    
    /**
     * ServiceManager�C���^�t�F�[�X�̎����N���X���B<p>
     * �f�t�H���g�́ADEFAULT_SERVICE_MANAGER_CLASS_NAME�B<br>
     *
     * @see #setServiceManagerClassName(String)
     * @see #getServiceManagerClassName()
     */
    private String serviceManagerClassName = DEFAULT_SERVICE_MANAGER_CLASS_NAME;
    
    /**
     * ���̃��[�_�Ń��[�h�����T�[�r�X��`�̃��[�g���^�f�[�^�B<p>
     *
     * @see #getServerMetaData()
     */
    private ServerMetaData serverData;
    
    /**
     * &lt;manager&gt;�v�f�Œ�`���ꂽServiceManager�̖��O�ƁA����ServiceManager���i�[����}�b�v�B<p>
     * <table border="1">
     *   <tr bgcolor="#CCCCFF"><th colspan="2">�L�[</th><th colspan="2">�l</th></tr>
     *   <tr bgcolor="#CCCCFF"><th>�^</th><th>���e</th><th>�^</th><th>���e</th></tr>
     *   <tr><td>String</td><td>ServiceManager�̖��O</td><td>{@link ServiceManager}</td><td>ServiceManager</td></tr>
     * </table>
     * 
     * @see #getServiceManagers()
     */
    private Map managerMap;
    
    /**
     * &lt;manager&gt;�v�f�Œ�`���ꂽServiceManager�ƁA���̎q�v�f�Ƃ��Ē�`���ꂽ&lt;service&gt;�v�f��Service���i�[����}�b�v�B<p>
     * <table border="1">
     *   <tr bgcolor="#CCCCFF"><th colspan="2">�L�[</th><th colspan="5">�l</th></tr>
     *   <tr bgcolor="#CCCCFF"><th>�^</th><th>���e</th><th>�^</th><th colspan="4">���e</th></tr>
     *   <tr rowspan="3"><td rowspan="3">String</td><td rowspan="3">ServiceManager�̖��O</td><td rowspan="3">java.util.Map</td><td colspan="4">ServiceMetaData���i�[����}�b�v</td></tr>
     *   <tr bgcolor="#CCCCFF"><th>�^</th><th>���e</th><th>�^</th><th>���e</th></tr>
     *   <tr><td>String</td><td>�T�[�r�X��</td><td>{@link ServiceMetaData}</td><td>�T�[�r�X�̒�`���</td></tr>
     * </table>
     */
    private Map managersServiceMetaMap;
    
    /**
     * &lt;property-editors&gt;�v�f�Őݒ肳�ꂽjava.beans.PropertyEditor��ێ�����}�b�v�B<p>
     * <table border="1">
     *   <tr bgcolor="#CCCCFF"><th colspan="2">�L�[</th><th colspan="2">�l</th></tr>
     *   <tr bgcolor="#CCCCFF"><th>�^</th><th>���e</th><th>�^</th><th>���e</th></tr>
     *   <tr><td>java.lang.Class</td><td>java.beans.PropertyEditor���ҏW����^�̃N���X</td><td>java.lang.Class</td>java.beans.PropertyEditor�̃N���X<td></td></tr>
     * </table>
     */
    private ClassMappingTree propertyEditors;
    
    /**
     * �T�[�r�X��`XML��DTD�ŕ]�����邩�ǂ����������t���O�B<p>
     * �]������ꍇtrue�B<br>
     */
    private boolean isValidate;
    
    /**
     * �����܂łɃ��[�h�����T�[�r�X���S�Đ���ɊJ�n�ł��Ă��邩���`�F�b�N���邩�ǂ����������t���O�B<p>
     * �`�F�b�N����ꍇtrue�B
     */
    private boolean isCheckLoadManagerCompleted;
    
    /**
     * �T�[�r�X���S�Đ���ɊJ�n�ł��Ă��邩���`�F�b�N����}�l�[�W�����̏W���B<p>
     */
    private Set checkLoadManagerNames;
    
    /**
     * �T�[�r�X���[�h�\�����B<p>
     */
    private ServiceLoaderConfig loaderConfig;
    
    /**
     * ���[�h�O��default-log�v�f�̏��B<p>
     */
    private DefaultLogMetaData preDefaultLogData;
    
    /**
     * �R���X�g���N�^�B<p>
     */
    public DefaultServiceLoaderService(){
        super();
        setServiceName(DEFAULT_NAME);
    }
    
    // ServiceLoader��JavaDoc
    public void setConfig(ServiceLoaderConfig config){
        loaderConfig = config;
    }
    
    // ServiceLoader��JavaDoc
    public ServiceLoaderConfig getConfig(){
        return loaderConfig;
    }
    
    // ServiceLoader��JavaDoc
    public ServerMetaData getServerMetaData(){
        return serverData;
    }
    
    // ServiceLoader��JavaDoc
    public void setServerMetaData(ServerMetaData data){
        serverData = data;
    }
    
    // ServiceLoader��JavaDoc
    public void setServiceManagerClassName(String className)
     throws ClassNotFoundException, IllegalArgumentException{
        final Logger logger = getLogger();
        if(className != null && className.length() != 0){
            Class clazz = null;
            try{
                clazz = Class.forName(
                    className,
                    true,
                    NimbusClassLoader.getInstance()
                );
            }catch(ClassNotFoundException e){
                logger.write(
                    SVCL_00001,
                    new Object[]{ServiceManager.class, className},
                    e
                );
                throw e;
            }
            if(ServiceManager.class.isAssignableFrom(clazz)){
                serviceManagerClassName = className;
                logger.write(SVCL_00002, className);
            }else{
                final MessageRecordFactory message = getMessageRecordFactory();
                throw new IllegalArgumentException(
                    message.findEmbedMessage(SVCL_00003, className)
                );
            }
        }
    }
    
    // ServiceLoader��JavaDoc
    public String getServiceManagerClassName(){
        return serviceManagerClassName;
    }
    
    // ServiceLoader��JavaDoc
    public void setServiceURL(URL url) throws IllegalArgumentException{
        final Logger logger = getLogger();
        try{
            url.openConnection();
        }catch(IOException e){
            final MessageRecordFactory message = getMessageRecordFactory();
            throw new IllegalArgumentException(
                message.findEmbedMessage(SVCL_00004, url)
            );
        }
        serviceURL = url;
        logger.write(SVCL_00005, serviceURL);
    }
    
    // ServiceLoader��JavaDoc
    public URL getServiceURL(){
        return serviceURL;
    }
    
    // ServiceLoader��JavaDoc
    public void setServicePath(String path) throws IllegalArgumentException{
        final Logger logger = getLogger();
        final URL url = Utility.convertServicePathToURL(path);
        try{
            setServiceURL(url);
            logger.write(SVCL_00006, url);
        }catch(IllegalArgumentException e){
            final MessageRecordFactory message = getMessageRecordFactory();
            throw new IllegalArgumentException(
                message.findEmbedMessage(SVCL_00007, path)
            );
        }
    }
    
    /**
     * �T�[�r�X��`��ǂݍ��݁A�T�[�r�X��`&lt;server&gt;�v�f���^�f�[�^���\�z����B<p>
     * �����ł́A�ȉ��̏������s���B<br>
     * <ol>
     *   <li>{@link #setServiceURL(URL)}�Őݒ肳�ꂽURL����T�[�r�X��`XML��ǂݍ��݁A�p�[�X����B</li>
     *   <li>�p�[�X�����T�[�r�X��`XML����A{@link ServerMetaData}�𐶐�����BServerMetaData�̐����̉ߒ��ŁA�e�v�f�ɑΉ����郁�^�f�[�^�����������B</li>
     * </ol>
     *
     * @exception ParserConfigurationException XML�p�[�T�̐����Ɏ��s�����ꍇ
     * @exception IOException {@link #setServiceURL(URL)}�Őݒ肳�ꂽURL�ɃT�[�r�X��`XML��������Ȃ��ꍇ
     * @exception SAXException �T�[�r�X��`XML�̃p�[�X�Ɏ��s�����ꍇ
     * @exception DeploymentException �T�[�r�X��`�̐ݒ�Ɍ�肪����ꍇ
     */
    public void loadServerMetaData()
     throws IOException, ParserConfigurationException, SAXException,
            DeploymentException{
        final Logger logger = getLogger();
        
        logger.write(SVCL_00008);
        
        if(serverData != null && serviceURL == null){
            return;
        }
        
        if(serviceURL == null){
            serviceURL = Utility.getDefaultServiceURL();
            logger.write(SVCL_00009, serviceURL);
        }
        serverData = loadServerMetaData(serviceURL.openStream());
    }
    
    /**
     * �T�[�r�X��`��ǂݍ��݁A�T�[�r�X��`&lt;server&gt;�v�f���^�f�[�^���\�z����B<p>
     * �����ł́A�ȉ��̏������s���B<br>
     * <ol>
     *   <li>�w�肳�ꂽURL����T�[�r�X��`XML��ǂݍ��݁A�p�[�X����B</li>
     *   <li>�p�[�X�����T�[�r�X��`XML����A{@link ServerMetaData}�𐶐�����BServerMetaData�̐����̉ߒ��ŁA�e�v�f�ɑΉ����郁�^�f�[�^�����������B</li>
     * </ol>
     *
     * @param url �T�[�r�X��`URL
     * @exception ParserConfigurationException XML�p�[�T�̐����Ɏ��s�����ꍇ
     * @exception IOException {@link #setServiceURL(URL)}�Őݒ肳�ꂽURL�ɃT�[�r�X��`XML��������Ȃ��ꍇ
     * @exception SAXException �T�[�r�X��`XML�̃p�[�X�Ɏ��s�����ꍇ
     * @exception DeploymentException �T�[�r�X��`�̐ݒ�Ɍ�肪����ꍇ
     */
    protected ServerMetaData loadServerMetaData(URL url)
     throws IOException, ParserConfigurationException, SAXException,
            DeploymentException{
        
        if(url == null){
            return null;
        }
        return loadServerMetaData(url.openStream());
    }
    
    /**
     * �T�[�r�X��`��ǂݍ��݁A�T�[�r�X��`&lt;server&gt;�v�f���^�f�[�^���\�z����B<p>
     * �����ł́A�ȉ��̏������s���B<br>
     * <ol>
     *   <li>�w�肳�ꂽ�X�g���[������T�[�r�X��`XML��ǂݍ��݁A�p�[�X����B</li>
     *   <li>�p�[�X�����T�[�r�X��`XML����A{@link ServerMetaData}�𐶐�����BServerMetaData�̐����̉ߒ��ŁA�e�v�f�ɑΉ����郁�^�f�[�^�����������B</li>
     * </ol>
     *
     * @param is �T�[�r�X��`���̓X�g���[��
     * @exception ParserConfigurationException XML�p�[�T�̐����Ɏ��s�����ꍇ
     * @exception IOException {@link #setServiceURL(URL)}�Őݒ肳�ꂽURL�ɃT�[�r�X��`XML��������Ȃ��ꍇ
     * @exception SAXException �T�[�r�X��`XML�̃p�[�X�Ɏ��s�����ꍇ
     * @exception DeploymentException �T�[�r�X��`�̐ݒ�Ɍ�肪����ꍇ
     */
    public ServerMetaData loadServerMetaData(InputStream is)
     throws IOException, ParserConfigurationException, SAXException,
            DeploymentException{
        final InputSource inputSource = new InputSource(is);
        final DocumentBuilderFactory domFactory
             = DocumentBuilderFactory.newInstance();
        domFactory.setValidating(isValidate());
        final DocumentBuilder builder = domFactory.newDocumentBuilder();
        final NimbusEntityResolver resolver = new NimbusEntityResolver();
        builder.setEntityResolver(resolver);
        final MyErrorHandler handler = new MyErrorHandler();
        builder.setErrorHandler(handler);
        final Document doc = builder.parse(inputSource);
        if(handler.isError()){
            final MessageRecordFactory message = getMessageRecordFactory();
            throw new DeploymentException(
                message.findEmbedMessage(SVCL_00033, serviceURL)
            );
        }
        
        final ServerMetaData serverData = new ServerMetaData(this, serviceURL);
        final DocumentType docType = doc.getDoctype();
        if(docType != null){
            serverData.setDocType(
                "<!DOCTYPE " + docType.getName() + " PUBLIC \""
                     + docType.getPublicId() + "\" \""
                     + docType.getSystemId() + "\">"
            );
        }
        if(inputSource.getEncoding() != null){
            serverData.setEncoding(inputSource.getEncoding());
        }
        serverData.importXML(doc.getDocumentElement());
        
        final Properties props = serverData.getProperties();
        final Object[] propKeys = props.keySet().toArray();
        for(int i = 0; i < propKeys.length; i++){
            final String propKey = (String)propKeys[i];
            String prop = props.getProperty(propKey);
            // �V�X�e���v���p�e�B�̒u��
            prop = Utility.replaceSystemProperty(prop);
            // �T�[�r�X���[�_�\���v���p�e�B�̒u��
            prop = Utility.replaceServiceLoderConfig(
                prop,
                getConfig()
            );
            // �T�[�o�v���p�e�B�̒u��
            prop = Utility.replaceServerProperty(prop);
            ServiceManagerFactory.setProperty(propKey, prop);
        }
        return serverData;
    }
    
    /**
     * �T�[�r�X��`&lt;server&gt;�v�f���^�f�[�^��z�u����B<p>
     *
     * @param serverData �T�[�r�X��`&lt;server&gt;�v�f���^�f�[�^
     * @exception ParserConfigurationException XML�p�[�T�̐����Ɏ��s�����ꍇ
     * @exception IOException &lt;ref-url&gt;�v�f�Ŏw�肳�ꂽURL�̃T�[�r�X��`XML��������Ȃ��ꍇ
     * @exception SAXException �T�[�r�X��`XML�̃p�[�X�Ɏ��s�����ꍇ
     * @exception DeploymentException �T�[�r�X��`�̐ݒ�Ɍ�肪����ꍇ
     */
    protected void deployServerMetaData(ServerMetaData serverData)
     throws IOException, ParserConfigurationException, SAXException,
            DeploymentException{
        final Logger logger = getLogger();
        
        logger.write(SVCL_00011, serverData);
        
        checkRefURL(serverData);
        
        final DefaultLogMetaData defaultLogData
             = serverData.getDefaultLog();
        deployDefaultLogMetaData(defaultLogData);
        
        deployPropertyEditors();
        
        // �T�[�oLogger�̐ݒ�
        final ServiceNameMetaData logData = serverData.getLog();
        if(logData != null){
            final String managerName = logData.getManagerName();
            final String logName = logData.getServiceName();
            if(managerName != null && logName != null){
                ServiceManagerFactory.setLogger(managerName, logName);
            }
        }
        
        // �T�[�oMessageRecordFactory�̐ݒ�
        final ServiceNameMetaData messageData = serverData.getMessage();
        if(messageData != null){
            final String managerName = messageData.getManagerName();
            final String messageName = messageData.getServiceName();
            if(managerName != null && messageName != null){
                ServiceManagerFactory.setMessageRecordFactory(
                    managerName,
                    messageName
                );
            }
        }
        
        // �T�[�oRepository�̐ݒ�
        final ServiceNameMetaData repositoryData = serverData.getRepository();
        if(repositoryData != null){
            final String managerName = repositoryData.getManagerName();
            final String repositoryName = repositoryData.getServiceName();
            if(managerName != null && repositoryName != null){
                ServiceManagerFactory.setManagerRepository(
                    managerName,
                    repositoryName
                );
            }
        }
        
        final Iterator managers = serverData.getManagers().iterator();
        while(managers.hasNext()){
            final ManagerMetaData managerData
                 = (ManagerMetaData)managers.next();
            deployManagerMetaData(managerData);
        }
    }
    
    /**
     * &lt;ref-url&gt;�v�f�Ŏw�肳�ꂽURL�̃T�[�r�X��`�ƁA���̃��[�_�Ń��[�h����T�[�r�X��`�̑��փ`�F�b�N���s���B<p>
     * ��`����Ă���T�[�r�X�A�ˑ��֌W�ɒ�`����Ă���T�[�r�X���A���̃T�[�r�X��`���ƁA&lt;ref-url&gt;�v�f�Ŏw�肳�ꂽURL�̃T�[�r�X��`���ɑS�đ��݂��邩�`�F�b�N����B�܂��A�ˑ��֌W���A���݂ɍs���Ă��Ȃ����`�F�b�N����B<br>
     *
     * @exception ParserConfigurationException XML�p�[�T�̐����Ɏ��s�����ꍇ
     * @exception IOException &lt;ref-url&gt;�v�f�Ŏw�肳�ꂽURL�̃T�[�r�X��`XML��������Ȃ��ꍇ
     * @exception SAXException �T�[�r�X��`XML�̃p�[�X�Ɏ��s�����ꍇ
     * @exception DeploymentException �T�[�r�X��`�̐ݒ�Ɍ�肪����ꍇ
     */
    protected void checkRefURL(ServerMetaData serverData)
     throws IOException, ParserConfigurationException, SAXException,
            DeploymentException{
        
        final Set refURLSet = serverData.getReferenceURL();
        if(refURLSet == null || refURLSet.size() == 0){
            return;
        }
        
        final Map refServices = new HashMap();
        
        final Iterator refURLs = refURLSet.iterator();
        while(refURLs.hasNext()){
            final String refURLStr = (String)refURLs.next();
            URL refURL = null;
            try{
                refURL = new URL(refURLStr);
                refURL.openConnection();
            }catch(MalformedURLException e){
                refURL = null;
            }catch(IOException e){
                refURL = null;
            }
            if(refURL == null){
                 String urlString = serviceURL.toString();
                urlString = urlString.substring(
                    0,
                    urlString.lastIndexOf('/') + 1
                );
                try{
                    refURL = new URL(urlString + refURLStr);
                    refURL.openConnection();
                }catch(MalformedURLException e){
                    refURL = null;
                }catch(IOException e){
                    refURL = null;
                }
                if(refURL == null){
                    try{
                        refURL = Utility.convertServicePathToURL(refURLStr);
                    }catch(IllegalArgumentException e){
                        throw new DeploymentException(
                            "ref-url tag is illegal value : " + refURLStr
                        );
                    }
                }
            }
            final ServerMetaData refServerData = loadServerMetaData(refURL);
            final Iterator managers = refServerData.getManagers().iterator();
            while(managers.hasNext()){
                final ManagerMetaData managerData
                     = (ManagerMetaData)managers.next();
                final String managerName = managerData.getName();
                if(refServices.containsKey(managerName)){
                    ((Map)refServices.get(managerName))
                        .putAll(managerData.getServices());
                }else{
                    refServices.put(
                        managerName,
                        new HashMap(managerData.getServices())
                    );
                }
            }
        }
        
        Iterator managers = serverData.getManagers().iterator();
        while(managers.hasNext()){
            final ManagerMetaData managerData
                 = (ManagerMetaData)managers.next();
            final String managerName = managerData.getName();
            if(refServices.containsKey(managerName)){
                ((Map)refServices.get(managerName))
                    .putAll(managerData.getServices());
            }else{
                refServices.put(
                    managerName,
                    new HashMap(managerData.getServices())
                );
            }
        }
        
        // server�v�f�̎q�v�fmanager-repository�̈ˑ��֌W�̃`�F�b�N
        checkDepends(refServices, serverData.getRepository());
        
        // server�v�f�̎q�v�flog�̈ˑ��֌W�̃`�F�b�N
        checkDepends(refServices, serverData.getLog());
        
        // server�v�f�̎q�v�flog�̈ˑ��֌W�̃`�F�b�N
        checkDepends(refServices, serverData.getMessage());
        
        managers = serverData.getManagers().iterator();
        while(managers.hasNext()){
            final ManagerMetaData managerData
                 = (ManagerMetaData)managers.next();
            
            // manager�v�f�̎q�v�frepository�̈ˑ��֌W�̃`�F�b�N
            checkDepends(refServices, managerData.getRepository());
            
            // manager�v�f�̎q�v�flog�̈ˑ��֌W�̃`�F�b�N
            checkDepends(refServices, managerData.getLog());
            
            // manager�v�f�̎q�v�flog�̈ˑ��֌W�̃`�F�b�N
            checkDepends(refServices, managerData.getMessage());
            
            // service�v�f�̎q�v�fdepends�̈ˑ��֌W�̃`�F�b�N
            final Map services = managerData.getServices();
            final Iterator serviceDatas = services.values().iterator();
            while(serviceDatas.hasNext()){
                final ServiceMetaData serviceData
                     = (ServiceMetaData)serviceDatas.next();
                final Iterator depends
                     = serviceData.getDepends().iterator();
                while(depends.hasNext()){
                    final ServiceMetaData.DependsMetaData dependsData
                         = (ServiceMetaData.DependsMetaData)depends.next();
                    checkDepends(refServices, serviceData, dependsData);
                }
            }
        }
    }
    
    private void checkDepends(
        Map refServices,
        ServiceNameMetaData dependsData
    )throws DeploymentException{
        if(dependsData == null){
            return;
        }
        final String depManagerName = dependsData.getManagerName();
        final String depServiceName = dependsData.getServiceName();
        if(refServices.containsKey(depManagerName)){
            final Map services = (Map)refServices.get(depManagerName);
            if(services.containsKey(depServiceName)){
                return;
            }
        }
        final MessageRecordFactory message = getMessageRecordFactory();
        throw new DeploymentException(
            message.findEmbedMessage(
                SVCL_00036,
                new Object[]{depManagerName, depServiceName}
            )
        );
    }
    
    private void checkDepends(
        Map refServices,
        ServiceMetaData serviceData,
        ServiceMetaData.DependsMetaData dependsData
    )throws DeploymentException{
        final ManagerMetaData managerData = serviceData.getManager();
        final String managerName = managerData.getName();
        final String serviceName = serviceData.getName();
        
        final String depManagerName = dependsData.getManagerName();
        final String depServiceName = dependsData.getServiceName();
        
        final Map ref = (Map)refServices.get(depManagerName);
        if(ref == null || !ref.containsKey(depServiceName)){
            final MessageRecordFactory message = getMessageRecordFactory();
            throw new DeploymentException(
                message.findEmbedMessage(
                    SVCL_00036,
                    new Object[]{depManagerName, depServiceName}
                )
            );
        }else{
            final ServiceMetaData depServiceData
                 = (ServiceMetaData)ref.get(depServiceName);
            final Iterator deps = depServiceData.getDepends().iterator();
            while(deps.hasNext()){
                final ServiceMetaData.DependsMetaData depdepServiceData
                     = (ServiceMetaData.DependsMetaData)deps.next();
                final String depdepManagerName
                     = depdepServiceData.getManagerName();
                final String depdepServiceName
                     = depdepServiceData.getServiceName();
                if(depdepManagerName.equals(managerName)
                   && depdepServiceName.equals(serviceName)
                ){
                    final MessageRecordFactory message
                         = getMessageRecordFactory();
                    throw new DeploymentException(
                        message.findEmbedMessage(
                            SVCL_00037,
                            new Object[]{
                                managerName, serviceName,
                                depdepManagerName, depdepServiceName
                            }
                        )
                    );
                }
                checkDepends(refServices, serviceData, depdepServiceData);
            }
        }
    }
    
    /**
     * �T�[�r�X��`&lt;server&gt;�v�f���^�f�[�^���폜����B<p>
     *
     * @param serverData �T�[�r�X��`&lt;server&gt;�v�f���^�f�[�^
     * @exception DeploymentException �T�[�r�X��`�̐ݒ�Ɍ�肪����ꍇ
     */
    protected void undeployServerMetaData(ServerMetaData serverData)
     throws DeploymentException{
        
        final Iterator managers = managerMap.values().iterator();
        while(managers.hasNext()){
            final ServiceManager manager = (ServiceManager)managers.next();
            shutdownServiceManager(manager);
        }
        
        // �T�[�oRepository�̐ݒ����
        final ServiceNameMetaData repositoryData = serverData.getRepository();
        if(repositoryData != null){
            final String managerName = repositoryData.getManagerName();
            final String repositoryName = repositoryData.getServiceName();
            if(managerName != null && repositoryName != null){
                if(ServiceManagerFactory
                    .isRegisteredService(managerName, repositoryName)){
                    ServiceManagerFactory.setManagerRepository(
                        (Repository)null
                    );
                }
            }
        }
        
        final DefaultLogMetaData defaultLogData
             = serverData.getDefaultLog();
        undeployDefaultLogMetaData(defaultLogData);
    }
    
    /**
     * �T�[�oLogger�̐ݒ���s���B<p>
     *
     * @param defaultLogData �T�[�r�X��`&lt;default-log&gt;�v�f���^�f�[�^
     */
    protected void deployDefaultLogMetaData(DefaultLogMetaData defaultLogData){
        if(defaultLogData == null){
            return;
        }
        
        preDefaultLogData = new DefaultLogMetaData(defaultLogData.getParent());
        
        final Logger logger = getLogger();
        DefaultLogMetaData.LogCategoryMetaData categoryData
             = defaultLogData.getDebug();
        if(categoryData != null){
            final DefaultLogMetaData.LogCategoryMetaData preDebugData
                 = new DefaultLogMetaData.LogCategoryMetaData(preDefaultLogData);
            preDebugData.setOutput(
                ServiceManagerFactory.DEFAULT_LOGGER.isSystemDebugEnabled()
            );
            ServiceManagerFactory.DEFAULT_LOGGER
                .setSystemDebugEnabled(categoryData.isOutput());
            ServiceManagerFactory.DEFAULT_LOGGER
                .setDebugEnabled(categoryData.isOutput());
            preDefaultLogData.setDebug(preDebugData);
            if(ServiceManagerFactory.DEFAULT_LOGGER.isSystemDebugEnabled()){
                logger.write(SVCL_00012, LogService.SYSTEM_DEBUG_CATEGORY_LABEL);
            }else{
                logger.write(SVCL_00013, LogService.SYSTEM_DEBUG_CATEGORY_LABEL);
            }
        }else{
            ServiceManagerFactory.DEFAULT_LOGGER.setSystemDebugEnabled(false);
            ServiceManagerFactory.DEFAULT_LOGGER.setDebugEnabled(false);
        }
        categoryData = defaultLogData.getInformation();
        if(categoryData != null){
            final DefaultLogMetaData.LogCategoryMetaData preInfoData
                 = new DefaultLogMetaData.LogCategoryMetaData(preDefaultLogData);
            preInfoData.setOutput(
                ServiceManagerFactory.DEFAULT_LOGGER.isSystemInfoEnabled()
            );
            ServiceManagerFactory.DEFAULT_LOGGER
                .setSystemInfoEnabled(categoryData.isOutput());
            preDefaultLogData.setInformation(preInfoData);
            if(ServiceManagerFactory.DEFAULT_LOGGER.isSystemInfoEnabled()){
                logger.write(SVCL_00012, LogService.SYSTEM_INFO_CATEGORY_LABEL);
            }else{
                logger.write(SVCL_00013, LogService.SYSTEM_INFO_CATEGORY_LABEL);
            }
        }else{
            ServiceManagerFactory.DEFAULT_LOGGER.setSystemInfoEnabled(true);
        }
        categoryData = defaultLogData.getWarning();
        if(categoryData != null){
            final DefaultLogMetaData.LogCategoryMetaData preWarnData
                 = new DefaultLogMetaData.LogCategoryMetaData(preDefaultLogData);
            preWarnData.setOutput(
                ServiceManagerFactory.DEFAULT_LOGGER.isSystemWarnEnabled()
            );
            ServiceManagerFactory.DEFAULT_LOGGER
                .setSystemWarnEnabled(categoryData.isOutput());
            preDefaultLogData.setWarning(preWarnData);
            if(ServiceManagerFactory.DEFAULT_LOGGER.isSystemWarnEnabled()){
                logger.write(SVCL_00012, LogService.SYSTEM_WARN_CATEGORY_LABEL);
            }else{
                logger.write(SVCL_00013, LogService.SYSTEM_WARN_CATEGORY_LABEL);
            }
        }else{
            ServiceManagerFactory.DEFAULT_LOGGER.setSystemWarnEnabled(true);
        }
        categoryData = defaultLogData.getError();
        if(categoryData != null){
            final DefaultLogMetaData.LogCategoryMetaData preErrorData
                 = new DefaultLogMetaData.LogCategoryMetaData(preDefaultLogData);
            preErrorData.setOutput(
                ServiceManagerFactory.DEFAULT_LOGGER.isSystemErrorEnabled()
            );
            ServiceManagerFactory.DEFAULT_LOGGER
                .setSystemErrorEnabled(categoryData.isOutput());
            preDefaultLogData.setError(preErrorData);
            if(ServiceManagerFactory.DEFAULT_LOGGER.isSystemErrorEnabled()){
                logger.write(SVCL_00012, LogService.SYSTEM_ERROR_CATEGORY_LABEL);
            }else{
                logger.write(SVCL_00013, LogService.SYSTEM_ERROR_CATEGORY_LABEL);
            }
        }else{
            ServiceManagerFactory.DEFAULT_LOGGER.setSystemErrorEnabled(true);
        }
        categoryData = defaultLogData.getFatal();
        if(categoryData != null){
            final DefaultLogMetaData.LogCategoryMetaData preFatalData
                 = new DefaultLogMetaData.LogCategoryMetaData(preDefaultLogData);
            preFatalData.setOutput(
                ServiceManagerFactory.DEFAULT_LOGGER.isSystemFatalEnabled()
            );
            ServiceManagerFactory.DEFAULT_LOGGER
                .setSystemFatalEnabled(categoryData.isOutput());
            preDefaultLogData.setFatal(preFatalData);
            if(ServiceManagerFactory.DEFAULT_LOGGER.isSystemFatalEnabled()){
                logger.write(SVCL_00012, LogService.SYSTEM_FATAL_CATEGORY_LABEL);
            }else{
                logger.write(SVCL_00013, LogService.SYSTEM_FATAL_CATEGORY_LABEL);
            }
        }else{
            ServiceManagerFactory.DEFAULT_LOGGER.setSystemFatalEnabled(true);
        }
    }
    
    /**
     * �T�[�oLogger�̐ݒ���f�t�H���g�̐ݒ�ɖ߂��B<p>
     *
     * @param defaultLogData �T�[�r�X��`&lt;default-log&gt;�v�f���^�f�[�^
     */
    protected void undeployDefaultLogMetaData(
        DefaultLogMetaData defaultLogData
    ){
        if(defaultLogData == null && preDefaultLogData == null){
            return;
        }
        if(preDefaultLogData.getDebug() != null){
            ServiceManagerFactory.DEFAULT_LOGGER.setSystemDebugEnabled(
                preDefaultLogData.getDebug().isOutput()
            );
            ServiceManagerFactory.DEFAULT_LOGGER.setDebugEnabled(
                preDefaultLogData.getDebug().isOutput()
            );
        }
        if(preDefaultLogData.getInformation() != null){
            ServiceManagerFactory.DEFAULT_LOGGER.setSystemInfoEnabled(
                preDefaultLogData.getInformation().isOutput()
            );
        }
        if(preDefaultLogData.getWarning() != null){
            ServiceManagerFactory.DEFAULT_LOGGER.setSystemWarnEnabled(
                preDefaultLogData.getWarning().isOutput()
            );
        }
        if(preDefaultLogData.getError() != null){
            ServiceManagerFactory.DEFAULT_LOGGER.setSystemErrorEnabled(
                preDefaultLogData.getError().isOutput()
            );
        }
        if(preDefaultLogData.getFatal() != null){
            ServiceManagerFactory.DEFAULT_LOGGER.setSystemFatalEnabled(
                preDefaultLogData.getFatal().isOutput()
            );
        }
    }
    
    /**
     * �T�[�r�X��`&lt;property-editors&gt;�v�f�Œ�`���ꂽjava.beans.PropertyEditor�����[�h���āAjava.beans.PropertyEditorManager�ɓo�^����B<p>
     */
    protected void deployPropertyEditors(){
        final Logger logger = getLogger();
        
        logger.write(SVCL_00014);
        
        final Map propertyEditors = serverData.getPropertyEditors();
        final Iterator editTypes = propertyEditors.keySet().iterator();
        final ClassLoader loader = NimbusClassLoader.getInstance();
        while(editTypes.hasNext()){
            final String typeName = (String)editTypes.next();
            final String editorClassName
                 = (String)propertyEditors.get(typeName);
            
            Class type = null;
            Class editorClass = null;
            try{
                type = Class.forName(typeName, true, loader);
            }catch(ClassNotFoundException e){
                logger.write(SVCL_00015, typeName, e);
                continue;
            }
            try{
                editorClass = Class.forName(editorClassName, true, loader);
            }catch(ClassNotFoundException e){
                logger.write(SVCL_00016, editorClassName, e);
                continue;
            }
            this.propertyEditors.add(type, editorClass);
            logger.write(SVCL_00017, new Object[]{type, editorClass});
        }
    }
    
    /**
     * �T�[�r�X��`&lt;manager&gt;�v�f���^�f�[�^��z�u����B<p>
     *
     * @param managerData �T�[�r�X��`&lt;manager&gt;�v�f���^�f�[�^
     * @exception DeploymentException �T�[�r�X��`�̐ݒ�Ɍ�肪����ꍇ
     */
    protected void deployManagerMetaData(ManagerMetaData managerData)
     throws DeploymentException{
        final Logger logger = getLogger();
        
        logger.write(SVCL_00018, managerData);
        
        final String name = managerData.getName();
        ServiceManager manager = ServiceManagerFactory.findManager(name);
        if(manager == null){
            manager = (ServiceManager)managerMap.get(name);
        }
        if(manager == null){
            try{
                final Class clazz = Class.forName(
                    serviceManagerClassName,
                    true,
                    NimbusClassLoader.getInstance()
                );
                manager = (ServiceManager)clazz.newInstance();
            }catch(Exception e){
                final MessageRecordFactory message = getMessageRecordFactory();
                throw new DeploymentException(
                    message.findEmbedMessage(SVCL_00019, name),
                    e
                );
            }
            manager.setServiceName(name);
            manager.setServiceManagerName(name);
            logger.write(SVCL_00020, name);
        }
        manager.addServiceLoader(this);
        managerMap.put(name, manager);
        
        // ServiceManager��Logger�̐ݒ�
        final ServiceNameMetaData logData = managerData.getLog();
        if(logData != null){
            final String managerName = logData.getManagerName();
            final String logName = logData.getServiceName();
            if(managerName != null && logName != null){
                manager.setSystemLoggerServiceName(
                    new ServiceName(managerName, logName)
                );
            }
        }
        
        // ServiceManager��MessageRecordFactory�̐ݒ�
        final ServiceNameMetaData messageData = managerData.getMessage();
        if(messageData != null){
            final String managerName = messageData.getManagerName();
            final String messageName = messageData.getServiceName();
            if(managerName != null && messageName != null){
                manager.setSystemMessageRecordFactoryServiceName(
                    new ServiceName(managerName, messageName)
                );
            }
        }
        
        // ServiceManager��Repository�̐ݒ�
        final ServiceNameMetaData repositoryData = managerData.getRepository();
        if(repositoryData != null){
            final String managerName = repositoryData.getManagerName();
            final String repositoryName = repositoryData.getServiceName();
            if(managerName != null && repositoryName != null){
                manager.setServiceRepository(managerName, repositoryName);
            }
        }
        
        if(!managersServiceMetaMap.containsKey(name)){
            final Map serviceMetaMap = new HashMap();
            managersServiceMetaMap.put(name, serviceMetaMap);
        }
        final Map serviceDataMap = managerData.getServices();
        final Iterator serviceNames = serviceDataMap.keySet().iterator();
        while(serviceNames.hasNext()){
            final String serviceName = (String)serviceNames.next();
            final ServiceMetaData serviceData
                 = (ServiceMetaData)serviceDataMap.get(serviceName);
            try{
                deployServiceMetaData(serviceData);
            }catch(DeploymentException e){
                logger.write(SVCL_00038, new Object[]{managerName, serviceName}, e);
            }
        }
    }
    
    /**
     * �T�[�r�X��`&lt;service&gt;�v�f���^�f�[�^��z�u����B<p>
     *
     * @param serviceData �T�[�r�X��`&lt;service&gt;�v�f���^�f�[�^
     * @exception DeploymentException �T�[�r�X��`�̐ݒ�Ɍ�肪����ꍇ
     */
    protected void deployServiceMetaData(ServiceMetaData serviceData)
     throws DeploymentException{
        final Logger logger = getLogger();
        
        logger.write(SVCL_00021, serviceData);
        
        final String managerName = serviceData.getManager().getName();
        final String serviceName = serviceData.getName();
        
        ServiceManager manager
             = ServiceManagerFactory.findManager(managerName);
        if(manager == null){
            manager = (ServiceManager)managerMap.get(managerName);
        }
        if(manager == null){
            final MessageRecordFactory message = getMessageRecordFactory();
            throw new DeploymentException(
                message.findEmbedMessage(SVCL_00029, managerName)
            );
        }
        
        final Map serviceMetaMap
             = (Map)managersServiceMetaMap.get(managerName);
        if(serviceData.isTemplate()){
            if(manager.isRegisteredService(serviceName) || serviceMetaMap.containsKey(serviceName)){
                final MessageRecordFactory message = getMessageRecordFactory();
                throw new DeploymentException(
                    message.findEmbedMessage(SVCL_00034, new Object[]{managerName, serviceName})
                );
            }
            serviceMetaMap.put(serviceName, serviceData);
        }else{
            serviceData = serviceData.applyTemplate(this);
            
            if(manager.isRegisteredService(serviceName) || serviceMetaMap.containsKey(serviceName)){
                final MessageRecordFactory message = getMessageRecordFactory();
                throw new DeploymentException(
                    message.findEmbedMessage(SVCL_00034, new Object[]{managerName, serviceName})
                );
            }
            
            logger.write(SVCL_00023, new Object[]{managerName, serviceName});
            
            serviceMetaMap.put(serviceName, serviceData);
            try{
                manager.registerService(serviceData);
            }catch(Exception e){
                logger.write(SVCL_00022, new Object[]{managerName, serviceName}, e);
                throw new DeploymentException(e);
            }
        }
    }
    
    // ServiceLoader��JavaDoc
    public void loadService(String managerName, String serviceName)
     throws DeploymentException{
        final ServiceMetaData serviceData
             = getServiceMetaData(managerName, serviceName);
        if(serviceData == null){
            final MessageRecordFactory message = getMessageRecordFactory();
            throw new DeploymentException(
                message.findEmbedMessage(
                    SVCL_00035,
                    new Object[]{managerName, serviceName}
                )
            );
        }
        deployServiceMetaData(serviceData);
    }
    
    // ServiceLoader��JavaDoc
    public void deployService(ServiceMetaData serviceData)
     throws DeploymentException{
        deployServiceMetaData(serviceData);
    }
    
    /**
     * ���[�_�̏������������s���B<p>
     * �����ł́A�ȉ��̏������s���B<br>
     * <ol>
     *   <li>{@link ServiceManagerFactory}�֎������g��o�^����B</li>
     *   <li>�C���X�^���X�ϐ��𐶐�����B</li>
     * </ol>
     */
    public void createService(){
        final Logger logger = getLogger();
        
        if(serverData == null && serviceURL == null){
            serviceURL = Utility.getDefaultServiceURL();
            logger.write(SVCL_00009, serviceURL);
        }
        
        if(serviceURL != null){
            final String myName = getServiceName();
            setServiceName(
                myName == null ? (DEFAULT_NAME + '{' + serviceURL + '}') : myName
            );
        }
        
        if(serviceURL != null){
            ServiceManagerFactory.registerLoader(this);
        }
        
        managerMap = new LinkedHashMap();
        managersServiceMetaMap = new HashMap();
        propertyEditors = new ClassMappingTree();
    }
    
    /**
     * ���[�_�̊J�n�������s���B<p>
     * �����ł́A�ȉ��̏������s���B<br>
     * <ol>
     *   <li>{@link #loadServerMetaData()}���Ăяo���B</li>
     *   <li>�C���X�^���X�ϐ�������������B</li>
     *   <li>{@link #deployServerMetaData(ServerMetaData)}���Ăяo���B</li>
     *   <li>&lt;manager&gt;�v�f�Œ�`���ꂽ{@link ServiceManager}���N������B</li>
     * </ol>
     * @exception Exception �T�[�r�X��`�̓ǂݍ��݁A�z�u�Ɏ��s�����ꍇ�B�܂��́AServiceManager�̐����A�J�n�Ɏ��s�����ꍇ
     */
    public void startService() throws Exception{
        final Logger logger = getLogger();
        
        try{
            loadServerMetaData();
        }catch(Exception e){
            logger.write(SVCL_00010, serviceURL, e);
            throw e;
        }
        
        managerMap.clear();
        managersServiceMetaMap.clear();
        propertyEditors.clear();
        
        try{
            deployServerMetaData(serverData);
        }catch(Exception e){
            logger.write(SVCL_00024, serviceURL, e);
            throw e;
        }
        
        final Iterator managers = managerMap.values().iterator();
        while(managers.hasNext()){
            final ServiceManager manager = (ServiceManager)managers.next();
            try{
                startupServiceManager(manager);
            }catch(Exception e){
                logger.write(SVCL_00025, manager.getServiceName(), e);
                manager.destroy();
            }
        }
        if(isCheckLoadManagerCompleted){
            if(checkLoadManagerNames == null){
                ServiceManagerFactory.checkLoadManagerCompleted();
            }else{
                ServiceManagerFactory.checkLoadManagerCompletedBy(
                    checkLoadManagerNames
                );
            }
        }
    }
    
    /**
     * ���[�_�̒�~�������s���B<p>
     * <ol>
     *   <li>&lt;manager&gt;�v�f�Œ�`���ꂽ{@link ServiceManager}���~����B</li>
     *   <li>&lt;manager&gt;�v�f��shutdown-hook�����Œ�`���ꂽ�l�ɏ]���āA{@link Runtime#removeShutdownHook(Thread)}���s���B</li>
     *   <li>&lt;manager&gt;�v�f�Œ�`���ꂽ{@link ServiceManager}��j������B</li>
     *   <li>�C���X�^���X�ϐ�������������B</li>
     * </ol>
     *
     * @exception Exception ServiceManager�̒�~�Ɏ��s�����ꍇ
     */
    public void stopService() throws Exception{
        
        undeployServerMetaData(serverData);
        
        managerMap.clear();
        managersServiceMetaMap.clear();
        propertyEditors.clear();
    }
    
    /**
     * ���[�_�̔j���������s���B<p>
     * <ol>
     *   <li>�C���X�^���X�ϐ���j������B</li>
     *   <li>{@link ServiceManagerFactory}���玩�����g���폜����B</li>
     * </ol>
     *
     * @exception Exception ServiceManager�̔j���Ɏ��s�����ꍇ
     */
    public void destroyService() throws Exception{
        
        managerMap = null;
        managersServiceMetaMap = null;
        propertyEditors = null;
        
        ServiceManagerFactory.unregisterLoader(this);
    }
    
    // ServiceLoader��JavaDoc
    public Set getServiceManagers(){
        return new HashSet(managerMap.values());
    }
    
    // ServiceLoader��JavaDoc
    public List getDepends(
        String managerName,
        String serviceName
    ){
        if(!managersServiceMetaMap.containsKey(managerName)){
            return null;
        }
        final Map serviceMetaMap = (Map)managersServiceMetaMap.get(managerName);
        if(!serviceMetaMap.containsKey(serviceName)){
            return null;
        }
        final List result = new ArrayList();
        final ServiceMetaData serviceData
             = (ServiceMetaData)serviceMetaMap.get(serviceName);
        result.addAll(serviceData.getDepends());
        return result;
    }
    
    // ServiceLoader��JavaDoc
    public List getDependedServices(
        String managerName,
        String serviceName
    ){
        final List result = new ArrayList();
        final Iterator managerNames
             = managersServiceMetaMap.keySet().iterator();
        while(managerNames.hasNext()){
            final String mngName = (String)managerNames.next();
            final Map serviceMetaMap = (Map)managersServiceMetaMap.get(mngName);
            final Iterator serviceNames = serviceMetaMap.keySet().iterator();
            while(serviceNames.hasNext()){
                final String name = (String)serviceNames.next();
                if(name.equals(serviceName)
                    && mngName.equals(managerName)){
                    continue;
                }
                final Iterator dependsDatas
                     = getDepends(mngName, name).iterator();
                while(dependsDatas.hasNext()){
                    final ServiceMetaData.DependsMetaData dependsData
                         = (ServiceMetaData.DependsMetaData)dependsDatas.next();
                    if(dependsData.getServiceName().equals(serviceName)
                        && dependsData.getManagerName().equals(managerName)){
                        final ServiceMetaData serviceData
                             = (ServiceMetaData)serviceMetaMap.get(name);
                        result.add(serviceData);
                        break;
                    }
                }
            }
        }
        return result;
    }
    
    // ServiceLoader��JavaDoc
    public ServiceMetaData getServiceMetaData(
        String managerName,
        String serviceName
    ){
        if(!managersServiceMetaMap.containsKey(managerName)){
            return null;
        }
        final Map serviceMetaMap = (Map)managersServiceMetaMap.get(managerName);
        return (ServiceMetaData)serviceMetaMap.get(serviceName);
    }
    
    // ServiceLoader��JavaDoc
    public void setServiceMetaData(
        String managerName,
        ServiceMetaData serviceData
    ){
        ManagerMetaData manager = serverData.getManager(managerName);
        serviceData.setParent(manager);
        serviceData.setManager(manager);
        serviceData.setServiceLoader(this);
        final Map serviceMetaMap = (Map)managersServiceMetaMap.get(managerName);
        serviceMetaMap.put(serviceData.getName(), serviceData);
    }
    
    // ServiceLoader��JavaDoc
    public PropertyEditor findEditor(Class type){
        final Logger logger = getLogger();
        if(type == null){
            return null;
        }
        PropertyEditor editor = null;
        Class clazz = (Class)propertyEditors.getValue(type);
        if(clazz == null){
            editor = NimbusPropertyEditorManager.findEditor(type);
        }else{
            try{
                editor = (PropertyEditor)clazz.newInstance();
            }catch(InstantiationException e){
                logger.write(SVCL_00028, new Object[]{type, clazz}, e);
                return null;
            }catch(IllegalAccessException e){
                logger.write(SVCL_00028, new Object[]{type, clazz}, e);
                return null;
            }
        }
        return editor;
    }
    
    /**
     * ���̃��[�_�Ń��[�h���ꂽ�T�[�r�X�̓��ŁA�w�肳�ꂽServiceManager�ɓo�^���ꂽ�T�[�r�X�̐����A�J�n���s���B<p>
     * ServiceManager���j������Ă����Ԃ̏ꍇ�́A{@link ServiceManager#create()}�A{@link ServiceManager#start()}�������Ăяo���B<br>
     * ServiceManager����~����Ă����Ԃ̏ꍇ�́A{@link ServiceManager#start()}���Ăяo���B<br>
     * ServiceManager���J�n����Ă����Ԃ̏ꍇ�́A���̃��[�_�Ń��[�h���ꂽ�T�[�r�X������{@link ServiceManager#createService(Set)}�A{@link ServiceManager#startService(Set)}���Ăяo���Đ����A�J�n����B<br>
     * ServiceManager����������Ă����Ԃ̏ꍇ�́A{@link ServiceManager#start()}���Ăяo���B<br>
     * �܂��AServiceManager���J�n����Ă��Ȃ���Ԃ̏ꍇ�ŁA&lt;manager&gt;�v�f��shutdown-hook������true�̏ꍇ�́AVM�̏I�����t�b�N����{@link ServiceManager#stop()}�A{@link ServiceManager#destroy()}���Ăяo���X���b�h���A{@link Runtime#addShutdownHook(Thread)}�Őݒ肷��B<br>
     *
     * @param manager �����A�J�n����ServiceManager
     * @exception Exception ServiceManager�̐����A�J�n�Ɏ��s�����ꍇ
     */
    private void startupServiceManager(final ServiceManager manager)
     throws Exception{
        final Logger logger = getLogger();
        final int state = manager.getState();
        if(state != STARTED){
            if(state == DESTROYED){
                manager.create();
            }
            manager.start();
            
            final ManagerMetaData managerData
                 = serverData.getManager(manager.getServiceName());
            if(managerData.isExistShutdownHook()){
                final String managerName = manager.getServiceName();
                if(shutdownHooks.containsKey(managerName)){
                    Runtime.getRuntime().removeShutdownHook(
                        (Thread)shutdownHooks.get(managerName)
                    );
                }
                final Thread shutdownHook = new Thread(
                    new Runnable(){
                        public void run(){
                            manager.stop();
                            manager.destroy();
                        }
                    },
                    "Nimbus ShutdownHook " + (getServiceNameObject() == null ? (serviceURL == null ? null : serviceURL.toString()) : getServiceNameObject().toString())
                );
                Runtime.getRuntime().addShutdownHook(shutdownHook);
                shutdownHooks.put(manager.getServiceName(), shutdownHook);
                logger.write(SVCL_00026, manager.getServiceName());
            }
        }else{
            final Map serviceMetaMap = (Map)managersServiceMetaMap.get(
                manager.getServiceName()
            );
            final Set serviceNames = new HashSet(serviceMetaMap.keySet());
            manager.createService(serviceNames);
            manager.startService(serviceNames);
        }
    }
    
    /**
     * ���̃��[�_�Ń��[�h���ꂽ�T�[�r�X�̓��ŁA�w�肳�ꂽServiceManager�ɓo�^���ꂽ�T�[�r�X�̒�~�A�j�����s���B<p>
     * ���̃��[�_�Ń��[�h���ꂽ�T�[�r�X�̓��ŁA�w�肳�ꂽServiceManager�ɓo�^���ꂽ�T�[�r�X��{@link ServiceManager#destroyService(Set)}�Œ�~�A�j������B<br>
     * ���̌�A�w�肳�ꂽServiceManager�ɁA���̃��[�_�Ń��[�h���ꂽ�T�[�r�X���o�^����Ă��Ȃ��ꍇ�́A{@link ServiceManager#stop()}�A{@link ServiceManager#destroy()}���Ăяo���AServiceManager���g����~�A�j������B�܂��A�V���b�g�_�E���t�b�N���ݒ肳��Ă���ꍇ�́A��������B<br>
     *
     * @param manager ServiceManager�I�u�W�F�N�g
     */
    private void shutdownServiceManager(ServiceManager manager){
        final Map serviceMetaMap = (Map)managersServiceMetaMap.get(
            manager.getServiceName()
        );
        Set serviceNames = new HashSet(serviceMetaMap.keySet());
        manager.destroyService(serviceNames);
        
        if(manager.getServiceLoaders().size() == 1){
            manager.stop();
            final Thread hook
                 = (Thread)shutdownHooks.remove(manager.getServiceName());
            if(hook != null){
                try{
                    Runtime.getRuntime().removeShutdownHook(hook);
                }catch(IllegalStateException e){}
            }
            manager.destroy();
        }
        manager.removeServiceLoader(this);
    }
    
    /**
     * �n�b�V���l���擾����B<p>
     * �T�[�r�X��`�t�@�C����URL�̃n�b�V���l��Ԃ��B�T�[�r�X��`�t�@�C����URL���w�肳��Ă��Ȃ��ꍇ�́A{@link ServiceBase#hashCode() super.hashCode()}�̖߂�l��Ԃ��B<br>
     *
     * @return �n�b�V���l
     */
    public int hashCode(){
        return serviceURL == null ? super.hashCode() : serviceURL.hashCode();
    }
    
    /**
     * ���̃C���X�^���X�Ɠ����������ׂ�B<p>
     *
     * @param obj ��r�Ώۂ̃I�u�W�F�N�g
     * @return �������ꍇ�Atrue
     */
    public boolean equals(Object obj){
        if(obj == null){
            return false;
        }
        if(obj instanceof DefaultServiceLoaderService){
            final DefaultServiceLoaderService loader
                 = (DefaultServiceLoaderService)obj;
            if((serviceURL == null && loader.serviceURL != null)
                || (serviceURL != null && loader.serviceURL == null)){
                return false;
            }else if(serviceURL != null && loader.serviceURL != null
                && !serviceURL.equals(loader.serviceURL)){
                return false;
            }
            return true;
        }
        return false;
    }
    
    // DefaultServiceLoaderServiceMBean��JavaDoc
    public void setValidate(boolean validate){
        isValidate = validate;
    }
    
    // DefaultServiceLoaderServiceMBean��JavaDoc
    public boolean isValidate(){
        return isValidate;
    }
    
    // DefaultServiceLoaderServiceMBean��JavaDoc
    public void setCheckLoadManagerCompleted(boolean isCheck){
        isCheckLoadManagerCompleted = isCheck;
    }
    
    // DefaultServiceLoaderServiceMBean��JavaDoc
    public boolean isCheckLoadManagerCompleted(){
        return isCheckLoadManagerCompleted;
    }
    
    // DefaultServiceLoaderServiceMBean��JavaDoc
    public void setCheckLoadManagerCompletedBy(String[] managerNames){
        if(managerNames != null && managerNames.length != 0){
            checkLoadManagerNames = new HashSet();
            for(int i = 0; i < managerNames.length; i++){
                checkLoadManagerNames.add(managerNames[i]);
            }
        }else{
            checkLoadManagerNames = null;
        }
    }
    
    // DefaultServiceLoaderServiceMBean��JavaDoc
    public String[] getCheckLoadManagerCompletedBy(){
        return checkLoadManagerNames == null
             ? new String[0] : (String[])checkLoadManagerNames
                .toArray(new String[checkLoadManagerNames.size()]);
    }
    
    private class MyErrorHandler implements ErrorHandler{
        
        private boolean isError;
        
        public void warning(SAXParseException e) throws SAXException{
            getLogger().write(SVCL_00030, new Object[]{e.getMessage(), Integer.toString(e.getLineNumber()), Integer.toString(e.getColumnNumber())});
        }
        public void error(SAXParseException e) throws SAXException{
            isError = true;
            getLogger().write(SVCL_00031, new Object[]{e.getMessage(), Integer.toString(e.getLineNumber()), Integer.toString(e.getColumnNumber())});
        }
        public void fatalError(SAXParseException e) throws SAXException{
            isError = true;
            getLogger().write(SVCL_00032, new Object[]{e.getMessage(), Integer.toString(e.getLineNumber()), Integer.toString(e.getColumnNumber())});
        }
        public boolean isError(){
            return isError;
        }
    }
}