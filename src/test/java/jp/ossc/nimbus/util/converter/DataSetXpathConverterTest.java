/*
 * This software is distributed under following license based on modified BSD
 * style license.
 * ----------------------------------------------------------------------
 * 
 * Copyright 2008 The Nimbus Project. All rights reserved.
 * 
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 * 
 * 1. Redistributions of source code must retain the above copyright notice,
 *    this list of conditions and the following disclaimer. 
 * 2. Redistributions in binary form must reproduce the above copyright notice,
 *    this list of conditions and the following disclaimer in the documentation
 *    and/or other materials provided with the distribution.
 * 
 * THIS SOFTWARE IS PROVIDED BY THE NIMBUS PROJECT ``AS IS'' AND ANY EXPRESS
 * OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES
 * OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN
 * NO EVENT SHALL THE NIMBUS PROJECT OR CONTRIBUTORS BE LIABLE FOR ANY
 * DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF
 * THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 * 
 * The views and conclusions contained in the software and documentation are
 * those of the authors and should not be interpreted as representing official
 * policies, either expressed or implied, of the Nimbus Project.
 */
package jp.ossc.nimbus.util.converter;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import jp.ossc.nimbus.beans.dataset.DataSet;
import jp.ossc.nimbus.beans.dataset.Header;
import jp.ossc.nimbus.beans.dataset.Record;
import jp.ossc.nimbus.beans.dataset.RecordList;
import junit.framework.Assert;
import junit.framework.TestCase;

/**
 * {@link DataSetXpathConverter}�e�X�g�P�[�X�B
 * <p>
 *   �K�{���F
 *   <ul>
 *      <li>JDK 5.0 �ȍ~</li>
 *      <li>
 *          �܂��́AJDK 1.4 + �ȉ���endorsed���W���[���F
 *          <ul>
 *              <li>Xerces 2.9</li>
 *              <li>Xalan 2.7</li>
 *              <li>Xalan Serializer 2.7</li>
 *          </ul>
 *          ���̏ꍇ�A�e�X�g�����i��JVM�N���I�v�V������-Djava.endorsed.dirs��ݒ肷��K�v����
 *          -Djava.endorsed.dirs=lib/endorsed
 *      </li>
 *   </ul>
 * </p>
 * @author T.Okada
 */
public class DataSetXpathConverterTest extends TestCase {

    private static final String TEST_DATA_PATH = "jp/ossc/nimbus/util/converter/DataSetXpathConverterTest.xml";
    
    public void testConvertToObject() {
        DataSet inputDataSet = new DataSetXPathConverterTestDataSet();
        
        // �����f�[�^�ݒ�
        Header inputHeader = inputDataSet.getHeader();
        inputHeader.setProperty(DataSetXPathConverterTestDataSet.PROPERTY0, "PROP0");
        inputHeader.setParseProperty(DataSetXPathConverterTestDataSet.PROPERTY1, "PROP1");
        
        BindingStreamConverter converter = new DataSetXpathConverter();
        DataSetXPathConverterTestDataSet dataSet = (DataSetXPathConverterTestDataSet)converter.convertToObject(createTestData(), inputDataSet);
        
        Header header = dataSet.getHeader();
        RecordList recordList = dataSet.getRecordList();
        
        // �����f�[�^�擾
        Assert.assertEquals("PROP0", header.get(DataSetXPathConverterTestDataSet.PROPERTY0));
        // �����擾
        Assert.assertEquals("ATTR", header.get(DataSetXPathConverterTestDataSet.PROPERTY1));
        // �����l�ɂ��w��m�[�h�擾
        Assert.assertEquals("�e�L�X�g2-3", header.get(DataSetXPathConverterTestDataSet.PROPERTY2));
        // �m�[�h�C���f�b�N�X�ɂ��w��m�[�h�擾
        Assert.assertEquals("ATTR1", header.get(DataSetXPathConverterTestDataSet.PROPERTY3));
        
        // �q�m�[�h�擾
        for(int i=0; i<recordList.size(); i++) {
            Record record = (Record)recordList.get(i);
            Assert.assertEquals("�e�L�X�g"+(i+1)+"-1", record.get(DataSetXPathConverterTestDataSet.PROPERTY4));
            Assert.assertEquals("�e�L�X�g"+(i+1)+"-2", record.get(DataSetXPathConverterTestDataSet.PROPERTY5));
            Assert.assertEquals("�e�L�X�g"+(i+1)+"-3", record.get(DataSetXPathConverterTestDataSet.PROPERTY6));
        }
    }
    
    public void testConvertToObject_Error() {
        InputStream inputStream = createTestData();
        BindingStreamConverter converter = new DataSetXpathConverter();
        
        Exception exception = null;
        
        try {
            converter.convertToObject(null, new DataSetXPathConverterTestDataSet());
        } catch (IllegalArgumentException e) {
            exception = e;
        }
        Assert.assertNotNull(exception);
        
        try {
            converter.convertToObject(null, null);
        } catch (ConvertException e) {
            exception = e;
        }
        Assert.assertNotNull(exception);
        
        try {
            converter.convertToObject(inputStream, new Object());
        } catch (ConvertException e) {
            exception = e;
        }
        Assert.assertNotNull(exception);
    }
    
    private InputStream createTestData() {
        FileInputStream inputStream = null;
        try {
            inputStream = new FileInputStream(TEST_DATA_PATH);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }
        return inputStream;
    }

}
