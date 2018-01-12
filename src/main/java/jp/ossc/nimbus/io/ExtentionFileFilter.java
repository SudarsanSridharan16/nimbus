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
package jp.ossc.nimbus.io;

import java.io.*;

/**
 * �g���q�w��̃t�@�C���t�B���^�B<p>
 * <pre>
 * import java.io.*;
 * import jp.ossc.nimbus.io.ExtentionFileFilter;
 *
 * File dir = new File("sample");
 * File[] files = dir.listFiles(new ExtentionFileFilter("def"));
 * </pre>
 *
 * @author H.Nakano
 */
public class ExtentionFileFilter implements FilenameFilter, Serializable{
    
    private static final long serialVersionUID = 5687776723127309667L;
    
    /**
     * �t�@�C���̊g���q�B<p>
     */
    protected String extention;
    
    /**
     * �g���q�̑啶���E����������ʂ��邩�ǂ����̃t���O�B<p>
     * �f�t�H���g�́Atrue�ŁA�啶���E����������ʂ��Ȃ��B<br>
     */
    protected boolean isIgnoreCase = true;
    
    private String upperExtention;
    
    /**
     * �g���q���w�肵�Ȃ��t�B���^�̃C���X�^���X�𐶐�����B<p>
     */
    public ExtentionFileFilter(){
        this(null, true);
    }
    
    /**
     * �w�肵���g���q�̃t�@�C���t�B���^�̃C���X�^���X�𐶐�����B<p>
     *
     * @param ext �g���q������
     */
    public ExtentionFileFilter(String ext){
        this(ext, true);
    }
    
    /**
     * �w�肵���g���q�̃t�@�C���t�B���^�̃C���X�^���X�𐶐�����B<p>
     *
     * @param ext �g���q������
     * @param isIgnoreCase �啶���E����������ʂ��Ȃ��ꍇ��true
     */
    public ExtentionFileFilter(String ext, boolean isIgnoreCase){
        setExtention(ext);
        setIgnoreCase(isIgnoreCase);
    }
    
    /**
     * �t�@�C���̊g���q��ݒ肷��B<p>
     * �w�肳�ꂽ�g���q���A"."����n�܂�Ȃ��ꍇ�́A�����I�ɕt������B�܂��Anull��󕶎����w�肵���ꍇ�́A�t�B���^�����O���Ȃ��B<br>
     * 
     * @param ext �t�@�C���̊g���q
     */
    public void setExtention(String ext){
        if(ext == null || ext.length() == 0){
            extention = null;
        }else if(ext.charAt(0) == '.'){
            extention = ext;
            upperExtention = extention.toUpperCase();
        }else{
            extention = '.' + ext;
            upperExtention = extention.toUpperCase();
        }
    }
    
    /**
     * �t�@�C���̊g���q���擾����B<p>
     * 
     * @return �t�@�C���̊g���q
     */
    public String getExtention(){
        return extention;
    }
    
    /**
     * �g���q�̑啶���E�������𖳎����邩�ǂ�����ݒ肷��B<p>
     * �f�t�H���g�́Atrue�B
     *
     * @param isIgnoreCase �啶���E����������ʂ��Ȃ��ꍇ��true
     */
    public void setIgnoreCase(boolean isIgnoreCase){
        this.isIgnoreCase = isIgnoreCase;
    }
    
    /**
     * �g���q�̑啶���E�������𖳎����邩�ǂ����𔻒肷��B<p>
     *
     * @return true�̏ꍇ�A�啶���E����������ʂ��Ȃ�
     */
    public boolean isIgnoreCase(){
        return isIgnoreCase;
    }
    
    /**
     * �w�肳�ꂽ�g���q�̃t�@�C�����ǂ������肷��B<p>
     * 
     * @param dir �f�B���N�g��
     * @param fileName �t�@�C����
     * @return �w�肳�ꂽ�g���q�̃t�@�C���̏ꍇtrue
     */
    public boolean accept(File dir, String fileName){
        if(extention == null){
            return true;
        }else if(isIgnoreCase){
            String tmp = fileName.toUpperCase();
            return tmp.endsWith(upperExtention);
        }else{
            return fileName.endsWith(extention);
        }
    }
}
