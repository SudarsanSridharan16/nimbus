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
package jp.ossc.nimbus.service.scheduler2;

/**
 * デフォルトスケジュール依存。<p>
 *
 * @author M.Takata
 */
public class DefaultScheduleDepends implements ScheduleDepends, java.io.Serializable{
    
    protected String masterId;
    protected boolean isIgnoreError;
    
    /**
     * 空のインスタンスを生成する。<p>
     */
    public DefaultScheduleDepends(){
    }
    
    /**
     * インスタンスを生成する。<p>
     *
     * @param id 依存するマスタID
     * @param isIgnore エラーの場合、無視するかどうか
     */
    public DefaultScheduleDepends(String id, boolean isIgnore){
        masterId = id;
        isIgnoreError = isIgnore;
    }
    
    public String getMasterId(){
        return masterId;
    }
    
    /**
     * 依存するスケジュールのマスタIDを設定する。<p>
     *
     * @param id 依存するスケジュールのマスタID
     */
    public void setMasterId(String id){
        masterId = id;
    }
    
    public boolean isIgnoreError(){
        return isIgnoreError;
    }
    
    /**
     * 依存するスケジュールがエラーの場合、無視するかどうかを設定する。<p>
     *
     * @param isIgnore 無視する場合、true
     */
    public void setIgnoreError(boolean isIgnore){
        isIgnoreError = isIgnore;
    }
}