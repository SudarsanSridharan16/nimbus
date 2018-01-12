package jp.ossc.nimbus.beans.dataset;

import junit.framework.TestCase;
//
/**
 * 
 * @author S.Teshima
 * @version 1.00 �쐬: 2008/01/22 - S.Teshima
 */

public class HeaderTest extends TestCase {

	public HeaderTest(String arg0) {
		super(arg0);
	}

	public static void main(String[] args) {
		junit.textui.TestRunner.run(HeaderTest.class);
	}

	/**
	 * �����X�L�[�}�������f�[�^�������Ȃ���̃w�b�_�𕡐�����e�X�g�B
	 * <p>
	 * �����F
	 * <ul>
	 * <li>���̃w�b�_���ƃX�L�[�}���w�肵��Header(String name, String schema)�����s����</li>
	 * <li>name: "Testheader"</li>
	 * <li>schema: ":A,java.lang.String,,,"</li>
	 * <li>cloneSchema()�����s���ă��R�[�h�̕����𐶐�����</li>
	 * </ul>
	 * �m�F�F
	 * <ul>
	 * <li>getName()�����s���āA���w�b�_�[�ƕ����w�b�_�[�̖��O��񂪓��������Ƃ��m�F</li>
	 * <li>getSchema()�����s���āA���w�b�_�[�ƕ����w�b�_�[�̃X�L�[�}��񂪓��������Ƃ��m�F</li>
	 * </ul>
	 */
	public void testCloneSchema() {
	    try{
	    	Header header = new Header("Testheader", ":A,java.lang.String,,,");
	    	Record rec = header.cloneSchema();
	    	Header header2 = (Header)rec;
	    	assertEquals("Testheader", header2.getName());
	    	assertEquals(":A,java.lang.String,,,", header2.getSchema());
    	}catch(Exception e){
    		e.printStackTrace();
    	}
	}


	/**
	 * �w�b�_����ݒ肷��e�X�g�B
	 * <p>
	 * �����F
	 * <ul>
	 * <li>���̃w�b�_���ƃX�L�[�}���w�肵��Header(String name, String schema)�����s����</li>
	 * <li>name: "Testheader"</li>
	 * <li>schema: ":A,java.lang.String,,,"</li>
	 * <li>���̃w�b�_�����w�肵��setName(String name)�����s����</li>
	 * <li>name: "TestheaderNew"</li>
	 * </ul>
	 * �m�F�F
	 * <ul>
	 * <li>getName()�����s���āA���O���"TestheaderNew"�Ɠ��������Ƃ��m�F</li>
	 * </ul>
	 */
	public void testSetName() {
	    try{
	    	Header header = new Header("Testheader", ":A,java.lang.String,,,");
	    	header.setName("TestheaderNew");
	    	assertEquals("TestheaderNew", header.getName());
    	}catch(Exception e){
    		e.printStackTrace();
    	}
	}

}
