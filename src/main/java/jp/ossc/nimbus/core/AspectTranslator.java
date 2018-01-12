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

import java.security.*;

/**
 * �A�X�y�N�g�ϊ��B<p>
 * �A�X�y�N�g�w���̊T�O�ɑ����āA�N���X�t�@�C����ϊ�����ϊ��҂̎������ׂ��C���^�t�F�[�X�ł���B<br>
 *
 * @author M.Takata
 * @see NimbusClassLoader#addAspectTranslator(AspectTranslator)
 */
public interface AspectTranslator{
    
    /**
     * ���̃A�X�y�N�g�ϊ������ʂ���A�X�y�N�g�̃L�[���擾����B<p>
     * �����L�[�����A�X�y�N�g�́A�d�����ăA�X�y�N�g����Ȃ��B<br>
     *
     * @return �A�X�y�N�g�̃L�[
     */
    public String getAspectKey();
    
    /**
     * �N���X�t�@�C����ϊ�����B<p>
     *
     * @param loader �N���X�t�@�C�������[�h����N���X���[�_
     * @param className �N���X��
     * @param domain �N���X�̃h���C��
     * @param bytecode �N���X�t�@�C���̃o�C�g�z��
     * @return �ϊ���̃N���X�t�@�C���̃o�C�g�z��B�ϊ��ΏۂłȂ��ꍇ�́Anull��Ԃ��B
     */
    public byte[] transform(
        ClassLoader loader,
        String className,
        ProtectionDomain domain,
        byte[] bytecode
    );
}
