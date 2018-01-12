package jp.ossc.nimbus.service.aop.interceptor.servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.security.Principal;
import java.util.Enumeration;
import java.util.Locale;
import java.util.Map;
import java.util.HashMap;
import java.util.StringTokenizer;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletInputStream;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class MockHttpServletRequest implements HttpServletRequest {
	protected ServletInputStream inputStream;
	
    protected String address;
    protected String name;
    protected int port;
    protected String characterEncoding;
    protected HashMap map = new HashMap();

	public MockHttpServletRequest() {
		super();
	}
	public MockHttpServletRequest(ServletInputStream inputStream) {
		super();
		this.inputStream = inputStream;
	}

    public String getLocalAddr() {
    	return address;
    }
    public int getLocalPort() {
    	return port;
    }
    public int getRemotePort() {
    	return port;
    }
    public String getLocalName() {
    	return name;
    }
	public String getAuthType() {
		// TODO �����������ꂽ���\�b�h�E�X�^�u
		return null;
	}

	public String getContextPath() {
		// TODO �����������ꂽ���\�b�h�E�X�^�u
		return null;
	}

	public Cookie[] getCookies() {
		// TODO �����������ꂽ���\�b�h�E�X�^�u
		return null;
	}

	public long getDateHeader(String arg0) {
		// TODO �����������ꂽ���\�b�h�E�X�^�u
		return 0;
	}

	public String getHeader(String key) {
        //���X�|���X���k�e�X�g�p�BAccept-Encoding���w�肳�ꂽ��gzip��Ԃ�
    	String val = "gzip";
    	if(key.equals("Accept-Encoding")){
    		return val;
    	}
        return null;
	}

	public Enumeration getHeaderNames() {
		// TODO �����������ꂽ���\�b�h�E�X�^�u
		return null;
	}

	public Enumeration getHeaders(String arg0) {
    	//notImplemented();
        //���k�����e�X�g�p�BContent-Encoding���w�肳�ꂽ��gzip��Ԃ�
    	Enumeration vals = new StringTokenizer("gzip");
    	if(arg0.equals("Content-Encoding")){
    		return vals;
    		
    	}
    		
         return null;
	}

	public int getIntHeader(String arg0) {
		// TODO �����������ꂽ���\�b�h�E�X�^�u
		return 0;
	}

	public String getMethod() {
		// TODO �����������ꂽ���\�b�h�E�X�^�u
		return null;
	}

	public String getPathInfo() {
		// TODO �����������ꂽ���\�b�h�E�X�^�u
		return null;
	}

	public String getPathTranslated() {
		// TODO �����������ꂽ���\�b�h�E�X�^�u
		return null;
	}

	public String getQueryString() {
		// TODO �����������ꂽ���\�b�h�E�X�^�u
		return null;
	}

	public String getRemoteUser() {
		// TODO �����������ꂽ���\�b�h�E�X�^�u
		return null;
	}

	public String getRequestURI() {
		// TODO �����������ꂽ���\�b�h�E�X�^�u
		return null;
	}

	public StringBuffer getRequestURL() {
		// TODO �����������ꂽ���\�b�h�E�X�^�u
		return null;
	}

	public String getRequestedSessionId() {
		// TODO �����������ꂽ���\�b�h�E�X�^�u
		return null;
	}

	public String getServletPath() {
		// TODO �����������ꂽ���\�b�h�E�X�^�u
		return null;
	}

	public HttpSession getSession() {
		// TODO �����������ꂽ���\�b�h�E�X�^�u
		return null;
	}

	public HttpSession getSession(boolean arg0) {
		// TODO �����������ꂽ���\�b�h�E�X�^�u
		return null;
	}

	public Principal getUserPrincipal() {
		// TODO �����������ꂽ���\�b�h�E�X�^�u
		return null;
	}

	public boolean isRequestedSessionIdFromCookie() {
		// TODO �����������ꂽ���\�b�h�E�X�^�u
		return false;
	}

	public boolean isRequestedSessionIdFromURL() {
		// TODO �����������ꂽ���\�b�h�E�X�^�u
		return false;
	}

	public boolean isRequestedSessionIdFromUrl() {
		// TODO �����������ꂽ���\�b�h�E�X�^�u
		return false;
	}

	public boolean isRequestedSessionIdValid() {
		// TODO �����������ꂽ���\�b�h�E�X�^�u
		return false;
	}

	public boolean isUserInRole(String arg0) {
		// TODO �����������ꂽ���\�b�h�E�X�^�u
		return false;
	}

	public Object getAttribute(String arg0) {
  	  return map.get(arg0);
	}

	public Enumeration getAttributeNames() {
		// TODO �����������ꂽ���\�b�h�E�X�^�u
		return null;
	}

	public String getCharacterEncoding() {
		// TODO �����������ꂽ���\�b�h�E�X�^�u
		return null;
	}

	public int getContentLength() {
		// TODO �����������ꂽ���\�b�h�E�X�^�u
		return 0;
	}

	public String getContentType() {
		// TODO �����������ꂽ���\�b�h�E�X�^�u
		return null;
	}

	public ServletInputStream getInputStream() throws IOException {
		return this.inputStream;
	}


	public Locale getLocale() {
		// TODO �����������ꂽ���\�b�h�E�X�^�u
		return null;
	}

	public Enumeration getLocales() {
		// TODO �����������ꂽ���\�b�h�E�X�^�u
		return null;
	}

	public String getParameter(String arg0) {
		// TODO �����������ꂽ���\�b�h�E�X�^�u
		return null;
	}

	public Map getParameterMap() {
		// TODO �����������ꂽ���\�b�h�E�X�^�u
		return null;
	}

	public Enumeration getParameterNames() {
		// TODO �����������ꂽ���\�b�h�E�X�^�u
		return null;
	}

	public String[] getParameterValues(String arg0) {
		// TODO �����������ꂽ���\�b�h�E�X�^�u
		return null;
	}

	public String getProtocol() {
		// TODO �����������ꂽ���\�b�h�E�X�^�u
		return null;
	}

	public BufferedReader getReader() throws IOException {
		// TODO �����������ꂽ���\�b�h�E�X�^�u
		return null;
	}

	public String getRealPath(String arg0) {
		// TODO �����������ꂽ���\�b�h�E�X�^�u
		return null;
	}

	public String getRemoteAddr() {
		// TODO �����������ꂽ���\�b�h�E�X�^�u
		return null;
	}

	public String getRemoteHost() {
		// TODO �����������ꂽ���\�b�h�E�X�^�u
		return null;
	}


	public RequestDispatcher getRequestDispatcher(String arg0) {
		// TODO �����������ꂽ���\�b�h�E�X�^�u
		return null;
	}

	public String getScheme() {
		// TODO �����������ꂽ���\�b�h�E�X�^�u
		return null;
	}

	public String getServerName() {
		// TODO �����������ꂽ���\�b�h�E�X�^�u
		return null;
	}

	public int getServerPort() {
		// TODO �����������ꂽ���\�b�h�E�X�^�u
		return 0;
	}

	public boolean isSecure() {
		// TODO �����������ꂽ���\�b�h�E�X�^�u
		return false;
	}

	public void removeAttribute(String arg0) {
		// TODO �����������ꂽ���\�b�h�E�X�^�u

	}

	public void setAttribute(String arg0, Object arg1) {
  	  map.put(arg0, arg1);

	}

	public void setCharacterEncoding(String arg0){
    this.characterEncoding = arg0;

	}

}
