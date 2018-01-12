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
package jp.ossc.nimbus.service.aop.interceptor.servlet;

import java.lang.reflect.InvocationTargetException;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import jp.ossc.nimbus.beans.Property;
import jp.ossc.nimbus.beans.PropertyAccess;
import jp.ossc.nimbus.core.ServiceManagerFactory;
import jp.ossc.nimbus.core.ServiceName;
import jp.ossc.nimbus.service.aop.InterceptorChain;
import jp.ossc.nimbus.service.aop.ServletFilterInvocationContext;
import jp.ossc.nimbus.service.context.Context;

/**
 * HttpServletRequest�v���p�e�B�ݒ�C���^�[�Z�v�^�B
 * <p>
 *
 * @author M.Ishida
 */
public class HttpServletRequestTransferInterceptorService extends ServletFilterInterceptorService implements
        HttpServletRequestTransferInterceptorServiceMBean {

    private static final long serialVersionUID = 8599129621419714729L;

    protected ServiceName threadContextServiceName;
    protected Context threadContext;
    protected PropertyAccess propertyAccess;
    protected Map requestPropertyAndContextKeyMapping;

    public void setThreadContextServiceName(ServiceName name) {
        threadContextServiceName = name;
    }

    public ServiceName getThreadContextServiceName() {
        return threadContextServiceName;
    }

    public void setRequestPropertyAndContextKeyMapping(Map mapping) {
        requestPropertyAndContextKeyMapping = mapping;
    }

    public Map getRequestPropertyAndContextKeyMapping() {
        return requestPropertyAndContextKeyMapping;
    }

    /**
     * �T�[�r�X�̐����������s���B
     * <p>
     *
     * @exception Exception �T�[�r�X�̐����Ɏ��s�����ꍇ
     */
    public void createService() throws Exception {
        propertyAccess = new PropertyAccess();
        propertyAccess.setIgnoreNullProperty(true);
    }

    /**
     * �T�[�r�X�̊J�n�������s���B
     * <p>
     *
     * @exception Exception �T�[�r�X�̊J�n�Ɏ��s�����ꍇ
     */
    public void startService() throws Exception {
        if (requestPropertyAndContextKeyMapping == null || requestPropertyAndContextKeyMapping.size() == 0) {
            throw new IllegalArgumentException("RequestPropertyAndContextKeyMapping must be specified.");
        }
        if (threadContextServiceName != null) {
            threadContext = (Context) ServiceManagerFactory.getServiceObject(threadContextServiceName);
        }
        Iterator entries = requestPropertyAndContextKeyMapping.entrySet().iterator();
        while (entries.hasNext()) {
            Map.Entry entry = (Map.Entry) entries.next();
            Property prop = propertyAccess.getProperty((String) entry.getKey());
            if (!prop.isReadable(HttpServletRequest.class)) {
                throw new IllegalArgumentException("'" + entry.getKey() + "' cannot acquire from a request. value=null");
            }
        }

    }

    /**
     * RequestPropertyAndContextKeyMapping�ɐݒ肳�ꂽ�}�b�s���O�ɏ]���A
     * HttpServletRequest�̃v���p�e�B�l��ThreadContext�ɐݒ肵�āA���̃C���^�[�Z�v�^���Ăяo���B
     * <p>
     * �T�[�r�X���J�n����Ă��Ȃ��ꍇ�́A���������Ɏ��̃C���^�[�Z�v�^���Ăяo���B<br>
     *
     * @param context �Ăяo���̃R���e�L�X�g���
     * @param chain ���̃C���^�[�Z�v�^���Ăяo�����߂̃`�F�[��
     * @return �Ăяo�����ʂ̖߂�l
     * @exception Throwable �Ăяo����ŗ�O�����������ꍇ�A�܂��͂��̃C���^�[�Z�v�^�ŔC�ӂ̗�O�����������ꍇ�B�A���A
     *                �{���Ăяo����鏈����throw���Ȃ�RuntimeException�ȊO�̗�O��throw���Ă�
     *                �A�Ăяo�����ɂ͓`�d����Ȃ��B
     */
    public Object invokeFilter(ServletFilterInvocationContext context, InterceptorChain chain) throws Throwable {
        if (getState() == STARTED) {
            final HttpServletRequest request = (HttpServletRequest) context.getServletRequest();
            Iterator entries = requestPropertyAndContextKeyMapping.entrySet().iterator();
            while (entries.hasNext()) {
                Map.Entry entry = (Map.Entry) entries.next();
                Object value = null;
                try {
                    value = propertyAccess.get(request, (String) entry.getKey());
                } catch (InvocationTargetException e) {
                    throw e.getTargetException();
                }
                threadContext.put(entry.getValue(), value);
            }
        }
        return chain.invokeNext(context);
    }
}
