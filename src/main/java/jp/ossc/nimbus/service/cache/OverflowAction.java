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
package jp.ossc.nimbus.service.cache;

/**
 * あふれ動作。<p>
 * あふれが発生した場合に行う動作を実装するためのインタフェースである。<br>
 *
 * @author M.Takata
 */
public interface OverflowAction{
    
    /**
     * あふれ制御を設定する。<p>
     * あふれ動作の実装において、あふれ制御を再度実行する必要がある場合などが考えられるため、あふれ制御の参照を設定する。<br>
     *
     * @param controller あふれ制御
     */
    public void setOverflowController(OverflowController controller);
    
    /**
     * あふれ動作を行う。<p>
     *
     * @param validator あふれ検証を行ったOverflowValidator
     * @param algorithm あふれキャッシュ参照を決定したOverflowAlgorithm
     * @param ref あふれたキャッシュ参照
     */
    public void action(
        OverflowValidator validator,
        OverflowAlgorithm algorithm,
        CachedReference ref
    );
    
    /**
     * あふれ動作を行うために保持している情報を初期化する。<p>
     */
    public void reset();
}
