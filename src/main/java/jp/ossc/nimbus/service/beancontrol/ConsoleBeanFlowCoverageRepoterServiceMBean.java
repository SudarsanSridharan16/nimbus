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
package jp.ossc.nimbus.service.beancontrol;

import jp.ossc.nimbus.core.*;

/**
 * {@link ConsoleBeanFlowCoverageRepoterService}のMBeanインタフェース。<p>
 * 
 * @author M.Takata
 */
public interface ConsoleBeanFlowCoverageRepoterServiceMBean
 extends ServiceBaseMBean{
    
    /**
     * {@link jp.ossc.nimbus.service.beancontrol.interfaces.BeanFlowInvokerFactory BeanFlowInvokerFactory}のサービス名を取得する。<p>
     *
     * @return BeanFlowInvokerFactoryのサービス名
     */
    public ServiceName getBeanFlowInvokerFactoryServiceName();
    
    /**
     * {@link jp.ossc.nimbus.service.beancontrol.interfaces.BeanFlowInvokerFactory BeanFlowInvokerFactory}のサービス名を設定する。<p>
     *
     * @param name BeanFlowInvokerFactoryのサービス名
     */
    public void setBeanFlowInvokerFactoryServiceName(ServiceName name);
    
    /**
     * サービスの停止時にレポートを出力するかどうかを設定する。<p>
     * デフォルトは、falseで出力しない。<br>
     *
     * @param isReport 出力する場合、true
     */
    public void setReportOnStop(boolean isReport);
    
    /**
     * サービスの停止時にレポートを出力するかどうかを判定する。<p>
     *
     * @return trueの場合、出力する
     */
    public boolean isReportOnStop();
    
    /**
     * 詳細をレポートに出力するかどうかを設定する。<p>
     * デフォルトは、falseで出力しない。<br>
     *
     * @param isDetail 出力する場合、true
     */
    public void setDetail(boolean isDetail);
    
    /**
     * 詳細をレポートに出力するかどうかを判定する。<p>
     *
     * @return trueの場合、出力する
     */
    public boolean isDetail();
    
    /**
     * レポートを取得する。<p>
     *
     * @return レポート
     */
    public String displayReport();
    
    /**
     * レポートを出力する。<p>
     *
     * @exception Exception 出力に失敗した場合
     */
    public void report() throws Exception;
}
