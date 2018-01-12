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
// �p�b�P�[�W
package jp.ossc.nimbus.service.debug;
//�C���|�[�g

/**
 * �f�o�b�O�N���X<p>
 * �f�o�b�O���o�͂��s��
 * @version $Name:  $
 * @author K.Nagai
 * @since 1.0
 */
public interface Debug {
    /**
     * �f�o�b�O���o��(��O��)<p>
     * �f�o�b�O�����o�͂���BisXXX���g�p���ďo�͉ۏ���
     * �₢���킹��O�ɃR�[������ƃT�[�r�X��O�𔭐�����B
     * @param str �G���[���b�Z�[�W
     * @param e ��O 
     * @throws isXXX�֐��g�p�O�ɂ��̊֐����R�[�������ۂ̗�O�B
     */
    public void write(String str,Throwable e);
    /**
     * �f�o�b�O���o��<p>
     * �f�o�b�O�����o�͂���BisXXX���g�p���ďo�͉ۏ���
     * �₢���킹��O�ɃR�[������ƃT�[�r�X��O�𔭐�����B
     * @param str �G���[���b�Z�[�W
     * @throws  isXXX�֐��g�p�O�ɂ��̊֐����R�[�������ۂ̗�O�B
     */
    public void write(String str);
    
    /**
     * �f�o�b�O���DUMP�o��<p>
     * �f�o�b�O����DUMP���o�͂���BisXXX���g�p���ďo�͉ۏ���
     * �₢���킹��O�ɃR�[������ƃT�[�r�X��O�𔭐�����B
     * @param object �G���[���b�Z�[�W
     * @throws  isXXX�֐��g�p�O�ɂ��̊֐����R�[�������ۂ̗�O�B
     */
    public void dump(Object object);
    /**
     * �f�o�b�O���DUMP�o��<p>
     * �f�o�b�O����DUMP���o�͂���BisXXX���g�p���ďo�͉ۏ���
     * �₢���킹��O�ɃR�[������ƃT�[�r�X��O�𔭐�����B
     * @param objects �G���[���b�Z�[�W
     * @throws  isXXX�֐��g�p�O�ɂ��̊֐����R�[�������ۂ̗�O�B
     */
    public void dump(Object[] objects);
    /**
     * �f�o�b�O���DUMP�o��<p>
     * �f�o�b�O����DUMP���o�͂���BisXXX���g�p���ďo�͉ۏ���
     * �₢���킹��O�ɃR�[������ƃT�[�r�X��O�𔭐�����B
     * @param msg �G���[���b�Z�[�W
     * @param object �G���[���b�Z�[�W
     * @throws  isXXX�֐��g�p�O�ɂ��̊֐����R�[�������ۂ̗�O�B
     */
    public void dump(String msg,Object object);
    /**
     * �f�o�b�O���DUMP�o��<p>
     * �f�o�b�O����DUMP���o�͂���BisXXX���g�p���ďo�͉ۏ���
     * �₢���킹��O�ɃR�[������ƃT�[�r�X��O�𔭐�����B
     * @param msg �G���[���b�Z�[�W
     * @param objects �G���[���b�Z�[�W
     * @throws  isXXX�֐��g�p�O�ɂ��̊֐����R�[�������ۂ̗�O�B
     */
    public void dump(String msg,Object[] objects);
    
    /**
     * �f�o�b�O���x�����o�͉�
     * @return �f�o�b�O���x���̏����o�͂��邩
     */
    public boolean isDebug();
    /**
     * �C���t�H�[���[�V�������x�����o�͉�
     * @return �C���t�H�[���[�V�������x���̏����o�͂��邩
     */
    public boolean isInfo();
    /**
     * �x�����x�����o�͉�
     * @return �x�����x���̏����o�͂��邩
     */
    public boolean isWarn();
    /**
     * �G���[���x�����o�͉�
     * @return �G���[���x���̏����o�͂��邩
     */
    public boolean isError();
    /**
     * �v���I�G���[���x�����o�͉�
     * @return �v���I�G���[���x���̏����o�͂��邩
     */
    public boolean isFatalError();
}
