package jp.ossc.nimbus.service.aop.interceptor.servlet;

import jp.ossc.nimbus.beans.dataset.*;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.zip.GZIPOutputStream;

import javax.servlet.*;

import jp.ossc.nimbus.service.aop.ServletFilterInvocationContext;
import jp.ossc.nimbus.service.journal.*;
import jp.ossc.nimbus.service.journal.editorfinder.*;
import jp.ossc.nimbus.service.context.*;
import jp.ossc.nimbus.core.ServiceManagerFactory;
import jp.ossc.nimbus.core.ServiceName;
import jp.ossc.nimbus.util.converter.DataSetXMLConverter;
import jp.ossc.nimbus.service.aop.*;

//import com.mockobjects.servlet.*;
import junit.framework.TestCase;

public class StreamExchangeInterceptorServiceTest extends TestCase {

	public StreamExchangeInterceptorServiceTest(String arg0) {
		super(arg0);
	}
    public static void main(String[] args) {
        junit.textui.TestRunner.run(StreamExchangeInterceptorServiceTest.class);
    }


	/**
	 * StreamExchangeInterceptorServiceTest�̊e�v���p�e�B��ݒ�A�擾����e�X�g�B
	 * <p>
	 * �����F
	 * <ul>
	 * <li>StreamExchangeInterceptorServiceTest�C���X�^���X�𐶐�����</li>
	 * <li>�esetter���\�b�h�����s</li>
	 * <li>�egetter���\�b�h�����s</li>
	 * </ul>
	 * �m�F�F
	 * <ul>
	 * <li>����I���B�ݒ肵���l���������擾�ł��邱�Ƃ��m�F</li>
	 * </ul>
	 */
	public void testSetterGetter() {

		StreamExchangeInterceptorService ic = new StreamExchangeInterceptorService();
		
		DataSetXMLConverter xconv = new DataSetXMLConverter();
		ic.setRequestStreamConverter(xconv);
		assertEquals(xconv, ic.getRequestStreamConverter());
		ic.setResponseStreamConverter(xconv);
		assertEquals(xconv, ic.getResponseStreamConverter());
		
		Context context = new ThreadContextService();
		ic.setThreadContext(context);
		assertEquals(context, ic.getThreadContext());
		
		Journal journal = new ThreadManagedJournalService();
		ic.setJournal(journal);
		assertEquals(journal, ic.getJournal());
		
		EditorFinder finder = new ObjectMappedEditorFinderService();
		ic.setExchangeEditorFinder(finder);
		assertEquals(finder, ic.getExchangeEditorFinder());
		ic.setExchangeRequestEditorFinder(finder);
		assertEquals(finder, ic.getExchangeRequestEditorFinder());
		ic.setExchangeResponseEditorFinder(finder);
		assertEquals(finder, ic.getExchangeResponseEditorFinder());
		ic.setRequestBytesEditorFinder(finder);
		assertEquals(finder, ic.getRequestBytesEditorFinder());
		ic.setRequestObjectEditorFinder(finder);
		assertEquals(finder, ic.getRequestObjectEditorFinder());
		ic.setResponseBytesEditorFinder(finder);
		assertEquals(finder, ic.getResponseBytesEditorFinder());
		ic.setResponseObjectEditorFinder(finder);
		assertEquals(finder, ic.getResponseObjectEditorFinder());
		ic.setExceptionEditorFinder(finder);
		assertEquals(finder, ic.getExceptionEditorFinder());
		
		ServiceName sname = new ServiceName("Service");
		ic.setRequestStreamConverterServiceName(sname);
		assertEquals(sname, ic.getRequestStreamConverterServiceName());
		ic.setResponseStreamConverterServiceName(sname);
		assertEquals(sname, ic.getResponseStreamConverterServiceName());
		ic.setThreadContextServiceName(sname);
		assertEquals(sname, ic.getThreadContextServiceName());
		ic.setJournalServiceName(sname);
		assertEquals(sname, ic.getJournalServiceName());
		ic.setExchangeEditorFinderServiceName(sname);
		assertEquals(sname, ic.getExchangeEditorFinderServiceName());
		ic.setExchangeRequestEditorFinderServiceName(sname);
		assertEquals(sname, ic.getExchangeRequestEditorFinderServiceName());
		ic.setExchangeResponseEditorFinderServiceName(sname);
		assertEquals(sname, ic.getExchangeResponseEditorFinderServiceName());
		ic.setRequestBytesEditorFinderServiceName(sname);
		assertEquals(sname, ic.getRequestBytesEditorFinderServiceName());
		ic.setRequestObjectEditorFinderServiceName(sname);
		assertEquals(sname, ic.getRequestObjectEditorFinderServiceName());
		ic.setResponseBytesEditorFinderServiceName(sname);
		assertEquals(sname, ic.getResponseBytesEditorFinderServiceName());
		ic.setResponseObjectEditorFinderServiceName(sname);
		assertEquals(sname, ic.getResponseObjectEditorFinderServiceName());
		ic.setExceptionEditorFinderServiceName(sname);
		assertEquals(sname, ic.getExceptionEditorFinderServiceName());

		ic.setResponseContentType("application/x-www-form-urlencoded");
		assertEquals("application/x-www-form-urlencoded", ic.getResponseContentType());

		ic.setRequestObjectAttributeName("TEST");
		assertEquals("TEST", ic.getRequestObjectAttributeName());
		ic.setResponseObjectAttributeName("TEST");
		assertEquals("TEST", ic.getResponseObjectAttributeName());
		ic.setRequestObjectContextKey("TEST");
		assertEquals("TEST", ic.getRequestObjectContextKey());
		ic.setResponseObjectContextKey("TEST");
		assertEquals("TEST", ic.getResponseObjectContextKey());

		ic.setRequestStreamInflate(false);
		assertEquals(false, ic.isRequestStreamInflate());

		ic.setExchangeJournalKey("TEST");
		assertEquals("TEST", ic.getExchangeJournalKey());
		ic.setExchangeRequestJournalKey("TEST");
		assertEquals("TEST", ic.getExchangeRequestJournalKey());
		ic.setExchangeResponseJournalKey("TEST");
		assertEquals("TEST", ic.getExchangeResponseJournalKey());
		ic.setRequestBytesJournalKey("TEST");
		assertEquals("TEST", ic.getRequestBytesJournalKey());
		ic.setRequestObjectJournalKey("TEST");
		assertEquals("TEST", ic.getRequestObjectJournalKey());
		ic.setResponseBytesJournalKey("TEST");
		assertEquals("TEST", ic.getResponseBytesJournalKey());
		ic.setResponseObjectJournalKey("TEST");
		assertEquals("TEST", ic.getResponseObjectJournalKey());
		ic.setExceptionJournalKey("TEST");
		assertEquals("TEST", ic.getExceptionJournalKey());
		

	}

	/**
	 * �T�[�r�X�J�n�A�I������e�X�g�B
	 * <p>
	 * �����F
	 * <ul>
	 * <li>���̃T�[�r�X���`������`�t�@�C�������[�h���ăT�[�r�X���J�n����</li>
	 * <li>requestStreamConverterService</li>
	 * <li>responseStreamConverterService</li>
	 * <li>threadContextService</li>
	 * <li>journalService</li>
	 * <li>exchangeEditorFinderService</li>
	 * <li>exchangeRequestEditorFinderService</li>
	 * <li>exchangeResponseEditorFinderService</li>
	 * <li>requestBytesEditorFinderService</li>
	 * <li>requestObjectEditorFinderService</li>
	 * <li>responseBytesEditorFinderService</li>
	 * <li>responseObjectEditorFinderService</li>
	 * <li>exceptionEditorFinderService</li>
	 * </ul>
	 * �m�F�F
	 * <ul>
	 * <li>����I���B</li>
	 * </ul>
	 */
	public void testStartService() {
		try {
			if (!ServiceManagerFactory
					.loadManager("jp/ossc/nimbus/service/aop/interceptor/servlet/service-definitionTest.xml")) {
				System.exit(-1);
			}
			final StreamExchangeInterceptorService ic
			= (StreamExchangeInterceptorService) ServiceManagerFactory
					.getServiceObject("StreamExchangeInterceptor");
			ic.startService();
			assertTrue(ic.getRequestStreamConverter() instanceof DataSetXMLConverter);
			assertTrue(ic.getResponseStreamConverter() instanceof DataSetXMLConverter);

			ic.stopService();

		} catch (Exception e) {
			e.printStackTrace();
			fail("��O����");
		} finally {
			ServiceManagerFactory
					.unloadManager("jp/ossc/nimbus/service/aop/interceptor/servlet/service-definitionTest.xml");
		}
	}

	/**
	 * �T�[�r�X�J�n�A�I������e�X�g�B
	 * <p>
	 * �����F
	 * <ul>
	 * <li>�R���o�[�^�̃T�[�r�X���`���Ă��Ȃ���`�t�@�C�������[�h���ăT�[�r�X���J�n����</li>
	 * </ul>
	 * �m�F�F
	 * <ul>
	 * <li>�T�[�r�X�̊J�n�Ɏ��s����B��OIllegalArgumentException���������邱�Ƃ��m�F����</li>
	 * </ul>
	 */
//	public void testStartServiceInvalid() {
//		try {
//			if (!ServiceManagerFactory
//					.loadManager("jp/ossc/nimbus/service/aop/interceptor/servlet/service-definition_Invalid.xml")) {
//				System.exit(-1);
//			}
//			final StreamExchangeInterceptorService ic
//			= (StreamExchangeInterceptorService) ServiceManagerFactory
//					.getServiceObject("StreamExchangeInterceptor");
//			ic.startService();
//			fail("��O���������Ȃ����߃e�X�g���s ");
//
//		} catch (IllegalArgumentException e) {
//		} catch (Exception e) {
//		} finally {
//			ServiceManagerFactory
//					.unloadManager("jp/ossc/nimbus/service/aop/interceptor/servlet/service-definition_Invalid.xml");
//		}
//	}


	/**
	 * Converter���g���ăX�g���[���Ɠ���I�u�W�F�N�g�̌������s���e�X�g�B
	 * <p>
	 * �����F
	 * <ul>
	 * <li>���̃T�[�r�X���`������`�t�@�C�������[�h���ăT�[�r�X���J�n����</li>
	 * <li>requestStreamConverterService</li>
	 * <li>responseStreamConverterService</li>
	 * <li>ServletRequest/Response�̃��b�N�𐶐�</li>
	 * <li>ServletRequest��InputStream�p�����[�^�ɕϊ���XML�X�g���[����ݒ�</li>
	 * <li>��L�C���X�^���X���g���ăR���e�L�X�g�C���X�^���X�𐶐����A<BR>
	 * invokeFilter(context, chain)�����s����</li>
	 * </ul>
	 * �m�F�F
	 * <ul>
	 * <li>HttpServletRequest�ɐݒ肳��Ă���ϊ�����XML�X�g���[�����������ϊ����邱�Ƃ��m�F����</li>
	 * </ul>
	 */
	public void testInvokeFilter() {
		try {
			if (!ServiceManagerFactory
					.loadManager("jp/ossc/nimbus/service/aop/interceptor/servlet/service-definitionTest.xml")) {
				System.exit(-1);
			}
			final StreamExchangeInterceptorService ic
			= (StreamExchangeInterceptorService) ServiceManagerFactory
					.getServiceObject("StreamExchangeInterceptor");
			ic.startService();
			
			//ServletRequest��InputStream�p�����[�^�ɕϊ���XML�X�g���[����ݒ�
			String inxml = "<?xml version=\"1.0\" encoding=\"Shift_JIS\"?>\n" +
			"<dataSet><schema><header name=\"TestHeader\">" +
			":name,java.lang.String,,,\n:password,java.lang.String,,,"  +
							"</header></schema><header name=\"TestHeader\">" +
							"<name>TestName</name><password>TestPassWord</password></header></dataSet>";
			//ServletinputStream�̃Z�b�g
			MockServletInputStream is = new MockServletInputStream(inxml.getBytes());
			//ServletRequest/Response�̃��b�N�𐶐�
			MockHttpServletRequest req = new MockHttpServletRequest(is);			
			ServletResponse res = new MockHttpServletResponse();
			MockFilterChain chain = new MockFilterChain();
			//�G���R�[�f�B���O�Z�b�g
			req.setCharacterEncoding("Shift_JIS");
			//�R���e�L�X�g�쐬			
			ServletFilterInvocationContext context = 
				new ServletFilterInvocationContext((ServletRequest)req,res,(javax.servlet.FilterChain)chain);

			//�C���^�Z�v�^�C���X�^���X����
			DefaultInterceptorChain ichain = new DefaultInterceptorChain();
			//���̓X�g���[���͔񈳏k
			ic.setRequestStreamInflate(false);
			ic.invokeFilter(context, (InterceptorChain)ichain);
			//�ϊ���̃f�[�^����
			DataSet ds = (DataSet)req.getAttribute(ic.requestObjectAttributeName);
			assertEquals("TestName", ds.getHeader("TestHeader").getProperty("name"));
			assertEquals("TestPassWord", ds.getHeader("TestHeader").getProperty("password"));
			
			
			ic.stopService();
			

		} catch (Throwable e) {
			e.printStackTrace();
			fail("��O����");
		} finally {
			ServiceManagerFactory
					.unloadManager("jp/ossc/nimbus/service/aop/interceptor/servlet/service-definitionTest.xml");
		}
	}



	/**
	 * ���̓X�g���[���̈��k����������e�X�g�B
	 * <p>
	 * �����F
	 * <ul>
	 * <li>HttpServletRequest�I�u�W�F�N�g�ƈ��k�f�[�^�̃X�g���[�����w�肵��<BR>
	 * decompress(HttpServletRequest request, InputStream is) �����s����</li>
	 * </ul>
	 * �m�F�F
	 * <ul>
	 * <li>����ɉ𓀂��s��ꂽ���Ƃ��m�F����</li>
	 * </ul>
	 */
	public void testDecompress() {
		try{
		StreamExchangeInterceptorService ic = new StreamExchangeInterceptorService();
		//ServletRequest/Response�̃��b�N�𐶐�
		MockHttpServletRequest req = new MockHttpServletRequest();			
		//�G���R�[�f�B���O�Z�b�g
		//���k�f�[�^�̃X�g���[������(HttpRequestImpl#compress()�ň��k�f�[�^�X�g���[�����쐬)
		String inxml = "<?xml version=\"1.0\" encoding=\"Shift_JIS\"?>\n" +
		"<dataSet><schema><header name=\"TestHeader\">" +
		":name,java.lang.String,,,\n:password,java.lang.String,,,"  +
						"</header></schema><header name=\"TestHeader\">" +
						"<name>TestName</name><password>TestPassWord</password></header></dataSet>";
		InputStream is = compress(inxml.getBytes());
		BufferedInputStream ois = new BufferedInputStream(ic.decompress(req, is));
		BufferedReader br = new BufferedReader(new InputStreamReader(ois));
		assertEquals("<?xml version=\"1.0\" encoding=\"Shift_JIS\"?>", br.readLine());
		assertEquals("<dataSet><schema><header name=\"TestHeader\">:name,java.lang.String,,,", br.readLine());
		assertEquals(":password,java.lang.String,,,</header></schema><header name=\"TestHeader\">" +
				"<name>TestName</name><password>TestPassWord</password></header></dataSet>", br.readLine());
		
		}catch (IOException e) {
			e.printStackTrace();
			fail("��O����");
		}
	}

    /**
     * ���̓X�g���[�������k����B<p>
     */
    protected InputStream compress(byte[] inputBytes) throws IOException {
        // �w�b�_�[[Content-Encoding]�̒l���擾
        final ByteArrayOutputStream baos = new ByteArrayOutputStream();
        OutputStream os = baos;
        // gzip���k
        os = new GZIPOutputStream(os);
        os.write(inputBytes, 0, inputBytes.length);
        os.flush();
        os.close();
        return new ByteArrayInputStream(baos.toByteArray());
    }

 
    
	
}
