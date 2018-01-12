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
package jp.ossc.nimbus.service.keepalive;

import java.util.*;
//
/**
 * �T�[�o��Ԏ擾�C���^�[�t�F�C�X�B<p>
 *
 * @author H.Nakano
 * @version  1.00 �쐬: 2003/10/08 - H.Nakano
 */
public interface QueryKeepAlive {
    
    /**
     * �T�[�o�ғ���Ԃ��i�[���ꂽ�}�b�v���擾����B<p>
     * �T�[�o�̃L�[�A�T�[�o�̉ғ���ԁiBoolean�^�j���i�[����Ă���B<br>
     *
     * @return �T�[�o�ғ���Ԃ��i�[���ꂽ�}�b�v
     */
    public Map getKeepAliveMap();
    
    /**
     * �T�[�o�ғ���ԃe�[�u���̏�Ԃ��X�V����B<p>
     *
     * @param msid �T�[�o�̃L�[
     * @param keepAlive �ғ���ԁitrue:���s���Afalse:��~���j
     */
    public void updateTbl(Object msid, boolean keepAlive);
    
    /**
     * �D�揇�ʕ����ŏo�͉\�ȃT�[�o�̃��X�g���擾����B<p>
     * 
     * @return �D�揇�ʕ����ŏo�͉\�ȃT�[�o�̃��X�g
     */
    public List getPriolityAry();
    
    /**
     * ���E���h���r�������ŏo�͉\�ȃT�[�o�̃��X�g���擾����B<p>
     *
     * @return ���E���h���r�������ŏo�͉\�ȃT�[�o�̃��X�g
     */
    public List getRoundrobinAry();
    
    /**
     * �D�揇�ʕ����ŏo�͉\�ȃT�[�o�̃��X�g���擾����B<p>
     *
     * @param available ���p�\�ȃT�[�o�L�[�̏W��
     * @return �D�揇�ʕ����ŏo�͉\�ȃT�[�o�̃��X�g
     */
    public List getPriolityAry(Set available);
    
    /**
     * ���E���h���r�������ŏo�͉\�ȃT�[�o�̃��X�g���擾����B<p>
     *
     * @param available ���p�\�ȃT�[�o�L�[�̏W��
     * @return ���E���h���r�������ŏo�͉\�ȃT�[�o�̃��X�g
     */
    public List getRoundrobinAry(Set available);
}
