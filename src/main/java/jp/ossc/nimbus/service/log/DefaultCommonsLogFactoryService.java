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
package jp.ossc.nimbus.service.log;

import java.util.*;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.logging.LogConfigurationException;

import jp.ossc.nimbus.core.*;
import jp.ossc.nimbus.service.message.MessageRecordFactory;

/**
 * {@link CommonsLogFactory}�̃f�t�H���g�����N���X�B<p>
 * The Apache Jakarta Project��Commons Logging�̃C���^�t�F�[�X�ŏo�͂������O���ANimbus�̃��O�Ƃ��ďo�͂���T�[�r�X�ł���B<br>
 * �܂��A{@link LogService}�̃T�u�N���X�Ȃ̂ŁANimbus��{@link Logger}�C���^�t�F�[�X�o�R�̃��O�o�͋@�\�����B<br>
 * Commons Logging�̃C���^�t�F�[�X�ŏo�͂������O���ANimbus�̃��O�Ƃ��ďo�͂���ꍇ�́A�T�[�r�X��`�ƕʂɁA"commons-logging.properties"�t�@�C�����N���X�p�X��ɒu���K�v������B<br>
 * "commons-logging.properties"�t�@�C���ɁA�ȉ��̐ݒ���s���B<br>
 * <ul>
 * <li>org.apache.commons.logging.LogFactory<br>
 * LogFactory�C���^�t�F�[�X�̎����N���X���w�肷��B���̃T�[�r�X���g�p����ꍇ�́Ajp.ossc.nimbus.service.log.NimbusLogFactory���w�肷��B </li>
 * <li>jp.ossc.nimbus.service.log.NimbusLogFactory.DefaultLogFactory<br>
 * ���̃T�[�r�X�̋N���O���~��Ɏg�p����LogFactory�C���^�t�F�[�X�̎����N���X���w�肷��B���̃v���p�e�B�̎w�肪�Ȃ��ꍇ�́Aorg.apache.commons.logging.impl.LogFactoryImpl���g�p����B </li>
 * <li>jp.ossc.nimbus.service.log.NimbusLogFactory.CommonsLogFactoryName<br>
 * ���̃T�[�r�X�̃T�[�r�X�����w�肷��B�T�[�r�X���́A"�}�l�[�W����#�T�[�r�X��"�Ŏw�肷��B���̃T�[�r�X���N������X���b�h�ƁAJakarta Commons Logging��Log�C���X�^���X��v������X���b�h�̃R���e�L�X�g�N���X���[�_���قȂ�ꍇ�́A���̃v���p�e�B���w�肷��K�v������B�N���X���[�_�������ꍇ�́A�w�肷��K�v�͂Ȃ��B </li>
 * </ul>
 * 
 * @author M.Takata
 */
public class DefaultCommonsLogFactoryService extends LogService
 implements CommonsLogFactory, DefaultCommonsLogFactoryServiceMBean{
    
    private static final long serialVersionUID = 7172007959847003109L;
    
    // ���b�Z�[�WID��`
    private static final String DCLF_ = "DCLF_";
    private static final String DCLF_0 = DCLF_ + 0;
    private static final String DCLF_00 = DCLF_0 + 0;
    private static final String DCLF_000 = DCLF_00 + 0;
    private static final String DCLF_0000 = DCLF_000 + 0;
    private static final String DCLF_00001 = DCLF_0000 + 1;
    
    /**
     * �L���ȃ��O�C���X�^���X�̃L�[���Z�b�g
     */
    private Set enabledClientSet = new HashSet();
    
    /** {@link #CATEGORY_COMMONS_TRACE}�J�e�S���̃��O�o�̓t���O */
    private boolean isCommonsTraceEnabled = false;
    
    /** {@link #CATEGORY_COMMONS_DEBUG}�J�e�S���̃��O�o�̓t���O */
    private boolean isCommonsDebugEnabled = false;
    
    /** {@link #CATEGORY_COMMONS_INFO}�J�e�S���̃��O�o�̓t���O */
    private boolean isCommonsInfoEnabled = true;
    
    /** {@link #CATEGORY_COMMONS_WARN}�J�e�S���̃��O�o�̓t���O */
    private boolean isCommonsWarnEnabled = true;
    
    /** {@link #CATEGORY_COMMONS_ERROR}�J�e�S���̃��O�o�̓t���O */
    private boolean isCommonsErrorEnabled = true;
    
    /** {@link #CATEGORY_COMMONS_FATAL}�J�e�S���̃��O�o�̓t���O */
    private boolean isCommonsFatalEnabled = true;
    
    /**
     * {@link #CATEGORY_COMMONS_TRACE}�J�e�S���̃��O�o�͂��s��{@link jp.ossc.nimbus.service.writer.MessageWriter MessageWriter}�T�[�r�X���B<p>
     */
    private ServiceName commonsTraceMessageWriterServiceName;
    
    /**
     * {@link #CATEGORY_COMMONS_DEBUG}�J�e�S���̃��O�o�͂��s��{@link jp.ossc.nimbus.service.writer.MessageWriter MessageWriter}�T�[�r�X���B<p>
     */
    private ServiceName commonsDebugMessageWriterServiceName;
    
    /**
     * {@link #CATEGORY_COMMONS_INFO}�J�e�S���̃��O�o�͂��s��{@link jp.ossc.nimbus.service.writer.MessageWriter MessageWriter}�T�[�r�X���B<p>
     */
    private ServiceName commonsInfoMessageWriterServiceName;
    
    /**
     * {@link #CATEGORY_COMMONS_WARN}�J�e�S���̃��O�o�͂��s��{@link jp.ossc.nimbus.service.writer.MessageWriter MessageWriter}�T�[�r�X���B<p>
     */
    private ServiceName commonsWarnMessageWriterServiceName;
    
    /**
     * {@link #CATEGORY_COMMONS_ERROR}�J�e�S���̃��O�o�͂��s��{@link jp.ossc.nimbus.service.writer.MessageWriter MessageWriter}�T�[�r�X���B<p>
     */
    private ServiceName commonsErrorMessageWriterServiceName;
    
    /**
     * {@link #CATEGORY_COMMONS_FATAL}�J�e�S���̃��O�o�͂��s��{@link jp.ossc.nimbus.service.writer.MessageWriter MessageWriter}�T�[�r�X���B<p>
     */
    private ServiceName commonsFatalMessageWriterServiceName;
    
    /**
     * {@link #CATEGORY_COMMONS_TRACE}�J�e�S���̃��O�o�̓t�H�[�}�b�g���s��{@link jp.ossc.nimbus.service.writer.WritableRecordFactory WritableRecordFactory}�T�[�r�X���B<p>
     */
    private ServiceName commonsTraceRecordFactoryServiceName;
    
    /**
     * {@link #CATEGORY_COMMONS_DEBUG}�J�e�S���̃��O�o�̓t�H�[�}�b�g���s��{@link jp.ossc.nimbus.service.writer.WritableRecordFactory WritableRecordFactory}�T�[�r�X���B<p>
     */
    private ServiceName commonsDebugRecordFactoryServiceName;
    
    /**
     * {@link #CATEGORY_COMMONS_INFO}�J�e�S���̃��O�o�̓t�H�[�}�b�g���s��{@link jp.ossc.nimbus.service.writer.WritableRecordFactory WritableRecordFactory}�T�[�r�X���B<p>
     */
    private ServiceName commonsInfoRecordFactoryServiceName;
    
    /**
     * {@link #CATEGORY_COMMONS_WARN}�J�e�S���̃��O�o�̓t�H�[�}�b�g���s��{@link jp.ossc.nimbus.service.writer.WritableRecordFactory WritableRecordFactory}�T�[�r�X���B<p>
     */
    private ServiceName commonsWarnRecordFactoryServiceName;
    
    /**
     * {@link #CATEGORY_COMMONS_ERROR}�J�e�S���̃��O�o�̓t�H�[�}�b�g���s��{@link jp.ossc.nimbus.service.writer.WritableRecordFactory WritableRecordFactory}�T�[�r�X���B<p>
     */
    private ServiceName commonsErrorRecordFactoryServiceName;
    
    /**
     * {@link #CATEGORY_COMMONS_FATAL}�J�e�S���̃��O�o�̓t�H�[�}�b�g���s��{@link jp.ossc.nimbus.service.writer.WritableRecordFactory WritableRecordFactory}�T�[�r�X���B<p>
     */
    private ServiceName commonsFatalRecordFactoryServiceName;
    
    /**
     * {@link Log}�C���X�^���X�Ǘ��}�b�v�B<p>
     * <table border="1">
     *   <tr bgcolor="#CCCCFF"><th colspan="2">�L�[</th><th colspan="2">�l</th></tr>
     *   <tr bgcolor="#CCCCFF"><th>�^</th><th>���e</th><th>�^</th><th>���e</th></tr>
     *   <tr><td>Object</td><td>Log�C���X�^���X���ʏ��</td><td>Log</td><td>Log�C���X�^���X</td></tr>
     * </table>
     */
    private Map logInstances;
    
    /**
     * �����Ǘ��}�b�v�B<p>
     * <table border="1">
     *   <tr bgcolor="#CCCCFF"><th colspan="2">�L�[</th><th colspan="2">�l</th></tr>
     *   <tr bgcolor="#CCCCFF"><th>�^</th><th>���e</th><th>�^</th><th>���e</th></tr>
     *   <tr><td>Object</td><td>������</td><td>Object</td><td>�����l</td></tr>
     * </table>
     */
    private Map attributes;
    
    /**
     * �����������s���B<p>
     * ���̃��\�b�h�ɂ́A�ȉ��̎������s���Ă���B<br>
     * <ol>
     *   <li>super.createService()�Ăяo���B</li>
     *   <li>Log�C���X�^���X�Ǘ��pMap�𐶐�����B</li>
     *   <li>�����Ǘ��pMap�𐶐�����B</li>
     * </ol>
     * 
     * @exception Exception ���������Ɏ��s�����ꍇ
     */
    public void createService() throws Exception{
        super.createService();
        
        logInstances = Collections.synchronizedMap(new HashMap());
        attributes = Collections.synchronizedMap(new HashMap());
    }
    
    /**
     * �J�n�������s���B<p>
     * ���̃��\�b�h�ɂ́A�ȉ��̎������s���Ă���B<br>
     * <ol>
     *   <li>super.startService()�Ăяo���B</li>
     *   <li>Commons���O�J�e�S�����f�t�H���g�J�e�S���Ƃ��ēo�^����B</li>
     *   <li>���O�o�̓t�H�[�}�b�g��{@link DefaultCommonsLogFactoryServiceMBean#DEFAULT_FORMAT}�ɕύX����B</li>
     *   <li>{@link NimbusLogFactory#setCommonsLogFactory(CommonsLogFactory)}�Ɏ������g��ݒ肷��B</li>
     * </ol>
     * 
     * @exception Exception �J�n�����Ɏ��s�����ꍇ
     */
    public void startService() throws Exception{
        defaultFormat = DefaultCommonsLogFactoryServiceMBean.DEFAULT_FORMAT;
        super.startService();
        
        // NimbusLogFactory�ɓo�^
        final LogFactory logFactory = LogFactory.getFactory();
        if(logFactory instanceof NimbusLogFactory){
            ((NimbusLogFactory)logFactory).setCommonsLogFactory(this);
        }
    }
    
    protected void initDefaultCategory() throws Exception{
        super.initDefaultCategory();
        
        // Commons���O�J�e�S���̓o�^
        addDefaultCategory(
            getDefaultMessageWriterService(),
            getDefaultWritableRecordFactoryService(),
            getCommonsTraceMessageWriterServiceName(),
            getCommonsTraceWritableRecordFactoryServiceName(),
            CATEGORY_COMMONS_TRACE,
            PRIORITY_COMMONS_TRACE_MIN,
            PRIORITY_COMMONS_TRACE_MAX,
            LABEL_COMMONS_TRACE,
            isCommonsTraceEnabled()
        );
        addDefaultCategory(
            getDefaultMessageWriterService(),
            getDefaultWritableRecordFactoryService(),
            getCommonsDebugMessageWriterServiceName(),
            getCommonsDebugWritableRecordFactoryServiceName(),
            CATEGORY_COMMONS_DEBUG,
            PRIORITY_COMMONS_DEBUG_MIN,
            PRIORITY_COMMONS_DEBUG_MAX,
            LABEL_COMMONS_DEBUG,
            isCommonsDebugEnabled()
        );
        addDefaultCategory(
            getDefaultMessageWriterService(),
            getDefaultWritableRecordFactoryService(),
            getCommonsInfoMessageWriterServiceName(),
            getCommonsInfoWritableRecordFactoryServiceName(),
            CATEGORY_COMMONS_INFO,
            PRIORITY_COMMONS_INFO_MIN,
            PRIORITY_COMMONS_INFO_MAX,
            LABEL_COMMONS_INFO,
            isCommonsInfoEnabled()
        );
        addDefaultCategory(
            getDefaultMessageWriterService(),
            getDefaultWritableRecordFactoryService(),
            getCommonsWarnMessageWriterServiceName(),
            getCommonsWarnWritableRecordFactoryServiceName(),
            CATEGORY_COMMONS_WARN,
            PRIORITY_COMMONS_WARN_MIN,
            PRIORITY_COMMONS_WARN_MAX,
            LABEL_COMMONS_WARN,
            isCommonsWarnEnabled()
        );
        addDefaultCategory(
            getDefaultMessageWriterService(),
            getDefaultWritableRecordFactoryService(),
            getCommonsErrorMessageWriterServiceName(),
            getCommonsErrorWritableRecordFactoryServiceName(),
            CATEGORY_COMMONS_ERROR,
            PRIORITY_COMMONS_ERROR_MIN,
            PRIORITY_COMMONS_ERROR_MAX,
            LABEL_COMMONS_ERROR,
            isCommonsErrorEnabled()
        );
        addDefaultCategory(
            getDefaultMessageWriterService(),
            getDefaultWritableRecordFactoryService(),
            getCommonsFatalMessageWriterServiceName(),
            getCommonsFatalWritableRecordFactoryServiceName(),
            CATEGORY_COMMONS_FATAL,
            PRIORITY_COMMONS_FATAL_MIN,
            PRIORITY_COMMONS_FATAL_MAX,
            LABEL_COMMONS_FATAL,
            isCommonsFatalEnabled()
        );
    }
    
    /**
     * ��~�������s���B<p>
     * ���̃��\�b�h�ɂ́A�ȉ��̎������s���Ă���B<br>
     * <ol>
     *   <li>super.stopService()�Ăяo���B</li>
     *   <li>{@link #release()}���Ăяo���B</li>
     *   <li>{@link NimbusLogFactory#setCommonsLogFactory(CommonsLogFactory)}��null��ݒ肷��B</li>
     * </ol>
     */
    public void stopService(){
        super.stopService();
        release();
        final LogFactory logFactory = LogFactory.getFactory();
        if(logFactory instanceof NimbusLogFactory){
            ((NimbusLogFactory)logFactory).setCommonsLogFactory(null);
        }
    }
    
    // CommonsLogFactory��JavaDoc
    public Log getInstance(Class clazz) throws LogConfigurationException{
        if(logInstances == null){
            final MessageRecordFactory message = getMessageRecordFactory();
            throw new LogConfigurationException(
                message.findMessage(DCLF_00001)
            );
        }
        if(logInstances.containsKey(clazz)){
            return (Log)logInstances.get(clazz);
        }
        final CommonsLog log = new CommonsLog(clazz);
        logInstances.put(clazz, log);
        if(!enabledClientSet.isEmpty()){
            log.setEnabled(containsEnabledClient(log));
        }
        return log;
    }
    
    // CommonsLogFactory��JavaDoc
    public Log getInstance(String name) throws LogConfigurationException{
        if(logInstances == null){
            final MessageRecordFactory message = getMessageRecordFactory();
            throw new LogConfigurationException(
                message.findMessage(DCLF_00001)
            );
        }
        if(logInstances.containsKey(name)){
            return (Log)logInstances.get(name);
        }
        final CommonsLog log = new CommonsLog(name);
        logInstances.put(name, log);
        if(!enabledClientSet.isEmpty()){
            log.setEnabled(containsEnabledClient(log));
        }
        return log;
    }
    
    // CommonsLogFactory��JavaDoc
    public void release(){
        if(logInstances != null){
            logInstances.clear();
        }
    }
    
    // CommonsLogFactory��JavaDoc
    public Object getAttribute(String name){
        if(attributes == null){
            return null;
        }
        return attributes.get(name);
    }
    
    // CommonsLogFactory��JavaDoc
    public String[] getAttributeNames(){
        if(attributes == null){
            return new String[0];
        }
        return (String[])attributes.keySet()
            .toArray(new String[attributes.size()]);
    }
    
    // CommonsLogFactory��JavaDoc
    public void removeAttribute(String name){
        if(attributes == null){
            return;
        }
        attributes.remove(name);
    }
    
    // CommonsLogFactory��JavaDoc
    public void setAttribute(String name, Object value){
        if(attributes == null){
            return;
        }
        attributes.put(name, value);
    }
    
    /**
     * ���O�̃L���[���o����̏������s���B<p>
     * super�Ăяo�����s��������e�ɈϏ�����B<br>
     * ���̌�AdequeuedRecord������o����LogMessageRecord�̃C���X�^���X���ACommonsLogMessageRecord�ł������ꍇ�A{@link #FORMAT_CLIENT_KEY}�ɑΉ�����N���C�A���g���ʕ������擾���āA{@link LogEnqueuedRecord#addWritableElement(Object, Object)}��{@link jp.ossc.nimbus.service.writer.WritableElement WritableElement}�Ƃ��Ēǉ�����B<br>
     *
     * @param dequeuedRecord LogEnqueuedRecord�I�u�W�F�N�g
     */
    protected void postDequeue(LogEnqueuedRecord dequeuedRecord){
        super.postDequeue(dequeuedRecord);
        
        final LogMessageRecord messageRecord
             = dequeuedRecord.getLogMessageRecord();
        if(messageRecord instanceof CommonsLogMessageRecord){
            dequeuedRecord.addWritableElement(
                FORMAT_CLIENT_KEY,
                ((CommonsLogMessageRecord)messageRecord).getShortClientKey()
            );
        }
    }
    
    // DefaultCommonsLogServiceMBean��JavaDoc
    public void setEnabledClients(String[] clients){
        if(clients != null){
            enabledClientSet.clear();
            for(int i = 0, max = clients.length; i < max; i++){
                enabledClientSet.add(clients[i]);
            }
        }
        if(logInstances != null){
            final Iterator keys = logInstances.keySet().iterator();
            while(keys.hasNext()){
                final Object key = keys.next();
                final CommonsLog log = (CommonsLog)logInstances.get(key);
                if(enabledClientSet.isEmpty()){
                    log.setEnabled(true);
                }else{
                    log.setEnabled(
                        containsEnabledClient(log)
                    );
                }
            }
        }
    }
    
    private boolean containsEnabledClient(CommonsLog log){
        final String key = log.getClientKey();
        if(enabledClientSet.contains(key)){
            return true;
        }
        final Iterator enabledClients = enabledClientSet.iterator();
        while(enabledClients.hasNext()){
            final String enabledClient = (String)enabledClients.next();
            final int length = enabledClient.length();
            if(length == 0){
                continue;
            }
            if(enabledClient.charAt(length - 1) == '*'){
                final String match = enabledClient.substring(0, length - 1);
                if(key.startsWith(match)){
                    return true;
                }
            }
        }
        return false;
    }
    
    // DefaultCommonsLogServiceMBean��JavaDoc
    public String[] getEnabledClients(){
        return (String[])enabledClientSet.toArray(new String[enabledClientSet.size()]);
    }
    
    // DefaultCommonsLogServiceMBean��JavaDoc
    public void setCommonsTraceEnabled(boolean isEnabled){
        isCommonsTraceEnabled = isEnabled;
        setEnabled(
            CATEGORY_COMMONS_TRACE,
            isEnabled
        );
    }
    
    // DefaultCommonsLogServiceMBean��JavaDoc
    public boolean isCommonsTraceEnabled(){
        return isEnabled(
            CATEGORY_COMMONS_TRACE,
            isCommonsTraceEnabled
        );
    }
    
    // DefaultCommonsLogServiceMBean��JavaDoc
    public void setCommonsDebugEnabled(boolean isEnabled){
        isCommonsDebugEnabled = isEnabled;
        setEnabled(
            CATEGORY_COMMONS_DEBUG,
            isEnabled
        );
    }
    
    // DefaultCommonsLogServiceMBean��JavaDoc
    public boolean isCommonsDebugEnabled(){
        return isEnabled(
            CATEGORY_COMMONS_DEBUG,
            isCommonsDebugEnabled
        );
    }
    
    // DefaultCommonsLogServiceMBean��JavaDoc
    public void setCommonsInfoEnabled(boolean isEnabled){
        isCommonsInfoEnabled = isEnabled;
        setEnabled(
            CATEGORY_COMMONS_INFO,
            isEnabled
        );
    }
    
    // DefaultCommonsLogServiceMBean��JavaDoc
    public boolean isCommonsInfoEnabled(){
        return isEnabled(
            CATEGORY_COMMONS_INFO,
            isCommonsInfoEnabled
        );
    }
    
    // DefaultCommonsLogServiceMBean��JavaDoc
    public void setCommonsWarnEnabled(boolean isEnabled){
        isCommonsWarnEnabled = isEnabled;
        setEnabled(
            CATEGORY_COMMONS_WARN,
            isEnabled
        );
    }
    
    // DefaultCommonsLogServiceMBean��JavaDoc
    public boolean isCommonsWarnEnabled(){
        return isEnabled(
            CATEGORY_COMMONS_WARN,
            isCommonsWarnEnabled
        );
    }
    
    // DefaultCommonsLogServiceMBean��JavaDoc
    public void setCommonsErrorEnabled(boolean isEnabled){
        isCommonsErrorEnabled = isEnabled;
        setEnabled(
            CATEGORY_COMMONS_ERROR,
            isEnabled
        );
    }
    
    // DefaultCommonsLogServiceMBean��JavaDoc
    public boolean isCommonsErrorEnabled(){
        return isEnabled(
            CATEGORY_COMMONS_ERROR,
            isCommonsErrorEnabled
        );
    }
    
    // DefaultCommonsLogServiceMBean��JavaDoc
    public void setCommonsFatalEnabled(boolean isEnabled){
        isCommonsFatalEnabled = isEnabled;
        setEnabled(
            CATEGORY_COMMONS_FATAL,
            isEnabled
        );
    }
    
    // DefaultCommonsLogServiceMBean��JavaDoc
    public boolean isCommonsFatalEnabled(){
        return isEnabled(
            CATEGORY_COMMONS_FATAL,
            isCommonsFatalEnabled
        );
    }
    
    // DefaultCommonsLogServiceMBean��JavaDoc
    public void setCommonsTraceMessageWriterServiceName(ServiceName name){
        commonsTraceMessageWriterServiceName = name;
    }
    
    // DefaultCommonsLogServiceMBean��JavaDoc
    public ServiceName getCommonsTraceMessageWriterServiceName(){
        return commonsTraceMessageWriterServiceName;
    }
    
    // DefaultCommonsLogServiceMBean��JavaDoc
    public void setCommonsDebugMessageWriterServiceName(ServiceName name){
        commonsDebugMessageWriterServiceName = name;
    }
    
    // DefaultCommonsLogServiceMBean��JavaDoc
    public ServiceName getCommonsDebugMessageWriterServiceName(){
        return commonsDebugMessageWriterServiceName;
    }
    
    // DefaultCommonsLogServiceMBean��JavaDoc
    public void setCommonsInfoMessageWriterServiceName(ServiceName name){
        commonsInfoMessageWriterServiceName = name;
    }
    
    // DefaultCommonsLogServiceMBean��JavaDoc
    public ServiceName getCommonsInfoMessageWriterServiceName(){
        return commonsInfoMessageWriterServiceName;
    }
    
    // DefaultCommonsLogServiceMBean��JavaDoc
    public void setCommonsWarnMessageWriterServiceName(ServiceName name){
        commonsWarnMessageWriterServiceName = name;
    }
    
    // DefaultCommonsLogServiceMBean��JavaDoc
    public ServiceName getCommonsWarnMessageWriterServiceName(){
        return commonsWarnMessageWriterServiceName;
    }
    
    // DefaultCommonsLogServiceMBean��JavaDoc
    public void setCommonsErrorMessageWriterServiceName(ServiceName name){
        commonsErrorMessageWriterServiceName = name;
    }
    
    // DefaultCommonsLogServiceMBean��JavaDoc
    public ServiceName getCommonsErrorMessageWriterServiceName(){
        return commonsErrorMessageWriterServiceName;
    }
    
    // DefaultCommonsLogServiceMBean��JavaDoc
    public void setCommonsFatalMessageWriterServiceName(ServiceName name){
        commonsFatalMessageWriterServiceName = name;
    }
    
    // DefaultCommonsLogServiceMBean��JavaDoc
    public ServiceName getCommonsFatalMessageWriterServiceName(){
        return commonsFatalMessageWriterServiceName;
    }
    
    // DefaultCommonsLogServiceMBean��JavaDoc
    public void setCommonsTraceWritableRecordFactoryServiceName(
        ServiceName name
    ){
        commonsTraceRecordFactoryServiceName = name;
    }
    
    // DefaultCommonsLogServiceMBean��JavaDoc
    public ServiceName getCommonsTraceWritableRecordFactoryServiceName(){
        return commonsTraceRecordFactoryServiceName;
    }
    
    // DefaultCommonsLogServiceMBean��JavaDoc
    public void setCommonsDebugWritableRecordFactoryServiceName(
        ServiceName name
    ){
        commonsDebugRecordFactoryServiceName = name;
    }
    
    // DefaultCommonsLogServiceMBean��JavaDoc
    public ServiceName getCommonsDebugWritableRecordFactoryServiceName(){
        return commonsDebugRecordFactoryServiceName;
    }
    
    // DefaultCommonsLogServiceMBean��JavaDoc
    public void setCommonsInfoWritableRecordFactoryServiceName(
        ServiceName name
    ){
        commonsInfoRecordFactoryServiceName = name;
    }
    
    // DefaultCommonsLogServiceMBean��JavaDoc
    public ServiceName getCommonsInfoWritableRecordFactoryServiceName(){
        return commonsInfoRecordFactoryServiceName;
    }
    
    // DefaultCommonsLogServiceMBean��JavaDoc
    public void setCommonsWarnWritableRecordFactoryServiceName(
        ServiceName name
    ){
        commonsWarnRecordFactoryServiceName = name;
    }
    
    // DefaultCommonsLogServiceMBean��JavaDoc
    public ServiceName getCommonsWarnWritableRecordFactoryServiceName(){
        return commonsWarnRecordFactoryServiceName;
    }
    
    // DefaultCommonsLogServiceMBean��JavaDoc
    public void setCommonsErrorWritableRecordFactoryServiceName(
        ServiceName name
    ){
        commonsErrorRecordFactoryServiceName = name;
    }
    
    // DefaultCommonsLogServiceMBean��JavaDoc
    public ServiceName getCommonsErrorWritableRecordFactoryServiceName(){
        return commonsErrorRecordFactoryServiceName;
    }
    
    // DefaultCommonsLogServiceMBean��JavaDoc
    public void setCommonsFatalWritableRecordFactoryServiceName(
        ServiceName name
    ){
        commonsFatalRecordFactoryServiceName = name;
    }
    
    // DefaultCommonsLogServiceMBean��JavaDoc
    public ServiceName getCommonsFatalWritableRecordFactoryServiceName(){
        return commonsFatalRecordFactoryServiceName;
    }
    
    private static class CommonsLogMessageRecord extends LogMessageRecordImpl
     implements java.io.Serializable{
        
        private static final long serialVersionUID = 7967745897491812488L;
        
        private final String clientKey;
        private final String shortClientKey;
        
        public CommonsLogMessageRecord(CommonsLog logger){
            clientKey = logger.getClientKey();
            shortClientKey = logger.getShortClientKey();
        }
        
        public String getClientKey(){
            return clientKey;
        }
        
        public String getShortClientKey(){
            return shortClientKey;
        }
    }
    
    private class CommonsLog
     implements org.apache.commons.logging.Log, java.io.Serializable{
        
        private static final long serialVersionUID = 6075471555520523752L;
        
        private final String clientKey;
        private String shortClientKey;
        private boolean isEnabled = true;
        
        public CommonsLog(){
            this(DefaultCommonsLogFactoryService.class);
        }
        
        public CommonsLog(Class clazz){
            this(clazz.getName());
            final String className = clazz.getName();
            final int index = className.lastIndexOf('.');
            shortClientKey
                 = index == -1 ? className : className.substring(index + 1);
        }
        
        public CommonsLog(String name){
            clientKey = name;
        }
        
        public String getClientKey(){
            return clientKey;
        }
        
        public String getShortClientKey(){
            return shortClientKey == null ? clientKey : shortClientKey;
        }
        
        public void setEnabled(boolean enable){
            isEnabled = enable;
        }
        
        public boolean isEnabled(){
            return isEnabled;
        }
        
        private LogMessageRecord createLogMessageRecord(
            String category,
            int priority,
            Object message,
            CommonsLog logger
        ){
            final CommonsLogMessageRecord record
                 = new CommonsLogMessageRecord(logger);
            record.addCategory(category);
            record.setPriority(priority);
            record.setMessageCode(EMPTY_STRING);
            record.addMessage(message != null ? message.toString() : null);
            record.setFactory(getMessageRecordFactoryService());
            return record;
        }
        
        private void write(String category, int priority, Object message){
            if(!isEnabled()){
                return;
            }
            final LogMessageRecord messageRecord = createLogMessageRecord(
                category,
                priority,
                message,
                this
            );
            if(!isWrite(messageRecord)){
                return;
            }
            DefaultCommonsLogFactoryService.this.write(
                messageRecord,
                null,
                (String)null,
                null
            );
        }
        
        private void write(
            String category,
            int priority,
            Object message,
            Throwable t
        ){
            if(!isEnabled()){
                return;
            }
            final LogMessageRecord messageRecord = createLogMessageRecord(
                category,
                priority,
                message,
                this
            );
            if(!isWrite(messageRecord)){
                return;
            }
            DefaultCommonsLogFactoryService.this.write(messageRecord, null, (String)null, t);
        }
        
        public void trace(Object message){
            write(CATEGORY_COMMONS_TRACE, PRIORITY_COMMONS_TRACE, message);
        }
        
        public void trace(Object message, Throwable t){
            write(CATEGORY_COMMONS_TRACE, PRIORITY_COMMONS_TRACE, message, t);
        }
        
        public void debug(Object message){
            write(CATEGORY_COMMONS_DEBUG, PRIORITY_COMMONS_DEBUG, message);
        }
        
        public void debug(Object message, Throwable t){
            write(CATEGORY_COMMONS_DEBUG, PRIORITY_COMMONS_DEBUG, message, t);
        }
        
        public void info(Object message){
            write(CATEGORY_COMMONS_INFO, PRIORITY_COMMONS_INFO, message);
        }
        
        public void info(Object message, Throwable t){
            write(CATEGORY_COMMONS_INFO, PRIORITY_COMMONS_INFO, message, t);
        }
        
        public void warn(Object message){
            write(CATEGORY_COMMONS_WARN, PRIORITY_COMMONS_WARN, message);
        }
        
        public void warn(Object message, Throwable t){
            write(CATEGORY_COMMONS_WARN, PRIORITY_COMMONS_WARN, message, t);
        }
        
        public void error(Object message){
            write(CATEGORY_COMMONS_ERROR, PRIORITY_COMMONS_ERROR, message);
        }
        
        public void error(Object message, Throwable t){
            write(CATEGORY_COMMONS_ERROR, PRIORITY_COMMONS_ERROR, message, t);
        }
        
        public void fatal(Object message){
            write(CATEGORY_COMMONS_FATAL, PRIORITY_COMMONS_FATAL, message);
        }
        
        public void fatal(Object message, Throwable t) {
            write(CATEGORY_COMMONS_FATAL, PRIORITY_COMMONS_FATAL, message, t);
        }
        
        public boolean isTraceEnabled() {
            return isCommonsTraceEnabled();
        }
        
        public boolean isDebugEnabled() {
            return isCommonsDebugEnabled();
        }
        
        public boolean isInfoEnabled() {
            return isCommonsInfoEnabled();
        }
        
        public boolean isWarnEnabled() {
            return isCommonsWarnEnabled();
        }
        
        public boolean isErrorEnabled() {
            return isCommonsErrorEnabled();
        }
        
        public boolean isFatalEnabled() {
            return isCommonsFatalEnabled();
        }
    }
}
