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
package jp.ossc.nimbus.service.ga;

import jp.ossc.nimbus.core.ServiceBaseMBean;

/**
 * {@link DefaultConvergenceConditionService}��MBean�C���^�t�F�[�X<p>
 * 
 * @author M.Takata
 * @see DefaultConvergenceConditionService
 */
public interface DefaultConvergenceConditionServiceMBean extends ServiceBaseMBean{
    
    /**
     * ��������߂邽�߂̍ő吢�㐔��ݒ肷��B<p>
     * �f�t�H���g�́A0�Ŏ�������܂Œ��߂Ȃ��B<br>
     *
     * @param max �ő吢�㐔
     */
    public void setMaxGenerationNum(int max);
    
    /**
     * ��������߂邽�߂̍ő吢�㐔���擾����B<p>
     *
     * @return �ő吢�㐔
     */
    public int getMaxGenerationNum();
    
    /**
     * �K���l���������ׂ�臒l�i臒l�͊܂ށj��ݒ肷��B<p>
     * �K���l������臒l�ɓ��B������A���������Ƃ݂Ȃ��B<br>
     *
     * @param threshold 臒l
     */
    public void setThreshold(Number threshold);
    
    /**
     * �K���l���������ׂ�臒l�i臒l�͊܂ށj���擾����B<p>
     *
     * @return 臒l
     */
    public Number getThreshold();
    
    /**
     * ���݂̐���Ɖ�����O�̓K���l���r���邩��ݒ肷��B<p>
     * �f�t�H���g�́A1��1�O�̐���B<br>
     *
     * @param index ���㐔
     */
    public void setPreIndex(int index);
    
    /**
     * ���݂̐���Ɖ�����O�̓K���l���r���邩���擾����B<p>
     *
     * @return ���㐔
     */
    public int getPreIndex();
    
    /**
     * ���e�덷��ݒ肷��B<p>
     * ��r�Ώۂ̓K���l�ƌ��݂̐���̓K���l���A���e�덷�ȉ��ɂȂ����ꍇ�A���������Ɣ��f����B<br>
     *
     * @param error ���e�덷
     */
    public void setPermissibleError(Number error);
    
    /**
     * ���e�덷���擾����B<p>
     *
     * @return ���e�덷
     */
    public Number getPermissibleError();
    
    /**
     * ���΋��e�덷��ݒ肷��B<p>
     * |��r�Ώۂ̓K���l - ���݂̐���̓K���l| / ���݂̐���̓K���l ���A���e�덷�ȉ��ɂȂ����ꍇ�A���������Ɣ��f����B<br>
     *
     * @param error ���΋��e�덷
     */
    public void setPermissibleRelativeError(float error);
    
    /**
     * ���΋��e�덷���擾����B<p>
     *
     * @return ���΋��e�덷
     */
    public float getPermissibleRelativeError();
}
