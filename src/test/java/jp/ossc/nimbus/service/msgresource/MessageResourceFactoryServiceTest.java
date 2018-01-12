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

import junit.framework.TestCase;
import jp.ossc.nimbus.core.*;
import jp.ossc.nimbus.service.resource.jmsqueue.*;
import jp.ossc.nimbus.service.jndi.*;
import javax.jms.*;
import javax.jms.Queue;

import java.util.*;

/**
 *	BytesOrStreamMessageFormat���e�X�g����B<BR>
 *	MessageResourceFactoryTest�ő��M���A<BR>
 *�@MessageResourceFactoryTest2�Ŏ�M����B<BR>
 *	@author	y-tokuda
 *	@version	1.00 �쐬�F2003/11/17�| y-tokuda<BR>
 *				�X�V�F
 */
public class MessageResourceFactoryServiceTest extends TestCase {
	private static final String serviceDefFilename = 
	"jp/ossc/nimbus/service/msgresource/nimbus-service.xml";
	private static final String QueueConnectionFactoryName = "QueueConnectionFactory";
	private static final String QueueName = "MyQueue";
	private MessageResourceFactory mMessageResourceFactory;
	private Queue mQueue;
	private QueueSender mSender;
	private QueueSession mSession;
	/**
	 * Constructor for MessageResourceFactoryServiceTest.
	 * @param arg0
	 */
	public static void dumpProp(Message msg) throws Exception{
		Enumeration propertyNames = msg.getPropertyNames();
		System.out.println("=====Properties=====");
		while(propertyNames.hasMoreElements()){
			String name = (String)propertyNames.nextElement();
			Object value = msg.getObjectProperty(name);
			System.out.println(name + " = " + value);
		}
		System.out.println("====================");
	}
	public static void write(String msg){
		System.out.println(msg);
	}
	public static void writeNG(Object val,Object expectedVal){
		write("NG It's not Expected val." + val);
		write("Expected val is " + expectedVal + ".");
	}
	public MessageResourceFactoryServiceTest(String arg0) {
		super(arg0);
		ServiceManagerFactory.loadManager(serviceDefFilename);
		//�R���X�g���N�^�ŁAQueueSession,Sender�̐����܂ōs��
		
		mMessageResourceFactory = (MessageResourceFactory)ServiceManagerFactory.getServiceObject("TheManager","MessageResourceFactoryService");
		JmsQueueSession jmsQueSession = (JmsQueueSession)ServiceManagerFactory.getServiceObject("TheManager","JmsQueueSessionService");
		//QueueSession���擾
		try{
			QueueTransanctionResource tranRes = (QueueTransanctionResource)jmsQueSession.makeResource(QueueConnectionFactoryName);
			mSession = (QueueSession)tranRes.getObject();
		}
		catch(Exception e){
			
		}
		
		JndiFinder finder = (JndiFinder)ServiceManagerFactory.getServiceObject("TheManager","JndiFinderService");
		//Queue���擾
		try{
			mQueue = (Queue)finder.lookup(QueueName);
			//QueueSender�쐬
			mSender = mSession.createSender(mQueue);
		}
		catch(Exception e){
			write("Fail to get Queue and QueueSender");
		}
	}

	public static void main(String[] args) {
		junit.textui.TestRunner.run(MessageResourceFactoryServiceTest.class);
	}
	
	/*
	public void testFindInstance() throws Exception{
		MessageResourceFactory msgResourceFactory = (MessageResourceFactory)ServiceManagerFactory.getServiceObject("TheManager","MessageResourceFactoryService");
		MessageResource msgResource = msgResourceFactory.findInstance("1");
		if(msgResource == null ){
			throw new Exception();
		}
	}
	public void testMakeTextMessage() throws Exception {
		MessageResourceFactory msgResourceFactory = (MessageResourceFactory)ServiceManagerFactory.getServiceObject("TheManager","MessageResourceFactoryService");
		JmsQueueSession jmsQueSession = (JmsQueueSession)ServiceManagerFactory.getServiceObject("TheManager","JmsQueueSessionService");
		//���b�Z�[�W���\�[�X���擾
		MessageResource msgResource = msgResourceFactory.findInstance("1");
		//QueueSession���擾
		QueueSession session = jmsQueSession.makeQueueSession(QueueConnectionFactoryName);
		TextMessage msg = (TextMessage)msgResource.makeMessage(session);
		
		//1��ڂ�makeMessage
		if(msg.getText().equals("This is test.")){
			System.out.println(msg.getText());
			dumpProp(msg);
		}
		else{
			throw new Exception();
		}
		//2��ڂ�makeMessage
		msg = (TextMessage)msgResource.makeMessage(session);
		if(msg.getText().equals("That's good.")){
			System.out.println(msg.getText());
			dumpProp(msg);			
		}
		else{
			throw new Exception();
		}
		
		//3��ڂ�makeMessage
		msg = (TextMessage)msgResource.makeMessage(session);
		if(msg.getText().equals("What's wrong?")){
			System.out.println(msg.getText());
			dumpProp(msg);			
		}
		else{
			throw new Exception();
		}
		
		//4��ڂ�makeMessage
		//�f�[�^�t�@�C����1�s�ڂɖ߂�B
		msg = (TextMessage)msgResource.makeMessage(session);
		if(msg.getText().equals("This is test.")){
			System.out.println(msg.getText());
			dumpProp(msg);			
		}
		else{
			throw new Exception();
		}
	}
	*/
	
	public void testSendBytesMessageByte() throws Exception {
		System.out.println("BytesMessage. set Payload Byte");
		MessageResource msgResource = mMessageResourceFactory.findInstance("2");
		//makeMessage���đ��M����B
		BytesMessage msg = (BytesMessage)msgResource.makeMessage(mSession);
		mSender.send(msg);
		//�ǂނ��߂Ƀ��Z�b�g
		msg.reset();
		//byte �� 0x30 = 48 �������Ă��锤
		byte val = msg.readByte();
		byte expected = 48;
		if( val == expected ){
			write("OK");	
		}
		else{
			write("NG this is not expected val " + val);
			throw new Exception();	
		}
	}
	
	public void testSendStreamMessageByte() throws Exception {
		System.out.println("StreamMessage. set Payload Byte");
		MessageResource msgResource = mMessageResourceFactory.findInstance("12");
		//makeMessage���đ��M����B
		StreamMessage msg = (StreamMessage)msgResource.makeMessage(mSession);
		mSender.send(msg);
		//�ǂނ��߂Ƀ��Z�b�g
		msg.reset();
		//byte �� 0x30 = 48 �������Ă��锤
		byte val = msg.readByte();
		byte expected = 48;
		if( val == expected ){
			write("OK");	
		}
		else{
			write("NG this is not expected val " + val);
			throw new Exception();	
		}
	}
		
	public void testSendBytesMessageBytes() throws Exception {
		System.out.println("BytesMessage. set Payload Bytes.");
		MessageResource msgResource = mMessageResourceFactory.findInstance("3");
		//makeMessage���đ��M����B
		BytesMessage msg = (BytesMessage)msgResource.makeMessage(mSession);
		mSender.send(msg);
		//�ǂނ��߂Ƀ��Z�b�g
		msg.reset();
		//boolean �� false �������Ă��锤
		byte[] buf = new byte[6];
		msg.readBytes(buf);		
		byte expected = 48;
		for(int i=0;i<6;i++){
			if (buf[i] != expected){
				write("NG this is not expected val " + buf[i]);
				write("expected val is " + expected);
				throw new Exception();
			}
			expected++;
		}
		write("OK");
	}
	
	public void testStreamMessageBytes() throws Exception {
		System.out.println("StreamMessage. set Payload Bytes.");
		MessageResource msgResource = mMessageResourceFactory.findInstance("13");
		//makeMessage���đ��M����B
		StreamMessage msg = (StreamMessage)msgResource.makeMessage(mSession);
		mSender.send(msg);
		//�ǂނ��߂Ƀ��Z�b�g
		msg.reset();
		//boolean �� false �������Ă��锤
		byte[] buf = new byte[6];
		msg.readBytes(buf);		
		byte expected = 48;
		for(int i=0;i<6;i++){
			if (buf[i] != expected){
				write("NG this is not expected val " + buf[i]);
				write("expected val is " + expected);
				throw new Exception();
			}
			expected++;
		}
		write("OK");
	}
	
	
	public void testSendBytesMessageBoolean() throws Exception {
		System.out.println("BytesMessage. set Payload Boolean.");
		MessageResource msgResource = mMessageResourceFactory.findInstance("4");
		//makeMessage���đ��M����B
		BytesMessage msg = (BytesMessage)msgResource.makeMessage(mSession);
		mSender.send(msg);
		//�ǂނ��߂Ƀ��Z�b�g
		msg.reset();
		//boolean �� false �������Ă��锤
		boolean val = msg.readBoolean();
		if(val == false){
			write("OK");
		}
		else{
			write("NG It's not Expected value " + val + ".");
			throw new Exception();
		}
	}
	
	public void testSendStreamMessageBoolean() throws Exception {
		System.out.println("BytesMessage. set Payload Boolean.");
		MessageResource msgResource = mMessageResourceFactory.findInstance("14");
		//makeMessage���đ��M����B
		StreamMessage msg = (StreamMessage)msgResource.makeMessage(mSession);
		mSender.send(msg);
		//�ǂނ��߂Ƀ��Z�b�g
		msg.reset();
		//boolean �� false �������Ă��锤
		boolean val = msg.readBoolean();
		if(val == false){
			write("OK");
		}
		else{
			write("NG It's not Expected value " + val + ".");
			throw new Exception();
		}
	}
	
	
	public void testSendBytesMessageChar() throws Exception {
		System.out.println("BytesMessage. set Payload Char.");
		MessageResource msgResource = mMessageResourceFactory.findInstance("5");
		//makeMessage���đ��M����B
		BytesMessage msg = (BytesMessage)msgResource.makeMessage(mSession);
		mSender.send(msg);
		//�ǂނ��߂Ƀ��Z�b�g
		msg.reset();
		//char��99�������Ă��锤
		char val = msg.readChar();
		if(val == '��'){
			write("OK");
		}
		else{
			write("NG It's not Expected value " + val + ".");
			write("Expected val is 99");
			throw new Exception();
		}
	}
	
	public void testSendStreamMessageChar() throws Exception {
		System.out.println("StreamMessage. set Payload Char.");
		MessageResource msgResource = mMessageResourceFactory.findInstance("15");
		//makeMessage���đ��M����B
		StreamMessage msg = (StreamMessage)msgResource.makeMessage(mSession);
		mSender.send(msg);
		//�ǂނ��߂Ƀ��Z�b�g
		msg.reset();
		//char��99�������Ă��锤
		char val = msg.readChar();
		if(val == '��'){
			write("OK");
		}
		else{
			write("NG It's not Expected value " + val + ".");
			write("Expected val is 99");
			throw new Exception();
		}
	}
	
	
	public void testSendBytesMessageInt() throws Exception {
		System.out.println("BytesMessage. set Payload Int.");
		MessageResource msgResource = mMessageResourceFactory.findInstance("6");
		//makeMessage���đ��M����B
		BytesMessage msg = (BytesMessage)msgResource.makeMessage(mSession);
		mSender.send(msg);
		//�ǂނ��߂Ƀ��Z�b�g
		msg.reset();
		//int��999�������Ă��锤
		int val = msg.readInt();
		if(val == 999){
			write("OK");
		}
		else{
			write("NG It's not Expected value " + val + ".");
			throw new Exception();
		}
	}
	
	public void testSendStreamMessageInt() throws Exception {
		System.out.println("BytesMessage. set Payload Int.");
		MessageResource msgResource = mMessageResourceFactory.findInstance("16");
		//makeMessage���đ��M����B
		StreamMessage msg = (StreamMessage)msgResource.makeMessage(mSession);
		mSender.send(msg);
		//�ǂނ��߂Ƀ��Z�b�g
		msg.reset();
		//int��999�������Ă��锤
		int val = msg.readInt();
		if(val == 999){
			write("OK");
		}
		else{
			write("NG It's not Expected value " + val + ".");
			throw new Exception();
		}
	}
	
	public void testSendBytesMessageShort() throws Exception {
		System.out.println("BytesMessage. set Payload Short.");
		MessageResource msgResource = mMessageResourceFactory.findInstance("7");
		//makeMessage���đ��M����B
		BytesMessage msg = (BytesMessage)msgResource.makeMessage(mSession);
		mSender.send(msg);
		//�ǂނ��߂Ƀ��Z�b�g
		msg.reset();
		//Short��999�������Ă��锤
		short val = msg.readShort();
		if(val == 999){
			write("OK");
		}
		else{
			write("NG It's not Expected value " + val + ".");
			throw new Exception();
		}
	}
	
	public void testSendStreamMessageShort() throws Exception {
		System.out.println("StreamMessage. set Payload Short.");
		MessageResource msgResource = mMessageResourceFactory.findInstance("17");
		//makeMessage���đ��M����B
		StreamMessage msg = (StreamMessage)msgResource.makeMessage(mSession);
		mSender.send(msg);
		//�ǂނ��߂Ƀ��Z�b�g
		msg.reset();
		//Short��999�������Ă��锤
		short val = msg.readShort();
		if(val == 999){
			write("OK");
		}
		else{
			write("NG It's not Expected value " + val + ".");
			throw new Exception();
		}
	}
	
	public void testSendBytesMessageLong() throws Exception {
		System.out.println("BytesMessage. set Payload Long.");
		MessageResource msgResource = mMessageResourceFactory.findInstance("8");
		//makeMessage���đ��M����B
		BytesMessage msg = (BytesMessage)msgResource.makeMessage(mSession);
		mSender.send(msg);
		//�ǂނ��߂Ƀ��Z�b�g
		msg.reset();
		//long��999�������Ă��锤
		long val = msg.readLong();
		if(val == 999){
			write("OK");
		}
		else{
			write("NG It's not Expected value " + val + ".");
			throw new Exception();
		}
	}
	
	public void testSendStreamMessageLong() throws Exception {
		System.out.println("StreamMessage. set Payload Long.");
		MessageResource msgResource = mMessageResourceFactory.findInstance("18");
		//makeMessage���đ��M����B
		StreamMessage msg = (StreamMessage)msgResource.makeMessage(mSession);
		mSender.send(msg);
		//�ǂނ��߂Ƀ��Z�b�g
		msg.reset();
		//long��999�������Ă��锤
		long val = msg.readLong();
		if(val == 999){
			write("OK");
		}
		else{
			write("NG It's not Expected value " + val + ".");
			throw new Exception();
		}
	}
	
	public void testSendBytesMessageFloat() throws Exception {
		System.out.println("BytesMessage. set Payload Float.");
		MessageResource msgResource = mMessageResourceFactory.findInstance("9");
		//makeMessage���đ��M����B
		BytesMessage msg = (BytesMessage)msgResource.makeMessage(mSession);
		mSender.send(msg);
		//�ǂނ��߂Ƀ��Z�b�g
		msg.reset();
		//float��1.2345F�������Ă���͂�
		float val = msg.readFloat();
		float expectedVal = 1.2345F;
		if(val == expectedVal){
			write("OK");
		}
		else{
			write("NG It's not Expected value " + val + ".");
			throw new Exception();
		}
	}
	
	public void testSendStreamMessageFloat() throws Exception {
		System.out.println("StreamMessage. set Payload Float.");
		MessageResource msgResource = mMessageResourceFactory.findInstance("19");
		//makeMessage���đ��M����B
		StreamMessage msg = (StreamMessage)msgResource.makeMessage(mSession);
		mSender.send(msg);
		//�ǂނ��߂Ƀ��Z�b�g
		msg.reset();
		//float��1.2345F�������Ă���͂�
		float val = msg.readFloat();
		float expectedVal = 1.2345F;
		if(val == expectedVal){
			write("OK");
		}
		else{
			write("NG It's not Expected value " + val + ".");
			throw new Exception();
		}
	}
	
	public void testSendBytesMessageDouble() throws Exception {
		System.out.println("BytesMessage. set Payload Double.");
		MessageResource msgResource = mMessageResourceFactory.findInstance("10");
		//makeMessage���đ��M����B
		BytesMessage msg = (BytesMessage)msgResource.makeMessage(mSession);
		mSender.send(msg);
		//�ǂނ��߂Ƀ��Z�b�g
		msg.reset();
		//double��2.3456�������Ă���͂�
		double val = msg.readDouble();
		double expectedVal = 2.3456;
		if(val == expectedVal){
			write("OK");
		}
		else{
			write("NG It's not Expected value " + val + ".");
			throw new Exception();
		}
	}
	
	public void testSendStreamMessageDouble() throws Exception {
		System.out.println("StreamMessage. set Payload Double.");
		MessageResource msgResource = mMessageResourceFactory.findInstance("20");
		//makeMessage���đ��M����B
		StreamMessage msg = (StreamMessage)msgResource.makeMessage(mSession);
		mSender.send(msg);
		//�ǂނ��߂Ƀ��Z�b�g
		msg.reset();
		//double��2.3456�������Ă���͂�
		double val = msg.readDouble();
		double expectedVal = 2.3456;
		if(val == expectedVal){
			write("OK");
		}
		else{
			write("NG It's not Expected value " + val + ".");
			throw new Exception();
		}
	}
	
	public void testSendBytesMessageUTF() throws Exception {
		System.out.println("BytesMessage. set Payload UTF.");
		MessageResource msgResource = mMessageResourceFactory.findInstance("11");
		//makeMessage���đ��M����B
		BytesMessage msg = (BytesMessage)msgResource.makeMessage(mSession);
		mSender.send(msg);
		//�ǂނ��߂Ƀ��Z�b�g
		msg.reset();
		//"Hello World"�������Ă��锤
		String val = msg.readUTF();
		if(val.equals("Hello World")){
			write("OK");
		}
		else{
			write("NG It's not Expected value " + val + ".");
			throw new Exception();
		}
	}
	
	public void testSendStreamMessageString() throws Exception {
		System.out.println("StreamMessage. set Payload String.");
		MessageResource msgResource = mMessageResourceFactory.findInstance("21");
		//makeMessage���đ��M����B
		StreamMessage msg = (StreamMessage)msgResource.makeMessage(mSession);
		mSender.send(msg);
		//�ǂވׂɃ��Z�b�g
		msg.reset();
		//"Hello World"�������Ă��锤
		String val = msg.readString();
		if(val.equals("Hello World")){
			write("OK");
		}
		else{
			write("NG It's not Expected value " + val + ".");
			throw new Exception();
		}
	}
	
	public void testSendBytesMessageObject() throws Exception {
		System.out.println("BytesMessage. set Payload Object.");
		MessageResource msgResource = mMessageResourceFactory.findInstance("22");
		//makeMessage���đ��M����B
		BytesMessage msg = (BytesMessage)msgResource.makeMessage(mSession);
		mSender.send(msg);
		//�ǂނ��߂Ƀ��Z�b�g
		msg.reset();
		//"Hello World"�������Ă��锤
		int val = msg.readInt();
		if(val == 777){
			write("OK");
		}
		else{
			write("NG It's not Expected value " + val + ".");
			throw new Exception();
		}
	}
	
	public void testSendStreamMessageObject() throws Exception {
		System.out.println("StreamMessage. set Payload Object.");
		MessageResource msgResource = mMessageResourceFactory.findInstance("23");
		//makeMessage���đ��M����B
		StreamMessage msg = (StreamMessage)msgResource.makeMessage(mSession);
		mSender.send(msg);
		//�ǂނ��߂Ƀ��Z�b�g
		msg.reset();
		//"Hello World"�������Ă��锤
		Integer val = (Integer)msg.readObject();
		if(val.intValue() == 777){
			write("OK");
		}
		else{
			write("NG It's not Expected value " + val + ".");
			throw new Exception();
		}
	}
	
	public void testSendStreamMessageIntUseFileData() throws Exception {
		System.out.println("StreamMessage. set Payload Int");
		MessageResource msgResource = mMessageResourceFactory.findInstance("24");
		//makeMessage���đ��M����B
		StreamMessage msg = (StreamMessage)msgResource.makeMessage(mSession);
		mSender.send(msg);
		//�ǂނ��߂Ƀ��Z�b�g
		msg.reset();
		//555�������Ă���͂��B
		int val1 = msg.readInt();
		int val2 = msg.readInt();
		int val3 = msg.readInt();		
		if( (val1 == 101) && (val2 == 201) && (val3 == 301)){
			write("OK");
		}	
		else{
			write("NG It's not Expected value " + val1 + ","+ val2 + ","+ val3 + ".");
			throw new Exception();
		}
	}
	
	public void testSendBytesMessageObjectAllType() throws Exception {
		System.out.println("BytesMessage. set Payload Object.(All type)");
		MessageResource msgResource = mMessageResourceFactory.findInstance("57");
		//makeMessage���đ��M����B
		BytesMessage msg = (BytesMessage)msgResource.makeMessage(mSession);
		mSender.send(msg);
		//�ǂނ��߂Ƀ��Z�b�g
		msg.reset();
		//Byte 0x30
		byte payload1 = msg.readByte();
		assertEquals(48,payload1);
		//Bytes 0x30,0x31,0x32,0x33
		byte[] payload2 = new byte[4];
		msg.readBytes(payload2);
		assertEquals(48,payload2[0]); 
		assertEquals(49,payload2[1]); 
		assertEquals(50,payload2[2]); 
		assertEquals(51,payload2[3]); 
		//Boolean false
		boolean payload3 = msg.readBoolean();	
		assertEquals(false,payload3);
		//Char "�Z"
		char payload4 = msg.readChar();
		assertEquals('�Z',payload4);
		//Short 555
		short payload5 = msg.readShort();
		assertEquals(555,payload5);
		//Int 55555
		int payload6 = msg.readInt();
		assertEquals(55555,payload6);
		//Long 5555555
		long payload7 = msg.readLong();
		assertEquals(5555555,payload7);
		//Float 1.2345F
		float payload8 = msg.readFloat();
		//���̏ꍇ�A�f���^��0�ł悢���낤�B
		assertEquals(1.2345F,payload8,0.0F);
		//Double 2.3456
		double payload9 = msg.readDouble();
		//���̏ꍇ�A�f���^��0�ł悢���낤�B
		assertEquals(2.3456,payload9,0.0);
		//String "Hello"
		String payload10 = msg.readUTF();
		assertEquals("Hello",payload10);	
	}
	
	public void testSendStreamMessageObjectAllType() throws Exception {
		System.out.println("StreamMessage. set Payload Object.(All type)");
		MessageResource msgResource = mMessageResourceFactory.findInstance("58");
		//makeMessage���đ��M����B
		StreamMessage msg = (StreamMessage)msgResource.makeMessage(mSession);
		mSender.send(msg);
		//�ǂނ��߂Ƀ��Z�b�g
		msg.reset();
		//Byte 0x30
		byte payload1 = msg.readByte();
		assertEquals(48,payload1);
		//Bytes 0x30,0x31,0x32,0x33
		byte[] payload2 = new byte[4];
		msg.readBytes(payload2);
		assertEquals(48,payload2[0]); 
		assertEquals(49,payload2[1]); 
		assertEquals(50,payload2[2]); 
		assertEquals(51,payload2[3]); 
		//Boolean false
		boolean payload3 = msg.readBoolean();	
		assertEquals(false,payload3);
		//Char "�Z"
		char payload4 = msg.readChar();
		assertEquals('�Z',payload4);
		//Short 555
		short payload5 = msg.readShort();
		assertEquals(555,payload5);
		//Int 55555
		int payload6 = msg.readInt();
		assertEquals(55555,payload6);
		//Long 5555555
		long payload7 = msg.readLong();
		assertEquals(5555555,payload7);
		//Float 1.2345F
		float payload8 = msg.readFloat();
		//���̏ꍇ�A�f���^��0�ł悢���낤�B
		assertEquals(1.2345F,payload8,0.0F);
		//Double 2.3456
		double payload9 = msg.readDouble();
		//���̏ꍇ�A�f���^��0�ł悢���낤�B
		assertEquals(2.3456,payload9,0.0);
		//String "Hello"
		String payload10 = msg.readString();
		assertEquals("Hello",payload10);	
	}
	
	
	/*
	public void testSendStreamMessage() throws Exception {
		MessageResourceFactory msgResourceFactory = (MessageResourceFactory)ServiceManagerFactory.getServiceObject("TheManager","MessageResourceFactoryService");
		JmsQueueSession jmsQueSession = (JmsQueueSession)ServiceManagerFactory.getServiceObject("TheManager","JmsQueueSessionService");
		//���b�Z�[�W���\�[�X���擾
		MessageResource msgResource = msgResourceFactory.findInstance("3");
		//QueueSession���擾
		QueueSession session = jmsQueSession.makeQueueSession(QueueConnectionFactoryName);
		JndiFinder finder = (JndiFinder)ServiceManagerFactory.getServiceObject("TheManager","JndiFinderService");
		//Queue���擾
		Queue queue = (Queue)finder.lookup(QueueName);
		//QueueSender�쐬
		QueueSender sender = session.createSender(queue);
		//4��makeMessage���đ��M����B
		for(int rCnt=0;rCnt<4;rCnt++){
			StreamMessage msg = (StreamMessage)msgResource.makeMessage(session);
			//���M
			sender.send(msg);			
		}
	}
	
	
	//TextMessage�𑗐M����B
	public void testSendTextMessage() throws Exception{
		MessageResourceFactory msgResourceFactory = (MessageResourceFactory)ServiceManagerFactory.getServiceObject("TheManager","MessageResourceFactoryService");
		JmsQueueSession jmsQueSession = (JmsQueueSession)ServiceManagerFactory.getServiceObject("TheManager","JmsQueueSessionService");
		JndiFinder finder = (JndiFinder)ServiceManagerFactory.getServiceObject("TheManager","JndiFinderService");
		//���b�Z�[�W���\�[�X���擾
		MessageResource msgResource = msgResourceFactory.findInstance("1");
		//QueueSession���擾
		QueueSession session = jmsQueSession.makeQueueSession(QueueConnectionFactoryName);
		TextMessage msg = (TextMessage)msgResource.makeMessage(session);
		//Queue���擾
		Queue queue = (Queue)finder.lookup(QueueName);
		//QueueSender�쐬
		QueueSender sender = session.createSender(queue);
		//���M
		sender.send(msg);
	}

	public void testSendMapMessage() throws Exception{
		MessageResourceFactory msgResourceFactory = (MessageResourceFactory)ServiceManagerFactory.getServiceObject("TheManager","MessageResourceFactoryService");
		JmsQueueSession jmsQueSession = (JmsQueueSession)ServiceManagerFactory.getServiceObject("TheManager","JmsQueueSessionService");
		JndiFinder finder = (JndiFinder)ServiceManagerFactory.getServiceObject("TheManager","JndiFinderService");
		//���b�Z�[�W���\�[�X���擾
		MessageResource msgResource = msgResourceFactory.findInstance("4");
		//QueueSession���擾
		QueueSession session = jmsQueSession.makeQueueSession(QueueConnectionFactoryName);
		MapMessage msg = (MapMessage)msgResource.makeMessage(session);
		//Queue���擾
		Queue queue = (Queue)finder.lookup(QueueName);
		//QueueSender�쐬
		QueueSender sender = session.createSender(queue);
		//���M
		sender.send(msg);
	}
	
	public void testSendObjectMessage() throws Exception{
		MessageResourceFactory msgResourceFactory = (MessageResourceFactory)ServiceManagerFactory.getServiceObject("TheManager","MessageResourceFactoryService");
		JmsQueueSession jmsQueSession = (JmsQueueSession)ServiceManagerFactory.getServiceObject("TheManager","JmsQueueSessionService");
		JndiFinder finder = (JndiFinder)ServiceManagerFactory.getServiceObject("TheManager","JndiFinderService");
		//���b�Z�[�W���\�[�X���擾
		MessageResource msgResource = msgResourceFactory.findInstance("5");
		//QueueSession���擾
		QueueSession session = jmsQueSession.makeQueueSession(QueueConnectionFactoryName);
		
		//Queue���擾
		Queue queue = (Queue)finder.lookup(QueueName);
		//QueueSender�쐬
		QueueSender sender = session.createSender(queue);
		//���M
		for(int rCnt=0;rCnt<4;rCnt++){
			ObjectMessage msg = (ObjectMessage)msgResource.makeMessage(session);
			sender.send(msg);
		}
	}
	*/

}
