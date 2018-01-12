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
package jp.ossc.nimbus.core;

import java.util.Set;

/**
 * {@link DefaultServiceLoaderService}��MBean�C���^�t�F�[�X<p>
 * 
 * @author M.Takata
 * @see DefaultServiceLoaderService
 */
public interface DefaultServiceLoaderServiceMBean extends ServiceBaseMBean, ServiceLoader{
    /**
     * �T�[�r�X��`XML�t�@�C����DTD�Ō��؂��邩�ǂ������w�肷��B<p>
     * �f�t�H���g�ł́A���؂��Ȃ��B<br>
     *
     * @param validate ���؂���ꍇtrue�B
     */
    public void setValidate(boolean validate);
    
    /**
     * �T�[�r�X��`XML�t�@�C����DTD�Ō��؂��邩�ǂ����𒲂ׂ�B<p>
     *
     * @return ���؂���ꍇtrue�B
     */
    public boolean isValidate();
    
    /**
     * �����܂łɃ��[�h�����T�[�r�X���S�Đ���ɊJ�n�ł��Ă��邩���`�F�b�N���邩�ǂ�����ݒ肷��B<p>
     * ���̑�����true�ɂ��Ă����ƁA����ServiceLoader�̋N���������ɁA{@link ServiceManagerFactory#checkLoadManagerCompleted()}���Ăяo���B
     *
     * @param isCheck �`�F�b�N����ꍇtrue
     */
    public void setCheckLoadManagerCompleted(boolean isCheck);
    
    /**
     * �����܂łɃ��[�h�����T�[�r�X���S�Đ���ɊJ�n�ł��Ă��邩���`�F�b�N���邩�ǂ����𒲂ׂ�B<p>
     *
     * @return �`�F�b�N����ꍇtrue
     */
    public boolean isCheckLoadManagerCompleted();
    
    /**
     * �w�肵���}�l�[�W���̃T�[�r�X���S�Đ���ɊJ�n�ł��Ă��邩���`�F�b�N���邩�ǂ�����ݒ肷��B<p>
     * ���̑������w�肷��ƁA����ServiceLoader�̋N���������ɁA{@link ServiceManagerFactory#checkLoadManagerCompletedBy(Set)}���Ăяo���B
     *
     * @param managerNames �`�F�b�N����}�l�[�W�����̏W��
     */
    public void setCheckLoadManagerCompletedBy(String[] managerNames);
    
    /**
     * �T�[�r�X���S�Đ���ɊJ�n�ł��Ă��邩���`�F�b�N����}�l�[�W�����̏W�����擾����B<p>
     *
     * @return �`�F�b�N����}�l�[�W�����̏W��
     */
    public String[] getCheckLoadManagerCompletedBy();
}