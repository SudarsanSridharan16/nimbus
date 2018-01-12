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
package jp.ossc.nimbus.service.aop;

import java.util.*;
import java.util.regex.*;
import javax.servlet.*;
import javax.servlet.http.*;

import jp.ossc.nimbus.beans.*;
import jp.ossc.nimbus.core.*;

/**
 * �T�[�u���b�g�t�B���^�̌Ăяo�������g���āA{@link InterceptorChainList}��U�蕪����InterceptorChainList�C���^�t�F�[�X�̎����T�[�r�X�B<p>
 * �ȉ��ɁA����̃p�X���ɈقȂ�{@link InterceptorChainList �C���^�[�Z�v�^�`�F�[�����X�g}��I������C���^�[�Z�v�^�`�F�[�����X�g�̃T�[�r�X��`��������B<br>
 * <pre>
 * &lt;?xml version="1.0" encoding="Shift_JIS"?&gt;
 * 
 * &lt;server&gt;
 *     
 *     &lt;manager name="Sample"&gt;
 *         
 *         &lt;service name="InterceptorChainList"
 *                  code="jp.ossc.nimbus.service.aop.SelectableServletFilterInterceptorChainListService"&gt;
 *             &lt;attribute name="EnabledPathMapping"&gt;
 *                 /hoge\.html=#HogeInterceptorChainList
 *                 /fuga.*=#FugaInterceptorChainList
 *             &lt;/attribute&gt;
 *             &lt;attribute name="DefaultInterceptorChainListServiceName"&gt;#DefaultInterceptorChainList&lt;/attribute&gt;
 *         &lt;/service&gt;
 *         
 *     &lt;/manager&gt;
 *     
 * &lt;/server&gt;
 * </pre>
 *
 * @author M.Takata
 */
public class SelectableServletFilterInterceptorChainListService
 extends ServiceBase
 implements SelectableServletFilterInterceptorChainListServiceMBean,
            InterceptorChainList{
    
    private static final long serialVersionUID = -3624632759924394508L;
    
    private Map enabledURLMapping;
    private Map enabledURLChainMapping;
    
    private Map enabledURIMapping;
    private Map enabledURIChainMapping;
    
    private Map enabledPathMapping;
    private Map enabledPathChainMapping;
    
    private Map disabledURLMapping;
    private Map disabledURLChainMapping;
    
    private Map disabledURIMapping;
    private Map disabledURIChainMapping;
    
    private Map disabledPathMapping;
    private Map disabledPathChainMapping;
    
    private ServiceName defaultInterceptorChainListServiceName;
    private InterceptorChainList defaultInterceptorChainList;
    
    // SelectableServletFilterInterceptorChainListServiceMBean��JavaDoc
    public void setEnabledURLMapping(Map mapping){
        enabledURLMapping = mapping;
    }
    
    // SelectableServletFilterInterceptorChainListServiceMBean��JavaDoc
    public Map getEnabledURLMapping(){
        return enabledURLMapping;
    }
    
    // SelectableServletFilterInterceptorChainListServiceMBean��JavaDoc
    public void setEnabledURIMapping(Map mapping){
        enabledURIMapping = mapping;
    }
    
    // SelectableServletFilterInterceptorChainListServiceMBean��JavaDoc
    public Map getEnabledURIMapping(){
        return enabledPathMapping;
    }
    
    // SelectableServletFilterInterceptorChainListServiceMBean��JavaDoc
    public void setEnabledPathMapping(Map mapping){
        enabledPathMapping = mapping;
    }
    
    // SelectableServletFilterInterceptorChainListServiceMBean��JavaDoc
    public Map getEnabledPathMapping(){
        return enabledPathMapping;
    }
    
    // SelectableServletFilterInterceptorChainListServiceMBean��JavaDoc
    public void setDisabledURLMapping(Map mapping){
        disabledURLMapping = mapping;
    }
    
    // SelectableServletFilterInterceptorChainListServiceMBean��JavaDoc
    public Map getDisabledURLMapping(){
        return disabledURLMapping;
    }
    
    // SelectableServletFilterInterceptorChainListServiceMBean��JavaDoc
    public void setDisabledURIMapping(Map mapping){
        disabledURIMapping = mapping;
    }
    
    // SelectableServletFilterInterceptorChainListServiceMBean��JavaDoc
    public Map getDisabledURIMapping(){
        return disabledPathMapping;
    }
    
    // SelectableServletFilterInterceptorChainListServiceMBean��JavaDoc
    public void setDisabledPathMapping(Map mapping){
        disabledPathMapping = mapping;
    }
    
    // SelectableServletFilterInterceptorChainListServiceMBean��JavaDoc
    public Map getDisabledPathMapping(){
        return disabledPathMapping;
    }
    
    // SelectableServletFilterInterceptorChainListServiceMBean��JavaDoc
    public void setDefaultInterceptorChainListServiceName(ServiceName name){
        defaultInterceptorChainListServiceName = name;
    }
    
    // SelectableServletFilterInterceptorChainListServiceMBean��JavaDoc
    public ServiceName getDefaultInterceptorChainListServiceName(){
        return defaultInterceptorChainListServiceName;
    }
    
    /**
     * �T�[�r�X�̐����������s���B<p>
     *
     * @exception Exception �T�[�r�X�̐����Ɏ��s�����ꍇ
     */
    public void createService() throws Exception{
        enabledURLChainMapping = new LinkedHashMap();
        enabledURIChainMapping = new LinkedHashMap();
        enabledPathChainMapping = new LinkedHashMap();
        disabledURLChainMapping = new LinkedHashMap();
        disabledURIChainMapping = new LinkedHashMap();
        disabledPathChainMapping = new LinkedHashMap();
    }
    
    /**
     * �T�[�r�X�̊J�n�������s���B<p>
     *
     * @exception Exception �T�[�r�X�̊J�n�Ɏ��s�����ꍇ
     */
    public void startService() throws Exception{
        
        final ServiceNameEditor editor = new ServiceNameEditor();
        editor.setServiceManagerName(getServiceManagerName());
        
        if(enabledURLMapping != null && enabledURLMapping.size() != 0){
            initMapping(editor, enabledURLMapping, enabledURLChainMapping);
        }
        
        if(enabledURIMapping != null && enabledURIMapping.size() != 0){
            initMapping(editor, enabledURIMapping, enabledURIChainMapping);
        }
        
        if(enabledPathMapping != null && enabledPathMapping.size() != 0){
            initMapping(editor, enabledPathMapping, enabledPathChainMapping);
        }
        
        if(disabledURLMapping != null && disabledURLMapping.size() != 0){
            initMapping(editor, disabledURLMapping, disabledURLChainMapping);
        }
        
        if(disabledURIMapping != null && disabledURIMapping.size() != 0){
            initMapping(editor, disabledURIMapping, disabledURIChainMapping);
        }
        
        if(disabledPathMapping != null && disabledPathMapping.size() != 0){
            initMapping(editor, disabledPathMapping, disabledPathChainMapping);
        }
        
        if(defaultInterceptorChainListServiceName != null){
            defaultInterceptorChainList
                 = (InterceptorChainList)ServiceManagerFactory
                    .getServiceObject(defaultInterceptorChainListServiceName);
        }
    }
    
    /**
     * �T�[�r�X�̒�~�������s���B<p>
     *
     * @exception Exception �T�[�r�X�̒�~�Ɏ��s�����ꍇ
     */
    public void stopService() throws Exception{
        enabledURLChainMapping.clear();
        enabledURIChainMapping.clear();
        enabledPathChainMapping.clear();
        disabledURLChainMapping.clear();
        disabledURIChainMapping.clear();
        disabledPathChainMapping.clear();
    }
    
    /**
     * �T�[�r�X�̔j���������s���B<p>
     *
     * @exception Exception �T�[�r�X�̔j���Ɏ��s�����ꍇ
     */
    public void destroyService() throws Exception{
        enabledURLChainMapping = null;
        enabledURIChainMapping = null;
        enabledPathChainMapping = null;
        disabledURLChainMapping = null;
        disabledURIChainMapping = null;
        disabledPathChainMapping = null;
    }
    
    private void initMapping(
        ServiceNameEditor editor,
        Map mapping,
        Map chainMapping
    ){
        final Iterator urls = mapping.keySet().iterator();
        while(urls.hasNext()){
            final String url = (String)urls.next();
            final Pattern pattern = Pattern.compile(url);
            
            final String serviceNameStr = (String)mapping.get(url);
            editor.setAsText(serviceNameStr);
            final ServiceName name = (ServiceName)editor.getValue();;
            final InterceptorChainList chain = (InterceptorChainList)
                ServiceManagerFactory.getServiceObject(name);
            
            chainMapping.put(pattern, chain);
        }
    }
    
    /**
     * �w�肳�ꂽ�C���f�b�N�X�̃C���^�[�Z�v�^���擾����B<p>
     * �����Ŏw�肳�ꂽ�Ăяo���R���e�L�X�g����{@link ServletFilterInvocationContext}�ɃL���X�g���āA���N�G�X�g�̃p�X�����擾���A���̃p�X�Ƀ}�b�s���O���ꂽ{@link InterceptorChainList}����A�C���^�[�Z�v�^���擾����B<br>
     *
     * @param context �Ăяo���̃R���e�L�X�g���B{@link ServletFilterInvocationContext}�łȂ���΂Ȃ�Ȃ��B
     * @param index �C���^�[�Z�v�^�̃`�F�[����̃C���f�b�N�X
     * @return �w�肳�ꂽ�C���f�b�N�X�̃C���^�[�Z�v�^�B�w�肳�ꂽ�C���f�b�N�X�̃C���^�[�Z�v�^�����݂��Ȃ��ꍇ�́Anull��Ԃ�
     */
    public Interceptor getInterceptor(InvocationContext context, int index){
        if(getState() != STARTED){
            return null;
        }
        InterceptorChainList chainList = null;
        final ServletFilterInvocationContext filtreContext
             = (ServletFilterInvocationContext)context;
        final ServletRequest request = filtreContext.getServletRequest();
        if(request instanceof HttpServletRequest){
            final HttpServletRequest httpReq = (HttpServletRequest)request;
            if(enabledURLChainMapping.size() != 0){
                final String reqURL = httpReq.getRequestURL().toString();
                chainList = selectInterceptorChainList(
                    reqURL,
                    enabledURLChainMapping,
                    true
                );
            }
            if(chainList == null && enabledURIChainMapping.size() != 0){
                final String reqURI = httpReq.getRequestURI().toString();
                chainList = selectInterceptorChainList(
                    reqURI,
                    enabledURIChainMapping,
                    true
                );
            }
            if(chainList == null && enabledPathChainMapping.size() != 0){
                String reqPath = httpReq.getServletPath();
                if(httpReq.getPathInfo() != null){
                    reqPath = reqPath == null ? httpReq.getPathInfo() : (reqPath + httpReq.getPathInfo());
                }
                chainList = selectInterceptorChainList(
                    reqPath,
                    enabledPathChainMapping,
                    true
                );
            }
            if(chainList == null && disabledURLChainMapping.size() != 0){
                final String reqURL = httpReq.getRequestURL().toString();
                chainList = selectInterceptorChainList(
                    reqURL,
                    disabledURLChainMapping,
                    false
                );
            }
            if(chainList == null && disabledURIChainMapping.size() != 0){
                final String reqURI = httpReq.getRequestURI().toString();
                chainList = selectInterceptorChainList(
                    reqURI,
                    disabledURIChainMapping,
                    false
                );
            }
            if(chainList == null && disabledPathChainMapping.size() != 0){
                String reqPath = httpReq.getPathInfo();
                if(reqPath == null){
                    reqPath = httpReq.getServletPath();
                }
                chainList = selectInterceptorChainList(
                    reqPath,
                    disabledPathChainMapping,
                    false
                );
            }
        }
        if(chainList == null){
            chainList = defaultInterceptorChainList;
        }
        if(chainList == null){
            return null;
        }
        return chainList.getInterceptor(context, index);
    }
    
    private InterceptorChainList selectInterceptorChainList(
        String target,
        Map patternMapping,
        boolean isMatch
    ){
        final Iterator patterns = patternMapping.keySet().iterator();
        while(patterns.hasNext()){
            final Pattern pattern = (Pattern)patterns.next();
            final Matcher m = pattern.matcher(target);
            if(m.matches() == isMatch){
                return (InterceptorChainList)patternMapping.get(pattern);
            }
        }
        return null;
    }
}
