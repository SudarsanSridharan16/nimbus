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
package jp.ossc.nimbus.service.websocket;

import java.util.Properties;

import jp.ossc.nimbus.core.ServiceBaseMBean;
import jp.ossc.nimbus.core.ServiceName;

/**
 * {@link ExceptionHandlerMappingService}のMBeanインタフェース。
 * <p>
 *
 * @author M.Ishida
 */
public interface ExceptionHandlerMappingServiceMBean extends ServiceBaseMBean {

    /**
     * 例外クラス名と{@link ExceptionHandler}サービスのサービス名のマッピングを設定する。
     * <p>
     *
     * @param map 例外クラス名とExceptionHandlerサービスのサービス名のマッピング。例外クラス名=
     *            ExceptionHandlerサービスのサービス名
     */
    public void setExceptionAndHandlerMapping(Properties map);

    /**
     * 例外クラス名と{@link ExceptionHandler}サービスのサービス名のマッピングを取得する。
     * <p>
     *
     * @return 例外クラス名とExceptionHandlerサービスのサービス名のマッピング
     */
    public Properties getExceptionAndHandlerMapping();

    /**
     * 発生した例外にマッピングされた{@link ExceptionHandler}
     * サービスがない場合に使用されるExceptionHandlerサービスのサービス名を設定する。
     * <p>
     *
     * @param name ExceptionHandlerサービスのサービス名
     */
    public void setDefaultExceptionHandlerServiceName(ServiceName name);

    /**
     * 発生した例外にマッピングされた{@link ExceptionHandler}
     * サービスがない場合に使用されるExceptionHandlerサービスのサービス名を取得する。
     * <p>
     *
     * @return ExceptionHandlerサービスのサービス名
     */
    public ServiceName getDefaultExceptionHandlerServiceName();
}
