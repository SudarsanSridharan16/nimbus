// �p�b�P�[�W
// �C���|�[�g
package jp.ossc.nimbus.recset;

/**
 * �t�@�C������N���X<p>
 * �t�@�C���̃R�s�[�⃊�l�[���ƌ�����������s��
 * @version $Name:  $
 * @author H.Nakano
 * @since 1.0
 * @see 
 */
public class TestRecSet extends RecordSet {

    private static final long serialVersionUID = -393903869827407791L;
    /** �X�L�[�}��` */
    private static final String C_SCHEMA =
       "OPERATIONDATE,CHAR,8,3,0" + C_SEPARATOR
     + "GYOUMUCD,CHAR,3,3,0" + C_SEPARATOR
     + "MEMOSEQ,INT,2,3,0" + C_SEPARATOR
	 + "LINENO,INT,3,3,0" + C_SEPARATOR
	 + "MEMODATA,VARCHAR,100,3,0" + C_SEPARATOR
	 + "ROWVERSION,INT,9,1,0";
    /** �e�[�u���� */
    private static final String TABLE_NAME = "COMMON_MEMO_DATA";
	public TestRecSet() {
		/**
		 * �R���X�g���N�^�[
		 */
		super();
        super.initSchema(C_SCHEMA);
        super.setFromTable(TABLE_NAME);
        super.setOrderbyStr("MEMOSEQ,LINENO");
	}

}
