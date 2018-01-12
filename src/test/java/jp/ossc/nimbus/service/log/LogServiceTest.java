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
package jp.ossc.nimbus.service.log;

import junit.framework.TestCase;
import jp.ossc.nimbus.core.*;
import jp.ossc.nimbus.lang.*;
import jp.ossc.nimbus.service.message.*;
import jp.ossc.nimbus.beans.*;
import jp.ossc.nimbus.service.writer.*;

/**
 *	
 *	@author	y-tokuda
 *	@version	1.00 �쐬�F2003/11/13�| y-tokuda<BR>
 *				�X�V�F
 */
public class LogServiceTest extends TestCase {

	/**
	 * Constructor for LogServiceTest.
	 * @param arg0
	 */
	public LogServiceTest(String arg0) {
		super(arg0);
	}
	protected void setUp(){
		ServiceManagerFactory.loadManager("jp/ossc/nimbus/service/log/kurofune-service.xml");
	}
	protected void tearDown(){
		ServiceManagerFactory.unloadManager("jp/ossc/nimbus/service/log/kurofune-service.xml");
	}
	
	public static void main(String[] args) {
		junit.textui.TestRunner.run(LogServiceTest.class);
	}
	//��`����Ă���R�[�h�͐���ɏo��	
	public void testDebug() throws Exception{
		System.out.println("Debug test start.");
		Logger logger = (Logger)ServiceManagerFactory.getServiceObject("TheManager","LogDebugService");	
		logger.debug("This is Debug Test 1.");
		logger.debug("This is Debug Test 2.");
		logger.debug("This is Debug Test 3.");
		((LogService)logger).stop();
		System.out.println("Debug test end.");
	}
	//��`����Ă���R�[�h�͐���ɏo��	
	public void testWrite1() throws Exception{
		Logger logger = (Logger)ServiceManagerFactory.getServiceObject("TheManager","LogService");
		((LogService)logger).start();
		logger.write("TST000001");	
		((LogService)logger).stop();
	}

	//��`����Ă��Ȃ��R�[�h�͏o�͂��Ȃ��B
	public void testWrite2() throws Exception{
		Logger logger = (Logger)ServiceManagerFactory.getServiceObject("TheManager","LogService");
		((LogService)logger).start();
		logger.write("TST111111");
		logger.write("TST000003");	
		((LogService)logger).stop();
	}

	//���ߍ��ݕ���������
	public void testWrite3() throws Exception{
		Logger logger = (Logger)ServiceManagerFactory.getServiceObject("TheManager","LogService");
		((LogService)logger).start();
		logger.write("TST000001","���ߍ��ݕ���1");	
		((LogService)logger).stop();
	}

	//��`����Ă��Ȃ��R�[�h�ł���΁A�o�͂��Ȃ��i�����Q��write�ɂ��āj
	public void testWrite4() throws Exception{
		Logger logger = (Logger)ServiceManagerFactory.getServiceObject("TheManager","LogService");
		((LogService)logger).start();
		logger.write("TST111111","���ߍ��ݕ���1");	
		((LogService)logger).stop();
	}

	//���ߍ��ݕ�����𕡐��w��
	public void testWrite5() throws Exception{
		Logger logger = (Logger)ServiceManagerFactory.getServiceObject("TheManager","LogService");
		((LogService)logger).start();
		String[] messages = {"���ߍ��ݕ���1","���ߍ��ݕ���2","���ߍ��ݕ���3","���ߍ��ݕ���4","���ߍ��ݕ���5"};
		logger.write("TST000002",messages);	
		//������͏o�͂���Ȃ��B�i��`����Ă��Ȃ��R�[�h�j
		logger.write("TST111111",messages);
		((LogService)logger).stop();
	}

	//�G�N�Z�v�V�����t��
	public void testWrite6() throws Exception{
		Logger logger = (Logger)ServiceManagerFactory.getServiceObject("TheManager","LogService");
		((LogService)logger).start();
		Object obj = null;
		try{
			obj.toString();
		}
		catch(Exception e){
			logger.write("TST000001",e);
		}
		((LogService)logger).stop();
	}

	//�G�N�Z�v�V�����Ɩ��ߍ��ݕ����t��
	public void testWrite7() throws Exception{
		Logger logger = (Logger)ServiceManagerFactory.getServiceObject("TheManager","LogService");
		((LogService)logger).start();
		String[] messages = {"����1","����2","����3","����4","����5"};
		Object obj = null;
		try{
			obj.toString();
		}
		catch(Exception e){
			logger.write("TST000002",messages,e);
		}
		((LogService)logger).stop();
	}

	//AppException
	public void testWrite8() throws Exception{
		Logger logger = (Logger)ServiceManagerFactory.getServiceObject("TheManager","LogService");
		((LogService)logger).start();
		LogMessageRecord rec = new LogMessageRecordImpl();
//		((MessageRecordImpl)rec).rec2Obj("TST000001,ApplicationException_1_@0,55,CATEGORY1");
		((MessageRecordImpl)rec).rec2Obj("TST000001,ApplicationException_1_@0");
		MessageRecordFactory fac = (MessageRecordFactory)ServiceManagerFactory.getServiceObject("TheManager","MessageService");
		((MessageRecordOperator)rec).setFactory(fac);
		AppException appExp = new AppException("APP000001","�A�v���P�[�V�����G�N�Z�v�V����",rec);
		
		logger.write(appExp);
		((LogService)logger).stop();
	}

	public void testWrite9() throws Exception{
		Logger logger = (Logger)ServiceManagerFactory.getServiceObject("TheManager","LogService");
		LogService logServ = (LogService)logger;
		/*
		System.out.println(logServ.getMessageServiceName());
		System.out.println(logServ.getQueueServiceName());
		System.out.println(logServ.getWritableRecordFactoryServiceName());
		System.out.println(logServ.getWritableRecordFactoryServiceName());
		System.out.println(logServ.getThreadContextServiceName());
		System.out.println(logServ.getMessageWriterServiceNames());
		System.out.println(logServ.getLogCategories());
		*/
	}
	
	public void testWrite10() throws Exception{
		Logger logger = (Logger)ServiceManagerFactory.getServiceObject("TheManager","LogService");
		LogService logServ = (LogService)logger;
        logServ.start();
		logger.write("TST000003");
		logger.write("TST000004");
		logger.write("TST000005");
		logger.write("TST000006");
		logger.write("TST000007");
		logger.write("TST000008");
		logger.write("TST000009");
		logger.write("TST000010");
		((LogService)logger).stop();
	}
	//Category�̃��x��100�B 100�ȉ��͏o�͂��Ȃ��B
	public void testWrite11() throws Exception{
		System.out.println("testWrite10 start.");
		Logger logger = (Logger)ServiceManagerFactory.getServiceObject("TheManager","LogService2");
		LogService logServ = (LogService)logger;
        logServ.start();
		logger.write("TST000003");
		logger.write("TST000004");
		logger.write("TST000005");
		logger.write("TST000006");
		logger.write("TST000007");
		logger.write("TST000008");
		logger.write("TST000009");
		logger.write("TST000010");
		((LogService)logger).stop();
		System.out.println("testWrite10 stop.");
	}
	//
	public void testGetLogLevel() throws Exception{
		System.out.println("testGetLogLevel start.");
		Logger logger = (Logger)ServiceManagerFactory.getServiceObject("TheManager","LogService");
		LogService logServ = (LogService)logger;
        logServ.start();
		
//		assertEquals(0,logServ.getPriorityRangeMin("CATEGORY1"));
//		assertEquals(49,logServ.getPriorityRangeMax("CATEGORY1"));
//		if(logServ.getLogLevelFrom("CATEGORY1") == 50){
//			System.out.println("CATEGORY1's level is 50");
//		}
//		else{
//			System.out.println("CATEGORY1's level is NOT  50. level is " + logServ.getLogLevelFrom("CATEGORY1"));
//			throw new Exception();
//		}
		//��`����Ă��Ȃ��J�e�S�����w�肵���ꍇ�A-1��Ԃ��B
//		if(logServ.getPriorityRangeMin("SOMECATEGORY") == -1){
//			System.out.println("SOMECATEGORY's level is -1");
//		}
//		else{
//			System.out.println("SOMECATEGORY's level is NOT  -1. level is " + logServ.getPriorityRangeMin("SOMECATEGORY"));
//			throw new Exception();
//		}
		System.out.println("testGetLogLevel end.");
	}
	
	//Category�̃��x��100�B 100�ȉ��͏o�͂��Ȃ��B
	public void testChangeLogLevel() throws Exception{
		System.out.println("testChangeLogLevel start.");
		Logger logger = (Logger)ServiceManagerFactory.getServiceObject("TheManager","LogService3");
		LogService logServ = (LogService)logger;
		((LogService)logger).start();
		logger.write("TST999998");
		logger.write("TST000003");
		logger.write("TST000004");
		logger.write("TST000005");
		logger.write("TST000006");
		logger.write("TST000007");
		logger.write("TST000008");
		logger.write("TST000009");
		logger.write("TST000010");
		logger.write("TST999999");
//		logServ.setPriorityRange("CATEGORY1",200,250);
		logger.write("TST999998");
		logger.write("TST000003");
		logger.write("TST000004");
		logger.write("TST000005");
		logger.write("TST000006");
		logger.write("TST000007");
		logger.write("TST000008");
		logger.write("TST000009");
		logger.write("TST999999");
		System.out.println("testChangeLogLevel stop.");
		logServ.stop();		
	}
	/**
	 * SystemDebug��Writer��ύX����B
	 * @throws Exception
	 */
	public void testDebugWriterChange() throws Exception{
		System.out.println("testDebugWriterChange start.");
		LogService logService = (LogService)ServiceManagerFactory.getServiceObject("TheManager","LogServiceForSystemMethodTest");
		//Writer��؂�ւ���O��write����B�W���o�͂ɂł�B
		logService.write("NIMBUS001");
		logService.write("NIMBUS002");
		logService.write("NIMBUS003");
		ServiceNameEditor editor = new ServiceNameEditor();
		editor.setAsText("TheManager#SystemDebugWriter");
		ServiceName writerName = (ServiceName)editor.getValue();
		logService.setSystemDebugMessageWriterServiceName(writerName);
		//��~���āA�ċN���Ŕ��f�����B
		logService.stop();
		logService.start();
		logService.write("NIMBUS001");//Writer��؂�ւ����̂ŁAFile�ɏo�͂����B
		logService.write("NIMBUS002");//Writer��؂�ւ����̂ŁAFile�ɏo�͂����B
		logService.write("NIMBUS003");//Writer�͐؂�ւ��Ă��Ȃ��̂ŁA�W���o�͂ɏo�͂����B
	}
	/**
	 * SystemInfo��Writer��ύX����B
	 * @throws Exception
	 */
	public void testInfoWriterChange() throws Exception{
		System.out.println("testInfoWriterChange start.");
		LogService logService = (LogService)ServiceManagerFactory.getServiceObject("TheManager","LogServiceForSystemMethodTest");
		//Writer��؂�ւ���O��write����B�W���o�͂ɂł�B
		logService.write("NIMBUS001");
		logService.write("NIMBUS002");
		logService.write("NIMBUS003");
		ServiceNameEditor editor = new ServiceNameEditor();
		editor.setAsText("TheManager#SystemInfoWriter");
		ServiceName writerName = (ServiceName)editor.getValue();
		logService.setSystemInfoMessageWriterServiceName(writerName);
		//��~���āA�ċN���Ŕ��f�����B
		logService.stop();
		logService.start();
		logService.write("NIMBUS001");
		logService.write("NIMBUS002");
		logService.write("NIMBUS003");
	}
	/**
	 * SystemWarn��WritableRecordFactory��ύX����B
	 * @throws Exception
	 */
	public void testWarnWritableRecordChange() throws Exception{
		System.out.println("testWarnWritableRecordChange start.");
		LogService logService = (LogService)ServiceManagerFactory.getServiceObject("TheManager","LogServiceForSystemMethodTest");
		//WritableRecordFactory��؂�ւ���O��write����B�ʏ�̃t�H�[�}�b�g�ŏo�͂����B
		logService.write("NIMBUS005");
		logService.write("NIMBUS006");
		//WritableRecordFactory��؂�ւ���B
		ServiceNameEditor editor = new ServiceNameEditor();
		editor.setAsText("TheManager#LogWritableRecordFactorySystemWarn");
		ServiceName factoryName = (ServiceName)editor.getValue();
		logService.setSystemWarnWritableRecordFactoryServiceName(factoryName);
		//��~���āA�ċN���Ŕ��f�����B
		logService.stop();
		logService.start();
		logService.write("NIMBUS005");
		logService.write("NIMBUS006");
		//�L���[�̓��e��f�����邽�߂�stop();
		logService.stop();
		logService.start();
	}
	
	/**
	 * Error�o�͂�L��/�����ɂ���B
	 * @throws Exception
	 */
	public void testSetSystemError() throws Exception{
		System.out.println("testSetSystemError start.");
		LogService logService = (LogService)ServiceManagerFactory.getServiceObject("TheManager","LogServiceForSystemMethodTest");
		//SystemError�͗L��
		logService.write("NIMBUS007");
		logService.write("NIMBUS008");
		System.out.println("setSystemError(false)");
		//SystemError�𖳌��ɂ���B
		logService.setSystemErrorEnabled(false);
		//�����ɂ��ꂽ�̂ŁA�o�͂���Ȃ��B
		logService.write("NIMBUS007");
		logService.write("NIMBUS008");
		System.out.println("setSystemError(true)");
		//SystemError��L���ɂ���B
		logService.setSystemErrorEnabled(true);
		logService.write("NIMBUS007");
		logService.write("NIMBUS008");
		//�L���[�̓��e��f�����邽�߂�stop();
		logService.stop();
		logService.start();
		System.out.println("testSetSystemError End.");
		//���v4�s�W���o�͂ɏo�͂����΂悢�B�`�F�b�N�̎������E�ڍ׉��͉ۑ�B
	}
	/**
	 * setDefaultMessageWriterServiceName(ServiceName)�̃e�X�g
	 */
	public void testSetDefaultMessageWriterServiceName() throws Exception{
		LogService logService = (LogService)ServiceManagerFactory.getServiceObject("TheManager","LogServiceForSetDefaultMessageWriterServiceTest");
		/* �A�v���P�[�V�������O�̊m�F �i�ꉞ�j*/
		logService.write("TST000011");
		logService.write("TST000012");
		logService.write("TST000013");
		logService.write("TST000014");
		logService.write("TST000015");
		logService.write("TST000016");
		logService.write("TST000017");
		logService.write("TST000018");
		logService.write("TST000019");
		logService.write("TST000020");
		/* debug���\�b�h�̊m�F */
		/* Default��MessageWriterService��writer3�Ɏw�肵�Ă���̂ŁAtemp/LogServiceTest3.log�ɏo�͂���� */
		logService.setDebugEnabled(true);
		logService.debug("This is Debug Message");
	}
	
	public void testSetDefaultWritableRecordFactoryServiceName() throws Exception{
		LogService logService = (LogService)ServiceManagerFactory.getServiceObject("TheManager","LogServiceForsetDefaultWritableRecordFactoryServiceName");
		/* debug���\�b�h�̊m�F */
		/* Default��MessageWriterService��writer3�Ɏw�肵�Ă���̂ŁAtemp/LogServiceTest3.log�ɏo�͂���� */
		/* Default��LogWritableRecordFactory��LogWritableRecordFactoryDefaultChTst�ɂ��Ă���̂ŁA*/
		/* ���b�Z�[�W�̑O�ɁA"LogWritableRecordFactoryDefaultChTst:"�ƕ\�������B*/
		logService.setDebugEnabled(true);
		logService.debug("This is Debug Message");
		logService.stop();
		logService.start();
	}
	
	
	public void testDestroyServiceNoMessageRecordServiceCase() throws Exception{
		/* MessageRecordService�̎w�肪�Ȃ�LogService���擾 */
		LogService logService = (LogService)ServiceManagerFactory.getServiceObject("TheManager","LogServiceNoMessageRecordService");
		logService.stop();
		logService.destroyService();
	}
	
	
	public void testSetCategoryServices() throws Exception{
		/* LogService�́AsetDefaultMessageWriterService�̃e�X�g�Ɏg�������̂��g���܂킷 */
		LogService logService = (LogService)ServiceManagerFactory.getServiceObject("TheManager","LogServiceForSetDefaultMessageWriterServiceTest");
		
		
		final SimpleCategoryService category = new SimpleCategoryService();
		category.setCategoryName("TstCategory");
		category.setPriorityRangeValue(300, 399);
		category.setLabel(300, 399, "TstCategory Label");
		category.create();
		category.start();		
		LogCategory[] categories = {category};
		logService.stop();
		logService.setCategoryServices(categories);
		logService.start();
		logService.write("TST000021");
		logService.stop();

	}
	
	/** getCategoryServices�ƁAsetLabel���e�X�g���� */
	public void testGetCategoryServices() throws Exception{
		/* LogService�́AsetDefaultWritableRecordFactoryServiceName�̃e�X�g�Ɏg�������̂��g���܂킷 */
		LogService logService = (LogService)ServiceManagerFactory.getServiceObject("TheManager","LogServiceForsetDefaultWritableRecordFactoryServiceName");
		logService.setSystemDebugEnabled(true);
        LogCategory[] cats = logService.getCategoryServices();		
		for(int i=0;i<cats.length;i++){
			String name = cats[i].getCategoryName();
			if(name.equals("jp.ossc.nimbus.service.log.SYSTEM_WARN_CATEGORY")){
//				assertEquals(149,cats[i].getPriorityRangeMax());
//				assertEquals(100,cats[i].getPriorityRangeMin());
			}
			if(name.equals("jp.ossc.nimbus.service.log.SYSTEM_DEBUG_CATEGORY")){
//				assertEquals(49,cats[i].getPriorityRangeMax());
//				assertEquals(0,cats[i].getPriorityRangeMin());
			}
			if(name.equals("jp.ossc.nimbus.service.log.SYSTEM_INFO_CATEGORY")){
//				assertEquals(99,cats[i].getPriorityRangeMax());
//				assertEquals(50,cats[i].getPriorityRangeMin());
			}			
			if(name.equals("jp.ossc.nimbus.service.log.SYSTEM_ERROR_CATEGORY")){
//				assertEquals(199,cats[i].getPriorityRangeMax());
//				assertEquals(150,cats[i].getPriorityRangeMin());
			}
			if(name.equals("jp.ossc.nimbus.service.log.SYSTEM_FATAL_CATEGORY")){
//				assertEquals(249,cats[i].getPriorityRangeMax());
//				assertEquals(200,cats[i].getPriorityRangeMin());
			}
			if(name.equals("jp.ossc.nimbus.service.log.DEBUG_METHOD_CATEGORY")){
//				assertEquals(-1,cats[i].getPriorityRangeMax());
//				assertEquals(-1,cats[i].getPriorityRangeMin());
			}
		}
//		logService.setLabel("CATEGORY1",0,49,"HOGEHOGE");
		/** Label��"HOGEHOGE"�ɕύX����� */
		logService.write("TST000020");
		/** ���łɁAgetPriorityRangeMax()�ُ̈�n��ʂ��R�[�h�������Ă��� */
//		int max = logService.getPriorityRangeMax("NONAMECATEGORY");/* ����ȃJ�e�S���͂Ȃ� */
//		assertEquals(-1,max);
		/** ���łɁAgetQueueService()��queue���擾�ł��邩�ǂ����m�F */
		assertNotNull(logService.getQueueService());
		logService.stop();

	}
	
	/** �l�X�Ȉ�����write���\�b�h���e�X�g���� */
	public void testWriteMethods() throws Exception{
		/* LogService�́AsetDefaultMessageWriterService�̃e�X�g�Ɏg�������̂��g���܂킷 */
		LogService logService = (LogService)ServiceManagerFactory.getServiceObject("TheManager",
																	"LogServiceForWriteMethodsTst");
		/* �ȉ��ALogServiceTest1.log�ɏo�͂���� */
		
		/* byte���� */
		byte x = 128 -1 ;
		logService.write("TST000022",x);
		/* short���� */
		short y = 256*256/2 -1;
		logService.write("TST000022",y);
		/* char ���� */
		char c = 'A';
		logService.write("TST000022",c);
		/* int �����@*/
		int z = Integer.MAX_VALUE;;
		logService.write("TST000022",z);
		/* long ���� */
		long zz = Long.MAX_VALUE;
		logService.write("TST000022",zz);
		/* float ���� */
		float f = 1.2345f;
		logService.write("TST000022",f);
		/* double ���� */
		double d = 2.3456;
		logService.write("TST000022",d);
		
		/* �z����� �e�X�g �z��ϐ����� */
		byte x2 = (byte)(x - 1);
		byte[] b_ary = {x,x2};
		short y2 = (byte)(y - 1);
		short[] s_ary ={y,y2};
		char c2 = 'B';
		char[] c_ary ={c,c2};
		int z2 = Integer.MIN_VALUE;
		int[] i_ary= {z,z2};
		long zz2 = Long.MIN_VALUE;
		long[] l_ary = {zz,zz2};
		float f2 = f - 1.0f;
		float[] f_ary = {f,f2};
		double d2 = d - 1.0;
		double[] d_ary = {d,d2};
		
		/* byte�z����� */
		logService.write("TST000022",b_ary);
		/* short�z����� */
		logService.write("TST000022",s_ary);
		/* char �z����� */
		logService.write("TST000022",c_ary);
		/* int �z������@*/
		logService.write("TST000022",i_ary);
		/* long �z����� */
		logService.write("TST000022",l_ary);
		/* float �z����� */
		logService.write("TST000022",f_ary);
		/* double �z����� */
		logService.write("TST000022",d_ary);
		
		/* Exception �t�� */
		try{
			String str = null;
			str.length();
		}
		catch(Exception e){
			logService.write("TST000023",e);
			/* byte���� */
			logService.write("TST000022",x,e);
			/* short���� */
			logService.write("TST000022",y,e);
			/* char ���� */
			logService.write("TST000022",c,e);
			/* int �����@*/
			logService.write("TST000022",z,e);
			/* long ���� */
			logService.write("TST000022",zz,e);
			/* float ���� */
			logService.write("TST000022",f,e);
			/* double ���� */
			logService.write("TST000022",d,e);
			/* byte�z����� */
			logService.write("TST000022",b_ary,e);
			/* short�z����� */
			logService.write("TST000022",s_ary,e);
			/* char �z����� */
			logService.write("TST000022",c_ary,e);
			/* int �z������@*/
			logService.write("TST000022",i_ary,e);
			/* long �z����� */
			logService.write("TST000022",l_ary,e);
			/* float �z����� */
			logService.write("TST000022",f_ary,e);
			/* double �z����� */
			logService.write("TST000022",d_ary,e);
				
			/* Message���擾�ł��Ȃ������e�X�g�P�[�X���J�o�[����ׁA�Ӑ}���đ��݂��Ȃ��R�[�h���w�� */
			logService.write("HOGEHOGE",e);
			/* byte���� */
			logService.write("HOGEHOGE",x,e);
			/* short���� */
			logService.write("HOGEHOGE",y,e);
			/* char ���� */
			logService.write("HOGEHOGE",c,e);
			/* int �����@*/
			logService.write("HOGEHOGE",z,e);
			/* long ���� */
			logService.write("HOGEHOGE",zz,e);
			/* float ���� */
			logService.write("HOGEHOGE",f,e);
			/* double ���� */
			logService.write("HOGEHOGE",d,e);
		}
		logService.stop();
		logService.start();
	}
	/** MessageWriter��Getter���e�X�g���� */
	public void testMessageWriterGetter() throws Exception{
		/* LogService�́AsetDefaultMessageWriterService�̃e�X�g�Ɏg�������̂��g���܂킷 */
		LogService logService = (LogService)ServiceManagerFactory.getServiceObject("TheManager",
																	"LogServiceForWriteMethodsTst");
		MessageWriter[] writer = null;
//		writer = logService.getSystemDebugMessageWriterServices();
//		assertNotNull(writer);
//		writer = logService.getSystemInfoMessageWriterServices();
//		assertNotNull(writer);
//		writer = logService.getSystemWarnMessageWriterServices();
//		assertNotNull(writer);
//		writer = logService.getSystemErrorMessageWriterServices();
//		assertNotNull(writer);
//		writer = logService.getSystemFatalMessageWriterServices();
//		assertNotNull(writer);

	}
	
	
	
	
}
