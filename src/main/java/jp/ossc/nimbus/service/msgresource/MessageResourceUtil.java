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
package jp.ossc.nimbus.service.msgresource;

import org.w3c.dom.*;
import jp.ossc.nimbus.lang.*;

/**
 *	XML�p�[�X���Ɏg�p���鋤�ʊ֐�
 *	@author	y-tokuda
 *	@version	1.00 �쐬�F2003/11/05�| y-tokuda<BR>
 *				�X�V�F
 */
public class MessageResourceUtil {
	/**
	 * �K�����݂��Ȃ���΂����Ȃ��l���擾���郁�\�b�h
	 * ���݂��Ȃ����ServiceException�𔭐�������B
	 */
	public static String getValueMustbeSpecified(Element elem){
		Node node = elem.getFirstChild();
		if(node != null){
			String ret = node.getNodeValue();
			ret = ret.trim();
			if(ret.length() > 0){
				return ret;
			}
		}
		throw new ServiceException("MESSAGERESOURCEFACTORY003",
									"Fail to get Value. Tag name is " +
									elem.getTagName());
	}
	/**
	 * ���݂��Ȃ��Ă������l���擾���郁�\�b�h�B
	 * ���݂��Ȃ����null��Ԃ��B
	 */
	public static String getValue(Element elem){
		Node node = elem.getFirstChild();
		if(node != null){
			String ret = node.getNodeValue();
			if(ret.length() > 0){
				return ret;
			}
		}
		return null;
	}
	
	/**
	 * �K���w�肳��Ă��Ȃ���΂Ȃ�Ȃ��������擾���郁�\�b�h
	 * �擾�ł��Ȃ���΁AServiceException��throw����B
	 * 
	 */
	public static String getAttMustBeSpecified(Element elem,String attName){
		String ret = elem.getAttribute(attName);
		if (ret != null){
			if(ret.length() > 0){
				return ret;
			}
		}
		throw new ServiceException("MESSAGERESOURCEFACTORY002","Fail to get Attribute ["
									 + attName + "] ." + "Tag name is " + elem.getTagName());
	}
}
