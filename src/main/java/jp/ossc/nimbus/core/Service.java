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

/**
 * �T�[�r�X�C���^�t�F�[�X�B<p>
 * {@link ServiceManager}�Ő���\�ȃT�[�r�X���`����C���^�t�F�[�X�ł��B<br>
 * ���̃C���^�t�F�[�X��implements�����N���X�́AServiceManager�ɂ���āA�����i{@link #create()}�j�A�N���i{@link #start()}�j�A��~�i{@link #stop()}�j�A�p���i{@link #destroy()}�j�̌_�@�𐧌䂷�鎖���\�ŁA���̓������������K�v������܂��B�܂��A�S�̓���̐���ɂ��A��Ԃ̊Ǘ�����������K�v������܂��B<br>
 * 
 * @author M.Takata
 * @see ServiceManager
 */
public interface Service{
    
    /**
     * ��Ԃ�\��������\���̔z��ł��B<p>
     * <table border=1>
     *   <tr><th>���</th><th>������\��</th></tr>
     *   <tr><td>�������F{@link #CREATING}</td><td>Creating</td></tr>
     *   <tr><td>���������F{@link #CREATED}</td><td>Created</td></tr>
     *   <tr><td>�J�n���F{@link #STARTING}</td><td>Starting</td></tr>
     *   <tr><td>�J�n�����F{@link #STARTED}</td><td>Started</td></tr>
     *   <tr><td>��~���F{@link #STOPPING}</td><td>Stopping</td></tr>
     *   <tr><td>��~�����F{@link #STOPPED}</td><td>Stopped</td></tr>
     *   <tr><td>�j�����F{@link #DESTROYING}</td><td>Destorying</td></tr>
     *   <tr><td>�j�������F{@link #DESTROYED}</td><td>Destroyed</td></tr>
     *   <tr><td>���s�F{@link #FAILED}</td><td>Failed</td></tr>
     *   <tr><td>�s���F{@link #UNKNOWN}</td><td>Unknown</td></tr>
     * </table>
     */
    public static final String[] STATES = {
        "Creating", "Created",
        "Starting", "Started",
        "Stopping", "Stopped",
        "Destorying", "Destroyed",
        "Failed", "Unknown"
    };
    
    /**
     * ��������\����Ԓl�B<p>
     */
    public static final int CREATING = 0;
    
    /**
     * ����������\����Ԓl�B<p>
     */
    public static final int CREATED = 1;
    
    /**
     * �J�n����\����Ԓl�B<p>
     */
    public static final int STARTING = 2;
    
    /**
     * �J�n������\����Ԓl�B<p>
     */
    public static final int STARTED  = 3;
    
    /**
     * ��~����\����Ԓl�B<p>
     */
    public static final int STOPPING = 4;
    
    /**
     * ��~������\����Ԓl�B<p>
     */
    public static final int STOPPED  = 5;
    
    /**
     * �j������\����Ԓl�B<p>
     */
    public static final int DESTROYING = 6;
    
    /**
     * �j��������\����Ԓl�B<p>
     */
    public static final int DESTROYED = 7;
    
    /**
     * ���s��\����Ԓl�B<p>
     */
    public static final int FAILED  = 8;
    
    /**
     * �s����\����Ԓl�B<p>
     */
    public static final int UNKNOWN  = 9;
    
    /**
     * ���̃T�[�r�X�̓o�^��ƂȂ�{@link ServiceManager}�̃T�[�r�X�����擾���܂��B<p>
     *
     * @return ServiceManager�̃T�[�r�X��
     * @see #setServiceManagerName(String)
     */
    public String getServiceManagerName();
    
    /**
     * ���̃T�[�r�X�̓o�^��ƂȂ�{@link ServiceManager}�̃T�[�r�X����ݒ肵�܂��B<p>
     *
     * @param name ServiceManager�̃T�[�r�X��
     * @see #getServiceManagerName()
     */
    public void setServiceManagerName(String name);
    
    /**
     * �T�[�r�X����ݒ肵�܂��B<p>
     *
     * @param name �T�[�r�X��
     * @see #getServiceName()
     */
    public void setServiceName(String name);
    
    /**
     * �T�[�r�X�����擾���܂��B<p>
     *
     * @return �T�[�r�X��
     * @see #setServiceName(String)
     */
    public String getServiceName();
    
    /**
     * ���̃T�[�r�X�̓o�^��ƂȂ�{@link ServiceManager}�̃T�[�r�X���Ƃ��̃T�[�r�X�̃T�[�r�X�����܂�{@link ServiceName}���擾���܂��B<p>
     *
     * @return �T�[�r�X��
     */
    public ServiceName getServiceNameObject();
    
    /**
     * ���݂̃T�[�r�X��Ԃ��擾���܂��B<p>
     *
     * @return ��Ԃ������l
     * @see #CREATING
     * @see #CREATED
     * @see #STARTING
     * @see #STARTED
     * @see #STOPPING
     * @see #STOPPED
     * @see #DESTROYING
     * @see #DESTROYED
     * @see #FAILED
     * @see #UNKNOWN
     */
    public int getState();
    
    /**
     * ���݂̃T�[�r�X��Ԃ̕�����\�����擾���܂��B<p>
     *
     * @return �T�[�r�X��Ԃ̕�����\��
     * @see #STATES
     */
    public String getStateString();
    
    /**
     * �T�[�r�X�𐶐����܂��B<p>
     * ���̃T�[�r�X�ɕK�v�ȃI�u�W�F�N�g�̐����Ȃǂ̏������������s���܂��B<br>
     *
     * @exception Exception �T�[�r�X�̐��������Ɏ��s�����ꍇ
     */
    public void create() throws Exception;
    
    /**
     * �T�[�r�X���J�n���܂��B<p>
     * ���̃T�[�r�X�𗘗p�\�ȏ�Ԃɂ��܂��B���̃��\�b�h�̌Ăяo����́A���̃T�[�r�X�̋@�\�𗘗p�ł��鎖���ۏ؂���܂��B<br>
     *
     * @exception Exception �T�[�r�X�̊J�n�����Ɏ��s�����ꍇ
     */
    public void start() throws Exception;
    
    /**
     * �T�[�r�X���~���܂��B<p>
     * ���̃T�[�r�X�𗘗p�s�\�ȏ�Ԃɂ��܂��B���̃��\�b�h�̌Ăяo����́A���̃T�[�r�X�̋@�\�𗘗p�ł��鎖�͕ۏ؂���܂���B<br>
     */
    public void stop();
    
    /**
     * �T�[�r�X��j�����܂��B<p>
     * ���̃T�[�r�X�Ŏg�p���郊�\�[�X���J�����܂��B���̃��\�b�h�̌Ăяo����́A���̃T�[�r�X�̋@�\�𗘗p�ł��鎖�͕ۏ؂���܂���B<br>
     */
    public void destroy();
}