package jp.ossc.nimbus.service.http.httpclient;

import java.io.*;
import java.net.*;

import org.apache.commons.httpclient.*;
import org.apache.commons.httpclient.auth.*;

import junit.framework.TestCase;
import jp.ossc.nimbus.beans.dataset.*;
import jp.ossc.nimbus.beans.dataset.Header;
import jp.ossc.nimbus.core.*;
import jp.ossc.nimbus.util.converter.*;
import jp.ossc.nimbus.service.http.*;
import jp.ossc.nimbus.service.http.httpclient.HttpClientFactoryService.HttpClientImpl;

//
/**
 * 
 * @author S.Teshima
 * @version 1.00 �쐬: 2008/01/28 - S.Teshima
 */

public class HttpClientFactoryServiceTest extends TestCase {

	public HttpClientFactoryServiceTest(String arg0) {
		super(arg0);
	}

	
	 public static void main(String[] args) {
	 junit.textui.TestRunner.run(HttpClientFactoryServiceTest.class); }
	 

	/**
	 * HttpClientFactoryService�̊e�v���p�e�B��ݒ�A�擾����e�X�g�B
	 * <p>
	 * �����F
	 * <ul>
	 * <li>HttpClientFactoryService�C���X�^���X�𐶐�����</li>
	 * <li>�egetter���\�b�h�����s</li>
	 * </ul>
	 * �m�F�F
	 * <ul>
	 * <li>����I���B�ݒ肵���l���������擾�ł��邱�Ƃ��m�F</li>
	 * </ul>
	 */
	public void testSetterGetter() {
		HttpClientFactoryService hcf = new HttpClientFactoryService();
		hcf.setConnectionTimeout(60);
		hcf.setLinger(70);
		hcf.setReceiveBufferSize(5000);
		hcf.setSendBufferSize(5500);
		hcf.setSoTimeout(80);
		hcf.setRequestContentType("application/x-www-form-urlencoded");
		hcf.setRequestCharacterEncoding("UTF-8");
		hcf.setHttpVersion("1.0");

		String[] values = { "gzip" };
		hcf.setRequestHeaders("Content-Encoding", values);

		hcf.setHttpClientParam("A", "a");
		hcf.setRequestDeflateLength(1000000);

		ServiceName name = new ServiceName("DataSetXMLConverter");
		hcf.setRequestStreamConverterServiceName(name);
		hcf.setResponseStreamConverterServiceName(name);

		ServiceName jname = new ServiceName("Journal");
		hcf.setJournalServiceName(jname);

		ServiceName sname = new ServiceName("Sequence");
		hcf.setSequenceServiceName(sname);

		ServiceName thname = new ServiceName("ThreadContext");
		hcf.setThreadContextServiceName(thname);

		ServiceName smname = new ServiceName("Semaphore");
		hcf.setSemaphoreServiceName(smname);

		DataSetXMLConverter xconv = new DataSetXMLConverter();
		hcf.setRequestStreamConverter(xconv);
		hcf.setResponseStreamConverter(xconv);

		assertEquals(60, hcf.getConnectionTimeout());
		assertEquals(70, hcf.getLinger());
		assertEquals(5000, hcf.getReceiveBufferSize());
		assertEquals(5500, hcf.getSendBufferSize());
		assertEquals(80, hcf.getSoTimeout());
		assertEquals("application/x-www-form-urlencoded", hcf
				.getRequestContentType());
		assertEquals("UTF-8", hcf.getRequestCharacterEncoding());
		assertEquals("1.0", hcf.getHttpVersion());
		assertEquals(values, hcf.getRequestHeaders("Content-Encoding"));
		assertEquals("a", hcf.getHttpClientParam("A"));
		assertEquals(1000000, hcf.getRequestDeflateLength());
		assertEquals(xconv, hcf.getRequestStreamConverter());
		assertEquals(xconv, hcf.getResponseStreamConverter());
		assertEquals(name, hcf.getRequestStreamConverterServiceName());
		assertEquals(name, hcf.getResponseStreamConverterServiceName());
		assertEquals(jname, hcf.getJournalServiceName());
		assertEquals(sname, hcf.getSequenceServiceName());
		assertEquals(thname, hcf.getThreadContextServiceName());
		assertEquals(smname, hcf.getSemaphoreServiceName());
	}

	/**
	 * �T�[�r�X�J�n�A�I������e�X�g�B
	 * <p>
	 * �����F
	 * <ul>
	 * <li>Sequence�AJournal�AThreadContext�ASemaphore�̊e�T�[�r�X���`������`�t�@�C��<BR>
	 * �����[�h����HttpClientFactory�T�[�r�X���J�n����</li>
	 * <li>HttpClientFactoryService#stopService()�����s</li>
	 * </ul>
	 * �m�F�F
	 * <ul>
	 * <li>����I���B</li>
	 * </ul>
	 */
	public void testStartService() {
		try {
			if (!ServiceManagerFactory
					.loadManager("jp/ossc/nimbus/service/http/httpclient/service-client.xml")) {
				System.exit(-1);
			}
			final HttpClientFactory factory = (HttpClientFactory) ServiceManagerFactory
					.getServiceObject("HttpClientFactory");
			HttpClientFactoryService hcf = (HttpClientFactoryService) factory;
			hcf.stopService();

		} catch (Exception e) {
			e.printStackTrace();
			fail("��O����");
		} finally {
			ServiceManagerFactory
					.unloadManager("jp/ossc/nimbus/service/http/httpclient/service-client.xml");
		}
	}

	/**
	 * Proxy���(�z�X�g���F�|�[�g�ԍ�)��ݒ�A�擾����e�X�g�B
	 * <p>
	 * �����F
	 * <ul>
	 * <li>HttpClientFactoryService�C���X�^���X�𐶐�����</li>
	 * <li>"localhost:80"l���w�肵��HttpClientFactoryService#setProxy()�����s</li>
	 * </ul>
	 * �m�F�F
	 * <ul>
	 * <li>����I�����邱�Ƃ��m�F</li>
	 * </ul>
	 */
	public void testSetProxy() {
		HttpClientFactoryService hcf = new HttpClientFactoryService();
		hcf.setProxy("localhost:80");
		assertEquals("localhost:80", hcf.getProxy());
	}

	/**
	 * Proxy���(�z�X�g���F�|�[�g�ԍ�)��ݒ�A�擾����e�X�g�B
	 * <p>
	 * �����F
	 * <ul>
	 * <li>HttpClientFactoryService�C���X�^���X�𐶐�����</li>
	 * <li>null���w�肵��HttpClientFactoryService#setProxy()�����s</li>
	 * </ul>
	 * �m�F�F
	 * <ul>
	 * <li>����I���BgetProxy()��null���Ԃ��Ă��邱�Ƃ��m�F</li>
	 * </ul>
	 */
	public void testSetProxyNull() {
		HttpClientFactoryService hcf = new HttpClientFactoryService();
		hcf.setProxy(null);
		assertNull(hcf.getProxy());
	}

	/**
	 * Proxy���(�z�X�g���F�|�[�g�ԍ�)��ݒ�A�擾����e�X�g�B
	 * <p>
	 * �����F
	 * <ul>
	 * <li>HttpClientFactoryService�C���X�^���X�𐶐�����</li>
	 * <li>":80"(�z�X�g�����w�肵�Ȃ��s���Ȓl)���w�肵��HttpClientFactoryService#setProxy()�����s</li>
	 * </ul>
	 * �m�F�F
	 * <ul>
	 * <li>��OIllegalArgumentException���������邱�Ƃ��m�F</li>
	 * <li>��O���b�Z�[�W��"Illegal proxy : :80"���Ԃ���邱�Ƃ��m�F</li>
	 * </ul>
	 */
	public void testSetProxyInvalid1() {
		try {
			HttpClientFactoryService hcf = new HttpClientFactoryService();
			hcf.setProxy(":80");
			fail("��O���������Ȃ����߃e�X�g���s ");
		} catch (IllegalArgumentException e) {
			assertEquals("Illegal proxy : :80", e.getMessage());
		}
	}

	/**
	 * Proxy���(�z�X�g���F�|�[�g�ԍ�)��ݒ�A�擾����e�X�g�B
	 * <p>
	 * �����F
	 * <ul>
	 * <li>HttpClientFactoryService�C���X�^���X�𐶐�����</li>
	 * <li>"localhost:NoInt"(�s���ȃ|�[�g�ԍ�)���w�肵��HttpClientFactoryService#setProxy()�����s</li>
	 * </ul>
	 * �m�F�F
	 * <ul>
	 * <li>��OIllegalArgumentException���������邱�Ƃ��m�F</li>
	 * <li>��O���b�Z�[�W��"Illegal proxy port : localhost:NoInt"���Ԃ���邱�Ƃ��m�F</li>
	 * </ul>
	 */
	public void testSetProxyInvalid2() {
		try {
			HttpClientFactoryService hcf = new HttpClientFactoryService();
			hcf.setProxy("localhost:NoInt");
			fail("��O���������Ȃ����߃e�X�g���s ");
		} catch (IllegalArgumentException e) {
			assertEquals("Illegal proxy port : localhost:NoInt", e.getMessage());
		}
	}

	/**
	 * ���[�J���A�h���X���(IP�A�h���X)��ݒ�A�擾����e�X�g�B
	 * <p>
	 * �����F
	 * <ul>
	 * <li>HttpClientFactoryService�C���X�^���X�𐶐�����</li>
	 * <li>"127.0.0.1"���w�肵��HttpClientFactoryService#setLocalAddress()�����s</li>
	 * </ul>
	 * �m�F�F
	 * <ul>
	 * <li>����I���BgetLocalAddress()��null���Ԃ��Ă��邱�Ƃ��m�F</li>
	 * </ul>
	 */
	public void testSetLocalAddress() {
		try {
			HttpClientFactoryService hcf = new HttpClientFactoryService();
			hcf.setLocalAddress("127.0.0.1");
			assertEquals("127.0.0.1", hcf.getLocalAddress());
		} catch (UnknownHostException e) {
			e.printStackTrace();
			fail("��O����");
		}
	}

	/**
	 * ���[�J���A�h���X���(IP�A�h���X)��ݒ�A�擾����e�X�g�B
	 * <p>
	 * �����F
	 * <ul>
	 * <li>HttpClientFactoryService�C���X�^���X�𐶐�����</li>
	 * <li>null���w�肵��HttpClientFactoryService#setLocalAddress()�����s</li>
	 * </ul>
	 * �m�F�F
	 * <ul>
	 * <li>����I���BgetLocalAddress()��null���Ԃ��Ă��邱�Ƃ��m�F</li>
	 * </ul>
	 */
	public void testSetLocalAddressNull() {
		try {
			HttpClientFactoryService hcf = new HttpClientFactoryService();
			hcf.setLocalAddress(null);
			assertNull(hcf.getLocalAddress());
		} catch (UnknownHostException e) {
			e.printStackTrace();
			fail("��O����");
		}
	}

//	/**
//	 * ���[�J���A�h���X���(IP�A�h���X)��ݒ�A�擾����e�X�g�B
//	 * <p>
//	 * �����F
//	 * <ul>
//	 * <li>HttpClientFactoryService�C���X�^���X�𐶐�����</li>
//	 * <li>�s���ȃA�h���X���w�肵��HttpClientFactoryService#setLocalAddress()�����s</li>
//	 * </ul>
//	 * �m�F�F
//	 * <ul>
//	 * <li>��OUnknownHostException���������邱�Ƃ��m�F</li>
//	 * </ul>
//	 */
//	public void testSetLocalAddressInvalid() {
//		try {
//			HttpClientFactoryService hcf = new HttpClientFactoryService();
//			hcf.setLocalAddress("1:1:1:1:1:1");
//			fail("��O���������Ȃ����߃e�X�g���s ");
//		} catch (UnknownHostException e) {
//		}
//	}

	/**
	 * �w�肳�ꂽ�_���A�N�V�������ɊY������HTTP���N�G�X�g��ݒ�A�擾����e�X�g�B
	 * <p>
	 * �����F
	 * <ul>
	 * <li>HttpClientFactoryService�C���X�^���X�𐶐�����</li>
	 * <li>�A�N�V������"login"�AHttpRequestImpl�I�u�W�F�N�g���w�肵�Ĉȉ������s</li>
	 * <li>HttpClientFactoryService#getRequest(String action)�����s</li>
	 * <li>HttpClientFactoryService#setRequest(String action, HttpRequestImpl
	 * request)�����s</li>
	 * <li>HttpClientFactoryService#getRequest(String action)�����s</li>
	 * </ul>
	 * �m�F�F
	 * <ul>
	 * <li>�P��ڂ�getRequest(String action)��null���Ԃ��Ă��邱�Ƃ��m�F</li>
	 * <li>�Q��ڂ�getRequest(String action)�Ŏw�肵��HttpRequestImpl�I�u�W�F�N�g���Ԃ��Ă��邱�Ƃ��m�F</li>
	 * </ul>
	 */
	public void testSetRequest() {
		HttpClientFactoryService hcf = new HttpClientFactoryService();
		String action = "login";
		assertNull(hcf.getRequest(action));

		HttpRequestImpl request = new PostHttpRequestImpl();
		hcf.setRequest(action, request);
		assertEquals(request, hcf.getRequest(action));
	}

	/**
	 * �w�肳�ꂽ�_���A�N�V�������ɊY������HTTP���X�|���X��ݒ�A�擾����e�X�g�B
	 * <p>
	 * �����F
	 * <ul>
	 * <li>HttpClientFactoryService�C���X�^���X�𐶐�����</li>
	 * <li>�A�N�V������"login"�AHttpResponseImpl�I�u�W�F�N�g���w�肵�Ĉȉ������s</li>
	 * <li>HttpClientFactoryService#getResponse(String action)�����s</li>
	 * <li>HttpClientFactoryService#setResponse(String action, HttpResponseImpl
	 * Response)�����s</li>
	 * <li>HttpClientFactoryService#getResponse(String action)�����s</li>
	 * </ul>
	 * �m�F�F
	 * <ul>
	 * <li>�P��ڂ�getResponse(String action)��null���Ԃ��Ă��邱�Ƃ��m�F</li>
	 * <li>�Q��ڂ�getResponse(String action)�Ŏw�肵��HttpResponseImpl�I�u�W�F�N�g���Ԃ��Ă��邱�Ƃ��m�F</li>
	 * </ul>
	 */
	public void testSetResponse() {
		HttpClientFactoryService hcf = new HttpClientFactoryService();
		String action = "login";
		assertNull(hcf.getResponse(action));

		HttpResponseImpl response = new HttpResponseImpl();
		hcf.setResponse(action, response);
		assertEquals(response, hcf.getResponse(action));
	}

	/**
	 * �F�؏���ݒ肷���ݒ�A�擾����e�X�g�B
	 * <p>
	 * �����F
	 * <ul>
	 * <li>HttpClientFactoryService�C���X�^���X�𐶐�����</li>
	 * <li>�F�؃X�R�[�v�A�F�؏��̊e�I�u�W�F�N�g���w�肵�Ĉȉ������s</li>
	 * <li>HttpClientFactoryService#setCredentials(AuthScope authscope,
	 * Credentials credentials)�����s</li>
	 * </ul>
	 * �m�F�F
	 * <ul>
	 * <li>credentialsMap�Ɏw�肵���l���ݒ肳��Ă��邱�Ƃ��m�F</li>
	 * </ul>
	 */
	public void testSetCredentials() {
		HttpClientFactoryService hcf = new HttpClientFactoryService();
		AuthScope authscope = new AuthScope("localhost", 80);
		Credentials credentials = new UsernamePasswordCredentials("hoge",
				"hoge");

		hcf.setCredentials(authscope, credentials);
		assertEquals(credentials, hcf.credentialsMap.get(authscope));
	}

	/**
	 * �v���L�V�F�؏���ݒ肷���ݒ�A�擾����e�X�g�B
	 * <p>
	 * �����F
	 * <ul>
	 * <li>HttpClientFactoryService�C���X�^���X�𐶐�����</li>
	 * <li>�F�؃X�R�[�v�A�F�؏��̊e�I�u�W�F�N�g���w�肵�Ĉȉ������s</li>
	 * <li>HttpClientFactoryService#setProxyCredentials(AuthScope authscope,
	 * Credentials credentials)�����s</li>
	 * </ul>
	 * �m�F�F
	 * <ul>
	 * <li>proxyCredentialsMap�Ɏw�肵���l���ݒ肳��Ă��邱�Ƃ��m�F</li>
	 * </ul>
	 */
	public void testSetProxyCredentials() {
		HttpClientFactoryService hcf = new HttpClientFactoryService();
		AuthScope authscope = new AuthScope("localhost", 80);
		Credentials credentials = new UsernamePasswordCredentials("hoge",
				"hoge");

		hcf.setProxyCredentials(authscope, credentials);
		assertEquals(credentials, hcf.proxyCredentialsMap.get(authscope));
	}

	/**
	 * �w�肳�ꂽ�_���A�N�V�������ɊY������HTTP���N�G�X�g�𔭍s����e�X�g�B
	 * <p>
	 * �����F
	 * <ul>
	 * <li>���̓��e�̒�`�t�@�C�������[�h���AHttpClientFactoryService�C���X�^���X�𐶐�����</li>
	 * <li>HttpVersion�F1.1</li>
	 * <li>RequestDeflateLength�F1000000</li>
	 * <li>RequestContentType�Fapplication/xml</li>
	 * <li>RequestCharacterEncoding�FUTF-8</li>
	 * <li>RequestHeaders(Accept-Encoding)�Fgzip</li>
	 * <li>RequestHeaders(Content-Encoding)�Fgzip</li>
	 * <li>RequestStreamConverterServiceName�F#DateSetXMLConverter</li>
	 * <li>HttpClientFactoryService#createRequest(String action)�����s</li>
	 * </ul>
	 * �m�F�F
	 * <ul>
	 * <li>HttpRequest�I�u�W�F�N�g�ɂɎw�肵���l���ݒ肳��Ă��邱�Ƃ��m�F</li>
	 * </ul>
	 */
	public void testCreateRequest() {
		try {
			if (!ServiceManagerFactory
					.loadManager("jp/ossc/nimbus/service/http/httpclient/service-client1.xml")) {
				System.exit(-1);
			}
			final HttpClientFactory factory = (HttpClientFactory) ServiceManagerFactory
					.getServiceObject("HttpClientFactory");
			HttpRequestImpl request = (HttpRequestImpl) factory
					.createRequest("help");

			assertEquals("1.1", request.getHttpVersion());
			assertEquals(1000000, request.getDeflateLength());
			assertEquals("application/xml", request.getContentType());
			assertEquals("UTF-8", request.getCharacterEncoding());
			String[] hparam = (String[]) request.headerMap
					.get("Accept-Encoding");
			assertEquals("gzip", hparam[0]);
			hparam = (String[]) request.headerMap.get("Content-Encoding");
			assertEquals("gzip", hparam[0]);
			assertEquals("DataSetXMLConverter", request
					.getStreamConverterServiceName().getServiceName());

		} catch (HttpRequestCreateException e) {
			e.printStackTrace();
			fail("��O����");
		}finally{
			ServiceManagerFactory
					.unloadManager("jp/ossc/nimbus/service/http/httpclient/service-client1.xml");
		}

	}

	/**
	 * �w�肳�ꂽ�_���A�N�V�������ɊY������HTTP���N�G�X�g�𐶐�����e�X�g�B
	 * <p>
	 * �����F
	 * <ul>
	 * <li>HttpClientFactoryService�C���X�^���X�𐶐�����</li>
	 * <li>�s���ȃA�N�V���������w�肵�Ĉȉ������s</li>
	 * <li>HttpClientFactoryService#createRequest(String action)�����s</li>
	 * </ul>
	 * �m�F�F
	 * <ul>
	 * <li>��OHttpRequestCreateException���������邱�Ƃ��m�F</li>
	 * <li>��O���b�Z�[�W��"No action."���Ԃ���邱�Ƃ��m�F</li>
	 * </ul>
	 */
	public void testCreateRequestInvalid() {
		try {
			HttpClientFactoryService hcf = new HttpClientFactoryService();
			hcf.createRequest("InvalidAction");
			fail("��O���������Ȃ����߃e�X�g���s ");
		} catch (HttpRequestCreateException e) {
			assertEquals("No action.", e.getMessage());
		}
	}

	/**
	 * �w�肳�ꂽ�_���A�N�V�������ɊY������HttpClient�𐶐�����e�X�g�B
	 * <p>
	 * �����F
	 * <ul>
	 * <li>���̓��e�̒�`�t�@�C�������[�h���AHttpClientFactoryService�C���X�^���X�𐶐�����</li>
	 * <li>LocalAddress�F127.0.0.1</li>
	 * <li>Proxy�Flocalhost:8280</li>
	 * <li>HttpVersion�F1.1</li>
	 * <li>ConnectionTimeout�F500</li>
	 * <li>SoTimeout�F1000</li>
	 * <li>Linger�F500</li>
	 * <li>RequestDeflateLength�F1000000</li>
	 * <li>RequestContentType�Fapplication/xml</li>
	 * <li>RequestCharacterEncoding�FUTF-8</li>
	 * <li>RequestHeaders(Accept-Encoding)�Fgzip</li>
	 * <li>RequestHeaders(Content-Encoding)�Fgzip</li>
	 * <li>RequestStreamConverterServiceName�F#DateSetXMLConverter</li>
	 * <li>HttpClientParam(ConnectionTimeout)�F500</li>
	 * <li>HttpClientFactoryService#createHttpClient()�����s</li>
	 * </ul>
	 * �m�F�F
	 * <ul>
	 * <li>HttpClientt�I�u�W�F�N�g�ɂɎw�肵���l���ݒ肳��Ă��邱�Ƃ��m�F</li>
	 * </ul>
	 */
	public void testCreateHttpClient() {
		try {
			if (!ServiceManagerFactory
					.loadManager("jp/ossc/nimbus/service/http/httpclient/service-client1.xml")) {
				System.exit(-1);
			}
			final HttpClientFactory factory = (HttpClientFactory) ServiceManagerFactory
					.getServiceObject("HttpClientFactory");
			HttpClientImpl client = (HttpClientImpl) factory.createHttpClient();

			assertEquals("localhost", client.client.getHostConfiguration()
					.getProxyHost());
			assertEquals(8280, client.client.getHostConfiguration()
					.getProxyPort());
			assertEquals("127.0.0.1", client.client.getHostConfiguration()
					.getLocalAddress().getHostAddress());
			assertEquals(500, client.client.getHttpConnectionManager()
					.getParams().getConnectionTimeout());
			assertEquals(1000, client.client.getHttpConnectionManager()
					.getParams().getSoTimeout());
			assertEquals(500, client.client.getHttpConnectionManager()
					.getParams().getLinger());
			assertEquals(5000, client.client.getHttpConnectionManager()
					.getParams().getReceiveBufferSize());
			assertEquals(5000, client.client.getHttpConnectionManager()
					.getParams().getSendBufferSize());

		} catch (HttpRequestCreateException e) {
			e.printStackTrace();
			fail("��O����");
		} finally {
			ServiceManagerFactory
					.unloadManager("jp/ossc/nimbus/service/http/httpclient/service-client1.xml");
		}
	}

	/**
	 * �w�肳�ꂽ�_���A�N�V�������ɊY������HttpClient�𐶐����A�N�b�L�[����ݒ�A�擾����e�X�g�B
	 * <p>
	 * �����F
	 * <ul>
	 * <li>���̓��e�̒�`�t�@�C�������[�h���AHttpClientFactoryService�C���X�^���X�𐶐�����</li>
	 * <li>HttpClientFactoryService#createHttpClient()�����s</li>
	 * <li>���̒l���w�肵�āAHttpClientImpl#ddCookie(javax.servlet.http.Cookie
	 * cookie)�����s</li>
	 * <li>���̒l���w�肵�ăN�b�L�[�I�u�W�F�N�g�𐶐����AHttpClientImpl#ddCookie(javax.servlet.http.Cookie
	 * cookie)�����s</li>
	 * <li>name:"TestCookie" value:"TestValue"</li>
	 * </ul>
	 * �m�F�F
	 * <ul>
	 * <li>HttpClientt�I�u�W�F�N�g�ɂɎw�肵���l���ݒ肳��Ă��邱�Ƃ��m�F</li>
	 * </ul>
	 */
	public void testCreateHttpClientCookie() {
		try {
			HttpClientFactoryService factory = new HttpClientFactoryService();
			HttpClientImpl client = (HttpClientImpl) factory.createHttpClient();

			javax.servlet.http.Cookie cookie = new javax.servlet.http.Cookie(
					"TestCookie", "TestValue");
			cookie.setDomain("localhost");
			cookie.setMaxAge(1000);
			client.addCookie(cookie);
			javax.servlet.http.Cookie[] result = client.getCookies();

			assertEquals("TestCookie", result[0].getName());
			assertEquals("TestValue", result[0].getValue());
		} catch (HttpRequestCreateException e) {
			e.printStackTrace();
			fail("��O����");
		} catch (Exception e) {
			e.printStackTrace();
			fail("��O����");
		}
	}

	/**
	 * �w�肳�ꂽ�_���A�N�V�������ɊY�����郊�N�G�X�g�𔭍s����e�X�g�B
	 * <p>
	 * �����F
	 * <ul>
	 * <li>���̓��e�̒�`�t�@�C�������[�h���AHttpClientFactoryService�C���X�^���X�𐶐�����</li>
	 * <li>RequestContentType�Fapplication/xml</li>
	 * <li>RequestCharacterEncoding�FShift_JIS</li>
	 * <li>RequestStreamConverterServiceName�F#DataSetXMLConverter</li>
	 * <li>ResponseStreamConverterServiceName�F#ResponseStreamConverter</li>
	 * <li>ResponseHeaders�FContentType=application/xml</li>
	 * <li>Proxy�F#localhost:8280</li>
	 * <li>�_���A�N�V������"login"�̃��N�G�X�g�����`</li>
	 * <li>HttpClientFactoryService#createHttpClient()�����s���AHttpClient�𐶐�</li>
	 * <li>HttpRequestImpl#createRequest(�_���A�N�V������)�����s���AHttpRequest�𐶐�</li>
	 * <li>���M����f�[�^�Z�b�g�����̓��e�Ő������AHttpRequest#setObject()�ŃZ�b�g<BR>
	 * �X�L�[�}�F:name,java.lang.String,,,\n:age,int,,,<BR>
	 * �l�@�@�@�Fname=hoge,age=25 </li>
	 * <li>��������HttpRequest���w�肵�āAHttpClientImpl#executeRequest(request)�����s</li>
	 * </ul>
	 * �m�F�F
	 * <ul>
	 * <li>���M��Ŏw�肵�����̒l��HTTP���N�G�X�g�ɐ������ݒ肳��Ă��邱�Ƃ��m�F</li>
	 * </ul>
	 */
	public void testExecuteRequestWithDataSet() {
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

			client.executeRequest(request);
			
			/*Proxy�e�X�g�p�v���O����(jp.ossc.nimbus.service.http.proxy.TestHttpProcessService)
			 * �̏o�̓t�@�C���̓��e���m�F���AHTTP���N�G�X�g�f�[�^������
			 */
			
			BufferedReader br = new BufferedReader(
					new FileReader("target/temp/jp/ossc/nimbus/service/http/httpclient/help_output.txt"));
			
			String s;
			StringBuffer sb = new StringBuffer();
			//Request�w�b�_�̌���
			while((s = br.readLine()) != null){
				if(s.startsWith("Content-Type:")){
					assertTrue(s.endsWith("application/xml;charset=Shift_JIS"));
				}
				sb.append(s);				
			}
			br.close();
			//DataSet���e�̌���
			assertTrue(sb.toString().endsWith(
					"<dataSet name=\"Login\"><schema>" +
					"<header name=\"UserInfo\">:name,java.lang.String,,,:age,int,,,</header>" +
					"</schema><header name=\"UserInfo\">" +
					"<name>hoge</name><age>25</age></header></dataSet>"));

		} catch (HttpRequestCreateException e) {
			e.printStackTrace();
			fail("��O����");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			fail("��O����");
		} catch (IOException e) {
			e.printStackTrace();
			fail("��O����");
		} finally {
			ServiceManagerFactory
					.unloadManager("jp/ossc/nimbus/service/http/httpclient/service-clientTest.xml");
		}
	}

	/**
	 * �w�肳�ꂽ�_���A�N�V�������ɊY�����郊�N�G�X�g�𔭍s����e�X�g�B
	 * <p>
	 * �����F
	 * <ul>
	 * <li>���̓��e�̒�`�t�@�C�������[�h���AHttpClientFactoryService�C���X�^���X�𐶐�����</li>
	 * <li>RequestContentType�Fapplication/xml</li>
	 * <li>RequestCharacterEncoding�FShift_JIS</li>
	 * <li>RequestStreamConverterServiceName�F#DataSetXMLConverter</li>
	 * <li>ResponseStreamConverterServiceName�F#ResponseStreamConverter</li>
	 * <li>Proxy�F#localhost:8280</li>
	 * <li>���̃p�����[�^��`�ƕ����Ę_���A�N�V������"login"�̃��N�G�X�g�����`<BR>
	 * sectionCode=22,account=05961,password=05961</li>
	 * <li>HttpClientFactoryService#createHttpClient()�����s���AHttpClient�𐶐�</li>
	 * <li>HttpRequestImpl#createRequest(�_���A�N�V������)�����s���AHttpRequest�𐶐�</li>
	 * <li>��������HttpRequest���w�肵�āAHttpClientImpl#executeRequest(request)�����s</li>
	 * </ul>
	 * �m�F�F
	 * <ul>
	 * <li>���M��Ŏw�肵�����̒l��HTTP���N�G�X�g�ɐ������ݒ肳��Ă��邱�Ƃ��m�F</li>
	 * <li>���X�|���X�̓��e���������ݒ肳��Ă��邱�Ƃ��m�F</li>
	 * </ul>
	 */
	public void testExecuteRequestWithoutData() {
		try {
			if (!ServiceManagerFactory
					.loadManager("jp/ossc/nimbus/service/http/httpclient/service-clientTest2.xml")) {
				System.exit(-1);
			}
			final HttpClientFactory factory = (HttpClientFactory) ServiceManagerFactory
					.getServiceObject("HttpClientFactory");
			HttpClientImpl client = (HttpClientImpl) factory.createHttpClient();

	        
	        HttpRequest request = factory.createRequest("login");

	        HttpResponse res = client.executeRequest(request);
			//���X�|���X���e�̊m�F
			assertEquals(200, res.getStatusCode());
			assertEquals("testdata", res.getObject());
			
			/*Proxy�e�X�g�p�v���O����(jp.ossc.nimbus.service.http.proxy.TestHttpProcessService)
			 * �̏o�̓t�@�C���̓��e���m�F���AHTTP���N�G�X�g�f�[�^������
			 */
			
			BufferedReader br = new BufferedReader(
					new FileReader("target/temp/jp/ossc/nimbus/service/http/httpclient/help_output.txt"));
			
			String s;
			StringBuffer sb = new StringBuffer();
			//Request�w�b�_�̌���
			while((s = br.readLine()) != null){
				if(s.startsWith("Content-Type:")){
					assertTrue(s.endsWith("application/xml;charset=Shift_JIS"));
				}
				sb.append(s);				
			}
			br.close();
			//DataSet���e�̌���
			assertTrue(sb.toString().endsWith("sectionCode=022&account=059641&password=059641"));

		} catch (HttpRequestCreateException e) {
			e.printStackTrace();
			fail("��O����");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			fail("��O����");
		} catch (IOException e) {
			e.printStackTrace();
			fail("��O����");
		} finally {
			ServiceManagerFactory
					.unloadManager("jp/ossc/nimbus/service/http/httpclient/service-clientTest2.xml");
		}
	}


//	/**
//	 * �w�肳�ꂽ�_���A�N�V�������ɊY�����郊�N�G�X�g�𔭍s����e�X�g�B
//	 * <p>
//	 * �����F
//	 * <ul>
//	 * <li>���̓��e�̒�`�t�@�C�������[�h���AHttpClientFactoryService�C���X�^���X�𐶐�����</li>
//	 * <li>Journal,Sequence�T�[�r�X��`����</li>
//	 * <li>RequestContentType�Fapplication/xml</li>
//	 * <li>RequestCharacterEncoding�FShift_JIS</li>
//	 * <li>RequestHeaders�FAccept-Encoding=gzip</li>
//	 * <li>RequestStreamConverterServiceName�F#DataSetXMLConverter</li>
//	 * <li>ResponseStreamConverterServiceName�F#ResponseStreamConverter</li>
//	 * <li>Proxy�F#localhost:8280</li>
//	 * <li>�_���A�N�V������"login"�̃��N�G�X�g�����`</li>
//	 * <li>HttpClientFactoryService#createHttpClient()�����s���AHttpClient�𐶐�</li>
//	 * <li>HttpRequestImpl#createRequest(�_���A�N�V������)�����s���AHttpRequest�𐶐�</li>
//	 * <li>���M����f�[�^�Z�b�g�����̓��e�Ő������AHttpRequest#setObject()�ŃZ�b�g<BR>
//	 * �X�L�[�}�F:name,java.lang.String,,,\n:age,int,,,<BR>
//	 * �l�@�@�@�Fname=hoge,age=25 </li>
//	 * <li>Proxy�F#localhost:8280</li>
//	 * <li>��������HttpRequest���w�肵�āAHttpClientImpl#executeRequest(request)�����s</li>
//	 * </ul>
//	 * �m�F�F
//	 * <ul>
//	 * <li>���M��Ŏw�肵�����̒l��HTTP���N�G�X�g�ɐ������ݒ肳��Ă��邱�Ƃ��m�F</li>
//	 * <li>���X�|���X�̓��e���������ݒ肳��Ă��邱�Ƃ��m�F</li>
//	 * </ul>
//	 */
//	public void testExecuteRequestWithJournal() {
//		try {
//			if (!ServiceManagerFactory
//					.loadManager("jp/ossc/nimbus/service/http/httpclient/service-clientTest3.xml")) {
//				System.exit(-1);
//			}
//			final HttpClientFactory factory = (HttpClientFactory) ServiceManagerFactory
//					.getServiceObject("HttpClientFactory");
//			HttpClientImpl client = (HttpClientImpl) factory.createHttpClient();
//
//			DataSet requestDs = new DataSet("Login");
//	        requestDs.setHeaderSchema(
//	            "UserInfo",
//	            ":name,java.lang.String,,,\n"
//	             + ":age,int,,,"
//	        );
//	        Header userInfo = requestDs.getHeader("UserInfo");
//	        userInfo.setProperty("name", "hoge");
//	        userInfo.setProperty("age", 25);
//	        
//	        HttpRequest request = factory.createRequest("login");
//	        request.setObject(requestDs);
//
//	        HttpResponse res = client.executeRequest(request);
//			//���X�|���X���e�̊m�F
//			assertEquals(200, res.getStatusCode());
//			assertEquals("testdata", res.getObject());
//				        
//			
//			/*Proxy�e�X�g�p�v���O����(jp.ossc.nimbus.service.http.proxy.TestHttpProcessService)
//			 * �̏o�̓t�@�C���̓��e���m�F���AHTTP���N�G�X�g�f�[�^������
//			 */
//			
//			BufferedReader br = new BufferedReader(
//					new FileReader("jp/ossc/nimbus/service/http/httpclient/help_output.txt"));
//			
//			String s;
//			StringBuffer sb = new StringBuffer();
//			//Request�w�b�_�̌���
//			while((s = br.readLine()) != null){
//				if(s.startsWith("Content-Type:")){
//					assertTrue(s.endsWith("application/xml;charset=Shift_JIS"));
//				}
//				if(s.startsWith("Accept-Encoding")){
//					assertTrue(s.endsWith("gzip"));
//				}
//				sb.append(s);				
//			}
//			br.close();
//			//DataSet���e�̌���
//			assertTrue(sb.toString().endsWith(
//					"<dataSet name=\"Login\"><schema>" +
//					"<header name=\"UserInfo\">:name,java.lang.String,,,:age,int,,,</header>" +
//					"</schema><header name=\"UserInfo\">" +
//					"<name>hoge</name><age>25</age></header></dataSet>"));
//			//Journal�̓��e����
//			HttpClientFactoryService hcf = (HttpClientFactoryService)factory;
//			final ObjectMappedEditorFinderService finder = 
//				(ObjectMappedEditorFinderService) ServiceManagerFactory.getServiceObject("JournalEditorFinder");
//			
//			assertEquals("",hcf.journal.getCurrentJournalString(finder));	
//			
//			
//			
//		} catch (HttpRequestCreateException e) {
//			e.printStackTrace();
//			fail("��O����");
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//			fail("��O����");
//		} catch (IOException e) {
//			e.printStackTrace();
//			fail("��O����");
//		} finally {
//			ServiceManagerFactory
//					.unloadManager("jp/ossc/nimbus/service/http/httpclient/service-clientTest3.xml");
//		}
//	}

		/**
		 * �w�肳�ꂽ�_���A�N�V�������ɊY�����郊�N�G�X�g�𔭍s����e�X�g�B
		 * <p>
		 * �����F
		 * <ul>
		 * <li>���̓��e�̒�`�t�@�C�������[�h���AHttpClientFactoryService�C���X�^���X�𐶐�����</li>
		 * <li>Journal,Sequence�T�[�r�X��`����</li>
		 * <li>RequestContentType�Fapplication/xml</li>
		 * <li>RequestCharacterEncoding�F�w��Ȃ�</li>
		 * <li>RequestHeaders�FAccept-Encoding=gzip</li>
		 * <li>RequestStreamConverterServiceName�F#DataSetXMLConverter</li>
		 * <li>ResponseStreamConverterServiceName�F�w��Ȃ�</li>
		 * <li>Proxy�F#localhost:8280</li>
		 * <li>�_���A�N�V������"login"�̃��N�G�X�g�����`</li>
		 * <li>HttpClientFactoryService#createHttpClient()�����s���AHttpClient�𐶐�</li>
		 * <li>HttpRequestImpl#createRequest(�_���A�N�V������)�����s���AHttpRequest�𐶐�</li>
		 * <li>���M����f�[�^�Z�b�g�����̓��e�Ő������AHttpRequest#setObject()�ŃZ�b�g<BR>
		 * �X�L�[�}�F:name,java.lang.String,,,\n:age,int,,,<BR>
		 * �l�@�@�@�Fname=hoge,age=25 </li>
		 * <li>Proxy�F#localhost:8280</li>
	     * <li>���X�|���X��body��Null���Ԃ��Ă��邱�Ƃ�z��</li>
		 * <li>��������HttpRequest���w�肵�āAHttpClientImpl#executeRequest(request)�����s</li>
		 * </ul>
		 * �m�F�F
		 * <ul>
		 * <li>���M��Ŏw�肵�����̒l��HTTP���N�G�X�g�ɐ������ݒ肳��Ă��邱�Ƃ��m�F</li>
		 * </ul>
		 */
		public void testExecuteRequestWithNoRequestCharacterEncoding() {
			try {
				if (!ServiceManagerFactory
						.loadManager("jp/ossc/nimbus/service/http/httpclient/service-clientTest4.xml")) {
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

		        HttpResponse res = client.executeRequest(request);
				//���X�|���X���e�̊m�F
				assertEquals(200, res.getStatusCode());
				assertNull(res.getObject());
				
				
				/*Proxy�e�X�g�p�v���O����(jp.ossc.nimbus.service.http.proxy.TestHttpProcessService)
				 * �̏o�̓t�@�C���̓��e���m�F���AHTTP���N�G�X�g�f�[�^������
				 */
				
				BufferedReader br = new BufferedReader(
						new FileReader("target/temp/jp/ossc/nimbus/service/http/httpclient/help_output.txt"));
				
				String s;
				StringBuffer sb = new StringBuffer();
				//Request�w�b�_�̌���
				while((s = br.readLine()) != null){
					if(s.startsWith("Content-Type:")){
						assertTrue(s.endsWith("application/xml"));
					}
					if(s.startsWith("Accept-Encoding")){
						assertTrue(s.endsWith("gzip"));
					}
					sb.append(s);				
				}
				br.close();
				//DataSet���e�̌���
				assertTrue(sb.toString().endsWith(
						"<dataSet name=\"Login\"><schema>" +
						"<header name=\"UserInfo\">:name,java.lang.String,,,:age,int,,,</header>" +
						"</schema><header name=\"UserInfo\">" +
						"<name>hoge</name><age>25</age></header></dataSet>"));
				
				
			} catch (HttpRequestCreateException e) {
				e.printStackTrace();
				fail("��O����");
			} catch (FileNotFoundException e) {
				e.printStackTrace();
				fail("��O����");
			} catch (IOException e) {
				e.printStackTrace();
				fail("��O����");
			} finally {
				ServiceManagerFactory
						.unloadManager("jp/ossc/nimbus/service/http/httpclient/service-clientTest4.xml");
			}
	}


		/**
		 * �w�肳�ꂽ�_���A�N�V�������ɊY�����郊�N�G�X�g�𔭍s����e�X�g�B
		 * <p>
		 * �����F
		 * <ul>
		 * <li>���̓��e�̒�`�t�@�C�������[�h���AHttpClientFactoryService�C���X�^���X�𐶐�����</li>
		 * <li>RequestContentType�Fapplication/xml</li>
		 * <li>RequestHeaders(Accept-Encoding)�Fgzip</li>
		 * <li>RequestHeaders(Content-Encoding)�Fgzip(���N�G�X�g�f�[�^�����k)</li>
		 * <li>RequestStreamConverterServiceName�F#DataSetXMLConverter</li>
		 * <li>ResponseStreamConverterServiceName�F#ResponseStreamConverter</li>
		 * <li>ResponseHeaders�FContentType=application/xml</li>
		 * <li>Proxy�F#localhost:8280</li>
		 * <li>�_���A�N�V������"login"�̃��N�G�X�g�����`</li>
		 * <li>HttpClientFactoryService#createHttpClient()�����s���AHttpClient�𐶐�</li>
		 * <li>HttpRequestImpl#createRequest(�_���A�N�V������)�����s���AHttpRequest�𐶐�</li>
		 * <li>���M����f�[�^�Z�b�g�����̓��e�Ő������AHttpRequest#setObject()�ŃZ�b�g<BR>
		 * �X�L�[�}�F:name,java.lang.String,,,\n:age,int,,,<BR>
		 * �l�@�@�@�Fname=hoge,age=25 </li>
		 * <li>��������HttpRequest���w�肵�āAHttpClientImpl#executeRequest(request)�����s</li>
		 * </ul>
		 * �m�F�F
		 * <ul>
		 * <li>���M��Ŏw�肵�����̒l��HTTP���N�G�X�g�ɐ������ݒ肳��Ă��邱�Ƃ��m�F</li>
		 * </ul>
		 */
		public void testExecuteRequestWithCompressDataSet() {
			try {
				if (!ServiceManagerFactory
						.loadManager("jp/ossc/nimbus/service/http/httpclient/service-clientTestComp.xml")) {
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

		        HttpResponse res = client.executeRequest(request);
				//���X�|���X���e�̊m�F
				assertEquals(200, res.getStatusCode());
				assertEquals("testdata", res.getObject());
				
				/*Proxy�e�X�g�p�v���O����(jp.ossc.nimbus.service.http.proxy.TestHttpProcessService)
				 * �̏o�̓t�@�C���̓��e���m�F���AHTTP���N�G�X�g�f�[�^������
				 */
				
				BufferedReader br = new BufferedReader(
						new FileReader("target/temp/jp/ossc/nimbus/service/http/httpclient/help_output.txt"));
				
				String s;
				StringBuffer sb = new StringBuffer();
				//Request�w�b�_�̌���
				while((s = br.readLine()) != null){
					if(s.startsWith("Content-Type:")){
						assertTrue(s.endsWith("application/xml"));
					}
					if(s.startsWith("Content-Encoding:")){
						assertTrue(s.endsWith("gzip"));
					}
					if(s.startsWith("Accept-Encoding:")){
						assertTrue(s.endsWith("gzip"));
					}
					sb.append(s);				
				}
				br.close();
				//DataSet���e�̌���
				assertTrue(sb.toString().endsWith(
						"<dataSet name=\"Login\"><schema>" +
						"<header name=\"UserInfo\">:name,java.lang.String,,,:age,int,,,</header>" +
						"</schema><header name=\"UserInfo\">" +
						"<name>hoge</name><age>25</age></header></dataSet>"));

			} catch (HttpRequestCreateException e) {
				e.printStackTrace();
				fail("��O����");
			} catch (FileNotFoundException e) {
				e.printStackTrace();
				fail("��O����");
			} catch (IOException e) {
				e.printStackTrace();
				fail("��O����");
			} catch (Exception e) {
				e.printStackTrace();
				fail("��O����");
			} finally {
				ServiceManagerFactory
						.unloadManager("jp/ossc/nimbus/service/http/httpclient/service-clientTestComp.xml");
			}
		}


		/**
		 * �w�肳�ꂽ�_���A�N�V�������ɊY�����郊�N�G�X�g�𔭍s����e�X�g�B
		 * <p>
		 * �����F
		 * <ul>
		 * <li>���̓��e�̒�`�t�@�C�������[�h���AHttpClientFactoryService�C���X�^���X�𐶐�����</li>
		 * <li>RequestContentType�Fapplication/xml</li>
		 * <li>RequestDeflateLength�F1000�i1000�o�C�g�ȉ��̃t�@�C���͈��k���Ȃ��j</li>
		 * <li>RequestHeaders(Accept-Encoding)�Fgzip</li>
		 * <li>RequestHeaders(Content-Encoding)�Fgzip(���N�G�X�g�f�[�^�����k)</li>
		 * <li>RequestStreamConverterServiceName�F#DataSetXMLConverter</li>
		 * <li>ResponseStreamConverterServiceName�F#ResponseStreamConverter</li>
		 * <li>ResponseHeaders�FContentType=application/xml</li>
		 * <li>Proxy�F#localhost:8280</li>
		 * <li>�_���A�N�V������"login"�̃��N�G�X�g�����`</li>
		 * <li>HttpClientFactoryService#createHttpClient()�����s���AHttpClient�𐶐�</li>
		 * <li>HttpRequestImpl#createRequest(�_���A�N�V������)�����s���AHttpRequest�𐶐�</li>
		 * <li>���M����f�[�^�Z�b�g(1000�o�C�g�ȉ�)�����̓��e�Ő������AHttpRequest#setObject()�ŃZ�b�g<BR>
		 * �X�L�[�}�F:name,java.lang.String,,,\n:age,int,,,<BR>
		 * �l�@�@�@�Fname=hoge,age=25 </li>
		 * <li>��������HttpRequest���w�肵�āAHttpClientImpl#executeRequest(request)�����s</li>
		 * </ul>
		 * �m�F�F
		 * <ul>
		 * <li>���M��Ŏw�肵�����̒l��HTTP���N�G�X�g�ɐ������ݒ肳��Ă��邱�Ƃ��m�F</li>
		 * <li>�񈳏k�����ɂȂ�̂Ńw�b�_������Content-Encoding���폜����Ă��邱�Ƃ��m�F</li>
		 * </ul>
		 */
		public void testExecuteRequestDeflateLength () {
			try {
				if (!ServiceManagerFactory
						.loadManager("jp/ossc/nimbus/service/http/httpclient/service-clientTestDeflen.xml")) {
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

		        HttpResponse res = client.executeRequest(request);
				//���X�|���X���e�̊m�F
				assertEquals(200, res.getStatusCode());
				assertEquals("testdata", res.getObject());
				
				/*Proxy�e�X�g�p�v���O����(jp.ossc.nimbus.service.http.proxy.TestHttpProcessService)
				 * �̏o�̓t�@�C���̓��e���m�F���AHTTP���N�G�X�g�f�[�^������
				 */
				
				BufferedReader br = new BufferedReader(
						new FileReader("target/temp/jp/ossc/nimbus/service/http/httpclient/help_output.txt"));
				
				String s;
				StringBuffer sb = new StringBuffer();
				//Request�w�b�_�̌���
				while((s = br.readLine()) != null){
					if(s.startsWith("Content-Type:")){
						assertTrue(s.endsWith("application/xml"));
					}
					if(s.startsWith("Content-Encoding:")){
						fail("Content-Encodin���폜����ĂȂ����ߎ��s");
					}
					sb.append(s);				
				}
				br.close();
				//DataSet���e�̌���
				assertTrue(sb.toString().endsWith(
						"<dataSet name=\"Login\"><schema>" +
						"<header name=\"UserInfo\">:name,java.lang.String,,,:age,int,,,</header>" +
						"</schema><header name=\"UserInfo\">" +
						"<name>hoge</name><age>25</age></header></dataSet>"));

			} catch (HttpRequestCreateException e) {
				e.printStackTrace();
				fail("��O����");
			} catch (FileNotFoundException e) {
				e.printStackTrace();
				fail("��O����");
			} catch (IOException e) {
				e.printStackTrace();
				fail("��O����");
			} catch (Exception e) {
				e.printStackTrace();
				fail("��O����");
			} finally {
				ServiceManagerFactory
						.unloadManager("jp/ossc/nimbus/service/http/httpclient/service-clientTestDeflen.xml");
			}
		}	
}
