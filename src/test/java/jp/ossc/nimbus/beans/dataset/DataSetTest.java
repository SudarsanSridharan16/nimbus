package jp.ossc.nimbus.beans.dataset;

import java.util.*;

import junit.framework.TestCase;


//
/**
 * 
 * @author S.Teshima
 * @version 1.00 �쐬: 2008/01/17 - S.Teshima
 */

public class DataSetTest extends TestCase {
	public DataSetTest(String arg0) {
		super(arg0);
	}

	
	 public static void main(String[] args) {
	 junit.textui.TestRunner.run(DataSetTest.class); }
	 
	/**
	 * ��̃f�[�^�Z�b�g�𐶐�����e�X�g�B
	 * <p>
	 * �����F
	 * <ul>
	 * <li>��̃f�[�^�Z�b�g�𐶐�����</li>
	 * </ul>
	 * �m�F�F
	 * <ul>
	 * <li>�f�[�^�Z�b�g�̖��O��null�ł��邱�Ƃ��m�F</li>
	 * </ul>
	 */
	public void testDataSet() {
		DataSet dataset = new DataSet();
		assertNull(dataset.getName());
	}

	/**
	 * ���O�t���̃f�[�^�Z�b�g�𐶐�����e�X�g�B
	 * <p>
	 * �����F
	 * <ul>
	 * <li>Dataset#DataSet("TEST_DATASET")�Ŗ��O�t����DataSet�𐶐�����</li>
	 * </ul>
	 * �m�F�F
	 * <ul>
	 * <li>Dataset#getname()��"TEST_DATASET"��Ԃ��B</li>
	 * </ul>
	 */
	public void testDataSetString() {
		DataSet dataset = new DataSet("TEST_DATASET");
		assertEquals("TEST_DATASET", dataset.getName());
	}

	/**
	 * �f�[�^�Z�b�g����ݒ肷��e�X�g�B
	 * <p>
	 * �����F
	 * <ul>
	 * <li>Dataset#DataSet("TESTDS")�Ŗ��O�t����DataSet�𐶐�����</li>
	 * <li>�f�[�^�Z�b�g�̖��O��"TEST"�ł��邱�Ƃ��m�F</li>
	 * <li>Dataset#SetName("TEST_DATASET")�Ŗ��O��ݒ肷��</li>
	 * </ul>
	 * �m�F�F
	 * <ul>
	 * <li>Dataset#getname()��"TEST_DATASET"��Ԃ��B</li>
	 * </ul>
	 */
	public void testSetName1() {
		DataSet dataset = new DataSet("TESTDS");
		assertEquals("TESTDS", dataset.getName());
		dataset.setName("TEST_DATASET");
		assertEquals("TEST_DATASET", dataset.getName());
	}

	/**
	 * ��̃f�[�^�Z�b�g�ɖ��O��ݒ肷��e�X�g�B
	 * <p>
	 * �����F
	 * <ul>
	 * <li>Dataset#DataSet()�ŋ��DataSet�𐶐�����</li>
	 * <li>�f�[�^�Z�b�g�̖��O��null�ł��邱�Ƃ��m�F</li>
	 * <li>Dataset#SetName("TEST_DATASET")�Ŗ��O��ݒ肷��</li>
	 * </ul>
	 * �m�F�F
	 * <ul>
	 * <li>Dataset#getname()��"TEST_DATASET"��Ԃ��B</li>
	 * </ul>
	 */
	public void testSetName2() {
		DataSet dataset = new DataSet();
		assertNull(dataset.getName());
		dataset.setName("TEST_DATASET");
		assertEquals("TEST_DATASET", dataset.getName());
	}

	/**
	 * ���O�������Ȃ�Header �̃X�L�[�}��ݒ肷��e�X�g�B
	 * <p>
	 * �����F
	 * <ul>
	 * <li>Dataset#DataSet()�ŋ��DataSet�𐶐�����</li>
	 * <li>���̃X�L�[�}���w�肵��Dataset#setHeaderSchema(String schema)�����s����</li>
	 * <li>":A,java.lang.String,,,"</li>
	 * </ul>
	 * �m�F�F
	 * <ul>
	 * <li>��OPropertySchemaDefineException���������Ȃ����Ƃ��m�F</li>
	 * <li>Dataset#getHeader()�Ŗ��O�Ȃ���Header���擾�ł���B</li>
	 * <li>�擾����Header�̃X�L�[�}���ݒ�l�ƈ�v���Ă���</li>
	 * </ul>
	 */
	public void testSetHeaderSchemaString() {
		try {
			DataSet dataset = new DataSet();
			String schema = ":A,java.lang.String,,,";
			dataset.setHeaderSchema(schema);
			Header header = dataset.getHeader();
			assertEquals(schema, header.schema);
		} catch (PropertySchemaDefineException e) {
			e.printStackTrace();
			fail("��O����");
		}
	}

	/**
	 * ���O�������Ȃ�Header �̃X�L�[�}��ݒ肷��e�X�g�B
	 * <p>
	 * �����F
	 * <ul>
	 * <li>Dataset#DataSet()�ŋ��DataSet�𐶐�����</li>
	 * <li>���̃X�L�[�}���w�肵��Dataset#setHeaderSchema(String schema)�����s����</li>
	 * <li>"A,java.lang.String,,," �i�N���X��؂�̂Ȃ��s���Ȏw��j</li>
	 * </ul>
	 * �m�F�F
	 * <ul>
	 * <li>��OPropertySchemaDefineException���������邱�Ƃ��m�F</li>
	 * </ul>
	 */
	public void testSetHeaderSchemaStringInvalid() {
		try {
			DataSet dataset = new DataSet();
			String schema = "A,java.lang.String,,,";
			dataset.setHeaderSchema(schema);
			fail("��O���������Ȃ����߃e�X�g���s ");
		} catch (PropertySchemaDefineException e) {
		}
	}

	/**
	 * �w�肵�����O��Header �̃X�L�[�}��ݒ肷��e�X�g�B
	 * <p>
	 * �����F
	 * <ul>
	 * <li>Dataset#DataSet()�ŋ��DataSet�𐶐�����</li>
	 * <li>���̃X�L�[�}���w�肵��Dataset#setHeaderSchema(String name, String schema)�����s����</li>
	 * <li>name : "test_header"</li>
	 * <li>schema: ":A,java.lang.String,,,"</li>
	 * </ul>
	 * �m�F�F
	 * <ul>
	 * <li>Dataset#getHeader(String name)��Header���擾�ł���B</li>
	 * </ul>
	 */
	public void testSetHeaderSchemaStringString() {
		try {
			DataSet dataset = new DataSet();
			String hname = "test_header";
			String schema = ":A,java.lang.String,,,";
			dataset.setHeaderSchema(hname, schema);
			Header header = dataset.getHeader(hname);
			assertEquals(schema, header.schema);
		} catch (PropertySchemaDefineException e) {
			e.printStackTrace();
			fail("��O����");
		}
	}

	/**
	 * �w�肵�����O��Header �̃X�L�[�}��ݒ肷��e�X�g�B
	 * <p>
	 * �����F
	 * <ul>
	 * <li>Dataset#DataSet()�ŋ��DataSet�𐶐�����</li>
	 * <li>���̃X�L�[�}���w�肵��Dataset#setHeaderSchema(String name, String schema)�����s����</li>
	 * <li>name : "test_header"</li>
	 * <li>"A,java.lang.String,,," �i�N���X��؂�̂Ȃ��s���Ȏw��j</li>
	 * </ul>
	 * �m�F�F
	 * <ul>
	 * <li>��OPropertySchemaDefineException���������邱�Ƃ��m�F</li>
	 * </ul>
	 */
	public void testSetHeaderSchemaStringStringInvalid() {
		try {
			DataSet dataset = new DataSet();
			String hname = "test_header";
			String schema = "A,java.lang.String,,,";
			dataset.setHeaderSchema(hname, schema);
			fail("��O���������Ȃ����߃e�X�g���s ");
		} catch (PropertySchemaDefineException e) {
		}
	}

	/**
	 * ���O�������Ȃ�RecordList �̃X�L�[�}��ݒ肷��e�X�g�B
	 * <p>
	 * �����F
	 * <ul>
	 * <li>Dataset#DataSet()�ŋ��DataSet�𐶐�����</li>
	 * <li>���̃X�L�[�}���w�肵��Dataset#setRecordListSchema(String schema)�����s����</li>
	 * <li>":A,java.lang.String,,,"</li>
	 * </ul>
	 * �m�F�F
	 * <ul>
	 * <li>Dataset#getRecordList()�Ŗ��O�Ȃ���RecordList���擾�ł���B</li>
	 * </ul>
	 */
	public void testSetRecordListSchemaString() {
		try {
			DataSet dataset = new DataSet();
			String schema = ":A,java.lang.String,,,";
			dataset.setRecordListSchema(schema);
			RecordList rlist = dataset.getRecordList();
			assertEquals(schema, rlist.schema);
		} catch (PropertySchemaDefineException e) {
			e.printStackTrace();
			fail("��O����");
		}
	}

	/**
	 * ���O�������Ȃ�RecordList �̃X�L�[�}��ݒ肷��e�X�g�B
	 * <p>
	 * �����F
	 * <ul>
	 * <li>Dataset#DataSet()�ŋ��DataSet�𐶐�����</li>
	 * <li>���̃X�L�[�}���w�肵��Dataset#setRecordListSchema(String schema)�����s����</li>
	 * <li>"A,java.lang.String,,," �i�N���X��؂�̂Ȃ��s���Ȏw��j</li>
	 * </ul>
	 * �m�F�F
	 * <ul>
	 * <li>��OPropertySchemaDefineException���������邱�Ƃ��m�F</li>
	 * </ul>
	 */
	public void testSetRecordListSchemaStringInvalid() {
		try {
			DataSet dataset = new DataSet();
			String schema = "A,java.lang.String,,,";
			dataset.setRecordListSchema(schema);
			fail("��O���������Ȃ����߃e�X�g���s ");
		} catch (PropertySchemaDefineException e) {
		}
	}

	/**
	 * �w�肵�����O��RecordList �̃X�L�[�}��ݒ肷��e�X�g�B
	 * <p>
	 * �����F
	 * <ul>
	 * <li>Dataset#DataSet()�ŋ��DataSet�𐶐�����</li>
	 * <li>���̃X�L�[�}���w�肵��Dataset#setRecordListSchema(String name, String
	 * schema)�����s����</li>
	 * <li>name : "test_rlist"</li>
	 * <li>schema: ":A,java.lang.String,,,"</li>
	 * </ul>
	 * �m�F�F
	 * <ul>
	 * <li>Dataset#getRecordList(String name)��RecordList���擾�ł���B</li>
	 * </ul>
	 */
	public void testSetRecordListSchemaStringString() {
		try {
			DataSet dataset = new DataSet();
			String name = "test_rlist";
			String schema = ":A,java.lang.String,,,";
			dataset.setRecordListSchema(name, schema);
			RecordList rlist = dataset.getRecordList(name);
			assertEquals(schema, rlist.schema);
		} catch (PropertySchemaDefineException e) {
			e.printStackTrace();
			fail("��O����");
		}
	}

	/**
	 * �w�肵�����O��RecordList �̃X�L�[�}��ݒ肷��e�X�g�B
	 * <p>
	 * �����F
	 * <ul>
	 * <li>Dataset#DataSet()�ŋ��DataSet�𐶐�����</li>
	 * <li>���̃X�L�[�}���w�肵��Dataset#setRecordListSchema(String name, String
	 * schema)�����s����</li>
	 * <li>name : "test_rlist"</li>
	 * <li>"A,java.lang.String,,," �i�N���X��؂�̂Ȃ��s���Ȏw��j</li>
	 * </ul>
	 * �m�F�F
	 * <ul>
	 * <li>��OPropertySchemaDefineException���������邱�Ƃ��m�F</li>
	 * </ul>
	 */
	public void testSetRecordListSchemaStringStringInvalid() {
		try {
			DataSet dataset = new DataSet();
			String name = "test_rlist";
			String schema = "A,java.lang.String,,,";
			dataset.setRecordListSchema(name, schema);
			fail("��O���������Ȃ����߃e�X�g���s ");
		} catch (PropertySchemaDefineException e) {
		}
	}
	

	/**
	 * �w�肵�����O�̃l�X�gRecordList �̃X�L�[�}��ݒ肷��e�X�g�B
	 * <p>
	 * �����F
	 * <ul>
	 * <li>Dataset#DataSet()�ŋ��DataSet�𐶐�����</li>
	 * <li>���̃X�L�[�}���w�肵��Dataset#setNestedRecordListSchema(String name, String
	 * schema)�����s����</li>
	 * <li>name : "test_nestlist"</li>
	 * <li>schema: ":A,java.lang.String\n:B,int"</li>
	 * </ul>
	 * �m�F�F
	 * <ul>
	 * <li>getNestedRecordListSchema(name)�Ŏw�肵�����O�̃l�X�gRecordList�̃X�L�[�}�����擾�ł���B</li>
	 * </ul>
	 */
	public void testSetGetNestedRecordListSchema() {
		try {
			DataSet dataset = new DataSet();
			String name = "test_nestlist";
			String schema = ":A,java.lang.String\n:B,int";
			dataset.setNestedRecordListSchema(name, schema);
			assertEquals(schema, dataset.getNestedRecordListSchema(name).getSchema());
		} catch (PropertySchemaDefineException e) {
			e.printStackTrace();
			fail("��O����");
		}
	}


	/**
	 * �w�肵�����O�̃l�X�gRecordList �̃X�L�[�}���擾����e�X�g�B
	 * <p>
	 * �����F
	 * <ul>
	 * <li>Dataset#DataSet()�ŋ��DataSet�𐶐�����</li>
	 * <li>�l�X�gRecordList�͐ݒ肵�Ȃ�</li>
	 * </ul>
	 * �m�F�F
	 * <ul>
	 * <li>getNestedRecordListSchema(name)��null���Ԃ����B</li>
	 * </ul>
	 */
	public void testGetNestedRecordListSchemaNull() {
		try {
			DataSet dataset = new DataSet();
			assertNull(dataset.getNestedRecordListSchema("test"));
		} catch (PropertySchemaDefineException e) {
			e.printStackTrace();
			fail("��O����");
		}
	}


	/**
	 * ��`���ꂽ���ɕ��񂾃l�X�g�������R�[�h���X�g���z����擾����e�X�g�B
	 * <p>
	 * �����F
	 * <ul>
	 * <li>Dataset#DataSet()�ŋ��DataSet�𐶐�����</li>
	 * <li>���̃X�L�[�}���w�肵��Dataset#setNestedRecordListSchema(String name, String
	 * schema)���R����s����(xx�̕�����3,1,6�̏��Ŏw�肷��)</li>
	 * <li>name : "test_nestlistxx"</li>
	 * <li>schema: ":Axx,java.lang.String\n:Bxx,int"</li>
	 * <li>getNestedRecordListSchemaNames()�����s����</li>
	 * <li>getNestedRecordListSchemaSize()�����s����</li>
	 * </ul>
	 * �m�F�F
	 * <ul>
	 * <li>��`���ꂽ���ɕ��񂾃l�X�g�������R�[�h���X�g���z����擾�ł���B</li>
	 * <li>��`���ꂽ�l�X�g�������R�[�h���X�g�����擾�ł���B</li>
	 * </ul>
	 */
	public void testGetNestedRecordListSchemaNames() {
		try {
			DataSet dataset = new DataSet();
			
			String name = "test_nestlist3";
			String schema = ":A3,java.lang.String\n:B3,int";
			dataset.setNestedRecordListSchema(name, schema);
			
			name = "test_nestlist1";
			schema = ":A1,java.lang.String\n:B1,int";
			dataset.setNestedRecordListSchema(name, schema);
			
			name = "test_nestlist6";
			schema = ":A6,java.lang.String\n:B6,int";
			dataset.setNestedRecordListSchema(name, schema);
			
			String[] names = new String[]{"test_nestlist3","test_nestlist1","test_nestlist6"};
			
			assertTrue(Arrays.equals(names, dataset.getNestedRecordListSchemaNames()));
			assertEquals(names.length, dataset.getNestedRecordListSchemaSize());
		} catch (PropertySchemaDefineException e) {
			e.printStackTrace();
			fail("��O����");
		}
	}


	/**
	 * ��`���ꂽ���ɕ��񂾃l�X�g�������R�[�h���X�g���z����擾����e�X�g�B
	 * <p>
	 * �����F
	 * <ul>
	 * <li>Dataset#DataSet()�ŋ��DataSet�𐶐�����</li>
	 * <li>�l�X�g�������R�[�h���X�g���w���getNestedRecordListSchemaNames()�����s����</li>
	 * </ul>
	 * �m�F�F
	 * <ul>
	 * <li>�v�f���O�̕����^�z�񂪕Ԃ����</li>
	 * </ul>
	 */
	public void testGetNestedRecordListSchemaNamesNotExist() {
		try {
			DataSet dataset = new DataSet();
			
			
			String[] names = new String[0];
			
			assertTrue(Arrays.equals(names, dataset.getNestedRecordListSchemaNames()));
			assertEquals(names.length, dataset.getNestedRecordListSchemaSize());
		} catch (PropertySchemaDefineException e) {
			e.printStackTrace();
			fail("��O����");
		}
	}


	/**
	 * �l�X�g�������R�[�h���X�g�̃}�b�v���擾����e�X�g�B
	 * <p>
	 * �����F
	 * <ul>
	 * <li>Dataset#DataSet()�ŋ��DataSet�𐶐�����</li>
	 * <li>���̃X�L�[�}���w�肵��Dataset#setNestedRecordListSchema(String name, String
	 * schema)���R����s����(xx�̕�����3,1,6�̏��Ŏw�肷��)</li>
	 * <li>name : "test_nestlistxx"</li>
	 * <li>schema: ":Axx,java.lang.String\n:Bxx,int"</li>
	 * <li>getNestedRecordListSchemaMap()�����s���āA�l�X�g�������R�[�h���X�g�̃}�b�v���擾����</li>
	 * </ul>
	 * �m�F�F
	 * <ul>
	 * <li>�}�b�v�̓��e�i�L�[�̓��R�[�h���X�g���A�l�̓��R�[�h�X�L�[�}�j�������������؂���</li>
	 * </ul>
	 */
	public void testGetNestedRecordListSchemaMap() {
		try {
			DataSet dataset = new DataSet();
			
			String name = "test_nestlist3";
			String schema = ":A3,java.lang.String\n:B3,int";
			dataset.setNestedRecordListSchema(name, schema);
			
			name = "test_nestlist1";
			schema = ":A1,java.lang.String\n:B1,int";
			dataset.setNestedRecordListSchema(name, schema);
			
			name = "test_nestlist6";
			schema = ":A6,java.lang.String\n:B6,int";
			dataset.setNestedRecordListSchema(name, schema);
			
			//map���擾���ēo�^����Ă�����e�����؂���
			Map map = dataset.getNestedRecordListSchemaMap();
			assertEquals(":A3,java.lang.String\n:B3,int",
					map.get("test_nestlist3"));
			assertEquals(":A1,java.lang.String\n:B1,int",
					map.get("test_nestlist1"));
			assertEquals(":A6,java.lang.String\n:B6,int",
					map.get("test_nestlist6"));
		} catch (PropertySchemaDefineException e) {
			e.printStackTrace();
			fail("��O����");
		}
	}


	/**
	 * �l�X�g�������R�[�h���X�g�̃}�b�v���擾����e�X�g�B
	 * <p>
	 * �����F
	 * <ul>
	 * <li>Dataset#DataSet()�ŋ��DataSet�𐶐�����</li>
	 * <li>getNestedRecordListSchemaMap()�����s���āA�l�X�g�������R�[�h���X�g�̃}�b�v���擾����</li>
	 * </ul>
	 * �m�F�F
	 * <ul>
	 * <li>��̃}�b�v���Ԃ����</li>
	 * </ul>
	 */
	public void testGetNestedRecordListSchemaMapEmpty() {
		try {
			DataSet dataset = new DataSet();
			
			//map���擾���ēo�^����Ă�����e�����؂���
			Map map = dataset.getNestedRecordListSchemaMap();
			assertEquals(0,map.size());
		} catch (PropertySchemaDefineException e) {
			e.printStackTrace();
			fail("��O����");
		}
	}
	
	/**
	 * ���O�������Ȃ�Header��RecordList �̃X�L�[�}��ݒ肷��e�X�g�B
	 * <p>
	 * �����F
	 * <ul>
	 * <li>Dataset#DataSet()�ŋ��DataSet�𐶐�����</li>
	 * <li>���̃X�L�[�}���w�肵��Dataset#setSchema(String headerSchema, String
	 * recordListSchema)�����s����</li>
	 * <li>headerSchema ":A,java.lang.String,,,"</li>
	 * <li>recordListSchema ":B,java.lang.String,,,"</li>
	 * </ul>
	 * �m�F�F
	 * <ul>
	 * <li>Dataset#getHeader()�Ŗ��O�Ȃ���Header���擾�ł���B</li>
	 * <li>Dataset#getRecordList()�Ŗ��O�Ȃ���RecordList���擾�ł���B</li>
	 * </ul>
	 */
	public void testSetSchemaStringString() {
		try {
			DataSet dataset = new DataSet();
			String hschema = ":A,java.lang.String,,,";
			String rschema = ":B,java.lang.String,,,";
			dataset.setSchema(hschema, rschema);

			Header header = dataset.getHeader();
			assertEquals(hschema, header.schema);

			RecordList rlist = dataset.getRecordList();
			assertEquals(rschema, rlist.schema);
		} catch (PropertySchemaDefineException e) {
			e.printStackTrace();
			fail("��O����");
		}
	}

	/**
	 * ���O�������Ȃ�Header��RecordList �̃X�L�[�}��ݒ肷��e�X�g�B
	 * <p>
	 * �����F
	 * <ul>
	 * <li>Dataset#DataSet()�ŋ��DataSet�𐶐�����</li>
	 * <li>���̃X�L�[�}���w�肵��Dataset#setSchema(String headerSchema, String
	 * recordListSchema)�����s����</li>
	 * <li>headerSchema ""A,java.lang.String,,," �i�N���X��؂�̂Ȃ��s���Ȏw��j"</li>
	 * <li>recordListSchema ":B,java.lang.String,,,"</li>
	 * </ul>
	 * �m�F�F
	 * <ul>
	 * <li>��OPropertySchemaDefineException���������邱�Ƃ��m�F</li>
	 * </ul>
	 */
	public void testSetSchemaStringStringInvalid() {
		try {
			DataSet dataset = new DataSet();
			String hschema = "A,java.lang.String,,,";
			String rschema = ":B,java.lang.String,,,";
			dataset.setSchema(hschema, rschema);
			fail("��O���������Ȃ����߃e�X�g���s ");
		} catch (PropertySchemaDefineException e) {
		}
	}

	/**
	 * �w�肵�����O��Header��RecordList �̃X�L�[�}��ݒ肷��e�X�g�B
	 * <p>
	 * �����F
	 * <ul>
	 * <li>Dataset#DataSet()�ŋ��DataSet�𐶐�����</li>
	 * <li>���̃X�L�[�}���w�肵��Dataset#setSchema(String name,String headerSchema, String
	 * recordListSchema)�����s����</li>
	 * <li>name : "test_name"</li>
	 * <li>headerSchema ":A,java.lang.String,,,"</li>
	 * <li>recordListSchema ":B,java.lang.String,,,"</li>
	 * </ul>
	 * �m�F�F
	 * <ul>
	 * <li>Dataset#getHeader(String name)��Header���擾�ł���B</li>
	 * <li>Dataset#getRecordList(String name)��RecordList���擾�ł���B</li>
	 * </ul>
	 */
	public void testSetSchemaStringStringString() {
		try {
			DataSet dataset = new DataSet();
			String name = "test_name";
			String hschema = ":A,java.lang.String,,,";
			String rschema = ":B,java.lang.String,,,";
			dataset.setSchema(name, hschema, rschema);

			Header header = dataset.getHeader(name);
			assertEquals(hschema, header.schema);

			RecordList rlist = dataset.getRecordList(name);
			assertEquals(rschema, rlist.schema);
		} catch (PropertySchemaDefineException e) {
			e.printStackTrace();
			fail("��O����");
		}
	}

	/**
	 * �w�肵�����O��Header��RecordList �̃X�L�[�}��ݒ肷��e�X�g�B
	 * <p>
	 * �����F
	 * <ul>
	 * <li>Dataset#DataSet()�ŋ��DataSet�𐶐�����</li>
	 * <li>���̃X�L�[�}���w�肵��Dataset#setSchema(String name,String headerSchema, String
	 * recordListSchema)�����s����</li>
	 * <li>name : "test_name"</li>
	 * <li>headerSchema "A,java.lang.String,,," �i�N���X��؂�̂Ȃ��s���Ȏw��j</li>
	 * <li>recordListSchema ":B,java.lang.String,,,"</li>
	 * </ul>
	 * �m�F�F
	 * <ul>
	 * <li>��OPropertySchemaDefineException���������邱�Ƃ��m�F</li>
	 * </ul>
	 */
	public void testSetSchemaStringStringStringInvalid() {
		try {
			DataSet dataset = new DataSet();
			String name = "test_name";
			String hschema = "A,java.lang.String,,,";
			String rschema = ":B,java.lang.String,,,";
			dataset.setSchema(name, hschema, rschema);
			fail("��O���������Ȃ����߃e�X�g���s ");
		} catch (PropertySchemaDefineException e) {
		}
	}

	/**
	 * ��`���ꂽ���ɕ��񂾃w�b�_�[���z����擾����e�X�g�B
	 * <p>
	 * �����F
	 * <ul>
	 * <li>Dataset#DataSet()�ŋ��DataSet�𐶐�����</li>
	 * <li>���̃X�L�[�}���w�肵��Dataset#setHeaderSchema(String name, String
	 * schema)��2����s����</li>
	 * <li>name : "test_header1"</li>
	 * <li>":A,java.lang.String,,," </li>
	 * <li>name : "test_header2"</li>
	 * <li>":A,java.lang.String,,," </li>
	 * </ul>
	 * �m�F�F
	 * <ul>
	 * <li>Dataset#getHeaderNames()�Œ�`���ꂽ���Ŗ��O�̔z����擾�ł���B</li>
	 * </ul>
	 */
	public void testGetHeaderNames() {
		try {
			DataSet dataset = new DataSet();
			String hname = "test_header1";
			String schema = ":A,java.lang.String,,,";
			dataset.setHeaderSchema(hname, schema);
			hname = "test_header2";
			dataset.setHeaderSchema(hname, schema);
			assertEquals("test_header1", dataset.getHeaderNames()[0]);
			assertEquals("test_header2", dataset.getHeaderNames()[1]);
		} catch (PropertySchemaDefineException e) {
			e.printStackTrace();
			fail("��O����");
		}
	}

	/**
	 * �w�b�_�[�̐����擾����e�X�g�B
	 * <p>
	 * �����F
	 * <ul>
	 * <li>Dataset#DataSet()�ŋ��DataSet�𐶐�����</li>
	 * <li>���̃X�L�[�}���w�肵��Dataset#setHeaderSchema(String name, String
	 * schema)��2����s����</li>
	 * <li>name : "test_header1"</li>
	 * <li>":A,java.lang.String,,," </li>
	 * <li>name : "test_header2"</li>
	 * <li>":A,java.lang.String,,," </li>
	 * </ul>
	 * �m�F�F
	 * <ul>
	 * <li>Dataset#getHeaderSize()�Ńw�b�_�����擾�ł���B</li>
	 * </ul>
	 */
	public void testGetHeaderSize() {
		try {
			DataSet dataset = new DataSet();
			String hname = "test_header1";
			String schema = ":A,java.lang.String,,,";
			dataset.setHeaderSchema(hname, schema);
			hname = "test_header2";
			dataset.setHeaderSchema(hname, schema);
			assertEquals(2, dataset.getHeaderSize());
		} catch (PropertySchemaDefineException e) {
			e.printStackTrace();
			fail("��O����");
		}
	}

	/**
	 * �w�b�_�[��map���擾����e�X�g�B
	 * <p>
	 * �����F
	 * <ul>
	 * <li>Dataset#DataSet()�ŋ��DataSet�𐶐�����</li>
	 * <li>���̃X�L�[�}���w�肵��Dataset#setHeaderSchema(String name, String
	 * schema)��2����s����</li>
	 * <li>name : "test_header1"</li>
	 * <li>":A,java.lang.String,,," </li>
	 * <li>name : "test_header2"</li>
	 * <li>":A,java.lang.String,,," </li>
	 * </ul>
	 * �m�F�F
	 * <ul>
	 * <li>Dataset#getHeaderMap()�Ńw�b�_�[��map�i�L�[�̓w�b�_���j���擾�ł���B</li>
	 * </ul>
	 */
	public void testGetHeaderMap() {
		try {
			DataSet dataset = new DataSet();
			String hname = "test_header1";
			String schema = ":A,java.lang.String,,,";
			dataset.setHeaderSchema(hname, schema);
			hname = "test_header2";
			dataset.setHeaderSchema(hname, schema);

			assertEquals(dataset.getHeader("test_header1"), dataset
					.getHeaderMap().get("test_header1"));
			assertEquals(dataset.getHeader("test_header2"), dataset
					.getHeaderMap().get("test_header2"));
		} catch (PropertySchemaDefineException e) {
			e.printStackTrace();
			fail("��O����");
		}
	}

	/**
	 * �w�b�_�[��ǉ�����e�X�g�B
	 * <p>
	 * �����F
	 * <ul>
	 * <li>���̃w�b�_���ƃX�L�[�}���w�肵��Header(String name, String schema)�����s����</li>
	 * <li>name: "test_header"</li>
	 * <li>schema: ":A,java.lang.String,,,"</li>
	 * <li>Dataset#DataSet()�ŋ��DataSet�𐶐�����</li>
	 * <li>���̃X�L�[�}���w�肵��Dataset#addHeader(Header header)�����s����</li>
	 * </ul>
	 * �m�F�F
	 * <ul>
	 * <li>Dataset#getHeader(String name)��Header���擾�ł���B</li>
	 * </ul>
	 */
	public void testAddHeader() {
		try {
			String hname = "test_header";
			String schema = ":A,java.lang.String,,,";
			Header header = new Header(hname, schema);

			DataSet dataset = new DataSet();
			dataset.addHeader(header);
			Header header2 = dataset.getHeader(hname);
			assertEquals(schema, header2.schema);
		} catch (PropertySchemaDefineException e) {
			e.printStackTrace();
			fail("��O����");
		}
	}

	/**
	 * ��`���ꂽ���ɕ��񂾃��R�[�h���X�g���z����擾����e�X�g�B
	 * <p>
	 * �����F
	 * <ul>
	 * <li>Dataset#DataSet()�ŋ��DataSet�𐶐�����</li>
	 * <li>���̃X�L�[�}���w�肵��Dataset#setRecordListSchema(String name, String
	 * schema)��2����s����</li>
	 * <li>name : "test_rlist1"</li>
	 * <li>":A,java.lang.String,,," </li>
	 * <li>name : "test_rlist2"</li>
	 * <li>":A,java.lang.String,,," </li>
	 * </ul>
	 * �m�F�F
	 * <ul>
	 * <li>Dataset#getRecordListNames()�Œ�`���ꂽ���Ŗ��O�̔z����擾�ł���B</li>
	 * </ul>
	 */
	public void testGetRecordListNames() {
		try {
			DataSet dataset = new DataSet();
			String name = "test_rlist1";
			String schema = ":A,java.lang.String,,,";
			dataset.setRecordListSchema(name, schema);
			name = "test_rlist2";
			dataset.setRecordListSchema(name, schema);
			assertEquals("test_rlist1", dataset.getRecordListNames()[0]);
			assertEquals("test_rlist2", dataset.getRecordListNames()[1]);
		} catch (PropertySchemaDefineException e) {
			e.printStackTrace();
			fail("��O����");
		}
	}

	/**
	 * ���R�[�h���X�g�̐����擾����e�X�g�B
	 * <p>
	 * �����F
	 * <ul>
	 * <li>Dataset#DataSet()�ŋ��DataSet�𐶐�����</li>
	 * <li>���̃X�L�[�}���w�肵��Dataset#setRecordListSchema(String name, String
	 * schema)��2����s����</li>
	 * <li>name : "test_rlist1"</li>
	 * <li>":A,java.lang.String,,," </li>
	 * <li>name : "test_rlist2"</li>
	 * <li>":A,java.lang.String,,," </li>
	 * </ul>
	 * �m�F�F
	 * <ul>
	 * <li>Dataset#getRecordListSize()�Ń��R�[�h���X�g�̐����擾�ł���B</li>
	 * </ul>
	 */
	public void testGetRecordListSize() {
		try {
			DataSet dataset = new DataSet();
			String name = "test_rlist1";
			String schema = ":A,java.lang.String,,,";
			dataset.setRecordListSchema(name, schema);
			name = "test_rlist2";
			dataset.setRecordListSchema(name, schema);
			assertEquals(2, dataset.getRecordListSize());
		} catch (PropertySchemaDefineException e) {
			e.printStackTrace();
			fail("��O����");
		}
	}

	/**
	 * ���R�[�h���X�g��map���擾����e�X�g�B
	 * <p>
	 * �����F
	 * <ul>
	 * <li>Dataset#DataSet()�ŋ��DataSet�𐶐�����</li>
	 * <li>���̃X�L�[�}���w�肵��Dataset#setRecordListSchema(String name, String
	 * schema)��2����s����</li>
	 * <li>name : "test_rlist1"</li>
	 * <li>":A,java.lang.String,,," </li>
	 * <li>name : "test_rlist2"</li>
	 * <li>":A,java.lang.String,,," </li>
	 * </ul>
	 * �m�F�F
	 * <ul>
	 * <li>Dataset#getRecordListMap()�Ń��R�[�h���X�g��map�i�L�[�̓��R�[�h���X�g���j���擾�ł���B</li>
	 * </ul>
	 */
	public void testGetRecordListMap() {
		try {
			DataSet dataset = new DataSet();
			String name = "test_rlist1";
			String schema = ":A,java.lang.String,,,";
			dataset.setRecordListSchema(name, schema);
			name = "test_rlist2";
			dataset.setRecordListSchema(name, schema);

			assertEquals(dataset.getRecordList("test_rlist1"), dataset
					.getRecordListMap().get("test_rlist1"));
			assertEquals(dataset.getRecordList("test_rlist2"), dataset
					.getRecordListMap().get("test_rlist2"));
		} catch (PropertySchemaDefineException e) {
			e.printStackTrace();
			fail("��O����");
		}
	}

	/**
	 * ���R�[�h���X�g��ǉ�����e�X�g�B
	 * <p>
	 * �����F
	 * <ul>
	 * <li>���̃��R�[�h���X�g���ƃX�L�[�}���w�肵��RecordList(String name, String schema)�����s����</li>
	 * <li>name: "test_rlist"</li>
	 * <li>schema: ":A,java.lang.String,,,"</li>
	 * <li>Dataset#DataSet()�ŋ��DataSet�𐶐�����</li>
	 * <li>���̃X�L�[�}���w�肵��Dataset#addRecordList(RecordList recList)�����s����</li>
	 * </ul>
	 * �m�F�F
	 * <ul>
	 * <li>Dataset#getRecordList(String name)��RecordList���擾�ł���B</li>
	 * </ul>
	 */
	public void testAddRecordList() {
		try {
			String name = "test_rlist";
			String schema = ":A,java.lang.String,,,";
			RecordList rlist = new RecordList(name, schema);

			DataSet dataset = new DataSet();
			dataset.addRecordList(rlist);
			RecordList rlist2 = dataset.getRecordList(name);
			assertEquals(schema, rlist2.schema);
		} catch (PropertySchemaDefineException e) {
			e.printStackTrace();
			fail("��O����");
		}
	}


	/**
	 * �w�肵�����O�̃l�X�g����{@link RecordList ���R�[�h���X�g}�𐶐�����e�X�g�B
	 * <p>
	 * �����F
	 * <ul>
	 * <li>Dataset#DataSet()�ŋ��DataSet�𐶐�����</li>
	 * <li>���̃X�L�[�}���w�肵��Dataset#setNestedRecordListSchema(String name, String
	 * schema)���R����s����(xx�̕�����3,1,6�̏��Ŏw�肷��)</li>
	 * <li>name : "test_nestlistxx"</li>
	 * <li>schema: ":Axx,java.lang.String\n:Bxx,int"</li>
	 * <li>createNestedRecordList(String name)�����s���āA�l�X�g�������R�[�h���X�g���擾����</li>
	 * </ul>
	 * �m�F�F
	 * <ul>
	 * <li>���R�[�h���X�g�̓��e�i���O�A�X�L�[�}�j�������������؂���</li>
	 * </ul>
	 */
	public void testCreateNestedRecordList() {
		try {
			DataSet dataset = new DataSet();
			
			String name = "test_nestlist3";
			String schema = ":A3,java.lang.String\n:B3,int";
			dataset.setNestedRecordListSchema(name, schema);
			
			name = "test_nestlist1";
			schema = ":A1,java.lang.String\n:B1,int";
			dataset.setNestedRecordListSchema(name, schema);
			
			name = "test_nestlist6";
			schema = ":A6,java.lang.String\n:B6,int";
			dataset.setNestedRecordListSchema(name, schema);
			
			//���R�[�h���X�g���擾���āi���O�A�X�L�[�}�j�������������؂���
			RecordList rlist = dataset.createNestedRecordList("test_nestlist1");
			assertEquals("test_nestlist1",rlist.getName());
			assertEquals(":A1,java.lang.String\n:B1,int",rlist.getSchema());
		} catch (PropertySchemaDefineException e) {
			e.printStackTrace();
			fail("��O����");
		}
	}


	/**
	 * �w�肵�����O�̃l�X�g����{@link RecordList ���R�[�h���X�g}�𐶐�����e�X�g�B
	 * <p>
	 * �����F
	 * <ul>
	 * <li>Dataset#DataSet()�ŋ��DataSet�𐶐�����</li>
	 * <li>���̃X�L�[�}���w�肵��Dataset#setNestedRecordListSchema(String name, String
	 * schema)���R����s����(xx�̕�����3,1,6�̏��Ŏw�肷��)</li>
	 * <li>name : "test_nestlistxx"</li>
	 * <li>schema: ":Axx,java.lang.String\n:Bxx,int"</li>
	 * <li>�ݒ肵�����O�łȂ��l���w�肵��createNestedRecordList(String name)�����s</li>
	 * </ul>
	 * �m�F�F
	 * <ul>
	 * <li>null���Ԃ����</li>
	 * </ul>
	 */
	public void testCreateNestedRecordListNotExist() {
		try {
			DataSet dataset = new DataSet();
			
			String name = "test_nestlist3";
			String schema = ":A3,java.lang.String\n:B3,int";
			dataset.setNestedRecordListSchema(name, schema);
			
			name = "test_nestlist1";
			schema = ":A1,java.lang.String\n:B1,int";
			dataset.setNestedRecordListSchema(name, schema);
			
			name = "test_nestlist6";
			schema = ":A6,java.lang.String\n:B6,int";
			dataset.setNestedRecordListSchema(name, schema);
			
			//���݂��Ȃ����R�[�h���X�g���擾����
			assertNull(dataset.createNestedRecordList("test_nestlist"));
		} catch (PropertySchemaDefineException e) {
			e.printStackTrace();
			fail("��O����");
		}
	}


	/**
	 * �w�肵�����O�̃l�X�g����{@link RecordList ���R�[�h���X�g}�𐶐�����e�X�g�B
	 * <p>
	 * �����F
	 * <ul>
	 * <li>Dataset#DataSet()�ŋ��DataSet�𐶐�����</li>
	 * <li>createNestedRecordList(String name)�����s���āA�l�X�g�������R�[�h���X�g���擾����</li>
	 * </ul>
	 * �m�F�F
	 * <ul>
	 * <li>���R�[�h���X�g���o�^�Ȃ̂�null���Ԃ����</li>
	 * </ul>
	 */
	public void testCreateNestedRecordListNotExist2() {
		try {
			DataSet dataset = new DataSet();
			
			
			//���R�[�h���X�g���o�^�̏�ԂŃ��R�[�h���X�g���擾����
			assertNull(dataset.createNestedRecordList("test_nestlist"));
		} catch (PropertySchemaDefineException e) {
			e.printStackTrace();
			fail("��O����");
		}
	}


	
	/**
	 * �f�[�^�Z�b�g�̃w�b�_�[�̃f�[�^�ƃ��R�[�h���X�g�̃��R�[�h���폜����e�X�g�B
	 * <p>
	 * �����F
	 * <ul>
	 * <li>Dataset#DataSet()�ŋ��DataSet�𐶐�����</li>
	 * <li>���̃X�L�[�}���w�肵��Dataset#setSchema(String name,String headerSchema, String
	 * recordListSchema)�����s����</li>
	 * <li>name : "test_name"</li>
	 * <li>headerSchema ":A,java.lang.String,,,"</li>
	 * <li>���̒l���w�肵��setProperty(String name, Object val)�����s����</li>
	 * <li>name :"A"</li>
	 * <li>Object:"a"</li>
	 * <li>recordListSchema ":B,java.lang.String,,,"</li>
	 * <li>���R�[�h��ǉ����A���̒l���w�肵��setProperty(String name, Object val)�����s����</li>
	 * <li>name :"B"</li>
	 * <li>Object:"b"</li>
	 * 
	 * <li>Dataset#clear()�����s����</li>
	 * </ul>
	 * �m�F�F
	 * <ul>
	 * <li>�w�b�_�[�̒l�����݂��Ȃ�(Header#getProperty("A"))null��Ԃ�</li>
	 * <li>���R�[�h���X�g�̃��R�[�h��(rRecordList#size())��0��Ԃ�</li>
	 * </ul>
	 */
	public void testClear() {
		try {
			DataSet dataset = new DataSet();
			String name = "test_name";
			String hschema = ":A,java.lang.String,,,";
			String rschema = ":B,java.lang.String,,,";
			dataset.setSchema(name, hschema, rschema);

			Header header = dataset.getHeader(name);
			header.setProperty("A", "a");
			RecordList rlist = dataset.getRecordList(name);
			Record rec = rlist.createRecord();
			rec.setProperty("B", "b");
			rlist.addRecord(rec);

			dataset.clear();
			assertNull(header.getProperty("A"));
			assertEquals(0, rlist.size());
		} catch (PropertySchemaDefineException e) {
			e.printStackTrace();
			fail("��O����");
		}
	}

	/**
	 * �����X�L�[�}�������f�[�^�������Ȃ���̃f�[�^�Z�b�g�𕡐�����e�X�g�B
	 * <p>
	 * �����F
	 * <ul>
	 * <li>Dataset#DataSet()�ŋ��DataSet�𐶐�����</li>
	 * <li>���̃X�L�[�}���w�肵��Dataset#setSchema(String name,String headerSchema, String
	 * recordListSchema)�����s����</li>
	 * <li>name : "test_name"</li>
	 * <li>headerSchema ":A,java.lang.String,,,"</li>
	 * <li>���̒l���w�肵��setProperty(String name, Object val)�����s����</li>
	 * <li>name :"A"</li>
	 * <li>Object:"a"</li>
	 * <li>recordListSchema ":B,java.lang.String,,,"</li>
	 * <li>���R�[�h��ǉ����A���̒l���w�肵��setProperty(String name, Object val)�����s����</li>
	 * <li>name :"B"</li>
	 * <li>Object:"b"</li>
	 * 
	 * <li>Dataset#cloneSchema()�����s</li>
	 * </ul>
	 * �m�F�F
	 * <ul>
	 * <li>�����̃f�[�^�Z�b�g�ɑ΂��āA���̊m�F���s��</li>
	 * <li>Header�ArecordList�̃X�L�[�}���������Ɠ���</li>
	 * <li>Header�ArecordList�̃f�[�^�͑��݂��Ȃ�</li>
	 * </ul>
	 */
	public void testCloneSchema() {
		try {
			DataSet dataset = new DataSet();
			String name = "test_name";
			String hschema = ":A,java.lang.String,,,";
			String rschema = ":B,java.lang.String,,,";
			dataset.setSchema(name, hschema, rschema);

			Header header = dataset.getHeader(name);
			header.setProperty("A", "a");
			RecordList rlist = dataset.getRecordList(name);
			Record rec = rlist.createRecord();
			rec.setProperty("B", "b");
			rlist.addRecord(rec);

			DataSet dataset2 = dataset.cloneSchema();
			Header header2 = dataset2.getHeader(name);
			RecordList rlist2 = dataset2.getRecordList(name);

			assertEquals(header.schema, header2.schema);
			assertEquals(rlist.schema, rlist2.schema);
			assertNull(header2.values);
			assertEquals(0, rlist2.size());
		} catch (PropertySchemaDefineException e) {
			e.printStackTrace();
			fail("��O����");
		}
	}

	/**
	 * �f�[�^�Z�b�g�𕡐�����e�X�g�B
	 * <p>
	 * �����F
	 * <ul>
	 * <li>Dataset#DataSet()�ŋ��DataSet�𐶐�����</li>
	 * <li>���̃X�L�[�}���w�肵��Dataset#setSchema(String name,String headerSchema, String
	 * recordListSchema)�����s����</li>
	 * <li>name : "test_name"</li>
	 * <li>headerSchema ":A,java.lang.String,,,"</li>
	 * <li>���̒l���w�肵��setProperty(String name, Object val)�����s����</li>
	 * <li>name :"A"</li>
	 * <li>Object:"a"</li>
	 * <li>recordListSchema ":B,java.lang.String,,,"</li>
	 * <li>���R�[�h��ǉ����A���̒l���w�肵��setProperty(String name, Object val)�����s����</li>
	 * <li>name :"B"</li>
	 * <li>Object:"b"</li>
	 * 
	 * <li>Dataset#cloneDataSet()�����s</li>
	 * </ul>
	 * �m�F�F
	 * <ul>
	 * <li>�����̃f�[�^�Z�b�g�ɑ΂��āA���̊m�F���s��</li>
	 * <li>Header�ArecordList�̃X�L�[�}���������Ɠ���</li>
	 * <li>Header�ArecordList�̃f�[�^�͕������Ɠ���</li>
	 * </ul>
	 */
	public void testCloneDataSet() {
		try {
			DataSet dataset = new DataSet();
			String name = "test_name";
			String hschema = ":A,java.lang.String,,,";
			String rschema = ":B,java.lang.String,,,";
			dataset.setSchema(name, hschema, rschema);

			Header header = dataset.getHeader(name);
			header.setProperty("A", "a");
			RecordList rlist = dataset.getRecordList(name);
			Record rec = rlist.createRecord();
			rec.setProperty("B", "b");
			rlist.addRecord(rec);

			DataSet dataset2 = dataset.cloneDataSet();

			Header header2 = dataset2.getHeader(name);
			assertEquals(header.schema, header2.schema);
			assertEquals(header.getProperty("A"), header2.getProperty("A"));

			RecordList rlist2 = dataset2.getRecordList(name);
			 Record rec2 = rlist2.getRecord(0);
			 assertEquals(rlist.schema, rlist2.schema);
			 assertEquals(rec.getProperty("B"), rec2.getProperty("B"));

		} catch (PropertySchemaDefineException e) {
			e.printStackTrace();
			fail("��O����");
		}
	}


	/**
	 * �l�X�g�������R�[�h���X�g���܂ރf�[�^�Z�b�g�𕡐�����e�X�g�B
	 * <p>
	 * �����F
	 * <ul>
	 * <li>Dataset#DataSet()�ŋ��DataSet�𐶐�����</li>
	 * <li>���̃l�X�g�������R�[�h���X�g���܂ރX�L�[�}���w�肵��Dataset#setSchema(String name,String headerSchema, String
	 * recordListSchema)�����s����</li>
	 * <li>name : "test_name"</li>
	 * <li>headerSchema "LIST:HrList,\"HrList\""</li>
	 * <li>recordListSchema "LIST:RrList,\"RrList\"</li>
	 * <li>���R�[�h���X�g"HrList"�A"RrList"�ɂ��Ă͎��O��setNestedRecordListSchema�ŃX�L�[�}���`����K�v������</li>
	 * 
	 * <li>Dataset#cloneDataSet()�����s</li>
	 * </ul>
	 * �m�F�F
	 * <ul>
	 * <li>�����̃f�[�^�Z�b�g�ɑ΂��āA���̊m�F���s��</li>
	 * <li>Header�ArecordList�̃X�L�[�}���������Ɠ���</li>
	 * <li>Header�ArecordList�̃f�[�^�͕������Ɠ���</li>
	 * </ul>
	 */
	public void testCloneDataSetNestedRecordList() {
		try {
			DataSet dataset = new DataSet();
			//�l�X�g���郌�R�[�h���X�g�����
			dataset.setNestedRecordListSchema("HrList", ":A,java.lang.String\n:B,java.lang.String");
			dataset.setNestedRecordListSchema("RrList", ":C,java.lang.String\n:D,int");
			
			String name = "test_name";
			String hschema = "LIST:HrList,HrList";
			String rschema = "LIST:RrList,RrList";
			dataset.setSchema(name, hschema, rschema);

			//Header�̒l�Ƃ��Đݒ肷��l�X�g�������R�[�h���X�g���擾���Ēl��ݒ�
			RecordList HrList = dataset.createNestedRecordList("HrList");
			Record nrec1 = HrList.createRecord();
			nrec1.setProperty("A", "a");
			nrec1.setProperty("B", "b");
			HrList.addRecord(nrec1);
			//Header���擾���ăl�X�g�������R�[�h���X�g��l�ɐݒ�
			Header header = dataset.getHeader(name);
			header.setProperty("HrList", HrList);


			//���R�[�h�̒l�Ƃ��Đݒ肷��l�X�g�������R�[�h���X�g���擾���Ēl��ݒ�
			RecordList RrList = dataset.createNestedRecordList("RrList");
			Record nrec2 = RrList.createRecord();
			nrec2.setProperty("C", "c");
			nrec2.setProperty("D", 1);
			RrList.addRecord(nrec2);
			//RecordList���擾���ă��R�[�h�Ƀl�X�g�������R�[�h���X�g��l�ɐݒ�

			RecordList rlist = dataset.getRecordList(name);
			Record rec = rlist.createRecord();
			rec.setProperty("RrList", RrList);
			rlist.addRecord(rec);

			DataSet dataset2 = dataset.cloneDataSet();

			//�R�s�[���Header�̃l�X�g�������R�[�h���X�g�̓��e�������������؂���			
			Header header2 = dataset2.getHeader(name);
			assertEquals(header.schema, header2.schema);
			Record copyr =  ((RecordList)header2.getProperty("HrList")).getRecord(0);
			assertEquals(HrList.getSchema(), ((RecordList)header2.getProperty("HrList")).getSchema());
			assertEquals("a", copyr.get("A"));
			assertEquals("b", copyr.get("B"));

			//�R�s�[���RecordList�̃l�X�g�������R�[�h���X�g�̓��e�������������؂���
			RecordList rlist2 = dataset2.getRecordList(name);
			assertEquals(rlist.schema, rlist2.schema);
			Record rec2 = rlist2.getRecord(0);

			copyr =  ((RecordList)rec2.getProperty("RrList")).getRecord(0);
			assertEquals(RrList.getSchema(), ((RecordList)rec2.getProperty("RrList")).getSchema());
			assertEquals("c", copyr.getProperty("C"));
			assertEquals(1, copyr.getIntProperty("D"));

		} catch (PropertySchemaDefineException e) {
			e.printStackTrace();
			fail("��O����");
		}
	}


	/**
	 * �����X�L�[�}�������f�[�^�������Ȃ���̃f�[�^�Z�b�g�𕡐�����e�X�g�B
	 * <p>
	 * �����F
	 * <ul>
	 * <li>Dataset#DataSet()�ŋ��DataSet�𐶐�����</li>
	 * <li>���̃X�L�[�}���w�肵��Dataset#setSchema(String name,String headerSchema, String
	 * recordListSchema)�����s����</li>
	 * <li>name : "test_name"</li>
	 * <li>headerSchema ":A,java.lang.String,,,"</li>
	 * <li>recordListSchema ":A,java.lang.String,,,"</li>
	 * <li>���̒l���w�肵�ĊeHeader�ARecord��setProperty(String name, long val)�����s����</li>
	 * <li>name :"A"</li>
	 * <li>value:1</li>
	 * <li>Dataset#clone()�����s</li>
	 * </ul>
	 * �m�F�F
	 * <ul>
	 * <li>�����̃f�[�^�Z�b�g�ɑ΂��āA���̊m�F���s��</li>
	 * <li>Header�ArecordList�̃X�L�[�}���������Ɠ���</li>
	 * <li>Header�ArecordList�̃f�[�^�͑��݂��Ȃ�</li>
	 * </ul>
	 */
	public void testClone() {
		try {
			DataSet dataset = new DataSet();
			String name = "test_name";
			String hschema = ":A,java.lang.String,,,";
			String rschema = ":B,java.lang.String,,,";
			dataset.setSchema(name, hschema, rschema);

			Header header = dataset.getHeader(name);
			header.setProperty("A", "a");
			RecordList rlist = dataset.getRecordList(name);
			Record rec = rlist.createRecord();
			rec.setProperty("B", "b");
			rlist.addRecord(rec);

			DataSet dataset2 = (DataSet)dataset.cloneDataSet();

			Header header2 = dataset2.getHeader(name);
			assertEquals(header.schema, header2.schema);
			assertEquals(header.getProperty("A"), header2.getProperty("A"));

			RecordList rlist2 = dataset2.getRecordList(name);
			 Record rec2 = rlist2.getRecord(0);
			 assertEquals(rlist.schema, rlist2.schema);
			 assertEquals(rec.getProperty("B"), rec2.getProperty("B"));

		} catch (PropertySchemaDefineException e) {
			e.printStackTrace();
			fail("��O����");
		}
	}

	/**
	 * �f�[�^�Z�b�g�̕�����\�����擾����e�X�g�B
	 * <p>
	 * �����F
	 * <ul>
	 * <li>Dataset#DataSet("TEST_DATASET")�Ŗ��O�t����DataSet�𐶐�����</li>
	 * </ul>
	 * �m�F�F
	 * <ul>
	 * <li>Dataset#toString()��"{name=TEST_DATASET}"�ŏI��郁�b�Z�[�W��Ԃ��B</li>
	 * </ul>
	 */
	public void testToString() {
		DataSet dataset = new DataSet("TEST_DATASET");
		assertTrue(dataset.toString().endsWith("{name=TEST_DATASET}"));
	}

}
