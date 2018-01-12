package jp.ossc.nimbus.service.aop.interceptor.servlet;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;


import jp.ossc.nimbus.core.ServiceManagerFactory;
import jp.ossc.nimbus.service.aop.DefaultInterceptorChain;
import jp.ossc.nimbus.service.aop.InterceptorChain;
import jp.ossc.nimbus.service.aop.ServletFilterInvocationContext;
import junit.framework.TestCase;

public class HttpServletResponseDeflateInterceptorServiceTest extends TestCase {

	public HttpServletResponseDeflateInterceptorServiceTest(String arg0) {
		super(arg0);
	}
    public static void main(String[] args) {
        junit.textui.TestRunner.run(HttpServletResponseDeflateInterceptorServiceTest.class);
    }
	/**
	 * HttpServletResponseDeflateInterceptorService�̊e�v���p�e�B��ݒ�A�擾����e�X�g�B
	 * <p>
	 * �����F
	 * <ul>
	 * <li>HttpServletResponseDeflateInterceptorService�C���X�^���X�𐶐�����</li>
	 * <li>�esetter���\�b�h�����s</li>
	 * <li>�egetter���\�b�h�����s</li>
	 * </ul>
	 * �m�F�F
	 * <ul>
	 * <li>����I���B�ݒ肵���l���������擾�ł��邱�Ƃ��m�F</li>
	 * </ul>
	 */
	public void testSetterGetter() {

		HttpServletResponseDeflateInterceptorService ic = new HttpServletResponseDeflateInterceptorService();
		
		String[] contentTypes = new String[]{"text/html"};
		ic.setEnabledContentTypes(contentTypes);
		assertEquals(contentTypes[0], ic.getEnabledContentTypes()[0]);
		String[] contentTypes2 = new String[]{"text/xml"};
		ic.setDisabledContentTypes(contentTypes2);
		assertEquals(contentTypes2[0], ic.getDisabledContentTypes()[0]);
		ic.setDeflateLength(1000000);
		assertEquals(1000000, ic.getDeflateLength());		

	}

	/**
	 * ���X�|���X�����k�������s�����b�p�[�Ń��b�v���āA���̃C���^�[�Z�v�^���Ăяo���e�X�g�B
	 * <p>
	 * �����F
	 * <ul>
	 * <li>HttpServletResponseDeflateInterceptorService�C���X�^���X�𐶐�����</li>
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
					.loadManager("jp/ossc/nimbus/service/aop/interceptor/servlet/service-definitionTestdef.xml")) {
				System.exit(-1);
			}
			final HttpServletResponseDeflateInterceptorService ic
			= (HttpServletResponseDeflateInterceptorService) ServiceManagerFactory
					.getServiceObject("HttpServletResponseDeflateInterceptor");
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
			ic.invokeFilter(context, (InterceptorChain)ichain);
			
			

		} catch (Throwable e) {
			e.printStackTrace();
			fail("��O����");
		} finally {
			ServiceManagerFactory
					.unloadManager("jp/ossc/nimbus/service/aop/interceptor/servlet/service-definitionTestdef.xml");
		}
	}

	/**
	 * ���X�|���X���k�������s�킸�ɁA���̃C���^�[�Z�v�^���Ăяo���e�X�g�B
	 * <p>
	 * �����F
	 * <ul>
	 * <li>HttpServletResponseDeflateInterceptorService�C���X�^���X�𐶐�����</li>
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
	public void testInvokeFilterNoDef() {
		try {
			if (!ServiceManagerFactory
					.loadManager("jp/ossc/nimbus/service/aop/interceptor/servlet/service-definitionTestdef.xml")) {
				System.exit(-1);
			}
			final HttpServletResponseDeflateInterceptorService ic
			= (HttpServletResponseDeflateInterceptorService) ServiceManagerFactory
					.getServiceObject("HttpServletResponseDeflateInterceptor");
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
			MockHttpServletRequest2 req = new MockHttpServletRequest2(is);			
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
			ic.invokeFilter(context, (InterceptorChain)ichain);
			
			

		} catch (Throwable e) {
			e.printStackTrace();
			fail("��O����");
		} finally {
			ServiceManagerFactory
					.unloadManager("jp/ossc/nimbus/service/aop/interceptor/servlet/service-definitionTestdef.xml");
		}
	}


}
