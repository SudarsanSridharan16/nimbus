package jp.ossc.nimbus.service.http.httpclient;

import java.io.*;
import java.util.*;

import org.apache.commons.httpclient.HttpMethodBase;


import jp.ossc.nimbus.beans.dataset.DataSet;
import jp.ossc.nimbus.beans.dataset.Header;
import jp.ossc.nimbus.beans.dataset.PropertySchemaDefineException;
import jp.ossc.nimbus.core.ServiceManagerFactory;
import jp.ossc.nimbus.core.ServiceName;
import jp.ossc.nimbus.service.http.HttpClientFactory;
import jp.ossc.nimbus.service.http.HttpRequest;
import jp.ossc.nimbus.service.http.httpclient.HttpClientFactoryService.HttpClientImpl;
import jp.ossc.nimbus.util.converter.ConvertException;
import jp.ossc.nimbus.util.converter.DataSetXMLConverter;
import junit.framework.TestCase;

public class HttpResponseImplTest extends TestCase {

	public HttpResponseImplTest(String name) {
		super(name);
	}

	
	 public static void main(String[] args) {
	 junit.textui.TestRunner.run(HttpResponseImplTest.class); }
	 

	/**
	 * HttpResponseImpl�̊e�v���p�e�B��ݒ�A�擾����e�X�g�B
	 * <p>
	 * �����F
	 * <ul>
	 * <li>HttpResponseImpl�C���X�^���X�𐶐�����</li>
	 * <li>�egetter���\�b�h�����s</li>
	 * </ul>
	 * �m�F�F
	 * <ul>
	 * <li>����I���B�ݒ肵���l���������擾�ł��邱�Ƃ��m�F</li>
	 * </ul>
	 */
	public void testSetterGetter() {
		HttpResponseImpl res = new HttpResponseImpl();

		ServiceName name = new ServiceName("DataSetXMLConverter");
		res.setStreamConverterServiceName(name);

		DataSetXMLConverter xconv = new DataSetXMLConverter();
		res.setStreamConverter(xconv);
		
		res.setStatusCode(200);
		res.setStatusMessage("OK");

		assertEquals(xconv, res.getStreamConverter());
		assertEquals(name, res.getStreamConverterServiceName());
		assertEquals(200, res.getStatusCode());
		assertEquals("OK", res.getStatusMessage());
	}


	/**
	 * HTTP���\�b�h��ݒ肷��e�X�g�B
	 * <p>
	 * �����F
	 * <ul>
	 * <li>��`�t�@�C�������[�h���AHttpClientFactoryService�C���X�^���X�𐶐�����</li>
	 * <li>HttpRequestImpl#createRequest(�_���A�N�V������)�����s���AHttpRequest�𐶐�</li>
	 * <li>��������HttpRequest���w�肵�āAHttpClientImpl#createHttpMethod()�����s</li>
	 * <li>��������Method���w�肵�āAHttpResponseImpl#setHttpMethod()�����s</li>
	 * </ul>
	 * �m�F�F
	 * <ul>
	 * <li>����I�����邱�Ƃ��m�F</li>
	 * </ul>
	 */
	public void testSetHttpMethod() {
		try {
			if (!ServiceManagerFactory
					.loadManager("jp/ossc/nimbus/service/http/httpclient/service-clientTest.xml")) {
				System.exit(-1);
			}
			final HttpClientFactory factory = (HttpClientFactory) ServiceManagerFactory
					.getServiceObject("HttpClientFactory");

			HttpClientImpl client = (HttpClientImpl)factory.createHttpClient();

	        HttpRequest request = factory.createRequest("login");
			
			HttpRequestImpl rec = (HttpRequestImpl)request;
			HttpMethodBase method = rec.createHttpMethod();
			client.client.executeMethod(method);
			HttpResponseImpl res = new HttpResponseImpl();
			res.setHttpMethod(method);
			
		}
		catch (Exception e) {
			e.printStackTrace();
			fail("��O����");
		} finally {
			ServiceManagerFactory
					.unloadManager("jp/ossc/nimbus/service/http/httpclient/service-clientTest.xml");
		}

	}

	
	
	/**
	 * �w�b�_�[�����擾����e�X�g�B
	 * <p>
	 * �����F
	 * <ul>
	 * <li>��`�t�@�C�������[�h���AHttpClientFactoryService�C���X�^���X�𐶐�����</li>
	 * <li>HttpRequestImpl#createRequest(�_���A�N�V������)�����s���AHttpRequest�𐶐�</li>
	 * <li>HttpRequestImpl#executeRequest()�����s���AHttpResponse�𐶐�</li>
	 * <li>���X�|���X�̃w�b�_�[��Content-Type=text/html;charset=Shift_JIS���܂܂�Ă���</li>
	 * <li>HttpResponseImpl#getHeadermap()�����s����map�𐶐�</li>
	 * <li>HttpResponseImpl#getHeader(),getHeaders()�����s</li>
	 * </ul>
	 * �m�F�F
	 * <ul>
	 * <li>HttpResponseImpl#getHeader(),getHeaders()�����������ʂ��Ԃ���邱�Ƃ��m�F</li>
	 * </ul>
	 */
	public void testGetHeaderMap() {
		try {
			if (!ServiceManagerFactory
					.loadManager("jp/ossc/nimbus/service/http/httpclient/service-clientTest.xml")) {
				System.exit(-1);
			}
			final HttpClientFactory factory = (HttpClientFactory) ServiceManagerFactory
					.getServiceObject("HttpClientFactory");

			HttpClientImpl client = (HttpClientImpl) factory.createHttpClient();

			DataSet requestDs = new DataSet("Login");
	        requestDs.setHeaderSchema(
	            "UserInfo",
	            ":name,java.lang.String,,,\n"
	             + ":age,int,,,"
	        );
	        Header userInfo = requestDs.getHeader("UserInfo");
	        userInfo.setProperty("name", "hoge");
	        userInfo.setProperty("age", 25);
	        
	        HttpRequest request = factory.createRequest("login");
	        request.setObject(requestDs);

			HttpResponseImpl res = (HttpResponseImpl)client.executeRequest(request);
			res.getHeaderMap();
			assertEquals("text/html;charset=Shift_JIS",res.getHeader("Content-Type"));
			assertEquals("text/html;charset=Shift_JIS",res.getHeaders("Content-Type")[0]);
			
			
		}
		catch (Exception e) {
			e.printStackTrace();
			fail("��O����");
		} finally {
			ServiceManagerFactory
					.unloadManager("jp/ossc/nimbus/service/http/httpclient/service-clientTest.xml");
		}

	}
	
	/**
	 * �w�b�_�[�����擾����e�X�g�B
	 * <p>
	 * �����F
	 * <ul>
	 * <li>��`�t�@�C�������[�h���AHttpClientFactoryService�C���X�^���X�𐶐�����</li>
	 * <li>HttpRequestImpl#createRequest(�_���A�N�V������)�����s���AHttpRequest�𐶐�</li>
	 * <li>HttpRequestImpl#executeRequest()�����s���AHttpResponse�𐶐�</li>
	 * <li>���X�|���X�̃w�b�_�[��Content-Type=text/html;charset=Shift_JIS���܂܂�Ă���</li>
	 * <li>HttpResponseImpl#getHeader(),getHeaders()�����s</li>
	 * </ul>
	 * �m�F�F
	 * <ul>
	 * <li>HttpResponseImpl#getHeader(),getHeaders()�����������ʂ��Ԃ���邱�Ƃ��m�F</li>
	 * </ul>
	 */
	public void testGetHeaderMapNull() {
		try {
			if (!ServiceManagerFactory
					.loadManager("jp/ossc/nimbus/service/http/httpclient/service-clientTest.xml")) {
				System.exit(-1);
			}
			final HttpClientFactory factory = (HttpClientFactory) ServiceManagerFactory
					.getServiceObject("HttpClientFactory");

			HttpClientImpl client = (HttpClientImpl) factory.createHttpClient();

			DataSet requestDs = new DataSet("Login");
	        requestDs.setHeaderSchema(
	            "UserInfo",
	            ":name,java.lang.String,,,\n"
	             + ":age,int,,,"
	        );
	        Header userInfo = requestDs.getHeader("UserInfo");
	        userInfo.setProperty("name", "hoge");
	        userInfo.setProperty("age", 25);
	        
	        HttpRequest request = factory.createRequest("login");
	        request.setObject(requestDs);

			HttpResponseImpl res = (HttpResponseImpl)client.executeRequest(request);
			assertEquals("text/html;charset=Shift_JIS",res.getHeader("Content-Type"));
			assertEquals("text/html;charset=Shift_JIS",res.getHeaders("Content-Type")[0]);
			
			
		}
		catch (Exception e) {
			e.printStackTrace();
			fail("��O����");
		} finally {
			ServiceManagerFactory
					.unloadManager("jp/ossc/nimbus/service/http/httpclient/service-clientTest.xml");
		}

	}

	/**
	 * HttpResponse�̃w�b�_�[�����擾����e�X�g�B
	 * <p>
	 * �����F
	 * <ul>
	 * <li>���̒l���w�肵��HttpResponse#headerMap�𐶐�</li>
	 * <li>name=ContentType,value={"application/xml"}</li>
	 * <li>HttpResponse#getHeaderNameSet�����s</li>
	 * </ul>
	 * �m�F�F
	 * <ul>
	 * <li>�w�肵�����e���Ԃ�l�Ɋ܂܂�Ă��邱�Ƃ��m�F</li>
	 * </ul>
	 */
	public void testGetHeader() {
		HttpResponseImpl res = new HttpResponseImpl();
		res.headerMap = new HashMap();

		String[] vals = new String[] { "application/xml" };
		res.headerMap.put("Content-Type", vals);

		assertEquals("Content-Type", res.getHeaderNameSet().toArray()[0]
				.toString());
		assertEquals("application/xml", res.getHeader("Content-Type"));
		assertEquals("application/xml", res.getHeaders("Content-Type")[0]);
	}

	public void testGetInputStream() {
		try {
			HttpResponseImpl res = new HttpResponseImpl();
			res.setInputStream(System.in);
			assertEquals(System.in, res.getInputStream());
		} catch (IOException e) {
			e.printStackTrace();
			fail("��O����");
		}

	}


	/**
	 * HttpResponse�̃X�g���[����ݒ�A�擾����e�X�g�B
	 * <p>
	 * �����F
	 * <ul>
	 * <li>XML�X�g���[���f�[�^���f�[�^�Z�b�g�ɕϊ����Ď擾����</li>
	 * <li>���̓��e��DataSetXMLConverter�ŕϊ�����<BR>	
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
	 * <li>��L�̃X�g���[���f�[�^�ƃR���o�[�^���v���p�e�B�ɐݒ肵�āA<BR>
	 * HttpResponse#getObject�����s</li>
	 * </ul>
	 * �m�F�F
	 * <ul>
	 * <li>�������l���Ԃ��Ă��邱�Ƃ��m�F</li>
	 * </ul>
	 */
	public void testSetObject() {
		try {
			HttpResponseImpl res = new HttpResponseImpl();
			//CharacterEncoding��ݒ肵�Ă���
			res.headerMap = new HashMap();

			String[] vals = new String[] { "application/xml;charset=Shift_JIS" };
			res.headerMap.put("Content-Type", vals);

			String inxml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
			"<dataSet><schema><header name=\"TestHeader\">" +
			":A,java.util.Date," +
			"\"jp.ossc.nimbus.util.converter.DateFormatConverter{ConvertType=2;Format=\"yyyy-MM-DD\"}\"," +
					"\"jp.ossc.nimbus.util.converter.DateFormatConverter{ConvertType=1;Format=\"yyyy-MM-DD\"}\"," +
							"\"@value@ != null\"\n:B,java.lang.String,,," +
							"</header></schema><header name=\"TestHeader\">" +
							"<A>2008-01-28</A><B>TestValue</B></header></dataSet>";
			//���̓X�g���[���ƃR���o�[�^�[���v���p�e�B�ɃZ�b�g
			InputStream is = new ByteArrayInputStream(inxml.getBytes());
			res.setInputStream(is);
			DataSetXMLConverter conv = new DataSetXMLConverter(DataSetXMLConverter.XML_TO_DATASET);
			res.setStreamConverter(conv);
			
			//
			DataSet dataset = (DataSet)res.getObject();
			
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
	 * HttpResponse�̃X�g���[����ݒ�A�擾����e�X�g�B
	 * <p>
	 * �����F
	 * <ul>
	 * <li>XML�X�g���[���f�[�^���f�[�^�Z�b�g�ɕϊ����Ď擾����</li>
	 * <li>���̓��e��DataSetXMLConverter�ŕϊ�����<BR>	
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
	 * <li>��L�̃X�g���[���f�[�^�ƃR���o�[�^���v���p�e�B�ɐݒ肵�āA<BR>
	 * HttpResponse#getObject�����s</li>
	 * </ul>
	 * �m�F�F
	 * <ul>
	 * <li>�������l���Ԃ��Ă��邱�Ƃ��m�F</li>
	 * </ul>
	 */
	public void testSetObjectByServiceName() {
		try {
			//�R���o�[�^�T�[�r�X�̒�`�t�@�C�������[�h
			if (!ServiceManagerFactory
					.loadManager("jp/ossc/nimbus/service/http/httpclient/service-conv.xml")) {
				System.exit(-1);
			}
			HttpResponseImpl res = new HttpResponseImpl();
			//CharacterEncoding��ݒ肵�Ă���
			res.headerMap = new HashMap();

			String[] vals = new String[] { "application/xml;charset=Shift_JIS" };
			res.headerMap.put("Content-Type", vals);

			String inxml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
			"<dataSet><schema><header name=\"TestHeader\">" +
			":A,java.util.Date," +
			"\"jp.ossc.nimbus.util.converter.DateFormatConverter{ConvertType=2;Format=\"yyyy-MM-DD\"}\"," +
					"\"jp.ossc.nimbus.util.converter.DateFormatConverter{ConvertType=1;Format=\"yyyy-MM-DD\"}\"," +
							"\"@value@ != null\"\n:B,java.lang.String,,," +
							"</header></schema><header name=\"TestHeader\">" +
							"<A>2008-01-28</A><B>TestValue</B></header></dataSet>";
			//���̓X�g���[���ƃR���o�[�^�[�T�[�r�X�����v���p�e�B�ɃZ�b�g
			InputStream is = new ByteArrayInputStream(inxml.getBytes());
			res.setInputStream(is);
			ServiceName name = new ServiceName("DataSetXMLConverter");
			res.setStreamConverterServiceName(name);
			
			//
			DataSet dataset = (DataSet)res.getObject();
			
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
		} finally {
		ServiceManagerFactory
				.unloadManager("jp/ossc/nimbus/service/http/httpclient/service-conv.xml");
		}
	}


	/**
	 * �w�b�_�[��CharacterEncoding���擾����e�X�g�B
	 * <p>
	 * �����F
	 * <ul>
	 * <li>���̒l���w�肵��HttpResponse#headerMap�𐶐�</li>
	 * <li>name=ContentType,value={"application/xml"}(�����Z�b�g�w��Ȃ�)</li>
	 * <li>HttpResponse#getCharacterEncoding�����s</li>
	 * </ul>
	 * �m�F�F
	 * <ul>
	 * <li>"ISO8859_1"���Ԃ��Ă��邱�Ƃ��m�F</li>
	 * </ul>
	 */
	public void testGetCharacterEncodingNoCharSet() {
		HttpResponseImpl res = new HttpResponseImpl();
		res.headerMap = new HashMap();

		String[] vals = new String[] { "application/xml" };
		res.headerMap.put("Content-Type", vals);

		assertEquals("ISO8859_1", res.getCharacterEncoding());
	}

	/**
	 * �w�b�_�[��CharacterEncoding���擾����e�X�g�B
	 * <p>
	 * �����F
	 * <ul>
	 * <li>���̒l���w�肵��HttpResponse#headerMap�𐶐�</li>
	 * <li>name=ContentType,value={"application/xml;charset=Shift_JIS"}</li>
	 * <li>HttpResponse#getCharacterEncoding�����s</li>
	 * </ul>
	 * �m�F�F
	 * <ul>
	 * <li>"Shift_JIS"���Ԃ��Ă��邱�Ƃ��m�F</li>
	 * </ul>
	 */
	public void testGetCharacterEncoding() {
		HttpResponseImpl res = new HttpResponseImpl();
		res.headerMap = new HashMap();

		String[] vals = new String[] { "application/xml;charset=Shift_JIS" };
		res.headerMap.put("Content-Type", vals);

		assertEquals("Shift_JIS", res.getCharacterEncoding());
	}

}
