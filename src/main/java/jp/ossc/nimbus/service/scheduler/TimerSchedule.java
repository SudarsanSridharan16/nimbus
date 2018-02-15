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
package jp.ossc.nimbus.service.scheduler;

import java.util.*;

/**
 * タイマースケジュール。<p>
 * {@link TimerSchedulerService}に登録するスケジュールが実装すべきインタフェースである。<br>
 *
 * @author M.Takata
 */
public interface TimerSchedule extends Schedule{
    
    /**
     * 依存するスケジュールの終了を待機しているかどうかを判定する。<p>
     *
     * @return 依存するスケジュールの終了を待機している場合はtrue
     */
    public boolean isWaiting();
    
    /**
     * 依存するスケジュールの終了待ちでタイムアウトしたかどうかを判定する。<p>
     *
     * @return 依存するスケジュールの終了待ちでタイムアウトした場合はtrue
     */
    public boolean isTimeout();
    
    /**
     * スケジューラを設定する。<p>
     *
     * @param scheduler スケジューラ
     */
    public void setScheduler(Scheduler scheduler);
    
    /**
     * タスクを強制実行する。<p>
     */
    public void executeForce();
    
    /**
     * タスクを強制的に遅延実行する。<p>
     * 
     * @param timer タイマー
     * @param delay 遅延時間[ms]
     */
    public void executeForce(Timer timer, long delay);
    
    /**
     * タスクを強制的に指定時刻実行する。<p>
     * 
     * @param timer タイマー
     * @param time 実行時刻
     */
    public void executeForce(Timer timer, Date time);
    
    /**
     * このスケジュールをタイマーに登録する。<p>
     *
     * @param timer タイマー
     */
    public void schedule(Timer timer);
    
    /**
     * スケジュールをキャンセルする。<p>
     */
    public void cancel();
    
    /**
     * スケジュールが最後に実行された時刻を取得する。<p>
     *
     * @return 最終実行時刻
     */
    public Date getLastExecutionTime();
    
    /**
     * スケジュールが実行される時刻を取得する。<p>
     * まだ実行されていない場合、戻り値は未定義。
     *
     * @return 実行時刻
     */
    public Date getScheduledExecutionTime();
}