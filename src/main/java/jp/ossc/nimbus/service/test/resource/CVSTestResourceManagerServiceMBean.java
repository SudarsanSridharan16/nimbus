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

/**
 * {@link CVSTestResourceManagerService}��MBean�C���^�t�F�[�X
 * <p>
 *
 * @author M.Ishida
 * @see CVSTestResourceManagerService
 */
public interface CVSTestResourceManagerServiceMBean {

    /**
     * CVS�T�[�o�֐ڑ�����ۂ�ext���\�b�h�p�萔�B
     * <p>
     */
    public static String METHOD_EXT = "ext";

    /**
     * CVS�T�[�o�֐ڑ�����ۂ�local���\�b�h�p�萔�B
     * <p>
     */
    public static String METHOD_LOCAL = "local";

    /**
     * CVS�T�[�o�֐ڑ�����ۂ�lserver���\�b�h�p�萔�B
     * <p>
     */
    public static String METHOD_LSERVER = "lserver";

    /**
     * CVS�T�[�o�֐ڑ�����ۂ�pserver���\�b�h�p�萔�B
     * <p>
     */
    public static String METHOD_PSERVER = "pserver";

    /**
     * CVS�T�[�o�֐ڑ�����ۂ�sspi���\�b�h�p�萔�B
     * <p>
     */
    public static String METHOD_SSPI = "sspi";

    /**
     * CVS�T�[�o�֐ڑ�����ۂ̃��\�b�h���擾����B
     * <p>
     *
     * @return ���\�b�h
     */
    public String getMethod();

    /**
     * CVS�T�[�o�֐ڑ�����ۂ̃��\�b�h��ݒ肷��B
     * <p>
     *
     * @param method ���\�b�h
     */
    public void setMethod(String method);

    /**
     * CVS�T�[�o�֐ڑ�����ۂ̃��[�U�����擾����B
     * <p>
     *
     * @return ���[�U��
     */
    public String getUserName();

    /**
     * CVS�T�[�o�֐ڑ�����ۂ̃��[�U����ݒ肷��B
     * <p>
     *
     * @param user ���[�U��
     */
    public void setUserName(String user);

    /**
     * CVS�T�[�o�֐ڑ�����ۂ̃p�X���[�h���擾����B
     * <p>
     *
     * @return �p�X���[�h
     */
    public String getPassword();

    /**
     * CVS�T�[�o�֐ڑ�����ۂ̃p�X���[�h��ݒ肷��B
     * <p>
     *
     * @param str �p�X���[�h
     */
    public void setPassword(String str);

    /**
     * CVS�T�[�o�֐ڑ�����ۂ̃T�[�o�����擾����B
     * <p>
     *
     * @return �T�[�o��
     */
    public String getServerName();

    /**
     * CVS�T�[�o�֐ڑ�����ۂ̃T�[�o����ݒ肷��B
     * <p>
     *
     * @param server �T�[�o��
     */
    public void setServerName(String server);

    /**
     * CVS�T�[�o�֐ڑ�����ۂ̃|�[�g���擾����B
     * <p>
     *
     * @return �|�[�g
     */
    public int getPort();

    /**
     * CVS�T�[�o�֐ڑ�����ۂ̃|�[�g��ݒ肷��B
     * <p>
     *
     * @param port �|�[�g
     */
    public void setPort(int port);

    /**
     * CVS�T�[�o�֐ڑ�����ۂ̃��|�W�g���p�X���擾����B
     * <p>
     *
     * @return ���|�W�g���p�X
     */
    public String getRepositoryPath();

    /**
     * CVS�T�[�o�֐ڑ�����ۂ̃��|�W�g���p�X��ݒ肷��B
     * <p>
     *
     * @param path ���|�W�g���p�X
     */
    public void setRepositoryPath(String path);

    /**
     * CVS�T�[�o�֐ڑ�����ۂ̃��W���[���p�X���擾����B
     * <p>
     *
     * @return ���W���[���p�X
     */
    public String getModulePath();

    /**
     * CVS�T�[�o�֐ڑ�����ۂ̃��W���[���p�X��ݒ肷��B
     * <p>
     *
     * @param module ���W���[���p�X
     */
    public void setModulePath(String module);

    /**
     * CVS�T�[�o���烂�W���[�����`�F�b�N�A�E�g����f�B���N�g�����擾����B
     * <p>
     *
     * @return ���W���[�����`�F�b�N�A�E�g����f�B���N�g��
     */
    public File getCvsCheckOutDirectory();

    /**
     * CVS�T�[�o���烂�W���[�����`�F�b�N�A�E�g����f�B���N�g����ݒ肷��B
     * <p>
     *
     * @param directory ���W���[�����`�F�b�N�A�E�g����f�B���N�g��
     */
    public void setCvsCheckOutDirectory(File directory);

    /**
     * CVS�T�[�o�֐ڑ�����ۂ̃u�����`�����擾����B
     * <p>
     *
     * @return �u�����`��
     */
    public String getTargetBranch();

    /**
     * CVS�T�[�o�֐ڑ�����ۂ̃u�����`����ݒ肷��B
     * <p>
     *
     * @param branch �u�����`��
     */
    public void setTargetBranch(String branch);

    /**
     * CVS�T�[�o�֐ڑ�����ۂ̃^�O�����擾����B
     * <p>
     *
     * @return �^�O��
     */
    public String getTargetTag();

    /**
     * CVS�T�[�o�֐ڑ�����ۂ̃^�O����ݒ肷��B
     * <p>
     *
     * @param tag �^�O��
     */
    public void setTargetTag(String tag);

    /**
     * CVS�R�}���h���s����Debug���O�L��/������ԋp����B
     *
     * @return Debug���O�L��/����
     */
    public boolean isDebugEnabled();

    /**
     * CVS�R�}���h���s����Debug���O�L��/������ݒ肷��B
     *
     * @param enabled Debug���O�L��/����
     */
    public void setDebugEnabled(boolean enabled);

    /**
     * CVS�R�}���h���s����Info���O�L��/������ԋp����B
     *
     * @return Info���O�L��/����
     */
    public boolean isInfoEnabled();

    /**
     * CVS�R�}���h���s����Info���O�L��/������ݒ肷��B
     *
     * @param enabled Info���O�L��/����
     */
    public void setInfoEnabled(boolean enabled);

    /**
     * CVS�R�}���h���s����Warn���O�L��/������ԋp����B
     *
     * @return Warn���O�L��/����
     */
    public boolean isWarnEnabled();

    /**
     * CVS�R�}���h���s����Warn���O�L��/������ݒ肷��B
     *
     * @param enabled Warn���O�L��/����
     */
    public void setWarnEnabled(boolean enabled);

    /**
     * CVS�R�}���h���s����Error���O�L��/������ԋp����B
     *
     * @return Error���O�L��/����
     */
    public boolean isErrorEnabled();

    /**
     * CVS�R�}���h���s����Error���O�L��/������ݒ肷��B
     *
     * @param enabled Error���O�L��/����
     */
    public void setErrorEnabled(boolean enabled);
}
