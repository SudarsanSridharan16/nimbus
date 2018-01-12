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

import jp.ossc.nimbus.core.*;
import jp.ossc.nimbus.util.SynchronizeMonitor;

/**
 * {@link BreakPointInterceptorService}��MBean�C���^�t�F�[�X<p>
 * 
 * @author M.Takata
 * @see BreakPointInterceptorService
 */
public interface BreakPointInterceptorServiceMBean extends ServiceBaseMBean, BreakPoint{
    
    /**
     * �u���C�N�|�C���g��������œ\�鎖�������萔�B<p>
     */
    public static final int BREAK_POINT_IN = 1;
    
    /**
     * �u���C�N�|�C���g���o�����œ\�鎖�������萔�B<p>
     */
    public static final int BREAK_POINT_OUT = 2;
    
    /**
     * �u���C�N�|�C���g��\��ꏊ��ݒ肷��B<p>
     * �f�t�H���g�́A{@link #BREAK_POINT_IN}�B<br>
     *
     * @param breakPoint �u���C�N�|�C���g��\��ꏊ
     * @see #BREAK_POINT_IN
     * @see #BREAK_POINT_OUT
     */
    public void setBreakPoint(int breakPoint);
    
    /**
     * �u���C�N�|�C���g��\��ꏊ���擾����B<p>
     *
     * @return �u���C�N�|�C���g��\��ꏊ
     */
    public int getBreakPoint();
    
    /**
     * �u���C�N�|�C���g�ŁAwait()����ۂ̃��j�^�[��ݒ肷��B<p>
     *
     * @param monitor wait()�̃��j�^�[
     */
    public void setMonitor(SynchronizeMonitor monitor);
    
    /**
     * �u���C�N�|�C���g�ŁAwait()����ۂ̃��j�^�[���擾����B<p>
     *
     * @return wait()�̃��j�^�[
     */
    public SynchronizeMonitor getMonitor();
    
    /**
     * �u���C�N�|�C���g�őҋ@����ő厞��[ms]��ݒ肷��B<p>
     * �f�t�H���g�́A0�Ŗ����ɑ҂B<br>
     * �ő厞�Ԃ܂őҋ@������́A�����I�ɍĊJ�����B<br>
     *
     * @param timeout �u���C�N�|�C���g�őҋ@����ő厞��[ms]
     */
    public void setTimeout(long timeout);
    
    /**
     * �u���C�N�|�C���g�őҋ@����ő厞��[ms]���擾����B<p>
     *
     * @return �u���C�N�|�C���g�őҋ@����ő厞��[ms]
     */
    public long getTimeout();
}
