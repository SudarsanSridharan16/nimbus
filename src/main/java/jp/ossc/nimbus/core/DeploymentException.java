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

import java.io.*;

/**
 * �T�[�r�X�z�u��O�B<p>
 * ServiceLoader���g����Service�̔z�u���s���ۂɁA�v���I�Ȗ�肪���������ꍇ��throw�����B�T�[�r�X��`�t�@�C���ɖ�肪����\��������B�܂��́A�T�[�r�X��`�t�@�C���̏ꏊ��A�T�[�r�X�N���X�̏ꏊ�Ȃǂɖ�肪����\��������B
 *
 * @author M.Takata
 */
public class DeploymentException extends Exception{
    
    private static final long serialVersionUID = 3303301324377552786L;
    
    private static final String CAUSED_PREFIX = "Caused by : ";
    
    private static final String METHOD_NAME_GET_CAUSE = "getCause";
    
    private static final boolean isExistsGetCause;
    
    static{
        java.lang.reflect.Method getCause = null;
        try{
            getCause = Exception.class.getMethod(METHOD_NAME_GET_CAUSE, (Class[])null);
        }catch(NoSuchMethodException e){
            // ��������
        }
        isExistsGetCause = (getCause != null);
    }
    
    /**
     * ���̗�O�̌����ƂȂ�����O�B<p>
     */
    private Throwable cause;
    
    protected String resourceName;
    
    /**
     * �R���X�g���N�^�B<p>
     */
    public DeploymentException(){
        super();
    }
    
    /**
     * �G���[���b�Z�[�W���������C���X�^���X�𐶐�����R���X�g���N�^�B<p>
     *
     * @param message �G���[���b�Z�[�W
     */
    public DeploymentException(String message){
        super(message);
    }
    
    /**
     * ���̗�O�̌����ƂȂ�����O���������C���X�^���X�𐶐�����R���X�g���N�^�B<p>
     *
     * @param cause �����ƂȂ�����O
     */
    public DeploymentException(Throwable cause){
        this(cause.getMessage(), cause);
    }
    
    /**
     * �G���[���b�Z�[�W�ƁA���̗�O�̌����ƂȂ�����O���������C���X�^���X�𐶐�����R���X�g���N�^�B<p>
     *
     * @param message �G���[���b�Z�[�W
     * @param cause �����ƂȂ�����O
     */
    public DeploymentException(String message, Throwable cause){
        super(message);
        this.cause = cause;
    }
    public void setResourceName(String name){
        if(resourceName == null){
            resourceName = name;
        }else{
            resourceName = name + ":" + resourceName;
        }
    }
    public String getResourceName(){
        return resourceName;
    }
    
    public String getMessage(){
        if(resourceName == null){
            return super.getMessage();
        }else{
            return super.getMessage() + " : " + resourceName;
        }
    }
    
    /**
     * ���̗�O�̌����ƂȂ�����O���擾����B<p>
     *
     * @return ���̗�O�̌����ƂȂ�����O
     */
    public Throwable getCause(){
        return cause;
    }
    
    /**
     * ���̗�O�Ƃ��̃o�b�N�g���[�X���w�肳�ꂽ����X�g���[���ɏo�͂��܂��B<p>
     *
     * @param s �o�͂Ɏg�p����PrintStream
     */
    public void printStackTrace(PrintStream s) {
        super.printStackTrace(s);
        if(!isExistsGetCause && cause != null){
            s.print(CAUSED_PREFIX);
            cause.printStackTrace(s);
        }
    }
    
    /**
     * ���̗�O�Ƃ��̃o�b�N�g���[�X���w�肳�ꂽ�v�����g���C�^�[�ɏo�͂��܂��B<p>
     *
     * @param s �o�͂Ɏg�p����PrintWriter
     */
    public void printStackTrace(PrintWriter s) { 
        super.printStackTrace(s);
        if(!isExistsGetCause && cause != null){
            s.print(CAUSED_PREFIX);
            cause.printStackTrace(s);
        }
    }
}