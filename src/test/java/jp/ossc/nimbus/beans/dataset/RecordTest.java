package jp.ossc.nimbus.beans.dataset;

import junit.framework.TestCase;

import java.util.*;
import java.math.*;

//
/**
 * 
 * @author S.Teshima
 * @version 1.00 �쐬: 2008/01/17 - S.Teshima
 */

public class RecordTest extends TestCase {
	public RecordTest(String arg0) {
		super(arg0);
	}

	
	 public static void main(String[] args) {
	 junit.textui.TestRunner.run(RecordTest.class); }
	 
	/**
	 * �X�L�[�}������w�肵�āA���R�[�h�𐶐�����e�X�g�B
	 * <p>
	 * �����F
	 * <ul>
	 * <li>���̃X�L�[�}���w�肵��Record(String schema)�����s����</li>
	 * <li>":A,java.lang.String,,,"</li>
	 * </ul>
	 * �m�F�F
	 * <ul>
	 * <li>����I���B��OPropertySchemaDefineException���������Ȃ����Ƃ��m�F</li>
	 * </ul>
	 */

	public void testRecordString1() {
		try {
			String schema = ":A,java.lang.String,,,";
			new Record(schema);
		} catch (PropertySchemaDefineException e) {
			e.printStackTrace();
			fail("��O����");
		}
	}

	/**
	 * �X�L�[�}������w�肵�āA���R�[�h�𐶐�����e�X�g�B
	 * <p>
	 * �����F
	 * <ul>
	 * <li>���̃X�L�[�}���w�肵��Record(String schema)�����s����</li>
	 * <li>"A,java.lang.String,,," (��؂蕶���F���w�肵�Ȃ�)</li>
	 * </ul>
	 * �m�F�F
	 * <ul>
	 * <li>��OPropertySchemaDefineException���������邱�Ƃ��m�F</li>
	 * </ul>
	 */

	public void testRecordString2() {
		try {
			String schema = "A,java.lang.String,,,";
			new Record(schema);
			fail("��O���������Ȃ����߃e�X�g���s ");
		} catch (PropertySchemaDefineException e) {
		}
	}

	/**
	 * ���R�[�h�̃X�L�[�}��������擾����e�X�g�B
	 * <p>
	 * �����F
	 * <ul>
	 * <li>���̃X�L�[�}���w�肵��Record(String schema)�����s����</li>
	 * <li>":A,java.lang.String,,,"</li>
	 * </ul>
	 * �m�F�F
	 * <ul>
	 * <li>getSchema()�����s���ĕԂ���镶���񂪎w�肵�����̂Ɠ������Ƃ��m�F</li>
	 * </ul>
	 */
	public void testGetSchema() {
		try {
			String schema = ":A,java.lang.String,,,";
			Record rec = new Record(schema);
			assertEquals(":A,java.lang.String,,,", rec.getSchema());
		} catch (PropertySchemaDefineException e) {
			e.printStackTrace();
			fail("��O����");
		}
	}

	/**
	 * ���R�[�h�̃X�L�[�}��������擾����e�X�g�B
	 * <p>
	 * �����F
	 * <ul>
	 * <li>���̃X�L�[�}���g���Ă���Record(String schema)�����s����</li>
	 * <li>":A,java.lang.String,,,"</li>
	 * </ul>
	 * �m�F�F
	 * <ul>
	 * <li>getRecordSchema()�����s���ĕԂ���郌�R�[�h�X�L�[�}��<BR>
	 * RecordSchema.getInstance(�w�肵���X�L�[�})�Ɠ������Ƃ��m�F</li>
	 * </ul>
	 */
	public void testGetRecordSchema() {
		try {
			String schema = ":A,java.lang.String,,,";
			Record rec = new Record(schema);
			assertEquals(RecordSchema.getInstance(schema), rec
					.getRecordSchema());
		} catch (PropertySchemaDefineException e) {
			e.printStackTrace();
			fail("��O����");
		}
	}

	/**
	 * ���R�[�h�̃v���p�e�B�ɒl���Z�b�g����e�X�g�B
	 * <p>
	 * �����F
	 * <ul>
	 * <li>���̃X�L�[�}���w�肵��Record(String schema)�����s����</li>
	 * <li>":A,java.lang.String,,,"</li>
	 * <li>���̒l���w�肵��setProperty(String name, Object val)�����s����</li>
	 * <li>name :"A"</li>
	 * <li>Object:"a"</li>
	 * </ul>
	 * �m�F�F
	 * <ul>
	 * <li>����I���B��OPropertySchemaDefineException���������Ȃ����Ƃ��m�F</li>
	 * <li>values.get(name)�Ŏw�肵���l���Ԃ���邱�Ƃ��m�F</li>
	 * </ul>
	 */
	public void testSetPropertyStringObject1() {
		try {
			String schema = ":A,java.lang.String,,,";
			Record rec = new Record(schema);
			rec.setProperty("A", "a");
			assertEquals("a", rec.get("A"));
		} catch (PropertySchemaDefineException e) {
			e.printStackTrace();
			fail("��O����");
		}
	}

	/**
	 * ���R�[�h�̃v���p�e�B�ɒl���Z�b�g����e�X�g�B
	 * <p>
	 * �����F
	 * <ul>
	 * <li>���̃X�L�[�}���w�肵��Record(String schema)�����s����</li>
	 * <li>":A,java.lang.String,,,"</li>
	 * <li>���̒l���w�肵��setProperty(String name, Object val)�����s����</li>
	 * <li>name :"B"(���݂��Ȃ����O)</li>
	 * <li>Object:"a"</li>
	 * </ul>
	 * �m�F�F
	 * <ul>
	 * <li>��OPropertySetException���������邱�Ƃ��m�F</li>
	 * <li>��O���b�Z�[�W��"No such property : B"���Ԃ���邱�Ƃ��m�F</li>
	 * </ul>
	 */
	public void testSetPropertyStringObject2() {
		try {
			String schema = ":A,java.lang.String,,,";
			Record rec = new Record(schema);
			rec.setProperty("B", "a");
			fail("��O���������Ȃ����߃e�X�g���s ");
		} catch (PropertySetException e) {
			assertEquals("No such property : B", e.getMessage());
		}
	}

	/**
	 * ���R�[�h�̃v���p�e�B�ɒl���Z�b�g����e�X�g�B
	 * <p>
	 * �����F
	 * <ul>
	 * <li>���̃X�L�[�}���w�肵��Record(String schema)�����s����</li>
	 * <li>":A,java.lang.String,,,"</li>
	 * <li>���̒l���w�肵��setProperty(int index, Object val)�����s����</li>
	 * <li>index : 0</li>
	 * <li>Object:"a"</li>
	 * </ul>
	 * �m�F�F
	 * <ul>
	 * <li>����I���B��OPropertySetException���������Ȃ����Ƃ��m�F</li>
	 * </ul>
	 */
	public void testSetPropertyIntObject1() {
		try {
			String schema = ":A,java.lang.String,,,";
			Record rec = new Record(schema);
			rec.setProperty(0, "a");
			assertEquals("a", rec.get("A"));
		} catch (PropertySchemaDefineException e) {
			e.printStackTrace();
			fail("��O����");
		}
	}

	/**
	 * ���R�[�h�̃v���p�e�B�ɒl���Z�b�g����e�X�g�B
	 * <p>
	 * �����F
	 * <ul>
	 * <li>���̃X�L�[�}���w�肵��Record(String schema)�����s����</li>
	 * <li>":A,java.lang.String,,,"</li>
	 * <li>���̒l���w�肵��setProperty(int index, Object val)�����s����</li>
	 * <li>index : 1 (���݂��Ȃ��C���f�b�N�X)</li>
	 * <li>Object:"a"</li>
	 * </ul>
	 * �m�F�F
	 * <ul>
	 * <li>��OPropertySetException���������邱�Ƃ��m�F</li>
	 * </ul>
	 */
	public void testSetPropertyIntObject2() {
		try {
			String schema = ":A,java.lang.String,,,";
			Record rec = new Record(schema);
			rec.setProperty(1, "a");
			fail("��O���������Ȃ����߃e�X�g���s ");
		} catch (PropertySetException e) {
		}
	}

	/**
	 * ���R�[�h�̃v���p�e�B�ɒl���Z�b�g����e�X�g�B
	 * <p>
	 * �����F
	 * <ul>
	 * <li>���̃X�L�[�}���w�肵��Record(String schema)�����s����</li>
	 * <li>":A,boolean,,,"</li>
	 * <li>���̒l���w�肵��setProperty(String name, boolean val)�����s����</li>
	 * <li>name :"A"</li>
	 * <li>Object:Boolean�l</li>
	 * </ul>
	 * �m�F�F
	 * <ul>
	 * <li>����I���B��OPropertySetException���������Ȃ����Ƃ��m�F</li>
	 * </ul>
	 */
	public void testSetPropertyStringBoolean1() {
		try {
			String schema = ":A,boolean,,,";
			Record rec = new Record(schema);
			rec.setProperty("A", true);
			assertEquals(new Boolean(true), rec.get("A"));
		} catch (PropertySchemaDefineException e) {
			e.printStackTrace();
			fail("��O����");
		}
	}

	/**
	 * ���R�[�h�̃v���p�e�B�ɒl���Z�b�g����e�X�g�B
	 * <p>
	 * �����F
	 * <ul>
	 * <li>���̃X�L�[�}���w�肵��Record(String schema)�����s����</li>
	 * <li>":A,boolean,,,"</li>
	 * <li>���̒l���w�肵��setProperty(String name, boolean val)�����s����</li>
	 * <li>name :"B"</li>
	 * <li>val:Boolean�l</li>
	 * </ul>
	 * �m�F�F
	 * <ul>
	 * <li>��OPropertySetException���������邱�Ƃ��m�F</li>
	 * </ul>
	 */
	public void testSetPropertyStringBoolean2() {
		try {
			String schema = ":A,boolean,,,";
			Record rec = new Record(schema);
			rec.setProperty("B", true);
			fail("��O���������Ȃ����߃e�X�g���s ");
		} catch (PropertySetException e) {
		}
	}

	/**
	 * ���R�[�h�̃v���p�e�B�ɒl���Z�b�g����e�X�g�B
	 * <p>
	 * �����F
	 * <ul>
	 * <li>���̃X�L�[�}���w�肵��Record(String schema)�����s����</li>
	 * <li>":A,boolean,,,"</li>
	 * <li>���̒l���w�肵��setProperty(int index, boolean val)�����s����</li>
	 * <li>index : 0</li>
	 * <li>val:Boolean�l</li>
	 * </ul>
	 * �m�F�F
	 * <ul>
	 * <li>����I���B��OPropertySetException���������Ȃ����Ƃ��m�F</li>
	 * </ul>
	 */
	public void testSetPropertyIntBoolean1() {
		try {
			String schema = ":A,boolean,,,";
			Record rec = new Record(schema);
			rec.setProperty(0, true);
			assertEquals(new Boolean(true), rec.get("A"));
		} catch (PropertySchemaDefineException e) {
			e.printStackTrace();
			fail("��O����");
		}
	}

	/**
	 * ���R�[�h�̃v���p�e�B�ɒl���Z�b�g����e�X�g�B
	 * <p>
	 * �����F
	 * <ul>
	 * <li>���̃X�L�[�}���w�肵��Record(String schema)�����s����</li>
	 * <li>":A,boolean,,,"</li>
	 * <li>���̒l���w�肵��setProperty(int index, boolean val)�����s����</li>
	 * <li>index : 1 (���݂��Ȃ��C���f�b�N�X)</li>
	 * <li>val:Boolean�l</li>
	 * </ul>
	 * �m�F�F
	 * <ul>
	 * <li>����I���B��OPropertySetException���������邱�Ƃ��m�F</li>
	 * </ul>
	 */
	public void testSetPropertyIntBoolean2() {
		try {
			String schema = ":A,boolean,,,";
			Record rec = new Record(schema);
			rec.setProperty(1, true);
			fail("��O���������Ȃ����߃e�X�g���s ");
		} catch (PropertySetException e) {
		}
	}

	/**
	 * ���R�[�h�̃v���p�e�B�ɒl���Z�b�g����e�X�g�B
	 * <p>
	 * �����F
	 * <ul>
	 * <li>���̃X�L�[�}���w�肵��Record(String schema)�����s����</li>
	 * <li>":A,byte,,,"</li>
	 * <li>���̒l���w�肵��setProperty(String name, byte val)�����s����</li>
	 * <li>name :"A"</li>
	 * <li>val:byte�l</li>
	 * </ul>
	 * �m�F�F
	 * <ul>
	 * <li>����I���B��OPropertySetException���������Ȃ����Ƃ��m�F</li>
	 * </ul>
	 */
	public void testSetPropertyStringByte1() {
		try {
			String schema = ":A,byte,,,";
			Record rec = new Record(schema);
			rec.setProperty("A", (byte) 1);
			assertEquals(new Byte((byte) 1), rec.get("A"));
		} catch (PropertySchemaDefineException e) {
			e.printStackTrace();
			fail("��O����");
		}
	}

	/**
	 * ���R�[�h�̃v���p�e�B�ɒl���Z�b�g����e�X�g�B
	 * <p>
	 * �����F
	 * <ul>
	 * <li>���̃X�L�[�}���w�肵��Record(String schema)�����s����</li>
	 * <li>":A,byte,,,"</li>
	 * <li>���̒l���w�肵��setProperty(String name, byte val)�����s����</li>
	 * <li>name :"B"</li>
	 * <li>val:byte�l</li>
	 * </ul>
	 * �m�F�F
	 * <ul>
	 * <li>��OPropertySetException���������邱�Ƃ��m�F</li>
	 * </ul>
	 */
	public void testSetPropertyStringByte2() {
		try {
			String schema = ":A,byte,,,";
			Record rec = new Record(schema);
			rec.setProperty("B", (byte) 1);
			fail("��O���������Ȃ����߃e�X�g���s ");
		} catch (PropertySetException e) {
		}
	}

	/**
	 * ���R�[�h�̃v���p�e�B�ɒl���Z�b�g����e�X�g�B
	 * <p>
	 * �����F
	 * <ul>
	 * <li>���̃X�L�[�}���w�肵��Record(String schema)�����s����</li>
	 * <li>":A,byte,,,"</li>
	 * <li>���̒l���w�肵��setProperty(int index, byte val)�����s����</li>
	 * <li>index : 0</li>
	 * <li>val:Byte�l</li>
	 * </ul>
	 * �m�F�F
	 * <ul>
	 * <li>����I���B��OPropertySetException���������Ȃ����Ƃ��m�F</li>
	 * </ul>
	 */
	public void testSetPropertyIntByte1() {
		try {
			String schema = ":A,byte,,,";
			Record rec = new Record(schema);
			rec.setProperty(0, (byte) 1);
			assertEquals(new Byte((byte) 1), rec.get("A"));
		} catch (PropertySchemaDefineException e) {
			e.printStackTrace();
			fail("��O����");
		}
	}

	/**
	 * ���R�[�h�̃v���p�e�B�ɒl���Z�b�g����e�X�g�B
	 * <p>
	 * �����F
	 * <ul>
	 * <li>���̃X�L�[�}���w�肵��Record(String schema)�����s����</li>
	 * <li>":A,byte,,,"</li>
	 * <li>���̒l���w�肵��setProperty(int index, byte val)�����s����</li>
	 * <li>index : 1 (���݂��Ȃ��C���f�b�N�X)</li>
	 * <li>val:byte�l</li>
	 * </ul>
	 * �m�F�F
	 * <ul>
	 * <li>����I���B��OPropertySetException���������邱�Ƃ��m�F</li>
	 * </ul>
	 */
	public void testSetPropertyIntByte2() {
		try {
			String schema = ":A,byte,,,";
			Record rec = new Record(schema);
			rec.setProperty(1, (byte) 1);
			fail("��O���������Ȃ����߃e�X�g���s ");
		} catch (PropertySetException e) {
		}
	}

	/**
	 * ���R�[�h�̃v���p�e�B�ɒl���Z�b�g����e�X�g�B
	 * <p>
	 * �����F
	 * <ul>
	 * <li>���̃X�L�[�}���w�肵��Record(String schema)�����s����</li>
	 * <li>":A,char,,,"</li>
	 * <li>���̒l���w�肵��setProperty(String name, char val)�����s����</li>
	 * <li>name :"A"</li>
	 * <li>val:char�l</li>
	 * </ul>
	 * �m�F�F
	 * <ul>
	 * <li>����I���B��OPropertySetException���������Ȃ����Ƃ��m�F</li>
	 * </ul>
	 */
	public void testSetPropertyStringChar1() {
		try {
			String schema = ":A,char,,,";
			Record rec = new Record(schema);
			rec.setProperty("A", 'a');
			assertEquals(new Character('a'), rec.get("A"));
		} catch (PropertySchemaDefineException e) {
			e.printStackTrace();
			fail("��O����");
		}
	}

	/**
	 * ���R�[�h�̃v���p�e�B�ɒl���Z�b�g����e�X�g�B
	 * <p>
	 * �����F
	 * <ul>
	 * <li>���̃X�L�[�}���w�肵��Record(String schema)�����s����</li>
	 * <li>":A,char,,,"</li>
	 * <li>���̒l���w�肵��setProperty(String name, char val)�����s����</li>
	 * <li>name :"B"�i���݂��Ȃ����O�j</li>
	 * <li>val:char�l</li>
	 * </ul>
	 * �m�F�F
	 * <ul>
	 * <li>��OPropertySetException���������邱�Ƃ��m�F</li>
	 * </ul>
	 */
	public void testSetPropertyStringChar2() {
		try {
			String schema = ":A,char,,,";
			Record rec = new Record(schema);
			rec.setProperty("B", 'a');
			fail("��O���������Ȃ����߃e�X�g���s ");
		} catch (PropertySetException e) {
		}
	}

	/**
	 * ���R�[�h�̃v���p�e�B�ɒl���Z�b�g����e�X�g�B
	 * <p>
	 * �����F
	 * <ul>
	 * <li>���̃X�L�[�}���w�肵��Record(String schema)�����s����</li>
	 * <li>":A,char,,,"</li>
	 * <li>���̒l���w�肵��setProperty(int index, char val)�����s����</li>
	 * <li>index : 0</li>
	 * <li>val:char�l</li>
	 * </ul>
	 * �m�F�F
	 * <ul>
	 * <li>����I���B��OPropertySetException���������Ȃ����Ƃ��m�F</li>
	 * </ul>
	 */
	public void testSetPropertyIntChar1() {
		try {
			String schema = ":A,char,,,";
			Record rec = new Record(schema);
			rec.setProperty(0, 'a');
			assertEquals(new Character('a'), rec.get("A"));
		} catch (PropertySchemaDefineException e) {
			e.printStackTrace();
			fail("��O����");
		}
	}

	/**
	 * ���R�[�h�̃v���p�e�B�ɒl���Z�b�g����e�X�g�B
	 * <p>
	 * �����F
	 * <ul>
	 * <li>���̃X�L�[�}���w�肵��Record(String schema)�����s����</li>
	 * <li>":A,char,,,"</li>
	 * <li>���̒l���w�肵��setProperty(int index, char val)�����s����</li>
	 * <li>index : 1 (���݂��Ȃ��C���f�b�N�X)</li>
	 * <li>val:char�l</li>
	 * </ul>
	 * �m�F�F
	 * <ul>
	 * <li>����I���B��OPropertySetException���������邱�Ƃ��m�F</li>
	 * </ul>
	 */
	public void testSetPropertyIntChar2() {
		try {
			String schema = ":A,char,,,";
			Record rec = new Record(schema);
			rec.setProperty(1, 'a');
			fail("��O���������Ȃ����߃e�X�g���s ");
		} catch (PropertySetException e) {

		}
	}


	/**
	 * ���R�[�h�̃v���p�e�B�ɒl���Z�b�g����e�X�g�B(Byte->Short)
	 * <p>
	 * �����F
	 * <ul>
	 * <li>���̃X�L�[�}���w�肵��Record(String schema)�����s����</li>
	 * <li>":A,short,,,"</li>
	 * <li>���̒l���w�肵��setProperty(String name, Byte val)�����s����</li>
	 * <li>name :"A"</li>
	 * <li>val:byte�l</li>
	 * </ul>
	 * �m�F�F
	 * <ul>
	 * <li>����I���B��OPropertySetException���������Ȃ����Ƃ��m�F</li>
	 * <li>getProperty()��short�l���Ԃ��Ă��邱�Ƃ��m�F</li>
	 * </ul>
	 */
	public void testSetPropertyByteToShort() {
		try {
			String schema = ":A,short,,,";
			Record rec = new Record(schema);
			rec.setProperty(0, (byte) 1);
			assertEquals(new Short((byte) 1), rec.get("A"));
		} catch (PropertySchemaDefineException e) {
			e.printStackTrace();
			fail("��O����");
		}
	}

	/**
	 * ���R�[�h�̃v���p�e�B�ɒl���Z�b�g����e�X�g�B(Byte->int)
	 * <p>
	 * �����F
	 * <ul>
	 * <li>���̃X�L�[�}���w�肵��Record(String schema)�����s����</li>
	 * <li>":A,int,,,"</li>
	 * <li>���̒l���w�肵��setProperty(String name, Byte val)�����s����</li>
	 * <li>name :"A"</li>
	 * <li>val:byte�l</li>
	 * </ul>
	 * �m�F�F
	 * <ul>
	 * <li>����I���B��OPropertySetException���������Ȃ����Ƃ��m�F</li>
	 * <li>getProperty()��int�l���Ԃ��Ă��邱�Ƃ��m�F</li>
	 * </ul>
	 */
	public void testSetPropertyByteToInt() {
		try {
			String schema = ":A,int,,,";
			Record rec = new Record(schema);
			rec.setProperty(0, (byte) 1);
			assertEquals(new Integer((byte) 1), rec.get("A"));
		} catch (PropertySchemaDefineException e) {
			e.printStackTrace();
			fail("��O����");
		}
	}

	/**
	 * ���R�[�h�̃v���p�e�B�ɒl���Z�b�g����e�X�g�B(short->int)
	 * <p>
	 * �����F
	 * <ul>
	 * <li>���̃X�L�[�}���w�肵��Record(String schema)�����s����</li>
	 * <li>":A,int,,,"</li>
	 * <li>���̒l���w�肵��setProperty(String name, short val)�����s����</li>
	 * <li>name :"A"</li>
	 * <li>val:short�l</li>
	 * </ul>
	 * �m�F�F
	 * <ul>
	 * <li>����I���B��OPropertySetException���������Ȃ����Ƃ��m�F</li>
	 * <li>getProperty()��int�l���Ԃ��Ă��邱�Ƃ��m�F</li>
	 * </ul>
	 */
	public void testSetPropertyShortToInt() {
		try {
			String schema = ":A,int,,,";
			Record rec = new Record(schema);
			rec.setProperty(0, (short) 1);
			assertEquals(new Integer((short) 1), rec.get("A"));
		} catch (PropertySchemaDefineException e) {
			e.printStackTrace();
			fail("��O����");
		}
	}


	/**
	 * ���R�[�h�̃v���p�e�B�ɒl���Z�b�g����e�X�g�B(Byte->long)
	 * <p>
	 * �����F
	 * <ul>
	 * <li>���̃X�L�[�}���w�肵��Record(String schema)�����s����</li>
	 * <li>":A,long,,,"</li>
	 * <li>���̒l���w�肵��setProperty(String name, Byte val)�����s����</li>
	 * <li>name :"A"</li>
	 * <li>val:byte�l</li>
	 * </ul>
	 * �m�F�F
	 * <ul>
	 * <li>����I���B��OPropertySetException���������Ȃ����Ƃ��m�F</li>
	 * <li>getProperty()��long�l���Ԃ��Ă��邱�Ƃ��m�F</li>
	 * </ul>
	 */
	public void testSetPropertyByteToLong() {
		try {
			String schema = ":A,long,,,";
			Record rec = new Record(schema);
			rec.setProperty(0, (byte) 1);
			assertEquals(new Long((byte) 1), rec.get("A"));
		} catch (PropertySchemaDefineException e) {
			e.printStackTrace();
			fail("��O����");
		}
	}

	/**
	 * ���R�[�h�̃v���p�e�B�ɒl���Z�b�g����e�X�g�B(short->long)
	 * <p>
	 * �����F
	 * <ul>
	 * <li>���̃X�L�[�}���w�肵��Record(String schema)�����s����</li>
	 * <li>":A,long,,,"</li>
	 * <li>���̒l���w�肵��setProperty(String name, short val)�����s����</li>
	 * <li>name :"A"</li>
	 * <li>val:short�l</li>
	 * </ul>
	 * �m�F�F
	 * <ul>
	 * <li>����I���B��OPropertySetException���������Ȃ����Ƃ��m�F</li>
	 * <li>getProperty()��long�l���Ԃ��Ă��邱�Ƃ��m�F</li>
	 * </ul>
	 */
	public void testSetPropertyShortToLong() {
		try {
			String schema = ":A,long,,,";
			Record rec = new Record(schema);
			rec.setProperty(0, (short) 1);
			assertEquals(new Long((short) 1), rec.get("A"));
		} catch (PropertySchemaDefineException e) {
			e.printStackTrace();
			fail("��O����");
		}
	}


	/**
	 * ���R�[�h�̃v���p�e�B�ɒl���Z�b�g����e�X�g�B(intt->long)
	 * <p>
	 * �����F
	 * <ul>
	 * <li>���̃X�L�[�}���w�肵��Record(String schema)�����s����</li>
	 * <li>":A,long,,,"</li>
	 * <li>���̒l���w�肵��setProperty(String name, int val)�����s����</li>
	 * <li>name :"A"</li>
	 * <li>val:int�l</li>
	 * </ul>
	 * �m�F�F
	 * <ul>
	 * <li>����I���B��OPropertySetException���������Ȃ����Ƃ��m�F</li>
	 * <li>getProperty()��long�l���Ԃ��Ă��邱�Ƃ��m�F</li>
	 * </ul>
	 */
	public void testSetPropertyIntToLong() {
		try {
			String schema = ":A,long,,,";
			Record rec = new Record(schema);
			rec.setProperty(0, (int) 1);
			assertEquals(new Long((int) 1), rec.get("A"));
		} catch (PropertySchemaDefineException e) {
			e.printStackTrace();
			fail("��O����");
		}
	}



	/**
	 * ���R�[�h�̃v���p�e�B�ɒl���Z�b�g����e�X�g�B(Byte->BigInteger)
	 * <p>
	 * �����F
	 * <ul>
	 * <li>���̃X�L�[�}���w�肵��Record(String schema)�����s����</li>
	 * <li>":A,java.math.BigInteger,,,"</li>
	 * <li>���̒l���w�肵��setProperty(String name, Byte val)�����s����</li>
	 * <li>name :"A"</li>
	 * <li>val:byte�l</li>
	 * </ul>
	 * �m�F�F
	 * <ul>
	 * <li>����I���B��OPropertySetException���������Ȃ����Ƃ��m�F</li>
	 * <li>getProperty()��BigInteger�l���Ԃ��Ă��邱�Ƃ��m�F</li>
	 * </ul>
	 */
	public void testSetPropertyByteToBigInteger() {
		try {
			String schema = ":A,java.math.BigInteger,,,";
			Record rec = new Record(schema);
			rec.setProperty(0, (byte) 1);
			assertEquals(new BigInteger("1"), rec.get("A"));
		} catch (PropertySchemaDefineException e) {
			e.printStackTrace();
			fail("��O����");
		}
	}

	/**
	 * ���R�[�h�̃v���p�e�B�ɒl���Z�b�g����e�X�g�B(short->BigInteger)
	 * <p>
	 * �����F
	 * <ul>
	 * <li>���̃X�L�[�}���w�肵��Record(String schema)�����s����</li>
	 * <li>":A,java.math.BigInteger,,,"</li>
	 * <li>���̒l���w�肵��setProperty(String name, short val)�����s����</li>
	 * <li>name :"A"</li>
	 * <li>val:short�l</li>
	 * </ul>
	 * �m�F�F
	 * <ul>
	 * <li>����I���B��OPropertySetException���������Ȃ����Ƃ��m�F</li>
	 * <li>getProperty()��BigInteger�l���Ԃ��Ă��邱�Ƃ��m�F</li>
	 * </ul>
	 */
	public void testSetPropertyShortToBigInteger() {
		try {
			String schema = ":A,java.math.BigInteger,,,";
			Record rec = new Record(schema);
			rec.setProperty(0, (short) 1);
			assertEquals(new BigInteger("1"), rec.get("A"));
		} catch (PropertySchemaDefineException e) {
			e.printStackTrace();
			fail("��O����");
		}
	}


	/**
	 * ���R�[�h�̃v���p�e�B�ɒl���Z�b�g����e�X�g�B(int->BigInteger)
	 * <p>
	 * �����F
	 * <ul>
	 * <li>���̃X�L�[�}���w�肵��Record(String schema)�����s����</li>
	 * <li>":A,java.math.BigInteger,,,"</li>
	 * <li>���̒l���w�肵��setProperty(String name, int val)�����s����</li>
	 * <li>name :"A"</li>
	 * <li>val:int�l</li>
	 * </ul>
	 * �m�F�F
	 * <ul>
	 * <li>����I���B��OPropertySetException���������Ȃ����Ƃ��m�F</li>
	 * <li>getProperty()��BigInteger�l���Ԃ��Ă��邱�Ƃ��m�F</li>
	 * </ul>
	 */
	public void testSetPropertyIntToBigInteger() {
		try {
			String schema = ":A,java.math.BigInteger,,,";
			Record rec = new Record(schema);
			rec.setProperty(0, (int) 1);
			assertEquals(new BigInteger("1"), rec.get("A"));
		} catch (PropertySchemaDefineException e) {
			e.printStackTrace();
			fail("��O����");
		}
	}


	/**
	 * ���R�[�h�̃v���p�e�B�ɒl���Z�b�g����e�X�g�B(long->BigInteger)
	 * <p>
	 * �����F
	 * <ul>
	 * <li>���̃X�L�[�}���w�肵��Record(String schema)�����s����</li>
	 * <li>":A,java.math.BigInteger,,,"</li>
	 * <li>���̒l���w�肵��setProperty(String name, long val)�����s����</li>
	 * <li>name :"A"</li>
	 * <li>val:long�l</li>
	 * </ul>
	 * �m�F�F
	 * <ul>
	 * <li>����I���B��OPropertySetException���������Ȃ����Ƃ��m�F</li>
	 * <li>getProperty()��BigInteger�l���Ԃ��Ă��邱�Ƃ��m�F</li>
	 * </ul>
	 */
	public void testSetPropertyLongToBigInteger() {
		try {
			String schema = ":A,java.math.BigInteger,,,";
			Record rec = new Record(schema);
			rec.setProperty(0, (long) 1);
			assertEquals(new BigInteger("1"), rec.get("A"));
		} catch (PropertySchemaDefineException e) {
			e.printStackTrace();
			fail("��O����");
		}
	}

	/**
	 * ���R�[�h�̃v���p�e�B�ɒl���Z�b�g����e�X�g�B(BigInteger->BigInteger)
	 * <p>
	 * �����F
	 * <ul>
	 * <li>���̃X�L�[�}���w�肵��Record(String schema)�����s����</li>
	 * <li>":A,java.math.BigInteger,,,"</li>
	 * <li>���̒l���w�肵��setProperty(String name, BigInteger val)�����s����</li>
	 * <li>name :"A"</li>
	 * <li>val:BigInteger�l</li>
	 * </ul>
	 * �m�F�F
	 * <ul>
	 * <li>����I���B��OPropertySetException���������Ȃ����Ƃ��m�F</li>
	 * <li>getProperty()��BigInteger�l���Ԃ��Ă��邱�Ƃ��m�F</li>
	 * </ul>
	 */
	public void testSetPropertyBigInteger() {
		try {
			String schema = ":A,java.math.BigInteger,,,";
			Record rec = new Record(schema);
			rec.setProperty(0, new BigInteger("1"));
			assertEquals(new BigInteger("1"), rec.get("A"));
		} catch (PropertySchemaDefineException e) {
			e.printStackTrace();
			fail("��O����");
		}
	}


	/**
	 * ���R�[�h�̃v���p�e�B�ɒl���Z�b�g����e�X�g�B(Byte->float)
	 * <p>
	 * �����F
	 * <ul>
	 * <li>���̃X�L�[�}���w�肵��Record(String schema)�����s����</li>
	 * <li>":A,float,,,"</li>
	 * <li>���̒l���w�肵��setProperty(String name, Byte val)�����s����</li>
	 * <li>name :"A"</li>
	 * <li>val:byte�l</li>
	 * </ul>
	 * �m�F�F
	 * <ul>
	 * <li>����I���B��OPropertySetException���������Ȃ����Ƃ��m�F</li>
	 * <li>getProperty()��float�l���Ԃ��Ă��邱�Ƃ��m�F</li>
	 * </ul>
	 */
	public void testSetPropertyByteToFloat() {
		try {
			String schema = ":A,float,,,";
			Record rec = new Record(schema);
			rec.setProperty(0, (byte) 1);
			assertEquals(new Float((byte)1), rec.get("A"));
		} catch (PropertySchemaDefineException e) {
			e.printStackTrace();
			fail("��O����");
		}
	}

	/**
	 * ���R�[�h�̃v���p�e�B�ɒl���Z�b�g����e�X�g�B(short->float)
	 * <p>
	 * �����F
	 * <ul>
	 * <li>���̃X�L�[�}���w�肵��Record(String schema)�����s����</li>
	 * <li>":A,float,,,"</li>
	 * <li>���̒l���w�肵��setProperty(String name, short val)�����s����</li>
	 * <li>name :"A"</li>
	 * <li>val:short�l</li>
	 * </ul>
	 * �m�F�F
	 * <ul>
	 * <li>����I���B��OPropertySetException���������Ȃ����Ƃ��m�F</li>
	 * <li>getProperty()��float�l���Ԃ��Ă��邱�Ƃ��m�F</li>
	 * </ul>
	 */
	public void testSetPropertyShortToFloat() {
		try {
			String schema = ":A,float,,,";
			Record rec = new Record(schema);
			rec.setProperty(0, (short) 1);
			assertEquals(new Float((short) 1), rec.get("A"));
		} catch (PropertySchemaDefineException e) {
			e.printStackTrace();
			fail("��O����");
		}
	}


	/**
	 * ���R�[�h�̃v���p�e�B�ɒl���Z�b�g����e�X�g�B(int->float)
	 * <p>
	 * �����F
	 * <ul>
	 * <li>���̃X�L�[�}���w�肵��Record(String schema)�����s����</li>
	 * <li>":A,javafloat,,,"</li>
	 * <li>���̒l���w�肵��setProperty(String name, int val)�����s����</li>
	 * <li>name :"A"</li>
	 * <li>val:int�l</li>
	 * </ul>
	 * �m�F�F
	 * <ul>
	 * <li>����I���B��OPropertySetException���������Ȃ����Ƃ��m�F</li>
	 * <li>getProperty()��float�l���Ԃ��Ă��邱�Ƃ��m�F</li>
	 * </ul>
	 */
	public void testSetPropertyIntToFloat() {
		try {
			String schema = ":A,float,,,";
			Record rec = new Record(schema);
			rec.setProperty(0, (int) 1);
			assertEquals(new Float((int) 1), rec.get("A"));
		} catch (PropertySchemaDefineException e) {
			e.printStackTrace();
			fail("��O����");
		}
	}


	/**
	 * ���R�[�h�̃v���p�e�B�ɒl���Z�b�g����e�X�g�B(long->float)
	 * <p>
	 * �����F
	 * <ul>
	 * <li>���̃X�L�[�}���w�肵��Record(String schema)�����s����</li>
	 * <li>":A,float,,,"</li>
	 * <li>���̒l���w�肵��setProperty(String name, long val)�����s����</li>
	 * <li>name :"A"</li>
	 * <li>val:long�l</li>
	 * </ul>
	 * �m�F�F
	 * <ul>
	 * <li>����I���B��OPropertySetException���������Ȃ����Ƃ��m�F</li>
	 * <li>getProperty()��float�l���Ԃ��Ă��邱�Ƃ��m�F</li>
	 * </ul>
	 */
	public void testSetPropertyLongToFloat() {
		try {
			String schema = ":A,float,,,";
			Record rec = new Record(schema);
			rec.setProperty(0, (long) 1);
			assertEquals(new Float((long) 1), rec.get("A"));
		} catch (PropertySchemaDefineException e) {
			e.printStackTrace();
			fail("��O����");
		}
	}

	/**
	 * ���R�[�h�̃v���p�e�B�ɒl���Z�b�g����e�X�g�B(Byte->double)
	 * <p>
	 * �����F
	 * <ul>
	 * <li>���̃X�L�[�}���w�肵��Record(String schema)�����s����</li>
	 * <li>":A,double,,,"</li>
	 * <li>���̒l���w�肵��setProperty(String name, Byte val)�����s����</li>
	 * <li>name :"A"</li>
	 * <li>val:byte�l</li>
	 * </ul>
	 * �m�F�F
	 * <ul>
	 * <li>����I���B��OPropertySetException���������Ȃ����Ƃ��m�F</li>
	 * <li>getProperty()��double�l���Ԃ��Ă��邱�Ƃ��m�F</li>
	 * </ul>
	 */
	public void testSetPropertyByteToDouble() {
		try {
			String schema = ":A,double,,,";
			Record rec = new Record(schema);
			rec.setProperty(0, (byte) 1);
			assertEquals(new Double((byte)1), rec.get("A"));
		} catch (PropertySchemaDefineException e) {
			e.printStackTrace();
			fail("��O����");
		}
	}

	/**
	 * ���R�[�h�̃v���p�e�B�ɒl���Z�b�g����e�X�g�B(short->double)
	 * <p>
	 * �����F
	 * <ul>
	 * <li>���̃X�L�[�}���w�肵��Record(String schema)�����s����</li>
	 * <li>":A,double,,,"</li>
	 * <li>���̒l���w�肵��setProperty(String name, short val)�����s����</li>
	 * <li>name :"A"</li>
	 * <li>val:short�l</li>
	 * </ul>
	 * �m�F�F
	 * <ul>
	 * <li>����I���B��OPropertySetException���������Ȃ����Ƃ��m�F</li>
	 * <li>getProperty()��double�l���Ԃ��Ă��邱�Ƃ��m�F</li>
	 * </ul>
	 */
	public void testSetPropertyShortToDouble() {
		try {
			String schema = ":A,double,,,";
			Record rec = new Record(schema);
			rec.setProperty(0, (short) 1);
			assertEquals(new Double((short) 1), rec.get("A"));
		} catch (PropertySchemaDefineException e) {
			e.printStackTrace();
			fail("��O����");
		}
	}


	/**
	 * ���R�[�h�̃v���p�e�B�ɒl���Z�b�g����e�X�g�B(int->double)
	 * <p>
	 * �����F
	 * <ul>
	 * <li>���̃X�L�[�}���w�肵��Record(String schema)�����s����</li>
	 * <li>":A,double,,,"</li>
	 * <li>���̒l���w�肵��setProperty(String name, int val)�����s����</li>
	 * <li>name :"A"</li>
	 * <li>val:int�l</li>
	 * </ul>
	 * �m�F�F
	 * <ul>
	 * <li>����I���B��OPropertySetException���������Ȃ����Ƃ��m�F</li>
	 * <li>getProperty()��float�l���Ԃ��Ă��邱�Ƃ��m�F</li>
	 * </ul>
	 */
	public void testSetPropertyIntToDouble() {
		try {
			String schema = ":A,double,,,";
			Record rec = new Record(schema);
			rec.setProperty(0, (int) 1);
			assertEquals(new Double((int) 1), rec.get("A"));
		} catch (PropertySchemaDefineException e) {
			e.printStackTrace();
			fail("��O����");
		}
	}


	/**
	 * ���R�[�h�̃v���p�e�B�ɒl���Z�b�g����e�X�g�B(long->double)
	 * <p>
	 * �����F
	 * <ul>
	 * <li>���̃X�L�[�}���w�肵��Record(String schema)�����s����</li>
	 * <li>":A,double,,,"</li>
	 * <li>���̒l���w�肵��setProperty(String name, long val)�����s����</li>
	 * <li>name :"A"</li>
	 * <li>val:long�l</li>
	 * </ul>
	 * �m�F�F
	 * <ul>
	 * <li>����I���B��OPropertySetException���������Ȃ����Ƃ��m�F</li>
	 * <li>getProperty()��double�l���Ԃ��Ă��邱�Ƃ��m�F</li>
	 * </ul>
	 */
	public void testSetPropertyLongToDouble() {
		try {
			String schema = ":A,double,,,";
			Record rec = new Record(schema);
			rec.setProperty(0, (long) 1);
			assertEquals(new Double((long) 1), rec.get("A"));
		} catch (PropertySchemaDefineException e) {
			e.printStackTrace();
			fail("��O����");
		}
	}


	/**
	 * ���R�[�h�̃v���p�e�B�ɒl���Z�b�g����e�X�g�B(float->double)
	 * <p>
	 * �����F
	 * <ul>
	 * <li>���̃X�L�[�}���w�肵��Record(String schema)�����s����</li>
	 * <li>":A,double,,,"</li>
	 * <li>���̒l���w�肵��setProperty(String name, float val)�����s����</li>
	 * <li>name :"A"</li>
	 * <li>val:float�l</li>
	 * </ul>
	 * �m�F�F
	 * <ul>
	 * <li>����I���B��OPropertySetException���������Ȃ����Ƃ��m�F</li>
	 * <li>getProperty()��double�l���Ԃ��Ă��邱�Ƃ��m�F</li>
	 * </ul>
	 */
	public void testSetPropertyFloatToDouble() {
		try {
			String schema = ":A,double,,,";
			Record rec = new Record(schema);
			rec.setProperty(0, (float) 1);
			assertEquals(new Double((float) 1), rec.get("A"));
		} catch (PropertySchemaDefineException e) {
			e.printStackTrace();
			fail("��O����");
		}
	}



	/**
	 * ���R�[�h�̃v���p�e�B�ɒl���Z�b�g����e�X�g�B(Byte->BigDecimal)
	 * <p>
	 * �����F
	 * <ul>
	 * <li>���̃X�L�[�}���w�肵��Record(String schema)�����s����</li>
	 * <li>":A,java.math.BigDecimal,,,"</li>
	 * <li>���̒l���w�肵��setProperty(String name, Byte val)�����s����</li>
	 * <li>name :"A"</li>
	 * <li>val:byte�l</li>
	 * </ul>
	 * �m�F�F
	 * <ul>
	 * <li>����I���B��OPropertySetException���������Ȃ����Ƃ��m�F</li>
	 * <li>getProperty()��BigDecimal�l���Ԃ��Ă��邱�Ƃ��m�F</li>
	 * </ul>
	 */
	public void testSetPropertyByteToBigDecimal() {
		try {
			String schema = ":A,java.math.BigDecimal,,,";
			Record rec = new Record(schema);
			rec.setProperty(0, (byte) 1);
			assertEquals(new BigDecimal("1"), rec.get("A"));
		} catch (PropertySchemaDefineException e) {
			e.printStackTrace();
			fail("��O����");
		}
	}

	/**
	 * ���R�[�h�̃v���p�e�B�ɒl���Z�b�g����e�X�g�B(short->BigDecimal)
	 * <p>
	 * �����F
	 * <ul>
	 * <li>���̃X�L�[�}���w�肵��Record(String schema)�����s����</li>
	 * <li>":A,java.math.BigDecimal,,,"</li>
	 * <li>���̒l���w�肵��setProperty(String name, short val)�����s����</li>
	 * <li>name :"A"</li>
	 * <li>val:short�l</li>
	 * </ul>
	 * �m�F�F
	 * <ul>
	 * <li>����I���B��OPropertySetException���������Ȃ����Ƃ��m�F</li>
	 * <li>getProperty()��BigDecimal�l���Ԃ��Ă��邱�Ƃ��m�F</li>
	 * </ul>
	 */
	public void testSetPropertyShortToBigDecimal() {
		try {
			String schema = ":A,java.math.BigDecimal,,,";
			Record rec = new Record(schema);
			rec.setProperty(0, (short) 1);
			assertEquals(new BigDecimal("1"), rec.get("A"));
		} catch (PropertySchemaDefineException e) {
			e.printStackTrace();
			fail("��O����");
		}
	}


	/**
	 * ���R�[�h�̃v���p�e�B�ɒl���Z�b�g����e�X�g�B(int->BigDecimal)
	 * <p>
	 * �����F
	 * <ul>
	 * <li>���̃X�L�[�}���w�肵��Record(String schema)�����s����</li>
	 * <li>":A,java.math.BigDecimal,,,"</li>
	 * <li>���̒l���w�肵��setProperty(String name, int val)�����s����</li>
	 * <li>name :"A"</li>
	 * <li>val:int�l</li>
	 * </ul>
	 * �m�F�F
	 * <ul>
	 * <li>����I���B��OPropertySetException���������Ȃ����Ƃ��m�F</li>
	 * <li>getProperty()��BigDecimal�l���Ԃ��Ă��邱�Ƃ��m�F</li>
	 * </ul>
	 */
	public void testSetPropertyIntToBigDecimal() {
		try {
			String schema = ":A,java.math.BigDecimal,,,";
			Record rec = new Record(schema);
			rec.setProperty(0, (int) 1);
			assertEquals(new BigDecimal("1"), rec.get("A"));
		} catch (PropertySchemaDefineException e) {
			e.printStackTrace();
			fail("��O����");
		}
	}


	/**
	 * ���R�[�h�̃v���p�e�B�ɒl���Z�b�g����e�X�g�B(long->BigDecimal)
	 * <p>
	 * �����F
	 * <ul>
	 * <li>���̃X�L�[�}���w�肵��Record(String schema)�����s����</li>
	 * <li>":A,java.math.BigDecimal,,,"</li>
	 * <li>���̒l���w�肵��setProperty(String name, long val)�����s����</li>
	 * <li>name :"A"</li>
	 * <li>val:long�l</li>
	 * </ul>
	 * �m�F�F
	 * <ul>
	 * <li>����I���B��OPropertySetException���������Ȃ����Ƃ��m�F</li>
	 * <li>getProperty()��BigDecimal�l���Ԃ��Ă��邱�Ƃ��m�F</li>
	 * </ul>
	 */
	public void testSetPropertyLongToBigDecimal() {
		try {
			String schema = ":A,java.math.BigDecimal,,,";
			Record rec = new Record(schema);
			rec.setProperty(0, (long) 1);
			assertEquals(new BigDecimal("1"), rec.get("A"));
		} catch (PropertySchemaDefineException e) {
			e.printStackTrace();
			fail("��O����");
		}
	}

	/**
	 * ���R�[�h�̃v���p�e�B�ɒl���Z�b�g����e�X�g�B(float->BigDecimal)
	 * <p>
	 * �����F
	 * <ul>
	 * <li>���̃X�L�[�}���w�肵��Record(String schema)�����s����</li>
	 * <li>":A,java.math.BigDecimal,,,"</li>
	 * <li>���̒l���w�肵��setProperty(String name, float val)�����s����</li>
	 * <li>name :"A"</li>
	 * <li>val:float�l</li>
	 * </ul>
	 * �m�F�F
	 * <ul>
	 * <li>����I���B��OPropertySetException���������Ȃ����Ƃ��m�F</li>
	 * <li>getProperty()��BigDecimal�l���Ԃ��Ă��邱�Ƃ��m�F</li>
	 * </ul>
	 */
	public void testSetPropertyFloatToBigDecimal() {
		try {
			String schema = ":A,java.math.BigDecimal,,,";
			Record rec = new Record(schema);
			rec.setProperty(0, (float) 1);
			assertEquals(new BigDecimal("1"), rec.get("A"));
		} catch (PropertySchemaDefineException e) {
			e.printStackTrace();
			fail("��O����");
		}
	}


	/**
	 * ���R�[�h�̃v���p�e�B�ɒl���Z�b�g����e�X�g�B(double->BigDecimal)
	 * <p>
	 * �����F
	 * <ul>
	 * <li>���̃X�L�[�}���w�肵��Record(String schema)�����s����</li>
	 * <li>":A,java.math.BigDecimal,,,"</li>
	 * <li>���̒l���w�肵��setProperty(String name, double val)�����s����</li>
	 * <li>name :"A"</li>
	 * <li>val:double�l</li>
	 * </ul>
	 * �m�F�F
	 * <ul>
	 * <li>����I���B��OPropertySetException���������Ȃ����Ƃ��m�F</li>
	 * <li>getProperty()��BigDecimal�l���Ԃ��Ă��邱�Ƃ��m�F</li>
	 * </ul>
	 */
	public void testSetPropertyDoubleToBigDecimal() {
		try {
			String schema = ":A,java.math.BigDecimal,,,";
			Record rec = new Record(schema);
			rec.setProperty(0, (double) 1);
			assertEquals(new BigDecimal("1"), rec.get("A"));
		} catch (PropertySchemaDefineException e) {
			e.printStackTrace();
			fail("��O����");
		}
	}
	
	/**
	 * ���R�[�h�̃v���p�e�B�ɒl���Z�b�g����e�X�g�B(BigDecimal->BigDecimal)
	 * <p>
	 * �����F
	 * <ul>
	 * <li>���̃X�L�[�}���w�肵��Record(String schema)�����s����</li>
	 * <li>":A,java.math.BigDecimal,,,"</li>
	 * <li>���̒l���w�肵��setProperty(String name, BigInteger val)�����s����</li>
	 * <li>name :"A"</li>
	 * <li>val:BigInteger�l</li>
	 * </ul>
	 * �m�F�F
	 * <ul>
	 * <li>����I���B��OPropertySetException���������Ȃ����Ƃ��m�F</li>
	 * <li>getProperty()��BigDecimal�l���Ԃ��Ă��邱�Ƃ��m�F</li>
	 * </ul>
	 */
	public void testSetPropertyBigDecimal() {
		try {
			String schema = ":A,java.math.BigDecimal,,,";
			Record rec = new Record(schema);
			rec.setProperty(0, new BigDecimal("1"));
			assertEquals(new BigDecimal("1"), rec.get("A"));
		} catch (PropertySchemaDefineException e) {
			e.printStackTrace();
			fail("��O����");
		}
	}



	
	
	/**
	 * ���R�[�h�̃v���p�e�B�ɒl���Z�b�g����e�X�g�B
	 * <p>
	 * �����F
	 * <ul>
	 * <li>���̃X�L�[�}���w�肵��Record(String schema)�����s����</li>
	 * <li>":A,short,,,"</li>
	 * <li>���̒l���w�肵��setProperty(String name, short val)�����s����</li>
	 * <li>name :"A"</li>
	 * <li>val:short�l</li>
	 * </ul>
	 * �m�F�F
	 * <ul>
	 * <li>����I���B��OPropertySetException���������Ȃ����Ƃ��m�F</li>
	 * </ul>
	 */
	public void testSetPropertyStringShort1() {
		try {
			String schema = ":A,short,,,";
			Record rec = new Record(schema);
			rec.setProperty(0, (short) 1);
			assertEquals(new Short((short) 1), rec.get("A"));
		} catch (PropertySchemaDefineException e) {
			e.printStackTrace();
			fail("��O����");
		}
	}

	/**
	 * ���R�[�h�̃v���p�e�B�ɒl���Z�b�g����e�X�g�B
	 * <p>
	 * �����F
	 * <ul>
	 * <li>���̃X�L�[�}���w�肵��Record(String schema)�����s����</li>
	 * <li>":A,short,,,"</li>
	 * <li>���̒l���w�肵��setProperty(String name, short val)�����s����</li>
	 * <li>name :"B"�i���݂��Ȃ����O�j</li>
	 * <li>val:short�l</li>
	 * </ul>
	 * �m�F�F
	 * <ul>
	 * <li>��OPropertySetException���������邱�Ƃ��m�F</li>
	 * </ul>
	 */
	public void testSetPropertyStringShort2() {
		try {
			String schema = ":A,short,,,";
			Record rec = new Record(schema);
			rec.setProperty("B", (short) 1);
			fail("��O���������Ȃ����߃e�X�g���s ");
		} catch (PropertySetException e) {
		}
	}

	/**
	 * ���R�[�h�̃v���p�e�B�ɒl���Z�b�g����e�X�g�B
	 * <p>
	 * �����F
	 * <ul>
	 * <li>���̃X�L�[�}���w�肵��Record(String schema)�����s����</li>
	 * <li>":A,short,,,"</li>
	 * <li>���̒l���w�肵��setProperty(int index, short val)�����s����</li>
	 * <li>index : 0</li>
	 * <li>val:short�l</li>
	 * </ul>
	 * �m�F�F
	 * <ul>
	 * <li>����I���B��OPropertySetException���������Ȃ����Ƃ��m�F</li>
	 * </ul>
	 */
	public void testSetPropertyIntShort1() {
		try {
			String schema = ":A,short,,,";
			Record rec = new Record(schema);
			rec.setProperty(0, (short) 1);
			assertEquals(new Short((short) 1), rec.get("A"));
		} catch (PropertySchemaDefineException e) {
			e.printStackTrace();
			fail("��O����");
		}
	}

	/**
	 * ���R�[�h�̃v���p�e�B�ɒl���Z�b�g����e�X�g�B
	 * <p>
	 * �����F
	 * <ul>
	 * <li>���̃X�L�[�}���w�肵��Record(String schema)�����s����</li>
	 * <li>":A,short,,,"</li>
	 * <li>���̒l���w�肵��setProperty(int index, short val)�����s����</li>
	 * <li>index : 1 (���݂��Ȃ��C���f�b�N�X)</li>
	 * <li>val:short�l</li>
	 * </ul>
	 * �m�F�F
	 * <ul>
	 * <li>����I���B��OPropertySetException���������邱�Ƃ��m�F</li>
	 * </ul>
	 */
	public void testSetPropertyIntShort2() {
		try {
			String schema = ":A,short,,,";
			Record rec = new Record(schema);
			rec.setProperty(1, (short) 1);
			fail("��O���������Ȃ����߃e�X�g���s ");
		} catch (PropertySetException e) {
		}
	}

	/**
	 * ���R�[�h�̃v���p�e�B�ɒl���Z�b�g����e�X�g�B
	 * <p>
	 * �����F
	 * <ul>
	 * <li>���̃X�L�[�}���w�肵��Record(String schema)�����s����</li>
	 * <li>":A,int,,,"</li>
	 * <li>���̒l���w�肵��setProperty(String name, int val)�����s����</li>
	 * <li>name :"A"</li>
	 * <li>val:int�l</li>
	 * </ul>
	 * �m�F�F
	 * <ul>
	 * <li>����I���B��OPropertySetException���������Ȃ����Ƃ��m�F</li>
	 * </ul>
	 */
	public void testSetPropertyStringInt1() {
		try {
			String schema = ":A,int,,,";
			Record rec = new Record(schema);
			rec.setProperty("A", (int) 1);
			assertEquals(new Integer((int) 1), rec.get("A"));
		} catch (PropertySchemaDefineException e) {
			e.printStackTrace();
			fail("��O����");
		}
	}

	/**
	 * ���R�[�h�̃v���p�e�B�ɒl���Z�b�g����e�X�g�B
	 * <p>
	 * �����F
	 * <ul>
	 * <li>���̃X�L�[�}���w�肵��Record(String schema)�����s����</li>
	 * <li>":A,int,,,"</li>
	 * <li>���̒l���w�肵��setProperty(String name, int val)�����s����</li>
	 * <li>name :"B"�i���݂��Ȃ����O�j</li>
	 * <li>val:int�l</li>
	 * </ul>
	 * �m�F�F
	 * <ul>
	 * <li>��OPropertySetException���������邱�Ƃ��m�F</li>
	 * </ul>
	 */
	public void testSetPropertyStringInt2() {
		try {
			String schema = ":A,int,,,";
			Record rec = new Record(schema);
			rec.setProperty("B", (int) 1);
			fail("��O���������Ȃ����߃e�X�g���s ");
		} catch (PropertySetException e) {
		}
	}

	/**
	 * ���R�[�h�̃v���p�e�B�ɒl���Z�b�g����e�X�g�B
	 * <p>
	 * �����F
	 * <ul>
	 * <li>���̃X�L�[�}���w�肵��Record(String schema)�����s����</li>
	 * <li>":A,int,,,"</li>
	 * <li>���̒l���w�肵��setProperty(int index, int val)�����s����</li>
	 * <li>index : 0</li>
	 * <li>val:int�l</li>
	 * </ul>
	 * �m�F�F
	 * <ul>
	 * <li>����I���B��OPropertySetException���������Ȃ����Ƃ��m�F</li>
	 * </ul>
	 */
	public void testSetPropertyIntInt1() {
		try {
			String schema = ":A,int,,,";
			Record rec = new Record(schema);
			rec.setProperty(0, (int) 1);
			assertEquals(new Integer((int) 1), rec.get("A"));
		} catch (PropertySchemaDefineException e) {
			e.printStackTrace();
			fail("��O����");
		}
	}

	/**
	 * ���R�[�h�̃v���p�e�B�ɒl���Z�b�g����e�X�g�B
	 * <p>
	 * �����F
	 * <ul>
	 * <li>���̃X�L�[�}���w�肵��Record(String schema)�����s����</li>
	 * <li>":A,int,,,"</li>
	 * <li>���̒l���w�肵��setProperty(int index, int val)�����s����</li>
	 * <li>index : 1 (���݂��Ȃ��C���f�b�N�X)</li>
	 * <li>val:int�l</li>
	 * </ul>
	 * �m�F�F
	 * <ul>
	 * <li>����I���B��OPropertySetException���������邱�Ƃ��m�F</li>
	 * </ul>
	 */
	public void testSetPropertyIntInt2() {
		try {
			String schema = ":A,int,,,";
			Record rec = new Record(schema);
			rec.setProperty(1, (int) 1);
			fail("��O���������Ȃ����߃e�X�g���s ");
		} catch (PropertySetException e) {
		}
	}

	/**
	 * ���R�[�h�̃v���p�e�B�ɒl���Z�b�g����e�X�g�B
	 * <p>
	 * �����F
	 * <ul>
	 * <li>���̃X�L�[�}���w�肵��Record(String schema)�����s����</li>
	 * <li>":A,long,,,"</li>
	 * <li>���̒l���w�肵��setProperty(String name, long val)�����s����</li>
	 * <li>name :"A"</li>
	 * <li>val:long�l</li>
	 * </ul>
	 * �m�F�F
	 * <ul>
	 * <li>����I���B��OPropertySetException���������Ȃ����Ƃ��m�F</li>
	 * </ul>
	 */
	public void testSetPropertyStringLong1() {
		try {
			String schema = ":A,long,,,";
			Record rec = new Record(schema);
			rec.setProperty("A", (long) 1);
			assertEquals(new Long((long) 1), rec.get("A"));
		} catch (PropertySchemaDefineException e) {
			e.printStackTrace();
			fail("��O����");
		}
	}

	/**
	 * ���R�[�h�̃v���p�e�B�ɒl���Z�b�g����e�X�g�B
	 * <p>
	 * �����F
	 * <ul>
	 * <li>���̃X�L�[�}���w�肵��Record(String schema)�����s����</li>
	 * <li>":A,long,,,"</li>
	 * <li>���̒l���w�肵��setProperty(String name, long val)�����s����</li>
	 * <li>name :"B"�i���݂��Ȃ����O�j</li>
	 * <li>val:long�l</li>
	 * </ul>
	 * �m�F�F
	 * <ul>
	 * <li>��OPropertySetException���������邱�Ƃ��m�F</li>
	 * </ul>
	 */
	public void testSetPropertyStringLong2() {
		try {
			String schema = ":A,long,,,";
			Record rec = new Record(schema);
			rec.setProperty("B", (long) 1);
			fail("��O���������Ȃ����߃e�X�g���s ");
		} catch (PropertySetException e) {
		}
	}

	/**
	 * ���R�[�h�̃v���p�e�B�ɒl���Z�b�g����e�X�g�B
	 * <p>
	 * �����F
	 * <ul>
	 * <li>���̃X�L�[�}���w�肵��Record(String schema)�����s����</li>
	 * <li>":A,long,,,"</li>
	 * <li>���̒l���w�肵��setProperty(int index, long val)�����s����</li>
	 * <li>index : 0</li>
	 * <li>val:long�l</li>
	 * </ul>
	 * �m�F�F
	 * <ul>
	 * <li>����I���B��OPropertySetException���������Ȃ����Ƃ��m�F</li>
	 * </ul>
	 */
	public void testSetPropertyIntLong1() {
		try {
			String schema = ":A,long,,,";
			Record rec = new Record(schema);
			rec.setProperty(0, (long) 1);
			assertEquals(new Long((long) 1), rec.get("A"));
		} catch (PropertySchemaDefineException e) {
			e.printStackTrace();
			fail("��O����");
		}
	}

	/**
	 * ���R�[�h�̃v���p�e�B�ɒl���Z�b�g����e�X�g�B
	 * <p>
	 * �����F
	 * <ul>
	 * <li>���̃X�L�[�}���w�肵��Record(String schema)�����s����</li>
	 * <li>":A,long,,,"</li>
	 * <li>���̒l���w�肵��setProperty(int index, long val)�����s����</li>
	 * <li>index : 1 (���݂��Ȃ��C���f�b�N�X)</li>
	 * <li>val:long�l</li>
	 * </ul>
	 * �m�F�F
	 * <ul>
	 * <li>����I���B��OPropertySetException���������邱�Ƃ��m�F</li>
	 * </ul>
	 */
	public void testSetPropertyIntLong2() {
		try {
			String schema = ":A,long,,,";
			Record rec = new Record(schema);
			rec.setProperty(1, (long) 1);
			fail("��O���������Ȃ����߃e�X�g���s ");
		} catch (PropertySetException e) {
		}
	}

	/**
	 * ���R�[�h�̃v���p�e�B�ɒl���Z�b�g����e�X�g�B
	 * <p>
	 * �����F
	 * <ul>
	 * <li>���̃X�L�[�}���w�肵��Record(String schema)�����s����</li>
	 * <li>":A,float,,,"</li>
	 * <li>���̒l���w�肵��setProperty(String name, float val)�����s����</li>
	 * <li>name :"A"</li>
	 * <li>val:float�l</li>
	 * </ul>
	 * �m�F�F
	 * <ul>
	 * <li>����I���B��OPropertySetException���������Ȃ����Ƃ��m�F</li>
	 * </ul>
	 */
	public void testSetPropertyStringFloat1() {
		try {
			String schema = ":A,float,,,";
			Record rec = new Record(schema);
			rec.setProperty("A", (float) 1);
			assertEquals(new Float((float) 1), rec.get("A"));
		} catch (PropertySchemaDefineException e) {
			e.printStackTrace();
			fail("��O����");
		}
	}

	/**
	 * ���R�[�h�̃v���p�e�B�ɒl���Z�b�g����e�X�g�B
	 * <p>
	 * �����F
	 * <ul>
	 * <li>���̃X�L�[�}���w�肵��Record(String schema)�����s����</li>
	 * <li>":A,float,,,"</li>
	 * <li>���̒l���w�肵��setProperty(String name, float val)�����s����</li>
	 * <li>name :"B"�i���݂��Ȃ����O�j</li>
	 * <li>val:float�l</li>
	 * </ul>
	 * �m�F�F
	 * <ul>
	 * <li>��OPropertySetException���������邱�Ƃ��m�F</li>
	 * </ul>
	 */
	public void testSetPropertyStringFloat2() {
		try {
			String schema = ":A,float,,,";
			Record rec = new Record(schema);
			rec.setProperty("B", (float) 1);
			fail("��O���������Ȃ����߃e�X�g���s ");
		} catch (PropertySetException e) {
		}
	}

	/**
	 * ���R�[�h�̃v���p�e�B�ɒl���Z�b�g����e�X�g�B
	 * <p>
	 * �����F
	 * <ul>
	 * <li>���̃X�L�[�}���w�肵��Record(String schema)�����s����</li>
	 * <li>":A,float,,,"</li>
	 * <li>���̒l���w�肵��setProperty(int index, float val)�����s����</li>
	 * <li>index : 0</li>
	 * <li>val:float�l</li>
	 * </ul>
	 * �m�F�F
	 * <ul>
	 * <li>����I���B��OPropertySetException���������Ȃ����Ƃ��m�F</li>
	 * </ul>
	 */
	public void testSetPropertyIntFloat1() {
		try {
			String schema = ":A,float,,,";
			Record rec = new Record(schema);
			rec.setProperty(0, (float) 1);
			assertEquals(new Float((float) 1), rec.get("A"));
		} catch (PropertySchemaDefineException e) {
			e.printStackTrace();
			fail("��O����");
		}
	}

	/**
	 * ���R�[�h�̃v���p�e�B�ɒl���Z�b�g����e�X�g�B
	 * <p>
	 * �����F
	 * <ul>
	 * <li>���̃X�L�[�}���w�肵��Record(String schema)�����s����</li>
	 * <li>":A,float,,,"</li>
	 * <li>���̒l���w�肵��setProperty(int index, float val)�����s����</li>
	 * <li>index : 1 (���݂��Ȃ��C���f�b�N�X)</li>
	 * <li>val:float�l</li>
	 * </ul>
	 * �m�F�F
	 * <ul>
	 * <li>����I���B��OPropertySetException���������邱�Ƃ��m�F</li>
	 * </ul>
	 */
	public void testSetPropertyIntFloat2() {
		try {
			String schema = ":A,float,,,";
			Record rec = new Record(schema);
			rec.setProperty(1, (float) 1);
			fail("��O���������Ȃ����߃e�X�g���s ");
		} catch (PropertySetException e) {
		}
	}

	/**
	 * ���R�[�h�̃v���p�e�B�ɒl���Z�b�g����e�X�g�B
	 * <p>
	 * �����F
	 * <ul>
	 * <li>���̃X�L�[�}���w�肵��Record(String schema)�����s����</li>
	 * <li>":A,double,,,"</li>
	 * <li>���̒l���w�肵��setProperty(String name, double val)�����s����</li>
	 * <li>name :"A"</li>
	 * <li>val:double�l</li>
	 * </ul>
	 * �m�F�F
	 * <ul>
	 * <li>����I���B��OPropertySetException���������Ȃ����Ƃ��m�F</li>
	 * </ul>
	 */
	public void testSetPropertyStringDouble1() {
		try {
			String schema = ":A,double,,,";
			Record rec = new Record(schema);
			rec.setProperty("A", (double) 1);
			assertEquals(new Double((double) 1), rec.get("A"));
		} catch (PropertySchemaDefineException e) {
			e.printStackTrace();
			fail("��O����");
		}
	}

	/**
	 * ���R�[�h�̃v���p�e�B�ɒl���Z�b�g����e�X�g�B
	 * <p>
	 * �����F
	 * <ul>
	 * <li>���̃X�L�[�}���w�肵��Record(String schema)�����s����</li>
	 * <li>":A,double,,,"</li>
	 * <li>���̒l���w�肵��setProperty(String name, double val)�����s����</li>
	 * <li>name :"B"�i���݂��Ȃ����O�j</li>
	 * <li>val:double�l</li>
	 * </ul>
	 * �m�F�F
	 * <ul>
	 * <li>��OPropertySetException���������邱�Ƃ��m�F</li>
	 * </ul>
	 */
	public void testSetPropertyStringDouble2() {
		try {
			String schema = ":A,double,,,";
			Record rec = new Record(schema);
			rec.setProperty("B", (double) 1);
			fail("��O���������Ȃ����߃e�X�g���s ");
		} catch (PropertySetException e) {
		}
	}

	/**
	 * ���R�[�h�̃v���p�e�B�ɒl���Z�b�g����e�X�g�B
	 * <p>
	 * �����F
	 * <ul>
	 * <li>���̃X�L�[�}���w�肵��Record(String schema)�����s����</li>
	 * <li>":A,double,,,"</li>
	 * <li>���̒l���w�肵��setProperty(int index, double val)�����s����</li>
	 * <li>index : 0</li>
	 * <li>val:double�l</li>
	 * </ul>
	 * �m�F�F
	 * <ul>
	 * <li>����I���B��OPropertySetException���������Ȃ����Ƃ��m�F</li>
	 * </ul>
	 */
	public void testSetPropertyIntDouble1() {
		try {
			String schema = ":A,double,,,";
			Record rec = new Record(schema);
			rec.setProperty(0, (double) 1);
			assertEquals(new Double((double) 1), rec.get("A"));
		} catch (PropertySchemaDefineException e) {
			e.printStackTrace();
			fail("��O����");
		}
	}

	/**
	 * ���R�[�h�̃v���p�e�B�ɒl���Z�b�g����e�X�g�B
	 * <p>
	 * �����F
	 * <ul>
	 * <li>���̃X�L�[�}���w�肵��Record(String schema)�����s����</li>
	 * <li>":A,double,,,"</li>
	 * <li>���̒l���w�肵��setProperty(int index, float val)�����s����</li>
	 * <li>index : 1 (���݂��Ȃ��C���f�b�N�X)</li>
	 * <li>val:double�l</li>
	 * </ul>
	 * �m�F�F
	 * <ul>
	 * <li>����I���B��OPropertySetException���������邱�Ƃ��m�F</li>
	 * </ul>
	 */
	public void testSetPropertyIntDouble2() {
		try {
			String schema = ":A,double,,,";
			Record rec = new Record(schema);
			rec.setProperty(1, (double) 1);
			fail("��O���������Ȃ����߃e�X�g���s ");
		} catch (PropertySetException e) {
		}
	}

	/**
	 * �v���p�e�B�̒l���擾����e�X�g�B
	 * <p>
	 * �����F
	 * <ul>
	 * <li>���̃X�L�[�}���w�肵��Record(String schema)�����s����</li>
	 * <li>":A,java.lang.String,,,"</li>
	 * <li>���̒l���w�肵��setProperty(String name, Object val)�����s����</li>
	 * <li>name :"A"</li>
	 * <li>Object:"a"</li>
	 * <li>���̒l���w�肵��getProperty(String name)�����s����</li>
	 * <li>name :"A"</li>
	 * </ul>
	 * �m�F�F
	 * <ul>
	 * <li>����I���B��OPropertySchemaDefineException���������Ȃ����Ƃ��m�F</li>
	 * <li>getProperty("A")�����s���āA"a"���Ԃ��Ă��邱�Ƃ��m�F</li>
	 * </ul>
	 */
	public void testGetPropertyString1() {
		try {
			String schema = ":A,java.lang.String,,,";
			Record rec = new Record(schema);
			rec.setProperty("A", "a");
			assertEquals("a", rec.getProperty("A"));
		} catch (PropertySchemaDefineException e) {
			e.printStackTrace();
			fail("��O����");
		}
	}

	/**
	 * �v���p�e�B�̒l���擾����e�X�g�B
	 * <p>
	 * �����F
	 * <ul>
	 * <li>���̃X�L�[�}���w�肵��Record(String schema)�����s����</li>
	 * <li>":A,java.lang.String,,,"</li>
	 * <li>�i�l�w��Ȃ��j</li>
	 * </ul>
	 * �m�F�F
	 * <ul>
	 * <li>����I���B��OPropertySchemaDefineException���������Ȃ����Ƃ��m�F</li>
	 * <li>getProperty("A")�����s���āAnull���Ԃ��Ă��邱�Ƃ��m�F</li>
	 * </ul>
	 */
	public void testGetPropertyString2() {
		try {
			String schema = ":A,java.lang.String,,,";
			Record rec = new Record(schema);
			assertNull(rec.getProperty("A"));
		} catch (PropertySchemaDefineException e) {
			e.printStackTrace();
			fail("��O����");
		}
	}

	/**
	 * �v���p�e�B�̒l���擾����e�X�g�B
	 * <p>
	 * �����F
	 * <ul>
	 * <li>���̃X�L�[�}���w�肵��Record(String schema)�����s����</li>
	 * <li>":A,java.lang.String,,,"</li>
	 * <li>���̒l���w�肵��setProperty(String name, Object val)�����s����</li>
	 * <li>name :"A"</li>
	 * <li>Object:"a"</li>
	 * <li>���̒l���w�肵��getProperty(String name)�����s����</li>
	 * <li>name :"B"(���݂��Ȃ����O���w��)</li>
	 * </ul>
	 * �m�F�F
	 * <ul>
	 * <li>����I���B��OPropertyGetException������</li>
	 * <li>��O���b�Z�[�W"No such property : B"���Ԃ��Ă��邱�Ƃ��m�F</li>
	 * </ul>
	 */
	public void testGetPropertyString3() {
		try {
			String schema = ":A,java.lang.String,,,";
			Record rec = new Record(schema);
			rec.setProperty("A", "a");
			rec.getProperty("B");
			fail("��O���������Ȃ����߃e�X�g���s ");
		} catch (PropertyGetException e) {
			assertEquals("No such property : B", e.getMessage());
		}
	}

	/**
	 * �v���p�e�B�̒l���擾����e�X�g�B
	 * <p>
	 * �����F
	 * <ul>
	 * <li>���̃X�L�[�}���w�肵��Record(String schema)�����s����</li>
	 * <li>":A,java.lang.String,,,"</li>
	 * <li>���̒l���w�肵��setProperty(String name, Object val)�����s����</li>
	 * <li>name :"A"</li>
	 * <li>Object:"a"</li>
	 * <li>���̒l���w�肵��getProperty(int index)�����s����</li>
	 * <li>int :0</li>
	 * </ul>
	 * �m�F�F
	 * <ul>
	 * <li>����I���B��OPropertySchemaDefineException���������Ȃ����Ƃ��m�F</li>
	 * <li>getProperty(0)�����s���āA"a"���Ԃ��Ă��邱�Ƃ��m�F</li>
	 * </ul>
	 */
	public void testGetPropertyInt1() {
		try {
			String schema = ":A,java.lang.String,,,";
			Record rec = new Record(schema);
			rec.setProperty("A", "a");
			assertEquals("a", rec.getProperty(0));
		} catch (PropertySchemaDefineException e) {
			e.printStackTrace();
			fail("��O����");
		}
	}

	/**
	 * �v���p�e�B�̒l���擾����e�X�g�B
	 * <p>
	 * �����F
	 * <ul>
	 * <li>���̃X�L�[�}���w�肵��Record(String schema)�����s����</li>
	 * <li>":A,java.lang.String,,,"</li>
	 * <li>���̒l���w�肵��setProperty(String name, Object val)�����s����</li>
	 * <li>name :"A"</li>
	 * <li>Object:"a"</li>
	 * <li>���̒l���w�肵��getProperty(int index)�����s����</li>
	 * <li>int :1(���݂��Ȃ��C���f�b�N�X)</li>
	 * </ul>
	 * �m�F�F
	 * <ul>
	 * <li>����I���B��OPropertyGetException���������邱�Ƃ��m�F</li>
	 * </ul>
	 */
	public void testGetPropertyInt2() {
		try {
			String schema = ":A,java.lang.String,,,";
			Record rec = new Record(schema);
			rec.setProperty("A", "a");
			assertEquals("a", rec.getProperty(1));
			fail("��O���������Ȃ����߃e�X�g���s ");
		} catch (PropertyGetException e) {
		}
	}

	/**
	 * �w�肳�ꂽ���O�̃v���p�e�B��boolean�Ƃ��Ď擾����e�X�g�B
	 * <p>
	 * �����F
	 * <ul>
	 * <li>���̃X�L�[�}���w�肵��Record(String schema)�����s����</li>
	 * <li>":A,boolean,,,"</li>
	 * <li>���̒l���w�肵��getBooleanProperty(String name)�����s����</li>
	 * <li>name :A (�l�Ȃ�)</li>
	 * </ul>
	 * �m�F�F
	 * <ul>
	 * <li>����I���B��OPropertySchemaDefineException���������Ȃ����Ƃ��m�F</li>
	 * <li>false���Ԃ��Ă��邱�Ƃ��m�F</li>
	 * </ul>
	 */
	public void testGetBooleanPropertyString1() {
		try {
			String schema = ":A,boolean,,,";
			Record rec = new Record(schema);
			assertEquals(false, rec.getBooleanProperty("A"));
		} catch (PropertySchemaDefineException e) {
			e.printStackTrace();
			fail("��O����");
		}
	}

	/**
	 * �w�肳�ꂽ���O�̃v���p�e�B��boolean�Ƃ��Ď擾����e�X�g�B
	 * <p>
	 * �����F
	 * <ul>
	 * <li>���̃X�L�[�}���w�肵��Record(String schema)�����s����</li>
	 * <li>":A,boolean,,,"</li>
	 * <li>���̒l���w�肵��setProperty(String name, Object val)�����s����</li>
	 * <li>name :"A"</li>
	 * <li>Object:true</li>
	 * <li>���̒l���w�肵��getBooleanProperty(String name)�����s����</li>
	 * <li>name :A</li>
	 * </ul>
	 * �m�F�F
	 * <ul>
	 * <li>����I���B��OPropertySchemaDefineException���������Ȃ����Ƃ��m�F</li>
	 * <li>true���Ԃ��Ă��邱�Ƃ��m�F</li>
	 * </ul>
	 */
	public void testGetBooleanPropertyString2() {
		try {
			String schema = ":A,boolean,,,";
			Record rec = new Record(schema);
			rec.setProperty("A", true);
			assertEquals(true, rec.getBooleanProperty("A"));
		} catch (PropertySchemaDefineException e) {
			e.printStackTrace();
			fail("��O����");
		}
	}

	/**
	 * �w�肳�ꂽ���O�̕����^�v���p�e�B��boolean�Ƃ��Ď擾����e�X�g�B
	 * <p>
	 * �����F
	 * <ul>
	 * <li>���̃X�L�[�}���w�肵��Record(String schema)�����s����</li>
	 * <li>":A,java.lang.String,,,"</li>
	 * <li>���̒l���w�肵��setProperty(String name, Object val)�����s����</li>
	 * <li>name :"A"</li>
	 * <li>Object:"1"</li>
	 * <li>���̒l���w�肵��getBooleanProperty(String name)�����s����</li>
	 * <li>name :A</li>
	 * </ul>
	 * �m�F�F
	 * <ul>
	 * <li>����I���B��OPropertySchemaDefineException���������Ȃ����Ƃ��m�F</li>
	 * <li>true���Ԃ��Ă��邱�Ƃ��m�F</li>
	 * </ul>
	 */
	public void testGetBooleanPropertyString3() {
		try {
			String schema = ":A,java.lang.String,,,";
			Record rec = new Record(schema);
			rec.setProperty("A", "1");
			assertEquals(true, rec.getBooleanProperty("A"));
		} catch (PropertySchemaDefineException e) {
			e.printStackTrace();
			fail("��O����");
		}
	}

	/**
	 * �w�肳�ꂽ���O�̕����^�v���p�e�B��boolean�Ƃ��Ď擾����e�X�g�B
	 * <p>
	 * �����F
	 * <ul>
	 * <li>���̃X�L�[�}���w�肵��Record(String schema)�����s����</li>
	 * <li>":A,java.lang.String,,,"</li>
	 * <li>���̒l���w�肵��setProperty(String name, Object val)�����s����</li>
	 * <li>name :"A"</li>
	 * <li>Object:�����ȊO�̒l</li>
	 * <li>���̒l���w�肵��getBooleanProperty(String name)�����s����</li>
	 * <li>name :A</li>
	 * </ul>
	 * �m�F�F
	 * <ul>
	 * <li>����I���B��OPropertySchemaDefineException���������Ȃ����Ƃ��m�F</li>
	 * <li>Boolean.valueOf((String)ret).booleanValue()���Ԃ��Ă��邱�Ƃ��m�F</li>
	 * </ul>
	 */
	public void testGetBooleanPropertyString4() {
		try {
			String schema = ":A,java.lang.String,,,";
			Record rec = new Record(schema);
			rec.setProperty("A", "Test");
			assertEquals(Boolean.valueOf("Test").booleanValue(), rec
					.getBooleanProperty("A"));
		} catch (PropertySchemaDefineException e) {
			e.printStackTrace();
			fail("��O����");
		}
	}

	/**
	 * �w�肳�ꂽ���O��int�^�v���p�e�B��boolean�Ƃ��Ď擾����e�X�g�B
	 * <p>
	 * �����F
	 * <ul>
	 * <li>���̃X�L�[�}���w�肵��Record(String schema)�����s����</li>
	 * <li>":A,int,,,"</li>
	 * <li>���̒l���w�肵��setProperty(String name, int val)�����s����</li>
	 * <li>name :"A"</li>
	 * <li>val:100</li>
	 * <li>���̒l���w�肵��getBooleanProperty(String name)�����s����</li>
	 * <li>name :A</li>
	 * </ul>
	 * �m�F�F
	 * <ul>
	 * <li>����I���B��OPropertySchemaDefineException���������Ȃ����Ƃ��m�F</li>
	 * <li>true���Ԃ��Ă��邱�Ƃ��m�F</li>
	 * </ul>
	 */
	public void testGetBooleanPropertyString5() {
		try {
			String schema = ":A,int,,,";
			Record rec = new Record(schema);
			rec.setProperty("A", 1);
			assertEquals(true, rec.getBooleanProperty("A"));
		} catch (PropertySchemaDefineException e) {
			e.printStackTrace();
			fail("��O����");
		}
	}

	/**
	 * �w�肳�ꂽ���O�̃v���p�e�B��boolean�Ƃ��Ď擾����e�X�g�B
	 * <p>
	 * �����F
	 * <ul>
	 * <li>���̃X�L�[�}���w�肵��Record(String schema)�����s����</li>
	 * <li>":A,java.util.Date,,,"</li>
	 * <li>���̒l���w�肵��setProperty(String name, Object val)�����s����</li>
	 * <li>name :"A"</li>
	 * <li>val:Date�^�̒l (Boolean�l�Ƃ��Ď擾�ł��Ȃ��^)</li>
	 * <li>���̒l���w�肵��getBooleanProperty(String name)�����s����</li>
	 * <li>name :A</li>
	 * </ul>
	 * �m�F�F
	 * <ul>
	 * <li>����I���B��OPropertyGetException���������邱�Ƃ��m�F</li>
	 * </ul>
	 */
	public void testGetBooleanPropertyString6() {
		try {
			String schema = ":A,java.util.Date,,,";
			Record rec = new Record(schema);
			rec.setProperty("A", new Date());
			rec.getBooleanProperty("A");
			fail("��O���������Ȃ����߃e�X�g���s ");
		} catch (PropertyGetException e) {
		}
	}

	/**
	 * �w�肳�ꂽ�C���f�b�N�X�̃v���p�e�B��boolean�Ƃ��Ď擾����e�X�g�B
	 * <p>
	 * �����F
	 * <ul>
	 * <li>���̃X�L�[�}���w�肵��Record(String schema)�����s����</li>
	 * <li>":A,boolean,,,"</li>
	 * <li>���̒l���w�肵��setProperty(String name, boolean val)�����s����</li>
	 * <li>name :"A"</li>
	 * <li>value:true</li>
	 * <li>���̒l���w�肵��getBooleanProperty(int index)�����s����</li>
	 * <li>int :0</li>
	 * </ul>
	 * �m�F�F
	 * <ul>
	 * <li>����I���B��OPropertySchemaDefineException���������Ȃ����Ƃ��m�F</li>
	 * <li>getProperty(0)�����s���āAtrue���Ԃ��Ă��邱�Ƃ��m�F</li>
	 * </ul>
	 */
	public void testGetBooleanPropertyInt1() {
		try {
			String schema = ":A,boolean,,,";
			Record rec = new Record(schema);
			rec.setProperty("A", true);
			assertEquals(true, rec.getBooleanProperty(0));
		} catch (PropertySchemaDefineException e) {
			e.printStackTrace();
			fail("��O����");
		}
	}

	/**
	 * �w�肳�ꂽ�C���f�b�N�X�̃v���p�e�B��boolean�Ƃ��Ď擾����e�X�g�B
	 * <p>
	 * �����F
	 * <ul>
	 * <li>���̃X�L�[�}���w�肵��Record(String schema)�����s����</li>
	 * <li>":A,boolean,,,"</li>
	 * <li>���̒l���w�肵��setProperty(String name, boolean val)�����s����</li>
	 * <li>name :"A"</li>
	 * <li>value:true</li>
	 * <li>���̒l���w�肵��getBooleanProperty(int index)�����s����</li>
	 * <li>int :1(���݂��Ȃ��C���f�b�N�X)</li>
	 * </ul>
	 * �m�F�F
	 * <ul>
	 * <li>��OPropertyGetException���������邱�Ƃ��m�F</li>
	 * </ul>
	 */
	public void testGetBooleanPropertyInt2() {
		try {
			String schema = ":A,boolean,,,";
			Record rec = new Record(schema);
			rec.setProperty("A", true);
			rec.getBooleanProperty(1);
			fail("��O���������Ȃ����߃e�X�g���s ");
		} catch (PropertyGetException e) {
		}
	}

	/**
	 * �w�肳�ꂽ���O�̃v���p�e�B��byte�Ƃ��Ď擾����e�X�g�B
	 * <p>
	 * �����F
	 * <ul>
	 * <li>���̃X�L�[�}���w�肵��Record(String schema)�����s����</li>
	 * <li>":A,byte,,,"</li>
	 * <li>���̒l���w�肵��getByteProperty(String name)�����s����</li>
	 * <li>name :A (�l�Ȃ�)</li>
	 * </ul>
	 * �m�F�F
	 * <ul>
	 * <li>����I���B��OPropertySchemaDefineException���������Ȃ����Ƃ��m�F</li>
	 * <li>0���Ԃ��Ă��邱�Ƃ��m�F</li>
	 * </ul>
	 */
	public void testGetBytePropertyString1() {
		try {
			String schema = ":A,byte,,,";
			Record rec = new Record(schema);
			assertEquals((byte) 0, rec.getByteProperty("A"));
		} catch (PropertySchemaDefineException e) {
			e.printStackTrace();
			fail("��O����");
		}
	}

	/**
	 * �w�肳�ꂽ���O�̃v���p�e�B��byte�Ƃ��Ď擾����e�X�g�B
	 * <p>
	 * �����F
	 * <ul>
	 * <li>���̃X�L�[�}���w�肵��Record(String schema)�����s����</li>
	 * <li>":A,byte,,,"</li>
	 * <li>���̒l���w�肵��setProperty(String name, Object val)�����s����</li>
	 * <li>name :"A"</li>
	 * <li>value:(byte)1</li>
	 * <li>���̒l���w�肵��getByteProperty(String name)�����s����</li>
	 * <li>name :A</li>
	 * </ul>
	 * �m�F�F
	 * <ul>
	 * <li>����I���B��OPropertySchemaDefineException���������Ȃ����Ƃ��m�F</li>
	 * <li>�w�肵��Byte�l���Ԃ��Ă��邱�Ƃ��m�F</li>
	 * </ul>
	 */
	public void testGetBytePropertyString2() {
		try {
			String schema = ":A,byte,,,";
			Record rec = new Record(schema);
			rec.setProperty("A", (byte) 1);
			assertEquals((byte) 1, rec.getByteProperty("A"));
		} catch (PropertySchemaDefineException e) {
			e.printStackTrace();
			fail("��O����");
		}
	}

	/**
	 * �w�肳�ꂽ���O�̕����^�v���p�e�B��byte�Ƃ��Ď擾����e�X�g�B
	 * <p>
	 * �����F
	 * <ul>
	 * <li>���̃X�L�[�}���w�肵��Record(String schema)�����s����</li>
	 * <li>":A,java.lang.String,,,"</li>
	 * <li>���̒l���w�肵��setProperty(String name, Object val)�����s����</li>
	 * <li>name :"A"</li>
	 * <li>Object:"1"</li>
	 * <li>���̒l���w�肵��getByteProperty(String name)�����s����</li>
	 * <li>name :A</li>
	 * </ul>
	 * �m�F�F
	 * <ul>
	 * <li>����I���B��OPropertySchemaDefineException���������Ȃ����Ƃ��m�F</li>
	 * <li>1���Ԃ��Ă��邱�Ƃ��m�F</li>
	 * </ul>
	 */
	public void testGetBytePropertyString3() {
		try {
			String schema = ":A,java.lang.String,,,";
			Record rec = new Record(schema);
			rec.setProperty("A", "1");
			assertEquals((byte) 1, rec.getByteProperty("A"));
		} catch (PropertySchemaDefineException e) {
			e.printStackTrace();
			fail("��O����");
		}
	}

	/**
	 * �w�肳�ꂽ���O��int�^�v���p�e�B��byte�Ƃ��Ď擾����e�X�g�B
	 * <p>
	 * �����F
	 * <ul>
	 * <li>���̃X�L�[�}���w�肵��Record(String schema)�����s����</li>
	 * <li>":A,int,,,"</li>
	 * <li>���̒l���w�肵��setProperty(String name, int val)�����s����</li>
	 * <li>name :"A"</li>
	 * <li>val:100</li>
	 * <li>���̒l���w�肵��getByteProperty(String name)�����s����</li>
	 * <li>name :A</li>
	 * </ul>
	 * �m�F�F
	 * <ul>
	 * <li>����I���B��OPropertySchemaDefineException���������Ȃ����Ƃ��m�F</li>
	 * <li>100���Ԃ��Ă��邱�Ƃ��m�F</li>
	 * </ul>
	 */
	public void testGetBytePropertyString4() {
		try {
			String schema = ":A,int,,,";
			Record rec = new Record(schema);
			rec.setProperty("A", 100);
			assertEquals((byte) 100, rec.getByteProperty("A"));
		} catch (PropertySchemaDefineException e) {
			e.printStackTrace();
			fail("��O����");
		}
	}

	/**
	 * �w�肳�ꂽ���O��boolean�^�v���p�e�B��byte�Ƃ��Ď擾����e�X�g�B
	 * <p>
	 * �����F
	 * <ul>
	 * <li>���̃X�L�[�}���w�肵��Record(String schema)�����s����</li>
	 * <li>":A,boolean,,,"</li>
	 * <li>���̒l���w�肵��setProperty(String name, boolean val)�����s����</li>
	 * <li>name :"A"</li>
	 * <li>val:true</li>
	 * <li>���̒l���w�肵��getByteProperty(String name)�����s����</li>
	 * <li>name :A</li>
	 * </ul>
	 * �m�F�F
	 * <ul>
	 * <li>����I���B��OPropertySchemaDefineException���������Ȃ����Ƃ��m�F</li>
	 * <li>1���Ԃ��Ă��邱�Ƃ��m�F</li>
	 * </ul>
	 */
	public void testGetBytePropertyString5() {
		try {
			String schema = ":A,boolean,,,";
			Record rec = new Record(schema);
			rec.setProperty("A", true);
			assertEquals((byte) 1, rec.getByteProperty("A"));
		} catch (PropertySchemaDefineException e) {
			e.printStackTrace();
			fail("��O����");
		}
	}

	/**
	 * �w�肳�ꂽ���O�̃v���p�e�B��byte�Ƃ��Ď擾����e�X�g�B
	 * <p>
	 * �����F
	 * <ul>
	 * <li>���̃X�L�[�}���w�肵��Record(String schema)�����s����</li>
	 * <li>":A,java.util.Date,,,"</li>
	 * <li>���̒l���w�肵��setProperty(String name, Object val)�����s����</li>
	 * <li>name :"A"</li>
	 * <li>val:Date�^�̒l (Byte�l�Ƃ��Ď擾�ł��Ȃ��^)</li>
	 * <li>���̒l���w�肵��getByteProperty(String name)�����s����</li>
	 * <li>name :A</li>
	 * </ul>
	 * �m�F�F
	 * <ul>
	 * <li>����I���B��OPropertyGetException���������邱�Ƃ��m�F</li>
	 * <li>��O���b�Z�[�W��"The type is unmatch. value=" + ret"���Ԃ���邱�Ƃ��m�F</li>
	 * </ul>
	 */
	public void testGetBytePropertyString6() {
		try {
			String schema = ":A,java.util.Date,,,";
			Record rec = new Record(schema);
			rec.setProperty("A", new Date());
			rec.getByteProperty("A");
			fail("��O���������Ȃ����߃e�X�g���s ");
		} catch (PropertyGetException e) {
		}
	}

	/**
	 * �w�肳�ꂽ�C���f�b�N�X�̃v���p�e�B��byte�Ƃ��Ď擾����e�X�g�B
	 * <p>
	 * �����F
	 * <ul>
	 * <li>���̃X�L�[�}���w�肵��Record(String schema)�����s����</li>
	 * <li>":A,byte,,,"</li>
	 * <li>���̒l���w�肵��setProperty(String name, byte val)�����s����</li>
	 * <li>name :"A"</li>
	 * <li>value:1</li>
	 * <li>���̒l���w�肵��getByteProperty(int index)�����s����</li>
	 * <li>int :0</li>
	 * </ul>
	 * �m�F�F
	 * <ul>
	 * <li>����I���B��OPropertySchemaDefineException���������Ȃ����Ƃ��m�F</li>
	 * <li>getProperty(0)�����s���āA1���Ԃ��Ă��邱�Ƃ��m�F</li>
	 * </ul>
	 */
	public void testGetBytePropertyInt1() {
		try {
			String schema = ":A,byte,,,";
			Record rec = new Record(schema);
			rec.setProperty("A", (byte) 1);
			assertEquals((byte) 1, rec.getByteProperty(0));
		} catch (PropertySchemaDefineException e) {
			e.printStackTrace();
			fail("��O����");
		}
	}

	/**
	 * �w�肳�ꂽ�C���f�b�N�X�̃v���p�e�B��byte�Ƃ��Ď擾����e�X�g�B
	 * <p>
	 * �����F
	 * <ul>
	 * <li>���̃X�L�[�}���w�肵��Record(String schema)�����s����</li>
	 * <li>":A,byte,,,"</li>
	 * <li>���̒l���w�肵��setProperty(String name, byte val)�����s����</li>
	 * <li>name :"A"</li>
	 * <li>value:1</li>
	 * <li>���̒l���w�肵��getByteProperty(int index)�����s����</li>
	 * <li>int :1(���݂��Ȃ��C���f�b�N�X)</li>
	 * </ul>
	 * �m�F�F
	 * <ul>
	 * <li>��OPropertyGetException���������邱�Ƃ��m�F</li>
	 * </ul>
	 */
	public void testGetBytePropertyInt2() {
		try {
			String schema = ":A,byte,,,";
			Record rec = new Record(schema);
			rec.setProperty("A", (byte) 1);
			rec.getByteProperty(1);
			fail("��O���������Ȃ����߃e�X�g���s ");
		} catch (PropertyGetException e) {
		}
	}

	/**
	 * �w�肳�ꂽ���O�̃v���p�e�B��short�Ƃ��Ď擾����e�X�g�B
	 * <p>
	 * �����F
	 * <ul>
	 * <li>���̃X�L�[�}���w�肵��Record(String schema)�����s����</li>
	 * <li>":A,short,,,"</li>
	 * <li>���̒l���w�肵��getShortProperty(String name)�����s����</li>
	 * <li>name :A (�l�Ȃ�)</li>
	 * </ul>
	 * �m�F�F
	 * <ul>
	 * <li>����I���B��OPropertySchemaDefineException���������Ȃ����Ƃ��m�F</li>
	 * <li>0���Ԃ��Ă��邱�Ƃ��m�F</li>
	 * </ul>
	 */
	public void testGetShortPropertyString1() {
		try {
			String schema = ":A,short,,,";
			Record rec = new Record(schema);
			assertEquals((short) 0, rec.getShortProperty("A"));
		} catch (PropertySchemaDefineException e) {
			e.printStackTrace();
			fail("��O����");
		}
	}

	/**
	 * �w�肳�ꂽ���O�̃v���p�e�B��short�Ƃ��Ď擾����e�X�g�B
	 * <p>
	 * �����F
	 * <ul>
	 * <li>���̃X�L�[�}���w�肵��Record(String schema)�����s����</li>
	 * <li>":A,short,,,"</li>
	 * <li>���̒l���w�肵��setProperty(String name, short val)�����s����</li>
	 * <li>name :"A"</li>
	 * <li>value:(short)1</li>
	 * <li>���̒l���w�肵��getShortProperty(String name)�����s����</li>
	 * <li>name :A</li>
	 * </ul>
	 * �m�F�F
	 * <ul>
	 * <li>����I���B��OPropertySchemaDefineException���������Ȃ����Ƃ��m�F</li>
	 * <li>�w�肵��Short�l���Ԃ��Ă��邱�Ƃ��m�F</li>
	 * </ul>
	 */
	public void testGetShortPropertyString2() {
		try {
			String schema = ":A,short,,,";
			Record rec = new Record(schema);
			rec.setProperty("A", (short) 1);
			assertEquals((short) 1, rec.getShortProperty("A"));
		} catch (PropertySchemaDefineException e) {
			e.printStackTrace();
			fail("��O����");
		}
	}

	/**
	 * �w�肳�ꂽ���O�̕����^�v���p�e�B��short�Ƃ��Ď擾����e�X�g�B
	 * <p>
	 * �����F
	 * <ul>
	 * <li>���̃X�L�[�}���w�肵��Record(String schema)�����s����</li>
	 * <li>":A,java.lang.String,,,"</li>
	 * <li>���̒l���w�肵��setProperty(String name, Object val)�����s����</li>
	 * <li>name :"A"</li>
	 * <li>Object:"1"</li>
	 * <li>���̒l���w�肵��getShortProperty(String name)�����s����</li>
	 * <li>int :A</li>
	 * </ul>
	 * �m�F�F
	 * <ul>
	 * <li>����I���B��OPropertySchemaDefineException���������Ȃ����Ƃ��m�F</li>
	 * <li>1���Ԃ��Ă��邱�Ƃ��m�F</li>
	 * </ul>
	 */
	public void testGetShortPropertyString3() {
		try {
			String schema = ":A,java.lang.String,,,";
			Record rec = new Record(schema);
			rec.setProperty("A", "1");
			assertEquals((short) 1, rec.getShortProperty("A"));
		} catch (PropertySchemaDefineException e) {
			e.printStackTrace();
			fail("��O����");
		}
	}

	/**
	 * �w�肳�ꂽ���O��int�v���p�e�B��short�Ƃ��Ď擾����e�X�g�B
	 * <p>
	 * �����F
	 * <ul>
	 * <li>���̃X�L�[�}���w�肵��Record(String schema)�����s����</li>
	 * <li>":A,int,,,"</li>
	 * <li>���̒l���w�肵��setProperty(String name, int val)�����s����</li>
	 * <li>name :"A"</li>
	 * <li>val:100</li>
	 * <li>���̒l���w�肵��getShortProperty(String name)�����s����</li>
	 * <li>name :A</li>
	 * </ul>
	 * �m�F�F
	 * <ul>
	 * <li>����I���B��OPropertySchemaDefineException���������Ȃ����Ƃ��m�F</li>
	 * <li>100���Ԃ��Ă��邱�Ƃ��m�F</li>
	 * </ul>
	 */
	public void testGetShortPropertyString4() {
		try {
			String schema = ":A,int,,,";
			Record rec = new Record(schema);
			rec.setProperty("A", 100);
			assertEquals((short) 100, rec.getShortProperty("A"));
		} catch (PropertySchemaDefineException e) {
			e.printStackTrace();
			fail("��O����");
		}
	}

	/**
	 * �w�肳�ꂽ���O��boolean�v���p�e�B��short�Ƃ��Ď擾����e�X�g�B
	 * <p>
	 * �����F
	 * <ul>
	 * <li>���̃X�L�[�}���w�肵��Record(String schema)�����s����</li>
	 * <li>":A,boolean,,,"</li>
	 * <li>���̒l���w�肵��setProperty(String name, boolean val)�����s����</li>
	 * <li>name :"A"</li>
	 * <li>val:true</li>
	 * <li>���̒l���w�肵��getShortProperty(String name)�����s����</li>
	 * <li>name :A</li>
	 * </ul>
	 * �m�F�F
	 * <ul>
	 * <li>����I���B��OPropertySchemaDefineException���������Ȃ����Ƃ��m�F</li>
	 * <li>1���Ԃ��Ă��邱�Ƃ��m�F</li>
	 * </ul>
	 */
	public void testGetShortPropertyString5() {
		try {
			String schema = ":A,boolean,,,";
			Record rec = new Record(schema);
			rec.setProperty("A", true);
			assertEquals((short) 1, rec.getShortProperty("A"));
		} catch (PropertySchemaDefineException e) {
			e.printStackTrace();
			fail("��O����");
		}
	}

	/**
	 * �w�肳�ꂽ���O�̃v���p�e�B��short�Ƃ��Ď擾����e�X�g�B
	 * <p>
	 * �����F
	 * <ul>
	 * <li>���̃X�L�[�}���w�肵��Record(String schema)�����s����</li>
	 * <li>":A,java.util.Date,,,"</li>
	 * <li>���̒l���w�肵��setProperty(String name, Object val)�����s����</li>
	 * <li>name :"A"</li>
	 * <li>val:Date�^�̒l (short�l�Ƃ��Ď擾�ł��Ȃ��^)</li>
	 * <li>���̒l���w�肵��getShortProperty(String name)�����s����</li>
	 * <li>name :A</li>
	 * </ul>
	 * �m�F�F
	 * <ul>
	 * <li>����I���B��OPropertyGetException���������邱�Ƃ��m�F</li>
	 * <li>��O���b�Z�[�W��"The type is unmatch. value=" + ret"���Ԃ���邱�Ƃ��m�F</li>
	 * </ul>
	 */
	public void testGetShortPropertyString6() {
		try {
			String schema = ":A,java.util.Date,,,";
			Record rec = new Record(schema);
			rec.setProperty("A", new Date());
			rec.getShortProperty("A");
			fail("��O���������Ȃ����߃e�X�g���s ");
		} catch (PropertyGetException e) {
		}
	}

	/**
	 * �w�肳�ꂽ�C���f�b�N�X�̃v���p�e�B��short�Ƃ��Ď擾����e�X�g�B
	 * <p>
	 * �����F
	 * <ul>
	 * <li>���̃X�L�[�}���w�肵��Record(String schema)�����s����</li>
	 * <li>":A,short,,,"</li>
	 * <li>���̒l���w�肵��setProperty(String name, short val)�����s����</li>
	 * <li>name :"A"</li>
	 * <li>value:1</li>
	 * <li>���̒l���w�肵��getShortProperty(int index)�����s����</li>
	 * <li>int :0</li>
	 * </ul>
	 * �m�F�F
	 * <ul>
	 * <li>����I���B��OPropertySchemaDefineException���������Ȃ����Ƃ��m�F</li>
	 * <li>1���Ԃ��Ă��邱�Ƃ��m�F</li>
	 * </ul>
	 */
	public void testGetShortPropertyInt1() {
		try {
			String schema = ":A,short,,,";
			Record rec = new Record(schema);
			rec.setProperty("A", (short) 1);
			assertEquals((short) 1, rec.getShortProperty(0));
		} catch (PropertySchemaDefineException e) {
			e.printStackTrace();
			fail("��O����");
		}
	}

	/**
	 * �w�肳�ꂽ�C���f�b�N�X�̃v���p�e�B��short�Ƃ��Ď擾����e�X�g�B
	 * <p>
	 * �����F
	 * <ul>
	 * <li>���̃X�L�[�}���w�肵��Record(String schema)�����s����</li>
	 * <li>":A,short,,,"</li>
	 * <li>���̒l���w�肵��setProperty(String name, short val)�����s����</li>
	 * <li>name :"A"</li>
	 * <li>value:1</li>
	 * <li>���̒l���w�肵��getShortProperty(int index)�����s����</li>
	 * <li>int :1(���݂��Ȃ��C���f�b�N�X)</li>
	 * </ul>
	 * �m�F�F
	 * <ul>
	 * <li>��OPropertyGetException���������邱�Ƃ��m�F</li>
	 * </ul>
	 */
	public void testGetShortPropertyInt2() {
		try {
			String schema = ":A,short,,,";
			Record rec = new Record(schema);
			rec.setProperty("A", (short) 1);
			rec.getShortProperty(1);
			fail("��O���������Ȃ����߃e�X�g���s ");
		} catch (PropertyGetException e) {
		}
	}

	/**
	 * �w�肳�ꂽ���O�̃v���p�e�B��int�Ƃ��Ď擾����e�X�g�B
	 * <p>
	 * �����F
	 * <ul>
	 * <li>���̃X�L�[�}���w�肵��Record(String schema)�����s����</li>
	 * <li>":A,int,,,"</li>
	 * <li>���̒l���w�肵��getIntProperty(String name)�����s����</li>
	 * <li>name :A (�l�Ȃ�)</li>
	 * </ul>
	 * �m�F�F
	 * <ul>
	 * <li>����I���B��OPropertySchemaDefineException���������Ȃ����Ƃ��m�F</li>
	 * <li>0���Ԃ��Ă��邱�Ƃ��m�F</li>
	 * </ul>
	 */
	public void testGetIntPropertyString1() {
		try {
			String schema = ":A,int,,,";
			Record rec = new Record(schema);
			assertEquals((int) 0, rec.getIntProperty("A"));
		} catch (PropertySchemaDefineException e) {
			e.printStackTrace();
			fail("��O����");
		}
	}

	/**
	 * �w�肳�ꂽ���O�̃v���p�e�B��int�Ƃ��Ď擾����e�X�g�B
	 * <p>
	 * �����F
	 * <ul>
	 * <li>���̃X�L�[�}���w�肵��Record(String schema)�����s����</li>
	 * <li>":A,int,,,"</li>
	 * <li>���̒l���w�肵��setProperty(String name, int val)�����s����</li>
	 * <li>name :"A"</li>
	 * <li>value:1</li>
	 * <li>���̒l���w�肵��getIntProperty(String name)�����s����</li>
	 * <li>name :A</li>
	 * </ul>
	 * �m�F�F
	 * <ul>
	 * <li>����I���B��OPropertySchemaDefineException���������Ȃ����Ƃ��m�F</li>
	 * <li>1���Ԃ��Ă��邱�Ƃ��m�F</li>
	 * </ul>
	 */
	public void testGetIntPropertyString2() {
		try {
			String schema = ":A,int,,,";
			Record rec = new Record(schema);
			rec.setProperty("A", (int) 1);
			assertEquals((int) 1, rec.getIntProperty("A"));
		} catch (PropertySchemaDefineException e) {
			e.printStackTrace();
			fail("��O����");
		}
	}

	/**
	 * �w�肳�ꂽ���O��string�v���p�e�B��int�Ƃ��Ď擾����e�X�g�B
	 * <p>
	 * �����F
	 * <ul>
	 * <li>���̃X�L�[�}���w�肵��Record(String schema)�����s����</li>
	 * <li>":A,java.lang.String,,,"</li>
	 * <li>���̒l���w�肵��setProperty(String name, Object val)�����s����</li>
	 * <li>name :"A"</li>
	 * <li>Object:"1"</li>
	 * <li>���̒l���w�肵��getIntProperty(String name)�����s����</li>
	 * <li>name :A</li>
	 * </ul>
	 * �m�F�F
	 * <ul>
	 * <li>����I���B��OPropertySchemaDefineException���������Ȃ����Ƃ��m�F</li>
	 * <li>1���Ԃ��Ă��邱�Ƃ��m�F</li>
	 * </ul>
	 */
	public void testGetIntPropertyString3() {
		try {
			String schema = ":A,java.lang.String,,,";
			Record rec = new Record(schema);
			rec.setProperty("A", "1");
			assertEquals((int) 1, rec.getIntProperty("A"));
		} catch (PropertySchemaDefineException e) {
			e.printStackTrace();
			fail("��O����");
		}
	}

	/**
	 * �w�肳�ꂽ���O��short�v���p�e�B��int�Ƃ��Ď擾����e�X�g�B
	 * <p>
	 * �����F
	 * <ul>
	 * <li>���̃X�L�[�}���w�肵��Record(String schema)�����s����</li>
	 * <li>":A,short,,,"</li>
	 * <li>���̒l���w�肵��setProperty(String name, short val)�����s����</li>
	 * <li>name :"A"</li>
	 * <li>val:100</li>
	 * <li>���̒l���w�肵��getIntProperty(String name)�����s����</li>
	 * <li>name :A</li>
	 * </ul>
	 * �m�F�F
	 * <ul>
	 * <li>����I���B��OPropertySchemaDefineException���������Ȃ����Ƃ��m�F</li>
	 * <li>100���Ԃ��Ă��邱�Ƃ��m�F</li>
	 * </ul>
	 */
	public void testGetIntPropertyString4() {
		try {
			String schema = ":A,short,,,";
			Record rec = new Record(schema);
			rec.setProperty("A", (short) 100);
			assertEquals(100, rec.getIntProperty("A"));
		} catch (PropertySchemaDefineException e) {
			e.printStackTrace();
			fail("��O����");
		}
	}

	/**
	 * �w�肳�ꂽ���O��boolean�v���p�e�B��int�Ƃ��Ď擾����e�X�g�B
	 * <p>
	 * �����F
	 * <ul>
	 * <li>���̃X�L�[�}���w�肵��Record(String schema)�����s����</li>
	 * <li>":A,boolean,,,"</li>
	 * <li>���̒l���w�肵��setProperty(String name, boolean val)�����s����</li>
	 * <li>name :"A"</li>
	 * <li>val:true</li>
	 * <li>���̒l���w�肵��getIntProperty(String name)�����s����</li>
	 * <li>name :A</li>
	 * </ul>
	 * �m�F�F
	 * <ul>
	 * <li>����I���B��OPropertySchemaDefineException���������Ȃ����Ƃ��m�F</li>
	 * <li>1���Ԃ��Ă��邱�Ƃ��m�F</li>
	 * </ul>
	 */
	public void testGetIntPropertyString5() {
		try {
			String schema = ":A,boolean,,,";
			Record rec = new Record(schema);
			rec.setProperty("A", true);
			assertEquals(1, rec.getIntProperty("A"));
		} catch (PropertySchemaDefineException e) {
			e.printStackTrace();
			fail("��O����");
		}
	}

	/**
	 * �w�肳�ꂽ���O�̃v���p�e�B��int�Ƃ��Ď擾����e�X�g�B
	 * <p>
	 * �����F
	 * <ul>
	 * <li>���̃X�L�[�}���w�肵��Record(String schema)�����s����</li>
	 * <li>":A,java.util.Date,,,"</li>
	 * <li>���̒l���w�肵��setProperty(String name, Object val)�����s����</li>
	 * <li>name :"A"</li>
	 * <li>val:Date�^�̒l (int�l�Ƃ��Ď擾�ł��Ȃ��^)</li>
	 * <li>���̒l���w�肵��getIntProperty(String name)�����s����</li>
	 * <li>name :A</li>
	 * </ul>
	 * �m�F�F
	 * <ul>
	 * <li>����I���B��OPropertyGetException���������邱�Ƃ��m�F</li>
	 * <li>��O���b�Z�[�W��"The type is unmatch. value=" + ret"���Ԃ���邱�Ƃ��m�F</li>
	 * </ul>
	 */
	public void testGetIntPropertyString6() {
		try {
			String schema = ":A,java.util.Date,,,";
			Record rec = new Record(schema);
			rec.setProperty("A", new Date());
			rec.getIntProperty("A");
			fail("��O���������Ȃ����߃e�X�g���s ");
		} catch (PropertyGetException e) {
		}
	}

	/**
	 * �w�肳�ꂽ�C���f�b�N�X�̃v���p�e�B��int�Ƃ��Ď擾����e�X�g�B
	 * <p>
	 * �����F
	 * <ul>
	 * <li>���̃X�L�[�}���w�肵��Record(String schema)�����s����</li>
	 * <li>":A,int,,,"</li>
	 * <li>���̒l���w�肵��setProperty(String name, int val)�����s����</li>
	 * <li>name :"A"</li>
	 * <li>value:1</li>
	 * <li>���̒l���w�肵��getIntProperty(int index)�����s����</li>
	 * <li>int :0</li>
	 * </ul>
	 * �m�F�F
	 * <ul>
	 * <li>����I���B��OPropertySchemaDefineException���������Ȃ����Ƃ��m�F</li>
	 * <li>1���Ԃ��Ă��邱�Ƃ��m�F</li>
	 * </ul>
	 */
	public void testGetIntPropertyInt1() {
		try {
			String schema = ":A,int,,,";
			Record rec = new Record(schema);
			rec.setProperty("A", 1);
			assertEquals(1, rec.getIntProperty(0));
		} catch (PropertySchemaDefineException e) {
			e.printStackTrace();
			fail("��O����");
		}
	}

	/**
	 * �w�肳�ꂽ�C���f�b�N�X�̃v���p�e�B��int�Ƃ��Ď擾����e�X�g�B
	 * <p>
	 * �����F
	 * <ul>
	 * <li>���̃X�L�[�}���w�肵��Record(String schema)�����s����</li>
	 * <li>":A,int,,,"</li>
	 * <li>���̒l���w�肵��setProperty(String name, int val)�����s����</li>
	 * <li>name :"A"</li>
	 * <li>value:1</li>
	 * <li>���̒l���w�肵��getIntProperty(int index)�����s����</li>
	 * <li>int :1(���݂��Ȃ��C���f�b�N�X)</li>
	 * </ul>
	 * �m�F�F
	 * <ul>
	 * <li>��OPropertyGetException���������邱�Ƃ��m�F</li>
	 * </ul>
	 */
	public void testGetIntPropertyInt2() {
		try {
			String schema = ":A,int,,,";
			Record rec = new Record(schema);
			rec.setProperty("A", 1);
			rec.getIntProperty(1);
			fail("��O���������Ȃ����߃e�X�g���s ");
		} catch (PropertyGetException e) {
		}
	}

	/**
	 * �w�肳�ꂽ���O�̃v���p�e�B��long�Ƃ��Ď擾����B
	 * <p>
	 * �����F
	 * <ul>
	 * <li>���̃X�L�[�}���w�肵��Record(String schema)�����s����</li>
	 * <li>":A,long,,,"</li>
	 * <li>���̒l���w�肵��getLongProperty(String name)�����s����</li>
	 * <li>name :A (�l�Ȃ�)</li>
	 * </ul>
	 * �m�F�F
	 * <ul>
	 * <li>����I���B��OPropertySchemaDefineException���������Ȃ����Ƃ��m�F</li>
	 * <li>0���Ԃ��Ă��邱�Ƃ��m�F</li>
	 * </ul>
	 */
	public void testGetLongPropertyString1() {
		try {
			String schema = ":A,long,,,";
			Record rec = new Record(schema);
			assertEquals((long) 0, rec.getLongProperty("A"));
		} catch (PropertySchemaDefineException e) {
			e.printStackTrace();
			fail("��O����");
		}
	}

	/**
	 * �w�肳�ꂽ���O�̃v���p�e�B��long�Ƃ��Ď擾����B
	 * <p>
	 * �����F
	 * <ul>
	 * <li>���̃X�L�[�}���w�肵��Record(String schema)�����s����</li>
	 * <li>":A,long,,,"</li>
	 * <li>���̒l���w�肵��setProperty(String name, long val)�����s����</li>
	 * <li>name :"A"</li>
	 * <li>value:1</li>
	 * <li>���̒l���w�肵��getLongProperty(String name)�����s����</li>
	 * <li>name :A</li>
	 * </ul>
	 * �m�F�F
	 * <ul>
	 * <li>����I���B��OPropertySchemaDefineException���������Ȃ����Ƃ��m�F</li>
	 * <li>1���Ԃ��Ă��邱�Ƃ��m�F</li>
	 * </ul>
	 */
	public void testGetLongPropertyString2() {
		try {
			String schema = ":A,long,,,";
			Record rec = new Record(schema);
			rec.setProperty("A", (long) 1);
			assertEquals((long) 1, rec.getLongProperty("A"));
		} catch (PropertySchemaDefineException e) {
			e.printStackTrace();
			fail("��O����");
		}
	}

	/**
	 * �w�肳�ꂽ���O��string�v���p�e�B��long�Ƃ��Ď擾����B
	 * <p>
	 * �����F
	 * <ul>
	 * <li>���̃X�L�[�}���w�肵��Record(String schema)�����s����</li>
	 * <li>":A,java.lang.String,,,"</li>
	 * <li>���̒l���w�肵��setProperty(String name, Object val)�����s����</li>
	 * <li>name :"A"</li>
	 * <li>Object:"1"</li>
	 * <li>���̒l���w�肵��getLongProperty(String name)�����s����</li>
	 * <li>name :A</li>
	 * </ul>
	 * �m�F�F
	 * <ul>
	 * <li>����I���B��OPropertySchemaDefineException���������Ȃ����Ƃ��m�F</li>
	 * <li>1���Ԃ��Ă��邱�Ƃ��m�F</li>
	 * </ul>
	 */
	public void testGetLongPropertyString3() {
		try {
			String schema = ":A,java.lang.String,,,";
			Record rec = new Record(schema);
			rec.setProperty("A", "1");
			assertEquals((long) 1, rec.getLongProperty("A"));
		} catch (PropertySchemaDefineException e) {
			e.printStackTrace();
			fail("��O����");
		}
	}

	/**
	 * �w�肳�ꂽ���O��int�v���p�e�B��long�Ƃ��Ď擾����B
	 * <p>
	 * �����F
	 * <ul>
	 * <li>���̃X�L�[�}���w�肵��Record(String schema)�����s����</li>
	 * <li>":A,int,,,"</li>
	 * <li>���̒l���w�肵��setProperty(String name, int val)�����s����</li>
	 * <li>name :"A"</li>
	 * <li>val:100</li>
	 * <li>���̒l���w�肵��getLongProperty(String name)�����s����</li>
	 * <li>name :A</li>
	 * </ul>
	 * �m�F�F
	 * <ul>
	 * <li>����I���B��OPropertySchemaDefineException���������Ȃ����Ƃ��m�F</li>
	 * <li>100���Ԃ��Ă��邱�Ƃ��m�F</li>
	 * </ul>
	 */
	public void testGetLongPropertyString4() {
		try {
			String schema = ":A,int,,,";
			Record rec = new Record(schema);
			rec.setProperty("A", 100);
			assertEquals((long) 100, rec.getLongProperty("A"));
		} catch (PropertySchemaDefineException e) {
			e.printStackTrace();
			fail("��O����");
		}
	}

	/**
	 * �w�肳�ꂽ���O��boolean�v���p�e�B��long�Ƃ��Ď擾����B
	 * <p>
	 * �����F
	 * <ul>
	 * <li>���̃X�L�[�}���w�肵��Record(String schema)�����s����</li>
	 * <li>":A,boolean,,,"</li>
	 * <li>���̒l���w�肵��setProperty(String name, boolean val)�����s����</li>
	 * <li>name :"A"</li>
	 * <li>val:true</li>
	 * <li>���̒l���w�肵��getLongProperty(String name)�����s����</li>
	 * <li>name :A</li>
	 * </ul>
	 * �m�F�F
	 * <ul>
	 * <li>����I���B��OPropertySchemaDefineException���������Ȃ����Ƃ��m�F</li>
	 * <li>1���Ԃ��Ă��邱�Ƃ��m�F</li>
	 * </ul>
	 */
	public void testGetLongPropertyString5() {
		try {
			String schema = ":A,boolean,,,";
			Record rec = new Record(schema);
			rec.setProperty("A", true);
			assertEquals((long) 1, rec.getLongProperty("A"));
		} catch (PropertySchemaDefineException e) {
			e.printStackTrace();
			fail("��O����");
		}
	}

	/**
	 * �w�肳�ꂽ���O�̃v���p�e�B��long�Ƃ��Ď擾����B
	 * <p>
	 * �����F
	 * <ul>
	 * <li>���̃X�L�[�}���w�肵��Record(String schema)�����s����</li>
	 * <li>":A,java.util.Date,,,"</li>
	 * <li>���̒l���w�肵��setProperty(String name, Object val)�����s����</li>
	 * <li>name :"A"</li>
	 * <li>val:Date�^�̒l (long�l�Ƃ��Ď擾�ł��Ȃ��^)</li>
	 * <li>���̒l���w�肵��getLongProperty(String name)�����s����</li>
	 * <li>name :A</li>
	 * </ul>
	 * �m�F�F
	 * <ul>
	 * <li>����I���B��OPropertyGetException���������邱�Ƃ��m�F</li>
	 * <li>��O���b�Z�[�W��"The type is unmatch. value=" + ret"���Ԃ���邱�Ƃ��m�F</li>
	 * </ul>
	 */
	public void testGetLongPropertyString6() {
		try {
			String schema = ":A,java.util.Date,,,";
			Record rec = new Record(schema);
			rec.setProperty("A", new Date());
			rec.getLongProperty("A");
			fail("��O���������Ȃ����߃e�X�g���s ");
		} catch (PropertyGetException e) {
		}
	}

	/**
	 * �w�肳�ꂽ�C���f�b�N�X�̃v���p�e�B��long�Ƃ��Ď擾����e�X�g�B
	 * <p>
	 * �����F
	 * <ul>
	 * <li>���̃X�L�[�}���w�肵��Record(String schema)�����s����</li>
	 * <li>":A,long,,,"</li>
	 * <li>���̒l���w�肵��setProperty(String name, long val)�����s����</li>
	 * <li>name :"A"</li>
	 * <li>value:1</li>
	 * <li>���̒l���w�肵��getLongProperty(int index)�����s����</li>
	 * <li>int :0</li>
	 * </ul>
	 * �m�F�F
	 * <ul>
	 * <li>����I���B��OPropertySchemaDefineException���������Ȃ����Ƃ��m�F</li>
	 * <li>1���Ԃ��Ă��邱�Ƃ��m�F</li>
	 * </ul>
	 */
	public void testGetLongPropertyInt1() {
		try {
			String schema = ":A,long,,,";
			Record rec = new Record(schema);
			rec.setProperty("A", (long) 1);
			assertEquals((long) 1, rec.getLongProperty(0));
		} catch (PropertySchemaDefineException e) {
			e.printStackTrace();
			fail("��O����");
		}
	}

	/**
	 * �w�肳�ꂽ�C���f�b�N�X�̃v���p�e�B��long�Ƃ��Ď擾����e�X�g�B
	 * <p>
	 * �����F
	 * <ul>
	 * <li>���̃X�L�[�}���w�肵��Record(String schema)�����s����</li>
	 * <li>":A,long,,,"</li>
	 * <li>���̒l���w�肵��setProperty(String name, long val)�����s����</li>
	 * <li>name :"A"</li>
	 * <li>value:1</li>
	 * <li>���̒l���w�肵��getLongProperty(int index)�����s����</li>
	 * <li>int :1(���݂��Ȃ��C���f�b�N�X)</li>
	 * </ul>
	 * �m�F�F
	 * <ul>
	 * <li>��OPropertyGetException���������邱�Ƃ��m�F</li>
	 * </ul>
	 */
	public void testGetLongPropertyInt2() {
		try {
			String schema = ":A,long,,,";
			Record rec = new Record(schema);
			rec.setProperty("A", (long) 1);
			rec.getLongProperty(1);
			fail("��O���������Ȃ����߃e�X�g���s ");
		} catch (PropertyGetException e) {
		}
	}

	/**
	 * �w�肳�ꂽ���O�̃v���p�e�B��float�Ƃ��Ď擾����e�X�g�B
	 * <p>
	 * �����F
	 * <ul>
	 * <li>���̃X�L�[�}���w�肵��Record(String schema)�����s����</li>
	 * <li>":A,float,,,"</li>
	 * <li>���̒l���w�肵��getFloatProperty(String name)�����s����</li>
	 * <li>name :A (�l�Ȃ�)</li>
	 * </ul>
	 * �m�F�F
	 * <ul>
	 * <li>����I���B��OPropertySchemaDefineException���������Ȃ����Ƃ��m�F</li>
	 * <li>0���Ԃ��Ă��邱�Ƃ��m�F</li>
	 * </ul>
	 */
	public void testGetFloatPropertyString1() {
		try {
			String schema = ":A,float,,,";
			Record rec = new Record(schema);
			assertEquals((float) 0, rec.getFloatProperty("A"), (float) 0);
		} catch (PropertySchemaDefineException e) {
			e.printStackTrace();
			fail("��O����");
		}
	}

	/**
	 * �w�肳�ꂽ���O�̃v���p�e�B��float�Ƃ��Ď擾����e�X�g�B
	 * <p>
	 * �����F
	 * <ul>
	 * <li>���̃X�L�[�}���w�肵��Record(String schema)�����s����</li>
	 * <li>":A,float,,,"</li>
	 * <li>���̒l���w�肵��setProperty(String name, float val)�����s����</li>
	 * <li>name :"A"</li>
	 * <li>value:1</li>
	 * <li>���̒l���w�肵��getFloatProperty(String name)�����s����</li>
	 * <li>name :A</li>
	 * </ul>
	 * �m�F�F
	 * <ul>
	 * <li>����I���B��OPropertySchemaDefineException���������Ȃ����Ƃ��m�F</li>
	 * <li>1���Ԃ��Ă��邱�Ƃ��m�F</li>
	 * </ul>
	 */
	public void testGetFloatPropertyString2() {
		try {
			String schema = ":A,float,,,";
			Record rec = new Record(schema);
			rec.setProperty("A", (float) 1);
			assertEquals((float) 1, rec.getFloatProperty("A"), (float) 0);
		} catch (PropertySchemaDefineException e) {
			e.printStackTrace();
			fail("��O����");
		}
	}

	/**
	 * �w�肳�ꂽ���O��string�v���p�e�B��float�Ƃ��Ď擾����e�X�g�B
	 * <p>
	 * �����F
	 * <ul>
	 * <li>���̃X�L�[�}���w�肵��Record(String schema)�����s����</li>
	 * <li>":A,java.lang.String,,,"</li>
	 * <li>���̒l���w�肵��setProperty(String name, Object val)�����s����</li>
	 * <li>name :"A"</li>
	 * <li>Object:"1"</li>
	 * <li>���̒l���w�肵��getFloatProperty(String name)�����s����</li>
	 * <li>name :A</li>
	 * </ul>
	 * �m�F�F
	 * <ul>
	 * <li>����I���B��OPropertySchemaDefineException���������Ȃ����Ƃ��m�F</li>
	 * <li>1���Ԃ��Ă��邱�Ƃ��m�F</li>
	 * </ul>
	 */
	public void testGetFloatPropertyString3() {
		try {
			String schema = ":A,java.lang.String,,,";
			Record rec = new Record(schema);
			rec.setProperty("A", "1");
			assertEquals((float) 1, rec.getFloatProperty("A"), (float) 0);
		} catch (PropertySchemaDefineException e) {
			e.printStackTrace();
			fail("��O����");
		}
	}

	/**
	 * �w�肳�ꂽ���O��int�v���p�e�B��float�Ƃ��Ď擾����e�X�g�B
	 * <p>
	 * �����F
	 * <ul>
	 * <li>���̃X�L�[�}���w�肵��Record(String schema)�����s����</li>
	 * <li>":A,int,,,"</li>
	 * <li>���̒l���w�肵��setProperty(String name, int val)�����s����</li>
	 * <li>name :"A"</li>
	 * <li>val:100</li>
	 * <li>���̒l���w�肵��getFloatProperty(String name)�����s����</li>
	 * <li>name :A</li>
	 * </ul>
	 * �m�F�F
	 * <ul>
	 * <li>����I���B��OPropertySchemaDefineException���������Ȃ����Ƃ��m�F</li>
	 * <li>100���Ԃ��Ă��邱�Ƃ��m�F</li>
	 * </ul>
	 */
	public void testGetFloatPropertyString4() {
		try {
			String schema = ":A,int,,,";
			Record rec = new Record(schema);
			rec.setProperty("A", 100);
			assertEquals((float) 100, rec.getFloatProperty("A"), (float) 0);
		} catch (PropertySchemaDefineException e) {
			e.printStackTrace();
			fail("��O����");
		}
	}

	/**
	 * �w�肳�ꂽ���O��boolean�v���p�e�B��float�Ƃ��Ď擾����e�X�g�B
	 * <p>
	 * �����F
	 * <ul>
	 * <li>���̃X�L�[�}���w�肵��Record(String schema)�����s����</li>
	 * <li>":A,boolean,,,"</li>
	 * <li>���̒l���w�肵��setProperty(String name, boolean val)�����s����</li>
	 * <li>name :"A"</li>
	 * <li>val:true</li>
	 * <li>���̒l���w�肵��getFloatProperty(String name)�����s����</li>
	 * <li>name :A</li>
	 * </ul>
	 * �m�F�F
	 * <ul>
	 * <li>����I���B��OPropertySchemaDefineException���������Ȃ����Ƃ��m�F</li>
	 * <li>1���Ԃ��Ă��邱�Ƃ��m�F</li>
	 * </ul>
	 */
	public void testGetFloatPropertyString5() {
		try {
			String schema = ":A,boolean,,,";
			Record rec = new Record(schema);
			rec.setProperty("A", true);
			assertEquals((float) 1, rec.getFloatProperty("A"), (float) 0);
		} catch (PropertySchemaDefineException e) {
			e.printStackTrace();
			fail("��O����");
		}
	}

	/**
	 * �w�肳�ꂽ���O�̃v���p�e�B��float�Ƃ��Ď擾����e�X�g�B
	 * <p>
	 * �����F
	 * <ul>
	 * <li>���̃X�L�[�}���w�肵��Record(String schema)�����s����</li>
	 * <li>":A,java.util.Date,,,"</li>
	 * <li>���̒l���w�肵��setProperty(String name, Object val)�����s����</li>
	 * <li>name :"A"</li>
	 * <li>val:Date�^�̒l (float�l�Ƃ��Ď擾�ł��Ȃ��^)</li>
	 * <li>���̒l���w�肵��getFloatProperty(String name)�����s����</li>
	 * <li>name :A</li>
	 * </ul>
	 * �m�F�F
	 * <ul>
	 * <li>����I���B��OPropertyGetException���������邱�Ƃ��m�F</li>
	 * <li>��O���b�Z�[�W��"The type is unmatch. value=" + ret"���Ԃ���邱�Ƃ��m�F</li>
	 * </ul>
	 */
	public void testGetFloatPropertyString6() {
		try {
			String schema = ":A,java.util.Date,,,";
			Record rec = new Record(schema);
			rec.setProperty("A", new Date());
			rec.getFloatProperty("A");
			fail("��O���������Ȃ����߃e�X�g���s ");
		} catch (PropertyGetException e) {
		}
	}

	/**
	 * �w�肳�ꂽ�C���f�b�N�X�̃v���p�e�B��float�Ƃ��Ď擾����e�X�g�B
	 * <p>
	 * �����F
	 * <ul>
	 * <li>���̃X�L�[�}���w�肵��Record(String schema)�����s����</li>
	 * <li>":A,float,,,"</li>
	 * <li>���̒l���w�肵��setProperty(String name, float val)�����s����</li>
	 * <li>name :"A"</li>
	 * <li>value:1</li>
	 * <li>���̒l���w�肵��getLongProperty(int index)�����s����</li>
	 * <li>int :0</li>
	 * </ul>
	 * �m�F�F
	 * <ul>
	 * <li>����I���B��OPropertySchemaDefineException���������Ȃ����Ƃ��m�F</li>
	 * <li>1���Ԃ��Ă��邱�Ƃ��m�F</li>
	 * </ul>
	 */
	public void testGetFloatPropertyInt1() {
		try {
			String schema = ":A,float,,,";
			Record rec = new Record(schema);
			rec.setProperty("A", (float) 1);
			assertEquals((float) 1, rec.getFloatProperty(0), (float) 0);
		} catch (PropertySchemaDefineException e) {
			e.printStackTrace();
			fail("��O����");
		}
	}

	/**
	 * �w�肳�ꂽ�C���f�b�N�X�̃v���p�e�B��float�Ƃ��Ď擾����e�X�g�B
	 * <p>
	 * �����F
	 * <ul>
	 * <li>���̃X�L�[�}���w�肵��Record(String schema)�����s����</li>
	 * <li>":A,long,,,"</li>
	 * <li>���̒l���w�肵��setProperty(String name, long val)�����s����</li>
	 * <li>name :"A"</li>
	 * <li>value:1</li>
	 * <li>���̒l���w�肵��getLongProperty(int index)�����s����</li>
	 * <li>int :1(���݂��Ȃ��C���f�b�N�X)</li>
	 * </ul>
	 * �m�F�F
	 * <ul>
	 * <li>��OPropertyGetException���������邱�Ƃ��m�F</li>
	 * </ul>
	 */
	public void testGetFloatPropertyInt2() {
		try {
			String schema = ":A,float,,,";
			Record rec = new Record(schema);
			rec.setProperty("A", (float) 1);
			rec.getFloatProperty(1);
			fail("��O���������Ȃ����߃e�X�g���s ");
		} catch (PropertyGetException e) {
		}
	}

	/**
	 * �w�肳�ꂽ���O�̃v���p�e�B��double�Ƃ��Ď擾����e�X�g�B
	 * <p>
	 * �����F
	 * <ul>
	 * <li>���̃X�L�[�}���w�肵��Record(String schema)�����s����</li>
	 * <li>":A,double,,,"</li>
	 * <li>���̒l���w�肵��getDoubleProperty(String name)�����s����</li>
	 * <li>name :A (�l�Ȃ�)</li>
	 * </ul>
	 * �m�F�F
	 * <ul>
	 * <li>����I���B��OPropertySchemaDefineException���������Ȃ����Ƃ��m�F</li>
	 * <li>0���Ԃ��Ă��邱�Ƃ��m�F</li>
	 * </ul>
	 */
	public void testGetDoublePropertyString1() {
		try {
			String schema = ":A,double,,,";
			Record rec = new Record(schema);
			assertEquals((double) 0, rec.getDoubleProperty("A"), (double) 0);
		} catch (PropertySchemaDefineException e) {
			e.printStackTrace();
			fail("��O����");
		}
	}

	/**
	 * �w�肳�ꂽ���O�̃v���p�e�B��double�Ƃ��Ď擾����e�X�g�B
	 * <p>
	 * �����F
	 * <ul>
	 * <li>���̃X�L�[�}���w�肵��Record(String schema)�����s����</li>
	 * <li>":A,double,,,"</li>
	 * <li>���̒l���w�肵��setProperty(String name, double val)�����s����</li>
	 * <li>name :"A"</li>
	 * <li>value:1</li>
	 * <li>���̒l���w�肵��getDoubleProperty(String name)�����s����</li>
	 * <li>name :A</li>
	 * </ul>
	 * �m�F�F
	 * <ul>
	 * <li>����I���B��OPropertySchemaDefineException���������Ȃ����Ƃ��m�F</li>
	 * <li>1���Ԃ��Ă��邱�Ƃ��m�F</li>
	 * </ul>
	 */
	public void testGetDoublePropertyString2() {
		try {
			String schema = ":A,double,,,";
			Record rec = new Record(schema);
			rec.setProperty("A", (double) 1);
			assertEquals((double) 1, rec.getDoubleProperty("A"), (double) 0);
		} catch (PropertySchemaDefineException e) {
			e.printStackTrace();
			fail("��O����");
		}
	}

	/**
	 * �w�肳�ꂽ���O��string�v���p�e�B��double�Ƃ��Ď擾����e�X�g�B
	 * <p>
	 * �����F
	 * <ul>
	 * <li>���̃X�L�[�}���w�肵��Record(String schema)�����s����</li>
	 * <li>":A,java.lang.String,,,"</li>
	 * <li>���̒l���w�肵��setProperty(String name, Object val)�����s����</li>
	 * <li>name :"A"</li>
	 * <li>Object:"1"</li>
	 * <li>���̒l���w�肵��getDoubleProperty(String name)�����s����</li>
	 * <li>name :A</li>
	 * </ul>
	 * �m�F�F
	 * <ul>
	 * <li>����I���B��OPropertySchemaDefineException���������Ȃ����Ƃ��m�F</li>
	 * <li>1���Ԃ��Ă��邱�Ƃ��m�F</li>
	 * </ul>
	 */
	public void testGetDoublePropertyString3() {
		try {
			String schema = ":A,java.lang.String,,,";
			Record rec = new Record(schema);
			rec.setProperty("A", "1");
			assertEquals((double) 1, rec.getDoubleProperty("A"), (double) 0);
		} catch (PropertySchemaDefineException e) {
			e.printStackTrace();
			fail("��O����");
		}
	}

	/**
	 * �w�肳�ꂽ���O��int�v���p�e�B��double�Ƃ��Ď擾����e�X�g�B
	 * <p>
	 * �����F
	 * <ul>
	 * <li>���̃X�L�[�}���w�肵��Record(String schema)�����s����</li>
	 * <li>":A,int,,,"</li>
	 * <li>���̒l���w�肵��setProperty(String name, int val)�����s����</li>
	 * <li>name :"A"</li>
	 * <li>val:100</li>
	 * <li>���̒l���w�肵��getDoubleProperty(String name)�����s����</li>
	 * <li>name :A</li>
	 * </ul>
	 * �m�F�F
	 * <ul>
	 * <li>����I���B��OPropertySchemaDefineException���������Ȃ����Ƃ��m�F</li>
	 * <li>100���Ԃ��Ă��邱�Ƃ��m�F</li>
	 * </ul>
	 */
	public void testGetDoublePropertyString4() {
		try {
			String schema = ":A,int,,,";
			Record rec = new Record(schema);
			rec.setProperty("A", 100);
			assertEquals((double) 100, rec.getDoubleProperty("A"), (double) 0);
		} catch (PropertySchemaDefineException e) {
			e.printStackTrace();
			fail("��O����");
		}
	}

	/**
	 * �w�肳�ꂽ���O��boolean�v���p�e�B��double�Ƃ��Ď擾����e�X�g�B
	 * <p>
	 * �����F
	 * <ul>
	 * <li>���̃X�L�[�}���w�肵��Record(String schema)�����s����</li>
	 * <li>":A,boolean,,,"</li>
	 * <li>���̒l���w�肵��setProperty(String name, boolean val)�����s����</li>
	 * <li>name :"A"</li>
	 * <li>val:true</li>
	 * <li>���̒l���w�肵��getDoubleProperty(String name)�����s����</li>
	 * <li>name :A</li>
	 * </ul>
	 * �m�F�F
	 * <ul>
	 * <li>����I���B��OPropertySchemaDefineException���������Ȃ����Ƃ��m�F</li>
	 * <li>1���Ԃ��Ă��邱�Ƃ��m�F</li>
	 * </ul>
	 */
	public void testGetDoublePropertyString5() {
		try {
			String schema = ":A,boolean,,,";
			Record rec = new Record(schema);
			rec.setProperty("A", true);
			assertEquals((double) 1, rec.getDoubleProperty("A"), (double) 0);
		} catch (PropertySchemaDefineException e) {
			e.printStackTrace();
			fail("��O����");
		}
	}

	/**
	 * �w�肳�ꂽ���O�̃v���p�e�B��double�Ƃ��Ď擾����e�X�g�B
	 * <p>
	 * �����F
	 * <ul>
	 * <li>���̃X�L�[�}���w�肵��Record(String schema)�����s����</li>
	 * <li>":A,java.util.Date,,,"</li>
	 * <li>���̒l���w�肵��setProperty(String name, Object val)�����s����</li>
	 * <li>name :"A"</li>
	 * <li>val:Date�^�̒l (float�l�Ƃ��Ď擾�ł��Ȃ��^)</li>
	 * <li>���̒l���w�肵��getDoubleProperty(String name)�����s����</li>
	 * <li>name :A</li>
	 * </ul>
	 * �m�F�F
	 * <ul>
	 * <li>��OPropertyGetException���������邱�Ƃ��m�F</li>
	 * <li>��O���b�Z�[�W��"The type is unmatch. value=" + ret"���Ԃ���邱�Ƃ��m�F</li>
	 * </ul>
	 */
	public void testGetDoublePropertyString6() {
		try {
			String schema = ":A,java.util.Date,,,";
			Record rec = new Record(schema);
			rec.setProperty("A", new Date());
			rec.getDoubleProperty("A");
			fail("��O���������Ȃ����߃e�X�g���s ");
		} catch (PropertyGetException e) {
		}
	}

	/**
	 * �w�肳�ꂽ���O��string�v���p�e�B��double�Ƃ��Ď擾����e�X�g�B
	 * <p>
	 * �����F
	 * <ul>
	 * <li>���̃X�L�[�}���w�肵��Record(String schema)�����s����</li>
	 * <li>":A,java.lang.String,,,"</li>
	 * <li>���̒l���w�肵��setProperty(String name, Object val)�����s����</li>
	 * <li>name :"A"</li>
	 * <li>Object:"aaa"�i���l�ϊ��ł��Ȃ������j</li>
	 * <li>���̒l���w�肵��getDoubleProperty(String name)�����s����</li>
	 * <li>name :A</li>
	 * </ul>
	 * �m�F�F
	 * <ul>
	 * <li>��OPropertyGetException���������邱�Ƃ��m�F</li>
	 * </ul>
	 */
	public void testGetDoublePropertyString7() {
		try {
			String schema = ":A,java.lang.String,,,";
			Record rec = new Record(schema);
			rec.setProperty("A", "0.0.0.0");
			rec.getDoubleProperty("A");
			fail("��O���������Ȃ����߃e�X�g���s ");
		} catch (PropertyGetException e) {
		}
	}


	/**
	 * �w�肳�ꂽ�C���f�b�N�X�̃v���p�e�B��double�Ƃ��Ď擾����e�X�g�B
	 * <p>
	 * �����F
	 * <ul>
	 * <li>���̃X�L�[�}���w�肵��Record(String schema)�����s����</li>
	 * <li>":A,double,,,"</li>
	 * <li>���̒l���w�肵��setProperty(String name, float val)�����s����</li>
	 * <li>name :"A"</li>
	 * <li>value:1</li>
	 * <li>���̒l���w�肵��getDoubleProperty(int index)�����s����</li>
	 * <li>int :0</li>
	 * </ul>
	 * �m�F�F
	 * <ul>
	 * <li>����I���B��OPropertySchemaDefineException���������Ȃ����Ƃ��m�F</li>
	 * <li>1���Ԃ��Ă��邱�Ƃ��m�F</li>
	 * </ul>
	 */
	public void testGetDoublePropertyInt1() {
		try {
			String schema = ":A,float,,,";
			Record rec = new Record(schema);
			rec.setProperty("A", (float) 1);
			assertEquals((float) 1, rec.getDoubleProperty(0), (float) 0);
		} catch (PropertySchemaDefineException e) {
			e.printStackTrace();
			fail("��O����");
		}
	}

	/**
	 * �w�肳�ꂽ�C���f�b�N�X�̃v���p�e�B��double�Ƃ��Ď擾����e�X�g�B
	 * <p>
	 * �����F
	 * <ul>
	 * <li>���̃X�L�[�}���w�肵��Record(String schema)�����s����</li>
	 * <li>":A,double,,,"</li>
	 * <li>���̒l���w�肵��setProperty(String name, long val)�����s����</li>
	 * <li>name :"A"</li>
	 * <li>value:1</li>
	 * <li>���̒l���w�肵��getDoubleProperty(int index)�����s����</li>
	 * <li>int :1(���݂��Ȃ��C���f�b�N�X)</li>
	 * </ul>
	 * �m�F�F
	 * <ul>
	 * <li>��OPropertyGetException���������邱�Ƃ��m�F</li>
	 * </ul>
	 */
	public void testGetDoublePropertyInt2() {
		try {
			String schema = ":A,float,,,";
			Record rec = new Record(schema);
			rec.setProperty("A", (float) 1);
			rec.getDoubleProperty(1);
			fail("��O���������Ȃ����߃e�X�g���s ");
		} catch (PropertyGetException e) {
		}
	}

	/**
	 * �w�肳�ꂽ���O�̃v���p�e�B�𕶎���Ƃ��Ď擾����e�X�g�B
	 * <p>
	 * �����F
	 * <ul>
	 * <li>���̃X�L�[�}���w�肵��Record(String schema)�����s����</li>
	 * <li>":A,java.lang.String,,,"</li>
	 * <li>���̒l���w�肵��getStringProperty(String name)�����s����</li>
	 * <li>name :A (�l�Ȃ�)</li>
	 * </ul>
	 * �m�F�F
	 * <ul>
	 * <li>����I���B��OPropertySchemaDefineException���������Ȃ����Ƃ��m�F</li>
	 * <li>null���Ԃ��Ă��邱�Ƃ��m�F</li>
	 * </ul>
	 */
	public void testGetStringPropertyString1() {
		try {
			String schema = ":A,java.lang.String,,,";
			Record rec = new Record(schema);
			assertNull(rec.getStringProperty("A"));
		} catch (PropertySchemaDefineException e) {
			e.printStackTrace();
			fail("��O����");
		}
	}

	/**
	 * �w�肳�ꂽ���O�̃v���p�e�B�𕶎���Ƃ��Ď擾����e�X�g�B
	 * <p>
	 * �����F
	 * <ul>
	 * <li>���̃X�L�[�}���w�肵��Record(String schema)�����s����</li>
	 * <li>":A,java.lang.String,,,"</li>
	 * <li>���̒l���w�肵��setProperty(String name, String val)�����s����</li>
	 * <li>name :"A"</li>
	 * <li>value:"B"</li>
	 * <li>���̒l���w�肵��getStringProperty(String name)�����s����</li>
	 * <li>name :A</li>
	 * </ul>
	 * �m�F�F
	 * <ul>
	 * <li>����I���B��OPropertySchemaDefineException���������Ȃ����Ƃ��m�F</li>
	 * <li>"B"���Ԃ��Ă��邱�Ƃ��m�F</li>
	 * </ul>
	 */
	public void testGetStringPropertyString2() {
		try {
			String schema = ":A,java.lang.String,,,";
			Record rec = new Record(schema);
			rec.setProperty("A", "B");
			assertEquals("B", rec.getStringProperty("A"));
		} catch (PropertySchemaDefineException e) {
			e.printStackTrace();
			fail("��O����");
		}
	}

	/**
	 * �w�肳�ꂽ���O��int�v���p�e�B�𕶎���Ƃ��Ď擾����e�X�g�B
	 * <p>
	 * �����F
	 * <ul>
	 * <li>���̃X�L�[�}���w�肵��Record(String schema)�����s����</li>
	 * <li>":A,int,,,"</li>
	 * <li>���̒l���w�肵��setProperty(String name, String val)�����s����</li>
	 * <li>name :"A"</li>
	 * <li>value:1</li>
	 * <li>���̒l���w�肵��getStringProperty(String name)�����s����</li>
	 * <li>name :A</li>
	 * </ul>
	 * �m�F�F
	 * <ul>
	 * <li>����I���B��OPropertySchemaDefineException���������Ȃ����Ƃ��m�F</li>
	 * <li>"1"���Ԃ��Ă��邱�Ƃ��m�F</li>
	 * </ul>
	 */
	public void testGetStringPropertyString3() {
		try {
			String schema = ":A,int,,,";
			Record rec = new Record(schema);
			rec.setProperty("A", 1);
			assertEquals("1", rec.getStringProperty("A"));
		} catch (PropertySchemaDefineException e) {
			e.printStackTrace();
			fail("��O����");
		}
	}

	/**
	 * �w�肳�ꂽ�C���f�b�N�X�̃v���p�e�B�𕶎���Ƃ��Ď擾����e�X�g�B
	 * <p>
	 * �����F
	 * <ul>
	 * <li>���̃X�L�[�}���w�肵��Record(String schema)�����s����</li>
	 * <li>":A,java.lang.String,,,"</li>
	 * <li>���̒l���w�肵��setProperty(String name, String val)�����s����</li>
	 * <li>name :"A"</li>
	 * <li>value:"B"</li>
	 * <li>���̒l���w�肵��getStringProperty(int index)�����s����</li>
	 * <li>int :0</li>
	 * </ul>
	 * �m�F�F
	 * <ul>
	 * <li>����I���B��OPropertySchemaDefineException���������Ȃ����Ƃ��m�F</li>
	 * <li>"B"���Ԃ��Ă��邱�Ƃ��m�F</li>
	 * </ul>
	 */
	public void testGetStringPropertyInt1() {
		try {
			String schema = ":A,java.lang.String,,,";
			Record rec = new Record(schema);
			rec.setProperty("A", "B");
			assertEquals("B", rec.getStringProperty(0));
		} catch (PropertySchemaDefineException e) {
			e.printStackTrace();
			fail("��O����");
		}
	}

	/**
	 * �w�肳�ꂽ�C���f�b�N�X�̃v���p�e�B�𕶎���Ƃ��Ď擾����e�X�g�B
	 * <p>
	 * �����F
	 * <ul>
	 * <li>���̃X�L�[�}���w�肵��Record(String schema)�����s����</li>
	 * <li>":A,java.lang.String,,,"</li>
	 * <li>���̒l���w�肵��setProperty(String name, String val)�����s����</li>
	 * <li>name :"A"</li>
	 * <li>value:"B"</li>
	 * <li>���̒l���w�肵��getProperty(int index)�����s����</li>
	 * <li>int :1(���݂��Ȃ��C���f�b�N�X)</li>
	 * </ul>
	 * �m�F�F
	 * <ul>
	 * <li>��OPropertyGetException���������邱�Ƃ��m�F</li>
	 * </ul>
	 */
	public void testGetStringPropertyInt2() {
		try {
			String schema = ":A,java.lang.String,,,";
			Record rec = new Record(schema);
			rec.setProperty("A", "B");
			rec.getStringProperty(1);
			fail("��O���������Ȃ����߃e�X�g���s ");
		} catch (PropertyGetException e) {
		}
	}

	/**
	 * �w�肳�ꂽ���O�̃v���p�e�B���t�H�[�}�b�g���Ď擾����e�X�g�B
	 * <p>
	 * �����F
	 * <ul>
	 * <li>���̃X�L�[�}���w�肵��Record(String schema)�����s����</li>
	 * <li>":A,java.util.Date,,"jp.ossc.nimbus.util.converter.DateFormatConverter<BR>
	 * {ConvertType=1;Format="yyyy-MM-DD"}","</li>
	 * <li>���̒l���w�肵��setProperty(String name, String val)�����s����</li>
	 * <li>name :"A"</li>
	 * <li>Date�^�̒l </li>
	 * <li>���̒l���w�肵��getFormatProperty(String name)�����s����</li>
	 * <li>name :A </li>
	 * </ul>
	 * �m�F�F
	 * <ul>
	 * <li>����I���B��OPropertySchemaDefineException���������Ȃ����Ƃ��m�F</li>
	 * <li>���������t�t�H�[�}�b�g(yyyy-MM-DD)����ĕԂ��Ă��邱�Ƃ��m�F</li>
	 * </ul>
	 */
	public void testGetFormatPropertyString1() {
		try {
			String schema = ":A,java.util.Date,,"
					+ "\"jp.ossc.nimbus.util.converter.DateFormatConverter{ConvertType=1;Format=\"yyyy-MM-DD\"}\",";
			Record rec = new Record(schema);

			Calendar cal = Calendar.getInstance();
			cal.set(2008, 0, 22);
			Date date = cal.getTime();

			rec.setProperty("A", date);
			assertEquals("2008-01-22", rec.getFormatProperty("A"));
		} catch (PropertySchemaDefineException e) {
			e.printStackTrace();
			fail("��O����");
		}
	}

	/**
	 * �w�肳�ꂽ���O�̃v���p�e�B���t�H�[�}�b�g���Ď擾����e�X�g�B
	 * <p>
	 * �����F
	 * <ul>
	 * <li>���̃X�L�[�}���w�肵��Record(String schema)�����s����</li>
	 * <li>":A,java.util.Date,,"jp.ossc.nimbus.util.converter.DateFormatConverter<BR>
	 * {ConvertType=1;Format="yyyy-MM-DD"}","</li>
	 * <li>���̒l���w�肵��setProperty(String name, String val)�����s����</li>
	 * <li>name :"A"</li>
	 * <li>Date�^�̒l </li>
	 * <li>val:Date�^�̒l </li>
	 * <li>���̒l���w�肵��getFormatProperty(String name)�����s����</li>
	 * <li>name :B �i���݂��Ȃ��v���p�e�B�j </li>
	 * </ul>
	 * �m�F�F
	 * <ul>
	 * <li>��OPropertyGetException���������邱�Ƃ��m�F</li>
	 * <li>���b�Z�[�W"No such property : B"���Ԃ��Ă��邱�Ƃ��m�F</li>
	 * </ul>
	 */
	public void testGetFormatPropertyString2() {
		try {
			String schema = ":A,java.util.Date,,"
					+ "\"jp.ossc.nimbus.util.converter.DateFormatConverter{ConvertType=1;Format=\"yyyy-MM-DD\"}\",";
			Record rec = new Record(schema);

			Calendar cal = Calendar.getInstance();
			cal.set(2008, 0, 22);
			Date date = cal.getTime();

			rec.setProperty("A", date);
			rec.getFormatProperty("B");
			fail("��O���������Ȃ����߃e�X�g���s ");
		} catch (PropertyGetException e) {
			assertEquals("No such property : B", e.getMessage());
		}
	}

	/**
	 * �w�肳�ꂽ�C���f�b�N�X�̃v���p�e�B���t�H�[�}�b�g���Ď擾����e�X�g�B
	 * <p>
	 * �����F
	 * <ul>
	 * <li>���̃X�L�[�}���w�肵��Record(String schema)�����s����</li>
	 * <li>":A,java.util.Date,,"jp.ossc.nimbus.util.converter.DateFormatConverter<BR>
	 * {ConvertType=1;Format="yyyy-MM-DD"}","</li>
	 * <li>���̒l���w�肵��setProperty(String name, String val)�����s����</li>
	 * <li>name :"A"</li>
	 * <li>Date�^�̒l </li>
	 * <li>���̒l���w�肵��getFormatProperty(int index)�����s����</li>
	 * <li>index :0 </li>
	 * </ul>
	 * �m�F�F
	 * <ul>
	 * <li>����I���B��OPropertySchemaDefineException���������Ȃ����Ƃ��m�F</li>
	 * <li>���������t�t�H�[�}�b�g(yyyy-MM-DD)����ĕԂ��Ă��邱�Ƃ��m�F</li>
	 * </ul>
	 */
	public void testGetFormatPropertyInt1() {
		try {
			String schema = ":A,java.util.Date,,"
					+ "\"jp.ossc.nimbus.util.converter.DateFormatConverter{ConvertType=1;Format=\"yyyy-MM-DD\"}\",";
			Record rec = new Record(schema);

			Calendar cal = Calendar.getInstance();
			cal.set(2008, 0, 22);
			Date date = cal.getTime();

			rec.setProperty("A", date);
			assertEquals("2008-01-22", rec.getFormatProperty(0));
		} catch (PropertySchemaDefineException e) {
			e.printStackTrace();
			fail("��O����");
		}
	}

	/**
	 * �w�肳�ꂽ���O�̃v���p�e�B���t�H�[�}�b�g���Ď擾����e�X�g�B
	 * <p>
	 * �����F
	 * <ul>
	 * <li>���̃X�L�[�}���w�肵��Record(String schema)�����s����</li>
	 * <li>":A,java.util.Date,,"jp.ossc.nimbus.util.converter.DateFormatConverter<BR>
	 * {ConvertType=1;Format="yyyy-MM-DD"}","</li>
	 * <li>���̒l���w�肵��setProperty(String name, String val)�����s����</li>
	 * <li>name :"A"</li>
	 * <li>Date�^�̒l </li>
	 * <li>���̒l���w�肵��getFormatProperty(int index)�����s����</li>
	 * <li>index :1 (�s���ȃC���f�b�N�X) </li>
	 * </ul>
	 * �m�F�F
	 * <ul>
	 * <li>��OPropertyGetException���������邱�Ƃ��m�F</li>
	 * </ul>
	 */
	public void testGetFormatPropertyInt2() {
		try {
			String schema = ":A,java.util.Date,,"
					+ "\"jp.ossc.nimbus.util.converter.DateFormatConverter{ConvertType=1;Format=\"yyyy-MM-DD\"}\",";
			Record rec = new Record(schema);

			Calendar cal = Calendar.getInstance();
			cal.set(2008, 0, 22);
			Date date = cal.getTime();

			rec.setProperty("A", date);
			rec.getFormatProperty(1);
			fail("��O���������Ȃ����߃e�X�g���s ");
		} catch (PropertyGetException e) {
		}
	}

	/**
	 * �w�肳�ꂽ���O�̃v���p�e�B�ɁA�w�肳�ꂽ�l���p�[�X���Đݒ肷��e�X�g�B
	 * <p>
	 * �����F
	 * <ul>
	 * <li>���̃X�L�[�}���w�肵��Record(String schema)�����s����</li>
	 * <li>":A,java.util.Date,<BR>
	 * "jp.ossc.nimbus.util.converter.DateFormatConverter{ConvertType=2;Format="yyyy-MM-DD"}",,"</li>
	 * <li>���̒l���w�肵��setParseProperty(String name, Object val)�����s����</li>
	 * <li>name:"A" </li>
	 * <li>val: "yyyy-MM-DD"�̃t�H�[�}�b�g�̓��t��\����������</li>
	 * <li>���̒l���w�肵��getProperty(String name)�����s����</li>
	 * <li>name :A </li>
	 * </ul>
	 * �m�F�F
	 * <ul>
	 * <li>����I���B��OPropertySchemaDefineException���������Ȃ����Ƃ��m�F</li>
	 * <li>�w�肵�����t��Date�^�I�u�W�F�N�g���Ԃ��Ă��邱�Ƃ��m�F</li>
	 * </ul>
	 */
	public void testSetParsePropertyStringObject1() {
		try {
			String schema = ":A,java.util.Date,"
					+ "\"jp.ossc.nimbus.util.converter.DateFormatConverter{ConvertType=2;Format=\"yyyy-MM-DD\"}\",,";
			Record rec = new Record(schema);
			rec.setParseProperty("A", "2008-01-22");

			Calendar cal = Calendar.getInstance();
			cal.set(2008, 0, 22);
			Date date = cal.getTime();

			String d1 = java.text.DateFormat.getDateInstance().format(date);
			String d2 = java.text.DateFormat.getDateInstance().format(
					(Date) rec.getProperty("A"));
			assertEquals(d1, d2);
		} catch (PropertySchemaDefineException e) {
			e.printStackTrace();
			fail("��O����");
		}
	}

	/**
	 * �w�肳�ꂽ���O�̃v���p�e�B�ɁA�w�肳�ꂽ�l���p�[�X���Đݒ肷��e�X�g�B
	 * <p>
	 * �����F
	 * <ul>
	 * <li>���̃X�L�[�}���w�肵��Record(String schema)�����s����</li>
	 * <li>":A,java.util.Date,<BR>
	 * "jp.ossc.nimbus.util.converter.DateFormatConverter{ConvertType=2;Format="yyyy-MM-DD"}",,"</li>
	 * <li>���̒l���w�肵��setParseProperty(String name, Object val)�����s����</li>
	 * <li>name:"B" (���݂��Ȃ��v���p�e�B)</li>
	 * <li>val: "yyyy-MM-DD"�̃t�H�[�}�b�g�̓��t��\����������</li>
	 * <li>���̒l���w�肵��getFormatProperty(String name)�����s����</li>
	 * <li>name :B �i���݂��Ȃ��v���p�e�B�j </li>
	 * </ul>
	 * �m�F�F
	 * <ul>
	 * <li>��OPropertySetException���������邱�Ƃ��m�F</li>
	 * <li>���b�Z�[�W"No such property : B"���Ԃ��Ă��邱�Ƃ��m�F</li>
	 * </ul>
	 */
	public void testSetParsePropertyStringObject2() {
		try {
			String schema = ":A,java.util.Date,"
					+ "\"jp.ossc.nimbus.util.converter.DateFormatConverter{ConvertType=2;Format=\"yyyy-MM-DD\"}\",,";
			Record rec = new Record(schema);
			rec.setParseProperty("B", "2008-01-22");
			fail("��O���������Ȃ����߃e�X�g���s ");
		} catch (PropertySetException e) {
			assertEquals("No such property : B", e.getMessage());
		}
	}

	/**
	 * �w�肳�ꂽ���O�̃v���p�e�B�ɁA�w�肳�ꂽ�l���p�[�X���Đݒ肷��e�X�g�B
	 * <p>
	 * �����F
	 * <ul>
	 * <li>���̃X�L�[�}���w�肵��Record(String schema)�����s����</li>
	 * <li>":A,java.util.Date,<BR>
	 * "jp.ossc.nimbus.util.converter.DateFormatConverter{ConvertType=2;Format="yyyy-MM-DD"}",,"</li>
	 * <li>���̒l���w�肵��setParseProperty(int index, Object val)�����s����</li>
	 * <li>index:0 </li>
	 * <li>val: "yyyy-MM-DD"�̃t�H�[�}�b�g�̓��t��\����������</li>
	 * <li>���̒l���w�肵��getProperty(int index)�����s����</li>
	 * <li>int :0</li>
	 * </ul>
	 * �m�F�F
	 * <ul>
	 * <li>����I���B��OPropertySchemaDefineException���������Ȃ����Ƃ��m�F</li>
	 * <li>�w�肵�����t��Date�^�I�u�W�F�N�g���Ԃ��Ă��邱�Ƃ��m�F</li>
	 * </ul>
	 */
	public void testSetParsePropertyIntObject1() {
		try {
			String schema = ":A,java.util.Date,"
					+ "\"jp.ossc.nimbus.util.converter.DateFormatConverter{ConvertType=2;Format=\"yyyy-MM-DD\"}\",,";
			Record rec = new Record(schema);
			rec.setParseProperty("A", "2008-01-22");

			Calendar cal = Calendar.getInstance();
			cal.set(2008, 0, 22);
			Date date = cal.getTime();

			String d1 = java.text.DateFormat.getDateInstance().format(date);
			String d2 = java.text.SimpleDateFormat.getDateInstance().format(
					(Date) rec.getProperty(0));
			assertEquals(d1, d2);
		} catch (PropertySchemaDefineException e) {
			e.printStackTrace();
			fail("��O����");
		}
	}

	/**
	 * �w�肳�ꂽ���O�̃v���p�e�B�ɁA�w�肳�ꂽ�l���p�[�X���Đݒ肷��e�X�g�B
	 * <p>
	 * �����F
	 * <ul>
	 * <li>���̃X�L�[�}���w�肵��Record(String schema)�����s����</li>
	 * <li>":A,java.util.Date,<BR>
	 * ,"jp.ossc.nimbus.util.converter.DateFormatConverter,{ConvertType=2;Format="yyyy-MM-DD"}","</li>
	 * <li>���̒l���w�肵��setParseProperty(int index, Object val)�����s����</li>
	 * <li>int :1(���݂��Ȃ��C���f�b�N�X)</li>
	 * </ul>
	 * �m�F�F
	 * <ul>
	 * <li>��OPropertySetException���������邱�Ƃ��m�F</li>
	 * </ul>
	 */
	public void testSetParsePropertyIntObject2() {
		try {
			String schema = ":A,java.util.Date,"
					+ "\"jp.ossc.nimbus.util.converter.DateFormatConverter{ConvertType=2;Format=\"yyyy-MM-DD\"}\",,";
			Record rec = new Record(schema);
			rec.setParseProperty(1, "2008-01-22");
			fail("��O���������Ȃ����߃e�X�g���s ");
		} catch (PropertySetException e) {
		}
	}

	/**
	 * �S�Ẵv���p�e�B���N���A����e�X�g�B
	 * <p>
	 * �����F
	 * <ul>
	 * <li>���̃X�L�[�}���w�肵��Record(String schema)�����s����</li>
	 * <li>":A,java.lang.String,,,"</li>
	 * <li>���̒l���w�肵��setProperty(String name, Object val)�����s����</li>
	 * <li>name :"A"</li>
	 * <li>Object:"a"</li>
	 * <li>clear()���s����</li>
	 * </ul>
	 * �m�F�F
	 * <ul>
	 * <li>����I���BgetProperty("A")��null�ł��邱�Ƃ��m�F</li>
	 * </ul>
	 */
	public void testClear() {
		try {
			String schema = ":A,java.lang.String,,,";
			Record rec = new Record(schema);
			rec.setProperty("A", "a");
			rec.clear();
			assertTrue(rec.getProperty("A") == null);
		} catch (PropertySchemaDefineException e) {
			e.printStackTrace();
			fail("��O����");
		}
	}

	/**
	 * �����X�L�[�}�������f�[�^�������Ȃ���̃��R�[�h�𕡐�����e�X�g�B
	 * <p>
	 * �����F
	 * <ul>
	 * <li>���̃X�L�[�}���w�肵��Record(String schema)�����s����</li>
	 * <li>":A,java.lang.String,,,"</li>
	 * <li>cloneSchema()�����s���ă��R�[�h�̕����𐶐�����</li>
	 * <li>�o���̃��R�[�h�ɑ΂���getSchema()�����s����</li>
	 * </ul>
	 * �m�F�F
	 * <ul>
	 * <li>�����R�[�h�ƕ������R�[�h�̃X�L�[�}��񂪓��������Ƃ��m�F</li>
	 * </ul>
	 */
	public void testCloneSchema() {
		try {
			String schema = ":A,java.lang.String,,,";
			Record rec = new Record(schema);
			Record rec2 =rec.cloneSchema();
			assertEquals(rec.getSchema(), rec2.getSchema());
		} catch (PropertySchemaDefineException e) {
			e.printStackTrace();
			fail("��O����");
		}
	}

	/**
	 * ���R�[�h�𕡐�����e�X�g�B
	 * <p>
	 * �����F
	 * <ul>
	 * <li>���̃X�L�[�}���w�肵��Record(String schema)�����s����</li>
	 * <li>":A,java.lang.String,,,"</li>
	 * <li>���̒l���w�肵��setProperty(String name, Object val)�����s����</li>
	 * <li>name :"A"</li>
	 * <li>Object:"a"</li>
	 * <li>cloneRecord()�����s���ă��R�[�h�̕����𐶐�����</li>
	 * <li>�o���̃��R�[�h�ɑ΂��āA���̒l���w�肵��getProperty(String name)�����s����</li>
	 * <li>name :"A"</li>
	 * </ul>
	 * �m�F�F
	 * <ul>
	 * <li>�����R�[�h�ƕ������R�[�h�Ŏ擾�����l�����������Ƃ��m�F</li>
	 * </ul>
	 */
	public void testCloneRecord() {
		try {
			String schema = ":A,java.lang.String,,,";
			Record rec = new Record(schema);
			rec.setProperty("A", "a");
			Record rec2 =rec.cloneRecord();
			assertEquals(rec.getProperty("A"), rec2.getProperty("A"));
		} catch (PropertySchemaDefineException e) {
			e.printStackTrace();
			fail("��O����");
		}
	}

	/**
	 * ���R�[�h�𕶎���\������e�X�g�B
	 * <p>
	 * �����F
	 * <ul>
	 * <li>���̃X�L�[�}���w�肵��Record(String schema)�����s����</li>
	 * <li>":A,java.lang.String,,,"</li>
	 * <li>���̒l���w�肵��setProperty(String name, Object val)�����s����</li>
	 * <li>name :"A"</li>
	 * <li>val:"a"</li>
	 * <li>toString()�����s����</li>
	 * </ul>
	 * �m�F�F
	 * <ul>
	 * <li>������������\������Ă��邱�Ƃ��m�F</li>
	 * </ul>
	 */
	public void testToString1() {
		try {
			String schema = ":A,java.lang.String,,,";
			Record rec = new Record(schema);
			rec.setProperty("A", "a");
			assertEquals("{A=a}", rec.toString());
		} catch (PropertySchemaDefineException e) {
			e.printStackTrace();
			fail("��O����");
		}
	}

	/**
	 * ���R�[�h�𕶎���\������e�X�g�B
	 * <p>
	 * �����F
	 * <ul>
	 * <li>���̃X�L�[�}���w�肵��Record(String schema)�����s����</li>
	 * <li>":A,java.lang.String,,,"</li>
	 * <li>�l��ݒ肹����toString()�����s����</li>
	 * </ul>
	 * �m�F�F
	 * <ul>
	 * <li>�Ԃ蕶����"{}"�ł��邱�Ƃ��m�F</li>
	 * </ul>
	 */
	public void testToString2() {
		try {
			String schema = ":A,java.lang.String,,,";
			Record rec = new Record(schema);
			assertEquals("{}", rec.toString());
		} catch (PropertySchemaDefineException e) {
			e.printStackTrace();
			fail("��O����");
		}
	}

	/**
	 * ���R�[�h�T�C�Y(�l�̌�)���v�Z����e�X�g�B
	 * <p>
	 * �����F
	 * <ul>
	 * <li>���̃X�L�[�}���w�肵��Record(String schema)�����s����</li>
	 * <li>":A,java.lang.String,,,"</li>
	 * <li>���̒l���w�肵��setProperty(String name, Object val)�����s����</li>
	 * <li>name :"A"</li>
	 * <li>val:"a"</li>
	 * <li>size()�����s����</li>
	 * </ul>
	 * �m�F�F
	 * <ul>
	 * <li>�Ԃ�l���P�ł��邱�Ƃ��m�F</li>
	 * </ul>
	 */
	public void testSize() {
		try {
			String schema = ":A,java.lang.String,,,";
			Record rec = new Record(schema);
			rec.setProperty("A", "a");
			assertEquals(1, rec.size());
		} catch (PropertySchemaDefineException e) {
			e.printStackTrace();
			fail("��O����");
		}
	}

	/**
	 * ���R�[�h���󂩂ǂ������`�F�b�N����e�X�g�B
	 * <p>
	 * �����F
	 * <ul>
	 * <li>���̃X�L�[�}���w�肵��Record(String schema)�����s����</li>
	 * <li>":A,java.lang.String,,,"</li>
	 * <li>isEmpty()�����s����</li>
	 * <li>���̃X�L�[�}���w�肵��Record(String schema)�����s����</li>
	 * <li>""(�󕶎�)</li>
	 * <li>isEmpty()�����s����</li>
	 * </ul>
	 * �m�F�F
	 * <ul>
	 * <li>1��ڂ̕Ԃ�l��false�A2��ڂ̕Ԃ�l��true�ł��邱�Ƃ��m�F����</li>
	 * </ul>
	 */
	public void testIsEmpty() {
		try {
			String schema = ":A,java.lang.String,,,";
			Record rec1 = new Record(schema);
			assertFalse(rec1.isEmpty());
			schema = "";
			Record rec2 = new Record(schema);
			assertTrue(rec2.isEmpty());
		} catch (PropertySchemaDefineException e) {
			e.printStackTrace();
			fail("��O����");
		}
	}

	/**
	 * �v���p�e�B�l���i�[����map��key�����݂��邩���`�F�b�N����e�X�g�B
	 * <p>
	 * �����F
	 * <ul>
	 * <li>���̃X�L�[�}���w�肵��Record(String schema)�����s����</li>
	 * <li>":A,java.lang.String,,,"</li>
	 * <li>containsKey(Object key)�����s����</li>
	 * <li>���̃X�L�[�}���w�肵��Record(String schema)�����s����</li>
	 * <li>""(�󕶎�)</li>
	 * <li>containsKey(Object key)�����s����</li>
	 * </ul>
	 * �m�F�F
	 * <ul>
	 * <li>1��ڂ̕Ԃ�l��true�A2��ڂ̕Ԃ�l��false�ł��邱�Ƃ��m�F����</li>
	 * </ul>
	 */
	public void testContainsKey() {
		try {
			String schema = ":A,java.lang.String,,,";
			Record rec1 = new Record(schema);
			assertTrue(rec1.containsKey("A"));
			schema = "";
			Record rec2 = new Record(schema);
			assertFalse(rec2.containsKey("A"));
		} catch (PropertySchemaDefineException e) {
			e.printStackTrace();
			fail("��O����");
		}
	}

	/**
	 * �v���p�e�B�l���i�[����map��value�����݂��邩���`�F�b�N����e�X�g�B
	 * <p>
	 * �����F
	 * <ul>
	 * <li>���̃X�L�[�}���w�肵��Record(String schema)�����s����</li>
	 * <li>":A,java.lang.String,,,"</li>
	 * <li>�l��ݒ肵�Ȃ���containsValue(Object value)�����s����</li>
	 * <li>���̒l���w�肵��setProperty(String name, Object val)�����s����</li>
	 * <li>name :"A"</li>
	 * <li>Object:"a"</li>
	 * <li>containsValue(Object value)�����s����</li>
	 * </ul>
	 * �m�F�F
	 * <ul>
	 * <li>1��ڂ̕Ԃ�l��false�A2��ڂ̕Ԃ�l��true�ł��邱�Ƃ��m�F����</li>
	 * </ul>
	 */
	public void testContainsValue() {
		try {
			String schema = ":A,java.lang.String,,,";
			Record rec = new Record(schema);
			assertFalse(rec.containsValue("a"));
			rec.setProperty("A", "a");
			assertTrue(rec.containsValue("a"));
		} catch (PropertySchemaDefineException e) {
			e.printStackTrace();
			fail("��O����");
		}
	}

	/**
	 * �v���p�e�B�l���擾����e�X�g�B
	 * <p>
	 * �����F
	 * <ul>
	 * <li>���̃X�L�[�}���w�肵��Record(String schema)�����s����</li>
	 * <li>":A,java.lang.String,,,"</li>
	 * <li>���̒l���w�肵��setProperty(String name, Object val)�����s����</li>
	 * <li>name :"A"</li>
	 * <li>val:"a"</li>
	 * <li>get("A")�����s����</li>
	 * </ul>
	 * �m�F�F
	 * <ul>
	 * <li>�Ԃ�l��getProperty("A")�Ɠ��������Ƃ��m�F����</li>
	 * </ul>
	 */
	public void testGet() {
		try {
			String schema = ":A,java.lang.String,,,";
			Record rec = new Record(schema);
			rec.setProperty("A", "a");
			assertEquals(rec.getProperty("A"), rec.get("A"));
		} catch (PropertySchemaDefineException e) {
			e.printStackTrace();
			fail("��O����");
		}
	}

	/**
	 * �v���p�e�B�l��ݒ肷��e�X�g�B
	 * <p>
	 * �����F
	 * <ul>
	 * <li>���̃X�L�[�}���w�肵��Record(String schema)�����s����</li>
	 * <li>":A,java.lang.String,,,"</li>
	 * <li>���̒l���w�肵��put(Object key, Object value)�����s����i�P��ځj</li>
	 * <li>name :"A"</li>
	 * <li>val  :"a"</li>
	 * <li>���̒l���w�肵��put(Object key, Object value)�����s����i�Q��ځj</li>
	 * <li>name :"A"</li>
	 * <li>val  :"b"</li>
	 * </ul>
	 * �m�F�F
	 * <ul>
	 * <li>1��ڂ̕Ԃ�l��null�A2��ڂ̕Ԃ�l��"a"(�ύX�O�̒l)�ł��邱�Ƃ��m�F����</li>
	 * <li>getProperty("A")�̕Ԃ�l��"b"�ł��邱�Ƃ��m�F����</li>
	 * </ul>
	 */
	public void testPut() {
		try {
			String schema = ":A,java.lang.String,,,";
			Record rec = new Record(schema);
			assertNull(rec.put("A", "a"));
			assertEquals("a", rec.put("A","b"));
			assertEquals("b", rec.getProperty("A"));
		} catch (PropertySchemaDefineException e) {
			e.printStackTrace();
			fail("��O����");
		}
	}

	/**
	 * �v���p�e�B�l���폜����e�X�g�B
	 * <p>
	 * �����F
	 * <ul>
	 * <li>���̃X�L�[�}���w�肵��Record(String schema)�����s����</li>
	 * <li>":A,java.lang.String,,,"</li>
	 * <li>���̒l���w�肵��setProperty(String name, Object val)�����s����</li>
	 * <li>name :"A"</li>
	 * <li>val:"a"</li>
	 * <li>���̒l���w�肵��remove(Object key)�����s����</li>
	 * <li>key :"A"</li>
	 * </ul>
	 * �m�F�F
	 * <ul>
	 * <li>getProperty("A")�����s���Ēl���폜����Ă���inull�ł���j���Ƃ��m�F</li>
	 * </ul>
	 */
	public void testRemove() {
		try {
			String schema = ":A,java.lang.String,,,";
			Record rec = new Record(schema);
			rec.setProperty("A", "a");
			rec.remove("A");
			assertNull(rec.getProperty("A"));
		} catch (PropertySchemaDefineException e) {
			e.printStackTrace();
			fail("��O����");
		}
	}

	/**
	 * �v���p�e�B�l���폜����e�X�g�B
	 * <p>
	 * �����F
	 * <ul>
	 * <li>���̃X�L�[�}���w�肵��Record(String schema)�����s����</li>
	 * <li>":A,java.lang.String,,,"</li>
	 * <li>���̒l���w�肵��setProperty(String name, Object val)�����s����</li>
	 * <li>name :"A"</li>
	 * <li>val:"a"</li>
	 * <li>���̒l���w�肵��remove(Object key)�����s����</li>
	 * <li>key :"B"�i���݂��Ȃ����O�j</li>
	 * </ul>
	 * �m�F�F
	 * <ul>
	 * <li>null���Ԃ���邱�Ƃ��m�F</li>
	 * </ul>
	 */
	public void testRemoveInvallid() {
		try {
			String schema = ":A,java.lang.String,,,";
			Record rec = new Record(schema);
			rec.setProperty("A", "a");
			assertNull(rec.remove("B"));
		} catch (PropertySchemaDefineException e) {
			e.printStackTrace();
			fail("��O����");
		}
	}


	/**
	 * void putAll(Map t)�̃e�X�g
	 * <p>
	 * �����F
	 * <ul>
	 * <li>���̃X�L�[�}���w�肵��Record(String schema)�����s����</li>
	 * <li>":A,java.lang.String,,,\n:B,java.lang.String,,,"</li>
	 * <li>���̒l���w�肵��setProperty(String name, Object val)�����s����</li>
	 * <li>���̂Q�g�̃L�[�ƒl���Z�b�g����Ă���map���w�肵��putAll(Map t)�����s����</li>
	 * <li>�L�[ �F"A" �l�F"a"</li>
	 * <li>�L�[ �F"B" �l�F"b"</li>
	 * <li>���̒l���w�肵��getProperty(String name)�����s����</li>
	 * </ul>
	 * �m�F�F
	 * <ul>
	 * <li>getProperty("A")�̕Ԃ�l��"a"</li>
	 * <li>getProperty("B")�̕Ԃ�l��"b"</li>
	 * </ul>
	 */
	public void testPutAll() {
		try {
			String schema = ":A,java.lang.String,,,\n:B,java.lang.String,,,";
			Record rec = new Record(schema);
			
			Map t = new HashMap();
			t.put("A", "a");
			t.put("B", "b");
			rec.putAll(t);
			
			assertEquals("a", rec.getProperty("A"));
			assertEquals("b", rec.getProperty("B"));
		} catch (PropertySchemaDefineException e) {
			e.printStackTrace();
			fail("��O����");
		}
	}
	/**
	 * void putAll(Map t)�̃e�X�g
	 * <p>
	 * �����F
	 * <ul>
	 * <li>���̃X�L�[�}���w�肵��Record(String schema)�����s����</li>
	 * <li>":A,java.lang.String,,,\n:B,java.lang.String,,,"</li>
	 * <li>���̒l���w�肵��setProperty(String name, Object val)�����s����</li>
	 * <li>null���w�肵��putAll(Map t)�����s����</li>
	 * </ul>
	 * �m�F�F
	 * <ul>
	 * <li>����I�����邱�Ƃ��m�F</li>
	 * </ul>
	 */
	public void testPutAllNull() {
		try {
			String schema = ":A,java.lang.String,,,\n:B,java.lang.String,,,";
			Record rec = new Record(schema);
			
			rec.putAll(null);
		} catch (PropertySchemaDefineException e) {
			e.printStackTrace();
			fail("��O����");
		}
	}

	/**
	 * Set keySet()�̃e�X�g
	 * <p>
	 * �����F
	 * <ul>
	 * <li>���̃X�L�[�}���w�肵��Record(String schema)�����s����</li>
	 * <li>":A,java.lang.String,,,\n:B,java.lang.String,,,"</li>
	 * <li>keySet()�����s����</li>
	 * </ul>
	 * �m�F�F
	 * <ul>
	 * <li>�Ԃ�l�̗v�f�ɐݒ肵���v���p�e�B��("A","B")���Z�b�g����Ă���</li>
	 * </ul>
	 */
	public void testKetSet() {
		try {
			String schema = ":A,java.lang.String,,,\n:B,java.lang.String,,,";
			Record rec = new Record(schema);
			
			Set s = rec.keySet();
			assertEquals("A", s.toArray()[0]);
			assertEquals("B", s.toArray()[1]);
		} catch (PropertySchemaDefineException e) {
			e.printStackTrace();
			fail("��O����");
		}
	}
	

	/**
	 * Collection values()�̃e�X�g
	 * <p>
	 * �����F
	 * <ul>
	 * <li>���̃X�L�[�}���w�肵��Record(String schema)�����s����</li>
	 * <li>":A,java.lang.String,,,\n:B,java.lang.String,,,"</li>
	 * <li>���̂Q�g�̖��O�ƒl���w�肵��setProperty�����s����</li>
	 * <li>���O �F"A" �l�F"a"</li>
	 * <li>���O �F"B" �l�F"b"</li>
	 * <li>values()�����s����</li>
	 * </ul>
	 * �m�F�F
	 * <ul>
	 * <li>�Ԃ�l�̗v�f�ɐݒ肵���v���p�e�B�l�i"a","b"�j���Z�b�g����Ă���</li>
	 * </ul>
	 */
	public void testValues() {
		try {
			String schema = ":A,java.lang.String,,,\n:B,java.lang.String,,,";
			Record rec = new Record(schema);
			rec.setProperty("A", "a");
			rec.setProperty("B", "b");
			
			Collection v = rec.values();
			assertEquals("a", v.toArray()[0]);
			assertEquals("b", v.toArray()[1]);
		} catch (PropertySchemaDefineException e) {
			e.printStackTrace();
			fail("��O����");
		}
	}


	/**
	 * Set entrySet()�̃e�X�g
	 * <p>
	 * �����F
	 * <ul>
	 * <li>���̃X�L�[�}���w�肵��Record(String schema)�����s����</li>
	 * <li>":A,java.lang.String,,,\n:B,java.lang.String,,,"</li>
	 * <li>entrySet()�����s����</li>
	 * </ul>
	 * �m�F�F
	 * <ul>
	 * <li>�Ԃ�l�̗v�f�ɐݒ肵���v���p�e�B��("A","B")���Z�b�g����Ă���</li>
	 * </ul>
	 */
	public void testEntrySet() {
		try {
			String schema = ":A,java.lang.String,,,\n:B,java.lang.String,,,";
			Record rec = new Record(schema);
			
			Set e = rec.keySet();
			assertEquals("A", e.toArray()[0]);
			assertEquals("B", e.toArray()[1]);
		} catch (PropertySchemaDefineException e) {
			e.printStackTrace();
			fail("��O����");
		}
	}

	/**
	 * boolean equals(Object o)�̃e�X�g
	 * <p>
	 * �����F
	 * <ul>
	 * <li>���̃X�L�[�}���w�肵��Record(String schema)�����s����</li>
	 * <li>":A,java.lang.String,,,\n:B,java.lang.String,,,"</li>
	 * <li>null���w�肵��equals()�����s����</li>
	 * </ul>
	 * �m�F�F
	 * <ul>
	 * <li>false���Ԃ��Ă���</li>
	 * </ul>
	 */
	public void testEquals1() {
		try {
			String schema = ":A,java.lang.String,,,\n:B,java.lang.String,,,";
			Record rec = new Record(schema);
			
			assertFalse(rec.equals(null));
		} catch (PropertySchemaDefineException e) {
			e.printStackTrace();
			fail("��O����");
		}
	}

	/**
	 * boolean equals(Object o)�̃e�X�g
	 * <p>
	 * �����F
	 * <ul>
	 * <li>���̃X�L�[�}���w�肵��Record(String schema)�����s����</li>
	 * <li>":A,java.lang.String,,,\n:B,java.lang.String,,,"</li>
	 * <li>�������g���w�肵��equals()�����s����</li>
	 * </ul>
	 * �m�F�F
	 * <ul>
	 * <li>true���Ԃ��Ă���</li>
	 * </ul>
	 */
	public void testEquals2() {
		try {
			String schema = ":A,java.lang.String,,,\n:B,java.lang.String,,,";
			Record rec = new Record(schema);
			
			assertTrue(rec.equals(rec));
		} catch (PropertySchemaDefineException e) {
			e.printStackTrace();
			fail("��O����");
		}
	}


	/**
	 * boolean equals(Object o)�̃e�X�g
	 * <p>
	 * �����F
	 * <ul>
	 * <li>���̃X�L�[�}���w�肵��Record(String schema)�����s����</li>
	 * <li>":A,java.lang.String,,,\n:B,java.lang.String,,,"</li>
	 * <li>Record�^�łȂ��I�u�W�F�N�g�w�肵��equals()�����s����</li>
	 * </ul>
	 * �m�F�F
	 * <ul>
	 * <li>false���Ԃ��Ă���</li>
	 * </ul>
	 */
	public void testEquals3() {
		try {
			String schema = ":A,java.lang.String,,,\n:B,java.lang.String,,,";
			Record rec = new Record(schema);
			
			assertFalse(rec.equals("aaa"));
		} catch (PropertySchemaDefineException e) {
			e.printStackTrace();
			fail("��O����");
		}
	}

	/**
	 * boolean equals(Object o)�̃e�X�g
	 * <p>
	 * �����F
	 * <ul>
	 * <li>���̃X�L�[�}���w�肵��Record(String schema)�����s����</li>
	 * <li>":A,java.lang.String,,,\n:B,java.lang.String,,,"</li>
	 * <li���̃X�L�[�}���w�肵��Record�I�u�W�F�N�g���w�肵��equals()�����s����</li>
	 * <li>":C,java.lang.String,,,\n:D,java.lang.String,,,"</li>
	 * </ul>
	 * �m�F�F
	 * <ul>
	 * <li>false���Ԃ��Ă���</li>
	 * </ul>
	 */
	public void testEquals4() {
		try {
			String schema = ":A,java.lang.String,,,\n:B,java.lang.String,,,";
			Record rec1 = new Record(schema);
			schema = ":C,java.lang.String,,,\n:D,java.lang.String,,,";
			Record rec2 = new Record(schema);
			
			assertFalse(rec1.equals(rec2));
		} catch (PropertySchemaDefineException e) {
			e.printStackTrace();
			fail("��O����");
		}
	}


	/**
	 * boolean equals(Object o)�̃e�X�g
	 * <p>
	 * �����F
	 * <ul>
	 * <li>���̃X�L�[�}���w�肵��Record(String schema)�����s����</li>
	 * <li>":A,java.lang.String,,,\n:B,java.lang.String,,,"</li>
	 * <li>���̖��O�ƒl���w�肵��setProperty�����s����</li>
	 * <li>���O �F"A" �l�F"a"</li>
	 * <li���̃X�L�[�}���w�肵��Record�I�u�W�F�N�g���w�肵��equals()�����s����</li>
	 * <li>":A,java.lang.String,,,\n:B,java.lang.String,,,"</li>
	 * <li>���̖��O�ƒl���w�肵��setProperty�����s����</li>
	 * <li>���O �F"A" �l�F"a"</li>
	 * </ul>
	 * �m�F�F
	 * <ul>
	 * <li>�X�L�[�}�A�l�������Ȃ̂�true���Ԃ��Ă���</li>
	 * </ul>
	 */
	public void testEquals5() {
		try {
			String schema = ":A,java.lang.String,,,\n:B,java.lang.String,,,";
			Record rec1 = new Record(schema);
			rec1.setProperty("A", "a");
			schema = ":A,java.lang.String,,,\n:B,java.lang.String,,,";
			Record rec2 = new Record(schema);
			rec2.setProperty("A", "a");
			
			assertTrue(rec1.equals(rec2));
		} catch (PropertySchemaDefineException e) {
			e.printStackTrace();
			fail("��O����");
		}
	}

	/**
	 * boolean equals(Object o)�̃e�X�g
	 * <p>
	 * �����F
	 * <ul>
	 * <li>���̃X�L�[�}���w�肵��Record(String schema)�����s����</li>
	 * <li>":A,java.lang.String,,,\n:B,java.lang.String,,,"</li>
	 * <li>�l���ݒ�</li>
	 * <li���̃X�L�[�}���w�肵��Record�I�u�W�F�N�g���w�肵��equals()�����s����</li>
	 * <li>":A,java.lang.String,,,\n:B,java.lang.String,,,"</li>
	 * <li>���̖��O�ƒl���w�肵��setProperty�����s����</li>
	 * <li>���O �F"A" �l�F"a"</li>
	 * </ul>
	 * �m�F�F
	 * <ul>
	 * <li>�l���قȂ�̂�false���Ԃ��Ă���</li>
	 * </ul>
	 */
	public void testEquals6() {
		try {
			String schema = ":A,java.lang.String,,,\n:B,java.lang.String,,,";
			Record rec1 = new Record(schema);
			schema = ":A,java.lang.String,,,\n:B,java.lang.String,,,";
			Record rec2 = new Record(schema);
			rec2.setProperty("A", "a");
			
			assertFalse(rec1.equals(rec2));
		} catch (PropertySchemaDefineException e) {
			e.printStackTrace();
			fail("��O����");
		}
	}


	/**
	 * boolean equals(Object o)�̃e�X�g
	 * <p>
	 * �����F
	 * <ul>
	 * <li>���̃X�L�[�}���w�肵��Record(String schema)�����s����</li>
	 * <li>":A,java.lang.String,,,\n:B,java.lang.String,,,"</li>
	 * <li>���̖��O�ƒl���w�肵��setProperty�����s����</li>
	 * <li>���O �F"A" �l�F"a"</li>
	 * <li���̃X�L�[�}���w�肵��Record�I�u�W�F�N�g���w�肵��equals()�����s����</li>
	 * <li>":A,java.lang.String,,,\n:B,java.lang.String,,,"</li>
	 * <li>�l���ݒ�</li>
	 * </ul>
	 * �m�F�F
	 * <ul>
	 * <li>�l���قȂ�̂�false���Ԃ��Ă���</li>
	 * </ul>
	 */
	public void testEquals7() {
		try {
			String schema = ":A,java.lang.String,,,\n:B,java.lang.String,,,";
			Record rec1 = new Record(schema);
			rec1.setProperty("A", "a");
			schema = ":A,java.lang.String,,,\n:B,java.lang.String,,,";
			Record rec2 = new Record(schema);
			
			assertFalse(rec1.equals(rec2));
		} catch (PropertySchemaDefineException e) {
			e.printStackTrace();
			fail("��O����");
		}
	}


	/**
	 * int hashCode()�̃e�X�g
	 * <p>
	 * �����F
	 * <ul>
	 * <li>���̃X�L�[�}���w�肵��Record(String schema)�����s����</li>
	 * <li>":A,java.lang.String,,,\n:B,java.lang.String,,,"</li>
	 * <li>���̖��O�ƒl���w�肵��setProperty�����s����</li>
	 * <li>���O �F"A" �l�F"a"</li>
	 * <li>hashCode()�����s����</li>
	 * </ul>
	 * �m�F�F
	 * <ul>
	 * <li>�Ԃ�l��recordSchema.hashCode()+values.hashCode()</li>
	 * </ul>
	 */
	public void testHashCode1() {
		try {
			String schema = ":A,java.lang.String,,,\n:B,java.lang.String,,,";
			Record rec1 = new Record(schema);
			rec1.setProperty("A", "a");
			Record rec2 = new Record(schema);
			rec2.setProperty("A", "a");
			
			assertEquals(rec1.hashCode(), rec2.hashCode());
		} catch (PropertySchemaDefineException e) {
			e.printStackTrace();
			fail("��O����");
		}
	}

	
	
	/**
	 * ���R�[�h�I�u�W�F�N�g���r����e�X�g�B
	 * <p>
	 * �����F
	 * <ul>
	 * <li>���̃X�L�[�}���w�肵��Record(String schema)��2����s</li>
	 * <li>":A,java.lang.String,,,"</li>
	 * <li>�o����Record�ɑ΂��A���̒l���w�肵��setProperty(String name, Object val)�����s����</li>
	 * <li>name :"A"</li>
	 * <li>Object:"B"</li>
	 * <li>equals���\�b�h�����s����</li>
	 * </ul>
	 * �m�F�F
	 * <ul>
	 * <li>�o����Record��������(equals���\�b�h��true)���Ƃ��m�F</li>
	 * </ul>
	 */
	public void testEqualsObject() {
		try {
			String schema = ":A,java.lang.String,,,";
			Record rec1 = new Record(schema);
			Record rec2 = new Record(schema);
			rec1.setProperty("A", "a");
			rec2.setProperty("A", "a");
			assertTrue(rec1.equals(rec2));
		} catch (PropertySchemaDefineException e) {
			e.printStackTrace();
			fail("��O����");
		}
	}

}
