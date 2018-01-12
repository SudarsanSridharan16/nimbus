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
package jp.ossc.nimbus.service.aop.interceptor;

import java.util.*;

import jp.ossc.nimbus.core.*;
import jp.ossc.nimbus.service.aop.*;
import jp.ossc.nimbus.service.context.*;

/**
 * {@link Context}�G�N�X�|�[�g�C���^�[�Z�v�^�B<p>
 * ���\�b�h�̌Ăяo������{@link Context}�̏���{@link InvocationContext}�ɏ悹��C���^�[�Z�v�^�ł���B<br>
 * �ȉ��ɁAContext�G�N�X�|�[�g�C���^�[�Z�v�^�̃T�[�r�X��`��������B<br>
 * <pre>
 * &lt;?xml version="1.0" encoding="Shift_JIS"?&gt;
 * 
 * &lt;server&gt;
 *     
 *     &lt;manager name="Sample"&gt;
 *         
 *         &lt;service name="ContextExportInterceptor"
 *                  code="jp.ossc.nimbus.service.aop.interceptor.ContextExportInterceptorService"&gt;
 *             &lt;attribute name="ContextServiceName"&gt;#Context&lt;/attribute&gt;
 *             &lt;depends&gt;#Context&lt;/depends&gt;
 *         &lt;/service&gt;
 *         
 *         &lt;service name="Context"
 *                  code="jp.ossc.nimbus.service.context.ThreadContextService"/&gt;
 *         
 *     &lt;/manager&gt;
 *     
 * &lt;/server&gt;
 * </pre>
 *
 * @author M.Takata
 * @see Context
 */
public class ContextExportInterceptorService extends ServiceBase
 implements Interceptor, ContextExportInterceptorServiceMBean{
    
    private static final long serialVersionUID = 6351075504267271338L;
    
    private ServiceName contextServiceName;
    private Context context;
    private String attributeName = DEFAULT_ATTRIBUTE_NAME;
    private String[] contextKeys;
    
    public void setContextServiceName(ServiceName name){
        contextServiceName = name;
    }
    public ServiceName getContextServiceName(){
        return contextServiceName;
    }
    
    private Context getContext() {
        if(contextServiceName != null) {
            context = (Context)ServiceManagerFactory.getServiceObject(contextServiceName);
        }
        return context;
    }
    
    /**
     * Context��ݒ肷��B
     */
    public void setContext(Context context) {
        this.context = context;
    }
    
    public void setAttributeName(String name){
        attributeName = name;
    }
    public String getAttributeName(){
        return attributeName;
    }
    
    public void setContextKeys(String[] keys){
        contextKeys = keys;
    }
    public String[] getContextKeys(){
        return contextKeys;
    }
    
    /**
     * {@link Context}�̏���{@link InvocationContext}�ɏ悹�āA���̃C���^�[�Z�v�^���Ăяo���B<p>
     * �T�[�r�X���J�n����Ă��Ȃ��ꍇ�́A���̃C���^�[�Z�v�^���Ăяo���B<br>
     *
     * @param context �Ăяo���̃R���e�L�X�g���
     * @param chain ���̃C���^�[�Z�v�^���Ăяo�����߂̃`�F�[��
     * @return �Ăяo�����ʂ̖߂�l
     * @exception Throwable �Ăяo����ŗ�O�����������ꍇ�A�܂��͂��̃C���^�[�Z�v�^�ŔC�ӂ̗�O�����������ꍇ�B�A���A�{���Ăяo����鏈����throw���Ȃ�RuntimeException�ȊO�̗�O��throw���Ă��A�Ăяo�����ɂ͓`�d����Ȃ��B
     */
    public Object invoke(
        InvocationContext context,
        InterceptorChain chain
    ) throws Throwable{
        if(getState() == STARTED){
            Context ctx = getContext();
            if(ctx != null){
                ctx = getContext();
                final Map tmp = new HashMap();
                if(contextKeys == null){
                    tmp.putAll(ctx);
                }else{
                    for(int i = 0; i < contextKeys.length; i++){
                        tmp.put(contextKeys[i], ctx.get(contextKeys[i]));
                    }
                }
                context.setAttribute(attributeName, tmp);
            }
        }
        return chain.invokeNext(context);
    }
}
