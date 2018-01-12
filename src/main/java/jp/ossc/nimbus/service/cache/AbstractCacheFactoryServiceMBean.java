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

import jp.ossc.nimbus.core.*;

/**
 * {@link AbstractCacheFactoryService}��MBean�C���^�t�F�[�X<p>
 * 
 * @author M.Takata
 * @see AbstractCacheFactoryService
 */
public interface AbstractCacheFactoryServiceMBean
 extends FactoryServiceBaseMBean{
    
    /**
     * ���ӂꐧ����s��OverflowController�C���^�t�F�[�X�����������T�[�r�X�̃T�[�r�X���̔z���ݒ肷��B<p>
     *
     * @param names ���ӂꐧ����s��OverflowController�C���^�t�F�[�X�����������T�[�r�X�̃T�[�r�X���̔z��
     */
    public void setOverflowControllerServiceNames(ServiceName[] names);
    
    /**
     * ���ӂꐧ����s��OverflowController�C���^�t�F�[�X�����������T�[�r�X�̃T�[�r�X���̔z����擾����B<p>
     *
     * @return ���ӂꐧ����s��OverflowController�C���^�t�F�[�X�����������T�[�r�X�̃T�[�r�X���̔z��
     */
    public ServiceName[] getOverflowControllerServiceNames();
    
    /**
     * �T�[�r�X�̒�~���ɃL���b�V�����N���A���邩�ǂ�����ݒ肷��B<p>
     * �f�t�H���g�́Afalse�B<br>
     *
     * @param isClear �T�[�r�X�̒�~���ɃL���b�V�����N���A����ꍇ�́Atrue
     */
    public void setClearOnStop(boolean isClear);
    
    /**
     * �T�[�r�X�̒�~���ɃL���b�V�����N���A���邩�ǂ����𒲂ׂ�B<p>
     *
     * @return �T�[�r�X�̒�~���ɃL���b�V�����N���A����ꍇ�́Atrue
     */
    public boolean isClearOnStop();
    
    /**
     * �T�[�r�X�̔j�����ɃL���b�V�����N���A���邩�ǂ�����ݒ肷��B<p>
     * �f�t�H���g�́Atrue�B<br>
     *
     * @param isClear �T�[�r�X�̔j�����ɃL���b�V�����N���A����ꍇ�́Atrue
     */
    public void setClearOnDestroy(boolean isClear);
    
    /**
     * �T�[�r�X�̔j�����ɃL���b�V�����N���A���邩�ǂ����𒲂ׂ�B<p>
     *
     * @return �T�[�r�X�̔j�����ɃL���b�V�����N���A����ꍇ�́Atrue
     */
    public boolean isClearOnDestroy();
    
    /**
     * �S�ẴL���b�V���l���A�Ǘ��Ώۂ̑S�ẴL���b�V������폜����B<p>
     */
    public void clear();
}
