package jp.ossc.nimbus.beans.dataset;

import junit.framework.TestCase;

public class RecordListPropertySchemaTest extends TestCase {

    public RecordListPropertySchemaTest(String arg0) {
        super(arg0);
    }
     public static void main(String[] args) {
         junit.textui.TestRunner.run(RecordListPropertySchemaTest.class); }
         

        /**
         * �X�L�[�}��ݒ肷��e�X�g�B
         * <p>
         * �����F
         * <ul>
         * <li>���̃X�L�[�}���w�肵��setSchema(String schema)�����s����</li>
         * <li>"A,recListName"</li>
         * </ul>
         * �m�F�F
         * <ul>
         * <li>�X�L�[�}������ɐݒ�ł���</li>
         * <li>��OPropertySchemaDefineException���������Ȃ����Ƃ��m�F</li>
         * <li>�ݒ肵���X�L�[�}��getSchema()�ŎQ�Ƃł���</li>
         * <li>name�t�B�[���h�Ƀv���p�e�B���ArecordListName�Ƀ��R�[�h���X�g�����ݒ肳���</li>
         * </ul>
         */
    public void testSetGetSchema() {
        try {
            RecordListPropertySchema rps = new RecordListPropertySchema();
            String schema = "A,recListName";
            rps.setSchema(schema);
            assertEquals(schema, rps.getSchema());
            assertEquals("A", rps.name);
            assertEquals("recListName", rps.recordListName);
            
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
     * <li>�������`��(�v���p�e�B���A���R�[�h���X�g��)�łȂ��̃X�L�[�}���w�肵��setSchema(String schema)�����s����</li>
     * </ul>
     * �m�F�F
     * <ul>
     * <li>��OPropertySchemaDefineException����������Ƃ��m�F<BR>
     * ���b�Z�[�W"Name and Schema must be specified."���Ԃ��Ă��邱�Ƃ��m�F</li>
     * </ul>
     */
    public void testSetGetSchemaInvalid() {
        try {
            RecordListPropertySchema rps = new RecordListPropertySchema();
            String schema = "A,recListName,test";
            rps.setSchema(schema);
            fail("��O���������Ȃ��̂Ńe�X�g���s");
            
            assertEquals(schema, rps.getSchema());
        } catch (PropertySchemaDefineException e) {
            assertEquals("A,recListName,test:The type is illegal.", e.getMessage());
        }
        try {
            RecordListPropertySchema rps = new RecordListPropertySchema();
            String schema = "A";
            rps.setSchema(schema);
            fail("��O���������Ȃ��̂Ńe�X�g���s");
            
            assertEquals(schema, rps.getSchema());
        } catch (PropertySchemaDefineException e) {
            assertEquals("Name and Schema must be specified.:null", e.getMessage());
        }
    }


    /**
     * �v���p�e�B�����擾����e�X�g�B
     * <p>
     * �����F
     * <ul>
     * <li>���̃X�L�[�}���w�肵��setSchema(String schema)�����s����</li>
     * <li>"A,recListName"</li>
     * <li> getName()�����s����</li>
     * </ul>
     * �m�F�F
     * <ul>
     * <li>�X�L�[�}�Ŏw�肵���v���p�e�B�����擾�ł���</li>
     * </ul>
     */
    public void testGetName() {
        try {
            RecordListPropertySchema rps = new RecordListPropertySchema();
            String schema = "A,recListName";
            rps.setSchema(schema);
            assertEquals("A", rps.getName());
            
        } catch (PropertySchemaDefineException e) {
            e.printStackTrace();
            fail("��O����");
        }
    }


    /**
     * �^���擾����e�X�g�B
     * <p>
     * �����F
     * <ul>
     * <li>���̃X�L�[�}���w�肵��setSchema(String schema)�����s����</li>
     * <li>"A,recListName"</li>
     * <li> getType()�����s����</li>
     * </ul>
     * �m�F�F
     * <ul>
     * <li>RecordList.class���擾�ł���</li>
     * </ul>
     */
    public void testGetType() {
        try {
            RecordListPropertySchema rps = new RecordListPropertySchema();
            String schema = "A,recListName";
            rps.setSchema(schema);
            assertEquals(RecordList.class, rps.getType());
            
        } catch (PropertySchemaDefineException e) {
            e.printStackTrace();
            fail("��O����");
        }
    }


    /**
     * �v���p�e�B�l(���R�[�h���X�g)��ݒ肷��e�X�g�B
     * <p>
     * �����F
     * <ul>
     * <li>���̃X�L�[�}���w�肵��setSchema(String schema)�����s����</li>
     * <li>"A,TestRecordList"</li>
     * <li> ��������RecordList���w�肵��set(Object val)�����s����</li>
     * <li> get(Object val)�����s����</li>
     * </ul>
     * �m�F�F
     * <ul>
     * <li>�w�肵���v���p�e�B�l���擾�ł���</li>
     * </ul>
     */
    public void testSetGet() {
        try {
            RecordListPropertySchema rps = new RecordListPropertySchema();
            String schema = "A,TestRecordList";
            rps.setSchema(schema);
            
            //RecordList�𐶐����Ēl�Ƃ��Đݒ肷��
            RecordList rlist = new RecordList("TestRecordList", ":A,java.lang.String\n:B,java.lang.String");
            rps.set(rlist);
            
            //get()�Ŏ擾���Č���
            assertEquals(rlist, rps.get(rlist));
            
        } catch (PropertySchemaDefineException e) {
            e.printStackTrace();
            fail("��O����");
        }
    }


    /**
     * Format�e�X�g�B
     * <p>
     * �����F
     * <ul>
     * <li>���̃X�L�[�}���w�肵��setSchema(String schema)�����s����</li>
     * <li>"A,recListName"</li>
     * <li> �K���Ȓl���w�肵��format(Object val) �����s����</li>
     * </ul>
     * �m�F�F
     * <ul>
     * <li>�w�肵���l���Ԃ����(�ϊ�����Ȃ�)</li>
     * </ul>
     */
    public void testFormat() {
        try {
            RecordListPropertySchema rps = new RecordListPropertySchema();
            String schema = "A,recListName";
            rps.setSchema(schema);

            assertEquals("test", rps.format("test"));
            
        } catch (PropertySchemaDefineException e) {
            e.printStackTrace();
            fail("��O����");
        }
    }


    /**
     * Parse�e�X�g�B
     * <p>
     * �����F
     * <ul>
     * <li>���̃X�L�[�}���w�肵��setSchema(String schema)�����s����</li>
     * <li>"A,recListName"</li>
     * <li> �K���Ȓl���w�肵��format(Object val) �����s����</li>
     * </ul>
     * �m�F�F
     * <ul>
     * <li>�w�肵���l���Ԃ����(�ϊ�����Ȃ�)</li>
     * </ul>
     */
    public void testParse() {
        try {
            RecordListPropertySchema rps = new RecordListPropertySchema();
            String schema = "A,recListName";
            rps.setSchema(schema);

            assertEquals("test", rps.parse("test"));
            
        } catch (PropertySchemaDefineException e) {
            e.printStackTrace();
            fail("��O����");
        }
    }


    /**
     * �l�X�g�������R�[�h���X�g�����擾����e�X�g�B
     * <p>
     * �����F
     * <ul>
     * <li>���̃X�L�[�}���w�肵��setSchema(String schema)�����s����</li>
     * <li>"A,recListName"</li>
     * <li> getRecordListName() �����s����</li>
     * </ul>
     * �m�F�F
     * <ul>
     * <li>�w�肵�����R�[�h���X�g��"recListName"���Ԃ����</li>
     * </ul>
     */
    public void testGetRecordListName() {
        try {
            RecordListPropertySchema rps = new RecordListPropertySchema();
            String schema = "A,recListName";
            rps.setSchema(schema);

            assertEquals("recListName", rps.getRecordListName());
            
        } catch (PropertySchemaDefineException e) {
            e.printStackTrace();
            fail("��O����");
        }
    }


    /**
     * �X�L�[�}�̕�����\�����擾����e�X�g�B
     * <p>
     * �����F
     * <ul>
     * <li>���̃X�L�[�}���w�肵��setSchema(String schema)�����s����</li>
     * <li>"A,recListName"</li>
     * <li> toString()�����s����</li>
     * </ul>
     * �m�F�F
     * <ul>
     * <li>������"jp.ossc.nimbus.beans.dataset.RecordListPropertySchema{name=A,recordListName=recListName}"���Ԃ����</li>
     * </ul>
     */
    public void testToString() {
        try {
            RecordListPropertySchema rps = new RecordListPropertySchema();
            String schema = "A,recListName";
            rps.setSchema(schema);

            assertEquals("jp.ossc.nimbus.beans.dataset.RecordListPropertySchema{name=A,recordListName=recListName,type=class jp.ossc.nimbus.beans.dataset.RecordList}"
                    , rps.toString());
            
        } catch (PropertySchemaDefineException e) {
            e.printStackTrace();
            fail("��O����");
        }
    }

}
