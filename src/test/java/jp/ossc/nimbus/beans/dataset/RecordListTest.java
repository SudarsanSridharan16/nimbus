package jp.ossc.nimbus.beans.dataset;

import java.util.*;

import junit.framework.TestCase;
//
/**
 * 
 * @author S.Teshima
 * @version 1.00 �쐬: 2008/01/22 - S.Teshima
 */

public class RecordListTest extends TestCase {

	public RecordListTest(String arg0) {
		super(arg0);
	}

    public static void main(String[] args) {
		junit.textui.TestRunner.run(RecordListTest.class);
	}

	/**
	 * ��̃��R�[�h���X�g�𐶐�����e�X�g�B
	 * <p>
	 * �����F
	 * <ul>
	 * <li>���̖��O�ƃX�L�[�}���w�肵��RecordList(String name, String schema)�����s����</li>
	 * <li>name    : "TestRecordList"</li>
	 * <li>schema  : ":A,java.lang.String,,,"</li>
	 * </ul>
	 * �m�F�F
	 * <ul>
	 * <li>����I���B��OPropertySchemaDefineException���������Ȃ����Ƃ��m�F</li>
     * <li>RecordList#getname()��"TestRecordList"��Ԃ��B</li>
     * <li>RecordList#getSchema()��":A,java.lang.String,,,"��Ԃ��B</li>
     * <li>RecordList#getRecordSchema()��RecordSchema.getInstance(�w�肵���X�L�[�})��Ԃ��B</li>
     * <li>RecordList#isEmpty()��true��Ԃ��B</li>
	 * </ul>
	 */
	public void testRecordList() {
	    try{
	    	RecordList rlist = new RecordList("TestRecordList", ":A,java.lang.String,,,");
	    	assertEquals("TestRecordList", rlist.getName());
	    	assertEquals(":A,java.lang.String,,,", rlist.getSchema());
	    	assertEquals(RecordSchema.getInstance(":A,java.lang.String,,,"), rlist.getRecordSchema());
	    	assertTrue(rlist.isEmpty());
    	}catch(PropertySchemaDefineException e){
    		e.printStackTrace();
			fail("��O����");
    	}
	}

	/**
	 * ��̃��R�[�h���X�g�𐶐�����e�X�g�B
	 * <p>
	 * �����F
	 * <ul>
	 * <li>���̖��O�ƃX�L�[�}���w�肵��RecordList(String name, String schema)�����s����</li>
	 * <li>name    : "TestRecordList"</li>
	 * <li>schema  : "A,java.lang.String,,," (��؂蕶���F���w�肵�Ȃ�)</li>
	 * </ul>
	 * �m�F�F
	 * <ul>
	 * <li>��OPropertySchemaDefineException���������邱�Ƃ��m�F</li>
	 * </ul>
	 */
	public void testRecordListInvalid() {
	    try{
	    	new RecordList("TestRecordList", "A,java.lang.String,,,");
			fail("��O���������Ȃ����߃e�X�g���s ");
		} catch (PropertySchemaDefineException e) {
		}
	}

	/**
	 * ���R�[�h��ǉ����A�擾����e�X�g�B
	 * <p>
	 * �����F
	 * <ul>
	 * <li>���̖��O�ƃX�L�[�}���w�肵��RecordList(String name, String schema)�����s����</li>
	 * <li>name    : "TestRecordList"</li>
	 * <li>schema  : ":A,java.lang.String,,,"</li>
	 * <li>RecordList#createRecord()�ŐV�������R�[�h�𐶐�����</li>
	 * <li>RecordList#addRecord(Record r)�Ń��R�[�h��ǉ�����</li>
	 * <li>RecordList#createRecord()�ŐV�������R�[�h�𐶐�����</li>
	 * <li>RecordList#addRecord(int index, Record r)�Ń��R�[�h��ǉ�����</li>
	 * </ul>
	 * �m�F�F
	 * <ul>
     * <li>RecordList#getRecord(int index)�Œǉ��������R�[�h���擾�B</li>
	 * </ul>
	 */
	public void testAddRecord() {
	    try{
	    	RecordList rlist = new RecordList("TestRecordList", ":A,java.lang.String,,,");
	    	Record rec1 = rlist.createRecord();
	    	rlist.addRecord(rec1);
	    	Record rec2 = rlist.createRecord();
	    	rlist.addRecord(1,rec2);
	    	assertEquals(rec1, rlist.getRecord(0));
	    	assertEquals(rec2, rlist.getRecord(1));
    	}catch(PropertySchemaDefineException e){
    		e.printStackTrace();
			fail("��O����");
    	}
	}

	/**
	 * �w�肳�ꂽ�C���f�b�N�X�̃��R�[�h��u��������e�X�g�B
	 * <p>
	 * �����F
	 * <ul>
	 * <li>���̖��O�ƃX�L�[�}���w�肵��RecordList(String name, String schema)�����s����</li>
	 * <li>name    : "TestRecordList"</li>
	 * <li>schema  : ":A,java.lang.String,,,"</li>
	 * <li>RecordList#createRecord()�ŐV�������R�[�h�𐶐�����</li>
	 * <li>RecordList#addRecord(int index, Record r)�Ń��R�[�h��ǉ�����</li>
	 * <li>���̃X�L�[�}���w�肵��Record(String schema)�𐶐�����</li>
	 * <li>":B,int,,," </li>
	 * <li>RecordList#setRecord(int index, Record r)�Ń��R�[�h��u��������</li>
	 * </ul>
	 * �m�F�F
	 * <ul>
	 * <li>RecordList#getRecord(int index)�Ń��R�[�h���擾���A�u���������Ă��邩�m�F</li>
	 * </ul>
	 */
	public void testSetRecord() {
	    try{
	    	RecordList rlist = new RecordList("TestRecordList", ":A,java.lang.String,,,");
	    	Record rec1 = rlist.createRecord();
	    	rlist.addRecord(0,rec1);
	    	Record rec2 = new Record(":B,int,,,");
	    	rlist.setRecord(0, rec2);
	    	assertEquals(rec2, rlist.getRecord(0));
    	}catch(PropertySchemaDefineException e){
    		e.printStackTrace();
			fail("��O����");
    	}
	}


	/**
	 * �w�肳�ꂽ���R�[�h���폜����e�X�g�B
	 * <p>
	 * �����F
	 * <ul>
	 * <li>���̖��O�ƃX�L�[�}���w�肵��RecordList(String name, String schema)�����s����</li>
	 * <li>name    : "TestRecordList"</li>
	 * <li>schema  : ":A,java.lang.String,,,"</li>
	 * <li>RecordList#createRecord()�ŐV�������R�[�h�𐶐�����</li>
	 * <li>RecordList#addRecord(Record r)�Ń��R�[�h��ǉ�����</li>
	 * <li>RecordList#removeRecord(Record r)�Ń��R�[�h�w��폜������</li>
	 * </ul>
	 * �m�F�F
	 * <ul>
     * <li>RecordList#size()��0</li>
	 * </ul>
	 */
	public void testRemoveRecordRecord() {
	    try{
	    	RecordList rlist = new RecordList("TestRecordList", ":A,java.lang.String,,,");
	    	Record rec = rlist.createRecord();
	    	rlist.addRecord(rec);
	    	rlist.removeRecord(rec);
	    	assertEquals(0, rlist.size());
    	}catch(PropertySchemaDefineException e){
    		e.printStackTrace();
			fail("��O����");
    	}
	}


	/**
	 * �w�肳�ꂽ�C���f�b�N�X�̃��R�[�h���폜����e�X�g�B
	 * <p>
	 * �����F
	 * <ul>
	 * <li>���̖��O�ƃX�L�[�}���w�肵��RecordList(String name, String schema)�����s����</li>
	 * <li>name    : "TestRecordList"</li>
	 * <li>schema  : ":A,java.lang.String,,,"</li>
	 * <li>RecordList#createRecord()�ŐV�������R�[�h�𐶐�����</li>
	 * <li>RecordList#addRecord(int index, Record r)�Ń��R�[�h��ǉ�����</li>
	 * <li>RecordList#removeRecord(int index)�Ń��R�[�h�w��폜������</li>
	 * </ul>
	 * �m�F�F
	 * <ul>
     * <li>RecordList#size()��0</li>
	 * </ul>
	 */
	public void testRemoveRecordInt() {
	    try{
	    	RecordList rlist = new RecordList("TestRecordList", ":A,java.lang.String,,,");
	    	Record rec = rlist.createRecord();
	    	rlist.addRecord(rec);
	    	rlist.removeRecord(0);
	    	assertEquals(0, rlist.size());
    	}catch(PropertySchemaDefineException e){
    		e.printStackTrace();
			fail("��O����");
    	}
	}


	/**
	 * �w�肳�ꂽ�v���p�e�B���̃v���p�e�B���\�[�g�L�[�ɂ��āA�����Ń\�[�g����e�X�g�B
	 * <p>
	 * �����F
	 * <ul>
	 * <li>���̖��O�ƃX�L�[�}���w�肵��RecordList(String name, String schema)�����s����</li>
	 * <li>name    : "TestRecordList"</li>
	 * <li>schema  : ":A,java.lang.String,,,"<BR>      ":B,java.lang.String,,,"</li>
	 * <li>RecordList#createRecord()�ŐV�������R�[�h�𐶐�����(�P�ڂ̃��R�[�h�쐬)</li>
	 * <li>�p�����[�^A�ɒl��ݒ肷��BRecord#setProperty("A", "2")�����s����</li>
	 * <li>�p�����[�^B�ɒl��ݒ肷��BRecord#setProperty("B", "2")�����s����</li>
	 * <li>RecordList#addRecord(Record r)�Ń��R�[�h��ǉ�����</li>
	 * <li>RecordList#createRecord()�ŐV�������R�[�h�𐶐�����(�Q�ڂ̃��R�[�h�쐬)</li>
	 * <li>�p�����[�^A�ɒl��ݒ肷��BRecord#setProperty("A", "3")�����s����</li>
	 * <li>�p�����[�^B�ɒl��ݒ肷��BRecord#setProperty("B", "3")�����s����</li>
	 * <li>RecordList#addRecord(Record r)�Ń��R�[�h��ǉ�����</li>
	 * <li>RecordList#createRecord()�ŐV�������R�[�h�𐶐�����(�R�ڂ̃��R�[�h�쐬)</li>
	 * <li>�p�����[�^A�ɒl��ݒ肷��BRecord#setProperty("A", "1")�����s����</li>
	 * <li>�p�����[�^B�ɒl��ݒ肷��BRecord#setProperty("B", "1")�����s����</li>
	 * <li>RecordList#addRecord(Record r)�Ń��R�[�h��ǉ�����</li>
	 * <li>RecordList#sort(String[] orderBy)�Ńv���p�e�B��"A"���w�肵�ă\�[�g����</li>
	 * </ul>
	 * �m�F�F
	 * <ul>
     * <li>Record���v���p�e�BA�̒l�ŏ����Ɋi�[����Ă��邱�Ƃ��m�F����</li>
	 * </ul>
	 */
	public void testSortStringArray() {
	    try{
	    	RecordList rlist = new RecordList("TestRecordList", ":A,java.lang.String,,,\n" +
	    			":B,java.lang.String,,,");
	    	
	    	Record rec1 = rlist.createRecord();
	    	rec1.setProperty("A", "2");
	    	rec1.setProperty("B", "2");
	    	rlist.addRecord(rec1);

	    	Record rec2 = rlist.createRecord();
	    	rec2.setProperty("A", "3");
	    	rec2.setProperty("B", "3");
	    	rlist.addRecord(rec2);

	    	Record rec3 = rlist.createRecord();
	    	rec3.setProperty("A", "1");
	    	rec3.setProperty("B", "1");
	    	rlist.addRecord(rec3);
	    	
	    	String skey[] = {"A"};
	    	rlist.sort(skey);

	    	assertEquals(rec3, rlist.getRecord(0));
	    	assertEquals(rec1, rlist.getRecord(1));
	    	assertEquals(rec2, rlist.getRecord(2));
    	}catch(PropertySchemaDefineException e){
    		e.printStackTrace();
			fail("��O����");
    	}
	}


	/**
	 * �w�肳�ꂽ�v���p�e�B�C���f�b�N�X�̃v���p�e�B���\�[�g�L�[�ɂ��āA�����Ń\�[�g����e�X�g�B
	 * <p>
	 * �����F
	 * <ul>
	 * <li>���̖��O�ƃX�L�[�}���w�肵��RecordList(String name, String schema)�����s����</li>
	 * <li>name    : "TestRecordList"</li>
	 * <li>schema  : ":A,java.lang.String,,,"<BR>      ":B,java.lang.String,,,"</li>
	 * <li>RecordList#createRecord()�ŐV�������R�[�h�𐶐�����(�P�ڂ̃��R�[�h�쐬)</li>
	 * <li>�p�����[�^A�ɒl��ݒ肷��BRecord#setProperty("A", "2")�����s����</li>
	 * <li>�p�����[�^B�ɒl��ݒ肷��BRecord#setProperty("B", "2")�����s����</li>
	 * <li>RecordList#addRecord(Record r)�Ń��R�[�h��ǉ�����</li>
	 * <li>RecordList#createRecord()�ŐV�������R�[�h�𐶐�����(�Q�ڂ̃��R�[�h�쐬)</li>
	 * <li>�p�����[�^A�ɒl��ݒ肷��BRecord#setProperty("A", "3")�����s����</li>
	 * <li>�p�����[�^B�ɒl��ݒ肷��BRecord#setProperty("B", "3")�����s����</li>
	 * <li>RecordList#addRecord(Record r)�Ń��R�[�h��ǉ�����</li>
	 * <li>RecordList#createRecord()�ŐV�������R�[�h�𐶐�����(�R�ڂ̃��R�[�h�쐬)</li>
	 * <li>�p�����[�^A�ɒl��ݒ肷��BRecord#setProperty("A", "1")�����s����</li>
	 * <li>�p�����[�^B�ɒl��ݒ肷��BRecord#setProperty("B", "1")�����s����</li>
	 * <li>RecordList#addRecord(Record r)�Ń��R�[�h��ǉ�����</li>
	 * <li>RecordList#sort(int[] orderBy)�Ńv���p�e�B��"A"��index(0)���w�肵�ă\�[�g����</li>
	 * </ul>
	 * �m�F�F
	 * <ul>
     * <li>Record���v���p�e�BA�̒l�ŏ����Ɋi�[����Ă��邱�Ƃ��m�F����</li>
	 * </ul>
	 */
	public void testSortIntArray() {
	    try{
	    	RecordList rlist = new RecordList("TestRecordList", ":A,java.lang.String,,,\n" +
	    			":B,java.lang.String,,,");
	    	
	    	Record rec1 = rlist.createRecord();
	    	rec1.setProperty("A", "2");
	    	rec1.setProperty("B", "2");
	    	rlist.addRecord(rec1);

	    	Record rec2 = rlist.createRecord();
	    	rec2.setProperty("A", "3");
	    	rec2.setProperty("B", "3");
	    	rlist.addRecord(rec2);

	    	Record rec3 = rlist.createRecord();
	    	rec3.setProperty("A", "1");
	    	rec3.setProperty("B", "1");
	    	rlist.addRecord(rec3);
	    	
	    	int skey[] = {0};
	    	rlist.sort(skey);

	    	assertEquals(rec3, rlist.getRecord(0));
	    	assertEquals(rec1, rlist.getRecord(1));
	    	assertEquals(rec2, rlist.getRecord(2));
    	}catch(PropertySchemaDefineException e){
    		e.printStackTrace();
			fail("��O����");
    	}
	}


	/**
	 * �w�肳�ꂽ�v���p�e�B�C���f�b�N�X�̃v���p�e�B���\�[�g�L�[�ɂ��ă\�[�g����e�X�g�B
	 * <p>
	 * �����F
	 * <ul>
	 * <li>���̖��O�ƃX�L�[�}���w�肵��RecordList(String name, String schema)�����s����</li>
	 * <li>name    : "TestRecordList"</li>
	 * <li>schema  : ":A,java.lang.String,,,"<BR>      ":B,java.lang.String,,,"</li>
	 * <li>RecordList#createRecord()�ŐV�������R�[�h�𐶐�����(�P�ڂ̃��R�[�h�쐬)</li>
	 * <li>�p�����[�^A�ɒl��ݒ肷��BRecord#setProperty("A", "1")�����s����</li>
	 * <li>�p�����[�^B�ɒl��ݒ肷��BRecord#setProperty("B", "1")�����s����</li>
	 * <li>RecordList#addRecord(Record r)�Ń��R�[�h��ǉ�����</li>
	 * <li>RecordList#createRecord()�ŐV�������R�[�h�𐶐�����(�Q�ڂ̃��R�[�h�쐬)</li>
	 * <li>�p�����[�^A�ɒl��ݒ肷��BRecord#setProperty("A", "1")�����s����</li>
	 * <li>�p�����[�^B�ɒl��ݒ肷��BRecord#setProperty("B", "2")�����s����</li>
	 * <li>RecordList#addRecord(Record r)�Ń��R�[�h��ǉ�����</li>
	 * <li>RecordList#createRecord()�ŐV�������R�[�h�𐶐�����(�R�ڂ̃��R�[�h�쐬)</li>
	 * <li>�p�����[�^A�ɒl��ݒ肷��BRecord#setProperty("A", "2")�����s����</li>
	 * <li>�p�����[�^B�ɒl��ݒ肷��BRecord#setProperty("B", "1")�����s����</li>
	 * <li>RecordList#addRecord(Record r)�Ń��R�[�h��ǉ�����</li>
	 * <li>RecordList#createRecord()�ŐV�������R�[�h�𐶐�����(�S�ڂ̃��R�[�h�쐬)</li>
	 * <li>�p�����[�^A�ɒl��ݒ肷��BRecord#setProperty("A", "2")�����s����</li>
	 * <li>�p�����[�^B�ɒl��ݒ肷��BRecord#setProperty("B", "2")�����s����</li>
	 * <li>RecordList#addRecord(Record r)�Ń��R�[�h��ǉ�����</li>
	 * <li>RecordList#createRecord()�ŐV�������R�[�h�𐶐�����(�T�ڂ̃��R�[�h�쐬)</li>
	 * <li>�p�����[�^A�ɒl��ݒ肷��BRecord#setProperty("A", "3")�����s����</li>
	 * <li>�p�����[�^B�ɒl��ݒ肷��BRecord#setProperty("B", "1")�����s����</li>
	 * <li>RecordList#addRecord(Record r)�Ń��R�[�h��ǉ�����</li>
	 * <li>RecordList#createRecord()�ŐV�������R�[�h�𐶐�����(�U�ڂ̃��R�[�h�쐬)</li>
	 * <li>�p�����[�^A�ɒl��ݒ肷��BRecord#setProperty("A", "3")�����s����</li>
	 * <li>�p�����[�^B�ɒl��ݒ肷��BRecord#setProperty("B", "2")�����s����</li>
	 * <li>RecordList#addRecord(Record r)�Ń��R�[�h��ǉ�����</li>
	 * <li>RecordList#sort(int[] orderBy, boolean[] isAsc)�Ńv���p�e�B��"A"���~���A<BR>
	 * �v���p�e�B��"B"�������ɂȂ�w������ă\�[�g����</li>
	 * </ul>
	 * �m�F�F
	 * <ul>
     * <li>Record���w�肵���ʂ�Ƀ\�[�g����Ċi�[����Ă��邱�Ƃ��m�F����</li>
	 * </ul>
	 */
	public void testSortIntArrayBooleanArray() {
	    try{
	    	RecordList rlist = new RecordList("TestRecordList", ":A,java.lang.String,,,\n" +
	    			":B,java.lang.String,,,");
	    	
	    	Record rec1 = rlist.createRecord();
	    	rec1.setProperty("A", "1");
	    	rec1.setProperty("B", "1");
	    	rlist.addRecord(rec1);

	    	Record rec2 = rlist.createRecord();
	    	rec2.setProperty("A", "1");
	    	rec2.setProperty("B", "2");
	    	rlist.addRecord(rec2);

	    	Record rec3 = rlist.createRecord();
	    	rec3.setProperty("A", "2");
	    	rec3.setProperty("B", "1");
	    	rlist.addRecord(rec3);
	    	
	    	Record rec4 = rlist.createRecord();
	    	rec4.setProperty("A", "2");
	    	rec4.setProperty("B", "2");
	    	rlist.addRecord(rec4);

	    	Record rec5 = rlist.createRecord();
	    	rec5.setProperty("A", "3");
	    	rec5.setProperty("B", "1");
	    	rlist.addRecord(rec5);

	    	Record rec6 = rlist.createRecord();
	    	rec6.setProperty("A", "3");
	    	rec6.setProperty("B", "2");
	    	rlist.addRecord(rec6);
	    	
	    	int skey[] = {0,1};
	    	boolean asc[] = {false,true};
	    	rlist.sort(skey,asc);

	    	assertEquals(rec5, rlist.getRecord(0));
	    	assertEquals(rec6, rlist.getRecord(1));
	    	assertEquals(rec3, rlist.getRecord(2));
	    	assertEquals(rec4, rlist.getRecord(3));
	    	assertEquals(rec1, rlist.getRecord(4));
	    	assertEquals(rec2, rlist.getRecord(5));
    	}catch(PropertySchemaDefineException e){
    		e.printStackTrace();
			fail("��O����");
    	}
	}


	/**
	 * �w�肳�ꂽ�v���p�e�B�C���f�b�N�X�̃v���p�e�B���\�[�g�L�[�ɂ��ă\�[�g����e�X�g�B
	 * <p>
	 * �����F
	 * <ul>
	 * <li>���̖��O�ƃX�L�[�}���w�肵��RecordList(String name, String schema)�����s����</li>
	 * <li>name    : "TestRecordList"</li>
	 * <li>schema  : ":A,java.lang.String,,,"<BR>      ":B,java.lang.String,,,"</li>
	 * <li>RecordList#createRecord()�ŐV�������R�[�h�𐶐�����(�P�ڂ̃��R�[�h�쐬)</li>
	 * <li>�p�����[�^A�ɒl��ݒ肷��BRecord#setProperty("A", "1")�����s����</li>
	 * <li>�p�����[�^B�ɒl��ݒ肷��BRecord#setProperty("B", "1")�����s����</li>
	 * <li>RecordList#addRecord(Record r)�Ń��R�[�h��ǉ�����</li>
	 * <li>RecordList#sort(int[] orderBy, boolean[] isAsc)�Ńv���p�e�B��"A"���~���A<BR>
	 * �v���p�e�B��"B"�������ɂȂ�w������ă\�[�g����</li>
	 * </ul>
	 * �m�F�F
	 * <ul>
     * <li>����I�����邱�Ƃ��m�F</li>
	 * </ul>
	 */
	public void testSortIntArrayBooleanArrayOneValue() {
	    try{
	    	RecordList rlist = new RecordList("TestRecordList", ":A,java.lang.String,,,\n" +
	    			":B,java.lang.String,,,");
	    	
	    	Record rec1 = rlist.createRecord();
	    	rec1.setProperty("A", "1");
	    	rec1.setProperty("B", "1");
	    	rlist.addRecord(rec1);

	    	
	    	int skey[] = {0,1};
	    	boolean asc[] = {false,true};
	    	rlist.sort(skey,asc);

    	}catch(PropertySchemaDefineException e){
    		e.printStackTrace();
			fail("��O����");
    	}
	}



	/**
	 * �w�肳�ꂽ�v���p�e�B�C���f�b�N�X�̃v���p�e�B���\�[�g�L�[�ɂ��ă\�[�g����e�X�g�B
	 * <p>
	 * �����F
	 * <ul>
	 * <li>���̖��O�ƃX�L�[�}���w�肵��RecordList(String name, String schema)�����s����</li>
	 * <li>name    : "TestRecordList"</li>
	 * <li>schema  : ":A,java.lang.String,,,"<BR>      ":B,java.lang.String,,,"</li>
	 * <li>RecordList#createRecord()�ŐV�������R�[�h�𐶐�����(�P�ڂ̃��R�[�h�쐬)</li>
	 * <li>�p�����[�^A�ɒl��ݒ肷��BRecord#setProperty("A", "1")�����s����</li>
	 * <li>�p�����[�^B�ɒl��ݒ肷��BRecord#setProperty("B", "1")�����s����</li>
	 * <li>RecordList#addRecord(Record r)�Ń��R�[�h��ǉ�����</li>
	 * <li>RecordList#createRecord()�ŐV�������R�[�h�𐶐�����(�Q�ڂ̃��R�[�h�쐬)</li>
	 * <li>�p�����[�^A�ɒl��ݒ肷��BRecord#setProperty("A", "1")�����s����</li>
	 * <li>�p�����[�^B�ɒl��ݒ肷��BRecord#setProperty("B", "2")�����s����</li>
	 * <li>RecordList#addRecord(Record r)�Ń��R�[�h��ǉ�����</li>
	 * <li>RecordList#createRecord()�ŐV�������R�[�h�𐶐�����(�R�ڂ̃��R�[�h�쐬)</li>
	 * <li>�p�����[�^A�ɒl��ݒ肷��BRecord#setProperty("A", "2")�����s����</li>
	 * <li>�p�����[�^B�ɒl��ݒ肷��BRecord#setProperty("B", "1")�����s����</li>
	 * <li>RecordList#addRecord(Record r)�Ń��R�[�h��ǉ�����</li>
	 * <li>RecordList#createRecord()�ŐV�������R�[�h�𐶐�����(�S�ڂ̃��R�[�h�쐬)</li>
	 * <li>�p�����[�^A�ɒl��ݒ肷��BRecord#setProperty("A", "2")�����s����</li>
	 * <li>�p�����[�^B�ɒl��ݒ肷��BRecord#setProperty("B", "2")�����s����</li>
	 * <li>RecordList#addRecord(Record r)�Ń��R�[�h��ǉ�����</li>
	 * <li>RecordList#createRecord()�ŐV�������R�[�h�𐶐�����(�T�ڂ̃��R�[�h�쐬)</li>
	 * <li>�p�����[�^A�ɒl��ݒ肷��BRecord#setProperty("A", "3")�����s����</li>
	 * <li>�p�����[�^B�ɒl��ݒ肷��BRecord#setProperty("B", "1")�����s����</li>
	 * <li>RecordList#addRecord(Record r)�Ń��R�[�h��ǉ�����</li>
	 * <li>RecordList#createRecord()�ŐV�������R�[�h�𐶐�����(�U�ڂ̃��R�[�h�쐬)</li>
	 * <li>�p�����[�^A�ɒl��ݒ肷��BRecord#setProperty("A", "3")�����s����</li>
	 * <li>�p�����[�^B�ɒl��ݒ肷��BRecord#setProperty("B", "2")�����s����</li>
	 * <li>RecordList#addRecord(Record r)�Ń��R�[�h��ǉ�����</li>
	 * <li>RecordList#sort(String[] orderBy, boolean[] isAsc)�Ńv���p�e�B��"A"���~���A<BR>
	 * �v���p�e�B��"B"�������ɂȂ�w������ă\�[�g����</li>
	 * </ul>
	 * �m�F�F
	 * <ul>
     * <li>Record���w�肵���ʂ�Ƀ\�[�g����Ċi�[����Ă��邱�Ƃ��m�F����</li>
	 * </ul>
	 */
	public void testSortStringArrayBooleanArray() {
	    try{
	    	RecordList rlist = new RecordList("TestRecordList", ":A,java.lang.String,,,\n" +
	    			":B,java.lang.String,,,");
	    	
	    	Record rec1 = rlist.createRecord();
	    	rec1.setProperty("A", "1");
	    	rec1.setProperty("B", "1");
	    	rlist.addRecord(rec1);

	    	Record rec2 = rlist.createRecord();
	    	rec2.setProperty("A", "1");
	    	rec2.setProperty("B", "2");
	    	rlist.addRecord(rec2);

	    	Record rec3 = rlist.createRecord();
	    	rec3.setProperty("A", "2");
	    	rec3.setProperty("B", "1");
	    	rlist.addRecord(rec3);
	    	
	    	Record rec4 = rlist.createRecord();
	    	rec4.setProperty("A", "2");
	    	rec4.setProperty("B", "2");
	    	rlist.addRecord(rec4);

	    	Record rec5 = rlist.createRecord();
	    	rec5.setProperty("A", "3");
	    	rec5.setProperty("B", "1");
	    	rlist.addRecord(rec5);

	    	Record rec6 = rlist.createRecord();
	    	rec6.setProperty("A", "3");
	    	rec6.setProperty("B", "2");
	    	rlist.addRecord(rec6);
	    	
	    	String skey[] = {"A","B"};
	    	boolean asc[] = {false,true};
	    	rlist.sort(skey,asc);

	    	assertEquals(rec5, rlist.getRecord(0));
	    	assertEquals(rec6, rlist.getRecord(1));
	    	assertEquals(rec3, rlist.getRecord(2));
	    	assertEquals(rec4, rlist.getRecord(3));
	    	assertEquals(rec1, rlist.getRecord(4));
	    	assertEquals(rec2, rlist.getRecord(5));
    	}catch(PropertySchemaDefineException e){
    		e.printStackTrace();
			fail("��O����");
    	}
	}

	/**
	 * �w�肳�ꂽ�v���p�e�B�C���f�b�N�X�̃v���p�e�B���\�[�g�L�[�ɂ��ă\�[�g����e�X�g�B
	 * <p>
	 * �����F
	 * <ul>
	 * <li>���̖��O�ƃX�L�[�}���w�肵��RecordList(String name, String schema)�����s����</li>
	 * <li>name    : "TestRecordList"</li>
	 * <li>schema  : ":A,java.lang.String,,,"<BR>      ":B,java.lang.String,,,"</li>
	 * <li>RecordList#createRecord()�ŐV�������R�[�h�𐶐�����(�P�ڂ̃��R�[�h�쐬)</li>
	 * <li>�p�����[�^A�ɒl��ݒ肷��BRecord#setProperty("A", "1")�����s����</li>
	 * <li>�p�����[�^B�ɒl��ݒ肷��BRecord#setProperty("B", "1")�����s����</li>
	 * <li>RecordList#addRecord(Record r)�Ń��R�[�h��ǉ�����</li>
	 * <li>RecordList#sort(String[] orderBy, boolean[] isAsc)�Ńv���p�e�B��"A"���~���A<BR>
	 * �v���p�e�B��"B"�������ɂȂ�w������ă\�[�g����</li>
	 * </ul>
	 * �m�F�F
	 * <ul>
     * <li>����I�����邱�Ƃ��m�F</li>
	 * </ul>
	 */
	public void testSortStringArrayBooleanArrayOneValue() {
	    try{
	    	RecordList rlist = new RecordList("TestRecordList", ":A,java.lang.String,,,\n" +
	    			":B,java.lang.String,,,");
	    	
	    	Record rec1 = rlist.createRecord();
	    	rec1.setProperty("A", "1");
	    	rec1.setProperty("B", "1");
	    	rlist.addRecord(rec1);
	    	
	    	String skey[] = {"A","B"};
	    	boolean asc[] = {false,true};
	    	rlist.sort(skey,asc);

    	}catch(PropertySchemaDefineException e){
    		e.printStackTrace();
			fail("��O����");
    	}
	}

	/**
	 * boolean contains(Object o)�̃e�X�g�B
	 * <p>
	 * �����F
	 * <ul>
	 * <li>���̖��O�ƃX�L�[�}���w�肵��RecordList(String name, String schema)�����s����</li>
	 * <li>name    : "TestRecordList"</li>
	 * <li>schema  : ":A,java.lang.String,,,"<BR>      ":B,java.lang.String,,,"</li>
	 * <li>RecordList#createRecord()�ŐV�������R�[�h�𐶐�����(�P�ڂ̃��R�[�h�쐬)</li>
	 * <li>�p�����[�^A�ɒl��ݒ肷��BRecord#setProperty("A", "1")�����s����</li>
	 * <li>�p�����[�^B�ɒl��ݒ肷��BRecord#setProperty("B", "1")�����s����</li>
	 * <li>RecordList#addRecord(Record r)�Ń��R�[�h��ǉ�����</li>
	 * <li>RecordList#createRecord()�ŐV�������R�[�h�𐶐�����(�Q�ڂ̃��R�[�h�쐬)</li>
	 * <li>�p�����[�^A�ɒl��ݒ肷��BRecord#setProperty("A", "2")�����s����</li>
	 * <li>�p�����[�^B�ɒl��ݒ肷��BRecord#setProperty("B", "2")�����s����</li>
	 * <li>RecordList#addRecord(Record r)�Ń��R�[�h��ǉ�����</li>
	 * <li>RecordList#createRecord()�ŐV�������R�[�h�𐶐�����(�R�ڂ̃��R�[�h�쐬)</li>
	 * <li>�p�����[�^A�ɒl��ݒ肷��BRecord#setProperty("A", "3")�����s����</li>
	 * <li>�p�����[�^B�ɒl��ݒ肷��BRecord#setProperty("B", "3")�����s����</li>
	 * <li>RecordList#addRecord(Record r)�Ń��R�[�h��ǉ�����</li>
	 * <li>�e���R�[�h���w�肵��RecordList#contains(Object o)�����s����</li>
	 * </ul>
	 * �m�F�F
	 * <ul>
     * <li>true���Ԃ��Ă��邱�Ƃ��m�F</li>
	 * </ul>
	 */
	public void testContains() {
	    try{
	    	RecordList rlist = new RecordList("TestRecordList", ":A,java.lang.String,,,\n" +
	    			":B,java.lang.String,,,");
	    	
	    	Record rec1 = rlist.createRecord();
	    	rec1.setProperty("A", "1");
	    	rec1.setProperty("B", "1");
	    	rlist.addRecord(rec1);

	    	Record rec2 = rlist.createRecord();
	    	rec2.setProperty("A", "2");
	    	rec2.setProperty("B", "2");
	    	rlist.addRecord(rec2);

	    	Record rec3 = rlist.createRecord();
	    	rec3.setProperty("A", "3");
	    	rec3.setProperty("B", "3");
	    	rlist.addRecord(rec3);
	    	
	    	assertTrue(rlist.contains(rec1));
	    	assertTrue(rlist.contains(rec2));
	    	assertTrue(rlist.contains(rec3));
    	}catch(PropertySchemaDefineException e){
    		e.printStackTrace();
			fail("��O����");
    	}
	}


	/**
	 * Map�����̃e�X�g�B
	 * <p>
	 * �����F
	 * <ul>
	 * <li>���̖��O�ƃX�L�[�}���w�肵��RecordList(String name, String schema)�����s����</li>
	 * <li>name    : "TestRecordList"</li>
	 * <li>schema  : ":A,java.lang.String,,,"<BR>      ":B,java.lang.String,,,"</li>
	 * <li>RecordList#createRecord()�ŐV�������R�[�h�𐶐�����(�P�ڂ̃��R�[�h�쐬)</li>
	 * <li>�p�����[�^A�ɒl��ݒ肷��BRecord#setProperty("A", "1")�����s����</li>
	 * <li>�p�����[�^B�ɒl��ݒ肷��BRecord#setProperty("B", "1")�����s����</li>
	 * <li>RecordList#addRecord(Record r)�Ń��R�[�h��ǉ�����</li>
	 * <li>RecordList#createRecord()�ŐV�������R�[�h�𐶐�����(�Q�ڂ̃��R�[�h�쐬)</li>
	 * <li>�p�����[�^A�ɒl��ݒ肷��BRecord#setProperty("A", "2")�����s����</li>
	 * <li>�p�����[�^B�ɒl��ݒ肷��BRecord#setProperty("B", "2")�����s����</li>
	 * <li>RecordList#addRecord(Record r)�Ń��R�[�h��ǉ�����</li>
	 * <li>RecordList#createRecord()�ŐV�������R�[�h�𐶐�����(�R�ڂ̃��R�[�h�쐬)</li>
	 * <li>�p�����[�^A�ɒl��ݒ肷��BRecord#setProperty("A", "3")�����s����</li>
	 * <li>�p�����[�^B�ɒl��ݒ肷��BRecord#setProperty("B", "3")�����s����</li>
	 * <li>RecordList#addRecord(Record r)�Ń��R�[�h��ǉ�����</li>
	 * </ul>
	 * �m�F�F
	 * <ul>
     * <li>subList(int fromIndex, int toIndex)�̕Ԃ�l��records.subList(fromIndex, toIndex)</li>
     * <li>Object[] toArray()�̕Ԃ�l��records.toArray()</li>
     * <li>indexOf(Object o)�̕Ԃ�l��records.indexOf(o)</li>
     * <li>lastIndexOf(Object o)�̕Ԃ�l��records.lastIndexOf(o)</li>
	 * </ul>
	 */
	public void testMap() {
	    try{
	    	RecordList rlist = new RecordList("TestRecordList", ":A,java.lang.String,,,\n" +
	    			":B,java.lang.String,,,");
	    	
	    	Record rec1 = rlist.createRecord();
	    	rec1.setProperty("A", "1");
	    	rec1.setProperty("B", "1");
	    	rlist.addRecord(rec1);

	    	Record rec2 = rlist.createRecord();
	    	rec2.setProperty("A", "2");
	    	rec2.setProperty("B", "2");
	    	rlist.addRecord(rec2);

	    	Record rec3 = rlist.createRecord();
	    	rec3.setProperty("A", "3");
	    	rec3.setProperty("B", "3");
	    	rlist.addRecord(rec3);
	    	
	    	assertEquals(rlist.subList(0, 1).get(0), rlist.records.subList(0, 1).get(0));
	    	assertEquals(rlist.toArray()[0], rlist.records.toArray()[0]);
	    	assertEquals(rlist.indexOf(rec1), rlist.records.indexOf(rec1));
	    	assertEquals(rlist.lastIndexOf(rec1), rlist.records.lastIndexOf(rec1));
    	}catch(PropertySchemaDefineException e){
    		e.printStackTrace();
			fail("��O����");
    	}
	}

	/**
	 * add(Object o)�̃e�X�g�B
	 * <p>
	 * �����F
	 * <ul>
	 * <li>���̖��O�ƃX�L�[�}���w�肵��RecordList(String name, String schema)�����s����</li>
	 * <li>name    : "TestRecordList"</li>
	 * <li>schema  : ":A,java.lang.String,,,"<BR>      ":B,java.lang.String,,,"</li>
	 * <li>RecordList#createRecord()�ŐV�������R�[�h�𐶐�����(�P�ڂ̃��R�[�h�쐬)</li>
	 * <li>�p�����[�^A�ɒl��ݒ肷��BRecord#setProperty("A", "1")�����s����</li>
	 * <li>�p�����[�^B�ɒl��ݒ肷��BRecord#setProperty("B", "1")�����s����</li>
	 * <li>RecordList#add(Object o)�Ń��R�[�h��ǉ�����</li>
	 * </ul>
	 * �m�F�F
	 * <ul>
     * <li>true���Ԃ��Ă��邱�Ƃ��m�F����</li>
     * <li>Object get(int index)�̕Ԃ�l��records.get(index)�Ɠ������Ƃ��m�F����</li>
	 * </ul>
	 */
	public void testAdd() {
	    try{
	    	RecordList rlist = new RecordList("TestRecordList", ":A,java.lang.String,,,\n" +
	    			":B,java.lang.String,,,");
	    	
	    	Record rec1 = rlist.createRecord();
	    	rec1.setProperty("A", "1");
	    	rec1.setProperty("B", "1");
	    	
	    	assertTrue(rlist.add(rec1));
	    	assertEquals(rlist.get(0), rlist.records.get(0));
    	}catch(PropertySchemaDefineException e){
    		e.printStackTrace();
			fail("��O����");
    	}
	}


	/**
	 * add(Object o)�̃e�X�g�B
	 * <p>
	 * �����F
	 * <ul>
	 * <li>���̖��O�ƃX�L�[�}���w�肵��RecordList(String name, String schema)�����s����</li>
	 * <li>name    : "TestRecordList"</li>
	 * <li>schema  : ":A,java.lang.String,,,"<BR>      ":B,java.lang.String,,,"</li>
	 * <li>RecordList#add(null)�Ń��R�[�h��ǉ�����</li>
	 * </ul>
	 * �m�F�F
	 * <ul>
     * <li>false���Ԃ��Ă��邱�Ƃ��m�F����</li>
	 * </ul>
	 */
	public void testAddNull() {
	    try{
	    	RecordList rlist = new RecordList("TestRecordList", ":A,java.lang.String,,,\n" +
	    			":B,java.lang.String,,,");
	    	assertFalse(rlist.add(null));
    	}catch(PropertySchemaDefineException e){
    		e.printStackTrace();
			fail("��O����");
    	}
	}


	/**
	 * add(Object o)�̃e�X�g�B
	 * <p>
	 * �����F
	 * <ul>
	 * <li>���̖��O�ƃX�L�[�}���w�肵��RecordList(String name, String schema)�����s����</li>
	 * <li>name    : "TestRecordList"</li>
	 * <li>schema  : ":A,java.lang.String,,,"<BR>      ":B,java.lang.String,,,"</li>
	 * <li>RecordList#add(Record�^�ȊO)�Ń��R�[�h��ǉ�����</li>
	 * </ul>
	 * �m�F�F
	 * <ul>
     * <li>DataSetException("Not record : " + o)���Ԃ��Ă��邱�Ƃ��m�F����</li>
	 * </ul>
	 */
	public void testAddInvalid() {
	    try{
	    	RecordList rlist = new RecordList("TestRecordList", ":A,java.lang.String,,,\n" +
	    			":B,java.lang.String,,,");
	    	assertFalse(rlist.add("ABC"));
			fail("��O���������Ȃ����߃e�X�g���s ");
    	}catch(DataSetException e){
			assertEquals("Not record : ABC", e.getMessage());
    	}
	}


	/**
	 * add(int index, Object o)�̃e�X�g�B
	 * <p>
	 * �����F
	 * <ul>
	 * <li>���̖��O�ƃX�L�[�}���w�肵��RecordList(String name, String schema)�����s����</li>
	 * <li>name    : "TestRecordList"</li>
	 * <li>schema  : ":A,java.lang.String,,,"<BR>      ":B,java.lang.String,,,"</li>
	 * <li>RecordList#createRecord()�ŐV�������R�[�h�𐶐�����(�P�ڂ̃��R�[�h�쐬)</li>
	 * <li>�p�����[�^A�ɒl��ݒ肷��BRecord#setProperty("A", "1")�����s����</li>
	 * <li>�p�����[�^B�ɒl��ݒ肷��BRecord#setProperty("B", "1")�����s����</li>
	 * <li>RecordList#add(int index,Object o)�Ń��R�[�h��ǉ�����</li>
	 * </ul>
	 * �m�F�F
	 * <ul>
     * <li>Object get(int index)�̕Ԃ�l��records.get(index)�Ɠ������Ƃ��m�F����</li>
	 * </ul>
	 */
	public void testAddIndex() {
	    try{
	    	RecordList rlist = new RecordList("TestRecordList", ":A,java.lang.String,,,\n" +
	    			":B,java.lang.String,,,");
	    	
	    	Record rec1 = rlist.createRecord();
	    	rec1.setProperty("A", "1");
	    	rec1.setProperty("B", "1");
	    	
	    	rlist.add(0, rec1);
	    	assertEquals(rlist.get(0), rlist.records.get(0));
    	}catch(PropertySchemaDefineException e){
    		e.printStackTrace();
			fail("��O����");
    	}
	}


	/**
	 * add(int index,Object o)�̃e�X�g�B
	 * <p>
	 * �����F
	 * <ul>
	 * <li>���̖��O�ƃX�L�[�}���w�肵��RecordList(String name, String schema)�����s����</li>
	 * <li>name    : "TestRecordList"</li>
	 * <li>schema  : ":A,java.lang.String,,,"<BR>      ":B,java.lang.String,,,"</li>
	 * <li>RecordList#add(int index, null)�Ń��R�[�h��ǉ�����</li>
	 * </ul>
	 * �m�F�F
	 * <ul>
     * <li>����I�����邱�Ƃ��m�F����</li>
	 * </ul>
	 */
	public void testAddIndexNull() {
	    try{
	    	RecordList rlist = new RecordList("TestRecordList", ":A,java.lang.String,,,\n" +
	    			":B,java.lang.String,,,");
	    	rlist.add(0,null);
    	}catch(PropertySchemaDefineException e){
    		e.printStackTrace();
			fail("��O����");
    	}
	}


	/**
	 * add(int index, Object o)�̃e�X�g�B
	 * <p>
	 * �����F
	 * <ul>
	 * <li>���̖��O�ƃX�L�[�}���w�肵��RecordList(String name, String schema)�����s����</li>
	 * <li>name    : "TestRecordList"</li>
	 * <li>schema  : ":A,java.lang.String,,,"<BR>      ":B,java.lang.String,,,"</li>
	 * <li>RecordList#add(int index, Record�^�ȊO)�Ń��R�[�h��ǉ�����</li>
	 * </ul>
	 * �m�F�F
	 * <ul>
     * <li>DataSetException("Not record : " + o)���Ԃ��Ă��邱�Ƃ��m�F����</li>
	 * </ul>
	 */
	public void testAddIndexInvalid() {
	    try{
	    	RecordList rlist = new RecordList("TestRecordList", ":A,java.lang.String,,,\n" +
	    			":B,java.lang.String,,,");
	    	rlist.add(0, "ABC");
			fail("��O���������Ȃ����߃e�X�g���s ");
    	}catch(DataSetException e){
			assertEquals("Not record : ABC", e.getMessage());
    	}
	}


	/**
	 * boolean containsAll(Collection c)�̃e�X�g�B
	 * <p>
	 * �����F
	 * <ul>
	 * <li>���̖��O�ƃX�L�[�}���w�肵��RecordList(String name, String schema)�����s����</li>
	 * <li>name    : "TestRecordList"</li>
	 * <li>schema  : ":A,java.lang.String,,,"<BR>      ":B,java.lang.String,,,"</li>
	 * <li>RecordList#createRecord()�ŐV�������R�[�h�𐶐�����(�P�ڂ̃��R�[�h�쐬)</li>
	 * <li>�p�����[�^A�ɒl��ݒ肷��BRecord#setProperty("A", "1")�����s����</li>
	 * <li>�p�����[�^B�ɒl��ݒ肷��BRecord#setProperty("B", "1")�����s����</li>
	 * <li>RecordList#addRecord(Record r)�Ń��R�[�h��ǉ�����</li>
	 * <li>RecordList#createRecord()�ŐV�������R�[�h�𐶐�����(�Q�ڂ̃��R�[�h�쐬)</li>
	 * <li>�p�����[�^A�ɒl��ݒ肷��BRecord#setProperty("A", "2")�����s����</li>
	 * <li>�p�����[�^B�ɒl��ݒ肷��BRecord#setProperty("B", "2")�����s����</li>
	 * <li>RecordList#addRecord(Record r)�Ń��R�[�h��ǉ�����</li>
	 * <li>RecordList#createRecord()�ŐV�������R�[�h�𐶐�����(�R�ڂ̃��R�[�h�쐬)</li>
	 * <li>�p�����[�^A�ɒl��ݒ肷��BRecord#setProperty("A", "3")�����s����</li>
	 * <li>�p�����[�^B�ɒl��ݒ肷��BRecord#setProperty("B", "3")�����s����</li>
	 * <li>RecordList#addRecord(Record r)�Ń��R�[�h��ǉ�����</li>
	 * <li>List�^�I�u�W�F�N�g�ɏ�LRecord�I�u�W�F�N�g��ǉ����AcontainsAll(Collection c)<BR>
	 * �Ɏw�肵�Ď��s</li>
	 * </ul>
	 * �m�F�F
	 * <ul>
     * <li>true���Ԃ��Ă��邱�Ƃ��m�F</li>
	 * </ul>
	 */
	public void testContainsAll() {
	    try{
	    	RecordList rlist = new RecordList("TestRecordList", ":A,java.lang.String,,,\n" +
	    			":B,java.lang.String,,,");
	    	
	    	Record rec1 = rlist.createRecord();
	    	rec1.setProperty("A", "1");
	    	rec1.setProperty("B", "1");
	    	rlist.addRecord(rec1);

	    	Record rec2 = rlist.createRecord();
	    	rec2.setProperty("A", "2");
	    	rec2.setProperty("B", "2");
	    	rlist.addRecord(rec2);

	    	Record rec3 = rlist.createRecord();
	    	rec3.setProperty("A", "3");
	    	rec3.setProperty("B", "3");
	    	rlist.addRecord(rec3);
	    	
	    	Collection list = new ArrayList();
	    	list.add(rec1);
	    	list.add(rec2);
	    	list.add(rec3);
	    	
	    	assertTrue(rlist.containsAll(list));
    	}catch(PropertySchemaDefineException e){
    		e.printStackTrace();
			fail("��O����");
    	}
	}


	/**
	 * addAll(Collection c)�̃e�X�g�B
	 * <p>
	 * �����F
	 * <ul>
	 * <li>���̖��O�ƃX�L�[�}���w�肵��RecordList(String name, String schema)�����s����</li>
	 * <li>name    : "TestRecordList"</li>
	 * <li>schema  : ":A,java.lang.String,,,"<BR>      ":B,java.lang.String,,,"</li>
	 * <li>RecordList#createRecord()�ŐV�������R�[�h�𐶐�����(�P�ڂ̃��R�[�h�쐬)</li>
	 * <li>�p�����[�^A�ɒl��ݒ肷��BRecord#setProperty("A", "1")�����s����</li>
	 * <li>�p�����[�^B�ɒl��ݒ肷��BRecord#setProperty("B", "1")�����s����</li>
	 * <li>RecordList#createRecord()�ŐV�������R�[�h�𐶐�����(�Q�ڂ̃��R�[�h�쐬)</li>
	 * <li>�p�����[�^A�ɒl��ݒ肷��BRecord#setProperty("A", "2")�����s����</li>
	 * <li>�p�����[�^B�ɒl��ݒ肷��BRecord#setProperty("B", "2")�����s����</li>
	 * <li>RecordList#createRecord()�ŐV�������R�[�h�𐶐�����(�R�ڂ̃��R�[�h�쐬)</li>
	 * <li>�p�����[�^A�ɒl��ݒ肷��BRecord#setProperty("A", "3")�����s����</li>
	 * <li>�p�����[�^B�ɒl��ݒ肷��BRecord#setProperty("B", "3")�����s����</li>
	 * <li>List�^�I�u�W�F�N�g�ɏ�LRecord�I�u�W�F�N�g��ǉ����AcontainsAll(Collection c)<BR>
	 * �Ɏw�肵�Ď��s</li>
	 * <li>addAll(Collection c)�Ɏw�肵�Ď��s</li>
	 * <li>containsAll(Collection c)�Ɏw�肵�Ď��s</li>
	 * </ul>
	 * �m�F�F
	 * <ul>
     * <li>�ŏ���containsAll�ł�false�A����ȊO��true���Ԃ��Ă��邱�Ƃ��m�F</li>
	 * </ul>
	 */
	public void testAddAll() {
	    try{
	    	RecordList rlist = new RecordList("TestRecordList", ":A,java.lang.String,,,\n" +
	    			":B,java.lang.String,,,");
	    	
	    	Record rec1 = rlist.createRecord();
	    	rec1.setProperty("A", "1");
	    	rec1.setProperty("B", "1");

	    	Record rec2 = rlist.createRecord();
	    	rec2.setProperty("A", "2");
	    	rec2.setProperty("B", "2");

	    	Record rec3 = rlist.createRecord();
	    	rec3.setProperty("A", "3");
	    	rec3.setProperty("B", "3");
	    	
	    	Collection list = new ArrayList();
	    	list.add(rec1);
	    	list.add(rec2);
	    	list.add(rec3);
	    	
	    	assertFalse(rlist.containsAll(list));
	    	assertTrue(rlist.addAll(list));
	    	assertTrue(rlist.containsAll(list));

	    }catch(PropertySchemaDefineException e){
    		e.printStackTrace();
			fail("��O����");
    	}
	}

	/**
	 * addAll(Collection c)�̃e�X�g�B
	 * <p>
	 * �����F
	 * <ul>
	 * <li>���̖��O�ƃX�L�[�}���w�肵��RecordList(String name, String schema)�����s����</li>
	 * <li>name    : "TestRecordList"</li>
	 * <li>schema  : ":A,java.lang.String,,,"<BR>      ":B,java.lang.String,,,"</li>
	 * <li>RecordList#createRecord()�ŐV�������R�[�h�𐶐�����(�P�ڂ̃��R�[�h�쐬)</li>
	 * <li>�p�����[�^A�ɒl��ݒ肷��BRecord#setProperty("A", "1")�����s����</li>
	 * <li>�p�����[�^B�ɒl��ݒ肷��BRecord#setProperty("B", "1")�����s����</li>
	 * <li>RecordList#createRecord()�ŐV�������R�[�h�𐶐�����(�Q�ڂ̃��R�[�h�쐬)</li>
	 * <li>�p�����[�^A�ɒl��ݒ肷��BRecord#setProperty("A", "2")�����s����</li>
	 * <li>�p�����[�^B�ɒl��ݒ肷��BRecord#setProperty("B", "2")�����s����</li>
	 * <li>RecordList#createRecord()�ŐV�������R�[�h�𐶐�����(�R�ڂ̃��R�[�h�쐬)</li>
	 * <li>�p�����[�^A�ɒl��ݒ肷��BRecord#setProperty("A", "3")�����s����</li>
	 * <li>�p�����[�^B�ɒl��ݒ肷��BRecord#setProperty("B", "3")�����s����</li>
	 * <li>Null��List�^�I�u�W�F�N�g��addAll(Collection c)�Ɏw�肵�Ď��s</li>
	 * </ul>
	 * �m�F�F
	 * <ul>
     * <li>false���Ԃ��Ă��邱�Ƃ��m�F</li>
	 * </ul>
	 */
	public void testAddAllNull() {
	    try{
	    	RecordList rlist = new RecordList("TestRecordList", ":A,java.lang.String,,,\n" +
	    			":B,java.lang.String,,,");
	    	
	    	Record rec1 = rlist.createRecord();
	    	rec1.setProperty("A", "1");
	    	rec1.setProperty("B", "1");

	    	Record rec2 = rlist.createRecord();
	    	rec2.setProperty("A", "2");
	    	rec2.setProperty("B", "2");

	    	Record rec3 = rlist.createRecord();
	    	rec3.setProperty("A", "3");
	    	rec3.setProperty("B", "3");
	    	
	    	Collection list = new ArrayList();
	    	
	    	assertFalse(rlist.addAll(list));

	    }catch(PropertySchemaDefineException e){
    		e.printStackTrace();
			fail("��O����");
    	}
	}

	/**
	 * addAll(int index, Collection c)�̃e�X�g�B
	 * <p>
	 * �����F
	 * <ul>
	 * <li>���̖��O�ƃX�L�[�}���w�肵��RecordList(String name, String schema)�����s����</li>
	 * <li>name    : "TestRecordList"</li>
	 * <li>schema  : ":A,java.lang.String,,,"<BR>      ":B,java.lang.String,,,"</li>
	 * <li>RecordList#createRecord()�ŐV�������R�[�h�𐶐�����(�P�ڂ̃��R�[�h�쐬)</li>
	 * <li>�p�����[�^A�ɒl��ݒ肷��BRecord#setProperty("A", "1")�����s����</li>
	 * <li>�p�����[�^B�ɒl��ݒ肷��BRecord#setProperty("B", "1")�����s����</li>
	 * <li>RecordList#createRecord()�ŐV�������R�[�h�𐶐�����(�Q�ڂ̃��R�[�h�쐬)</li>
	 * <li>�p�����[�^A�ɒl��ݒ肷��BRecord#setProperty("A", "2")�����s����</li>
	 * <li>�p�����[�^B�ɒl��ݒ肷��BRecord#setProperty("B", "2")�����s����</li>
	 * <li>RecordList#createRecord()�ŐV�������R�[�h�𐶐�����(�R�ڂ̃��R�[�h�쐬)</li>
	 * <li>�p�����[�^A�ɒl��ݒ肷��BRecord#setProperty("A", "3")�����s����</li>
	 * <li>�p�����[�^B�ɒl��ݒ肷��BRecord#setProperty("B", "3")�����s����</li>
	 * <li>List�^�I�u�W�F�N�g�ɏ�LRecord�I�u�W�F�N�g��ǉ����AcontainsAll(Collection c)<BR>
	 * �Ɏw�肵�Ď��s</li>
	 * <li>addAll(int index, Collection c)�Ɏw�肵�Ď��s</li>
	 * <li>containsAll(Collection c)�Ɏw�肵�Ď��s</li>
	 * </ul>
	 * �m�F�F
	 * <ul>
     * <li>�ŏ���containsAll�ł�false�A����ȊO��true���Ԃ��Ă��邱�Ƃ��m�F</li>
	 * </ul>
	 */
	public void testAddAllIndex() {
	    try{
	    	RecordList rlist = new RecordList("TestRecordList", ":A,java.lang.String,,,\n" +
	    			":B,java.lang.String,,,");
	    	
	    	Record rec1 = rlist.createRecord();
	    	rec1.setProperty("A", "1");
	    	rec1.setProperty("B", "1");

	    	Record rec2 = rlist.createRecord();
	    	rec2.setProperty("A", "2");
	    	rec2.setProperty("B", "2");

	    	Record rec3 = rlist.createRecord();
	    	rec3.setProperty("A", "3");
	    	rec3.setProperty("B", "3");
	    	
	    	Collection list = new ArrayList();
	    	list.add(rec1);
	    	list.add(rec2);
	    	list.add(rec3);
	    	
	    	assertFalse(rlist.containsAll(list));
	    	assertTrue(rlist.addAll(0, list));
	    	assertTrue(rlist.containsAll(list));

	    }catch(PropertySchemaDefineException e){
    		e.printStackTrace();
			fail("��O����");
    	}
	}

	/**
	 * addAll(int index, Collection c)�̃e�X�g�B
	 * <p>
	 * �����F
	 * <ul>
	 * <li>���̖��O�ƃX�L�[�}���w�肵��RecordList(String name, String schema)�����s����</li>
	 * <li>name    : "TestRecordList"</li>
	 * <li>schema  : ":A,java.lang.String,,,"<BR>      ":B,java.lang.String,,,"</li>
	 * <li>RecordList#createRecord()�ŐV�������R�[�h�𐶐�����(�P�ڂ̃��R�[�h�쐬)</li>
	 * <li>�p�����[�^A�ɒl��ݒ肷��BRecord#setProperty("A", "1")�����s����</li>
	 * <li>�p�����[�^B�ɒl��ݒ肷��BRecord#setProperty("B", "1")�����s����</li>
	 * <li>RecordList#createRecord()�ŐV�������R�[�h�𐶐�����(�Q�ڂ̃��R�[�h�쐬)</li>
	 * <li>�p�����[�^A�ɒl��ݒ肷��BRecord#setProperty("A", "2")�����s����</li>
	 * <li>�p�����[�^B�ɒl��ݒ肷��BRecord#setProperty("B", "2")�����s����</li>
	 * <li>RecordList#createRecord()�ŐV�������R�[�h�𐶐�����(�R�ڂ̃��R�[�h�쐬)</li>
	 * <li>�p�����[�^A�ɒl��ݒ肷��BRecord#setProperty("A", "3")�����s����</li>
	 * <li>�p�����[�^B�ɒl��ݒ肷��BRecord#setProperty("B", "3")�����s����</li>
	 * <li>Null��List�^�I�u�W�F�N�g��addAll(int index, Collection c)�Ɏw�肵�Ď��s</li>
	 * </ul>
	 * �m�F�F
	 * <ul>
     * <li>false���Ԃ��Ă��邱�Ƃ��m�F</li>
	 * </ul>
	 */
	public void testAddAllIndexNull() {
	    try{
	    	RecordList rlist = new RecordList("TestRecordList", ":A,java.lang.String,,,\n" +
	    			":B,java.lang.String,,,");
	    	
	    	Record rec1 = rlist.createRecord();
	    	rec1.setProperty("A", "1");
	    	rec1.setProperty("B", "1");

	    	Record rec2 = rlist.createRecord();
	    	rec2.setProperty("A", "2");
	    	rec2.setProperty("B", "2");

	    	Record rec3 = rlist.createRecord();
	    	rec3.setProperty("A", "3");
	    	rec3.setProperty("B", "3");
	    	
	    	Collection list = new ArrayList();
	    	
	    	assertFalse(rlist.addAll(0,list));

	    }catch(PropertySchemaDefineException e){
    		e.printStackTrace();
			fail("��O����");
    	}
	}

	/**
	 * removeAll(Collection c)�̃e�X�g�B
	 * <p>
	 * �����F
	 * <ul>
	 * <li>���̖��O�ƃX�L�[�}���w�肵��RecordList(String name, String schema)�����s����</li>
	 * <li>name    : "TestRecordList"</li>
	 * <li>schema  : ":A,java.lang.String,,,"<BR>      ":B,java.lang.String,,,"</li>
	 * <li>RecordList#createRecord()�ŐV�������R�[�h�𐶐�����(�P�ڂ̃��R�[�h�쐬)</li>
	 * <li>�p�����[�^A�ɒl��ݒ肷��BRecord#setProperty("A", "1")�����s����</li>
	 * <li>�p�����[�^B�ɒl��ݒ肷��BRecord#setProperty("B", "1")�����s����</li>
	 * <li>RecordList#addRecord(Record r)�Ń��R�[�h��ǉ�����</li>
	 * <li>RecordList#createRecord()�ŐV�������R�[�h�𐶐�����(�Q�ڂ̃��R�[�h�쐬)</li>
	 * <li>�p�����[�^A�ɒl��ݒ肷��BRecord#setProperty("A", "2")�����s����</li>
	 * <li>�p�����[�^B�ɒl��ݒ肷��BRecord#setProperty("B", "2")�����s����</li>
	 * <li>RecordList#addRecord(Record r)�Ń��R�[�h��ǉ�����</li>
	 * <li>RecordList#createRecord()�ŐV�������R�[�h�𐶐�����(�R�ڂ̃��R�[�h�쐬)</li>
	 * <li>�p�����[�^A�ɒl��ݒ肷��BRecord#setProperty("A", "3")�����s����</li>
	 * <li>�p�����[�^B�ɒl��ݒ肷��BRecord#setProperty("B", "3")�����s����</li>
	 * <li>RecordList#addRecord(Record r)�Ń��R�[�h��ǉ�����</li>
	 * <li>List�^�I�u�W�F�N�g�ɏ�L�ō쐬����Record�I�u�W�F�N�g��ǉ����AremoveAll(Collection c)<BR>
	 * �Ɏw�肵�Ď��s</li>
	 * </ul>
	 * �m�F�F
	 * <ul>
     * <li>removeAll���s�O�̗v�f����3�A���s��0�ɂȂ邱�Ƃ��m�F</li>
	 * </ul>
	 */
	public void testRemoveAll() {
	    try{
	    	RecordList rlist = new RecordList("TestRecordList", ":A,java.lang.String,,,\n" +
	    			":B,java.lang.String,,,");
	    		    	
	    	Record rec1 = rlist.createRecord();
	    	rec1.setProperty("A", "1");
	    	rec1.setProperty("B", "1");
	    	rlist.addRecord(rec1);

	    	Record rec2 = rlist.createRecord();
	    	rec2.setProperty("A", "2");
	    	rec2.setProperty("B", "2");
	    	rlist.addRecord(rec2);

	    	Record rec3 = rlist.createRecord();
	    	rec3.setProperty("A", "3");
	    	rec3.setProperty("B", "3");
	    	rlist.addRecord(rec3);
	    	
	    	Collection list = new ArrayList();
	    	list.add(rec1);
	    	list.add(rec2);
	    	list.add(rec3);
	    	
	    	assertEquals(3, rlist.size());
	    	rlist.removeAll(list);
	    	assertEquals(0, rlist.size());

	    }catch(PropertySchemaDefineException e){
    		e.printStackTrace();
			fail("��O����");
    	}
	}


	/**
	 * retainAll(Collection c)�̃e�X�g�B
	 * <p>
	 * �����F
	 * <ul>
	 * <li>���̖��O�ƃX�L�[�}���w�肵��RecordList(String name, String schema)�����s����</li>
	 * <li>name    : "TestRecordList"</li>
	 * <li>schema  : ":A,java.lang.String,,,"<BR>      ":B,java.lang.String,,,"</li>
	 * <li>RecordList#createRecord()�ŐV�������R�[�h�𐶐�����(�P�ڂ̃��R�[�h�쐬)</li>
	 * <li>�p�����[�^A�ɒl��ݒ肷��BRecord#setProperty("A", "1")�����s����</li>
	 * <li>�p�����[�^B�ɒl��ݒ肷��BRecord#setProperty("B", "1")�����s����</li>
	 * <li>RecordList#addRecord(Record r)�Ń��R�[�h��ǉ�����</li>
	 * <li>RecordList#createRecord()�ŐV�������R�[�h�𐶐�����(�Q�ڂ̃��R�[�h�쐬)</li>
	 * <li>�p�����[�^A�ɒl��ݒ肷��BRecord#setProperty("A", "2")�����s����</li>
	 * <li>�p�����[�^B�ɒl��ݒ肷��BRecord#setProperty("B", "2")�����s����</li>
	 * <li>RecordList#addRecord(Record r)�Ń��R�[�h��ǉ�����</li>
	 * <li>RecordList#createRecord()�ŐV�������R�[�h�𐶐�����(�R�ڂ̃��R�[�h�쐬)</li>
	 * <li>�p�����[�^A�ɒl��ݒ肷��BRecord#setProperty("A", "3")�����s����</li>
	 * <li>�p�����[�^B�ɒl��ݒ肷��BRecord#setProperty("B", "3")�����s����</li>
	 * <li>RecordList#addRecord(Record r)�Ń��R�[�h��ǉ�����</li>
	 * <li>List�^�I�u�W�F�N�g�ɏ�L�ō쐬�����R�ڂ�Record�I�u�W�F�N�g��ǉ����AretainAll(Collection c)<BR>
	 * �Ɏw�肵�Ď��s</li>
	 * </ul>
	 * �m�F�F
	 * <ul>
     * <li>removeAll���s�O�̗v�f����3�A���s��1�ɂȂ邱�Ƃ��m�F</li>
     * <li>�c���Ă��郌�R�[�h��retainAll�w�肵���R�ڂ̃��R�[�h�ł��邱�Ƃ��m�F</li>
	 * </ul>
	 */
	public void testRetainAll() {
	    try{
	    	RecordList rlist = new RecordList("TestRecordList", ":A,java.lang.String,,,\n" +
	    			":B,java.lang.String,,,");
	    		    	
	    	Record rec1 = rlist.createRecord();
	    	rec1.setProperty("A", "1");
	    	rec1.setProperty("B", "1");
	    	rlist.addRecord(rec1);

	    	Record rec2 = rlist.createRecord();
	    	rec2.setProperty("A", "2");
	    	rec2.setProperty("B", "2");
	    	rlist.addRecord(rec2);

	    	Record rec3 = rlist.createRecord();
	    	rec3.setProperty("A", "3");
	    	rec3.setProperty("B", "3");
	    	rlist.addRecord(rec3);
	    	
	    	Collection list = new ArrayList();
	    	list.add(rec3);
	    	
	    	assertEquals(3, rlist.size());
	    	rlist.retainAll(list);
	    	assertEquals(1, rlist.size());
	    	assertEquals(rec3, rlist.getRecord(0));

	    }catch(PropertySchemaDefineException e){
    		e.printStackTrace();
			fail("��O����");
    	}
	}


	
	/**
	 * �S�Ẵ��R�[�h���폜����e�X�g�B
	 * <p>
	 * �����F
	 * <ul>
	 * <li>���̖��O�ƃX�L�[�}���w�肵��RecordList(String name, String schema)�����s����</li>
	 * <li>name    : "TestRecordList"</li>
	 * <li>schema  : ":A,java.lang.String,,,"<BR>      ":B,java.lang.String,,,"</li>
	 * <li>RecordList#createRecord()�ŐV�������R�[�h�𐶐�����(�P�ڂ̃��R�[�h�쐬)</li>
	 * <li>�p�����[�^A�ɒl��ݒ肷��BRecord#setProperty("A", "1")�����s����</li>
	 * <li>�p�����[�^B�ɒl��ݒ肷��BRecord#setProperty("B", "1")�����s����</li>
	 * <li>RecordList#addRecord(Record r)�Ń��R�[�h��ǉ�����</li>
	 * <li>RecordList#createRecord()�ŐV�������R�[�h�𐶐�����(�Q�ڂ̃��R�[�h�쐬)</li>
	 * <li>�p�����[�^A�ɒl��ݒ肷��BRecord#setProperty("A", "2")�����s����</li>
	 * <li>�p�����[�^B�ɒl��ݒ肷��BRecord#setProperty("B", "2")�����s����</li>
	 * <li>RecordList#addRecord(Record r)�Ń��R�[�h��ǉ�����</li>
	 * <li>RecordList#createRecord()�ŐV�������R�[�h�𐶐�����(�R�ڂ̃��R�[�h�쐬)</li>
	 * <li>�p�����[�^A�ɒl��ݒ肷��BRecord#setProperty("A", "3")�����s����</li>
	 * <li>�p�����[�^B�ɒl��ݒ肷��BRecord#setProperty("B", "3")�����s����</li>
	 * <li>RecordList#addRecord(Record r)�Ń��R�[�h��ǉ�����</li>
	 * <li>RecordList#clear()�����s����</li>
	 * </ul>
	 * �m�F�F
	 * <ul>
     * <li>RecordList#isEmpty()��true��Ԃ��B</li>
	 * </ul>
	 */
	public void testClear() {
	    try{
	    	RecordList rlist = new RecordList("TestRecordList", ":A,java.lang.String,,,\n" +
	    			":B,java.lang.String,,,");
	    	
	    	Record rec1 = rlist.createRecord();
	    	rec1.setProperty("A", "1");
	    	rec1.setProperty("B", "1");
	    	rlist.addRecord(rec1);

	    	Record rec2 = rlist.createRecord();
	    	rec2.setProperty("A", "2");
	    	rec2.setProperty("B", "2");
	    	rlist.addRecord(rec2);

	    	Record rec3 = rlist.createRecord();
	    	rec3.setProperty("A", "3");
	    	rec3.setProperty("B", "3");
	    	rlist.addRecord(rec3);
	    	
	    	rlist.clear();

	    	assertTrue(rlist.isEmpty());
    	}catch(PropertySchemaDefineException e){
    		e.printStackTrace();
    	}
	}


	/**
	 * ���R�[�h���X�g�𕡐�����e�X�g�B
	 * <p>
	 * �����F
	 * <ul>
	 * <li>���̖��O�ƃX�L�[�}���w�肵��RecordList(String name, String schema)�����s����</li>
	 * <li>name    : "TestRecordList"</li>
	 * <li>schema  : ":A,java.lang.String,,,"<BR>      ":B,java.lang.String,,,"</li>
	 * <li>RecordList#createRecord()�ŐV�������R�[�h�𐶐�����(�P�ڂ̃��R�[�h�쐬)</li>
	 * <li>�p�����[�^A�ɒl��ݒ肷��BRecord#setProperty("A", "1")�����s����</li>
	 * <li>�p�����[�^B�ɒl��ݒ肷��BRecord#setProperty("B", "1")�����s����</li>
	 * <li>RecordList#addRecord(Record r)�Ń��R�[�h��ǉ�����</li>
	 * <li>RecordList#createRecord()�ŐV�������R�[�h�𐶐�����(�Q�ڂ̃��R�[�h�쐬)</li>
	 * <li>�p�����[�^A�ɒl��ݒ肷��BRecord#setProperty("A", "2")�����s����</li>
	 * <li>�p�����[�^B�ɒl��ݒ肷��BRecord#setProperty("B", "2")�����s����</li>
	 * <li>RecordList#addRecord(Record r)�Ń��R�[�h��ǉ�����</li>
	 * <li>RecordList#createRecord()�ŐV�������R�[�h�𐶐�����(�R�ڂ̃��R�[�h�쐬)</li>
	 * <li>�p�����[�^A�ɒl��ݒ肷��BRecord#setProperty("A", "3")�����s����</li>
	 * <li>�p�����[�^B�ɒl��ݒ肷��BRecord#setProperty("B", "3")�����s����</li>
	 * <li>RecordList#addRecord(Record r)�Ń��R�[�h��ǉ�����</li>
	 * <li>RecordList#cloneSchema()�����s����</li>
	 * </ul>
	 * �m�F�F
	 * <ul>
     * <li>RecordList�̃X�L�[�}�̂ݕ�������Ă��邱�Ƃ��m�F</li>
     * <li>RecordList#isEmpty()��true��Ԃ��B</li>
	 * </ul>
	 */
	public void testCloneSchema() {
	    try{
	    	RecordList rlist = new RecordList("TestRecordList", ":A,java.lang.String,,,\n" +
	    			":B,java.lang.String,,,");
	    	
	    	Record rec1 = rlist.createRecord();
	    	rec1.setProperty("A", "1");
	    	rec1.setProperty("B", "1");
	    	rlist.addRecord(rec1);

	    	Record rec2 = rlist.createRecord();
	    	rec2.setProperty("A", "2");
	    	rec2.setProperty("B", "2");
	    	rlist.addRecord(rec2);

	    	Record rec3 = rlist.createRecord();
	    	rec3.setProperty("A", "3");
	    	rec3.setProperty("B", "3");
	    	rlist.addRecord(rec3);
	    	
	    	RecordList rlist2 = rlist.cloneSchema();

	    	assertEquals(rlist.getSchema(),rlist2.getSchema());
	    	assertTrue(rlist2.isEmpty());
    	}catch(PropertySchemaDefineException e){
    		e.printStackTrace();
    	}
	}


	/**
	 * ���R�[�h���X�g�𕡐�����e�X�g�B
	 * <p>
	 * �����F
	 * <ul>
	 * <li>���̖��O�ƃX�L�[�}���w�肵��RecordList(String name, String schema)�����s����</li>
	 * <li>name    : "TestRecordList"</li>
	 * <li>schema  : ":A,java.lang.String,,,"<BR>      ":B,java.lang.String,,,"</li>
	 * <li>RecordList#createRecord()�ŐV�������R�[�h�𐶐�����(�P�ڂ̃��R�[�h�쐬)</li>
	 * <li>�p�����[�^A�ɒl��ݒ肷��BRecord#setProperty("A", "1")�����s����</li>
	 * <li>�p�����[�^B�ɒl��ݒ肷��BRecord#setProperty("B", "1")�����s����</li>
	 * <li>RecordList#addRecord(Record r)�Ń��R�[�h��ǉ�����</li>
	 * <li>RecordList#createRecord()�ŐV�������R�[�h�𐶐�����(�Q�ڂ̃��R�[�h�쐬)</li>
	 * <li>�p�����[�^A�ɒl��ݒ肷��BRecord#setProperty("A", "2")�����s����</li>
	 * <li>�p�����[�^B�ɒl��ݒ肷��BRecord#setProperty("B", "2")�����s����</li>
	 * <li>RecordList#addRecord(Record r)�Ń��R�[�h��ǉ�����</li>
	 * <li>RecordList#createRecord()�ŐV�������R�[�h�𐶐�����(�R�ڂ̃��R�[�h�쐬)</li>
	 * <li>�p�����[�^A�ɒl��ݒ肷��BRecord#setProperty("A", "3")�����s����</li>
	 * <li>�p�����[�^B�ɒl��ݒ肷��BRecord#setProperty("B", "3")�����s����</li>
	 * <li>RecordList#addRecord(Record r)�Ń��R�[�h��ǉ�����</li>
	 * <li>RecordList#cloneRecordList()�����s����</li>
	 * </ul>
	 * �m�F�F
	 * <ul>
     * <li>RecordList�̃X�L�[�}�A���R�[�h����������Ă��邱�Ƃ��m�F</li>
	 * </ul>
	 */
	public void testCloneRecordList() {
	    try{
	    	RecordList rlist = new RecordList("TestRecordList", ":A,java.lang.String,,,\n" +
	    			":B,java.lang.String,,,");
	    	
	    	Record rec1 = rlist.createRecord();
	    	rec1.setProperty("A", "1");
	    	rec1.setProperty("B", "1");
	    	rlist.addRecord(rec1);

	    	Record rec2 = rlist.createRecord();
	    	rec2.setProperty("A", "2");
	    	rec2.setProperty("B", "2");
	    	rlist.addRecord(rec2);

	    	Record rec3 = rlist.createRecord();
	    	rec3.setProperty("A", "3");
	    	rec3.setProperty("B", "3");
	    	rlist.addRecord(rec3);
	    	
	    	RecordList rlist2 = rlist.cloneRecordList();

	    	assertEquals(rlist.getSchema(),rlist2.getSchema());
	    	assertEquals(rlist.records.size(),rlist2.records.size());
    	}catch(PropertySchemaDefineException e){
    		e.printStackTrace();
    	}
	}
}
