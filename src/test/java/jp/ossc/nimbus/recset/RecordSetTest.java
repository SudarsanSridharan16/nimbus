// �p�b�P�[�W
package jp.ossc.nimbus.recset;
//�C���|�[�g

import junit.framework.TestCase;

/**
 * �t�@�C������N���X<p>
 * �t�@�C���̃R�s�[�⃊�l�[���ƌ�����������s��
 * @version $Name:  $
 * @author H.Nakano
 * @since 1.0
 * @see 
 */
public class RecordSetTest extends TestCase {
	//���b�Z�[�W�t�@�C���p�X���P�w�肵���ꍇ
	public void testInsertRecord() throws Exception{
		TestRecSet tmp = new TestRecSet() ;
		RowData rd = tmp.createNewRecord() ;
		rd.setValue("OPERATIONDATE","20030303");
		rd.setValue("GYOUMUCD","111") ;
		rd.setValue("MEMOSEQ",1) ;
		rd.setValue("LINENO",1) ;
		rd.setValue("MEMODATA","aaaa") ;
		tmp.insertRecord(rd);
		rd = tmp.createNewRecord() ;
		rd.setValue("OPERATIONDATE","20030303");
		rd.setValue("GYOUMUCD","111") ;
		rd.setValue("MEMOSEQ",1) ;
		rd.setValue("LINENO",1) ;
		rd.setValue("MEMODATA","aaaa") ;
		tmp.insertRecord(rd);
	}

}
