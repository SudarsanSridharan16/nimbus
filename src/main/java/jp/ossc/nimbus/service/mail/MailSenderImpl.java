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
package jp.ossc.nimbus.service.mail;
import jp.ossc.nimbus.core.*;
//
/**
 * 
 * @author   NRI. Hirotaka Nakano
 * @version  1.00 �쐬: 2003/10/09 -�@H.Nakano
 */
public class MailSenderImpl 
	implements MailSender,MailSenderOperator, java.io.Serializable {
	
    private static final long serialVersionUID = -778867525711484959L;
    
    /** ���M��				*/	
	protected String mTo = null;
	/** ���M��				*/	
	protected String mFrom = null;
	/** ����				*/	
	protected String mSubject = null;
	/** �{��				*/	
	protected String mBody = null;
	protected SenderFactoryCallBack mFc ;	
	protected ServiceName mServerName ;
	/**
	 * Constructor for MailSenderImpl.
	 */
	public MailSenderImpl() {
		super();
	}

	/**
	 * @see jp.ossc.nimbus.service.mail.MailSender#setTo(String)
	 */
	public void setTo(String to) {
		mTo = to ;
	}

	/**
	 * @see jp.ossc.nimbus.service.mail.MailSender#setFrom(String)
	 */
	public void setFrom(String from) {
		mFrom = from ;
	}

	/**
	 * @see jp.ossc.nimbus.service.mail.MailSender#setSubject(String)
	 */
	public void setSubject(String subject) {
		mSubject = subject ;
	}

	/**
	 * @see jp.ossc.nimbus.service.mail.MailSender#setBody(String)
	 */
	public void setBody(String body) {
		this.mBody = body ;		
	}
	/**
	 *	���b�Z�[�W�̑��M���ݒ肷��<BR>
	 *	@return		����
	 */
	public String getTo(){
		return this.mTo ;
	}
	/**
	 *	���b�Z�[�W�̑��M����ݒ肷��<BR>
	 *	@return	���o�l
	 */
	public String getFrom(){
		return this.mFrom;
	}
	/**
	 *	���b�Z�[�W�̌�����ݒ肷��<BR>
	 *	@return	����
	 */
	public String getSubject(){
		return this.mSubject ;
	}
	/**
	 *	���b�Z�[�W�̖{����ݒ肷��<BR>
	 *	@return	�{��
	 */
	public String getBody(){
		return this.mBody;
	}
	/**
	 *	���[���T�[�o�����o�͂���B<BR>
	 *	@param	fc 
	 */
	public void setFactory(SenderFactoryCallBack fc) {
		this.mFc = fc ;
	}

	/**
	 * @see jp.ossc.nimbus.service.mail.MailSender#sendMessage()
	 */
	public void sendMessage()  {
		mServerName = mFc.sendSmtp(this) ;		
	}

	/**
	 * @see jp.ossc.nimbus.service.mail.MailSender#getMailServerName()
	 */
	public String getMailServerName() {
		return  mServerName.toString() ;
	}

}
