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
package jp.ossc.nimbus.service.test.resource;

import java.io.File;

import jp.ossc.nimbus.core.ServiceBaseMBean;

/**
 * {@link LocalStubResourceManagerService}��MBean�C���^�t�F�[�X<p>
 * 
 * @author M.Aono
 * @see LocalStubResourceManagerService
 */
public interface LocalStubResourceManagerServiceMBean extends ServiceBaseMBean{
    
    /**
     * �A�b�v���[�h���ꂽ�t�@�C�����_�E�����[�h�����܂ŕۑ����Ă����ꎞ�f�B���N�g�����擾����B<p>
     *
     * @return �ꎞ�f�B���N�g��
     */
    public File getTemporaryDirectory();
    
    /**
     * �A�b�v���[�h���ꂽ�t�@�C�����_�E�����[�h�����܂ŕۑ����Ă����ꎞ�f�B���N�g����ݒ肷��B<p>
     * �w�肵�Ȃ��ꍇ�́A"java.io.tmpdir"�B<br>
     * �A�b�v���[�h���ꂽ�t�@�C���́A�����Ŏw�肳�ꂽ�ꎞ�f�B���N�g�����ɁA�N���X��/�^�C���X�^���v�̃f�B���N�g�����쐬���A���̉��ɕۑ������B�܂��A�����̃f�B���N�g���́AJavaVM�I�����ɑS�č폜�����B<br>
     *
     * @param path �ꎞ�f�B���N�g��
     */
    public void setTemporaryDirectory(File path);
}
