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
package jp.ossc.nimbus.service.aspect.metadata;
//�C���|�[�g
import java.io.*;
import org.w3c.dom.*;
import jp.ossc.nimbus.core.*;

/**
 * �R���|�[�l���g��`&lt;pattern&gt;�v�f���^�f�[�^�B<br>
 * �R���|�[�l���g��`�t�@�C����&lt;pattern&gt;�v�f�ɋL�q���ꂽ���e���i�[���郁�^�f�[�^�R���e�i�ł���B
 * @version $Name:  $
 * @author H.Nakano
 * @since 1.0
 */
public class PatternMetaData extends MetaData implements Serializable {
	
    private static final long serialVersionUID = -381300837581852951L;
    
    /**
	 * &lt;pattern&gt;�v�f�̗v�f��������B
	 */
	public static final String PATTERN_TAG_NAME = "pattern";
	/**
	 * &lt;pattern&gt;�v�f�̃��^�f�[�^�B
	 * @see #getPattern()
	 */
	private String pattern;
	/**
	 * �e�v�f�̃��^�f�[�^�����C���X�^���X�𐶐�����B<br>
	 * PatternMetaData�̐e�v�f�́A&lt;patterns&gt;�v�f��\��PatternsMetaData�ł���B
	 * @param parent �e�v�f�̃��^�f�[�^
	 * @see PatternsMetaData
	 */
	public PatternMetaData(MetaData parent){
		super(parent);
	}
	/**
	 * �p�^�[����\���v�f�̓��e�Ŏw�肳�ꂽ�p�^�[�����擾����B<br>
	 * ���e���w�肳��Ă��Ȃ��ꍇ�́Anull��Ԃ��B
	 * @return �p�^�[����\���v�f�̓��e
	 */
	public String getPattern(){
		return pattern;
	}
	/**
	 * &lt;pattern&gt;�v�f��Element���p�[�X���āA�������g�̏������A�y�юq�v�f�̃��^�f�[�^�̐������s���B<br>
	 *
	 * @param element &lt;pattern&gt;�v�f��Element
	 * @exception DeploymentException &lt;pattern&gt;�v�f�̉�́A���̌��ʂɂ�郁�^�f�[�^�̐����Ɏ��s�����ꍇ
	 */
	public void importXML(Element element) throws DeploymentException{
		super.importXML(element);
		if(!element.getTagName().equals(PATTERN_TAG_NAME)){
			throw new DeploymentException(
				"Tag must be " + PATTERN_TAG_NAME + " : "
				 + element.getTagName()
			);
		}
		final String content = getElementContent(element);
		if(content == null || content.length() == 0){
			throw new DeploymentException(
				PATTERN_TAG_NAME + " is empty "
			);
		}
		pattern = content;
	}

}
