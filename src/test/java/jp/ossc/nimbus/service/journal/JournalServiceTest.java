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
package jp.ossc.nimbus.service.journal;
import junit.framework.TestCase;
import jp.ossc.nimbus.core.*;
import jp.ossc.nimbus.lang.*;
import java.util.*;
import jp.ossc.nimbus.service.journal.editorfinder.*;
import java.text.*;

/**
 *	
 *	@author	y-tokuda
 *	@version	1.00 �쐬�F2003/11/14�| y-tokuda<BR>
 *				�X�V�F
 */
public class JournalServiceTest extends TestCase {

	/**
	 * Constructor for JournalServiceTest.
	 * @param arg0
	 */
	public JournalServiceTest(String arg0) {
		super(arg0);
		//ServiceManagerFactory.loadManager("C:/usr/local/eclipse/workspace/nimbus-1016/org/nimbus/keel/journal/nimbus-service.xml");
	}

	public static void main(String[] args) {
		junit.textui.TestRunner.run(JournalServiceTest.class);
	}
	//�P���ȃW���[�i���擾
	public void testAddInfo1() throws Exception{
		ServiceManagerFactory.loadManager("jp/ossc/nimbus/service/journal/nimbus-service.xml");
		System.out.println("testAddInfo1 start");
		Journal journal = (Journal)ServiceManagerFactory.getServiceObject("tstruts","BusinessJournal");
		journal.startJournal("start");
		String msg = "testAddInfo1";
		journal.addInfo("info1",msg);
		journal.endJournal();
		ServiceManagerFactory.unloadManager("jp/ossc/nimbus/service/journal/nimbus-service.xml");
	}

	//�P���ȃW���[�i���擾�i�����R�E��R����null)
	public void testAddInfo2() throws Exception{
		ServiceManagerFactory.loadManager("jp/ossc/nimbus/service/journal/nimbus-service.xml");
		System.out.println("testAddInfo2 start");
		Journal journal = (Journal)ServiceManagerFactory.getServiceObject("tstruts","BusinessJournal");
		Date now = new Date();
		journal.startJournal("start",now,null);
		String msg = "testAddInfo2";
		journal.addInfo("info2",msg);
		journal.endJournal();
		ServiceManagerFactory.unloadManager("jp/ossc/nimbus/service/journal/nimbus-service.xml");
	}
	

	//StartJournal�i�����R�E��R�����w��j
	public void testAddInfo3() throws Exception{
		ServiceManagerFactory.loadManager("jp/ossc/nimbus/service/journal/nimbus-service.xml");
		System.out.println("testAddInfo3 start");
		Journal journal = (Journal)ServiceManagerFactory.getServiceObject("tstruts","BusinessJournal");
		EditorFinder finder = (EditorFinder)ServiceManagerFactory.getServiceObject("tstruts","BusinessJournalDefaultEditorFinder2");
		Date now = new Date();
		journal.startJournal("start",now,finder);
		String msg = "testAddInfo3";
		journal.addInfo("info3",msg);
		journal.endJournal();
		ServiceManagerFactory.unloadManager("jp/ossc/nimbus/service/journal/nimbus-service.xml");
	}
	//�����R�E��P������null�w��
	public void testAddInfo4() throws Exception{
		ServiceManagerFactory.loadManager("jp/ossc/nimbus/service/journal/nimbus-service.xml");
		System.out.println("testAddInfo4 start");
		Journal journal = (Journal)ServiceManagerFactory.getServiceObject("tstruts","BusinessJournal");
		EditorFinder finder = (EditorFinder)ServiceManagerFactory.getServiceObject("tstruts","BusinessJournalDefaultEditorFinder");
		Date now = new Date();
		journal.startJournal(null,now,finder);
		String msg = "testAddInfo4";
		journal.addInfo("info4",msg);
		journal.endJournal();
		ServiceManagerFactory.unloadManager("jp/ossc/nimbus/service/journal/nimbus-service.xml");
	}
	//StartJournal�A���R�[��
	public void testAddInfo5() throws Exception{
		ServiceManagerFactory.loadManager("jp/ossc/nimbus/service/journal/nimbus-service.xml");
		System.out.println("testAddInfo5 start");
		Journal journal = (Journal)ServiceManagerFactory.getServiceObject("tstruts","BusinessJournal");
		journal.startJournal("start");
		journal.startJournal("start");
		String msg = "testAddInfo5";
		journal.addInfo("info5",msg);
		journal.endJournal();
		journal.endJournal();
		ServiceManagerFactory.unloadManager("jp/ossc/nimbus/service/journal/nimbus-service.xml");
	}
	
	//EndJournal�i�����w��j�R�[��
	public void testAddInfo6() throws Exception{
		ServiceManagerFactory.loadManager("jp/ossc/nimbus/service/journal/nimbus-service.xml");
		Journal journal = (Journal)ServiceManagerFactory.getServiceObject("tstruts","BusinessJournal");
		System.out.println("testAddInfo6 start");
		journal.startJournal("start");
		String msg = "testAddInfo6";
		journal.addInfo("info6",msg);
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss"); 
		Date endTime = simpleDateFormat.parse("2001.10.01 23:34:45");
		journal.endJournal(endTime);
		ServiceManagerFactory.unloadManager("jp/ossc/nimbus/service/journal/nimbus-service.xml");
	}
	//StartJournal���Ȃ���addInfo
	public void testAddInfo7() throws Exception{
		ServiceManagerFactory.loadManager("jp/ossc/nimbus/service/journal/nimbus-service.xml");
		
		Journal journal = (Journal)ServiceManagerFactory.getServiceObject("tstruts","BusinessJournal");
		System.out.println("testAddInfo7 start");
		//journal.startJournal("start");
		String msg = "testAddInfo7";
		journal.addInfo("info7",msg);
		ServiceManagerFactory.unloadManager("jp/ossc/nimbus/service/journal/nimbus-service.xml");
	}
	
	//addInfo(String,Object,EditorFinder)
	public void testAddInfo8() throws Exception{
		ServiceManagerFactory.loadManager("jp/ossc/nimbus/service/journal/nimbus-service.xml");
		EditorFinder finder = (EditorFinder)ServiceManagerFactory.getServiceObject("tstruts","BusinessJournalDefaultEditorFinder2");
		Journal journal = (Journal)ServiceManagerFactory.getServiceObject("tstruts","BusinessJournal");
		System.out.println("testAddInfo8 start");
		journal.startJournal("start");
		String msg = "testAddInfo8";
		journal.addInfo("info8",msg,finder);
		journal.endJournal();
		ServiceManagerFactory.unloadManager("jp/ossc/nimbus/service/journal/nimbus-service.xml");
	}
	
	//addInfo(String,Object,EditorFinder) startJournal���Ȃ��B
	public void testAddInfo9() throws Exception{
		ServiceManagerFactory.loadManager("jp/ossc/nimbus/service/journal/nimbus-service.xml");
		EditorFinder finder = (EditorFinder)ServiceManagerFactory.getServiceObject("tstruts","BusinessJournalDefaultEditorFinder2");
		Journal journal = (Journal)ServiceManagerFactory.getServiceObject("tstruts","BusinessJournal");
		System.out.println("testAddInfo9 start");
		//journal.startJournal("start");
		String msg = "testAddInfo9";
		journal.addInfo("info9",msg,finder);
		ServiceManagerFactory.unloadManager("jp/ossc/nimbus/service/journal/nimbus-service.xml");
		//journal.endJournal();
	}
	
	//addStartStep(String,Date,EditorFinder) startJournal���Ȃ��B
	public void testAddStartStep1() throws Exception{
		ServiceManagerFactory.loadManager("jp/ossc/nimbus/service/journal/nimbus-service.xml");
		EditorFinder finder = (EditorFinder)ServiceManagerFactory.getServiceObject("tstruts","BusinessJournalDefaultEditorFinder2");
		Journal journal = (Journal)ServiceManagerFactory.getServiceObject("tstruts","BusinessJournal");
		System.out.println("testAddStartStep1 start");
		Date now = new Date();
		//journal.startJournal("start");
		journal.addStartStep("start",now,finder);
		ServiceManagerFactory.unloadManager("jp/ossc/nimbus/service/journal/nimbus-service.xml");

	}
	
	//addStartStep(String,Date,EditorFinder) ����
	public void testAddStartStep2() throws Exception{
		ServiceManagerFactory.loadManager("jp/ossc/nimbus/service/journal/nimbus-service.xml");
		EditorFinder finder = (EditorFinder)ServiceManagerFactory.getServiceObject("tstruts","BusinessJournalDefaultEditorFinder2");
		Journal journal = (Journal)ServiceManagerFactory.getServiceObject("tstruts","BusinessJournal");
		System.out.println("testAddStartStep2 start");
		Date now = new Date();
		journal.startJournal("start");
		journal.addStartStep("start",now,finder);
		journal.endJournal();
		ServiceManagerFactory.unloadManager("jp/ossc/nimbus/service/journal/nimbus-service.xml");
		
	}
	
	//addEndStep() ����
	public void testAddEndStep1() throws Exception{
		ServiceManagerFactory.loadManager("jp/ossc/nimbus/service/journal/nimbus-service.xml");
		EditorFinder finder = (EditorFinder)ServiceManagerFactory.getServiceObject("tstruts","BusinessJournalDefaultEditorFinder2");
		Journal journal = (Journal)ServiceManagerFactory.getServiceObject("tstruts","BusinessJournal");
		System.out.println("testAddEndStep1 start");
		Date now = new Date();
		journal.startJournal("start");
		journal.addStartStep("start",now,finder);
		journal.addInfo("testAddEndStep","testAddEndStep");
		journal.addEndStep();
		journal.endJournal();
		ServiceManagerFactory.unloadManager("jp/ossc/nimbus/service/journal/nimbus-service.xml");
		
	}
	
	//addEndStep(Date) ����BaddStartStep��Step��ǉ������̂��AaddEndStep���R�[��
	public void testAddEndStep2() throws Exception{
		ServiceManagerFactory.loadManager("jp/ossc/nimbus/service/journal/nimbus-service.xml");
		EditorFinder finder = (EditorFinder)ServiceManagerFactory.getServiceObject("tstruts","BusinessJournalDefaultEditorFinder2");
		Journal journal = (Journal)ServiceManagerFactory.getServiceObject("tstruts","BusinessJournal");
		System.out.println("testAddEndStep1 start");
		Date now = new Date();
		journal.startJournal("start");
		journal.addStartStep("start",now,finder);
		journal.addInfo("testAddEndStep","testAddEndStep");
		journal.addEndStep(now);
		journal.endJournal();
		ServiceManagerFactory.unloadManager("jp/ossc/nimbus/service/journal/nimbus-service.xml");
		
	}
	
	//addEndStep(Date) ����BaddStartStep��Step��ǉ������AaddEndStep���R�[��
	public void testAddEndStep3() throws Exception{
		ServiceManagerFactory.loadManager("jp/ossc/nimbus/service/journal/nimbus-service.xml");
		Journal journal = (Journal)ServiceManagerFactory.getServiceObject("tstruts","BusinessJournal");
		System.out.println("testAddEndStep1 start");
		Date now = new Date();
		journal.startJournal("start");
		//�X�e�b�v��ǉ����Ȃ��B
		//journal.addStartStep("start",now,finder);
		journal.addInfo("testAddEndStep","testAddEndStep");
		journal.addEndStep(now);
		journal.endJournal();
		ServiceManagerFactory.unloadManager("jp/ossc/nimbus/service/journal/nimbus-service.xml");
		
	}
	//���������A�u���b�N�{�b�N�X�I�e�X�g
	public void testOutputPattern1() throws Exception{
		ServiceManagerFactory.loadManager("jp/ossc/nimbus/service/journal/nimbus-service.xml");
		//EditorFinder finder = (EditorFinder)ServiceManagerFactory.getServiceObject("tstruts","BusinessJournalDefaultEditorFinder2");
		Journal journal = (Journal)ServiceManagerFactory.getServiceObject("tstruts","BusinessJournal3");
		System.out.println("testOutputPattern1 start");
		journal.startJournal("start");
		journal.addInfo("Pattern1_1st_addInfo","1st");
		journal.startJournal("start");
		journal.addInfo("Pattern1_2nd_addInfo","2nd");
		journal.endJournal();
		journal.endJournal();
		ServiceManagerFactory.unloadManager("jp/ossc/nimbus/service/journal/nimbus-service.xml");		
	}
	
	public void testOutputPattern2() throws Exception{
		ServiceManagerFactory.loadManager("jp/ossc/nimbus/service/journal/nimbus-service.xml");
		//EditorFinder finder = (EditorFinder)ServiceManagerFactory.getServiceObject("tstruts","BusinessJournalDefaultEditorFinder2");
		Journal journal = (Journal)ServiceManagerFactory.getServiceObject("tstruts","BusinessJournal3");
		System.out.println("testOutputPattern2 start");
		journal.startJournal("start");
		journal.addInfo("Pattern2_1st_addInfo","1st");
		journal.startJournal("start");
		journal.addInfo("Pattern2_2nd_addInfo","2nd");
		journal.startJournal("start");
		journal.addInfo("Pattern2_2nd_addInfo","3rd");
		journal.endJournal();
		journal.endJournal();
		journal.endJournal();
		ServiceManagerFactory.unloadManager("jp/ossc/nimbus/service/journal/nimbus-service.xml");		
	}
	//addStartStep�ɂ�����q
	public void testOutputPattern3() throws Exception{
		ServiceManagerFactory.loadManager("jp/ossc/nimbus/service/journal/nimbus-service.xml");
		//EditorFinder finder = (EditorFinder)ServiceManagerFactory.getServiceObject("tstruts","BusinessJournalDefaultEditorFinder2");
		Journal journal = (Journal)ServiceManagerFactory.getServiceObject("tstruts","BusinessJournal3");
		System.out.println("testOutputPattern3 start");
		journal.startJournal("start");
		journal.addInfo("Pattern3_1st_addInfo","1st");
		journal.addStartStep("start");
		journal.addInfo("Pattern3_2nd_addInfo","2nd");
		journal.endJournal();
		ServiceManagerFactory.unloadManager("jp/ossc/nimbus/service/journal/nimbus-service.xml");		
	}
	//addStartStep�ɂ�����q
	public void testOutputPattern4() throws Exception{
		ServiceManagerFactory.loadManager("jp/ossc/nimbus/service/journal/nimbus-service.xml");
		//EditorFinder finder = (EditorFinder)ServiceManagerFactory.getServiceObject("tstruts","BusinessJournalDefaultEditorFinder2");
		Journal journal = (Journal)ServiceManagerFactory.getServiceObject("tstruts","BusinessJournal3");
		System.out.println("testOutputPattern4 start");
		journal.startJournal("start");
		journal.addInfo("Pattern4_1st_addInfo","P_1st");
		journal.addStartStep("start1");
		journal.addInfo("Pattern4_2nd_addInfo","P_2nd");
		journal.addStartStep("start2");
		journal.addInfo("Pattern4_3rd_addInfo","P_3rd");
		//journal.addEndStep();
		//journal.addEndStep();
		journal.endJournal();
		ServiceManagerFactory.unloadManager("jp/ossc/nimbus/service/journal/nimbus-service.xml");		
	}
	//addStartStep�ɂ�����q�i�P�i�E�q���Q�j
	public void testOutputPattern5() throws Exception{
		ServiceManagerFactory.loadManager("jp/ossc/nimbus/service/journal/nimbus-service.xml");
		//EditorFinder finder = (EditorFinder)ServiceManagerFactory.getServiceObject("tstruts","BusinessJournalDefaultEditorFinder2");
		Journal journal = (Journal)ServiceManagerFactory.getServiceObject("tstruts","BusinessJournal3");
		System.out.println("testOutputPattern5 start");
		journal.startJournal("start");
		journal.addInfo("Pattern5_1st_addInfo","Pattern5_1st");
		journal.addStartStep("start");
		journal.addInfo("Pattern5_2nd_addInfo","Pattern5_2nd");
		journal.addEndStep();
		journal.addStartStep("start");
		journal.addInfo("Pattern5_3rd_addInfo","Pattern5_3rd");
		journal.addEndStep();
		journal.endJournal();
		ServiceManagerFactory.unloadManager("jp/ossc/nimbus/service/journal/nimbus-service.xml");		
	}
	
	//addStartStep�ɂ�����q�i�P�i�ځE�q���Q�����P�ɑ��v�f����j
	public void testOutputPattern6() throws Exception{
		ServiceManagerFactory.loadManager("jp/ossc/nimbus/service/journal/nimbus-service.xml");
		//EditorFinder finder = (EditorFinder)ServiceManagerFactory.getServiceObject("tstruts","BusinessJournalDefaultEditorFinder2");
		Journal journal = (Journal)ServiceManagerFactory.getServiceObject("tstruts","BusinessJournal3");
		System.out.println("testOutputPattern6 start");
		journal.startJournal("start");
		journal.addInfo("Pattern6_1st_addInfo","Pattern6_1st");
		journal.addStartStep("start");
		journal.addInfo("Pattern6_2nd_addInfo","Pattern6_2nd");
		journal.addEndStep();
		journal.addStartStep("start");
		journal.addInfo("Pattern6_3rd_addInfo","Pattern6_3rd");
		journal.addStartStep("start");
		journal.addInfo("Pattern6_4th_addInfo","Pattern6_4th");//���v�f
		journal.addEndStep();
		journal.addEndStep();
		//journal.endJournal();
		journal.endJournal();
		ServiceManagerFactory.unloadManager("jp/ossc/nimbus/service/journal/nimbus-service.xml");		
	}
	
	//endJournal�̃R�[�����P�񂽂�Ȃ��P�[�X
	public void testOutputPattern7() throws Exception{
		ServiceManagerFactory.loadManager("jp/ossc/nimbus/service/journal/nimbus-service.xml");
		//EditorFinder finder = (EditorFinder)ServiceManagerFactory.getServiceObject("tstruts","BusinessJournalDefaultEditorFinder2");
		Journal journal = (Journal)ServiceManagerFactory.getServiceObject("tstruts","BusinessJournal3");
		System.out.println("testOutputPattern7 start");
		journal.startJournal("start");
		journal.addInfo("Pattern7_1st_addInfo","Pattern7_1st");
		journal.startJournal("start");
		journal.addInfo("Pattern7_2nd_addInfo","Pattern7_2nd");
		journal.startJournal("start");
		journal.addInfo("Pattern7_3rd_addInfo","Pattern7_3rd");
		journal.endJournal();
		journal.endJournal();
		ServiceManagerFactory.unloadManager("jp/ossc/nimbus/service/journal/nimbus-service.xml");		
	}
	
	//endJournal�̃R�[�����P�񑽂��P�[�X
	public void testOutputPattern8() throws Exception{
		try {
			ServiceManagerFactory.loadManager("jp/ossc/nimbus/service/journal/nimbus-service.xml");
			//EditorFinder finder = (EditorFinder)ServiceManagerFactory.getServiceObject("tstruts","BusinessJournalDefaultEditorFinder2");
			Journal journal = (Journal)ServiceManagerFactory.getServiceObject("tstruts","BusinessJournal3");
			System.out.println("testOutputPattern8 start");
			journal.startJournal("start");
			journal.addInfo("Pattern8_1st_addInfo","Pattern8_1st");
			journal.startJournal("start");
			journal.addInfo("Pattern8_2nd_addInfo","Pattern8_2nd");
			journal.startJournal("start");
			journal.addInfo("Pattern8_3rd_addInfo","Pattern8_3rd");
			journal.endJournal();
			journal.endJournal();
			journal.endJournal();
			journal.endJournal();
			fail();
		} catch (ServiceException e) {
		} finally {
			ServiceManagerFactory.unloadManager("jp/ossc/nimbus/service/journal/nimbus-service.xml");		
		}
		
	}
	//addEndStep�̃R�[����1�񑽂��P�[�X
	public void testOutputPattern9() throws Exception{
		ServiceManagerFactory.loadManager("jp/ossc/nimbus/service/journal/nimbus-service.xml");
		//EditorFinder finder = (EditorFinder)ServiceManagerFactory.getServiceObject("tstruts","BusinessJournalDefaultEditorFinder2");
		Journal journal = (Journal)ServiceManagerFactory.getServiceObject("tstruts","BusinessJournal3");
		System.out.println("testOutputPattern9 start");
		journal.startJournal("start");
		journal.addInfo("Pattern9_1st_addInfo","Pattern9_1st");
		journal.addStartStep("start");
		journal.addInfo("Pattern9_2nd_addInfo","Pattern9_2nd");
		journal.addEndStep();
		journal.addEndStep();
		journal.addInfo("Pattern9_3rd_addInfo","Pattern9_3rd");
		journal.endJournal();
		ServiceManagerFactory.unloadManager("jp/ossc/nimbus/service/journal/nimbus-service.xml");		
	}
	
	//setRequestId�̃e�X�g
	public void testOutputPattern10() throws Exception{
		ServiceManagerFactory.loadManager("jp/ossc/nimbus/service/journal/nimbus-service.xml");
		//EditorFinder finder = (EditorFinder)ServiceManagerFactory.getServiceObject("tstruts","BusinessJournalDefaultEditorFinder2");
		Journal journal = (Journal)ServiceManagerFactory.getServiceObject("tstruts","BusinessJournal3");
		System.out.println("testOutputPattern10 start");
		journal.startJournal("start");
		journal.setRequestId("MyRequestID");
		journal.addInfo("Pattern10_1st_addInfo","Pattern10_1st");
		journal.addStartStep("start");
		journal.addInfo("Pattern10_2nd_addInfo","Pattern10_2nd");
		journal.addEndStep();
		journal.addEndStep();
		journal.addInfo("Pattern10_3rd_addInfo","Pattern10_3rd");
		journal.endJournal();
		ServiceManagerFactory.unloadManager("jp/ossc/nimbus/service/journal/nimbus-service.xml");		
	}
	
	//setRequestId�̃e�X�g�i�ʔԃT�[�r�X���ݒ肳��Ă��Ȃ��ꍇ)
	public void testOutputPattern11() throws Exception{
		ServiceManagerFactory.loadManager("jp/ossc/nimbus/service/journal/nimbus-service.xml");
		//EditorFinder finder = (EditorFinder)ServiceManagerFactory.getServiceObject("tstruts","BusinessJournalDefaultEditorFinder2");
		Journal journal = (Journal)ServiceManagerFactory.getServiceObject("tstruts","BusinessJournal4");
		System.out.println("testOutputPattern10 start");
		journal.startJournal("start");
		journal.setRequestId("MyRequestID");
		journal.addInfo("Pattern10_1st_addInfo","Pattern10_1st");
		journal.addStartStep("start");
		journal.addInfo("Pattern10_2nd_addInfo","Pattern10_2nd");
		journal.addEndStep();
		journal.addEndStep();
		journal.addInfo("Pattern10_3rd_addInfo","Pattern10_3rd");
		journal.endJournal();
		ServiceManagerFactory.unloadManager("jp/ossc/nimbus/service/journal/nimbus-service.xml");		
	}
	//setRequestId�̃e�X�g�i�ʔԃT�[�r�X���ݒ肳��Ă��Ȃ��ꍇ)
	public void testOutputPattern12() throws Exception{
		ServiceManagerFactory.loadManager("jp/ossc/nimbus/service/journal/nimbus-service.xml");
		//EditorFinder finder = (EditorFinder)ServiceManagerFactory.getServiceObject("tstruts","BusinessJournalDefaultEditorFinder2");
		Journal journal = (Journal)ServiceManagerFactory.getServiceObject("tstruts","BusinessJournal4");
		journal.startJournal("start");
		journal.setRequestId("MyRequestID");
		journal.addInfo("Pattern10_1st_addInfo","Pattern10_1st");
		journal.addStartStep("start");
		journal.addInfo("Pattern10_2nd_addInfo","Pattern10_2nd");
		journal.startJournal("start1");
		journal.addInfo("Pattern10_3nd_addInfo","Pattern10_3nd");
		journal.endJournal() ;
		//journal.addEndStep();
		journal.addInfo("Pattern10_3rd_addInfo","Pattern10_3rd");
		journal.endJournal();
		ServiceManagerFactory.unloadManager("jp/ossc/nimbus/service/journal/nimbus-service.xml");		
	}
//	addStartStep�ɂ�����q�i�P�i�ځE�q���Q�����P�ɑ��v�f����j
	public void testOutputPattern13() throws Exception{
		ServiceManagerFactory.loadManager("jp/ossc/nimbus/service/journal/nimbus-service.xml");
		//EditorFinder finder = (EditorFinder)ServiceManagerFactory.getServiceObject("tstruts","BusinessJournalDefaultEditorFinder2");
		Journal journal = (Journal)ServiceManagerFactory.getServiceObject("tstruts","BusinessJournal3");
		System.out.println("testOutputPattern6 start");
		journal.startJournal("start");
		journal.addInfo("Pattern6_1st_addInfo","Pattern6_1st");
		journal.addStartStep("start1");
		journal.addInfo("Pattern6_2nd_addInfo","Pattern6_2nd");
		journal.startJournal("start2");
		journal.addInfo("Pattern6_1st_addInfo","Pattern6_3nd");
		journal.endJournal();
		journal.addEndStep();
		journal.endJournal();
		ServiceManagerFactory.unloadManager("jp/ossc/nimbus/service/journal/nimbus-service.xml");		
	}
}
