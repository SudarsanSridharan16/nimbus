package jp.ossc.nimbus.service.http.httpclient;

import org.apache.commons.httpclient.*;
import jp.ossc.nimbus.core.ServiceManagerFactory;
import jp.ossc.nimbus.service.http.HttpClientFactory;
import jp.ossc.nimbus.service.http.HttpRequestCreateException;
import junit.framework.TestCase;

public class GetHttpRequestImplTest extends TestCase {

	public GetHttpRequestImplTest(String arg0) {
		super(arg0);
	}
	public static void main(String[] args) {
		 junit.textui.TestRunner.run(GetHttpRequestImplTest.class); }

	
	/**
	 * ���N�G�X�g�p�����[�^���N�G���Ƃ��Đݒ肷��e�X�g�B
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
	 * <li>��������HttpRequest���w�肵�āAHttpClientImpl#createHttpMethod()�����s</li>
	 * <li>��������HttpMethod�A�p�����[�^�}�b�v���w�肵�āAGetHttpClientImpl#initParameter()�����s</li>
	 * </ul>
	 * �m�F�F
	 * <ul>
	 * <li>�w�肵���p�����[�^���N�G���Ƃ��Đݒ肳��邱�Ƃ�GetHttpClientImpl#getQueryString()�Ŋm�F</li>
	 * </ul>
	 */
	public void testExecuteRequestGET() {
		try {
			if (!ServiceManagerFactory
					.loadManager("jp/ossc/nimbus/service/http/httpclient/service-clientTestGET.xml")) {
				System.exit(-1);
			}
			final HttpClientFactory factory = (HttpClientFactory) ServiceManagerFactory
					.getServiceObject("HttpClientFactory");

	        
	        GetHttpRequestImpl request = (GetHttpRequestImpl)factory.createRequest("login");
	        
	        HttpMethodBase method = request.createHttpMethod();
	        //�p�����[�^���ݒ�
	        request.setParameter("nameA","valueA");
	        
	        String[] vals = new String[]{"valueB1","valueB2"};
	        request.setParameters("nameB", vals);
	        
	        //���N�G�X�g�p�����[�^���N�G���Ƃ��Đݒ�
	        request.initParameter(method, request.getParameterMap());
	        //�������ݒ肳��Ă��邩�m�F
	        assertEquals("nameA=valueA&nameB=valueB1&nameB=valueB2", method.getQueryString());
	        

		} catch (HttpRequestCreateException e) {
			e.printStackTrace();
			fail("��O����");
		} catch (Exception e) {
			e.printStackTrace();
			fail("��O����");
		} finally {
			ServiceManagerFactory
					.unloadManager("jp/ossc/nimbus/service/http/httpclient/service-clientTestGET.xml");
		}
	}


}
