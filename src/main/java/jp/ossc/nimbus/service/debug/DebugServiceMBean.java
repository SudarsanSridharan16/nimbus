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
import jp.ossc.nimbus.core.ServiceBaseMBean;
import jp.ossc.nimbus.core.ServiceName;
/**
 * �f�o�b�O�T�[�r�XMBean�C���^�[�t�F�C�X
 * @version $Name:  $
 * @author K.Nagai
 * @since 1.0
 */
public interface DebugServiceMBean extends ServiceBaseMBean {
    /**�f�o�b�O���x���F�f�o�b�O*/
    public final static int DEBUG_LEVEL_DEBUG = 0;
    /**�f�o�b�O���x���F���*/
    public final static int DEBUG_LEVEL_INFO  = 20;
    /**�f�o�b�O���x���F�x��*/
    public final static int DEBUG_LEVEL_WARN  = 40;
    /**�f�o�b�O���x���F�G���[*/
    public final static int DEBUG_LEVEL_ERROR = 60;
    /**�f�o�b�O���x���F�v���I�G���[*/
    public final static int DEBUG_LEVEL_FATALERROR = 80;
    /**�f�o�b�O���x���F�v���I�G���[*/
    public final static int DEBUG_LEVEL_NOOUTPUT = 81;
    //���O�̃L�[
    //��O��
    public final static String DEBUG_DEBUG_WRITE_KEY1  ="DEBUG00001";    
    //��O�Ȃ�
    public final static String DEBUG_DEBUG_WRITE_KEY2  ="DEBUG00002";    
    //DUMP
    public final static String DEBUG_DEBUG_DUMP_KEY1  ="DEBUG00003";    
    //DUMP []
    public final static String DEBUG_DEBUG_DUMP_KEY2  ="DEBUG00004";    
    //message + DUMP
    public final static String DEBUG_DEBUG_MSG_DUMP_KEY1  ="DEBUG00005";    
    //message + DUMP []
    public final static String DEBUG_DEBUG_MSG_DUMP_KEY2  ="DEBUG00006";    
	//�l�X�g���x���ُ픭����
	public static final String DEBUG_NESTLEVEL_ERR_KEY = "DEBUG00007";    /**
     * getDebugLevel<p>
     * �f�o�b�O���x�����擾
     * @return �f�o�b�O���x��
     */
    public int getDebugLevel();
    /**
     * setDebugLevel<p>
     * �f�o�b�O���x����ݒ�B�ݒ�ł��郌�x���Ɋւ��Ă͂��̃C���^�[�t�F�C�X�̒萔�l���Q�ƁB
     * @param level �f�o�b�O���x��
     */
    public void setDebugLevel(int level);
    /**
     * setLoggerServiceName<p>
     * ���O�T�[�r�X�����擾����B
     * @return �f�o�b�O���x��
     */
    public ServiceName getLogServiceName();
    /**
     * setLoggerServiceName<p>
     * ���O�T�[�r�X����ݒ肷��B
     * @param svn ���O�T�[�r�X��
     */
    public void setLogServiceName(ServiceName svn);
    /**
     * setNestedLevel
     * @param level �R�[���X�^�b�N�̉��Ԗڂ��֐��X�^�b�N�Ƃ���
     * �\�����邩
     */
    public void setNestedLevel(int level);
    /**
     * setEditorFinderServiceName
     * @param name �G�f�B�^�t�@�C���_��
     */
    public void setEditorFinderServiceName(ServiceName name);
    /**
     * getEditorFinderServiceName
     * @return �G�f�B�^�t�@�C���_��
     * �\�����邩
     */
    public ServiceName getEditorFinderServiceName();
    /**
     * getSeparator
     * @return �Z�p���[�^�[
     * �\�����邩
     */
	public String getSeparator() ;
    /**
     * setSeparator
     * @param separator �Z�p���[�^�[
     * �\�����邩
     */
	public void setSeparator(String separator);
}
