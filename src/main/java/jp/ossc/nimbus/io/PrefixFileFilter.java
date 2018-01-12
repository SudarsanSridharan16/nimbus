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
 * �v���t�B�N�X�t�@�C���t�B���^�B<p>
 * �w�肳�ꂽ�v���t�B�N�X�̃t�@�C���݂̂𒊏o����t�B���^�B
 * <pre>
 * import java.io.*;
 * import jp.ossc.nimbus.io.PrefixFileFilter;
 *
 * File dir = new File("sample");
 * File[] files = dir.listFiles(new PrefixFileFilter("test"));
 * </pre>
 *
 * @author H.Nakano
 */
public class PrefixFileFilter implements FilenameFilter, Serializable{
    
    private static final long serialVersionUID = 3579204076741445814L;
    
    /**
     * �t�@�C���̃v���t�B�N�X�B<p>
     */
    protected String prefix;
    
    private String upperPrefix;
    
    /**
     * �v���t�B�N�X�̑啶���E����������ʂ��邩�ǂ����̃t���O�B<p>
     * �f�t�H���g�́Afalse�ŁA�啶���E����������ʂ���B<br>
     */
    protected boolean isIgnoreCase;
    
    /**
     * �v���t�B�N�X���w�肵�Ȃ��t�B���^�̃C���X�^���X�𐶐�����B<p>
     */
    public PrefixFileFilter(){
        this(null, false);
    }
    
    /**
     * �w�肵���v���t�B�N�X�̃t�@�C���݂̂𒊏o����t�B���^�̃C���X�^���X�𐶐�����B<p>
     *
     * @param prefix �v���t�B�N�X
     */
    public PrefixFileFilter(String prefix){
        this(prefix, false);
    }
    
    /**
     * �w�肵���v���t�B�N�X�̃t�@�C���݂̂𒊏o����t�B���^�̃C���X�^���X�𐶐�����B<p>
     *
     * @param prefix �v���t�B�N�X
     * @param isIgnoreCase �v���t�B�N�X�̑啶���E����������ʂ��Ȃ��ꍇ��true
     */
    public PrefixFileFilter(
        String prefix,
        boolean isIgnoreCase
    ){
        setPrefix(prefix);
        setIgnoreCase(isIgnoreCase);
    }
    
    /**
     * �t�@�C���̃v���t�B�N�X��ݒ肷��B<p>
     * null��󕶎����w�肵���ꍇ�́A�t�B���^�����O���Ȃ��B<br>
     * 
     * @param prefix �t�@�C���̃v���t�B�N�X
     */
    public void setPrefix(String prefix){
        if(prefix == null || prefix.length() == 0){
            this.prefix = null;
        }else{
            this.prefix = prefix;
            upperPrefix = prefix.toUpperCase();
        }
    }
    
    /**
     * �t�@�C���̃v���t�B�N�X���擾����B<p>
     * 
     * @return �t�@�C���̃v���t�B�N�X
     */
    public String getPrefix(){
        return prefix;
    }
    
    /**
     * �v���t�B�N�X�̑啶���E�������𖳎����邩�ǂ�����ݒ肷��B<p>
     * �f�t�H���g�́Atrue�B
     *
     * @param isIgnoreCase �啶���E����������ʂ��Ȃ��ꍇ��true
     */
    public void setIgnoreCase(boolean isIgnoreCase){
        this.isIgnoreCase = isIgnoreCase;
    }
    
    /**
     * �v���t�B�N�X�̑啶���E�������𖳎����邩�ǂ����𔻒肷��B<p>
     *
     * @return true�̏ꍇ�A�啶���E����������ʂ��Ȃ�
     */
    public boolean isIgnoreCase(){
        return isIgnoreCase;
    }
    
    /**
     * �w�肳�ꂽ�v���t�B�N�X�̃t�@�C�����ǂ������肷��B<p>
     * 
     * @param dir �f�B���N�g��
     * @param fileName �t�@�C����
     * @return �w�肳�ꂽ�v���t�B�N�X�̃t�@�C���̏ꍇtrue
     */
    public boolean accept(File dir, String fileName) {
        if(isIgnoreCase){
            final String tmp = fileName.toUpperCase();
            return tmp.startsWith(upperPrefix);
        }else{
            return fileName.startsWith(prefix);
        }
    }
}
