package jp.ossc.nimbus.beans.dataset;

import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.Date;
import java.util.Calendar;
import java.math.*;

import junit.framework.TestCase;

//
/**
 * 
 * @author S.Teshima
 * @version 1.00 �쐬: 2008/01/18 - S.Teshima
 */

public class DefaultPropertySchemaTest extends TestCase {

	public DefaultPropertySchemaTest(String arg0) {
		super(arg0);
	}

	
	 public static void main(String[] args) {
	 junit.textui.TestRunner.run(DefaultPropertySchemaTest.class); }
	 

	/**
	 * �X�L�[�}��ݒ肷��e�X�g�B
	 * <p>
	 * �����F
	 * <ul>
	 * <li>���̃X�L�[�}���w�肵��setSchema(String schema)�����s����</li>
	 * <li>"A,java.lang.String,,,"</li>
	 * </ul>
	 * �m�F�F
	 * <ul>
	 * <li>�X�L�[�}������ɐݒ�ł���</li>
	 * <li>��OPropertySchemaDefineException���������Ȃ����Ƃ��m�F</li>
	 * </ul>
	 */
	public void testSetSchema1() {
		try {
			DefaultPropertySchema dps = new DefaultPropertySchema();
			String schema = "A,java.lang.String,,,";
			dps.setSchema(schema);
		} catch (PropertySchemaDefineException e) {
			e.printStackTrace();
			fail("��O����");
		}

	}

	/**
	 * �X�L�[�}��ݒ肷��e�X�g�B
	 * <p>
	 * �����F
	 * <ul>
	 * <li>null�����A�󕶎��̃X�L�[�}���w�肵��setSchema(String schema)�����s����</li>
	 * </ul>
	 * �m�F�F
	 * <ul>
	 * <li>��OPropertySchemaDefineException���������邱�Ƃ��m�F</li>
	 * <li>��O���b�Z�[�W"The schema is insufficient."��Ԃ�</li>
	 * </ul>
	 */
	public void testSetSchema2() {
		try {
			DefaultPropertySchema dps = new DefaultPropertySchema();
			String schema = null;
			dps.setSchema(schema);
			fail("��O���������Ȃ����߃e�X�g���s");
		} catch (PropertySchemaDefineException e) {
			assertEquals("null:The schema is insufficient.", e.getMessage());
		}
		try {
			DefaultPropertySchema dps = new DefaultPropertySchema();
			String schema = "";
			dps.setSchema(schema);
			fail("��O���������Ȃ����߃e�X�g���s ");
		} catch (PropertySchemaDefineException e) {
			assertEquals(":The schema is insufficient.", e.getMessage());
		}
	}

	/**
	 * �X�L�[�}��������擾����e�X�g�B
	 * <p>
	 * �����F
	 * <ul>
	 * <li>���̃X�L�[�}���w�肵��setSchema(String schema)�����s����</li>
	 * <li>"A,java.lang.String,,,"</li>
	 * </ul>
	 * �m�F�F
	 * <ul>
	 * <li>getSchema()�Ŏ擾�������R�[�h�X�L�[�}����`�������e�ƈ�v���Ă���</li>
	 * </ul>
	 */
	public void testGetSchema() {
		try {
			DefaultPropertySchema dps = new DefaultPropertySchema();
			String schema = "A,java.lang.String,,,";
			dps.setSchema(schema);
			assertEquals("A,java.lang.String,,,", dps.getSchema());
		} catch (PropertySchemaDefineException e) {
			e.printStackTrace();
			fail("��O����");
		}
	}

	/**
	 * �X�L�[�}���������͂���e�X�g�B
	 * <p>
	 * �����F
	 * <ul>
	 * <li>���̃X�L�[�}���w�肵��parseCSV(String text)�����s����</li>
	 * <li>"A,java.lang.String,,,"</li>
	 * </ul>
	 * �m�F�F
	 * <ul>
	 * <li>�Ԃ�l��List�̗v�f����4</li>
	 * <li>�Ԃ�l��List��1�Ԗڂ̗v�f��"A"</li>
	 * <li>�Ԃ�l��List��2�Ԗڂ̗v�f��"java.lang.String"</li>
	 * </ul>
	 */
	public void testParseCSV1() {
		String schema = "A,java.lang.String,,,";
		List pcsv = DefaultPropertySchema.parseCSV(schema);
		assertEquals(pcsv.size(), 4);
		assertEquals(pcsv.get(0), "A");
		assertEquals(pcsv.get(1), "java.lang.String");
	}

	/**
	 * �X�L�[�}���������͂���e�X�g�B
	 * <p>
	 * �����F
	 * <ul>
	 * <li>���̃u���b�N�G�X�P�[�v�u"�v���܂ރX�L�[�}���w�肵��parseCSV(String text)�����s����</li>
	 * <li>"A,java.lang.String,"jp.ossc.nimbus.util.converter.PaddingStringConverter<BR>
	 * {1,1,1,*}",,"</li>
	 * </ul>
	 * �m�F�F
	 * <ul>
	 * <li>�Ԃ�l��List��1�v�f����4</li>
	 * <li>�Ԃ�l��List��1�Ԗڂ̗v�f��"A"</li>
	 * <li>�Ԃ�l��List��2�Ԗڂ̗v�f��"java.lang.String"</li>
	 * <li>�Ԃ�l��List��3�Ԗڂ̗v�f��"jp.ossc.nimbus.util.converter.PaddingStringConverter{ConvertType=1;PaddingLength=1;PaddingLiteral=*}"</li>
	 * </ul>
	 */
	public void testParseCSV2() {
		String schema = "A,java.lang.String,\"jp.ossc.nimbus.util.converter.PaddingStringConverter{ConvertType=1;PaddingLength=1;PaddingLiteral=*}\",,";
		List pcsv = DefaultPropertySchema.parseCSV(schema);
		assertEquals(pcsv.size(), 4);
		assertEquals(pcsv.get(0), "A");
		assertEquals(pcsv.get(1), "java.lang.String");
		assertEquals(pcsv.get(2),
				"jp.ossc.nimbus.util.converter.PaddingStringConverter{ConvertType=1;PaddingLength=1;PaddingLiteral=*}");
	}

	/**
	 * �X�L�[�}���������͂���e�X�g�B
	 * <p>
	 * �����F
	 * <ul>
	 * <li>���̃u���b�N�G�X�P�[�v�u"�v�������p�^�[�����܂ރX�L�[�}���w�肵��parseCSV(String text)�����s����</li>
	 * <li>"A,java.lang.String,"",,"</li>
	 * </ul>
	 * �m�F�F
	 * <ul>
	 * <li>�Ԃ�l��List��1�v�f����4</li>
	 * <li>�Ԃ�l��List��1�Ԗڂ̗v�f��"A"</li>
	 * <li>�Ԃ�l��List��2�Ԗڂ̗v�f��"java.lang.String"</li>
	 * </ul>
	 */
	public void testParseCSV3() {
		String schema = "A,java.lang.String,\"\",,";
		List pcsv = DefaultPropertySchema.parseCSV(schema);
		assertEquals(pcsv.size(), 4);
		assertEquals(pcsv.get(0), "A");
		assertEquals(pcsv.get(1), "java.lang.String");
	}

	/**
	 * �X�L�[�}���������͂���e�X�g�B
	 * <p>
	 * �����F
	 * <ul>
	 * <li>���̃G�X�P�[�v�u\�v���܂ރX�L�[�}���w�肵��parseCSV(String text)�����s����</li>
	 * <li>"A,java.lang.String,jp.ossc.nimbus.util.converter.PaddingStringConverter,<BR>
	 * {1\,1\,1\,*},,"</li>
	 * </ul>
	 * �m�F�F
	 * <ul>
	 * <li>�Ԃ�l��List��1�v�f����4</li>
	 * <li>�Ԃ�l��List��1�Ԗڂ̗v�f��"A"</li>
	 * <li>�Ԃ�l��List��2�Ԗڂ̗v�f��"java.lang.String"</li>
	 * <li>�Ԃ�l��List��3�Ԗڂ̗v�f��"jp.ossc.nimbus.util.converter.PaddingStringConverter{1,1,1,*}"</li>
	 * </ul>
	 */
	public void testParseCSV4() {
		String schema = "A,java.lang.String,jp.ossc.nimbus.util.converter.PaddingStringConverter{ConvertType=1;PaddingLength=1;PaddingLiteral=\\,},,";
		List pcsv = DefaultPropertySchema.parseCSV(schema);
		assertEquals(pcsv.size(), 4);
		assertEquals(pcsv.get(0), "A");
		assertEquals(pcsv.get(1), "java.lang.String");
		assertEquals(pcsv.get(2),
				"jp.ossc.nimbus.util.converter.PaddingStringConverter{ConvertType=1;PaddingLength=1;PaddingLiteral=,}");
	}

	/**
	 * �X�L�[�}���������͂���e�X�g�B
	 * <p>
	 * �����F
	 * <ul>
	 * <li>���̃G�X�P�[�v�u\�v�������p�^�[�����܂ރX�L�[�}���w�肵��parseCSV(String text)�����s����</li>
	 * <li>"A\\,java.lang.String,,,"</li>
	 * </ul>
	 * �m�F�F
	 * <ul>
	 * <li>�Ԃ�l��List��1�v�f����4</li>
	 * <li>�Ԃ�l��List��1�Ԗڂ̗v�f��"A\"</li>
	 * <li>�Ԃ�l��List��2�Ԗڂ̗v�f��"java.lang.String"</li>
	 * </ul>
	 */
	public void testParseCSV5() {
		String schema = "A\\\\,java.lang.String,,,";
		List pcsv = DefaultPropertySchema.parseCSV(schema);
		assertEquals(pcsv.size(), 4);
		assertEquals(pcsv.get(0), "A\\");
		assertEquals(pcsv.get(1), "java.lang.String");
	}

	/**
	 * �X�L�[�}���������͂���e�X�g�B
	 * <p>
	 * �����F
	 * <ul>
	 * <li>���̃G�X�P�[�v�u\�v�̌�Ƀu���b�N�G�X�P�[�v�u"�v�������p�^�[�����܂ރX�L�[�}���w�肵��<BR>
	 * parseCSV(String text)�����s����</li>
	 * <li>"A\"A,java.lang.String,,,"</li>
	 * </ul>
	 * �m�F�F
	 * <ul>
	 * <li>�Ԃ�l��List��1�v�f����4</li>
	 * <li>�Ԃ�l��List��1�Ԗڂ̗v�f��"A"A"</li>
	 * <li>�Ԃ�l��List��2�Ԗڂ̗v�f��"java.lang.String"</li>
	 * </ul>
	 */
	public void testParseCSV6() {
		String schema = "A\\\"A,java.lang.String,,,";
		List pcsv = DefaultPropertySchema.parseCSV(schema);
		assertEquals(pcsv.size(), 4);
		assertEquals(pcsv.get(0), "A\"A");
		assertEquals(pcsv.get(1), "java.lang.String");
	}

	/**
	 * �X�L�[�}���������͂���e�X�g�B
	 * <p>
	 * �����F
	 * <ul>
	 * <li>���̃G�X�P�[�v�u\�v�̌�ɒʏ핶���������p�^�[�����܂ރX�L�[�}���w�肵��<BR>
	 * parseCSV(String text)�����s����</li>
	 * <li>"A\A,java.lang.String,,,"</li>
	 * </ul>
	 * �m�F�F
	 * <ul>
	 * <li>�Ԃ�l��List��1�v�f����4</li>
	 * <li>�Ԃ�l��List��1�Ԗڂ̗v�f��"A\A"</li>
	 * <li>�Ԃ�l��List��2�Ԗڂ̗v�f��"java.lang.String"</li>
	 * </ul>
	 */
	public void testParseCSV7() {
		String schema = "A\\A,java.lang.String,,,";
		List pcsv = DefaultPropertySchema.parseCSV(schema);
		assertEquals(pcsv.size(), 4);
		assertEquals(pcsv.get(0), "A\\A");
		assertEquals(pcsv.get(1), "java.lang.String");
	}

	/**
	 * �X�L�[�}���������͂���e�X�g�B
	 * <p>
	 * �����F
	 * <ul>
	 * <li>���̃u���b�N�G�X�P�[�v�u"�v�̌�ɃG�X�P�[�v�u\�v�������p�^�[�����܂ރX�L�[�}���w�肵��<BR>
	 * parseCSV(String text)�����s����</li>
	 * <li>"A"\A,java.lang.String,,,"</li>
	 * </ul>
	 * �m�F�F
	 * <ul>
	 * <li>�Ԃ�l��List��1�v�f����4</li>
	 * <li>�Ԃ�l��List��1�Ԗڂ̗v�f��"A"\A"</li>
	 * <li>�Ԃ�l��List��2�Ԗڂ̗v�f��"java.lang.String"</li>
	 * </ul>
	 */
	public void testParseCSV8() {
		String schema = "A\\\"\\A,java.lang.String,,,";
		List pcsv = DefaultPropertySchema.parseCSV(schema);
		assertEquals(pcsv.size(), 4);
		assertEquals(pcsv.get(0), "A\"\\A");
		assertEquals(pcsv.get(1), "java.lang.String");
	}

	/**
	 * �v���p�e�B�X�L�[�}�̊e���ڂ��p�[�X����e�X�g�B
	 * <p>
	 * �����F
	 * <ul>
	 * <li>���̃X�L�[�}���w�肵��parseSchemata(String schema, List schemata)�����s����<BR>
	 * schemata�ɂ�parseCSV(schema)���w��</li>
	 * <li>"A,java.lang.String,"jp.ossc.nimbus.util.converter.PaddingStringConverter
	 * {ConvertType=1;PaddingLength=1;PaddingLiteral=*}","jp.ossc.nimbus.util.converter.PaddingStringConverter{ConvertType=1;PaddingLength=1;PaddingLiteral=*}","@value@ !=
	 * null""</li>
	 * </ul>
	 * �m�F�F
	 * <ul>
	 * <li>�X�L�[�}������ɐݒ�ł���</li>
	 * <li>��OPropertySchemaDefineException���������Ȃ����Ƃ��m�F</li>
	 * </ul>
	 */
	public void testParseSchemata() {
		try {
			DefaultPropertySchema dps = new DefaultPropertySchema();
			String schema = "A,java.lang.String,"
					+ "\"jp.ossc.nimbus.util.converter.PaddingStringConverter{ConvertType=1;PaddingLength=1;PaddingLiteral=*}\","
					+ "\"jp.ossc.nimbus.util.converter.PaddingStringConverter{ConvertType=1;PaddingLength=1;PaddingLiteral=*}\","
					+ "\"@value@ != null\"";
			dps.parseSchemata(schema, DefaultPropertySchema.parseCSV(schema));
		} catch (PropertySchemaDefineException e) {
			e.printStackTrace();
			fail("��O����");
		}

	}

	/**
	 * �v���p�e�B�X�L�[�}�̌^���p�[�X����e�X�g�B
	 * <p>
	 * �����F
	 * <ul>
	 * <li>���̃X�L�[�}���w�肵��parseType(String schema, String val)�����s����</li>
	 * <li>schema:"A,java.lang.String,,," val:"java.lang.String"</li>
	 * </ul>
	 * �m�F�F
	 * <ul>
	 * <li>����I���B��OPropertySchemaDefineException���������Ȃ�</li>
	 * </ul>
	 */
	public void testParseType1() {
		try {
			DefaultPropertySchema dps = new DefaultPropertySchema();
			String schema = "A,java.lang.String,,,";
			String val = "java.lang.String";
			dps.parseType(schema, val);
		} catch (PropertySchemaDefineException e) {
			e.printStackTrace();
			fail("��O����");
		}
	}

	/**
	 * �v���p�e�B�X�L�[�}�̌^���p�[�X����e�X�g�B
	 * <p>
	 * �����F
	 * <ul>
	 * <li>���̃X�L�[�}���w�肵��parseType(String schema, String val)�����s����</li>
	 * <li>schema:"A,java.lang.String,,," val:null����</li>
	 * </ul>
	 * �m�F�F
	 * <ul>
	 * <li>����I���B��OPropertySchemaDefineException���������Ȃ�</li>
	 * </ul>
	 */
	public void testParseType2() {
		try {
			DefaultPropertySchema dps = new DefaultPropertySchema();
			String schema = "A,java.lang.String,,,";
			String val = null;
			dps.parseType(schema, val);
		} catch (PropertySchemaDefineException e) {
			e.printStackTrace();
			fail("��O����");
		}
	}

	/**
	 * �v���p�e�B�X�L�[�}�̌^���p�[�X����e�X�g�B
	 * <p>
	 * �����F
	 * <ul>
	 * <li>���̃X�L�[�}���w�肵��parseType(String schema, String val)�����s����</li>
	 * <li>schema:"A,DUMMYCLASS,,," val:"DUMMYCLASS"�i���݂��Ȃ��N���X�j</li>
	 * </ul>
	 * �m�F�F
	 * <ul>
	 * <li>��OPropertySchemaDefineException���������邱�Ƃ��m�F<BR>
	 * ��O���b�Z�[�W"The type is illegal."��Ԃ�</li>
	 * </ul>
	 */
	public void testParseType3() {
		try {
			DefaultPropertySchema dps = new DefaultPropertySchema();
			String schema = "A,DUMMYCLASS,,,";
			String val = "DUMMYCLASS";
			dps.parseType(schema, val);
			fail("��O���������Ȃ����߃e�X�g���s ");
		} catch (PropertySchemaDefineException e) {
			assertEquals("A,DUMMYCLASS,,,:The type is illegal.", e.getMessage());
		}
	}

	/**
	 * �v���p�e�B�X�L�[�}�̓��͕ϊ��̍��ڂ��p�[�X����e�X�g�B
	 * <p>
	 * �����F
	 * <ul>
	 * <li>���̃X�L�[�}�ƕϊ����ڕ�������w�肵��parseParseConverter(String schema, String
	 * val)�����s����</li>
	 * <li>�X�L�[�}�F"A,java.lang.String,jp.ossc.nimbus.util.converter.DataSetXMLConverter,<BR>
	 * jp.ossc.nimbus.util.converter.DataSetXMLConverter,"@value@ != null""</li>
	 * <li>�ϊ�����
	 * "jp.ossc.nimbus.util.converter.DataSetXMLConverter"(Converter�N���X�w��)</li>
	 * </ul>
	 * �m�F�F
	 * <ul>
	 * <li>����I���B��OPropertySchemaDefineException���������Ȃ�</li>
	 * <li>parseConverter�Ɏw�肵��Converter�N���X���ݒ肳��Ă���</li>
	 * </ul>
	 */
	public void testParseParseConverter1() {
		try {
			DefaultPropertySchema dps = new DefaultPropertySchema();
			String schema = "A,java.lang.String,jp.ossc.nimbus.util.converter.DataSetXMLConverter,"
					+ "jp.ossc.nimbus.util.converter.DataSetXMLConverter,\"@value@ != null\"";
			String val = "jp.ossc.nimbus.util.converter.DataSetXMLConverter";
			dps.parseParseConverter(schema, val);
			assertTrue(dps.parseConverter instanceof jp.ossc.nimbus.util.converter.DataSetXMLConverter);
		} catch (PropertySchemaDefineException e) {
			e.printStackTrace();
			fail("��O����");
		}
	}

	/**
	 * �v���p�e�B�X�L�[�}�̓��͕ϊ��̍��ڂ��p�[�X����e�X�g�B
	 * <p>
	 * �����F
	 * <ul>
	 * <li>���̃X�L�[�}�ƕϊ����ڕ�������w�肵��parseParseConverter(String schema, String
	 * val)�����s����</li>
	 * <li>�X�L�[�}�F"A,java.lang.String,Nimbus#DataSetXMLConverter,<BR>
	 * Nimbus#DataSetXMLConverter,"@value@ != null""</li>
	 * <li>�ϊ����� "Nimbus#DataSetXMLConverter"(�T�[�r�X��)</li>
	 * </ul>
	 * �m�F�F
	 * <ul>
	 * <li>����I���B��OPropertySchemaDefineException���������Ȃ�</li>
	 * <li>parseConverterName�Ɏw�肵��ServiceName�I�u�W�F�N�g���ݒ肳��Ă���</li>
	 * </ul>
	 */
	public void testParseParseConverter2() {
		try {
			DefaultPropertySchema dps = new DefaultPropertySchema();
			String schema = "A,java.lang.String,Nimbus#DataSetXMLConverter,"
					+ "Nimbus#DataSetXMLConverter,\"@value@ != null\"";
			String val = "Nimbus#DataSetXMLConverter";
			dps.parseParseConverter(schema, val);
			assertEquals(dps.parseConverterName.getServiceManagerName(),
					"Nimbus");
			assertEquals(dps.parseConverterName.getServiceName(),
					"DataSetXMLConverter");
		} catch (PropertySchemaDefineException e) {
			e.printStackTrace();
			fail("��O����");
		}
	}

	/**
	 * �v���p�e�B�X�L�[�}�̓��͕ϊ��̍��ڂ��p�[�X����e�X�g�B
	 * <p>
	 * �����F
	 * <ul>
	 * <li>���̃X�L�[�}�ƕϊ����ڕ�������w�肵��parseParseConverter(String schema, String
	 * val)�����s����</li>
	 * <li>�X�L�[�}�F"A,java.lang.String,#TestService,<BR>
	 * #TestService,"@value@ != null""</li>
	 * <li>�ϊ����� "#TestService"(�}�l�[�W���[�����ݒ肳��Ă��Ȃ��s���ȃT�[�r�X��)</li>
	 * </ul>
	 * �m�F�F
	 * <ul>
	 * <li>��OPropertySchemaDefineException���������邱�Ƃ��m�F<BR>
	 * </ul>
	 */
	public void testParseParseConverter3() {
		try {
			DefaultPropertySchema dps = new DefaultPropertySchema();
			String schema = "A,java.lang.String,#TestService,"
					+ "#TestService,\"@value@ != null\"";
			String val = "#TestService";
			dps.parseParseConverter(schema, val);
			fail("��O���������Ȃ����߃e�X�g���s ");
		} catch (PropertySchemaDefineException e) {

		}
	}

	/**
	 * �v���p�e�B�X�L�[�}�̏o�͕ϊ��̍��ڂ��p�[�X����e�X�g�B
	 * <p>
	 * �����F
	 * <ul>
	 * <li>���̃X�L�[�}�ƕϊ����ڕ�������w�肵��parseFormatConverter(String schema, String
	 * val)�����s����</li>
	 * <li>�X�L�[�}�F"A,java.lang.String,jp.ossc.nimbus.util.converter.DataSetXMLConverter,<BR>
	 * jp.ossc.nimbus.util.converter.DataSetXMLConverter,"@value@ != null""</li>
	 * <li>�ϊ�����
	 * "jp.ossc.nimbus.util.converter.DataSetXMLConverter"(Converter�N���X�w��)</li>
	 * </ul>
	 * �m�F�F
	 * <ul>
	 * <li>����I���B��OPropertySchemaDefineException���������Ȃ�</li>
	 * <li>formatConverter�Ɏw�肵��Converter�N���X���ݒ肳��Ă���</li>
	 * </ul>
	 */
	public void testParseFormatConverter1() {
		try {
			DefaultPropertySchema dps = new DefaultPropertySchema();
			String schema = "A,java.lang.String,jp.ossc.nimbus.util.converter.DataSetXMLConverter,"
					+ "jp.ossc.nimbus.util.converter.DataSetXMLConverter,\"@value@ != null\"";
			String val = "jp.ossc.nimbus.util.converter.DataSetXMLConverter";
			dps.parseFormatConverter(schema, val);
			assertTrue(dps.formatConverter instanceof jp.ossc.nimbus.util.converter.DataSetXMLConverter);
		} catch (PropertySchemaDefineException e) {
			e.printStackTrace();
			fail("��O����");
		}
	}

	/**
	 * �v���p�e�B�X�L�[�}�̏o�͕ϊ��̍��ڂ��p�[�X����e�X�g�B
	 * <p>
	 * �����F
	 * <ul>
	 * <li>���̃X�L�[�}�ƕϊ����ڕ�������w�肵��parseFormatConverter(String schema, String
	 * val)�����s����</li>
	 * <li>�X�L�[�}�F"A,java.lang.String,Nimbus#DataSetXMLConverter,<BR>
	 * Nimbus#DataSetXMLConverter,"@value@ != null""</li>
	 * <li>�ϊ����� "Nimbus#DataSetXMLConverter"(�T�[�r�X��)</li>
	 * </ul>
	 * �m�F�F
	 * <ul>
	 * <li>����I���B��OPropertySchemaDefineException���������Ȃ�</li>
	 * <li>formatConverterName��ServiceName�I�u�W�F�N�g���ݒ肳��Ă���</li>
	 * </ul>
	 */
	public void testParseFormatConverter2() {
		try {
			DefaultPropertySchema dps = new DefaultPropertySchema();
			String schema = "A,java.lang.String,Nimbus#DataSetXMLConverter,"
					+ "Nimbus#DataSetXMLConverter,\"@value@ != null\"";
			String val = "Nimbus#DataSetXMLConverter";
			dps.parseFormatConverter(schema, val);
			assertEquals(dps.formatConverterName.getServiceManagerName(),
					"Nimbus");
			assertEquals(dps.formatConverterName.getServiceName(),
					"DataSetXMLConverter");
		} catch (PropertySchemaDefineException e) {
			e.printStackTrace();
			fail("��O����");
		}
	}

	/**
	 * �v���p�e�B�X�L�[�}�̏o�͕ϊ��̍��ڂ��p�[�X����e�X�g�B
	 * <p>
	 * �����F
	 * <ul>
	 * <li>���̃X�L�[�}�ƕϊ����ڕ�������w�肵��parseFormatConverter(String schema, String
	 * val)�����s����</li>
	 * <li>�X�L�[�}�F"A,java.lang.String,"jp.ossc.nimbus.util.converter.PaddingStringConverter,<BR>
	 * {1,1,1,*}","jp.ossc.nimbus.util.converter.PaddingStringConverter,{1,1,1,*}","@value@ !=
	 * null""</li>
	 * <li>�ϊ����� "#TestService"(�}�l�[�W���[�����ݒ肳��Ă��Ȃ��s���ȃT�[�r�X��)</li>
	 * </ul>
	 * �m�F�F
	 * <ul>
	 * <li>��OPropertySchemaDefineException���������邱�Ƃ��m�F<BR>
	 * </ul>
	 */
	public void testParseFormatConverter3() {
		try {
			DefaultPropertySchema dps = new DefaultPropertySchema();
			String schema = "A,java.lang.String,#TestService,"
					+ "#TestService,\"@value@ != null\"";
			String val = "#TestService";
			dps.parseFormatConverter(schema, val);
			fail("��O���������Ȃ����߃e�X�g���s ");
		} catch (PropertySchemaDefineException e) {

		}
	}

	/**
	 * �v���p�e�B�X�L�[�}�̕ϊ����ڂ��p�[�X����e�X�g�B
	 * <p>
	 * �����F
	 * <ul>
	 * <li>���̃X�L�[�}�ƕϊ����ڕ�������w�肵��parseConverter(String schema, String val)�����s����</li>
	 * <li>�X�L�[�}�F"A,java.lang.String,jp.ossc.nimbus.util.converter.DataSetXMLConverter,<BR>
	 * jp.ossc.nimbus.util.converter.DataSetXMLConverter,"@value@ != null""</li>
	 * <li>�ϊ����� "jp.ossc.nimbus.util.converter.DataSetXMLConverter"(�v���p�e�B�Ȃ�)</li>
	 * </ul>
	 * �m�F�F
	 * <ul>
	 * <li>����I���B��OPropertySchemaDefineException���������Ȃ�</li>
	 * </ul>
	 */
	public void testParseConverter1() {
		try {
			DefaultPropertySchema dps = new DefaultPropertySchema();
			String schema = "A,java.lang.String,jp.ossc.nimbus.util.converter.DataSetXMLConverter,"
					+ "jp.ossc.nimbus.util.converter.DataSetXMLConverter,\"@value@ != null\"";
			String val = "jp.ossc.nimbus.util.converter.DataSetXMLConverter";
			dps.parseConverter(schema, val);
		} catch (PropertySchemaDefineException e) {
			e.printStackTrace();
			fail("��O����");
		}
	}

	/**
	 * �v���p�e�B�X�L�[�}�̕ϊ����ڂ��p�[�X����e�X�g�B
	 * <p>
	 * �����F
	 * <ul>
	 * <li>���̃X�L�[�}�ƕϊ����ڕ�������w�肵��parseConverter(String schema, String val)�����s����</li>
	 * <li>�X�L�[�}�F"A,java.lang.String,"jp.ossc.nimbus.util.converter.PaddingStringConverter<BR>
	 * {1,1,1,*}","jp.ossc.nimbus.util.converter.PaddingStringConverter{1,1,1,*}","@value@ !=
	 * null""</li>
	 * <li>�ϊ�����
	 * "jp.ossc.nimbus.util.converter.PaddingStringConverter,{1,1,1,*}"(�v���p�e�B����)</li>
	 * </ul>
	 * �m�F�F
	 * <ul>
	 * <li>����I���B��OPropertySchemaDefineException���������Ȃ�</li>
	 * </ul>
	 */
	public void testParseConverter2() {
		try {
			DefaultPropertySchema dps = new DefaultPropertySchema();
			String schema = "A,java.lang.String,"
					+ "\"jp.ossc.nimbus.util.converter.PaddingStringConverter{ConvertType=1;PaddingLength=1;PaddingLiteral=*}\","
					+ "\"jp.ossc.nimbus.util.converter.PaddingStringConverter{ConvertType=2;PaddingLength=1;PaddingLiteral=*}\","
					+ "\"@value@ != null\"";
			String val = "jp.ossc.nimbus.util.converter.PaddingStringConverter{ConvertType=2;PaddingLength=1;PaddingLiteral=*}";
			dps.parseConverter(schema, val);
		} catch (PropertySchemaDefineException e) {
			e.printStackTrace();
			fail("��O����");
		}
	}

	/**
	 * �v���p�e�B�X�L�[�}�̕ϊ����ڂ��p�[�X����e�X�g�B
	 * <p>
	 * �����F
	 * <ul>
	 * <li>���̃X�L�[�}�ƕϊ����ڕ�������w�肵��parseConverter(String schema, String val)�����s����</li>
	 * <li>�X�L�[�}�F"A,java.lang.String,Nimbus#DataSetXMLConverter,<BR>
	 * Nimbus#DataSetXMLConverter,"@value@ != null""</li>
	 * <li>�ϊ����� "Nimbus#DataSetXMLConverter"(�T�[�r�X��)</li>
	 * </ul>
	 * �m�F�F
	 * <ul>
	 * <li>����I���B��OPropertySchemaDefineException���������Ȃ�</li>
	 * </ul>
	 */
	public void testParseConverter3() {
		try {
			DefaultPropertySchema dps = new DefaultPropertySchema();
			String schema = "A,java.lang.String,"
					+ "Nimbus#DataSetXMLConverter,"
					+ "Nimbus#DataSetXMLConverter," + "\"@value@ != null\"";
			String val = "Nimbus#DataSetXMLConverter";
			dps.parseConverter(schema, val);
		} catch (PropertySchemaDefineException e) {
			e.printStackTrace();
			fail("��O����");
		}
	}

	/**
	 * �v���p�e�B�X�L�[�}�̕ϊ����ڂ��p�[�X����e�X�g�B
	 * <p>
	 * �����F
	 * <ul>
	 * <li>���̃X�L�[�}�ƕϊ����ڕ�������w�肵��parseConverter(String schema, String val)�����s����</li>
	 * <li>�X�L�[�}�F"A,java.lang.String,#TestService<BR>
	 * #TestService,"@value@ != null""</li>
	 * <li>�ϊ����� "#TestService"(�}�l�[�W���[�����ݒ肳��Ă��Ȃ��s���ȃT�[�r�X��)</li>
	 * </ul>
	 * �m�F�F
	 * <ul>
	 * <li>��OPropertySchemaDefineException���������邱�Ƃ��m�F<BR>
	 * ��O���b�Z�[�W"Converter is illegal."��Ԃ�</li>
	 * </ul>
	 */
	public void testParseConverter4() {
		try {
			DefaultPropertySchema dps = new DefaultPropertySchema();
			String schema = "A,java.lang.String,#TestService,"
					+ "#TestService,\"@value@ != null\"";
			String val = "#TestService";
			dps.parseConverter(schema, val);
			fail("��O���������Ȃ����߃e�X�g���s ");
		} catch (PropertySchemaDefineException e) {
			assertEquals(
					"A,java.lang.String,#TestService,#TestService,\"@value@ != null\""
							+ ":Converter is illegal.", e.getMessage());
		}
	}

	/**
	 * �v���p�e�B�X�L�[�}�̕ϊ����ڂ��p�[�X����e�X�g�B
	 * <p>
	 * �����F
	 * <ul>
	 * <li>���̃X�L�[�}�ƕϊ����ڕ�������w�肵��parseConverter(String schema, String val)�����s����</li>
	 * <li>�X�L�[�}�F"A,java.lang.String,"jp.ossc.nimbus.beans.dataset.DataSet,<BR>
	 * jp.ossc.nimbus.beans.dataset.DataSet,"@value@ != null""</li>
	 * <li>�ϊ����� "jp.ossc.nimbus.beans.dataset.DataSet"(�R���o�[�^�Ƃ��Ď�������Ă��Ȃ��N���X)</li>
	 * </ul>
	 * �m�F�F
	 * <ul>
	 * <li>��OPropertySchemaDefineException���������邱�Ƃ��m�F<BR>
	 * ��O���b�Z�[�W"Converter dose not implement Converter."��Ԃ�</li>
	 * </ul>
	 */
	public void testParseConverter5() {
		try {
			DefaultPropertySchema dps = new DefaultPropertySchema();
			String schema = "A,java.lang.String,jp.ossc.nimbus.beans.dataset.DataSet,"
					+ "jp.ossc.nimbus.beans.dataset.DataSet,\"@value@ != null\"";
			String val = "jp.ossc.nimbus.beans.dataset.DataSet";
			dps.parseConverter(schema, val);
			fail("��O���������Ȃ����߃e�X�g���s ");
		} catch (PropertySchemaDefineException e) {
			assertEquals(
					"A,java.lang.String,jp.ossc.nimbus.beans.dataset.DataSet,"
							+ "jp.ossc.nimbus.beans.dataset.DataSet,\"@value@ != null\""
							+ ":Converter dose not implement Converter.", e
							.getMessage());
		}
	}

	/**
	 * �R���o�[�^�̃v���p�e�B��ݒ肷��e�X�g�B
	 * <p>
	 * �����F
	 * <ul>
	 * <li>���̃X�L�[�}�ƕϊ����ڕ�������w�肵��parseConverter(String schema, String val)�����s����</li>
	 * <li>�X�L�[�}�F"A,java.lang.String,"jp.ossc.nimbus.util.converter.CustomConverter{1}",,"</li>
	 * <li>�ϊ�����
	 * "jp.ossc.nimbus.util.converter.CustomConverter,{1}"(�v���p�e�B�w�肪�s�K�v�ȃR���o�[�^������Ďw��)</li>
	 * </ul>
	 * �m�F�F
	 * <ul>
	 * <li>��OPropertySchemaDefineException���������邱�Ƃ��m�F<BR>
	 * ��O���b�Z�[�W"The property injection of this converter is not supported."��Ԃ�</li>
	 * </ul>
	 */
	public void testInitConverter() {
		try {
			DefaultPropertySchema dps = new DefaultPropertySchema();
			String schema = "A,java.lang.String,\"jp.ossc.nimbus.util.converter.CustomConverter{1}\",,";
			String val = "jp.ossc.nimbus.util.converter.CustomConverter{1}";
			dps.parseConverter(schema, val);
			fail("��O���������Ȃ����߃e�X�g���s ");
		} catch (PropertySchemaDefineException e) {
		}
	}

	/**
	 * �t�H�[�}�b�g�R���o�[�^�̃v���p�e�B��ݒ肷��e�X�g�B
	 * <p>
	 * �����F
	 * <ul>
	 * <li>���̃X�L�[�}�ƕϊ����ڕ�������w�肵��parseParseConverter(String schema, String
	 * val)�����s����</li>
	 * <li>�X�L�[�}�F"A,java.lang.String,"jp.ossc.nimbus.util.converter.DateFormatConverter<BR>
	 * {1,"YYYY-MM-DD"}",,"</li>
	 * <li>�ϊ�����
	 * "jp.ossc.nimbus.util.converter.DateFormatConverter,{1,"YYYY-MM-DD"}"</li>
	 * </ul>
	 * �m�F�F
	 * <ul>
	 * <li>����I���B��OPropertySchemaDefineException���������Ȃ�</li>
	 * <li>�R���o�[�^��getter���\�b�h�Ŏ擾�����l���ݒ�l�Ɠ�����</li>
	 * </ul>
	 */

	public void testInitFormatConverter1() {
		try {
			DefaultPropertySchema dps = new DefaultPropertySchema();
			String schema = "A,java.lang.String,\"jp.ossc.nimbus.util.converter.DateFormatConverter{ConvertType=1;Format=\"YYYY-MM-DD\"}\",,";
			String val = "jp.ossc.nimbus.util.converter.DateFormatConverter{ConvertType=1;Format=\"YYYY-MM-DD\"}";
			dps.parseParseConverter(schema, val);
			jp.ossc.nimbus.util.converter.DateFormatConverter dfc = new jp.ossc.nimbus.util.converter.DateFormatConverter();
			dfc = (jp.ossc.nimbus.util.converter.DateFormatConverter) dps.parseConverter;
			assertEquals(dfc.getConvertType(), 1);
			assertEquals(dfc.getFormat(), "YYYY-MM-DD");
		} catch (PropertySchemaDefineException e) {
			e.printStackTrace();
			fail("��O����");
		}
	}

	/**
	 * �t�H�[�}�b�g�R���o�[�^�̃v���p�e�B��ݒ肷��e�X�g�B
	 * <p>
	 * �����F
	 * <ul>
	 * <li>���̃X�L�[�}�ƕϊ����ڕ�������w�肵��parseConverter(String schema, String val)�����s����</li>
	 * <li>�X�L�[�}�F"A,java.lang.String,"jp.ossc.nimbus.util.converter.DateFormatConverter<BR> {"
	 * ","YYYY-MM-DD"}",,"</li>
	 * <li>�ϊ����� "jp.ossc.nimbus.util.converter.DateFormatConverter{"
	 * ","YYYY-MM-DD"}" (�����ȊO���w��)</li>
	 * </ul>
	 * �m�F�F
	 * <ul>
	 * <li>��OPropertySchemaDefineException���������邱�Ƃ��m�F<BR>
	 * ��O���b�Z�[�W"The property injection of this converter is
	 * "convertType,format""��Ԃ�</li>
	 * </ul>
	 */
	public void testInitFormatConverter2() {
		try {
			DefaultPropertySchema dps = new DefaultPropertySchema();
			String schema = "A,java.lang.String,\"jp.ossc.nimbus.util.converter.DateFormatConverter"
					+ "{ConvertType=\" \";Format=\"YYYY-MM-DD\"}\",,";
			String val = "jp.ossc.nimbus.util.converter.DateFormatConverter{\" \",\"YYYY-MM-DD\"}";
			dps.parseConverter(schema, val);
			fail("��O���������Ȃ����߃e�X�g���s ");
		} catch (PropertySchemaDefineException e) {
		}
	}

	/**
	 * �p�f�B���O�R���o�[�^�̃v���p�e�B��ݒ肷��e�X�g�B
	 * <p>
	 * �����F
	 * <ul>
	 * <li>���̃X�L�[�}�ƕϊ����ڕ�������w�肵��parseConverter(String schema, String val)�����s����</li>
	 * <li>�X�L�[�}�F"A,java.lang.String,"jp.ossc.nimbus.util.converter.PaddingStringConverter<BR>
	 * {ConvertType=1;PaddingDirection=1;PaddingLength=1;PaddingLiteral=*}",,"</li>
	 * <li>�ϊ����� "jp.ossc.nimbus.util.converter.PaddingStringConverter{ConvertType=1;PaddingDirection=1;PaddingLength=1;PaddingLiteral=*}"</li>
	 * </ul>
	 * �m�F�F
	 * <ul>
	 * <li>����I���B��OPropertySchemaDefineException���������Ȃ�</li>
	 * <li>�R���o�[�^��getter���\�b�h�Ŏ擾�����l���ݒ�l�Ɠ�����</li>
	 * </ul>
	 */
	public void testInitPaddingConverter1() {
		try {
			DefaultPropertySchema dps = new DefaultPropertySchema();
			String schema = "A,java.lang.String,\"jp.ossc.nimbus.util.converter.PaddingStringConverter{ConvertType=1;PaddingDirection=1;PaddingLength=1;PaddingLiteral=*}\",,";
			String val = "jp.ossc.nimbus.util.converter.PaddingStringConverter{ConvertType=1;PaddingDirection=1;PaddingLength=1;PaddingLiteral=*}";
			dps.parseParseConverter(schema, val);
			jp.ossc.nimbus.util.converter.PaddingStringConverter psc = new jp.ossc.nimbus.util.converter.PaddingStringConverter();
			psc = (jp.ossc.nimbus.util.converter.PaddingStringConverter) dps.parseConverter;
			assertEquals(psc.getConvertType(), 1);
			assertEquals(psc.getPaddingDirection(), 1);
			assertEquals(psc.getPaddingLength(), 1);
			assertEquals(psc.getPaddingLiteral(), '*');
		} catch (PropertySchemaDefineException e) {
			e.printStackTrace();
			fail("��O����");
		}
	}

	/**
	 * �p�f�B���O�R���o�[�^�̃v���p�e�B��ݒ肷��e�X�g�B
	 * <p>
	 * �����F
	 * <ul>
	 * <li>���̃X�L�[�}�ƕϊ����ڕ�������w�肵��parseConverter(String schema, String val)�����s����</li>
	 * <li>�X�L�[�}�F"A,java.lang.String,"jp.ossc.nimbus.util.converter.PaddingStringConverter<BR> {"
	 * ",1,1,*}",,"</li>
	 * <li>�ϊ����� "jp.ossc.nimbus.util.converter.PaddingStringConverter{"
	 * ",1,1,*}"(�v���p�e�B�w��̌��B�����ȊO���w��)</li>
	 * </ul>
	 * �m�F�F
	 * <ul>
	 * <li>��OPropertySchemaDefineException���������邱�Ƃ��m�F<BR>
	 * ��O���b�Z�[�W"The property injection of this converter is
	 * "convertType,paddingLength,paddingDirection,paddingLiteral""��Ԃ�</li>
	 * </ul>
	 */
	public void testInitPaddingConverter2() {
		try {
			DefaultPropertySchema dps = new DefaultPropertySchema();
			String schema = "A,java.lang.String,\"jp.ossc.nimbus.util.converter.PaddingStringConverter"
					+ "{\" \",1,1,*}\",,";
			String val = "jp.ossc.nimbus.util.converter.PaddingStringConverter{ConvertType=\" \";PaddingDirection=1;PaddingLength=1;PaddingLiteral=*}";
			dps.parseConverter(schema, val);
			fail("��O���������Ȃ����߃e�X�g���s ");
		} catch (PropertySchemaDefineException e) {
		}
	}

	/**
	 * �t�R���o�[�^�̃v���p�e�B��ݒ肷��e�X�g�B
	 * <p>
	 * �����F
	 * <ul>
	 * <li>���̃X�L�[�}�ƕϊ����ڕ�������w�肵��parseParseConverter(String schema, String
	 * val)�����s����</li>
	 * <li>�X�L�[�}�F"A,java.lang.String,"jp.ossc.nimbus.util.converter.DataSetXMLConverter<BR>
	 * {1}",,"</li>
	 * <li>�ϊ����� "jp.ossc.nimbus.util.converter.DataSetXMLConverter{ConvertType=1}"</li>
	 * </ul>
	 * �m�F�F
	 * <ul>
	 * <li>����I���B��OPropertySchemaDefineException���������Ȃ�</li>
	 * <li>�R���o�[�^��getter���\�b�h�Ŏ擾�����l���ݒ�l�Ɠ�����</li>
	 * </ul>
	 */
	public void testInitReversibleConverter1() {
		try {
			DefaultPropertySchema dps = new DefaultPropertySchema();
			String schema = "A,java.lang.String,\"jp.ossc.nimbus.util.converter.DataSetXMLConverter{ConvertType=1}\",,";
			String val = "jp.ossc.nimbus.util.converter.DataSetXMLConverter{ConvertType=1}";
			dps.parseParseConverter(schema, val);
			jp.ossc.nimbus.util.converter.DataSetXMLConverter dxc = new jp.ossc.nimbus.util.converter.DataSetXMLConverter();
			dxc = (jp.ossc.nimbus.util.converter.DataSetXMLConverter) dps.parseConverter;
			assertEquals(dxc.getConvertType(), 1);
		} catch (PropertySchemaDefineException e) {
			e.printStackTrace();
			fail("��O����");
		}
	}

	/**
	 * �t�R���o�[�^�̃v���p�e�B��ݒ肷��e�X�g�B
	 * <p>
	 * �����F
	 * <ul>
	 * <li>���̃X�L�[�}�ƕϊ����ڕ�������w�肵��parseConverter(String schema, String val)�����s����</li>
	 * <li>�X�L�[�}�F"A,java.lang.String,"jp.ossc.nimbus.util.converter.DataSetXMLConverter<BR> {"
	 * "}",,"</li>
	 * <li>�ϊ����� "jp.ossc.nimbus.util.converter.DateFormatConverter{"
	 * "}"(�v���p�e�B�w��̌��B�����ȊO���w��)</li>
	 * </ul>
	 * �m�F�F
	 * <ul>
	 * <li>��OPropertySchemaDefineException���������邱�Ƃ��m�F<BR>
	 * ��O���b�Z�[�W"The property injection of this converter is "convertType""��Ԃ�</li>
	 * </ul>
	 */
	public void testInitReversibleConverter3() {
		try {
			DefaultPropertySchema dps = new DefaultPropertySchema();
			String schema = "A,java.lang.String,\"jp.ossc.nimbus.util.converter.DataSetXMLConverter"
					+ "{\" \"}\",,";
			String val = "jp.ossc.nimbus.util.converter.DataSetXMLConverter{ConvertType=\" \"}";
			dps.parseConverter(schema, val);
			fail("��O���������Ȃ����߃e�X�g���s ");
		} catch (PropertySchemaDefineException e) {
		}
	}

	/**
	 * �v���p�e�B�X�L�[�}�̐���̍��ڂ��p�[�X����e�X�g�B
	 * <p>
	 * �����F
	 * <ul>
	 * <li>���̃X�L�[�}�Ɛ��񕶎�����w�肵��parseConstrain(String schema, String val)�����s����</li>
	 * <li>�X�L�[�}�F"A,java.lang.String,"jp.ossc.nimbus.util.converter.DataSetXMLConverter<BR>
	 * {1}",,"</li>
	 * <li>���񕶎��� ""(�����w�肵�Ȃ�)</li>
	 * </ul>
	 * �m�F�F
	 * <ul>
	 * <li>����I���B��OPropertySchemaDefineException���������Ȃ�</li>
	 * </ul>
	 */
	public void testParseConstrain1() {
		try {
			DefaultPropertySchema dps = new DefaultPropertySchema();
			String schema = "A,java.lang.String,\"jp.ossc.nimbus.util.converter.DataSetXMLConverter{1}\",,";
			String val = "";
			dps.parseConstrain(schema, val);
		} catch (PropertySchemaDefineException e) {
			e.printStackTrace();
			fail("��O����");
		}
	}

	/**
	 * �v���p�e�B�X�L�[�}�̐���̍��ڂ��p�[�X����e�X�g�B
	 * <p>
	 * �����F
	 * <ul>
	 * <li>���̃X�L�[�}�Ɛ��񕶎�����w�肵��parseConstrain(String schema, String val)�����s����</li>
	 * <li>�X�L�[�}�F"A,java.lang.String,"jp.ossc.nimbus.util.converter.DataSetXMLConverter<BR>
	 * {1}",,"@value@ != null""</li>
	 * <li>���񕶎��� "@value@ != nul"</li>
	 * </ul>
	 * �m�F�F
	 * <ul>
	 * <li>����I���B��OPropertySchemaDefineException���������Ȃ�</li>
	 * </ul>
	 */
	public void testParseConstrain2() {
		try {
			DefaultPropertySchema dps = new DefaultPropertySchema();
			String schema = "A,java.lang.String,\"jp.ossc.nimbus.util.converter.DataSetXMLConverter{1}\","
					+ ",\"@value@ != null\"";
			String val = "@value@ != null";
			dps.parseConstrain(schema, val);
		} catch (PropertySchemaDefineException e) {
			e.printStackTrace();
			fail("��O����");
		}
	}

	/**
	 * �v���p�e�B�X�L�[�}�̐���̍��ڂ��p�[�X����e�X�g�B
	 * <p>
	 * �����F
	 * <ul>
	 * <li>���̃X�L�[�}�Ɛ��񕶎�����w�肵��parseConstrain(String schema, String val)�����s����</li>
	 * <li>�X�L�[�}�F"A,java.lang.String,"jp.ossc.nimbus.util.converter.DataSetXMLConverter<BR>
	 * {1}",,"@value.length@ != 0""</li>
	 * <li>���񕶎��� "@value.length@ != 0"</li>
	 * </ul>
	 * �m�F�F
	 * <ul>
	 * <li>����I���B��OPropertySchemaDefineException���������Ȃ�</li>
	 * </ul>
	 */
	public void testParseConstrain3() {
		try {
			DefaultPropertySchema dps = new DefaultPropertySchema();
			String schema = "A,java.lang.String,\"jp.ossc.nimbus.util.converter.DataSetXMLConverter{1}\","
					+ ",\"@value.length@ != 0\"";
			String val = "@value.length@ != 0";
			dps.parseConstrain(schema, val);
		} catch (PropertySchemaDefineException e) {
			e.printStackTrace();
			fail("��O����");
		}
	}

	/**
	 * �v���p�e�B�X�L�[�}�̐���̍��ڂ��p�[�X����e�X�g�B
	 * <p>
	 * �����F
	 * <ul>
	 * <li>���̃X�L�[�}�Ɛ��񕶎�����w�肵��parseConstrain(String schema, String val)�����s����</li>
	 * <li>�X�L�[�}�F"A,java.lang.String,"jp.ossc.nimbus.util.converter.DataSetXMLConverter<BR>
	 * {1}",,"@valu@ != nul""</li>
	 * <li>���񕶎��� "@valu@ != nul"("value"�̒Ԃ肪�������Ȃ�)</li>
	 * </ul>
	 * �m�F�F
	 * <ul>
	 * <li>��OPropertySchemaDefineException���������邱�Ƃ��m�F<BR>
	 * ��O���b�Z�[�W""Illegal constrain :
	 * 
	 * @valu@ != nul"��Ԃ�</li>
	 *        </ul>
	 */
	public void testParseConstrain4() {
		try {
			DefaultPropertySchema dps = new DefaultPropertySchema();
			String schema = "A,java.lang.String,\"jp.ossc.nimbus.util.converter.DataSetXMLConverter{ConvertType=1}\","
					+ ",\"@valu@ != nul\"";
			String val = "@valu@ != nul";
			dps.parseConstrain(schema, val);
			fail("��O���������Ȃ����߃e�X�g���s ");
		} catch (PropertySchemaDefineException e) {
			assertEquals(
					"jp.ossc.nimbus.beans.dataset.DefaultPropertySchema{name=null,type=null,parseConverter=null,"
							+ "formatConverter=null,constrain=null}:Illegal constrain : @valu@ != nul",
					e.getMessage());
		}
	}

	/**
	 * �X�L�[�}�Ɏw�肵�����O���擾����e�X�g�B
	 * <p>
	 * �����F
	 * <ul>
	 * <li>���̃X�L�[�}���w�肵��setSchema(String schema)�����s����</li>
	 * <li>"A,java.lang.String,,,"</li>
	 * </ul>
	 * �m�F�F
	 * <ul>
	 * <li>getName()�Ŏ擾�������O����`�������e�ƈ�v���Ă���</li>
	 * </ul>
	 */
	public void testGetName() {
		try {
			DefaultPropertySchema dps = new DefaultPropertySchema();
			String schema = "A,java.lang.String,,,";
			dps.setSchema(schema);
			assertEquals(dps.getName(), "A");
		} catch (PropertySchemaDefineException e) {
			e.printStackTrace();
			fail("��O����");
		}
	}

	/**
	 * �X�L�[�}�Ɏw�肵���^���擾����e�X�g�B
	 * <p>
	 * �����F
	 * <ul>
	 * <li>���̃X�L�[�}���w�肵��setSchema(String schema)�����s����</li>
	 * <li>"A,java.lang.String,,,"</li>
	 * </ul>
	 * �m�F�F
	 * <ul>
	 * <li>getType()�Ŏ擾�������O����`�������e�ƈ�v���Ă���</li>
	 * </ul>
	 */
	public void testGetType() {
		try {
			DefaultPropertySchema dps = new DefaultPropertySchema();
			String schema = "A,java.lang.String,,,";
			dps.setSchema(schema);
			assertTrue(dps.getType() == java.lang.String.class);
		} catch (PropertySchemaDefineException e) {
			e.printStackTrace();
			fail("��O����");
		}
	}

	/**
	 * �X�L�[�}�Ɏw�肵���p�[�X�p�R���o�[�^���擾����e�X�g�B
	 * <p>
	 * �����F
	 * <ul>
	 * <li>���̃X�L�[�}���w�肵��setSchema(String schema)�����s����</li>
	 * <li>�X�L�[�}�F"A,java.lang.String,"jp.ossc.nimbus.util.converter.DataSetXMLConverter<BR>
	 * {ConvertType=1}",,"</li>
	 * </ul>
	 * �m�F�F
	 * <ul>
	 * <li>getParseConverter()�Ŏ擾����Converter����`�������e�ƈ�v���Ă���</li>
	 * </ul>
	 */
	public void testGetParseConverter() {
		try {
			DefaultPropertySchema dps = new DefaultPropertySchema();
			String schema = "A,java.lang.String," +
					"\"jp.ossc.nimbus.util.converter.DataSetXMLConverter{ConvertType=1}\",,";
			dps.setSchema(schema);
			assertTrue(dps.getParseConverter() instanceof 
					jp.ossc.nimbus.util.converter.DataSetXMLConverter); 
		} catch (PropertySchemaDefineException e) {
			e.printStackTrace();
			fail("��O����");
		}
	}

	/**
	 * �X�L�[�}�Ɏw�肵���p�[�X�p�R���o�[�^���擾����e�X�g�B
	 * <p>
	 * �����F
	 * <ul>
	 * <li>���̃X�L�[�}���w�肵��setSchema(String schema)�����s����</li>
	 * <li>�X�L�[�}�F"A,java.lang.String,Nimbus#DataSetXMLConverter,,"</li>
	 * </ul>
	 * �m�F�F
	 * <ul>
	 * <li>parseConverterName�Ɏw�肵��ServiceName�I�u�W�F�N�g���ݒ肳��Ă���</li>
	 * </ul>
	 */
	public void testGetParseConverterService() {
		try {
			DefaultPropertySchema dps = new DefaultPropertySchema();
			String schema = "A,java.lang.String,Nimbus#DataSetXMLConverter,,";
			dps.setSchema(schema);
			assertEquals(dps.parseConverterName.getServiceManagerName(),
					"Nimbus");
			assertEquals(dps.parseConverterName.getServiceName(),
					"DataSetXMLConverter");
		} catch (PropertySchemaDefineException e) {
			e.printStackTrace();
			fail("��O����");
		}
	}

	/**
	 * �X�L�[�}�Ɏw�肵���t�H�[�}�b�g�p�R���o�[�^���擾����e�X�g�B
	 * <p>
	 * �����F
	 * <ul>
	 * <li>���̃X�L�[�}���w�肵��setSchema(String schema)�����s����</li>
	 * <li>�X�L�[�}�F"A,java.lang.String,<BR>
	 * ,"jp.ossc.nimbus.util.converter.DataSetXMLConverter,{ConvertType=1}","</li>
	 * </ul>
	 * �m�F�F
	 * <ul>
	 * <li>getFormatConverter()�Ŏ擾����Converter����`�������e�ƈ�v���Ă���</li>
	 * </ul>
	 */
	public void testGetFormatConverter() {
		try {
			DefaultPropertySchema dps = new DefaultPropertySchema();
			String schema = "A,java.lang.String,," +
					"\"jp.ossc.nimbus.util.converter.DataSetXMLConverter{ConvertType=1}\",";
			dps.setSchema(schema);
			assertTrue(dps.getFormatConverter() instanceof 
					jp.ossc.nimbus.util.converter.DataSetXMLConverter); 
		} catch (PropertySchemaDefineException e) {
			e.printStackTrace();
			fail("��O����");
		}
	}

	/**
	 * �X�L�[�}�Ɏw�肵���t�H�[�}�b�g�p�R���o�[�^���擾����e�X�g�B
	 * <p>
	 * �����F
	 * <ul>
	 * <li>���̃X�L�[�}���w�肵��setSchema(String schema)�����s����</li>
	 * <li>�X�L�[�}�F"A,java.lang.String,,"Nimbus#DataSetXMLConverter,"</li>
	 * </ul>
	 * �m�F�F
	 * <ul>
	 * <li>formatConverterName��ServiceName�I�u�W�F�N�g���ݒ肳��Ă���</li>
	 * </ul>
	 */
	public void testGetFormatConverterService() {
		try {
			DefaultPropertySchema dps = new DefaultPropertySchema();
			String schema = "A,java.lang.String,,Nimbus#DataSetXMLConverter,";
			dps.setSchema(schema);
			assertEquals(dps.formatConverterName.getServiceManagerName(),
			"Nimbus");
			assertEquals(dps.formatConverterName.getServiceName(),
			"DataSetXMLConverter");
		} catch (PropertySchemaDefineException e) {
			e.printStackTrace();
			fail("��O����");
		}
	}

	/**
	 * �X�L�[�}�Ɏw�肵�����񎮂��擾����e�X�g�B
	 * <p>
	 * �����F
	 * <ul>
	 * <li>���̃X�L�[�}���w�肵��setSchema(String schema)�����s����</li>
	 * <li>�X�L�[�}�F"A,java.lang.String,"jp.ossc.nimbus.util.converter.DataSetXMLConverter<BR> {"
	 * "}",,"@value@ != null""</li>
	 * </ul>
	 * �m�F�F
	 * <ul>
	 * <li>getConstrain()�Ŏ擾����������"@value@ != null"�ƈ�v���Ă���</li>
	 * </ul>
	 */
	public void testGetConstrain() {
		try {
			DefaultPropertySchema dps = new DefaultPropertySchema();
			String schema = "A,java.lang.String,jp.ossc.nimbus.util.converter.DataSetXMLConverter,"
					+ "jp.ossc.nimbus.util.converter.DataSetXMLConverter,\"@value@ != null\"";
			dps.setSchema(schema);
			assertEquals(dps.getConstrain(), "@value@ != null");
		} catch (PropertySchemaDefineException e) {
			e.printStackTrace();
			fail("��O����");
		}
	}

	/**
	 * �X�L�[�}�Ɏw�肵�����񎮂��擾����e�X�g�B
	 * <p>
	 * �����F
	 * <ul>
	 * <li>���̃X�L�[�}���w�肵��setSchema(String schema)�����s����</li>
	 * <li>�X�L�[�}�F"A,java.lang.String,"jp.ossc.nimbus.util.converter.DataSetXMLConverter<BR> {"
	 * "}",,"(����w��Ȃ�)</li>
	 * </ul>
	 * �m�F�F
	 * <ul>
	 * <li>getConstrain()�Ŏ擾����������Null</li>
	 * </ul>
	 */
	public void testGetConstrainNull() {
		try {
			DefaultPropertySchema dps = new DefaultPropertySchema();
			String schema = "A,java.lang.String,jp.ossc.nimbus.util.converter.DataSetXMLConverter,"
					+ "jp.ossc.nimbus.util.converter.DataSetXMLConverter,";
			dps.setSchema(schema);
			assertEquals(dps.getConstrain(), null);
		} catch (PropertySchemaDefineException e) {
			e.printStackTrace();
			fail("��O����");
		}
	}

	/**
	 * �v���p�e�B�̒l�̐ݒ�`�F�b�N�e�X�g�B
	 * <p>
	 * �����F
	 * <ul>
	 * <li>���̃X�L�[�}���w�肵��setSchema(String schema)�����s����</li>
	 * <li>�X�L�[�}�F"A,java.lang.String,,,"</li>
	 * <li>���̒l���w�肵��set(Object val)�����s����</li>
	 * <li>"������"</li>
	 * </ul>
	 * �m�F�F
	 * <ul>
	 * <li>����I���B��OClassNotFoundException���������Ȃ����Ƃ��m�F</li>
	 * <li>�Ԃ�l�Ɛݒ�l���������Ƃ��m�F</li>
	 * </ul>
	 */
	public void testSet() {
		try {
			DefaultPropertySchema dps = new DefaultPropertySchema();
			String schema = "A,java.lang.String,,,";
			dps.setSchema(schema);
			String val = "������";
			assertEquals(dps.set(val), val);
		} catch (PropertySchemaDefineException e) {
			e.printStackTrace();
			fail("��O����");
		}
	}

	/**
	 * �v���p�e�B�̒l�̐ݒ�`�F�b�N�e�X�g�B
	 * <p>
	 * �����F
	 * <ul>
	 * <li>���̃X�L�[�}���w�肵��setSchema(String schema)�����s����</li>
	 * <li>�X�L�[�}�F"A,int,,,</li>
	 * <li>���̒l���w�肵��set(Object val)�����s����</li>
	 * <li>Map�^�̒l (�^�̕s��v)</li>
	 * </ul>
	 * �m�F�F
	 * <ul>
	 * <li>��OPropertySchemaCheckException������������m�F</li>
	 * </ul>
	 */
	public void testSetBadValue() {
		try {
			DefaultPropertySchema dps = new DefaultPropertySchema();
			String schema = "A,int,,,";
			dps.setSchema(schema);
			Map val = new HashMap();
			assertEquals(dps.set(val), val);
			fail("��O���������Ȃ����߃e�X�g���s ");
		} catch (PropertySchemaCheckException e) {
		}
	}

	/**
	 * �v���p�e�B�l���t�H�[�}�b�g���Ď擾����e�X�g�B
	 * <p>
	 * �����F
	 * <ul>
	 * <li>���̃X�L�[�}���w�肵��setSchema(String schema)�����s����</li>
	 * <li>�X�L�[�}�F"A,java.util.Date,,"jp.ossc.nimbus.util.converter.DateFormatConverter<BR>
	 * {ConvertType=1;Format="yyyy-MM-DD"}","</li>
	 * <li>���̒l���w�肵��format(Object val)�����s����</li>
	 * <li>Date�^�I�u�W�F�N�g</li>
	 * </ul>
	 * �m�F�F
	 * <ul>
	 * <li>����I���B��OPropertySchemaDefineException���������Ȃ����Ƃ��m�F</li>
	 * <li>�Ԃ�l���w�肵���t�H�[�}�b�g"yyyy-MM-DD"�Ő������ϊ�����邱�Ƃ��m�F
	 */
	public void testFormat() {
		try {
			DefaultPropertySchema dps = new DefaultPropertySchema();
			String schema = "A,java.util.Date,," +
					"\"jp.ossc.nimbus.util.converter.DateFormatConverter{ConvertType=1;Format=\"yyyy-MM-DD\"}\",";
			dps.setSchema(schema);
			Calendar cal = Calendar.getInstance();
			cal.set(2008, 0, 22);
			Date date = cal.getTime();
			assertEquals(dps.format(date), "2008-01-22");
		} catch (PropertySchemaDefineException e) {
			e.printStackTrace();
			fail("��O����");
		}
	}


	/**
	 * �v���p�e�B�̒l�̐ݒ�`�F�b�N�e�X�g(Date)�B
	 * <p>
	 * �����F
	 * <ul>
	 * <li>���̃X�L�[�}���w�肵��setSchema(String schema)�����s����</li>
	 * <li>�X�L�[�}�F"A,java.util.Date,,,"(�o�͕ϊ��w��Ȃ�)</li>
	 * <li>���̒l���w�肵��format(Object val)�����s����</li>
	 * <li>Date�^�I�u�W�F�N�g</li>
	 * </ul>
	 * �m�F�F
	 * <ul>
	 * <li>����I���B��OClassNotFoundException���������Ȃ����Ƃ��m�F</li>
	 * <li>�Ԃ�l���w�肵���l�Ɠ�����"yyyy/MM/dd"�̌`���ŕԂ���邱�Ƃ��m�F
	 */
	public void testFormatNullDate() {
		try {
			DefaultPropertySchema dps = new DefaultPropertySchema();
			String schema = "A,java.util.Date,,,";
			dps.setSchema(schema);
			Calendar cal = Calendar.getInstance();
			cal.set(2008, 0, 22);
			Date date = cal.getTime();
			String s = (String)dps.format(date);
			assertTrue(s.startsWith("2008/01/22"));
		} catch (PropertySchemaDefineException e) {
			e.printStackTrace();
			fail("��O����");
		}
	}

	/**
	 * �v���p�e�B�̒l�̐ݒ�`�F�b�N�e�X�g(BigDecimal)�B
	 * <p>
	 * �����F
	 * <ul>
	 * <li>���̃X�L�[�}���w�肵��setSchema(String schema)�����s����</li>
	 * <li>�X�L�[�}�F"A,java.math.BigDecimal,,,"(�o�͕ϊ��w��Ȃ�)</li>
	 * <li>�l���w�肵��format(Object val)�����s����</li>
	 * </ul>
	 * �m�F�F
	 * <ul>
	 * <li>����I���B��OClassNotFoundException���������Ȃ����Ƃ��m�F</li>
	 * <li>�Ԃ�l���w�肵���l�𕶎��񉻂������̂��Ԃ���邱�Ƃ��m�F
	 */
	public void testFormatNullBigDecimal() {
		try {
			DefaultPropertySchema dps = new DefaultPropertySchema();
			String schema = "A,java.math.BigDecimal,,,";
			dps.setSchema(schema);
			BigDecimal val = new BigDecimal("12345678901234567890.12345678");
			assertEquals("12345678901234567890.12345678", (String)dps.format(val));
		} catch (PropertySchemaDefineException e) {
			e.printStackTrace();
			fail("��O����");
		}
	}

	/**
	 * �v���p�e�B�̒l�̐ݒ�`�F�b�N�e�X�g(BigDecimal�z��)�B
	 * <p>
	 * �����F
	 * <ul>
	 * <li>���̃X�L�[�}���w�肵��setSchema(String schema)�����s����</li>
	 * <li>�X�L�[�}�F"A,java.math.BigDecimal[],,,"(�o�͕ϊ��w��Ȃ�)</li>
	 * <li>�l���w�肵��format(Object val)�����s����</li>
	 * </ul>
	 * �m�F�F
	 * <ul>
	 * <li>����I���B��OClassNotFoundException���������Ȃ����Ƃ��m�F</li>
	 * <li>�Ԃ�l���w�肵���l�𕶎��񉻂������̂��Ԃ���邱�Ƃ��m�F
	 */
	public void testFormatNullBigDecimalArray() {
		try {
			DefaultPropertySchema dps = new DefaultPropertySchema();
			String schema = "A,java.math.BigDecimal[],,,";
			dps.setSchema(schema);
			BigDecimal[] val = new BigDecimal[]{new BigDecimal("12345678901234567890.12345678"),
					new BigDecimal("99945678901234567890.12345678")};
			assertEquals("12345678901234567890.12345678," +
					"99945678901234567890.12345678", (String)dps.format(val));
		} catch (PropertySchemaDefineException e) {
			e.printStackTrace();
			fail("��O����");
		}
	}

	/**
	 * �v���p�e�B�̒l�̐ݒ�`�F�b�N�e�X�g(BigInteger)�B
	 * <p>
	 * �����F
	 * <ul>
	 * <li>���̃X�L�[�}���w�肵��setSchema(String schema)�����s����</li>
	 * <li>�X�L�[�}�F"A,java.math.BigInteger,,,"(�o�͕ϊ��w��Ȃ�)</li>
	 * <li>�l���w�肵��format(Object val)�����s����</li>
	 * </ul>
	 * �m�F�F
	 * <ul>
	 * <li>����I���B��OClassNotFoundException���������Ȃ����Ƃ��m�F</li>
	 * <li>�Ԃ�l���w�肵���l�𕶎��񉻂������̂��Ԃ���邱�Ƃ��m�F
	 */
	public void testFormatNullBigInteger() {
		try {
			DefaultPropertySchema dps = new DefaultPropertySchema();
			String schema = "A,java.math.BigInteger,,,";
			dps.setSchema(schema);
			BigInteger val = new BigInteger("12345678901234567890");
			assertEquals("12345678901234567890", (String)dps.format(val));
		} catch (PropertySchemaDefineException e) {
			e.printStackTrace();
			fail("��O����");
		}
	}

	/**
	 * �v���p�e�B�̒l�̐ݒ�`�F�b�N�e�X�g(BigInteger�z��)�B
	 * <p>
	 * �����F
	 * <ul>
	 * <li>���̃X�L�[�}���w�肵��setSchema(String schema)�����s����</li>
	 * <li>�X�L�[�}�F"A,java.math.BigInteger[],,,"(�o�͕ϊ��w��Ȃ�)</li>
	 * <li>�l���w�肵��format(Object val)�����s����</li>
	 * </ul>
	 * �m�F�F
	 * <ul>
	 * <li>����I���B��OClassNotFoundException���������Ȃ����Ƃ��m�F</li>
	 * <li>�Ԃ�l���w�肵���l�𕶎��񉻂������̂��Ԃ���邱�Ƃ��m�F
	 */
	public void testFormatNullBigIntegerArray() {
		try {
			DefaultPropertySchema dps = new DefaultPropertySchema();
			String schema = "A,java.math.BigInteger[],,,";
			dps.setSchema(schema);
			BigDecimal[] val = new BigDecimal[]{new BigDecimal("12345678901234567890"),
					new BigDecimal("99945678901234567890")};
			assertEquals("12345678901234567890," +
					"99945678901234567890", (String)dps.format(val));
		} catch (PropertySchemaDefineException e) {
			e.printStackTrace();
			fail("��O����");
		}
	}


	/**
	 * �v���p�e�B�̒l�̐ݒ�`�F�b�N�e�X�g�B
	 * <p>
	 * �����F
	 * <ul>
	 * <li>���̃X�L�[�}���w�肵��setSchema(String schema)�����s����</li>
	 * <li>�X�L�[�}�F"A,java.util.Date,<BR>
	 * "jp.ossc.nimbus.util.converter.DateFormatConverter,{ConvertType=1,Format="yyyy-MM-DD"}",,"</li>
	 * <li>���̒l���w�肵��parse(Object val)�����s����</li>
	 * <li>"yyyy-MM-DD"�̃t�H�[�}�b�g�̓��t��\����������</li>
	 * </ul>
	 * �m�F�F
	 * <ul>
	 * <li>����I���B��OClassNotFoundException���������Ȃ����Ƃ��m�F</li>
	 * <li>�Ԃ�l��Date�^�I�u�W�F�N�g�Ő������ϊ�����邱�Ƃ��m�F
	 */
	public void testParse() {
		try {
			DefaultPropertySchema dps = new DefaultPropertySchema();
			String schema = "A,java.util.Date," +
					"\"jp.ossc.nimbus.util.converter.DateFormatConverter{ConvertType=1;Format=\"yyyy-MM-DD\"}\",,";
			dps.setSchema(schema);
			Calendar cal = Calendar.getInstance();
			cal.set(2008, 0, 22);
			Date date = cal.getTime();
			assertEquals(dps.parse(date), "2008-01-22");
		} catch (PropertySchemaDefineException e) {
			e.printStackTrace();
			fail("��O����");
		}
	}

	/**
	 * �v���p�e�B�̒l�̐ݒ�`�F�b�N�e�X�g�B(Date)
	 * <p>
	 * �����F
	 * <ul>
	 * <li>���̃X�L�[�}���w�肵��setSchema(String schema)�����s����</li>
	 * <li>�X�L�[�}�F"A,java.util.Date,,,"(���͕ϊ��w��Ȃ�)</li>
	 * <li>���̒l���w�肵��parse(Object val)�����s����</li>
	 * <li>Date�^�I�u�W�F�N�g</li>
	 * </ul>
	 * �m�F�F
	 * <ul>
	 * <li>����I���B��OClassNotFoundException���������Ȃ����Ƃ��m�F</li>
	 * <li>�Ԃ�l���w�肵���l�Ɠ������Ƃ��m�F
	 */
	public void testParseNullDate() {
		try {
			DefaultPropertySchema dps = new DefaultPropertySchema();
			String schema = "A,java.util.Date,,,";
			dps.setSchema(schema);
			String vals = "2008/02/04 12:11:10 100";
			Date val = new java.text.SimpleDateFormat("yyyy/MM/dd HH:mm:ss SSS").parse(vals);
			assertEquals(val, (Date)dps.parse(vals));
		} catch (PropertySchemaDefineException e) {
			e.printStackTrace();
			fail("��O����");
		}catch (java.text.ParseException e) {
			e.printStackTrace();
			fail("��O����");
		}

	}
	
	/**
	 * �v���p�e�B�̒l�̐ݒ�`�F�b�N�e�X�g(BigDecimal)�B
	 * <p>
	 * �����F
	 * <ul>
	 * <li>���̃X�L�[�}���w�肵��setSchema(String schema)�����s����</li>
	 * <li>�X�L�[�}�F"A,java.math.BigDecimal,,,"(���͕ϊ��w��Ȃ�)</li>
	 * <li>���̒l���w�肵��parse(Object val)�����s����</li>
	 * </ul>
	 * �m�F�F
	 * <ul>
	 * <li>����I���B��OClassNotFoundException���������Ȃ����Ƃ��m�F</li>
	 * <li>�Ԃ�l���w�肵���l���Ԃ���邱�Ƃ��m�F
	 */
	public void testParseNullBigDecimal() {
		try {
			DefaultPropertySchema dps = new DefaultPropertySchema();
			String schema = "A,java.math.BigDecimal,,,";
			dps.setSchema(schema);
			String vals = "12345678901234567890.12345678";
			BigDecimal val = new BigDecimal(vals);
			assertEquals(val, (BigDecimal)dps.parse(vals));
		} catch (PropertySchemaDefineException e) {
			e.printStackTrace();
			fail("��O����");
		}
	}

	
	/**
	 * �v���p�e�B�̒l�̐ݒ�`�F�b�N�e�X�g(BigDecimal�z��)�B
	 * <p>
	 * �����F
	 * <ul>
	 * <li>���̃X�L�[�}���w�肵��setSchema(String schema)�����s����</li>
	 * <li>�X�L�[�}�F"A,java.math.BigDecimal[],,,"(���͕ϊ��w��Ȃ�)</li>
	 * <li>���̒l���w�肵��parse(Object val)�����s����</li>
	 * </ul>
	 * �m�F�F
	 * <ul>
	 * <li>����I���B��OClassNotFoundException���������Ȃ����Ƃ��m�F</li>
	 * <li>�Ԃ�l���w�肵���l���Ԃ���邱�Ƃ��m�F
	 */
	public void testParseNullBigDecimalArray() {
		try {
			DefaultPropertySchema dps = new DefaultPropertySchema();
			String schema = "A,java.math.BigDecimal[],,,";
			dps.setSchema(schema);
			String vals = "12345678901234567890.12345678,99945678901234567890.12345678";
			BigDecimal[] val = new BigDecimal[]{new BigDecimal("12345678901234567890.12345678"),
					new BigDecimal("99945678901234567890.12345678")};
			BigDecimal[] pval = (BigDecimal[])dps.parse(vals);
			assertEquals(val[0], pval[0]);
			assertEquals(val[1], pval[1]);
		} catch (PropertySchemaDefineException e) {
			e.printStackTrace();
			fail("��O����");
		}
	}

	
	/**
	 * �v���p�e�B�̒l�̐ݒ�`�F�b�N�e�X�g(BigInteger)�B
	 * <p>
	 * �����F
	 * <ul>
	 * <li>���̃X�L�[�}���w�肵��setSchema(String schema)�����s����</li>
	 * <li>�X�L�[�}�F"A,java.math.BigInteger,,,"(���͕ϊ��w��Ȃ�)</li>
	 * <li>���̒l���w�肵��parse(Object val)�����s����</li>
	 * </ul>
	 * �m�F�F
	 * <ul>
	 * <li>����I���B��OClassNotFoundException���������Ȃ����Ƃ��m�F</li>
	 * <li>�Ԃ�l���w�肵���l���Ԃ���邱�Ƃ��m�F
	 */
	public void testParseNullBigInteger() {
		try {
			DefaultPropertySchema dps = new DefaultPropertySchema();
			String schema = "A,java.math.BigInteger,,,";
			dps.setSchema(schema);
			String vals = "12345678901234567890";
			BigInteger val = new BigInteger(vals);
			assertEquals(val, (BigInteger)dps.parse(vals));
		} catch (PropertySchemaDefineException e) {
			e.printStackTrace();
			fail("��O����");
		}
	}

	
	/**
	 * �v���p�e�B�̒l�̐ݒ�`�F�b�N�e�X�g(BigInteger�z��)�B
	 * <p>
	 * �����F
	 * <ul>
	 * <li>���̃X�L�[�}���w�肵��setSchema(String schema)�����s����</li>
	 * <li>�X�L�[�}�F"A,java.math.BigInteger[],,,"(���͕ϊ��w��Ȃ�)</li>
	 * <li>���̒l���w�肵��parse(Object val)�����s����</li>
	 * </ul>
	 * �m�F�F
	 * <ul>
	 * <li>����I���B��OClassNotFoundException���������Ȃ����Ƃ��m�F</li>
	 * <li>�Ԃ�l���w�肵���l���Ԃ���邱�Ƃ��m�F
	 */
	public void testParseNullBigIntegerArray() {
		try {
			DefaultPropertySchema dps = new DefaultPropertySchema();
			String schema = "A,java.math.BigInteger[],,,";
			dps.setSchema(schema);
			String vals = "12345678901234567890,99945678901234567890";
			BigInteger[] val = new BigInteger[]{new BigInteger("12345678901234567890"),
					new BigInteger("99945678901234567890")};
			BigInteger[] pval = (BigInteger[])dps.parse(vals);
			assertEquals(val[0], pval[0]);
			assertEquals(val[1], pval[1]);
		} catch (PropertySchemaDefineException e) {
			e.printStackTrace();
			fail("��O����");
		}
	}
	
	
	
	/**
	 * �v���p�e�B�̒l�̃`�F�b�N�e�X�g�B
	 * <p>
	 * �����F
	 * <ul>
	 * <li>���̃X�L�[�}���w�肵��setSchema(String schema)�����s����</li>
	 * <li>�X�L�[�}�F"A,java.lang.String,,,"</li>
	 * <li>���̒l���w�肵��checkSchema(Object val)�����s����</li>
	 * <li>"������"</li>
	 * </ul>
	 * �m�F�F
	 * <ul>
	 * <li>����I���B��OClassNotFoundException���������Ȃ����Ƃ��m�F</li>
	 * </ul>
	 */
	public void testCheckSchema() {
		try {
			DefaultPropertySchema dps = new DefaultPropertySchema();
			String schema = "A,java.lang.String,,,";
			dps.setSchema(schema);
			String val = "������";
			dps.checkSchema(val);
		} catch (PropertySchemaDefineException e) {
			e.printStackTrace();
			fail("��O����");
		}
	}

	/**
	 * �v���p�e�B�̒l�̃`�F�b�N�e�X�g�B
	 * <p>
	 * �����F
	 * <ul>
	 * <li>���̃X�L�[�}���w�肵��setSchema(String schema)�����s����</li>
	 * <li>�X�L�[�}�F"A,int,,,</li>
	 * <li>���̒l���w�肵��checkSchema(Object val)�����s����</li>
	 * <li>Map�^�̒l (�^�̕s��v)</li>
	 * </ul>
	 * �m�F�F
	 * <ul>
	 * <li>��OPropertySchemaCheckException������������m�F</li>
	 * </ul>
	 */
	public void testCheckSchemaInvalidValue() {
		try {
			DefaultPropertySchema dps = new DefaultPropertySchema();
			String schema = "A,int,,,";
			dps.setSchema(schema);
			Map val = new HashMap();
			dps.checkSchema(val);
			fail("��O���������Ȃ����߃e�X�g���s ");
		} catch (PropertySchemaCheckException e) {
		}
	}

	/**
	 * �v���p�e�B�̒l���X�L�[�}��`�̌^�ɓK�����Ă��邩�̃`�F�b�N�e�X�g�B
	 * <p>
	 * �����F
	 * <ul>
	 * <li>���̃X�L�[�}���w�肵��setSchema(String schema)�����s����</li>
	 * <li>�X�L�[�}�F"A,java.lang.String,,,</li>
	 * <li>���̒l���w�肵��checkType(Object val)�����s����</li>
	 * <li>"������"</li>
	 * </ul>
	 * �m�F�F
	 * <ul>
	 * <li>����I���B��OClassNotFoundException���������Ȃ����Ƃ��m�F</li>
	 * </ul>
	 */
	public void testCheckTypeStr() {
		try {
			DefaultPropertySchema dps = new DefaultPropertySchema();
			String schema = "A,java.lang.String,,,";
			dps.setSchema(schema);
			String val = "������";
			dps.checkType(val);
		} catch (PropertySchemaDefineException e) {
			e.printStackTrace();
			fail("��O����");
		}
	}

	/**
	 * �v���p�e�B�̒l���X�L�[�}��`�̌^�ɓK�����Ă��邩�̃`�F�b�N�e�X�g�B
	 * <p>
	 * �����F
	 * <ul>
	 * <li>���̃X�L�[�}���w�肵��setSchema(String schema)�����s����</li>
	 * <li>�X�L�[�}�F"A,int,,,</li>
	 * <li>���̒l���w�肵��checkType(Object val)�����s����</li>
	 * <li>Integer�^�̒l</li>
	 * </ul>
	 * �m�F�F
	 * <ul>
	 * <li>����I���B��OClassNotFoundException���������Ȃ����Ƃ��m�F</li>
	 * </ul>
	 */
	public void testCheckTypeInt() {
		try {
			DefaultPropertySchema dps = new DefaultPropertySchema();
			String schema = "A,int,,,";
			dps.setSchema(schema);
			int val = 1;
			dps.checkType(new Integer(val));
		} catch (PropertySchemaDefineException e) {
			e.printStackTrace();
			fail("��O����");
		}
	}

	/**
	 * �v���p�e�B�̒l���X�L�[�}��`�̌^�ɓK�����Ă��邩�̃`�F�b�N�e�X�g�B
	 * <p>
	 * �����F
	 * <ul>
	 * <li>���̃X�L�[�}���w�肵��setSchema(String schema)�����s����</li>
	 * <li>�X�L�[�}�F"A,int,,,</li>
	 * <li>���̒l���w�肵��checkType(Object val)�����s����</li>
	 * <li>null</li>
	 * </ul>
	 * �m�F�F
	 * <ul>
	 * <li>����I���B��OClassNotFoundException���������Ȃ����Ƃ��m�F</li>
	 * </ul>
	 */
	public void testCheckTypeNull1() {
		try {
			DefaultPropertySchema dps = new DefaultPropertySchema();
			String schema = "A,int,,,";
			dps.setSchema(schema);
			dps.checkType(null);
		} catch (PropertySchemaDefineException e) {
			e.printStackTrace();
			fail("��O����");
		}
	}

	/**
	 * �v���p�e�B�̒l���X�L�[�}��`�̐���ɓK�����Ă��邩�`�F�b�N�̃`�F�b�N�e�X�g�B
	 * <p>
	 * �����F
	 * <ul>
	 * <li>���̃X�L�[�}���w�肵��setSchema(String schema)�����s����</li>
	 * <li>�X�L�[�}�F"A,java.lang.String,"jp.ossc.nimbus.util.converter.DataSetXMLConverter<BR>
	 * {1}",,"@value@ != null""</li>
	 * <li>���̒l���w�肵��checkConstrain(Object val)�����s����</li>
	 * <li>null�łȂ�������</li>
	 * </ul>
	 * �m�F�F
	 * <ul>
	 * <li>����I���B��OPropertySchemaCheckException���������Ȃ����Ƃ��m�F</li>
	 * </ul>
	 */
	public void testCheckConstrain() {
		try {
			DefaultPropertySchema dps = new DefaultPropertySchema();
			String schema = "A,java.lang.String,\"jp.ossc.nimbus.util.converter.DataSetXMLConverter{ConvertType=1}\"" +
					",,\"@value@ != null\"";
			dps.setSchema(schema);
			dps.validate("test");
		} catch (PropertySchemaDefineException e) {
			e.printStackTrace();
			fail("��O����");
		}
	}

	/**
	 * �v���p�e�B�̒l���X�L�[�}��`�̐���ɓK�����Ă��邩�`�F�b�N�̃`�F�b�N�e�X�g�B
	 * <p>
	 * �����F
	 * <ul>
	 * <li>���̃X�L�[�}���w�肵��setSchema(String schema)�����s����</li>
	 * <li>�X�L�[�}�F"A,java.lang.String,"jp.ossc.nimbus.util.converter.DataSetXMLConverter<BR>
	 * {1}",,"@value.length@ > 5""</li>
	 * <li>���̒l���w�肵��validate(Object val)�����s����</li>
	 * <li>1234 �i����ɔ�����j</li>
	 * </ul>
	 * �m�F�F
	 * <ul>
	 * <li>�߂�l��false�ł��邱�Ƃ��m�F</li>
	 * </ul>
	 */
	public void testCheckConstrainNull() {
		DefaultPropertySchema dps = new DefaultPropertySchema();
		String schema = "A,java.lang.String,\"jp.ossc.nimbus.util.converter.DataSetXMLConverter{ConvertType=1}\"" +
				",,\"@value.length@ > 5\"";
		dps.setSchema(schema);
		assertFalse(dps.validate("1234"));
	}

	/**
	 * �X�L�[�}�̕�����\�����擾����e�X�g�B
	 * <p>
	 * �����F
	 * <ul>
	 * <li>���̃X�L�[�}���w�肵��setSchema(String schema)�����s����</li>
	 * <li>�X�L�[�}�F"A,java.lang.String,"jp.ossc.nimbus.util.converter.DataSetXMLConverter<BR>
	 * {1}","jp.ossc.nimbus.util.converter.DataSetXMLConverter{1}","@value.length@ > 5""</li>
	 * <li>���̒l���w�肵��toString()�����s����</li>
	 * </ul>
	 * �m�F�F
	 * <ul>
	 * <li>���̕����񂪕Ԃ���邱�Ƃ��m�F</li>
	 * <li>"jp.ossc.nimbus.beans.dataset.DefaultPropertySchema{name=A,<BR>
	 * type=java.lang.String,parseConverter=jp.ossc.nimbus.util.converter.DataSetXMLConverter<BR>
	 * �Ŏn�܂镶����ł��邱�Ƃ��m�F
	 * 
	 * constrain=null}"</li>
	 * </ul>
	 */
	public void testToString() {
		try {
			DefaultPropertySchema dps = new DefaultPropertySchema();
			String schema = "A,java.lang.String," +
					"\"jp.ossc.nimbus.util.converter.DataSetXMLConverter{ConvertType=1}\"," +
					"\"jp.ossc.nimbus.util.converter.DataSetXMLConverter{ConvertType=1}\"," +
					"\"@value@ != null \"";
			dps.setSchema(schema);
			assertTrue(dps.toString().startsWith("jp.ossc.nimbus.beans.dataset" +
					".DefaultPropertySchema{name=A,type=java.lang.String," +
					"parseConverter=jp.ossc.nimbus.util.converter.DataSetXMLConverter")); 
		} catch (PropertySchemaDefineException e) {
			e.printStackTrace();
			fail("��O����");
		}
	}

}
