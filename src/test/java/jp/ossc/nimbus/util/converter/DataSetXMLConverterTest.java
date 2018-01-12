package jp.ossc.nimbus.util.converter;

import java.io.*;
import jp.ossc.nimbus.beans.dataset.*;
import junit.framework.TestCase;
//
/**
 * 
 * @author S.Teshima
 * @version 1.00 �쐬: 2008/01/28 - S.Teshima
 */

public class DataSetXMLConverterTest extends TestCase {

	public DataSetXMLConverterTest(String arg0) {
		super(arg0);
	}

	public static void main(String[] args) {
		junit.textui.TestRunner.run(DataSetXMLConverterTest.class);
	}


	/**
	 * DataSetXMLConverter�C���X�^���X�𐶐�����e�X�g�B
	 * <p>
	 * �����F
	 * <ul>
	 * <li>DataSetXMLConverter()�����s����</li>
	 * </ul>
	 * �m�F�F
	 * <ul>
	 * <li>convertType��DATASET_TO_XML���ݒ肳��Ă��邱�Ƃ��m�F</li>
	 * </ul>
	 */
	public void testDataSetXMLConverter() {
		DataSetXMLConverter conv = new DataSetXMLConverter();
		assertEquals(DataSetXMLConverter.DATASET_TO_XML, conv.getConvertType());
	}


	/**
	 * �ϊ���ʂ��w�肵��DataSetXMLConverter�C���X�^���X�𐶐�����e�X�g�B
	 * <p>
	 * �����F
	 * <ul>
	 * <li>�ϊ���ʂ�XML_TO_DATASET���w�肵�āADataSetXMLConverter#DataSetXMLConverter()�����s����</li>
	 * </ul>
	 * �m�F�F
	 * <ul>
	 * <li>convertType��XML_TO_DATASET���ݒ肳��Ă��邱�Ƃ��m�F</li>
	 * </ul>
	 */
	public void testDataSetXMLConverterInt() {
		DataSetXMLConverter conv = new DataSetXMLConverter(DataSetXMLConverter.XML_TO_DATASET);
		assertEquals(DataSetXMLConverter.XML_TO_DATASET, conv.getConvertType());
	}


	/**
	 * �ϊ���ʂ�ݒ肷��e�X�g�B
	 * <p>
	 * �����F
	 * <ul>
	 * <li>�ϊ���ʂ�XML_TO_DATASET���w�肵�āADataSetXMLConverter#DataSetXMLConverter()�����s����</li>
	 * <li>�ϊ���ʂ�DATASET_TO_XML���w�肵�āADataSetXMLConverter#setConvertType(int type)�����s����</li>
	 * </ul>
	 * �m�F�F
	 * <ul>
	 * <li>convertType��DATASET_TO_XML���ݒ肳��Ă��邱�Ƃ��m�F</li>
	 * </ul>
	 */
	public void testSetConvertType() {
		DataSetXMLConverter conv = new DataSetXMLConverter(DataSetXMLConverter.XML_TO_DATASET);
		conv.setConvertType(DataSetXMLConverter.DATASET_TO_XML);
		assertEquals(DataSetXMLConverter.DATASET_TO_XML, conv.getConvertType());
	}



	/**
	 * �f�[�^�Z�b�g���ƃf�[�^�Z�b�g�̃}�b�s���O��ݒ肷��e�X�g�B
	 * <p>
	 * �����F
	 * <ul>
	 * <li>DataSet�ADataSetXMLConverter�̊e�C���X�^���X�𐶐�����</li>
	 * <li>�f�[�^�Z�b�g����"ds"�A�f�[�^�Z�b�g�ɐ��������C���X�^���X�����w�肵�āA<BR>
	 * DataSetXMLConverter#setDataSet(String name, DataSet dataSet)�����s����</li>
	 * </ul>
	 * �m�F�F
	 * <ul>
	 * <li>dataSetMap�Ɏw�肵���l���ݒ肳��Ă��邱�Ƃ��m�F</li>
	 * </ul>
	 */
	public void testSetDataSet() {
		DataSet ds = new DataSet();
		DataSetXMLConverter conv = new DataSetXMLConverter();
		conv.setDataSet("ds", ds);
		assertEquals(ds, conv.dataSetMap.get("ds"));
	}


	/**
	 * �X�L�[�}�����o�͂��邩�ǂ�����ݒ肷��e�X�g�B
	 * <p>
	 * �����F
	 * <ul>
	 * <li>DataSetXMLConverter()�����s����</li>
	 * <li>isOutput��true���w�肵�āADataSetXMLConverter#setOutputSchema()�����s����</li>
	 * </ul>
	 * �m�F�F
	 * <ul>
	 * <li>DataSetXMLConverter#isOutputSchema()��true�ł��邱�Ƃ��m�F</li>
	 * </ul>
	 */
	public void testSetOutputSchema() {
		DataSetXMLConverter conv = new DataSetXMLConverter();
		conv.setOutputSchema(true);
		assertTrue(conv.isOutputSchema());
	}


	/**
	 * �f�[�^�Z�b�g��XML�ϊ����Ɏg�p����XSL�t�@�C���̃p�X��ݒ肷��e�X�g�B
	 * <p>
	 * �����F
	 * <ul>
	 * <li>DataSetXMLConverter()�����s����</li>
	 * <li>path��"C:\XSL\Test.xsl"���w�肵�āADataSetXMLConverter#setXSLFilePath(String path)�����s����</li>
	 * </ul>
	 * �m�F�F
	 * <ul>
	 * <li>DataSetXMLConverter#getXSLFilePath()��"C:\XSL\Test.xsl"���Ԃ���邱�Ƃ��m�F</li>
	 * </ul>
	 */
	public void testSetXSLFilePath() {
		DataSetXMLConverter conv = new DataSetXMLConverter();
		conv.setXSLFilePath("C:\\XSL\\Test.xsl");
		assertEquals("C:\\XSL\\Test.xsl", conv.getXSLFilePath());
	}


	/**
	 * �f�[�^�Z�b�g��XML�ϊ����Ɏg�p���镶���G���R�[�f�B���O��ݒ肷��e�X�g�B
	 * <p>
	 * �����F
	 * <ul>
	 * <li>DataSetXMLConverter()�����s����</li>
	 * <li>�����G���R�[�f�B���O��"UTF-8"���w�肵�āADataSetXMLConverter#DataSetXMLConverter()�����s����</li>
	 * </ul>
	 * �m�F�F
	 * <ul>
	 * <li>DataSetXMLConverter#getCharacterEncodingToStream()��"UTF-8"���Ԃ���邱�Ƃ��m�F</li>
	 * </ul>
	 */
	public void testSetCharacterEncodingToStream() {
		DataSetXMLConverter conv = new DataSetXMLConverter();
		conv.setCharacterEncodingToStream("UTF-8");
		assertEquals("UTF-8", conv.getCharacterEncodingToStream());
	}


	/**
	 * XML���f�[�^�Z�b�gXML�ϊ����Ɏg�p���镶���G���R�[�f�B���O��ݒ肷��e�X�g�B
	 * <p>
	 * �����F
	 * <ul>
	 * <li>DataSetXMLConverter()�����s����</li>
	 * <li>�����G���R�[�f�B���O��"UTF-8"���w�肵�āADataSetXMLConverter#setCharacterEncodingToObject()�����s����</li>
	 * </ul>
	 * �m�F�F
	 * <ul>
	 * <li>DataSetXMLConverter#getCharacterEncodingToObject()��"UTF-8"���Ԃ���邱�Ƃ��m�F</li>
	 * </ul>
	 */
	public void testSetCharacterEncodingToObject() {
		DataSetXMLConverter conv = new DataSetXMLConverter();
		conv.setCharacterEncodingToObject("UTF-8");
		assertEquals("UTF-8", conv.getCharacterEncodingToObject());
	}

	
	/**
	 * �w�肵���I�u�W�F�N�g���f�[�^�Z�b�g��XML�ϊ�����e�X�g�B
	 * <p>
	 * �����F
	 * <ul>
	 * <li>Dataset#DataSet()�ŋ��DataSet�𐶐�����</li>
	 * <li>���̃X�L�[�}���w�肵��Dataset#setHeaderSchema(String name, String schema)�����s����</li>
	 * <li>name : "TestHeader"</li>
	 * <li>":A,java.util.Date,<BR>
	 * "jp.ossc.nimbus.util.converter.DateFormatConverter{ConvertType=2;Format="yyyy-MM-DD"}",<BR>
	 *  "jp.ossc.nimbus.util.converter.DateFormatConverter{ConvertType=1;Format="yyyy-MM-DD"}",<BR>
	 *  "@value@ != null"\n:B,java.lang.String,,,"</li>
	 * <li>�v���p�e�B�`��"2008-01-28"���w��</li>
	 * <li>�v���p�e�B�a��"TestValue"���w��</li>
	 * <li>�ϊ���ʂ�DATASET_TO_XML���w�肵�āADataSetXMLConverter#convert(Object obj)�����s����</li>
	 * <li>���������f�[�^�Z�b�g���w�肵�āADataSetXMLConverter#�����s����</li>
	 * </ul>
	 * �m�F�F
	 * <ul>
	 * <li>DataSetXMLConverter#conv.convert()�Ŏ��̓��e��XML�X�g���[�����Ԃ���邱�Ƃ��m�F�B<BR>
	 * <PRE>
	 * <?xml version="1.0" encoding="UTF-8"?>
	 *  <dataSet>
	 *   <schema>
	 *    <header name="TestHeader">
	 *     :A,java.util.Date,
	 *     "jp.ossc.nimbus.util.converter.DateFormatConverter{ConvertType=2;Format="yyyy-MM-DD"}",
	 *     "jp.ossc.nimbus.util.converter.DateFormatConverter{ConvertType=1;Format="yyyy-MM-DD"}",
	 *     "@value@ != null"
	 *     :B,java.lang.String,,,
	 *    </header>
	 *   </schema>
	 *    <header name="TestHeader"><A>2008-01-28</A><B>TestValue</B></header></dataSet>
	 * <PRE></li>
	 * </ul>
	 */
	public void testConvertToXML() {
		try {
			DataSet dataset = new DataSet();
			String hname = "TestHeader";
			String schema = ":A,java.util.Date," +
					"\"jp.ossc.nimbus.util.converter.DateFormatConverter{ConvertType=2;Format=\"yyyy-MM-DD\"}\"," +
							"\"jp.ossc.nimbus.util.converter.DateFormatConverter{ConvertType=1;Format=\"yyyy-MM-DD\"}\"," +
									"\"@value@ != null\"\n:B,java.lang.String,,,";
			dataset.setHeaderSchema(hname, schema);
            Header header = dataset.getHeader("TestHeader");
            header.setParseProperty("A", "2008-01-28");
            header.setProperty("B", "TestValue");
			
			DataSetXMLConverter conv = new DataSetXMLConverter(DataSetXMLConverter.DATASET_TO_XML);
			InputStream XmlStream = (InputStream)conv.convert(dataset);
			InputStreamReader reader = new InputStreamReader(XmlStream);
			BufferedReader br = new BufferedReader(reader);
			
			String s;
			StringBuffer sb = new StringBuffer();
			//���s���Ȃ�����������o��
			while ((s = br.readLine()) != null){
				sb.append(s);
			}
			br.close();
			
			assertTrue(sb.toString().endsWith(
					"<dataSet><schema><header name=\"TestHeader\">" +
					":A,java.util.Date," +
					"\"jp.ossc.nimbus.util.converter.DateFormatConverter{ConvertType=2;Format=\"yyyy-MM-DD\"}\"," +
							"\"jp.ossc.nimbus.util.converter.DateFormatConverter{ConvertType=1;Format=\"yyyy-MM-DD\"}\"," +
									"\"@value@ != null\":B,java.lang.String,,," +
									"</header></schema><header name=\"TestHeader\">" +
									"<A>2008-01-28</A><B>TestValue</B></header></dataSet>"));
		} catch (PropertySchemaDefineException e) {
			e.printStackTrace();
			fail("��O����");
		} catch (ConvertException e) {
			e.printStackTrace();
			fail("��O����");
		} catch (IOException e) {
			e.printStackTrace();
			fail("��O����");
		}

	}

	
	/**
	 * �w�肵���I�u�W�F�N�g���f�[�^�Z�b�g��XML�ϊ�����e�X�g�B
	 * <p>
	 * �����F
	 * <ul>
	 * <li>Dataset#DataSet()�ŋ��DataSet�𐶐�����</li>
	 * <li>���̃X�L�[�}���w�肵��Dataset#setHeaderSchema(String name, String schema)�����s����</li>
	 * <li>name : "TestHeader"</li>
	 * <li>":A,java.util.Date,<BR>
	 * "jp.ossc.nimbus.util.converter.DateFormatConverter{ConvertType=2;Format="yyyy-MM-DD"}",<BR>
	 *  "jp.ossc.nimbus.util.converter.DateFormatConverter{ConvertType=2;Format="yyyy-MM-DD"}",<BR>
	 *  "@value@ != null"\n:B,java.lang.String,,,\n:C,java.lang.String,,,"</li>
	 * <li>�v���p�e�B�`��"2008-01-28"���w��</li>
	 * <li>�v���p�e�B�a��Null���w��</li>
	 * <li>�v���p�e�B�b��"TestValue"���w��</li>
	 * <li>�ϊ���ʂ�DATASET_TO_XML���w�肵�āADataSetXMLConverter#convert(Object obj)�����s����</li>
	 * <li>���������f�[�^�Z�b�g���w�肵�āADataSetXMLConverter#�����s����</li>
	 * </ul>
	 * �m�F�F
	 * <ul>
	 * <li>DataSetXMLConverter#conv.convert()�Ŏ��̓��e��XML�X�g���[�����Ԃ���邱�Ƃ��m�F�B<BR>
	 * (null�w��̃v���p�e�B�͏o�͂���Ȃ����Ƃ��m�F����)
	 * <PRE>
	 * <?xml version="1.0" encoding="UTF-8"?>
	 *  <dataSet>
	 *   <schema>
	 *    <header name="TestHeader">
	 *     :A,java.util.Date,
	 *     "jp.ossc.nimbus.util.converter.DateFormatConverter{ConvertType=2;Format="yyyy-MM-DD"}",
	 *     "jp.ossc.nimbus.util.converter.DateFormatConverter{ConvertType=2;Format="yyyy-MM-DD"}",
	 *     "@value@ != null"
	 *     :B,java.lang.String,,,
	 *     :,java.lang.String,,,
	 *    </header>
	 *   </schema>
	 *    <header name="TestHeader"><A>2008-01-28</A><C>TestValue</C></header></dataSet>
	 * <PRE></li>
	 * </ul>
	 */
	public void testConvertToXMLNull() {
		try {
			DataSet dataset = new DataSet();
			String hname = "TestHeader";
			String schema = ":A,java.util.Date," +
					"\"jp.ossc.nimbus.util.converter.DateFormatConverter{ConvertType=2;Format=\"yyyy-MM-DD\"}\"," +
							"\"jp.ossc.nimbus.util.converter.DateFormatConverter{ConvertType=1;Format=\"yyyy-MM-DD\"}\"," +
									"\"@value@ != null\"\n:B,java.lang.String,,,\n:C,java.lang.String,,,";
			dataset.setHeaderSchema(hname, schema);
            Header header = dataset.getHeader("TestHeader");
            header.setParseProperty("A", "2008-01-28");
            header.setProperty("B", null);
            header.setProperty("C", "TestValue");
			
			DataSetXMLConverter conv = new DataSetXMLConverter(DataSetXMLConverter.DATASET_TO_XML);
			InputStream XmlStream = (InputStream)conv.convert(dataset);
			InputStreamReader reader = new InputStreamReader(XmlStream);
			BufferedReader br = new BufferedReader(reader);
			
			String s;
			StringBuffer sb = new StringBuffer();
			//���s���Ȃ�����������o��
			while ((s = br.readLine()) != null){
				sb.append(s);
			}
			br.close();
			
			assertTrue(sb.toString().endsWith(
					"<dataSet><schema><header name=\"TestHeader\">" +
					":A,java.util.Date," +
					"\"jp.ossc.nimbus.util.converter.DateFormatConverter{ConvertType=2;Format=\"yyyy-MM-DD\"}\"," +
							"\"jp.ossc.nimbus.util.converter.DateFormatConverter{ConvertType=1;Format=\"yyyy-MM-DD\"}\"," +
									"\"@value@ != null\":B,java.lang.String,,,:C,java.lang.String,,," +
									"</header></schema><header name=\"TestHeader\">" +
									"<A>2008-01-28</A><C>TestValue</C></header></dataSet>"));
		} catch (PropertySchemaDefineException e) {
			e.printStackTrace();
			fail("��O����");
		} catch (ConvertException e) {
			e.printStackTrace();
			fail("��O����");
		} catch (IOException e) {
			e.printStackTrace();
			fail("��O����");
		}

	}


	
	/**
	 * �w�肵���I�u�W�F�N�g���f�[�^�Z�b�g��XML�ϊ�����e�X�g�B
	 * <p>
	 * �����F
	 * <ul>
	 * <li>Dataset#DataSet()�ŋ��DataSet�𐶐�����</li>
	 * <li>���̃l�X�g�������R�[�h���X�g���܂ރX�L�[�}���w�肵��Dataset#setHeaderSchema(String name, String schema)�����s����</li>
	 * <li>name : "TestHeader"</li>
	 * <li>"LIST:HrList,\"HrList\""</li>
     * <li>�l�X�g�������R�[�h���X�g�̃X�L�[�}��":A,java.lang.String\n:B,java.lang.String"</li>
	 * <li>�v���p�e�B�`��"a"���w��</li>
	 * <li>�v���p�e�B�a��"b"���w��</li>
	 * <li>�ϊ���ʂ�DATASET_TO_XML���w�肵�āADataSetXMLConverter#convert(Object obj)�����s����</li>
	 * </ul>
	 * �m�F�F
	 * <ul>
	 * <li>DataSetXMLConverter#conv.convert()�Ŏ��̓��e��XML�X�g���[�����Ԃ���邱�Ƃ��m�F�B<BR>
	 * (null�w��̃v���p�e�B�͏o�͂���Ȃ����Ƃ��m�F����)
	 * <PRE>
	 * <?xml version="1.0" encoding="UTF-8"?>
	 *  <dataSet>
	 *   <schema>
	 *    <header name="TestHeader">
	 *     :A,java.util.Date,
	 *     "jp.ossc.nimbus.util.converter.DateFormatConverter{2,"yyyy-MM-DD"}",
	 *     "jp.ossc.nimbus.util.converter.DateFormatConverter{1,"yyyy-MM-DD"}",
	 *     "@value@ != null"
	 *     :B,java.lang.String,,,
	 *     :,java.lang.String,,,
	 *    </header>
	 *   </schema>
	 *    <header name="TestHeader"><A>2008-01-28</A><C>TestValue</C></header></dataSet>
	 * <PRE></li>
	 * </ul>
	 */
	public void testConvertToXMLWithHeaderNestedRecordList() {
		try {
			DataSet dataset = new DataSet();
            //�l�X�g���郌�R�[�h���X�g�����
            dataset.setNestedRecordListSchema("HrList", ":A,java.lang.String\n:B,java.lang.String");
			
			String hname = "TestHeader";
			String schema = "LIST:HrList,HrList";
			dataset.setHeaderSchema(hname, schema);
            //Header�̒l�Ƃ��Đݒ肷��l�X�g�������R�[�h���X�g���擾���Ēl��ݒ�
            RecordList HrList = dataset.createNestedRecordList("HrList");
            Record nrec1 = HrList.createRecord();
            nrec1.setProperty("A", "a");
            nrec1.setProperty("B", "b");
            HrList.addRecord(nrec1);
            //Header���擾���ăl�X�g�������R�[�h���X�g��l�ɐݒ�
            Header header = dataset.getHeader(hname);
            header.setProperty("HrList", HrList);
			
			DataSetXMLConverter conv = new DataSetXMLConverter(DataSetXMLConverter.DATASET_TO_XML);
			InputStream XmlStream = (InputStream)conv.convert(dataset);
			InputStreamReader reader = new InputStreamReader(XmlStream);
			BufferedReader br = new BufferedReader(reader);
			
			String s;
			StringBuffer sb = new StringBuffer();
			//���s���Ȃ�����������o��
			while ((s = br.readLine()) != null){
				sb.append(s);
			}
			br.close();
			
			assertTrue(sb.toString().endsWith(
                    "<header name=\"TestHeader\">LIST:HrList,HrList</header>" +
                    "<nestedRecordList name=\"HrList\">:A,java.lang.String:B,java.lang.String</nestedRecordList>" +
                    "</schema><header name=\"TestHeader\">" +
                    "<HrList><recordList name=\"HrList\"><record><A>a</A><B>b</B></record></recordList></HrList>" +
                    "</header></dataSet>"));
		} catch (PropertySchemaDefineException e) {
			e.printStackTrace();
			fail("��O����");
		} catch (ConvertException e) {
			e.printStackTrace();
			fail("��O����");
		} catch (IOException e) {
			e.printStackTrace();
			fail("��O����");
		}

	}


    
    /**
     * �w�肵���I�u�W�F�N�g���f�[�^�Z�b�g��XML�ϊ�����e�X�g�B
     * <p>
     * �����F
     * <ul>
     * <li>Dataset#DataSet()�ŋ��DataSet�𐶐�����</li>
     * <li>���̃l�X�g�������R�[�h���X�g���܂ރX�L�[�}���w�肵��Dataset#setHeaderSchema(String name, String schema)�����s����</li>
     * <li>name : "TestHeader"</li>
     * <li>"LIST:HrList,HrList\n:C,java.lang.String"</li>
     * <li>�����̃v���p�e�BC�ɂ�"c"��ݒ�</li>
     * <li>�l�X�g�������R�[�h���X�g�̃X�L�[�}��":A,java.lang.String\n:B,java.lang.String"</li>
     * <li>�v���p�e�B�`��"a"���w��</li>
     * <li>�v���p�e�B�a��"b"���w��</li>
     * <li>�ϊ���ʂ�DATASET_TO_XML���w�肵�āADataSetXMLConverter#convert(Object obj)�����s����</li>
     * </ul>
     * �m�F�F
     * <ul>
     * <li>DataSetXMLConverter#conv.convert()�Ő������̓��e��XML�X�g���[�����Ԃ���邱�Ƃ��m�F�B<BR>
     * (null�w��̃v���p�e�B�͏o�͂���Ȃ����Ƃ��m�F����)</li>
     * </ul>
     */
    public void testConvertToXMLWithHeaderNestedRecordList2() {
        try {
            DataSet dataset = new DataSet();
            //�l�X�g���郌�R�[�h���X�g�����
            dataset.setNestedRecordListSchema("HrList", ":A,java.lang.String\n:B,java.lang.String");
            
            String hname = "TestHeader";
            String schema = "LIST:HrList,HrList\n:C,java.lang.String";
            dataset.setHeaderSchema(hname, schema);
            //Header�̒l�Ƃ��Đݒ肷��l�X�g�������R�[�h���X�g���擾���Ēl��ݒ�
            RecordList HrList = dataset.createNestedRecordList("HrList");
            Record nrec1 = HrList.createRecord();
            nrec1.setProperty("A", "a");
            nrec1.setProperty("B", "b");
            HrList.addRecord(nrec1);
            //Header���擾���ăl�X�g�������R�[�h���X�g��l�ɐݒ�
            Header header = dataset.getHeader(hname);
            header.setProperty("HrList", HrList);
            header.setProperty("C", "c");
            
            DataSetXMLConverter conv = new DataSetXMLConverter(DataSetXMLConverter.DATASET_TO_XML);
            InputStream XmlStream = (InputStream)conv.convert(dataset);
            InputStreamReader reader = new InputStreamReader(XmlStream);
            BufferedReader br = new BufferedReader(reader);
            
            String s;
            StringBuffer sb = new StringBuffer();
            //���s���Ȃ�����������o��
            while ((s = br.readLine()) != null){
                sb.append(s);
            }
            br.close();
            
            assertTrue(sb.toString().endsWith("<dataSet><schema>" +
            		"<header name=\"TestHeader\">LIST:HrList,HrList:C,java.lang.String</header>" +
            		"<nestedRecordList name=\"HrList\">:A,java.lang.String:B,java.lang.String</nestedRecordList>" +
            		"</schema><header name=\"TestHeader\">" +
            		"<HrList><recordList name=\"HrList\"><record><A>a</A><B>b</B></record></recordList></HrList>" +
            		"<C>c</C></header></dataSet>"));
        } catch (PropertySchemaDefineException e) {
            e.printStackTrace();
            fail("��O����");
        } catch (ConvertException e) {
            e.printStackTrace();
            fail("��O����");
        } catch (IOException e) {
            e.printStackTrace();
            fail("��O����");
        }

    }


    
    /**
     * �w�肵���I�u�W�F�N�g���f�[�^�Z�b�g��XML�ϊ�����e�X�g�B
     * <p>
     * �����F
     * <ul>
     * <li>Dataset#DataSet()�ŋ��DataSet�𐶐�����</li>
     * <li>���̃l�X�g�������R�[�h���X�g���܂ރX�L�[�}���w�肵��Dataset#RecordListSchema(String name, String schema)�����s����</li>
     * <li>name : "TestRecList"</li>
     * <li>"LIST:RrList,HrList\n:C,java.lang.String"</li>
     * <li>�����̃v���p�e�BC�ɂ�"c"��ݒ�</li>
     * <li>�l�X�g�������R�[�h���X�g�̃X�L�[�}��":A,java.lang.String\n:B,java.lang.String"</li>
     * <li>�v���p�e�B�`��"a"���w��</li>
     * <li>�v���p�e�B�a��"b"���w��</li>
     * <li>�ϊ���ʂ�DATASET_TO_XML���w�肵�āADataSetXMLConverter#convert(Object obj)�����s����</li>
     * </ul>
     * �m�F�F
     * <ul>
     * <li>DataSetXMLConverter#conv.convert()�Ő������̓��e��XML�X�g���[�����Ԃ���邱�Ƃ��m�F�B<BR>
     * (null�w��̃v���p�e�B�͏o�͂���Ȃ����Ƃ��m�F����)</li>
     * </ul>
     */
    public void testConvertToXMLWithRecordListNestedRecordList() {
        try {
            DataSet dataset = new DataSet();
            //�l�X�g���郌�R�[�h���X�g�����
            dataset.setNestedRecordListSchema("RrList", ":A,java.lang.String\n:B,java.lang.String");
            
            String name = "TestRecList";
            String schema = "LIST:RrList,RrList\n:C,java.lang.String";
            dataset.setRecordListSchema(name, schema);
            //Record�̒l�Ƃ��Đݒ肷��l�X�g�������R�[�h���X�g���擾���Ēl��ݒ�
            RecordList RrList = dataset.createNestedRecordList("RrList");
            Record nrec1 = RrList.createRecord();
            nrec1.setProperty("A", "a");
            nrec1.setProperty("B", "b");
            RrList.addRecord(nrec1);
            //���R�[�h���X�g���擾���ăl�X�g�������R�[�h���X�g��l�ɐݒ�
            Record rec = dataset.getRecordList(name).createRecord();
            rec.setProperty("RrList", RrList);
            rec.setProperty("C", "c");
            dataset.getRecordList(name).addRecord(rec);
            
            DataSetXMLConverter conv = new DataSetXMLConverter(DataSetXMLConverter.DATASET_TO_XML);
            InputStream XmlStream = (InputStream)conv.convert(dataset);
            InputStreamReader reader = new InputStreamReader(XmlStream);
            BufferedReader br = new BufferedReader(reader);
            
            String s;
            StringBuffer sb = new StringBuffer();
            //���s���Ȃ�����������o��
            while ((s = br.readLine()) != null){
                sb.append(s);
            }
            br.close();
            
            assertTrue(sb.toString().endsWith("<dataSet><schema>" +
                    "<recordList name=\"TestRecList\">LIST:RrList,RrList:C,java.lang.String</recordList>" +
                    "<nestedRecordList name=\"RrList\">:A,java.lang.String:B,java.lang.String</nestedRecordList>" +
                    "</schema><recordList name=\"TestRecList\"><record><RrList><recordList name=\"RrList\">" +
                    "<record><A>a</A><B>b</B></record></recordList></RrList><C>c</C></record>" +
                    "</recordList></dataSet>"));
        } catch (PropertySchemaDefineException e) {
            e.printStackTrace();
            fail("��O����");
        } catch (ConvertException e) {
            e.printStackTrace();
            fail("��O����");
        } catch (IOException e) {
            e.printStackTrace();
            fail("��O����");
        }

    }

    
    /**
     * �w�肵���I�u�W�F�N�g���f�[�^�Z�b�g��XML�ϊ�����e�X�g�B
     * <p>
     * �����F
     * <ul>
     * <li>Dataset#DataSet()�ŋ��DataSet�𐶐�����</li>
     * <li>���̃l�X�g�������R�[�h���X�g���܂ރX�L�[�}���w�肵��Dataset#setHeaderSchema(String name, String schema)�����s����</li>
     * <li>name : "TestHeader"</li>
     * <li>"LIST:HrList,HrList\n:C,java.lang.String"</li>
     * <li>�����̃v���p�e�BC�ɂ͒l���w�肵�Ȃ�</li>
     * <li>�l�X�g�������R�[�h���X�g�̃X�L�[�}��":A,java.lang.String\n:B,java.lang.String"</li>
     * <li>�v���p�e�B�`��"a"���w��</li>
     * <li>�v���p�e�B�a�ɒl���w�肵�Ȃ�</li>
     * <li>���̃l�X�g�������R�[�h���X�g���܂ރX�L�[�}���w�肵��Dataset#RecordListSchema(String name, String schema)�����s����</li>
     * <li>name : "TestRecList"</li>
     * <li>"LIST:RrList,RrList\n:LC,java.lang.String"</li>
     * <li>�����̃v���p�e�BLC�ɂ͒l���w�肵�Ȃ�</li>
     * <li>�l�X�g�������R�[�h���X�g�̃X�L�[�}��":LA,java.lang.String\n:LB,int"</li>
     * <li>�v���p�e�BL�`��"�l���w�肵�Ȃ�</li>
     * <li>�v���p�e�BL�a��1���w��</li>
     * <li>�ϊ���ʂ�DATASET_TO_XML���w�肵�āADataSetXMLConverter#convert(Object obj)�����s����</li>
     * </ul>
     * �m�F�F
     * <ul>
     * <li>DataSetXMLConverter#conv.convert()�Ő������̓��e��XML�X�g���[�����Ԃ���邱�Ƃ��m�F�B<BR>
     * (null�w��̃v���p�e�B�͏o�͂���Ȃ����Ƃ��m�F����)</li>
     * </ul>
     */
    public void testConvertToXMLWithHeaderAndRecordListNestedRecordList() {
        try {
            DataSet dataset = new DataSet();
            //�l�X�g���郌�R�[�h���X�g�����
            dataset.setNestedRecordListSchema("HrList", ":A,java.lang.String\n:B,java.lang.String");
            dataset.setNestedRecordListSchema("RrList", ":LA,java.lang.String\n:LB,int");
            
            String name = "test_name";
            String hschema = "LIST:HrList,HrList\n:C,java.lang.String";
            String rschema = "LIST:RrList,RrList\n:LC,java.lang.String";
            dataset.setSchema(name, hschema, rschema);

            //Header�̒l�Ƃ��Đݒ肷��l�X�g�������R�[�h���X�g���擾���Ēl��ݒ�
            RecordList HrList = dataset.createNestedRecordList("HrList");
            Record nrec1 = HrList.createRecord();
            nrec1.setProperty("A", "a");
            HrList.addRecord(nrec1);
            //Header���擾���ăl�X�g�������R�[�h���X�g��l�ɐݒ�
            Header header = dataset.getHeader(name);
            header.setProperty("HrList", HrList);


            //���R�[�h�̒l�Ƃ��Đݒ肷��l�X�g�������R�[�h���X�g���擾���Ēl��ݒ�
            RecordList RrList = dataset.createNestedRecordList("RrList");
            Record nrec2 = RrList.createRecord();
            nrec2.setProperty("LB", 1);
            RrList.addRecord(nrec2);
            //RecordList���擾���ă��R�[�h�Ƀl�X�g�������R�[�h���X�g��l�ɐݒ�

            RecordList rlist = dataset.getRecordList(name);
            Record rec = rlist.createRecord();
            rec.setProperty("RrList", RrList);
            rlist.addRecord(rec);
            
            DataSetXMLConverter conv = new DataSetXMLConverter(DataSetXMLConverter.DATASET_TO_XML);
            InputStream XmlStream = (InputStream)conv.convert(dataset);
            InputStreamReader reader = new InputStreamReader(XmlStream);
            BufferedReader br = new BufferedReader(reader);
            
            String s;
            StringBuffer sb = new StringBuffer();
            //���s���Ȃ�����������o��
            while ((s = br.readLine()) != null){
                sb.append(s);
            }
            br.close();
            
            assertTrue(sb.toString().endsWith("<dataSet><schema>" +
            		"<header name=\"test_name\">LIST:HrList,HrList:C,java.lang.String</header>" +
            		"<recordList name=\"test_name\">LIST:RrList,RrList:LC,java.lang.String</recordList>" +
            		"<nestedRecordList name=\"HrList\">:A,java.lang.String:B,java.lang.String</nestedRecordList>" +
            		"<nestedRecordList name=\"RrList\">:LA,java.lang.String:LB,int</nestedRecordList></schema>" +
            		"<header name=\"test_name\"><HrList><recordList name=\"HrList\">" +
            		"<record><A>a</A></record></recordList></HrList></header><recordList name=\"test_name\"><record>" +
            		"<RrList><recordList name=\"RrList\"><record><LB>1</LB></record></recordList></RrList></record>" +
            		"</recordList></dataSet>"));
        } catch (PropertySchemaDefineException e) {
            e.printStackTrace();
            fail("��O����");
        } catch (ConvertException e) {
            e.printStackTrace();
            fail("��O����");
        } catch (IOException e) {
            e.printStackTrace();
            fail("��O����");
        }

    }


    
    /**
     * �w�肵���I�u�W�F�N�g���f�[�^�Z�b�g��XML�ϊ�����e�X�g�B
     * <p>
     * �����F
     * <ul>
     * <li>Dataset#DataSet()�ŋ��DataSet�𐶐�����</li>
     * <li>���̃l�X�g�������R�[�h���X�g���܂ރX�L�[�}���w�肵��Dataset#setHeaderSchema(String name, String schema)�����s����</li>
     * <li>name : "TestHeader"</li>
     * <li>"LIST:HrList,HrList\n:C,java.lang.String"</li>
     * <li>�����̃v���p�e�BC�ɂ�"c"��ݒ�</li>
     * <li>�l�X�g�������R�[�h���X�g�̃X�L�[�}��":A,java.lang.String\n:B,java.lang.String"</li>
     * <li>�v���p�e�B�`��"a"���w��</li>
     * <li>�v���p�e�B�a��"b"���w��</li>
     * <li>���̃l�X�g�������R�[�h���X�g���܂ރX�L�[�}���w�肵��Dataset#RecordListSchema(String name, String schema)�����s����</li>
     * <li>name : "TestRecList"</li>
     * <li>"LIST:RrList,RrList\n:LC,java.lang.String"</li>
     * <li>�����̃v���p�e�BC�ɂ�"c"��ݒ�</li>
     * <li>�l�X�g�������R�[�h���X�g�̃X�L�[�}��":LA,java.lang.String\n:LB,int"</li>
     * <li>�v���p�e�BL�`��"la"���w��</li>
     * <li>�v���p�e�BL�a��1���w��</li>
     * <li>�ϊ���ʂ�DATASET_TO_XML���w�肵�āADataSetXMLConverter#convert(Object obj)�����s����</li>
     * </ul>
     * �m�F�F
     * <ul>
     * <li>DataSetXMLConverter#conv.convert()�Ő������̓��e��XML�X�g���[�����Ԃ���邱�Ƃ��m�F�B<BR>
     * (null�w��̃v���p�e�B�͏o�͂���Ȃ����Ƃ��m�F����)</li>
     * </ul>
     */
    public void testConvertToXMLWithHeaderAndRecordListNestedRecordList2() {
        try {
            DataSet dataset = new DataSet();
            //�l�X�g���郌�R�[�h���X�g�����
            dataset.setNestedRecordListSchema("HrList", ":A,java.lang.String\n:B,java.lang.String");
            dataset.setNestedRecordListSchema("RrList", ":LA,java.lang.String\n:LB,int");
            
            String name = "test_name";
            String hschema = "LIST:HrList,HrList\n:C,java.lang.String";
            String rschema = "LIST:RrList,RrList\n:LC,java.lang.String";
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
            header.setProperty("C", "c");


            //���R�[�h�̒l�Ƃ��Đݒ肷��l�X�g�������R�[�h���X�g���擾���Ēl��ݒ�
            RecordList RrList = dataset.createNestedRecordList("RrList");
            Record nrec2 = RrList.createRecord();
            nrec2.setProperty("LA", "la");
            nrec2.setProperty("LB", 1);
            RrList.addRecord(nrec2);
            //RecordList���擾���ă��R�[�h�Ƀl�X�g�������R�[�h���X�g��l�ɐݒ�

            RecordList rlist = dataset.getRecordList(name);
            Record rec = rlist.createRecord();
            rec.setProperty("RrList", RrList);
            rec.setProperty("LC", "lc");            
            rlist.addRecord(rec);
            
            DataSetXMLConverter conv = new DataSetXMLConverter(DataSetXMLConverter.DATASET_TO_XML);
            InputStream XmlStream = (InputStream)conv.convert(dataset);
            InputStreamReader reader = new InputStreamReader(XmlStream);
            BufferedReader br = new BufferedReader(reader);
            
            String s;
            StringBuffer sb = new StringBuffer();
            //���s���Ȃ�����������o��
            while ((s = br.readLine()) != null){
                sb.append(s);
            }
            br.close();
            
            assertTrue(sb.toString().endsWith("<dataSet><schema>" +
            		"<header name=\"test_name\">LIST:HrList,HrList:C,java.lang.String</header>" +
            		"<recordList name=\"test_name\">LIST:RrList,RrList:LC,java.lang.String</recordList>" +
            		"<nestedRecordList name=\"HrList\">:A,java.lang.String:B,java.lang.String</nestedRecordList>" +
            		"<nestedRecordList name=\"RrList\">:LA,java.lang.String:LB,int</nestedRecordList>" +
            				"</schema><header name=\"test_name\"><HrList><recordList name=\"HrList\">" +
            				"<record><A>a</A><B>b</B></record></recordList></HrList><C>c</C></header>" +
            				"<recordList name=\"test_name\"><record><RrList><recordList name=\"RrList\">" +
            				"<record><LA>la</LA><LB>1</LB></record></recordList></RrList><LC>lc</LC></record>" +
            				"</recordList></dataSet>"));
        } catch (PropertySchemaDefineException e) {
            e.printStackTrace();
            fail("��O����");
        } catch (ConvertException e) {
            e.printStackTrace();
            fail("��O����");
        } catch (IOException e) {
            e.printStackTrace();
            fail("��O����");
        }

    }

    
    /**
     * �w�肵���I�u�W�F�N�g���f�[�^�Z�b�g��XML�ϊ�����e�X�g�B
     * <p>
     * �����F
     * <ul>
     * <li>Dataset#DataSet()�ŋ��DataSet�𐶐�����</li>
     * <li>���̃l�X�g�������R�[�h���X�g���܂ރX�L�[�}���w�肵��Dataset#setHeaderSchema(String name, String schema)�����s����</li>
     * <li>name : "TestHeader"</li>
     * <li>"LIST:HrList,HrList\n:C,java.lang.String"</li>
     * <li>�����̃v���p�e�BC�ɂ�"c"��ݒ�</li>
     * <li>�l�X�g�������R�[�h���X�g�̃X�L�[�}��":A,java.lang.String\n:B,java.lang.String"</li>
     * <li>�v���p�e�BHrList2�Ƀ��R�[�h���X�g���w��</li>
     * <li>�v���p�e�B�a��"b"���w��</li>
     * <li>���̃l�X�g�������R�[�h���X�g���܂ރX�L�[�}���w�肵��Dataset#RecordListSchema(String name, String schema)�����s����</li>
     * <li>name : "TestRecList"</li>
     * <li>"LIST:RrList,RrList\n:LC,java.lang.String"</li>
     * <li>�����̃v���p�e�BC�ɂ�"c"��ݒ�</li>
     * <li>�l�X�g�������R�[�h���X�g�̃X�L�[�}��":LA,java.lang.String\n:LB,int"</li>
     * <li>�v���p�e�BLRrList2��RrList2���w��</li>
     * <li>�v���p�e�BL�a��1���w��</li>
     * <li>�ϊ���ʂ�DATASET_TO_XML���w�肵�āADataSetXMLConverter#convert(Object obj)�����s����</li>
     * </ul>
     * �m�F�F
     * <ul>
     * <li>DataSetXMLConverter#conv.convert()�Ő������̓��e��XML�X�g���[�����Ԃ���邱�Ƃ��m�F�B<BR>
     * (null�w��̃v���p�e�B�͏o�͂���Ȃ����Ƃ��m�F����)</li>
     * </ul>
     */
    public void testConvertToXMLWithHeaderAndRecordListNestedRecordList3() {
        try {
            DataSet dataset = new DataSet();
            //�l�X�g���郌�R�[�h���X�g�Ƀl�X�g���郌�R�[�h���X�g�����
            dataset.setNestedRecordListSchema("HrList2", ":A,java.lang.String\n:B,int");
            dataset.setNestedRecordListSchema("RrList2", ":A,long\n:B,short");            

            //�l�X�g���郌�R�[�h���X�g�����
            dataset.setNestedRecordListSchema("HrList", "LIST:HrList2,HrList2\n:B,java.lang.String");
            dataset.setNestedRecordListSchema("RrList", "LIST:RrList2,RrList2\n:LB,int");            
            
            String name = "test_name";
            String hschema = "LIST:HrList,HrList\n:C,java.lang.String";
            String rschema = "LIST:RrList,RrList\n:LC,java.lang.String";
            dataset.setSchema(name, hschema, rschema);

            //Header�̒l�Ƃ��Đݒ肷��l�X�g�������R�[�h���X�g���擾���Ēl��ݒ�
            
            //�l�X�g�̃l�X�g
            RecordList HrList2 = dataset.createNestedRecordList("HrList2");
            Record nnrec1 = HrList2.createRecord();
            nnrec1.setProperty("A", "a");
            nnrec1.setProperty("B", 999);
            HrList2.addRecord(nnrec1);

            //Header�ɒl��ݒ�
            RecordList HrList = dataset.createNestedRecordList("HrList");
            Record nrec1 = HrList.createRecord();
            nrec1.setProperty("HrList2", HrList2);
            nrec1.setProperty("B", "b");
            HrList.addRecord(nrec1);
            //Header���擾���ăl�X�g�������R�[�h���X�g��l�ɐݒ�
            Header header = dataset.getHeader(name);
            header.setProperty("HrList", HrList);
            header.setProperty("C", "c");

            //���R�[�h�̒l�Ƃ��Đݒ肷��l�X�g�������R�[�h���X�g���擾���Ēl��ݒ�
            
            //�l�X�g�̃l�X�g
            RecordList RrList2 = dataset.createNestedRecordList("RrList2");
            Record nnrec2 = RrList2.createRecord();
            nnrec2.setProperty("A", (long)111);
            nnrec2.setProperty("B", (short)222);
            RrList2.addRecord(nnrec2);

            RecordList RrList = dataset.createNestedRecordList("RrList");
            Record nrec2 = RrList.createRecord();
            nrec2.setProperty("RrList2", RrList2);
            nrec2.setProperty("LB", 1);
            RrList.addRecord(nrec2);
            //RecordList���擾���ă��R�[�h�Ƀl�X�g�������R�[�h���X�g��l�ɐݒ�

            RecordList rlist = dataset.getRecordList(name);
            Record rec = rlist.createRecord();
            rec.setProperty("RrList", RrList);
            rec.setProperty("LC", "lc");            
            rlist.addRecord(rec);
            
            DataSetXMLConverter conv = new DataSetXMLConverter(DataSetXMLConverter.DATASET_TO_XML);
            InputStream XmlStream = (InputStream)conv.convert(dataset);
            InputStreamReader reader = new InputStreamReader(XmlStream);
            BufferedReader br = new BufferedReader(reader);
            
            String s;
            StringBuffer sb = new StringBuffer();
            //���s���Ȃ�����������o��
            while ((s = br.readLine()) != null){
                sb.append(s);
            }
            br.close();
            
            assertTrue(sb.toString().endsWith(
            		"<dataSet><schema><header name=\"test_name\">" +
            		"LIST:HrList,HrList:C,java.lang.String</header>" +
            		"<recordList name=\"test_name\">LIST:RrList,RrList:LC,java.lang.String</recordList>" +
            		"<nestedRecordList name=\"HrList2\">:A,java.lang.String:B,int</nestedRecordList>" +
            		"<nestedRecordList name=\"RrList2\">:A,long:B,short</nestedRecordList>" +
            		"<nestedRecordList name=\"HrList\">LIST:HrList2,HrList2:B,java.lang.String</nestedRecordList>" +
            		"<nestedRecordList name=\"RrList\">LIST:RrList2,RrList2:LB,int</nestedRecordList>" +
            		"</schema><header name=\"test_name\"><HrList><recordList name=\"HrList\">" +
            		"<record><HrList2><recordList name=\"HrList2\"><record><A>a</A><B>999</B></record>" +
            		"</recordList></HrList2><B>b</B></record></recordList></HrList><C>c</C></header>" +
            		"<recordList name=\"test_name\"><record><RrList><recordList name=\"RrList\">" +
            		"<record><RrList2><recordList name=\"RrList2\"><record><A>111</A><B>222</B></record>" +
            		"</recordList></RrList2><LB>1</LB></record></recordList></RrList><LC>lc</LC></record>" +
            		"</recordList></dataSet>"));
        } catch (PropertySchemaDefineException e) {
            e.printStackTrace();
            fail("��O����");
        } catch (ConvertException e) {
            e.printStackTrace();
            fail("��O����");
        } catch (IOException e) {
            e.printStackTrace();
            fail("��O����");
        }

    }

	
	/**
	 * �w�肵���I�u�W�F�N�g���f�[�^�Z�b�g��XML�ϊ�����e�X�g�B
	 * <p>
	 * �����F
	 * <ul>
	 * <li>Dataset#DataSet()�ŋ��DataSet�𐶐�����</li>
	 * <li>���̃X�L�[�}���w�肵��Dataset#setRecordListSchema(String name, String schema)�����s����</li>
	 * <li>name : "TestRs"</li>
	 * <li>":A,java.util.Date,<BR>
	 * "jp.ossc.nimbus.util.converter.DateFormatConverter{ConvertType=2;Format="yyyy-MM-DD"}",<BR>
	 *  "jp.ossc.nimbus.util.converter.DateFormatConverter{ConvertType=1;Format="yyyy-MM-DD"}",<BR>
	 *  "@value@ != null"\n:B,java.lang.String,,,"</li>
	 * <li>�v���p�e�B�`��"2008-01-28"���w��</li>
	 * <li>�v���p�e�B�a��"TestValue"���w��</li>
	 * <li>�ϊ���ʂ�DATASET_TO_XML���w�肵�āADataSetXMLConverter#convert(Object obj)�����s����</li>
	 * <li>���������f�[�^�Z�b�g���w�肵�āADataSetXMLConverter#�����s����</li>
	 * </ul>
	 * �m�F�F
	 * <ul>
	 * <li>DataSetXMLConverter#conv.convert()�Ŏ��̓��e��XML�X�g���[�����Ԃ���邱�Ƃ��m�F�B<BR>
	 * <PRE>
	 * <?xml version="1.0" encoding="UTF-8"?>
	 *  <dataSet>
	 *   <schema>
	 *    <recordList name="TestRs">
	 *     :A,java.util.Date,
	 *     "jp.ossc.nimbus.util.converter.DateFormatConverter{ConvertType=2;Format="yyyy-MM-DD"}",
	 *     "jp.ossc.nimbus.util.converter.DateFormatConverter{ConvertType=1;Format="yyyy-MM-DD"}",
	 *     "@value@ != null"
	 *     :B,java.lang.String,,,
	 *    </recordList>
	 *   </schema>
	 *    <recordList name="TestRs"><record><A>2008-01-28</A><B>TestValue</B></record></recordList></dataSet>
	 * <PRE></li>
	 * </ul>
	 */
	public void testConvertToXML2() {
		try {
			DataSet dataset = new DataSet();
			String name = "TestRs";
			String schema = ":A,java.util.Date," +
					"\"jp.ossc.nimbus.util.converter.DateFormatConverter{ConvertType=2;Format=\"yyyy-MM-DD\"}\"," +
							"\"jp.ossc.nimbus.util.converter.DateFormatConverter{ConvertType=1;Format=\"yyyy-MM-DD\"}\"," +
									"\"@value@ != null\"\n:B,java.lang.String,,,";
			dataset.setRecordListSchema(name, schema);
			RecordList rs = dataset.getRecordList("TestRs");
			Record rec = rs.createRecord();
            rec.setParseProperty("A", "2008-01-28");
            rec.setProperty("B", "TestValue");
            rs.addRecord(rec);
			
			DataSetXMLConverter conv = new DataSetXMLConverter(DataSetXMLConverter.DATASET_TO_XML);
			InputStream XmlStream = (InputStream)conv.convert(dataset);
			InputStreamReader reader = new InputStreamReader(XmlStream);
			BufferedReader br = new BufferedReader(reader);
			
			String s;
			StringBuffer sb = new StringBuffer();
			//���s���Ȃ�����������o��
			while ((s = br.readLine()) != null){
				sb.append(s);
			}
			br.close();
			
			assertTrue(sb.toString().endsWith(
					"<dataSet><schema><recordList name=\"TestRs\">" +
					":A,java.util.Date," +
					"\"jp.ossc.nimbus.util.converter.DateFormatConverter{ConvertType=2;Format=\"yyyy-MM-DD\"}\"," +
							"\"jp.ossc.nimbus.util.converter.DateFormatConverter{ConvertType=1;Format=\"yyyy-MM-DD\"}\"," +
									"\"@value@ != null\":B,java.lang.String,,," +
									"</recordList></schema><recordList name=\"TestRs\">" +
									"<record><A>2008-01-28</A><B>TestValue</B></record></recordList></dataSet>"));
		} catch (PropertySchemaDefineException e) {
			e.printStackTrace();
			fail("��O����");
		} catch (ConvertException e) {
			e.printStackTrace();
			fail("��O����");
		} catch (IOException e) {
			e.printStackTrace();
			fail("��O����");
		}

	}

	
	/**
	 * �w�肵���I�u�W�F�N�g��XML�ϊ�����e�X�g�B
	 * <p>
	 * �����F
	 * <ul>
	 * <li>�f�[�^�Z�b�g�ȊO�̃I�u�W�F�N�g(�����^)���w�肵�āADataSetXMLConverter#convert(Object obj)�����s����</li>
	 * </ul>
	 * �m�F�F
	 * <ul>
	 * <li>��OConvertException���������邱�Ƃ��m�F</li>
	 * <li>��O���b�Z�[�W��"Invalid input type : class java.lang.String"���Ԃ���邱�Ƃ��m�F</li>
	 * </ul>
	 */
	public void testConvertToXMLInvalid() {
		try {
			
			DataSetXMLConverter conv = new DataSetXMLConverter(DataSetXMLConverter.DATASET_TO_XML);
			conv.convert("ABC");
			fail("��O���������Ȃ����߃e�X�g���s ");
		} catch (ConvertException e) {
			assertEquals("Invalid input type : class java.lang.String", e.getMessage());
		}
	}


	
	/**
	 * �w�肵���I�u�W�F�N�g��XML���f�[�^�Z�b�g�ϊ�����e�X�g�B
	 * <p>
	 * �����F
	 * <ul>
	 * <li>���̓��e��XML�X�g���[���ƕϊ���ʂ�XML_TO_DATASET���w�肵�āA<BR>
	 * DataSetXMLConverter#convert(Object obj)�����s����<BR>
	 * <PRE>
	 * <?xml version="1.0" encoding="UTF-8"?>
	 *  <dataSet>
	 *   <schema>
	 *    <header name="TestHeader">
	 *     :A,java.util.Date,
	 *     "jp.ossc.nimbus.util.converter.DateFormatConverter{ConvertType=2;Format="yyyy-MM-DD"}",
	 *     "jp.ossc.nimbus.util.converter.DateFormatConverter{ConvertType=1;Format="yyyy-MM-DD"}",
	 *     "@value@ != null"
	 *     :B,java.lang.String,,,
	 *    </header>
	 *   </schema>
	 *    <header name="TestHeader"><A>2008-01-28</A><B>TestValue</B></header></dataSet>
	 * <PRE>
	 * </li>
	 * </ul>
	 * �m�F�F
	 * <ul>
	 * <li>�擾����DataSet�̃w�b�_�X�L�[�}���ϊ����̓��e�Ɠ��������Ƃ��m�F�B</li>
	 * <li>�擾����DataSet�̊e�v���p�e�B�̒l���ϊ����̓��e�Ɠ��������Ƃ��m�F�B</li>
	 * </ul>
	 */
	public void testConvertToDataSetFromInputStream() {
		try {
			String inxml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
			"<dataSet><schema><header name=\"TestHeader\">" +
			":A,java.util.Date," +
			"\"jp.ossc.nimbus.util.converter.DateFormatConverter{ConvertType=2;Format=\"yyyy-MM-DD\"}\"," +
					"\"jp.ossc.nimbus.util.converter.DateFormatConverter{ConvertType=1;Format=\"yyyy-MM-DD\"}\"," +
							"\"@value@ != null\"\n:B,java.lang.String,,," +
							"</header></schema><header name=\"TestHeader\">" +
							"<A>2008-01-28</A><B>TestValue</B></header></dataSet>";
			InputStream is = new ByteArrayInputStream(inxml.getBytes());
			
			DataSetXMLConverter conv = new DataSetXMLConverter(DataSetXMLConverter.XML_TO_DATASET);
			DataSet dataset = (DataSet)conv.convert(is);
			
			assertEquals("TestHeader",dataset.getHeader("TestHeader").getName());
			assertEquals(":A,java.util.Date," +
					"\"jp.ossc.nimbus.util.converter.DateFormatConverter{ConvertType=2;Format=\"yyyy-MM-DD\"}\"," +
					"\"jp.ossc.nimbus.util.converter.DateFormatConverter{ConvertType=1;Format=\"yyyy-MM-DD\"}\"," +
							"\"@value@ != null\"\n:B,java.lang.String,,,",dataset.getHeader("TestHeader").getSchema());
			assertEquals("2008-01-28",dataset.getHeader("TestHeader").getFormatProperty("A"));
			assertEquals("TestValue",dataset.getHeader("TestHeader").getProperty("B"));
			
		} catch (PropertySchemaDefineException e) {
			e.printStackTrace();
			fail("��O����");
		} catch (ConvertException e) {
			e.printStackTrace();
			fail("��O����");
		}
	}

	
	/**
	 * �w�肵���I�u�W�F�N�g��XML���f�[�^�Z�b�g�ϊ�����e�X�g�B
	 * <p>
	 * �����F
	 * <ul>
	 * <li>���̃l�X�g�������R�[�h���X�g���܂ޓ��e��XML�X�g���[���ƕϊ���ʂ�XML_TO_DATASET���w�肵�āA<BR>
	 * DataSetXMLConverter#convert(Object obj)�����s����<BR>
	 * <PRE>
     * <?xml version="1.0" encoding="UTF-8"?>
     * <dataSet>
     *     <schema>
     *         <header name="TestHeader">
     *             LIST:HrList,"HrList"
     *             :C,java.lang.String
     *         </header>
     *         <nestedRecordList name="HrList">
     *             :A,java.lang.String
     *             :B,java.lang.String
     *         </nestedRecordList>
     *     </schema>
     *     <header name="TestHeader">
     *         �@<recordList name="HrList">
     *             <A>a</A>
     *             <B>b</B>
     *         �@</recordList>
     *         <C>c</C>
     *     </header>
     * </dataSet>
	 * <PRE>
	 * </li>
	 * </ul>
	 * �m�F�F
	 * <ul>
	 * <li>�擾����DataSet�̃w�b�_�X�L�[�}���ϊ����̓��e�Ɠ��������Ƃ��m�F�B</li>
	 * <li>�擾����DataSet�̊e�v���p�e�B�̒l���ϊ����̓��e�Ɠ��������Ƃ��m�F�B</li>
	 * </ul>
	 */
	public void testConvertToDataSetFromInputStreamWithNestedRecordList() {
		try {
			String inxml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
			"<dataSet><schema><header name=\"TestHeader\">" +
			"LIST:HrList,HrList\n:C,java.lang.String</header>" +
							"<nestedRecordList name=\"HrList\">" +
							":A,java.lang.String\n:B,java.lang.String" +
							"</nestedRecordList></schema><header name=\"TestHeader\">" +
							"<HrList><recordList name=\"HrList\">" +
							"<record><A>a</A><B>b</B></record></recordList></HrList><C>c</C></header></dataSet>";
			InputStream is = new ByteArrayInputStream(inxml.getBytes());
			
			DataSetXMLConverter conv = new DataSetXMLConverter(DataSetXMLConverter.XML_TO_DATASET);
			DataSet dataset = (DataSet)conv.convert(is);
			
			assertEquals("TestHeader",dataset.getHeader("TestHeader").getName());
			assertEquals("LIST:HrList,HrList\n:C,java.lang.String",dataset.getHeader("TestHeader").getSchema());
            assertEquals("c",dataset.getHeader("TestHeader").getProperty("C"));
            //�l�X�g�������R�[�h���X�g�̓��e�������������؂���
            RecordList rlist = (RecordList)dataset.getHeader("TestHeader").getProperty("HrList");
            assertEquals(":A,java.lang.String\n:B,java.lang.String", rlist.getSchema());
            Record rec = rlist.getRecord(0);
            assertEquals("a", rec.getProperty("A"));
            assertEquals("b", rec.getProperty("B"));
			
		} catch (PropertySchemaDefineException e) {
			e.printStackTrace();
			fail("��O����");
		} catch (ConvertException e) {
			e.printStackTrace();
			fail("��O����");
		}
	}

    
    /**
     * �w�肵���I�u�W�F�N�g��XML���f�[�^�Z�b�g�ϊ�����e�X�g�B
     * <p>
     * �����F
     * <ul>
     * <li>���̃l�X�g�������R�[�h���X�g���܂ޓ��e��XML�X�g���[���ƕϊ���ʂ�XML_TO_DATASET���w�肵�āA<BR>
     * DataSetXMLConverter#convert(Object obj)�����s����<BR>
     * <PRE>
     * <dataSet>
     *     <schema>
     *         <recordList name="TestRecList">
     *             LIST:RrList,RrList
     *             :C,java.lang.String
     *         </recordList>
     *         <nestedRecordList name="RrList">
     *         :A,java.lang.String
     *         :B,java.lang.String
     *         </nestedRecordList>
     *     </schema>
     *     <recordList name="TestRecList">
     *         <record>
     *             <RrList>
     *                 <recordList name="RrList">
     *                     <record><A>a</A><B>b</B></record>
     *                 </recordList>
     *             </RrList>
     *             <C>c</C>
     *         </record>
     *     </recordList>
     * </dataSet>
     * <PRE>
     * </li>
     * </ul>
     * �m�F�F
     * <ul>
     * <li>�擾����DataSet�̃w�b�_�X�L�[�}���ϊ����̓��e�Ɠ��������Ƃ��m�F�B</li>
     * <li>�擾����DataSet�̊e�v���p�e�B�̒l���ϊ����̓��e�Ɠ��������Ƃ��m�F�B</li>
     * </ul>
     */
    public void testConvertToDataSetFromInputStreamWithNestedRecordList2() {
        try {
            String inxml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><dataSet><schema>" +
                    "<recordList name=\"TestRecList\">LIST:RrList,RrList\n:C,java.lang.String</recordList>" +
                    "<nestedRecordList name=\"RrList\">:A,java.lang.String\n:B,java.lang.String</nestedRecordList>" +
                    "</schema><recordList name=\"TestRecList\"><record><RrList><recordList name=\"RrList\">" +
                    "<record><A>a</A><B>b</B></record></recordList></RrList><C>c</C></record>" +
                    "</recordList></dataSet>";
            InputStream is = new ByteArrayInputStream(inxml.getBytes());
            
            DataSetXMLConverter conv = new DataSetXMLConverter(DataSetXMLConverter.XML_TO_DATASET);
            DataSet dataset = (DataSet)conv.convert(is);
            
            assertEquals("TestRecList",dataset.getRecordList("TestRecList").getName());
            assertEquals("LIST:RrList,RrList\n:C,java.lang.String",dataset.getRecordList("TestRecList").getSchema());
            assertEquals("c",dataset.getRecordList("TestRecList").getRecord(0).getProperty("C"));
            //�l�X�g�������R�[�h���X�g�̓��e�������������؂���
            RecordList rlist = (RecordList)dataset.getRecordList("TestRecList").getRecord(0).getProperty("RrList");
            assertEquals(":A,java.lang.String\n:B,java.lang.String", rlist.getSchema());
            Record rec = rlist.getRecord(0);
            assertEquals("a", rec.getProperty("A"));
            assertEquals("b", rec.getProperty("B"));
            
        } catch (PropertySchemaDefineException e) {
            e.printStackTrace();
            fail("��O����");
        } catch (ConvertException e) {
            e.printStackTrace();
            fail("��O����");
        }
    }

    
    /**
     * �w�肵���I�u�W�F�N�g��XML���f�[�^�Z�b�g�ϊ�����e�X�g�B
     * <p>
     * �����F
     * <ul>
     * <li>���̃l�X�g�������R�[�h���X�g���܂�Header�ARecordList���ݒ肳��Ă�����e��XML�X�g���[��<BR>
     * �ƕϊ���ʂ�XML_TO_DATASET���w�肵�āADataSetXMLConverter#convert(Object obj)�����s����<BR>
     * <PRE>
     * <dataSet>
     *  <schema>
     *      <header name="test_name">
     *          LIST:HrList,HrList
     *          :C,java.lang.String
     *      </header>
     *      <recordList name="test_name">
     *          LIST:RrList,RrList
     *          :LC,java.lang.String
     *      </recordList>
     *      <nestedRecordList name="HrList">
     *          :A,java.lang.String
     *          :B,java.lang.String
     *      </nestedRecordList>
     *      <nestedRecordList name="RrList">
     *          :LA,java.lang.String
     *          :LB,int
     *      </nestedRecordList>
     *  </schema>
     *  <header name="test_name">
     *      <HrList>
     *          <recordList name="HrList">
     *              <record><A>a</A><B>b</B></record>
     *          </recordList>
     *          <C>c</C>
     *      </HrList>
     *  </header>
     *  <recordList name="test_name">
     *      <record>
     *          <RrList>
     *              <recordList name="RrList">
     *                  <record><LA>la</LA><LB>1</LB></record>
     *              </recordList>
     *          </RrList>
     *          <LC>lc</LC>
     *      </record>
     *  </recordList>
     * </dataSet>
     * <PRE>
     * </li>
     * </ul>
     * �m�F�F
     * <ul>
     * <li>�擾����DataSet�̃w�b�_�X�L�[�}���ϊ����̓��e�Ɠ��������Ƃ��m�F�B</li>
     * <li>�擾����DataSet�̊e�v���p�e�B�̒l���ϊ����̓��e�Ɠ��������Ƃ��m�F�B</li>
     * </ul>
     */
    public void testConvertToDataSeWithHeaderAndRecordListtFromInputStreamWithNestedRecordList() {
        try {
            String inxml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><dataSet><schema>" +
                    "<header name=\"test_hname\">LIST:HrList,HrList\n:C,java.lang.String</header>" +
                    "<recordList name=\"test_rname\">LIST:RrList,RrList\n:LC,java.lang.String</recordList>" +
                    "<nestedRecordList name=\"HrList\">:A,java.lang.String\n:B,java.lang.String</nestedRecordList>" +
                    "<nestedRecordList name=\"RrList\">:LA,java.lang.String\n:LB,int</nestedRecordList>" +
                            "</schema><header name=\"test_hname\"><HrList><recordList name=\"HrList\">" +
                            "<record><A>a</A><B>b</B></record></recordList></HrList><C>c</C></header>" +
                            "<recordList name=\"test_rname\"><record><RrList><recordList name=\"RrList\">" +
                            "<record><LA>la</LA><LB>1</LB></record></recordList></RrList><LC>lc</LC></record>" +
                            "</recordList></dataSet>";
            InputStream is = new ByteArrayInputStream(inxml.getBytes());
            
            DataSetXMLConverter conv = new DataSetXMLConverter(DataSetXMLConverter.XML_TO_DATASET);
            DataSet dataset = (DataSet)conv.convert(is);
            
            assertEquals("test_hname",dataset.getHeader("test_hname").getName());
            assertEquals("LIST:HrList,HrList\n:C,java.lang.String",dataset.getHeader("test_hname").getSchema());
            assertEquals("c",dataset.getHeader("test_hname").getProperty("C"));
            //�l�X�g�������R�[�h���X�g�̓��e�������������؂���
            RecordList rlist = (RecordList)dataset.getHeader("test_hname").getProperty("HrList");
            assertEquals(":A,java.lang.String\n:B,java.lang.String", rlist.getSchema());
            Record rec = rlist.getRecord(0);
            assertEquals("a", rec.getProperty("A"));
            assertEquals("b", rec.getProperty("B"));
            
            assertEquals("test_rname",dataset.getRecordList("test_rname").getName());
            assertEquals("LIST:RrList,RrList\n:LC,java.lang.String",dataset.getRecordList("test_rname").getSchema());
            assertEquals("lc",dataset.getRecordList("test_rname").getRecord(0).getProperty("LC"));
            //�l�X�g�������R�[�h���X�g�̓��e�������������؂���
            rlist = (RecordList)dataset.getRecordList("test_rname").getRecord(0).getProperty("RrList");
            assertEquals(":LA,java.lang.String\n:LB,int", rlist.getSchema());
            rec = rlist.getRecord(0);
            assertEquals("la", rec.getProperty("LA"));
            assertEquals(1, rec.getIntProperty("LB"));
            
        } catch (PropertySchemaDefineException e) {
            e.printStackTrace();
            fail("��O����");
        } catch (ConvertException e) {
            e.printStackTrace();
            fail("��O����");
        }
    }


    
    /**
     * �w�肵���I�u�W�F�N�g��XML���f�[�^�Z�b�g�ϊ�����e�X�g�B
     * <p>
     * �����F
     * <ul>
     * <li>���̃l�X�g�������R�[�h���X�g���܂�Header�ARecordList���ݒ肳��Ă�����e��XML�X�g���[��<BR>
     * �ƕϊ���ʂ�XML_TO_DATASET���w�肵�āADataSetXMLConverter#convert(Object obj)�����s����<BR>
     * �ꕔ�̃v���p�e�B�̒l�𖢎w��ɂ���
     * <PRE>
     * <dataSet>
     *  <schema>
     *      <header name="test_name">
     *          LIST:HrList,HrList
     *          :C,java.lang.String
     *      </header>
     *      <recordList name="test_name">
     *          LIST:RrList,RrList
     *          :LC,java.lang.String
     *      </recordList>
     *      <nestedRecordList name="HrList">
     *          :A,java.lang.String
     *          :B,java.lang.String
     *      </nestedRecordList>
     *      <nestedRecordList name="RrList">
     *          :LA,java.lang.String
     *          :LB,int
     *      </nestedRecordList>
     *  </schema>
     *  <header name="test_name">
     *      <HrList>
     *          <recordList name="HrList">
     *              <record><A>a</A><B>b</B></record>
     *          </recordList>
     *      </HrList>
     *  </header>
     *  <recordList name="test_name">
     *      <record>
     *          <RrList>
     *              <recordList name="RrList">
     *                  <record><LB>1</LB></record>
     *              </recordList>
     *          </RrList>
     *          <LC>lc</LC>
     *      </record>
     *  </recordList>
     * </dataSet>
     * <PRE>
     * </li>
     * </ul>
     * �m�F�F
     * <ul>
     * <li>�擾����DataSet�̃w�b�_�X�L�[�}���ϊ����̓��e�Ɠ��������Ƃ��m�F�B</li>
     * <li>�擾����DataSet�̊e�v���p�e�B�̒l���ϊ����̓��e�Ɠ��������Ƃ��m�F�B</li>
     * <li>�l���w�肵�Ă��Ȃ��v���p�e�B�ɂ��Ă�null���Ԃ���邱�Ƃ��m�F</li>
     * </ul>
     */
    public void testConvertToDataSeWithHeaderAndRecordListtFromInputStreamWithNestedRecordList2() {
        try {
            String inxml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><dataSet><schema>" +
                    "<header name=\"test_hname\">LIST:HrList,HrList\n:C,java.lang.String</header>" +
                    "<recordList name=\"test_rname\">LIST:RrList,RrList\n:LC,java.lang.String</recordList>" +
                    "<nestedRecordList name=\"HrList\">:A,java.lang.String\n:B,java.lang.String</nestedRecordList>" +
                    "<nestedRecordList name=\"RrList\">:LA,java.lang.String\n:LB,int</nestedRecordList>" +
                            "</schema><header name=\"test_hname\"><HrList><recordList name=\"HrList\">" +
                            "<record><A>a</A><B>b</B></record></recordList></HrList></header>" +
                            "<recordList name=\"test_rname\"><record><RrList><recordList name=\"RrList\">" +
                            "<record><LB>1</LB></record></recordList></RrList><LC>lc</LC></record>" +
                            "</recordList></dataSet>";
            InputStream is = new ByteArrayInputStream(inxml.getBytes());
            
            DataSetXMLConverter conv = new DataSetXMLConverter(DataSetXMLConverter.XML_TO_DATASET);
            DataSet dataset = (DataSet)conv.convert(is);
            
            assertEquals("test_hname",dataset.getHeader("test_hname").getName());
            assertEquals("LIST:HrList,HrList\n:C,java.lang.String",dataset.getHeader("test_hname").getSchema());
            assertNull(dataset.getHeader("test_hname").getProperty("C"));
            //�l�X�g�������R�[�h���X�g�̓��e�������������؂���
            RecordList rlist = (RecordList)dataset.getHeader("test_hname").getProperty("HrList");
            assertEquals(":A,java.lang.String\n:B,java.lang.String", rlist.getSchema());
            Record rec = rlist.getRecord(0);
            assertEquals("a", rec.getProperty("A"));
            assertEquals("b", rec.getProperty("B"));
            
            assertEquals("test_rname",dataset.getRecordList("test_rname").getName());
            assertEquals("LIST:RrList,RrList\n:LC,java.lang.String",dataset.getRecordList("test_rname").getSchema());
            assertEquals("lc",dataset.getRecordList("test_rname").getRecord(0).getProperty("LC"));
            //�l�X�g�������R�[�h���X�g�̓��e�������������؂���
            rlist = (RecordList)dataset.getRecordList("test_rname").getRecord(0).getProperty("RrList");
            assertEquals(":LA,java.lang.String\n:LB,int", rlist.getSchema());
            rec = rlist.getRecord(0);
            assertNull(rec.getProperty("LA"));
            assertEquals(1, rec.getIntProperty("LB"));
            
        } catch (PropertySchemaDefineException e) {
            e.printStackTrace();
            fail("��O����");
        } catch (ConvertException e) {
            e.printStackTrace();
            fail("��O����");
        }
    }


    
    /**
     * �w�肵���I�u�W�F�N�g��XML���f�[�^�Z�b�g�ϊ�����e�X�g�B
     * <p>
     * �����F
     * <ul>
     * <li>���̂Q�K�w�l�X�g�������R�[�h���X�g���܂�Header�ARecordList���ݒ肳��Ă�����e��XML�X�g���[��<BR>
     * �ƕϊ���ʂ�XML_TO_DATASET���w�肵�āADataSetXMLConverter#convert(Object obj)�����s����<BR>
     * �ꕔ�̃v���p�e�B�̒l�𖢎w��ɂ���
     * <PRE>
     *  <dataSet>
     *      <schema>
     *          <header name="test_name">
     *              LIST:HrList,HrList
     *              :C,java.lang.String
     *          </header>
     *          <recordList name="test_name">
     *              LIST:RrList,RrList
     *              :LC,java.lang.String
     *          </recordList>
     *          <nestedRecordList name="HrList2">
     *              :A,java.lang.String
     *              :B,int
     *          </nestedRecordList>
     *          <nestedRecordList name="RrList2">
     *              :A,long
     *              :B,short
     *          </nestedRecordList>
     *          <nestedRecordList name="HrList">
     *              LIST:HrList2,HrList2
     *              :B,java.lang.String
     *          </nestedRecordList>
     *          <nestedRecordList name="RrList">
     *              LIST:RrList2,RrList2
     *              :LB,int
     *          </nestedRecordList>
     *      </schema>
     *      <header name="test_name">
     *          <HrList><recordList name="HrList"><record>
     *              <HrList2>
     *                  <recordList name="HrList2">
     *                      <record><A>a</A><B>999</B></record>
     *                  </recordList>
     *              </HrList2>
     *              <B>b</B>
     *          </record></recordList></HrList>
     *          <C>c</C>
     *      </header>
     *      <recordList name="test_name"><record>
     *          <RrList><recordList name="RrList"><record>
     *              <RrList2>
     *                  <recordList name="RrList2">
     *                      <record><A>111</A><B>222</B></record>
     *                  </recordList>
     *              </RrList2>
     *              <LB>1</LB>
     *          </record></recordList></RrList>
     *          <LC>lc</LC>
     *      </record></recordList>
     *  </dataSet>
     * <PRE>
     * </li>
     * </ul>
     * �m�F�F
     * <ul>
     * <li>�擾����DataSet�̃w�b�_�X�L�[�}���ϊ����̓��e�Ɠ��������Ƃ��m�F�B</li>
     * <li>�擾����DataSet�̊e�v���p�e�B�̒l���ϊ����̓��e�Ɠ��������Ƃ��m�F�B</li>
     * </ul>
     */
    public void testConvertToDataSeWithHeaderAndRecordListtFromInputStreamWithNestedRecordList3() {
        try {
            String inxml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" +
                    "<dataSet><schema><header name=\"test_hname\">" +
                    "LIST:HrList,HrList\n:C,java.lang.String</header>" +
                    "<recordList name=\"test_rname\">LIST:RrList,RrList\n:LC,java.lang.String</recordList>" +
                    "<nestedRecordList name=\"HrList2\">:A,java.lang.String\n:B,int</nestedRecordList>" +
                    "<nestedRecordList name=\"RrList2\">:A,long\n:B,short</nestedRecordList>" +
                    "<nestedRecordList name=\"HrList\">LIST:HrList2,HrList2\n:B,java.lang.String</nestedRecordList>" +
                    "<nestedRecordList name=\"RrList\">LIST:RrList2,RrList2\n:LB,int</nestedRecordList>" +
                    "</schema><header name=\"test_hname\"><HrList><recordList name=\"HrList\">" +
                    "<record><HrList2><recordList name=\"HrList2\"><record><A>a</A><B>999</B></record>" +
                    "</recordList></HrList2><B>b</B></record></recordList></HrList><C>c</C></header>" +
                    "<recordList name=\"test_rname\"><record><RrList><recordList name=\"RrList\">" +
                    "<record><RrList2><recordList name=\"RrList2\"><record><A>111</A><B>222</B></record>" +
                    "</recordList></RrList2><LB>1</LB></record></recordList></RrList><LC>lc</LC></record>" +
                    "</recordList></dataSet>";
            InputStream is = new ByteArrayInputStream(inxml.getBytes());
            
            DataSetXMLConverter conv = new DataSetXMLConverter(DataSetXMLConverter.XML_TO_DATASET);
            DataSet dataset = (DataSet)conv.convert(is);
            
            assertEquals("test_hname",dataset.getHeader("test_hname").getName());
            assertEquals("LIST:HrList,HrList\n:C,java.lang.String",dataset.getHeader("test_hname").getSchema());
            assertEquals("c",dataset.getHeader("test_hname").getProperty("C"));
            //�l�X�g�������R�[�h���X�g�̓��e�������������؂���
            RecordList rlist = (RecordList)dataset.getHeader("test_hname").getProperty("HrList");
            assertEquals("LIST:HrList2,HrList2\n:B,java.lang.String", rlist.getSchema());
            Record rec = rlist.getRecord(0);
            assertEquals("b", rec.getProperty("B"));
            //�Q�K�w���̃��R�[�h���X�g�`�F�b�N
            RecordList nrlist = (RecordList)rec.getProperty("HrList2");
            assertEquals(":A,java.lang.String\n:B,int", nrlist.getSchema());
            Record nrec = nrlist.getRecord(0);
            assertEquals("a", nrec.getProperty("A"));
            assertEquals(999, nrec.getIntProperty("B"));
            
            assertEquals("test_rname",dataset.getRecordList("test_rname").getName());
            assertEquals("LIST:RrList,RrList\n:LC,java.lang.String",dataset.getRecordList("test_rname").getSchema());
            assertEquals("lc",dataset.getRecordList("test_rname").getRecord(0).getProperty("LC"));
            //�l�X�g�������R�[�h���X�g�̓��e�������������؂���
            rlist = (RecordList)dataset.getRecordList("test_rname").getRecord(0).getProperty("RrList");
            assertEquals("LIST:RrList2,RrList2\n:LB,int", rlist.getSchema());
            rec = rlist.getRecord(0);
            assertEquals(1, rec.getIntProperty("LB"));

            //�Q�K�w���̃��R�[�h���X�g�`�F�b�N
            nrlist = (RecordList)rec.getProperty("RrList2");
            assertEquals(":A,long\n:B,short", nrlist.getSchema());
            nrec = nrlist.getRecord(0);
            assertEquals(111, nrec.getLongProperty("A"));
            assertEquals(222, nrec.getShortProperty("B"));
            
        } catch (PropertySchemaDefineException e) {
            e.printStackTrace();
            fail("��O����");
        } catch (ConvertException e) {
            e.printStackTrace();
            fail("��O����");
        }
    }



	
	/**
	 * �w�肵���I�u�W�F�N�g��XML���f�[�^�Z�b�g�ϊ�����e�X�g�B
	 * <p>
	 * �����F
	 * <ul>
	 * <li>���̓��e��XML�X�g���[���ƕϊ���ʂ�XML_TO_DATASET���w�肵�āA<BR>
	 * DataSetXMLConverter#convert(Object obj)�����s����<BR>
	 * <PRE>
	 * <?xml version="1.0" encoding="UTF-8"?>
	 *  <dataSet>
	 *   <schema>
	 *    <recordList name="TestRs">
	 *     :A,java.util.Date,
	 *     "jp.ossc.nimbus.util.converter.DateFormatConverter{ConvertType=2;Format="yyyy-MM-DD"}",
	 *     "jp.ossc.nimbus.util.converter.DateFormatConverter{ConvertType=1;Format="yyyy-MM-DD"}",
	 *     "@value@ != null"
	 *     :B,java.lang.String,,,
	 *    </recordList>
	 *   </schema>
	 *    <recordList name="TestRs"><record><A>2008-01-28</A><B>TestValue</B></record></recordList></dataSet>
	 * <PRE>
	 * </li>
	 * </ul>
	 * �m�F�F
	 * <ul>
	 * <li>�擾����DataSet�̃��R�[�h���X�g�X�L�[�}���ϊ����̓��e�Ɠ��������Ƃ��m�F�B<BR>
	 * <li>�擾����DataSet�̊e�v���p�e�B�̒l���ϊ����̓��e�Ɠ��������Ƃ��m�F�B<BR>
	 * </ul>
	 */
	public void testConvertToDataSetFromInputStream2() {
		try {
			String inxml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
			"<dataSet><schema><recordList name=\"TestRs\">" +
			":A,java.util.Date," +
			"\"jp.ossc.nimbus.util.converter.DateFormatConverter{ConvertType=2;Format=\"yyyy-MM-DD\"}\"," +
					"\"jp.ossc.nimbus.util.converter.DateFormatConverter{ConvertType=1;Format=\"yyyy-MM-DD\"}\"," +
							"\"@value@ != null\"\n:B,java.lang.String,,," +
							"</recordList></schema><recordList name=\"TestRs\">" +
							"<record><A>2008-01-28</A><B>TestValue</B></record></recordList></dataSet>";
			InputStream is = new ByteArrayInputStream(inxml.getBytes());
			
			DataSetXMLConverter conv = new DataSetXMLConverter(DataSetXMLConverter.XML_TO_DATASET);
			DataSet dataset = (DataSet)conv.convert(is);
			
			assertEquals("TestRs",dataset.getRecordList("TestRs").getName());
			assertEquals(":A,java.util.Date," +
					"\"jp.ossc.nimbus.util.converter.DateFormatConverter{ConvertType=2;Format=\"yyyy-MM-DD\"}\"," +
					"\"jp.ossc.nimbus.util.converter.DateFormatConverter{ConvertType=1;Format=\"yyyy-MM-DD\"}\"," +
							"\"@value@ != null\"\n:B,java.lang.String,,,",dataset.getRecordList("TestRs").getSchema());
			assertEquals("2008-01-28",dataset.getRecordList("TestRs").getRecord(0).getFormatProperty("A"));
			assertEquals("TestValue",dataset.getRecordList("TestRs").getRecord(0).getProperty("B"));
			
		} catch (PropertySchemaDefineException e) {
			e.printStackTrace();
			fail("��O����");
		} catch (ConvertException e) {
			e.printStackTrace();
			fail("��O����");
		}
	}
	

	
	/**
	 * �w�肵���I�u�W�F�N�g��XML���f�[�^�Z�b�g�ϊ�����e�X�g�B
	 * <p>
	 * �����F
	 * <ul>
	 * <li>���̓��e��XML�t�@�C���ƕϊ���ʂ�XML_TO_DATASET���w�肵�āA<BR>
	 * DataSetXMLConverter#convert(Object obj)�����s����<BR>
	 * <PRE>
	 * <?xml version="1.0" encoding="UTF-8"?>
	 *  <dataSet>
	 *   <schema>
	 *    <header name="TestHeader">
	 *     :A,java.util.Date,
	 *     "jp.ossc.nimbus.util.converter.DateFormatConverter{ConvertType=2;Format="yyyy-MM-DD"}",
	 *     "jp.ossc.nimbus.util.converter.DateFormatConverter{ConvertType=1;Format="yyyy-MM-DD"}",
	 *     "@value@ != null"
	 *     :B,java.lang.String,,,
	 *    </header>
	 *   </schema>
	 *    <header name="TestHeader"><A>2008-01-28</A><B>TestValue</B></header></dataSet>
	 * <PRE></li>
	 * </li>
	 * </ul>
	 * �m�F�F
	 * <ul>
	 * <li>�擾����DataSet�̃w�b�_�X�L�[�}���ϊ����̓��e�Ɠ��������Ƃ��m�F�B<BR>
	 * <li>�擾����DataSet�̊e�v���p�e�B�̒l���ϊ����̓��e�Ɠ��������Ƃ��m�F�B<BR>
	 * </ul>
	 */
	public void testConvertToDataSetFromFile() {
		try {
			File xmlf = new File("src/test/resources/jp/ossc/nimbus/util/converter/DataSetXMLConverterTest.xml");
			DataSetXMLConverter conv = new DataSetXMLConverter(DataSetXMLConverter.XML_TO_DATASET);
			DataSet dataset = (DataSet)conv.convert(xmlf);
			
			assertEquals("TestHeader",dataset.getHeader("TestHeader").getName());
			assertEquals(":A,java.util.Date," +
					"\"jp.ossc.nimbus.util.converter.DateFormatConverter{ConvertType=2;Format=\"yyyy-MM-DD\"}\"," +
					"\"jp.ossc.nimbus.util.converter.DateFormatConverter{ConvertType=1;Format=\"yyyy-MM-DD\"}\"," +
							"\"@value@ != null\"\n:B,java.lang.String,,,",dataset.getHeader("TestHeader").getSchema());
			assertEquals("2008-01-28",dataset.getHeader("TestHeader").getFormatProperty("A"));
			assertEquals("TestValue",dataset.getHeader("TestHeader").getProperty("B"));
			
		} catch (PropertySchemaDefineException e) {
			e.printStackTrace();
			fail("��O����");
		} catch (ConvertException e) {
			e.printStackTrace();
			fail("��O����");
		}

	}

	
	/**
	 * �w�肵���I�u�W�F�N�g��XML�ϊ�����e�X�g�B
	 * <p>
	 * �����F
	 * <ul>
	 * <li>�f�[�^�Z�b�g�ȊO�̃I�u�W�F�N�g(�����^)���w�肵�āADataSetXMLConverter#convertToStream(Object obj)�����s����</li>
	 * </ul>
	 * �m�F�F
	 * <ul>
	 * <li>��OConvertException���������邱�Ƃ��m�F</li>
	 * <li>��O���b�Z�[�W��"Invalid input type : class java.lang.String"���Ԃ���邱�Ƃ��m�F</li>
	 * </ul>
	 * </ul>
	 */
	public void testConvertToDataSetIvalid() {
		try {
			
			DataSetXMLConverter conv = new DataSetXMLConverter(DataSetXMLConverter.XML_TO_DATASET);
			conv.convert("ABC");
			fail("��O���������Ȃ����߃e�X�g���s ");
		} catch (ConvertException e) {
			assertEquals("Invalid input type : class java.lang.String", e.getMessage());
		}
	}

	
	
	/**
	 * �w�肵���I�u�W�F�N�g��XML���f�[�^�Z�b�g�ϊ�����e�X�g�B
	 * <p>
	 * �����F
	 * <ul>
	 * <li>null�ƕϊ���ʂ�XML_TO_DATASET���w�肵�āA<BR>
	 * DataSetXMLConverter#convert(Object obj)�����s����<BR></li>
	 * </ul>
	 * �m�F�F
	 * <ul>
	 * <li>null���Ԃ��Ă��邱�Ƃ��m�F�B<BR>
	 * </ul>
	 */
	public void testConvertToObjectNull() {
		try {			
			DataSetXMLConverter conv = new DataSetXMLConverter(DataSetXMLConverter.XML_TO_DATASET);
			assertNull(conv.convert(null));
			
		} catch (ConvertException e) {
			e.printStackTrace();
			fail("��O����");
		}
	}
	
	/**
	 * �w�肵���I�u�W�F�N�g��XML���f�[�^�Z�b�g�ϊ�����e�X�g�B
	 * <p>
	 * �����F
	 * <ul>
	 * <li>�ϊ���ʂ�999(�s���Ȓl)���w�肵�āA<BR>
	 * DataSetXMLConverter#convert(Object obj)�����s����<BR></li>
	 * </ul>
	 * �m�F�F
	 * <ul>
	 * <li>��OConvertException���������邱�Ƃ��m�F</li>
	 * <li>��O���b�Z�[�W��"Invalid convert type : 999"���Ԃ���邱�Ƃ��m�F</li>
	 * </ul>
	 */
	public void testConvertToObjectIvalid() {
		try {			
			String inxml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
			"<dataSet><schema><header name=\"TestHeader\">" +
			":A,java.util.Date," +
			"\"jp.ossc.nimbus.util.converter.DateFormatConverter{ConvertType=2;Format=\"yyyy-MM-DD\"}\"," +
					"\"jp.ossc.nimbus.util.converter.DateFormatConverter{ConvertType=1;Format=\"yyyy-MM-DD\"}\"," +
							"\"@value@ != null\"\n:B,java.lang.String,,," +
							"</header></schema><header name=\"TestHeader\">" +
							"<A>2008-01-28</A><B>TestValue</B></header></dataSet>";
			InputStream is = new ByteArrayInputStream(inxml.getBytes());
			
			DataSetXMLConverter conv = new DataSetXMLConverter(999);
			conv.convert(is);
			fail("��O���������Ȃ����߃e�X�g���s ");
		} catch (ConvertException e) {
			assertEquals("Invalid convert type : 999", e.getMessage());
		}
	}
	
	/**
	 * �w�肵���I�u�W�F�N�g��XML�ϊ�����e�X�g�B
	 * <p>
	 * �����F
	 * <ul>
	 * <li>�f�[�^�Z�b�g�ȊO�̃I�u�W�F�N�g(�����^)���w�肵�āADataSetXMLConverter#convertToStream(Object obj)�����s����</li>
	 * </ul>
	 * �m�F�F
	 * <ul>
	 * <li>��OConvertException���������邱�Ƃ��m�F</li>
	 * <li>��O���b�Z�[�W��"Invalid input type : class java.lang.String"���Ԃ���邱�Ƃ��m�F</li>
	 * </ul>
	 */
	public void testConvertToStreamInvalid() {
		try {
			
			DataSetXMLConverter conv = new DataSetXMLConverter(DataSetXMLConverter.DATASET_TO_XML);
			conv.convertToStream("ABC");
			fail("��O���������Ȃ����߃e�X�g���s ");
		} catch (ConvertException e) {
			assertEquals("Invalid input type : class java.lang.String", e.getMessage());
		}
	}

	
	/**
	 * �w�肵���I�u�W�F�N�g��XML���f�[�^�Z�b�g�ϊ�����e�X�g�B
	 * <p>
	 * �����F
	 * <ul>
	 * <li>���̓��e��XML�X�g���[���ƕϊ���ʂ�XML_TO_DATASET���w�肵�āA<BR>
	 * DataSetXMLConverter#convertToObject(Object obj)�����s����<BR>
	 * <PRE>
	 * <?xml version="1.0" encoding="UTF-8"?>
	 *  <dataSet>
	 *   <schema>
	 *    <header name="TestHeader">
	 *     :A,java.util.Date,
	 *     "jp.ossc.nimbus.util.converter.DateFormatConverter{ConvertType=2;Format="yyyy-MM-DD"}",
	 *     "jp.ossc.nimbus.util.converter.DateFormatConverter{ConvertType=1;Format="yyyy-MM-DD"}",
	 *     "@value@ != null"
	 *     :B,java.lang.String,,,
	 *    </header>
	 *   </schema>
	 *    <header name="TestHeader"><A>2008-01-28</A><B>TestValue</B></header></dataSet>
	 * <PRE></li>
	 * </li>
	 * </ul>
	 * �m�F�F
	 * <ul>
	 * <li>�擾����DataSet�̃w�b�_�X�L�[�}���ϊ����̓��e�Ɠ��������Ƃ��m�F�B<BR>
	 * <li>�擾����DataSet�̊e�v���p�e�B�̒l���ϊ����̓��e�Ɠ��������Ƃ��m�F�B<BR>
	 * </ul>
	 */
	public void testconvertToObjectFromInputStream() {
		try {
			String inxml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
			"<dataSet><schema><header name=\"TestHeader\">" +
			":A,java.util.Date," +
			"\"jp.ossc.nimbus.util.converter.DateFormatConverter{ConvertType=2;Format=\"yyyy-MM-DD\"}\"," +
					"\"jp.ossc.nimbus.util.converter.DateFormatConverter{ConvertType=1;Format=\"yyyy-MM-DD\"}\"," +
							"\"@value@ != null\"\n:B,java.lang.String,,," +
							"</header></schema><header name=\"TestHeader\">" +
							"<A>2008-01-28</A><B>TestValue</B></header></dataSet>";
			InputStream is = new ByteArrayInputStream(inxml.getBytes());
			
			DataSetXMLConverter conv = new DataSetXMLConverter(DataSetXMLConverter.XML_TO_DATASET);
			DataSet dataset = (DataSet)conv.convertToObject(is);
			
			assertEquals("TestHeader",dataset.getHeader("TestHeader").getName());
			assertEquals(":A,java.util.Date," +
					"\"jp.ossc.nimbus.util.converter.DateFormatConverter{ConvertType=2;Format=\"yyyy-MM-DD\"}\"," +
					"\"jp.ossc.nimbus.util.converter.DateFormatConverter{ConvertType=1;Format=\"yyyy-MM-DD\"}\"," +
							"\"@value@ != null\"\n:B,java.lang.String,,,",dataset.getHeader("TestHeader").getSchema());
			assertEquals("2008-01-28",dataset.getHeader("TestHeader").getFormatProperty("A"));
			assertEquals("TestValue",dataset.getHeader("TestHeader").getProperty("B"));
			
		} catch (PropertySchemaDefineException e) {
			e.printStackTrace();
			fail("��O����");
		} catch (ConvertException e) {
			e.printStackTrace();
			fail("��O����");
		}
	}

	
	/**
	 * �w�肵���I�u�W�F�N�g��XML���f�[�^�Z�b�g�ϊ�����e�X�g�B
	 * <p>
	 * �����F
	 * <ul>
	 * <li>���̓��e��XML�X�g���[���ƕϊ���ʂ�XML_TO_DATASET���w�肵�āA<BR>
	 * DataSetXMLConverter#convert(Object obj)�����s����<BR>
	 * (�v���p�e�BB�̒l�Ȃ�)
	 * <PRE>
	 * <?xml version="1.0" encoding="UTF-8"?>
	 *  <dataSet>
	 *   <schema>
	 *    <header name="TestHeader">
	 *     :A,java.util.Date,
	 *     "jp.ossc.nimbus.util.converter.DateFormatConverter{ConvertType=2;Format="yyyy-MM-DD"}",
	 *     "jp.ossc.nimbus.util.converter.DateFormatConverter{ConvertType=1;Format="yyyy-MM-DD"}",
	 *     "@value@ != null"
	 *     :B,java.lang.String,,,
	 *     :C,java.lang.String,,,
	 *    </header>
	 *   </schema>
	 *    <header name="TestHeader"><A>2008-01-28</A><C>TestValue</C></header></dataSet>
	 * <PRE>
	 * </li>
	 * </ul>
	 * �m�F�F
	 * <ul>
	 * <li>�擾����DataSet�̃w�b�_�X�L�[�}���ϊ����̓��e�Ɠ��������Ƃ��m�F�B<BR>
	 * �擾����DataSet�̊e�v���p�e�B�̒l���ϊ����̓��e�Ɠ��������Ƃ��m�F�B<BR>
	 * �w�肵�Ȃ������v���p�e�B�̒l�ɂ��Ă�null�I�u�W�F�N�g�ɂȂ�</li>
	 * </ul>
	 */
	public void testConvertToDataSetFromInputStreamNull() {
		try {
			String inxml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
			"<dataSet><schema><header name=\"TestHeader\">" +
			":A,java.util.Date," +
			"\"jp.ossc.nimbus.util.converter.DateFormatConverter{ConvertType=2;Format=\"yyyy-MM-DD\"}\"," +
					"\"jp.ossc.nimbus.util.converter.DateFormatConverter{ConvertType=1;Format=\"yyyy-MM-DD\"}\"," +
							"\"@value@ != null\"\n:B,java.lang.String,,,\n:C,java.lang.String,,," +
							"</header></schema><header name=\"TestHeader\">" +
							"<A>2008-01-28</A><C>TestValue</C></header></dataSet>";
			InputStream is = new ByteArrayInputStream(inxml.getBytes());
			
			DataSetXMLConverter conv = new DataSetXMLConverter(DataSetXMLConverter.XML_TO_DATASET);
			DataSet dataset = (DataSet)conv.convert(is);
			
			assertEquals("TestHeader",dataset.getHeader("TestHeader").getName());
			assertEquals(":A,java.util.Date," +
					"\"jp.ossc.nimbus.util.converter.DateFormatConverter{ConvertType=2;Format=\"yyyy-MM-DD\"}\"," +
					"\"jp.ossc.nimbus.util.converter.DateFormatConverter{ConvertType=1;Format=\"yyyy-MM-DD\"}\"," +
							"\"@value@ != null\"\n:B,java.lang.String,,,\n:C,java.lang.String,,,",dataset.getHeader("TestHeader").getSchema());
			assertEquals("2008-01-28",dataset.getHeader("TestHeader").getFormatProperty("A"));
			assertEquals("TestValue",dataset.getHeader("TestHeader").getProperty("C"));
			//�l���w�肵�Ă��Ȃ�B��null
			assertNull(dataset.getHeader("TestHeader").getProperty("B"));
			
		} catch (PropertySchemaDefineException e) {
			e.printStackTrace();
			fail("��O����");
		} catch (ConvertException e) {
			e.printStackTrace();
			fail("��O����");
		}
	}

	public void testConvertToDataSetNoMatchSchema() {
		try {
			DataSetXMLConverter conv = new DataSetXMLConverter(DataSetXMLConverter.XML_TO_DATASET);
			DataSet dataset = new DataSet();
			dataset.setHeaderSchema("Header1", ":A,java.lang.String");
			dataset.setHeaderSchema("Header2", ":B,int\nLIST:C,ListC");
			dataset.setRecordListSchema("RecordList1", ":C,java.lang.String");
			dataset.setRecordListSchema("RecordList2", ":D,int\nLIST:E,ListC");
			dataset.setNestedRecordListSchema("ListC", ":F,java.util.Date");
			conv.setDataSet("test", dataset);
			
			// �X�L�[�}��`�ɑ��݂��Ȃ��w�b�_������
			String inxml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n<dataSet name=\"test\">"
				 + "<header name=\"Header1\"><A>a</A></header>"
				 + "<header name=\"Header3\"><Z>z</Z></header>"
				 + "<header name=\"Header2\"><B>100</B></header></dataSet>";
			InputStream is = new ByteArrayInputStream(inxml.getBytes());
			conv.setIgnoreUnknownElement(false);
			try{
				dataset = (DataSet)conv.convert(is);
				fail("Must be detect error.");
			}catch(ConvertException e){
			}
			try{
				is.reset();
			}catch(IOException e){}
			conv.setIgnoreUnknownElement(true);
			try{
				dataset = (DataSet)conv.convert(is);
			}catch(ConvertException e){
				e.printStackTrace();
				fail("Must not be detect error.");
			}
			assertEquals("a", dataset.getHeader("Header1").getProperty("A"));
			assertEquals(new Integer(100), dataset.getHeader("Header2").getProperty("B"));
			assertNull(dataset.getHeader("Header3"));
			
			// �X�L�[�}��`�ɑ��݂��Ȃ��w�b�_�̃v���p�e�B������
			inxml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n<dataSet name=\"test\">"
				 + "<header name=\"Header1\"><A>a</A><B>100</B></header>"
				 + "<header name=\"Header2\"><B>100</B></header></dataSet>";
			is = new ByteArrayInputStream(inxml.getBytes());
			conv.setIgnoreUnknownElement(false);
			try{
				dataset = (DataSet)conv.convert(is);
				fail();
			}catch(ConvertException e){
			}
			try{
				is.reset();
			}catch(IOException e){}
			conv.setIgnoreUnknownElement(true);
			try{
				dataset = (DataSet)conv.convert(is);
			}catch(ConvertException e){
				e.printStackTrace();
				fail();
			}
			assertEquals("a", dataset.getHeader("Header1").getProperty("A"));
			try{
				dataset.getHeader("Header1").getProperty("B");
				fail();
			}catch(PropertyGetException e){
			}
			assertEquals(new Integer(100), dataset.getHeader("Header2").getProperty("B"));
			
			// �X�L�[�}��`�ɑ��݂��Ȃ����R�[�h���X�g������
			inxml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n<dataSet name=\"test\">"
				 + "<header name=\"Header1\"><A>a</A><B>100</B></header>"
				 + "<recordList name=\"RecordList3\"><record><Z>z1</Z></record><record><Z>z2</Z></record></recordList>"
				 + "<recordList name=\"RecordList1\"><record><C>c1</C></record><record><C>c2</C></record></recordList>"
				 + "</dataSet>";
			is = new ByteArrayInputStream(inxml.getBytes());
			conv.setIgnoreUnknownElement(false);
			try{
				dataset = (DataSet)conv.convert(is);
				fail();
			}catch(ConvertException e){
			}
			try{
				is.reset();
			}catch(IOException e){}
			conv.setIgnoreUnknownElement(true);
			try{
				dataset = (DataSet)conv.convert(is);
			}catch(ConvertException e){
				e.printStackTrace();
				fail();
			}
			assertEquals("a", dataset.getHeader("Header1").getProperty("A"));
			assertTrue(dataset.getRecordList("RecordList1").size() == 2);
			assertEquals("c1", dataset.getRecordList("RecordList1").getRecord(0).getProperty("C"));
			assertEquals("c2", dataset.getRecordList("RecordList1").getRecord(1).getProperty("C"));
			assertNull(dataset.getRecordList("RecordList3"));
			
			// �X�L�[�}��`�ɑ��݂��Ȃ����R�[�h���X�g�̃v���p�e�B������
			inxml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n<dataSet name=\"test\">"
				 + "<header name=\"Header1\"><A>a</A><B>100</B></header>"
				 + "<recordList name=\"RecordList1\"><record><C>c1</C><D>d</D></record><record><C>c2</C></record></recordList>"
				 + "<recordList name=\"RecordList2\"><record><D>1</D></record><record><D>2</D></record></recordList>"
				 + "</dataSet>";
			is = new ByteArrayInputStream(inxml.getBytes());
			conv.setIgnoreUnknownElement(false);
			try{
				dataset = (DataSet)conv.convert(is);
				fail();
			}catch(ConvertException e){
			}
			try{
				is.reset();
			}catch(IOException e){}
			conv.setIgnoreUnknownElement(true);
			try{
				dataset = (DataSet)conv.convert(is);
			}catch(ConvertException e){
				e.printStackTrace();
				fail();
			}
			assertEquals("a", dataset.getHeader("Header1").getProperty("A"));
			assertTrue(dataset.getRecordList("RecordList1").size() == 2);
			assertEquals("c1", dataset.getRecordList("RecordList1").getRecord(0).getProperty("C"));
			assertEquals("c2", dataset.getRecordList("RecordList1").getRecord(1).getProperty("C"));
			try{
				dataset.getRecordList("RecordList1").getRecord(0).getProperty("D");
				fail();
			}catch(PropertyGetException e){
			}
			assertTrue(dataset.getRecordList("RecordList2").size() == 2);
			assertEquals(1, dataset.getRecordList("RecordList2").getRecord(0).getIntProperty("D"));
			assertEquals(2, dataset.getRecordList("RecordList2").getRecord(1).getIntProperty("D"));
			
		} catch (PropertySchemaDefineException e) {
			e.printStackTrace();
			fail("��O����");
		} catch (ConvertException e) {
			e.printStackTrace();
			fail("��O����");
		}
	}
	

}



