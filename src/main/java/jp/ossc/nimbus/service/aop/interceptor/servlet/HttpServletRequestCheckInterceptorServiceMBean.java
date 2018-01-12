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
package jp.ossc.nimbus.service.aop.interceptor.servlet;

import java.util.Properties;

/**
 * {@link HttpServletRequestCheckInterceptorService}��MBean�C���^�t�F�[�X�B<p>
 * 
 * @author M.Takata
 * @see HttpServletRequestCheckInterceptorService
 */
public interface HttpServletRequestCheckInterceptorServiceMBean
 extends ServletFilterInterceptorServiceMBean{
    
    /**
     * ���N�G�X�g�w�b�_��Content-Length�̍ő�l��ݒ肷��B<p>
     * �ݒ肵�Ȃ��ꍇ�́AContent-Length�̍ő�l�̓`�F�b�N����Ȃ��B<br>
     *
     * @param max Content-Length�̍ő�l
     */
    public void setMaxContentLength(int max);
    
    /**
     * ���N�G�X�g�w�b�_��Content-Length�̍ő�l���擾����B<p>
     *
     * @return Content-Length�̍ő�l
     */
    public int getMaxContentLength();
    
    /**
     * ���N�G�X�g�w�b�_��Content-Length�̍ŏ��l��ݒ肷��B<p>
     * �ݒ肵�Ȃ��ꍇ�́AContent-Length�̍ŏ��l�̓`�F�b�N����Ȃ��B<br>
     *
     * @param min Content-Length�̍ŏ��l
     */
    public void setMinContentLength(int min);
    
    /**
     * ���N�G�X�g�w�b�_��Content-Length�̍ŏ��l���擾����B<p>
     *
     * @return Content-Length�̍ŏ��l
     */
    public int getMinContentLength();
    
    /**
     * ���N�G�X�g�w�b�_��Content-Type��null�ł��鎖�����e���邩�ǂ�����ݒ肷��B<p>
     * �f�t�H���g�́Atrue�B<br>
     *
     * @param isAllow Content-Type��null�ł��鎖�����e����ꍇ�Atrue
     */
    public void setAllowNullContentType(boolean isAllow);
    
    /**
     * ���N�G�X�g�w�b�_��Content-Type��null�ł��鎖�����e���邩�ǂ����𔻒肷��B<p>
     *
     * @return true�̏ꍇ�AContent-Type��null�ł��鎖�����e����
     */
    public boolean isAllowNullContentType();
    
    /**
     * ���N�G�X�g�w�b�_��Content-Type�̒l�Ƃ��ėL���Ȓl��ݒ肷��B<p>
     * �ݒ肵�Ȃ��ꍇ�́AContent-Type�̒l�̓`�F�b�N����Ȃ��B<br>
     *
     * @param types Content-Type�̒l�Ƃ��ėL���Ȓl�̕�����z��
     */
    public void setValidContentTypes(String[] types);
    
    /**
     * ���N�G�X�g�w�b�_��Content-Type�̒l�Ƃ��ėL���Ȓl���擾����B<p>
     *
     * @return Content-Type�̒l�Ƃ��ėL���Ȓl�̕�����z��
     */
    public String[] getValidContentTypes();
    
    /**
     * ���N�G�X�g�w�b�_��Content-Type�̒l�Ƃ��Ė����Ȓl��ݒ肷��B<p>
     * �ݒ肵�Ȃ��ꍇ�́AContent-Type�̒l�̓`�F�b�N����Ȃ��B<br>
     *
     * @param types Content-Type�̒l�Ƃ��Ė����Ȓl�̕�����z��
     */
    public void setInvalidContentTypes(String[] types);
    
    /**
     * ���N�G�X�g�w�b�_��Content-Type�̒l�Ƃ��Ė����Ȓl���擾����B<p>
     *
     * @return Content-Type�̒l�Ƃ��Ė����Ȓl�̕�����z��
     */
    public String[] getInvalidContentTypes();
    
    /**
     * ���N�G�X�g�{�f�B�̕����G���R�[�f�B���O���w�肳��Ă��Ȃ��������e���邩�ǂ�����ݒ肷��B<p>
     * �f�t�H���g�́Atrue�B<br>
     *
     * @param isAllow ���N�G�X�g�{�f�B�̕����G���R�[�f�B���O���w�肳��Ă��Ȃ��������e����ꍇ�Atrue
     */
    public void setAllowNullCharacterEncoding(boolean isAllow);
    
    /**
     * ���N�G�X�g�{�f�B�̕����G���R�[�f�B���O���w�肳��Ă��Ȃ��������e���邩�ǂ����𔻒肷��B<p>
     *
     * @return true�̏ꍇ�A���N�G�X�g�{�f�B�̕����G���R�[�f�B���O���w�肳��Ă��Ȃ��������e����
     */
    public boolean isAllowNullCharacterEncoding();
    
    /**
     * ���N�G�X�g�{�f�B�̕����G���R�[�f�B���O�Ƃ��ėL���Ȓl��ݒ肷��B<p>
     * �ݒ肵�Ȃ��ꍇ�́A�����G���R�[�f�B���O�̓`�F�b�N����Ȃ��B<br>
     *
     * @param encodings �����G���R�[�f�B���O�Ƃ��ėL���Ȓl�̕�����z��
     */
    public void setValidCharacterEncodings(String[] encodings);
    
    /**
     * ���N�G�X�g�{�f�B�̕����G���R�[�f�B���O�Ƃ��ėL���Ȓl���擾����B<p>
     *
     * @return �����G���R�[�f�B���O�Ƃ��ėL���Ȓl�̕�����z��
     */
    public String[] getValidCharacterEncodings();
    
    /**
     * ���N�G�X�g�{�f�B�̕����G���R�[�f�B���O�Ƃ��Ė����Ȓl��ݒ肷��B<p>
     * �ݒ肵�Ȃ��ꍇ�́A�����G���R�[�f�B���O�̓`�F�b�N����Ȃ��B<br>
     *
     * @param encodings �����G���R�[�f�B���O�Ƃ��Ė����Ȓl�̕�����z��
     */
    public void setInvalidCharacterEncodings(String[] encodings);
    
    /**
     * ���N�G�X�g�{�f�B�̕����G���R�[�f�B���O�Ƃ��Ė����Ȓl���擾����B<p>
     *
     * @return �����G���R�[�f�B���O�Ƃ��Ė����Ȓl�̕�����z��
     */
    public String[] getInvalidCharacterEncodings();
    
    /**
     * ���N�G�X�g�w�b�_��Accept-Language��null�ł��鎖�����e���邩�ǂ�����ݒ肷��B<p>
     * �f�t�H���g�́Atrue�B<br>
     *
     * @param isAllow Accept-Language��null�ł��鎖�����e����ꍇ�Atrue
     */
    public void setAllowNullLocale(boolean isAllow);
    
    /**
     * ���N�G�X�g�w�b�_��Accept-Language��null�ł��鎖�����e���邩�ǂ����𔻒肷��B<p>
     *
     * @return true�̏ꍇ�AAccept-Language��null�ł��鎖�����e����
     */
    public boolean isAllowNullLocale();
    
    /**
     * ���N�G�X�g�w�b�_��Accept-Language�̒l�Ƃ��ėL���Ȓl��ݒ肷��B<p>
     * �ݒ肵�Ȃ��ꍇ�́AAccept-Language�̒l�̓`�F�b�N����Ȃ��B<br>
     *
     * @param locales Accept-Language�̒l�Ƃ��ėL���Ȓl�i���K�\���j�̕�����z��
     */
    public void setValidLocales(String[] locales);
    
    /**
     * ���N�G�X�g�w�b�_��Accept-Language�̒l�Ƃ��ėL���Ȓl���擾����B<p>
     *
     * @return Accept-Language�̒l�Ƃ��ėL���Ȓl�i���K�\���j�̕�����z��
     */
    public String[] getValidLocales();
    
    /**
     * ���N�G�X�g�̃v���g�R���̒l�Ƃ��ėL���Ȓl��ݒ肷��B<p>
     * �ݒ肵�Ȃ��ꍇ�́A�v���g�R���̒l�̓`�F�b�N����Ȃ��B<br>
     *
     * @param protocols �v���g�R���̒l�Ƃ��ėL���Ȓl�i���K�\���j�̕�����z��
     */
    public void setValidProtocols(String[] protocols);
    
    /**
     * ���N�G�X�g�̃v���g�R���̒l�Ƃ��ėL���Ȓl���擾����B<p>
     *
     * @return �v���g�R���̒l�Ƃ��ėL���Ȓl�i���K�\���j�̕�����z��
     */
    public String[] getValidProtocols();
    
    /**
     * �N���C�A���g��IP�A�h���X�̒l�Ƃ��ėL���Ȓl��ݒ肷��B<p>
     * �ݒ肵�Ȃ��ꍇ�́AIP�A�h���X�̒l�̓`�F�b�N����Ȃ��B<br>
     *
     * @param addrs IP�A�h���X�̒l�Ƃ��ėL���Ȓl�i���K�\���j�̕�����z��
     */
    public void setValidRemoteAddrs(String[] addrs);
    
    /**
     * �N���C�A���g��IP�A�h���X�̒l�Ƃ��ėL���Ȓl���擾����B<p>
     *
     * @return IP�A�h���X�̒l�Ƃ��ėL���Ȓl�i���K�\���j�̕�����z��
     */
    public String[] getValidRemoteAddrs();
    
    /**
     * �N���C�A���g�̃z�X�g���Ƃ��ėL���Ȓl��ݒ肷��B<p>
     * �ݒ肵�Ȃ��ꍇ�́A�z�X�g���̓`�F�b�N����Ȃ��B<br>
     *
     * @param hosts �z�X�g���Ƃ��ėL���Ȓl�i���K�\���j�̕�����z��
     */
    public void setValidRemoteHosts(String[] hosts);
    
    /**
     * �N���C�A���g�̃z�X�g���Ƃ��ėL���Ȓl���擾����B<p>
     *
     * @return �z�X�g���Ƃ��ėL���Ȓl�i���K�\���j�̕�����z��
     */
    public String[] getValidRemoteHosts();
    
    /**
     * �N���C�A���g�̃|�[�g�ԍ��Ƃ��ėL���Ȓl��ݒ肷��B<p>
     * �ݒ肵�Ȃ��ꍇ�́A�|�[�g�ԍ��̓`�F�b�N����Ȃ��B<br>
     *
     * @param ports �|�[�g�ԍ��Ƃ��ėL���Ȓl�̔z��
     */
    public void setValidRemotePorts(int[] ports);
    
    /**
     * �N���C�A���g�̃|�[�g�ԍ��Ƃ��ėL���Ȓl���擾����B<p>
     *
     * @return �|�[�g�ԍ��Ƃ��ėL���Ȓl�̔z��
     */
    public int[] getValidRemotePorts();
    
    /**
     * ���N�G�X�gURL�̃X�L�[�}�̒l�Ƃ��ėL���Ȓl��ݒ肷��B<p>
     * �ݒ肵�Ȃ��ꍇ�́A�X�L�[�}�̒l�̓`�F�b�N����Ȃ��B<br>
     *
     * @param schemata �X�L�[�}�̒l�Ƃ��ėL���Ȓl�̕�����z��
     */
    public void setValidSchemata(String[] schemata);
    
    /**
     * ���N�G�X�gURL�̃X�L�[�}�̒l�Ƃ��ėL���Ȓl���擾����B<p>
     *
     * @return �X�L�[�}�̒l�Ƃ��ėL���Ȓl�̕�����z��
     */
    public String[] getValidSchemata();
    
    /**
     * ���N�G�X�gURL�̃z�X�g���̒l�Ƃ��ėL���Ȓl��ݒ肷��B<p>
     * �ݒ肵�Ȃ��ꍇ�́A�z�X�g���̒l�̓`�F�b�N����Ȃ��B<br>
     *
     * @param names �z�X�g���̒l�Ƃ��ėL���Ȓl�i���K�\���j�̕�����z��
     */
    public void setValidServerNames(String[] names);
    
    /**
     * ���N�G�X�gURL�̃z�X�g���̒l�Ƃ��ėL���Ȓl���擾����B<p>
     *
     * @return �z�X�g���̒l�Ƃ��ėL���Ȓl�i���K�\���j�̕�����z��
     */
    public String[] getValidServerNames();
    
    /**
     * HTTP���N�G�X�g�̃��\�b�h���̒l�Ƃ��ėL���Ȓl��ݒ肷��B<p>
     * �ݒ肵�Ȃ��ꍇ�́A���\�b�h���̒l�̓`�F�b�N����Ȃ��B<br>
     *
     * @param methods ���\�b�h���̒l�Ƃ��ėL���Ȓl�̕�����z��
     */
    public void setValidMethods(String[] methods);
    
    /**
     * HTTP���N�G�X�g�̃��\�b�h���̒l�Ƃ��ėL���Ȓl���擾����B<p>
     *
     * @return ���\�b�h���̒l�Ƃ��ėL���Ȓl�̕�����z��
     */
    public String[] getValidMethods();
    
    /**
     * HTTP���N�G�X�g�̃��\�b�h���̒l�Ƃ��Ė����Ȓl��ݒ肷��B<p>
     * �ݒ肵�Ȃ��ꍇ�́A���\�b�h���̒l�̓`�F�b�N����Ȃ��B<br>
     *
     * @param methods ���\�b�h���̒l�Ƃ��Ė����Ȓl�̕�����z��
     */
    public void setInvalidMethods(String[] methods);
    
    /**
     * HTTP���N�G�X�g�̃��\�b�h���̒l�Ƃ��Ė����Ȓl���擾����B<p>
     *
     * @return ���\�b�h���̒l�Ƃ��Ė����Ȓl�̕�����z��
     */
    public String[] getInvalidMethods();
    
    /**
     * �C�ӂ̃��N�G�X�g�w�b�_�̒l�Ƃ��ėL���Ȓl��ݒ肷��B<p>
     *
     * @param cond �C�ӂ̃��N�G�X�g�w�b�_���ƗL���Ȓl�i���K�\���j�̃}�b�s���O�B���N�G�X�g�w�b�_��=�l�i���K�\���j
     */
    public void setHeaderEquals(Properties cond);
    
    /**
     * �C�ӂ̃��N�G�X�g�w�b�_�̒l�Ƃ��ėL���Ȓl���擾����B<p>
     *
     * @return �C�ӂ̃��N�G�X�g�w�b�_���ƗL���Ȓl�i���K�\���j�̃}�b�s���O
     */
    public Properties getHeaderEquals();
    
    /**
     * �`�F�b�N�G���[�ɂȂ����ꍇ�ɕԂ�HTTP���X�|���X�̃X�e�[�^�X��ݒ肷��B<p>
     * �f�t�H���g�́A400�B<br>
     * {@link #isThrowOnError()}��true�̏ꍇ�́A���̐ݒ�͖����ł���B<br>
     *
     * @param status HTTP���X�|���X�̃X�e�[�^�X
     */
    public void setErrorStatus(int status);
    
    /**
     * �`�F�b�N�G���[�ɂȂ����ꍇ�ɕԂ�HTTP���X�|���X�̃X�e�[�^�X���擾����B<p>
     *
     * @return HTTP���X�|���X�̃X�e�[�^�X
     */
    public int getErrorStatus();
    
    /**
     * �`�F�b�N�G���[�ɂȂ����ꍇ�ɗ�O��throw���邩�ǂ�����ݒ肷��B<p>
     * �f�t�H���g�́Afalse�B<br>
     *
     * @param isThrow �`�F�b�N�G���[�ɂȂ����ꍇ�ɗ�O��throw����ꍇ�Atrue
     */
    public void setThrowOnError(boolean isThrow);
    
    /**
     * �`�F�b�N�G���[�ɂȂ����ꍇ�ɗ�O��throw���邩�ǂ����𔻒肷��B<p>
     *
     * @return true�̏ꍇ�A�`�F�b�N�G���[�ɂȂ����ꍇ�ɗ�O��throw����
     */
    public boolean isThrowOnError();
}
