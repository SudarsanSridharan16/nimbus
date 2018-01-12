package jp.ossc.nimbus.service.http.httpclient;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;

import jp.ossc.nimbus.core.ServiceManagerFactory;
import jp.ossc.nimbus.service.http.HttpClientFactory;
import jp.ossc.nimbus.service.http.HttpRequestCreateException;
import jp.ossc.nimbus.service.http.httpclient.HttpClientFactoryService.HttpClientImpl;
import junit.framework.TestCase;

public class HttpRequestImplTest extends TestCase {

	
	 public static void main(String[] args) {
	 junit.textui.TestRunner.run(HttpRequestImplTest.class); }
	 
	
	

	public HttpRequestImplTest(String arg0) {
		super(arg0);
	}


	/**
	 * HttpRequest�Ƀw�b�_����ݒ肷��e�X�g�B
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
	 * <li>��������HttpRequest�ɑ΂���setContentType()�����s���AContentType�w�b�_���ݒ�</li>
	 * <li>��������HttpRequest�ɑ΂���setHeader(),addHeader()�����s���w�b�_���ݒ�</li>
	 * <li>��������HttpRequest���w�肵�āAHttpClientImpl#executeRequest(request)�����s</li>
	 * </ul>
	 * �m�F�F
	 * <ul>
	 * <li>setContentType()�Őݒ肵���w�b�_��񂪗D�悳��邱�Ƃ��m�F</li>
	 * <li>ssetHeader(),addHeader()�ݒ肵���w�b�_��񂪔��f����邱�Ƃ��m�F</li>
	 * </ul>
	 */
	public void testRequestSetHeader() {
		try {
			if (!ServiceManagerFactory
					.loadManager("jp/ossc/nimbus/service/http/httpclient/service-clientTest2.xml")) {
				System.exit(-1);
			}
			final HttpClientFactory factory = (HttpClientFactory) ServiceManagerFactory
					.getServiceObject("HttpClientFactory");
			HttpClientImpl client = (HttpClientImpl) factory.createHttpClient();

//			DataSet requestDs = new DataSet("Login");
//	        requestDs.setHeaderSchema(
//	            "UserInfo",
//	            ":name,java.lang.String,,,\n"
//	             + ":age,int,,,"
//	        );
//	        Header userInfo = requestDs.getHeader("UserInfo");
//	        userInfo.setProperty("name", "hoge");
//	        userInfo.setProperty("age", 25);
	        
	        HttpRequestImpl request = (HttpRequestImpl)factory.createRequest("login");
//	        request.setObject(requestDs);
	        
	        //�w�b�_���ݒ�
	        request.setContentType("text/html");
	        request.addHeader("Accept","text/html");
	        request.addHeader("Accept","text/html");
	        request.setHeader("Accept-Language","jp");
	        //settr,getter�̓���m�F
	        request.setHttpVersion("1.1");
	        assertEquals("1.1", request.getHttpVersion());
	        request.setDoAuthentication(true);
	        assertTrue(request.isDoAuthentication());
	        request.setFollowRedirects(false);
	        assertFalse(request.isFollowRedirects());
	        request.setHttpMethodParam("TEST", "test");
	        request.setHttpMethodParam("TEST1", "test1");
	        assertEquals("test", request.getHttpMethodParam("TEST"));
	        assertEquals("test1", request.getHttpMethodParam("TEST1"));
	        assertTrue(request.getHttpMethodParamNameSet().contains("TEST"));
	        assertTrue(request.getHttpMethodParamNameSet().contains("TEST1"));
	        

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
					assertTrue(s.endsWith("text/html;charset=Shift_JIS"));
				}
				if(s.startsWith("Accept:")){
					assertTrue(s.endsWith("text/html"));
				}
				if(s.startsWith("Accept-Language:")){
					assertTrue(s.endsWith("jp"));
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


	/**
	 * HttpRequest�Ƀp�����[�^���A���̓X�g���[����ݒ肷��e�X�g�B
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
	 * <li>���M����f�[�^�Z�b�g��XML���̓X�g���[���Ő������AHttpRequest#setInputStream()�ŃZ�b�g<BR>
	 * <li>��������HttpRequest�ɑ΂���setParameter(),setParameter()�����s���A�p�����[�^���ݒ�</li>
	 * </ul>
	 * �m�F�F
	 * <ul>
	 * <li>�ݒ肵���p�����[�^���A���̓X�g���[�������������f����邱�Ƃ��m�F</li>
	 * </ul>
	 */
	public void testRequestSetParamQuely() {
		try {
			if (!ServiceManagerFactory
					.loadManager("jp/ossc/nimbus/service/http/httpclient/service-clientTest2.xml")) {
				System.exit(-1);
			}
			final HttpClientFactory factory = (HttpClientFactory) ServiceManagerFactory
					.getServiceObject("HttpClientFactory");
			HttpClientImpl client = (HttpClientImpl) factory.createHttpClient();

	        //���̓X�g���[����ݒ�
			String inxml = "TEST1234567890";
			InputStream is = new ByteArrayInputStream(inxml.getBytes());
	        
	        HttpRequestImpl request = (HttpRequestImpl)factory.createRequest("login");
	        request.setInputStream(is);
	        
	        //�p�����[�^���ݒ�
	        request.setContentType("text/html");
	        request.setParameter("nameA","valueA");
	        request.setParameter("nameA","valueB");
	        
	        String[] vals = new String[]{"valueB1","valueB2"};
	        request.setParameters("nameB", vals);
	        
	        //�p�����[�^���m�F
	        assertEquals("valueA", request.getParameter("nameA"));
	        String[] getvals = request.getParameters("nameB");
	        assertEquals(vals[0], getvals[0]);
	        assertEquals(vals[1], getvals[1]);
	        
	        

			client.executeRequest(request);
			
	        //���̓X�g���[�������������M���ꂽ���̊m�F
			/*Proxy�e�X�g�p�v���O����(jp.ossc.nimbus.service.http.proxy.TestHttpProcessService)
			 * �̏o�̓t�@�C���̓��e���m�F���AHTTP���N�G�X�g�f�[�^������
			 */
			
			BufferedReader br = new BufferedReader(
					new FileReader("target/temp/jp/ossc/nimbus/service/http/httpclient/help_output.txt"));
			
			String s;
			StringBuffer sb = new StringBuffer();
			//Request�w�b�_�̌���
			while((s = br.readLine()) != null){
				sb.append(s);				
			}
			br.close();
			assertTrue(sb.toString().endsWith("TEST1234567890"));

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

	

}
