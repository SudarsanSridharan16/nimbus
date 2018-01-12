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
package jp.ossc.nimbus.beans.dataset;

import java.io.*;

/**
 * �w�b�_�[�B<p>
 * �f�[�^�Z�b�g�̃w�b�_�[�ŁA�w�b�_�[����������{@link Record ���R�[�h}�ł���B<br>
 * ���R�[�h�Ɠ��l�ɁA�����̃v���p�e�B������Bean�ŁA�X�L�[�}��`�ɂ���āA�ǂ̂悤��Bean�ɂ���̂��i�v���p�e�B���A�^�Ȃǁj�𓮓I�Ɍ���ł���B<br>
 * 
 * @author M.Takata
 */
public class Header extends Record{
    
    private static final long serialVersionUID = -2149254849180957920L;
    
    /**
     * �w�b�_�[���B<p>
     */
    protected String name;
    
    /**
     * ����`�̃w�b�_�[�𐶐�����B<p>
     */
    public Header(){
    }
    
    /**
     * ����`�̃w�b�_�[�𐶐�����B<p>
     *
     * @param name �w�b�_�[��
     */
    public Header(String name){
        this.name = name;
    }
    
    /**
     * �w�b�_�[�𐶐�����B<p>
     *
     * @param name �w�b�_�[��
     * @param schema �X�L�[�}
     * @exception PropertySchemaDefineException �v���p�e�B�̃X�L�[�}��`�Ɏ��s�����ꍇ
     */
    public Header(String name, String schema)
     throws PropertySchemaDefineException{
        super(schema);
        this.name = name;
    }
    
    /**
     * �w�b�_�[�𐶐�����B<p>
     *
     * @param name �w�b�_�[��
     * @param recordSchema �X�L�[�}�����񂩂琶�����ꂽ���R�[�h�X�L�[�}
     * @exception PropertySchemaDefineException �v���p�e�B�̃X�L�[�}��`�Ɏ��s�����ꍇ
     */
    public Header(String name, RecordSchema recordSchema){
        super(recordSchema);
        this.name = name;
    }
    
    /**
     * �w�b�_�[�����擾����B<p>
     *
     * @return �w�b�_�[��
     */
    public String getName(){
        return name;
    }
    
    /**
     * �w�b�_�[����ݒ肷��B<p>
     *
     * @param name �w�b�_�[��
     */
    public void setName(String name){
        this.name = name;
    }
    
    protected void writeSchema(ObjectOutput out) throws IOException{
        super.writeSchema(out);
        out.writeObject(name);
    }
    
    protected void readSchema(ObjectInput in) throws IOException, ClassNotFoundException{
        super.readSchema(in);
        name = (String)in.readObject();
    }
}
