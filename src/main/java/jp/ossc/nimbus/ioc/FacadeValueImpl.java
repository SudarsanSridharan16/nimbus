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
// �C���|�[�g
package jp.ossc.nimbus.ioc;
import java.util.*;
/**
 * EJB�t�@�T�[�h�֓n�����[�g�̃o�����[�I�u�W�F�N�g�N���X<p>
 * @version $Name:  $
 * @author H.Nakano
 * @since 1.0
 * @see Command
 * @see UnitOfWorkImpl 
 */
public class FacadeValueImpl extends UnitOfWorkImpl 
	implements FacadeValue,java.io.Serializable {
	
    private static final long serialVersionUID = -6246161797805065625L;
    
    /** �w�b�_�[���HASH */
	private HashMap mHeader ;
	/**
	 * �R���X�g���N�^�[
	 */
	public FacadeValueImpl(){
		super() ;
		this.mHeader = new HashMap() ;	
	}
	/*
	 * �w�b�_�[���̃Q�b�^�[
	 * @param key
	 * @return �w�b�_�[���
	 */
	public Object getHeader(String key){
		return this.mHeader.get(key) ;
	}
	/*
	 * �w�b�_�[���̃Z�b�^�[
	 * @param key
	 * @param value
	 */
	public void putHeader(String key,Object value ){
		this.mHeader.put(key,value) ;
	}
	/*
	 * �w�b�_�[�L�[�̃Q�b�^�[
	 * @return�@�L�[����Set
	 */
	public Set getHederKeys(){
		return this.mHeader.keySet() ;
	}

}
