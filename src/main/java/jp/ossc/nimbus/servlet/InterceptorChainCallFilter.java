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
package jp.ossc.nimbus.servlet;

import java.io.*;
import java.lang.reflect.*;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;

import jp.ossc.nimbus.core.*;
import jp.ossc.nimbus.beans.*;
import jp.ossc.nimbus.service.aop.*;

/**
 * �C���^�[�Z�v�^�`�F�[���Ăяo���t�B���^�B<p>
 * �T�[�u���b�g�t�B���^����C���^�[�Z�v�^�`�F�[�����Ăяo���t�B���^�N���X�ł���B<br>
 * �A�v���P�[�V������Օ��i�Ƃ��āA�T�[�u���b�g�t�B���^���쐬���鎖�����΂��΂���B<br>
 * �������A�T�[�u���b�g�t�B���^��web.xml�Œ�`�ł�����͌����Ă���A�쐬�����T�[�u���b�g�t�B���^�ɁA�l�X�ȃp�����[�^��n������C���W�F�N�V���������肷�鎖������ł���B�܂��A�t�B���^��ʂ��p�X�̐ݒ���A�O����v�y�ь����v�̃p�X�w����x�����ł��Ȃ��B<br>
 * Nimbus�Ƃ̑g�ݍ��킹�Ƃ����Ӗ��ł́A�t�B���^�ƃT�[�r�X�̘A�g���e�Ղł͂Ȃ��B<br>
 * �����ŁA�T�[�u���b�g�t�B���^�̋@�\���C���^�[�Z�v�^�ɈϏ����鎖�ŁA�����̎�_��₦��悤�ɂ����B<br>
 * �A�v���P�[�V������ՊJ���҂́A�T�[�u���b�g�t�B���^���J������ς��ɁA�C���^�[�Z�v�^���J�����鎖�ŁA�O�q�̎�_�������J�����\�ɂȂ�B<br>
 * ���̃T�[�u���b�g�t�B���^�ɂ́A�ȉ��̏������p�����[�^������B<br>
 * <table border="1" width="90%">
 *     <tr bgcolor="#cccccc"><th>#</th><th>�p�����[�^��</th><th>�l�̐���</th><th>�f�t�H���g</th></tr>
 *     <tr><td>1</td><td>InterceptorChainListServiceName</td><td>{@link InterceptorChainList}�C���^�t�F�[�X�����������T�[�r�X�̃T�[�r�X����ݒ肷��B</td><td></td></tr>
 *     <tr><td>2</td><td>InterceptorChainFactoryServiceName</td><td>{@link InterceptorChainFactory}�C���^�t�F�[�X�����������T�[�r�X�̃T�[�r�X����ݒ肷��B</td><td></td></tr>
 *     <tr><td>3</td><td>UseThreadLocalInterceptorChain</td><td>{@link DefaultThreadLocalInterceptorChain}���g�p���邩�ǂ�����ݒ肷��B<br>true���w�肵���ꍇ�A�g�p����B�f�t�H���g��true�B�A���AInterceptorChainFactoryServiceName���w�肵���ꍇ�́A{@link DefaultThreadLocalInterceptorChain}�͎g�p����Ȃ��B</td><td></td></tr>
 * </table>
 * <p>
 * �ȉ��ɁA�T�[�u���b�g�t�B���^��web.xml��`��������B<br>
 * <pre>
 * &lt;filter&gt;
 *     &lt;filter-name&gt;InterceptorChainCallFilter&lt;/filter-name&gt;
 *     &lt;filter-class&gt;jp.ossc.nimbus.servlet.InterceptorChainCallFilter&lt;/filter-class&gt;
 *     &lt;init-param&gt;
 *         &lt;param-name&gt;InterceptorChainListServiceName&lt;/param-name&gt;
 *         &lt;param-value&gt;Nimbus#InterceptorChainList&lt;/param-value&gt;
 *     &lt;/init-param&gt;
 * &lt;/filter&gt;
 * 
 * &lt;filter-mapping&gt;
 *     &lt;filter-name&gt;InterceptorChainCallFilter&lt;/filter-name&gt;
 *     &lt;url-pattern&gt;/*&lt;/url-pattern&gt;
 * &lt;/filter-mapping&gt;
 * </pre>
 *
 * @author M.Takata
 */
public class InterceptorChainCallFilter implements Filter, Invoker{
    
    public static final String INIT_PARAM_NAME_INTERCEPTOR_CHAIN_LIST_SERVICE_NAME = "InterceptorChainListServiceName";
    
    public static final String INIT_PARAM_NAME_INTERCEPTOR_CHAIN_FACTORY_SERVICE_NAME = "InterceptorChainFactoryServiceName";
    
    public static final String INIT_PARAM_NAME_USE_THREAD_LOCAL_INTERCEPTOR_CHAIN = "UseThreadLocalInterceptorChain";
    
    protected boolean isUseThreadLocalInterceptorChain = true;
    
    protected InterceptorChain interceptorChain;
    protected ServiceName interceptorChainFactoryServiceName;
    
    /**
     * �t�B���^�̏��������s���B<p>
     * �������p�����[�^�Ŏw�肳�ꂽ{@link InterceptorChainList}�T�[�r�X���擾���A{@link Invoker}�Ƃ��āA�������g��ݒ肷��B<br>
     *
     * @param filterConfig �t�B���^�\�����
     * @exception ServletException �t�B���^�̏������Ɏ��s�����ꍇ
     */
    public void init(FilterConfig filterConfig) throws ServletException{
        final ServiceNameEditor editor = new ServiceNameEditor();
        
        String name = filterConfig.getInitParameter(
            INIT_PARAM_NAME_INTERCEPTOR_CHAIN_LIST_SERVICE_NAME
        );
        ServiceName interceptorChainListServiceName = null;
        if(name != null){
            editor.setAsText(name);
            interceptorChainListServiceName = (ServiceName)editor.getValue();
        }
        
        final String isUseStr = filterConfig.getInitParameter(
            INIT_PARAM_NAME_USE_THREAD_LOCAL_INTERCEPTOR_CHAIN
        );
        if(isUseStr != null){
            isUseThreadLocalInterceptorChain
                = Boolean.valueOf(isUseStr).booleanValue();
        }
        
        if(interceptorChainListServiceName != null){
            if(isUseThreadLocalInterceptorChain){
                final DefaultThreadLocalInterceptorChain chain
                     = new DefaultThreadLocalInterceptorChain(
                        interceptorChainListServiceName,
                        null
                    );
                chain.setInvoker(this);
                interceptorChain = chain;
            }else{
                final DefaultInterceptorChain chain
                     = new DefaultInterceptorChain(
                        interceptorChainListServiceName,
                        null
                    );
                chain.setInvoker(this);
                interceptorChain = chain;
            }
        }
        
        name = filterConfig.getInitParameter(
            INIT_PARAM_NAME_INTERCEPTOR_CHAIN_FACTORY_SERVICE_NAME
        );
        if(name != null){
            editor.setAsText(name);
            interceptorChainFactoryServiceName = (ServiceName)editor.getValue();
        }
    }
    
    /**
     * �t�B���^�̔j���������s���B<p>
     */
    public void destroy(){
        interceptorChain = null;
        interceptorChainFactoryServiceName = null;
    }
    
    /**
     * �t�B���^�������s���B<p>
     * �������p�����[�^�Ŏw�肳�ꂽ{@link InterceptorChainList}�T�[�r�X���Ăяo���B<br>
     *
     * @param request ���N�G�X�g���
     * @param response ���X�|���X���
     * @param chain �t�B���^�`�F�[��
     */
    public void doFilter(
        ServletRequest request,
        ServletResponse response,
        FilterChain chain
    ) throws IOException, ServletException{
        if(interceptorChain == null && interceptorChainFactoryServiceName == null){
            chain.doFilter(request, response);
        }else if(interceptorChain != null){
            InterceptorChain ic = interceptorChain;
            if(!isUseThreadLocalInterceptorChain){
                ic = interceptorChain.cloneChain();
            }
            try{
                ic.setCurrentInterceptorIndex(-1);
                ic.invokeNext(
                    new ServletFilterInvocationContext(request, response, chain)
                );
            }catch(IOException e){
                throw e;
            }catch(ServletException e){
                throw e;
            }catch(RuntimeException e){
                throw e;
            }catch(Error err){
                throw err;
            }catch(Throwable th){
                throw new UndeclaredThrowableException(th);
            }finally{
                ic.setCurrentInterceptorIndex(-1);
            }
        }else{
            String reqPath = null;
            if(request instanceof HttpServletRequest){
                final HttpServletRequest httpReq = (HttpServletRequest)request;
                reqPath = httpReq.getServletPath();
                if(httpReq.getPathInfo() != null){
                    reqPath = reqPath + httpReq.getPathInfo();
                }
            }
            InterceptorChainFactory interceptorChainFactory = (InterceptorChainFactory)ServiceManagerFactory
                .getServiceObject(interceptorChainFactoryServiceName);
            InterceptorChain ic = interceptorChainFactory.getInterceptorChain(reqPath);
            if(ic == null){
                chain.doFilter(request, response);
            }else{
                ic.setInvoker(this);
                try{
                    ic.setCurrentInterceptorIndex(-1);
                    ic.invokeNext(
                        new ServletFilterInvocationContext(request, response, chain)
                    );
                }catch(IOException e){
                    throw e;
                }catch(ServletException e){
                    throw e;
                }catch(RuntimeException e){
                    throw e;
                }catch(Error err){
                    throw err;
                }catch(Throwable th){
                    throw new UndeclaredThrowableException(th);
                }finally{
                    ic.setCurrentInterceptorIndex(-1);
                }
            }
        }
    }
    
    /**
     * �t�B���^�`�F�[�����Ăяo���B<p>
     *
     * @param context �Ăяo���̃R���e�L�X�g���
     * @return �Ăяo�����ʂ̖߂�l
     * @exception Throwable �Ăяo����ŗ�O�����������ꍇ�A�܂��͂����ŔC�ӂ̗�O�����������ꍇ�B�A���A�{���Ăяo����鏈����throw���Ȃ�RuntimeException�ȊO�̗�O��throw���Ă��A�Ăяo�����ɂ͓`�d����Ȃ��B
     */
    public Object invoke(InvocationContext context) throws Throwable{
        final ServletFilterInvocationContext filterContext
             = (ServletFilterInvocationContext)context;
        filterContext.getFilterChain().doFilter(
            filterContext.getServletRequest(),
            filterContext.getServletResponse()
        );
        return null;
    }
}