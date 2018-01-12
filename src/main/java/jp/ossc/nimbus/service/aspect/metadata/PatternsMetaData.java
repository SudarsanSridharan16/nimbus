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
import java.util.*;
import org.w3c.dom.*;
import jp.ossc.nimbus.core.*;
/**
 * �C���^�[�Z�v�^�}�b�s���O�X��`&lt;patterns&gt;�v�f���^�f�[�^�B<br>
 * �C���^�[�Z�v�^��`�t�@�C����&lt;patterns&gt;�v�f�ɋL�q���ꂽ���e���i�[���郁�^�f�[�^�R���e�i�ł���B<br>
 * @version $Name:  $
 * @author H.Nakano
 * @since 1.0
 */
public class PatternsMetaData extends MetaData implements Serializable {
	
    private static final long serialVersionUID = 6594049214635771499L;
    
    /**
	 * &lt;InterceptorMappings&gt;�v�f�̗v�f��������B<br>
	 */
	public static final String PATTERNS_TAG_NAME = "patterns";
	/**
	 * &lt;pattern&gt;�v�f�̃��^�f�[�^�̃}�b�v
	 */
	private final List patternList = new ArrayList();
	//
	/**
	 * �R���X�g���N�^
	 */
	public PatternsMetaData(MetaData parent){
		super(parent);
	}
	/**
	 * &lt;patterns&gt;�v�f�̎q�v�f��&lt;patter&gt;�v�f�̃��^�f�[�^�̃��X�g���擾����B
	 *	@return List - &lt;pattern&gt;�v�f�̃��^�f�[�^�̃��X�g
	 */
	public List getPatternList(){
		return patternList;
	}
	/**
	 * &lt;patterns&gt;�v�f�̎q�v�f�Ƃ��Ē�`�����&lt;pattern&gt;�v�f�̃��^�f�[�^��o�^����B
	 *	@param pattern - &lt;pattern&gt;�v�f�̃��^�f�[�^
	 */
	public void addPattern(PatternMetaData pattern){
		patternList.add(pattern);
	}
	/**
	 *	<br>
	 *	@param element
	 */
	public void importXML(Element element) throws DeploymentException{
		super.importXML(element);
		if(!element.getTagName().equals(PATTERNS_TAG_NAME)){
			throw new DeploymentException(
				"Tag must be " + PATTERNS_TAG_NAME + " : "
				 + element.getTagName()
			);
		}
		final Iterator patternElements = getChildrenByTagName(
			element,
			PatternMetaData.PATTERN_TAG_NAME
		);
		while(patternElements.hasNext()){
			final PatternMetaData patternData = new PatternMetaData(this);
			patternData.importXML((Element)patternElements.next());
			addPattern(patternData);
		}
	}

}
