package jp.ossc.nimbus.beans.dataset;

import junit.framework.TestCase;
//
/**
 * 
 * @author   S.Teshima 
 * @version  1.00 �쐬: 2008/01/18 -�@S.Teshima
 */

public class RecordSchemaTest extends TestCase {

	public RecordSchemaTest(String arg0) {
        super(arg0);
    }
    
 
 	public static void main(String[] args) {
        junit.textui.TestRunner.run(RecordSchemaTest.class);
    }
 	
    /**
     * ���R�[�h�X�L�[�}���擾����e�X�g�B<p>
     * �����F
     * <ul>
     *   <li>���̃X�L�[�}���w�肵��getInstance(String schema)�����s����</li>
     *   <li>":A,java.lang.String,,,"  + ���s</li>
     * </ul>
     * �m�F�F
     * <ul>
     *   <li>���R�[�h�X�L�[�}������Ɏ擾�ł���</li>
     *   <li>��OPropertySchemaDefineException���������Ȃ����Ƃ��m�F</li>
     * </ul>
     */
	public void testGetInstance1() {
		try {
			String schema = ":A,java.lang.String,,,\n";
			RecordSchema.getInstance(schema);
		} catch (PropertySchemaDefineException e) {
			e.printStackTrace();
			fail("��O����");
		}
	}

    /**
     * �����X�L�[�}��`�̃��R�[�h�X�L�[�}���擾����e�X�g�B<p>
     * �����F
     * <ul>
      *  <li>���̃X�L�[�}���w�肵��getInstance(String schema)��2����s����</li>
     *   <li>":A,java.lang.String,,,"  + ���s</li>
    * </ul>
     * �m�F�F
     * <ul>
     *   <li>���R�[�h�X�L�[�}������Ɏ擾�ł���</li>
     *   <li>��OPropertySchemaDefineException���������Ȃ�</li>
     *   <li>2��ڂŎ擾�����C���X�^���X���V������������Ă��Ȃ�(1��ڂƓ����I�u�W�F�N�g)</li>
     * </ul>
     */
	public void testGetInstance2() {
		try {
			String schema = ":A,java.lang.String,,,\n";
			RecordSchema rsm1 = RecordSchema.getInstance(schema);
			assertEquals(rsm1.getSchema(), schema);
			RecordSchema rsm2 = RecordSchema.getInstance(schema);
			assertEquals(rsm1, rsm2);
		} catch (PropertySchemaDefineException e) {
			e.printStackTrace();
			fail("��O����");
		}
	}

    /**
     * ���R�[�h�X�L�[�}��ݒ肷��e�X�g�B<p>
     * �����F
     * <ul>
     *   <li>�v���p�e�B���͂P��</li>
     *   <li>�v���p�e�B�X�L�[�}�����N���X�w��Ȃ�</li>
     *   <li>�v���p�e�B�X�L�[�}��`���e�i�^�̂݁j</li>
     *   <li>���̓��e�Ŏw�肷�� ":A,java.lang.String,,,"</li>
     *   <li>�Ō�ɉ��s������Ƃ��ƂȂ��Ƃ��Ŋm�F����</li>
     * </ul>
     * �m�F�F
     * <ul>
     *   <li>���R�[�h�X�L�[�}������ɐݒ�ł���</li>
     *   <li>��OPropertySchemaDefineException���������Ȃ�</li>
     * </ul>
     */
	public void testSetSchema1() {
		try {
			String schema = ":A,java.lang.String,,,\n";
			RecordSchema rsm1 = RecordSchema.getInstance(schema);
			assertEquals(rsm1.getSchema(), schema);
			schema = ":A,java.lang.String,,,";
			RecordSchema rsm2 = RecordSchema.getInstance(schema);
			assertEquals(rsm2.getSchema(), schema);
		} catch (PropertySchemaDefineException e) {
			e.printStackTrace();
			fail("��O����");
		}
	}

	/**
     * ���R�[�h�X�L�[�}��ݒ肷��e�X�g�B<p>
     * �����F
     * <ul>
     *   <li>�v���p�e�B���͂Q��</li>
     *   <li>�v���p�e�B�X�L�[�}�����N���X�w��Ȃ�</li>
     *   <li>�v���p�e�B�X�L�[�}��`���e�i�^�̂݁j</li>
     *   <li>���̓��e�Ŏw�肷��<BR> ":A,java.lang.String,,," <BR> ":B,int,,,"</li>
     *   <li>���s��CRLF,CR,LF�Ŋm�F����</li>
     * </ul>
     * �m�F�F
     * <ul>
     *   <li>���R�[�h�X�L�[�}������ɐݒ�ł���</li>
     *   <li>��OPropertySchemaDefineException���������Ȃ�</li>
     * </ul>
     */
	public void testSetSchema2() {
		try {
			String schema = ":A,java.lang.String,,,\n:B,int,,,";
			RecordSchema rsm1 = RecordSchema.getInstance(schema);
			assertEquals(rsm1.getSchema(), schema);
			schema = ":A,java.lang.String,,,\r:B,int,,,";
			RecordSchema rsm2 = RecordSchema.getInstance(schema);
			assertEquals(rsm2.getSchema(), schema);
			schema = ":A,java.lang.String,,,\r\n:B,int,,,";
			RecordSchema rsm3 = RecordSchema.getInstance(schema);
			assertEquals(rsm3.getSchema(), schema);
		} catch (PropertySchemaDefineException e) {
			e.printStackTrace();
			fail("��O����");
		}
	}

	/**
     * ���R�[�h�X�L�[�}��ݒ肷��e�X�g�B<p>
     * �����F
     * <ul>
     *   <li>�v���p�e�B���͂Q��</li>
     *   <li>�v���p�e�B�X�L�[�}�����N���X�w��Ȃ�</li>
     *   <li>�v���p�e�B�X�L�[�}��`���e�i�^�̂݁j</li>
     *   <li>�Q�̃v���p�e�B�X�L�[�}��`�̊Ԃɉ��s�̂ݎw�肷��</li>
     *   <li>���̓��e�Ŏw�肷��<BR> ":A,java.lang.String,,," <BR><BR> ":B,int,,,"</li>
     * </ul>
     * �m�F�F
     * <ul>
     *   <li>���R�[�h�X�L�[�}������ɐݒ�ł���</li>
     *   <li>��OPropertySchemaDefineException���������Ȃ�</li>
     * </ul>
     */
	public void testSetSchema3() {
		try {
			String schema = ":A,java.lang.String,,,\n\n:B,int,,,";
			RecordSchema rsm1 = RecordSchema.getInstance(schema);
			assertEquals(rsm1.getSchema(), schema);
		} catch (PropertySchemaDefineException e) {
			e.printStackTrace();
			fail("��O����");
		}
	}

	/**
     * ���R�[�h�X�L�[�}��ݒ肷��e�X�g�B<p>
     * �����F
     * <ul>
     *   <li>�v���p�e�B���͂Q��</li>
     *   <li>�v���p�e�B�X�L�[�}�����N���X�w��Ȃ�</li>
     *   <li>�v���p�e�B�X�L�[�}��`���e�i�^�̂݁j</li>
     *   <li>�Q�ڂ̒�`�̃v���p�e�B�X�L�[�}�����N���X�̋�؂蕶���u:�v���w�肵�Ȃ�</li>
     *   <li>���̓��e�Ŏw�肷��<BR> ":A,java.lang.String,,," <BR> "B,int,,,"</li>
     * </ul>
     * �m�F�F
     * <ul>
     *   <li>��OPropertySchemaDefineException����������</li>
     *   <li>��O���b�Z�[�W"The class name of PropertySchema is not specified."��Ԃ�</li>
     * </ul>
     */
	public void testSetSchema4() {
		try {
			String schema = ":A,java.lang.String,,,\nB,int,,,";
			RecordSchema.getInstance(schema);
			fail("��O���������Ȃ����߃e�X�g���s ");
		} catch (PropertySchemaDefineException e) {
			assertEquals("B,int,,,:The class name of PropertySchema is not specified.", e.getMessage());
		}
	}

	/**
     * ���R�[�h�X�L�[�}��ݒ肷��e�X�g�B<p>
     * �����F
     * <ul>
     *   <li>�v���p�e�B���͂Q��</li>
     *   <li>�v���p�e�B�X�L�[�}�����N���X�w��Ȃ�</li>
     *   <li>�v���p�e�B�X�L�[�}��`���e�i�^�̂݁j</li>
     *   <li>�Q�ڂ̒�`�̓v���p�e�B�X�L�[�}�����N���X�̋�؂蕶���u:�v�̂ݎw��</li>
     *   <li>���̓��e�Ŏw�肷��<BR> ":A,java.lang.String,,," <BR> ":,,,,"</li>
     * </ul>
     * �m�F�F
     * <ul>
     *   <li>���R�[�h�X�L�[�}������ɐݒ�ł���</li>
     *   <li>��OPropertySchemaDefineException���������Ȃ�</li>
     * </ul>
     */
	public void testSetSchema5() {
		try {
			String schema = ":A,java.lang.String,,,\n:,,,,";
			RecordSchema rsm1 = RecordSchema.getInstance(schema);
			assertEquals(rsm1.getSchema(), schema);
		} catch (PropertySchemaDefineException e) {
			e.printStackTrace();
			fail("��O����");
		}
	}

	/**
     * ���R�[�h�X�L�[�}��ݒ肷��e�X�g�B<p>
     * �����F
     * <ul>
     *   <li>�v���p�e�B���͂Q��</li>
     *   <li>�v���p�e�B�X�L�[�}�����N���X�w�肠��i���݂��Ȃ��N���X�j</li>
     *   <li>�v���p�e�B�X�L�[�}��`���e�i�^�̂݁j</li>
     *   <li>���̓��e�Ŏw�肷�� <BR> "DUMMYCLASS:A,java.lang.String,,," <BR> "DUMMYCLASS:B,int,,,"</li>
     * </ul>
     * �m�F�F
     * <ul>
     *   <li>��OPropertySchemaDefineException����������</li>
     *   <li>��O���b�Z�[�W"The class name of PropertySchema is illegal."��Ԃ�</li>
     * </ul>
     */
	public void testSetSchema6() {
		try {
			String schema = "DUMMYCLASS:A,java.lang.String,,,\nDUMMYCLASS:B,,,,";
			RecordSchema.getInstance(schema);
			fail("��O���������Ȃ����߃e�X�g���s ");
		} catch (PropertySchemaDefineException e) {
			assertEquals("DUMMYCLASS:A,java.lang.String,,," +
					":The class name of PropertySchema is illegal.", e.getMessage());
		}
	}

	/**
     * ���R�[�h�X�L�[�}��ݒ肷��e�X�g�B<p>
     * �����F
     * <ul>
     *   <li>�v���p�e�B���͂Q��</li>
     *   <li>�v���p�e�B�X�L�[�}�����N���X�w��Ȃ�</li>
     *   <li>�v���p�e�B�X�L�[�}��`���e�i�^�̂݁j</li>
     *   <li>���̓��e�Ŏw�肷�� <BR> ":A,java.lang.String,,," <BR> ":B,int,,,"</li>
     * </ul>
     * �m�F�F
     * <ul>
     *   <li>���R�[�h�X�L�[�}������ɐݒ�ł���</li>
     *   <li>��OPropertySchemaDefineException���������Ȃ�</li>
     *   <li>�v���p�e�B�X�L�[�}�C���X�^���X������Ɏ擾�ł��Ă���</li>
     *   <li>propertySchemaMap�ɂQ�̃X�L�[�}��񂪃Z�b�g����Ă���</li>
     * </ul>
     */
	public void testSetSchema7() {
		try {
			String schema = ":A,java.lang.String,,,\n:B,int,,,";
			RecordSchema rsm1 = RecordSchema.getInstance(schema);
			assertEquals(rsm1.getSchema(), schema);
			assertTrue(rsm1.propertySchemaMap.size() == 2);
		} catch (PropertySchemaDefineException e) {
			e.printStackTrace();
			fail("��O����");
		}
	}

	/**
     * ���R�[�h�X�L�[�}��ݒ肷��e�X�g�B<p>
     * �����F
     * <ul>
     *   <li>�X�L�[�}���قȂ�R�̃��R�[�h�X�L�[�}�C���X�^���X�𐶐�</li>
     *   <li>�P�� <BR> ":A,java.lang.String,,," <BR> ":B,int,,,"</li>
     *   <li>�Q�� <BR> ":A,java.lang.String,,," <BR> ":C,int,,,"</li>
     *   <li>�R�� <BR> ":B,java.lang.String,,," <BR> ":C,int,,,"(�d�������`)</li>
     * </ul>
     * �m�F�F
     * <ul>
     *   <li>��OPropertySchemaDefineException���������Ȃ�</li>
     *   <li>�Q�ڂƂR�ڂŃv���p�e�B�X�L�[�}�̃C���X�^���X����������Ȃ�<BR>
     *���Ƃ��m�F</li>
     * </ul>
     */
	public void testSetSchema8() {
		try {
			String schema = ":A,java.lang.String,,,\n:B,int,,,";
			RecordSchema.getInstance(schema);
			schema = ":A,java.lang.String,,,\n:C,int,,,";
			RecordSchema.getInstance(schema);
			int scnt1 = RecordSchema.propertySchemaManager.size();
			schema = ":B,int,,,\n:C,int,,,";
			RecordSchema.getInstance(schema);
			int scnt2 = RecordSchema.propertySchemaManager.size();
			assertEquals(scnt1, scnt2);
		} catch (PropertySchemaDefineException e) {
			e.printStackTrace();
			fail("��O����");
		}
	}

	/**
     * ���R�[�h�X�L�[�}���擾����e�X�g�B<p>
     * �����F
     * <ul>
     *   <li>�v���p�e�B���͂Q��</li>
     *   <li>�v���p�e�B�X�L�[�}�����N���X�w��Ȃ�</li>
     *   <li>�v���p�e�B�X�L�[�}��`���e�i�^�̂݁j</li>
     *   <li>���̓��e�Ŏw�肷�� <BR> ":A,java.lang.String,,," <BR> ":B,int,,,"</li>
     * </ul>
     * �m�F�F
     * <ul>
     *   <li>getSchema()�Ŏ擾�������R�[�h�X�L�[�}����`�������e�ƈ�v���Ă���</li>
     * </ul>
     */
	public void testGetSchema() {
		try {
			String schema = ":A,java.lang.String,,,\n:B,int,,,";
			RecordSchema rsm = RecordSchema.getInstance(schema);
			assertEquals(rsm.getSchema(), ":A,java.lang.String,,,\n:B,int,,,");
		} catch (PropertySchemaDefineException e) {
			e.printStackTrace();
			fail("��O����");
		}
	}

	/**
     * �v���p�e�B�����擾����e�X�g�B<p>
     * �����F
     * <ul>
     *   <li>�v���p�e�B���͂Q��</li>
     *   <li>�v���p�e�B�X�L�[�}�����N���X�w��Ȃ�</li>
     *   <li>�v���p�e�B�X�L�[�}��`���e�i�^�̂݁j</li>
     *   <li>���̓��e�Ŏw�肷�� <BR> ":A,java.lang.String,,," <BR> ":B,int,,,"</li>
     * </ul>
     * �m�F�F
     * <ul>
     *   <li>getPropertyName(0)�Ŏ擾�����v���p�e�B������`�������e�ƈ�v���Ă���</li>
     *   <li>getPropertyName(1)�Ŏ擾�����v���p�e�B������`�������e�ƈ�v���Ă���</li>
     * </ul>
     */
	public void testGetPropertyName1() {
		try {
			String schema = ":A,java.lang.String,,,\n:B,int,,,";
			RecordSchema rsm = RecordSchema.getInstance(schema);
			assertEquals(rsm.getPropertyName(0), "A");
			assertEquals(rsm.getPropertyName(1), "B");
		} catch (PropertySchemaDefineException e) {
			e.printStackTrace();
			fail("��O����");
		}
	}

	/**
     * �v���p�e�B�����擾����e�X�g�B<p>
     * �����F
     * <ul>
     *   <li>�v���p�e�B���͂Q��</li>
     *   <li>�v���p�e�B�X�L�[�}�����N���X�w��Ȃ�</li>
     *   <li>�v���p�e�B�X�L�[�}��`���e�i�^�̂݁j</li>
     *   <li>���̓��e�Ŏw�肷�� <BR> ":A,java.lang.String,,," <BR> ":B,int,,,"</li>
     *   <li>getPropertyName(index)�Ő������Ȃ��C���f�b�N�X���w��</li>
     * </ul>
     * �m�F�F
     * <ul>
     *   <li>getPropertyName(-1)�Ŏ擾�����v���p�e�B����null</li>
     *   <li>getPropertyName(2)�Ŏ擾�����v���p�e�B����null</li>
     * </ul>
     */
	public void testGetPropertyName2() {
		try {
			String schema = ":A,java.lang.String,,,\n:B,int,,,";
			RecordSchema rsm = RecordSchema.getInstance(schema);
			assertNull(rsm.getPropertyName(-1));
			assertNull(rsm.getPropertyName(2));
		} catch (PropertySchemaDefineException e) {
			e.printStackTrace();
			fail("��O����");
		}
	}

	/**
     * �w�肵���v���p�e�B���̃C���f�b�N�X���擾����e�X�g�B<p>
     * �����F
     * <ul>
     *   <li>�v���p�e�B���͂Q��</li>
     *   <li>�v���p�e�B�X�L�[�}�����N���X�w��Ȃ�</li>
     *   <li>�v���p�e�B�X�L�[�}��`���e�i�^�̂݁j</li>
     *   <li>���̓��e�Ŏw�肷�� <BR> ":A,java.lang.String" <BR> ":B,int"</li>
     * </ul>
     * �m�F�F
     * <ul>
     *   <li>getPropertyIndex("A")�Ŏ擾�����C���f�b�N�X����`�������e�ƈ�v���Ă���</li>
     *   <li>getPropertyIndex("B")�Ŏ擾�����C���f�b�N�X����`�������e�ƈ�v���Ă���</li>
     * </ul>
     */
	public void testGetPropertyIndex() {
		try {
			String schema = ":A,java.lang.String,,,\n:B,int,,,";
			RecordSchema rsm = RecordSchema.getInstance(schema);
			assertEquals(rsm.getPropertyIndex("A"), 0);
			assertEquals(rsm.getPropertyIndex("B"), 1);
		} catch (PropertySchemaDefineException e) {
			e.printStackTrace();
			fail("��O����");
		}
	}

	/**
     * �w�肳�ꂽ�C���f�b�N�X�̃v���p�e�B�X�L�[�}���擾����e�X�g�B<p>
     * �����F
     * <ul>
     *   <li>�v���p�e�B���͂Q��</li>
     *   <li>�v���p�e�B�X�L�[�}�����N���X�w��Ȃ�</li>
     *   <li>�v���p�e�B�X�L�[�}��`���e�i�^�̂݁j</li>
     *   <li>���̓��e�Ŏw�肷�� <BR> ":A,java.lang.String,,," <BR> ":B,int,,,"</li>
     * </ul>
     * �m�F�F
     * <ul>
     *   <li>getPropertySchema(0).getSchema()�Ŏ擾�����X�L�[�}����`�������e�ƈ�v���Ă���</li>
     *   <li>getPropertySchema(1).getSchema()�Ŏ擾�����X�L�[�}����`�������e�ƈ�v���Ă���</li>
     * </ul>
     */
	public void testGetPropertySchemaInt1() {
		try {
			String schema = ":A,java.lang.String,,,\n:B,int,,,";
			RecordSchema rsm = RecordSchema.getInstance(schema);
			assertEquals(rsm.getPropertySchema(0).getSchema(), "A,java.lang.String,,,");
			assertEquals(rsm.getPropertySchema(1).getSchema(), "B,int,,,");
		} catch (PropertySchemaDefineException e) {
			e.printStackTrace();
			fail("��O����");
		}
	}

	/**
     * �w�肳�ꂽ�C���f�b�N�X�̃v���p�e�B�X�L�[�}���擾����e�X�g�B<p>
     * �����F
     * <ul>
     *   <li>�v���p�e�B���͂Q��</li>
     *   <li>�v���p�e�B�X�L�[�}�����N���X�w��Ȃ�</li>
     *   <li>�v���p�e�B�X�L�[�}��`���e�i�^�̂݁j</li>
     *   <li>���̓��e�Ŏw�肷�� <BR> ":A,java.lang.String,,," <BR> ":B,int,,,"</li>
     *   <li>getPropertySchema(index)�Ő������Ȃ��C���f�b�N�X���w��</li>
     * </ul>
     * �m�F�F
     * <ul>
     *   <li>getPropertySchema(-1)�Ŏ擾�����X�L�[�}��null</li>
     *   <li>getPropertySchema(2)�Ŏ擾�����X�L�[�}��null</li>
     * </ul>
     */
	public void testGetPropertySchemaInt2() {
		try {
			String schema = ":A,java.lang.String,,,\n:B,int,,,";
			RecordSchema rsm = RecordSchema.getInstance(schema);
			assertNull(rsm.getPropertySchema(-1));
			assertNull(rsm.getPropertySchema(2));
		} catch (PropertySchemaDefineException e) {
			e.printStackTrace();
			fail("��O����");
		}
	}

	/**
     * �w�肵���v���p�e�B���̃v���p�e�B�X�L�[�}���擾����e�X�g�B<p>
     * �����F
     * <ul>
     *   <li>�v���p�e�B���͂Q��</li>
     *   <li>�v���p�e�B�X�L�[�}�����N���X�w��Ȃ�</li>
     *   <li>�v���p�e�B�X�L�[�}��`���e�i�^�̂݁j</li>
     *   <li>���̓��e�Ŏw�肷�� <BR> ":A,java.lang.String" <BR> ":B,int"</li>
     * </ul>
     * �m�F�F
     * <ul>
     *   <li>getPropertySchema("A").getSchema()�Ŏ擾�����v���p�e�B�X�L�[�}����`�������e�ƈ�v���Ă���</li>
     *   <li>getPropertySchema("B").getSchema()�Ŏ擾�����v���p�e�B�X�L�[�}����`�������e�ƈ�v���Ă���</li>
     * </ul>
     */
	public void testGetPropertySchemaString1() {
		try {
			String schema = ":A,java.lang.String,,,\n:B,int,,,";
			RecordSchema rsm = RecordSchema.getInstance(schema);
			assertEquals(rsm.getPropertySchema("A").getSchema(), "A,java.lang.String,,,");
			assertEquals(rsm.getPropertySchema("B").getSchema(), "B,int,,,");
		} catch (PropertySchemaDefineException e) {
			e.printStackTrace();
			fail("��O����");
		}
	}

	/**
     * �w�肵���v���p�e�B���̃v���p�e�B�X�L�[�}���擾����e�X�g�B<p>
     * �����F
     * <ul>
     *   <li>�v���p�e�B���͂Q��</li>
     *   <li>�v���p�e�B�X�L�[�}�����N���X�w��Ȃ�</li>
     *   <li>�v���p�e�B�X�L�[�}��`���e�i�^�̂݁j</li>
     *   <li>���̓��e�Ŏw�肷�� <BR> ":A,java.lang.String" <BR> ":B,int"</li>
     *   <li>null���w��</li>
     * </ul>
     * �m�F�F
     * <ul>
     *   <li>getPropertySchema(null������).getSchema()�Ŏ擾�����v���p�e�B�X�L�[�}��null</li>
     * </ul>
     */
	public void testGetPropertySchemaString2() {
		try {
			String schema = ":A,java.lang.String,,,\n:B,int,,,";
			RecordSchema rsm = RecordSchema.getInstance(schema);
			assertNull(rsm.getPropertySchema((String)null));
		} catch (PropertySchemaDefineException e) {
			e.printStackTrace();
			fail("��O����");
		}
	}

	/**
     * �v���p�e�B�X�L�[�}�z����擾����e�X�g�B<p>
     * �����F
     * <ul>
     *   <li>�v���p�e�B���͂Q��</li>
     *   <li>�v���p�e�B�X�L�[�}�����N���X�w��Ȃ�</li>
     *   <li>�v���p�e�B�X�L�[�}��`���e�i�^�̂݁j</li>
     *   <li>���̓��e�Ŏw�肷�� <BR> ":A,java.lang.String,,," <BR> ":B,int,,,"</li>
     * </ul>
     * �m�F�F
     * <ul>
     *   <li>getPropertySchemata()�Ŏ擾�����v���p�e�B�X�L�[�}�z��̗v�f����2��</li>
     *   <li>getPropertySchemata()�Ŏ擾�����v���p�e�B�X�L�[�}�z��̊e�v�f����擾����<BR>
     *   �X�L�[�}�����񂪒�`�������e�ƈ�v����</li>
     * </ul>
     */
	public void testGetPropertySchemata() {
		try {
			String schema = ":A,java.lang.String,,,\n:B,int,,,";
			RecordSchema rsm = RecordSchema.getInstance(schema);
			assertTrue(rsm.getPropertySchemata().length == 2);
			assertEquals(rsm.getPropertySchemata()[0].getSchema(), "A,java.lang.String,,,");
			assertEquals(rsm.getPropertySchemata()[1].getSchema(), "B,int,,,");
		} catch (PropertySchemaDefineException e) {
			e.printStackTrace();
			fail("��O����");
		}
	}

	/**
     * �v���p�e�B�̐����擾����e�X�g�B<p>
     * �����F
     * <ul>
     *   <li>�v���p�e�B���͂Q��</li>
     *   <li>�v���p�e�B�X�L�[�}�����N���X�w��Ȃ�</li>
     *   <li>�v���p�e�B�X�L�[�}��`���e�i�^�̂݁j</li>
     *   <li>���̓��e�Ŏw�肷�� <BR> ":A,java.lang.String,,," <BR> ":B,int,,,"</li>
     * </ul>
     * �m�F�F
     * <ul>
     *   <li>getPropertySize()�Ŏ擾�����v���p�e�B����2��</li>
     * </ul>
     */
	public void testGetPropertySize() {
		try {
			String schema = ":A,java.lang.String,,,\n:B,int,,,";
			RecordSchema rsm = RecordSchema.getInstance(schema);
			assertTrue(rsm.getPropertySize() == 2);
		} catch (PropertySchemaDefineException e) {
			e.printStackTrace();
			fail("��O����");
		}
	}


	/**
     * ���R�[�h�X�L�[�}��������擾����e�X�g�B<p>
     * �����F
     * <ul>
     *   <li>�v���p�e�B���͂Q��</li>
     *   <li>�v���p�e�B�X�L�[�}�����N���X�w��Ȃ�</li>
     *   <li>�v���p�e�B�X�L�[�}��`���e�i�^�̂݁j</li>
     *   <li>���̓��e�Ŏw�肷�� <BR> ":A,java.lang.String" <BR> ":B,int"</li>
     * </ul>
     * �m�F�F
     * <ul>
     *   <li>toString()�Ŏ擾����������<BR>
     *   "{name=A,type=java.lang.String,parseConverter=null,<BR>
     *   formatConverter=null,constrain=null};<BR>
     *   jp.ossc.nimbus.beans.dataset.DefaultPropertySchema<BR>
     *   {name=B,type=java.lang.Integer,parseConverter=null,<BR>
     *   formatConverter=null,constrain=null}}"�ƈ�v���邩</li>
     * </ul>
     */
	public void testToString() {
		try {
			String schema = ":A,java.lang.String,,,\n:B,int,,,";
			RecordSchema rsm = RecordSchema.getInstance(schema);
			assertEquals(rsm.toString(), "{jp.ossc.nimbus.beans.dataset.DefaultPropertySchema" +
					"{name=A,type=java.lang.String,parseConverter=null," +
					"formatConverter=null,constrain=null};" +
					"jp.ossc.nimbus.beans.dataset.DefaultPropertySchema" +
					"{name=B,type=int,parseConverter=null," +
					"formatConverter=null,constrain=null}}");
		} catch (PropertySchemaDefineException e) {
			e.printStackTrace();
			fail("��O����");
		}
	}

}
